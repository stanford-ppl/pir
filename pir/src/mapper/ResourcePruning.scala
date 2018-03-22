package pir.mapper

import pir._
import pir.node._

import prism._
import prism.util._

trait ResourcePruning { self:PIRPass =>
  var constrains:List[Constrain] = Nil
  def prune(pmap:PIRMap):MOption[PIRMap] = {
    flatFold(constrains, pmap) { case (pmap, constrain) => constrain.prune(pmap) }
  }
  

}

