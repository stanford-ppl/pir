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
trait ProductNode[N<:ProductNode[N]] extends Node[N] with DefNode[N] with Product { self:N =>
  implicit val src = this

  def newIn:Input[N] = new Input[N]
  def newOut:Output[N] = new Output[N]
  val out = newOut

  private val efields = mutable.ListBuffer[Input[N]]()
  productIterator.foreach { field => 
    unpack(field) { case x:N => connectField(x) }
  }

  def connectField[T<:ProductNode[N]](x:T) = { efields += newIn.connect(x.out); x }

  def nfields = efields.toList.map { field =>
    unpack(field) { case x:Edge[N,a,b] => x.connected.map { _.src} }
  }

  def traceOut[T<:N:ClassTag]:T = assertOne(this.collect[T](visitGlobalOut _), 
    s"$this.traceOut[${classTag[T]}]")

  def traceIn[T<:N:ClassTag] = this.collect[T](visitGlobalIn _)
}
