import os
import csv
import shutil
from collections import OrderedDict
import pandas as pd
from pandautil import *
import numpy as np
import math
import fnmatch

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
parser.add_argument('--logdir', default="{}/spatial/pir/logs/".format(os.environ['HOME']))
parser.add_argument('--spatial_dir', default="{}/spatial/".format(os.environ['HOME']))
parser.add_argument('--pir_dir', default="{}/spatial/pir".format(os.environ['HOME']))
parser.add_argument('-f', '--filter', dest='filter_str', action='append', help='Filter apps')
parser.add_argument('-m', '--message', default='', help='Additional fields to print in message')
parser.add_argument('-pf', '--print_fields', default=False, action='store_true', help='Print fields')

def to_conf(tab, **kws):
    tab = lookup(tab, drop=False, **kws)
    conf = tab.to_dict()
    for k in conf:
        if type(conf[k]) in [float, np.float64] and math.isnan(conf[k]):
            conf[k] = None
    return conf

def load_history(opts):
    logs = os.listdir(opts.logdir)
    logs = sorted(logs, reverse = True)[:20]
    # print(logs)

    history = None
    for log in logs:
        tab = pd.read_csv(opts.logdir + log, header=0)
        if history is None:
            history = tab
        else:
            history = pd.concat([history, tab], axis=0, sort=False)  

    if history is None:
        print("No history to compare with")
    else:
        opts.history = history

def summarize(backend, opts, confs):
    if not opts.summarize: return
    spatial_sha = confs[0]['spatial_sha']
    time = confs[0]['time']
    timeshort = time[2:].replace("-","").replace(" ","_").replace(":","")
    summary_path = "{}/{}_{}_{}_{}.csv".format(opts.logdir, timeshort, backend, opts.project,
            spatial_sha)
    # create new csv
    conf = confs[0]
    with open(summary_path, "w") as f:
        summary = csv.DictWriter(f, delimiter=',', fieldnames=conf.keys())
        summary.writeheader()
        for conf in confs:
            summary.writerow(conf)
    print('Generate summary in {}'.format(summary_path))

def getMessage(conf, opts):
    msg = []
    msg.append(conf['backend'])
    msg.append(conf['app'])
    succeeded = False

    for f in opts.message.split(","):
        if f in conf:
            msg.append(cstr(CYAN, f + ':' + str(get(conf,f))))
        # else:
            # print("{} not in config. options: {}".format(f, ','.join([k for k in conf])))

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

    # if len(opts.filter) > 0:
        # msg = [get(conf,'app')]

    return ' '.join(msg)

def get(conf,key):
    if key not in conf: return None
    return conf[key]

def removeRules(conf, opts):
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
        # reruns.append('gentst')
        # reruns.append('maketst')
        # reruns.append('runtst')
    # if not conf['succeeded']:
        # reruns.append('genpir')
        # reruns.append('gentst')
        # reruns.append('maketst')
        # reruns.append('runtst')

    for p in reruns:
        if p == 'genpir':
            remove(conf['AccelMain'], opts)
        elif p == 'runpsim':
            remove(conf['gentrace'], opts)
            remove(conf['genpsim'], opts)
            remove(conf['runpsim'], opts)
        else:
            remove(conf[p], opts)
    return reruns

def print_message(conf, opts):
    msg = getMessage(conf, opts)
    if not opts.summarize and not opts.show_diff:
        print(msg)

    if not opts.show_diff:
        return

    if opts.history is None:
        print("No history to compare with")
        return

    diffkey = 'succeeded'
    # diffkey = 'genpir'

    history = opts.history

    history = history[(history.app==conf['app']) & (history.project == conf['project']) & \
            (history.backend==conf['backend'])]
    prevsucc =  history[history[diffkey]]

    if opts.show_diff:
        if not conf[diffkey] and prevsucc.shape[0] > 0:
            times = get_col(prevsucc, 'time')
            pconf = to_conf(prevsucc.iloc[np.argmax(times), :])
            print('{} {}'.format(msg, cstr(RED,'(Regression)')))
            print('{} {} {} {}'.format(getMessage(pconf, opts), pconf['spatial_sha'], 
                get(pconf,'pir_sha'), pconf['time']))
        if conf[diffkey] and prevsucc.shape[0] == 0:
            print('{} {}'.format(msg, cstr(GREEN,'(New)')))

