package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.util._
import scala.collection.mutable

class PlastisimAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
//class PlastisimAnalyzer(implicit compiler:PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal {
  import compiler.env._
  val forward = true

  override def visitNode(n:N) = {
    n.to[Context].foreach { n =>
      n.getCount
    }
  }

  override def finPass = {
    val ctxs = pirTop.collectDown[Context]()
    ctxs.foreach { n =>
      val count = n.getCount
      if (n.collectDown[HostOutController]().nonEmpty) {
        assert(count == Finite(1), s"Host out count != 1: $count")
      }
      n.collectDown[FringeStreamRead]().headOption.foreach { streamRead =>
        streamRead.count.v.foreach { v =>
          assert(count == v, s"StreamOut count $count != annotated count $v")
        }
      }
      countByReads(n).foreach { c =>
        assert(c == count, s"${n.reads}.count($c) * scale != $n.count($count)")
      }
    }
    super.finPass
  }

}
