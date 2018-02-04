package prism.traversal

import pirc._
import pirc.util._
import prism.node._
import prism.codegen.Logging

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait Traversal extends GraphTraversal with prism.pass.Pass with GraphUtil {

  override def reset = { reset; resetTraversal }
  override def initPass = { resetTraversal }

}

trait GraphUtil {

  type N <: Node[N]

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
  def visitLocalIn(n:N):List[N] = n.localDeps.toList

  /*
   * Visit outputs of a node 
   * */
  def visitLocalOut(n:N):List[N] = n.localDepeds.toList


  def visitGlobalIn(n:N):List[N] = n.deps.toList
  def visitGlobalOut(n:N):List[N] = n.depeds.toList

  def leastCommonAncesstor(n1:N, n2:N):Option[N] = {
    (n1.ancestors intersect n2.ancestors).headOption
  }

}

trait GraphTraversal extends Memorization {
  type N
  type T

  val visited = mutable.ListBuffer[Any]()

  def isVisited(n:N) = visited.contains(n)

  def resetTraversal = {
    visited.clear
  }

  def visitFunc(n:N):List[N]

  def visitNode(n:N, prev:T):T = {
    assert(isVisited(n), n)
    traverse(n, prev)
  }

  def traverseNode(n:N, prev:T):T = {
    if (isVisited(n)) return prev
    visited += n
    visitNode(n, prev)
  }

  def traverse(n:N, zero:T):T = throw new Exception(s"Shouldn't hit this method")
  def traverse(ns: => List[N], zero:T):T = throw new Exception(s"Shouldn't hit this method")

  def dbgs(s:String) = {
    this match {
      case self:Logging => self.dbg(s)
      case _ =>
    }
  }
}

trait UnitTraversal extends GraphTraversal {
  type T = Unit

  override def visitNode(n:N, prev:T):T = visitNode(n)
  def visitNode(n:N):T = super.visitNode(n, ())

  override def traverseNode(n:N, prev:T):T = traverseNode(n)
  def traverseNode(n:N):T = super.traverseNode(n,())

  override def traverse(n:N, zero:T):T = traverse(n)
  def traverse(n:N):T = super.traverse(n,())

  override def traverse(ns: => List[N], zero:T):T = traverse(ns)
  def traverse(ns: => List[N]):T = super.traverse(ns, ())

}

trait GraphSchedular extends GraphTraversal { self =>
  type N
  type T = List[N]

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = super.visitNode(n, prev:+n)

  final def schedule(n:N) = {
    resetTraversal
    traverseNode(n, Nil)
  }

  final def schedule(ns: => List[N]) = {
    resetTraversal
    traverse(ns, Nil)
  }
}


trait DFSTraversal extends GraphTraversal {
  override def traverse(n:N, zero:T):T = {
    traverse(visitFunc(n), zero)
  }

  override def traverse(ns: => List[N], zero:T):T = {
    var prev = zero 
    var nexts = ns.filterNot(isVisited)
    // Cannot use fold left because graph might be changing while traversing
    while (nexts.nonEmpty) {
      prev = traverseNode(nexts.head, prev)
      nexts = nexts.filterNot(isVisited)
      if (nexts.isEmpty) nexts = ns.filterNot(isVisited)
    }
    prev
  }

}

trait BFSTraversal extends GraphTraversal {

  val queue = mutable.Queue[N]()

  override def resetTraversal = {
    super.resetTraversal
    queue.clear
  }

  override def traverse(n:N, zero:T):T = {
    traverse(visitFunc(n), zero)
  }

  override def traverse(ns: => List[N], zero:T):T = {
    var prev = zero 
    queue ++= ns.filterNot(isVisited)
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      prev = traverseNode(next, prev)
      if (queue.isEmpty) queue ++= ns.filterNot(isVisited)
    }
    return prev
  }
}

trait TopologicalTraversal extends GraphTraversal {
  val forward:Boolean
  def visitIn(n:N):List[N]
  def visitOut(n:N):List[N]
  def depedFunc(n:N):List[N] = if (forward) visitOut(n) else visitIn(n)
  def depFunc(n:N):List[N] = if (forward) visitIn(n) else visitOut(n)
  def isDepFree(n:N) = depFunc(n).filterNot(isVisited).isEmpty

  val frontier = mutable.Set[N]()

  override def resetTraversal = {
    super.resetTraversal
    frontier.clear
  }

  def visitFunc(n:N):List[N] = visitDepFree(n)
  //implicit val nct:ClassTag[N]
  //private val cache = Cache((n:N) => visitDepFree(n))
  //def visitFunc(n:N):List[N] = cache.memorize(n)
  
  /*
   * Return dependent free nodes by checking whether dependent nodes are free
   * */
  def visitDepFree(n:N):List[N] = {
    val unvisited = depedFunc(n).filterNot(isVisited)
    frontier ++= unvisited
    val depFree = unvisited.filter(isDepFree)
    depFree
  }

}

trait HiearchicalTraversal extends GraphTraversal {
  override type N <:Node[N]
  def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[_] => n.children.asInstanceOf[List[N]]
    case n:Atom[_] => Nil
  }
}
trait ChildFirstTraversal extends DFSTraversal with HiearchicalTraversal {
  override def traverse(n:N, zero:T):T = {
    assert(!n.children.exists(isVisited))
    val res = super.traverse(n, zero)
    assert(!n.children.exists(c => !isVisited(c)))
    res
  }
}
trait SiblingFirstTraversal extends BFSTraversal with HiearchicalTraversal

