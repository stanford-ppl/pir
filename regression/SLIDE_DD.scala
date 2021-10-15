import spatial.dsl._
import utils.io.files._
import spatial.metadata.memory.{Barrier => _,_}

class SLIDE_DD_16_1_1 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_16",
    L1 = 16,
    pipeFactor = 1,
    op = 1
)

class SLIDE_DD_16_16_1 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_16",
    L1 = 16,
    pipeFactor = 16,
    op = 1
)

class SLIDE_DD_16_1_4 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_16",
    L1 = 16,
    pipeFactor = 1,
    op = 4
)

class SLIDE_DD_16_16_4 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_16",
    L1 = 16,
    pipeFactor = 16,
    op = 4
)





class SLIDE_DD_32_1_1 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_32",
    L1 = 32,
    pipeFactor = 1,
    op = 1
)

class SLIDE_DD_32_16_1 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_32",
    L1 = 32,
    pipeFactor = 16,
    op = 1
)

class SLIDE_DD_32_1_4 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_32",
    L1 = 32,
    pipeFactor = 1,
    op = 4
)

class SLIDE_DD_32_16_4 extends SLIDE_DD(
    data = "/home/kosho/IO/DD_32",
    L1 = 32,
    pipeFactor = 16,
    op = 4
)

@spatial abstract class SLIDE_DD(
    numBatch:scala.Int = 128,
    epoch:scala.Int = 1,
    field:scala.Int = 75,    
    L2:scala.Int = 1000,
    ratio:scala.Int = 3,
    lr:scala.Float = 1e-3f,
    ip:scala.Int = 16,
    op2:scala.Int = 1,
    
    data:java.lang.String = "/home/kosho/IO/DD_16",
    L1:scala.Int = 16,
    pipeFactor:scala.Int = 1,
    op:scala.Int = 1    
    
) extends SpatialTest with AppUtil {

    type T = Float
    
    def forward(pre_layer_size: Int, curr_layer_size: Int, input: SRAM1[T], d_w: DRAM1[T], d_b: DRAM1[T]) = {
        
        val w = SRAM[T](curr_layer_size * pre_layer_size)
        w load d_w(0::curr_layer_size * pre_layer_size par ip)
        
        val b = SRAM[T](curr_layer_size)
        b load d_b(0::curr_layer_size par ip)
        
        val h_out = SRAM[T](curr_layer_size)
        Foreach(0 until curr_layer_size by 1 par op2) { i =>
            val sum = Reduce(Reg[T])(0 until pre_layer_size by 1 par ip) { j =>
                w(i * pre_layer_size + j) * input(j)
            }{_+_}
         
            h_out(i) = mux(sum + b(i) >= 0, sum + b(i), 0) // relu
        }
        
        (h_out, w, b)
    }
    
    def backprop(pre_layer_size: Int, curr_layer_size: Int, lr: Float, w: SRAM1[T], b: SRAM1[T], err_out: SRAM1[T], h_in: SRAM1[T], h_out: SRAM1[T]) = {
    
        val w_new = SRAM[T](curr_layer_size * pre_layer_size)
        val b_new = SRAM[T](curr_layer_size)
        
        val err_in_tmp = SRAM[T](curr_layer_size, pre_layer_size)
        val err_in = SRAM[T](pre_layer_size)
        
        Foreach(0 until curr_layer_size by 1 par op2) { i =>
            val err_out_tmp = err_out(i)
            val h_out_tmp = h_out(i)
            
            Foreach(0 until pre_layer_size by 1 par ip) { j =>
                val w_tmp = w(i * pre_layer_size + j)
            
                w_new(i * pre_layer_size + j) = w_tmp - lr * err_out_tmp * h_in(j) * mux(h_out_tmp >= 0, 1.to[T], 0)
                err_in_tmp(i, j) = w_tmp * err_out_tmp * mux(h_out_tmp >= 0, 1.to[T], 0)
            }
            b_new(i) = b(i) - lr * err_out_tmp * mux(h_out_tmp >= 0, 1.to[T], 0)
        }
        
        Foreach(0 until pre_layer_size by 1 par op2) { j =>
            val sum = Reduce(Reg[T])(0 until curr_layer_size by 1 par ip) { i =>
                err_in_tmp(i, j)
            }{_+_}
         
            err_in(j) = sum
        }
        
        (err_in, w_new, b_new)
    }
    
    
    def writeback(pre_layer_size: Int, curr_layer_size: Int, w: SRAM1[T], b: SRAM1[T], d_w: DRAM1[T], d_b: DRAM1[T]) = {
    
        d_w(0::curr_layer_size * pre_layer_size par ip) store w
        d_b(0::curr_layer_size par ip) store b
    }
    
  


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
    
    
        val input_value = DRAM[T](numBatch * field)
        val t3 = loadCSV1D[T](data + "/input_value.csv")
        setMem(input_value, t3)
        
    
        Accel{
	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1 par op) { x =>

                    val s_trainX = SRAM[T](field)
                    s_trainX load input_value(x * field::(x + 1) * field par ip)

           
                    // forward
                    val (h_l1, w_l1, b_l1) = forward(field, L1, s_trainX, d_w_l1, d_b_l1)
                    val (h_l2, w_l2, b_l2) = forward(L1, L2, h_l1, d_w_l2, d_b_l2)
                    
		   
                    val err_l2 = SRAM[T](L2)
                    Foreach(0 until L2 by 1 par ip) { i =>
                        err_l2(i) = h_l2(i)
                    }


                    // backprop
                    val (err_l1, w_l2_new, b_l2_new) = backprop(L1, L2, lr, w_l2, b_l2, err_l2, h_l1, h_l2)
                    val (err_field, w_l1_new, b_l1_new) = backprop(field, L1, lr, w_l1, b_l1, err_l1, s_trainX, h_l1)
                      
                    
                    // writeback to DRAM
                    writeback(L1, L2, w_l2_new, b_l2_new, d_w_l2, d_b_l2)
                    writeback(field, L1, w_l1_new, b_l1_new, d_w_l1, d_b_l1)
             
                }
            }
        

        }

        
        assert(true)
    }
}