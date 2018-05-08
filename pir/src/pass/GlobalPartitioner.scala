package pir.pass

import pir.node._
import pir.mapper._
import pir.codegen._

import spade.node._

trait GlobalPartioner extends PIRPass with CUPruner {
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.map { pmap => log(pmap.set[CUMap](initCUMap)) }

    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => pruneAndSplit(cumap) }
    }
  }

  def pruneAndSplit(cumap:CUMap):EOption[CUMap] = {
    prune(cumap) match {
      case Left(CostConstrainFailure(constrain, fg:CUMap, key:CUMap.K)) =>
        val vs = cumap(key)
        val ks = split(key)
        val newCUMap = (cumap - key) ++ (ks -> vs)
        pruneAndSplit(newCUMap)
      case Left(f) => Left(f)
      case Right(map) => Right(map)
    }
  }

  def split(cu:GlobalContainer):Set[GlobalContainer]

}
