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
    val x1552_1_rd_vector = Vector("x1552_1_rd")
    val x1557_0_rd_vector = Vector("x1557_0_rd")
    val x1536_oc = OffChip("x1536")
    val x1552_1_wt_vector = Vector("x1552_1_wt")
    val x1552_0_wt_vector = Vector("x1552_0_wt")
    val x1527_argin = ArgIn("x1527")
    val x1604_0_wt_vector = Vector("x1604_0_wt")
    val x1542_oc = OffChip("x1542")
    val x1533_oc = OffChip("x1533")
    val x1604_0_rd_vector = Vector("x1604_0_rd")
    val x1525_argin = ArgIn("x1525")
    val x1558_0_rd_vector = Vector("x1558_0_rd")
    val x1526_argin = ArgIn("x1526")
    val x1552_0_rd_vector = Vector("x1552_0_rd")
    val x1571_mc = MemoryController(TileLoad, x1533_oc).parent("x1579")
    val x1592_mc = MemoryController(TileLoad, x1536_oc).parent("x1600")
    val x1649_mc = MemoryController(TileStore, x1542_oc).parent("x1650")
    val x1652 = Sequential(name = "x1652", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1652_unitcc = CounterChain(name = "x1652_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1651 = MetaPipeline(name = "x1651", parent=x1652) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1525_argin), Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x1526_argin), Const("64i").out) // Counter
      val x1551 = CounterChain(name = "x1551", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x1552_dsp0 = MemoryPipeline(name = "x1552_dsp0", parent="x1651") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr295 = CU.temp
      val tr296 = CU.temp
      val tr298 = CU.temp
      val tr299 = CU.temp
      val x1640 = CounterChain.copy("x1645", "x1640")
      val x1556 = CounterChain.copy("x1629", "x1556")
      val x1618 = CounterChain.copy("x1627", "x1618")
      val x1632 = CounterChain.copy("x1650", "x1632")
      val x1552_x1641 = SRAM(size = 256, banking = Strided(1)).wtPort(x1552_0_wt_vector).rdPort(x1552_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1552_x1641))
      Stage(stage(1), operands=List(x1618(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr295)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr295), CU.ctr(stage(1), x1618(1))), op=FixAdd, results=List(x1552_x1641.writeAddr, CU.temp(stage(2), tr296)))
      stage = stage0 +: RAStages(2, List(x1552_x1641))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1632(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr298)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr298), CU.ctr(stage(1), x1640(0))), op=FixAdd, results=List(x1552_x1641.readAddr, CU.temp(stage(2), tr299)))
    }
    val x1552_dsp1 = MemoryPipeline(name = "x1552_dsp1", parent="x1628") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr302 = CU.temp
      val tr303 = CU.temp
      val tr305 = CU.temp
      val tr306 = CU.temp
      val x1618 = CounterChain.copy("x1627", "x1618")
      val x1552_x1620 = SRAM(size = 256, banking = Strided(1)).wtPort(x1552_1_wt_vector).rdPort(x1552_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1552_x1620))
      Stage(stage(1), operands=List(x1618(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr302)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr302), CU.ctr(stage(1), x1618(1))), op=FixAdd, results=List(x1552_x1620.writeAddr, CU.temp(stage(2), tr303)))
      stage = stage0 +: RAStages(2, List(x1552_x1620))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1618(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr305)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr305), CU.ctr(stage(1), x1618(1))), op=FixAdd, results=List(x1552_x1620.readAddr, CU.temp(stage(2), tr306)))
    }
    val x1629 = MetaPipeline(name = "x1629", parent=x1651) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1527_argin), Const("64i").out) // Counter
      val x1556 = CounterChain(name = "x1556", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1557_dsp0 = MemoryPipeline(name = "x1557_dsp0", parent="x1629") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr310 = CU.temp
      val tr311 = CU.temp
      val x1603 = CounterChain.copy("x1628", "x1603")
      val x1607 = CounterChain.copy("x1615", "x1607")
      val x1557_x1608 = SemiFIFO(size = 256, banking = Strided(1)).wtPort(x1571_mc.data).rdPort(x1557_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1557_x1608))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1607(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr310)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr310), CU.ctr(stage(1), x1603(0))), op=FixAdd, results=List(x1557_x1608.readAddr, CU.temp(stage(2), tr311)))
    }
    val x1558_dsp0 = MemoryPipeline(name = "x1558_dsp0", parent="x1629") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr314 = CU.temp
      val tr315 = CU.temp
      val x1603 = CounterChain.copy("x1628", "x1603")
      val x1607 = CounterChain.copy("x1615", "x1607")
      val x1558_x1609 = SemiFIFO(size = 4096, banking = Strided(1)).wtPort(x1592_mc.data).rdPort(x1558_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1558_x1609))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1603(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr314)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr314), CU.ctr(stage(1), x1607(1))), op=FixAdd, results=List(x1558_x1609.readAddr, CU.temp(stage(2), tr315)))
    }
    val x1579 = StreamController(name = "x1579", parent=x1629) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1561 = CounterChain(name = "x1561", ctr5)
      var stage: List[Stage] = Nil
    }
    val x1567 = StreamPipeline(name = "x1567", parent=x1579) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr318 = CU.temp
      val tr319 = CU.temp
      val tr320 = CU.temp
      val x1551 = CounterChain.copy("x1651", "x1551")
      val x1556 = CounterChain.copy("x1629", "x1556")
      val x1561 = CounterChain.copy("x1579", "x1561")
      val x1567_unitcc = CounterChain(name = "x1567_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1551(0)), CU.ctr(stage(0), x1561(0))), op=FixAdd, results=List(CU.temp(stage(1), tr318)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr318), CU.scalarIn(stage(1), x1527_argin)), op=FixMul, results=List(CU.temp(stage(2), tr319)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr319), CU.ctr(stage(2), x1556(0))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1571_mc.ofs), CU.temp(stage(3), tr320)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1571_mc.len)))
    }
    val x1600 = StreamController(name = "x1600", parent=x1629) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1582 = CounterChain(name = "x1582", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1588 = StreamPipeline(name = "x1588", parent=x1600) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr331 = CU.temp
      val tr332 = CU.temp
      val tr333 = CU.temp
      val x1588_unitcc = CounterChain(name = "x1588_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1551 = CounterChain.copy("x1651", "x1551")
      val x1556 = CounterChain.copy("x1629", "x1556")
      val x1582 = CounterChain.copy("x1600", "x1582")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1556(0)), CU.ctr(stage(0), x1582(0))), op=FixAdd, results=List(CU.temp(stage(1), tr331)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr331), CU.scalarIn(stage(1), x1526_argin)), op=FixMul, results=List(CU.temp(stage(2), tr332)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr332), CU.ctr(stage(2), x1551(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1592_mc.ofs), CU.temp(stage(3), tr333)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1592_mc.len)))
    }
    val x1628 = MetaPipeline(name = "x1628", parent=x1629) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1603 = CounterChain(name = "x1603", ctr4)
      var stage: List[Stage] = Nil
    }
    val x1604_dsp0 = MemoryPipeline(name = "x1604_dsp0", parent="x1628") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val tr345 = CU.temp
      val tr347 = CU.temp
      val tr348 = CU.temp
      val x1618 = CounterChain.copy("x1627", "x1618")
      val x1607 = CounterChain.copy("x1615", "x1607")
      val x1604_x1619 = SRAM(size = 256, banking = Strided(1)).wtPort(x1604_0_wt_vector).rdPort(x1604_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1604_x1619))
      Stage(stage(1), operands=List(x1607(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr344), CU.ctr(stage(1), x1607(1))), op=FixAdd, results=List(x1604_x1619.writeAddr, CU.temp(stage(2), tr345)))
      stage = stage0 +: RAStages(2, List(x1604_x1619))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1618(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr347)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr347), CU.ctr(stage(1), x1618(1))), op=FixAdd, results=List(x1604_x1619.readAddr, CU.temp(stage(2), tr348)))
    }
    val x1615 = Pipeline(name = "x1615", parent=x1628) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr357 = CU.temp
      val ctr9 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1607 = CounterChain(name = "x1607", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1557_0_rd_vector), CU.vecIn(stage(0), x1558_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x1604_0_wt_vector), CU.temp(stage(1), tr357)))
    }
    val x1627 = Pipeline(name = "x1627", parent=x1628) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr365 = CU.temp
      val tr369 = CU.temp
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1618 = CounterChain(name = "x1618", ctr11, ctr12)
      val x1603 = CounterChain.copy("x1628", "x1603")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1603(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr365)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1604_0_rd_vector), CU.vecIn(stage(1), x1552_1_rd_vector)), op=FixAdd, results=List(CU.reduce(stage(2))))
      val (rs1, rr368) = Stage.reduce(op=FixAdd, init=Const("0l"))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr365), CU.reduce(stage(2)), rr368), op=Mux, results=List(CU.vecOut(stage(3), x1552_1_wt_vector), CU.vecOut(stage(3), x1552_0_wt_vector), CU.temp(stage(3), tr369)))
    }
    val x1650 = StreamController(name = "x1650", parent=x1651) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1632 = CounterChain(name = "x1632", ctr13)
      var stage: List[Stage] = Nil
    }
    val x1638 = StreamPipeline(name = "x1638", parent=x1650) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr373 = CU.temp
      val tr374 = CU.temp
      val tr375 = CU.temp
      val x1551 = CounterChain.copy("x1651", "x1551")
      val x1632 = CounterChain.copy("x1650", "x1632")
      val x1638_unitcc = CounterChain(name = "x1638_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1551(0)), CU.ctr(stage(0), x1632(0))), op=FixAdd, results=List(CU.temp(stage(1), tr373)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr373), CU.scalarIn(stage(1), x1526_argin)), op=FixMul, results=List(CU.temp(stage(2), tr374)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr374), CU.ctr(stage(2), x1551(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1649_mc.ofs), CU.temp(stage(3), tr375)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1649_mc.len)))
    }
    val x1645 = Pipeline(name = "x1645", parent=x1650) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1640 = CounterChain(name = "x1640", ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1552_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1649_mc.data)))
    }
    
  }
}
