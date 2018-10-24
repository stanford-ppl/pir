package prism
package graph

import scala.collection.mutable._


abstract class State[T] {
  def value:T
  val key = this.getClass
  def initNode(n:Node[_], value:T):Unit
  def initNodeX(n:Node[_], value:Any):Unit = initNode(n, value.asInstanceOf[T])
}

trait BuildEnvironment extends Logging {
  implicit val env:BuildEnvironment = this

  var _id = -1
  def nextId = { _id += 1; _id }

  val stacks = Map[Class[_], Stack[State[_]]]()

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

  def initNode(n:Node[_]) = {
    stacks.foreach { case (key,stack) =>
      stack.headOption.foreach { head =>
        head.initNodeX(n, head.value)
      }
    }
  }
}
abstract class EnvNode[N](implicit env:BuildEnvironment) extends Node[N] { self:N =>
  override val id = env.nextId
}
