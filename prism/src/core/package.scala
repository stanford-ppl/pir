import prism.util._

package object prism extends ScalaAlias with Serialization {
  type Design = prism.node.Design

  type PIRException = prism.exceptions.PIRException
  val PIRException = prism.exceptions.PIRException
}
