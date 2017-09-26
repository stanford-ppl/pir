import pir._
import pir.node._
import arch._
import pirc.enums._

object TPCHQ6 extends PIRApp {
  def main(top:Top) = {
    val discts_oc = OffChip("discts")
    val x2085_x2106_wa_s = Scalar("x2085_x2106_wa")
    val x2086_x2175_ra_s = Scalar("x2086_x2175_ra")
    val x2169_x2194_s = Scalar("x2169_x2194")
    val x2087_x2087_dsp0_bank0_data_s = Scalar("x2087_x2087_dsp0_bank0_data")
    val bus_161_v = Vector("bus_161")
    val x2089_b2231_x2098_b2233_s = Scalar("x2089_b2231_x2098_b2233")
    val x2087_x2174_ra_s = Scalar("x2087_x2174_ra")
    val x2088_x2166_wa_s = Scalar("x2088_x2166_wa")
    val x2086_x2126_wa_s = Scalar("x2086_x2126_wa")
    val bus_168_v = Vector("bus_168")
    val discts_da = DRAMAddress("discts", "discts")
    val x2149_b2245_x2158_b2247_s = Scalar("x2149_b2245_x2158_b2247")
    val x2085_x2173_ra_s = Scalar("x2085_x2173_ra")
    val x2150_x2160_data_s = Scalar("x2150_x2160_data")
    val prices_da = DRAMAddress("prices", "prices")
    val x2076_x2204_argout = ArgOut("x2076_x2204")
    val x2090_x2100_data_s = Scalar("x2090_x2100_data")
    val x2087_x2146_wa_s = Scalar("x2087_x2146_wa")
    val x2088_x2088_dsp0_bank0_data_s = Scalar("x2088_x2088_dsp0_bank0_data")
    val x2109_b2235_x2118_b2237_s = Scalar("x2109_b2235_x2118_b2237")
    val x2110_x2120_data_s = Scalar("x2110_x2120_data")
    val dates_da = DRAMAddress("dates", "dates")
    val x2149_b2246_x2158_b2248_s = Scalar("x2149_b2246_x2158_b2248")
    val x2088_x2176_ra_s = Scalar("x2088_x2176_ra")
    val x2086_x2086_dsp0_bank0_data_s = Scalar("x2086_x2086_dsp0_bank0_data")
    val dates_oc = OffChip("dates")
    val quants_da = DRAMAddress("quants", "quants")
    val x2109_b2236_x2118_b2238_s = Scalar("x2109_b2236_x2118_b2238")
    val x2085_x2085_dsp0_bank0_data_s = Scalar("x2085_x2085_dsp0_bank0_data")
    val x2129_b2241_x2138_b2243_s = Scalar("x2129_b2241_x2138_b2243")
    val x2089_b2230_x2098_b2232_s = Scalar("x2089_b2230_x2098_b2232")
    val dataSize_argin = ArgIn("dataSize")
    val x2130_x2140_data_s = Scalar("x2130_x2140_data")
    val prices_oc = OffChip("prices")
    val x2129_b2240_x2138_b2242_s = Scalar("x2129_b2240_x2138_b2242")
    val quants_oc = OffChip("quants")
    val bus_160_v = Vector("bus_160")
    val x2206 = Sequential(name="x2206",parent=top) { implicit CU => 
      val x2206_unit = CounterChain(name = "x2206_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2202 = MetaPipeline(name="x2202",parent=x2206) { implicit CU => 
      val x2066 = ScalarBuffer(name="x2066").wtPort(dataSize_argin)
      val ctr1 = Counter(min=Const(0), max=x2066.readPort, step=Const(384), par=1) // Counter
      val x2084 = CounterChain(name = "x2084", ctr1).iter(1)
    }
    val x2085_dsp0_bank0 = MemoryPipeline(name="x2085_dsp0_bank0",parent="x2202") { implicit CU => 
      val b2250 = ScalarFIFO(size=1,name="b2250").wtPort(x2085_x2173_ra_s)
      val x2106 = ScalarFIFO(size=1,name="x2106").wtPort(x2090_x2100_data_s)
      val b2234 = ScalarFIFO(size=1,name="b2234").wtPort(x2085_x2106_wa_s)
      val x2085 = SRAM(size=24,name="x2085",banking = Strided(1,16)).wtPort(x2106.readPort).rdPort(x2085_x2085_dsp0_bank0_data_s).rdAddr(b2250.readPort).wtAddr(b2234.readPort)
    }
    val x2086_dsp0_bank0 = MemoryPipeline(name="x2086_dsp0_bank0",parent="x2202") { implicit CU => 
      val b2239 = ScalarFIFO(size=1,name="b2239").wtPort(x2086_x2126_wa_s)
      val b2252 = ScalarFIFO(size=1,name="b2252").wtPort(x2086_x2175_ra_s)
      val x2126 = ScalarFIFO(size=1,name="x2126").wtPort(x2110_x2120_data_s)
      val x2086 = SRAM(size=24,name="x2086",banking = Strided(1,16)).wtPort(x2126.readPort).rdPort(x2086_x2086_dsp0_bank0_data_s).rdAddr(b2252.readPort).wtAddr(b2239.readPort)
    }
    val x2087_dsp0_bank0 = MemoryPipeline(name="x2087_dsp0_bank0",parent="x2202") { implicit CU => 
      val b2251 = ScalarFIFO(size=1,name="b2251").wtPort(x2087_x2174_ra_s)
      val x2146 = ScalarFIFO(size=1,name="x2146").wtPort(x2130_x2140_data_s)
      val b2244 = ScalarFIFO(size=1,name="b2244").wtPort(x2087_x2146_wa_s)
      val x2087 = SRAM(size=24,name="x2087",banking = Strided(1,16)).wtPort(x2146.readPort).rdPort(x2087_x2087_dsp0_bank0_data_s).rdAddr(b2251.readPort).wtAddr(b2244.readPort)
    }
    val x2088_dsp0_bank0 = MemoryPipeline(name="x2088_dsp0_bank0",parent="x2202") { implicit CU => 
      val b2253 = ScalarFIFO(size=1,name="b2253").wtPort(x2088_x2176_ra_s)
      val x2166 = ScalarFIFO(size=1,name="x2166").wtPort(x2150_x2160_data_s)
      val b2249 = ScalarFIFO(size=1,name="b2249").wtPort(x2088_x2166_wa_s)
      val x2088 = SRAM(size=24,name="x2088",banking = Strided(1,16)).wtPort(x2166.readPort).rdPort(x2088_x2088_dsp0_bank0_data_s).rdAddr(b2253.readPort).wtAddr(b2249.readPort)
    }
    val x2108 = StreamController(name="x2108",parent=x2202) { implicit CU => 
      val x2108_unit = CounterChain(name = "x2108_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2099_0 = Pipeline(name="x2099_0",parent=x2108) { implicit CU => 
      val x2092 = CU.temp(None)
      val x2094 = ScalarBuffer(name="x2094").wtPort(dates_da)
      val x2084 = CounterChain.copy("x2202", "x2084").iterIdx(0, 0)
      val x2099_unit = CounterChain(name = "x2099_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2084(0)), Const(2)), op=FixSla, results=List(x2092))
      Stage(operands=List(x2092, CU.load(x2094)), op=FixAdd, results=List(CU.scalarOut(x2089_b2230_x2098_b2232_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2089_b2231_x2098_b2233_s)))
    }
    val x2100 = MemoryController(name="x2100",parent=x2108,offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2089_b2230 = ScalarFIFO(size=1,name="offset").wtPort(x2089_b2230_x2098_b2232_s)
      val x2089_b2231 = ScalarFIFO(size=1,name="size").wtPort(x2089_b2231_x2098_b2233_s)
      CU.newSout("data", x2090_x2100_data_s)
    }
    val x2107 = Pipeline(name="x2107",parent=x2108) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2102 = CounterChain(name = "x2102", ctr2).iter(24)
    }
    val x2106_0 = Pipeline(name="x2106_0",parent=x2108) { implicit CU => 
      val x2102 = CounterChain.copy("x2107", "x2102").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2102(0))), op=Bypass, results=List(CU.scalarOut(x2085_x2106_wa_s)))
    }
    val x2128 = StreamController(name="x2128",parent=x2202) { implicit CU => 
      val x2128_unit = CounterChain(name = "x2128_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2119_0 = Pipeline(name="x2119_0",parent=x2128) { implicit CU => 
      val x2112 = CU.temp(None)
      val x2114 = ScalarBuffer(name="x2114").wtPort(quants_da)
      val x2084 = CounterChain.copy("x2202", "x2084").iterIdx(0, 0)
      val x2119_unit = CounterChain(name = "x2119_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2084(0)), Const(2)), op=FixSla, results=List(x2112))
      Stage(operands=List(x2112, CU.load(x2114)), op=FixAdd, results=List(CU.scalarOut(x2109_b2235_x2118_b2237_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2109_b2236_x2118_b2238_s)))
    }
    val x2120 = MemoryController(name="x2120",parent=x2128,offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2109_b2236 = ScalarFIFO(size=1,name="size").wtPort(x2109_b2236_x2118_b2238_s)
      val x2109_b2235 = ScalarFIFO(size=1,name="offset").wtPort(x2109_b2235_x2118_b2237_s)
      CU.newSout("data", x2110_x2120_data_s)
    }
    val x2127 = Pipeline(name="x2127",parent=x2128) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2122 = CounterChain(name = "x2122", ctr3).iter(24)
    }
    val x2126_0 = Pipeline(name="x2126_0",parent=x2128) { implicit CU => 
      val x2122 = CounterChain.copy("x2127", "x2122").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2122(0))), op=Bypass, results=List(CU.scalarOut(x2086_x2126_wa_s)))
    }
    val x2148 = StreamController(name="x2148",parent=x2202) { implicit CU => 
      val x2148_unit = CounterChain(name = "x2148_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2139_0 = Pipeline(name="x2139_0",parent=x2148) { implicit CU => 
      val x2132 = CU.temp(None)
      val x2134 = ScalarBuffer(name="x2134").wtPort(discts_da)
      val x2084 = CounterChain.copy("x2202", "x2084").iterIdx(0, 0)
      val x2139_unit = CounterChain(name = "x2139_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2084(0)), Const(2)), op=FixSla, results=List(x2132))
      Stage(operands=List(x2132, CU.load(x2134)), op=FixAdd, results=List(CU.scalarOut(x2129_b2240_x2138_b2242_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2129_b2241_x2138_b2243_s)))
    }
    val x2140 = MemoryController(name="x2140",parent=x2148,offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2129_b2241 = ScalarFIFO(size=1,name="size").wtPort(x2129_b2241_x2138_b2243_s)
      val x2129_b2240 = ScalarFIFO(size=1,name="offset").wtPort(x2129_b2240_x2138_b2242_s)
      CU.newSout("data", x2130_x2140_data_s)
    }
    val x2147 = Pipeline(name="x2147",parent=x2148) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2142 = CounterChain(name = "x2142", ctr4).iter(24)
    }
    val x2146_0 = Pipeline(name="x2146_0",parent=x2148) { implicit CU => 
      val x2142 = CounterChain.copy("x2147", "x2142").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2142(0))), op=Bypass, results=List(CU.scalarOut(x2087_x2146_wa_s)))
    }
    val x2168 = StreamController(name="x2168",parent=x2202) { implicit CU => 
      val x2168_unit = CounterChain(name = "x2168_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2159_0 = Pipeline(name="x2159_0",parent=x2168) { implicit CU => 
      val x2152 = CU.temp(None)
      val x2154 = ScalarBuffer(name="x2154").wtPort(prices_da)
      val x2084 = CounterChain.copy("x2202", "x2084").iterIdx(0, 0)
      val x2159_unit = CounterChain(name = "x2159_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2084(0)), Const(2)), op=FixSla, results=List(x2152))
      Stage(operands=List(x2152, CU.load(x2154)), op=FixAdd, results=List(CU.scalarOut(x2149_b2245_x2158_b2247_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2149_b2246_x2158_b2248_s)))
    }
    val x2160 = MemoryController(name="x2160",parent=x2168,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2149_b2245 = ScalarFIFO(size=1,name="offset").wtPort(x2149_b2245_x2158_b2247_s)
      val x2149_b2246 = ScalarFIFO(size=1,name="size").wtPort(x2149_b2246_x2158_b2248_s)
      CU.newSout("data", x2150_x2160_data_s)
    }
    val x2167 = Pipeline(name="x2167",parent=x2168) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2162 = CounterChain(name = "x2162", ctr5).iter(24)
    }
    val x2166_0 = Pipeline(name="x2166_0",parent=x2168) { implicit CU => 
      val x2162 = CounterChain.copy("x2167", "x2162").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2162(0))), op=Bypass, results=List(CU.scalarOut(x2088_x2166_wa_s)))
    }
    val x2195 = StreamController(name="x2195",parent=x2202) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2171 = CounterChain(name = "x2171", ctr6).iter(24)
    }
    val x2195_0 = Pipeline(name="x2195_0",parent=x2195) { implicit CU => 
      val x2180 = CU.temp(None)
      val x2181 = CU.temp(None)
      val x2183 = CU.temp(None)
      val x2179 = CU.temp(None)
      val x2173 = ScalarFIFO(size=1,name="x2173").wtPort(x2085_x2085_dsp0_bank0_data_s)
      val x2174 = ScalarFIFO(size=1,name="x2174").wtPort(x2087_x2087_dsp0_bank0_data_s)
      Stage(operands=List(Const(0), CU.load(x2173)), op=FixLt, results=List(x2179))
      Stage(operands=List(CU.load(x2173), Const(9999)), op=FixLt, results=List(x2180))
      Stage(operands=List(x2179, x2180), op=BitAnd, results=List(x2181))
      Stage(operands=List(Const(0), CU.load(x2174)), op=FixLeq, results=List(x2183))
      Stage(operands=List(x2181, x2183), op=BitAnd, results=List(CU.vecOut(bus_160_v)))
      Stage(operands=List(CU.load(x2174), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_161_v)))
    }
    val x2195_1 = Pipeline(name="x2195_1",parent=x2195) { implicit CU => 
      val x2191 = CU.temp(None)
      val x2188 = CU.temp(None)
      val x2189 = CU.temp(None)
      val x2186 = CU.temp(None)
      val x2174 = ScalarFIFO(size=1,name="x2174").wtPort(x2087_x2087_dsp0_bank0_data_s)
      val x2175 = ScalarFIFO(size=1,name="x2175").wtPort(x2086_x2086_dsp0_bank0_data_s)
      val x2185 = VectorFIFO(size=1,name="x2185").wtPort(bus_161_v)
      val x2184 = VectorFIFO(size=1,name="x2184").wtPort(bus_160_v)
      val x2176 = ScalarFIFO(size=1,name="x2176").wtPort(x2088_x2088_dsp0_bank0_data_s)
      Stage(operands=List(CU.load(x2184), CU.load(x2185)), op=BitAnd, results=List(x2186))
      Stage(operands=List(CU.load(x2175), Const(24)), op=FixLt, results=List(x2188))
      Stage(operands=List(x2186, x2188), op=BitAnd, results=List(x2189))
      Stage(operands=List(CU.load(x2176), CU.load(x2174)), op=FixMul, results=List(x2191))
      Stage(operands=List(x2189, x2191, Const(0)), op=MuxOp, results=List(CU.vecOut(bus_168_v)))
    }
    val x2195_2 = Pipeline(name="x2195_2",parent=x2195) { implicit CU => 
      val rr168 = VectorFIFO(size=1,name="rr168").wtPort(bus_168_v)
      Stage(operands=List(CU.load(rr168)), op=Bypass, results=List(CU.reduce))
      val (_, rr170) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2195")
      Stage(operands=List(rr170), op=Bypass, results=List(CU.scalarOut(x2169_x2194_s)))
    }
    val x2173_0 = Pipeline(name="x2173_0",parent=x2202) { implicit CU => 
      val x2171 = CounterChain.copy("x2195", "x2171").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2171(0))), op=Bypass, results=List(CU.scalarOut(x2085_x2173_ra_s)))
    }
    val x2174_0 = Pipeline(name="x2174_0",parent=x2202) { implicit CU => 
      val x2171 = CounterChain.copy("x2195", "x2171").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2171(0))), op=Bypass, results=List(CU.scalarOut(x2087_x2174_ra_s)))
    }
    val x2175_0 = Pipeline(name="x2175_0",parent=x2202) { implicit CU => 
      val x2171 = CounterChain.copy("x2195", "x2171").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2171(0))), op=Bypass, results=List(CU.scalarOut(x2086_x2175_ra_s)))
    }
    val x2176_0 = Pipeline(name="x2176_0",parent=x2202) { implicit CU => 
      val x2171 = CounterChain.copy("x2195", "x2171").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x2171(0))), op=Bypass, results=List(CU.scalarOut(x2088_x2176_ra_s)))
    }
    val x2201 = StreamController(name="x2201",parent=x2202) { implicit CU => 
      val x2201_unit = CounterChain(name = "x2201_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2201_1 = Pipeline(name="x2201_1",parent=x2201) { implicit CU => 
      val rr171 = ScalarFIFO(size=1,name="rr171").wtPort(x2169_x2194_s)
      Stage(operands=List(CU.load(rr171)), op=Bypass, results=List(CU.reduce))
      val (_, rr173) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2202")
      Stage(operands=List(rr173), op=Bypass, results=List(CU.scalarOut(x2076_x2204_argout)))
    }
    
  }
}
