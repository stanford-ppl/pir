package prism
package graph

import scala.collection.mutable

/*
 * Product node provide a concise interface to specify IRs in case classes. 
 * Each product node has exactly one output, and fields of the case classes
 * are input nodes. Edges are created while constructing the IR. 
 * While the graph can be mirrored. The connection is not mutable
 * */
abstract class ProductNode[N:ClassTag](implicit design:Design) extends Node[N] with Product { self:N =>
  implicit val src = this
  val Nct = classTag[N]

  def newIn = new Input
  def newOut = new Output
  val out = newOut

  val efields = productIterator.map { field => 
    unpack(field) { case x:ProductNode[_] => newIn.connect(x.out) }
  }.toList

  def nfields = efields.map { field =>
    unpack(field) { case x:Edge => x.connected.map { _.src} }
  }
}
