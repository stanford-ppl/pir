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
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, LocalLoad((accum:Reg)::Nil, None))) =>
        val numReduceStages = (Math.log(getParOf(ctrlOf(n))) / Math.log(2)).toInt
        dbg(s"numReduceStages=$numReduceStages")
        var reduceInput = input
        (0 until numReduceStages).foreach { i =>
          reduceInput = ReduceOp(op=op, input=reduceInput)
          ctrlOf(reduceInput) = ctrlOf(n)
        }
        lowerNode(n) {
          val accumOp = AccumOp(op=op, input=reduceInput, accum.init)
          ctrlOf(accumOp) = ctrlOf(n)
          accumOp
        }
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, accum)) =>
        err(s"ReduceAccumOp $n on non register type!")
        n
      case Def(n:OpDef, OpDef(op:Op, inputs:List[Def])) =>
        def visitIn(n:N):List[N] = n match {
          case n:Counter => Nil
          case n:CounterIter => Nil
          case n:Memory => Nil
          case n => visitGlobalIn(n)
        }
        def visitOut(n:N):List[N] = n match {
          case n:Counter => Nil
          case n:CounterIter => Nil
          case n:Memory => Nil
          case n => visitGlobalOut(n)
        }
        val inMems = n.collect[Memory](visitFunc=visitIn).filter { mem => isAccum(mem) }.toSet
        val outMems = n.collect[Memory](visitFunc=visitOut).filter { mem => isAccum(mem) }.toSet
        val accums = inMems.intersect(outMems)
        assertOneOrLess(accums, s"accums").fold[Def]{
          n
        } { accum =>
          dbgblk(s"Lower accumOp ${qdef(n)} acum=$accum") {
            accum match {
              case accum:Reg => 
                lowerNode[Def](n) {
                  val input = assertOne(inputs.filterNot { input =>
                    input.collectInTillMem[Memory]().contains(accum)
                  }, s"accumInput")
                  val accumOp = AccumOp(op=op, input=input, accum.init)
                  ctrlOf(accumOp) = ctrlOf(n)
                  accumOp
                }
              case accum if isRemoteMem(accum) => n
              case accum => err(s"unexpected accumulator type $accum"); n
            }
          }
        }
      case n => n
    }
  }

}

