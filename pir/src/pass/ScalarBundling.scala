package pir.pass

import pir._
import pir.node._

import pirc.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack

class ScalarBundling(implicit design: PIR) extends Pass {
  def shouldRun = true 
  import pirmeta._

  lazy val par = design.arch.numLanes

  addPass {
    bundleScalarOuts
    bundleScalarIns
  } 

  // Try to bundle scalar outs that going into the same set of readers into a single
  // VecOut as much as possible
  def bundleScalarOuts = {
    design.top.spadeCtrlers.foreach { cl => bundleScalarOut(cl) }
  }

  def bundleScalarOut(cl:Controller) = {
    val grps = cl.souts.groupBy(_.scalar.readers.map(_.ctrler).toSet).to[Stack]
    val vecs = ListBuffer[DummyVector]()
    val freeVecs = Stack[DummyVector]()
    while (grps.size!=0) {
      val (readers, sos) = grps.pop
      val vec = if (sos.size >= par || freeVecs.isEmpty) {
        val v = new DummyVector(); vecs += v; v
      } else { 
        freeVecs.pop 
      }
      val bundle = if (sos.size > vec.remainSpace) {
        val (b,rest) = sos.splitAt(vec.remainSpace)
        grps.push(readers -> rest)
        b
      } else {
        sos
      }
      bundle.foreach { so => vec.addScalar(so.scalar) }
      val vo = bundle.head.ctrler.newVout(vec)
      bundle.foreach { so => vecOf(so) = vo }
      //println(s"vec:$vec, readers:${readers} bundle:$bundle vec.scalars:${vec.scalars}")
      if (!vec.isFull) freeVecs.push(vec)
    }
    design.top.vectors(design.top.vectors ++ vecs.toList)
  }

  def bundleScalarIns = {
    design.top.spadeCtrlers.foreach { cl => bundleScalarIn(cl) }
  }

  def bundleScalarIn(cl:Controller) = {
    val grps = cl.sins.groupBy { sin => vecOf(sin.scalar.writer) }
    grps.foreach { case (dvout, sins) =>
      val vi = cl.newVin(dvout.vector)
      sins.foreach { sin => vecOf(sin) = vi }
    }
  }

  override def finPass = {
    endInfo("Finishing Scalar Bundling")
  }

}
