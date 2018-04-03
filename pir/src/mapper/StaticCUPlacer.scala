package pir.mapper

import pir.node._
import spade.node._

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

  def bindLambda(p:P, s:S, m:PIRMap) = {
    m.flatMap[CUMap] { cumap =>
      cumap.set(p,s)
    }
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
        init=addIOs(pmap),
        bindLambda=bindLambda _
      )
    }
  }

}
