import pir.mapper._

package object pir extends pir.util.SpadeAlias with spade.util.PrismAlias {
  /* ------------- Alias ------------- **/

  type PNode = pir.node.PIRNode
  type PIRDesign = pir.node.PIRDesign

  /* pass */
  type PIRPass = pir.pass.PIRPass

  /* mapper */
  type PIRMap = pir.mapper.PIRMap
  val PIRMap = pir.mapper.PIRMap

  /* util */
  type PIRMetadata = pir.util.PIRMetadata

  /* ------------- Alias (END) ------- **/

}
