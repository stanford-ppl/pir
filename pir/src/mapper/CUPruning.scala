package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with CUPruner {
  import pirmeta._

  type N = PIRNode

  override def runPass =  {
    pirMap = pirMap.map { pmap => log(pmap.set[CUMap](initCUMap)) }

    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => log(prune(cumap)) }
    }
  }

  override def finPass = {
    super.finPass
    pirMap.left.map {
      case f:MappingFailure =>
        fail(s"CUPruning failed. ${f}")
        runner.setFailed
    }
  }

}
