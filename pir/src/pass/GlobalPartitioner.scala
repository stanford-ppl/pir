package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

import spade.node._

trait GlobalPartioner extends PIRTransformer with CUPruner {
  import pirmeta._

  override def runPass =  {
    pirMap = initCUMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => pruneAndSplit(cumap) }
    }
    pirMap.fold (
      { failure => fail(failure) },
      { pmap => pmap.cumap.freeKeys.foreach(retimeGlobal) }
    )
    log(pirMap)
  }

  def retimeGlobal(cu:GlobalContainer) = dbgblk(s"retimeGlobal") {
    cu.ins.filterNot { _.src.isInstanceOf[LocalStore] }.groupBy { in =>
      in.from.src.asInstanceOf[Def]
    }.foreach { case (fromsrc, ins) =>
      dbg(s"retiming ${ins.map{ in => s"${in.src}.$in"}} from $fromsrc")
      val load = retime(fromsrc, cu)
      ins.foreach { in =>
        swapConnection(in, fromsrc.out, load.out)
      }
    }
  }

  var splitCount = 61
  def pruneAndSplit(cumap:CUMap):EOption[CUMap] = {
    if (splitCount < 0) assert(false)
    splitCount -= 1
    log(prune(cumap)) match {
      case Left(f@CostConstrainFailure(constrain, fg, key:CUMap.K)) if isSplitableConstrain(constrain) =>
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
