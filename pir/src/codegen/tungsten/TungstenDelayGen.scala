package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenDelayGen extends TungstenCodegen with TungstenCtxGen with TungstenBlackBoxGen {

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && (ctx.descendents.exists { case lock:ScratchpadDelay => true; case _ => false}) => 
      withinBB {
        visitNode(n)
      }

    case n:ScratchpadDelay =>
      val in = nameOf(n.in.T.as[BufferRead]).&
      val out = nameOf(assertOne(n.out.T, s"$n.out").as[BufferWrite].out.singleConnected.get.src).&
      genTopMember(n, Seq(n.qstr), end=false)
      genTopMember(
        tp="Broadcast<Token>", 
        name=s"bc_${n}_in", 
        args=Seq(s"bc_${n}_in".qstr, in, Seq(n.&).qlist), 
        end=true,
        extern=false,
        escape=false
      )
      genTopMember(
        tp="Broadcast<Token>", 
        name=s"bc_${n}_out", 
        args=Seq(s"bc_${n}_ou".qstr, n.&, Seq(out).qlist), 
        end=true,
        extern=false,
        escape=false
      )

    case n:Delay =>
      emitVec(n) { i => n.in.qidx(i) }

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:ScratchpadDelay => (s"FIFO<Token,${n.cycle}>", s"${n}")
    case n => super.varOf(n)
  }

}



