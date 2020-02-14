import spatial.dsl._

//class LSTM_4 extends LSTM(H = 1024, step = 4, hp = 1, rp = 4, ip = 16)

@spatial abstract class LSTM(
    N: scala.Int = 1,
    H: scala.Int = 512,
    step: scala.Int = 2,
    np: scala.Int = 1,
    hp: scala.Int = 1,
    rp: scala.Int = 1,
    ip: scala.Int = 16,
) extends SpatialTest
    with AppUtil {

  type T = Float
  type F = Float

  val nGates: scala.Int = 4

  def main(args: Array[String]): Unit = {
    val xData = DRAM[T](N,step, H)
    setMem(xData, (0::N, 0::step, 0::H) { (i,j,k) => 1.to[T] })

    val hResult = DRAM[T](N,step, H)
    val cResult = DRAM[T](N,step, H)

    Accel {
      val wxhgLUTs = List.fill(nGates) { newLUT[T](H, H * 2) }
      val bLUTs = List.fill(nGates) { newLUT[T](H) }

      val sigLUT = newLUT[T](1024)
      val tanhLUT = newLUT[T](1024)
      val nUnitsPerReduceTile: scala.Int = H * 2 / rp

      Foreach(N by 1 par np) { i =>
        val xBuf = SRAM[T](step, H)
        val hBuf = SRAM[T](step, H).buffer
        val cBuf = SRAM[T](step, H)

        xBuf load xData(i,0 :: step, 0 :: H par ip)
        Sequential.Foreach(step by 1) { iStep =>
          Foreach(H by 1 par hp) { iHiddenUnit =>
            val ijfo: scala.List[T] = wxhgLUTs.map { wxhSrc =>
              val g = Reg[T]
              Reduce(g)(H*2 by ip par rp) { iOuter =>
                val gInner = Reg[T]
                Reduce(gInner)(0 until ip par ip) { iInner =>
                  val iReduceIdx = (iOuter + iInner)
                  val isX = iReduceIdx < H
                  val firstStep = iStep == 0.to[I32]
                  val xEle = xBuf.read(Seq(iStep, iReduceIdx), Set(isX))
                  val hEle = mux(firstStep,0.to[T], hBuf.read(Seq(iStep - 1, iReduceIdx-H),Set(!isX,!firstStep)))
                  val xhEle = if (isX) xEle else hEle
                  wxhSrc(iHiddenUnit, iReduceIdx) * xhEle
                } { _ + _ }
                gInner.value
              } { _ + _ }
              g.value
            }

            val bijfo = List.tabulate(nGates) { i =>
              bLUTs(i)(iHiddenUnit) + ijfo(i)
            }

            val iReg :: jReg :: fReg :: oReg :: rr =
              List.fill(nGates)(Reg[T](0.to[T]))

            val indexFIFO = FIFO[Int](16)
            Foreach(nGates by 1) { iGate =>
              val flt = mux(iGate === 0,
                            bijfo.head,
                            mux(iGate === 1,
                                bijfo(1),
                                mux(iGate === 2, bijfo(2), bijfo(3))))

              val index = indexTranslate(flt)
              indexFIFO.enq(index)
            }

            Foreach(nGates by 1) { iGate =>
              val index = indexFIFO.deq()
              val sigEle = sigLUT(index)
              val tanhEle = tanhLUT(index)
              val actEle = mux(
                iGate == 1.to[I32],
                tanhEle,
                sigEle
              )

              iReg.write(actEle, iGate === 0)
              jReg.write(actEle, iGate === 1)
              fReg.write(actEle, iGate === 2)
              oReg.write(actEle, iGate === 3)
            }

            val cIndexFIFO = FIFO[Int](16)

            Pipe {
              val si = iReg.value
              val tj = jReg.value
              val sf = fReg.value
              val sitjEM = si * tj
              val ctsfEM = sf * {
                mux(iStep == 0.to[I32], 0.to[T], cBuf(iStep - 1, iHiddenUnit))
              }
              val cNew = sitjEM + ctsfEM
              cBuf(iStep, iHiddenUnit) = cNew
              val cIndex = indexTranslate(cNew)
              cIndexFIFO.enq(cIndex)
            }

            Pipe {
              val so = oReg.value
              val cIndex = cIndexFIFO.deq()
              val cTanh = tanhLUT(cIndex)
              val hNew = cTanh * so
              hBuf(iStep, iHiddenUnit) = hNew
            }
          }
        }

        hResult(i,0 :: step, 0 :: H par ip) store hBuf
        cResult(i,0 :: step, 0 :: H par ip) store cBuf
      }
    }

    //printMatrix(getMatrix(hResult))
    //printMatrix(getMatrix(cResult))
    assert(true)
  }

  def indexTranslate(x: T): I32 = {
    val ltBias = -8.to[T]
    val ltScale = 64.to[T]
    val re: I32 = min(
      1023.to[I32],
      max(
        0.to[I32],
        ((x - ltBias) * ltScale).to[I32]
      )
    )
    re
  }
}
