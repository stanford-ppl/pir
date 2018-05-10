package prism
package codegen

import scala.collection.mutable.Map
import sys.process._

trait DotCodegen extends Printer { self:Codegen =>

  val regex = "\\[[0-9]*\\]".r
  def q(s:Any) = regex.replaceAllIn(s.toString, "")

  def emitNode(n:Any, label:String) = {
    emitln(s"""${q(n)} [label="${q(label)}"];""")
  }
  def emitNode(n:Any, attr:DotAttr) = {
    attr.attrMap.get("label").foreach { label =>
      attr.attrMap += "label" -> q(label)
    }
    emitln(s"""${q(n)} [${attr.list}];""")
  }
  def emitNode(n:Any, label:Any, attr:DotAttr) = {
    emitln(s"""${q(n)} [label="${q(label)}" ${attr.list} ];""")
  }
  def emitEdge(from:Any, to:Any, attr:DotAttr):Unit = {
    emitln(s"""${q(from)} -> ${q(to)} ${if (attr.attrMap.size!=0) s"[${attr.list}]" else ""}""")
  }
  def emitEdge(from:Any, to:Any):Unit = {
    emitln(s"""${q(from)} -> ${q(to)}""")
  }
  def emitEdge(from:Any, ffield:Any, to:Any, tfield:Any):Unit = {
    emitEdge(s"${from}:${ffield}", s"${to}:${tfield}")
  }
  def emitEdge(from:Any, ffield:Any, to:Any, tfield:Any, attr:DotAttr):Unit = {
    emitEdge(s"${from}:${ffield}", s"${to}:${tfield}", attr)
  }
  def emitEdge(from:AnyVal, ffield:Any, fd:String, to:Any, tfield:Any, td:String):Unit = {
    emitEdge(s"${from}:${ffield}:${fd}", s"${to}:${tfield}:${td}")
  }

  def emitSubGraph(n:Any, label:Any)(block: =>Any):Unit = {
		emitSubGraph(n, DotAttr().label(label.toString))(block)
	}
  def emitSubGraph(n:Any, attr:DotAttr)(block: =>Any):Unit = {
		emitBlock(s"""subgraph cluster_${n}""") {
      emitln(attr.expand)
			block
		}
  }

  class DotAttr() {
    val attrMap:Map[String, String] = Map.empty 
    val graphAttrMap:Map[String, String] = Map.empty 
  
    def + (rec:(String, String)):DotAttr = { attrMap += rec; this}
  
    def shape(s:Shape) = { attrMap += "shape" -> s.field; this }
    def color(s:Color):this.type = color(s.field) 
    def color(s:String):this.type = { attrMap += "color" -> s; this }
    def fillcolor(s:Color):this.type = fillcolor(s.field) 
    def fillcolor(s:String):this.type = { attrMap += "fillcolor" -> s; this }
    def labelfontcolor(s:Color) = { attrMap += "labelfontcolor" -> s.field; this }
    def style(ss:Style*) = { attrMap += "style" -> ss.map(_.field).mkString(","); this }
    def graphStyle(s:Style) = { graphAttrMap += "style" -> s"${s.field}"; this }
    def label(s:Any) = { attrMap += "label" -> s.toString; this }
    def label = { attrMap.get("label") }
    def dir(s:Direction) = { attrMap += "dir" -> s.field; this }
    def pos(coord:(Double,Double)) = { attrMap += "pos" -> s"${coord._1},${coord._2}!"; this }
    def set(header:String, value:String) = { attrMap += header -> value; this }
  
    def elements:List[String] = {
      var elems = attrMap.map{case (k,v) => s"""$k="$v""""}.toList
      if (graphAttrMap.size!=0)
        elems = elems :+ s"graph[${graphAttrMap.map{case(k,v) => s"""$k="$v"""" }.mkString(",")}]"
      elems
    }
    def list = elements.mkString(",")
    def expand = elements.mkString(";") 
  }
  object DotAttr {
    def apply():DotAttr = new DotAttr()
    def empty:DotAttr = new DotAttr()
    def copy(attr:DotAttr):DotAttr = {
      val newAttr = DotAttr()
      attr.attrMap.foreach { e => newAttr + e }
      newAttr
    }
  }
  trait DotField { val field:String }
  case class Shape(field:String) extends DotField
  case class Color(field:String) extends DotField
  case class Style(field:String) extends DotField
  case class Direction(field:String) extends DotField
  
  val Mrecord   = Shape("Mrecord")
  val box       = Shape("box")
  val ellipse   = Shape("ellipse")
  val circle    = Shape("circle")
  val hexagon   = Shape("hexagon")
  
  val filled    = Style("filled")
  val bold      = Style("bold")
  val dashed    = Style("dashed")
  val rounded   = Style("rounded")
  val dotted    = Style("dotted")
  val solid     = Style("solid")
  
  val white     = Color("white")
  val black     = Color("black")
  val lightgrey = Color("lightgrey")
  val gold      = Color("gold")
  val limegreen = Color("limegreen")
  val blue      = Color("blue")
  val deepskyblue = Color("deepskyblue")
  val red       = Color("red")
  val indianred = Color("indianred1")
  val cyan      = Color("cyan")
  val brown      = Color("brown1")
  val orange = Color(s"darkorange")
  val chartreuse = Color(s"chartreuse3")
  val palevioletred = Color(s"palevioletred1")
  
  val both = Direction("both")
  
  implicit def field_to_string(f:DotField):String = f.field

  def open = {
    s"bin/dot -c ${outputPath} &".replace(".dot", "") !
  }
}

