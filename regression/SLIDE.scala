import spatial.dsl._
import utils.io.files._

class SLIDE_0 extends SLIDE

@spatial abstract class SLIDE(
    numBatch:scala.Int = 100,
    epoch:scala.Int = 2,
  
    lr:scala.Float = 1e-3f,
  
    field:scala.Int = 135909,
    L1:scala.Int = 128,
    L2:scala.Int = 670091,
  
    K_l1:scala.Int = 6,
    K_l2:scala.Int = 18,
  
    L_l1:scala.Int = 20,
    L_l2:scala.Int = 50,
  
    ratio:scala.Int = 3,
  
    row_l1:scala.Int = 64,
    row_l2:scala.Int = 262144,
  
    bucket:scala.Int = 64,
    
    input_max:scala.Int = 20,
    label_max:scala.Int = 7
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
            val value = Reduce(Reg[Int])(0 until K by 1) {k => // every hash function
                val sum = Reduce(Reg[T])(0 until max by 1) { j => // sum across all inputs
                    val rand = if (sparse) mersenne_hash(active(j), k, l) else mersenne_hash(j, k, l)
                    mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(j), -input(j)), 0)
                }{_+_}
                mux(sum.value < 0, 1.to[Int] << k.to[I16], 0)
            }{_|_}

            results(l) = value
        }
        
        results
    }
  
    def forward(input: SRAM1[T], active_in: SRAM1[Int], counter_in: Int, active_len_in: Int, pre_layer_size: Int, K: Int, L: Int, row: Int, x: Int, d_cnt: DRAM1[Int], d_table: DRAM1[Int], d_w: DRAM1[T], d_b: DRAM1[T]) = {
        val hashcode = simhash(true, input, active_in, counter_in, K, L)
        val active_len_out = bucket
        val active_out = SRAM[Int](active_len_out)
        
        val num = (x * 127) % L
        val size = SRAM[Int](1)
        size load d_cnt(num * row + hashcode(num)::num * row + hashcode(num) + 1)
        
        active_out load d_table(num * row * bucket + hashcode(num) * bucket::num * row * bucket + hashcode(num) * bucket + bucket)
        
        val counter_out = size(0)
        
        

        val w = SRAM[T](active_len_out, active_len_in).buffer
        val fifo = FIFO[T](active_len_out * active_len_in)


        val idx = FIFO[Int](active_len_out * active_len_in)
        Foreach(0 until counter_out by 1) { i =>
            Foreach(0 until counter_in by 1) { j =>
                idx.enq(active_out(i) * pre_layer_size + active_in(j))
            }
        }
       
       
        fifo gather d_w(idx, counter_out * counter_in)
        Foreach(0 until counter_out by 1) { i =>
            Foreach(0 until counter_in by 1) { j =>
                w(i, j) = fifo.deq()
            }
        }
       
        val b = SRAM[T](active_len_out).buffer
        b gather d_b(active_out, counter_out)
        
        val h_out = SRAM[T](active_len_out)

        Foreach(0 until counter_out by 1) { i =>
            val sum = Reduce(Reg[T])(0 until counter_in by 1) { j =>
                w(i, j) * input(j)
            }{_+_}
         
            h_out(i) = mux(sum + b(i) >= 0, sum + b(i), 0) // relu
        }
        
        (h_out, active_out, counter_out, active_len_out, w, b, idx)
    }
    
    def backprop(active_len_in: Int, counter_in: Int, counter_out: Int, lr: Float, w: SRAM2[T], b: SRAM1[T], err_out: SRAM1[T], h_in: SRAM1[T], h_out: SRAM1[T]) = {
        val err_in = SRAM[T](active_len_in)
        Foreach(0 until counter_in by 1) { j =>
            val sum = Reduce(Reg[T])(0 until counter_out by 1) { i =>
                w(i, j) * err_out(i) * mux(h_out(i) >= 0, 1.to[T], 0)
            }{_+_}
         
            err_in(j) = sum
        }
       
        Foreach(0 until counter_out by 1) { i =>
            Foreach(0 until counter_in by 1) { j =>
                w(i, j) = w(i, j) - lr * err_out(i) * h_in(j) * mux(h_out(i) >= 0, 1.to[T], 0)
            }
        }
       
        Foreach(0 until counter_out by 1) { i =>
            b(i) = b(i) - lr * err_out(i) * mux(h_out(i) >= 0, 1.to[T], 0)
        }
        
        err_in
    }
    
    
    def writeback(active_len_in: Int, active_len_out: Int, counter_in: Int, counter_out: Int, idx: FIFO[Int], active_out: SRAM1[Int], w: SRAM2[T], b: SRAM1[T], d_w: DRAM1[T], d_b: DRAM1[T]) = {
        val fifo = FIFO[T](active_len_out * active_len_in)
        Foreach(0 until counter_out by 1) { i =>
            Foreach(0 until counter_in by 1) { j =>
                fifo.enq(w(i, j))
            }
        }
        d_w(idx, counter_out * counter_in) scatter fifo
       
        d_b(active_out, counter_out) scatter b
    }
    
  


    def main(args: Array[String]): Unit = {
	
        val d_w_l1 = DRAM[T](L1 * field)
        val d_w_l2 = DRAM[T](L2 * L1)
        val d_b_l1 = DRAM[T](L1)
        val d_b_l2 = DRAM[T](L2)
        val t1 = loadCSV1D[T]("/home/kosho/IO/load/d_w1.csv")
        val t2 = loadCSV1D[T]("/home/kosho/IO/load/d_w2.csv")
        setMem(d_w_l1, t1)
        setMem(d_w_l2, t2)
        setMem(d_b_l1, (0::L1) { i => 0.to[T] })
        setMem(d_b_l2, (0::L2) { i => 0.to[T] })
    
    
        val input = DRAM[Int](numBatch * input_max)
        val input_size = DRAM[Int](numBatch)
        val input_value = DRAM[T](numBatch * input_max)
        val t3 = loadCSV1D[Int]("/home/kosho/IO/load/input.csv")
        val t4 = loadCSV1D[Int]("/home/kosho/IO/load/input_size.csv")
        val t5 = loadCSV1D[T]("/home/kosho/IO/load/input_value.csv")
        setMem(input, t3)
        setMem(input_size, t4)
        setMem(input_value, t5)
	
	
        val label = DRAM[Int](numBatch * label_max)
        val label_size = DRAM[Int](numBatch)
        val t6 = loadCSV1D[Int]("/home/kosho/IO/load/label.csv")
        val t7 = loadCSV1D[Int]("/home/kosho/IO/load/label_size.csv")
        setMem(label, t6)
        setMem(label_size, t7)


        val d_table_l1 = DRAM[Int](L_l1 * row_l1 * bucket)
        val d_table_l2 = DRAM[Int](L_l2 * row_l2 * bucket)
        val t8 = loadCSV1D[Int]("/home/kosho/IO/load/table_l1.csv")
        val t9 = loadCSV1D[Int]("/home/kosho/IO/load/table_l2.csv")
        setMem(d_table_l1, t8)
        setMem(d_table_l2, t9)
	
    
        val d_cnt_l1 = DRAM[Int](L_l1 * row_l1)
        val d_cnt_l2 = DRAM[Int](L_l2 * row_l2)
        val t10 = loadCSV1D[Int]("/home/kosho/IO/load/cnt_l1.csv")
        val t11 = loadCSV1D[Int]("/home/kosho/IO/load/cnt_l2.csv")
        setMem(d_cnt_l1, t10)
        setMem(d_cnt_l2, t11)
        

    
        Accel{ 
            // val table_l1 = SRAM[Int](L_l1 * row_l1 * bucket)
            // val table_l2 = SRAM[Int](L_l2 * row_l2 * bucket)
        
            // val cnt_l1 = SRAM[Int](L_l1 * row_l1)
            // val cnt_l2 = SRAM[Int](L_l2 * row_l2)
            
            
            // table_l1 load d_table_l1
            // table_l2 load d_table_l2
            
            // cnt_l1 load d_cnt_l1
            // cnt_l2 load d_cnt_l2
           
           
            // Foreach(0 until L_l1 by 1) { i =>
                // Foreach(0 until row_l1 by 1) { j =>
                    // cnt_l1(i, j) = 0
                // }
            // }
	   
            // Foreach(0 until L_l2 by 1) { i =>
                // Foreach(0 until row_l2 by 1) { j =>
                    // cnt_l2(i, j) = 0
                // }
            // }
       

            // Foreach(0 until L1 by 1) { i =>
                // val tmp = SRAM[T](field)
                // tmp load d_w1(i * field::(i + 1) * field)
          
                // val hashcode = d_simhash(tmp, K_l1, L_l1)
             
                // Foreach(0 until L_l1 by 1) { j =>
                    // if (cnt_l1(j, hashcode(j)) < bucket) {
                        // table_l1(j, hashcode(j), cnt_l1(j, hashcode(j))) = i     
                        // cnt_l1(j, hashcode(j)) = cnt_l1(j, hashcode(j)) + 1
                    // }
                // }
            // }
	   
	   
            // Foreach(0 until L2 by 1) { i =>
                // val tmp = SRAM[T](L1)
                // tmp load d_w2(i * L1::(i + 1) * L1)
          
                // val hashcode = d_simhash(tmp, K_l2, L_l2)
             
                // Foreach(0 until L_l2 by 1) { j =>
                    // if (cnt_l2(j, hashcode(j)) < bucket) {
                        // table_l2(j, hashcode(j), cnt_l2(j, hashcode(j))) = i     
                        // cnt_l2(j, hashcode(j)) = cnt_l2(j, hashcode(j)) + 1
                    // }
                // }
            // }
       

	   
            Foreach (0 until epoch by 1) { _ =>
                Foreach (0 until numBatch by 1) { x =>
		   
		   
                    val active_len_field = input_max

                    val active_field = SRAM[Int](input_max)
                    val tmp1 = SRAM[Int](1)
                    val s_trainX = SRAM[T](input_max)
                    active_field load input(x * input_max::(x + 1) * input_max)
                    tmp1 load input_size(x::x + 1)
                    s_trainX load input_value(x * input_max::(x + 1) * input_max)
		          
                    val counter_field = tmp1(0)
           


           
                    // forward
                    val (h_l1, active_l1, counter_l1, active_len_l1, w_l1, b_l1, idx_l1) = forward(s_trainX, active_field, counter_field, active_len_field, field, K_l1, L_l1, row_l1, x, d_cnt_l1, d_table_l1, d_w_l1, d_b_l1)
                    val (h_l2, active_l2, counter_l2, active_len_l2, w_l2, b_l2, idx_l2) = forward(h_l1, active_l1, counter_l1, active_len_l1, L1, K_l2, L_l2, row_l2, x, d_cnt_l2, d_table_l2, d_w_l2, d_b_l2)


                    // loss
                    val s_label = SRAM[Int](label_max)
                    val tmp2 = SRAM[Int](1)
                    s_label load label(x * label_max::(x + 1) * label_max)
                    tmp2 load label_size(x::x + 1) 
                    
                    val s_label_size = tmp2(0)
		   
                    val err_l2 = SRAM[T](active_len_l2).buffer
                    Foreach(0 until counter_l2 by 1) { i =>
                        err_l2(i) = h_l2(i)
                    }
                    
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until s_label_size by 1) { j =>
                            if (active_l2(i) == s_label(j)) {
                                err_l2(i) = h_l2(i) - 1.0f / (s_label_size).to[T]
                            }
                        }
                    }

                    // backprop
                    val err_l1 = backprop(active_len_l1, counter_l1, counter_l2, lr, w_l2, b_l2, err_l2, h_l1, h_l2)
                    val err_field = backprop(active_len_field, counter_field, counter_l1, lr, w_l1, b_l1, err_l1, s_trainX, h_l1)
                      
                    
                    // writeback to DRAM
                    writeback(active_len_l1, active_len_l2, counter_l1, counter_l2, idx_l2, active_l2, w_l2, b_l2, d_w_l2, d_b_l2)
                    writeback(active_len_field, active_len_l1, counter_field, counter_l1, idx_l1, active_l1, w_l1, b_l1, d_w_l1, d_b_l1)
             
                }
            }
        

        }

        
        assert(true)
    }
}