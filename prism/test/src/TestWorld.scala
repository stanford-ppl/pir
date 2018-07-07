package prism
package test

import prism.node._
import java.io._

abstract class TestDesign extends Design {
  val top:TestSubGraph
  val compiler = new Compiler {
    type D = TestDesign
    override lazy val outDir = Config.outDir.getOrElse {
      val pir_home = Config.PIR_HOME.getOrElse(throw PIRException(s"Please define PIR_HOME or provide output directory with --out"))
      s"$pir_home${separator}out${separator}${TestDesign.this.getClass.getSimpleName}"
    }
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
  def newOut = new TestOutput
}
case class TestSubGraph(ds:TestNode*)(implicit design:Design) extends ProductSubGraph[TestNode] with TestNode

