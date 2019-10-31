package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenLockGen extends TungstenCodegen with TungstenCtxGen with TungstenMemGen with TungstenBlackBoxGen with Memorization {

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && (ctx.descendents.exists { case lock:Lock => true; case splitter:Splitter => true; case _ => false }) => 
      withinBB {
        visitNode(n)
      }

    case n:Splitter =>
      val (tp, name) = varOf(n)
      genTopMember(n, Seq(name.qstr))
      n.addrIn.toStream.zip(n.addrOut).zipWithIndex.foreach{ case ((ain, aout),i) =>
        val ainfifo = ain.T
        genTopMember(
          tp="Broadcast<Token>", 
          name=s"bc_$ainfifo", 
          args=Seq(s"bc_$ainfifo".qstr, nameOf(ainfifo).&, Seq(s"$name.in[$i]".&).qlist), 
          end=true,
          extern=false,
          escape=false
        )
        val aoutfifo = aout.T.as[BufferWrite].gout.get
        genTopMember(
          tp="Broadcast<Token>", 
          name=s"bc_$aoutfifo", 
          args=Seq(s"bc_$aoutfifo".qstr, s"$name.out[$i]".&, Seq(nameOf(aoutfifo).&).qlist), 
          end=true,
          extern=false,
          escape=false
        )
      }

    case n:Lock =>
      val (tp, name) = varOf(n)
      genTopMember(n, Seq(name.qstr))
      val lock = n.lock.T
      genTopMember(
        tp="Broadcast<Token>", 
        name=s"bc_${n}_lock",
        args=Seq(s"bc_${n}_lock".qstr, nameOf(lock).&, Seq(s"$name.GetLockPort(${lock.id})").qlist), 
        end=true,
        extern=false,
        escape=false
      )
      val en = assertOne(n.out.T, s"$n.out").as[BufferWrite].gout.get
      genTopMember(
        tp="Broadcast<Token>",
        name=s"bc_${n}_en",
        args=Seq(s"bc_${n}_en".qstr, s"$name.GetLockPort(${lock.id})", Seq(nameOf(en).&).qlist), 
        end=true,
        extern=false,
        escape=false
      )
      val unlock = n.unlock.T
      genTopMember(
        tp="Broadcast<Token>", 
        name=s"bc_${n}_unlock",
        args=Seq(s"bc_${n}_unlock".qstr, nameOf(n.unlock.T).&, Seq(s"$name.GetUnlockPort(${unlock.id})").qlist), 
        end=true,
        extern=false,
        escape=false
      )

    case n:LockWrite =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"$name = ${nameOf(n.mem.T)}->GetWritePorts();")
        emitln(s"${ctrler}->AddOutput(${name}.first);")
        emitln(s"${ctrler}->AddOutput(${name}.second);")
      }
      emitln(s"$name.first->Push(${nameOf(n.addr.T)}->Read());")
      emitln(s"$name.second->Push(${nameOf(n.data.T)}->Read());")
      genCtxComputeEnd {
        if (!n.ack.isConnected) {
          // If ack is not used, i.e. write is not the last access that need to release lock, than
          // drop the ack to prevent deadlock
          emitIf(s"$name.first->Valid()") {
            emitln(s"$name.first->Pop();")
          }
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
      emitln(s"$name->Push(${nameOf(n.addr.T)}->Read());")

    case WithData(n:BufferWrite, data:LockAccess) => // LockRead.out or LockWrite.ack
      val dataOut = n.data.singleConnected.get match {
        case OutputField(data:LockRead,"out") => nameOf(data)
        case OutputField(data:LockWrite,"ack") => s"${nameOf(data)}.first"
      }
      genCtxMember("Broadcast<Token>", s"bc_$data", Seq(dataOut, n.out.T.map { nameOf(_) }.qlist), end=true)
      genCtxInits {
        n.out.T.foreach { send =>
          addEscapeVar(send)
        }
      }

    case n:LockMem if !n.isDRAM => genTopMember(n, Seq(n.qstr))

    case n:LockRMABlock if !n.isDRAM => 
      genTopMember(n, Seq(n.qstr, "net".&, "statnet".&, "idealnet".&))

    case n:LockRMABlock => 
      val drams = n.accums.map { _.dram.get }
      genTopMember(n, Seq(n.qstr, "net".&, "statnet".&, "idealnet".&, "DRAM".&, drams.qlist))

    case n => super.emitNode(n)
  }

  override def initPass = {
    resetAllCaches
    super.initPass
  }

  def sramParam = memorize("sramParam") { spadeParam.traceIn[SRAMParam].head }
  def wordPerBank = sramParam.sizeInWord / sramParam.bank

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:Splitter => (s"AddressSplitter<${n.addrIn.size},0,${spadeParam.vecWidth}>", s"${n}")
    case n:Lock => (s"SparseLock<$wordPerBank,${spadeParam.vecWidth}>", s"${n}")
    case n:LockRead => (s"${tpOf(n.mem.T)}::SparsePMUPort*", s"${n}_port")
    case n:LockWrite => (s"pair<${tpOf(n.mem.T)}::SparsePMUPort*,${tpOf(n.mem.T)}::SparsePMUPort*>", s"${n}_ports")
    case n:LockMem if !n.isDRAM => (s"SparsePMU<${n.qtp},$wordPerBank,${spadeParam.vecWidth}>", s"$n")

    case n:LockRMABlock => 
      val nlr = n.unlockReadAddrs.values.head.head.connected.size
      val nlw = n.unlockWriteAddrs.values.head.head.connected.size
      val nin = n.lockInputIns.size
      val tp = if (n.isDRAM) "DRAM" else "SRAM"
      //accum type, # accums, outerPar, # banks, # unlock read, # unlock write, #input
      (s"Sparse${tp}RMWBlock<${n.accums.head.tp.qtp},${n.accums.size},${n.par},${spadeParam.vecWidth},$nlr,$nlw,$nin>",s"$n")
    case n => super.varOf(n)
  }

}

