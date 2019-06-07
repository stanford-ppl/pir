package prism
package codegen

import scala.collection.mutable.Map

trait DotCodegen extends Codegen {

  override def dirName = buildPath(super.dirName, s"dot") 

  def dotFile:String = fileName.replace(".dot", ".html")
  lazy val dotPath = getAbsolutePath(buildPath(dirName, dotFile))

  val regex = "\\[[0-9]*\\]".r
  def q(s:Any) = regex.replaceAllIn(s.toString, "")

  def emitNode(n:Any, attr:DotAttr) = {
    val attrMap = attr.node
    attrMap.amap.transform { case ("label", v) => q(v); case (k,v) => v }
    emitln(s"""${q(n)} ${attrMap.list};""")
  }
  def emitNode(n:Any, label:String):Unit = emitNode(n, DotAttr().label(label))

  def emitEdge(from:Any, to:Any, attr:DotAttr):Unit = {
    val attrMap = attr.edge

    var fstr = q(from)
    attrMap.strip("ffield").foreach { field => fstr += s":$field" }
    attrMap.strip("fd").foreach { field => fstr += s":$field" }

    var tstr = q(to)
    attrMap.strip("tfield").foreach { field => fstr += s":$field" }
    attrMap.strip("td").foreach { field => fstr += s":$field" }

    emitln(s"""${q(fstr)} -> ${q(tstr)} ${if (attrMap.amap.size!=0) attrMap.list else ""}""")
  }

  def emitEdge(from:Any, to:Any):Unit = emitEdge(from, to, DotAttr.empty)

  def emitSubGraph(n:Any, label:Any)(block: =>Any):Unit = {
		emitSubGraph(n, DotAttr().setGraph.label(label.toString))(block)
	}

  def emitSubGraph(n:Any, attr:DotAttr)(block: =>Any):Unit = {
    val attrMap = attr.graph
    attrMap.amap.transform { case ("label", v) => q(v); case (k,v) => v }
		emitBlock(s"""subgraph cluster_${n}""") {
      emitln(attrMap.expand)
			block
		}
  }

  class DotAttrMap() {
    val amap:Map[String, String] = Map.empty 
  
    def elements:List[String] = {
      amap.map{case (k,v) => s"""$k="$v""""}.toList
    }
    def list = s"[${elements.mkString(",")}]"
    def expand = elements.mkString(";") 

    def strip(field:String):Option[String] = {
      val r = amap.get("field")
      amap -= "field"
      r
    }
  }

  case class DotAttr(node:DotAttrMap=new DotAttrMap, graph:DotAttrMap=new DotAttrMap, edge:DotAttrMap = new DotAttrMap) {
    var amap = node.amap
    def setNode = { amap = node.amap; this }
    def setGraph = { amap = graph.amap; this }
    def setEdge = { amap = edge.amap; this }

    def + (rec:(String, String)):DotAttr = { amap += rec; this}
    def shape(s:Shape) = { amap += "shape" -> s.field; this }
    def color(s:Color):this.type = color(s.field) 
    def color(s:String):this.type = { amap += "color" -> s; this }
    def fillcolor(s:Color):this.type = fillcolor(s.field) 
    def fillcolor(s:String):this.type = { amap += "fillcolor" -> s; this }
    def labelfontcolor(s:Color) = { amap += "labelfontcolor" -> s.field; this }
    def style(ss:Style*) = { amap += "style" -> ss.map(_.field).mkString(","); this }
    def label(s:Any) = { amap += "label" -> s.toString; this }
    def label = { amap.get("label") }
    def getLabel = label.get
    def dir(s:Direction) = { amap += "dir" -> s.field; this }
    def pos(coord:(Double,Double)) = { amap += "pos" -> s"${coord._1},${coord._2}!"; this }
    def url(link:String) = { amap += "URL" -> link; this }
    def attr(header:String, a:Any) = { amap += header -> a.toString; this }
  }

  object DotAttr {
    def apply():DotAttr = new DotAttr()
    def empty:DotAttr = new DotAttr()
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

  protected var usePos = false

  override def finPass = {
    super.finPass
    val flag = if (usePos) "-Kfdp -n" else ""
    val command = s"dot $flag -Tsvg -o $dotPath $outputPath"
    shell(command)
  }

  def open = {
    shell(s"open $dotPath")
  }
}

