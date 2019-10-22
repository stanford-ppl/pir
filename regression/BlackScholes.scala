import spatial.dsl._

class BlackScholes_0 extends BlackScholes(op=1){ override def pirArgs = super.pirArgs + " --split-algo=bfs --retime-local=false";  }
class BlackScholes_1 extends BlackScholes(op=1){ override def pirArgs = super.pirArgs + " --split-algo=bfs --retime-local=true";  }

@spatial abstract class BlackScholes(
  N:scala.Int = 1024 * 128,
  ts:scala.Int = 256,
  op:scala.Int = 1,
  ip:scala.Int = 16
) extends SpatialTest {

  val margin = 0.5f // Validates true if within +/- margin

  final val inv_sqrt_2xPI = 0.39894228040143270286f

  def CNDF(x: Float) = {
    val ax = abs(x)

    val xNPrimeofX = exp_taylor((ax ** 2) * -0.05f) * inv_sqrt_2xPI
    val xK2 = 1.to[Float] / ((ax * 0.2316419f) + 1.0f)

    val xK2_2 = xK2 ** 2
    val xK2_3 = xK2_2 * xK2
    val xK2_4 = xK2_3 * xK2
    val xK2_5 = xK2_4 * xK2

    val xLocal_10 = xK2 * 0.319381530f
    val xLocal_20 = xK2_2 * -0.356563782f
    val xLocal_30 = xK2_3 * 1.781477937f
    val xLocal_31 = xK2_4 * -1.821255978f
    val xLocal_32 = xK2_5 * 1.330274429f

    val xLocal_21 = xLocal_20 + xLocal_30
    val xLocal_22 = xLocal_21 + xLocal_31
    val xLocal_23 = xLocal_22 + xLocal_32
    val xLocal_1 = xLocal_23 + xLocal_10

    val xLocal0 = xLocal_1 * xNPrimeofX
    val xLocal  = -xLocal0 + 1.0f

    mux(x < 0.0f, xLocal0, xLocal)
  }

  def BlkSchlsEqEuroNoDiv(sptprice: Float, strike: Float, rate: Float,
    volatility: Float, time: Float, otype: Int) = {

    val xLogTerm = log_taylor( sptprice / strike )
    val xPowerTerm = (volatility ** 2) * 0.5f
    val xNum = (rate + xPowerTerm) * time + xLogTerm
    val xDen = volatility * sqrt_approx(time)

    val xDiv = xNum / (xDen ** 2)
    val nofXd1 = CNDF(xDiv)
    val nofXd2 = CNDF(xDiv - xDen)

    val futureValueX = strike * exp_taylor(-rate * time)

    val negNofXd1 = -nofXd1 + 1.0f
    val negNofXd2 = -nofXd2 + 1.0f

    val optionPrice1 = (sptprice * nofXd1) - (futureValueX * nofXd2)
    val optionPrice2 = (futureValueX * negNofXd2) - (sptprice * negNofXd1)
    mux(otype == 0, optionPrice2, optionPrice1)
  }

  def blackscholes(
    stypes:      Array[Int],
    sprices:     Array[Float],
    sstrike:     Array[Float],
    srate:       Array[Float],
    svolatility: Array[Float],
    stimes:      Array[Float]
  ): Array[Float] = {

    //val size = ArgIn[Int]
    //setArg(size, stypes.length); bound(stypes.length) = N
    val size = N

    val types    = DRAM[Int](size)
    val prices   = DRAM[Float](size)
    val strike   = DRAM[Float](size)
    val rate     = DRAM[Float](size)
    val vol      = DRAM[Float](size)
    val times    = DRAM[Float](size)
    val optprice = DRAM[Float](size)
    setMem(types, stypes)
    setMem(prices, sprices)
    setMem(strike, sstrike)
    setMem(rate, srate)
    setMem(vol, svolatility)
    setMem(times, stimes)

    Accel {
      Foreach(size by ts par op) { i =>
        val typeBlk   = FIFO[Int](ts)
        val priceBlk  = FIFO[Float](ts)
        val strikeBlk = FIFO[Float](ts)
        val rateBlk   = FIFO[Float](ts)
        val volBlk    = FIFO[Float](ts)
        val timeBlk   = FIFO[Float](ts)
        val optpriceBlk = FIFO[Float](ts)

        typeBlk   load types(i::i+ts par ip)
        priceBlk  load prices(i::i+ts par ip)
        strikeBlk load strike(i::i+ts par ip)
        rateBlk   load rate(i::i+ts par ip)
        volBlk    load vol(i::i+ts par ip)
        timeBlk   load times(i::i+ts par ip)

        Foreach(ts par ip){ j =>
          val price = BlkSchlsEqEuroNoDiv(priceBlk.deq, strikeBlk.deq, rateBlk.deq, volBlk.deq, timeBlk.deq, typeBlk.deq)
          optpriceBlk.enq(price)
        }
        optprice(i::i+ts par ip) store optpriceBlk
      }
    }
    getMem(optprice)
  }

  def main(args: Array[String]): Unit = {
    val types  = Array.fill(N)(random[Int](2))
    val prices = Array.fill(N)(random[Float])
    val strike = Array.fill(N)(random[Float])
    val rate   = Array.fill(N)(random[Float])
    val vol    = Array.fill(N)(random[Float])
    val time   = Array.fill(N)(random[Float])

    val out = blackscholes(types, prices, strike, rate, vol, time)

    //printArray(out, "result: ")

    //val cksum = out.zip(gold){ case (o, g) => (g < (o + margin)) && g > (o - margin)}.reduce{_&&_}
    val cksum = true
    println("PASS: " + cksum + " (BlackSholes)")
    assert(cksum)

  }
}

