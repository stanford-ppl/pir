package pir.pass
import pir.graph._
import pir._
import pir.codegen.{Printer, Logger}
import pir.mapper._
import pir.exceptions._
import scala.util.{Try, Success, Failure}
import pir.util.misc._

class PIRMapping(implicit design: Design) extends Pass with Logger {

  override lazy val stream = newStream(Config.mapFile)

  def shouldRun = Config.mapping

  var mapping:PIRMap = _
  var succeeded = false

  def failed = !succeeded && Config.mapping

  val sramMapper = new SramMapper()
  val vfifoMapper = new VFifoMapper()
  val sfifoMapper = new SFifoMapper()
  val stageMapper = new StageMapper()
  val outputMapper = new OutputMapper()
  val ctrlMapper = new CtrlMapper()
  val regAlloc = new RegAlloc() {
    override def finPass(ctrler:InnerController)(m:M):M = { 
      var mp = m
      mp = ctrlMapper.map(ctrler, mp)
      mp = stageMapper.map(ctrler, mp)
      mp
    }
  }
  val ctrMapper = new CtrMapper() { 
    override def finPass(ctrler:ComputeUnit)(m:M):M = { 
      var mp = m
      mp = regAlloc.map(ctrler, mp)
      mp
    }
  }
  def mapPrim(ctrler:Controller)(m:PIRMap):PIRMap = {
    var mp = m
    mp = sramMapper.map(ctrler, mp)
    mp = vfifoMapper.map(ctrler, mp) 
    mp = sfifoMapper.map(ctrler, mp)
    mp = ctrMapper.map(ctrler, mp)
    mp
  }

  val cuMapper:CUMapper = new CUMapper() {
    override def finPass(m:PIRMap) = {
      try {
        m.clmap.map.foldLeft(m) { case (pm, (ctrler, _)) =>
          mapPrim(ctrler)(pm)
        }
      } catch {
        case e:MappingException[_] => throw PassThroughException(cuMapper, e, m)(design)
        case e:Throwable => throw e 
      } 
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
        printMap(mapping)
      case Failure(e) =>
        succeeded = false
        info(s"Mapping failed")
        e match {
          case e:OutOfResource[_] =>
            err(e)
            printException(e)
            mapping = e.mapping.asInstanceOf[PIRMap]
          case ExceedExceptionLimit(mapper, m) =>
            err(s"$e")
            mapping = m.asInstanceOf[PIRMap]
          case PassThroughException(mapper, e, m) =>
            printMap(m)
            printException(e)
            mapping = m
          case e:MappingException[_] =>
            printException(e)
            mapping = e.mapping.asInstanceOf[PIRMap]
          case e:PIRException => 
            printException(e)
          case e => throw e 
        }
    }
    toc(s"Mapping", "s")
    design.mapperLogger.close
  } 

  override def initPass:Unit = {
    info(s"Start mapping...")
  }

  override def finPass = {
    design.mapperLogger.close
    close
    super.finPass
  }

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
