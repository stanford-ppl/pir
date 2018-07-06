package pir
package codegen

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
    //case n:NetworkNode if within[ArgFringe](n) => super.visitNode(n)
    case n:NetworkNode => emitSingleNode(n)
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
      countsOf(n).foreach { counts => label += s"\ncounts=$counts" }
      cuP match {
        case cuP:DramFringe if PIRConfig.enableTrace =>
        case cuP:ArgFringe =>
        case cuP =>
          latencyOf(n).foreach { lat => label += s"\nlat = $lat" }
      }
      if (spade.node.isDynamic(topS)) {
        addrOf(n).foreach { addr => label += s"\naddr=${addr}" }
      }
      inlinksOf(n).foreach { case (link, reads) =>
        val accums = link.filter { mem => isAccum(mem) }
        label += s"\n${quote(link)} ${if (accums.nonEmpty) "(isAccum)" else "" }"
        getItersOf(reads).foreach { sin => label += s"\n[sin=$sin]" }
        bufferSizeOf(reads).foreach { bs => label += s"\n[bs=$bs]" }
      }
      outlinksOf(n).foreach { case (link, writes) =>
        label += s"\n${quote(link)}"
        getItersOf(writes).foreach { sout => label += s"\n[sout=$sout]" }
      }
      attr.label(label)
    case n => super.label(attr, n)
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val counts = assertUnify(n, "counts") { mem => getCountsOf(mem) }
    n.foreach { mem =>
      val memSrcs = srcsOf(mem)
      val memDsts = dstsOf(mem)
      memSrcs.foreach { src =>
        val lat = staticLatencyOf(src, mem)
        val gin = ginFrom(src, mem)
        memDsts.foreach { dst =>
          var label = s"${quote(n)}"
          gin.foreach { gin =>
            label += s"\nfrom=${quote(goutOf(gin).get)}"
            label += s"\nto=${quote(gin)}"
          }
          lat.foreach { lat => label += s"\nlat=$lat" }
          counts.foreach { counts => label += s"\n[counts=$counts]" }
          emitEdgeMatched(src, dst, DotAttr.empty.label(label))
        }
      }
    }
  }
}
