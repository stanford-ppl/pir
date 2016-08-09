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

  implicit def ib_to_rmp(ib:InBus):RMPort = ib.viport
  implicit def ib_to_op(ib:InBus):OutPort = ib.viport
  implicit def ob_to_rmp(ob:OutBus):RMPort = ob.voport
  implicit def ob_to_ip(ob:OutBus):InPort = ob.voport
  implicit def si_to_rmp(si:ScalarIn):RMPort = si.out
  implicit def so_to_rmp(so:ScalarOut):RMPort = so.in
  implicit def pr_to_ip(pr:PipeReg):InPort = pr.in
  implicit def pr_to_op(pr:PipeReg):OutPort = pr.out
}
