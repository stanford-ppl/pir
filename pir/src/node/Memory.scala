package pir.node

import pir._
import pir.util._

import pirc._
import pirc.enums._

import scala.math.max
import scala.reflect.runtime.universe._
import scala.collection.mutable

abstract class OnChipMem(implicit val ctrler:Controller, design:PIR) extends Primitive {
  import pirmeta._
  ctrler.mems(List(this))

  var size:Int = 1
  def size(s:Int):this.type = { size = s; this }
  var banking:Banking = NoBanking()
  def banking(b:Banking):this.type = { banking = b; this }

  val readPort: Output = Output(this, s"${this}.rp") 
  val writePort: Input = Input(this, s"${this}.wp")
  val writePortMux= new ValidMux().name(s"${this}.wpMux")
  writePort.connect(writePortMux.out)
  /* Control Signals */
  val enqueueEnable = Input(this, s"$this.enqEn")
  val dequeueEnable = Input(this, s"$this.deqEn")
  val predicate = Input(this, s"$this.predicate")
  val notFull = Output(this, s"$this.notFull")
  val notEmpty = Output(this, s"$this.notEmpty")

  def readPort(data:Any):Output = {
    data match {
      case data:Input => readPort.connect(data); readPort
      case data:GlobalOutput => readPort(data.in)
      case data:Variable => readPort(ctrler.newOut(data))
    }
  }

  def writePort(data:Any):Input = {
    data match {
      case data:Output => val in = writePortMux.addInput; in.connect(data); in
      case data:GlobalInput => writePort(data.out)
      case data:Variable => writePort(ctrler.newIn(data))
      case data:OnChipMem => writePort(data.readPort)
    }
  }

  def readAddr(addr:Any):Input = throw PIRException(s"$this does not have readAddress")
  def writeAddr(addr:Any):Input = throw PIRException(s"$this does not have writeAddress")

  val addrMap = mutable.Map[IO, IO]()
  val topCtrlMap = mutable.Map[IO, Controller]()
  // For write
  // - with addr: data -> Controller, addr -> Controller
  // - without addr: data -> Controller
  // For read:
  // - with addr: addr -> Controller (a single data but multiple Controller)
  // - without addr: data -> Controller

  def setTopCtrl(data:IO, addr:Option[IO], topCtrl:Option[Any]):Unit = {
    topCtrl.foreach {
      case name:String =>
        design.lazyUpdate { 
          val topCtrl = nameOf.find[Controller](name)
          setTopCtrl(data, addr, Some(topCtrl))
        }
      case topCtrl:Controller =>
        topCtrlMap += data -> topCtrl
        addr.foreach { addr => topCtrlMap += addr -> topCtrl }
    }
  }

  def load(data:Any, addr:Option[Any], topCtrl:Option[Any]):this.type = {
    val rdata = readPort(data)
    val raddr = addr.map(readAddr)
    raddr.foreach { raddr =>
      addrMap += raddr -> rdata
      addrMap += rdata -> raddr
    }
    setTopCtrl(rdata, raddr, topCtrl)
    this
  }

  def store(data:Any, addr:Option[Any], topCtrl:Option[Any]):this.type = {
    val wdata = writePort(data)
    val waddr = addr.map(writeAddr)
    waddr.foreach { waddr =>
      addrMap += waddr -> wdata
      addrMap += wdata -> waddr
    }
    setTopCtrl(wdata, waddr, topCtrl)
    this
  }

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

