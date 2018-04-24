package pir.node

case class ArgFringe(topController:Controller)(implicit design:PIRDesign) extends GlobalContainer {

  val argInController = ArgInController().setParent(topController)
  val argOutController = ArgOutController().setParent(topController)

  def argIn(init:AnyVal)(implicit design:PIRDesign) = {
    val reg = ArgIn(init)
    val argInDef = ArgInDef().setParent(this).ctrl(argInController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argInController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIRDesign) = { //TODO use argOutController
    ArgOut(init).setParent(this)
  }
  def tokenOut()(implicit design:PIRDesign) = {
    TokenOut().setParent(this)
  }

  def dramAddress(dram:DRAM)(implicit design:PIRDesign) = {
    val reg = ArgIn()
    reg.name(s"DramAddr${reg.id}")
    val argInDef = ArgInDef().setParent(this).ctrl(argInController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argInController)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
//case class ArgInValid()(implicit design:PIRDesign) extends ControlNode
