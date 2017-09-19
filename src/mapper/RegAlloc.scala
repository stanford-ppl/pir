package pir.mapper
import pir.util.typealias._
import pir.node._
import pir._
import pir.codegen.DotCodegen
import pir.spade.node._
import pir.codegen.DotCodegen
import pirc.exceptions._
import pir.spade.util._
import pir.spade.main.Spade
import pirc.enums._

import scala.collection.mutable

class RegAlloc(implicit val design:PIR) extends Mapper {
  type N = Reg
  type R = PReg
  val typeStr = "RegAlloc"
  override def debug = Config.debugRAMapper
  import pirmeta._
  import spademeta._

  def finPass(cu:CL)(m:M):M = m

  val voMap = mutable.Map[Reg, mutable.Stack[PGO[PModule]]]()

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:MC => finPass(cu)(pirMap)
      case cu:ICL => finPass(cu)(map(cu, pirMap))
      case cu => finPass(cu)(pirMap)
    }
  } 

  def map(cu:ICL, pirMap:M):M = {
    var mp = pirMap
    mp = preColor(cu, mp)
    mp = color(cu, mp)
    mp
  }

  def resFunc(cu:ICL, allRes:N => List[R])(n:N, m:M, triedRes:List[R]):List[R] = {
    val infs = cu.infGraph(n)
    allRes(n).diff(triedRes).filterNot { r => 
      m.rcmap.get(r).fold (false) { regs =>
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
    val pcu = pirMap.pmmap(cu)
    val pregs = pcu.asCU.regs
    val regs = mutable.ListBuffer[Reg]()
    cu.regs.foreach {
      //case reg@VecOutPR(out) if pirMap.vomap.contains(out) =>
        //val pout = pirMap.vomap(out)
        //dprintln(s"$out -> ${quote(pout)}")
        //pout.foreach { pvo => regs += reg }
      case reg if reg.isTemp =>
      case reg => regs += reg
    }
    def allRes(n:N):List[R] = {
      def allRes(n:N, out:O) = {
        val regTp = out match {
          case out:SO => ScalarOutReg
          case out:VO => VecOutReg
        }
        val pregs = pirMap.vomap.get(out).map { pouts =>
          dprintln(s"out=$out -> pouts:$pouts")
          pirMap.vomap(out).foldLeft(regsOf(pouts.head.ic)) { case (pregs, pouts) =>
            dprintln(s"regsOf($pouts) = ${regsOf(pouts)}")
            pregs intersect regsOf(pouts.ic)
          }
        }.getOrElse(pcu.asCU.regs.filter{_.is(regTp)})
        dprintln(s"allRes(reg=$n) pregs=$pregs")
        pregs
      }
      n match {
        case LoadPR(mem) => 
          val pmem = pirMap.pmmap(mem)
          regsOf(pmem.readPort)
        case WtAddrPR(waPort) => 
          val pmem = pirMap.pmmap(waPort.src.asSRAM).asSRAM
          regsOf(pmem.writeAddr)
        case CtrPR(ctr) => 
          val pctr = pirMap.pmmap(ctr)
          regsOf(pctr.out)
        case ReducePR() => 
          val cu = pirMap.pmmap(pcu)
          if (parOf(cu)>1) pregs.filter(_.is(ReduceReg))
          else pregs 
        case VecOutPR(out) => allRes(n, out)
        case ScalarOutPR(out) => allRes(n, out)
        case AccumPR(init) => pregs.filter(_.is(AccumReg))
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
    val pcu = pirMap.pmmap(cu)
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

case class PreColorInterfere(r1:Reg, r2:Reg, c:PReg, mp:PIRMap)(implicit mapper:Mapper, design:PIR) extends MappingException(mp) {
  override val msg = s"Interfering $r1 and $r2 in ${r1.ctrler} have the same predefined color ${quote(c)(design.arch)}" 
}
case class InterfereException(r:Reg, itr:Reg, p:PReg, mp:PIRMap)(implicit mapper:Mapper, design:PIR) extends MappingException(mp){
  override val msg = s"Cannot allocate $r to $p due to interference with $itr "
}
