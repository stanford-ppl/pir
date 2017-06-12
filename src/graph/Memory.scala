package pir.graph

import pir.util._
import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import scala.reflect.runtime.universe._
import pir.{Design, Config}
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.pass.ForwardRef
import pir.util.misc._
import pir.mapper.PIRMap

abstract class OnChipMem(implicit override val ctrler:ComputeUnit, design:Design) extends Primitive {
  import pirmeta._
  val size:Int
  val banking:Banking

  val readPort: ReadOutPort = ReadOutPort(this, s"${this}.rp") 
  val writePort: WriteInPort = WriteInPort(this, s"${this}.wp")

  def isRemoteWrite = this match {
    case _:CommandFIFO => writePort.from.src.isInstanceOf[ScalarIn] 
    case _ => writePort.from.src.isInstanceOf[VecIn]
  } 

  def wtPort(wp:OutPort):this.type = { writePort.connect(wp); this } 
  def load = readPort

  def writer:Controller = writerOf(this)
  def readers:List[Controller] = readersOf(this)
  def isVFifo = this.isInstanceOf[VectorFIFO]
  def isSFifo = this.isInstanceOf[ScalarFIFO]
  def asVFifo = this.asInstanceOf[VectorFIFO]
  def isSRAM = this.isInstanceOf[SRAM]
}

trait SRAMOnRead extends MultiBuffering {
  val readAddr: RdAddrInPort = RdAddrInPort(this, s"${this}.ra")
  def rdAddr(ra:OutPort):this.type = { 
    readAddr.connect(ra); 
    ra.src match {
      case PipeReg(stage,r) =>
        throw PIRException(s"Currently don't support register to readAddr! sram:${this}")
      case _ =>
    }
    this
  } 
}
trait FIFOOnRead extends OnChipMem {
  /* Control Signals */
  val notEmpty = CtrlOutPort(this, s"$this.notEmpty")
  val dequeueEnable = CtrlInPort(this, s"$this.deqEn")
}

trait SRAMOnWrite extends MultiBuffering {
  val writeAddr: WtAddrInPort = WtAddrInPort(this, s"${this}.wa")
  def wtAddr(wa:OutPort):this.type = { 
    writeAddr.connect(wa)
    this 
  }
  def writeCtr = writeAddr.from.src.asInstanceOf[Counter].cchain.inner
}
trait FIFOOnWrite extends OnChipMem { ocm:OnChipMem =>
  var _wtStart:Option[OutPort] = None
  var _wtEnd:Option[OutPort] = None 
  def wtStart(op:OutPort):this.type = { _wtStart = Some(op); this }
  def wtEnd(op:OutPort):this.type = { _wtEnd = Some(op); this }
  def wtStart:Option[OutPort] = _wtStart
  def wtEnd:Option[OutPort] = _wtEnd 

  /* Control Signals */
  val notFull = CtrlOutPort(this, s"$this.notFull")
  val enqueueEnable = CtrlInPort(this, s"$this.enqEn")
  override def toUpdate = super.toUpdate

  def isOfsFIFO:Boolean = {
    ocm.ctrler match {
      case mc:MemoryController =>
        if (!mc.mctpe.isDense) false
        else mc.getFifo("offset") == this
      case _ => false
    }
  }
}

trait MultiBuffering extends OnChipMem {
  val design:Design
  var _producer:Controller = _
  var _consumer:Controller = _
  def producer:Controller = _producer
  def consumer:Controller = _consumer
  var trueDep:Boolean = true // Whether the consumer is a true dependency

  def producer[T](pd:T):this.type = {
    pd match {
      case pd:String =>
        design.updateLater(pd, (n:Node) => producer(n.asInstanceOf[Controller]))
      case pd:Controller =>
        this._producer = pd
        pd.produce(this)
    }
    this
  }
  def consumer[T](cs:T):this.type = {
    cs match {
      case cs:String =>
        design.updateLater(cs, (n:Node) => consumer(n.asInstanceOf[Controller]))
      case cs:Controller =>
        this._consumer = cs
        cs.consume(this)
    }
    this
  }
  def consumer[T](cs:T, trueDep:Boolean):this.type = {
    this.trueDep = trueDep
    consumer(cs)
  }

