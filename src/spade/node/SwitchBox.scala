package spade.node

import spade._
import spade.network._
import spade.simulation._
import spade.util._

import scala.language.reflectiveCalls


/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:SwitchNetwork) extends Routable {
  import spademeta._
  override val typeStr = "sb"
  val scalarIO:ScalarIO[this.type] = ScalarIO(this)
  val vectorIO:VectorIO[this.type] = VectorIO(this)
  val ctrlIO:ControlIO[this.type] = ControlIO(this)
  def connectXbar[P<:PortType](gio:GridIO[P, this.type]) = {
    gio.ins.foreach { in => gio.outs.foreach { out => out.ic <== in.ic } }
  }
  def connectXbars = {
    connectXbar(scalarIO)
    connectXbar(vectorIO)
    connectXbar(ctrlIO)
  }
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    (souts ++ vouts ++ couts).foreach { out =>
      fimap.get(out.ic).foreach { inic => 
        out.ic :== inic
      }
    }
    super.register
  }
}

