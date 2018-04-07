package pir.mapper

import pir.node._
import spade.node._

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking with Routing {
  import pirmeta._

  private lazy val dpfx = debug && routingVerbosity > 0

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = dbgblk(dpfx, s"Mapping ${quote(cuP)} -> ${quote(cuS)}"){
    val unmapped = pmap.cumap.freeKeys.toSet
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(dpfx, s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap =>
      val newMapped = (unmapped -- pmap.cumap.freeKeys)
      dbg(s"newMapped=${newMapped.map(quote)}")
      flatFold(newMapped, pmap) { case (pmap, cuP) =>
        route(cuP, addIOs(pmap,cuP))
      }
    }
  }

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap =>
      val init = pmap.cumap.mappedKeys.foldLeft(pmap) { case (pmap, cuP) => addIOs(pmap, cuP) }
      logging(bind[CUMap.K, CUMap.V, PIRMap](
        pnodes=pmap.cumap.keys.toSet,
        snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap(cuP).toList.sortBy { case v => -m.cumap.bmap(v).size},
        init=init,
        bindLambda=bindLambda _
      ))
    }
  }

}
