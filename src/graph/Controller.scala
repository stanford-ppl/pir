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

abstract class Controller(implicit design:Design) extends Node { self =>
  implicit val ctrler = self 
  val sinMap = Map[Scalar, ScalarIn]()
  val vinMap = Map[Vector, VecIn]()
  def sins = sinMap.values.toList
  def vins = vinMap.values.toList 
  def newSin(s:Scalar):ScalarIn = sinMap.getOrElseUpdate(s, ScalarIn(s))
  def newVin(v:Vector):VecIn = {
    v match {
      case v:DummyVector => vinMap.getOrElseUpdate(v, new DummyVecIn(None, v))
      case _ => vinMap.getOrElseUpdate(v,VecIn(v))
    }
  }

  //TODO inner controller shouldn't have children
  val _children = ListBuffer[ComputeUnit]()
  def children:List[ComputeUnit] = _children.toList
  def removeChildren(c:ComputeUnit) = { _children -= c }
  def addChildren(c:ComputeUnit) = { if (!_children.contains(c)) _children += c }

  // Including current CU. From current to top
  def ancestors: List[ComputeUnit] = {
    val list = ListBuffer[ComputeUnit]()
    var child:Controller = this 
    while (!child.isInstanceOf[Top]) {
      val temp = child.asInstanceOf[ComputeUnit]
      list += temp 
      child = temp.parent
    }
    list.toList
  }
}

/* Controller that can be binded with a controler in spade. Including InnerController and Top and
 * MemoryController */
trait SpadeController extends Controller { self =>
  override implicit val ctrler:SpadeController = self 
  val soutMap = Map[Scalar, ScalarOut]()
  def souts = soutMap.values.toList
  def newSout(s:Scalar):ScalarOut = soutMap.getOrElseUpdate(s,ScalarOut(s))
  val voutMap = Map[Vector, VecOut]()
  def vouts = voutMap.values.toList
  def newVout(v:Vector):VecOut = {
    v match {
      case v:DummyVector => voutMap.getOrElseUpdate(v, new DummyVecOut(None, v))
      case _ => voutMap.getOrElseUpdate(v, VecOut(v))
    }
  }
  def ctrlIns:List[CtrlInPort]
  def ctrlOuts:List[CtrlOutPort]
  // No need to consider scalar after bundling
  def readers:List[SpadeController] = voutMap.keys.flatMap {
    _.readers.map{ _.ctrler match {
        case top:Top => top
        case cu:ComputeUnit => cu.inner
      }
    }
  }.toList
  def ctrlReaders:List[SpadeController] = ctrlOuts.flatMap {_.to }.map { _.asInstanceOf[CtrlInPort].ctrler }.filter { _ != this }

  def writers:List[SpadeController] = vinMap.keys.map(_.writer.ctrler).toList
} 

abstract class ComputeUnit(override val name: Option[String])(implicit design: Design) extends Controller with OuterRegBlock { self => 
  implicit val cu:ComputeUnit = self 
  override val typeStr = "CU"

  private var _parent:Controller = _
  def parent:Controller = { _parent }
  def parent(p:Controller) = { _parent = p }
  def removeParent:Unit = _parent = null
  // List of controllers the current controller expecting token from 
  private val _dependencies = ListBuffer[ComputeUnit]()
  // List of controllers the current controller send token to
  private val _dependeds = ListBuffer[ComputeUnit]()
  def dependencies = _dependencies.toList
  def dependeds = _dependeds.toList
  def removeDep(dep:ComputeUnit) = { _dependencies -= dep; dep.removeDeped(this) }
  def removeDeped(deped:ComputeUnit) = { _dependeds -= deped }
  def removeDeps = { dependencies.foreach{ dep => removeDep(dep) } }
  def removeDepeds = { dependeds.foreach { deped => deped.removeDep(this) } }
  def isHead = (dependencies.size==0)
  def isLast = (dependeds.size==0)
  def isUnitStage = isHead && isLast

  val ctrlBox = CtrlBox() 

  /* Fields */
  def cchains:List[CounterChain]
  def addCChain(cc:CounterChain):Unit
  def removeCChain(cc:CounterChain):Unit
  def removeCChainCopy(cc:CounterChain):Unit
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  def inner:InnerController

