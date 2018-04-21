package pir.codegen

import pir.node._

import prism.collection.mutable._

class PlastisimDotCodegen(fileName:String)(implicit compiler: PIR) extends PIRIRDotCodegen(fileName) with PlastisimCodegen {
  import pirmeta._
  import spademeta._

  val links = ListBuffer[Link]()
  override def initPass = {
    super.initPass
    links.clear
  }
  override def finPass = {
    links.foreach(emitLink)
    super.finPass
  }

  override def emitNode(n:N) = n match {
    case n:ArgFringe => emitSingleNode(n); super.visitNode(n)
    case n:GlobalContainer => super.emitNode(n)
    case n:ContextEnable => super.emitNode(n)
    case n:Link => links += n
    case n => super.visitNode(n)
  }

  override def label(attr:DotAttr, n:Any) = n match {
    case n:ContextEnable =>
      val cuP = globalOf(n).get
      attr.label(s"${quote(n)}\n(${quote(cuP)})\nlatency=${latencyOf(n)}")
    case n => super.label(attr, n)
  }

  override def emitSingleNode(n:N) = n match {
    case n:ArgFringe =>
      emitNode(s"${n}_in", setAttrs(DotAttr.empty,n).label(s"${n}_in"))
      emitNode(s"${n}_out", setAttrs(DotAttr.empty,n).label(s"${n}_out"))
    case n => super.emitSingleNode(n)
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val mem = memsOf(n).head
    val src = linkSrc(n)
    val dst = linkDst(n)
    val srcCUP = globalOf(src).getOrElse(src).asInstanceOf[GlobalContainer]
    val dstCUP = globalOf(dst).getOrElse(dst).asInstanceOf[GlobalContainer]
    val srcCUS = cumap.mappedValue(srcCUP)
    val dstCUS = cumap.mappedValue(dstCUP)

    val srcName = s"${src}${srcSuffix(srcCUP)}"
    val dstName = s"${dst}${dstSuffix(dstCUP)}"
    val isStatic = isStaticLink(mem, srcCUP, dstCUP)
    var label = s"${quote(n)}\n"
    label += s"${linkTp(n)}_${if (isStatic) "static" else "dynamic"}\n"
    if (!isStatic) {
      label += s"saddr=${addrOf(srcCUS)} "
      label += s"daddr=${addrOf(dstCUS)}\n"
    }
    label += s"scale_out=${linkScaleOutOf(n)}\n"
    label += s"scale_in=${linkScaleInOf(n)}\n"
    label += s"buffer_in=${bufferSizeOf(n)}\n"
    emitEdge(srcName, dstName, DotAttr.empty.label(label))
  }
}


