package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._

abstract class Controller(implicit design:Design) extends Node {
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
  def newVin(v:Vector):VecIn = vinMap.getOrElseUpdate(v,VecIn(v))
  def newVout(v:Vector):VecOut = voutMap.getOrElseUpdate(v, VecOut(v))
  def newVout(name:String, v:Vector):VecOut = voutMap.getOrElseUpdate(v, VecOut(name, v))

  def cins:List[CtrlInPort] = ctrlBox.ctrlIns
  def couts:List[CtrlOutPort] = ctrlBox.ctrlOuts 

  // No need to consider scalar after bundling
  def readers:List[Controller] = voutMap.keys.flatMap {
    _.readers.map{ _.ctrler }
  }.toList
  def writers:List[Controller] = vinMap.keys.map(_.writer.ctrler).toList

  def ctrlBox:CtrlBox

  val _children = ListBuffer[ComputeUnit]()
  def children:List[ComputeUnit] = _children.toList
  def removeChild(c:ComputeUnit) = { _children -= c }
  def addChildren(c:ComputeUnit) = { if (!_children.contains(c)) _children += c }

  private val _consumed = ListBuffer[MultiBuffering]()
  private val _produced = ListBuffer[MultiBuffering]()
  def consume(mem:MultiBuffering) = _consumed += mem
  def produce(mem:MultiBuffering) = _produced += mem
  def consumed = _consumed.toList
  def produced = _produced.toList
  def trueConsumed = consumed.filter { _.trueDep }
  def trueProduced = produced.filter { _.trueDep }
  def writtenMems:List[OnChipMem] = {
    (souts ++ vouts).flatMap{_.readers.flatMap{ _.out.to }}.map{_.src}.collect{ case ocm:OnChipMem => ocm }.toList
  }

  def length = lengthOf(this)
  def ancestors: List[Controller] = ancestorsOf(this)
  def descendents: List[Controller] = descendentsOf(this)
  def isHead = pirmeta.isHead(this)
  def isLast = pirmeta.isLast(this)
  def isUnitStage = isHead && isLast
  def isStreaming = pirmeta.isStreaming(this)
  def isPipelining = pirmeta.isPipelining(this)

  def isMP = this.isInstanceOf[MemoryPipeline]
  def asCU = this.asInstanceOf[ComputeUnit]

  def cloneType(name:String):Controller = {
    cloneType(Some(name))
  }

  def cloneType(name:Option[String] = None):Controller = {
    val clone = this match {
      case _:Sequential => new Sequential(Some(s"${this}_${name.getOrElse("clone")}"))
      case _:MetaPipeline => new MetaPipeline(Some(s"${this}_${name.getOrElse("clone")}"))
      case _:StreamController => new StreamController(Some(s"${this}_${name.getOrElse("clone")}"))
      case _ => throw PIRException(s"Cannot clone $this")
    }
    design.top.addCtrler(clone)
    clone
  }
}

abstract class ComputeUnit(override val name: Option[String])(implicit design: Design) extends Controller with OuterRegBlock {
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
    if (cchainMap.contains(cc.original))
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

  def getCopy(cchain:CounterChain):CounterChain = {
    val copy = CounterChain.copy(cchain.original)(this, design)
    assert(cchainMap.contains(copy.original))
    copy
  }

  def cloneCC(cchain:CounterChain):CounterChain = {
    val clone = CounterChain.clone(cchain.original)(this, design)
    assert(cchainMap.contains(clone.original))
    clone
  }

  def containsCopy(cchain:CounterChain):Boolean = {
    cchainMap.contains(cchain.original)
  }
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  lazy val localCChain:CounterChain = {
    this match {
      //case cu:StreamPipeline =>
        //if (isHead) {
          //cu.getCopy(cu.parent.localCChain)
        //} else if (isLast) {
          //cu match {
            //case mc:MemoryController => throw PIRException(s"MemoryController $this doesn't have localCChain")
            //case sp:StreamPipeline => cu.getCopy(cu.parent.localCChain)
          //}
        //} else { // middle stages
          //if (cu.containsCopy(cu.parent.localCChain)) {
            //cu.getCopy(cu.parent.localCChain)
          //} else if (cchains.size==0) {
            //val dc = CounterChain.dummy(cu, design)
            //cu.addCChain(dc)
            //dc
          //} else {
            //val dcs = cchains.filter{_.isDummy}
            //assert(dcs.size==1, s"${cu} is not head and has non dummy counter chain $cchains")
            //dcs.head
          //}
        //}
      case cu:MemoryPipeline =>
        throw PIRException(s"MemoryPipeline $this doesn't have local counter chain")
      case cu:Pipeline if isStreaming =>
        sortCChains(cu.cchains).headOption.getOrElse(CounterChain.dummy)
      case cu =>
        val locals = cchains.filter{_.isLocal}
        assert(locals.size<=1, 
          s"Currently assume each ComputeUnit only have a single local Counterchain ${this} [${locals.mkString(",")}]")
        locals.headOption.getOrElse {
          addCChain(CounterChain.dummy)
        }
    }
  }

  def parLanes:Int

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
  def mbuffers:List[MultiBuffering] = mems.collect { case buf:MultiBuffering => buf }
  def vfifos = mems.collect { case fifo:VectorFIFO => fifo }
  def sfifos = mems.collect { case fifo:ScalarFIFO => fifo }
  def smems = mems.collect { case smem:ScalarMem => smem }
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

abstract class OuterController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) {

