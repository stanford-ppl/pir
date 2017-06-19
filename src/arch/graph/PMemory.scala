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

trait OnChipMem extends Primitive with Simulatable {
  import spademeta._
  val size:Int
  type P<:PortType
  val readPort:Output[P, OnChipMem]
  val writePort:Input[P, OnChipMem]
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
      case mem:pir.graph.FIFO => mem.finalSize(sim.mapping) + 10 //TODO make this huge as a start
      case mem:pir.graph.MultiBuffering => mem.buffering
    }
  }
  def clearArray:Unit
  var updatedArray = false
  final def updateArray(implicit sim:Simulator):Unit = {
    import sim.util._
    if (updatedArray) return
    //emitBlock(s"UpdateArray ${quote(this)} #$cycle") {
      mainUpdateArray
    //}
    updatedArray = true
  }
  def mainUpdateArray(implicit sim:Simulator):Unit
  override def updateModule(implicit sim:Simulator):Unit = {
    super.updateModule
    if (isMapped(this)(sim.mapping)) {
      updateArray
    }
  }
  override def clearModule(implicit sim:Simulator):Unit = {
    super.clearModule
    updatedArray = false
  }
  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      def incPtr(v:WordValue) = {
        v <<= v + 1; if (v.value.get.toInt>=bufferSize) v <<= 0
      }
      readPtr.v <<= 0
      writePtr.v <<= 0
      count.v <<= 0
      readPtr.v.set { v => If (incReadPtr.pv) { incPtr(v) } }
      writePtr.v.set { v => If (incWritePtr.pv) { incPtr(v) }; updateArray }
      count.v.set { v => If (incReadPtr.pv) { v <<= v - 1 }; If (incWritePtr.pv) { v <<= v + 1 } }
    }
    super.register
  }
}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(size:Int)(implicit spade:Spade, pne:ComputeUnit) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  type P = Bus 
  var array:Array[Array[Word]] = _
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val writeEn = Input(Bit(), this, s"${this}.we")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val readOut = Output(Bus(Word()), this, s"${this}.ro")
  //val debug = Output(Bus(spade.numLanes * 2, Word()), this, s"${this}.debug")
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
  val swapWrite = Output(Bit(), this, s"${this}.swapWrite")
  def mainUpdateArray(implicit sim:Simulator):Unit = {
    writePtr.pv.update.value.foreach { writePtr => 
      writeAddr.pv.update.value.foreach { writeAddr =>
        writePort.pv.update.foreach { case (writePort, i) =>
          If (writeEn.pv) {
            array(writePtr.toInt)(writeAddr.toInt + i) <<= writePort
          }
        }
      }
    }
    //debug.v.update
  }
  def clearArray:Unit = {
    if (array==null) return
    array.foreach { _.foreach { _ <<= 0 } }
  }
  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      array = Array.tabulate(bufferSize, size) { case (i,j) => Word(s"$this.array[$i,$j]") }
      readOut.v.set { v => 
        updateArray
        readAddr.v.update.value.foreach { rdhead =>
          v.foreach { case (ev, i) =>
            ev <<= array(readPtr.v.update.value.get.toInt)(rdhead.toInt + i)
          }
        }
      }
      readPort.v := readOut.pv
      swapWrite := incWritePtr
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

  def array:Array[P]

  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      readPort.v.set { v => 
        updateArray
        v <<= array(readPtr.v.update.value.get.toInt)
      }
      notEmpty.v := count.v > 0
      notFull.v := count.v < bufferSize //TODO: implement almost full
    }
    super.register
  }
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "sm"
  type P = Word
  var array:Array[P] = _
  val writePort = Input(Word(), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
  def mainUpdateArray(implicit sim:Simulator):Unit = {
    writePtr.pv.update.value.foreach { pv => array(pv.toInt) <<= writePort.pv }
  }
  def clearArray:Unit = {
    if (array==null) return
    array.foreach { _ <<= 0 }
  }
  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      array = Array.tabulate(bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
    }
    super.register
  }
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "vm"
  type P = Bus
  def clearArray:Unit = {
    if (array==null) return
    array.foreach { _.foreach { case (v:WordValue, i) => v <<= 0 } }
  }
  def mainUpdateArray(implicit sim:Simulator):Unit = {
    If (writePort.pv.update.valid) { //TODO: if valid is X, output should be X
      writePtr.pv.update.value.foreach { pv => array(pv.toInt) <<= writePort.pv }
    }
  }
  var array:Array[P] = _
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
  val readPort = Output(Bus(Word()), this, s"${this}.rp") 
  override def register(implicit sim:Simulator):Unit = {
    import sim.mapping._
    smmap.pmap.get(this).foreach { mem =>
      array = Array.tabulate(bufferSize) { i => readPort.tp.clone(s"$this.array[$i]") }
      incWritePtr.v.set { _ <<= writePort.v.update.valid }
    }
    super.register
  }
}

