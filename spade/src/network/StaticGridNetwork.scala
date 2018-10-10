package spade
package node

import param._
import scala.collection.mutable

case class StaticGridNetwork[B<:PinType](
  param:StaticGridNetworkParam[B], 
  top:StaticGridTop
)(implicit design:SpadeDesign) extends Network[B](param, top){
  import param._
  import top._

  /** ----- Central Array Connection ----- **/
  for (y <- 0 until numRows) {
    for (x <- 0 until numCols) {

      /* ----- CU to CU Connection ----- */
      if (x!=numCols-1) connect(cuArray(x)(y), cuArray(x+1)(y)) // (Horizontal)
      if (y!=numRows-1) connect(cuArray(x)(y), cuArray(x)(y+1)) // (Vertical)

      /* ----- CU to SB Connection ----- */
      connect(cuArray(x)(y), sbArray(x)(y))
      connect(cuArray(x)(y), sbArray(x)(y+1))
      connect(cuArray(x)(y), sbArray(x+1)(y))
      connect(cuArray(x)(y), sbArray(x+1)(y+1))

    }
  }

  for (y <- 0 until numRows+1) {
    for (x <- 0 until numCols+1) {

      /* ---- SB to SB connections ----*/
      if (x!=numCols) connect(sbArray(x)(y), sbArray(x+1)(y)) // (Horizontal)
      if (y!=numRows) connect(sbArray(x)(y), sbArray(x)(y+1)) // (Vertical)

      /* ---- Top to SB connections ----*/
      if (y==numRows) connect(argFringe, sbArray(x)(y)) // Top Switches
      //if (y==0) connect(argFringe, sbArray(x)(y)) // Bottom Switches

    }
  }

  if (param.isTorus) {
    for (y <- 0 until numRows+1) {
      connect(sbArray(0)(y), sbArray(numCols)(y)) // Horizontal, first col to last col
    }
    for (x <- 0 until numCols+1) {
      connect(sbArray(x)(0), sbArray(x)(numRows)) // Vertical, first row to last row
    }
  }

  ///** ----- Fringe Connection ----- **/
  for (y <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
    for (x <- 0 until mcArray.size) { //rows

      ///* ---- MC and SB connection ---- */
      if (x==0) connect(mcArray(x)(y), sbArray(0)(y)) // (left side)
      else connect(mcArray(x)(y), sbArray(numCols)(y)) // (right side)
      
    }
  }

  dagArray.foreach { dagArray =>
    for (y <- 0 until mcArray.headOption.map(_.size).getOrElse(0)) { //cols
      for (x <- 0 until mcArray.size) { //rows

        ///* ---- DAG and SB connection ---- */
        if (x==0) { connect(dagArray(x)(y), sbArray(0)(y)) // (left side)
        } else connect(dagArray(x)(y), sbArray(numCols)(y)) // (right side)

        ///* ---- MC and DAG connection ---- */
        connect(mcArray(x)(y), dagArray(x)(y))
      }
    }
  }
}
