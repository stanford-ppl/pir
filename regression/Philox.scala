import argon._
import spatial.metadata.memory._

import spatial.dsl._

// --merge=false pir (bin/test)
class Philox_0 extends Philox

@spatial abstract class Philox(
    len:scala.Int = 32768,
    tileSize:scala.Int = 8192,
    num_rounds:scala.Int = 7
) extends SpatialTest { self =>

    val op:scala.Int = 1
    val ip:scala.Int = 16

    val mult_0:UInt32 = 0xD2511F53
    val mult_1:UInt32 = 0xCD9E8D57
    val weyl_0:UInt32 = 0x9E3779B9
    val weyl_1:UInt32 = 0xBB67AE85

    def mul_32(x: UInt32, y: UInt32): (UInt32, UInt32) = {
        // val a:UInt32 = x & 0xFFFF
        // val b:UInt32 = x >> 16
        // val c:UInt32 = y & 0xFFFF
        // val d:UInt32 = y >> 16

        // val ac:UInt32 = a * c
        // val ad:UInt32 = a * d
        // val bc:UInt32 = b * c
        // val bd:UInt32 = b * d

        // val ad_hi:UInt32 = ad >> 16
        // val ad_lo:UInt32 = ad << 16
        // val bc_hi:UInt32 = bc >> 16
        // val bc_lo:UInt32 = bc << 16

        // val acad:UInt32 = ac + ad_lo
        // val carry1:UInt32 = mux(acad < ac, 1.to[UInt32], 0.to[UInt32])
        // val bot:UInt32 = acad + bc_lo
        // val carry2:UInt32 = mux(bot < acad, 1.to[UInt32], 0.to[UInt32])

        // val top:UInt32 = bd + ad_hi + bc_hi + carry1 + carry2

        // (top, bot)
        (mulh(x, y), x * y)
    }

    def philox_round(key_0: UInt32,
                     key_1: UInt32,
                     r:     scala.Int,
                     ctr_0: UInt32,
                     ctr_1: UInt32,
                     ctr_2: UInt32,
                     ctr_3: UInt32): (UInt32, UInt32, UInt32, UInt32) = {

        // this could be one hardware block instead of two
        val _key_0:UInt32 = key_0 + (weyl_0 * r.to[UInt32])
        val _key_1:UInt32 = key_1 + (weyl_1 * r.to[UInt32])

        val (hi_0, lo_0) = mul_32(ctr_0, mult_0)
        val (hi_1, lo_1) = mul_32(ctr_2, mult_1)

        val _ctr_0_next = hi_1 ^ _key_0 ^ ctr_1
        val _ctr_1_next = lo_1
        val _ctr_2_next = hi_0 ^ _key_1 ^ ctr_3
        val _ctr_3_next = lo_0

        (_ctr_0_next, _ctr_1_next, _ctr_2_next, _ctr_3_next)
    }

    def philox(ctr_0: UInt32,
               ctr_1: UInt32,
               ctr_2: UInt32,
               ctr_3: UInt32,
               key_0: UInt32,
               key_1: UInt32): Array[UInt32] = {

        val rand_d = DRAM[UInt32](len)

        Accel {
            // Write meta loop for 2^32 blocks, so overflow is easy to handle
            Foreach(len by tileSize par op){tile =>

                // val _ctr_0 = SRAM[UInt32](tileSize)
                // val _ctr_1 = SRAM[UInt32](tileSize)
                // val _ctr_2 = SRAM[UInt32](tileSize)
                // val _ctr_3 = SRAM[UInt32](tileSize)

                // _ctr_0.asInstanceOf[Sym[_]].setMemReduceAccum
                // _ctr_1.asInstanceOf[Sym[_]].setMemReduceAccum
                // _ctr_2.asInstanceOf[Sym[_]].setMemReduceAccum
                // _ctr_3.asInstanceOf[Sym[_]].setMemReduceAccum

                val rand_out_fifo = FIFO[UInt32](16)

                // Foreach(num_rounds by 1 par 1){r =>
                Pipe.Foreach(tileSize by 1 par ip){i => 
                    val offset:UInt32 = tile.to[UInt32] + i.to[UInt32]

                    // val (res_0, res_1, res_2, res_3) = philox_rounds(key_0, key_1, num_rounds, ctr_0 + offset, ctr_1, ctr_2, ctr_3)
                    val (ctr_0_1, ctr_1_1, ctr_2_1, ctr_3_1) = philox_round(key_0, key_1, 0, ctr_0 + offset, ctr_1, ctr_2, ctr_3)
                    val (ctr_0_2, ctr_1_2, ctr_2_2, ctr_3_2) = philox_round(key_0, key_1, 1, ctr_0_1, ctr_1_1, ctr_2_1, ctr_3_1)
                    val (ctr_0_3, ctr_1_3, ctr_2_3, ctr_3_3) = philox_round(key_0, key_1, 2, ctr_0_2, ctr_1_2, ctr_2_2, ctr_3_2)
                    val (ctr_0_4, ctr_1_4, ctr_2_4, ctr_3_4) = philox_round(key_0, key_1, 3, ctr_0_3, ctr_1_3, ctr_2_3, ctr_3_3)
                    val (ctr_0_5, ctr_1_5, ctr_2_5, ctr_3_5) = philox_round(key_0, key_1, 4, ctr_0_4, ctr_1_4, ctr_2_4, ctr_3_4)
                    val (ctr_0_6, ctr_1_6, ctr_2_6, ctr_3_6) = philox_round(key_0, key_1, 5, ctr_0_5, ctr_1_5, ctr_2_5, ctr_3_5)
                    val (ctr_0_7, ctr_1_7, ctr_2_7, ctr_3_7) = philox_round(key_0, key_1, 6, ctr_0_6, ctr_1_6, ctr_2_6, ctr_3_6)

                    // val _key_0:UInt32 = key_0 + (weyl_0 * r.to[UInt32])
                    // val _key_1:UInt32 = key_1 + (weyl_1 * r.to[UInt32])

                    // // philox sbox and pbox
                    // val (hi_0, lo_0) = mul_32(mux(r == 0, ctr_0 + offset, _ctr_0(i)), mult_0)
                    // val (hi_1, lo_1) = mul_32(mux(r == 0, ctr_2, _ctr_2(i)), mult_1)

                    // val _ctr_0_next = hi_1 ^ _key_0 ^ mux(r == 0, ctr_1, _ctr_1(i))
                    // val _ctr_1_next = lo_1
                    // val _ctr_2_next = hi_0 ^ _key_1 ^ mux(r == 0, ctr_3, _ctr_3(i))
                    // val _ctr_3_next = lo_0

                    // _ctr_0(i) = _ctr_0_next
                    // _ctr_1(i) = _ctr_1_next
                    // _ctr_2(i) = _ctr_2_next
                    // _ctr_3(i) = _ctr_3_next

                    // set the ith rand to be ctr[offset % 4]
                    val selector_0:UInt32 = offset & 1.to[UInt32]
                    val selector_1:UInt32 = offset & 2.to[UInt32]
                    val rand_num = mux(selector_0 == 0,
                                        mux(selector_1 == 0, ctr_0_7, ctr_2_7),
                                        mux(selector_1 == 0, ctr_1_7, ctr_3_7))
                    rand_out_fifo.enq(rand_num, true)
                    // val rand_num = mux(selector_0 == 0,
                    //                     mux(selector_1 == 0, _ctr_0_next, _ctr_2_next),
                    //                     mux(selector_1 == 0, _ctr_1_next, _ctr_3_next))
                    // rand_out_fifo.enq(rand_num, true)
                    // rand_out_fifo.enq(rand_num, r == num_rounds - 1)
                }
                // }

                rand_d(tile::tile+tileSize par ip) store rand_out_fifo
            }
        }

        getMem(rand_d)
    }

    def main(args: Array[String]): Unit = {
        val gold = loadCSV1D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand.csv", "\n")
        val rands = philox(0, 0, 0, 0, 0, 0)

        writeCSV1D(rands, sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand_out.csv", "\n")
        assert(rands == gold)
        // assert(true)
    }

}

