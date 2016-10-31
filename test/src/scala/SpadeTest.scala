package pir.test

import pir._
import pir.misc._
import pir.graph.traversal._
import pir.plasticine.config._
import pir.plasticine.main._
import pir.graph.mapper.PIRException

import org.scalatest._
import scala.language.reflectiveCalls
import scala.util.Random
import scala.collection.mutable.ListBuffer

class SpadeTest extends UnitTest { self =>

  "SN_4x4" should "fail" taggedAs(ARCH) in {
    val design = new Design { self =>
      override val arch = SN_4x4 
      val printer = new CUCtrlDotPrinter()
      printer.print
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

}

