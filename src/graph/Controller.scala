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
  def newSin(s:Scalar):ScalarIn = {
    if (!sinMap.contains(s))
      sinMap += s -> ScalarIn(s)
    sinMap(s)
  }
  def newSout(s:Scalar):ScalarOut = {
    if (!soutMap.contains(s))
      soutMap += (s -> ScalarOut(s))
    soutMap(s) 
  }
  def newVin(v:Vector):VecIn = {
    if (!vinMap.contains(v))
      vinMap += (v -> VecIn(v))
    vinMap(v)
  }
  def newVout(v:Vector):VecOut = {
    if (!voutMap.contains(v))
      voutMap += (v -> VecOut(v))
    voutMap(v) 
  }

  val children = ListBuffer[ComputeUnit]()

  def readers:List[Controller] = soutMap.keys.flatMap(_.readers.map(_.ctrler)).toList ++
                                 voutMap.keys.flatMap(_.readers.map(_.ctrler)).toList
  def writers:List[Controller] = sinMap.keys.map(_.writer.ctrler).toList ++
                                 vinMap.keys.map(_.writer.ctrler).toList
}

class ComputeUnit(override val name: Option[String], val tpe:CtrlType)(implicit design: Design) extends Controller { self =>
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

  val ctrlBox = CtrlBox() 
  /* List of outer controllers reside in current inner*/
  var outers:List[Controller] = Nil

  /* Fields */
  def cchains:List[CounterChain] = cchainMap.values.toList
  var srams:List[SRAM] = _ 
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  val emptyStage = EmptyStage()
  val wtAddrStages = ListBuffer[List[WAStage]]()
  val localStages = ListBuffer[LocalStage]()

  var infGraph:Map[Reg, Set[Reg]] = Map.empty

  def stages = (emptyStage :: wtAddrStages.flatMap(l => l).toList ++ localStages).toList
  lazy val localCChain:CounterChain = {
    val locals = cchains.filter { cc => cc.copy.isEmpty }
    if (locals.size!=1)
      throw PIRException(s"Currently assume each CU have exactly 1 local counterchain")
    locals.head
  }
  def addCChain(cc:CounterChain) = {
    val original = cc.copy.getOrElse(cc)
    if (cchainMap.contains(original))
      throw PIRException(s"Already have copy/original copy of ${original} but adding duplicated copy ${cc}")
    else cchainMap += (original -> cc)
  }
  val cchainMap = Map[CounterChain, CounterChain]() // map between original and copied cchains
  //lazy val remoteCChains = cchains.filter { cc => cc.copy.isDefined } 
  //def totalCChains = outers.collect {case c:ComputeUnit => c}.flatMap(_.cchains) ++ cchains

  override def toUpdate = { 
    super.toUpdate || parent==null || cchains==null || srams==null
  }

