package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenControllerGen extends TungstenCodegen with TungstenCtxGen {
  override def quoteRef(n:Any):String = n match {
    case OutputField(ctrler:Controller, "done") => s"$ctrler->Done()"
    case OutputField(ctrler:Controller, "childDone") => s"$ctrler->ChildDone()"
    case OutputField(ctrler:LoopController, "firstIter") => s"$ctrler->FirstIter()"
    case n => super.quoteRef(n)
  }

  override def emitNode(n:N) = n match {
    case n:ControlBlock => 
      val ctrler = getCtrler(n)
      emitIf(s"${ctrler}->Enabled()") {
        if (n.getCtrl.isLeaf) {
          emitln("Active();")
          // If last level controller is loop controller, generate lane valids
          ctrler.to[LoopController].foreach { ctrler =>
            val laneValids = ctrler.cchain.T.foldLeft(List[String]()) { 
              case (Nil, ctr) => List.tabulate(ctr.par) { i => s"$ctr->Valids()[$i]" }
              case (prev, ctr) => 
                prev.flatMap { valid => 
                  List.tabulate(ctr.par) { i => s"$valid & $ctr->Valids()[$i]" }
                }
            }
            emitln(s"bool laneValids[] = {${laneValids.mkString(",")}};")
          }
        }
        super.visitNode(n)

        ctrler.to[HostOutController].foreach { ctrler =>
          emitln(s"Complete(1);")
          genCtxInits {
            emitln(s"Expect(1);")
          }
        }
      }

    case n:Controller =>
      val tp = n match {
        case n:TopController => "StepController"
        case n:LoopController => "LoopController"
        case n => "UnitController"
      }
      emitNewMember(tp, n)
      genCtxInits {
        emitln(s"AddCtrler($n);");
        n.to[LoopController].foreach { n =>
          n.cchain.foreach { ctr =>
            emitln(s"$n->AddCounter(${ctr});")
          }
        }
      }
      if (n.en.isConnected) {
        emitln(s"$n->SetEn(${n.en.qref}); // ${n.getCtrl}")
      }

      n.to[LoopController].foreach { n =>
        n.stopWhen.T.foreach { stop =>
          genCtxComputeMid {
            emitln(s"$n->SetStop($stop);")
          }
        }
      }

      super.visitNode(n)

    case n:Counter if n.isForever =>
      emitNewMember(s"ForeverCounter<${n.par}>", n)

    case n:Counter if !n.isForever =>
      emitNewMember(s"Counter<${n.par}>", n)
      emitln(s"$n->setMin(${n.min.T.get});")
      emitln(s"$n->setStep(${n.step.T.get});")
      emitln(s"$n->setMax(${n.max.T.get});")
      emitln(s"$n->Eval(); // ${n.getCtrl}")

    case n@CounterIter(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Iters()[$i]" })

    case n@CounterValid(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Valids()[$i]" })

    case n => super.emitNode(n)
  }
}
