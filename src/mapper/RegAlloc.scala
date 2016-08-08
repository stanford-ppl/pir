package pir.graph.mapper
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, _}
import pir.plasticine.graph.{Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, 
                             Reg => PReg}
import pir._
import pir.PIRMisc._
import pir.graph.mapper._

import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.{Set => MSet}

object RegAlloc extends Mapper {
  type R = PReg
  type N = Reg

  type RC = MMap[Reg, PReg]

  private def preColorAnalysis(cu:CU, cuMap:M):RC = {
    val cm:RC = MMap.empty // Color Map
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    def preColorReg(r:Reg, pr:PReg):Unit = {
      cu.infGraph(r).foreach { ifr =>
        if (cm.contains(ifr) && cm(ifr) == pr )
          throw PreColorInterfere(r, ifr, pr)
      }
      cm += (r -> pr)
    }
    def preColor(r:Reg, prs:List[PReg]):Unit = {
      assert(prs.size == 1, 
        s"Current mapper assuming each PipeReg Mappable Port is mapped to 1 Register. Found ${prs}")
      preColorReg(r, prs.head)
    }
    cu.infGraph.foreach { case (r, itfs)  =>
      r match {
        case LoadPR(regId, rdPort) =>
          val sram = rdPort.src
          val psram = cuMap.smmap(sram)
          preColor(r, psram.readPort.mappedRegs.toList)
        case StorePR(regId, wtPort) =>
          val sram = wtPort.src
          val psram = cuMap.smmap(sram)
          preColor(r, psram.writePort.mappedRegs.toList)
        //case rr:WtAddrPR =>
        //  val waPorts = rr.waPorts
        //  val srams = waPorts.map{_.src}
        //  val psrams = srams.map{ sram => cuMap.smmap(sram.asInstanceOf[SRAM]) }
        //  val colors = psrams.map { psram => preColor(r, psram.writeAddr.mappedRegs.toList) }
        //  if (colors.toSet.size!=1) { throw PreColorSameReg(r) } 
        //case rr:RdAddrPR =>
        //  val raPorts = rr.raPorts
        //  val srams = raPorts.map{_.src}
        //  val psrams = srams.map{ sram => cuMap.smmap(sram.asInstanceOf[SRAM]) }
        //  val colors = psrams.map { psram => preColor(r, psram.readAddr.mappedRegs.toList) }
        //  if (colors.toSet.size!=1) { throw PreColorSameReg(r) } 
        case CtrPR(regId, ctr) =>
          val pctr = cuMap.ctmap(ctr)
          preColor(r, pctr.out.mappedRegs.toList)
        case ReducePR(regId) =>
          preColor(r, pcu.reduce.mappedRegs.toList)
r       case VecInPR(regId, vecIn) =>
          val pvin = cuMap.vimap(vecIn)
          preColor(r, pvin.rmport.mappedRegs.toList)
        case VecOutPR(regId) =>
          val pvout = pcu.vout
          preColor(r, pvout.rmport.mappedRegs.toList)
        case ScalarInPR(regId, scalarIn) =>
          val psi = cuMap.simap(scalarIn)
          preColor(r, psi.out.mappedRegs.toList)
        case ScalarOutPR(regId, scalarOut) =>
          val pso = cuMap.somap(scalarOut)
          preColor(r, pso.in.mappedRegs.toList)
        case _ => // No predefined color
      }
    }
    cm
  }

  private def regColor(cu:CU)(n:N, p:R, cuMap:M):M = {
    val rc = cuMap.rcmap.map
    if (rc.contains(n)) return cuMap
    cu.infGraph(n).foreach{ itf => 
      if (rc.contains(itf) && rc(itf) == p) throw InterfereException(n, itf, p)
    }
    cuMap.setRC(n, p)
  }

  def map(cu:CU, cuMap:M):M = {
    val rc = preColorAnalysis(cu, cuMap)
    val prc = RCMap(cuMap.rcmap.map ++ rc.toMap) 
    val cmap = cuMap.copy(prc)
    val remainRegs = (cu.infGraph.keys.toSet -- rc.keys.toSet).toList
    val pcu = cmap.clmap(cu).asInstanceOf[PCU]
    //val finPass:Option[M=>M] = Some(StageMapper.map(cu, _))
    val finPass:Option[M=>M] = None 
    simAneal(pcu.pregs, remainRegs, cmap, List(regColor(cu) _), finPass, OutOfReg(pcu, _, _))
  } 
}

trait PreColorException extends MappingException {
  override val mapper = RegAlloc
}
case class PreColorSameReg(reg:Reg)(implicit design:Design) extends PreColorException{
  override val msg = s"${reg} has more than 1 predefined color" 
}
case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg)(implicit design:Design) extends PreColorException {
  override val msg = s"Interfering $r1 and $r2 have the same predefined color $c" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg)(implicit design:Design) extends MappingException{
  override val mapper = RegAlloc
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
case class OutOfReg(pcu:PCU, nres:Int, nnode:Int)(implicit design:Design) extends OutOfResource{
  override val mapper = RegAlloc
  override val msg = s"Not enough pipeline registers in ${pcu} to map application."
}
