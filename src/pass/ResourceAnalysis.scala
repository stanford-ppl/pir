package pir.pass
import pir.graph._
import pir._
import pir.util.misc._
import pir.exceptions.PIRException
import pir.mapper.{StageMapper, PIRMap, RegAlloc}
import pir.spade.main.SwitchNetwork
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

  val activeCycle = Map[PNode, Long]()

  val regUsed = Map[PNode, Util]()
  val ctrUsed = Map[PNode, Util]()
  val fuUsed = Map[PNode, Util]()
  val sBufUsed = Map[PNode, Util]()
  val vBufUsed = Map[PNode, Util]()

  val sinPinUsed = Map[PNode, Util]()
  val soutPinUsed = Map[PNode, Util]()
  val vinPinUsed = Map[PNode, Util]()
  val voutPinUsed = Map[PNode, Util]()
  val cinPinUsed = Map[PNode, Util]()
  val coutPinUsed = Map[PNode, Util]()

  val sChannelUsed = Map[PNode, Util]()
  val vChannelUsed = Map[PNode, Util]()
  val cChannelUsed = Map[PNode, Util]()

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
  var totalFUUtil = Util.empty

  var totalSBufUtil = Util.empty
  var totalVBufUtil = Util.empty
  var totalSinPinUtil = Util.empty
  var totalSoutPinUtil = Util.empty
  var totalVinPinUtil = Util.empty
  var totalVoutPinUtil = Util.empty
  var totalCinPinUtil = Util.empty
  var totalCoutPinUtil = Util.empty

  var totalSChannelUtil = Util.empty
  var totalVChannelUtil = Util.empty
  var totalCChannelUtil = Util.empty

  def parOf(prt:PCL):Int = {
    mp.pmmap.get(prt).fold(-1) { cl => pirmeta.parOf(cl) }
  }

  def sum(list:List[Util]):Util = list.reduceOption[Util]{ _ + _ }.getOrElse(Util.empty)

  def count(list:List[_]):Util = {
    sum(list.map {
      case x:List[_] => count(x)
      case (x:Option[_]) => Util(used=if (x.isEmpty) 0 else 1, total=1) // Not Mapped
      case x:Util => x
    })
  }

  def collectRegUtil(prt:PRT) = prt match {
    case prt:PCU if mp.pmmap.contains(prt) =>
      regUsed += prt -> count(prt.stages.map { pstage => pstage.prs.map { ppr => mp.fimap.get(ppr.in) } }).map {
        case (used, total) => (used * parOf(prt), total * prt.param.numLanes)
      }
    case prt =>
      regUsed += prt -> Util.empty
  }

  def collectFUUtil(prt:PRT) = prt match {
    case prt:PCU if mp.pmmap.contains(prt) =>
      fuUsed += prt -> count(prt.stages.map { pst => mp.pmmap.get(pst) }).map {
        case (used, total) => (used * parOf(prt), total * prt.param.numLanes)
      }
    case prt =>
      fuUsed += prt -> Util.empty
  }

  def collectCtrUtil(prt:PRT) = prt match {
    case prt:PCU =>
      ctrUsed += prt -> count(prt.ctrs.map { pctr => mp.pmmap.get(pctr) })
    case prt =>
      ctrUsed += prt -> Util.empty
  }

  def collectBufUtil(map:Map[PNode, Util], prt:PRT)(bufs:PCU => List[PLMem]) = prt match {
    case prt:PCU =>
      map += prt -> count(bufs(prt).map { buf => mp.pmmap.get(buf) })
    case prt =>
      map += prt -> Util.empty
  }

  def collectPinUtil(map: Map[PNode, Util], prt:PRT)(ios:PRT => List[PGIO[PModule]]) = {
    map += prt -> count(ios(prt).map { 
      case io:PGI[_] => mp.vimap.get(io)
      case io:PGO[_] => mp.vomap.get(io)
    })
  }

  def channels(ins:List[PGI[PModule]]) = {
    ins.filter { _.fanIns.forall { _.src.isInstanceOf[PSB] } }
  }

  def collectChannelUtil(map: Map[PNode, Util], prt:PRT)(ins:PRT => List[PGI[PModule]]) = {
    prt match {
      case prt:PSB =>
        map += prt -> count(channels(ins(prt)).map { in => mp.vimap.get(in) })
      case prt =>
    }
  }

  addPass {
    spade.prts.foreach { cl =>
      collectRegUtil(cl)
      collectCtrUtil(cl)
      collectFUUtil(cl)
      collectBufUtil(sBufUsed, cl) { prt => prt.sbufs }
      collectBufUtil(vBufUsed, cl) { prt => prt.vbufs }
      collectPinUtil(sinPinUsed, cl) { prt => prt.sins }
      collectPinUtil(vinPinUsed, cl) { prt => prt.vins }
      collectPinUtil(cinPinUsed, cl) { prt => prt.cins }
      collectPinUtil(soutPinUsed, cl) { prt => prt.souts }
      collectPinUtil(voutPinUsed, cl) { prt => prt.vouts }
      collectPinUtil(coutPinUsed, cl) { prt => prt.couts }
      //collectChannelUtil(sChannelUsed, cl) { prt => prt.sins }
      //collectChannelUtil(vChannelUsed, cl) { prt => prt.vins }
      //collectChannelUtil(cChannelUsed, cl) { prt => prt.cins }
    }
    pcuUtil = count(spade.pcus.map(pcu => mp.pmmap.get(pcu)))
    mcuUtil = count(spade.mcus.map(pcu => mp.pmmap.get(pcu)))
    scuUtil = count(spade.scus.map(pcu => mp.pmmap.get(pcu)))
    ocuUtil = count(spade.ocus.map(pcu => mp.pmmap.get(pcu)))
    mcUtil = count(spade.mcs.map(pcu => mp.pmmap.get(pcu)))

    slinkUtil = count(spade.sbs.map(sb => sb.sins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))
    vlinkUtil = count(spade.sbs.map(sb => sb.vins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))
    clinkUtil = count(spade.sbs.map(sb => sb.cins.filter{_.connectedToSwitch}.map( in => mp.fimap.get(in)) ))

    totalRegUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => regUsed(pcu)))
    totalFUUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => fuUsed(pcu)))
    totalSBufUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => sBufUsed(pcu)))
    totalVBufUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => vBufUsed(pcu)))
    totalSinPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => sinPinUsed(pcu)))
    totalSoutPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => soutPinUsed(pcu)))
    totalVinPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => vinPinUsed(pcu)))
    totalVoutPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => voutPinUsed(pcu)))
    totalCinPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => cinPinUsed(pcu)))
    totalCoutPinUtil = count(spade.cus.filter(pcu => mp.pmmap.contains(pcu)).map(pcu => coutPinUsed(pcu)))

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
    //val (mcs, cus) = design.top.innerCUs.partition { cu => cu.isInstanceOf[MemoryController] }
    //design.pirStat.numCUs(cu=cus.size, mc=mcs.size)
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
    val spade = design.arch.asInstanceOf[SwitchNetwork]
    import spade.param._
    val row = summary.addRow
    row += "App"           -> design.name
    row += "numRow"        -> numRows
    row += "numCol"        -> numCols
    row += "PCU Util"      -> pcuUtil.toPct
    row += "MCU Util"      -> mcuUtil.toPct
    row += "SCU Util"      -> scuUtil.toPct
    row += "SwitchCU Util" -> ocuUtil.toPct
    row += "MC Util"       -> mcUtil.toPct
    row += "CLink Util"    -> clinkUtil.toPct
    row += "SLink Util"    -> slinkUtil.toPct
    row += "VLink Util"    -> vlinkUtil.toPct

    row += "Total Reg Util"    -> totalRegUtil.toPct
    row += "Total FU Util"    -> totalFUUtil.toPct
    row += "Total SFifo Util"    -> totalSBufUtil.toPct
    row += "Total VFifo Util"    -> totalVBufUtil.toPct
    row += "Total SinPin Util"    -> totalSinPinUtil.toPct
    row += "Total SoutPin Util"    -> totalSoutPinUtil.toPct
    row += "Total VinPin Util"    -> totalVinPinUtil.toPct
    row += "Total VoutPin Util"    -> totalVoutPinUtil.toPct
    row += "Total CinPin Util"    -> totalCinPinUtil.toPct
    row += "Total CoutPin Util"    -> totalCoutPinUtil.toPct

    row += "Total Reg Used"    -> totalRegUtil.used
    row += "Total FU Used"    -> totalFUUtil.used
    row += "Total SFifo Used"    -> totalSBufUtil.used
    row += "Total VFifo Used"    -> totalVBufUtil.used
    row += "Total SinPin Used"    -> totalSinPinUtil.used
    row += "Total SoutPin Used"    -> totalSoutPinUtil.used
    row += "Total VinPin Used"    -> totalVinPinUtil.used
    row += "Total VoutPin Used"    -> totalVoutPinUtil.used
    row += "Total CinPin Used"    -> totalCinPinUtil.used
    row += "Total CoutPin Used"    -> totalCoutPinUtil.used
    summary.emitFile
  }

  def emitDetail = {
    spade.cus.foreach { cl =>
      val row = detail.addRow
      row += s"cu" -> s"$cl"
      row += s"regUsed" -> regUsed(cl).used
      row += s"ctrUsed" -> ctrUsed(cl).used
      row += s"fuUsed" -> fuUsed(cl).used
      row += s"SFifoUsed" -> sBufUsed(cl).used
      row += s"VFifoUsed" -> vBufUsed(cl).used
      row += s"SinPinUsed" -> sinPinUsed(cl).used
      row += s"SoutPinUsed" -> soutPinUsed(cl).used
      row += s"VinPinUsed" -> vinPinUsed(cl).used
      row += s"VoutPinUsed" -> voutPinUsed(cl).used

      row += s"regUtil" -> regUsed(cl).toPct
      row += s"ctrUtil" -> ctrUsed(cl).toPct
      row += s"fuUtil" -> fuUsed(cl).toPct
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
    spade.cus.foreach { pcl =>
      val cl = mp.pmmap.get(pcl)
      logger.emitBlock(s"${quote(pcl)} -> $cl parOf(${quote(pcl)}) = ${parOf(pcl)}") {
        regUsed.get(pcl).foreach { util => dprintln(s"reg:$util")}
        ctrUsed.get(pcl).foreach { util => dprintln(s"ctr:$util")}
        fuUsed.get(pcl).foreach { util => dprintln(s"fu:$util")}
        sBufUsed.get(pcl).foreach { util => dprintln(s"sBuf:$util")}
        vBufUsed.get(pcl).foreach { util => dprintln(s"vBuf:$util")}
        sinPinUsed.get(pcl).foreach { util => dprintln(s"sinPin:$util")}
        soutPinUsed.get(pcl).foreach { util => dprintln(s"soutPin:$util")}
        vinPinUsed.get(pcl).foreach { util => dprintln(s"vinPin:$util")}
        voutPinUsed.get(pcl).foreach { util => dprintln(s"voutPin:$util")}
        cinPinUsed.get(pcl).foreach { util => dprintln(s"cinPin:$util")}
        coutPinUsed.get(pcl).foreach { util => dprintln(s"coutPin:$util")}
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
    dprintln(s"totalFUUtil=$totalFUUtil")
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
    if (total==0) 0 else used.toFloat / total
  }
  def map(func:(Int, Int) =>(Int,Int)) = {
    val (newUsed, newTotal) = func(used, total)
    Util(newUsed, newTotal)
  }
}
object Util {
  def empty = Util(0,0)
}
