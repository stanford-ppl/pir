import spatial.dsl._

//class LSTM_0 extends LSTMApp
//class LSTM_1 extends LSTMApp(rp=2)
//class LSTM_2 extends LSTMApp(rp=4)
//class LSTM_3 extends LSTMApp(rp=6)
//class LSTM_4 extends LSTMApp(rp=8)

@spatial abstract class LSTMApp(
  hp:scala.Int=1,
  rp:scala.Int=1
) extends SpatialTest with RNNHelper {

  val nHiddenUnits = 512
  val nGates = 4
  val innerPar = 16

  def main(args: Array[String]): Unit = {

    val cArg = ArgOut[T]
    val hArg = ArgOut[T]
    Accel {
      val wxhgLUTs = scala.Array.tabulate (nGates) { _ =>
        getLUT2D (
          nHiddenUnits,
          nHiddenUnits * 2
        )
      }
      val bLUTs = scala.Array.tabulate (nGates) { _ =>
        getLUT1D (
          nHiddenUnits
        )
      }
      val xhInit = getLUT1D (nHiddenUnits * 2)
      val cInit = getLUT1D (nHiddenUnits)
      val c = SRAM[T](nHiddenUnits)
      val h = SRAM[T](nHiddenUnits)
      val sigLUT = getLUT1D(1024)//LUT.fromFile[T](1024)(sigSrcPath)
      val tanhLUT = getLUT1D(1024)//LUT.fromFile[T](1024)(tanhSrcPath)
      val nUnitsPerReduceTile:scala.Int = nHiddenUnits * 2 / rp

      Foreach (nHiddenUnits by 1 par hp) { iHiddenUnit =>
        val ijfo: scala.Array[T] = wxhgLUTs.map { wxhSrc =>
          val g = Reduce (Reg[T]) (rp by 1 par rp) { iReduce =>
            val gInner = Reduce (Reg[T]) (nUnitsPerReduceTile by 1 par innerPar) { iReduceInner =>
              val iReduceIdx = iReduceInner + iReduce * nUnitsPerReduceTile
              wxhSrc (iHiddenUnit, iReduceIdx) * xhInit (iReduceIdx)
            } {_+_}
            gInner
          } {_+_} // assume that we div by 2
          g.value
        }

        val soReg = Reg[T] (0.to[T])

        val bijfo = scala.Array.tabulate (4) { i =>
          bLUTs (i) (iHiddenUnit) + ijfo (i)
        }

        val indexQ = FIFO[Int](1)
        val cIndexQ = FIFO[Int](1)
        Foreach (4 by 1 par 4) { iGate =>
          val gate = mux (
            iGate == 0, bijfo (0), mux (
              iGate == 1, bijfo (1), mux (
                iGate == 2, bijfo (2), bijfo (3)
              )
            )
          )

          val ltBias = (-8).to[T]
          val ltScale = 64.to[T]
          val gateIndex = gate

          val index = min (
            1023.to[Int], max (
              0.to[Int], ((gateIndex - ltBias) * ltScale).to[Int]
            )
          )
          indexQ.enq(index)
        }

        Foreach (4 by 1 par 4) { iGate =>
          val index = indexQ.deq()
          val sigEle = sigLUT (index)
          val tanhEle = tanhLUT (index)
          val actEle = mux (
            iGate == 1,
            tanhEle,
            sigEle
          )
          val siReg = actEle
          val tjReg = actEle
          val sfReg = actEle
          soReg := actEle

          val si = siReg
          val tj = tjReg
          val sf = sfReg

          val sitjEM = si * tj
          val ctsfEM = cInit (iHiddenUnit) * sf
          val cNew = sitjEM + ctsfEM
          c (iHiddenUnit) = cNew

          val cIndex = min (
            1023.to[Int], max (
              0.to[Int], ((cNew - (-8).to[T]) * 64.to[T]).to[Int]
            )
          )

          cIndexQ.enq (cIndex)
        }

        Foreach (4 by 1 par 4) { iGate =>
          val so = soReg.value

          val cIndex = cIndexQ.deq()
          val cTanh = tanhLUT (cIndex)
          // val cTanh = tanhWrapper (cNew) // Assuming that we don't need extra logic to do look up
          val hNew = cTanh * so
          h (iHiddenUnit) = hNew
        }
      }

      cArg := c (0)
      hArg := h (0)
    }
    assert(true)
  }
}
