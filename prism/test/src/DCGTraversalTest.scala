package prism
package test

import prism.graph._

object DCG1 extends TestDesign {
  val a   = TestPNode("a")
  val b   = TestPNode("b",a)
  val c   = TestPNode("c",b)
  val d   = TestPNode("d",b)
  val e   = TestPNode("e",c)
  val f   = TestPNode("f",c)
  val g1  = TestPNode("g1").addChild(a,b,d)
  val g2  = TestPNode("g2").addChild(c,e)
  val top = TestPNode("top").addChild(g1,g2,f)
  d.newIn.connect(f.out)
  b.newIn.connect(d.out)
  println(top.Nct)
}

class DCGTraversalTest extends UnitTest {
  import DCG1._

  "DCGGraphTest" should "success" in {
    new prism.codegen.BasicIRPrinter(testOut, s"IR.txt", top).run
    new prism.codegen.BasicIRDotGen(testOut, s"test.dot", top).run
  }

  "DCGTestDFSTDTopo" should "success" in {
    val traversal = new DFSTopDownTopologicalTraversal with Schedular {
      val top = DCG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      override def visitNode(n:N, prev:T):T = {
        assert(!n.children.exists(prev.contains), s"n=$n prev=$prev")
        val res = super.visitNode(n, prev)
        assert(n.children.forall(res.contains), s"n=$n res=$res")
        res
      }
    }
    var res = traversal.scheduleScope(top)
    //println(s"CFTopo", res)
  }

  "DCGTestBUTopo" should "success" in {
    val traversal = new DFSBottomUpTopologicalTraversal with Schedular {
      val top = DCG1.top
      implicit val nct:ClassTag[N] = classTag[N]
      val forward = true
      override def visitNode(n:N, prev:T):T = {
        super.visitNode(n, prev)
      }
    }
    var res = traversal.scheduleScope(top)
    //println("BUTopo", res)
    //assert((top::top.descendents).forall(traversal.isVisited))
  }
}
