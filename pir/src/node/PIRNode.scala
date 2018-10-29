package pir
package node

import prism.graph._

import scala.collection.mutable

abstract class PIRNode(implicit env:BuildEnvironment) extends EnvNode[PIRNode] with FieldNode[PIRNode] with DefNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name")
  val sname = new Metadata[String]("sname")
  val srcCtx = new Metadata[String]("srcCtx")

  val ctrl = new Metadata[ControlTree]("ctrl") {
    override def reset = {
      self match {
        case _:Controller => value.foreach { v => v.ctrler.reset }
        case _ =>
      }
      super.reset
    }
  }

  override def asOutput = output

  env.initNode(this)
}

trait PIRNodeUtil extends MemoryUtil with AccessUtil {
  implicit class PIRNodeOp(n:PIRNode) {
    def ctx = n.collectUp[Context]().headOption
    def global = n.collectUp[GlobalContainer]().headOption
  }
}

case class ControlTree(schedule:String)(implicit env:Env) extends EnvNode[ControlTree] with FieldNode[ControlTree] { self =>
  lazy val Nct = classTag[ControlTree]

  val ctrler = new Metadata[Controller]("ctrler")

  env.initNode(this)
}
