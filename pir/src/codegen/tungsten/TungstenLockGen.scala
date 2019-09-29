package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenLockGen extends TungstenCodegen with TungstenCtxGen with TungstenBlackBoxGen {

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && (ctx.descendents.collect { case lock:Lock => true; case splitter:Splitter => true }.nonEmpty) => 
      withinBB {
        visitNode(n)
      }

    case n:Splitter =>
      val addrIn = n.addrIn.map { addrIn => nameOf(addrIn.T) }
      val addrOut = n.addrOut.map { addrOut => nameOf(addrOut.T.as[BufferWrite].gout.get) }
      genTopMember(n, Seq(n.qstr, addrIn.qlist, addrOut.qlist), end=true)

    case n:Lock =>
      val key = nameOf(n.key.T)
      val en = nameOf(assertOne(n.out.T, s"$n.out").as[BufferWrite].gout.get)
      genTopMember(n, Seq(n.qstr, key, en), end=true)

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:Splitter => (s"Splitter<>", s"${n}")
    case n:Lock => (s"Lock<>", s"${n}")
    case n => super.varOf(n)
  }

}

