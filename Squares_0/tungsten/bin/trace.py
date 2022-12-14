import json
import os
import argparse
import fnmatch
import code
from os.path import dirname

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
    # return "{}{}{}".format(color, msg, NC)
    return msg

def tprint(msg, inc=False, dec=False):
    print("  "*opts.tab + str(msg))
    if inc:
        opts.tab +=1
    if dec:
        opts.tab -=1

def err(msg):
    tprint(cstr(RED, f'[error] {msg}'))

def tinc():
    opts.tab +=1

def tdec():
    opts.tab -=1

class Indent:
    def __init__(self, msg=None):
        self.msg = msg
    def __enter__(self):
        if self.msg is None:
            tinc()
        else:
            tprint(self.msg, inc=True)
        return
    def __exit__(self, type, value, traceback):
        tdec()
        return

def parse(msg):
    msg = msg.strip()
    array = msg.split(" ")
    if len(array) == 1:
        cmd = array[0]
        args = []
    elif len(array) == 2:
        cmd = array[0]
        args = [array[1]]
    else:
        cmd, args = array
    return cmd, args

def filtermd(args, searchlist):
    modules = searchlist 
    if len(args) != 0:
        filtered = []

        for m in modules:
            if any([fnmatch.fnmatch(m, arg) for arg in args]):
                filtered.append(m)
        modules = filtered
    return modules

def selectmd(args, searchlist):
    modules = filtermd(args, searchlist)
    if len(modules) > 1:
        tprint(', '.join(modules))
        tprint('which one?')
        return
    if len(modules) == 0:
        tprint(f"No module matched {' '.join(args)}")
        return
    module = modules[0]
    return module

class Quit:
    def __init__(self):
        self.cmd = "q"
        self.description = "Quit tracing."
    def handle(self, args):
        exit()

class Help:
    def __init__(self, rules):
        self.cmd = "help"
        self.rules = rules
        self.description = "help"
    def handle(self, args):
        for rule in self.rules:
            tprint(f"{rule.cmd}: {rule.description}")

class List:
    def __init__(self):
        self.cmd = "ls"
        self.description = "List modules. Usage: ls [patterns]"
    def handle(self, args):
        modules = filtermd(args, opts.allmodules)
                    
        tprint(', '.join(modules))
        tprint(f"{len(modules)} modules")

def printstate(module):
    if module not in opts.modules:
        return
    state = opts.modules[module]
    msg = ""
    for key in state:
        if key in ['global']:
            continue
        token = f'{key}: {state[key]} ' 
        if key in ['ins', 'valid'] and state[key] == 0:
            token = cstr(RED,token)
        if key in ['outs', 'ready'] and state[key] == 0:
            token = cstr(YELLOW,token)
        if key == 'complete' and state[key] == 1:
            token = cstr(GREEN,token)
        msg += token
    tprint(msg)

def printsrcctx(module):
    if module not in opts.graph:
        tprint(module)
    else:
        name = '' if 'name' not in opts.graph[module] else opts.graph[module]['name']
        srcctx = ' '.join(opts.graph[module]['srcCtx'])
        tprint(f"{module} ${name} {srcctx}")

def printdep(module, recurse=True):
    graph = opts.graph[module]
    if 'ctrlers' in graph:
        with Indent(cstr(CYAN, "ctrlers:")):
            opts.ctrlers += graph['ctrlers']
            for dep in graph['ctrlers']:
                printsrcctx(dep)
                with Indent():
                    printstate(dep)
    if 'contexts' in graph:
        with Indent(cstr(CYAN, "contexts:")):
            opts.contexts += graph['contexts']
            for dep in graph['contexts']:
                printsrcctx(dep)
                with Indent():
                    printstate(dep)
    if 'inputs' in graph:
        with Indent(cstr(CYAN, "inputs:")):
            opts.inputs += graph['inputs']
            for dep in graph['inputs']:
                printsrcctx(dep)
                with Indent():
                    printstate(dep)
    if 'outputs' in graph:
        with Indent(cstr(CYAN, "outputs:")):
            opts.outputs += graph['outputs']
            for dep in graph['outputs']:
                printsrcctx(dep)
                with Indent():
                    printstate(dep)
    if recurse and 'parent' in graph:
        with Indent(cstr(CYAN, "parent:")):
            dep = graph['parent']
            opts.parent = dep
            printsrcctx(dep)
            with Indent():
                printstate(dep)

def printhandle(args):
    module = selectmd(args, opts.allmodules)
    if module is None:
        return
    tprint(cstr(CYAN, f"================= {module} ================="))
    tprint("**************** Metadata ****************")
    graph = opts.graph[module]
    for key in graph:
        if key in ['ctrlers', 'contexts', 'inputs', 'outputs', 'parent']:
            continue
        tprint(f"{key}: {graph[key]}")
    print ("")
    tprint("***************** States *****************")
    printstate(module)
    print ("")
    tprint("**************** Dependency ****************")
    opts.ctrlers = []
    opts.contexts = []
    opts.inputs = []
    opts.outputs = []
    opts.parent = None
    printdep(module)
    if not opts.traceback:
        opts.tracestack.append(module)

class Print:
    def __init__(self):
        self.cmd = "p"
        self.description = "Print module final state. Usage: p <module name>"
    def handle(self, args):
        printhandle(args)

