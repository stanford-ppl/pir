package spade.node

import spade._
import spade.util._

import scala.collection.mutable.ListBuffer

trait Module extends Node with Configurable {
  type CT <: Configuration

  val _ins = ListBuffer[Input[_<:PortType, Module]]()
  def ins:List[Input[_<:PortType, Module]] = _ins.toList
  val _outs = ListBuffer[Output[_<:PortType, Module]]()
  def outs:List[Output[_<:PortType, Module]] = _outs.toList
  def addIO(io:IO[_, Module]) = io match {
    case input:Input[_,_] => _ins += input.asInstanceOf[Input[_<:PortType, Module]]
    case output:Output[_,_] => _outs += output.asInstanceOf[Output[_<:PortType, Module]]
  }
  def ios:List[IO[_<:PortType, Module]] = ins ++ outs
}
