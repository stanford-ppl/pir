package pir.pass
import pir.graph.{Const => _, _}
import pir._
import pir.plasticine.main._
import pir.util.typealias._
import pir.util._
import pir.util.misc._
import pir.exceptions._
import pir.codegen.PIRPrinter

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit design: Design) extends Pass {
  import pirmeta._

  def shouldRun = true 

  override def traverse:Unit = {
    design.allNodes.foreach{ n => 
      if (n.toUpdate) {
        val printer = new PIRPrinter()
        printer.run
        printer.close
        throw PIRException(s"Node ${n} contains unupdated field/fields! Refer to ${printer.getPath} for more information")
      }
      n match {
        case top:Top => 
          if (top.children.filter{_.isLast}.size!=1) throw PIRException("Top must have single last stage")
        case cu:OuterController =>
          //if (cu.children.size==1)
            //warn(s"Nested Single Stage. Control won't be correctly generated at the moment! ${cu} children:[${cu.children.mkString(",")}]")
          cu match {
            case cu:StreamController =>
              cu.children.foreach { c =>
                if (!c.isHead && !c.isInstanceOf[StreamPipeline])
                  throw PIRException("Only first stage in StreamController can be non-StreamPipeline")
              }
            case _ =>
          }
        case cu:Controller =>
        case n:CtrlBox =>
        case n:Counter =>
          n.ctrler match {
            case cu:OuterController =>
              if (cu.cchains.exists( _.isCopy)) 
                throw PIRException(s"Outer controller cannot have counter copy")
            case cu:MemoryPipeline =>
              cu.cchains.foreach { cc =>
                assert((forRead(cc) && !forWrite(cc)) || (forWrite(cc) && !forRead(cc)), s"$n in $cu forRead:${forRead(n)} forWrite:${forWrite(n)}")
              }
            case _ =>
          }
        case n:SRAM => 
          if (!n.writePort.isConnected)
            throw PIRException(s"writePort of $n in ${n.ctrler} is not connected")
          if (!n.writeAddr.isConnected)
            throw PIRException(s"writeAddr of $n in ${n.ctrler} is not connected")
          if (!n.readAddr.isConnected)
            throw PIRException(s"readAddr of $n in ${n.ctrler} is not connected")
        case n:LocalMem =>
          n.ctrler match {
            case cu:MemoryPipeline =>
              assert(forRead(n) || forWrite(n), s"$n in $cu forRead:${forRead(n)} forWrite:${forWrite(n)}")
            case _ =>
          }
        case n =>
      }
    }
    design.arch match {
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
    design.arch.cus.foreach { cu =>
      cu.stages.zipWithIndex.foreach { case (stage, i) =>
        if (stage!=cu.stages.head) assert(stage.prev==Some(cu.stages(i-1)))
        else assert(stage.prev==None)
        if (stage!=cu.stages.last) assert(stage.next==Some(cu.stages(i+1)))
        else assert(stage.next==None)
      }
    }
  } 

  override def finPass = {
    endInfo("Finishing checking mutable fields")
    design.arch match {
      case sn:SwitchNetwork =>
        //val dbw = sn.switchNetworkDataBandwidth
        //val cbw = sn.switchNetworkCtrlBandwidth
        //dprint(s"Switch Network Data Bandwidth:${dbw} Ctrl Bandwidth:${cbw}")
        //design.top.spadeCtrlers.foreach { cu =>
          //cu.vins.groupBy { vin =>
            //vin.writer
          //}.foreach { case (fromcu, vins) =>
            //if (vins.size > dbw)
              //warn(s"Data Connectivity from $fromcu to $cu: ${vins.size}. Data Bandwidth:$dbw")
          //}
          //cu.ctrlIns.groupBy { cin =>
            //if (!cin.isConnected) throw PIRException(s"$cin is not connected")
            //cin.from.asInstanceOf[CtrlOutPort].ctrler
          //}.foreach { case (fromcu, cins) =>
            //if (cins.size > cbw)
              //warn(s"Ctrl Connectivity from $fromcu to $cu: ${cins.size}. Ctrl Bandwidth:$cbw")
          //}
        //}
      case pn:PointToPointNetwork =>
    }
  }

}
