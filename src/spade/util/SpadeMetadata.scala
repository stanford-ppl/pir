package spade.util

import spade.node._
import spade.network._
import spade._

import pirc.collection.mutable._

trait SpadeMetadata { self:Spade =>
  /* Coordinate of a spade node. Used for pisa and dot codegen */
  object coordOf extends MOneToOneMap { 
    type K = Node
    type V = (Int, Int)
  }
  
  /* Index of a spade node. Used for pisa codegen */
  object indexOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }
  
  /* Can a counter be the inner most counter in a chain */
  object isInnerCounter extends MOneToOneMap {
    type K = Node
    type V = Boolean 
  }

  /* GridNetork of GlobalIO */
  object networkOf extends MOneToOneMap {
    type K = GlobalIO[_<:PortType, _] 
    type V = GridNetwork
  }

  /* Name of nodes */
  object nameOf extends MOneToOneMap {
    type K = Node
    type V = String 
  }

  object validOf extends MOneToOneMap {
    type K = GlobalOutput[_<:PortType, Module] 
    type V = Output[_<:PortType, Module]
  }

}
