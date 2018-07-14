package pir
package node

case class Top()(implicit design:PIRDesign) extends Container { 
  lazy val topController:TopController = TopController()
  lazy val argFringe = withParent(this) { ArgFringe() }
}