def logApp(conf, opts):
    print_message(conf, opts)
    reruns = removeRules(conf, opts)
    if opts.show_app:
        # tail(conf['runpir'])
        # tail(conf['mappir'])
        # tail(conf['gentrace'])
        # tail(conf['genpsim'])
        # tail(conf['runpsim'])
        tail(conf['gentst'])
        tail(conf['runproute'])
        tail(conf['maketst'])
        tail(conf['runp2p'])
        tail(conf['runhybrid'])

rules = []
def addRule(func):
    rules.append(func) 

def parse(conf, opts):
    app = conf['app']
    backend = conf['backend']
    if 'Tst' in backend:
        backend = backend + "_" + conf['project']
    conf["freq"] = 1e9
    conf['runproute'] = os.path.join(opts.gendir,backend,app,"log/runproute.log")
    conf['proutesh'] = os.path.join(opts.gendir,backend,app,"log/runproute.sh")
    conf['prouteSummary'] = os.path.join(opts.gendir,backend,app,"plastisim","summary.csv")
    conf['AccelMain'] = os.path.join(opts.gendir,backend,app,"pir","AccelMain.scala")
    conf['logpath'] = os.path.join(opts.gendir,backend,app,"log/")
    conf['gentst'] = os.path.join(opts.gendir,backend,app,"log/gentst.log")
    conf['resreport'] = os.path.join(opts.gendir,backend,app,"pir/out/resource.csv")
    conf['maketst'] = os.path.join(opts.gendir,backend,app,"log/maketst.log")
    conf['runp2p'] = os.path.join(opts.gendir,backend,app,"log/runp2p.log")
    conf['runhybrid'] = os.path.join(opts.gendir,backend,app,"log/runhybrid.log")
    conf['p2pstat'] = os.path.join(opts.gendir,backend,app,"log/p2pstat.log")
    parse_genpir(conf['AccelMain'], conf, opts)
    parseLog('gentst', conf)
    parseLog('resreport', conf)
    parseLog('proutesh', conf)
    parseLog('runproute', conf)
    parse_proutesummary(conf['prouteSummary'], conf, opts)
    parseLog('maketst', conf)
    parseLog('runp2p', conf)
    parseLog('runhybrid', conf)
    parseLog('p2pstat', conf)
    conf['succeeded'] = parse_success(conf)
    for rule in rules:
        rule(conf, opts)
    return conf

def parse_success(conf):
    for p in ['runp2p', 'runhybrid']:
        conf[p+'_success'] = \
            conf[p+'_complete'] and \
            not conf[p+'_deadlock'] and \
            conf[p+'_err'] is None
    return conf['runp2p_success']

def parse_genpir(pirsrc, conf, opts):
    if os.path.exists(pirsrc):
        conf['genpir'] = True
        conf['genpir_err'] = None
    else:
        conf['genpir'] = False
        match = grep("{}/00*".format(conf['logpath']),
                ["error", "exception", "Exception"])
        lines = [line for pat in match for line in match[pat]]

        match = grep("{}/*_exception.log".format(conf['logpath']),
                ["error", "exception", "Exception"])
        lines = lines + [line for pat in match for line in match[pat]]
        if len(lines) != 0:
            conf['genpir_err'] = lines[0].replace("\n","")
        else:
            conf['genpir_err'] = None

Parser(
    'cycle', 
    'Simulation complete at cycle:',
    lambda lines,conf: int(lines[0].split('Simulation complete at cycle:')[1]),
    logs=['runpsim']
)
Parser(
    'lbw', 
    'Total DRAM:',
    lambda lines,conf: float(lines[0].split("(")[1].split("GB/s R")[0].strip()),
    logs=['runpsim']
)
Parser(
    'sbw', 
    'Total DRAM:',
    lambda lines,conf: float(lines[0].split(",")[1].split("GB/s W")[0].strip()),
    logs=['runpsim']
)
Parser(
    'deadlock', 
    'POSSIBLE DEADLOCK',
    lambda lines,conf: True,
    default=False,
    logs=['runpsim']
)

