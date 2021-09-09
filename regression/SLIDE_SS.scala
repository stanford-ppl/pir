import spatial.dsl._
import utils.io.files._
import spatial.metadata.memory.{Barrier => _,_}

class SLIDE_SS_16_1_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_16",
    L1 = 16,
    pipeFactor = 1,
    op = 1
)

class SLIDE_SS_16_16_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_16",
    L1 = 16,
    pipeFactor = 16,
    op = 1
)

class SLIDE_SS_16_1_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_16",
    L1 = 16,
    pipeFactor = 1,
    op = 4
)

class SLIDE_SS_16_16_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_16",
    L1 = 16,
    pipeFactor = 16,
    op = 4
)





class SLIDE_SS_32_1_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_32",
    L1 = 32,
    pipeFactor = 1,
    op = 1
)

class SLIDE_SS_32_16_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_32",
    L1 = 32,
    pipeFactor = 16,
    op = 1
)

class SLIDE_SS_32_1_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_32",
    L1 = 32,
    pipeFactor = 1,
    op = 4
)

class SLIDE_SS_32_16_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_32",
    L1 = 32,
    pipeFactor = 16,
    op = 4
)







class SLIDE_SS_64_1_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_64",
    L1 = 64,
    pipeFactor = 1,
    op = 1
)

class SLIDE_SS_64_16_1 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_64",
    L1 = 64,
    pipeFactor = 16,
    op = 1
)

class SLIDE_SS_64_1_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_64",
    L1 = 64,
    pipeFactor = 1,
    op = 4
)

class SLIDE_SS_64_16_4 extends SLIDE_SS(
    data = "/home/kosho/IO/SS_64",
    L1 = 64,
    pipeFactor = 16,
    op = 4
)


