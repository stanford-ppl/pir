import os
import subprocess
from util import *
import socket

class SpatialTest:
    def __init__(self, args=None):
        parser = argparse.ArgumentParser(description='Run experiments')
        parser.add_argument('-a', '--app', action='append', help='name of application to run')
        parser.add_argument('-b', '--backend', type=str, action="append", help='Testing Backend')
        parser.add_argument('-t', '--thread', type=int, default=16, help='Number of threads to run')
        parser.add_argument('-p', '--project', type=str, default="pirTest", help='project name')
        parser.add_argument('-r', '--rerun', action='append', help='passes to rerun', default=[])
        parser.add_argument('-f', '--cppfast', action='store_true', default=False)
        parser.add_argument('-H', '--hybrid', action='store_true', default=False)
        parser.add_argument('-T', '--tee', action='store_true', default=False)
        parser.add_argument('-u', '--publish', action='store_true', default=False)
        parser.add_argument('-n', '--nice', action='store_true', default=False)
        (opts, args) = parser.parse_known_args(args=args)

        self.opts = opts

        if opts.publish:
            cp = subprocess.run("sbt publishAll", shell=True, cwd='pir/')
            cp.check_returncode()

        # resolve apps to run
        if opts.app is None:
            opts.app = ["*"]
        apps = []
        for app in opts.app:
            if app.startswith("file:"):
                with open(app.split(":")[1], "r") as f:
                    for line in f:
                        apps.append('*' + line.replace('\n', ''))
            else: 
                apps.append(app)
        opts.app = apps

        # resolve flags and args
        if opts.backend is None:
            spatialargs = args
            pirargs = []
        else:
            spatialargs = [arg for arg in args if '--s:' in arg]
            pirargs = [arg for arg in args if '--s:' not in arg]
            spatialargs = [arg.replace('--s:','--') for arg in spatialargs]

        d = get_configs()
        if 'spatial-home' not in d and opts.backend is not None:
            pirargs.insert(0,f'--spatial-home={os.getcwd()}')
        hostname = socket.gethostname()

        # if on cluster and currently under /home, put gen in the scratch
        if all(['--gendir' not in arg for arg in args]) and \
                (hostname in ['lagos','tucson'] or 'edo-' in hostname) and \
                os.getcwd().startswith('/home'):
            current = os.getcwd()
            self.opts.gendir = current.replace('/home/','/scratch/')
            print(f'Change gendir to {self.opts.gendir}. Use --gendir override')
            spatialargs.append('--gendir={}'.format(self.opts.gendir))
        else:
            self.opts.gendir = os.path.join(os.gencwd(), 'gen')

        java_cmd = ""
        java_cmd += "export TEST_ARGS=\"{}\"; ".format(' '.join(spatialargs))
        java_cmd += "export PIR_ARGS=\"{}\"; ".format(' '.join(pirargs))
        java_cmd += "sbt -Dmaxthreads={} ".format(opts.thread)
        if (opts.backend is not None):
            for b in opts.backend:
                java_cmd += "-Dtest.{}=true ".format(b)
            for p in opts.rerun:
                java_cmd += "-Drerun.{}=true ".format(p)
        else:
            java_cmd += "-Dci=true "
        java_cmd += "-Dproject={} ".format(opts.project)
        java_cmd += "-Dhybrid={} ".format("true" if opts.hybrid else "false")
        java_cmd += "-Dfast={} ".format("true" if opts.cppfast else "false")
        java_cmd += "-Dtest.tee={} ".format("true" if opts.tee else "false")
        java_cmd += "\"; "
        java_cmd += " project {}; testOnly {}".format(opts.project, ' '.join(opts.app))
        java_cmd += "\""

        if opts.nice:
            java_cmd = "nice -20 " + java_cmd

        self.opts.cmd = java_cmd

    def run(self):
        java_cmd = self.opts.cmd
        print(java_cmd)
        ecode = os.system(java_cmd)
