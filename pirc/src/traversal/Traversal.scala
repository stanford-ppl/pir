package prism.traversal

import pirc._
import prism.node._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait Traversal extends GraphTraversal {
  type N<:Node[N]

  /*
   * Visit from buttom up
   * */
  def visitUp(n:N):List[N] = n.parent.toList

  /*
   * Visit subgraph
   * */
  def visitDown(n:N):List[N] = n.children

  /*
   * Visit inputs of a node
   * */
  def visitIn(n:N):List[N] = n.localDeps.toList

  /*
   * Visit outputs of a node 
   * */
  def visitOut(n:N):List[N] = n.localDepeds.toList

  def allNodes(n:N) = n.parent.toList.flatMap { parent => parent.children }
}

trait GraphTraversal {
  type N
  type T

  val visited = mutable.ListBuffer[Any]()

  def isVisited(n:N) = visited.contains(n)

  def reset = {
    visited.clear
  }

  def visitFunc(n:N):List[N]

  def visitNode(n:N, prev:T):T = traverse(n, prev)

  def traverseNode(n:N, prev:T):T = {
    if (isVisited(n)) return prev
    visited += n
    visitNode(n, prev)
  }

  def traverse(n:N, zero:T):T
}

trait GraphSchedular extends GraphTraversal { self =>
  type N
  type T = List[N]

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  final def schedule(n:N) = {
    reset
    traverseNode(n, Nil)
  }
}


trait DFSTraversal extends GraphTraversal {
  override def traverse(n:N, zero:T):T = {
    var prev = zero 
    var nexts = visitFunc(n).filterNot(isVisited)
    // Cannot use fold left because graph might be changing while traversing
    while (nexts.nonEmpty) {
      prev = traverseNode(nexts.head, prev)
      nexts = visitFunc(n).filterNot(isVisited)
    }
    prev
  }

}

trait BFSTraversal extends GraphTraversal {

  val queue = mutable.Queue[N]()

  override def reset = {
    super.reset
    queue.clear
  }

  override def traverse(n:N, zero:T):T = {
    var prev = zero 
    queue ++= visitFunc(n).filterNot(isVisited)
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      prev = traverseNode(next, prev)
    }
    return prev
  }
}

trait TopologicalTraversal extends GraphTraversal {
  def allNodes(n:N):List[N]
  def depFunc(n:N):List[N]
  def isDepFree(n:N) = depFunc(n).filterNot(isVisited).isEmpty
  def visitFunc(n:N):List[N] = visitDepFree(allNodes(n))
  /*
   * Return dependent free nodes in allNodes. 
   * Break cycle by pick the node with fewest dependency
   * */
  def visitDepFree(allNodes:List[N]):List[N] = {
    val unvisited = allNodes.filterNot(isVisited)
    if (unvisited.isEmpty) return Nil
    val depFree = unvisited.filter(isDepFree)
    if (depFree.nonEmpty) return depFree
    List(unvisited.sortBy { n => depFunc(n).filterNot(isVisited).size }.head)
  }
}

trait HiearchicalTraversal extends Traversal with GraphTraversal {

  def visitChild(n:N):List[N] = n.children
  def visitFunc(n:N):List[N] = Nil 

  def traverseChildren(n:N, zero:T):T = {
    var prev = zero
    var childs = visitChild(n).filterNot(isVisited)
    while (childs.nonEmpty) {
      prev = traverseNode(childs.head, prev)
      childs = visitChild(n).filterNot(isVisited)
    }
    prev
  }

}

trait ChildFirstTraversal extends DFSTraversal with HiearchicalTraversal {
  override def traverse(n:N, zero:T):T = {
    super.traverse(n, traverseChildren(n, zero))
  }
  override def traverseChildren(n:N, zero:T):T = {
    val res = super.traverseChildren(n, zero)
    val unvisited = n.children.filterNot(isVisited) 
    assert(unvisited.isEmpty, 
      s"traverseChildren:$n Not all children are visited unvisited=${unvisited}")
    res
  }
}

