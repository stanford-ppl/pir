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
        result = np.maximum(1 - np.abs(center - x), 0)
        assert (np.all(result >= 0))
        return result

    return f


def construct_num_nodes_constraint_func(partitions, weight_mask):
    def compute_node_counts(x):
        node_counts = np.empty(partitions, np.float)
        for partition in range(partitions):
            similarity_func = construct_similarity_score(partition)
            node_counts[partition] = np.sum(similarity_func(x) * weight_mask)
        return node_counts

    return compute_node_counts


def non_integer_penalty(x):
    return np.sum(1 - (np.cos(x * 2 * np.pi) + 1) / 2)


def downshift(x):
    return x - np.min(x)


def shuffle(x):
    shuffles = np.arange(np.floor(np.min(x)), np.ceil(np.max(x)) + 1)
    np.random.shuffle(shuffles)
    new_x = np.empty_like(x)
    for i in range(len(x)):
        bucket = np.round(x[i])
        diff = x[i] - bucket
        new_x[i] = shuffles[int(bucket)] + diff
    return new_x

def compute_neighbors(location, min_loc, max_loc):
    adjusted = np.clip(location, min_loc, max_loc)
    if adjusted == min_loc:
        return [(min_loc, 1)]
    if adjusted == max_loc:
        return [(max_loc, 1)]

    lower = np.floor(adjusted)
    if adjusted == lower:
        return [(lower, 1)]

    upper = np.ceil(adjusted)
    return [(lower, adjusted - lower), (upper, upper - adjusted)]

def compute_output_bandwidth(x, partitions, nodes, inverse_map, outgoing_edges):
    # calculates the vector and scalar output counts of each partition
    v_out = np.zeros(partitions)
    s_out = np.zeros(partitions)
    partition_contributions = defaultdict(lambda: defaultdict(int))
    for node in nodes:
        entry = inverse_map[node.node]
        src_loc = x[entry]
        # likely actual partitions
        for candidate_src, src_probability in compute_neighbors(src_loc, 0, partitions - 1):
            # P[edge | src = candidate_src]
            out_contrib = {
                "v": 0,
                "s": 0
            }
            src_as_int = int(candidate_src)
            for edge in outgoing_edges[node.node]:
                dest = edge.dst
                if dest[0] == "e":
                    out_contrib[edge.tp] = 1
                    continue
                dest_loc = x[inverse_map[dest]]
                for candidate_dst, dst_probability in compute_neighbors(dest_loc, 0, partitions - 1):
                    if int(candidate_dst) == src_as_int:
                        continue
                    out_contrib[edge.tp] += dst_probability
            v_out[src_as_int] += min(out_contrib["v"], 1) * src_probability
            s_out[src_as_int] += min(out_contrib["s"], 1) * src_probability
    return np.concatenate((v_out, s_out))

def partition(nodes, edges, constraints: Constraint, partitions: int, max_iters:int = 10, sharps_per_iter:int = 10):
    """
    :param nodes: List of Node objects
    :param edges: List of Edge objects
    :param constraints: Constraint
    :param partitions: number of candidate partitions
    :return: Mapping[int, List[Nodes]] a mapping from partition ID to nodes in partition
    """

    remap = {i: s for i, s in enumerate(nodes)}
    inverse_map = {s.node: i for i, s in enumerate(nodes)}
    print(inverse_map)
    num_nodes = len(nodes)

    partitioning = np.random.uniform(0, partitions, num_nodes)

    bounds = scipy.optimize.Bounds(0, partitions - 1)

    weight_mask = np.empty(num_nodes)
    for i, node in enumerate(nodes):
        weight_mask[i] = node.op

    num_nodes_constraint_func = construct_num_nodes_constraint_func(partitions, weight_mask)

    num_nodes_constraint = scipy.optimize.NonlinearConstraint(
        fun=num_nodes_constraint_func,
        lb=-np.inf,
        ub=constraints.ops,
    )

    outgoing_edges = defaultdict(list)
    incoming_edges = defaultdict(list)
    for edge in edges:
        outgoing_edges[edge.src].append(edge)
        incoming_edges[edge.dst].append(edge)

    output_bandwidth_constraint_func = functools.partial(compute_output_bandwidth, partitions=partitions,
                                                  outgoing_edges=outgoing_edges, nodes=nodes, inverse_map=inverse_map)

    output_bandwidth_constraint = scipy.optimize.NonlinearConstraint(
        fun = output_bandwidth_constraint_func,
        lb=-np.inf,
        ub=[constraints.vout] * partitions + [constraints.sout] * partitions,
    )

    for iterations in range(max_iters):
        for it, sharpness in enumerate(np.geomspace(0.0625, (iterations + 1.0) / max_iters, sharps_per_iter)):
            enforce_integer = it > sharps_per_iter * 3 / 4
            def target(x):
                # minimize the largest used partition id
                # plus a non-integer penalty.
                num_partitions = np.max(x) - np.min(x) + 1
                if enforce_integer:
                    return num_partitions + non_integer_penalty(x) * sharpness
                return num_partitions

            result = scipy.optimize.minimize(fun=target, x0=partitioning, method="COBYLA", constraints=[
                num_nodes_constraint,
                output_bandwidth_constraint
            ], bounds=bounds, options={"disp": True})
            print("Sharpness:", sharpness, "Enforce Integer", enforce_integer, "Assignment:", result)
            print("Max Nodes:", num_nodes_constraint_func(np.round(result.x, 2)))
            partitioning = result.x
        partitioning = downshift(partitioning)


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
    constraint = load_csv(opts.spec, Constraint)[0]
    constraint = Constraint(*list(map(int, constraint)))

    partition(nodes, edges, constraint, 100, 60)


if __name__ == "__main__":
    main()
