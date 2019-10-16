#!/usr/bin/env python

import sys
import os
import csv
import argparse
import numpy as np
import scipy.sparse
import cvxpy
from collections import namedtuple, defaultdict
import operator
import functools
import typing

Constraint = namedtuple("Constraint", ["ops", "vin", "sin", "vout", "sout"])
Node = namedtuple("Node", ["node", "op", "comment"])
Edge = namedtuple("Edge", ["src", "dst", "tp", "comment"])
NodePartition = namedtuple("NodePartition", ["node", "partition"])


def load_csv(fname, tp, use_fieldnames=False):
    with open(fname, "r") as f:
        return [tp(**row) for row in
                csv.DictReader(f, delimiter=",", fieldnames=tp._fields if use_fieldnames else None)]


def cleaned_name(node_id):
    if node_id[0] == "e":
        return node_id[1:]
    return node_id


class CVXPartitioner:
    nodes: typing.List[Node]
    edges: typing.List[Edge]
    constraints: Constraint
    cvx_constraints: typing.List

    def __init__(self, nodes, edges, constraints, pre_partitioning):
        self.nodes = nodes[:]
        self.edges = edges[:]
        self.constraints = constraints
        self.cvx_constraints = []
        self.to_print = {}

        # gurobi can't currently handle a warm start, so instead we just initialize with the number of partitions
        self.partitions = max(partition.partition for partition in pre_partitioning)
        self.num_nodes = len(nodes)

        print("Initializing problem with", self.num_nodes, "nodes and", self.partitions, "partitions.")

        self.node_to_loc_map = {node_id.node: i for i, node_id in enumerate(nodes)}
        print(self.node_to_loc_map)

        initial_value = np.zeros(self.num_nodes)
        for node, partition in pre_partitioning:
            initial_value[self.node_to_loc_map[str(node)]] = partition

        self.node_to_partition_matrix = cvxpy.Variable(name="Matrix", shape=(self.num_nodes, self.partitions),
                                                       boolean=True)

        # constructs an array of just the partition numbers. Useful for dep constraints.
        self.node_partitions = cvxpy.Variable(name="Partitions", shape=(self.num_nodes,), nonneg=True,
                                              value=initial_value)
        self._add_constraint(self.node_partitions == self.node_to_partition_matrix @ np.arange(self.partitions))

        # stochasticity constraint: each node only maps to 1 partition.
        self._add_constraint(cvxpy.sum(self.node_to_partition_matrix, axis=1) == 1)

        # constraint on number of ops
        total_ops = functools.reduce(operator.add, [
            self.node_to_partition_matrix[self.node_to_loc_map[node.node], :] * node.op for node in self.nodes])
        self._add_constraint(total_ops <= self.constraints.ops)
        self._init_dep_constraints()
        self._init_input_constraints()
        self._init_output_constraints()
        self._init_offsets()
        self.objective = cvxpy.Minimize(self.delay_gap())


    def _add_constraint(self, constraint):
        assert constraint.is_dcp()
        self.cvx_constraints.append(constraint)

    def _is_internal_node(self, node):
        return node in self.node_to_loc_map

    @functools.lru_cache(None)
    def _init_dep_constraints(self):
        filtered_edges = [edge for edge in self.edges if
                          self._is_internal_node(edge.src) and self._is_internal_node(edge.dst)]
        for src, dst, _, _ in filtered_edges:
            self._add_constraint(
                self.node_partitions[self.node_to_loc_map[src]] <= self.node_partitions[self.node_to_loc_map[dst]])

    def _init_input_constraints(self):
        input_constraints = {
            "s": self.constraints.sin,
            "v": self.constraints.vin
        }
        node_push_map = {
            "s": defaultdict(set),
            "v": defaultdict(set)
        }

        for src, dst, tp, _ in self.edges:
            if dst not in self.node_to_loc_map:
                continue
            node_push_map[tp][src].add(dst)

        for tp, push_map in node_push_map.items():
            movement = []
            for src, destinations in push_map.items():
                if not self._is_internal_node(src):
                    # we use this to filter out all of the contributions within a partition.
                    # since external nodes aren't in any relevant partition, they always contribute.
                    base_filter = np.zeros(self.partitions)
                else:
                    base_filter = self.node_to_partition_matrix[self.node_to_loc_map[src], :]

                contributions = functools.reduce(operator.add, [
                    self.node_to_partition_matrix[self.node_to_loc_map[dst]] for dst in destinations])
                movement.append(cvxpy.maximum(self._project_to_bool(contributions) - base_filter, 0))

                self.to_print["output - " + tp] = movement

            self._add_constraint(
                functools.reduce(operator.add, movement) <= input_constraints[tp])

    @functools.lru_cache(None)
    def _init_output_constraints(self):
        output_constraints = {
            "s": self.constraints.sout,
            "v": self.constraints.vout
        }
        # for each node, if it's used anywhere not in the same partition, then it counts.

        # an external output automatically counts
        nodes_with_external_outputs = {
            "s": set(),
            "v": set()
        }
        for src, dst, tp, _ in self.edges:
            if self._is_internal_node(src) and not self._is_internal_node(dst):
                nodes_with_external_outputs[tp].add(src)

        node_push_map = {
            "s": defaultdict(set),
            "v": defaultdict(set)
        }

        for src, dst, tp, _ in self.edges:
            # don't count external nodes
            if not self._is_internal_node(src):
                continue
            # if a node already pushes out to an external node, we know it's guaranteed to have an output.
            if src in nodes_with_external_outputs[tp]:
                continue

            node_push_map[tp][self.node_to_loc_map[src]].add(self.node_to_loc_map[dst])

        for tp, push_map in node_push_map.items():
            exports = []
            for src, destinations in push_map.items():
                local_exports = functools.reduce(operator.add, [
                    self.node_to_partition_matrix[dst, :] for dst in destinations
                ])

                src_partition_data = self.node_to_partition_matrix[src, :]
                # removes the src partition from the exports
                exports_without_src = cvxpy.maximum(self._project_to_bool(local_exports) - src_partition_data, 0)

                # check if any export is made, 0/1 value
                has_exports = self._project_to_bool(cvxpy.sum(exports_without_src))

                update = cvxpy.maximum(has_exports + src_partition_data - 1, 0)
                exports.append(update)

            total_movement = functools.reduce(operator.add, exports)
            for src in nodes_with_external_outputs[tp]:
                total_movement += self.node_to_partition_matrix[self.node_to_loc_map[src], :]
            self._add_constraint(total_movement <= output_constraints[tp])

    @functools.lru_cache(None)
    def _init_offsets(self):
        self.offsets = cvxpy.Variable(shape=self.num_nodes, name="Delay", integer=True)
        for src, dst, _, _ in self.edges:
            if not (self._is_internal_node(src) and self._is_internal_node(dst)):
                continue
            src_id = self.node_to_loc_map[src]
            dst_id = self.node_to_loc_map[dst]
            # if src, dst in the same partition, then their delay is 1 (lb) since we don't track relative positions
            # within a partition.
            # otherwise, their delay is constraints.ops + 1
            is_in_same_partition = self._project_to_bool(
                self.node_partitions[dst_id] - self.node_partitions[src_id])

            self._add_constraint(
                self.offsets[dst_id] >= self.offsets[src_id] + 1 + self.constraints.ops * is_in_same_partition)

    @functools.lru_cache(None)
    def num_partitions(self):
        return cvxpy.max(self.node_partitions)

    @functools.lru_cache(None)
    def delay_gap(self):
        # minimize the maximum delay gap for any particular node.
        delays = []
        for src, dst, _, _ in self.edges:
            if not (self._is_internal_node(src) and self._is_internal_node(dst)):
                continue
            src_id = self.node_to_loc_map[src]
            dst_id = self.node_to_loc_map[dst]
            delays.append(self.offsets[dst_id] - self.offsets[src_id])
        return cvxpy.maximum(*delays)

    def _project_to_bool(self, var):
        projected = cvxpy.Variable(boolean=True, shape=var.shape)
        slack = cvxpy.Variable(shape=var.shape, nonneg=True)
        self._add_constraint(projected + slack >= var)
        self._add_constraint(slack <= projected * self.partitions * self.num_nodes)
        return projected

    def solve(self, **kwargs):
        problem = cvxpy.Problem(self.objective, self.cvx_constraints)
        # for i, v in enumerate(problem.variables()):
        #     print(i, v)
        problem.solve(**kwargs)

    def get_assignment(self):
        return [
            (node.node, int(self.node_partitions.value[self.node_to_loc_map[node.node]]))
            for node in self.nodes
        ]


