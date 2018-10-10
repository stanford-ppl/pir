package spade
package node

import param._
import scala.collection.mutable

case class DynamicGridNetwork[B<:PinType](
  param:DynamicGridNetworkParam[B], 
  top:DynamicGridTop
)(implicit design:SpadeDesign) extends Network[B](param, top) {
  import param._
  import top._

  def connectTerminalWithRouter(terminal:BundleGroup) = {
    val (x,y) = terminal.coord.get
    val rt = rtArray(x)(y)
    connect(terminal, rt)
  }

  def connectTerminalArrayWithRouter(array:List[List[BundleGroup]]) = {
    array.foreach { col => 
      col.foreach { terminal => connectTerminalWithRouter(terminal) }
    }
  }

  /** ----- Central Array Connection ----- **/
  connectTerminalArrayWithRouter(cuArray)
  /* ----- CU to CU Connection ----- */
  for (y <- 0 until numRows) {
    for (x <- 0 until numCols) {
      if (x!=numCols-1) connect(cuArray(x)(y), cuArray(x+1)(y)) // (Horizontal)
      if (y!=numRows-1) connect(cuArray(x)(y), cuArray(x)(y+1)) // (Vertical)
    }
  }
  /* ----- router to router Connection ----- */
  for (y <- 0 until numTotalRows) {
    for (x <- 0 until numTotalCols) {
      if (x!=numTotalCols-1) connect(rtArray(x)(y), rtArray(x+1)(y)) // (Horizontal)
      if (y!=numTotalRows-1) connect(rtArray(x)(y), rtArray(x)(y+1)) // (Vertical)
    }
  }
  if (param.isTorus) {
    for (y <- 0 until numTotalRows) {
      connect(rtArray.head(y), rtArray.last(y)) // Horizontal, first col to last col
    }
    for (x <- 0 until numTotalCols) {
      connect(rtArray(x).head, rtArray(x).last) // Vertical, first row to last row
    }
  }

  /** ----- Fringe Connection ----- **/
  dagArray.map { dagArray => 
    connectTerminalArrayWithRouter(dagArray)
    dagArray.zipWithIndex.foreach { case (col, i) =>
      col.zipWithIndex.foreach { case (dag, j) =>
        val mc = mcArray(i)(j)
        connect(mc, dag)
      }
    }
  }
  connectTerminalArrayWithRouter(mcArray)
  connectTerminalWithRouter(argFringe)

}
