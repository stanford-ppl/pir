package pir

package object pass extends pir.util.SpadeAlias with spade.util.PrismAlias {
  type PIR = pir.PIR
  type PIRApp = pir.PIRApp
  type PNode = pir.node.PIRNode
  type PIRMetadata = pir.util.PIRMetadata
  val PIRConfig = pir.PIRConfig
}
