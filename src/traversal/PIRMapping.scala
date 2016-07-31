package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper._
import scala.util.{Try, Success, Failure}

class PIRMapping(implicit val design: Design) extends Traversal{

  var mapping:PIRMap = _

  override def reset = {
    mapping = null
    CUMapper.setDesign(design)
    CtrMapper.setDesign(design)
    SRAMMapper.setDesign(design)
    ScalarInMapper.setDesign(design)
    ScalarOutMapper.setDesign(design)
    VecInMapper.setDesign(design)
  }

  val p:Printer = new Printer { override val stream = newStream(Config.mapFile) }
  override def traverse = {
    Try(mapping = CUMapper.map) match {
      case Success(_) =>
        info(s"Mapping succeeded") 
        if (Config.debug) {
          mapping.printMap(p)
        }
      case Failure(e) => e match {
        case me:MappingException =>
          info(s"Mapping failed")
          p.emitln("Mapping Exceptions:")
          p.emitln(me)
        case _ => throw e
      }
    }
  } 

  override def finPass = {
    if (Config.debug) {
      if (mapping!=null) mapping.printMap(p)
      info(s"Mapping printed in ${p.getPath}")
    }
    info("Finishing PIR Mapping")
    p.close
  }
}
