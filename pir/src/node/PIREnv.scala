package pir
package node

import prism.graph._
import spade.node._
import spade.param2._

class PIRStates extends States {
  var pirTop:Top = _
  var simulationCycle:Option[Long] = None
}
trait PIREnv extends Env with DefaultParamLoader { self =>

  override def newStates = new PIRStates
  override def states:PIRStates = super.states.asInstanceOf[PIRStates]

  def pirTop = states.pirTop
  def config:PIRConfig
  def getOpt[T](name:String):Option[T] = config.getOption[T](name)
  lazy val factory = new BaseFactory {
    override def states = self.states
  }
  lazy val spadeParam = topParam
  lazy val spadeTop = factory.create[spade.node.Top](topParam)

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

