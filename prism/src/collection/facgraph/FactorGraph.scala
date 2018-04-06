package prism.collection.immutable

import prism.mapper._

trait FactorGraphLike[K,V,S<:FactorGraphLike[K,V,S]] extends BiManyToManyMapLike[K,V,S] { self:S =>
  def -* (pair:(K,V)) = {
    val (k,v) = pair
    (this - (k,v)).check(k)
  }
  def --* (pair:(Any,Any)) = { 
    val nfg = -- (pair)
    pair._1 match {
      case AsK(k) => nfg.check(k)
      case AsKK(kk) => flatFold(kk, nfg) { case (prev, k) => nfg.check(k) }
    }
  }
  def filter(lambda:(K,V) => Boolean):EOption[S] = {
    def notLambda(k:K, v:V) = !lambda(k,v)
    filterNot(notLambda _)
  }
  def filterNot(lambda:(K,V) => Boolean):EOption[S] = {
    val pairs = fmap.map.toSeq.map { case (k,vv) =>
      (k, vv.filter { v => lambda(k,v) })
    }
    flatFold(pairs, this) { case (prev, (k, vv)) => prev --* (k,vv) }
  }
  def filterNot(k:K)(lambda:V => Boolean):EOption[S] = {
    val vv = fmap(k).filter { v => lambda(v) }
    --* (k, vv)
  }
  def set(k:K, v:V):EOption[S]
  def check(k:K):EOption[S] = if (fmap(k).isEmpty) Left(InvalidFactorGraph(this, k)) else Right(this)
}
case class InvalidFactorGraph[K,FG<:FactorGraphLike[K,_,_]](@transient fg:FG, k:K) extends MappingFailure {
  var info = s"InvalidFactorGraph ${fg.getClass.getSimpleName} at key=$k\n"
  info += s"freeValues: \n"
  info += ""; fg.fmap.map[String] { case (k, vs) => s"$k -> $vs" }.mkString("\n") + "\n"
  val msg = info
}

