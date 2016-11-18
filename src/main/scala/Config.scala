package pir

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

  // Properties go here
  var test = getProperty("pir.test", "false") == "true"
  var genDot = getProperty("pir.dot", "true") == "true"
  var mapping = getProperty("pir.mapping", "false") == "true"
  var modeling = getProperty("pir.modeling", "true") == "true"
  var genPisa = getProperty("pir.pisa", "true") == "true"
  var quick = getProperty("pir.quick", "false") == "true"
  var outDir = getProperty("pir.outDir", "out")
  var ctrl = false

  var pirFile = getProperty("pir.pirfile", "PIR.txt")
  var pirStat = getProperty("pir.pirstat", "Stat.txt")
  var pirNetworkDot = getProperty("pir.pir_network", "PIRNetwork.dot")
  var pirCtrlNetworkDot = getProperty("pir.pir_ctrl_network", "PIRCtrlNetwork.dot")
  var spadeFile = getProperty("pir.spadefile", "Spade.txt")
  //var pisaFile = getProperty("pir.pisafile", "pisa.json")
  var spadeNetwork = getProperty("pir.spade_network", "Network.dot")
  var spadeCtrlNetwork = getProperty("pir.spade_ctrl_network", "CtrlNetwork.dot")
  var spadeArgInOut = getProperty("pir.spade_arginout", "ArgInOut.dot")
  var ctrlDot = getProperty("pir.ctrl_dot", "Ctrl.dot")
  var ctrlFile = getProperty("pir.ctrl_file", "Ctrl.txt")
  var spadeCtr = getProperty("pir.spade_ctr", "PCtr.dot")
  var mapFile = getProperty("pir.mapfile", "Mapping.txt")
  var mapperLog = getProperty("pir.mapperLog", "Mapper.log")
  var debugLog = getProperty("pir.debugLog", "Debug.log")

  var debug = getProperty("pir.debug", "true") == "true"
  var debugMapper = debug && getProperty("pir.debugMapper", "true") == "true"
  var debugCUMapper = debugMapper && true 
  var debugSOMapper = debugMapper && true 
  var debugSIMapper = debugMapper && true 
  var debugVIMapper = debugMapper && true 
  var debugCtrlMapper = debugMapper && true 
  var debugSMMapper = debugMapper && true 
  var debugCTMapper = debugMapper && true 
  var debugRAMapper = debugMapper && true 
  var debugSTMapper = debugMapper && true 
  var debugCodegen = debug && getProperty("pir.debugCodegen", "true") == "true"

  if (quick) {
    genPisa = false
    genDot = false
    test = false
  }
}
