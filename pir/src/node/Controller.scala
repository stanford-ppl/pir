package pir.node

import pir._
import pir.util._

import pirc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.reflect.runtime.universe._

abstract class Controller(implicit design:PIR) extends Module {
  implicit def ctrler:this.type = this
  import pirmeta._

  val ginMap = Map[Variable, GlobalInput]()
  val goutList = ListBuffer[GlobalOutput]()

  def gins = ginMap.values.toList
  def gouts = goutList.toList

  def cins = gins.filter {_.isControl }
  def couts = gouts.filter {_.isControl }
  def sins = gins.filter {_.isScalar }
  def souts = gouts.filter {_.isScalar }
  def vins = gins.filter {_.isVector }
  def vouts = gouts.filter {_.isVector }

  def newIn(v:Variable) = ginMap.getOrElseUpdate(v, GlobalInput(v))
  def newOut[T<:Variable](out:Output)(implicit ev:TypeTag[T]):GlobalOutput = 
  goutList.filter{ gout => !gout.in.isConnected && gout.in.from == out }.headOption.getOrElse {
    val bus = if (typeOf[T] =:= typeOf[Vector]) {
      Vector(s"${this}.$out")
    } else if (typeOf[T] =:= typeOf[Scalar]) {
      Scalar(s"${this}.$out")
    } else {
      Control(s"${this}.$out")
    }
    GlobalOutput(bus)
  }
  def newOut[T<:Variable](v:Variable)(implicit ev:TypeTag[T]):GlobalOutput = { 
    val out = GlobalOutput(v)
    goutList += out
    out
  }
  def newOut(name:String, v:Variable):GlobalOutput = newOut(v).name(name)

  //def readers:List[Controller] = ioMap.keys.flatMap {
    //_.readers.map{ _.ctrler }
  //}.toList
  //def writers:List[Controller] = ioMap.keys.map(_.writer.ctrler).toList

  def ctrlBox:CtrlBox

  val _children = ListBuffer[Controller]()
  def children:List[Controller] = _children.toList
  def removeChild(c:Controller) = { _children -= c }
  def addChildren(c:Controller) = { if (!_children.contains(c)) _children += c }

  def produced = producerOf(this) 
  def consumed = consumerOf(this)

  /* Memories */
  val _mems = ListBuffer[OnChipMem]()
  def mems(ms:List[OnChipMem]) = { ms.foreach { m => if (!_mems.contains(m)) _mems += m } }
  def mems:List[OnChipMem] = _mems.toList
  def fifos:List[FIFO] = mems.collect {case fifo:FIFO => fifo }
  def mbuffers:List[MultiBuffer] = mems.collect { case buf:MultiBuffer => buf }
  def vfifos = mems.collect { case fifo:VectorFIFO => fifo }
  def sfifos = mems.collect { case fifo:ScalarFIFO => fifo }
  def smems = mems.collect { case smem:ScalarMem => smem }
  def sbufs = mems.collect { case smem:ScalarBuffer => smem }
  def srams = mems.collect { case mem:SRAM => mem }
  def lmems = mems.collect { case mem:LocalMem => mem }
  def writtenMems:List[OnChipMem] = {
    def f(x:Any):Iterable[Any] = x match {
      case x:GlobalInput => f(x.out)
      case x:ValidMux => f(x.out)
      case x:CtrlPrimitive => Nil
      case x => visitOut(x)
    }
    collectOut[OnChipMem](outs.flatMap{_.to}, visitOut=f _).toList
  }
  def writtenFIFOs:List[FIFO] = writtenMems.collect { case fifo:FIFO => fifo }
  def writtenSFIFOs:List[ScalarFIFO] = writtenFIFOs.collect { case fifo:ScalarFIFO => fifo }

  val retiming:Map[GlobalInput, FIFO] = Map.empty
  def getRetimingFIFO(gin:GlobalInput):FIFO = {
    retiming.getOrElseUpdate(gin, {
      val fifo = gin.variable match {
        case v:Vector => VectorFIFO(size = 10)
        case v:Scalar => ScalarFIFO(size = 10)
        case v:Control => ControlFIFO(size = 10)
      }
      backPressureOf(fifo) = true
      fifo.wtPort(gin)
      mems(List(fifo))
      fifo
    })
  }
  def getRetimingFIFO(variable:Variable):FIFO = {
    val gin = newIn(variable)
    getRetimingFIFO(gin)
  }
  val scalarBuf:Map[Variable, ScalarBuffer] = Map.empty
  def getScalarBuffer(scalar:Scalar):ScalarBuffer = {
    scalarBuf.getOrElseUpdate(scalar, {
      val buf = ScalarBuffer(s"${scalar}_buf")
      mems(List(buf))
      buf 
    })
  }

  def getFifo(name:String) = fifos.filter { _.name.fold{false} { _.contains(name) } }.head
  def getBus(name:String) = {
    (gins ++ gouts).filter { io =>
    val nameOpt:Option[String] = io.name
    nameOpt.fold(false) { name => name.contains(name)}
  }.map{_.variable}.head
  }

  private var _parent:Option[Controller] = None
  def parent:Option[Controller] = { _parent }
  def parent[T](parent:T):this.type = {
    parent match {
      case p:String =>
        design.updateLater { this.parent(nameOf.find[Controller](p)) }
      case p:Controller =>
        _parent = Some(p)
        p.addChildren(this)
      case Some(p) => 
        this.parent(p)
    }
    this
  }
  def removeParent:Unit = _parent = None

  def length = lengthOf(this)
  def ancestors: List[Controller] = ancestorsOf(this)
  def descendents: List[Controller] = descendentsOf(this)
  def isHead = pirmeta.isHead(this)
  def isLast = pirmeta.isLast(this)
  def isUnitStage = isHead && isLast
  def isStreaming = pirmeta.isStreaming(this)
  def isPipelining = pirmeta.isPipelining(this)

  def isMP = this.isInstanceOf[MemoryPipeline]
  def isSC = this.isInstanceOf[StreamController]
  def isSeq = this.isInstanceOf[Sequential]
  def isMeta = this.isInstanceOf[MetaPipeline]
  def isCU = this.isInstanceOf[ComputeUnit]
  def asCU = this.asInstanceOf[ComputeUnit]
  def asCL = this.asInstanceOf[Controller]
  def asICL = this.asInstanceOf[InnerController]
  def asMP = this.asInstanceOf[MemoryPipeline]

  def cloneType(name:String):Controller = {
    cloneType(Some(name))
  }

  def cloneType(name:Option[String] = None):Controller = {
    val clone = this match {
      case _:Sequential => new Sequential().name(s"${this}_${name.getOrElse("clone")}")
      case _:MetaPipeline => new MetaPipeline().name(s"${this}_${name.getOrElse("clone")}")
      case _:StreamController => new StreamController().name(s"${this}_${name.getOrElse("clone")}")
      case _ => throw PIRException(s"Cannot clone $this")
    }
    design.top.addCtrler(clone)
    clone
  }

  def updateBlock(block: this.type => Any)(implicit design: PIR):this.type = {
    block(this)
    this
  }
}

