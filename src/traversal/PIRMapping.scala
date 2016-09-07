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

  val siMapper = new ScalarInMapper()
  val sramMapper = new SRAMMapper()
  val regAlloc = new RegAlloc()
  val stageMapper = new StageMapper()
  val soMapper = new ScalarOutMapper()
  val viMapper = new VecInMapper()
  val ctrlMapper = new CtrlMapper() {
    override def finPass(cu:ComputeUnit)(m:M):M =  { stageMapper.map(cu, regAlloc.map(cu, m)) }
  }
  val ctrMapper = new CtrMapper() { 
    override def finPass(cu:ComputeUnit)(m:M):M = ctrlMapper.map(cu, m)
  }
  val cuMapper = new CUMapper(soMapper, viMapper) {
    override def finPass(m:M):M = {
      var cmap = m 
      Try {
        cmap.clmap.map.foldLeft(cmap) { case (pm, (ctrler, v)) =>
          cmap = siMapper.map(ctrler, cmap)
          ctrler match {
            case cu:ComputeUnit =>
              cmap = sramMapper.map(cu, cmap)
              cmap = ctrMapper.map(cu, cmap)
            case _ => pm
          }
          cmap
        }
      } match {
        case Success(m) => return m
        // TODO: at the moment if prim failed. stop trying
        case Failure(e) =>
          e.printStackTrace
          MapPrinter.printMap(cmap)(design)
          System.exit(-1)
          throw e
      }
    }
  }

  override def reset = {
    mapping = null
    success = false
  }

  override def traverse = {
    Try(mapping = cuMapper.map(PIRMap.empty)) match {
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
