import spatial.dsl._
import spatial.dsl._
import utils.io.files._

class NaivePageRank_0 extends NaivePageRank(ipls=1, ip=1)
class NaivePageRank_1 extends NaivePageRank(ipls=16, ip=16)
class NaivePageRank_2 extends NaivePageRank(opts=2,ipls=16, ip=16)
class NaivePageRank_3 extends NaivePageRank(opN=3,opts=3,ipls=16, ip=16)
class NaivePageRank_4 extends NaivePageRank(iters=2,ipls=1, ip=1)

@spatial class NaivePageRank(
  val ts: scala.Int=32,
  val iters:scala.Int = 1, // number of iterations
  val damp:scala.Float = 0.125f,
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
    val argIters = ArgIn[Int]
    setArg(argN, N)
    setArg(argIR, initRank)
    setArg(argIters, iters)

    val pageranks = DRAM[T](N)
    val lens = DRAM[Int](N)
    val ofsts = DRAM[Int](N)
    val edges = DRAM[Int](edgeData.length)
    setMem(lens, lenData)
    setMem(ofsts, ofstData)
    setMem(edges, edgeData)

    Accel { 
      Sequential.Foreach(argIters.value by 1) { iter =>
        Foreach(0 until argN.value by ts par opN) { i =>
          val prTile = SRAM[T](ts)
          val lenTile = SRAM[Int](ts)
          val ofstTile = SRAM[Int](ts)
          ofstTile load ofsts(i :: i + ts par ipls)
          lenTile load lens(i :: i + ts)
          Foreach(ts by 1 par opts) { j =>
            val start = ofstTile(j)
            val len = lenTile(j)
            val neighbors = FIFO[Int](maxEdge)
            neighbors load edges(start::start+len)
            val neighborRanks = FIFO[T](maxEdge)
            neighborRanks gather pageranks(neighbors, len)
            val neighborLens = FIFO[Int](maxEdge)
            neighborLens gather lens(neighbors, len)
            val rankSum = Reg[T]
            Reduce(rankSum)(0 until len par ip) { k =>
              val nrank = mux(iter===0, argIR.value, neighborRanks.deq)
              nrank / neighborLens.deq.to[T]
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

class NaivePageRank2_0 extends NaivePageRank2()(ipls=1, ip=1)
class NaivePageRank2_1 extends NaivePageRank2()(ipls=16, ip=16)
class NaivePageRank2_2 extends NaivePageRank2()(opts=2,ipls=16, ip=16)
class NaivePageRank2_3 extends NaivePageRank2()(opN=3,opts=3,ipls=16, ip=16)
class NaivePageRank2_4 extends NaivePageRank2(iters=2)(ipls=1, ip=1)

@spatial class NaivePageRank2(
  val ts: scala.Int=32,
  val ets:scala.Int = 16,
  val iters:scala.Int = 1, // number of iterations
  val damp:scala.Float = 0.125f,
)(
  opN:scala.Int = 1, // N / ts
  opE:scala.Int = 1,
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
    val argIters = ArgIn[Int]
    setArg(argN, N)
    setArg(argIR, initRank)
    setArg(argIters, iters)

    val pageranks = DRAM[T](N)
    val lens = DRAM[Int](N)
    val ofsts = DRAM[Int](N)
    val edges = DRAM[Int](edgeData.length)
    setMem(lens, lenData)
    setMem(ofsts, ofstData)
    setMem(edges, edgeData)

    Accel { 
      Sequential.Foreach(argIters.value by 1) { iter =>
        Foreach(0 until argN.value by ts par opN) { i =>
          val prTile = SRAM[T](ts)
          val lenTile = SRAM[Int](ts)
          val ofstTile = SRAM[Int](ts)
          val end = min(i + ts, argN.value)
          ofstTile load ofsts(i :: end par ipls)
          lenTile load lens(i :: end)
          Foreach(ts by 1 par opts) { j =>
            val start = ofstTile(j)
            val len = lenTile(j)
            val rank = Reg[T](0.to[T])
            Reduce(rank)(len by ets par opE) { et =>
              val etsBound = min(ets, len - et)
              val neighbors = FIFO[Int](ets)
              val tileStart = start + et
              neighbors load edges(tileStart::tileStart+etsBound)
              val neighborRanks = FIFO[T](ets)
              neighborRanks gather pageranks(neighbors, etsBound)
              val neighborLens = FIFO[Int](ets)
              neighborLens gather lens(neighbors, etsBound)
              val rankSum = Reg[T](0.to[T])
              Reduce(rankSum)(0 until etsBound par ip) { k =>
                val nrank = mux(iter===0, argIR.value, neighborRanks.deq)
                nrank / neighborLens.deq.to[T]
              } { _ + _ }
              rankSum.value
            } { _ + _ }
            prTile(j) = rank.value * damp + ((1-damp).to[T] / argN.value.to[T])
          }
          pageranks(i::end par ipls) store prTile
        }
      }
    }

    checkGold(pageranks)
  }

}
