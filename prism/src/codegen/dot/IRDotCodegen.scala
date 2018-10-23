package prism
package codegen

import prism.graph._

import scala.collection.mutable

trait IRDotCodegen extends DotCodegen with ChildFirstTraversal {

  val horizontal:Boolean = false
  def fileName:String
  def dotFile:String = fileName.replace(".dot", ".html")
  lazy val dotPath = buildPath(dirName, dotFile)

  val nodes = mutable.ListBuffer[N]()

  private var usePos = false

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
    val flag = if (usePos) "-Kfdp -n" else ""
    val command = s"dot $flag -Tsvg -o $dotPath $outputPath"
    shell(command)
  }

  override def codegenInfo = {
    super.codegenInfo
    info(s"Generate $dotPath")
  }

  def shape(attr:DotAttr, n:N) = attr.shape(box)

  def color(attr:DotAttr, n:N) = {
    if (n.children.nonEmpty) attr.fillcolor(white).style(dashed)
    else attr.fillcolor(white).style(filled)
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

  def emitSingleNode(n:N):Unit = {
    emitNode(n,setAttrs(n))
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
    n.localIns.foreach { in =>
      in.connected.foreach { out => emitEdge(out, in, DotAttr.empty) }
    }
  }

  def emitEdge(from:E, to:E, attr:DotAttr):Unit = {
    emitEdgeMatched(from.src, to.src, attr) 
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
