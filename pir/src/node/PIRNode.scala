package pir
package node

import prism.graph._

import scala.collection.mutable

abstract class PIRNode(implicit env:BuildEnvironment) extends EnvNode[PIRNode] with FieldNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name")
  val sname = new Metadata[String]("sname")
  val srcCtx = new Metadata[String]("srcCtx")

  val ctrl = new Metadata[ControlTree]("ctrl") {
    override def :=(v:ControlTree) = { 
      super.:=(v)
      v.pnodes(self)
    }
    override def reset = {
      value.foreach { v => v.pnodes.get -= self }
      super.reset
    }
  }
  
  env.initNode(this)
}

trait PIRNodeUtil extends MemoryUtil 

case class ControlTree(schedule:String)(implicit env:Env) extends EnvNode[ControlTree] with FieldNode[ControlTree] { self =>
  lazy val Nct = classTag[ControlTree]

  val pnodes = new Metadata[mutable.ListBuffer[PIRNode]]("ctrl", Some(mutable.ListBuffer.empty)) {
    def apply(value:PIRNode) = { get += value; self }
  }

  env.initNode(this)
}

