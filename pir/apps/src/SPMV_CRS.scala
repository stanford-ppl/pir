import pir._
import pir.node._
import arch._
import pirc.enums._

object SPMV_CRS extends PIRApp {
  def main(top:Top) = {
    val rowid_dram_da = DRAMAddress("rowid_dram", "rowid_dram")
    val result_dram_da = DRAMAddress("result_dram", "result_dram")
    val x5059_b5474_x5091_b5482_s = Scalar("x5059_b5474_x5091_b5482")
    val x5126_x5349_ra_v = Vector("x5126_x5349_ra")
    val x5125_x5273_wa_s = Scalar("x5125_x5273_wa")
    val x5060_x5093_data_s = Scalar("x5060_x5093_data")
    val x5124_x5309_ra_s = Scalar("x5124_x5309_ra")
    val values_dram_oc = OffChip("values_dram")
    val x5277_x5282_s = Scalar("x5277_x5282")
    val x5377_x5409_s = Scalar("x5377_x5409")
    val x5125_x5125_dsp0_bank0_data_v = Vector("x5125_x5125_dsp0_bank0_data")
    val result_dram_oc = OffChip("result_dram")
    val x5048_x5055_s = Scalar("x5048_x5055")
    val values_dram_da = DRAMAddress("values_dram", "values_dram")
    val x5045_x5045_dsp1_bank0_data_s = Scalar("x5045_x5045_dsp1_bank0_data")
    val x5059_b5473_x5091_b5481_s = Scalar("x5059_b5473_x5091_b5481")
    val vec_dram_oc = OffChip("vec_dram")
    val x5143_b5488_x5175_b5496_s = Scalar("x5143_b5488_x5175_b5496")
    val x5124_x5124_dsp0_bank0_data_s = Scalar("x5124_x5124_dsp0_bank0_data")
    val rowid_dram_oc = OffChip("rowid_dram")
    val x5375_x5407_s = Scalar("x5375_x5407")
    val x5212_x5246_data_s = Scalar("x5212_x5246_data")
    val x5047_x5054_s = Scalar("x5047_x5054")
    val x5058_b5471_x5089_b5479_s = Scalar("x5058_b5471_x5089_b5479")
    val x5363_x5369_s = Scalar("x5363_x5369")
    val x5145_x5179_data_s = Scalar("x5145_x5179_data")
    val x5045_x5045_dsp0_bank0_data_s = Scalar("x5045_x5045_dsp0_bank0_data")
    val x5364_x5370_s = Scalar("x5364_x5370")
    val x5126_x5328_wa_s = Scalar("x5126_x5328_wa")
    val x5124_x5206_wa_s = Scalar("x5124_x5206_wa")
    val x5211_b5504_x5244_b5512_s = Scalar("x5211_b5504_x5244_b5512")
    val cols_dram_da = DRAMAddress("cols_dram", "cols_dram")
    val x5058_b5472_x5089_b5480_s = Scalar("x5058_b5472_x5089_b5480")
    val x5298_x5315_s = Scalar("x5298_x5315")
    val x5049_x5056_s = Scalar("x5049_x5056")
    val vec_dram_da = DRAMAddress("vec_dram", "vec_dram")
    val x5210_b5502_x5242_b5510_s = Scalar("x5210_b5502_x5242_b5510")
    val x5143_b5487_x5175_b5495_s = Scalar("x5143_b5487_x5175_b5495")
    val x5045_x5132_ra_s = Scalar("x5045_x5132_ra")
    val cols_dram_oc = OffChip("cols_dram")
    val x5144_b5489_x5177_b5497_s = Scalar("x5144_b5489_x5177_b5497")
    val x5045_x5118_wa_s = Scalar("x5045_x5118_wa")
    val x5210_b5501_x5242_b5509_s = Scalar("x5210_b5501_x5242_b5509")
    val x5373_x5426_s = Scalar("x5373_x5426")
    val x5299_x5317_data_s = Scalar("x5299_x5317_data")
    val x5046_x5046_dsp0_bank0_data_s = Scalar("x5046_x5046_dsp0_bank0_data")
    val x5284_x5296_s = Scalar("x5284_x5296")
    val x5126_x5126_dsp0_bank0_data_v = Vector("x5126_x5126_dsp0_bank0_data")
    val x5333_x5356_s = Scalar("x5333_x5356")
    val x5144_b5490_x5177_b5498_s = Scalar("x5144_b5490_x5177_b5498")
    val x5211_b5503_x5244_b5511_s = Scalar("x5211_b5503_x5244_b5511")
    val x5372_b5520_x5406_b5522_s = Scalar("x5372_b5520_x5406_b5522")
    val x5376_x5408_s = Scalar("x5376_x5408")
    val x5372_b5521_x5406_b5523_s = Scalar("x5372_b5521_x5406_b5523")
    val x5136_x5141_s = Scalar("x5136_x5141")
    val x5045_x5130_ra_s = Scalar("x5045_x5130_ra")
    val x5046_x5422_ra_s = Scalar("x5046_x5422_ra")
    val x5125_x5347_ra_v = Vector("x5125_x5347_ra")
    val x5046_x5360_wa_s = Scalar("x5046_x5360_wa")
    val x5433 = MetaPipeline(name="x5433",parent="top") { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5044 = CounterChain(name = "x5044", ctr1).iter(1)
    }
    val x5045_dsp0_bank0 = MemoryPipeline(name="x5045_dsp0_bank0",parent="x5433") { implicit CU => 
      val b5484 = ScalarFIFO(size=1,name="b5484").wtPort(x5045_x5118_wa_s)
      val b5486 = ScalarFIFO(size=1,name="b5486").wtPort(x5045_x5132_ra_s)
      val x5118 = ScalarFIFO(size=1,name="x5118").wtPort(x5060_x5093_data_s)
      val x5045 = SRAM(size=495,name="x5045",banking = NoBanking()).buffering(1).wtPort(x5118.readPort).rdPort(x5045_x5045_dsp0_bank0_data_s).rdAddr(b5486.readPort).wtAddr(b5484.readPort)
    }
    val x5045_dsp1_bank0 = MemoryPipeline(name="x5045_dsp1_bank0",parent="x5433") { implicit CU => 
      val b5484 = ScalarFIFO(size=1,name="b5484").wtPort(x5045_x5118_wa_s)
      val x5118 = ScalarFIFO(size=1,name="x5118").wtPort(x5060_x5093_data_s)
      val b5485 = ScalarFIFO(size=1,name="b5485").wtPort(x5045_x5130_ra_s)
      val x5045 = SRAM(size=495,name="x5045",banking = NoBanking()).buffering(1).wtPort(x5118.readPort).rdPort(x5045_x5045_dsp1_bank0_data_s).rdAddr(b5485.readPort).wtAddr(b5484.readPort)
    }
    val x5046_dsp0_bank0 = MemoryPipeline(name="x5046_dsp0_bank0",parent="x5433") { implicit CU => 
      val b5519 = ScalarFIFO(size=1,name="b5519").wtPort(x5046_x5360_wa_s)
      val x5360 = ScalarFIFO(size=1,name="x5360").wtPort(x5333_x5356_s)
      val b5524 = ScalarFIFO(size=1,name="b5524").wtPort(x5046_x5422_ra_s)
      val x5046 = SRAM(size=494,name="x5046",banking = NoBanking()).buffering(1).wtPort(x5360.readPort).rdPort(x5046_x5046_dsp0_bank0_data_s).rdAddr(b5524.readPort).wtAddr(b5519.readPort)
    }
    val x5057 = Pipeline(name="x5057",parent="x5433") { implicit CU => 
      val x5052 = CU.temp(None).name("x5052")
      val x5050 = CU.temp(None).name("x5050")
      val x5051 = CU.temp(None).name("x5051")
      val x5044 = CounterChain.copy("x5433", "x5044").iterIdx(0, 0)
      val x5057_unit = CounterChain(name = "x5057_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5044(0), Const(495)), op=FixMul, results=List(x5050, CU.scalarOut(x5047_x5054_s)))
      Stage(operands=List(x5044(0), Const(1)), op=FixAdd, results=List(x5051, CU.scalarOut(x5048_x5055_s)))
      Stage(operands=List(x5051, Const(495)), op=FixMul, results=List(x5052))
      Stage(operands=List(x5052, x5050), op=FixSub, results=List(CU.scalarOut(x5049_x5056_s)))
    }
    val x5121 = StreamController(name="x5121",parent="x5433") { implicit CU => 
      val x5121_unit = CounterChain(name = "x5121_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5092 = Pipeline(name="x5092",parent="x5121") { implicit CU => 
      val x5065 = CU.temp(None).name("x5065")
      val x5063 = CU.temp(None).name("x5063")
      val x5072 = CU.temp(None).name("x5072")
      val x5074 = CU.temp(None).name("x5074")
      val x5070 = CU.temp(None).name("x5070")
      val x5080 = CU.temp(None).name("x5080")
      val x5090_x5077 = CU.temp(None).name("x5090_x5077")
      val x5075 = CU.temp(None).name("x5075")
      val x5082 = CU.temp(None).name("x5082")
      val x5069 = CU.temp(None).name("x5069")
      val x5078 = CU.temp(None).name("x5078")
      val x5068 = CU.temp(None).name("x5068")
      val x5076 = CU.temp(None).name("x5076")
      val x5047 = ScalarBuffer(name="x5047").buffering(1).wtPort(x5047_x5054_s)
      val x5085 = ScalarBuffer(name="x5085").buffering(1).wtPort(rowid_dram_da)
      val x5049 = ScalarBuffer(name="x5049").buffering(1).wtPort(x5049_x5056_s)
      val x5092_unit = CounterChain(name = "x5092_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5047, Const(2)), op=FixSla, results=List(x5063))
      Stage(operands=List(x5063, Const(31)), op=BitAnd, results=List(x5065))
      Stage(operands=List(x5065, Const(2)), op=FixSra, results=List(x5090_x5077, CU.scalarOut(x5059_b5474_x5091_b5482_s)))
      Stage(operands=List(x5049, Const(2)), op=FixSla, results=List(x5068))
      Stage(operands=List(x5063, x5068), op=FixAdd, results=List(x5070))
      Stage(operands=List(x5070, Const(31)), op=BitAnd, results=List(x5072))
      Stage(operands=List(x5072, Const(0)), op=FixEql, results=List(x5074))
      Stage(operands=List(Const(64), x5072), op=FixSub, results=List(x5075))
      Stage(operands=List(x5074, Const(0), x5075), op=MuxOp, results=List(x5076))
      Stage(operands=List(x5076, Const(2)), op=FixSra, results=List(x5078))
      Stage(operands=List(x5049, x5090_x5077), op=FixAdd, results=List(x5080))
      Stage(operands=List(x5080, x5078), op=FixAdd, results=List(CU.scalarOut(x5059_b5473_x5091_b5481_s)))
      Stage(operands=List(x5068, x5065), op=FixAdd, results=List(x5082))
      Stage(operands=List(x5082, x5076), op=FixAdd, results=List(CU.scalarOut(x5058_b5472_x5089_b5480_s)))
      Stage(operands=List(x5063, x5065), op=FixSub, results=List(x5069))
      Stage(operands=List(x5069, x5085), op=FixAdd, results=List(CU.scalarOut(x5058_b5471_x5089_b5479_s)))
    }
    val x5093 = MemoryController(name="x5093",parent="x5121",offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5058_b5472 = ScalarFIFO(size=1,name="size").wtPort(x5058_b5472_x5089_b5480_s)
      val x5058_b5471 = ScalarFIFO(size=1,name="offset").wtPort(x5058_b5471_x5089_b5479_s)
      CU.newOut("data", x5060_x5093_data_s)
    }
    val x5120 = Sequential(name="x5120",parent="x5121") { implicit CU => 
      val x5120_unit = CounterChain(name = "x5120_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5119 = Pipeline(name="x5119",parent="x5120") { implicit CU => 
      val x5094 = ScalarBuffer(name="x5094").buffering(1).wtPort(x5059_b5474_x5091_b5482_s)
      val x5096 = ScalarBuffer(name="x5096").buffering(1).wtPort(x5059_b5473_x5091_b5481_s)
      val ctr2 = Counter(min=Const(0), max=x5096.readPort, step=Const(1), par=1) // Counter
      val x5107 = CounterChain(name = "x5107", ctr2).iter(1)
      Stage(operands=List(x5107(0), x5094), op=FixSub, results=List(CU.scalarOut(x5045_x5118_wa_s)))
    }
    val x5362 = MetaPipeline(name="x5362",parent="x5433") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(494), step=Const(1), par=1) // Counter
      val x5123 = CounterChain(name = "x5123", ctr3).iter(494)
    }
    val x5124_dsp0_bank0 = MemoryPipeline(name="x5124_dsp0_bank0",parent="x5362") { implicit CU => 
      val x5206 = ScalarFIFO(size=1,name="x5206").wtPort(x5145_x5179_data_s)
      val b5515 = ScalarFIFO(size=1,name="b5515").wtPort(x5124_x5309_ra_s)
      val b5500 = ScalarFIFO(size=1,name="b5500").wtPort(x5124_x5206_wa_s)
      val x5124 = SRAM(size=494,name="x5124",banking = NoBanking()).buffering(4).wtPort(x5206.readPort).rdPort(x5124_x5124_dsp0_bank0_data_s).rdAddr(b5515.readPort).wtAddr(b5500.readPort).consumer("x5309", "x5330").producer("x5207", "x5209")
    }
    val x5125_dsp0_bank0 = MemoryPipeline(name="x5125_dsp0_bank0",parent="x5362") { implicit CU => 
      val b5514 = ScalarFIFO(size=1,name="b5514").wtPort(x5125_x5273_wa_s)
      val b5517 = VectorFIFO(size=1,name="b5517").wtPort(x5125_x5347_ra_v)
      val x5273 = ScalarFIFO(size=1,name="x5273").wtPort(x5212_x5246_data_s)
      val x5125 = SRAM(size=247,name="x5125",banking = Strided(1,2)).buffering(6).wtPort(x5273.readPort).rdPort(x5125_x5125_dsp0_bank0_data_v).rdAddr(b5517.readPort).wtAddr(b5514.readPort).consumer("x5347", "x5357").producer("x5274", "x5276")
    }
    val x5126_dsp0_bank0 = MemoryPipeline(name="x5126_dsp0_bank0",parent="x5362") { implicit CU => 
      val x5328 = ScalarFIFO(size=1,name="x5328").wtPort(x5299_x5317_data_s)
      val b5516 = ScalarFIFO(size=1,name="b5516").wtPort(x5126_x5328_wa_s)
      val b5518 = VectorFIFO(size=1,name="b5518").wtPort(x5126_x5349_ra_v)
      val x5126 = SRAM(size=247,name="x5126",banking = Strided(1,2)).buffering(3).wtPort(x5328.readPort).rdPort(x5126_x5126_dsp0_bank0_data_v).rdAddr(b5518.readPort).wtAddr(b5516.readPort).consumer("x5349", "x5357").producer("x5329", "x5330")
    }
    val x5130 = Pipeline(name="x5130",parent="x5362") { implicit CU => 
      val x5123 = CounterChain.copy("x5362", "x5123").iterIdx(0, 0)
      Stage(operands=List(x5123(0)), op=Bypass, results=List(CU.scalarOut(x5045_x5130_ra_s)))
    }
    val x5132 = Pipeline(name="x5132",parent="x5362") { implicit CU => 
      val x5123 = CounterChain.copy("x5362", "x5123").iterIdx(0, 0)
      Stage(operands=List(x5123(0), Const(1)), op=FixAdd, results=List(CU.scalarOut(x5045_x5132_ra_s)))
    }
    val x5142 = Pipeline(name="x5142",parent="x5362") { implicit CU => 
      val x5128 = ScalarBuffer(name="x5128").buffering(2).wtPort(x5045_x5045_dsp0_bank0_data_s).consumer("x5142", "x5142").producer("x5135", "x5135")
      val x5127 = ScalarBuffer(name="x5127").buffering(2).wtPort(x5045_x5045_dsp1_bank0_data_s).consumer("x5142", "x5142").producer("x5135", "x5135")
      val x5142_unit = CounterChain(name = "x5142_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5128, x5127), op=FixSub, results=List(CU.scalarOut(x5136_x5141_s)))
    }
    val x5209 = StreamController(name="x5209",parent="x5362") { implicit CU => 
      val x5209_unit = CounterChain(name = "x5209_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5178 = Pipeline(name="x5178",parent="x5209") { implicit CU => 
      val x5159 = CU.temp(None).name("x5159")
      val x5165 = CU.temp(None).name("x5165")
      val x5155 = CU.temp(None).name("x5155")
      val x5150 = CU.temp(None).name("x5150")
      val x5176_x5162 = CU.temp(None).name("x5176_x5162")
      val x5161 = CU.temp(None).name("x5161")
      val x5154 = CU.temp(None).name("x5154")
      val x5160 = CU.temp(None).name("x5160")
      val x5163 = CU.temp(None).name("x5163")
      val x5153 = CU.temp(None).name("x5153")
      val x5167 = CU.temp(None).name("x5167")
      val x5148 = CU.temp(None).name("x5148")
      val x5157 = CU.temp(None).name("x5157")
      val x5170 = ScalarBuffer(name="x5170").buffering(1).wtPort(cols_dram_da)
      val x5136 = ScalarBuffer(name="x5136").buffering(1).wtPort(x5136_x5141_s)
      val x5127 = ScalarBuffer(name="x5127").buffering(2).wtPort(x5045_x5045_dsp1_bank0_data_s).consumer("x5178", "x5209").producer("x5135", "x5135")
      val x5178_unit = CounterChain(name = "x5178_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5127, Const(2)), op=FixSla, results=List(x5148))
      Stage(operands=List(x5148, Const(31)), op=BitAnd, results=List(x5150))
      Stage(operands=List(x5150, Const(2)), op=FixSra, results=List(x5176_x5162, CU.scalarOut(x5144_b5490_x5177_b5498_s)))
      Stage(operands=List(x5136, Const(2)), op=FixSla, results=List(x5153))
      Stage(operands=List(x5148, x5153), op=FixAdd, results=List(x5155))
      Stage(operands=List(x5155, Const(31)), op=BitAnd, results=List(x5157))
      Stage(operands=List(x5157, Const(0)), op=FixEql, results=List(x5159))
      Stage(operands=List(Const(64), x5157), op=FixSub, results=List(x5160))
      Stage(operands=List(x5159, Const(0), x5160), op=MuxOp, results=List(x5161))
      Stage(operands=List(x5161, Const(2)), op=FixSra, results=List(x5163))
      Stage(operands=List(x5136, x5176_x5162), op=FixAdd, results=List(x5165))
      Stage(operands=List(x5165, x5163), op=FixAdd, results=List(CU.scalarOut(x5144_b5489_x5177_b5497_s)))
      Stage(operands=List(x5153, x5150), op=FixAdd, results=List(x5167))
      Stage(operands=List(x5167, x5161), op=FixAdd, results=List(CU.scalarOut(x5143_b5488_x5175_b5496_s)))
      Stage(operands=List(x5148, x5150), op=FixSub, results=List(x5154))
      Stage(operands=List(x5154, x5170), op=FixAdd, results=List(CU.scalarOut(x5143_b5487_x5175_b5495_s)))
    }
    val x5179 = MemoryController(name="x5179",parent="x5209",offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5143_b5487 = ScalarFIFO(size=1,name="offset").wtPort(x5143_b5487_x5175_b5495_s)
      val x5143_b5488 = ScalarFIFO(size=1,name="size").wtPort(x5143_b5488_x5175_b5496_s)
      CU.newOut("data", x5145_x5179_data_s)
    }
    val x5208 = Sequential(name="x5208",parent="x5209") { implicit CU => 
      val x5208_unit = CounterChain(name = "x5208_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5207 = Pipeline(name="x5207",parent="x5208") { implicit CU => 
      val x5182 = ScalarBuffer(name="x5182").buffering(1).wtPort(x5144_b5489_x5177_b5497_s)
      val x5180 = ScalarBuffer(name="x5180").buffering(1).wtPort(x5144_b5490_x5177_b5498_s)
      val ctr4 = Counter(min=Const(0), max=x5182.readPort, step=Const(1), par=1) // Counter
      val x5194 = CounterChain(name = "x5194", ctr4).iter(1)
      Stage(operands=List(x5194(0), x5180), op=FixSub, results=List(CU.scalarOut(x5124_x5206_wa_s)))
    }
    val x5276 = StreamController(name="x5276",parent="x5362") { implicit CU => 
      val x5276_unit = CounterChain(name = "x5276_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5245 = Pipeline(name="x5245",parent="x5276") { implicit CU => 
      val x5220 = CU.temp(None).name("x5220")
      val x5221 = CU.temp(None).name("x5221")
      val x5227 = CU.temp(None).name("x5227")
      val x5230 = CU.temp(None).name("x5230")
      val x5232 = CU.temp(None).name("x5232")
      val x5226 = CU.temp(None).name("x5226")
      val x5224 = CU.temp(None).name("x5224")
      val x5215 = CU.temp(None).name("x5215")
      val x5243_x5229 = CU.temp(None).name("x5243_x5229")
      val x5228 = CU.temp(None).name("x5228")
      val x5217 = CU.temp(None).name("x5217")
      val x5234 = CU.temp(None).name("x5234")
      val x5222 = CU.temp(None).name("x5222")
      val x5136 = ScalarBuffer(name="x5136").buffering(1).wtPort(x5136_x5141_s)
      val x5127 = ScalarBuffer(name="x5127").buffering(2).wtPort(x5045_x5045_dsp1_bank0_data_s).consumer("x5245", "x5276").producer("x5135", "x5135")
      val x5237 = ScalarBuffer(name="x5237").buffering(1).wtPort(values_dram_da)
      val x5245_unit = CounterChain(name = "x5245_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5127, Const(2)), op=FixSla, results=List(x5215))
      Stage(operands=List(x5215, Const(31)), op=BitAnd, results=List(x5217))
      Stage(operands=List(x5217, Const(2)), op=FixSra, results=List(x5243_x5229, CU.scalarOut(x5211_b5504_x5244_b5512_s)))
      Stage(operands=List(x5136, Const(2)), op=FixSla, results=List(x5220))
      Stage(operands=List(x5215, x5220), op=FixAdd, results=List(x5222))
      Stage(operands=List(x5222, Const(31)), op=BitAnd, results=List(x5224))
      Stage(operands=List(x5224, Const(0)), op=FixEql, results=List(x5226))
      Stage(operands=List(Const(64), x5224), op=FixSub, results=List(x5227))
      Stage(operands=List(x5226, Const(0), x5227), op=MuxOp, results=List(x5228))
      Stage(operands=List(x5228, Const(2)), op=FixSra, results=List(x5230))
      Stage(operands=List(x5136, x5243_x5229), op=FixAdd, results=List(x5232))
      Stage(operands=List(x5232, x5230), op=FixAdd, results=List(CU.scalarOut(x5211_b5503_x5244_b5511_s)))
      Stage(operands=List(x5220, x5217), op=FixAdd, results=List(x5234))
      Stage(operands=List(x5234, x5228), op=FixAdd, results=List(CU.scalarOut(x5210_b5502_x5242_b5510_s)))
      Stage(operands=List(x5215, x5217), op=FixSub, results=List(x5221))
      Stage(operands=List(x5221, x5237), op=FixAdd, results=List(CU.scalarOut(x5210_b5501_x5242_b5509_s)))
    }
    val x5246 = MemoryController(name="x5246",parent="x5276",offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5210_b5502 = ScalarFIFO(size=1,name="size").wtPort(x5210_b5502_x5242_b5510_s)
      val x5210_b5501 = ScalarFIFO(size=1,name="offset").wtPort(x5210_b5501_x5242_b5509_s)
      CU.newOut("data", x5212_x5246_data_s)
    }
    val x5275 = Sequential(name="x5275",parent="x5276") { implicit CU => 
      val x5275_unit = CounterChain(name = "x5275_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5274 = Pipeline(name="x5274",parent="x5275") { implicit CU => 
      val x5247 = ScalarBuffer(name="x5247").buffering(1).wtPort(x5211_b5504_x5244_b5512_s)
      val x5249 = ScalarBuffer(name="x5249").buffering(1).wtPort(x5211_b5503_x5244_b5511_s)
      val ctr5 = Counter(min=Const(0), max=x5249.readPort, step=Const(1), par=1) // Counter
      val x5261 = CounterChain(name = "x5261", ctr5).iter(1)
      Stage(operands=List(x5261(0), x5247), op=FixSub, results=List(CU.scalarOut(x5125_x5273_wa_s)))
    }
    val x5283 = Pipeline(name="x5283",parent="x5362") { implicit CU => 
      val x5128 = ScalarBuffer(name="x5128").buffering(3).wtPort(x5045_x5045_dsp0_bank0_data_s).consumer("x5283", "x5283").producer("x5135", "x5135")
      val x5127 = ScalarBuffer(name="x5127").buffering(3).wtPort(x5045_x5045_dsp1_bank0_data_s).consumer("x5283", "x5283").producer("x5135", "x5135")
      val x5283_unit = CounterChain(name = "x5283_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5128, x5127), op=FixSub, results=List(CU.scalarOut(x5277_x5282_s)))
    }
    val x5297 = Pipeline(name="x5297",parent="x5362") { implicit CU => 
      val x5286 = CU.temp(None).name("x5286")
      val x5288 = CU.temp(None).name("x5288")
      val x5293 = CU.temp(None).name("x5293")
      val x5290 = CU.temp(None).name("x5290")
      val x5292 = CU.temp(None).name("x5292")
      val x5291 = CU.temp(None).name("x5291")
      val x5277 = ScalarBuffer(name="x5277").buffering(2).wtPort(x5277_x5282_s).consumer("x5297", "x5297").producer("x5283", "x5283")
      val x5297_unit = CounterChain(name = "x5297_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5277, Const(16)), op=FixLt, results=List(x5286))
      Stage(operands=List(x5277, Const(7)), op=BitAnd, results=List(x5288))
      Stage(operands=List(x5288, Const(0)), op=FixEql, results=List(x5290))
      Stage(operands=List(x5277, Const(16)), op=FixAdd, results=List(x5291))
      Stage(operands=List(x5291, x5288), op=FixSub, results=List(x5292))
      Stage(operands=List(x5290, x5277, x5292), op=MuxOp, results=List(x5293))
      Stage(operands=List(x5286, Const(16), x5293), op=MuxOp, results=List(CU.scalarOut(x5284_x5296_s)))
    }
    val x5330 = StreamController(name="x5330",parent="x5362") { implicit CU => 
      val x5330_unit = CounterChain(name = "x5330_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5316 = Pipeline(name="x5316",parent="x5330") { implicit CU => 
      val x5311 = CU.temp(None).name("x5311")
      val x5313 = CU.temp(None).name("x5313")
      val x5304 = CU.temp(None).name("x5304")
      val x5305 = ScalarBuffer(name="x5305").buffering(1).wtPort(vec_dram_da)
      val x5277 = ScalarBuffer(name="x5277").buffering(3).wtPort(x5277_x5282_s).consumer("x5316", "x5330").producer("x5283", "x5283")
      val x5309 = ScalarFIFO(size=1,name="x5309").wtPort(x5124_x5124_dsp0_bank0_data_s)
      val x5284 = ScalarBuffer(name="x5284").buffering(2).wtPort(x5284_x5296_s).consumer("x5316", "x5330").producer("x5297", "x5297")
      val ctr6 = Counter(min=Const(0), max=x5284.readPort, step=Const(1), par=1) // Counter
      val x5302 = CounterChain(name = "x5302", ctr6).iter(1)
      Stage(operands=List(x5277, x5302(0)), op=FixLeq, results=List(x5304))
      Stage(operands=List(x5309, Const(2)), op=FixSla, results=List(x5311))
      Stage(operands=List(x5311, x5305), op=FixAdd, results=List(x5313))
      Stage(operands=List(x5304, x5305, x5313), op=MuxOp, results=List(CU.scalarOut(x5298_x5315_s)))
    }
    val x5309 = Pipeline(name="x5309",parent="x5330") { implicit CU => 
      val x5302 = CounterChain.copy("x5316", "x5302").iterIdx(0, 0)
      Stage(operands=List(x5302(0)), op=Bypass, results=List(CU.scalarOut(x5124_x5309_ra_s)))
    }
    val x5317 = MemoryController(name="x5317",parent="x5330",offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x5298 = ScalarFIFO(size=1,name="addr").wtPort(x5298_x5315_s)
      CU.newOut("data", x5299_x5317_data_s)
    }
    val x5329 = Pipeline(name="x5329",parent="x5330") { implicit CU => 
      val x5284 = ScalarBuffer(name="x5284").buffering(2).wtPort(x5284_x5296_s).consumer("x5329", "x5330").producer("x5297", "x5297")
      val ctr7 = Counter(min=Const(0), max=x5284.readPort, step=Const(1), par=1) // Counter
      val x5320 = CounterChain(name = "x5320", ctr7).iter(1)
      Stage(operands=List(x5320(0)), op=Bypass, results=List(CU.scalarOut(x5126_x5328_wa_s)))
    }
    val x5357 = Pipeline(name="x5357",parent="x5362") { implicit CU => 
      val x5333 = CU.temp(Some(0.0)).name("x5333")
      val x5349 = VectorFIFO(size=1,name="x5349").wtPort(x5126_x5126_dsp0_bank0_data_v)
      val x5277 = ScalarBuffer(name="x5277").buffering(5).wtPort(x5277_x5282_s).consumer("x5357", "x5357").producer("x5283", "x5283")
      val x5347 = VectorFIFO(size=1,name="x5347").wtPort(x5125_x5125_dsp0_bank0_data_v)
      val ctr8 = Counter(min=Const(0), max=x5277.readPort, step=Const(1), par=2) // Counter
      val x5344 = CounterChain(name = "x5344", ctr8).iter(1)
      Stage(operands=List(x5347, x5349), op=FixMul, results=List(CU.reduce))
      val (_, rr412) = Stage.reduce(op=FixAdd, init=Const(0.0), accumParent="x5357")
      Stage(operands=List(rr412), op=Bypass, results=List(CU.scalarOut(x5333_x5356_s)))
    }
    val x5347 = Pipeline(name="x5347",parent="x5362") { implicit CU => 
      val x5344 = CounterChain.copy("x5357", "x5344").iterIdx(0, 0)
      Stage(operands=List(x5344(0)), op=Bypass, results=List(CU.vecOut(x5125_x5347_ra_v)))
    }
    val x5349 = Pipeline(name="x5349",parent="x5362") { implicit CU => 
      val x5344 = CounterChain.copy("x5357", "x5344").iterIdx(0, 0)
      Stage(operands=List(x5344(0)), op=Bypass, results=List(CU.vecOut(x5126_x5349_ra_v)))
    }
    val x5361 = Pipeline(name="x5361",parent="x5362") { implicit CU => 
      val x5123 = CounterChain.copy("x5362", "x5123").iterIdx(0, 0)
      val x5361_unit = CounterChain(name = "x5361_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5123(0)), op=Bypass, results=List(CU.scalarOut(x5046_x5360_wa_s)))
    }
    val x5371 = Pipeline(name="x5371",parent="x5433") { implicit CU => 
      val x5365 = CU.temp(None).name("x5365")
      val x5367 = CU.temp(None).name("x5367")
      val x5048 = ScalarBuffer(name="x5048").buffering(1).wtPort(x5048_x5055_s)
      val x5044 = CounterChain.copy("x5433", "x5044").iterIdx(0, 0)
      val x5371_unit = CounterChain(name = "x5371_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5044(0), Const(494)), op=FixMul, results=List(x5365, CU.scalarOut(x5363_x5369_s)))
      Stage(operands=List(x5048, Const(494)), op=FixMul, results=List(x5367))
      Stage(operands=List(x5367, x5365), op=FixSub, results=List(CU.scalarOut(x5364_x5370_s)))
    }
    val x5432 = StreamController(name="x5432",parent="x5433") { implicit CU => 
      val x5432_unit = CounterChain(name = "x5432_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5428 = Sequential(name="x5428",parent="x5432") { implicit CU => 
      val x5428_unit = CounterChain(name = "x5428_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5410 = Pipeline(name="x5410",parent="x5428") { implicit CU => 
      val x5385 = CU.temp(None).name("x5385")
      val x5392 = CU.temp(None).name("x5392")
      val x5391 = CU.temp(None).name("x5391")
      val x5380 = CU.temp(None).name("x5380")
      val x5399 = CU.temp(None).name("x5399")
      val x5386 = CU.temp(None).name("x5386")
      val x5395 = CU.temp(None).name("x5395")
      val x5393 = CU.temp(None).name("x5393")
      val x5394 = CU.temp(None).name("x5394")
      val x5387 = CU.temp(None).name("x5387")
      val x5397 = CU.temp(None).name("x5397")
      val x5389 = CU.temp(None).name("x5389")
      val x5382 = CU.temp(None).name("x5382")
      val x5364 = ScalarBuffer(name="x5364").buffering(1).wtPort(x5364_x5370_s)
      val x5363 = ScalarBuffer(name="x5363").buffering(1).wtPort(x5363_x5369_s)
      val x5402 = ScalarBuffer(name="x5402").buffering(1).wtPort(result_dram_da)
      val x5410_unit = CounterChain(name = "x5410_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x5363, Const(2)), op=FixSla, results=List(x5380))
      Stage(operands=List(x5364, Const(2)), op=FixSla, results=List(x5385))
      Stage(operands=List(x5380, x5385), op=FixAdd, results=List(x5387))
      Stage(operands=List(x5387, Const(31)), op=BitAnd, results=List(x5389))
      Stage(operands=List(x5389, Const(0)), op=FixEql, results=List(x5391))
      Stage(operands=List(Const(64), x5389), op=FixSub, results=List(x5392))
      Stage(operands=List(x5391, Const(0), x5392), op=MuxOp, results=List(x5393))
      Stage(operands=List(x5393, Const(2)), op=FixSra, results=List(x5395))
      Stage(operands=List(x5380, Const(31)), op=BitAnd, results=List(x5382))
      Stage(operands=List(x5382, Const(2)), op=FixSra, results=List(x5394, CU.scalarOut(x5375_x5407_s)))
      Stage(operands=List(x5364, x5394), op=FixAdd, results=List(x5397))
      Stage(operands=List(x5397, x5395), op=FixAdd, results=List(CU.scalarOut(x5377_x5409_s)))
      Stage(operands=List(x5394, x5364), op=FixAdd, results=List(CU.scalarOut(x5376_x5408_s)))
      Stage(operands=List(x5385, x5382), op=FixAdd, results=List(x5399))
      Stage(operands=List(x5399, x5393), op=FixAdd, results=List(CU.scalarOut(x5372_b5521_x5406_b5523_s)))
      Stage(operands=List(x5380, x5382), op=FixSub, results=List(x5386))
      Stage(operands=List(x5386, x5402), op=FixAdd, results=List(CU.scalarOut(x5372_b5520_x5406_b5522_s)))
    }
    val x5427 = Pipeline(name="x5427",parent="x5428") { implicit CU => 
      val x5425_x5418 = CU.temp(None).name("x5425_x5418")
      val x5417 = CU.temp(None).name("x5417")
      val x5415 = CU.temp(None).name("x5415")
      val x5376 = ScalarBuffer(name="x5376").buffering(1).wtPort(x5376_x5408_s)
      val x5375 = ScalarBuffer(name="x5375").buffering(1).wtPort(x5375_x5407_s)
      val x5422 = ScalarFIFO(size=1,name="x5422").wtPort(x5046_x5046_dsp0_bank0_data_s)
      val x5377 = ScalarBuffer(name="x5377").buffering(1).wtPort(x5377_x5409_s)
      val ctr9 = Counter(min=Const(0), max=x5377.readPort, step=Const(1), par=1) // Counter
      val x5413 = CounterChain(name = "x5413", ctr9).iter(1)
      Stage(operands=List(x5375, x5413(0)), op=FixLeq, results=List(x5415))
      Stage(operands=List(x5413(0), x5376), op=FixLt, results=List(x5417))
      Stage(operands=List(x5415, x5417), op=BitAnd, results=List(x5425_x5418))
      Stage(operands=List(x5425_x5418, x5422, Const(0.0)), op=MuxOp, results=List(CU.scalarOut(x5373_x5426_s)))
    }
    val x5422 = Pipeline(name="x5422",parent="x5428") { implicit CU => 
      val x5375 = ScalarBuffer(name="x5375").buffering(1).wtPort(x5375_x5407_s)
      val x5413 = CounterChain.copy("x5427", "x5413").iterIdx(0, 0)
      Stage(operands=List(x5413(0), x5375), op=FixSub, results=List(CU.scalarOut(x5046_x5422_ra_s)))
    }
    val x5429 = MemoryController(name="x5429",parent="x5432",offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x5372_b5520 = ScalarFIFO(size=1,name="offset").wtPort(x5372_b5520_x5406_b5522_s)
      val x5373 = ScalarFIFO(size=1,name="data").wtPort(x5373_x5426_s)
      val x5372_b5521 = ScalarFIFO(size=1,name="size").wtPort(x5372_b5521_x5406_b5523_s)
    }
    
  }
}
