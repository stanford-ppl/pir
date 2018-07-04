package pir
package util

import pir.node._
import prism.collection.mutable._

//TODO: use macro to set names
class PIRMetadata extends Metadata {

  /* User defined string name of for an IR. */
  val nameOf = new OneToOneMap[IR, String] with MetadataMap
  nameOf.setName("nameOf")

  /* Source context of the IR from upper compiler */
  val srcCtxOf = new OneToOneMap[IR, String] with MetadataMap {
    override def get(k:K):Option[V] = k match {
      case k:ContextEnable => get(ctrlOf(k))
      case k:ComputeContext => ctxEnOf(k).flatMap { ctxEn => get(ctxEn) }
      case k:GlobalContainer => 
        super.get(k).orElse {
          ctrlOf.get(k).flatMap { ctrl => get(ctrl) }.orElse {
            val remoteMems = k.collectDown[Memory]().filter { m => isRemoteMem(m) }
            val ctxs = k.collectDown[ComputeContext]()
            val scs = (remoteMems ++ ctxs).flatMap { n => get(n) }
            if (scs.isEmpty) None else Some(scs.mkString("\n"))
          }
        }
      case k => super.get(k)
    }
  }
  srcCtxOf.setName("srcCtxOf")

  /*
   * Whether a memory is a accumulator. Set by spatial
   * */
  val isAccum = new OneToOneMap[Memory, Boolean] with MetadataMap {
    override def apply(k:K):V = get(k).getOrElse(false)
  }
  isAccum.setName("isAccum")

  /* 
   * For ComputeNode: Controller associated with a node. 
   * For memory, it's the lca controller of controller of all its
   * accesses 
   * */
  //val ctrlOf = new BiManyToOneMap[PIRNode, Controller] with MetadataMap
  val ctrlOf = new OneToOneMap[PIRNode, Controller] with MetadataMap {
    def bmap(v:V) = map.flatMap { case (k, `v`) => Some(k); case _ => None }.toSet
  }
  ctrlOf.setName("ctrlOf")

  /*
   * Defined on accesses of Memory. Child of the ctrlOf(mem) on ancesstor path of the access if
   * ctrlOf(mem) != ctrlOf(access). Otherwise it's the ctrlOf(access) 
   * */
  val topCtrlOf = new BiManyToOneMap[PIRNode, Controller] with MetadataMap
  topCtrlOf.setName("topCtrlOf")

  /*
   * User annotation on variable value
   * */
  val boundOf = new OneToOneMap[PIRNode, Any] with MetadataMap
  boundOf.setName("boundOf")

  /*
   * Static dimension of dram
   * */
  val staticDimsOf = new OneToOneMap[DRAM, List[Int]] with MetadataMap

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
  val itersOf =  new OneToOneMap[Any, Option[Long]] with MetadataMap {
    override def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {}
  }
  itersOf.setName("itersOf")

  /*
   * Number of iterations before the node is active through out the execution of the program
   * */
  val countsOf =  new OneToOneMap[Any, Option[Long]] with MetadataMap {
    override def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {}
  }
  countsOf.setName("countsOf")


  /* 
   * If a node is mirrored, originOf points to the original copy
   * */
  val originOf = new OneToOneMap[PIRNode, PIRNode] with MetadataMap { // Many to one
    override def mirror(orig:Any, clone:Any, logger:Option[Logging]=None):Unit = {}
    def bmap(v:V) = map.flatMap { case (k, `v`) => Some(k); case _ => None }.toSet
  }
  originOf.setName("originOf")

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

