package pir.plasticine.graph

import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set

/* Routable element at interconnection level */
trait NetworkElement extends Module with Simulatable {
  import spademeta._
  implicit val ctrler:this.type = this 
  def scalarIO:ScalarIO[this.type]
  def vectorIO:VectorIO[this.type]
  def ctrlIO:ControlIO[this.type]
  def gridIOs = scalarIO :: vectorIO :: ctrlIO :: Nil
  def sins = scalarIO.ins // Scalar Inputs
  def souts = scalarIO.outs // Scalar Outputs
  def vins = vectorIO.ins// Input Buses/Vector inputs
  def vouts = vectorIO.outs // Output Buses/Vector outputs
  def cins = ctrlIO.ins // Control inputs
  def couts = ctrlIO.outs // Control outputs

  def isMCU:Boolean = this.isInstanceOf[MemoryComputeUnit]
  def isSCU:Boolean = this.isInstanceOf[ScalarComputeUnit]
  def isTop:Boolean = this.isInstanceOf[Top]
  def asCU:ComputeUnit = this.asInstanceOf[ComputeUnit]
  def genConnections:this.type = { spade.factory.genConnections(this); this } 
  def config(implicit spade:SwitchNetwork):Unit = {}
  //override def toString = s"${coordOf.get(this).fold(super.toString) { case (x,y) => s"$typeStr[$x,$y]"}}"
}

/* Controller */
abstract class Controller(implicit spade:Spade) extends NetworkElement {
  import spademeta._

  lazy val scalarIO:ScalarIO[this.type] = ScalarIO(this)
  lazy val vectorIO:VectorIO[this.type] = VectorIO(this)
  lazy val ctrlIO:ControlIO[this.type] = ControlIO(this)

  var vbufs:List[VectorMem] = Nil
  var sbufs:List[ScalarMem] = Nil
  def numScalarBufs(num:Int, size:Int):this.type = { sbufs = List.tabulate(num)  { i => ScalarMem(size).index(i) }; this }
  def numScalarBufs:Int = sbufs.size
  def numVecBufs(num:Int, size:Int):this.type = { vbufs = List.tabulate(num) { i => VectorMem(size).index(i) }; this }
  def numVecBufs:Int = vbufs.size

  def ctrlBox:CtrlBox
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(numArgIns:Int, numArgOuts:Int)(implicit spade:Spade) extends Controller { self =>
  import spademeta._
  lazy val ctrlBox:TopCtrlBox = TopCtrlBox()
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    souts.foreach { psout =>
      sim.mapping.vomap.pmap.get(psout).foreach { case sout:pir.graph.ScalarOut =>
        boundOf.get(sout.scalar) match {
          case Some(b:Int) => psout.ic.v := b
          case Some(b:Float) => psout.ic.v := b
          case None => warn(s"${sout.scalar} doesn't have a bound")
          case b => err(s"Don't know how to simulate bound:$b of ${sout.scalar}")
        }
      }
    }
    super.register
  }
}

/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:SwitchNetwork) extends NetworkElement {
  import spademeta._
  override val typeStr = "sb"
  val scalarIO:ScalarIO[this.type] = ScalarIO(this)
  val vectorIO:VectorIO[this.type] = VectorIO(this)
  val ctrlIO:ControlIO[this.type] = ControlIO(this)
  def connectXbar[P<:PortType](gio:GridIO[P, this.type]) = {
    gio.ins.foreach { in => gio.outs.foreach { out => out.ic <== in.ic } }
  }
  def connectXbars = {
    connectXbar(scalarIO)
    connectXbar(vectorIO)
    connectXbar(ctrlIO)
  }
  override def register(implicit sim:Simulator):Unit = {
    super.register
    val fimap = sim.mapping.fimap
    (souts ++ vouts ++ couts).foreach { out =>
      fimap.get(out.ic).foreach { out.ic :== _ }
    }
  }
}
/*
 * ComputeUnit
 * */
