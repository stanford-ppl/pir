package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenMemGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n:GlobalOutput if noPlaceAndRoute =>
      val (tp, name) = varOf(n)
      genTop {
        emitln(s"""$tp $name("$n");""")
        dutArgs += name
      }
      genTopEnd {
        val bcArgs = n.out.T.map { out => varOf(out)._2.& }
        emitln(s"""Broadcast<Token> bc_$n("bc_$n", ${name.&}, {${bcArgs.mkString(",")}});""")
        dutArgs += s"bc_$n"
      }
      
    case n:GlobalInput if noPlaceAndRoute =>
      val (tp, name) = varOf(n)
      genTop {
        emitln(s"""$tp $name("$n");""")
        dutArgs += name
      }
      genTopEnd {
        val bcArgs = n.out.T.map { out => varOf(out)._2.& }
        emitln(s"""Broadcast<Token> bc_$n("bc_$n", ${name.&}, {${bcArgs.mkString(",")}});""")
        dutArgs += s"bc_$n"
      }

    case n:GlobalOutput =>
      val (tp, name) = varOf(n)
      val bcArgs = n.out.T.map { out => varOf(out)._2.& }
      emitln(s"""$tp $name("$n", &net);""")
      emitln(s"""// dst = ${bcArgs.mkString(",")}""")
      dutArgs += name
      
    case n:GlobalInput =>
      val (tp, name) = varOf(n)
      emitln(s"""$tp $name("$n", &net);""")
      dutArgs += name

      genTopEnd {
        val bcArgs = n.out.T.map { out => varOf(out)._2 }
        assert(bcArgs.length == 1) // This assertion can fail
        val src = s"$name"
        val dst = s"${bcArgs.mkString(",")}"
        emitln(s"""Bridge<Token> brg_${src}_${dst}("brg_${src}_${dst}", &$src, &$dst);""")
        dutArgs += s"brg_${src}_${dst}"
      }

    case n:LocalOutAccess =>
      val (tp, name) = varOf(n)
      genTop {
        emitln(s"""$tp $name("$n");""")
        dutArgs += name
      }
      n.ctx.get match {
        case DRAMContext(cmd) =>
        case _ =>
          addEscapeVar(n)
          genCtxInits {
            emitln(s"""inputs.push_back($name);""")
            if (n.initToken.get) {
              emitln(s"$name->Push(make_token((${n.qtp}) ${assertOne(n.inits.get, s"$n.inits")}));")
            }
          }
          emitVec(n) { i =>
            s"toT<${n.qtp}>($name->Read(), ${i.getOrElse(0)})" 
          }
          genCtxComputeEnd {
            emitIf(s"${n.done.qref}") {
              emitln(s"$name->Pop();")
            }
          }
      }

    case n:BufferWrite if n.data.T.isInstanceOf[DRAMCommand] =>

    case n:BufferWrite if n.data.T.isInstanceOf[BankedRead] =>
      n.out.T.foreach { send =>
        addEscapeVar(send)
        genCtxInits {
          emitln(s"AddSend(${nameOf(send)});");
        }
        genCtxEval {
          val data = nameOf(n.data.T)
          emitIf(s"$data->Valid() && ${nameOf(send)}->Ready()") {
            emitln(s"${nameOf(send)}->Push($data->Read());")
            emitln(s"$data->Pop();")
          }
        }
      }

    case n:BufferWrite =>
      val (tp, name) = varOf(n)
      emitNewMember(tp, name)
      val ctx = n.ctx.get
      n.out.T.foreach { send =>
        if (!send.isDescendentOf(ctx)) addEscapeVar(send)
        genCtxInits {
          emitln(s"AddSend(${nameOf(send)}, $name);")
        }
      }
      emitIf(s"${n.done.qref} && ${n.en.qref}") {
        emitln(s"$name->Push(make_token(${n.data.qref}));")
      }

    case n:TokenWrite =>
      n.out.T.foreach { send =>
        addEscapeVar(send)
        genCtxInits {
          emitln(s"AddSend(${nameOf(send)});");
        }
        genCtxComputeEnd {
          emitIf(s"${n.done.qref}") {
            emitln(s"${nameOf(send)}->Push(make_token(true));")
          }
        }
      }

    case n:Memory =>
      val (tp, name) = varOf(n)
      val accesses = n.accesses.filterNot { _.port.isEmpty }
      emitln(s"""$tp $name("$n", {${accesses.map { a => s""""$a"""" }.mkString(",")}});""")
      dutArgs += name

    case n:MemRead if n.mem.T.isFIFO =>
      val mem = n.mem.T
      addEscapeVar(mem)
      emitEn(n.en)
      emitln(s"int $n = 0;")
      emitIf(s"${n}_en"){
        emitln(s"${n} = $mem->Read();")
        emitIf(n.done.T) {
          emitln(s"${mem}.Pop();")
        }
      }

    case n:MemWrite if n.mem.T.isFIFO =>
      val mem = n.mem.T
      addEscapeVar(mem)
      emitEn(n.en)
      emitIf(s"${n}_en && ${n.done.T}"){
        emitln(s"$mem->Push(${n.data.qref});")
      }

    case n:BankedRead =>
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      emitNewMember(tp, name)
      emitAccess(n) { mem =>
        emitln(s"${mem}->SetupRead(make_token(${n.offset.qref}));")
      }
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }
      genCtxEval {
        emitAccess(n, prev=true) { buffer =>
          emitIf(s"$buffer->ReadValid()") {
            emitln(s"$name->Push($buffer->ReadData());");
          }
        }
      }

    case n:BankedWrite =>
      addEscapeVar(n.mem.T)
      emitIf(s"${n.en.qref}") {
        emitAccess(n) { mem =>
          emitln(s"${mem}->Write(make_token(${n.data.qref}), make_token(${n.offset.qref}));")
        }
      }
      genCtxComputeEnd {
        emitln(s"""${n.mem.T}->SetDone("$n", ${n.done.qref});""")
      }

    //case n:MemRead =>
      //emitAccess(n) { mem =>
        //emitln(s"auto $n = ${mem}->Read();")
      //}

    //case n:MemWrite =>
      //emitAccess(n) { mem =>
        //emitln(s"if (${n.en.qref}) ${mem}->Write(${n.data.T});")
      //}

    case n => super.emitNode(n)
  }

  override def quoteRef(n:Any):String = n match {
    case n@InputField(access:Access, "done") if !n.as[Input[PIRNode]].isConnected => "false"
    case n@OutputField(access:BankedRead, "valid") => s"${access}_buffer->ReadValid()"
    case n => super.quoteRef(n)
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

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:GlobalOutput if noPlaceAndRoute =>
      (s"FIFO<Token,2>", s"$n")
    case n:GlobalInput if noPlaceAndRoute =>
      (s"FIFO<Token,2>", s"$n")
    case n:GlobalOutput =>
      (s"NetworkInput", s"ni_$n")
    case n:GlobalInput =>
      (s"NetworkOutput", s"no_$n")
    case n:BufferRead =>
      (s"FIFO<Token, 4>", s"fifo_$n") //TODO
    case n:TokenRead =>
      (s"FIFO<Token, ${n.getDepth}>", s"fifo_$n")
    case n:LocalInAccess =>
      val data = n match {
        case n:BufferWrite => n.data.T
        case n:TokenWrite => n.done.T
      }
      val pipeDepth = data match {
        case data:BankedRead => 1
        case _ => numStagesOf(n.ctx.get)
      }
      (s"ValPipeline<Token, $pipeDepth>", s"pipe_$n")
    case n:FIFO =>
      (s"FIFO<${n.qtp}, ${n.getDepth}>", s"$n")
    case n:SRAM =>
      val numBanks = n.getBanks.product
      (s"NBuffer<BankedSRAM<${n.qtp}, ${n.capacity/n.getDepth}, ${n.nBanks}>, ${n.getDepth}>", s"$n")
    case n:LUT =>
      (s"NBuffer<BankedSRAM<${n.qtp}, ${n.capacity/n.getDepth}, ${n.nBanks}>, ${n.getDepth}>", s"$n")
    case n:Reg =>
      (s"NBuffer<Register<${n.qtp}>, ${n.getDepth}>", s"$n")
    case n:BankedRead =>
      (s"FIFO<Token,2>", s"$n")
    case n => super.varOf(n)
  }

}
