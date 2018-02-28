package pir.node

import pir._

import prism._
import prism.util._
import scala.collection.mutable

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:Design) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:PIRNode*)(implicit design:Design) extends GlobalContainer

case class ArgFringe(argController:ArgInController)(implicit design:Design) extends GlobalContainer {

  def argIn(init:AnyVal)(implicit design:Design) = {
    val reg = ArgIn(init)
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:Design) = {
    ArgOut(init).setParent(this)
  }

  def dramAddress(dram:DRAM)(implicit design:Design) = {
    val reg = ArgIn()
    reg.name(s"DramAddr${reg.id}")
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:Design) extends Def
case class ArgInValid()(implicit design:Design) extends ControlNode

case class PIRDesign() extends prism.node.ProductNode[PIRNode](0) with PIRNode with Container with prism.node.Design { 
  val pirmeta = new PIRMetadata

  implicit override lazy val design:this.type = this

  val topController:TopController = TopController()(design)
  val argController = ArgInController()(design).setParent(topController)
  lazy val argFringe = ArgFringe(argController)(design)

}

