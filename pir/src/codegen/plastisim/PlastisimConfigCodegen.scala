package pir.codegen

import pir.node._
import prism.collection.mutable._
import sys.process._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PlastisimCodegen {
  import pirmeta._

  val fileName = s"${compiler}.psim"
  val appPath = s"${Config.SPATIAL_HOME}${separator}gen${separator}${compiler.name}"
  val tracePath = s"${appPath}${separator}traces"

  // Execution of codegen
  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    emitNetwork("vec")
    emitNetwork("scal")
    emitNetwork("ctrl")
  }

  override def emitNode(n:N) = n match {
    case n:ContextEnable => 
      val cuP = globalOf(n).get
      emitNodeBlock(s"node ${quote(n)} # ${quote(cuP)}") {
        emitNodeLatency(n)
        emitInLinks(n)
        emitOutLinks(n)
      }
    case n:ArgFringe =>
      emitNodeBlock(s"node ${quote(n)}_in # ${quote(n)}") {
        emitln(s"stop_after_tokens = 1")
        emitInLinks(n)
      }
      emitNodeBlock(s"node ${quote(n)}_out # ${quote(n)}") {
        emitln(s"start_at_tokens = 1")
        emitOutLinks(n)
      }
      super.visitNode(n)
    case n:Link => emitLink(n)
    case n => super.visitNode(n)
  }
  
  def emitNodeLatency(n:ContextEnable) = {
    val cuP = globalOf(n).get
    cuP match {
      case cuP:FringeContainer if PIRConfig.loadTrace =>
        val path = s"${tracePath}${separator}${nameOf(cuP)}.trace"
        if (exists(path)) {
          val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
          emitln(s"offset_trace = ${path}")
          emitln(s"size = ${boundOf(size)}")
        } else {
          err(s"trace file for ${cuP} at ${path} does not exist!")
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
    val mem = memsOf(n).head
    val src = linkSrc(n)
    val dst = linkDst(n)
    val srcCUP = globalOf(src).getOrElse(src).asInstanceOf[GlobalContainer]
    val dstCUP = globalOf(dst).getOrElse(dst).asInstanceOf[GlobalContainer]
    val srcCUS = cumap.mappedValue(srcCUP)
    val dstCUS = cumap.mappedValue(dstCUP)

    val isStatic = isStaticLink(mem, srcCUP, dstCUP)
    val linkstr = if (isStatic) "" else "net"

    emitNodeBlock(s"${linkstr}link $n") {
      val tp = linkTp(n)
      emitln(s"type = $tp")
      emitlnc(s"src = ${src}${srcSuffix(srcCUP)}", s"${quote(srcCUP)} ${quote(srcCUS)}")
      emitlnc(s"dst = ${dst}${dstSuffix(dstCUP)}", s"${quote(dstCUP)} ${quote(dstCUS)}")
      (mem, src, dst) match {
        case (mem, src, dst) if isStatic =>
          emitln(s"lat = 1")
        case (mem, src, dst) => 
          emitln(s"net = ${tp}net")
          emitln(s"saddr = ${addrOf(srcCUS)}")
          emitln(s"daddr = ${addrOf(dstCUS)}")
      }
    }
  }

  def emitInLinks(n:PIRNode) = dbgblk(s"emitInLinks($n)") { // ContextEnable or ArgFringe
    val mems = n match {
      case n:ArgFringe => n.collectDown[ArgOut]()
      case n:ContextEnable => 
        globalOf(n).get match {
          case cuP:FringeContainer => cuP.collectDown[StreamOut]()
          case cu => n.collectInTillMem[Memory]()
        }
    }
    val links = mems.flatMap {
      case mem@WithWriter(writer) => List(writer)
      case mem@WithWriters(Nil) => List()
      case mem@WithWriters(writers) if writers.size > 1 =>
        throw PIRException(s"TODO add support to multiple writers in PlastisimConfigCodegen. mem=$mem, writers=$writers")
    }
    links.zipWithIndex.foreach { case (link, idx) =>
      emitln(s"link_in[$idx] = $link")
      emitln(s"scale_in[$idx] = ${linkScaleInOf(link)}")
      emitln(s"buffer[$idx]=${bufferSizeOf(link)}")
    }
  }

  def emitOutLinks(n:PIRNode) = dbgblk(s"emitOutLinks($n)") { // ContextEnable or ArgFringe
    val links = n.collectOutTillMem[LocalStore]()
    links.zipWithIndex.foreach { case (link, idx) =>
      emitln(s"link_out[$idx] = $link")
      emitln(s"scale_out[$idx] = ${linkScaleOutOf(link)}")
    }
  }

}

