package pir

package object codegen extends pir.util.SpadeAlias with spade.util.PrismAlias with prism.util.FileManager {
  type PIR = pir.PIR
  type PIRPass = pir.pass.PIRPass
  type PNode = pir.node.PIRNode
  type PIRMetadata = pir.util.PIRMetadata
  type PIRMap = pir.mapper.PIRMap
  val PIRConfig = pir.PIRConfig
}
