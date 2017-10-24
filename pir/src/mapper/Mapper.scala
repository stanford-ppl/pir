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

trait Mapper { self =>
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

  def mappingCheck(mapping:PIRMap):Unit = {}

  def checkRemain(mapping:PIRMap) = {}

  //def recRes[R,N,M](
  //  n:N,
  //  allRes:List[R],
  //  constrains:List[(N, R, M) => M],
  //  finPass:M => M,
  //  map:M
  //):M = {
  //  def rf(triedRes:List[R]) = allRes.diff(triedRes) 
  //  recRes(n, constrains, rf _, finPass, map)
  //}

  def recRes[R,N,M](
    n:N,
    constrain:(N, R, M) => M,
    resFilter:List[R] => List[R], // triedRes => reses 
    finPass:M => M,
    map:M
  ):M = {
    def cons(n:N, r:R, m:M, es:List[E]) = constrain(n, r, m)
    def rf(triedRes:List[R], exceps:List[E]) = resFilter(triedRes) 
    recResWithExcept(n, cons _, rf _, finPass, map)
  }

  def recResWithExcept[R,N,M](
    n:N,
    constrain:(N, R, M, List[E]) => M,
    resFilter:(List[R], List[E]) => List[R], // triedRes, exceptions => reses 
    finPass:M => M,
    map:M
  ):M = {
    val exceps = ListBuffer[E]()
    val triedRes = ListBuffer[R]()
    def compRes = {
      Try (resFilter(triedRes.toList, exceps.toList)) match {
        case Success(rs) => rs
        case Failure(e:MappingException[_]) => exceps += e; Nil
        case Failure(e) => throw e
      }
    }
    var reses = compRes 
    while (reses.nonEmpty) {
      val res = reses.head
      Try { 
        finPass(constrain(n, res, map, exceps.toList))
      } match {
        case Success(m) => return m
        case Failure(e@NoSolFound(es, mp)) => 
          exceps ++= es
        case Failure(e@FailToMapNode(n, es, mp)) => 
          exceps ++= es
        case Failure(me:E) => 
          exceps += me // constrain failed
        case Failure(e) => throw e
      }
      triedRes += res
      reses = compRes
    }
    throw FailToMapNode(n, exceps.toList, map)
  }

  def bind[R<:PNode,N<:Node,M](
    allRes:List[R], 
    allNodes:List[N], 
    initMap:M, 
    constrain:(N, R, M) => M,
    finPass: M => M,
    oor:(List[R], List[N], M) => OutOfResource[M]
  ):M = {
    checkOOR(allRes, allNodes, initMap, oor)
    bind(allRes, allNodes, initMap, constrain, finPass)
  }

  def bind[R,N,M](
    allRes:List[R], 
    allNodes:List[N], 
    initMap:M, 
    constrain:(N, R, M) => M,
    finPass: M => M
  ):M = {
    type MP = (M, List[R])
    // Add filter to exclude mappedRes and triedRes
    def rf(n:N, mp:MP, triedRes:List[R]) = {
      val (m, usedRes) = mp
      allRes.diff(usedRes).diff(triedRes)
    }
    // Add a list to map to keep track of mappedRes/usedRes
    def cons(n:N, r:R, mp:MP):MP = {
      val (m, urs) = mp
      (constrain(n, r, m), urs :+ r) 
    }
    val (map,_) = 
      bind[R,N,MP](allNodes, (initMap, Nil), cons _, rf _, (mp:MP) => (finPass(mp._1),Nil))
    map
  }

