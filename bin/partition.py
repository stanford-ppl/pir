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


def load_csv(fname, tp):
    with open(fname, "r") as f:
        return [tp(**row) for row in csv.DictReader(f, delimiter=",")]


def cleaned_name(node_id):
    if node_id[0] == "e":
        return node_id[1:]
    return node_id


class CVXPartitioner:
    nodes: typing.List[Node]
    edges: typing.List[Edge]
    constraints: Constraint
    cvx_constraints: typing.List

    def __init__(self, nodes, edges, constraints):
        self.nodes = nodes[:]
        self.edges = edges[:]
        self.constraints = constraints
        self.cvx_constraints = []
        self.to_print = {}

        self.num_nodes = len(nodes)
        self.partitions = self.num_nodes
        self.node_to_loc_map = {node_id.node: i for i, node_id in enumerate(nodes)}
        print(self.node_to_loc_map)
        self.node_to_partition_matrix = cvxpy.Variable(name="Matrix", shape=(self.num_nodes, self.partitions),
                                                       boolean=True,
                                                       value=np.fliplr(np.identity(self.num_nodes)))

        # constructs an array of just the partition numbers. Useful for dep constraints.
        self.node_partitions = cvxpy.Variable(name="Partitions", shape=(self.num_nodes,), nonneg=True)
        self.cvx_constraints.append(self.node_partitions == self.node_to_partition_matrix @ np.arange(self.partitions))

        # stochasticity constraint: each node only maps to 1 partition.
        self.cvx_constraints.append(cvxpy.sum(self.node_to_partition_matrix, axis=1) == 1)

        # constraint on number of ops
        total_ops = functools.reduce(operator.add, [
            self.node_to_partition_matrix[self.node_to_loc_map[node.node], :] * node.op for node in self.nodes])
        self.cvx_constraints.append(total_ops <= self.constraints.ops)
        self._init_dep_constraints()
        self._init_input_constraints()
        self._init_output_constraints()

    def _init_dep_constraints(self):
        filtered_edges = [edge for edge in self.edges if
                          edge.src in self.node_to_loc_map and edge.dst in self.node_to_loc_map]
        for src, dst, _, _ in filtered_edges:
            self.cvx_constraints.append(
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
                if src not in self.node_to_loc_map:
                    # we use this to filter out all of the contributions within a partition.
                    # since external nodes aren't in any relevant partition, they always contribute.
                    base_filter = np.zeros(self.partitions)
                else:
                    base_filter = self.node_to_partition_matrix[self.node_to_loc_map[src], :]

                contributions = functools.reduce(operator.add, [
                    self.node_to_partition_matrix[self.node_to_loc_map[dst]] for dst in destinations])
                movement.append(cvxpy.maximum(self._project_to_bool(contributions) - base_filter, 0))

                self.to_print["output - " + tp] = movement

            self.cvx_constraints.append(
                functools.reduce(operator.add, movement) <= input_constraints[tp])

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
            if src in self.node_to_loc_map and dst not in self.node_to_loc_map:
                nodes_with_external_outputs[tp].add(src)

        node_push_map = {
            "s": defaultdict(set),
            "v": defaultdict(set)
        }

        for src, dst, tp, _ in self.edges:
            # don't count external nodes
            if src not in self.node_to_loc_map:
                continue
            # if a node already pushes out to an external node, we know it's guaranteed to have an output.
            if src in nodes_with_external_outputs[tp]:
                continue

            node_push_map[tp][self.node_to_loc_map[src]].add(self.node_to_loc_map[dst])

        print(node_push_map)

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
            self.cvx_constraints.append(total_movement <= output_constraints[tp])

    def _objective(self):
        return cvxpy.Minimize(cvxpy.max(self.node_partitions))

    def _project_to_bool(self, var):
        projected = cvxpy.Variable(boolean=True, shape=var.shape)
        slack = cvxpy.Variable(shape=var.shape, nonneg=True)
        self.cvx_constraints.append(projected + slack >= var)
        self.cvx_constraints.append(slack <= projected * self.partitions * self.num_nodes)
        return projected

    def solve(self, **kwargs):
        problem = cvxpy.Problem(self._objective(), self.cvx_constraints)
        problem.solve(**kwargs)

    def get_assignment(self):
        return [
            (node.node, int(self.node_partitions.value[self.node_to_loc_map[node.node]]))
            for node in self.nodes
        ]


def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
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

    # partition(nodes, edges, constraint)
    solver = CVXPartitioner(nodes, edges, constraint)
    solver.solve(solver="GUROBI", verbose=True, warm_start=True, Threads=64)

    with open(opts.partition, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node, partition in solver.get_assignment():
            writer.writerow([node, partition])


if __name__ == "__main__":
    main()
