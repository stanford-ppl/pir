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
    data = "/home/kosho/data/16_2_128",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8),
    batch = 128
)

class Pixelfly_1024_4_128 extends Pixelfly( // Pixelfly_N_B_batch
    N = 1024,
    B = 4,
    V = 10,
    data = "/home/kosho/data/1024_4_128",
    s_list = List(1024, 512, 256, 128, 64, 32, 16, 8, 4, 2),
    s_over_2_list = List(512, 256, 128, 64, 32, 16, 8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8, 16, 32, 64, 128, 256, 512),
    batch = 128
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
        val w = DRAM[T](V*N*B*2*B)
        setMem(w, loadCSV1D[T](data+"/w.csv"))
        
        val in = DRAM[T](batch*N*B)
        setMem(in, loadCSV1D[T](data+"/in.csv"))
        
        val out = DRAM[T](batch*N*B)
        
        // val err_in = DRAM[T](batch*N*B)
        val w_modified = DRAM[T](V*N*B*2*B)
        
        Accel {
            val w_sram = (0 to V-1) map {i => SRAM[T](N*B*2*B).buffer} // unrolled on V
            
            for (v <- 0 to V-1) {    
                w_sram(v) load w(v*N*B*2*B::(v+1)*N*B*2*B par ip)
            }
            
            
            Foreach(0 until batch by 1) { ba =>
            
                val in_sram = SRAM[T](N*B)
                in_sram load in(ba*N*B::(ba+1)*N*B par ip) 
                
                
                val tmp_out_sram = (0 to V-1) map {i => SRAM[T](N*B)} 
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s by 1, 0 until 2 by 1, 0 until s_over_2 by 1, 0 until B by 1) { case Seq(c, tv, d, bv) =>
                        val sum = Reduce(Reg[T])(0 until 2 by 1 par 2, 0 until B by 1 par B) { (th, bh) =>
                            w_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) * in_sram(c*s*B + th*s_over_2*B + d*B + bh)
                        }{_+_}
                        tmp_out_sram(v)(c*s*B + tv*s_over_2*B + d*B + bv) = sum                 
                    }
                } 
                
                val out_sram = SRAM[T](N*B)
                Foreach(0 until N*B by 1) { i =>
                    out_sram(i) = tmp_out_sram map {a => a(i)} reduceTree{_+_} 
                }


                
                
                // val tmp_err_in_sram = (0 to V-1) map {i => SRAM[T](N*B)} 
                // for (v <- 0 to V-1) {
                    // val s = s_list(v)
                    // val s_over_2 = s_over_2_list(v)
                    // val N_over_s = N_over_s_list(v)
                    
                    // Foreach(0 until N_over_s by 1, 0 until 2 by 1, 0 until s_over_2 by 1, 0 until B by 1) { case Seq(c, th, d, bh) =>
                        // val sum = Reduce(Reg[T])(0 until 2 by 1 par 2, 0 until B by 1 par B) { (tv, bv) =>
                            // w_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) * out_sram(c*s*B + tv*s_over_2*B + d*B + bv)
                        // }{_+_}
                        // tmp_err_in_sram(v)(c*s*B + th*s_over_2*B + d*B + bh) = sum                 
                    // }
                // }
                
                // val err_in_sram = SRAM[T](N*B)
                // Foreach(0 until N*B by 1) { i =>
                    // err_in_sram(i) = tmp_err_in_sram map {a => a(i)} reduceTree{_+_} 
                // }



                val w_new_sram = (0 to V-1) map {i => SRAM[T](N*B*2*B).buffer}
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s by 1, 0 until 2 by 1, 0 until s_over_2 by 1, 0 until B by 1 par B, 0 until 2 by 1 par 2, 0 until B by 1 par B) { case Seq(c, tv, d, bv, th, bh) =>
                        w_new_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) = w_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) - lr * in_sram(c*s*B + th*s_over_2*B + d*B + bh) * out_sram(c*s*B + tv*s_over_2*B + d*B + bv) 
                    }
                    
                    Foreach(0 until N*B*2*B by 1 par ip) { i =>
                        w_sram(v)(i) = w_new_sram(v)(i)
                    }
                }
                
                
                out(ba*N*B::(ba+1)*N*B par ip) store out_sram  
                // err_in(ba*N*B::(ba+1)*N*B par ip) store err_in_sram
                    
            }
            
            for (v <- 0 to V-1) {    
                w_modified(v*N*B*2*B::(v+1)*N*B*2*B par ip) store w_sram(v)
            }
                 
        }
        
        writeCSV1D(getMem(out), data+"/out.csv", delim="\n")
        // writeCSV1D(getMem(err_in), data+"/err_in.csv", delim="\n")
        writeCSV1D(getMem(w_modified), data+"/w_modified.csv", delim="\n")
        
        assert(true)
    }
    
}