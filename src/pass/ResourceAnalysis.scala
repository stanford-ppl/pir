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
  import spademeta._
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

  val regUsed = Map[PNode, Util]()
  val ctrUsed = Map[PNode, Util]()
  val stageUsed = Map[PNode, Util]()
  val sBufUsed = Map[PNode, Util]()
  val vBufUsed = Map[PNode, Util]()

  val sinPinUsed = Map[PNode, Util]()
  val soutPinUsed = Map[PNode, Util]()
  val vinPinUsed = Map[PNode, Util]()
  val voutPinUsed = Map[PNode, Util]()
  val cinPinUsed = Map[PNode, Util]()
  val coutPinUsed = Map[PNode, Util]()

  var pcuUtil = Util.empty
  var mcuUtil = Util.empty
  var ocuUtil = Util.empty
  var scuUtil = Util.empty
  var mcUtil = Util.empty
  var slinkUtil = Util.empty
  var vlinkUtil = Util.empty
  var clinkUtil = Util.empty
  var totalRegUtil = Util.empty
  var totalCtrUtil = Util.empty
  var totalStageUtil = Util.empty
  var totalSBufUtil = Util.empty
  var totalVBufUtil = Util.empty
  var totalSinPinUtil = Util.empty
  var totalSoutPinUtil = Util.empty
  var totalVinPinUtil = Util.empty
  var totalVoutPinUtil = Util.empty
  var totalCinPinUtil = Util.empty
  var totalCoutPinUtil = Util.empty

  def sum(list:List[Util]):Util = list.reduceOption[Util]{ _ + _ }.getOrElse(Util(0,0))

  def count(list:List[_]):Util = {
    sum(list.map {
      case x:List[_] => count(x)
      case (x:Option[_]) => Util(if (x.isEmpty) 0 else 1, 1)
      case x:Util => x
    })
  }

  def collectRegUtil(pne:PNE) = pne match {
    case pne:PCU =>
      regUsed += pne -> count(pne.stages.map { pstage => pstage.prs.map { ppr => mp.fimap.get(ppr.in) } }) * parOf(pne)
    case pne =>
  }

  def collectCtrUtil(pne:PNE) = pne match {
    case pne:PCU =>
      ctrUsed += pne -> count(pne.ctrs.map { pctr => mp.ctmap.pmap.get(pctr) })
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
    row += "PCU Util"      -> pcuUtil.toPct
    row += "MCU Util"      -> mcuUtil.toPct
    row += "SCU Util"      -> scuUtil.toPct
    row += "SwitchCU Util" -> ocuUtil.toPct
    row += "MC Util"       -> mcUtil.toPct
    row += "CLink Util"    -> clinkUtil.toPct
    row += "SLink Util"    -> slinkUtil.toPct
    row += "VLink Util"    -> vlinkUtil.toPct
    row += "Total Reg Util"    -> totalRegUtil.toPct
    row += "Total Stage Util"    -> totalStageUtil.toPct
    row += "Total SFifo Util"    -> totalSBufUtil.toPct
    row += "Total VFifo Util"    -> totalVBufUtil.toPct
    row += "Total SinPin Util"    -> totalSinPinUtil.toPct
    row += "Total SoutPin Util"    -> totalSoutPinUtil.toPct
    row += "Total VinPin Util"    -> totalVinPinUtil.toPct
    row += "Total VoutPin Util"    -> totalVoutPinUtil.toPct
    row += "Total CinPin Util"    -> totalCinPinUtil.toPct
    row += "Total CoutPin Util"    -> totalCoutPinUtil.toPct
    summary.emitFile
  }

  def emitDetail = {
    spade.cus.foreach { cl =>
      val row = detail.addRow
      row += s"cu" -> s"$cl"
      row += s"regUtil" -> regUsed(cl).toPct
      row += s"stageUtil" -> stageUsed(cl).toPct
      row += s"SFifoUtil" -> sBufUsed(cl).toPct
      row += s"VFifoUtil" -> vBufUsed(cl).toPct
      row += s"SinPinUtil" -> sinPinUsed(cl).toPct
      row += s"SoutPinUtil" -> soutPinUsed(cl).toPct
      row += s"VinPinUtil" -> vinPinUsed(cl).toPct
      row += s"VoutPinUtil" -> voutPinUsed(cl).toPct
      row += s"CinPinUtil" -> cinPinUsed(cl).toPct
      row += s"CoutPinUtil" -> coutPinUsed(cl).toPct
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

case class Util(used:Int, total:Int) {
  def + (ut2:Util):Util = {
    val Util(u2, t2) = ut2
    Util(used + u2, total + t2)
  }
  def *(factor:Int):Util = {
    Util(used * factor, total * factor)
  }
  def toPct:Float = {
    if (total==0) -1 else used.toFloat / total
  }
}
object Util {
  def empty = Util(-1,-1)
}
