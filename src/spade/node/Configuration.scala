package spade.node

trait Configuration {
}

trait Configurable extends Node {
  type CT <: Configuration
  def toConfig(x:Configuration) = x.asInstanceOf[CT]
}
