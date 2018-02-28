package pir.pass

import pir._
import pir.node._
import scala.reflect._

trait PIRWorld {
  implicit val nct = classTag[N]
  type N = PIRNode
  type P = Container
  type A = Primitive
  type D = PIRDesign 
}
