import spatial.dsl._

@spatial 
trait RNNHelper {
  type sInt = scala.Int
  type s = scala.Int
  type sDouble = scala.Double
  type T = Float
  type F = Float

  type shiftSigType = FixPt[TRUE, _8, _0]
  type shiftTanhType = FixPt[TRUE, _8, _0]

  def getLUT1D (
    dim0: sInt
  //): LUT1[T] = LUT[T](dim0)(Seq.fill(dim0){ 0.to[T] }:_*)
  ): LUT1[T] = LUT[T](1)(Seq.fill(dim0){ 0.to[T] }:_*)

  def getLUT2D(
    dim0: sInt,
    dim1: sInt
  ): LUT2[T] = LUT[T] (
    dim0,
    dim1
  //)(Seq.fill(dim0 * dim1){ 0.to[T] }:_*)
  )(Seq.fill(1){ 0.to[T] }:_*)

  val lutHigh = 1
  val lutLow = -1

  def lutWrapper (
    in: T,
    lutFn: T => T,
    lo: T = -1,
    hi: T = 1
  ): T = {
    val lutVal = lutFn(in)
    val isHigh = in > lutHigh.to[T]
    val isLow = in < lutLow.to[T]
    mux(isHigh, hi, mux(isLow, lo, lutVal))
  }

  def lutTemplate(in: T): T = {
    val bias = (-8).to[T]
    val nEles = 1024
    val scale = 64.to[T]
    val lut = getLUT1D(nEles)// LUT.fromFile[T](nEles)(srcPath)
    val index = ((in - bias) * scale).to[Int]
    val re = lut(index)
    re
  }

  def tanhLUT(in: T): T = {
    lutTemplate(in)
  }

  def sigLUT(in: T): T = {
    lutTemplate(in)
  }

  val sigWrapper = (in: T) => {
    val result = lutWrapper (
      in,
      sigLUT _,
      0,
      1
    )

    result
  }

  val tanhWrapper = (in: T) => {
    val result = lutWrapper (
      in,
      tanhLUT _,
      -1,
      1
    )

    result
  }
}

