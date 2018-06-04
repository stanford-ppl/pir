package pir
package mapper

import pir.node._

class CUPlacer(implicit compiler:PIR) extends PIRPass with Placer with StaticPlacer with DynamicPlacer with AsicPlacer {
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap => log(place(pmap)) }
  }

  override def finPass:Unit = {
    super.finPass
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

}
case class UnsupportedTarget() extends MappingFailure

trait Placer extends PIRPass {
  def place(pmap:PIRMap):EOption[PIRMap] = Left(UnsupportedTarget())
}

