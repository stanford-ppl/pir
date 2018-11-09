package spade
package node
import param._

case class StaticCMeshTop(override val param:StaticCMeshTopParam)(implicit design:SpadeDesign) extends Top {
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

  @transient val sbArray = List.tabulate(numCols, numRows) { case (i,j) => 
    bundleGroup(
      switchParam, 
      coord=Some(i*3+1, j*3+1)
    )
  }

  val sbrx = sbArray.last.head.coord.get._1
  val sblx = sbArray.head.head.coord.get._1
  val sbuy = sbArray.head.last.coord.get._2
  val sbby = sbArray.head.head.coord.get._2

  @transient val argFringe = bundleGroup(pattern.argFringeParam, coord=Some(((sbrx + sblx)/2), sbuy+2))

  @transient val networks = networkParams.map { param => Factory.create(param, this) }

  createSubmodules
}

