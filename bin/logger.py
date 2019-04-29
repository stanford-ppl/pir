import os
import csv
import shutil
from collections import OrderedDict
import pandas as pd
from pandautil import *
import numpy as np
import math

from util import *

parser.add_argument('path', type=str, help='Path to parsing directory')
parser.add_argument('-A', '--isApp', dest="path_type", action='store_const', const='app',
        default='backend')
parser.add_argument('-B', '--isBackend', dest="path_type", action='store_const', const='backend', default='backend')
parser.add_argument('-G', '--isGen', dest="path_type", action='store_const', const='gen',
        default='backend')
parser.add_argument('-s', '--summarize', action='store_true', default=False, help='summarize log into csv')
parser.add_argument('-d', '--diff', dest='show_diff', action='store_true', default=False, help='showing difference')
parser.add_argument('--logdir', default="{}/spatial/pir/logs/".format(os.environ['HOME']))

def to_conf(tab, **kws):
    tab = lookup(tab, drop=False, **kws)
    conf = tab.to_dict()
    for k in conf:
        if type(conf[k]) in [float, np.float64] and math.isnan(conf[k]):
            conf[k] = None
    return conf

def load_history(opts):
    if not opts.show_diff: return
    logs = os.listdir(opts.logdir)
    logs = logs[-10:]

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

def show_diff(conf, opts):
    msg = getMessage(conf, opts)
    if not opts.show_message: return

    if not opts.show_diff:
        print(msg)
        return

    if opts.history is None:
        print("No history to compare with")
        return

    history = opts.history
    prevsucc = history[(history.app==conf['app']) & history.succeeded]

    if not conf['succeeded'] and prevsucc.shape[0] > 0:
        times = get_col(prevsucc, 'time')
        pconf = to_conf(prevsucc.iloc[np.argmax(times), :])
        print('{} {}'.format(msg, cstr(RED,'(Regression)')))
        print('{} {} {}'.format(getMessage(pconf, opts), pconf['sha'], pconf['time']))
    if conf['succeeded'] and prevsucc.shape[0] == 0:
        print('{} {}'.format(msg, cstr(GREEN,'(New)')))

