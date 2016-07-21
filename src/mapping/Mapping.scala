package pir.graph.mapping
import pir.graph.{ComputeUnit => CU, MemoryController => MC, _}
import pir._
import pir.plasticine.config._
import pir.plasticine.graph.{ComputeUnit => PCU, MemoryController => PMC}

import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.util.{Try, Success, Failure}

abstract class Mapping(implicit design:Design) {
  type R
  type N
  type V

  val mapping:Map[N, V]
  def reset:Unit = {}
  def printMap:Unit

  def simAneal(allRes:List[R], allNodes:List[N], initMap:Map[N,V], 
    constrains:List[(N, R, Map[N, V]) => Map[N, V]]): (Boolean, Map[N, V]) = {

    def recMap(usedRes:Set[R], remainNodes:List[N], map:Map[N, V]):(Boolean, Map[N, V]) = {
      if (remainNodes.size==0) {
        return (true, map)
      }
      val n::restNodes = remainNodes 

      // Try map n on all unused resource
      allRes.foldLeft((false, map)) { case ((preSuc, preMap), res) =>
        if (preSuc) // Already find a mapping for cu
          return (true, preMap)
        if (usedRes.contains(res))
          return (preSuc, preMap)

        // Checking whether mapping n on res satisfies all constrains 
        Try {
            constrains.foldLeft(preMap) { case (pm, cons) =>
            // Check current constrain, if success, update mapping
            cons(n, res, pm) 
          }
        } match {
          case Success(cmap) =>
            recMap(usedRes + res, restNodes, cmap)
          case Failure(e) =>
            e.printStackTrace
            (false, preMap)
        }

        //if (csuc) // If all constrains pass, recursively map next node 
        //  recMap(usedRes + res, restNodes, cmap)
        //else // If failed, try next res
        //  (false, preMap)
      }
    }

    recMap(Set[R](), allNodes, initMap)
  }
}

