package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.graph.enums._
import pir.graph.mapper.PIRException
import scala.reflect.runtime.universe._
import pir.graph.traversal.ForwardRef
import pir.misc._

abstract class Controller(implicit design:Design) extends Node {
  implicit def ctrler:this.type = this
  val sinMap = Map[Scalar, ScalarIn]()
  val soutMap = Map[Scalar, ScalarOut]()
  def souts = soutMap.values.toList
  val vinMap = Map[Vector, VecIn]()
  val voutMap = Map[Vector, VecOut]()
  def vouts = voutMap.values.toList
  def newSout(s:Scalar):ScalarOut = soutMap.getOrElseUpdate(s,ScalarOut(s))
  def newVout(v:Vector):VecOut = {
    v match {
      case v:DummyVector => voutMap.getOrElseUpdate(v, new DummyVecOut(None, v))
      case _ => voutMap.getOrElseUpdate(v, VecOut(v))
    }
  }
  def ctrlIns:List[CtrlInPort] = ctrlBox.ctrlIns
  def ctrlOuts:List[CtrlOutPort] = ctrlBox.ctrlOuts 
  // No need to consider scalar after bundling
  def readers:List[Controller] = voutMap.keys.flatMap {
    _.readers.map{ _.ctrler }
  }.toList
  def writers:List[Controller] = vinMap.keys.map(_.writer.ctrler).toList
  def ctrlReaders:List[Controller] = ctrlOuts.flatMap {_.to }.map { _.asInstanceOf[CtrlInPort].ctrler }.filter { _ != this }

  def sins = sinMap.values.toList
  def vins = vinMap.values.toList 
  def newSin(s:Scalar):ScalarIn = sinMap.getOrElseUpdate(s, ScalarIn(s))
  def newVin(v:Vector):VecIn = {
    v match {
      case v:DummyVector => vinMap.getOrElseUpdate(v, new DummyVecIn(None, v))
      case _ => vinMap.getOrElseUpdate(v,VecIn(v))
    }
  }

  def ctrlBox:CtrlBox

  val _children = ListBuffer[ComputeUnit]()
  def children:List[ComputeUnit] = _children.toList
  def removeChildren(c:ComputeUnit) = { _children -= c }
  def addChildren(c:ComputeUnit) = { if (!_children.contains(c)) _children += c }

  private val _consumed = ListBuffer[MultiBuffering]()
  private val _produced = ListBuffer[MultiBuffering]()
  def consume(mem:MultiBuffering) = _consumed += mem
  def produce(mem:MultiBuffering) = _produced += mem
  def consumed = _consumed.toList
  def produced = _produced.toList
  def trueConsumed = consumed.filter { _.trueDep }
  def trueProduced = produced.filter { _.trueDep }
  def writtenMem:List[OnChipMem] = {
    (soutMap ++ voutMap).values.flatMap{_.readers.flatMap{ _.out.to }}.map{_.src}.collect{ case ocm:OnChipMem => ocm }.toList
  }

  def isHead = (trueConsumed.size==0)
  def isLast = (trueProduced.size==0)
  def isUnitStage = isHead && isLast

  /* Number of children stages on the critical path */
  def length:Int = {
    var count = 1
    var heads:List[Controller] = children.filter{!_.isHead}
    while(heads.size!=0) {
      // Collect consumers that are not Top
      heads = heads.flatMap { _.trueProduced.map { _.consumer } }.toSet.toList
      count +=1
    }
    count
  }

  // Including current CU. From current to top
  def ancestors: List[Controller] = {
    val list = ListBuffer[Controller]()
    var child:Controller = this 
    list += child
    while (!child.isInstanceOf[Top]) {
      child = child.asInstanceOf[ComputeUnit].parent
      list += child
    }
    list.toList
  }

}

