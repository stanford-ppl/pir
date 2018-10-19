package prism
package graph

import implicits._

import scala.collection.mutable

/*
 * Product node provide a concise interface to specify IRs in case classes. 
 * Each product node has exactly one output, and fields of the case classes
 * are input nodes. Edges are created while constructing the IR. 
 * While the graph can be mirrored. The connection is not mutable
 * */
trait ProductNode[N] extends Node[N] with Product { self:N =>
  implicit val src = this
  val Nct:ClassTag[N]

  def newIn = new Input
  def newOut = new Output
  val out = newOut

  val efields = productIterator.map { field => 
    unpack(field) { case x:ProductNode[_] => newIn.connect(x.out) }
  }.toList

  def nfields = efields.map { field =>
    unpack(field) { case x:Edge => x.connected.map { _.src} }
  }

  def map[T<:N](func:Any => Any) = {
    val args = productIterator.toList
    val targs = args.map { arg => func(arg) }
    val change = args.zip(targs).exists { case (a,t) => a != t }
    if (change) newInstance[T](targs) else this
  }

  def mapFields[T>:this.type <: Product:TypeTag](func:(String, Any) => Any) = {
    val args = productIterator.toList
    val fields= graph.ProductHelper[T](this).fields
    val targs = fields.map { case (name, arg) => func(name, arg) }
    val change = args.zip(targs).exists { case (a,t) => a != t }
    if (change) newInstance[T](targs) else this
  }
}
