package spade
package pass

import spade.node._

trait SpadeWorld {
  implicit val nct = classTag[N]
  type N = SpadeNode
  type P = Module
  type A = Pin[_]
  type D = SpadeDesign
}

