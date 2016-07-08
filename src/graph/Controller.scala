package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

class Controller(name: Option[String], block: => Any,typeStr:String)(implicit design: Design) extends Node(name, typeStr) {
  val nodes = design.addBlock(block, (n:Node) => true) 
}

class InnerController(name: Option[String], block: => Any, typeStr:String)(implicit design: Design) extends Controller(name, block, typeStr) {
  var parent:Controller = _
  val cchains = nodes.filter(n => n.isInstanceOf[CounterChain]).asInstanceOf[List[CounterChain]] 
  def content = List(s"""cchains: [
    ${if (cchains.size > 0) cchains.map(_.toString).reduce{_ + ",\n" + _} else ""} 
  ]""")
  override def toString = {
s"""${super.toString}(${s"parent=${parent.title}"}) { 
  ${content.reduce(_+"\n"+_)}
}"""
  }

  nodes.foreach{_ match {
    case p:Primitive => p.ctrler = this
    case _ =>
  }}

  def this(name:Option[String], block: => Any, p:Controller, typeStr:String) (implicit design: Design){
    this(name, block, typeStr)
    parent = p
  }

  def this(name:Option[String], block: => Any, parStr:String, typeStr:String) (implicit design: Design){
    this(name, block, typeStr)
    design.updateLater(parStr, (n:Node) => this.parent = n.asInstanceOf[Controller])
  }
}

trait ComputeUnit extends InnerController {
  val pipeline = {
    val temp = nodes.filter(n => n.isInstanceOf[Pipeline]) 
    assert(temp.size == 1, "ComputeUnit has exactly 1 Pipeline")
    temp(0).asInstanceOf[Pipeline]
  }
  override def content = super.content :+ s"  ${pipeline}"
}
object ComputeUnit {
  val typeStr = "CU"
  def apply (parent:String) (block: => Any) (implicit design: Design):ComputeUnit =
    new InnerController(None, block, parent, typeStr) with ComputeUnit
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):ComputeUnit =
    new InnerController(Some(name), block, parent, typeStr) with ComputeUnit
  def apply (parent:InnerController) (block: => Any) (implicit design: Design):ComputeUnit =
    new InnerController(None, block, parent, typeStr) with ComputeUnit
  def apply (name:String, parent: InnerController) (block: => Any) (implicit design: Design):ComputeUnit =
    new InnerController(Some(name), block, parent, typeStr) with ComputeUnit
}

trait MemoryController extends InnerController
object MemoryController {
  val typeStr = "MemCtrl"
  def apply (parent:String) (block: => Any) (implicit design: Design):MemoryController =
    new InnerController(None, block, parent, typeStr) with MemoryController
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):MemoryController =
    new InnerController(Some(name), block, parent, typeStr) with MemoryController
  def apply (parent:Controller) (block: => Any) (implicit design: Design):MemoryController =
    new InnerController(None, block, parent, typeStr) with MemoryController
  def apply (name:String, parent: InnerController) (block: => Any) (implicit design: Design):MemoryController =
    new InnerController(Some(name), block, parent, typeStr) with MemoryController
}

trait Sequential extends InnerController
object Sequential {
  val typeStr = "Seq"
  def apply (parent:String) (block: => Any) (implicit design: Design):Sequential =
    new InnerController(None, block, parent, typeStr) with Sequential
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):Sequential =
    new InnerController(Some(name), block, parent, typeStr) with Sequential
  def apply (parent:Controller) (block: => Any) (implicit design: Design):Sequential =
    new InnerController(None, block, parent, typeStr) with Sequential
  def apply (name:String, parent: InnerController) (block: => Any) (implicit design: Design):Sequential =
    new InnerController(Some(name), block, parent, typeStr) with Sequential
}

trait MetaPipeline extends InnerController
object MetaPipeline {
  val typeStr = "MetaPipe"
  def apply (parent:String) (block: => Any) (implicit design: Design):MetaPipeline =
    new InnerController(None, block, parent, typeStr) with MetaPipeline
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):MetaPipeline =
    new InnerController(Some(name), block, parent, typeStr) with MetaPipeline
  def apply (parent:Controller) (block: => Any) (implicit design: Design):MetaPipeline =
    new InnerController(None, block, parent, typeStr) with MetaPipeline
  def apply (name:String, parent: InnerController) (block: => Any) (implicit design: Design):MetaPipeline =
    new InnerController(Some(name), block, parent, typeStr) with MetaPipeline
}

trait Top extends Controller {
  val ctrlList:List[Controller] =
    nodes.filter(_.isInstanceOf[Controller]).asInstanceOf[List[Controller]] 
  val cuList:List[ComputeUnit] =
    nodes.filter(_.isInstanceOf[ComputeUnit]).asInstanceOf[List[ComputeUnit]] 
  val mcList:List[MemoryController] =
    nodes.filter(_.isInstanceOf[MemoryController]).asInstanceOf[List[MemoryController]]
}
object Top {
  val typeStr = "Top"
  def apply (block: => Any) (implicit design: Design):Top =
    new Controller(Some("Top"), block, typeStr) with Top
}
