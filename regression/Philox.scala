import spatial.dsl._

class Philox_0 extends Philox

@spatial abstract class Philox(
    len:scala.Int = 128,
    tileSize:scala.Int = 32
) extends SpatialTest { self =>

    val op:scala.Int = 1
    val ip:scala.Int = 16

    val num_rounds:scala.Int = 7
    val mult_0:UInt32 = 0xD2511F53
    val mult_1:UInt32 = 0xCD9E8D57
    val weyl_0:UInt32 = 0x9E3779B9
    val weyl_1:UInt32 = 0xBB67AE85

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
        // (mulh(x, y), x * y)
    }

    def philox(ctr_0: UInt32,
               ctr_1: UInt32,
               ctr_2: UInt32,
               ctr_3: UInt32,
               key_0: UInt32,
               key_1: UInt32): Array[UInt32] = {

        val rand_d = DRAM[UInt32](len)

        Accel {
            val rand_s = SRAM[UInt32](tileSize)

            Foreach(len by tileSize par op){tile =>
                val _ctr_0 = SRAM[UInt32](tileSize).buffer
                val _ctr_1 = SRAM[UInt32](tileSize).buffer
                val _ctr_2 = SRAM[UInt32](tileSize).buffer
                val _ctr_3 = SRAM[UInt32](tileSize).buffer

                // initialize value of counters
                Foreach(tileSize by 1 par 1){i =>
                    // naive increment that assumes (ctr(0) + (i + tile)) never overflows
                    // TODO: iterates in a way that avoids complicated overflow logic
                    val offset:UInt32 = tile.to[UInt32] + i.to[UInt32]
                    _ctr_0(i) = ctr_0 + offset
                    _ctr_1(i) = ctr_1
                    _ctr_2(i) = ctr_2
                    _ctr_3(i) = ctr_3
                }

                // val actualTileSize = min(tileSize, len - tile)
                // Foreach(actualTileSize by 1 par ip){i =>

                // Philox rounds of computation
                Sequential.Foreach(num_rounds by 1){r =>
                    Foreach(tileSize by 1 par ip){i =>
                        val offset:UInt32 = tile.to[UInt32] + i.to[UInt32]

                        // key update according to weyl sequence
                        val _key_0:UInt32 = key_0 + (weyl_0 * r.to[UInt32])
                        val _key_1:UInt32 = key_1 + (weyl_1 * r.to[UInt32])

                        // philox sbox and pbox
                        val (hi_0, lo_0) = mul_32(_ctr_0(i), mult_0)
                        val (hi_1, lo_1) = mul_32(_ctr_2(i), mult_1)

                        _ctr_0(i) = hi_1 ^ _key_0 ^ _ctr_1(i)
                        _ctr_1(i) = lo_1
                        _ctr_2(i) = hi_0 ^ _key_1 ^ _ctr_3(i)
                        _ctr_3(i) = lo_0
                    }
                }

                // Selector function
                Foreach(tileSize by 1 par ip){i =>
                    // TODO: This is kind of an awkward way to implement such a simple mechanism

                    // set the ith rand to be ctr[offset % 4]
                    val offset:UInt32 = tile.to[UInt32] + i.to[UInt32]
                    val selector_0:UInt32 = offset & 1.to[UInt32]
                    val selector_1:UInt32 = offset & 2.to[UInt32]
                    rand_s(i) = mux(selector_0 == 0,
                                    mux(selector_1 == 0, _ctr_0(i), _ctr_2(i)),
                                    mux(selector_1 == 0, _ctr_1(i), _ctr_3(i)))
                }

                // rand_d(tile::tile+actualTileSize par ip) store rand_s
                rand_d(tile::tile+tileSize par ip) store rand_s
            }
        }

        getMem(rand_d)
    }

    def main(args: Array[String]): Unit = {
        val gold = loadCSV1D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand.csv", "\n")
        val rands = philox(0, 0, 0, 0, 0, 0)

        writeCSV1D(rands, sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand_out.csv", "\n")
        assert(rands == gold)
    }

}

