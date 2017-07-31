package pir.util

import pir._
import pir.graph._
import pir.util.maps._
import scala.collection.mutable
import scala.util.{Try, Success, Failure}

trait PIRMetadata extends { self:Design =>

  val maps = mutable.ListBuffer[PIRMetadataMaps]()

  def reset = {
    maps.foreach(_.clear)
  }

  def summary(n:Node):List[String] = {
    maps.flatMap { map => map.getInfo(n) }.toList
  }

  trait PIRMetadataMaps extends MMap { 
    maps += this
    def getInfo(n:Node):Option[String] = {
      Try {
        n.asInstanceOf[K] //TODO refactor this 
      } match {
        case Success(k) => get(k).map { v => s"${name}=${v}" }
        case Failure(e) => None
      }
    }
    def info(n:K):String = { s"${name}($n)=${get(n)}" }
  }

  object indexOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object vecOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = VectorIO[_]
  }

  object forRead extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
    override def check(rec:(K,V)):Unit = {}
  }

  object forWrite extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object isHead extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = Boolean
  }

  object isLast extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = Boolean
  }

  object isStreaming extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = Boolean
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object isPipelining extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = Boolean
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object scuOf extends MBiOneToOneMap {
    type K = Controller
    type V = Controller
  }

  /* Number of children stages on the critical path */
  object lengthOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = Int 
  }

  // Including current CU. From current to top
  object ancestorsOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = List[Controller]
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  // Including current CU. From current to leaf children 
  object descendentsOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller
    type V = List[Controller]
    //Allow redefine
    override def check(rec:(K,V)):Unit = {}
  }

  object writersOf extends MOneToOneMap with PIRMetadataMaps {
    type K = OnChipMem 
    type V = List[Controller]
  }

  object readersOf extends MOneToOneMap with PIRMetadataMaps {
    type K = OnChipMem 
    type V = List[Controller]
  }

  /* counters for read address calculation. 
   * inner counter comes first */
  object readCChainsOf extends MOneToOneMap with PIRMetadataMaps {
    type K = ComputeUnit
    type V = List[CounterChain]
    override def check(rec:(K,V)):Unit = {}
  }

  /* counters for write address calculation. 
   * inner counter comes first */
  object writeCChainsOf extends MOneToOneMap with PIRMetadataMaps {
    type K = ComputeUnit 
    type V = List[CounterChain]
    override def check(rec:(K,V)):Unit = {}
  }

  /* counters for computation
   * inner counter comes first */
  object compCChainsOf extends MOneToOneMap with PIRMetadataMaps {
    type K = ComputeUnit 
    type V = List[CounterChain]
    override def check(rec:(K,V)):Unit = {}
  }

  object swapReadCChainOf extends MOneToOneMap with PIRMetadataMaps {
    type K = MultiBuffer
    type V = CounterChain
  }

  object swapWriteCChainOf extends MOneToOneMap with PIRMetadataMaps {
    type K = MultiBuffer
    type V = CounterChain
  }

  object accumCounterOf extends MOneToOneMap with PIRMetadataMaps {
    type K = AccumPR 
    type V = Counter
  }

  object localCChainOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Controller 
    type V = CounterChain
  }

  object contentionOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object cycleOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Long
  }

  object totalCycleOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Long
  }

  object iterOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Long
  }

  object constOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object parOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object rparOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object wparOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Int
  }

  object boundOf extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = AnyVal
    override def check(rec:(K,V)):Unit = {}
  }

  object isTailCollector extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object isHeadSplitter extends MOneToOneMap with PIRMetadataMaps {
    type K = Node
    type V = Boolean
    override def apply(k:K):V = {
      super.get(k).getOrElse(false)
    }
  }

  object backPressureOf extends MOneToOneMap with PIRMetadataMaps {
    type K = OnChipMem
    type V = Boolean
    override def check(rec:(K,V)):Unit = {}
    override def apply(k:K):V = {
      super.get(k).getOrElse(true)
    }
  }

}

