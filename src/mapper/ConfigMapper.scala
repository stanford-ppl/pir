package pir.mapper
import pir.node.{Const => _, _}
import pir._
import pir.util._
import pirc.exceptions._
import pir.util.misc._
import pir.codegen.Logger
import pir.spade.node.{SRAM => _, Top => _, Const => _, _}
import pir.util.typealias._
import scala.language.existentials

import scala.collection.mutable._

class ConfigMapper(implicit val design: PIR) extends Mapper {
  def shouldRun = Config.ctrl && Config.mapping
  import pirmeta._
  import spademeta._

  def typeStr = "ConfigMapper"

  def config(m:SRAM, mp:M):M = {
    val pm = mp.pmmap(m)
     val cfg = SRAMConfig (
      name=s"${m.ctrler}.${m}", 
      rpar = rparOf(m),
      wpar = wparOf(m),
      bufferSize = m.buffering,
      notFullOffset = 0,
      backPressure = backPressureOf(m),
      banking = m.banking,
      size = m.size
    )
    mp.setCF(pm, cfg)
  }

  def config(m:LMem, mp:M):M = {
    val pm = mp.pmmap(m)
    val cu = m.ctrler
    val (bufferSize, notFullOffset) = if (m.notFull.isConnected) {
      val fhop = mp.rtmap(pir.util.collectIn[I](m.writePort).head)
      val bhop = mp.rtmap(m.notFull.to.head)
      val pipeDepth = m.writers.map{ writer => mp.pmmap(writer.ctrler).asCU.stages.size }.max
      val notFullOffset = fhop + bhop + pipeDepth
      dprintln(s" - fhop=$fhop bhop=$bhop pipeDepth=$pipeDepth")
      val bufferSize = m.size + notFullOffset + 2 //TODO a little buffer just in case 
      (bufferSize, notFullOffset)
    } else {
      val bufferSize = (m, cu) match {
        case (m:FIFO, cu:MP) if m.readPort.to.exists{ _ == cu.sram.writePort} =>
          m.size + mp.cfmap(mp.pmmap(cu.ctrlBox.writeEnDelay)).delay
        case (m:FIFO, cu) =>
          m.size
        case (m:MBuf, cu) =>
          m.buffering
      }
      (bufferSize, 0)
    }
    val cfg = LocalMemConfig(
      name=s"${m.ctrler}.$m",
      isFifo=m.isFifo,
      backPressure=backPressureOf(m),
      bufferSize=bufferSize,
      notFullOffset=notFullOffset
    )
    mp.setCF(pm, cfg)
  }

  def config(ctr:Ctr, mp:M):M = {
    val pctr = mp.pmmap(ctr)
    mp.setCF(pctr, CounterConfig(ctr.par))
  }

  def config(pudc:PUC, mp:M):M = {
    if (pir.spade.util.isMapped(pudc)(mp)) {
      val udc = mp.pmmap.get(pudc)
      val initVal = udc.map { _.initVal }.getOrElse(0)
      dprintln(s"$pudc -> $udc initVal=$initVal")
      mp.setCF(pudc, UDCounterConfig(initVal=initVal, name=s"$udc"))
    } else mp
  }

  def config(ppdu:PPDU, mp:M):M = {
    mp.pmmap.get(ppdu).fold(mp) { pdu =>
      mp.setCF(ppdu, PredicateUnitConfig(const=pdu.const, op=pdu.op))
    }
  }

  def config(cb:CB, map:M):M = {
    var mp = map
    val pcb = mp.pmmap(cb)
    pcb.udcs.foreach { pudc => mp = config(pudc, mp) }
    pcb.predicateUnits.foreach { ppdu => mp = config(ppdu, mp) }

    mp
  }

  def config(st:ST, map:M):M = {
    var mp = map
    val pst = mp.pmmap(st)
    val par = parOf(st)
    val op = st.fu.get.op
    val isReduce = st.isInstanceOf[pir.node.ReduceStage]
    // Pass through operand in accumulation
    val accumInput = st match {
      case st:pir.node.AccumStage =>
        val operands = st.fu.get.operands
        val accOprd = filterIn(operands, st.acc)
        Some(mp.ipmap((operands diff accOprd.toSeq).head))
      case _ => None
    }
    mp = mp.setCF(pst, StageConfig(par, op, isReduce, accumInput))
    mp
  }

  def config(mc:MC, mp:M):M = {
    val pmc = mp.pmmap(mc)
    mp.setCF(pmc, MemoryControllerConfig(mctpe=mc.mctpe))
  }

