package lattice

import argon.uconst
import emul.FixedPoint
import spatial.libdsl._

sealed trait InterpType
object HypercubeInterp extends InterpType
object SimplexInterp extends InterpType

protected object Defs {
  val sizes = Array.fill(5)(2)
  val dimensions = sizes.length
  val strides: Array[Int] = Array.fill(dimensions){1}

  (1 to (dimensions - 1)) foreach {
    d => {
      strides(d) = strides(d-1) * sizes(d-1)
    }
  }

  val size = sizes reduce {_*_}

  type Log2Size = _5 // ceil(log2(size))

  type indexBits = _3

  type intBits = _3
  val intBits = 3
  type mantissaBits = _13
  val mantissaBits = 13


  val totalBits = intBits + mantissaBits

  type T = FixPt[FALSE, intBits, mantissaBits]
  type CornerType = FixPt[FALSE, intBits, _0]
  type ResidualType = FixPt[FALSE, _0, mantissaBits]
  type AccumResidualType = FixPt[FALSE, _1, mantissaBits]

  type IndexType = FixPt[FALSE, indexBits, _0]

  type ParameterIndex = FixPt[FALSE, Log2Size, _0]
  def ParameterIndex(x: Int): ParameterIndex = uconst[ParameterIndex](FixedPoint.fromInt(x))

  type OutputType = FixPt[TRUE, _3, mantissaBits]
}

protected object Utils {
  import Defs._
 
  def allCorners(maxes: Seq[scala.Int], partials: Seq[Seq[scala.Int]] = Seq(Seq.empty)): Seq[Seq[scala.Int]] = maxes match {
    case Nil => Nil
    case h::tail if tail.nonEmpty => (0 to h).flatMap{i => allCorners(tail, partials.map(_ ++ Seq(i)))}
    case h::tail if tail.isEmpty => (0 to h).flatMap{i => partials.map(_ ++ Seq(i))}
  }

  object CombinationTree {

    private def join[T](x1: Seq[T], x2: Seq[T], func: (T,T) => T): Seq[T] = x1.flatMap{a => x2.map{b => func(a,b)}}

    private def combine[T](xs: Seq[Seq[T]], func: (T,T) => T): Seq[Seq[T]] = xs.length match {
      case 0 => throw new Exception("Empty reduction level")
      case 1 => xs
      case len if len % 2 == 0 => combine(List.tabulate(len/2){i => join(xs(2*i), xs(2*i+1), func)}, func)
      case len => combine(List.tabulate(len/2){i => join(xs(2*i), xs(2*i+1), func) } :+ xs.last, func)
    }

    def apply[T](xs: Seq[T]*)(func: (T,T) => T): Seq[T] = combine(xs, func).head
  }


  def evaluate(input: (scala.Int => T), 
               params: (I32 => OutputType), 
               interpolation_type: InterpType
              )(implicit state: argon.State): OutputType = interpolation_type match {
      case SimplexInterp =>
        val simplex = new SimplexLattice[intBits, mantissaBits, Log2Size, intBits, FALSE](dimensions, strides)
        simplex.evaluate(input, params)

      case HypercubeInterp =>
        val hypercube = new HypercubeLattice[intBits, mantissaBits, Log2Size, intBits, FALSE](dimensions, strides)
        hypercube.evaluate(input, params)

  }
}

@spatial object Simplex extends SpatialApp {
  import Defs._
  import spatial.dsl._

  def main(args: Array[String]): Unit = {
    val input = loadCSV1D[T](s"${System.getProperty("user.dir")}/test_parameters/simplex/${dimensions}-${sizes(0)}/input.csv")
    val parameters = loadCSV1D[OutputType](s"${System.getProperty("user.dir")}/test_parameters/simplex/${dimensions}-${sizes(0)}/parameters.csv")

    val input_DRAM = DRAM[T](dimensions)
    val parameter_DRAM = DRAM[OutputType](size)

    setMem(input_DRAM, input)
    setMem(parameter_DRAM, parameters)

    val iterations = ArgIn[Int]
    setArg(iterations, args(0).to[Int])

    val output = ArgOut[OutputType]

    Accel {
      val input_sram = SRAM[T](I32(dimensions)).nobank
      val param_sram = SRAM[OutputType](I32(size)).nobank
      Parallel {
        input_sram load input_DRAM(0 :: dimensions par I32(32))
        param_sram load parameter_DRAM(0 :: size par I32(32))
      }

      Foreach (iterations par 1) {
        _ => {
          output := Utils.evaluate(
            { x: scala.Int => input_sram(I32(x)) },
            { x: I32 => param_sram(x) },
            SimplexInterp
          )
        }
      }
    }

    println(r"Received Output: $output")
    val gold = loadCSV1D[OutputType](s"${System.getProperty("user.dir")}/test_parameters/simplex/${dimensions}-${sizes(0)}/output.csv")
    println(r"Wanted: ${gold(0)}")
  }
}

@spatial object Hypercube extends SpatialApp {
  import Defs._
  import spatial.dsl._

  def main(args: Array[String]): Unit = {
    val input = loadCSV1D[T](s"${System.getProperty("user.dir")}/test_parameters/hypercube/${dimensions}-${sizes(0)}/input.csv")
    val parameters = loadCSV1D[OutputType](s"${System.getProperty("user.dir")}/test_parameters/hypercube/${dimensions}-${sizes(0)}/parameters.csv")

    val input_DRAM = DRAM[T](dimensions)
    val parameter_DRAM = DRAM[OutputType](size)

    setMem(input_DRAM, input)
    setMem(parameter_DRAM, parameters)

    val iterations = ArgIn[Int]
    setArg(iterations, args(0).to[Int])

    val output = ArgOut[OutputType]

    Accel {
      val input_sram = SRAM[T](I32(dimensions)).nobank
      val param_sram = SRAM[OutputType](I32(size)).nobank
      Parallel {
        input_sram load input_DRAM(0 :: dimensions par I32(8))
        param_sram load parameter_DRAM(0 :: size par I32(8))
      }

      Foreach (iterations par 1) {
        _ => {
          output := Utils.evaluate(
            { x: scala.Int => input_sram(I32(x)) },
            { x: I32 => param_sram(x) },
            HypercubeInterp
          )
        }
      }
    }

    println(r"Received Output: $output")
    val gold = loadCSV1D[OutputType](s"${System.getProperty("user.dir")}/test_parameters/hypercube/${dimensions}-${sizes(0)}/output.csv")
    println(r"Wanted: ${gold(0)}")

  }
}
