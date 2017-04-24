package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

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
case class UDCounter()(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  override val typeStr = "udc"
  val inc = Input(Bit(), this, s"${this}.inc")
  val dec = Input(Bit(), this, s"${this}.dec")
  val out = Output(Bit(), this, s"${this}.out")
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, pne:NetworkElement):UDCounter = UDCounter().index(idx)
}

case class AndTree()(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  import spademeta._
  override val typeStr = "at"
  val out = Output(Bit(), this, s"${this}.out")
  private[plasticine] def <== (outs:List[Output[Bit, Module]]):Unit = outs.foreach { out => <==(out) }
  private[plasticine] def <== (out:Output[Bit, Module]):Unit = {
    val i = ins.size
    val in = Input(Bit(), this, s"${this}.in$i").index(i)
    in <== out
  }
  var name:String = super.toString
}
object AndTree {
  def apply(name:String)(implicit spade:Spade, pne:NetworkElement):AndTree = {
    val at = AndTree()
    at.name = name
    at
  }
}

case class PulserSM()(implicit spade:Spade, pne:NetworkElement) extends Primitive {
  val done = Input(Bit(), this, s"${this}.done")
  val en = Input(Bit(), this, s"${this}.en")
  val init = Input(Bit(), this, s"${this}.init")
  val out = Output(Bit(), this, s"${this}.out")
}

class CtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:Controller) extends Primitive {
  import spademeta._
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
}

class InnerCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0)
  val en = Delay(Bit(), 0)
  val tokenInXbar = Delay(Bit(), 0)
  val siblingAndTree = AndTree("siblingAndTree") 
  val fifoAndTree = AndTree("fifoAndTree")
  val tokenInAndTree = AndTree("tokenInAndTree")
  val andTree = AndTree()
  andTree <== tokenInAndTree.out
  andTree <== fifoAndTree.out
}

class OuterCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:OuterComputeUnit) extends CtrlBox(numUDCs) {
  val doneXbar = Delay(Bit(), 0)
  val en = Delay(Bit(), 0)
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
    //sim.dprintln(s"setting $command")
    command.v.set { v => 
      if (sim.cycle == 1)
        v.value.value = Some(false) 
      else if (sim.cycle == 2)
        v.value.value = Some(true) 
      else if (v.prevValue.value==Some(true))
        v.value.value = Some(false)
      //sim.dprintln(s"#${sim.cycle} ${o} ${v.value.s}")
    }
  }
}

class MCCtrlBox()(implicit spade:Spade, override val pne:MemoryController) extends CtrlBox(0) {
  val done = Output(Bit(), this, s"${this}.done")
}
