import spatial.dsl._
import utils.io.files

class SimHash_0 extends SimHash

@spatial abstract class SimHash (K:scala.Int = 5) extends SpatialTest {
  type T = Float
  val L = 3
  val ratio = 3
  val ip = 16

  def mersenne_hash(i:Int, j:Int, k:Int, l:Int) : Int = {
    val MERSENNE_1 = 127
    val MERSENNE_2 = 8191
    val MERSENNE_3 = 131071
    val MERSENNE_4 = 524287
    (i * MERSENNE_1) + (j * MERSENNE_2) + (k * MERSENNE_3) + (l * MERSENNE_4)
  }

  def d_simhash[T:Num](input: SRAM2[T]): SRAM2[Int] = {
    val results = SRAM[Int](input.rows, L)
    // for every vector in input
    Foreach(0 until input.rows by 1) { i =>
      // for every hash table
      Foreach(0 until L by 1) { l =>
        // for every hash function
        val active = Reduce(Reg[Int])(0 until K by 1) {k =>
          // for every element in a vector
          val sum = Reduce(Reg[T])(0 until input.cols by 1) { j =>
            val rand = mersenne_hash(i, j, k, l)
            mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(i, j), -input(i, j)), 0)
          }{_+_}
          mux(sum.value < 0, 1.to[Int] << k.to[I16], 0.to[Int])
        }{_|_}
        results(i, l) = active
      }
    }
    results
  }

  def main(args: Array[String]) : Unit = {
    val dim_0 = 100
    val dim_1 = 50
    val vector = loadCSV2D[T](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/test_input.csv", "\n")
    val dramVector = DRAM[T](dim_0, dim_1)
    setMem(dramVector, vector)
    val dramResult = DRAM[Int](dim_0, L)
    Accel {
      val sramVector = SRAM[T](dim_0, dim_1)
      sramVector load dramVector(0::dim_0, 0::dim_1 par 16)
      /* Foreach (dim_0 by 1) { i =>
        val tmp = SRAM[T](dim_1)
        tmp load dramVector(i, 0::dim_1 par ip)
        Foreach (dim_1 par ip) { j =>
          sramVector(i, j) = tmp(j)
        }
      } */
      val sramResult = d_simhash(sramVector)
      dramResult(0::dim_0, 0::L par ip) store sramResult
    }

    val out = getMatrix(dramResult)
    val gold = loadCSV2D[Int](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/test_output.csv", ",")

    assert(out == gold, "SimHash failed, results do not match")
    println("PASSED: SimHash")
  }
} 
