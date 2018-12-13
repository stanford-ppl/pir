package FullyConnected

import argon.uconst
import emul.FixedPoint
import spatial.libdsl._

sealed trait ActivationType
object RELU extends ActivationType
object Sigmoid extends ActivationType

class LayerForward[IntBits <: INT[_], MantissaBits <: INT[_], Sign <: BOOL[_]]
(val dims_in: Int, val dims_out: Int)(implicit state: argon.State) {
  type T = FixPt[Sign, IntBits, MantissaBits]

  def evaluate_linear(input: Seq[T],
               params: ((scala.Int,scala.Int) => T),
               bias:   (scala.Int => T)): Seq[T] = {
    val output_data = Seq.tabulate(dims_out) {i => 
      val weights = Seq.tabulate(dims_in){j => params(i,j)}
      val wx = input.zip(weights).map{case (a,w) => a*w}.reduceTree {_+_}
      wx + bias(i)
    }
    output_data
  }

  def evaluate_act(input: Seq[T], fcn: (T => T)): Seq[T] = {
    Seq.tabulate(dims_out) {i => fcn(input(i))}
  }

}

protected object Defs {
  val layers_dims = List(10,2,1)//List(12288, 20, 7, 5, 1)

  def choose_act(layer: Int, max: Int, relu: (T => T), sigmoid: (T => T) ): (T => T) = if (layer < max-1) relu else sigmoid
  def choose_inp(layer: Int, in1: (scala.Int => T), in2: (scala.Int => T) ): (scala.Int => T) = if (layer == 0) in1 else in2
  def choose_store(layer: Int, max: Int, func: ( () => Unit)): ( () => Unit) = { if (layer == max) func else {() => ()} }

  type intBits = _8
  val intBits = 8
  type mantissaBits = _56
  val mantissaBits = 56


  val totalBits = intBits + mantissaBits

  type T = FixPt[TRUE, intBits, mantissaBits]
}

@spatial object FCNN extends SpatialApp {
  import Defs._
  import spatial.dsl._

  def linear(x: Seq[T]): Seq[T] = x
  def relu(x: Seq[T]): Seq[T] = x.map{y => mux(y < 0, 0, y)}
  def sigmoid(x: Seq[T]): Seq[T] = {
    val numPoints = 256
    val stepSize = 0.1
    val data = List.tabulate[T](numPoints){i => 1/(1+scala.math.exp(-i*stepSize))}
    val sigLUT = LUT[T](numPoints)(data:_*)
    x.map{y => 
      val bin = (abs(y) / stepSize.to[T]).to[Int]
      val result = sigLUT(mux(bin > numPoints-1, numPoints-1, bin))
      mux(y < 0, {1-result}, result)
    }
  }
  def hard_sigmoid(x: Seq[T]): Seq[T] = {
    x.map{y => mux(y < -2.5.to[T], 0, mux(y > 2.5.to[T], 1, 0.2.to[T] * y + 0.5.to[T]))}
  }

  def linear_act(inputs: Seq[T], weights: Seq[Seq[T]], biases: Seq[T]): Seq[T] = {
    weights.zip(biases).map{case(w,b) => inputs.zip(w).map{case(a,b) => a*b}.reduceTree{_+_} + b}
  }

  def main(args: Array[String]): Unit = {
    val layers_dims = loadCSVNow[scala.Int](s"${System.getProperty("user.dir")}/tf-src/dnn-nids/DNN-NIDS_LUTs/DIMS.csv", ","){_.toInt}
    val input = loadCSV2D[T](s"${System.getProperty("user.dir")}/tf-src/dnn-nids/DNN-NIDS_LUTs/INPUT_LUT.csv")
    val total_samples = input.rows // Transposed so single image is along leading dimension in memory
    val parameters = Seq.tabulate(layers_dims.size - 1){i => 
      loadCSV2D[T](s"${System.getProperty("user.dir")}/tf-src/dnn-nids/DNN-NIDS_LUTs/L${i+1}_NEURON_W_LUT.csv")
    }
    val biases = Seq.tabulate(layers_dims.size - 1){i => 
      loadCSV1D[T](s"${System.getProperty("user.dir")}/tf-src/dnn-nids/DNN-NIDS_LUTs/L${i+1}_NEURON_B_LUT.csv")
    }

    val input_DRAM = DRAM[T](input.rows, input.cols)
    val parameter_DRAMs = Seq.tabulate(layers_dims.size - 1){i => DRAM[T](parameters(i).rows, parameters(i).cols)}
    val bias_DRAMs = Seq.tabulate(layers_dims.size - 1){i => DRAM[T](biases(i).length)}

    setMem(input_DRAM, input)
    parameter_DRAMs.zip(parameters).foreach{case (dram, p) => setMem(dram,p)}
    bias_DRAMs.zip(biases).foreach{case (dram, p) => setMem(dram,p)}

    val output_DRAM = DRAM[T](total_samples,layers_dims.last)

    val points = ArgIn[Int]
    setArg(points, total_samples)

    Accel {
      val param_srams = Seq.tabulate(layers_dims.size-1){i => SRAM[T](I32(layers_dims(i+1)), I32(layers_dims(i))).nobank}
      val bias_srams = Seq.tabulate(layers_dims.size-1){i => SRAM[T](I32(layers_dims(i+1))).nobank}
      Parallel {
        param_srams.zip(parameter_DRAMs).foreach{case (sram, dram) => sram load dram}
        bias_srams.zip(bias_DRAMs).foreach{case (sram, dram) => sram load dram}
      }

      Foreach (points par 1) { p => 
        val input_sram = SRAM[T](I32(layers_dims.head)).nobank
        val output_sram = SRAM[T](I32(layers_dims.last)).nobank
        input_sram load input_DRAM(p,0 :: layers_dims.head par I32(512/totalBits))
        Pipe{
          val Z1 = linear_act(
            Seq.tabulate(layers_dims.head){x => input_sram(I32(x))},
            Seq.tabulate(layers_dims(1)){i => Seq.tabulate(layers_dims.head){j => param_srams.head.apply(i,j)}},
            Seq.tabulate(layers_dims(1)){i => bias_srams.head.apply(i)}
          )
          val A1 = sigmoid(Z1)

          val Z2 = linear_act(
            A1,
            Seq.tabulate(layers_dims(2)){i => Seq.tabulate(layers_dims(1)){j => param_srams(1).apply(i,j)}},
            Seq.tabulate(layers_dims(2)){i => bias_srams(1).apply(i)}
          )
          val A2 = relu(Z2)
          
          A2.zipWithIndex.foreach{case(a,i) => output_sram(i) = a}
        }

        output_DRAM(p,0::layers_dims.last par I32(512/totalBits)) store output_sram
      }
    }

    printMatrix(getMatrix(output_DRAM), "output")
    // val gold = loadCSV2D[T](s"${System.getProperty("user.dir")}/test_parameters/fcnn/output.csv")
    // printMatrix(gold, "Wanted")
  }
}

