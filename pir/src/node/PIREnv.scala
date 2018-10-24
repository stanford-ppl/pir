package pir
package node

import prism.graph._

trait PIREnv extends Env {
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
        case n:PIRNode => n.ctrl(value)
        case n => 
      }
    }
  }

}

