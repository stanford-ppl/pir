package spade
package config

import prism.collection.immutable._

import ConfigMap._
case class ConfigMap(map:Map[K,V]) extends OneToOneMapLike[K,V,ConfigMap] {
  override def apply(n:Configurable) = n.toConfig(map(n))
  override def get(n:Configurable) = map.get(n).map{ c => n.toConfig(c) }
  def isMapped(n:K) = map.contains(n)
}

object ConfigMap {
  type K = Configurable
  type V = Configuration
  def empty = ConfigMap(Map.empty)
}
