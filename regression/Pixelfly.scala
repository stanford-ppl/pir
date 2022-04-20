import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._

class Pixelfly_16_2_128 extends Pixelfly( // Pixelfly_N_B_batch
    N = 16,
    B = 2,
    V = 4,
    data = "/home/kosho/data/16_2_128_test",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8),
    batch = 1280
)

class Pixelfly_16_4_128 extends Pixelfly( // Pixelfly_N_B_batch
    N = 16,
    B = 4,
    V = 4,
    data = "/home/kosho/data/16_4_128_test",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8),
    batch = 1280
)

class Pixelfly_32_2_128 extends Pixelfly( // Pixelfly_N_B_batch
    N = 32,
    B = 2,
    V = 5,
    data = "/home/kosho/data/32_2_128_test",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8, 16),
    batch = 1280
)

class Pixelfly_32_4_128 extends Pixelfly( // Pixelfly_N_B_batch
    N = 32,
    B = 4,
    V = 5,
    data = "/home/kosho/data/32_4_128_test",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8, 16),
    batch = 1280
)

@spatial abstract class Pixelfly(
    N: scala.Int = 16, // number of blocks
    B: scala.Int = 2, // block size
    V: scala.Int = 4, // matrix variant
    data: java.lang.String = "/home/kosho/data/16_2",
    s_list: scala.List[scala.Int] = List(16, 8, 4, 2),
    s_over_2_list: scala.List[scala.Int] = List(8, 4, 2, 1),
    N_over_s_list: scala.List[scala.Int] = List(1, 2, 4, 8),
    batch: scala.Int = 2,
    lr: scala.Float = 1e-4f,
    
    ip: scala.Int = 16
    
) extends SpatialTest with MetaProgramming with AppUtil {
    
    type T = Float
    
    def main(args: Array[String]): Unit = {
        val w = (0 to V-1) map {v => DRAM[T](N_over_s_list(v), s_over_2_list(v), 2, B, 2, B)}
        for (v <- 0 to V-1) {
            setMem(w(v), loadCSV1D[T](data+"/w.csv"))
        }
        
        val in = DRAM[T](batch, N*B)
        setMem(in, loadCSV1D[T](data+"/in.csv"))
        
        val out = DRAM[T](batch, N*B)
        
        val err_in = DRAM[T](batch, N*B)
        
        Accel {
            val w_sram = (0 to V-1) map {v => SRAM[T](N_over_s_list(v), s_over_2_list(v), 2, B, 2, B).buffer.hierarchical}
            
            for (v <- 0 to V-1) {
                w_sram(v) load w(v)(0::N_over_s_list(v), 0::s_over_2_list(v), 0::2, 0::B, 0::2, 0::B par B)
            }
            
            
            Foreach(0 until batch) { ba =>
            
                val in_sram = SRAM[T](N*B).buffer // 14 MU
                in_sram load in(ba, 0::N*B par ip) // 16 CU
                
                
                 
                val tmp_out_sram = (0 to V-1) map {i => SRAM[T](N*B).buffer} // 5 MU
                val out_sram = SRAM[T](N*B).buffer // 6 MU
                
                val w_tmp_sram = (0 to V-1) map {v => SRAM[T](N_over_s_list(v), s_over_2_list(v), 2, B, 2, B).buffer.hierarchical} // 6 MU
                val tmp_err_in_sram = (0 to V-1) map {i => SRAM[T](N*B).buffer} // 5 MU
                val err_in_sram = SRAM[T](N*B).buffer // 1 MU
                
                val w_new_sram = (0 to V-1) map {v => SRAM[T](N_over_s_list(v), s_over_2_list(v), 2, B, 2, B).buffer.hierarchical} // 5 MU
                
                
                
                
                
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B) { case Seq(c, d, tv, bv) =>
                        val sum = Reduce(Reg[T])(0 until 2 par 2, 0 until B par B) { (th, bh) =>
                            w_sram(v)(c, d, tv, bv, th, bh) * in_sram(c*s*B + d*2*B + th*B + bh) // 10 CU, 6 CU
                        }{_+_} // 14 CU
                        tmp_out_sram(v)(c*s*B + d*2*B + tv*B + bv) = sum // 3 CU     
                    }                    
                }
                
                
                Foreach(0 until N*B) { i => // 1 CU
                    out_sram(i) = tmp_out_sram map {a => a(i)} reduceTree{_+_} 
                }




                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B, 0 until 2 par 2, 0 until B par B) { case Seq(c, d, tv, bv, th, bh) => // 21 CU
                        val read_w = w_sram(v)(c, d, tv, bv, th, bh) // 10 CU
                        val read_in = in_sram(c*s*B + d*2*B + th*B + bh) // 6 CU
                        val read_out = out_sram(c*s*B + d*2*B + tv*B + bv) // 5 CU
                        
                        w_tmp_sram(v)(c, d, th, bh, tv, bv) = read_w * read_out // 10 CU     
                        w_new_sram(v)(c, d, tv, bv, th, bh) = read_w - lr * read_in * read_out // 10 CU
                    }
                }
                
                
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B) { case Seq(c, d, th, bh) =>
                        val sum = Reduce(Reg[T])(0 until 2 par 2, 0 until B par B) { (tv, bv) =>
                            w_tmp_sram(v)(c, d, th, bh, tv, bv) // 13 CU
                        }{_+_} // 10 CU
                        tmp_err_in_sram(v)(c*s*B + d*2*B + th*B + bh) = sum // 3 CU               
                    }
                }
                
                
                
                
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B, 0 until 2 par 2, 0 until B par B) { case Seq(c, d, tv, bv, th, bh) =>
                        w_sram(v)(c, d, tv, bv, th, bh) = w_new_sram(v)(c, d, tv, bv, th, bh) // 5 CU
                    }
                } 
                
                
                Foreach(0 until N*B) { i => // 1 CU
                    err_in_sram(i) = tmp_err_in_sram map {a => a(i)} reduceTree{_+_} 
                }
                
                
                
                out(ba, 0::N*B par ip) store out_sram // 6 CU
                err_in(ba, 0::N*B par ip) store err_in_sram // 6 CU
                    
            }
                 
        }
        
        writeCSV1D(getMem(out), data+"/out.csv", delim="\n")
        writeCSV1D(getMem(err_in), data+"/err_in.csv", delim="\n")
        
        assert(true)
    }
    
}