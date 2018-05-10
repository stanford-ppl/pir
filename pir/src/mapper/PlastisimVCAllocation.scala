package pir
package mapper

import pir.node._
import pir.pass._
import spade.node._
import prism.collection.mutable
import prism.collection.immutable

class PlastisimVCAllocation(implicit compiler: PIR) extends PIRPass with PlastisimUtil with MappingLogger with BackTracking {
  import pirmeta._

  override def runPass:Unit =  {
    if (!isDynamic(compiler.arch.top)) return
    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[VCMap] { vcmap =>
        log(assignVCColor(VCMap.empty))
      }
    }
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
    bind[Memory,Int,VCMap](
      pnodes=sortedLinks,
      snodes=(k:Memory, m:VCMap) => m(k).toList.sortBy { case v => -m.freeKeys(v).size }, // use the most flexable color
      init=vcmap,
      bindLambda=assignColor _
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
