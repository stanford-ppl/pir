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
git checkout --track origin/pir
make pir
```

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

## Options
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
```


