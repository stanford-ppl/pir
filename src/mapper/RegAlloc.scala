package pir.graph.mapper
import pir.typealias._
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper._
import pir.plasticine.graph.{PipeReg}

import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.{Set => MSet}

class RegAlloc(implicit val design:Design) extends Mapper {
  type R = PReg
  type N = Reg

  type RC = MMap[Reg, PReg]
  val typeStr = "RegAlloc"

  def finPass(cu:CU)(m:M):M = m

  private def preColorAnalysis(cu:CU, pirMap:M):RC = {
    val rc:RC = MMap.empty // Color Map
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    def preColorReg(r:Reg, pr:PReg):Unit = {
      cu.infGraph(r).foreach { ifr =>
        if (rc.contains(ifr) && rc(ifr) == pr ) throw PreColorInterfere(r, ifr, pr)
      }
      rc += (r -> pr)
    }
    def preColor(r:Reg, prs:List[PReg]):Unit = {
      assert(prs.size == 1, 
        s"Current mapper assuming each PipeReg Mappable Port is mapped to 1 Register. Found $r -> ${prs}")
      preColorReg(r, prs.head)
    }
    cu.infGraph.foreach { case (r, itfs)  =>
      r match {
        case LoadPR(regId, rdPort) =>
          val sram = rdPort.src
          val psram = pirMap.smmap(sram)
          preColor(r, psram.readPort.mappedRegs.toList)
        case StorePR(regId, wtPort) =>
          val sram = wtPort.src
          val psram = pirMap.smmap(sram)
          val regs = psram.writePort.fanIns.filter{ fi => 
            val PipeReg(stage, reg) = fi.src
            stage == psram.ctrler.stages.last
          }.map(_.src.asInstanceOf[PipeReg].reg).toList
          preColor(r, regs)
        case rr:WtAddrPR =>
          val waPort = rr.waPort
          val sram = waPort.src
          val psram = pirMap.smmap(sram)
          preColor(r, psram.writeAddr.mappedRegs.toList)
        case CtrPR(regId, ctr) =>
          val pctr = pirMap.ctmap(ctr)
          preColor(r, pctr.out.mappedRegs.toList)
        case ReducePR(regId) =>
          preColor(r, pcu.reduce.mappedRegs.toList)
r       case VecInPR(regId, vecIn) =>
          val pvin = pirMap.vimap(vecIn)
          preColor(r, pvin.viport.mappedRegs.toList)
        case VecOutPR(regId) =>
          val pvout = pcu.vout
          preColor(r, pvout.voport.mappedRegs.toList)
        case ScalarInPR(regId, scalarIn) =>
          val psi = pirMap.simap(scalarIn)
          preColor(r, psi.out.mappedRegs.toList)
        case ScalarOutPR(regId, scalarOut) =>
          val pso = pirMap.somap(scalarOut)
          preColor(r, pso.in.mappedRegs.toList)
        case _ => // No predefined color
      }
    }
    rc
  }

  private def regColor(cu:CU)(n:N, p:R, pirMap:M):M = {
    val rc = pirMap.rcmap.map
    if (rc.contains(n)) return pirMap
    cu.infGraph(n).foreach{ itf => 
      if (rc.contains(itf) && rc(itf) == p) throw InterfereException(n, itf, p)
    }
    pirMap.setRC(n, p)
  }

  def map(cu:CU, pirMap:M):M = {
    val prc = preColorAnalysis(cu, pirMap)
    val cmap = pirMap.set(RCMap(pirMap.rcmap.map ++ prc.toMap))
    val remainRegs = (cu.infGraph.keys.toSet -- prc.keys.toSet).toList
    val pcu = cmap.clmap(cu).asInstanceOf[PCU]
    bind(pcu.regs, remainRegs, cmap, List(regColor(cu) _), finPass(cu) _)
  } 
}

trait PreColorException extends MappingException
case class PreColorSameReg(reg:Reg)(implicit val mapper:Mapper, design:Design) extends PreColorException{
  override val msg = s"${reg} has more than 1 predefined color" 
}
case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg)(implicit val mapper:Mapper, design:Design) extends PreColorException {
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color $c" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg)(implicit val mapper:Mapper, design:Design) extends MappingException{
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
case class OutOfReg(pcu:PCU, nres:Int, nnode:Int)(implicit val mapper:Mapper, design:Design) extends OutOfResource{
  override val msg = s"Not enough pipeline registers in ${pcu} to map application."
}
