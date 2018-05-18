package pir
package pass

import pir.node._
import spade.node._

trait PlastisimUtil extends PIRPass {
  import pirmeta._
  import spademeta._

  type Node = ContextEnable
  type Link = linkGroupOf.V

  lazy val topS = compiler.arch.design.top

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
    val topParam = compiler.arch.topParam.asInstanceOf[DynamicMeshTopParam]
    import topParam._
    indexOf.get(sn).map { case List(x,y) =>
      val idx = y * numTotalCols + x
      dbg(s"$sn: coord = ($x, $y) idx = $idx")
      idx
    }
  }

  def addrOf(node:Node):Option[Int] = {
    val cuP = globalOf(node).get
    cumap.usedMap.get(cuP).flatMap { cuS => addrOf(cuS) }
  }

  def isStaticLink(src:Node, dst:Node):Boolean = {
    topS match {
      case topS if isDynamic(topS) =>
        val srcCUP = globalOf(src).get
        val dstCUP = globalOf(dst).get
        val isStatic = srcCUP == dstCUP
        dbg(s"isStatic($src($srcCUP), $dst($dstCUP)) = $isStatic")
        isStatic
      case topS if isStatic(topS) => true
      case topS if isAsic(topS) => true
    }
  }

  def isStaticLink(n:Link):Boolean = {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)
    val pairs = srcs.flatMap{ src => dsts.map { dst => (src, dst) } }
    assertUnify(pairs, "isStatic"){ case (src, dst) => isStaticLink(src, dst) }
  }

  
  def bufferSizeOf(memP:Memory, cuP:GlobalContainer, cuS:Routable) = {
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

  def bufferSizeOf(memP:Memory):Option[Int] = {
    val cuP = globalOf(memP).get
    topS match {
      case topS if isAsic(topS) => Some(10) // as large as possible
      case topS if cumap.isMapped(cuP) =>
        val cuS = cumap(cuP).head
        Some(bufferSizeOf(memP, cuP, cuS))
      case topS => None
    }
  }

  def bufferSizeOf(reads:List[LocalLoad]):Option[Int] = {
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

  def latencyOf(n:Node):Option[Int] = {
    val cuP = globalOf(n).get
    topS match {
      case topS if isAsic(topS) => Some(Math.max(1, cuP.collectDown[StageDef]().size))
      case topS if cumap.isMapped(cuP) =>
        cumap.mappedValue(cuP) match {
          case cuS:MC => Some(100)
          case cuS:CU => Some(cuS.param.simdParam.map { _.stageParams.size }.getOrElse(1))
        }
      case topS => None
    }
  }

  def staticLatencyOf(src:Node, dst:Node):Option[Int] = {
    topS match {
      case topS if isAsic(topS) => Some(1)
      case topS if isDynamic(topS) && isStaticLink(src, dst)=> Some(1) //TODO
      case topS if isDynamic(topS) => None
      //case topS if isStatic(topS) => //TODO
        //val scuP = globalOf(src).get
        //val dcuP = globalOf(dst).get
        //val mem = link.filter { mem => globalOf(mem).get == dcuP }.head
        //mem.collectInTillMem[GlobalInput]().headOption
    }
  }

  override def quote(n:Any):String = n match {
    case n:Set[_] if n.forall(_.isInstanceOf[Memory]) => n.mkString("_") // Link
    case n:ClassTag[_] if isBit(n.asInstanceOf[ClassTag[_<:PinType]]) => "ctrl"
    case n:ClassTag[_] if isWord(n.asInstanceOf[ClassTag[_<:PinType]]) => "scal"
    case n:ClassTag[_] if isVector(n.asInstanceOf[ClassTag[_<:PinType]]) => "vec"
    case n => super.quote(n)
  }
}
