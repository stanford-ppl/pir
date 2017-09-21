package spade.node

import spade._
import spade.util._

import pirc.enums._
import pirc.util._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer

trait MemoryConfig extends Configuration {
  val name:String
}
trait Memory extends Module with Simulatable with Configurable {
  type CT <: MemoryConfig
  val size:Int
  var updatedMemory = false
  type M
  def memory:M
  private val memoryFuncs = ListBuffer[(M => Unit, String)]()
  def setMem (f:M => Unit)(implicit sim:Simulator):Unit = {
    assert(!sim.inSimulation)
    val stackTrace = getStackTrace(1, 20)
    memoryFuncs += ((f,stackTrace))
  }
  final def updateMemory(config:MemoryConfig)(implicit sim:Simulator):Unit = {
    import sim.util._
    if (!updatedMemory) {
      memoryFuncs.foreach { case (f, stackTrace) => 
        updatedMemory = true
        try {
          f(memory)
        } catch {
          case e:Exception =>
            val info = this match {
              case mem:OnChipMem => s"${quote(mem.prt)}.${quote(mem)}(${config.name})" //TODO
              case mem:DRAM => s"dram"
            }
            errmsg(s"[$info #$cycle]: ${e.toString}")
            errmsg(e.getStackTrace.slice(0,15).mkString("\n"))
            errmsg(s"\nStaged trace for $this: ")
            errmsg(stackTrace)
            throw e
        }
      }
    }
  }
  override def updateModule(implicit sim:Simulator):Unit = {
    import sim.util._
    super.updateModule
    cfmap.get(this).foreach { config => updateMemory(config) }
  }
  override def clearModule(implicit sim:Simulator):Unit = {
    super.clearModule
    updatedMemory = false
  }
  def zeroMemory(implicit sim:Simulator):Unit
  override def zeroModule(implicit sim:Simulator):Unit = {
    super.zeroModule
    zeroMemory
  }
}

case class DRAMConfig() extends MemoryConfig { val name="dram" }
case class DRAM(size:Int)(implicit spade:Spade) extends Memory with Simulatable {
  import spademeta._
  override val typeStr = "dram"
  type M = Array[Word]
  val memory = Array.tabulate(size) { i => Word(s"$this.array[$i]") }
  val dramDefault:Array[AnyVal] = Array.tabulate(size) { i => i }

  def getValue:Array[Option[AnyVal]] = memory.map(_.value)

  val config = DRAMConfig()

  def updateMemory(implicit sim:Simulator):Unit = super.updateMemory(config)(sim)

  override def register(implicit sim:Simulator):Unit = {
    memory.zipWithIndex.foreach { case (v, i) => v.default = dramDefault(i) }
  }

  def zeroMemory(implicit sim:Simulator):Unit = {
    memory.foreach(_.zero)
  }
}

trait OnChipMemConfig extends MemoryConfig {
  val backPressure:Boolean
  val bufferSize:Int
  val notFullOffset:Int
}

abstract class OnChipMem(implicit spade:Spade, ctrler:Controller) extends Primitive with Memory {
  type CT <: OnChipMemConfig
  import spademeta._
  type P<:PortType

  def wtp:Bus
  val readPort:Output[_<:PortType, OnChipMem]
  val writePort = Input(wtp, this, s"${this}.wp")
  val writePortMux = Mux(s"$this.wpMux", wtp)
  val dequeueEnable = Input(Bit(), this, s"${this}.deqEn")
  val enqueueEnable = Input(Bit(), this, s"${this}.enqEn")
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val notEmpty = Output(Bit(), this, s"${this}.notEmpty")
  val notFull = Output(Bit(), this, s"${this}.notFull")
  val writePtr = Output(Word(), this, s"${this}.writePtr")
  val readPtr = Output(Word(), this, s"${this}.readPtr")
  val count = Output(Word(), this, s"${this}.count")
  val actualCount = Output(Word(), this, s"${this}.actualCount")
  def asSRAM = this.asInstanceOf[SRAM]
  def asVBuf = this.asInstanceOf[VectorMem]
  def asSBuf = this.asInstanceOf[ScalarMem]
  def asBuf = this.asInstanceOf[LocalMem]
  writePort <== writePortMux.out

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { config =>
      notEmpty.v.default = false
      notFull.v.default = true
      readPtr.v.default = 0
      writePtr.v.default = 0
      dequeueEnable.v.default = false
      enqueueEnable.v.default = false
      count.v.default = 0
      actualCount.v.default = 0
      this match {
        case mem:SRAM =>
          notEmpty.v := count.v > 0
        case mem:LocalMem =>
          notEmpty.v := eval(BitOr, mem.predicate.v, (count.v > 0))
      }
      if (!config.backPressure) {
        notFull.v := true
      } else {
        notFull.v := count.v < (config.bufferSize - config.notFullOffset)
      }
      def incPtr(v:SingleValue) = {
        v <<= v + 1; if (v.toInt>=config.bufferSize) v <<= 0
      }
      readPtr.v.set { v => If (dequeueEnable.pv) { incPtr(v) } }
      writePtr.v.set { v => If (enqueueEnable.pv) { incPtr(v) }; updateMemory(config) }
      if (!enqueueEnable.isConnected) { enqueueEnable.v := writePort.v.update.valid }
      if (!inc.isConnected) { inc.v := enqueueEnable.v }
      count.v.set { v => 
        If (dec.pv) { 
          if (sim.inSimulation && v.toInt==0) 
            warn(s"${quote(prt)}.${quote(this)}(${config.name}) underflow at #$cycle!")
          v <<= v - 1
        }
        If (inc.pv) { 
          if (sim.inSimulation && (v.toInt >= config.bufferSize) && config.backPressure)
            warn(s"${quote(prt)}.${quote(this)}(${config.name}) overflow at $cycle!")
          v <<= v + 1
        }
      }
      actualCount.v.set { v => 
        If (dequeueEnable.pv) { 
          if (sim.inSimulation && v.toInt==0) 
            warn(s"${quote(prt)}.${quote(this)}(${config.name}) silent underflow at #$cycle!")
          v <<= v - 1
        }
        If (enqueueEnable.pv) { 
          if (sim.inSimulation && (v.toInt >= config.bufferSize) && config.backPressure)
            warn(s"${quote(prt)}.${quote(this)}(${config.name}) silent overflow at $cycle!")
          v <<= v + 1
        }
      }
      writePort.v.valid.default = false
    }
  }
}

