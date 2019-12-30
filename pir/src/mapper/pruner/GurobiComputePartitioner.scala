package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._
import prism.codegen.CSVPrinter

trait GurobiComputePartitioner extends ComputePartitioning with SolverUtil { self:ComputePartitioner =>

  case class ExtInCost(prefix:Boolean=false) extends PrefixCost[ExtInCost]
  case class ExtOutCost(prefix:Boolean=false) extends PrefixCost[ExtOutCost]

  var extInVecCost = 0
  var extInScalCost = 0
  var extOutVecCost = 0
  var extOutScalCost = 0
  override protected def compCost(x:Any, ct:ClassTag[_]):Cost[_] = {
    switch[StageCost](x,ct) {
      case -1 => StageCost(0)
      case -2 => StageCost(0)
    } orElse switch[InputCost](x,ct) {
      case -1 => InputCost(0,0)
      case -2 => InputCost(extOutScalCost,extOutVecCost)
      case _: GlobalContainer | _:Context =>
        super.compCost(x,ct).as
      case x:PIRNode =>
        val ins = x.localIns.filter { in => in.isConnected && in.neighbors.forall { include } }
        val (vins, sins) = ins.partition { in => isVec(in) }
        InputCost(sins.size, vins.size)
      case x => super.compCost(x,ct).as

    } orElse switch[OutputCost](x,ct) {
      case -1 => OutputCost(extInScalCost,extInVecCost)
      case -2 => OutputCost(0,0)
      case _: GlobalContainer | _:Context =>
        super.compCost(x,ct).as
      case x: PIRNode => 
        val outs = x.localOuts.filter { out => out.isConnected && out.neighbors.forall { include } }
        val (vouts, souts) = outs.partition { out => isVec(out) }
        OutputCost(souts.size, vouts.size)
      case x => super.compCost(x,ct).as

    } orElse switch[ExtInCost](x,ct) {
      case -1 => ExtInCost(true)
      case _ => ExtInCost(false)

    } orElse switch[ExtOutCost](x,ct) {
      case -2 => ExtOutCost(true)
      case _ => ExtOutCost(false)

    } getOrElse super.compCost(x,ct)
  }

  private def getCostsX(x:Any):List[Cost[_]] = getCosts(x) :+ x.getCost[ExtInCost] :+ x.getCost[ExtOutCost]

  private def genProgram(nodes:List[PIRNode]) = {

    val nodeCSV = new CSVPrinter {}
    val edgeCSV = new CSVPrinter {}

    extInVecCost = 0
    extInScalCost = 0
    extOutVecCost = 0
    extOutScalCost = 0
    nodes.foreach { n =>
      val row = nodeCSV.newRow
      row("node") = n.id
      row("initTp") = "PCUParam"
      row("comment") = n
      val costs = getCostsX(n)
      costs.foreach { c =>
        emitCost(c, row)
      }
      n.localOuts.foreach { out =>
        out.connected.foreach { in =>
          val row = edgeCSV.newRow
          val vec = isVec(in)
          val dst = in.src match {
            case _:LocalOutAccess => 
              if (vec)
                extOutVecCost += 1
              else
                extOutScalCost += 1
              -2
            case _ => in.src.id
          }
          row("outid") = n.id
          row("src") = n.id
          row("dst") = dst
          row("tp") = if (vec) "v" else "s"
          row("backEdge") = false
          row("comment") = s"${n} -> ${in.src}"
        }
      }
      n.localIns.foreach { in =>
        in.connected.foreach { out =>
          out.src match {
            case a:LocalOutAccess =>
              val row = edgeCSV.newRow
              val vec = isVec(in)
              row(s"outid") = s"${a.id}"
              row(s"src") = -1
              row(s"dst") = s"${n.id}"
              row(s"tp") = if(vec) "v" else "s"
              row("backEdge") = false
              row("comment") = s"${a} -> ${n}"
              if (vec)
                extInVecCost += 1
              else
                extInScalCost += 1
            case _ =>
          }
        }
      }
    }
    var row = nodeCSV.newRow
    row("node") = -1
    row("initTp") = "ExternIn"
    row("comment") = "ExternIn"
    getCostsX(-1).foreach { c =>
      emitCost(c, row)
    }
    row = nodeCSV.newRow
    row("node") = -2
    row("initTp") = "ExternOut"
    row("comment") = "ExternOut"
    getCostsX(-2).foreach { c =>
      emitCost(c, row)
    }

    nodeCSV.writeToCSV(config.splitDir, "node.csv")
    edgeCSV.writeToCSV(config.splitDir, "edge.csv")
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
      var row = newRow
      row("tp") = "PCUParam"
      vcost.foreach { c =>
        emitCost(c, row)
      }
      row = newRow
      row("tp") = "ExternIn"
      emitCost(StageCost(0), row)
      emitCost(InputCost(1000,1000), row)
      emitCost(OutputCost(1000,1000), row)
      emitCost(ExtInCost(true),row)
      emitCost(ExtOutCost(false),row)
      row = newRow
      row("tp") = "ExternOut"
      emitCost(StageCost(0), row)
      emitCost(InputCost(1000,1000), row)
      emitCost(OutputCost(1000,1000), row)
      emitCost(ExtInCost(false),row)
      emitCost(ExtOutCost(true),row)
    }
    withCSV(config.splitDir, "spec_count.csv") {
      val row = newRow
      row("PCUParam") = nodes.size
      row("ExternIn") = 1
      row("ExternOut") = 1
    }
    withCSV(config.splitDir,"costtp.csv") {
      val row = newRow
      vcost.foreach { c =>
        emitCostType(c, row)
      }
    }
  }

  override def partition(nodes:List[PIRNode], vcost:List[Cost[_]]) = if (splitAlgo=="gurobi"){
    genProgram(nodes)
    //genInit(nodes,vcost)
    genSpec(nodes,vcost :+ ExtInCost(false) :+ ExtOutCost(false))
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
    val partMap = getLines(buildPath(config.splitDir, "merge.csv")).flatMap { line =>
      val tup = line.split(",")
      val node = tup(0).toInt
      val part = tup(1).toInt
      node match {
        case -1 => assert(tup(2)=="ExternIn"); None
        case -2 => assert(tup(2)=="ExternOut"); None
        case node => Some(idmap(node) -> part)
      }
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
