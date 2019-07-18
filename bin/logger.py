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
parser.add_argument('-H', '--history', dest='show_history', action='store_true', default=False,
        help='showing history')
parser.add_argument('--logdir', default="{}/spatial/pir/logs/".format(os.environ['HOME']))
parser.add_argument('-f', '--filter', dest='filter_str', action='append', help='Filter apps')
parser.add_argument('-m', '--message', default='', help='Additional fields to print in message')

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

    if len(opts.filter) > 0:
        msg = [get(conf,'app')]

    for f in opts.message.split(","):
        if f in conf:
            msg.append(cstr(CYAN, f + ':' + str(get(conf,f))))


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
        elif p == 'runproute':
            remove(conf['prouteSummary'], opts)
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

    if not opts.show_history and not opts.show_diff:
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
    elif opts.show_history:
        history = prevsucc
        if history.shape[0] > 0:
            times = sorted(get_col(history, 'time'))
            history = history.sort_values('time')
            for idx, row in history.iterrows():
                pconf = to_conf(row)
                print('{} {} {} {}'.format(getMessage(pconf, opts), pconf['spatial_sha'], 
                    get(pconf,'pir_sha'), pconf['time']))
        print(msg)

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

def parse(conf, opts):
    app = conf['app']
    backend = conf['backend']
    if 'Tst' in backend:
        backend = backend + "_" + conf['project']
    conf["freq"] = 1e9
    # conf['mappir'] = os.path.join(opts.gendir,backend,app,"log/mappir.log")
    # conf['runpir'] = os.path.join(opts.gendir,backend,app,"log/runpir.log")
    # conf['genpsim'] = os.path.join(opts.gendir,backend,app,"log/genpsim.log")
    # conf['gentrace'] = os.path.join(opts.gendir,backend,app,"log/gentrace.log")
    # conf['runpsim'] = os.path.join(opts.gendir,backend,app,"log/runpsim.log")
    # conf['psimsh'] = os.path.join(opts.gendir,backend,app,"log/runpsim.sh")
    conf['runproute'] = os.path.join(opts.gendir,backend,app,"log/runproute.log")
    conf['proutesh'] = os.path.join(opts.gendir,backend,app,"log/runproute.sh")
    conf['prouteSummary'] = os.path.join(opts.gendir,backend,app,"plastisim","summary.csv")
    conf['AccelMain'] = os.path.join(opts.gendir,backend,app,"pir","AccelMain.scala")
    conf['logpath'] = os.path.join(opts.gendir,backend,app,"log/")
    conf['gentst'] = os.path.join(opts.gendir,backend,app,"log/gentst.log")
    conf['maketst'] = os.path.join(opts.gendir,backend,app,"log/maketst.log")
    conf['runp2p'] = os.path.join(opts.gendir,backend,app,"log/runp2p.log")
    conf['runhybrid'] = os.path.join(opts.gendir,backend,app,"log/runhybrid.log")
    parse_genpir(conf['AccelMain'], conf, opts)
    # parseLog('runpir', parsers, conf)
    # parseLog('mappir', parsers, conf)
    # parseLog('genpsim', parsers, conf)
    # parseLog('gentrace', parsers, conf)
    # parseLog('runpsim', parsers, conf)
    # parseLog('psimsh', parsers, conf)
    parseLog('gentst', parsers, conf)
    parseLog('proutesh', parsers, conf)
    parseLog('runproute', parsers, conf)
    parse_proutesummary(conf['prouteSummary'], conf, opts)
    parseLog('maketst', parsers, conf)
    parseLog('runp2p', parsers, conf)
    parseLog('runhybrid', parsers, conf)
    conf['succeeded'] = parse_success(conf)
    return conf

def parse_success(conf):
    # for p in ['runp2p', 'runhybrid']:
    for p in ['runp2p']:
        if conf[p+'_complete'] != True: return False
        if conf[p+'_deadlock']: return False
        if conf[p+'_err'] is not None: return False
    return True

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

parsers = []
Parser(
    'cycle', 
    'Simulation complete at cycle:',
    lambda lines: int(lines[0].split('Simulation complete at cycle:')[1]),
    parsers=parsers,
    logs=['runpsim']
)
Parser(
    'lbw', 
    'Total DRAM:',
    lambda lines: float(lines[0].split("(")[1].split("GB/s R")[0].strip()),
    parsers=parsers,
    logs=['runpsim']
)
Parser(
    'sbw', 
    'Total DRAM:',
    lambda lines: float(lines[0].split(",")[1].split("GB/s W")[0].strip()),
    parsers=parsers,
    logs=['runpsim']
)
Parser(
    'deadlock', 
    'POSSIBLE DEADLOCK',
    lambda lines: True,
    default=False,
    parsers=parsers,
    logs=['runpsim']
)