Parser(
    'cycle', 
    'Simulation complete at cycle ',
    lambda lines,conf: int(lines[0].split('Simulation complete at cycle ')[1].split(" ")[0]),
    logs=['runp2p', 'runhybrid']
)
Parser(
    'err', 
    ["[bug]", "error", "fail", "exception", "Exception", "fault", "terminated by signal"],
    lambda lines,conf: lines[0],
    logs=['runp2p', 'runhybrid', 'maketst', 'runproute', 'gentst']
)
Parser(
    'pass', 
    ["PASS: "],
    lambda lines,conf: bool(lines[0].split('PASS: ')[1].split(" (")[0]),
    logs=['runp2p', 'runhybrid']
)
Parser(
    'deadlock', 
    'DEADLOCK',
     lambda lines,conf: True,
    default=False,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'complete', 
    'Complete Simulation',
     lambda lines,conf: True,
    default=False,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'dram_power', 
    'Average DRAM Power',
    lambda lines,conf: float(lines[0].split(':')[1].split("W")[0]),
    logs=['runp2p', 'runhybrid']
)
Parser(
    'rbw', 
    'Average DRAM Read Bandwidth: ',
    lambda lines,conf: float(lines[0].split(':')[1].split("GB/s")[0]),
    logs=['runp2p', 'runhybrid']
)
Parser(
    'wbw', 
    'Average DRAM Write Bandwidth: ',
    lambda lines,conf: float(lines[0].split(':')[1].split("GB/s")[0]),
    logs=['runp2p', 'runhybrid']
)
Parser(
    'time', 
    ["Runtime:"],
    lambda lines,conf: lines[0].split("Runtime:")[1].strip(),
    logs=['maketst', 'runp2p', 'runhybrid', 'runproute'],
    prefix=True,
)
Parser(
    'time', 
    ["Compilation failed in", "Compilation succeed in"],
    lambda lines,conf: float(lines[0].split("in ")[1].split("s")[0].strip()),
    logs=['gentst'],
    prefix=True,
)

for key in ["All", "FU", "Fix", "Flt", "Other", "Prog FU"]:
    Parser(
        'OPS-{}'.format(key), 
        key,
        lambda lines,conf: lines[0].split(": ")[1].strip(),
        logs=['p2pstat'],
        prefix=False,
    )

Parser(
    'notFit', 
    'Not enough resource of type',
    lambda lines,conf: lines[0],
    logs=['gentst'],
)
pattern = ["MC", "DAG", "PMU", "PCU"]
for pat in pattern:
    Parser(
        pat, 
        pat,
        lambda lines,conf, pat=pat: int(lines[0].split(pat+",")[1].split(",")[0].strip()),
        logs=['resreport'],
    )
pattern = ["MC", "DAG", "PMU", "PCU"]
for pat in pattern:
    Parser(
        pat+"_Total", 
        pat,
        lambda lines,conf, pat=pat: int(lines[0].split(",")[2].strip()),
        logs=['resreport'],
    )
Parser(
    'row', 
    '--row=',
    lambda lines,conf: int(lines[-1].split("--row=")[-1].split(",")[0]),
    logs=['gentst'],
)

Parser(
    'col', 
    '--col=',
    lambda lines,conf: int(lines[-1].split("--col=")[-1].split(",")[0].split("]")[0]),
    logs=['gentst'],
)

Parser(
    'algo', 
    '',
    lambda lines,conf: lines[0].split("-a ")[1].split(" ")[0],
    logs=['proutesh'],
)
Parser(
    'pattern', 
    '',
     lambda lines,conf: lines[0].split("-T ")[1].split(" ")[0],
    logs=['proutesh'],
)
Parser(
    'slink', 
    '',
     lambda lines,conf: int(lines[0].split("-e ")[1].split(" ")[0]),
    logs=['proutesh'],
)
Parser(
    'vlink', 
    '',
     lambda lines,conf: int(lines[0].split("-x ")[1].split(" ")[0]),
    logs=['proutesh'],
)
Parser(
    'prtime', 
    '',
     lambda lines,conf: int(lines[0].split("-S ")[1].split(" ")[0]),
    logs=['proutesh'],
)
Parser(
    'vcLimit', 
    '',
     lambda lines,conf: int(lines[0].split("-q")[1].split("-")[0].strip()),
    logs=['proutesh'],
)

