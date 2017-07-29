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
  var placeAndRouteSucceeded = false
  var localMappingSucceeded = false
  def succeeded = placeAndRouteSucceeded & localMappingSucceeded 

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
  def mapPrimtivies(ctrler:Controller)(m:PIRMap):PIRMap = {
    var mp = m
    mp = vfifoMapper.map(ctrler, mp) 
    mp = sfifoMapper.map(ctrler, mp)
    mp = sramMapper.map(ctrler, mp)
    mp = ctrMapper.map(ctrler, mp)
    mp
  }
  val prescreen = new ResourcePrescreen()
  val cuMapper = new CUMapper()

  val spadeVecDotPrinter = new CUVectorDotPrinter()
  val spadeScalDotPrinter = new CUScalarDotPrinter()
  val spadeCtrlDotPrinter = new CUCtrlDotPrinter()

  override def reset = {
    mapping = None
    placeAndRouteSucceeded = false
    localMappingSucceeded = false
  }

  def handle(e:Throwable) = {
    e match {
      case ExceedExceptionLimit(mapper, m) =>
        mapping = Some(m.asInstanceOf[PIRMap])
      case PassThroughException(mapper, e, m) =>
        mapping = Some(m)
      case e:MappingException[_] =>
        mapping = Some(e.mapping.asInstanceOf[PIRMap])
        (e.mapper, e.mapping) match {
          case (mapper:VectorRouter, mapping:PIRMap) =>
            new CUVectorDotPrinter(open=true)(design).print(Some(mapping))
          case (mapper:ScalarRouter, mapping:PIRMap) =>
            new CUScalarDotPrinter(open=true)(design).print(Some(mapping))
          case (mapper:ControlRouter, mapping:PIRMap) =>
            new CUCtrlDotPrinter(open=true)(design).print(Some(mapping))
          case _ =>
        }
      case e =>
    }
  }

  addPass {
    tic
    Try[PIRMap]{
      prescreen.run
      cuMapper.map(PIRMap.empty)
    }.map { m =>
      spadeVecDotPrinter.print(Some(m))
      spadeScalDotPrinter.print(Some(m))
      spadeCtrlDotPrinter.print(Some(m))
      mapping = Some(m)
    } match {
      case Success(_) =>
        placeAndRouteSucceeded = true
        success(s"Placement & Routing succeeded") 
      case Failure(e) =>
        placeAndRouteSucceeded = false
        errmsg(s"Placement & Routing failed")
        handle(e)
        throw e
    }
    toc(s"Placement & Routing", "s")
  } 

  addPass(canRun=placeAndRouteSucceeded && (!Config.ctrl || design.ctrlAlloc.hasRun)) {
    Try[PIRMap]{
      var mp = mapping.get
      design.top.ctrlers.foreach { ctrler =>
        mp = mapPrimtivies(ctrler)(mp)
      }
      mp
    }.map { m =>
      mapping = Some(m)
      design.mappers.foreach{ _.mappingCheck(m) }
    } match {
      case Success(_) =>
        localMappingSucceeded = true
        success(s"Local Mapping succeeded") 
      case Failure(e) =>
        placeAndRouteSucceeded = false
        errmsg(s"Local Mapping failed")
        handle(e)
        throw e
    }
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
