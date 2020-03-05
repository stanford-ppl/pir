# pir

# Get Started
## Compile app from spatial
Clone spatial repo and checkout pir branch for latest development
For place and route and simulation, you need to get access to these repos

https://github.com/acrucker/plastiroute.git

https://github.com/acrucker/tungsten.git
```
git clone https://github.com/stanford-ppl/spatial.git
cd spatial
git checkout pir --     # << Don't miss the double dashes!
make pir
```

`make pir` initializes and compiles all submodlues, which can takes some time for the first time. It will print bunch of stuff and make sure there's no error before proceeding. 

## Run test end-to-end
If your app extends `SpatialTest` instead of SpatialApp, you can run all following passes with the script `bin/test`. 

Each ScalaTest has multiple backend that runs FPGA or Plasticine simulation. For Plasticine, the `Tst` backend runs

Spatial -> PIR -> IdealNetwork Simulation -> PlaceAndRoute -> Hybrid Network Simulation 

```
cd $SPAITAL_HOME
bin/test -a <app> -b <backend> -p <project> -t <threads> [-H, -T]
```
`<app>` can use wildcard matching, for example DotProduct*. Unspecified will run all apps in the project. You can provide multiple -a for a list of apps to run. 

`<backend>` to target plasticine use Tst, to FPGA Chisel simulation use VCS

`<project>` for apps in spatial/pir/regression use `pirTest`. for apps in test/spatial/tests/ use `tests`

`-H` runs place and route and hybrid network simulation. By default the script stops at ideal network simulation. 

`-T` will prints logs of individual passes in console for easy debugging. it's recommanded only if you are running a single app. 

example command:
`bin/test -a DotProduct_0 -b Tst -p pirTest`

*By default bin/test cache succeeded passes. To force rerun all passes, add `-r all`*

which will generates apps in `spatial/gen/Tst_pirTest`. 

While `bin/test` is running. you can monitor the status by run `bin/log <gendir>`, which will show the progress of each pass. you can add `-m field1,field2` to get summary of the apps. Useful fields includes VPCU,VPMU,VDAG,VMC, which are number of virual CUs used. PCU,PMU,DAG,MC,for number of physical CUs used. 
example: `bin/log gen/Tst_pirTest -m PCU,PMU`, `bin/log --print_fields` for all possible fields to check. 

## Run each step by hand
Generate app from spatial
```
bin/spatial <App> --pir
```

Run pir
```
bin/pir <gendir> [options]  # By default generated directory is in out/<app> unless override by --out=<gendir>
```
or equivalently 
```
cd <gendir>
bash run.sh [options]
```

Run Tungsten
```
cd <gendir>/tungsten
make
./tungsten <args>
```

## PIR Options
To see all possible options, run
```
bash run.sh --help
```
Optionally all options can also be set from a config file at $HOME/.pirconf
Commend line options will override config file options. Example config
```
# compiler configs
mapping=true
stat=true # print out CU statistics

# architectural configs
topo=mesh
row=4
col=4
```
Command line options start with `--`. `--mapping` is equivalent to `mapping=true` in config file

## Compiler Options
```
--stat: Printing CU statistics [default=false]
--dot: Enable dot codegen [default=true]
--psim: Enable plastisim generation [default=true]
--run-psim: run simulation [default=false]
--trace: enable dram trace generation [default=true]
--tungsten: Enable tungsten generation [default=false]
```

## Architectural Parameters
```
--word: Word width [default=32]
--vec: Vector width of SIMD lanes and vector network [default=16]
--net: Network type [dynamic, static, p2p, asic] [default=static]
--topo: Network topology [mesh, torus, cmesh] [default=mesh]
--row: number of rows in network [default=2]
--col: number of columns in network [default=2]
--pattern: Pattern in layout of different CU types. [checkerboard,mcmcstrip]
--dag: enable dram address generator in network [default=true]
--fifo-depth: Depth of FIFO for all CUs [default=4]
--argout: number of ArgOut [default=4]
--argin: number of ArgIn [default=6]
--tokenout: number of TokenOut [default=5]
--vlink: number of static links in vector network [default=4]
--slink: number of static links in scalar network [default=2]
--pcu-stage: number of stages for PCU, similar for pmu, and dag
```

## Troubleshooting

1. `SpatialTest` extends ScalaTest which can be run in parallel managed by their sbt plugin. If your app is an `SpatialTest`, you must have an assertion check in your app, orelse you will get the following error. 
```
 argon.UnhandledException: Uncaught exception Test had no validation checks. (Indeterminate result) (null)   
```

2. When you start sbt, it first looks at your `project/build.properties` for a sbt version. If the version you installed doesn't match the required version in your `build.sbt`, it will download the required version in `~/.sbt`. Then it looks for library dependencies and download them to your local ivy cache in `~/.ivy2`. Sometimes the cached version can be corrupted. You can remove the the wrong sbt in `~/.sbt` or the problematic library in `~/.ivy2/cache/libpath`. 

3. If you see this error
```
 java.lang.NoClassDefFoundError: sourcecode/Name 
```
or 
```
[error] sbt.internal.inc.InvalidScalaInstance: Scala instance doesn't exist or is invalid:                                                                               
[error]     version 2.12.10, library jar: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-library/scala-library-2.12.10.jar, compiler jar: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-compile
r/scala-compiler-2.12.10.jar, other jars: /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.scala-lang/scala-reflect/scala-reflect-2.12.10.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/bundles/org.scala-lang.modules/scala-xml_2.12/scala-xml_2.12-1.0.6.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/jline/jline/jline-2.14.6.jar, /home/yaqiz/pldi20/spatial/pir/lib_managed/jars/org.fusesource.jansi/jansi/jansi-1.12.jar
```
Or error relates to `AbstractMethod` or other `ClassDefNotFoundError` during genpir pass, recompile pir and try again. 
```
cd spatial/pir
make clean-local
make pir
```

4. If you see errors saying something related to `syntax error EOF`. That's outdated emptyness dependency. Remove your `~/bin/emptyness*` and rerun again. You need to have `pkg-config`, which spatial uses to install `emptyness` dependency.

5. If you see an error like this when starting sbt
```
SERVER ERROR: HTTPS Required url=http://repo1.maven.org/maven2/org/scala-sbt/sbt/1.1.1/sbt-1.1.1.jar
...
unresolved dependency: org.scala-sbt#sbt;1.1.1: not found
...
```
Try `cp spatial/pir/bin/repositories ~/.sbt/repositories` and load sbt again. 
This repo uses sbt 1.1.1. If you have a old version of sbt, the default repositories uses http, which they now moved to https. This fix manually adds new urls for repositories in `~/.sbt/repositories`, which uses https. 
Sbt then should download the required sbt version and you can remove the file once sbt 1.1.1 is downloaded. 
