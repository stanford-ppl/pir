
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

abstract class Node[N<:Node[N, P]:ClassTag, P<:SubGraph[N, P]](implicit design:Design) extends IR { self:Product with N =>
  val nt = implicitly[ClassTag[N]]

  override def productName = s"$productPrefix(${productIterator.mkString(",")})" 

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

  def matchLevel(n:Atom[N,P]) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption.asInstanceOf[Option[N]] // why is this necessary
  def deps = ins.flatMap { _.connected.map { _.src } }.toSet
  def localDeps = deps.flatMap(matchLevel)
  def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  def depeds = outs.flatMap { _.connected.map { _.src } }.toSet
  def localDepeds = depeds.flatMap(matchLevel)
  def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }

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

  def visitNode(n:N, prev:T):T = {
    traverse(n, prev :+ n)
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

  def visitNode(n:N, prev:T):T

  def traverse(n:N, zero:T):T = throw new Exception(s"Undefined traverse function")
}

trait DFSTraversal extends GraphTraversal {
  override def traverse(n:N, zero:T):T = {
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

  override def traverse(n:N, zero:T):T = {
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
  def isDepFree(n:N) = depFunc(n).filterNot(isVisited).isEmpty
  def visitFunc(n:N):List[N] = allNodes(n).filter { n => isDepFree(n) }
}

trait HiearchicalTraversal extends Traversal with GraphTraversal {

  def visitChild(n:N):List[N] = n.children
  def visitFunc(n:N):List[N] = Nil 

  def traverseChildren(n:N, prev:T):T = {
    visitChild(n).foldLeft(prev) { 
      case (prev, n) if isVisited(n) => prev 
      case (prev, n) => visitNode(n, prev)
    }
  }

}

trait ChildFirstTraversal extends DFSTraversal with HiearchicalTraversal {
  override def traverse(n:N, zero:T):T = {
    visited += n
    super.traverse(n, traverseChildren(n, zero))
  }
}

trait ChildLastTraversal extends BFSTraversal with HiearchicalTraversal {
  override def traverse(n:N, zero:T):T = {
    visited += n
    traverseChildren(n, super.traverse(n, zero))
  }
}

trait HiearchicalTopologicalTraversal extends TopologicalTraversal with HiearchicalTraversal {
  override def visitChild(n:N) = {
    n match {
      case n:SubGraph[_,_] => 
        super.visitChild(n.asInstanceOf[N]).filter(c => depFunc(c.asInstanceOf[N]).isEmpty).asInstanceOf[List[N]]
      case n => Nil
    }
  }
  override def visitFunc(n:N):List[N] = super[TopologicalTraversal].visitFunc(n)
}

trait ChildFirstTopologicalTraversal extends ChildFirstTraversal with HiearchicalTopologicalTraversal
trait ChildLastTopologicalTraversal extends ChildLastTraversal with HiearchicalTopologicalTraversal

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
      type P = TestSubGraph
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
      type P = TestSubGraph
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
      type P = TestSubGraph
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

  def testHierTopoDFS = {
    val traversal = new ChildFirstTopologicalTraversal with GraphSchedular with Traversal {
      type N = TestNode
      type P = TestSubGraph
      def depFunc(n:N):List[N] = visitIn(n)
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited))
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(top)
    println(s"testHierTopoDFS", res)
  }

  def testHierTopoBFS = {
    val traversal = new ChildLastTopologicalTraversal with GraphSchedular with Traversal {
      type N = TestNode
      type P = TestSubGraph
      def depFunc(n:N):List[N] = visitIn(n)
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited))
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(top)
    println(s"testHierTopoBFS", res)
  }

}

