package pir.util

import pir._
import pir.graph._
import pir.util.maps._
import scala.collection.mutable.Map

trait PIRMetadata extends { self:Design =>
  object indexOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }

  object vecOf extends MOneToOneMap {
    type K = Node
    type V = VectorIO[_]
  }

  object forRead extends MOneToOneMap {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object forWrite extends MOneToOneMap {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object isHead extends MOneToOneMap {
    type K = Controller
    type V = Boolean
  }

  object isLast extends MOneToOneMap {
    type K = Controller
    type V = Boolean
  }

  object isStreaming extends MOneToOneMap {
    type K = Controller
    type V = Boolean
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object isPipelining extends MOneToOneMap {
    type K = Controller
    type V = Boolean
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object writesOfs extends MOneToOneMap {
    type K = Controller
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  /* Number of children stages on the critical path */
  object lengthOf extends MOneToOneMap {
    type K = Controller
    type V = Int 
  }

  // Including current CU. From current to top
  object ancestorsOf extends MOneToOneMap {
    type K = Controller
    type V = List[Controller]
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  // Including current CU. From current to leaf children 
  object descendentsOf extends MOneToOneMap {
    type K = Controller
    type V = List[Controller]
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object writerOf extends MOneToOneMap {
    type K = OnChipMem 
    type V = Controller
  }

  object readersOf extends MOneToOneMap {
    type K = OnChipMem 
    type V = List[Controller]
  }

  object readCChainsOf extends MOneToOneMap {
    type K = ComputeUnit
    type V = List[CounterChain]
  }

  object writeCChainsOf extends MOneToOneMap {
    type K = ComputeUnit 
    type V = List[CounterChain]
  }

  object compCChainsOf extends MOneToOneMap {
    type K = ComputeUnit 
    type V = List[CounterChain]
  }

  object contentionOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }

  object cycleOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }

  object iterOf extends MOneToOneMap {
    type K = Node
    type V = Long
  }

  object constOf extends MOneToOneMap {
    type K = Node
    type V = Int
  }

  object isTailCollector extends MOneToOneMap {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object isHeadSplitter extends MOneToOneMap {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

}

