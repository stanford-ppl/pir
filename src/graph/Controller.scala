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
  val children = ListBuffer[ComputeUnit]()

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
  // No need to consider scalar after bundling
  def readers:List[SpadeController] = voutMap.keys.flatMap {
    _.readers.map{ _.ctrler match {
        case top:Top => top
        case cu:ComputeUnit => cu.inner
        case mc:MemoryController => mc
      }
    }
  }.toList
  def writers:List[SpadeController] = vinMap.keys.map(_.writer.ctrler).toList
} 

abstract class ComputeUnit(override val name: Option[String])(implicit design: Design) extends Controller with OuterRegBlock { self => 
  implicit val cu:ComputeUnit = self 
  override val typeStr = "CU"

  /* Pointer */
  var parent:Controller = _
  // List of controllers the current controller expecting token from 
  val dependencies = ListBuffer[ComputeUnit]()
  // List of controllers the current controller send token to
  val dependeds = ListBuffer[ComputeUnit]()
  def isHead = (dependencies.size==0)
  def isTail = (dependeds.size==0)
  def isUnitStage = isHead && isTail

  val ctrlBox = CtrlBox() 

  /* Fields */
  def cchains:List[CounterChain]
  def addCChain(cc:CounterChain):Unit
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  def inner:InnerController

  var index = -1
  def nextIndex = { val temp = index; index +=1 ; temp}

  val emptyStage = EmptyStage(); indexOf(emptyStage) = nextIndex 
  def stages:List[Stage] = emptyStage :: Nil 
  
  var localCChain:CounterChain = _

  override def toUpdate = { super.toUpdate || parent==null || localCChain == null }

  def updateFields(cchains:List[CounterChain]):this.type = {
    cchains.foreach { cc => 
      addCChain(cc)
      if (!cc.isCopy && !cc.streaming) {
        if (localCChain==null) localCChain = cc
        else throw PIRException(s"Currently assume each CU have exactly 1 local counterchain. Already set local to be ${localCChain}. Trying to reset to $cc")
      }
    }
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
        this.parent = p 
        p.children += this
    }
    this
  }

  def updateDeped(deped:ComputeUnit):Unit = {
    dependeds += deped
  }

  def updateDep(dep:ComputeUnit):Unit = {
    dependencies += dep
    dep match {
      case d:ComputeUnit => d.updateDeped(this)
      case _ =>
    }
  }

  def updateDeps[T](deps:List[T])(implicit cltp:TypeTag[T]):this.type = {
    deps.foreach { dep =>
      dep match {
        case d:String => 
          design.updateLater(d, (n:Node) => updateDep(n.asInstanceOf[ComputeUnit]))
        case d:ComputeUnit => 
          updateDep(d)
      }
    }
    this
  }

  def apply(block:this.type => Any) (implicit design:Design):this.type =
    updateBlock(block)
}

class InnerController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) with SpadeController with InnerRegBlock { self =>
  implicit val icu:InnerController = self

  override val typeStr = "PipeCU"

  var srams:List[SRAM] = _ 

  val cchainMap = Map[CounterChain, CounterChain]() // map between original and copied cchains
  override def cchains = cchainMap.values.toList

  override def addCChain(cc:CounterChain):Unit = {
    if (!cc.isDefined) return // If cc is a copy but haven't been updated, addCChain during update 
    if (cchainMap.contains(cc.original))
      throw PIRException(s"Already have copy/original copy of ${cc.original} but adding duplicated copy ${cc}")
    else cchainMap += (cc.original -> cc)
  }

  def getCopy(cchain:CounterChain):CounterChain = {
    assert(cchain.isDefined)
    cchainMap.getOrElseUpdate(cchain.original, CounterChain.copy(cchain.original)(this, design))
  }

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

  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil
  def inner:InnerController = this

  lazy val ancestors: List[ComputeUnit] = {
    val list = ListBuffer[ComputeUnit]()
    var child:Controller = this 
    while (!child.isInstanceOf[Top]) {
      val temp = child.asInstanceOf[ComputeUnit]
      list += temp 
      child = temp.parent
    }
    list.toList
  }

