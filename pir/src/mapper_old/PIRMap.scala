package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

trait MappingCollection {
  override def toString = this.getClass.getSimpleName
}

case class PIRMap (
  cumap:CUMap=CUMap.empty,
  fimap:FIMap=FIMap.empty,
  cfmap:ConfigMap=ConfigMap.empty,
  mkmap:MKMap=MKMap.empty,
  vcmap:VCMap=VCMap.empty
) extends SpadeMapLike with MappingCollection {
  type S = PIRMap
  def flatMap[F:ClassTag](lambda: F => EOption[F]):EOption[S] = {
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
  def empty:PIRMap = PIRMap(
    CUMap.empty, 
    FIMap.empty, 
    ConfigMap.empty, 
    MKMap.empty,
    VCMap.empty
  ) 
}

case class CUMap (
  freeMap:BiManyToManyMap[CUMap.K,CUMap.V],
  weights:Map[(CUMap.K,CUMap.V),Int],
  usedMap:BiOneToOneMap[CUMap.K,CUMap.V]
) extends OneToOneFactorGraphLike[CUMap.K,CUMap.V,CUMap] with MappingCollection
object CUMap {
  type K = GlobalContainer
  type V = Routable
  def empty = CUMap(BiManyToManyMap.empty, Map.empty, BiOneToOneMap.empty)
}

case class MKMap(
  fmap:OneToManyMap[MKMap.K,MKMap.V], 
  bmap:OneToManyMap[MKMap.V,MKMap.K]
) extends BiManyToManyMapLike[MKMap.K,MKMap.V,MKMap] with MappingCollection{
  def apply(v:V):KK = bmap(v)
  def get(v:V):Option[KK] = bmap.get(v)
  def contains(v:V) = bmap.contains(v)
}
object MKMap {
  type K = spade.node.Port[_<:PinType]
  type V = GlobalOutput 
  def empty = MKMap(OneToManyMap.empty, OneToManyMap.empty)
}

case class VCMap(
  freeMap:BiManyToManyMap[VCMap.K,VCMap.V],
  weights:Map[(VCMap.K,VCMap.V),Int],
  usedMap:BiManyToOneMap[VCMap.K,VCMap.V]
) extends ManyToOneFactorGraphLike[VCMap.K,VCMap.V,VCMap] with MappingCollection 
object VCMap {
  type K = Memory
  type V = Int
  def empty = VCMap(BiManyToManyMap.empty, Map.empty, BiManyToOneMap.empty)
}
