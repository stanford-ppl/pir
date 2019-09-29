package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenLockGen extends TungstenCodegen with TungstenCtxGen with TungstenMemGen with TungstenBlackBoxGen with Memorization {

  override def emitNode(n:N) = n match {
    //case ctx:Context if ctx.streaming.get && (ctx.descendents.collect { case lock:Lock => true; case splitter:Splitter => true }.nonEmpty) => 
      //withinBB {
        //visitNode(n)
      //}

    case n:Splitter =>
      val (tp, name) = varOf(n)
      emitNewMember(tp, name)

    case n:Lock =>
      val (tp, name) = varOf(n)
      emitNewMember(tp, name)
      //val key = nameOf(n.key.T)
      //val en = nameOf(assertOne(n.out.T, s"$n.out").as[BufferWrite].gout.get)
      //genTopMember(n, Seq(n.qstr, key, en), end=true)

    case n:LockWrite =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"$name = ${nameOf(n.mem.T)}->GetWritePort();")
        emitln(s"${ctrler}->AddOutput(${name}.first);")
        emitln(s"${ctrler}->AddOutput(${name}.second);")
      }
      emitln(s"$name.first->Push(${n.addr.T});")
      emitln(s"$name.second->Push(${n.data.T});")
      genCtxComputeEnd {
        emitIf(s"$name.first->Valid()") {
          emitln(s"$name.first->Pop();") //TODO: not doing anything with the done of write yet
        }
      }

    case n:LockRead =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=Some("NULL"));
      genCtxInits {
        emitln(s"$name = ${nameOf(n.mem.T)}->GetReadPort();")
        emitln(s"${ctrler}->AddOutput(${name});")
      }
      emitln(s"$name.first->Push(${n.addr.T});")

    case WithData(n:BufferWrite, data:LockRead) =>
      val ctrler = getCtrler(n)
      emitNewMember("Broadcast<Token>", s"bc_$data", nameOf(data).&, n.out.T.map { nameOf(_) }.qlist)
      genCtxInits {
        n.out.T.foreach { send =>
          addEscapeVar(send)
        }
      }

    case n => super.emitNode(n)
  }

  override def initPass = {
    resetAllCaches
    super.initPass
  }

  def sramParam = memorize("sramParam") { spadeParam.traceIn[SRAMParam].head }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:Splitter => (s"AddressSplitter<${n.addrIn.size},0,${spadeParam.vecWidth}>", s"${n}")
    case n:Lock => (s"SparseLock<${sramParam.size / sramParam.bank},${spadeParam.vecWidth}>", s"${n}")
    case n:LockRead => (s"SparsePMUPort*", s"${n}_port")
    case n:LockWrite => (s"pair<SparsePMUPort*,SparsePMUPort*>", s"${n}_ports")
    case n => super.varOf(n)
  }

}