abstract class ComputeUnit(override val name: Option[String])(implicit design: Design) extends Controller with OuterRegBlock {
  override val typeStr = "CU"

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
  def addCChain(cc:CounterChain):Unit = {
    if (!cc.isDefined) return // If cc is a copy but haven't been updated, addCChain during update 
    if (cchainMap.contains(cc.original))
      throw PIRException(s"Already have copy/original copy of ${cc.original} but adding duplicated copy ${cc}")
    else cchainMap += (cc.original -> cc)
  }
  def removeCChain(cc:CounterChain):Unit = {
    cchainMap.get(cc.original).foreach { cp => if (cp== cc) cchainMap -= cc.original }
  }
  def removeCChainCopy(cc:CounterChain):Unit = { 
    assert(!cc.isCopy)
    cchainMap -= cc
  }

  def getCopy(cchain:CounterChain):CounterChain = {
    assert(cchain.isDefined)
    cchainMap.getOrElseUpdate(cchain.original, CounterChain.copy(cchain.original)(this, design))
  }

  def containsCopy(cchain:CounterChain):Boolean = {
    cchainMap.contains(cchain.original)
  }
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  def inner:InnerController

  lazy val localCChain:CounterChain = {
    this match {
      case cu:StreamPipeline =>
        if (cu.isHead) {
          cu.getCopy(cu.parent.localCChain)
        } else if (cu.isLast) {
          cu match {
            case mc:MemoryController => throw PIRException(s"MemoryController $this doesn't have localCChain")
            case sp:StreamPipeline => cu.getCopy(cu.parent.localCChain)
          }
        } else { // middle stages
          if (cu.containsCopy(cu.parent.localCChain)) {
            cu.getCopy(cu.parent.localCChain)
          } else if (cchains.size==0) {
            val dc = CounterChain.dummy(cu, design)
            cu.addCChain(dc)
            dc
          } else {
            val dcs = cchains.filter{_.isDummy}
            assert(dcs.size==1, s"${cu} is not head and has non dummy counter chain $cchains")
            dcs.head
          }
        }
      case cu:MemoryPipeline =>
        throw PIRException(s"MemoryPipeline $this doesn't have local counter chain")
      case cu =>
        val locals = cchains.filter{_.isLocal}
        assert(locals.size==1, 
          s"Currently assume each ComputeUnit only have a single local Counterchain ${this} [${locals.mkString(",")}]")
        locals.head
    }
  }

  override def toUpdate = { super.toUpdate }

  def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val (cchains, mems) = design.addBlock[CounterChain, OnChipMem](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain],
                            (n:Node) => n.isInstanceOf[OnChipMem] 
                            ) 
    cchains.foreach { cc => addCChain(cc) }
    this.mems(mems)
    this
  }

  var index = -1
  def nextIndex = { val temp = index; index +=1 ; temp}

  val emptyStage = EmptyStage(); indexOf(emptyStage) = nextIndex 
  def stages:List[Stage] = emptyStage :: Nil 

  /* Memories */
  val _mems = ListBuffer[OnChipMem]()
  def mems(ms:List[OnChipMem]) = { ms.foreach { m => if (!_mems.contains(m)) _mems += m } }
  def mems:List[OnChipMem] = _mems.toList
  def fifos:List[FIFO] = mems.collect {case fifo:FIFO => fifo }
  def mbuffers:List[MultiBuffering] = mems.collect { case buf:MultiBuffering => buf }

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

class OuterController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) {

  var inner:InnerController = _

  override def toUpdate = super.toUpdate || inner == null 

  override def addCChain(cc:CounterChain):Unit = {
    assert(!cc.isCopy, "Outer controller cannot make copy of other CounterChain")
    super.addCChain(cc)
  }
  override def getCopy(cchain:CounterChain):CounterChain = {
    if (cchain.ctrler!=ctrler)
      throw PIRException(s"OuterController cannot make copy of other CounterChain")
    else cchain
  }

  lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()
}

