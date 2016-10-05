package pir.graph.mapper

import pir._
import pir.typealias._
import pir.graph.traversal.{MapperLogger, CUDotPrinter}
import pir.plasticine.main._
import pir.plasticine.graph.{ Node => PNode }

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

trait Mapper { self =>
  implicit val mapper = self

  type M = PIRMap 

  val a:CU = null
  implicit val design:Design
  
  val typeStr:String
  override def toString = s"$typeStr"

  def debug = Config.debugMapper
  def dprintln(s:Any):Unit = MapperLogger.dprintln(debug, s"$this", s)
  def dprint(s:Any):Unit = MapperLogger.dprint(debug, s"$this", s)
  def dprintln(mapper:Mapper, s:Any):Unit = MapperLogger.dprintln(debug, s"$mapper", s)
  def quote(pne:Any)(implicit spade:Spade) = PNode.quote(pne) 

  def log[M](mapper:Mapper, info:Any)(block: => M):M = {
    dprintln(mapper, s"$info { ")
    Try(block) match {
      case Success(m) =>
        dprintln(mapper, s"} $info (succeeded)"); MapperLogger.flush; m
      case Failure(e) =>
        dprintln(mapper, s"} $info (failed) $e"); MapperLogger.flush; throw e
    }
  }
  def log[M](info:Any)(block: => M):M = log(this, info)(block)

  type RecResFunc[R,N,M] = (List[R], N, List[N], M) => M
  type RecNodeFunc[R,N,M] = (List[R], List[N], M) => M
  type ResFunc[R,N,M] = (N, M, List[R]) => List[R]

  def recRes[R,N,M](constrains:List[(N, R, M) => M], resFunc:(N, M) => List[R], recNode:(List[N], M) => M)
                   (n:N, remainNodes:List[N], map:M):M = {
    def rn(remainRes:List[R], remainNodes: List[N], m:M) = recNode(remainNodes, m)
    def rf(n:N, m:M, remainRes:List[R]) = resFunc(n, m)
    recRes[R,N,M](constrains, rf _, rn _)(Nil, n, remainNodes, map)
  }

  def recRes[R,N,M](constrains:List[(N, R, M) => M], recNode:RecNodeFunc[R,N,M])
                   (remainRes:List[R], n:N, remainNodes:List[N], map:M):M = {
    recRes[R,N,M](constrains, (n:N, m:M, rm:List[R]) => rm, recNode)(remainRes, n, remainNodes, map)
  }
  // Recursively try a node on a list of resource defined by resFunc 
  def recRes[R,N,M](constrains:List[(N, R, M) => M], resFunc:ResFunc[R,N,M], recNode:RecNodeFunc[R,N,M])
                   (remainRes:List[R], n:N, remainNodes:List[N], map:M):M = {
    val exceps = ListBuffer[MappingException]()
    val reses = resFunc(n, map, remainRes)
    for (ir <- 0 until reses.size) {
      val (h, res::rt) = reses.splitAt(ir)
      val restRes = h ++ rt
      (Try {
        constrains.foldLeft(map) { case (pm, cons) => cons(n, res, pm) }
      } match {
        case Success(m) => 
          dprintln(s"Try $n -> ${quote(res)(design.arch)} (success) remain:[${remainNodes.mkString(",")}] {")
          Try(recNode(restRes, remainNodes, m))
        case Failure(ResourceNotUsed(_,_,_,m)) => 
          dprintln(s"Try $n already mapped remain:[${remainNodes.mkString(",")}] {")
          Try(recNode(reses, remainNodes, m.asInstanceOf[M]))
        case Failure(e) => 
          dprintln(s"Try $n -> ${quote(res)(design.arch)} (failed) ${e} {") 
          Failure(e) 
      }) match {
        case Success(m) => 
          dprintln(s"} (success)")
          return m
        case Failure(e) => 
          dprintln(s"} (failed) ${e}")
          e match {
          case me:MappingException => exceps += me // constrains failed
          case _ => throw e
        }
      }
    }
    throw FailToMapNode(this, n, exceps.toList)
  }

  /* Bind a list of nodes to a list of resource exhausting all possibilities 
   * before failing and throw NoSolFound Exception
   * @param allRes list of resource 
   * @param allNodes list of nodes
   * @param initMap initial mapping
   * @param constrains constrains to map a node to a resource. Throw Mapping exceptio if failed 
   * @param resFunc a function defines list of resource to try to bind node n. The list of resource
   * is defined based on current node n,  current mapping M, and remaining unbinded resource.
   * The resulting list should be ordered based on convergence speed, where resource likely 
   * to success should be placed early in the list.
   * @param finPass Additional mapping or map transformation after all nodes are successfully mapped
   * before return the in recursive mapping. If finPass fails, the last binding was
   * considered failed and binding process continues try different options
   * */
  def bind[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], resFunc:ResFunc[R,N,M], finPass: M => M):M = {

    /* Recursively map a list of nodes to a list of resource */
    def recNode(remainRes:List[R], remainNodes:List[N], map:M):M = {
      if (remainNodes.size==0) { //Successfully mapped all nodes
        return finPass(map) // throw MappingException
      }
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        Try{
          recRes[R,N,M](constrains, resFunc, recNode _)(remainRes, n, restNodes, map)
        } match {
          case Success(m) => return m 
          case Failure(e) => e match {
            case fe:FailToMapNode => exceps += fe // recRes failed
            case _ => throw e // Unknown exception
          }
        }
      }
      throw NoSolFound(this, exceps.toList) 
    }

    recNode(allRes, allNodes, initMap)
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
  def bind[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M):M = {
    bind(allRes, allNodes, initMap, constrains, (n:N, m:M, rm:List[R]) => rm, finPass)
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
   * @param oor (resSize, nodeSize) => OutOfResource Exception
   * */
  def bind[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M, 
    oor:(Int, Int) => OutOfResource):M = {
    checkOOR(allRes.size, allNodes.size, oor)
    bind(allRes, allNodes, initMap, constrains, finPass)
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
  def bindInOrder[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M, 
    oor:(Int, Int) => OutOfResource):M = {

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, remainNodes:List[N], preMap:M):M = {
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (_, res::rt) = remainRes.splitAt(ir)
        val restRes = rt
        Try {
          val mp = constrains.foldLeft(preMap) { case (pm, cons) => cons(n, res, pm) }
          recMap(restRes, remainNodes, mp)
        } match {
          case Success(m) => return m 
          case Failure(e) => e match {
            case me:MappingException => exceps += me // constrains failed
            case _ => throw e // Unknown exception
          }
        }
      }
      throw FailToMapNode(this, n, exceps.toList)
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
          case fe:FailToMapNode => 
            throw NoSolFound(this, List(fe)) // recRes failed
          case _ => throw e // Unknown exception
        }
      }
    }

    checkOOR(allRes.size, allNodes.size, oor)
    recMap(allRes, allNodes, initMap)
  }
  
  private def checkOOR(nres:Int, nnodes:Int, oor:(Int, Int) => OutOfResource) = {
    if (nres < nnodes)
      throw oor(nres, nnodes)
  }
}

