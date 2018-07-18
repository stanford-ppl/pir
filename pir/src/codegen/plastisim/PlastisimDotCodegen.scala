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
    case n:NetworkNode if ctrlOf(n).isInstanceOf[ArgInController] => super.visitNode(n)
    case n:NetworkNode => emitSingleNode(n)
    case n:ArgFringe => super.visitNode(n)
    case n:GlobalContainer => emitSubGraph(n) { super.visitNode(n) }
    case n => super.visitNode(n)
  }

  override def label(attr:DotAttr, n:Any) = n match {
    case n:ContextEnable =>
      var label = ""
      label += s"${quote(n)}"
      label += s"\nctx=${quote(contextOf(n).get)}"
      label += s"\nsrcCts=${quote(srcCtxOf.get(contextOf(n).get).getOrElse(""))}"
      label += s"\nctrl=${ctrlOf(n)}"
      inMemsOf(n).foreach { 
        case (mem:ArgIn, reads) =>
          label += s"\n$mem"
          getScaleOf(reads).foreach { sin => label += s" sin=$sin" }
          bufferSizeOf(mem).foreach { bs => label += s" bs=$bs" }
        case _ =>
      }
      startAtToken(n).foreach { token => label += s"\nstart_at_tokens=$token" }
      stopAfterToken(n).foreach { token => label += s"\nstop_after_tokens=$token" }
      countsOf(n).foreach { counts => label += s"\ncounts=$counts" }
      activeOf.get(n).foreach { active => label += s"\nactive=$active" }
      stalledOf.get(n).foreach { stalled => label += s"\nstalled=$stalled %" }
      starvedOf.get(n).foreach { starved => label += s"\nstarved=$starved %" }
      val cuP = globalOf(n).get
      cuP match {
        case cuP:DramFringe if PIRConfig.enableTrace =>
        case cuP:ArgFringe =>
        case cuP =>
          latencyOf(n).foreach { lat => label += s"\nlat = $lat" }
      }
      if (spade.node.isDynamic(topS)) {
        addrOf(n).foreach { addr => label += s"\naddr=${addr}" }
      }
      attr.label(label)
    case n => super.label(attr, n)
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcMap = srcsOf(n)
    val dstMap = dstsOf(n)
    val srcs:List[NetworkNode] = srcMap.values.flatMap { _.keys }.toSet.toList
    val dsts:List[NetworkNode] = dstMap.values.flatMap { _.keys }.toSet.toList
    val counts = assertOptionUnify(n, "counts") { mem => countsOf.getOrElse(mem, None) }

    val from = goutOf(n)
    n.foreach { mem =>
      val bs = bufferSizeOf(mem)
      srcMap(mem).foreach { case (src, ias) =>
        val lat = staticLatencyOf(src, mem)
        val sout = getScaleOf(ias)
        dstMap(mem).foreach { case (dst, oas) =>
          val sin = getScaleOf(oas)
          var label = s"$mem"
          from.foreach{ from => label += s"\nid=${from.id}" } 
          sout.foreach { sout => label += s"\nsout=$sout" }
          counts.foreach { counts => label += s"\ncount=$counts" }
          sin.foreach { sin => label += s"\nsin=$sin" }
          bs.foreach { bs => label += s"\nbs=$bs" }
          lat.foreach { lat => label += s"\nlat=$lat" }
          emitEdgeMatched(src, dst, DotAttr.empty.label(label))
        }
      }
    }
  }
}