class Sequential(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "SeqCU"
}
object Sequential {
  def apply[P](name: Option[String], parent:P) (block: Sequential => Any)
                (implicit design: Design):Sequential = {
    new Sequential(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply[P](parent:P) (block: Sequential => Any)
                 (implicit design:Design):Sequential =
    Sequential(None, parent)(block)
  def apply[P](name:String, parent:P) (block:Sequential => Any)
                 (implicit design:Design):Sequential =
    Sequential(Some(name), parent)(block)
}

class MetaPipeline(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  def apply[P](name: Option[String], parent:P) (block: MetaPipeline => Any)
                (implicit design: Design):MetaPipeline = {
    new MetaPipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: MetaPipeline => Any)
                 (implicit design:Design):MetaPipeline =
    MetaPipeline(None, parent)(block)
  def apply[P](name:String, parent:P) (block:MetaPipeline => Any)
                (implicit design:Design):MetaPipeline =
    MetaPipeline(Some(name), parent)(block)
}

class StreamController(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "StreamCtrler"
  override def children:List[InnerController] = {
    super.children.asInstanceOf[List[InnerController]]
  }
}
object StreamController {
  def apply[P](name: Option[String], parent:P) (block: StreamController => Any)
                (implicit design: Design):StreamController = {
    new StreamController(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: StreamController => Any)
                 (implicit design:Design):StreamController =
    StreamController(None, parent)(block)
  def apply[P](name:String, parent:P) (block:StreamController => Any)
                 (implicit design:Design):StreamController =
    StreamController(Some(name), parent)(block)
}

abstract class InnerController(name:Option[String])(implicit design:Design) extends ComputeUnit(name)
 with InnerRegBlock {

  def srams:List[SRAM] = mems.collect{ case sm:SRAM => sm }
  def fows:List[FIFOOnWrite] = mems.collect{ case sm:FIFOOnWrite => sm }

  /* Stages */
  val rdAddrStages = ListBuffer[List[RAStage]]()
  val wtAddrStages = ListBuffer[List[WAStage]]()
  val localStages = ListBuffer[LocalStage]()

  override def stages = (emptyStage :: wtAddrStages.flatMap(l => l).toList ++ rdAddrStages.flatMap(l => l) ++ localStages).toList

  def addWAStages(was:List[WAStage]) = {
    wtAddrStages += was
    was.foreach { wa => addStage(wa) }
  }

  def addRAStages(ras:List[RAStage]) = {
    rdAddrStages += ras
    ras.foreach { ra => addStage(ra) }
  }

  def addStage(s:Stage):Unit = { 
    indexOf(s) = nextIndex
    val prev = stages.last
    s.prev = Some(prev)
    prev.next = Some(s)
    s match {
      case ss:LocalStage =>
        localStages += ss
      case ss:WAStage => // WAstages are added in addWAStages 
      case ss:RAStage => // RAstages are added in addRAStages 
    }
  }

  def stage:LocalStage = { 
    val stage = LocalStage(None)
    ctrler.addStage(stage)
    stage
  }

  def reduceStage:ReduceStage = {
    val stage = ReduceStage(None)
    ctrler.addStage(stage)
    stage
  }

  /* Controller Hierarchy */
  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil
  def inner:InnerController = this

  /* Control Signals */
  lazy val ctrlBox:InnerCtrlBox = InnerCtrlBox()
  
  def udcounters = locals.flatMap{ _.ctrlBox.udcounters }
  def enLUTs:List[EnLUT] = locals.flatMap(_.ctrlBox.enLUTs)
  def tokDownLUTs = locals.flatMap(_.ctrlBox.tokDownLUTs)
  def tokOutLUTs = locals.flatMap(_.ctrlBox.tokOutLUTs)

  /* Block updates */
  override def reset =  { super.reset; localStages.clear; wtAddrStages.clear }

}

class Pipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "PipeCU"

}
object Pipeline {
  def apply[P](name: Option[String], parent:P)(block: Pipeline => Any)(implicit design: Design):Pipeline = {
    new Pipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: Pipeline => Any) (implicit design:Design):Pipeline =
    apply(None, parent)(block)
  def apply[P](name:String, parent:P) (block:Pipeline => Any) (implicit design:Design):Pipeline =
    apply(Some(name), parent)(block)
}

/* Inner Unit Pipe */
class UnitPipeline(override val name: Option[String])(implicit design: Design) extends Pipeline(name) { self =>
  override val typeStr = "UnitPipe"
}
object UnitPipeline {
  def apply[P](name: Option[String], parent:P)(implicit design: Design):UnitPipeline =
    new UnitPipeline(name).parent(parent)
  /* Sugar API */
  def apply[P](parent:P) (block: UnitPipeline => Any) (implicit design:Design):UnitPipeline =
    UnitPipeline(None, parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:UnitPipeline => Any) (implicit design:Design):UnitPipeline =
    UnitPipeline(Some(name), parent).updateBlock(block)
}

/* Memory Pipeline */
class MemoryPipeline(override val name: Option[String])(implicit design: Design) extends Pipeline(name) {

  override val typeStr = "MemPipe"
  override lazy val ctrlBox:MemCtrlBox = MemCtrlBox()
  override def isHead = false
  override def isLast = false

  lazy val mem:MultiBuffering = {
    val rms = mems.collect{ case m:SemiFIFO => m; case m:SRAM => m}
    assert(rms.size==1)
    rms.head
  }
  lazy val dataOut = {
    val dout = mem.readPort.to.map{_.src}.collect{ case vo:VecOut => vo}.head
    dout.in.connect(mem.load)
    dout
  }
  def data = dataOut.vector
}
object MemoryPipeline {
  def apply(name: Option[String])(implicit design: Design):MemoryPipeline =
    new MemoryPipeline(name)
  /* Sugar API */
  def apply[P](parent:P) (block: MemoryPipeline => Any) (implicit design:Design):MemoryPipeline =
    MemoryPipeline(None).parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P) (block:MemoryPipeline => Any) (implicit design:Design):MemoryPipeline =
    MemoryPipeline(Some(name)).parent(parent).updateBlock(block)
  def apply[P](name:String) (block:MemoryPipeline => Any) (implicit design:Design):MemoryPipeline =
    MemoryPipeline(Some(name)).updateBlock(block)
}

case class TileTransfer(override val name:Option[String], memctrl:MemoryController, mctpe:MCType, vec:Vector)
  (implicit design:Design) extends MemoryPipeline(name)  {
  override val typeStr = s"${mctpe}"
} 
object TileTransfer extends {
  /* Sugar API */
  def apply[P](name:Option[String], parent:P, memctrl:MemoryController, mctpe:MCType, vec:Vector)(block:TileTransfer => Any)
                (implicit design:Design):TileTransfer =
    TileTransfer(name, memctrl, mctpe, vec).parent(parent).updateBlock(block)
  def apply[P](name:String, parent:P, memctrl:MemoryController, mctpe:MCType, vec:Vector) (block:TileTransfer => Any)             
                (implicit design:Design):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe, vec:Vector).parent(parent).updateBlock(block)
}

class StreamPipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "StreamPipe"
  private var _parent:StreamController = _
  override def parent:StreamController = _parent
  override def parent[T](parent:T):this.type = {
    parent match {
      case p:StreamController => _parent = p; p.addChildren(this)
      case p:String => super.parent(parent)
      case p => throw PIRException(s"StreamPipeline's parent must be StreamController $this.parent=$p")
    }
    this
  }
  override def removeParent:Unit = _parent = null

  def writtenFIFO:List[FIFO] = writtenMem.collect { case fifo:FIFO => fifo }

  override def isHead = { fifos.filter { fifo =>
      fifo.writer match {
        case cu:ComputeUnit => cu.parent == this.parent 
        case top:Top => false
      }
    }.size==0
  }
  override def isLast = writtenFIFO.filter{_.ctrler.parent==parent}.size==0

}
object StreamPipeline {
  def apply[P](name: Option[String], parent:P) (block: StreamPipeline => Any)
                (implicit design: Design):StreamPipeline = {
    new StreamPipeline(name).parent(parent).updateBlock(block)
  }
  /* Sugar API */
  def apply [P](parent:P) (block: StreamPipeline => Any)
                 (implicit design:Design):StreamPipeline =
    StreamPipeline(None, parent)(block)
  def apply[P](name:String, parent:P) (block:StreamPipeline => Any)
                (implicit design:Design):StreamPipeline =
    StreamPipeline(Some(name), parent)(block)
}

