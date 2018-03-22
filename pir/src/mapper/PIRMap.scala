package pir.mapper

import pir._
import pir.node._
import spade.node._
import spade.config._
import prism._
import prism.collection.immutable._

case class PIRMap (
  cumap:CUMap,
  fimap:FIMap,
  cfmap:ConfigMap
) extends SpadeMapLike {
  type S = PIRMap
  def flatMap[F:ClassTag](lambda: F => MOption[F]):MOption[S] = {
    val field = productIterator.toSeq.collect { case field:F => field }.head
    lambda(field).map { newField =>
      val args = productIterator.toSeq.map {
        case `field` => newField
        case field => field
      }
      val constructor = this.getClass.getConstructors()(0) 
      constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[S]
    }
  }
}
object PIRMap {
  def empty:PIRMap = PIRMap(CUMap.empty, FIMap.empty, ConfigMap.empty) 
}

case class CUMap(freeMap:CUMap.FM, var weights:CUMap.W) extends FactorGraph[CUMap.K, CUMap.V, CUMap]
object CUMap {
  type K = GlobalContainer
  type V = Routable
  type W = Map[(CUMap.K, CUMap.V), Float]
  type FM = BiManyToManyMap[CUMap.K, CUMap.V]
  def empty = CUMap(BiManyToManyMap.empty, Map.empty)
}
