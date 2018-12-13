import spatial.dsl._

case class GDAParam(
  C:scala.Int = 128,
  R:scala.Int = 1024,
  ts:scala.Int = 256,
  op:scala.Int = 1,
  mp1:scala.Int = 1,
  mp2:scala.Int = 1
) extends Param[GDAParam]

class GDA_0 extends GDA
//class GDA_1 extends GDA {override lazy val param = GDAParam(mp1 = 8,mp2 = 1)}
class GDA_2 extends GDA {override lazy val param = GDAParam(mp1 = 4,mp2 = 2)}
class GDA_3 extends GDA {override lazy val param = GDAParam(mp1 = 2,mp2 = 4)}
class GDA_4 extends GDA {override lazy val param = GDAParam(mp1 = 6,mp2 = 1)}
class GDA_5 extends GDA {override lazy val param = GDAParam(mp1 = 5,mp2 = 1)}
class GDA_6 extends GDA {override lazy val param = GDAParam(mp1 = 4,mp2 = 1)}
class GDA_7 extends GDA {override lazy val param = GDAParam(mp1 = 1,mp2 = 4)}
class GDA_8 extends GDA {override lazy val param = GDAParam(mp1 = 1,mp2 = 2)}
class GDA_9 extends GDA {override lazy val param = GDAParam(mp1 = 2,mp2 = 1)}
class GDA_11 extends GDA {override lazy val param = GDAParam(mp1 = 3,mp2 = 1)}
class GDA_12 extends GDA {override lazy val param = GDAParam(mp1 = 1,mp2 = 3)}
class GDA_13 extends GDA {override lazy val param = GDAParam(op = 2,mp1 = 1,mp2 = 1)}

@spatial abstract class GDA extends DSETest { self => // Regression (Dense) // Args: 64

  type X = Float

  lazy val param = GDAParam()
  import param._

  val ip = 16
  val margin = 1

  def gda[T: Num](xCPU: Array[T], yCPU: Array[Int], mu0CPU: Array[T], mu1CPU: Array[T]) = {

    //val R = ArgIn[Int]
    //setArg(R, yCPU.length); bound(yCPU.length) = self.R

    val x = DRAM[T](R, C)
    val y = DRAM[Int](R)
    val mu0 = DRAM[T](C)
    val mu1 = DRAM[T](C)
    val sigma = DRAM[T](C, C)

    setMem(x, xCPU)
    setMem(y, yCPU)
    setMem(mu0, mu0CPU)
    setMem(mu1, mu1CPU)

    Accel {
      val mu0Tile = SRAM[T](C)
      val mu1Tile = SRAM[T](C)
      Parallel {
        mu0Tile load mu0(0 :: C par ip) // Load mu0
        mu1Tile load mu1(0 :: C par ip) // Load mu1
      }

      val sigmaOut = SRAM[T](C, C)

      MemReduce(sigmaOut par ip)(R by ts par op){ r =>
        val gdaYtile = SRAM[Int](ts)
        val gdaXtile = SRAM[T](ts, C)
        Parallel {
          gdaYtile load y(r :: r + ts par ip)
          gdaXtile load x(r :: r + ts, 0 :: C par ip) // Load tile of x
        }

        val sigmaBlk = SRAM[T](C, C)

        MemReduce(sigmaBlk par ip)(ts par mp1) { rr =>
          val subTile = SRAM[T](C)
          val sigmaTile = SRAM[T](C, C)
          Foreach(C par ip) { cc =>
            subTile(cc) = gdaXtile(rr, cc) - mux(gdaYtile(rr) == 1, mu1Tile(cc), mu0Tile(cc))
          }
          Foreach(C by 1 par mp2) { ii =>
            Foreach(C par ip) { jj =>
              sigmaTile(ii, jj) = subTile(ii) * subTile(jj)
            }
          }
          sigmaTile
        }{_+_}
      }{_+_}

      sigma(0 :: C, 0 :: C par ip) store sigmaOut
    }

    getMem(sigma)
  }

  def main(args: Array[String]): Unit = {
    // val C = args(0).to[SInt] // TODO: Should be selectable up to maximum

    // val x  = Array.fill(R){ Array.fill(C){ random[X](10) }}
    // val ys = Array.fill(R){ random[Int](1) }
    // val mu0 = Array.fill(C){ random[X](10) }
    // val mu1 = Array.fill(C){ random[X](10) }

    val x = Array.tabulate(R) { i => Array.tabulate(C) { j => (i * C + j) % 256 } }
    val ys = Array.tabulate(R) { i => i % 256 }
    val mu0 = Array.tabulate(C) { i => i % 2 }
    val mu1 = Array.tabulate(C) { i => i % 2 }

    val result = gda(x.flatten, ys, mu0, mu1)

    val gold = x.zip(ys) { (row, y) =>
      val sub = if (y == 1) row.zip(mu1){_-_} else row.zip(mu0) {_-_}
      Array.tabulate(C) { i => Array.tabulate(C) { j => sub(i) * sub(j) } }.flatten
    }.reduce { (a, b) => a.zip(b) {_+_} }

    printArray(gold, "gold: ")
    printArray(result, "result: ")

    val cksum = gold.zip(result){ case (a,b) => a < b + margin && a > b - margin }.reduce{_&&_}
    println("PASS: " + cksum  + " (GDA)")
    assert(cksum)
  }

}
