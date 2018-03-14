package pir.mapper

import pir._
import pir.node._

import spade.node._
import spade.network._

import prism._
import prism.util._

trait ResourcePruning { self:PIRPass =>


  def initMap = {
    var cumap = CUMap.empty

    val topP = compiler.top
    val topS = compiler.arch.top
    var pnodes1 = collectDown[GlobalContainer](topP)
    var snodes1 = spadeCollector.collectDown[Routable](topS)
    val (afgP, pnodes2) = pnodes1.partition(isAFG)
    val (afgS, snodes2) = snodes1.partition { case n:spade.node.ArgFringe => true }
    val (dfgP, pnodes3) = pnodes2.partition(isDFG)
    val (dfgS, snodes3) = snodes2.partition { case n:spade.node.MC => true }
    //val (dfgP, pnodes4) = pnodes3.partition(isDFG)
    //val (dfgS, snodes4) = snodes3.partition { case n:spade.node.MC => true }

    cumap ++= afgP.toSet[PNode] -> afgS.toSet[SNode]
    cumap ++= dfgP.toSet[PNode] -> dfgS.toSet[SNode]
  }
}
