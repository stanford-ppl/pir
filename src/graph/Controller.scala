package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

class Controller(nameStr: Option[String], block: => Any, var parent:Option[Controller], typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr) {
  if (nameStr.isDefined) design.addName(this, name)

  val nodes = design.addBlock(block, (n:Node) => true) 
  val cchains = nodes.filter(n => n.isInstanceOf[CounterChain]) 


  def content = List(s"""cchains: [
    ${if (cchains.size > 0) cchains.map(_.toString).reduce{_ + ",\n" + _} else ""} 
  ]""")
  override def toString = {
s"""${super.toString} { 
  ${content.reduce(_+"\n"+_)}
}"""
  }

  def this(nameStr:Option[String], block: => Any, parStr:String, typeStr:String) (implicit design: Design){
    this(nameStr, block, None, typeStr)
    design.updateLater(parStr, (n:Node) => this.parent = Some(n.asInstanceOf[Controller]))
  }

}

trait ComputeUnit extends Controller {
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
    new Controller(None, block, parent, typeStr) with ComputeUnit
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):ComputeUnit =
    new Controller(Some(name), block, parent, typeStr) with ComputeUnit
  def apply (parent:Controller) (block: => Any) (implicit design: Design):ComputeUnit =
    new Controller(None, block, Some(parent), typeStr) with ComputeUnit
  def apply (name:String, parent: Controller) (block: => Any) (implicit design: Design):ComputeUnit =
    new Controller(Some(name), block, Some(parent), typeStr) with ComputeUnit
}

trait MemoryController extends Controller
object MemoryController {
  val typeStr = "MemCtrl"
  def apply (parent:String) (block: => Any) (implicit design: Design):MemoryController =
    new Controller(None, block, parent, typeStr) with MemoryController
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):MemoryController =
    new Controller(Some(name), block, parent, typeStr) with MemoryController
  def apply (parent:Controller) (block: => Any) (implicit design: Design):MemoryController =
    new Controller(None, block, Some(parent), typeStr) with MemoryController
  def apply (name:String, parent: Controller) (block: => Any) (implicit design: Design):MemoryController =
    new Controller(Some(name), block, Some(parent), typeStr) with MemoryController
}

trait Sequential extends Controller
object Sequential {
  val typeStr = "Sequential"
  def apply (parent:String) (block: => Any) (implicit design: Design):Sequential =
    new Controller(None, block, parent, typeStr) with Sequential
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):Sequential =
    new Controller(Some(name), block, parent, typeStr) with Sequential
  def apply (parent:Controller) (block: => Any) (implicit design: Design):Sequential =
    new Controller(None, block, Some(parent), typeStr) with Sequential
  def apply (name:String, parent: Controller) (block: => Any) (implicit design: Design):Sequential =
    new Controller(Some(name), block, Some(parent), typeStr) with Sequential
  def apply (block: => Any) (implicit design: Design):Sequential =
    new Controller(None, block, None, typeStr) with Sequential
}

trait MetaPipeline extends Controller
object MetaPipeline {
  val typeStr = "MetaPipe"
  def apply (parent:String) (block: => Any) (implicit design: Design):MetaPipeline =
    new Controller(None, block, parent, typeStr) with MetaPipeline
  def apply (name:String, parent: String) (block: => Any) (implicit design: Design):MetaPipeline =
    new Controller(Some(name), block, parent, typeStr) with MetaPipeline
  def apply (parent:Controller) (block: => Any) (implicit design: Design):MetaPipeline =
    new Controller(None, block, Some(parent), typeStr) with MetaPipeline
  def apply (name:String, parent: Controller) (block: => Any) (implicit design: Design):MetaPipeline =
    new Controller(Some(name), block, Some(parent), typeStr) with MetaPipeline
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
  def apply (name:String) (block: => Any) (implicit design: Design):Top =
    new Controller(Some(name), block, None, typeStr) with Top
  def apply (block: => Any) (implicit design: Design):Top =
    Top("Top")(block)
}
