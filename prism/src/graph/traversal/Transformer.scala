package prism
package graph

import scala.collection.mutable

trait Transformer extends Logging {

  def removeUnusedIOs(node:N) = {
    node.edges.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def removeNode(node:N) = {
    dbg(s"Remove $node")
    node.metadata.foreach{_.reset}
    val neighbors = node.neighbors
    node.edges.foreach { io => 
      val connected = io.connected.map(_.src)
      io.disconnect
      connected.foreach(removeUnusedIOs)
    }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      assert(!parent.children.contains(node), s"$parent still contains $node after removeNode")
    }
    neighbors.foreach { nb =>
      assert(!nb.neighbors.contains(node), 
        s"neighbor=$nb's neighbors still contains $node after removeNode $node")
    }
  }

  def swapParent(node:N, newParent:N):Unit = {
    dbg(s"swapParent($node, $newParent)")
    if (newParent.isParentOf(node)) return
    node.parent.foreach { parent => parent.removeChild(node) }
    node.setParent(newParent)
  }

  def swapDep(node:N, depFrom:N, depTo:N) = swapConnections(node, depFrom, depTo, (n:N) => n.localOuts)
  def swapDeped(node:N, depedFrom:N, depedTo:N) = swapConnections(node, depedFrom, depedTo, (n:N) => n.localIns)

  def swapConnections(node:N, from:N, to:N, edges:N => Seq[Edge]) = {
    dbg(s"swapConnection(node=$node, from=$from, to=$to)")
    assert(edges(from).size == edges(to).size, s"$from and $to have different number of edges from=${edges(from)} to ${edges(to)}")
    dbg(s"from=$from, fromEdges=${edges(from)}, to=$to, toEdges=${edges(to)}")
    val connected = node.localEdges.flatMap { io =>
      val fromedges = io.connected.filter { _.src == from }
      if (fromedges.nonEmpty) Some((io, fromedges))
      else None
    }
    assert(connected.nonEmpty, s"$node is not connected to $from")
    connected.foreach { case (io, fromedges) =>
      fromedges.foreach { fromio =>
        val index = edges(from).indexOf(fromio)
        val toio = edges(to)(index)
        io.disconnectFrom(fromio)
        io.connect(toio)
      }
    }
  }

  def swapConnection(target:Edge, from:Edge, to:Edge):Unit = {
    dbg(s"swapConnection(target=$target, from=$from, to=$to)")
    assert(target.isConnectedTo(from), s"${target.src}.$target is not connect to ${from.src}.$from")
    target.disconnectFrom(from)
    target.connect(to)
  }

  def swapConnection(node:N, from:Edge, to:Edge):Unit = {
    val connected = node.edges.filter { io => io.isConnectedTo(from) }
    assert (connected.nonEmpty, s"$node is not connected to ${from.src}.$from")
    connected.foreach { io => swapConnection(io, from, to) }
  }

  def areConnected(node1:N, node2:N) = {
    node1.edges.exists { io1 => node2.edges.exists { io2 => io1.isConnectedTo(io2) } }
  }

  def disconnect(a:N, b:N) = {
    val pairs = a.edges.flatMap { aio => 
      aio.connected.filter{ _.src == b }.map { bio => (aio, bio) }
    }
    assert(pairs.nonEmpty, s"$a is not connected to $b, a.connected=${a.deps++a.depeds}")
    pairs.foreach { case (aio,bio) => aio.disconnectFrom(bio) }
  }

  def mirrorAll(nodes:Iterable[N], mapping:mutable.Map[N,N]=mutable.Map.empty):mutable.Map[N,N] = {
    if (nodes.nonEmpty) {
      nodes.head match {
        case node:FieldNode[_] => mirrorField(nodes.asInstanceOf[Iterable[FieldNode[_]]], mapping)
        case node:ProductNode[_] => mirrorProduct(nodes.asInstanceOf[Iterable[ProductNode[_]]], mapping)
        case _ => throw new Exception(s"Don't know thow to mirror $nodes")
      }
    }
    mapping
  }

  /*
   * Make a copy of nodes. 
   * Structure the mirrored nodes with identical connection and hiearchy as the original
   * nodes. If part of the connection or hiearchy is not in the mirror list, use the 
   * original nodes to build the hiearchy and connection. Only input connection order
   * is preserved
   * */
  def mirrorField(nodes:Iterable[FieldNode[_]], mapping:mutable.Map[N,N]) = {
    // First pass mirror all nodes and put in a map
    nodes.foreach { n => mirror[N](n, mapping) }

    // Second pass build hiearchy and connection
    mapping.foreach { case (n,m) =>
      n.parent.foreach { p => 
        mapping.get(p).foreach { mp =>
          m.unsetParent
          m.setParent(mp)
        }
      }
      n.localIns.zipWithIndex.foreach { case (io, idx) =>
        val mio = m.localIns(idx)
        io.connected.foreach { c => 
          val cs = c.src
          val cidx = cs.edges.indexOf(c)
          val mcs = mapping.getOrElse(cs, cs)
          val mc = mcs.edges(cidx)
          mio.connect(mc)
        }
      }
    }
  }

  def mirrorProduct(nodes:Iterable[ProductNode[_]], mapping:mutable.Map[N,N]) = {
    nodes.foreach { n => mirror(n, mapping) }
  }

  final def mirror[T](n:Any, mapping:mutable.Map[N,N]=mutable.Map.empty):T = {
    (unpack(n) {
      case n:Node[_] => mapping.getOrElseUpdate(n, mirrorN(n))
      case n => n
    }).asInstanceOf[T]
  }

  def mirrorN(n:N, mapping:mutable.Map[N,N]=mutable.Map.empty):N = {
    val margs = newInstanceArgs(n, mapping)
    mapping.getOrElseUpdate(n, {
      val m = n.newInstance[N](margs)
      m.mirrorMetas(n)
      m
    })
  }

  def newInstanceArgs(n:Node[_], mapping:mutable.Map[N,N]):Seq[Any] = n match {
    case n:EnvNode[_] with ProductNode[_] => n.productIterator.map { arg => mirror[Any](arg, mapping) }.toList :+ getEnv
    case n:ProductNode[_] => n.productIterator.map { arg => mirror[Any](arg, mapping) }.toList
    case n:EnvNode[_] with Product => n.productIterator.toSeq :+ getEnv
    case n:EnvNode[_] => Seq(getEnv)
    case n => Nil
  }

  def getEnv = this match {
    case env:BuildEnvironment => env
    case _ => throw PIRException(s"Cannot find env")
  }

  def transform[T](x:T):T = {
    (x match {
      case x:Iterable[_] => x.map { transform }
      case x:Option[_] => x.map { transform }
      case (a,b) => (transform(a), transform(b))
      case (a,b,c) => (transform(a), transform(b), transform(c))
      case (a,b,c,d) => (transform(a), transform(b), transform(c), transform(d))
      case x:ProductNode[_] => x.map(transform)
      case x => x
    }).asInstanceOf[T]
  }

}
