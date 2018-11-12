package prism
package test

import prism.graph._
import prism.codegen._

case class X(name:String)(implicit env:Env) extends EnvNode[TestFNode] with TestFNode {

  override def toString = name
  val sram = new InputField[X]("sram")
  val addr = new InputField[List[X]]("addr")
  val ofs = new InputField[List[X]]("ofs")
  val ens = new InputField[Set[X]]("ens")
  val out = new OutputField[List[X]]("out")
  val cchain = new ChildField[X, List[X]]("cchain")
  env.initNode(this)
}

class FieldNodeTest extends UnitTest with TestEnv with Transformer {

  "FieldNodeTest1" should "success" in {
    createNewState
    val top = X("top")
    val (a,b,c,d) = within(top) {
      val a = X("a")
      val b = X("b").sram(a.out)
      val c = X("c").out(a.sram)
      val d = X("d").cchain(a, b)
      (a,b,c,d)
    }
    assert(d.parent==Some(top))
    assert(a.parent==Some(d))
    assert(a.deps.contains(c))
    assert(a.depeds.contains(b))
    new prism.codegen.BasicIRDotGen(testOut, s"field1.dot", top).run

    val mapping = mirrorAll(top+:top.descendents)
    val mtop = mapping(top)
    new prism.codegen.BasicIRDotGen(testOut, s"field2.dot", top).run
    val ma = mapping(a)
    val mb = mapping(b)
    val md = mapping(d)
    assert(md.as[X].cchain.T==List(ma,mb))
  }
}
