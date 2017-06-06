package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class Primitive(implicit spade:Spade, val pne:NetworkElement) extends Module

class Const[P<:PortType](tp:P, value:Option[AnyVal])(implicit spade:Spade) extends Simulatable {
  override val typeStr = "const"
  val out:Output[P, this.type] = Output(tp.clone, this, s"$this.out")

  override def register(implicit sim:Simulator):Unit = {
    def assign(v:Value, value:AnyVal) = {
      (out.v, value) match {
        case (v:WordValue, value:Float) => v := value
        case (v:WordValue, value:Int) => v := value
        case (v:BitValue, value:Boolean) => v := value
        case (v, value) => err(s"Cannot create constant $value with type of $v")
      }
    }

    super.register
    value.foreach { value =>
      assign(out.v, value)
    }
    sim.mapping.pmmap.get(this).foreach { case c:pir.graph.Const[_] => 
      assign(out.v, c.value)
    }
  }
}
object Const {
  def apply()(implicit spade:Spade):Const[Word] = new Const(Word(), None)
  def apply(v:Boolean)(implicit spade:Spade):Const[Bit] = new Const(Bit(), Some(v))
  def apply(v:Int)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
  def apply(v:Float)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
}

case class Delay[P<:PortType](tp:P, delay:Int, ts:Option[String])(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  override val typeStr = ts.getOrElse("delay")
  val in = Input(tp, this, s"${this}_in(0)")
  val out = Output(tp.clone, this, s"${this}_out")
  override def register(implicit sim:Simulator):Unit = {
    super.register
    out.v := in.vAt(delay) 
  }
}
object Delay {
  def apply(tp:Bit, delay:Int,ts:Option[String])
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = {
    val d = new Delay(tp, delay, ts)(spade, pne)
    ctrlBox.delays += d
    d
  }
  def apply(tp:Bit, delay:Int,ts:String)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, delay, Some(ts))
  def apply(tp:Bit, delay:Int)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, delay, None)
}
