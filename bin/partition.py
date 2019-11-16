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
import atexit

Constraint = namedtuple("Constraint", ["ops", "vin", "sin", "vout", "sout"])
Node = namedtuple("Node", ["node", "op", "retime", "comment"])
Edge = namedtuple("Edge", ["src", "dst", "tp", "comment"])
NodePartition = namedtuple("NodePartition", ["node", "partition"])


def load_csv(fname, tp, use_fieldnames=False):
    with open(fname, "r") as f:
        return [tp(**row) for row in
                csv.DictReader(f, delimiter=",", fieldnames=tp._fields if use_fieldnames else None)]


def cleaned_name(node_id):
    if node_id[0] == "e":
        return node_id[1:]
    return node_id


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
        print(self._indent * "\t", self.name, self.elapsed)
        sys.stdout.flush()
        self._initialized = False
        assert self.contexts.pop() is self


TMP = []


class CVXPartitioner:
    nodes: typing.List[Node]
    edges: typing.List[Edge]
    constraints: Constraint
    cvx_constraints: typing.List

    delay_per_partition = 6
    buffer_capacity = 16
    network_delay = 1
    merge_probability = 0

    OPTIONS = frozenset({
        ("partitions", True),
        ("retiming_nodes", True),
        ("external_edges", False)
    })

    def __init__(self, nodes, edges, constraints, pre_partitioning):
        self.nodes = nodes[:]
        self.edges = edges[:]
        self.constraints = constraints
        self.cvx_constraints = []
        self.to_print = {}
        self.opts = {}
        self.verifiers = []
        self._pseudo_constraints = []

        # gurobi can't currently handle a warm start, so instead we just initialize with the number of partitions
        self.partitions = len({partition.partition for partition in pre_partitioning})
        self.num_nodes = len(nodes)
        self.retime_nodes = {node.node for node in nodes if node.retime}

        print("Initializing problem with", self.num_nodes, "nodes and", self.partitions, "partitions.")

        self.node_to_loc_map = {node_id.node: i for i, node_id in enumerate(nodes)}
        print(self.node_to_loc_map)

        initial_value = np.zeros(self.num_nodes)
        for node, partition in pre_partitioning:
            initial_value[self.node_to_loc_map[str(node)]] = partition

        self.node_to_partition_matrix = cvxpy.Variable(name="Matrix", shape=(self.num_nodes, self.partitions),
                                                       boolean=True)

        # constructs an array of just the partition numbers. Useful for dep constraints.
        self.node_partitions = cvxpy.Variable(name="Partitions", shape=(self.num_nodes,), nonneg=True,
                                              value=initial_value)
        self._add_constraint(self.node_partitions == self.node_to_partition_matrix @ np.arange(self.partitions))

        # stochasticity constraint: each node only maps to 1 partition.
        self._add_constraint(cvxpy.sum(self.node_to_partition_matrix, axis=1) == 1)

        # constraint on number of ops
        with TimeContext("Total Op Constraint"):
            total_ops = functools.reduce(operator.add, [
                self.node_to_partition_matrix[self.node_to_loc_map[node.node], :] * node.op for node in self.nodes])
            self._add_constraint(total_ops <= self.constraints.ops)

        # with TimeContext("Dependency Constraints"):
        #     self._init_dep_constraints()

        with TimeContext("Input Constraints"):
            self._init_input_constraints()

        with TimeContext("Output Constraints"):
            self._init_output_constraints()

    def set_opts(self, opts):
        self.opts.update(opts)

    @property
    @functools.lru_cache(None)
    def objective(self):
        total = {
            "partitions": (lambda: 0, self.num_partitions),
            "retiming_nodes": ( lambda: (self._init_edge_deps(), 0)[1], lambda: self.delay_gap()[0]),
            "external_edges": (lambda: 0, self.num_edges)
        }
        to_print = []
        with TimeContext("Objective"):
            objective = 0
            for option_name, func in total.items():
                print(option_name, self.opts[option_name])
                additional = func[self.opts[option_name]]()
                objective += additional
                if hasattr(additional, "value"):
                    to_print.append((option_name, additional))
        for name, var in to_print:
            atexit.register(lambda x, y: print(x, y.value), name, var)

        # large multiplier
        pseudo_constraint_sum = sum(self._pseudo_constraints, 0) * 256
        return cvxpy.Minimize(objective + pseudo_constraint_sum)

    def _add_constraint(self, constraint):
        assert constraint.is_dcp()
        self.cvx_constraints.append(constraint)

    def _add_pseudo_constraint(self, constraint):
        assert constraint.is_dcp()
        self._pseudo_constraints.append(constraint)

    def _is_internal_node(self, node):
        return node in self.node_to_loc_map

    def loc_to_node_id(self, loc):
        # for debugging purposes
        for node_id, nloc in self.node_to_loc_map.items():
            if loc == nloc:
                return node_id
        return ""

    @functools.lru_cache(None)
    def _init_edge_deps(self):
        filtered_edges = [edge for edge in self.edges if
                          self._is_internal_node(edge.src) and self._is_internal_node(edge.dst)]
        for src, dst, _, _ in filtered_edges:
            # If either end is a retime node, then we make sure that it can't merge.
            twiddle = src in self.retime_nodes or dst in self.retime_nodes
            # self._add_constraint(
            #     self.node_partitions[self.node_to_loc_map[src]] + twiddle <= self.node_partitions[
            #         self.node_to_loc_map[dst]])
            self._add_pseudo_constraint(cvxpy.maximum(self.node_partitions[self.node_to_loc_map[src]] + twiddle - self.node_partitions[self.node_to_loc_map[dst]], 0))

    @functools.lru_cache(None)
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
            if not push_map:
                continue
            movement = []
            for src, destinations in push_map.items():
                if not self._is_internal_node(src):
                    # we use this to filter out all of the contributions within a partition.
                    # since external nodes aren't in any relevant partition, they always contribute.
                    base_filter = np.zeros(self.partitions)
                else:
                    base_filter = self.node_to_partition_matrix[self.node_to_loc_map[src], :]

                contributions = functools.reduce(operator.add, [
                    self.node_to_partition_matrix[self.node_to_loc_map[dst]] for dst in destinations])
                movement.append(cvxpy.maximum(self._project_to_bool(contributions) - base_filter, 0))

                self.to_print["output - " + tp] = movement
            self._add_constraint(
                functools.reduce(operator.add, movement) <= input_constraints[tp])

    @functools.lru_cache(None)
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
            if self._is_internal_node(src) and not self._is_internal_node(dst):
                nodes_with_external_outputs[tp].add(src)

        node_push_map = {
            "s": defaultdict(set),
            "v": defaultdict(set)
        }

        for src, dst, tp, _ in self.edges:
            # don't count external nodes
            if not self._is_internal_node(src):
                continue
            # if a node already pushes out to an external node, we know it's guaranteed to have an output.
            if src in nodes_with_external_outputs[tp]:
                continue

            node_push_map[tp][self.node_to_loc_map[src]].add(self.node_to_loc_map[dst])

        for tp, push_map in node_push_map.items():
            if not push_map:
                continue
            exports = []
            for src_loc, destination_locs in push_map.items():
                src_partition = self.node_to_partition_matrix[src_loc, :]
                destination_vec = sum(self.node_to_partition_matrix[dst] for dst in destination_locs)
                has_exports = self._project_to_bool(
                    cvxpy.sum(cvxpy.maximum(destination_vec - src_partition * self.num_nodes, 0)))
                # has_exports && src_partition
                exports.append(cvxpy.maximum(has_exports + src_partition - 1, 0))

            total_movement = functools.reduce(operator.add, exports)
            for src in nodes_with_external_outputs[tp]:
                total_movement += self.node_to_partition_matrix[self.node_to_loc_map[src], :]
            self._add_constraint(total_movement <= output_constraints[tp])

    @functools.lru_cache(None)
    def num_partitions(self):
        return cvxpy.max(self.node_partitions) + 1

    @property
    @functools.lru_cache(None)
    def internal_edges(self):
        return [edge for edge in self.edges if self._is_internal_node(edge.src) and self._is_internal_node(edge.dst)]

    @property
    @functools.lru_cache(None)
    def external_input_edges(self):
        return [edge for edge in self.edges if
                not self._is_internal_node(edge.src) and self._is_internal_node(edge.dst)]

    @property
    @functools.lru_cache(None)
    def external_output_edges(self):
        return [edge for edge in self.edges if
                self._is_internal_node(edge.src) and not self._is_internal_node(edge.dst)]

    @property
    @functools.lru_cache(None)
    def adjacency_matrix(self):
        adjacency_matrix = scipy.sparse.lil_matrix((self.num_nodes, self.num_nodes), dtype=bool)
        for src, dst, _, _ in self.internal_edges:
            adjacency_matrix[self.node_to_loc_map[src], self.node_to_loc_map[dst]] = True
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

    @functools.lru_cache(None)
    def _nodes_between(self, src_loc, dst_loc, depth):
        if depth == 0:
            return set()
        if self.reachability_matrix[src_loc, dst_loc]:
            return {src_loc, dst_loc}
        results = set()
        for _, middle in scipy.transpose(self.reachability_matrix[src_loc].nonzero()):
            results |= self._nodes_between(middle, dst_loc, depth - 1)
        if not results:
            return results
        results.add(src_loc)
        return results

    def _possible_in_same_partition(self, loc1, loc2):
        # if all the all nodes between src and dst <= 6 (inclusive), then it can fit, or if src and dst are disconnected
        # check if they are disconnected:

        # no dependency
        if not (self.full_reachability_matrix[loc1, loc2] or self.full_reachability_matrix[loc2, loc1]):
            return True

        # loc1 -> loc2 dependency
        if len(self._nodes_between(loc1, loc2, self.constraints.ops)) > self.constraints.ops:
            return False

        # was backwards; loc2 -> loc1 dependency
        if len(self._nodes_between(loc2, loc1, self.constraints.ops)) > self.constraints.ops:
            return False
        return True

    def _is_different_value(self, a, b):
        v1 = self._project_to_bool(cvxpy.abs(b - a))
        return v1

    @functools.lru_cache(None)
    def delay_gap(self):
        max_delay = self.partitions * self.delay_per_partition * 2

        # When the outputs start receiving
        self.external_destination_delay = external_destination_delay = cvxpy.Variable(name="External Delay")
        self.delays = delays = cvxpy.Variable(name="Delays", shape=self.num_nodes)
        partition_delays = cvxpy.Variable(name="Partition Delays", shape=self.partitions)
        for src, dst, _, _ in self.internal_edges:
            src_loc = self.node_to_loc_map[src]
            dst_loc = self.node_to_loc_map[dst]
            if not self._possible_in_same_partition(src_loc, dst_loc):
                # the constraint is always active if they can't be in the same partition
                activity_component = 0
            else:
                # strongly negative if in same partition, otherwise is 0
                not_same_partition = self._is_different_value(self.node_partitions[dst_loc],
                                                              self.node_partitions[src_loc])
                activity_component = not_same_partition * max_delay - max_delay
            min_start_delay = delays[src_loc] + activity_component + self.delay_per_partition + self.network_delay
            # self._add_constraint(
            #     delays[dst_loc] >= min_start_delay)
            self._add_pseudo_constraint(
                cvxpy.maximum(min_start_delay - delays[dst_loc], 0)
            )

            twiddle = src in self.retime_nodes or dst in self.retime_nodes
            self._add_constraint(delays[dst_loc] >= delays[src_loc] + twiddle)
            # self._add_constraint(delays[dst_loc] >= delays[src_loc])

        for i in range(self.num_nodes):
            # 0 if node is in partition otherwise strongly positive
            activation = self.node_to_partition_matrix[i, :] * -max_delay + max_delay
            target_delay = cvxpy.min(partition_delays + activation)
            # self._add_constraint(delays[i] <= target_delay)
            self._add_pseudo_constraint(cvxpy.maximum(delays[i] - target_delay, 0))

            # 0 if node is in partition otherwise strongly negative
            activation = self.node_to_partition_matrix[i, :] * max_delay - max_delay
            target_delay = cvxpy.max(partition_delays + activation)
            # self._add_constraint(delays[i] >= target_delay)
            self._add_pseudo_constraint(cvxpy.maximum(target_delay - delays[i], 0))

        # # constrain that the delay for nodes in the same partition are equal.
        # # We only care for nodes which might be in the same partition.
        # for loc1, loc2 in itertools.combinations(range(self.num_nodes), 2):
        #     if not self._possible_in_same_partition(loc1, loc2):
        #         continue
        #     # if the nodes are in the same partition, then impose an equality constraint on the delays
        #     peq = cvxpy.sum(
        #         cvxpy.maximum(self.node_to_partition_matrix[loc1, :] + self.node_to_partition_matrix[loc2, :] - 1, 0))
        #     dne = self._project_to_bool(cvxpy.abs(delays[loc1] - delays[loc2]))
        #     # dne = cvxpy.minimum(cvxpy.abs(delays[loc1] - delays[loc2]), 1)
        #     self._add_pseudo_constraint(cvxpy.maximum(peq + dne - 1.5, 0))
        #     # self._add_constraint(peq + dne <= 1.3)

        nodes_with_external_input = {edge.dst for edge in self.external_input_edges}
        for dst in nodes_with_external_input:
            # src is external, dst is internal. The destination must start at least
            self._add_constraint(
                self.delays[self.node_to_loc_map[dst]] >= self.network_delay + self.delay_per_partition)

        for src, dst, _, _ in self.external_output_edges:
            # self._add_constraint(delays[self.node_to_loc_map[
            #     src]] + self.network_delay + self.delay_per_partition <= external_destination_delay)
            self._add_pseudo_constraint(
                cvxpy.maximum(delays[self.node_to_loc_map[
                    src]] + self.network_delay + self.delay_per_partition - external_destination_delay, 0)
            )

        gaps = {
            "s": [],
            "v": []
        }
        for src, dst, tp, _ in self.edges:
            if self._is_internal_node(src):
                src_loc = self.node_to_loc_map[src]
                src_delay = delays[src_loc]
            else:
                # external input
                src_delay = 0
            if self._is_internal_node(dst):
                dst_loc = self.node_to_loc_map[dst]
                dst_delay = delays[dst_loc]
            else:
                dst_delay = external_destination_delay
            gaps[tp].append(dst_delay - src_delay)

        gap_costs = {
            "v": 1.0 / min(self.constraints.vin, self.constraints.vout),
            "s": 1.0 / min(self.constraints.sin, self.constraints.sout)
        }
        total_retime_cost = 0
        for tp in "sv":
            num_large_gaps = sum(
                [self._project_to_bool(cvxpy.maximum(gap - self.buffer_capacity, 0)) for gap in gaps[tp]], 0)
            # if merge_probability is low, then there's a high chance of requiring separate compute anyways.
            # if merge_probability is 1, then it requires gap_costs[tp]
            # if merge_probability is 0, then it requires 1 full PCU.
            retime_multi = gap_costs[tp] * self.merge_probability + (1 - gap_costs[tp]) * (1 - self.merge_probability)
            total_retime_cost += gap_costs[tp] * num_large_gaps * retime_multi

        # want to minimize total_delay

        def verify():
            for loc1, loc2 in itertools.combinations(range(self.num_nodes), 2):
                if self.node_partitions.value[loc1] == self.node_partitions.value[loc2]:
                    if delays[loc1].value != delays[loc2].value:
                        return False
            return True

        self.verifiers.append(verify)

        return total_retime_cost, external_destination_delay

    @functools.lru_cache(None)
    def num_edges(self):
        # calculate the number of edges between partitions

        # When retiming, we use additional fractional PCUs.
        additional_edge_costs = {
            "v": 1.0 / min(self.constraints.vin, self.constraints.vout),
            "s": 1.0 / min(self.constraints.sin, self.constraints.sout)
        }
        cross_edges = {
            "s": [],
            "v": []
        }
        for src, dst, tp, _ in self.edges:
            if not (self._is_internal_node(src) and self._is_internal_node(dst)):
                continue
            src_loc = self.node_to_loc_map[src]
            dst_loc = self.node_to_loc_map[dst]
            not_in_same_partition = self._project_to_bool(self.node_partitions[dst_loc] - self.node_partitions[src_loc])
            cross_edges[tp].append(not_in_same_partition)

        total = 0
        for tp, cross_edge_list in cross_edges.items():
            total += additional_edge_costs[tp] * sum(cross_edge_list, 0)
        return total

    def verify(self):
        return all(verifier() for verifier in self.verifiers)

    def _project_to_bool(self, var):
        projected = cvxpy.Variable(boolean=True, shape=var.shape)
        large_constant = (self.num_nodes * self.partitions)
        self._add_constraint(var <= projected * large_constant)
        # self._add_constraint(var >= projected)
        return projected

    def _multiply_bool(self, a, b):
        return cvxpy.maximum(a + b - 1, 0)

    def _add_bool(self, a, b):
        return self._project_to_bool(a + b)

    def solve(self, **kwargs):
        problem = cvxpy.Problem(self.objective, self.cvx_constraints)
        problem.solve(**kwargs)
        print(problem.status)

    def get_assignment(self):
        return [
            (node.node, int(self.node_partitions.value[self.node_to_loc_map[node.node]]))
            for node in self.nodes
        ]


