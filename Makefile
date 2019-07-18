all: pir

env:
	virtualenv -p python2 env
	env/bin/pip install -r requirements.txt

clean:
	#rm -rf out
	#rm -f docs/api
	sbt clean

distclean: clean
	make -C docs/manual clean

tag:
	ctags -R src/ apps/
	#sbt gen-ctags

install: pir psim proute tungsten env

update: pir psim-update proute-update tungsten-update

spatial:
	cd ../ && sbt "; project pirTest; compile"

pir:
	sbt publishAll

psim-update:
	git submodule update plastisim
	mkdir -p plastisim/build
	cd plastisim && make CC=gcc Cpp=g++ CXX=g++

psim: psim-update
	bin/pconf --psim-home=$(shell pwd)/plastisim

proute-update:
	git submodule update plastiroute
	cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 

proute: proute-update
	bin/pconf --proute-home=$(shell pwd)/plastiroute

tungsten-update:
	git submodule update tungsten
	cd tungsten && make Cpp=g++ CXX=g++ 

tungsten: tungsten-update
	bin/pconf --tungsten-home=$(shell pwd)/tungsten

pull:
	cd plastisim && git pull && git submodule update --init
	cd plastiroute && git pull

.PHONY: all spatial pir psim proute init pull install env tungsten update

