package pir.test

import pir.{Design}
import pir.typealias._
import pir.misc._
import pir.graph._
import pir.graph.enums._
import pir.graph.traversal.{CUDotPrinter}
import pir.plasticine.main._
import pir.plasticine.config._
import pir.plasticine.graph.{ComputeUnit => PCU, Top => PTop}
import pir.graph.mapper._
import pir.graph.traversal._
import scala.language.reflectiveCalls

import org.scalatest.{Sequential => _, _}
import scala.util.{Try, Success, Failure}

class CUMapperTest extends UnitTest with Metadata {

  "Point-to-point connection mapping" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.tabulate(8){ i => Scalar(s"$i") }
      val vts = List.fill(2)(Vector())
      val outer = Sequential("outer", top, Nil) { implicit CU => }
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
        CU.scalarOut(sls(0)) 
        CU.scalarOut(sls(1)) 
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        //CU.vecOut(vts(1)) 
        CU.scalarOut(sls(7))
        CU.scalarOut(sls(6))
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(2))
        CU.scalarIn(sls(3))
        CU.scalarIn(sls(4))
        CU.scalarIn(sls(5))
        CU.scalarIn(sls(7))
      }
      val c3 = Pipeline("c3", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        //CU.vecIn(vts(1))
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c4 = Pipeline("c4", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        CU.scalarIn(sls(1))
        CU.scalarOut(sls(2))
        CU.scalarOut(sls(3))
        CU.scalarOut(sls(4))
        CU.scalarOut(sls(5))
      }
      val cus = c0::c1::c2::c3::c4::Nil
      top.innerCUs(cus)
      top.outerCUs(outer::Nil)
      top.scalars(sls)

      // PNodes
      override val arch = new Spade with PointToPointNetwork {
        val memCtrlDataValidBusIdx:Int = 2
        override val numLanes = 4
        val scalarBandwidth = numLanes 
        val numScalarInReg = 6 
        val memCtrlCommandFIFOEnqBusIdx:Int = 0
        val memCtrlDataFIFOEnqBusIdx:Int = 1
        val memCtrlCommandFIFONotFullBusIdx:Int = 0
        val memCtrlDataFIFONotFullBusIdx:Int = 1

        val numPCUs = 5
        val numVins = 2
        val numRegs = 10
        val pcus = List.tabulate(numPCUs) { i =>
          new PCU()
              .numSRAMs(numVins)
              .numCtrs(0)
              .numRegs(numRegs)
              .numSinReg(0)
              .vectorIO.addIns(numVins, numLanes)
              .vectorIO.addOuts(1, numLanes)
              .addRegstages(numStage=4, numOprds=3, ops)
              .addRdstages(numStage=4, numOprds=3, ops)
              .ctrlBox(numUDCs=4)
              .index(i)
              //.genConnections
              //.genMapping(vinsPtr=12, voutPtr=0, sinsPtr=12, soutsPtr=0, ctrsPtr=0, waPtr=1, wpPtr=1, loadsPtr=8, rdPtr=0)
        } 
        val mcus = Nil
        val scus = Nil
        val mcs = Nil
        val sbs = Nil 
        val ocus = Nil 
        val top = PTop(0, 0)

        /* Network Constrain */ 
        pcus(1).vins(0) <== pcus(0).vout 
        pcus(1).vins(1) <== pcus(0).vout
        pcus(2).vins(0) <== pcus(1).vout 
        pcus(2).vins(1) <== pcus(4).vout
        pcus(3).vins(0) <== pcus(0).vout 
        pcus(3).vins(1) <== pcus(1).vout
        pcus(4).vins(0) <== pcus(0).vout 
        pcus(4).vins(1) <== pcus(0).vout
      }
      val pcus = arch.pcus

      // Mapping
      val outputMapper = new OutputMapper()
      val viMapper = new VecInMapper()
      val mapper = new CUP2PMapper(outputMapper, viMapper)

      new PIRDataDotGen().run
      Try {
        mapper.map(PIRMap.empty)
      } match {
        case Success(mapping) => 
          MapperLogger.close
          new CUVectorDotPrinter("TestP2P.dot").print
        case Failure(e) => 
          MapperLogger.close
          new CUVectorDotPrinter("TestP2P.dot").print; throw e
      }
      // Printer
    }
  }

}

