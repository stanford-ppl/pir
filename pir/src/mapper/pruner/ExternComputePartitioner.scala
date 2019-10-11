package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait ExternComputePartitioner { self:ComputePartitioner =>

  lazy val nodeGen = new PIRTraversal with prism.codegen.CSVCodegen with ChildFirstTraversal {
    override def dirName = self.config.graphDir
    override lazy val logger = self.logger
    val fileName = "node.csv"
    override def runPass = {}
    def genNodes(nodes:List[PIRNode]) = {
      val ctx = nodes.head.ctx.get
      nodes.foreach { n =>
        val row = newRow
        row("node") = n.id
        row("op") = n.to[OpNode].fold(0){ n => 1 }
        row("comment") = s"$n"
      }
      super.run
    }
  } 

  lazy val edgeGen = new PIRTraversal with prism.codegen.CSVCodegen with ChildFirstTraversal {
    override def dirName = self.config.graphDir
    override lazy val logger = self.logger
    val fileName = "edge.csv"
    override def runPass = {}
    def genEdges(nodes:List[PIRNode]) = {
      val ctx = nodes.head.ctx.get
      val nodeSet = nodes.toSet
      nodes.foreach { n =>
        n.localOuts.foreach { out =>
          out.connected.foreach { in =>
            if (nodeSet.contains(in.src)) {
              val row = newRow
              row("src") = n.id
              row("dst") = in.src.id
              row("tp") = if (isVec(in)) "v" else "s"
              row("comment") = s"${n} -> ${in.src}"
            } else if (!in.src.isDescendentOf(ctx)) {
              val row = newRow
              row("src") = n.id
              row("dst") = s"e${in.src.id}"
              row("tp") = if (isVec(in)) "v" else "s"
              row("comment") = s"${n} -> ${in.src}"
            }
          }
        }
        n.localIns.foreach { in =>
          in.connected.foreach { out =>
            out.src match {
              case a:LocalOutAccess =>
                val row = newRow
                row(s"src") = s"e${a.id}"
                row(s"dst") = s"${n.id}"
                row(s"tp") = if(isVec(in)) "v" else "s"
                row("comment") = s"${a} -> ${n}"
              case _ =>
            }
          }
        }
      }
      super.run
    }
  } 

  lazy val specGen = new PIRTraversal with prism.codegen.CSVCodegen with ChildFirstTraversal {
    override def dirName = self.config.graphDir
    override lazy val logger = self.logger
    val fileName = "spec.csv"
    override def runPass = {}
    def genSpec(vcost:List[Cost[_]]) = {
      val (stageCost:StageCost)::(inCost:InputCost)::(outCost:OutputCost)::_ = vcost
      val row = newRow
      row("ops") = stageCost.quantity
      row("vin") = inCost.vin
      row("sin") = inCost.sin
      row("vout") = outCost.vout
      row("sout") = outCost.sout
      super.run
    }
  } 

  lazy val initGen = new PIRTraversal with prism.codegen.Codegen with ChildFirstTraversal {
    override def dirName = self.config.graphDir
    override lazy val logger = self.logger
    val fileName = "init.csv"
    override def runPass = {}
    def genInit(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
      super.initPass
      val parts = withAlgo("bfs") { binarySplit(nodes,vcost) }
      parts.zipWithIndex.foreach { case (p,i) =>
        p.scope.foreach { node =>
          emitln(s"${node.id}, $i")
        }
      }
      super.finPass
    }
  }

  def externSplit(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    nodeGen.genNodes(nodes)
    edgeGen.genEdges(nodes)
    specGen.genSpec(vcost)
    initGen.genInit(nodes,vcost)
    val script = buildPath(config.pirHome, "bin", "partition.py")
    shell("partition", s"$script ${config.graphDir}", buildPath(config.graphDir, "partition.log"))
    val idmap = nodes.map { node => (node.id, node) }.toMap
    val pidmap = getLines(buildPath(config.graphDir, "part.csv")).map { line =>
      val node::part::_ = line.split(",").map { _.toInt }.toList
      idmap(node) -> part
    }.toMap
    nodes.foreach { node => 
      if (!pidmap.contains(node)) {
        bug(s"${node} was not assigned with partition!")
      }
    }
    pidmap.groupBy { case (node, pid) => pid }.map { case (pid,nodes) =>
      new Partition(nodes.map { _._1 }.toList)
    }
  }

}

