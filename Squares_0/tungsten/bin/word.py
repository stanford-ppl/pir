#!/usr/bin/python

import argparse
import os
from shutil import copyfile

parser = argparse.ArgumentParser(description='Convert DRAM address to word offset')
parser.add_argument('path', help='Path to DRAM address log')
parser.add_argument('offset', type=long, help='Starting offset of DRAM array')
parser.add_argument('-w', '--word', default=32, help='Word width')
(opts, args) = parser.parse_known_args()
opts.dst = opts.path.replace(".log","_w.log")

with open(opts.path, 'r') as rf:
    with open(opts.dst, "w") as wf:
        for line in rf:
            addr = long(line)
            word = (addr - opts.offset) * 8 / opts.word
            wf.write(str(word) + "\n")
