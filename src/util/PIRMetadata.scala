package pir.util

import pir._
import pir.graph._
import pir.util.maps._
import scala.collection.mutable.Map

trait PIRMetadata extends { self:Design =>
  // Metadata Maps
  object indexOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }

  object vecOf extends MOneToOneMap {
    type K = Node
    type V = VectorIO[_]
  }
}

