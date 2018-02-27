package pir.node

import pir._

import pirc._
import pirc.util._
import pirc.enums._

trait Controller extends prism.node.SubGraph[Controller] with IR {
  type P = Controller
  val style:ControlStyle
  val level:ControlLevel
  def isInnerControl = level==InnerControl 
  def isOuterControl = level==OuterControl
}
case class LoopController(style:ControlStyle, level:ControlLevel, cchain:CounterChain)(implicit design:PIR) extends Controller {
  override def className = s"$style"
}
case class UnitController(style:ControlStyle, level:ControlLevel)(implicit design:PIR) extends Controller
case class TopController()(implicit design:PIR) extends Controller {
  val style = SeqPipe
  val level = OuterControl 
}
case class ArgInController()(implicit design:PIR) extends Controller {
  val style = InnerPipe
  val level = InnerControl 
}
