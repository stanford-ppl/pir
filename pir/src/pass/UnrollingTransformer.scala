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


  def foldReduce = PIRConfig.option[Boolean]("folded-redacc")
  def transform(n:N):N = {
    n match {
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, LocalLoad((accum:Reg)::Nil, None))) if !foldReduce =>
        val numReduceStages = log2(getParOf(ctrlOf(n)))
        dbg(s"numReduceStages=$numReduceStages")
        var reduceInput = input
        (0 until numReduceStages).foreach { i =>
          reduceInput = withParentCtrl(compiler.top, ctrlOf(n)) { 
            ReduceOp(op=op, input=reduceInput)
          }
        }
        lowerNode(n) {
          withParentCtrl(compiler.top, ctrlOf(n)) { 
            AccumOp(op=op, input=reduceInput, accum.init)
          }
        }
      case Def(n:ReduceAccumOp, ReduceAccumOp(op, input, LocalLoad((accum:Reg)::Nil, None))) =>
        val wordWidth = designS.param.wordWidth
        val numLowPrecReduceStage = log2(SINGLE_PRECISION /! wordWidth)
        dbg(s"numLowPrecReduceStage=$numLowPrecReduceStage")
        var reduceInput = input
        (0 until numLowPrecReduceStage).foreach { i =>
          reduceInput = withParentCtrl(compiler.top, ctrlOf(n)) { 
            StructReduceOp(op=op, input=reduceInput)
          }
        }
        lowerNode(n) {
          withParentCtrl(compiler.top, ctrlOf(n)) { 
            FoldedReduceAccumOp(op=op, input=reduceInput, accum.init)
          }
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
                  val accumOp = withParentCtrl(compiler.top, ctrlOf(n)) {
                    AccumOp(op=op, input=input, accum.init)
                  }
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

