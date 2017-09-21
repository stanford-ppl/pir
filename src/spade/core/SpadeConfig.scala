package spade

import pirc._

import scala.collection.mutable

object SpadeConfig extends GlobalConfig {

  // Properties go here
  var test:Boolean = register("test", false) { v => test = v == "true" }
  var codegen:Boolean = register("codegen", false) { v => codegen = v == "true" }
  var genDot:Boolean = register("dot", true) { v => genDot = v == "true" }
  var simulate:Boolean = register("simulate", false) { v => simulate = v == "true" }
  var verbose:Boolean = register("verbose", false) { v => verbose = v == "true" }
  var waveform:Boolean = register("waveform", true) { v => waveform = v == "true" }
  var simulationTimeOut:Int = register("time-out", 100) { v => simulationTimeOut = v.toInt }

  var quick = getProperty("pir.quick", "false") == "true"
  var outDir = getProperty("pir.outDir", "out")

  var spadeFile = getProperty("spadefile", "Spade.log")
  //var pisaFile = getProperty("pir.pisafile", "pisa.json")
  var spadeVectorNetwork = getProperty("spade_vector_network", "VecNetwork.dot")
  var spadeScalarNetwork = getProperty("spade_scalar_network", "ScalNetwork.dot")
  var spadeCtrlNetwork = getProperty("spade_ctrl_network", "CtrlNetwork.dot")
  var spadeArgInOut = getProperty("spade_arginout", "ArgInOut.dot")
  var spadeCtr = getProperty("spade_ctr", "PCtr.dot")

  def debug = Config.debug
  var debugCodegen:Boolean = debug && register("debug-codegen", true) { v => debugCodegen = v == "true" }
  
}
