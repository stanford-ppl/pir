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
  ):List[PT] = dbgblk(s"portS(${quote(gio)}, ${quote(cuS)})"){
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
        ports = origPorts.filter { port => portTp(port) == classTag[Vector] }
    }

    val (marked, unmarked) = ports.partition { port => getStaticMarkerOf(pmap, port).nonEmpty }
    val markedAndMatched = marked.filter { port => staticMarkerOf(pmap, port) == marker }
    dbg(s"markedAndMatched=${markedAndMatched.map(quote)}")
    dbg(s"unmarked=${unmarked.map(quote)}")
    assertOneOrLess(markedAndMatched, s"marked ports for ${quote(gio)} in ${quote(cuS)}").map { p => List(p) }.getOrElse(unmarked)
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
    marker:MKMap.V
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
    dbgblk(s"search(headP=${quote(start)} tailP=${quote(end)} scuS=${quote(scuS)} ecuS=${quote(ecuS)} maxCost=$maxCost",buffer=true) {
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

  def spanMaxCost(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap) = -1 
  //def spanMaxCost(tailP:GlobalIO, headP:GlobalIO, pmap:PIRMap) = dbgblk(s"spanMaxCost(${quote(tailP)}, ${quote(headP)})", buffer=false) {
    //type ArgFringe = pir.node.ArgFringe
    //type DramFringe = pir.node.DramFringe
    //val scuP = globalOf(tailP).get
    //val ecuP = globalOf(headP).get
    //(scuP, ecuP) match {
      //case (scuP:ArgFringe  , ecuP           ) if (isMesh(designS) | isCMesh(designS)) & isStatic(designS) => numRows + 2
      //case (scuP            , ecuP:ArgFringe ) if (isMesh(designS) | isCMesh(designS)) & isStatic(designS) => numRows + 2
      //case (scuP:ArgFringe  , ecuP           ) if (isMesh(designS) | isCMesh(designS)) & isDynamic(designS) => numCols / 2 + numRows + 2
      //case (scuP            , ecuP:ArgFringe ) if (isMesh(designS) | isCMesh(designS)) & isDynamic(designS) => numCols / 2 + numRows + 2
      //case (scuP:DramFringe , ecuP           ) => numCols / 2 + 2
      //case (scuP            , ecuP:DramFringe) => numCols / 2 + 2
      //case (scuP            , ecuP           ) => 
        //val lowerBound = 3
        //val upperBound = numRows / 2 + numCols / 2
        //val sdegree = degree(scuP)
        //val edegree = degree(ecuP)
        //val degreeScale = (sdegree + edegree) * 1.0 / (maxDegree * 2)
        //val usageScale = pmap.cumap.mappedValues.size * 1.0 / pmap.cumap.values.size
        //val scale = Math.max(degreeScale, usageScale)
        //val cost = lowerBound + (Math.max(0, (upperBound - lowerBound)) * scale).toInt
        //dbg(s"lowerBound=$lowerBound, upperBound=$upperBound, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree")
        //dbg(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        ////println(s"degreeScale=$degreeScale, usageScale=$usageScale, cost=$cost")
        //assert(cost >= lowerBound, s"cost=$cost < lowerBound=$lowerBound")
        //assert(cost <= upperBound, s"cost=$cost > upperBound=$upperBound")
        ////println(s"lowerBound=$lowerBound, upperBound=$upperBound, sdegree=$sdegree, edegree=$edegree, maxDegree=$maxDegree cost=$cost")
        //cost
      //case _ => throw PIRException(s"Don't know how to compute spanMaxCost")
    //}
  //}

  def refineUnplacedNeighbors(unplaced:List[(GlobalIO, GlobalIO)], pmap:PIRMap) =  {
    dbgblk(s"refineUnplacedNeighbors", buffer=true){
      val groups = unplaced.groupBy { case (tailP, headP) => tailP }
      flatFold(groups, pmap) { case (pmap, (tailP, edgesP)) =>
        val costMap = edgesP.map { case (tailP, headP) => (headP, spanMaxCost(tailP, headP, pmap)) }
        val maxCost = costMap.map { _._2 }.max
        val reached = span(tailP, pmap, maxCost)
        pmap.flatMap[CUMap]{ cumap =>
          flatFold(costMap, cumap) { case (cumap, (headP, maxCost)) =>
            val neighborP = globalOf(headP).get
            val canReach = reached.filter { case (cuS, cost) => maxCost < 0 || cost <= maxCost }.toMap
            dbg(s"neighborP=${quote(neighborP)}, maxCost=$maxCost")
            dbg(s"canReach=${canReach.map(quote).mkString(",")}")
            //dbg(s"cumap($neighborP)=${cumap(neighborP)}")
            cumap.weightedFilterAt(neighborP) { cuS => 
              canReach.get(cuS).map { cost =>
                cumap.weight(neighborP, cuS) + math.ceil(cost.toFloat / unplaced.size).toInt
              }
            }.map { filtered =>
              dbg(s"filtered=${filtered.freeWeightedValues(neighborP).map(quote)}")
              filtered
            }
          }
        }
      }
    }
  }

  def routePlacedNeighbors(placed:List[(GlobalIO, GlobalIO)], pmap:PIRMap) = dbgblk(s"routePlacedNeighbors"){
    flatFold(placed, pmap) { case (pmap, (tailP, headP)) =>
      val (outP, inP) = (tailP, headP) match {
        case (outP, inP) if isGlobalOutput(outP) & isGlobalInput(inP)  => (outP,inP)
        case (inP, outP) if isGlobalOutput(outP) & isGlobalInput(inP)  => (outP,inP)
      }
      search(start=outP,end=inP,pmap=pmap).flatMap { route => set(pmap, route, markerOf(outP)) }
    }
  }

  def route(cuP:CUMap.K, pmap:PIRMap):EOption[PIRMap] = breakPoint(pmap) { 
    dbgblk(s"route(${quote(cuP)})",buffer=false) {
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

}
