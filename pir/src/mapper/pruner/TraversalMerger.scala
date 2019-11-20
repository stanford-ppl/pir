package pir
package mapper

import pir.node._
import pir.util._
import prism.collection.immutable._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TraversalMerger extends GlobalMerging with CSVPrinter with PartitionCost { self =>

  override def mergeGlobals(x:CUMap) = scheduler.map { scheduler =>
    val globs = pirTop.children
    val nodes = scheduler.scheduleScope(globs).as[List[GlobalContainer]]
    merge(nodes, x, scheduler)
  }.getOrElse(super.mergeGlobals(x))

  private def merge(nodes:List[GlobalContainer],x:CUMap, scheduler:GlobalScheduler):CUMap = {
    if (nodes.size==0) return x
    var canFit = true
    var (inpart, rest) = nodes.splitAt(1)
    while (canFit && rest.nonEmpty) {
      val next = rest.head
      inpart = inpart :+ next
      rest = rest.tail
      val part = new Partition(inpart)
      val kcost = getCosts(part)
      val nextvs = x.freeValuesOf(next)
      val vs = inpart.flatMap { k => x.freeValuesOf(k).filter(nextvs.contains) }.distinct
      canFit = if (vs.nonEmpty) {
        val vcost = getCosts(vs.groupBy { _.param }.maxBy { _._2.size }._1)
        fit(kcost, vcost)
      } else false
      //dbg(s"kcost=$kcost")
      //dbg(s"fit=$canFit inpart=$inpart next=$next $vs=${vs.map{_.param}.distinct}")
      if (!canFit) {
        rest = next :: rest
        inpart = inpart.slice(0,inpart.size-1)
      }
    }
    val newMap = if (inpart.size!=1) {
      val newGlob = getNewGlobs(inpart)
      val kcost = getCosts(newGlob)
      val vcosts = inpart.flatMap { k => x.freeValuesOf(k).filter { v => 
          fit(kcost, getCosts(v)) 
        }
      }.toSet
      val newMap = x -- inpart ++ (newGlob -> vcosts)
      dbg(s"Split ${inpart.size}/${nodes.size}")
      newMap
    } else x
    if (config.mergeAlgo=="dfs") {
      val restSorted = scheduler.scheduleScope(rest.reverse).as[List[GlobalContainer]]
      merge(restSorted,newMap,scheduler)
    } else {
      merge(rest,newMap,scheduler)
    }
  }

  trait GlobalScheduler extends TopologicalTraversal with Scheduler { 
    type N = PIRNode
    val forward = config.mergeForward 
    override def visitIn(n:N) = self.visitIn(n)
    override def visitOut(n:N) = self.visitOut(n)
  }

  private def visitIn(n:PIRNode):List[PIRNode] = {
    n.ins.flatMap { in => 
      if (isBackEdge(in)) Nil else in.connected.map { _.src.global.get }
    }.toList.distinct
  }

  private def visitOut(n:PIRNode):List[PIRNode] = {
    n.outs.flatMap { out => out.connected.flatMap { in =>
        if (isBackEdge(in)) None else Some(in.src.global.get)
      }
    }.toList.distinct
  }

  private def scheduler = config.mergeAlgo match {
    case "bfs" => Some(new BFSTopologicalTraversal with GlobalScheduler {})
    case "dfs" => Some(new DFSTopologicalTraversal with GlobalScheduler {})
    case _ => None
  }

}

