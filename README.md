# pir

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

Optionally add config file .prismrc under $PIR_HOME
```
load-pir=false
save-pir=true
load-spade=false
save-spade=false
arch=DMeshCB4x4
verbosity-routing=1
bp=false
mapping=true
```

To generate app from spatial
```
$SPATIAL_HOME/bin/spatial <App> --cgra+
```
Run pir compiler
```
$PIR_HOME/bin/pir <App> [args] [--options]
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
