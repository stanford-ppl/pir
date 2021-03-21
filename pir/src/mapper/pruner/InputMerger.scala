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
    val globs = pirTop.children
    globs.filterNot { isBB(_) }.foldLeft(x) { case(map, glob) =>
      dbgblk(s"glob: $glob") {
        /* glob.collectDown[LocalOutAccess]().map { buf =>
          dbg(s"buf: $buf")
          dbg(s"buf.in: ${buf.in}")
          dbg(s"buf.in.T: ${buf.in.T}")
          dbg(s"buf.in.T.global: ${buf.in.T.global}")
        }*/
        val adj = glob.collectDown[LocalOutAccess]().map{ _.in.T.global.get }.filter{ _ != glob }.distinct.as[List[GlobalContainer]]
        adj.map { adj =>
          dbg(s"adj: $adj")
        }
        var canFit = true
        var (inpart, rest) = (glob :: adj).filterNot{isBB(_)}.as[List[GlobalContainer]].splitAt(1)
        while (canFit && rest.nonEmpty) {
          val next = rest.head
          inpart = inpart :+ next
          rest = rest.tail
          val part = new Partition(inpart)
          val kcost = getCosts(part)
          val nextvs = map.freeValuesOf(next)
          val vs = inpart.flatMap { k => map.freeValuesOf(k).filter(nextvs.contains) }.distinct
          canFit = if (vs.nonEmpty) {
            val vcost = getCosts(vs.groupBy { _.param }.maxBy { _._2.size }._1)
            fit(kcost, vcost)
          } else false
          dbg(s"kcost=$kcost")
          dbg(s"fit=$canFit inpart=$inpart next=$next $vs=${vs.map{_.param}.distinct}")
          if (!canFit) {
            rest = next :: rest
            inpart = inpart.slice(0,inpart.size-1)
          }
        }
        if (inpart.size!=1) {
          val newGlob = getNewGlobs(inpart)
          val kcost = getCosts(newGlob)
          val vcosts = inpart.flatMap { k => map.freeValuesOf(k).filter { v => 
              fit(kcost, getCosts(v)) 
            }
          }.toSet
          val newMap = map -- inpart ++ (newGlob -> vcosts)
          dbg(s"Split ${inpart.size}/${adj.size}")
          newMap
        } else map
      }
    }
  } else super.mergeGlobals(x)
  private def isBB(node:PIRNode):Boolean = {
    node match {
      case x:BlackBoxContainer => true; 
      case x => x.descendents.exists { _.isInstanceOf[BlackBox] }; 
    }
  }
}

