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
  var newMemAPI = getProperty("pir.newMemAPI", "false") == "true"
  var genScala = getProperty("pir.scala", "true") == "true"
  var genDot = getProperty("pir.dot", "true") == "true"
  var genMaxJ = getProperty("pir.maxj", "true") == "true"
  var quick = getProperty("pir.quick", "false") == "true"
  var outDir = getProperty("pir.outDir", "out")
  var pirFile = getProperty("pir.pirfile", "PIR.txt")
  var spadeFile = getProperty("pir.spadefile", "Spade.txt")
  var pisaFile = getProperty("pir.pisafile", "pisa.json")
  var spadeNetwork = getProperty("pir.spade_network", "Network.dot")
  var spadeArgInOut = getProperty("pir.spade_arginout", "ArgInOut.dot")
  var ctrlDot = getProperty("pir.ctrl_dot", "Ctrl.dot")
  var ctrlFile = getProperty("pir.ctrl_file", "Ctrl.txt")
  var spadeCtr = getProperty("pir.spade_ctr", "PCtr.dot")
  var mapFile = getProperty("pir.mapfile", "Mapping.txt")
  var mapping = getProperty("pir.mapping", "true") == "true"
  var mapperLog = getProperty("pir.mapperLog", "Mapper.log")
  var debug = getProperty("pir.debug", "true") == "true"
  var debugMapper = debug && getProperty("pir.debugMapper", "true") == "true"
  var debugCUMapper = debugMapper && true 
  var debugSOMapper = debugMapper && false 
  var debugVIMapper = debugMapper && false 
  var debugSMMapper = debugMapper && false 
  var debugCTMapper = debugMapper && true 
  var debugRAMapper = debugMapper && true 
  var debugCodegen = debug && getProperty("pir.debugCodegen", "false") == "true"

  var dse = false

  if (quick) {
    genMaxJ = false
    genDot = false
    genScala = false
    test = false
  }
}
