import spatial.dsl._

case class GEMMParam(
  dim:scala.Int = 128,
  ts:scala.Int = 64,
  its:scala.Int = 64,
  loop_ii:scala.Int = 1,
  loop_jj:scala.Int = 1,
  loop_kk:scala.Int = 1,
  loop_i:scala.Int = 1,
  loop_j:scala.Int = 1,
  ip:scala.Int = 16 
) extends Param[GEMMParam]

class GEMM_0 extends GEMM
class GEMM_1 extends GEMM {override lazy val param = GEMMParam(ip=1)}
class GEMM_2 extends GEMM {override lazy val param = GEMMParam(loop_j = 2)}
class GEMM_3 extends GEMM {override lazy val param = GEMMParam(loop_kk = 2)}
class GEMM_4 extends GEMM {override lazy val param = GEMMParam(loop_i = 2)}
class GEMM_5 extends GEMM {override lazy val param = GEMMParam(loop_i = 1,loop_j = 2)}
class GEMM_6 extends GEMM {override lazy val param = GEMMParam(loop_kk = 2,loop_i = 2,loop_j = 2)}
class GEMM_7 extends GEMM {override lazy val param = GEMMParam(loop_j = 2, ip = 1, ts=16, its=16, dim=32)}
//class GEMM_3 extends GEMM {override lazy val param = GEMMParam(loop_i = 4,loop_j = 4)}
//class GEMM_4 extends GEMM {override lazy val param = GEMMParam(loop_i = 2,loop_j = 2)}
//class GEMM_5 extends GEMM {override lazy val param = GEMMParam(loop_i = 1,loop_j = 2)}
//class GEMM_6 extends GEMM {override lazy val param = GEMMParam(loop_i = 2,loop_j = 1)}
//class GEMM_7 extends GEMM {override lazy val param = GEMMParam(loop_jj = 2)}
//class GEMM_8 extends GEMM {override lazy val param = GEMMParam(loop_i = 3)}
//class GEMM_9 extends GEMM {override lazy val param = GEMMParam(loop_j = 3)}
//class GEMM_10 extends GEMM {override lazy val param = GEMMParam(loop_i = 3,loop_j = 3)}

@spatial abstract class GEMM extends DSETest { // Regression (Dense) // Args: 128
                                                                                                  
  lazy val param = GEMMParam()
  import param._

  type T = Float
  //type T = Int

  def gemm(a_data:Matrix[T], b_data:Matrix[T], c_init:Matrix[T]) = {

    val a_dram = DRAM[T](dim,dim) // i, k
    val b_dram = DRAM[T](dim,dim) // j, k
    val c_dram = DRAM[T](dim,dim) // i, j

    setMem(a_dram, a_data)
    setMem(b_dram, b_data)
    setMem(c_dram, c_init)

    Accel{
      Foreach(dim by its par loop_ii, dim by ts par loop_jj) { (ii, jj) =>
        val c_col = SRAM[T](its,ts)
        MemReduce(c_col par ip)(dim by ts par loop_kk) { kk => 
          val c_col_partial = SRAM[T](its,ts)
          val b_sram = SRAM[T](ts,ts)
          b_sram load b_dram(kk::kk+ts, jj::jj+ts par ip)
          Foreach(its by 1 par loop_i) { i => 
            val a_sram = SRAM[T](ts)
            a_sram load a_dram(ii+i, kk::kk+ts par ip)
            Foreach(ts by 1 par loop_j) { j => 
              val dot = Reduce(Reg[T])(ts by 1 par ip) { k =>
                b_sram(k, j) * a_sram(k)
              } { _ + _ }
              Pipe { c_col_partial(i,j) = dot }
            }
          }
          c_col_partial
        }{_+_}
        c_dram(ii::ii+its, jj::jj+ts par ip) store c_col
      }
    }
    getMatrix(c_dram)
  }

  def main(args: Array[String]): Unit = {

    val a_data = (0::dim,0::dim){(i,j) => (i*dim+j).to[T]}
    val b_data = (0::dim,0::dim){(i,j) => (i*dim+j).to[T]}
    //val a_data = (0::dim,0::dim){(i,j) => random[T](5)}
    //val b_data = (0::dim,0::dim){(i,j) => random[T](5)}
    val c_init = (0::dim, 0::dim){(i,j) => 0.to[T]}

    val c_gold = (0::dim,0::dim){(i,j) => 
      Array.tabulate(dim){k => a_data(i,k) * b_data(k,j)}.reduce{_+_}
    }
    val c_result = gemm(a_data, b_data, c_init)

    printMatrix(c_gold, "C Gold: ")
    printMatrix(c_result, "C Result: ")

    val margin = 0.5.to[T]
    val cksum = c_gold.zip(c_result){(a,b) => abs(a-b) <= margin}.reduce{_&&_}
    println("PASS: " + cksum + " (GEMM)")
    assert(cksum)
  }
}

