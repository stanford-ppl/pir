package spade

class SpadeConfig(compiler:Spade) extends prism.Config(compiler) {

  register("save-spade", default=false, info="Save IR into a file")
  register("load-spade", default=false, info="Load IR from a file")
  register("simulate", false, info="Enable simulation")
  register("waveform", true, info="Enable waveform")
  register("time-out", 100, info="Simulation time out after X cycles")
  register("open", default=false, info="Open dot graph after codegen")
  register("stat", default=false, info="Printing statistics")

  def simulate:Boolean = option[Boolean]("simulate")
  def waveform:Boolean = option[Boolean]("waveform")
  def simulationTimeOut:Int = option[Int]("time-out")
  def openDot = option[Boolean]("open")
  def printStat = option[Boolean]("stat")
  
  /* ------------------ Architecture parameters ---------------- */
  register[Int]("word", default=32, info="Word width")
  register[Int]("vec", default=16, info="Vector width of SIMD lanes and vector network")
  register[Int]("pmu-sram-size", default=64*1024, info="SRAM capacity in PMU in word.")
  register[String]("net", default="static", info="Network type [dynamic, static, asic, p2p]")
  register[String]("topo", default="mesh", info="Network topology [mesh, torus, cmesh]")
  register[Int]("row", default=2, info="number of rows in network")
  register[Int]("col", default=2, info="number of columns in network")
  register[Boolean]("nn", default=false, info="enable nearest neighbor")
  register[Boolean]("dag", default=true, info="enable dram address generator in network")
  register[String]("pattern", default="checkerboard", info="Pattern in layout of different CU types. For topo=[mesh/torus] - [checkerboard,cstrip,rstrip,mixall,half&half], for topo=cmesh - [checkerboard]")
  register[Int]("argin", default=6, info="number of ArgIn")
  register[Int]("argout", default=4, info="number of ArgOut")
  register[Int]("tokenout", default=5, info="number of TokenOut")
  register[Int]("fifo-depth", default=4, info="Depth of FIFO for all CUs")
  register[Int]("vfifo", default=4, info="Number of vector FIFO within Terminal")
  register[Int]("vlink", default=2, info="Number of vector link between switches")
  register[Int]("slink", default=4, info="Number of scalar link between switches")
  register[Int]("pcu-vfifo", info="Number of vector fifo in pcu")
  register[Int]("pcu-sfifo", info="Number of scalar fifo in pcu")
  register[Int]("pcu-cfifo", info="Number of scalar fifo in pcu")
  register[Int]("pmu-vfifo", info="Number of vector fifo in pmu")
  register[Int]("pmu-sfifo", info="Number of scalar fifo in pmu")
  register[Int]("pmu-cfifo", info="Number of scalar fifo in pmu")
  register[Int]("vc", default=4, info="Number of virtual classes per network")
  register[String]("link-prop", default="db", info="[db-double buffered, cd-credit based]")
  register[Int]("flit-width", default=512, info="Flit width for dynamic network")

}
