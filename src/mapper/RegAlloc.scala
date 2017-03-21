package pir.mapper
import pir.util.typealias._
import pir.graph._
import pir._
import pir.codegen.DotCodegen
import pir.plasticine.graph.{PipeReg}
import pir.codegen.DotCodegen
import pir.exceptions._
import pir.plasticine.util._
import pir.plasticine.main.Spade

import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.{Set => MSet}

class RegAlloc(implicit val design:Design) extends Mapper {
  type R = PReg
  type N = Reg

  type RC = RCMap
  val typeStr = "RegAlloc"
  override def debug = Config.debugRAMapper
  val spademeta: SpadeMetadata = spade
  import spademeta._

  implicit def spade:Spade = design.arch

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
          preColor(r, mappingOf(psram.readPort))
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
          preColor(r, mappingOf(psram.writeAddr))
        case CtrPR(regId, ctr) =>
          val pctr = pirMap.ctmap(ctr)
          preColor(r, mappingOf(pctr.out))
        case ReducePR(regId) =>
          preColor(r, mappingOf(pcu.reduce))
r       case VecInPR(regId, vecIn) =>
          val pvin = pirMap.vimap(vecIn)
          val buf = { val bufs = bufsOf(pvin); assert(bufs.size==1); bufs.head }
          preColor(r, mappingOf(buf.out))
        case VecOutPR(regId, vecOut) =>
          val pvout = pirMap.vomap(vecOut).head //FIXME need to map vecout
          val buf = { val bufs = bufsOf(pvout); assert(bufs.size==1); bufs.head }
          preColor(r, mappingOf(buf.in))
        case ScalarInPR(regId, scalarIn) =>
          val psi = pirMap.simap(scalarIn)
          val pregs = mappingOf(psi.out)
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
          preColor(r, mappingOf(pso.in))
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
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color ${quote(c)(design.arch)}" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp){
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
