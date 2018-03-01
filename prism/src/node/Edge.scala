package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

abstract class Edge[N<:Node[N]:ClassTag]() extends IR {
  type A <: Atom[N] with N
  def src:A

  protected val _connected = mutable.ListBuffer[Edge[N]]()
  def connected:List[Edge[N]] = _connected.toList
  def singleConnected:Option[Edge[N]] = {
    assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    connected.headOption
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(e:Edge[N]) = connected.contains(e)
  def connect(e:Edge[N]):this.type = {
    if (isConnectedTo(e)) return this
    _connected += e 
    e.connect(this)
    this
  }

  def disconnectFrom(e:Edge[N]):Unit = {
    assert(this.isConnectedTo(e), s"$this is not connected to $e. this.connected=$connected")
    _connected -= e
    if (e.isConnectedTo(this)) e.disconnectFrom(this)
  }
  def disconnect = connected.foreach(disconnectFrom)

  src.addEdge(this)
}

abstract class DirectedEdge[N<:Node[N]:ClassTag,+TE<:Edge[N]:ClassTag]() extends Edge[N] {
  type E <: TE
  override def connected:List[E] = super.connected.toList.asInstanceOf[List[E]]
  override def singleConnected:Option[E] = super.singleConnected.asInstanceOf[Option[E]]
  override def connect(e:Edge[N]):this.type = {
    super.connect(e.asInstanceOf[E])
  }
}

trait Input[N<:Node[N]] extends DirectedEdge[N,Output[N]]

trait Output[N<:Node[N]] extends DirectedEdge[N,Input[N]]

