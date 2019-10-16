package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenMergeBufferGen extends TungstenCodegen with TungstenCtxGen with TungstenBlackBoxGen {

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && (ctx.descendents.exists { case lock:MergeBuffer => true; case _ => false}) => 
      withinBB(global=false) {
        visitNode(n)
      }

    case n:MergeBuffer =>
      val ins = n.inputs.map { in => nameOf(in.T.as[BufferRead]).& }.qlist
      val bounds = n.bounds.map { bound => nameOf(bound.T.as[BufferRead]).& }.qlist
      val init = nameOf(n.init.T.as[BufferRead]).&
      val output = nameOf(assertOne(n.out.T, s"$n.out").as[BufferWrite].gout.get).&
      val outBound = assertOneOrLess(n.outBound.T, s"$n.outBound").fold("NULL") { outBound => nameOf(outBound.as[BufferWrite].gout.get).& }
      val outInit = assertOneOrLess(n.outInit.T, s"$n.outInit").fold("NULL") { outInit => nameOf(outInit.as[BufferWrite].gout.get).& }
      genTopMember(n, Seq(n.qstr, ins, bounds, init, output, outBound, outInit), end=true)

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:MergeBuffer => (s"MergePCU<${n.qtp},${n.par}>", s"${n}")
    case n => super.varOf(n)
  }

}


