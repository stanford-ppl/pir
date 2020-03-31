package pir
package node

import prism.graph._
import spade.node._
import spade.param._
import pir.mapper._

class PIRStates extends States {
  var pirTop:pir.node.Top = _
  var spadeTop:spade.node.Top = _
  var spadeParam:spade.param.TopParam = _
  var simulationCycle:Option[Long] = None
  var topMap:EOption[TopMap] = _
  var liveNodes:Set[ND] = Set.empty
}
trait PIREnv extends Env { self =>
  implicit override val env:PIREnv = this

  override def newStates = new PIRStates
  override def states:PIRStates = super.states.asInstanceOf[PIRStates]

  def pirTop = states.pirTop
  def spadeParam = states.spadeParam
  def spadeTop = states.spadeTop
  def topMap = states.topMap
  def topMap_=(tmap:EOption[TopMap])= states.topMap_=(tmap)

  implicit class PIRParent(val value:PIRNode) extends State[PIRNode] {
    def initNode[N<:Node[N]](n:Node[N], value:PIRNode):Unit = {
      n.to[PIRNode].foreach{ _.setParent(value) }
    }
  }
  
  implicit class Ctrl(val value:ControlTree) extends State[ControlTree] {
    def initNode[N<:Node[N]](n:Node[N], value:ControlTree):Unit = {
      n match {
        case n:ControlTree => 
          n.setParent(value) //TODO: why is this necessary
        case n:Memory => 
        case n:PIRNode => n.ctrl(value)
        case n => 
      }
    }
  }

  implicit class SrcCtx(val value:Option[String]) extends State[Option[String]] {
    def initNode[N<:Node[N]](n:Node[N], value:Option[String]):Unit = {
      value.foreach { value =>
        n match {
          case n:ControlTree => n.srcCtx(value)
          case n:PIRNode => n.srcCtx(value)
          case n => 
        }
      }
    }
  }

}

