package pir.pass

import pir._
import pir.util._

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._

import scala.language.postfixOps
import scala.collection.mutable
import scala.reflect._

class TestPass(implicit design:PIR) extends Pass {
  def shouldRun = true

  addPass {
    TraversalTest.testGraph
    TraversalTest.testBFS
    TraversalTest.testDFS
    TraversalTest.testChildFirst
    TraversalTest.testSiblingFirst
    TraversalTest.testCFTopo
    TraversalTest.testSFTopo

    MapTest.test
  }
}

trait TestDesign extends Design {
  val configs = Nil
  def handle(e:Exception) = {}
  implicit val self:Design = this
}

abstract class TestNode(implicit design:Design) extends Node[TestNode] { self:Product with TestNode =>
  override type A = TestAtom
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

class TestInput(implicit override val src:TestAtom, design:Design) extends Edge[TestNode]() with Input[TestNode] {
  override type A = TestAtom
  type E = TestOutput
}
class TestOutput(implicit override val src:TestAtom, design:Design) extends Edge[TestNode]() with Output[TestNode] {
  override type A = TestAtom
  type E = TestInput
}
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends TestNode with Atom[TestNode] {
  val out = new TestOutput
  override def connectFields(x:Any)(implicit design:Design):Any = {
    x match {
      case x:TestAtom => (new TestInput).connect(x.out)
      case x => super.connectFields(x) 
    }
  }
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with SubGraph[TestNode]

class TestDotCodegen(val fileName:String)(implicit design:Design) extends IRDotCodegen {
  type N = TestNode
  val dirName = design.outDir

  def visitIn(n:N):List[N] = n.localDeps.toList
  def visitOut(n:N):List[N] = n.localDepeds.toList

  def top = TraversalTest.top

  def quote(n:N) = n.toString

  def runPass = {
    traverseNode(top, ())
  }
}

object TraversalTest extends TestDesign {
  import prism.traversal.Traversal

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
    assert(i.deps == Set(g, h), i.deps)
    assert(i.globalDeps == Set(g))
    assert(i.localDeps == Set(h))
    assert(g.deps == Set(c, e))
    assert(g.globalDeps == Set())
    assert(g.localDeps == Set(g2, e))
    println(g1.deps)
  }

  def testBFS = {
    val traversal = new BFSTraversal with GraphSchedular {
      type N = TestNode
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    println(s"")
    var res = traversal.schedule(e)
    assert(res==List(e, g1), s"res=$res")
    res = traversal.schedule(g3)
    assert(res == List(g3, f, g, e, g2, g1), s"res=$res")
  }

  def testDFS = {
    val traversal = new DFSTraversal with GraphSchedular {
      type N = TestNode
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    var res = traversal.schedule(e)
    assert(res==List(e, g1), s"res=$res")
    res = traversal.schedule(g3)
    assert(res == List(g3, f, g, e, g1, g2), s"res=$res")
  }

  def testChildFirst = {
    val traversal = new ChildFirstTraversal with GraphSchedular {
      type N = TestNode
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.schedule(top)
    //println(s"testChildFirst", res)
  }

  def testSiblingFirst = {
    val traversal = new SiblingFirstTraversal with GraphSchedular {
      type N = TestNode
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        n.parent.foreach { parent =>
          parent.parent.foreach { grandParent =>
            assert(grandParent.children.forall(prev.contains), s"$n, parent=$parent prev=$prev")
          }
        }
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(top)
    //println(s"testSiblingFirst", res)
  }

  def testCFTopo = {
    val traversal = new ChildFirstTopologicalTraversal with GraphSchedular {
      type N = TestNode
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitIn(n:N):List[N] = n.localDeps.toList
      def visitOut(n:N):List[N] = n.localDepeds.toList
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        assert(depFunc(n).forall(isVisited))
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.schedule(top)
  }

  def testSFTopo = {
    val traversal = new SiblingFirstTopologicalTraversal with GraphSchedular {
      type N = TestNode
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitIn(n:N):List[N] = n.localDeps.toList
      def visitOut(n:N):List[N] = n.localDepeds.toList
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited))
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        n.parent.foreach { parent =>
          parent.parent.foreach { grandParent =>
            assert(grandParent.children.forall(prev.contains), s"$n, parent=$parent prev=$prev")
          }
        }
        super.visitNode(n, prev)
      }
    }
    var res = traversal.schedule(top)
  }

}

import prism.collection.immutable._
import pirc.exceptions._
object MapTest {

  def test = {
    testIOneToOne
    testIOneToMany
    testIBiOneToOne
    testIBiOneToMany
    testIBiManyToMany
  }

  def testIOneToOne = {
    var map = OneToOneMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (3 -> "c")
    
    try {
      map = map + (1 -> "d")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    map = map + (4 -> "a")
    assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  def testIOneToMany = {
    var map = OneToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
  }

  def testIBiOneToOne = {
    var map = BiOneToOneMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (3 -> "c")
    
    try {
      map = map + (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  }

  def testIBiOneToMany = {
    var map = BiOneToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    try {
      map = map + (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1))

    map = map ++ (1 -> Set("d", "e"))
    println(map.fmap)
    println(map.bmap)
  }

  def testIBiManyToMany = {
    var map = BiManyToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    map = map + (4 -> "a")
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))
  }
}
