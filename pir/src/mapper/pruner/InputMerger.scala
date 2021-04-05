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
      val adj = glob.collectDown[LocalOutAccess]().map{ _.in.T.global.get }.filter{ _ != glob }.filterNot{isBB}.distinct.as[List[GlobalContainer]].filterNot {_.isInstanceOf[ArgFringe] }
      // If we're merging into the ArgFringe, do not merge anything
      val adj_filt_argout = if (glob.isInstanceOf[ArgFringe]) {
        dbg(s"ArgFringe")
        if (config.mergeArgout == true) {
          dbg(s"Limit outputs!")
          adj.filterNot { adjNode =>
            adjNode.collectDown[LocalInAccess]().exists { outAcc =>
              outAcc.out.T.exists { inAcc =>
                if (inAcc.global.get != glob) {
                  dbg(s"Node $adjNode fail for $outAcc -> $inAcc!")
                }
                inAcc.global.get != glob
              }
            }
          /* }.filterNot { adjNode =>
            adjNode.collectDown[LocalOutAccess]().exists { inAcc =>
              if (inAcc.getTp != Bool && inAcc.in.T.global.get != glob) {
                dbg(s"Node $adjNode fail for $inAcc (tp=${inAcc.getTp}, glob=${inAcc.in.T.global.get})!")
                true
              } else {
                false
              }
            }*/
          }
        } else {
          dbg(s"prevent merge!")
          List()
        }
      } else {
        adj
      }
      dbg(s"Raw adjacency: $adj")
      dbg(s"Filtered adjacency: $adj_filt_argout")
      val globWritesPar = writesParBlock(glob)
      val adj_fit = adj_filt_argout.filter { adj_node =>
        dbgblk(s"adj: $adj_node") {
          val inpart = List(glob, adj_node)
          val part = new Partition(inpart)
          val kcost = getCosts(part)
          val nextvs = map.freeValuesOf(adj_node) ++ map.freeValuesOf(glob)
          dbg(s"nextvs=$nextvs")
          val vs = inpart.flatMap { k => map.freeValuesOf(k).filter(nextvs.contains) }.distinct
          dbg(s"vs=$vs")
          val canFit = if (vs.nonEmpty) {
            vs.groupBy { _.param }.exists { case (paramType, instances) =>
              val vcost = getCosts(paramType)
              // val vcost = getCosts(map.mappedValue(glob))
              dbg(s"vcost($paramType)=$vcost")
              fit(kcost, vcost)

            }
              /* val xA = vs.groupBy { _.param }
              dbg(s"\t$xA")
              val xB = xA.maxBy { _._2.size }
              dbg(s"\t$xB")
              val xC = xB._1
              dbg(s"\t$xC")
              val vcost = getCosts(xC)
              // val vcost = getCosts(map.mappedValue(glob))
              dbg(s"vcost=$vcost")
              fit(kcost, vcost)*/
          // } else if (glob.isInstanceOf[ArgFringe]) {
            // true 
          } else {
            false
          }
          val outsNotBothToPar = !(writesParBlock(adj_node) && globWritesPar)
          dbg(s"kcost=$kcost")
          dbg(s"fit=$canFit outsToPar=$outsNotBothToPar root=$glob next=$adj_node $vs=${vs.map{_.param}.distinct}")
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

