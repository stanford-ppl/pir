package pir.spade.main

import pir.spade.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.spade.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

case class PreloadSwitchNetworkParam (
  override val numArgIns:Int = 6,
  override val numArgOuts:Int = 5,
  override val numRows:Int = 2,
  override val numCols:Int = 2,
  override val pattern:Pattern = MixAll
) extends SwitchNetworkParam (
  numArgIns = numArgIns,
  numArgOuts = numArgOuts,
  numRows = numRows,
  numCols = numCols,
  pattern = pattern
) with PreLoadSpadeParam

class SwitchNetworkParam(
  val numArgIns:Int = 6,
  val numArgOuts:Int = 5,
  val numRows:Int = 2,
  val numCols:Int = 2,
  val pattern:Pattern = MixAll
) extends SpadeParam

abstract class SwitchNetwork(val param:SwitchNetworkParam=new SwitchNetworkParam()) extends Spade {
  import param._
  // input <== output: input can be configured to output
  // input <== outputs: input can be configured to 1 of the outputs
  
  override def toString = s"SN${numRows}x${numCols}"

  def diameter = Math.max(
                  numRows + numCols, // Allow top left to talk to top right
                  Math.ceil(numRows*1.0/2).toInt+3 // allow top to talk to middle CUs
                )

  /* --- Controller at specific coordinate --- */
  def cuAt(x:Int, y:Int) = param.pattern.cuAt(this)(x,y)

  def pcuAt(x:Int, y:Int):PatternComputeUnit = {
    val param = if (x>0 && x<numCols) new PatternComputeUnitParam()
                else new SRAMAddrGenParam()
    new PatternComputeUnit(param).coord(x,y)
  }

  def mcuAt(x:Int, y:Int):MemoryComputeUnit = new MemoryComputeUnit().coord(x,y)

  def scuAt(x:Int, y:Int):ScalarComputeUnit = new ScalarComputeUnit().coord(x,y)

  def mcAt(x:Int, y:Int):MemoryController = new MemoryController().coord(x,y)

  def ocuAt(x:Int, y:Int):OuterComputeUnit = new OuterComputeUnit().coord(x,y)

  /* --- Controller Instantiation --- */
  val top = Top()
  
  val cuArray:List[List[Controller]] = List.tabulate(numCols, numRows) { case (x, y) => cuAt(x, y) }

  val dramAGs = List.tabulate(2, numRows+1) { case (x, y) => if (x==0) scuAt(-1, y) else scuAt(numCols, y) }

  val sramAGs = List.tabulate(2, numRows+1) { case (x, y) => if (x==0) pcuAt(-1, y) else pcuAt(numCols, y) }

  val mcArray = List.tabulate(2, numRows+1) { case (x, y) => if (x==0) mcAt(-1, y) else mcAt(numCols, y) }

  val sbArray:List[List[SwitchBox]] = List.tabulate(numCols+1, numRows+1) { case (x, y) => SwitchBox().coord(x,y) }

  val ocuArray = List.tabulate(numCols+1, numRows+1) { case (x, y) => ocuAt(x, y) }

  /* --- All Controllers --- */
  val prts = top :: 
                cuArray.flatten ++ 
                dramAGs.flatten ++ 
                sramAGs.flatten ++ 
                mcArray.flatten ++ 
                sbArray.flatten ++ 
                ocuArray.flatten

  val sbs:List[SwitchBox] = sbArray.flatten

  /* --- Network --- */
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

