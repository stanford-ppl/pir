package spade
package config

import spade.node._

import prism.collection.immutable._

case class FIMap(
  fmap:OneToOneMap[FIMap.K,FIMap.V], 
  bmap:OneToManyMap[FIMap.V,FIMap.K]
) extends BiManyToOneMapLike[FIMap.K,FIMap.V,FIMap] {
  def apply(v:V):KK = bmap(v)
  def get(v:V):Option[KK] = bmap.get(v)
  def contains(v:V) = bmap.contains(v)
}
object FIMap {
  type K = prism.node.Input[SpadeNode]
  type V = prism.node.Output[SpadeNode]
  def empty = FIMap(OneToOneMap.empty, OneToManyMap.empty)
}
