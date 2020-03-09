[README](../README.md)

# DSE
Since Plasticine currently does not have a runtime and area model, the only way to perform DSE of a
application is run various configuration of the appilcation through the compiler and simulation.
[`spatial/pir/bin/dse_util.py`](https://github.com/stanford-ppl/pir/blob/master/bin/dse_util.py) 
provides some helper function for easy generation of these parameters. 

Example usage in 
[here](https://github.com/stanford-ppl/spatial-quickstart-submodule/blob/master/bin/dse). 

```
from dse_util import *

def gen_apps():
    with dseopen("src/DSE.scala") as f:
        dseApp("BlackScholes", 
            ip=16, 
            op=1, 
            N=1024*100, 
            pirArgs=["--split-algo=dfs --retime-glob=true " + \
                "--pcu-vin={} --pcu-vout={}".format(pcuVin, pcuVout) 
                for pcuVin in  [3,4]
                for pcuVout in [1,2]],
        )

dse = DSE(logdir="logs", project="spatialApps")
dse.setup_dse(gen_apps)
```

To generate apps, run `bin/dse -g`. 
To run apps, run `bin/dse [options]`

Options used by `bin/test` can be used in `bin/dse` options. To override default pir options, 
set `dse.pirArgs = 'new options'` before calling `dse.setup_dse`

