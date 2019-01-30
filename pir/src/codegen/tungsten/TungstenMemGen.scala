package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenMemGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n:GlobalOutput =>
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
      
    case n:GlobalInput =>
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

    case n:LocalOutAccess =>
      val (tp, name) = varOf(n)
      genTop {
        emitln(s"""$tp $name("$n");""")
        dutArgs += name
      }
      addEscapeVar(n)
      genCtxInits {
        emitln(s"""inputs.push_back($name);""")
        if (n.initToken.get) {
          emitToken(s"init_$n", n.getVec, n.inits.get)
          emitln(s"$name->Push(init_$n);")
        }
      }
      val readByFringe = n.out.T.exists { _.isInstanceOf[DRAMCommand] }
      if (!readByFringe) {
        emitVec(n)(s"$name->Read().intVec_${if (n.getVec==1) "[0]" else "[i]"};")
        genCtxComputeEnd {
          emitIf(s"${n.done.T}") {
            emitln(s"$name->Pop();")
          }
        }
      }

    case n:LocalInAccess =>
      val data = n match {
        case n:BufferWrite => n.data.T
        case n:TokenWrite => n.done.T
      }
      val (tp, name) = varOf(n)
      genCtxFields {
        emitln(s"""$tp *$name = new $tp("$n");""")
      }
      genCtxInits {
        emitln(s"""AddChild($name);""")
      }
      val ctx = n.ctx.get
      n.out.T.foreach { send =>
        if (!send.isDescendentOf(ctx)) addEscapeVar(send)
        genCtxInits {
          emitln(s"AddSend(${varOf(send)._2}, $name);")
        }
      }
      val sendValid = data match {
        case data:BankedRead => s"${data}->Valid()"
        case data => s"${n.done.T}"
      }
      emitIf(s"$sendValid") {
        emitToken(n, n.getVec, List.tabulate(n.getVec) { i => if (data.getVec>1) s"$data[$i]" else s"$data" })
        emitln(s"$name->Push($n);")
      }

    case n:Memory =>
      val (tp, name) = varOf(n)
      emitln(s"""$tp $name("$n");""")

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
        emitln(s"$mem->Push(${n.data.T});")
      }

    case n:BankedRead =>
      emitAccess(n) { mem =>
        emitln(s"${mem}->SetupRead(${n.offset.T});")
        emitln(s"Token $n;")
        emitBlock(s"if ($mem->ReadValid())") {
          emitln(s"$n = $mem->ReadData();")
        }
      }

    case n:BankedWrite =>
      emitAccess(n) { mem =>
        emitln(s"if (${quoteEn(n.en)}) ${mem}->Write(${n.data.T}, ${n.offset.T});")
      }

    case n:MemRead =>
      emitAccess(n) { mem =>
        emitln(s"auto $n = ${mem}->Read();")
      }

    case n:MemWrite =>
      emitAccess(n) { mem =>
        emitln(s"if (${quoteEn(n.en)}) ${mem}->Write(${n.data.T});")
      }

    case n => super.emitNode(n)
  }

  def emitAccess(n:Access)(func:String => Unit) = {
    val mem = n.mem.T
    if (n.port.nonEmpty) {
      emitln(s"""auto* ${n}_buffer = $mem->GetBuffer("$n", ${n.done.T.getOrElse(false)})""")
      func(s"${n}_buffer")
    } else {
      emitBlock(s"for (int i = 0; i < $mem.buffers.size(); i++)") {
        emitln(s"""auto* ${n}_buffer = $mem->buffers[i]""")
        func(s"${n}_buffer");
      }
    }
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:GlobalOutput =>
      (s"FIFO<Token, 4>", s"$n")
    case n:GlobalInput =>
      (s"FIFO<Token, 1>", s"$n")
    case n:LocalOutAccess =>
      (s"FIFO<Token, 4>", s"fifo_$n") //TODO
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
      (s"FIFO<int, ${n.getDepth}>", s"$n")
    case n:SRAM =>
      val numBanks = n.getBanks.product
      (s"NBuffer<BankedSRAM<int, ${n.capacity}, ${n.nBanks}>, ${n.getDepth}>", s"$n")
    case n:LUT =>
      (s"NBuffer<BankedSRAM<int, ${n.capacity}, ${n.nBanks}>, ${n.getDepth}>", s"$n")
    case n:Reg =>
      (s"NBuffer<Register<int>, ${n.getDepth}>", s"$n")
    case n => super.varOf(n)
  }

}