  var _buffering:Int = _
  def buffering = _buffering
  def buffering(buf:Int):this.type = { _buffering = buf; this }
  val swapRead = CtrlInPort(this, s"$this.swapRead")
  val swapWrite = CtrlInPort(this, s"$this.swapWrite")
}
trait FIFO extends OnChipMem with FIFOOnRead with FIFOOnWrite with LocalMem {
  override val typeStr = "FIFO"
  override val banking = Strided(1)
  def finalSize(mp:PIRMap):Int = {
    size + mp.rtmap(writePort.from.src)
  }
}

trait LocalMem extends OnChipMem {
  def reader:Controller = {
    val readers = super.readers
    assert(readers.size==1, s"local mem should only have 1 reader, ${this}, ${readers}")
    readers.head
  }
}
trait RemoteMem extends OnChipMem { self:VectorMem =>
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
 *  @param size: size of SRAM in all dimensions 
 *  @param banking: Banking mode of SRAM
 *  @param writeCtr: TODO what was this again? counter that controls the write enable and used to
 *  calculate write address?
 */
case class SRAM(name: Option[String], size: Int, banking:Banking)(implicit override val ctrler:MemoryPipeline, design: Design) 
  extends VectorMem with RemoteMem with SRAMOnRead with SRAMOnWrite {
  override val typeStr = "SRAM"
}
object SRAM {
  def apply(size:Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: Design): SRAM
    = SRAM(None, size, banking)
  def apply(name:String, size:Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: Design): SRAM
    = SRAM(Some(name), size, banking)
}
case class SemiFIFO(name: Option[String], size: Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: Design) 
  extends VectorMem with RemoteMem with SRAMOnRead with FIFOOnWrite {
  override val typeStr = "SemiFIFO"
}
object SemiFIFO {
  def apply(size:Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: Design): SemiFIFO
    = SemiFIFO(None, size, banking)
  def apply(name:String, size:Int, banking:Banking)(implicit ctrler:MemoryPipeline, design: Design): SemiFIFO
    = SemiFIFO(Some(name), size, banking)
}

class VectorFIFO(val name: Option[String], val size: Int)(implicit ctrler:ComputeUnit, design: Design) 
  extends VectorMem with FIFO {
  override val typeStr = "FIFO"
}
object VectorFIFO {
  def apply(size:Int)(implicit ctrler:ComputeUnit, design: Design): VectorFIFO
    = new VectorFIFO(None, size)
  def apply(name:String, size:Int)(implicit ctrler:ComputeUnit, design: Design): VectorFIFO
    = new VectorFIFO(Some(name), size)
}

trait ScalarMem extends OnChipMem with LocalMem {
  def wtPort(scalarIn:ScalarIn):this.type = { wtPort(scalarIn.out) }
  def wtPort(scalar:Scalar):this.type = { wtPort(ctrler.newSin(scalar)) }
  def wtPort(scalarOut:ScalarOut):this.type = { wtPort(scalarOut.scalar) }
}

case class ScalarBuffer(name:Option[String])(implicit ctrler:ComputeUnit, design: Design) 
  extends ScalarMem with MultiBuffering {
  override val typeStr = "ScalBuf"
  override val size = 1
  override val banking = NoBanking()
}
object ScalarBuffer {
  def apply()(implicit ctrler:ComputeUnit, design: Design):ScalarBuffer
    = ScalarBuffer(None)
  def apply(name:String)(implicit ctrler:ComputeUnit, design: Design):ScalarBuffer
    = ScalarBuffer(Some(name))
}

class ScalarFIFO(val name: Option[String], val size: Int)(implicit ctrler:ComputeUnit, design: Design) 
  extends ScalarMem with FIFO {
  override val typeStr = "ScalarFIFO"
}
object ScalarFIFO {
  def apply(size:Int)(implicit ctrler:ComputeUnit, design: Design): ScalarFIFO
    = new ScalarFIFO(None, size)
  def apply(name:String, size:Int)(implicit ctrler:ComputeUnit, design: Design): ScalarFIFO
    = new ScalarFIFO(Some(name), size)
}

case class CommandFIFO(mc:MemoryController)(implicit ctrler:InnerController, design: Design) 
  extends ScalarFIFO(Some(s"${mc}CommandFIFO"), 1000)
