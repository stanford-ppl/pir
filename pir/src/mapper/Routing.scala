package pir
package mapper

import pir.node._
import spade.param._
import spade.node._
import prism.collection.immutable._

trait Routing extends spade.util.NetworkAStarSearch with Debugger { self:PIRPass =>

  import pirmeta._

  def searchMaxCost(start:GlobalIO, end:GlobalIO) = -1

  def portsS(gio:GlobalIO, cuS:Routable, pmap:PIRMap)
            (implicit portTp:PT => ClassTag[_<:PinType], gioTp:GlobalIO => ClassTag[_<:PinType]):List[PT]

  def tailToHead(pmap:PIRMap, start:GlobalIO)(tail:Edge):List[Edge]

  def span(
    start:GlobalIO, 
    pmap:PIRMap,
    maxCost:C
  ):Seq[(Routable,C)] = dbgblk(s"span(${quote(start)},maxCost=$maxCost)",buffer=true) {
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
        maxCost=maxCost
      ) _,
    ).flatMap { case (bundle, cost) => 
      routableOf(bundle).get match {
        case sb:SwitchBox => None
        case routable => Some((routable, cost))
      }
    }
  }

  def search[M](
    start:GlobalIO, 
    end:GlobalIO,
    pmap:PIRMap
  ):EOption[Route] = dbgblk(s"search(headP=${quote(start)} tailP=${quote(end)} maxCost=${searchMaxCost(start, end)}",buffer=false) {
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
  }

  lazy val maxDegree = pirMap.right.get.cumap.keys.map { cuP => degree(cuP) }.max

  def spanMaxCost(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap) = dbgblk(s"spanMaxCost(${quote(tailP)}, ${quote(headP)})", buffer=false) {
    type ArgFringe = pir.node.ArgFringe
    type DramFringe = pir.node.DramFringe
    val scuP = globalOf(tailP).get
    val ecuP = globalOf(headP).get
    (scuP, ecuP, self.compiler.arch.designParam.topParam) match {
      case (scuP:ArgFringe  , ecuP            , param:MeshTopParam) => param.numRows + 2
      case (scuP            , ecuP:ArgFringe  , param:MeshTopParam) => param.numRows + 2
      case (scuP:DramFringe , ecuP            , param:MeshTopParam) => param.numCols / 2 + 2
      case (scuP            , ecuP:DramFringe , param:MeshTopParam) => param.numCols / 2 + 2
      case (scuP            , ecuP            , param:MeshTopParam) => 
        val minCost = 3
        val maxCost = param.numRows / 2 + param.numCols / 2
        val sdegree = degree(scuP)
        val edegree = degree(ecuP)
        val degreeScale = (sdegree + edegree) * 1.0 / (maxDegree * 2)
        val usageScale = pmap.cumap.mappedValues.size * 1.0 / pmap.cumap.values.size
        val scale = Math.max(degreeScale, usageScale)
        val cost = minCost + (Math.max(0, (maxCost - minCost)) * scale).toInt
        dbg(s"minCost=$minCost, maxCost=$maxCost, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree")
        dbg(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        //println(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        assert(cost >= minCost, s"cost=$cost < minCost=$minCost")
        assert(cost <= maxCost, s"cost=$cost > maxCost=$maxCost")
        //println(s"minCost=$minCost, maxCost=$maxCost, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree cost=$cost")
        cost
      case _ => throw PIRException(s"Don't know what is spanMaxCost")
    }
  }

  def refineUnplacedNeighbors(unplaced:List[(GlobalIO, GlobalIO)], pmap:PIRMap) = dbgblk(s"refineUnplacedNeighbors", buffer=true){
    val groups = unplaced.groupBy { case (tailP, headP) => tailP }
    flatFold(groups, pmap) { case (pmap, (tailP, edgesP)) =>
      val costMap = edgesP.map { case (tailP, headP) => (headP, spanMaxCost(tailP, headP, pmap)) }
      val maxCost = costMap.map { _._2 }.max
      val reached = span(tailP, pmap, maxCost)
      pmap.flatMap[CUMap]{ cumap =>
        flatFold(costMap, cumap) { case (cumap, (headP, maxCost)) =>
          val neighborP = globalOf(headP).get
          val canReach = reached.filter { case (cuS, cost) => cost <= maxCost }.map { _._1 }
          dbg(s"neighborP=$neighborP, maxCost=$maxCost, canReach=$canReach")
          dbg(s"canReach=$canReach")
          dbg(s"cumap($neighborP)=${cumap(neighborP)}")
          cumap.filterAt(neighborP) { cuS => canReach.contains(cuS) }.map { filtered =>
            dbg(s"filtered=$filtered")
            filtered
          }
        }
      }
    }
  }

  def routePlacedNeighbors(placed:List[(GlobalIO, GlobalIO)], pmap:PIRMap) = dbgblk(s"routePlacedNeighbors"){
    flatFold(placed, pmap) { case (pmap, (tailP, headP)) =>
      search(start=tailP,end=headP,pmap=pmap).flatMap { route => 
        set(pmap, route, headP, tailP)
      }
    }
  }

  def route(cuP:CUMap.K, pmap:PIRMap):EOption[PIRMap] = breakPoint(pmap) { 
    dbgblk(s"route(${quote(cuP)})",buffer=true) {
      val iosP = cuP.collectDown[GlobalIO]().toList
      val edgesP = iosP.flatMap { tailP => connectedOf(tailP).map { headP => (tailP, headP) } }
      val (placed, unplaced) = edgesP.partition { case (tailP, headP) =>
        pmap.cumap.isMapped(globalOf(headP).head)
      }
      refineUnplacedNeighbors(unplaced, pmap).flatMap { pmap =>
        routePlacedNeighbors(placed, pmap)
      }
    } 
  }

  def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap]

  override def quote(n:Any) = n match {
    case n:GlobalIO => s"${globalOf(n).get}.${super.quote(n)}"
    case n:PT => s"${quote(n.src)}.${n}"
    case n => super.quote(n)
  }

}
