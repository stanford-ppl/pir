package pir.node

case class Top()(implicit design:PIRDesign) extends Container { 
  lazy val topController:TopController = TopController()
  lazy val argController = ArgInController().setParent(topController)
  lazy val argFringe = ArgFringe(argController)
}

