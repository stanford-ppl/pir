import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._

class Pixelfly_test_16_2_1280 extends Pixelfly_test( // Pixelfly_N_B_batch
    N = 16,
    B = 2,
    V = 1,
    data = "/home/kosho/data/16_2_1280",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    n_times_b = 32,
    two_times_b = 4,
    batch = 1280
)

class Pixelfly_test_16_4_1280 extends Pixelfly_test( // Pixelfly_N_B_batch
    N = 16,
    B = 4,
    V = 1,
    data = "/home/kosho/data/16_4_1280",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    n_times_b = 64,
    two_times_b = 8,
    batch = 1280
)

class Pixelfly_test_32_2_1280 extends Pixelfly_test( // Pixelfly_N_B_batch
    N = 32,
    B = 2,
    V = 1,
    data = "/home/kosho/data/32_2_1280",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    n_times_b = 64,
    two_times_b = 4,
    batch = 1280
)

class Pixelfly_test_32_4_1280 extends Pixelfly_test( // Pixelfly_N_B_batch
    N = 32,
    B = 4,
    V = 1,
    data = "/home/kosho/data/32_4_1280",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    n_times_b = 128,
    two_times_b = 8,
    batch = 1280
)

@spatial abstract class Pixelfly_test(
    N: scala.Int = 16, // number of blocks
    B: scala.Int = 2, // block size
    V: scala.Int = 4, // matrix variant
    data: java.lang.String = "/home/kosho/data/16_2",
    s_list: scala.List[scala.Int] = List(16, 8, 4, 2),
    s_over_2_list: scala.List[scala.Int] = List(8, 4, 2, 1),
    n_times_b: scala.Int = 32,
    two_times_b: scala.Int = 4,
    batch: scala.Int = 2,
    lr: scala.Float = 1e-4f,
    
    ip: scala.Int = 16
    
) extends SpatialTest with MetaProgramming with AppUtil {
    
    type T = Float
    
    def main(args: Array[String]): Unit = {
        val w = DRAM[T](V*N*B*2*B)
        setMem(w, loadCSV1D[T](data+"/w.csv"))
        
        val in = DRAM[T](batch, N*B)
        setMem(in, loadCSV1D[T](data+"/in.csv"))
        
        val out = DRAM[T](batch, N*B)
        
        val err_in = DRAM[T](batch, N*B)
        
        Accel {
            val w_sram = (0 to V-1) map {i => SRAM[T](N*B*2*B).buffer} // 15 MU
            
            for (v <- 0 to V-1) {
                w_sram(v) load w(v*N*B*2*B::(v+1)*N*B*2*B par ip) // 20 CU
            }
            
            
            Foreach(0 until batch) { ba =>
            
                val in_sram = SRAM[T](N*B).buffer // 14 MU
                in_sram load in(ba, 0::N*B par ip) // 16 CU 
                
                
                 
                val tmp_out_sram = (0 to V-1) map {i => SRAM[T](N*B).buffer} // 5 MU
                val out_sram = SRAM[T](N*B).buffer // 6 MU
                
                val tmp_err_in_sram = (0 to V-1) map {i => SRAM[T](N*B).buffer} // 5 MU
                val err_in_sram = SRAM[T](N*B).buffer // 1 MU
                
                val w_new_sram = (0 to V-1) map {i => SRAM[T](N*B*2*B).buffer} // 5 MU
                
                
                
                
                
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    
                    Foreach(0 until n_times_b) { i =>
                        val block_i = i / B
                        val offset_i = i % B
                        
                        val sum = Reduce(Reg[T])(0 until two_times_b) { j =>
                            val block_j = j / B
                            val offset_j = j % B
                            val dense_to_sparse = (block_i % s_over_2 + s_over_2 * block_j + s * (block_i / s)) * B + offset_j
                            
                            w_sram(v)(i*two_times_b + j) * in_sram(dense_to_sparse)
                        }{_+_}
                        tmp_out_sram(v)(i) = sum
                    }                    
                }

                Foreach(0 until N*B) { i => // 1 CU
                    out_sram(i) = tmp_out_sram map {a => a(i)} reduceTree{_+_} 
                }




                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    
                    Foreach(0 until n_times_b) { i =>
                        val block_i = i / B
                        val offset_i = i % B
                        
                        val sum = Reduce(Reg[T])(0 until two_times_b) { j =>
                            val block_j = j / B
                            val offset_j = j % B
                            val dense_to_sparse = (block_i % s_over_2 + s_over_2 * block_j + s * (block_i / s)) * B + offset_j
                            
                            w_sram(v)(dense_to_sparse*two_times_b + j) * out_sram(dense_to_sparse) // 10 CU, 6 CU
                        }{_+_} // 14 CU
                        tmp_err_in_sram(v)(i) = sum // 3 CU
                    

                        Foreach(0 until two_times_b) { j =>
                            val block_j = j / B
                            val offset_j = j % B
                            val dense_to_sparse = (block_i % s_over_2 + s_over_2 * block_j + s * (block_i / s)) * B + offset_j
                            val tmp = i*two_times_b + j
                            
                            w_new_sram(v)(tmp) = w_sram(v)(tmp) - lr * in_sram(dense_to_sparse) * out_sram(i) // 10 CU, 6 CU
                        }
                    }
                }
                
                Foreach(0 until N*B) { i => // 1 CU
                    err_in_sram(i) = tmp_err_in_sram map {a => a(i)} reduceTree{_+_} 
                }
                
                
                for (v <- 0 to V-1) {
                    Foreach(0 until N*B*2*B par ip) { i =>
                        w_sram(v)(i) = w_new_sram(v)(i) // 5 CU
                    }
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