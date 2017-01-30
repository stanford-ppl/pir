package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.{PIRException, TODOException}
import pir.plasticine.main._
import pir.typealias._

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit val design: Design) extends Traversal {
  implicit val spade = design.arch
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
          if (top.children.size!=1) throw PIRException(s"Top must have a single children!")
        case cu:OuterController =>
          if (cu.children.size==1)
            warn(s"Nested Single Stage. Control won't be correctly generated at the moment! ${cu} children:[${cu.children.mkString(",")}]")
          cu match {
            case cu:StreamController =>
              cu.children.foreach { c =>
                if (!c.isHead && !c.isInstanceOf[StreamPipeline])
                  throw PIRException("Only first stage in StreamController can be non-StreamPipeline")
                if (c.isHead) {
                  //assert(c.mems.collect{ case mem:FIFOOnRead => mem}.size==0, 
                    //s"First stage of StreamController shouldn't have FIFOOnRead: ${c}")
                } else {
                  if (!c.ctrlBox.tokenBuffers.isEmpty) throw PIRException(s"StreamController's children other than the first stage shouldn't have tokenBuffer")
                }
                if (!c.ctrlBox.creditBuffers.isEmpty) throw PIRException(s"StreamController's children shouldn't have creditBuffer")
              }
            case _ =>
          }
        case cu:Controller =>
          cu.ctrlReaders.foreach { reader =>
            if (reader == cu)
              throw PIRException(s"Ctrl Reader $reader same as writer $cu")
          }
          cu match {
            case mc:MemoryController => 
              mc.cchains.flatMap{_.counters}.foreach { ctr =>
                if (!ctr.min.from.src.isInstanceOf[Const]) throw TODOException(s"Counter in MC need to have constant min ${ctr} ${ctr.min.from}")
                if (!ctr.step.from.src.isInstanceOf[Const]) throw TODOException(s"Counter in MC need to have constant min ${ctr} ${ctr.step.from}")
              }
            case _ =>
          }
        case n:CtrlBox =>
          n.udcounters.foreach { case (_, udc) => assert(udc.ctrler==n.ctrler) }
          n.luts.foreach { lut => assert(lut.ctrler==n.ctrler) }
        case n:Counter =>
          n.ctrler match {
            case cu:OuterController =>
              if (cu.cchains.exists( _.isCopy)) 
                throw PIRException(s"Outer controller cannot have counter copy")
            case _ =>
          }
        case n:SRAM => 
          if (!n.writePort.isConnected)
            throw PIRException(s"writePort of $n in ${n.ctrler} is not connected")
          if (!n.writeAddr.isConnected)
            throw PIRException(s"writeAddr of $n in ${n.ctrler} is not connected")
          if (!n.readAddr.isConnected)
            throw PIRException(s"readAddr of $n in ${n.ctrler} is not connected")
        case n =>
      }
    }
    design.arch match {
      case sn:SwitchNetwork =>
        (sn.sbs ++ sn.csbs).flatten.foreach { sb => 
          sb.vins.foreach { vin => 
            if(vin.fanIns.size>1) 
              throw PIRException(s"Switchbox $sb has $vin with fanIns > 1 ${vin.fanIns}")
          }
          sb.vouts.foreach { vout => 
            if(vout.fanOuts.size>1) 
              throw PIRException(s"Switchbox $sb has $vout with fanOuts > 1 ${vout.fanOuts}")
          }
        }
        sn.cus.foreach { pcu =>
          (pcu.vins ++ pcu.cins).foreach { in => 
            if(in.fanIns.size>1) 
              throw PIRException(s"ComputeUnit $pcu has $in with fanIns > 1 ${in.fanIns}")
          }
          pcu.couts.foreach { out => 
            if(out.fanOuts.size>1) 
              throw PIRException(s"ComputeUnit $pcu has $out with fanOuts > 1 ${out.fanOuts}")
          }
        }
      case _ =>
    }
    design.arch.cus.foreach { cu =>
      cu.stages.zipWithIndex.foreach { case (stage, i) =>
        assert(stage.index==i-1, s"stage:$stage stage.index=${stage.index} should be ${i-1}")
        if (stage!=cu.stages.head) assert(stage.pre==Some(cu.stages(i-1)))
        else assert(stage.pre==None)
        if (stage!=cu.stages.last) assert(stage.next==Some(cu.stages(i+1)))
        else assert(stage.next==None)
      }
      assert(cu.souts.size==(design.arch.scalarBandwidth*cu.vouts.size), 
        s"cu:$cu cu.souts.size=${cu.souts.size}, scalarBandwidth=${design.arch.scalarBandwidth}")
    }
  } 

  override def finPass = {
    info("Finishing checking mutable fields")
    design.arch match {
      case sn:SwitchNetwork =>
        val dbw = sn.switchNetworkDataBandwidth
        val cbw = sn.switchNetworkCtrlBandwidth
        dprint(s"Switch Network Data Bandwidth:${dbw} Ctrl Bandwidth:${cbw}")
        design.top.spadeCtrlers.foreach { cu =>
          cu.vins.groupBy { vin =>
            vin.writer
          }.foreach { case (fromcu, vins) =>
            if (vins.size > dbw)
              warn(s"Data Connectivity from $fromcu to $cu: ${vins.size}. Data Bandwidth:$dbw")
          }
          cu.ctrlIns.groupBy { cin =>
            if (!cin.isConnected) throw PIRException(s"$cin is not connected")
            cin.from.asInstanceOf[CtrlOutPort].ctrler
          }.foreach { case (fromcu, cins) =>
            if (cins.size > cbw)
              warn(s"Ctrl Connectivity from $fromcu to $cu: ${cins.size}. Ctrl Bandwidth:$cbw")
          }
        }
      case pn:PointToPointNetwork =>
    }
  }

}
