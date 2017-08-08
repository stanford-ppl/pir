package pir.test

import pir._
import pir.util.typealias._
import pir.util.misc._
import pir.util.enums._
import pir.graph._
import pir.pass._
import pir.spade.config._
import pir.codegen._

import org.scalatest.{ Sequential => _, _ }
import scala.language.reflectiveCalls

class ScalarBundleTest extends UnitTest { self =>

  "ScalarBundleTest Test1" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.tabulate(8){ i => Scalar(s"s$i") }
      val outer = Sequential("outer", top, Nil) { implicit CU => } 
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.newSout(sls(0)) 
        CU.newSout(sls(1)) 
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.newSin(sls(0))
        CU.newSout(sls(7))
        CU.newSout(sls(6))
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.newSin(sls(6))
        CU.newSin(sls(2))
        CU.newSin(sls(3))
        CU.newSin(sls(4))
        CU.newSin(sls(5))
        CU.newSin(sls(7))
      }
      val c3 = Pipeline("c3", outer, Nil){ implicit CU => 
        CU.newSin(sls(6))
        CU.newSin(sls(7))
      }
      val c4 = Pipeline("c4", outer, Nil){ implicit CU => 
        CU.newSin(sls(0))
        CU.newSin(sls(1))
        CU.newSout(sls(2))
        CU.newSout(sls(3))
        CU.newSout(sls(4))
        CU.newSout(sls(5))
      }
      val cus = c0::c1::c2::c3::c4::Nil
      cus.foreach(top.addCtrler)
      top.scalars(sls)

      // PNodes
      val arch = SN4x4

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRDataDotGen().run
    }
  }

  "ScalarBundleTest Test2" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val ais = List.tabulate(7) { i => ArgIn(s"ai$i") }

      val sls = Nil 
      val outer = Sequential("outer", top, Nil) { implicit CU => } 
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.newSin(ais(0))
        CU.newSin(ais(1))
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.newSin(ais(2))
        CU.newSin(ais(3))
        CU.newSin(ais(4))
        CU.newSin(ais(5))
        CU.newSin(ais(6))
      }
      val cus = c0::c1::Nil
      cus.foreach(top.addCtrler)
      top.scalars(sls ++ ais)

      // PNodes
      val arch = SN2x2

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRDataDotGen().run

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
      val outer = Sequential("outer", top, Nil) { implicit CU => } 
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.newSin(ais(0))
        CU.newSin(ais(1))
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.newSin(ais(1))
        CU.newSin(ais(2))
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.newSin(ais(2))
      }
      val cus = c0::c1::c2::Nil
      cus.foreach(top.addCtrler)
      top.addCtrler(outer)
      top.scalars(sls ++ ais)

      // PNodes
      val arch = SN2x2

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRDataDotGen().run

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
        CU.newSin(ais(2))
      }
      val c0 = Pipeline("c0", s0, Nil){ implicit CU => 
        CU.newSin(ais(0))
        CU.newSin(ais(1))
      }
      val c1 = Pipeline("c1", s0, Nil){ implicit CU => 
        CU.newSin(ais(1))
        CU.newSin(ais(2))
      }
      val cus = c0::c1::Nil
      val outers = s0::Nil 
      cus.foreach(top.addCtrler)
      outers.foreach(top.addCtrler)
      top.scalars(sls ++ ais)

      // PNodes
      val arch = SN2x2

      val sb = new ScalarBundling()
      sb.run
      // Printer
      new PIRPrinter().run
      new PIRDataDotGen().run

      top.vouts should have size 1
    }
  }

}

