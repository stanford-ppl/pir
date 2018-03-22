package pir.mapper

import prism.exceptions._

case class InvalidFactorGraph[K,FG<:FactorGraph[K,_,_]](fg:FG, k:K) extends MappingFailure {
  var info = s"InvalidFactorGraph ${fg.name} at key=$k\n"
  info += s"freeValues: \n"
  info += fg.keys.map { k => s"$k -> ${fg.freeValues(k)}" }.mkString("\n") + "\n"
  info += s"values: \n${fg.values.mkString(",")}\n"
  val msg = info
}
