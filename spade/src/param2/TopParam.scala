package spade
package param

import prism.graph._
import spray.json._
import DefaultJsonProtocol._

case class TopParam(
  wordWidth:Int=32, // bit
  vecWidth:Int=16, // word
  clockFrequency:Int=1000000000, //Hz
  burstSize:Int=512, // bit
  pattern:Pattern = Checkerboard(),
  scheduled:Boolean=false,
  memTech:String="DDR3",
) extends Parameter {
  val bytePerWord = wordWidth / 8
  val burstSizeWord = burstSize / wordWidth
  val burstSizeByte = burstSize / 8 
}
object WithPattern {
  def unapply(x:TopParam) = Some(x.pattern)
}

trait Pattern extends Parameter

case class AsicPattern(
  networkParam:NetworkParam=NetworkParam("vec")
) extends Pattern

case class InfinatePattern(
  cu1:CUParam=PCUParam(),
  cu2:CUParam=PMUParam(),
  fringePattern:FringePattern=FringePattern()
) extends Pattern

trait GridPattern extends Pattern {
  def row:Int
  def col:Int
}

/*
 *
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *  | PCU | PMU |
 *  +-----+-----+
 *
 * */
case class Checkerboard(
  row:Int=8,
  col:Int=8,
  cu1:CUParam=PCUParam(),
  cu2:CUParam=PMUParam(),
  fringePattern:FringePattern=FringePattern(),
  networkParams:List[NetworkParam]=List(NetworkParam("bit"), NetworkParam("word"), NetworkParam("vec", numVC=4))
) extends GridPattern {
  def cuAt(i:Int, j:Int) = {
    if ((i+j) % 2 == 0) cu1 else cu2
  }
}

/*
 *
 *  +-----+-----+-----+
 *  | PMU | PCU | PMU |
 *  +-----+-----+-----+
 *  | PMU | PCU | PMU |
 *  +-----+-----+-----+
 *
 * */
case class MCMColumnStrip(
  row:Int=8,
  col:Int=8,
  cu1:CUParam=PCUParam(),
  cu2:CUParam=PMUParam(),
  fringePattern:FringePattern=FringePattern(),
  networkParams:List[NetworkParam]=List(NetworkParam("bit"), NetworkParam("word"), NetworkParam("vec", numVC=4))
) extends GridPattern {
  def cuAt(i:Int, j:Int) = {
    if (i % 3 % 2 == 0) cu2 else cu1
  }
}

case class FringePattern(
  mcParam:MCParam=MCParam(),
  dagParam:Option[DramAGParam]=Some(DramAGParam()),
  argFringeParam:ArgFringeParam=ArgFringeParam(),
  shareNode:Boolean=true,
  rank:Int=1, // Number of columns of MCs and AGs on both sides
) extends Parameter {
  lazy val topParam = traceOut[TopParam]
  // Number of columns of nodes assigned to either MC or AG on both sides
  def fringeColumn = (if (shareNode) 1 else if (dagParam.nonEmpty) 2 else 1) * rank
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
  numVC:Int = 0, // if vc is zero, it's pure static network
  linkProp:String="db", // Static link property. "db" - double buffer, "cd" - credit based
  flitWidth:Int=512, // Flitwidth for dynamic network
  bundleParam:BundleParam=BundleParam()
) extends Parameter

case class BundleParam() extends Parameter {
  lazy val granularity = traceOut[NetworkParam]
}

case class SRAMParam (
  bank:Int = 16,
  capacity:Int= 256 * 1024 // in Byte
) extends Parameter {
  lazy val topParam = traceOut[TopParam]
  lazy val sizeInWord = capacity / topParam.bytePerWord
}

case class FIFOParam( 
  granularity:String,
  count:Int,
  depth:Int=16
  // depth:Int=32
) extends Parameter {
  lazy val cuParam = traceOut[CUParam]
}

sealed trait CUParam extends Parameter {
  val sramParam:SRAMParam
  val fifoParams:List[FIFOParam]
  val numCounters:Int
  val numPCU:Int
  val numStage:Int
  val ops:Set[Opcode]
  val numReg:Int
  val numVin:Int
  val numVout:Int
  val numSin:Int
  val numSout:Int
  val numSplitter:Int
  val numLock:Int
  val numCtx:Int
  val numMergeBuffer:Int
  val mergeBufferWays:Int
  lazy val numLane:Int = traceOut[TopParam].vecWidth
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
  numStage:Int=15,
  numPCU:Int=0,
  ops:Set[Opcode]=noFltOps,
  numReg:Int=16,
  numSin:Int=4,
  numSout:Int=5,
  numVin:Int=4,
  numVout:Int=4,
  numSplitter:Int=0,
  numLock:Int=0,
  numCtx:Int = 4,
  numMergeBuffer:Int=0,
  mergeBufferWays:Int=0,
) extends CUParam {
  override lazy val numLane:Int = 1
  val sramParam = connectField(SRAMParam(bank=0))
}
case class PCUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=8,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numCounters:Int=16,
  numStage:Int=6,
  ops:Set[Opcode]=allOps,
  numPCU:Int=1,
  numReg:Int=16,
  numSin:Int=6,
  numSout:Int=4,
  numVin:Int=8,
  numVout:Int=4,
  numSplitter:Int=1,
  numLock:Int=0,
  numCtx:Int = 4,
  numMergeBuffer:Int=1,
  mergeBufferWays:Int=2,
) extends CUParam {
  val sramParam = connectField(SRAMParam(bank=0))
}
case class PMUParam(
  fifoParams:List[FIFOParam]=List(
    FIFOParam("bit",count=16,depth=1000),
    FIFOParam("word",count=8,depth=4),
    FIFOParam("vec",count=4,depth=4)
  ),
  numPCU:Int=0,
  numCounters:Int=16,
  numStage:Int=6,
  ops:Set[Opcode]=noFltOps,
  numReg:Int=16,
  sramParam:SRAMParam=SRAMParam(bank=16),
  numSin:Int=16,
  numSout:Int=8,
  numVin:Int=8,
  numVout:Int=8,
  numSplitter:Int=0,
  numLock:Int=1,
  numCtx:Int = 4,
  numMergeBuffer:Int=0,
  mergeBufferWays:Int=0,
) extends CUParam

