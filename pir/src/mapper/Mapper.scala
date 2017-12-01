package pir.mapper

import pir._
import pir.util.typealias._
import pir.codegen._

import spade._

import pirc._
import pirc.util._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import scala.util.{Try, Success, Failure}
import java.lang.Thread

trait Mapper extends BackTrackingSearch { self =>
  type M = PIRMap 
  type E = MappingException[_]

  implicit val mapper = self
  val exceptLimit:Int = -1
  lazy val excepsStack = Stack[ListBuffer[E]]()
  def currentExceptScope = excepsStack.lastOption.getOrElse {
    val newList = ListBuffer[E]()
    excepsStack.push(newList)
    newList
  }
  def exceedExceptLimit = {
    (exceptLimit > 0) && (currentExceptScope.size > exceptLimit)
  }
  def addException(e:E) = {
    currentExceptScope += e
  }

  implicit def design:PIR
  implicit def arch:Spade = design.arch
  lazy val spademeta: SpadeMetadata = design.arch
  lazy val pirmeta:PIRMetadata = design

  implicit def logger = design.mapperLogger
  design.mappers += this
  
  def typeStr:String
  override def toString = s"$typeStr"

  def debug = PIRConfig.debugMapper
  def dprintln(s:Any):Unit = logger.dprintln(s"$this", s)
  def dprint(s:Any):Unit = logger.dprint(s"$this", s)
  def dprintln(p:Boolean, s:Any):Unit = logger.dprintln(p && debug, s"$this", s)
  def dprintln(mapper:Mapper, s:Any):Unit = logger.dprintln(s"$mapper", s)
  def dprintln(header:String, s:Any):Unit = logger.dprintln(header, s) 
  def dbsln(mapper:Mapper, s:Any):Unit = logger.dbsln(debug, Some(s"$mapper"), s) 
  def dbeln(mapper:Mapper, s:Any):Unit = logger.dbeln(debug, Some(s"$mapper"), s) 
  def dbsln(s:Any):Unit = dbsln(this, s) 
  def dbeln(s:Any):Unit = dbeln(this, s) 
  def emitBlock[T](block: =>T):T = logger.emitBlock(block) 
  def emitBlock[T](s:String)(block: =>T):T = logger.emitBlock(s"$mapper", s)(block) 

  def quote(n:Any)(implicit arch:Spade):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => spade.util.quote(n)
    case n:Iterable[_] => s"[${n.map(quote).mkString(",")}]"
    case n:Any => n.toString
  }

  def log[M](
    info:Any, 
    buffer:Boolean=false,
    finPass:M => Unit = (m:M) => (), 
    failPass:Throwable => Unit=(e:Throwable) => ()
  )(block: => M):M = {
    dbsln(this, s"$info")
    if (buffer) {
      logger.openBuffer
      excepsStack.push(ListBuffer.empty)
    }
    Try(block) match {
      case Success(m) => 
        if (buffer) { 
          logger.closeBuffer
          excepsStack.pop
        }
        dbeln(this, s"$info (succeeded)")
        finPass(m); m
      case Failure(e) => 
        if (buffer) logger.closeAndWriteBuffer
        dbeln(this, s"$info (failed ${currentExceptScope.size}/$exceptLimit) $e")
        failPass(e); throw e
    }
  }

  def printCaller:Unit = {
    if (Config.debug) logger.pprintln(getStackTrace(7,10))
  }
  def getStackTrace:String = {
    getStackTrace(1, 5)
  }
  def getStackTrace(start:Int, end:Int):String = {
    Thread.currentThread().getStackTrace().slice(start,end).map("" + _).mkString("\n")
  }

  /* Called after mapping is done to make sure all nodes are mapped*/
  def mappingCheck(mapping:PIRMap):Unit = {}

  /* Called when handle mapping exception to show remaining nodes*/
  def checkRemain(mapping:PIRMap) = {}

    // DEBUG
  def breakPoint(mp:Option[M], msg:String, interactive:Boolean):Unit = if (debug) {
    bp(msg)
    var open = false
    if (interactive) {
      val answer = ask(s"Waiting for input ...")
      answer match {
        case "o" => open = true
        case "q" =>
          info(s"Stop debugging routing and exiting...")
          System.exit(-1)
        case _ =>
      }
    }
    if (open) {
      this match {
        case router:VectorRouter =>
          new PlasticineVectorDotPrinter().print(mp).open
        case router:ScalarRouter =>
          new PlasticineScalarDotPrinter().print(mp).open
        case router:ControlRouter =>
          new PlasticineCtrlDotPrinter().print(mp).open
        case router:CUMapper =>
          new PlasticineVectorDotPrinter().print(mp).open
          new PlasticineScalarDotPrinter().print(mp).open
          new PlasticineCtrlDotPrinter().print(mp).open
      }
    }
  }

  def breakPoint(mp:M, msg:String, interactive:Boolean):Unit = if (debug) {
    breakPoint(Some(mp), msg, interactive)
  }

  def breakPoint(msg:String, interactive:Boolean):Unit = if (debug) {
    breakPoint(None, msg, interactive)
  }
    // DEBUG --
}

