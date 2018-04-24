package pir.node

case class ArgFringe(topController:Controller)(implicit design:PIRDesign) extends GlobalContainer {

  lazy val argInController = ArgInController().setParent(topController)
  lazy val argOutController = ArgOutController().setParent(topController)

  lazy val hostRead = HostRead().setParent(this).ctrl(argOutController)

  def dramAddress(dram:DRAM)(implicit design:PIRDesign) = {
    val reg = DramAddress(dram)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
case class HostRead()(implicit design:PIRDesign) extends Def
