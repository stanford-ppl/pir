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

class PIRMapping(implicit val design: Design) extends Pass {
  def shouldRun = Config.mapping

  var mapping:PIRMap = _
  var succeeded = false

  def failed = !succeeded && Config.mapping

  val siMapper = new ScalarInMapper()
  val sramMapper = new SramMapper()
  val vfifoMapper = new VFifoMapper()
  val sfifoMapper = new SFifoMapper()
  val stageMapper = new StageMapper()
  val outputMapper = new OutputMapper()
  val ctrlMapper = new CtrlMapper()
  val regAlloc = new RegAlloc() {
    override def finPass(ctrler:InnerController)(m:M):M = { 
      var mp = m
      stageMapper.map(ctrler, m)
      mp
    }
  }
  val ctrMapper = new CtrMapper() { 
    override def finPass(ctrler:ComputeUnit)(m:M):M = { 
      var mp = m
      mp = ctrlMapper.map(ctrler, mp)
      mp = regAlloc.map(ctrler, mp)
      mp
    }
  }
  def mapPrim(ctrler:Controller)(m:PIRMap):PIRMap = {
    var mp = m
    mp = sramMapper.map(ctrler, mp)
    mp = vfifoMapper.map(ctrler, mp) 
    mp = ctrMapper.map(ctrler, mp)
    mp = sfifoMapper.map(ctrler, mp)

    //mp = siMapper.map(ctrler, mp)
    //ctrler match {
      //case ctrler:InnerController => 
        //ctrler match {
          //case mc:MemoryController =>
          //case _ => mp = sramMapper.map(ctrler, mp)
        //}
        //mp = ctrMapper.map(ctrler, mp)
      //case t:Top => mp = ctrlMapper.map(t, mp)
      //case _ => assert(false, s"Unknown ctrler:$ctrler")
    //}

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
