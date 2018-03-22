package pir.mapper

import pir._
import pir.node._

import prism._
import prism.util._

import scala.collection.mutable.ListBuffer

trait ResourcePruning { self:PIRPass =>
  val constrains = ListBuffer[Constrain]()
  def prune(pmap:PIRMap):EOption[PIRMap] = {
    flatFold(constrains, pmap) { case (pmap, constrain) => constrain.prune(pmap) }
  }
  

}

