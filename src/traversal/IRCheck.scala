package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException
import pir.plasticine.main._

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit val design: Design) extends Traversal {
  override def traverse:Unit = {
    design.allNodes.foreach{ n => 
      if (n.toUpdate) {
        val printer = new PIRPrinter()
        printer.run
        printer.close
        throw PIRException(s"Node ${n} contains unupdated field/fields! Refer to ${printer.getPath} for more information")
      }
      n match {
        case c:SpadeController =>
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
            cin.from.src match {
              case prim:Primitive => prim.ctrler.asInstanceOf[ComputeUnit].inner
              case top:Top => top 
            }
          }.foreach { case (fromcu, cins) =>
            if (cins.size > cbw)
              warn(s"Ctrl Connectivity from $fromcu to $cu: ${cins.size}. Ctrl Bandwidth:$cbw")
          }
        }
      case pn:PointToPointNetwork =>
    }
  }

}
