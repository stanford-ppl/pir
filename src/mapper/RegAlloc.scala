package pir.mapper
import pir.typealias._
import pir.graph._
import pir._
import pir.misc._
import pir.codegen.DotCodegen
import pir.plasticine.graph.{PipeReg}
import pir.codegen.DotCodegen
import pir.util._

import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.{Set => MSet}

class RegAlloc(implicit val design:Design) extends Mapper {
  type R = PReg
  type N = Reg

  type RC = RCMap
  val typeStr = "RegAlloc"
  override def debug = Config.debugRAMapper

  def finPass(cu:ICL)(m:M):M = m

  private def preColorAnalysis(cu:ICL, pirMap:M):RC = {
    var rc:RC = RCMap.empty // Color Map
    val pcu = pirMap.clmap(cu).asInstanceOf[PCU]
    def preColorReg(r:Reg, pr:PReg):Unit = {
      cu.infGraph(r).foreach { ifr =>
        if (rc.contains(ifr) && rc(ifr) == pr ) throw PreColorInterfere(r, ifr, pr, pirMap)
      }
      dprintln(s"preg mapping $r -> $pr")
      rc = rc + (r -> pr)
    }
    def preColor(r:Reg, prs:List[PReg]):Unit = {
      assert(prs.size == 1, 
        s"Current mapper assuming each PipeReg Mappable Port is mapped to 1 Register. Found $r -> ${prs}")
      preColorReg(r, prs.head)
    }
    cu.infGraph.foreach { case (r, itfs)  =>
      r match {
        case LoadPR(regId, sram) =>
          val psram = pirMap.smmap(sram)
          preColor(r, psram.readPort.mappedRegs.toList)
        case StorePR(regId, sram) =>
          val psram = pirMap.smmap(sram)
          val pregs = psram.writePort.fanIns.filter{ fi => 
            val PipeReg(stage, reg) = fi.src
            stage == psram.ctrler.stages.last
          }.map(_.src.asInstanceOf[PipeReg].reg).toList
          preColor(r, pregs)
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
          val pregs = psi.out.mappedRegs.toList
          var info = s"$r connected to $pregs. Interference:"
          def mapReg:Unit = {
            pregs.foreach { pr =>
              if (cu.infGraph(r).size==0) {
                rc += (r -> pr)
                return
              } else {
                cu.infGraph(r).foreach { ifr =>
                  if (!rc.contains(ifr) || rc(ifr) != pr ) {
                    rc += (r -> pr)
                    return
                  } else {
                    info += s"$ifr mapped ${rc.contains(ifr)} -> $pr" 
                  }
                }
              }
            }
            throw PIRException(s"Cannot find non interfering register to map $scalarIn $psi $info" ) //TODO
          }
          mapReg
        case ScalarOutPR(regId, scalarOut) =>
          val pso = pirMap.somap(scalarOut)
          preColor(r, pso.in.mappedRegs.toList)
        case _ => // No predefined color
      }
    }
    rc
  }

  private def regColor(cu:ICL)(n:N, p:R, pirMap:M):M = {
    val rc = pirMap.rcmap.map
    if (rc.contains(n)) return pirMap
    cu.infGraph(n).foreach{ itf => 
      if (rc.contains(itf) && rc(itf) == p) throw InterfereException(n, itf, p, pirMap)
    }
    pirMap.setRC(n, p)
  }

  def map(cu:ICL, pirMap:M):M = {
    log(cu) {
      val prc = preColorAnalysis(cu, pirMap)
      val cmap = pirMap.set(RCMap(pirMap.rcmap.map ++ prc.map))
      val remainRegs = (cu.infGraph.keys.toSet -- prc.keys.toSet).toList
      val pcu = cmap.clmap(cu).asInstanceOf[PCU]
      bind(pcu.regs, remainRegs, cmap, regColor(cu) _, finPass(cu) _)
    }
  } 
}

case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color ${DotCodegen.quote(c)}" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp){
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