  def ctrlIns = locals.flatMap(_.ctrlBox.getCtrlIns)
  def ctrlOuts = locals.flatMap(_.ctrlBox.getCtrlOuts)
  def udcounters = locals.flatMap{ _.ctrlBox.udcounters }
  def enLUTs = locals.flatMap(_.ctrlBox.enLUTs)
  def tokDownLUTs = locals.flatMap(_.ctrlBox.tokDownLUTs)
  def tokOutLUTs = locals.flatMap(_.ctrlBox.tokOutLUTs)

  override def reset =  { super.reset; localStages.clear; wtAddrStages.clear }

  override def toUpdate = { 
    super.toUpdate || parent==null || cchains==null || srams==null
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM]):this.type = {
    super.updateFields(cchains)
    this.srams = srams 
    this
  }

  override def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val (cchains, srams) = 
      design.addBlock[CounterChain, SRAM](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM]
                            ) 
    updateFields(cchains, srams)
  }
}
object Pipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: InnerController => Any)  (implicit design: Design, dtp:TypeTag[D]):InnerController = {
    new InnerController(name).updateParent(parent).updateDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: InnerController => Any) (implicit design:Design, dtp:TypeTag[D]):InnerController =
    apply(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:InnerController => Any) (implicit design:Design, dtp:TypeTag[D]):InnerController =
    apply(Some(name), parent, deps)(block)
}

class OuterController(name:Option[String])(implicit design:Design) extends ComputeUnit(name) {
  var inner:InnerController = _
  override def toUpdate = super.toUpdate || inner == null

  private val _cchains = ListBuffer[CounterChain]()
  def cchains:List[CounterChain] = _cchains.toList 
  def addCChain(cc:CounterChain):Unit = {
    assert(!cc.isCopy, "Outer controller cannot make copy of other CounterChain")
    _cchains += cc
  }
}

class Sequential(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "SeqCU"
}
object Sequential {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: Sequential => Any)  (implicit design: Design, dtp:TypeTag[D]):Sequential = {
    new Sequential(name).updateParent(parent).updateDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: Sequential => Any) (implicit design:Design, dtp:TypeTag[D]):Sequential =
    apply(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:Sequential => Any) (implicit design:Design, dtp:TypeTag[D]):Sequential =
    apply(Some(name), parent, deps)(block)
}

class MetaPipeline(name:Option[String])(implicit design:Design) extends OuterController(name) {
  override val typeStr = "MetaPipeCU"
}
object MetaPipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D]) (block: MetaPipeline => Any)  (implicit design: Design, dtp:TypeTag[D]):MetaPipeline = {
    new MetaPipeline(name).updateParent(parent).updateDeps(deps).updateBlock(block)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D]) (block: MetaPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):MetaPipeline =
    apply(None, parent, deps)(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:MetaPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):MetaPipeline =
    apply(Some(name), parent, deps)(block)
}

/* Inner Unit Pipe */
class UnitPipeline(override val name: Option[String])(implicit design: Design) extends InnerController(name) { self =>
  override val typeStr = "UnitCompUnit"
  def updateBlock(block: UnitPipeline => Any)(implicit design: Design):UnitPipeline = {
    val (cchains, srams) = 
      design.addBlock[CounterChain, SRAM](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM]
                            ) 
    super.updateFields(cchains, srams)
    this
  }
}
object UnitPipeline {
  def apply[P,D](name: Option[String], parent:P, deps:List[D])(implicit design: Design, dtp:TypeTag[D]):UnitPipeline =
    new UnitPipeline(name).updateParent(parent).updateDeps(deps)
  /* Sugar API */
  def apply[P,D](parent:P, deps:List[D]) (block: UnitPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):UnitPipeline =
    UnitPipeline(None, parent, deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:UnitPipeline => Any) (implicit design:Design, dtp:TypeTag[D]):UnitPipeline =
    UnitPipeline(Some(name), parent, deps).updateBlock(block)
}

