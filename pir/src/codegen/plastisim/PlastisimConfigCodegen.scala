package pir.codegen

import pir.node._
import prism.collection.mutable._
import sys.process._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PlastisimCodegen {
  import pirmeta._

  val fileName = s"${compiler}.psim"
  val appPath = s"${Config.SPATIAL_HOME}${separator}gen${separator}${compiler.name}"
  val tracePath = s"${appPath}${separator}traces"

  lazy val topS = compiler.arch.design.top

  // Execution of codegen
  override def runPass = {
    super.runPass
    if (spade.node.isDynamic(topS)) {
      emitNetwork("vec")
      emitNetwork("scal")
      emitNetwork("ctrl")
    }
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

  def emitNetwork(nettp:String) = {
    import topParam._
    val nr = numRows
    val nc = numCols + 2
    emitNodeBlock(s"net ${nettp}net configs/mesh${nr}x${nc}.cfg") {
      emitln(s"dim[0] = $nr")
      emitln(s"dim[1] = $nc")
    }
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val isStatic = isStaticLink(n)
    val linkstr = if (isStatic) "" else "net"

    emitNodeBlock(s"${linkstr}link ${quote(n)}") {
      val tp = linkTp(n)
      emitln(s"type = ${tp}")
      emitln(s"src = ${quote(srcs)}")
      emitln(s"dst = ${quote(dsts)}")
      if (isStatic) {
        val lats = srcs.map { src => dsts.map { dst => staticLatencyOf(src, dst) }}
        emitlnc(s"lat = ${quote(lats)}", "src[dst[]]")
      } else {
        emitln(s"net = ${tp}net")
        val saddrs = srcs.map(src => addrOf(src).get)
        val daddrs = dsts.map(dst => addrOf(dst).get)
        emitln(s"saddr = ${quote(saddrs)}")
        emitln(s"daddr = ${quote(daddrs)}")
      }
    }
  }

  def emitInLinks(n:Node) = dbgblk(s"emitInLinks($n)") { // ContextEnable or ArgFringe
    inlinksOf(n).zipWithIndex.foreach { case ((link, scaleIn, bufferSize), idx) =>
      emitln(s"link_in[$idx] = ${quote(link)}")
      emitln(s"scale_in[$idx] = $scaleIn")
      emitln(s"buffer[$idx]=$bufferSize")
    }
  }

  def emitOutLinks(n:Node) = dbgblk(s"emitOutLinks($n)") { // ContextEnable or ArgFringe
    outlinksOf(n).zipWithIndex.foreach { case ((link, scaleOut), idx) =>
      emitln(s"link_out[$idx] = ${quote(link)}")
      emitln(s"scale_out[$idx] = $scaleOut")
    }
  }

}

