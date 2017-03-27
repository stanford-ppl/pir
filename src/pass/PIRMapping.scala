package pir.pass
import pir.graph._
import pir._
import pir.codegen.Printer
import pir.mapper._
import pir.exceptions._
import scala.util.{Try, Success, Failure}
import pir.util.misc._

object MapPrinter extends Printer { 
  def shouldRun = Config.debug

  override lazy val stream = newStream(Config.mapFile)
  def printMap(mapping:PIRMap)(implicit design:Design) = {
    if (Config.debug) {
      emitTitleComment(s"Mapping")
      mapping.printPMap(this, design)
    }
  }

  def printException(e:PIRException) = {
    if (Config.debug) {
      emitTitleComment("Mapping Exceptions:")
      emitln(s"$e ${e.msg} \n ${e.printStackTrace}")
    }
  }
}

class PIRMapping(implicit val design: Design) extends Pass{
  def shouldRun = Config.mapping

  var mapping:PIRMap = _
  var succeeded = false

  def failed = !succeeded && Config.mapping

  val siMapper = new ScalarInMapper()
  val sramMapper = new SRAMMapper()
  val stageMapper = new StageMapper()
  val outputMapper = new OutputMapper()
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

  def mapPrim(ctrler:Controller)(m:PIRMap):PIRMap = {
    var cmap = m
    cmap = siMapper.map(ctrler, cmap)
    ctrler match {
      case cu:InnerController => 
        cu match {
          case mc:MemoryController =>
          case _ => cmap = sramMapper.map(cu, cmap)
        }
        cmap = ctrMapper.map(cu, cmap)
      case t:Top => cmap = ctrlMapper.map(t, cmap)
      case _ => assert(false, s"Unknown ctrler:$ctrler")
    }
    cmap
  }

  val cuMapper:CUMapper = new CUMapper() {
    override def finPass(m:PIRMap) = {
      //try {
        //m.clmap.map.foldLeft(m) { case (pm, (ctrler, _)) =>
          //mapPrim(ctrler)(pm)
        //}
      //} catch {
        //case e:MappingException => throw PassThroughException(cuMapper, e, m)
        //case e:Throwable => throw e 
      //} 
      m
    }
  }

  override def reset = {
    mapping = null
    succeeded = false
  }

  override def traverse = {
    tic
    Try[PIRMap](cuMapper.map(PIRMap.empty)).map { m =>
      mapping = m
      design.mappers.foreach{ _.mappingCheck(m) }
    } match {
      case Success(_) =>
        succeeded = true
        info(s"Mapping succeeded") 
        MapPrinter.printMap(mapping)
      case Failure(e) =>
        succeeded = false
        info(s"Mapping failed")
        e match {
          case e:OutOfResource[_] =>
            err(e)
            MapPrinter.printException(e)
            mapping = e.mapping.asInstanceOf[PIRMap]
          case ExceedExceptionLimit(mapper, m) =>
            err(s"$e")
            mapping = m.asInstanceOf[PIRMap]
          case PassThroughException(mapper, e, m) =>
            MapPrinter.printMap(m)
            MapPrinter.printException(e)
            mapping = m
          case e:MappingException[_] =>
            MapPrinter.printException(e)
            mapping = e.mapping.asInstanceOf[PIRMap]
          case e:PIRException => 
            MapPrinter.printException(e)
          case e => throw e 
        }
    }
    toc(s"Mapping", "s")
    design.mapperLogger.close
    MapPrinter.close
  } 

  override def initPass:Unit = {
    info(s"Start mapping...")
  }

  override def finPass = {
    design.mapperLogger.close
    info("Finishing PIR Mapping")
  }
}
