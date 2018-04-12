package pir.pass

import pir.node._

trait PIRWorld {
  implicit val nct = classTag[N]
  type N = PIRNode
  type P = Container
  type A = Primitive
  type D = PIRDesign 
}
