package prism.codegen

import pirc._
import pirc.util._
import prism.node._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

trait IRDotCodegen extends Codegen with DotCodegen {

  type N <: prism.node.Node[N]

  val horizontal:Boolean = false
  def shouldRun = Config.debug
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

  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  def shape(attr:DotAttr, n:Any) = attr.shape(box)

  def color(attr:DotAttr, n:Any) = attr.fillcolor(white).style(filled)

  def label(attr:DotAttr, n:Any) = attr.label(quote(n))

  def emitSubGraph(n:N)(block: => Unit):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) { block }
  }

  def emitSingleNode(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
    nodes += n
  }

  def emitSingleNode(n:Any):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
  }

  override def emitNode(n:N) = {
    n match {
      case _:Atom[_] => emitSingleNode(n); super.visitNode(n) 
      case g:SubGraph[_] if g.children.isEmpty => emitSingleNode(n); super.visitNode(n) 
      case g:SubGraph[_] => emitSubGraph(n) { super.visitNode(n) }
    }
  }

  def matchLevel(n:N):Option[N] = {
    (n::n.ancestors).foreach { n => if (nodes.contains(n)) return Some(n) }
    return None
  }

  def emitEdge(n:N):Unit = {
    n.ins.foreach { 
      case in if in.isConnected =>
        in.connected.foreach { out => emitEdge(out.src.asInstanceOf[N], n) }
      case in =>
    }
  }

  def emitEdge(from:N, to:N):Unit = {
    matchLevel(from).foreach { from => super.emitEdge(from, to) }
  }

}
