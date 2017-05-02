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

class PowerAnalyzer(implicit design: Design) extends Pass {
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
    override lazy val stream = newStream(s"PowerAnalyzer.log")
  }

  lazy val mp = design.mapping.get
  override lazy val spade = design.arch.asInstanceOf[SwitchNetwork]


  def timeOf(n:PNode):Double = n match {
    case n:PCL =>
      mp.clmap.pmap.get(n).fold(0.0) { cl => cycleOf(cl) * 1.0 / spade.clockFrequency }
    case _ => 0.0
  }

  val regEnergy = Map[PNode, Double]()
  val ctrEnergy = Map[PNode, Double]()
  val sramEnergy = Map[PNode, Double]()
  val sBufEnergy = Map[PNode, Double]()
  val vBufEnergy = Map[PNode, Double]()
  val aluEnergy = Map[PNode, Double]()
  val pneEnergy = Map[PNode, Double]()

  val regUnitEnergy = 1
  val ctrUnitEnergy = 1
  val sramUnitEnergy = 1
  val sBufUnitEnergy = 1
  val vBufUnitEnergy = 1
  val aluUnitEnergy = 1

  def writeTime(mp:MP):Double = {
    mp.mem.producer match {
      case mp:MP => writeTime(mp) 
      case cl:CL => cycleOf(cl)
    }
  }

  def readTime(mp:MP):Double = {
    mp.mem.consumer match {
      case mp:MP => readTime(mp) 
      case cl:CL => cycleOf(cl)
    }
  }

  def compEnergy(pne:PCU):Unit = {
    pne match {
      case pne:PMCU =>
        mp.clmap.pmap.get(pne).fold {
          regEnergy += pne -> 0 
          ctrEnergy += pne -> 0 
          aluEnergy += pne -> 0
          sBufEnergy += pne -> 0 
          vBufEnergy += pne -> 0
          sramEnergy += pne -> 0
        } { cl =>
          val mp = cl.asMP
          val rt = readTime(mp) 
          val wt = writeTime(mp)
          val at = (rt + wt)/2
          regEnergy += pne -> regUnitEnergy * regUsed(pne).used * at
          ctrEnergy += pne -> mp.cchains.map {
            case cc if forRead(cc) => ctrUnitEnergy * rt * cc.counters.size
            case cc if forWrite(cc) => ctrUnitEnergy * wt * cc.counters.size
          }.sum
          aluEnergy += pne -> mp.stages.map {
            case stage if forRead(stage) => aluUnitEnergy * rt
            case stage if forWrite(stage) => aluUnitEnergy * wt
          }.sum
          sBufEnergy += pne -> mp.sfifos.map {
            case fifo if forRead(fifo) => sBufUnitEnergy * rt
            case fifo if forWrite(fifo) => sBufUnitEnergy * wt
          }.sum
          vBufEnergy += pne -> mp.vfifos.map {
            case fifo if forRead(fifo) => vBufUnitEnergy * rt
            case fifo if forWrite(fifo) => vBufUnitEnergy * wt
          }.sum
          sramEnergy += pne -> sramUnitEnergy * at
        }
      case pne =>
        regEnergy += pne -> regUnitEnergy * regUsed(pne).used * timeOf(pne)
        ctrEnergy += pne -> ctrUnitEnergy * ctrUsed(pne).used * timeOf(pne)
        aluEnergy += pne -> aluUnitEnergy * stageUsed(pne).used * timeOf(pne) * parOf(pne)
        sBufEnergy += pne -> sBufUnitEnergy * sBufUsed(pne).used * timeOf(pne)
        vBufEnergy += pne -> vBufUnitEnergy * vBufUsed(pne).used * timeOf(pne)
    }
    pneEnergy += pne -> (regEnergy(pne) + 
                        ctrEnergy(pne) + 
                        aluEnergy(pne) + 
                        sBufEnergy(pne) + 
                        vBufEnergy(pne) + 
                        sramEnergy(pne))
  }

  def compEnergy(psb:PSB):Unit = {
    pneEnergy += psb -> (psb.outs.map { pout => mp.mkmap.get(pout).fold(0.0) { out =>
        out match {
          case out:VO => spade.numLanes * regUnitEnergy * cycleOf(out.ctrler)
          case out:SO => regUnitEnergy * cycleOf(out.ctrler)
          case out:OP => 0.0
        }
      }
    }).sum
  }
  
  def compEnergy(pne:PNE):Unit = {
    pne match {
      case pne:PCU => compEnergy(pne)
      case pne:PSB => compEnergy(pne)
      case pne:PTop =>
    }
  }

  override def traverse:Unit = {
    spade.pnes.foreach { pne =>
      compEnergy(pne)
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
    row += "totalALUEnergy" -> aluEnergy.map { case (n, e) => e }.sum
    row += "totalPCUEnergy" -> spade.pcus.map { cu => pneEnergy(cu) }.sum
    row += "totalPMUEnergy" -> spade.mcus.map { cu => pneEnergy(cu) }.sum
    row += "totalSCUEnergy" -> spade.scus.map { cu => pneEnergy(cu) }.sum
    row += "totalOCUEnergy" -> spade.ocus.map { cu => pneEnergy(cu) }.sum
    row += "totalSwitchEnergy" -> spade.sbs.map { sb => pneEnergy(sb) }.sum
    val totalEnergy = spade.pnes.map(pne => pneEnergy(pne)).sum
    row += "totalEnergy" -> totalEnergy
    row += "totalPower" -> totalEnergy / cycleOf(design.top) 
    summary.emitFile
  }

  def emitDetail = {
    spade.cus.foreach { cl =>
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
