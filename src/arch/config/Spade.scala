package pir.plasticine.config

import pir.plasticine.graph._

abstract class Spade {
  val wordWidth:Int
  val numLanes:Int

  val computeUnits:List[ComputeUnit]
  def numCUs = computeUnits.size
}
