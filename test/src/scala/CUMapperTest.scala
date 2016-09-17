package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph._
import pir.graph.enums._
import plasticine.main._
import plasticine.config.Config0
import pir.plasticine.graph.{ComputeUnit => PCU, Top => PTop, SwitchBoxes}
import pir.graph.mapper._
import pir.graph.traversal._
import scala.language.reflectiveCalls

import org.scalatest._
import scala.util.{Try, Success, Failure}

class CUMapperTest extends UnitTest {

  "CUMapper Test1: Point-to-point connection" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.fill(8)(Scalar())
      val vts = List.fill(2)(Vector())
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
        CU.scalarOut(sls(0)) 
        CU.scalarOut(sls(1)) 
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        //CU.vecOut(vts(1)) 
        CU.scalarOut(sls(6))
        CU.scalarOut(sls(7))
      }
      val c2 = Pipeline("c2", top, Nil){ implicit CU => 
        CU.scalarIn(sls(2))
        CU.scalarIn(sls(3))
        CU.scalarIn(sls(4))
        CU.scalarIn(sls(5))
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c3 = Pipeline("c3", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        //CU.vecIn(vts(1))
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c4 = Pipeline("c4", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        CU.scalarIn(sls(1))
        CU.scalarOut(sls(2))
        CU.scalarOut(sls(3))
        CU.scalarOut(sls(4))
        CU.scalarOut(sls(5))
      }
      val cus = c0::c1::c2::c3::c4::Nil

      // PNodes
      override val arch = new Spade {
        val numLanes = 4
        val numRCUs = 5
        val numVins = 2
        val numRegs = 10
        val rcus = List.tabulate(numRCUs) { i =>
          val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox, ptr) =
            Config0.genFields[PCU](numRegs, 0, numVins, numLanes)
          val c = PCU(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox)
          c
        } 
        val ttcus = Nil
        val top = PTop(Nil, Nil, Nil, Nil)
        val wordWidth = 32

        /* Network Constrain */ 
        rcus(1).vins(0) <== rcus(0).vout 
        rcus(1).vins(1) <== rcus(0).vout
        rcus(2).vins(0) <== rcus(1).vout 
        rcus(2).vins(1) <== rcus(4).vout
        rcus(3).vins(0) <== rcus(0).vout 
        rcus(3).vins(1) <== rcus(1).vout
        rcus(4).vins(0) <== rcus(0).vout 
        rcus(4).vins(1) <== rcus(0).vout
      }
      val pcus = arch.rcus

      // Mapping
      val soMapper = new ScalarOutMapper()
      val viMapper = new VecInMapper()
      val mapper = new CUMapper(soMapper, viMapper)
      Try {
        mapper.mapCUs(pcus, cus, PIRMap.empty, (m:PIRMap) => m)
      } match {
        case Success(mapping) =>
          new CUDotPrinter("TestPCU1.dot").print(pcus, cus, mapping)
        case Failure(e) =>
          new CUDotPrinter("TestPCU1.dot").print(pcus, cus)
          throw e
      }
      // Printer
    }
  }

  "Test2: SwitchBox Connection" should "success" taggedAs(WIP) in {
    new Design {
      // Nodes

      // PNodes
      override val arch = new Spade {
        val numLanes = 4
        val numRowCUs = 4
        val numColCUs = 4
        val numRCUs = numRowCUs * numColCUs
        val numVins = 4
        val numRegs = 20
        val wordWidth = 32
        val top = PTop(Nil, Nil, Nil, Nil)
        val ttcus = Nil
        val scale = 4
        val sbs = SwitchBoxes(numRowCUs+1, numColCUs+1, numLanes)
        for (i <- 0 until sbs.size) {
          for (j <- 0 until sbs.head.size) {
            coordOf(sbs(i)(j)) = (i*scale-scale/2, j*scale-scale/2)
          }
        }
        val rcus = {
          val cus = List.tabulate(numRowCUs, numColCUs) { case (i, j) =>
            val (regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox, ptr) =
              Config0.genFields[PCU](numRegs, 0, numVins, numLanes)
            val c = PCU(regs, srams, ctrs, scalarIns, scalarOuts, vecIns, vecOut, stages, ctrlBox)
            coordOf(c) = (i*scale,j*scale)
            c
          }
          /* Network Constrain */ 
          for (i <- 0 until numRowCUs) {
            for (j <- 0 until numColCUs) {
              // CU to CU (Horizontal)
              if (i!=numRowCUs-1)
                cus(i+1)(j).vins(0) <== cus(i)(j).vout
              // CU to CU (Vertical)
              if (j!=numColCUs-1)
                cus(i)(j+1).vins(2) <== cus(i)(j).vout
            }
          }
          for (i <- 0 until numRowCUs+1) {
            for (j <- 0 until numColCUs+1) {
              // SB to SB (Horizontal)
              if (i!=numRowCUs) {
                sbs(i+1)(j).vins(2) <== sbs(i)(j).vouts(1)
                sbs(i)(j).vins(5) <== sbs(i+1)(j).vouts(4)
              }
              // SB to SB (Vertical)
              if (j!=numColCUs) {
                sbs(i)(j+1).vins(0) <== sbs(i)(j).vouts(2)
                sbs(i)(j).vins(4) <== sbs(i)(j+1).vouts(0)
              }
            }
          }
          for (i <- 0 until numRowCUs) {
            for (j <- 0 until numColCUs) {
              // SB to CU (NW -> SE)
              cus(i)(j).vins(1) <== sbs(i)(j).vouts(3)
              // SB to CU (SW -> NE)
              cus(i)(j).vins(3) <== sbs(i)(j+1).vouts(5)
              // CU to SB (SW -> NE)
              sbs(i+1)(j).vins(3) <== cus(i)(j).vout
              // CU to SB (NW -> SE)
              sbs(i+1)(j+1).vins(1) <== cus(i)(j).vout
            }
          }
          cus.flatten
        }

      }
      val pcus = arch.rcus
      val sbs = arch.sbs.flatten

      // Mapping
      //val soMapper = new ScalarOutMapper()
      //val viMapper = new VecInMapper()
      //val mapper = new CUMapper(soMapper, viMapper)
      //Try {
      //  mapper.mapCUs(pcus, cus, PIRMap.empty, (m:PIRMap) => m)
      //} match {
      //  case Success(mapping) =>
      //    new CUDotPrinter("TestPCU1.dot").print(pcus, cus, mapping)
      //  case Failure(e) =>
      //    new CUDotPrinter("TestPCU1.dot").print(pcus, cus)
      //    throw e
      //}
      // Printer
      new CUDotPrinter("TestPCU2.dot").print(pcus, sbs)
    }
  }

}

