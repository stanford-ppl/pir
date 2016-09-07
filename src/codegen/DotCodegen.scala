package pir.codegen

import scala.language.implicitConversions
import scala.collection.mutable.Map

class DotAttr() {
  val attrMap:Map[String, String] = Map.empty 

  def shape(s:DotField) = { attrMap += "shape" -> s.field; this }
  def color(s:DotField) = { attrMap += "color" -> s.field; this }
  def style(s:DotField) = { attrMap += "style" -> s.field; this }
  def label(s:String) = { attrMap += "label" -> s; this }

  def + (rec:(String, String)):DotAttr = { attrMap += rec; this}

  def list = attrMap.map{case (k,v) => s"""${k}="${v}""""}.mkString(",")
}
object DotAttr {
  def apply() = new DotAttr()
}
trait DotField { val field:String }
case class Shape(field:String) extends DotField
case class Color(field:String) extends DotField
case class Style(field:String) extends DotField

trait DotEnum {
  val Mrecord   = Shape("Mrecord")
  val box       = Shape("box")
  val ellipse   = Shape("ellipse")
  val circle    = Shape("circle")
  val indianred = Shape("indianred1")

	val filled    = Style("filled")
  val bold      = Style("bold")
  val dashed    = Style("dashed")

	val white     = Color("white")
	val lightgrey = Color("lightgrey")
  val hexagon   = Color("hexagon")
  val gold      = Color("gold")
  val limegreen = Color("limegreen")
  val blue      = Color("blue")
  val red       = Color("red")

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
  def emitEdge(from:Any, to:Any, attr:DotAttr):Unit = {
    emitln(s"""${q(from)} -> ${q(to)} [${attr.list}]""")
  }
  def emitEdge(from:Any, to:Any):Unit = {
    emitln(s"""${q(from)} -> ${q(to)}""")
  }
  def emitEdge(from:Any, ffield:Any, to:Any, tfield:Any):Unit = {
    emitEdge(s"${from}:${ffield}", s"${to}:${tfield}")
  }
  def emitEdge(from:AnyVal, ffield:Any, fd:String, to:Any, tfield:Any, td:String):Unit = {
    emitEdge(s"${from}:${ffield}:${fd}", s"${to}:${tfield}:${td}")
  }

  def emitSubGraph(n:Any, label:Any)(block: =>Any):Unit = {
		emitSubGraph(n, label, filled, lightgrey)(block)
	}
  def emitSubGraph(n:Any, label:Any, style:String, color:String)(block: =>Any):Unit = {
		emitBlock(s"""subgraph cluster_${n}""") {
			emitln(s"""style=${style};""")
			emitln(s"""color=${color};""")
      emitln(s"""node [style=filled]""")
			emitln(s"""label = "${label}";""")
			block
		}
  }
}

