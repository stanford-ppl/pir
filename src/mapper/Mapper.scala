package pir.graph.mapper
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir._
import pir.plasticine.config._
import pir.plasticine.graph.{Node => PNode, Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

trait Mapper {
  type R
  type N
  type M = PIRMap 

  implicit var design:Design = _
  def setDesign(d:Design) = design = d
  
  override def toString = this.getClass().getSimpleName() 

  /* Bind a list of nodes to a list of resource exhausting all possibilities 
   * */
  def simAneal(allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: Option[M => M], 
    oor:(Int, Int) => OutOfResource):M = {
    //println(s"simAneal")

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, remainNodes:List[N], preMap:M):M = {
      //println(s"recRes: ${n}")
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (h, t) = remainRes.splitAt(ir)
        val res::rt = t
        Try {
          constrains.foldLeft(preMap) { case (pm, cons) => cons(n, res, pm) }
        } match {
          case Success(m) => 
            Try(recMap(h ++ rt, remainNodes, m)) match {
              case Success(m) => return m
              case Failure(e) => e match {
                case me:NoSolFound => exceps += me
                case e => throw e
              }
            }
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
        //println(s"Running finPass")
        return if (finPass.isDefined) finPass.get(recmap) else recmap
      }
      //println(s"recMap")
      //assert(remainRes.size>0)
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h,t) = remainNodes.splitAt(in)
        val n::rt = t
        Try{
          recRes(remainRes, n, h ++ rt, recmap)
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

    if (allRes.size < allNodes.size)
      throw oor(allRes.size, allNodes.size)
    recMap(allRes, allNodes, initMap)
  }

  def inOrderBind(allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], finPass: Option[M => M], 
    oor:(Int, Int) => OutOfResource):M = {
    //println(s"simAneal")

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, remainNodes:List[N], preMap:M):M = {
      //println(s"recRes: ${n}")
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (h, t) = remainRes.splitAt(ir)
        val res::rt = t
        Try {
          constrains.foldLeft(preMap) { case (pm, cons) => cons(n, res, pm) }
        } match {
          case Success(m) => 
            Try(recMap(h ++ rt, remainNodes, m)) match {
              case Success(m) => return m
              case Failure(e) => e match {
                case me:NoSolFound => exceps += me
                case e => throw e
              }
            }
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
        //println(s"Running finPass")
        return if (finPass.isDefined) finPass.get(recmap) else recmap
      }
      //println(s"recMap")
      //assert(remainRes.size>0)
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h,t) = remainNodes.splitAt(in)
        val n::rt = t
        Try{
          recRes(remainRes, n, h ++ rt, recmap)
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

    if (allRes.size < allNodes.size)
      throw oor(allRes.size, allNodes.size)
    recMap(allRes, allNodes, initMap)
  }
}

