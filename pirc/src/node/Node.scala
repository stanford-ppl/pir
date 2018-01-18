
package pirc.node

import pirc._
import pirc.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

@SerialVersionUID(123L)
abstract class IR(implicit design:Design) extends Serializable { 
  val id = design.nextId

  override def equals(that: Any) = that match {
    case n: IR => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName
  def productName = this.getClass.getSimpleName

  override def toString = s"${this.getClass.getSimpleName}$id"
}

abstract class Node[N<:Node[N]:ClassTag](implicit design:Design) extends IR { self:Product with N =>

  type P <: SubGraph[N]
  val nct = implicitly[ClassTag[N]]

  override def productName = s"$productPrefix(${productIterator.mkString(",")})" 

  lazy val arity = self.productArity
  def values = self.productIterator.toList
  lazy val fields = self.getClass.getDeclaredFields.filterNot(_.isSynthetic).slice(0, arity).map(_.getName).toList

  // Parent
  var _parent:Option[P] = None
  def parent:Option[P] = _parent
  def setParent(p:P):this.type =  {
    assert(p != this)
    _parent.foreach { parent =>
      assert(p == parent, s"Resetting parent of $this from $parent to $p")
    }
    if (p.isParentOf(this)) return this
    _parent = Some(p)
    p.addChild(this)
    this
  }
  def unsetParent = {
    parent.foreach { p =>
      _parent = None
      p.removeChild(this)
    }
  }
  def isParentOf(m:N) = m.parent == Some(this)

  // Children
  def children:List[N]
  def isChildOf(p:N) = p.children.contains(this)

  def ancestors:List[P] = parent.toList.flatMap { parent => parent :: parent.ancestors.asInstanceOf[List[P]] }
  def descendents:List[N] = children.flatMap { child => 
    child :: child.descendents
  }

  def ins:List[Input[N]]
  def outs:List[Output[N]]
  def ios:List[Edge[N]] = ins ++ outs

  def matchLevel(n:N) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption.asInstanceOf[Option[N]] // why is this necessary
  def deps:Set[N] = ins.flatMap { _.connected.map { _.src.asInstanceOf[N] } }.toSet
  def localDeps = deps.flatMap(matchLevel)
  def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  def depeds:Set[N] = outs.flatMap { _.connected.map { _.src.asInstanceOf[N] } }.toSet
  def localDepeds = depeds.flatMap(matchLevel)
  def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }

  def connectFields(x:Any)(implicit design:Design):Unit = x match {
    case (x:N, field:String) => connectField(x, field)
    case Some(x) => connectFields(x)
    case x:Iterable[_] => x.foreach(connectFields)
    case x:Iterator[_] => x.foreach(connectFields)
    case x =>
  }

  def connectField(x:N, field:String)(implicit design:Design):Unit

  connectFields(productIterator.toList.zip(fields))
}

trait Atom[N<:Node[N]] extends Node[N] { self: Product with N =>
  implicit lazy val atom:this.type = this

  def children:List[N] = Nil

  private lazy val _ins = mutable.ListBuffer[Input[N]]()
  private lazy val _outs = mutable.ListBuffer[Output[N]]()

  def addEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins += io
      case io:Output[N] => _outs += io
    }
  }
  def removeEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins -= io
      case io:Output[N] => _outs -= io
    }
  }
  def ins = _ins.toList
  def outs = _outs.toList
}

trait SubGraph[N<:Node[N]] extends Node[N] { self: Product with N with SubGraph[N] =>
  // Children
  private lazy val _children = mutable.ListBuffer[N]()
  def children = _children.toList
  def addChild(c:N):Unit = { 
    assert(c != this)
    if (c.isChildOf(this)) return
    _children += c
    c.setParent(this.asInstanceOf[c.P])
  }
  def removeChild(c:N):Unit = {
    if (!isParentOf(c)) return
    _children -= c
    c.unsetParent
  }

  def ins = descendents.flatMap { _.ins.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }
  def outs = descendents.flatMap { _.outs.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }

  def connectField(x:N, field:String)(implicit design:Design):Unit = {
    implicit val ev = nct
    x match {
      case x:N => this.addChild(x)
      case x => 
    }
  }

}
abstract class Edge[N<:Node[N]:ClassTag](val src:Atom[N])(implicit design:Design) extends IR {
  src.addEdge(this)