def summarize(backend, opts, confs):
    if not opts.summarize: return
    sha = confs[0]['sha']
    time = confs[0]['time']
    timeshort = time[2:].replace("-","").replace(" ","_").replace(":","")
    summary_path = "{}/{}_{}_{}_{}.csv".format(opts.logdir, timeshort, backend, opts.project, sha)
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

    if conf['genpir']:
        msg.append(cstr(GREEN, 'genpir'))
    else:
        msg.append(cstr(RED, 'genpir'))

    if conf['runpir_err'] is not None:
        msg.append(cstr(RED,'runpir'))
        msg.append(conf['runpir_err'].strip())
    elif conf['runpir_time'] is None:
        msg.append(cstr(YELLOW,'runpir'))
    else:
        msg.append(cstr(GREEN,'runpir[{:.2f}s]'.format(conf['runpir_time'])))

    if conf['mappir_err'] is not None:
        msg.append(cstr(RED,'mappir'))
        msg.append(conf['mappir_err'].strip())
    elif conf['notFit'] is not None:
        msg.append(cstr(YELLOW, 'notFit'))
    elif conf['mappir_time'] is None:
        msg.append(cstr(YELLOW,'mappir'))
    elif conf['PCU'] is not None:
        msg.append(cstr(GREEN, "mappir[{:.1f}s]".format(conf['mappir_time'])))
        msg.append(cstr(GREEN,' '.join(['{}:{}'.format(k,conf[k]) for k in ['PCU', "PMU"]])))
    else:
        msg.append(cstr(GREEN,'mappir[{:.1f}s]'.format(conf['mappir_time'])))

    if conf['NetVC'] is None:
        msg.append(cstr(RED,'runproute'))
    else:
        msg.append(cstr(GREEN,'vc:{}'.format(conf['NetVC'])))

    if conf['psim_deadlock']:
        msg.append(cstr(RED, 'DEADLOCK'))
    elif conf['gentrace_err'] is not None:
        msg.append(cstr(RED, 'gentrace') + ": " + conf['gentrace_err'].strip())
    elif conf['genpsim_err'] is not None:
        msg.append(cstr(RED, 'genpsim') + ": " + conf['genpsim_err'].strip())
    elif conf['psimcycle'] is None:
        msg.append(cstr(RED, 'runpsim'))
    else:
        msg.append(cstr(GREEN, 'psimcycle:{} lbw:{} sbw:{}'.format(conf['psimcycle'], conf['lbw'], conf['sbw'])))

    if conf['gentst_err'] is not None:
        msg.append(cstr(RED, 'gentst') + ": "+ conf['gentst_err'].strip())
    elif conf['maketst_err'] is not None:
        msg.append(cstr(RED, 'maketst') + ": " + conf['maketst_err'].strip())
    elif conf['tst_deadlock']:
        msg.append(cstr(RED, 'runtst: DEADLOCK'))
    elif conf['runtst_err'] is not None:
        msg.append(cstr(RED, 'runtst') + ": " + conf['runtst_err'].strip())
    elif conf['tstcycle'] is None:
        msg.append(cstr(RED, 'runtst'))
    elif conf['runtst_pass'] is None and conf['tstcycle'] is not None:
        msg.append(cstr(GREEN, 'tstcycle:{}'.format(conf['tstcycle'])))
        succeeded = True
    elif not conf['runtst_pass']:
        msg.append(cstr(RED, 'tstcycle:{} PASS:false'.format(conf['tstcycle'])))
    else:
        msg.append(cstr(GREEN, 'tstcycle:{} PASS:true'.format(conf['tstcycle'])))
        succeeded = True

    conf['succeeded'] = succeeded

    return ' '.join(msg)

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
    # if not con['succeeded']:
        # reruns.append('genpir')
        # reruns.append('gentst')
        # reruns.append('maketst')
        # reruns.append('runtst')

    for p in reruns:
        if p == 'genpir':
            remove(conf['AccelMain'], opts)
        elif p == 'runproute':
            remove(conf['prouteSummary'], opts)
        # elif p == 'runtst':
            # remove(conf['gentstlog'], opts)
            # remove(conf['maketstlog'], opts)
            # remove(conf['runtstlog'], opts)
        elif p == 'runpsim':
            remove(conf['gentracelog'], opts)
            remove(conf['genpsimlog'], opts)
            remove(conf['runpsimlog'], opts)
        else:
            remove(conf[p + 'log'], opts)
    return reruns

def logApp(conf, opts):
    parse(conf, opts)
    show_diff(conf, opts)
    reruns = removeRules(conf, opts)
    if opts.show_app:
        tail(conf['runpirlog'])
        tail(conf['mappirlog'])
        tail(conf['runproutelog'])
        tail(conf['gentracelog'])
        tail(conf['genpsimlog'])
        tail(conf['runpsimlog'])
        tail(conf['gentstlog'])
        tail(conf['maketstlog'])
        tail(conf['runtstlog'])

def parse(conf, opts):
    app = conf['app']
    backend = conf['backend']
    if backend == 'Tst':
        backend = backend + "_" + conf['project']
    conf["freq"] = 1e9
    conf['mappirlog'] = os.path.join(opts.gendir,backend,app,"log/mappir.log")
    conf['runpirlog'] = os.path.join(opts.gendir,backend,app,"log/runpir.log")
    conf['genpsimlog'] = os.path.join(opts.gendir,backend,app,"log/genpsim.log")
    conf['gentracelog'] = os.path.join(opts.gendir,backend,app,"log/gentrace.log")
    conf['runpsimlog'] = os.path.join(opts.gendir,backend,app,"log/runpsim.log")
    conf['psimsh'] = os.path.join(opts.gendir,backend,app,"log/runpsim.sh")
    conf['runproutelog'] = os.path.join(opts.gendir,backend,app,"log/runproute.log")
    conf['proutesh'] = os.path.join(opts.gendir,backend,app,"log/runproute.sh")
    conf['prouteSummary'] = os.path.join(opts.gendir,backend,app,"plastisim","summary.csv")
    conf['AccelMain'] = os.path.join(opts.gendir,backend,app,"pir","AccelMain.scala")
    conf['gentstlog'] = os.path.join(opts.gendir,backend,app,"log/gentst.log")
    conf['maketstlog'] = os.path.join(opts.gendir,backend,app,"log/maketst.log")
    conf['runtstlog'] = os.path.join(opts.gendir,backend,app,"log/runtst.log")
    parse_genpir(conf['AccelMain'], conf, opts)
    parse_runpir(conf['runpirlog'], conf, opts)
    parse_mappir(conf['mappirlog'], conf, opts)
    parse_genpsim(conf['genpsimlog'], conf, opts)
    parse_gentrace(conf['gentracelog'], conf, opts)
    parse_runpsim(conf['runpsimlog'], conf, opts)
    parse_runpsimsh(conf['psimsh'], conf, opts)
    parse_runproutesh(conf['proutesh'], conf, opts)
    parse_proutesummary(conf['prouteSummary'], conf, opts)
    parse_gentst(conf['gentstlog'], conf, opts)
    parse_maketst(conf['maketstlog'], conf, opts)
    parse_runtst(conf['runtstlog'], conf, opts)
    return conf