def indexinto(key, args):
    if key == 'back':
        opts.traceback = True
        if len(opts.tracestack) <= 0:
            err(f'Empty trace stack')
            return
        else:
            return opts.tracestack.pop()
    if not key.endswith('s'):
        if getattr(opts, key) is None:
            err(f'no previous {key} available')
            return
        else:
            return getattr(opts, key)
    l = getattr(opts,key)
    if len(l) == 0:
        err(f'no previous {key.replace("s","")} available')
        return
    if len(args) == 0:
        if len(l) == 1:
            idx = 0
        else:
            err(f'Select input! Avaialbe: ' + ' '.join(l))
            return
    else:
        idx = int(args[0])
    if idx >= len(l):
        err('Index out of bound. Avaialbe: ' + ' '.join(l))
        return
    dep = l[idx]
    return dep

class Trace:
    def __init__(self, key):
        if key == "contexts":
            self.cmd = "tx"
        else:
            self.cmd = "t" + key[0]
        self.description = f"Trace {key}. Usage: {self.cmd} <#>"
        self.key = key
    def handle(self, args):
        opts.traceback = False
        dep = indexinto(self.key, args)
            
        if dep is None:
            return
        printhandle([dep])

class PrintStack:
    def __init__(self):
        self.cmd = "pt"
        self.description = f"Print trace stack"
    def handle(self, args):
        if len(opts.tracestack) == 0:
            print("Empty trace stack")
        for module in opts.tracestack:
            tprint(module)

class Log:
    def __init__(self):
        self.cmd = "log"
        self.description = "Open module log. Usage: log <module name>"
    def handle(self, args):
        module = selectmd(args, opts.allmodules)
        if module is None:
            return
        path = f"logs/{module}.log"
        if os.path.exists(path):
            os.system(f"vim {path}")
        else:
            tprint(f"{path} doesn't exists. ")
            tprint("Turn on module logging for this module in script and rerun simulation.")

class Name:
    def __init__(self):
        self.cmd = "n"
        self.description = f"Print modules with matching name. Usage: n <pattern>"
    def handle(self, args):
        names = filtermd(args, opts.names.keys())
        for name in names:
            with Indent(f"{name}:"):
                for md in opts.names[name]:
                    printsrcctx(md)

def handle(msg):
    cmd, args = parse(msg)
    for rule in opts.rules:
        if rule.cmd == cmd:
            rule.handle(args)
            break

def interactive():
    #f = open("/dev/stdin", "r")
    #while True:
        #l = f.readline()
        #handle(f)
    x = input("> ")
    handle(x)

    interactive()

def appendto(key, module, appended):
    if key not in opts.graph[module]:
        opts.graph[module][key] = []
    opts.graph[module][key].append(appended)

def parsectxcc(dirname, src):
    with open (os.path.join(dirname, src), 'r') as f:
        for line in f:
            if 'AddInput' in line:
                ctrler, input = line.strip().split('->AddInput(')
                input = input.split(")")[0].replace("fifo_","").split(",")[0]
                ctx = opts.graph[ctrler]['parent']
                appendto("inputs", ctx, input)
                appendto("outputs", input, ctx)
            if 'AddOutput' in line:
                ctrler, output = line.strip().split('->AddOutput(')
                output = output.split(")")[0].split(",")[0].replace("fifo_","").split(",")[0]
                if output not in opts.graph: continue
                ctx = opts.graph[ctrler]['parent']
                appendto("outputs", ctx, output)
                appendto("inputs", output, ctx)

def parsesrc():
    for dirname, sudirlist, filelist in os.walk('src/'):
        for src in filelist:
            if src.startswith("Context") and src.endswith('.cc'):
                parsectxcc(dirname, src)

def buildnames():
    opts.names = {}
    for module in opts.graph:
        if 'name' in opts.graph[module]:
            name = opts.graph[module]['name']
            if name not in opts.names:
                opts.names[name] = []
            opts.names[name].append(module)

def main():
    parser = argparse.ArgumentParser(description='Load simulation states')
    parser.add_argument('-s', '--state_path', type=str, default='{}/logs/state.json'.format(os.getcwd()), help='state log path')

    global opts
    (opts, args) = parser.parse_known_args()

    opts.tab = 0
    opts.tracestack = []
    opts.traceback = False

    opts.state_path = os.path.abspath(opts.state_path)

    opts.gen_path = dirname(dirname(dirname(opts.state_path)))
    opts.graph_path = os.path.join(opts.gen_path, "pir", "out", "program.json")

    if not os.path.exists(opts.state_path):
        tprint(cstr(WARNING, f"[warn] Simulation states {opts.state_path} not avaiable! Run simulation!"))
        opts.modules = {}
    else:
        print(f"Load states from {opts.state_path}")
        with open(opts.state_path, 'r') as f:
            opts.states = json.load(f)
            opts.modules = opts.states['modules']

    print(f"Load graph from {opts.graph_path}")
    with open(opts.graph_path, 'r') as f:
        opts.graph = json.load(f)['IR']

    opts.allmodules = list(opts.modules.keys())

    buildnames()

    for md in opts.graph:
        if md not in opts.allmodules:
            opts.allmodules.append(md)

    parsesrc()

    rules = []
    rules.append(Quit())
    rules.append(Log())
    rules.append(Help(rules))
    rules.append(Print())
    rules.append(List())
    rules.append(Name())
    rules.append(Trace("inputs"))
    rules.append(Trace("outputs"))
    rules.append(Trace("ctrlers"))
    rules.append(Trace("contexts"))
    rules.append(Trace("parent"))
    rules.append(Trace("back"))
    rules.append(PrintStack())

    opts.rules = rules

    if len(args) > 0:
        args = ' '.join(args)
        args = args.split(";")
        for arg in args:
            handle(arg)
        exit()

    print("Happy debugging! 😊 ")
    # print("Happy debugging! )

    interactive()

if __name__ == "__main__":
    main()
