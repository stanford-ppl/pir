package spade.node

import spade._
import spade.util._

import pirc.util._

case class TopParam (
  sbufSize:Int = 16,
  vbufSize:Int = 16
) extends ControllerParam {
}

case class TopConfig (
  bounds:Map[GlobalOutput[_<:PortType, Module], Option[AnyVal]],
  override val outputValid:Map[GlobalOutput[_<:PortType, Module], Output[_<:PortType, Module]]
) extends ControllerConfig(outputValid)

/* Top-level controller (host)
 * @param argIns argument inputs. scalar inputs to the accelerator
 * @param argOuts argument outputs. scalar outputs to the accelerator
 * @param argInBuses output buses argIns are mapped to
 * @param argOutBuses input buses argOuts are mapped to
 * */
case class Top(override val param:TopParam=new TopParam())(implicit spade:Spade) extends Controller(param) with Configurable { self =>

  type CT = TopConfig
  import spademeta._
  import param._

  lazy val ctrlBox:TopCtrlBox = TopCtrlBox()
  override def register(implicit sim:Simulator):Unit = {
    import sim.util._
    val bounds = cfmap(this).bounds
    souts.foreach { psout =>
      bounds.get(psout).foreach { _ match {
        case Some(b:Int) => 
          psout.ic.v.head.asSingle := b
          psout.ic.v.valid := validOf(psout).v
        case Some(b:Float) => 
          psout.ic.v.head.asSingle := b
          psout.ic.v.valid := validOf(psout).v
        case None => warn(s"$psout doesn't have a bound")
        case b => err(s"Don't know how to simulate bound:$b of $psout")
      } }
    }
    super.register
  }
  def config:Unit = {}
}
