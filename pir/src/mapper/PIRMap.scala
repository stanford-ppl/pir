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
  usedMap:BiOneToOneMap[CUMap.K,CUMap.V]
) extends OneToOneFactorGraphLike[CUMap.K,CUMap.V,CUMap] with MappingCollection
object CUMap {
  type K = GlobalContainer
  type V = Routable
  def empty = CUMap(BiManyToManyMap.empty, BiOneToOneMap.empty)
}

case class MKMap(
  fmap:OneToOneMap[MKMap.K,MKMap.V], 
  bmap:OneToManyMap[MKMap.V,MKMap.K]
) extends BiManyToOneMapLike[MKMap.K,MKMap.V,MKMap] {
  def apply(v:V):KK = bmap(v)
  def get(v:V):Option[KK] = bmap.get(v)
  def contains(v:V) = bmap.contains(v)
}
object MKMap {
  type K = spade.node.Port[_]
  type V = GlobalOutput 
  def empty = MKMap(OneToOneMap.empty, OneToManyMap.empty)
}

case class VCMap(
  freeMap:BiManyToManyMap[VCMap.K,VCMap.V],
  usedMap:BiManyToOneMap[VCMap.K,VCMap.V]
) extends ManyToOneFactorGraphLike[VCMap.K,VCMap.V,VCMap] with MappingCollection 
object VCMap {
  type K = Memory
  type V = Int
  def empty = VCMap(BiManyToManyMap.empty, BiManyToOneMap.empty)
}
