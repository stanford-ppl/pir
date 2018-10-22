package prism
package test

import prism.graph._
import prism.codegen._

abstract class TestDesign extends Design {
  val top:Node[_]
}

case class TestPNode(label:String, inputs:TestPNode*) extends ProductNode[TestPNode] {
  override lazy val Nct = classTag[TestPNode]
  override def toString = label
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
  override lazy val dirName = testOut
}

case class TestDotCodegen(val fileName:String, val top:Node[_]) extends TestCodegen with IRDotCodegen

case class TestIRPrinter(fileName:String, top:Node[_]) extends TestCodegen with IRPrinter {
  val forward = true
  def qdef(n:Any) = s"$n"
}

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}

case class ParamA(a:Int, b:Int) extends Parameter
case class ParamB(a:ParamA) extends Parameter
