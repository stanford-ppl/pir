package pir.codegen

import pir.node._
import spade.node._

import prism.util.Memorization
import prism.collection.mutable._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PIRCodegen with pir.pass.ConstantPropogator with Memorization {
  import pirmeta._
  import spademeta._

  def shouldRun = PIRConfig.mapping && Config.codegen && isMesh(compiler.arch.top) && isDynamic(compiler.arch.top) && pirMap.isRight
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  val fileName = s"${compiler}.psim"

  val linkSrc = new OneToOneMap[LocalStore, N]()
  val linkDst = new OneToOneMap[LocalStore, N]()

  def cumap = pirMap.right.get.cumap

  override def reset = {
    resetAllCaches
    linkSrc.clear
    linkDst.clear
  }

  // Execution of codegen
  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    assert(linkSrc.keys.size == linkDst.keys.size)
    linkSrc.keys.foreach(emitLink)
    emitNetwork("vec")
    emitNetwork("scal")
    emitNetwork("ctrl")
  }

  override def emitNode(n:N) = n match {
    case n:ComputeContext if ctxEnOf(n).nonEmpty =>
      emitNodeBlock(s"node $n # ${globalOf(n).get}") {
        val cuP = globalOf(n).get
        val cuS = cumap.mappedValue(cuP)
        val stages = cuS.collectDown[Stage]()
        emitln(s"lat = ${Math.max(stages.size, 1)}")
        emitInByLocalLoad(n)
        emitOutByGlobalOutput(n)
        emitOutByLocalStore(n)
      }
    case n:FringeContainer if isLoadFringe(n) =>
      emitNodeBlock(s"node $n # DRAM Load") {
        emitln(s"trace = 0")
        emitlnc(s"lat = 100", s"assume dram latency of 100 cycles")
        emitOutByGlobalOutput(n)
        val size = n.collectDown[StreamOut]().filter { _.field == "size" }.head
        val data = n.collectDown[StreamIn]().filter { _.field == "data" }.head
        val csize = getConstOf[Int](size)
        var idx = 0;
        dbg(s"csize = $csize")
        n.collectDown[StreamOut]().foreach { mem =>
          dbgblk(qdef(mem)) {
            writersOf(mem).foreach { case (store) =>
              emitln(s"link_in[$idx] = $store")
              emitln(s"scale_in[$idx] = ${csize / 4 / parOf(data).get}") // size in bytes to words
              emitln(s"buffer[$idx] = ${bufferSizeOf(store)}")
              idx = idx+1
              linkDst(store) = n
            }
          }
        }
      }
    case n:FringeContainer if isStoreFringe(n) => 
      emitNodeBlock(s"node $n # DRAM Store") {
        emitln(s"trace = 0")
        emitlnc(s"lat = 100", s"assume dram latency of 100 cycles")
        emitOutByGlobalOutput(n)
        val size = n.collectDown[StreamOut]().filter { _.field == "size" }.head
        val data = n.collectDown[StreamIn]().filter { _.field == "data" }.head
        val csize = getConstOf[Int](size)
        var idx = 0;
        dbg(s"csize = $csize")
        n.collectDown[StreamOut]().foreach { mem =>
          dbgblk(qdef(mem)) {
            writersOf(mem).foreach { case (store) =>
              emitln(s"link_in[$idx] = $store")
              emitln(s"scale_in[$idx] = ${csize / 4 / parOf(data).get}") // size in bytes to words
              emitln(s"buffer[$idx] = ${bufferSizeOf(store)}")
              idx = idx+1
              linkDst(store) = n
            }
          }
        }
      }
    case n:pir.node.ArgFringe =>
      emitNodeBlock(s"node ${n}_out") {
        emitOutByGlobalOutput(n)
        emitln(s"start_at_tokens = 1")
      }
      emitNodeBlock(s"node ${n}_in") {
        n.collectDown[ArgOut]().foreach { mem =>
          dbgblk(qdef(mem)) {
            writersOf(mem).zipWithIndex.foreach { case (store, idx) =>
              emitln(s"link_in[$idx] = $store")
              emitln(s"scale_in[$idx] = 1")
              emitln(s"buffer[$idx] = ${bufferSizeOf(store)}")
              linkDst(store) = n
            }
          }
          emitln(s"stop_after_tokens = 1")
        }
      }
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

  def bufferSizeOf(n:LocalStore) = {
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

  def emitLink(n:LocalStore) = {
    val src = linkSrc(n)
    val dst = linkDst(n)
    val srcCUP = globalOf(src).getOrElse(src).asInstanceOf[GlobalContainer]
    val dstCUP = globalOf(dst).getOrElse(dst).asInstanceOf[GlobalContainer]
    val srcCUS = cumap(srcCUP).head
    val dstCUS = cumap(dstCUP).head
    val mem = memsOf(n).head

    val linkstr = if (isStaticLink(mem, src, dst))
      ""
    else
      "net"

    val srcSuffix = src match {
      case x:pir.node.ArgFringe => "_out"
      case _ => ""
    }

    val dstSuffix = dst match {
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
      emitlnc(s"src = ${src}${srcSuffix}", s"${globalOf(src)} $srcCUS")
      emitlnc(s"dst = ${dst}${dstSuffix}", s"${globalOf(dst)} $dstCUS")
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

  val itersOf = Cache[Def, Int](computeIters)
  def computeIters(n:Def):Int = dbgblk(s"computeIters($n)") {
    n match {
      case Def(n, CounterDone(Def(ctr, EnabledCounter(min, max, step, par, en)))) =>
        val cmin = getConstOf[Int](min, logger=Some(this))
        val cmax = getConstOf[Int](max)
        val cstep = getConstOf[Int](step)
        dbg(s"ctr=$ctr cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
        val iters = (cmax - cmin) / (cstep * par)
        iters * itersOf(en)
      case n:ContextEnable => 1
      case n:ArgInValid => 1
    }
  }

  def emitOutByGlobalOutput(n:N) = {
    // PCU + PMU write
    n.collectDown[GlobalOutput]().flatMap { gout =>
      val stores = gout.collect[LocalStore](visitFunc=visitGlobalOut)
      stores.map { store => (gout, store) }
    }.zipWithIndex.foreach { case ((Def(gout, GlobalOutput(data, valid)), store), idx) =>
      dbgblk(s"gout=$gout store=${qdef(store)}") {
        emitln(s"link_out[$idx] = $store")
        val storeCount = itersOf(valid)
        emitln(s"scale_out[$idx] = $storeCount")
        linkSrc(store) = n
      }
    }
  }

  def emitOutByLocalStore(n:N) = {
    // PMU read
    n.collectDown[LocalStore]().zipWithIndex.foreach { case (store, idx) =>
      dbgblk(s"${qdef(store)}") {
        emitln(s"link_out[$idx] = $store")
        val writeNext = accessNextOf(store)
        val storeCount = itersOf(writeNext)
        val mem = memsOf(store).head
        assert(isRemoteMem(mem), s"$store in $n with ContextEnable")
        emitln(s"scale_out[$idx] = $storeCount")
        linkSrc(store) = n
      }
    }
  }

  def emitInByLocalLoad(n:N) = {
    n.collectDown[LocalLoad]().zipWithIndex.foreach { case (load, idx) =>
      dbgblk(s"${qdef(load)}") {
        val mem = memsOf(load).head
        dbg(s"mem=${qtype(mem)}")
        val stores = writersOf(mem)
        assert(stores.size==1, s"TODO add support to multiple writers in PlastisimConfigCodegen. stores=$stores")
        val store = stores.head
        dbg(s"store=${qtype(store)}")
        emitln(s"link_in[$idx] = $store")
        val loadCount = itersOf(accessNextOf(load))
        dbg(s"loadCount=$loadCount")
        emitln(s"scale_in[$idx] = $loadCount")
        emitln(s"buffer[$idx] = ${bufferSizeOf(store)}")
        linkDst(store) = n
      }
    }
  }

}