@spatial abstract class SLIDE_SS(
    numBatch:scala.Int = 128,
    epoch:scala.Int = 1,
    field:scala.Int = 100,
    L2:scala.Int = 600,  
    K_l1:scala.Int = 1,
    L_l1:scala.Int = 1,    
    K_l2:scala.Int = 4,
    L_l2:scala.Int = 4,    
    row_l1:scala.Int = 2,
    row_l2:scala.Int = 16,
    bucket:scala.Int = 64,
    ratio:scala.Int = 3,
    lr:scala.Float = 1e-3f,
    input_max:scala.Int = 5,
    ip:scala.Int = 16,
    op2:scala.Int = 1,

    data:java.lang.String = "/home/kosho/IO/SS_16",
    L1:scala.Int = 16,
    pipeFactor:scala.Int = 1,
    op:scala.Int = 1
    
) extends SpatialTest with AppUtil {

     type T = Float
  
    def mersenne_hash(j:Int, k:Int, l:Int) = {
        val MERSENNE_2 = 8191
        val MERSENNE_3 = 131071
        val MERSENNE_4 = 524287
    
        (j * MERSENNE_2) + (k * MERSENNE_3) + (l * MERSENNE_4)
    }
    
    def simhash[T:Num](sparse: Boolean, input: SRAM1[T], active: SRAM1[Int], max: Int, K: Int, L: Int) = {
        val results = SRAM[Int](L)
    
        Foreach(0 until L by 1) { l => // every table
            val value = Reduce(Reg[Int])(0 until K by 1 par op2) {k => // every hash function
                val sum = Reduce(Reg[T])(0 until max by 1 par ip) { j => // sum across all inputs
                    val rand = if (sparse) mersenne_hash(active(j), k, l) else mersenne_hash(j, k, l)
                    mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(j), -input(j)), 0)
                }{_+_}
                mux(sum.value < 0, 1.to[Int] << k.to[I16], 0)
            }{_|_}

            results(l) = value
        }
        
        results
    }
  
    def forward(input: SRAM1[T], active_in: SRAM1[Int], counter_in: Int, active_len_in: Int, pre_layer_size: Int, curr_layer_size: Int, K: Int, L: Int, row: Int, x: Int, d_cnt: DRAM1[Int], d_table: DRAM1[Int], d_w: DRAM1[T], d_b: DRAM1[T]) = {
        val hashcode = simhash(true, input, active_in, counter_in, K, L)
        val active_len_out = bucket
        val active_out = SRAM[Int](active_len_out)
        
        val num = (x * 127) % L
        val size = SRAM[Int](1)
        size load d_cnt(num * row + hashcode(num)::num * row + hashcode(num) + 1)
        
        active_out load d_table(num * row * bucket + hashcode(num) * bucket::num * row * bucket + hashcode(num) * bucket + bucket par ip)
        
        val counter_out = size(0)
        

        val w = SRAM[T](active_len_out * active_len_in)
        val idx = SRAM[Int](active_len_out * active_len_in)
        
        Foreach(0 until counter_out by 1) { i =>
            Foreach(0 until counter_in by 1 par ip) { j =>
                idx(i * counter_in + j) = active_out(i) * pre_layer_size + active_in(j)
            }
        }
       
        w gather d_w(idx par ip, counter_out * counter_in)
        
       
        val b = SRAM[T](active_len_out)
        b gather d_b(active_out par ip, counter_out)
        
        val h_out = SRAM[T](active_len_out)

        Foreach(0 until counter_out by 1 par op2) { i =>
            val sum = Reduce(Reg[T])(0 until counter_in by 1 par ip) { j =>
                w(i * counter_in + j) * input(j)
            }{_+_}
         
            h_out(i) = mux(sum + b(i) >= 0, sum + b(i), 0) // relu
        }
        
        (h_out, active_out, counter_out, active_len_out, w, b, idx)
    }
    
    def backprop(active_len_out: Int, active_len_in: Int, counter_in: Int, counter_out: Int, lr: Float, w: SRAM1[T], b: SRAM1[T], err_out: SRAM1[T], h_in: SRAM1[T], h_out: SRAM1[T]) = {
    
        val w_new = SRAM[T](active_len_out * active_len_in)
        val b_new = SRAM[T](active_len_out)
        
        val err_in = SRAM[T](active_len_in)
        val err_in_tmp = SRAM[T](active_len_out, active_len_in)
        
        Foreach(0 until counter_out by 1 par op2) { i =>
            val err_out_tmp = err_out(i)
            val h_out_tmp = h_out(i)
            
            Foreach(0 until counter_in by 1 par ip) { j =>
                val w_tmp = w(i * counter_in + j)
                
                w_new(i * counter_in + j) = w_tmp - lr * err_out_tmp * h_in(j) * mux(h_out_tmp >= 0, 1.to[T], 0)
                err_in_tmp(i, j) = w_tmp * err_out_tmp * mux(h_out_tmp >= 0, 1.to[T], 0)
            }
            
            b_new(i) = b(i) - lr * err_out(i) * mux(h_out(i) >= 0, 1.to[T], 0)
        }
        
        Foreach(0 until counter_in by 1 par op2) { j =>
            val sum = Reduce(Reg[T])(0 until counter_out by 1 par ip) { i =>
                err_in_tmp(i, j)
            }{_+_}
         
            err_in(j) = sum
        }
        
        (err_in, w_new, b_new)
    }
    
    
    def writeback(active_len_in: Int, active_len_out: Int, counter_in: Int, counter_out: Int, idx: SRAM1[Int], active_out: SRAM1[Int], w: SRAM1[T], b: SRAM1[T], d_w: DRAM1[T], d_b: DRAM1[T]) = {
    
        d_w(idx par ip, counter_out * counter_in) scatter w
       
        d_b(active_out par ip, counter_out) scatter b
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
    
    
        val input = DRAM[Int](numBatch * input_max)
        val input_value = DRAM[T](numBatch * input_max)
        val t3 = loadCSV1D[Int](data + "/input.csv")
        val t4 = loadCSV1D[T](data + "/input_value.csv")
        setMem(input, t3)
        setMem(input_value, t4)
        

        val d_table_l1 = DRAM[Int](L_l1 * row_l1 * bucket)
        val d_table_l2 = DRAM[Int](L_l2 * row_l2 * bucket)
        val t5 = loadCSV1D[Int](data + "/table_l1.csv")
        val t6 = loadCSV1D[Int](data + "/table_l2.csv")
        setMem(d_table_l1, t5)
        setMem(d_table_l2, t6)
	
    
        val d_cnt_l1 = DRAM[Int](L_l1 * row_l1)
        val d_cnt_l2 = DRAM[Int](L_l2 * row_l2)
        val t7 = loadCSV1D[Int](data + "/cnt_l1.csv")
        val t8 = loadCSV1D[Int](data + "/cnt_l2.csv")
        setMem(d_cnt_l1, t7)
        setMem(d_cnt_l2, t8)
        

    
        Accel{ 

	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1 par op) { x =>
                    
                    val active_len_field = input_max
                    
                    val active_field = SRAM[Int](input_max)
                    val s_trainX = SRAM[T](input_max)
                    active_field load input(x * input_max::(x + 1) * input_max par ip)
                    s_trainX load input_value(x * input_max::(x + 1) * input_max par ip)
		          
                    val counter_field = input_max
           


           
                    // forward
                    val (h_l1, active_l1, counter_l1, active_len_l1, w_l1, b_l1, idx_l1) = forward(s_trainX, active_field, counter_field, active_len_field, field, L1, K_l1, L_l1, row_l1, x, d_cnt_l1, d_table_l1, d_w_l1, d_b_l1)
                    val (h_l2, active_l2, counter_l2, active_len_l2, w_l2, b_l2, idx_l2) = forward(h_l1, active_l1, counter_l1, active_len_l1, L1, L2, K_l2, L_l2, row_l2, x, d_cnt_l2, d_table_l2, d_w_l2, d_b_l2)


                    val err_l2 = SRAM[T](active_len_l2)
                    Foreach(0 until counter_l2 by 1 par ip) { i =>
                        err_l2(i) = h_l2(i)
                    }
                    

                    // backprop
                    val (err_l1, w_l2_new, b_l2_new) = backprop(active_len_l2, active_len_l1, counter_l1, counter_l2, lr, w_l2, b_l2, err_l2, h_l1, h_l2)
                    val (err_field, w_l1_new, b_l1_new) = backprop(active_len_l1, active_len_field, counter_field, counter_l1, lr, w_l1, b_l1, err_l1, s_trainX, h_l1)
                      
                    
                    // writeback to DRAM
                    writeback(active_len_l1, active_len_l2, counter_l1, counter_l2, idx_l2, active_l2, w_l2_new, b_l2_new, d_w_l2, d_b_l2)
                    writeback(active_len_field, active_len_l1, counter_field, counter_l1, idx_l1, active_l1, w_l1_new, b_l1_new, d_w_l1, d_b_l1)
             
                }
            }
        

        }

        
        assert(true)
    }
}