package pir.test

import pir._
import pir.graph.{Counter => Ctr, CounterChain, ComputeUnit => CU}
import pir.plasticine.graph.{Counter => PCtr, Const => PConst, Top => PTop}
import pir.PIRMisc._
import plasticine.config._
import pir.graph.mapper._
import pir.graph.traversal._

import org.scalatest._

class CtrMapperTest extends UnitTest with Design { self =>
  override val arch = Config0 
  implicit val ctrler:CU = null
  val mapper = new CtrMapper()

  "CtrMapper Test1" should "success" in {
    // Nodes
    val cc0 = CounterChain(0 until 1 by 2, 3 until 4 by 5, 6 until 7 by 8)
    val ctrs = cc0.counters 
    // PNodes
    val numCtrs = 3
    val pctrs = List.tabulate(numCtrs) { ic => 
      val c = PCtr(ic) 
      c.min <== PConst.out
      c.max <== PConst.out
      c.step <== PConst.out
      c
    }
    for (i <- 1 until numCtrs) { pctrs(i).en <== pctrs(i-1).done } 
    val ptop = PTop(Nil, Nil, Nil, Nil)
    for (i <- 0 until numCtrs by 2) { pctrs(i).en <== ptop.clk }
    // Mapping
    mapper.map(ctrs, pctrs, PIRMap.empty, (m:PIRMap) => m)
    // Printer
    new PCtrPrinter("TestPCtr1.dot").print(pctrs)
  }
}

