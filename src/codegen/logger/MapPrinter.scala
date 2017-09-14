package pir.codegen

import pir._
import pir.mapper.PIRMap
import pir.util._
import pir.util.misc._
import pir.util.typealias._
import pir.spade.util._
import pir.spade.main._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class MapPrinter(implicit design: Design) extends Codegen {
  def shouldRun = Config.debug && Config.mapping

  implicit lazy val mp:PIRMap = design.mapping.get

  override lazy val stream = newStream("Mapping.log") 
  
  def emit(pin:PI[PModule]) = {
    val fin = fanInOf(pin).fold("") { from =>
      s" <= ${quote(from)}"
    }
    val ip = mp.ipmap.pmap.get(pin).fold("") { in =>
      s"  (${in})"
    }
    val gip = pin match {
      case pin:PGI[PModule] => mp.vimap.pmap.get(pin).fold("") { in => s" ($in)" }
      case pin => ""
    }
    emitln(s"${quote(pin)}$fin$ip$gip")
  }

  def emit(pout:PO[PModule]) = {
    val fout = if (fanOutOf(pout).nonEmpty) {
      s" => [${fanOutOf(pout).map(quote).mkString(",")}]"
    } else ""
    val op = mp.opmap.pmap.get(pout).fold("") { out =>
      s" (${out})"
    }
    val gop = pout match {
      case pout:PGO[PModule] => mp.vomap.pmap.get(pout).fold("") { out => s" ($out)" }
      case pout =>
    }
    emitln(s"${quote(pout)}$fout$op$gop")
  }

  def emit(m:PModule):Unit = {
    emitModule(m, s"${quote(m)}") {}
  }

  //TODO: merge with ocm
  def emit(pctr:PCtr):Unit = {
    mp.pmmap.get(pctr).foreach { ctr =>
      emitModule(pctr, s"${quote(pctr)} -> $ctr") {}
    }
  }

  def emit(pmem:POCM):Unit = {
    mp.pmmap.get(pmem).foreach { mem =>
      emitModule(pmem, s"${quote(pmem)} -> $mem") {}
    }
  }

  def emit(pst:PST):Unit = {
    emitList(s"${quote(pst)} <- ${mp.pmmap.pmap.get(pst)}"){
      mp.pmmap.get(pst).foreach { st =>
        pst.funcUnit.foreach { pfu =>
          val fu = st.fu.get
          emitln(s"$pfu <- $fu")
          pfu.ins.foreach(emit)
          emitln(s"$pfu.op=${fu.op}")
        }
        pst.prs.foreach(pr => emit(pr.in))
      }
    }
  }

  def emit(preg:PReg):Unit = {
    mp.rcmap.pmap.get(preg).foreach { regs =>
      emitln(s"${quote(preg)} -> [${regs.mkString(",")}]")
    }
  }

  def emit(reg:Reg):Unit = {
    mp.rcmap.get(reg).foreach { pregs =>
      emitln(s"${reg} -> [${pregs.map(quote).mkString(",")}]")
    }
  }

  def emit(pprim:PPRIM):Unit = {
    val title = mp.pmmap.pmap.get(pprim).fold {
      s"${quote(pprim)}"
    } { prim =>
      s"${prim} -> ${quote(pprim)}"
    }
    emitModule(pprim, title) {}
  }

  def emit(pcb:PCB):Unit = {
    val cb = mp.pmmap.pmap.get(pcb)
    emitModule(pcb, s"${quote(pcb)} -> $cb") {
      pcb.udcs.foreach(emit)
      pcb.andTrees.foreach(emit)
      pcb.andGates.foreach(emit)
      pcb.delays.foreach(emit)
    }
  }

  def emit(pcl:PCL):Unit = {
    mp.clmap.pmap.get(pcl).foreach { cl =>
      emitModule(pcl, s"${quote(pcl)} -> $cl") {
        (cl, pcl) match {
          case (cu:CU, pcu:PCU) =>
            pcu.ctrs.foreach(emit)
            pcu.mems.foreach(emit)
            pcu.stages.foreach(emit)
            emitList(s"pregs") { pcu.regs.foreach(emit) }
            emitList(s"regs") { cu.regs.foreach(emit) }
          case _ =>
        }
        pcl.souts.map(sout => emit(sout.ic))
        pcl.vouts.map(vout => emit(vout.ic))
        emit(pcl.ctrlBox)
      }
    }
  }

  override def initPass = {
    super.initPass
  }

  addPass(canRun=design.mapping.nonEmpty) {
    design.arch.ctrlers.foreach(emit)
    design.arch match {
      case sn:SwitchNetwork =>
        sn.sbs.foreach(emit)
      case _ =>
    }
    emitBlock(s"mkmap") {
      mp.mkmap.map.foreach { case (k,v) =>
        emitln(s"${quote(k.src)}.$k -> $v")
      }
    }
  }

  def print(mapping:PIRMap) = {
    run
  }

  def emitModule(m:PModule, title:String)(block: =>Any) = {
    emitList(s"${title}") {
      m.ins.foreach(emit)
      m.outs.foreach(emit)
      block
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing MapPrinter Printing in ${getPath}")
  }

}
