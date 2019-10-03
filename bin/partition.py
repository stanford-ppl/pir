#!env/bin/python

import sys,os
import csv
import argparse

def main():
    parser = argparse.ArgumentParser(description='Graph Partition')
    parser.add_argument('path', help='Path to node.csv graph.csv spec.csv')
    (opts, args) = parser.parse_known_args()

    opts.node = os.path.join(opts.path, "node.csv")
    opts.edge = os.path.join(opts.path, "edge.csv")
    opts.spec = os.path.join(opts.path, "spec.csv")
    opts.partition = os.path.join(opts.path, "part.csv")

    with open(opts.node, 'r') as f:
        reader = csv.DictReader(f, delimiter=',')
        with open(opts.partition, "w") as pf:
            writer = csv.writer(pf, delimiter=",")
            for row in reader:
                writer.writerow([row["node"], row["node"]])

if __name__ == "__main__":
    main()


