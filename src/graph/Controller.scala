package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.graph.mapper.PIRException
import scala.reflect.runtime.universe._

trait Controller extends Node {
  var sins:List[ScalarIn] = _
  var souts:List[ScalarOut] = _
  var vins:List[VecIn] = _
  var vouts:List[VecOut] = _
  override def toUpdate = (sins==null) || (souts==null) || (vins==null) || (vouts==null)

  def readers:List[Controller] = souts.flatMap(_.scalar.readers.map(_.ctrler)) ++
                                 vouts.flatMap(_.vector.readers.map(_.ctrler))
  def writers:List[Controller] = sins.map(_.scalar.writer.ctrler) ++
                                 vins.map(_.vector.writer.ctrler)

  def updateFields(sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]):this.type = {
    this.sins = sins.toSet.toList 
    this.souts = souts.toSet.toList 
    this.vins = vins.toSet.toList
    this.vouts = vouts.toSet.toList
    this
  }
}

class ComputeUnit(override val name: Option[String], val tpe:CtrlType)(implicit design: Design) extends Controller { self =>
  implicit val ctrler:Controller = self
  override val typeStr = "CU"

  /* Pointer */
  var parent:Controller = _
  // List of controllers the current controller expecting token from 
  val dependencies = ListBuffer[Controller]()

  /* Fields */
  var cchains:List[CounterChain] = _
  var srams:List[SRAM] = _ 
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  val wtAddrStages = ListBuffer[WAStage]()
  val localStages = ListBuffer[Stage]()

  def stages = (wtAddrStages ++ localStages).toList

  override def toUpdate = { 
    super.toUpdate || parent==null || cchains==null || srams==null
  }

