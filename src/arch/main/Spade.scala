package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.plasticine.config.ConfigFactory
import pir.util.misc._

trait SpadeParam {
  val wordWidth = 32
  val numLanes = 16
  val clockFrequency:Int = 1000000000 //Hz
}

trait Spade extends SpadeMetadata with SpadeParam {
  implicit def spade:this.type = this

  override def toString = getClass().getSimpleName().replace("$", "")
  def top:Top
  def pcus:List[PatternComputeUnit]
  def mcus:List[MemoryComputeUnit]
  def scus:List[ScalarComputeUnit]
  def ocus:List[OuterComputeUnit]
  def mcs:List[MemoryController]

  def diameter:Int

  def cus = pcus ++ mcus ++ scus ++ ocus
  def ctrlers = top :: cus ++ mcs

  def prts:List[Routable] = ctrlers

  def numCUs = (pcus ++ mcus).size

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  
  val simulatable = ListBuffer[Simulatable]()
  def config:Unit = {
    top.genConnections
    pcus.foreach { _.config }
    mcus.foreach { _.config }
    scus.foreach { _.config }
    mcs.foreach { _.config }
    ocus.foreach { _.config }
  }

  def asSwitchNetwork = this.asInstanceOf[SwitchNetwork]

  val factory = new ConfigFactory()
          
  val dram = DRAM(size=1024) 
}

trait PointToPointNetwork extends Spade {
  def diameter = (pcus ++ mcus).length
}
