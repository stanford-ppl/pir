package spade
package param

import SpadeConfig._
case class StaticGridTopParam (
  numRows:Int=option[Int]("row"),
  numCols:Int=option[Int]("col"),
  switchParam:SwitchParam=SwitchParam(),
  centrolPattern:GridCentrolPattern=defaultCentrolPattern,
  fringePattern:GridFringePattern=defaultFringePattern,
  networkParams:List[StaticGridNetworkParam[_<:PinType]] = List(
    StaticGridControlNetworkParam(),
    StaticGridScalarNetworkParam(),
    StaticGridVectorNetworkParam()
  )
) extends GridTopParam {
  //val busWithReady = false
  val busWithReady = true
}

