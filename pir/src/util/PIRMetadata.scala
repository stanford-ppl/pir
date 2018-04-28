package pir.util

import pir.node._
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
   * User annotation on variable value
   * */
  val boundOf = new OneToOneMap[PIRNode, Any] with MetadataMap
  boundOf.setName("boundOf")

  /*
   * bufferDepth of on chip mem
   * */
  val bufferDepthOf = new OneToOneMap[Memory, Int] with MetadataMap
  bufferDepthOf.setName("bufferDepthOf")

  /*
   * output parallelization of a node
   * */
  val parOf =  new OneToOneMap[Any, Int] with MetadataMap
  parOf.setName("parOf")

  /*
   * Number of iterations before the node is active/done again with respect to enable of the
   * ContextEnable
   * */
  val itersOf =  new OneToOneMap[Any, Int] with MetadataMap {
    override def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {}
  }
  itersOf.setName("itersOf")

  /*
   * Number of iterations before the node is active through out the execution of the program
   * */
  val countsOf =  new OneToOneMap[Any, Int] with MetadataMap {
    override def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {}
  }
  countsOf.setName("countsOf")

  /* ------------- Plastsim metadata (start) ---------- */
  val linkGroupOf = new OneToOneMap[Memory, Set[Memory]] with MetadataMap
  linkGroupOf.setName("linkGroupOf")
  // Interference memories are memories that are read by the same compute context. They must be
  // assigned to distinct virtual class to avoid head of line blocking
  val infMemsOf = new OneToOneMap[Memory, Set[Memory]] with MetadataMap
  infMemsOf.setName("infMemsOf")
  /* ------------- Plastsim metadata (start) ---------- */

  var pirMap:EOption[PIRMap] = Right(PIRMap.empty)

}

