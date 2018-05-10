
import pir.mapper._
import pir.util._
import prism.util._

package object pir extends spade.SpadeAlias with prism.PrismAlias {
  /* ------------- Alias ------------- **/

  type PNode = pir.node.PIRNode
  type PIRDesign = pir.node.PIRDesign

  /* pass */
  type PIRPass = pir.pass.PIRPass

  /* mapper */
  type PIRMap = pir.mapper.PIRMap
  val PIRMap = pir.mapper.PIRMap
  val EmptyMapping = pir.mapper.EmptyMapping

  /* util */
  type PIRMetadata = pir.util.PIRMetadata

  /* ------------- Alias (END) ------- **/

}
