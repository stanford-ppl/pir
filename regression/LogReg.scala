import spatial.dsl._

case class LogRegParam(
  iters:scala.Int = 4,
  D:scala.Int = 128,
  N:scala.Int = 8192,
  ts:scala.Int = 512,
  op:scala.Int = 1,
  mp:scala.Int = 1
) extends Param[LogRegParam]

class LogReg_0 extends LogReg

class LogReg_1 extends LogReg {override lazy val param = LogRegParam(mp=2)}
//class LogReg_2 extends LogReg {override lazy val param = LogRegParam(mp=3)}
//class LogReg_3 extends LogReg {override lazy val param = LogRegParam(mp=4)}
//class LogReg_4 extends LogReg {override lazy val param = LogRegParam(mp=5)}
//class LogReg_5 extends LogReg {override lazy val param = LogRegParam(mp=6)}
//class LogReg_6 extends LogReg {override lazy val param = LogRegParam(op=2)}
//class LogReg_7 extends LogReg {override lazy val param = LogRegParam(op=2,mp=1)}
//class LogReg_8 extends LogReg {override lazy val param = LogRegParam(op=2,mp=2)}
//class LogReg_9 extends LogReg {override lazy val param = LogRegParam(op=2,mp=4)}
//class LogReg_10 extends LogReg {override lazy val param = LogRegParam(op=3,mp=2)}

//class LogReg_2 extends LogReg {
  //override lazy val param = LogRegParam(mp=8)
//}

@spatial abstract class LogReg extends DSETest {

  type X = Float //FixPt[TRUE,_16,_16]

  lazy val param = LogRegParam()
  import param._

  val ip = 16
  val margin = 5
  val A = 1

  def logreg[T:Num](xIn: Array[T], yIn: Array[T], tt: Array[T], n: Int, it: Int): Array[T] = {

    val x = DRAM[T](N, D)
    val y = DRAM[T](N)
    val theta = DRAM[T](D)

    setMem(x, xIn)
    setMem(y, yIn)
    setMem(theta, tt)

    Accel {
      val btheta = SRAM[T](D)

      btheta load theta(0::D par ip)

      Sequential.Foreach(iters by 1) { epoch =>

        val grad = MemReduce(SRAM[T](D) par ip)(N by ts par op){ i =>
          val xTile = SRAM[T](ts, D)
          val yTile = SRAM[T](ts)
          Parallel {
            xTile load x(i::i+ts, 0::D par ip)
            yTile load y(i::i+ts par ip)
          }
          MemReduce(SRAM[T](D) par ip)(ts by 1 par mp) { ii =>
            val dot = Reduce(Reg[T])(D by 1 par ip) { d => xTile(ii,d) * btheta(d) } { _ + _ }
            val sub = Reg[T]
            Pipe { 
              sub := yTile(ii) - sigmoid[T](dot.value)
            }
            val gradRow = SRAM[T](D)
            Foreach(D by 1 par ip) { d => gradRow(d) = xTile(ii, d) * sub.value }
            gradRow
          } { _ + _ }
        } { _ + _ }

        Foreach(D by 1 par ip) { d => btheta(d) = btheta(d) + grad(d) * A.to[T] }

      }

      theta(0::D par ip) store btheta // read
    }


    getMem(theta)
  }


  def main(args: Array[String]): Unit = {
    val iters = args(0).to[Int]
    val N = args(1).to[Int]

    val sX = Array.fill(N){ Array.fill(D){ random[X](10.to[X])} }
    val sY = Array.tabulate(N){ i => i.to[X]} //fill(N)( random[T](10.0) )
    val theta = Array.fill(D) {random[X](1.to[X]) }

    val result = logreg(sX.flatten,sY, theta, N, iters)

    val gold = Array.empty[X](D)
    val ids = Array.tabulate(D){i => i}
    for (i <- 0 until D) {
      gold(i) = theta(i)
    }
    for (i <- 0 until iters) {
      val next = sX.zip(sY) {case (row, y) =>
        // println("sigmoid for " + y + " is " + sigmoid(row.zip(gold){_*_}.reduce{_+_}))
        val sub = y - sigmoid(row.zip(gold){(a,b) =>
          // println("doing " + a + " * " + b + " on row " + y)
          a*b}.reduce{_+_})
        row.map{a =>
          // println("subtraction for " + y + " is " + (a - sub))
          a - sub}
      }.reduce{(a,b) => a.zip(b){_+_}}
      for (i <- 0 until D) {
        gold(i) = gold(i) + next(i)
      }
      // printArr(gold, "gold now")
    }


    printArray(gold, "gold: ")
    printArray(result, "result: ")

    val cksum = result.zip(gold){ (a,b) => a > b-margin && a < b+margin}.reduce{_&&_}
    println("PASS: " + cksum  + " (LogReg)")
    assert(cksum)

  }

}