  var _buffering:Int = 1
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
trait FIFO extends OnChipMem
trait LocalMem extends OnChipMem {
  def reader:Controller = {
    val readers = super.readers
    assert(readers.size==1, s"local mem should only have 1 reader, ${this}, ${readers}")
    readers.head
  }
}
trait RemoteMem extends OnChipMem
trait VectorMem extends OnChipMem
/** SRAM 
 *  @param name: user defined optional name of SRAM 
 *  @param size: size of each bank 
 *  calculate write address?
 */
class SRAM()(implicit override val ctrler:MemoryPipeline, design: PIR) 
  extends VectorMem with RemoteMem with MultiBuffer {
  override val typeStr = "SRAM"
  def banks = banking match {
    case Strided(stride, banks) => banks
    case Diagonal(_, _) => throw PIRException(s"Not supporting diagnoal banking at the moment")
    case NoBanking() => 1
    case Duplicated() => throw PIRException(s"Shouldn't matching Duplicated. No support in pirgen yet")
  }

  var _mode:MemMode = SramMode
  def mode = _mode
  def mode(m:MemMode):this.type = { _mode = m; this}

  val readAddr: Input = Input(this, s"${this}.ra")
  override def readAddr(addr:Any):Input = { 
    addr match {
      case addr:Output => val in = readAddrMux.addInput; in.connect(addr); in
      case addr:Counter => readAddr(addr.out)
      case data:OnChipMem => writePort(data.readPort)
    }
  } 

  val writeAddr: Input = Input(this, s"${this}.wa")
  override def writeAddr(addr:Any):Input = { 
    addr match {
      case addr:Output => val in = writeAddrMux.addInput; in.connect(addr); in
      case addr:Counter => writeAddr(addr.out)
      case data:OnChipMem => writePort(data.readPort)
    }
  }

  val readAddrMux = new ValidMux().name(s"$this.raMux")
  readAddr.connect(readAddrMux.out)
  val writeAddrMux = ValidMux().name(s"$this.waMux")
  writeAddr.connect(writeAddrMux.out)
}
object SRAM {
  def apply(size:Int, name:String, banking:Banking)(implicit ctrler:MemoryPipeline, design: PIR):SRAM = {
    new SRAM().size(size).name(name).banking(banking)
  }
}

class VectorFIFO()(implicit ctrler:Controller, design: PIR) 
  extends VectorMem with FIFO with LocalMem {
  override val typeStr = "FIFO"
}
object VectorFIFO {
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: PIR): VectorFIFO
    = new VectorFIFO().size(size).name(name)
}

trait ScalarMem extends OnChipMem with LocalMem

class ScalarBuffer()(implicit ctrler:Controller, design: PIR) 
  extends ScalarMem with MultiBuffer {
  override val typeStr = "ScalBuf"
}
object ScalarBuffer {
  def apply(name:String)(implicit ctrler:Controller, design: PIR):ScalarBuffer
    = new ScalarBuffer().name(name)
  def clone(orig:ScalarBuffer, logger:Option[Logger] = None)(implicit ctrler:Controller, design: PIR) = {
    val sb = new ScalarBuffer()
    logger.foreach { _.dprintln(s"creating clone $sb of $orig in $ctrler") }
    //TODO: make this metadata mirroring
    orig.writePortMux.inputs.foreach { input =>
      val v = collectIn[GlobalInput](input).map(_.variable).head
      val data = sb.writePort(v)
      sb.setTopCtrl(data, None, orig.topCtrlMap.get(data))
    }
    sb.setTopCtrl(sb.readPort, None, orig.topCtrlMap.get(sb.readPort))
    ctrler.mems(List(sb))
    design.pirmeta.mirror(orig, sb)
    sb
  }
}

class ScalarFIFO()(implicit ctrler:Controller, design: PIR) 
  extends ScalarMem with FIFO {
  override val typeStr = "ScalarFIFO"
}
object ScalarFIFO {
  def apply(size:Int)(implicit ctrler:Controller, design: PIR): ScalarFIFO
    = new ScalarFIFO().size(size)
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: PIR): ScalarFIFO
    = new ScalarFIFO().name(name).size(size)
}

trait ControlMem extends OnChipMem with LocalMem

class ControlFIFO()(implicit ctrler:Controller, design: PIR) 
  extends ControlMem with FIFO {
  override val typeStr = "ControlFIFO"
}
object ControlFIFO {
  def apply(size:Int)(implicit ctrler:Controller, design: PIR): ControlFIFO
    = new ControlFIFO().size(size)
  def apply(name:String, size:Int)(implicit ctrler:Controller, design: PIR): ControlFIFO
    = new ControlFIFO().name(name).size(size)
}

