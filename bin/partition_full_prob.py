#!usr/bin/env python

import sys
import os
import csv
import argparse
import numpy as np
import scipy as sp
import scipy.optimize
import scipy.stats
import scipy.sparse
import scipy.special
from collections import namedtuple
import random
import time

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

def partition(nodes, edges, constraints: Constraint, partitions: int):
    """
    :param nodes: List of Node objects
    :param edges: List of Edge objects
    :param constraints: Constraint
    :param partitions: number of candidate partitions
    :return: Mapping[int, List[Nodes]] a mapping from partition ID to nodes in partition
    """
    node_ids = {node.node for node in nodes}
    new_nodes = ({edge.src for edge in edges} | {edge.dst for edge in edges}) - node_ids

    node_to_loc_map = {node_id : i for i, node_id in enumerate(node_ids)}

    num_nodes = len(node_ids)

    valid_bounds = scipy.optimize.Bounds(lb=0, ub=1)

    node_to_partition_map = np.zeros((num_nodes, partitions), dtype=np.float)
    np.fill_diagonal(node_to_partition_map, 1)
    print(node_to_partition_map)

    # really want to handle this as a 1-D vector.
    node_to_partition_map = node_to_partition_map.ravel()

    # constraint on maximum number of ops per partition
    ops_per_partition_matrix = scipy.sparse.hstack([scipy.sparse.identity(partitions)] * num_nodes)
    ops_per_partition_constraint = scipy.optimize.LinearConstraint(
        A = ops_per_partition_matrix,
        lb=0,
        ub=constraints.ops
    )

    stochasticity_constraint_matrix = scipy.sparse.kron(scipy.sparse.identity(num_nodes), np.ones(partitions))
    stochasticity_constraint = scipy.optimize.LinearConstraint(
        A = stochasticity_constraint_matrix,
        lb = 1,
        ub = 1.1,
    )

    # ordering constraint
    # if a -> b then constrain avg_partition_id(a) <= avg_partition_id(b)
    ordering_constraints = []
    for edge in edges:
        if edge.src not in node_ids or edge.dst not in node_ids:
            continue
        helper_matrix = np.zeros(num_nodes)
        helper_matrix[node_to_loc_map[edge.src]] = -1
        helper_matrix[node_to_loc_map[edge.dst]] = 1
        ordering_constraints.append(scipy.optimize.LinearConstraint(
            A = scipy.sparse.kron(helper_matrix, np.arange(partitions)),
            lb=0, ub=np.inf
        ))

    def cost_func(partitioning, sharpness_multiplier):
        print("Evaluating Cost Function")
        t = time.time()
        reshaped = partitioning.reshape(num_nodes, partitions)
        partition_nums = np.argmax(reshaped, axis=1)

        grad = np.zeros((num_nodes, partitions))
        grad[np.arange(num_nodes), partition_nums] = partition_nums

        smoothness = np.median(reshaped, axis=1) / np.max(reshaped, axis=1)
        sharpness_penalty = np.max(smoothness) * sharpness_multiplier

        # gradient based on median and max values.
        least_sharp_index = np.argmax(smoothness)
        least_sharp_row = reshaped[least_sharp_index, :]
        argsorted = np.argsort(least_sharp_row)
        medians = (argsorted[partitions//2], argsorted[(partitions+1)//2])
        max_index = np.argmax(least_sharp_row)
        max_value = least_sharp_row[max_index]
        for median in medians:
            grad[least_sharp_index, median] += 0.5 * sharpness_penalty / max_value

        grad[least_sharp_index, max_index] = sharpness_penalty * -np.median(least_sharp_row) / max_value**2

        function_value = np.sum(partition_nums) + sharpness_penalty
        print("Done Evaluating Cost Function:", time.time() - t)
        return function_value, grad.ravel()

    print("Beginning Partitioning")
    for i in range(1, len(ordering_constraints)+1):
        print("Running with", i, "random ordering constraints")
        for sharpness in np.geomspace(0.1, 1):
            print("Sharpness:", sharpness)
            update = scipy.optimize.minimize(
                fun=cost_func,
                method="SLSQP",  # SLSQP or COBYLA
                jac=True,
                args=(sharpness,),
                x0=node_to_partition_map,
                bounds=valid_bounds,
                constraints=[
                    ops_per_partition_constraint,
                    stochasticity_constraint,
                ] + random.sample(ordering_constraints, i)
            )
            print(update)
            np.savetxt("tmp.csv", update.x)
            node_to_partition_map = update.x


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

    partition(nodes, edges, constraint, len(nodes))


if __name__ == "__main__":
    main()
