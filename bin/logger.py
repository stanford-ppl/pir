import os
import csv
import shutil
from collections import OrderedDict
import pandas as pd
from pandautil import *
import numpy as np
import math
import fnmatch
import json
import traceback
import time

from util import *

parser.add_argument('path', type=str, help='Path to parsing directory')
parser.add_argument('-A', '--isApp', dest="path_type", action='store_const', const='app',
        default='backend')
parser.add_argument('-B', '--isBackend', dest="path_type", action='store_const', const='backend', default='backend')
parser.add_argument('-G', '--isGen', dest="path_type", action='store_const', const='gen',
        default='backend')
parser.add_argument('-s', '--summarize', action='store_true', default=False, help='summarize log into csv')
parser.add_argument('-d', '--diff', dest='show_diff', action='store_true', default=False, help='showing difference')
parser.add_argument('-H', '--history', dest='history_depth', type=int, default=0, help='showing history with depth')
parser.add_argument('-i', '--history_id', type=int, help='showing ith history in reverse order')
parser.add_argument('-l', '--log', type=str, help='showing log by name')
parser.add_argument('-wh', '--walk_history', default=False, action='store_true', help='Walk through history')
parser.add_argument('--logdir', default="{}/spatial/pir/logs/".format(os.environ['HOME']))
parser.add_argument('--spatial_dir', default="{}/spatial/".format(os.environ['HOME']))
parser.add_argument('--pir_dir', default="{}/spatial/pir".format(os.environ['HOME']))
parser.add_argument('-f', '--filter', dest='filter_str', action='append', help='Filter apps')
parser.add_argument('-m', '--message', default='', help='Additional fields to print in message')
parser.add_argument('-pf', '--print_fields', default=False, action='store_true', help='Print fields')
parser.add_argument('-cg', '--clean_gen', default=False, action='store_true', 
    help='Clean generated directory')
parser.add_argument('--live', action='store_true', default=False, help='continues showing status')

def to_conf(tab, **kws):
    tab = lookup(tab, drop=False, **kws)
    conf = tab.to_dict()
    for k in conf:
        if type(conf[k]) in [float, np.float64] and math.isnan(conf[k]):
            conf[k] = None
    return conf

def get(conf,key):
    if key not in conf: return None
    return conf[key]

def get_sha(path):
    shatime = subprocess.check_output("git log --pretty=format:'%h,%ad' -n 1 --date=iso".split(" "),
        cwd=path).decode().replace("'","").split(",")
    return shatime

def get_sha_msg(sha, path):
    if sha is None: return ""
    msg = subprocess.check_output("git log --format=%B -n 1 {}".format(sha).split(" "),
    cwd=path).decode().replace("'","").replace("\n","")
    return msg

def derive_simfreq(conf, opts):
    p2ptime = get(conf,'runp2p_time')
    p2pcycle = get(conf,'runp2p_cycle')
    if p2ptime is None or p2pcycle is None:
        conf['simfreq'] = None
        return None
    p2ptime = p2ptime.split(":")
    if len(p2ptime) == 2:
        p2ptime.insert(0, 0)
    hour,min,sec = [float(time) for time in p2ptime]
    sec = hour * 60 * 60 + min * 60 + sec
    ans = float(p2pcycle) / sec / 1000
    conf['simfreq'] = '{}kHz'.format(round(ans,2))

def parse_success(conf):
    for p in ['runp2p', 'runhybrid']:
        conf[p+'_success'] = \
            conf[p+'_complete'] and \
            not conf[p+'_deadlock'] and \
            conf[p+'_err'] is None
    return conf['runp2p_success']

