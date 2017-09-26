package pir.pass

import pir._
import pir.node._
import pir.util._

import pirc._
import pirc.util._
import pirc.enums._

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
    while (curr.parent.nonEmpty) {
      curr = curr.parent.get
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
    isStreaming(ctrler) = ctrler.parent match {
      case Some(parent:StreamController) => true
      case parent => false
    }
  }

  def setPipelining(ctrler:Controller) = {
    isPipelining(ctrler) = ctrler.parent match {
      case Some(parent:StreamController) => false
      case parent => true
    }
  }

  def setHead(ctrler:Controller) = isHead(ctrler) = emitBlock(s"setHead($ctrler)") {
    ctrler.parent match {
      case None => true
      case Some(parent) =>
        descendentsOf(ctrler).forall { descendent =>
          emitBlock(s"descendent=$descendent") {
            descendent.mems.forall { mem => 
              dprintln(s"mem=$mem")
              writersOf(mem).forall { writer =>
                dprintln(s"ancestorsOf(writer=$writer)=${ancestorsOf(writer)}")
                !ancestorsOf(writer).contains(parent) || writer.ctrler.isInstanceOf[Top]
              } 
            }
          }
        }
    }
  }

  def setLast(ctrler:Controller) = isLast(ctrler) = emitBlock(s"setLast($ctrler)") {
    ctrler.parent match {
      case None => true
      case Some(parent) =>
        descendentsOf(ctrler).forall { descendent =>
          emitBlock(s"descendent=$descendent") {
            descendent.writtenMems.forall { mem => 
              dprintln(s"writtenMem=$mem")
              readersOf(mem).forall { reader =>
                dprintln(s"ancestorsOf(reader=$reader)=${ancestorsOf(reader)}")
                !ancestorsOf(reader).contains(parent)
              } 
            }
          }
        }
    }
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
        def writtenCtrler(cu:Controller) = {
          val cls = cu.writtenFIFOs.map { _.ctrler }.toSet
          dprintln(s"writtenCtrler: $cls")
          cls
        }
        mc.parent.get.children.filter { cu => 
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
      if (PIRConfig.ctrl) setLength(ctrler) //TODO: fix this
      setDAG(ctrler)
      setSAG(ctrler)
    }
  }

}
