package pir.util

import pir._
import pir.node._

import prism.collection.mutable._
class PIRMetadata extends prism.node.Metadata {

  /* User defined string name of for an IR. */
  object nameOf extends OneToOneMap[IR, String] with MetadataMap

  /* Controller associated with a node. For memory, it's the lca controller of controller of all its
   * accesses */
  object ctrlOf extends BiManyToOneMap[Node, Controller] with MetadataMap

  /*
   * A memory is local mem if all of it's accesses shares the same controller. An example localMem
   * is accumulator
   * */
  object isLocalMem extends OneToOneMap[Memory, Boolean] with MetadataMap

  /*
   * Defined on accesses of Memory. Child of the ctrlOf(mem) on ancesstor path of the access if
   * ctrlOf(mem) != ctrlOf(access). Otherwise it's the ctrlOf(access) 
   * */
  object topCtrlOf extends OneToOneMap[Node, Controller] with MetadataMap

  object parOf extends OneToOneMap[Node, Int] with MetadataMap
}

