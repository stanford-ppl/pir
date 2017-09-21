package pir.pass
import pir.node.{Const => _, _}
import pir._
import pir.util.typealias._
import pir.util._
import pirc.util._
import pirc.exceptions._
import pir.codegen.PIRPrinter
import spade._

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit design: PIR) extends Pass {
  import pirmeta._

  override def toString = "IRCheck"

  def shouldRun = true 

  def checkCounter(n:Node) = n match {
    case n:MemoryController =>
    case cu:OuterController =>
      if (cu.cchains.exists( _.isCopy)) throw PIRException(s"Outer controller cannot have counter copy")
    case cu:MemoryPipeline => //TODO fix kmeanas
      //cu.cchains.foreach { cc =>
        //assert((forRead(cc) && !forWrite(cc)) || (forWrite(cc) && !forRead(cc)), 
          //s"$cc in $cu forRead:${forRead(cc)} forWrite:${forWrite(cc)}")
      //}
    case cu:ComputeUnit =>
    case n =>
  }

  def checkUpdate(n:Node) = {
    if (n.toUpdate) {
      val printer = new PIRPrinter()
      printer.run
      printer.close
      throw PIRException(s"Node ${n} contains unupdated field/fields! Refer to ${printer.getPath} for more information")
    }
  }

  def checkChildren(n:Node) = n match {
    case n:Top =>
      if (n.children.filter{_.isLast}.size!=1) throw PIRException("Top must have single last stage")
    case _ =>
  }

  def checkMemory(n:Node) = n match {
    case n:SRAM => 
      if (!n.writePort.isConnected)
        throw PIRException(s"writePort of $n in ${n.ctrler} is not connected")
      if (!n.writeAddr.isConnected)
        throw PIRException(s"writeAddr of $n in ${n.ctrler} is not connected")
      if (!n.readAddr.isConnected)
        throw PIRException(s"readAddr of $n in ${n.ctrler} is not connected")
    case n:LocalMem =>
      n.ctrler match {
        case cu:MemoryPipeline =>//TODO: add this back
          //assert(forRead(n) || forWrite(n), s"$n in $cu forRead:${forRead(n)} forWrite:${forWrite(n)}")
        case _ =>
      }
    case n =>
  }

  def checkNetwork(arch:Spade) = arch match {
    case sn:SwitchNetwork =>
      sn.sbs.foreach { sb => 
        (sb.vectorIO.ins ++ sb.scalarIO.ins ++ sb.ctrlIO.ins).foreach { in => 
          if(in.fanIns.size>1) 
            throw PIRException(s"Switchbox ${quote(sb)} has $in with fanIns > 1 ${in.fanIns} ${in.fanIns.map(_.src)}")
        }
        (sb.vectorIO.outs ++ sb.scalarIO.outs ++ sb.ctrlIO.outs).foreach { out => 
          if(out.fanOuts.filterNot{_.src.isInstanceOf[PTop]}.size>1) 
            throw PIRException(s"Switchbox ${quote(sb)} has $out with fanOuts > 1 ${out.fanOuts} ${out.fanOuts.map(_.src)}")
        }
      }
      sn.cus.foreach { pcu =>
        (pcu.vectorIO.ins ++ pcu.scalarIO.ins ++ pcu.ctrlIO.ins).foreach { in => 
          if(in.fanIns.size>1) 
            throw PIRException(s"ComputeUnit ${quote(pcu)} has $in with fanIns > 1 ${in.fanIns} ${in.fanIns.map(_.src)}")
        }
        pcu.ctrlIO.outs.foreach { out => 
          if(out.fanOuts.size>1) 
            throw PIRException(s"ComputeUnit ${quote(pcu)} has $out with fanOuts > 1 ${out.fanOuts} ${out.fanOuts.map(_.src)}")
        }
      }
    case _ =>
  }

  def checkSpade = {
    design.arch.cus.foreach { cu =>
      cu.stages.zipWithIndex.foreach { case (stage, i) =>
        if (stage!=cu.stages.head) assert(stage.prev==Some(cu.stages(i-1)))
        else assert(stage.prev==None)
        if (stage!=cu.stages.last) assert(stage.next==Some(cu.stages(i+1)))
        else assert(stage.next==None)
      }
    }
    checkNetwork(design.arch)
  }

  def checkPIR = {
    design.allNodes.foreach{ n => 
      checkCounter(n)
      checkUpdate(n)
      checkChildren(n)
      checkMemory(n)
    }
  }

  addPass {
    checkPIR
    checkSpade
  } 

}
