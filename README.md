# Plasticine IR 

Internal IR to target Plasticine from Spatial

## Cloning and installation
~~~~
  git clone https://github.com/stanford-ppl/pir.git 
  export PIR_HOME=`pwd`/pir
  cd pir
  make
~~~~

## Directory structure
~~~~
  dhdl
  ├── apps........................ PIR applications. New applications must be added to this folder
  │   ├── src
  │   ├── test
  ├── bin......................... PIR helper script to build and run applications
  ├── docs
  │   ├── api..................... Scaladoc documentation generated from comments in code
  │   └── manual.................. [WIP] Tex sources for user guide
  ├── lib_managed
  ├── project..................... PIR project and build definitions
  ├── out......................... Directory in which all generated files are created after compilation.
  ├── src
  │   ├── codegen
  │   │   └── csv
  │   │   ├── dot
  │   │   └── json
  │   │   └── logger 
  │   │   ├── scala
  │   │   └── vcd
  │   ├── exceptions ............. PIR Exceptions
  │   └── main ................... Application entry and definition of traits to be mixed with in with every PIR application
  │   └── mapper ................. PIR Mappers
  │   ├── nodes .................. IR Nodes 
  │   └── passes ................. Compiler passes 
  │   └── util ................... pir utilities 
  └── test
~~~~

## Quick start
All the commands described below are to be executed from PIR's home directory:
~~~~
  cd $PIR_HOME
~~~~
* To build PIR and all applications:
~~~~
  make
~~~~

* To build and run a particular application (e.g. `DotProduct`):
~~~~
  bin/pir DotProduct
  # Files generated in $PIR_HOME/out folder
~~~~
* The dataflow graph of a PIR program is output as a 'dot' file. To view the graph (requires 'dot'
  to be installed on your machine):
~~~
  cd out/DotProduct
  bin/run PIR
  # Open 'PIR.pdf' in your favorite PDF viewer
~~~

* To build documentation:
~~~~
  make doc
~~~~

### Contact ###

* Yaqi Zhang (yaqiz@stanford.edu)
