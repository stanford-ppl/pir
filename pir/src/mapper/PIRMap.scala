package pir.mapper

import pir._
import pir.node._
import spade.node._
import prism._

case class PIRMap (
  cumap:CUMap,
  fimap:FIMap,
  cfmap:ConfigMap,
) extends SpadeMapLike {
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
  //def isMapped(n:PNode) = n match {
    //case n:GlobalContainer => cumap.contains(n)
  //}
}
object PIRMap {
  def empty:PIRMap = PIRMap(CUMap.empty, FIMap.empty, ConfigMap.empty) 
}

case class CUMap(
  fmap:OneToManyMap[CUMap.K,CUMap.V], 
  bmap:OneToManyMap[CUMap.V,CUMap.K]
) extends FactorGraphLike[CUMap.K,CUMap.V,CUMap]
object CUMap {
  type K = GlobalContainer
  type V = Routable
  def empty = CUMap(OneToManyMap.empty, OneToManyMap.empty)
}