def parse_genpir(log, conf, opts):
    if os.path.exists(log):
        conf['genpir'] = True
    else:
        conf['genpir'] = False

def parse_runpsim(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'psimcycle', 
        'Simulation complete at cycle:',
        lambda lines: int(lines[0].split('Simulation complete at cycle:')[1])
    ))
    parsers.append(Parser(
        conf,
        'lbw', 
        'Total DRAM:',
         lambda lines: float(lines[0].split("(")[1].split("GB/s R")[0].strip())
    ))
    parsers.append(Parser(
        conf,
        'sbw', 
        'Total DRAM:',
         lambda lines: float(lines[0].split(",")[1].split("GB/s W")[0].strip())
    ))
    parsers.append(Parser(
        conf,
        'psim_deadlock', 
        'POSSIBLE DEADLOCK',
         lambda lines: True,
         default=False
    ))
    parseLog(log, parsers, conf)

def parse_runtst(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'tstcycle', 
        'Simulation complete at cycle ',
        lambda lines: int(lines[0].split('Simulation complete at cycle ')[1].split(" ")[0])
    ))
    parsers.append(Parser(
        conf,
        'runtst_err', 
        ["error", "fail", "exception"],
        lambda lines: lines[0] 
    ))
    parsers.append(Parser(
        conf,
        'runtst_pass', 
        ["PASS: "],
        lambda lines: bool(lines[0].split('PASS: ')[1].split(" (")[0])
    ))
    parsers.append(Parser(
        conf,
        'tst_deadlock', 
        'DEADLOCK',
         lambda lines: True,
         default=False
    ))
    parseLog(log, parsers, conf)

def parse_maketst(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'maketst_err', 
        ["error", "fail", "Exception"],
        lambda lines: lines[0] 
    ))
    parseLog(log, parsers, conf)

def parse_gentst(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'gentst_err', 
        ["error", "fail", "Exception"],
        lambda lines: lines[0] 
    ))
    parseLog(log, parsers, conf)

def parse_mappir(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'notFit', 
        'Not enough resource of type',
        lambda lines: lines[0]
    ))
    pattern = ["MC Usage:", "DAG Usage:", "PMU Usage:", "PCU Usage:"]
    for pat in pattern:
        key = pat.replace(" Usage:", "")
        parsers.append(Parser(
            conf,
            key, 
            pat,
            lambda lines, pat=pat: float(lines[0].split(pat)[1].split("%")[0].strip())
        ))
    parsers.append(Parser(
        conf,
        'row', 
        '--row=',
        lambda lines: int(lines[-1].split("--row=")[-1].split(",")[0])
    ))
    parsers.append(Parser(
        conf,
        'col', 
        '--col=',
        lambda lines: int(lines[-1].split("--col=")[-1].split(",")[0].split(']')[0])
    ))
    parsers.append(Parser(
        conf,
        'p2p', 
        '--net=',
        lambda lines: lines[-1].split("--net=")[-1].split(",")[0] == "p2p",
        default=False
    ))
    parsers.append(Parser(
        conf,
        'asic', 
        '--net=',
        lambda lines: lines[-1].split("--net=")[-1].split(",")[0].split(']')[0] == "asic",
        default=False
    ))
    parsers.append(Parser(
        conf,
        'scheduled', 
        '--scheduled',
        lambda lines: lines[-1].split("--scheduled=")[-1].split(",")[0].split(']')[0] == "true",
        default=False
    ))
    parsers.append(Parser(
        conf,
        'fifo_depth', 
        '--fifo-depth',
        lambda lines: int(lines[-1].split("--fifo-depth=")[-1].split(",")[0].split(']')[0]),
        default=100
    ))
    parsers.append(Parser(
        conf,
        'mappir_err', 
        ["error", "Exception"],
        lambda lines: lines[0] 
    ))
    parsers.append(Parser(
        conf,
        'mappir_time', 
        ["Compilation failed in", "Compilation succeed in"],
        lambda lines: float(lines[0].split("in ")[1].split("s")[0].strip())
    ))
    parseLog(log, parsers, conf)

