package prism
package test

import prism.graph._
import prism.codegen._

case class X(name:String)(implicit env:Env) extends EnvNode[TestFNode] with TestFNode {

  override def toString = name
  val sram = new InputField[X]
  val addr = new InputField[List[X]]
  val ofs = new InputField[List[X]]
  val ens = new InputField[Set[X]]
  val out = new OutputField[List[X]]
  val cchain = new ChildField[X, List[X]]
}

class FieldNodeTest extends UnitTest with Env {
  "FieldNodeTest1" should "success" in {
    val top = X("top")
    within(top) {
      val a = X("a")
      val b = X("b").sram(a.out)
      val c = X("c").out(a.sram)
      val d = X("d").cchain(a)
      println(d.parent)
      println(a.parent)
      println(top.children)
      println(d.children)
    }
    TestDotCodegen("field1.dot", top).run
  }
}
