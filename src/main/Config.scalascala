package pir

import scala.collection.mutable

object Config {
  private def getProperty(prop: String, default: String) = {
    val p1 = System.getProperty(prop)
    val p2 = System.getProperty(prop.substring(1))
    if (p1 != null && p2 != null) {
      assert(p1 == p2, "ERROR: conflicting properties")
      p1
    }
    else if (p1 != null) p1 else if (p2 != null) p2 else default
  }

  val optMap = mutable.Map[String, String => Any]()

  def register[T](key:String, default:T)(update:String => Unit) = {
    optMap += key -> update 
    default
  }

  def setOption(opt:String):Unit = {
    val (key, value) = if (opt.contains("=")) {
      val key::value::_ = opt.split("=").toList
      (key, value)
    } else {
      (opt, "true")
    }
    optMap(key)(value)
  }

  // Properties go here
  var test:Boolean = register("test", false) { v => test = v == "true" }
  var codegen:Boolean = register("codegen", false) { v => codegen = v == "true" }
  var genDot:Boolean = register("dot", true) { v => genDot = v == "true" }
  var mapping:Boolean = register("mapping", true) { v => mapping = v == "true" }
  var ctrl:Boolean = register("ctrl", true) { v => ctrl = v == "true" }
  var simulate:Boolean = register("simulate", false) { v => simulate = v == "true" }
  var verbose:Boolean = register("verbose", false) { v => verbose = v == "true" }
  var waveform:Boolean = register("waveform", true) { v => waveform = v == "true" }
  var simulationTimeOut:Int = register("time-out", 100) { v => simulationTimeOut = v.toInt }

  var genPisa = getProperty("pir.pisa", "false") == "true" && codegen
  var quick = getProperty("pir.quick", "false") == "true"
  var outDir = getProperty("pir.outDir", "out")

  var pirFile = getProperty("pir.pirfile", "PIR.log")
  var pirDot = getProperty("pir.pir_network", "PIR.dot")
  var pirCtrlDot = getProperty("pir.pir_ctrl_network", "PIRCtrl.dot")
  var spadeFile = getProperty("pir.spadefile", "Spade.log")
  //var pisaFile = getProperty("pir.pisafile", "pisa.json")
  var spadeVectorNetwork = getProperty("pir.spade_vector_network", "VecNetwork.dot")
  var spadeScalarNetwork = getProperty("pir.spade_scalar_network", "ScalNetwork.dot")
  var spadeCtrlNetwork = getProperty("pir.spade_ctrl_network", "CtrlNetwork.dot")
  var spadeArgInOut = getProperty("pir.spade_arginout", "ArgInOut.dot")
  var ctrlDot = getProperty("pir.ctrl_dot", "Ctrl.dot")
  var ctrlFile = getProperty("pir.ctrl_file", "Ctrl.log")
  var spadeCtr = getProperty("pir.spade_ctr", "PCtr.dot")
  var mapFile = getProperty("pir.mapfile", "Mapping.log")
  var mapperLog = getProperty("pir.mapperLog", "Mapper.log")
  var debugLog = getProperty("pir.debugLog", "Debug.log")

  var debug:Boolean = register("debug", true) { v => debug = v == "true" }
  var debugMapper:Boolean = debug && register("debug-mapper", true) { v => debugMapper = v == "true" }
  var debugVecRouter = debugMapper && true 
  var debugScalRouter = debugMapper && true 
  var debugCtrlRouter = debugMapper && false 
  var debugCUMapper = debugMapper && true 
  var debugSOMapper = debugMapper && true 
  var debugSIMapper = debugMapper && true 
  var debugVIMapper = debugMapper && true 
  var debugCtrlMapper = debugMapper && true 
  var debugSramMapper = debugMapper && true 
  var debugVFifoMapper = debugMapper && true 
  var debugSFifoMapper = debugMapper && true 
  var debugCtrMapper = debugMapper && true 
  var debugRAMapper = debugMapper && true 
  var debugSTMapper = debugMapper && true 
  var debugCodegen:Boolean = debug && register("debug-codegen", true) { v => debugCodegen = v == "true" }
  
//plasticine {
  //sin-ucu = 4
  //stages-ucu = 4
  //sin-pcu = 4
  //sout-pcu = 4
  //vin-pcu = 4
  //vout-pcu = 1
  //comp = 6
  //sin-pmu = 4
  //sout-pmu = 0
  //vin-pmu = 4
  //vout-pmu = 1
  //rw = 10
  //lanes = 16
//}

  if (quick) {
    genPisa = false
    genDot = false
    test = false
  }

  //case class PlasticineConf(
    //sinUcu: Int,
    //stagesUcu: Int,
    //sinPcu: Int,
    //soutPcu:Int,
    //vinPcu: Int,
    //voutPcu: Int,
    //regsPcu: Int,
    //comp: Int,
    //sinPmu: Int,
    //soutPmu:Int,
    //vinPmu: Int,
    //voutPmu: Int,
    //regsPmu: Int,
    //rw: Int,
    //lanes: Int
  //)
    //loadConfig[PlasticineConf](mergedPlasticineConf, "plasticine") match {
      //case Right(plasticineConf) =>
        //sIn_UCU = plasticineConf.sinUcu
        //stages_UCU = plasticineConf.stagesUcu
        //sIn_PCU = plasticineConf.sinPcu
        //sOut_PCU = plasticineConf.soutPcu
        //vIn_PCU = plasticineConf.vinPcu
        //vOut_PCU = plasticineConf.voutPcu
        //stages = plasticineConf.comp
        //regs_PCU = plasticineConf.regsPcu
        //sIn_PMU = plasticineConf.sinPmu
        //sOut_PMU = plasticineConf.soutPmu
        //vIn_PMU = plasticineConf.vinPmu
        //vOut_PMU = plasticineConf.voutPmu
        //readWrite = plasticineConf.rw
        //regs_PMU = plasticineConf.regsPmu
        //lanes = plasticineConf.lanes

      //case Left(failures) =>
        //error("Unable to read Plasticine configuration")
        //error(failures.head.description)
        //failures.tail.foreach{x => error(x.description) }
        //sys.exit(-1)
    //}
  //}

}
