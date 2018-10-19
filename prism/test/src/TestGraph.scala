package prism
package test

import prism.graph._
import prism.codegen._

abstract class TestDesign extends Design {
  val top:Node[_]
}

case class TestPNode(name:String, inputs:TestPNode*) extends ProductNode[TestPNode] {
  override def toString = name
  val Nct = classTag[TestPNode]
}

/*
 * Example
 * */
trait TestFNode extends FieldNode[TestFNode]

case class Read() extends TestFNode {
  val sram = new FieldInput[Node[_]]
  val addr = new FieldInput[List[Node[_]]]
  val ofs = new FieldInput[List[Node[_]]]
  val ens = new FieldInput[Set[Node[_]]]
  val out = new FieldOutput[List[Node[_]]]
}

abstract class TestCodegen extends Pass()(null) with prism.codegen.Codegen {
  override lazy val dirName = "out/test"
}

class TestDotCodegen(val fileName:String)(implicit design:TestDesign) extends TestCodegen with IRDotCodegen {
  val top = design.top
}

class TestIRPrinter(val fileName:String)(implicit design:TestDesign) extends TestCodegen with IRPrinter {
  val forward = true
  val metadata = None
  def qdef(n:Any) = s"$n"
  val top = design.top
}

trait Parameter extends ProductNode[Parameter] {
  val Nct = classTag[Parameter]
}

case class ParamA(a:Int, b:Int) extends Parameter
case class ParamB(a:ParamA) extends Parameter
