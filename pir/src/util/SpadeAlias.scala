package pir.util

trait SpadeAlias {
  type Spade = spade.Spade
  type SpadeMetadata = spade.util.SpadeMetadata
  type SNode = spade.node.SpadeNode
  type SpadeMapLike = spade.config.SpadeMapLike
  type PinType = spade.node.PinType

  type FIMap = spade.config.FIMap
  val FIMap = spade.config.FIMap
  type ConfigMap = spade.config.ConfigMap
  val ConfigMap = spade.config.ConfigMap
  val SpadeConfig = spade.SpadeConfig
}
