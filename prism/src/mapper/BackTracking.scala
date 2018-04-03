package prism.mapper

import prism._

import scala.collection.mutable

trait BackTracking { self:Logging => 

  def bind[P, S, M](
    pnodes:M => Option[P], 
    snodes:(P,M) => List[S],
    init:M, 
    bindLambda:(P,S,M) => EOption[M]
  ):EOption[M] = {
    pnodes(init).fold[EOption[M]](Right(init)) { pnode =>
      snodes(pnode, init).foldLeft[EOption[M]](Left(EmptyBinding(pnode))) {
        case (Right(m), snode) => Right(m)
        case (Left(f), snode) => bindLambda(pnode, snode, init)
      }.flatMap { m => bind(pnodes, snodes, m, bindLambda) }
    }
  }

}
