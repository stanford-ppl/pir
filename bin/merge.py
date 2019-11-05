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
import abc
import enum
import atexit

# Constraint = namedtuple("Constraint", ["ops", "vin", "sin", "vout", "sout"])
# Node = namedtuple("Node",
#         ["node","initTp","comment","AFGCost","MCCost","MergeBufferCost_quantity","MergeBufferCost_ways",
#         "SplitterCost_quantity","LockCost_quantity","SRAMCost_bank","SRAMCost_size","InputCost_sin",
#         "InputCost_vin","OutputCost_sout","OutputCost_vout","StageCost_quantity","LaneCost","OpCost"])
# Edge = namedtuple("Edge", ["src", "dst", "tp", "comment"])
# NodePartition = namedtuple("NodePartition", ["node", "partition"])

# Cost Types: Prefix, Quant, Max, Set
COST_REGISTRY = {}


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
        indent = self._indent
        name = self.name
        elapsed = self.elapsed
        atexit.register(lambda: print(indent * "\t", name, elapsed))
        self._initialized = False
        assert self.contexts.pop() is self


def time_this(func):
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        with TimeContext(func.__name__):
            return func(*args, **kwargs)

    return wrapper


class Filterable:
    @classmethod
    def get_from_iterable(cls, iterable):
        return (i for i in iterable if isinstance(i, cls))


class Cost(typing.NamedTuple, Filterable):
    attribute_name: str
    value: typing.Any

    @abc.abstractmethod
    def accepts(self, value: typing.Any):
        pass

    @staticmethod
    @abc.abstractmethod
    def parse(s: str):
        pass

    def __eq__(self, other):
        if not isinstance(other, Cost):
            return False
        return self.attribute_name == other.attribute_name and self.value == other.value

    def __hash__(self):
        return hash((hash(self.attribute_name + "__COST"), hash(self.value)))

    @classmethod
    def __init_subclass__(cls, **kwargs):
        super().__init_subclass__(**kwargs)
        COST_REGISTRY[cls.__name__] = cls


class HardCostAbstractMixin(Filterable):
    pass


class ReducibleCostMixin(Filterable):
    @classmethod
    @abc.abstractmethod
    def reduce(cls, args: Cost):
        pass


class QuantityCost(Cost, ReducibleCostMixin):
    """Straightforward sum of components"""

    @classmethod
    def reduce(cls, args: typing.List):
        return sum(args)

    def accepts(self, value):
        return value <= self.value

    @staticmethod
    def parse(s: str):
        return float(s)


class MaxCost(Cost, ReducibleCostMixin):
    """Straightforward max of components"""

    @classmethod
    def reduce(cls, args: typing.List):
        return cvxpy.maximum(*args)

    def accepts(self, value: typing.Any):
        return value <= self.value

    @staticmethod
    def parse(s: str):
        return float(s)


class SetCost(Cost, HardCostAbstractMixin):
    """Capability based: Nodes require certain capabilities which only some types of partitions can support."""

    def accepts(self, other) -> bool:
        return other <= self.value

    @staticmethod
    def parse(s: str):
        return frozenset(filter(bool, s.split("|")))


class PrefixCost(Cost, HardCostAbstractMixin):
    def accepts(self, other) -> bool:
        if other:
            return self.value
        return True

    @staticmethod
    def parse(s: str):
        assert s in {"true", "false"}
        return s == "true"


class CustomCost(Cost):
    def accepts(self, value: typing.Any):
        raise NotImplementedError("CustomCosts should not have a default accept method.")

    @staticmethod
    def parse(s: str):
        return float(s)


class PartitionType(typing.NamedTuple):
    typename: str
    limits: typing.FrozenSet[Cost]

    @property
    @functools.lru_cache()
    def limit_map(self) -> typing.Dict[str, Cost]:
        return {limit.attribute_name: limit for limit in self.limits}

    def get_limit(self, limit: Cost):
        return self.limit_map[limit.attribute_name]


class Node(typing.NamedTuple):
    node_id: typing.Hashable
    costs: typing.FrozenSet[Cost]

    @property
    @functools.lru_cache()
    def cost_map(self) -> typing.Dict[str, Cost]:
        return {cost.attribute_name: cost for cost in self.costs}

    def get_cost(self, cost: Cost):
        return self.cost_map[cost.attribute_name]


