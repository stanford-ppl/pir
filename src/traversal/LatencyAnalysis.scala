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

  def offchipLatency(mc:MemoryController) = {
    val len = mc.len
    contentionOf(mc) * 1000//TODO
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
    cchain.counters.map { ctr => iter(ctr) }.reduce{_*_}
  }

  def calcLatency(cl:Controller):Int = {
    cl match {
      case mc:MemoryController => cycleOf(mc) =  offchipLatency(mc)
      case cl:UnitPipeline => cycleOf(cl) = numPStage 
      case cl:Pipeline => cycleOf(cl) = iter(cl.localCChain) + (numPStage-1)
      case cl:StreamController =>
        val heads = cl.children.filter{ case (c:ComputeUnit) => c.isHead && c.isInstanceOf[Pipeline] }
        val singLat = heads.map { child => 
          cycleOf.getOrElseUpdate(child, calcLatency(child))
        }.reduceOption[Int] { case (a,b) => max(a,b) }.getOrElse(1)
        cycleOf(cl) = iter(cl.localCChain) + (singLat-1) //TODO this is not right
      case cl:Sequential => 
        def accumLat(cl:ComputeUnit):Int = {
          val pres = cl.dependencies
          val myLat = cycleOf.getOrElseUpdate(cl, calcLatency(cl))
          if (pres.size==0) myLat 
          else myLat + pres.map{ dep => accumLat(dep) }.reduce[Int]{ case (a,b) => max(a,b) }
        }
        val lasts = cl.children.filter {_.isLast}
        val singLat = lasts.map { child => accumLat(child) }.reduce[Int]{ case (a,b) => max(a,b) }
        cycleOf(cl) = iter(cl.localCChain) * singLat 
      case cl:MetaPipeline => 
        val singLat = cl.children.map { child => cycleOf.getOrElseUpdate(child, calcLatency(child)) }
          .reduce[Int]{ case (a,b) => max(a,b) }
        cycleOf(cl) = iter(cl.localCChain) * singLat
      case cl:Top =>
        assert(cl.children.size==1)
        val child = cl.children.head
        cycleOf(cl) = cycleOf.getOrElseUpdate(child, calcLatency(child))
    }
    cycleOf(cl)
  }

  override def run = {
    if (design.contentionAnalysis.isTraversed) super.run
  }

  override def traverse:Unit = {
    calcLatency(design.top)
  } 

  def finPass = {
    info("Finishing latency modeling")
  }

}
