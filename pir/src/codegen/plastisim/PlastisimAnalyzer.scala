package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.util._
import scala.collection.mutable

class PlastisimAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
  val forward = true

  override def visitNode(n:N) = {
    n.to[Context].foreach { n =>
      compCount(n)
    }
  }

  override def finPass = {
    val ctxs = pirTop.collectDown[Context]()
    // Two passes to handle cycle in data flow graph
    ctxs.foreach { n =>
      val count = n.getCount
      if (n.collectDown[HostOutController]().nonEmpty & count.isKnown) {
        assert(count == Finite(1), s"Host out count != 1: $count")
      }
      n.collectDown[FringeStreamRead]().headOption.foreach { streamRead =>
        streamRead.count.v.foreach { v =>
          assert(count == v, s"StreamOut count $count != annotated count $v")
        }
      }
      countByReads(n).foreach { c =>
        c.zip(count).foreach { case (c, count) =>
          assert(c == count, s"${n.reads}.count($c) * scale != $n.count($count)")
        }
      }
    }
    super.finPass
  }

}
