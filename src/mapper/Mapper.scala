package pir.graph.mapper

import pir._
import pir.typealias._
import pir.graph.traversal.{CUDotPrinter}
import pir.plasticine.main._
import pir.plasticine.graph.{ Node => PNode }
import pir.codegen.{Logger, DotCodegen}
import java.lang.Thread

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

object MapperLogger extends Logger {
  override val stream = newStream(Config.mapperLog)
}
trait Mapper { self =>
  implicit val mapper = self
  val exceptLimit:Int = -1
  lazy val mapExceps = ListBuffer[MappingException]()
  def exceedExceptLimit = (exceptLimit > 0) && (mapExceps.size > exceptLimit)

  type M = PIRMap 

  val a:CU = null
  implicit val design:Design
  
  val typeStr:String
  override def toString = s"$typeStr"

  def debug = Config.debugMapper
  def dprintln(s:Any):Unit = MapperLogger.dprintln(debug, s"$this", s)
  def dprint(s:Any):Unit = MapperLogger.dprint(debug, s"$this", s)
  def dprintln(mapper:Mapper, s:Any):Unit = MapperLogger.dprintln(debug, s"$mapper", s)
  def dbsln(mapper:Mapper, s:Any):Unit = MapperLogger.dbsln(debug, Some(s"$mapper"), s) 
  def dbeln(mapper:Mapper, s:Any):Unit = MapperLogger.dbeln(debug, Some(s"$mapper"), s) 
  def dbsln(s:Any):Unit = dbsln(this, s) 
  def dbeln(s:Any):Unit = dbeln(this, s) 

  def quote(pne:Any)(implicit spade:Spade) = DotCodegen.quote(pne) 

  def log[M](mapper:Mapper, info:Any, finPass:M => Unit, failPass:Throwable => Unit)(block: => M):M = {
    dbsln(mapper, s"$info")
    //printCaller 
    Try(block) match {
      case Success(m) => dbeln(mapper, s"$info (succeeded)"); finPass(m); m
      case Failure(e) => dbeln(mapper, s"$info (failed) $e"); failPass(e); throw e
    }
  }
  def log[M](info:Any, finPass:M => Unit, failPass:Throwable => Unit)(block: => M):M = log(this, info, finPass, failPass)(block)
  def log[M](info:Any, finPass:M => Unit)(block: => M):M = log(this, info, finPass, (e:Throwable) => ())(block)
  def log[M](mapper:Mapper, info:Any)(block: => M):M = log(mapper, info, (m:M) => (), (e:Throwable) => ())(block)
  def log[M](info:Any)(block: => M):M = log(this, info)(block)