trait ChildFirstTopologicalTraversal extends TopologicalTraversal with ChildFirstTraversal { //TODO fir this
  def visitLocalIn(n:N):List[N]
  def visitLocalOut(n:N):List[N]
  def visitIn(n:N):List[N] = visitLocalIn(n)
  def visitOut(n:N):List[N] = visitLocalOut(n)
  override def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[_] => 
      val unvisited = n.children.asInstanceOf[List[N]].filterNot(isVisited) 
      val depFree = unvisited.filter(isDepFree) 
      val res = if (unvisited.nonEmpty && depFree.isEmpty) {
        val nexts = frontier.filterNot(isVisited).filterNot(_.children.nonEmpty)
        dbgs(s"Loop in Data flow graph. Breaking loop at ${nexts}")
        nexts.toList
      } else depFree
      dbgs(s"visitFunc($n) = $res")
      res
    case _:Atom[_] => visitDepFree(n)
  }
}
trait SiblingFirstTopologicalTraversal extends TopologicalTraversal with SiblingFirstTraversal { self =>
  def visitLocalIn(n:N):List[N]
  def visitLocalOut(n:N):List[N]
  def visitIn(n:N):List[N] = visitLocalIn(n)
  def visitOut(n:N):List[N] = visitLocalOut(n)
  override def visitFunc(n:N):List[N] = n match {
    case n:SubGraph[_] => 
      val children = n.children.asInstanceOf[List[N]]
      var unscheduled = children
      var scheduled = List[N]()
      while (unscheduled.nonEmpty) {
        scheduled ++= scheduleDepFree(unscheduled, scheduled)
        unscheduled = unscheduled.filterNot(scheduled.contains)
      }
      scheduled
    case _:Atom[_] => Nil 
  }
  def scheduleDepFree(nodes:List[N], scheduled:List[N]):List[N] = {
    val unvisited = nodes.filterNot(scheduled.contains) 
    var depFree = unvisited.filter(isDepFree) 
    if (unvisited.nonEmpty && depFree.isEmpty) {
      val next = unvisited.map { n => (depFunc(n).filterNot(scheduled.contains).size,n) }.minBy(_._1)._2
      List(next)
    } else depFree
  }
}

trait BottomUpTopologicalTraversal extends TopologicalTraversal {
  override type N <:Node[N]
  def visitGlobalIn(n:N):List[N]
  def visitGlobalOut(n:N):List[N]
  def visitIn(n:N):List[N] = visitGlobalIn(n)
  def visitOut(n:N):List[N] = visitGlobalOut(n)
  override def depedFunc(n:N):List[N] = n.parent.toList ++ super.depedFunc(n)
  override def depFunc(n:N):List[N] = super.depFunc(n) ++ n.children

  val scope = mutable.ListBuffer[N]()

  override def resetTraversal = {
    super.resetTraversal
    scope.clear
  }

  override def traverseNode(n:N, zero:T) = {
    if (scope.isEmpty) {
      scope ++= (n::n.descendents)
      def ns = {
        dbgs(s"calling ns")
        val depFrees = scope.toList.filterNot(isVisited).filter(isDepFree)
        if (depFrees.isEmpty && scope.exists(n => !isVisited(n))) {
          val nexts = frontier.filterNot(isVisited).filterNot(_.children.nonEmpty)
          dbgs(s"Loop in Data flow graph. Breaking loop at ${nexts}")
          nexts.toList
        } else depFrees
      }
      traverse(ns, zero)
    } else {
      super.traverseNode(n, zero)
    }
  }
}

import scala.collection.JavaConverters._
trait GraphTransformer extends GraphTraversal { self:UnitTraversal =>
  type N<:Node[N] with Product
  type P<:SubGraph[N] with N
  type A<:Atom[N] with N
  type D <: Design
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

  def transform(n:N):Unit = super.visitNode(n,())

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


trait GraphCollector extends GraphUtil {
  type N<:Node[N]

  private def newTraversal[M<:N:ClassTag](vf:N => List[N], logger:Option[Logging]) = new BFSTraversal {
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
        case node:M if depth > 0 & !prev.contains(node) => prev :+ node 
        case node:M if depth > 0 & prev.contains(node) => prev 
        case _ if depth == 0 => prev 
        case _ => super.visitNode(n, prev)
      }
    }
    override def traverse(n:N, zero:T):T = {
      val (node, depth) = n
      def lambda = super.traverse(n, zero)
      logger.fold(lambda) { logger => logger.dbgblk(s"collect($n, depth=$depth)") { lambda } }
    }
    def visitFunc(n:N):List[N] = {
      val (node, depth) = n
      vf(node).map { next => (next, depth-1) }
    }
  }
 
  def collectUp[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logging]=None):List[M] = {
    newTraversal(visitUp _, logger).traverse((n, depth), Nil)
  }

  def collectDown[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logging]=None):List[M] = {
    newTraversal(visitDown _, logger).traverse((n, depth), Nil)
  }

  def collectIn[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logging]=None):List[M] = {
    newTraversal(visitLocalIn _, logger).traverse((n, depth), Nil)
  }

  def collectOut[M<:N:ClassTag](n:N, depth:Int=10, logger:Option[Logging]=None):List[M] = {
    newTraversal(visitLocalOut _, logger).traverse((n, depth), Nil)
  }

}

