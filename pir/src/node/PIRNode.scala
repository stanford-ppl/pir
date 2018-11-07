package pir
package node

import prism.graph._

import scala.collection.mutable

abstract class PIRNode(implicit env:BuildEnvironment) extends EnvNode[PIRNode] with FieldNode[PIRNode] with DefNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name")
  val sname = new Metadata[String]("sname") {
    override def check(v:String) = {}
  }
  val srcCtx = new Metadata[String]("srcCtx") {
    override def check(v:String) = {}
  }

  val ctrl = new Metadata[ControlTree]("ctrl") {
    override def apply(value:ControlTree) = self match {
      case self:GlobalContainer => getSelf
      case self => super.apply(value)
    }
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
    def isUnder[T:ClassTag] = n.ancestors.exists { _.to[T].nonEmpty }
  }
}

case class ControlTree(schedule:String)(implicit env:Env) extends EnvNode[ControlTree] with FieldNode[ControlTree] { self =>
  lazy val Nct = classTag[ControlTree]

  val ctrler = new Metadata[Controller]("ctrler")

  env.initNode(this)
}
