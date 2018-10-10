package arch

import spade.param._
import prism.enums._

object Asic extends Spade {
  override lazy val designParam = DesignParam(topParam=AsicTopParam())
}
