package pir.node

import pir._

trait ControlNode extends Def
case class High()(implicit design:Design) extends ControlNode
case class Low()(implicit design:Design) extends ControlNode
