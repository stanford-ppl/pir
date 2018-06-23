package prism
package test

import prism.node._
import prism.traversal._

object DAG1 extends TestDesign { self =>
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
}

class DAGTraversalTest extends UnitTest with Logging {
  import DAG1._

  type N = TestNode

  "DAGGraphTest" should "success" in {
    assert(i.deps == Set(g, h), i.deps)
    assert(i.globalDeps == Set(g))
    assert(i.localDeps == Set(h))
    assert(g.deps == Set(c, e))
    assert(g.globalDeps == Set())
    assert(g.localDeps == Set(g2, e))
    assert(g1.children.contains(b))
    assert(top.children.contains(g1))
    new TestDotCodegen(s"test.dot").run
  }

  "DAGTestBFS" should "success" in {
    val traversal = new BFSTraversal with GraphSchedular {
      type N = TestNode
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    var res = traversal.scheduleNode(e)
    assert(res==List(e, g1))
    res = traversal.scheduleNode(g3)
    assert(res == List(g3, f, g, e, g2, g1))
  }

  "DAGTestDFS" should "success" in {
    val traversal = new DFSTraversal with GraphSchedular {
      type N = TestNode
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    var res = traversal.scheduleNode(e)
    assert(res==List(e, g1))
    res = traversal.scheduleNode(g3)
    assert(res == List(g3, f, g, e, g1, g2))
  }

  "DAGTestChildFirst" should "success" in {
    val traversal = new ChildFirstTraversal with ScopeSchedular {
      type N = TestNode
      val top = DAG1.top
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.scheduleScope(top)
    //println(s"testChildFirst", res)
  }

  "DAGTestSiblingFirst" should "success" in {
    val traversal = new SiblingFirstTraversal with ScopeSchedular {
      type N = TestNode
      val top = DAG1.top
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
    var res = traversal.scheduleScope(top)
    //println(s"testSiblingFirst", res)
  }

  "DAGTestDFSTDTopo" should "success" in {
    val traversal = new DFSTopDownTopologicalTraversal with ScopeSchedular {
      type N = TestNode
      val top = DAG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitLocalIn(n:N):List[N] = n.localDeps.toList
      def visitLocalOut(n:N):List[N] = n.localDepeds.toList
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        assert(depFunc(n).forall(isVisited))
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.scheduleScope(top)
    //println(s"CFTopo", res)
  }

  "DAGTestBUTopo" should "success" in {
    val traversal = new BottomUpTopologicalTraversal with ScopeSchedular with DFSTraversal {
      type N = TestNode
      val top = DAG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitGlobalIn(n:N):List[N] = n.deps.toList
      def visitGlobalOut(n:N):List[N] = n.depeds.toList
      override def visitNode(n:N, prev:T):T = {
        assert(depFunc(n).forall(isVisited), s"depFunc()")
        super.visitNode(n, prev)
      }
    }
    var res = traversal.scheduleScope(top)
    assert((top::top.descendents).forall(traversal.isVisited))
    //println("BUTopo", res)
  }

}
