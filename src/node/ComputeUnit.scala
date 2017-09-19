package pir.node

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.PIR
import pir.node._
import pir.util.enums._
import pir.util._
import pir.codegen.Logger
import pir.exceptions._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef

abstract class ComputeUnit(override val name: Option[String])(implicit design: PIR) extends Controller with OuterRegBlock {
  override val typeStr = "CU"
  import pirmeta._

  private var _parent:Controller = _
  def parent:Controller = { _parent }
  def parent[T](parent:T):this.type = {
    parent match {
      case p:String =>
        design.updateLater(p, (n:Node) => this.parent(n.asInstanceOf[Controller]))
      case p:Controller =>
        _parent = p
        p.addChildren(this)
    }
    this
  }
  def removeParent:Unit = _parent = null

  /* Fields */
  /* CounterChains */
  val cchainMap = Map[CounterChain, CounterChain]() // map between original and copied cchains
  def cchains = cchainMap.values.toList
  def addCChain(cc:CounterChain):CounterChain = {
    if (!cc.isDefined) return cc // If cc is a copy but haven't been updated, addCChain during update 
    if (cchainMap.contains(cc.original) && cchainMap(cc.original)!=cc)
      throw PIRException(s"Already have copy/original copy of ${cc.original} but adding duplicated copy ${cc}")
    else cchainMap += (cc.original -> cc)
    return cc
  }
  def removeCChain(cc:CounterChain):Unit = {
    cchainMap.get(cc.original).foreach { cp => if (cp== cc) cchainMap -= cc.original }
  }
  def removeCChainCopy(cc:CounterChain):Unit = { 
    assert(!cc.isCopy)
    cchainMap -= cc
  }

  def getCC(cchain:CounterChain):CounterChain = cchainMap(cchain.original)

  def getCopy(cchain:CounterChain)(implicit logger:Logger):CounterChain = {
    val copy = cchainMap.getOrElseUpdate(cchain.original, {
      val cp = CounterChain.copy(cchain.original)(this, design)
      logger.dprintln(s"Creating new copy=$cp of $cchain in $this")
      cp
    })
    assert(cchainMap.contains(copy.original))
    copy
  }

  def cloneCC(cchain:CounterChain):CounterChain = {
    val clone = CounterChain.clone(cchain.original)(this, design)
    assert(cchainMap.contains(clone.original)) // clone's original should be it self
    clone
  }

  def containsCopy(cchain:CounterChain):Boolean = {
    cchainMap.contains(cchain.original)
  }
  
  override def toUpdate = { super.toUpdate }

  def updateBlock(block: this.type => Any)(implicit design: PIR):this.type = {
    val mems = design.addBlock[OnChipMem](block(this), 
                            (n:Node) => n.isInstanceOf[OnChipMem] 
                            ) 
    this.mems(mems)
    this
  }

  var index = 0
  def nextIndex = { val temp = index; index +=1 ; temp}

  //val emptyStage = EmptyStage(); indexOf(emptyStage) = nextIndex 
  //def stages:List[Stage] = emptyStage :: Nil 
  def stages:List[Stage] = Nil 

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
}

