package pir
package mapper

import pir.node._
import spade.node._

trait CostScheme { self:Routing =>
  import PIRConfig._

  def linkCost(
    pmap:PIRMap,
    start:GlobalIO,
    end:Option[GlobalIO]
  )(
    from:PT,
    to:PT
  ) = 0

}
