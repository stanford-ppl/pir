package pir.graph.mapper
import pir._
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT}
import pir.graph.{CtrlBox => CB, Counter => Ctr, InPort => IP, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT}
import pir.plasticine.graph.{CtrlBox => PCB, Counter => PCtr, SRAM => PSRAM, InPort => PIP, OutPort => POP, Const => PConst}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

object CtrlMapper extends Mapper {
  type R = PCB
  type N = CB
  
  def next(cu:CU, pirMap:M) = {
    val cmap = RegAlloc.map(cu, pirMap)
    StageMapper.map(cu, cmap)
  }
  def map(cu:CU, pirMap:M):M = {
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    val pcb = pcu.ctrlBox
    val cb = cu.ctrlBox
    val finPass:Option[M => M] = Some(next(cu, _))
    finPass.fold(pirMap)(_(pirMap))
  }

}
case class OutOfEnLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough EnLUT in ${pcu} to map application."
}
case class OutOfTokenOutLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenOutLUT in ${pcu} to map application."
}
case class OutOfTokenDownLUT(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource {
  override val mapper = CtrlMapper 
  override val msg = s"Not enough TokenDownLUT in ${pcu} to map application."
}
//case class CtrRouting(n:Ctr, p:PCtr)(implicit design:Design) extends MappingException {
//  override val mapper = CtrlBox
//  override val msg = s"Fail to map ${n} to ${p}"
//}
