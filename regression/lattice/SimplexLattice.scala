package lattice

import emul.FixedPoint
import spatial.libdsl._
import argon.{Cast, uconst}

class SimplexLattice[IntBits <: INT[_], MantissaBits <: INT[_], Log2Size <: INT[_], OutputIntBits <: INT[_], Sign <: BOOL[_]]
(val dimensions: Int, val strides: Array[Int])(implicit state: argon.State, pev: argon.Type[FixPt[FALSE, Log2Size, _0]],
                                               tev: argon.Type[FixPt[Sign, IntBits, MantissaBits]],
                                               rev: argon.Type[FixPt[FALSE, _0, MantissaBits]],
                                               oev: argon.Type[FixPt[TRUE, OutputIntBits, MantissaBits]],
                                               c1: Cast[FixPt[FALSE, _0, MantissaBits], FixPt[TRUE, OutputIntBits, MantissaBits]],
                                               c2: Cast[FixPt[FALSE, Log2Size, _0], I32],
                                               c3: Cast[FixPt[Sign, IntBits, MantissaBits], FixPt[FALSE, Log2Size, _0]],
                                               c4: Cast[FixPt[Sign, IntBits, MantissaBits], FixPt[FALSE, _0, MantissaBits]],
                                               c5: Cast[Int, FixPt[FALSE, Log2Size, _0]],
) {

  type T = FixPt[Sign, IntBits, MantissaBits]
  type ResidualType = FixPt[FALSE, _0, MantissaBits]
  type ParameterIndex = FixPt[FALSE, Log2Size, _0]
  type OutputType = FixPt[TRUE, OutputIntBits, MantissaBits]

  def spatial_sort(inputs: (Int => ResidualType)): (Array[ResidualType], Array[ParameterIndex]) = {

    var values: Array[ParameterIndex] = null
    var keys: Array[ResidualType] = null

    SimplexHelper.sort(dimensions).zipWithIndex foreach {
      case (stage, stage_index) => {

        val new_indices = Array.fill[ParameterIndex](dimensions) {
          null
        }
        val new_values = Array.fill[ResidualType](dimensions) {
          null
        }

        SimplexHelper.compute_complement(stage, dimensions) foreach {
          pass => {
            if (stage_index == 0) {
              new_indices(pass) = uconst[ParameterIndex](strides(pass))
              new_values(pass) = inputs(pass)
            } else {
              new_indices(pass) = values(pass)
              new_values(pass) = keys(pass)
            }
          }
        }
        stage foreach {
          pair => {
            val a = if (stage_index == 0) inputs(pair._1) else keys(pair._1)
            val b = if (stage_index == 0) inputs(pair._2) else keys(pair._2)
            val a_ind: ParameterIndex = if (stage_index == 0) uconst[ParameterIndex](strides(pair._1)) else values(pair._1)
            val b_ind: ParameterIndex = if (stage_index == 0) uconst[ParameterIndex](strides(pair._2)) else values(pair._2)

            val cmp = a > b

            new_values(pair._1) = spatial.dsl.mux(cmp, a, b)
            new_values(pair._2) = spatial.dsl.mux(cmp, b, a)
            new_indices(pair._1) = spatial.dsl.mux(cmp, a_ind, b_ind)
            new_indices(pair._2) = spatial.dsl.mux(cmp, b_ind, a_ind)
          }
        }
        keys = new_values
        values = new_indices
      }
    }

    (keys, values)
  }

  def uevaluate(input: (Int => Any), params: (I32 => Any)): OutputType = {
    evaluate(
      input.asInstanceOf[(Int => T)],
      params.asInstanceOf[(I32 => OutputType)]
    )
  }

  def evaluate(input: (scala.Int => T),
               params: (I32 => OutputType)): OutputType = {
    val corners = Array.tabulate(dimensions) {input(_).to[ParameterIndex]}

    // Extends residuals by 1 bit.
    val (sorted_residuals, sorted_strides) = spatial_sort(input(_).to[ResidualType])

    var lower_index = (corners zip strides) map {
      p: (ParameterIndex, Int) =>
        p match {
          case (corner, stride) =>
            corner * stride.to[ParameterIndex]
        }
    } reduce {_ + _}

    // Compute top corner.

    var upper_index = lower_index + strides.sum.to[ParameterIndex]


    // Compute Bidirectional Simplex Interpolation
    val weights = (uconst[ResidualType](FixedPoint.fromInt(0)) :: sorted_residuals.toList) zip
      (sorted_residuals.toList :+ uconst[ResidualType](FixedPoint.fromInt(0))) map {
      case (a, b) => a - b
    }


    val midpoint = weights.size / 2

    // We can start half the interpolation from the bottom corner and the other half from the top corner.

    var total_lower = uconst[OutputType](FixedPoint.fromInt(0))
    var total_upper = uconst[OutputType](FixedPoint.fromInt(0))

    (0 to midpoint - 1) foreach {
      dim =>
        total_lower = total_lower + weights(dim).to[OutputType] * params(lower_index.to[I32])
        lower_index = lower_index + sorted_strides(dim)
    }

    (weights.size - 1 to midpoint by -1) foreach {
      dim =>
        total_upper = total_upper + weights(dim).to[OutputType] * params(upper_index.to[I32])
        upper_index = upper_index - sorted_strides(dim - 1)
    }

    total_lower + total_upper
  }
}
