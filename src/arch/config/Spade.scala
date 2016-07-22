package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions

trait Spade {
  val wordWidth:Int
  val numLanes:Int

  val computeUnits:List[ComputeUnit]
  def numCUs = computeUnits.size

  implicit def reg_to_port(r:Reg):OutPort = r.out
}
