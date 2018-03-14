package pir.mapper

import pir._
import pir.node._
import spade.node._
import prism.collection.immutable._

case class PIRMap (
  cumap:CUMap
) {
}

case class CUMap(fmap:OneToManyMap[CUMap.K,CUMap.V], bmap:OneToManyMap[CUMap.V,CUMap.K]) extends BiManyToManyMapLike[CUMap.K, CUMap.V, CUMap] {
}
object CUMap {
  type K = PNode
  type V = SNode
  def empty = CUMap(OneToManyMap.empty, OneToManyMap.empty)
}
