import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._

class Pixelfly_16_2 extends Pixelfly(
    N = 16,
    B = 2,
    V = 4,
    data = "/home/kosho/data/16_2",
    s_list = List(16, 8, 4, 2),
    s_over_2_list = List(8, 4, 2, 1),
    N_over_s_list = List(1, 2, 4, 8)
)

@spatial abstract class Pixelfly(
    N: scala.Int = 16, // number of blocks
    B: scala.Int = 2, // block size
    V: scala.Int = 4, // matrix variant
    data: java.lang.String = "/home/kosho/data/16_2",
    s_list: scala.List[scala.Int] = List(16, 8, 4, 2),
    s_over_2_list: scala.List[scala.Int] = List(8, 4, 2, 1),
    N_over_s_list: scala.List[scala.Int] = List(1, 2, 4, 8),
    iteration: scala.Int = 2,
    
    ip: scala.Int = 16
    
) extends SpatialTest with MetaProgramming with AppUtil {
    
    type T = Float
    
    def main(args: Array[String]): Unit = {
        // val w = DRAM[T](V*N*B*2*B)
        // setMem(w, loadCSV1D[T](data + "/w.csv"))
        
        // val in = DRAM[T](N*B)
        // setMem(in, loadCSV1D[T](data + "/in.csv"))
        
        // val out = DRAM[T](N*B)
        
        Accel {
            val w_sram = SRAM[T](V*N*B*2*B)
            val in_sram = SRAM[T](N*B)
            val out_sram = SRAM[T](N*B)   
            
            // w_sram load w(0::V*N*B*2*B par ip)
            // in_sram load in(0::N*B par ip)
            
            
            Foreach(0 until iteration by 1) { iter =>
                val tmp_out_sram = (0 to V-1) map {i => SRAM[T](N*B)} 
                
                for (v <- 0 to V-1) {
                    val s = s_list(v)
                    val s_over_2 = s_over_2_list(v)
                    val N_over_s = N_over_s_list(v)
                    
                    val v_spatial = v.to[Int]
                    
                    
                    
                    Foreach(0 until N_over_s by 1, 0 until 2 by 1, 0 until s_over_2 by 1, 0 until B by 1) { case Seq(c, tv, d, bv) =>
                        val sum = Reduce(Reg[T])(0 until 2 by 1 par 2, 0 until B by 1 par B) { (th, bh) =>
                            w_sram(v_spatial*N*2*B*B + c*2*s*B*B + tv*s*B*B + d*B*2*B + bv*2*B + th*B + bh) * in_sram(c*s*B + th*s_over_2*B + d*B + bh)
                        }{_+_}
                        tmp_out_sram(v)(c*s*B + tv*s_over_2*B + d*B + bv) = sum                 
                    }
                } 
                
                Foreach(0 until N*B by 1) { i =>
                    out_sram(i) = tmp_out_sram map {a => a(i)} reduceTree{_+_} 
                }
                
            }
            
          
            
            out(0::N*B par ip) store out_sram
        }
        
        writeCSV1D(getMem(out), data + "/out.csv")
        
        assert(true)
    }
    
}