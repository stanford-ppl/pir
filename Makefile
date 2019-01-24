all:
	sbt "; compile; project apps; compile"

env:
	virtualenv env

clean:
	#rm -rf out
	#rm -f docs/api
	#sbt clean

distclean: clean
	make -C docs/manual clean

tag:
	ctags -R src/ apps/
	#sbt gen-ctags

install: pir psim proute

spatial:
	cd ../ && sbt "; project pirTest; compile"

pir:
	sbt publishAll

psim:
		mkdir -p plastisim/build
		cd plastisim && make CC=gcc Cpp=g++ CXX=g++
		bin/pconf --psim-home=$(shell pwd)/plastisim

proute:
		cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 
		bin/pconf --proute-home=$(shell pwd)/plastiroute

tungsten:
		cd tungsten && make Cpp=g++ CXX=g++ 
		bin/pconf --tungsten-home=$(shell pwd)/tungsten

pull:
	cd plastisim && git pull && git submodule update --init
	cd plastiroute && git pull

.PHONY: all spatial pir psim proute init pull install env tungsten