def parse_genpir(pirsrc, logpath, conf, opts):
    if os.path.exists(pirsrc):
        conf['genpir'] = True
        conf['genpir_err'] = None
    else:
        conf['genpir'] = False
        conf['genpir_err'] = None
        # match = grep("{}/00*".format(logpath),
                # ["error", "exception", "Exception"])
        # lines = [line for pat in match for line in match[pat]]

        # match = grep("{}/*_exception.log".format(logpath),
                # ["error", "exception", "Exception"])
        # lines = lines + [line for pat in match for line in match[pat]]
        # if len(lines) != 0:
            # conf['genpir_err'] = lines[0].replace("\n","")
        # else:
            # conf['genpir_err'] = None

def parseLog(conf, key, patterns, parseLambda, default=None, logs=[], prefix=False):
    prefix = prefix or len(logs) > 1
    if type(patterns)!=list:
        patterns = [patterns]
    for log in logs:
        logname = log.split("/")[-1].split(".log")[0]
        mykey = key if not prefix else logname + "_" + key
        conf[mykey] = default
        if not os.path.exists(log): continue
        found = grep(log, patterns)
        alllines = []
        for pat in patterns:
            lines = found[pat]
            alllines +=lines
        if len(alllines) > 0:
            try:
                conf[mykey] = parseLambda(alllines)
            except Exception as e: 
                print(alllines)
                traceback.print_exc()
                print(log)
                # removed = remove(log, False)
                # if not removed:
                    # exit()
                conf[mykey] = "parse error"

