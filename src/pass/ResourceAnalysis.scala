package pir.pass
import pir.graph._
import pir._
import pir.util.misc._
import pir.exceptions.PIRException
import pir.mapper.{StageMapper, PIRMap, RegAlloc}
import pir.plasticine.main.SwitchNetwork
import pir.util.typealias._
import pir.codegen.{Logger, CSVPrinter, Row}

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class ResourceAnalysis(implicit design: Design) extends Pass {
  import pirmeta._
  def shouldRun = design.pirMapping.succeeded

  val summary = new CSVPrinter {
    override lazy val stream = newStream(Config.outDir, s"Utilization.csv", append=true)
  }

  val detail = new CSVPrinter {
    override lazy val stream = newStream(s"UtilizationDetail.csv", append=false)
  }

  val logger = new Logger {
    override lazy val stream = newStream(s"ResourceAnalysis.log")
  }

  lazy val mp = design.mapping.get
  override lazy val spade = design.arch.asInstanceOf[SwitchNetwork]

  val numPStage = 8 // Number of stages per CU 

  val activeCycle = Map[PNode, Long]()

  type Util = (Int, Int)
  def pctUtil(util:Util):Float = {
    val (u,t) = util
    if (t==0) -1 else u.toFloat / t
  }

  val regUsed = Map[PNode, Util]()
  val stageUsed = Map[PNode, Util]()
  val sBufUsed = Map[PNode, Util]()
  val vBufUsed = Map[PNode, Util]()

  val sinPinUsed = Map[PNode, Util]()
  val soutPinUsed = Map[PNode, Util]()
  val vinPinUsed = Map[PNode, Util]()
  val voutPinUsed = Map[PNode, Util]()
  val cinPinUsed = Map[PNode, Util]()
  val coutPinUsed = Map[PNode, Util]()

  var pcuUtil = (-1, -1)
  var mcuUtil = (-1, -1)
  var ocuUtil = (-1, -1)
  var scuUtil = (-1, -1)
  var mcUtil = (-1, -1)
  var slinkUtil = (-1, -1)
  var vlinkUtil = (-1, -1)
  var clinkUtil = (-1, -1)
  var totalRegUtil = (-1, -1)
  var totalStageUtil = (-1, -1)
  var totalSBufUtil = (-1, -1)
  var totalVBufUtil = (-1, -1)

  var totalSinPinUtil = (-1, -1)
  var totalSoutPinUtil = (-1, -1)
  var totalVinPinUtil = (-1, -1)
  var totalVoutPinUtil = (-1, -1)
  var totalCinPinUtil = (-1, -1)
  var totalCoutPinUtil = (-1, -1)

    //if (design.contentionAnalysis.isTraversed && design.latencyAnalysis.isTraversed) super.run
    //if (design.contentionAnalysis.hasRun) super.run

  //val numLanes = 16
  //val numPRegs = 16 * (numPStage + 1) * numLanes
  //val numAlus = 16 * numPStage  
  //val numSrams = 4
  //val numVins = 4

  def sum(list:List[Util]) = list.reduceOption[Util]{
    case ((u1:Int,t1:Int), (u2:Int, t2:Int)) => (u1+u2, t1+t2)
  }.getOrElse((0,0))

  def count(list:List[_]):Util = {
    sum(list.map {
      case x:List[_] => count(x)
      case (x:Option[_]) => (if (x.isEmpty) 0 else 1, 1)
      case x:(_,_) => x.asInstanceOf[Util]
    })
  }

  def collectRegUtil(pne:PNE) = pne match {
    case pne:PCU =>
      regUsed += pne -> count(pne.stages.map { pstage => pstage.prs.map { ppr => mp.ipmap.pmap.get(ppr.in) } })
    case pne =>
  }

  def collectStageUtil(pne:PNE) = pne match {
    case pne:PCU =>
      stageUsed += pne -> count(pne.stages.map { pst => mp.stmap.pmap.get(pst) })
    case pne =>
  }

  def collectVBufUtil(pne:PNE) = pne match {
    case pne:PCU =>
      vBufUsed += pne -> count(pne.vbufs.map { vbuf => mp.smmap.pmap.get(vbuf) })
    case pne =>
  }

  def collectSBufUtil(pne:PNE) = pne match {
    case pne:PCU =>
      sBufUsed += pne -> count(pne.sbufs.map { sbuf => mp.smmap.pmap.get(sbuf) })
    case pne =>
  }

  def collectSinPinUtil(pne:PNE) = {
    sinPinUsed += pne -> count(pne.sins.map { in => mp.vimap.pmap.get(in) })
  }

  def collectSoutPinUtil(pne:PNE) = {
    soutPinUsed += pne -> count(pne.souts.map { out => mp.vomap.pmap.get(out) })
  }

  def collectVinPinUtil(pne:PNE) = {
    vinPinUsed += pne -> count(pne.vins.map { in => mp.vimap.pmap.get(in) })
  }

  def collectVoutPinUtil(pne:PNE) =  {
    voutPinUsed += pne -> count(pne.vouts.map { out => mp.vomap.pmap.get(out) })
  }

  def collectCinPinUtil(pne:PNE) = {
    cinPinUsed += pne -> count(pne.cins.map { in => mp.vimap.pmap.get(in) })
  }

  def collectCoutPinUtil(pne:PNE) =  {
    coutPinUsed += pne -> count(pne.couts.map { out => mp.vomap.pmap.get(out) })
  }

  override def traverse:Unit = {
    spade.pnes.foreach { cl =>
      collectRegUtil(cl)
      collectStageUtil(cl)
      collectSBufUtil(cl)
      collectVBufUtil(cl)
      collectSinPinUtil(cl)
      collectSoutPinUtil(cl)
      collectVinPinUtil(cl)
      collectVoutPinUtil(cl)
      collectCinPinUtil(cl)
      collectCoutPinUtil(cl)
    }
    pcuUtil = count(spade.pcus.map(pcu => mp.clmap.pmap.get(pcu)))
    mcuUtil = count(spade.mcus.map(pcu => mp.clmap.pmap.get(pcu)))
    scuUtil = count(spade.scus.map(pcu => mp.clmap.pmap.get(pcu)))
    ocuUtil = count(spade.ocus.map(pcu => mp.clmap.pmap.get(pcu)))
    mcUtil = count(spade.mcs.map(pcu => mp.clmap.pmap.get(pcu)))
    slinkUtil = count(spade.sbs.map(sb => sb.sins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))
    vlinkUtil = count(spade.sbs.map(sb => sb.vins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))
    clinkUtil = count(spade.sbs.map(sb => sb.cins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))
    totalRegUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => regUsed(pcu)))
    totalStageUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => stageUsed(pcu)))
    totalSBufUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => sBufUsed(pcu)))
    totalVBufUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => vBufUsed(pcu)))
    totalSinPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => sinPinUsed(pcu)))
    totalSoutPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => soutPinUsed(pcu)))
    totalVinPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => vinPinUsed(pcu)))
    totalVoutPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => voutPinUsed(pcu)))
    totalCinPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => cinPinUsed(pcu)))
    totalCoutPinUtil = count(spade.cus.filter(pcu => mp.clmap.pmap.contains(pcu)).map(pcu => coutPinUsed(pcu)))

    //var totRegs = 0
    //var totAlus = 0
    //var totSrams = 0
    //design.top.innerCUs.foreach { inner =>
      //val parentIter = inner.ancestors.drop(1).map{ an => iterOf(an) }.reduce(_*_)
      //val cycle = cycleOf(inner) * parentIter 
      //activeCycle += inner -> cycle
      //val numFUs = inner.stages.size 
      //var numRegs = inner.stages.map { stage => stage.prs.size }.reduce{_+_}
      //numRegs += inner.stages.last.liveOuts.size * (numPStage - inner.stages.size) // Liveout Regs that needs to propogate to the end of the stage
      //totRegs += numRegs * numLanes
      //totAlus += inner.stages.size * numLanes 
      //totSrams += inner.vouts.size
    //}
    //val groups = design.top.innerCUs.groupBy { cu => cu.isInstanceOf[MemoryController] }
    //design.pirStat.numCUs(cu=groups(false).size, mc=groups(true).size)
    //design.pirStat.numRegs(totRegs, numPRegs * design.top.innerCUs.size)
    //design.pirStat.numStages(totAlus, numAlus * design.top.innerCUs.size)
    //design.pirStat.numSrams(totSrams, numSrams * design.top.innerCUs.size)
  } 

  override def finPass = {
    logResults
    emitSummary
    emitDetail
    summary.close
    detail.close
    close
    super.finPass
  }

  def emitSummary = {
    val row = summary.addRow
    row += "App"           -> design.name
    row += "numRow"        -> spade.numRows
    row += "numCol"        -> spade.numCols
    row += "PCU Util"      -> pctUtil(pcuUtil)
    row += "MCU Util"      -> pctUtil(mcuUtil)
    row += "SCU Util"      -> pctUtil(scuUtil)
    row += "SwitchCU Util" -> pctUtil(ocuUtil)
    row += "MC Util"       -> pctUtil(mcUtil)
    row += "CLink Util"    -> pctUtil(clinkUtil)
    row += "SLink Util"    -> pctUtil(slinkUtil)
    row += "VLink Util"    -> pctUtil(vlinkUtil)
    row += "Total Reg Util"    -> pctUtil(totalRegUtil)
    row += "Total Stage Util"    -> pctUtil(totalStageUtil)
    row += "Total SFifo Util"    -> pctUtil(totalSBufUtil)
    row += "Total VFifo Util"    -> pctUtil(totalVBufUtil)
    row += "Total SinPin Util"    -> pctUtil(totalSinPinUtil)
    row += "Total SoutPin Util"    -> pctUtil(totalSoutPinUtil)
    row += "Total VinPin Util"    -> pctUtil(totalVinPinUtil)
    row += "Total VoutPin Util"    -> pctUtil(totalVoutPinUtil)
    row += "Total CinPin Util"    -> pctUtil(totalCinPinUtil)
    row += "Total CoutPin Util"    -> pctUtil(totalCoutPinUtil)
    summary.emitFile
  }

  def emitDetail = {
    spade.cus.foreach { cl =>
      val row = detail.addRow
      row += s"cu" -> s"$cl"
      row += s"regUtil" -> pctUtil(regUsed(cl))
      row += s"stageUtil" -> pctUtil(stageUsed(cl))
      row += s"SFifoUtil" -> pctUtil(sBufUsed(cl))
      row += s"VFifoUtil" -> pctUtil(vBufUsed(cl))
      row += s"SinPinUtil" -> pctUtil(sinPinUsed(cl))
      row += s"SoutPinUtil" -> pctUtil(soutPinUsed(cl))
      row += s"VinPinUtil" -> pctUtil(vinPinUsed(cl))
      row += s"VoutPinUtil" -> pctUtil(voutPinUsed(cl))
      row += s"CinPinUtil" -> pctUtil(cinPinUsed(cl))
      row += s"CoutPinUtil" -> pctUtil(coutPinUsed(cl))
    }
    detail.emitFile
  }

  def logResults = {
    import logger._
    spade.cus.foreach { cl =>
      logger.emitBlock(s"$cl") {
        regUsed.get(cl).foreach { util => dprintln(s"regs:$util")}
        stageUsed.get(cl).foreach { util => dprintln(s"stage:$util")}
        sBufUsed.get(cl).foreach { util => dprintln(s"sBuf:$util")}
        vBufUsed.get(cl).foreach { util => dprintln(s"vBuf:$util")}
        sinPinUsed.get(cl).foreach { util => dprintln(s"sinPin:$util")}
        soutPinUsed.get(cl).foreach { util => dprintln(s"soutPin:$util")}
        vinPinUsed.get(cl).foreach { util => dprintln(s"vinPin:$util")}
        voutPinUsed.get(cl).foreach { util => dprintln(s"voutPin:$util")}
        cinPinUsed.get(cl).foreach { util => dprintln(s"cinPin:$util")}
        coutPinUsed.get(cl).foreach { util => dprintln(s"coutPin:$util")}
      }
    }
    dprintln(s"pcuUtil=$pcuUtil")
    dprintln(s"mcuUtil=$mcuUtil")
    dprintln(s"scuUtil=$scuUtil")
    dprintln(s"ocuUtil=$ocuUtil")
    dprintln(s"mcUtil=$mcUtil")
    dprintln(s"slinkUtil=$slinkUtil")
    dprintln(s"vlinkUtil=$vlinkUtil")
    dprintln(s"clinkUtil=$clinkUtil")
    dprintln(s"totalRegUtil=$totalRegUtil")
    dprintln(s"totalStageUtil=$totalStageUtil")
    dprintln(s"totalSBufUtil=$totalSBufUtil")
    dprintln(s"totalVBufUtil=$totalVBufUtil")
  }

}
