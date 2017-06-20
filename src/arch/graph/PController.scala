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
  def bufs:List[LocalBuffer] = sbufs ++ vbufs
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
    import sim.util._
    souts.foreach { psout =>
      vomap.pmap.get(psout).foreach { case sout:pir.graph.ScalarOut =>
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
    import sim.util._
    super.register
    (souts ++ vouts ++ couts).foreach { out =>
      fimap.get(out.ic).foreach { out.ic :== _ }
    }
  }
}

trait ComputeUnitParam {
  val numRegs = 16
  val numCtrs = 5
  val numSRAMs = 0
  val sramSize = 0
  val numUDCs = 5
}
/*
 * ComputeUnit
 * */
abstract class ComputeUnit(val param:ComputeUnitParam)(implicit spade:Spade) extends Controller {
  import spademeta._
  import param._
  //override implicit val ctrler:ComputeUnit = this 
  override val typeStr = "cu"

  val regs:List[ArchReg] = List.tabulate(numRegs) { ir => ArchReg().index(ir) }
  val srams:List[SRAM] = List.tabulate(numSRAMs) { i => SRAM(sramSize).index(i) }
  val ctrs:List[Counter] = List.tabulate(numCtrs) { i => Counter().index(i) }
  //var sbufs:List[ScalarMem] = Nil // in Controller
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

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    // Add delay to output if input is from doneXBar
    clmap.pmap.get(this).foreach { cu =>
      ctrlBox match {
        case cb:InnerCtrlBox =>
        case _ =>
      }
      val enable = ctrlBox match {
        case cb:MemoryCtrlBox => 
          val readStages = cu.asMP.rdAddrStages
          val numReadStages = if (readStages.isEmpty) 0 else stmap(readStages.last).index - stmap(readStages.head).index
          Some(cb.readEn.out.vAt(numReadStages + 1))
        case cb:InnerCtrlBox => Some(cb.enDelay.out.v)
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

class PatternComputeUnitParam() extends ComputeUnitParam() {

  def config(cu:PatternComputeUnit)(implicit spade:SwitchNetwork) = {
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.addRdstages(numStage=4, numOprds=3, ops)
    cu.addRegstages(numStage=2, numOprds=3, ops)
    cu.numScalarBufs(4, 256)
    cu.numVecBufs(cu.vins.size, 256)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(0, ReduceReg).color(1, AccumReg)
    cu.color(5 until 5 + cu.numScalarBufs, ScalarInReg)
    cu.color(5 until 5 + cu.souts.size, ScalarOutReg)
    cu.color(9 until 9 + cu.numVecBufs, VecInReg)
    cu.color(9 until 9 + cu.vouts.size, VecOutReg)
    cu.genConnections
  }
}
class PatternComputeUnit(override val param:PatternComputeUnitParam=new PatternComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "pcu"
  override def config(implicit spade:SwitchNetwork) = param.config(this)
}

class OuterComputeUnitParam() extends ComputeUnitParam() {
  override val numRegs = 0
  override val numCtrs = 6
  override val numUDCs = 15

  def config(cu:OuterComputeUnit)(implicit spade:SwitchNetwork) = {
    cu.numScalarBufs(4, 16)
    cu.genConnections
  }
}

class OuterComputeUnit(override val param:OuterComputeUnitParam=new OuterComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  import spademeta._
  import param._
  override val typeStr = "ocu"
  override def config(implicit spade:SwitchNetwork) = param.config(this)

  override lazy val ctrlBox:OuterCtrlBox = new OuterCtrlBox(numUDCs)
  override def numLanes:Int = 1
}

class MemoryComputeUnitParam() extends ComputeUnitParam() {
  override val numRegs = 16
  override val numCtrs = 8
  override val numSRAMs = 1
  override val sramSize = 32768
  override val numUDCs = 0

  /* Parameters */
  def config(cu:MemoryComputeUnit)(implicit spade:SwitchNetwork) = {
    cu.addWAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    cu.addRAstages(numStage=3, numOprds=3, fixOps ++ otherOps)
    cu.numScalarBufs(4, 16)
    cu.numVecBufs(cu.vins.size, 16)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(7, ReadAddrReg).color(8, WriteAddrReg)
    cu.color(8 until 8 + cu.numScalarBufs, ScalarInReg)
    cu.color(12 until 12 + cu.numVecBufs, VecInReg)
    cu.genConnections
  }
}
class MemoryComputeUnit(override val param:MemoryComputeUnitParam=new MemoryComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "mcu"
  override def config(implicit spade:SwitchNetwork) = param.config(this)
  import spademeta._
  import param._

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
}

class ScalarComputeUnitParam() extends ComputeUnitParam() {
  override val numRegs = 16
  override val numCtrs = 6
  override val numUDCs = 4

  /* Parameters */
  def config(cu:ScalarComputeUnit)(implicit spade:SwitchNetwork) = {
    cu.addRegstages(numStage=6, numOprds=3, fixOps ++ bitOps ++ otherOps)
    cu.numScalarBufs(6, 16)
    cu.numVecBufs(cu.vins.size, 16)
    cu.color(0 until numCtrs, CounterReg)
    cu.color(7 until 7 + cu.numScalarBufs, ScalarInReg)
    cu.color(8 until 8 + cu.souts.size, ScalarOutReg)
    cu.color(12 until 12 + cu.numVecBufs, VecInReg)
    cu.genConnections
  }
}
/* A spetial type of CU used for memory loader/storer */
class ScalarComputeUnit(override val param:ScalarComputeUnitParam=new ScalarComputeUnitParam())(implicit spade:Spade) 
  extends ComputeUnit(param) {
  override val typeStr = "scu"
  override def config(implicit spade:SwitchNetwork) = param.config(this)
  import spademeta._
  import param._
  override def numLanes:Int = 1
}
class MemoryController()(implicit spade:Spade) extends Controller {
  override val typeStr = "mc"
  import spademeta._
  lazy val ctrlBox:MCCtrlBox = new MCCtrlBox()

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

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    val dram = spade.dram
    clmap.pmap.get(this).foreach { case mc:pir.graph.MemoryController =>
      mc.mctpe match {
        case TileLoad =>
          val offset = sbufs.filter{ sb => nameOf(sb)=="roffset" }.head
          val size = sbufs.filter{ sb => nameOf(sb)=="rsize" }.head
          dram.zipWithIndex.foreach { case (e,i) => e <<= i }
          vouts.foreach { vout =>
            vout.ic.v.set { v =>
              If (ctrlBox.state.v==ctrlBox.LOADING) {
                val so = offset.readPort.v.value.get.toInt / 4
                val sz = size.readPort.v.value.get.toInt / 4
                dprintln(s"${quote(this)} so=$so sz=$sz ${ctrlBox.count.v.update}")
                v.foreach { case (ev, i) =>
                  ev <<= dram(so + i + ctrlBox.count.v.value.get.toInt)
                }
              }
              v.valid <<= ctrlBox.state.v==ctrlBox.LOADING
            }
          }
        case TileStore =>
          val offset = sbufs.filter{ sb => nameOf(sb)=="woffset" }.head
          val size = sbufs.filter{ sb => nameOf(sb)=="wsize" }.head
          val data = sbufs.filter{ sb => nameOf(sb)=="data" }.head
        case Gather =>
        case Scatter =>
      }
    }
    super.register
  }
}
