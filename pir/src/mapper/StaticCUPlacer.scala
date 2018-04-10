package pir.mapper

import pir.node._
import spade.node._

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking with Routing with Debugger {
  import pirmeta._

  private lazy val dpfx = debug && routingVerbosity > 0

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    val unmapped = pmap.cumap.freeKeys.toSet
    breakPoint(pmap) {
      pmap.flatMap[CUMap] { cumap => 
        dbgblk(dpfx, s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
      }.flatMap { pmap =>
        route(cuP, addIOs(pmap,cuP))
      }
    }
  }

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap =>
      log(bind[CUMap.K, CUMap.V, PIRMap](
        pnodes=pmap.cumap.freeKeys.toSet,
        snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList.sortBy { case v => -m.cumap.freeKeys(v).size},
        init=pmap,
        bindLambda=bindLambda _
      ))
    }
  }

  override def finPass(runner:RunPass[_]):Unit = {
    super.finPass(runner)
    pirMap.fold ({ failure =>
      fail(s"Static place and route failed: ${failure}")
    },{ mapping =>
      succeed(s"Static place and route succeeded")
    })
  }

  override def quote(n:Any) = n match {
    case n:GlobalIO => s"${globalOf(n).get}.${super.quote(n)}"
    case n => super.quote(n)
  }

}
