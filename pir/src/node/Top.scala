package pir.node

import pir._

import prism._
import prism.util._
import scala.collection.mutable

case class Top()(implicit design:Design) extends Container { 
  lazy val topController:TopController = TopController()
  lazy val argController = ArgInController().setParent(topController)
  lazy val argFringe = ArgFringe(argController)
}