def parse_proutesummary(log, conf, opts):
    if not os.path.exists(log): return
    with open(log, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            for k in row:
                if k in conf:
                    conf[k] = row[k]

def parseSimState(log, conf, opts):
    if not os.path.exists(log):
        return
    try:
        with open(log) as json_file:
            data = json.load(json_file)
            active = []
            for m in data['modules']:
                if "Context"  in m:
                    active.append(int(data['modules'][m]['active']))
            cycle = data['cycle']
            maxActive = max(active)
            avgActive = float(np.mean(active))
            conf["maxActive"] = maxActive * 100.0 / cycle
            conf["avgActive"] = avgActive * 100.0 / cycle
    except Exception as e:
        traceback.print_exc()
        print(log)
        return

def parsePirConf(log, conf, opts):
    if not os.path.exists(log):
        return
    with open(log, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            if row['default'] == 'None' and row['value'] == 'None':
                continue
            key = row['key']
            if 'home' in key or key in ['ckpt','path']:
                continue
            conf[key] = row['default'] if row['value'] == 'None' else row['value']
            if conf[key] == 'true':
                conf[key] = True
            elif conf[key] == 'false':
                conf[key] = False
            elif conf[key].isdigit():
                conf[key] = int(conf[key])

def parseProgReport(log, conf, postfix, opts):
    if not os.path.exists(log):
        return
    try:
        with open(log) as json_file:
                data = json.load(json_file)
    except Exception as e:
        traceback.print_exc()
        print(log)
        # removed = remove(log, opts.force)
        # if not removed:
            # exit()
        # else:
            # return
        return
    for k in data:
        if k == "IR":
            continue
        conf["V" + k+postfix] = data[k]

def applyHistFilter(history, fs, opts):
    for k in cond:
        if k in fs:
            pat,v = fs.split(k)
            if pat in history.columns.values:
                if k == "<":
                    return history[history[pat] < float(v)]
                if k == "<=":
                    return history[history[pat] <= float(v)]
                if k == ">":
                    return history[history[pat] > float(v)]
                if k == ">=":
                    return history[history[pat] >= float(v)]
                if k == "==":
                    return history[history[pat] == float(v)]
    if ":" in fs:
        p,pat = fs.split(":",1)
        if pat == "notNone":
            return history[history[pat].notnull]
        if pat == "None":
            return history[history[pat].isnull]
        mask = []
        for v in history[p].values:
            mask.append(fnmatch.fnmatch(str(v), pat))
        return history[mask]
    else:
        pat = fs
        return history[history[pat]]

def applyFilter(conf, opts):
    return all([f(conf) for f in opts.filter]);

cond = ["<",">","==","<=",">="]
def condfunc(k, c, v):
    c = float(c)
    v = float(v)
    if k == "<":
        return c < v
    if k == ">":
        return c > v
    if k == "<=":
        return c <= v
    if k == ">=":
        return c >= v
    if k == "==":
        return c == v

def setFilter(fs, opts):
    for k in cond:
        if k in fs:
            pat,v = fs.split(k)
            opts.filter.append(lambda conf: get(conf,pat) is not None and condfunc(k, conf[pat], v))
            return
    if ":" in fs:
        p,pat = fs.split(":",1)
        if pat == "notNone":
            opts.filter.append(lambda conf: p in conf and conf[p] is not None)
            return
        if pat == "None":
            opts.filter.append(lambda conf: p in conf and conf[p] is None)
            return
        opts.filter.append(lambda conf: get(conf,p) is not None and fnmatch.fnmatch(str(conf[p]), pat))
    else:
        p = fs
        opts.filter.append(lambda conf: get(conf,p) is not None and conf[p])

def setFilterRules(opts):
    opts.filter = []
    if opts.filter_str is None: return
    for fs in opts.filter_str:
        setFilter(fs, opts)

class Logger():
    def __init__(self, args=None):
        (opts, args) = parser.parse_known_args(args=args)
        self.opts = opts

        self.show_history()

        if opts.show_diff:
            self.load_history(logFilter = lambda logs: logs[:22])

        path = opts.path.rstrip('/')
        if opts.path_type == "app":
            path, app = path.rsplit('/',1)
            gen, backend = path.rsplit('/',1)
            opts.app = [app]
            opts.backend = [backend]
            opts.gendir = gen
        elif opts.path_type == "backend":
            gen, backend = path.rsplit('/',1)
            opts.backend = [backend]
            opts.gendir = gen
        elif opts.path_type == "gen":
            opts.gendir = path
            opts.backend = getBackends(opts)

        if opts.clean_gen:
            self.clean_gen()

        for i in range(len(opts.backend)):
            if 'Tst' in opts.backend[i]:
                opts.project = backend.split("_")[1]
                opts.backend[i] = backend.split("_")[0]

        setFilterRules(opts)

        if opts.live:
            while True:
                self.show_gen()
                time.sleep(15)
        else:
            self.show_gen()

    def clean_gen(self):
        toremove = []
        def addremove(path,approved):
            toremove.append(path)
            if len(toremove) > 200 and not approved:
                for path in toremove[0:200]:
                    print(path)
                ans = input('remove? y/n ')
                if ans == 'y':
                    return True
                else:
                    exit()
            return approved
        approved=False
        for (dirpath, dirnames, filenames) in os.walk(self.opts.gendir):
            for filename in filenames:
                path = os.sep.join([dirpath, filename])
                if (fnmatch.fnmatch(filename,"00*_*.log")):
                    approved = addremove(path,approved)
                # print(path)
            for dir in dirnames:
                path = os.sep.join([dirpath, dir])
                if dir in ['target','info']:
                    approved = addremove(path,approved)
                if fnmatch.fnmatch(path, "*/pir/out/dot"):
                    approved = addremove(path,approved)
                if fnmatch.fnmatch(path, "*/pir/out/pir*.ckpt"):
                    approved = addremove(path,approved)

        self.opts.force = True
        ans = input('remove {} files? y/n '.format(len(toremove)))
        if ans == 'y':
            for path in toremove:
                remove(path,self.opts.force)
        exit()

    def load_history(self, logFilter=lambda logs: logs):
        opts = self.opts
        logs = os.listdir(opts.logdir)
        logs = sorted(logs, reverse = True)
        logs = logFilter(logs)
        opts.logs = logs
        history = None
        for log in logs:
            tab = pd.read_csv(opts.logdir + log, header=0)
            tab['time'] = os.path.getmtime(opts.logdir + log)
            tab['log'] = log
            if history is None:
                history = tab
            else:
                history = pd.concat([history, tab], axis=0, sort=False)  

        if history is None:
            print("No history to compare with")
        else:
            self.history = history

    def print_history(self, logFilter):
        opts = self.opts

        self.load_history(logFilter)
        history = self.history

        # diffkey = 'succeeded'
        # history =  history[history['succeeded']]

        if opts.filter_str is not None:
            for fs in opts.filter_str:
                history = applyHistFilter(history, fs, opts)

        if opts.project is not None:
            history = lookup(history, project=opts.project, drop=False)

        if opts.backend is not None:
            history = history[history["backend"].isin(opts.backend)]

        if opts.app is not None:
            mask = []
            for app in history["app"]:
                mask.append(any([fnmatch.fnmatch(app, pat) for pat in opts.app]))
            history = history[mask]


        if opts.history_depth>0:
            history = history.groupby(["project", "app", "backend"]).apply(lambda x: x.sort_values(["time"]).tail(opts.history_depth))
        else:
            history = history.sort_values(["project", "app", "backend"])

        if history.shape[0] > 0:
            for idx, row in history.iterrows():
                pconf = to_conf(row)
                print(self.getMessage(pconf,isHistory=True))
        
        for log in opts.logs:
            print(log)

    def show_history(self):
        opts = self.opts

        if opts.print_fields:
            self.load_history(logFilter=lambda logs: [logs[0]])
            fields = sorted(self.history.columns.values)
            for f in fields:
                print(f)
            exit()
        if opts.history_id is not None:
            self.print_history(logFilter=lambda logs: [logs[opts.history_id]])
            exit()
        if opts.log is not None:
            self.print_history(logFilter=lambda logs: [opts.log])
            exit()
        if opts.walk_history:
            nlogs = len(os.listdir(opts.logdir))
            for i in range(nlogs):
                self.print_history(logFilter=lambda logs: [logs[i]])
                ans = input('[{}] continue? '.format(i))
                if ans != 'y' and ans !='n':
                    exit()
            exit()

    def show_gen(self):
        opts = self.opts
        self.pir_sha,self.pir_time = get_sha(opts.pir_dir)
        self.spatial_sha,_ = get_sha(opts.spatial_dir)
        self.rules = []

        for backend in opts.backend:
            numRun = 0
            numSucc = 0
            if 'Tst' in backend:
                apps = getApps(backend + "_" + opts.project, opts)
            else:
                apps = getApps(backend, opts)
            confs = []
            opts.show_app = len(apps)==1 and not opts.summarize
            for app in apps:
                conf = OrderedDict()
                conf['app'] = app
                conf['project'] = opts.project
                conf['backend'] = backend
                self.parse(conf)
                matched = applyFilter(conf, opts)
                if not matched: continue
                self.print_message(conf)
                reruns = self.removeRules(conf)
                if opts.show_app:
                    tail(self.gentst)
                    tail(self.runproute)
                    tail(self.maketst)
                    tail(self.runp2p)
                    tail(self.runhybrid)
                confs.append(conf)
                numRun += 1
                if conf['succeeded']: numSucc += 1
            self.summarize(backend, confs)
            if numRun != 0 and 'apponly' not in opts.message:
                print('Succeeded {} / {} ({:0.2f}) %'.format(numSucc, numRun, numSucc*100.0/numRun))


    def removeRules(self,conf):
        opts = self.opts
        reruns = [] + opts.rerun
        # if conf['runpir_err'] is not None and 'not found: value x' in conf['runpir_err']:
            # print(conf['runpir_err'].strip())
            # reruns.append('genpir')
        # if conf['runpir_err'] is not None and 'value depth is not a member of object pir.node.RegFile' in conf['runpir_err']:
            # print(conf['runpir_err'].strip())
            # reruns.append('genpir')
        # if conf['runpir_err'] is not None and 'value addr is not a member of pir.node.FringeDenseStore' in conf['runpir_err']:
            # print(conf['runpir_err'].strip())
            # reruns.append('genpir')
        # if conf['runtst_err'] is not None and 'Assertion fail' in conf['runtst_err']:
            # rerunr.append('gentst')
            # reruns.append('maketst')
            # reruns.append('runtst')
        # if not conf['succeeded']:
            # reruns.append('genpir')
            # reruns.append('gentst')
            # reruns.append('maketst')
            # reruns.append('runtst')
    
        for p in reruns:
            if p == 'genpir':
                remove(self.AccelMain, opts.force)
            elif p == 'runpsim':
                remove(self.gentrace, opts.force)
                remove(self.genpsim, opts.force)
                remove(self.runpsim, opts.force)
            elif p == 'all':
                remove(self.appdir, opts.force)
            else:
                remove(getattr(self, p), opts.force)
        return reruns

    def print_message(self, conf):
        opts = self.opts
        msg = self.getMessage(conf)
        if not opts.summarize and not opts.show_diff:
            print(msg)
    
        if not opts.show_diff:
            return
    
        if self.history is None:
            print("No history to compare with")
            return
    
        diffkey = 'succeeded'
        # diffkey = 'genpir'
    
        history = self.history
    
        history = history[(history.app==conf['app']) & (history.project == conf['project']) & \
                (history.backend==conf['backend'])]
        prevsucc =  history[history[diffkey]]
    
        error = False
        for key in conf:
            if 'err' in conf:
                if conf[key] is not None:
                    error = True
        if error and prevsucc.shape[0] > 0:
            times = get_col(prevsucc, 'time')
            pconf = to_conf(prevsucc.iloc[np.argmax(times), :])
            print('{} {}'.format(msg, cstr(RED,'(Regression)')))
            print('{} {} {} {}'.format(self.getMessage(pconf), pconf['spatial_sha'], 
                get(pconf,'pir_sha'), pconf['time']))
        elif not conf[diffkey] and not error:
            print('{}'.format(msg))
        elif conf[diffkey]:
            if prevsucc.shape[0] == 0:
                print('{} {}'.format(msg, cstr(GREEN,'(New)')))
            # else:
                # times = get_col(prevsucc, 'time')
                # pconf = to_conf(prevsucc.iloc[np.argmax(times), :])
                # prevcu = pconf['PCU'] + pconf['PMU'] + pconf['DAG']
                # cu = conf['PCU'] + conf['PMU'] + conf['DAG']
                # prevcycle = pconf['runp2p_cycle']
                # cycle = conf['runp2p_cycle']
                # if cycle is not None and prevcycle is not None:
                    # worse = (cu - prevcu) > 2 or (cycle - prevcycle) > max(cycle * 0.05,200)
                    # better = (prevcu - cu) > 2 or (prevcycle - cycle) > max(cycle * 0.05,200)
                    # if worse:
                        # print('{} {}'.format(msg, cstr(RED,'(Worse)')))
                        # print('{} {} {} {}'.format(self.getMessage(pconf), pconf['spatial_sha'], 
                            # get(pconf,'pir_sha'), pconf['time']))
                    # elif better:
                        # print('{} {}'.format(msg, cstr(GREEN,'(Better)')))
                        # print('{} {} {} {}'.format(self.getMessage(pconf), pconf['spatial_sha'], 
                            # get(pconf,'pir_sha'), pconf['time']))

    def getMessage(self, conf, isHistory=False):
        opts = self.opts
        if opts.message=='apponly':
            return conf['app']
        msg = []
        msg.append(conf['backend'])
        msg.append(conf['app'])
        succeeded = False
    
        for f in opts.message.split(","):
            ans = get(conf,f)
            if (type(ans) == float):
                ans = round(ans,1)
            if ans is None: continue
            msg.append(cstr(CYAN, f + ':' + str(ans)))
    
        if get(conf,'genpir'):
            msg.append(cstr(GREEN, 'genpir'))
        else:
            msg.append(cstr(RED, 'genpir'))
        if get(conf,'genpir_err') is not None:
            msg.append(cstr(RED, get(conf,'genpir_err')))
    
        # if get(conf,'psim_deadlock'):
            # msg.append(cstr(RED, 'DEADLOCK'))
        # elif get(conf,'gentrace_err') is not None:
            # msg.append(cstr(RED, 'gentrace') + ": " + get(conf,'gentrace_err').strip())
        # elif get(conf,'genpsim_err') is not None:
            # msg.append(cstr(RED, 'genpsim') + ": " + get(conf,'genpsim_err').strip())
        # elif get(conf,'psimcycle') is None:
            # msg.append(cstr(RED, 'runpsim'))
        # else:
            # msg.append(cstr(GREEN, 'psimcycle:{} lbw:{} sbw:{}'.format(get(conf,'psimcycle'], conf['lbw'], conf['sbw'))))
    
        if get(conf,'gentst_err') is not None:
            msg.append(cstr(RED, 'gentst') + ": "+ get(conf,'gentst_err').strip())
        elif get(conf,'gentst_time') is None:
            msg.append(cstr(YELLOW, 'gentst'))
        else:
            msg.append(cstr(GREEN, 'gentst'))
        if get(conf,'gentst_time') is not None:
            msg.append('[{}s]'.format(round(get(conf,'gentst_time')),2))
    
        if get(conf,'maketst_err') is not None:
            msg.append(cstr(RED, 'make') + ": " + get(conf,'maketst_err').strip())
        elif get(conf,'maketst_time') is None:
            msg.append(cstr(YELLOW, 'make'))
        else:
            msg.append(cstr(GREEN, 'make'))
        if get(conf,'maketst_time') is not None:
            msg.append('[{}]'.format(get(conf,'maketst_time')))
    
        def printtst(p):
            color = None
            if get(conf,p + '_deadlock'):
                color = RED
                msg.append(cstr(color, p.replace('run','') + ': DEADLOCK'))
            elif get(conf,p + '_err') is not None:
                color = RED
                msg.append(cstr(color, p.replace('run','') + '') + ": " + get(conf,p + '_err').strip())
            elif get(conf,p + '_pass') is not None and not get(conf,p + '_pass'):
                color = RED
                msg.append(cstr(color, p.replace('run','') + ' PASS:false'))
            elif not get(conf,p + '_complete'):
                color = YELLOW
                msg.append(cstr(color, p.replace('run','') + ''))
            else:
                color = GREEN
                msg.append(cstr(GREEN, p.replace('run','') + ''))
            if get(conf,p + '_cycle') is not None and color != RED:
                color = GREEN
            if get(conf,p + '_cycle') is not None:
                msg.append('cycle:{}'.format(get(conf,p + '_cycle')))
            if get(conf,p + '_time') is not None:
                msg.append('[{}]'.format(get(conf,p + '_time')))
    
        printtst('runp2p')
    
        if get(conf,'runproute_err') is not None:
            msg.append(cstr(RED, 'proute') + ": "+ get(conf,'runproute_err').strip())
        elif get(conf,'runproute_time') is None:
            msg.append(cstr(YELLOW, 'proute'))
        else:
            msg.append(cstr(GREEN,'proute') + ' vc:{}'.format(get(conf,'NetVC')))
        if get(conf,'runproute_time') is not None:
            msg.append('[{}]'.format(get(conf,'runproute_time')))
    
        printtst('runhybrid')

        # if isHistory:
            # pir_sha = get(conf,'pir_sha')
            # pirmsg = get_sha_msg(pir_sha, opts.pir_dir)
            # msg.append(conf['spatial_sha'])
            # msg.append(pir_sha)
            # msg.append(pirmsg)
            # msg.append(conf['time'])

        return ' '.join([str(m) for m in msg])

    def get_summary_path(self, conf):
        time = conf['time']
        timeshort = time[2:].replace("-","").replace(" ","_").replace(":","")
        summary_path = "{}/{}_{}_{}_{}_{}.csv".format(
            self.opts.logdir, 
            timeshort, 
            conf['backend'],
            conf['project'],
            conf['pir_sha'],
            conf['spatial_sha']
            )
        return summary_path

    def summarize(self, backend, confs):
        opts = self.opts
        if not opts.summarize: return
        if len(confs) == 0: return
        summary_path = self.get_summary_path(confs[0])
        # create new csv
        conf = confs[0]
        keys = set([])
        for conf in confs:
            keys.update(conf.keys())
        with open(summary_path, "w") as f:
            summary = csv.DictWriter(f, delimiter=',', fieldnames=keys)
            summary.writeheader()
            for conf in confs:
                summary.writerow(conf)
        print('Generate summary in {}'.format(summary_path))

    def parse(self, conf):
        opts = self.opts
        app = conf['app']
        backend = conf['backend']
        if 'Tst' in backend:
            backend = backend + "_" + conf['project']
        conf["freq"] = 1e9
        conf['spatial_sha'] = self.spatial_sha
        conf['pir_sha'] = self.pir_sha
        conf['time'] = self.pir_time
        self.appdir = os.path.join(opts.gendir,backend,app)
        self.runproute = os.path.join(self.appdir,"log/runproute.log")
        self.proutesh = os.path.join(self.appdir,"proute.sh")
        self.prouteSummary = os.path.join(self.appdir,"plastisim","summary.csv")
        self.AccelMain = os.path.join(self.appdir,"pir","AccelMain.scala")
        self.logpath = os.path.join(self.appdir,"log/")
        self.gentst = os.path.join(self.appdir,"log/gentst.log")
        self.resreport = os.path.join(self.appdir,"pir/out/resource.csv")
        self.progreport = os.path.join(self.appdir,"pir/out/program.json")
        self.progreport_alloc = os.path.join(self.appdir,"pir/out/program_alloc.json")
        self.progreport_split = os.path.join(self.appdir,"pir/out/program_split.json")
        self.maketst = os.path.join(self.appdir,"log/maketst.log")
        self.runp2p = os.path.join(self.appdir,"log/runp2p.log")
        self.runhybrid = os.path.join(self.appdir,"log/runhybrid.log")
        self.p2pstat = os.path.join(self.appdir,"log/p2pstat.log")
        self.simstat = os.path.join(self.appdir,"tungsten/logs/state.json")
        self.pirconf = os.path.join(self.appdir,"pir/out/config.csv")
        parse_genpir(self.AccelMain, self.logpath, conf, opts)
        parse_proutesummary(self.prouteSummary, conf, opts)

        parseLog(
            conf,
            'cycle', 
            'Simulation complete at cycle ',
            lambda lines: int(lines[0].split('Simulation complete at cycle ')[1].split(" ")[0]),
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'err', 
            ["[bug]", "error", "Caught exception", "fail", "Exception", "fault", 
                "terminated by signal", "Command exited with non-zero status"],
            lambda lines: lines[0],
            logs=[self.runp2p, self.runhybrid, self.maketst, self.runproute, self.gentst]
        )
        parseLog(
            conf,
            'pass', 
            ["PASS: "],
            lambda lines: bool(lines[0].split('PASS: ')[1].split(" (")[0]),
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'deadlock', 
            'DEADLOCK',
             lambda lines: True,
            default=False,
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'complete', 
            'Complete Simulation',
             lambda lines: True,
            default=False,
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'dram_power', 
            'Average DRAM Power',
            lambda lines: float(lines[0].split(':')[1].split("W")[0]),
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'rbw', 
            'Average DRAM Read Bandwidth: ',
            lambda lines: float(lines[0].split(':')[1].split("GB/s")[0]),
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'wbw', 
            'Average DRAM Write Bandwidth: ',
            lambda lines: float(lines[0].split(':')[1].split("GB/s")[0]),
            logs=[self.runp2p, self.runhybrid]
        )
        parseLog(
            conf,
            'time', 
            ["Runtime:"],
            lambda lines: lines[0].split("Runtime:")[1].strip(),
            logs=[self.maketst, self.runp2p, self.runhybrid, self.runproute],
            prefix=True,
        )
        parseLog(
            conf,
            'time', 
            ["Compilation failed in", "Compilation succeed in"],
            lambda lines: float(lines[0].split("in ")[1].split("s")[0].strip()),
            logs=[self.gentst],
            prefix=True,
        )
        
        for key in ["All", "FU", "Fix", "Flt", "Other", "Prog FU"]:
            parseLog(
                conf,
                'OPS-{}'.format(key), 
                key,
                lambda lines: lines[0].split(": ")[1].strip(),
                logs=[self.p2pstat],
                prefix=False,
            )
        
        parseLog(
            conf,
            'notFit', 
            'Not enough resource of type',
            lambda lines: lines[0],
            logs=[self.gentst],
        )

        pattern = ["MC", "DAG", "PMU", "PCU"]
        for pat in pattern:
            parseLog(
                conf,
                pat, 
                pat,
                lambda lines, pat=pat: int(lines[0].split(pat+",")[1].split(",")[0].strip()),
                logs=[self.resreport],
            )

        pattern = ["MC", "DAG", "PMU", "PCU"]
        for pat in pattern:
            parseLog(
                conf,
                pat+"_Total", 
                pat,
                lambda lines, pat=pat: int(lines[0].split(",")[2].strip()),
                logs=[self.resreport],
            )

        parseProgReport(self.progreport, conf, "", opts)
        parseProgReport(self.progreport_alloc, conf, "-alloc", opts)
        parseProgReport(self.progreport_split, conf, "-split", opts)

        parseLog(
            conf,
            'row', 
            '--row=',
            lambda lines: int(lines[-1].split("--row=")[-1].split(",")[0]),
            logs=[self.gentst],
        )
        
        parseLog(
            conf,
            'col', 
            '--col=',
            lambda lines: int(lines[-1].split("--col=")[-1].split(",")[0].split("]")[0]),
            logs=[self.gentst],
        )
        parseLog(
            conf,
            'pirArgs', 
            'args=[',
            lambda lines: lines[-1].split("args=[")[-1].split("]")[0],
            logs=[self.gentst],
        )
        parseLog(
            conf,
            'mergeTime_ms', 
            'GlobalMerger in',
             lambda lines: float(lines[0].split("in ")[1].split("ms")[0].strip()),
            logs=[self.gentst],
        )
        
        parseLog(
            conf,
            'algo', 
            'plastiroute',
            lambda lines: lines[0].split("-a ")[1].split(" ")[0],
            logs=[self.proutesh],
        )
        parseLog(
            conf,
            'pattern', 
            'plastiroute',
             lambda lines: lines[0].split("-T ")[1].split(" ")[0],
            logs=[self.proutesh],
        )
        parseLog(
            conf,
            'slink', 
            'plastiroute',
             lambda lines: int(lines[0].split("-e ")[1].split(" ")[0]),
            logs=[self.proutesh],
        )
        parseLog(
            conf,
            'vlink', 
            'plastiroute',
             lambda lines: int(lines[0].split("-x ")[1].split(" ")[0]),
            logs=[self.proutesh],
        )
        parseLog(
            conf,
            'prtime', 
            '-S',
             lambda lines: int(lines[0].split("-S ")[1].split(" ")[0]),
            logs=[self.proutesh],
        )
        parseLog(
            conf,
            'vcLimit', 
            'plastiroute',
             lambda lines: int(lines[0].split("-q")[1].split("-")[0].strip()),
            logs=[self.proutesh],
        )
        parsePirConf(self.pirconf, conf, opts)

        parseSimState(self.simstat, conf, opts)
        
        conf['succeeded'] = parse_success(conf)
        derive_simfreq(conf, opts)
        return conf

def main():
    Logger()