class MemoryController(name: Option[String], val mctpe:MCType, val offchip:OffChip)(implicit design: Design) extends StreamPipeline(name) { self =>
  override val typeStr = "MemoryController"

  val mcfifos = Map[String, FIFO]()
  val mcvecs = Map[String, Vector]()
  
  val done = CtrlOutPort(this, s"${this}.done")
  val dummyCtrl = CtrlOutPort(this, s"${this}.dummy")
  
  override def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    super.updateBlock(block)
    mcvecs.foreach { case (field, vec) => newVout(vec) }
    this
  }
}
object MemoryController {
  def apply[P](name:String, parent:P, mctpe:MCType, offchip:OffChip)(block: MemoryController => Any)
    (implicit design: Design): MemoryController 
    = new MemoryController(Some(name), mctpe, offchip).parent(parent).updateBlock(block)
}

case class Top()(implicit design: Design) extends Controller { self =>
  implicit val top:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  private var _innerCUs:List[InnerController] = Nil
  def innerCUs(innerCUs:List[InnerController]) = _innerCUs = innerCUs
  def innerCUs = _innerCUs

  private var _outerCUs:List[OuterController] = Nil
  def outerCUs(outerCUs:List[OuterController]) = _outerCUs = outerCUs 
  def outerCUs = _outerCUs

  private var _memCUs:List[MemoryPipeline] = Nil
  def memCUs(memCUs:List[MemoryPipeline]) = _memCUs = memCUs
  def memCUs = _memCUs

  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  def spadeCtrlers:List[Controller] = this :: innerCUs
  def ctrlers = this :: compUnits

  def removeCtrler(ctrler:Controller) = {
    ctrler match {
      case _:InnerController => 
        _innerCUs = _innerCUs.filterNot(_==ctrler)
      case _:OuterController => 
        _outerCUs = _outerCUs.filterNot(_==ctrler)
    }
  }

  def topoSort = {
    val list = ListBuffer[Controller]()
    def isDepFree(cl:Controller) = {
      cl.isHead || cl.trueConsumed.forall { csm => list.contains(csm.producer) }
    }
    def addCtrler(cl:Controller):Unit = {
      list += cl
      var children = cl.children
      while (!children.isEmpty) {
        children = children.filter { child =>
          if (isDepFree(child)) {
            addCtrler(child)
            false
          } else { true }
        }
      }
    }
    addCtrler(top)
    list.toList.reverse
  }

  val command = CtrlOutPort(this, s"${this}.command")
  val status = CtrlInPort(this, s"${this}.status")

  private var _scalars:List[Scalar] = Nil
  def scalars:List[Scalar] = _scalars
  def scalars(scalars:List[Scalar]) = _scalars = scalars

  private var _vectors:List[Vector] = Nil
  def vectors:List[Vector] = _vectors
  def vectors(vectors:List[Vector]) = _vectors = vectors

  override lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()(this, design)

  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  
  override def toUpdate = super.toUpdate || innerCUs == null || outerCUs == null

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
    val (inners, outers, memcus, scalars, vectors) = 
      design.addBlock[InnerController, OuterController, MemoryPipeline, Scalar, Vector](block(this), 
                      (n:Node) => n.isInstanceOf[InnerController],
                      (n:Node) => n.isInstanceOf[OuterController],
                      (n:Node) => n.isInstanceOf[MemoryPipeline],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector] 
                      )
    this.innerCUs(inners)
    this.outerCUs(outers)
    this.memCUs(memcus)
    this.scalars(scalars)
    scalars.foreach { s => s match {
        case a:ArgIn => 
          super.newSout(a)
        case a:ArgOut => 
          super.newSin(a)
        case _ => 
      }
    }
    this.vectors(vectors)
    this
  }
}

