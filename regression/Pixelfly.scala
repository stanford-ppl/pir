import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._
import utils.io.files._
import scala.math.pow

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
    
    ip: scala.Int = 16
    
) extends SpatialTest with MetaProgramming with AppUtil {

    type T = Float
	
    def main(args: Array[String]): Unit = {
    
        val w = DRAM[T](V*N*B*2*B)
        setMem(w, loadCSV1D[T](data + "/w.csv"))
        
        val in = DRAM[T](N*B)
        setMem(in, loadCSV1D[T](data + "/in.csv"))
        
        val out = DRAM[T](N*B)
        
        Accel {
            val w_sram = SRAM[T](V*N*B*2*B)
            val in_sram = SRAM[T](N*B)
            val out_sram = SRAM[T](N*B)
            
            
            w_sram load w(0::V*N*B*2*B par ip)
            in_sram load in(0::N*B par ip)
            

			Foreach(0 until V by 1) { v =>
				s = s_list(v)
				s_over_2 = s_over_2_list(v)
				N_over_s = N_over_s_list(v)
				
				Foreach(0 until N_over_s by 1) { c =>
					Foreach(0 until 2 by 1) { tv =>
						Foreach(0 until s_over_2 by 1) { d => 
							Foreach(0 until B by 1) { bv =>
								val sum = Reduce(Reg[T])(0 until 2 by 1, 0 until B by 1) { (th, bh) =>
									w_sram(c*2*s_over_2*B*2*B + tv*s_over_2*B*2*B + d*B*2*B + bv*2*B + th*B + bh) * in_sram(c*s*B + th*s_over_2*B + d*B + bh)
								}{_+_}
								out_sram(c*s*B + tv*s_over_2*B + d*B + bv) = out_sram(c*s*B + tv*s_over_2*B + d*B + bv) + sum
							}
						}
					}                    
				}
			}
			
			
			
            out(0::N*B par ip) store out_sram
        }

        writeCSV1D(getMem(out), data + "/out.csv")
        
        assert(true)
    }
    
}