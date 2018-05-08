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

  var splitCount = 60
  def pruneAndSplit(cumap:CUMap):EOption[CUMap] = {
    if (splitCount < 0) assert(false)
    splitCount -= 1
    prune(cumap) match {
      case Left(f@CostConstrainFailure(constrain, fg, key:CUMap.K)) if isSplitableConstrain(constrain) =>
        dbg(s"$f")
        val vs = cumap(key)
        val ks = split(key)
        val newCUMap = (cumap - key) ++ (ks -> vs)
        pruneAndSplit(newCUMap)
      case Left(f) => Left(f)
      case Right(map) => Right(map)
    }
  }

  def isSplitableConstrain(constrain:Constrain) = constrain match {
    case constrain:CUPrefixConstrain => false
    case constrain:SramConstrain => false
    case constrain:LaneConstrain => false
    case constrain:CUQuantityConstrain => true
    case constrain => false
  }

  def split(cu:GlobalContainer):Set[GlobalContainer]

}
