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
  implicit def ib_to_op(ib:InBus):RMOutPort = ib.rmport
  implicit def ob_to_ip(ob:OutBus):RMInPort = ob.rmport
}
