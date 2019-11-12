package pir
package mapper

import pir.node._
import pir.util._
import spade.param._
import prism.collection.immutable._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

class GlobalMerger(implicit compiler:PIR) extends PIRTransformer with CUCostUtil with CSVPrinter with MappingLogger { self =>

  def merge[T](x:T):T = (x match {
    case x:TopMap => x.mapAll(field => merge[Any](field))
    case x:CUMap if !spadeParam.isAsic => mergeGlobals(x)
    case x => x
  }).asInstanceOf[T]

  override def runPass = {
    topMap = topMap.map { tmap => merge[TopMap](tmap) }
  }

  def mergeGlobals(x:CUMap) = {
    emitSpec(x)
    emitProgram(x)
    val python = buildPath(config.pirHome, "env", "bin", "python")
    if (!exists(python)) {
      err(s"$python doesn't exists. Please do make install in ${config.pirHome}")
    }
    shell(
      header="merge", 
      command=s"${buildPath("env","bin","python")} ${buildPath("bin","merge.py")} ${config.mergeDir} -t ${config.splitThread}", 
      cwd=config.pirHome,
      logPath=buildPath(config.mergeDir, "merge.log")
    )
    logging(x)
    val newMap = processMerge(x)
    logging(newMap)
    validate(x)
    newMap
  }

  def validate(x:CUMap) = {
    x.freeKeys.foreach { k =>
      val values = x.freeValuesOf(k)
      values.foreach { v =>
        val kcost = getCosts(k)
        val vcost = getCosts(v)
        if (!fit(kcost, vcost))
          bug(s"$k cannot fit ${v} after merging!\nkcost=$kcost\nvcost=$vcost")
      }
    }
  }

  def processMerge(x:CUMap) = {
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
      val newGlob = within(pirTop) { 
        val (other,comp) = globs.partition { _.isInstanceOf[ComputePartitioner] }
        val toMirror = other.headOption.getOrElse(comp.head)
        mirrorAll(List(toMirror))(toMirror).asInstanceOf[GlobalContainer]
      }
      dbgblk(s"Create new global $assignid") {
        globs.foreach { g =>
          g.children.foreach { c => 
            swapParent(c, newGlob)
          }
        }
      }
      free(globs)
      val assigned = paramMap(tp)
      prev ++ (newGlob -> assigned)
    }
  }

  def getCosts(x:Any) = {
    x.getCost[AFGCost] ::
    x.getCost[MCCost] ::
    x.getCost[MergeBufferCost] ::
    x.getCost[SplitterCost] ::
    x.getCost[LockCost] ::
    x.getCost[SRAMCost] ::
    x.getCost[InputCost] ::
    x.getCost[OutputCost] ::
    x.getCost[StageCost] ::
    x.getCost[LaneCost] ::
    x.getCost[OpCost] ::
    x.getCost[ActorCost] ::
    Nil
  }

  def emitSpec(x:CUMap) = {
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

  def emitProgram(x:CUMap) = {
    withCSV(config.mergeDir, "node.csv") {
      x.freeKeys.foreach { glob =>
        val row = newRow
        row("node") = glob.id
        row("initTp") = x.freeValuesOf(glob).head.param.simpleName
        row("comment") = glob
        val costs = getCosts(glob)
        costs.foreach { c =>
          emitCost(c, row)
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
            val backEdge = if (in.src.isUnder[ArgFringe]) true
            else {
              out.src.ctx.get.collectDown[LocalOutAccess]().exists { _.initToken.get }
            }
            row("backEdge") = backEdge
            row("comment") = s"${glob} -> ${dstGlob}"
          }
        }
      }
    }
  }

  def emitCost(c:Cost[_],row:CSVRow) = c match {
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
