package pir.node

import pir._

trait ControlNode extends Def
case class High()(implicit design:PIR) extends ControlNode
case class Low()(implicit design:PIR) extends ControlNode
