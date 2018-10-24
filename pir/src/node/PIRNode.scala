package pir
package node

import prism.graph._

abstract class PIRNode(implicit env:BuildEnvironment) extends EnvNode[PIRNode] with FieldNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name")
  val srcCtx = new Metadata[String]("srcCtx")

  val ctrl = new Metadata[ControlTree]("ctrl")

  //def qdef = s"${name.getOrElse(toString)} = ${productName}"
  //
  
  env.initNode(this)
}
