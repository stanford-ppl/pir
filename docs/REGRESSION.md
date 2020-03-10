[README](../README.md)

# Regression

### Running Plasticine regression tests
```
cd spatial/pir
bin/regression -b Tst -p pirTest -t <#parallel apps>
```

The regression script should generates a regression summary after run in `spatial/pir/logs`. 
If regression passed, please check in this file to pir repository. 

### Running Plasticine regression tests with FPGA regression tests. 
Only about 50% of the tests work on Plasticine.

```
cd spatial/pir
bin/regression -b Tst -p test -t <#parallel apps>
```

### Running regression on slurm machines. 

The Plasticine regression tests take about 1.5 hours to run.
```
srun -c 40 --mem=150 regression_command
```
When running regression on the slurm machines, the script will create a symlink to the generated
directory on those machines named `gen-slurmX` in the running directory.
You can use this symlink to check status of the regression job

### Status checking on the regression job
```
bin/log <gendir> 

# example

bin/log gen/Tst_pirTest
```

To see if there is any change with previous regression runs, the following command will print
the new regression apps and new working apps compared to 20 previous logs.
```
bin/log <gendir> -d
```
