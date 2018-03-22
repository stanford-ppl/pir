
import pir.mapper._
import pir.node._
import spade.node._

import scala.language.implicitConversions

package object pir {
  /* ------------- Alias ------------- **/

  type PNode = PIRNode
  type SNode = SpadeNode

  /* pass */
  type PIRPass = pir.pass.PIRPass

  /* mapper */
  type PIRMap = pir.mapper.PIRMap
  val PIRMap = pir.mapper.PIRMap
  type MOption[T] = pir.mapper.MOption[T]

  /* util */
  type PIRMetadata = pir.util.PIRMetadata
  /* ------------- Alias (END) ------- **/

}
