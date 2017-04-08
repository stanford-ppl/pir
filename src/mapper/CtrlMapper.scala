package pir.mapper
import pir.{Config}
import pir.Design
import pir.util.typealias._
import pir.plasticine.main._
import pir.util.enums._
import pir.pass.{PIRMapping}
import pir.codegen.{CUCtrlDotPrinter}
import pir.exceptions._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.collection.immutable.Set
import scala.collection.immutable.Map
import scala.collection.mutable.{ Map => MMap }
import scala.util.{Try, Success, Failure}

class CtrlMapper(implicit val design:Design) extends Mapper {
  implicit def spade:Spade = design.arch
  val typeStr = "CtrlMapper"
  override def debug = Config.debugCtrlMapper

  def finPass(cu:CL)(m:M):M = m

  def map(cu:CL, pirMap:M):M = {
    log(cu) {
      mapCtrl(cu, pirMap)
    }
  }

  def mapCtrl(cu:CL, pirMap:M):M = {
    var pmap = pirMap
    val pcu = pmap.clmap(cu).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    assert(cu.couts.size <= pcu.ctrlIO.outs.size)
    assert(cu.cins.size <= pcu.ctrlIO.ins.size)
    assert(cu.ctrlBox.udcounters.size <= pcu.ctrlBox.udcs.size)
    //assert(cu.enLUTs.size <= pcu.ctrlBox.enLUTs.size)
    //assert(cu.tokDownLUTs.size <= pcu.ctrlBox.tokenDownLUTs.size)
    //assert(cu.tokOutLUTs.size <= pcu.ctrlBox.tokenOutLUTs.size)

    var ucmap = pmap.ucmap
    var pmmap = pmap.pmmap
    /* Up-Down Counter mapping */
    cu.ctrlBox.udcounters.groupBy { case (_, udc) => 
      // Whether an udc can placed in the first pudc or not
      udc.out.to.map{_.src}.collect{case enlut:EnLUT => enlut}.exists{_.ins.exists{_.from.src.isInstanceOf[AT]} } 
    }.foreach {
      case (true, udcs) => udcs.zip(pcb.udcs.tail.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
      case (false, udcs) => udcs.zip(pcb.udcs.filterNot{ pudc => ucmap.pmap.contains(pudc)}).foreach { case ((_, udc), pudc) => ucmap += (udc -> pudc) }
    }

    /* Enable LUT mapping */
    cu.ctrlBox.enLUTs.foreach { enLut =>
      val ctr = enLut.out.to.flatMap{ _.src match {
          case ctr:Ctr if ctr.ctrler == enLut.ctrler => Some(ctr)
          case _ => None 
        }
      }.head
      val pctr = pmap.ctmap(ctr)
      //val penLut = pcb.enLUTs(indexOf(pctr))
      //assert(enLut.ins.size <= penLut.numIns)
      //pmmap += (enLut -> penLut)
    }

    def findPto(lut:LUT, pluts:List[PLUT]):Unit = {
      pluts.foreach { plut =>
        assert(lut.ins.size <= plut.numIns)
        if (!pmmap.pmap.contains(plut)) {
          pmmap += (lut -> plut)
          return
        }
      }
      throw PIRException(s"Cannot map ${lut} in ${lut.ctrler} ${pcu} pluts:${pluts}}")
    }

    /* TokenDown LUT mapping */
    //cu.tokDownLUTs.foreach { tdlut => 
      //findPto(tdlut, pcb.tokenDownLUTs.filter(plut => !pmmap.pmap.contains(plut)))
    //}

    /* TokenOut LUT mapping */
    //cu.tokOutLUTs.foreach { tolut => 
      //findPto(tolut, pcb.tokenOutLUTs.filter(plut => !pmmap.pmap.contains(plut)))
    //}

    pmap.set(ucmap).set(pmmap)
  }

}

case class NotReachable(to:CL, topcu:PCL, fromcu:CL, frompcu:Option[PCL], mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Cannot map $to to $topcu because due to incapable of reaching from $fromcu at $frompcu"
}
