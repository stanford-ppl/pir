package pir

import pirc._

import scala.collection.mutable

object PIRConfig extends GlobalConfig {

  // Properties go here
  var test:Boolean = register("test", false) { test = _ }
  var genDot:Boolean = register("dot", true) { genDot = _ }
  var mapping:Boolean = register("mapping", true) { mapping = _ }
  var ctrl:Boolean = register("ctrl", true) { ctrl = _ }
  def codegen = Config.codegen
  def verbose = Config.verbose

  var genPisa:Boolean = register("pisa", false) { genPisa = _ } && codegen
  var quick:Boolean = register("quick", false) { quick = _ }

  var pirFile:String = register("pirlog", "PIR.log") { pirFile = _ }
  var pirDot:String = register("pirdot", "PIR.dot") { pirDot = _ }
  var pirCtrlDot:String = register("pirctrldot", "PIRCtrl.dot") { pirCtrlDot = _ }
  //var pisaFile = register("pir.pisafile", "pisa.json")
  var ctrlDot:String = register("ctrldot", "Ctrl.dot") { ctrlDot = _ }
  var ctrlFile:String = register("ctrllog", "Ctrl.log") { ctrlFile = _ }
  var mapFile:String = register("mapfile", "Mapping.log") { mapFile = _ }
  var mapperLog:String = register("mapperLog", "Mapper.log") { mapperLog = _ }
  var debugLog:String = register("debugLog", "Debug.log") { debugLog = _ }

  def debug:Boolean = Config.debug
  def debugCodegen:Boolean = Config.debugCodegen
  var debugMapper:Boolean = debug && register("debug-mapper", true) { debugMapper = _ }
  var debugRouting:Boolean = debugMapper && register("routing", false) { debugRouting = _ }
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
  var openDot:Boolean = register("open", false) { openDot = _ }

}
