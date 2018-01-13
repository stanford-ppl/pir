
package pirc.node

import pirc._
import pirc.util._

import scala.reflect._
import scala.collection.mutable

@SerialVersionUID(123L)
abstract class IR(implicit design:Design) extends Serializable { 
  val id = design.nextId

  override def equals(that: Any) = that match {
    case n: IR => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  override def toString = s"${this.getClass.getSimpleName}$id"
}

abstract class Node[N<:Node[N, P]:ClassTag, P<:SubGraph[N, P]](implicit design:Design) extends IR { self:Product with N =>
  val nt = implicitly[ClassTag[N]]

  override def className = s"$productPrefix(${productIterator.mkString(",")})" 

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

  def ancestors:List[SubGraph[N,P]] = parent.toList.flatMap { parent => parent :: parent.ancestors }
  def descendents:List[N] = children.flatMap { child => 
    child :: child.descendents
  }

  def ins:List[Input[N,P]]
  def outs:List[Output[N,P]]
  def ios:List[Edge[N,P]] = ins ++ outs

  def matchLevel(n:Atom[N,P]) = (n :: n.ancestors).filter { _.parent == this.parent }.head.asInstanceOf[N] // why is this necessary
  def deps = ins.flatMap { _.connected.map { c => matchLevel(c.src) } }.toSet.toList
  def localDeps = deps.filter { _.parent == this.parent }
  def globalDeps = deps.filter { _.parent != this.parent }
  def depeds = outs.flatMap { _.connected.map { c => matchLevel(c.src) } }.toSet.toList
  def localDepeds = depeds.filter { _.parent == this.parent }
  def globalDepeds = depeds.filter { _.parent != this.parent }

  def connectFields(x:Any)(implicit design:Design):Unit = x match {
    case x:N => connectField(x)
    case Some(x) => connectFields(x)
    case x:Iterable[_] => x.foreach(connectFields)
    case x:Iterator[_] => x.foreach(connectFields)
    case x =>
  }

  def connectField(x:N)(implicit design:Design):Unit

  connectFields(productIterator)
}

trait Atom[N<:Node[N, P], P<:SubGraph[N, P]] extends Node[N, P] { self: Product with N =>
  implicit lazy val atom:this.type = this

  def children:List[N] = Nil

  private lazy val _ins = mutable.ListBuffer[Input[N,P]]()
  private lazy val _outs = mutable.ListBuffer[Output[N,P]]()
  def addEdge(io:Edge[N,P]) = {
    io match {
      case io:Input[N,P] => _ins += io
      case io:Output[N,P] => _outs += io
    }
  }
  def removeEdge(io:Edge[N,P]) = {
    io match {
      case io:Input[N,P] => _ins -= io
      case io:Output[N,P] => _outs -= io
    }
  }
  def ins = _ins.toList
  def outs = _outs.toList
}

trait SubGraph[N<:Node[N, P], P<:SubGraph[N,P]] extends Node[N,P] { self: Product with N with P =>
  // Children
  private lazy val _children = mutable.ListBuffer[N]()
  def children = _children.toList
  def addChild(c:N):Unit = { 
    assert(c != this)
    if (c.isChildOf(this)) return
    _children += c
    c.setParent(this)
  }
  def removeChild(c:N):Unit = {
    if (!isParentOf(c)) return
    _children -= c
    c.unsetParent
  }

  def ins = descendents.flatMap { _.ins.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }
  def outs = descendents.flatMap { _.outs.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }

  def connectField(x:N)(implicit design:Design):Unit = {
    implicit val ev = nt
    x match {
      case x:N => this.addChild(x)
      case x => 
    }
  }

}

abstract class Edge[N<:Node[N, P]:ClassTag, P<:SubGraph[N,P]](val src:Atom[N,P])(implicit design:Design) extends IR {
  src.addEdge(this)

  type E<:Edge[N,P]
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

trait Input[N<:Node[N, P], P<:SubGraph[N,P]] extends Edge[N,P] {
  type E <: Output[N,P]
}

trait Output[N<:Node[N, P], P<:SubGraph[N,P]] extends Edge[N,P] {
  type E <: Input[N,P]
}

trait Traversal extends GraphTraversal {
  type N<:Node[N,P]
  type P<:SubGraph[N,P] with N 

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
  def visitIn(n:N):List[N] = n.localDeps

  /*
   * Visit outputs of a node 
   * */
  def visitOut(n:N):List[N] = n.localDepeds

  def allNodes(n:N) = n.parent.toList.flatMap { parent => parent.children }
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

  def visitNode(n:N, prev:T):T

