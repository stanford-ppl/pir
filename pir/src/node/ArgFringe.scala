package pir
package node

case class ArgFringe()(implicit design:PIRDesign) extends GlobalContainer {

  lazy val topController = design.top.topController
  lazy val argInController = ArgInController().setParent(topController)
  lazy val argOutController = ArgOutController().setParent(topController)

  lazy val tokenInDef = TokenInDef().setParent(this).ctrl(argInController)
  lazy val hostRead = HostRead().setParent(this).ctrl(argOutController)

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
case class TokenInDef()(implicit design:PIRDesign) extends Def with ControlNode
case class HostRead()(implicit design:PIRDesign) extends Def

trait PIRArgFringe {
  implicit def dramAddress_to_access(dramAddr:DramAddress)(implicit design:PIRDesign) = {
    ReadMem(dramAddr)
  }
}
