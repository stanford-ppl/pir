package prism
package graph

import scala.collection.mutable

trait Transformer extends Logging {

  def removeUnusedIOs(node:N) = {
    node.localEdges.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def removeNode(node:N) = {
    dbg(s"Remove $node")
    node.metadata.values.foreach{_.reset}
    node.localEdges.foreach { io => 
      val connected = io.connected.map(_.src)
      io.disconnect
    }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      assert(!parent.children.contains(node), s"$parent still contains $node after removeNode")
    }
  }

  def swapParent(node:N, newParent:N):Unit = {
    dbg(s"swapParent($node, $newParent)")
    if (newParent.isParentOf(node)) return
    node.parent.foreach { parent => parent.removeChild(node) }
    node.setParent(newParent)
  }

  def swapDep(node:N, from:N, to:N) = {
    dbg(s"swapDep(node=$node, from=$from, to=$to)")
    dbg(s"fromOuts=${from.localOuts} toOuts=${to.localOuts}")
    assert(from.localOuts.size == to.localOuts.size, 
      s"$from and $to have different number of localOuts from=${from.localOuts} to ${to.localOuts}")
    (from.localOuts, to.localOuts).zipped.foreach { case (from, to) =>
      if (from.neighbors.contains(node)) swapInput(node, from, to)
    }
  }

  /*
   * Find node's connection to from and swap it to to
   * */
  def swapInput(node:N, from:Output, to:Output):Unit = {
    dbg(s"swapInput(node=${node}, from=$from, to=${to.src}.$to)")
    val connected = node.localIns.filter { _.isConnectedTo(from) }
    dbg(s"connected=${node.localIns}")
    assert (connected.nonEmpty, s"$node.localIns is not connected to ${from.src}.$from")
    connected.foreach { _.swapConnection(from, to) }
  }

  /*
   * Find node's connection to edges of from and swap them to to
   * */
  def swapInput(node:N, from:N, to:Output):Unit = {
    dbg(s"swapInput(node=${node}, from=$from, to=${to.src}.$to)")
    val connected = node.localIns.flatMap { nodeEdge => 
      from.localOuts.filter { fromEdge =>
        nodeEdge.isConnectedTo(fromEdge)
      }.map { fromEdge => (nodeEdge, fromEdge) }
    }
    assert (connected.nonEmpty, s"$node is not connected to $from")
    connected.foreach { case (nodeEdge, fromEdge) => nodeEdge.swapConnection(fromEdge, to) }
  }

  /*
   * Find connection between n1 and n2, and insert new connection such that
   * n1.old connection -> e1 and n2.old conection -> e2
   * */
  def insertConnection(n1:N, n2:N, e1:Edge, e2:Edge) = {
    dbg(s"insertConnection($n1, $n2, ${e1.src}.${e1}, ${e2.src}.${e2})")
    val connected = n1.localEdges.flatMap { n1e => 
      n2.localEdges.filter { n2e =>
        n1e.isConnectedTo(n2e)
      }.map { n2e => (n1e, n2e) }
    }
    assert (connected.nonEmpty, s"$n1 is not connected to $n2")
    connected.foreach { case (n1e, n2e) =>
      n1e.disconnectFrom(n2e)
      if (!n1e.isConnectedTo(e1)) n1e.connect(e1)
      if (!n2e.isConnectedTo(e2)) n2e.connect(e2)
    }
  }

  def areConnected(node1:N, node2:N) = {
    node1.localEdges.exists { io1 => node2.localEdges.exists { io2 => io1.isConnectedTo(io2) } }
  }

  def assertConnected(node1:N, node2:N) = {
    assert(areConnected(node1, node2), s"$node1 and $node2 are not connected")
  }

  def disconnect(a:N, b:N) = {
    dbg(s"disconnect($a, $b)")
    val pairs = a.localEdges.flatMap { aio => 
      aio.connected.filter{ _.src == b }.map { bio => (aio, bio) }
    }
    assert(pairs.nonEmpty, s"$a is not connected to $b, a.connected=${a.deps++a.depeds}")
    pairs.foreach { case (aio,bio) => aio.disconnectFrom(bio) }
  }

  def disconnect(a:Edge, b:N) = {
    dbg(s"disconnect($a, $b)")
    val bios = a.connected.filter { _.src == b }
    assert(bios.nonEmpty, s"$a is not connected to $b, a.connected=${a.neighbors}")
    bios.foreach { bio => a.disconnectFrom(bio) }
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
          val cidx = cs.localEdges.indexOf(c)
          val mcs = mapping.getOrElse(cs, cs)
          val mc = mcs.localEdges(cidx)
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
      val m = dbgblk(s"mirrorN($n)") { n.newInstance[N](margs) }
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

  implicit class ProductNodeOp[N](x:ProductNode[N]) {
    def map[T:ClassTag](func:Any => Any) = {
      x match {
        case x:T =>
          val args = x.productIterator.toList
          val targs = args.map { arg => func(arg) }
          val change = args.zip(targs).exists { case (a,t) => a != t }
          if (change) {
            removeNode(x)
            val nn = x.newInstance[T](targs) 
            dbg(s"Create $nn")
            nn
          } else x
        case x => x
      }
    }

    def mapFields[T<:Product:TypeTag:ClassTag](func:(String, Any) => Any) = {
      x match {
        case x:T =>
          val args = x.productIterator.toList
          val fields = x.fields
          val targs = fields.map { case (name, arg) => func(name, arg) }
          val change = args.zip(targs).exists { case (a,t) => a != t }
          if (change) {
            removeNode(x)
            val nn = x.newInstance[T](targs) 
            dbg(s"Create $nn")
            nn
          } else x
        case x => x
      }
    }
  }

  def transform[T](x:T):T = {
    (x match {
      case x:Iterable[_] => x.map { transform }
      case x:Option[_] => x.map { transform }
      case (a,b) => (transform(a), transform(b))
      case (a,b,c) => (transform(a), transform(b), transform(c))
      case (a,b,c,d) => (transform(a), transform(b), transform(c), transform(d))
      case x:ProductNode[_] => x.map[ProductNode[_]](transform)
      case x => x
    }).asInstanceOf[T]
  }

}
