package pir
package pass

import pir.node._
import pir.mapper._
import spade.node._
import spade.param._

trait PlastisimUtil extends PIRPass with prism.util.Memorization {
  import pirmeta._
  import spademeta._

  type NetworkNode = ContextEnable
  type Link = linkGroupOf.V

  override def initPass = {
    super.initPass
    memorizing = true
    resetAllCaches
  }

  lazy val topS = compiler.arch.design.top
  lazy val topParam = topS.param
  lazy val burstSize = topParam.burstSizeWord

  lazy val (numTotalRows, numTotalCols) = topParam match {
    case param:StaticGridTopParam => (param.numRows, param.numCols)
    case param:DynamicGridTopParam => (param.numTotalRows, param.numTotalCols)
    case param:StaticCMeshTopParam => (param.numRows, param.numCols)
    case param:DynamicCMeshTopParam => (param.numTotalRows, param.numTotalCols)
  }

  lazy val (numRows, numCols) = topParam match {
    case param:StaticGridTopParam => (param.numRows, param.numCols)
    case param:DynamicGridTopParam => (param.numRows, param.numCols)
    case param:StaticCMeshTopParam => (param.numRows, param.numCols)
    case param:DynamicCMeshTopParam => (param.numRows, param.numCols)
  }

  lazy val maxDim = math.max(numTotalRows, numTotalCols)

  def pmap = pirMap.toOption

  def srcsOf(n:Link):Map[Memory, Map[NetworkNode, List[LocalInAccess]]] = {
    n.map { mem => mem -> inAccessesOf(mem).groupBy { a => ctxEnOf(a).get } }.toMap
  }

  def dstsOf(n:Link):Map[Memory, Map[NetworkNode, List[LocalOutAccess]]] = {
    n.map { mem => mem -> outAccessesOf(mem).groupBy { a => ctxEnOf(a).get } }.toMap
  }

  def inMemsOf(n:NetworkNode):Map[Memory, List[LocalAccess]] = inMemsOf(contextOf(n).get)
  def outMemsOf(n:NetworkNode):Map[Memory, List[LocalAccess]] = outMemsOf(contextOf(n).get)

  def inlinksOf(n:NetworkNode):Map[Link, List[LocalAccess]] = {
    inMemsOf(n).groupBy { case (mem, accesses) => linkGroupOf(mem) }.map { case (link, map) =>
      link -> map.values.flatten.toList
    }
  }

  def outlinksOf(n:NetworkNode):Map[Link, List[LocalAccess]] = {
    outMemsOf(n).groupBy { case (mem, accesses) => linkGroupOf(mem) }.map { case (link, map) =>
      link -> map.values.flatten.toList
    }
  }

  override def inAccessesOf(mem:Memory):List[LocalInAccess] = super.inAccessesOf(mem).filterNot { a => isMuted(a) }
  override def outAccessesOf(mem:Memory):List[LocalOutAccess] = super.outAccessesOf(mem).filterNot { a => isMuted(a) }
  override def localAccessesOf(n:ComputeContext) = super.localAccessesOf(n).filterNot(a => isMuted(a))
  override def remoteAccessesOf(n:ComputeContext) = super.remoteAccessesOf(n).filterNot(a => isMuted(a))

  def startAtToken(n:NetworkNode) = globalOf(n).get match {
    case FringeStreamIn(streamIn) => countOf.get(streamIn).flatten
    case cuP:pir.node.ArgFringe if ctrlOf(n).isInstanceOf[ArgInController] => Some(1)
    case cuP =>
      val token = inMemsOf(n).flatMap {
        case (mem, accesses) if isAccum(mem) && inAccessesOf(mem).size <= 1 => Some(constScaleOf(accesses))
        case (mem, accesses) => None
      }
      assertOneOrLess(token, s"token")
  }

  def stopAfterToken(n:NetworkNode) = globalOf(n).get match {
    case FringeStreamOut(streamOut) => countOf.get(streamOut).flatten
    case cuP:pir.node.ArgFringe if ctrlOf(n).isInstanceOf[ArgOutController] => Some(1)
    case cuP => None
  }

