import pandas as pd
import argparse
import os
import xml.etree.ElementTree as ET
import pydot
import fnmatch
import json

## With colour library
from colour import Color
red = Color("red")
white = Color("white")
black = Color("black")
green = Color("green")
orange = Color("orange")
light_green = Color(hex="#afec57")
dark_green = Color(hex="#0e5d2c")
gradient = list(light_green.range_to(dark_green, 101))
# print(red.hex_l)
# print(green.hex_l)
# print([c.hex_l for c in gradient])

## Without colour library
# red = '#ff0000'
# green = '#008000'
# gradient = ['#ff0000', '#fe0500', '#fc0a00', '#fb0f00', '#fa1400', '#f91900', '#f71e00', '#f62200', '#f52700', '#f42c00', '#f23000', '#f13500', '#f03a00', '#ee3e00', '#ed4200', '#ec4700', '#eb4b00', '#e94f00', '#e85400', '#e75800', '#e65c00', '#e46000', '#e36400', '#e26800', '#e16c00', '#df7000', '#de7300', '#dd7700', '#db7b00', '#da7f00', '#d98200', '#d88600', '#d68900', '#d58d00', '#d49000', '#d39300', '#d19700', '#d09a00', '#cf9d00', '#cda000', '#cca300', '#cba600', '#caa900', '#c8ac00', '#c7af00', '#c6b200', '#c5b500', '#c3b800', '#c2ba00', '#c1bd00', '#bfbf00', '#babe00', '#b5bd00', '#b0bc00', '#acba00', '#a7b900', '#a2b800', '#9db700', '#98b500', '#94b400', '#8fb300', '#8ab200', '#86b000', '#81af00', '#7dae00', '#79ac00', '#74ab00', '#70aa00', '#6ca900', '#68a700', '#64a600', '#60a500', '#5ca400', '#58a200', '#54a100', '#50a000', '#4c9e00', '#489d00', '#459c00', '#419b00', '#3d9900', '#3a9800', '#369700', '#339600', '#2f9400', '#2c9300', '#299200', '#269100', '#228f00', '#1f8e00', '#1c8d00', '#198b00', '#168a00', '#138900', '#108800', '#0d8600', '#0b8500', '#088400', '#058300', '#038100', '#008000']

# def xmlns(tag):
    # return '{http://www.w3.org/2000/svg}' + tag 

# def classof(node):
    # if 'class' not in node.attrib:
        # return None
    # return node.attrib['class']

# def title(node):
    # return node.find(xmlns('title')).text

# def annotate_ctx(node, states):
    # if "cluster_Context" not in title(node): return

# def annotate(opts):
    # states = opts.tab.to_dict(orient='index')
    # tree = ET.parse(opts.dot)
    # root = tree.getroot() # svg node
    # for node in root[0].iter(xmlns('g')):
        # annotate_ctx(node, states)
        # if (classof(node) == 'cluster'):
            # print(title(node))

def traverse(graph, func):
    func(graph)
    if (issubclass(type(graph), pydot.Graph)):
        for node in graph.get_node_list():
            traverse(node, func)
        for subgraph in graph.get_subgraph_list():
            traverse(subgraph, func)
        for edge in graph.get_edge_list():
            traverse(edge, func)

def check_complete(l, states):
    finish_cnt = False
    if ("count=Finite(") in l:
        cnt = int(l.split("count=Finite(")[1].split(")")[0])
        finish_cnt = states['active'] >= cnt
    if 'complete' in states and states['complete'] == 1:
        finish_cnt = True
    return finish_cnt

def annotate_default(node, states):
    l = node.get_label().replace("\"","")
    for s in states:
        if s == "inactive": continue
        if s == "cont_inactive": continue
        l += "\n{}={}".format(s,states[s])
    node.set_label(l)

def annotate_context(node, states):
    l = node.get_label().replace("\"","")
    completed = check_complete(l, states)
    for s in ['active']:
        k = s
        v = states[s]
        if k == 'active': 
            cycle = opts.states['cycle']
            active_pct = float(100*v) / cycle if cycle !=0 else 0
            v = "{} ({:.2f}%)".format(v, active_pct) 
            if completed: v += " [V]"
            else: v += " [X]"
        l += "\n{}={}".format(k,v)
    node.set_label(l)

    if opts.format=="cycle":
        if completed:
            node.set('fillcolor', "\"{}\"".format(green))
        elif not states['ins']:
            node.set('fillcolor', "\"{}\"".format(red))
        elif not states['outs']:
            node.set('fillcolor', "\"{}\"".format(orange))
        else:
            node.set('fillcolor', "\"{}\"".format(green))
    else:
        node.set('fillcolor', "\"{}\"".format(gradient[int(active_pct)]))

