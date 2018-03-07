package pir.util

import pir._
import pir.node._
import prism._
import prism.util._
import prism.collection.mutable._

class PIRMetadata extends Metadata {

  /* User defined string name of for an IR. */
  object nameOf extends OneToOneMap[IR, String] with MetadataMap

  /* 
   * For ComputeNode: Controller associated with a node. 
   * For memory, it's the lca controller of controller of all its
   * accesses 
   * */
  object ctrlOf extends BiManyToOneMap[PIRNode, Controller] with MetadataMap 

  /*
   * Whether a memory is a accumulator. Set by spatial
   * */
  object isAccum extends OneToOneMap[Memory, Boolean] with MetadataMap {
    override def apply(k:K):V = get(k).getOrElse(false)
  }

  /*
   * Whether a memory is a accumulator and all of its accesses shares the same controller. Set by memory analyzer
   * */
  object isInnerAccum extends OneToOneMap[Memory, Boolean] with MetadataMap

  /*
   * Defined on accesses of Memory. Child of the ctrlOf(mem) on ancesstor path of the access if
   * ctrlOf(mem) != ctrlOf(access). Otherwise it's the ctrlOf(access) 
   * */
  object topCtrlOf extends OneToOneMap[PIRNode, Controller] with MetadataMap

  /*
   * Control signal indicating the last element of the access
   * */
  object accessDoneOf extends OneToOneMap[LocalAccess, Def] with MetadataMap {
    override def mirror(orig:K, clone:K, logger:Option[Logging]=None):Unit = {}
  }

  /*
   * Enable of CounterChain
   * */
  object enableOf extends OneToOneMap[PIRNode, Def] with MetadataMap

}

