package pir

package object codegen extends pir.util.SpadeAlias with pir.util.PrismAlias {
  type PIR = pir.PIR
  type PIRPass = pir.pass.PIRPass
  type PNode = pir.node.PIRNode
  type PIRMetadata = pir.util.PIRMetadata
}
