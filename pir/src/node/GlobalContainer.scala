package pir.node

import pir._

import pirc._
import pirc.util._
import scala.collection.mutable

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class ArgFringe(argController:ArgInController)(implicit design:PIR) extends GlobalContainer {

  def argIn(init:AnyVal)(implicit design:PIR) = {
    val reg = ArgIn(init)
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIR) = {
    ArgOut(init).setParent(this)
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = ArgIn()
    reg.name(s"DramAddr${reg.id}")
    val argInDef = ArgInDef().setParent(this).ctrl(argController)
    WriteMem(reg, argInDef).setParent(this).ctrl(argController)
    ReadMem(reg)
  }

}

case class ArgInDef()(implicit design:PIR) extends Def
case class ArgInValid()(implicit design:PIR) extends ControlNode

case class Top()(implicit design: PIR) extends Container { 
  val metadata = new PIRMetadata

  val topController:TopController = TopController()
  val argController = ArgInController().setParent(topController)
  lazy val argFringe = ArgFringe(argController)

}

