package pir.spade.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.util.pipelinedBy
import pir.spade.main._
import pir.spade.util._
import pir.spade.simulation._
import pir.mapper.PIRMap

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class LUT(implicit spade:Spade, prt:Routable) extends Node {
  val numIns:Int
}
case class EnLUT(numIns:Int)(implicit spade:Spade, prt:Routable) extends LUT {
  import spademeta._
  override val typeStr = "enlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
case class TokenOutLUT()(implicit spade:Spade, prt:Routable) extends LUT{
  import spademeta._
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2 // Token out is a combination of two output
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade, prt:Routable) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade, prt:Routable):TokenDownLUT = 
    TokenDownLUT(numIns).index(idx)
}

case class UDCounterConfig (
  initVal:Int,
  name:String
) extends Configuration

case class UDCounter()(implicit spade:Spade, override val prt:Controller) extends Primitive with Simulatable 
with Configurable {
  import spademeta._
  type CT = UDCounterConfig

  override val typeStr = "udc"
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val count = Output(Word(), this, s"${this}.count")
  val out = Output(Bit(), this, s"${this}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { config =>
      dprintln(s"${quote(this)} -> ${config.name} initVal=${config.initVal}")
      count.v.default = config.initVal
      count.v.set { countv =>
        if (rst) {
          countv <<= config.initVal
        } else {
          If (inc.pv) { countv <<= countv + 1 }
          If (dec.pv) {
            if (sim.inSimulation && countv.toInt==0) {
              warn(s"${quote(this)}(${config.name}) of ${quote(prt)} underflow at cycle #$cycle")
            }
            countv <<= countv - 1
          }
        }
      }
      out.v := (count.v > 0)
    }
  }
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, prt:Controller, cb:CtrlBox):UDCounter = {
    val udc = UDCounter().index(idx)
    cb._udcs += udc
    udc
  }
}

case class AndGate(name:Option[String])(implicit spade:Spade, override val prt:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  cb.andGates += this
  override val typeStr = name.getOrElse("ag")
  val out = Output(Bit(), this, s"${this}.out")
  private[spade] def <== (outs:List[Output[Bit, Module]]):Unit = outs.foreach { out => <==(out) }
  private[spade] def <== (out:Output[Bit, Module]):Unit = {
    val i = ins.size
    val in = Input(Bit(), this, s"${this}.in$i").index(i)
    in <== out
  }

  override def register(implicit sim:Simulator):Unit = {
    val invs = ins.map(_.v).collect{ case v:SingleValue => v }
    out.v := {
      val res = invs.map{ _.update.value }.reduceOption[Option[AnyVal]]{ case (in1, in2) => 
        eval(BitAnd, in1, in2)
      }
      res.getOrElse(None)
    }
  }
}
object AndGate {
  def apply(name:String)(implicit spade:Spade, prt:Controller, cb:CtrlBox):AndGate = {
    AndGate(Some(name))
  }
}
case class AndTree(name:Option[String])(implicit spade:Spade, override val prt:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = name.getOrElse("at")
  cb.andTrees += this
  val out = Output(Bit(), this, s"${this}.out")
  private[spade] def <== (outs:List[Output[Bit, Module]]):Unit = outs.foreach { out => <==(out) }
  private[spade] def <== (out:Output[Bit, Module]):Unit = {
    val i = ins.size
    val in = Input(Bit(), this, s"${this}.in$i").index(i)
    in <== Const(true).out
    in <== out
  }

  override def register(implicit sim:Simulator):Unit = {
    val invs = ins.map(_.v).collect{ case v:SingleValue => v }
    out.v := {
      val res = invs.map{_.update.value }.reduceOption[Option[AnyVal]]{ case (in1, in2) => 
        eval(BitAnd, in1, in2)
      }
      res.getOrElse(None)
    }
  }
}
object AndTree {
  def apply(name:String)(implicit spade:Spade, prt:Controller, cb:CtrlBox):AndTree = AndTree(Some(name))
  def apply()(implicit spade:Spade, prt:Controller, cb:CtrlBox):AndTree = AndTree(None)
}

case class PulserSMConfig (
  pulserLength:Int
) extends Configuration

case class PulserSM()(implicit spade:Spade, override val prt:OuterComputeUnit) extends Primitive with Simulatable with Configurable {
  type CT = PulserSMConfig
  val done = Input(Bit(), this, s"${this}.done")
  val en = Input(Bit(), this, s"${this}.en")
  val init = Input(Bit(), this, s"${this}.init")
  val out = Output(Bit(), this, s"${this}.out")
  val INIT = false
  val RUNNING = true
  val state = Output(Bit(), this, s"${this}.state")
  var pulseLength = 1

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(prt).foreach { cuconfig:OuterComputeUnitConfig =>
      val config = cfmap(this)
      if (cuconfig.isSeq || cuconfig.isMeta) {
        state.v.default = INIT 
        out.v.set { outv =>
          If (state.v =:= INIT) {
            If(init.v) {
              outv.setHigh
              pulseLength = config.pulserLength// lengthOf(cu) / pipelinedBy(cu)(sim.design)
              state.v <<= RUNNING
            }
          } 
          If(state.v =:= RUNNING) {
            IfElse (done.v) {
              state.v <<= INIT
            } {
              If (en.pv) {
                outv.setHigh
                pulseLength = 1 
              }
            }
          } 
          If(out.vAt(pulseLength)) { outv.setLow }
        }
      }
    }
  }
}

