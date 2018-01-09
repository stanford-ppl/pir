package pir.pass

import pir._
import pir.util.typealias._

import spade._

import pirc._
import pirc.codegen._

import scala.collection.mutable.Map
import Math._

class PowerAnalyzer(implicit design: PIR) extends Pass {
  import pirmeta._
  import spademeta._
  import design.resourceAnalyzer._

  lazy val top = arch.top
  import top._

  def shouldRun = design.pirMapping.succeeded

  val summary = new CSVPrinter {
    override lazy val stream = newStream(Config.outDir, s"Power.csv", append=true)
  }

  val detail = new CSVPrinter {
    override lazy val stream = newStream(s"PowerDetail.csv", append=false)(design)
  }

  val logger = new Logger {
    override lazy val stream = newStream(s"PowerAnalyzer.log")(design)
  }

  lazy val mp = design.mapping.get

  val regPower = Map[PNode, Double]()
  val ctrPower = Map[PNode, Double]()
  val sramPower = Map[PNode, Double]()
  val sBufPower = Map[PNode, Double]()
  val vBufPower = Map[PNode, Double]()
  val fuPower = Map[PNode, Double]()
  val prtPower = Map[PNode, Double]()

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

  def compPower(prt:PCU):Unit = {
    prt match {
      case prt:PCL =>
        mp.pmmap.get(prt).fold {
          regPower += prt -> 0 
          ctrPower += prt -> 0 
          fuPower += prt -> 0
          sBufPower += prt -> 0 
          vBufPower += prt -> 0
          sramPower += prt -> 0
        } { cl =>
          regPower += prt -> regUnitPower * regUsed(prt).used
          ctrPower += prt -> ctrUnitPower * ctrUsed(prt).used
          fuPower += prt -> fuUnitPower * fuUsed(prt).used
          sBufPower += prt -> sBufUnitPower * sBufUsed(prt).used 
          vBufPower += prt -> vBufUnitPower * vBufUsed(prt).used 
          cl match {
            case mp:MP => sramPower += prt -> (sramReadUnitPower + sramWriteUnitPower)
            case mp => sramPower += prt -> 0
          }
        }
      case prt =>
    }
    prtPower += prt -> (regPower(prt) + 
                        ctrPower(prt) + 
                        fuPower(prt) + 
                        sBufPower(prt) + 
                        vBufPower(prt) + 
                        sramPower(prt))
  }

  def compPower(psb:PSB):Unit = {
    prtPower += psb -> (psb.outs.map { pout => 
      mp.mkmap.get(pout).fold(0.0) { out =>
        out match {
          case out:GO if out.isVector => arch.numLanes * regUnitPower
          case out:GO if out.isScalar => regUnitPower
          case out:O => 0.0
        }
      }
    }).sum
  }
  
  def compPower(prt:PRT):Unit = {
    prt match {
      case prt:PCU => compPower(prt)
      case prt:PSB => compPower(prt)
      case prt:PTop => prtPower += prt -> 0
      case prt:PMC => prtPower += prt -> 0
    }
  }

  addPass {
    //arch.prts.foreach { prt =>
      //compPower(prt)
    //}
    DCPower
  } 

  override def finPass = {
    emitSummary
    //emitDetail
    summary.close
    detail.close
    super.finPass
  }

  def DCPower = {
    prts.map {
      case prt:PMCU if mp.pmmap.contains(prt) => totalMCUPower += unitMCUPower
      case prt:PSCU if mp.pmmap.contains(prt) => totalSCUPower += unitSCUPower
      case prt:POCU if mp.pmmap.contains(prt) => totalOCUPower += unitOCUPower
      case prt:PCU if mp.pmmap.contains(prt) => totalPCUPower += unitPCUPower
      case prt =>
    }
  }

  def emitSummary = {
    val row = summary.addRow
    val totalSwitchPower = sbs.map { sb => prtPower.getOrElse(sb, 0.0) }.sum //TODO
    row += "App"           -> design.name
    row += "totalRegPower" -> regPower.map { case (n, e) => e }.sum
    row += "totalCtrPower" -> ctrPower.map { case (n, e) => e }.sum
    row += "totalSFifoPower" -> sBufPower.map { case (n, e) => e }.sum
    row += "totalVFifoPower" -> vBufPower.map { case (n, e) => e }.sum
    row += "totalSramPower" -> sramPower.map { case (n, e) => e }.sum
    row += "totalFUPower" -> fuPower.map { case (n, e) => e }.sum
    row += "totalPCUPower" -> pcus.map { cu => prtPower.getOrElse(cu, 0.0) }.sum //TODO remove getOrElse
    row += "totalPMUPower" -> pmus.map { cu => prtPower.getOrElse(cu, 0.0) }.sum
    row += "totalSCUPower" -> scus.map { cu => prtPower.getOrElse(cu, 0.0) }.sum
    row += "totalOCUPower" -> ocus.map { cu => prtPower.getOrElse(cu, 0.0) }.sum
    row += "totalSwitchPower" -> totalSwitchPower
    row += "totalPower" -> prts.map(prt => prtPower.getOrElse(prt, 0.0)).sum
    row += "DCtotalPCUPower" -> totalPCUPower
    row += "DCtotalPMUPower" -> totalMCUPower
    row += "DCtotalSCUPower" -> totalSCUPower
    row += "DCtotalOCUPower" -> totalOCUPower
    row += "DCtotalPower" -> (totalPCUPower + totalOCUPower  + totalSCUPower + totalMCUPower) 
    summary.emitFile
  }

  def emitDetail = {
    cus.foreach { cl =>
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
