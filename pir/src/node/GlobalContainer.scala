package pir.node

import pir._

import pirc._
import pirc.util._
import scala.collection.mutable

trait GlobalContainer extends Container { self => }

case class CUContainer(contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class FringeContainer(dram:DRAM, contains:PIRNode*)(implicit design:PIR) extends GlobalContainer

case class ArgFringe(argController:ArgInController)(implicit design:PIR) extends GlobalContainer {
  val argInDef = ArgInDef().setParent(this).ctrl(argController)
}
case class ArgInValid()(implicit design:PIR) extends ControlNode

case class Top()(implicit design: PIR) extends Container { 
  val metadata = new PIRMetadata

  val topController:TopController = TopController()
  val argController = ArgInController().setParent(topController)
  lazy val argFringe = ArgFringe(argController).setParent(this)

  val argIns = mutable.ListBuffer[ArgIn]()
  val argOuts = mutable.ListBuffer[ArgOut]()
  val dramAddresses = mutable.Map[DRAM, ArgIn]()

  def argIn(init:AnyVal)(implicit design:PIR) = {
    val reg = ArgIn(init).setParent(this)
    argIns += reg
    WriteMem(reg, argFringe.argInDef).setParent(argFringe).ctrl(argController)
    reg
  }

  def argOut(init:AnyVal)(implicit design:PIR) = {
    val reg = ArgOut(init)
    argOuts += reg
    argFringe.addChild(reg)
    reg
  }

  def dramAddress(dram:DRAM)(implicit design:PIR) = {
    val reg = ArgIn().setParent(this)
    reg.name(s"DramAddr${reg.id}")
    dramAddresses += dram -> reg
    WriteMem(reg, argFringe.argInDef).setParent(argFringe).ctrl(argController)
    ReadMem(reg)
  }

}

