import spatial.dsl._
import spatial.dsl._
import utils.io.files._

class NaivePageRank_0 extends NaivePageRank()(ipls=1, ip=1)
class NaivePageRank_1 extends NaivePageRank()(ipls=16, ip=16)
class NaivePageRank_2 extends NaivePageRank()(opts=2,ipls=16, ip=16)
class NaivePageRank_3 extends NaivePageRank()(opN=3,opts=3,ipls=16, ip=16)
class NaivePageRank_4 extends NaivePageRank(iters=2)(ipls=1, ip=1)

@spatial class NaivePageRank(
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
          ofstTile load ofsts(i :: i + ts par ipls)
          lenTile load lens(i :: i + ts par ipls)
          Foreach(ts by 1 par opts) { j =>
            val start = ofstTile(j)
            val len = lenTile(j)
            val neighbors = SRAM[Int](maxEdge)
            neighbors load edges(start::start+len)
            val neighborRanks = SRAM[T](maxEdge)
            neighborRanks gather pageranks(neighbors par ipls, len)
            val neighborLens = SRAM[Int](maxEdge)
            neighborLens gather lens(neighbors par ipls, len)
            val rankSum = Reg[T]
            Reduce(rankSum)(0 until len par ip) { k =>
              val nrank = mux(iter===0, argIR.value, neighborRanks(k))
              nrank / neighborLens(k).to[T]
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