case class SRAMConfig (
  name:String,
  rpar:Int,
  wpar:Int,
  banking:Banking,
  bufferSize:Int, // number of buffers for multibuffer 
  notFullOffset:Int,
  backPressure:Boolean,
  size:Int // size of each buffer 
) extends OnChipMemConfig
/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(size:Int, banks:Int)(implicit spade:Spade, prt:Controller) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  type CT = SRAMConfig
  type P = Bus 
  type M = Array[Array[Word]]
  def wtp:Bus = Bus(Word())
  var memory:M = _
  val readAddr = Input(Word(), this, s"${this}.ra")
  val readAddrMux = Mux(s"$this.raMux", Word()) //TODO: connect select for mux
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val writeAddrMux = Mux(s"$this.waMux", Word())
  val writeEn = Input(Bit(), this, s"${this}.we")
  val readEn = Input(Bit(), this, s"${this}.re")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val readOut = Output(Bus(Word()), this, s"${this}.ro")
  val DEBUG = Output(Bus(2*spade.numLanes, Word()), this, s"${this}.DEBUG")

  readAddr <== readAddrMux.out
  writeAddr <== writeAddrMux.out
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.foreach { _.zero } }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.spademeta._
    import sim.util._
    cfmap.get(this).foreach { config =>
      memory = Array.tabulate(config.bufferSize, config.size) { case (i,j) => Word(s"$this.array[$i,$j]") }

      writeEn.v.default = false
      readEn.v.default = false
      readOut.v.valid.default = false

      setMem { memory =>
        If (writeEn.pv) {
          writePort.pv.foreach { 
            case (writePort, i) if i < config.wpar =>
              writeAddr.pv.getInt.foreach { writeAddr =>
                memory(writePtr.pv.toInt)(writeAddr + i) <<= writePort
              }
            case (writePort, i) =>
          }
        }
        //DEBUG.v.update
      }
      def calcReadAddr(ra:Int, i:Int) = config.banking match {
        case Diagonal(_,_) => throw PIRException(s"Not supporting diagonal banking at the moment")
        case Strided(stride, banks) => ra + i * stride
        case Duplicated() => ra
        case NoBanking() => ra
      }
      readOut.v.set { v => 
        updateMemory(config)
        v.foreach { 
          case (ev, i) if i < config.rpar =>
            readAddr.v.getInt.fold {
              ev.asSingle <<= None
            } { readAddr =>
              ev <<= memory(readPtr.v.toInt)(calcReadAddr(readAddr, i))
            }
          case _ =>
        }
      }
      readPort.v := readOut.pv
      // --- DEBUG ---
      DEBUG.v.set { v =>
        updateMemory(config)
        v.foreach { case (ev, i) =>
          ev <<= memory(0)(i)
        }
      }
    }
    super.register
  }
}

case class LocalMemConfig (
  name:String,
  isFifo:Boolean,
  backPressure:Boolean,
  notFullOffset:Int,
  bufferSize:Int // number of buffers for multibuffer 
) extends OnChipMemConfig
/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalMem extends OnChipMem {
  type M = Array[P]
  type CT = LocalMemConfig

  val predicate = Input(Bit(), this, s"${this}.predicate")

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import sim.spademeta._
    cfmap.get(this).foreach { config =>
      readPort.v.set { v => 
        updateMemory(config)
        v <<= memory(readPtr.v.toInt)
      }
      if (config.isFifo) {
        fanInOf(dequeueEnable).foreach { dequeueEnable.v := _.v.asSingle & predicate.v.not }
        fanInOf(dec).foreach { dec.v := _.v.asSingle & predicate.v.not }
      }
    }
    super.register
  }
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem(size:Int)(implicit spade:Spade, prt:Controller) extends LocalMem {
  import spademeta._
  override val typeStr = "sm"
  type P = Word
  var memory:Array[P] = _
  def wtp = Bus(1, Word())
  val readPort = Output(Word(), this, s"${this}.rp")
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.zero }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { config =>
      memory = Array.tabulate(config.bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
      setMem { memory => memory(writePtr.pv.toInt) <<= writePort.pv.head }
    }
    super.register
  }
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem(size:Int)(implicit spade:Spade, prt:Controller) extends LocalMem {
  import spademeta._
  override val typeStr = "vm"
  type P = Bus
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.foreach { case (v:SingleValue, i) => v.zero } }
  }
  var memory:Array[P] = _
  def wtp = Bus(1, Bus(Word()))
  val readPort = Output(Bus(Word()), this, s"${this}.rp") 
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { config =>
      memory = Array.tabulate(config.bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
      setMem { memory => memory(writePtr.pv.toInt) <<= writePort.pv }
    }
    super.register
  }
}

