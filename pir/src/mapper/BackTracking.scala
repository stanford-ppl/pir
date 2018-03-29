package pir.mapper

import pir._

import prism._

import scala.collection.mutable

trait BackTracking { self:Logging => 

  def bind[P, S, M](
    pnodes:List[P], 
    snodes:(P,M) => List[S],
    init:M, 
    bindLambda:(P,S,M) => EOption[M]
  ):EOption[M] = {
    if (pnodes.isEmpty) return Right(init)
    val pnode::rest = pnodes
    snodes(pnode, init).foldLeft[EOption[M]](Left(EmptyBinding(pnode))) {
      case (Right(m), snode) => Right(m)
      case (Left(f), snode) => bindLambda(pnode, snode, init)
    }.flatMap { m => bind(rest, snodes, m, bindLambda) }
  }

  def bind[P, S, F<:FactorGraph[P,S,F]:ClassTag](
    pnodes:List[P], 
    snodes:(P,PIRMap) => List[S],
    init:PIRMap, 
  ):EOption[PIRMap] = {
    bind[P,S,PIRMap](
      pnodes=pnodes,
      snodes=snodes,
      init=init,
      bindLambda=(p:P, s:S, m:PIRMap) => m.flatMap[F] { f => f.map(p,s) }
    )
  }

  def bind[P, S, F<:FactorGraph[P,S,F]:ClassTag](
    pnodes:List[P], 
    init:PIRMap, 
  ):EOption[PIRMap] = {
    bind(
      pnodes=pnodes,
      snodes=(p:P, m:PIRMap) => m.get[F].sortedFreeValues(p),
      init=init
    )
  }

  def bind[P, S, F<:FactorGraph[P,S,F]:ClassTag](
    pnodes:List[P], 
    init:F, 
  ):EOption[F] = {
    bind[P,S,F](
      pnodes=pnodes,
      snodes=(p:P, m:F) => m.sortedFreeValues(p),
      init=init,
      bindLambda=(p:P, s:S, m:F) => m.map(p,s)
    )
  }
}
