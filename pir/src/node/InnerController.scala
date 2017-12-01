package pir.node

import pir._

import scala.collection.mutable.ListBuffer

abstract class InnerController(implicit design:PIR) extends ComputeUnit {
  import pirmeta._

  /* Controller Hierarchy */
  def locals = this :: outers
  /* List of outer controllers reside in current inner*/
  var outers:List[OuterController] = Nil

}
