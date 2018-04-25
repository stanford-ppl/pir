package pir.codegen

import pir.node._
import spade.node._

import prism.collection.mutable._

trait PlastisimCodegen extends PIRCodegen {
  import pirmeta._
  import spademeta._

  type Node = ContextEnable
  type Link = linkGroup.V
  implicit val lct = classTag[Link]

  def cumap = pirMap.right.get.cumap
  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]

  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    linkGroup.values.foreach { link => emitLink(link) }
  }

  override def emitNode(n:N) = n match {
    case n:Node => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:ContextEnable):Unit
  def emitLink(n:Link):Unit

  def srcOf(mem:Memory) = dbgblk(s"srcOf(${quote(mem)})") {
    def visitFunc(n:PIRNode) = visitGlobalIn(n).filterNot {
      case n:Memory => true
      case _ => false
    }
    mem.collect[Node](visitFunc=visitFunc _)
  }

  def srcsOf(n:Link) = {
    n.flatMap { mem => srcOf(mem) }
  }

  def dstOf(mem:Memory) = dbgblk(s"dstOf(${quote(mem)})"){
    def visitFunc(n:PIRNode) = visitGlobalOut(n).filterNot {
      case n:Memory => true
      case n:NotFull => true
      case _ => false
    }
    mem.collect[Node](visitFunc=visitFunc _)
  }

  def dstsOf(n:Link) = {
    n.flatMap { mem => dstOf(mem) }
  }

  def inlinksOf(n:Node) = {
    val reads = n.collectPeer[LocalLoad]()
    reads.map { read =>
      val mem::Nil = memsOf(read)
      val link = linkGroup(mem)
      (link, itersOf(read), bufferSizeOf(mem))
    }
  }

  def outlinksOf(n:Node) = {
    val writes = n.collectOutTillMem[LocalStore]()
    val links = writes.groupBy { write =>
      val mems = memsOf(write)
      linkGroup(mems.head) // All mems should belongs to the same linkGroup
    }
    links.toSeq.map { case (link, writers) =>
      val iters = writers.map { writer => itersOf(writer) }
      assert(iters.toSet.size==1, s"not all writers have the same iters on the same link, link=$link, iters=$iters")
      (link, iters.head)
    }
  }

  // Convert coordinate to linear index
  def addrOf(sn:SNode):Option[Int] = {
    import topParam._
    indexOf.get(sn).map { case List(xx,y) =>
      val x = xx + 1 // get ride of negative x coordinate
      val idx = y * (numCols + 2) + x
      dbg(s"$sn: coord = ($x, $y) idx = $idx")
      idx
    }
  }

  def addrOf(node:Node):Option[Int] = {
    val cuP = globalOf(node).get
    val cuS = cumap.mappedValue(cuP)
    addrOf(cuS)
  }

  def isStaticLink(src:Node, dst:Node):Boolean = {
    val srcCUP = globalOf(src).get
    val dstCUP = globalOf(dst).get
    val isStatic = srcCUP == dstCUP
    dbg(s"isStatic($src($srcCUP), $dst($dstCUP)) = $isStatic")
    isStatic
  }

  def isStaticLink(n:Link):Boolean = {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val isStatics = srcs.flatMap { src => dsts.map { dst => isStaticLink(src, dst) } }
    assert(isStatics.size == 1, s"not all srcs and dsts conform on isStaticLink")
    isStatics.head
  }

  def bufferSizeOf(memP:Memory):Int = {
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
            if (isWord(memP)) cuS.param.sDataFifoParam.size
            else if (isVector(memP)) cuS.param.vDataFifoParam.size
            else throw PIRException(s"Unsupported dram data type ${pinTypeOf(memP)}")
          case memP:StreamIn if memP.field == "data" & isLoad => 1
          case memP:StreamIn if memP.field == "data" & isStore => 1
        }
      case cuS:CU =>
        memP match {
          case memP if isFIFO(memP) =>
            memP match {
              case memP if isBit(memP) => cuS.param.controlFifoParam.size
              case memP if isWord(memP) => cuS.param.scalarFifoParam.size
              case memP if isVector(memP) => cuS.param.vectorFifoParam.size
            }
          case memP:pir.node.SRAM => cuS.param.sramParam.depth
          case memP if isReg(memP) => cuS.param.scalarFifoParam.size
        }
      case cuS:spade.node.ArgFringe => 1
    }

  }

  def linkTp(n:Memory):String = n match {
    case n if isBit(n) => "ctrl"
    case n if isWord(n) => "scal"
    case n if isVector(n) => "vec"
  }

  def linkTp(n:Link):String = {
    val tps = n.map(linkTp)
    assert(tps.toSet.size==1, s"link $n has tps = $tps")
    tps.head
  }

  def latencyOf(n:Node) = {
    globalOf(n).get match {
      case cuP:DramFringe => 100
      case cuP =>
        val cuS = cumap.mappedValue(cuP)
        val stages = cuS.collectDown[Stage]()
        Math.max(stages.size, 1)
    }
  }

  def staticLatencyOf(src:Node, dst:Node) = {
    1 //TODO: change for static network
  }

  override def quote(n:Any):String = n match {
    case n:Set[_] if n.forall(_.isInstanceOf[Memory]) => n.mkString("_") // Link
    case n:Iterable[_] => s"[${n.map(quote).mkString(",")}]"
    case n => super.quote(n)
  }

}