  /*
   * (r,0) .... (r, c)
   *   .          .       
   *   .          .       
   *   .          .       
   * (0,0) .... (c, 0)
   *
   * To
   *
   * 0       ....   c-1 
   * c       ....   2r-1 
   * .         .
   * .         .
   * (r-1)*c ....   (r-1)*c-1
   * */
  // Convert coordinate to linear index
  def addrOf(sn:SNode):Option[Int] = topS match {
    case topS:StaticGridTop => Some(0)
    case topS:DynamicGridTop =>
      indexOf.get(sn).map { case List(x,y) =>
        val idx = (numTotalRows-1-y) * maxDim + x
        dbg(s"$sn: coord = ($x, $y) idx = $idx")
        idx
      }
  }

  def addrOf(cuP:GlobalContainer):Option[Int] = {
    mappedTo[Routable](cuP, pmap).flatMap { cuS => addrOf(cuS) }
  }

  def addrOf(node:NetworkNode):Option[Int] = {
    val cuP = globalOf(node).get
    addrOf(cuP)
  }

  def isStaticLink(mem:Memory):Boolean = {
    assertUnify(inAccessesOf(mem), "isStaticLink") {
      case Def(n, LocalStore(_, _, data:GlobalInput)) => 
        val port = mappedTo[MKMap.K](data).get
        isStaticLink(port)
      case Def(n, LocalStore(_, _, data)) => true // Local edge
      case Def(n, LocalReset(_, reset:GlobalInput)) =>
        val port = mappedTo[MKMap.K](reset).get
        isStaticLink(port)
    }.get
  }

  def isStaticLink(n:Link):Boolean = {
    topS match {
      case topS if isAsic(topS) => true
      case topS if isPointToPoint(topS) => true
      case topS if isStatic(topS) => true
      case topS if isDynamic(topS) =>
        assertUnify(n, "isStaticLink") { mem => isStaticLink(mem) }.get
    }
  }

  def isLocalLink(mem:Memory):Boolean = {
    assertUnify(inAccessesOf(mem), "isStaticLink"){
      case Def(n, LocalStore(_, _, data:GlobalInput)) => false
      case Def(n, LocalStore(_, _, data)) => true // Local edge
      case Def(n, LocalReset(_, reset:GlobalInput)) => false
      case Def(n, LocalReset(_, reset)) => true
    }.get
  }

