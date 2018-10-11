package prism
package node

import prism.traversal._

import scala.collection.mutable

abstract class Node[N<:Node[N]:ClassTag](implicit design:Design) extends IR with GraphCollector[N] { self:N =>

  //design.updateState(this)

  val nct:ClassTag[N] = implicitly[ClassTag[N]]

  // Parent
  var _parent:Option[N] = None
  def parent:Option[N] = _parent
  def setParent(p:N):this.type =  {
    assert(p != this, s"setting parent of $this to be the same as it self!")
    _parent match {
      case Some(`p`) => this
      case Some(parent) => err(s"Resetting parent of $this from $parent to $p"); this
      case None =>
        _parent = Some(p)
        p.addChild(this)
        this
    }
  }
  def unsetParent = {
    parent.foreach { p =>
      _parent = None
      p.removeChild(this)
    }
  }
  def isParentOf(m:N) = m.parent == Some(this)

  // Children
  def children:List[N]
  def addChild(c:N):Unit
  def removeChild(c:N):Unit
  def isChildOf(p:N) = p.children.contains(this)

  def siblings:List[N] = parent.map { _.children.filterNot { _ == this} }.getOrElse(Nil)
  def ancestors:List[N] = parent.toList.flatMap { parent => parent :: parent.ancestors }
  def isAncestorOf(n:N) = n.ancestors.contains(this) 
  // Inclusive
  def ancestorSlice(top:N) = { // from this to top inclusive
    val chain = this :: this.ancestors
    val idx = chain.indexOf(top)
    assert(idx != -1, s"$top is not ancestor of the $this")
    chain.slice(0, idx+1)
  }
  def descendents:List[N] = children.flatMap { child => child :: child.descendents }
  def isDescendentOf(p:N) = p.isAncestorOf(this)

  def ins:List[Input[N]]
  def outs:List[Output[N]]
  def ios:List[Edge[N]] = ins ++ outs

  def matchLevel(n:N) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption

  def deps:Set[N] = {
    ins.flatMap { _.connected.map { _.src.asInstanceOf[N] } }.toSet
  }
  def localDeps = deps.flatMap(matchLevel)
  def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  def depeds = outs.flatMap { _.connected.map { _.src } }.toSet
  def localDepeds = depeds.flatMap(matchLevel)
  def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }
  def neighbors = deps ++ depeds

}

