package pir
package node

import prism.graph._

class PIRStates extends States {
  var pirTop:Top = _
  var spadeTop:spade.node.Top = _
  var spadeParam:spade.param2.TopParam = _
}
trait PIREnv extends Env {

  override def newStates = new PIRStates
  override def states:PIRStates = super.states.asInstanceOf[PIRStates]

  def pirTop = states.pirTop
  def spadeTop = states.spadeTop
  def spadeParam = states.spadeParam

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

