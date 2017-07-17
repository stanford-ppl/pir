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

/** Physical Counter  */
case class Counter()(implicit spade:Spade, prt:ComputeUnit) extends Primitive with Simulatable {
  import spademeta._
  override val typeStr = "ctr"
  //override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val min = Input(Word(), this, s"${this}.min")
  val max = Input(Word(), this, s"${this}.max")
  val step = Input(Word(), this, s"${this}.step")
  val out = Output(Bus(prt.param.numLanes, Word()), this, s"${this}.out")
  val en = Input(Bit(), this, s"${this}.en")
  val done = Output(Bit(), this, s"${this}.done")
  def isDep(c:Counter) = en.canConnect(c.done)

  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.mapping._
    ctmap.pmap.get(this).foreach { ctr =>
      val cu = ctr.ctrler
      val prevCtr = fimap(en).src match {
        case c:Counter => Some(c)
        case _ => None
      }
      val outPar = ctr.par 
      sim.dprintln(s"$this outPar = $outPar")
      val head = out.v.head.asSingle
      out.v.foreach { 
        case (v, i) if (i==0) =>
          done.pv
          en.pv
          head.set { headv =>
            Match(
              sim.rst -> { () => headv <<= min.v },
              (done.pv & prevCtr.fold(done.pv) { _.done.pv } ) -> { () => headv <<= min.v },
              en.pv -> { () => headv <<= headv + (step.v * outPar) }
            ) {}
          }
        case (v, i) if i < outPar =>
          v.asSingle := head + (step.v * i)
        case (v, i) =>
      }
      done.v.set { donev =>
        donev.setLow
        If (en.v & (head >= (max.v - (step.v * outPar)))) {
          donev.setHigh
        }
      }
    }
  }
}

