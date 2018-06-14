package pir
package pass

import pir.node._
import pir.mapper._
import spade.node._
import spade.param._

trait PlastisimUtil extends PIRPass {
  import pirmeta._
  import spademeta._

  type NetworkNode = ContextEnable
  type Link = linkGroupOf.V

  lazy val topS = compiler.arch.design.top

  lazy val (numRows, numCols) = compiler.arch.designParam.topParam match {
    case param:StaticGridTopParam => (param.numRows, param.numCols)
    case param:DynamicGridTopParam => (param.numTotalRows, param.numTotalCols)
    case param:StaticCMeshTopParam => (param.numRows, param.numCols)
    case param:DynamicCMeshTopParam => (param.numTotalRows, param.numTotalCols)
  }

  def cumap = pirMap.right.get.cumap
  def vcmap = pirMap.right.get.vcmap

  def srcsOf(mem:Memory):List[NetworkNode] = dbgblk(s"srcsOf(${quote(mem)})") {
    def visitFunc(n:PIRNode) = visitGlobalIn(n).filterNot {
      case n:Memory => true
      case _ => false
    }
    mem.collect[NetworkNode](visitFunc=visitFunc _)
  }

  def srcsOf(n:Link):List[NetworkNode] = {
    n.toList.flatMap { mem => srcsOf(mem) }
  }

  def dstsOf(mem:Memory):List[NetworkNode] = dbgblk(s"dstsOf(${quote(mem)})"){
    def visitFunc(n:PIRNode) = visitGlobalOut(n).filterNot {
      case n:Memory => true
      case n:NotFull => true
      case _ => false
    }
    mem.collect[NetworkNode](visitFunc=visitFunc _)
  }

  def dstsOf(n:Link):List[NetworkNode] = {
    n.toList.flatMap { mem => dstsOf(mem) }
  }

  def inlinksOf(n:NetworkNode) = {
    loadAccessesOf(n).groupBy { read =>
      val mem::Nil = memsOf(read) // All mems should belongs to the same linkGroup
      linkGroupOf(mem)
    }.toSeq
  }

  def outlinksOf(n:NetworkNode) = {
    (storeAccessesOf(n) ++ resetAccessesOf(n)).groupBy { write =>
      val mem::Nil = memsOf(write) // All mems should belongs to the same linkGroup
      linkGroupOf(mem)
    }.toSeq
  }

  // Convert coordinate to linear index
  def addrOf(sn:SNode):Option[Int] = {
    indexOf.get(sn).map { case List(x,y) =>
      val idx = y * numCols + x
      dbg(s"$sn: coord = ($x, $y) idx = $idx")
      idx
    }
  }

  def addrOf(node:NetworkNode):Option[Int] = {
    val cuP = globalOf(node).get
    cumap.usedMap.get(cuP).flatMap { cuS => addrOf(cuS) }
  }

  def isStaticLink(mem:Memory):Boolean = {
    assertUnify(writersOf(mem), "isStaticLink"){
      case Def(n, LocalStore(_, _, data:GlobalInput)) => 
        val port = mappedTo[MKMap.K](data).get
        isStaticLink(port)
      case Def(n, LocalStore(_, _, data)) => true // Local edge
    }
  }

  def isStaticLink(n:Link):Boolean = {
    topS match {
      case topS if isAsic(topS) => true
      case topS if isStatic(topS) => true
      case topS if isDynamic(topS) =>
        assertUnify(n, "isStaticLink") { mem => isStaticLink(mem) }
    }
  }

  def bufferSizeOf(memP:Memory, cuP:GlobalContainer, cuS:Routable) = {
    cuS match {
      case cuS:MC =>
        val isLoad = isLoadFringe(cuP)
        val isStore = isStoreFringe(cuP)
        val isStream = isStreamFringe(cuP)
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
          case memP:StreamOut if memP.field == "data" & isStream =>
            cuS.param.sDataFifoParam.size
        }
      case cuS:CU =>
        memP match {
          case memP if isFIFO(memP) =>
            memP match {
              case memP if isBit(memP) => cuS.param.controlFifoParam.size
              case memP if isWord(memP) => cuS.param.scalarFifoParam.size
              case memP if isVector(memP) => cuS.param.vectorFifoParam.size
            }
          case memP if isRemoteMem(memP) => cuS.param.sramParam.depth
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

  def constItersOf(x:PIRNode):Long = {
    getItersOf(x).getOrElse(throw PIRException(s"Cannot constant propogate itersOf($x)"))
  }

  def constCountsOf(x:PIRNode):Long = {
    getCountsOf(x).getOrElse(throw PIRException(s"Cannot constant propogate countsOf($x)"))
  }

  def constItersOf(accesses:List[LocalAccess]):Long = {
    assertUnify(accesses, "iters") { access => constItersOf(access) }
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

  def latencyOf(n:NetworkNode):Option[Int] = {
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

  def ginFrom(src:NetworkNode, mem:Memory):Option[GlobalInput] = {
    val writers = writersOf(mem)
    val (globalWriters, localWriters) = writers.partition { writer => 
      dataOf(writer).isInstanceOf[GlobalInput] 
    }
    if (localWriters.nonEmpty && globalWriters.isEmpty) {
      None
    } else if (localWriters.isEmpty && globalWriters.nonEmpty) {
      val gins = globalWriters.map {
        case Def(n, LocalStore(_, _, data:GlobalInput)) => data
      }.filter { gin => goutOf(gin).get.collectPeer[NetworkNode]().contains(src) }
      Some(assertUnify(gins, s"$mem's GlobalInput from $src") { gin => gin })
    } else {
      throw PIRException(s"mem=$mem globalWriters=$globalWriters, localWriters=$localWriters")
    }
  }

  def staticLatencyOf(src:NetworkNode, mem:Memory):Option[Int] = {
    topS match {
      case topS if isAsic(topS) => Some(1)
      case topS => 
        ginFrom(src, mem) match {
          case Some(gin) => staticLatencyOf(gin)
          case None => Some(1) // Local Link
        }
    }
  }

  def staticLatencyOf(gin:GlobalInput):Option[Int] = {
    mappedTo[MKMap.K](gin).flatMap { port =>
      if (isStaticLink(port)) {
        val gout = goutOf(gin).get
        val routes = mappedTo[List[(MKMap.K,MKMap.K)]]((gin, gout)).get
        Some(routes.size)
      } else None
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
