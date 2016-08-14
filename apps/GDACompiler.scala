import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object GDACompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x2213_vector = Vector()
    val x1851_oc = OffChip()
    val x1898_vector = Vector()
    val x1852_oc = OffChip()
    val x2181_scalar = Scalar()
    val x1938_vector = Vector()
    val x2205_vector = Vector()
    val x2299_vector = Vector()
    val x1849_oc = OffChip()
    val x1848_oc = OffChip()
    val x2332_scalar = Scalar()
    val x1917_vector = Vector()
    val x2163_vector = Vector()
    val x1850_oc = OffChip()
    val x2177_vector = Vector()
    val x2212_vector = Vector()
    val x1899_mc_mc = MemoryController(TileLoad, x1850_oc)
    val x2188_mc_mc = MemoryController(TileLoad, x1848_oc)
    val x1918_mc_mc = MemoryController(TileLoad, x1851_oc)
    val x2349_mc_mc = MemoryController(TileStore, x1852_oc)
    val x2164_mc_mc = MemoryController(TileLoad, x1849_oc)
    val x2354 = ComputeUnit(name="x2354", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2354_unitCC = CounterChain(name = "x2354_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1916 = ComputeUnit(name="x1916", parent=x2354, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1916_unitCC = CounterChain(name = "x1916_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1899 = TileTransfer(name="x1899", parent=x1916, memctrl=x1899_mc_mc, mctpe=TileLoad, deps=List(), vec=x1898_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1899_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1899_cc = CounterChain(name = "x1899_cc", x1899_ctr)
      val x1900 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1901 = CounterChain(name = "x1901", x1900)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1899_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1899_mc_mc.saddr)))
    }
    val x1935 = ComputeUnit(name="x1935", parent=x2354, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1935_unitCC = CounterChain(name = "x1935_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1918 = TileTransfer(name="x1918", parent=x1935, memctrl=x1918_mc_mc, mctpe=TileLoad, deps=List(), vec=x1917_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1918_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1918_cc = CounterChain(name = "x1918_cc", x1918_ctr)
      val x1919 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1920 = CounterChain(name = "x1920", x1919)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1918_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1918_mc_mc.saddr)))
    }
    val x2298 = ComputeUnit(name="x2298", parent=x2354, tpe = MetaPipeline, deps=List(x1916, x1935)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1939 = (Const("0i").out, Const("384i").out, Const("96i").out) // Counter
      val x1940 = CounterChain(name = "x1940", x1939)
    }
    val x2176 = ComputeUnit(name="x2176", parent=x2298, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2176_unitCC = CounterChain(name = "x2176_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2164 = TileTransfer(name="x2164", parent=x2176, memctrl=x2164_mc_mc, mctpe=TileLoad, deps=List(), vec=x2163_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1940 = CounterChain.copy(x2298, "x1940")
      val x2164_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x2164_cc = CounterChain(name = "x2164_cc", x2164_ctr)
      val x2165 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2166 = CounterChain(name = "x2166", x2165)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1940(0)), CU.ctr(stage(0), x2164_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2164_mc_mc.saddr)))
    }
    val x2202 = ComputeUnit(name="x2202", parent=x2298, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2178 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2179 = CounterChain(name = "x2179", x2178)
    }
    val x2186 = UnitComputeUnit(name ="x2186", parent=x2202, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr174 = CU.temp
      val x1940 = CounterChain.copy(x2298, "x1940")
      val x2179 = CounterChain.copy(x2202, "x2179")
      val x2186_unitCC = CounterChain(name = "x2186_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1940(0)), CU.ctr(stage(0), x2179(0))), op=FixAdd, results=List(CU.temp(stage(1), tr174)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr174), Const("96i")), op=FixMul, results=List(CU.scalarOut(stage(2), x2181_scalar)))
    }
    val x2188 = TileTransfer(name="x2188", parent=x2202, memctrl=x2188_mc_mc, mctpe=TileLoad, deps=List(x2186), vec=x2177_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2188_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x2188_cc = CounterChain(name = "x2188_cc", x2188_ctr)
      val x2189 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2190 = CounterChain(name = "x2190", x2189)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2181_scalar), CU.ctr(stage(0), x2188_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2188_mc_mc.saddr)))
    }
    val x2276 = ComputeUnit(name="x2276", parent=x2298, tpe = MetaPipeline, deps=List(x2176, x2202)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2206 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2207 = CounterChain(name = "x2207", x2206)
    }
    val x2235 = ComputeUnit(name="x2235", parent=x2276, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr197 = CU.temp
      val tr187 = CU.temp
      val tr183 = CU.temp
      val x1920 = CounterChain.copy(x1918, "x1920")
      val x2214 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2215 = CounterChain(name = "x2215", x2214)
      val x1901 = CounterChain.copy(x1899, "x1901")
      val x2179 = CounterChain.copy(x2202, "x2179")
      val x2166 = CounterChain.copy(x2164, "x2166")
      val x2190 = CounterChain.copy(x2188, "x2190")
      val x2207 = CounterChain.copy(x2276, "x2207")
      val x1896_x2225 = SRAM(size = 96, vec = x1898_vector, readAddr = x2215(0), writeAddr = x1901(0), swapCtr = x1901(0), writeCtr = x1901(0), banking = Strided(1), doubleBuffer = false)
      val x1897_x2224 = SRAM(size = 96, vec = x1917_vector, readAddr = x2215(0), writeAddr = x1920(0), swapCtr = x1920(0), writeCtr = x1920(0), banking = Strided(1), doubleBuffer = false)
      val x2161_x2222 = SRAM(size = 96, vec = x2163_vector, readAddr = x2207(0), writeAddr = x2166(0), swapCtr = x2166(0), writeCtr = x2166(0), banking = Duplicated(), doubleBuffer = true)
      val x2162_x2220 = SRAM(size = 9216, vec = x2177_vector, swapCtr = x2179(0), writeCtr = x2190(0), banking = Strided(1), doubleBuffer = true)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2162_x2220))
      Stage(stage(1), operands=List(x2179(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr183)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr183), CU.ctr(stage(1), x2190(0))), op=FixAdd, results=List(x2162_x2220.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2207(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr187)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr187), CU.ctr(stage(1), x2215(0))), op=FixAdd, results=List(x2162_x2220.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2161_x2222), CU.load(stage(2), x1897_x2224), CU.load(stage(2), x1896_x2225)), op=Mux, results=List(CU.temp(stage(3), tr197)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2162_x2220), CU.temp(stage(3), tr197)), op=FltSub, results=List(CU.vecOut(stage(4), x2212_vector)))
    }
    val x2254 = ComputeUnit(name="x2254", parent=x2276, tpe = Pipe, deps=List(x2235)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2236 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2237 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2238 = CounterChain(name = "x2238", x2236, x2237)
      val x2215 = CounterChain.copy(x2235, "x2215")
      val x2212_x2242 = SRAM(size = 96, vec = x2212_vector, readAddr = x2238(0), writeAddr = x2215(0), swapCtr = x2215(0), writeCtr = x2215(0), banking = Duplicated(), doubleBuffer = true)
      val x2212_x2244 = SRAM(size = 96, vec = x2212_vector, readAddr = x2238(1), writeAddr = x2215(0), swapCtr = x2215(0), writeCtr = x2215(0), banking = Strided(1), doubleBuffer = true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2212_x2242.load, x2212_x2244.load), op=FltMul, results=List(CU.vecOut(stage(1), x2213_vector)))
    }
    val x2274 = ComputeUnit(name="x2274", parent=x2276, tpe = Pipe, deps=List(x2254)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr218 = CU.temp
      val tr208 = CU.temp
      val tr219 = CU.temp
      val tr211 = CU.temp
      val tr221 = CU.temp
      val tr217 = CU.temp
      val x2208 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2209 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2210 = CounterChain(name = "x2210", x2208, x2209)
      val x2238 = CounterChain.copy(x2254, "x2238")
      val x2207 = CounterChain.copy(x2276, "x2207")
      val x2213_x2262 = SRAM(size = 9216, vec = x2213_vector, swapCtr = x2238(0), writeCtr = x2238(0), banking = Strided(1), doubleBuffer = true)
      val x2205_x2263 = SRAM(size = 9216, swapCtr = x2210(0), writeCtr = x2210(0), banking = Strided(1), doubleBuffer = false)
      val wr224 = CU.wtAddr(x2205_x2263)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2213_x2262))
      Stage(stage(1), operands=List(x2238(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr208)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr208), CU.ctr(stage(1), x2238(1))), op=FixAdd, results=List(x2213_x2262.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2210(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr211)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr211), CU.ctr(stage(1), x2210(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr224), x2205_x2263.readAddr, x2213_x2262.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x2207(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(3), tr217)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x2210(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr218)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr217), CU.temp(stage(4), tr218)), op=BitAnd, results=List(CU.temp(stage(5), tr219)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr219), CU.load(stage(5), x2213_x2262), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr221)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr221), CU.load(stage(6), x2205_x2263)), op=FltAdd, results=List(CU.vecOut(stage(7), x2205_vector), CU.store(stage(7), x2205_x2263)))
    }
    val x2296 = ComputeUnit(name="x2296", parent=x2298, tpe = Pipe, deps=List(x2276)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr238 = CU.temp
      val tr237 = CU.temp
      val tr226 = CU.temp
      val tr229 = CU.temp
      val tr240 = CU.temp
      val tr236 = CU.temp
      val x2210 = CounterChain.copy(x2274, "x2210")
      val x1941 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1942 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1943 = CounterChain(name = "x1943", x1941, x1942)
      val x1940 = CounterChain.copy(x2298, "x1940")
      val x2205_x2285 = SRAM(size = 9216, vec = x2205_vector, swapCtr = x2210(0), writeCtr = x2210(0), banking = Strided(1), doubleBuffer = true)
      val x1938_x2288 = SRAM(size = 9216, swapCtr = x1943(0), writeCtr = x1943(0), banking = Strided(1), doubleBuffer = false)
      val wr243 = CU.wtAddr(x1938_x2288)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2205_x2285))
      Stage(stage(1), operands=List(x2210(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr226)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr226), CU.ctr(stage(1), x2210(1))), op=FixAdd, results=List(x2205_x2285.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1943(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr229)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr229), CU.ctr(stage(1), x1943(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr243), x1938_x2288.readAddr, x2205_x2285.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1940(0)), Const("384i")), op=FixLt, results=List(CU.temp(stage(3), tr236)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x1943(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr237)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr236), CU.temp(stage(4), tr237)), op=BitAnd, results=List(CU.temp(stage(5), tr238)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr238), CU.load(stage(5), x2205_x2285), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr240)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr240), CU.load(stage(6), x1938_x2288)), op=FltAdd, results=List(CU.vecOut(stage(7), x1938_vector), CU.store(stage(7), x1938_x2288)))
    }
    val x2352 = ComputeUnit(name="x2352", parent=x2354, tpe = MetaPipeline, deps=List(x2298)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2300 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2301 = CounterChain(name = "x2301", x2300)
    }
    val x2336 = UnitComputeUnit(name ="x2336", parent=x2352, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2301 = CounterChain.copy(x2352, "x2301")
      val x2336_unitCC = CounterChain(name = "x2336_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2301(0)), Const("96i")), op=FixMul, results=List(CU.scalarOut(stage(1), x2332_scalar)))
    }
    val x2348 = ComputeUnit(name="x2348", parent=x2352, tpe = Pipe, deps=List(x2336)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr248 = CU.temp
      val x1943 = CounterChain.copy(x2296, "x1943")
      val x2330 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2331 = CounterChain(name = "x2331", x2330)
      val x1938_x2341 = SRAM(size = 9216, vec = x1938_vector, swapCtr = x1943(0), writeCtr = x1943(0), banking = Strided(1), doubleBuffer = false)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1938_x2341))
      Stage(stage(1), operands=List(x1943(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr248)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr248), CU.ctr(stage(1), x1943(1))), op=FixAdd, results=List(x1938_x2341.writeAddr))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2332_scalar), CU.ctr(stage(0), x2331(0))), op=FixAdd, results=List(x1938_x2341.readAddr))
      Stage(stage(2), operands=List(CU.load(stage(1), x1938_x2341)), op=Bypass, results=List(CU.vecOut(stage(2), x2299_vector)))
    }
    val x2349 = TileTransfer(name="x2349", parent=x2352, memctrl=x2349_mc_mc, mctpe=TileStore, deps=List(x2348), vec=x2299_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2349_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x2349_cc = CounterChain(name = "x2349_cc", x2349_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2332_scalar), CU.ctr(stage(0), x2349_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2349_mc_mc.saddr)))
    }
    
  }
}
