import spatial.dsl._
import utils.io.files._
import spatial.metadata.memory.{Barrier => _,_}

class SLIDE_SS_load_16 extends SLIDE_SS_load(
    data = "/home/kosho/IO/SS_load_16",
    L1 = 16
)

class SLIDE_SS_load_32 extends SLIDE_SS_load(
    data = "/home/kosho/IO/SS_load_32",
    L1 = 32
)

@spatial abstract class SLIDE_SS_load(
    numBatch:scala.Int = 128,
    epoch:scala.Int = 1,
    field:scala.Int = 1000,
    L2:scala.Int = 800,  
    input_max:scala.Int = 75,
    output_max:scala.Int = 100,
    ip:scala.Int = 16,
    pipeFactor:scala.Int = 16,
    op:scala.Int = 4,

    data:java.lang.String = "/home/kosho/IO/SS_load_16",
    L1:scala.Int = 16
    
) extends SpatialTest with AppUtil {

     type T = Float

    def main(args: Array[String]): Unit = {
	
        val d_w_l1 = DRAM[T](L1 * field)
        val d_w_l2 = DRAM[T](L2 * L1)
        d_w_l1.parAllowed = pipeFactor
        d_w_l2.parAllowed = pipeFactor
        val t1 = loadCSV1D[T](data + "/d_w_l1.csv")
        val t2 = loadCSV1D[T](data + "/d_w_l2.csv")
        setMem(d_w_l1, t1)
        setMem(d_w_l2, t2)
    
    
        val input = DRAM[Int](numBatch * input_max)
        val t3 = loadCSV1D[Int](data + "/input.csv")
        setMem(input, t3)
        

        val d_table_l1 = DRAM[Int](numBatch * L1 / 2)
        val d_table_l2 = DRAM[Int](numBatch * output_max)
        val t4 = loadCSV1D[Int](data + "/table_l1.csv")
        val t5 = loadCSV1D[Int](data + "/table_l2.csv")
        setMem(d_table_l1, t4)
        setMem(d_table_l2, t5)
        

    
        Accel{ 

	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1 par op) { x =>
                    
                    val half_L1 = L1 / 2
                    
                    val active_field = SRAM[Int](input_max)
                    active_field load input(x * input_max::(x + 1) * input_max par ip)
                    
                    val active_L1 = SRAM[Int](half_L1)
                    active_L1 load d_table_l1(x * half_L1::(x + 1) * half_L1 par ip)
                    
                    val active_L2 = SRAM[Int](output_max)
                    active_L2 load d_table_l2(x * output_max::(x + 1) * output_max par ip)
                    
                    
                    
                    
                    val w_l1 = SRAM[T](half_L1 * input_max)
                    val idx_l1 = SRAM[Int](half_L1 * input_max)
                    Foreach(0 until half_L1 by 1) { i =>
                        Foreach(0 until input_max by 1 par ip) { j =>
                            idx_l1(i * input_max + j) = active_L1(i) * field + active_field(j)
                        }
                    }
                    w_l1 gather d_w_l1(idx_l1 par ip, half_L1 * input_max)
                    
                    
                    
                    
                    
                    val w_l2 = SRAM[T](output_max * half_L1)
                    val idx_l2 = SRAM[Int](output_max * half_L1)
                    Foreach(0 until output_max by 1) { i =>
                        Foreach(0 until half_L1 by 1 par ip) { j =>
                            idx_l2(i * half_L1 + j) = active_L2(i) * L1 + active_L1(j)
                        }
                    }
                    w_l2 gather d_w_l2(idx_l2 par ip, output_max * half_L1)
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    d_w_l1(idx_l1 par ip, half_L1 * input_max) scatter w_l1
                    d_w_l2(idx_l2 par ip, output_max * half_L1) scatter w_l2
                    
                    
             
                }
            }
        

        }

        
        assert(true)
    }
}