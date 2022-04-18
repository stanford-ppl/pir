import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._

class Pixelfly_test_forward_16_2_128 extends Pixelfly_test_forward( // Pixelfly_N_B_batch
    N = 16,
    B = 2,
    V = 4,
    data = "/home/kosho/data/16_2_128_test",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    n_times_b = 32,
    two_times_b = 4,
    batch = 128
)

class Pixelfly_test_forward_16_4_128 extends Pixelfly_test_forward( // Pixelfly_N_B_batch
    N = 16,
    B = 4,
    V = 4,
    data = "/home/kosho/data/16_4_128_test",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    n_times_b = 64,
    two_times_b = 8,
    batch = 128
)

class Pixelfly_test_forward_32_2_128 extends Pixelfly_test_forward( // Pixelfly_N_B_batch
    N = 32,
    B = 2,
    V = 5,
    data = "/home/kosho/data/32_2_128_test",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    n_times_b = 64,
    two_times_b = 4,
    batch = 128
)

class Pixelfly_test_forward_32_4_128 extends Pixelfly_test_forward( // Pixelfly_N_B_batch
    N = 32,
    B = 4,
    V = 5,
    data = "/home/kosho/data/32_4_128_test",
    s_list = List(32, 16, 8, 4, 2),
    s_over_2_list = List(16, 8, 4, 2, 1),
    n_times_b = 128,
    two_times_b = 8,
    batch = 128
)

@spatial abstract class Pixelfly_test_forward(
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
                            
                            w_sram(v)(i*two_times_b + j) * in_sram(dense_to_sparse) // 10 CU, 6 CU
                        }{_+_} // 14 CU
                        tmp_out_sram(v)(i) = sum // 3 CU             
                    }                    
                }

                Foreach(0 until N*B) { i => // 1 CU
                    out_sram(i) = tmp_out_sram map {a => a(i)} reduceTree{_+_} 
                }



                
                out(ba, 0::N*B par ip) store out_sram // 6 CU
                    
            }
                 
        }
        
        writeCSV1D(getMem(out), data+"/out.csv", delim="\n")
        
        assert(true)
    }
    
}