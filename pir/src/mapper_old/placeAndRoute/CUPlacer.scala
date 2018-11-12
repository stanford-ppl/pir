package pir
package mapper

import pir.node._

class CUPlacer(implicit compiler:PIR) extends PIRPass with Placer 
  with StaticDynamicPlacer 
  with AsicPlacer 
  with PointToPointPlacer 
{
  import pirmeta._

  override def runPass =  {
    pirMap = pirMap.flatMap { pmap => log(place(pmap)) }
  }

  override def finPass:Unit = {
    super.finPass
    pirMap.fold ({ failure => fail(failure) },{ mapping => succeed })
  }

}

trait Placer extends PIRPass {
  def place(pmap:PIRMap):EOption[PIRMap] = throw PIRException(s"UnsupportedTarget")
}


