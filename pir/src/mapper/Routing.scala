package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

trait Routing extends spade.util.NetworkAStarSearch { self:PIRPass =>

  def routingVerbosity:Int = PIRConfig.routingVerbosity

  def searchMaxCost(start:GlobalIO, end:GlobalIO) = -1

  def spanMaxCost(start:GlobalIO) = {
    val startCU = globalOf(start).get
    (startCU, self.compiler.arch.topParam) match {
      case (startCU:pir.node.ArgFringe, param:MeshTopParam) => param.numRows / 2 + 2
      case (startCU:pir.node.FringeContainer, param:MeshTopParam) => param.numCols / 2 + 2
      case (startCU, param:MeshTopParam) => 3
      case _ => 3
    }
  }

  def addIOs(pmap:PIRMap, cuP:CUMap.K):PIRMap = dbgblk(1, s"addIOs(${quote(cuP)})") {
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

  def portsS(port:GlobalIO, pmap:PIRMap) = port match {
    case port:GlobalInput => pmap.inmap(port).toList.asInstanceOf[List[PT]]
    case port:GlobalOutput => pmap.outmap(port).toList.asInstanceOf[List[PT]]
  }

  def tailToHead(pmap:PIRMap, scuS:Routable, endHeads:Option[List[PT]])(tail:Edge) = {
    val heads = (tail match {
      case tail:FIMap.V => tail.connected
      case tail:FIMap.K if pmap.fimap.contains(tail) => List(pmap.fimap(tail))
      case tail:FIMap.K => tail.connected
    }).toList.asInstanceOf[List[Edge]]
    endHeads.fold {
      heads
    } { endHeads =>
      heads.filter { head =>
        routableOf(head.src).get match {
          case rt:SwitchBox => true
          case rt:Routable => endHeads.contains(head.src)
        }
      }
    }
  }

  def span(
    start:GlobalIO, 
    pmap:PIRMap
  ):Seq[Routable] = dbgblk(2, s"span(${quote(start)}, ${spanMaxCost(start)})") {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val startTails = portsS(start, pmap)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    dbg(s"scuS=${quote(scuS)}")
    uniformCostSpan(
      start=startBundle, 
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, scuS, None) _,
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
  ):EOption[Route] = dbgblk(2, s"search(headP=${quote(start)} tailP=${quote(end)} maxCost=${searchMaxCost(start, end)}") {
    val startTails = portsS(start, pmap)
    val endHeads = portsS(end, pmap)
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val ecuP = globalOf(end).get
    val ecuS = pmap.cumap.mappedValue(ecuP)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    val endBundle = endHeads.head.src.asInstanceOf[Bundle[_]]
    dbg(s"scuS=${quote(scuS)}")
    dbg(s"ecuS=${quote(ecuS)}")
    dbg(s"endHeads=${endHeads.map(quote)}")
    uniformCostSearch (
      start=startBundle, 
      end=endBundle,
      advance=advance(
        startTails=startTails,
        tailToHead=tailToHead(pmap, scuS, Some(endHeads)) _,
        heuristic=heuristic(ecuS) _,
        maxCost=searchMaxCost(start, end)
      ) _
    )
  }

  def route(cuP:CUMap.K, pmap:PIRMap):EOption[PIRMap] = {
    val iosP = cuP.collectDown[GlobalIO]().toList
    flatFold(iosP, pmap) { case (pmap, tailP) =>
      val headsP = connectedOf(tailP)
      dbg(1, s"tailP:${quote(tailP)}")
      dbg(1, s"headsP:${headsP.map(quote)}")
      val existsUnplacedHeads = headsP.exists { headP => !pmap.cumap.isMapped(globalOf(headP).head) }
      val reached = if (existsUnplacedHeads) span(start=tailP,pmap=pmap) else Nil
      flatFold(headsP, pmap) { case (pmap, headP) =>
        route(tailP, headP, pmap, reached)
      }
    }
  }

  def route(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap, reached:Seq[Routable]):EOption[PIRMap] = {
    val neighborP = globalOf(headP).head
    if (pmap.cumap.isMapped(neighborP)) {
      search(start=tailP,end=headP,pmap=pmap).flatMap { route => set(pmap, route, headP, tailP) }
    } else {
      pmap.flatMap[CUMap]{ cumap =>
        val filtered = cumap.filterNot(neighborP) { cuS => !reached.contains(cuS) }
        log(1, filtered.map { cumap => s"neighborP=${quote(neighborP)}: ${cumap(neighborP).map(quote)}" })
        filtered
      }
    }
  }

  def set(pmap:PIRMap, portP:GlobalIO, portS:PT) = {
    (portP, portS) match {
      case (portP:InMap.K, portS:InMap.V) => pmap.flatMap[InMap] { _.set(portP, portS) }
      case (portP:OutMap.K, portS:OutMap.V) => pmap.flatMap[OutMap] { _.set(portP, portS) }
      case (portP, portS) => throw PIRException(s"impossible case $portP, $portS")
    }
  }

  def set(fimap:FIMap, tail:Edge, head:Edge):FIMap = {
    (tail, head) match {
      case (tail:FIMap.K, head:FIMap.V) =>
        fimap + (tail, head)
      case (tail:FIMap.V, head:FIMap.K) =>
        fimap + (head, tail)
      case (tail, head) => throw PIRException(s"impossible case $tail, $head")
    }
  }

  def set(pmap:PIRMap, route:Route, headP:GlobalIO, tailP:GlobalIO):EOption[PIRMap] = {
    Right(pmap).flatMap { pmap =>
      val (_, (tailS,_)) = route.head
      set(pmap, tailP, tailS)
    }.flatMap { pmap =>
      val (_, (_,headS)) = route.last
      set(pmap, headP, headS)
    }.map { pmap =>
      pmap.map[FIMap] { fimap =>
        var fm = fimap
        route.iterator.sliding(size=2,step=1).foreach {
          case List((reached1S, (tail1S, head1S)), (reached2S, (tail2S, head2S))) =>
            fm = set(fm, tail1S.external, head1S.external)
            fm = set(fm, head1S.internal, tail2S.internal)
          case List((reached1S, (tail1S, head1S))) =>  // If only 1 element in route
        }
        val (_, (tailS, headS)) = route.last
        fm = set(fm, tailS.external, headS.external)
        fm
      }
    }
  }

}
