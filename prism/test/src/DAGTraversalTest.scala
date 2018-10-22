package prism
package test

import prism.graph._

object DAG1 extends TestDesign { self =>
  val a   = TestPNode("a")
  val b   = TestPNode("b")
  val c   = TestPNode("c")
  val d   = TestPNode("d",a,b)
  val e   = TestPNode("e",d)
  val f   = TestPNode("f")
  val g   = TestPNode("g",e, c)
  val h   = TestPNode("h",f)
  val i   = TestPNode("i",g,h)
  val j   = TestPNode("j",i)
  val k   = TestPNode("k")
  val l   = TestPNode("l",k)
  val m   = TestPNode("m",j)
  val n   = TestPNode("n",m, j)
  val g1  = TestPNode("g1").addChild(a,b,d)
  val g2  = TestPNode("g2").addChild(c)
  val g3  = TestPNode("g3").addChild(h,i, j, k, m, l, n)
  val top = TestPNode("top").addChild(e, f, g, g1,g2,g3)
}

class DAGTraversalTest extends UnitTest with Logging {
  import DAG1._

  "DAGGraphTest" should "success" in {
    TestIRPrinter(s"IR.txt", top).run
    TestDotCodegen(s"test.dot", top).run
    assert(i.deps.toSet == Set(g, h), i.deps)
    assert(i.globalDeps.toSet == Set(g))
    assert(i.localDeps.toSet == Set(h))
    assert(g.deps.toSet == Set(c, e))
    assert(g.globalDeps.toSet == Set())
    assert(g.localDeps.toSet == Set(g2, e))
    assert(g1.children.contains(b))
    assert(top.children.contains(g1))
  }

  "DAGTestBFS" should "success" in {
    val traversal = new BFSTraversal with Schedular {
      type N = Node[_]
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    var res = traversal.scheduleNode(e)
    assert(res==List(e, g1))
    res = traversal.scheduleNode(g3)
    assert(res == List(g3, f, g, e, g2, g1))
  }

  "DAGTestDFS" should "success" in {
    val traversal = new DFSTraversal with Schedular {
      type N = Node[_]
      def visitFunc(n:N):List[N] = n.localDeps.toList
    }
    var res = traversal.scheduleNode(e)
    assert(res==List(e, g1))
    res = traversal.scheduleNode(g3)
    assert(res == List(g3, f, g, e, g1, g2))
  }

  "DAGTestChildFirst" should "success" in {
    val traversal = new ChildFirstTraversal with Schedular {
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
    val traversal = new SiblingFirstTraversal with Schedular {
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
    val traversal = new DFSTopDownTopologicalTraversal with Schedular {
      val top = DAG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
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
    val traversal = new BottomUpTopologicalTraversal with Schedular with DFSTraversal {
      val top = DAG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
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