  def config(cu:MP, map:M):M = {
    var mp = map
    val pmcu = mp.pmmap(cu)

    // Set valid
    val outputValid = (cu.souts ++ cu.vouts).flatMap { out =>  
      mp.vomap(out).map { pout => 
        pout -> mp.pmmap(cu.ctrlBox.readEnDelay).out
      }
    }
    mp = mp.setCF(pmcu, new ControllerConfig(outputValid.toMap))

    // Set delay
    val rstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forRead(st) } }
    val wstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forWrite(st) } }
    val ridxs = rstages.map(_.index)
    val widxs = wstages.map(_.index)
    val rdelay = (if (ridxs.nonEmpty) ridxs.max - ridxs.min else 0)
    val wdelay = if (widxs.nonEmpty) widxs.max - widxs.min else 0
    dprintln(s"$cu(${quote(pmcu)}) rdelay=$rdelay wdelay=$wdelay")
    mp = mp.setCF(pmcu.ctrlBox.readEnDelay, DelayConfig(rdelay))
    mp = mp.setCF(pmcu.ctrlBox.readDoneDelay, DelayConfig(rdelay))
    mp = mp.setCF(pmcu.ctrlBox.writeEnDelay, DelayConfig(wdelay))
    mp = mp.setCF(pmcu.ctrlBox.writeDoneDelay, DelayConfig(wdelay))

    mp
  }

  def config(cu:PL, map:M):M = {
    var mp = map
    val pcu = mp.pmmap(cu)

    // Set valid
    val outputValid = (cu.souts ++ cu.vouts).flatMap { out =>
      val in = out.readers.head
      in.ctrler match {
        case top:Top => 
          mp.vomap(out).map { _ -> mp.pmmap(cu.ctrlBox.doneDelay).out }
        case _ =>
          if (collectOut[FIFO](in).nonEmpty)
            mp.vomap(out).map { _ -> mp.pmmap(cu.ctrlBox.enDelay).out }
          else if (collectOut[MultiBuffer](in).nonEmpty)
            mp.vomap(out).map { _ -> mp.pmmap(cu.ctrlBox.doneDelay).out }
          else Nil
      }
    }
    mp = mp.setCF(pcu, new ControllerConfig(outputValid.toMap))
    
    // Set delay
    val delay = pcu.stages.size
    dprintln(s"$cu(${quote(pcu)}) delay=$delay")
    mp = mp.setCF(mp.pmmap(cu.ctrlBox.enDelay), DelayConfig(delay))
    mp = mp.setCF(mp.pmmap(cu.ctrlBox.doneDelay), DelayConfig(delay))

    mp
  }

  def config(cu:Top, map:M):M = {
    var mp = map
    val pcu = mp.pmmap(cu)
    val bounds = cu.souts.flatMap { sout => mp.vomap(sout).map { _ -> boundOf.get(sout) } }
    val outputValid = (cu.souts ++ cu.vouts).flatMap { out =>  
      mp.vomap(out).map { _ -> pcu.ctrlBox.command }
    }
    mp = mp.setCF(pcu, new TopConfig(bounds.toMap, outputValid.toMap))
    mp
  }

  def config(cu:OCL, map:M):M = {
    var mp = map
    val pcu = mp.pmmap(cu)
    mp = mp.setCF(pcu, new OuterComputeUnitConfig(isSeq=cu.isSeq, isMeta=cu.isMeta))
    mp
  }

  def config(cu:CU, map:M):M = {
    var mp = map

    cu.mems.foreach { 
      case mem:SRAM => mp = config(mem, mp)
      case mem:LMem => mp = config(mem, mp)
    }

    cu.cchains.foreach { 
      _.counters.foreach { ctr => mp = config(ctr, mp) }
    }

    cu.stages.foreach { st => mp = config(st, mp) }

    cu match {
      case cu:MP => mp = config(cu, map)
      case cu:PL => mp = config(cu, map) 
      case mc:MC => mp = config(mc, mp)
      case ocu:OCL => mp = config(ocu, mp)
    }
    mp
  }

  def config(cu:CL, map:M):M = {
    var mp = map
    emitBlock(s"${quote(mp.pmmap(cu))} -> $cu") {
      cu match {
        case cu:CU => mp = config(cu, mp)
        case cu:Top =>  mp = config(cu, mp)
      }
      mp = config(cu.ctrlBox, mp)
    }
    mp
  }

  def config(c:Const, mp:M) = {
    val pc = mp.pmmap(c)
    mp.setCF(pc, ConstConfig(c.value))
  }

  def map(map:M):M = {
    var mp = map
    mp = design.top.ctrlers.foldLeft(mp) { case (mp, cu) => config(cu, mp) }
    mp.pmmap.keys.foreach {
      case n:Const => mp = config(n, mp)
      case _ =>
    }
    mp
  }

}
