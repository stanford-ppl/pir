package pir.mapper

import spade.node._

import scala.collection.mutable

trait DynamicCUPlacer extends PIRPass with BackTracking {
  import pirmeta._

  def dynamicPlace(pmap:PIRMap) = log {
    pmap.flatMap[CUMap] { cumap => 
      bind[CUMap.K, CUMap.V, CUMap](
        init=cumap
      )
    }
  }

}