  type E<:Edge[N]
  protected val _connected = mutable.ListBuffer[E]()
  def connected:List[E] = _connected.toList
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(p:E) = connected.contains(p)
  def connect(p:E):this.type = {
    if (isConnectedTo(p)) return this
    _connected += p 
    p.connect(this.asInstanceOf[p.E])
    this
  }

  def disconnectFrom(io:E):Unit = {
    _connected -= io
    if (io.isConnectedTo(this.asInstanceOf[io.E])) io.disconnectFrom(this.asInstanceOf[io.E])
  }
  def disconnect = connected.foreach(disconnectFrom)
}

trait Input[N<:Node[N]] extends Edge[N] {
  type E <: Output[N]
}

trait Output[N<:Node[N]] extends Edge[N] {
  type E <: Input[N]
}


trait Traversal extends GraphTraversal {
  type N<:Node[N]

  /*
   * Visit from buttom up
   * */
  def visitUp(n:N):List[N] = n.parent.toList.asInstanceOf[List[N]]

  /*
   * Visit subgraph
   * */
  def visitDown(n:N):List[N] = n.children

  /*
   * Visit inputs of a node
   * */
  def visitIn(n:N):List[N] = {
    n.localDeps.toList
  }

  /*
   * Visit outputs of a node 
   * */
  def visitOut(n:N):List[N] = n.localDepeds.toList

  def allNodes(n:N) = n.parent.toList.flatMap { parent => parent.children }
}

trait GraphSchedular extends GraphTraversal { self =>
  type N
  type T = List[N]

  def visitFunc(n:N):List[N]

  override def visitNode(n:N, prev:T):T = {
    traverse(n, super.visitNode(n, prev) :+ n)
  }

  def schedule(n:N) = {
    reset
    visitNode(n, Nil)
  }
}

trait GraphTraversal {
  type N
  type T

  val visited = mutable.ListBuffer[N]()

  def isVisited(n:N) = visited.contains(n)

  def reset = {
    visited.clear
  }

  def visitFunc(n:N):List[N]

  def visitNode(n:N, prev:T):T = {
    visited += n
    prev
  }

  def traverse(n:N, zero:T):T = throw new Exception(s"Undefined traverse function")
}

