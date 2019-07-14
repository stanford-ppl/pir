package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable
import spade.param._

trait TungstenCtxGen extends TungstenCodegen with TungstenTopGen {

  def numStagesOf(ctx:Context) = ctx.global.get match {
    case g:ArgFringe => 1
    case g:DRAMFringe => 100
    case g:GlobalContainer if spadeParam.isAsic =>
      Math.max(ctx.collectDown[OpNode]().size, 1)
    case g:GlobalContainer if spadeParam.isInf =>
      val cuParam = topMap.right.get.cumap(g).head.params.get.as[CUParam]
      if (cuParam.traceOut[TopParam].scheduled) 1 else cuParam.numStage
    case g:GlobalContainer =>
      val cuParam = topMap.right.get.cumap.usedMap(g).params.get.as[CUParam]
      if (cuParam.traceOut[TopParam].scheduled) 1 else cuParam.numStage
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      val (tp, name) = varOf(n)
      val numStages = numStagesOf(n)
      enterFile(dirName, s"$n.h", false) {
        genCtxCompute {
          visitNode(n)
        }

        emitln("""
using namespace std;
""")  
        emitBlock(s"""class $n: public Context<$numStages>""") {
          emitln(s"public:")
          getBuffer("fields").foreach { _.flushTo(sw) }
          ctxExtVars.foreach { case (tp, field) => 
            emitln(s"$tp* $field;")
          }
          emitln(s"public:")
          val constructorArgs = ctxExtVars.map { case (tp, field) => s"$tp* _$field" }.mkString(",")
          val constructor = s"""explicit $n($constructorArgs):Context<$numStages>("$n")"""
          emitBlock(constructor) {
            ctxExtVars.foreach { case (tp, field) =>
              emitln(s"$field = _$field;")
            }
            getBuffer("inits").foreach { _.flushTo(sw) }
            emitExpectStop(n)
          }
          emitBlock(s"void Compute()") {
            getBuffer("computes").foreach { _.flushTo(sw) }
            getBuffer("computes-mid").foreach { _.flushTo(sw) }
            getBuffer("computes-end").foreach { _.flushTo(sw) }
            emitStopSim(n)
          }
          //emitBlock(s"void Eval()") {
            //emitln(s"Context::Eval();")
            //getBuffer("eval").foreach { _.flushTo(sw) }
          //}
        }
        emit(s""";""")
      }
      genTop {
        emitln(s"""#include "$n.h"""")
      }
      genTopMember(n, ctxExtVars.map { _._2 }.map { _.& }, end=true)
      ctxExtVars.clear

    case n => super.emitNode(n)
  }

  def emitStop(ctx:Context):Boolean = {
    if (!ctx.global.get.isInstanceOf[ArgFringe]) return false
    ctx.collectDown[HostOutController]().headOption.fold(false) { hostOut =>
      val noStreamReadCtxs = !pirTop.collectDown[Context]().exists { case StreamReadContext(_) => true; case _ => false }
      val hasInputStream = ctx.collectDown[LocalOutAccess]().nonEmpty
      hasInputStream || noStreamReadCtxs
    }
  }

  def emitExpectStop(ctx:Context) = {
    if (emitStop(ctx))
      emitln(s"Expect(1);")
  }

  def emitStopSim(ctx:Context) = {
    if (emitStop(ctx))
      emitln(s"Complete(1);")
  }

  val ctxExtVars = mutable.ListBuffer[(String, String)]()

  final protected def genCtxFields(block: => Unit) = enterBuffer("fields"){ incLevel(1); block; decLevel(1) }

  final protected def genCtxInits(block: => Unit) = enterBuffer("inits") { incLevel(2); block; decLevel(2) }

  final protected def genCtxCompute(block: => Unit) = enterBuffer("computes") { incLevel(2); block; decLevel(2) }

  final protected def genCtxComputeMid(block: => Unit) = enterBuffer("computes-mid") { incLevel(2); block; decLevel(2) }

  final protected def genCtxComputeEnd(block: => Unit) = enterBuffer("computes-end") { incLevel(2); block; decLevel(2) }

  //final protected def genCtxEval(block: => Unit) = enterBuffer("eval") { incLevel(2); block; decLevel(2) }

  final protected def addEscapeVar(n:PIRNode):Unit = {
    addEscapeVar(varOf(n))
  }
  final protected def addEscapeVar(v:(String,String)):Unit = {
    if (!ctxExtVars.contains(v)) ctxExtVars += v
  }

  def emitNewMember(tp:String, name:Any) = {
    genCtxFields {
      emitln(s"""$tp* $name = new $tp("$name");""")
    }
    genCtxInits {
      emitln(s"AddChild($name);");
    }
  }
}
