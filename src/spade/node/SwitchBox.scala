package pir.spade.node

import pirc.enums._
import pirc.util._
import pir.spade.main._
import pir.spade.network._
import pir.spade.simulation._
import pir.spade.util._
import pirc.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set


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

