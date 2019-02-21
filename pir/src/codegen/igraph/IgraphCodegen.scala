package pir
package codegen

import pir.node._
import pir.mapper._
import prism.graph._
import prism.codegen._

class IgraphCodegen(implicit compiler:PIR) extends PIRTraversal with PythonCodegen with ChildFirstTraversal with CUCostUtil {
  override def dirName = buildPath(config.outDir, s"../igraph")
  val fileName = "graph.py"

  override def initPass = {
    clearDir(dirName)
    super.initPass
    emitln(s"from igraph import *")
    emitln(s"g = Graph(directed=True)")
  }

  override def finPass = {
    pirTop.collectDown[GlobalOutput]().foreach { emitOutput }
    super.finPass
  }

  override def emitNode(n:N) = n match {
    case n:GlobalContainer =>
      val attr = scala.collection.mutable.Map[String, Any]()
      attr += "stage" -> n.getCost[StageCost].quantity
      attr += "lane" -> n.getCost[LaneCost].quantity
      attr += "sramCount" -> n.getCost[SRAMCost].count
      attr += "sramBank" -> n.getCost[SRAMCost].bank
      attr += "sramSize" -> n.getCost[SRAMCost].size
      val attrs = attr.map { case (k,v) => s"""$k=$v""" }.mkString(",")
      emitln(s"""g.add_vertex("$n", $attrs)""")
    case n => visitNode(n)
  }

  def emitOutput(n:GlobalOutput) = {
    n.out.T.foreach { gin =>
      val attr = scala.collection.mutable.Map[String, Any]()
      attr += s"name" -> s""""$n""""
      attr += s"vec" -> n.getVec
      n.count.v.foreach { _.foreach { c => attr += s"count" -> c } }
      val attrs = attr.map { case (k,v) => s"""$k=$v""" }.mkString(",")
      emitln(s"""g.add_edge("${n.global.get}", "${gin.global.get}", $attrs)""")
    }
  }
}
