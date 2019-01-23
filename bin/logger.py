import os
import csv
import shutil

from util import *

parser.add_argument('path', type=str, help='Path to parsing directory')
parser.add_argument('-A', '--isApp', dest="path_type", action='store_const', const='app',
        default='backend')
parser.add_argument('-B', '--isBackend', dest="path_type", action='store_const', const='backend', default='backend')
parser.add_argument('-G', '--isGen', dest="path_type", action='store_const', const='gen',
        default='backend')
parser.add_argument('-s', '--summarize', dest="summarize", action='store_true', default=False, help='summarize log into csv')
parser.add_argument('-ap', '--append', dest="append", action='store_true', default=False,
        help='Append to previous summary')

def initSummary(backend, opts):
    opts.sfile = None
    opts.summary = None
    if opts.summarize:
        summary_path ="data/sims/{}.csv".format(backend)
        append = opts.append
        if append:
            rows = []
            with open(summary_path, "r") as f:
                reader = csv.DictReader(f)
                for row in reader:
                    rows.append(row)
        # create new csv
        opts.sfile = open(summary_path, "w")
        conf = parse('', '')
        opts.summary = csv.DictWriter(opts.sfile, delimiter=',', fieldnames=conf.keys())
        opts.summary.writeheader()
        if append:
            for row in rows:
                opts.summary.writerow(row)
    return opts.sfile,opts.summary

def getMessage(backend, app, conf):
    msg = []
    msg.append(backend)
    msg.append(app)
    succeeded = False

    if conf['genpir']:
        msg.append(cstr(GREEN, 'genpir'))
    else:
        msg.append(cstr(RED, 'genpir'))
        return msg,succeeded

    if conf['runpir_err'] is not None:
        msg.append(cstr(RED,'runpir'))
        msg.append(conf['runpir_err'].strip())
        return msg,succeeded
    elif conf['runpir_time'] is None:
        msg.append(cstr(YELLOW,'runpir'))
        return msg,succeeded
    else:
        msg.append(cstr(GREEN,'runpir[{:.2f}s]'.format(conf['runpir_time'])))

    if conf['mappir_err'] is not None:
        msg.append(cstr(RED,'mappir'))
        msg.append(conf['mappir_err'].strip())
        return msg,succeeded
    elif conf['notFit'] is not None:
        msg.append(cstr(YELLOW, 'notFit'))
        return msg,succeeded
    elif conf['mappir_time'] is None:
        msg.append(cstr(YELLOW,'mappir'))
        return msg,succeeded
    elif conf['PCU'] is not None:
        msg.append(cstr(GREEN, "mappir[{:.1f}s]".format(conf['mappir_time'])))
        msg.append(cstr(GREEN,' '.join(['{}:{}'.format(k,conf[k]) for k in ['PCU', "PMU"]])))
    else:
        msg.append(cstr(GREEN,'mappir[{:.1f}s]'.format(conf['mappir_time'])))

    if conf['NetVC'] is None:
        msg.append(cstr(RED,'runproute'))
    else:
        msg.append(cstr(GREEN,'vc:{}'.format(conf['NetVC'])))

    if conf['deadlock']:
        msg.append(cstr(RED, 'DEADLOCK'))
        return msg,succeeded
    elif conf['cycle'] is None:
        msg.append(cstr(RED, 'runpsim'))
        return msg,succeeded
    else:
        msg.append(cstr(GREEN, 'cycle:{} lbw:{} sbw:{}'.format(conf['cycle'], conf['lbw'], conf['sbw'])))

    succeeded = True
    return msg,succeeded

def removeRules(conf, opts):
    reruns = [] + opts.rerun
    # if conf['runpir_err'] is not None and 'not found: value x' in conf['runpir_err']:
        # print(conf['runpir_err'].strip())
        # reruns.append('genpir')
    # if conf['runpir_err'] is not None and 'value depth is not a member of object pir.node.RegFile' in conf['runpir_err']:
        # print(conf['runpir_err'].strip())
        # reruns.append('genpir')
    if conf['runpir_err'] is not None and 'value addr is not a member of pir.node.FringeDenseStore' in conf['runpir_err']:
        print(conf['runpir_err'].strip())
        reruns.append('genpir')

    for p in reruns:
        if p == 'genpir':
            remove(conf['AccelMain'], opts)
        elif p == 'runproute':
            remove(conf['prouteSummary'], opts)
        else:
            remove(conf[p + 'log'], opts)
    return reruns

