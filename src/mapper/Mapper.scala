package pir.graph.mapper
import pir.graph.{ComputeUnit => CU, TileTransfer => TT, _}
import pir._
import pir.plasticine.config._
import pir.plasticine.graph.{Node => PNode, ComputeUnit => PCU, TileTransfer => PTT}

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

trait Mapper {
  type R
  type N
  type V
  type M = Map[N,V]

  def printMap(m:M)(implicit p:Printer):Unit

  def simAneal(allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M], 
    oor:(Int, Int) => OutOfResource)(implicit design:Design):M = {

    /* Recursively try a node on a list of resource */
    def recRes(remainRes:List[R], n:N, preMap:M):(M, List[R]) = {
      val exceps = ListBuffer[MappingException]()
      for (ir <- 0 until remainRes.size) {
        val (h, t) = remainRes.splitAt(ir)
        val res::rt = t
        Try {
          constrains.foldLeft(preMap) { case (pm, cons) => cons(n, res, pm) }
        } match {
          case Success(m) => return (m, h ++ rt)
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
      if (remainNodes.size==0) return recmap
      //assert(remainRes.size>0)
      val exceps = ListBuffer[MappingException]()
      for (in <- 0 until remainNodes.size) { 
        val (h,t) = remainNodes.splitAt(in)
        val n::rt = t
        Try{
          val (cm, rr) = recRes(remainRes, n, recmap)
          recMap(rr, h ++ rt, cm)
        } match {
          case Success(m) => return m 
          case Failure(e) => e match {
            case fe:FailToMapNode => exceps += fe // recRes failed
            case fe:NoSolFound => exceps += fe // recMap failed
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

