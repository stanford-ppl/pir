package pir
package mapper

import pir.node._
import pir.util._
import prism.collection.immutable._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait ExternMerger extends GlobalMerging with CSVPrinter with PointToPointPlaceAndRoute { self =>

  override def mergeGlobals(x:CUMap) = if (config.mergeAlgo=="solver") {
    emitSpec(x)
    emitProgram(x)
    val python = buildPath(config.pirHome, "env", "bin", "python")
    if (!exists(python)) {
      err(s"$python doesn't exists. Please do make install in ${config.pirHome}")
    }
    shell(
      header="merge", 
      command=s"${buildPath(config.pirHome,"env","bin","python")} ${buildPath("bin","merge.py")} ${config.mergeDir} -t ${config.splitThread}", 
      cwd=config.pirHome,
      logPath=buildPath(config.mergeDir, "merge.log")
    )
    processMerge(x)
  } else super.mergeGlobals(x)

  private def processMerge(x:CUMap) = {
    val globs = x.freeKeys
    val idmap = globs.map { glob => (glob.id, glob) }.toMap
    val assignments = getLines(buildPath(config.mergeDir, "merge.csv")).map { line => 
      val tup = line.split(",")
      val glob = tup(0).toInt
      val newGlob = tup(1).toInt
      val param = tup(2)
      idmap(glob) -> (newGlob,param)
    }.toMap
    globs.foreach { glob =>
      if (!assignments.contains(glob)) {
        bug(s"$glob was not assigned with cu!")
      }
    }
    val paramMap = x.freeValues.groupBy { v => v.param.simpleName }
    assignments.groupBy { case (glob, (assignid, tp)) => (assignid,tp) }
      .foldLeft(CUMap()) { case (prev, ((assignid,tp), assigns)) =>
      val globs = assigns.map { _._1 }
      val newGlob = dbgblk(s"Create new global $assignid") {
        getNewGlobs(globs)
      }
      val assigned = paramMap(tp)
      prev ++ (newGlob -> assigned)
    }
  }

  private def emitSpec(x:CUMap) = {
    val terms = x.freeValues.groupBy { v => v.param }
    withCSV(config.mergeDir, "spec_cost.csv") {
      terms.keys.foreach { param =>
        val row = newRow
        val costs = getCosts(param)
        row("tp") = param.getClass.getSimpleName
        costs.foreach { c =>
          emitCost(c, row)
        }
      }
    }
    withCSV(config.mergeDir, "spec_count.csv") {
      val row = newRow
      terms.foreach { case (param, terms) =>
        row(param.getClass.getSimpleName) = terms.size
      }
    }
    withCSV(config.mergeDir,"costtp.csv") {
      val costs = getCosts(terms.keys.head)
      val row = newRow
      costs.foreach { c =>
        val tp = c match {
          case c:InputCost => "CustomCost"
          case c:OutputCost => "CustomCost"
          case c:PrefixCost[_] => "PrefixCost"
          case c:QuantityCost[_] => "QuantityCost"
          case c:MaxCost[_] => "MaxCost"
          case c:SetCost[_,_] => "SetCost"
        }
        c match {
          case c:QuantityCost[_] =>
            c.fieldNames.foreach { n =>
              row(s"${c.simpleName}_$n") = tp 
            }
          case c =>
            row(c.simpleName) = tp 
        }
      }
    }
  }

  private def emitProgram(x:CUMap) = {
    withCSV(config.mergeDir, "node.csv") {
      bind(x) match {
        case Left(f) =>
          x.freeKeys.foreach { glob =>
            val row = newRow
            row("node") = glob.id
            row("initTp") = "Unknown"
            row("comment") = glob
            val costs = getCosts(glob)
            costs.foreach { c =>
              emitCost(c, row)
            }
          }
        case Right(x) =>
          x.usedMap.fmap.keys.foreach { glob =>
            val row = newRow
            row("node") = glob.id
            row("initTp") = x.usedMap(glob).param.simpleName
            row("comment") = glob
            val costs = getCosts(glob)
            costs.foreach { c =>
              emitCost(c, row)
            }
          }
      }
    }
    withCSV(config.mergeDir, "edge.csv") {
      x.freeKeys.foreach { glob =>
        glob.outs.foreach { out =>
          out.connected.foreach { in =>
            val row = newRow
            val dstGlob = in.src.global.get
            row("outid") = in.src.id
            row("src") = glob.id
            row("dst") = dstGlob.id
            row("tp") = if (isVec(in)) "v" else "s"
            row("backEdge") = isBackEdge(in)
            row("comment") = s"${glob} -> ${dstGlob}"
          }
        }
      }
    }
  }

  private def emitCost(c:Cost[_],row:CSVRow) = c match {
    case c:PrefixCost[_] => row(c.simpleName) = c.prefix
    case c:QuantityCost[_] => 
      c.quantities.zip(c.fieldNames).foreach { case (q,n) =>
        row(s"${c.simpleName}_${n}") = q
      }
    case c:MaxCost[_] => 
      row(c.simpleName) = c.quantity
    case c:SetCost[_,_] => 
      row(c.simpleName) = c.set.mkString("|")
  }

}

