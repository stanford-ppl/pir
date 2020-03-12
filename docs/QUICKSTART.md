[README](../README.md)

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

```
cd $SPAITAL_HOME
bin/test -a <app> -b <backend> -p <project> -t <threads> [-H, -T]
```

Each ScalaTest has multiple backend that runs FPGA or Plasticine simulation. For Plasticine, the `Tst` backend runs
the following passes (passname):

1. Compile Spatial (genpir)
2. Compile PIR (gentst)
3. Compile generated Cpp simulator (maketst)
4. Run ideal network simulation (runp2p)
5. Place and Route (runproute)
6. Run hybrid network simulation (runhybrid)

`<app>` can use wildcard matching, for example DotProduct*. Unspecified will run all apps in the project. You can provide multiple -a for a list of apps to run. 

`<backend>` to target plasticine use Tst, to FPGA Chisel simulation use VCS

`<project>` for apps in spatial/pir/regression use `pirTest`. for apps in test/spatial/tests/ use `tests`

`-H` runs place and route and hybrid network simulation. By default the script stops at ideal network simulation. 

`-T` will prints logs of individual passes in console for easy debugging. it's recommanded only if you are running a single app. 

`-u` will recompile pir and publish pir to sbt local ivy cache. This is required if you make change
in pir.

example command:
`bin/test -a DotProduct_0 -b Tst -p pirTest -T`

**By default bin/test cache succeeded passes.** To force rerun all passes, add `-r all`

which will generates apps in `spatial/gen/Tst_pirTest`. 

### Monitor Job Status

While `bin/test` is running, you can monitor the status with `bin/log <gendir>`, which will show the progress of each pass. 

You can add `-m field1,field2` to get summary of the apps. Useful fields includes VPCU,VPMU,VDAG,VMC, which are number of virual CUs used. PCU,PMU,DAG,MC,for number of physical CUs used. 
example: `bin/log gen/Tst_pirTest -m PCU,PMU`, `bin/log x --print_fields` for all possible fields to check. 

To produce a snapshot of the summary with `bin/log` use `-s`, which will stores the parsed fields
and apps into a csv file. To change directory to store the log file, use `--logdir`. 

For more options in `bin/log`, use `bin/log --help`.

## Run Passes by Hand
If you don't want to use bin/test, you can generate each pass by hand.

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
--proutesh: passing an absolute path to a script to run plastiroute with customized options. It should be a
bash script, which will be called to run plastiroute. 
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
--mem-tech: DRAM technology. [DDR3, DDR4, HBM]
```
