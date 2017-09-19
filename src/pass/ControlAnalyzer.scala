package pir.pass
import pir.node._
import pir._
import pir.util._
import pirc.exceptions._
import pirc.util._
import pirc.enums._
import pir.codegen.Logger

import scala.collection.mutable._

class ControlAnalyzer(implicit design: PIR) extends Pass with Logger {
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
  def findDescendent(ctrler:Controller) = emitBlock(s"findDescendent($ctrler)") {
    val list = ListBuffer[Controller]()
    val queue = Queue[Controller]()
    queue.enqueue(ctrler)
    while (queue.nonEmpty) {
      val curr = queue.dequeue
      queue ++= (curr.children)
      list += curr
    }
    descendentsOf(ctrler) = list.toList
    dprintln(descendentsOf.info(ctrler))
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

  def setHead(ctrler:Controller) = emitBlock(s"setHead: $ctrler") {
    isHead(ctrler) = ctrler match {
      case ctrler:MemoryPipeline => false
      case ctrler if isPipelining(ctrler) =>
        ctrler.trueConsumed.isEmpty
      case ctrler:ComputeUnit if isStreaming(ctrler) =>
        val descendents = ctrler.descendents.collect { case cu:ComputeUnit => cu }
        val fifos = descendents.flatMap { _.fifos.filter {
          _.writers.forall { writer =>
              writer match {
                case writer:ComputeUnit => writer.ancestors.contains(ctrler.parent)
                case top:Top => false
              }
            }
          }
        }
        dprintln(s"setHead: $ctrler descendents:[${descendents.mkString(",")}] fifos:[${fifos.mkString(",")}]")
        fifos.isEmpty
    }
    dprintln(isHead.info(ctrler))
  }

  def setLast(ctrler:Controller) = emitBlock(s"setLast: $ctrler") {
    isLast(ctrler) = ctrler match {
      case ctrler:MemoryPipeline => false
      case ctrler if isPipelining(ctrler) =>
        dprintln(s"trueProduced:[${ctrler.trueProduced.mkString(",")}]")
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
        dprintln(s"descendents:[${descendents.mkString(",")}] fifos:[${fifos.mkString(",")}]")
        fifos.isEmpty
    }
    dprintln(isLast.info(ctrler))
  }

  def setLength(ctrler:Controller) = emitBlock(s"setLength($ctrler)") {
    var count = 1
    var heads:List[Controller] = ctrler.children.filter{child => !isHead(child) }
    while(heads.size!=0) {
      // Collect consumers that are not Top
      heads = heads.flatMap { _.trueProduced.map { _.consumer } }.toSet.toList
      count +=1
    }
    lengthOf(ctrler) = count
    dprintln(lengthOf.info(ctrler))
  }

  def setDAG(ctrler:Controller) = emitBlock(s"setDAG($ctrler)"){
    ctrler match {
      case mc:MemoryController if mc.mctpe.isDense =>
        dagOf(mc) = mc.getFifo("offset").writers.head.ctrler
        dprintln(dagOf.info(ctrler))
      case mc:MemoryController if mc.mctpe.isSparse =>
        dagOf(mc) = mc.getFifo("addr").writers.head.ctrler
        dprintln(dagOf.info(ctrler))
      case mc =>
    }
  }

  def setSAG(ctrler:Controller) = emitBlock(s"setSAG($ctrler)"){
    ctrler match {
      case mc:MemoryController if mc.mctpe==TileLoad || mc.mctpe==Gather =>
        def writtenCtrler(cu:ComputeUnit) = {
          val cls = cu.writtenFIFOs.map { _.ctrler }.toSet
          dprintln(s"writtenCtrler: $cls")
          cls
        }
        mc.parent.children.filter { cu => 
          cu != mc && (writtenCtrler(mc) == writtenCtrler(cu))
        }.foreach { cu => sagOf(mc) = cu }
        dprintln(sagOf.info(ctrler))
      case mc =>
    }
  }

  def setStyle = {
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

  def setLocalCChain = {
    design.top.compUnits.foreach { implicit compUnit:ComputeUnit =>
      compUnit match {
        case cu:MemoryPipeline =>
        case cu:MemoryController =>
        case cu:Pipeline if isStreaming(cu) =>
          localCChainOf(cu) = sortCChains(cu.cchains).headOption.getOrElse(CounterChain.dummy)
        case cu =>
          val locals = cu.cchains.filter{_.isLocal}
          assert(locals.size<=1, 
            s"Currently assume each ComputeUnit only have a single local Counterchain ${cu} [${locals.mkString(",")}]")
          localCChainOf(cu) = (locals ++ sortCChains(cu.cchains)).headOption.getOrElse(CounterChain.dummy)
      }
    }
  }

  addPass(canRun=true, runCount=1) {
    setStyle
    setLocalCChain
  }

  addPass(canRun=(!design.fusionTransform.shouldRun || design.fusionTransform.hasRun), runCount=1) {
    setStyle
  }

  addPass(canRun=design.multiBufferAnalyzer.hasRun, runCount=1) {
    assert(design.multiBufferAnalyzer.hasRun)
    design.top.ctrlers.foreach { ctrler =>
      setHead(ctrler)
      setLast(ctrler)
    }
  }

  addPass(canRun=(!Config.debug || design.pirDataDotGen4.hasRun) && this.hasRun(2), runCount=1) {
    design.top.ctrlers.foreach { ctrler =>
      if (Config.ctrl) setLength(ctrler) //TODO: fix this
      setDAG(ctrler)
      setSAG(ctrler)
    }
  }

}
