import spatial.dsl._
import spatial.lib.ML._
import spatial.lib.metaprogramming._
import utils.math.log2

class BankRange_0 extends BankRange

@spatial abstract class BankRange(
  nCols:scala.Int = 32,
  indPtrLen:scala.Int = 33,
  nIter:scala.Int = 1,
  nodePar:scala.Int = 2,
  edgePar:scala.Int = 4,
  ip:scala.Int = 16
) extends SpatialTest with MetaProgramming {
  type T = Float

  def main(args: Array[String]): Unit = {
    val dram = DRAM[T](nCols)
    Accel {
      val indPtr = LUT[Int](indPtrLen)(Seq.tabulate(indPtrLen) { i => i.to[Int] }:_*)
      val qNonDiag = SRAM[T](nCols,nCols/edgePar, edgePar).forcebank(List(nodePar,1,edgePar),List(1,1,1),List(1,1,1))
      val sram = SRAM[T](nCols)
      ForeachWithLane(nCols by 1 par nodePar) { (iCol,in) =>
        val rowIdxStart = indPtr(iCol)
        val rowIdxEnd = indPtr(iCol + 1)
        ForeachWithLane(rowIdxStart until rowIdxEnd par edgePar) { (iRowIdx,ie) =>
          qNonDiag(iCol,(iRowIdx-rowIdxStart)>>log2(edgePar),ie) = iRowIdx.to[T]
        } // O(nnz)
      }
      Sequential.Foreach(nIter by 1) { iIter =>
        ForeachWithLane(nCols by 1 par nodePar) { (iCol,in) =>
      		val rowIdxStart = indPtr(iCol)
      		val rowIdxEnd = indPtr(iCol + 1)
          val accum = Reg(0.to[T])
          ReduceWithLane(accum)(rowIdxStart until rowIdxEnd par edgePar) { (iRowIdx,ie) =>
            qNonDiag(iCol,(iRowIdx-rowIdxStart)>>log2(edgePar),ie) 
      		} { _ + _ }
          sram(iCol) = accum.value
      	}
      }
      dram(0::nCols par ip) store sram
    }

    assert(true)
  }
}


