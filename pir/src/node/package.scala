package pir

import pirc._
import pirc.util._
import scala.reflect._

package object node {
  def isRemoteMem(n:Memory) = n match {
    case (_:SRAM | _:StreamIn | _:StreamOut)  => true
    case n:FIFO if n.writers.size > 1 => true
    case n:RegFile => true
    case _ => false
  }

  def isInnerAccum(n:Memory, logger:Option[Logging]=None)(implicit design:PIR) = dbgblk(logger, s"isInnerAccum($n)"){
    import design.pirmeta._
    // Memory with no reader/writer is not a innerAccum
    val readCtrls = n.readers.map { a => 
      val cu = a.ancestors.collect { case cu:GlobalContainer => cu }.head
      (ctrlOf(a), cu)
    }.toSet
    dbg(logger, s"readCtrls:${readCtrls}")
    val writeCtrls = n.writers.map { a => 
      val cu = a.ancestors.collect { case cu:GlobalContainer => cu }.head
      (ctrlOf(a), cu)
    }.toSet
    dbg(logger, s"writeCtrls:${writeCtrls}")
    (readCtrls.size==1 && writeCtrls.size==1 && readCtrls == writeCtrls)
  }

  def withinGlobal(n:PIRNode) = within[GlobalContainer](n)

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

}
