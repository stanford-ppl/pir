import spatial.dsl._
import spatial.dsl._
import utils.io.files._

class TiledPageRank_0 extends TiledPageRank()(ipls=1, ip=1)
class TiledPageRank_1 extends TiledPageRank()(ipls=16, ip=16)
class TiledPageRank_2 extends TiledPageRank()(opts=2,ipls=16, ip=16)
class TiledPageRank_3 extends TiledPageRank()(opN=3,opts=3,ipls=16, ip=16)
class TiledPageRank_4 extends TiledPageRank(iters=2)(ipls=1, ip=1)

@spatial class TiledPageRank(
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

    val lenData = loadCSV1D[Int](lenPath)
    val ofstData = loadCSV1D[Int](ofstPath)
    val edgeData = loadCSV1D[Int](edgePath)

    val N = lenData.length // Number of nodes

    val initRank = (1.0f.to[T] / N.to[T]).to[T]
    val argN = ArgIn[Int]
    val argIR = ArgIn[T]
    setArg(argN, N)
    setArg(argIR, initRank)

    val pageranks = DRAM[T](N)
    val lens = DRAM[Int](N)
    val ofsts = DRAM[Int](N)
    val edges = DRAM[Int](edgeData.length)
    setMem(lens, lenData)
    setMem(ofsts, ofstData)
    setMem(edges, edgeData)

    Accel { 
      Sequential.Foreach(iters by 1) { iter =>
        Foreach(0 until argN.value by ts par opN) { i =>
          val prTile = SRAM[T](ts)
          val lenTile = SRAM[Int](ts)
          val ofstTile = SRAM[Int](ts)
          val prRTile = SRAM[T](ts)
          ofstTile load ofsts(i :: i + ts par ipls)
          lenTile load lens(i :: i + ts par ipls)
          prRTile load pageranks(i :: i + ts par ipls)
          Foreach(ts by 1 par opts) { j =>
            val start = ofstTile(j)
            val len = lenTile(j)
            val neighbors = FIFO[Int](maxEdge)
            neighbors load edges(start::start+len)
            val farNeighbors = FIFO[Int](maxEdge)
            val farFIFO = FIFO[Bit](maxEdge)
            val nearIdxFIFO = FIFO[Int](maxEdge)
            Foreach(0 until len par ip) { k => 
              val neighbor = neighbors.deq
              nearIdxFIFO.enq(neighbor - i)
              val far = (neighbor < i) | (neighbor >= (i+ts))
              farFIFO.enq(far)
              farNeighbors.enq(neighbor, far)
            }
            val farCount = Reg[Int]
            Reduce(farCount)(0 until len par ip) { k => 
              mux(farFIFO.deq, 1, 0)
            } { _ + _ }
            val farNeighborRanks = FIFO[T](maxEdge)
            farNeighborRanks gather pageranks(farNeighbors, farCount.value)
            val farNeighborLens = FIFO[Int](maxEdge)
            farNeighborLens gather lens(farNeighbors, farCount.value)
            val rankSum = Reg[T]
            Reduce(rankSum)(0 until len) { k =>
              val neighbor = neighbors.deq
              val far = farFIFO.deq
              val near = !far
              val nearIdx = nearIdxFIFO.deq
              val nearNeighborRank = prRTile.read(Seq(nearIdx), Set(near))
              val nearNeighborLen = lenTile.read(Seq(nearIdx), Set(near))
              val neighborRank = mux(far, farNeighborRanks.deq(far), nearNeighborRank)
              val neighborLen = mux(far, farNeighborLens.deq(far), nearNeighborLen).to[T]
              val nrank = mux(iter===0, argIR.value, neighborRank)
              nrank / neighborLen
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
