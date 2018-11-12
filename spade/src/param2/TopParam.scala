package spade
package param2

import prism.graph._
import prism.graph.implicits._
import spray.json._
import DefaultJsonProtocol._

case class TopParam(
  wordWidth:Int=32, // bit
  vecWidth:Int=16, // word
  clockFrequency:Int=1000000000, //Hz
  pattern:Pattern = Checkerboard()
) extends Parameter

trait Pattern extends Parameter

case class AsicPattern() extends Pattern

case class Checkerboard(
  row:Int=8,
  col:Int=8,
  cu1:CUParam=PCUParam(),
  cu2:CUParam=PMUParam(),
  fringeParam:FringeParam=FringeParam(),
  networkParams:List[NetworkParam]=List(NetworkParam("bit"), NetworkParam("word"), NetworkParam("vec"))
) extends Pattern {
  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) cu1 else cu2
  }
}

case class FringeParam(
  burstSize:Int=512, // bit
  mcParam:MCParam=MCParam(),
  dagParam:Option[DramAGParam]=Some(DramAGParam()),
  argFringeParam:ArgFringeParam=ArgFringeParam(),
  shareNode:Boolean=true
) extends Parameter {
  lazy val topParam = this.collectOut[TopParam]().head
  def burstSizeWord = burstSize / topParam.wordWidth
  def burstSizeByte = burstSize / 8 
  def bytePerWord = topParam.wordWidth / 8
  def fringeColumn = if (shareNode) 1 else if (dagParam.nonEmpty) 2 else 1
}

case class ArgFringeParam(
  numArgIns:Int=20,
  numArgOuts:Int=10,
  numTokenIns:Int=10,
  numTokenOuts:Int=10
) extends Parameter

case class NetworkParam(
  granularity:String,
  topology:String="mesh", // mesh, torus, cmesh, p2p
  staticInput:Int=4, // Number of inputs from switch to node
  staticOutput:Int=4, // Number of outptus from node to switch
  switchLink:Int=2, // Number of links between switches
  isDynamic:Boolean=true, // Whether there is a dynamic router
  numVC:Int = 4,
  bundleParam:BundleParam=BundleParam()
) extends Parameter

case class BundleParam() extends Parameter {
  lazy val granularity = this.collectOut[NetworkParam]().head.granularity
}

case class SRAMParam (
  count:Int=1,
  depth:Int=4
) extends Parameter {
  lazy val bank:Int = this.collectUp[TopParam]().head.vecWidth
}

case class FIFOParam( 
  granularity:String,
  count:Int,
  depth:Int=16
) extends Parameter

sealed trait CUParam extends Parameter {
  val sramParam:SRAMParam
  val fifoParams:List[FIFOParam]
  val numCounters:Int
  val numStage:Int
  val numReg:Int
  lazy val numLane:Int = this.collectUp[TopParam]().head.vecWidth
  def fifoParamOf(granularity:String):Option[FIFOParam] = 
    assertOneOrLess(fifoParams.filter { _.granularity == granularity }, 
      s"$this fifoParam at $granularity")
}

case class MCParam() extends Parameter

case class DramAGParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=4,depth=16)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  numReg:Int=16,
) extends CUParam {
  override lazy val numLane:Int = 1
  val sramParam = SRAMParam(count=0)
}
case class PCUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=4,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  numReg:Int=16,
) extends CUParam {
  val sramParam = SRAMParam(count=0)
}
case class PMUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=4,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  numReg:Int=16,
  sramParam:SRAMParam=SRAMParam(count=1)
) extends CUParam

