//import spatial.dsl._
//import forge.tags._

//@spatial class SplitStream extends DSETest {

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

		//val project_dir = "/home/tushar/spatial-lang/apps/src/spatial-network-apps/SPLIT-NET_LUTs/"
		
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


		//val stream_count = 1024l


      ////val x = ArgIn[Float]
        ////val out = ArgOut[Float]
		////setArg(x, X)

		//val stream_in  = StreamIn[Float](NetStreamBus); //countOf(stream_in) = stream_count
		//val stream_out = StreamOut[Float](NetStreamBus)

		//Accel(*) {
			
			//val L1_res = SRAM[Float](num_l1_neurons)
			////val L1_res1 = RegFile[Float](num_l1_neurons)
			////val L1_res2 = RegFile[Float](num_l1_neurons)
			//val L2_res = RegFile[Float](num_l2_neurons)
			//val L3_res = RegFile[Float](num_l3_neurons)

      ////val input = LUT.fromFile[Float](input_size)(input_file)

			////val L1_W_LUT = LUT.fromFile[Float](num_l1_neurons, l1_neuron_size)(L1_neuron_weight_file)
            ////val L1_B_LUT = LUT.fromFile[Float](num_l1_neurons)(L1_neuron_bias_file)
            
			
			////val L2_W_LUT = LUT.fromFile[Float](num_l2_neurons, l2_neuron_size)(L2_weight_file)
            ////val L2_B_LUT = LUT.fromFile[Float](num_l2_neurons)(L2_bias_file)
		
			
			////val L3_SM_W_LUT = LUT.fromFile[Float](num_l3_neurons, l3_neuron_size)(L3_softmax_weight_file)
			////val L3_SM_B_LUT = LUT.fromFile[Float](num_l3_neurons)(L3_softmax_bias_file)
      ////
      //val input = newLUT[Float](input_size)(input_file)

			//val L1_W_LUT = newLUT[Float](num_l1_neurons, l1_neuron_size)(L1_neuron_weight_file)
            //val L1_B_LUT = newLUT[Float](num_l1_neurons)(L1_neuron_bias_file)
            
			
			//val L2_W_LUT = newLUT[Float](num_l2_neurons, l2_neuron_size)(L2_weight_file)
            //val L2_B_LUT = newLUT[Float](num_l2_neurons)(L2_bias_file)
		
			
			//val L3_SM_W_LUT = newLUT[Float](num_l3_neurons, l3_neuron_size)(L3_softmax_weight_file)
			//val L3_SM_B_LUT = newLUT[Float](num_l3_neurons)(L3_softmax_bias_file)

			//val s_reg = Reg[Float](0)

			

			///*
			
			//Pipe {
				//Foreach(0 until L1_res_size){ i=> 
					//print(L1_res(i))
					//print("\n")
				//}
				//out := 2
			//}
			//*/

			//Pipe {
				//s_reg := stream_in.value
			//}

			////Pipe {

				//Foreach(0 until num_l1_neurons par 1){ i => 

					//val w = Reg[Float]

					//w := Reduce(Reg[Float](0.to[Float]))(l1_neuron_size by 1 par 2){ j =>
						//L1_W_LUT(i,j).to[Float] * input(j)
					//}{_ + _}

					//L1_res(i) = max(w + L1_B_LUT(i) + s_reg, 0)
				//}
			////}

			///*
			//Pipe {

				//Foreach(32 until 64 par 16){ i => 

					//val w = Reg[Float]

					//w := Reduce(Reg[Float](0.to[Float]))(l1_neuron_size by l1_neuron_size){ j =>
						//L1_W_LUT(i,j).to[Float] * input(j)
					//}{_ + _}

					//L1_res2(i) = max(w + L1_B_LUT(i) + s_reg, 0)
				//}
			//}*/
			
			///*
			//Pipe {

				//Foreach(0 until 32){ i=>
					//L1_res(i) = L1_res1(i)
				//}
				//Foreach(32 until 64){ i=>
					//L1_res(i) = L1_res2(i)
				//}
			//}
			//*/
			
			//Pipe {

				//Foreach(0 until num_l2_neurons par 4){ i => 

					//val w = Reg[Float]

					//w := Reduce(Reg[Float](0.to[Float]))(l2_neuron_size by 1 par 16){ j =>
						//L2_W_LUT(i,j).to[Float] * L1_res(j)
					//}{_ + _}

					//L2_res(i) = max(w + L2_B_LUT(i), 0)
				//}
			//}


			//Pipe {

				//val numerators = RegFile[Float](num_l3_neurons)
				//val denominator = Reg[Float](0)

				//Foreach(0 until num_l3_neurons by 1 par 1){ i =>
				
					//val wx = Reg[Float]

					//wx := Reduce(Reg[Float](0.to[Float]))(l3_neuron_size by 1 par 16){ j =>
						//L3_SM_W_LUT(i, j) * L2_res(j)
					//}{_ + _}

					//numerators(i) = exp(wx + L3_SM_B_LUT(i))
				
				//}


				//Pipe {
					//denominator := Reduce(Reg[Float](0.to[Float]))(num_l3_neurons by 1 par 1){ i =>
						//numerators(i)
					//}{_ + _}
				//}

				//Pipe {
					
					//Foreach(0 until num_l3_neurons par 1){ i =>
						//L3_res(i) = numerators(i) / denominator
						////print(L3_res(i))
						////print("\n")
					//}

					//stream_out := L3_res(0)
				//}


			//}
			
			
			
			
			
		//}
    //assert(true)
	//}

//}
