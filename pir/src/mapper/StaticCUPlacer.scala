package pir.mapper

import pir.node._
import spade.node._

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking with Routing {
  import pirmeta._

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  type P = CUMap.K
  type S = CUMap.V

  def bind(pmap:PIRMap, portP:GlobalIO, portS:PT) = {
    (portP, portS) match {
      case (portP:InMap.K, portS:InMap.V) => pmap.flatMap[InMap] { _.set(portP, portS) }
      case (portP:OutMap.K, portS:OutMap.V) => pmap.flatMap[OutMap] { _.set(portP, portS) }
      case (portP, portS) => throw PIRException(s"impossible case $portP, $portS")
    }
  }

  def bind(fimap:FIMap, tail:Edge, head:Edge):FIMap = {
    (tail, head) match {
      case (tail:FIMap.K, head:FIMap.V) =>
        fimap + (tail, head)
      case (tail:FIMap.V, head:FIMap.K) =>
        fimap + (head, tail)
      case (tail, head) => throw PIRException(s"impossible case $tail, $head")
    }
  }

  def bind(pmap:PIRMap, route:Route, headP:GlobalIO, tailP:GlobalIO):EOption[PIRMap] = {
    Right(pmap).flatMap { pmap =>
      val (_, (tailS,_)) = route.head
      bind(pmap, tailP, tailS)
    }.flatMap { pmap =>
      val (_, (_,headS)) = route.last
      bind(pmap, headP, headS)
    }.map { pmap =>
      pmap.map[FIMap] { fimap =>
        var fm = fimap
        route.iterator.sliding(size=2,step=1).foreach {
          case List((reached1S, (tail1S, head1S)), (reached2S, (tail2S, head2S))) =>
            fm = bind(fm, tail1S.external, head1S.external)
            fm = bind(fm, head1S.internal, tail2S.internal)
          case List((reached1S, (tail1S, head1S))) =>  // If only 1 element in route
        }
        val (_, (tailS, headS)) = route.last
        fm = bind(fm, tailS.external, headS.external)
        fm
      }
    }
  }

  def bindLambda(p:P, s:S, m:PIRMap) = {
    m.flatMap[CUMap] { cumap => cumap.set(p,s) }.map { m =>
      addIOs(m, p, s)
    }.flatMap { pmap =>
      val iosP = p.collectDown[GlobalIO]().toList
      flatFold(iosP, pmap) { case (pmap, tailP) =>
        val headsP = connectedOf(tailP)
        flatFold(headsP, pmap) { case (pmap, headP) =>
          val neighborP = globalOf(headP).head
          if (pmap.cumap.isMapped(neighborP)) {
            val route = search (
              start=tailP,
              end=headP,
              pmap=pmap,
              logger=None
            )
            route.flatMap { route => bind(pmap, route, headP, tailP) }
          } else {
            val reached = span(
              start=tailP,
              pmap=pmap,
              logger=None
            )
            pmap.flatMap[CUMap]{ cumap =>
              cumap.filterNot(neighborP) { cuS => !reached.contains(cuS) }
            }
          }
        }
      }
    }
  }

  def addIOs(pmap:PIRMap, cuP:P, cuS:S):PIRMap = {
    val iosP = cuP.collectDown[GlobalIO]()
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

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap =>
      logging(bind[P, S, PIRMap](
        pnodes=(m:PIRMap) => minOptionBy(m.cumap.freeKeys) { case k => m.cumap(k).size },
        snodes=(p:P, m:PIRMap) => m.cumap(p).toList.sortBy { case v => -m.cumap.bmap(v).size},
        init=pmap,
        bindLambda=bindLambda _
      ))
    }
  }

}
