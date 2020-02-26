import spatial.dsl._
import spatial.lib.ML._
import argon._
import spatial.lang.types._
import spatial.metadata.memory._

@spatial
trait AppUtil extends SpatialTest {

  def newLUT[T:Num](d1:scala.Int) = {
    //LUT.fromSeq[T](d1,d2)(Seq.fill(1)(0.to[T]))
    val sram = SRAM[T](d1)
    sram(0) = 0.to[T]
    sram
  }
  def newLUT[T:Num](d1:scala.Int, d2:scala.Int) = {
    //LUT.fromSeq[T](d1,d2)(Seq.fill(1)(0.to[T]))
    val sram = SRAM[T](d1,d2)
    sram(0,0) = 0.to[T]
    sram
  }
  def newLUT[T:Num](d1:scala.Int, d2:scala.Int, d3:scala.Int) = {
    //LUT.fromSeq[T](d1,d2,d3)(Seq.fill(1)(0.to[T]))
    val sram = SRAM[T](d1,d2,d3)
    sram(0,0,0) = 0.to[T]
    sram
  }
  def newLUT[T:Num](d1:scala.Int, d2:scala.Int, d3:scala.Int, d4:scala.Int) = {
    //LUT.fromSeq[T](d1,d2,d3,d4)(Seq.fill(1)(0.to[T]))
    val sram = SRAM[T](d1,d2,d3,d4)
    sram(0,0,0,0) = 0.to[T]
    sram
  }
}
