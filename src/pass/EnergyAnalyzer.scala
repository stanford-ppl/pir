package pir.pass
import pir.node._
import pir._
import pirc.util._
import pir.mapper.{StageMapper, PIRMap, RegAlloc}
import pir.util.typealias._
import pir.codegen.{Logger, CSVPrinter, Row}
import spade.util._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Queue
import Math._

class EnergyAnalyzer(implicit design: PIR) extends Pass {
  import pirmeta._
  import spademeta._
  import design.resourceAnalyzer._

  def shouldRun = design.pirMapping.succeeded

  val summary = new CSVPrinter {
    override lazy val stream = newStream(Config.outDir, s"Energy.csv", append=true)
  }

  val detail = new CSVPrinter {
    override lazy val stream = newStream(s"EnergyDetail.csv", append=false)
  }

  val logger = new Logger {
    override lazy val stream = newStream(s"EnergyAnalyzer.log")
  }

  lazy val mp = design.mapping.get
  override lazy val arch = design.arch.asInstanceOf[SwitchNetwork]
  import arch._

  def timeOf(n:CL):Double = {
    totalCycleOf(n) * 1.0 / arch.clockFrequency
  }

  def timeOf(n:PRT):Double = {
    n match {
      case n:PCL => mp.pmmap.get(n).fold(0.0) { cl => timeOf(cl) }
    }
  }

  def readTime(n:CL):Double = n match {
    case n:MP => timeOf(n.sram.consumer)
    case n => timeOf(n)
  }

  def writeTime(n:CL):Double = n match {
    case n:MP => timeOf(n.sram.producer)
    case n => timeOf(n)
  }

  val regEnergy = Map[PNode, Double]()
  val ctrEnergy = Map[PNode, Double]()
  val sramEnergy = Map[PNode, Double]()
  val sBufEnergy = Map[PNode, Double]()
  val vBufEnergy = Map[PNode, Double]()
  val fuEnergy = Map[PNode, Double]()
  val prtEnergy = Map[PNode, Double]()

  val regUnitPower = 0.12856 * 2 // mW //read and write
  val ctrUnitPower = 0.12856 * 2 // mW //read and write
  val sramReadUnitPower = 83.064832 // mW
  val sramWriteUnitPower = 83.064832 // mW
  val sBufUnitPower = 0.610175 * 2 // read and write
  val vBufUnitPower = 9.7628 * 2 // read and write
  val fuUnitPower = 3.3726 // mW

  def compEnergy(prt:PCU):Unit = {
    prt match {
      case prt:PMCU =>
        mp.pmmap.get(prt).fold {
          regEnergy += prt -> 0 
          ctrEnergy += prt -> 0 
          fuEnergy += prt -> 0
          sBufEnergy += prt -> 0 
          vBufEnergy += prt -> 0
          sramEnergy += prt -> 0
        } { cl =>
          val mp = cl.asMP
          val rt = readTime(mp) 
          val wt = writeTime(mp)
          val at = (rt + wt)/2
          regEnergy += prt -> regUnitPower * regUsed(prt).used * at
          ctrEnergy += prt -> mp.cchains.map {
            case cc if forRead(cc) => ctrUnitPower * rt * cc.counters.size
            case cc if forWrite(cc) => ctrUnitPower * wt * cc.counters.size
          }.sum
          fuEnergy += prt -> mp.stages.map {
            case stage if forRead(stage) => fuUnitPower * rt
            case stage if forWrite(stage) => fuUnitPower * wt
          }.sum
          sBufEnergy += prt -> mp.sfifos.map {
            case fifo if forRead(fifo) => sBufUnitPower * rt
            case fifo if forWrite(fifo) => sBufUnitPower * wt
          }.sum
          vBufEnergy += prt -> mp.vfifos.map {
            case fifo if forRead(fifo) => vBufUnitPower * rt
            case fifo if forWrite(fifo) => vBufUnitPower * wt
          }.sum
          sramEnergy += prt -> sramReadUnitPower * rt
          sramEnergy += prt -> sramWriteUnitPower * wt
        }
      case prt =>
        regEnergy += prt -> regUnitPower * regUsed(prt).used * timeOf(prt)
        ctrEnergy += prt -> ctrUnitPower * ctrUsed(prt).used * timeOf(prt)
        fuEnergy += prt -> fuUnitPower * fuUsed(prt).used * timeOf(prt)
        sBufEnergy += prt -> sBufUnitPower * sBufUsed(prt).used * timeOf(prt)
        vBufEnergy += prt -> vBufUnitPower * vBufUsed(prt).used * timeOf(prt)
        sramEnergy += prt -> 0
    }
    prtEnergy += prt -> (regEnergy(prt) + 
                        ctrEnergy(prt) + 
                        fuEnergy(prt) + 
                        sBufEnergy(prt) + 
                        vBufEnergy(prt) + 
                        sramEnergy(prt))
  }

  def compEnergy(psb:PSB):Unit = {
    prtEnergy += psb -> (psb.outs.map { pout => 
      mp.mkmap.get(pout).fold(0.0) { out =>
        out match {
          case out:VO => arch.numLanes * regUnitPower * writeTime(out.ctrler)
          case out:SO => regUnitPower * timeOf(out.ctrler)
          case out:OP => 0.0
        }
      }
    }).sum
  }
  
  def compEnergy(prt:PRT):Unit = {
    prt match {
      case prt:PCU => compEnergy(prt)
      case prt:PSB => compEnergy(prt)
      case prt:PTop => prtEnergy += prt -> 0
      case prt:PMC => prtEnergy += prt -> 0
    }
  }

  addPass {
    assert(design.latencyAnalyzer.hasRun)
    arch.prts.foreach { prt =>
      compEnergy(prt)
    }
  } 

  override def finPass = {
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
    row += "totalRegEnergy" -> regEnergy.map { case (n, e) => e }.sum
    row += "totalCtrEnergy" -> ctrEnergy.map { case (n, e) => e }.sum
    row += "totalSFifoEnergy" -> sBufEnergy.map { case (n, e) => e }.sum
    row += "totalVFifoEnergy" -> vBufEnergy.map { case (n, e) => e }.sum
    row += "totalSramEnergy" -> sramEnergy.map { case (n, e) => e }.sum
    row += "totalFUEnergy" -> fuEnergy.map { case (n, e) => e }.sum
    row += "totalPCUEnergy" -> pcus.map { cu => prtEnergy(cu) }.sum
    row += "totalPMUEnergy" -> mcus.map { cu => prtEnergy(cu) }.sum
    row += "totalSCUEnergy" -> scus.map { cu => prtEnergy(cu) }.sum
    row += "totalOCUEnergy" -> ocus.map { cu => prtEnergy(cu) }.sum
    row += "totalSwitchEnergy" -> sbs.map { sb => prtEnergy(sb) }.sum
    val totalEnergy = prts.map(prt => prtEnergy(prt)).sum
    row += "totalEnergy" -> totalEnergy
    row += "totalPower" -> totalEnergy / timeOf(design.top) 
    summary.emitFile
  }

  def emitDetail = {
    cus.foreach { cl =>
      val row = detail.addRow
      row += s"cu" -> s"$cl"
      row += s"regEnergy" -> regEnergy(cl)
      row += s"ctrEnergy" -> ctrEnergy(cl)
      row += s"sBufEnergy" -> sBufEnergy(cl)
      row += s"vBufEnergy" -> vBufEnergy(cl)
      row += s"sramEnergy" -> sramEnergy(cl)
    }
    detail.emitFile
  }

}
