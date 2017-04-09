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
  override val typeStr = "udlut"
  override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  //val init = Input(Word(), this, s"${this}.init")
  //val inc = Input(Word(), this, s"${this}.inc")
  //val dec = Input(Word(), this, s"${this}.dec")
  //val out = Output(Word(), this, s"${this}.out")
}
object UDCounter {
  def apply(idx:Int)(implicit spade:Spade, pne:NetworkElement):UDCounter = UDCounter().index(idx)
}

case class PulsesrSM()(implicit spade:Spade, pne:NetworkElement) {
}

class CtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:Controller) extends Primitive {
  import spademeta._
  val udcs = List.tabulate(numUDCs) { i => UDCounter(i) }
}

class InnerCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val done = Delay(Bit(), 0)
}
class OuterCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val done = Delay(Bit(), 0)
  //val puslerSM = PulserSM()
}
class MemoryCtrlBox(numUDCs:Int)(implicit spade:Spade, override val pne:ComputeUnit) extends CtrlBox(numUDCs) {
  val readDone = Delay(Bit(), 0)
  val writeDone = Delay(Bit(), 0)
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
