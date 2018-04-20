package pir.mapper

import prism.collection.immutable._
import prism.util.Memorization

abstract class Constrain(implicit val pass:PIRPass) {
  type K
  type V
  type FG <: FactorGraphLike[K,V,FG]
  implicit def fgct:ClassTag[FG]
  override def toString = this.getClass.getSimpleName.replace("$","")
  def prune(fg:FG):EOption[FG]
  def prune(pmap:PIRMap):EOption[PIRMap] = {
    pmap.flatMap[FG](field => prune(field))
  }
}
trait CostConstrain extends Constrain {
  def fit(cuP:K, cuS:V):Boolean
  def prune(fg:FG):EOption[FG] = {
    fg.filter { case (cuP,cuS) => fit(cuP, cuS) }
  }
}
trait PrefixConstrain extends CostConstrain with Memorization {
  import pass._
  memorizing = true
  def prefixKey(cuP:K):Boolean
  def prefixValue(cuS:V):Boolean
  val prefixKeyOf = memorize(prefixKey _)
  val prefixValueOf = memorize(prefixValue _)
  def fit(cuP:K, cuS:V):Boolean = {
    val key = prefixKeyOf(cuP)
    val value = prefixValueOf(cuS)
    val factor = key == value
    pass.dbg(s"$this ${quote(cuP)}:$key == ${quote(cuS)}:$value factor=$factor")
    factor
  }
}
trait QuantityConstrain extends CostConstrain with Memorization {
  import pass._
  memorizing = true
  def numPNodes(cuP:K):Int
  def numSnodes(cuS:V):Int
  val numPNodesOf = memorize(numPNodes _)
  val numSnodesOf = memorize(numSnodes _)
  def fit(cuP:K, cuS:V):Boolean = {
    val key = numPNodesOf(cuP)
    val value = numSnodesOf(cuS)
    val factor = key <= value
    pass.dbg(s"$this ${quote(cuP)}:$key == ${quote(cuS)}:$value factor=$factor")
    factor
  }
}
trait ArcConsistencyConstrain extends Constrain {
  def prune(fg:FG):EOption[FG] = {
    import pass.{pass => _, _}
    flatFold(fg.freeKeys,fg) { case (fg, k) => ac3[K,V,FG](fg, k) }
  }
  def ac3[K,V,FG<:FactorGraphLike[K,V,FG]](fg:FG, k:K):EOption[FG] = {
    flatFold(fg(k),fg) { case (fg, v) =>
      val neighbors = fg.freeKeys(v).filterNot { _ == k }
      val nfg = fg.set(k,v)
      nfg match {
        case Left(_) => fg - (k,v)
        case Right(nfg) =>
          flatFold(neighbors, fg) { case (fg, neighbor) => 
            if (ac3[K,V,FG](nfg, neighbor).isLeft) fg - (k,v) else Right(fg)
          }
      }
    }
  }
}
