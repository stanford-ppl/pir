import prism.util._

package object prism extends ScalaAlias with Serialization with Misc with FileManager {
  type Design = prism.node.Design
  type Logging = prism.codegen.Logging

  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException
  type Serializable = prism.util.Serializable
}
