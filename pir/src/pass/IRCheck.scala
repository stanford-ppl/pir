package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._


class IRCheck(implicit design:PIR) extends PIRPass {
  import pirmeta._

  type N = Node with Product
  def shouldRun = true

  def warn(s:Any) = {
    dbg(s"$s")
    pirc.util.warn(s)
  }

  def err(s:Any) = {
    dbg(s"$s")
    pirc.util.err(s)
  }

  def assert(predicate:Boolean, info:Any) = {
    dbg(s"$info")
    pirc.util.assert(predicate, info)
  }

  def checkEscapeNode = {
    design.newTop.children.foreach {
      case child:GlobalContainer =>
      case child => err(s"$child is not enclosed by a GlobalContainer!")
    }
  }

  def checkCUIO(cu:GlobalContainer) = {
    cu.ins.foreach { in =>
      in.src match {
        case node:LocalLoad =>
        case node:Memory =>
        case node =>
          dbg(s"$cu's global input $in.src = $node")
          in.connected.foreach { out =>
            dbg(s"out=$out out.src=${out.src}")
          }
          throw PIRException(s"$cu's global output $in.src = $node")
      }
    }
    cu.outs.foreach { out =>
      out.src match {
        case node:LocalStore =>
        case node:Memory =>
        case node =>
          dbg(s"$cu's global output $out.src = $node")
          out.connected.foreach { in =>
            dbg(s"in=$in in.src=${in.src}")
          }
          throw PIRException(s"$cu's global output $out.src = $node")
      }
    }
  }

  def checkMemoryAccess(cu:GlobalContainer) = {
    val mems = collectDown[Memory](cu)
    mems.foreach { mem =>
      mem match {
        case mem:ArgIn =>
        case mem:StreamIn =>
        case mem if mem.writers.isEmpty =>
          warn(s"$mem in $cu does not have writer")
        case _ =>
      }
      mem match {
        case mem:ArgOut =>
        case mem:StreamOut =>
        case mem:StreamIn if mem.field == "ack" =>
        case mem if mem.readers.isEmpty =>
          warn(s"$mem in $cu does not have reader")
        case _ =>
      }
    }
  }

  def checkDefControl(cu:GlobalContainer) = {
    val stageDef = collectDown[StageDef](cu)
    stageDef.foreach { stageDef =>
      assert(ctrlOf.contains(stageDef), s"${qtype(stageDef)} in $cu doesn't have ctrl defined")
      val ctrl = ctrlOf(stageDef)
      assert(ctrl.isInnerControl, s"${qtype(stageDef)}.ctrl.level = ${ctrl.level}. stageDef.ctrl=${ctrl}")
    }
  }

  override def runPass =  {
    checkEscapeNode
    val cus = collectDown[GlobalContainer](design.newTop)
    cus.foreach { cu =>
      checkCUIO(cu)
      checkMemoryAccess(cu)
      checkDefControl(cu)
    }
  }

}

