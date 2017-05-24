package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
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
case class UDCounter()(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "udc"
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val count = Output(Word(), this, s"${this}.count")
  val out = Output(Bit(), this, s"${this}.out")
  def init(mp:PIRMap):Option[Int] = {
    mp.pmmap.pmap.get(this).map { case udc:pir.graph.UDCounter => udc.initVal }
  }
  override def register(implicit sim:Simulator):Unit = {
    val fimap = sim.mapping.fimap
    val pmmap = sim.mapping.pmmap
    pmmap.pmap.get(this).fold {
      out.v := Some(true) 
    } { udc =>
      sim.dprintln(s"$inc -> ${fimap.get(inc)} ${inc.fanIns}")
      count.v.set { countv =>
        if (sim.rst) countv <<= init(sim.mapping)
        else {
          Match(
            inc.v -> { () => countv <<= countv + 1 },
            dec.v -> { () => countv <<= countv - 1 }
          ) {}
        }
      }
      out.v := (count.v > 0)
    }
    super.register
  }
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, pne:NetworkElement):UDCounter = UDCounter().index(idx)
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
    val invs = ins.map(_.v).collect{case v:BitValue => v}
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

case class PulserSM()(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  val done = Input(Bit(), this, s"${this}.done")
  val en = Input(Bit(), this, s"${this}.en")
  val init = Input(Bit(), this, s"${this}.init")
  val out = Output(Bit(), this, s"${this}.out")
}

abstract class CtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:Controller) extends Primitive {
  implicit val ctrlBox:CtrlBox = this
  import spademeta._
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
  lazy val andTrees = ListBuffer[AndTree]()
  lazy val delays = ListBuffer[Delay[Bit]]()
}

class InnerCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0, s"$pne.doneXbar")
  val en = Delay(Bit(), 0, s"$pne.en")
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
}

class OuterCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:OuterComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0, s"$pne.doneXbar")
  val en = Delay(Bit(), 0, s"$pne.en")
  val childrenAndTree = AndTree("childrenAndTree") 
  val siblingAndTree = AndTree("siblingAndTree") 
  val pulserSM = PulserSM()
}

class MemoryCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:MemoryComputeUnit) extends CtrlBox(numUDCs) {
  val readDoneXbar = Delay(Bit(), 0)
  val writeDoneXbar = Delay(Bit(), 0)
  val tokenInXbar = Delay(Bit(), 0)
  val writeFifoAndTree = AndTree("writeFifoAndTree") 
  val readFifoAndTree = AndTree("readFifoAndTree") 
  val writeEn = Delay(Bit(), 0)
  val readEn = Delay(Bit(),0) 
}

case class TopCtrlBox()(implicit spade:Spade, override val pne:Top) extends CtrlBox(0) with Simulatable {
  val command = Output(Bit(), this, s"command")
  val status = Input(Bit(), this, s"status")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    command.v.set { v =>
      if (sim.rst) v.setHigh
      else v.setLow
      //if (sim.cycle == 1) v.setLow
      //else if (sim.cycle == 2) v.setHigh
      //else if (command.pv.isHigh.getOrElse(false)) v.setLow
      //sim.dprintln(s"#${sim.cycle} ${o} ${v.value.s}")
    }
  }
}

class MCCtrlBox()(implicit spade:Spade, override val pne:MemoryController) extends CtrlBox(0) {
  val rdone = Output(Bit(), this, s"${this}.rdone")
  val wdone = Output(Bit(), this, s"${this}.wdone")
}
