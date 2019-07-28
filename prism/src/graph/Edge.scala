package prism
package graph

import scala.collection.mutable

trait Edge[N<:Node[N], A<:Edge[N,A,B], B<:Edge[N,B,A]] extends IR { self:A =>
  type TA = A
  type TB = B

  /*  ------- State -------- */
  def src:N
  val _connected = mutable.ListBuffer[B]()
  /*  ------- Metadata -------- */
  val dynamic = new Metadata[Boolean]("dynamic", default=Some(false))
  def isDynamic = dynamic.get
  def isStatic = !dynamic.get

  def connected:List[B] = _connected.toList
  def singleConnected:Option[B] = {
    assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    connected.headOption
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(e:Edge[N,_,_]) = _connected.contains(e.as[B])
  def onlyConnectedTo(e:Edge[N,B,A]) = _connected.forall { _ == e }
  def connect(e:Edge[N,B,A]):this.type = {
    _connected += e.as[B]
    e._connected += this.as[A]
    this
  }
  def neighbors:Seq[N] = _connected.map(_.src).distinct
  def isConnectedTo(n:Node[N]) = _connected.exists{ _.src == n }

  def disconnectFrom(e:Edge[N,B,A]):Unit = {
    assert(this.isConnectedTo(e), s"${this.src}.$this is not connected to ${e.src}.$e. connected=$connected")
    while (_connected.contains(e.as[B])) _connected -= e
    while (e._connected.contains(this.as[A])) e._connected -= this.as[A]
  }
  def disconnect = _connected.distinct.foreach(disconnectFrom)

  src.addEdge(this)
}
object Edge {
  implicit def edgeToA[N<:Node[N], A<:Edge[N,A,B], B<:Edge[N,B,A]](e:Edge[N,A,B]):A = e.as[A]
}
/*
 * Un-directed edge
 * */
trait UEdge[N<:Node[N]] extends Edge[N,UEdge[N],UEdge[N]]

/*
 * Directed Edges
 * */
class Input[N<:Node[N]](implicit val src:N) extends Edge[N,Input[N],Output[N]] {
  def swapOutputConnection(from:Output[N], to:Output[N]) = {
    _connected.transform {
      case `from` =>
        to._connected += this
        from._connected -= this
        to
      case from => from
    }
  }
}

class Output[N<:Node[N]](implicit val src:N) extends Edge[N,Output[N],Input[N]]


object WithNode {
  def unapply(x:Edge[_,_,_]) = Some(x.src)
}
