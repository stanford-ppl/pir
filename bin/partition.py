#!usr/bin/env python

import sys
import os
import csv
import argparse
import numpy as np
import scipy.sparse
import cvxpy
from collections import namedtuple

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
    node_to_loc_map = {node_id.node : i for i, node_id in enumerate(nodes)}
    node_to_partition_map = cvxpy.Variable(name="Mapping", shape=(num_nodes, partitions), boolean=True)
    node_to_partition_map.value = np.fliplr(np.identity(num_nodes))

    stochasticity_constraint = cvxpy.sum(node_to_partition_map, axis=1) == 1
    op_constraint = cvxpy.sum(node_to_partition_map, axis=0) <= constraints.ops

    weights = np.arange(partitions)
    filtered_edges = [edge for edge in edges if edge.src in node_to_loc_map and edge.dst in node_to_loc_map]
    dep_constraint_matrix = scipy.sparse.lil_matrix((len(filtered_edges), num_nodes))
    for i, (src, dst, _, _) in enumerate(filtered_edges):
        dep_constraint_matrix[i, node_to_loc_map[src]] = -1
        dep_constraint_matrix[i, node_to_loc_map[dst]] = 1

    dep_constraint = (dep_constraint_matrix.tocsr() * node_to_partition_map * weights) >= 0

    problem = cvxpy.Problem(cvxpy.Minimize(cvxpy.sum(node_to_partition_map * np.arange(partitions))), [
        stochasticity_constraint,
        op_constraint,
        dep_constraint
    ])
    problem.solve(solver="GLPK_MI", verbose=True, warm_start=True)
    print(np.argmax(node_to_partition_map.value.reshape(num_nodes, partitions), axis=1))

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
