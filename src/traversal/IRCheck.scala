package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException
import pir.plasticine.main._

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit val design: Design) extends Traversal{
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
        sn.sbs.flatten.foreach { sb => 
          sb.vins.foreach { vin => 
            if(vin.fanIns.size>1) 
              throw PIRException(s"Switchbox $sb has $vin with fanIns > 1 ${vin.fanIns}")
          }
          sb.vouts.foreach { vout => 
            if(vout.fanOuts.size>1) 
              throw PIRException(s"Switchbox $sb has $vout with fanOuts > 1 ${vout.fanOuts}")
          }
        }
      case _ =>
    }
  } 
  override def finPass = {
    info("Finishing checking mutable fields")
  }

}
