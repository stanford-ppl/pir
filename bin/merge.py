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
import cachetools

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
        start = time.time()
        print("Starting:", func.__name__)
        with TimeContext(func.__name__):
            result = func(*args, **kwargs)
        print("Finishing:", func.__name__, time.time() - start)
        return result

    return wrapper


class Filterable:
    @classmethod
    def get_from_iterable(cls, iterable) -> typing.Iterable['Cost']:
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

    @classmethod
    @abc.abstractmethod
    def numeric_reduce(cls, args):
        pass


class QuantityCost(Cost, ReducibleCostMixin):
    """Straightforward sum of components"""

    @classmethod
    def reduce(cls, args: typing.List):
        return sum(args)

    @classmethod
    def numeric_reduce(cls, args):
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

    @classmethod
    def numeric_reduce(cls, args):
        return max(args)

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

    @property
    def num_nodes(self):
        return len(self.nodes)

    @property
    def num_partition_types(self):
        return len(self.partition_counts)

    def get_assignment(self):
        next_partition = itertools.count()
        partition_ids = defaultdict(lambda: next(next_partition))
        for i, (partition_type, matrix) in enumerate(self.partition_matrices.items()):
            inverse_mapping = {
                n: loc for loc, n in self.node_to_loc_map[partition_type].items()
            }
            for loc, partition_num in np.transpose(matrix.value.nonzero()):
                node = inverse_mapping[loc]
                yield node, partition_ids[(partition_num, partition_type)], partition_type

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
        # for each node, for each partition, establish a map.
        # this is so that some nodes have very few possible partitions to map to.
        self.node_to_loc_map = {}
        self.partition_matrices = {}
        node_to_row_map = defaultdict(list)
        for partition_type, count in self.partition_counts.items():
            conforming_nodes = [
                node for node in self.nodes if
                all(partition_type.get_limit(cost).accepts(cost.value) for cost in
                    HardCostAbstractMixin.get_from_iterable(node.costs))
            ]
            self.node_to_loc_map[partition_type] = loc_map = {node: i for i, node in enumerate(conforming_nodes)}
            num_conforming_nodes = len(conforming_nodes)
            self.partition_matrices[partition_type] = matrix = cvxpy.Variable(
                name=partition_type.typename, shape=(num_conforming_nodes, count),
                boolean=True)

            for node in conforming_nodes:
                node_to_row_map[node].append(matrix[loc_map[node], :])
        for vecs in node_to_row_map.values():
            # assert that every node has an allocation
            self._add_constraint(sum((cvxpy.sum(vec) for vec in vecs), 0) == 1)

    @property
    @functools.lru_cache(None)
    def adjacency_matrix(self):
        adjacency_matrix = scipy.sparse.lil_matrix((self.num_nodes, self.num_nodes), dtype=bool)
        for edge in self.edges:
            if edge.is_backedge:
                continue
            adjacency_matrix[
                self.get_loc(self.id_to_node_lookup[edge.src]), self.get_loc(self.id_to_node_lookup[edge.dst])] = True
        return adjacency_matrix

    @property
    @functools.lru_cache(None)
    def reachability_matrix(self):
        reachability_matrix = self.adjacency_matrix.copy()
        # add in diagonal so that matrix powers work.
        for i in range(self.num_nodes):
            reachability_matrix[i, i] = True
        return reachability_matrix

    @property
    @functools.lru_cache(None)
    def full_reachability_matrix(self):
        return self.reachability_matrix ** self.num_nodes

    @cachetools.cached(cache={}, key=lambda self, src, dst, *_: (self, src, dst))
    def _nodes_between(self, src_loc, dst_loc, current=frozenset()):
        if src_loc == dst_loc:
            return {src_loc}
        results = set()
        for _, middle in scipy.transpose(self.adjacency_matrix[src_loc].nonzero()):
            if middle in current:
                continue
            results |= self._nodes_between(middle, dst_loc, frozenset(results | {middle} | current))
        if not results:
            return set()
        results.add(src_loc)
        return results

    @functools.lru_cache(None)
    def _candidate_partitions(self, node: Node):
        constraints = list(HardCostAbstractMixin.get_from_iterable(node.costs))
        return frozenset(partition for partition in self.partition_counts if
                         all(partition.get_limit(constraint).accepts(constraint.value) for constraint in constraints))

    @functools.lru_cache(None)
    def _possible_in_same_partition(self, a, b):
        # if all the all nodes between src and dst <= 6 (inclusive), then it can fit, or if src and dst are disconnected
        # check if they are disconnected:
        loc1 = self.get_loc(a)
        loc2 = self.get_loc(b)

        # check if they can be in the same partition type
        common_partitions = self._candidate_partitions(a) & self._candidate_partitions(b)
        if not common_partitions:
            return False

        # no dependency
        if not (self.full_reachability_matrix[loc1, loc2] or self.full_reachability_matrix[loc2, loc1]):
            return True

        for src, dst in ((loc1, loc2), (loc2, loc1)):
            nodes_between = [self.nodes[index] for index in self._nodes_between(src, dst)]
            candidate_partitions = functools.reduce(frozenset.union,
                                                    [self._candidate_partitions(node) for node in nodes_between],
                                                    frozenset())
            if not candidate_partitions:
                # there aren't any partitions that can take all of the intermediate nodes, or there isn't a path.
                continue
            for candidate_partition in candidate_partitions:
                candidate_partition: PartitionType
                # must fit all nodes in between.
                for cost in ReducibleCostMixin.get_from_iterable(candidate_partition.limits):
                    node_costs = [node.get_cost(cost).value for node in self.nodes]
                    if cost.accepts(cost.numeric_reduce(node_costs)):
                        return True
        return False

    @functools.lru_cache()
    def _get_row(self, partition_type: PartitionType, node: Node):
        matrix = self.partition_matrices[partition_type]
        location = self.node_to_loc_map[partition_type][node]
        return matrix[location, :]

    @staticmethod
    def _get_row_or_zeroes(matrix, loc_map, node):
        if node in loc_map:
            return matrix[loc_map[node], :]
        return np.zeros(matrix.shape[1])

    @time_this
    @functools.lru_cache()
    def _init_edge_deps(self):
        self.delays = defaultdict(lambda: cvxpy.Variable(integer=True))
        self.delay_violations = []
        for edge in self.edges:
            src_node = self.id_to_node_lookup[edge.src]
            dst_node = self.id_to_node_lookup[edge.dst]
            # if they're not in the same partition, then guarantee inequality.
            enforce_inequality: int
            if not self._possible_in_same_partition(src_node, dst_node):
                enforce_inequality = 1
            else:
                dst_candidates = self._candidate_partitions(dst_node)
                inequalities = []
                for partition_type in self._candidate_partitions(src_node):
                    src_row = self._get_row(partition_type, src_node)
                    if partition_type not in dst_candidates:
                        inequalities.append(cvxpy.sum(src_row))
                        continue
                    dst_row = self._get_row(partition_type, dst_node)
                    inequalities.append(cvxpy.sum(cvxpy.maximum(src_row - dst_row, 0)))
                enforce_inequality = cvxpy.maximum(*inequalities, 0) if inequalities else 0
            self.delay_violations.append(self._project_to_bool(
                cvxpy.maximum(self.delays[src_node] + enforce_inequality - self.delays[dst_node], 0),
                self.num_nodes * 2))

        # constrain that nodes within the same partition have the same delay.
        # constrain that the delay for nodes in the same partition are equal.
        # We only care for nodes which might be in the same partition.

        for n1, n2 in itertools.combinations(self.nodes, 2):
            if not self._possible_in_same_partition(n1, n2):
                continue
            # if the nodes are in the same partition, then impose an equality constraint on the delays
            peq = 0
            for partition_type in self._candidate_partitions(n1) & self._candidate_partitions(n2):
                peq += cvxpy.sum(
                    cvxpy.maximum(self._get_row(partition_type, n1) + self._get_row(partition_type, n2) - 1, 0))
            dne = self._project_to_bool(cvxpy.abs(self.delays[n1] - self.delays[n2]), self.num_nodes)
            self._add_constraint(peq + dne <= 1)

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
                    components = []
                    for node, index in self.node_to_loc_map[partitiontype].items():
                        value = node.get_cost(constraint).value
                        if value <= 1e-5:
                            continue
                        components.append(value * matrix[index, :])
                    resultant = constraint.reduce(components)
                    self._add_constraint(constraint.accepts(resultant))
                    continue
                if isinstance(constraint, CustomCost):
                    continue
                    method = getattr(self, "_init_custom_" + constraint.attribute_name)
                    method(constraint, matrix, self.node_to_loc_map[pt])
                    continue
                raise NotImplementedError("Not implemented yet for constraint:", constraint)

    @time_this
    def _init_input_costs(self, cost: Cost, matrix: cvxpy.Variable, loc_map: typing.Dict[Node, int],
                          edge_type: EdgeType):
        edge_map = defaultdict(list)
        edge_srcs = {}
        for edge in (edge for edge in self.edges if edge.tp == edge_type):
            edge_map[edge.out_id].append(edge.dst)
            edge_srcs[edge.out_id] = edge.src
        movement = []
        for out_id in edge_srcs:
            src_node = self.id_to_node_lookup[edge_srcs[out_id]]
            src_row = self._get_row_or_zeroes(matrix, loc_map, src_node)
            dst_nodes = [self.id_to_node_lookup[dst] for dst in edge_map[out_id] if
                         self.id_to_node_lookup[dst] in loc_map]
            if not dst_nodes:
                continue
            push_set = self._project_to_bool(sum(self._get_row_or_zeroes(matrix, loc_map, dest) for dest in dst_nodes),
                                             cost.value)
            movement.append(cvxpy.maximum(push_set - src_row, 0))
        self._add_constraint(sum(movement, 0) <= cost.value)

    _init_custom_InputCost_vin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.VECTOR)
    _init_custom_InputCost_sin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.SCALAR)

    def _init_output_costs(self, cost: Cost, matrix: cvxpy.Variable, loc_map: typing.Dict[Node, int],
                           edge_type: EdgeType):
        # for each node, for count number of distinct out_ids that aren't in the same partition.
        edge_map = defaultdict(list)
        edge_srcs = {}
        for edge in (edge for edge in self.edges if edge.tp == edge_type):
            edge_map[edge.out_id].append(edge.dst)
            edge_srcs[edge.out_id] = edge.src
        movement = []
        for out_id in edge_srcs:
            src_node = self.id_to_node_lookup[edge_srcs[out_id]]
            if src_node not in loc_map:
                continue
            src_row = self._get_row_or_zeroes(matrix, loc_map, src_node)
            dst_nodes = [self.id_to_node_lookup[dst] for dst in edge_map[out_id]]
            if any(node not in loc_map for node in dst_nodes):
                # if a node can't be in this partition type, then we have to export.
                movement.append(src_row)
            else:
                num_outputs = len(dst_nodes)
                matrix_sum = sum(self._get_row_or_zeroes(matrix, loc_map, dst) for dst in dst_nodes)
                num_out_of_partition_type_nodes = num_outputs - cvxpy.sum(matrix_sum)
                out_of_partition_type_movement = self._project_to_bool(num_out_of_partition_type_nodes, num_outputs)
                in_partition_type_movement = cvxpy.sum(
                    cvxpy.maximum(self._project_to_bool(matrix_sum, self.num_nodes) - src_row, 0))
                has_movement = self._project_to_bool(out_of_partition_type_movement + in_partition_type_movement,
                                                     cost.value)
                movement.append(cvxpy.maximum(has_movement + src_row - 1, 0))
        if movement:
            self._add_constraint(sum(movement) <= cost.value)

    _init_custom_OutputCost_vout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.VECTOR)
    _init_custom_OutputCost_sout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.SCALAR)

    @property
    @functools.lru_cache()
    def utilization(self):
        total = 0
        for partition_type, matrix in self.partition_matrices.items():
            max_count = self.partition_counts[partition_type]
            currently_active = cvxpy.sum(matrix)
            total += currently_active / max_count
        return total

    @property
    def total_delay_violations(self):
        return cvxpy.sum(self.delay_violations)

    @property
    @functools.lru_cache()
    def problem(self):
        return cvxpy.Problem(cvxpy.Minimize(self.utilization + self.total_delay_violations), self.cvx_constraints)

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
        if isinstance(constraint, bool):
            # already true constraints don't need enforcement
            assert constraint
            return
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

    for partition_type, count in partition_counts.items():
        print("Partition Type:", partition_type.typename, count)

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


if __name__ == "__main__":
    main()
