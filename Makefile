all:
	sbt "; compile; project apps; compile"

doc:
	sbt doc
	ln -s `readlink -f target/scala-2.11/api` docs/api
	#make -C docs/manual

clean:
	rm -rf out
	rm -f docs/api
	sbt clean

distclean: clean
	make -C docs/manual clean

tag:
	ctags -R src/ apps/
	#sbt gen-ctags
