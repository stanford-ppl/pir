package pir
package mapper

import pir.node._

class CUPlacer(implicit compiler:PIR) extends PIRPass with StaticRouting with BackTrackingMatch {
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap =>
      compiler.arch.top match {
        case topS if isStatic(topS) => place(pmap)
        case topS if isDynamic(topS) => place(pmap)
        case topS if isAsic(topS) => Right(pmap)
      }
    }
  }

  def bindLambda(cuP:CUMap.K, cuS:CUMap.V, pmap:PIRMap) = {
    pmap.flatMap[CUMap] { cumap => 
      dbgblk(s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
    }.flatMap { pmap => route(cuP, pmap) }.map { pmap => snapshot(pmap) }
  }

  def place(pmap:PIRMap) = log {
    bind(
      pnodes=pmap.cumap.freeKeys.toSeq,
      snodes=(cuP:CUMap.K, m:PIRMap) => m.cumap.freeValues(cuP).toList,
      init=pmap,
      bindLambda=bindLambda _
    )(
      rankP = { case (cuP, m) => (-m.cumap.freeValues(cuP).size, cuP.collectDown[GlobalIO]().size) },
      rankS = { case (cuS,m) => -m.cumap.freeKeys(cuS).size }
    )
  }


  override def finPass:Unit = {
    super.finPass
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

}

