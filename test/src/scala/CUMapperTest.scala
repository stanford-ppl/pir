package pir.test

import pir._
import pir.graph.{Counter => Ctr, CounterChain, ComputeUnit => CU}
import pir.plasticine.graph.{Counter => PCtr, Const => PConst, Top => PTop}
import pir.PIRMisc._
import plasticine.config._
import pir.graph.mapper._
import pir.graph.traversal._

import org.scalatest._

import org.scalatest.Tag

class CUMapperTest extends UnitTest with Design { self =>
  override val arch = new Spade {
    val numLanes = 0
    val rcus = Nil
    val ttcus = Nil
    val top = PTop(Nil, Nil, Nil, Nil)
    val wordWidth = 32
  }
  val ptop = arch.top
  val mapper = new CUMapper()

  //"CUMapper Test1" should "success" in {
  //  // Nodes
  //  val ctrs = cc0.counters ++ cc1.counters
  //  // PNodes
  //  val numCtrs = 3
  //  val pctrs = List.tabulate(numCtrs) { ic => 
  //    val c = PCtr(ic) 
  //    c.min <== PConst.out
  //    c.max <== PConst.out
  //    c.step <== PConst.out
  //    c
  //  }
  //  for (i <- 1 until numCtrs) { pctrs(i).en <== pctrs(i-1).done } 
  //  for (i <- 0 until numCtrs by 2) { pctrs(i).en <== ptop.clk }
  //  // Mapping
  //  val mapping = mapper.map(ctrs, pctrs, PIRMap.empty, (m:PIRMap) => m)
  //  // Printer
  //  new CtrDotPrinter("TestPCtr1.dot").print(pctrs, ctrs, mapping)
  //}

  //"CtrMapper Test2" should "fail" in {
  //  intercept[PIRException] {
  //    // Nodes
  //    val cc1 = CounterChain(0 until 1 by 2)
  //    val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5)
  //    val ctrs = cc0.counters ++ cc1.counters
  //    // PNodes
  //    val numCtrs = 3
  //    val pctrs = List.tabulate(numCtrs) { ic => 
  //      val c = PCtr(ic) 
  //      c.min <== PConst.out
  //      c.max <== PConst.out
  //      c.step <== PConst.out
  //      c
  //    }
  //    for (i <- 1 until numCtrs) { pctrs(i).en <== pctrs(i-1).done } 
  //    for (i <- 0 until numCtrs by 3) { pctrs(i).en <== ptop.clk }
  //    // Mapping
  //    val mapping = mapper.map(ctrs, pctrs, PIRMap.empty, (m:PIRMap) => m)
  //    new CtrDotPrinter("TestPCtr2.dot").print(pctrs, ctrs, mapping)
  //  }
  //}

  //"CtrMapper Test3" should "success" taggedAs(Current) in {
  //  // Nodes
  //  val cc1 = CounterChain(0 until 1 by 2)
  //  val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5)
  //  val ctrs = cc0.counters ++ cc1.counters
  //  // PNodes
  //  val numCtrs = 3
  //  val pctrs = List.tabulate(numCtrs) { ic => 
  //    val c = PCtr(ic) 
  //    c.min <== PConst.out
  //    c.max <== PConst.out
  //    c.step <== PConst.out
  //    c
  //  }
  //  pctrs(1).en <== pctrs(0).done
  //  for (i <- 0 until numCtrs by 1) { pctrs(i).en <== ptop.clk }
  //  // Mapping
  //  val mapping = mapper.map(ctrs, pctrs, PIRMap.empty, (m:PIRMap) => m)
  //  new CtrDotPrinter("TestPCtr3.dot").print(pctrs, ctrs, mapping)
  //  val ctmap = mapping.ctmap
  //  assert(ctmap(cc1.inner)==pctrs(2))
  //  assert(ctmap(cc0.inner)==pctrs(0))
  //  assert(ctmap(cc0.outer)==pctrs(1))
  //}

}

