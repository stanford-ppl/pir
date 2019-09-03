//import spatial.dsl._
//import forge.tags._

//@spatial class SplitStream2 extends SpatialTest {

  //// PIR arguments goes here
  //override protected def pirArgs = super.pirArgs :+ "--count=stream_in,2046"

  //object NetStreamBus extends spatial.lang.Bus {
    //@rig def nbits: scala.Int = 32
  //}

  //def newLUT[T:Bits](dim1:scala.Int)(path:java.lang.String) = {
    ////LUT.fromFile[T](dim1)(path)
    //LUT[T](dim1)(Seq.empty:_*)
  //}
  //def newLUT[T:Bits](dim1:scala.Int, dim2:scala.Int)(path:java.lang.String) = {
    ////LUT.fromFile[T](dim1,dim2)(path)
    //LUT[T](dim1,dim2)(Seq.empty:_*)
  //}

  //def main(args: Array[String]): Unit = {

		//val project_dir = s"${sys.env("SPATIAL_HOME")}/apps/src/microbenchmarks/SPLIT-NET_LUTs/"
		
		//val input_file = project_dir + "INPUT_LUT.csv"
		
		//val L1_neuron_weight_file = project_dir + "L1_NEURON_W_LUT.csv"
		//val L1_neuron_bias_file = project_dir + "L1_NEURON_B_LUT.csv"

		//val L2_weight_file = project_dir + "L2_NEURON_W_LUT.csv"
		//val L2_bias_file = project_dir + "L2_NEURON_B_LUT.csv"

		//val L3_softmax_weight_file = project_dir + "L3_SOFTMAX_W_LUT.csv"
		//val L3_softmax_bias_file = project_dir + "L3_SOFTMAX_B_LUT.csv"
		
		/* 
		 * First Layer parameters
		 */

		//// First layer neuron paramters
		//val input_size = 2
		//val num_l1_neurons = 64
		//val l1_neuron_size = input_size


		/*
		 * Second Layer Parameters
		 */

		//// Second Layer neuron paramters
		//val num_l2_neurons = 32
		//val l2_neuron_size = num_l1_neurons



		/*
		 * Third Layer Parameters
		 */

		//val num_l3_neurons = 4
		//val l3_neuron_size = num_l2_neurons


		//val stream_count=1l
		//val stream_in  = StreamIn[Float](NetStreamBus); //countOf(stream_in) = stream_count
		//val stream_out = StreamOut[Float](NetStreamBus)


    //val inner_par=16
    ////val outer_par=32
    ////val l1_outer_par=outer_par
    ////val l2_outer_par=scala.math.max(l1_outer_par/2, 1)
    ////val l2_mid_par = 4
    //////val l2_outer_par=32
    ////val l3_outer_par=scala.math.max(l2_outer_par/2, 1)
    //////val l3_outer_par=32
    ////val l3_mid_par = 2

    //val outer_par=2
    //val l1_outer_par=outer_par
    //val l2_outer_par=scala.math.max(l1_outer_par/2, 1)
    //val l2_mid_par = 1
    ////val l2_outer_par=32
    //val l3_outer_par=scala.math.max(l2_outer_par/2, 1)
    ////val l3_outer_par=32
    //val l3_mid_par = 1

    ////val l1_unroll_par=scala.math.max(input_size/inner_par, 1)
    ////val l1_inner_loop_it=scala.math.min(input_size, inner_par)

    ////val l2_unroll_par=scala.math.max(num_l1_neurons/inner_par, 1)
    ////val l2_inner_loop_it=scala.math.min(num_l1_neurons, inner_par)

    ////val l3_unroll_par=scala.math.max(num_l2_neurons/inner_par, 1)
    ////val l3_inner_loop_it=scala.math.min(num_l2_neurons, inner_par)
      
    ////val x = ArgIn[Float]
    ////val out = ArgOut[Float]
    ////setArg(x, X)


		//Accel(*) {
			
			//val L1_res = SRAM[Float](num_l1_neurons)
			//val L2_res = RegFile[Float](num_l2_neurons)
			//val L3_res = RegFile[Float](num_l3_neurons)

			//val input = newLUT[Float](input_size)(input_file)

			//val L1_W_LUT = newLUT[Float](num_l1_neurons, l1_neuron_size)(L1_neuron_weight_file)
      //val L1_B_LUT = newLUT[Float](num_l1_neurons)(L1_neuron_bias_file)
            
			
			//val L2_W_LUT = newLUT[Float](num_l2_neurons, l2_neuron_size)(L2_weight_file)
      //val L2_B_LUT = newLUT[Float](num_l2_neurons)(L2_bias_file)
		
			
			//val L3_W_LUT = newLUT[Float](num_l3_neurons, l3_neuron_size)(L3_softmax_weight_file)
			//val L3_B_LUT = newLUT[Float](num_l3_neurons)(L3_softmax_bias_file)

			//val s_reg = Reg[Float](0)

      //def untiledLayer(outputSize:scala.Int, inputSize:scala.Int, op:scala.Int, ip:scala.Int, input:LUT1[Float], weight:LUT2[Float], bias:LUT1[Float]) = {
        //val output = SRAM[Float](outputSize)
        //Foreach(0 until outputSize par op){ i => 
          //val w = Reg[Float]
          //w := Reduce(Reg[Float])(inputSize by 1 par ip){ j =>
            //weight(i,j) * input(j)
          //}{_ + _}
          //output(i) = max(w + bias(i) + s_reg, 0)
        //}
        //output
      //}

      //def tiledLayer(outputSize:scala.Int, inputSize:scala.Int, op:scala.Int, mp:scala.Int, ip:scala.Int, input:SRAM1[Float], weight:LUT2[Float], bias:LUT1[Float]) = {
        //val output = SRAM[Float](outputSize)
        //Foreach(0 until outputSize par op){ i => 
          //val w = Reg[Float]
          //w := Reduce(Reg[Float])(inputSize by ip par mp){ j =>
            //Reduce(Reg[Float])(ip by 1 par ip){ k =>
              //val jj = j + k
              //weight(i,jj) * input(jj)
            //} { _ + _ }
          //}{_ + _}
          //output(i) = max(w + bias(i), 0)
        //}
        //output
      //}

      //Pipe {
        //s_reg := stream_in.value

        //val out1 = untiledLayer(
          //num_l1_neurons, 
          //l1_neuron_size, 
          //l1_outer_par, 
          //inner_par, 
          //input, 
          //L1_W_LUT, 
          //L1_B_LUT
        //)
        //val out2 = tiledLayer(
          //num_l2_neurons, 
          //num_l1_neurons, 
          //l2_outer_par, 
          //l2_mid_par, 
          //inner_par, 
          //out1, 
          //L2_W_LUT, 
          //L2_B_LUT
        //)
        //val out3 = tiledLayer(
          //num_l3_neurons, 
          //num_l2_neurons, 
          //l3_outer_par, 
          //l3_mid_par, 
          //inner_par, 
          //out2, 
          //L3_W_LUT, 
          //L3_B_LUT
        //)
        //Pipe {
          //val w = Reg[Float]
          //w := Reduce(Reg[Float](0.to[Float]))(num_l3_neurons by 1 par inner_par){ i=>
            //out3(i)	
          //}{ _ + _ }
          //stream_out := w
        //}
      //}

      ////Pipe {
        ////s_reg := stream_in.value

        ///*
        //Foreach(0 until num_l1_neurons par l1_outer_par){ i => 

          //val w = Reg[Float]

          //w := Reduce(Reg[Float](0.to[Float]))(l1_neuron_size by 1 par inner_par){ j =>
            //L1_W_LUT(i,j).to[Float] * input(j)
          //}{_ + _}

          //L1_res(i) = max(w + L1_B_LUT(i) + s_reg, 0)
        //}
        //*/

      ////}


      ////Pipe {

        /////*
        ////Foreach(0 until num_l2_neurons par l2_outer_par){ i =>

          ////val w = Reg[Float]

          ////w := Reduce(Reg[Float](0.to[Float]))(l2_neuron_size by 1 par inner_par){ j =>
            ////L2_W_LUT(i,j).to[Float] * L1_res(j)
          ////}{_ + _}

          ////L2_res(i) = max(w + L2_B_LUT(i), 0)
        ////}
        ////*/



       ////Foreach(0 until num_l2_neurons par l2_outer_par){ i => 


         ////val neuron = Reg[Float]
         ////neuron := Reduce(Reg[Float](0.to[Float]))(l2_unroll_par by 1 par l2_unroll_par){ j =>

           ////val w = Reg[Float]

           ////w := Reduce(Reg[Float](0.to[Float]))((l2_inner_loop_it) by 1 par inner_par){ k =>
             ////val jj = j*l2_inner_loop_it + k
             ////L2_W_LUT(i,jj).to[Float] * L1_res(jj)
             //////L2_W_LUT(i,k).to[Float] * L1_res(k)
           ////}{_ + _}

           ////w


         ////}{_ + _}

         ////L2_res(i) = max(neuron + L2_B_LUT(i), 0)
         //////L2_res(i) = max(neuron + s_reg, 0)
       ////}
      ////}


      ////Pipe {

        ////Foreach(0 until num_l3_neurons par l3_outer_par){ i => 


          ////val neuron = Reg[Float]
          ////neuron := Reduce(Reg[Float](0.to[Float]))(l3_unroll_par by 1 par l3_unroll_par){ j =>

            ////val w = Reg[Float]

            ////w := Reduce(Reg[Float](0.to[Float]))((l3_inner_loop_it) by 1 par inner_par){ k =>
              ////val jj = j*l3_inner_loop_it + k
              ////L3_W_LUT(i,jj).to[Float] * L2_res(jj)
              //////L3_W_LUT(i, k) * L2_res(k)
            ////}{_ + _}

            ////w


          ////}{_ + _}

          ////L3_res(i) = max(neuron + L3_B_LUT(i), 0)
        ////}

        /////*
        ////// Layer 2 no activation function
        ////Pipe {
          ////Foreach(0 until num_l3_neurons par l3_outer_par){ i =>

            ////val w = Reg[Float]

            ////w := Reduce(Reg[Float](0.to[Float]))(l3_neuron_size by 1 par inner_par){ j =>
              ////L3_W_LUT(i, j) * L2_res(j)
            ////}{_+_}

            ////L3_res(i) = w + L3_B_LUT(i)

          ////}


        ////}
        ////*/

       ////// Hard max
       ////Pipe {

         ////val w = Reg[Float]

         ////w := Reduce(Reg[Float](0.to[Float]))(num_l3_neurons by 1 par inner_par){ i=>
           ////L3_res(i)	
         ////}{ _ + _ }

         ////stream_out := w
       ////}
      ////}

    //}
    //assert(true)
  //}

//}

