//import spatial.dsl._

//@spatial class PageRankGrazelle extends DSETest {
  ////override def runtimeArgs: Args = "50 0.125"
  //lazy val param = DotProductParam()

  //type Elem = FixPt[TRUE, _16, _16] // Float
  ////type X = FixPt[TRUE, _16, _16] // Float
  //type X = FixPt[TRUE, _32, _32] // Float

  //type P = Fix[TRUE, _64, _0] // Long

  //def cceil(num: Double): Long = {
    //val numm = num.to[Long]
    //val nummm = numm.to[Double]
    //if (num === nummm) numm
    //else numm + 1
  //}

  //val margin = 0.01

  //def gather_out_degrees(arr: Array[Long], edges:Array[Long]) = {

    //assert (edges.length % 4 == 0)


    //for (idx <- 0 until arr.length) {
      //arr(idx) = 0
    //}

    //for (idx <- 0 until edges.length) {
      //val edge:Long = edges(idx)
      //val valid = edge >> 63

      //if (valid != 0) {
        //val src = (edge & 0x0000ffffffffffffl).to[Int]
        //val orig = arr(src)
        //arr(src) = orig + 1
      //}
    //}
  //}

  //def main(args: Array[String]): Unit = {
    //val dir = "/armadillo/users/ctimothy/fpga-grazelle/test-data/pagerank/"
    //val mat = "vs/ash331.csv"

    //// I/O
    //val f = openBinary(dir.concat(mat), false)

    //val numbers = readBinary[Long](f)

    //val n_edge_spaces = (numbers.length - 2)
    //val edges_per_container = 4
    //val n_containers =  n_edge_spaces / edges_per_container
    //val n_nodes = numbers(0)
    //val n_edges = numbers(1)

    //val itersIN = 1

    //val n_thread = 4
    //val container_per_thread = 8
    //val container_per_compute = 2
    //val simd = container_per_compute * edges_per_container
    //val tile_size = 16
    //val edges_per_thread = edges_per_container * container_per_thread // # total number of edges processed by thread

    //val container_batch_size = 64

    //val thread_iters = cceil(n_containers.to[Double] /  container_per_thread.to[Double])
    //val edges_only = Array.tabulate(n_edge_spaces){ i => numbers(i + 2) }
    //val out_degrees = Array.empty[Long](n_nodes.to[Int])

    //gather_out_degrees(out_degrees, edges_only)

    //println("n_containers: " + n_containers)
    //println("n_nodes: " + n_nodes)
    //println("n_edges: " + n_edges)


    //val OCnodes = DRAM[X](n_nodes.to[Int] * 2)
    //val OCcontainers = DRAM[Long](n_edge_spaces)
    //val OCout_degrees = DRAM[Long](n_nodes.to[Int])

    //val nodesInit = Array.tabulate(n_nodes.to[Int]) { i => 1.to[X] / n_nodes.to[X] }

     //val dampIN = 0.85f.to[X]

    ////val n_thread = 16
    ////val simd_per_thread = 16
    ////val edges_per_thread = 64

    //val OCNN = ArgIn[Long]
    //val OCNcontainer = ArgIn[Long]
    //val OCthread_iters = ArgIn[Int]
    //val iters = ArgIn[Int]
    //val damp = ArgIn[X]
    //setArg(damp, dampIN)

    ////
    //val ud_start = 0
    //val ud_fringe = 1
    //val ud_write = 2
    //val ud_all = 3

    //setArg(OCNN, n_nodes.to[Long])
    //setArg(OCNcontainer, n_containers.to[Long] )
    //setArg(OCthread_iters, thread_iters.to[Int])
    //setArg(iters, itersIN)

    //setMem(OCnodes, nodesInit)
    //setMem(OCcontainers, edges_only)
    //setMem(OCout_degrees, out_degrees)

    //Accel {

      ////// Global data structure among threads
      ////
      //val n_container_gz:Long = OCNcontainer
      //val noupdate = (1.to[X] - damp) / OCNN.value.to[X]

      //val iter_num = Reg[Bit]
      //iter_num := 1.to[Bit]

      ////// Two stages:
      ////// 1. each thread reads src and aggregating update lists
      ////// 2. Aggregating update list and writing result to Mem

      //Sequential.Foreach(iters by 1) { iter =>

         //val from = mux(iter_num, 0, OCNN)
         //val to = mux(iter_num, OCNN, 0)

        //val dst_prev = Reg[Long](-1)
        //val val_prev = Reg[X](0)

        //Foreach(OCthread_iters by container_batch_size * n_thread) { group_iter =>

          //val thd_updates_dst = List.tabulate(n_thread) {i=> FIFO[Long](container_batch_size)}
          //val thd_updates_val = List.tabulate(n_thread) {i=> FIFO[X](container_batch_size)}

          //val thd_updates_dst_s = List.tabulate(ud_all) { i => List.tabulate(n_thread) { j => FIFO[Long](1) }}
          //val thd_updates_val_s = List.tabulate(ud_all) { i => List.tabulate(n_thread) { j => FIFO[X](1) }}

          //val combine_cur_dst = Reg[Long](-1)
          //val combine_accum = Reg[X](0)

          //val final_writes_dst = FIFO[Long](n_thread)
          //val final_writes_val = FIFO[X](n_thread)

          //List.tabulate(n_thread) { thread =>

            //val my_cur_dst = Reg[Long](-1)
            //val my_accum = Reg[X](0)

            //my_cur_dst.reset()
            //my_accum.reset()

            //if (thread == 0) {
              //my_cur_dst := dst_prev
              //my_accum := val_prev
            //}

          //// Process in batches
          //// Each iteration with all threads running in parallel

            //// Calculating starting edge
            //val container_begin = ((group_iter * n_thread + thread) * container_batch_size) .to[Long]
            //val container_end = min(container_begin + container_batch_size, n_container_gz)
            //val edge_begin = container_begin * edges_per_container

            //val my_thread_num_container = (container_end - container_begin).to[Int]

            //if (my_thread_num_container > 0) {

              //Pipe.Foreach(my_thread_num_container by container_per_compute) { container_begin_idx =>

                //val edge_begin_idx = container_begin_idx * edges_per_container

                //val local_edges_l = SRAM[Long](simd)

                //val num_active_container = min(container_per_compute, my_thread_num_container - container_begin_idx)
                //val num_active_container_edges = num_active_container * edges_per_container

                //val my_simd_begin = (edge_begin + edge_begin_idx).to[Long]
                //val my_simd_end = my_simd_begin + num_active_container_edges

                //local_edges_l load OCcontainers(my_simd_begin.to[Int] :: my_simd_end.to[Int])

                //val cyc_update_dst = SRAM[Long](container_per_compute)
                //val cyc_update_val = SRAM[X](container_per_compute)

               //Foreach (num_active_container by 1 par container_per_compute) { compute_iter =>
                  //val container_begin = compute_iter * edges_per_container

                  //val my_simd_srcs = FIFO[Long](edges_per_container)
                  //val my_simd_srcs_od = FIFO[Long](edges_per_container)

                  //val my_simd_dst = (
                    //((local_edges_l(container_begin + 0) & 0x7fff000000000000l) >> 48) |
                    //((local_edges_l(container_begin + 1) & 0x7fff000000000000l) >> 33) |
                    //((local_edges_l(container_begin + 2) & 0x7fff000000000000l) >> 18) |
                    //((local_edges_l(container_begin + 3) & 0x0007000000000000l) >>  3))

                  ////Pipe.Foreach (edges_per_container by 1 par edges_per_container) { edge_iter =>
                  //Pipe.Foreach (edges_per_container by 1) { edge_iter =>
                    //val valid = (local_edges_l(container_begin + edge_iter) & 0x8000000000000000l).bit(63)

                    //if (valid) {
                      //val src = (local_edges_l(container_begin + edge_iter) & 0x0000ffffffffffffl)
                      //val src_offset= src + from
                      //my_simd_srcs.enq(src_offset)
                      //my_simd_srcs_od.enq(src)
                    //}
                  //}

                  //val local_src_prop = FIFO[X](edges_per_container)
                  //val local_src_out_degrees = FIFO[Long](edges_per_container)

                  //local_src_prop gather OCnodes(my_simd_srcs)
                  //local_src_out_degrees gather OCout_degrees(my_simd_srcs_od)

                 //val partial_pr = Pipe.Reduce(Reg[X](0))(my_simd_srcs.numel by 1 par edges_per_container) { i =>
                    //local_src_prop.deq() / local_src_out_degrees.deq().to[X] * damp
                 //}{_+_}

                 //cyc_update_dst(compute_iter) = my_simd_dst
                 //cyc_update_val(compute_iter) = partial_pr.value
               //}


               //// Combining results and write update to local structure
               //Foreach (num_active_container by 1) { c =>
                 //val container_dst = cyc_update_dst(c)
                 //val container_val = cyc_update_val(c)

                 //if (my_cur_dst != container_dst && my_cur_dst != -1) {
                   //if (thd_updates_dst_s(ud_start)(thread).isEmpty) {
                     //thd_updates_dst_s(ud_start)(thread).enq(container_dst)
                     //thd_updates_val_s(ud_start)(thread).enq(my_accum)
                   //} else {
                     //my_accum := my_accum + noupdate
                     //thd_updates_dst(thread).enq(container_dst)
                     //thd_updates_val(thread).enq(my_accum)
                     //////If it is not the first and last, we can safely add first term
                   //}
                   //my_accum := container_val
                 //} else {
                   //my_accum := my_accum + container_val
                 //}
                 //my_cur_dst := container_dst
               //}
              //}

              //// Completing fringe
              //thd_updates_dst_s(ud_fringe)(thread).enq(my_cur_dst.value)
              //thd_updates_val_s(ud_fringe)(thread).enq(my_accum.value)
            //}
          //}

          ////Store per thread updates
          //List.tabulate(n_thread) { thread =>

            //val prev_thd:scala.Int = (thread + n_thread - 1) % n_thread
            //val last_element_prev_thd_dst:Long = thd_updates_dst_s(ud_fringe)(thread).deq()
            //val last_element_prev_thd_val = thd_updates_val_s(ud_fringe)(prev_thd).deq()

            //if (thd_updates_dst_s(ud_start)(thread).isAlmostEmpty){
              ////// If this thread has at least 2 updates to be written

              //////Never edit the last element of thread of current thread
              //////Only edit the last element of previous thread (merging with first if needed)

              //val first_element_dst:Long = thd_updates_dst_s(ud_start)(thread).deq()
              //val first_element_val = thd_updates_val_s(ud_start)(thread).deq()

              //if (first_element_dst == last_element_prev_thd_dst) {
                //// Merging
                //thd_updates_val_s(ud_write)(prev_thd).enq(last_element_prev_thd_val + first_element_val)
                //thd_updates_dst_s(ud_write)(prev_thd).enq(first_element_dst)

              //} else {
                ////////// To be stored
                ////thd_updates_dst(thread).enq(first_element_dst)
                ////thd_updates_val(thread).enq(first_element_val + noupdate)
                //val val_to_store = SRAM[X](1)
                //val_to_store(0) = (first_element_val + noupdate)
                //OCnodes(first_element_dst :: first_element_dst + 1) store val_to_store
              //}

              //OCnodes(thd_updates_dst(thread)) scatter thd_updates_val(thread)
            //}
            ////else
             ////If this thread has only one update
             ////Can't really store anything, need to post-process left over from each thread
          //}

          //val my_write_almost_emptys = thd_updates_dst_s(ud_write).map(_.isAlmostEmpty)

          ////Combine cross-thread updates
          //Foreach(n_thread - 1 by 1) { thread =>

            //val sels = List.tabulate(n_thread){j => (thread == j)}

            //val my_write_almost_empty = oneHotMux(sels, my_write_almost_emptys)

            //if (my_write_almost_empty) {

              //val f_dst = thd_updates_dst_s(ud_write).zipWithIndex.map{ case (f, idx) => {if (idx == thread) f.deq() else 0L }}.reduce{_ | _}
              //val f_val = thd_updates_val_s(ud_write).zipWithIndex.map{ case (f, idx) => {if (idx == thread) f.deq() else 0L }}.reduce{_ | _}

              //if (f_dst != combine_cur_dst.value && combine_cur_dst.value != -1) {

                //combine_accum := combine_accum + noupdate
                //final_writes_dst.enq(combine_cur_dst.value)
                //final_writes_val.enq(combine_accum)

                //combine_accum := f_val
              //} else {
                //combine_accum := combine_accum + f_val
              //}
              //combine_cur_dst := f_dst
            //}
          //}

           ////Transfer final
          //dst_prev := combine_cur_dst.value
          //val_prev := combine_accum.value

          ////Writing out combined results
          //OCnodes(final_writes_dst) scatter final_writes_val
        //}
      //}
    //}

    //closeBinary(f)
  //}
//}
