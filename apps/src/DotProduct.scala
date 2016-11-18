import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2732_oc = OffChip("x2732")
    val x2731_argout = ArgOut("x2731")
    val bus_2576_scalar = Scalar("bus_2576")
    val x3671_scalar = Scalar("x3671")
    val x3667_scalar = Scalar("x3667")
    val x3664_scalar = Scalar("x3664")
    val x3663_scalar = Scalar("x3663")
    val x3666_scalar = Scalar("x3666")
    val x3668_scalar = Scalar("x3668")
    val x2733_oc = OffChip("x2733")
    val x3665_scalar = Scalar("x3665")
    val x3670_scalar = Scalar("x3670")
    val bus_2573_scalar = Scalar("bus_2573")
    val x3669_scalar = Scalar("x3669")
    val x3316_mc = MemoryController(TileLoad, x2732_oc)
    val x3500_mc = MemoryController(TileLoad, x2732_oc)
    val x2901_mc = MemoryController(TileLoad, x2733_oc)
    val x3269_mc = MemoryController(TileLoad, x2733_oc)
    val x3177_mc = MemoryController(TileLoad, x2733_oc)
    val x3224_mc = MemoryController(TileLoad, x2732_oc)
    val x2993_mc = MemoryController(TileLoad, x2733_oc)
    val x3040_mc = MemoryController(TileLoad, x2732_oc)
    val x3637_mc = MemoryController(TileLoad, x2733_oc)
    val x3408_mc = MemoryController(TileLoad, x2732_oc)
    val x2856_mc = MemoryController(TileLoad, x2732_oc)
    val x2948_mc = MemoryController(TileLoad, x2732_oc)
    val x3545_mc = MemoryController(TileLoad, x2733_oc)
    val x3453_mc = MemoryController(TileLoad, x2733_oc)
    val x3361_mc = MemoryController(TileLoad, x2733_oc)
    val x3132_mc = MemoryController(TileLoad, x2732_oc)
    val x3592_mc = MemoryController(TileLoad, x2732_oc)
    val x3085_mc = MemoryController(TileLoad, x2733_oc)
    val x3847 = Sequential(name = "x3847", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3847_unitcc = CounterChain(name = "x3847_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3841 = MetaPipeline(name = "x3841", parent=x3847, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("768000000i").out, Const("2000i").out) // Counter
      val x2812 = CounterChain(name = "x2812", ctr1)
      var stage: List[Stage] = Nil
    }
    val x2877 = StreamController(name = "x2877", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2877_unitcc = CounterChain(name = "x2877_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2852_0 = UnitPipeline(name = "x2852_0", parent=x2877, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2041 = CU.temp
      val tr2040 = CU.temp
      val tr2038 = CU.temp
      val tr2037 = CU.temp
      val tr2035 = CU.temp
      val tr2030 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x2852_unitcc = CounterChain(name = "x2852_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2030)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2030)), op=FixSub, results=List(CU.scalarOut(stage(2), x2856_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2030), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2035)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2035), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2037)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2035), CU.temp(stage(4), tr2037)), op=FixSub, results=List(CU.temp(stage(5), tr2038)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2037), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2040)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2040), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2041)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2038), CU.temp(stage(7), tr2041)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2856_mc.len)))
    }
    val x2922 = StreamController(name = "x2922", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2922_unitcc = CounterChain(name = "x2922_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2897_0 = UnitPipeline(name = "x2897_0", parent=x2922, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2064 = CU.temp
      val tr2063 = CU.temp
      val tr2061 = CU.temp
      val tr2060 = CU.temp
      val tr2058 = CU.temp
      val tr2053 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x2897_unitcc = CounterChain(name = "x2897_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2053)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2053)), op=FixSub, results=List(CU.scalarOut(stage(2), x2901_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2053), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2058)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2058), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2060)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2058), CU.temp(stage(4), tr2060)), op=FixSub, results=List(CU.temp(stage(5), tr2061)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2060), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2063)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2063), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2064)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2061), CU.temp(stage(7), tr2064)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2901_mc.len)))
    }
    val x2969 = StreamController(name = "x2969", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2969_unitcc = CounterChain(name = "x2969_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2944_0 = UnitPipeline(name = "x2944_0", parent=x2969, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2087 = CU.temp
      val tr2086 = CU.temp
      val tr2084 = CU.temp
      val tr2083 = CU.temp
      val tr2081 = CU.temp
      val tr2076 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x2944_unitcc = CounterChain(name = "x2944_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2076)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2076)), op=FixSub, results=List(CU.scalarOut(stage(2), x2948_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2076), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2081)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2081), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2083)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2081), CU.temp(stage(4), tr2083)), op=FixSub, results=List(CU.temp(stage(5), tr2084)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2083), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2086)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2086), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2087)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2084), CU.temp(stage(7), tr2087)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2948_mc.len)))
    }
    val x3014 = StreamController(name = "x3014", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3014_unitcc = CounterChain(name = "x3014_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2989_0 = UnitPipeline(name = "x2989_0", parent=x3014, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2110 = CU.temp
      val tr2109 = CU.temp
      val tr2107 = CU.temp
      val tr2106 = CU.temp
      val tr2104 = CU.temp
      val tr2099 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x2989_unitcc = CounterChain(name = "x2989_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2099)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2099)), op=FixSub, results=List(CU.scalarOut(stage(2), x2993_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2099), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2104)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2104), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2106)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2104), CU.temp(stage(4), tr2106)), op=FixSub, results=List(CU.temp(stage(5), tr2107)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2106), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2109)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2109), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2110)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2107), CU.temp(stage(7), tr2110)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2993_mc.len)))
    }
    val x3061 = StreamController(name = "x3061", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3061_unitcc = CounterChain(name = "x3061_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3036_0 = UnitPipeline(name = "x3036_0", parent=x3061, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2133 = CU.temp
      val tr2132 = CU.temp
      val tr2130 = CU.temp
      val tr2129 = CU.temp
      val tr2127 = CU.temp
      val tr2122 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3036_unitcc = CounterChain(name = "x3036_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2122)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2122)), op=FixSub, results=List(CU.scalarOut(stage(2), x3040_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2122), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2127)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2127), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2129)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2127), CU.temp(stage(4), tr2129)), op=FixSub, results=List(CU.temp(stage(5), tr2130)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2129), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2132)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2132), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2133)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2130), CU.temp(stage(7), tr2133)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3040_mc.len)))
    }
    val x3106 = StreamController(name = "x3106", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3106_unitcc = CounterChain(name = "x3106_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3081_0 = UnitPipeline(name = "x3081_0", parent=x3106, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2156 = CU.temp
      val tr2155 = CU.temp
      val tr2153 = CU.temp
      val tr2152 = CU.temp
      val tr2150 = CU.temp
      val tr2145 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3081_unitcc = CounterChain(name = "x3081_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2145)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2145)), op=FixSub, results=List(CU.scalarOut(stage(2), x3085_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2145), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2150)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2150), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2152)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2150), CU.temp(stage(4), tr2152)), op=FixSub, results=List(CU.temp(stage(5), tr2153)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2152), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2155)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2155), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2156)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2153), CU.temp(stage(7), tr2156)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3085_mc.len)))
    }
    val x3153 = StreamController(name = "x3153", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3153_unitcc = CounterChain(name = "x3153_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3128_0 = UnitPipeline(name = "x3128_0", parent=x3153, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2179 = CU.temp
      val tr2178 = CU.temp
      val tr2176 = CU.temp
      val tr2175 = CU.temp
      val tr2173 = CU.temp
      val tr2168 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3128_unitcc = CounterChain(name = "x3128_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2168)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2168)), op=FixSub, results=List(CU.scalarOut(stage(2), x3132_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2168), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2173)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2173), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2175)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2173), CU.temp(stage(4), tr2175)), op=FixSub, results=List(CU.temp(stage(5), tr2176)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2175), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2178)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2178), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2179)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2176), CU.temp(stage(7), tr2179)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3132_mc.len)))
    }
    val x3198 = StreamController(name = "x3198", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3198_unitcc = CounterChain(name = "x3198_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3173_0 = UnitPipeline(name = "x3173_0", parent=x3198, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2202 = CU.temp
      val tr2201 = CU.temp
      val tr2199 = CU.temp
      val tr2198 = CU.temp
      val tr2196 = CU.temp
      val tr2191 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3173_unitcc = CounterChain(name = "x3173_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2191)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2191)), op=FixSub, results=List(CU.scalarOut(stage(2), x3177_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2191), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2196)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2196), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2198)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2196), CU.temp(stage(4), tr2198)), op=FixSub, results=List(CU.temp(stage(5), tr2199)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2198), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2201)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2201), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2202)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2199), CU.temp(stage(7), tr2202)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3177_mc.len)))
    }
    val x3245 = StreamController(name = "x3245", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3245_unitcc = CounterChain(name = "x3245_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3220_0 = UnitPipeline(name = "x3220_0", parent=x3245, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2225 = CU.temp
      val tr2224 = CU.temp
      val tr2222 = CU.temp
      val tr2221 = CU.temp
      val tr2219 = CU.temp
      val tr2214 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3220_unitcc = CounterChain(name = "x3220_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2214)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2214)), op=FixSub, results=List(CU.scalarOut(stage(2), x3224_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2214), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2219)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2219), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2221)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2219), CU.temp(stage(4), tr2221)), op=FixSub, results=List(CU.temp(stage(5), tr2222)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2221), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2224)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2224), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2225)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2222), CU.temp(stage(7), tr2225)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3224_mc.len)))
    }
    val x3290 = StreamController(name = "x3290", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3290_unitcc = CounterChain(name = "x3290_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3265_0 = UnitPipeline(name = "x3265_0", parent=x3290, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2248 = CU.temp
      val tr2247 = CU.temp
      val tr2245 = CU.temp
      val tr2244 = CU.temp
      val tr2242 = CU.temp
      val tr2237 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3265_unitcc = CounterChain(name = "x3265_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2237)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2237)), op=FixSub, results=List(CU.scalarOut(stage(2), x3269_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2237), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2242)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2242), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2244)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2242), CU.temp(stage(4), tr2244)), op=FixSub, results=List(CU.temp(stage(5), tr2245)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2244), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2247)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2247), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2248)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2245), CU.temp(stage(7), tr2248)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3269_mc.len)))
    }
    val x3337 = StreamController(name = "x3337", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3337_unitcc = CounterChain(name = "x3337_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3312_0 = UnitPipeline(name = "x3312_0", parent=x3337, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2271 = CU.temp
      val tr2270 = CU.temp
      val tr2268 = CU.temp
      val tr2267 = CU.temp
      val tr2265 = CU.temp
      val tr2260 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3312_unitcc = CounterChain(name = "x3312_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2260)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2260)), op=FixSub, results=List(CU.scalarOut(stage(2), x3316_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2260), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2265)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2265), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2267)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2265), CU.temp(stage(4), tr2267)), op=FixSub, results=List(CU.temp(stage(5), tr2268)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2267), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2270)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2270), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2271)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2268), CU.temp(stage(7), tr2271)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3316_mc.len)))
    }
    val x3382 = StreamController(name = "x3382", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3382_unitcc = CounterChain(name = "x3382_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3357_0 = UnitPipeline(name = "x3357_0", parent=x3382, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2294 = CU.temp
      val tr2293 = CU.temp
      val tr2291 = CU.temp
      val tr2290 = CU.temp
      val tr2288 = CU.temp
      val tr2283 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3357_unitcc = CounterChain(name = "x3357_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2283)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2283)), op=FixSub, results=List(CU.scalarOut(stage(2), x3361_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2283), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2288)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2288), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2290)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2288), CU.temp(stage(4), tr2290)), op=FixSub, results=List(CU.temp(stage(5), tr2291)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2290), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2293)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2293), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2294)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2291), CU.temp(stage(7), tr2294)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3361_mc.len)))
    }
    val x3429 = StreamController(name = "x3429", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3429_unitcc = CounterChain(name = "x3429_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3404_0 = UnitPipeline(name = "x3404_0", parent=x3429, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2317 = CU.temp
      val tr2316 = CU.temp
      val tr2314 = CU.temp
      val tr2313 = CU.temp
      val tr2311 = CU.temp
      val tr2306 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3404_unitcc = CounterChain(name = "x3404_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2306)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2306)), op=FixSub, results=List(CU.scalarOut(stage(2), x3408_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2306), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2311)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2311), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2313)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2311), CU.temp(stage(4), tr2313)), op=FixSub, results=List(CU.temp(stage(5), tr2314)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2313), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2316)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2316), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2317)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2314), CU.temp(stage(7), tr2317)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3408_mc.len)))
    }
    val x3474 = StreamController(name = "x3474", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3474_unitcc = CounterChain(name = "x3474_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3449_0 = UnitPipeline(name = "x3449_0", parent=x3474, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2340 = CU.temp
      val tr2339 = CU.temp
      val tr2337 = CU.temp
      val tr2336 = CU.temp
      val tr2334 = CU.temp
      val tr2329 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3449_unitcc = CounterChain(name = "x3449_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2329)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2329)), op=FixSub, results=List(CU.scalarOut(stage(2), x3453_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2329), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2334)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2334), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2336)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2334), CU.temp(stage(4), tr2336)), op=FixSub, results=List(CU.temp(stage(5), tr2337)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2336), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2339)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2339), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2340)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2337), CU.temp(stage(7), tr2340)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3453_mc.len)))
    }
    val x3521 = StreamController(name = "x3521", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3521_unitcc = CounterChain(name = "x3521_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3496_0 = UnitPipeline(name = "x3496_0", parent=x3521, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2363 = CU.temp
      val tr2362 = CU.temp
      val tr2360 = CU.temp
      val tr2359 = CU.temp
      val tr2357 = CU.temp
      val tr2352 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3496_unitcc = CounterChain(name = "x3496_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2352)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2352)), op=FixSub, results=List(CU.scalarOut(stage(2), x3500_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2352), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2357)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2357), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2359)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2357), CU.temp(stage(4), tr2359)), op=FixSub, results=List(CU.temp(stage(5), tr2360)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2359), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2362)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2362), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2363)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2360), CU.temp(stage(7), tr2363)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3500_mc.len)))
    }
    val x3566 = StreamController(name = "x3566", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3566_unitcc = CounterChain(name = "x3566_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3541_0 = UnitPipeline(name = "x3541_0", parent=x3566, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2386 = CU.temp
      val tr2385 = CU.temp
      val tr2383 = CU.temp
      val tr2382 = CU.temp
      val tr2380 = CU.temp
      val tr2375 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3541_unitcc = CounterChain(name = "x3541_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2375)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2375)), op=FixSub, results=List(CU.scalarOut(stage(2), x3545_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2375), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2380)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2380), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2382)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2380), CU.temp(stage(4), tr2382)), op=FixSub, results=List(CU.temp(stage(5), tr2383)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2382), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2385)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2385), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2386)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2383), CU.temp(stage(7), tr2386)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3545_mc.len)))
    }
    val x3613 = StreamController(name = "x3613", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3613_unitcc = CounterChain(name = "x3613_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3588_0 = UnitPipeline(name = "x3588_0", parent=x3613, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2409 = CU.temp
      val tr2408 = CU.temp
      val tr2406 = CU.temp
      val tr2405 = CU.temp
      val tr2403 = CU.temp
      val tr2398 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3588_unitcc = CounterChain(name = "x3588_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2398)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2398)), op=FixSub, results=List(CU.scalarOut(stage(2), x3592_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2398), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2403)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2403), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2405)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2403), CU.temp(stage(4), tr2405)), op=FixSub, results=List(CU.temp(stage(5), tr2406)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2405), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2408)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2408), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2409)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2406), CU.temp(stage(7), tr2409)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3592_mc.len)))
    }
    val x3658 = StreamController(name = "x3658", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3658_unitcc = CounterChain(name = "x3658_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3633_0 = UnitPipeline(name = "x3633_0", parent=x3658, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2432 = CU.temp
      val tr2431 = CU.temp
      val tr2429 = CU.temp
      val tr2428 = CU.temp
      val tr2426 = CU.temp
      val tr2421 = CU.temp
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3633_unitcc = CounterChain(name = "x3633_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2812(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr2421)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2812(0)), CU.temp(stage(1), tr2421)), op=FixSub, results=List(CU.scalarOut(stage(2), x3637_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2421), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr2426)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2426), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2428)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2426), CU.temp(stage(4), tr2428)), op=FixSub, results=List(CU.temp(stage(5), tr2429)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2428), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr2431)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2431), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr2432)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2429), CU.temp(stage(7), tr2432)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3637_mc.len)))
    }
    val x3703_0 = Pipeline(name = "x3703_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3681 = CounterChain(name = "x3681", ctr5)
      val x2815_x3692 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3681(0))).wtPort(x2856_mc.vdata).rdAddr(x3681(0))
      val x2824_x3695 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3681(0))).wtPort(x2901_mc.vdata).rdAddr(x3681(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2815_x3692.load, x2824_x3695.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2454) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2454), op=Bypass, results=List(CU.scalarOut(stage(2), x3663_scalar)))
    }
    val x3717_0 = Pipeline(name = "x3717_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3682 = CounterChain(name = "x3682", ctr10)
      val x2816_x3706 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3682(0))).wtPort(x2948_mc.vdata).rdAddr(x3682(0))
      val x2825_x3709 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3682(0))).wtPort(x2993_mc.vdata).rdAddr(x3682(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2816_x3706.load, x2825_x3709.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2467) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2467), op=Bypass, results=List(CU.scalarOut(stage(2), x3664_scalar)))
    }
    val x3731_0 = Pipeline(name = "x3731_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3683 = CounterChain(name = "x3683", ctr15)
      val x2817_x3720 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3683(0))).wtPort(x3040_mc.vdata).rdAddr(x3683(0))
      val x2826_x3723 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3683(0))).wtPort(x3085_mc.vdata).rdAddr(x3683(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2817_x3720.load, x2826_x3723.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2480) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2480), op=Bypass, results=List(CU.scalarOut(stage(2), x3665_scalar)))
    }
    val x3745_0 = Pipeline(name = "x3745_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr20 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3684 = CounterChain(name = "x3684", ctr20)
      val x2818_x3734 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3684(0))).wtPort(x3132_mc.vdata).rdAddr(x3684(0))
      val x2827_x3737 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3684(0))).wtPort(x3177_mc.vdata).rdAddr(x3684(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2818_x3734.load, x2827_x3737.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2493) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2493), op=Bypass, results=List(CU.scalarOut(stage(2), x3666_scalar)))
    }
    val x3759_0 = Pipeline(name = "x3759_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3685 = CounterChain(name = "x3685", ctr25)
      val x2819_x3748 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3685(0))).wtPort(x3224_mc.vdata).rdAddr(x3685(0))
      val x2828_x3751 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3685(0))).wtPort(x3269_mc.vdata).rdAddr(x3685(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2819_x3748.load, x2828_x3751.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2506) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2506), op=Bypass, results=List(CU.scalarOut(stage(2), x3667_scalar)))
    }
    val x3773_0 = Pipeline(name = "x3773_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr30 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3686 = CounterChain(name = "x3686", ctr30)
      val x2820_x3762 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3686(0))).wtPort(x3316_mc.vdata).rdAddr(x3686(0))
      val x2829_x3765 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3686(0))).wtPort(x3361_mc.vdata).rdAddr(x3686(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2820_x3762.load, x2829_x3765.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2519) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2519), op=Bypass, results=List(CU.scalarOut(stage(2), x3668_scalar)))
    }
    val x3787_0 = Pipeline(name = "x3787_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr35 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3687 = CounterChain(name = "x3687", ctr35)
      val x2821_x3776 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3687(0))).wtPort(x3408_mc.vdata).rdAddr(x3687(0))
      val x2830_x3779 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3687(0))).wtPort(x3453_mc.vdata).rdAddr(x3687(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2821_x3776.load, x2830_x3779.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2532) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2532), op=Bypass, results=List(CU.scalarOut(stage(2), x3669_scalar)))
    }
    val x3801_0 = Pipeline(name = "x3801_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr40 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3688 = CounterChain(name = "x3688", ctr40)
      val x2822_x3790 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3688(0))).wtPort(x3500_mc.vdata).rdAddr(x3688(0))
      val x2831_x3793 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3688(0))).wtPort(x3545_mc.vdata).rdAddr(x3688(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2822_x3790.load, x2831_x3793.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2545) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2545), op=Bypass, results=List(CU.scalarOut(stage(2), x3670_scalar)))
    }
    val x3815_0 = Pipeline(name = "x3815_0", parent=x3841, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr45 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x3689 = CounterChain(name = "x3689", ctr45)
      val x2823_x3804 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3689(0))).wtPort(x3592_mc.vdata).rdAddr(x3689(0))
      val x2832_x3807 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3689(0))).wtPort(x3637_mc.vdata).rdAddr(x3689(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x2823_x3804.load, x2832_x3807.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr2558) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr2558), op=Bypass, results=List(CU.scalarOut(stage(2), x3671_scalar)))
    }
    val x3839 = StreamController(name = "x3839", parent=x3841, deps=List(x3815_0, x3801_0, x3703_0, x3787_0, x3717_0, x3731_0, x3759_0, x3745_0, x3773_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3839_unitcc = CounterChain(name = "x3839_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3839_0 = StreamPipeline(name = "x3839_0", parent=x3839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2572 = CU.temp
      val tr2571 = CU.temp
      val x3839_unitcc = CounterChain.copy(x3839, "x3839_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3663_scalar), CU.scalarIn(stage(0), x3664_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr2571)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x3665_scalar), CU.scalarIn(stage(1), x3666_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr2572)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2571), CU.temp(stage(2), tr2572)), op=FixAdd, results=List(CU.scalarOut(stage(3), bus_2573_scalar)))
    }
    val x3839_1 = StreamPipeline(name = "x3839_1", parent=x3839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2575 = CU.temp
      val tr2574 = CU.temp
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3667_scalar), CU.scalarIn(stage(0), x3668_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr2574)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x3669_scalar), CU.scalarIn(stage(1), x3670_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr2575)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2574), CU.temp(stage(2), tr2575)), op=FixAdd, results=List(CU.scalarOut(stage(3), bus_2576_scalar)))
    }
    val x3839_2 = StreamPipeline(name = "x3839_2", parent=x3839, deps=List(x3839_0, x3839_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2578 = CU.temp
      val tr2577 = CU.temp
      val ar2570 = CU.accum(init = Const("0i"))
      val x3839_unitcc = CounterChain.copy(x3839, "x3839_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_2573_scalar), CU.scalarIn(stage(0), bus_2576_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr2577)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2577), CU.scalarIn(stage(1), x3671_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr2578)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2578), CU.accum(stage(3), ar2570)), op=FixAdd, results=List(CU.scalarOut(stage(3), x2731_argout), CU.accum(stage(3), ar2570)))
    }
    val x3841_leaf = UnitPipeline(name = "x3841_leaf", parent=x3841, deps=List(x3337, x3658, x3429, x3245, x2922, x3566, x2877, x3521, x3290, x2969, x3014, x3061, x3153, x3613, x3474, x3198, x3839, x3106, x3382)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2812 = CounterChain.copy(x3841, "x2812")
      val x3841_unitcc = CounterChain(name = "x3841_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    
  }
}
