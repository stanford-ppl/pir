import spatial.dsl._
import utils.io.files._
import spatial.metadata.memory.{Barrier => _,_}

class SLIDE_DD_load_16 extends SLIDE_DD_load(
    data = "/home/kosho/IO/DD_16",
    L1 = 16
)

class SLIDE_DD_load_32 extends SLIDE_DD_load(
    data = "/home/kosho/IO/DD_32",
    L1 = 32
)

@spatial abstract class SLIDE_DD_load(
    numBatch:scala.Int = 128,
    epoch:scala.Int = 1,
    field:scala.Int = 75,    
    L2:scala.Int = 1000,
    ip:scala.Int = 16,
    pipeFactor:scala.Int = 16,
    op:scala.Int = 4,  
    
    data:java.lang.String = "/home/kosho/IO/DD_16",
    L1:scala.Int = 16
    
) extends SpatialTest with AppUtil {

    type T = Float

    def main(args: Array[String]): Unit = {
	
        val d_w_l1 = DRAM[T](L1 * field)
        val d_w_l2 = DRAM[T](L2 * L1)
        val d_b_l1 = DRAM[T](L1)
        val d_b_l2 = DRAM[T](L2)
        d_w_l1.parAllowed = pipeFactor
        d_w_l2.parAllowed = pipeFactor
        d_b_l1.parAllowed = pipeFactor
        d_b_l2.parAllowed = pipeFactor
        val t1 = loadCSV1D[T](data + "/d_w_l1.csv")
        val t2 = loadCSV1D[T](data + "/d_w_l2.csv")
        setMem(d_w_l1, t1)
        setMem(d_w_l2, t2)
        setMem(d_b_l1, (0::L1) { i => 0.to[T] })
        setMem(d_b_l2, (0::L2) { i => 0.to[T] })
        
    
        Accel{
	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1 par op) { x =>
                    
                    val w_l1 = SRAM[T](L1 * field)
                    w_l1 load d_w_l1(0::L1 * field par ip)
                    
                    val w_l2 = SRAM[T](L2 * L1)
                    w_l2 load d_w_l2(0::L2 * L1 par ip)
             
             
                    
                    
                    d_w_l1(0::L1 * field par ip) store w_l1
                    d_w_l2(0::L2 * L1 par ip) store w_l2
                    
                }
            }
        

        }

        
        assert(true)
    }
}