package pir.mapper

import pir._
import pir.util._
import pir.util.typealias._

import spade.node.{SRAM => _, Top => _, Const => _, _}
import spade.util.isMapped
import spade.traversal._

import pirc._

import scala.language.existentials

class ConfigMapper(implicit val design: PIR) extends Mapper with HiearchicalTraversal {
  def shouldRun = PIRConfig.ctrl && PIRConfig.mapping
  import pirmeta._
  import spademeta._

  def typeStr = "ConfigMapper"

  def config(pm:PSRAM, mp:M):M = {
    val m = mp.pmmap.to[SRAM](pm)
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

  def config(pm:PLMem, mp:M):M = {
    val m = mp.pmmap.to[LMem](pm)
    val cu = m.ctrler
    val (bufferSize, notFullOffset) = if (m.notFull.isConnected) {
      val fhop = mp.rtmap(pir.util.collectIn[GI](m.writePort).head).max
      val bhop = mp.rtmap(pir.util.collectOut[GO](m.notFull).head).max
      val pipeDepth = m.writers.map{ writer => mp.pmmap(writer.ctrler).asCU.stages.size }.max
      val notFullOffset = fhop + bhop + pipeDepth
      dprintln(s" - fhop=$fhop bhop=$bhop pipeDepth=$pipeDepth")
      val bufferSize = m.size + notFullOffset + 2 //TODO a little buffer just in case 
      (bufferSize, notFullOffset)
    } else {
      val bufferSize = (m, cu) match {
        case (m:FIFO, cu:MP) if m.readPort.to.exists{ _ == cu.sram.writePort} =>
          m.size + mp.cfmap(mp.pmmap.to[PD](cu.ctrlBox.writeEnDelay)).delay
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

  def config(pctr:PCtr, mp:M):M = {
    val ctr = mp.pmmap.to[Ctr](pctr)
    mp.setCF(pctr, CounterConfig(ctr.par))
  }

  def config(pudc:PUC, mp:M):M = {
    val udc = mp.pmmap.get[UC](pudc)
    val initVal = udc.map { _.initVal }.getOrElse(0)
    dprintln(s"$pudc -> $udc initVal=$initVal")
    mp.setCF(pudc, UDCounterConfig(initVal=initVal, name=s"$udc"))
  }

  def config(ppdu:PPDU, mp:M):M = {
    val pdu = mp.pmmap.to[PDU](ppdu)
    mp.setCF(ppdu, PredicateUnitConfig(const=pdu.const, op=pdu.op))
  }

  def config(pcb:PCB, map:M):M = {
    var mp = map
    val cb = mp.pmmap(pcb)
    //TODO
    mp
  }

  def config(ppr:PPR, map:M):M = {
    var mp = map
    val stage = mp.pmmap.to[ST](ppr.stage)
    val reg = mp.rcmap(ppr.reg).filter { reg => stage.liveOuts.contains(reg) }.head
    mp = mp.setCF(ppr, PipeRegConfig(reg.getInit))
    mp
  }

  def config(pst:PST, map:M):M = {
    var mp = map
    val st = mp.pmmap.to[ST](pst)
    val par = parOf(st)
    val op = st.fu.get.op
    val isReduce = st.isInstanceOf[pir.node.ReduceStage]
    // Pass through operand in accumulation
    val accumInput = st match {
      case st:pir.node.AccumStage =>
        val operands = st.fu.get.operands
        val accOprd = operands.filter { oprd => existsIn(oprd) { _ == st.acc } }
        Some(mp.ipmap((operands diff accOprd.toSeq).head))
      case _ => None
    }
    mp = mp.setCF(pst, StageConfig(par, op, isReduce, accumInput))
    mp
  }

  def config(pmc:PMC, mp:M):M = {
    val mc = mp.pmmap.to[MC](pmc)
    mp.setCF(pmc, MemoryControllerConfig(name=s"$mc", mctpe=mc.mctpe))
  }

  def config(pmcu:PMCU, map:M):M = {
    var mp = map
    val cu = mp.pmmap(pmcu)
    mp = mp.setCF(pmcu, new ControllerConfig(name=s"$cu"))

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

  def config(pcu:PCU, map:M):M = {
    var mp = map
    val cu = mp.pmmap.to[PL](pcu)

    mp = mp.setCF(pcu, new ControllerConfig(name=s"$cu"))
    
    // Set delay
    val delay = pcu.stages.size
    dprintln(s"$cu(${quote(pcu)}) delay=$delay")
    mp = mp.setCF(mp.pmmap.to[PD](cu.ctrlBox.enDelay), DelayConfig(delay))
    mp = mp.setCF(mp.pmmap.to[PD](cu.ctrlBox.doneDelay), DelayConfig(delay))

    mp
  }

  def config(pcu:PTop, map:M):M = {
    var mp = map
    val cu = mp.pmmap(pcu)
    val bounds = cu.souts.flatMap { sout => mp.vomap(sout).map { _ -> boundOf.get(sout.variable) } }
    dprintln(s"bounds of $cu:")
    dprintln(s"- ${bounds}")
    mp = mp.setCF(pcu, new TopConfig(name=s"$cu", bounds.toMap))
    mp
  }

  def config(pcu:POCU, map:M):M = {
    var mp = map
    val cu = mp.pmmap(pcu)
    mp = mp.setCF(pcu, new OuterComputeUnitConfig(name=s"$cu",isSeq=cu.isSeq, isMeta=cu.isMeta))
    mp
  }

  def config(c:Const, mp:M) = {
    val pc = mp.pmmap.to[PConst](c)
    mp.setCF(pc, ConstConfig(c.value))
  }

  def config[T](n:Any, map:T):T = {
    val mp = map.asInstanceOf[M]
    n match {
      case n:PTop  => config(n, mp)
      case n:PPCU  => config(n, mp)
      case n:PSCU  => config(n, mp)
      case n:PMCU  => config(n, mp)
      case n:POCU  => config(n, mp)
      case n:PMC   => config(n, mp)

      case n:PSRAM => config(n, mp)
      case n:PLMem => config(n, mp)
      case n:PCtr  => config(n, mp)
      case n:PST   => config(n, mp)

      case n:PUC   => config(n, mp)
      case n:PPDU  => config(n, mp)

      case n => mp 
    }
    mp.asInstanceOf[T]
  }

  var mp:M = _

  override def visitDown(x:Any):Iterable[Any] = {
    super.visitDown(x).filter {
      case c@(_:PCL | _:PMem | _:PCtr | _:PST | _:PCB) => 
        mp.pmmap.contains(c.asInstanceOf[PNode])
      case c:PUC => c.prt.isInstanceOf[POCU] || mp.pmmap.contains(c)
      case c:PPR => mp.fimap.contains(c.out) // LiveOut Config Initial value
      case _ => true
    }
  }

  override def traverseDown[T](n:Any, result:T):T = {
    super.traverseDown(n, config(n, result))
  }

  def map(map:M):M = {
    mp = map
    traverseDown(design.arch.top, mp)
    mp.pmmap.keys.foreach {
      case n:Const => mp = config(n, mp)
      case _ =>
    }
    mp
  }
}
