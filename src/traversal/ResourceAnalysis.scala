package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException
import pir.graph.mapper.{StageMapper, PIRMap, RegAlloc}
import pir.plasticine.main.SwitchNetwork

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class ResourceAnalysis(implicit val design: Design) extends Traversal with Metadata {

  val numPStage = 8 // Number of stages per CU 

  val activeCycle = Map[Node, Long]()

  override def run = {
    if (design.contentionAnalysis.isTraversed && design.latencyAnalysis.isTraversed) super.run
  }

  val numLanes = 16
  val numPRegs = 16 * (numPStage + 1) * numLanes
  val numAlus = 16 * numPStage  
  val numSrams = 4
  val numVins = 4

  override def traverse:Unit = {
    var totRegs = 0
    var totAlus = 0
    var totSrams = 0
    design.top.innerCUs.foreach { inner =>
      val parentIter = inner.ancestors.drop(1).map{ an => iterOf(an) }.reduce(_*_)
      val cycle = cycleOf(inner) * parentIter 
      activeCycle += inner -> cycle
      val numFUs = inner.stages.size 
      var numRegs = inner.stages.map { stage => stage.prs.size }.reduce{_+_}
      numRegs += inner.stages.last.liveOuts.size * (numPStage - inner.stages.size) // Liveout Regs that needs to propogate to the end of the stage
      totRegs += numRegs * numLanes
      totAlus += inner.stages.size * numLanes 
      totSrams += inner.vouts.size
    }
    val groups = design.top.innerCUs.groupBy { cu => cu.isInstanceOf[MemoryController] }
    PIRStat.numCUs(cu=groups(false).size, mc=groups(true).size)
    PIRStat.numRegs(totRegs, numPRegs * design.top.innerCUs.size)
    PIRStat.numStages(totAlus, numAlus * design.top.innerCUs.size)
    PIRStat.numSrams(totSrams, numSrams * design.top.innerCUs.size)
  } 

  def finPass = {
    info("Finishing latency modeling")
  }

}
