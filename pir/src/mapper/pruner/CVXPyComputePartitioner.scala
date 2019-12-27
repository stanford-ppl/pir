package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._
import prism.codegen.CSVPrinter

trait CVXPyComputePartitioner extends ComputePartitioning with CSVPrinter { self:ComputePartitioner =>
  private def genNodes(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    withCSV(config.splitDir, "node.csv") {
      nodes.foreach { n =>
        val row = newRow
        row("node") = n.id
        row("op") = n.to[OpNode].fold(0){ n => 1 }
        row("retime") = n.isInstanceOf[Delay]
        row("comment") = s"$n"
      }
    }
  }

  private def genEdges(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    withCSV(config.splitDir, "edge.csv") {
      val ctx = nodes.head.ctx.get
      val nodeSet = nodes.toSet
      nodes.foreach { n =>
        n.localOuts.foreach { out =>
          out.connected.foreach { in =>
            val row = newRow
            row("src") = n.id
            if (nodeSet.contains(in.src)) {
              row("dst") = in.src.id
            } else if (!in.src.isDescendentOf(ctx)) {
              row("dst") = s"e${in.src.id}"
            }
            row("tp") = if (isVec(in)) "v" else "s"
            row("comment") = s"${n} -> ${in.src}"
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
    }
  }

  private def genInit(nodes:List[PIRNode], vcost:List[Cost[_]]):Unit = {
    withCSV(config.splitDir, "init.csv") {
      val parts = withAlgo("dfs") { partition(nodes,vcost) }
      parts.zipWithIndex.foreach { case (p,i) =>
        p.scope.foreach { node =>
          val row = newRow
          row("node") = node.id
          row("partition") = i
        }
      }
    }
  }

  private def genSpec(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    withCSV(config.splitDir, "spec.csv") {
      val (stageCost:StageCost)::(inCost:InputCost)::(outCost:OutputCost)::_ = vcost
      val row = newRow
      row("ops") = stageCost.quantity
      row("vin") = inCost.vin
      row("sin") = inCost.sin
      row("vout") = outCost.vout
      row("sout") = outCost.sout
    }
  }

  override def partition(nodes:List[PIRNode], vcost:List[Cost[_]]) = if (splitAlgo=="cvxpy") {
    genNodes(nodes,vcost)
    genEdges(nodes,vcost)
    genInit(nodes,vcost)
    genSpec(nodes,vcost)
    val python = buildPath(config.pirHome, "env", "bin", "python")
    if (!exists(python)) {
      err(s"$python doesn't exists. Please do make install in ${config.pirHome}")
    }
    shell(
      header="partition", 
      command=s"${buildPath("env","bin","python")} ${buildPath("bin","partition.py")} ${config.splitDir} -t ${config.splitThread}", 
      cwd=config.pirHome,
      logPath=buildPath(config.splitDir, "partition.log")
    )
    val idmap = nodes.map { node => (node.id, node) }.toMap
    val partMap = getLines(buildPath(config.splitDir, "part.csv")).map { line =>
      val node::part::_ = line.split(",").map { _.toInt }.toList
      idmap(node) -> part
    }.toMap
    nodes.foreach { node => 
      if (!partMap.contains(node)) {
        bug(s"${node} was not assigned with partition!")
      }
    }
    val parts = partMap.groupBy { case (node, pid) => pid }.map { case (pid,nodes) =>
      pid -> new Partition(nodes.map { _._1 }.toList)
    }
    val delayPath = buildPath(config.splitDir, "delay.csv")
    if (exists(delayPath)) {
      getLines(delayPath).foreach { line =>
        val node::delay::_ = line.split(",").toList
        parts(partMap(idmap(node.toInt))).delay = Some(delay.toFloat.toInt)
      }
    }
    dbgblk(s"Create Partitions") {
      parts.foreach { case (pid, part) =>
        dbg(s"$pid -> $part")
      }
    }
    parts.values.toList
  } else super.partition(nodes, vcost)

}
