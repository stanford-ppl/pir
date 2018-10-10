package spade
package param

case class SwitchParam(
  connection:SwitchConnection=CrossBarSwitchConnection
) extends Parameter

sealed trait SwitchConnection extends prism.enums.Enum
case object CrossBarSwitchConnection extends SwitchConnection
