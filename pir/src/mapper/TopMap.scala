package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._
import prism.collection.immutable._
import prism.graph._

case class TopMap(
  cumap:CUMap=CUMap()
) extends CaseTuple[TopMap]

case class CUMap (
  freeMap:BiManyToManyMap[CUMap.K,CUMap.V]=BiManyToManyMap.empty,
  weights:Map[(CUMap.K,CUMap.V),Int]=Map.empty,
  usedMap:BiOneToOneMap[CUMap.K,CUMap.V]=BiOneToOneMap.empty
) extends OneToOneFactorGraphLike[CUMap.K,CUMap.V,CUMap]
object CUMap {
  type K = GlobalContainer
  type V = Routable
}
