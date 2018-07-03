package pir
package mapper

import pir.node._
import spade.param._
import spade.node._
import prism.collection.immutable._

trait Routing extends PIRPass with spade.util.NetworkAStarSearch with CostScheme with Debugger {

  import pirmeta._
  import PIRConfig._

  def portsS(
    gio:GlobalIO, 
    cuS:Routable, 
    pmap:PIRMap
  ) (implicit 
    portTp:PT => ClassTag[_<:PinType], 
    gioTp:GlobalIO => ClassTag[_<:PinType]
  ):List[PT] = {
    val marker = markerOf(gio)
    var ports = cuS.collectDown[PT]()
    ports = ports.filter { port => isInput(port) == isGlobalInput(gio) }

    val origPorts = ports
    gioTp(marker) match {
      case tp if tp == classTag[Bit] =>
        ports = origPorts.filter { port => portTp(port) == classTag[Bit] }
        if (ports.isEmpty) ports = origPorts.filter { port => portTp(port) == classTag[Bit] }
        if (ports.isEmpty) ports = origPorts.filter { port => portTp(port) == classTag[Word] }
        if (ports.isEmpty) ports = origPorts.filter { port => portTp(port) == classTag[Vector] }
      case tp if tp == classTag[Word] =>
        ports = origPorts.filter { port => portTp(port) == classTag[Word] }
        if (ports.isEmpty) ports = origPorts.filter { port => portTp(port) == classTag[Vector] }
      case tp if tp == classTag[Vector] =>
        ports = ports.filter { port => portTp(port) == classTag[Vector] }
    }

    val (marked, unmarked) = ports.partition { port => getStaticMarkerOf(pmap, port).nonEmpty }
    val markedAndMatched = marked.filter { port => staticMarkerOf(pmap, port) == marker }
    gio match {
      case gio:GlobalOutput => markedAndMatched ++ unmarked // one to many
      case gio:GlobalInput if markedAndMatched.nonEmpty => markedAndMatched // one to one
      case gio:GlobalInput => unmarked // one to one
    }
  }

  def tailToHead(
    pmap:PIRMap, 
    start:GlobalIO
  )(
    tail:Edge,
    backPointers:BackPointer
  ):List[Edge] = throw PIRException(s"UnsupportedTarget isDynamic=${isDynamicLink(tail)} routingAlgo=${routingAlgo}")

  def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = throw PIRException(s"UnsupportedTarget")

  def span(
    start:GlobalIO, 
    pmap:PIRMap,
    maxCost:C
  ):Seq[(Routable,C)] =  {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    dbgblk(s"span(${quote(start)} scuS=${quote(scuS)} maxCost=$maxCost)",buffer=true) {
      val startTails = portsS(start, scuS, pmap)
      val startBundle = startTails.head.src.asInstanceOf[Bundle[_]]
      uniformCostSpan(
        start=startBundle, 
        advance=advance(
          startTails=startTails,
          tailToHead=tailToHead(pmap, start) _,
          linkCost=linkCost(pmap, start, None) _,
          maxCost=maxCost
        ) _,
      ).flatMap { case (bundle, cost) => 
        routableOf(bundle).get match {
          case sb:SwitchBox => None
          case rt:Router => None
          case routable => Some((routable, cost))
        }
      }
    }
  }

  def searchMaxCost(start:GlobalIO, end:GlobalIO) = -1

