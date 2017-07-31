package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._
import pir.mapper.PIRMap

import scala.language.reflectiveCalls
import scala.reflect.runtime.universe.{SingleType =>_, _}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set

abstract class Primitive(implicit spade:Spade, val prt:Routable) extends Module

class Const[P<:SingleType](tp:P, value:Option[AnyVal])(implicit spade:Spade) extends Simulatable {
  override val typeStr = "const"
  val out:Output[P, this.type] = Output(tp.clone, this, s"$this.out")

  override def register(implicit sim:Simulator):Unit = {
    sim.mapping.pmmap.get(this).fold {
      out.v := value
      value.foreach { out.v.default = _ }
    } { c => 
      c.value match {
        case v:Float => out.v := v
        case v:Int => out.v := v
        case v:Boolean => out.v := v
      }
      out.v.default = c.value
    }
  }
}
object Const {
  def apply()(implicit spade:Spade):Const[Word] = new Const(Word(), None)
  def apply(v:Boolean)(implicit spade:Spade):Const[Bit] = new Const(Bit(), Some(v))
  def apply(v:Int)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
  def apply(v:Float)(implicit spade:Spade):Const[Word] = new Const(Word(), Some(v))
}

class Delay[P<:PortType](tp:P, staticDelay:Option[Int], ts:Option[String])(implicit spade:Spade, prt:Routable, ev:TypeTag[P]) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = ts.getOrElse("delay")
  val in = Input(tp, this, s"${this}_in(0)")
  val out = Output(tp.clone, this, s"${this}_out")
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    import sim.spade
    delayOf.get(this).orElse(staticDelay).foreach { delay =>
      out.v := in.vAt(delay) 
    }
  }
}
object Delay {
  def apply(tp:Bit, staticDelay:Option[Int], ts:Option[String])
    (implicit spade:Spade, prt:Routable, ctrlBox:CtrlBox):Delay[Bit] = {
    val d = new Delay(tp, staticDelay, ts)(spade, prt, typeTag[Bit])
    ctrlBox.delays += d
    d
  }
  def apply(tp:Bit,ts:String)
    (implicit spade:Spade, prt:Routable, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, None, Some(ts))
  def apply(tp:Bit, delay:Int,ts:String)
    (implicit spade:Spade, prt:Routable, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, Some(delay), Some(ts))
  def apply(tp:Bit, delay:Int)
    (implicit spade:Spade, prt:Routable, ctrlBox:CtrlBox):Delay[Bit] = Delay(tp, Some(delay), None)

  def apply[P<:PortType](tp:P, delay:Int,ts:Option[String])
    (implicit spade:Spade, prt:Routable, ev:TypeTag[P]):Delay[P] = new Delay(tp, Some(delay), ts)
  def apply[P<:PortType](tp:P,ts:String)
    (implicit spade:Spade, prt:Routable, ev:TypeTag[P]):Delay[P] = new Delay(tp, None, Some(ts))
}
case class Mux[P<:PortType](name:Option[String], tp:P)(implicit spade:Spade, override val prt:Controller) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = name.getOrElse("mux")
  val sel = Input(Word(), this, s"${this}.sel")
  val out = Output(tp.clone, this, s"${this}.out")
  val _inputs = ListBuffer[Input[P, Module]]()
  def inputs = _inputs.toList 
  def addInput:Input[P, Mux[P]] = { val i = inputs.size; val in = Input(tp.clone, this, s"${this}.in$i").index(i); _inputs += in; in }
  private[plasticine] def <== (outs:List[Output[P, Module]]):Unit = outs.foreach { out => <==(out) }
  private[plasticine] def <== (out:Output[P, Module]):Unit = {
    addInput <== out
  }

  override def register(implicit sim:Simulator):Unit = {
    sel.v.default = 0
    //out.v := inputs(sel.v.toInt).v //TODO: support this
    out.v.set { v => v <<= inputs(sel.v.update.toInt).v.update }
  }
}
object Mux {
  def apply[P<:PortType](name:String, tp:P)(implicit spade:Spade, prt:Controller):Mux[P] = Mux(Some(name), tp)
  def apply[P<:PortType](tp:P)(implicit spade:Spade, prt:Controller):Mux[P] = Mux(None, tp)
}
