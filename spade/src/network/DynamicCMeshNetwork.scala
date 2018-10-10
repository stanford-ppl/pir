package spade
package node

import param._

case class DynamicCMeshNetwork[B<:PinType](
  param:DynamicCMeshNetworkParam[B], 
  top:DynamicCMeshTop
)(implicit design:SpadeDesign) extends Network[B](param, top){
  import param._
  import top._

  cuArray.zipWithIndex.foreach { case (col, i) =>
    col.zipWithIndex.foreach { case (cluster, j) =>
      val rt = rtArray(i)(j)
      /* ---- CU to router connections ----*/
      cluster.foreach { col => col.foreach { cu => connect(cu, rt) } }
      /* ---- CU to CU connections ----*/
      connect(cluster(0)(0), cluster(0)(1))
      connect(cluster(0)(1), cluster(1)(1))
      connect(cluster(1)(1), cluster(1)(0))
      connect(cluster(1)(0), cluster(0)(0))
    }
  }

  /* ---- router to router connections ----*/
  for (y <- 0 until numTotalRows) {
    for (x <- 0 until numTotalCols) {
      if (x!=numTotalCols-1) connect(rtArray(x)(y), rtArray(x+1)(y)) // (Horizontal)
      if (y!=numTotalRows-1) connect(rtArray(x)(y), rtArray(x)(y+1)) // (Vertical)
    }
  }

  /* ---- ArgFringe to router connections ----*/
  connect(argFringe, argRouter)

}
