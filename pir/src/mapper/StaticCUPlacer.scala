package pir.mapper

import pir.node._
import spade.node._

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking with Routing {
  import pirmeta._

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  type P = CUMap.K
  type S = CUMap.V

  //def bind(pmap:PIRMap, portP:GlobalIO, portS:PT) = {
    //(portP, portS) match {
      //case (portP:InMap.K, portS:InMap.V) => pmap.map[InMap] { _.set(portP, portS) }
      //case (portP:OutMap.K, portS:OutMap.V) => pmap.map[OutMap] { _.set(portP, portS) }
    //}
  //}

  //def bind(fimap:FIMap, tail:Edge, head:Edge) = {
    //(tail, head) match {
      //case (tail, head) if isInput(tail) & isOutput(head) =>
        //fimap + (tail, head)
      //case (tail, head) if isOutput(tail) & isInput(head) =>
        //fimap + (head, tail)
    //}
  //}

  //def bind(pmap:PIRMap, route:Route, headP:GlobalIO, tailP:GlobalIO):PIRMap = {
     //var pm:PIRMap = pmap.map[FIMap] { fimap =>
       //val fm = route.iterator.sliding(size=2,step=1).foldLeft(fimap) { 
         //case (fimap, List((reached1S, (tail1S, head1S)), (reached2S, (tail2S, head2S)))) =>
           //bind(bind(fimap, tail1S.external, head1S.external), head1S.internal, reached2S.internal)
         //case (fimap, List((reached1S, (tail1S, head1S)))) => fimap // If only 1 element in route
       //}
       //val (_, (tailS, headS)) = route.last
       //bind(fm, (tailS.external, headS.external))
     //}
     //val (_, (tailS,_)) = route.head
     //val (_, (_,headS)) = route.last
     //pm = bind(pm, tailP, tailS)
     //pm = bind(pm, headP, headS)
     //pm
  //}

  def bindLambda(p:P, s:S, m:PIRMap) = {
    m.flatMap[CUMap] { cumap => cumap.set(p,s) }//.flatMap { pmap =>
      //val iosP = p.collectDown[GlobalIO]().toList
      //flatFold(iosP, pmap) { case (pmap, tailP) =>
        //val headsP = connectedOf(tailP)
        //flatFold(headsP, pmap) { case (pmap, headP) =>
          //val neighborP = globalOf(head).head
          //if (pmap.cumap.isMapped(neighborP)) {
            //val route = search (
              //start=tailP,
              //end=headP,
              //pmap=pmap,
              //logger=None
            //)
            //route.map { route => bind(pmap, route, headP, tailP) }
          //} else {
            //val reached = span(
              //start=tail,
              //pmap=pmap,
              //logger=None
            //)
            //pmap.flatMap[CUMap]{ cumap =>
              //cumap.filterNot(neighbor) { cuS => !reached.contains(cuS) }
            //}
          //}
        //}
      //}
    //}
  }

  def addIOs(pmap:PIRMap, cuP:P, cuS:S) = {
    val iosP = p.collectDown[GlobalIO]().toList
  }

  def addIOs(pmap:PIRMap) = {
    pmap.cumap.keys.foldLeft(pmap) { case (pmap, cu) =>
      val ins = cu.collectDown[GlobalInput]().toList
      val outs = cu.collectDown[GlobalOutput]().toList
      val ingrp = ins.groupBy(in => bundleTypeOf(in))
      val outgrp = outs.groupBy(in => bundleTypeOf(in))
      val cinsP = ingrp.getOrElse(Bit,Nil).toSet
      val sinsP = ingrp.getOrElse(Word,Nil).toSet
      val vinsP = ingrp.getOrElse(Vector,Nil).toSet
      val coutsP = outgrp.getOrElse(Bit,Nil).toSet
      val soutsP = outgrp.getOrElse(Word,Nil).toSet
      val voutsP = outgrp.getOrElse(Vector,Nil).toSet
      val insS = pmap.cumap(cu).flatMap { _.collectDown[spade.node.Input[_]]() }.toSet
      val outsS = pmap.cumap(cu).flatMap { _.collectDown[spade.node.Output[_]]() }.toSet
      val cinsS = insS.filter { in => is[Bit](in) }
      val sinsS = insS.filter { in => is[Word](in) }
      val vinsS = insS.filter { in => is[Vector](in) }
      val coutsS = outsS.filter { out => is[Bit](out) }
      val soutsS = outsS.filter { out => is[Word](out) }
      val voutsS = outsS.filter { out => is[Vector](out) }
      pmap.map[InMap] { inmap =>
        var inmp = inmap
        inmp ++= (cinsP -> cinsS)
        inmp ++= (sinsP -> sinsS)
        inmp ++= (vinsP -> vinsS)
        inmp
      }.map[OutMap] { outmap =>
        var outmp = outmap
        outmp ++= (coutsP -> coutsS)
        outmp ++= (soutsP -> soutsS)
        outmp ++= (voutsP -> voutsS)
        outmp
      }
    }
  }

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap =>
      bind[P, S, PIRMap](
        pnodes=(m:PIRMap) => minOptionBy(m.cumap.freeKeys) { case k => m.cumap(k).size },
        snodes=(p:P, m:PIRMap) => m.cumap(p).toList.sortBy { case v => -m.cumap.bmap(v).size},
        init=pmap,
        bindLambda=bindLambda _
      )
    }
  }

}
