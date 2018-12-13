package lattice

import emul.FixedPoint
import spatial.libdsl._
import argon.{Cast, uconst}

class HypercubeLattice[IntBits <: INT[_], MantissaBits <: INT[_], Log2Size <: INT[_], OutputIntBits <: INT[_], Sign <: BOOL[_]]
(val dimensions: Int, val strides: Array[Int])(implicit state: argon.State, pev: argon.Type[FixPt[FALSE, Log2Size, _0]],
                                               tev: argon.Type[FixPt[Sign, IntBits, MantissaBits]],
                                               rev: argon.Type[FixPt[FALSE, _0, MantissaBits]],
                                               oev: argon.Type[FixPt[TRUE, OutputIntBits, MantissaBits]],
                                               c1: Cast[FixPt[FALSE, _0, MantissaBits], FixPt[TRUE, OutputIntBits, MantissaBits]],
                                               c2: Cast[FixPt[FALSE, Log2Size, _0], I32],
                                               c3: Cast[FixPt[Sign, IntBits, MantissaBits], FixPt[FALSE, Log2Size, _0]],
                                               c4: Cast[FixPt[Sign, IntBits, MantissaBits], FixPt[FALSE, _0, MantissaBits]],
                                               c5: Cast[Int, FixPt[FALSE, Log2Size, _0]],
                                               c6: Cast[FixPt[Sign, IntBits, MantissaBits], FixPt[FALSE, _1, MantissaBits]],
                                               c7: Cast[FixPt[FALSE, _0, MantissaBits], FixPt[FALSE, _1, MantissaBits]],
                                               c8: Cast[Int, FixPt[FALSE, _1, MantissaBits]],
                                               c9: Cast[FixPt[FALSE, _1, MantissaBits], FixPt[TRUE, OutputIntBits, MantissaBits]]
) {
  type T = FixPt[Sign, IntBits, MantissaBits]
  type ResidualType = FixPt[FALSE, _0, MantissaBits]
  type AccumResidualType = FixPt[FALSE, _1, MantissaBits]
  type ParameterIndex = FixPt[FALSE, Log2Size, _0]
  type OutputType = FixPt[TRUE, OutputIntBits, MantissaBits]


  def uevaluate(input: (Int => Any), params: (I32 => Any)): OutputType = {
    evaluate(
      input.asInstanceOf[(Int => T)],
      params.asInstanceOf[(I32 => OutputType)]
    )
  }

  def evaluate(input: (scala.Int => T),
               params: (I32 => OutputType)): OutputType = {
    val residualPairs = Seq.tabulate(dimensions) {i =>
      val x = input(i).to[ResidualType]
      Seq(x.to[AccumResidualType], 1.to[AccumResidualType]-x.to[AccumResidualType])
    }
    // Compute all hypervolumes in binary counting order (000, 001, 010, 011, etc..)
    val hypervolumes: Seq[AccumResidualType] = HypercubeLattice.CombinationTree(residualPairs:_*)(_*_)
    // Compute hypercube origin
    val base: Seq[ParameterIndex] = Array.tabulate(dimensions) {x => input(x).to[ParameterIndex]}
    // Get all vertices of hypercube and reverse so that these are opposite the hypervolumes
    val corners: Seq[Seq[scala.Int]] = HypercubeLattice.allCorners(Seq.fill(dimensions)(1)).reverse

    // Get flat index for each (corner + origin)
    val indices: Seq[ParameterIndex] = corners map { c =>
      val corner = (base zip c.map(_.to[ParameterIndex])) map {case (a,b) => a + b}
      (corner zip strides) map { case (cc, stride) =>
        cc * uconst[ParameterIndex](FixedPoint.fromInt(stride))
      } reduce {_+_}
    }

    // Get weighted sum
    hypervolumes.map(_.to[OutputType]).zip(indices).map{case (hv,i) => hv * params(i.to[I32])}.reduceTree {_+_}
  }
}

object HypercubeLattice {
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
}