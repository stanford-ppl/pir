package prism
package graph

import scala.collection.mutable._


abstract class State[T] extends Serializable {
  def value:T
  val key = this.getClass
  def initNode[N<:Node[N]](n:Node[N], value:T):Unit
  def initNodeX[N<:Node[N]](n:Node[N], value:Any):Unit = initNode(n, value.asInstanceOf[T])
}

class States extends Serializable {
  var id = -1
  val stacks = Map[Class[_], Stack[State[_]]]()
}

trait BuildEnvironment extends Logging {
  implicit val env:BuildEnvironment = this

  var _states:Option[States] = None
  def newStates:States = new States
  def createNewState = {
    info(s"Creating new states ...")
    val s = newStates
    _states = Some(s)
  }
  def states:States = _states.get
  def stacks = states.stacks

  def nextId = { states.id += 1; states.id }
  def stackTop[T:ClassTag] = stacks(classTag[T].runtimeClass).headOption.map(_.value)

  def within[T](xs:State[_]*)(block: => T):T = {
    xs.foreach(beginState)
    val res = block
    xs.foreach(endState)
    res
  }

  def beginState(x:State[_]) = {
    stacks.getOrElseUpdate(x.key, Stack.empty[State[_]]).push(x)
  }

  def endState(x:State[_]) = stacks(x.key).pop

  def endState[T:ClassTag] = stacks(classTag[T].runtimeClass).pop

  def initNode[N<:Node[N]](n:Node[N]) = {
    stacks.foreach { case (key,stack) =>
      stack.headOption.foreach { head =>
        head.initNodeX(n, head.value)
      }
    }
  }
}
abstract class EnvNode[N<:Node[N]](implicit env:BuildEnvironment) extends Node[N] { self:N =>
  override val id = env.nextId
}
