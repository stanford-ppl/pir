import os
import subprocess
import argparse

def recurse(func, **params):
    for param in params:
        if type(params[param]) == list:
            res = []
            for val in params[param]:
                newparams = params.copy()
                newparams[param] = val
                res += recurse(func, **newparams)
            return res
    if 'filterNot' in params:
        filterNot = params['filterNot'](params)
        if filterNot: return []
        del params['filterNot']
    return [func(params)]

def moreThanOne(p, **kws):
    return len([p[key] for key in kws if p[key] == kws[key]]) > 1

def divup(a,b):
    return (a + b - 1) / b

def pirArgs(**kws):
    def func(kws):
        pa = []
        for k in kws:
            if 'more' in k:
                pa.append(kws[k])
            else:
                pa.append('--{}={}'.format(k.replace("_",'-'),kws[k]))
        return ' '.join(pa)
    return recurse(func,**kws)

appCtr = {}

def emitApp(f, app, **params):
    trait = app.split("[")[0]
    if 'postfix' in params:
        postfix = '_'+params['postfix']
        del params['postfix']
    else:
        postfix = ''
    override = ""
    if 'pirArgs' in params:
        override = 'override def pirArgs = super.pirArgs + " {}"; '.format(params['pirArgs'])
        del params['pirArgs']
    if 'runtimeArgs' in params:
        override = 'override def runtimeArgs = "{}"; '.format(params['runtimeArgs'])
        del params['runtimeArgs']
    key = trait+postfix
    if key not in appCtr:
        appCtr[key] = 0
    f.write("class {}{}_D{} extends {}({}){{ {} }}\n".format(
        trait, 
        postfix,
        appCtr[key],
        app,
        ",".join([f"{k}={params[k]}" for k in params]),
        override,
    ))
    appCtr[key] +=1
    return

dsefile = None
class dseopen:
    def __init__(self, filename):
        self.file = open(filename, 'w')
    def __enter__(self):
        global dsefile
        dsefile = self.file
        self.file.write("""
import spatial.dsl._
import forge.tags._

@virtualize
class Dummy3
""")
        return self.file
    def __exit__(self, type, value, traceback):
        self.file.close()
        dsefile = None


def dseApp(app, **params):
    global dsefile
    if 'postfix' in params:
        postfix = '_'+params['postfix']
    else:
        postfix = ''
    res = recurse(lambda params: emitApp(dsefile, app,**params), **params)
    print("{}{} {} design points".format(app, postfix, len(res)))


class DSE():
    def __init__(self, args=None, logdir="logs", project='spatialApps'):
        self.args = args
        self.logdir = logdir 
        self.project = project

    def run_dse(self, opts, args):
        pirArgs = "--fast --debug=false"
        pirArgs += " --net=p2p --row=20 --col=20 --mem-tech=DDR4"
        pirArgs += " --split-thread=4"
        pirArgs += " " + ' '.join(args)
    
        os.system(f'mkdir -p {self.logdir}/')
        if opts.publish:
            code = subprocess.call('sbt "; project pir; publishAll"', shell=True, cwd='spatial/pir')
            if code != 0:
                exit()
        apps = opts.app
        if apps is None: apps = []
        gendir = f"Tst_{self.project}"
        if opts.clear:
            os.system('rm -r gen/{}'.format(gendir))
        os.environ["_JAVA_OPTIONS"] = "-Xmx10G -Xms1G -Xss1G"
        apps = ' '.join(['-a ' + a for a in apps])
        os.system(f'bin/test -t {opts.thread} -p {self.project} -b Tst {apps} {pirArgs}')
    
        if opts.save:
            os.system('bin/log gen/{} -p spatialApps -b Tst {}'.format(gendir,apps))
            os.system('bin/log gen/{} -p spatialApps -b Tst -s --logdir log {}'.format(gendir,apps))
            # os.system('bin/log ../gen/{} -s -p {} -b {}'.format(gendir, project,
                # opts.backend))
    
    def setup_dse(self, gen_apps):
        parser = argparse.ArgumentParser(description='Run experiments')
        parser.add_argument('-g', '--gen', action='store_true', default=False)
        parser.add_argument('-t', '--thread', type=int, default=30, help='Number of threads to run')
        parser.add_argument('-a', '--app', action='append', help='name of application to run')
        parser.add_argument('-c', '--clear', default=False, action='store_true')
        parser.add_argument('-x', dest='save', default=True, action='store_false')
        parser.add_argument('-nu', '--no-publish', dest="publish", default=True, action='store_false')
        (opts, args) = parser.parse_known_args(args=self.args)
    
        if opts.gen:
            gen_apps()
            return
    
        self.run_dse(opts, args)
