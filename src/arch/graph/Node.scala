package pir.spade.graph

import pir.graph._
import pir.spade.main._
import pir.spade.util._
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

  def isConst = this.isInstanceOf[Const[_]]
}

trait Module extends Node {
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
