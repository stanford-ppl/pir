package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenDRAMGen extends TungstenCodegen with TungstenCtxGen {

  val issuers = mutable.ListBuffer[String]()

  override def finPass = {
    genTopEnd {
      //emitln(s"""DRAMController DRAM("DRAM", "ini/DRAM.ini", "ini/system.ini", ".", {${issuers.map{_.&}.mkString(",")}}, {${respondQueues.map(_.&).mkString(",")}});""")
      emitln(s"""IDRAMController DRAM("DRAM", {${issuers.map{_.&}.mkString(",")}});""")
      dutArgs += "DRAM"
    }
    super.finPass
  }

  override def emitNode(n:N) = n match {
    case n:FringeDenseLoad =>
      val (tp, name) = varOf(n)
      genTopEnd {
        emitln(s"""$tp $name("$n", ${n.data.T.as[BufferWrite].gout.get.&}, ${n.data.T.getVec}, ${n.data.T.tokenTp});""")
        dutArgs += name
      }
      genCtxInits {
        emitln(s"AddSend(${n}->offset);")
        emitln(s"AddSend(${n}->size);")
      }
      addEscapeVar(n)
      emitln(s"${n}->offset->Push(make_token(${n.offset.T}));")
      emitln(s"${n}->size->Push(make_token(${n.size.T}));")
      issuers += s"$n"
    //case n:DRAMCommand =>
      //val (tp, name) = varOf(n)
      //genTop {
        //emitln(s"""$tp $name("$n", ${n.data.T.getVec});""")
        //dutArgs += name
      //}
      //addEscapeVar(n)
      //n match {
        //case n:FringeDenseLoad =>
          //emitln(s"$name->SetupCommand(${n.offset.T}, ${n.size.T});")
          //respondQueues += nameOf(n.data.T.as[BufferWrite].gout.get)
        //case n:FringeDenseStore =>
          //emitln(s"$name->SetupCommand(${n.offset.T}, ${n.size.T}, ${n.data.T});")
          //respondQueues += nameOf(n.ack.T.as[BufferWrite].gout.get)
        //case n:FringeSparseLoad =>
          //emitln(s"$name->SetupCommand(${n.addr.T});")
          //respondQueues += nameOf(n.data.T.as[BufferWrite].gout.get)
        //case n:FringeSparseStore =>
          //emitln(s"$name->SetupCommand(${n.addr.T}, ${n.data.T});")
          //respondQueues += nameOf(n.ack.T.as[BufferWrite].gout.get)
      //}
      //cmdQueues += s"$name.cmd"
      //genCtxInits {
        //emitln(s"AddSend(${name}->cmd);")
      //}

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:FringeDenseLoad => (s"DenseLoadIssuer", s"${n}")
    case n => super.varOf(n)
  }

  override def quoteRef(n:Any):String = n match {
    case n@OutputField(x:DRAMCommand, field) => s"true"
    case n => super.quoteRef(n)
  }

}
