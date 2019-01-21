package spade

import prism.graph._
import spade.node._
import spade.param._

trait SpadeTraversal extends Traversal {
  type N = SpadeNode
}

trait ParamTraversal extends Traversal {
  type N = Parameter
}
