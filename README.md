# pir

# Get Started
## Compile app from spatial
Clone spatial repo and checkout pir branch
```
git clone https://github.com/stanford-ppl/spatial-lang.git
cd spatial-lang
git submodule update --init
git checkout pir
```

Clone pir repo
```
git clone https://github.com/stanford-ppl/pir.git
git submodule update --init
```
Set `SPATIAL_HOME` and `PIR_HOME` environmental variables.

To generate app from spatial
```
$SPATIAL_HOME/bin/spatial <App> --cgra+
```
Run pir compiler
```
$PIR_HOME/bin/pir <App> [args] [--options]
```
## Options
To see all possible options, run
```
$PIR_HOME/bin/pir <app> --help
```
Optionally all options can also been set from a config file .prismrc under $PIR_HOME.
Commend line option will override config file option. Example config
```
mapping=true
stat=true # print out CU statistics

# routing algo
routing-algo=dor # dimentional order routing

# architectural configs
topo=mesh
net=dynamic
row=4
col=4
```

To visualize the top level data flow graph
```
$PIR_HOME/bin/dot out/<App>/simple
```
To visualize the detailed data flow graph
```
$PIR_HOME/bin/dot out/<App>/top
```
To visualize the mapping
```
$PIR_HOME/bin/dot -c out/<App>/control
$PIR_HOME/bin/dot -c out/<App>/scalar
$PIR_HOME/bin/dot -c out/<App>/vector
```
Run spade compiler
```
$PIR_HOME/bin/spade <Arch> [--options]
```
To visualize the architecture
```
$PIR_HOME/bin/dot -c out/<Arch>/control
$PIR_HOME/bin/dot -c out/<Arch>/scalar
$PIR_HOME/bin/dot -c out/<Arch>/vector
```

## Compiler Options
```
--ag-dce: Enable aggressive dead code elimination [default=true]
--ctrl: Enable control logic generation [default=true]
--splitting: Enable splitting [default=true]
--splitting-algo: splitting algorithm. [weighted_igraph, alias_igraph, alias_weighted_igraph] [default=alias_weighted_igraph]
--mapping: Enable mapping [default=true]
--codegen: Enable codegen [default=true]
--out: Relative output directory for pir compiler. Relative to $PIR_HOME [default=out]
--save-pir: Save IR into a file [default=false]
--load-pir: Load IR from a file [default=false]
--save-spade: Save IR into a file [default=false]
--load-spade: Load IR from a file [default=false]
--stat: Printing CU statistics [default=false]
```

## Debug Options
```
--verbose: Enter verbose mode [default=false]
--debug: Enable debug [default=true]
--open: Open dot graph after codegen [default=false]
--snapint: Placement snapshot interval [default=10]
--snapshot: Enable placement snapshot [default=false]
--bp: Enable break point [default=false]
--dot: Enable dot codegen [default=true]
```

## Architectural Parameters
```
--arch: Default architecture for mapping [default=MyDesign]
--word: Word width [default=32]
--vec: Vector width of SIMD lanes and vector network [default=16]
--net: Network type [dynamic, static, asic] [default=static]
--nn: enable nearest neighbor [default=false]
--topo: Network topology [mesh, torus, cmesh] [default=mesh]
--row: number of rows in network [default=2]
--col: number of columns in network [default=2]
--pattern: Pattern in layout of different CU types. For topo=[mesh/torus] - [checkerboard,cstrip,rstrip,mixall,half&half], for topo=cmesh - [checkerboard] [default=checkerboard]
--dag: enable dram address generator in network [default=true]
--fifo-depth: Depth of FIFO for all CUs [default=4]
--argout: number of ArgOut [default=4]
--argin: number of ArgIn [default=6]
--tokenout: number of TokenOut [default=5]
```

# Place and route
```
--routing-algo: If net=[dynamic] - [dor, planed, proute]. Option ignored for other network. dor - dimention order routing. planed - arbitrary source routing, proute - use plastiroute for place and route. If proute is chosen plastiroute will be launched from pir if $PLASTIROUTE_HOME is set [default=dor]
--routing-cost: Routing cost [hop, balanced, H-hop, H-balanced]. hop - use hop count only for cost in search, balanced - use traffic load + hop count as cost, H-hop: use Manhattan distance as heurisc cost and use hop count for cost. H-balanced: use Manhattan distance as heurisc cost and use hop count and traffic load for cost. [default=H-hop]
```

# Plastisim
Install plastisim
git clone https://github.com/acrucker/plastisim
set $PLASTISIM_HOME
cd $PLASTISIM_HOME
git submodule update --init
make
```
--psim: Enable code generations for plastisim [default=true]
--psim_out: Directory to copy psim files over 
--run-psim: Launch Plastisim simulation [default=false]
--trace: Enable trace generation for simulation [default=false]
```

