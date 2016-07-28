package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir.Design
import pir.graph._
import pir.graph.mapper.PIRException

trait Controller extends Node {
  var sins:List[ScalarIn] = _
  var souts:List[ScalarOut] = _
  var vins:List[VecIn] = _
  var vouts:List[VecOut] = _
  override def toUpdate = (sins==null) || (souts==null) || (vins==null) || (vouts==null)

  def readers:List[Controller] = souts.flatMap(_.scalar.readers) ++ vouts.flatMap(_.vector.readers)
  def writers:List[Controller] = sins.map(_.scalar.writers.head) ++ vins.map(_.vector.writers.head)

  def updateFields(sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]) = {
    this.sins = sins.toSet.toList 
    this.souts = souts.toSet.toList 
    this.vins = vins.toSet.toList
    this.vouts = vouts.toSet.toList
  }
}

class ComputeUnit(override val name: Option[String], val tpe:CtrlType)(implicit design: Design) extends Controller { self =>
  implicit val ctrler:Controller = self
  override val typeStr = "CU"

  /* Pointer */
  var parent:Controller = _

  /* Fields */
  var cchains:List[CounterChain] = _
  var srams:List[SRAM] = _ 
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  val stages = ListBuffer[Stage]()

  override def toUpdate = { 
    super.toUpdate || parent==null || cchains==null || srams==null
  }

