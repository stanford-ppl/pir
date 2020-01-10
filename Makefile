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


gurobi:
ifndef license
	$(error license is undefined. get from https://www.gurobi.com/downloads/free-academic-license/)
endif
	echo $(license)
	cd $(HOME) && curl https://packages.gurobi.com/8.1/gurobi8.1.1_linux64.tar.gz -o gurobi8.1.1_linux64.tar.gz
	cd $(HOME) && tar xvfz gurobi8.1.1_linux64.tar.gz
	echo "export GUROBI_HOME=$(HOME)/gurobi811/linux64" >> $(HOME)/.bashrc
	echo "export PATH=\$$PATH:\$$GUROBI_HOME/bin" >> $(HOME)/.bashrc
	echo "export LD_LIBRARY_PATH=\$$LD_LIBRARY_PATH:\$$GUROBI_HOME/lib" >> $(HOME)/.bashrc
	$(HOME)/gurobi811/linux64/bin/grbgetkey $(license)

gurobipy:
	cd $(HOME)/gurobi811/linux64 && $(PWD)/env/bin/python setup.py install

.PHONY: all spatial pir psim proute init pull install env tungsten update clean clean-local gurobi gurobipy