  override def toUpdate = super.toUpdate

  override def getCopy(cchain:CounterChain):CounterChain = {
    if (cchain.ctrler!=ctrler)
      throw PIRException(s"OuterController cannot make copy of other CounterChain")
    else cchain
  }

  lazy val ctrlBox:OuterCtrlBox = OuterCtrlBox()

  def parLanes:Int = 1 

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
  import pirmeta._

  def srams:List[SRAM] = mems.collect{ case sm:SRAM => sm }
  def fows:List[FIFOOnWrite] = mems.collect{ case sm:FIFOOnWrite => sm }

  /* Stages */
  val _rdAddrStages = ListBuffer[RAStage]()
  def rdAddrStages = _rdAddrStages.toList
  val _wtAddrStages = ListBuffer[WAStage]()
  def wtAddrStages = _wtAddrStages.toList
  val _localStages = ListBuffer[LocalStage]()
  def localStages = _localStages.toList 

  //override def stages = (emptyStage :: wtAddrStages ++ rdAddrStages ++ localStages).toList
  override def stages:List[Stage] = localStages

  def addStage(s:Stage):Unit = { 
    indexOf(s) = nextIndex
    val pool = s match {
      case s:WAStage => wtAddrStages
      case s:RAStage => rdAddrStages
      case s:LocalStage => localStages
    }
    pool.foreach { prev =>
      s.prev = Some(prev)
      prev.next = Some(s)
    }
    s match {
      case s:LocalStage => _localStages += s
      case s:WAStage => _wtAddrStages += s
      case s:RAStage => _rdAddrStages += s 
    }
  }

  def parLanes:Int = localCChain.inner.par
  /* Controller Hierarchy */
  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil

  /* Control Signals */
  lazy val ctrlBox:CtrlBox = InnerCtrlBox()
  
  def udcounters = locals.flatMap{ _.ctrlBox.udcounters }
  def enLUTs:List[EnLUT] = locals.flatMap(_.ctrlBox.enLUTs)
  def tokDownLUTs = locals.flatMap(_.ctrlBox.tokDownLUTs)
  def tokOutLUTs = locals.flatMap(_.ctrlBox.tokOutLUTs)

  /* Block updates */
  override def reset =  { super.reset; _localStages.clear; _wtAddrStages.clear; _rdAddrStages.clear }

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
  import pirmeta._

  override val typeStr = "MemPipe"
  override lazy val ctrlBox:MemCtrlBox = MemCtrlBox()

  override def stages:List[Stage] = wtAddrStages ++ rdAddrStages

  //lazy val mem:MultiBuffering = {
  lazy val mem:SRAM = {
    //val rms = mems.collect{ case m:SemiFIFO => m; case m:SRAM => m}
    val rms = mems.collect{ case m:SRAM => m}
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

class StreamPipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "StreamPipe"
  import pirmeta._

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
  import pirmeta._
  
  override lazy val ctrlBox:MCCtrlBox = MCCtrlBox()

  def getFifo(name:String) = fifos.filter { _.name == Some(name) }.head

  override def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    super.updateBlock(block)
    sfifos.foreach { sfifo =>
      CtrlInPort(this, s"$this.${sfifo.name.get}").connect(sfifo.readPort)
    }
    this
  }

  def len = fifos.filter { _.name==Some("size") }.head
}
object MemoryController {
  def apply[P](name:String, parent:P, mctpe:MCType, offchip:OffChip)(block: MemoryController => Any)
    (implicit design: Design): MemoryController 
    = new MemoryController(Some(name), mctpe, offchip).parent(parent).updateBlock(block)
}

case class Top()(implicit design: Design) extends Controller { self =>
  implicit val top:Controller = self
  import pirmeta._

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  private val _innerCUs = ListBuffer[InnerController]()
  def innerCUs = _innerCUs.toList

  private val _outerCUs = ListBuffer[OuterController]()
  def outerCUs = _outerCUs.toList

  private var _memCUs = ListBuffer[MemoryPipeline]()
  def memCUs = _memCUs.toList

  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  def spadeCtrlers:List[Controller] = this :: innerCUs
  def ctrlers = this :: compUnits

  def addCtrler(ctrler:Controller) = {
    ctrler match {
      case ctrler:MemoryPipeline =>
        _memCUs += ctrler
        _innerCUs += ctrler
      case ctrler:InnerController => 
        _innerCUs += ctrler
      case ctrler:OuterController => 
        _outerCUs += ctrler
    }
  }
  
  def removeCtrler(ctrler:Controller) = {
    ctrler match {
      case ctrler:MemoryPipeline =>
        _memCUs -= ctrler
        _innerCUs -= ctrler
      case ctrler:InnerController => 
        _innerCUs -= ctrler
      case ctrler:OuterController => 
        _outerCUs -= ctrler
    }
  }

  private var _scalars:List[Scalar] = Nil
  def scalars:List[Scalar] = _scalars
  def scalars(scalars:List[Scalar]) = _scalars = scalars

  private var _vectors:List[Vector] = Nil
  def vectors:List[Vector] = _vectors
  def vectors(vectors:List[Vector]) = _vectors = vectors

  override lazy val ctrlBox:TopCtrlBox = TopCtrlBox()(this, design)

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
    this._innerCUs ++= inners
    this._outerCUs ++= outers
    this._memCUs ++= memcus
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

