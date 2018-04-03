package pir.node

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:PIRDesign) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:PIRNode*)(implicit design:PIRDesign) extends GlobalContainer

case class ArgFringe(argController:ArgInController)(implicit design:PIRDesign) extends GlobalContainer {

  def argIn(init:AnyVal)(implicit design:PIRDesign) = {
    val reg = ArgIn(init)
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIRDesign) = {
    ArgOut(init).setParent(this)
  }
  def tokenOut()(implicit design:PIRDesign) = {
    TokenOut().setParent(this)
  }

  def dramAddress(dram:DRAM)(implicit design:PIRDesign) = {
    val reg = ArgIn()
    reg.name(s"DramAddr${reg.id}")
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:PIRDesign) extends Def
case class ArgInValid()(implicit design:PIRDesign) extends ControlNode

case class DRAM()(implicit design:PIRDesign) extends IR {
  val id = design.nextId
}

