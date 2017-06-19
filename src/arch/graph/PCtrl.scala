package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.util.pipelinedBy
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._
import pir.mapper.PIRMap

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class LUT(implicit spade:Spade, pne:NetworkElement) extends Node {
  val numIns:Int
}
case class EnLUT(numIns:Int)(implicit spade:Spade, pne:NetworkElement) extends LUT {
  import spademeta._
  override val typeStr = "enlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
}
case class TokenOutLUT()(implicit spade:Spade, pne:NetworkElement) extends LUT{
  import spademeta._
  override val typeStr = "tolut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  override val numIns = 2 // Token out is a combination of two output
}
case class TokenDownLUT(numIns:Int)(implicit spade:Spade, pne:NetworkElement) extends LUT {
  override val typeStr = "tdlut"
}
object TokenDownLUT {
  def apply(idx:Int, numIns:Int)(implicit spade:Spade, pne:NetworkElement):TokenDownLUT = 
    TokenDownLUT(numIns).index(idx)
}
case class UDCounter()(implicit spade:Spade, override val pne:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "udc"
  cb._udcs += this
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val count = Output(Word(), this, s"${this}.count")
  val out = Output(Bit(), this, s"${this}.out")
  def init(mp:PIRMap):Option[Int] = {
    mp.pmmap.pmap.get(this).map { case udc:pir.graph.UDCounter => udc.initVal }
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    if (isMapped(this)(mapping)) {
      val initVal = init(mapping).getOrElse(0)
      dprintln(s"${quote(this)} -> ${pmmap.pmap.get(this)} initVal=$initVal")
      count.v.default = initVal
      count.v.set { countv =>
        if (rst) {
          countv <<= initVal
        } else {
          Match(
            inc.pv -> { () => countv <<= countv + 1 },
            dec.pv -> { () => 
              If(countv==0) { errmsg(s"${quote(this)} of ${quote(pne)} underflow at cycle #$cycle") }
              countv <<= countv - 1
            } 
          ) {}
        }
      }
      out.v := (count.v > 0)
    }
    super.register
  }
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, pne:Controller, cb:CtrlBox):UDCounter = UDCounter().index(idx)
}

case class AndGate(name:Option[String])(implicit spade:Spade, override val pne:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  override val typeStr = name.getOrElse("ag")
  cb.andGates += this
  val out = Output(Bit(), this, s"${this}.out")
  private[plasticine] def <== (outs:List[Output[Bit, Module]]):Unit = outs.foreach { out => <==(out) }
  private[plasticine] def <== (out:Output[Bit, Module]):Unit = {
    val i = ins.size
    val in = Input(Bit(), this, s"${this}.in$i").index(i)
    in <== out
  }

  override def register(implicit sim:Simulator):Unit = {
    val invs = ins.map(_.v).collect{ case v:BitValue => v }
    out.v := {
      val res = invs.map{_.update.value }.reduceOption[Option[Boolean]]{ case (in1, in2) => 
        eval(BitAnd, in1, in2).asInstanceOf[Option[Boolean]]
      }
      res.getOrElse(None)
    }
    super.register
  }
}
object AndGate {
  def apply(name:String)(implicit spade:Spade, pne:Controller, cb:CtrlBox):AndGate = {
    AndGate(Some(name))
  }
}
case class AndTree(name:Option[String])(implicit spade:Spade, override val pne:Controller, cb:CtrlBox) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = name.getOrElse("at")
  cb.andTrees += this
  val out = Output(Bit(), this, s"${this}.out")
  private[plasticine] def <== (outs:List[Output[Bit, Module]]):Unit = outs.foreach { out => <==(out) }
  private[plasticine] def <== (out:Output[Bit, Module]):Unit = {
    val i = ins.size
    val in = Input(Bit(), this, s"${this}.in$i").index(i)
    in <== Const(true).out
    in <== out
  }

  override def register(implicit sim:Simulator):Unit = {
    val invs = ins.map(_.v).collect{ case v:BitValue => v }
    out.v := {
      val res = invs.map{_.update.value }.reduceOption[Option[Boolean]]{ case (in1, in2) => 
        eval(BitAnd, in1, in2).asInstanceOf[Option[Boolean]]
      }
      res.getOrElse(None)
    }
    super.register
  }
}
object AndTree {
  def apply(name:String)(implicit spade:Spade, pne:Controller, cb:CtrlBox):AndTree = AndTree(Some(name))
  def apply()(implicit spade:Spade, pne:Controller, cb:CtrlBox):AndTree = AndTree(None)
}