case class TileTransfer(override val name:Option[String], memctrl:MemoryController, mctpe:MCType, vec:Vector)
  (implicit design:Design) extends InnerController(name)  {

  /* Fields */
  val dataIn:VecIn = if (mctpe==TileLoad) newVin(memctrl.load) else newVin(vec) 
  val dataOut:VecOut = if (mctpe==TileStore) newVout(memctrl.store) else newVout(vec)

  def in:Vector = dataIn.vector
  def out:Vector = dataOut.vector

  override val typeStr = "TileTransfer"
  def updateBlock(block: TileTransfer => Any)(implicit design: Design):TileTransfer = {
    val cchains = design.addBlock[CounterChain](block(this), (n:Node) => n.isInstanceOf[CounterChain]) 
    super.updateFields(cchains, Nil)
  }

  def streamCChain:CounterChain = {
    val ccs = cchains.filter(cc => cc.streaming)
    assert(mctpe==TileLoad, s"Only TileLoad has streaming CounterChain")
    assert(ccs.size==1, s"streaming CounterChain in ${this}: ${ccs}")
    ccs.head
  }

} 
object TileTransfer extends {
  /* Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector)(block:TileTransfer => Any)(implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(name, memctrl, mctpe, vec).updateParent(parent).updateDeps(deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector) (block:TileTransfer => Any) (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe, vec:Vector).updateParent(parent).updateDeps(deps).updateBlock(block)
}

case class MemoryController(name: Option[String], mctpe:MCType, offchip:OffChip)(implicit design: Design) extends SpadeController { self =>

  val typeStr = "MemoryController"
  val addr = newSin(Scalar())
  val dataIn  = if (mctpe==TileStore) Some(newVin(Vector())) else None
  val dataOut = if (mctpe==TileLoad) Some(newVout(Vector())) else None

  def saddr = addr.scalar 
  def load = if (mctpe==TileLoad) dataOut.get.vector
    else throw PIRException(s"Cannot load from a MemoryController with mctpe=${mctpe}")
  def store = if(mctpe==TileStore) dataIn.get.vector 
    else throw PIRException(s"Cannot store to a MemoryController with mctpe=${mctpe}")

  def updateFields = {
    sinMap += addr.scalar -> addr 
    dataIn.foreach { di => vinMap += di.vector -> di }
    dataOut.foreach { dout => voutMap += dout.vector -> dout }
  }
}
object MemoryController {
  def apply(mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = MemoryController(None, mctpe, offchip)
  def apply(name:String, mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = MemoryController(Some(name), mctpe, offchip)
}

case class Top()(implicit design: Design) extends SpadeController { self =>
  implicit val top:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  var innerCUs:List[InnerController] = _ 
  var outerCUs:List[OuterController] = _
  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  var memCtrls:List[MemoryController] = _
  def ctrlers = this :: compUnits ++ memCtrls
  def spadeCtrlers:List[SpadeController] = this :: innerCUs ++ memCtrls
  val command = OutPort(this, s"${this}.command")
  val status = InPort(this, s"${this}.status")
  var scalars:List[Scalar] = _
  var vectors:List[Vector] = _

  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  
  override def toUpdate = super.toUpdate || innerCUs == null || outerCUs == null || memCtrls==null

  def updateFields(inners:List[InnerController], outers:List[OuterController], scalars:List[Scalar], vectors:List[Vector], memCtrls:List[MemoryController]) = {
    //TODO change innerCU and outerCU to a type
    this.innerCUs = inners 
    this.outerCUs = outers 
    this.memCtrls = memCtrls
    this.scalars = scalars
    this.vectors = vectors
    scalars.foreach { s => s match {
        case a:ArgIn => super.newSout(a)
        case a:ArgOut => super.newSin(a)
        case _ => 
      }
    }
    memCtrls.foreach { oc => oc.updateFields }
    this
  }

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
    val (inners, outers, scalars, vectors, memCtrls) = 
      design.addBlock[InnerController, OuterController, Scalar, Vector, MemoryController](block(this), 
                      (n:Node) => n.isInstanceOf[InnerController],
                      (n:Node) => n.isInstanceOf[OuterController],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector], 
                      (n:Node) => n.isInstanceOf[MemoryController] 
                      )
    updateFields(inners, outers, scalars, vectors, memCtrls)
  }
}

