package pir.mapper

import pir._
import pir.node._

import prism._
import prism.util._

trait ResourcePruning { self:PIRPass =>

  val constrains:List[Constrain]
  def prune(cumap:CUMap):CUMap = {
    constrains.foldLeft(cumap) { case (cumap, constrain) => constrain.prune(cumap) }
  }
}

