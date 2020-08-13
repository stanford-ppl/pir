package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param._
import scala.collection.mutable

trait TungstenSparseGen extends TungstenCodegen with TungstenCtxGen with TungstenMemGen with TungstenBlackBoxGen with Memorization {

  override def emitNode(n:N) = n match {
    case ctx:Context if ctx.streaming.get && (ctx.descendents.exists { case datascanner:DataScanner => true; case scanner:Scanner => true; case splitter:SplitLeader => true; case bvbuild:BVBuildNoTree => true; case bvbuild:BVBuildTree => true; case bvbuild:BVBuildTreeLen => true; case _ => false }) => 
      withinBB {
        visitNode(n)
      }
      
    case n:DataScanner =>
      val mask = nameOf(n.mask.T.as[BufferRead]).&
      val count = nameOf(n.cnt.T.as[BufferWrite].out.singleConnected.get.src).&
      val index = nameOf(n.index.T.as[BufferWrite].out.singleConnected.get.src).&
      val data = nameOf(n.data.T.as[BufferWrite].out.singleConnected.get.src).&
      genTopMember(n, Seq(n.qstr, mask, count, index, data), end=true)
      
    case n:Scanner =>
      val masks = n.masks.map { mask => nameOf(mask.T.as[BufferRead]).& }.qlist
      val ctrlWord = nameOf(n.ctrlWord.T.as[BufferWrite].out.singleConnected.get.src).&
      // val tileCount = nameOf(n.tileCount.T.as[BufferRead]).&
      // val packCntIdx = n.packedCntIdx.map { packCntIdx => nameOf(packCntIdx.T.as[BufferWrite].out.singleConnected.get.src).& }.qlist
      val packCntIdx = nameOf(n.packedCntIdx.T.as[BufferWrite].out.singleConnected.get.src).&
      val vecTotals = n.vecTotals.map { vecTotal => nameOf(vecTotal.T.as[BufferWrite].out.singleConnected.get.src).& }.qlist
      genTopMember(n, Seq(n.qstr, n.mode.qstr, masks, ctrlWord, packCntIdx, vecTotals), end=true)

    case n:SplitLeader =>
      val addrIn = nameOf(n.addrIn.T.as[BufferRead]).&
      val ctrlOut = nameOf(assertOne(n.ctrlOut.T, s"$n.out").as[BufferWrite].out.singleConnected.get.src).&
      genTopMember(n, Seq(n.qstr, addrIn, ctrlOut), end=true)

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

    case n@SparseRMW(op, opOrder, remoteAddr, key) =>
      emitln(s"// ${n}")
      val ctrler = getCtrler(n)
      addEscapeVar(n.mem.T)
      val (tp, name) = varOf(n)
      declareInit(tp, name, init=None);
      genCtxInits {
        emitln(s"""$name = ${nameOf(n.mem.T)}->GetRMWPorts("$op","$opOrder",${n.id});""")
        emitln(s"${ctrler}->AddOutput(${name}.first);") // (addr input, ack)
        emitln(s"${ctrler}->AddOutput(${name}.second);") // (data)
      }
      emitln(s"$name.first->Push(${nameOf(n.addr.T)}->Read());")
      emitln(s"$name.second->Push(${nameOf(n.input.T)}->Read());")

    case WithData(n:BufferWrite, data:SparseAccess) => // SparseRead.out or SparseWrite.ack
      val dataOut = n.data.singleConnected.get match {
        case OutputField(data:SparseRead,"out") => nameOf(data)
        case OutputField(data:SparseWrite,"ack") => s"${nameOf(data)}.first"
        case OutputField(data:SparseRMW,"dataOut") => s"${nameOf(data)}.first"
      }
      genCtxMember("Broadcast<Token>", s"bc_$data", Seq(dataOut, n.out.T.map { nameOf(_) }.qlist), end=true)
      genCtxInits {
        n.out.T.foreach { send =>
          addEscapeVar(send)
        }
      }

    case n:SparseMem if n.memType == "SRAM" => genTopMember(n, Seq(n.qstr))

    case n:SparseParSRAMBlock => 
      val orderList = n.rmwPorts.map { case (a, ports) => n.rmwOps(a)._2 }.to[List]
      val order = assertOneOrLess(orderList.distinct, s"$n RMW order").getOrElse("order")
      n.alias.v match {
        case None =>
          if (n.swizzle.get) {
            genTopMember(n, Seq(n.qstr, order.qstr, "NULL", s"NULL", s"make_tuple(&net, &statnet, &idealnet)", s"false", s"true"))
          } else {
            genTopMember(n, Seq(n.qstr, order.qstr, "NULL", s"NULL", s"make_tuple(&net, &statnet, &idealnet)", s"false", s"false"))
          }
        case Some(alias) =>
          assert(false)
      }
      genTopInit {
        n.readPorts.foreach { case (a, ports) =>
          emitln(s"""$n.RegisterRead("read${a}_", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
        n.writePorts.foreach { case (a, ports) =>
          emitln(s"""$n.RegisterWrite("write${a}_", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
        n.rmwPorts.foreach { case (a, ports) =>
          val (op, order) = n.rmwOps(a)
          //emitln(s"""$n.RegisterRMW("rmw${a}_", "$op", "$order", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
          emitln(s"""$n.RegisterRMW("rmw${a}_", "$op", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
      }

    case n:SparseDRAMBlock => 
      val orderList = n.rmwPorts.map { case (a, ports) => n.rmwOps(a)._2 }.to[List]
      val order = assertOneOrLess(orderList.distinct, s"$n RMW order").getOrElse("order")
      n.alias.v match {
        case None =>
          genTopFields {
            emitln(s"${n.qtp}* ${n}_data = (${n.qtp}*) malloc(sizeof(${n.qtp}) * ${n.dims.get.product} + ${spadeParam.burstSizeByte});")
          }
          genTopMember(n, Seq(n.qstr, order.qstr, "&DRAM", s"${n}_data", s"make_tuple(&net, &statnet, &idealnet)", s"false"))
        case Some(alias) =>
          genTopAfterModule {
            emitln(s"extern void* ${alias.sname.get};")
          }
          genTopMember(n, Seq(n.qstr, order.qstr, "&DRAM", s"(${n.qtp}*) ${alias.sname.get}", s"make_tuple(&net, &statnet, &idealnet)", s"false"))
      }
      genTopInit {
        n.readPorts.foreach { case (a, ports) =>
          emitln(s"""$n.RegisterRead("read${a}_", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
        n.writePorts.foreach { case (a, ports) =>
          emitln(s"""$n.RegisterWrite("write${a}_", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
        n.rmwPorts.foreach { case (a, ports) =>
          val (op, order) = n.rmwOps(a)
          //emitln(s"""$n.RegisterRMW("rmw${a}_", "$op", "$order", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
          emitln(s"""$n.RegisterRMW("rmw${a}_", "$op", {${(0 until ports.size).map { i => i }.mkString(",")}});""")
        }
      }

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
    case n:SplitLeader => (s"SplitLeader", s"${n}")
    case n:Scanner => (s"VecScanHelper<${n.nstream}, ${n.par}>", s"${n}")
    case n:DataScanner => (s"DataScanner", s"${n}")
    case n:SparseRead => (s"${tpOf(n.mem.T)}::SparsePMUPort*", s"${n}_port")
    case n:SparseWrite => (s"pair<${tpOf(n.mem.T)}::SparsePMUPort*,${tpOf(n.mem.T)}::SparsePMUPort*>", s"${n}_ports")
    case n:SparseRMW => (s"pair<${tpOf(n.mem.T)}::SparsePMUPort*,${tpOf(n.mem.T)}::SparsePMUPort*>", s"${n}_ports")
    case n:SparseMem if n.memType == "SRAM" => (s"SparsePMU<${n.qtp},$wordPerBank,${spadeParam.vecWidth}>", s"$n")
    case n:SparseDRAMBlock => (s"ParDRAM<true,${n.dramPar},${n.qtp},1>", s"$n")
    case n:SparseParSRAMBlock => (s"ParDRAM<false,${n.dramPar},${n.qtp},1>", s"$n")
    case n => super.varOf(n)
  }

}

