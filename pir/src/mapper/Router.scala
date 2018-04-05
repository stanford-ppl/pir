package pir.mapper

import pir.node._
import spade.node._

trait Routing extends spade.util.NetworkAStarSearch { self:PIRPass =>

  def portsS(start:GlobalIO, pmap:PIRMap) = start match {
    case start:GlobalInput => pmap.inmap(start).toList.asInstanceOf[List[PT]]
    case start:GlobalOutput => pmap.outmap(start).toList.asInstanceOf[List[PT]]
  }
  def tailToHead(pmap:PIRMap)(tail:Edge) = (tail match {
    case tail:FIMap.V => tail.connected
    case tail:FIMap.K if pmap.fimap.contains(tail) => List(pmap.fimap(tail))
    case tail:FIMap.K => tail.connected
  }).toList.asInstanceOf[List[Edge]]

  def span(
    start:GlobalIO, 
    pmap:PIRMap,
    logger:Option[Logging]
  ):Seq[(Routable,C)] = {
    val cuP = globalOf(start).get
    val cuS = pmap.cumap.mappedValue(cuP).get
    val startTails = portsS(start, pmap)
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    uniformCostSpan(
      start=startBundle, 
      advance=advance(cuS, None, startTails, tailToHead(pmap) _)_,
      logger=logger
    ).map { case (bundle, cost) => (routableOf(bundle).get, cost) }
  }

  def isEnd(endTails:List[PT])(n:Bundle[_], backPointers:BackPointer) = {
    val (_, (tail, head), _) = backPointers(n)
    endTails.contains(head)
  }

  def search[M](
    start:GlobalIO, 
    end:GlobalIO,
    pmap:PIRMap,
    logger:Option[Logging]
  ):EOption[Route] = {
    val startTails = portsS(start, pmap)
    val endTails = portsS(end, pmap)
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP).get
    val ecuP = globalOf(end).get
    val ecuS = pmap.cumap.mappedValue(ecuP).get
    val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
    uniformCostSearch(
      start=startBundle, 
      isEnd=isEnd(endTails) _,
      advance=advance(scuS, Some(ecuS), startTails, tailToHead(pmap) _)_,
      logger=logger
    )
  }

}
