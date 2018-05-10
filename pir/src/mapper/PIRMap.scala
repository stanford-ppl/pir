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
  inmap:InMap=InMap.empty,
  outmap:OutMap=OutMap.empty,
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
    InMap.empty,
    OutMap.empty,
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

case class InMap(
  freeMap:BiManyToManyMap[InMap.K,InMap.V],
  usedMap:BiOneToOneMap[InMap.K,InMap.V]
) extends OneToOneFactorGraphLike[InMap.K,InMap.V,InMap] with MappingCollection 
object InMap {
  type K = GlobalInput
  type V = spade.node.Input[_]
  def empty = InMap(BiManyToManyMap.empty, BiOneToOneMap.empty)
}

case class OutMap(
  freeMap:BiManyToManyMap[OutMap.K,OutMap.V],
  usedMap:BiOneToManyMap[OutMap.K,OutMap.V]
) extends OneToManyFactorGraphLike[OutMap.K,OutMap.V,OutMap] with MappingCollection 
object OutMap {
  type K = GlobalOutput
  type V = spade.node.Output[_]
  def empty = OutMap(BiManyToManyMap.empty, BiOneToManyMap.empty)
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
