import spatial.dsl._
import virtualized._
import scala.util.Sorting.quickSort

object SimHash extends SpatialApp {

  @virtualize
  def main() {

    // initialization
    // only perform once

    val dimension = ArgIn[Int]
    setArg(dimension, args(0).to[Int])

    // BUG: cannot pass more than one command line argument
    val numHashes = ArgIn[Int]
    // setArg(numHashes, args(1).to[Int])
    setArg(numHashes, 8.to[Int])

    val ratio = ArgIn[Float]
    // setArg(ratio, args(2).to[Float])
    setArg(ratio, 0.3.to[Float])

    val r = scala.util.Random

    // BUG: all values are the same, the code block inside is only running once
    val randBits = (0::numHashes, 0::dimension){(i, j) => 
      if (r.nextFloat() > ratio)
        0
      else if (r.nextInt(2) > 0)
        1
      else
        -1
    }
    val dramRandBits = DRAM[Int](numHashes, dimension)
    setMem(dramRandBits, randBits)

    // end initialization

    val dramResults = DRAM[Int](numHashes)

    val vector = loadCSV1D[Float](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/test_input.csv", "\n")

    val dramVector = DRAM[Float](dimension)
    setMem(dramVector, vector)

    val tileSize = 64

    Accel {
      Foreach(numHashes by 1){i =>
        val sum = Reg[Float]
        val res = RegFile[Int](1)

        sum := Reduce(Reg[Float](0))(dimension by tileSize par 2){tile =>
            val numel = min(tileSize.to[Int], dimension - tile)
            val rBits = SRAM[Int](tileSize)
            val input = SRAM[Float](tileSize)

            rBits load dramRandBits(i, tile :: tile + numel)
            input load dramVector(tile :: tile + numel)

            Reduce(Reg[Float](0))(numel by 1 par 4){j => rBits(j).to[Float] * input(j)}{_+_}
        }{_+_}

        if (sum < 0)
          res(0) = 1
        else
          res(0) = 0

        dramResults(i) store res
      }
    }

    val results = getMem(dramResults)
    writeCSV2D[Int](randBits, sys.env("SPATIAL_HOME") + "/test-data/sim_hash/test_rand_bits.csv", ",")
    writeCSV1D[Int](results, sys.env("SPATIAL_HOME") + "/test-data/sim_hash/test_output.csv", "\n")

  }
} 