trait ChildLastTraversal extends BFSTraversal with HiearchicalTraversal {
  override def traverse(n:N, zero:T):T = {
    traverseChildren(n, super.traverse(n, zero))
  }
}

trait HiearchicalTopologicalTraversal extends TopologicalTraversal with ChildFirstTraversal {
  override def visitChild(n:N) = {
    n match {
      case n:SubGraph[_] => visitDepFree(super.visitChild(n.asInstanceOf[N]))
      case n => Nil
    }
  }
  override def visitFunc(n:N):List[N] = super[TopologicalTraversal].visitFunc(n)
}

import scala.collection.JavaConverters._
trait GraphTransformer extends GraphTraversal {
  type N<:Node[N] with Product
  type P<:SubGraph[N] with N
  type A<:Atom[N] with N
  type D <: Design
  type T = Unit
  implicit val nct:ClassTag[N]

  def removeNode(node:N) = {
    node.ios.foreach { io => io.disconnect }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      (parent.children.filterNot { _ == node } :+ parent).foreach(removeUnusedIOs)
    }
  }

  def swapParent(node:N, newParent:N) = {
    node.parent.foreach { parent =>
      parent.removeChild(node)
    }
    node.setParent(newParent.asInstanceOf[node.P])
  }

  /*
   * Given a node that was originally connected to from, swap the connection to node to
   * at the same io port.
    * Assume from and to have the same IO interface
   * */
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

  override def visitNode(n:N, prev:T):T = transform(n)

  def transform(n:N):Unit = super.visitNode(n, ())

  def traverseNode(n:N):Unit = traverseNode(n, ())

  def mirror[T<:N](n:T)(implicit design:D):(T, List[N]) = {
    val mapping = mirrorX(n)
    val newNodes = mapping.values.collect { case n:N => n }.toSet diff mapping.keys.collect { case n:N => n}.toSet
    (mapping(n).asInstanceOf[T], newNodes.toList)
  }

  def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    n match {
      case n if mapping.contains(n) => mapping
      case n:N => 
        val args = n.values //n.productIterator.toList
        val newMapping = args.foldLeft(mapping) { case (mapping, arg) => mirrorX(arg, mapping) }
        if (!newMapping.contains(n)) {
          newMapping + (n -> n.newInstance[T](args.map { a => newMapping(a) }))
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


trait GraphCollector extends Pass {
  type N<:Node[N]

  private def newTraversal[M<:N:ClassTag](vf:N => List[N], logger:Option[Logger]) = new BFSTraversal {
    type T = List[M]
    type N = (GraphCollector.this.N, Int)

    override def isVisited(n:N) = {
      val (node, depth) = n
      visited.contains(node)
    }
    override def visitNode(n:N, prev:T):T = {
      val (node, depth) = n
      visited += node
      node match {
        case node:M if depth > 0 => prev :+ node 
        case _ if depth == 0 => prev 
        case _ => super.visitNode(n, prev)
      }
    }
    override def traverse(n:N, zero:T):T = {
      val (node, depth) = n
      def lambda = super.traverse(n, zero)
      logger.fold(lambda) { logger => logger.emitBlock(s"collect($n, depth=$depth)") { lambda } }
    }
    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
  }
 
  def collectUp[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logger]=None):Iterable[M] = {
    def visitUp(n:N):List[N] = n.parent.toList
    newTraversal(visitUp _, logger).traverse((n, depth), Nil)
  }

  def collectDown[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logger]=None):Iterable[M] = {
    def visitDown(n:N):List[N] = n.children
    newTraversal(visitDown _, logger).traverse((n, depth), Nil)
  }

  def collectIn[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logger]=None):Iterable[M] = {
    def visitIn(n:N):List[N] = n.localDeps.toList
    newTraversal(visitIn _, logger).traverse((n, depth), Nil)
  }

  def collectOut[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logger]=None):Iterable[M] = {
    def visitOut(n:N):List[N] = n.localDepeds.toList
    newTraversal(visitOut _, logger).traverse((n, depth), Nil)
  }

}

