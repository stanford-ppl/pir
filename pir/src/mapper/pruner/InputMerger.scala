package pir
package mapper

import pir.node._
import pir.util._
import prism.collection.immutable._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait InputMerger extends GlobalMerging with CSVPrinter with PartitionCost { self =>

  override def mergeGlobals(x:CUMap) = if (config.mergeAlgo=="input") {
    dbg(s"Running the input merger!")
    val globs = pirTop.children.filterNot { isBB(_) }
    tryMerge(globs.as[List[GlobalContainer]], x)
    //globs.filterNot { isBB(_) }.foldLeft(x) { case(map, glob) =>
    //}
  } else super.mergeGlobals(x)

  private def tryMerge(globs:List[GlobalContainer], map:CUMap):CUMap = {
    if (globs.isEmpty)
      return map
    val glob = globs.head
    dbgblk(s"glob: $glob") {
      /* glob.collectDown[LocalOutAccess]().map { buf =>
        dbg(s"buf: $buf")
        dbg(s"buf.in: ${buf.in}")
        dbg(s"buf.in.T: ${buf.in.T}")
        dbg(s"buf.in.T.global: ${buf.in.T.global}")
      }*/
      val adj = glob.collectDown[LocalOutAccess]().map{ _.in.T.global.get }.filter{ _ != glob }.filterNot{isBB}.distinct.as[List[GlobalContainer]]
      val globWritesPar = writesParBlock(glob)
      val adj_fit = adj.filter { adj =>
        dbgblk(s"adj: $adj") {
          val inpart = List(glob, adj)
          val part = new Partition(inpart)
          val kcost = getCosts(part)
          val nextvs = map.freeValuesOf(adj) ++ map.freeValuesOf(glob)
          dbg(s"nextvs=$nextvs")
          val vs = inpart.flatMap { k => map.freeValuesOf(k).filter(nextvs.contains) }.distinct
          dbg(s"vs=$vs")
          val canFit = if (vs.nonEmpty) {
            val vcost = getCosts(vs.groupBy { _.param }.maxBy { _._2.size }._1)
            // val vcost = getCosts(map.mappedValue(glob))
            dbg(s"vcost=$vcost")
            fit(kcost, vcost)
          // } else if (glob.isInstanceOf[ArgFringe]) {
            // true 
          } else {
            false
          }
          val outsNotBothToPar = !(writesParBlock(adj) && globWritesPar)
          dbg(s"kcost=$kcost")
          dbg(s"fit=$canFit outsToPar=$outsNotBothToPar root=$glob next=$adj $vs=${vs.map{_.param}.distinct}")
          canFit && outsNotBothToPar
        }
      }
      if (!adj_fit.isEmpty) {
        val inpart = List(glob, adj_fit.head)
        val newGlob = getNewGlobs(inpart)
        val kcost = getCosts(newGlob)
        val vcosts = inpart.flatMap { k => map.freeValuesOf(k).filter { v => 
            fit(kcost, getCosts(v)) 
          }
        }.toSet
        val newMap = map -- inpart ++ (newGlob -> vcosts)
        dbg(s"Split ${inpart.size}/${adj.size}")
        // Successfully merged, try to merge again with this global
        tryMerge(newGlob :: globs.tail, newMap)
      } else {
        // Could not merge, advance to next global
        tryMerge(globs.tail, map)
      }
    }
  }

  private def writesParBlock(node:GlobalContainer):Boolean = {
    return node.collectDown[BufferWrite]().exists { _.out.T.exists { _.global.get.isParBlock.get }}
  }

  private def isBB(node:PIRNode):Boolean = {
    node match {
      case x:BlackBoxContainer => true; 
      case x => x.descendents.exists { _.isInstanceOf[BlackBox] }; 
    }
  }
}