  def bind[R,N,M](
    allNodes:List[N],
    initMap:M, 
    constrain:(N, R, M) => M,
    resFunc:(N,M,List[R]) => List[R], //(n, m, triedRes) => List[R]
    finPass: M => M
  ):M = {
    val total = allNodes.size
    /* Recursively map a list of nodes to a list of resource */
    def recNode(remainNodes:List[N], map:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(map) // throw MappingException
      }
      val exceps = ListBuffer[E]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        log (s"Mapping $n (${total-restNodes.size}/${total})", 
          finPass = { (m:M) => return m },
          failPass = { 
            case FailToMapNode(n, es, mp) => exceps ++= es
            case e => throw e // Unknown exception
          }
        ) { // Block
          def rn(m:M): M = recNode(restNodes, m)
          def rf(trs:List[R]):List[R] = resFunc(n, map, trs)
          recRes[R,N,M](n=n, constrain=constrain, resFilter=rf _, finPass=rn _, map=map)
        } 
      }
      throw NoSolFound(exceps.toList, map) 
    }

    recNode(allNodes, initMap)
  }

  /* Bind a list of nodes to a list of resource exhausting all possibilities 
   * before failing and throw NoSolFound Exception
   * @param allRes list of resource
   * @param allNodes list of nodes
   * @param initMap initial mapping
   * @param constrain constrain to map a node to a resource. Throw Mapping exceptio if failed 
   * @param finPass Additional mapping or map transformation after all nodes are successfully mapped
   * before return in recursive mapping. If finPass throws an exception, previous mapping of all
   * nodes was considered failed and continues trying
   * */

  def bindInOrder[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M],
    finPass: M => M, 
    oor:(List[R], List[N], M) => OutOfResource[M]):M = {
    checkOOR(allRes, allNodes, initMap, oor)
    bindInOrder(allRes, allNodes, initMap, constrains, finPass)
  }

  /* Trying to map a list of nodes to a list of resource exhausting all
   * possibilities that guarantees binded ordered nodes possesses resource that are of the same
   * order in allRes. E.g. mapping { n1 -> r1, n2 -> r2 }. If n1 before n2 in allNodes, r1 also
   * before r2 in allRes
   * @param allRes list of resource
   * @param allNodes list of nodes
   * @param initMap initial mapping
   * @param constrains constrains to map a node to a resource. Throw Mapping exception if failed 
   * @param finPass Additional mapping or map transformation after all nodes are successfully mapped
   * before return in recursive mapping. If finPass throws an exception, previous mapping of all
   * nodes was considered failed and continues trying
   * @param oor (resSize, nodeSize) => OutOfResource Exception
   * */
  def bindInOrder[R,N,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M):M = {

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, remainNodes:List[N], map:M):M = {
      val exceps = ListBuffer[E]()
      for (ir <- 0 until remainRes.size) {
        val (_, res::rt) = remainRes.splitAt(ir)
        val restRes = rt
        Try {
          val mp = constrains.foldLeft(map) { case (pm, cons) => cons(n, res, pm) }
          recMap(restRes, remainNodes, mp)
        } match {
          case Success(m) => return m 
          case Failure(e@NoSolFound(es, mp)) => exceps ++= es
          case Failure(e@FailToMapNode(n, es, mp)) => exceps ++= es
          case Failure(me:E) => exceps += me // constrains failed
          case Failure(e) => throw e // Unknown exception
        }
      }
      map match {
        case map:PIRMap => design.pirMapping.mapping = Some(map)
        case _ =>
      }
      throw FailToMapNode(n, exceps.toList, map)
    }

    /* Recursively map a list of nodes to a list of resource */
    def recMap(remainRes:List[R], remainNodes:List[N], recmap:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(recmap) // throw MappingException
      }
      val n::rt = remainNodes
      val restNodes = rt
      Try {
        recRes(remainRes, n, restNodes, recmap)
      } match {
        case Success(m) => return m
        case Failure(fe@FailToMapNode(n, es, mp)) => 
          throw NoSolFound(List(fe), fe.mapping) // recRes failed
        case Failure(e) => throw e // Unknown exception
      }
    }

    recMap(allRes, allNodes, initMap)
  }
  
  private def checkOOR[R<:PNode,N<:Node, M](pnodes:List[R], nodes:List[N], mp:M, 
    oor:(List[R], List[N], M) => OutOfResource[M]) = {
    if (pnodes.size < nodes.size) throw oor(pnodes, nodes, mp)
  }

  def flattenExceptions(es:List[E]):Set[E] = {
    es.flatMap { e =>
      e match {
        case FailToMapNode(n, exceps, m) => flattenExceptions(exceps)
        case NoSolFound(exceps, m) => flattenExceptions(exceps)
        case e => e::Nil
      }
    }.toSet
  }

    // DEBUG
  def breakPoint(mp:Option[M], msg:String, interactive:Boolean):Unit = if (debug) {
    bp(msg)
    //val arch = design.arch.asInstanceOf[SwitchNetwork]
    //val ocu = arch.ocuArray(0)(4)
    //ocu.ctrlIO.ins.foreach { pin =>
      //println(s"$ocu $pin -> ${mp.vimap.pmap.get(pin)}")
    //}
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

