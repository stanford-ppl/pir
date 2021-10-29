import spatial.dsl._

class Philox_0 extends Philox

@spatial abstract class Philox(
    len:scala.Int = 128,
    tileSize:scala.Int = 32
) extends SpatialTest { self =>

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
    }

    def philox(ctr: Array[UInt32], key: Array[UInt32]): Array[UInt32] = {
        val multiplier_0 = ArgIn[UInt32]
        val multiplier_1 = ArgIn[UInt32]
        val weyl_const_0 = ArgIn[UInt32]
        val weyl_const_1 = ArgIn[UInt32]
        setArg(multiplier_0, mult_0)
        setArg(multiplier_1, mult_1)
        setArg(weyl_const_0, weyl_0)
        setArg(weyl_const_1, weyl_1)

        val rand_d = DRAM[UInt32](len)

        val ctr_d = DRAM[UInt32](4)
        val key_d = DRAM[UInt32](2)
        setMem(ctr_d, ctr)
        setMem(key_d, key)

        Accel {
            val rand_s = SRAM[UInt32](tileSize)

            val ctr_s = SRAM[UInt32](4).buffer
            val key_s = SRAM[UInt32](2)
            ctr_s load ctr_d(0::4)
            key_s load key_d(0::2)

            val selector = Reg[Int](0)

            Foreach(len by tileSize par 1){tile =>
                // val actualTileSize = min(tileSize, len - tile)
                // Foreach(actualTileSize by 1 par 1){i =>
                Foreach(tileSize by 1 par 1){i =>
                    // make a copy of current counter and key
                    // TODO: better way to do this?
                    val _ctr = SRAM[UInt32](4).buffer
                    val _key = SRAM[UInt32](2).buffer
                    Foreach(4 by 1 par 4){j =>
                        _ctr(j) = ctr_s(j)
                    }
                    Foreach(2 by 1 par 2){j =>
                        _key(j) = key_s(j)
                    }

                    // Rounds of philox sbox and pbox
                    Foreach(num_rounds by 1 par 1){ k =>
                        // TODO: buffer hazard? Implement with Fold or Reduce?
                        val (hi_0, lo_0) = mul_32(_ctr(0), multiplier_0)
                        val (hi_1, lo_1) = mul_32(_ctr(2), multiplier_1)
                        _ctr(0) = hi_1 ^ _key(0) ^ _ctr(1)
                        _ctr(1) = lo_1
                        _ctr(2) = hi_0 ^ _key(1) ^ _ctr(3)
                        _ctr(3) = lo_0
                        _key(0) = _key(0) + weyl_const_0
                        _key(1) = _key(1) + weyl_const_1
                    }

                    rand_s(i) = _ctr(selector)
                    selector := (selector + 1) & 3

                    // TODO: this looks horrendous
                    // introduces bug "Setup Write SRAM twice in the same cycle."
                    // this also requires that ctr_s be declared as a buffer
                    ctr_s(0) = ctr_s(0) + 1
                    ctr_s(1) = mux(ctr_s(0) == 0, ctr_s(1) + 1, ctr_s(1))
                    ctr_s(2) = mux((ctr_s(0) == 0) & (ctr_s(1) == 0), ctr_s(2) + 1, ctr_s(2))
                    ctr_s(3) = mux((ctr_s(0) == 0) & (ctr_s(1) == 0) & (ctr_s(2) == 0), ctr_s(3) + 1, ctr_s(3))
                }
                // rand_d(tile::tile+actualTileSize par 1) store rand_s
                rand_d(tile::tile+tileSize par 1) store rand_s
            }
        }

        getMem(rand_d)
    }

    def main(args: Array[String]): Unit = {
        val gold = loadCSV1D[UInt32](sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand.csv", "\n")

        val ctr = Array.fill[UInt32](4)(0)
        val key = Array.fill[UInt32](2)(0)

        val rands = philox(ctr, key)
        writeCSV1D(rands, sys.env("SPATIAL_HOME") + "/test-data/philox_test/rand_out.csv", "\n")
        assert(rands == gold)
    }

}

