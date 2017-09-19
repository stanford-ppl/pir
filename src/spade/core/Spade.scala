package pir.spade.main

import pir.spade.node._
import pir.spade.simulation._
import pir.spade.util._

import pirc._
import pirc.util._

import scala.language.implicitConversions
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait SpadeParam {
  lazy val wordWidth = 32
  lazy val numLanes = 16
  lazy val clockFrequency:Int = 1000000000 //Hz
}

trait PreLoadSpadeParam extends SpadeParam {
  override lazy val numLanes = ConfigFactory.plasticineConf.lanes
}

trait Spade extends Design with SpadeMetadata with SpadeParam {
  implicit def spade:this.type = this

  override def toString = getClass().getSimpleName().replace("$", "")
  def top:Top

  def prts:List[Routable]

  lazy val ctrlers:List[Controller]      = prts.collect    { case cl:Controller          => cl }
  lazy val cus:List[ComputeUnit]         = ctrlers.collect { case cu:ComputeUnit         => cu }
  lazy val pcus:List[PatternComputeUnit] = ctrlers.collect { case pcu:PatternComputeUnit => pcu }
  lazy val mcus:List[MemoryComputeUnit]  = ctrlers.collect { case mcu:MemoryComputeUnit  => mcu }
  lazy val scus:List[ScalarComputeUnit]  = ctrlers.collect { case scu:ScalarComputeUnit  => scu }
  lazy val ocus:List[OuterComputeUnit]   = ctrlers.collect { case ocu:OuterComputeUnit   => ocu }
  lazy val mcs:List[MemoryController]    = ctrlers.collect { case mc:MemoryController    => mc }

  def diameter:Int

  def numCUs = (pcus ++ mcus).size

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  
  val simulatable = ListBuffer[Simulatable]()
  def config:Unit = {
    top.genConnections
    pcus.foreach { _.config }
    mcus.foreach { _.config }
    //mus.foreach { _.config }
    scus.foreach { _.config }
    mcs.foreach { _.config }
    ocus.foreach { _.config }
  }

  def asSwitchNetwork = this.asInstanceOf[SwitchNetwork]

  val dram = DRAM(size=1024) 

  def handle(e:Exception):Unit = throw e
}

trait PointToPointNetwork extends Spade {
  def diameter = (pcus ++ mcus).length
}
