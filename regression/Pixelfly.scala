import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._

class Pixelfly_2048_2_1280 extends Pixelfly( // Pixelfly_N_B_batch
    N = 2048,
    B = 2,
    data = "/home/kosho/data/2048_2_1280",
    s = 2048,
    s_over_2 = 1024,
    N_over_s = 1,
    batch = 1280,
    parB = 2
)

class Pixelfly_1024_4_1280 extends Pixelfly( // Pixelfly_N_B_batch
    N = 1024,
    B = 4,
    data = "/home/kosho/data/1024_4_1280",
    s = 1024,
    s_over_2 = 512,
    N_over_s = 1,
    batch = 1280,
    parB = 4
)

class Pixelfly_512_8_1280 extends Pixelfly( // Pixelfly_N_B_batch
    N = 512,
    B = 8,
    data = "/home/kosho/data/512_8_1280",
    s = 512,
    s_over_2 = 256,
    N_over_s = 1,
    batch = 1280,
    parB = 8
)

class Pixelfly_256_16_1280 extends Pixelfly( // Pixelfly_N_B_batch
    N = 256,
    B = 16,
    data = "/home/kosho/data/256_16_1280",
    s = 256,
    s_over_2 = 128,
    N_over_s = 1,
    batch = 1280,
    parB = 8
)

class Pixelfly_128_32_1280 extends Pixelfly( // Pixelfly_N_B_batch
    N = 128,
    B = 32,
    data = "/home/kosho/data/128_32_1280",
    s = 128,
    s_over_2 = 64,
    N_over_s = 1,
    batch = 1280,
    parB = 8
)




@spatial abstract class Pixelfly(
    N: scala.Int = 16, // number of blocks
    B: scala.Int = 2, // block size
    data: java.lang.String = "",
    batch: scala.Int = 2,
    lr: scala.Float = 1e-4f,
    ip: scala.Int = 16,
    parB: scala.Int = 2,
    s: scala.Int = 2,
    s_over_2: scala.Int = 2,
    N_over_s: scala.Int = 2
    
) extends SpatialTest with MetaProgramming with AppUtil {
    
    type T = Float
    
    def main(args: Array[String]): Unit = {
        val w = DRAM[T](N_over_s, s_over_2, 2, B, 2, B)
        setMem(w, loadCSV1D[T](data+"/w.csv"))
        
        val in = DRAM[T](batch, N*B)
        setMem(in, loadCSV1D[T](data+"/in.csv"))
        
        val w_final = DRAM[T](N_over_s, s_over_2, 2, B, 2, B)
        
        Accel {
            val w_sram = SRAM[T](N_over_s, s_over_2, 2, B, 2, B).buffer.hierarchical
            w_sram load w(0::N_over_s, 0::s_over_2, 0::2, 0::B, 0::2, 0::B par parB)
            
            Pipe.Foreach(0 until batch) { ba =>
            
                val in_sram = SRAM[T](N*B).buffer // 14 MU
                in_sram load in(ba, 0::N*B par ip) // 16 CU
                
                val out_sram = SRAM[T](N*B).buffer // 6 MU
                
                val w_new_sram = SRAM[T](N_over_s, s_over_2, 2, B, 2, B).buffer.hierarchical
                
                
                
                Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B) { case Seq(c, d, tv, bv) =>
                    val sum = Reduce(Reg[T])(0 until 2 par 2, 0 until B par parB) { (th, bh) =>
                        w_sram(c, d, tv, bv, th, bh) * in_sram(c*s*B + d*B + th*s_over_2*B + bh)
                    }{_+_}
                    out_sram(c*s*B + d*B + tv*s_over_2*B + bv) = sum
                }



                
                    
                Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B, 0 until 2 par 2, 0 until B par parB) { case Seq(c, d, tv, bv, th, bh) => // 21 CU
                    val read_w = w_sram(c, d, tv, bv, th, bh) // 10 CU
                    val read_in = in_sram(c*s*B + d*B + th*s_over_2*B + bh) // 6 CU
                    val read_out = out_sram(c*s*B + d*B + tv*s_over_2*B + bv) // 5 CU
                      
                    w_new_sram(c, d, tv, bv, th, bh) = read_w - lr * read_in * read_out // 10 CU
                }
                
                
                
                
                Foreach(0 until N_over_s, 0 until s_over_2, 0 until 2, 0 until B, 0 until 2 par 2, 0 until B par parB) { case Seq(c, d, tv, bv, th, bh) =>
                    w_sram(c, d, tv, bv, th, bh) = w_new_sram(c, d, tv, bv, th, bh) // 5 CU
                }
                    
            }
            
            
            w_final(0::N_over_s, 0::s_over_2, 0::2, 0::B, 0::2, 0::B par parB) store w_sram
                 
        }
        
        writeCSV1D(getMem(w_final), data+"/w_final.csv", delim="\n")
        
        assert(true)
    }
    
}