Parser(
    'cycle', 
    'Simulation complete at cycle ',
    lambda lines: int(lines[0].split('Simulation complete at cycle ')[1].split(" ")[0]),
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'err', 
    ["error", "fail", "exception", "Exception", "fault", "terminated by signal"],
    lambda lines: lines[0],
    parsers=parsers,
    logs=['runp2p', 'runhybrid', 'maketst', 'runproute', 'gentst']
)
Parser(
    'pass', 
    ["PASS: "],
    lambda lines: bool(lines[0].split('PASS: ')[1].split(" (")[0]),
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'deadlock', 
    'DEADLOCK',
     lambda lines: True,
    default=False,
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'complete', 
    'Complete Simulation',
     lambda lines: True,
    default=False,
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'dram_power', 
    'Average DRAM Power',
    lambda lines: float(lines[0].split(':')[1].split("W")[0]),
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'rbw', 
    'Average DRAM Read Bandwidth: ',
    lambda lines: float(lines[0].split(':')[1].split("GB/s")[0]),
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'wbw', 
    'Average DRAM Write Bandwidth: ',
    lambda lines: float(lines[0].split(':')[1].split("GB/s")[0]),
    parsers=parsers,
    logs=['runp2p', 'runhybrid']
)
Parser(
    'time', 
    ["Runtime:"],
    lambda lines: lines[0].split("Runtime:")[1].strip(),
    parsers=parsers,
    logs=['maketst', 'runp2p', 'runhybrid', 'runproute']
)
Parser(
    'time', 
    ["Compilation failed in", "Compilation succeed in"],
    lambda lines: float(lines[0].split("in ")[1].split("s")[0].strip()),
    parsers=parsers,
    logs=['gentst'],
)

Parser(
    'notFit', 
    'Not enough resource of type',
    lambda lines: lines[0],
    parsers=parsers,
    logs=['gentst'],
)
pattern = ["MC Usage:", "DAG Usage:", "PMU Usage:", "PCU Usage:"]
for pat in pattern:
    key = pat.replace(" Usage:", "")
    Parser(
        key, 
        pat,
        lambda lines, pat=pat: float(lines[0].split(pat)[1].split("%")[0].strip()),
        parsers=parsers,
        logs=['gentst'],
    )
Parser(
    'row', 
    '--row=',
    lambda lines: int(lines[-1].split("--row=")[-1].split(",")[0]),
    parsers=parsers,
    logs=['gentst'],
)

Parser(
    'col', 
    '--col=',
    lambda lines: int(lines[-1].split("--col=")[-1].split(",")[0].split("]")[0]),
    parsers=parsers,
    logs=['gentst'],
)

Parser(
    'algo', 
    '',
    lambda lines: lines[0].split("-a ")[1].split(" ")[0],
    parsers=parsers,
    logs=['proutesh'],
)
Parser(
    'pattern', 
    '',
     lambda lines: lines[0].split("-T ")[1].split(" ")[0],
    parsers=parsers,
    logs=['proutesh'],
)
Parser(
    'slink', 
    '',
     lambda lines: int(lines[0].split("-e ")[1].split(" ")[0]),
    parsers=parsers,
    logs=['proutesh'],
)
Parser(
    'vlink', 
    '',
     lambda lines: int(lines[0].split("-x ")[1].split(" ")[0]),
    parsers=parsers,
    logs=['proutesh'],
)
Parser(
    'prtime', 
    '',
     lambda lines: int(lines[0].split("-S ")[1].split(" ")[0]),
    parsers=parsers,
    logs=['proutesh'],
)
Parser(
    'vcLimit', 
    '',
     lambda lines: int(lines[0].split("-q")[1].split("-")[0].strip()),
    parsers=parsers,
    logs=['proutesh'],
)

Parser(
    'link_prop', 
    '',
    lambda lines: lines[0].split("-l ")[1].split(" ")[0],
    parsers=parsers,
    logs=['psimsh'],
)
Parser(
    'flit_data_width', 
    '-i',
    lambda lines: lines[0].split("-i")[1].split(" ")[0],
    default=512,
    parsers=parsers,
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

def show_gen(opts):
    gitmsg = subprocess.check_output("git log --pretty=format:'%h,%ad' -n 1 --date=iso".split(" "),
            cwd=opts.logdir + '../../').replace("'","")
    spatial_sha = gitmsg.split(",")[0]
    time = gitmsg.split(",")[1].split(" -")[0].strip()
    gitmsg = subprocess.check_output("git log --pretty=format:'%h' -n 1".split(" "),
            cwd=opts.logdir + '../').replace("'","")
    pir_sha = gitmsg.split(",")[0]
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
        if numRun != 0 and len(opts.filter) == 0:
            print('Succeeded {} / {} ({:0.2f}) %'.format(numSucc, numRun, numSucc*100.0/numRun))

def applyFilter(conf, opts):
    matched = False
    if len(opts.filter) > 0:
        for f in opts.filter:
            if f(conf):
                matched = True
    else:
        matched = True
    return matched

def setFilterRules(opts):
    opts.filter = []
    if opts.filter_str is None: return
    for fs in opts.filter_str:
        if ":" in fs:
            p,pat = fs.split(":",1)
            opts.filter.append(lambda conf: p in conf and conf[p] is not None and fnmatch.fnmatch(str(conf[p]), pat))
        else:
            p = fs
            opts.filter.append(lambda conf: p in conf and conf[p] is not None and conf[p])

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

    for i in range(len(opts.backend)):
        if 'Tst' in opts.backend[i]:
            opts.project = backend.split("_")[1]
            opts.backend[i] = backend.split("_")[0]

    setFilterRules(opts)
    if opts.show_diff or opts.show_history:
        load_history(opts)
    show_gen(opts)

