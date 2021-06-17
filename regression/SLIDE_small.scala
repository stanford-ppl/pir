import spatial.dsl._
import utils.io.files._

class SLIDE_small_0 extends SLIDE_small

@spatial abstract class SLIDE_small(
    numBatch:scala.Int = 100,
    epoch:scala.Int = 2,
  
    lr:scala.Float = 1e-3f,
  
    field:scala.Int = 12,
    L1:scala.Int = 6,
    L2:scala.Int = 20,
  
    K_l1:scala.Int = 2,
    K_l2:scala.Int = 6,
  
    L_l1:scala.Int = 20,
    L_l2:scala.Int = 50,
  
    ratio:scala.Int = 3,
  
    row_l1:scala.Int = 4,
    row_l2:scala.Int = 64,
  
    bucket:scala.Int = 16,
    
    input_max:scala.Int = 100,
    label_max:scala.Int = 7
) extends SpatialTest with AppUtil {

    type T = Float
  
    def mersenne_hash(j:Int, k:Int, l:Int): Int = {
        val MERSENNE_2 = 8191
        val MERSENNE_3 = 131071
        val MERSENNE_4 = 524287
    
        (j * MERSENNE_2) + (k * MERSENNE_3) + (l * MERSENNE_4)
    }
  
    def s_simhash_with_max[T:Num](input: SRAM1[T], active: SRAM1[Int], max: Int, K: Int, L: Int): SRAM1[Int] = {
        val results = SRAM[Int](L)
    
        Foreach(0 until L by 1) { l => // every table
            val value = Reduce(Reg[Int])(0 until K by 1) {k => // every hash function
                val sum = Reduce(Reg[T])(0 until max by 1) { j => // sum across all inputs
                    val rand = mersenne_hash(active(j), k, l)
                    mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(j), -input(j)), 0)
                }{_+_}
                mux(sum.value < 0, 1.to[Int] << k.to[I16], 0)
            }{_|_}

            results(l) = value
        }
        
        results
    }
  
    def d_simhash[T:Num](input: SRAM1[T], K:Int, L:Int): SRAM1[Int] = {
        val results = SRAM[Int](L)
    
        Foreach(0 until L by 1) { l => // every table
            val value = Reduce(Reg[Int])(0 until K by 1) {k => // every hash function
                val sum = Reduce(Reg[T])(0 until input.length by 1) { j => // sum across all inputs
                    val rand = mersenne_hash(j, k, l)
                    mux((rand >> 2) % ratio == 0, mux(rand % 2 == 0, input(j), -input(j)), 0)
                }{_+_}
                mux(sum.value < 0, 1.to[Int] << k.to[I16], 0)
            }{_|_}

            results(l) = value
        }
    
        results
    }
  


    def main(args: Array[String]): Unit = {
	
        val d_w1 = DRAM[T](L1 * field)
        val d_w2 = DRAM[T](L2 * L1)
        val d_b1 = DRAM[T](L1)
        val d_b2 = DRAM[T](L2)
        val t1 = loadCSV1D[T]("/home/kosho/IO/load_small/d_w1.csv", ",")
        val t2 = loadCSV1D[T]("/home/kosho/IO/load_small/d_w2.csv", ",")
        setMem(d_w1, t1)
        setMem(d_w2, t2)
        setMem(d_b1, (0::L1) { i => 0.to[T] })
        setMem(d_b2, (0::L2) { i => 0.to[T] })
    
    
        val input = DRAM[Int](numBatch * input_max)
        val input_size = DRAM[Int](numBatch)
        val input_value = DRAM[T](numBatch * input_max)
        val t3 = loadCSV1D[Int]("/home/kosho/IO/load_small/input.csv", ",")
        val t4 = loadCSV1D[Int]("/home/kosho/IO/load_small/input_size.csv", ",")
        val t5 = loadCSV1D[T]("/home/kosho/IO/load_small/input_value.csv", ",")
        setMem(input, t3)
        setMem(input_size, t4)
        setMem(input_value, t5)
	
	
        val label = DRAM[Int](numBatch * label_max)
        val label_size = DRAM[Int](numBatch)
        val t6 = loadCSV1D[Int]("/home/kosho/IO/load_small/label.csv", ",")
        val t7 = loadCSV1D[Int]("/home/kosho/IO/load_small/label_size.csv", ",")
        setMem(label, t6)
        setMem(label_size, t7)


        val d_table_l1 = DRAM[Int](L_l1 * row_l1 * bucket)
        val d_table_l2 = DRAM[Int](L_l2 * row_l2 * bucket)
        val t8 = loadCSV1D[Int]("/home/kosho/IO/load_small/table_l1.csv", ",")
        val t9 = loadCSV1D[Int]("/home/kosho/IO/load_small/table_l2.csv", ",")
        setMem(d_table_l1, t8)
        setMem(d_table_l2, t9)
	
    
        val d_cnt_l1 = DRAM[Int](L_l1 * row_l1)
        val d_cnt_l2 = DRAM[Int](L_l2 * row_l2)
        val t10 = loadCSV1D[Int]("/home/kosho/IO/load_small/cnt_l1.csv", ",")
        val t11 = loadCSV1D[Int]("/home/kosho/IO/load_small/cnt_l2.csv", ",")
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
                    val size1 = SRAM[Int](1)
                    val s_trainX = SRAM[T](input_max)
                    active_field load input(x * input_max::(x + 1) * input_max)
                    size1 load input_size(x::x + 1)
                    s_trainX load input_value(x * input_max::(x + 1) * input_max)
		   
           
                    val counter_field = size1(0)
           
           
                    // forward l1
                    val hashcode_l1 = s_simhash_with_max(s_trainX, active_field, counter_field, K_l1, L_l1)
                    val active_len_l1 = bucket
                    val active_l1 = SRAM[Int](active_len_l1)
                    
                    val num_l1 = (x * 127) % L_l1
                    val size2 = SRAM[Int](1)
                    size2 load d_cnt_l1(num_l1 * row_l1 + hashcode_l1(num_l1)::num_l1 * row_l1 + hashcode_l1(num_l1) + 1)
                    
                    active_l1 load d_table_l1(num_l1 * row_l1 * bucket + hashcode_l1(num_l1) * bucket::num_l1 * row_l1 * bucket + hashcode_l1(num_l1) * bucket + bucket)
                    
                    val counter_l1 = size2(0)
                    
                    
  
                    val s_w1 = SRAM[T](active_len_l1, active_len_field).buffer
                    val fifo_w1 = FIFO[T](active_len_l1 * active_len_field)
           
		   
                    val idx_l1 = SRAM[Int](active_len_l1 * active_len_field)
                    Foreach(0 until counter_l1 by 1) { i =>
                        Foreach(0 until counter_field by 1) { j =>
                            idx_l1(i * counter_field + j) = active_l1(i) * field + active_field(j)
                        }
                    }
                   
                   
                    fifo_w1 gather d_w1(idx_l1, counter_l1 * counter_field)
                    Foreach(0 until counter_l1 by 1) { i =>
                        Foreach(0 until counter_field by 1) { j =>
                            s_w1(i, j) = fifo_w1.deq()
                        }
                    }
                   
                    val s_b1 = SRAM[T](active_len_l1).buffer
                    s_b1 gather d_b1(active_l1, counter_l1)
                    
                    val s_h1 = SRAM[T](active_len_l1)

                    Foreach(0 until counter_l1 by 1) { i =>
                        val sum = Reduce(Reg[T])(0 until counter_field by 1) { j =>
                            s_w1(i, j) * s_trainX(j)
                        }{_+_}
                     
                        s_h1(i) = mux(sum + s_b1(i) >= 0, sum + s_b1(i), 0) // relu
                    }
           




		   
		   
                    // forward l2
                    val hashcode_l2 = s_simhash_with_max(s_h1, active_l1, counter_l1, K_l2, L_l2)
                    val active_len_l2 = bucket
                    val active_l2 = SRAM[Int](active_len_l2)

                    val num_l2 = (x * 127) % L_l2
                    val size3 = SRAM[Int](1)
                    size3 load d_cnt_l2(num_l2 * row_l2 + hashcode_l2(num_l2)::num_l2 * row_l2 + hashcode_l2(num_l2) + 1)
                    
                    active_l2 load d_table_l2(num_l2 * row_l2 * bucket + hashcode_l2(num_l2) * bucket::num_l2 * row_l2 * bucket + hashcode_l2(num_l2) * bucket + bucket)
                    
                    val counter_l2 = size3(0)
                    
           

                    val s_w2 = SRAM[T](active_len_l2, active_len_l1).buffer
                    val fifo_w2 = FIFO[T](active_len_l2 * active_len_l1)
                   
                    val idx_l2 = SRAM[Int](active_len_l2 * active_len_l1)
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until counter_l1 by 1) { j =>
                            idx_l2(i * counter_l1 + j) = active_l2(i) * L1 + active_l1(j)
                        }
                    }
                    
                    fifo_w2 gather d_w2(idx_l2, counter_l2 * counter_l1)
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until counter_l1 by 1) { j =>
                            s_w2(i, j) = fifo_w2.deq()
                        }
                    }
                   
                    val s_b2 = SRAM[T](active_len_l2).buffer
                    s_b2 gather d_b2(active_l2, counter_l2) 
                   
                    val s_h2 = SRAM[T](active_len_l2)
                   
                    Foreach(0 until counter_l2 by 1) { i =>
                        val sum = Reduce(Reg[T])(0 until counter_l1 by 1) { j =>
                            s_w2(i, j) * s_h1(j)
                        }{_+_}
                     
                        s_h2(i) = mux(sum + s_b2(i) >= 0, sum + s_b2(i), 0) // relu
                    }
                   
                      





                    // loss
                    val s_label = SRAM[Int](label_max)
                    val size4 = SRAM[Int](1)
                    s_label load label(x * label_max::(x + 1) * label_max)
                    size4 load label_size(x::x + 1)
                    
                    
                    val s_label_size = size4(0)
                    
		   
		   
                    val s_err2 = SRAM[T](active_len_l2).buffer
                    Foreach(0 until counter_l2 by 1) { i =>
                        s_err2(i) = s_h2(i)
                    }
                    
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until s_label_size by 1) { j =>
                            if (active_l2(i) == s_label(j)) {
                                s_err2(i) = s_h2(i) - 1.0f / (s_label_size).to[T]
                            }
                        }
                    }
              
              
              
              

           
           
           


                    // backprop l2
                    val s_err1 = SRAM[T](active_len_l1)
                    Foreach(0 until counter_l1 by 1) { j =>
                        val sum = Reduce(Reg[T])(0 until counter_l2 by 1) { i =>
                            s_w2(i, j) * s_err2(i) * mux(s_h2(i) >= 0, 1.to[T], 0)
                        }{_+_}
                     
                        s_err1(j) = sum
                    }
                   
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until counter_l1 by 1) { j =>
                            s_w2(i, j) = s_w2(i, j) - lr * s_err2(i) * s_h1(j) * mux(s_h2(i) >= 0, 1.to[T], 0)
                        }
                    }
                   
                    Foreach(0 until counter_l2 by 1) { i =>
                        s_b2(i) = s_b2(i) - lr * s_err2(i) * mux(s_h2(i) >= 0, 1.to[T], 0)
                    } 
                      
                      
                      
                      
                      
                    

                    
                    // backprop l1
                    Foreach(0 until counter_l1 by 1) { i =>
                        Foreach(0 until counter_field by 1) { j =>
                            s_w1(i, j) = s_w1(i, j) - lr * s_err1(i) * s_trainX(j) * mux(s_h1(i) >= 0, 1.to[T], 0)
                        }
                    }
                   
                    Foreach(0 until counter_l1 by 1) { i =>
                        s_b1(i) = s_b1(i) - lr * s_err1(i) * mux(s_h1(i) >= 0, 1.to[T], 0)
                    }   
                      
                   
                      

                   
                   
                    // store l2
                    val fifo_w2_store = FIFO[T](active_len_l2 * active_len_l1)
                    Foreach(0 until counter_l2 by 1) { i =>
                        Foreach(0 until counter_l1 by 1) { j =>
                            fifo_w2_store.enq(s_w2(i, j))
                        }
                    }
                    d_w2(idx_l2, counter_l2 * counter_l1) scatter fifo_w2_store
                   
                    d_b2(active_l2, counter_l2) scatter s_b2  
                      
                      
                
                      
                      
                    // store l1
                    val fifo_w1_store = FIFO[T](active_len_l1 * active_len_field)
                    Foreach(0 until counter_l1 by 1) { i =>
                        Foreach(0 until counter_field by 1) { j =>
                            fifo_w1_store.enq(s_w1(i, j))
                        }
                    }
                    d_w1(idx_l1, counter_l1 * counter_field) scatter fifo_w1_store
                   
                    d_b1(active_l1, counter_l1) scatter s_b1    
           
           

             
                }
            }
        

        }
    
    

        // val dd_table_ll = getMem(d_table_l1)
        // val dd_table_l2 = getMem(d_table_l2)
        
        // val dd_cnt_l1 = getMem(d_cnt_l1)
        // val dd_cnt_l2 = getMem(d_cnt_l2) 
        

	
	
        // writeCSV1D[Int](dd_table_ll, "/home/kosho/IO/store/dd_table_ll.csv", ",")
        // writeCSV1D[Int](dd_table_l2, "/home/kosho/IO/store/dd_table_l2.csv", ",")
        
        // writeCSV1D[Int](dd_cnt_l1, "/home/kosho/IO/store/dd_cnt_l1.csv", ",")
        // writeCSV1D[Int](dd_cnt_l2, "/home/kosho/IO/store/dd_cnt_l2.csv", ",")
        
        assert(true)
    }
}