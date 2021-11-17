import spatial.dsl._

@spatial class MulTest extends SpatialTest { self =>

    val N:scala.Int = 100
    val op:scala.Int = 1

    def mul_32(x: UInt32, y: UInt32): (UInt32, UInt32) = {
        (mulh(x, y), x * y)
    }

    def mul_64(a: UInt32, b: UInt32, c: UInt32, d: UInt32): (UInt32, UInt32) = {
        val (ac_hi, ac_lo) = mul_32(a, c)
        val ad = a * d
        val bc = b * c
        (ac_hi + ad + bc, ac_lo)
    }

    def main(args: Array[String]): Unit = {
        val hi = ArgOut[UInt32]
        val lo = ArgOut[UInt32]
        Accel {
            val (_hi, _lo) = mul_32(0, 12345)
            hi := _hi
            lo := _lo
        }
        println(hi)
        println(lo)
        assert(hi == 0.to[UInt32])
        assert(lo == 0.to[UInt32])
        // val input = loadCSV2D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/mul_test/test_input.csv", ",")
        // val dramInput = DRAM[UInt32](N, 4)
        // setMem(dramInput, input)

        // val dramOutput = DRAM[UInt32](N, 2)

        // Accel {
        //     val sramInput = SRAM[UInt32](N, 4)
        //     sramInput load dramInput(0::N, 0::4 par op)

        //     val sramOutput = SRAM[UInt32](N, 2)
        //     Foreach (N by 1 par op) { i =>
        //         val (top, bot) = mul_64(sramInput(i, 0), sramInput(i, 1), sramInput(i, 2), sramInput(i, 3))
        //         sramOutput(i, 0) = top
        //         sramOutput(i, 1) = bot
        //     }
        //     dramOutput(0::N, 0::2 par op) store sramOutput
        // }

        // val out = getMatrix(dramOutput)
        // val gold = loadCSV2D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/mul_test/test_output.csv", ",")

        // writeCSV2D[UInt32](out, sys.env("SPATIAL_HOME") + "/test-data/mul_test/results.csv", delim1=",")

        // assert(out == gold)
    }

}