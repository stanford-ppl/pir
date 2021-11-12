import spatial.dsl._
class Squares_0 extends Squares
class Squares_2 extends Squares(op=2)
class Squares_4 extends Squares(op=4)
class Squares_8 extends Squares(op=8)

@spatial abstract class Squares(
    len:scala.Int = 131072,
    op:scala.Int = 1,
    tileSize:scala.Int = 8192
) extends SpatialTest { self =>

    val ip:scala.Int = 16

    def mul_32(x: UInt32, y: UInt32): (UInt32, UInt32) = {
        (mulh(x, y), x * y)
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

    def squares_rng(ctr_hi: UInt32, ctr_lo: UInt32, key_hi: UInt32, key_lo: UInt32): Array[UInt32] = {

        val rand_d = DRAM[UInt32](len)

        Accel {
            Foreach(len by tileSize par op){tile =>

                // val rand_out_fifo = FIFO[UInt32](16)
                val rand_s = SRAM[UInt32](tileSize)

                Foreach(tileSize by 1 par ip){i => 
                    val offset:UInt32 = tile.to[UInt32] + i.to[UInt32]
                    // _ctr = ctr + offset
                    val (_ctr_hi, _ctr_lo) = add_64(ctr_lo, ctr_hi, offset, 0)
                    
                    // y = ctr * key; z = y + key
                    val (y_hi, y_lo) = mul_64(_ctr_lo, _ctr_hi, key_lo, key_hi)
                    val (z_hi, z_lo) = add_64(y_lo, y_hi, key_lo, key_hi)

                    // x = y; x = x * x + y (round 1)
                    val (x_0_hi, x_0_lo) = mul_64(y_lo, y_hi, y_lo, y_hi)
                    val (x_1_hi, x_1_lo) = add_64(x_0_lo, x_0_hi, y_lo, y_hi)

                    // x = (x>>32) | (x<<32); x = x * x + z (round 2)
                    val (x_2_hi, x_2_lo) = mul_64(x_1_hi, x_1_lo, x_1_hi, x_1_lo)
                    val (x_3_hi, x_3_lo) = add_64(x_2_lo, x_2_hi, z_lo, z_hi)

                    // x = (x>>32) | (x<<32); x = x * x + y (round 3)
                    val (x_4_hi, x_4_lo) = mul_64(x_3_hi, x_3_lo, x_3_hi, x_3_lo)
                    val (x_5_hi, x_5_lo) = add_64(x_4_lo, x_4_hi, y_lo, y_hi)

                    // x = (x>>32) | (x<<32); x = x * x + z (round 4)
                    val (x_6_hi, x_6_lo) = mul_64(x_5_hi, x_5_lo, x_5_hi, x_5_lo)
                    val (x_7_hi, x_7_lo) = add_64(x_6_lo, x_6_hi, z_lo, z_hi)

                    // res = x >> 32
                    // rand_out_fifo.enq(x_7_hi, true)
                    rand_s(i) = x_7_hi
                }

                // rand_d(tile::tile+tileSize par ip) store rand_out_fifo
                rand_d(tile::tile+tileSize par ip) store rand_s
            }
        }

        getMem(rand_d)
    }

    def main(args: Array[String]): Unit = {
        val gold = loadCSV1D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/squares_test/rand.csv", "\n")
        val rands = squares_rng(0, 0, 0xc8e4fd15, 0x4ce32f6d)  // test_key = 0xc8e4fd154ce32f6d

        writeCSV1D(rands, sys.env("SPATIAL_HOME") + "/test-data/squares_test/rand_out.csv", "\n")
        assert(rands == gold)
    }

}


