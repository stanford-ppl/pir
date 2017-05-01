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

  def leastCommonAncestor(reader:Controller, writer:Controller):Controller = {
    val ra = reader.ancestors
    val wa = writer.ancestors
    val ca = ra.intersect(wa)
    ca.size match {
      case 0 => throw new Exception(s"$reader and $writer doesn't have common ancestors. \nreader ancestors:$ra \nwriter ancestors: $wa")
      case _ => ca.head
    }
  }

  def findProducerConsumer(reader:Controller, writer:Controller, lca:Controller):Option[(Controller, Controller)] = {
    val producers = writer.ancestors.intersect(lca.children)
    val consumers = reader.ancestors.intersect(lca.children)
    val (producer, consumer) = if (producers.isEmpty || consumers.isEmpty) {
      (lca, lca)
    } else {
      (producers.head, consumers.head)
    }
    if (isStreaming(producer) || isStreaming(consumer))
      None
    else Some((producer, consumer))
  }

  def setProducerConsumer(cu:ComputeUnit, buf:MultiBuffering):Unit = {
    if (buf.producer!=null && buf.consumer!=null && cu.parent!=null) return
    val readers = buf.readers
    val writer = buf.writer
    readers.foreach { reader =>
      reader match {
        case mp:MemoryPipeline => setProducerConsumer(mp, mp.mem)
        case _ =>
      }
    }
    val reader = readers.head //HACK TODO multiple reader should happen only after splitting. shouldn't change control
    var lca = leastCommonAncestor(reader, writer)
    var pc = findProducerConsumer(reader, writer, lca)
    while (pc.isEmpty) {
      lca = lca.asCU.parent
      pc = findProducerConsumer(reader, writer, lca)
    }
    val (producer, consumer) = pc.get

    buf.producer(producer)
    buf.consumer(consumer, true) // detect back edge later
    dprintln(s"$cu parent:$lca")
    dprintln(s"$buf writer:$writer writer.ancestors:${writer.ancestors}")
    dprintln(s"$buf reader:$reader reader.ancestors:${reader.ancestors}")
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

  def findCycle(ctrlers:List[Controller]):Unit = {
    val visited = ListBuffer[Controller]()
    val toVisit = Queue[Controller]()
    val heads = ctrlers.filter{_.consumed.isEmpty}.filterNot{ c => c.isInstanceOf[MemoryPipeline]}
    //Hack: if there's a loop without entry point, assume the first ctrler is the entry point in the
    //program order
    toVisit ++= (if (heads.isEmpty) List(ctrlers.head) else heads)
    while(toVisit.nonEmpty) {
      val node = toVisit.dequeue
      visited += node
      dprintln(s"Visiting $node produced:${node.produced}")
      node.produced.foreach { mem =>
        if (visited.contains(mem.consumer)) {
          mem.trueDep = false
          dprintln(s"Set $mem.trueDep=false in ${mem.ctrler}")
        } else {
          if (!toVisit.contains(mem.consumer))
            toVisit += mem.consumer
        }
      }
    }
    dprintln(s"----")
    visited.foreach{c => if (c.children.nonEmpty) findCycle(c.children) }
  }

  def findBackEdge:Unit = {
    dprintln(s"Finding back edge in dependencies")
    
    findCycle(List(design.top))
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
        }
      }
    }
  }

  override def traverse:Unit = {
    assert(design.accessAnalyzer.hasRun)
    setProducerConsumer
    findBackEdge
    setBufferSize
    //ForwardRef.collectOuters
  } 

  override def finPass = {
    close
    super.finPass
  }

}
