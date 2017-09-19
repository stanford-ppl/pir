package pir.pass
import pir.node._
import pir._
import pir.util.misc._
import pirc.enums._
import pirc.exceptions.PIRException
import pir.codegen.{Logger, CSVPrinter, Row}
import Math._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue

class LatencyAnalysis(implicit design: PIR) extends Pass with Logger {
  import pirmeta._

  def shouldRun = design.pirMapping.succeeded && design.controlAnalyzer.hasRun && design.contentionAnalyzer.hasRun
  override lazy val stream = newStream(s"LatencyAnalysis.log")

  val latency = Map[Controller,List[Int]]()
  lazy val mp = design.mapping.get

  def lenOf(mc:MemoryController) = mc.fifos.filter { _.name==Some("size") }.head

  // Not used. Doens't work when addr calculation is splitted 
  def hackLen(mc:MemoryController) = {
    val lenWritter = lenOf(mc).writers.head.ctrler
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
  //offchipLat += (1  , "DotProductPIR", TileLoad)  -> 788
  //offchipLat += (1  , "OuterProductPIR", TileLoad)    -> 56
  //offchipLat += (48 , "OuterProductPIR", TileStore)  -> 1324
  //offchipLat += (1  , "TPCHQ6PIR", TileLoad)  -> 788
  //offchipLat += (1  , "BlackScholesPIR", TileLoad)  -> 788
  //offchipLat += (1  , "BlackScholesPIR", TileStore) -> 847
  //offchipLat += (16 , "MatMult_innerPIR", TileLoad)   -> 385
  //offchipLat += (48 , "MatMult_innerPIR", TileLoad)   -> 1078
  //offchipLat += (16 , "MatMult_innerPIR", TileStore)  -> 469

  //offchipLat += (16 , "MatMult_outerPIR", TileLoad)   -> 385
  //offchipLat += (48 , "MatMult_outerPIR", TileLoad)   -> 1078
  //offchipLat += (16 , "MatMult_outerPIR", TileStore)  -> 469

  //offchipLat += (1, "LogRegPIR", TileStore)  -> 469
  //offchipLat += (1, "LogRegPIR", TileStore)  -> 469
  //offchipLat += (1, "LogRegPIR", TileStore)  -> 469
  //offchipLat += (1, "LogRegPIR", TileStore)  -> 469

  //offchipLat += (1, "SGDPIR", TileStore)  -> 469
  //offchipLat += (1, "SGDPIR", TileStore)  -> 469
  //offchipLat += (1, "SGDPIR", TileStore)  -> 469

  //offchipLat += (1, "Kmeans_fissionPIR", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionPIR", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionPIR", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionPIR", TileStore)  -> 469
  //offchipLat += (1, "Kmeans_fissionPIR", TileStore)  -> 469

  //offchipLat += (1, "GDAPIR", TileStore)  -> 469
  //offchipLat += (1, "GDAPIR", TileStore)  -> 469
  //offchipLat += (1, "GDAPIR", TileStore)  -> 469
  //offchipLat += (1, "GDAPIR", TileStore)  -> 469
  //offchipLat += (1, "GDAPIR", TileStore)  -> 469
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
    ////if ((numRow.toInt, s"$design", mc.mctpe) == (16 , "MatMult_outerPIR", TileLoad)) {
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "MatMult_outerPIR", TileStore)) {

    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (10, /*"LogRegPIR"*/ 768, TileLoad)) { 758
    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegPIR"*/ 4, TileLoad)) {
    ////} else if ((numRow.toInt, numBytes, mc.mctpe) == (1, /*"LogRegPIR"*/ 768, TileStore)) {

    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDPIR", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDPIR", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDPIR", TileStore)) { 
    ////} else if ((numRow.toInt, s"$design", mc.mctpe) == (1, "SGDPIR", TileStore)) { 

    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionPIR", TileStore)) { 
    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 64  , "ConvolutionPIR", TileLoad)) { 
    ////} else if ((numRow.toInt, numBytes, s"$design", mc.mctpe) == (1, 1024, "ConvolutionPIR", TileLoad)) { 
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
          case op:Op1 =>
            val op1 = constProp(fu.operands(0))
            op.eval(op1.asInstanceOf[op.T]).asInstanceOf[Int]
          case op:Op2 =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            op.eval(op1.asInstanceOf[op.T], op2.asInstanceOf[op.T]).asInstanceOf[Int]
          case op:Op3 =>
            val op1 = constProp(fu.operands(0))
            val op2 = constProp(fu.operands(1))
            val op3 = constProp(fu.operands(2))
            op.eval(op1.asInstanceOf[op.T], op2.asInstanceOf[op.T], op3.asInstanceOf[op.T]).asInstanceOf[Int]
          case _ => throw PIRException(s"Don't know how to const propogate $fu ${fu.op}")
        }
      case ScalarIn(_, scalar) => constProp(scalar)
      case ctr:Counter => 0
      case s:ScalarOut => constProp(s.in.from)
      case n:ArgIn => boundOf.get(n) match {
          case Some(c:Int) => c
          case _ => throw PIRException(s"Don't know how to const propogate ArgIn $n")
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

  def cycle(cl:Controller) = {
    cycleOf.getOrElseUpdate(cl, calcLatency(cl))
  }

  /* Latency of the outer controller if only run 1 iteration */
  def singleIterLatency(cl:Controller):Long = {
    def accumLatency(cl:ComputeUnit):Long = {
      val prevs = (cl match {
        case cl if isStreaming(cl) & isHead(cl) => Nil 
        case cl if isStreaming(cl) => 
          val fifos = cl.descendents.flatMap {
            case cu:ComputeUnit => cu.fifos
            case cu => Nil
          }
          var writers = fifos.map { _.writers.head } //TODO
          dprintln(s"$cl fifowriters:[${fifos.zip(writers).map { case (fifo,writer) => s"$fifo $writer"}.mkString(",")}]" )
          writers = writers.filter { writer => cl.parent.descendents.contains(writer)}
          dprintln(s"$cl.parent= ${cl.parent} parent descendents:${cl.parent.descendents}" )
          dprintln(s"$cl filtered writers:[${writers.mkString(",")}]" )
          writers
        case cl if isPipelining(cl) => cl.trueConsumed.map(_.producer)
      }).toSet
      dprintln(s"prevs:[${prevs.mkString(",")}]")
      val myLat = cycle(cl)
      if (prevs.size==0) myLat 
      else myLat + prevs.map{ dep => accumLatency(dep.asCU) }.reduce[Long]{ case (a,b) => max(a,b) }
    }
    val lasts = cl.children.filter {_.isLast}
    val singleLat = lasts.map { child => accumLatency(child) }.reduce[Long]{ case (a,b) => max(a,b) }
    dprintln(s"$cl singleLat:$singleLat")
    singleLat
  }

  def calcLatency(cl:Controller):Long = emitBlock(s"calcLatency($cl)") {
    cl match {
      case mc:MemoryController => cycleOf(mc) =  offchipLatency(mc)
      case cl:MetaPipeline => 
        val maxLat = cl.children.filterNot {
          case child:MemoryPipeline => true
          case child => false
        }.map { child => cycle(child) }
        .reduce[Long]{ case (a,b) => max(a,b) }
        cycleOf(cl) = (iterOf(cl)-1)*maxLat + singleIterLatency(cl) 
      case cl:Pipeline if (isPipelining(cl)) => 
        val pcl = mp.pmmap(cl)
        cycleOf(cl) = (iterOf(cl)-1) + pcl.stages.length
      case cl:Pipeline if (isStreaming(cl)) => 
        val pcl = mp.pmmap(cl)
        cycleOf(cl) = pcl.stages.length
      case cl:StreamController => 
        cl.children.foreach { child => cycle(child) } // calculate cycle of nested SteamController
        cycleOf(cl) = (iterOf(cl)-1) + singleIterLatency(cl)
      case cl:Sequential => cycleOf(cl) = iterOf(cl) * singleIterLatency(cl) 
      case cl:Top =>
        assert(cl.children.size==1)
        val child = cl.children.head
        cycleOf(cl) = cycle(child)
    }
    if (cycleOf(cl) < 0) throw PIRException(s"$cl has negative number of cycles = ${cycleOf(cl)}")
    dprintln(s"cycleOf($cl) = ${cycleOf(cl)}")
    cycleOf(cl)
  }

  def setIter = {
    design.top.ctrlers.foreach {
      case cl:MemoryPipeline =>
      case cl if isStreaming(cl) => iterOf(cl) = 1
      case cl:ComputeUnit => iterOf(cl) = iterOf(localCChainOf(cl))
      case cl:Top => iterOf(cl) = 1
    }
  }

  def calcTotalCycle = {
    design.top.ctrlers.foreach { cl =>
      cycleOf.get(cl).map { cycle =>
        totalCycleOf(cl) = cycle * ancestorsOf(cl).drop(1).map(anc => iterOf(anc)).fold(1l)(_*_)
      }
    }
  }

  addPass {
    setIter
    calcLatency(design.top)
    calcTotalCycle
  }

}
