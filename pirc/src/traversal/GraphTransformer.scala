package prism.traversal

import pirc._
import prism.node._

import scala.reflect._
import scala.collection.JavaConverters._

trait GraphTransformer {
  type N<:Node[N] with Product
  type P<:SubGraph[N] with N
  type A<:Atom[N] with N
  type D <: Design
  implicit val nct:ClassTag[N]

  def removeNode(node:N) = {
    val neighbors = node.neighbors
    node.ios.foreach { io => 
      val connected = io.connected.map(_.src)
      io.disconnect
      connected.foreach(removeUnusedIOs)
    }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
    }
  }

  def swapParent(node:N, newParent:N) = {
    node.parent.foreach { parent =>
      parent.removeChild(node)
    }
    node.setParent(newParent.asInstanceOf[node.P])
  }

  def swapOutputs[A1<:A](node:A, from:A1, to:A1) = swapConnections(node, from, to, (n:A1) => n.outs)
  def swapInputs[A1<:A](node:A, from:A1, to:A1) = swapConnections(node, from, to, (n:A1) => n.ins)

  def swapConnections[A1<:A](node:A, from:A1, to:A1, ios:A1 => List[Edge[N]]) = {
    assert(ios(from).size == ios(to).size, s"$from and $to have different number of ios from=${ios(from)} to ${ios(to)}")
    val connected = node.ios.flatMap { io =>
      val fromios = io.connected.filter { _.src == from }
      if (fromios.nonEmpty) Some((io, fromios))
      else None
    }
    assert(connected.nonEmpty, s"$node is not connected to $from")
    connected.foreach { case (io, fromios) =>
      fromios.foreach { fromio =>
        val index = ios(from).indexOf(fromio)
        val toio = ios(to)(index)
        io.disconnectFrom(fromio)
        io.connect(toio.asInstanceOf[io.E])
      }
    }
  }

  def swapConnection[A1<:A](node:A, from:Edge[N], to:Edge[N]) = {
    val connected = node.ios.filter { io =>
      if (io.isConnectedTo(from)) {
        io.disconnectFrom(from)
        io.connect(to.asInstanceOf[io.E])
        true
      } else false
    }
    assert (connected.nonEmpty, s"$node is not connected to $from")
  }

  def disconnect(a:A, b:A) = {
    val pairs = a.ios.flatMap { aio => 
      aio.connected.filter{ _.src == b }.map { bio => (aio, bio) }
    }
    assert(pairs.nonEmpty, s"$a is not connected to $b, a.connected=${a.deps++a.depeds}")
    pairs.foreach { case (aio,bio) => aio.disconnectFrom(bio) }
  }

  def removeUnusedIOs(node:N) = {
    node.ios.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    n match {
      case n if mapping.contains(n) => mapping
      case n:N => 
        val args = n.values //n.productIterator.toList
        val newMapping = args.foldLeft(mapping) { case (mapping, arg) => mirrorX(arg, mapping) }
        if (!newMapping.contains(n)) {
          newMapping + (n -> n.newInstance[Any](args.map { a => newMapping(a) }))
        } else newMapping
      case n:Option[_] => 
        val newMapping = n.foldLeft(mapping) { case (mapping, n) => mirrorX(n, mapping) }
        newMapping + (n -> n.map { n => newMapping(n) } )
      case n:Iterable[_] => 
        val newMapping = n.foldLeft(mapping) { case (mapping, n) => mirrorX(n, mapping) }
        newMapping + (n -> n.map { n => newMapping(n) } )
      case n:Iterator[_] => 
        val newMapping = n.foldLeft(mapping) { case (mapping, n) => mirrorX(n, mapping) }
        newMapping + (n -> n.map { n => newMapping(n) } )
      case n => mapping + (n -> n)
    }
  }
  
  def lookUp[X](a:X, mapping:Map[N,N]):X = {
    (a match {
      case a:N => mapping(a)
      case Some(a) => Some(lookUp(a, mapping))
      case a:Iterable[_] => a.map{ a => lookUp(a, mapping) }
      case a:Iterator[_] => a.map{ a => lookUp(a, mapping) }
      case a => a
    }).asInstanceOf[X]
  }
}
