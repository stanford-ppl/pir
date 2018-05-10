package prism
package node

import scala.collection.mutable

trait Atom[N<:Node[N]] extends Node[N] { self:Atom[N] with N =>
  implicit lazy val atom:this.type = this

  def children:List[N] = Nil
  override def descendents:List[N] = Nil

  private lazy val _ins = mutable.ListBuffer[Input[N]]()
  private lazy val _outs = mutable.ListBuffer[Output[N]]()

  def addEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins += io
      case io:Output[N] => _outs += io
    }
  }
  def removeEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins -= io
      case io:Output[N] => _outs -= io
    }
  }
  def ins = _ins.toList
  def outs = _outs.toList
}

