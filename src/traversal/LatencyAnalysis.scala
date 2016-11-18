package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.enums._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class LatencyAnalysis(implicit val design: Design) extends Traversal with Metadata {

  val numPStage = 10 // Number of stages per CU 

  val latency = Map[Controller,List[Long]]()

  // Not used. Doens't work when addr calculation is splitted 
  def hackLen(mc:MemoryController) = {
    val lenWritter = mc.len.writer.ctrler
    assert(lenWritter.isInstanceOf[UnitPipeline] || lenWritter.isInstanceOf[StreamPipeline])
    lenWritter.asInstanceOf[ComputeUnit].stages.flatMap{ 
      _.fu.fold(List[Node]())(_.operands.map(_.from.src)) }.collect { case Const(_, str) =>
        val (num, tpe) = str.splitAt(str.length-1)
      num.toLong
    }.reduce[Long]{ case (a,b) => max(a,b) }
  }

  val sizeSet = Set[(Int, Int, MCType)]()
  //val offchipLat = Map[(Int, Int, MCType), Long]()
  //offchipLat += (1  , 125 , TileLoad)  -> 100.toLong
  //offchipLat += (1  , 1   , TileLoad)    -> 100.toLong
  //offchipLat += (48 , 3   , TileStore)  -> 100.toLong
  //offchipLat += (1  , 125 , TileLoad)  -> 100.toLong
  //offchipLat += (1  , 125 , TileLoad)  -> 100.toLong
  //offchipLat += (1  , 125 , TileStore) -> 100.toLong
  //offchipLat += (16 , 1   , TileLoad)   -> 100.toLong
  //offchipLat += (48 , 1   , TileLoad)   -> 100.toLong
  //offchipLat += (16 , 3   , TileStore)  -> 100.toLong

  //val sizeSet = Set[(Int, String, MCType)]()
  val offchipLat = Map[(Int, String, MCType), Long]()
  offchipLat += (1  , "DotProductDesign", TileLoad)  -> 788.toLong
  offchipLat += (1  , "OuterProductDesign", TileLoad)    -> 56.toLong
  offchipLat += (48 , "OuterProductDesign", TileStore)  -> 1324.toLong
  offchipLat += (1  , "TPCHQ6Design", TileLoad)  -> 788.toLong
  offchipLat += (1  , "BlackScholesDesign", TileLoad)  -> 788.toLong
  offchipLat += (1  , "BlackScholesDesign", TileStore) -> 847.toLong
  offchipLat += (16 , "MatMult_innerDesign", TileLoad)   -> 385.toLong
  offchipLat += (48 , "MatMult_innerDesign", TileLoad)   -> 1078.toLong
  offchipLat += (16 , "MatMult_innerDesign", TileStore)  -> 469.toLong

  offchipLat += (16 , "MatMult_outerDesign", TileLoad)   -> 385.toLong
  offchipLat += (48 , "MatMult_outerDesign", TileLoad)   -> 1078.toLong
  offchipLat += (16 , "MatMult_outerDesign", TileStore)  -> 469.toLong

  offchipLat += (1, "LogRegDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "LogRegDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "LogRegDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "LogRegDesign", TileStore)  -> 469.toLong

  offchipLat += (1, "SGDDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "SGDDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "SGDDesign", TileStore)  -> 469.toLong

  offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469.toLong
  offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469.toLong

  offchipLat += (1, "GDADesign", TileStore)  -> 469.toLong
  offchipLat += (1, "GDADesign", TileStore)  -> 469.toLong
  offchipLat += (1, "GDADesign", TileStore)  -> 469.toLong
  offchipLat += (1, "GDADesign", TileStore)  -> 469.toLong
  offchipLat += (1, "GDADesign", TileStore)  -> 469.toLong
  def offchipLatency(mc:MemoryController) = {
    val len = constOf.getOrElseUpdate(mc.len, constProp(mc.len))
    val numRow = iter(mc.parent.localCChain)
    val numBytes = ceil(len.toDouble * 4).toLong
    val comb = (numRow.toInt, numBytes.toInt, mc.mctpe)
    if (!sizeSet.contains(comb)) {
      println(s"OffChip Access: $design numRow=$numRow numBytes=$numBytes len=$len tpe=${mc.mctpe}")
      sizeSet += comb
    }
    //contentionOf(mc) * numBytes / 64 * 40 //TODO
    if ((numRow.toInt, s"$design", mc.mctpe) == (16 , "MatMult_outerDesign", TileLoad)) {
    } else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "MatMult_outerDesign", TileStore)) {

    } else if ((numRow.toInt, numBytes, mc.mctpe) == (10, /*"LogRegDesign"*/ 768, TileLoad)) { 758
    } else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegDesign"*/ 4, TileLoad)) {
    } else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegDesign"*/ 768, TileStore)) {

    } else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    } else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    } else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    } else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 

    } else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionDesign", TileStore)) { 
    } else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 64  , "ConvolutionDesign", TileLoad)) { 
    } else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionDesign", TileLoad)) { 
    }
    //val comb = (numRow.toInt, s"$design", mc.mctpe)
    //offchipLat(comb)
    10
  }

  def constProp(node:Node):Long = {
    val const:Long = node match {
      case op:OutPort => constProp(op.src) 
      case ip:InPort => constProp(ip.from)
      case Const(_, str) =>
        val (num, tpe) = str.splitAt(str.length-1)
        num.toLong
      case PipeReg(stage, ScalarInPR(_, scalarIn)) => constProp(scalarIn)
      case PipeReg(stage, CtrPR(_, ctr)) => constProp(ctr)
      case pr:PipeReg => constProp(pr.in.from.src)
      case fu:FuncUnit => 
        fu.op match {
          case FixMin =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            min(op1, op2)
          case FixSub =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            op1 - op2
          case FixAdd =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            op1 + op2
          case FixMod =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            op1 % op2
          case FixMul =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            op1 * op2
          case Mux =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            val op3 = constProp(fu.operands(2))
            if (op3==0) op1 else op2
          case FixNeq =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            if (op1!=op2) 1 else 0
          case Bypass =>
            val op1 = constProp(fu.operands(0))
            op1
          case _ => throw PIRException(s"Don't know how to const propogate $fu ${fu.op}")
        }
      case ScalarIn(_, scalar) => constProp(scalar)
      case ctr:Counter => 0
      case s:ScalarOut => constProp(s.in.from)
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

  def iter(ctr:Counter):Long = {
    val max = getConst(ctr.max)
    val step = getConst(ctr.step)
    val min = getConst(ctr.min)
    (max - min) / step //Should rounds down
  }

  def iter(cchain:CounterChain):Long = {
    val it = cchain.counters.map { ctr => iter(ctr) }.reduce{_*_}
    iterOf(cchain.ctrler) = it
    it
  }

  /* Latency of the outer controller if only run 1 iteration */
  def singleIterLat(cl:Controller):Long = {
    def accumLat(cl:ComputeUnit):Long = {
      val pres = cl.dependencies
      val myLat = cycleOf.getOrElseUpdate(cl, calcLatency(cl))
      if (pres.size==0) myLat 
      else myLat + pres.map{ dep => accumLat(dep) }.reduce[Long]{ case (a,b) => max(a,b) }
    }
    val lasts = cl.children.filter {_.isLast}
    lasts.map { child => accumLat(child) }.reduce[Long]{ case (a,b) => max(a,b) }
  }

  def calcLatency(cl:Controller):Long = {
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
            case cu:Pipeline =>
          }
        }
        val pipes = cl.children.map{_.ctrler}.collect{ case p:Pipeline if !p.isInstanceOf[UnitPipeline] => p}
        if (pipes.size>0) { // OffChip Store
          assert(pipes.size==1)
          val pipe = pipes.head
          assert(pipe.vouts.head.readers.exists{_.ctrler.isInstanceOf[MemoryController]})
          cycleOf(cl) = (iter(cl.localCChain)-1)*cycleOf.getOrElseUpdate(pipe, calcLatency(pipe)) + singleIterLat(cl)
        } else { // Spliting
          cycleOf(cl) = (iter(cl.localCChain)-1) + singleIterLat(cl)
        }
      case cl:Sequential => 
        cycleOf(cl) = iter(cl.localCChain) * singleIterLat(cl) 
      case cl:MetaPipeline => 
        val maxLat = cl.children.map { child => cycleOf.getOrElseUpdate(child, calcLatency(child)) }
          .reduce[Long]{ case (a,b) => max(a,b) }
        cycleOf(cl) = (iter(cl.localCChain)-1)*maxLat + singleIterLat(cl) 
      case cl:Top =>
        assert(cl.children.size==1)
        val child = cl.children.head
        cycleOf(cl) = cycleOf.getOrElseUpdate(child, calcLatency(child))
    }
    if (cycleOf(cl) < 0) throw PIRException(s"$cl has negative number of cycles = ${cycleOf(cl)}")
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
