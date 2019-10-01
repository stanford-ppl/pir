#!usr/bin/env python

import sys
import os
import csv
import argparse
import numpy as np
import scipy as sp
import scipy.optimize
import scipy.stats
from collections import namedtuple, defaultdict
import functools
import queue

Constraint = namedtuple("Constraint", ["ops", "vin", "sin", "vout", "sout"])
Node = namedtuple("Node", ["node", "op", "comment"])
Edge = namedtuple("Edge", ["src", "dst", "tp", "comment"])

def load_csv(fname, tp):
    with open(fname, "r") as f:
        return [tp(**row) for row in csv.DictReader(f, delimiter=",")]

def construct_similarity_score(center):
    def f(x):
        result = np.maximum(1 - 2 * np.abs(center - x), 0)
        assert(np.all(result >= 0))
        return result
    return f

def non_integer_penalty(x):
    return np.sum(1 - (np.cos(x * 2 * np.pi) + 1) / 2)

def partition(nodes, edges, constraints: Constraint, partitions: int):
    """
    :param nodes: List of Node objects
    :param edges: List of Edge objects
    :param constraints: Constraint
    :param partitions: number of candidate partitions
    :return: Mapping[int, List[Nodes]] a mapping from partition ID to nodes in partition
    """

    remap = {i: s for i, s in enumerate(nodes)}
    num_nodes = len(nodes)

    partitioning = np.random.uniform(0, partitions, num_nodes)

    bounds = scipy.optimize.Bounds(-0.5, partitions - 0.5, keep_feasible=True)

    enforce_integer = True

    for sharpness in np.geomspace(0.125, 32, 10):
        def target(x):
            # minimize the largest used partition id
            # plus a non-integer penalty.
            num_partitions = np.max(x) - np.min(x) + 1
            if enforce_integer:
                return num_partitions + non_integer_penalty(x) * sharpness / 32
            return num_partitions


        # Minimize number of

        def num_nodes_constraint_func(x):
            node_counts = np.empty(partitions, np.float)
            for partition in range(partitions):
                similarity_func = construct_similarity_score(partition)
                node_counts[partition] = np.sum(similarity_func(x))
            return node_counts

        num_nodes_constraint = scipy.optimize.NonlinearConstraint(
            fun = num_nodes_constraint_func,
            lb = 0,
            ub = constraints.ops,
        )
        result = scipy.optimize.minimize(fun = target, x0 = partitioning, method = "SLSQP", constraints = [
            num_nodes_constraint
        ], bounds = bounds, options={"disp": True})
        # result = scipy.optimize.shgo(func = target, constraints = [{
        #     "type": "ineq", "fun": num_nodes_constraint_func
        # }], bounds = [(-0.5, partitions - 0.5)]*num_nodes, callback=lambda x: print("Max:", np.max(x), "X:", x))
        print("Sharpness:", sharpness, "Assignment:", result)
        print("Max Nodes:", num_nodes_constraint_func(result.x))
        partitioning = result.x



def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
    (opts, args) = parser.parse_known_args()

    opts.node = os.path.join(opts.path, "node.csv")
    opts.edge = os.path.join(opts.path, "edge.csv")
    opts.spec = os.path.join(opts.path, "spec.csv")
    opts.partition = os.path.join(opts.path, "part.csv")

    nodes = load_csv(opts.node, Node)
    nodes = [element._replace(op = int(element.op)) for element in nodes]

    edges = load_csv(opts.edge, Edge)
    constraint = load_csv(opts.spec, Constraint)[0]
    constraint = Constraint(*list(map(int, constraint)))

    partition(nodes, edges, constraint, 100)


if __name__ == "__main__":
    main()


