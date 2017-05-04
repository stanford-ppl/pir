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
    override lazy val stream = newStream(Config.outDir, s"Power.csv", append=true)
  }

  val detail = new CSVPrinter {
    override lazy val stream = newStream(s"PowerDetail.csv", append=false)
  }

  val logger = new Logger {
    override lazy val stream = newStream(s"PowerAnalyzer.log")
  }

  lazy val mp = design.mapping.get
  override lazy val spade = design.arch.asInstanceOf[SwitchNetwork]

  val regPower = Map[PNode, Double]()
  val ctrPower = Map[PNode, Double]()
  val sramPower = Map[PNode, Double]()
  val sBufPower = Map[PNode, Double]()
  val vBufPower = Map[PNode, Double]()
  val fuPower = Map[PNode, Double]()
  val pnePower = Map[PNode, Double]()

  val regUnitPower = 0.12856 * 2 // mW //read and write
  val ctrUnitPower = 0.12856 * 2 // mW //read and write
  val sramReadUnitPower = 83.064832 // mW
  val sramWriteUnitPower = 83.064832 // mW
  val sBufUnitPower = 0.610175 * 2 // read and write
  val vBufUnitPower = 9.7628 * 2 // read and write
  val fuUnitPower = 3.3726 // mW

  var totalPCUPower = 0.0
  var totalMCUPower = 0.0
  var totalSCUPower = 0.0
  var totalOCUPower = 0.0

  val unitPCUPower = 857.8603 //mW
  val unitSCUPower = 31.4111 //mW
  val unitMCUPower = unitSCUPower + sramReadUnitPower + sramWriteUnitPower //mW
  val unitOCUPower = 89e-6 //mW

  def compPower(pne:PCU):Unit = {
    pne match {
      case pne:PCL =>
        mp.clmap.pmap.get(pne).fold {
          regPower += pne -> 0 
          ctrPower += pne -> 0 
          fuPower += pne -> 0
          sBufPower += pne -> 0 
          vBufPower += pne -> 0
          sramPower += pne -> 0
        } { cl =>
          regPower += pne -> regUnitPower * regUsed(pne).used
          ctrPower += pne -> ctrUnitPower * ctrUsed(pne).used
          fuPower += pne -> fuUnitPower * fuUsed(pne).used
          sBufPower += pne -> sBufUnitPower * sBufUsed(pne).used 
          vBufPower += pne -> vBufUnitPower * vBufUsed(pne).used 
          cl match {
            case mp:MP => sramPower += pne -> (sramReadUnitPower + sramWriteUnitPower)
            case mp => sramPower += pne -> 0
          }
        }
      case pne =>
    }
    pnePower += pne -> (regPower(pne) + 
                        ctrPower(pne) + 
                        fuPower(pne) + 
                        sBufPower(pne) + 
                        vBufPower(pne) + 
                        sramPower(pne))
  }

  def compPower(psb:PSB):Unit = {
    pnePower += psb -> (psb.outs.map { pout => 
      mp.mkmap.get(pout).fold(0.0) { out =>
        out match {
          case out:VO => spade.numLanes * regUnitPower
          case out:SO => regUnitPower
          case out:OP => 0.0
        }
      }
    }).sum
  }
  
  def compPower(pne:PNE):Unit = {
    pne match {
      case pne:PCU => compPower(pne)
      case pne:PSB => compPower(pne)
      case pne:PTop => pnePower += pne -> 0
      case pne:PMC => pnePower += pne -> 0
    }
  }

  override def traverse:Unit = {
    spade.pnes.foreach { pne =>
      compPower(pne)
    }
    DCPower
  } 

  override def finPass = {
    emitSummary
    emitDetail
    summary.close
    detail.close
    close
    super.finPass
  }

  def DCPower = {
    spade.pnes.map {
      case pne:PMCU if mp.clmap.pmap.contains(pne) => totalMCUPower += unitPCUPower
      case pne:PSCU if mp.clmap.pmap.contains(pne) => totalSCUPower += unitSCUPower
      case pne:POCU if mp.clmap.pmap.contains(pne) => totalOCUPower += unitOCUPower
      case pne:PCU if mp.clmap.pmap.contains(pne) => totalPCUPower += unitPCUPower
      case pne =>
    }
  }

  def emitSummary = {
    val row = summary.addRow
    val totalSwitchPower = spade.sbs.map { sb => pnePower(sb) }.sum
    row += "App"           -> design.name
    row += "totalRegPower" -> regPower.map { case (n, e) => e }.sum
    row += "totalCtrPower" -> ctrPower.map { case (n, e) => e }.sum
    row += "totalSFifoPower" -> sBufPower.map { case (n, e) => e }.sum
    row += "totalVFifoPower" -> vBufPower.map { case (n, e) => e }.sum
    row += "totalSramPower" -> sramPower.map { case (n, e) => e }.sum
    row += "totalFUPower" -> fuPower.map { case (n, e) => e }.sum
    row += "totalPCUPower" -> spade.pcus.map { cu => pnePower(cu) }.sum
    row += "totalPMUPower" -> spade.mcus.map { cu => pnePower(cu) }.sum
    row += "totalSCUPower" -> spade.scus.map { cu => pnePower(cu) }.sum
    row += "totalOCUPower" -> spade.ocus.map { cu => pnePower(cu) }.sum
    row += "totalSwitchPower" -> totalSwitchPower
    row += "totalPower" -> spade.pnes.map(pne => pnePower(pne)).sum
    row += "DCtotalPCUPower" -> totalPCUPower
    row += "DCtotalPMUPower" -> totalMCUPower
    row += "DCtotalSCUPower" -> totalSCUPower
    row += "DCtotalOCUPower" -> totalOCUPower
    row += "DCtotalPower" -> (totalPCUPower + totalOCUPower  + totalSCUPower + totalMCUPower + totalSwitchPower) 
    summary.emitFile
  }

  def emitDetail = {
    spade.cus.foreach { cl =>
      val row = detail.addRow
      row += s"cu" -> s"$cl"
      row += s"regPower" -> regPower(cl)
      row += s"ctrPower" -> ctrPower(cl)
      row += s"sBufPower" -> sBufPower(cl)
      row += s"vBufPower" -> vBufPower(cl)
      row += s"sramPower" -> sramPower(cl)
    }
    detail.emitFile
  }

}
