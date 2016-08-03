package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper._
import scala.util.{Try, Success, Failure}

object MapPrinter extends Printer { override val stream = newStream(Config.mapFile) }
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
    RegAlloc.setDesign(design)
    StageMapper.setDesign(design)
  }

  override def traverse = {
    Try(mapping = CUMapper.map) match {
      case Success(_) =>
        info(s"Mapping succeeded") 
        if (Config.debug) {
          MapPrinter.emitTitleComment(s"Mapping")
          mapping.printMap(MapPrinter)
        }
      case Failure(e) => e match {
        case me:MappingException =>
          info(s"Mapping failed")
          MapPrinter.emitln("Mapping Exceptions:")
          MapPrinter.emitln(me)
        case _ => throw e
      }
    }
  } 

  override def finPass = {
    info("Finishing PIR Mapping")
    MapPrinter.close
  }
}
