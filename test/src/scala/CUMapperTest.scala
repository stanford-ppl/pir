package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph._
import pir.graph.enums._
import plasticine.main._
import plasticine.config._
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
        val sbs = Nil 
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
        val switchBoxes = SwitchBoxes(numRowCUs+1, numColCUs+1, numLanes)
        override val sbs = switchBoxes.flatten 
        for (i <- 0 until switchBoxes.size) {
          for (j <- 0 until switchBoxes.head.size) {
            coordOf(switchBoxes(i)(j)) = (i*scale-scale/2, j*scale-scale/2)
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
          Config1.genNetwork(cus, switchBoxes)
          cus.flatten
        }

      }
      val pcus = arch.rcus
      val sbs = arch.sbs

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

