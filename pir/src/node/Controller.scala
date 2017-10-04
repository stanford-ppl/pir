package pir.node

import pir._
import pir.util._

import pirc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

abstract class Controller(implicit design:PIR) extends Module {
  implicit def ctrler:this.type = this
  import pirmeta._

  val sinMap = Map[Scalar, ScalarIn]()
  val soutMap = Map[Scalar, ScalarOut]()
  val vinMap = Map[Vector, VecIn]()
  val voutMap = Map[Vector, VecOut]()

  def sins = sinMap.values.toList
  def souts = soutMap.values.toList
  def vins = vinMap.values.toList 
  def vouts = voutMap.values.toList
  def newSin(s:Scalar):ScalarIn = sinMap.getOrElseUpdate(s, ScalarIn(s))
  def newSout(s:Scalar):ScalarOut = soutMap.getOrElseUpdate(s,ScalarOut(s))
  def newSout(name:String, s:Scalar):ScalarOut = soutMap.getOrElseUpdate(s, ScalarOut(s).name(name))
  def newVin(v:Vector):VecIn = vinMap.getOrElseUpdate(v,VecIn(v))
  def newVout(v:Vector):VecOut = voutMap.getOrElseUpdate(v, VecOut(v))
  def newVout(name:String, v:Vector):VecOut = voutMap.getOrElseUpdate(v, VecOut(v).name(name))

  def cins:List[InPort] = ctrlBox.ctrlIns
  def couts:List[OutPort] = ctrlBox.ctrlOuts 

  // No need to consider scalar after bundling
  def readers:List[Controller] = voutMap.keys.flatMap {
    _.readers.map{ _.ctrler }
  }.toList
  def writers:List[Controller] = vinMap.keys.map(_.writer.ctrler).toList

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
    collectOut[OnChipMem]((souts ++ vouts).flatMap{_.readers}).toList
  }
  def writtenFIFOs:List[FIFO] = writtenMems.collect { case fifo:FIFO => fifo }
  def writtenSFIFOs:List[ScalarFIFO] = writtenFIFOs.collect { case fifo:ScalarFIFO => fifo }

  val retiming:Map[Variable, FIFO] = Map.empty
  def getRetimingFIFO(variable:Variable):FIFO = {
    retiming.getOrElseUpdate(variable, {
      val fifo = variable match {
        case v:Vector => VectorFIFO(size = 10)
        case v:Scalar => ScalarFIFO(size = 10)
      }
      mems(List(fifo))
      fifo
    })
  }
  val scalarBuf:Map[Variable, ScalarBuffer] = Map.empty
  def getScalarBuffer(scalar:Scalar):ScalarBuffer = {
    scalarBuf.getOrElseUpdate(scalar, {
      val buf = ScalarBuffer(s"${scalar}_buf")
      mems(List(buf))
      buf 
    })
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

