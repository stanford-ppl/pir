import spatial.dsl._

class PCG32_0 extends PCG32

@spatial abstract class PCG32(
    len:scala.Int = 128,
    tileSize:scala.Int = 32
) extends SpatialTest { self =>

    val mult_lo:UInt32 = 0x4C957F2D
    val mult_hi:UInt32 = 0x5851F42D
    val incr_lo:UInt32  = 0xF767814F
    val incr_hi:UInt32  = 0x14057B7E
//     static const uint32_t mult_lo = 0x4C957F2D;
// static const uint32_t mult_hi = 0x5851F42D;
// static const uint32_t incr_lo = 0xF767814F;
// static const uint32_t incr_hi = 0x14057B7E;

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

    def add_64(a: UInt32, b: UInt32, c: UInt32, d: UInt32): (UInt32, UInt32) = {
        val ac:UInt32 = a + c
        val carry:UInt32 = mux(ac < a, 1.to[UInt32], 0.to[UInt32])
        (b + d + carry, ac)
    }

    def pcg32(seed_hi: UInt32, seed_lo: UInt32): Array[UInt32] = {
        val resDram = DRAM[UInt32](len)

        val multiplier_lo = ArgIn[UInt32]
        val multiplier_hi = ArgIn[UInt32]
        val increment_lo = ArgIn[UInt32]
        val increment_hi = ArgIn[UInt32]
        setArg(multiplier_lo, mult_lo)
        setArg(multiplier_hi, mult_hi)
        setArg(increment_lo, incr_lo)
        setArg(increment_hi, incr_hi)

        val _seed_lo = ArgIn[UInt32]
        val _seed_hi = ArgIn[UInt32]
        setArg(_seed_lo, seed_lo)
        setArg(_seed_hi, seed_hi)

        Accel {
            val (_init_hi, _init_lo) = add_64(seed_lo, seed_hi, increment_lo, increment_hi)
            val state_lo = Reg[UInt32](0)
            val state_hi = Reg[UInt32](0)
            // state_lo := _init_lo
            // state_hi := _init_hi

            val sram = SRAM[UInt32](tileSize)
            Foreach(len by tileSize par 1){tile =>
                // val actualTileSize = min(tileSize, len - tile)
                // Foreach(actualTileSize by 1 par 1){i =>
                Foreach(tileSize by 1 par 1){i =>
                    // val tmp = List.fill(13) { SRAM[UInt32](1, 1) }
                    val _s_lo = mux(i+tile == 0, _init_lo, state_lo)
                    val _s_hi = mux(i+tile == 0, _init_hi, state_hi)

                    val (mul_hi, mul_lo) = mul_64(_s_lo, _s_hi, multiplier_lo, multiplier_hi)
                    val (x_hi, x_lo) = add_64(mul_lo, mul_hi, increment_lo, increment_hi)
                    state_lo := x_lo
                    state_hi := x_hi

                    val count:I16 = (x_hi >> 27).to[I16]

                    val x_sh_hi:UInt32 = x_hi >> 18
                    val x_sh_lo:UInt32 = (x_hi << 14) | (x_lo >> 18)

                    val x_xsh_hi:UInt32 = x_hi ^ x_sh_hi
                    val x_xsh_lo:UInt32 = x_lo ^ x_sh_lo

                    val x_keep:UInt32 = (x_xsh_hi << 5) | (x_xsh_lo >> 27)

                    // rotate right by count
                    val rand = (x_keep >> count) | (x_keep << (-count & 31))

                    sram(i) = rand

                    // tmp(0)(0, 0) = _s_lo
                    // tmp(1)(0, 0) = _s_hi
                    // tmp(2)(0, 0) = mul_hi
                    // tmp(3)(0, 0) = mul_lo
                    // tmp(4)(0, 0) = x_hi
                    // tmp(5)(0, 0) = x_lo
                    // tmp(6)(0, 0) = count.to[UInt32]
                    // tmp(7)(0, 0) = x_sh_hi
                    // tmp(8)(0, 0) = x_sh_lo
                    // tmp(9)(0, 0) = x_xsh_hi
                    // tmp(10)(0, 0) = x_xsh_lo
                    // tmp(11)(0, 0) = x_keep
                    // tmp(12)(0, 0) = rand

                    // List.tabulate(13) { j => resDram(tile+i::tile+i+1, j::j+1) store tmp(j) }
                }
                // resDram(tile::tile+actualTileSize par 1) store sram
                resDram(tile::tile+tileSize par 1) store sram
            }
        }

        getMem(resDram)
    }

    def main(args: Array[String]): Unit = {
        val gold = loadCSV1D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/pcg_test/rand.csv", "\n")
        val rands = pcg32(0, 1234)
        writeCSV1D(rands, sys.env("SPATIAL_HOME") + "/test-data/pcg_test/rand_out.csv", "\n")
        assert(rands == gold)
        // assert(true)
    }

}

