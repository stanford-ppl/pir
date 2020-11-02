package prism
package codegen

import prism.graph._

import scala.collection.mutable

class BasicIRDotGen[TN<:Node[TN]](override val dirName:String, val fileName:String, val top:TN) extends Pass()(null) with IRDotCodegen {
  type N = TN
}

trait IRDotCodegen extends DotCodegen with ChildFirstTraversal {

  val horizontal:Boolean = false
  def fileName:String
  override def codegenPostfix = dotFile

  val nodes = mutable.HashSet[N]()

  override def initPass = {
    super.initPass
    usePos = false
    emitBSln("digraph G")
    if (horizontal) emitln(s"rankdir=LR")
  }

  override def finPass = {
    emitEdges
    emitBEln
    super.finPass
  }

  def shape(attr:DotAttr, n:N) = attr.shape(box)

  def color(attr:DotAttr, n:N) = {
    if (n.children.nonEmpty) attr.setNode.fillcolor(white).style(dashed).setGraph.fillcolor(white).style(filled)
    else attr.setNode.fillcolor(white).style(filled)
  }

  def label(attr:DotAttr, n:N) = {
    var at = attr
    if (n.children.nonEmpty) at = at.setGraph.label(quote(n))
    at.setNode.label(quote(n))
  }

  def pos(attr:DotAttr, n:N) = {
    n.pos.value.fold(attr) { case (x,y) => 
      usePos = true
      attr.pos(x.toInt, y.toInt)
    }
  }

  def setAttrs(n:N):DotAttr = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    attr = pos(attr, n)
    attr
  }

  def emitSubGraph(n:N)(block: => Unit):Unit = {
    emitSubGraph(n, setAttrs(n)) { block }
  }

  def emitSingleNode(n:N, sub:String = ""):Unit = {
    emitNode(n,setAttrs(n),sub)
    nodes += n
  }

  override def emitNode(n:N) = {
    if (n.children.isEmpty) {
      emitSingleNode(n)
    } else {
      emitSubGraph(n) { 
        if (n.localEdges.exists { _.isConnected }) emitSingleNode(n)
        visitNode(n)
      }
    }
  }

  def emitEdges = { nodes.foreach(emitEdge) }

  def emitEdge(n:N):Unit = {
    (n+:(n.descendents.filter { d => !nodes.contains(d) })).foreach { d =>
      d.localIns.foreach { in =>
        in.connected.foreach { out => 
          if (!out.src.isDescendentOf(n)) emitEdge(out, in, DotAttr.empty, "", "")
        }
      }
    }
  }

  def emitEdge(from:EN[N], to:EN[N], attr:DotAttr, f_sub:String, t_sub:String):Unit = {
    emitEdgeMatched(from.src, to.src, attr, f_sub, t_sub) 
  }

  def lift(n:N) = {
    n.ancestorTree.foldLeft[Option[N]](None) {
      case (Some(matched), a) => Some(matched)
      case (None, a) if nodes.contains(a) => Some(a)
      case (None, a) => None
    }
  }

  def emitEdgeMatched(from:N, to:N, attr:DotAttr, f_sub:String, t_sub:String):Unit = {
    (lift(from), lift(to)) match {
      case (Some(from), Some(to)) => emitEdge(from, to, attr, f_sub, t_sub)
      case _ =>
    }
  }

  def emitEdge(from:N, to:N, attr:DotAttr, f_sub:String, t_sub:String):Unit = {
    super.emitEdge(from, to, attr, f_sub, t_sub) // String, String
  }

}
