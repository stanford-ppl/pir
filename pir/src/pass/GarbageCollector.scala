//package pir
//package pass

//import pir.node._
//import prism.graph._
//import prism.graph.implicits._

//import scala.collection.mutable

//// BFS is slightly faster than DFS
//trait GarbageCollector { self:PIRPass =>

  //// Breaking loop in traversal
  //override def visitIn(n:N):List[N] = n match {
    //case n:LocalOutAccess => n.in.neighbors.toList ++ n.done.neighbors.filterNot { case c:Controller => true; case _ => false }
    //case n@UnderControlBlock(cb) if depDupHasRun => super.visitIn(cb)
    //case n if depDupHasRun => cover[PIRNode, ControlBlock](super.visitIn(n))
    //case n => super.visitIn(n)
  //}

  //override def visitOut(n:N):List[N] = n match {
    //case n@UnderControlBlock(cb) if depDupHasRun => 
      //super.visitOut(cb).tryFilter { case x:LocalOutAccess => false; case _ => true }.toList
    //case n:ControlBlock if depDupHasRun => 
      //super.visitOut(n).tryFilter { case x:LocalOutAccess => false; case _ => true }.toList
    //case n => super.visitOut(n)
  //}

  //def depedsExistsLive(n:N):Boolean = {
    //val depeds = depFunc(n)
    //val (analyzedDepeds, unanalyzedDepeds) = depeds.partition { deped => isLive(deped).nonEmpty }
    //val live = analyzedDepeds.exists { deped => isLive(deped).get }
    //if (live) return true
    //if (unanalyzedDepeds.isEmpty) return false
    //if (config.aggressive_dce) {
      //dbg(s"depeds=${depeds.map { deped => (deped, isLive(deped)) }}")
      //dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be aggressive here")
      ////warn(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be aggressive here")
      //return false
    //} else {
      //dbg(s"depeds=${depeds.map { deped => (deped, isLive(deped)) }}")
      //dbg(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be conservative here")
      ////warn(s"n=$n unkownDeps=${depFunc(n).filter { deped => isLive(deped).isEmpty }} liveness unknown! be conservative here")
      //return true
    //}
  //}

  //def isLive(n:N):Option[Boolean] = n match {
    //case n if liveMap.contains(n) => Some(liveMap(n))
    //case n:HostRead => Some(true)
    //case n:HostWrite => Some(true)
    //case n:TokenRead => Some(true)
    //case n:AssertIf => Some(true)
    //case n:ExitIf => Some(true)
    //case n:FringeStreamRead => Some(true)
    //case n:HostInController => Some(true)
    //case n:HostOutController => Some(true)
    //case n:Controller if !depDupHasRun => Some(true)
    //case n if n.isUnder[Controller] && !depDupHasRun => Some(true)
    //case n => None
  //}

//}
