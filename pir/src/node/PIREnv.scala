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
}
trait PIREnv extends Env { self =>

  override def newStates = new PIRStates
  override def states:PIRStates = super.states.asInstanceOf[PIRStates]

  def pirTop = states.pirTop
  def spadeParam = states.spadeParam
  def spadeTop = states.spadeTop
  def topMap = states.topMap
  def topMap_=(tmap:EOption[TopMap])= states.topMap_=(tmap)

  implicit class PIRParent(val value:PIRNode) extends State[PIRNode] {
    def initNode[N<:Node[N]](n:N, value:PIRNode):Unit = {
      n.to[PIRNode].foreach{ n =>
        n.setParent(value.as[PIRNode])
      }
    }
  }
  
  implicit class Ctrl(val value:ControlTree) extends State[ControlTree] {
    def initNode[N<:Node[N]](n:N, value:ControlTree):Unit = {
      n match {
        case n:ControlTree => 
          n.as[ControlTree].setParent(value) //TODO: why is this necessary
        case n:Memory => 
        case n:PIRNode => n.ctrl(value)
        case n => 
      }
    }
  }

}