  def search[M](
    start:GlobalIO, 
    end:GlobalIO,
    pmap:PIRMap
  ):EOption[Route] = {
    val scuP = globalOf(start).get
    val scuS = pmap.cumap.mappedValue(scuP)
    val ecuP = globalOf(end).get
    val ecuS = pmap.cumap.mappedValue(ecuP)
    val maxCost = searchMaxCost(start, end)
    dbgblk(s"search(headP=${quote(start)} tailP=${quote(end)} scuS=${quote(scuS)} ecuS=${quote(ecuS)} maxCost=$maxCost",buffer=false) {
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
          linkCost=linkCost(pmap, start, Some(end)) _,
          maxCost=searchMaxCost(start, end)
        ) _
      )
    }
  }

  lazy val maxDegree = pirMap.right.get.cumap.keys.map { cuP => degree(cuP) }.max

  lazy val (numRows, numCols) = compiler.arch.designParam.topParam match {
    case param:StaticGridTopParam => (param.numRows, param.numCols)
    case param:DynamicGridTopParam => (param.numTotalRows, param.numTotalCols)
    case param:StaticCMeshTopParam => (param.numRows, param.numCols)
    case param:DynamicCMeshTopParam => (param.numTotalRows, param.numTotalCols)
  }

  def spanMaxCost(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap) = dbgblk(s"spanMaxCost(${quote(tailP)}, ${quote(headP)})", buffer=false) {
    type ArgFringe = pir.node.ArgFringe
    type DramFringe = pir.node.DramFringe
    val scuP = globalOf(tailP).get
    val ecuP = globalOf(headP).get
    (scuP, ecuP) match {
      case (scuP:ArgFringe  , ecuP           ) if (isMesh(designS) | isCMesh(designS)) & isStatic(designS) => numRows + 2
      case (scuP            , ecuP:ArgFringe ) if (isMesh(designS) | isCMesh(designS)) & isStatic(designS) => numRows + 2
      case (scuP:ArgFringe  , ecuP           ) if (isMesh(designS) | isCMesh(designS)) & isDynamic(designS) => numCols / 2 + numRows + 2
      case (scuP            , ecuP:ArgFringe ) if (isMesh(designS) | isCMesh(designS)) & isDynamic(designS) => numCols / 2 + numRows + 2
      case (scuP:DramFringe , ecuP           ) => numCols / 2 + 2
      case (scuP            , ecuP:DramFringe) => numCols / 2 + 2
      case (scuP            , ecuP           ) => 
        val lowerBound = 3
        val upperBound = numRows / 2 + numCols / 2
        val sdegree = degree(scuP)
        val edegree = degree(ecuP)
        val degreeScale = (sdegree + edegree) * 1.0 / (maxDegree * 2)
        val usageScale = pmap.cumap.mappedValues.size * 1.0 / pmap.cumap.values.size
        val scale = Math.max(degreeScale, usageScale)
        val cost = lowerBound + (Math.max(0, (upperBound - lowerBound)) * scale).toInt
        dbg(s"lowerBound=$lowerBound, upperBound=$upperBound, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree")
        dbg(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        //println(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        assert(cost >= lowerBound, s"cost=$cost < lowerBound=$lowerBound")
        assert(cost <= upperBound, s"cost=$cost > upperBound=$upperBound")
        //println(s"lowerBound=$lowerBound, upperBound=$upperBound, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree cost=$cost")
        cost
      case _ => throw PIRException(s"Don't know how to compute spanMaxCost")
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
          val canReach = reached.filter { case (cuS, cost) => cost <= maxCost }.toMap
          dbg(s"neighborP=$neighborP, maxCost=$maxCost, canReach=$canReach")
          dbg(s"canReach=$canReach")
          dbg(s"cumap($neighborP)=${cumap(neighborP)}")
          cumap.weightedFilterAt(neighborP) { cuS => 
            canReach.get(cuS).map { cost =>
              cumap.weight(neighborP, cuS) + cost
            }
          }.map { filtered =>
            dbg(s"filtered=${filtered.freeWeightedValues(neighborP)}")
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

  override def quote(n:Any) = n match {
    case n:PT => s"${quote(n.src)}.${n}"
    case n:Edge => s"${quote(n.src)}.$n"
    case n:GlobalIO => s"${globalOf(n).get}.${super.quote(n)}"
    case n => super.quote(n)
  }

}
