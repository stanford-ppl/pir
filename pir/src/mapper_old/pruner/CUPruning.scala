package pir
package mapper

import pir.node._
import prism.collection.immutable._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with CUPruner {
  import pirmeta._
  import PIRConfig._

  if (isStatic(designS) || isDynamic(designS) || isPointToPoint(designS)) {
    //constrains += new CUArcConsistencyConstrain
    constrains += new CUMatchingConstrain
  }

  override def runPass =  {
    pirMap = initCUMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => log(prune(cumap)) }
    }
  }

  override def finPass = {
    super.finPass
    pirMap.left.map { 
      case f:CostConstrainFailure[_] => fail(s"CostConstrainFailure: ${f.key}\n${f.keyCost}")
      case f:MappingFailure => fail(f)
    }
  }

  override def fail(f:Any):Unit = {
    if (enableMapping) super.fail(f)
    else warn(s"$f")
  }

}
