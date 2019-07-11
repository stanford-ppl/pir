import spatial.dsl._

//class GRU_0 extends GRUApp
//class GRU_1 extends GRUApp(rp=2)
//class GRU_2 extends GRUApp(rp=4)
//class GRU_3 extends GRUApp(rp=6)
//class GRU_5 extends GRUApp(hp=2)
//class GRU_6 extends GRUApp(hp=4)
//class GRU_7 extends GRUApp(hp=6)
//class GRU_8 extends GRUApp(hp=2,rp=2)

@spatial abstract class GRUApp(
  hp:scala.Int=1,
  rp:scala.Int=1
) extends DSETest with RNNHelper {

  val innerPar = 16
  val nHiddenUnits = 512
  val nGates = 3

  def main(args: Array[String]): Unit = {
    val hArg = ArgOut[T]
    Accel {
      val sigLUT = getLUT1D(1024)//LUT.fromFile[T](1024)(sigSrcPath)
      val tanhLUT = getLUT1D(1024)//LUT.fromFile[T](1024)(tanhSrcPath)
      val hBuf = SRAM[T](nHiddenUnits)
      val uhLUTs = scala.Array.tabulate (nGates) { _ =>
        getLUT2D (
          nHiddenUnits,
          nHiddenUnits
        ) // z, u, r
      }
      val wxLUTs = scala.Array.tabulate (nGates) { _ =>
        getLUT2D (
          nHiddenUnits,
          nHiddenUnits
        ) // r, z, w
      }
      val xInit = getLUT1D (nHiddenUnits)
      val hInit = getLUT1D (nHiddenUnits)
      val nUnitsPerReduceTile = nHiddenUnits / rp
      Foreach (nHiddenUnits by 1 par hp) { iHiddenUnit =>
        val hLast = hInit (iHiddenUnit)
        def reduceTriGates (
          gates: scala.Array[LUT2[T]],
          src: LUT1[T]
        ): scala.Array[T] = {
          gates.map { gW =>
            val gWEle = Reduce (Reg[T]) (rp by 1 par rp) { iReduce =>
              val innerEle = Reduce (Reg[T]) (nUnitsPerReduceTile by 1 par innerPar) { iReduceInner =>
                val iReduceIdx = iReduceInner + iReduce * nUnitsPerReduceTile
                gW (iHiddenUnit, iReduceIdx) * src (iReduceIdx)
              } {_+_}
              innerEle
            } {_+_}
            gWEle.value
          }
        }

        val zur = reduceTriGates (uhLUTs, hInit)
        val rzw = reduceTriGates (wxLUTs, xInit)
        val (xr, xz, xw) = (rzw(0), rzw(1), rzw(2))
        val (hz, hu, hr) = (zur(0), zur(1), zur(2))
        // TODO: may be able to move these two up to reduction
        val xrhrAdd = xr + hr
        val hzxzAdd = hz + xz

        val indexQ = FIFO[Int](1)
        val tanhQ = FIFO[Int](1)
        val xrhrAddReg = Reg[T](0.to[T])
        val hzxzAddReg = Reg[T](0.to[T])
        Foreach (2 by 1 par 2) { iGate =>
          val gate = mux (iGate == 0, xrhrAdd, hzxzAdd)
          val ltBias = (-8.to[T])
          val ltScale = 64.to[T]
          val gateIndex = gate
          val index = min (
            1023.to[Int], max (
              0.to[Int], ((gateIndex - ltBias) * ltScale).to[Int]
            )
          )
          indexQ.enq(index)
        }

        Foreach (2 by 1 par 2) { iGate =>
          val index = indexQ.deq()
          val sigEle = sigLUT (index)
          xrhrAddReg := sigEle
          hzxzAddReg := sigEle
        }

        // tanh
        Foreach (1 by 1) { _ =>
          val rt = xrhrAddReg.value
          val rthuxwMAC = rt * hu + xw
          val tfIndex = min (
            1023.to[Int], max (
              0.to[Int], ((rthuxwMAC - (-8).to[T]) * 64.to[T]).to[Int]
            )
          )
          tanhQ.enq(tfIndex)
        }

        Foreach (1 by 1) { _ =>
          val tfIndex = tanhQ.deq()
          val tfnMAC = tanhLUT (tfIndex)
          val zt = hzxzAddReg.value
          val htCand0 = tfnMAC * zt
          val htCand1 = (1.to[T] - zt) * hLast
          val hUpdate = htCand0 + htCand1
          hBuf (iHiddenUnit) = hUpdate
        }
      }

      hArg := hBuf (0)
    }
    assert(true)
  }
}

