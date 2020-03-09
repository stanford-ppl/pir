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
            op=[1,2], 
            N=1024*100, 
            pirArgs=["--split-algo=dfs --retime-glob=true " + \
                f"--pcu-stage={pcuStage}" for pcuStage in  [5,6]],
        )

dse = DSE(logdir="logs", project="spatialApps")
dse.setup_dse(gen_apps)
```

Run `bin/dse -g` will generates `src/DSE.scala`

```
import spatial.dsl._
import forge.tags._

@virtualize
class Dummy2
class BlackScholes_D0 extends BlackScholes(ip=16,op=1,N=102400){ override def pirArgs = super.pirArgs + " --split-algo=dfs --retime-glob=true --pcu-stage=5";  }
class BlackScholes_D1 extends BlackScholes(ip=16,op=1,N=102400){ override def pirArgs = super.pirArgs + " --split-algo=dfs --retime-glob=true --pcu-stage=6";  }
class BlackScholes_D2 extends BlackScholes(ip=16,op=2,N=102400){ override def pirArgs = super.pirArgs + " --split-algo=dfs --retime-glob=true --pcu-stage=5";  }
class BlackScholes_D3 extends BlackScholes(ip=16,op=2,N=102400){ override def pirArgs = super.pirArgs + " --split-algo=dfs --retime-glob=true --pcu-stage=6";  }  
```

To run apps, run `bin/dse -a BlackScholes_D* [options]`

Options used by `bin/test` can be used in `bin/dse` options. To override default pir options, 
set `dse.pirArgs = 'new options'` before calling `dse.setup_dse`