case class UpDownSMConfig (
  name:String
) extends Configuration

case class UpDownSM()(implicit spade:Spade, override val prt:Controller) extends Primitive with Simulatable with Configurable {

  type CT = UpDownSMConfig

  val doneIn = Input(Bit(), this, s"${this}.doneIn")
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val doneOut = Output(Bit(), this, s"${this}.doneOut")
  val notDone = Output(Bit(), this, s"${this}.notDone")
  val notRun = Output(Bit(), this, s"${this}.notRun")
  val finished = Output(Bit(), this, s"${this}.finished")
  // Internal signals 
  val out = Output(Bit(), this, s"${this}.out")
  val count = Output(Word(), this, s"${this}.count")
  val done = Output(Bit(), this, s"${this}.done") // Initially low
  val udc = UDCounter()

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { config =>
      dprintln(s"${quote(this)} -> ${config.name}")
      done.v.default = false 
      done.v.set { donev =>
        If (doneIn.v) { donev.setHigh }
        If (doneOut.v) { donev.setLow }
      }
      notDone.v := done.v.not
      udc.inc.v := inc.v
      udc.dec.v := dec.v
      count.v := udc.count.v
      out.v := udc.out.v
      notRun.v := out.v.not 
      finished.v := (done.pv & notRun.pv)
      doneOut.v.set { doneOutv =>
        Match(
          (finished.v & finished.pv.not) -> { () => doneOutv.setHigh },
          doneOut.pv -> { () => doneOutv.setLow }
        ) { doneOutv.setLow }
      } 
    }
  }
}

case class PredicateUnit(name:String)(implicit spade:Spade, override val prt:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  override val typeStr = s"pu_$name"
  cb.predicateUnits += this
  val in = Input(Word(), this, s"${quote(this)}.in")
  //prt.match {
    //case cu:ComputeUnit => 
      //(0 until numCtrs).foreach { i => Input(Word(), this, s"${quote(this)}.in$i") }
    //case _ =>
  //}
  val out = Output(Bit(), this, s"${quote(this)}.out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import pir.util.typealias._
    pmmap.get(this).fold {
      out.v := false
    } { case pdu:PDU =>
      out.v := eval(pdu.op, in.v, pdu.const)
    }
  }
}

abstract class CtrlBox()(implicit spade:Spade, override val prt:Controller) extends Primitive with Simulatable {
  implicit val ctrlBox:CtrlBox = this
  import spademeta._
  lazy val _udcs = ListBuffer[UDCounter]()
  def udcs = _udcs.toList
  lazy val andTrees = ListBuffer[AndTree]()
  lazy val delays = ListBuffer[Delay[Bit]]()
  lazy val andGates = ListBuffer[AndGate]()
  lazy val predicateUnits = ListBuffer[PredicateUnit]()

  val fifoAndTree = AndTree("fifoAndTree")
  fifoAndTree <== prt.bufs.map(_.notEmpty) 

  def register(implicit sim:Simulator):Unit = {
    delays.foreach { delay =>
      delay.in.v.default = false
      delay.out.v.default = false
    }
  }
}

abstract class StageCtrlBox()(implicit spade:Spade, override val prt:ComputeUnit) extends CtrlBox {
  import prt.param._

  val en = Delay(Bit(), 0, s"${quote(prt)}.en")
  val done = Delay(Bit(), 0, s"${quote(prt)}.done")

  for (i <- 0 until numUDCs) { UDCounter(idx=i) }
  val siblingAndTree = AndTree("siblingAndTree") 
  siblingAndTree <== udcs.map(_.out)
}

class InnerCtrlBox()(implicit spade:Spade, override val prt:ComputeUnit) 
  extends StageCtrlBox {
  val doneDelay = Delay(Bit(), prt.stages.size, s"${quote(prt)}.doneDelay")
  doneDelay.in <== done.out

  val enDelay = Delay(Bit(), prt.stages.size, s"${quote(prt)}.enDelay")
  enDelay.in <== en.out

  val tokenInXbar = Delay(Bit(), 0)
  tokenInXbar.in <== prt.cins.map(_.ic)

  val tokenInAndTree = AndTree("tokenInAndTree")
  tokenInAndTree <== prt.cins.map(_.ic)

  val enAnd = AndGate("enAnd")
  enAnd <== siblingAndTree.out
  enAnd <== tokenInAndTree.out
  enAnd <== fifoAndTree.out

  en.in <== enAnd.out

  val accumPredUnit = PredicateUnit("accum")
  val fifoPredUnit = PredicateUnit("fifo")
  prt.ctrs.foreach { ctr => 
    accumPredUnit.in <== (ctr.out, 0)
    fifoPredUnit.in <== (ctr.out, 0) 
  }
}

