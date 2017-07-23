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
import pir.util.enums._

import scala.collection.mutable

class RegAlloc(implicit val design:Design) extends Mapper {
  type N = Reg
  type R = PReg
  val typeStr = "RegAlloc"
  override def debug = Config.debugRAMapper
  import spademeta._

  def finPass(cu:CL)(m:M):M = m

  val voMap = mutable.Map[Reg, mutable.Stack[PGO[PModule]]]()

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

  def resFunc(cu:ICL, allRes:N => List[R])(n:N, m:M, triedRes:List[R]):List[R] = {
    val infs = cu.infGraph(n)
    allRes(n).diff(triedRes).filterNot{ r => 
      m.rcmap.pmap.get(r).fold (false) { regs =>
        dprintln(s"${quote(r)} <- [${regs.mkString(",")}]")
        regs.exists { mapped => infs.contains(mapped) }
      }
    }
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
    val regs = mutable.ListBuffer[Reg]()
    cu.regs.foreach {
      case reg@VecOutPR(vo) if pirMap.vomap.contains(vo) =>
        val pvos = pirMap.vomap(vo)
        voMap += reg -> mutable.Stack() 
        dprintln(s"$vo -> ${quote(pvos)}")
        pvos.foreach { pvo =>
          voMap(reg).push(pvo)
          regs += reg
        }
      case reg if reg.isTemp =>
      case reg => regs += reg
    }
    def allRes(n:N):List[R] = {
      n match {
        case LoadPR(mem) => 
          val pmem = pirMap.smmap(mem)
          regsOf(pmem.readPort)
        case WtAddrPR(waPort) => 
          val pmem = pirMap.smmap(waPort.src.asSRAM).asSRAM
          regsOf(pmem.writeAddr)
        case CtrPR(ctr) => 
          val pctr = pirMap.ctmap(ctr)
          regsOf(pctr.out)
        case ReducePR() => 
          pcu.asCU.regs.filter(_.is(ReduceReg))
        case VecOutPR(vecOut) =>
          voMap.get(n).map { stack =>
            val pvout = stack.pop()
            regsOf(pvout.ic)
          }.getOrElse(pcu.asCU.regs.filter{_.is(VecOutReg)})
        case ScalarOutPR(scalarOut) =>
          pirMap.vomap.get(scalarOut).map { psos =>
            dprintln(s"$n -> $psos")
            psos.foldLeft(regsOf(psos.head.ic)) { case (prev, pso) => 
              dprintln(s"regsOf($pso) = ${regsOf(pso)}")
              prev intersect regsOf(pso.ic)
            }
          }.getOrElse(pcu.asCU.regs.filter(_.is(ScalarOutReg)))
        case AccumPR(init) => pcu.asCU.regs.filter(_.is(AccumReg))
      }
    }
    log(s"precolor $cu") {
      bind(
        allNodes=regs.toList,
        initMap=pirMap, 
        constrain=constrain(cu) _,
        resFunc=resFunc(cu, allRes _) _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  private def color(cu:ICL, pirMap:M) = {
    val pcu = pirMap.clmap(cu)
    val regs:List[N] = cu.regs.filter{_.isTemp}
    log(s"color $cu") {
      bind(
        allNodes=regs,
        initMap=pirMap,
        constrain=constrain(cu) _,
        resFunc=resFunc(cu, (n:N) => pcu.asCU.regs) _,
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
