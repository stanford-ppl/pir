package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import pir.Design
import pir.graph._

trait Controller extends Node

case class ComputeUnit(n: Option[String], tpe:CtrlType, cchains:List[CounterChain], srams:List[SRAM], 
  sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) 
(implicit design: Design) extends Node(n, "CU") with Controller {
  var parent:Controller = _
  toUpdate = true
  cchains.foreach(_.updateCtrler(this))
  srams.foreach(_.updateCtrler(this))
  pipeline.updateCtrler(this)

  def update = (p:Controller) => {this.parent = p; toUpdate = false}
}
object ComputeUnit {
  def apply (name:Option[String], parent: String, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit = {
    val (cchains, srams, sins, souts, pipeline) = unwrapBlock(block)
    ComputeUnit(name, parent, tpe, cchains, srams, sins, souts, pipeline)
  }
  def apply(name:Option[String], parent: Controller, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit = {
    val (cchains, srams, sins, souts, pipeline) = unwrapBlock(block)
    ComputeUnit(name, parent, tpe, cchains, srams, sins, souts, pipeline)
  }
  def unwrapBlock(block: Pipeline => Any)(implicit design: Design):
    (List[CounterChain], List[SRAM], List[ScalarIn], List[ScalarOut], Pipeline) = {
    val pipeline = Pipeline(None)
    val (cchains, srams, sins, souts) = 
      design.addBlock[CounterChain, SRAM, ScalarIn, ScalarOut](block(pipeline), 
                            (n:Node) => n.isInstanceOf[CounterChain], 
                            (n:Node) => n.isInstanceOf[SRAM],
                            (n:Node) => n.isInstanceOf[ScalarIn],
                            (n:Node) => n.isInstanceOf[ScalarOut]
                            ) 
    (cchains, srams, sins, souts, pipeline)
  }
  /* Sugar API */
  def apply (parent:String, tpe:CtrlType) (block: Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, parent, tpe) (block)
  def apply (name:String, parent: String, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), parent, tpe) (block)
  def apply (parent:Controller, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(None, parent, tpe) (block)
  def apply (name:String, parent: Controller, tpe:CtrlType) (block:Pipeline => Any) (implicit design: Design):ComputeUnit =
    ComputeUnit(Some(name), parent, tpe) (block)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):ComputeUnit = {
    val c = ComputeUnit(name, tpe, cchains, srams, sins, souts, pipeline); c.update(parent); c
  }
  def apply(name:Option[String], parent:String, tpe:CtrlType, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):ComputeUnit = {
    val c = ComputeUnit(name, tpe, cchains, srams, sins, souts, pipeline)
    design.updateLater(parent, (n:Node) => c.update(n.asInstanceOf[Controller]))
    c
  }
}

trait MemoryController extends ComputeUnit {
  val dram:String
  override val typeStr = "MemCtrl"
} 
object MemoryController extends {
  def apply(name:Option[String], d:String, cchains:List[CounterChain], srams:List[SRAM], 
    sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):MemoryController = {
    new {override val dram = d} with ComputeUnit(name, Pipe, cchains, srams, sins, souts, pipeline) with MemoryController
  }
  def apply (name:Option[String], parent: String, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController = {
    val (cchains, srams, sins, souts, pipeline) = ComputeUnit.unwrapBlock(block)
    MemoryController(name, parent, dram, cchains, srams, sins, souts, pipeline) 
  }
  def apply (name:Option[String], parent: Controller, dram:String)(block:Pipeline => Any) (implicit design: Design):MemoryController = {
    val (cchains, srams, sins, souts, pipeline) = ComputeUnit.unwrapBlock(block)
    MemoryController(name, parent, dram, cchains, srams, sins, souts, pipeline)
  }
  /* Sugar API */
  def apply (parent:String, dram:String) (block: Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(None, parent, dram) (block)
  def apply (name:String, parent: String, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), parent, dram) (block)
  def apply (parent:Controller, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(None, parent, dram) (block)
  def apply (name:String, parent: Controller, dram:String) (block:Pipeline => Any) (implicit design: Design):MemoryController =
    MemoryController(Some(name), parent, dram) (block)
  /* No Sugar API */
  def apply(name:Option[String], parent: Controller, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):MemoryController = {
    val c = MemoryController(name, dram, cchains, srams, sins, souts, pipeline); c.update(parent); c
  }
  def apply(name:Option[String], parent:String, dram:String, cchains:List[CounterChain], 
  srams:List[SRAM], sins:List[ScalarIn], souts:List[ScalarOut], pipeline:Pipeline) (implicit design: Design):MemoryController = {
    val c = MemoryController(name, dram, cchains, srams, sins, souts, pipeline)
    design.updateLater(parent, (n:Node) => c.update(n.asInstanceOf[Controller]))
    c
  }
}

case class Top(ctrlNodes:List[Controller])(implicit design: Design) 
  extends Node(Some("Top"), "Top") with Controller {
    val argIns = ListBuffer[ArgIn]()
    val argOuts = ListBuffer[ArgOut]()
}