  def this(name: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM], 
  sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])(implicit design: Design) = {
    this(name, tpe)
    updateFields(cchains, srams, sins, souts, vins, vouts)
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]):this.type = {
    this.cchains = cchains
    this.srams = srams 
    super.updateFields(sins, souts, vins, vouts)
  }

  def updateBlock(block: this.type => Any)(implicit design: Design):this.type = {
    val (cchains, srams, sins, souts, vins, vouts) = 
      design.addBlock[CounterChain, SRAM, ScalarIn, ScalarOut, VecIn, VecOut](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM],
                            (n:Node) => n.isInstanceOf[ScalarIn],
                            (n:Node) => n.isInstanceOf[ScalarOut],
                            (n:Node) => n.isInstanceOf[VecIn],
                            (n:Node) => n.isInstanceOf[VecOut]
                            ) 
    updateFields(cchains, srams, sins, souts, vins, vouts)
  }

  def updateParent[T](parent:T):this.type = {
    parent match {
      case p:String =>
        design.updateLater(p, (n:Node) => updateParent(n.asInstanceOf[Controller]))
      case p:Controller =>
        this.parent = p 
    }
    this
  }

  def updateDep(dep:Controller):Unit = {
    dependencies += dep
  }

  def updateDeps[T](deps:List[T])(implicit cltp:TypeTag[T]):this.type = {
    deps.foreach { dep =>
      dep match {
        case d:String => 
          design.updateLater(d, (n:Node) => updateDep(n.asInstanceOf[Controller]))
        case d:Controller => 
          updateDep(d)
      }
    }
    this
  }

  var regId = 0
  private def newTemp = {val temp = regId; regId +=1; temp}

  /* Register Mapping */
  val reduceReg  = ReducePR(newTemp)
  val vecIns     = Map[VecIn, VecInPR]()
  val vecOut     = VecOutPR(newTemp)
  val scalarIns  = Map[ScalarIn, ScalarInPR]()
  val scalarOuts = Map[ScalarOut, ScalarOutPR]()
  val loadRegs   = Map[SRAM, LoadPR]()
  val storeRegs  = Map[SRAM, StorePR]()
  val wtAddrRegs = Map[SRAM, WtAddrPR]()
  val rdAddrRegs = Map[SRAM, RdAddrPR]()
  val ctrRegs    = Map[Counter, CtrPR]()
  val tempRegs   = Set[Reg]()
  val accumRegs  = Set[AccumPR]()
  val liveOuts   = Set[Reg]()
  val stageUses  = Map[Stage, Set[Reg]]()
  val stageDefs  = Map[Stage, Set[Reg]]()
  //val stagePRs   = Map[Stage, Map[Int,PipeReg]]()
  def reset      = { regId = 0; loadRegs.clear; storeRegs.clear; ctrRegs.clear; 
    stageUses.clear; stageDefs.clear; localStages.clear; wtAddrStages.clear}

  def addStage(s:Stage):Unit = { s match {
      case ss:WAStage =>
        wtAddrStages += ss
      case ss:Stage =>
        if (localStages.size==0) {
          s.setPRForward
          localStages += s
        }
    }
    s.operands.foreach { opd => 
      opd.src match {
        case pr:PipeReg => addUse(pr)
        case _ =>
      } 
    }
    s.result.src match {
      case pr:PipeReg => addDef(pr)
      case _ =>
    }
  }
  private def addUse(p:PipeReg) = { 
    stageUses(p.stage) += p.reg
}
  private def addDef(p:PipeReg) = stageDefs(p.stage) += p.reg

 /** Create a pipeline register for a stage corresponding to 
  *  the register that loads from the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
 def load(stage:Stage, s:SRAM):PipeReg = {
    if (!loadRegs.contains(s)) loadRegs += (s -> LoadPR(newTemp, s.readPort))
    PipeReg(stage, loadRegs(s))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that stores to the sram
  * @param stage: Stage of the pipeline register 
  * @param s: sram to load from 
  */
  def store(stage:Stage, s:SRAM):PipeReg = {
    if (!storeRegs.contains(s)) storeRegs += (s -> StorePR(newTemp, s.writePort))
    liveOuts += storeRegs(s)
    val pr = PipeReg(stage, storeRegs(s))
    s.writePort = pr.out
    pr
  }
  def wtAddr():WtAddrPR = WtAddrPR(newTemp)
  def wtAddr(stage:Stage, reg:WtAddrPR):PipeReg = {
    val pr = PipeReg(stage, reg)
    val srams = reg.waPorts.map{_.src.asInstanceOf[SRAM]}
    srams.foreach { s => 
      if (!wtAddrRegs.contains(s)) wtAddrRegs += (s -> reg)
      s.writeAddr = pr.out
    }
    liveOuts += reg
    pr
  }
  def rdAddr():RdAddrPR = RdAddrPR(newTemp)
  def rdAddr(stage:Stage, reg:RdAddrPR):PipeReg = {
    val pr = PipeReg(stage, reg)
    val srams = reg.raPorts.map{_.src.asInstanceOf[SRAM]}
    srams.foreach {s =>
      if (!rdAddrRegs.contains(s)) rdAddrRegs += (s -> reg)
      s.readAddr = pr.out
    }
    liveOuts += reg 
    pr
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the counter 
  * @param stage: Stage of the pipeline register 
  * @param c: counter 
  */
  def ctr(stage:Stage, c:Counter):PipeReg = {
    if (!ctrRegs.contains(c)) ctrRegs += (c -> CtrPR(newTemp, c))
    PipeReg(stage, ctrRegs(c))
  }
  /* Create a new logical accumulator register */
  def accum(init:Const):AccumPR = {
    AccumPR(newTemp, init)
  }
  /* Create a new logical accumulator register and return a PipeReg for the stage and the created
   * accumulator 
  * @param stage: Stage of the pipeline register 
   * @param init initial value of the accumulator
   * */
  def accum(stage:Stage, init:Const):PipeReg = {
    val reg = accum(init) 
    accum(stage, reg)
    PipeReg(stage, reg)
  }
  /* Create a pipeline register for a stage that connects to the accumulator reg 
   * @param stage
   * @param acc 
   */
  def accum(stage:Stage, acc:AccumPR):PipeReg = {
    accumRegs += acc 
    PipeReg(stage, acc)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the reduction network 
  * @param stage: Stage of the pipeline register 
  * @param i: initial value
  */
  def reduce(stage:Stage):PipeReg = {
    PipeReg(stage, reduceReg)
  }
 /** Create a ScalarIn object 
  * @param s: scalar value 
  */
  def scalarIn(s:Scalar):ScalarIn = ScalarIn(s)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param s: ScalarIn buffer 
  */
  def scalarIn(stage:Stage, s:ScalarIn):PipeReg = {
    if (!scalarIns.contains(s)) scalarIns += (s -> ScalarInPR(newTemp, s))
    PipeReg(stage, scalarIns(s))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage of the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarIn(stage:Stage, s:Scalar):PipeReg = scalarIn(stage, ScalarIn(s))
  /** Create a ScalarOut object 
  * @param s: scalar value 
  */
  //No use case
  def scalarOut(s:Scalar):ScalarOut = ScalarOut(s)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:ScalarOut):PipeReg = {
    if (!scalarOuts.contains(s)) scalarOuts += (s -> ScalarOutPR(newTemp, s))
    val reg = scalarOuts(s)
    liveOuts += reg 
    PipeReg(stage, reg)
  }
 /** Create a pipeline register and a scalar buffer for a stage. 
  *  The pipeline register connects to the scalarOut buffer
  * @param stage: Stage of the pipeline register 
  */
  def scalarOut(stage:Stage, s:Scalar):PipeReg = scalarOut(stage, ScalarOut(s))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, v:VecIn):PipeReg = {
    if (!vecIns.contains(v)) vecIns += (v -> VecInPR(newTemp, v))
    PipeReg(stage, vecIns(v))
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage of the pipeline register 
  */
  def vecIn(stage:Stage, vec:Vector):PipeReg = vecIn(stage, VecIn(vec))
  def vecIn(vec:Vector):Port = VecIn(vec).out
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  * @param vo: VecOut of current ComputeUnit. One per CU 
  */
  def vecOut(stage:Stage, vo:VecOut):PipeReg = {
    vecOut.vecOut = vo
    liveOuts += vecOut
    PipeReg(stage, vecOut)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage of the pipeline register 
  */
  def vecOut(stage:Stage, vec:Vector):PipeReg = vecOut(stage, VecOut(vec))

  /* Create a new logical register 
   * */
  def temp = Reg(newTemp)

 /** Refer to the pipeline register for stage with rid 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage, reg:Reg):PipeReg = {
    assert(tempRegs.contains(reg), s"PipeReg with reg:${reg} wans't created but try to refer to it")
    PipeReg(stage, reg)
  }
 /** Allocate a new pipeline register in the stage 
  * @param stage: Stage of the pipeline register 
  */
  def temp(stage:Stage):PipeReg = {
    val reg = Reg(newTemp)
    tempRegs += reg 
    PipeReg(stage, reg)
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
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])
  (implicit design:Design, dtp:TypeTag[D]):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent).updateDeps(deps)
  }
}

