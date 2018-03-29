package pir.mapper

import pir._
import pir.node._

import spade.node._
import spade.util._

import prism._
import prism.util._

import scala.collection.mutable

abstract class Router[B<:PinType:ClassTag:TypeTag](implicit pass:Pass) extends NetworkAStarSearch[B] {
}