class ComputeUnit()(implicit spade:Spade) extends Controller {
  import spademeta._
  //override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  val regs:List[ArchReg] = List.tabulate(numRegs) { ir => ArchReg().index(ir) }
  val srams:List[SRAM] = List.tabulate(numSRAMs) { i => SRAM(sramSize).index(i) }
  val ctrs:List[Counter] = List.tabulate(numCtrs) { i => Counter().index(i) }
  //var sbufs:List[ScalarMem] = Nil // in Controller
  def bufs:List[LocalBuffer] = sbufs ++ vbufs
  def mems:List[OnChipMem] = srams ++ sbufs ++ vbufs

  lazy val ctrlBox:CtrlBox = new InnerCtrlBox(numUDCs)
  def vout = vouts.head
  def numLanes:Int = spade.numLanes
  
 // Scalar inputs. 1 per word in bus input 
  //lazy val _sins:List[ScalarIn] = List.tabulate(vins.size, spade.numLanes) { case (ib, is) =>
      //ScalarIn(vins(ib).outports(is)).index(spade.numLanes*ib + is)
  //}.flatten
  //def sins = _sins
  //lazy val _souts:List[ScalarOut] = List.tabulate(spade.numLanes) { is => ScalarOut(None) }
  //def souts = _souts
  protected val _regstages:ListBuffer[FUStage] = ListBuffer.empty  // Regular Stages
  protected val _rdstages:ListBuffer[ReduceStage] = ListBuffer.empty // Reduction Stages
  protected val _fustages:ListBuffer[FUStage] = ListBuffer.empty // Function Unit Stages
  protected val _stages:ListBuffer[Stage] = ListBuffer.empty // All stages
  //_stages += etstage

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

  /* Parameters */
  def numRegs = 16
  def numCtrs = 5
  def numSRAMs = 0
  def sramSize = 0
  def numUDCs = 5
  override def config(implicit spade:SwitchNetwork) = {
    addRegstages(numStage=2, numOprds=3, ops)
    addRdstages(numStage=4, numOprds=3, ops)
    addRegstages(numStage=2, numOprds=3, ops)
    numScalarBufs(4, 256)
    numVecBufs(vins.size, 256)
    color(0 until numCtrs, CounterReg)
    color(0, ReduceReg).color(1, AccumReg)
    color(5 until 5 + numScalarBufs, ScalarInReg)
    color(5 until 5 + souts.size, ScalarOutReg)
    color(9 until 9 + numVecBufs, VecInReg)
    color(9 until 9 + vouts.size, VecOutReg)
    genConnections
  }

  override def register(implicit sim:Simulator):Unit = {
    import sim.mapping._
    // Add delay to output if input is from doneXBar
    clmap.pmap.get(this).foreach { cu =>
      ctrlBox match {
        case cb:InnerCtrlBox =>
          couts.foreach { cout =>
            sim.mapping.fimap.get(cout.ic).foreach { 
              case from if from==cb.doneXbar.out => cout.ic.v := from.vAt(stages.size)
              case _ =>
            }
          }
        case _ =>
      }
      val enable = ctrlBox match {
        case cb:MemoryCtrlBox => 
          val readStages = cu.asMP.rdAddrStages
          val numReadStages = if (readStages.isEmpty) 0 else stmap(readStages.last).index - stmap(readStages.head).index
          Some(cb.readEn.out.vAt(numReadStages + 1))
        case cb:InnerCtrlBox => Some(cb.en.out.vAt(stages.size))
        case _ => None
      }
      vouts.foreach { vout =>
        fimap.get(vout.ic).fold {
          if (vout.ic.fanIns.size==1) {
            vout.ic.v.set { v =>
              v <<= vout.ic.fanIns.head.v
              v.valid <<= enable.get
            }
          }
        } { out => 
          vout.ic.v.set { v =>
            v <<= out.v
            v.valid <<= enable.get
          }
        }
      }
    }
    super.register
  }
}

class OuterComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  import spademeta._
  override val typeStr = "ocu"
  
  override lazy val ctrlBox:OuterCtrlBox = new OuterCtrlBox(numUDCs)
  override def numLanes:Int = 1

  /* Parameters */
  override def numRegs = 0
  override def numCtrs = 6
  override def numUDCs = 15
  override def config(implicit spade:SwitchNetwork) = {
    numScalarBufs(4, 16)
    genConnections
  }
}

class MemoryComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  override val typeStr = "mcu"
  import spademeta._

  override lazy val ctrlBox:MemoryCtrlBox = new MemoryCtrlBox(numUDCs)
  override def numLanes:Int = 1

  private val _wastages:ListBuffer[WAStage] = ListBuffer.empty // Write Addr Stages
  private val _rastages:ListBuffer[RAStage] = ListBuffer.empty // Read Addr Stages
  def wastages:List[WAStage] = _wastages.toList // Write Addr Stages
  def rastages:List[RAStage] = _rastages.toList // Read Addr Stages
  def addWAstages(stages:List[WAStage]) = { _wastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addRAstages(stages:List[RAStage]) = { _rastages ++= stages; _fustages ++= stages; addStages(stages) }
  def addWAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addWAstages(List.fill(numStage) { WAStage(numOprds=numOprds, regs, ops)}); this // Write Addr stage 
  } 
  def addRAstages(numStage:Int, numOprds:Int, ops:List[Op]):this.type = {
    addRAstages(List.fill(numStage) { RAStage(numOprds=numOprds, regs, ops)}); this // Read Addr stage 
  } 
  def sram = srams.head

  /* Parameters */
  override def numRegs = 16
  override def numCtrs = 8
  override def numSRAMs = 1
  override def sramSize = 32768
  override def numUDCs = 0
  override def config(implicit spade:SwitchNetwork) = {
    addWAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    addRAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    numScalarBufs(4, 16)
    numVecBufs(vins.size, 16)
    color(0 until numCtrs, CounterReg)
    color(7, ReadAddrReg).color(8, WriteAddrReg)
    color(8 until 8 + numScalarBufs, ScalarInReg)
    color(12 until 12 + numVecBufs, VecInReg)
    genConnections
  }
}

/* A spetial type of CU used for memory loader/storer */
class ScalarComputeUnit()(implicit spade:Spade) extends ComputeUnit {
  override val typeStr = "scu"
  import spademeta._
  override def numLanes:Int = 1

  /* Parameters */
  override def numRegs = 16
  override def numCtrs = 6
  override def numUDCs = 4
  override def config(implicit spade:SwitchNetwork) = {
    addRegstages(numStage=6, numOprds=3, fixOps ++ bitOps ++ otherOps)
    numScalarBufs(6, 16)
    numVecBufs(vins.size, 16)
    color(0 until numCtrs, CounterReg)
    color(7 until 7 + numScalarBufs, ScalarInReg)
    color(8 until 8 + souts.size, ScalarOutReg)
    color(12 until 12 + numVecBufs, VecInReg)
    genConnections
  }
}
class MemoryController()(implicit spade:Spade) extends Controller {
  override val typeStr = "mc"
  import spademeta._
  lazy val ctrlBox:CtrlBox = new MCCtrlBox()

  /* Parameters */
  override def config(implicit spade:SwitchNetwork) = {
    //assert(sins.size==2)
    //assert(vins.size==1)
    numScalarBufs(4, 256)
    numVecBufs(vins.size, 16)
    nameOf(sbufs(0)) = "roffset"
    nameOf(sbufs(1)) = "woffset"
    nameOf(sbufs(2)) = "rsize"
    nameOf(sbufs(3)) = "wsize"
    nameOf(vbufs(0)) = "data"
    genConnections
  }
}
