package pir.plasticine.graph

import pir.util.enums._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.config.ConfigFactory
import pir.plasticine.simulation._
import pir.plasticine.util._
import pir.exceptions._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.Set


/* Switch box (6 inputs 6 outputs) */
case class SwitchBox()(implicit spade:SwitchNetwork) extends NetworkElement {
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
    super.register
    (souts ++ vouts ++ couts).foreach { out =>
      fimap.get(out.ic).foreach { out.ic :== _ }
    }
  }
}

