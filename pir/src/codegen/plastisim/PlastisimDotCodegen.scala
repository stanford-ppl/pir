package pir.codegen

import pir.node._
import pir.mapper._

import prism.collection.mutable._

class PlastisimDotCodegen(fileName:String)(implicit compiler: PIR) extends PIRIRDotCodegen(fileName) with PlastisimCodegen {
  import pirmeta._
  import spademeta._

  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    linkGroupOf.values.toSet.foreach { link => emitLink(link) }
  }

  override def emitNode(n:N) = n match {
    case n:Node => emitSingleNode(n)
    case n:ArgFringe => super.visitNode(n)
    case n:GlobalContainer => emitSubGraph(n) { super.visitNode(n) }
    case n => super.visitNode(n)
  }

  override def label(attr:DotAttr, n:Any) = n match {
    case n:ContextEnable =>
      val cuP = globalOf(n).get
      var label = ""
      label += s"${quote(n)}"
      label += s"\ncu=${quote(cuP)}"
      label += s"\nctrl=${ctrlOf(n)}"
      label += s"\ncounts=${countsOf(n)}"
      if (compiler.session.hasRun[CUPlacer]) {
        label += s"\nlat=${latencyOf(n)}"
        addrOf(n).foreach { addr => label += s"\naddr=${addr}" }
        inlinksOf(n).foreach { case (link, reads) =>
          label += s"\n${quote(link)} [sin=${getItersOf(reads)}, bs=${bufferSizeOf(reads)}]"
        }
      } else {
        inlinksOf(n).foreach { case (link, reads) =>
          label += s"\n${quote(link)} [sin=${getItersOf(reads)}]"
        }
      }
      outlinksOf(n).foreach { case (link, writes) =>
        label += s"\n${quote(link)} [sout=${getItersOf(writes)}, counts=${getCountsOf(writes)}]"
      }
      //countsOf.get(n).foreach { count => label += s"\ncounts=$count" }
      attr.label(label)
    case n => super.label(attr, n)
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    srcs.foreach { src =>
      dsts.foreach { dst =>
        var label = s"${quote(n)}"
        val isStatic = isStaticLink(src, dst)
        label += s"\nisStatic=${isStatic}"
        if (isStatic && compiler.session.hasRun[CUPlacer]) {
          label += s"\nlat=${staticLatencyOf(src, dst)}"
        }
        emitEdge(src, dst, DotAttr.empty.label(label))
      }
    }
  }
}
