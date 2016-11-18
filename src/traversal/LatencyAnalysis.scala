package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class LatencyAnalysis(implicit val design: Design) extends Traversal with Metadata {

  val numPStage = 10 // Number of stages per CU 

  val latency = Map[Controller,List[Int]]()

  def hackLen(mc:MemoryController) = {
    mc.len.writer.ctrler.asInstanceOf[UnitPipeline].stages.flatMap{ 
      _.fu.fold(List[Node]())(_.operands.map(_.from.src)) }.collect { case Const(_, str) =>
        val (num, tpe) = str.splitAt(str.length-1)
      num.toInt
    }.reduce[Int]{ case (a,b) => max(a,b) }
  }

  def offchipLatency(mc:MemoryController) = {
    val len = constOf.getOrElseUpdate(mc.len, hackLen(mc))
    contentionOf(mc) * 40//TODO
  }

  def constProp(node:Node):Int = {
    val const = node match {
      case op:OutPort => constProp(op.src) 
      case ip:InPort => constProp(ip.from)
      case Const(_, str) =>
        val (num, tpe) = str.splitAt(str.length-1)
        num.toInt
      case PipeReg(stage, ScalarInPR(_, scalarIn)) => constProp(scalarIn)
      case ScalarIn(_, scalar) => constProp(scalar)
      case n:ArgIn => n.const match {
          case Some(c) => c
          case None => throw PIRException(s"Don't know you to const propogate ArgIn $n")
        }
      case s:Scalar => constProp(s.writer)
      case n => throw PIRException(s"Don't know how to const propogate $n") 
    }
    constOf(node) = const
    const
  }

  def getConst(n:Node) = {
    constOf.getOrElseUpdate(n, constProp(n))
  }

  def iter(ctr:Counter):Int = {
    val max = getConst(ctr.max)
    val step = getConst(ctr.step)
    val min = getConst(ctr.min)
    (max - min) / step //Should rounds down
  }

  def iter(cchain:CounterChain):Int = {
    val it = cchain.counters.map { ctr => iter(ctr) }.reduce{_*_}
    iterOf(cchain.ctrler) = it
    it
  }

  /* Latency of the outer controller if only run 1 iteration */
  def singleIterLat(cl:Controller):Int = {
    def accumLat(cl:ComputeUnit):Int = {
      val pres = cl.dependencies
      val myLat = cycleOf.getOrElseUpdate(cl, calcLatency(cl))
      if (pres.size==0) myLat 
      else myLat + pres.map{ dep => accumLat(dep) }.reduce[Int]{ case (a,b) => max(a,b) }
    }
    val lasts = cl.children.filter {_.isLast}
    lasts.map { child => accumLat(child) }.reduce[Int]{ case (a,b) => max(a,b) }
  }

  def calcLatency(cl:Controller):Int = {
    cl match {
      case mc:MemoryController => cycleOf(mc) =  offchipLatency(mc)
      case cl:UnitPipeline => cycleOf(cl) = iter(cl.localCChain); numPStage 
      case cl:Pipeline => cycleOf(cl) = (iter(cl.localCChain)-1) + numPStage 
      case cl:StreamPipeline => cycleOf(cl) = numPStage 
      case cl:StreamController =>
        val mcs = cl.children.collect { case mc:MemoryController => mc }
        cl.children.foreach { cl => 
          cl match { // Assert children of StreamController doesn't have a counter
            case mc:MemoryController =>
            case sp:StreamPipeline =>
            case cu:UnitPipeline =>
          }
        }
        cycleOf(cl) = (iter(cl.localCChain)-1) + singleIterLat(cl)
      case cl:Sequential => 
        cycleOf(cl) = iter(cl.localCChain) * singleIterLat(cl) 
      case cl:MetaPipeline => 
        val maxLat = cl.children.map { child => cycleOf.getOrElseUpdate(child, calcLatency(child)) }
          .reduce[Int]{ case (a,b) => max(a,b) }
        cycleOf(cl) = (iter(cl.localCChain)-1)*maxLat + singleIterLat(cl) 
      case cl:Top =>
        assert(cl.children.size==1)
        val child = cl.children.head
        cycleOf(cl) = cycleOf.getOrElseUpdate(child, calcLatency(child))
    }
    cycleOf(cl)
  }

  override def run = {
    if (design.contentionAnalysis.isTraversed) {
      super.run
      PIRStat.cycle(cycleOf(design.top))
      iterOf(design.top) = 1
    }
  }

  override def traverse:Unit = {
    calcLatency(design.top)
  } 

  def finPass = {
    info("Finishing latency modeling")
  }

}
