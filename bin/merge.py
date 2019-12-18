#!/usr/bin/env python

import sys
import os
import csv
import argparse
import numpy as np
import scipy.sparse
import gurobipy as gpy
from gurobipy import GRB
from collections import namedtuple, defaultdict
from collections.abc import Iterable
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
        return gpy.quicksum(args)

    @classmethod
    def numeric_reduce(cls, args):
        return sum(args)

    def accepts(self, value):
        return value <= self.value

    @staticmethod
    def parse(s: str):
        return float(s)

    SENSE = GRB.LESS_EQUAL


class MaxCost(Cost, ReducibleCostMixin):
    """Straightforward max of components"""

    @classmethod
    def reduce(cls, args: typing.List):
        return gpy.max_(args)

    @classmethod
    def numeric_reduce(cls, args):
        return max(args)

    def accepts(self, value: typing.Any):
        return value <= self.value

    @staticmethod
    def parse(s: str):
        return float(s)

    SENSE = GRB.LESS_EQUAL


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
        # self.partition_counts = partition_counts.copy()
        self.partition_counts = {}
        self.conforming_nodes = {}
        for partition_type, count in partition_counts.items():
            conforming_nodes = [
                node for node in self.nodes if
                all(partition_type.get_limit(cost).accepts(cost.value) for cost in
                    node.costs if not isinstance(cost, CustomCost))
            ]
            num_conforming_nodes = len(conforming_nodes)
            self.partition_counts[partition_type] = min(count, num_conforming_nodes)
            self.conforming_nodes[partition_type] = conforming_nodes

        self.model = gpy.Model("Model")

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
            converted = np.zeros_like(matrix)
            for index in np.ndindex(*matrix.shape):
                converted[index] = matrix[index].X
            for loc, partition_num in np.transpose(converted.nonzero()):
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

    def _create_matrix(self, name, shape, **kwargs):
        matrix = np.ndarray(shape=shape, dtype=object)
        for index in np.ndindex(*shape):
            matrix[index] = self.model.addVar(name="{}_{}".format(name, "_".join(map(str, index))), **kwargs)
        return matrix

    @time_this
    @functools.lru_cache()
    def _init_partitions(self):
        # for each node, for each partition, establish a map.
        # this is so that some nodes have very few possible partitions to map to.
        self.node_to_loc_map = {}
        self.partition_matrices = {}
        node_to_row_map = defaultdict(list)
        for partition_type, count in self.partition_counts.items():
            # conforming_nodes = [
            #     node for node in self.nodes if
            #     all(partition_type.get_limit(cost).accepts(cost.value) for cost in
            #         HardCostAbstractMixin.get_from_iterable(node.costs))
            # ]
            conforming_nodes = self.conforming_nodes[partition_type]
            self.node_to_loc_map[partition_type] = loc_map = {node: i for i, node in enumerate(conforming_nodes)}
            print("Map:", partition_type.typename, {node.node_id: i for node, i in loc_map.items()})
            num_conforming_nodes = len(conforming_nodes)
            matrix = np.ndarray(shape=(num_conforming_nodes, min(count, num_conforming_nodes)), dtype=object)
            for i, j in itertools.product(range(num_conforming_nodes), range(count)):
                matrix[i, j] = self.model.addVar(vtype=GRB.BINARY,
                                                 name="matrix_{}_{}_{}".format(partition_type.typename, i, j))
            self.partition_matrices[partition_type] = matrix

            for node in conforming_nodes:
                node_to_row_map[node].append(matrix[loc_map[node], :])
        for node, vecs in node_to_row_map.items():
            # assert that every node has an allocation
            all_vars = np.concatenate(vecs)
            self.model.addConstr(gpy.quicksum(all_vars) == 1, name="alloc_{}".format(node.node_id))

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
        return frozenset(partition for partition in self.partition_counts if node in self.conforming_nodes[partition])

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
        self.delays = {node: self.model.addVar(name="delay_{}".format(node.node_id)) for node in self.nodes}
        for edge in self.forward_edges:
            src_node = self.id_to_node_lookup[edge.src]
            dst_node = self.id_to_node_lookup[edge.dst]
            # if they're not in the same partition, then guarantee inequality.
            dst_candidates = self._candidate_partitions(dst_node)
            enforce_inequality = self.model.addVar(name="enforce_inequality_{}".format(edge.out_id), vtype=GRB.BINARY)
            for partition_type in self._candidate_partitions(src_node):
                src_row = self._get_row(partition_type, src_node)
                if partition_type not in dst_candidates:
                    self.model.addConstr((enforce_inequality == 0) >> (gpy.quicksum(src_row) == 0),
                                         name="edge_dep_pt_constraint_{}_{}".format(edge.out_id, partition_type.typename))
                    continue
                dst_row = self._get_row(partition_type, dst_node)
                for i, diff in enumerate(src_row - dst_row):
                    self.model.addConstr((enforce_inequality == 0) >> (diff == 0),
                                         name="edge_dep_pt_constraint_{}_{}_{}".format(edge.out_id, partition_type.typename, i))
            self.model.addConstr(self.delays[src_node] + enforce_inequality <= self.delays[dst_node],
                                 name="edge_dep_{}".format(edge.out_id))

        # for each node, compute its partition delay. Then constrain that the partition delay and actual delay are
        # close.

        partition_delays = {
            partition_type: self._create_matrix("partition_delays_{}".format(partition_type.typename), (count,)) for
            partition_type, count in
            self.partition_counts.items()}

        max_delay = self.num_nodes
        for pt_type, delay_vec in partition_delays.items():
            for i, delay in enumerate(delay_vec.flatten()):
                self.model.addConstr(delay <= max_delay, name = "max_delay_{}_{}".format(pt_type.typename, i))

        for node in self.nodes:
            target_delay_min = []
            target_delay_max = []
            for partition_type, node_to_loc in self.node_to_loc_map.items():
                if node not in node_to_loc:
                    continue

                # constrain that if node in partition, then delay[node] == delay[partition]
                loc = node_to_loc[node]
                row = self.partition_matrices[partition_type][loc, :]
                # we really just want row @ partition_delays[partition_type] but this is a quadratic constraint
                # instead, formulate as two sided constraint.
                activation = row * -max_delay + max_delay
                target_delay_min.extend(partition_delays[partition_type] + activation)

                activation = row * max_delay - max_delay
                target_delay_max.extend(partition_delays[partition_type] + activation)
            target_min = self._convert_to_var(gpy.min_(*self._convert_to_var(target_delay_min)),
                                              name="target_delay_min_{}".format(node.node_id))
            target_max = self._convert_to_var(gpy.max_(*self._convert_to_var(target_delay_max)),
                                              name="target_delay_max_{}".format(node.node_id))
            # self.model.addConstr(self.delays[node] == target_min, name="delay_target_min_{}".format(node.node_id))
            # self.model.addConstr(self.delays[node] >= target_max)

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
                    for partition_num in range(self.partition_counts[partitiontype]):
                        components = []
                        for node, index in self.node_to_loc_map[partitiontype].items():
                            value = node.get_cost(constraint).value
                            if value <= 1e-5:
                                continue
                            val = self.model.addVar(
                                name="cost_{}_{}_{}_{}".format(partitiontype.typename, partition_num,
                                                               constraint.attribute_name,
                                                               node.node_id))
                            self.model.addConstr(
                                val == self._convert_to_var(matrix[index, partition_num] * value))
                            components.append(val)
                        if not components:
                            continue
                        resultant = constraint.reduce(components)
                        temp = self.model.addVar(
                            name="cost_{}_{}_{}".format(partitiontype.typename, partition_num,
                                                        constraint.attribute_name))
                        self.model.addConstr(temp == resultant)
                        self.model.addConstr(temp, constraint.SENSE, constraint.value)
                    continue
                if isinstance(constraint, CustomCost):
                    method = getattr(self, "_init_custom_" + constraint.attribute_name)
                    method(constraint, matrix, self.node_to_loc_map[pt], partitiontype)
                    continue
                raise NotImplementedError("Not implemented yet for constraint:", constraint)

    @time_this
    def _init_input_costs(self, cost: Cost, matrix, loc_map: typing.Dict[Node, int], partitiontype,
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
            # push_set = self._project_to_bool(sum(self._get_row_or_zeroes(matrix, loc_map, dest) for dest in dst_nodes),
            #                                  cost.value)
            push_set = sum(self._get_row_or_zeroes(matrix, loc_map, dest) for dest in dst_nodes)
            projected = self._project_to_bool(push_set)
            # projected = np.array([self.model.addVar() for _ in push_set])
            # for proj, push in zip(projected, push_set):
            #     self.model.addConstr((proj == 0) >> (push == 0))
            diff = self._convert_to_var(push_set - src_row, name="push-src")
            movement.append(self._convert_to_var([(gpy.max_(d, 0)) for d in diff]))
        if not movement:
            return
        total_movement = self._convert_to_var(sum(movement, 0))
        for i, move in enumerate(total_movement):
            self.model.addConstr(move <= cost.value,  name="cost_{}_{}_{}_input_cost".format(partitiontype.typename, i, edge_type))

    _init_custom_InputCost_vin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.VECTOR)
    _init_custom_InputCost_sin = functools.partialmethod(_init_input_costs, edge_type=EdgeType.SCALAR)

    def _init_output_costs(self, cost: Cost, matrix, loc_map: typing.Dict[Node, int], partitiontype,
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
                num_out_of_partition_type_nodes = num_outputs - gpy.quicksum(matrix_sum)
                out_of_partition_type_movement = self._project_to_bool(num_out_of_partition_type_nodes)
                in_partition_type_movement = gpy.quicksum(list(self._convert_to_var(
                    [gpy.max_(x, 0) for x in self._convert_to_var(self._project_to_bool(matrix_sum) - src_row)])))
                has_movement = self._project_to_bool(out_of_partition_type_movement + in_partition_type_movement)
                movement.append(np.array([self.and_(has_movement, s) for s in src_row]))
                # out_of_partition_type_movement = self._project_to_bool(num_out_of_partition_type_nodes, num_outputs)
                # in_partition_type_movement = cvxpy.sum(
                #     cvxpy.maximum(self._project_to_bool(matrix_sum, self.num_nodes) - src_row, 0))
                # has_movement = self._project_to_bool(out_of_partition_type_movement + in_partition_type_movement,
                #                                      cost.value)
                # movement.append(cvxpy.maximum(has_movement + src_row - 1, 0))
        if movement:
            self.model.update()
            total_movement = sum(movement)
            for i, move in enumerate(total_movement):
                self.model.addConstr(move <= cost.value,  name="cost_{}_{}_{}_output_cost".format(partitiontype.typename, i, edge_type))

    _init_custom_OutputCost_vout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.VECTOR)
    _init_custom_OutputCost_sout = functools.partialmethod(_init_output_costs, edge_type=EdgeType.SCALAR)

    @property
    @functools.lru_cache()
    def utilization(self):
        total = 0
        total_units = sum(self.partition_counts.values())
        for partition_type, matrix in self.partition_matrices.items():
            # max_count = self.partition_counts[partition_type]
            # add up columns with non-zero elements in matrix
            active = sum(self._project_to_bool(np.sum(matrix, axis=0)))
            # currently_active = cvxpy.sum(matrix)
            total += active
            # total += currently_active / max_count
        return total / total_units

    def solve(self, **kwargs):
        self.model.setObjective(self.utilization, GRB.MINIMIZE)
        for k, v in kwargs.items():
            self.model.setParam(k, v)
        self.model.update()
        self.model.optimize()
        print("Status:", self.model.Status)
        if self.model.Status == GRB.OPTIMAL:
            return
        self.model.computeIIS()
        self.model.write("model.ilp")

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

    def _project_to_bool(self, var, name=""):
        if isinstance(var, Iterable):
            return np.array([self._project_to_bool(v, name=(name + str(i)) if name else "") for i, v in enumerate(var)])
        projected = self.model.addVar(vtype=GRB.BINARY, name=name)
        self.model.addConstr((projected == 0) >> (var == 0), name=(name + "_proj_bool" if name else ""))
        return projected

    def _convert_to_var(self, variable, name=""):
        if isinstance(variable, Iterable):
            return np.array([self._convert_to_var(v, name=(name + str(i)) if name else "") for i, v in enumerate(variable)])
        new_var = self.model.addVar(name=name, lb=-GRB.INFINITY, ub=GRB.INFINITY)
        constr_name = name + "_cvt_to_var" if name else ""
        self.model.addConstr(new_var == variable, name = constr_name)
        return new_var

    def and_(self, v1, v2):
        result = self.model.addVar(vtype=GRB.BINARY)
        self.model.addConstr(result == gpy.and_(v1, v2))
        return result


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
    # if model is INF_OR_UNBD, set DualReductions=0
    solver.solve(Threads=opts.thread, MIPGap=0.15)

    with open(opts.merge, "w") as merge_file:
        writer = csv.writer(merge_file, delimiter=",")
        for node, part_id, partition_type in solver.get_assignment():
            writer.writerow([node.node_id, part_id, partition_type.typename])


if __name__ == "__main__":
    main()
