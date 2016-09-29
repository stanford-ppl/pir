package pir.codegen

import scala.language.implicitConversions
import scala.collection.mutable.Map

class DotAttr() {
  val attrMap:Map[String, String] = Map.empty 

  def + (rec:(String, String)):DotAttr = { attrMap += rec; this}

  def shape(s:Shape) = { attrMap += "shape" -> s.field; this }
  def color(s:Color) = { attrMap += "color" -> s.field; this }
  def fillcolor(s:Color) = { attrMap += "fillcolor" -> s.field; this }
  def labelfontcolor(s:Color) = { attrMap += "labelfontcolor" -> s.field; this }
  def style(s:Style) = { attrMap += "style" -> s.field; this }
  def label(s:Any) = { attrMap += "label" -> s.toString; this }
  def label = { attrMap.get("label") }
  def dir(s:Direction) = { attrMap += "dir" -> s.field; this }
  def pos(coord:(Int,Int)) = { attrMap += "pos" -> s"${coord._1},${coord._2}!"; this }

  def list = attrMap.map{case (k,v) => s"""${k}="${v}""""}.mkString(",")
  def expand = attrMap.map{case (k,v) => s"""${k}="${v}""""}.mkString(";\n")
}
object DotAttr {
  def apply() = new DotAttr()
}
trait DotField { val field:String }
case class Shape(field:String) extends DotField
case class Color(field:String) extends DotField
case class Style(field:String) extends DotField
case class Direction(field:String) extends DotField

trait DotEnum {
  val Mrecord   = Shape("Mrecord")
  val box       = Shape("box")
  val ellipse   = Shape("ellipse")
  val circle    = Shape("circle")

	val filled    = Style("filled")
  val bold      = Style("bold")
  val dashed    = Style("dashed")
  val rounded   = Style("rounded")

	val white     = Color("white")
	val lightgrey = Color("lightgrey")
  val hexagon   = Color("hexagon")
  val gold      = Color("gold")
  val limegreen = Color("limegreen")
  val blue      = Color("blue")
  val red       = Color("red")
  val indianred = Color("indianred1")

  val both = Direction("both")

  implicit def field_to_string(f:DotField):String = f.field
}

trait DotCodegen extends Printer with DotEnum {

  val regex = "\\[[0-9]*\\]".r
  def q(s:Any) = regex.replaceAllIn(s.toString, "")

  def emitNode(n:Any, label:Any) = {
    emitln(s"""${q(n)} [label="${q(label)}"];""")
  }
  def emitNode(n:Any, label:Any, attr:DotAttr) = {
    emitln(s"""${q(n)} [label="${q(label)}" ${attr.list} ];""")
  }
  def emitEdge(from:Any, to:Any, label:String):Unit = {
    emitln(s"""${q(from)} -> ${q(to)} [label=${label}]""")
  }
  def emitEdge(from:Any, to:Any, attr:DotAttr):Unit = {
    emitln(s"""${q(from)} -> ${q(to)} [${attr.list}]""")
  }
  def emitEdge(from:Any, to:Any):Unit = {
    emitln(s"here?")
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
      //emitln(s"""${attr.expand}""")
			//emitln(s"""style=${filled};""")
			//emitln(s"""color=${lightgrey};""")
      //emitln(s"""node [style=filled]""")
			//emitln(s"""label = "${label}";""")
      emitln(attr.expand)
			block
		}
  }
}

