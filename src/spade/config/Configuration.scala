package spade.config

import spade.util._

trait Configuration

trait Configurable extends Node {
  type CT <: Configuration
  def toConfig(x:Configuration) = x.asInstanceOf[CT]
}
