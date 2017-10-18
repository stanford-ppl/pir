package pir

import pirc._

import scala.collection.mutable

object PIRConfig extends GlobalConfig {

  // Properties go here
  var test:Boolean = register("test", false) { v => test = v == "true" }
  var genDot:Boolean = register("dot", true) { v => genDot = v == "true" }
  var mapping:Boolean = register("mapping", true) { v => mapping = v == "true" }
  var ctrl:Boolean = register("ctrl", true) { v => ctrl = v == "true" }
  def codegen = Config.codegen
  def verbose = Config.verbose

  var genPisa = getProperty("pir.pisa", "false") == "true" && codegen
  var quick = getProperty("pir.quick", "false") == "true"

  var pirFile = getProperty("pir.pirfile", "PIR.log")
  var pirDot = getProperty("pir.pir_network", "PIR.dot")
  var pirCtrlDot = getProperty("pir.pir_ctrl_network", "PIRCtrl.dot")
  var spadeFile = getProperty("spadefile", "Spade.log")
  //var pisaFile = getProperty("pir.pisafile", "pisa.json")
  var ctrlDot = getProperty("pir.ctrl_dot", "Ctrl.dot")
  var ctrlFile = getProperty("pir.ctrl_file", "Ctrl.log")
  var mapFile = getProperty("pir.mapfile", "Mapping.log")
  var mapperLog = getProperty("pir.mapperLog", "Mapper.log")
  var debugLog = getProperty("pir.debugLog", "Debug.log")

  def debug = Config.debug
  def debugCodegen = Config.debugCodegen
  var debugMapper:Boolean = debug && register("debug-mapper", true) { v => debugMapper = v == "true" }
  var debugRouting:Boolean = debugMapper && register("routing", false) { v => debugRouting = v == "true" }
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
  var openDot:Boolean = register("open", false) { v => openDot = v == "true" }

}
