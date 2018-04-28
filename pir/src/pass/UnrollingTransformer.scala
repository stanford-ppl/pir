package pir.pass

import pir.node._

class UnrollingTransformer(implicit compiler:PIR) extends PIRTransformer with DFSTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    visited += node
    super.visitNode(node)
  }

  def transform(n:N):N = {
    dbgs(s"transform ${qdef(n)}")
    n match {
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, accum)) =>
        val numReduceStages = (Math.log(getParOf(n)) / Math.log(2)).toInt
        dbg(s"numReduceStages=$numReduceStages")
        var reduceInput = input
        (0 until numReduceStages).foreach { i =>
          reduceInput = ReduceOp(op=op, input=reduceInput)
          ctrlOf(reduceInput) = ctrlOf(n)
        }
        val accum = AccumOp(op=op, input=reduceInput)
        ctrlOf(accum) = ctrlOf(n)
        swapNode(n, accum)
        accum
      case n => n
    }
  }

}

