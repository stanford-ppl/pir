import os
import subprocess
from util import *

def main(args=None):
    parser.add_argument('-f', '--cppfast', action='store_true', default=False)
    parser.add_argument('-H', '--hybrid', action='store_true', default=False)
    parser.add_argument('-T', '--tee', action='store_true', default=False)
    parser.add_argument('-u', '--publish', action='store_true', default=False)
    (opts, args) = parser.parse_known_args(args=args)

    spatial_flags = [arg for arg in args if '--s:' in arg]
    args = [arg for arg in args if '--s:' not in arg]
    spatial_flags = [arg.replace('--s:','--') for arg in spatial_flags]

    if opts.publish:
        cp = subprocess.run("sbt publishAll", shell=True, cwd='pir/')
        cp.check_returncode()

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
    java_cmd = ""
    d = get_configs()
    enablePIR = '--pir' in args or (opts.backend is not None and 'Tst' in opts.backend)
    if 'spatial-home' not in d and enablePIR:
        args.insert(0,f'--spatial-home={os.getcwd()}')
    java_cmd += "export TEST_ARGS=\"{}\"; ".format(' '.join(args))
    if len(spatial_flags) != 0:
        java_cmd += "export SPATIAL_FLAGS=\"{}\"; ".format(' '.join(spatial_flags))
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

    print(java_cmd)

    ecode = os.system(java_cmd)
