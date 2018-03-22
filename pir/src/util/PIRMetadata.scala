package pir.util

import pir._
import pir.node._
import prism._
import prism.util._
import prism.collection.mutable._

//TODO: use macro to set names
class PIRMetadata extends Metadata {

  /* User defined string name of for an IR. */
  val nameOf = new OneToOneMap[IR, String] with MetadataMap
  nameOf.setName("nameOf")

  /* 
   * For ComputeNode: Controller associated with a node. 
   * For memory, it's the lca controller of controller of all its
   * accesses 
   * */
  val ctrlOf = new BiManyToOneMap[PIRNode, Controller] with MetadataMap 
  ctrlOf.setName("ctrlOf")

  /*
   * Whether a memory is a accumulator. Set by spatial
   * */
  val isAccum = new OneToOneMap[Memory, Boolean] with MetadataMap {
    override def apply(k:K):V = get(k).getOrElse(false)
  }
  isAccum.setName("isAccum")

  /*
   * Whether a memory is a accumulator and all of its accesses shares the same controller. Set by memory analyzer
   * */
  val isInnerAccum = new OneToOneMap[Memory, Boolean] with MetadataMap
  isInnerAccum.setName("isInnerAccum")

  /*
   * Defined on accesses of Memory. Child of the ctrlOf(mem) on ancesstor path of the access if
   * ctrlOf(mem) != ctrlOf(access). Otherwise it's the ctrlOf(access) 
   * */
  val topCtrlOf = new OneToOneMap[PIRNode, Controller] with MetadataMap
  topCtrlOf.setName("topCtrlOf")

  /*
   * Control signal indicating the last element of the access
   * */
  val accessDoneOf = new OneToOneMap[LocalAccess, Def] with MetadataMap {
    override def mirror(orig:K, clone:K, logger:Option[Logging]=None):Unit = {}
  }
  accessDoneOf.setName("accessDoneOf")

  /*
   * Enable of CounterChain
   * */
  val enableOf = new OneToOneMap[PIRNode, Def] with MetadataMap {
    override def mirror(orig:K, clone:K, logger:Option[Logging]=None):Unit = {}
  }
  enableOf.setName("enableOf")

  /*
   * User annotation on variable value
   * */
  val boundOf = new OneToOneMap[PIRNode, Any] with MetadataMap
  boundOf.setName("boundOf")

  var pirMap:MOption[PIRMap] = Right(PIRMap.empty)

}

