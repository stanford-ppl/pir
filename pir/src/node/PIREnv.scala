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
    def initNode(n:Node[_], value:PIRNode) = {
      n match {
        case n:PIRNode => n.setParent(value)
        case _ =>
      }
    }
  }
  
  implicit class Ctrl(val value:ControlTree) extends State[ControlTree] {
    def initNode(n:Node[_], value:ControlTree) = {
      n match {
        case n:ControlTree => n.setParent(value)
        case n:Memory => 
        case n:PIRNode => n.ctrl(value)
        case n => 
      }
    }
  }

}

