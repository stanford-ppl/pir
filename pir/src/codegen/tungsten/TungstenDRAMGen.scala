package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenDRAMGen extends TungstenCodegen with TungstenCtxGen with TungstenBlackBoxGen {

  override def initPass = {
    super.initPass
    val tstHome = config.tstHome.getOrElse(err(s"tungsten-home is not set"))
    // DRAMSim
    //val dramFile = buildPath(tstHome, "ini", "DRAM.ini")
    //val systemFile = buildPath(tstHome, "ini", "system.ini")
    //copyFile(dramFile, buildPath(config.tstOut, "DRAM.ini"))
    //copyFile(systemFile, buildPath(config.tstOut, "system.ini"))
    //genTopMember("DRAMController", "DRAM", Seq("DRAM".qstr, "DRAM.ini".qstr, "system.ini".qstr, ".".qstr, s"{}", s"{}"), extern=true, end=false, escape=true)

    // Ramulator 
    val configFile = s"${spadeParam.memTech}-config.cfg"
    val configPath = buildPath(tstHome, "configs", configFile)
    copyFile(configPath, buildPath(config.tstOut, configFile))
    genTopMember("DRAMController", "DRAM", Seq("DRAM".qstr, configFile.qstr, s"{}", s"{}"), extern=true, end=false, escape=true)
  }

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && ctx.collectDown[DRAMCommand]().nonEmpty =>
      withinBB {
        visitNode(n)
      }

    case n:DRAMAddr =>
      emitln(s"extern void* ${n.dram.sname.get};")
      declare(n.qtp, n.qref, s"(${n.qtp}) ${n.dram.sname.get}")

    case n:FringeDenseLoad =>
      val offset = nameOf(n.offset.T.as[BufferRead]).&
      val size = nameOf(n.size.T.as[BufferRead]).&
      val data = nameOf(n.data.T.as[BufferWrite].gout.get).&
      genTopMember(n, Seq(n.qstr, offset, size, data, "DRAM".&), end=true)

    case n:FringeDenseStore =>
      val offset = nameOf(n.offset.T.as[BufferRead]).&
      val size = nameOf(n.size.T.as[BufferRead]).&
      val data = nameOf(n.data.T.as[BufferRead]).&
      val valid = nameOf(n.valid.T.as[BufferRead]).&
      val ack = nameOf(n.ack.T.as[BufferWrite].gout.get).&
      genTopMember(n, Seq(n.qstr, offset, size, data, valid, ack, "DRAM".&), end=true)

    case n:FringeSparseLoad =>
      val addr = nameOf(n.addr.T.as[BufferRead]).&
      val data = nameOf(n.data.T.as[BufferWrite].gout.get).&
      genTopMember(n, Seq(n.qstr, addr, data, "DRAM".&), end=true)

    case n:FringeSparseStore =>
      val addr = nameOf(n.addr.T.as[BufferRead]).&
      val data = nameOf(n.data.T.as[BufferRead]).&
      val ack = nameOf(n.ack.T.as[BufferWrite].gout.get).&
      genTopMember(n, Seq(n.qstr, addr, data, ack, "DRAM".&), end=true)

    case n:FringeCoalStore =>
      val addr = nameOf(n.offset.T.as[BufferRead]).&
      val size = nameOf(n.size.T.as[BufferRead]).&
      val data = nameOf(n.data.T.as[BufferRead]).&
      val valid = nameOf(n.valid.T.as[BufferRead]).&
      val ack = nameOf(n.ack.T.as[BufferWrite].gout.get).&
      genTopMember(n, Seq(n.qstr, addr, size, data, valid, ack, "DRAM".&), end=true)

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:FringeDenseLoad => 
      if (n.dram.compressed.get)
        (s"DecompressAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
      else
        (s"DenseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeDenseStore => (s"DenseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseLoad => (s"SparseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseStore => (s"SparseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeCoalStore => (s"CoalStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n => super.varOf(n)
  }

  override def quoteRef(n:Any):String = n match {
    case n@OutputField(x:DRAMCommand, field) => s"true"
    case n => super.quoteRef(n)
  }

}
