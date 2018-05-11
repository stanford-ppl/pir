package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

trait Routing extends spade.util.NetworkAStarSearch with Debugger { self:PIRPass =>

  def searchMaxCost(start:GlobalIO, end:GlobalIO) = -1

  def markerOf(gio:GlobalIO) = gio match {
    case gio:GlobalInput => goutOf(gio).get
    case gio:GlobalOutput => gio
  }

  def portsS(gio:GlobalIO, cuS:Routable, pmap:PIRMap)
            (implicit portTp:PT => ClassTag[_<:PinType], gioTp:GlobalIO => ClassTag[_<:PinType]):List[PT] = {
    val marker = markerOf(gio)
    val markerTp = (gioTp(marker), isGlobalInput(gio))
    val ports = cuS.collectDown[PT]().filter { port => (portTp(port), isInput(port)) == markerTp }

    val (marked, unmarked) = ports.partition { port => pmap.mkmap.contains(port) }
    val markedAndMatched = marked.filter { port => pmap.mkmap(port) == marker }
    gio match {
      case gio:GlobalOutput => markedAndMatched ++ unmarked // one to many
      case gio:GlobalInput if markedAndMatched.nonEmpty => markedAndMatched // one to one
      case gio:GlobalInput => unmarked // one to one
    }
  }

  def tailToHead(pmap:PIRMap, start:GlobalIO)(tail:Edge):List[Edge] = {
    val marker = markerOf(start)
    dbgblk(s"tailToHead(tail=${quote(tail)},marker=${quote(marker)})",buffer=false) {
      val (marked, unmarked) = tail.connected.partition { head => pmap.mkmap.contains(head.src.asInstanceOf[PT]) }
      val markedAndMatched = marked.filter { head => pmap.mkmap(head.src.asInstanceOf[PT]) == marker }
      (tail match {
        case out:OutputEdge[_] => markedAndMatched ++ unmarked // one to many
        case in:InputEdge[_] if markedAndMatched.nonEmpty => markedAndMatched // one to one
        case in:InputEdge[_] => unmarked // one to one
      }).toList.asInstanceOf[List[Edge]]
    }
  }

