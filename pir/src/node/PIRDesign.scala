package pir.node

import pir._

import prism._
import prism.util._
import scala.collection.mutable

case class PIRDesign() extends prism.node.ProductNode[PIRNode](0) with PIRNode with Container with prism.node.Design { 
  val pirmeta = new PIRMetadata

  implicit override lazy val design:this.type = this

  val topController:TopController = TopController()(design)
  val argController = ArgInController()(design).setParent(topController)
  lazy val argFringe = ArgFringe(argController)(design)

}

