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
import itertools

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
        return [(int(min_loc), 1)]
    if adjusted == max_loc:
        return [(int(max_loc), 1)]

    lower = np.floor(adjusted)
    if adjusted == lower:
        return [(int(lower), 1)]

    upper = np.ceil(adjusted)
    return [(int(lower), adjusted - lower), (int(upper), upper - adjusted)]


def cleaned_name(node_id):
    if node_id[0] == "e":
        return node_id[1:]
    return node_id


def compute_bandwidth(x, partitions, inverse_map, edges):
    print("computing bandwidth")
    # calculates the vector and scalar output counts of each partition
    num_real_nodes = len(x)
    # node -> src_partition -> dst_partition
    # extra item for external nodes.
    communication_map = {etype: np.zeros((len(inverse_map), partitions + 1, partitions + 1), dtype=np.float) for etype
                         in "vs"}
    print("Populating Map")
    for src, dst, tp, _ in edges:
        src_loc = inverse_map[src]
        src_partition_candidates = compute_neighbors(x[src_loc], 0, partitions - 1) if src_loc < num_real_nodes else (
            partitions, 1)
        dst_loc = inverse_map[dst]
        dst_partition_candidates = compute_neighbors(x[dst_loc], 0, partitions - 1) if dst_loc < num_real_nodes else (
            partitions, 1)
        for (src_partition, src_probability), (dst_partition, dst_probability) in itertools.product(
                src_partition_candidates, dst_partition_candidates):
            if src_partition == dst_partition:
                continue
            communication_map[tp][src_loc, src_partition, dst_partition] += src_probability * dst_probability

    # in-place clip each element to [0, 1]
    for comm in communication_map.values():
        np.clip(comm, 0, 1, out=comm)

    outputs = {
        "v": {},
        "s": {}
    }
    print("Done populating map")

    for edge_tp, comm_map in communication_map.items():
        outputs[edge_tp]["out"] = np.sum(comm_map, axis=(0, 2))[:-1]
        outputs[edge_tp]["in"] = np.sum(comm_map, axis=(0, 1))[:-1]

    return np.concatenate((outputs["v"]["out"], outputs["v"]["in"], outputs["s"]["out"], outputs["s"]["in"]))


def partition(nodes, edges, constraints: Constraint, partitions: int, max_iters: int = 10, sharps_per_iter: int = 10):
    """
    :param nodes: List of Node objects
    :param edges: List of Edge objects
    :param constraints: Constraint
    :param partitions: number of candidate partitions
    :return: Mapping[int, List[Nodes]] a mapping from partition ID to nodes in partition
    """

    remap = {i: s for i, s in enumerate(nodes)}
    inverse_map = {s.node: i for i, s in enumerate(nodes)}
    num_nodes = len(nodes)
    # some edges might also have external nodes.
    for src, dst, _, _ in edges:
        if src not in inverse_map:
            inverse_map[src] = num_nodes
            remap[num_nodes] = src
            num_nodes += 1
        if dst not in inverse_map:
            inverse_map[dst] = num_nodes
            remap[num_nodes] = dst
            num_nodes += 1

    partitioning = np.random.uniform(0, partitions, num_nodes)
    bounds = scipy.optimize.Bounds(0, partitions - 1)

    weight_mask = np.zeros((num_nodes,))
    for i, node in enumerate(nodes):
        weight_mask[i] = node.op

    num_nodes_constraint_func = construct_num_nodes_constraint_func(partitions, weight_mask)

    num_nodes_constraint = scipy.optimize.NonlinearConstraint(
        fun=num_nodes_constraint_func,
        lb=-np.inf,
        ub=constraints.ops,
    )

    bandwidth_constraint_func = functools.partial(compute_bandwidth, partitions=partitions, edges=edges,
                                                  inverse_map=inverse_map)

    bandwidth_constraint = scipy.optimize.NonlinearConstraint(
        fun=bandwidth_constraint_func,
        lb=-np.inf,
        ub=[constraints.vout] * partitions + [constraints.vin] * partitions + [
            constraints.sout] * partitions + [
               constraints.sin] * partitions,
    )

    directionality_constraint_matrix = np.zeros((len(edges), num_nodes))
    # enforce that if edge a -> b then partition(a) <= partition(b)

    for i, (src, dst, _, _) in enumerate(edges):
        directionality_constraint_matrix[i, inverse_map[dst]] = 1
        directionality_constraint_matrix[i, inverse_map[src]] = -1

    directionality_constraint = scipy.optimize.LinearConstraint(A=directionality_constraint_matrix, lb=0, ub=np.inf)

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
                directionality_constraint,
                bandwidth_constraint
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
    edges = [element._replace(
        src=cleaned_name(element.src),
        dst=cleaned_name(element.dst)
    ) for element in edges]
    constraint = load_csv(opts.spec, Constraint)[0]
    constraint = Constraint(*list(map(int, constraint)))

    partition(nodes, edges, constraint, 100, 60)


if __name__ == "__main__":
    main()
