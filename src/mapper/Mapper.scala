package pir.graph.mapper
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Node}
import pir._
import pir.plasticine.config._
import pir.plasticine.graph.{Node => PNode, Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

trait Mapper {
  type M = PIRMap 

  implicit val design:Design
  
  override def toString = this.getClass().getSimpleName() 

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
  def simAneal[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: M => M, 
    oor:(Int, Int) => OutOfResource):M = {

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, remainNodes:List[N], preMap:M):M = {
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (h, res::rt) = remainRes.splitAt(ir)
        val restRes = h ++ rt
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
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h, n::rt) = remainNodes.splitAt(in)
        val restNodes = h ++ rt
        Try{
          recRes(remainRes, n, restNodes, recmap)
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

    checkOOR(allRes.size, allNodes.size, oor)
    recMap(allRes, allNodes, initMap)
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
  def inordBind[R<:PNode,N<:Node,M](allRes:List[R], allNodes:List[N], initMap:M, 
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

