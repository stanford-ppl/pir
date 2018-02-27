package pirc.test

import pirc._
import pirc.util._
import prism.node._

trait TestDesign extends Design {
  val configs = Nil
  def handle(e:Exception) = {}
  def load:Boolean = false
  def save:Boolean = false

  def loadDesign:Unit = {}
  def newDesign:Unit = {}
  def saveDesign:Unit = {}
  implicit val self:Design = this
}

abstract class TestNode(implicit design:Design) extends ProductNode[TestNode] { self:TestNode =>
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
case class TestAtom(ds:TestAtom*)(implicit design:Design) extends TestNode with ProductAtom[TestNode] {
  val out = new TestOutput
  def newIn(implicit design:Design) = new TestInput
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends TestNode with ProductSubGraph[TestNode]

