package pir.codegen

import pir._
import pir.node._
import pirc.util._

import scala.collection.mutable.Set

class CtrlDotGen(implicit design: PIR) extends Codegen with DotCodegen {
  def shouldRun = PIRConfig.debug && PIRConfig.ctrl

  override lazy val stream = newStream(PIRConfig.ctrlDot)

  override def initPass = {
    emitBSln("dinode G")
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

  addPass {
    design.top.innerCUs.foreach { icl =>
      emitSubGraph(s"inner_$icl", DotAttr().label(icl)) {
        icl match {
          case icu:InnerController =>
            icu.fifos.foreach { mem =>
              emitNode(mem, mem, DotAttr().shape(box).style(filled).fillcolor(cyan))
              emitEdge(mem.dequeueEnable.from.src, mem, s"${mem.dequeueEnable}")
              emitEdge(mem.enqueueEnable.from.src, mem, s"${mem.enqueueEnable}")
            }
        }
        icl.cchains.foreach { cchain =>
          emitSubGraph(cchain, DotAttr().label(cchain).color(black).style(dashed)) {
            cchain.counters.foreach { c =>
              emitNode(c, c, DotAttr().shape(circle).color(indianred).style(filled))
              if (c.en.isConnected) emitEdge(c.en, "en")
            }
          }
        }
        icl.locals.foreach { cu =>
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
            //cu.ctrlBox.luts.foreach { lut =>
              //val label = s"{${lut}|tf=${lut.transFunc.info}}"
              //emitNode(lut, label, DotAttr().shape(Mrecord).style(filled).fillcolor(white))
            //}
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
      cu.ctrlBox.andTrees.foreach { at =>
        at.ins.foreach { in => emitEdge(in, "in") }
      }
      //emitEdge(cu.parent, cu, DotAttr().style(bold).color(red))
    }
    emitNode(design.top, s"${design.top}")
    val command = design.top.ctrlBox.command 
    command.to.foreach { to => emitEdge(to, "command") }
    //TODO: Somehow dot fails if add this edge
    //val status = design.top.status
    //emitEdge(status, "status")
  }

  override def finPass = {
    emitBEln
    close
    endInfo(s"Finishing Ctrl Dot Printing in ${getPath}")
  }

  override def quote(n:Any):String = super[DotCodegen].quote(n)

}
