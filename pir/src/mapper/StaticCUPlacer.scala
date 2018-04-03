package pir.mapper

import pir.node._
import spade.node._
import spade.network._

import scala.collection.mutable

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking {
  import pirmeta._

  val controlRouter = new Router[Bit]
  val scalarRouter = new Router[Word]
  val vectorRouter = new Router[Vector]

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  type P = CUMap.K
  type S = CUMap.V

  //def isMapped(n:PNode, m:PIRMap) = n match {
    //case n:GlobalContainer => n.cumap.contains(n)
  //}

  //def snodes(p:P, m:PIRMap) = {
    //val gins = p.collectDown[GlobalInput]()
    //val gouts = p.collectDown[GlobalOutput]()
    //val sources = (gins ++ gouts).flatMap { 
      //case gin:GlobalInput => goutOf(gin)
      //case gout:GlobalOutput => ginsOf(gout)
    //}
    //val mappedSources = sources.flatMap { gio =>
      //if (isMapped(globalOf(gio))) Some(gio) else None
    //}
    //val reachedAndCosts = mappedSources.map { gio =>
      //val router = gio match {
        //case gio if isBit(gio) => controlRouter
        //case gio if isScalar(gio) => scalarRouter
        //case gio if isVector(gio) => vectorRouter
      //}
      //router.span(
        //start=gio, 
        //logger=Some(this)
      //)
    //}
    //reachedAndCosts.flatten.groupBy { _._1 }.foreach { case (reached, list) =>
      //if (list.size < mappedSources.size) { // Not reachable by everyone
        //m.cumap.multiplyFactor(p, reached, 0)
      //} else {
        //val totalCost = list.map { case (reached, cost) => cost }.product
        //m.cumap.multiplyFactor(p, reached, 1.0 / totalCost)
      //}
    //}
    //m.cumap.sortedFreeValues(p)
  //}

  //def bindLambda(p:P, s:S, m:PIRMap) = {
    //m.flatMap[CUMap]{ _.map(p, s) }
  //}

  //override def runPass(runner:RunPass[_]) =  {
    //pirMap = pirMap.flatMap { pmap =>
      //bind[P, S, PIRMap](
        //pnodes=pmap.cumap.freeKeys.toList, 
        //snodes=snodes _,
        //init=pmap, 
        //bindLambda=bindLambda _
      //)
    //}
  //}

}
