package prism
package graph

import scala.collection.mutable

trait BuildEnvironment extends Logging {
  implicit val env:BuildEnvironment = this

  def initNode(n:Node[_]) = {
    initParent(n)
  }

  val parentStack = mutable.Stack[Node[_]]()
  def currentParent = parentStack.head
  def withParent[T](p:Node[_])(scope: => T) = dbgblk(s"withParent($p)"){
    parentStack.push(p)
    val res = scope
    parentStack.pop
    res
  }

  def initParent(n:Node[_]) = {
    parentStack.headOption.foreach { p =>
      n.setParent(p)
    }
  }

}