/* Corresponding to inner loop unit pipe */
class UnitComputeUnit(override val name: Option[String])(implicit design: Design) extends ComputeUnit(name, Pipe) { self =>
  override val typeStr = "UnitCompUnit"
  def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut]):UnitComputeUnit = 
      super.updateFields(cchains, srams, sins, souts, Nil, Nil).asInstanceOf[UnitComputeUnit]
  def updateBlock(block: UnitComputeUnit => Any)(implicit design: Design):UnitComputeUnit = {
    val (cchains, srams, sins, souts) = 
      design.addBlock[CounterChain, SRAM, ScalarIn, ScalarOut](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM],
                            (n:Node) => n.isInstanceOf[ScalarIn],
                            (n:Node) => n.isInstanceOf[ScalarOut]
                            ) 
    updateFields(cchains, srams, sins, souts)
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
  def apply[P,D](name:Option[String], parent:P, deps:List[D], cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut])
  (implicit design:Design, dtp:TypeTag[D]):UnitComputeUnit = {
    UnitComputeUnit(name, parent, deps).updateFields(cchains, srams, sins, souts)
  }
}

case class TileTransfer(override val name:Option[String], memctrl:MemoryController, mctpe:MCType)
  (implicit design:Design) extends ComputeUnit(name, Pipe) {

  /* Fields */
  val dataIn:VecIn = if (mctpe==TileLoad) VecIn(memctrl.load) else VecIn(Vector()) 
  val dataOut:VecOut = if (mctpe==TileStore) VecOut(memctrl.store) else VecOut(Vector())

  def in:Vector = dataIn.vector
  def out:Vector = dataOut.vector

  override val typeStr = "TileTransfer"
  def updateBlock(block: TileTransfer => Any)(implicit design: Design):TileTransfer = {
    val (cchains, srams, sins, souts, vins, vouts) = 
      design.addBlock[CounterChain, SRAM, ScalarIn, ScalarOut, VecIn, VecOut](block(this), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM],
                            (n:Node) => n.isInstanceOf[ScalarIn],
                            (n:Node) => n.isInstanceOf[ScalarOut],
                            (n:Node) => n.isInstanceOf[VecIn],
                            (n:Node) => n.isInstanceOf[VecOut]
                            ) 
    updateFields(cchains, srams, sins, souts, vins, vouts)
  }

} 
object TileTransfer extends {
  /* Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType)(implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(name, memctrl, mctpe).updateParent(parent).updateDeps(deps)
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(name, memctrl, mctpe).updateBlock(block).updateParent(parent).updateDeps(deps)
  def apply[P,D](name:String, parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design:Design, dtp:TypeTag[D]):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe).updateBlock(block).updateParent(parent).updateDeps(deps)
  /* No Sugar API */
  def apply[P,D](name:Option[String], parent:P, deps:List[D], memctrl:MemoryController, mctpe:MCType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])   (implicit design:Design, dtp:TypeTag[D]):TileTransfer = {
    TileTransfer(name, memctrl, mctpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent).updateDeps(deps)
  }
}

