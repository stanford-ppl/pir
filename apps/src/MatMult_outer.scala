import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_outer extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1465_0_rd_vector = Vector("x1465_0_rd")
    val x1452_oc = OffChip("x1452")
    val x1460_0_rd_vector = Vector("x1460_0_rd")
    val x1435_argin = ArgIn("x1435")
    val x1460_1_wt_vector = Vector("x1460_1_wt")
    val x1509_0_rd_vector = Vector("x1509_0_rd")
    val x1509_0_wt_vector = Vector("x1509_0_wt")
    val x1437_argin = ArgIn("x1437")
    val x1460_0_wt_vector = Vector("x1460_0_wt")
    val x1460_1_rd_vector = Vector("x1460_1_rd")
    val x1464_0_rd_vector = Vector("x1464_0_rd")
    val x1443_oc = OffChip("x1443")
    val x1436_argin = ArgIn("x1436")
    val x1446_oc = OffChip("x1446")
    val x1553_mc = MemoryController(TileStore, x1452_oc).parent("x1554")
    val x1477_mc = MemoryController(TileLoad, x1443_oc).parent("x1485")
    val x1497_mc = MemoryController(TileLoad, x1446_oc).parent("x1505")
    val x1556 = Sequential(name = "x1556", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1556_unitcc = CounterChain(name = "x1556_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1555 = MetaPipeline(name = "x1555", parent=x1556) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1435_argin), Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x1436_argin), Const("64i").out) // Counter
      val x1459 = CounterChain(name = "x1459", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x1460_dsp0 = MemoryPipeline(name = "x1460_dsp0", parent="x1533") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr79 = CU.temp
      val tr80 = CU.temp
      val tr84 = CU.temp
      val tr87 = CU.temp
      val x1523 = CounterChain.copy("x1532", "x1523")
      val x1537 = CounterChain.copy("x1554", "x1537")
      val x1545 = CounterChain.copy("x1550", "x1545")
      val x1460_x1546 = SRAM(size = 256, banking = Strided(1)).wtPort(x1460_0_wt_vector).rdPort(x1460_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1460_x1546))
      Stage(stage(1), operands=List(x1523(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr79)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr79), CU.ctr(stage(1), x1523(1))), op=FixAdd, results=List(x1460_x1546.writeAddr, CU.temp(stage(2), tr80)))
      stage = stage0 +: RAStages(2, List(x1460_x1546))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1537(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr84)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr84), CU.ctr(stage(1), x1545(0))), op=FixAdd, results=List(x1460_x1546.readAddr, CU.temp(stage(2), tr87)))
    }
    val x1460_dsp1 = MemoryPipeline(name = "x1460_dsp1", parent="x1533") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr94 = CU.temp
      val tr95 = CU.temp
      val tr97 = CU.temp
      val tr98 = CU.temp
      val x1523 = CounterChain.copy("x1532", "x1523")
      val x1460_x1525 = SRAM(size = 256, banking = Strided(1)).wtPort(x1460_1_wt_vector).rdPort(x1460_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1460_x1525))
      Stage(stage(1), operands=List(x1523(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr94)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr94), CU.ctr(stage(1), x1523(1))), op=FixAdd, results=List(x1460_x1525.writeAddr, CU.temp(stage(2), tr95)))
      stage = stage0 +: RAStages(2, List(x1460_x1525))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1523(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr97)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr97), CU.ctr(stage(1), x1523(1))), op=FixAdd, results=List(x1460_x1525.readAddr, CU.temp(stage(2), tr98)))
    }
    val x1534 = MetaPipeline(name = "x1534", parent=x1555) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1437_argin), Const("64i").out) // Counter
      val x1463 = CounterChain(name = "x1463", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1464_dsp0 = MemoryPipeline(name = "x1464_dsp0", parent="x1534") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr106 = CU.temp
      val tr109 = CU.temp
      val x1512 = CounterChain.copy("x1520", "x1512")
      val x1508 = CounterChain.copy("x1533", "x1508")
      val x1464_x1513 = SemiFIFO(size = 256, banking = Strided(1)).wtPort(x1477_mc.data).rdPort(x1464_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1464_x1513))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1512(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr106)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr106), CU.ctr(stage(1), x1508(0))), op=FixAdd, results=List(x1464_x1513.readAddr, CU.temp(stage(2), tr109)))
    }
    val x1465_dsp0 = MemoryPipeline(name = "x1465_dsp0", parent="x1534") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr114 = CU.temp
      val tr119 = CU.temp
      val x1508 = CounterChain.copy("x1533", "x1508")
      val x1512 = CounterChain.copy("x1520", "x1512")
      val x1465_x1514 = SemiFIFO(size = 4096, banking = Strided(1)).wtPort(x1497_mc.data).rdPort(x1465_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1465_x1514))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1508(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr114)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr114), CU.ctr(stage(1), x1512(1))), op=FixAdd, results=List(x1465_x1514.readAddr, CU.temp(stage(2), tr119)))
    }
    val x1485 = StreamController(name = "x1485", parent=x1534) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1468 = CounterChain(name = "x1468", ctr5)
      var stage: List[Stage] = Nil
    }
    val x1474 = StreamPipeline(name = "x1474", parent=x1485) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr128 = CU.temp
      val tr129 = CU.temp
      val tr132 = CU.temp
      val x1474_unitcc = CounterChain(name = "x1474_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1459 = CounterChain.copy("x1555", "x1459")
      val x1468 = CounterChain.copy("x1485", "x1468")
      val x1463 = CounterChain.copy("x1534", "x1463")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1459(0)), CU.ctr(stage(0), x1468(0))), op=FixAdd, results=List(CU.temp(stage(1), tr128)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr128), CU.scalarIn(stage(1), x1437_argin)), op=FixMul, results=List(CU.temp(stage(2), tr129)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr129), CU.ctr(stage(2), x1463(0))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1477_mc.ofs), CU.temp(stage(3), tr132)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1477_mc.len)))
    }
    val x1505 = StreamController(name = "x1505", parent=x1534) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1488 = CounterChain(name = "x1488", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1494 = StreamPipeline(name = "x1494", parent=x1505) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr155 = CU.temp
      val tr156 = CU.temp
      val tr161 = CU.temp
      val x1494_unitcc = CounterChain(name = "x1494_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1463 = CounterChain.copy("x1534", "x1463")
      val x1488 = CounterChain.copy("x1505", "x1488")
      val x1459 = CounterChain.copy("x1555", "x1459")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1463(0)), CU.ctr(stage(0), x1488(0))), op=FixAdd, results=List(CU.temp(stage(1), tr155)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr155), CU.scalarIn(stage(1), x1436_argin)), op=FixMul, results=List(CU.temp(stage(2), tr156)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr156), CU.ctr(stage(2), x1459(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1497_mc.ofs), CU.temp(stage(3), tr161)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1497_mc.len)))
    }
    val x1533 = MetaPipeline(name = "x1533", parent=x1534) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1508 = CounterChain(name = "x1508", ctr4)
      var stage: List[Stage] = Nil
    }
    val x1509_dsp0 = MemoryPipeline(name = "x1509_dsp0", parent="x1533") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr184 = CU.temp
      val tr185 = CU.temp
      val tr191 = CU.temp
      val tr192 = CU.temp
      val x1512 = CounterChain.copy("x1520", "x1512")
      val x1523 = CounterChain.copy("x1532", "x1523")
      val x1509_x1524 = SRAM(size = 256, banking = Strided(1)).wtPort(x1509_0_wt_vector).rdPort(x1509_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1509_x1524))
      Stage(stage(1), operands=List(x1512(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr184)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr184), CU.ctr(stage(1), x1512(1))), op=FixAdd, results=List(x1509_x1524.writeAddr, CU.temp(stage(2), tr185)))
      stage = stage0 +: RAStages(2, List(x1509_x1524))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1523(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr191)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr191), CU.ctr(stage(1), x1523(1))), op=FixAdd, results=List(x1509_x1524.readAddr, CU.temp(stage(2), tr192)))
    }
    val x1520 = Pipeline(name = "x1520", parent=x1533) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr209 = CU.temp
      val ctr9 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1512 = CounterChain(name = "x1512", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1464_0_rd_vector), CU.vecIn(stage(0), x1465_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x1509_0_wt_vector), CU.temp(stage(1), tr209)))
    }
    val x1532 = Pipeline(name = "x1532", parent=x1533) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr225 = CU.temp
      val tr226 = CU.temp
      val tr227 = CU.temp
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1523 = CounterChain(name = "x1523", ctr11, ctr12)
      val x1508 = CounterChain.copy("x1533", "x1508")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1508(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr225)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1509_0_rd_vector), CU.vecIn(stage(1), x1460_1_rd_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr226)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr225), CU.vecIn(stage(2), x1509_0_rd_vector), CU.temp(stage(2), tr226)), op=Mux, results=List(CU.vecOut(stage(3), x1460_1_wt_vector), CU.vecOut(stage(3), x1460_0_wt_vector), CU.temp(stage(3), tr227)))
    }
    val x1554 = StreamController(name = "x1554", parent=x1555) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1537 = CounterChain(name = "x1537", ctr13)
      var stage: List[Stage] = Nil
    }
    val x1543 = StreamPipeline(name = "x1543", parent=x1554) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr237 = CU.temp
      val tr238 = CU.temp
      val tr239 = CU.temp
      val x1543_unitcc = CounterChain(name = "x1543_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1459 = CounterChain.copy("x1555", "x1459")
      val x1537 = CounterChain.copy("x1554", "x1537")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1459(0)), CU.ctr(stage(0), x1537(0))), op=FixAdd, results=List(CU.temp(stage(1), tr237)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr237), CU.scalarIn(stage(1), x1436_argin)), op=FixMul, results=List(CU.temp(stage(2), tr238)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr238), CU.ctr(stage(2), x1459(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1553_mc.ofs), CU.temp(stage(3), tr239)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1553_mc.len)))
    }
    val x1550 = StreamPipeline(name = "x1550", parent=x1554) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1545 = CounterChain(name = "x1545", ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1460_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1553_mc.data)))
    }
    
  }
}
