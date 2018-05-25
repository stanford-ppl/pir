package pir
package mapper

import scala.collection.mutable

trait DynamicPlacement extends BackTrackingMatch { self:PIRPass =>
  import pirmeta._

  def dynamicPlace(pmap:PIRMap) = log {
    pmap.flatMap[CUMap] { cumap => 
      bind[CUMap.K, CUMap.V, CUMap](
        init=cumap
      )
    }
  }

}