  def printCaller:Unit = {
    val str = Thread.currentThread().getStackTrace().slice(7,10).map("" + _).mkString("\n")
    if (Config.debug) MapperLogger.pprintln(str)
  }

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
    constrains:List[(N, R, M) => M],
    resFilter:List[R] => List[R], // triedRes => reses 
    finPass:M => M,
    map:M
  ):M = {
    val conses = constrains.map { constrain =>
      def cons(n:N, r:R, m:M, es:List[MappingException]) = constrain(n, r, m)
      cons _
    }
    def rf(triedRes:List[R], exceps:List[MappingException]) = resFilter(triedRes) 
    recResWithExcept(n, conses, rf _, finPass, map)
  }

  def recResWithExcept[R,N,M](
    n:N,
    constrains:List[(N, R, M, List[MappingException]) => M],
    resFilter:(List[R], List[MappingException]) => List[R], // triedRes => reses 
    finPass:M => M,
    map:M
  ):M = {
    val exceps = ListBuffer[MappingException]()
    val triedRes = ListBuffer[R]()
    var reses = resFilter(triedRes.toList, exceps.toList)
    while (reses.size!=0) {
      val res = reses.head
      (Try {
        constrains.foldLeft(map) { case (pm, cons) => cons(n, res, pm, exceps.toList) }
      } match {
        case Success(m) => 
          //dbsln(s"Try $n -> ${quote(res)(design.arch)} (success) remain:[${remainNodes.mkString(",")}]") 
          Try(finPass(m))
        case Failure(e) => 
          //dbeln(s"Try $n -> ${quote(res)(design.arch)} (failed) ${e}")
          Failure(e)
      }) match {
        case Success(m) => 
          //dbeln(s"(success)")
          return m
        case Failure(e) => 
          //dbeln(s"(failed) ${e}")
          e match {
          case me:MappingException => exceps += me// constrains failed
          case _ => throw e
        }
      }
      triedRes += res
      reses = resFilter(triedRes.toList, exceps.toList)
    }
    throw FailToMapNode(this, n, exceps.toList, map)
  }

  def bind[R,N,M](
    allRes:List[R], 
    allNodes:List[N], 
    initMap:M, 
    constrains:List[(N, R, M) => M],
    finPass: M => M,
    oor:(Int, Int) => OutOfResource
  ):M = {
    checkOOR(allRes.size, allNodes.size, oor)
    bind(allRes, allNodes, initMap, constrains, finPass)
  }

  def bind[R,N,M](
    allRes:List[R], 
    allNodes:List[N], 
    initMap:M, 
    constrains:List[(N, R, M) => M],
    finPass: M => M
  ):M = {
    type MP = (M, List[R])
    def rf(n:N, mp:MP, triedRes:List[R]) = {  // Add filter to exclude mappedRes and triedRes
      val (m, usedRes) = mp
      allRes.diff(usedRes).diff(triedRes)
    }
    // Add a list to map to keep track of mappedRes/usedRes
    val conses:List[(N,R,MP) => MP] = constrains.map{ cons =>
      (n:N, r:R, mp:MP) => {
        val (m, urs) = mp
        if (cons==constrains.last) {
          (cons(n, r, m), urs :+ r) 
        } else {
          (cons(n, r, m), urs) 
        }
      }
    }
    val (map,_) = 
      bind[R,N,MP](allNodes, (initMap, Nil), conses, rf _, (mp:MP) => (finPass(mp._1),Nil))
    map
  }

  def bind[R,N,M](
    allNodes:List[N],
    initMap:M, 
    constrains:List[(N, R, M) => M],
    resFunc:(N,M,List[R]) => List[R], //(n, m, triedRes) => List[R]
    finPass: M => M
  ):M = {
    /* Recursively map a list of nodes to a list of resource */
    def recNode(remainNodes:List[N], map:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(map) // throw MappingException
      }
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        log(s"Mapping $n", { (m:M) => return m; () // finPass
        }, { (e:Throwable) => // Failpass
          e match {
            case fe:FailToMapNode[_] => exceps += fe // recRes failed
            case _ => throw e // Unknown exception
          }
        }) { // Block
          def rn(m:M): M = recNode(restNodes, m)
          def rf(trs:List[R]):List[R] = resFunc(n, map, trs)
          recRes[R,N,M](n, constrains, rf _, rn _, map)
        } 
      }
      throw NoSolFound(this, exceps.toList) 
    }

    recNode(allNodes, initMap)
  }

  /* Bind a list of nodes to a list of resource exhausting all possibilities 
   * before failing and throw NoSolFound Exception
   * @param allRes list of resource
   * @param allNodes list of nodes
   * @param initMap initial mapping
   * @param constrains constrains to map a node to a resource. Throw Mapping exceptio if failed 
   * @param finPass Additional mapping or map transformation after all nodes are successfully mapped
   * before return in recursive mapping. If finPass throws an exception, previous mapping of all
   * nodes was considered failed and continues trying
   * */

  def bindInOrder[R,N,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M, 
    oor:(Int, Int) => OutOfResource):M = {
    checkOOR(allRes.size, allNodes.size, oor)
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
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (_, res::rt) = remainRes.splitAt(ir)
        val restRes = rt
        Try {
          val mp = constrains.foldLeft(map) { case (pm, cons) => cons(n, res, pm) }
          recMap(restRes, remainNodes, mp)
        } match {
          case Success(m) => return m 
          case Failure(e) => e match {
            case me:MappingException => exceps += me // constrains failed
            case _ => throw e // Unknown exception
          }
        }
      }
      map match {
        case map:PIRMap => design.pirMapping.mapping = map
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
        case Failure(e) => e match { 
          case fe:FailToMapNode[_] => 
            throw NoSolFound(this, List(fe)) // recRes failed
          case _ => throw e // Unknown exception
        }
      }
    }

    recMap(allRes, allNodes, initMap)
  }
  
  private def checkOOR(nres:Int, nnodes:Int, oor:(Int, Int) => OutOfResource) = {
    if (nres < nnodes)
      throw oor(nres, nnodes)
  }

  def flattenExceptions(es:List[MappingException]):Set[MappingException] = {
    es.flatMap { e =>
      e match {
        case FailToMapNode(mapper, n, exceps, m) => flattenExceptions(exceps)
        case NoSolFound(mapper, exceps) => flattenExceptions(exceps)
        case e => e::Nil
      }
    }.toSet
  }
}

