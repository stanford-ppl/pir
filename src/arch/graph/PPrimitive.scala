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

abstract class Primitive(implicit spade:Spade, val pne:NetworkElement) extends Module

class Const[P<:SingleType](tp:P, value:Option[AnyVal])(implicit spade:Spade) extends Simulatable {
  override val typeStr = "const"
  val out:Output[P, this.type] = Output(tp.clone, this, s"$this.out")

  override def register(implicit sim:Simulator):Unit = {
    super.register
    sim.mapping.pmmap.get(this).fold {
      out.v := value
    } { c => 
      c.value match {
        case v:Float => out.v := v
        case v:Int => out.v := v
        case v:Boolean => out.v := v
      }
    }
  }
}
object Const {
  def apply()(implicit spade:Spade):Const[Word] = new Const(Word(), None)
  def apply(v:Boolean)(implicit spade:Spade):Const[Bit] = new Const(Bit(), Some(v))
  def apply(v:Int)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
  def apply(v:Float)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
}

case class Delay[P<:PortType](tp:P, staticDelay:Option[Int], ts:Option[String])(implicit spade:Spade, pne:NetworkElement) extends Primitive with Simulatable {
  override val typeStr = ts.getOrElse("delay")
  val in = Input(tp, this, s"${this}_in(0)")
  val out = Output(tp.clone, this, s"${this}_out")
  def delay(mapping:PIRMap):Option[Int] = mapping.rtmap.get(this).orElse(staticDelay) 
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import sim.spade
    super.register
    delay(mapping).foreach { delay =>
      dprintln(s"${quote(this)}.delay=$delay")
      out.v := in.vAt(delay) 
    }
  }
}
object Delay {
  def apply(tp:Bit, delay:Int,ts:Option[String])
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = {
    val d = new Delay(tp, Some(delay), ts)(spade, pne)
    ctrlBox.delays += d
    d
  }
  def apply(tp:Bit,ts:String)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, None, Some(ts))
  def apply(tp:Bit, delay:Int,ts:String)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, Some(delay), Some(ts))
  def apply(tp:Bit, delay:Int)
    (implicit spade:Spade, pne:NetworkElement, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, Some(delay), None)

  def apply[P<:PortType](tp:P, delay:Int,ts:Option[String])
    (implicit spade:Spade, pne:NetworkElement):Delay[P] = { new Delay(tp, Some(delay), ts)(spade, pne) }
  def apply[P<:PortType](tp:P,ts:String)
    (implicit spade:Spade, pne:NetworkElement):Delay[P] = Delay(tp, None, Some(ts))
}
