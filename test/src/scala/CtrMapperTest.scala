package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.plasticine.graph.{Counter => PCtr, Top => PTop}
import plasticine.main._
import pir.graph.mapper._
import pir.graph.traversal._

import org.scalatest._
import scala.language.reflectiveCalls

class CtrMapperTest extends UnitTest { self =>

  "CtrMapper Test1" should "success" in {
    new Design {
      implicit val ctrler:CU = new Pipeline(Some("Test")) 
      // Nodes
      val cc1 = CounterChain(0 until 1 by 2)(ctrler, design)
      val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5)
      cc1.inner.en.connect(EnLUT(0, null).out)
      cc0.inner.en.connect(EnLUT(0, null).out)
      val ctrs = cc0.counters ++ cc1.counters
      // PNodes
      override val arch = new Spade {
        override val numLanes = 4
        val scalarBandwidth = numLanes 
        val numScalarInReg = numLanes 
        val memCtrlCommandFIFOEnqBusIdx:Int = 0
        val memCtrlDataFIFOEnqBusIdx:Int = 1
        val memCtrlCommandFIFONotFullBusIdx:Int = 0
        val memCtrlDataFIFONotFullBusIdx:Int = 1
        val memCtrlDataValidBusIdx:Int = 2

        val rcus = Nil
        val mcs = Nil
        val sbs = Nil 
        val top = PTop(0, 0)
        val numCtrs = 3
        implicit val ctrler:PCU = null
        val pctrs = List.tabulate(numCtrs) { ic => 
          val c = PCtr().index(ic) 
          c.min <== const.out
          c.max <== const.out
          c.step <== const.out
          c
        }
        for (i <- 1 until numCtrs) { pctrs(i).en <== pctrs(i-1).done } 
        for (i <- 0 until numCtrs by 2) { pctrs(i).en <== top.clk }
      }
      // Mapping
      val mapper = new CtrMapper()
      override val mapping = mapper.map(ctrs, arch.pctrs, PIRMap.empty, (m:PIRMap) => m)
      // Printer
      new CtrDotPrinter("TestPCtr1.dot").print(arch.pctrs, ctrs, mapping)
    }
  }

  "CtrMapper Test2" should "fail" in {
    new Design {
      implicit val ctrler:CU = new Pipeline(Some("Test")) 
      // Nodes
      val cc1 = CounterChain(0 until 1 by 2)
      val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5)
      cc1.inner.en.connect(EnLUT(0, null).out)
      cc0.inner.en.connect(EnLUT(0, null).out)
      val ctrs = cc0.counters ++ cc1.counters
      // PNodes
      override val arch = new Spade {
        override val numLanes = 4
        val scalarBandwidth = numLanes 
        val numScalarInReg = numLanes 
        val memCtrlCommandFIFOEnqBusIdx:Int = 0
        val memCtrlDataFIFOEnqBusIdx:Int = 1
        val memCtrlCommandFIFONotFullBusIdx:Int = 0
        val memCtrlDataFIFONotFullBusIdx:Int = 1
        val memCtrlDataValidBusIdx:Int = 2

        val rcus = Nil
        val mcs = Nil
        val sbs = Nil 
        val top = PTop(0, 0)
        val numCtrs = 3
        implicit val ctrler:PCU = null
        val pctrs = List.tabulate(numCtrs) { ic => 
          val c = PCtr().index(ic) 
          c.min <== const.out
          c.max <== const.out
          c.step <== const.out
          c
        }
        for (i <- 1 until numCtrs) { pctrs(i).en <== pctrs(i-1).done } 
        for (i <- 0 until numCtrs by 3) { pctrs(i).en <== top.clk }
      }
      // Mapping
      intercept[PIRException] {
        val mapper = new CtrMapper()
        val mapping = mapper.map(ctrs, arch.pctrs, PIRMap.empty, (m:PIRMap) => m)
      // Printer
        new CtrDotPrinter("TestPCtr2.dot").print(arch.pctrs, ctrs, mapping)
      }
    }
  }

  "CtrMapper Test3" should "success" in {
    new Design {
      implicit val ctrler:CU = new Pipeline(Some("Test")) 
      // Nodes
      val cc1 = CounterChain(0 until 1 by 2)
      val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5)
      cc1.inner.en.connect(EnLUT(0, null).out)
      cc0.inner.en.connect(EnLUT(0, null).out)
      val ctrs = cc0.counters ++ cc1.counters
      // PNodes
      override val arch = new Spade {
        override val numLanes = 4
        val scalarBandwidth = numLanes 
        val numScalarInReg = numLanes 
        val memCtrlCommandFIFOEnqBusIdx:Int = 0
        val memCtrlDataFIFOEnqBusIdx:Int = 1
        val memCtrlCommandFIFONotFullBusIdx:Int = 0
        val memCtrlDataFIFONotFullBusIdx:Int = 1
        val memCtrlDataValidBusIdx:Int = 2

        val rcus = Nil
        val mcs = Nil
        val sbs = Nil 
        val top = PTop(0, 0)
        val numCtrs = 3
        implicit val ctrler:PCU = null
        val pctrs = List.tabulate(numCtrs) { ic => 
          val c = PCtr().index(ic) 
          c.min <== const.out
          c.max <== const.out
          c.step <== const.out
          c
        }
        pctrs(1).en <== pctrs(0).done
        for (i <- 0 until numCtrs by 1) { pctrs(i).en <== top.clk }
      }
      // Mapping
      val mapper = new CtrMapper()
      override val mapping = mapper.map(ctrs, arch.pctrs, PIRMap.empty, (m:PIRMap) => m)
      // Printer
      new CtrDotPrinter("TestPCtr1.dot").print(arch.pctrs, ctrs, mapping)
      val ctmap = mapping.ctmap
      assert(ctmap(cc1.inner)==arch.pctrs(2))
      assert(ctmap(cc0.inner)==arch.pctrs(0))
      assert(ctmap(cc0.outer)==arch.pctrs(1))
    }
  }

}

