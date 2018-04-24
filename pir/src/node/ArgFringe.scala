package pir.node

case class ArgFringe(topController:Controller)(implicit design:PIRDesign) extends GlobalContainer {

  val argInController = ArgInController().setParent(topController)
  val argOutController = ArgOutController().setParent(topController)

  def dramAddress(dram:DRAM)(implicit design:PIRDesign) = {
    val reg = DramAddress(dram)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