  def isLocalLink(n:Link):Boolean = assertUnify(n, s"isLocalLink"){ mem => isLocalLink(mem) }.get

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
          case memP:StreamOut if memP.field == "addr" & isLoad =>
            cuS.param.rOffsetFifoParam.size
          case memP:StreamOut if memP.field == "size" & isStore =>
            cuS.param.wSizeFifoParam.size
          case memP:StreamOut if memP.field == "offset" & isStore =>
            cuS.param.wOffsetFifoParam.size
          case memP:StreamOut if memP.field == "addr" & isStore =>
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
      case topS =>
        mappedTo[Routable](cuP, pmap).map { cuS => bufferSizeOf(memP, cuP, cuS) }
    }
  }

  def constScaleOf(x:PIRNode):Long = {
    getScaleOf(x).getOrElse(throw PIRException(s"Cannot constant propogate itersOf($x)"))
  }

  def constCountsOf(x:PIRNode):Long = {
    getCountsOf(x).getOrElse(throw PIRException(s"Cannot constant propogate countOf($x)"))
  }

  def constScaleOf(accesses:List[LocalAccess]):Long = {
    assertUnify(accesses, "iters") { access => constScaleOf(access) }.get
  }

  def constCountsOf(accesses:List[LocalAccess]):Long = {
    assertUnify(accesses, "count") { access => constCountsOf(access) }.get
  }

  def getScaleOf(accesses:List[LocalAccess]):Option[Long] = {
    assertIdentical(accesses.flatMap { a => getScaleOf(a)}, "iter")
  }

  def getCountsOf(accesses:List[LocalAccess]):Option[Long] = {
    assertIdentical(accesses.flatMap { a => getCountsOf(a)}, "count")
  }

  override def pinTypeOf(n:PIRNode):ClassTag[_<:PinType] = memorize("pinTypeOf", n) { n => super.pinTypeOf(n) }

  def pinTypeOf(link:Link):ClassTag[_<:PinType] = assertUnify(link, "tp")(mem => pinTypeOf(mem)).get

  def latencyOf(n:NetworkNode):Option[Int] = {
    val cuP = globalOf(n).get
    topS match {
      case topS if isAsic(topS) => Some(Math.max(1, cuP.collectDown[StageDef]().size))
      case topS => None
        mappedTo[Routable](cuP, pmap).map {
          case cuS:MC => 100
          case cuS:CU => cuS.param.simdParam.map { _.stageParams.size }.getOrElse(1)
          case cuS:spade.node.ArgFringe => 1
        }
    }
  }

  // HACK: get global output of link
  def goutOf(n:Link):Option[Def] = assertOptionUnify(n, s"write data of $n") { mem =>
    assertOptionUnify(inAccessesOf(mem), s"write data of $n") {
      case Def(n, LocalStore(mems, addr, data:GlobalInput)) => Some(goutOf(data).get)
      case Def(n, LocalReset(mems, reset:GlobalInput)) => Some(goutOf(reset).get)
      case n => None
    }
  }

  def ginFrom(src:NetworkNode, mem:Memory):Option[GlobalInput] = {
    val (globalWriters, localWriters) = inAccessesOf(mem).partition {
      case Def(_,LocalStore(_, _, data:GlobalInput)) => true
      case Def(_,LocalReset(_, reset:GlobalInput)) => true
      case _ => false
    }
    if (localWriters.nonEmpty && globalWriters.isEmpty) {
      None
    } else if (localWriters.isEmpty && globalWriters.nonEmpty) {
      val gins = globalWriters.map {
        case Def(_,LocalStore(_, _, data:GlobalInput)) => data
        case Def(_,LocalReset(_, reset:GlobalInput)) => reset
      }.filter { gin => goutOf(gin).get.collectPeer[NetworkNode]().contains(src) }
      Some(assertIdentical(gins, s"$mem's GlobalInput from $src").get )
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
    topS match {
      case topS if isPointToPoint(topS) | isAsic(topS) => Some(1)
      case topS =>
        mappedTo[MKMap.K](gin, pmap).flatMap { port =>
          val gout = goutOf(gin).get
          val routes = mappedTo[List[(MKMap.K,MKMap.K)]]((gin, gout)).get
          Some(routes.size)
        }
    }
  }

  override def quote(n:Any):String = n match {
    case n:ContextEnable => s"CE${n.id}"
    case n:Set[_] if n.forall(_.isInstanceOf[Memory]) => n.mkString("_") // Link
    case n:ClassTag[_] if isBit(n.asInstanceOf[ClassTag[_<:PinType]]) => "ctrl"
    case n:ClassTag[_] if isWord(n.asInstanceOf[ClassTag[_<:PinType]]) => "scal"
    case n:ClassTag[_] if isVector(n.asInstanceOf[ClassTag[_<:PinType]]) => "vec"
    // TODO: Not sure why this in RoutingUtil no work
    case n:Bundle[_] => s"${routableOf(n).get}.$n"
    case n:Port[_] => s"${quote(n.src)}.$n"
    case n:Edge => s"${quote(n.src)}.$n"
    case n:GlobalIO => s"${globalOf(n).get}.${super.quote(n)}"
    case n:GlobalContainer => s"${globalOf(n).get}(${cuType(n).get})"
    case (a,b) => s"(${quote(a)},${quote(b)})"
    case n => super.quote(n)
  }


  def checkActive(node:NetworkNode):Option[(Long, Long)] = {
    zipOption(countOf.getOrElse(node,None), activeOf.get(node)).map { case (count, active) =>
      val expectedCount = globalOf(node).get match {
        case cuP:FringeDenseLoad =>
          val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
          count / (burstSize / par)
        case cuP => count
      }
      (active, expectedCount)
    }
  }
}
