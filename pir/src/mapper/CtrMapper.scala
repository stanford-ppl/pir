package pir.mapper

import pir._
import pir.util.typealias._

import scala.collection.mutable.ListBuffer

class CtrMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type R = PCtr
  type N = Ctr
  import spademeta._
  import pirmeta._
  val typeStr = "CtrMapper"
  override def debug = PIRConfig.debugCtrMapper
  override val exceptLimit = 200
  
  def finPass(cu:CL)(m:M):M = m

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:CU => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  }

  /*Make sure counters that are chained are next to each other and the counter is order such that
   * inner counter comes first */
  def sortCChains(cchains:List[CC]):List[Ctr] = {
    val sorted = cchains.filter(_.inner.isInner).flatMap { cc =>
      val ctrs = ListBuffer[Ctr]()
      var cur = cc.inner
      while (!cur.isOuter) {
        ctrs += cur
        cur = cur.next
      }
      ctrs += cur
      ctrs.toList
    }
    val unSorted = cchains.flatMap(_.counters)
    if (PIRConfig.ctrl) {
      assert(unSorted.size==sorted.size, s"unSorted=${unSorted.size} sorted=${sorted.size}, cchains:${cchains}")
      sorted
    } else {
      unSorted
    }
  }

  def map(cu:CU, pirMap:M):M = {
    log[M](cu, buffer=false) {
      // Mapping inner counter first converges faster
      val ctrs = sortCChains(cu.cchains) //++ cu.mems.collect{case f:FOW => f.dummyCtr}
      val pctrs = pirMap.pmmap(cu) match {
        case pcu:PCU => pcu.ctrs
        case _ => Nil
      }
      bind(
        allNodes=ctrs,
        initMap=pirMap,
        constrain=mapCtr _, 
        resFunc=resFunc(pctrs) _, 
        finPass=finPass(cu) _
      )
    }
  }

  def resFunc(allRes:List[R])(n:N, m:M, triedRes:List[R]):List[R] = {
    //if (n.id==507){
      //new CtrDotPrinter().print(allRes, m)
    //}
    val remainRes = allRes.diff(triedRes).filter( pc => !m.pmmap.contains(pc))
    val ptop = design.arch.top
    val enCtrs = if (PIRConfig.ctrl) {
      n.en.from.src match {
        case dep:Ctr if n.ctrler == dep.ctrler => // Counter in the same CU
          m.pmmap.get(dep).fold(remainRes) { pdep =>
            pdep.done.fanOuts.map{ fo => fo.src }.collect{ case pc:R => pc }.toList
          }
          // Inner most counter or copied inner most counter whose enable is routed fron network
        case _ => remainRes.filter(pc => isInnerCounter(pc))
      }
    } else {
      remainRes
    }
    val doneCtrs = if (PIRConfig.ctrl) {
      n.done.to.map { done =>
        done.src match {
          case deped:Ctr if n.ctrler==deped.ctrler =>
            m.pmmap.get(deped).fold(remainRes) { pdeped =>
              pdeped.en.fanIns.map{ fi => fi.src}.collect{case pc:R => pc}.toList
            }
          case _ => remainRes
        }
      }.reduceOption{ _ intersect _ }.getOrElse(remainRes)
    } else {
      remainRes
    }

    var res = enCtrs intersect doneCtrs
    if (forWrite(n)) {
      res = res.filterNot{ _.index == 0 }
    }
    res 
  }

  def mapCtr(n:N, p:R, map:M):M = {
    var mp = map
    mp = mapInput(n.min, p.min, mp)
    mp = mapInput(n.max, p.max, mp)
    mp = mapInput(n.step, p.step, mp)
    mp = mapOutput(n.out, p.out, mp)
    mp = mp.setPM(n,p)
    dprintln(s"mapping $n -> ${mp.pmmap(n)}")
    mp
  }

}
