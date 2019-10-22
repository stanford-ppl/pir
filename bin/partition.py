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
import itertools
import time
import contextlib

Constraint = namedtuple("Constraint", ["ops", "vin", "sin", "vout", "sout"])
Node = namedtuple("Node", ["node", "op", "retime", "comment"])
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

class TimeContext:
    contexts = []
    def __init__(self, name):
        self.name = name
        self._initialized = False

    def __enter__(self):
        self._indent = len(self.contexts)
        self.contexts.append(self)
        self._initialized = True
        self.start = time.time()

    @property
    def elapsed(self):
        if not self._initialized:
            raise ValueError("Time Not Initialized")
        return time.time() - self.start

    def __exit__(self, exc_type, exc_val, exc_tb):
        print(self._indent * "\t", self.name, self.elapsed)
        sys.stdout.flush()
        self._initialized = False
        assert self.contexts.pop() is self

class CVXPartitioner:
    nodes: typing.List[Node]
    edges: typing.List[Edge]
    constraints: Constraint
    cvx_constraints: typing.List

    delay_per_partition = 6
    buffer_capacity = 16
    network_delay = 1

    def __init__(self, nodes, edges, constraints, pre_partitioning):
        self.nodes = nodes[:]
        self.edges = edges[:]
        self.constraints = constraints
        self.cvx_constraints = []
        self.to_print = {}

        # gurobi can't currently handle a warm start, so instead we just initialize with the number of partitions
        self.partitions = len({partition.partition for partition in pre_partitioning})
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
        with TimeContext("Total Op Constraint"):
            total_ops = functools.reduce(operator.add, [
                self.node_to_partition_matrix[self.node_to_loc_map[node.node], :] * node.op for node in self.nodes])
            self._add_constraint(total_ops <= self.constraints.ops)

        with TimeContext("Dependency Constraints"):
            self._init_dep_constraints()

        with TimeContext("Input Constraints"):
            self._init_input_constraints()

        with TimeContext("Output Constraints"):
            self._init_output_constraints()

        with TimeContext("Objective"):
            # self.objective = cvxpy.Minimize(self.delay_gap())
            self.objective = cvxpy.Minimize(self.num_partitions())


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

    @functools.lru_cache(None)
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
            if not push_map:
                continue
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
            if not push_map:
                continue
            exports = []
            for src, destinations in push_map.items():
                src_partition = self.node_partitions[src]
                has_exports = self._project_to_bool(sum(self.node_partitions[dst] - src_partition for dst in destinations))
                update = cvxpy.maximum(has_exports + self.node_to_partition_matrix[src, :] - 1, 0)
                exports.append(update)

            total_movement = functools.reduce(operator.add, exports)
            for src in nodes_with_external_outputs[tp]:
                total_movement += self.node_to_partition_matrix[self.node_to_loc_map[src], :]
            self._add_constraint(total_movement <= output_constraints[tp])

    @functools.lru_cache(None)
    def num_partitions(self):
        return cvxpy.max(self.node_partitions)

    @functools.lru_cache(None)
    def delay_gap(self):
        # Let B be the boolean matrix, and A be our adjacency matrix.
        # Then we have T = proj(B^TAB) is the partition-to-partition transfer matrix
        # Then for i, j > i, we have delay(j) >= 1 + delay[i] - max_delay + max_delay * T[i, j]
        # We then wish to minimize max(delay(j) - delay(i) - max_delay + max_delay * T[i, j])

        delays = cvxpy.Variable(name="Partition Delays", shape=self.partitions, nonneg=True)
        max_delay = self.partitions * (self.delay_per_partition + self.network_delay)

        delay_gaps = []
        # adj[i, j] = i -> j
        for src, dst, _, _ in self.edges:
            if not (self._is_internal_node(src) and self._is_internal_node(dst)):
                continue
            src_loc = self.node_to_loc_map[src]
            dst_loc = self.node_to_loc_map[dst]
            src_partition_vec = self.node_to_partition_matrix[src_loc, :]
            dst_partition_vec = self.node_to_partition_matrix[dst_loc, :]
            for (spart_index, spart_var), (dpart_index, dpart_var) in itertools.product(enumerate(src_partition_vec), enumerate(dst_partition_vec)):
                if spart_index <= dpart_index:
                    continue
                # This constraint is only active if the partition assignments are actually correct.
                is_in_partition = self._multiply_bool(spart_var, dpart_var)
                delay_active = max_delay * is_in_partition - max_delay
                self._add_constraint(delays[dpart_index] >= self.delay_per_partition + delays[spart_index] + delay_active)
                delay_gaps.append(delays[dpart_index] - delays[spart_index] + delay_active)
        return cvxpy.maximum(*delay_gaps)


    def _project_to_bool(self, var):
        projected = cvxpy.Variable(boolean=True, shape=var.shape)
        large_constant = (self.num_nodes * self.partitions) ** 2
        self._add_constraint(var <= projected * large_constant)
        self._add_constraint(var * (1/large_constant) <= projected)
        return projected

    def _multiply_bool(self, a, b):
        return cvxpy.maximum(a + b - 1, 0)

    def _add_bool(self, a, b):
        return self._project_to_bool(a + b)

    def solve(self, **kwargs):
        problem = cvxpy.Problem(self.objective, self.cvx_constraints)
        problem.solve(**kwargs)

    def get_assignment(self):
        return [
            (node.node, int(self.node_partitions.value[self.node_to_loc_map[node.node]]))
            for node in self.nodes
        ]


def partition_solver(nodes, edges, constraint, pre_partitioning, opts):
    with TimeContext("Solver Initalization"):
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
