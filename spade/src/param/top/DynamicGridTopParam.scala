package spade
package param

import SpadeConfig._
case class DynamicGridTopParam (
  numRows:Int=option[Int]("row"),
  numCols:Int=option[Int]("col"),
  routerParam:RouterParam=RouterParam(),
  centrolPattern:GridCentrolPattern=defaultCentrolPattern,
  fringePattern:GridFringePattern=defaultFringePattern,
  networkParams:List[DynamicGridNetworkParam[_<:PinType]] = List(
    DynamicGridControlNetworkParam(),
    DynamicGridScalarNetworkParam(),
    DynamicGridVectorNetworkParam()
  )
) extends GridTopParam {
  val busWithReady = true
  val fringeNumCols = fringePattern match {
    case _:MCOnly => 1
    case _:MC_DramAG => 1
  }
  // Oneside
  val numTotalRows = numRows
  val numTotalCols = numCols+fringeNumCols*2
}
