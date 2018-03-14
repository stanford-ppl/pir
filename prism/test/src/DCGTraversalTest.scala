package prism.test

import prism._
import prism.util._
import prism.node._
import prism.traversal._

object DCG1 extends TestDesign {
  val a = TestAtom().name("a")
  val b = TestAtom(a).name("b")
  val c = TestAtom(b).name("c")
  val d = TestAtom(b).name("d")
  val e = TestAtom(c).name("e")
  val f = TestAtom(c).name("f")
  val g1 = TestSubGraph(a,b,d).name("g1")
  val g2 = TestSubGraph(c,e).name("g2")
  val top = TestSubGraph(g1, g2, f).name("top")
  d.newIn.connect(f.out)
  b.newIn.connect(d.out)
}

class DCGTraversalTest extends UnitTest with GraphCollector {
  import DCG1._

  type N = TestNode

  "DCGGraphTest" should "success" in {
    //new TestDotCodegen(top, s"test.dot").newRun(0).run
  }

  "DCGTestDFSTDTopo" should "success" in {
    val traversal = new DFSTopDownTopologicalTraversal with GraphSchedular {
      type N = TestNode
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitLocalIn(n:N):List[N] = n.localDeps.toList
      def visitLocalOut(n:N):List[N] = n.localDepeds.toList
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.schedule(top)
    //println(s"CFTopo", res)
  }

  "DCGTestBUTopo" should "success" in {
    val traversal = new BottomUpTopologicalTraversal with GraphSchedular with DFSTraversal {
      type N = TestNode
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      def visitGlobalIn(n:N):List[N] = n.deps.toList
      def visitGlobalOut(n:N):List[N] = n.depeds.toList
      override def visitNode(n:N, prev:T):T = {
        super.visitNode(n, prev)
      }
      override def schedule(n:N) = {
        resetTraversal
        traverseScope(n, Nil)
      }
    }
    var res = traversal.schedule(top)
    //println("BUTopo", res)
    //assert((top::top.descendents).forall(traversal.isVisited))
  }
}
