package pir.codegen

import pir.node._

import prism.collection.mutable._

class PlastisimDotCodegen(fileName:String)(implicit compiler: PIR) extends PIRIRDotCodegen(fileName) with PlastisimCodegen {
  import pirmeta._
  import spademeta._

  override def emitNode(n:N) = n match {
    case n:ArgFringe => super.visitNode(n)
    case n:GlobalContainer => emitSubGraph(n) { super.visitNode(n) }
    case n:Node => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:Node) = emitSingleNode(n)

  override def label(attr:DotAttr, n:Any) = n match {
    case n:ContextEnable =>
      val cuP = globalOf(n).get
      var label = ""
      label += s"${quote(n)}"
      label += s"\n(${quote(cuP)})"
      label += s"\nlat=${latencyOf(n)}"
      addrOf(n).foreach { addr => label += s"\naddr=${addr}" }
      inlinksOf(n).foreach { case (link, scaleIn, bufferSize) =>
        label += s"\n${quote(link)} [sin=$scaleIn, bs=$bufferSize]"
      }
      outlinksOf(n).foreach { case (link, scaleOut) =>
        label += s"\n${quote(link)} [sout=$scaleOut]"
      }
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
        if (isStatic) {
          label += s"\nlat=${staticLatencyOf(src, dst)}"
        }
        emitEdge(src, dst, DotAttr.empty.label(label))
      }
    }
  }
}
