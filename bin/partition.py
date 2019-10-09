#!usr/bin/env python

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


def partition(nodes, edges, constraints: Constraint):
    """
    :param nodes: List of Node objects
    :param edges: List of Edge objects
    :param constraints: Constraint
    :return: Mapping[int, List[Nodes]] a mapping from partition ID to nodes in partition
    """
    # using CVX
    num_nodes = len(nodes)
    partitions = num_nodes
    node_to_loc_map = {node_id.node: i for i, node_id in enumerate(nodes)}
    node_to_partition_map = cvxpy.Variable(name="Mapping", shape=(num_nodes, partitions), boolean=True)
    node_to_partition_map.value = np.fliplr(np.identity(num_nodes))

    node_partitions = cvxpy.Variable(name="Partitions", shape=(num_nodes,), pos=True)
    partition_eq_constraint = node_partitions == node_to_partition_map @ np.arange(partitions)

    stochasticity_constraint = cvxpy.sum(node_to_partition_map, axis=1) == 1
    op_constraint = cvxpy.sum(node_to_partition_map, axis=0) <= constraints.ops

    weights = np.arange(partitions)
    filtered_edges = [edge for edge in edges if edge.src in node_to_loc_map and edge.dst in node_to_loc_map]
    dep_constraint_matrix = scipy.sparse.lil_matrix((len(filtered_edges), num_nodes))
    for i, (src, dst, _, _) in enumerate(filtered_edges):
        dep_constraint_matrix[i, node_to_loc_map[src]] = -1
        dep_constraint_matrix[i, node_to_loc_map[dst]] = 1

    dep_constraint = (dep_constraint_matrix.tocsr() * node_to_partition_map * weights) >= 0

    # in/out degree constraints
    output_limits = {
        "s": constraints.sout,
        "v": constraints.vout
    }

    # these nodes always have an output edge.
    nodes_with_external_outs = {(edge.src, edge.tp) for edge in edges if
                                edge.src in node_to_loc_map and edge.dst not in node_to_loc_map}
    always_out_mask = {
        "s": np.zeros(num_nodes),
        "v": np.zeros(num_nodes)
    }
    for node, tp in nodes_with_external_outs:
        always_out_mask[tp][node_to_loc_map[node]] = 1

    outputs = {}
    for k, mask in always_out_mask.items():
        outputs[k] = mask @ node_to_partition_map

    node_outs = {
        "s" : defaultdict(set),
        "v" : defaultdict(set)
    }
    for src, dst, tp, _ in edges:
        if (src, tp) in nodes_with_external_outs:
            continue
        if src not in node_to_loc_map:
            continue
        node_outs[tp][node_to_loc_map[src]].add(node_to_loc_map[dst])

    approx_constraints = []
    for tp, node_to_out_map in node_outs.items():
        for src, destinations in node_to_out_map.items():
            diff = node_partitions[list(destinations)] - node_partitions[src]
            # diff[i] == 0 means they're on the same partition
            # output_ind = cvxpy.minimum(cvxpy.sum(diff), 1)
            output_ind = cvxpy.max(diff)
            # output_ind != 0 means diff[i] != 0
            # if diff == 0, then this gives 0.
            # if diff == 1, then it's 0 everywhere except for the right location
            outputs[tp] += cvxpy.maximum(output_ind + node_to_partition_map[src, :] - 1, 0)

    output_constraints = [outputs[tp] <= output_limits[tp] for tp in "sv"]
    problem = cvxpy.Problem(cvxpy.Minimize(cvxpy.sum(node_to_partition_map * np.arange(partitions))), [
        stochasticity_constraint,
        op_constraint,
        dep_constraint,
        partition_eq_constraint
    ] + output_constraints + approx_constraints)
    problem.solve(solver="GUROBI", verbose=True, warm_start=True, Threads=64)
    print("SOLVED")
    print(node_partitions.value)


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

    partition(nodes, edges, constraint)


if __name__ == "__main__":
    main()
