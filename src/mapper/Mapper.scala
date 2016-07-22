package pir.graph.mapper
import pir.graph.{ComputeUnit => CU, MemoryController => MC, _}
import pir._
import pir.plasticine.config._
import pir.plasticine.graph.{Node => PNode, ComputeUnit => PCU, MemoryController => PMC}

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

trait Mapper {
  type R
  type N
  type V
  type M = Map[N,V]

  def printMap(m:M)(implicit p:Printer):Unit

  def simAneal(allRes:List[R], allNodes:List[N], initMap:M, 
    constrains:List[(N, R, M) => M])(implicit design:Design):M = {

    def recMap(usedRes:Set[R], remainNodes:List[N], recmap:M):M = {
      if (remainNodes.size==0) return recmap
      val n::restNodes = remainNodes 

      // Try map n on all unused resource
      val (rsuc, rmap) = allRes.foldLeft((false, recmap)) { case ((preSuc, preMap), res) =>
        if (preSuc) {// Already find a mapping for cu
          (true, preMap)
        } else {
          if (usedRes.contains(res)) {
            (preSuc, preMap)
          } else {
            // Checking whether mapping n on res satisfies all constrains 
            Try { // Check current constrain, if success, update mapping, else throw an exception
              constrains.foldLeft(preMap) { case (pm, cons) => cons(n, res, pm) }
            } match {
              case Success(cmap) => // If all constrains pass, recursively map next node 
                Try(recMap(usedRes + res, restNodes, cmap)) match {
                  case Success(m) => (true, m) 
                  case Failure(e) => (false, preMap)
                }
              case Failure(e) => (false, preMap)
            }
          }
        }
      }

      if (!rsuc) throw NoSolFound(n)
      else rmap
    }

    recMap(Set[R](), allNodes, initMap)
  }
}

