package prism
package graph

import scala.collection.mutable

trait Edge extends IR {

  /*  ------- State -------- */
  def src:Node[_]
  val _connected = mutable.ListBuffer[Edge]()

  def connected:List[Edge] = _connected.toList
  def singleConnected:Option[Edge] = {
    assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    connected.headOption
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(e:Edge) = connected.contains(e)
  def connect(e:Edge):this.type = {
    if (e.src.Nct == src.Nct) {
      _connected += e
      e._connected += this
      this
    } else {
      throw new Exception(s"Cannot connect edge with node type ${src.Nct} from $src to edge with node type ${e.src.Nct} from ${e.src}")
    }
  }
  def neighbors = connected.map(_.src)

  def disconnectFrom(e:Edge):Unit = {
    assert(this.isConnectedTo(e), s"$this is not connected to $e. this.connected=$connected")
    _connected -= e
    if (e.isConnectedTo(this)) e.disconnectFrom(this)
  }
  def disconnect = connected.foreach(disconnectFrom)

  def Ns:List[Node[_]] = connected.map(_.src).toList

  src.addEdge(this)
}

class Input(implicit val src:Node[_]) extends Edge {
  override def connect(e:Edge):this.type = {
    e match {
      case _:Output => super.connect(e)
      case e => throw new Exception(s"$this cannot connect non output to input")
    }
  }
}

class Output(implicit val src:Node[_]) extends Edge {
  override def connect(e:Edge):this.type = {
    e match {
      case _:Input => super.connect(e)
      case e => throw new Exception(s"$this cannot connect non input to output")
    }
  }
}

