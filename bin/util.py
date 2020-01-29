import argparse
import subprocess
import time
import pickle
import os, sys
import csv
import fnmatch
import glob
import shutil
import timeit
import datetime
import socket
import getpass
import socket
from collections import OrderedDict

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
    print(cstr(CYAN,os.path.abspath(path) + " ==============="))
    if not os.path.exists(path): return
    with open(path, "r") as f:
        for line in f:
            sys.stdout.write(line)

def tail(path):
    print(cstr(CYAN,os.path.abspath(path) + " ==============="))
    if not os.path.exists(path): return
    subprocess.call(["tail", path])

def vim(path):
    print(cstr(CYAN,os.path.abspath(path) + " ==============="))
    if not os.path.exists(path): return
    subprocess.call(["vim", path])

def grep(path, patterns):
    found = {}
    if type(patterns)!=list:
        patterns = [patterns]
    for pattern in patterns:
        found[pattern] = []
    paths = [path] if os.path.isfile(path) else glob.glob(path)
    for p in paths:
        with open(p, 'r') as f:
            for line in f:
                ncline = line.replace(RED,'').replace(NC,'')
                ncline = ncline.replace('\x1b[31m','').replace('\x1b[31m','')
                for pattern in patterns:
                    if pattern in ncline:
                        found[pattern].append(line)
    return found

def remove(path, force):
    if os.path.exists(path):
        if (force):
            if os.path.isdir(path): 
                shutil.rmtree(path)
            else:
                os.remove(path)
            return True
        else:
            ans = input('remove {}? y/n '.format(path))
            if ans == 'y':
                if os.path.isdir(path): 
                    shutil.rmtree(path)
                else:
                    os.remove(path)
                return True
            elif ans == 'q':
                exit(0)
        return False

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

def default_config_path():
    path = os.path.join(os.environ['HOME'], '.pirconf')
    return path

def parse_token(token):
    token = token.strip()
    if token == '' or token.startswith('#'):
        return None,None
    if '=' in token:
        key,value,=token.split('=')
    else:
        key = token
        value = 'true'
    return key,value

def get_configs(path=default_config_path()):
    d = OrderedDict()
    if os.path.exists(path):
        with open(path, 'r') as f:
            for line in f:
                key,value = parse_token(line)
                if key is not None:
                    d[key] = value
    return d

def set_config(key, value, path=default_config_path()):
    d = OrderedDict()
    if os.path.exists(path):
        d = get_configs(path)
    d[key] = value
    with open(path, 'w') as f:
        for key in d:
            f.write(f'{key}={d[key]}\n')

def parse_configs(optstr):
    for opt in optstr.split('--'):
        opt = opt.strip()
        if opt=='':
            continue
        key,value = parse_token(opt)

start_time = None
def tic():
    global start_time
    start_time = timeit.default_timer()

def toc():
    duration = timeit.default_timer() - start_time 
    return str(datetime.timedelta(seconds=round(duration, 2)))

def oncluster():
    hostname = socket.gethostname()
    return hostname in ['lagos','tucson'] or 'edo-' in hostname

def create_ivy2():
    username = getpass.getuser()
    if oncluster():
        ivy2 = f'/scratch/{username}/.ivy2'
        if not os.path.exists(ivy2):
            os.mkdir(ivy2)
