import pandas as pd
import argparse
import os
import xml.etree.ElementTree as ET
import fnmatch
import json

from collections import defaultdict

def compute_ops():
    with open(opts.prog_path, 'r') as f:
        opts.prog = json.load(f)

    totops = defaultdict(int)

    for glb in opts.prog["IR"]:
        for ctx in opts.prog["IR"][glb]:
            if ctx not in opts.states["modules"]:
                continue
            active = opts.states["modules"][ctx]["active"]
            for op in opts.prog["IR"][glb][ctx]:
                vec,op,sc = op.split("|")
                vec = int(vec)
                totops["FU"] += active * vec
                if sc != "":
                    totops["Prog FU"] += active * vec
                if "FMA" in op:
                    cnt = 2
                else:
                    cnt = 1
                totops["All"] += cnt * active * vec
                if "Fix" in op:
                    totops["Fix"] += cnt * active * vec
                elif "Flt" in op:
                    totops["Flt"] += cnt * active * vec
                else:
                    totops["Other"] += cnt * active * vec

    runtime = opts.states["cycle"] / 1e9 # sec

    def quote(key):
        return key

    for key in totops:
        print("{}: {} OP, {:.2f} GOPS".format(quote(key), totops[key], totops[key] / runtime / 1e9))

def main():
    parser = argparse.ArgumentParser(description='Load simulation states')
    parser.add_argument('-s', '--state_path', type=str, default='{}/logs/state.json'.format(os.getcwd()), help='state log path')
    parser.add_argument('-p', '--prog_path', type=str,
            default='{}/../pir/out/program.json'.format(os.getcwd()), help='program stat path')

    global opts
    (opts, args) = parser.parse_known_args()

    opts.state_path = os.path.abspath(opts.state_path)
    opts.prog_path = os.path.abspath(opts.prog_path)

    with open(opts.state_path, 'r') as f:
        opts.states = json.load(f)

    compute_ops()

if __name__ == "__main__":
    main()
