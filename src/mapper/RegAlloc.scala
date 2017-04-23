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
  import spademeta._

  def finPass(cu:CL)(m:M):M = m

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:ICL => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  } 

  def map(cu:ICL, pirMap:M):M = {
    var mp = pirMap
    mp = preColor(cu, mp)
    mp = color(cu, mp)
    finPass(cu)(mp)
  }

  def constrain(cu:ICL)(n:N, r:R, m:M):M = {
    var mp = m
    cu.infGraph(n).foreach { ifr =>
      if (mp.rcmap.get(ifr) == Some(r) ) throw InterfereException(n, ifr, r, m)
    }
    dprintln(s"mapping $n -> $r")
    mp = mp.setRC(n, r)
    mp
  }

  /* Register coloring for registers with predefined colors */
  private def preColor(cu:ICL, pirMap:M):M = {
    val pcu = pirMap.clmap(cu)
    def resFunc(n:N, m:M, triedRes:List[R]):List[R] = {
      val pregs = n match {
        case LoadPR(mem) => 
          val pmem = pirMap.smmap(mem)
          regsOf(pmem.readPort)
        //case StorePR(sram) =>
        case WtAddrPR(waPort) => 
          val pmem = pirMap.smmap(waPort.src).asSRAM
          regsOf(pmem.writeAddr)
        case CtrPR(ctr) => 
          val pctr = pirMap.ctmap(ctr)
          regsOf(pctr.out)
        case ReducePR() => pcu.asCU.regs.filter(_.is(ReduceReg))
        case VecOutPR(vecOut) =>
          val pvout = pirMap.vomap(vecOut).head
          regsOf(pvout.ic)
        case ScalarOutPR(scalarOut) =>
          val psos = pirMap.vomap(scalarOut)
          dprintln(s"sout:${scalarOut} -> psos:[${psos.mkString(",")}]")
          val pregs = psos.foldLeft(regsOf(psos.head.ic)) { case (prev, pso) => 
            prev intersect regsOf(pso.ic)
          }
          dprintln(s"pregs:[${pregs.mkString(",")}]")
          pregs
        case AccumPR(init) => pcu.asCU.regs.filter(_.is(AccumReg))
      }
      pregs.diff(triedRes)
    }
    log(s"precolor $cu") {
      bind(
        allNodes=cu.regs.filterNot{_.isTemp},
        initMap=pirMap, 
        constrain=constrain(cu) _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  private def color(cu:ICL, pirMap:M) = {
    val pcu = pirMap.clmap(cu)
    val regs:List[N] = cu.regs.filter{_.isTemp}
    val pregs:List[R] = pcu match {
      case pcu:PCU => (pcu.regs diff (pirMap.rcmap.values.toList)).toList
      case pcu => Nil
    }

    log(s"color $cu") {
      bind(
        allRes=pregs,
        allNodes=regs,
        initMap=pirMap,
        constrain=constrain(cu) _,
        finPass=(m:M) => m
      )
    }
  }

}

case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp) {
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color ${quote(c)(design.arch)}" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg, mp:PIRMap)(implicit val mapper:Mapper, design:Design) extends MappingException(mp){
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
