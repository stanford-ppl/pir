import spatial.dsl._

@spatial class MulTest extends SpatialTest { self =>

    val N:scala.Int = 100
    val op:scala.Int = 1

    def mul_32(x: UInt32, y: UInt32): (UInt32, UInt32) = {
        val a:UInt32 = x & 0xFFFF
        val b:UInt32 = x >> 16
        val c:UInt32 = y & 0xFFFF
        val d:UInt32 = y >> 16

        val ac:UInt32 = a * c
        val ad:UInt32 = a * d
        val bc:UInt32 = b * c
        val bd:UInt32 = b * d

        val ad_hi:UInt32 = ad >> 16
        val ad_lo:UInt32 = ad << 16
        val bc_hi:UInt32 = bc >> 16
        val bc_lo:UInt32 = bc << 16

        val acad:UInt32 = ac + ad_lo
        val carry1:UInt32 = mux(acad < ac, 1.to[UInt32], 0.to[UInt32])
        val bot:UInt32 = acad + bc_lo
        val carry2:UInt32 = mux(bot < acad, 1.to[UInt32], 0.to[UInt32])

        val top:UInt32 = bd + ad_hi + bc_hi + carry1 + carry2

        (top, bot)
    }

    def mul_64(a: UInt32, b: UInt32, c: UInt32, d: UInt32): (UInt32, UInt32) = {
        val (ac_hi, ac_lo) = mul_32(a, c)
        val ad = a * d
        val bc = b * c
        (ac_hi + ad + bc, ac_lo)
    }

    def main(args: Array[String]): Unit = {
        val input = loadCSV2D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/mul_test/test_input.csv", ",")
        val dramInput = DRAM[UInt32](N, 4)
        setMem(dramInput, input)

        val dramOutput = DRAM[UInt32](N, 2)

        Accel {
            val sramInput = SRAM[UInt32](N, 4)
            sramInput load dramInput(0::N, 0::4 par op)

            val sramOutput = SRAM[UInt32](N, 2)
            Foreach (N by 1 par op) { i =>
                val (top, bot) = mul_64(sramInput(i, 0), sramInput(i, 1), sramInput(i, 2), sramInput(i, 3))
                sramOutput(i, 0) = top
                sramOutput(i, 1) = bot
            }
            dramOutput(0::N, 0::2 par op) store sramOutput
        }

        val out = getMatrix(dramOutput)
        val gold = loadCSV2D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/mul_test/test_output.csv", ",")

        writeCSV2D[UInt32](out, sys.env("SPATIAL_HOME") + "/test-data/mul_test/results.csv", delim1=",")

        assert(out == gold)
    }

}