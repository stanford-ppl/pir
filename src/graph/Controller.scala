package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

class Controller(nameStr: Option[String], block: => Any, typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr) {
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

}

class ComputeUnit(nameStr: Option[String], block: => Any, val parent:Controller)(implicit design: Design) extends Controller(nameStr, block, "CU") {
  val pipeline = {
    val temp = nodes.filter(n => n.isInstanceOf[Pipeline]) 
    assert(temp.size == 1, "ComputeUnit has exactly 1 Pipeline")
    temp(0)
  }
  override def content = super.content :+ s"  ${pipeline}"
}
object ComputeUnit {
  def apply (parent:Controller) (block: => Any) (implicit design: Design):ComputeUnit =
    new ComputeUnit(None, block, parent)
  def apply (name:String, parent:Controller) (block: => Any) (implicit design: Design):ComputeUnit =
    new ComputeUnit(Some(name), block, parent)
}

class MemoryController(nameStr: Option[String], block: => Any)(implicit design: Design) extends Controller(nameStr, block, "MemCtrl") {
}
object MemoryController {
  def apply (block: => Any) (implicit design: Design):MemoryController =
    new MemoryController(None, block)
  def apply (name:String) (block: => Any) (implicit design: Design):MemoryController =
    new MemoryController(Some(name), block)
}

class MetaPipeline(nameStr: Option[String], block: => Any)(implicit design: Design) extends Controller(nameStr, block, "MetaPipe")
object MetaPipeline {
  def apply (block: => Any) (implicit design: Design):MetaPipeline =
    new MetaPipeline(None, block)
  def apply (name:String) (block: => Any) (implicit design: Design):MetaPipeline =
    new MetaPipeline(Some(name), block)
}

class Sequential(nameStr: Option[String], block: => Any)(implicit design: Design) extends Controller(nameStr, block, "Sequential")
object Sequential {
  def apply (block: => Any) (implicit design: Design):Sequential =
    new Sequential(None, block)
  def apply (name:String) (block: => Any) (implicit design: Design):Sequential =
    new Sequential(Some(name), block)
}
