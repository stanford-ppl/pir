package pir
package node

case class ArgFringe()(implicit design:PIRDesign) extends GlobalContainer {

  lazy val topController = design.top.topController
  lazy val argInController = withCtrl(topController) { ArgInController() }
  lazy val argOutController = withCtrl(topController) { ArgOutController() }

  lazy val tokenInDef = withParentCtrl(this, argInController) { TokenInDef() }
  lazy val hostRead = withParentCtrl(this, argOutController) { HostRead() }

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
case class TokenInDef()(implicit design:PIRDesign) extends Def with ControlNode
case class HostRead()(implicit design:PIRDesign) extends Def

trait ArgFringeUtil {
  implicit def dramAddress_to_access(dramAddr:DramAddress)(implicit design:PIRDesign) = {
    ReadMem(dramAddr)
  }

}