Parser(
    'link_prop', 
    '',
    lambda lines,conf: lines[0].split("-l ")[1].split(" ")[0],
    logs=['psimsh'],
)
Parser(
    'flit_data_width', 
    '-i',
    lambda lines,conf: lines[0].split("-i")[1].split(" ")[0],
    default=512,
    logs=['psimsh'],
)

def parse_proutesummary(log, conf, opts):
    conf["DynHopsVec"] = None
    conf["DynHopsScal"] = None
    conf["StatHopsVec"] = None
    conf["StatHopsScal"] = None
    conf["Score"] = None
    conf["NetVC"] = None
    conf["TotPkts"] = None
    conf["LinkLim"] = None
    conf["InjectLim"] = None
    conf["EjectLim"] = None
    conf["LongRoute"] = None
    conf["Q0Pct"] = None
    conf["Q0Lim"] = None
    if not os.path.exists(log): return
    with open(log, "r") as f:
        reader = csv.DictReader(f)
        for row in reader:
            for k in row:
                if k in conf:
                    conf[k] = row[k]

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

def show_history(opts):
    if not opts.show_history: return

    history = opts.history

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

    history = history.groupby(["project", "app", "backend"]).apply(lambda x:
            x.sort_values(["time"]).tail(opts.history_depth))

    if history.shape[0] > 0:
        for idx, row in history.iterrows():
            pconf = to_conf(row)
            pir_sha = get(pconf,'pir_sha')
            if pir_sha is not None:
                pirmsg = subprocess.check_output("git log --format=%B -n 1 {}".format(pir_sha).split(" "),
                cwd=opts.pir_dir).replace("'","").replace("\n","")
            else:
                pirmsg = ""
            print('{} {} {} {} {}'.format(getMessage(pconf, opts), pconf['spatial_sha'], 
                pir_sha, pconf['time'], pirmsg))

def show_gen(opts):
    gitmsg = subprocess.check_output("git log --pretty=format:'%h' -n 1".split(" "),
            cwd=opts.spatial_dir).replace("'","")
    spatial_sha = gitmsg.split(",")[0]
    gitmsg = subprocess.check_output("git log --pretty=format:'%h,%ad' -n 1 --date=iso".split(" "),
            cwd=opts.pir_dir).replace("'","")
    pir_sha = gitmsg.split(",")[0]
    time = gitmsg.split(",")[1].split(" -")[0].strip()
    for backend in opts.backend:
        numRun = 0
        numSucc = 0
        if 'Tst' in backend:
            apps = getApps(backend + "_" + opts.project, opts)
        else:
            apps = getApps(backend, opts)
        confs = []
        opts.show_app = len(apps)==1 and not opts.summarize and not opts.show_history
        for app in apps:
            conf = OrderedDict()
            conf['spatial_sha'] = spatial_sha
            conf['pir_sha'] = pir_sha
            conf['time'] = time
            conf['app'] = app
            conf['project'] = opts.project
            conf['backend'] = backend
            parse(conf, opts)
            matched = applyFilter(conf, opts)
            if not matched: continue
            logApp(conf, opts)
            confs.append(conf)
            numRun += 1
            if conf['succeeded']: numSucc += 1
        summarize(backend, opts, confs)
        if numRun != 0:
            print('Succeeded {} / {} ({:0.2f}) %'.format(numSucc, numRun, numSucc*100.0/numRun))

def applyFilter(conf, opts):
    return all([f(conf) for f in opts.filter]);
    # matched = False
    # if len(opts.filter) > 0:
        # for f in opts.filter:
            # if f(conf):
                # matched = True
    # else:
        # matched = True
    # return matched

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

def main(args=None):
    (opts, args) = parser.parse_known_args(args=args)
    opts.show_history = opts.history_depth > 0

    if opts.show_diff or opts.show_history:
        load_history(opts)
    if opts.print_fields:
        fields = sorted(opts.history.columns.values)
        for f in fields:
            print(f)
        return
    if opts.show_history:
        show_history(opts)
        return

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

    for i in range(len(opts.backend)):
        if 'Tst' in opts.backend[i]:
            opts.project = backend.split("_")[1]
            opts.backend[i] = backend.split("_")[0]

    setFilterRules(opts)
    show_gen(opts)

