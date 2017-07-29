package pir

import pir.graph._
import pir.util.misc._
import mapper._
import pir.codegen.{Printer, Logger}
import scala.language.implicitConversions
import pir.exceptions._
import java.lang.Thread
import pir.codegen.Logger
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
    import ctrler.design.pirmeta._
    val list = ListBuffer[Controller]()
    def isAdded(cl:Controller) = list.contains(cl)
    def isDepFree(cl:Controller) = cl match {
      case cl if isTailCollector(cl) => true // list will be reversed
      case cl:MemoryPipeline => isAdded(cl.sram.writer)
      case cl:ComputeUnit if isStreaming(cl) => cl.isHead || cl.fifos.forall { 
        case fifo if fifo.writer.asCU.parent == cl.parent => isAdded(fifo.writer)
        case fifo => true
      } 
      case cl if isPipelining(cl) => cl.isHead || cl.trueConsumed.forall { 
        case csm:SRAM => isAdded(csm.ctrler) && isAdded(csm.producer)
        case csm => isAdded(csm.producer)
      }
    }
    def addCtrler(cl:Controller):Unit = {
      list += cl
      var children = cl.children.reverse
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

  def fillChain(cu:ComputeUnit, cchains:List[CounterChain])(implicit design:Design, logger:Logger) = logger.emitBlock(s"fillChain"){
    val pirmeta:PIRMetadata = design 
    import pirmeta._

    logger.dprintln(s"cu=$cu")
    logger.emitBlock(s"cchains") {
      cchains.foreach { cc => logger.dprintln(s"${cc} original=${cc.original} from ${cc.original.ctrler}") }
    }
    val chained = ListBuffer[CounterChain]()
    def prevParent(cc:CounterChain) = {
      val prev = chained.last
      val prevOrig = prev.original
      prevOrig.ctrler.parent match {
        case prevParent:ComputeUnit => 
          prevParent
        case top:Top =>
          var info = s"fillChain: ${cchains}\n"
          info += s"prev=$prev cchain.original=$prevOrig\n"
          info += s"original.ctrler=${prevOrig.ctrler}'s parent is Top! curr=${cc} in ${cc.ctrler}" 
          throw new Exception(info)
      }
    }
    cchains.foreach { cc =>
      if (chained.nonEmpty) {
        while (prevParent(cc)!=cc.original.ctrler) {
          val pp = prevParent(cc)
          val newCC = cu.getCopy(localCChainOf(pp))
          logger.dprintln(s"cc=$cc prevParent=$pp newCC=$newCC")
          forRead(newCC) = forRead(chained.last)
          forWrite(newCC) = forWrite(chained.last)
          newCC.counters.foreach { ctr => 
            forRead(ctr) = forRead(newCC)
            forWrite(ctr) = forWrite(newCC)
          }
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
        println(s"ctrler:${cc.ctrler} cchain:$cc original:${cc.original} ${cc.original.ctrler} $size")
      }
      throw new Exception(s"Don't know how to sort!")
    }
    cchains.sortBy { cc => cc.original.ctrler.ancestors.size }.reverse
  }

  def pipelinedBy(ctrler:Controller)(implicit design:Design) = {
    import design.pirmeta._
    ctrler match {
      case ctrler:StreamController => -1
      case ctrler:InnerController => -1
      case ctrler:MetaPipeline => 1 
      case ctrler:Sequential => lengthOf(ctrler) 
      case ctrler:Top => 1 
    }
  }

  def toValue(s:String):AnyVal = {
    if (s.contains("true") || s.contains("false")) 
      s.toBoolean
    else if (s.contains("."))
      s.toFloat
    else s.toInt
  }

  //def readParOf(node:Node):Int = {
    //import node.pirmeta._
    //node match {
      //case node:SRAM => readParOf(node.ctrler)
      //case node:MemoryPipeline => readCChainsOf(node).head.inner.par
    //}
  //}

  //def writeParOf(node:Node):Int = {
    //import node.pirmeta._
    //node match {
      //case node:SRAM => writeParOf(node.ctrler)
      //case node:MemoryPipeline => writeCChainsOf(node).head.inner.par
    //}
  //}

  //def parOf(node:Node):Int = {
    //import node.pirmeta._
    //node match {
      //case node:Primitive =>
        //val cu = node.ctrler.asCU
        //if (forRead(node)) readParOf(cu)
        //else if (forWrite(node)) writeParOf(cu)
        //else parOf(cu)
      //case node:MemoryPipeline =>
        //err(s"No par defined for $node")
      //case node:ComputeUnit =>
        //compCChainsOf(node).head.inner.par
    //}
  //}

}

