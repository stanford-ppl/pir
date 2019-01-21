package prism
package graph

import scala.collection.mutable

trait Edge[N<:Node[N]] extends IR {

  /*  ------- State -------- */
  def src:N
  val _connected = mutable.ListBuffer[Edge[N]]()

  def connected:List[Edge[N]] = _connected.toList
  def singleConnected:Option[Edge[N]] = {
    assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    connected.headOption
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(e:Edge[N]) = connected.contains(e)
  def connect(e:Edge[N]):this.type = {
    if (e.src.Nct == src.Nct) {
      _connected += e
      e._connected += this
      this
    } else {
      throw new Exception(s"Cannot connect edge with node type ${src.Nct} from $src to edge with node type ${e.src.Nct} from ${e.src}")
    }
  }
  def neighbors = connected.map(_.src).distinct
  def isConnectedTo(n:N) = neighbors.contains(n)

  def disconnectFrom(e:Edge[N]):Unit = {
    assert(this.isConnectedTo(e), s"${this.src}.$this is not connected to ${e.src}.$e. connected=$connected")
    while (_connected.contains(e)) _connected -= e
    while (e._connected.contains(this)) e._connected -= this
  }
  def disconnect = connected.distinct.foreach(disconnectFrom)

  src.addEdge(this)
}

class Input[N<:Node[N]](implicit val src:N) extends Edge[N] {
  override def connect(e:Edge[N]):this.type = {
    e match {
      case _:Output[n] => super.connect(e)
      case e => throw new Exception(s"$this cannot connect non output to input")
    }
  }
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

class Output[N<:Node[N]](implicit val src:N) extends Edge[N] {
  override def connect(e:Edge[N]):this.type = {
    e match {
      case _:Input[n] => super.connect(e)
      case e => throw new Exception(s"$this cannot connect non input to output")
    }
  }
}

