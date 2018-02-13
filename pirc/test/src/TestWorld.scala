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
  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    x match {
      case x:TestAtom => newIn.connect(x.out)
      case x => super.connectFields(x, i:Int) 
    }
  }
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with SubGraph[TestNode] {
  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val ev = nct
    x match {
      case x:N => this.addChild(x); x
      case x => super.connectFields(x, i)
    }
  }
}