def partition_solver(nodes, edges, constraint, pre_partitioning, opts):
    with TimeContext("Solver Initalization"):
        solver = CVXPartitioner(nodes, edges, constraint, pre_partitioning)
        print(vars(opts))
        solver.set_opts(vars(opts))
    solver.solve(solver="GUROBI", verbose=True, warm_start=True, Threads=opts.thread, MIPGap=0.15)
    assert solver.verify()

    with open(opts.partition, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node, partition in solver.get_assignment():
            writer.writerow([node, partition])

    if hasattr(solver, "delays"):
        with open(opts.delays, "w") as df:
            writer = csv.writer(df, delimiter=",")
            for node in nodes:
                writer.writerow([node.node, solver.delays.value[solver.node_to_loc_map[node.node]]])
    print("Generate {}".format(opts.partition))


def partition_dummy(nodes, edges, constraint, pre_partitioning, opts):
    with open(opts.partition, "w") as pf:
        writer = csv.writer(pf, delimiter=",")
        for node in nodes:
            writer.writerow([node.node, node.node])
    print("Generate {}".format(opts.partition))

def str2bool(v):
    if isinstance(v, bool):
        return v
    if v.lower() in ('yes', 'true', 't', 'y', '1'):
        return True
    elif v.lower() in ('no', 'false', 'f', 'n', '0'):
        return False
    else:
        raise argparse.ArgumentTypeError('Boolean value expected.')

def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
    parser.add_argument('-t', '--thread', type=int, default=1, help='Number of threads for solver')

    for opt, default in CVXPartitioner.OPTIONS:
        short_name = opt[0].lower()
        parser.add_argument("-" + short_name, "--" + opt.lower(), type=str2bool, default=default,
                            help="Solver opt: {}".format(opt))

    opts, args = parser.parse_known_args()
    print(opts, args)

    opts.node = os.path.join(opts.path, "node.csv")
    opts.edge = os.path.join(opts.path, "edge.csv")
    opts.spec = os.path.join(opts.path, "spec.csv")
    opts.partition = os.path.join(opts.path, "part.csv")
    opts.delays = os.path.join(opts.path, "delay.csv")

    nodes = load_csv(opts.node, Node)
    nodes = [element._replace(op=int(element.op), retime=element.retime == "true") for element in nodes]

    edges = load_csv(opts.edge, Edge)
    edges = [element._replace(
        src=cleaned_name(element.src),
        dst=cleaned_name(element.dst)
    ) for element in edges]
    constraint = load_csv(opts.spec, Constraint)[0]
    constraint = Constraint(*list(map(int, constraint)))

    pre_partitioning = load_csv(os.path.join(opts.path, "init.csv"), NodePartition)
    pre_partitioning = [NodePartition(*list(map(int, partition))) for partition in pre_partitioning]

    partition_solver(nodes, edges, constraint, pre_partitioning, opts)
    # partition_dummy(nodes,edges,constraint,pre_partitioning, opts)


if __name__ == "__main__":
    main()
