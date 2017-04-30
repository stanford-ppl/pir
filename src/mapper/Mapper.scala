package pir.mapper

import pir.{Design, Config}
import pir.util.typealias._
import pir.codegen.{CUDotPrinter}
import pir.plasticine.main._
import pir.plasticine.graph.{ Node => PNode }
import pir.codegen.{Logger}
import pir.plasticine.util.SpadeMetadata
import pir.util.PIRMetadata

import java.lang.Thread
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import scala.util.{Try, Success, Failure}
import pir.exceptions._

trait Mapper { self =>
  type M = PIRMap 
  type E = MappingException[_]

  implicit val mapper = self
  val exceptLimit:Int = -1
  lazy val excepsStack = Stack[ListBuffer[E]]()
  def currentExceptScope = excepsStack.lastOption.getOrElse{
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

  implicit def design:Design
  implicit def spade:Spade = design.arch
  lazy val spademeta: SpadeMetadata = design.arch
  lazy val pirmeta:PIRMetadata = design

  def logger = design.mapperLogger
  design.mappers += this
  
  def typeStr:String
  override def toString = s"$typeStr"

  def debug = Config.debugMapper
  def dprintln(s:Any):Unit = logger.dprintln(debug, s"$this", s)
  def dprint(s:Any):Unit = logger.dprint(debug, s"$this", s)
  def dprintln(mapper:Mapper, s:Any):Unit = logger.dprintln(debug, s"$mapper", s)
  def dprintln(header:String, s:Any):Unit = logger.dprintln(header, s) 
  def dbsln(mapper:Mapper, s:Any):Unit = logger.dbsln(debug, Some(s"$mapper"), s) 
  def dbeln(mapper:Mapper, s:Any):Unit = logger.dbeln(debug, Some(s"$mapper"), s) 
  def dbsln(s:Any):Unit = dbsln(this, s) 
  def dbeln(s:Any):Unit = dbeln(this, s) 
  def emitBlock[T](block: =>T):T = logger.emitBlock(block) 
  def emitBlock[T](s:String)(block: =>T):T = logger.emitBlock(s"$mapper", s)(block) 

  def quote(n:Any)(implicit spade:Spade):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => pir.plasticine.util.quote(n)
  }

  def log[M](mapper:Mapper, info:Any, finPass:M => Unit, failPass:Throwable => Unit)(block: => M):M = {
    val (infoStr, buffer) = info match {
      case (infoStr:Any, buffer:Boolean) => (infoStr, buffer)
      case info => (s"$info", false)
    }
    dbsln(mapper, s"$infoStr")
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
        dbeln(mapper, s"$infoStr (succeeded)")
        finPass(m); m
      case Failure(e) => 
        if (buffer) logger.closeAndWriteBuffer
        dbeln(mapper, s"$infoStr (failed) $e")
        failPass(e); throw e
    }
  }
  def log[M](info:Any, finPass:M => Unit, failPass:Throwable => Unit)(block: => M):M = log(this, info, finPass, failPass)(block)
  def log[M](info:Any, finPass:M => Unit)(block: => M):M = log(this, info, finPass, (e:Throwable) => ())(block)
  def log[M](mapper:Mapper, info:Any)(block: => M):M = log(mapper, info, (m:M) => (), (e:Throwable) => ())(block)
  def log[M](info:Any)(block: => M):M = log(this, info)(block)

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
    resFilter:(List[R], List[E]) => List[R], // triedRes => reses 
    finPass:M => M,
    map:M
  ):M = {
    val exceps = ListBuffer[E]()
    val triedRes = ListBuffer[R]()
    var reses = resFilter(triedRes.toList, exceps.toList)
    while (reses.size!=0) {
      val res = reses.head
      (Try {
        constrain(n, res, map, exceps.toList)
      }.flatMap { m => Try(finPass(m)) }) match {
        case Success(m) => return m
        case Failure(e@NoSolFound(_, es, mp)) => exceps ++= es
        case Failure(e@FailToMapNode(_, n, es, mp)) => exceps ++= es
        case Failure(me:E) => exceps += me // constrain failed
        case Failure(e) => throw e
      }
      triedRes += res
      reses = Try {
        resFilter(triedRes.toList, exceps.toList)
      } match {
        case Success(rs) => rs
        case Failure(e:MappingException[_]) => exceps += e; Nil
        case Failure(e) => throw e
      }
    }
    throw FailToMapNode(this, n, exceps.toList, map)
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
    /* Recursively map a list of nodes to a list of resource */
    def recNode(remainNodes:List[N], map:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(map) // throw MappingException
      }
      val exceps = ListBuffer[E]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        log(s"Mapping $n", { (m:M) => return m; () // finPass
        }, { (e:Throwable) => // Failpass
          e match {
            case FailToMapNode(_, n, es, mp) => exceps ++= es
            //case fe:FailToMapNode[_] => exceps += fe//; dprintln(flattenExceptions(fe.exceps)) // recRes failed
            case _ => throw e // Unknown exception
          }
        }) { // Block
          def rn(m:M): M = recNode(restNodes, m)
          def rf(trs:List[R]):List[R] = resFunc(n, map, trs)
          recRes[R,N,M](n, constrain, rf _, rn _, map)
        } 
      }
      throw NoSolFound(this, exceps.toList, map) 
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
          case Failure(e@NoSolFound(_, es, mp)) => exceps ++= es
          case Failure(e@FailToMapNode(_, n, es, mp)) => exceps ++= es
          case Failure(me:E) => exceps += me // constrains failed
          case Failure(e) => throw e // Unknown exception
        }
      }
      map match {
        case map:PIRMap => design.pirMapping.mapping = Some(map)
        case _ =>
      }
      throw FailToMapNode(this, n, exceps.toList, map)
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
        case Failure(fe@FailToMapNode(_, n, es, mp)) => 
          throw NoSolFound(this, List(fe), fe.mapping) // recRes failed
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
        case FailToMapNode(mapper, n, exceps, m) => flattenExceptions(exceps)
        case NoSolFound(mapper, exceps, m) => flattenExceptions(exceps)
        case e => e::Nil
      }
    }.toSet
  }
}

