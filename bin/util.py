import argparse
import subprocess
import time
import pickle
import os, sys
import csv
import fnmatch

global parser
parser = argparse.ArgumentParser(description='Run experiments')
parser.add_argument('-a', '--app', action='append', help='name of application to run')
parser.add_argument('-b', '--backend', type=str, action="append", help='Testing Backend')
parser.add_argument('-t', '--thread', type=int, default=16, help='Number of threads to run')
parser.add_argument('-p', '--project', type=str, default="pirTest", help='project name')
parser.add_argument('--gen', action='store_true', default=False, help='compile only')
parser.add_argument('--gendir', default="gen/")
parser.add_argument('-r', '--rerun', action='append', help='passes to rerun', default=[])
parser.add_argument('-F', '--force', action='store_true', default=False)

HEADER    = '\033[95m'
OKBLUE    = '\033[94m'
OKGREEN   = '\033[92m'
WARNING   = '\033[93m'
FAIL      = '\033[91m'
ENDC      = '\033[0m'
BOLD      = '\033[1m'
UNDERLINE = '\033[4m'
BLUE      = '\033[0;34m'
YELLOW    = '\033[1;33m'
GREEN     = '\033[0;32m'
RED       = '\033[0;31m'
CYAN      = '\033[0;36m'
ORANGE    = '\033[038;5;202m'
NC        = '\033[0m'

def cstr(color, msg):
    return "{}{}{}".format(color, msg, NC)

class Parser:
    def __init__(self, conf, key, pattern, parseLambda, default=None):
        self.key = key
        if type(pattern) == list:
            patterns = pattern
        else:
            patterns = [pattern]
        self.patterns = patterns
        self.conf = conf
        self.parseLambda = parseLambda
        conf[key] = default

    def parse(self, found):
        lines = [line for pat in self.patterns for line in found[pat]]
        if len(lines) != 0:
            self.conf[self.key] = self.parseLambda(lines)

def parseLog(log, parsers, conf):
    if not os.path.exists(log): return
    patterns = [pat for parser in parsers for pat in parser.patterns] 
    found = grep(log, patterns)
    for parser in parsers:
        parser.parse(found)

def getApps(backend, opts):
    apps = []
    gendir = "{}/{}".format(opts.gendir, backend)
    for app in os.listdir(gendir):
        path = os.path.join(gendir, app)
        if os.path.isdir(path):
            apps.append(app)
    apps = sorted(apps)
    if opts.app is not None:
        apps = [app for app in apps if any([fnmatch.fnmatch(app, pat) for pat in opts.app])]
    return apps

def getBackends():
    backends = []
    if opts.backend is not None:
        return opts.backend
    for backend in os.listdir(opts.gendir):
        backends.append(backend)
    return backends

def cat(path):
    print(cstr(CYAN,path + " ==============="))
    if not os.path.exists(path): return
    with open(path, "r") as f:
        for line in f:
            sys.stdout.write(line)

def tail(path):
    print(cstr(CYAN,path + " ==============="))
    if not os.path.exists(path): return
    subprocess.call(["tail", path])

def vim(path):
    print(cstr(CYAN,path + " ==============="))
    if not os.path.exists(path): return
    subprocess.call(["vim", path])

def grep(path, patterns):
    found = {}
    if type(patterns)!=list:
        patterns = [patterns]
    for pattern in patterns:
        found[pattern] = []
    if not os.path.exists(path): return found
    with open(path, 'r') as f:
        for line in f:
            for pattern in patterns:
                if pattern in line:
                    found[pattern].append(line)
    return found

def remove(path, opts):
    if os.path.exists(path):
        if (opts.force):
            os.remove(path)
        else:
            ans = raw_input('remove {}? y/n '.format(path))
            if ans == 'y':
                os.remove(path)
            elif ans == 'q':
                exit(0)

def loadSimData(datapath, backends=None):
    if backends is None:
        backends = []
        for backend in os.listdir(datapath):
            backend = backend.split(".csv")[0]
            if backend != 'summary':
                backends.append(backend)
    df = None
    for backend in backends:
        path = "{}/{}.csv".format(datapath, backend)
        if not os.path.exists(path): continue
        sims = csvToDataFrame(
            path, 
            "backend,app,param"
            )
        if df is None:
            df = sims
        else:
            prev = df.shape[0]
            new = sims.shape[0]
            df = pd.concat([df, sims], axis=0, sort=True)
            now = df.shape[0]
            if prev + new != now:
                print(prev, new, now, backend)
                assert(False)
    return df

def loadSummary(datapath):
    return csvToDataFrame(datapath, "backend,app,param")
