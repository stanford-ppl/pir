package pir.mapper

import pir._
import pir.node._

import spade.node._
import spade.network._

import prism._
import prism.util._

import scala.collection.mutable

class DynamicCUPlacer(implicit compiler:PIR) extends PIRPass {
  def shouldRun = isMesh(compiler.arch.top) && isDynamic(compiler.arch.top)

  import pirmeta._

  // TODO: change this to metadata
  val mapping = mutable.Map[PNode, SNode]()

  override def runPass(runner:RunPass[_]) =  {
    val topP = compiler.top
    val topS = compiler.arch.top.asInstanceOf[DynamicMeshTop]
    val rest1 = collectDown[GlobalContainer](topP)
    val (afgP, rest2) = rest1.partition { n => isAFG(n) }
    val (dfgP, rest3) = rest2.partition { n => isDFG(n) }
    val (pmuP, rest4) = rest3.partition { n => isPMU(n) }
    val pcuP = rest3

    val dfgS = spadeCollector.collectDown[MC](topS)
    val pmuS = spadeCollector.collectDown[PMU](topS)
    val pcuS = spadeCollector.collectDown[PCU](topS)

    mapNodes(dfgP, dfgS, mapping)
    mapNodes(pcuP, pcuS, mapping)
    mapNodes(pmuP, pmuS, mapping)
  }

  def mapNodes(pnodes:List[PNode], snodes:List[SNode], mapping:mutable.Map[PNode, SNode]) = {
    val tp = pnodes.head.getClass
    dbg(s"tp:$tp pnodes:${pnodes.size} snodes:${snodes.size}")
    assert(snodes.size >= pnodes.size, s"Not enough resource to map $tp\n, pnodes=${pnodes.size}, snodes=${snodes.size}")
    pnodes.zip(snodes).foreach { case (p, s) =>
      mapping += p -> s
      dbg(s"map $p -> $s")
    }
  }

}
