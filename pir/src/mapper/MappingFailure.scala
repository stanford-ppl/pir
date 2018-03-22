package pir.mapper

import prism.exceptions._

trait MappingFailure extends PIRException {
}

case class InvalidFactorGraph[K,FG<:FactorGraph[K,_,_]](fg:FG, k:K) extends MappingFailure {
  val msg = s"InvalidFactorGraph $fg at key=$k"
}
