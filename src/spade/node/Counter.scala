package spade.node

import spade._
import spade.util._

/** Physical Counter  */
case class CounterConfig (
  par:Int
) extends Configuration

case class Counter()(implicit spade:Spade, prt:ComputeUnit) extends Primitive with Simulatable 
with Configurable {
  import spademeta._
  override val typeStr = "ctr"
  type CT = CounterConfig
  //override def toString =s"${super.toString}${indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  val min = Input(Word(), this, s"$this.min")
  val max = Input(Word(), this, s"$this.max")
  val step = Input(Word(), this, s"$this.step")
  val out = Output(Bus(prt.param.numLanes, Word()), this, s"$this.out")
  val en = Input(Bit(), this, s"$this.en")
  val done = Output(Bit(), this, s"$this.done")
  def isDep(c:Counter) = en.canConnect(c.done)

  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    cfmap.get(this).foreach { cf =>
      val prevCtr = fimap(en).src match {
        case c:Counter => Some(c)
        case _ => None
      }
      val ctrPar = cf.par 
      sim.dprintln(s"$this ctrPar = $ctrPar")
      val head = out.v.head.asSingle
      out.v.foreach { 
        case (v, i) if (i==0) =>
          done.pv
          en.pv
          head.set { headv =>
            Match(
              sim.rst -> { () => headv <<= min.v },
              (done.pv & prevCtr.fold(done.pv) { _.done.pv } ) -> { () => headv <<= min.v },
              en.pv -> { () => headv <<= headv + (step.v * ctrPar) }
            ) {}
          }
        case (v, i) if i < ctrPar =>
          v.asSingle := head + (step.v * i)
        case (v, i) =>
          v.asSingle := head + (step.v * (ctrPar-1))
      }
      done.v.set { donev =>
        donev.setLow
        If (en.v & (head >= (max.v - (step.v * ctrPar)))) {
          donev.setHigh
        }
      }
    }
  }
}

