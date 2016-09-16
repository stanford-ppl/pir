package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map

trait Spade extends Metadata { self =>
  implicit val spade:Spade = self
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

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  val const = Const()

  val coordMap:coordOf.M = Map.empty
  val indexMap:indexOf.M = Map.empty
}

trait Metadata {
  trait Metadata {
    type V
    type M = Map[Node, V]
    def map(implicit spade:Spade):M
    def update(n:Node, v:V)(implicit spade:Spade):Unit = map += (n -> v)
    def apply(n:Node)(implicit spade:Spade):V = { val m = map; m(n) }
    def get(n:Node)(implicit spade:Spade):Option[V] =  { val m = map; m.get(n) }
  }
  /* Coordinate of a spade node. Used for pisa and dot codegen */
  object coordOf extends Metadata{ 
    type V = (Int, Int)
    def map(implicit spade:Spade) = spade.coordMap
  }

  /* Index of a spade node. Used for pisa codegen */
  object indexOf extends Metadata {
    type V = Int
    def map(implicit spade:Spade) = spade.indexMap
  }
}