  def traverse(n:N, zero:T):T
}

trait DFSTraversal extends GraphTraversal {
  def traverse(n:N, zero:T):T = {
    visited += n
    visitFunc(n).filterNot(isVisited).foldLeft(zero) { 
      case (prev, n) if isVisited(n) => prev 
      case (prev, n) => visitNode(n, prev)
    }
  }

}

trait BFSTraversal extends GraphTraversal {

  val queue = mutable.Queue[N]()

  override def reset = {
    super.reset
    queue.clear
  }

  def traverse(n:N, zero:T):T = {
    visited += n
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
  def visitFunc(n:N):List[N] = {
    allNodes(n).filter { n => depFunc(n).filterNot(isVisited).isEmpty }
  }
}

trait BFSTopologicalTraversal extends TopologicalTraversal with BFSTraversal

trait DFSTopologicalTraversal extends TopologicalTraversal with DFSTraversal

trait TestDesign extends Design {
  val configs = Nil
  def handle(e:Exception) = {}
  implicit val self:Design = this
}

abstract class TestNode(implicit design:Design) extends Node[TestNode, TestSubGraph] { self:Product with TestNode =>
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

class TestInput(implicit src:TestAtom, design:Design) extends Edge[TestNode, TestSubGraph](src) with Input[TestNode, TestSubGraph] {
  type E = TestOutput
}
class TestOutput(implicit src:TestAtom, design:Design) extends Edge[TestNode, TestSubGraph](src) with Output[TestNode, TestSubGraph] {
  type E = TestInput
}
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends TestNode with Atom[TestNode, TestSubGraph] {
  val out = new TestOutput
  def connectField(x:TestNode)(implicit design:Design) {
    x match {
      case x:TestAtom =>
        val in = new TestInput
        in.connect(x.out)
      case _ =>
    }
  }
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with SubGraph[TestNode, TestSubGraph]

object TraversalTest extends TestDesign {
  val a = TestAtom().name("a")
  val b = TestAtom().name("b")
  val c = TestAtom().name("c")
  val d = TestAtom(a,b).name("d")
  val e = TestAtom(d).name("e")
  val f = TestAtom().name("f")
  val g = TestAtom(c, e).name("g")
  val h = TestAtom(f).name("h")
  val i = TestAtom(g,h).name("i")
  val g1 = TestSubGraph(a,b,d).name("g1")
  val g2 = TestSubGraph(c).name("g2")
  val g3 = TestSubGraph(h,i).name("g3")
  val top = TestSubGraph(e, f, g, g1,g2,g3).name("top")

  def testBFS = {
    val traversal = new BFSTraversal with Traversal {
      type N = TestNode
      type P = TestSubGraph
      type T = List[N]
      def visitFunc(n:N):List[N] = visitIn(n)
      def visitNode(n:N, prev:T):T = {
        traverse(n, prev :+ n)
      }
    }
    var res = traversal.visitNode(e, Nil)
    println(s"")
    println(s"testBFS", res)
    traversal.reset
    res = traversal.visitNode(g3, Nil)
    println(s"testBFS", res)
    //assert(res == List(e, d, c, a, b))
  }

  def testDFS = {
    val traversal = new DFSTraversal with Traversal {
      type N = TestNode
      type P = TestSubGraph
      type T = List[N]
      def visitFunc(n:N):List[N] = visitIn(n)
      def visitNode(n:N, prev:T):T = {
        traverse(n, prev :+ n)
      }
    }
    var res = traversal.visitNode(e, Nil)
    println(s"testDFS", res)
    traversal.reset
    res = traversal.visitNode(g3, Nil)
    println(s"testDFS", res)
    //assert(res == List(e, d, a, b, c))
  }

  def testTopoBFS = {
    val traversal = new BFSTopologicalTraversal with Traversal {
      type N = TestNode
      type P = TestSubGraph
      type T = List[N]
      def depFunc(n:N):List[N] = visitIn(n)
      def visitNode(n:N, prev:T):T = {
        traverse(n, prev :+ n)
      }
    }
    var res = traversal.visitNode(a, Nil)
    println(s"testTopoBFS", res)
    traversal.reset
    res = traversal.visitNode(g1, Nil)
    println(s"testTopoBFS", res)
    //assert(res == List(a,b,c,d,e))
  }

  def testTopoDFS = {
    val traversal = new DFSTopologicalTraversal with Traversal {
      type N = TestNode
      type P = TestSubGraph
      type T = List[N]
      def depFunc(n:N):List[N] = visitIn(n)
      def visitNode(n:N, prev:T):T = {
        traverse(n, prev :+ n)
      }
    }
    var res = traversal.visitNode(a, Nil)
    println(s"testTopoDFS", res)
    traversal.reset
    res = traversal.visitNode(g1, Nil)
    println(s"testTopoDFS", res)
    //assert(res == List(a,b,c,d,e))
  }

}

