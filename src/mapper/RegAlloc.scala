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

  type LI = MMap[Stage, (Set[Reg], Set[Reg])] // Stage -> (LiveIn, LiveOut)
  type IG = MMap[Reg, MSet[Reg]]
  type RC = MMap[Reg, PReg]

  private def liveAnalysis(cu:CU):LI = {
    val liveMap:LI = MMap.empty
    val stages = cu.stages
    for (i <- stages.size-1 to 0 by -1){
      val s = stages(i)
      val liveOut = 
        if (i==stages.size-1) cu.liveOuts.toSet 
        else {
          val next = stages(i+1)
          val nextLiveIn = liveMap(next)._1
          next match {
            case as:AccumStage => // Implicit def due to initial value
              nextLiveIn - as.accReg
            case _ => nextLiveIn 
          }
        } 
      val liveIn = (liveOut -- cu.stageDefs(s) ++ cu.stageUses(s))
      liveMap += s -> (liveIn, liveOut)
    }
    liveMap
  }

  private def infAnalysis(cu:CU, lm:LI):IG = {
    val infGraph:IG = MMap.empty
    val stages = cu.stages
    stages.foreach { s =>
      val (liveIn, liveOut) = lm(s)
      liveIn.foreach { r =>
        if (!infGraph.contains(r)) infGraph += (r -> MSet.empty)
        infGraph(r) ++= (liveIn - r)
      }
      liveOut.foreach { r =>
        if (!infGraph.contains(r)) infGraph += (r -> MSet.empty)
        infGraph(r) ++= (liveOut - r)
      }
    }
    infGraph
  }

  private def preColorAnalysis(cu:CU, cuMap:M, lm:LI, ig:IG):RC = {
    val cm:RC = MMap.empty // Color Map
    val pcu = cuMap.clmap(cu).asInstanceOf[PCU]
    def preColorReg(r:Reg, pr:PReg):Unit = {
      ig(r).foreach { ifr =>
        if (cm.contains(ifr) && cm(ifr) == pr )
          throw PreColorException(r, ifr, pr)
      }
      cm += (r -> pr)
    }
    def preColor(r:Reg, prs:List[PReg]):Unit = {
      assert(prs.size == 1, 
        s"Current mapper assuming each PipeReg Mappable Port is mapped to 1 Register. Found ${prs}")
      preColorReg(r, prs.head)
    }
    ig.foreach { case (r, itfs)  =>
      r match {
        case LoadPR(regId, rdPort) =>
          val sram = rdPort.src
          val psram = cuMap.smmap(sram.asInstanceOf[SRAM])
          preColor(r, psram.readPort.mappedRegs.toList)
        case StorePR(regId, wtPort) =>
          val sram = wtPort.src
          val psram = cuMap.smmap(sram.asInstanceOf[SRAM])
          preColor(r, psram.writePort.mappedRegs.toList)
        case WtAddrPR(regId, waPort) =>
          val sram = waPort.src
          val psram = cuMap.smmap(sram.asInstanceOf[SRAM])
          preColor(r, psram.writeAddr.mappedRegs.toList)
        case RdAddrPR(regId, rdPort) =>
          val sram = rdPort.src
          val psram = cuMap.smmap(sram.asInstanceOf[SRAM])
          preColor(r, psram.readAddr.mappedRegs.toList)
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
    val ig = cuMap.igmap.map
    val rc = cuMap.rcmap.map
    if (rc.contains(n)) return cuMap
    ig(n).foreach{ itf => if (rc.contains(itf) && rc(itf) == p) throw InterfereException(n, itf, p) }
    cuMap.setRC(n, p)
  }

  def map(cu:CU, cuMap:M):M = {
    val li = liveAnalysis(cu)
    val ig = infAnalysis(cu, li)
    val rc = preColorAnalysis(cu, cuMap, li, ig)
    val newLi = LIMap(cuMap.limap.map ++ li.toMap) 
    val newIg = IGMap(cuMap.igmap.map ++ ig.map{case (k,v) => (k, v.toSet)}.toMap)
    val newRc = RCMap(cuMap.rcmap.map ++ rc.toMap) 
    val cmap = cuMap.copy(newLi).copy(newIg).copy(newRc)
    val remainRegs = (ig.keys.toSet -- rc.keys.toSet).toList
    val pcu = cmap.clmap(cu).asInstanceOf[PCU]
    //val finPass:Option[M=>M] = Some(StageMapper.map(cu, _))
    val finPass:Option[M=>M] = None 
    simAneal(pcu.pregs, remainRegs, cmap, List(regColor(cu) _), finPass, OutOfReg(pcu, _, _))
  } 
}

case class PreColorException(r1:Reg, r2:Reg, c:PReg)(implicit design:Design) extends MappingException {
  override val mapper = RegAlloc
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
