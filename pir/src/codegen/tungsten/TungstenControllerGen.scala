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

    case n:Counter =>
      genCtxMember(n)
      if (!n.isForever) {
        emitln(s"$n->setMin(${n.min.T.get});")
        emitln(s"$n->setStep(${n.step.T.get});")
        emitln(s"$n->setMax(${n.max.T.get});")
        emitln(s"$n->Eval(); // ${n.getCtrl}")
      }

    case n@CounterIter(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Iters()[$i]" })

    case n@CounterValid(is) =>
      val ctr = n.counter.T
      emitVec(n, is.map { i => s"$ctr->Valids()[$i]" })

    case n => super.emitNode(n)
  }

  def getLoopLaneValids(n:LoopController) = {
    n.cchain.T.foldLeft(List[String]()) { 
      case (Nil, ctr) => List.tabulate(ctr.par) { i => s"$ctr->Valids()[$i]" }
      case (prev, ctr) => 
        prev.flatMap { valid => (0 until ctr.par).map { i => s"$valid & $ctr->Valids()[$i]" } }
    }
  }

  def emitLaneValid(n:Controller) = {
    if (n.getCtrl.isLeaf) {
      n match {
        case n:LoopController => 
          emitVec(n.laneValid, getLoopLaneValids(n))
        case n:SplitController => 
          n.parent.get match {
            case p:LoopController => emitVec(n.laneValid, getLoopLaneValids(p))
            case p => emitVec(n.laneValid) { i => "true" }
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
    case n:Counter if n.isForever => (s"ForeverCounter<${n.par}>",s"$n")
    case n:Counter => (s"Counter<${n.par}>",s"$n")
    case n => super.varOf(n)
  }
}
