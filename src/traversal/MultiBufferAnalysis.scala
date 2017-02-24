package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue

class MultiBufferAnalysis(implicit val design: Design) extends Traversal with Logger {

  override val stream = newStream(s"MultiBufferAnalysis.log")

  def leastCommonAncestor(reader:Controller, writer:Controller):Controller = {
    val ra = reader.ancestors
    val wa = writer.ancestors
    val ca = ra.intersect(wa)
    ca.size match {
      case 0 => throw new Exception(s"$reader and $writer doesn't have common ancestors. \nreader ancestors:$ra \nwriter ancestors: $wa")
      case _ => ca.head
    }
  }

  def setProducerConsumer:Unit = {
    emitln(s"Set producer consumer ...")
    design.top.compUnits.foreach { cu =>
      emitBlock(s"$cu") {
        cu.mbuffers.foreach { buf =>
          val reader = buf.reader
          val writer = buf.writer
          val lca = leastCommonAncestor(reader, writer)
          val producers = writer.ancestors.intersect(lca.children)
          val consumers = reader.ancestors.intersect(lca.children)
          val (producer, consumer) = if (producers.isEmpty || consumers.isEmpty) {
            (lca, lca)
          } else {
            (producers.head, consumers.head)
          }
          buf.producer(producer)
          buf.consumer(consumer, true) // detect back edge later
          cu match {
            case cu:MemoryPipeline if (buf.isInstanceOf[RemoteMem]) => cu.parent(lca)
            case _ =>
          }
          emitln(s"$cu parent:$lca")
          emitln(s"$buf writer:$writer writer.ancestors:${writer.ancestors}")
          emitln(s"$buf reader:$reader reader.ancestors:${reader.ancestors}")
          emitln(s"$buf lca: $lca lca.children:${lca.children} producers:$producers consumers:$consumers")
          emitln(s"$buf producer:${buf.producer} consumer:${buf.consumer}")
        }
      }
    }
  }

  def findCycle(ctrlers:List[Controller]):Unit = {
    val visited = ListBuffer[Controller]()
    val toVisit = Queue[Controller]()
    val heads = ctrlers.filter{_.consumed.isEmpty}
    //Hack: if there's a loop without entry point, assume the first ctrler is the entry point in the
    //program order
    toVisit ++= (if (heads.isEmpty) List(ctrlers.head) else heads)
    while(toVisit.nonEmpty) {
      val node = toVisit.dequeue
      visited += node
      emitln(s"Visiting $node produced:${node.produced}")
      node.produced.foreach { mem =>
        if (visited.contains(mem.consumer)) {
          mem.trueDep = false
          emitln(s"Set $mem.trueDep=false in ${mem.ctrler}")
        } else {
          if (!toVisit.contains(mem.consumer))
            toVisit += mem.consumer
        }
      }
    }
    emitln(s"----")
    visited.foreach{c => if (c.children.nonEmpty) findCycle(c.children) }
  }

  def findBackEdge:Unit = {
    emitln(s"Finding back edge in dependencies")
    
    findCycle(List(design.top))
  }

  def setBufferSize:Unit = {
    emitln(s"Set BufferSize ...")
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
            }
          }
          buf.buffering(bufSize)
          emitln(s"$buf buffering=${bufSize}")
        }
      }
    }
  }

  override def traverse:Unit = {
    setProducerConsumer
    findBackEdge
    setBufferSize
    //ForwardRef.collectOuters
  } 

  override def finPass = {
    misc.endInfo("Finishing multiBuffer analysis")
  }

}
