package pir
package mapper

import pir.node._
import pir.pass._
import spade.param._
import prism.collection.mutable
import prism.collection.immutable

class PlastisimVCAllocation(implicit compiler: PIR) extends PIRPass with PlastisimUtil with MappingLogger with BackTrackingMatch {
  import pirmeta._
  
  lazy val topParam = compiler.arch.designParam.topParam.asInstanceOf[DynamicGridTopParam]

  override def runPass:Unit =  {
    if (!isDynamic(compiler.arch.top)) return
    dbg(pirMap)
    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[VCMap] { vcmap =>
        log(assignVCColor(VCMap.empty))
      }
    }
  }

  override def finPass:Unit = {
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

  def assignVCColor(init:VCMap) = {
    var vcmap = init
    infMemsOf.keys.foreach { mem =>
      val linktp = pinTypeOf(mem)
      val networkParam = topParam.networkParams.filter { net => bctOf(net) == linktp }.head
      val numVC = networkParam.numVirtualClasses
      vcmap ++= mem -> (0 until numVC).toSet
    }
    log(vcmap)
    // Color the link with most interference first
    val sortedLinks = infMemsOf.keys.toSeq.sortBy { key => -infMemsOf(key).size }
    bind(
      pnodes=sortedLinks,
      snodes=(k:Memory, m:VCMap) => m(k).toList,
      init=vcmap,
      bindLambda=assignColor _
    )(
      rankS = { case (s, m) => m.freeKeys(s).size } // use the most flexable color
    )
  }

  def assignColor(k:Memory, v:Int, m:VCMap):EOption[VCMap] = {
    m.set(k,v).flatMap { vcmap =>
      // Remove v from freeValue of interferece k
      val interfereds = infMemsOf(k).filterNot { i => vcmap.isMapped(i) }
      flatFold[Memory, VCMap](interfereds, vcmap) { case (vcmap, interfered) =>
        vcmap.filterNotAt(interfered) { _ == v }
      }
    }.flatMap { vcmap =>
      // Constrain memory in the same link to have the same vc
      val others = linkGroupOf(k).filterNot { o => o == k || vcmap.isMapped(o)  }
      flatFold[Memory, VCMap](others, vcmap) { case (vcmap, other) =>
        vcmap.filterAt(other) { _ == v }
      }
    }
  }

}
