package spade
package param

trait CMeshTopParam extends TopParam {
  val numRows:Int
  val numCols:Int
  val pattern:CMeshPattern
}

import SpadeConfig._
case class StaticCMeshTopParam (
  numRows:Int=option[Int]("row"),
  numCols:Int=option[Int]("col"),
  switchParam:SwitchParam=SwitchParam(),
  pattern:CMeshPattern=CMeshCheckerboard(),
  networkParams:List[StaticCMeshNetworkParam[_<:PinType]] = List(
    StaticCMeshControlNetworkParam(),
    StaticCMeshScalarNetworkParam(),
    StaticCMeshVectorNetworkParam()
  )
) extends CMeshTopParam {
  val busWithReady = true
}
case class DynamicCMeshTopParam (
  numRows:Int=option[Int]("row"),
  numCols:Int=option[Int]("col"),
  routerParam:RouterParam=RouterParam(),
  pattern:CMeshPattern=CMeshCheckerboard(),
  networkParams:List[DynamicCMeshNetworkParam[_<:PinType]] = List(
    DynamicCMeshControlNetworkParam(),
    DynamicCMeshScalarNetworkParam(),
    DynamicCMeshVectorNetworkParam()
  )
) extends CMeshTopParam {
  val busWithReady = true
  val numTotalRows = numRows + 1
  val numTotalCols = numCols
}
