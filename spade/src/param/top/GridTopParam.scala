package spade
package param

trait GridTopParam extends TopParam {
  val numRows:Int
  val numCols:Int
  val centrolPattern:GridCentrolPattern
  val fringePattern:GridFringePattern
  val networkParams:List[NetworkParam[_<:PinType]]
}
