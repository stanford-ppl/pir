package prism
package graph

import scala.collection.mutable

trait Transformer extends Logging {

  def removeUnusedIOs[N<:Node[N]](node:Node[N]) = {
    node.localEdges.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def removeNodes[N<:Node[N]](nodes:Iterable[Node[N]]):Unit = {
    if (nodes.isEmpty) return
    dbg(s"Remove ${nodes.mkString(",")}")
    nodes.foreach { node =>
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
  }

  def swapParent[N<:Node[N]](node:Node[N], newParent:Node[N]):Unit = {
    dbg(s"swapParent($node, $newParent)")
    if (newParent.isParentOf(node)) return
    node.parent.foreach { parent => parent.removeChild(node) }
    node.setParent(newParent)
  }

  def swapDep[N<:Node[N]](node:Node[N], from:Node[N], to:Node[N]) = {
    dbg(s"swapDep(node=$node, from=$from, to=$to)")
    dbg(s"fromOuts=${from.localOuts} toOuts=${to.localOuts}")
    assert(from.localOuts.size == to.localOuts.size, 
      s"$from and $to have different number of localOuts from=${from.localOuts} to ${to.localOuts}")
    (from.localOuts, to.localOuts).zipped.foreach { case (from, to) =>
      if (from.neighbors.contains(node)) swapInput(node, from, to)
    }
  }

  /*
   * Change input connection from from to to
   * */
  def swapConnection[N<:Node[N]](input:Input[N], from:Output[N], to:Output[N]):Unit = {
    if (from == to) return
    dbg(s"swapInput(input=${input.src}.${input}, from=${from.src}.$from, to=${to.src}.$to)")
    assert(input.isConnectedTo(from), s"${input.src}.${input} is not connected to ${from.src}.${from}")
    input.swapOutputConnection(from, to)
  }

  /*
   * Find node's connection to from and swap it to to
   * */
  def swapInput[N<:Node[N]](node:Node[N], from:Output[N], to:Output[N]):Unit = {
    dbg(s"swapInput(node=${node}, from=$from, to=${to.src}.$to)")
    val connected = node.localIns.filter { _.isConnectedTo(from) }
    dbg(s"connected=${node.localIns}")
    assert (connected.nonEmpty, s"$node.localIns is not connected to ${from.src}.$from")
    connected.foreach { in => swapConnection(in, from, to) }
  }

  /*
   * Find node's connection to edges of from and swap them to to
   * */
  def swapInput[N<:Node[N]](node:Node[N], from:Node[N], to:Output[N]):Unit = {
    dbg(s"swapInput(node=${node}, from=$from, to=${to.src}.$to)")
    val connected = node.localIns.flatMap { nodeEdge => 
      from.localOuts.filter { fromEdge =>
        nodeEdge.isConnectedTo(fromEdge)
      }.map { fromEdge => (nodeEdge, fromEdge) }
    }
    assert (connected.nonEmpty, s"$node is not connected to $from")
    connected.foreach { case (nodeEdge, fromEdge) => swapConnection(nodeEdge, fromEdge, to) }
  }

  /*
   * Find usage of from and change it to to
   * */
  def swapOutput[N<:Node[N]](from:Output[N], to:Output[N]) = {
    dbg(s"swapOutput ${from.src}.$from to ${to.src}.$to")
    from.connected.distinct.foreach { in =>
      swapConnection(in, from, to)
    }
  }

  /*
   * Find connection between n1 and n2, and insert new connection such that
   * n1.old connection -> e1 and n2.old conection -> e2
   * */
  def insertConnection[N<:Node[N],A<:Edge[N,A,B],B<:Edge[N,B,A]](n1:Node[N], n2:Node[N], e1:Edge[N,A,B], e2:Edge[N,B,A]) = {
    dbg(s"insertConnection($n1, $n2, ${e1.src}.${e1}, ${e2.src}.${e2})")
    val connected = n1.localEdges.flatMap { n1ex => 
      n2.localEdges.flatMap { n2ex =>
        val n1e = n1ex.as[B]
        val n2e = n2ex.as[A]
        if (n1e.isConnectedTo(n2e)) Some((n1e, n2e)) else None
      }
    }
    assert (connected.nonEmpty, s"$n1 is not connected to $n2")
    connected.foreach { case (n1e, n2e) =>
      n1e.disconnectFrom(n2e)
      if (!n1e.isConnectedTo(e1)) n1e.connect(e1)
      if (!n2e.isConnectedTo(e2)) n2e.connect(e2)
    }
  }

  def areConnected[N<:Node[N]](node1:Node[N], node2:Node[N]) = {
    node1.localEdges.exists { case io1:Edge[N,a,b] => 
      node2.localEdges.exists { io2 => io1.as[Edge[N,a,b]].isConnectedTo(io2.as[b]) }
    }
  }

  def assertConnected[N<:Node[N]](node1:Node[N], node2:Node[N]) = {
    assert(areConnected(node1, node2), s"$node1 and $node2 are not connected")
  }

  def disconnect[N<:Node[N]](a:Node[N], b:Node[N]) = {
    dbg(s"disconnect($a, $b)")
    val pairs = a.localEdges.flatMap[(EN[N],EN[N]), ListBuffer[(EN[N],EN[N])]] { aio => 
      aio.connected.filter{ _.src == b }.map { bio => (aio, bio) }
    }
    assert(pairs.nonEmpty, s"$a is not connected to $b, a.connected=${a.deps++a.depeds}")
    pairs.foreach { case (aio,bio) => aio.disconnectFrom(bio.as) }
  }

  def disconnect[N<:Node[N],A<:Edge[N,A,B],B<:Edge[N,B,A]](a:Edge[N,A,B], b:Node[N]) = {
    dbg(s"disconnect($a, $b)")
    val bios = a.connected.filter { _.src == b }
    assert(bios.nonEmpty, s"$a is not connected to $b, a.connected=${a.neighbors}")
    bios.foreach { bio => a.disconnectFrom(bio) }
  }

  def mirrorAll(nodes:Iterable[ND], mapping:mutable.Map[ND,ND]=mutable.Map.empty):mutable.Map[ND,ND] = {
    type F = FN forSome { type FN <:FieldNode[FN] }
    if (nodes.nonEmpty) {
      nodes.head match {
        case node:FieldNode[n] => mirrorField(nodes.as, mapping)
        case node:ProductNode[n] => mirrorProduct(nodes, mapping)
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
  def mirrorField[N<:Node[N]](nodes:Iterable[FieldNode[N]], mapping:mutable.Map[ND,ND]) = {
    // First pass mirror all nodes and put in a map
    nodes.foreach { n => mirror[N](n, mapping) }

    // Second pass build hiearchy and connection
    mapping.foreach { case (n,m) =>
      n.parent.foreach { p => 
        mapping.get(p).foreach { mp =>
          m.unsetParent
          m.setParent(mp.as)
        }
      }
      n.localIns.zipWithIndex.foreach { case (io, idx) =>
        val mio = m.localIns(idx)
        io.connected.foreach { c => 
          val cs = c.src
          val cidx = cs.localEdges.indexOf(c)
          val mcs = mapping.getOrElse(cs, cs)
          val mc = mcs.localEdges(cidx)
          mio.connect(mc.as)
        }
      }
    }
  }

  def mirrorProduct(nodes:Iterable[ND], mapping:mutable.Map[ND,ND]) = {
    nodes.foreach { n => mirror(n, mapping) }
  }

  final def mirror[T](n:Any, mapping:mutable.Map[ND,ND]=mutable.Map.empty):T = {
    (unpack(n) {
      case n:Node[n] => 
        mapping.getOrElseUpdate(n, mirrorN[n](n.as[n]))
      case n => n
    }).asInstanceOf[T]
  }

  def mirrorN[N<:Node[N]](n:Node[N], mapping:mutable.Map[ND,ND]=mutable.Map.empty):N = {
    val margs = newInstanceArgs(n, mapping)
    mapping.getOrElseUpdate(n, {
      val m = n.newInstance[N](margs)
      dbg(s"mirror $n -> $m")
      m.mirrorMetas(n)
      m
    }).as[N]
  }

  def newInstanceArgs(n:ND, mapping:mutable.Map[ND,ND]):Seq[Any] = n match {
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

  implicit class ProductOp[T<:ND with Product](x:T) {
    def mapFields(func:(T, Any) => Any):T = {
      val args = x.productIterator.toList
      dbg(s"$x, args=$args")
      val targs = args.map { arg => func(x, arg) }
      val change = args.zip(targs).exists { case (a,t) => a != t }
      if (change) {
        removeNodes(Seq(x.as[x.TN]))
        val nn = x.newInstance[T](targs) 
        dbg(s"Create $nn")
        nn
      } else x
    }

    def mapFieldWithName(func:(String, T, Any) => Any)(implicit tg:TypeTag[T]):T = {
      val args = x.productIterator.toList
      val fields = x.fields
      val targs = fields.map { case (name, arg) => func(name, x, arg) }
      val change = args.zip(targs).exists { case (a,t) => a != t }
      if (change) {
        removeNodes(Seq(x.as[x.TN]))
        val nn = x.newInstance[T](targs) 
        dbg(s"Create $nn")
        nn
      } else x
    }
  }

  def transform[T](x:T):T = {
    (x match {
      case x:Iterable[_] => x.map { transform }
      case x:Option[_] => x.map { transform }
      case (a,b) => (transform(a), transform(b))
      case (a,b,c) => (transform(a), transform(b), transform(c))
      case (a,b,c,d) => (transform(a), transform(b), transform(c), transform(d))
      case x:Node[n] with Product => new ProductOp[n with Product](x.as).mapFields { case (x, arg) => transform(arg) }
      case x => x
    }).asInstanceOf[T]
  }

}
