import spatial.dsl._
import spatial.dsl._
import utils.io.files._

class NaivePageRankCSR_0 extends NaivePageRankCSR()(ipls=1, ip=1)
class NaivePageRankCSR_1 extends NaivePageRankCSR()(ipls=16, ip=16)
class NaivePageRankCSR_2 extends NaivePageRankCSR()(opts=2,ipls=16, ip=16)
class NaivePageRankCSR_3 extends NaivePageRankCSR()(opN=3,opts=2,ipls=16, ip=16)
class NaivePageRankCSR_4 extends NaivePageRankCSR(iters=2)(ipls=1, ip=1)

@spatial class NaivePageRankCSR(
  val ts: scala.Int=32,
  val iters:scala.Int = 1, // number of iterations
  val damp:scala.Float = 0.125f,
)(
  opN:scala.Int = 1, // N / ts
  opts:scala.Int = 1, // tile par
  ipls:scala.Int = 16, // load store par
  ip:scala.Int = 16,
) extends SpatialTest with PageRankHost {

  type T = FixPt[TRUE, _16, _16] // Float

  def main(args: Array[String]): Unit = {
    genData
    compGold

    val ofstData = loadCSV1D[Int](ofstPath)
    val edgeData = loadCSV1D[Int](edgePath)

    val N = ofstData.length - 1 // Number of nodes

    val initRank = (1.0f.to[T] / N.to[T]).to[T]
    val argN = ArgIn[Int]
    val argIR = ArgIn[T]
    val argIters = ArgIn[Int]
    setArg(argN, N)
    setArg(argIR, initRank)
    setArg(argIters, iters)

    val pageranks = DRAM[T](N)
    val ofsts = DRAM[Int](N)
    val edges = DRAM[Int](edgeData.length)
    setMem(ofsts, ofstData)
    setMem(edges, edgeData)

    val rts = ts + 1
    Accel { 
      Sequential.Foreach(argIters.value by 1) { iter =>
        Foreach(0 until argN.value by ts par opN) { i =>
          val prTile = SRAM[T](ts)
          val ofstTile = SRAM[Int](rts)
          ofstTile load ofsts(i :: i + rts par ipls)
          Foreach(ts by 1 par opts) { j =>
            val start = ofstTile(j)
            val end = ofstTile(j+1)
            val len = end - start
            val neighbors = FIFO[Int](maxEdge)
            neighbors load edges(start::end)
            val neighborRanks = FIFO[T](maxEdge)
            neighborRanks gather pageranks(neighbors, len)
            val neighborEndIdx = FIFO[Int](maxEdge)
            Foreach(0 until len par ip) { k =>
              neighborEndIdx.enq(neighbors.deq + 1)
            }
            val neighborStart = FIFO[Int](maxEdge)
            val neighborEnd = FIFO[Int](maxEdge)
            neighborStart gather ofsts(neighbors, len)
            neighborEnd gather ofsts(neighborEndIdx, len)
            val rankSum = Reg[T]
            Reduce(rankSum)(0 until len par ip) { k =>
              val neighborLen = neighborEnd.deq - neighborStart.deq
              val nrank = mux(iter===0, argIR.value, neighborRanks.deq)
              nrank / neighborLen.to[T]
            } { _ + _ }
            prTile(j) = rankSum.value * damp + ((1-damp).to[T] / argN.value.to[T])
          }
          pageranks(i::i+ts par ipls) store prTile
        }
      }
    }

    checkGold(pageranks)
  }

}
