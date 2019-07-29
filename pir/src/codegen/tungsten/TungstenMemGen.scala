package pir
package codegen

import pir.node._
import spade.param._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenMemGen extends TungstenCodegen with TungstenCtxGen {

  override def emitInit = {
    val luts = pirTop.collectChildren[MemoryContainer].flatMap { _.collectChildren[LUT] }
    luts.foreach { lut =>
      val (tp, name) = varOf(lut)
      emitln(s"${lut.qtp} ${name}_init[] = {${lut.inits.get.as[List[_]].mkString(",")}};")
      emitBlock(s"for (auto* buf: $name.buffers)") {
        emitBlock(s"for (auto* bank: buf->banks)") {
          emitln(s"memcpy(bank->data.data(), &${name}_init, sizeof(${name}_init));")
        }
      }
    }
    super.emitInit
  }

  override def emitNode(n:N) = n match {
    case n:LocalOutAccess =>
      val (tp, name) = varOf(n)
      genTopMember(n, Seq(n.qstr))
      n.ctx.get match {
        case DRAMContext(cmd) =>
        case _ =>
          addEscapeVar(n)
          genCtxInits {
            if (!n.nonBlocking)
              emitln(s"""inputs.push_back($name);""")
            if (n.initToken.get) {
              val initVal = n.inits.get
              val banks = n.banks.map { _.head }.getOrElse(n.getVec)
              val init = if (banks > 1) {
                emitln(s"${n.qtp} ${n}_init[${banks}] = {${List.fill(banks)(initVal).mkString(",")}};")
              } else {
                s"(${n.qtp}) $initVal"
              }
              emitln(s"$name->Init(make_token($init));")
            }
            val ctrler = n.collectPeer[Controller]().headOption
            ctrler.foreach { ctrler =>
              emitln(s"${ctrler}->AddInput(${nameOf(n)});")
            }
          }
          genCtxComputeBegin {
            emitIf(s"$name->Valid()") {
              emitVec(n) { i =>
                s"toT<${n.qtp}>($name->Read(), ${i.getOrElse(0)})" 
              }
            }
          }
          genCtxComputeEnd {
            emitIf(n.done.qany) {
              emitln(s"$name->Pop();")
            }
          }
      }

    case WithData(n:BufferWrite, data:StreamCommand) =>

    case WithData(n:BufferWrite, data:FlatBankedRead) =>
      n.out.T.foreach { send =>
        addEscapeVar(send)
        genCtxInits {
          emitln(s"AddSend(${nameOf(send)});");
          emitln(s"""${data.mem.T}->SetSend("$data", ${nameOf(send)});""")
        }
      }

    case n:LocalInAccess =>
      val (tp, name) = varOf(n)
      val ctx = n.ctx.get
      val withPipe = ctx.collectDown[OpNode]().nonEmpty
      if (withPipe) emitNewMember(tp, name)
      val ctrler = n.collectPeer[Controller]().headOption
      n.out.T.foreach { send =>
        if (!send.isDescendentOf(ctx)) addEscapeVar(send)
        genCtxInits {
          if (withPipe) emitln(s"AddSend(${nameOf(send)}, $name);")
          else emitln(s"AddSend(${nameOf(send)});");
          ctrler.foreach { ctrler =>
            //TODO: add val ready pipeline
            //if (!withPipe) emitln(s"${ctrler}->AddOutput(${nameOf(send)});")
            //else emitln(s"${ctrler}->AddOutput($name);")
            emitln(s"${ctrler}->AddOutput(${nameOf(send)});")
          }
        }
      }
      declare(n)
      genCtxComputeEnd {
        val ctrlerEn = ctrler.map { ctrler => s"$ctrler->Enabled()"}.getOrElse(true)
        emitIfElse(s"$ctrlerEn") {
          emitAssign(n) { i => 
            n match {
              case n:BufferWrite => s"${n.en.qidx(i)} ? ${n.data.qidx(i)} : ${n.qidx(i)}" 
              case n:TokenWrite => s"true"
            }
          }
        } {
          val outs = n.out.T.collect { case out:LocalOutAccess if out.ctx.get == ctx => out }
          assertOneOrLess(outs, s"written LocalOutAccess").foreach { out =>
            genCtxComputeBegin {
              emitAssign(n) { i => out.qidx(i) }
            }
          }
        }
        //TODO: coalesce
        emitIf(n.done.qany) {
          if (withPipe) emitln(s"$name->Push(make_token(${n.qref}));")
          else n.out.T.foreach { send =>
            emitln(s"${nameOf(send)}->Push(make_token(${n.qref}));")
          }
        }
      }

    case n:FIFO =>
      genTopMember(n, Seq(n.qstr))

    case n:Memory =>
      val accesses = n.accesses.map { a => s"""make_tuple("$a", ${a.port.get.isEmpty})""" }.mkString(",")
      genTopMember(n, Seq(n.qstr, s"{$accesses}"))

    case n:MemRead if n.mem.T.isFIFO =>
      val mem = n.mem.T
      addEscapeVar(mem)
      val (tp, name) = varOf(mem)
      genCtxInits {
        val ctrler = n.collectPeer[Controller]().headOption
        ctrler.foreach { ctrler =>
          emitln(s"${ctrler}->AddInput(${name});")
        }
      }
      genCtxComputeBegin {
        emitIf(s"$name->Valid()") {
          emitVec(n) { i =>
            s"toT<${n.qtp}>($name->Read(), ${i.getOrElse(0)})" 
          }
        }
      }
      genCtxComputeEnd {
        emitIf(n.done.qany) {
          emitln(s"$name->Pop();")
        }
      }

    case n:MemWrite if n.mem.T.isFIFO =>
      val mem = n.mem.T
      val (tp, name) = varOf(mem)
      addEscapeVar(mem)
      val ctrler = n.collectPeer[Controller]().headOption
      genCtxInits {
        emitln(s"AddSend(${name});");
        ctrler.foreach { ctrler =>
          emitln(s"${ctrler}->AddOutput(${name});")
        }
      }
      declare(n)
      genCtxComputeEnd {
        val ctrlerEn = ctrler.map { ctrler => s"$ctrler->Enabled()"}.getOrElse(true)
        emitIf(s"$ctrlerEn") {
          emitAssign(n) { i => 
            s"${n.en.qidx(i)} ? ${n.data.qidx(i)} : ${n.qidx(i)}" 
          }
        }
        //TODO: coalesce
        emitIf(n.done.qany) {
          emitln(s"${name}->Push(make_token(${n.qref}));")
        }
      }

    case n:FlatBankedRead =>
      addEscapeVar(n.mem.T)
      emitln(s"Active();")
      emitln(s"""${n.mem.T}->SetupRead("$n",make_token(${n.offset.qref}));""")
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }

    case n:FlatBankedWrite =>
      addEscapeVar(n.mem.T)
      emitln(s"Active();")
      emitln(s"""${n.mem.T}->Write("$n", make_token(${n.data.qref}), make_token(${n.offset.qref}));""")
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }

    case n:MemRead =>
      addEscapeVar(n.mem.T)
      emitln(s"""auto $n = ${n.mem.T}->Read("$n");""")
      emitln(s"Active();")
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }

    case n:MemWrite =>
      addEscapeVar(n.mem.T)
      emitln(s"Active();")
      emitln(s"""if (${n.en.qref}) ${n.mem.T}->Write("$n", ${n.data.T});""")
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }

    case n => super.emitNode(n)
  }

  def emitAccess(n:Access, prev:Boolean=false)(func:String => Unit) = {
    val mem = n.mem.T
    if (n.port.nonEmpty) {
      if (!prev)
        emitln(s"""auto* ${n}_buffer = $mem->GetBuffer("$n");""")
      else
        emitln(s"""auto* ${n}_buffer = $mem->GetPrevBuffer("$n");""")
      func(s"${n}_buffer")
    } else {
      emitBlock(s"for (auto* ${n}_buffer: $mem->buffers)") {
        func(s"""${n}_buffer""");
      }
    }
  }

  var fifoDepth = -1
  override def initPass = {
    super.initPass
    fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }
      .getOrElse{ 100 }
    dbg(s"fifoDepth=$fifoDepth")
  } 

  override def quoteRef(n:Any):String = n match {
    case n@InputField(x:BufferRegRead, "in") if !x.nonBlocking => varOf(x)._2.field("data")
    case n@InputField(x:BufferRegRead, f@("writeEn" | "writeDone")) => varOf(x)._2.field(f)
    case n@InputField(x:LocalOutAccess, "in") => varOf(x)._2
    case n@InputField(_:LocalAccess, "en" | "done") => quoteEn(n.as[Input[PIRNode]], None)
    case n@InputField(_:Access, "en" | "done") => quoteEn(n.as[Input[PIRNode]], None)
    case n => super.quoteRef(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:LocalOutAccess if n.nonBlocking =>
      (s"NBlockBufferReg<Token>", s"fifo_$n")
    case n:BufferRegRead =>
      (s"BufferReg<${n.in.getVec}, $fifoDepth>", s"fifo_$n")
    case n:BufferRead if n.out.getVec != n.in.getVec =>
      (s"RateMatchingTokenFIFO<${n.qtp}, ${fifoDepth*n.in.getVec}, ${n.in.getVec}, ${n.out.getVec}>", s"fifo_$n") 
    case n:BufferRead =>
      (s"FIFO<Token, ${fifoDepth}>", s"fifo_$n")
    case n:TokenRead =>
      (s"FIFO<Token, ${n.getDepth}>", s"fifo_$n")
    case n:LocalInAccess =>
      val data = n match {
        case n:BufferWrite => n.data.T
        case n:TokenWrite => n.done.T
      }
      val pipeDepth = data match {
        case data:FlatBankedRead => 1
        case _ => numStagesOf(n.ctx.get)
      }
      (s"ValPipeline<Token, $pipeDepth>", s"pipe_$n")
    case n:FIFO =>
      (s"FIFO<Token, ${n.depth.get}>", s"$n")
    case n:SRAM =>
      (s"NBufferSRAM<${n.getDepth}, ${n.qtp}, ${n.bankSize}, ${n.nBanks}>", s"$n")
    case n:LUT =>
      (s"NBufferSRAM<${n.getDepth}, ${n.qtp}, ${n.bankSize}, ${n.nBanks}>", s"$n")
    case n:Reg =>
      (s"NBufferReg<${n.getDepth}, ${n.qtp}>", s"$n")
    case n => super.varOf(n)
  }

}
