package pir.codegen

import pir.node._
import spade.node._

import prism.collection.mutable._

class PlastisimConfigCodegenNew(implicit compiler: PIR) extends PIRCodegen {
  import pirmeta._
  import spademeta._

  type Link = LocalStore

  def shouldRun = PIRConfig.mapping && Config.codegen && isMesh(compiler.arch.top) && isDynamic(compiler.arch.top) && pirMap.isRight
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  val fileName = s"${compiler}.psim.new"

  def cumap = pirMap.right.get.cumap

  // Execution of codegen
  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    emitNetwork("vec")
    emitNetwork("scal")
    emitNetwork("ctrl")
  }

  override def emitNode(n:N) = n match {
    case n:ContextEnable => 
      globalOf(n).get match {
        case cuP:FringeContainer if isLoadFringe(cuP) =>
          emitNodeBlock(s"node ${quote(n)} # ${quote(cuP)}") {
            emitln(s"trace = 0") // what is this for?
            emitlnc(s"lat = 100", s"assume dram latency of 100 cycles")
            emitInLinks(n)
            emitOutLinks(n)
          }
        case cuP:FringeContainer if isStoreFringe(cuP) =>
          assert(false, s"TODO: add backend for storeFringe")
          emitNodeBlock(s"node ${quote(n)} # ${quote(cuP)}") {
            emitInLinks(n)
            emitOutLinks(n)
          }
        case cuP =>
          emitNodeBlock(s"node ${quote(n)} # ${quote(cuP)}") {
            val cuS = cumap.mappedValue(cuP)
            val stages = cuS.collectDown[Stage]()
            emitln(s"lat = ${Math.max(stages.size, 1)}")
            emitInLinks(n)
            emitOutLinks(n)
          }
      }
    case n:pir.node.ArgFringe =>
      emitNodeBlock(s"node ${quote(n)}_in # ${quote(n)}") {
        emitln(s"stop_after_tokens = 1")
        emitInLinks(n)
      }
      emitNodeBlock(s"node ${quote(n)}_out # ${quote(n)}") {
        emitln(s"start_at_tokens = 1")
        emitOutLinks(n)
      }
    case n:Link => emitLink(n)
    case n => super.visitNode(n)
  }

  def emitNodeBlock(n:Any)(block: => Unit) = dbgblk(s"emitNodeBlock($n)") {
    emitBlock(s"$n", b=NoneBraces)(block)
  }

  override def emitComment(msg:String) = emitln(s"# $msg")

  // Convert coordinate to linear index
  def addrOf(sn:SNode) = {
    import topParam._
    var List(x,y) = indexOf(sn)
    x += 1 // get ride of negative x coordinate
    val idx = y * (numCols + 2) + x
    dbg(s"$sn: coord = ($x, $y) idx = $idx")
    idx
  }

  def emitNetwork(nettp:String) = {
    import topParam._
    val nr = numRows
    val nc = numCols + 2
    emitNodeBlock(s"net ${nettp}net configs/mesh${nr}x${nc}.cfg") {
      emitln(s"dim[0] = $nr")
      emitln(s"dim[1] = $nc")
    }
  }

  def isStaticLink(mem:Memory, src:N, dst:N) = (mem, src, dst) match {
    case (mem, src:pir.node.ArgFringe, dst) => true
    case (mem, src, dst:pir.node.ArgFringe) => true
    case (mem, src:ComputeContext, dst:ComputeContext) if isPMU(globalOf(src).get) && isPMU(globalOf(dst).get) => true
    case _ => false
  }

  def bufferSizeOf(n:Link) = {
    val memP = memsOf(n).head
    val cuP = globalOf(memP).get
    val cuS = cumap(cuP).head
    cuS match {
      case cuS:MC =>
        val isLoad = isLoadFringe(cuP)
        val isStore = isStoreFringe(cuP)
        memP match {
          case memP:StreamOut if memP.field == "size" & isLoad =>
            cuS.param.rSizeFifoParam.size
          case memP:StreamOut if memP.field == "offset" & isLoad =>
            cuS.param.rOffsetFifoParam.size
          case memP:StreamOut if memP.field == "size" & isStore =>
            cuS.param.wSizeFifoParam.size
          case memP:StreamOut if memP.field == "offset" & isStore =>
            cuS.param.wOffsetFifoParam.size
          case memP:StreamOut if memP.field == "data" & isStore=>
            if (isWord(n)) cuS.param.sDataFifoParam.size
            else if (isVector(n)) cuS.param.vDataFifoParam.size
            else throw PIRException(s"Unsupported dram data type ${pinTypeOf(n)}")
        }
      case cuS:CU =>
        memP match {
          case memP:pir.node.RetimingFIFO =>
            n match {
              case n if isBit(n) => cuS.param.controlFifoParam.size
              case n if isWord(n) => cuS.param.scalarFifoParam.size
              case n if isVector(n) => cuS.param.vectorFifoParam.size
            }
          case memP:pir.node.SRAM => cuS.param.sramParam.depth
          case memP:pir.node.ArgIn => cuS.param.scalarFifoParam.size
          case memP:pir.node.Reg => cuS.param.scalarFifoParam.size
        }
      case cuS:spade.node.ArgFringe =>
        1
    }

  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val mem = memsOf(n).head
    val src = mem match {
      case mem:ArgIn => globalOf(n.collectInTillMem[ArgInDef]().head).get
        // case mem:StreamIn => no store on StreamIn
      case mem => n.collectInTillMem[ContextEnable]().head
    }
    val dst = mem match {
      case mem:StreamOut => ctxEnOf(globalOf(mem).get).get
      case mem:ArgOut => globalOf(mem).get
      case WithReader(load) => load.collectInTillMem[ContextEnable]().head
    }
    val srcCUP = globalOf(src).getOrElse(src).asInstanceOf[GlobalContainer]
    val dstCUP = globalOf(dst).getOrElse(dst).asInstanceOf[GlobalContainer]
    val srcCUS = cumap.mappedValue(srcCUP)
    val dstCUS = cumap.mappedValue(dstCUP)

    val linkstr = if (isStaticLink(mem, src, dst))
      ""
    else
      "net"

    val srcSuffix = srcCUP match {
      case x:pir.node.ArgFringe => "_out"
      case _ => ""
    }

    val dstSuffix = dstCUP match {
      case x:pir.node.ArgFringe => "_in"
      case _ => ""
    }


    emitNodeBlock(s"${linkstr}link $n") {
      val tp = n match {
        case n if isBit(n) => "ctrl"
        case n if isWord(n) => "scal"
        case n if isVector(n) => "vec"
      }
      emitln(s"type = $tp")
      emitlnc(s"src = ${src}${srcSuffix}", s"${quote(srcCUP)} ${quote(srcCUS)}")
      emitlnc(s"dst = ${dst}${dstSuffix}", s"${quote(dstCUP)} ${quote(dstCUS)}")
      (mem, src, dst) match {
        case (mem, src, dst) if isStaticLink(mem, src, dst) =>
          emitln(s"lat = 1")
        case (mem, src, dst) => 
          emitln(s"net = ${tp}net")
          emitln(s"saddr = ${addrOf(srcCUS)}")
          emitln(s"daddr = ${addrOf(dstCUS)}")
      }
    }
  }

  def emitOutLinks(n:PIRNode) = { // ContextEnable or ArgFringe
    val links = n.collectOutTillMem[LocalStore]()
    links.zipWithIndex.foreach { case (link, idx) =>
      emitln(s"link_out[$idx] = $link")
      emitln(s"scale_out[$idx] = ${linkScaleOutOf(link)}")
    }
  }

  def emitInLinks(n:PIRNode) = { // ContextEnable or ArgFringe
    val mems = n match {
      case n:pir.node.ArgFringe => n.collectDown[ArgOut]()
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

}

