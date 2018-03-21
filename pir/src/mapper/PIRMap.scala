package pir.mapper

import pir._
import pir.node._
import spade.node._
import spade.config._
import prism.collection.immutable._

case class PIRMap (
  cumap:CUMap,
  fimap:FIMap,
  cfmap:ConfigMap
) extends SpadeMapLike {
  type S = PIRMap
}
object PIRMap {
  def empty:PIRMap = PIRMap(CUMap.empty, FIMap.empty, ConfigMap.empty) 
}

case class CUMap(freeMap:CUMap.FM, weights:CUMap.W, usedMap:CUMap.UM) extends FactorGraph[CUMap.K, CUMap.V, CUMap]
object CUMap {
  type K = GlobalContainer
  type V = Routable
  type W = Map[(CUMap.K, CUMap.V), Float]
  type FM = BiManyToManyMap[CUMap.K, CUMap.V]
  type UM = OneToOneMap[CUMap.K, CUMap.V]
  def empty = CUMap(BiManyToManyMap.empty, Map.empty, OneToOneMap.empty)
}
