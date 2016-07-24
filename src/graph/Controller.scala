package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir.Design
import pir.graph._

trait Controller extends Node {
  var sins:List[ScalarIn] = _
  var souts:List[ScalarOut] = _
  def updateFields(sins:List[ScalarIn], souts:List[ScalarOut]) = {
    this.sins = sins 
    this.souts = souts 
  }
}

case class ComputeUnit(val name: Option[String], tpe:CtrlType)(implicit design: Design) extends Controller { self =>
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
  toUpdate = true

  def this(name: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM], 
  sins:List[ScalarIn], souts:List[ScalarOut])(implicit design: Design) = {
    this(name, tpe)
    updateFields(cchains, srams, sins, souts)
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut]) = {
    this.cchains = cchains
    this.srams = srams 
    super.updateFields(sins, souts)
    this
  }

  def updateBlock(block: ComputeUnit => Any)(implicit design: Design):ComputeUnit = {
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

  def updateParent(parent:String):ComputeUnit = {
    design.updateLater(parent, (n:Node) => updateParent(n.asInstanceOf[Controller]))
    this
  }
  def updateParent(parent:Controller):ComputeUnit = { this.parent = parent; toUpdate = false; this }

  var regId = 0
  private def newTemp = {val temp = regId; regId +=1; temp}

  /* Register Mapping */
  val reduceReg = newTemp
  val vecIn = newTemp
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
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to 1 scalarIn buffer 
  * @param stage: Stage for the pipeline register 
  */
  //def scalarIn(stage:Stage):PipeReg = {
  //  val rid = newTemp; scalarIns += rid 
  //  val prs = stagePRs(stage)
  //  if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with ScalarInPR)
  //  prs(rid)
  //}
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
  *  the register that connects to 1 scalarOut buffer 
  * @param stage: Stage for the pipeline register 
  */
  //def scalarOut(stage:Stage):PipeReg = {
  //  val rid = newTemp; scalarOuts += rid 
  //  val prs = stagePRs(stage)
  //  if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with ScalarOutPR)
  //  prs(rid)
  //}
 /** Create a pipeline register for a stage corresponding to 
  *  the register that connects to the scalarOut buffer with register rid
  * @param stage: Stage for the pipeline register 
  * @param rid: reg rid of scalar input 
  */
  def scalarOut(stage:Stage, s:ScalarOut):PipeReg = {
    if (!scalarOuts.contains(s)) scalarOuts += (s -> newTemp)
    val prs = stagePRs(stage); val rid = scalarOuts(s)
    if (!prs.contains(rid)) 
      prs += (rid -> new { override val scalarOut = s } with PipeReg(stage, rid) with ScalarOutPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU input ports in streaming communication 
  * @param stage: Stage for the pipeline register 
  */
  def vecIn(stage:Stage):PipeReg = {
    val prs = stagePRs(stage); val rid = vecIn
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with VecInPR)
    prs(rid)
  }
 /** Create a pipeline register for a stage corresponding to 
  *  the register that directly connects to CU output ports 
  * @param stage: Stage for the pipeline register 
  */
  def vecOut(stage:Stage):PipeReg = {
    val prs = stagePRs(stage); val rid = vecOut 
    if (!prs.contains(rid)) prs += (rid -> new PipeReg(stage, rid) with VecOutPR)
    prs(rid)
  }
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
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut]) (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut]) (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts).updateParent(parent)
  }
}

trait MemoryController extends ComputeUnit {
  val dram:String
  override val typeStr = "MemCtrl"
  override def updateParent(parent:String):MemoryController = 
    super.updateParent(parent).asInstanceOf[MemoryController]
  override def updateParent(parent:Controller):MemoryController = 
    super.updateParent(parent).asInstanceOf[MemoryController]
  override def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut]):MemoryController = 
      super.updateFields(cchains, srams, sins, souts).asInstanceOf[MemoryController]
  override def updateBlock(block: ComputeUnit => Any)(implicit design: Design):MemoryController =
    super.updateBlock(block).asInstanceOf[MemoryController]
} 
object MemoryController extends {
  def apply(name:Option[String], d:String) (implicit design: Design):MemoryController = 
    new {override val dram = d} with ComputeUnit(name, Pipe) with MemoryController
  /* Sugar API */
  def apply (parent:String, dram:String) (block: ComputeUnit => Any) (implicit design: Design):MemoryController =
    MemoryController(None, dram).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: String, dram:String) (block:ComputeUnit => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), dram).updateBlock(block).updateParent(parent)
  def apply (parent:Controller, dram:String) (block:ComputeUnit => Any) (implicit design: Design):MemoryController =
    MemoryController(None, dram).updateBlock(block).updateParent(parent)
  def apply (name:String, parent: Controller, dram:String) (block:ComputeUnit => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), dram).updateBlock(block).updateParent(parent)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut]) (implicit design: Design):MemoryController = {
    MemoryController(name, dram).updateFields(cchains, srams, sins, souts).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut]) (implicit design: Design):MemoryController = {
    MemoryController(name, dram).updateFields(cchains, srams, sins, souts).updateParent(parent)
  }
}

case class Top()(implicit design: Design) extends Controller { self =>
  implicit val ctrler:Controller = self

  override val name = Some("Top")
  override val typeStr = "Top"

  /* Fields */
  var ctrlNodes:List[Controller] = _
  //  sins:List[ScalarIn] = _
  //  souts:List[ScalarOut] = _
  
  def this(cs:List[Controller], ais:List[ArgIn], aos:List[ArgOut])(implicit design: Design) = {
    this()
    updateFields(cs, ais, aos)
  }

  def updateFields(cs:List[Controller], ais:List[ArgIn], aos:List[ArgOut]) = {
    this.ctrlNodes = cs
    super.updateFields(
      ais.map(ai => new ScalarIn(ai.name, ai) {toUpdate = false}), // No writer
      aos.map(ao => ScalarOut(ao.name, ao))
    )
  }

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
    val (cs, ais, aos) = design.addBlock[Controller, ArgIn, ArgOut](block(this), 
                      (n:Node) => n.isInstanceOf[Controller],
                      (n:Node) => n.isInstanceOf[ArgIn], 
                      (n:Node) => n.isInstanceOf[ArgOut])
    updateFields(cs, ais, aos)
    this
  }
}
