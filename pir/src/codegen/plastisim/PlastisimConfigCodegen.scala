package pir
package codegen

import pir.node._
import prism.collection.mutable._
import sys.process._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PlastisimCodegen {
  import pirmeta._

  val fileName = s"$compiler.psim"
  lazy val SPATIAL_HOME = Config.SPATIAL_HOME.getOrElse(s"Please set SPATIAL_HOME for using trace!")
  val appPath = s"${Config.SPATIAL_HOME}${separator}gen${separator}${compiler.name}"
  val tracePath = s"${appPath}${separator}traces"

  lazy val topS = compiler.arch.design.top

  // Execution of codegen
  override def runPass = {
    super.runPass
    linkGroupOf.values.toSet.foreach { link => emitLink(link) }
    if (spade.node.isDynamic(topS)) {
      emitNetwork
    }
  }

  override def emitNode(n:N) = n match {
    case n:Node => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:Node) = {
    val cuP = globalOf(n).get
    emitNodeBlock(s"node ${quote(n)} # ${quote(cuP)}") {
      emitNodeSpecs(n)
      emitInLinks(n)
      emitOutLinks(n)
    }
  }

  def emitNodeSpecs(n:ContextEnable) = {
    val cuP = globalOf(n).get
    cuP match {
      case cuP:DramFringe if PIRConfig.loadTrace =>
        val path = s"${tracePath}${separator}${nameOf(cuP)}.trace"
        if (exists(path)) {
          val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
          emitln(s"offset_trace = ${path}")
          emitln(s"size = ${boundOf(size)}")
        } else {
          err(s"trace file for ${cuP} at ${path} does not exist!")
        }
      case cuP:ArgFringe =>
        ctrlOf(n) match {
          case _:ArgInController =>
            emitln(s"start_at_tokens = 1")
          case _:ArgOutController =>
            emitln(s"stop_after_tokens = 1")
        }
      case cuP =>
        emitln(s"lat = ${latencyOf(n)}")
    }
  }

  def emitNodeBlock(n:Any)(block: => Unit) = dbgblk(s"emitNodeBlock($n)") {
    emitBlock(s"$n", b=NoneBraces)(block)
  }

  override def emitComment(msg:String) = emitln(s"# $msg")

  def emitNetwork = {
    import topParam._
    if (spade.node.isDynamic(topS)) {
      topParam.networkParams.foreach { networkParam =>
        val tp = networkParam.bct
        val nr = numRows + 1
        val nc = numCols + 2
        val sq = math.max(nr, nc)
        emitNodeBlock(s"net ${quote(tp)}net") {
          emitln(s"cfg = mesh_generic.cfg")
          emitln(s"dim[0] = $sq")
          emitln(s"dim[1] = $sq")
          emitln(s"num_classes = ${networkParam.numVirtualClasses}")
        }
      }
    }
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val isStatic = isStaticLink(n)
    val linkstr = if (isStatic) "" else "net"

    emitNodeBlock(s"${linkstr}link ${quote(n)}") {
      val tp = pinTypeOf(n)
      emitln(s"type = ${quote(tp)}")
      srcs.zipWithIndex.foreach { case (src,idx) =>
        emitln(s"src[$idx] = ${quote(src)}")
      }
      dsts.zipWithIndex.foreach { case (dst,idx) =>
        emitln(s"dst[$idx] = ${quote(dst)}")
      }
      if (isStatic) {
        srcs.zipWithIndex.foreach { case (src, srcIdx) =>
          dsts.zipWithIndex.foreach { case (dst, dstIdx) =>
            emitln(s"lat[$srcIdx, $dstIdx] = ${staticLatencyOf(src, dst)}")
          }
        }
      } else {
        emitln(s"net = ${quote(tp)}net")
        val vc = assertUnify(n, "vc") { mem => vcmap.mappedValue(mem) }
        emitln(s"class = $vc")
        val saddrs = srcs.map(src => globalOf(src).get.id)
        val daddrs = dsts.map(dst => globalOf(dst).get.id)
        saddrs.zipWithIndex.foreach { case (saddr, idx) =>
          emitln(s"src_id[$idx] = $saddr")
        }
        daddrs.zipWithIndex.foreach { case (daddr, idx) =>
          emitln(s"dst_id[$idx] = $daddr")
        }
      }
    }
  }

  def emitInLinks(n:Node) = dbgblk(s"emitInLinks($n)") {
    inlinksOf(n).zipWithIndex.foreach { case ((link, reads), idx) =>
      emitln(s"link_in[$idx] = ${quote(link)}")
      emitln(s"scale_in[$idx] = ${constItersOf(reads)}")
      emitln(s"buffer[$idx]=${bufferSizeOf(reads)}")
    }
  }

  def emitOutLinks(n:Node) = dbgblk(s"emitOutLinks($n)") {
    outlinksOf(n).zipWithIndex.foreach { case ((link, writes), idx) =>
      emitln(s"link_out[$idx] = ${quote(link)}")
      emitln(s"scale_out[$idx] = ${constItersOf(writes)}")
    }
  }

}
