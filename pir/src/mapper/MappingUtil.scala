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
      case (n, bt@BindingTrace(pnode, mapping)) => 
        bt.last.fold { mappedTo(n, mapping) } { failure => mappedTo(n, failure) }
      case (n, InvalidFactorGraph(fg, k)) => mappedTo(n, fg)

      case (n:Routable, mapping:PIRMap) => mappedTo(n, mapping.cumap)
      case (n:Routable, mapping:CUMap) => mapping.usedMap.bmap.get(n)

      case (edge:prism.node.Input[_], mapping:PIRMap) => mappedTo(n, mapping.fimap)
      case (edge:prism.node.Input[_], mapping:FIMap) => mapping.get(edge.asInstanceOf[FIMap.K])
      case (n, mapping) => err(s"don't know how to lookup mappedTo(${quote(n)}, $mapping)")
    }).asInstanceOf[Option[T]]
  }

  def isMapped(n:Any, mapping:Any=pirMap):Boolean = mappedTo[Any](n, mapping).nonEmpty
}
