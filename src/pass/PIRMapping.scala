package pir.pass

import pir.node._
import pir._
import pir.codegen._
import pir.mapper._
import pirc.exceptions._
import scala.util.{Try, Success, Failure}
import pirc.util._
import pirc._

class PIRMapping(implicit design: PIR) extends Pass with Logger {

  override lazy val stream = newStream(PIRConfig.mapFile)

  def shouldRun = PIRConfig.mapping

  def mapping:Option[PIRMap] = design.mapping
  def mapping_=(m:Option[PIRMap]):Unit = design.mapping = m
  var placeAndRouteSucceeded = false
  var localMappingSucceeded = false
  var configSucceeded = false
  def succeeded = placeAndRouteSucceeded & localMappingSucceeded 

  def failed = !succeeded && PIRConfig.mapping

  val configMapper = new ConfigMapper()
  val sramMapper = new SramMapper()
  //val vfifoMapper = new VFifoMapper()
  //val sfifoMapper = new SFifoMapper()
  val fifoMapper = new FifoMapper()
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
    //mp = vfifoMapper.map(ctrler, mp) 
    //mp = sfifoMapper.map(ctrler, mp)
    mp = fifoMapper.map(ctrler, mp)
    mp = sramMapper.map(ctrler, mp)
    mp = ctrMapper.map(ctrler, mp)
    mp
  }
  val cuMapper = new CUMapper()

  val spadeVecDotPrinter = new SpadeVectorDotPrinter()
  val spadeScalDotPrinter = new SpadeScalarDotPrinter()
  val spadeCtrlDotPrinter = new SpadeCtrlDotPrinter()

  override def reset = {
    mapping = None
    placeAndRouteSucceeded = false
    localMappingSucceeded = false
  }

  def viewRouting(mapper:Mapper):Unit = {
    mapper match {
      case mapper:VectorRouter =>
        new SpadeVectorDotPrinter(open=true)(design).print(mapping)
        new SpadeScalarDotPrinter(open=false)(design).print(mapping)
        new SpadeCtrlDotPrinter(open=false)(design).print(mapping)
      case mapper:ScalarRouter =>
        new SpadeVectorDotPrinter(open=false)(design).print(mapping)
        new SpadeScalarDotPrinter(open=true)(design).print(mapping)
        new SpadeCtrlDotPrinter(open=false)(design).print(mapping)
      case mapper:ControlRouter =>
        new SpadeVectorDotPrinter(open=false)(design).print(mapping)
        new SpadeScalarDotPrinter(open=false)(design).print(mapping)
        new SpadeCtrlDotPrinter(open=true)(design).print(mapping)
      case mapper:CUMapper =>
        new SpadeVectorDotPrinter(open=true)(design).print(mapping)
        new SpadeScalarDotPrinter(open=true)(design).print(mapping)
        new SpadeCtrlDotPrinter(open=true)(design).print(mapping)
      case _ =>
    }
  }
  def handle(e:Throwable) = {
    e match {
      case e@ExceedExceptionLimit(m) =>
        mapping = Some(m.asInstanceOf[PIRMap])
        viewRouting(e.mapper)
        e.mapper.checkRemain(mapping.get)
      case PassThroughException(e, m) =>
        mapping = Some(m)
      case e:MappingException[_] =>
        e.mapping match {
          case m:PIRMap => mapping = Some(m)
          case (m:PIRMap, _) => mapping = Some(m)
        }
        viewRouting(e.mapper)
      case e =>
        dprintln(e)
        dprintln(e.getStackTrace.mkString("\n"))
    }
    throw e
  }

  addPass(canRun=design.prescreen.hasRun){
    tic
    Try[PIRMap]{
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
    }
    toc(s"Placement & Routing", "s")
  } 

  addPass(canRun=placeAndRouteSucceeded && (!PIRConfig.ctrl || design.ctrlAlloc.hasRun)) {
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
    }
  }

  addPass(canRun=localMappingSucceeded) {
    Try[PIRMap]{
      var mp = mapping.get
      mp = configMapper.map(mp)
      mp
    }.map { m =>
      mapping = Some(m)
    } match {
      case Success(_) =>
        configSucceeded = true
        success(s"Configuration succeeded") 
      case Failure(e) =>
        configSucceeded = false
        errmsg(s"Configuration failed")
        handle(e)
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

  //def printMap(mapping:PIRMap)(implicit design:PIR) = {
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
