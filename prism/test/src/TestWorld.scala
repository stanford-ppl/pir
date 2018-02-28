package prism.test

import prism._
import prism.util._
import prism.node._

abstract class TestDesign extends IR with Design {
  implicit val design:this.type = this
  val top:TestSubGraph
  val id = 0
}

trait TestNode extends ProductNode[TestNode] {
  type N = TestNode
  type A = TestAtom
  type P = TestSubGraph
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

class TestInput(implicit override val src:TestAtom, design:Design) extends Input[TestNode] {
  override type A = TestAtom
  type E = TestOutput
  val id = design.nextId
}
class TestOutput(implicit override val src:TestAtom, design:Design) extends Output[TestNode] {
  override type A = TestAtom
  type E = TestInput
  val id = design.nextId
}
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends ProductAtom[TestNode] with TestNode {
  def newIn = new TestInput
  val out = new TestOutput
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends ProductSubGraph[TestNode] with TestNode

