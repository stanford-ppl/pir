package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._
import prism.codegen.CSVPrinter

trait GurobiComputePartitioner extends ComputePartitioning with SolverUtil { self:ComputePartitioner =>
  override protected def compCost(x:Any, ct:ClassTag[_]):Cost[_] = {
    switch[InputCost](x,ct) {
      case _: GlobalContainer | _:Context =>
        super.compCost(x,ct).as
      case x:PIRNode =>
        val ins = x.localIns.filter { in => in.isConnected && in.neighbors.forall { include } }
        val (vins, sins) = ins.partition { in => isVec(in) }
        InputCost(sins.size, vins.size)
      case x => super.compCost(x,ct).as

    } orElse switch[OutputCost](x,ct) {
      case _: GlobalContainer | _:Context =>
        super.compCost(x,ct).as
      case x: PIRNode => 
        val outs = x.localOuts.filter { out => out.isConnected && out.neighbors.forall { include } }
        val (vouts, souts) = outs.partition { out => isVec(out) }
        OutputCost(souts.size, vouts.size)
      case x => super.compCost(x,ct).as

    } getOrElse super.compCost(x,ct)
  }

  private def genNodes(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
    withCSV(config.splitDir, "node.csv") {
      nodes.foreach { n =>
        val row = newRow
        row("node") = n.id
        row("initTp") = "PCUParam"
        row("comment") = n
        val costs = getCosts(n)
        costs.foreach { c =>
          emitCost(c, row)
        }
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
            row("outid") = n.id
            row("src") = n.id
            row("dst") = in.src.id
            row("tp") = if (isVec(in)) "v" else "s"
            row("backEdge") = false
            row("comment") = s"${n} -> ${in.src}"
          }
        }
        n.localIns.foreach { in =>
          in.connected.foreach { out =>
            out.src match {
              case a:LocalOutAccess =>
                val row = newRow
                row(s"outid") = s"${a.id}"
                row(s"src") = s"${a.id}"
                row(s"dst") = s"${n.id}"
                row(s"tp") = if(isVec(in)) "v" else "s"
                row("backEdge") = false
                row("comment") = s"${a} -> ${n}"
              case _ =>
            }
          }
        }
      }
    }
  }

  private def genInit(nodes:List[PIRNode], vcost:List[Cost[_]]) = {
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
    withCSV(config.splitDir, "spec_cost.csv") {
      val row = newRow
      row("tp") = "PCUParam"
      vcost.foreach { c =>
        emitCost(c, row)
      }
    }
    withCSV(config.splitDir, "spec_count.csv") {
      val row = newRow
      row("PCUParam") = nodes.size
    }
    withCSV(config.splitDir,"costtp.csv") {
      val row = newRow
      vcost.foreach { c =>
        emitCostType(c, row)
      }
    }
  }

  override def partition(nodes:List[PIRNode], vcost:List[Cost[_]]) = if (splitAlgo=="solver"){
    genNodes(nodes,vcost)
    genEdges(nodes,vcost)
    //genInit(nodes,vcost)
    genSpec(nodes,vcost)
    val python = buildPath(config.pirHome, "env", "bin", "python")
    if (!exists(python)) {
      err(s"$python doesn't exists. Please do make install in ${config.pirHome}")
    }
    shell(
      header="partition", 
      command=s"${buildPath("env","bin","python")} ${buildPath("bin","merge.py")} ${config.splitDir} -t ${config.splitThread}", 
      cwd=config.pirHome,
      logPath=buildPath(config.splitDir, "partition.log")
    )
    val idmap = nodes.map { node => (node.id, node) }.toMap
    val partMap = getLines(buildPath(config.splitDir, "merge.csv")).map { line =>
      val tup = line.split(",")
      val node = tup(0).toInt
      val part = tup(1).toInt
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
