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

  def leastCommonAncestor(reader:ComputeUnit, writer:ComputeUnit):Controller = {
    reader.ancestors.intersect(writer.ancestors).head
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
          assert(producers.size==1)
          assert(consumers.size==1)
          val producer = producers.head
          val consumer = consumers.head
          buf.producer(producer)
          buf.consumer(consumer, true) //TODO: how to detect back edge?
          emitln(s"$buf producer:${buf.producer} consumer:${buf.consumer}")
        }
      }
    }
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
                      case cu:ComputeUnit => cu.produced.map{_.consumer}
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
    setBufferSize
    //ForwardRef.collectOuters
  } 

  override def finPass = {
    misc.info("Finishing multiBuffer analysis")
  }

}
