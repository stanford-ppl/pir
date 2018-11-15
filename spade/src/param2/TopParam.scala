package spade
package param

import prism.graph._
import prism.graph.implicits._
import spray.json._
import DefaultJsonProtocol._

case class TopParam(
  wordWidth:Int=32, // bit
  vecWidth:Int=16, // word
  clockFrequency:Int=1000000000, //Hz
  pattern:Pattern = Checkerboard()
) extends Parameter {
  def bytePerWord = wordWidth / 8
}

trait Pattern extends Parameter {
  def cuParams = this.collectIn[CUParam]()
}

case class AsicPattern(
  networkParam:NetworkParam=NetworkParam("vec", isDynamic=false)
) extends Pattern

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
  lazy val topParam = trace[TopParam]
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
  linkProp:String="db", // Static link property. "db" - double buffer, "cd" - credit based
  flitWidth:Int=512, // Flitwidth for dynamic network
  bundleParam:BundleParam=BundleParam()
) extends Parameter

case class BundleParam() extends Parameter {
  lazy val granularity = trace[NetworkParam]
}

case class SRAMParam (
  count:Int=1,
  size:Int= 256 * 1024 // in Byte
) extends Parameter {
  lazy val topParam = trace[TopParam]
  lazy val bank:Int = topParam.vecWidth
  lazy val sizeInWord = size / topParam.bytePerWord
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
  val ops:Set[Opcode]
  val numReg:Int
  val numVin:Int
  val numVout:Int
  val numSin:Int
  val numSout:Int
  lazy val numLane:Int = trace[TopParam].vecWidth
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
  ops:Set[Opcode]=noFltOps,
  numReg:Int=16,
  numSin:Int=4,
  numSout:Int=4,
  numVin:Int=4,
  numVout:Int=4,
) extends CUParam {
  override lazy val numLane:Int = 1
  val sramParam = connectField(SRAMParam(count=0))
}
case class PCUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=4,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  ops:Set[Opcode]=allOps,
  numReg:Int=16,
  numSin:Int=4,
  numSout:Int=4,
  numVin:Int=4,
  numVout:Int=4,
) extends CUParam {
  val sramParam = connectField(SRAMParam(count=0))
}
case class PMUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=4,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  ops:Set[Opcode]=noFltOps,
  numReg:Int=16,
  sramParam:SRAMParam=SRAMParam(count=1),
  numSin:Int=4,
  numSout:Int=4,
  numVin:Int=4,
  numVout:Int=4,
) extends CUParam

