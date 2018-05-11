package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

trait Routing extends spade.util.NetworkAStarSearch with Debugger { self:PIRPass =>

  def searchMaxCost(start:GlobalIO, end:GlobalIO) = -1

  def spanMaxCost(start:GlobalIO) = {
    val startCU = globalOf(start).get
    (startCU, self.compiler.arch.topParam) match {
      case (startCU:pir.node.ArgFringe, param:MeshTopParam) => param.numRows / 2 + 2
      case (startCU:pir.node.DramFringe, param:MeshTopParam) => param.numCols / 2 + 2
      case (startCU, param:MeshTopParam) => 3
      case _ => 3
    }
  }

  def addIOs(pmap:PIRMap, cuP:CUMap.K):PIRMap = dbgblk(s"addIOs(${quote(cuP)})",buffer=true) {
    val iosP = cuP.collectDown[GlobalIO]()
    val cuS = pmap.cumap.mappedValue(cuP)
    val insP = iosP.collect{ case io:InMap.K => io }.toSet[InMap.K]
    val outsP = iosP.collect{ case io:OutMap.K => io }.toSet[OutMap.K]
    val iosS = cuS.collectDown[PT]()
    val insS = iosS.collect{ case io:InMap.V => io }.toSet[InMap.V]
    val outsS = iosS.collect{ case io:OutMap.V => io }.toSet[OutMap.V]
    pmap.map[InMap] { inmap =>
      addIOs(inmap, insP, insS)
    }.map[OutMap] { outmap =>
      addIOs(outmap, outsP, outsS)
    }
  }

  def addIOs[K,V,FG<:FactorGraphLike[K,V,FG]](
    fg:FG, 
    keys:Set[K], 
    vals:Set[V]
  )(implicit f1:K => ClassTag[_<:PinType], f2:V => ClassTag[_<:PinType]):FG = {
    val kgrp = keys.groupBy { k => f1(k) }
    val vgrp = vals.groupBy { v => f2(v) }
    (kgrp.keys.toSet ++ vgrp.keys.toSet).foldLeft[FG](fg) { case (fg, tp) =>
      val iosP = kgrp.getOrElse(tp, Set[K]())
      val iosS = vgrp.getOrElse(tp, Set[V]())
      fg ++ (iosP -> iosS)
    }
  }

  def markerOf(gio:GlobalIO) = gio match {
    case gio:GlobalInput => goutOf(gio).get
    case gio:GlobalOutput => gio
  }

  def portsS(port:GlobalIO, pmap:PIRMap) = {
    port match {
      case port:GlobalInput => pmap.inmap(port).toList.asInstanceOf[List[PT]]
      case port:GlobalOutput => pmap.outmap(port).toList.asInstanceOf[List[PT]]
    }
  }

  def tailToHead(pmap:PIRMap, endHeads:Option[List[PT]], marker:GlobalOutput)(tail:Edge) = dbgblk(s"tailToHead(${quote(tail)})",buffer=false) {
    dbg(s"endHeads=${quote(endHeads)}")
    val heads = (tail match {
      case out:FIMap.V => out.connected.filter { in =>
        pmap.fimap.get(in).fold(true) { _ == out }
      }
      case in:FIMap.K if pmap.fimap.contains(in) => List(pmap.fimap(in))
      case in:FIMap.K => in.connected.filter { out =>
        pmap.mkmap.get(out.src.asInstanceOf[PT]).fold(true) { _ == marker }
      }
    }).toList.asInstanceOf[List[Edge]]
    dbg(s"validHeads=${quote(heads)}")
    endHeads.fold {
      heads
    } { endHeads =>
      val filteredHeads = heads.filter { head =>
        routableOf(head.src).get match {
          case rt:SwitchBox => true
          case rt:Routable => endHeads.contains(head.src)
        }
      }
      dbg(s"filteredByEndHeads=${quote(filteredHeads)}")
      filteredHeads
    }
  }

  def span(
    start:GlobalIO, 
    pmap:PIRMap
  ):Seq[Routable] = dbgblk(s"span(${quote(start)}, ${spanMaxCost(start)})",buffer=true) {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val startTails = portsS(start, pmap)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    val marker = markerOf(start)
    dbg(s"scuP=${quote(scuP)}, scuS=${quote(scuS)}")
    uniformCostSpan(
      start=startBundle, 
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, None, marker) _,
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
    val startTails = portsS(start, pmap)
    val endHeads = portsS(end, pmap)
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val ecuP = globalOf(end).get
    val ecuS = pmap.cumap.mappedValue(ecuP)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    val endBundle = endHeads.head.src.asInstanceOf[Bundle[_]]
    val marker = markerOf(start)
    dbg(s"scuP=${quote(scuP)}, scuS=${quote(scuS)}")
    dbg(s"ecuP=${quote(ecuP)}, ecuS=${quote(ecuS)}")
    dbg(s"startTails=${startTails.map(quote)}")
    dbg(s"endHeads=${endHeads.map(quote)}")
    uniformCostSearch (
      start=startBundle, 
      end=endBundle,
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, Some(endHeads), marker) _,
        heuristic=heuristic(ecuS) _,
        maxCost=searchMaxCost(start, end)
      ) _
    )
  } }

  def route(cuP:CUMap.K, pmap:PIRMap):EOption[PIRMap] = breakPoint(pmap) { dbgblk(s"route(${quote(cuP)})",buffer=true) {
    val iosP = cuP.collectDown[GlobalIO]().toList
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

  def set(pmap:PIRMap, portP:GlobalIO, portS:PT) = {
    dbg(s"setPort: ${quote(portP)} -> ${quote(portS)}")
    (portP, portS) match {
      case (portP:InMap.K, portS:InMap.V) => pmap.flatMap[InMap] { _.set(portP, portS) }
      case (portP:OutMap.K, portS:OutMap.V) => pmap.flatMap[OutMap] { _.set(portP, portS) }
      case (portP, portS) => throw PIRException(s"impossible case $portP, $portS")
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
    Right(pmap).flatMap { pmap =>
      val (tailS,_) = route.head
      set(pmap, tailP, tailS)
    }.flatMap { pmap =>
      val (_,headS) = route.last
      set(pmap, headP, headS)
    }.map { pmap =>
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
