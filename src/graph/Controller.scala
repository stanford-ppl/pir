package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir.Design
import pir.graph._

trait Controller extends Node

case class ComputeUnit(val name: Option[String], tpe:CtrlType)(implicit design: Design) extends Controller {
  override val typeStr = "CU"

  /* Fields */
  var parent:Controller = _
  var cchains:List[CounterChain] = _
  var srams:List[SRAM] = _ 
  var sins:List[ScalarIn] = _
  var souts:List[ScalarOut] = _
  var pipeline:Pipeline = _

  toUpdate = true

  def this(name: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM], 
  sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline)(implicit design: Design) = {
    this(name, tpe)
    updateFields(cchains, srams, sins, souts, pipeline)
  }

  def updateFields(cchains:List[CounterChain], srams:List[SRAM], sins:List[ScalarIn], 
    souts:List[ScalarOut], pipeline:Pipeline) = {
    this.cchains = cchains
    this.srams = srams 
    this.sins = sins 
    this.souts = souts 
    this.pipeline = pipeline 
    this.cchains.foreach(_.updateCtrler(this))
    this.srams.foreach(_.updateCtrler(this))
    this.sins.foreach(_.updateCtrler(this))
    this.souts.foreach(_.updateCtrler(this))
    this.pipeline.updateCtrler(this)
    this
  }

  def unwrapBlock(block: Pipeline => Any)(implicit design: Design):ComputeUnit = {
    val pipeline = Pipeline(None)
    val (cchains, srams, sins, souts) = 
      design.addBlock[CounterChain, SRAM, ScalarIn, ScalarOut](block(pipeline), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM],
                            (n:Node) => n.isInstanceOf[ScalarIn],
                            (n:Node) => n.isInstanceOf[ScalarOut]
                            ) 
    updateFields(cchains, srams, sins, souts, pipeline)
    this
  }

  def updateParent(parent:String):ComputeUnit = {
    design.updateLater(parent, (n:Node) => updateParent(n.asInstanceOf[Controller]))
    this
  }
  def updateParent(parent:Controller):ComputeUnit = { this.parent = parent; toUpdate = false; this }
}

object ComputeUnit {
  /* Sugar API */
  def apply (parent:String, tpe:CtrlType) (block: Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, tpe).unwrapBlock(block).updateParent(parent)
  def apply (name:String, parent: String, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), tpe).unwrapBlock(block).updateParent(parent)
  def apply (parent:Controller, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, tpe).unwrapBlock(block).updateParent(parent)
  def apply (name:String, parent: Controller, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), tpe).unwrapBlock(block).updateParent(parent)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts, pipeline).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):ComputeUnit = {
    ComputeUnit(name, tpe).updateFields(cchains, srams, sins, souts, pipeline).updateParent(parent)
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
    souts:List[ScalarOut], pipeline:Pipeline):MemoryController = 
      super.updateFields(cchains, srams, sins, souts, pipeline).asInstanceOf[MemoryController]
  override def unwrapBlock(block: Pipeline => Any)(implicit design: Design):MemoryController =
    super.unwrapBlock(block).asInstanceOf[MemoryController]
} 
object MemoryController extends {
  def apply(name:Option[String], d:String) (implicit design: Design):MemoryController = 
    new {override val dram = d} with ComputeUnit(name, Pipe) with MemoryController
  /* Sugar API */
  def apply (parent:String, dram:String) (block: Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(None, dram).unwrapBlock(block).updateParent(parent)
  def apply (name:String, parent: String, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), dram).unwrapBlock(block).updateParent(parent)
  def apply (parent:Controller, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(None, dram).unwrapBlock(block).updateParent(parent)
  def apply (name:String, parent: Controller, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), dram).unwrapBlock(block).updateParent(parent)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):MemoryController = {
    MemoryController(name, dram).updateFields(cchains, srams, sins, souts, pipeline).updateParent(parent)
  }
  def apply(name:Option[String], parent:String, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):MemoryController = {
    MemoryController(name, dram).updateFields(cchains, srams, sins, souts, pipeline).updateParent(parent)
  }
}

case class Top(ctrlNodes:List[Controller])(implicit design: Design) extends Controller {
  override val name = Some("Top")
  override val typeStr = "Top"
    val argIns = ListBuffer[ArgIn]()
    val argOuts = ListBuffer[ArgOut]()
}