trait DFSTraversal extends GraphTraversal {
  override def traverse(n:N, zero:T):T = {
    var nexts = visitFunc(n).filterNot(isVisited)
    var prev = zero
    // Cannot use fold left because graph might be changing while traversing
    while (nexts.nonEmpty) {
      prev = visitNode(nexts.head, prev)
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
    queue ++= visitFunc(n).filterNot(isVisited)
    while (queue.nonEmpty) {
      val next = queue.dequeue()
      if (!isVisited(next)) return visitNode(next, zero)
    }
    return zero
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
      prev = visitNode(childs.head, prev)
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

trait TestDesign extends Design {
  val configs = Nil
  def handle(e:Exception) = {}
  implicit val self:Design = this
}

abstract class TestNode(implicit design:Design) extends Node[TestNode] { self:Product with TestNode =>
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

class TestInput(implicit src:TestAtom, design:Design) extends Edge[TestNode](src) with Input[TestNode] {
  type E = TestOutput
}
class TestOutput(implicit src:TestAtom, design:Design) extends Edge[TestNode](src) with Output[TestNode] {
  type E = TestInput
}
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends TestNode with Atom[TestNode] {
  val out = new TestOutput
  def connectField(x:TestNode, field:String)(implicit design:Design) {
    x match {
      case x:TestAtom =>
        val in = new TestInput
        in.connect(x.out)
      case _ =>
    }
  }
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with SubGraph[TestNode]

object TraversalTest extends TestDesign {
  val a = TestAtom().name("a")
  val b = TestAtom().name("b")
  val c = TestAtom().name("c")
  val d = TestAtom(a,b).name("d")
  val e = TestAtom(d).name("e")
  val f = TestAtom().name("f")
  val g = TestAtom(e, c).name("g")
  val h = TestAtom(f).name("h")
  val i = TestAtom(g,h).name("i")
  val j = TestAtom(i).name("j")
  val k = TestAtom().name("k")
  val l = TestAtom(k).name("l")
  val m = TestAtom(j).name("m")
  val n = TestAtom(m, j).name("n")
  val g1 = TestSubGraph(a,b,d).name("g1")
  val g2 = TestSubGraph(c).name("g2")
  val g3 = TestSubGraph(h,i, j, k, m, l, n).name("g3")
  val top = TestSubGraph(e, f, g, g1,g2,g3).name("top")

  def testGraph = {
    assert(i.deps == Set(g, h))
    assert(i.globalDeps == Set(g))
    assert(i.localDeps == Set(h))
    assert(g.deps == Set(c, e))
    assert(g.globalDeps == Set())
    assert(g.localDeps == Set(g2, e))
    println(g1.deps)
  }

  def testBFS = {
    val traversal = new BFSTraversal with GraphSchedular with Traversal {
      type N = TestNode
      def visitFunc(n:N):List[N] = {
        visitIn(n)
      }
    }
    println(s"")
    var res = traversal.schedule(e)
    //println(s"testBFS", res)
    assert(res==List(e, g1))
    res = traversal.schedule(g3)
    //println(s"testBFS", res)
    assert(res == List(g3, f, g, e, g2, g1))
  }

  def testDFS = {
    val traversal = new DFSTraversal with GraphSchedular with Traversal {
      type N = TestNode
      def visitFunc(n:N):List[N] = visitIn(n)
    }
    var res = traversal.schedule(e)
    //println(s"testDFS", res)
    assert(res==List(e, g1))
    res = traversal.schedule(g3)
    //println(s"testDFS", res)
    assert(res == List(g3, f, g, e, g1, g2))
  }

  def testTopo = {
    val traversal = new TopologicalTraversal with BFSTraversal with GraphSchedular with Traversal {
      type N = TestNode
      def depFunc(n:N):List[N] = visitIn(n)
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited))
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(a)
    println(s"testTopo", res)
    res = traversal.schedule(g1)
    println(s"testTopo", res)
  }

  def testHierTopo = {
    val traversal = new HiearchicalTopologicalTraversal with GraphSchedular with Traversal {
      type N = TestNode
      def depFunc(n:N):List[N] = visitIn(n)
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited))
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(top)
    println(s"testHierTopoDFS", res)
  }

}

import scala.collection.JavaConverters._
trait GraphTransformer extends GraphTraversal {
  type N<:Node[N]
  type P<:SubGraph[N] with N
  type T = Unit
  type D <: Design

  def visitNode(n:N):T = visitNode(n, ())

  def removeNode(node:N) = {
    node.ios.foreach { io => io.disconnect }
    node.parent.foreach { parent =>
      parent.removeChild(node)
      node.unsetParent
      (parent.children.filterNot { _ == node } :+ parent.asInstanceOf[N]).foreach(removeUnusedIOs)
    }
  }

  def swapParent(node:N, newParent:N) = {
    node.parent.foreach { parent =>
      parent.removeChild(node)
    }
    node.setParent(newParent.asInstanceOf[node.P])
  }

  def removeUnusedIOs(node:N) = {
    node.ios.foreach { io => if (!io.isConnected) io.src.removeEdge(io) }
  }

  def transform(n:N):Unit = traverse(n, ())

  override def visitNode(n:N, prev:T):T = {
    super.visitNode(n, prev)
    transform(n)
  }

  def mirror[T<:N](n:T)(implicit ct:ClassTag[N], design:D):(T, List[N]) = {
    val values = n.values :+ design
    //TODO: n.getClass.getConstructor(values.map{_.getClass}:_*).newInstance(values.map{
    // Some how this compiles but gives runtime error for not able to find the constructor when values contain Int type since
    // field.getClass returns java.lang.Integer type but getConstructor expects typeOf[Int]
    val constructor = n.getClass.getConstructors()(0) 
    val (args, prevs) = values.map { // Only works with a single constructor
      case n:N => mirror(n)
      case n => (n,Nil)
    }.unzip
    val m = constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    (m, prevs.flatten :+ m)
  }
}
