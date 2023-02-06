all: env-update proute tungsten pir 

env: 
	virtualenv -p python3.6 env

env-update: env requirements.txt
	env/bin/pip install -r requirements.txt

clean: clean-local
	make -C tungsten/ clean
	make -C plastiroute/ clean
	make -C plastisim/ clean

clean-local:
	rm -r lib_managed
	sbt "; project pir; clean; project spade; clean ; project prism; clean"

distclean: clean
	make -C docs/manual clean

tag:
	ctags -R src/ apps/
	#sbt gen-ctags

spatial:
	cd ../ && sbt "; project pirTest; test:compile"

pir:
	sbt publishAll

psim:
	mkdir -p plastisim/build
	cd plastisim && make CC=gcc Cpp=g++ CXX=g++

proute:
	cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 

tungsten:
	cd tungsten && make Cpp=g++ CXX=g++ 

update:
	git submodule update --init plastisim
	git submodule update --init plastiroute
	git submodule update --init tungsten
	git submodule update --remote
pull:
	cd plastisim && git pull && git submodule update --init && git submodule update --remote
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

.PHONY: all spatial pir psim proute pull tungsten clean clean-local gurobi gurobipy env-update

