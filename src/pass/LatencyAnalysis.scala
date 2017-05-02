package pir.pass
import pir.graph._
import pir._
import pir.util.misc._
import pir.util.enums._
import pir.exceptions.PIRException
import pir.codegen.{Logger, CSVPrinter, Row}

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class LatencyAnalysis(implicit design: Design) extends Pass with Logger {
  import pirmeta._

  def shouldRun = design.pirMapping.succeeded && design.controlAnalyzer.hasRun && design.contentionAnalyzer.hasRun
  override lazy val stream = newStream(s"LatencyAnalysis.log")

  val latency = Map[Controller,List[Int]]()
  lazy val mp = design.mapping.get

  def lenOf(mc:MemoryController) = mc.fifos.filter { _.name==Some("size") }.head

  // Not used. Doens't work when addr calculation is splitted 
  def hackLen(mc:MemoryController) = {
    val lenWritter = lenOf(mc).writer.ctrler
    assert(lenWritter.isInstanceOf[UnitPipeline] || lenWritter.isInstanceOf[StreamPipeline])
    lenWritter.asInstanceOf[ComputeUnit].stages.flatMap{ 
      _.fu.fold(List[Node]())(_.operands.map(_.from.src)) }.collect { 
        case Const(c:Int) => c
    }.reduce[Int]{ case (a,b) => max(a,b) }
  }

  def hackLen2(mc:MemoryController):Int = {
    def constProp(node:Node):Option[Int] = {
      val const:Option[Int] = node match {
        case op:OutPort => constProp(op.src) 
        case ip:InPort => constProp(ip.from)
        case Const(c:Int) => Some(c)
        case PipeReg(stage, ScalarInPR(scalarIn)) => constProp(scalarIn)
        case PipeReg(stage, CtrPR(ctr)) => None 
        case pr:PipeReg => constProp(pr.in.from.src)
        case fu:FuncUnit => 
          //println(s"${fu.op} ${fu.ctrler}")
          fu.ctrler.asInstanceOf[InnerController].stages.reverseIterator.foreach { s =>
            val fu = s.fu.get
            fu.operands.foreach { op =>
              op.from.src match {
                case c:Const[_] => constProp(c).foreach { c => return Some(c) }
                case PipeReg(stage, CtrPR(ctr)) => return None 
                case _ =>
              }
            }
          }
          return None
          //var consts = fu.operands.flatMap{ _.from.src }.collect{ case c:Const => constProp(c) }.toSet
          //if (consts.size>1) throw PIRException(s"Got more than 1 valid constant for ${mc} ${fu.ctrler} $fu ${fu.op} $consts")
          //if (consts.size==1) return Some(consts.head)

          //consts = fu.operands.flatMap{ op => constProp(op.from.src) }
          //if (consts.size>1) throw PIRException(s"Got more than 1 valid constant for ${mc} ${fu.ctrler} $fu ${fu.op} $consts")
          //if (consts.size==1) return Some(consts.head)

        case ScalarIn(_,scalar) => constProp(scalar)
        case ctr:Counter => None
        case s:ScalarOut => constProp(s.in.from)
        case s:Scalar => constProp(s.writer)
        case n => throw PIRException(s"Don't know how to const propogate $n") 
      }
      const
    }
    constProp(lenOf(mc)).getOrElse(throw PIRException(s"Didn't find len of $mc"))
  }

  val sizeSet = Set[(Int, Int, MCType)]()
  //val offchipLat = Map[(Int, Int, MCType), Int]()
  //offchipLat += (1  , 125 , TileLoad)  -> 100
  //offchipLat += (1  , 1   , TileLoad)    -> 100
  //offchipLat += (48 , 3   , TileStore)  -> 100
  //offchipLat += (1  , 125 , TileLoad)  -> 100
  //offchipLat += (1  , 125 , TileLoad)  -> 100
  //offchipLat += (1  , 125 , TileStore) -> 100
  //offchipLat += (16 , 1   , TileLoad)   -> 100
  //offchipLat += (48 , 1   , TileLoad)   -> 100
  //offchipLat += (16 , 3   , TileStore)  -> 100

  //val sizeSet = Set[(Int, String, MCType)]()
  val offchipLat = Map[(Int, Int, MCType), Int]()
  //val offchipLat = Map[(Int, Int, MCType), Int]()
  // DotProduct
  offchipLat += (1  , 8000 , TileLoad)  -> 788
  // Outer Product
  offchipLat += (1  , 192  , TileLoad)    -> 2587
  offchipLat += (48 , 192  , TileStore)  -> 1324
  // TPCHQ6
  offchipLat += (1  , 8000 , TileLoad)  -> 788
  // Blackscholes 
  offchipLat += (1  , 8000 , TileLoad)  -> 788
  offchipLat += (1  , 8000 , TileStore) -> 847
  // MatMult_outer
  offchipLat += (16 , 192 , TileLoad)   -> 385
  offchipLat += (48 , 192 , TileLoad)   -> 1078
  offchipLat += (16 , 192 , TileStore)  -> 469
  // MatMult_inner
  //offchipLat += (16 , 192 , TileLoad)   -> 385
  //offchipLat += (48 , 192 , TileLoad)   -> 1078
  //offchipLat += (16 , 192 , TileStore)  -> 469
  // Log Reg
  offchipLat += (1 , 768 , TileStore)  -> 113
  offchipLat += (1 , 40 , TileLoad)  -> 50
  offchipLat += (10, 768, TileLoad)  -> 758
  // SGD
  offchipLat += (1 , 3072, TileStore)  -> 113
  offchipLat += (1 , 3072, TileLoad)  -> 326
  offchipLat += (1 , 768, TileLoad)  -> 110
  // Kmeans
  offchipLat += (1,   7680, TileStore)  -> 794
  offchipLat += (20 , 384, TileLoad)  -> 763
  // GDA
  offchipLat += (48 , 192, TileStore)  -> 2821
  offchipLat += (1 , 80, TileLoad)  -> 50
  offchipLat += (20 , 192, TileLoad)  -> 457
  // Convolution
  offchipLat += (1 , 1024, TileStore)  -> 56
  offchipLat += (1 , 64, TileLoad)  -> 50
  offchipLat += (1 , 16384, TileLoad)  -> 1574
  

  //
  //offchipLat += (1  , "DotProductDesign", TileLoad)  -> 788
  //offchipLat += (1  , "OuterProductDesign", TileLoad)    -> 56
  //offchipLat += (48 , "OuterProductDesign", TileStore)  -> 1324
  //offchipLat += (1  , "TPCHQ6Design", TileLoad)  -> 788
  //offchipLat += (1  , "BlackScholesDesign", TileLoad)  -> 788
  //offchipLat += (1  , "BlackScholesDesign", TileStore) -> 847
  //offchipLat += (16 , "MatMult_innerDesign", TileLoad)   -> 385
  //offchipLat += (48 , "MatMult_innerDesign", TileLoad)   -> 1078
  //offchipLat += (16 , "MatMult_innerDesign", TileStore)  -> 469

  //offchipLat += (16 , "MatMult_outerDesign", TileLoad)   -> 385
  //offchipLat += (48 , "MatMult_outerDesign", TileLoad)   -> 1078
  //offchipLat += (16 , "MatMult_outerDesign", TileStore)  -> 469

  //offchipLat += (1, "LogRegDesign", TileStore)  -> 469
  //offchipLat += (1, "LogRegDesign", TileStore)  -> 469
  //offchipLat += (1, "LogRegDesign", TileStore)  -> 469
  //offchipLat += (1, "LogRegDesign", TileStore)  -> 469

  //offchipLat += (1, "SGDDesign", TileStore)  -> 469
  //offchipLat += (1, "SGDDesign", TileStore)  -> 469
  //offchipLat += (1, "SGDDesign", TileStore)  -> 469

  //offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionDesign", TileStore)  -> 469

  //offchipLat += (1, "GDADesign", TileStore)  -> 469
  //offchipLat += (1, "GDADesign", TileStore)  -> 469
  //offchipLat += (1, "GDADesign", TileStore)  -> 469
  //offchipLat += (1, "GDADesign", TileStore)  -> 469
  //offchipLat += (1, "GDADesign", TileStore)  -> 469
  //def offchipLatency(mc:MemoryController) = {
    ////val len = constOf.getOrElseUpdate(mc.len, constProp(mc.len))
    //val len = constOf.getOrElseUpdate(lenOf(mc), hackLen2(mc))
    //val numRow = iter(mc.parent.localCChain)
    //val numBytes = ceil(len.toDouble * 4)
    //val comb = (numRow.toInt, numBytes.toInt, mc.mctpe)
    //if (!sizeSet.contains(comb)) {
      //println(s"OffChip Access: $design numRow=$numRow numBytes=$numBytes len=$len tpe=${mc.mctpe}")
      //sizeSet += comb
    //}
    //////contentionOf(mc) * numBytes / 64 * 40 //TODO
    ////if ((numRow.toInt, s"$design", mc.mctpe) == (16 , "MatMult_outerDesign", TileLoad)) {
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "MatMult_outerDesign", TileStore)) {

    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (10, /*"LogRegDesign"*/ 768, TileLoad)) { 758
    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegDesign"*/ 4, TileLoad)) {
    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegDesign"*/ 768, TileStore)) {

    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDDesign", TileStore)) { 

    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionDesign", TileStore)) { 
    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 64  , "ConvolutionDesign", TileLoad)) { 
    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionDesign", TileLoad)) { 
    ////}
    ////val comb = (numRow.toInt, s"$design", mc.mctpe)
    //offchipLat(comb) / 4 
  //}

  def offchipLatency(mc:MemoryController) = {
    100
  }

  def constProp(node:Node):Int = {
    val const:Int = node match {
      case op:OutPort => constProp(op.src) 
      case ip:InPort => constProp(ip.from)
      case Const(c:Int) => c
      case PipeReg(stage, ScalarInPR(scalarIn)) => constProp(scalarIn)
      case PipeReg(stage, CtrPR(ctr)) => constProp(ctr)
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
          case Some(Const(c:Int)) => c
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
  def singleIterLatency(cl:Controller):Int = {
    def accumLatency(cl:ComputeUnit):Int = {
      val prevs = cl match {
        case cl if isStreaming(cl) => cl.fifos.map { _.writer }
        case cl if isPipelining(cl) => cl.trueConsumed.map(_.producer)
      }
      val myLat = cycleOf.getOrElseUpdate(cl, calcLatency(cl))
      if (prevs.size==0) myLat 
      else myLat + prevs.map{ dep => accumLatency(dep.asCU) }.reduce[Int]{ case (a,b) => max(a,b) }
    }
    val lasts = cl.children.filter {_.isLast}
    lasts.map { child => accumLatency(child) }.reduce[Int]{ case (a,b) => max(a,b) }
  }

  def calcLatency(cl:Controller):Int = {
    cl match {
      case mc:MemoryController => cycleOf(mc) =  offchipLatency(mc)
      case cl:Pipeline if (isPipelining(cl)) => 
        val pcl = mp.clmap(cl)
        cycleOf(cl) = (iter(cl.localCChain)-1) + pcl.stages.length
      case cl:Pipeline if (isStreaming(cl)) => 
        val pcl = mp.clmap(cl)
        cycleOf(cl) = pcl.stages.length
      case cl:StreamController => cycleOf(cl) = (iter(cl.localCChain)-1) + singleIterLatency(cl)
      case cl:Sequential => cycleOf(cl) = iter(cl.localCChain) * singleIterLatency(cl) 
      case cl:MetaPipeline => 
        val maxLat = cl.children.filterNot {
          case child:MemoryPipeline => true
          case child => false
        }.map { child => cycleOf.getOrElseUpdate(child, calcLatency(child)) }
        .reduce[Int]{ case (a,b) => max(a,b) }
        cycleOf(cl) = (iter(cl.localCChain)-1)*maxLat + singleIterLatency(cl) 
      case cl:Top =>
        assert(cl.children.size==1)
        val child = cl.children.head
        cycleOf(cl) = cycleOf.getOrElseUpdate(child, calcLatency(child))
    }
    if (cycleOf(cl) < 0) throw PIRException(s"$cl has negative number of cycles = ${cycleOf(cl)}")
    cycleOf(cl)
  }

  override def traverse = {
    calcLatency(design.top)
    design.top.ctrlers.foreach { ctrler =>
      dprintln(s"cycleOf($ctrler) = ${cycleOf(ctrler)}")
    }
  }

}
