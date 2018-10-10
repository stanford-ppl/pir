package arch

import spade.param._
import prism.enums._

class StaticMeshCB(numRows:Int=2, numCols:Int=2, numArgIns:Int=3, numArgOuts:Int=3) extends Spade {
  override lazy val designParam = DesignParam(topParam=StaticGridTopParam(
    numRows=numRows,
    numCols=numCols,
    centrolPattern=Checkerboard(),
    fringePattern=MCOnly(
      argFringeParam=ArgFringeParam(numArgIns=numArgIns, numArgOuts=numArgOuts)
    )
  ))
}

object SMeshCB2x2 extends StaticMeshCB(numRows=2, numCols=2, numArgIns=3, numArgOuts=3)
object SMeshCB4x4 extends StaticMeshCB(numRows=4, numCols=4, numArgIns=10, numArgOuts=3)
object SMeshCB16x8 extends StaticMeshCB(numRows=16, numCols=8, numArgIns=10, numArgOuts=3)

class DynamicMeshCB(numRows:Int=2, numCols:Int=2, numArgIns:Int=3, numArgOuts:Int=3) extends Spade {
  override lazy val designParam = DesignParam(topParam=DynamicGridTopParam(
    numRows=numRows,
    numCols=numCols,
    centrolPattern=Checkerboard(),
    fringePattern=MCOnly(
      argFringeParam=ArgFringeParam(numArgIns=numArgIns, numArgOuts=numArgOuts)
    )
  ))
}

object DMeshCB2x2 extends DynamicMeshCB(numRows=2, numCols=2, numArgIns=3, numArgOuts=3)
object DMeshCB4x2 extends DynamicMeshCB(numRows=4, numCols=2, numArgIns=3, numArgOuts=3)
object DMeshCB4x4 extends DynamicMeshCB(numRows=4, numCols=4, numArgIns=10, numArgOuts=3)
object DMeshCB16x8 extends DynamicMeshCB(numRows=16, numCols=8, numArgIns=10, numArgOuts=3)

object MyDesign extends Spade
