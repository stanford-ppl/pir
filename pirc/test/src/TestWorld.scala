package pirc.test

import pirc._
import pirc.util._
import prism.node._

trait TestDesign extends Design {
  val configs = Nil
  def handle(e:Exception) = {}
  implicit val self:Design = this
}

abstract class TestNode(implicit design:Design) extends Node[TestNode] { self:Product with TestNode =>
  override type A = TestAtom
  var name:Option[String] = None
  def name(n:String):this.type = { this.name = Some(n); this }
  override def toString = {
    val default = s"${this.getClass.getSimpleName}$id"
    if (name == null) default else name.getOrElse(default) 
  }
}

class TestInput(implicit override val src:TestAtom, design:Design) extends Edge[TestNode]() with Input[TestNode] {
  override type A = TestAtom
  type E = TestOutput
}
class TestOutput(implicit override val src:TestAtom, design:Design) extends Edge[TestNode]() with Output[TestNode] {
  override type A = TestAtom
  type E = TestInput
}
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends TestNode with Atom[TestNode] {
  val out = new TestOutput
  def newIn = new TestInput
  override def connectFields(x:Any)(implicit design:Design):Any = {
    x match {
      case x:TestAtom => newIn.connect(x.out)
      case x => super.connectFields(x) 
    }
  }
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with SubGraph[TestNode]

