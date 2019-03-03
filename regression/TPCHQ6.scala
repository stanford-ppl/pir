import spatial.dsl._

case class TPCHQ6Param(
  N:scala.Int = 1024,
  ts:scala.Int = 64,
  op:scala.Int = 1,
  ip:scala.Int = 16,
) extends Param[TPCHQ6Param]

class TPCHQ6_0 extends TPCHQ6
class TPCHQ6_1 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=2) }
//class TPCHQ6_2 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=3) }
//class TPCHQ6_3 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=4) }
//class TPCHQ6_4 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=5) }
//class TPCHQ6_5 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=6) }
//class TPCHQ6_6 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=7) }
//class TPCHQ6_7 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=8) }
//class TPCHQ6_8 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=10) }
//class TPCHQ6_9 extends TPCHQ6 { override lazy val param = TPCHQ6Param(op=12) }

@spatial abstract class TPCHQ6 extends DSETest { // Regression (Dense) // Args: 3840
  lazy val param = TPCHQ6Param()
  import param._

  type FT = Int

  val MIN_DATE = 0
  val MAX_DATE = 9999
  val MIN_DISC = 0
  val MAX_DISC = 9999
  val margin = 1

  def tpchq6[T:Num](datesIn: Array[Int], quantsIn: Array[Int], disctsIn: Array[T], pricesIn: Array[T]): T = {
    //val dataSize = ArgIn[Int]
    //setArg(dataSize, datesIn.length); bound(datesIn.length) = N
    val dataSize = N

    val dates  = DRAM[Int](dataSize)
    val quants = DRAM[Int](dataSize)
    val discts = DRAM[T](dataSize)
    val prices = DRAM[T](dataSize)
    val minDateIn = MIN_DATE
    val maxDateIn = MAX_DATE
    val out = ArgOut[T]

    setMem(dates, datesIn)
    setMem(quants, quantsIn)
    setMem(discts, disctsIn)
    setMem(prices, pricesIn)

    Accel {
      val minDate = minDateIn
      val maxDate = maxDateIn

      val acc = Reg[T]
      Reduce(acc)(dataSize by ts par op){ i =>
        val datesTile  = SRAM[Int](ts)
        val quantsTile = SRAM[Int](ts)
        val disctsTile = SRAM[T](ts)
        val pricesTile = SRAM[T](ts)
        Parallel {
          datesTile  load dates(i::i+ts par ip)
          quantsTile load quants(i::i+ts par ip)
          disctsTile load discts(i::i+ts par ip)
          pricesTile load prices(i::i+ts par ip)
        }
        Reduce(Reg[T])(ts par ip){ j =>
          val date  = datesTile(j)
          val disct = disctsTile(j)
          val quant = quantsTile(j)
          val price = pricesTile(j)
          val valid = date > minDate && date < maxDate && disct >= MIN_DISC.to[T] && disct <= MAX_DISC.to[T] && quant < 24
          mux(valid, price * disct, 0.to[T])
        }{_+_}
      }{_+_}

      out := acc
    }
    getArg(out)
  }

  def main(args: Array[String]): Unit = {

    // val dates  = Array.fill(N){random[Int](20) + 65}
    // val quants = Array.fill(N){random[Int](25) }
    // // val discts = Array.fill(N){random[FT] * 0.05f + 0.02f}
    // // val prices = Array.fill(N){random[FT] * 1000f}
    // val discts = Array.fill(N){random[FT] /*/ 100000*/}
    // val prices = Array.fill(N){random[FT] /*/ 100000*/}

    val dates  = Array.tabulate[Int](N){i => i % 256 } // Standard array
    val quants = Array.tabulate[Int](N){i => i % 256 } // Standard array
    val discts = Array.tabulate[FT](N){i => i % 256 } // Standard array
    val prices = Array.tabulate[FT](N){i => i % 256 } // Standard array

    val result = tpchq6(dates, quants, discts, prices)

    // --- software version
    val conds = Array.tabulate(N){i => dates(i) > MIN_DATE && dates(i) < MAX_DATE  &&
                                       quants(i) < 24 && discts(i) >= MIN_DISC  && discts(i) <= MAX_DISC}
    // printArr(conds, "conds: ")

    val gold = Array.tabulate(N){i => if (conds(i)) prices(i) * discts(i) else 0.to[FT] }.reduce{_+_}

    println("expected " + gold)
    println("result " + result)

    val cksum = (gold < result + margin && gold > result - margin)
    println("PASS: " + cksum + " (TPCHQ6)")
    assert(cksum)
  }
}

