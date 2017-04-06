package pir.mapper
import pir.util.typealias._
import pir.graph._
import pir._
import pir.codegen.DotCodegen
import pir.plasticine.graph._
import pir.codegen.DotCodegen
import pir.exceptions._
import pir.plasticine.util._
import pir.plasticine.main.Spade

import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.{Set => MSet}

class RegAlloc(implicit val design:Design) extends Mapper {
  type N = Reg
  type R = PReg
  val typeStr = "RegAlloc"
  override def debug = Config.debugRAMapper
  val spademeta: SpadeMetadata = spade
  import spademeta._

  implicit def spade:Spade = design.arch

  def finPass(cu:ICL)(m:M):M = m

  def constrain(cu:ICL, pirMap:M)(n:N, r:R, m:RCMap):RCMap = {
    cu.infGraph(n).foreach { ifr =>
      if (m.contains(ifr) && m(ifr) == r ) throw InterfereException(n, ifr, r, pirMap.set(m))
    }
    dprintln(s"mapping $n -> $r")
    m + (n -> r)
  }

  /* Register coloring for registers with predefined colors */
  private def preColor(cu:ICL, pirMap:M):RCMap = {
    type M = RCMap
    val pcu = pirMap.clmap(cu).asPCU
    def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
      val pregs = n match {
        case LoadPR(regId, mem) => 
        val pmem = pirMap.smmap(mem)
        mappingOf(pmem.readPort)
        //case StorePR(regId, sram) =>
        case WtAddrPR(regId, waPort) => 
          val pmem = pirMap.smmap(waPort.src).asSRAM
          mappingOf(pmem.writeAddr)
        case CtrPR(regId, ctr) => 
          val pctr = pirMap.ctmap(ctr)
          mappingOf(pctr.out)
        case ReducePR(regId) => pcu.regs.filter(_.is(ReduceReg))
        case VecOutPR(regId, vecOut) =>
          val pvout = pirMap.vomap(vecOut).head //FIXME need to map vecout
          val buf = { val bufs = bufsOf(pvout); assert(bufs.size==1); bufs.head }
          mappingOf(buf.writePort)
        case ScalarOutPR(regId, scalarOut) =>
          val pso = pirMap.somap(scalarOut)
          mappingOf(pso.writePort)
      }
      pregs.diff(triedRes)
    }
    log(s"precolor $cu") {
      bind(
        allNodes=cu.regs,
        initMap=RCMap.empty, 
        constrain=constrain(cu, pirMap) _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  private def color(cu:ICL, pirMap:M) = {
    type M = RCMap
    val pcu = pirMap.clmap(cu).asPCU
    val regs:List[N] = (cu.regs diff (pirMap.rcmap.keys.toList)).toList
    val pregs:List[R] = (pcu.regs diff (pirMap.rcmap.values.toList)).toList

    log(s"color $cu") {
      bind[R,N,M](
        allRes=pregs,
        allNodes=regs,
        initMap=pirMap.rcmap,
        constrain=constrain(cu, pirMap) _,
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:ICL, pirMap:M):M = {
    var prc = preColor(cu, pirMap)
    prc = color(cu, pirMap.set(RCMap(pirMap.rcmap.map ++ prc.map)))
    finPass(cu)(pirMap.set(prc))
  } 
}

case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color ${quote(c)(design.arch)}" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp){
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
