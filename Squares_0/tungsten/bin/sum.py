#!/usr/bin/python

import argparse
import os
from shutil import copyfile

parser = argparse.ArgumentParser(description='Sum values in log')
parser.add_argument('path', help='Path to DRAM address log')
(opts, args) = parser.parse_known_args()

with open(opts.path, 'r') as rf:
    v = 0
    for line in rf:
        v += int(line)
print(v)
