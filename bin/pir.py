#!env/bin/python

import glob
import re
from argparse import ArgumentParser
import os, sys, datetime
import subprocess
from os.path import dirname, abspath
from util import *

def main():
    if (opts.backend is None):
        backends = [None]
    else:
        backends = opts.backend
    os.system('sbt "; project pir; publishAll"')
    for app in opts.app:
        for backend in backends:
            if (backend is not None):
                genDir = "out/{}/{}".format(backend, app)
            else:
                genDir = "out/{}".format(app)

            print(genDir)

            os.chdir(genDir)
            ecode = os.system('sbt "runMain AccelMain {}"'.format(' '.join(args)))

if __name__ == "__main__":
    main()
