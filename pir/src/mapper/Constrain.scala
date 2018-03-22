package pir.mapper

import pir._
import pir.node._

import spade.node._

import prism._
import prism.util._

trait Constrain {
  type K
  type V
  type FG <: FactorGraph[K,V,FG]
  implicit def fgct:ClassTag[FG]
  override def toString = this.getClass.getSimpleName.replace("$","")
  def prune(fg:FG)(implicit pass:PIRPass):EOption[FG]
  def prune(pmap:PIRMap)(implicit pass:PIRPass):EOption[PIRMap] = {
    pmap.flatMap[FG](field => prune(field))
  }
}
trait PrefixConstrain extends Constrain {
  def prefixKey(cuP:K)(implicit pass:PIRPass):Boolean
  def prefixValue(cuS:V)(implicit pass:PIRPass):Boolean
  def prune(fg:FG)(implicit pass:PIRPass):EOption[FG] = {
    import pass.{pass => _, _}
    fg.multiplyFactor { case (cuP,cuS) =>
      val factor = if (prefixKey(cuP) == prefixValue(cuS)) 1 else 0
      dbg(s"$this ${quote(cuP)} -> ${quote(cuS)} factor=$factor")
      factor
    }
  }
}
trait QuantityConstrain extends Constrain {
  def numPNodes(cuP:K)(implicit pass:PIRPass):Int
  def numSnodes(cuS:V)(implicit pass:PIRPass):Int
  def prune(fg:FG)(implicit pass:PIRPass):EOption[FG] = {
    import pass.{pass => _, _}
    fg.multiplyFactor { case (cuP,cuS) =>
      val np = numPNodes(cuP)
      val ns = numSnodes(cuS)
      val factor = if (np > ns) 0 else 1
      dbg(s"$this ${quote(cuP)} -> ${quote(cuS)} factor=$factor pnodes=$np snodes=$ns")
      factor
    }
  }
}
trait ArcConsistencyConstrain extends Constrain {
  def prune(fg:FG)(implicit pass:PIRPass):EOption[FG] = {
    import pass.{pass => _, _}
    flatFold(fg.freeKeys,fg) { case (fg, key) => ac3[K,V,FG](fg, key) }
  }
  def ac3[K,V,FG<:FactorGraph[K,V,FG]](fg:FG, k:K):EOption[FG] = {
    if (fg.freeValues(k).isEmpty) return Left(InvalidFactorGraph(fg,k))
    flatFold(fg.freeValues(k),fg) { case (fg, v) =>
      val neighbors = fg.freeKeys(v).filterNot { _ == k }
      val nfg = fg.map(k,v)
      nfg match {
        case Left(_) => fg.removeEdge(k,v)
        case Right(nfg) =>
          flatFold(neighbors, fg) { case (fg, neighbor) => 
            if (ac3[K,V,FG](nfg, neighbor).isLeft) fg.removeEdge(k,v) else Right(fg)
          }
      }
    }
  }
}