  var index = -1
  def nextIndex = { val temp = index; index +=1 ; temp}

  val emptyStage = EmptyStage(); indexOf(emptyStage) = nextIndex 
  def stages:List[Stage] = emptyStage :: Nil 
  
  lazy val localCChain:CounterChain = {
    this match {
      case cu:StreamPipeline =>
        assert(cchains.size==1)
        cchains.head // Should be the copy of StreamController
      case cu =>
        val locals = cchains.filter{_.isLocal}
        assert(locals.size==1, 
          s"Currently assume each ComputeUnit only have a single local Counterchain ${this} [${locals.mkString(",")}]")
        locals.head
    }
  }

  override def toUpdate = { super.toUpdate }

  def updateFields(cchains:List[CounterChain]):this.type = {
    cchains.foreach { cc => addCChain(cc) }
    this
  }

  def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val cchains = design.addBlock[CounterChain](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain] 
                            ) 
    updateFields(cchains)
  }

  def updateParent[T](parent:T):this.type = {
    parent match {
      case p:String =>
        design.updateLater(p, (n:Node) => updateParent(n.asInstanceOf[Controller]))
      case p:Controller =>
        this.parent(p)
        p.addChildren(this)
    }
    this
  }

  def addDeped(deped:ComputeUnit):Unit = { if (!_dependeds.contains(deped)) _dependeds += deped }

  def addDep(dep:ComputeUnit):Unit = {
    if (!_dependencies.contains(dep)) _dependencies += dep
    dep.addDeped(this)
  }

  /* Current CU depends on deps (input sources) */
  def addDeps[T](deps:List[T])(implicit cltp:TypeTag[T]):this.type = {
    deps.foreach { dep =>
      dep match {
        case d:String => 
          design.updateLater(d, (n:Node) => addDep(n.asInstanceOf[ComputeUnit]))
        case d:ComputeUnit => 
          addDep(d)
      }
    }
    this
  }

  def apply(block:this.type => Any) (implicit design:Design):this.type =
    updateBlock(block)
}

class OuterController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) {
  //var _parent:Controller = _
  //def parent:Controller = { _parent }
  //def parent(p:Controller) = { _parent = p }
  //def removeParent:Unit = _parent = null
  var inner:InnerController = _
  override def toUpdate = super.toUpdate || inner == null 

  private val _cchains = ListBuffer[CounterChain]()
  def cchains:List[CounterChain] = _cchains.toList 
  def addCChain(cc:CounterChain):Unit = {
    assert(!cc.isCopy, "Outer controller cannot make copy of other CounterChain")
    _cchains += cc
  }
  def removeCChain(cc:CounterChain):Unit = { _cchains -= cc }
  def removeCChainCopy(cc:CounterChain):Unit = { 
    assert(!cc.isCopy)
    _cchains.filterNot( _.original == cc)
  }

  /* Size of longest depended children */
  def height:Int = {
    var count = 1
    var heads = children.filter{!_.isHead}
    while(heads.size!=0) {
      heads = heads.flatMap{_.dependeds}
      count +=1
    }
    count
  }
}

class Sequential(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "SeqCU"
}
object Sequential {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: Sequential => Any)
                (implicit design: Design, dtp:TypeTag[D]):Sequential = {
    new Sequential(name).updateParent(parent).addDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: Sequential => Any)
                 (implicit design:Design, dtp:TypeTag[D]):Sequential =
    Sequential(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:Sequential => Any)
                 (implicit design:Design, dtp:TypeTag[D]):Sequential =
    Sequential(Some(name), parent, deps)(block)
}

class MetaPipeline(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: MetaPipeline => Any)
                (implicit design: Design, dtp:TypeTag[D]):MetaPipeline = {
    new MetaPipeline(name).updateParent(parent).addDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: MetaPipeline => Any)
                 (implicit design:Design, dtp:TypeTag[D]):MetaPipeline =
    MetaPipeline(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:MetaPipeline => Any)
                (implicit design:Design, dtp:TypeTag[D]):MetaPipeline =
    MetaPipeline(Some(name), parent, deps)(block)
}

