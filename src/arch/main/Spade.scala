package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import pir.plasticine.simulation._

trait Spade extends Metadata with ImplicitConversion { self =>
  implicit def spade:Spade = self

  override def toString = getClass().getSimpleName().replace("$", "")
  val wordWidth = 32
  val numLanes = 16

  def top:Top
  def pcus:List[ComputeUnit]
  def mcus:List[MemoryComputeUnit]
  def scus:List[ScalarComputeUnit]
  def ocus:List[OuterComputeUnit]
  def mcs:List[MemoryController]

  def diameter:Int

  def cus = pcus ++ mcus ++ scus ++ ocus
  def ctrlers = top :: cus

  def pnes:List[NetworkElement] = ctrlers ++ mcs

  def numCUs = (pcus ++ mcus).size

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  val const = Const()
  
  // Metadata Maps 
  val coordMap:coordOf.M = Map.empty
  val indexMap:indexOf.M = Map.empty
  val busMap:busOf.M = Map.empty

  val simulatable = ListBuffer[Simulatable]()
}

trait PointToPointNetwork extends Spade {
  def diameter = (pcus ++ mcus).length
}

trait ImplicitConversion {
  implicit def ib_to_rmp[S<:NetworkElement](ib:InBus[S]):RMOutPort[S] = ib.viport
  implicit def ib_to_op[S<:NetworkElement](ib:InBus[S]):OutPort[S] = ib.viport
  implicit def ob_to_rmp[S<:NetworkElement](ob:OutBus[S]):RMInPort[S] = ob.voport
  implicit def ob_to_ip[S<:NetworkElement](ob:OutBus[S]):InPort[S] = ob.voport
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

  /* Bus of a port that belongs to a bus */
  object busOf extends Metadata {
    type V = Bus
    def map(implicit spade:Spade) = spade.busMap
  }
}
