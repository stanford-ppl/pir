package spade

trait SpadeLocalAlias {
  type SpadePass = spade.pass.SpadePass
  type SpadeWorld = spade.pass.SpadeWorld
  type SpadeTraversal = spade.pass.SpadeTraversal

  type SpadeMapLike = spade.config.SpadeMapLike

  type SpadeMetadata = spade.util.SpadeMetadata
}

trait SpadeAlias extends SpadeLocalAlias with spade.node.SpadeNodeUtil {
  type Spade = spade.Spade
  type SNode = spade.node.SpadeNode
  type SpadeDesign = spade.node.SpadeDesign

  type FIMap = spade.config.FIMap
  val FIMap = spade.config.FIMap
  type ConfigMap = spade.config.ConfigMap
  val ConfigMap = spade.config.ConfigMap
  val SpadeConfig = spade.SpadeConfig

  type Bit = spade.Bit
  type Word = spade.Word
  type Vector = spade.Vector
  type PinType = spade.PinType
}