def get(states, key):
    if key not in states: return None
    else: return states[key]

def annotate_fifo(node, states):
    l = node.get_label().replace("\"","")
    completed = check_complete(l, states)
    if opts.format=="cycle":
        for s in ['active', 'nelem', 'next',"valid","ready"]:
            if s not in states: continue
            v = states[s]
            if s == 'nelem': v = int(v)
            l += "\n{}={}".format(s,v)
    if opts.states['deadlock']:
        if completed:
            node.set('fillcolor', "\"{}\"".format(green))
        elif 'valid' in states and not states['valid']:
            node.set('fillcolor', "\"{}\"".format(red))
        elif 'ready' in states and not states['ready']:
            node.set('fillcolor', "\"{}\"".format(orange))
        else:
            node.set('fillcolor', "\"{}\"".format(white))
    if opts.format=="pct":
        cycle = float(get(opts.states, "cycle"))
        for s in ['active',"stall","starve"]:
            if s not in states: continue
            v = round((states[s] / cycle) * 100,1)
            l += "\n{}={}".format(s,v)
    node.set_label(l)

def checkStarve(dsts, opts):
    for dst in dsts:
        dststate = opts.states['modules'][dst]
        if 'valid' in dststate and not dststate['valid']:
            return True
    return False

def getMaxState(field, dsts, opts):
    prev = None
    count = 0
    for dst in dsts:
        dststate = opts.states['modules'][dst]
        count = float(get(dststate,field))
        if prev is None or count > prev:
            prev = count
    return count

def annotate_gio(edge, states):
    go = get_name(edge)
    dsts = edge.get_id().replace("\"","").split(",")
    gi = dsts[0]
    reads = [read for read in dsts[1:] if read in opts.states['modules']]
    completed = False # TODO: change this to check_complete
    ett = edge.get_labeltooltip()
    active = int(get(states, "active"))
    if "count=" in ett:
        cnt = int(ett.split("count=")[1].split("\n")[0])
        completed = active == cnt
    l = ""
    if opts.format=="pct":
        cycle = float(get(opts.states, "cycle"))
        pct = -1
        if active is not None:
            pct = round(active * 100.0 / cycle,1)
        starvePct = round(getMaxState("starve",reads,opts) * 100 / cycle,1)
        stallPct = round(getMaxState("stall",reads,opts) * 100 / cycle,1)
        l += "\na:{}\n!v:{}\n!r:{}".format(pct, starvePct, stallPct)
        if starvePct <= 1 and stallPct <= 1:
            edge.set('fontcolor', "\"{}\"".format(green))
        elif stallPct > starvePct:
            edge.set('fontcolor', "\"{}\"".format(orange))
        else:
            edge.set('fontcolor', "\"{}\"".format(red))
    else:
        l += go.replace("go","")
        l += "\n[{}]".format(active)
        if completed:
            edge.set('fontcolor', "\"{}\"".format(green))
        elif checkStarve(reads, opts):
            edge.set('fontcolor', "\"{}\"".format(red))
        elif 'ready' in states and not states['ready']:
            edge.set('fontcolor', "\"{}\"".format(orange))
        else:
            edge.set('fontcolor', "\"{}\"".format(black))
    edgetooltip = []
    for dst in dsts:
        if dst not in opts.states['modules']:
            continue
        dststate = opts.states['modules'][dst]
        edgetooltip.append("{}|{}".format(dst, dststate['active']))
    edgetooltip = '\n'.join(edgetooltip)
    edge.set_label(l)
    edge.set_tooltip(edgetooltip)
    edge.set_labelURL(f"../../../tungsten/logs/{go}.log")

def annotate_global(node, states):
    tooltip = node.get_tooltip()
    lines = tooltip.split("\n")
    ctxs = []
    ctxlabel = []
    for line in lines:
        if "Context" in line:
            ctx = line.split("\n")[0]
            ctxs.append(ctx)
            after = tooltip.split(ctx)[1]
            countidx = after.find('count')
            retidx = after.find("\n",countidx)
            ctxlabel.append(after[0:retidx])
    l = node.get_label().replace("\"","")
    gstates = opts.states["modules"]
    cycle = float(get(opts.states, "cycle"))
    for ctx,label in zip(ctxs,ctxlabel):
        if ctx not in gstates: continue
        active = get(gstates[ctx], "active")
        if opts.format=="cycle":
            l += "\nc{}[{}]".format(ctx.split("Context")[1], active)
        else:
            pct = round(active * 100.0 / cycle,1)
            l += "\nc{}[{}%]".format(ctx.split("Context")[1], pct)
        if opts.states['deadlock']:
            l += "[V]" if check_complete(label,gstates[ctx]) else "[X]"
    node.set_label(l)

