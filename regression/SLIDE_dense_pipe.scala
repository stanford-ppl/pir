import spatial.dsl._
import utils.io.files._
import spatial.metadata.memory.{Barrier => _,_}

class SLIDE_dense_pipe_1 extends SLIDE_dense_pipe(
    pipeFactor = 1
)

class SLIDE_dense_pipe_2 extends SLIDE_dense_pipe(
    pipeFactor = 2
)

class SLIDE_dense_pipe_5 extends SLIDE_dense_pipe(
    pipeFactor = 5
)

class SLIDE_dense_pipe_10 extends SLIDE_dense_pipe(
    pipeFactor = 10
)

@spatial abstract class SLIDE_dense_pipe(
    numBatch:scala.Int = 10,
    epoch:scala.Int = 1,
    field:scala.Int = 20,
    L1:scala.Int = 32,
    L2:scala.Int = 64,
    data:java.lang.String = "/home/kosho/IO/load_dense_small",
    
    ratio:scala.Int = 3,
    lr:scala.Float = 1e-3f,
    input_max:scala.Int = 20,
    label_max:scala.Int = 7,
    ip:scala.Int = 16,
    pipeFactor:scala.Int = 1
    
) extends SpatialTest with AppUtil {

    type T = Float
    
    def forward(input: SRAM1[T], pre_layer_size: Int, curr_layer_size: Int, d_w: DRAM1[T], d_b: DRAM1[T]) = {
        
        val w = SRAM[T](curr_layer_size * pre_layer_size).buffer
        w load d_w
        
        val b = SRAM[T](curr_layer_size).buffer
        b load d_b
        
        val h_out = SRAM[T](curr_layer_size)
        Foreach(0 until curr_layer_size by 1) { i =>
            val sum = Reduce(Reg[T])(0 until pre_layer_size by 1 par ip) { j =>
                w(i * pre_layer_size + j) * input(j)
            }{_+_}
         
            h_out(i) = mux(sum + b(i) >= 0, sum + b(i), 0) // relu
        }
        
        (h_out, w, b)
    }
    
    def backprop(pre_layer_size: Int, curr_layer_size: Int, lr: Float, w: SRAM1[T], b: SRAM1[T], err_out: SRAM1[T], h_in: SRAM1[T], h_out: SRAM1[T]) = {
    
        val err_in = SRAM[T](pre_layer_size)
        Foreach(0 until pre_layer_size by 1) { j =>
            val sum = Reduce(Reg[T])(0 until curr_layer_size by 1 par ip) { i =>
                w(i * pre_layer_size + j) * err_out(i) * mux(h_out(i) >= 0, 1.to[T], 0)
            }{_+_}
         
            err_in(j) = sum
        }
       
        Foreach(0 until curr_layer_size by 1) { i =>
            Foreach(0 until pre_layer_size by 1 par ip) { j =>
                w(i * pre_layer_size + j) = w(i * pre_layer_size + j) - lr * err_out(i) * h_in(j) * mux(h_out(i) >= 0, 1.to[T], 0)
            }
        }
       
        Foreach(0 until curr_layer_size by 1 par ip) { i =>
            b(i) = b(i) - lr * err_out(i) * mux(h_out(i) >= 0, 1.to[T], 0)
        }
        
        err_in
    }
    
    
    def writeback(w: SRAM1[T], b: SRAM1[T], d_w: DRAM1[T], d_b: DRAM1[T]) = {
    
        d_w store w
        d_b store b
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
        val t1 = loadCSV1D[T](data + "/d_w1.csv")
        val t2 = loadCSV1D[T](data + "/d_w2.csv")
        setMem(d_w_l1, t1)
        setMem(d_w_l2, t2)
        setMem(d_b_l1, (0::L1) { i => 0.to[T] })
        setMem(d_b_l2, (0::L2) { i => 0.to[T] })
    
    

        val input_value = DRAM[T](numBatch * input_max)
        val t5 = loadCSV1D[T](data + "/input_value.csv")
        setMem(input_value, t5)
        setMem(input_value, (0::numBatch * input_max) { i => 2.5f.to[T] })
        
	
        val label = DRAM[Int](numBatch * label_max)
        val label_size = DRAM[Int](numBatch)
        val t6 = loadCSV1D[Int](data + "/label.csv")
        val t7 = loadCSV1D[Int](data + "/label_size.csv")
        setMem(label, t6)
        setMem(label_size, t7)

    
        Accel{
	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1) { x =>
		   
		   

                    val s_trainX = SRAM[T](input_max)
                    s_trainX load input_value(x * input_max::(x + 1) * input_max)



           
                    // forward
                    val (h_l1, w_l1, b_l1) = forward(s_trainX, field, L1, d_w_l1, d_b_l1)
                    val (h_l2, w_l2, b_l2) = forward(h_l1, L1, L2, d_w_l2, d_b_l2)


                    // loss
                    val s_label = SRAM[Int](label_max)
                    val tmp2 = SRAM[Int](1)
                    s_label load label(x * label_max::(x + 1) * label_max)
                    tmp2 load label_size(x::x + 1) 
                    
                    val s_label_size = tmp2(0)
		   
                    val err_l2 = SRAM[T](L2).buffer
                    Foreach(0 until L2 by 1 par ip) { i =>
                        err_l2(i) = h_l2(i)
                    }
                    
                    Foreach(0 until L2 by 1) { i =>
                        Foreach(0 until s_label_size by 1) { j =>
                            if (i == s_label(j)) {
                                err_l2(i) = h_l2(i) - 1.0f / (s_label_size).to[T]
                            }
                        }
                    }

                    // backprop
                    val err_l1 = backprop(L1, L2, lr, w_l2, b_l2, err_l2, h_l1, h_l2)
                    val err_field = backprop(field, L1, lr, w_l1, b_l1, err_l1, s_trainX, h_l1)
                      
                    
                    // writeback to DRAM
                    writeback(w_l2, b_l2, d_w_l2, d_b_l2)
                    writeback(w_l1, b_l1, d_w_l1, d_b_l1)
             
                }
            }
        

        }

        
        assert(true)
    }
}