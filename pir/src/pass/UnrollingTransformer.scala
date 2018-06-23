package pir
package pass

import pir.node._

class UnrollingTransformer(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    visited += node
    super.visitNode(node)
  }

  def transform(n:N):N = {
    n match {
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, accum)) =>
        val numReduceStages = (Math.log(getParOf(ctrlOf(n))) / Math.log(2)).toInt
        dbg(s"numReduceStages=$numReduceStages")
        var reduceInput = input
        (0 until numReduceStages).foreach { i =>
          reduceInput = ReduceOp(op=op, input=reduceInput)
          ctrlOf(reduceInput) = ctrlOf(n)
        }
        lowerNode(n) {
          val accum = AccumOp(op=op, input=reduceInput)
          ctrlOf(accum) = ctrlOf(n)
          accum
        }
      case n => n
    }
  }

}

