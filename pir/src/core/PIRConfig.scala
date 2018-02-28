package pir

import prism._

import scala.collection.mutable
import java.io._

object PIRConfig extends GlobalConfig {
  import Config._

  var arch:String = register("arch", default="MeshCB2x2", info="Default architecture for mapping") { arch = _ }
  var saveDesign:Boolean = register("save-pir", default=false, info="Save IR into a file") { saveDesign = _ }
  var loadDesign:Boolean = register("load-pir", default=false, info="Load IR from a file") { loadDesign = _ }

  var test:Boolean = register("test", default=false) { test = _ }
  var genDot:Boolean = register("dot", default=true) { genDot = _ }
  var mapping:Boolean = register("mapping", default=true) { mapping = _ }
  var ctrl:Boolean = register("ctrl", default=true) { ctrl = _ }
  def codegen = Config.codegen
  def verbose = Config.verbose

  var genPisa:Boolean = register("pisa", default=false) { genPisa = _ } && codegen
  var quick:Boolean = register("quick", default=false) { quick = _ }

  var pirFile:String = register("pirlog", default="PIR.log") { pirFile = _ }
  var pirDot:String = register("pirdot", default="PIR.dot") { pirDot = _ }
  var pirCtrlDot:String = register("prismtrldot", default="PIRCtrl.dot") { pirCtrlDot = _ }
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
