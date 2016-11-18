import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDADesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4758_x5062_data_vector = Vector("x4758_x5062_data")
    val x4990_vector = Vector("x4990")
    val x4991_vector = Vector("x4991")
    val bus_1840_vector = Vector("bus_1840")
    val x4217_oc = OffChip("x4217")
    val x4365_scalar = Scalar("x4365")
    val x4786_vector = Vector("x4786")
    val x4214_oc = OffChip("x4214")
    val bus_1492_scalar = Scalar("bus_1492")
    val x4923_vector = Vector("x4923")
    val x4760_x5068_data_vector = Vector("x4760_x5068_data")
    val bus_1832_vector = Vector("bus_1832")
    val x4362_x4928_addr_vector = Vector("x4362_x4928_addr")
    val bus_1433_scalar = Scalar("bus_1433")
    val x4367_scalar = Scalar("x4367")
    val x4758_x5062_addr_vector = Vector("x4758_x5062_addr")
    val x4760_vector = Vector("x4760")
    val x4759_vector = Vector("x4759")
    val bus_1650_vector = Vector("bus_1650")
    val bus_1592_vector = Vector("bus_1592")
    val bus_1766_vector = Vector("bus_1766")
    val x4761_vector = Vector("x4761")
    val x4760_x5068_addr_vector = Vector("x4760_x5068_addr")
    val x4854_vector = Vector("x4854")
    val x4215_oc = OffChip("x4215")
    val x4363_x4996_addr_vector = Vector("x4363_x4996_addr")
    val x4350_x5074_addr_vector = Vector("x4350_x5074_addr")
    val bus_1376_scalar = Scalar("bus_1376")
    val bus_1551_scalar = Scalar("bus_1551")
    val x4360_x4792_addr_vector = Vector("x4360_x4792_addr")
    val x4216_oc = OffChip("x4216")
    val x4361_x4860_addr_vector = Vector("x4361_x4860_addr")
    val x4758_vector = Vector("x4758")
    val x4761_x5071_addr_vector = Vector("x4761_x5071_addr")
    val bus_1824_vector = Vector("bus_1824")
    val bus_1435_scalar = Scalar("bus_1435")
    val x4364_scalar = Scalar("x4364")
    val bus_1553_scalar = Scalar("bus_1553")
    val bus_1494_scalar = Scalar("bus_1494")
    val x4350_vector = Vector("x4350")
    val bus_1843_vector = Vector("bus_1843")
    val x4218_oc = OffChip("x4218")
    val x4787_vector = Vector("x4787")
    val x4366_scalar = Scalar("x4366")
    val bus_1374_scalar = Scalar("bus_1374")
    val x4759_x5065_addr_vector = Vector("x4759_x5065_addr")
    val x4922_vector = Vector("x4922")
    val bus_1708_vector = Vector("bus_1708")
    val x4855_vector = Vector("x4855")
    val x4327_mc = MemoryController(TileLoad, x4217_oc)
    val x4682_mc = MemoryController(TileLoad, x4215_oc)
    val x4438_mc = MemoryController(TileLoad, x4214_oc)
    val x4488_mc = MemoryController(TileLoad, x4215_oc)
    val x4632_mc = MemoryController(TileLoad, x4214_oc)
    val x5116_mc = MemoryController(TileStore, x4218_oc)
    val x4535_mc = MemoryController(TileLoad, x4214_oc)
    val x4729_mc = MemoryController(TileLoad, x4214_oc)
    val x4585_mc = MemoryController(TileLoad, x4215_oc)
    val x4391_mc = MemoryController(TileLoad, x4215_oc)
    val x4285_mc = MemoryController(TileLoad, x4216_oc)
    val x5120 = Sequential(name = "x5120", parent=top, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5120_unitcc = CounterChain(name = "x5120_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4305 = StreamController(name = "x4305", parent=x5120, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4305_unitcc = CounterChain(name = "x4305_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4281_0 = UnitPipeline(name = "x4281_0", parent=x4305, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1304 = CU.temp
      val tr1303 = CU.temp
      val tr1302 = CU.temp
      val tr1301 = CU.temp
      val x4281_unitcc = CounterChain(name = "x4281_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("48i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1301)))
      Stage(stage(2), operands=List(Const("48i"), CU.temp(stage(1), tr1301)), op=FixSub, results=List(CU.temp(stage(2), tr1302)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1301), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr1303)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1303), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr1304)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1302), CU.temp(stage(4), tr1304)), op=FixAdd, results=List(CU.scalarOut(stage(5), x4285_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x4285_mc.ofs)))
    }
    val x4347 = StreamController(name = "x4347", parent=x5120, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4347_unitcc = CounterChain(name = "x4347_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4323_0 = UnitPipeline(name = "x4323_0", parent=x4347, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1325 = CU.temp
      val tr1324 = CU.temp
      val tr1323 = CU.temp
      val tr1322 = CU.temp
      val x4323_unitcc = CounterChain(name = "x4323_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("48i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1322)))
      Stage(stage(2), operands=List(Const("48i"), CU.temp(stage(1), tr1322)), op=FixSub, results=List(CU.temp(stage(2), tr1323)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1322), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr1324)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1324), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr1325)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1323), CU.temp(stage(4), tr1325)), op=FixAdd, results=List(CU.scalarOut(stage(5), x4327_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x4327_mc.ofs)))
    }
    val x5096 = MetaPipeline(name = "x5096", parent=x5120, deps=List(x4305, x4347)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("3840000i").out, Const("20i").out) // Counter
      val x4352 = CounterChain(name = "x4352", ctr3)
      //val ctr5 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      //val ctr6 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      //val x4355 = CounterChain(name = "x4355", ctr5, ctr6)
      var stage: List[Stage] = Nil
    }
    val x4412 = StreamController(name = "x4412", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4412_unitcc = CounterChain(name = "x4412_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4387_0 = UnitPipeline(name = "x4387_0", parent=x4412, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1350 = CU.temp
      val tr1349 = CU.temp
      val tr1347 = CU.temp
      val tr1346 = CU.temp
      val tr1344 = CU.temp
      val tr1339 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4387_unitcc = CounterChain(name = "x4387_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1339)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4352(0)), CU.temp(stage(1), tr1339)), op=FixSub, results=List(CU.scalarOut(stage(2), x4391_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1339), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr1344)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1344), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1346)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1344), CU.temp(stage(4), tr1346)), op=FixSub, results=List(CU.temp(stage(5), tr1347)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1346), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1349)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1349), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1350)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1347), CU.temp(stage(7), tr1350)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4391_mc.len)))
    }
    val x4459 = MetaPipeline(name = "x4459", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr23 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x4415 = CounterChain(name = "x4415", ctr23)
      var stage: List[Stage] = Nil
    }
    val x4434 = StreamController(name = "x4434", parent=x4459, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4434_unitcc = CounterChain(name = "x4434_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4434_0 = StreamPipeline(name = "x4434_0", parent=x4434, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1373 = CU.temp
      val tr1371 = CU.temp
      val tr1367 = CU.temp
      val tr1366 = CU.temp
      val tr1364 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4434_unitcc = CounterChain.copy(x4434, "x4434_unitcc")
      val x4415 = CounterChain.copy(x4459, "x4415")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), CU.ctr(stage(0), x4415(0))), op=FixAdd, results=List(CU.temp(stage(1), tr1364)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1364), Const("48i")), op=FixMul, results=List(CU.temp(stage(2), tr1366)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1366), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr1367)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1366), CU.temp(stage(3), tr1367)), op=FixSub, results=List(CU.scalarOut(stage(4), x4438_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1367), Const("48i")), op=FixAdd, results=List(CU.temp(stage(5), tr1371)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1371), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr1373)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1371), CU.temp(stage(6), tr1373)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_1374_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1373), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_1376_scalar)))
    }
    val x4434_1 = StreamPipeline(name = "x4434_1", parent=x4434, deps=List(x4434_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1377 = CU.temp
      val x4434_unitcc = CounterChain.copy(x4434, "x4434_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_1376_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr1377)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_1374_scalar), CU.temp(stage(1), tr1377)), op=FixAdd, results=List(CU.scalarOut(stage(2), x4438_mc.len)))
    }
    val x4462_0 = UnitPipeline(name = "x4462_0", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1391 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4462_unitcc = CounterChain(name = "x4462_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("3840000i"), CU.ctr(stage(0), x4352(0))), op=FixSub, results=List(CU.temp(stage(1), tr1391)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1391), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4364_scalar)))
    }
    val x4509 = StreamController(name = "x4509", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4509_unitcc = CounterChain(name = "x4509_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4484_0 = UnitPipeline(name = "x4484_0", parent=x4509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1409 = CU.temp
      val tr1408 = CU.temp
      val tr1406 = CU.temp
      val tr1405 = CU.temp
      val tr1403 = CU.temp
      val tr1398 = CU.temp
      val x4484_unitcc = CounterChain(name = "x4484_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4352 = CounterChain.copy(x5096, "x4352")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1398)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4352(0)), CU.temp(stage(1), tr1398)), op=FixSub, results=List(CU.scalarOut(stage(2), x4488_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1398), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr1403)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1403), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1405)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1403), CU.temp(stage(4), tr1405)), op=FixSub, results=List(CU.temp(stage(5), tr1406)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1405), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1408)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1408), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1409)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1406), CU.temp(stage(7), tr1409)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4488_mc.len)))
    }
    val x4556 = MetaPipeline(name = "x4556", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr29 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x4512 = CounterChain(name = "x4512", ctr29)
      var stage: List[Stage] = Nil
    }
    val x4531 = StreamController(name = "x4531", parent=x4556, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4531_unitcc = CounterChain(name = "x4531_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4531_0 = StreamPipeline(name = "x4531_0", parent=x4531, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1432 = CU.temp
      val tr1430 = CU.temp
      val tr1426 = CU.temp
      val tr1425 = CU.temp
      val tr1423 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4531_unitcc = CounterChain.copy(x4531, "x4531_unitcc")
      val x4512 = CounterChain.copy(x4556, "x4512")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), CU.ctr(stage(0), x4512(0))), op=FixAdd, results=List(CU.temp(stage(1), tr1423)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1423), Const("48i")), op=FixMul, results=List(CU.temp(stage(2), tr1425)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1425), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr1426)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1425), CU.temp(stage(3), tr1426)), op=FixSub, results=List(CU.scalarOut(stage(4), x4535_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1426), Const("48i")), op=FixAdd, results=List(CU.temp(stage(5), tr1430)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1430), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr1432)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1430), CU.temp(stage(6), tr1432)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_1433_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1432), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_1435_scalar)))
    }
    val x4531_1 = StreamPipeline(name = "x4531_1", parent=x4531, deps=List(x4531_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1436 = CU.temp
      val x4531_unitcc = CounterChain.copy(x4531, "x4531_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_1435_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr1436)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_1433_scalar), CU.temp(stage(1), tr1436)), op=FixAdd, results=List(CU.scalarOut(stage(2), x4535_mc.len)))
    }
    val x4559_0 = UnitPipeline(name = "x4559_0", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1450 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4559_unitcc = CounterChain(name = "x4559_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("3840000i"), CU.ctr(stage(0), x4352(0))), op=FixSub, results=List(CU.temp(stage(1), tr1450)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1450), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4365_scalar)))
    }
    val x4606 = StreamController(name = "x4606", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4606_unitcc = CounterChain(name = "x4606_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4581_0 = UnitPipeline(name = "x4581_0", parent=x4606, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1468 = CU.temp
      val tr1467 = CU.temp
      val tr1465 = CU.temp
      val tr1464 = CU.temp
      val tr1462 = CU.temp
      val tr1457 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4581_unitcc = CounterChain(name = "x4581_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1457)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4352(0)), CU.temp(stage(1), tr1457)), op=FixSub, results=List(CU.scalarOut(stage(2), x4585_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1457), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr1462)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1462), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1464)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1462), CU.temp(stage(4), tr1464)), op=FixSub, results=List(CU.temp(stage(5), tr1465)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1464), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1467)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1467), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1468)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1465), CU.temp(stage(7), tr1468)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4585_mc.len)))
    }
    val x4653 = MetaPipeline(name = "x4653", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr35 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x4609 = CounterChain(name = "x4609", ctr35)
      var stage: List[Stage] = Nil
    }
    val x4628 = StreamController(name = "x4628", parent=x4653, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4628_unitcc = CounterChain(name = "x4628_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4628_0 = StreamPipeline(name = "x4628_0", parent=x4628, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1491 = CU.temp
      val tr1489 = CU.temp
      val tr1485 = CU.temp
      val tr1484 = CU.temp
      val tr1482 = CU.temp
      val x4628_unitcc = CounterChain.copy(x4628, "x4628_unitcc")
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4609 = CounterChain.copy(x4653, "x4609")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), CU.ctr(stage(0), x4609(0))), op=FixAdd, results=List(CU.temp(stage(1), tr1482)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1482), Const("48i")), op=FixMul, results=List(CU.temp(stage(2), tr1484)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1484), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr1485)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1484), CU.temp(stage(3), tr1485)), op=FixSub, results=List(CU.scalarOut(stage(4), x4632_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1485), Const("48i")), op=FixAdd, results=List(CU.temp(stage(5), tr1489)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1489), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr1491)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1489), CU.temp(stage(6), tr1491)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_1492_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1491), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_1494_scalar)))
    }
    val x4628_1 = StreamPipeline(name = "x4628_1", parent=x4628, deps=List(x4628_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1495 = CU.temp
      val x4628_unitcc = CounterChain.copy(x4628, "x4628_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_1494_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr1495)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_1492_scalar), CU.temp(stage(1), tr1495)), op=FixAdd, results=List(CU.scalarOut(stage(2), x4632_mc.len)))
    }
    val x4656_0 = UnitPipeline(name = "x4656_0", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1509 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4656_unitcc = CounterChain(name = "x4656_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("3840000i"), CU.ctr(stage(0), x4352(0))), op=FixSub, results=List(CU.temp(stage(1), tr1509)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1509), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4366_scalar)))
    }
    val x4703 = StreamController(name = "x4703", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4703_unitcc = CounterChain(name = "x4703_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4678_0 = UnitPipeline(name = "x4678_0", parent=x4703, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1527 = CU.temp
      val tr1526 = CU.temp
      val tr1524 = CU.temp
      val tr1523 = CU.temp
      val tr1521 = CU.temp
      val tr1516 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4678_unitcc = CounterChain(name = "x4678_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1516)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4352(0)), CU.temp(stage(1), tr1516)), op=FixSub, results=List(CU.scalarOut(stage(2), x4682_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1516), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr1521)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1521), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1523)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1521), CU.temp(stage(4), tr1523)), op=FixSub, results=List(CU.temp(stage(5), tr1524)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1523), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1526)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1526), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1527)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1524), CU.temp(stage(7), tr1527)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4682_mc.len)))
    }
    val x4750 = MetaPipeline(name = "x4750", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr41 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x4706 = CounterChain(name = "x4706", ctr41)
      var stage: List[Stage] = Nil
    }
    val x4725 = StreamController(name = "x4725", parent=x4750, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4725_unitcc = CounterChain(name = "x4725_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4725_0 = StreamPipeline(name = "x4725_0", parent=x4725, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1550 = CU.temp
      val tr1548 = CU.temp
      val tr1544 = CU.temp
      val tr1543 = CU.temp
      val tr1541 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4725_unitcc = CounterChain.copy(x4725, "x4725_unitcc")
      val x4706 = CounterChain.copy(x4750, "x4706")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4352(0)), CU.ctr(stage(0), x4706(0))), op=FixAdd, results=List(CU.temp(stage(1), tr1541)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1541), Const("48i")), op=FixMul, results=List(CU.temp(stage(2), tr1543)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1543), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr1544)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1543), CU.temp(stage(3), tr1544)), op=FixSub, results=List(CU.scalarOut(stage(4), x4729_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1544), Const("48i")), op=FixAdd, results=List(CU.temp(stage(5), tr1548)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1548), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr1550)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1548), CU.temp(stage(6), tr1550)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_1551_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1550), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_1553_scalar)))
    }
    val x4725_1 = StreamPipeline(name = "x4725_1", parent=x4725, deps=List(x4725_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1554 = CU.temp
      val x4725_unitcc = CounterChain.copy(x4725, "x4725_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_1553_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr1554)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_1551_scalar), CU.temp(stage(1), tr1554)), op=FixAdd, results=List(CU.scalarOut(stage(2), x4729_mc.len)))
    }
    val x4753_0 = UnitPipeline(name = "x4753_0", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1568 = CU.temp
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4753_unitcc = CounterChain(name = "x4753_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("3840000i"), CU.ctr(stage(0), x4352(0))), op=FixSub, results=List(CU.temp(stage(1), tr1568)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1568), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4367_scalar)))
    }
    val x4853 = MetaPipeline(name = "x4853", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x4364_scalar).out, Const("1i").out) // Counter
      val x4770 = CounterChain(name = "x4770", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4815 = StreamController(name = "x4815", parent=x4853, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4789 = CounterChain(name = "x4789", ctr8)
      var stage: List[Stage] = Nil
    }
    val x4815_0 = StreamPipeline(name = "x4815_0", parent=x4815, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1591 = CU.temp
      val tr1581 = CU.temp
      val x4770 = CounterChain.copy(x4853, "x4770")
      val x4789 = CounterChain.copy(x4815, "x4789")
      val x4262_x4801 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4285_mc.vdata).rdAddr(x4789(0))
      val x4356_x4795 = SemiFIFO(size = 20, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4770(0))).wtPort(x4391_mc.vdata).rdAddr(x4770(0))
      val x4263_x4798 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4327_mc.vdata).rdAddr(x4789(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4770(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1581)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1581), CU.ctr(stage(1), x4789(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x4360_x4792_addr_vector)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4356_x4795), Const("1i")), op=FixEql, results=List(CU.temp(stage(3), tr1591)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1591), CU.load(stage(3), x4263_x4798), CU.load(stage(3), x4262_x4801)), op=Mux, results=List(CU.vecOut(stage(4), bus_1592_vector)))
    }
    val x4815_1 = StreamPipeline(name = "x4815_1", parent=x4815, deps=List(x4815_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4770 = CounterChain.copy(x4853, "x4770")
      val x4789 = CounterChain.copy(x4815, "x4789")
      val x4360_x4792 = SemiFIFO(size = 960, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4770(0))).wtPort(x4438_mc.vdata)
      val x4360_x4792_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4360_x4792_addr_vector)
      val bus_1592_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1592_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4360_x4792_addr_fifo.load), op=Bypass, results=List(x4360_x4792.readAddr))
      Stage(stage(2), operands=List(x4360_x4792.load, bus_1592_fifo.load), op=FltSub, results=List(CU.vecOut(stage(2), x4786_vector)))
    }
    val x4834_0 = Pipeline(name = "x4834_0", parent=x4853, deps=List(x4815)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr45 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr46 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4818 = CounterChain(name = "x4818", ctr45, ctr46)
      val x4789 = CounterChain.copy(x4815, "x4789")
      val x4786_x4821 = SRAM(size = 48, writeCtr = x4789(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4818(0), swapWrite = x4789(0))).wtPort(x4786_vector).rdAddr(x4818(0)).wtAddr(x4789(0))
      val x4786_x4824 = SRAM(size = 48, writeCtr = x4789(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4818(0), swapWrite = x4789(0))).wtPort(x4786_vector).rdAddr(x4818(1)).wtAddr(x4789(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4786_x4821.load, x4786_x4824.load), op=FltMul, results=List(CU.vecOut(stage(1), x4787_vector)))
    }
    val x4851_0 = Pipeline(name = "x4851_0", parent=x4853, deps=List(x4834_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1625 = CU.temp
      val tr1615 = CU.temp
      val tr1611 = CU.temp
      val tr1609 = CU.temp
      val tr1608 = CU.temp
      val x4818 = CounterChain.copy(x4834_0, "x4818")
      val ctr49 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr50 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4782 = CounterChain(name = "x4782", ctr49, ctr50)
      val x4758_x4840 = SRAM(size = 2304, writeCtr = x4782(0), banking = Strided(1), buffering = SingleBuffer())
      val x4787_x4837 = SRAM(size = 2304, writeCtr = x4818(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4782(0), swapWrite = x4818(0))).wtPort(x4787_vector)
      val wr1627 = CU.wtAddr(x4758_x4840)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4787_x4837))
      Stage(stage(1), operands=List(x4818(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1608)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1608), CU.ctr(stage(1), x4818(1))), op=FixAdd, results=List(x4787_x4837.writeAddr, CU.temp(stage(2), tr1609)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4782(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1611)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1611), CU.ctr(stage(1), x4782(1))), op=FixAdd, results=List(x4787_x4837.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4782(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr1615)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1615), CU.ctr(stage(3), x4782(1))), op=FixAdd, results=List(x4758_x4840.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x4787_x4837), x4758_x4840.load), op=FltAdd, results=List(CU.vecOut(stage(5), x4758_vector), CU.store(stage(5), x4758_x4840)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x4782(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr1625)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1625), CU.ctr(stage(6), x4782(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr1627)))
    }
    val x4921 = MetaPipeline(name = "x4921", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, CU.scalarIn(stage0, x4365_scalar).out, Const("1i").out) // Counter
      val x4771 = CounterChain(name = "x4771", ctr10)
      var stage: List[Stage] = Nil
    }
    val x4883 = StreamController(name = "x4883", parent=x4921, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4857 = CounterChain(name = "x4857", ctr11)
      var stage: List[Stage] = Nil
    }
    val x4883_0 = StreamPipeline(name = "x4883_0", parent=x4883, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1649 = CU.temp
      val tr1639 = CU.temp
      val x4857 = CounterChain.copy(x4883, "x4857")
      val x4771 = CounterChain.copy(x4921, "x4771")
      val x4357_x4863 = SemiFIFO(size = 20, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4771(0))).wtPort(x4488_mc.vdata).rdAddr(x4771(0))
      val x4262_x4869 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4285_mc.vdata).rdAddr(x4857(0))
      val x4263_x4866 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4327_mc.vdata).rdAddr(x4857(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4771(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1639)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1639), CU.ctr(stage(1), x4857(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x4361_x4860_addr_vector)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4357_x4863), Const("1i")), op=FixEql, results=List(CU.temp(stage(3), tr1649)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1649), CU.load(stage(3), x4263_x4866), CU.load(stage(3), x4262_x4869)), op=Mux, results=List(CU.vecOut(stage(4), bus_1650_vector)))
    }
    val x4883_1 = StreamPipeline(name = "x4883_1", parent=x4883, deps=List(x4883_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4857 = CounterChain.copy(x4883, "x4857")
      val x4771 = CounterChain.copy(x4921, "x4771")
      val x4361_x4860 = SemiFIFO(size = 960, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4771(0))).wtPort(x4535_mc.vdata)
      val x4361_x4860_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4361_x4860_addr_vector)
      val bus_1650_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1650_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4361_x4860_addr_fifo.load), op=Bypass, results=List(x4361_x4860.readAddr))
      Stage(stage(2), operands=List(x4361_x4860.load, bus_1650_fifo.load), op=FltSub, results=List(CU.vecOut(stage(2), x4854_vector)))
    }
    val x4902_0 = Pipeline(name = "x4902_0", parent=x4921, deps=List(x4883)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr53 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr54 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4886 = CounterChain(name = "x4886", ctr53, ctr54)
      val x4857 = CounterChain.copy(x4883, "x4857")
      val x4854_x4889 = SRAM(size = 48, writeCtr = x4857(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4886(0), swapWrite = x4857(0))).wtPort(x4854_vector).rdAddr(x4886(0)).wtAddr(x4857(0))
      val x4854_x4892 = SRAM(size = 48, writeCtr = x4857(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4886(0), swapWrite = x4857(0))).wtPort(x4854_vector).rdAddr(x4886(1)).wtAddr(x4857(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4854_x4889.load, x4854_x4892.load), op=FltMul, results=List(CU.vecOut(stage(1), x4855_vector)))
    }
    val x4919_0 = Pipeline(name = "x4919_0", parent=x4921, deps=List(x4902_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1683 = CU.temp
      val tr1673 = CU.temp
      val tr1669 = CU.temp
      val tr1667 = CU.temp
      val tr1666 = CU.temp
      val ctr57 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr58 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4783 = CounterChain(name = "x4783", ctr57, ctr58)
      val x4886 = CounterChain.copy(x4902_0, "x4886")
      val x4759_x4908 = SRAM(size = 2304, writeCtr = x4783(0), banking = Strided(1), buffering = SingleBuffer())
      val x4855_x4905 = SRAM(size = 2304, writeCtr = x4886(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4783(0), swapWrite = x4886(0))).wtPort(x4855_vector)
      val wr1685 = CU.wtAddr(x4759_x4908)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4855_x4905))
      Stage(stage(1), operands=List(x4886(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1666)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1666), CU.ctr(stage(1), x4886(1))), op=FixAdd, results=List(x4855_x4905.writeAddr, CU.temp(stage(2), tr1667)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4783(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1669)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1669), CU.ctr(stage(1), x4783(1))), op=FixAdd, results=List(x4855_x4905.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4783(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr1673)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1673), CU.ctr(stage(3), x4783(1))), op=FixAdd, results=List(x4759_x4908.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x4855_x4905), x4759_x4908.load), op=FltAdd, results=List(CU.vecOut(stage(5), x4759_vector), CU.store(stage(5), x4759_x4908)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x4783(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr1683)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1683), CU.ctr(stage(6), x4783(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr1685)))
    }
    val x4989 = MetaPipeline(name = "x4989", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, CU.scalarIn(stage0, x4366_scalar).out, Const("1i").out) // Counter
      val x4772 = CounterChain(name = "x4772", ctr13)
      var stage: List[Stage] = Nil
    }
    val x4951 = StreamController(name = "x4951", parent=x4989, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr14 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4925 = CounterChain(name = "x4925", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4951_0 = StreamPipeline(name = "x4951_0", parent=x4951, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1707 = CU.temp
      val tr1697 = CU.temp
      val x4772 = CounterChain.copy(x4989, "x4772")
      val x4925 = CounterChain.copy(x4951, "x4925")
      val x4262_x4937 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4285_mc.vdata).rdAddr(x4925(0))
      val x4358_x4931 = SemiFIFO(size = 20, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4772(0))).wtPort(x4585_mc.vdata).rdAddr(x4772(0))
      val x4263_x4934 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4327_mc.vdata).rdAddr(x4925(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4772(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1697)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1697), CU.ctr(stage(1), x4925(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x4362_x4928_addr_vector)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4358_x4931), Const("1i")), op=FixEql, results=List(CU.temp(stage(3), tr1707)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1707), CU.load(stage(3), x4263_x4934), CU.load(stage(3), x4262_x4937)), op=Mux, results=List(CU.vecOut(stage(4), bus_1708_vector)))
    }
    val x4951_1 = StreamPipeline(name = "x4951_1", parent=x4951, deps=List(x4951_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4772 = CounterChain.copy(x4989, "x4772")
      val x4925 = CounterChain.copy(x4951, "x4925")
      val x4362_x4928 = SemiFIFO(size = 960, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4772(0))).wtPort(x4632_mc.vdata)
      val x4362_x4928_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4362_x4928_addr_vector)
      val bus_1708_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1708_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4362_x4928_addr_fifo.load), op=Bypass, results=List(x4362_x4928.readAddr))
      Stage(stage(2), operands=List(x4362_x4928.load, bus_1708_fifo.load), op=FltSub, results=List(CU.vecOut(stage(2), x4922_vector)))
    }
    val x4970_0 = Pipeline(name = "x4970_0", parent=x4989, deps=List(x4951)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr59 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr60 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4954 = CounterChain(name = "x4954", ctr59, ctr60)
      val x4925 = CounterChain.copy(x4951, "x4925")
      val x4922_x4957 = SRAM(size = 48, writeCtr = x4925(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4954(0), swapWrite = x4925(0))).wtPort(x4922_vector).rdAddr(x4954(0)).wtAddr(x4925(0))
      val x4922_x4960 = SRAM(size = 48, writeCtr = x4925(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4954(0), swapWrite = x4925(0))).wtPort(x4922_vector).rdAddr(x4954(1)).wtAddr(x4925(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4922_x4957.load, x4922_x4960.load), op=FltMul, results=List(CU.vecOut(stage(1), x4923_vector)))
    }
    val x4987_0 = Pipeline(name = "x4987_0", parent=x4989, deps=List(x4970_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1741 = CU.temp
      val tr1731 = CU.temp
      val tr1727 = CU.temp
      val tr1725 = CU.temp
      val tr1724 = CU.temp
      val ctr63 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr64 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4784 = CounterChain(name = "x4784", ctr63, ctr64)
      val x4954 = CounterChain.copy(x4970_0, "x4954")
      val x4760_x4976 = SRAM(size = 2304, writeCtr = x4784(0), banking = Strided(1), buffering = SingleBuffer())
      val x4923_x4973 = SRAM(size = 2304, writeCtr = x4954(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4784(0), swapWrite = x4954(0))).wtPort(x4923_vector)
      val wr1743 = CU.wtAddr(x4760_x4976)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4923_x4973))
      Stage(stage(1), operands=List(x4954(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1724)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1724), CU.ctr(stage(1), x4954(1))), op=FixAdd, results=List(x4923_x4973.writeAddr, CU.temp(stage(2), tr1725)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4784(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1727)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1727), CU.ctr(stage(1), x4784(1))), op=FixAdd, results=List(x4923_x4973.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4784(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr1731)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1731), CU.ctr(stage(3), x4784(1))), op=FixAdd, results=List(x4760_x4976.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x4923_x4973), x4760_x4976.load), op=FltAdd, results=List(CU.vecOut(stage(5), x4760_vector), CU.store(stage(5), x4760_x4976)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x4784(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr1741)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1741), CU.ctr(stage(6), x4784(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr1743)))
    }
    val x5057 = MetaPipeline(name = "x5057", parent=x5096, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr16 = (Const("0i").out, CU.scalarIn(stage0, x4367_scalar).out, Const("1i").out) // Counter
      val x4773 = CounterChain(name = "x4773", ctr16)
      var stage: List[Stage] = Nil
    }
    val x5019 = StreamController(name = "x5019", parent=x5057, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr17 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4993 = CounterChain(name = "x4993", ctr17)
      var stage: List[Stage] = Nil
    }
    val x5019_0 = StreamPipeline(name = "x5019_0", parent=x5019, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1765 = CU.temp
      val tr1755 = CU.temp
      val x4993 = CounterChain.copy(x5019, "x4993")
      val x4773 = CounterChain.copy(x5057, "x4773")
      val x4262_x5005 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4285_mc.vdata).rdAddr(x4993(0))
      val x4359_x4999 = SemiFIFO(size = 20, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4773(0))).wtPort(x4682_mc.vdata).rdAddr(x4773(0))
      val x4263_x5002 = SemiFIFO(size = 48, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4327_mc.vdata).rdAddr(x4993(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4773(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1755)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1755), CU.ctr(stage(1), x4993(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x4363_x4996_addr_vector)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4359_x4999), Const("1i")), op=FixEql, results=List(CU.temp(stage(3), tr1765)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1765), CU.load(stage(3), x4263_x5002), CU.load(stage(3), x4262_x5005)), op=Mux, results=List(CU.vecOut(stage(4), bus_1766_vector)))
    }
    val x5019_1 = StreamPipeline(name = "x5019_1", parent=x5019, deps=List(x5019_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4993 = CounterChain.copy(x5019, "x4993")
      val x4773 = CounterChain.copy(x5057, "x4773")
      val x4363_x4996 = SemiFIFO(size = 960, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4773(0))).wtPort(x4729_mc.vdata)
      val x4363_x4996_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4363_x4996_addr_vector)
      val bus_1766_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1766_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4363_x4996_addr_fifo.load), op=Bypass, results=List(x4363_x4996.readAddr))
      Stage(stage(2), operands=List(x4363_x4996.load, bus_1766_fifo.load), op=FltSub, results=List(CU.vecOut(stage(2), x4990_vector)))
    }
    val x5038_0 = Pipeline(name = "x5038_0", parent=x5057, deps=List(x5019)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr65 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr66 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5022 = CounterChain(name = "x5022", ctr65, ctr66)
      val x4993 = CounterChain.copy(x5019, "x4993")
      val x4990_x5025 = SRAM(size = 48, writeCtr = x4993(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5022(0), swapWrite = x4993(0))).wtPort(x4990_vector).rdAddr(x5022(0)).wtAddr(x4993(0))
      val x4990_x5028 = SRAM(size = 48, writeCtr = x4993(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5022(0), swapWrite = x4993(0))).wtPort(x4990_vector).rdAddr(x5022(1)).wtAddr(x4993(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4990_x5025.load, x4990_x5028.load), op=FltMul, results=List(CU.vecOut(stage(1), x4991_vector)))
    }
    val x5055_0 = Pipeline(name = "x5055_0", parent=x5057, deps=List(x5038_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1799 = CU.temp
      val tr1789 = CU.temp
      val tr1785 = CU.temp
      val tr1783 = CU.temp
      val tr1782 = CU.temp
      val ctr69 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr70 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x4785 = CounterChain(name = "x4785", ctr69, ctr70)
      val x5022 = CounterChain.copy(x5038_0, "x5022")
      val x4761_x5044 = SRAM(size = 2304, writeCtr = x4785(0), banking = Strided(1), buffering = SingleBuffer())
      val x4991_x5041 = SRAM(size = 2304, writeCtr = x5022(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4785(0), swapWrite = x5022(0))).wtPort(x4991_vector)
      val wr1801 = CU.wtAddr(x4761_x5044)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4991_x5041))
      Stage(stage(1), operands=List(x5022(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1782)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1782), CU.ctr(stage(1), x5022(1))), op=FixAdd, results=List(x4991_x5041.writeAddr, CU.temp(stage(2), tr1783)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4785(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1785)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1785), CU.ctr(stage(1), x4785(1))), op=FixAdd, results=List(x4991_x5041.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4785(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr1789)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1789), CU.ctr(stage(3), x4785(1))), op=FixAdd, results=List(x4761_x5044.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x4991_x5041), x4761_x5044.load), op=FltAdd, results=List(CU.vecOut(stage(5), x4761_vector), CU.store(stage(5), x4761_x5044)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x4785(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr1799)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1799), CU.ctr(stage(6), x4785(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr1801)))
    }
    val x5094 = StreamController(name = "x5094", parent=x5096, deps=List(x4853, x4921, x4989, x5057)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr51 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val ctr52 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x4355 = CounterChain(name = "x4355", ctr51, ctr52)
      var stage: List[Stage] = Nil
    }
    val x5094_0 = StreamPipeline(name = "x5094_0", parent=x5094, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1820 = CU.temp
      val tr1816 = CU.temp
      val x4355 = CounterChain.copy(x5094, "x4355")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1816)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1816), CU.ctr(stage(1), x4355(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x4758_x5062_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr1820)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1820), CU.ctr(stage(3), x4355(1))), op=FixAdd, results=List(CU.vecOut(stage(4), x4759_x5065_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x4355(0)), Const("48i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_1824_vector)))
    }
    val x5094_1 = StreamPipeline(name = "x5094_1", parent=x5094, deps=List(x5094_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1828 = CU.temp
      val x4355 = CounterChain.copy(x5094, "x4355")
      val bus_1824_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1824_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_1824_fifo.load, CU.ctr(stage(0), x4355(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x4760_x5068_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(2), tr1828)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1828), CU.ctr(stage(2), x4355(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x4761_x5071_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x4355(0)), Const("48i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_1832_vector)))
    }
    val x5094_2 = StreamPipeline(name = "x5094_2", parent=x5094, deps=List(x5094_1)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4355 = CounterChain.copy(x5096, "x4355")
      val bus_1832_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1832_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_1832_fifo.load, CU.ctr(stage(0), x4355(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x4350_x5074_addr_vector)))
    }
    val x5094_3 = StreamPipeline(name = "x5094_3", parent=x5094, deps=List(x5094_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1805 = CU.temp
      val tr1804 = CU.temp
      val x4782 = CounterChain.copy(x4851_0, "x4782")
      val x4770 = CounterChain.copy(x4853, "x4770")
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x4758_x5062 = SRAM(size = 2304, writeCtr = x4782(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4355(0), swapWrite = x4770(0))).wtPort(x4758_vector)
      val x4758_x5062_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4758_x5062_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4758_x5062))
      Stage(stage(1), operands=List(x4782(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1804)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1804), CU.ctr(stage(1), x4782(1))), op=FixAdd, results=List(x4758_x5062.writeAddr, CU.temp(stage(2), tr1805)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4758_x5062_addr_fifo.load), op=Bypass, results=List(x4758_x5062.readAddr))
      Stage(stage(2), operands=List(x4758_x5062.load), op=Bypass, results=List(CU.vecOut(stage(2), x4758_x5062_data_vector)))
    }
    val x5094_4 = StreamPipeline(name = "x5094_4", parent=x5094, deps=List(x5094_0, x5094_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1808 = CU.temp
      val tr1807 = CU.temp
      val x4783 = CounterChain.copy(x4919_0, "x4783")
      val x4771 = CounterChain.copy(x4921, "x4771")
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x4759_x5065 = SRAM(size = 2304, writeCtr = x4783(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4355(0), swapWrite = x4771(0))).wtPort(x4759_vector)
      val x4759_x5065_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4759_x5065_addr_vector)
      val x4758_x5062_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4758_x5062_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4759_x5065))
      Stage(stage(1), operands=List(x4783(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1807)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1807), CU.ctr(stage(1), x4783(1))), op=FixAdd, results=List(x4759_x5065.writeAddr, CU.temp(stage(2), tr1808)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4759_x5065_addr_fifo.load), op=Bypass, results=List(x4759_x5065.readAddr))
      Stage(stage(2), operands=List(x4758_x5062_data_fifo.load, x4759_x5065.load), op=FltAdd, results=List(CU.vecOut(stage(2), bus_1840_vector)))
    }
    val x5094_5 = StreamPipeline(name = "x5094_5", parent=x5094, deps=List(x5094_1)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1811 = CU.temp
      val tr1810 = CU.temp
      val x4772 = CounterChain.copy(x4989, "x4772")
      val x4784 = CounterChain.copy(x4987_0, "x4784")
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x4760_x5068 = SRAM(size = 2304, writeCtr = x4784(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4355(0), swapWrite = x4772(0))).wtPort(x4760_vector)
      val x4760_x5068_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4760_x5068_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4760_x5068))
      Stage(stage(1), operands=List(x4784(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1810)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1810), CU.ctr(stage(1), x4784(1))), op=FixAdd, results=List(x4760_x5068.writeAddr, CU.temp(stage(2), tr1811)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4760_x5068_addr_fifo.load), op=Bypass, results=List(x4760_x5068.readAddr))
      Stage(stage(2), operands=List(x4760_x5068.load), op=Bypass, results=List(CU.vecOut(stage(2), x4760_x5068_data_vector)))
    }
    val x5094_6 = StreamPipeline(name = "x5094_6", parent=x5094, deps=List(x5094_1, x5094_5)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1814 = CU.temp
      val tr1813 = CU.temp
      val x4785 = CounterChain.copy(x5055_0, "x4785")
      val x4773 = CounterChain.copy(x5057, "x4773")
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x4761_x5071 = SRAM(size = 2304, writeCtr = x4785(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4355(0), swapWrite = x4773(0))).wtPort(x4761_vector)
      val x4761_x5071_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4761_x5071_addr_vector)
      val x4760_x5068_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4760_x5068_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4761_x5071))
      Stage(stage(1), operands=List(x4785(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1813)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1813), CU.ctr(stage(1), x4785(1))), op=FixAdd, results=List(x4761_x5071.writeAddr, CU.temp(stage(2), tr1814)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4761_x5071_addr_fifo.load), op=Bypass, results=List(x4761_x5071.readAddr))
      Stage(stage(2), operands=List(x4760_x5068_data_fifo.load, x4761_x5071.load), op=FltAdd, results=List(CU.vecOut(stage(2), bus_1843_vector)))
    }
    val x5094_7 = StreamPipeline(name = "x5094_7", parent=x5094, deps=List(x5094_2, x5094_4, x5094_6)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1846 = CU.temp
      val tr1844 = CU.temp
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x4350_x5074 = SRAM(size = 2304, writeCtr = x4355(0), banking = Strided(1), buffering = SingleBuffer())
      val x4350_x5074_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4350_x5074_addr_vector)
      val bus_1840_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1840_vector)
      val bus_1843_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1843_vector)
      val wr1848 = CU.wtAddr(x4350_x5074)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4350_x5074_addr_fifo.load), op=Bypass, results=List(x4350_x5074.readAddr))
      Stage(stage(2), operands=List(bus_1840_fifo.load, bus_1843_fifo.load), op=FltAdd, results=List(CU.temp(stage(2), tr1844)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1844), CU.load(stage(2), x4350_x5074)), op=FltAdd, results=List(CU.vecOut(stage(3), x4350_vector), CU.store(stage(3), x4350_x5074)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x4355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(4), tr1846)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1846), CU.ctr(stage(4), x4355(1))), op=FixAdd, results=List(CU.wtAddr(stage(5), wr1848)))
    }
    val x5118 = StreamController(name = "x5118", parent=x5120, deps=List(x5096)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr71 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x5099 = CounterChain(name = "x5099", ctr71)
      var stage: List[Stage] = Nil
    }
    val x5120_leafX = UnitPipeline(name = "x5120_leafX", parent=x5120, deps=List(x5118)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5120_unitcc = CounterChain(name = "x5120_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5103_0 = UnitPipeline(name = "x5103_0", parent=x5118, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5099 = CounterChain.copy(x5118, "x5099")
      val x5103_unitcc = CounterChain(name = "x5103_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5099(0)), Const("48i")), op=FixMul, results=List(CU.scalarOut(stage(1), x5116_mc.ofs)))
      Stage(stage(2), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(2), x5116_mc.len)))
    }
    val x5114_0 = Pipeline(name = "x5114_0", parent=x5118, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr1858 = CU.temp
      val tr1856 = CU.temp
      val tr1855 = CU.temp
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x5099 = CounterChain.copy(x5118, "x5099")
      val ctr73 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x5105 = CounterChain(name = "x5105", ctr73)
      val x4350_x5108 = SRAM(size = 2304, writeCtr = x4355(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4350_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4350_x5108))
      Stage(stage(1), operands=List(x4355(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1855)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1855), CU.ctr(stage(1), x4355(1))), op=FixAdd, results=List(x4350_x5108.writeAddr, CU.temp(stage(2), tr1856)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5099(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr1858)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1858), CU.ctr(stage(1), x5105(0))), op=FixAdd, results=List(x4350_x5108.readAddr))
      Stage(stage(3), operands=List(x4350_x5108.load), op=Bypass, results=List(CU.vecOut(stage(3), x5116_mc.vdata)))
    }
    val x5096_leaf = UnitPipeline(name = "x5096_leaf", parent=x5096, deps=List(x5094, x4462_0, x4509, x4459, x4753_0, x4606, x4559_0, x4412, x4656_0, x4556, x4750, x4653, x4703)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x4352 = CounterChain.copy(x5096, "x4352")
      val x4355 = CounterChain.copy(x5094, "x4355")
      val x5096_unitcc = CounterChain(name = "x5096_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }

  }
}
