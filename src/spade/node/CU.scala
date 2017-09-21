package spade.node

import spade._
import spade.util._

import pirc.enums._
import pirc.util._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer

trait ComputeUnitParam extends ControllerParam {
  val numRegs:Int
  val numCtrs:Int
  val numSRAMs:Int
  val sramSize:Int
  val numUDCs:Int
  val numLanes:Int
  val numStages:Int
  val numVins:Int
  val numVouts:Int
  val numSins:Int
  val numSouts:Int
  val sbufSize:Int
  val vbufSize:Int
  val muxSize:Int
}

/*
 * ComputeUnit
 * */
abstract class ComputeUnit(override val param:ComputeUnitParam)(implicit spade:Spade) extends Controller(param) {

  import spademeta._
  import param._
  //override implicit val ctrler:ComputeUnit = this 
  val regs:List[ArchReg] = List.tabulate(numRegs) { ir => ArchReg().index(ir) }
  val srams:List[SRAM] = List.tabulate(numSRAMs) { i => SRAM(sramSize, spade.numLanes).index(i) }
  val ctrs:List[Counter] = List.tabulate(numCtrs) { i => Counter().index(i) }
  //var sbufs:List[ScalarMem] = Nil // in Controller
  override def mems:List[OnChipMem] = sbufs ++ vbufs ++ srams
  def vout = vouts.head

  protected val _regstages:ListBuffer[FUStage] = ListBuffer.empty  // Regular Stages
  protected val _rdstages:ListBuffer[ReduceStage] = ListBuffer.empty // Reduction Stages
  protected val _fustages:ListBuffer[FUStage] = ListBuffer.empty // Function Unit Stages
  protected val _stages:ListBuffer[Stage] = ListBuffer.empty // All stages

  def regstages:List[FUStage] = _regstages.toList // Regular Stages
  def rdstages:List[ReduceStage] = _rdstages.toList // Reduction Stages
  def fustages:List[FUStage] = _fustages.toList // Function Unit Stages
  def stages:List[Stage] = _stages.toList // All stages

  def addRegstages(stages:List[FUStage]) = { _regstages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRdstages(stages:List[ReduceStage]) = { _rdstages ++= stages; _fustages ++= stages; addStages(stages) }

  protected def addStages(sts:List[Stage]) = {
    sts.zipWithIndex.foreach { case (stage, i) =>
      stage.index(stages.size)
      if (stages.nonEmpty) {
        stage.prev = Some(stages.last)
        stage.prev.get.next = Some(stage)
      }
      _stages += stage
    }
  }

  def addRegstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = { 
    addRegstages(List.fill(numStage) { FUStage(numOprds=numOprds, regs, ops) }); this // Regular stages
  }
  def addRdstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRdstages(List.fill(numStage) { ReduceStage(numOprds=numOprds, regs, ops)}); this // Reduction stage 
  } 

  def color(range:Range, color:RegColor):this.type = { range.foreach { i => regs(i).color(color) }; this }
  def color(i:Int, color:RegColor):this.type = { regs(i).color(color); this }

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    // Add delay to output if input is from doneXBar
    cfmap.get(this).foreach { config =>
      (souts++vouts).foreach { 
        case out if isMapped(out)(mapping) =>
          this match {
            case cu:MemoryComputeUnit =>
              fanInOf(out.ic).foreach { pout =>
                out.ic.v.set { v =>
                  v <<= pout.v
                  v.valid <<= validOf(out).pv // 1 more cycle for sram read
                }
              }
            case cu =>
              fanInOf(out.ic).foreach { pout =>
                out.ic.v.set { v =>
                  v <<= pout.v
                  v.valid <<= validOf(out).v
                }
              }
          }
          out.ic.v.valid.default = false
        case _ =>
      }
    }
    super.register
  }

}

