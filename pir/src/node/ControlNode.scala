package pir
package node

trait ControlNode extends Def
case class High()(implicit design:PIRDesign) extends ControlNode
case class Low()(implicit design:PIRDesign) extends ControlNode

// Equivalent to Low
case class ForeverControllerDone()(implicit design:PIRDesign) extends ControlNode
