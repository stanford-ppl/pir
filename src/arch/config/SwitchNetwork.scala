package pir.plasticine.main

import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

case class SwitchNetworkParam(
  numArgIns:Int = 6,
  numArgOuts:Int = 5,
  numRows:Int = 2,
  numCols:Int = 2
) extends SpadeParam

abstract class SwitchNetwork(val param:SwitchNetworkParam=new SwitchNetworkParam()) extends Spade {
  import param._
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  override def prts = super.prts ++ sbs

  def diameter = Math.max(
                  numRows + numCols, // Allow top left to talk to top right
                  Math.ceil(numRows*1.0/2).toInt+3 // allow top to talk to middle CUs
                )

  // Top level controller ~= Host
  val top = Top()

  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) pcuAt(i,j) 
    else mcuAt(i,j) 
  }
  
  //def cuAt(i:Int, j:Int) = {
    //if (i % 2 == 0) {
      //if (j % 2 == 0) pcuAt(i,j)
      //else muAt(i,j)
    //}
    //else scuAt(i,j)
  //}

  def pcuAt(i:Int, j:Int):PatternComputeUnit = new PatternComputeUnit()

  def mcuAt(i:Int, j:Int):MemoryComputeUnit = new MemoryComputeUnit()

  def muAt(i:Int, j:Int):MemoryUnit = new MemoryUnit()

  def scuAt(i:Int, j:Int):ScalarComputeUnit = new ScalarComputeUnit()

  def mcAt(i:Int, j:Int):MemoryController = new MemoryController()

  def ocuAt(i:Int, j:Int):OuterComputeUnit = new OuterComputeUnit()

  val cuArray:List[List[Controller]] = List.tabulate(numCols, numRows) { case (x, y) => cuAt(x, y).coord(x, y) }

  val scuArray = List.tabulate(2, numRows+1) { case (x, y) => 
    val scu = scuAt(x, y)
    if (x==0) {
      scu.coord(-1, y)
    } else {
      scu.coord(numCols, y)
    }
    scu
  }
  def scus = scuArray.flatten ++ cuArray.flatten.collect { case cu:ScalarComputeUnit => cu }
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
  lazy val mus = cuArray.flatten.collect { case mcu:MemoryUnit => mcu }
  lazy val pcus = cuArray.flatten.collect { case pcu:PatternComputeUnit => pcu }
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
    sbs.foreach { _.genConnections }
    super.config
  }
}
