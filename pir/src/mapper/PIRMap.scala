package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

trait MappingCollection {
  override def toString = this.getClass.getSimpleName
}

case class PIRMap (
  cumap:CUMap,
  fimap:FIMap,
  cfmap:ConfigMap,
  inmap:InMap,
  outmap:OutMap
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
    OutMap.empty
  ) 
}

//case class CUMap(
  //fmap:OneToManyMap[CUMap.K,CUMap.V], 
  //bmap:OneToManyMap[CUMap.V,CUMap.K]
//) extends OneToOneFactorGraphLike[CUMap.K,CUMap.V,CUMap] with MappingCollection 
//object CUMap {
  //type K = GlobalContainer
  //type V = Routable
  //def empty = CUMap(OneToManyMap.empty, OneToManyMap.empty)
//}
case class CUMap (
  freeMap:BiManyToManyMap[CUMap.K,CUMap.V],
  usedMap:BiOneToOneMap[CUMap.K,CUMap.V]
) extends OneToOneFactorGraphLike[CUMap.K,CUMap.V,CUMap]
object CUMap {
  type K = GlobalContainer
  type V = Routable
  def empty = CUMap(BiManyToManyMap.empty, BiOneToOneMap.empty)
}

//case class InMap(
  //fmap:OneToManyMap[InMap.K,InMap.V], 
  //bmap:OneToManyMap[InMap.V,InMap.K]
//) extends OneToOneFactorGraphLike[InMap.K,InMap.V,InMap] with MappingCollection 
//object InMap {
  //type K = GlobalInput
  //type V = spade.node.Input[_]
  //def empty = InMap(OneToManyMap.empty, OneToManyMap.empty)
//}
case class InMap(
  freeMap:BiManyToManyMap[InMap.K,InMap.V],
  usedMap:BiOneToOneMap[InMap.K,InMap.V]
) extends OneToOneFactorGraphLike[InMap.K,InMap.V,InMap] with MappingCollection 
object InMap {
  type K = GlobalInput
  type V = spade.node.Input[_]
  def empty = InMap(BiManyToManyMap.empty, BiOneToOneMap.empty)
}

//case class OutMap(
  //fmap:OneToManyMap[OutMap.K,OutMap.V], 
  //bmap:OneToManyMap[OutMap.V,OutMap.K]
//) extends OneToManyFactorGraphLike[OutMap.K,OutMap.V,OutMap] with MappingCollection 
//object OutMap {
  //type K = GlobalOutput
  //type V = spade.node.Output[_]
  //def empty = OutMap(OneToManyMap.empty, OneToManyMap.empty)
//}
case class OutMap(
  freeMap:BiManyToManyMap[OutMap.K,OutMap.V],
  usedMap:BiOneToManyMap[OutMap.K,OutMap.V]
) extends OneToManyFactorGraphLike[OutMap.K,OutMap.V,OutMap] with MappingCollection 
object OutMap {
  type K = GlobalOutput
  type V = spade.node.Output[_]
  def empty = OutMap(BiManyToManyMap.empty, BiOneToManyMap.empty)
}

