package pir
package pass

import pir.node._

class PlastisimCountCheck(implicit compiler: PIR) extends PIRTraversal with SiblingFirstTraversal with UnitTraversal with PlastisimUtil {
  import pirmeta._

  val forward = true

  override def visitNode(n:N) = {
    n match {
      case n:NetworkNode => countCheck(n)
      case n => 
    }
    super.visitNode(n)
  }

  def countCheck(n:NetworkNode) = {
    countsOf(n).foreach { ncnt =>
      inMemsOf(n).foreach { case (mem, reads) =>
        val sin = assertOptionUnify(reads, "scaleOf") { r => scaleOf(r) }
        val mcnt = countsOf.getOrElse(mem, None)
        zipOption(sin, mcnt).foreach { case (sin, mcnt) =>
          assert(mcnt * sin == ncnt, s"$mem.count=$mcnt * sin=$sin != $n.count=$ncnt")
        }
      }
      outMemsOf(n).foreach { case (mem, writes) =>
        val sout = assertOptionUnify(writes, "scaleOf") { r => scaleOf(r) }
        val mcnt = countsOf.getOrElse(mem, None)
        zipOption(sout, mcnt).foreach { case (sout, mcnt) =>
          assert(mcnt * sout == ncnt, s"$mem.count=$mcnt * sout=$sout != $n.count=$ncnt")
        }
      }
    }
  }

}
