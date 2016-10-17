package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.graph.traversal._
import pir.plasticine.config._

import org.scalatest.{ Sequential => _, _ }
import scala.language.reflectiveCalls

class ScalarBundleTest extends UnitTest { self =>

  "ScalarBundleTest Test1" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.tabulate(8){ i => Scalar(s"s$i") }
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.scalarOut(sls(0)) 
        CU.scalarOut(sls(1)) 
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.scalarIn(sls(0))
        CU.scalarOut(sls(7))
        CU.scalarOut(sls(6))
      }
      val c2 = Pipeline("c2", top, Nil){ implicit CU => 
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(2))
        CU.scalarIn(sls(3))
        CU.scalarIn(sls(4))
        CU.scalarIn(sls(5))
        CU.scalarIn(sls(7))
      }
      val c3 = Pipeline("c3", top, Nil){ implicit CU => 
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c4 = Pipeline("c4", top, Nil){ implicit CU => 
        CU.scalarIn(sls(0))
        CU.scalarIn(sls(1))
        CU.scalarOut(sls(2))
        CU.scalarOut(sls(3))
        CU.scalarOut(sls(4))
        CU.scalarOut(sls(5))
      }
      val cus = c0::c1::c2::c3::c4::Nil
      top.updateFields(cus, Nil, sls, Nil, Nil)

      // PNodes
      override val arch = P2P_2CU  

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRNetworkDotGen().run
    }
  }

  "ScalarBundleTest Test2" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val ais = List.tabulate(7) { i => ArgIn(s"ai$i") }

      val sls = Nil 
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.scalarIn(ais(0))
        CU.scalarIn(ais(1))
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.scalarIn(ais(2))
        CU.scalarIn(ais(3))
        CU.scalarIn(ais(4))
        CU.scalarIn(ais(5))
        CU.scalarIn(ais(6))
      }
      val cus = c0::c1::Nil
      top.updateFields(cus, Nil, sls ++ ais, Nil, Nil)

      // PNodes
      override val arch = P2P_2CU  

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRNetworkDotGen().run

      c0.vins should have size 1
      c1.vins should have size 2
      c0.vins.collect{case dv:DummyVecIn => dv.vector.scalars}.flatten should have size 3
      c1.vins.collect{case dv:DummyVecIn => dv.vector.scalars}.flatten should have size 7
    }
  }

  "ScalarBundleTest Test3" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val ais = List.tabulate(3) { i => ArgIn(s"ai$i") }

      val sls = Nil 
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.scalarIn(ais(0))
        CU.scalarIn(ais(1))
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.scalarIn(ais(1))
        CU.scalarIn(ais(2))
      }
      val c2 = Pipeline("c2", top, Nil){ implicit CU => 
        CU.scalarIn(ais(2))
      }
      val cus = c0::c1::c2::Nil
      top.updateFields(cus, Nil, sls ++ ais, Nil, Nil)

      // PNodes
      override val arch = P2P_2CU  

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRNetworkDotGen().run

      top.vouts should have size 1
    }
  }

  //TODO: Currently outer controller's input would be routed twice when it's inner also route the
  //same input
  "ScalarBundleTest Test4" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val ais = List.tabulate(3) { i => ArgIn(s"ai$i") }

      val sls = Nil 
      val s0 = Sequential("s0", top, Nil){ implicit CU => 
        CU.scalarIn(ais(2))
      }
      val c0 = Pipeline("c0", s0, Nil){ implicit CU => 
        CU.scalarIn(ais(0))
        CU.scalarIn(ais(1))
      }
      val c1 = Pipeline("c1", s0, Nil){ implicit CU => 
        CU.scalarIn(ais(1))
        CU.scalarIn(ais(2))
      }
      val cus = c0::c1::Nil
      val outers = s0::Nil 
      top.updateFields(cus, outers, sls ++ ais, Nil, Nil)

      // PNodes
      override val arch = P2P_2CU  

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRNetworkDotGen().run

      top.vouts should have size 1
    }
  }

}

