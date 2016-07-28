package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions

trait Spade {
  val wordWidth:Int
  val numLanes:Int

  val computeUnits:List[ComputeUnit]
  val argIns:List[OutBus]
  val argOuts:List[InBus]

  def numCUs = computeUnits.size
  def numArgIn = argIns.size
  def numArgOut = argOuts.size

  implicit def reg_to_port(r:Reg):OutPort = r.out
}
