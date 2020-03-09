[README](../README.md)

# Instrumentation

## Compiler Flags for Debugging

- `-T` when running `bin/test`. This flag prints logs for each pass, which can make it easier to
  tell which pass the error is coming from. You can also track status and error message of each pass
  with `bin/log <gendir>`. More detail in [Quick Start](QUICKSTART.md).

- `--debug` PIR flag. Turn on logging in pir. Recommend to turn off in batch jobs.

- `--dot` and `--vdot` PIR flag. Generate dot graph. Recommend to turn off in batch jobs.

All PIR flags can be specified when running `bin/test` or within `$HOME/.pirconf`.

## Logs
When using bin/test to run spatial app, there will be a summary log for each of the pass described
in [Quick Start](QUICKSTART.md) in `<gendir>/log`, e.g.
`spatial/gen/Tst_pirTest/DotProduct_0/log/genpir.log`.  `bin/log` parses these logs to produce a
summary of useful informations.

Spatial produces one log file per spatial pass name 00XX-PassName.log in the same directory, e.g.
0000_Staging.log 

PIR also produces a per PIR pass log file when enable logging with `--debug`, and the logs are in 
`<gendir>/pir/logs/`.

## Visualization

Both spatial and PIR have visualization of the IRs. Spatial dot graph is enabled by default if
running with `bin/test -b Tst`. Otherwise it can be turned on with `--dot` when running spatial with
`bin/spatial`.
PIR `--dot` enables the CU-level dot graph and `--vdot` enables
the context- and IR-level dot graph, which can run really slowly for large apps.

### Spatial Visualization
After spatial compilation (genpir pass), the spatial IR is shown as a hierarchical dot graph at each
block level in `<gendir>/info/`. The `Top.html` is the top-level IR under main function scope in
spatial. The red ovals are Block node. Clicking on those node will navigates to scope within the
block. Generally you want to look for the `Accel` block within `Top.html` to show IRs within the
Accel block.

![Spatial Dot Graph](figs/Top.html) 

Spatial also contains an expandable controller tree diagram in the same directory called `controller_tree.html`, 
which shows a more concise view of the controller hierarchy.