def match(patterns, func, node, states, isEdge=False):
    if node.annotated: return
    if isEdge and type(node) != pydot.Edge: return
    if not isEdge and type(node) != pydot.Node and type(node) != pydot.Subgraph: return
    matched = False;
    name = get_name(node)
    for pat in patterns:
        matched = matched or fnmatch.fnmatch(name, pat)
    if matched:
        func(node, states)
        node.annotated = True

def get_name(node):
    if type(node) == pydot.Edge:
        return "go" + node.get_label().replace("\n","").replace("\"","")
    else:
        return node.get_name().replace("cluster_","")

def annotate_ctx_dot():
    cycle = opts.states['cycle']
    states = opts.states['modules']
    def visitNode(node):
        if type(node) == pydot.Edge:
            return
        name = get_name(node)
        if name in states:
            node.annotated = False;
            match(["Context*"], annotate_context, node, states[name]);
            match(["BufferRead*", "TokenRead*", "gi*", "go*"], annotate_fifo, node, states[name]);
            match(["*"], annotate_default, node, states[name]);

    graph, = pydot.graph_from_dot_file(opts.ctx)
    traverse(graph, visitNode)
    name = opts.ctx.split("/")[-1]
    path = opts.ctx.replace(name, "csim.html")
    succeed = graph.write(path, format='svg')
    path = os.path.abspath(path)
    if not succeed:
        print("Write to {} failed!".format(path))
    else:
        print("Annotated graph at {}".format(path))

def annotate_global_dot():
    if not os.path.exists(opts.glob):
        return
    cycle = opts.states['cycle']
    states = opts.states['modules']
    def visitNode(node):
        name = get_name(node)
        node.annotated = False;
        if name in states:
            match(["go*"], annotate_gio, node, states[name], isEdge=True);
        match(["*"], annotate_global, node, states, isEdge=False);

    graph, = pydot.graph_from_dot_file(opts.glob)
    traverse(graph, visitNode)
    name = opts.glob.split("/")[-1]
    path = opts.glob.replace(name, "gsim.html")
    succeed = graph.write(path, format='svg')
    path = os.path.abspath(path)
    if not succeed:
        print("Write to {} failed!".format(path))
    else:
        print("Annotated graph at {}".format(path))

def analyze():
    ms = opts.states['modules']
    rows = []
    for m in ms:
        d = {}
        d['name'] = m
        d.update(ms[m])
        rows.append(d)
    tab = pd.DataFrame.from_records(rows)
    cycle = opts.states['cycle']
    tab = tab[tab.name.str.contains("Context")].copy()
    tab['activePct'] = tab['active'] * 100 / cycle
    tab['starvePct'] = tab['starve'] * 100 / cycle
    tab['stallPct'] = tab['stall'] * 100 / cycle

    tab = tab[tab.starvePct > 0] # with input
    maxActive = max(tab['activePct'].values)
    tab = tab.nlargest(1,'activePct',keep='all') # on the critical path
    tab = tab[tab.stallPct > 0]
    tab = tab.nlargest(5,'stallPct',keep='all')
    print(tab[['name','global','activePct','starvePct','stallPct']])

def main():
    parser = argparse.ArgumentParser(description='Load simulation states')
    parser.add_argument('-s', '--state_path', type=str, default='{}/logs/state.json'.format(os.getcwd()), help='state log path')
    parser.add_argument('-c', '--ctx', type=str, default='../pir/out/dot/ctx.dot', 
        help='path to ctx dot graph to annotate on')
    parser.add_argument('-g', '--glob', type=str, default='../pir/out/dot/global.dot', 
        help='path to global dot graph to annotate on')
    parser.add_argument('--format', type=str, default=None, help='formatting for stats')

    global opts
    (opts, args) = parser.parse_known_args()

    opts.ctx = os.path.abspath(opts.ctx)
    opts.glob = os.path.abspath(opts.glob)
    opts.state_path = os.path.abspath(opts.state_path)

    # tab = pd.read_json("file://{}".format(opts.state_path), orient='records')
    # tab = tab.set_index("module")
    # print(tab)
    # opts.tab = tab
    # opt.states = tab.to_dict(orient='index')

    with open(opts.state_path, 'r') as f:
        opts.states = json.load(f)
    
    if opts.format is None:
        opts.format = "cycle" if opts.states['deadlock'] else "pct"

    # annotate_ctx_dot()
    annotate_global_dot()
    # analyze()

if __name__ == "__main__":
    main()
