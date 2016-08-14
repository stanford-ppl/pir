package pir.graph.traversal
import pir.graph._
import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.graph.mapper._
import scala.util.{Try, Success, Failure}

object MapPrinter extends Printer { 
  override val stream = newStream(Config.mapFile)
  def printMap(mapping:PIRMap)(implicit design:Design) = {
    if (Config.debug) {
      MapPrinter.emitTitleComment(s"Mapping")
      mapping.printPMap(MapPrinter, design)
      MapPrinter.close
    }
  }

  def printException(e:PIRException) = {
    if (Config.debug) {
      MapPrinter.emitTitleComment("Mapping Exceptions:")
      MapPrinter.emitln(e.toString)
      MapPrinter.close
    }
  }

}
class PIRMapping(implicit val design: Design) extends Traversal{

  var mapping:PIRMap = _
  var success = false

  def failed = !success

  override def reset = {
    mapping = null
    success = false
    CUMapper.setDesign(design)
    CtrMapper.setDesign(design)
    CtrlMapper.setDesign(design)
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
        success = true
        info(s"Mapping succeeded") 
        MapPrinter.printMap(mapping)
      case Failure(e) => 
        success = false
        e match {
        case me:MappingException =>
          info(s"Mapping failed")
          MapPrinter.printException(me)
        case _ => throw e
      }
    }
  } 

  override def finPass = {
    info("Finishing PIR Mapping")
  }
}
