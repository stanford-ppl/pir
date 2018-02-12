package pir.pass

import pir._
import pir.node._
import scala.reflect._

trait PIRWorld {
  implicit val nct = classTag[N]
  type N = PIRNode with Product
  type P = Container
  type A = Primitive
  type D = PIR
}
