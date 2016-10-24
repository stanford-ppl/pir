package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map

trait Spade extends Metadata with ImplicitConversion { self =>
  implicit val spade:Spade = self

  override def toString = getClass().getSimpleName()
  val wordWidth:Int
  val numLanes:Int

  val top:Top
  val rcus:List[ComputeUnit]
  val ttcus:List[TileTransfer]

  def ctrlers = top :: rcus ++ ttcus
  def cus = rcus ++ ttcus

  def numCUs = rcus.size

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  val const = Const()

  // Metadata Maps 
  val coordMap:coordOf.M = Map.empty
  val indexMap:indexOf.M = Map.empty
}
trait PointToPointNetwork extends Spade
trait SwitchNetwork extends Spade {
  val cuArray:List[List[ComputeUnit]]
  val sbs:List[List[SwitchBox]]
  val csbs:List[List[SwitchBox]]
  def switchNetworkDataBandwidth:Int = {
    sbs.flatten.map{ sb =>
      sb.vins.filter(_.isConnected).flatMap { vin =>
        vin.fanIns.filter{_.src.isInstanceOf[SwitchBox]}.headOption.map{ _.src }
      }.groupBy( k => k ).map{ case (k, l)  => l.size}.max
    }.max
  }
  def switchNetworkCtrlBandwidth:Int = {
    csbs.flatten.map{ sb =>
      sb.vins.filter(_.isConnected).flatMap { vin =>
        vin.fanIns.filter{_.src.isInstanceOf[SwitchBox]}.headOption.map{ _.src }
      }.groupBy( k => k ).map{ case (k, l)  => l.size}.max
    }.max
  }
}

trait ImplicitConversion {
  implicit def ib_to_rmp[S<:NetworkElement](ib:InBus[S]):RMOutPort[InBus[S]] = ib.viport
  implicit def ib_to_op[S<:NetworkElement](ib:InBus[S]):OutPort[InBus[S]] = ib.viport
  implicit def ob_to_rmp[S<:NetworkElement](ob:OutBus[S]):RMInPort[OutBus[S]] = ob.voport
  implicit def ob_to_ip[S<:NetworkElement](ob:OutBus[S]):InPort[OutBus[S]] = ob.voport
  implicit def si_to_rmp(si:ScalarIn):RMOutPort[ScalarIn] = si.out
  implicit def so_to_rmp(so:ScalarOut):RMInPort[ScalarOut] = so.in
  implicit def pr_to_ip(pr:PipeReg):InPort[PipeReg] = pr.in
  implicit def pr_to_op(pr:PipeReg):OutPort[PipeReg] = pr.out
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
