package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._
import prism.codegen.CSVPrinter

trait ExternComputePartitioner { self:ComputePartitioner =>

  lazy val nodeGen = new CSVPrinter {
    val dirName = self.config.graphDir
    val fileName = "node.csv"
    val append = false
    def genNodes(nodes:List[PIRNode]) = {
      val ctx = nodes.head.ctx.get
      nodes.foreach { n =>
        val row = newRow
        row("node") = n.id
        row("op") = n.to[OpNode].fold(0){ n => 1 }
        row("comment") = s"$n"
      }
      gencsv
    }
  } 

  lazy val edgeGen = new CSVPrinter {
    val dirName = self.config.graphDir
    val fileName = "edge.csv"
    val append = false
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
      gencsv
    }
  } 

  lazy val specGen = new CSVPrinter {
    val dirName = self.config.graphDir
    val fileName = "spec.csv"
    val append = false
    def genSpec(vcost:List[Cost[_]]) = {
      val (stageCost:StageCost)::(inCost:InputCost)::(outCost:OutputCost)::_ = vcost
      val row = newRow
      row("ops") = stageCost.quantity
      row("vin") = inCost.vin
      row("sin") = inCost.sin
      row("vout") = outCost.vout
      row("sout") = outCost.sout
      gencsv
    }
  } 

  lazy val initGen = new CSVPrinter {
    val dirName = self.config.graphDir
    val fileName = "init.csv"
    val append = false
    override val printHeader = false
    def genInit(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
      val parts = withAlgo("bfs") { binarySplit(nodes,vcost) }
      parts.zipWithIndex.foreach { case (p,i) =>
        p.scope.foreach { node =>
          val row = newRow
          row("node") = node.id
          row("part") = i
        }
      }
      gencsv
    }
  }

  def externSplit(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    nodeGen.genNodes(nodes)
    edgeGen.genEdges(nodes)
    specGen.genSpec(vcost)
    initGen.genInit(nodes,vcost)
    val script = buildPath(config.pirHome, "bin", "partition.py")
    shell("partition", s"$script ${config.graphDir} -t ${config.splitThread}", buildPath(config.graphDir, "partition.log"))
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

