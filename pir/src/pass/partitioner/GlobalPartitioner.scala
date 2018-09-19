package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

import spade.node._

trait GlobalPartioner extends PIRTransformer with CUPruner with Debugger { self =>
  import pirmeta._

  override def runPass =  {
    pirMap = initCUMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap => 
        val cus = cumap.keys
        pruneAndSplit(cumap).map { case (cumap, splitMap) => cumap }
      }
    }
    log(pirMap)
  }

  override def finPass = {
    super.finPass
    pirMap.fold (
      { failure => fail(failure) },
      { pmap => pmap.cumap.freeKeys.foreach(retimeGlobal) }
    )
  }

  def retimeGlobal(cu:GlobalContainer) = dbgblk(s"retimeGlobal") {
    cu.ins.filter { in => 
      in.src match {
        case src:LocalInAccess if memsOf(src).forall(isLocalMem) => false
        case src => true
      }
    }.groupBy { in =>
      in.from.src.asInstanceOf[Def]
    }.foreach { case (fromsrc, ins) =>
      dbg(s"retiming ${ins.map{ in => s"${in.src}.$in"}} from $fromsrc")
      val load = retime(fromsrc, cu)
      ins.foreach { in =>
        swapConnection(in, fromsrc.out, load.out)
      }
    }
  }

  type SplitMap = Map[CUMap.K, Set[CUMap.K]]
  object SplitMap {
    def empty:SplitMap = Map.empty
  }

  def printResult(splitMap:SplitMap, origKeys:Iterable[CUMap.K]) = {
    def printKey(k:CUMap.K):Unit = {
      if (splitMap.contains(k)) {
        dbgblk(s"$k ->") {
          val (kk, vs) = splitMap(k).partition { _ == k }
          kk.foreach { k => dbg(s"$k") }
          vs.foreach(printKey)
        }
      } else {
        dbg(s"$k")
      }
    }
    dbgblk(s"Splitting Results") {
      origKeys.filter { key => splitMap.contains(key) }.foreach { k => printKey(k) }
    }
  }

  def pruneAndSplit(cumap:CUMap):EOption[(CUMap, SplitMap)] = {
    val keys = cumap.keys
    pruneAndSplit(cumap, SplitMap.empty, keys).map { case (cumap, splitMap) =>
      printResult(splitMap, keys)
      (cumap, splitMap)
    }
  }

  def pruneAndSplit(cumap:CUMap, splitMap:SplitMap, keys:Iterable[CUMap.K]):EOption[(CUMap, SplitMap)] = {
    flatFold(keys, (cumap, splitMap)) { case ((cumap, sm), key) =>
      pruneAndSplit(cumap, sm, key)
    }
  }

  def pruneAndSplit(cumap:CUMap, splitMap:SplitMap, key:CUMap.K):EOption[(CUMap, SplitMap)] = {
    prune(cumap, key) match {
      case Left(f@CostConstrainFailure(fg, key:CUMap.K, kcost, isSplittable)) if isSplittable =>
        dbgblk(s"split(${quote(key)})") {
          dbg(s"$f")
          val vs = cumap(key)
          def fit(k:CUMap.K) = vs.exists { v => 
            resetCacheOn {
              case (kk, _) => kk == k
              case kk => kk == k
            }
            costConstrains.forall{ c => c.fit(k, v)._1 }
          }
          val ks = split(key, fit _)
          val newCUMap = (cumap - key) ++ (ks -> vs)
          val newSplitMap = splitMap + (key -> ks)
          pruneAndSplit(newCUMap, newSplitMap, ks)
        }
      case Left(f) => Left(f)
      case Right(map) => Right((map, splitMap))
    }
  }

  def split(cu:GlobalContainer, fit:CUMap.K => Boolean):Set[GlobalContainer] = throw PIRException(s"Undefined partitioner")

  def breakPoint[T<:Iterable[GlobalContainer]](origPartitions:T, info:String)(newPartitionBlk: => T):T = 
    if (PIRConfig.enableSplitBreakPoint) {
    var newPartitions:Option[T] = None
    val act:BreakAction = {
      case ("o", bp) =>
        new PartitalDotCodegen("before.dot", origPartitions).run.open
        newPartitions = Some(newPartitionBlk)
        new PartitalDotCodegen("after.dot", newPartitions.get).run.open
        bp(())
      case ("s", bp) =>
        new SimpleIRDotCodegen(s"simple.dot") { override lazy val logger = self.logger }.run.open
        bp(())
      case ("t", bp) =>
        new PIRIRDotCodegen(s"top.dot"){ override lazy val logger = self.logger }.run.open
        bp(())
    }
    breakPoint(info, act)
    newPartitions.getOrElse(newPartitionBlk)
  } else newPartitionBlk

}
