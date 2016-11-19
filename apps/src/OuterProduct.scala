import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4094_scalar = Scalar("x4094")
    val x4087_vector = Vector("x4087")
    val x4097_scalar = Scalar("x4097")
    val x4099_scalar = Scalar("x4099")
    val x4093_scalar = Scalar("x4093")
    val x4085_vector = Vector("x4085")
    val x4092_scalar = Scalar("x4092")
    val x4090_vector = Vector("x4090")
    val x4091_scalar = Scalar("x4091")
    val x4101_scalar = Scalar("x4101")
    val x4088_vector = Vector("x4088")
    val x4102_scalar = Scalar("x4102")
    val x4089_vector = Vector("x4089")
    val x4098_scalar = Scalar("x4098")
    val x3992_oc = OffChip("x3992")
    val x3993_oc = OffChip("x3993")
    val x3991_oc = OffChip("x3991")
    val x4096_scalar = Scalar("x4096")
    val x4100_scalar = Scalar("x4100")
    val x4095_scalar = Scalar("x4095")
    val x4086_vector = Vector("x4086")
    val x4465_mc = MemoryController(TileLoad, x3992_oc)
    val x4911_mc = MemoryController(TileStore, x3993_oc)
    val x4892_mc = MemoryController(TileStore, x3993_oc)
    val x4171_mc = MemoryController(TileLoad, x3992_oc)
    val x4420_mc = MemoryController(TileLoad, x3991_oc)
    val x4968_mc = MemoryController(TileStore, x3993_oc)
    val x4322_mc = MemoryController(TileLoad, x3991_oc)
    val x4949_mc = MemoryController(TileStore, x3993_oc)
    val x4126_mc = MemoryController(TileLoad, x3991_oc)
    val x4518_mc = MemoryController(TileLoad, x3991_oc)
    val x4661_mc = MemoryController(TileLoad, x3992_oc)
    val x4269_mc = MemoryController(TileLoad, x3992_oc)
    val x4563_mc = MemoryController(TileLoad, x3992_oc)
    val x4873_mc = MemoryController(TileStore, x3993_oc)
    val x4616_mc = MemoryController(TileLoad, x3991_oc)
    val x4224_mc = MemoryController(TileLoad, x3991_oc)
    val x4930_mc = MemoryController(TileStore, x3993_oc)
    val x4367_mc = MemoryController(TileLoad, x3992_oc)
    val x4976 = Sequential(name = "x4976", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4976_unitcc = CounterChain(name = "x4976_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4974 = MetaPipeline(name = "x4974", parent=x4976, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const(s"${76800/6}i").out, Const("48i").out) // Counter
      val ctr2 = (Const("0i").out, Const("76800i").out, Const("48i").out) // Counter
      val x4072 = CounterChain(name = "x4072", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x4147 = StreamController(name = "x4147", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4147_unitcc = CounterChain(name = "x4147_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4122_0 = UnitPipeline(name = "x4122_0", parent=x4147, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1854 = CU.temp
      val tr1853 = CU.temp
      val tr1851 = CU.temp
      val tr1850 = CU.temp
      val tr1848 = CU.temp
      val tr1843 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4122_unitcc = CounterChain(name = "x4122_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1843)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr1843)), op=FixSub, results=List(CU.scalarOut(stage(2), x4126_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1843), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1848)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1848), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1850)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1848), CU.temp(stage(4), tr1850)), op=FixSub, results=List(CU.temp(stage(5), tr1851)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1850), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1853)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1853), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1854)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1851), CU.temp(stage(7), tr1854)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4126_mc.len)))
    }
    val x4192 = StreamController(name = "x4192", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4192_unitcc = CounterChain(name = "x4192_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4167_0 = UnitPipeline(name = "x4167_0", parent=x4192, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1878 = CU.temp
      val tr1877 = CU.temp
      val tr1875 = CU.temp
      val tr1874 = CU.temp
      val tr1872 = CU.temp
      val tr1867 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4167_unitcc = CounterChain(name = "x4167_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1867)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr1867)), op=FixSub, results=List(CU.scalarOut(stage(2), x4171_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1867), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1872)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1872), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1874)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1872), CU.temp(stage(4), tr1874)), op=FixSub, results=List(CU.temp(stage(5), tr1875)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1874), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1877)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1877), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1878)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1875), CU.temp(stage(7), tr1878)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4171_mc.len)))
    }
    val x4195_0 = UnitPipeline(name = "x4195_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1891 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4195_unitcc = CounterChain(name = "x4195_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr1891)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1891), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4091_scalar)))
    }
    val x4198_0 = UnitPipeline(name = "x4198_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1896 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4198_unitcc = CounterChain(name = "x4198_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr1896)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1896), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4873_mc.len), CU.scalarOut(stage(2), x4097_scalar)))
    }
    val x4245 = StreamController(name = "x4245", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4245_unitcc = CounterChain(name = "x4245_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4220_0 = UnitPipeline(name = "x4220_0", parent=x4245, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1912 = CU.temp
      val tr1911 = CU.temp
      val tr1909 = CU.temp
      val tr1908 = CU.temp
      val tr1906 = CU.temp
      val tr1901 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4220_unitcc = CounterChain(name = "x4220_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1901)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr1901)), op=FixSub, results=List(CU.scalarOut(stage(2), x4224_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1901), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1906)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1906), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1908)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1906), CU.temp(stage(4), tr1908)), op=FixSub, results=List(CU.temp(stage(5), tr1909)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1908), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1911)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1911), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1912)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1909), CU.temp(stage(7), tr1912)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4224_mc.len)))
    }
    val x4290 = StreamController(name = "x4290", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4290_unitcc = CounterChain(name = "x4290_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4265_0 = UnitPipeline(name = "x4265_0", parent=x4290, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1936 = CU.temp
      val tr1935 = CU.temp
      val tr1933 = CU.temp
      val tr1932 = CU.temp
      val tr1930 = CU.temp
      val tr1925 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4265_unitcc = CounterChain(name = "x4265_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1925)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr1925)), op=FixSub, results=List(CU.scalarOut(stage(2), x4269_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1925), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1930)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1930), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1932)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1930), CU.temp(stage(4), tr1932)), op=FixSub, results=List(CU.temp(stage(5), tr1933)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1932), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1935)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1935), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1936)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1933), CU.temp(stage(7), tr1936)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4269_mc.len)))
    }
    val x4293_0 = UnitPipeline(name = "x4293_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1949 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4293_unitcc = CounterChain(name = "x4293_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr1949)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1949), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4092_scalar)))
    }
    val x4296_0 = UnitPipeline(name = "x4296_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1954 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4296_unitcc = CounterChain(name = "x4296_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr1954)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1954), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4892_mc.len), CU.scalarOut(stage(2), x4098_scalar)))
    }
    val x4343 = StreamController(name = "x4343", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4343_unitcc = CounterChain(name = "x4343_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4318_0 = UnitPipeline(name = "x4318_0", parent=x4343, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1970 = CU.temp
      val tr1969 = CU.temp
      val tr1967 = CU.temp
      val tr1966 = CU.temp
      val tr1964 = CU.temp
      val tr1959 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4318_unitcc = CounterChain(name = "x4318_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1959)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr1959)), op=FixSub, results=List(CU.scalarOut(stage(2), x4322_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1959), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1964)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1964), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1966)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1964), CU.temp(stage(4), tr1966)), op=FixSub, results=List(CU.temp(stage(5), tr1967)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1966), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1969)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1969), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1970)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1967), CU.temp(stage(7), tr1970)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4322_mc.len)))
    }
    val x4388 = StreamController(name = "x4388", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4388_unitcc = CounterChain(name = "x4388_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4363_0 = UnitPipeline(name = "x4363_0", parent=x4388, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1994 = CU.temp
      val tr1993 = CU.temp
      val tr1991 = CU.temp
      val tr1990 = CU.temp
      val tr1988 = CU.temp
      val tr1983 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4363_unitcc = CounterChain(name = "x4363_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1983)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr1983)), op=FixSub, results=List(CU.scalarOut(stage(2), x4367_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1983), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr1988)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1988), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1990)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1988), CU.temp(stage(4), tr1990)), op=FixSub, results=List(CU.temp(stage(5), tr1991)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1990), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1993)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1993), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1994)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1991), CU.temp(stage(7), tr1994)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4367_mc.len)))
    }
    val x4391_0 = UnitPipeline(name = "x4391_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2007 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4391_unitcc = CounterChain(name = "x4391_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr2007)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2007), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4093_scalar)))
    }
    val x4394_0 = UnitPipeline(name = "x4394_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2012 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4394_unitcc = CounterChain(name = "x4394_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr2012)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2012), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4911_mc.len), CU.scalarOut(stage(2), x4099_scalar)))
    }
    val x4441 = StreamController(name = "x4441", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4441_unitcc = CounterChain(name = "x4441_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4416_0 = UnitPipeline(name = "x4416_0", parent=x4441, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2028 = CU.temp
      val tr2027 = CU.temp
      val tr2025 = CU.temp
      val tr2024 = CU.temp
      val tr2022 = CU.temp
      val tr2017 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4416_unitcc = CounterChain(name = "x4416_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2017)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr2017)), op=FixSub, results=List(CU.scalarOut(stage(2), x4420_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2017), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2022)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2022), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2024)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2022), CU.temp(stage(4), tr2024)), op=FixSub, results=List(CU.temp(stage(5), tr2025)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2024), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2027)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2027), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2028)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2025), CU.temp(stage(7), tr2028)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4420_mc.len)))
    }
    val x4486 = StreamController(name = "x4486", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4486_unitcc = CounterChain(name = "x4486_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4461_0 = UnitPipeline(name = "x4461_0", parent=x4486, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2052 = CU.temp
      val tr2051 = CU.temp
      val tr2049 = CU.temp
      val tr2048 = CU.temp
      val tr2046 = CU.temp
      val tr2041 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4461_unitcc = CounterChain(name = "x4461_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2041)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr2041)), op=FixSub, results=List(CU.scalarOut(stage(2), x4465_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2041), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2046)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2046), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2048)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2046), CU.temp(stage(4), tr2048)), op=FixSub, results=List(CU.temp(stage(5), tr2049)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2048), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2051)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2051), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2052)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2049), CU.temp(stage(7), tr2052)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4465_mc.len)))
    }
    val x4489_0 = UnitPipeline(name = "x4489_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2065 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4489_unitcc = CounterChain(name = "x4489_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr2065)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2065), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4094_scalar)))
    }
    val x4492_0 = UnitPipeline(name = "x4492_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2070 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4492_unitcc = CounterChain(name = "x4492_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr2070)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2070), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4930_mc.len), CU.scalarOut(stage(2), x4100_scalar)))
    }
    val x4539 = StreamController(name = "x4539", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4539_unitcc = CounterChain(name = "x4539_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4514_0 = UnitPipeline(name = "x4514_0", parent=x4539, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2086 = CU.temp
      val tr2085 = CU.temp
      val tr2083 = CU.temp
      val tr2082 = CU.temp
      val tr2080 = CU.temp
      val tr2075 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4514_unitcc = CounterChain(name = "x4514_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2075)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr2075)), op=FixSub, results=List(CU.scalarOut(stage(2), x4518_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2075), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2080)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2080), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2082)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2080), CU.temp(stage(4), tr2082)), op=FixSub, results=List(CU.temp(stage(5), tr2083)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2082), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2085)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2085), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2086)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2083), CU.temp(stage(7), tr2086)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4518_mc.len)))
    }
    val x4584 = StreamController(name = "x4584", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4584_unitcc = CounterChain(name = "x4584_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4559_0 = UnitPipeline(name = "x4559_0", parent=x4584, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2110 = CU.temp
      val tr2109 = CU.temp
      val tr2107 = CU.temp
      val tr2106 = CU.temp
      val tr2104 = CU.temp
      val tr2099 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4559_unitcc = CounterChain(name = "x4559_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2099)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr2099)), op=FixSub, results=List(CU.scalarOut(stage(2), x4563_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2099), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2104)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2104), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2106)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2104), CU.temp(stage(4), tr2106)), op=FixSub, results=List(CU.temp(stage(5), tr2107)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2106), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2109)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2109), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2110)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2107), CU.temp(stage(7), tr2110)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4563_mc.len)))
    }
    val x4587_0 = UnitPipeline(name = "x4587_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2123 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4587_unitcc = CounterChain(name = "x4587_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr2123)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2123), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4095_scalar)))
    }
    val x4590_0 = UnitPipeline(name = "x4590_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2128 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4590_unitcc = CounterChain(name = "x4590_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr2128)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2128), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4949_mc.len), CU.scalarOut(stage(2), x4101_scalar)))
    }
    val x4637 = StreamController(name = "x4637", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4637_unitcc = CounterChain(name = "x4637_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4612_0 = UnitPipeline(name = "x4612_0", parent=x4637, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2144 = CU.temp
      val tr2143 = CU.temp
      val tr2141 = CU.temp
      val tr2140 = CU.temp
      val tr2138 = CU.temp
      val tr2133 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4612_unitcc = CounterChain(name = "x4612_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2133)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(0)), CU.temp(stage(1), tr2133)), op=FixSub, results=List(CU.scalarOut(stage(2), x4616_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2133), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2138)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2138), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2140)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2138), CU.temp(stage(4), tr2140)), op=FixSub, results=List(CU.temp(stage(5), tr2141)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2140), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2143)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2143), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2144)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2141), CU.temp(stage(7), tr2144)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4616_mc.len)))
    }
    val x4682 = StreamController(name = "x4682", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4682_unitcc = CounterChain(name = "x4682_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4657_0 = UnitPipeline(name = "x4657_0", parent=x4682, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2168 = CU.temp
      val tr2167 = CU.temp
      val tr2165 = CU.temp
      val tr2164 = CU.temp
      val tr2162 = CU.temp
      val tr2157 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4657_unitcc = CounterChain(name = "x4657_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(1)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2157)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4072(1)), CU.temp(stage(1), tr2157)), op=FixSub, results=List(CU.scalarOut(stage(2), x4661_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2157), Const("48i")), op=FixAdd, results=List(CU.temp(stage(3), tr2162)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2162), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2164)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2162), CU.temp(stage(4), tr2164)), op=FixSub, results=List(CU.temp(stage(5), tr2165)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2164), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2167)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2167), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2168)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2165), CU.temp(stage(7), tr2168)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4661_mc.len)))
    }
    val x4685_0 = UnitPipeline(name = "x4685_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2181 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4685_unitcc = CounterChain(name = "x4685_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(0))), op=FixSub, results=List(CU.temp(stage(1), tr2181)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2181), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4096_scalar)))
    }
    val x4688_0 = UnitPipeline(name = "x4688_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2186 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4688_unitcc = CounterChain(name = "x4688_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("76800i"), CU.ctr(stage(0), x4072(1))), op=FixSub, results=List(CU.temp(stage(1), tr2186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2186), Const("48i")), op=FixMin, results=List(CU.scalarOut(stage(2), x4968_mc.len), CU.scalarOut(stage(2), x4102_scalar)))
    }
    val x4756_0 = Pipeline(name = "x4756_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x4091_scalar).out, Const("1i").out) // Counter
      val ctr8 = (Const("0i").out, CU.scalarIn(stage0, x4097_scalar).out, Const("16i").out) // Counter
      val x4735 = CounterChain(name = "x4735", ctr7, ctr8)
      val x4073_x4743 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4735(0))).wtPort(x4126_mc.vdata).rdAddr(x4735(0))
      val x4079_x4746 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4735(0))).wtPort(x4171_mc.vdata).rdAddr(x4735(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4073_x4743.load, x4079_x4746.load), op=FixMul, results=List(CU.vecOut(stage(1), x4085_vector)))
    }
    val x4772_0 = Pipeline(name = "x4772_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, CU.scalarIn(stage0, x4092_scalar).out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, CU.scalarIn(stage0, x4098_scalar).out, Const("16i").out) // Counter
      val x4736 = CounterChain(name = "x4736", ctr13, ctr14)
      val x4074_x4759 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4736(0))).wtPort(x4224_mc.vdata).rdAddr(x4736(0))
      val x4080_x4762 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4736(0))).wtPort(x4269_mc.vdata).rdAddr(x4736(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4074_x4759.load, x4080_x4762.load), op=FixMul, results=List(CU.vecOut(stage(1), x4086_vector)))
    }
    val x4788_0 = Pipeline(name = "x4788_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr19 = (Const("0i").out, CU.scalarIn(stage0, x4093_scalar).out, Const("1i").out) // Counter
      val ctr20 = (Const("0i").out, CU.scalarIn(stage0, x4099_scalar).out, Const("16i").out) // Counter
      val x4737 = CounterChain(name = "x4737", ctr19, ctr20)
      val x4075_x4775 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4737(0))).wtPort(x4322_mc.vdata).rdAddr(x4737(0))
      val x4081_x4778 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4737(0))).wtPort(x4367_mc.vdata).rdAddr(x4737(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4075_x4775.load, x4081_x4778.load), op=FixMul, results=List(CU.vecOut(stage(1), x4087_vector)))
    }
    val x4804_0 = Pipeline(name = "x4804_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, CU.scalarIn(stage0, x4094_scalar).out, Const("1i").out) // Counter
      val ctr26 = (Const("0i").out, CU.scalarIn(stage0, x4100_scalar).out, Const("16i").out) // Counter
      val x4738 = CounterChain(name = "x4738", ctr25, ctr26)
      val x4076_x4791 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4738(0))).wtPort(x4420_mc.vdata).rdAddr(x4738(0))
      val x4082_x4794 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4738(0))).wtPort(x4465_mc.vdata).rdAddr(x4738(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4076_x4791.load, x4082_x4794.load), op=FixMul, results=List(CU.vecOut(stage(1), x4088_vector)))
    }
    val x4820_0 = Pipeline(name = "x4820_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr31 = (Const("0i").out, CU.scalarIn(stage0, x4095_scalar).out, Const("1i").out) // Counter
      val ctr32 = (Const("0i").out, CU.scalarIn(stage0, x4101_scalar).out, Const("16i").out) // Counter
      val x4739 = CounterChain(name = "x4739", ctr31, ctr32)
      val x4077_x4807 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4739(0))).wtPort(x4518_mc.vdata).rdAddr(x4739(0))
      val x4083_x4810 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4739(0))).wtPort(x4563_mc.vdata).rdAddr(x4739(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4077_x4807.load, x4083_x4810.load), op=FixMul, results=List(CU.vecOut(stage(1), x4089_vector)))
    }
    val x4836_0 = Pipeline(name = "x4836_0", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr37 = (Const("0i").out, CU.scalarIn(stage0, x4096_scalar).out, Const("1i").out) // Counter
      val ctr38 = (Const("0i").out, CU.scalarIn(stage0, x4102_scalar).out, Const("16i").out) // Counter
      val x4740 = CounterChain(name = "x4740", ctr37, ctr38)
      val x4078_x4823 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4740(0))).wtPort(x4616_mc.vdata).rdAddr(x4740(0))
      val x4084_x4826 = SemiFIFO(size = 48, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4740(0))).wtPort(x4661_mc.vdata).rdAddr(x4740(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4078_x4823.load, x4084_x4826.load), op=FixMul, results=List(CU.vecOut(stage(1), x4090_vector)))
    }
    val x4875 = StreamController(name = "x4875", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr41 = (Const("0i").out, CU.scalarIn(stage0, x4091_scalar).out, Const("1i").out) // Counter
      val x4851 = CounterChain(name = "x4851", ctr41)
      var stage: List[Stage] = Nil
    }
    val x4860_0 = UnitPipeline(name = "x4860_0", parent=x4875, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2283 = CU.temp
      val tr2281 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4851 = CounterChain.copy(x4875, "x4851")
      val x4860_unitcc = CounterChain(name = "x4860_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4851(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2281)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2281), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2283)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2283), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4873_mc.ofs)))
    }
    val x4871_0 = Pipeline(name = "x4871_0", parent=x4875, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2291 = CU.temp
      val tr2288 = CU.temp
      val tr2287 = CU.temp
      val ctr47 = (Const("0i").out, CU.scalarIn(stage0, x4097_scalar).out, Const("16i").out) // Counter
      val x4862 = CounterChain(name = "x4862", ctr47)
      val x4851 = CounterChain.copy(x4875, "x4851")
      val x4735 = CounterChain.copy(x4756_0, "x4735")
      val x4085_x4865 = SRAM(size = 2304, writeCtr = x4735(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4851(0), swapWrite = x4735(0))).wtPort(x4085_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4085_x4865))
      Stage(stage(1), operands=List(x4735(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2287)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2287), CU.ctr(stage(1), x4735(1))), op=FixAdd, results=List(x4085_x4865.writeAddr, CU.temp(stage(2), tr2288)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4851(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2291)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2291), CU.ctr(stage(1), x4862(0))), op=FixAdd, results=List(x4085_x4865.readAddr))
      Stage(stage(3), operands=List(x4085_x4865.load), op=Bypass, results=List(CU.vecOut(stage(3), x4873_mc.vdata)))
    }
    val x4894 = StreamController(name = "x4894", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr42 = (Const("0i").out, CU.scalarIn(stage0, x4092_scalar).out, Const("1i").out) // Counter
      val x4852 = CounterChain(name = "x4852", ctr42)
      var stage: List[Stage] = Nil
    }
    val x4879_0 = UnitPipeline(name = "x4879_0", parent=x4894, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2304 = CU.temp
      val tr2302 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4852 = CounterChain.copy(x4894, "x4852")
      val x4879_unitcc = CounterChain(name = "x4879_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4852(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2302)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2302), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2304)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2304), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4892_mc.ofs)))
    }
    val x4890_0 = Pipeline(name = "x4890_0", parent=x4894, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2312 = CU.temp
      val tr2309 = CU.temp
      val tr2308 = CU.temp
      val x4852 = CounterChain.copy(x4894, "x4852")
      val ctr49 = (Const("0i").out, CU.scalarIn(stage0, x4098_scalar).out, Const("16i").out) // Counter
      val x4881 = CounterChain(name = "x4881", ctr49)
      val x4736 = CounterChain.copy(x4772_0, "x4736")
      val x4086_x4884 = SRAM(size = 2304, writeCtr = x4736(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4852(0), swapWrite = x4736(0))).wtPort(x4086_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4086_x4884))
      Stage(stage(1), operands=List(x4736(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2308)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2308), CU.ctr(stage(1), x4736(1))), op=FixAdd, results=List(x4086_x4884.writeAddr, CU.temp(stage(2), tr2309)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4852(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2312)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2312), CU.ctr(stage(1), x4881(0))), op=FixAdd, results=List(x4086_x4884.readAddr))
      Stage(stage(3), operands=List(x4086_x4884.load), op=Bypass, results=List(CU.vecOut(stage(3), x4892_mc.vdata)))
    }
    val x4913 = StreamController(name = "x4913", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr43 = (Const("0i").out, CU.scalarIn(stage0, x4093_scalar).out, Const("1i").out) // Counter
      val x4853 = CounterChain(name = "x4853", ctr43)
      var stage: List[Stage] = Nil
    }
    val x4898_0 = UnitPipeline(name = "x4898_0", parent=x4913, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2325 = CU.temp
      val tr2323 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4853 = CounterChain.copy(x4913, "x4853")
      val x4898_unitcc = CounterChain(name = "x4898_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4853(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2323)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2323), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2325)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2325), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4911_mc.ofs)))
    }
    val x4909_0 = Pipeline(name = "x4909_0", parent=x4913, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2333 = CU.temp
      val tr2330 = CU.temp
      val tr2329 = CU.temp
      val x4853 = CounterChain.copy(x4913, "x4853")
      val ctr51 = (Const("0i").out, CU.scalarIn(stage0, x4099_scalar).out, Const("16i").out) // Counter
      val x4900 = CounterChain(name = "x4900", ctr51)
      val x4737 = CounterChain.copy(x4788_0, "x4737")
      val x4087_x4903 = SRAM(size = 2304, writeCtr = x4737(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4853(0), swapWrite = x4737(0))).wtPort(x4087_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4087_x4903))
      Stage(stage(1), operands=List(x4737(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2329)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2329), CU.ctr(stage(1), x4737(1))), op=FixAdd, results=List(x4087_x4903.writeAddr, CU.temp(stage(2), tr2330)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4853(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2333)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2333), CU.ctr(stage(1), x4900(0))), op=FixAdd, results=List(x4087_x4903.readAddr))
      Stage(stage(3), operands=List(x4087_x4903.load), op=Bypass, results=List(CU.vecOut(stage(3), x4911_mc.vdata)))
    }
    val x4932 = StreamController(name = "x4932", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr44 = (Const("0i").out, CU.scalarIn(stage0, x4094_scalar).out, Const("1i").out) // Counter
      val x4854 = CounterChain(name = "x4854", ctr44)
      var stage: List[Stage] = Nil
    }
    val x4917_0 = UnitPipeline(name = "x4917_0", parent=x4932, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2346 = CU.temp
      val tr2344 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4854 = CounterChain.copy(x4932, "x4854")
      val x4917_unitcc = CounterChain(name = "x4917_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4854(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2344), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2346)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2346), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4930_mc.ofs)))
    }
    val x4928_0 = Pipeline(name = "x4928_0", parent=x4932, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2354 = CU.temp
      val tr2351 = CU.temp
      val tr2350 = CU.temp
      val x4738 = CounterChain.copy(x4804_0, "x4738")
      val x4854 = CounterChain.copy(x4932, "x4854")
      val ctr53 = (Const("0i").out, CU.scalarIn(stage0, x4100_scalar).out, Const("16i").out) // Counter
      val x4919 = CounterChain(name = "x4919", ctr53)
      val x4088_x4922 = SRAM(size = 2304, writeCtr = x4738(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4854(0), swapWrite = x4738(0))).wtPort(x4088_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4088_x4922))
      Stage(stage(1), operands=List(x4738(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2350)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2350), CU.ctr(stage(1), x4738(1))), op=FixAdd, results=List(x4088_x4922.writeAddr, CU.temp(stage(2), tr2351)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4854(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2354)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2354), CU.ctr(stage(1), x4919(0))), op=FixAdd, results=List(x4088_x4922.readAddr))
      Stage(stage(3), operands=List(x4088_x4922.load), op=Bypass, results=List(CU.vecOut(stage(3), x4930_mc.vdata)))
    }
    val x4951 = StreamController(name = "x4951", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr45 = (Const("0i").out, CU.scalarIn(stage0, x4095_scalar).out, Const("1i").out) // Counter
      val x4855 = CounterChain(name = "x4855", ctr45)
      var stage: List[Stage] = Nil
    }
    val x4936_0 = UnitPipeline(name = "x4936_0", parent=x4951, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2367 = CU.temp
      val tr2365 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4855 = CounterChain.copy(x4951, "x4855")
      val x4936_unitcc = CounterChain(name = "x4936_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4855(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2365)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2365), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2367)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2367), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4949_mc.ofs)))
    }
    val x4947_0 = Pipeline(name = "x4947_0", parent=x4951, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2375 = CU.temp
      val tr2372 = CU.temp
      val tr2371 = CU.temp
      val x4855 = CounterChain.copy(x4951, "x4855")
      val ctr55 = (Const("0i").out, CU.scalarIn(stage0, x4101_scalar).out, Const("16i").out) // Counter
      val x4938 = CounterChain(name = "x4938", ctr55)
      val x4739 = CounterChain.copy(x4820_0, "x4739")
      val x4089_x4941 = SRAM(size = 2304, writeCtr = x4739(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4855(0), swapWrite = x4739(0))).wtPort(x4089_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4089_x4941))
      Stage(stage(1), operands=List(x4739(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2371)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2371), CU.ctr(stage(1), x4739(1))), op=FixAdd, results=List(x4089_x4941.writeAddr, CU.temp(stage(2), tr2372)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4855(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2375)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2375), CU.ctr(stage(1), x4938(0))), op=FixAdd, results=List(x4089_x4941.readAddr))
      Stage(stage(3), operands=List(x4089_x4941.load), op=Bypass, results=List(CU.vecOut(stage(3), x4949_mc.vdata)))
    }
    val x4970 = StreamController(name = "x4970", parent=x4974, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr46 = (Const("0i").out, CU.scalarIn(stage0, x4096_scalar).out, Const("1i").out) // Counter
      val x4856 = CounterChain(name = "x4856", ctr46)
      var stage: List[Stage] = Nil
    }
    val x4955_0 = UnitPipeline(name = "x4955_0", parent=x4970, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2388 = CU.temp
      val tr2386 = CU.temp
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4856 = CounterChain.copy(x4970, "x4856")
      val x4955_unitcc = CounterChain(name = "x4955_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4072(0)), CU.ctr(stage(0), x4856(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2386)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2386), Const("76800i")), op=FixMul, results=List(CU.temp(stage(2), tr2388)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2388), CU.ctr(stage(2), x4072(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x4968_mc.ofs)))
    }
    val x4966_0 = Pipeline(name = "x4966_0", parent=x4970, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2396 = CU.temp
      val tr2393 = CU.temp
      val tr2392 = CU.temp
      val ctr57 = (Const("0i").out, CU.scalarIn(stage0, x4102_scalar).out, Const("16i").out) // Counter
      val x4957 = CounterChain(name = "x4957", ctr57)
      val x4740 = CounterChain.copy(x4836_0, "x4740")
      val x4856 = CounterChain.copy(x4970, "x4856")
      val x4090_x4960 = SRAM(size = 2304, writeCtr = x4740(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4856(0), swapWrite = x4740(0))).wtPort(x4090_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4090_x4960))
      Stage(stage(1), operands=List(x4740(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2392)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2392), CU.ctr(stage(1), x4740(1))), op=FixAdd, results=List(x4090_x4960.writeAddr, CU.temp(stage(2), tr2393)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4856(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2396)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2396), CU.ctr(stage(1), x4957(0))), op=FixAdd, results=List(x4090_x4960.readAddr))
      Stage(stage(3), operands=List(x4090_x4960.load), op=Bypass, results=List(CU.vecOut(stage(3), x4968_mc.vdata)))
    }
    val x4974_leafX = UnitPipeline(name = "x4974_leafX", parent=x4974, deps=List(x4394_0, x4293_0, x4932, x4894, x4147, x4590_0, x4820_0, x4688_0, x4539, x4245, x4441, x4875, x4637, x4290, x4788_0, x4587_0, x4195_0, x4486, x4756_0, x4685_0, x4489_0, x4391_0, x4682, x4296_0, x4198_0, x4836_0, x4388, x4804_0, x4192, x4492_0, x4584, x4951, x4913, x4343, x4970, x4772_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4072 = CounterChain.copy(x4974, "x4072")
      val x4974_unitcc = CounterChain(name = "x4974_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    
  }
}
