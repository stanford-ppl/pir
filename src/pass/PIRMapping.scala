package pir.pass
import pir.graph._
import pir._
import pir.codegen.{Printer, Logger, CUVectorDotPrinter, CUScalarDotPrinter, CUCtrlDotPrinter}
import pir.mapper._
import pir.exceptions._
import scala.util.{Try, Success, Failure}
import pir.util.misc._

class PIRMapping(implicit design: Design) extends Pass with Logger {

  override lazy val stream = newStream(Config.mapFile)

  def shouldRun = Config.mapping

  var mapping:Option[PIRMap] = None
  var succeeded = false

  def failed = !succeeded && Config.mapping

  val sramMapper = new SramMapper()
  val vfifoMapper = new VFifoMapper()
  val sfifoMapper = new SFifoMapper()
  val stageMapper = new StageMapper()
  val ctrlMapper = new CtrlMapper()
  val regAlloc = new RegAlloc() {
    override def finPass(ctrler:Controller)(m:M):M = { 
      var mp = m
      mp = ctrlMapper.map(ctrler, mp)
      mp = stageMapper.map(ctrler, mp)
      mp
    }
  }
  val ctrMapper = new CtrMapper() { 
    override def finPass(ctrler:Controller)(m:M):M = { 
      var mp = m
      mp = regAlloc.map(ctrler, mp)
      mp
    }
  }
  def mapPrim(ctrler:Controller)(m:PIRMap):PIRMap = {
    var mp = m
    mp = vfifoMapper.map(ctrler, mp) 
    mp = sramMapper.map(ctrler, mp)
    mp = sfifoMapper.map(ctrler, mp)
    mp = ctrMapper.map(ctrler, mp)
    mp
  }

  val spadeVecDotPrinter = new CUVectorDotPrinter()
  val spadeScalDotPrinter = new CUScalarDotPrinter()
  val spadeCtrlDotPrinter = new CUCtrlDotPrinter()

  val cuMapper:CUMapper = new CUMapper() {
    override def finPass(m:PIRMap) = {
      spadeVecDotPrinter.print(Some(m))
      spadeScalDotPrinter.print(Some(m))
      spadeCtrlDotPrinter.print(Some(m))
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
    mapping = None
    succeeded = false
  }

  override def traverse = {
    tic
    Try[PIRMap](cuMapper.map(PIRMap.empty)).map { m =>
      mapping = Some(m)
      design.mappers.foreach{ _.mappingCheck(m) }
    } match {
      case Success(_) =>
        succeeded = true
        info(s"Mapping succeeded") 
      case Failure(e) =>
        succeeded = false
        info(s"Mapping failed")
        e match {
          case e:OutOfResource[_] =>
            err(e)
            mapping = Some(e.mapping.asInstanceOf[PIRMap])
          case ExceedExceptionLimit(mapper, m) =>
            mapping = Some(m.asInstanceOf[PIRMap])
            throw e
          case PassThroughException(mapper, e, m) =>
            mapping = Some(m)
            throw e
          case e:MappingException[_] =>
            mapping = Some(e.mapping.asInstanceOf[PIRMap])
          case e:PIRException => throw e
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

  //def printMap(mapping:PIRMap)(implicit design:Design) = {
    //if (Config.debug) {
      //emitTitleComment(s"Mapping")
      //mapping.printPMap(this, design)
    //}
  //}

  //def printException(e:PIRException) = {
    //if (Config.debug) {
      //emitTitleComment("Mapping Exceptions:")
      //emitln(s"$e ${e.msg} \n ${e.printStackTrace}")
    //}
  //}
}