def parse_runproutesh(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'algo', 
        '',
        lambda lines: lines[0].split("-a ")[1].split(" ")[0]
    ))
    parsers.append(Parser(
        conf,
        'pattern', 
        '',
         lambda lines: lines[0].split("-T ")[1].split(" ")[0]
    ))
    parsers.append(Parser(
        conf,
        'slink', 
        '',
         lambda lines: int(lines[0].split("-e ")[1].split(" ")[0])
    ))
    parsers.append(Parser(
        conf,
        'vlink', 
        '',
         lambda lines: int(lines[0].split("-x ")[1].split(" ")[0])
    ))
    parsers.append(Parser(
        conf,
        'prtime', 
        '',
         lambda lines: int(lines[0].split("-S ")[1].split(" ")[0])
    ))
    parsers.append(Parser(
        conf,
        'vcLimit', 
        '',
         lambda lines: int(lines[0].split("-q")[1].split("-")[0].strip())
    ))
    parseLog(log, parsers, conf)

def parse_runpsimsh(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'link_prop', 
        '',
        lambda lines: lines[0].split("-l ")[1].split(" ")[0]
    ))
    parsers.append(Parser(
        conf,
        'flit_data_width', 
        '-i',
        lambda lines: lines[0].split("-i")[1].split(" ")[0],
        default=512
    ))
    parseLog(log, parsers, conf)

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

def parse_runpir(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'runpir_time', 
        ["Compilation failed in", "Compilation succeed in"],
        lambda lines: float(lines[0].split("in ")[1].split("s")[0].strip())
    ))
    parsers.append(Parser(
        conf,
        'runpir_err', 
        ["error", "fail", "Exception"],
        lambda lines: lines[0] 
    ))
    parseLog(log, parsers, conf)

def parse_genpsim(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'genpsim_err', 
        ["error", "fail", "Exception"],
        lambda lines: lines[0] 
    ))
    parseLog(log, parsers, conf)

def parse_gentrace(log, conf, opts):
    parsers = []
    parsers.append(Parser(
        conf,
        'gentrace_err', 
        ["error", "fail", "Exception"],
        lambda lines: lines[0] 
    ))
    parseLog(log, parsers, conf)

def main():
    (opts, args) = parser.parse_known_args()
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

    opts.show_message = not opts.summarize 

    gitmsg = subprocess.check_output("git log --pretty=format:'%h,%ad' -n 1 --date=iso".split(" "),
            cwd=opts.logdir + '../../').replace("'","")
    sha = gitmsg.split(",")[0]
    time = gitmsg.split(",")[1].split(" -")[0].strip()

    load_history(opts)
    for backend in opts.backend:
        numRun = 0
        numSucc = 0
        apps = getApps(backend, opts)
        confs = []
        opts.show_app = len(apps)==1 and not opts.summarize
        if 'Tst' in backend:
            opts.project = backend.split("_")[1]
            backend = 'Tst'
        for app in apps:
            conf = OrderedDict()
            conf['sha'] = sha
            conf['time'] = time
            conf['app'] = app
            conf['project'] = opts.project
            conf['backend'] = backend
            logApp(conf, opts)
            confs.append(conf)
            numRun += 1
            if conf['succeeded']: numSucc += 1
        summarize(backend, opts, confs)
        if numRun != 0:
            print('Succeeded {} / {} ({:0.2f}) %'.format(numSucc, numRun, numSucc*100.0/numRun))