case class MemoryController(name: Option[String], mctpe:MCType, offchip:OffChip)(implicit design: Design) extends Controller { self =>
  implicit val ctrler:Controller = self

  val typeStr = "MemoryController"
  val addr = ScalarIn(Scalar())
  val dataIn = if (mctpe==TileStore) Some(VecIn(Vector())) else None
  val dataOut = if (mctpe==TileLoad) Some(VecOut(Vector())) else None

  def saddr = addr.scalar 
  def load = if (mctpe==TileLoad) dataOut.get.vector
    else throw PIRException(s"Cannot load from a MemoryController with mctpe=${mctpe}")
  def store = if(mctpe==TileStore) dataIn.get.vector 
    else throw PIRException(s"Cannot store to a MemoryController with mctpe=${mctpe}")

  def updateFields = {
    super.updateFields(List(addr), Nil, dataIn.toList, dataOut.toList)
  }
}
object MemoryController {
  def apply(mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = MemoryController(None, mctpe, offchip)
  def apply(name:String, mctpe:MCType, offchip:OffChip)(implicit design: Design): MemoryController 
    = MemoryController(Some(name), mctpe, offchip)
}

case class Top()(implicit design: Design) extends Controller { self =>
  implicit val ctrler:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  var compUnits:List[ComputeUnit] = _
  var memctrls:List[MemoryController] = _
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  override def toUpdate = super.toUpdate || compUnits==null || memctrls==null

  def updateFields(cs:List[ComputeUnit], scalars:List[Scalar], memctrls:List[MemoryController]) = {
    this.compUnits = cs
    this.memctrls = memctrls
    val sins = ListBuffer[ScalarIn]()
    val souts = ListBuffer[ScalarOut]()
    scalars.foreach { s => s match {
        case a:ArgIn => souts += ScalarOut(a)
        case a:ArgOut => sins += ScalarIn(a)
        case _ =>
      }
    }
    memctrls.foreach { oc => oc.updateFields }
    super.updateFields(sins.toList, souts.toList, Nil, Nil)
    this
  }

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
    val (cs, scalars, memctrls) = 
      design.addBlock[ComputeUnit, Scalar, MemoryController](block(this), 
                      (n:Node) => n.isInstanceOf[ComputeUnit],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[MemoryController] 
                      )
    updateFields(cs, scalars, memctrls)
  }
}