def logApp(backend, app, show, opts):
    conf = parse(backend, app, opts)

    msg, succeeded = getMessage(backend, app, conf)

    reruns = removeRules(conf, opts)
    if len(reruns) != 0:
        return

    if not opts.summarize:
        print(' '.join(msg))


    if succeeded and opts.summarize:
        opts.summary.writerow(conf)

    if opts.summarize and backend in ["P14x14", "Asic", 'H14x14v3s4c4w']:
        igraph = os.path.join(opts.gendir,backend,app,"pir/igraph/graph.py")
        if os.path.exists(igraph):
            shutil.copyfile(igraph, "data/igraph/{}__{}.py".format(backend, app))
    if opts.summarize and backend in ['H14x14v3s4', 'H14x14v3s4c4w']:
        link = os.path.join(opts.gendir,backend,app,"pir/plastisim/link.csv")
        if os.path.exists(link):
            shutil.copyfile(link, "data/link/{}__{}.csv".format(backend, app))
    if show:
        tail(conf['runpirlog'])
        tail(conf['mappirlog'])
        tail(conf['runproutelog'])
        tail(conf['runpsimlog'])

def parse(backend, app, opts):
    conf = {}
    conf["backend"] = backend
    conf["app"] = app.split("_")[0]
    conf["param"] = app
    conf["freq"] = 1e9
    conf['mappirlog'] = os.path.join(opts.gendir,backend,app,"log/mappir.log")
    conf['runpirlog'] = os.path.join(opts.gendir,backend,app,"log/runpir.log")
    conf['runpsimlog'] = os.path.join(opts.gendir,backend,app,"log/runpsim.log")
    conf['psimsh'] = os.path.join(opts.gendir,backend,app,"log/runpsim.sh")
    conf['runproutelog'] = os.path.join(opts.gendir,backend,app,"log/runproute.log")
    conf['proutesh'] = os.path.join(opts.gendir,backend,app,"log/runproute.sh")
    conf['prouteSummary'] = os.path.join(opts.gendir,backend,app,"pir","plastisim","summary.csv")
    conf['AccelMain'] = os.path.join(opts.gendir,backend,app,"pir","AccelMain.scala")
    parse_genpir(conf['AccelMain'], conf, opts)
    parse_runpir(conf['runpirlog'], conf, opts)
    parse_mappir(conf['mappirlog'], conf, opts)
    parse_runpsim(conf['runpsimlog'], conf, opts)
    parse_runpsimsh(conf['psimsh'], conf, opts)
    parse_runproutesh(conf['proutesh'], conf, opts)
    parse_proutesummary(conf['prouteSummary'], conf, opts)
    return conf

def parse_genpir(log, conf, opts):
    if os.path.exists(log):
        conf['genpir'] = True
    else:
        conf['genpir'] = False

def parse_runpsim(log, conf, opts):
    if opts.summarize:
        print(log)
    parsers = []
    parsers.append(Parser(
        conf,
        'cycle', 
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
        'deadlock', 
        'POSSIBLE DEADLOCK',
         lambda lines: True,
         default=False
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

def main():
    (opts, args) = parser.parse_known_args()
    path = opts.path.rstrip('/')
    if opts.path_type == "app":
        path, app = path.rsplit('/',1)
        gen, backend = path.rsplit('/',1)
        opts.app = app
        opts.backend = [backend]
        opts.gendir = gen
    elif opts.path_type == "backend":
        gen, backend = path.rsplit('/',1)
        opts.backend = [backend]
        opts.gendir = gen
    elif opts.path_type == "gen":
        opts.gendir = path
        opts.backend = getBackends(opts)

    for backend in opts.backend:
        initSummary(backend, opts)
        apps = getApps(backend, opts)
        for app in apps:
            logApp(backend, app, len(apps)==1 and not opts.summarize, opts)
        if opts.summarize:
            opts.sfile.close()