def partition_solver(nodes, edges, constraint, pre_partitioning, opts):
    solver = CVXPartitioner(nodes, edges, constraint, pre_partitioning)
    solver.solve(solver="GUROBI", verbose=True, warm_start=True, Threads=opts.thread)

    with open(opts.partition, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node, partition in solver.get_assignment():
            writer.writerow([node, partition])
    print("Generate {}".format(opts.partition))


def partition_dummy(nodes, edges, constraint, pre_partitioning, opts):
    with open(opts.partition, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node in nodes:
            writer.writerow([node.node, node.node])
    print("Generate {}".format(opts.partition))


def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
    parser.add_argument('-t', '--thread', type=int, default=1, help='Number of threads for solver')
    (opts, args) = parser.parse_known_args()

    opts.node = os.path.join(opts.path, "node.csv")
    opts.edge = os.path.join(opts.path, "edge.csv")
    opts.spec = os.path.join(opts.path, "spec.csv")
    opts.partition = os.path.join(opts.path, "part.csv")

    nodes = load_csv(opts.node, Node)
    nodes = [element._replace(op=int(element.op)) for element in nodes]

    edges = load_csv(opts.edge, Edge)
    edges = [element._replace(
        src=cleaned_name(element.src),
        dst=cleaned_name(element.dst)
    ) for element in edges]
    constraint = load_csv(opts.spec, Constraint)[0]
    constraint = Constraint(*list(map(int, constraint)))

    pre_partitioning = load_csv(os.path.join(opts.path, "init.csv"), NodePartition, use_fieldnames=True)
    pre_partitioning = [NodePartition(*list(map(int, partition))) for partition in pre_partitioning]

    partition_solver(nodes, edges, constraint, pre_partitioning, opts)
    # partition_dummy(nodes,edges,constraint,pre_partitioning, opts)


if __name__ == "__main__":
    main()
