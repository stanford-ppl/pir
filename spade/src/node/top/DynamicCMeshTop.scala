package spade
package node
import param._

case class DynamicCMeshTop(override val param:DynamicCMeshTopParam)(implicit design:SpadeDesign) extends Top {
  import param._
  import design.spademeta._

  @transient val cuArray = List.tabulate(numCols, numRows) { case (i,j) => 
    List.tabulate(2, 2) { case (ii, jj) =>
      bundleGroup(
        pattern.cuAt(i,j)(ii,jj), 
        coord=Some(i*3+ii*2, j*3+jj*2)
      )
    }
  }

  @transient val rtArray = List.tabulate(numTotalCols, numTotalRows) { case (i,j) => 
    bundleGroup(
      routerParam, 
      coord=Some(i*3+1, j*3+1)
    )
  }

  val rtrx = rtArray.last.head.coord.get._1
  val rtlx = rtArray.head.head.coord.get._1
  val rtuy = rtArray.head.last.coord.get._2
  val rtby = rtArray.head.head.coord.get._2

  val argRouter = rtArray(numCols/2)(numTotalRows-1)
  val Some((argRouterX, argRouterY)) = argRouter.coord
  @transient val argFringe = bundleGroup(pattern.argFringeParam, coord=Some(argRouterX-1, argRouterY-1))

  @transient val networks = networkParams.map { param => Factory.create(param, this) }

  createSubmodules
}
