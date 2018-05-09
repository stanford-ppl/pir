package pir.mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with CUPruner {
  import pirmeta._

  //constrains += new CUArcConsistencyConstrain
  constrains += new CUMatchingConstrain

  override def runPass =  {
    pirMap = initCUMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => log(prune(cumap)) }
    }
  }

  override def finPass = {
    super.finPass
    pirMap.left.map { case f:MappingFailure => fail(f) }
  }

}
