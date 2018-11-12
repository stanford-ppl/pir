package pir
package mapper

import pir.node._

trait AsicPlacer extends Placer {
  import pirmeta._

  override def place(pmap:PIRMap) = if (isAsic(designS)) Right(pmap) else super.place(pmap)

}
