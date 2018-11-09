package spade
package node
import param._

case class StaticGridTop(
  override val param:StaticGridTopParam
)(implicit design:SpadeDesign) extends GridTop {
  import param._
  import design.spademeta._

  val scale = 2

  @transient val cuArray = List.tabulate(numCols, numRows) { case (i,j) => 
    bundleGroup(
      centrolPattern.cuAt(i,j), 
      coord=Some(i*scale+2, j*scale+1)
    )
  }

  @transient val sbArray = List.tabulate(numCols + 1, numRows + 1) { case (i,j) => 
    bundleGroup(
      switchParam, 
      coord=Some(i*scale+1, j*scale)
    )
  }

  val sbrx = sbArray.last.head.coord.get._1
  val sblx = sbArray.head.head.coord.get._1
  val sbuy = sbArray.head.last.coord.get._2
  val sbby = sbArray.head.head.coord.get._2

  @transient val dagArray = fringePattern.dagParam.map { dagParam =>
    List.tabulate(2, sbArray.head.size) { case (i, j) => 
      bundleGroup(
        dagParam, 
        coord=Some(if (i==0) (0*scale, j*scale+1) else (sbrx+1, j*scale+1))
      )
    }
  }

  @transient val mcArray = List.tabulate(2, sbArray.head.size) { case (i, j) => 
    bundleGroup(
      fringePattern.mcParam, 
      coord=Some(if (i==0) (0*scale, j*scale) else (sbrx+1, j*scale))
    )
  }

  @transient val argFringe = bundleGroup(fringePattern.argFringeParam, coord=Some(((sbrx + sblx)/2), sbuy+1))

  @transient val networks = networkParams.map { param => Factory.create(param, this) }

  createSubmodules
}

