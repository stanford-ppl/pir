package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

class TungstenPIRGen(implicit design:PIR) extends TungstenCodegen 
  with TungstenTopGen 
  with TungstenCtxGen 
  with TungstenDRAMGen 
  with TungstenOpGen
  with TungstenMemGen

trait TungstenCodegen extends PIRTraversal with DFSTopDownTopologicalTraversal with CppCodegen {
  override def dirName = buildPath(super.dirName, s"../tungsten") 
  val forward = true
  val fileName = "Top.h"

  override def quote(n:Any) = n match {
    case n:Iterable[_] => 
      s"{${n.map(quote).mkString(",")}}"
    case n => super.quote(n)
  }

  override def visitIn(n:N) = n match {
    case n:BufferRead => super.visitIn(n).filterNot{_.isInstanceOf[BufferWrite]}
    case n => super.visitIn(n)
  }

  override def visitOut(n:N) = n match {
    case n:BufferWrite => super.visitOut(n).filterNot{_.isInstanceOf[BufferRead]}
    case n => super.visitOut(n)
  }

  def quoteRef(n:PIRNode) = {
    if (n.getVec > 1) s"${n}[i]" else s"${n}"
  }

  def emitEn(en:Input with Field[_]):Unit = {
    val src = en.src
    val ens = en.neighbors
    val enName = s"${src}_${en.name}"
    emitln(s"bool $enName = ${ens.map { _.toString}.foldLeft("true"){ case (prev,b) => s"$prev & $b" }};")
    en.src match {
      case n:BufferWrite if n.getCtrl.schedule != "Streaming" =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case n:InAccess if n.getCtrl.schedule != "Streaming" =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case n:RegAccumOp =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case _ =>
    }
  }

  def emitVec(n:PIRNode)(rhs:Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"float $n[${vec}] = {};")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"$n[i] = ${rhs}")
      }
    } else {
      emitln(s"float ${n} = ${rhs}")
    }
  }

  val numStages = 6

  val ctxArgs = mutable.Set[(String, String)]()
  val dutArgs = mutable.ListBuffer[String]()

  final protected def genTop(block: => Unit) = enterFile(outputPath, false)(block)

  final protected def genTopEnd(block: => Unit) = enterBuffer("top_end")(block)

  final protected def genFields(block: => Unit) = enterBuffer("fields"){ incLevel(1); block; decLevel(1) }

  final protected def genInits(block: => Unit) = enterBuffer("inits") { incLevel(2); block; decLevel(2) }

  final protected def genCompute(block: => Unit) = enterBuffer("computes") { incLevel(2); block; decLevel(2) }

  final protected def genPush(block: => Unit) = enterBuffer("push") { incLevel(2); block; decLevel(2) }

  final protected def addEscapeVar(tp:String, name:String):Unit = {
    ctxArgs += ((tp, name))
  }

  final protected def addEscapeVar(n:PIRNode):Unit = {
    addEscapeVar(tpOf(n), s"$n")
  }

  def tpOf(n:PIRNode):String = throw PIRException(s"Don't know tpOf($n)")
}
