package prism.test

import prism._
import prism.util._
import prism.node._
import java.io._

abstract class TestDesign extends Design {
  val top:TestSubGraph
  val compiler = new Compiler {
    type D = TestDesign
    override def outDir = Config.outDir + File.separator + TestDesign.this.getClass.getSimpleName
    val configs = Nil
    def handle(e:Exception) = {}
    def load = false
    def save = false
    val designPath = ""
    def newDesign = {}
  }
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

