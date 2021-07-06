import spatial.dsl._
import utils.io.files

class SimHash_0 extends SimHash

@spatial class SimHash(K:scala.Int = 5, L:scala.Int = 3, ratio:scala.Int = 3) extends SpatialTest {
    type T = Float

    val ip = 16

    def mersenne_hash(i:Int, j:Int) : Int = {
        val MERSENNE_1 = 131071
        val MERSENNE_2 = 524287
        (i * MERSENNE_1) + (j * MERSENNE_2)
    }

    // def fnv_hash(i:Int, j:Int) : Int = {
    //     var FNV_PRIME = Reg[Int](0x1000193)
    //     var OFFSET_BASIS = Reg[Int](0x811C9DC5)

    //     var tmp = SRAM[Int](4)
    //     tmp(0) = i & 0xff
    //     tmp(1) = (i & 0xff00) >> 8
    //     tmp(2) = j & 0xff
    //     tmp(3) = (j & 0xff00) >> 8

    //     var hash = Reg[Int](OFFSET_BASIS)

    //     Foreach(0 until 4 by 1) {k=>
    //         hash = hash ^ tmp(k)
    //         hash = hash * FNV_PRIME
    //     }

    //     hash
    // }

    def sha256_hash(i:Int, j:Int) {
        val rand_bits = loadCSV2D[T](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/sha256_rand.csv", ",")
        rand_bits(i, j)
    }

    def simhash[T:Num](sparse: Boolean, input: SRAM1[T], active: SRAM1[Int], max: Int, K: Int, L: Int) = {
        val results = SRAM[Int](L)

        Foreach(0 until L by 1) { l => // every table
            val value = Reduce(Reg[Int])(0 until K by 1) {k => // every hash function
                val sum = Reduce(Reg[T])(0 until max by 1 par ip) { j => // sum across all inputs
                    val rand = if (sparse) mersenne_hash(K*l+k, active(j)) else mersenne_hash(K*l+k, j)
                    mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(j), -input(j)), 0)
                }{_+_}
                mux(sum.value < 0, 1.to[Int] << k.to[I16], 0)
            }{_|_}

            results(l) = value
        }
        
        results
    }

    def main(args: Array[String]) : Unit = {
        val dim = 50
        val vector = loadCSV1D[T](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/gold_input.csv", ",")
        writeCSV1D(vector, sys.env("SPATIAL_HOME") + "/test-data/sim_hash/spatial_input.csv", ",")
        val dramVector = DRAM[T](dim)
        setMem(dramVector, vector)
        val dramResult = DRAM[Int](L)
        Accel {
            val sramVector = SRAM[T](50)
            val active = SRAM[Int](0)
            sramVector load dramVector
            val sramResult = simhash(false, sramVector, active, 50, K, L)
            dramResult store sramResult
        }

        val out = getMem(dramResult)
        writeCSV1D(out, sys.env("SPATIAL_HOME") + "/test-data/sim_hash/spatial_out.csv", ",")
        val gold = loadCSV1D[Int](sys.env("SPATIAL_HOME") + "/test-data/sim_hash/gold_output.csv", ",")

        assert(out == gold, "SimHash failed, results do not match")
        println("PASSED: SimHash")
    }
} 
