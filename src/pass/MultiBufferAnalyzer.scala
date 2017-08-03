package pir.pass
import pir.graph._
import pir._
import pir.exceptions._
import pir.codegen.Logger
import pir.util.misc._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue

class MultiBufferAnalyzer(implicit design: Design) extends Pass with Logger {
  import pirmeta._
  def shouldRun = true 

  override lazy val stream = newStream(s"MultiBufferAnalyzer.log")

  def leastCommonAncestor(ctrlers:List[Controller]):Controller = {
    val cas = ctrlers.map { ctrler => ancestorsOf(ctrler) }.reduce { _ intersect _ }
    if (cas.size < 0)
      throw PIRException(s"$ctrlers doesn't share common ancestors")
    cas.head
  }

  def findProducerConsumer(readers:List[Controller], writers:List[Controller], lca:Controller):Option[(Controller, Controller)] = {
    val producers = ancestorsOf(leastCommonAncestor(writers)).intersect(lca.children)
    val consumers = ancestorsOf(leastCommonAncestor(readers)).intersect(lca.children)
    val (producer, consumer) = if (producers.isEmpty || consumers.isEmpty) {
      (lca, lca)
    } else {
      (producers.head, consumers.head)
    }
    if (isStreaming(producer) || isStreaming(consumer)) None
    else Some((producer, consumer))
  }

  def setProducerConsumer(cu:ComputeUnit, buf:MultiBuffer):Unit = emitBlock(s"setProducerConsumer($cu, $buf)"){
    if (buf.producer!=null && buf.consumer!=null && cu.parent!=null) return
    val readers = buf.readers
    val writers = buf.writers
    var lca = leastCommonAncestor(readers ++ writers)
    var pc = findProducerConsumer(readers, writers, lca)
    while (pc.isEmpty) {
      lca = lca.asCU.parent
      pc = findProducerConsumer(readers, writers, lca)
    }
    val (producer, consumer) = pc.get

    buf.producer(producer)
    buf.consumer(consumer) // detect back edge later
    dprintln(s"$cu parent:$lca")
    dprintln(s"$buf writers:${writers.map{writer => s"$writer ${writer.ancestors}"}.mkString(",")}")
    dprintln(s"$buf readers: ${readers.map{reader => s"$reader ${reader.ancestors}"}.mkString(",")}")
    dprintln(s"$buf lca: $lca lca.children:${lca.children}")
    dprintln(s"$buf producer:${buf.producer} consumer:${buf.consumer}")
  }

  def setProducerConsumer:Unit = {
    dprintln(s"Set producer consumer ...")
    design.top.compUnits.foreach { cu =>
      emitBlock(s"$cu") {
        cu.mbuffers.foreach { buf =>
          setProducerConsumer(cu, buf)
        }
      }
    }
  }

  def findCycle(ctrler:Controller):Unit = emitBlock(s"FindCycle $ctrler"){
    val children = ctrler.children.filterNot{ _.isMP }
    val heads = children.filter{_.consumed.isEmpty}
    val visited = ListBuffer[Controller]()
    val toVisit = Queue[Controller]()
    //Hack: if there's a loop without entry point, assume the first ctrler is the entry point in the
    //program order
    toVisit ++= (if (heads.isEmpty && children.nonEmpty) List(children.head) else heads)
    while(toVisit.nonEmpty) {
      val node = toVisit.dequeue
      visited += node
      dprintln(s"Visiting $node produced=${node.produced}")
      node.produced.foreach { mem =>
        emitBlock(s"checking $mem") {
          val consumer = mem.consumer
          if (visited.contains(consumer)) {
            mem.trueDep = false
            dprintln(s"Set $mem.trueDep=false in ${mem.ctrler}")
          } else if (!toVisit.contains(consumer)) {
            toVisit += consumer
            dprintln(s"toVisit += ${consumer}")
          }
        }
      }
    }
    children.foreach(findCycle)
  }

  def findBackEdge:Unit = {
    dprintln(s"Finding back edge in dependencies")
    
    findCycle(design.top)
  }

  def setBufferSize:Unit = {
    dprintln(s"Set BufferSize ...")
    design.top.compUnits.foreach { cu =>
      emitBlock(s"$cu") {
        cu.mbuffers.foreach { buf =>
          val bufSize = buf.producer match {
            case top:Top => 1
            case cu:ComputeUnit => cu.parent match {
              case m:MetaPipeline =>
                var next = List(buf.producer)
                var dist = 1
                while (next.size!=0 && !next.contains(buf.consumer)) {
                  next = next.flatMap{ _ match {
                      case cu:ComputeUnit => cu.trueProduced.map{_.consumer}
                      case top:Top => Nil //TODO
                    }
                  } 
                  dist +=1
                }
                dist
              case _:Sequential | _:Top => 1
              case s:StreamController => 1 //TODO: fix this
            }
          }
          buf.buffering(bufSize)
          dprintln(s"$buf buffering=${bufSize}")
          backPressureOf(buf) = bufSize > 1
        }
      }
    }
  }

  addPass(design.accessAnalyzer.hasRun, runCount=2) {
    setProducerConsumer // producer consumer
    findBackEdge
    setBufferSize // buffering, backPressureOf
    //ForwardRef.collectOuters
  } 

  override def finPass = {
    close
    super.finPass
  }

}
