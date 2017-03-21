package pir.plasticine.graph

import pir.graph._
import pir.plasticine.main._
import pir.plasticine.util._
import scala.collection.mutable.ListBuffer

import scala.language.reflectiveCalls

/* Spade Node */
class Node(implicit val spade:Spade) { 
  val spademeta: SpadeMetadata = spade
  import spademeta._
  val id : Int = spade.nextId
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }
  val typeStr = this.getClass().getSimpleName()
  override def toString = s"${typeStr}${id}" 
  def index(i:Int)(implicit spade:Spade):this.type = { indexOf(this) = i; this }
  def index(implicit spade:Spade):Int = { indexOf(this) }
  def coord(c:(Int, Int))(implicit spade:Spade):this.type = { coordOf(this) = c; this} // Coordinate
  def coord(implicit spade:Spade):(Int, Int) = { coordOf(this) }
  def buses(bus:IO[Bus, _])(implicit spade:Spade):this.type = { busesOf(this) += bus; this }
  def buses(implicit spade:Spade) = { busesOf(this) }
}

class Module(implicit spade:Spade) extends Node {
  val _ins = ListBuffer[Input[_<:PortType, Module]]()
  def ins = _ins.toList
  val _outs = ListBuffer[Output[_<:PortType, Module]]()
  def outs = _outs.toList
  def addIO(io:IO[_, Module]) = io match {
    case input:Input[_,_] => _ins += input.asInstanceOf[Input[_<:PortType, Module]]
    case output:Output[_,_] => _outs += output.asInstanceOf[Output[_<:PortType, Module]]
  }
  def ios = ins ++ outs
}