  def this(name: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM], 
  sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])(implicit design: Design) = {
    this(name, tpe)
    updateFields(cchains, srams, sins, souts, vins, vouts)
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]) = {
    this.cchains = cchains
    this.srams = srams 
    super.updateFields(sins, souts, vins, vouts)
    this
  }

  def updateBlock(block: ComputeUnit => Any)(implicit design: Design):ComputeUnit = {
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
    this
  }

  def updateParent(parent:String):ComputeUnit = {
    design.updateLater(parent, (n:Node) => updateParent(n.asInstanceOf[Controller]))
    this
  }
  def updateParent(parent:Controller):ComputeUnit = { this.parent = parent; this }

  var regId = 0
  private def newTemp = {val temp = regId; regId +=1; temp}

  /* Register Mapping */
  val reduceReg = newTemp
  val vecIns = HashMap[VecIn, Int]() 
  val vecOut = newTemp
  val scalarIns = HashMap[ScalarIn, Int]() 
  val scalarOuts = HashMap[ScalarOut, Int]() 
  val loadRegs  = HashMap[SRAM, Int]()
  val storeRegs  = HashMap[SRAM, Int]()
  val ctrRegs   = HashMap[Counter, Int]()
  val tempRegs  = ListBuffer[Int]()

  val stageUses = HashMap[Stage, Set[PipeReg]]()
  val stageDefs = HashMap[Stage, Set[PipeReg]]()
  val stagePRs  = HashMap[Stage, HashMap[Int,PipeReg]]()
  def reset     = { regId = 0; loadRegs.clear; storeRegs.clear; ctrRegs.clear; stageUses.clear; stageDefs.clear }

  def addStage(s:Stage):Unit = {
    stages += s
    s.operands.foreach { opd => opd.src match {
        case pr:PipeReg => addUse(pr)
        case _ =>
      } 
    }
    s.result.src match {
      case pr:PipeReg => addDef(pr)
      case _ =>
    }
  }
  private def addUse(p:PipeReg) = stageUses(p.stage) += p
  private def addDef(p:PipeReg) = stageDefs(p.stage) += p

 /** Create a pipeline register for a stage corresponding to 
  *  the register that loads from the sram
  * @param stage: Stage for the pipeline register 
  * @param s: sram to load from 
  */
 def load(stage:Stage, s:SRAM):PipeReg = {
    if (!loadRegs.contains(s)) loadRegs += (s -> newTemp)
    val prs = stagePRs(stage); val rid = loadRegs(s)
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with LoadPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that stores to the sram
  * @param stage: Stage for the pipeline register 
  * @param s: sram to load from 
  */
  def stores(stage:Stage, s:SRAM):PipeReg = {
    if (!storeRegs.contains(s)) storeRegs += (s -> newTemp)
    val prs = stagePRs(stage); val rid = storeRegs(s)
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with StorePR)
    s.writePort = prs(rid).out
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the counter 
  * @param stage: Stage for the pipeline register 
  * @param c: counter 
  */
  def ctr(stage:Stage, c:Counter):PipeReg = {
    if (!ctrRegs.contains(c)) ctrRegs += (c -> newTemp)
    val prs = stagePRs(stage); val rid = ctrRegs(c)
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with CtrPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the reduction network 
  * @param stage: Stage for the pipeline register 
  */
  def reduce(stage:Stage):PipeReg = {
    val prs = stagePRs(stage); val rid = reduceReg
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with ReducePR)
    prs(rid)
  }
 /** Create a ScalarIn object 
  * @param s: scalar value 
  */
  def scalarIn(s:Scalar):ScalarIn = ScalarIn(s)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage for the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarIn(stage:Stage, s:ScalarIn):PipeReg = {
    if (!scalarIns.contains(s)) scalarIns += (s -> newTemp)
    val prs = stagePRs(stage); val rid = scalarIns(s)
    if (!prs.contains(rid)) 
      prs += (rid -> new { override val scalarIn = s} with PipeReg(stage, rid) with ScalarInPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarIn buffer with register rid
  * @param stage: Stage for the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarIn(stage:Stage, s:Scalar):PipeReg = scalarIn(stage, ScalarIn(s))
  /** Create a ScalarOut object 
  * @param s: scalar value 
  */
  //No use case
  //def scalarOut(s:Scalar):ScalarOut = ScalarOut(s)
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarOut buffer
  * @param stage: Stage for the pipeline register 
  */
  def scalarOut(stage:Stage, s:ScalarOut):PipeReg = {
    if (!scalarOuts.contains(s)) scalarOuts += (s -> newTemp)
    val prs = stagePRs(stage); val rid = scalarOuts(s)
    if (!prs.contains(rid)) 
      prs += (rid -> new { override val scalarOut = s } with PipeReg(stage, rid) with ScalarOutPR)
    prs(rid)
  }
 /** Create a pipeline register and a scalar buffer for a stage. 
  *  The pipeline register connects to the scalarOut buffer
  * @param stage: Stage for the pipeline register 
  */
  def scalarOut(stage:Stage, s:Scalar):PipeReg = scalarOut(stage, ScalarOut(s))
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage for the pipeline register 
  */
  def vecIn(stage:Stage, v:VecIn):PipeReg = {
    if (!vecIns.contains(v)) vecIns += (v -> newTemp)
    val prs = stagePRs(stage); val rid = vecIns(v)
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with VecInPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage for the pipeline register 
  */
  def vecIn(stage:Stage, vec:Vector):PipeReg = vecIn(stage, VecIn(vec))
  def vecIn(vec:Vector):Port = VecIn(vec).out
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage for the pipeline register 
  * @param vo: VecOut of current ComputeUnit. One per CU 
  */
  def vecOut(stage:Stage, vo:VecOut):PipeReg = {
    val prs = stagePRs(stage); val rid = vecOut 
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with VecOutPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage for the pipeline register 
  */
  def vecOut(stage:Stage, vec:Vector):PipeReg = vecOut(stage, VecOut(vec))

  /* Create a new register id for mapping
   * */
  def temp = newTemp

 /** Get the pipeline register for stage with rid 
  * @param stage: Stage for the pipeline register 
  */
  def temp(stage:Stage, rid:Int):PipeReg = {
    val prs = stagePRs(stage)
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with TempPR)
    prs(rid)
  }
 /** Allocate a new pipeline register in the stage 
  * @param stage: Stage for the pipeline register 
  */
  def temp(stage:Stage):PipeReg = {
    val prs = stagePRs(stage); val rid = newTemp
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with TempPR)
    prs(rid)
  }

}

object ComputeUnit {
  def apply(name: Option[String], tpe:CtrlType)(implicit design: Design) = new ComputeUnit(name, tpe)
  /* Sugar API */
  def apply (parent:String, tpe:CtrlType) (block: ComputeUnit => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, tpe).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: String, tpe:CtrlType) (block:ComputeUnit => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), tpe).updateBlock(block).updateParent(parent)
  def apply (parent:Controller, tpe:CtrlType) (block:ComputeUnit => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, tpe).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: Controller, tpe:CtrlType) (block:ComputeUnit => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), tpe).updateBlock(block).updateParent(parent)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])
  (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]) (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent)
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
  override def updateParent(parent:String):TileTransfer = 
    super.updateParent(parent).asInstanceOf[TileTransfer]
  override def updateParent(parent:Controller):TileTransfer = 
    super.updateParent(parent).asInstanceOf[TileTransfer]
  override def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]):TileTransfer = 
      super.updateFields(cchains, srams, sins, souts, vins, vouts).asInstanceOf[TileTransfer]
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
    this
  }
} 
object TileTransfer extends {
  /* Sugar API */
  def apply (parent:String, memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design: Design):TileTransfer =
    TileTransfer(None, memctrl, mctpe).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: String, memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design: Design):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe).updateBlock(block).updateParent(parent)
  def apply (parent:Controller, memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design: Design):TileTransfer =
    TileTransfer(None, memctrl, mctpe).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: Controller, memctrl:MemoryController, mctpe:MCType) (block:TileTransfer => Any) (implicit design: Design):TileTransfer =
    TileTransfer(Some(name), memctrl, mctpe).updateBlock(block).updateParent(parent)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, memctrl:MemoryController, mctpe:MCType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut])   (implicit design: Design):TileTransfer = {
    TileTransfer(name, memctrl, mctpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, memctrl:MemoryController, mctpe:MCType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], vins:List[VecIn], vouts:List[VecOut]) (implicit design: Design):TileTransfer = {
    TileTransfer(name, memctrl, mctpe).updateFields(cchains, srams, sins, souts, vins, vouts).updateParent(parent)
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
  var ctrlNodes:List[ComputeUnit] = _
  var memctrls:List[MemoryController] = _
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  //  vins:List[VecIn] = _
  //  vouts:List[VecOut] = _
  override def toUpdate = super.toUpdate || ctrlNodes==null || memctrls==null

  def updateFields(cs:List[ComputeUnit], scalars:List[Scalar], memctrls:List[MemoryController]) = {
    this.ctrlNodes = cs
    this.memctrls = memctrls
    val sins = ListBuffer[ScalarIn]()
    val souts = ListBuffer[ScalarOut]()
    scalars.foreach { s => s match {
        case a:ArgIn => souts += ScalarOut(a.addWriter(this))
        case a:ArgOut => sins += ScalarIn(a.addReader(this))
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