case class PulserSM()(implicit spade:Spade, override val pne:Controller) extends Primitive with Simulatable {
  val done = Input(Bit(), this, s"${this}.done")
  val en = Input(Bit(), this, s"${this}.en")
  val init = Input(Bit(), this, s"${this}.init")
  val out = Output(Bit(), this, s"${this}.out")
  val INIT = false
  val RUNNING = true
  val state = Output(Bit(), this, s"${this}.state")
  var pulseLength = 1
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.util._
    clmap.pmap.get(pne).foreach { cu =>
      if (cu.isSeq || cu.isMeta) {
        state.v.default = INIT 
        out.v.set { outv =>
          If (state.v == INIT) {
            If(init.v) {
              outv.setHigh
              pulseLength = lengthOf(cu) / pipelinedBy(cu)(sim.design)
              state.v <<= RUNNING
            }
          } 
          If(state.v == RUNNING) {
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
    super.register
  }
}

case class UpDownSM()(implicit spade:Spade, override val pne:Controller) extends Primitive with Simulatable {
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

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    if (isMapped(this)(mapping)) {
      dprintln(s"${quote(this)} -> ${pmmap.pmap.get(this)}")
      done.v.default = false 
      done.v.set { donev =>
        If (doneIn.v) { donev.setHigh }
        If (doneOut.v) { donev.setLow }
      }
      notDone.v := done.v.not
      count.v.set { countv =>
        if (rst) countv <<= 0 
        else {
          Match(
            (inc.pv & done.pv.not) -> { () => countv <<= countv + 1 },
            dec.pv -> { () => countv <<= countv - 1 }
          ) {}
        }
      }
      out.v := (count.v > 0)
      notRun.v := out.v.not 
      finished.v := (done.pv & notRun.pv)
      doneOut.v.set { doneOutv =>
        Match(
          (finished.v & finished.pv.not) -> { () => doneOutv.setHigh },
          doneOut.pv -> { () => doneOutv.setLow }
        ) { doneOutv.setLow }
      } 
    }
    super.register
  }
}

abstract class CtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:Controller) extends Primitive with Simulatable {
  implicit val ctrlBox:CtrlBox = this
  import spademeta._
  for (i <- 0 until numUDCs) { UDCounter(idx=i) }
  lazy val _udcs = ListBuffer[UDCounter]()
  def udcs = _udcs.toList
  lazy val andTrees = ListBuffer[AndTree]()
  lazy val delays = ListBuffer[Delay[Bit]]()
  lazy val andGates = ListBuffer[AndGate]()
}



class InnerCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0, s"${quote(pne)}.doneXbar")
  val doneDelay = Delay(Bit(), pne.stages.size, s"${quote(pne)}.doneDelay")
  doneDelay.in <== doneXbar.out
  val en = Delay(Bit(), 0, s"${quote(pne)}.en")
  val enDelay = Delay(Bit(), pne.stages.size, s"${quote(pne)}.enDelay")
  enDelay.in <== en.out
  val tokenInXbar = Delay(Bit(), 0)
  val siblingAndTree = AndTree("siblingAndTree") 
  val fifoAndTree = AndTree("fifoAndTree")
  val tokenInAndTree = AndTree("tokenInAndTree")
  val pipeAndTree = AndTree("pipeAndTree")
  pipeAndTree <== siblingAndTree.out
  pipeAndTree <== fifoAndTree.out
  val streamAndTree = AndTree("streamAndTree")
  streamAndTree <== tokenInAndTree.out
  streamAndTree <== fifoAndTree.out
  en.in <== pipeAndTree.out // 0
  en.in <== streamAndTree.out // 1
  val accumPassThrough = Output(Bit(), this, s"${quote(pne)}.accumPassThrough")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import sim.pirmeta._
    super.register
    clmap.pmap.get(pne).foreach { case icl:pir.graph.InnerController =>
      assert(icl.accumRegs.size<=1)
      icl.accumRegs.headOption match {
        case None => //accumPassThrough.v := false
        case Some(acc) => 
          val ctr = accumCounterOf(acc)
          val pctr = ctmap(ctr)
          accumPassThrough.v := pctr.out.v.head.asWord == 0
      }
    }
  }
}

class OuterCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:OuterComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0, s"$pne.doneXbar")
  val en = Delay(Bit(), 0, s"$pne.en")
  val childrenAndTree = AndTree("childrenAndTree") 
  val siblingAndTree = AndTree("siblingAndTree") 
  val udsm = UpDownSM()
  udsm.doneIn <== doneXbar.out
  udsm.dec <== childrenAndTree.out
  udsm.inc <== en.out
  val enAnd = AndGate("enAnd")
  enAnd <== udsm.notDone
  enAnd <== udsm.notRun
  enAnd.ins(1).asBit <== Const(true).out
  enAnd <== siblingAndTree.out
  en.in <== enAnd.out 
}

class MemoryCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:MemoryComputeUnit) extends CtrlBox(numUDCs) {
  val readDoneXbar = Delay(Bit(), 0, s"$pne.readDoneXbar")
  val writeDoneXbar = Delay(Bit(), 0, s"$pne.writeDoneXbar")
  val tokenInXbar = Delay(Bit(), 0, s"$pne.tokenInXbar")
  val writeFifoAndTree = AndTree("writeFifoAndTree") 
  val readFifoAndTree = AndTree("readFifoAndTree") 
  val writeEn = Delay(Bit(), 0, s"$pne.writeEn")
  val readEn = Delay(Bit(),0, s"$pne.readEn") 
  val readUDC = UDCounter()
  val readAndGate = AndGate(s"$pne.readAndGate")
  readAndGate <== readUDC.out
  readAndGate <== readFifoAndTree.out 
}

case class TopCtrlBox()(implicit spade:Spade, override val pne:Top) extends CtrlBox(0) {
  val command = Output(Bit(), this, s"command")
  val status = Input(Bit(), this, s"status")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    super.register
    status.vAt(3)
    command.v.set { v =>
      if (rst) v.setHigh
      else v.setLow
    }
  }
}

class MCCtrlBox()(implicit spade:Spade, override val pne:MemoryController) extends CtrlBox(0) {
  val rdone = Output(Bit(), this, s"${this}.rdone")
  val wdone = Output(Bit(), this, s"${this}.wdone")
  val fifoAndTree = AndTree("fifoAndTree")
  val en = Delay(Bit(), 0, s"$pne.en")
  val WAITING = false
  val LOADING = true
  val state = Output(Bit(), this, s"${this}.state")
  val count = Output(Word(), this, s"${this}.count")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import spademeta._
    clmap.pmap.get(pne).foreach { case mc:pir.graph.MemoryController =>
      state.v.default = WAITING 
      mc.mctpe match {
        case TileLoad =>
          en.in.v.set { env =>
            If(fifoAndTree.out.v & (rdone.pv | (state.v == WAITING))) {
              env.setHigh
            }
            If(state.v==LOADING) {
              env.setLow
            }
          }
          state.v.set { statev =>
            If(en.out.pv & (state.pv==WAITING)) {
              statev <<= LOADING
            }
            If(rdone.pv & (state.pv==LOADING)) {
              statev <<= WAITING
            }
          }
          val par = spade.numLanes //TODO loader's par
          count.v.set { countv =>
            Match(
              sim.rst -> { () => countv <<= 0 },
              rdone.pv -> { () => countv <<= 0 },
              (state.pv==LOADING) -> { () => countv <<= countv + par }
            ) {}
          }
          val size = pne.sbufs.filter{ sb => nameOf(sb)=="rsize" }.head.readPort
          rdone.v := (count.v >= eval(FltSub, size.v.update / 4, par))
        case TileStore =>
        case Gather =>
        case Scatter =>
      }
    }
    super.register
  }
}
