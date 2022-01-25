import spatial.dsl._
import scala.math.Pi

class BoxMuller_0 extends BoxMuller

@spatial abstract class BoxMuller(
    len:scala.Int = 10000,
    tileSize:scala.Int = 100,
    op:scala.Int = 1
) extends SpatialTest { self =>

    type T = Float

    val ip:scala.Int = 16

    // turn array into 2d
    def boxMuller(uniform_input: Array[T]): Array[T] = {

        val half_len = len / 2

        val uniform_2d = (0::half_len, 0::2){(i, j) => uniform_input(i * 2 + j)}

        val uniform_d = DRAM[T](half_len, 2)
        setMem(uniform_d, uniform_input)

        val normal_d = DRAM[T](half_len, 2)

        Accel {
            Foreach(half_len by tileSize par op){tile =>

                val uniform_s = SRAM[T](tileSize, 2)
                uniform_s load uniform_d(tile::tile+tileSize, 0::2 par ip)

                val normal_s = SRAM[T](tileSize, 2)

                Foreach(tileSize by 2 par ip){i => 
                    // this i, i+1 stuff makes banking difficult
                    val u_1:T = uniform_s(i, 0)
                    val u_2:T = uniform_s(i, 1)
                    
                    val z_1:T = sqrt(-2 * ln(u_1)) * cos(2 * Pi * u_2)
                    val z_2:T = sqrt(-2 * ln(u_1)) * sin(2 * Pi * u_2)

                    normal_s(i, 0) = z_1
                    normal_s(i, 1) = z_2
                }

                normal_d(tile::tile+tileSize, 0::2 par ip) store normal_s
            }
        }

        val normal_2d = getMatrix(normal_d)
        Array.tabulate[T](len)(i => normal_2d(i / 2, i % 2))
    }


    def main(args: Array[String]): Unit = {
        val uniform = loadCSV1D[T](sys.env("SPATIAL_HOME") + "/test-data/box_muller_test/uniform.csv", "\n")
        val normal = boxMuller(uniform)

        writeCSV1D(normal, sys.env("SPATIAL_HOME") + "/test-data/box_muller_test/normal.csv", "\n")
        assert(true)
    }

}
