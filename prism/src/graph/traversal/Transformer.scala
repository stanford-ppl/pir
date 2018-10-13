package prism
package graph

import scala.collection.mutable

trait Transformer {

  def removeUnusedIOs(node:N) = {
    node.edges.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def removeNode(node:N) = {
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
    if (newParent.isParentOf(node)) return
    node.parent.foreach { parent => parent.removeChild(node) }
    node.setParent(newParent)
  }

  def swapOutputs(node:N, from:N, to:N) = swapConnections(node, from, to, (n:N) => n.outs)
  def swapInputs(node:N, from:N, to:N) = swapConnections(node, from, to, (n:N) => n.ins)

  def swapConnections(node:N, from:N, to:N, edges:N => Seq[Edge]) = {
    assert(edges(from).size == edges(to).size, s"$from and $to have different number of edges from=${edges(from)} to ${edges(to)}")
    val connected = node.edges.flatMap { io =>
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

  def mirror[N<:Node[_]:ClassTag](nodes:Iterable[N]):Iterable[Node[_]] = {
    classTag[N] match {
      case ct if ct == classTag[FieldNode[_]] => mirrorField(nodes.asInstanceOf[Iterable[FieldNode[_]]])
      case ct if ct == classTag[ProductNode[_]] => mirrorProduct(nodes.asInstanceOf[Iterable[ProductNode[_]]])
      case ct => throw new Exception(s"Don't know thow to mirror node of type $ct")
    }
  }

  /*
   * Make a copy of nodes. 
   * Structure the mirrored nodes with identical connection and hiearchy as the original
   * nodes. If part of the connection or hiearchy is not in the mirror list, use the 
   * original nodes to build the hiearchy and connection.
   * */
  def mirrorField(nodes:Iterable[FieldNode[_]]):Iterable[N] = {
    // First pass mirror all nodes and put in a map
    val nmap:Map[N,N] = nodes.map { n => 
      val m = n match {
        case x:Product => n.newInstance[N](x.productIterator.toList :+ n.Nct :+ x.design)
        case x => n.newInstance[N](Seq(n.Nct, x.design))
      }
      n -> m
    }.toMap

    // Second pass build hiearchy and connection
    nmap.foreach { case (n,m) =>
      n.parent.foreach { p => 
        m.setParent(nmap.getOrElse(p, p))
      }
      n.localEdges.zipWithIndex.foreach { case (io, idx) =>
        val mio = m.localEdges(idx)
        io.connected.foreach { c => 
          val cs = c.src
          val cidx = cs.edges.indexOf(c)
          val mcs = nmap.getOrElse(cs, cs)
          val mc = mcs.edges(cidx)
          mio.connect(mc)
        }
      }
    }
    nmap.values
  }

  def mirrorProduct(nodes:Iterable[ProductNode[_]]):Iterable[N] = {
    val mapping = mutable.Map[N,N]()
    nodes.foreach { n => mirrorProduct(n, mapping) }
    mapping.values
  }

  def mirrorProduct(n:Any, mapping:mutable.Map[N,N]):Any = {
    n match {
      case n:Iterable[_] => n.map { n => mirrorProduct(n, mapping) }
      case n:Option[_] => n.map { n => mirrorProduct(n, mapping) }
      case (a,b) => (mirrorProduct(a, mapping), mirrorProduct(b, mapping))
      case (a,b,c) => (mirrorProduct(a, mapping), mirrorProduct(b, mapping), mirrorProduct(c, mapping))
      case n:ProductNode[_] =>
        mapping.getOrElseUpdate(n, {
          val args = n.productIterator.map { arg => mirrorProduct(arg, mapping) }.toList
          mapping.getOrElseUpdate(n, n.newInstance[N](args :+ n.Nct :+ n.design))
        })
      case n => n
    }
  }

}
