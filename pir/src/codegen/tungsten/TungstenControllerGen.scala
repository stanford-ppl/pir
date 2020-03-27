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
    case OutputField(ctrler:Controller, "stepped") => s"$ctrler->Stepped()"
    case OutputField(ctrler:LoopController, "firstIter") => s"$ctrler->FirstIter()"
    case OutputField(ctrler:Controller, "laneValid") => s"laneValid"
    case n => super.quoteRef(n)
  }

  override def emitNode(n:N) = n match {
    case n:ControlBlock => 
      val ctrler = getCtrler(n)
      emitIf(s"${ctrler}->Enabled()") {
        if (n.getCtrl.isLeaf) {
          val uen = ctrler.ancestorTreeCtrlers.flatMap { _.uen.connected }.distinct
          emitln(s"bool uen = ${uen.map { _.qref }.reduceOption { (a,b) => s"$a & $b" }.getOrElse("true")};")
          emitln("Active();")
          visitNode(n)
        } else {
          visitNode(n)
        }

        ctrler.to[HostOutController].foreach { ctrler =>
          emitln(s"Complete(1);")
          genCtxInits {
            emitln(s"Expect(1);")
          }
        }
      }
    case n:SplitController =>
      genCtxMember(n)
      genCtxInits {
        emitln(s"AddCtrler($n);");
      }
      if (n.en.isConnected) {
        emitln(s"$n->SetEn(${n.en.qref}); // ${n.getCtrl}")
      }

      val splitOn = n.splitOn.T
      emitln(s"$n->SetMask($splitOn, ${splitOn.getVec});")

      visitNode(n)

      emitLaneValid(n)

    case n:LoopController =>
      genCtxMember(n)
      genCtxInits {
        emitln(s"AddCtrler($n);");
        n.cchain.T.foreach { ctr =>
          emitln(s"$n->AddCounter(${ctr});")
        }
      }
      if (n.en.isConnected) {
        emitln(s"$n->SetEn(${n.en.qref}); // ${n.getCtrl}")
      }

      genCtxComputeMid {
        n.stopWhen.T.foreach { stop =>
          emitln(s"$n->SetStop($stop);")
        }
      }

      visitNode(n)

      emitLaneValid(n)

    case n:Controller =>
      genCtxMember(n)
      genCtxInits {
        emitln(s"AddCtrler($n);");
      }
      if (n.en.isConnected) {
        emitln(s"$n->SetEn(${n.en.qref}); // ${n.getCtrl}")
      }

      visitNode(n)

      emitLaneValid(n)

    case n:StridedCounter =>
      genCtxMember(n)
      emitln(s"$n->setMin(${n.min.T});")
      emitln(s"$n->setStep(${n.step.T});")
      emitln(s"$n->setMax(${n.max.T});")
      emitln(s"$n->Eval(); // ${n.getCtrl}")

    case n:ForeverCounter =>
      genCtxMember(n)

    case n:ScanCounter =>
      genCtxMember(n)
      emitln(s"$n->setCount(${n.cnt.T});")
      emitln(s"$n->setIndex(${n.index.T});")
      emitln(s"$n->Eval(); // ${n.getCtrl}")

    case n@CounterIter(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Iters()[$i]" })

    case n@CounterValid(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Valids()[$i]" })

    case n => super.emitNode(n)
  }

  def emitLaneValid(n:Controller) = {
    if (n.getCtrl.isLeaf) {
      n match {
        case n:LoopController => 
          emitln(s"$n->EvalLaneValids();")
          emitVec(n.laneValid) { i =>
            s"$n->LaneValids()[${i.getOrElse("0")}]"
          }
        case n:SplitController => 
          emitln(s"$n->EvalLaneValids();")
          emitVec(n.laneValid) { i =>
            s"$n->LaneValids()[${i.getOrElse("0")}]"
          }
        case n => emitVec(n.laneValid) { i => "true" }
      }
    }
  }

  override def varOf(n:PIRNode):(String, String) = n match {
    case n:SplitController => (s"SplitController",s"$n")
    case n:LoopController => (s"LoopController",s"$n")
    case n:TopController => (s"StepController",s"$n")
    case n:Controller => (s"UnitController",s"$n")
    case n:ForeverCounter => (s"ForeverCounter<1>",s"$n")
    case n:StridedCounter => (s"Counter<${n.par}>",s"$n")
    case n:ScanCounter => (s"ScanCounter",s"$n")
    case n => super.varOf(n)
  }
}