class EdgeType(enum.Enum):
    VECTOR = 0
    SCALAR = 1


class Edge(typing.NamedTuple):
    src: int  # Node id
    dst: int  # Node id
    out_id: int  # unique ID for value along edge
    is_backedge: bool
    tp: EdgeType


def load_csv(fname, tp, use_fieldnames=False):
    with open(fname, "r") as f:
        return [tp(**row) for row in
                csv.DictReader(f, delimiter=",", fieldnames=tp._fields if use_fieldnames else None)]

TMP = []
class CVXMerger:
    def __init__(self, nodes: typing.List[Node], edges: typing.List[Edge],
                 partition_counts: typing.Dict[PartitionType, int]):
        self.nodes = nodes[:]
        self.edges = edges[:]
        self.partition_counts = partition_counts.copy()
        self.cvx_constraints = []
        self._init_node_lookup()
        self._init_partitions()
        self._init_edge_deps()
        self._init_partition_constraints()
        self._init_node_constraints()

    @property
    def num_nodes(self):
        return len(self.nodes)

    @property
    def num_partition_types(self):
        return len(self.partition_counts)

    def get_assignment(self):
        for i, (partition_type, matrix) in enumerate(self.partition_matrices.items()):
            for loc, partition_num in np.transpose(matrix.value.nonzero()):
                node = self.get_node(loc)
                yield node, partition_num * self.num_partition_types + i, partition_type

    @functools.lru_cache()
    def _init_node_lookup(self):
        self._forward_node_lookup: typing.Dict[Node, int] = {}
        for i, node in enumerate(self.nodes):
            self._forward_node_lookup[node] = i

    @property
    @functools.lru_cache()
    def id_to_node_lookup(self):
        return {node.node_id: node for node in self.nodes}

    @time_this
    @functools.lru_cache()
    def _init_partitions(self):
        # interleave all of these partitions, with all of them as the same size.
        max_size = min(max(self.partition_counts.values()), self.num_nodes)
        partition_count = self.num_partition_types * max_size
        self.partition_matrices = {
            partitiontype: cvxpy.Variable(name=partitiontype.typename, shape=(self.num_nodes, partition_count),
                                          boolean=True)
            for partitiontype in self.partition_counts
        }

        self.active_partitions = {}

        for partitiontype, count in self.partition_counts.items():
            # assert that the count for the type is indeed less than our constraint
            partition_matrix = self.partition_matrices[partitiontype]
            active_partitions = cvxpy.sum(self._project_to_bool(cvxpy.sum(partition_matrix, axis=0), max_value=count))
            self._add_constraint(active_partitions <= count)
            self.active_partitions[partitiontype] = active_partitions

        stacked = cvxpy.hstack(self.partition_matrices.values())
        self._add_constraint(cvxpy.sum(stacked, axis=1) == 1)

        # linearize partition ids via interleaving.
        base_vec = np.arange(partition_count)
        slices = []
        for i, partitiontype in enumerate(self.partition_counts):
            slices.append(self.partition_matrices[partitiontype] @ (base_vec + i))
        self.partition_ids = sum(slices)

    @time_this
    @functools.lru_cache()
    def _init_edge_deps(self):
        for edge in self.forward_edges:
            src_node = self.id_to_node_lookup[edge.src]
            dst_node = self.id_to_node_lookup[edge.dst]
            src_loc = self.get_loc(src_node)
            dst_loc = self.get_loc(dst_node)
            self._add_constraint(self.partition_ids[src_loc] <= self.partition_ids[dst_loc])

    @time_this
    @functools.lru_cache()
    def _init_partition_constraints(self):
        for pt, matrix in self.partition_matrices.items():
            # Just for type hinting
            partitiontype: PartitionType = pt
            for constraint in partitiontype.limits:
                if isinstance(constraint, HardCostAbstractMixin):
                    # These are probably better handled from the node perspective
                    continue
                if isinstance(constraint, ReducibleCostMixin):
                    # for Quantity, MaxCost, we can handle these using boolean projections.
                    cost_vector = np.zeros(self.num_nodes)
                    for node in self.nodes:
                        value = node.get_cost(constraint).value
                        cost_vector[self.get_loc(node)] = value
                    # for element of cost vector:
                    #   take cv[i] * matrix[i, :]
                    #   then reduce with constraint function
                    components = [cost_vector[i] * matrix[i, :] for i in range(self.num_nodes)]
                    resultant = constraint.reduce(components)
                    self._add_constraint(constraint.accepts(resultant))
                    continue
                if isinstance(constraint, CustomCost):
                    method = getattr(self, "_init_custom_" + constraint.attribute_name)
                    method(constraint, matrix)
                    continue
                raise NotImplementedError("Not implemented yet for constraint:", constraint)

    @time_this
    def _init_input_costs(self, cost: Cost, matrix: cvxpy.Variable, edge_type: EdgeType):
        edge_map = defaultdict(list)
        edge_srcs = {}
        for edge in (edge for edge in self.edges if edge.tp == edge_type):
            edge_map[edge.out_id].append(edge.dst)
            edge_srcs[edge.out_id] = edge.src
        movement = []
        for out_id in edge_srcs:
            src = self.get_loc(self.id_to_node_lookup[edge_srcs[out_id]])
            destinations = [self.get_loc(self.id_to_node_lookup[dst]) for dst in edge_map[out_id]]
            push_set = self._project_to_bool(sum(matrix[dest, :] for dest in destinations), cost.value)
            movement.append(cvxpy.maximum(push_set - matrix[src, :], 0))
        self._add_constraint(sum(movement, 0) <= cost.value)

    _init_custom_InputCost_vin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.VECTOR)
    _init_custom_InputCost_sin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.SCALAR)

    def _init_output_costs(self, cost: Cost, matrix: cvxpy.Variable, edge_type: EdgeType):
        # for each node, for count number of distinct out_ids that aren't in the same partition.
        edge_map = defaultdict(list)
        edge_srcs = {}
        for edge in (edge for edge in self.edges if edge.tp == edge_type):
            edge_map[edge.out_id].append(edge.dst)
            edge_srcs[edge.out_id] = edge.src
        movement = []
        for out_id in edge_srcs:
            src = self.get_loc(self.id_to_node_lookup[edge_srcs[out_id]])
            destinations = [self.get_loc(self.id_to_node_lookup[dst]) for dst in edge_map[out_id]]
            push_set = self._project_to_bool(sum(matrix[dest, :] for dest in destinations), cost.value)
            outputs = cvxpy.sum(self._project_to_bool(cvxpy.maximum(push_set - matrix[src, :], 0), cost.value))
            # these are the outputs allocated to src
            movement.append(cvxpy.maximum(matrix[src, :] + outputs - 1, 0))
        self._add_constraint(sum(movement, 0) <= cost.value)

    _init_custom_OutputCost_vout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.VECTOR)
    _init_custom_OutputCost_sout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.SCALAR)

    @time_this
    @functools.lru_cache()
    def _init_node_constraints(self):
        for node in self.nodes:
            # for each node:
            # viable partitions are ones that satisfy the Hard Costs.
            hard_costs = HardCostAbstractMixin.get_from_iterable(node.costs)
            for hard_cost in hard_costs:
                for partitiontype, matrix in self.partition_matrices.items():
                    partition_constraint = partitiontype.get_limit(hard_cost)
                    if partition_constraint.accepts(hard_cost.value):
                        continue
                    # constrain that node cannot be in partition
                    self._add_constraint(matrix[self.get_loc(node)] == 0)

    @property
    @functools.lru_cache()
    def utilization(self):
        total = 0
        for partition_type, currently_active in self.active_partitions.items():
            max_count = self.partition_counts[partition_type]
            total += currently_active / max_count
        return total

    @property
    @functools.lru_cache()
    def problem(self):
        return cvxpy.Problem(cvxpy.Minimize(self.utilization), self.cvx_constraints)

    def solve(self, *args, **kwargs):
        self.problem.solve(*args, **kwargs)
        assert self.problem.status not in {"unbounded", "infeasible"}

    @property
    @functools.lru_cache()
    def forward_edges(self):
        return [edge for edge in self.edges if not edge.is_backedge]

    @property
    @functools.lru_cache()
    def back_edges(self):
        return [edge for edge in self.edges if edge.is_backedge]

    def get_loc(self, node: Node):
        return self._forward_node_lookup[node]

    def get_node(self, loc: int):
        return self.nodes[loc]

    def _add_constraint(self, constraint):
        assert constraint.is_dcp()
        self.cvx_constraints.append(constraint)

    def _project_to_bool(self, var, max_value):
        projected = cvxpy.Variable(boolean=True, shape=var.shape)
        self._add_constraint(var <= projected * max_value)
        return projected


