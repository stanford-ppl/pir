import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.misc._
import pir.graph.enums._
import pir.plasticine.config._

/**
----------------------------------------------------------
Convolution in PIR
----------------------------------------------------------

DRAM:

  A1   A2   A3  ...  A64
  B1   B2   B3  ...  B64
  C1   C2   C3  ...  C64
  ...


Scratchpad:

  A1   A17  ...  ...  B1   ...  ...  ...  ...         <-- SRAM 1
  A2   A18  ...  ...  B2   ...  ...  ...  ...         <-- SRAM 2
  ...  ...  ...  ...  ...  ...  ...  ...  ...              ...
  A16  A32  ...  A64  B16  ...  ...  ...  H64         <-- SRAM 16

Flattened Matrix format:

  A1..A16  A17..A32 ...
  B1..B16  B17..B32 ...
  ...


ALU read order:

  A1   B1   C1   D1   A17
  A2   B2   C2   D2   A18
  ...  ...  ...  ...  ...
  A16  B16  C16  D16  A31

**/

object Convolution extends PIRApp {

  def main(args: String*)(top:Top) = {
    
    /***
      
        for outD_i = 1 to outD {      // loop_outer_depth   seq
          SRAM holding partial sum = 0
          for inD_i = 1 to inD {      // loop_inner_depth   seq
       x    read first 4 rows from DRAM, i.e. 0 -> 64*4
            read next 16 kernel weights from DRAM
            for img_DRAM_addr = 64*4, img_DRAM_addr < 64*64, img_DRAM_addr += 64*4 {      // loop_conv2D
              parallel{
               1.{
                read next 4 rows into DRAM (line buffer)
               }//P1
               2.{
                for j = 1 to 4 (this is sliding the 4x16 computation, i.e. the 4 4x4 windows, horizontally across 4 times) {
                  for i = 1 to 4 (this is loading the 4 rows, i.e. this is a vertical counter) {
                    load vector of size 16 from one SRAM to the other SRAM (reorganized)
                    read 4 kernel weights from SRAM to ALUs
                    read this vector of size 16 into ALUs and process
                    coalesce 4 outputs
                  }
                }
                accumulate the 16 coalesced back to what is already in SRAM
               }//P2
              }//Parallel
            }//loop_conv2D
          }//loop_inner_depth
          write SRAM with partial sum to DRAM
        }//loop_outer_depth
              
            
     ***/
    
    val img = OffChip("img")
    val kernel = OffChip("kernel")
    val out = OffChip("out")
    val mc_img = MemoryController(TileLoad,  img)
    val mc_kernel = MemoryController(TileLoad, kernel)
    val mc_out = MemoryController(TileStore, out)
    val img_reorg_vector = Vector("img_reorg")
    //val kernel_vector = Vector("kernel_vector")
    val psum_vector = Vector("psum_vector")
    val final_sum_vector = Vector("final_sum_vector")  // Passed from accum CU to write_to_DRAM CU
    
    // ------------------------------------------------------------------------------
    // loop_outer_depth
    // ------------------------------------------------------------------------------
    // loop_outer_depth sequential, keeps track of kernel weights DRAM storage and also where to write outputs back to DRAM
    val loop_outer_depth = Sequential(name="loop_outer_depth", parent=top, deps=Nil){ implicit CU =>
      // Will multiply this counter by constants to turn it into
      // - address counter for kernel weights
      // - address counter of where to write results back to DRAM
      val counter_outer_depth = CounterChain(name="counter_outer_depth", Const("96i") by Const("1i"))
      // TODO: Need to reset output SRAM holding partial sums to 0 (add mux to write 0s to it or to not accumulate, see below)
    }

    // ------------------------------------------------------------------------------
    // loop_inner_depth
    // ------------------------------------------------------------------------------
    // loop_inner_depth MetaPipeline, keeps track of where to load next 2D image from DRAM
    val loop_inner_depth = MetaPipeline(name="loop_inner_depth", parent=loop_outer_depth, deps=Nil){ implicit CU =>
      val counter_inner_depth = CounterChain(name="counter_inner_depth", Const("96i") by Const("1i"))
    }

    // Stream controller for reading in kernel
    val streamControllerKernel = StreamController(name = "streamControllerKernel", parent=loop_inner_depth, deps=List()) { implicit CU => 
      // val stage0 = CU.emptyStage
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      // var stage: List[Stage] = Nil
    }
    // The goal of this CU is to set the proper offset and len for mc_kernel
    val setUpLoadsKernel = UnitPipeline(name="setUpLoadsKernel", parent=streamControllerKernel, deps=Nil) { implicit CU =>
    
      val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val counter_outer_depth = CounterChain.copy(loop_outer_depth, "counter_outer_depth")
      val counter_inner_depth = CounterChain.copy(loop_inner_depth, "counter_inner_depth")
      val DRAM_address_tmp1 = CU.temp
      val DRAM_address_tmp2 = CU.temp
      
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(Const("16i") ), op=Bypass, results=List(CU.scalarOut(stage(1), mc_kernel.len)))
      Stage(stage(2), operands=List(counter_outer_depth(0), Const("1536i" /* 16x96 */)), op=FixMul, results=List(CU.temp(stage(2), DRAM_address_tmp1)))
      Stage(stage(3), operands=List(counter_inner_depth(0), Const("16i")),   op=FixMul, results=List(CU.temp(stage(3), DRAM_address_tmp2)))
      Stage(stage(4), operands=List(CU.temp(stage(3), DRAM_address_tmp1), CU.temp(stage(3), DRAM_address_tmp2)), op=FixAdd, results=List(CU.scalarOut(stage(4), mc_kernel.ofs)))
    }
    
