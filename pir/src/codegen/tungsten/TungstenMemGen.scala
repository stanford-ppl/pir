package pir
package codegen

import pir.node._
import spade.param._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenMemGen extends TungstenCtxGen {

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
      addEscapeVar(n)
      genCtxInits {
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
        val ctrler = n.done.T.map { 
          case ctrler:Controller => ctrler
          case ctrler => getCtrler(n)
        }
        ctrler.foreach { ctrler =>
          emitln(s"${ctrler}->AddInput(${nameOf(n)});")
        }
      }
      emitEn(n.en)
      emitln(s"$name->SetReadEn(${n.en.qref});")
      emitIf(s"$name->Valid()") {
        emitVec(n) { i =>
          s"toT<${n.qtp}>($name->Read(), ${i.getOrElse(0)})" 
        }
      }
      genCtxComputeEnd {
        val cond = if (n.isFIFO) List(n.done.qref, n.en.qany) else List(n.done.qref)
        emitIf(cond.mkString(" & ")) {
          emitln(s"$name->Pop();")
        }
      }

    case WithData(n:BufferWrite, data:FlatBankedRead) =>
      val ctrler = getCtrler(n)
      n.out.T.foreach { send =>
        addEscapeVar(send)
        genCtxInits {
          emitln(s"${ctrler}->AddOutput(${nameOf(send)});")
          emitln(s"""${data.mem.T}->SetSend(${data.id}, ${nameOf(send)});""")
        }
      }

    case n:LocalInAccess =>
      val (tp, name) = varOf(n)
      val ctx = n.ctx.get
      val withPipe = ctx.collectDown[OpNode]().nonEmpty
      if (withPipe) {
        val data = n match {
          case n:BufferWrite => n.data.T
          case n:TokenWrite => n.done.T
        }
        val pipeDepth = numStagesOf(n.ctx.get)
        genCtxMember(n, pipeDepth)
      }
      val ctrler = getCtrler(n)
      n.out.T.foreach { send =>
        if (!send.isDescendentOf(ctx)) addEscapeVar(send)
        genCtxInits {
          val ctrler = n.done.T.map { 
            case ctrler:Controller => ctrler
            case ctrler => getCtrler(n)
          }
          ctrler.foreach { ctrler =>
            if (withPipe) emitln(s"${ctrler}->AddOutput(${nameOf(send)}, $name);")
            else emitln(s"${ctrler}->AddOutput(${nameOf(send)});")
          }
        }
      }
      declare(n)
      genCtxComputeEnd { // Data of write can be controller.done. So must evaluate data after controller is evaluated
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(s"$ctrlerEn") {
          emitEn(n.en)
          emitAssign(n) { i => 
            n match {
              case n:BufferWrite => s"${n.en.qidx(i)} ? ${n.data.qidx(i)} : ${n.qidx(i)}" 
              case n:TokenWrite => s"true"
            }
          }
        }
        val cond = if (n.isFIFO) List(n.done.qref, n.en.qany) else List(n.done.qref)
        emitIf(cond.mkString(" & ")) {
          emitln(s"Token data = make_token(${n.qref});")
          if (n.en.getVec > 1) {
            emitln(s"set_token_en<${n.en.getVec}>(data, ${n.en.qref});")
          } else {
            emitln(s"set_token_en(data, ${n.en.qref});")
          }
          if (withPipe) emitln(s"$name->Push(data);")
          else n.out.T.foreach { send =>
            emitln(s"${nameOf(send)}->Push(data);")
          }
        }
      }

    case n:FIFO =>
      genTopMember(n, Seq(n.qstr))

    case n:Memory =>
      val accesses = n.accesses.map { a => s"""make_tuple(${a.id}, ${a.isInAccess}, ${a.port.get.isEmpty})""" }.mkString(",")
      genTopMember(n, Seq(n.qstr, s"{$accesses}"))

    case n:MemRead if n.mem.T.isFIFO =>
      val mem = n.mem.T
      addEscapeVar(mem)
      val (tp, name) = varOf(mem)
      val ctrler = getCtrler(n)
      genCtxInits {
        emitln(s"${ctrler}->AddInput(${name});")
      }
      emitEn(n.en)
      if (n.en.isConnected) {
        emitln(s"$name->SetReadEn(${n.en.qref});")
      }
      emitIf(s"$name->Valid()") {
        emitVec(n) { i =>
          s"toT<${n.qtp}>($name->Read(), ${i.getOrElse(0)})" 
        }
      }
      genCtxComputeEnd {
        val cond = List(n.done.qref, n.en.qany)
        emitIf(cond.mkString(" & ")) {
          emitln(s"$name->Pop();")
        }
      }

    case n:MemWrite if n.mem.T.isFIFO =>
      val mem = n.mem.T
      val (tp, name) = varOf(mem)
      addEscapeVar(mem)
      val ctrler = getCtrler(n)
      genCtxInits {
        emitln(s"${ctrler}->AddOutput(${name});")
      }
      declare(n)
      genCtxComputeEnd {
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(s"$ctrlerEn") {
          emitEn(n.en)
          emitAssign(n) { i => 
            s"${n.en.qidx(i)} ? ${n.data.qidx(i)} : ${n.qidx(i)}" 
          }
        }
        val cond = List(n.done.qref, n.en.qany)
        emitIf(cond.mkString(" & ")) {
          emitln(s"Token data = make_token(${n.qref});")
          if (n.en.getVec > 1) {
            emitln(s"set_token_en<${n.en.getVec}>(data, ${n.en.qref});")
          } else {
            emitln(s"set_token_en(data, ${n.en.qref});")
          }
          emitln(s"${name}->Push(data);")
        }
      }

    case n:FlatBankedRead =>
      emitln(s"// ${n}")
      addEscapeVar(n.mem.T)
      emitln(s"""${n.mem.T}->SetupRead(${n.id},make_token(${n.offset.qref}));""")
      genCtxComputeEnd {
        val ctrler = getCtrler(n)
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(ctrlerEn) {
          emitln(s"""${n.mem.T}->SetDone(${n.id}, ${n.done.qref});""")
        }
      }

    case n:FlatBankedWrite =>
      emitln(s"// ${n}")
      addEscapeVar(n.mem.T)
      emitln(s"""${n.mem.T}->Write(${n.id}, make_token(${n.data.qref}), make_token(${n.offset.qref}));""")
      genCtxComputeEnd {
        val ctrler = getCtrler(n)
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(ctrlerEn) {
          emitln(s"""${n.mem.T}->SetDone(${n.id}, ${n.done.qref});""")
        }
      }

    case n:MemRead =>
      emitln(s"// ${n}")
      addEscapeVar(n.mem.T)
      emitln(s"""auto $n = ${n.mem.T}->Read("$n");""")
      genCtxComputeEnd {
        val ctrler = getCtrler(n)
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(ctrlerEn) {
          emitln(s"""${n.mem.T}->SetDone(${n.id}, ${n.done.qref});""")
        }
      }

    case n:MemWrite =>
      emitln(s"// ${n}")
      addEscapeVar(n.mem.T)
      emitEn(n.en)
      emitln(s"""if (${n.en.qref}) ${n.mem.T}->Write(${n.id}, ${n.data.T});""")
      genCtxComputeEnd {
        val ctrler = getCtrler(n)
        val ctrlerEn = s"$ctrler->Enabled()"
        emitIf(ctrlerEn) {
          emitln(s"""${n.mem.T}->SetDone(${n.id}, ${n.done.qref});""")
        }
      }

    case n => super.emitNode(n)
  }

  var fifoDepth = -1
  override def initPass = {
    super.initPass
    fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }
      .getOrElse{ 100 }
    dbg(s"fifoDepth=$fifoDepth")
  } 

  def emitEn(en:Input[PIRNode]):Unit = {
    declare(en)
    genCtxComputeBegin {
      emitAssign(en) { i => "false" }
    }
    emitAssign(en) { i =>
      var ens = en.connected.map { _.qidx(i) }
      ens.distinct.reduceOption[String]{ _ + " & " + _ }.getOrElse("true")
    }
  }

  override def quoteRef(n:Any):String = n match {
    //case n@InputField(x:BufferRegRead, "in") if !x.nonBlocking => varOf(x)._2.field("data")
    //case n@InputField(x:BufferRegRead, f@("writeEn" | "writeDone")) => varOf(x)._2.field(f)
    case n@InputField(a@(_:LocalAccess | _:Access), "en") => s"${a}_en"
    case n@InputField(a@(_:Access | _:LocalAccess), "done") => 
      val isFIFO = a match {
        case a:Access => a.mem.T.isFIFO
        case a:LocalAccess => a.isFIFO
      }
      //n.as[Input[PIRNode]].singleConnected.map { _.qref }.getOrElse("false")
      n.as[Input[PIRNode]].singleConnected.map { 
        case done@OutputField(read:BufferRead, _) if !isFIFO => read.done.qref + " & " + done.qref
        case done => done.qref
      }.getOrElse("false")
    case n@InputField(x:LocalOutAccess, "in") => varOf(x)._2
    case n => super.quoteRef(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:LocalOutAccess if n.nonBlocking =>
      (s"NBlockBufferReg<Token>", s"fifo_$n")
    case n:BufferRegRead =>
      (s"BufferReg<${n.in.getVec}, $fifoDepth>", s"fifo_$n")
    case n:BufferRead if isRateMatchingFIFO(n) =>
      (s"RateMatchingTokenFIFO<${n.qtp}, ${fifoDepth*math.max(n.in.getVec, n.out.getVec)}, ${n.in.getVec}, ${n.out.getVec}>", s"fifo_$n") 
    case n:BufferRead =>
      (s"FIFO<Token, ${fifoDepth}>", s"fifo_$n")
    case n:TokenRead =>
      (s"FIFO<Token, ${n.getDepth}>", s"fifo_$n")
    case n:LocalInAccess =>
      (s"ValReadyPipeline<Token>", s"pipe_$n")
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
