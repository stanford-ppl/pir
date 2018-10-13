package prism
package test

import prism.graph._
import prism.codegen._

abstract class TestDesign extends Design {
  val top:Node[_]
}

case class TestPNode(name:String, inputs:TestPNode*)(implicit design:Design) extends ProductNode[TestPNode] {
  override def toString = name
}


/*
 * Example
 * */
trait TestFNode extends FieldNode[TestFNode]

case class Read()(implicit design:Design) extends TestFNode {
  val sram = new FieldInput[Node[_]]
  val addr = new FieldInput[List[Node[_]]]
  val ofs = new FieldInput[List[Node[_]]]
  val ens = new FieldInput[Set[Node[_]]]
  val out = new FieldOutput[List[Node[_]]]
}

class TestDotCodegen(val fileName:String)(implicit design:TestDesign) extends Pass()(null) with IRDotCodegen {
  override lazy val dirName = "out/test"
  val top = design.top
}

class TestIRPrinter(val fileName:String)(implicit design:TestDesign) extends Pass()(null) with IRPrinter {
  override lazy val dirName = "out/test"
  val forward = true
  val metadata = None
  def qdef(n:Any) = s"$n"
  val top = design.top
}

