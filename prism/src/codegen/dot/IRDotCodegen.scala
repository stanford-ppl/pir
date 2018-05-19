package prism
package codegen

import prism.node._
import prism.traversal.GraphUtil

import scala.collection.mutable

trait IRDotCodegen extends Codegen with DotCodegen with GraphUtil {

  type N <: prism.node.Node[N]

  val horizontal:Boolean = false
  val fileName:String

  val nodes = mutable.ListBuffer[N]()

  override def initPass = {
    super.initPass
    emitBSln("digraph G")
    if (horizontal) emitln(s"rankdir=LR")
  }

  override def finPass = {
    emitEdges
    emitBEln
    super.finPass
  }

  def emitEdges = { nodes.foreach(emitEdge) }

  def shape(attr:DotAttr, n:Any) = attr.shape(box)

  def color(attr:DotAttr, n:Any) = attr.fillcolor(white).style(filled)

  def label(attr:DotAttr, n:Any) = attr.label(quote(n))

  def setAttrs(n:Any):DotAttr = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    attr
  }

  def emitSubGraph(n:N)(block: => Unit):Unit = {
    emitSubGraph(n, setAttrs(n)) { block }
  }

  def emitSingleNode(n:N):Unit = {
    emitNode(n,setAttrs(n))
    nodes += n
  }

  def emitSingleNode(n:Any):Unit = {
    emitNode(n,setAttrs(n))
  }

  override def emitNode(n:N) = {
    n match {
      case _:Atom[_] => emitSingleNode(n); super.visitNode(n) 
      case g:SubGraph[_] if g.children.isEmpty => emitSingleNode(n); super.visitNode(n) 
      case g:SubGraph[_] => emitSubGraph(n) { super.visitNode(n) }
    }
  }

  def emitEdge(n:N):Unit = {
    n.ins.foreach { in =>
      in.connected.foreach { out => emitEdge(out, in, DotAttr.empty) }
    }
  }

  def emitEdge(from:Output[N], to:Input[N], attr:DotAttr):Unit = {
    emitEdgeMatched(from.src.asInstanceOf[N], to.src, attr) 
  }

  def lift(n:N) = {
    (n::n.ancestors).foldLeft[Option[N]](None) {
      case (Some(matched), a) => Some(matched)
      case (None, a) if nodes.contains(a) => Some(a)
      case (None, a) => None
    }
  }

  def emitEdgeMatched(from:N, to:N, attr:DotAttr):Unit = {
    (lift(from), lift(to)) match {
      case (Some(from), Some(to)) => emitEdge(from, to, attr)
      case _ =>
    }
  }

  def emitEdge(from:N, to:N, attr:DotAttr):Unit = {
    super.emitEdge(from, to, attr) // String, String
  }

}
