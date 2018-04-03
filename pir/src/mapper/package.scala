package pir

package object mapper extends MappingLogger with pir.util.SpadeAlias with spade.util.PrismAlias {
  type PIR = pir.PIR
  type PNode = pir.node.PIRNode
  type PIRPass = pir.pass.PIRPass
  type PIRMetadata = pir.util.PIRMetadata
}
