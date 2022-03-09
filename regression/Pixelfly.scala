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
        
        // val w_dram = (0 to V-1) map { i =>
            // DRAM[T](N/2, 2, B, 2, B) 
            // setMem(w_dram(i), loadCSV1D[T](data+s"/w_$i.csv"))
        // }
        
        
        
        
        
        val in = DRAM[T](batch, N, B)
        setMem(in, loadCSV1D[T](data+"/in.csv"))
        
        val out = DRAM[T](batch, N, B)
        
        val err_in = DRAM[T](batch, N, B)
        
        
        // val w_modified = (0 to V-1) map { i =>
            // DRAM[T](N/2, 2, B, 2, B)
        // }
        
        
        
        Accel {
            // val w_sram = (0 to V-1) map {i => SRAM[T](N/2, 2, B, 2, B).buffer}
            
            // for (v <- 0 to V-1) {    
                // w_sram(v) load w(v, N*B*2*B::N*B*2*B par ip)
            // }
            
            
            Foreach(0 until batch) { ba =>
            
                val in_sram = SRAM[T](N, B).buffer
                in_sram load in(ba::(ba+1), 0::N, 0::B par ip) 
                
                
                val tmp_out_sram = (0 to V-1) map {i => SRAM[T](N, B).buffer} 
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    Foreach(0 until N_over_s, 0 until 2, 0 until s_over_2, 0 until B) { case Seq(c, tv, d, bv) =>
                        val sum = Reduce(Reg[T])(0 until 2 par 2, 0 until B par B) { (th, bh) =>
                            w_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) * in_sram(c*s + th*s_over_2 + d, bh)
                        }{_+_}
                        tmp_out_sram(v)(c*s + tv*s_over_2 + d, bv) = sum                 
                    }
                } 
                
                val out_sram = SRAM[T](N, B).buffer
                Foreach(0 until N, 0 until B) { (i, j) =>
                    out_sram(i, j) = tmp_out_sram map {a => a(i, j)} reduceTree{_+_}
                }


                
                
                
                


                val tmp_err_in_sram = (0 to V-1) map {i => SRAM[T](N, B).buffer} 
                val w_new_sram = (0 to V-1) map {i => SRAM[T](N*B*2*B).buffer}
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)

                    Foreach(0 until N_over_s, 0 until 2, 0 until s_over_2, 0 until B par B, 0 until 2 par 2, 0 until B par B) { case Seq(c, tv, d, bv, th, bh) =>
                        val read_w = w_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh)
                        val read_out = out_sram(c*s + tv*s_over_2 + d, bv)
                        val read_in = in_sram(c*s + th*s_over_2 + d, bh)
                        
                        w_new_sram(v)(c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) = read_w - lr * read_in * read_out
                    }
                    
                    Foreach(0 until N_over_s, 0 until 2, 0 until s_over_2, 0 until B) { case Seq(c, th, d, bh) =>
                        val sum = Reduce(Reg[T])(0 until 2 par 2, 0 until B par B) { (tv, bv) =>
                            w_sram(v)(c*2*s*B*B + th*s*B*B + d*B*2*B + bh*2*B + tv*B + bv) * out_sram(c*s + tv*s_over_2 + d, bv) 
                        }{_+_}
                        tmp_err_in_sram(v)(c*s + th*s_over_2 + d, bh) = sum                 
                    }
                    
                    
                    Foreach(0 until N*B*2*B par ip) { i =>
                        w_sram(v)(i) = w_new_sram(v)(i)
                    }
                }
                
                
                
                
                val err_in_sram = SRAM[T](N, B).buffer
                Foreach(0 until N, 0 until B) { (i, j) =>
                    err_in_sram(i, j) = tmp_err_in_sram map {a => a(i, j)} reduceTree{_+_} 
                }
                
                
                out(ba::(ba+1), 0::N, 0::B par ip) store out_sram  
                err_in(ba::(ba+1), 0::N, 0::B par ip) store err_in_sram
                    
            }
            
            for (v <- 0 to V-1) {    
                w_modified(v*N*B*2*B::(v+1)*N*B*2*B par ip) store w_sram(v)
            }
                 
        }
        
        writeCSV1D(getMem(out), data+"/out.csv", delim="\n")
        writeCSV1D(getMem(err_in), data+"/err_in.csv", delim="\n")
        writeCSV1D(getMem(w_modified), data+"/w_modified.csv", delim="\n")
        
        assert(true)
    }
    
}
