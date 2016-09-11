package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.graph.mapper.PIRException
import scala.reflect.runtime.universe._
import pir.graph.traversal.ForwardRef

abstract class Controller(implicit design:Design) extends Node {
  implicit val ctrler = this
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

  val children = ListBuffer[ComputeUnit]()

  def readers:List[Controller] = soutMap.keys.flatMap(_.readers.map(_.ctrler)).toList ++
                                 voutMap.keys.flatMap(_.readers.map(_.ctrler)).toList
  def writers:List[Controller] = sinMap.keys.map(_.writer.ctrler).toList ++
                                 vinMap.keys.map(_.writer.ctrler).toList
}

class ComputeUnit(override val name: Option[String])(implicit val design: Design) extends Controller with OuterRegBlock { self =>
  override implicit val ctrler:ComputeUnit = self
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
  def cchains:List[CounterChain] = cchainMap.values.toList
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  val emptyStage = EmptyStage()
  def stages:List[Stage] = emptyStage :: Nil 
  
  lazy val localCChain:CounterChain = {
    val locals = cchains.filter { cc => cc.copy.isEmpty && !cc.streaming }
    if (locals.size!=1)
      throw PIRException(s"Currently assume each CU have exactly 1 local counterchain. locals:${locals}")
    locals.head
  }
  def addCChain(cc:CounterChain) = {
    val original = cc.copy.getOrElse(cc)
    if (cchainMap.contains(original))
      throw PIRException(s"Already have copy/original copy of ${original} but adding duplicated copy ${cc}")
    else cchainMap += (original -> cc)
  }
  val cchainMap = Map[CounterChain, CounterChain]() // map between original and copied cchains
  def getCopy(cchain:CounterChain) = {
    if (cchainMap.contains(cchain)) {
      cchainMap(cchain)
    } else {
      val local = CounterChain.copy(cchain)(this, design)
      this.addCChain(local)
      local
    }
  }

  override def toUpdate = { super.toUpdate || parent==null || cchains==null }

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
object ComputeUnit {
  def apply(name: Option[String], tpe:CtrlType)(implicit design: Design):ComputeUnit = {
    tpe match {
      case Pipe => new InnerComputeUnit(name)
      case Sequential => new ComputeUnit(name) with SequentialComputeUnit
      case MetaPipeline => new ComputeUnit(name) with MetaPipelineComputeUnit 
    }
  }
  def apply[P,D](name: Option[String], tpe:CtrlType, parent:P, deps:List[D]) (implicit design: Design, dtp:TypeTag[D]):ComputeUnit = {
    ComputeUnit(name, tpe).updateParent(parent).updateDeps(deps)
  }
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D], tpe:CtrlType) (block: ComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):ComputeUnit =
    ComputeUnit(None, tpe).updateBlock(block).updateParent(parent).updateDeps(deps)
  def apply[P,D](name:String, parent:P, deps:List[D], tpe:CtrlType) (block:ComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):ComputeUnit =
    ComputeUnit(Some(name), tpe).updateBlock(block).updateParent(parent).updateDeps(deps)
}

class InnerComputeUnit(name:Option[String])(implicit design:Design) extends ComputeUnit(name) with InnerRegBlock { self =>
  override implicit val ctrler:InnerComputeUnit = self

