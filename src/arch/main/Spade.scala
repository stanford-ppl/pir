package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.plasticine.config.ConfigFactory
import pir.util.misc._

trait Spade extends SpadeMetadata {
  implicit def spade:this.type = this

  override def toString = getClass().getSimpleName().replace("$", "")
  val wordWidth = 32
  val numLanes = 16
  val clockFrequency:Int = 1000000000 //Hz

  def top:Top
  def pcus:List[ComputeUnit]
  def mcus:List[MemoryComputeUnit]
  def scus:List[ScalarComputeUnit]
  def ocus:List[OuterComputeUnit]
  def mcs:List[MemoryController]

  def diameter:Int

  def cus = pcus ++ mcus ++ scus ++ ocus
  def ctrlers = top :: cus ++ mcs

  def pnes:List[NetworkElement] = ctrlers

  def numCUs = (pcus ++ mcus).size

  var nextSym = 0
  def nextId = {val temp = nextSym; nextSym +=1; temp}
  
  val simulatable = ListBuffer[Simulatable]()
  def config:Unit = {}

  def asSwitchNetwork = this.asInstanceOf[SwitchNetwork]

  val factory = new ConfigFactory()
}

trait PointToPointNetwork extends Spade {
  def diameter = (pcus ++ mcus).length
}

