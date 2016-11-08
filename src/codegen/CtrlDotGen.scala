package pir.graph.traversal

import pir._
import pir.codegen._
import pir.misc._
import pir.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import java.io.File
import scala.language.implicitConversions

class CtrlDotGen(implicit design: Design) extends Traversal with DotCodegen {

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
    if (to.isConnected) emitEdge(to.from, to, DotAttr().label(label))
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
    design.top.innerCUs.foreach { icu =>
      emitSubGraph(s"inner_$icu", DotAttr().label(icu)) {
        icu.mems.collect{ case mem:FIFOOnRead => mem; case mem:FIFOOnWrite => mem}.foreach { mem =>
          emitNode(mem, mem, DotAttr().shape(box).style(filled).fillcolor(cyan))
          mem match {
            case mem:FIFOOnRead => emitEdge(mem.dequeueEnable.from.src, mem, s"${mem.dequeueEnable}")
            case _ =>
          }
          mem match {
            case mem:FIFOOnWrite => emitEdge(mem.enqueueEnable.from.src, mem, s"${mem.enqueueEnable}")
            case _ =>
          }
        }
        icu.cchains.foreach { cchain =>
          emitSubGraph(cchain, DotAttr().label(cchain).color(black).style(dashed)) {
            cchain.counters.foreach { c =>
              emitNode(c, c, DotAttr().shape(circle).color(indianred).style(filled))
              if (c.en.isConnected) emitEdge(c.en, "en")
            }
          }
        }
        icu.locals.foreach { cu =>
          /* Emit nodes in cluster */
			    emitSubGraph(cu, DotAttr().label(cu).style(filled).color(lightgrey)) {
          	emitNode(cu, cu.ctrlBox, DotAttr().shape(Mrecord).style(filled).fillcolor(white))
            cu.ctrlBox.tokenBuffers.foreach{ case (dep, t) =>
              val label = s"{${t}|init=${t.initVal}|dep=${t.dep}}"
              emitNode(t, label, DotAttr().shape(Mrecord).style(filled).fillcolor(gold))
            }
            cu.ctrlBox.creditBuffers.foreach { case (deped, c) =>
              val label = s"{${c}|init=${c.initVal}|deped=${c.deped}}"
              emitNode(c, label, DotAttr().shape(Mrecord).style(filled).fillcolor(limegreen))
            }
            cu.ctrlBox.luts.foreach { lut =>
              val label = s"{${lut}|tf=${lut.transFunc.info}}"
              emitNode(lut, label, DotAttr().shape(Mrecord).style(filled).fillcolor(white))
            }
			    }
        }
      }
    }
    design.top.compUnits.foreach { cu =>
      /* Emit edges */
      cu.ctrlBox.tokenBuffers.foreach{ case (dep, t) =>
        emitEdge(t.inc, "inc")
        emitEdge(t.dec, "dec")
        emitEdge(t.init, "init")
      }
      cu.ctrlBox.creditBuffers.foreach { case (deped, c) =>
        emitEdge(c.inc, "inc")
        emitEdge(c.dec, "dec")
      }
      cu.ctrlBox.luts.foreach { lut =>
        lut.ins.flatMap { in =>
          in.from.src match {
            case at:AndTree => cu.ctrlBox.fifoAndTree.ins ++ cu.ctrlBox.tokInAndTree.ins
            case _ => in::Nil
          }
        }.foreach { in => emitEdge(in.from.src, lut, s"${in.from}") }
      }
      //emitEdge(cu.parent, cu, DotAttr().style(bold).color(red))
      cu.dependencies.foreach { dep => emitEdge(dep, cu, DotAttr().style(dashed)) }
    }
    emitNode(design.top, design.top)
    val command = design.top.command 
    command.to.foreach { to => emitEdge(to, "command") }
    //TODO: Somehow dot fails if add this edge
    //val status = design.top.status
    //emitEdge(status, "status")
  }

  override def finPass = {
    emitBEln
    close
    info(s"Finishing Ctrl Dot Printing in ${getPath}")
  }

}
