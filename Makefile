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
		export PLASTISIM_HOME=$(shell pwd)/plastisim

proute:
		cd plastiroute && make CC=gcc Cpp=g++ CXX=g++ 
		export PLASTIROUTE_HOME=$(shell pwd)/plastiroute

pull:
	cd plastisim && git pull && git submodule update --init
	cd plastiroute && git pull

.PHONY: all spatial pir psim proute init pull install env