class StreamController(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "StreamCtrler"
  override def children:List[InnerController] = {
    super.children.asInstanceOf[List[InnerController]]
  }
}
object StreamController {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: StreamController => Any)
                (implicit design: Design, dtp:TypeTag[D]):StreamController = {
    new StreamController(name).updateParent(parent).addDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: StreamController => Any)
                 (implicit design:Design, dtp:TypeTag[D]):StreamController =
    StreamController(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:StreamController => Any)
                 (implicit design:Design, dtp:TypeTag[D]):StreamController =
    StreamController(Some(name), parent, deps)(block)
}

abstract class InnerController(name:Option[String])(implicit design:Design) extends ComputeUnit(name)
  with SpadeController with InnerRegBlock {
  implicit val icu:InnerController = this 

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

  /* Memories */
  def mems:List[OnChipMem]
  def mems(ms:List[OnChipMem])
  def srams:List[SRAM] = mems.collect{ case sm:SRAM => sm }
  def fows:List[FIFOOnWrite] = mems.collect{ case sm:FIFOOnWrite => sm }
  def writtenMem:List[OnChipMem] = vouts.flatMap { vout =>
    vout.vector.readers.flatMap { vin => vin.out.to.map(_.src.asInstanceOf[OnChipMem]) }.toList
  }
  def writtenFIFO:List[FIFOOnWrite] = {
    writtenMem.collect { case mem:FIFOOnWrite => mem }
  }
  private val _scalarMem = ListBuffer[ScalarMem]()
  def scalarMems = _scalarMem.toList
  def addScalarMem(sm: ScalarMem):Unit = {
    _scalarMem += (sm)
  }

  /* Stages */
  val wtAddrStages = ListBuffer[List[WAStage]]()
  val localStages = ListBuffer[LocalStage]()

  override def stages = (emptyStage :: wtAddrStages.flatMap(l => l).toList ++ localStages).toList

  def addWAStages(was:List[WAStage]) = {
    wtAddrStages += was
    was.foreach { wa => indexOf(wa) = nextIndex }
  }

  def addStage(s:Stage):Unit = { s match {
      case ss:LocalStage =>
        localStages += ss
        indexOf(ss) = nextIndex
      case ss:WAStage => // WAstages are added in addWAStages 
    }
  }

  /* Controller Hierarchy */
  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil
  def inner:InnerController = this

  /* Control Signals */
  def ctrlIns:List[CtrlInPort] = locals.flatMap(_.ctrlBox.ctrlIns)
  def ctrlOuts:List[CtrlOutPort] = locals.flatMap(_.ctrlBox.ctrlOuts)
  def udcounters = locals.flatMap{ _.ctrlBox.udcounters }
  def enLUTs = locals.flatMap(_.ctrlBox.enLUTs)
  def tokDownLUTs = locals.flatMap(_.ctrlBox.tokDownLUTs)
  def tokOutLUTs = locals.flatMap(_.ctrlBox.tokOutLUTs)

  /* Block updates */
  override def reset =  { super.reset; localStages.clear; wtAddrStages.clear }

  def updateFields(cchains:List[CounterChain], mems:List[OnChipMem]):this.type = {
    super.updateFields(cchains)
    this.mems(mems)
    this
  }

  override def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val (cchains, mems) = 
      design.addBlock[CounterChain, OnChipMem](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[OnChipMem]
                            ) 
    updateFields(cchains, mems)
  }
}

class Pipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "PipeCU"

  val _mems = ListBuffer[SRAMOnRead]()
  override def mems:List[SRAMOnRead] = _mems.toList
  def mems(ms:List[OnChipMem]) = { ms.foreach { m => _mems += m.asInstanceOf[SRAMOnRead] } }
}
object Pipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D])(block: Pipeline => Any)(implicit design: Design, dtp:TypeTag[D]):Pipeline = {
    new Pipeline(name).updateParent(parent).addDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: Pipeline => Any) (implicit design:Design, dtp:TypeTag[D]):Pipeline =
    apply(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:Pipeline => Any) (implicit design:Design, dtp:TypeTag[D]):Pipeline =
    apply(Some(name), parent, deps)(block)
}

