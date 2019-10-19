all: pir

env:
	virtualenv -p python3.6 env
	env/bin/pip install -r requirements.txt
	# source env/bin/activate && cd /opt/gurobi752/linux64/ && python setup.py install && deactivate

clean: clean-local
	make -C tungsten/ clean
	make -C plastiroute/ clean
	make -C plastisim/ clean

clean-local:
	sbt "; project pir; clean; project spade; clean ; project prism; clean"

distclean: clean
	make -C docs/manual clean

tag:
	ctags -R src/ apps/
	#sbt gen-ctags

install: pir proute tungsten env
	bin/pconf --pir-home=$(shell pwd)

update: pir proute-update tungsten-update

spatial:
	cd ../ && sbt "; project pirTest; compile"

pir:
	sbt publishAll

psim-update:
	git submodule update plastisim
	mkdir -p plastisim/build
	cd plastisim && make CC=gcc Cpp=g++ CXX=g++

psim: 
	git submodule update --init plastisim
	mkdir -p plastisim/build
	cd plastisim && make CC=gcc Cpp=g++ CXX=g++
	bin/pconf --psim-home=$(shell pwd)/plastisim

proute-update:
	git submodule update plastiroute
	cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 

proute:
	git submodule update --init plastiroute
	cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 
	bin/pconf --proute-home=$(shell pwd)/plastiroute

tungsten-update:
	git submodule update tungsten
	cd tungsten && make Cpp=g++ CXX=g++ 

tungsten:
	git submodule update --init tungsten
	cd tungsten && make Cpp=g++ CXX=g++ 
	bin/pconf --tungsten-home=$(shell pwd)/tungsten

pull:
	cd plastisim && git pull && git submodule update --init
	cd plastiroute && git pull

.PHONY: all spatial pir psim proute init pull install env tungsten update clean clean-local

