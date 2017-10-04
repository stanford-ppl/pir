package pir.node

import pir._

import pirc._
import pirc.enums._

import scala.math.max
import scala.reflect.runtime.universe._

abstract class OnChipMem(implicit ctrler:Controller, design:PIR) extends Primitive {
  import pirmeta._
  ctrler.mems(List(this))

  val size:Int
  val banking:Banking

  val readPort: OutPort = OutPort(this, s"${this}.rp") 
  val writePort: InPort = InPort(this, s"${this}.wp")
  val writePortMux:Mux = new Mux().name(s"$this.wpMux")
  writePort.connect(writePortMux.out)
  /* Control Signals */
  val enqueueEnable = InPort(this, s"$this.enqEn")
  val dequeueEnable = InPort(this, s"$this.deqEn")
  val inc = InPort(this, s"$this.inc")
  val dec = InPort(this, s"$this.dec")
  val predicate = InPort(this, s"$this.predicate")
  val notFull = OutPort(this, s"$this.notFull")
  val notEmpty = OutPort(this, s"$this.notEmpty")

  def isRemoteWrite = this match {
    case _:CommandFIFO => writePort.from.src.isInstanceOf[ScalarIn] 
    case _ => writePort.from.src.isInstanceOf[VecIn]
  } 

  def wtPort(wp:OutPort):this.type = { writePortMux.addInput.connect(wp); this } 

  def load = readPort

  def readers:List[Controller] = readersOf(this)
  def writers:List[Controller] = writersOf(this)
  def isVFifo = this.isInstanceOf[VectorFIFO]
  def isSFifo = this.isInstanceOf[ScalarFIFO]
  def isFifo = this.isInstanceOf[FIFO]
  def asVFifo = this.asInstanceOf[VectorFIFO]
  def isSRAM = this.isInstanceOf[SRAM]
  def isMbuffer = this.isInstanceOf[MultiBuffer]
  def asMbuffer = this.asInstanceOf[MultiBuffer]

  def producer(writer:String, pd:String):this.type = {
    design.updateLater { producer(nameOf.find[Controller](writer), nameOf.find[Controller](pd)) }
    this
  }
  def producer(writer:Controller, pd:Controller):this.type = {
    producerOf((this, writer)) = pd
    this
  }
  def consumer(reader:String, cs:String):this.type = {
    design.updateLater { consumer(nameOf.find[Controller](reader), nameOf.find[Controller](cs)) }
    this
  }
  def consumer(reader:Controller, cs:Controller):this.type = {
    consumerOf((this, reader)) = cs
    this
  }

  var _buffering:Int = _
  def buffering = _buffering
  def buffering(buf:Int):this.type = { _buffering = buf; this }

  def isOfsFIFO:Boolean = {
    ctrler match {
      case mc:MemoryController =>
        if (!mc.mctpe.isDense) false
        else mc.getFifo("offset") == this
      case _ => false
    }
  }
}

trait MultiBuffer extends OnChipMem {}
trait FIFO extends OnChipMem {
  val banking = NoBanking()
}
trait LocalMem extends OnChipMem {
  def reader:Controller = {
    val readers = super.readers
    assert(readers.size==1, s"local mem should only have 1 reader, ${this}, ${readers}")
    readers.head
  }
}
trait RemoteMem extends OnChipMem { self:VectorMem =>
  def rdPort(scalar:Scalar):this.type = { rdPort(ctrler.newSout(scalar)) }
  def rdPort(scalarOut:ScalarOut):this.type = { scalarOut.in.connect(readPort); this }
  def rdPort(vec:Vector):this.type = { rdPort(ctrler.newVout(vec)) }
  def rdPort(vecOut:VecOut):this.type = { vecOut.in.connect(readPort); this }
  //override def wtPort(vecIn:VecIn):this.type = { // Move this insertion to spatial
    //val fifo = ctrler.getRetimingFIFO(vecIn.vector)
    //fifo.wtPort(vecIn.out)
    //wtPort(fifo.load)
  //}
}

trait VectorMem extends OnChipMem {
  def wtPort(vecIn:VecIn):this.type = { wtPort(vecIn.out) }
  def wtPort(vec:Vector):this.type = { wtPort(ctrler.newVin(vec)) }
  def wtPort(vecOut:VecOut):this.type = { wtPort(vecOut.vector) }
}

/** SRAM 
 *  @param name: user defined optional name of SRAM 
 *  @param size: size of each bank 
 *  @param banking: Banking mode of SRAM
 *  calculate write address?
 */
case class SRAM(size: Int, banking:Banking)(implicit override val ctrler:MemoryPipeline, design: PIR) 
  extends VectorMem with RemoteMem with MultiBuffer {
  override val typeStr = "SRAM"
  def banks = banking match {
    case Strided(stride, banks) => banks
    case Diagonal(_, _) => throw PIRException(s"Not supporting diagnoal banking at the moment")
    case NoBanking() => 1
    case Duplicated() => throw PIRException(s"Shouldn't matching Duplicated. No support in pirgen yet")
  }
  val readAddr: InPort = InPort(this, s"${this}.ra")
  def rdAddr(ra:OutPort):this.type = { 
    readAddrMux.addInput.connect(ra); 
    this
  } 
  val writeAddr: InPort = InPort(this, s"${this}.wa")
  def wtAddr(wa:OutPort):this.type = { 
    writeAddrMux.addInput.connect(wa)
    this 
  }

  val readAddrMux:Mux = new Mux().name(s"$this.raMux")
  readAddr.connect(readAddrMux.out)
  val writeAddrMux:Mux = Mux().name(s"$this.waMux")
  writeAddr.connect(writeAddrMux.out)
}

object SRAM {
  def apply(name:String, size:Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: PIR): SRAM
    = SRAM(size, banking).name(name)
}

case class VectorFIFO(size: Int)(implicit ctrler:Controller, design: PIR) 
  extends VectorMem with FIFO {
  override val typeStr = "FIFO"
}
object VectorFIFO {
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: PIR): VectorFIFO
    = new VectorFIFO(size).name(name)
}

trait ScalarMem extends OnChipMem with LocalMem {
  def wtPort(scalarIn:ScalarIn):this.type = { wtPort(scalarIn.out) }
  def wtPort(scalar:Scalar):this.type = { wtPort(ctrler.newSin(scalar)) }
  def wtPort(scalarOut:ScalarOut):this.type = { wtPort(scalarOut.scalar) }
}

case class ScalarBuffer()(implicit ctrler:Controller, design: PIR) 
  extends ScalarMem with MultiBuffer {
  override val typeStr = "ScalBuf"
  override val size = 1
  override val banking = NoBanking()
}
object ScalarBuffer {
  def apply(name:String)(implicit ctrler:Controller, design: PIR):ScalarBuffer
    = ScalarBuffer().name(name)
}

class ScalarFIFO(val size: Int)(implicit ctrler:Controller, design: PIR) 
  extends ScalarMem with FIFO {
  override val typeStr = "ScalarFIFO"
}
object ScalarFIFO {
  def apply(size:Int)(implicit ctrler:Controller, design: PIR): ScalarFIFO
    = new ScalarFIFO(size)
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: PIR): ScalarFIFO
    = new ScalarFIFO(size).name(name)
}

case class CommandFIFO(mc:MemoryController)(implicit ctrler:InnerController, design: PIR) 
  extends ScalarFIFO(1000) {
    this.name(s"${mc}CommandFIFO")
  }
