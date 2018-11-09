package spade
package node
import param._

case class PointToPointTop(
  override val param:PointToPointTopParam
)(implicit design:SpadeDesign) extends GridTop {
  import param._
  import design.spademeta._

  @transient val cuArray = List.tabulate(numCols, numRows) { case (i,j) => 
    if (i == numCols/2-1 && j == numRows-1) {
      bundleGroup(fringePattern.argFringeParam, coord=Some(i+fringeNumCols,j))
    } else {
      bundleGroup(
        centrolPattern.cuAt(i,j),
        coord=Some(i+fringeNumCols, j)
      )
    }
  }

  @transient val dagArray = fringePattern.dagParam.map { dagParam =>
    List.tabulate(2, numRows) { case (i, j) => 
      bundleGroup(
        dagParam, 
        coord=Some(if (i==0) (1, j) else (numTotalCols-2, j))
      ) 
    }
  }

  @transient val mcArray = List.tabulate(2, numRows) { case (i, j) => 
    bundleGroup(
      fringePattern.mcParam,
      coord=Some(if (i==0) (0,j) else (numTotalCols-1,j))
    )
  }

  val networks = Nil

  createSubmodules

  override def minInputs[B<:PinType:ClassTag](param:Parameter) = 1000

  override def maxInputs[B<:PinType:ClassTag](param:Parameter) = 1000

  override def minOutputs[B<:PinType:ClassTag](param:Parameter) = 1000

  override def maxOutputs[B<:PinType:ClassTag](param:Parameter) = 1000
}

