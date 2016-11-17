package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue

class MultiBufferAnalysis(implicit val design: Design) extends Traversal{

  val bufSizeMap = Map[DummyVecIn, Int]()

  override def traverse:Unit = {
    design.top.spadeCtrlers.foreach { cu =>
      cu.vouts.collect{ case v:DummyVecOut => v }.foreach { dvout =>
        dvout.readers.foreach { dvin =>
          val writer = cu
          val reader = dvin.ctrler
          reader match {
            case reader:InnerController =>
              val size = if (writer.isInstanceOf[Top]) {
                1
              } else {
                val reader = dvin.ctrler
                val ancestors1 = cu.ancestors
                val ancestors2 = reader.ancestors
                val ca = ancestors1.intersect(ancestors2).head
                if (ca==reader)
                  throw PIRException(s"Ancesstor shouldn't read descendent's ScalarOut CommonAncesstor:$ca writer:$cu reader:$reader")
                ca match {
                  case m:MetaPipeline =>
                    val anc1 = ancestors1.intersect(ca.children)
                    val anc2 = ancestors2.intersect(ca.children)
                    assert(anc1.size==1)
                    assert(anc2.size==1)
                    var next = anc1.head.dependeds 
                    var dist = 1
                    while (next.size!=0 && !next.contains(anc2.head)) {
                      next = next.flatMap{_.dependeds} 
                      dist +=1
                    }
                    dist
                  case _ => 1
                }
              }
              bufSizeMap += dvin -> size 
              //if (size!=1) {
                //val sm = ScalarMem(dvin) 
                //reader.inner.addScalarMem(sm)
              //}
            case _ =>
          }
        }
      }
    }

    ForwardRef.collectOuters
  } 

  def finPass = {
    info("Finishing multiBuffer analysis")
  }

}
