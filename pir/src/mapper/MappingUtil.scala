package pir
package mapper

import pir.node._
import spade.node._
import prism.mapper._
import prism.collection.immutable._

trait MappingUtil { self:Logging =>

  val pirmeta:PIRMetadata
  import pirmeta._

  def mappedTo[T](n:Any, mapping:Any=pirMap):Option[T] = {
    ((n, mapping) match {
      case (n, Left(mapping)) => mappedTo(n, mapping) 
      case (n, Right(mapping)) => mappedTo(n, mapping) 
      case (n, bt@BindingTrace(pnode, mapping)) => mappedTo(n, mapping)

      case (n:CUMap.V, mapping:PIRMap) => mappedTo(n, mapping.cumap)
      case (n:CUMap.V, mapping:CUMap) => mapping.usedMap.bmap.get(n)
      case (n:CUMap.K, mapping:PIRMap) => mappedTo(n, mapping.cumap)
      case (n:CUMap.K, mapping:CUMap) => mapping.usedMap.get(n)

      case (edge:prism.node.Edge[_], mapping:PIRMap) => mappedTo(n, mapping.mkmap)
      case (edge:prism.node.Edge[_], mapping:MKMap) => mapping.get(edge.src.asInstanceOf[MKMap.K])

      case ((from:Edge, to:Edge), mapping) => 
        zipMap(mappedTo[Set[MKMap.V]](from, mapping), mappedTo[Set[MKMap.V]](to, mapping)) { 
          case (tmk, fmk) if tmk == fmk => Some(tmk)
          case _ => None
        }.flatten.asInstanceOf[Option[T]]

      case (n, mapping) => err(s"don't know how to lookup mappedTo(${quote(n)}, $mapping)")
    }).asInstanceOf[Option[T]]
  }

  def isMapped(n:Any, mapping:Any=pirMap):Boolean = mappedTo[Any](n, mapping).nonEmpty
}
