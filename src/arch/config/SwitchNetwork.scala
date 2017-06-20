package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

abstract class SwitchNetwork(val numRows:Int, val numCols:Int, val numArgIns:Int, val numArgOuts:Int) extends Spade {
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  override def pnes = super.pnes ++ sbs

  def diameter = Math.max(
                  numRows + numCols, // Allow top left to talk to top right
                  Math.ceil(numRows*1.0/2).toInt+3 // allow top to talk to middle CUs
                )

  // Top level controller ~= Host
  val top = Top(numArgIns, numArgOuts)

  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) pcuAt(i,j) 
    else mcuAt(i,j) 
  }

  def pcuAt(i:Int, j:Int) = new PatternComputeUnit()

  def mcuAt(i:Int, j:Int) = new MemoryComputeUnit()

  def scuAt(c:Int, r:Int):ScalarComputeUnit = new ScalarComputeUnit()

  def mcAt(c:Int, r:Int):MemoryController = new MemoryController()

  def ocuAt(c:Int, r:Int):OuterComputeUnit = new OuterComputeUnit()

  val cuArray = List.tabulate(numCols, numRows) { case (x, y) => cuAt(x, y).coord(x, y) }
  val scuArray = List.tabulate(2, numRows+1) { case (x, y) => 
    val scu = scuAt(x, y)
    if (x==0) {
      scu.coord(-1, y)
    } else {
      scu.coord(numCols, y)
    }
    scu
  }
  def scus = scuArray.flatten
  val mcArray = List.tabulate(2, numRows+1) { case (x, y) => 
    val mc = mcAt(x, y)
    if (x==0) {
      mc.coord(-1, y)
    } else {
      mc.coord(numCols, y)
    }
    mc
  }
  def mcs = mcArray.flatten
  lazy val mcus = cuArray.flatten.collect { case mcu:MemoryComputeUnit => mcu }
  lazy val pcus = cuArray.flatten.filterNot { _.isInstanceOf[MemoryComputeUnit] }
  val ocuArray = List.tabulate(numCols+1, numRows+1) { case (x, y) => ocuAt(x, y).coord(x, y) }
  def ocus:List[OuterComputeUnit] = ocuArray.flatten

  val sbArray:List[List[SwitchBox]] = List.tabulate(numCols+1, numRows+1) { case (x, y) => SwitchBox().coord(x, y) }
  def sbs:List[SwitchBox] = sbArray.flatten

  lazy val ctrlNetwork:GridNetwork = new CtrlNetwork()

  lazy val vectorNetwork:GridNetwork = new VectorNetwork()

  lazy val scalarNetwork:GridNetwork = new ScalarNetwork()

  override def config:Unit = {
    scalarNetwork.reset
    ctrlNetwork.reset
    vectorNetwork.reset
    scalarNetwork.config
    ctrlNetwork.config
    vectorNetwork.config
    top.genConnections
    sbs.foreach { _.genConnections }
    pcus.foreach { _.config }
    mcus.foreach { _.config }
    scus.foreach { _.config }
    mcs.foreach { _.config }
    ocus.foreach { _.config }
  }
}



