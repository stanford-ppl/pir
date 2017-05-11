package pir.plasticine.util

import pir.util.maps._
import pir.plasticine.graph._
import pir.plasticine.config._
import pir.plasticine.main._
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

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

  /* parallelization factor of a pipeline */
  object parOf extends MOneToOneMap {
    type K = Node
    type V = Int 
  }

}
