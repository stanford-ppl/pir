package pir.codegen

import pir._
import pir.node._
import pir.pass._
import spade.node.{isMesh, isDynamic}
import spade.params._
import prism._
import prism.util.Memorization
import prism.collection.mutable._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PIRCodegen with ConstantPropogator with Memorization {
  import spademeta._

  def shouldRun = Config.codegen && isMesh(compiler.arch.top) && isDynamic(compiler.arch.top)
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  val fileName = s"${compiler}.psim"

  val mapping = compiler.dynamicCUPlacer.mapping

  val linkSrc = new OneToOneMap[LocalStore, N]()
  val linkDst = new OneToOneMap[LocalStore, N]()

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
        val stages = collectDown[StageDef](n)
        emitln(s"lat = ${Math.max(stages.size, 1)}")
        emitInByLocalLoad(n)
        emitOutByGlobalOutput(n)
        emitOutByLocalStore(n)
      }
    case n:FringeContainer if isLoadFringe(n) =>
      emitNodeBlock(s"node $n") {
        emitlnc(s"lat = 100", s"assume dram latency of 100 cycles")
        emitOutByGlobalOutput(n)
        val size = collectDown[StreamOut](n).filter { _.field == "size" }.head
        val csize = getConstOf[Int](size)
        dbg(s"csize = $csize")
        collectDown[StreamOut](n).foreach { mem =>
          dbgblk(qdef(mem)) {
            writersOf(mem).zipWithIndex.foreach { case (store, idx) =>
              emitln(s"link_in[$idx] = $store")
              emitln(s"scale_in[$idx] = ${csize / 4}") // size in bytes to words
              linkDst(store) = n
            }
          }
        }
      }
    case n:FringeContainer if isStoreFringe(n) => 
      assert(false, s"TODO: add backend for storeFringe")
    case n:ArgFringe =>
      emitNodeBlock(s"node ${n}_out") {
        emitOutByGlobalOutput(n)
        emitln(s"start_at_tokens = 2")
      }
      emitNodeBlock(s"node ${n}_in") {
        collectDown[ArgOut](n).foreach { mem =>
          dbgblk(qdef(mem)) {
            writersOf(mem).zipWithIndex.foreach { case (store, idx) =>
              emitln(s"link_in[$idx] = $store")
              emitln(s"scale_in[$idx] = 1")
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
    emitNodeBlock(s"${nettp}net configs/mesh${nr}x${nc}.cfg") {
      emitln(s"dim[0] = $nr")
      emitln(s"dim[1] = $nc")
    }
  }

  def isStaticLink(mem:Memory, src:N, dst:N) = (mem, src, dst) match {
    case (mem, src:ArgFringe, dst) => true
    case (mem, src, dst:ArgFringe) => true
    case (mem, src:ComputeContext, dst:ComputeContext) if isPMU(globalOf(src).get) && isPMU(globalOf(dst).get) => true
    case _ => false
  }

  def emitLink(n:LocalStore) = {
    emitNodeBlock(s"netlink $n") {
      val tp = bundleTypeOf(n) match {
        case Vector => "vec"
        case Word => "scal"
        case Bit => "ctrl"
        case _ => "impossible case"
      }
      emitln(s"tp = $tp")
      val src = linkSrc(n)
      val dst = linkDst(n)
      val srcCUP = globalOf(src).getOrElse(src)
      val dstCUP = globalOf(dst).getOrElse(dst)
      val srcCUS = mapping.get(srcCUP)
      val dstCUS = mapping.get(dstCUP)
      emitlnc(s"src = ${src}", s"${globalOf(src)} $srcCUS")
      emitlnc(s"dst = ${dst}", s"${globalOf(dst)} $dstCUS")
      val mem = memsOf(n).head
      (mem, src, dst) match {
        case (mem, src, dst) if isStaticLink(mem, src, dst) =>
          emitln(s"lat = 1")
        case (mem, src, dst) => 
          emitln(s"net = ${tp}net")
          emitln(s"srcAddr = ${addrOf(srcCUS.get)}")
          emitln(s"dstAddr = ${addrOf(dstCUS.get)}")
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
    collectDown[GlobalOutput](n).flatMap { gout =>
      val stores = collectOut[LocalStore](gout, visitFunc=visitGlobalOut)
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
    collectDown[LocalStore](n).zipWithIndex.foreach { case (store, idx) =>
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
    collectDown[LocalLoad](n).zipWithIndex.foreach { case (load, idx) =>
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
        linkDst(store) = n
      }
    }
  }

}