  def this(name: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM]) (implicit design: Design) = {
    this(name, tpe)
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM]):this.type = {
    cchains.foreach { cc => addCChain(cc) }
    this.srams = srams 
    this
  }

  def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val (cchains, srams) = 
      design.addBlock[CounterChain, SRAM](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM]
                            ) 
    updateFields(cchains, srams)
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

  var regId = 0
  def newTemp = {val temp = regId; regId +=1; temp}

  /* Register Mapping */
  val reduceReg  = ReducePR(newTemp)
  val vecIns     = Map[VecIn, VecInPR]()
  val vecOut     = VecOutPR(newTemp)
  val scalarIns  = Map[ScalarIn, ScalarInPR]()
  val scalarOuts = Map[ScalarOut, ScalarOutPR]()
  val loadRegs   = Map[SRAM, LoadPR]()
  val storeRegs  = Map[SRAM, StorePR]()
  val wtAddrRegs = Map[SRAM, WtAddrPR]()
  //val rdAddrRegs = Map[SRAM, RdAddrPR]()
  val ctrRegs    = Map[Counter, CtrPR]()
  val tempRegs   = Set[Reg]()
  val accumRegs  = Set[AccumPR]()
  def reset      = { regId = 0; loadRegs.clear; storeRegs.clear; ctrRegs.clear; 
    localStages.clear; wtAddrStages.clear}

  def addWAStages(was:List[WAStage]) = {
    wtAddrStages += was
  }

  def addStage(s:Stage):Unit = { s match {
      case ss:LocalStage =>
        localStages += ss
      case ss:WAStage => // Added at WAStages
    }
  }

  def pipeReg(stage:Stage, reg:Reg) = {
    val prs = stage.prs
    if (!prs.contains(reg))
      prs += (reg -> PipeReg(stage, reg))
    prs(reg)
  }
  def loadPR(s:SRAM):LoadPR = {
    if (!loadRegs.contains(s)) loadRegs += (s -> LoadPR(newTemp, s.readPort))
    loadRegs(s)
  }
  def storePR(s:SRAM):StorePR = {
    if (!storeRegs.contains(s)) storeRegs += (s -> StorePR(newTemp, s.writePort))
    storeRegs(s)
  }
  def wtAddrPR(s:SRAM):WtAddrPR = {
    if (!wtAddrRegs.contains(s)) wtAddrRegs += (s -> WtAddrPR(newTemp, s.writeAddr))
    wtAddrRegs(s)
  }
  def ctrPR(c:Counter):CtrPR = {
    if (!ctrRegs.contains(c)) ctrRegs += (c -> CtrPR(newTemp, c))
    ctrRegs(c)
  }
  def accumPR(init:Const):AccumPR = {
    val acc = AccumPR(newTemp, init)
    accumRegs += acc 
    acc
  }
  def scalarInPR(s:ScalarIn):ScalarInPR = {
    if (!scalarIns.contains(s)) scalarIns += (s -> ScalarInPR(newTemp, s))
    scalarIns(s)
  }
  def scalarOutPR(s:ScalarOut):ScalarOutPR = {
    if (!scalarOuts.contains(s)) scalarOuts += (s -> ScalarOutPR(newTemp, s))
    scalarOuts(s)
  }
  def vecInPR(v:VecIn):VecInPR =  {
    if (!vecIns.contains(v)) vecIns += (v -> VecInPR(newTemp, v))
    vecIns(v)
  }
  def vecOutPR(vo:VecOut):VecOutPR = {
    vecOut.vecOut = vo
    vecOut
  }
  def tempPR():Reg = {
    val reg = Reg(newTemp)
    tempRegs += reg 
    reg
  }

 /** Create a pipeline register for a stage corresponding to 
  *  the register that loads from the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
 def load(stage:Stage, s:SRAM):PipeReg = {
    pipeReg(stage, loadPR(s))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that stores to the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
  def store(stage:Stage, s:SRAM):PipeReg = pipeReg(stage, storePR(s))
  def wtAddr(sram:SRAM):WtAddrPR = wtAddrPR(sram)
  //def wtAddr(stage:WAStage):PipeReg = {
  //  val reg = wtAddr()
  //  wtAddr(stage, reg)
  //}
  def wtAddr(stage:Stage, reg:WtAddrPR):PipeReg = {
    pipeReg(stage, reg)
  }
  //def rdAddr():RdAddrPR = RdAddrPR(newTemp)
  //def rdAddr(stage:Stage):PipeReg = {
  //  val reg = rdAddr()
  //  rdAddr(stage, reg)
  //}
  //def rdAddr(stage:Stage, reg:RdAddrPR):PipeReg = {
  //  val pr = pipeReg(stage, reg)
  //  val srams = reg.raPorts.map{_.src.asInstanceOf[SRAM]}
  //  srams.foreach {s =>
  //    if (!rdAddrRegs.contains(s)) rdAddrRegs += (s -> reg)
  //    s.readAddr = pr.out
  //  }
  //  pr
  //}
  
  def ctr(c:Counter):PipeReg = {
    pipeReg(emptyStage, ctrPR(c))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the counter 
  * @param stage: Stage of the pipeline register 
  * @param c: counter 
  */
  def ctr(stage:Stage, c:Counter):PipeReg = {
    pipeReg(stage, ctrPR(c))
  }
  /* Create a new logical accumulator register */
  def accum(init:Const):AccumPR = accumPR(init)
  /* Create a new logical accumulator register and return a PipeReg for the stage and the created
   * accumulator 
  * @param stage: Stage of the pipeline register 
   * @param init initial value of the accumulator
   * */
  def accum(stage:Stage, init:Const):PipeReg = pipeReg(stage, accum(init))
  /* Create a pipeline register for a stage that connects to the accumulator reg 
   * @param stage
   * @param acc 
   */
  def accum(stage:Stage, acc:AccumPR):PipeReg = pipeReg(stage, acc)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the reduction network 
  * @param stage: Stage of the pipeline register 
  * @param i: initial value
  */
  def reduce(stage:Stage):PipeReg = {
    pipeReg(stage, reduceReg)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param s: ScalarIn buffer 
  */
  def scalarIn(stage:Stage, s:ScalarIn):PipeReg = pipeReg(stage, scalarInPR(s))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarIn(stage:Stage, s:Scalar):PipeReg = scalarIn(stage, newSin(s))
  /** Create a ScalarOut object 
  * @param s: scalar value 
  */
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:ScalarOut):PipeReg = pipeReg(stage, scalarOutPR(s))
 /** Create a pipeline register and a scalar buffer for a stage. 
  *  The pipeline register connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:Scalar):PipeReg = scalarOut(stage, newSout(s))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, v:VecIn):PipeReg = {
    pipeReg(stage, vecInPR(v))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, vec:Vector):PipeReg = vecIn(stage, newVin(vec))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  * @param vo: VecOut of current ComputeUnit. One per CU 
  */
  def vecOut(stage:Stage, vo:VecOut):PipeReg = {
    pipeReg(stage, vecOutPR(vo))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  */
  def vecOut(stage:Stage, vec:Vector):PipeReg = vecOut(stage, newVout(vec))

  /* Create a new logical register 
   * */
  def temp = Reg(newTemp)

 /** Refer to the pipeline register for stage with rid 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage, reg:Reg):PipeReg = {
    assert(tempRegs.contains(reg), s"PipeReg with reg:${reg} wans't created but try to refer to it")
    pipeReg(stage, reg)
  }
 /** Allocate a new pipeline register in the stage 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage):PipeReg = {
    pipeReg(stage, tempPR())
  }

  def apply(block:this.type => Any) (implicit design:Design):this.type =
    updateBlock(block)
}

object ComputeUnit {
  def apply(name: Option[String], tpe:CtrlType)(implicit design: Design) = new ComputeUnit(name, tpe)
  def apply[P,D](name: Option[String], tpe:CtrlType, parent:P, deps:List[D]) (implicit design: Design, dtp:TypeTag[D]) =
    new ComputeUnit(name, tpe).updateParent(parent).updateDeps(deps)
  /* Sugar API */
  def apply [P,D](parent:P, deps:List[D], tpe:CtrlType) (block: ComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):ComputeUnit =
    ComputeUnit(None, tpe).updateBlock(block).updateParent(parent).updateDeps(deps)
  def apply[P,D](name:String, parent:P, deps:List[D], tpe:CtrlType) (block:ComputeUnit => Any) (implicit design:Design, dtp:TypeTag[D]):ComputeUnit =
    ComputeUnit(Some(name), tpe).updateBlock(block).updateParent(parent).updateDeps(deps)
  /* No Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM])
  (implicit design:Design, dtp:TypeTag[D]):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams).updateParent(parent).updateDeps(deps)
  }
}

/* Corresponding to inner loop unit pipe */
class UnitComputeUnit(override val name: Option[String])(implicit design: Design) extends ComputeUnit(name, Pipe) { self =>
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
  (implicit design:Design) extends ComputeUnit(name, Pipe) {

  /* Fields */
  val dataIn:VecIn = if (mctpe==TileLoad) newVin(memctrl.load) else newVin(vec) 
  val dataOut:VecOut = if (mctpe==TileStore) newVout(memctrl.store) else newVout(vec)

  def in:Vector = dataIn.vector
  def out:Vector = dataOut.vector

  override val typeStr = "TileTransfer"
  def updateBlock(block: TileTransfer => Any)(implicit design: Design):TileTransfer = {
    val cchains = 
      design.addBlock[CounterChain](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain]) 
    updateFields(cchains, Nil)
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
  var innerCUs:List[ComputeUnit] = _
  var outerCUs:List[ComputeUnit] = _
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

  def updateFields(cs:List[ComputeUnit], scalars:List[Scalar], vectors:List[Vector], memCtrls:List[MemoryController]) = {
    //TODO change innerCU and outerCU to a type
    this.innerCUs = cs.filter { c => c.tpe==Pipe }
    this.outerCUs = cs.filter { c => c.tpe!=Pipe }
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
    val (cs, scalars, vectors, memCtrls) = 
      design.addBlock[ComputeUnit, Scalar, Vector, MemoryController](block(this), 
                      (n:Node) => n.isInstanceOf[ComputeUnit],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector], 
                      (n:Node) => n.isInstanceOf[MemoryController] 
                      )
    updateFields(cs, scalars, vectors, memCtrls)
  }
}

