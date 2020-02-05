# pir

# Get Started
## Compile app from spatial
Clone spatial repo and checkout pir branch or pir-dev branch for latest development
For place and route and simulation, you need to get access to these repos
https://github.com/acrucker/plastiroute.git

https://github.com/acrucker/tungsten.git
```
git clone https://github.com/stanford-ppl/spatial.git
cd spatial
git checkout pir --
make pir
```

## Run app end-to-end
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


