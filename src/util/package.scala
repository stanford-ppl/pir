package pir

import pir.graph._
import mapper._
import pir.codegen.{Printer, Logger}
import scala.language.implicitConversions
import pir.exceptions._
import java.lang.Thread
import scala.collection.mutable.ListBuffer

package object util {
  implicit def pr_to_inport(pr:PipeReg):InPort = pr.in
  implicit def pr_to_outport(pr:PipeReg):OutPort = pr.out
  implicit def sram_to_outport(sram:SRAM):OutPort = sram.readPort
  implicit def ctr_to_port(ctr:Counter):OutPort = ctr.out
  implicit def const_to_port(const:Const[_<:AnyVal]):OutPort = const.out
  implicit def mExcep_to_string(e:MappingException[_]):String = e.toString
  implicit def range_to_bound(r:Range)(implicit design:Design) = r by Const(1) 
  implicit def sRange_to_bound(r:scala.collection.immutable.Range)(implicit design:Design): (OutPort, OutPort, OutPort) =
    (Const(r.min.toInt).out, Const(r.max.toInt+1).out, Const(r.step.toInt).out)

  def quote(n:Node)(implicit design:Design) = {
    val pirmeta: PIRMetadata = design
    import pirmeta._
    n match {
      case n => indexOf.get(n).fold(s"$n"){ i =>s"$n[$i]"}
    }
  }

  def topoSort(ctrler:Controller):List[Controller] = {
    val list = ListBuffer[Controller]()
    def isDepFree(cl:Controller) = {
      cl.isHead || cl.trueConsumed.forall { csm => list.contains(csm.producer) }
    }
    def addCtrler(cl:Controller):Unit = {
      list += cl
      var children = cl.children
      while (!children.isEmpty) {
        children = children.filter { child =>
          if (isDepFree(child)) {
            addCtrler(child)
            false
          } else { true }
        }
      }
    }
    addCtrler(ctrler)
    list.toList.reverse
  }

  def fillChain(cu:ComputeUnit, cchains:List[CounterChain])(implicit design:Design) = {
    val pirmeta:PIRMetadata = design 
    import pirmeta._

    val chained = ListBuffer[CounterChain]()
    def prevParent(cc:CounterChain) = {
      val prev = chained.last
      val prevOrig = prev.original
      prevOrig.ctrler.parent match {
        case prevParent:ComputeUnit => prevParent
        case top:Top =>
          var info = s"prev cchain.original=$prevOrig's ctrler=${prevOrig.ctrler}'s parent is Top! curr=${cc} in ${cc.ctrler}" 
          throw new Exception(info)
      }
    }
    cchains.foreach { cc =>
      if (chained.nonEmpty) {
        while (prevParent(cc)!=cc.original.ctrler) {
          val newCC = cu.getCopy(prevParent(cc).localCChain)
          forRead(newCC) = forRead(chained.last)
          forWrite(newCC) = forWrite(chained.last)
          chained += newCC
        }
      }
      chained += cc
    }
    chained.toList
  }

  def sortCChains(cchains:List[CounterChain]) = {
    val ancSize = cchains.map { _.original.ctrler.ancestors.size }
    if (ancSize.size != ancSize.toSet.size) {
      cchains.zip(ancSize).foreach { case (cc,size) =>
        println(s"ctrler:${cc.ctrler} cchain:$cc ${cc.original.ctrler} $size")
      }
      throw new Exception(s"Don't know how to sort!")
    }
    cchains.sortBy { cc => cc.original.ctrler.ancestors.size }.reverse
  }

}

