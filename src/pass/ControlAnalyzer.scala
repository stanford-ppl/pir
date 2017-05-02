package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.util.misc._
import pir.codegen.Logger

import scala.collection.mutable._

class ControlAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = true
  import pirmeta._
  import spademeta._
  override lazy val stream = newStream(s"ControlAnalyzer.log")

  // Including current CU. From current to top
  def findAncestors(ctrler:Controller) = {
    val list = ListBuffer[Controller]()
    var curr:Controller = ctrler
    list += curr
    while (!curr.isInstanceOf[Top]) {
      curr = curr.asInstanceOf[ComputeUnit].parent
      list += curr
    }
    ancestorsOf(ctrler) = list.toList
  }

  // Including current CU. From current to leaf children 
  def findDescendent(ctrler:Controller) = {
    val list = ListBuffer[Controller]()
    val queue = Queue[Controller]()
    queue.enqueue(ctrler)
    while (queue.nonEmpty) {
      val curr = queue.dequeue
      queue ++= (curr.children)
      list += curr
    }
    descendentsOf(ctrler) = list.toList
  }

  def setStreaming(ctrler:Controller) = {
    isStreaming(ctrler) = ctrler match {
      case cu:ComputeUnit =>
        cu.parent match {
          case parent:StreamController => true
          case parent => false
        }
      case top:Top => false
    }
  }

  def setPipelining(ctrler:Controller) = {
    isPipelining(ctrler) = ctrler match {
      case cu:ComputeUnit =>
        cu.parent match {
          case parent:StreamController => false
          case parent:MetaPipeline => true
          case parent:Sequential => true
          case parent:Top => true
        }
      case top:Top => true
    }
  }

  def setHead(ctrler:Controller) = {
    isHead(ctrler) = ctrler match {
      case ctrler:MemoryPipeline => false
      case ctrler if isPipelining(ctrler) =>
        ctrler.trueConsumed.isEmpty
      case ctrler:ComputeUnit if isStreaming(ctrler) =>
        val descendents = ctrler.descendents.collect { case cu:ComputeUnit => cu }
        val fifos = descendents.flatMap { _.fifos.filter {
            _.writer match {
              case writer:ComputeUnit => writer.ancestors.contains(ctrler.parent)
              case top:Top => false
            }
          }
        }
        dprintln(s"setHead: $ctrler descendents:[${descendents.mkString(",")}] fifos:[${fifos.mkString(",")}]")
        fifos.isEmpty
    }
  }

  def setLast(ctrler:Controller) = {
    isLast(ctrler) = ctrler match {
      case ctrler:MemoryPipeline => false
      case ctrler if isPipelining(ctrler) =>
        ctrler.trueProduced.isEmpty
      case ctrler:ComputeUnit if isStreaming(ctrler) =>
        val descendents = ctrler.descendents.collect { case cu:ComputeUnit => cu }
        val fifos = descendents.flatMap { _.writtenFIFOs.filter {
            _.reader match {
              case reader:ComputeUnit => reader.ancestors.contains(ctrler.parent)
              case top:Top => false
            }
          }
        }
        dprintln(s"setLast: $ctrler descendents:[${descendents.mkString(",")}] fifos:[${fifos.mkString(",")}]")
        fifos.isEmpty
    }
  }

  def setLength(ctrler:Controller) = {
    var count = 1
    var heads:List[Controller] = ctrler.children.filter{child => !isHead(child) }
    while(heads.size!=0) {
      // Collect consumers that are not Top
      heads = heads.flatMap { _.trueProduced.map { _.consumer } }.toSet.toList
      count +=1
    }
    lengthOf(ctrler) = count
  }

  def setSCUs(ctrler:Controller) = {
    ctrler match {
      case mc:MemoryController if mc.mctpe.isDense =>
        writesOfs(mc.getFifo("offset").writer.ctrler) = true
      case _ => 
    }
  }

  addPass(true, 1) {
    design.top.ctrlers.foreach { ctrler =>
      findAncestors(ctrler)
      findDescendent(ctrler)
      setStreaming(ctrler)
      setPipelining(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      emitBlock(s"$ctrler") {
        dprintln(s"ancestors = ${ancestorsOf(ctrler)}")
        dprintln(s"descendents = ${descendentsOf(ctrler)}")
        dprintln(s"isStreaming = ${isStreaming(ctrler)}")
        dprintln(s"isPipelining = ${isPipelining(ctrler)}")
      }
    }
  }

  addPass(design.multiBufferAnalyzer.hasRun, 1) {
    assert(design.multiBufferAnalyzer.hasRun)
    design.top.ctrlers.foreach { ctrler =>
      setHead(ctrler)
      setLast(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      setLength(ctrler)
      setSCUs(ctrler)
    }
    design.top.ctrlers.foreach { ctrler =>
      emitBlock(s"$ctrler") {
        dprintln(s"isHead = ${isHead(ctrler)}")
        dprintln(s"isLast = ${isLast(ctrler)}")
        dprintln(s"length = ${lengthOf(ctrler)}")
      }
    }
  }

  addPass(design.pirMapping.succeeded, 1) {
    val mp = design.mapping.get
    design.arch.cus.foreach { pcu =>
      mp.clmap.pmap.get(pcu).foreach {
        case cu:MemoryPipeline => parOf(pcu) = 1
        case cu:OuterController => parOf(pcu) = 1
        case cu:ComputeUnit => parOf(pcu) = cu.localCChain.inner.par
      }
    }
  }
  
  override def traverse = {
    runPasses
  } 

}
