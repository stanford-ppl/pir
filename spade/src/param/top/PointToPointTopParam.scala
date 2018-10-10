package spade
package param

import SpadeConfig._
case class PointToPointTopParam(
  numRows:Int=option[Int]("row"),
  numCols:Int=option[Int]("col"),
  centrolPattern:GridCentrolPattern=defaultCentrolPattern,
  fringePattern:GridFringePattern=defaultFringePattern
) extends GridTopParam {
  val busWithReady = true
  val fringeNumCols = fringePattern match {
    case _:MCOnly => 1
    case _:MC_DramAG => 2
  }
  // Oneside
  val numTotalRows = numRows
  val numTotalCols = numCols+fringeNumCols*2
  val networkParams = Nil
}