/* Inner Unit Pipe */
class UnitPipeline(override val name: Option[String])(implicit design: Design) extends Pipeline(name) { self =>
  override val typeStr = "UnitCompUnit"
  // UnitPipeline can also have SRAM
  //def updateBlock(block: UnitPipeline => Any)(implicit design: Design):UnitPipeline = {
    //val cchains = design.addBlock[CounterChain](block(this), (n:Node) => n.isInstanceOf[CounterChain]) 
    //super.updateFields(cchains, Nil)
    //this
  //}
  override def writtenMem:List[CommandFIFO] = souts.flatMap { sout =>
    sout.scalar.readers.flatMap { sin => sin.out.to.map(_.src).collect { case mem:CommandFIFO => mem} }.toList
  }
}
object UnitPipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D])(implicit design: Design, dtp:TypeTag[D]):UnitPipeline =
    new UnitPipeline(name).updateParent(parent).addDeps(deps)
  /* Sugar API */
  def apply[P,D](parent:P, deps:List[D]) (block: UnitPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):UnitPipeline =
    UnitPipeline(None, parent, deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:UnitPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):UnitPipeline =
    UnitPipeline(Some(name), parent, deps).updateBlock(block)
}

case class TileTransfer(override val name:Option[String], memctrl:MemoryController, mctpe:MCType, vec:Vector)
  (implicit design:Design) extends UnitPipeline(name)  {
  override val typeStr = s"${mctpe}"
  def updateBlock(block: TileTransfer => Any)(implicit design: Design):TileTransfer = {
    val cchains = design.addBlock[CounterChain](block(this), (n:Node) => n.isInstanceOf[CounterChain]) 
    super.updateFields(cchains, Nil)
    this
  }
} 
object TileTransfer extends {
  /* Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector)(block:TileTransfer => Any)
                (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(name, memctrl, mctpe, vec).updateParent(parent).addDeps(deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector) (block:TileTransfer => Any)             
                (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe, vec:Vector).updateParent(parent).addDeps(deps).updateBlock(block)
}

class StreamPipeline(name:Option[String])(implicit design:Design) extends InnerController(name) { self =>
  override val typeStr = "StreamPipe"
  private var _parent:StreamController = _
  override def parent:StreamController = _parent
  override def parent(p:Controller) = { 
    p match {
      case p:StreamController => _parent = p
      case _ => throw PIRException(s"StreamPipeline's parent must be StreamController $this.parent=$p")
    }
  }
  override def removeParent:Unit = _parent = null

  val _mems = ListBuffer[OnChipMem]()
  override def mems:List[OnChipMem] = _mems.toList
  def mems(ms:List[OnChipMem]) = {
    ms.foreach { m =>
      _mems += m.asInstanceOf[OnChipMem]
    }
  }
  //val _mems = ListBuffer[FIFOOnRead]()
  //override def mems:List[FIFOOnRead] = _mems.toList
  //def mems(ms:List[OnChipMem]) = {
    //ms.foreach { m =>
      //_mems += m.asInstanceOf[FIFOOnRead]
    //}
  //}
  override def writtenMem:List[OnChipMem] = {
    val vmems = vouts.flatMap { _.vector.readers.flatMap { vin => vin.out.to.map(_.src.asInstanceOf[OnChipMem]) }.toList }
    val smems = souts.flatMap{ _.scalar.readers.flatMap { sout => sout.out.to.map(_.src).collect { case mem:CommandFIFO => mem} }.toList }
    vmems ++ smems
  }
}
object StreamPipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: StreamPipeline => Any)
                (implicit design: Design, dtp:TypeTag[D]):StreamPipeline = {
    new StreamPipeline(name).updateParent(parent).addDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: StreamPipeline => Any)
                 (implicit design:Design, dtp:TypeTag[D]):StreamPipeline =
    StreamPipeline(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:StreamPipeline => Any)
                (implicit design:Design, dtp:TypeTag[D]):StreamPipeline =
    StreamPipeline(Some(name), parent, deps)(block)
}

class MemoryController(name: Option[String], val mctpe:MCType, val offchip:OffChip)(implicit design: Design) extends StreamPipeline(name) { self =>
  override val typeStr = "MemoryController"

  val vdata = Vector()
  val sofs = if (mctpe==TileLoad || mctpe==TileStore) Some(Scalar()) else None
  val slen = if (mctpe==TileLoad || mctpe==TileStore) Some(Scalar()) else None
  val saddrs = if (mctpe==Gather || mctpe==Scatter) Some(Vector()) else None
  def addrs = saddrs.get
  def ofs = sofs.get
  def len = slen.get
  val siofs = {
    sofs.map { ofs => newSin(ofs) }
  }
  val silen = {
    slen.map { len => newSin(len) }
  }
  val viaddrs = {
    saddrs.map { addrs => newVin(addrs) }
  }
  private val _dataIn  = if (mctpe==TileStore || mctpe==Scatter) { Some(newVin(vdata)) } else None
  private val _dataOut = if (mctpe==TileLoad || mctpe==Gather) { Some(newVout(vdata)) } else None
  def dataIn = _dataIn.get
  def dataOut = {
    _dataOut.get
  }

  val dataValid = CtrlOutPort(this, s"${this}.dataValid")
  val done = CtrlOutPort(this, s"${this}.done")
  val dummyCtrl = CtrlOutPort(this, s"${this}.dummy")

  val commandFIFO = CommandFIFO(this) 
  mctpe match {
    case (TileLoad | TileStore) => commandFIFO.wtPort(siofs.get.out)
    case (Gather | Scatter) => commandFIFO.wtPort(viaddrs.get.out)
  }
  commandFIFO.dequeueEnable.connect(dummyCtrl)
  val dataFIFO = mctpe match {
    case TileStore => 
      val fifo = FIFO(s"${this}MCDataFIFO", 100, NoBanking()).wtPort(vdata)
      fifo.dequeueEnable.connect(dummyCtrl)
      Some(fifo)
    case _ => None
  }
  mems(commandFIFO::dataFIFO.toList) 
}
object MemoryController {
  def apply(mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = new MemoryController(None, mctpe, offchip)
  def apply(name:String, mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = new MemoryController(Some(name), mctpe, offchip)
}

case class Top()(implicit design: Design) extends SpadeController { self =>
  implicit val top:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  private var _innerCUs:List[InnerController] = _ 
  private var _outerCUs:List[OuterController] = _
  def innerCUs = _innerCUs
  def outerCUs = _outerCUs
  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  def ctrlers = this :: compUnits
  def removeCtrler(ctrler:Controller) = {
    ctrler match {
      case _:InnerController => 
        _innerCUs = _innerCUs.filterNot(_==ctrler)
      case _:OuterController => 
        _outerCUs = _outerCUs.filterNot(_==ctrler)
    }
  }
  def spadeCtrlers:List[SpadeController] = this :: innerCUs
  val command = CtrlOutPort(this, s"${this}.command")
  val status = CtrlInPort(this, s"${this}.status")
  var scalars:List[Scalar] = _
  var vectors:List[Vector] = _

  def ctrlIns:List[CtrlInPort] = status::Nil 
  def ctrlOuts:List[CtrlOutPort] = command::Nil 
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  
  override def toUpdate = super.toUpdate || innerCUs == null || outerCUs == null

  def updateFields(inners:List[InnerController], outers:List[OuterController], scalars:List[Scalar], vectors:List[Vector]) = {
    //TODO change innerCU and outerCU to a type
    this._innerCUs = inners 
    this._outerCUs = outers 
    this.scalars = scalars
    this.vectors = vectors
    scalars.foreach { s => s match {
        case a:ArgIn => super.newSout(a)
        case a:ArgOut => super.newSin(a)
        case _ => 
      }
    }
    this
  }

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
    val (inners, outers, scalars, vectors) = 
      design.addBlock[InnerController, OuterController, Scalar, Vector](block(this), 
                      (n:Node) => n.isInstanceOf[InnerController],
                      (n:Node) => n.isInstanceOf[OuterController],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector] 
                      )
    updateFields(inners, outers, scalars, vectors)
  }
}

