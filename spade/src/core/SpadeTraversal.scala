package spade

import prism.graph._
import spade.node._
import spade.param._

trait SpadeTraversal {
  type N = SpadeNode
}

trait ParamTraversal {
  type N = Parameter
}
