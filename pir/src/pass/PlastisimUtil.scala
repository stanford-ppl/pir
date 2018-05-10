package pir
package pass

import pir.node._
import spade.node._

trait PlastisimUtil extends PIRPass {
  import pirmeta._
  import spademeta._

  type Node = ContextEnable
  type Link = linkGroupOf.V

  lazy val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]
  def cumap = pirMap.right.get.cumap
  def vcmap = pirMap.right.get.vcmap

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

  def inAccessOf(n:Node) = {
    n.collectOutTillMem[LocalLoad]() //reads enabled by this contextEnable
  }

  def inMemsOf(n:Node) = {
    val reads = inAccessOf(n)
    reads.flatMap { read => memsOf(read) }
  }

  def inlinksOf(n:Node) = {
    val reads = inAccessOf(n)
    reads.groupBy { read =>
      val mem::Nil = memsOf(read) // All mems should belongs to the same linkGroup
      linkGroupOf(mem)
    }.toSeq
  }

  def outAccessOf(n:Node) = {
    n.collectOutTillMem[LocalStore]() // writes enabled by this contextEnable
  }

  def outMemsOf(n:Node) = {
    val writes = outAccessOf(n)
    writes.flatMap { write => memsOf(write) }
  }

  def outlinksOf(n:Node) = {
    val writes = n.collectOutTillMem[LocalStore]() // writes enabled by this contextEnable
    writes.groupBy { write =>
      val mem::Nil = memsOf(write) // All mems should belongs to the same linkGroup
      linkGroupOf(mem)
    }.toSeq
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
    val pairs = srcs.flatMap{ src => dsts.map { dst => (src, dst) } }
    assertUnify(pairs, "isStatic"){ case (src, dst) => isStaticLink(src, dst) }
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
  
  def bufferSizeOf(reads:List[LocalLoad]):Int = {
    assertUnify(reads, "bufferSize") { read => val mem::Nil = memsOf(read); bufferSizeOf(mem) }
  }

  def constItersOf(x:Any):Long = {
    getItersOf(x).getOrElse(throw PIRException(s"Cannot constant propogate itersOf($x)"))
  }

  def constCountsOf(x:Any):Long = {
    getCountsOf(x).getOrElse(throw PIRException(s"Cannot constant propogate countsOf($x)"))
  }

  def constItersOf(accesses:List[LocalAccess]):Long = {
    assertUnify(accesses, "iter") { access => constItersOf(access) }
  }

  def constCountsOf(accesses:List[LocalAccess]):Long = {
    assertUnify(accesses, "counts") { access => constCountsOf(access) }
  }

  def getItersOf(accesses:List[LocalAccess]):Option[Long] = {
    assertUnify(accesses, "iter") { access => getItersOf(access) }
  }

  def getCountsOf(accesses:List[LocalAccess]):Option[Long] = {
    assertUnify(accesses, "counts") { access => getCountsOf(access) }
  }

  def pinTypeOf(link:Link):ClassTag[_<:PinType] = assertUnify(link, "tp")(mem => pinTypeOf(mem))

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
    case n:ClassTag[_] if isBit(n.asInstanceOf[ClassTag[_<:PinType]]) => "ctrl"
    case n:ClassTag[_] if isWord(n.asInstanceOf[ClassTag[_<:PinType]]) => "scal"
    case n:ClassTag[_] if isVector(n.asInstanceOf[ClassTag[_<:PinType]]) => "vec"
    case n => super.quote(n)
  }
}
