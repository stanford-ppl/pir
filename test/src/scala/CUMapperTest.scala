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

  "Point-to-point connection mapping" should "success" taggedAs(WIP) in {
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
      top.updateFields(cus, outer::Nil, sls, Nil, Nil)

      // PNodes
      override val arch = new Spade with PointToPointNetwork {
        val numLanes = 4
        val scalarBandwidth = numLanes 
        val numScalarInReg = 6 
        val numRCUs = 5
        val numVins = 2
        val numRegs = 10
        val rcus = List.tabulate(numRCUs) { i =>
          ConfigFactory.genRCU(numSRAMs=numVins, numCtrs=0, numRegs=numRegs)
        } 
        val ttcus = Nil
        val sbs = Nil 
        val top = PTop(0, 0)
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
      val outputMapper = new OutputMapper()
      val viMapper = new VecInMapper()
      val ctrlMapper = new CtrlMapper()
      val mapper = CUMapper(outputMapper, viMapper, ctrlMapper)

      new PIRNetworkDotGen().run
      Try {
        mapper.map(PIRMap.empty)
      } match {
        case Success(mapping) => 
          MapperLogger.close
          new CUDotPrinter("TestP2P.dot").print(pcus, mapping)
        case Failure(e) => 
          MapperLogger.close
          new CUDotPrinter("TestP2P.dot").print(pcus); throw e
      }
      // Printer
    }
  }

}

