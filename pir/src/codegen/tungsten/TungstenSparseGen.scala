package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenSparseGen extends TungstenCodegen with TungstenCtxGen with TungstenMemGen with TungstenBlackBoxGen with Memorization {

  override def emitNode(n:N) = n match {
    //case ctx:Context if ctx.streaming.get && (ctx.descendents.exists { case lock:Lock => true; case splitter:Splitter => true; case _ => false }) => 
      //withinBB {
        //visitNode(n)
      //}

    case n:SparseWrite =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"$name = ${nameOf(n.mem.T)}->GetWritePorts(${n.id});")
        emitln(s"${ctrler}->AddOutput(${name}.first);") // (addr input, done output) 
        emitln(s"${ctrler}->AddOutput(${name}.second);") // (data input)
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

    case n:SparseRead =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"$name = ${nameOf(n.mem.T)}->GetReadPort(${n.id});")
        emitln(s"${ctrler}->AddOutput(${name});") // (addr input, data output)
      }
      emitln(s"$name->Push(${nameOf(n.addr.T)}->Read());")

    case n@SparseRMW(op, opOrder) =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"""$name = ${nameOf(n.mem.T)}->GetRMWPorts("$op",${n.id});""")
        emitln(s"${ctrler}->AddOutput(${name}.first);") // (addr input, ack)
        emitln(s"${ctrler}->AddOutput(${name}.second);") // (data)
      }
      emitln(s"$name.first->Push(${nameOf(n.addr.T)}->Read());")
      emitln(s"$name.second->Push(${nameOf(n.input.T)}->Read());")

    case WithData(n:BufferWrite, data:SparseAccess) => // SparseRead.out or SparseWrite.ack
      val dataOut = n.data.singleConnected.get match {
        case OutputField(data:SparseRead,"out") => nameOf(data)
        case OutputField(data:SparseWrite,"ack") => s"${nameOf(data)}.first"
        case OutputField(data:SparseRMW,"ack") => s"${nameOf(data)}.first"
      }
      genCtxMember("Broadcast<Token>", s"bc_$data", Seq(dataOut, n.out.T.map { nameOf(_) }.qlist), end=true)
      genCtxInits {
        n.out.T.foreach { send =>
          addEscapeVar(send)
        }
      }

    case n:SparseMem if !n.isDRAM => genTopMember(n, Seq(n.qstr))

    //case n:SparseRMWBlock if !n.isDRAM => 
      //genTopMember(n, Seq(n.qstr, "net".&, "statnet".&, "idealnet".&, "DRAM".&))

    //case n:SparseRMWBlock => 
      //val drams = n.accums.map { _.dram.get }
      //genTopMember(n, Seq(n.qstr, "net".&, "statnet".&, "idealnet".&, "DRAM".&, drams.qlist))

    case n => super.emitNode(n)
  }

  override def initPass = {
    resetAllCaches
    super.initPass
  }

  private def sramParam = memorize("sramParam") { spadeParam.traceIn[PMUParam].head.sramParam }
  private def wordPerBank = {
    sramParam.sizeInWord / sramParam.bank
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:SparseRead => (s"${tpOf(n.mem.T)}::SparsePMUPort*", s"${n}_port")
    case n:SparseWrite => (s"pair<${tpOf(n.mem.T)}::SparsePMUPort*,${tpOf(n.mem.T)}::SparsePMUPort*>", s"${n}_ports")
    case n:SparseRMW => (s"pair<${tpOf(n.mem.T)}::SparsePMUPort*,${tpOf(n.mem.T)}::SparsePMUPort*>", s"${n}_ports")
    case n:SparseMem if !n.isDRAM => (s"SparsePMU<${n.qtp},$wordPerBank,${spadeParam.vecWidth}>", s"$n")
    //case n:SparseRMWBlock => 
      //val nlr = n.unlockReadAddr.values.head.head.connected.size
      //val nlw = n.unlockWriteAddr.values.head.head.connected.size
      //val nin = n.numIns.get
      //val dbk = 1 //TODO: what is DRAM_BANK
      //val tp = if (n.isDRAM) "DRAM" else ""
      ////PMU par, RMW par, # accumTp, # accums, # banks, # unlock read, # unlock write, #input
      //(s"Sparse${tp}RMW<${n.memPar},${n.outerPar},${n.accums.head.tp.qtp},${n.accums.size},${spadeParam.vecWidth},$nlr,$nlw,$nin,$dbk>",s"$n")
    case n => super.varOf(n)
  }

}

