
import pir.mapper._
import pir.node._

import scala.language.implicitConversions

package object pir {
  /* ------------- Alias ------------- **/

  /* pass */
  type PIRPass = pir.pass.PIRPass

  /* mapper */

  /* util */
  type PIRMetadata = pir.util.PIRMetadata
  /* ------------- Alias (END) ------- **/

}
