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

trait OnChipMem extends Primitive {
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
  def createArray(capacity:Int):Array[P]

}

/** Physical SRAM 
 *  @param numPort: number of banks. Usually equals to number of lanes in CU */
case class SRAM(size:Int)(implicit spade:Spade, pne:ComputeUnit) extends OnChipMem {
  import spademeta._
  override val typeStr = "sram"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  type P = Bus 
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
  val readAddr = Input(Word(), this, s"${this}.ra")
  val writeAddr = Input(Word(), this, s"${this}.wa")
  val readPort = Output(Bus(Word()), this, s"${this}.rp")
  val writePort = Input(Bus(Word()), this, s"${this}.wp")
}

/* Scalar Buffer between the bus inputs/outputs and first/last stage */
trait LocalBuffer extends OnChipMem with Simulatable {
  val notEmpty = Output(Bit(), this, s"${this}.notEmpty")
  val notFull = Output(Bit(), this, s"${this}.notFull")

  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.smmap.pmap.get(this).foreach { mem =>
      val capacity = mem match {
        case mem:pir.graph.FIFO => mem.size
        case mem:pir.graph.MultiBuffering => mem.buffering
      }
      val array:Array[P] = createArray(capacity)
      def incPtr(v:WordValue) = {
        v <<= v + 1; if (v.value.get.toInt>=capacity) v <<= 0
      }
      readPtr.v <<= 0
      writePtr.v <<= 0
      count.v <<= 0
      readPtr.v.set { v => If (incReadPtr.v) { incPtr(v) } }
      writePtr.v.set { v => If (incWritePtr.v) { incPtr(v) }; array(v.value.get.toInt) <<= writePort.v }
      count.v.set { v => If (incReadPtr.v) { v <<= v - 1 }; If (incWritePtr.v) { v <<= v + 1 } }
      readPort.v.set { v => 
        writePtr.v.update
        readPtr.v.update
        v <<= array(readPtr.v.value.get.toInt)
      }
      notEmpty.v := count.v > 0
      notFull.v := count.v < capacity //TODO: implement almost full
    }
    super.register
  }
}

/* Scalar buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class ScalarMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "sm"
  type P = Word
  val writePort = Input(Word(), this, s"${this}.wp")
  val readPort = Output(Word(), this, s"${this}.rp")
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
}
/* Vector buffer between bus input and the empty stage. (Is an IR but doesn't physically 
 * exist). Input connects to 1 out port of the InBus */
case class VectorMem(size:Int)(implicit spade:Spade, pne:NetworkElement) extends LocalBuffer {
  override val typeStr = "vm"
  type P = Bus
  val writePort = Input(Bus(Word()), this, s"${this}.in")
  val readPort = Output(Bus(Word()), this, s"${this}.out") 
  def createArray(capacity:Int):Array[P] = Array.tabulate(capacity) { i => readPort.tp.clone }
}

