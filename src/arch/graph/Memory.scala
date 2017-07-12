package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

trait Memory extends Module with Simulatable {
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
  final def updateMemory(implicit sim:Simulator):Unit = {
    import sim.util._
    if (!updatedMemory) {
      memoryFuncs.foreach { case (f, stackTrace) => 
        updatedMemory = true
        try {
          f(memory)
        } catch {
          case e:Exception =>
            val info = this match {
              case mem:OnChipMem => s"${quote(mem.prt)}.${quote(mem)}(${smmap.pmap.get(mem)})"
              case mem:DRAM => s"dram"
            }
            errmsg(s"[$info]: ${e.toString}")
            errmsg(e.getStackTrace.slice(0,15).mkString("\n"))
            errmsg(s"\nStaged trace for $this: ")
            errmsg(stackTrace)
            throw e
        }
      }
    }
  }
  override def updateModule(implicit sim:Simulator):Unit = {
    super.updateModule
    if (isMapped(this)(sim.mapping)) {
      updateMemory
    }
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

case class DRAM(size:Int)(implicit spade:Spade) extends Memory with Simulatable {
  import spademeta._
  override val typeStr = "dram"
  type M = Array[Word]
  val memory = Array.tabulate(size) { i => Word(s"$this.array[$i]") }

  def getValue:Array[Option[AnyVal]] = memory.map(_.value)

  override def register(implicit sim:Simulator):Unit = {
    memory.zipWithIndex.foreach { case (v, i) => v.default = i }
  }

  def zeroMemory(implicit sim:Simulator):Unit = {
    memory.foreach(_.zero)
  }
}

trait OnChipMem extends Primitive with Memory {
  import spademeta._
  type P<:PortType
  val readPort:Output[_<:PortType, OnChipMem]
  val writePort:Input[_<:PortType, OnChipMem]
  val incReadPtr = Input(Bit(), this, s"${this}.incReadPtr")
  val incWritePtr = Input(Bit(), this, s"${this}.incWritePtr")
  val writePtr = Output(Word(), this, s"${this}.writePtr")
  val readPtr = Output(Word(), this, s"${this}.readPtr")
  val count = Output(Word(), this, s"${this}.count")
  def asSRAM = this.asInstanceOf[SRAM]
  def asVBuf = this.asInstanceOf[VectorMem]
  def asSBuf = this.asInstanceOf[ScalarMem]
  def asBuf = this.asInstanceOf[LocalBuffer]
  def bufferSize(implicit sim:Simulator) = {
    val mem = sim.mapping.smmap.pmap(this)
    mem match {
      case mem:pir.graph.FIFO => mem.finalSize(sim.mapping) + 16 //TODO make this huge as a start
      case mem:pir.graph.MultiBuffering => mem.buffering
    }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      def incPtr(v:SingleValue) = {
        v <<= v + 1; if (v.toInt>=bufferSize) v <<= 0
      }
      readPtr.v.default = 0
      writePtr.v.default = 0
      count.v.default = 0
      if (mem.isMbuffer && mem.asMbuffer.buffering <= 1) {
        incReadPtr.v := false
        incWritePtr.v := false
      }
      incReadPtr.pv; incWritePtr.pv
      readPtr.v.set { v => If (incReadPtr.pv) { incPtr(v) } }
      writePtr.v.set { v => If (incWritePtr.pv) { incPtr(v) }; updateMemory }
      count.v.set { v => 
        If (incReadPtr.pv) { 
          if (v.value==Some(0)) warn(s"${quote(prt)}.${quote(this)}'s count underflow at #$cycle!")
          v <<= v - 1
        }
        If (incWritePtr.pv) { v <<= v + 1 }
      }
    }
    super.register
  }
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(size:Int)(implicit spade:Spade, prt:Controller) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  type P = Bus 
  type M = Array[Array[Word]]
  var memory:M = _
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val writeEn = Input(Bit(), this, s"${this}.we")
  val writeEnDelay = Input(Bit(), this, s"${this}.wed")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val readOut = Output(Bus(Word()), this, s"${this}.ro")
  //val debug = Output(Bus(spade.numLanes * 2, Word()), this, s"${this}.debug")
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
  val writePortDelay = Output(Bus(Word()), this, s"${this}.wpd")
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.foreach { _.zero } }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.util._
    smmap.pmap.get(this).foreach { mem =>
      memory = Array.tabulate(bufferSize, size) { case (i,j) => Word(s"$this.array[$i,$j]") }
      val wdelay = rtmap(writePort)
      writePortDelay.v := writePort.vAt(wdelay)
      writeEnDelay.v := writeEn.vAt(wdelay)
      setMem { memory =>
        writePtr.pv
        writeAddr.pv
        writePortDelay.pv
        writeEnDelay.pv
        writePtr.v.default = 0
        If (writeEnDelay.pv) {
          writePortDelay.pv.foreach { 
            case (writePort, i) if i < wparOf(mem) =>
              writeAddr.pv.getInt.foreach { writeAddr =>
                memory(writePtr.pv.toInt)(writeAddr + i) <<= writePort
              }
            case (writePort, i) =>
          }
        }
        //debug.v.update
      }
      readPtr.v.default = 0
      readOut.v.set { v => 
        updateMemory
        v.foreach { 
          case (ev, i) if i < rparOf(mem) =>
            readAddr.v.getInt.foreach { readAddr =>
              ev <<= memory(readPtr.v.toInt)(readAddr + i)
            }
          case _ =>
        }
      }
      readPort.v := readOut.pv
      //debug.v.set { v =>
        //v.foreach { case (ev, i) =>
          //ev <<= array(0)(i)
        //}
      //}
    }
    super.register
  }
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalBuffer extends OnChipMem {
  val notEmpty = Output(Bit(), this, s"${this}.notEmpty")
  val notFull = Output(Bit(), this, s"${this}.notFull")
  type M = Array[P]

  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      readPort.v.set { v => 
        updateMemory
        v <<= memory(readPtr.v.toInt)
      }
      notEmpty.v := count.v > 0
      notFull.v := count.v < bufferSize //TODO: implement almost full
    }
    super.register
  }
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem(size:Int)(implicit spade:Spade, prt:Routable) extends LocalBuffer {
  override val typeStr = "sm"
  type P = Word
  var memory:Array[P] = _
  val writePort = Input(Bus(1,Word()), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.zero }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    smmap.pmap.get(this).foreach { mem =>
      memory = Array.tabulate(bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
      writePtr.pv
      writePort.pv
      setMem { memory => memory(writePtr.pv.toInt) <<= writePort.pv.head }
      if (mem.isSFifo) {
        incWritePtr.v.set { v => 
          If(notFull.v.not) { warn(s"${quote(prt)}.${quote(this)} overflow at $cycle!") }
          v <<= writePort.v.update.valid
        }
      }
      writePtr.v.default = 0
    }
    super.register
  }
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem(size:Int)(implicit spade:Spade, prt:Routable) extends LocalBuffer {
  override val typeStr = "vm"
  type P = Bus
  def zeroMemory(implicit sim:Simulator):Unit = {
    if (memory==null) return
    memory.foreach { _.foreach { case (v:SingleValue, i) => v.zero } }
  }
  var memory:Array[P] = _
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
  val readPort = Output(Bus(Word()), this, s"${this}.rp") 
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    smmap.pmap.get(this).foreach { mem =>
      memory = Array.tabulate(bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
      writePtr.pv
      writePort.pv
      setMem { memory =>
        If (writePort.pv.update.valid) { //TODO: if valid is X, output should be X
          If(notFull.v.not) { warn(s"${quote(prt)}.${quote(this)} overflow at $cycle!") }
          memory(writePtr.pv.toInt) <<= writePort.pv
        }
      }
      assert(mem.isVFifo)
      incWritePtr.v.set { v =>
        v <<= writePort.v.update.valid
      }
      writePtr.v.default = 0
    }
    super.register
  }
}

