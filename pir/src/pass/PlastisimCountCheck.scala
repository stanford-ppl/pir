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
      inlinksOf(n).foreach { case (link, reads) =>
        val sin = assertIdentical(reads.flatMap{ r => itersOf(r) }, "itersOf")
        val lcnt = assertIdentical(link.flatMap { m => countsOf(m) }, "counts")
        zipOption(sin, lcnt).foreach { case (sin, lcnt) =>
          assert(lcnt * sin == ncnt, s"$link.count=$lcnt * sin=$sin != $n.count=$ncnt")
        }
      }
      outlinksOf(n).foreach { case (link, writes) =>
        val sout = assertIdentical(writes.flatMap{ r => itersOf(r) }, "itersOf")
        val lcnt = assertIdentical(link.flatMap { m => countsOf(m) }, "counts")
        zipOption(sout, lcnt).foreach { case (sout, lcnt) =>
          assert(lcnt * sout == ncnt, s"$link.count=$lcnt * sout=$sout != $n.count=$ncnt")
        }
      }
    }
  }

}
