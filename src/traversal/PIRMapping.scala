package pir.graph.traversal
import pir.graph._
import pir._
import pir.codegen.Printer
import pir.misc._
import pir.graph.mapper._
import scala.util.{Try, Success, Failure}

object MapPrinter extends Printer { 
  override val stream = newStream(Config.mapFile)
  def printMap(mapping:PIRMap)(implicit design:Design) = {
    if (Config.debug) {
      emitTitleComment(s"Mapping")
      mapping.printPMap(this, design)
      close
    }
  }

  def printException(e:PIRException) = {
    if (Config.debug) {
      emitTitleComment("Mapping Exceptions:")
      emitln(e.toString)
      close
    }
  }
}

class PIRMapping(implicit val design: Design) extends Traversal{

  var mapping:PIRMap = _
  var success = false

  def failed = !success

  val siMapper = new ScalarInMapper()
  val sramMapper = new SRAMMapper()
  val stageMapper = new StageMapper()
  val outputMapper = new OutputMapper()
  val viMapper = new VecInMapper()
  val ctrlMapper = new CtrlMapper()
  val regAlloc = new RegAlloc() {
    override def finPass(cu:InnerController)(m:M):M = { stageMapper.map(cu, m) }
  }
  val ctrMapper = new CtrMapper() { 
    override def finPass(cu:InnerController)(m:M):M = { 
      var cmap = ctrlMapper.map(cu, m)
      regAlloc.map(cu, cmap)
    }
  }
  val cuMapper:CUMapper = CUMapper(outputMapper, viMapper, { case (m:PIRMap) =>
    var cmap = m 
    Try {
      cmap.clmap.map.foldLeft(cmap) { case (pm, (ctrler, v)) =>
        cmap = siMapper.map(ctrler, cmap)
        ctrler match {
          case cu:InnerController => 
            cmap = sramMapper.map(cu, cmap)
            //cmap = ctrMapper.map(cu, cmap)
            //cmap = stageMapper.map(cu, cmap)
          case t:Top => 
          case _ => assert(false, s"Unknown ctrler:$ctrler")
        }
        cmap
      }
    } match {
      case Success(m) => 
        dprintln(s"Final Pass: Primitive Mapping (succeeded)"); m
      case Failure(e) => 
        dprintln(s"Final Pass: Primitive Mapping (failed) Exception:")
        e match {
          case e:PIRException => dprintln(e)
          e.printStackTrace
          case e => e.printStackTrace 
        }
        MapPrinter.printMap(cmap)(design)
        MapperLogger.flush
        design.cuDotPrinter.print(cmap)
        System.exit(-1) // TODO: at the moment if prim failed. stop trying because CUs 
                        // are homogenous
        throw e
    }
  })

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
          me.printStackTrace
        case pe:PIRException => 
          MapperLogger.close
          throw e
        case _ => throw e
      }
    }
  } 

  override def finPass = {
    MapperLogger.close
    info("Finishing PIR Mapping")
  }
}
