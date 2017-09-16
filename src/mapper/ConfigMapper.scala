package pir.mapper
import pir.graph.{Const => _, _}
import pir._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.codegen.Logger
import pir.spade.graph.{SRAM => _, Top => _, Const => _, _}
import pir.util.typealias._

import scala.collection.mutable._

class ConfigMapper(implicit val design: Design) extends Mapper {
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

  def config(mc:MC, mp:M):M = {
    val pmc = mp.pmmap(mc)
    mp.setCF(pmc, MemoryControllerConfig(mctpe=mc.mctpe))
  }

  def config(cb:CB, map:M):M = {
    var mp = map
    val pcb = mp.pmmap(cb)
    pcb.udcs.foreach { pudc => mp = config(pudc, mp) }
    pcb.predicateUnits.foreach { ppdu => mp = config(ppdu, mp) }

    cb match {
      case ocb:OCB => mp = config(ocb, mp)
      case _ =>
    }
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
    mp
  }

  def config(cu:CL, map:M):M = {
    var mp = map
    emitBlock(s"${quote(mp.pmmap(cu))} -> $cu") {
      cu match {
        case cu:CU => mp = config(cu, mp)
        case cu:Top => 
      }
      cu match {
        case mc:MC => mp = config(mc, mp)
        case _ =>
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
