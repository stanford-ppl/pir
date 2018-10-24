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
trait TestFNode extends FieldNode[TestFNode] {
  lazy val Nct = classTag[TestFNode]
}

trait Parameter extends ProductNode[Parameter] {
  override lazy val Nct = classTag[Parameter]
}

case class ParamA(a:Int, b:Int) extends Parameter
case class ParamB(a:ParamA) extends Parameter

trait TestEnv extends Env {
  implicit class Parent(val value:Node[_]) extends State[Node[_]] {
    def initNode(n:Node[_], value:Node[_]) = {
      n match {
        case n:Node[_] => n.setParent(value)
        case _ =>
      }
    }
  }

}
