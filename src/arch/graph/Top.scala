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

case class TopParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16
) extends ControllerParam {
}

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(override val param:TopParam=new TopParam())(implicit spade:Spade) extends Controller(param) { self =>
  import spademeta._
  import param._

  lazy val ctrlBox:TopCtrlBox = TopCtrlBox()
  override def register(implicit sim:Simulator):Unit = {
    import sim.pirmeta._
    import sim.util._
    souts.foreach { psout =>
      vomap.pmap.get(psout).foreach { case sout:pir.graph.ScalarOut =>
        boundOf.get(sout.scalar) match {
          case Some(b:Int) => 
            psout.ic.v.head.asSingle := b
            psout.ic.v.valid := true
          case Some(b:Float) => 
            psout.ic.v.head.asSingle := b
            psout.ic.v.valid := true
          case None => warn(s"${sout.scalar} doesn't have a bound")
          case b => err(s"Don't know how to simulate bound:$b of ${sout.scalar}")
        }
      }
    }
    super.register
  }
  def config:Unit = {}
}
