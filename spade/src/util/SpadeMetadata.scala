package spade.util

import spade._
import spade.node._

import prism.util._
import prism.collection.mutable._

class SpadeMetadata extends Metadata {

  /* N-Dimential coordinate of a nodes.  */
  val indexOf = new OneToOneMap[SpadeNode, List[Int]] with MetadataMap {
    def update(k:K, v:Int):Unit = update(k, List(v))
    def update(k:K, v:(Int,Int)):Unit = update(k, List(v._1, v._2))
  }
  
  /* Can a counter be the inner most counter in a chain */
  val isInnerCounter = new OneToOneMap[SpadeNode, Boolean] with MetadataMap

  /* Name of nodes */
 //TODO
  val nameOf = new OneToOneMap[SpadeNode, String] with MetadataMap

}