  def span(
    start:GlobalIO, 
    pmap:PIRMap
  ):Seq[Routable] = dbgblk(s"span(${quote(start)}, ${spanMaxCost(start)})",buffer=true) {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val startTails = portsS(start, scuS, pmap)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    dbg(s"scuP=${quote(scuP)}, scuS=${quote(scuS)}")
    uniformCostSpan(
      start=startBundle, 
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, start) _,
        heuristic={ newState => 0 },
        maxCost=spanMaxCost(start)
      ) _,
    ).flatMap { case (bundle, cost) => 
      routableOf(bundle).get match {
        case sb:SwitchBox => None
        case routable => Some(routable)
      }
    }
  }

  def search[M](
    start:GlobalIO, 
    end:GlobalIO,
    pmap:PIRMap
  ):EOption[Route] = breakPoint(pmap) { dbgblk(s"search(headP=${quote(start)} tailP=${quote(end)} maxCost=${searchMaxCost(start, end)}",buffer=true) {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val ecuP = globalOf(end).get
    val ecuS = pmap.cumap.mappedValue(ecuP)
    val startTails = portsS(start, scuS, pmap)
    val endHeads = portsS(end, ecuS, pmap)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    val endBundle = endHeads.head.src.asInstanceOf[Bundle[_]]
    dbg(s"scuP=${quote(scuP)}, scuS=${quote(scuS)}")
    dbg(s"ecuP=${quote(ecuP)}, ecuS=${quote(ecuS)}")
    dbg(s"startTails=${startTails.map(quote)}")
    dbg(s"endHeads=${endHeads.map(quote)}")
    uniformCostSearch (
      start=startBundle, 
      end=endBundle,
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, start) _,
        heuristic=heuristic(ecuS) _,
        maxCost=searchMaxCost(start, end)
      ) _
    )
  } }

  def degree(cuP:GlobalContainer) = cuP.collectDown[GlobalIO]().size

  def spanMaxCost(start:GlobalIO) = {
    val startCU = globalOf(start).get
    (startCU, self.compiler.arch.topParam) match {
      case (startCU:pir.node.ArgFringe, param:MeshTopParam) => param.numRows / 2 + 2
      case (startCU:pir.node.DramFringe, param:MeshTopParam) => param.numCols / 2 + 2
      case (startCU, param:MeshTopParam) => 6 //TODO
      case _ => 3
    }
  }

  def spanMaxCost(tailP:GlobalIO, headP:GlobalIO) = {
    type ArgFringe = pir.node.ArgFringe
    type DramFringe = pir.node.DramFringe
    val scuP = globalOf(tailP).get
    val ecuP = globalOf(headP).get
    (scuP, ecuP, self.compiler.arch.topParam) match {
      case (scuP:ArgFringe  , ecuP            , param:MeshTopParam) => param.numRows / 2 + 2
      case (scuP            , ecuP:ArgFringe  , param:MeshTopParam) => param.numRows / 2 + 2
      case (scuP:DramFringe , ecuP            , param:MeshTopParam) => param.numCols / 2 + 2
      case (scuP            , ecuP:DramFringe , param:MeshTopParam) => param.numCols / 2 + 2
      case (scuP            , ecuP            , param:MeshTopParam) => 3
      case _ => throw PIRException(s"Don't know what is spanMaxCost")
    }
  }

  def route(cuP:CUMap.K, pmap:PIRMap):EOption[PIRMap] = breakPoint(pmap) { dbgblk(s"route(${quote(cuP)})",buffer=true) {
    val iosP = cuP.collectDown[GlobalIO]().toList
    //val edgesP = iosP.flatMap { case (pmap, tailP) => connectedOf(tailP).map { headP => (tailP, headP) } }
    //val (placed, unplaced) = edgesP.partition { case (tailP, headP) =>
      //pmap.cuMap.isMapped(globalOf(headP).head)
    //}
    //if (unplaced.nonEmpty) {
    //}

    flatFold(iosP, pmap) { case (pmap, tailP) =>
      val headsP = connectedOf(tailP)
      dbg(s"tailP:${quote(tailP)}")
      dbg(s"headsP:${headsP.map(quote)}")
      val existsUnplacedHeads = headsP.exists { headP => !pmap.cumap.isMapped(globalOf(headP).head) }
      val reached = if (existsUnplacedHeads) span(start=tailP,pmap=pmap) else Nil
      flatFold(headsP, pmap) { case (pmap, headP) =>
        route(tailP, headP, pmap, reached)
      }
    }
  } }

  def route(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap, reached:Seq[Routable]):EOption[PIRMap] = dbgblk(s"route(${quote(tailP)}, ${quote(headP)})", buffer=true) {
    val neighborP = globalOf(headP).head
    if (pmap.cumap.isMapped(neighborP)) {
      search(start=tailP,end=headP,pmap=pmap).flatMap { route => set(pmap, route, headP, tailP) }
    } else {
      pmap.flatMap[CUMap]{ cumap =>
        val filtered = cumap.filterNotAt(neighborP) { cuS => !reached.contains(cuS) }
        log(filtered.map { cumap => s"neighborP=${quote(neighborP)}: ${cumap(neighborP).map(quote)}" })
        filtered
      }
    }
  }

  def set(fimap:FIMap, tail:Edge, head:Edge):FIMap = {
    dbg(s"setFIMap: ${quote(tail.src)}.${quote(tail)} - ${quote(head.src)}.${quote(head)}")
    (tail, head) match {
      case (tail:FIMap.K, head:FIMap.V) =>
        fimap + (tail, head)
      case (tail:FIMap.V, head:FIMap.K) =>
        fimap + (head, tail)
      case (tail, head) => throw PIRException(s"impossible case $tail, $head")
    }
  }

  def set(mkmap:MKMap, port:PT, marker:GlobalOutput):MKMap = {
    dbg(s"setMKMap: ${quote(port.src)}.${quote(port)} - ${quote(marker)}")
    mkmap + (port, marker)
  }

  def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=true){
    dbg(s"route:")
    dbg(quote(route))
    Right(pmap).map { pmap =>
      pmap.map[FIMap] { fimap =>
        var fm = fimap
        route.iterator.sliding(size=2,step=1).foreach {
          case List((tail1S, head1S), (tail2S, head2S)) =>
            fm = set(fm, tail1S.external, head1S.external)
            fm = set(fm, head1S.internal, tail2S.internal)
          case List((tail1S, head1S)) =>  // If only 1 element in route
        }
        val (tailS, headS) = route.last
        fm = set(fm, tailS.external, headS.external)
        fm
      }
    }.map { pmap =>
      pmap.map[MKMap] { mkmap =>
        var mk = mkmap
        val marker = markerOf(headP)
        route.foreach { case (tailS, headS) =>
          mk = set(mk, tailS, marker)
          mk = set(mk, headS, marker)
        }
        mk
      }
    }
  }

  override def quote(n:Any) = n match {
    case n:GlobalIO => s"${globalOf(n).get}.${super.quote(n)}"
    case n:PT => s"${quote(n.src)}.${n}"
    case n => super.quote(n)
  }

}
