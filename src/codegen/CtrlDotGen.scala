package pir.graph.traversal

import pir._
import pir.codegen.Printer
import pir.PIRMisc._
import pir.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import java.io.File

case class DotAttr() {
  val attrMap:Map[String, String] = Map.empty 
  def setShape(s:String) = { attrMap += "shape" -> s; this }
  def setColor(s:String) = { attrMap += "color" -> s; this }
  def setStyle(s:String) = { attrMap += "style" -> s; this }
  def setLabel(s:String) = { attrMap += "label" -> s; this }

  def + (rec:(String, String)):DotAttr = { attrMap += rec; this}

  def list = attrMap.map{case (k,v) => s"""${k}="${v}""""}.mkString(",")
}
object DotEnum {
  val Mrecord = "Mrecord"
  val box="box"
  val ellipse="ellipse"
  val circle = "circle"
  val indianred = "indianred1"

	val filled = "filled"
	val white = "white"
	val lightgrey = "lightgrey"
  val hexagon = "hexagon"
  val gold = "gold"
  val limegreen = "limegreen"
}
trait DotGen extends Printer {
  import DotEnum._

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
    emitEdge(s"${from}:${to}", s"${to}:${tfield}")
  }
  def emitEdge(from:AnyVal, ffield:Any, fd:String, to:Any, tfield:Any, td:String):Unit = {
    emitEdge(s"${from}:${to}:${fd}", s"${to}:${tfield}:${td}")
  }

  def emitSG(n:Any, label:Any)(block: =>Any):Unit = {
		emitSG(n, label, filled, lightgrey, filled, white)(block)
	}
  def emitSG(n:Any, label:Any, style:String, color:String, nstyle:String, ncolor:String)(block: =>Any):Unit = {
		emitBlock(s"""subgraph cluster_${n}""") {
			emitln(s"""style=${style};""")
			emitln(s"""color=${color};""")
      emitln(s"""node [style=$nstyle,color=$ncolor]""")
			emitln(s"""label = "${label}";""")
			block
		}
  }
}

class CtrlDotGen(implicit design: Design) extends Traversal with DotGen {
  import DotEnum._

  override val stream = newStream(Config.ctrlDot)

  override def initPass = {
    emitBSln("digraph G")
    //emitln(s"splines=ortho;")
  }

  def q(in:InPort) = in.src
  def q(out:OutPort) = out.src

  override def reset = { emittedEdges.clear; super.reset}
  val emittedEdges = Set[(OutPort, InPort)]() 
  def emitEdge(to:InPort):Unit = { if (to.isConnected) emitEdge(to.from, to) }
  def emitEdge(to:InPort, label:String):Unit = 
    if (to.isConnected) emitEdge(to.from, to, DotAttr().setLabel(label))
  def emitEdge(to:InPort, attr:DotAttr):Unit = 
    if (to.isConnected) emitEdge(to.from, to, attr)
  def emitEdge(from:OutPort, to:InPort):Unit = {
    if (!emittedEdges.contains((from, to))) {
      emitEdge(q(from), q(to))
      val t = (from, to)
      emittedEdges += t
    }
  }
  def emitEdge(from:OutPort, to:InPort, attr:DotAttr):Unit = {
    if (!emittedEdges.contains((from, to))) {
      emitEdge(q(from), q(to), attr)
      val t = (from, to)
      emittedEdges += t 
    }
  }

  override def traverse = {
    design.top.compUnits.foreach { cu =>
			emitSG(cu, cu) {
      	emitNode(cu, cu.ctrlBox, DotAttr().setShape(Mrecord))
        cu.ctrlBox.tokenBuffers.foreach{ case (dep, t) =>
          val label = s"{${t}|init=${t.initVal}|dep=${t.dep}}"
          emitNode(t, label, DotAttr().setShape(Mrecord).setColor(gold))
          emitEdge(t.inc, "inc")
          emitEdge(t.dec, "dec")
          emitEdge(t.init, "init")
        }
        cu.ctrlBox.creditBuffers.foreach { case (deped, c) =>
          val label = s"{${c}|init=${c.initVal}|deped=${c.deped}}"
          emitNode(c, label, DotAttr().setShape(Mrecord).setColor(limegreen))
          emitEdge(c.inc, "inc")
          emitEdge(c.dec, "dec")
        }
        cu.ctrlBox.luts.foreach { lut =>
          val label = s"{${lut}|${cu}|tf=${lut.info}}"
          emitNode(lut, label, DotAttr().setShape(Mrecord))
          lut match {
            case l:EnLUT =>
              lut.ins.foreach { in => emitEdge(in, "in") }
            case _ =>
          }
        }
        val cchain = cu match {
          case cu:InnerComputeUnit => cu.localCChain
          case cu:OuterComputeUnit => cu.inner.cchainMap(cu.localCChain)
        }
        cchain.counters.foreach { c =>
          emitNode(c, c, DotAttr().setShape(circle).setColor(indianred).setStyle(filled))
          if (c.en.isConnected) emitEdge(c.en, "en")
        }
        if (cu.ctrlBox.innerCtrEn.isConnected) {
          val attr = DotAttr().setColor("blue").setLabel("en")
          emitEdge(cu.ctrlBox.innerCtrEn, attr)
        }
			}
      emitEdge(cu.parent, cu, DotAttr().setStyle("bold"))
      cu.dependencies.foreach { dep => emitEdge(dep, cu, DotAttr().setStyle("dashed")) }
    }
    val command = design.top.command 
    command.to.foreach { to => emitEdge(to, "command") }
    val status = design.top.status
    emitEdge(status, "status")
  }

  override def finPass = {
    emitBEln
    close
    info(s"Finishing Ctrl Dot Printing in ${getPath}")
  }

}