def merge_dummy(nodes, opts):
    with open(opts.merge, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node in nodes:
            writer.writerow([node.node, node.node, node.initTp])
    print("Generate {}".format(opts.merge))


def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
    parser.add_argument('-t', '--thread', type=int, default=1, help='Number of threads for solver')
    (opts, args) = parser.parse_known_args()

    opts.node = os.path.join(opts.path, "node.csv")
    opts.edge = os.path.join(opts.path, "edge.csv")
    opts.spec_count = os.path.join(opts.path, "spec_count.csv")
    opts.spec_cost = os.path.join(opts.path, "spec_cost.csv")
    opts.costtp = os.path.join(opts.path, "costtp.csv")
    opts.merge = os.path.join(opts.path, "merge.csv")

    with TimeContext("Cost TP parsing"):
        cost_types = {}
        with open(opts.costtp) as costtp_file:
            reader = csv.DictReader(costtp_file, delimiter=",")
            for cost_name, cost_tp_string in next(reader).items():
                cost_types[cost_name] = COST_REGISTRY[cost_tp_string]

    with TimeContext("Spec Cost parsing"):
        partition_types: typing.Dict[str, PartitionType] = {}
        with open(opts.spec_cost) as spec_cost_file:
            reader = csv.DictReader(spec_cost_file, delimiter=",")
            for line in reader:
                tp = line["tp"]
                del line["tp"]
                costs = {cost_types[name](attribute_name=name, value=cost_types[name].parse(value)) for name, value in
                         line.items()}
                partition_type = PartitionType(typename=tp, limits=frozenset(costs))
                partition_types[tp] = partition_type

    with TimeContext("Spec Count parsing"):
        partition_counts: typing.Dict[PartitionType, int] = {}
        with open(opts.spec_count) as spec_count_file:
            reader = csv.DictReader(spec_count_file, delimiter=",")
            for name, count_str in next(reader).items():
                partition_counts[partition_types[name]] = int(count_str)

    with TimeContext("Node parsing"):
        nodes: typing.List[Node] = []
        with open(opts.node) as node_file:
            reader = csv.DictReader(node_file, delimiter=",")
            for line in reader:
                costs = set()
                for cost_name, cost_tp in cost_types.items():
                    costs.add(cost_tp(attribute_name=cost_name, value=cost_tp.parse(line[cost_name])))
                nodes.append(Node(node_id=int(line["node"]), costs=frozenset(costs)))

    with TimeContext("Edge parsing"):
        edges: typing.List[Edge] = []
        tp_map = {
            "s": EdgeType.SCALAR,
            "v": EdgeType.VECTOR
        }
        with open(opts.edge) as edge_file:
            reader = csv.DictReader(edge_file, delimiter=",")
            for line in reader:
                assert line["backEdge"] in {"true", "false"}
                edges.append(Edge(
                    src=int(line["src"]),
                    dst=int(line["dst"]),
                    is_backedge=line["backEdge"] == "true",
                    tp=tp_map[line["tp"]],
                    out_id=int(line["outid"])
                ))

    solver = CVXMerger(nodes=nodes, edges=edges, partition_counts=partition_counts)
    solver.solve(solver="GUROBI", verbose=True, Threads=opts.thread, MIPGap=0.15)

    with open(opts.merge, "w") as merge_file:
        writer = csv.writer(merge_file, delimiter=",")
        for node, part_id, partition_type in solver.get_assignment():
            writer.writerow([node.node_id, part_id, partition_type.typename])

    print(solver.utilization.value)
    for tp, v in solver.active_partitions.items():
        print(tp.typename, v.value)

    for tp, mat in solver.partition_matrices.items():
        print(tp.typename)
        print(np.transpose(mat.value.nonzero()))
        print()

    for i in TMP:
        print(i.value)


if __name__ == "__main__":
    main()
