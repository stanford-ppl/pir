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

install: spatial pir psim proute

spatial:
	cd ../
	sbt "; project pir_test; compile"

pir:
	sbt publishAll

psim:
		cd plastisim; mkdir -p build; make

proute:
		cd plastiroute; make

.PHONY: all spatial pir psim proute init pull install add env igraph