    // ------------------------------------------------------------------------------
    // loop_conv2D
    // ------------------------------------------------------------------------------
    // loop_conv2D meta-pipe, keeps track of where to load next rows of this 2D image from DRAM
    val loop_conv2D = MetaPipeline(name="loop_conv2D", parent=loop_inner_depth, deps=List(streamControllerKernel)){ implicit CU =>
      // Assume image is 64x64
      // This will load from DRAM into SRAM
      // Overall SRAM will store 8 rows = 512 words (floats), and buffers 4 rows (256) at a time
      val read_DRAM_counter_conv2D = CounterChain(name="read_DRAM_counter_conv2D", Const("4096i") by Const("256i")) // 256 incr = 4 rows, and total 16 groups of 4 rows = 64x64 = 4096
    }
    // Stream controller for reading in image
    val streamControllerImage = StreamController(name = "streamControllerImage", parent=loop_conv2D, deps=List()) { implicit CU => 
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      // val stage0 = CU.emptyStage
      // val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      // var stage: List[Stage] = Nil
    }
    // The goal of this CU is to set the proper offset and len for mc_img
    val setUpLoads = UnitPipeline(name="setUpLoads", parent=streamControllerImage, deps=Nil) { implicit CU =>
    
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val counter_inner_depth = CounterChain.copy(loop_inner_depth, "counter_inner_depth")
      val read_DRAM_counter_conv2D = CounterChain.copy(loop_conv2D, "read_DRAM_counter_conv2D")
      val DRAM_address_tmp = CU.temp
      
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(Const("256i") ), op=Bypass, results=List(CU.scalarOut(stage(1), mc_img.len))) // This is 4 rows
      Stage(stage(2), operands=List(counter_inner_depth(0), Const("4096i")), op=FixMul, results=List(CU.temp(stage(2), DRAM_address_tmp)))
      Stage(stage(3), operands=List(read_DRAM_counter_conv2D(0), CU.temp(stage(2), DRAM_address_tmp)), op=FixAdd, results=List(CU.scalarOut(stage(3), mc_img.ofs)))
    }
    // The goal of this CU is to read from the memory controller and
    // write to other SRAMs vector output (vecOut).
    // It will read from DRAM in the format (A1...A64 B1...B64 C1...C64 D1...D64),
    // reformat and write to the SRAM as    (A1...A16 B1...B16 C1...C16 D1...D16 A17...A32 B17...B32 C17...C32 D17...D32 ...)  
    val reorgData = MetaPipeline(name="reorgData", parent=loop_conv2D, deps=List(streamControllerImage)) { implicit CU =>
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      // Contains 2 counters
    }
    val convCompute_j = Pipeline(name="convCompute_j", parent=reorgData, deps=Nil) { implicit CU =>   // Horizontal counter (move right to next group of 4 rows)
      val j = CounterChain("j", Const("64i") by Const("16i"))
    }
    val convCompute_i = Pipeline(name="convCompute_i", parent=reorgData, deps=List(convCompute_j)) { implicit CU => // Vertical counter (move down 1 row in current group of 4 rows)
    
      /*
      Counters:
      - from DRAM, we read 4 rows (256) which is e.g. (A1...A64 B1...B64 C1...C64 D1...D64)
        - so the counter increments by 256
      - then in this CU, we read that 256 in groups of 16 (16 of these in total), and write it to the correct place:
        Correct destinations: (should be 1 row of 16 but I split into 2D for clarity)
        - 0     64      128     192
          16    80      144     208
          32    96      160     224
          48    112     176     240
      */
      
      // FUTURE TODO: Once we pass the reorganized into next CU, we will need to load new weights from SRAM every cycle (row A B C D A B C D A B C D A B C D)
      // We can instead do the multiplication in this CU, since we are reading from DRAM A A A A B B B B C C C C D D D D
    
      // DRAM reads 4 rows but needs to reorganize when writing to SRAM, using 2 counters
      val i = CounterChain(Const("256i") by Const("64i")) // TODO: assumes 16 (illegal now) or do in CU? Can multiply by 4i
      val j = CounterChain.copy(convCompute_j, "j")
      
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
      
      val img_DRAM_format = SemiFIFO(size = 16, banking = Strided(1), buffering = SingleBuffer()).wtPort(mc_img.vdata)
      // stage = stage0 +: WAStages(1, List(img_DRAM_format))
      // Stage(stage(1), operands=List(i(0), j(0)), op=FixAdd, results=List(img_DRAM_format.writeAddr))
      
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(i(0), j(0)), op=FixAdd, results=List(img_DRAM_format.readAddr))
      Stage(stage(2), operands=List(img_DRAM_format.load), op=Bypass, results=List(CU.vecOut(stage(2), img_reorg_vector)))
    }
    // The goal of this CU is to read from DRAM to SRAM and perform the convolution
    val convCompute = Pipeline(name="convCompute", parent=loop_conv2D, deps=List(reorgData)) { implicit CU =>

      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
      
      // Double buffering -- maybe this should be the j counter above? When it wraps swap read/write buffers?
      // Also is it ok to have same counter for read/write? (Since only 2 buffers)
      // val swapReadWriteBuffers = CounterChain(name="swapReadWriteBuffers", Const("256i") by Const("16i"))
      val image_SRAM_read_write_pointer = CounterChain(name="image_SRAM_read_write_pointer", Const("256i") by Const("16i"))
      
      // ------------------------------------
      // SRAMs:
      // ------------------------------------
      
      // Read image from vector input of reorganizing CU
      // Currently I store into SRAM (double-buffered to implement line buffer). Is this SRAM necessary?
      val img_SRAM = SRAM(size = 256 /*512*/ /*64*8*/, writeCtr = image_SRAM_read_write_pointer(0), banking = Strided(1),
        buffering=MultiBuffer(2, swapRead = image_SRAM_read_write_pointer(0), swapWrite = image_SRAM_read_write_pointer(0))).wtPort(img_reorg_vector)
      stage = stage0 +: WAStages(1, List(img_SRAM))
      Stage(stage(1), operands=List(image_SRAM_read_write_pointer(0)), op=Bypass, results=List(img_SRAM.writeAddr))
      
      // Read kernel from DRAM.
      // FUTURE TODO: This currently will read the weights in the order:
      //  1 2 3 4 5 ... 16
      // However we want to read them in the order:
      //  1 2 3 4 1 2 3 4 1 2 3 4 1 2 3 4 
      // then the next cycle, read
      //  5 6 7 8 5 6 7 8 5 6 7 8 5 6 7 8 
      // etc., until after 4 cycles it wraps back to 1 2 3 4
      // For that we need another CU to reorganize, and then this will come from vecIn like the image, but for now
      // read directly from DRAM into SRAM
      val kernel_SRAM = SemiFIFO(name="kernel_SRAM", size = 16, banking = Strided(1), buffering = SingleBuffer()).wtPort(mc_kernel.vdata)
      
      // Previously we also had SRAM for output, but now output goes to another CU (through vecOut) to be coalesced
      // val sPsum = SRAM(name="sKernel", size=32, readAddr=ii(0), writeAddr=ii(0),
        // banking=Strided(4), buffering=SingleBuffer(), writeCtr=ii(0))
      
      // Do SRAM load + multiplication
      val horizontal_accumulator = CU.accum(init = Const("0i"))
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(image_SRAM_read_write_pointer(0)), op=Bypass, results=List(img_SRAM.readAddr))
      Stage(stage(2), operands=List(Const("0i")), op=Bypass, results=List(kernel_SRAM.readAddr))
      Stage(stage(3), operands=List(img_SRAM.load, kernel_SRAM.load), op=FltMul, results=List(CU.reduce(stage(3))))
      // Do reduction, first vertical then horizontal
      // FUTURE TODO: Reduce into multiple registers
      val (sr, products_vertical_sum) = Stage.reduce(op=FltAdd, init=Const("0i"))
      val horiz_parial_sum = CU.temp
      Stage(stage(4), operands=List(products_vertical_sum, CU.accum(stage(4), horizontal_accumulator)), op=FltAdd, results=List(CU.temp(stage(4), horiz_parial_sum), CU.accum(stage(4), horizontal_accumulator)))
      Stage(stage(5), operands=List(CU.temp(stage(4), horiz_parial_sum)), op=Bypass, results=List(CU.vecOut(stage(5), psum_vector)))
      
      // SRAM store
      // FUTURE TODO: Need logic only to store sometimes? And coalesce before storing?
      // val SRAM_write_counter = CounterChain(name = "SRAM_write_counter", Const("256i") by Const("16i"))
      // Stage(stage(3), operands=List(horiz_parial_sum), op=Bypass, results=List(CU.store(stage(3), sPsum)))
    }
    // The goal of this CU is to read current partial sums (psums) from previous CU vecOut, coaslesce that vector output, 
    // store into SRAM, and then read from that SRAM as well as the other previous sum SRAM, sum them, and write back to
    // that second SRAM. For now the coalescing is skipped (so it seems unnecessary to store the vecOut of the previous
    // CU into an SRAM, but this is because the coalescing does not happen at the same rate as the summing, it happens 4x slower)
    // FUTURE TODO: use constant mask, since we know statically what is valid for now
    val accum2dOutput = Pipeline(name="accum2dOutput", parent=loop_conv2D, deps=List(convCompute)) { implicit CU =>

      val psum_SRAM_read_write_addr = CounterChain(name="psum_SRAM_read_write_addr", Const("256i") by Const("16i")) // read 16 x 16 image 1 row at a time
      val coalescing_SRAM = SRAM(size = 256, writeCtr = psum_SRAM_read_write_addr(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(psum_vector)
      val psum_SRAM       = SRAM(size = 256, writeCtr = psum_SRAM_read_write_addr(0), banking = Strided(1), buffering = SingleBuffer())
      val previous_psum = CU.temp
      val to_be_added_psum = CU.temp
      
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
      
      // Store vector output from previous CU (assume coalesced for now) into SRAM
      stage = stage0 +: WAStages(1, List(coalescing_SRAM))
      Stage(stage(1), operands=List(psum_SRAM_read_write_addr(0)), op=Bypass, results=List(coalescing_SRAM.writeAddr))

      // Sum and write back to SRAM
      stage = stage0 +: WAStages(1, List(psum_SRAM))
      Stage(stage(1), operands=List(psum_SRAM_read_write_addr(0)), op=Bypass, results=List(psum_SRAM.writeAddr))
      
      stage = stage0 +: Stages(5)
      // Load from 2 SRAMs
      Stage(stage(1), operands=List(psum_SRAM_read_write_addr(0)), op=Bypass, results=List(coalescing_SRAM.readAddr))
      Stage(stage(2), operands=List(coalescing_SRAM.load), op=Bypass, results=List(CU.temp(stage(2), to_be_added_psum)))
      Stage(stage(3), operands=List(psum_SRAM_read_write_addr(0)), op=Bypass, results=List(psum_SRAM.readAddr))
      Stage(stage(4), operands=List(psum_SRAM.load), op=Bypass, results=List(CU.temp(stage(4), previous_psum)))
      // Add
      Stage(stage(5), operands=List(CU.temp(stage(4), previous_psum), CU.temp(stage(4), to_be_added_psum)), op=FltAdd, results=List(CU.store(stage(5), psum_SRAM), CU.vecOut(stage(5), final_sum_vector)))
   
      // From GDA:
      // Stage(stage(5), operands=List(CU.load(stage(4), x3794_x3871), x3684_x3874.load), op=FltAdd, results=List(CU.vecOut(stage(5), x3684_vector), CU.store(stage(5), x3684_x3874)))
      // Stage(stage(6), operands=List(CU.ctr(stage(5), x3690(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(6), tr422)))
      // Stage(stage(7), operands=List(CU.temp(stage(6), tr422), CU.ctr(stage(6), x3690(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr424)))
    }
    // ------------------------------------------------------------------------------
    // END loop_conv2D
    // ------------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------------
    // END loop_inner_depth
    // ------------------------------------------------------------------------------
    
    // Stream controller for writing output to DRAM
    val streamControllerOutput = StreamController(name = "streamControllerOutput", parent=loop_outer_depth, deps=List(loop_inner_depth)) { implicit CU => 
      // val stage0 = CU.emptyStage
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      // var stage: List[Stage] = Nil
    }
    // The goal of this CU is to set up store to DRAM (offset, length)
    UnitPipeline (name="setUpStore", parent=streamControllerOutput, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
       val x3639_unitcc = CounterChain(name = "x3639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val write_SRAM_to_DRAM_counter = CounterChain.copy(loop_outer_depth, "counter_outer_depth")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      // Output size is 16x16 = 256
      Stage(stage(1), operands=List(Const("256i")), op=Bypass, results=List(CU.scalarOut(stage(1), mc_out.len)))
      Stage(stage(2), operands=List(write_SRAM_to_DRAM_counter(0), Const("256i")), op=FixMul, results=List(CU.scalarOut(stage(2), mc_out.ofs)))
    }
    // The goal of this CU is to read vecOut of the CU doing SRAM accumulation into another SRAM,
    // and then write the contents of that SRAM (the output image) to DRAM
    Pipeline(name="coalesceStore", parent=streamControllerOutput, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      // Read from other CU's vector output into SRAM of this CU
      val SRAM_read_write_counter = CounterChain(name = "SRAM_read_write_counter", Const("256i") by Const("16i"))
      val SRAM_with_final_sums = SRAM(size = 256, writeCtr = SRAM_read_write_counter(0), // TODO: Is this writeCtr input correct?
        banking = Strided(1), buffering = SingleBuffer()).wtPort(final_sum_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(SRAM_with_final_sums))
      Stage(stage(1), operands=List(SRAM_read_write_counter(0)), op=Bypass, results=List(SRAM_with_final_sums.writeAddr))
      
      // Now also write from the SRAM of this CU out to DRAM
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(SRAM_read_write_counter(0)), op=Bypass, results=List(SRAM_with_final_sums.readAddr))
      Stage(stage(2), operands=List(SRAM_with_final_sums.load),  op=Bypass, results=List(CU.vecOut(stage(2), mc_out.vdata)))
    }
    // ------------------------------------------------------------------------------
    // END loop_outer_depth
    // ------------------------------------------------------------------------------
  }
}
