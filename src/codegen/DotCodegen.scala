package pir.codegen

import pir.Design
import pir.plasticine.main._
import pir.util.typealias._
import scala.language.implicitConversions
import scala.collection.mutable.Map

class DotAttr() {
  val attrMap:Map[String, String] = Map.empty 
  val graphAttrMap:Map[String, String] = Map.empty 

  def + (rec:(String, String)):DotAttr = { attrMap += rec; this}

  def shape(s:Shape) = { attrMap += "shape" -> s.field; this }
  def color(s:Color) = { attrMap += "color" -> s.field; this }
  def fillcolor(s:Color) = { attrMap += "fillcolor" -> s.field; this }
  def labelfontcolor(s:Color) = { attrMap += "labelfontcolor" -> s.field; this }
  def style(ss:Style*) = { attrMap += "style" -> ss.map(_.field).mkString(","); this }
  def graphStyle(s:Style) = { graphAttrMap += "style" -> s"${s.field}"; this }
  def label(s:Any) = { attrMap += "label" -> s.toString; this }
  def label = { attrMap.get("label") }
  def dir(s:Direction) = { attrMap += "dir" -> s.field; this }
  def pos(coord:(Double,Double)) = { attrMap += "pos" -> s"${coord._1},${coord._2}!"; this }

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

trait DotEnum {
  val Mrecord   = Shape("Mrecord")
  val box       = Shape("box")
  val ellipse   = Shape("ellipse")
  val circle    = Shape("circle")

	val filled    = Style("filled")
  val bold      = Style("bold")
  val dashed    = Style("dashed")
  val rounded   = Style("rounded")
  val dotted    = Style("dotted")

	val white     = Color("white")
	val black     = Color("black")
	val lightgrey = Color("lightgrey")
  val hexagon   = Color("hexagon")
  val gold      = Color("gold")
  val limegreen = Color("limegreen")
  val blue      = Color("blue")
  val red       = Color("red")
  val indianred = Color("indianred1")
  val cyan      = Color("cyan4")

  val both = Direction("both")

  implicit def field_to_string(f:DotField):String = f.field
}

trait DotCodegen extends Printer with DotEnum {

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
  def emitEdge(from:Any, to:Any, label:String):Unit = {
    emitEdge(from, to, DotAttr().label(label))
  }
  def emitEdge(from:Any, to:Any, attr:DotAttr):Unit = {
    //attr.attrMap.get("label").foreach { label =>
      //attr.attrMap.remove("label")
      //attr.attrMap += "xlabel" -> label
    //}
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
  def quote(n:Any)(implicit design:Design) = {
    implicit val spade:Spade = design.arch
    n match {
      case pin:PI[_] =>
        pin.src match {
          case cu:PCU => s"""${pin.src}:${pin}:n"""
          case sb:PSB => s"""${pin.src}"""
          case _ => pin.src.toString
        }
      case pout:PO[_] =>
        pout.src match {
          case cu:PCU => s"""${pout.src}:${pout}:s"""
          case sb:PSB => s"""${pout.src}"""
          case _ => pout.src.toString
        }
      case n:Node => pir.util.quote(n)
      case n:PNode => pir.plasticine.util.quote(n)
      case _ => s"$n"
    } 
  }
}