  override val typeStr = "PipeCU"
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterComputeUnit] = Nil

  var srams:List[SRAM] = _ 
  val wtAddrStages = ListBuffer[List[WAStage]]()
  val localStages = ListBuffer[LocalStage]()

  override def stages = (emptyStage :: wtAddrStages.flatMap(l => l).toList ++ localStages).toList

  override def reset =  { super.reset; localStages.clear; wtAddrStages.clear }

  override def toUpdate = { 
    super.toUpdate || parent==null || cchains==null || srams==null
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM]):this.type = {
    cchains.foreach { cc => addCChain(cc) }
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

trait OuterComputeUnit extends ComputeUnit {
  var inner:InnerComputeUnit = _
  override def toUpdate = super.toUpdate || inner == null
}

trait SequentialComputeUnit extends OuterComputeUnit {
  override val typeStr = "SeqCU"
}

trait MetaPipelineComputeUnit extends OuterComputeUnit {
  override val typeStr = "MetaPipeCU"
}

/* Inner Unit Pipe */
class UnitComputeUnit(override val name: Option[String])(implicit design: Design) extends InnerComputeUnit(name) { self =>
  override val typeStr = "UnitCompUnit"
  def updateBlock(block: UnitComputeUnit => Any)(implicit design: Design):UnitComputeUnit = {
    val (cchains, srams) = 
      design.addBlock[CounterChain, SRAM](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM]
                            ) 
    super.updateFields(cchains, srams)
    this
  }
}
object UnitComputeUnit {
  def apply[P,D](name: Option[String], parent:P, deps:List[D])(implicit design: Design, dtp:TypeTag[D]):UnitComputeUnit =
    new UnitComputeUnit(name).updateParent(parent).updateDeps(deps)
  /* Sugar API */
  def apply[P,D](parent:P, deps:List[D]) (block: UnitComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):UnitComputeUnit =
    UnitComputeUnit(None, parent, deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D]) (block:UnitComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):UnitComputeUnit =
    UnitComputeUnit(Some(name), parent, deps).updateBlock(block)
  /* No Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], cchains:List[CounterChain], srams:List[SRAM])
  (implicit design:Design, dtp:TypeTag[D]):UnitComputeUnit = {
    UnitComputeUnit(name, parent, deps).updateFields(cchains, srams)
  }
}

case class TileTransfer(override val name:Option[String], memctrl:MemoryController, mctpe:MCType, vec:Vector)
  (implicit design:Design) extends InnerComputeUnit(name)  {

  /* Fields */
  val dataIn:VecIn = if (mctpe==TileLoad) newVin(memctrl.load) else newVin(vec) 
  val dataOut:VecOut = if (mctpe==TileStore) newVout(memctrl.store) else newVout(vec)

  def in:Vector = dataIn.vector
  def out:Vector = dataOut.vector

  override val typeStr = "TileTransfer"
  def updateBlock(block: TileTransfer => Any)(implicit design: Design):TileTransfer = {
    val cchains = design.addBlock[CounterChain](block(this), (n:Node) => n.isInstanceOf[CounterChain]) 
    updateFields(cchains, Nil)
  }

  def streamCChain:CounterChain = {
    val ccs = cchains.filter(cc => cc.streaming)
    assert(ccs.size==1, s"streaming ctrs in ${this}: ${ccs}")
    ccs.head
  }

} 
object TileTransfer extends {
  /* Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector)(block:TileTransfer => Any)(implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(name, memctrl, mctpe, vec).updateParent(parent).updateDeps(deps).updateBlock(block)
  def apply[P,D](name:String, parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector) (block:TileTransfer => Any) (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe, vec:Vector).updateParent(parent).updateDeps(deps).updateBlock(block)
  /* No Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, vec:Vector, cchains:List[CounterChain], 
  srams:List[SRAM])   (implicit design:Design, dtp:TypeTag[D]):TileTransfer = {
    TileTransfer(name, memctrl, mctpe, vec).updateFields(cchains, srams).updateParent(parent).updateDeps(deps)
  }
}

case class MemoryController(name: Option[String], mctpe:MCType, offchip:OffChip)(implicit design: Design) extends Controller { self =>
  override implicit val ctrler:Controller = self

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

case class Top()(implicit design: Design) extends Controller { self =>
  override implicit val ctrler:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  var innerCUs:List[InnerComputeUnit] = _
  var outerCUs:List[OuterComputeUnit] = _
  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  var memCtrls:List[MemoryController] = _
  def ctrlers = this :: compUnits ++ memCtrls
  val command = OutPort(this, s"${this}.command")
  val status = InPort(this, s"${this}.status")
  var scalars:List[Scalar] = _
  var vectors:List[Vector] = _
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  
  override def toUpdate = super.toUpdate || innerCUs==null || outerCUs==null || memCtrls==null

  def updateFields(inners:List[InnerComputeUnit], outers:List[OuterComputeUnit], scalars:List[Scalar], vectors:List[Vector], memCtrls:List[MemoryController]) = {
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
      design.addBlock[InnerComputeUnit, OuterComputeUnit, Scalar, Vector, MemoryController](block(this), 
                      (n:Node) => n.isInstanceOf[InnerComputeUnit],
                      (n:Node) => n.isInstanceOf[OuterComputeUnit],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector], 
                      (n:Node) => n.isInstanceOf[MemoryController] 
                      )
    updateFields(inners, outers, scalars, vectors, memCtrls)
  }
}

