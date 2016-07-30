package pir.plasticine.config

import pir.plasticine.graph._
import scala.language.implicitConversions

trait Spade {
  val wordWidth:Int
  val numLanes:Int

  val top:Top
  val rcus:List[ComputeUnit]
  val ttcus:List[TileTransfer]

  def ctrlers = top :: rcus ++ ttcus
  def cus = rcus ++ ttcus

  def numCUs = rcus.size

  implicit def reg_to_port(r:Reg):OutPort = r.out
}
