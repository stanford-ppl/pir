package pir.codegen

import pir._
import pir.mapper.PIRMap
import pir.util._
import pir.util.misc._
import pir.util.typealias._
import pir.plasticine.util._
import pir.plasticine.main._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class MapPrinter(implicit design: Design) extends Codegen {
  def shouldRun = Config.debug && design.mapping.nonEmpty

  var mp:PIRMap = _

  override lazy val stream = newStream("Mapping.log") 
  
  def emit(in:PI[PModule]) = {
    mp.fimap.get(in).foreach { from =>
      emitln(s"${quote(in)} <= ${quote(from)}")
    }
  }

  def emit(m:PModule):Unit = {
    emitModule(m, s"${quote(m)}") {}
  }

  def emit(pctr:PCtr):Unit = {
    mp.ctmap.pmap.get(pctr).foreach { ctr =>
      emitModule(pctr, s"${quote(pctr)} -> $ctr") {}
    }
  }

  def emit(pmem:POCM):Unit = {
    mp.smmap.pmap.get(pmem).foreach { mem =>
      emitModule(pmem, s"${quote(pmem)} -> $mem") {}
    }
  }

  def emit(pst:PST):Unit = {
    emitList(s"${quote(pst)} <- ${mp.stmap.pmap.get(pst)}"){
      pst.funcUnit.foreach { fu =>
        mp.stmap.pmap.get(pst).foreach { st =>
          fu.ins.foreach(emit)
          emitln(s"$fu.op=${st.fu.get.op}")
        }
      }
      pst.prs.foreach(pr => emit(pr.in))
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

  def emit(pcl:PCL):Unit = {
    mp.clmap.pmap.get(pcl).foreach { cl =>
      emitBlock(s"${quote(pcl)} -> $cl") {
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
      }
    }
  }

  override def initPass = {
    super.initPass
    if (mp==null) mp = design.mapping.get
  }

  addPass {
    design.arch.ctrlers.foreach(emit)
    emitBlock(s"mkmap") {
      mp.mkmap.map.foreach { case (k,v) =>
        emitln(s"${quote(k.src)}.$k -> $v")
      }
    }
  }

  def print(mapping:PIRMap) = {
    mp = mapping
    run
  }

  def emitModule(m:PModule, title:String)(block: =>Any) = {
    emitList(s"${title}") {
      m.ins.foreach(emit)
      block
    }
  }

  override def finPass = {
    close
    endInfo(s"Finishing MapPrinter Printing in ${getPath}")
  }

}