class OuterCtrlBox()(implicit spade:Spade, override val prt:OuterComputeUnit) 
  extends StageCtrlBox {
  val childrenAndTree = AndTree("childrenAndTree") 
  childrenAndTree <== udcs.map(_.out)

  val udsm = UpDownSM()
  udsm.doneIn <== done.out
  udsm.dec <== childrenAndTree.out
  udsm.inc <== en.out

  val enAnd = AndGate("enAnd")
  enAnd <== udsm.notDone
  enAnd <== udsm.notRun
  enAnd.ins(1).asBit <== Const(true).out
  enAnd <== siblingAndTree.out

  en.in <== enAnd.out 
}

class MemoryCtrlBox()(implicit spade:Spade, override val prt:MemoryComputeUnit) extends CtrlBox() {
  val tokenInXbar = Delay(Bit(), 0, s"$prt.tokenInXbar")

  val writeFifoAndTree = AndTree("writeFifoAndTree") 

  val readFifoAndTree = AndTree("readFifoAndTree") 

  val tokenInAndTree = AndTree("tokenInAndTree")
  tokenInAndTree <== prt.cins.map(_.ic)

  //val readUDC = UDCounter()

  val readAndGate = AndGate(s"$prt.readAndGate")
  //readAndGate <== readUDC.out
  readAndGate <== tokenInAndTree.out
  readAndGate <== readFifoAndTree.out 

  val readEn = Delay(Bit(),0, s"$prt.readEn") 
  readEn.in <== readAndGate.out
  val writeEn = Delay(Bit(), 0, s"$prt.writeEn")
  writeEn.in <== writeFifoAndTree.out

  val readEnDelay = Delay(Bit(),0, s"$prt.readEnDelay") 
  readEnDelay.in <== readEn.out
  val writeEnDelay = Delay(Bit(),0, s"$prt.writeEnDelay") 
  writeEnDelay.in <== writeEn.out

  val readDone = Delay(Bit(), 0, s"$prt.readDone")
  val writeDone = Delay(Bit(), 0, s"$prt.writeDone")

  val readDoneDelay = Delay(Bit(), 0, s"$prt.readDoneDelay")
  readDoneDelay.in <== readDone.out
  val writeDoneDelay = Delay(Bit(), 0, s"$prt.writeDoneDelay")
  writeDoneDelay.in <== writeDone.out
}

case class TopCtrlBox()(implicit spade:Spade, override val prt:Top) extends CtrlBox() {

  val command = Output(Bit(), this, s"command")
  val status = Input(Bit(), this, s"status")

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    status.vAt(3)
    command.v.set { v =>
      if (rst) v.setHigh
      else v.setLow
    }
    super.register
  }
}

class MCCtrlBox()(implicit spade:Spade, override val prt:MemoryController) extends CtrlBox() {
  val rdone = Output(Bit(), this, s"${this}.rdone")
  val wdone = Output(Bit(), this, s"${this}.wdone")
  val en = Delay(Bit(), 0, s"$prt.en")
  val WAITING = false
  val RUNNING = true
  val state = Output(Bit(), this, s"${this}.state")
  val running = Output(Bit(), this, s"${this}.running")
  val count = Output(Word(), this, s"${this}.count")

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import spademeta._
    pmmap.get(prt).foreach { case mc:pir.graph.MemoryController =>
      state.v.default = WAITING 
      running.v.default = false
      mc.mctpe match {
        case tp if tp.isDense =>
          val (done, size) = tp match {
            case TileLoad => (rdone, prt.rsize)
            case TileStore => (wdone, prt.wsize)
            case _ => throw new Exception(s"Not possible match")
          }
          running.v := (state.v =:= RUNNING)
          en.in.v := fifoAndTree.out.v & (done.pv | running.pv.not)
          state.v.set { statev =>
            If(done.v) {
              statev <<= WAITING
            }
            If(en.out.v) {
              statev <<= RUNNING
            }
          }
          val par = spade.numLanes //TODO loader's / store's par
          count.v.set { countv =>
            Match(
              sim.rst -> { () => countv <<= 0 },
              done.pv -> { () => countv <<= 0 },
              (running.pv) -> { () => countv <<= countv + par }
            ) {}
          }
          done.v := running.v & (count.v >= eval(FixSub, size.readPort.v / 4, par))
        case Gather =>
        case Scatter =>
      }
    }
    super.register
  }
}
