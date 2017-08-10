import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GEMM_Blocked extends PIRApp {
  def main(top:Top) = {
    val x5495_b6104_x5510_b6106_s = Scalar("x5495_b6104_x5510_b6106")
    val x5492_x5492_dsp1_bank0_s = Scalar("x5492_x5492_dsp1_bank0")
    val x5568_x5585_data_s = Scalar("x5568_x5585_data")
    val x5567_b6115_x5583_b6117_s = Scalar("x5567_b6115_x5583_b6117")
    val x5362_x5362_dsp0_bank0_s = Scalar("x5362_x5362_dsp0_bank0")
    val x5189_x5207_s = Scalar("x5189_x5207")
    val x5185_x5185_dsp1_bank0_s = Scalar("x5185_x5185_dsp1_bank0")
    val x5122_b6063_x5138_b6065_s = Scalar("x5122_b6063_x5138_b6065")
    val x5112_x5112_dsp0_bank0_s = Scalar("x5112_x5112_dsp0_bank0")
    val x5808_x5808_dsp1_bank0_s = Scalar("x5808_x5808_dsp1_bank0")
    val x5495_b6103_x5510_b6105_s = Scalar("x5495_b6103_x5510_b6105")
    val x5491_x5491_dsp1_bank0_s = Scalar("x5491_x5491_dsp1_bank0")
    val x5122_b6064_x5138_b6066_s = Scalar("x5122_b6064_x5138_b6066")
    val x5631_x5631_dsp0_bank0_s = Scalar("x5631_x5631_dsp0_bank0")
    val x5041_x5041_dsp1_bank0_s = Scalar("x5041_x5041_dsp1_bank0")
    val x5186_x5262_s = Scalar("x5186_x5262")
    val x5492_x5492_dsp0_bank0_s = Scalar("x5492_x5492_dsp0_bank0")
    val x5366_x5384_s = Scalar("x5366_x5384")
    val x5599_b6120_x5615_b6122_s = Scalar("x5599_b6120_x5615_b6122")
    val x5288_x5288_dsp0_bank0_s = Scalar("x5288_x5288_dsp0_bank0")
    val b_dram_oc = OffChip("b_dram")
    val x5526_x5542_data_s = Scalar("x5526_x5542_data")
    val x5744_b6132_x5760_b6134_s = Scalar("x5744_b6132_x5760_b6134")
    val x5289_x5289_dsp0_bank0_s = Scalar("x5289_x5289_dsp0_bank0")
    val c_dram_oc = OffChip("c_dram")
    val x5808_x5884_s = Scalar("x5808_x5884")
    val x5185_x5223_s = Scalar("x5185_x5223")
    val x5776_b6135_x5792_b6137_s = Scalar("x5776_b6135_x5792_b6137")
    val x5044_x5044_dsp0_bank1_s = Scalar("x5044_x5044_dsp0_bank1")
    val x5228_x5246_s = Scalar("x5228_x5246")
    val x5972_b6161_x5986_b6163_s = Scalar("x5972_b6161_x5986_b6163")
    val a_dram_oc = OffChip("a_dram")
    val x5405_x5405_dsp0_bank0_s = Scalar("x5405_x5405_dsp0_bank0")
    val x5811_x5829_s = Scalar("x5811_x5829")
    val x5972_b6162_x5986_b6164_s = Scalar("x5972_b6162_x5986_b6164")
    val x5525_b6109_x5540_b6111_s = Scalar("x5525_b6109_x5540_b6111")
    val x5362_x5400_s = Scalar("x5362_x5400")
    val x5807_x5807_dsp1_bank0_s = Scalar("x5807_x5807_dsp1_bank0")
    val x5673_x5673_dsp0_bank0_s = Scalar("x5673_x5673_dsp0_bank0")
    val x5777_x5794_data_s = Scalar("x5777_x5794_data")
    val a_dram_da = DRAMAddress("a_dram", "a_dram")
    val c_dram_da = DRAMAddress("c_dram", "c_dram")
    val x5331_b6083_x5347_b6085_s = Scalar("x5331_b6083_x5347_b6085")
    val x5185_x5185_dsp0_bank0_s = Scalar("x5185_x5185_dsp0_bank0")
    val x5046_x5046_dsp1_bank0_s = Scalar("x5046_x5046_dsp1_bank0")
    val x5046_x5046_dsp0_bank0_s = Scalar("x5046_x5046_dsp0_bank0")
    val x5299_b6079_x5315_b6081_s = Scalar("x5299_b6079_x5315_b6081")
    val x5634_x5652_s = Scalar("x5634_x5652")
    val x5111_x5111_dsp0_bank0_s = Scalar("x5111_x5111_dsp0_bank0")
    val x5673_x5691_s = Scalar("x5673_x5691")
    val x5228_x5228_dsp0_bank0_s = Scalar("x5228_x5228_dsp0_bank0")
    val x5189_x5189_dsp0_bank0_s = Scalar("x5189_x5189_dsp0_bank0")
    val x5496_x5512_data_s = Scalar("x5496_x5512_data")
    val x5489_x5489_dsp0_bank1_s = Scalar("x5489_x5489_dsp0_bank1")
    val x5045_x5045_dsp0_bank0_s = Scalar("x5045_x5045_dsp0_bank0")
    val x5080_b6057_x5095_b6059_s = Scalar("x5080_b6057_x5095_b6059")
    val x5630_x5630_dsp1_bank0_s = Scalar("x5630_x5630_dsp1_bank0")
    val x5363_x5363_dsp0_bank0_s = Scalar("x5363_x5363_dsp0_bank0")
    val x5040_x5484_s = Scalar("x5040_x5484")
    val x5489_x5489_dsp0_bank0_s = Scalar("x5489_x5489_dsp0_bank0")
    val x5362_x5362_dsp1_bank0_s = Scalar("x5362_x5362_dsp1_bank0")
    val x5850_x5868_s = Scalar("x5850_x5868")
    val x5154_b6067_x5170_b6069_s = Scalar("x5154_b6067_x5170_b6069")
    val x5050_b6052_x5065_b6054_s = Scalar("x5050_b6052_x5065_b6054")
    val x5051_x5067_data_s = Scalar("x5051_x5067_data")
    val x5630_x5668_s = Scalar("x5630_x5668")
    val x5776_b6136_x5792_b6138_s = Scalar("x5776_b6136_x5792_b6138")
    val x5363_x5363_dsp1_bank0_s = Scalar("x5363_x5363_dsp1_bank0")
    val b_dram_da = DRAMAddress("b_dram", "b_dram")
    val x5363_x5439_s = Scalar("x5363_x5439")
    val x5299_b6080_x5315_b6082_s = Scalar("x5299_b6080_x5315_b6082")
    val x5599_b6119_x5615_b6121_s = Scalar("x5599_b6119_x5615_b6121")
    val x5366_x5366_dsp0_bank0_s = Scalar("x5366_x5366_dsp0_bank0")
    val x5808_x5808_dsp0_bank0_s = Scalar("x5808_x5808_dsp0_bank0")
    val x5807_x5845_s = Scalar("x5807_x5845")
    val x5081_x5097_data_s = Scalar("x5081_x5097_data")
    val x5733_x5733_dsp0_bank0_s = Scalar("x5733_x5733_dsp0_bank0")
    val x5634_x5634_dsp0_bank0_s = Scalar("x5634_x5634_dsp0_bank0")
    val x5556_x5556_dsp0_bank0_s = Scalar("x5556_x5556_dsp0_bank0")
    val x5600_x5617_data_s = Scalar("x5600_x5617_data")
    val x5631_x5631_dsp1_bank0_s = Scalar("x5631_x5631_dsp1_bank0")
    val x5044_x5044_dsp0_bank0_s = Scalar("x5044_x5044_dsp0_bank0")
    val x5567_b6116_x5583_b6118_s = Scalar("x5567_b6116_x5583_b6118")
    val x5491_x5491_dsp0_bank0_s = Scalar("x5491_x5491_dsp0_bank0")
    val x5850_x5850_dsp0_bank0_s = Scalar("x5850_x5850_dsp0_bank0")
    val x5080_b6058_x5095_b6060_s = Scalar("x5080_b6058_x5095_b6060")
    val x5300_x5317_data_s = Scalar("x5300_x5317_data")
    val x5045_x5045_dsp0_bank1_s = Scalar("x5045_x5045_dsp0_bank1")
    val x5123_x5140_data_s = Scalar("x5123_x5140_data")
    val x5186_x5186_dsp1_bank0_s = Scalar("x5186_x5186_dsp1_bank0")
    val x5490_x5490_dsp0_bank0_s = Scalar("x5490_x5490_dsp0_bank0")
    val x5745_x5762_data_s = Scalar("x5745_x5762_data")
    val x5630_x5630_dsp0_bank0_s = Scalar("x5630_x5630_dsp0_bank0")
    val x5186_x5186_dsp0_bank0_s = Scalar("x5186_x5186_dsp0_bank0")
    val x5047_x5047_dsp1_bank0_s = Scalar("x5047_x5047_dsp1_bank0")
    val x5047_x5047_dsp0_bank0_s = Scalar("x5047_x5047_dsp0_bank0")
    val x5405_x5423_s = Scalar("x5405_x5423")
    val x5631_x5707_s = Scalar("x5631_x5707")
    val x5490_x5490_dsp0_bank1_s = Scalar("x5490_x5490_dsp0_bank1")
    val x5041_x5041_dsp0_bank0_s = Scalar("x5041_x5041_dsp0_bank0")
    val x5050_b6051_x5065_b6053_s = Scalar("x5050_b6051_x5065_b6053")
    val x5154_b6068_x5170_b6070_s = Scalar("x5154_b6068_x5170_b6070")
    val x5940_b6155_x5954_b6157_s = Scalar("x5940_b6155_x5954_b6157")
    val x5331_b6084_x5347_b6086_s = Scalar("x5331_b6084_x5347_b6086")
    val x5807_x5807_dsp0_bank0_s = Scalar("x5807_x5807_dsp0_bank0")
    val x5734_x5734_dsp0_bank0_s = Scalar("x5734_x5734_dsp0_bank0")
    val x5040_x5040_dsp1_bank0_s = Scalar("x5040_x5040_dsp1_bank0")
    val x5040_x5040_dsp0_bank0_s = Scalar("x5040_x5040_dsp0_bank0")
    val x5332_x5349_data_s = Scalar("x5332_x5349_data")
    val x5744_b6131_x5760_b6133_s = Scalar("x5744_b6131_x5760_b6133")
    val x5041_x5929_s = Scalar("x5041_x5929")
    val x5525_b6110_x5540_b6112_s = Scalar("x5525_b6110_x5540_b6112")
    val x5557_x5557_dsp0_bank0_s = Scalar("x5557_x5557_dsp0_bank0")
    val x5940_b6156_x5954_b6158_s = Scalar("x5940_b6156_x5954_b6158")
    val x5811_x5811_dsp0_bank0_s = Scalar("x5811_x5811_dsp0_bank0")
    val x5155_x5172_data_s = Scalar("x5155_x5172_data")
    val x6004 = Sequential(name="x6004",parent=top) { implicit CU => 
      val x6004_unit = CounterChain(name = "x6004_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6003 = MetaPipeline(name="x6003",parent=x6004) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x5039 = CounterChain(name = "x5039", ctr1).iter(2)
    }
    val x5040_dsp0_bank0 = MemoryPipeline(name="x5040_dsp0_bank0",parent="x5486") { implicit CU => 
      val b6101 = CU.temp(None)
      val b6099 = CU.temp(None)
      val x5484 = ScalarFIFO(size=1,name="x5484").wtPort(x5040_x5484_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5040 = SRAM(size=1024,name="x5040",banking = Strided(1,16)).wtPort(x5484.readPort).rdPort(x5040_x5040_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6101))
      WAStage(operands=List(b6101, CU.ctr(x5466(1))), op=FixAdd, results=List(x5040.writeAddr))
      RAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6099))
      RAStage(operands=List(b6099, CU.ctr(x5466(1))), op=FixAdd, results=List(x5040.readAddr))
    }
    val x5040_dsp1_bank0 = MemoryPipeline(name="x5040_dsp1_bank0",parent="x5486") { implicit CU => 
      val b6159 = CU.temp(None)
      val b6101 = CU.temp(None)
      val x5484 = ScalarFIFO(size=1,name="x5484").wtPort(x5040_x5484_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5957 = CounterChain.copy("x5964", "x5957")
      val x5939 = CounterChain.copy("x5969", "x5939")
      val x5040 = SRAM(size=1024,name="x5040",banking = Strided(1,16)).wtPort(x5484.readPort).rdPort(x5040_x5040_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6101))
      WAStage(operands=List(b6101, CU.ctr(x5466(1))), op=FixAdd, results=List(x5040.writeAddr))
      RAStage(operands=List(CU.ctr(x5939(0)), Const(16)), op=FixMul, results=List(b6159))
      RAStage(operands=List(b6159, CU.ctr(x5957(0))), op=FixAdd, results=List(x5040.readAddr))
    }
    val x5041_dsp0_bank0 = MemoryPipeline(name="x5041_dsp0_bank0",parent="x5931") { implicit CU => 
      val b6153 = CU.temp(None)
      val b6151 = CU.temp(None)
      val x5929 = ScalarFIFO(size=1,name="x5929").wtPort(x5041_x5929_s)
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5041 = SRAM(size=1024,name="x5041",banking = Strided(1,16)).wtPort(x5929.readPort).rdPort(x5041_x5041_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6153))
      WAStage(operands=List(b6153, CU.ctr(x5911(1))), op=FixAdd, results=List(x5041.writeAddr))
      RAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6151))
      RAStage(operands=List(b6151, CU.ctr(x5911(1))), op=FixAdd, results=List(x5041.readAddr))
    }
    val x5041_dsp1_bank0 = MemoryPipeline(name="x5041_dsp1_bank0",parent="x5931") { implicit CU => 
      val b6153 = CU.temp(None)
      val b6165 = CU.temp(None)
      val x5929 = ScalarFIFO(size=1,name="x5929").wtPort(x5041_x5929_s)
      val x5989 = CounterChain.copy("x5996", "x5989")
      val x5971 = CounterChain.copy("x6001", "x5971")
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5041 = SRAM(size=1024,name="x5041",banking = Strided(1,16)).wtPort(x5929.readPort).rdPort(x5041_x5041_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6153))
      WAStage(operands=List(b6153, CU.ctr(x5911(1))), op=FixAdd, results=List(x5041.writeAddr))
      RAStage(operands=List(CU.ctr(x5971(0)), Const(16)), op=FixMul, results=List(b6165))
      RAStage(operands=List(b6165, CU.ctr(x5989(0))), op=FixAdd, results=List(x5041.readAddr))
    }
    val x5486 = MetaPipeline(name="x5486",parent=x6003) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x5043 = CounterChain(name = "x5043", ctr2).iter(2)
    }
    val x5044_dsp0_bank0 = MemoryPipeline(name="x5044_dsp0_bank0",parent="x5486") { implicit CU => 
      val b6095 = CU.temp(None)
      val b6075 = CU.temp(None)
      val x5273 = ScalarFIFO(size=1,name="x5273").wtPort(x5185_x5185_dsp1_bank0_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5110 = CounterChain.copy("x5285", "x5110")
      val x5267 = CounterChain.copy("x5274", "x5267")
      val x5044 = SRAM(size=1024,name="x5044",banking = Strided(1,16)).wtPort(x5273.readPort).rdPort(x5044_x5044_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5110(0)), Const(16)), op=FixMul, results=List(b6075))
      WAStage(operands=List(b6075, CU.ctr(x5267(0))), op=FixAdd, results=List(x5044.writeAddr))
      RAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6095))
      RAStage(operands=List(b6095, CU.ctr(x5466(1))), op=FixAdd, results=List(x5044.readAddr))
    }
    val x5044_dsp0_bank1 = MemoryPipeline(name="x5044_dsp0_bank1",parent="x5486") { implicit CU => 
      val b6095 = CU.temp(None)
      val b6077 = CU.temp(None)
      val x5282 = ScalarFIFO(size=1,name="x5282").wtPort(x5186_x5186_dsp1_bank0_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5110 = CounterChain.copy("x5285", "x5110")
      val x5276 = CounterChain.copy("x5283", "x5276")
      val x5044 = SRAM(size=1024,name="x5044",banking = Strided(1,16)).wtPort(x5282.readPort).rdPort(x5044_x5044_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x5110(0)), Const(16)), op=FixMul, results=List(b6077))
      WAStage(operands=List(b6077, CU.ctr(x5276(0))), op=FixAdd, results=List(x5044.writeAddr))
      RAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6095))
      RAStage(operands=List(b6095, CU.ctr(x5466(1))), op=FixAdd, results=List(x5044.readAddr))
    }
    val x5045_dsp0_bank0 = MemoryPipeline(name="x5045_dsp0_bank0",parent="x5486") { implicit CU => 
      val b6097 = CU.temp(None)
      val b6091 = CU.temp(None)
      val x5450 = ScalarFIFO(size=1,name="x5450").wtPort(x5362_x5362_dsp1_bank0_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5444 = CounterChain.copy("x5451", "x5444")
      val x5287 = CounterChain.copy("x5462", "x5287")
      val x5045 = SRAM(size=1024,name="x5045",banking = Strided(1,16)).wtPort(x5450.readPort).rdPort(x5045_x5045_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5287(0)), Const(16)), op=FixMul, results=List(b6091))
      WAStage(operands=List(b6091, CU.ctr(x5444(0))), op=FixAdd, results=List(x5045.writeAddr))
      RAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6097))
      RAStage(operands=List(b6097, CU.ctr(x5466(1))), op=FixAdd, results=List(x5045.readAddr))
    }
    val x5045_dsp0_bank1 = MemoryPipeline(name="x5045_dsp0_bank1",parent="x5486") { implicit CU => 
      val b6093 = CU.temp(None)
      val b6097 = CU.temp(None)
      val x5459 = ScalarFIFO(size=1,name="x5459").wtPort(x5363_x5363_dsp1_bank0_s)
      val x5466 = CounterChain.copy("x5485_0", "x5466")
      val x5287 = CounterChain.copy("x5462", "x5287")
      val x5453 = CounterChain.copy("x5460", "x5453")
      val x5045 = SRAM(size=1024,name="x5045",banking = Strided(1,16)).wtPort(x5459.readPort).rdPort(x5045_x5045_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x5287(0)), Const(16)), op=FixMul, results=List(b6093))
      WAStage(operands=List(b6093, CU.ctr(x5453(0))), op=FixAdd, results=List(x5045.writeAddr))
      RAStage(operands=List(CU.ctr(x5466(0)), Const(16)), op=FixMul, results=List(b6097))
      RAStage(operands=List(b6097, CU.ctr(x5466(1))), op=FixAdd, results=List(x5045.readAddr))
    }
    val x5046_dsp0_bank0 = MemoryPipeline(name="x5046_dsp0_bank0",parent="x5486") { implicit CU => 
      val b6071 = CU.temp(None)
      val b6055 = CU.temp(None)
      val x5075 = ScalarFIFO(size=1,name="x5075").wtPort(x5051_x5067_data_s)
      val x5188 = CounterChain.copy("x5225", "x5188")
      val x5049 = CounterChain.copy("x5077", "x5049")
      val x5198 = CounterChain.copy("x5208_0", "x5198")
      val x5069 = CounterChain.copy("x5076", "x5069")
      val x5046 = SRAM(size=256,name="x5046",banking = Strided(1,16)).wtPort(x5075.readPort).rdPort(x5046_x5046_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5049(0)), Const(16)), op=FixMul, results=List(b6055))
      WAStage(operands=List(b6055, CU.ctr(x5069(0))), op=FixAdd, results=List(x5046.writeAddr))
      RAStage(operands=List(CU.ctr(x5188(0)), Const(16)), op=FixMul, results=List(b6071))
      RAStage(operands=List(b6071, CU.ctr(x5198(0))), op=FixAdd, results=List(x5046.readAddr))
    }
    val x5046_dsp1_bank0 = MemoryPipeline(name="x5046_dsp1_bank0",parent="x5486") { implicit CU => 
      val b6073 = CU.temp(None)
      val b6055 = CU.temp(None)
      val x5075 = ScalarFIFO(size=1,name="x5075").wtPort(x5051_x5067_data_s)
      val x5049 = CounterChain.copy("x5077", "x5049")
      val x5237 = CounterChain.copy("x5247_0", "x5237")
      val x5227 = CounterChain.copy("x5264", "x5227")
      val x5069 = CounterChain.copy("x5076", "x5069")
      val x5046 = SRAM(size=256,name="x5046",banking = Strided(1,16)).wtPort(x5075.readPort).rdPort(x5046_x5046_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5049(0)), Const(16)), op=FixMul, results=List(b6055))
      WAStage(operands=List(b6055, CU.ctr(x5069(0))), op=FixAdd, results=List(x5046.writeAddr))
      RAStage(operands=List(CU.ctr(x5227(0)), Const(16)), op=FixMul, results=List(b6073))
      RAStage(operands=List(b6073, CU.ctr(x5237(0))), op=FixAdd, results=List(x5046.readAddr))
    }
    val x5047_dsp0_bank0 = MemoryPipeline(name="x5047_dsp0_bank0",parent="x5486") { implicit CU => 
      val b6061 = CU.temp(None)
      val b6087 = CU.temp(None)
      val x5105 = ScalarFIFO(size=1,name="x5105").wtPort(x5081_x5097_data_s)
      val x5375 = CounterChain.copy("x5385_0", "x5375")
      val x5099 = CounterChain.copy("x5106", "x5099")
      val x5365 = CounterChain.copy("x5402", "x5365")
      val x5079 = CounterChain.copy("x5107", "x5079")
      val x5047 = SRAM(size=256,name="x5047",banking = Strided(1,16)).wtPort(x5105.readPort).rdPort(x5047_x5047_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5079(0)), Const(16)), op=FixMul, results=List(b6061))
      WAStage(operands=List(b6061, CU.ctr(x5099(0))), op=FixAdd, results=List(x5047.writeAddr))
      RAStage(operands=List(CU.ctr(x5365(0)), Const(16)), op=FixMul, results=List(b6087))
      RAStage(operands=List(b6087, CU.ctr(x5375(0))), op=FixAdd, results=List(x5047.readAddr))
    }
    val x5047_dsp1_bank0 = MemoryPipeline(name="x5047_dsp1_bank0",parent="x5486") { implicit CU => 
      val b6089 = CU.temp(None)
      val b6061 = CU.temp(None)
      val x5105 = ScalarFIFO(size=1,name="x5105").wtPort(x5081_x5097_data_s)
      val x5099 = CounterChain.copy("x5106", "x5099")
      val x5404 = CounterChain.copy("x5441", "x5404")
      val x5414 = CounterChain.copy("x5424_0", "x5414")
      val x5079 = CounterChain.copy("x5107", "x5079")
      val x5047 = SRAM(size=256,name="x5047",banking = Strided(1,16)).wtPort(x5105.readPort).rdPort(x5047_x5047_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5079(0)), Const(16)), op=FixMul, results=List(b6061))
      WAStage(operands=List(b6061, CU.ctr(x5099(0))), op=FixAdd, results=List(x5047.writeAddr))
      RAStage(operands=List(CU.ctr(x5404(0)), Const(16)), op=FixMul, results=List(b6089))
      RAStage(operands=List(b6089, CU.ctr(x5414(0))), op=FixAdd, results=List(x5047.readAddr))
    }
    val x5077 = StreamController(name="x5077",parent=x5486) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5049 = CounterChain(name = "x5049", ctr3).iter(16)
    }
    val x5066_0 = Pipeline(name="x5066_0",parent=x5077) { implicit CU => 
      val x5052 = CU.temp(None)
      val x5057 = CU.temp(None)
      val x5056 = CU.temp(None)
      val x5054 = CU.temp(None)
      val x5059 = ScalarBuffer(name="x5059").wtPort(b_dram_da)
      val x5043 = CounterChain.copy("x5486", "x5043")
      val x5049 = CounterChain.copy("x5077", "x5049")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5066_unit = CounterChain(name = "x5066_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5043(0)), CU.ctr(x5049(0))), op=FixAdd, results=List(x5052))
      Stage(operands=List(x5052, Const(6)), op=FixSla, results=List(x5054))
      Stage(operands=List(x5054, CU.ctr(x5039(0))), op=FixAdd, results=List(x5056))
      Stage(operands=List(x5056, Const(3)), op=FixSla, results=List(x5057))
      Stage(operands=List(x5057, CU.load(x5059)), op=FixAdd, results=List(CU.scalarOut(x5050_b6051_x5065_b6053_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5050_b6052_x5065_b6054_s)))
    }
    val x5067 = MemoryController(name="x5067",parent=x5077,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5050_b6052 = ScalarFIFO(size=1,name="size").wtPort(x5050_b6052_x5065_b6054_s)
      val x5050_b6051 = ScalarFIFO(size=1,name="offset").wtPort(x5050_b6051_x5065_b6053_s)
      CU.newSout("data", x5051_x5067_data_s)
    }
    val x5076 = Pipeline(name="x5076",parent=x5077) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5069 = CounterChain(name = "x5069", ctr4).iter(1)
    }
    val x5107 = StreamController(name="x5107",parent=x5486) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5079 = CounterChain(name = "x5079", ctr5).iter(16)
    }
    val x5096_0 = Pipeline(name="x5096_0",parent=x5107) { implicit CU => 
      val x5082 = CU.temp(None)
      val x5084 = CU.temp(None)
      val x5086 = CU.temp(None)
      val x5087 = CU.temp(None)
      val x5089 = ScalarBuffer(name="x5089").wtPort(b_dram_da)
      val x5043 = CounterChain.copy("x5486", "x5043")
      val x5079 = CounterChain.copy("x5107", "x5079")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5096_unit = CounterChain(name = "x5096_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5043(0)), CU.ctr(x5079(0))), op=FixAdd, results=List(x5082))
      Stage(operands=List(x5082, Const(6)), op=FixSla, results=List(x5084))
      Stage(operands=List(x5084, CU.ctr(x5039(0))), op=FixAdd, results=List(x5086))
      Stage(operands=List(x5086, Const(3)), op=FixSla, results=List(x5087))
      Stage(operands=List(x5087, CU.load(x5089)), op=FixAdd, results=List(CU.scalarOut(x5080_b6057_x5095_b6059_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5080_b6058_x5095_b6060_s)))
    }
    val x5097 = MemoryController(name="x5097",parent=x5107,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5080_b6058 = ScalarFIFO(size=1,name="size").wtPort(x5080_b6058_x5095_b6060_s)
      val x5080_b6057 = ScalarFIFO(size=1,name="offset").wtPort(x5080_b6057_x5095_b6059_s)
      CU.newSout("data", x5081_x5097_data_s)
    }
    val x5106 = Pipeline(name="x5106",parent=x5107) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5099 = CounterChain(name = "x5099", ctr6).iter(1)
    }
    val x5285 = MetaPipeline(name="x5285",parent=x5486) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x5110 = CounterChain(name = "x5110", ctr7).iter(32)
    }
    val x5111_dsp0_bank0 = MemoryPipeline(name="x5111_dsp0_bank0",parent="x5285") { implicit CU => 
      val x5149 = ScalarFIFO(size=1,name="x5149").wtPort(x5123_x5140_data_s)
      val x5188 = CounterChain.copy("x5225", "x5188")
      val x5142 = CounterChain.copy("x5150", "x5142")
      val x5111 = SRAM(size=16,name="x5111",banking = NoBanking()).wtPort(x5149.readPort).rdPort(x5111_x5111_dsp0_bank0_s).rdAddr(x5188(0)).wtAddr(x5142(0))
    }
    val x5112_dsp0_bank0 = MemoryPipeline(name="x5112_dsp0_bank0",parent="x5285") { implicit CU => 
      val x5181 = ScalarFIFO(size=1,name="x5181").wtPort(x5155_x5172_data_s)
      val x5174 = CounterChain.copy("x5182", "x5174")
      val x5227 = CounterChain.copy("x5264", "x5227")
      val x5112 = SRAM(size=16,name="x5112",banking = NoBanking()).wtPort(x5181.readPort).rdPort(x5112_x5112_dsp0_bank0_s).rdAddr(x5227(0)).wtAddr(x5174(0))
    }
    val x5151 = StreamController(name="x5151",parent=x5285) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5121 = CounterChain(name = "x5121", ctr8).iter(1)
    }
    val x5139_0 = Pipeline(name="x5139_0",parent=x5151) { implicit CU => 
      val x5129 = CU.temp(None)
      val x5124 = CU.temp(None)
      val x5126 = CU.temp(None)
      val x5128 = CU.temp(None)
      val x5131 = ScalarBuffer(name="x5131").wtPort(a_dram_da)
      val x5121 = CounterChain.copy("x5151", "x5121")
      val x5139_unit = CounterChain(name = "x5139_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x5110 = CounterChain.copy("x5285", "x5110")
      val x5043 = CounterChain.copy("x5486", "x5043")
      Stage(operands=List(CU.ctr(x5110(0)), CU.ctr(x5121(0))), op=FixAdd, results=List(x5124))
      Stage(operands=List(x5124, Const(6)), op=FixSla, results=List(x5126))
      Stage(operands=List(x5126, CU.ctr(x5043(0))), op=FixAdd, results=List(x5128))
      Stage(operands=List(x5128, Const(3)), op=FixSla, results=List(x5129))
      Stage(operands=List(x5129, CU.load(x5131)), op=FixAdd, results=List(CU.scalarOut(x5122_b6063_x5138_b6065_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5122_b6064_x5138_b6066_s)))
    }
    val x5140 = MemoryController(name="x5140",parent=x5151,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5122_b6064 = ScalarFIFO(size=1,name="size").wtPort(x5122_b6064_x5138_b6066_s)
      val x5122_b6063 = ScalarFIFO(size=1,name="offset").wtPort(x5122_b6063_x5138_b6065_s)
      CU.newSout("data", x5123_x5140_data_s)
    }
    val x5150 = Pipeline(name="x5150",parent=x5151) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5142 = CounterChain(name = "x5142", ctr9).iter(16)
    }
    val x5183 = StreamController(name="x5183",parent=x5285) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5153 = CounterChain(name = "x5153", ctr10).iter(1)
    }
    val x5171_0 = Pipeline(name="x5171_0",parent=x5183) { implicit CU => 
      val x5161 = CU.temp(None)
      val x5160 = CU.temp(None)
      val x5156 = CU.temp(None)
      val x5158 = CU.temp(None)
      val x5163 = ScalarBuffer(name="x5163").wtPort(a_dram_da)
      val x5153 = CounterChain.copy("x5183", "x5153")
      val x5171_unit = CounterChain(name = "x5171_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x5110 = CounterChain.copy("x5285", "x5110")
      val x5043 = CounterChain.copy("x5486", "x5043")
      Stage(operands=List(CU.ctr(x5110(0)), CU.ctr(x5153(0))), op=FixAdd, results=List(x5156))
      Stage(operands=List(x5156, Const(6)), op=FixSla, results=List(x5158))
      Stage(operands=List(x5158, CU.ctr(x5043(0))), op=FixAdd, results=List(x5160))
      Stage(operands=List(x5160, Const(3)), op=FixSla, results=List(x5161))
      Stage(operands=List(x5161, CU.load(x5163)), op=FixAdd, results=List(CU.scalarOut(x5154_b6067_x5170_b6069_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5154_b6068_x5170_b6070_s)))
    }
    val x5172 = MemoryController(name="x5172",parent=x5183,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5154_b6067 = ScalarFIFO(size=1,name="offset").wtPort(x5154_b6067_x5170_b6069_s)
      val x5154_b6068 = ScalarFIFO(size=1,name="size").wtPort(x5154_b6068_x5170_b6070_s)
      CU.newSout("data", x5155_x5172_data_s)
    }
    val x5182 = Pipeline(name="x5182",parent=x5183) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5174 = CounterChain(name = "x5174", ctr11).iter(16)
    }
    val x5185_dsp0_bank0 = MemoryPipeline(name="x5185_dsp0_bank0",parent="x5225") { implicit CU => 
      val x5223 = ScalarFIFO(size=1,name="x5223").wtPort(x5185_x5223_s)
      val x5210 = CounterChain.copy("x5224_0", "x5210")
      val x5185 = SRAM(size=16,name="x5185",banking = Strided(1,16)).wtPort(x5223.readPort).rdPort(x5185_x5185_dsp0_bank0_s).rdAddr(x5210(0)).wtAddr(x5210(0))
    }
    val x5185_dsp1_bank0 = MemoryPipeline(name="x5185_dsp1_bank0",parent="x5225") { implicit CU => 
      val x5223 = ScalarFIFO(size=1,name="x5223").wtPort(x5185_x5223_s)
      val x5210 = CounterChain.copy("x5224_0", "x5210")
      val x5267 = CounterChain.copy("x5274", "x5267")
      val x5185 = SRAM(size=16,name="x5185",banking = Strided(1,16)).wtPort(x5223.readPort).rdPort(x5185_x5185_dsp1_bank0_s).rdAddr(x5267(0)).wtAddr(x5210(0))
    }
    val x5186_dsp0_bank0 = MemoryPipeline(name="x5186_dsp0_bank0",parent="x5264") { implicit CU => 
      val x5262 = ScalarFIFO(size=1,name="x5262").wtPort(x5186_x5262_s)
      val x5249 = CounterChain.copy("x5263_0", "x5249")
      val x5186 = SRAM(size=16,name="x5186",banking = Strided(1,16)).wtPort(x5262.readPort).rdPort(x5186_x5186_dsp0_bank0_s).rdAddr(x5249(0)).wtAddr(x5249(0))
    }
    val x5186_dsp1_bank0 = MemoryPipeline(name="x5186_dsp1_bank0",parent="x5264") { implicit CU => 
      val x5262 = ScalarFIFO(size=1,name="x5262").wtPort(x5186_x5262_s)
      val x5276 = CounterChain.copy("x5283", "x5276")
      val x5249 = CounterChain.copy("x5263_0", "x5249")
      val x5186 = SRAM(size=16,name="x5186",banking = Strided(1,16)).wtPort(x5262.readPort).rdPort(x5186_x5186_dsp1_bank0_s).rdAddr(x5276(0)).wtAddr(x5249(0))
    }
    val x5225 = MetaPipeline(name="x5225",parent=x5285) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5188 = CounterChain(name = "x5188", ctr12).iter(16)
    }
    val x5189_dsp0_bank0 = MemoryPipeline(name="x5189_dsp0_bank0",parent="x5225") { implicit CU => 
      val x5207 = ScalarFIFO(size=1,name="x5207").wtPort(x5189_x5207_s)
      val x5210 = CounterChain.copy("x5224_0", "x5210")
      val x5198 = CounterChain.copy("x5208_0", "x5198")
      val x5189 = SRAM(size=16,name="x5189",banking = Strided(1,16)).wtPort(x5207.readPort).rdPort(x5189_x5189_dsp0_bank0_s).rdAddr(x5210(0)).wtAddr(x5198(0))
    }
    val x5208_0 = Pipeline(name="x5208_0",parent=x5225) { implicit CU => 
      val x5203 = ScalarFIFO(size=1,name="x5203").wtPort(x5046_x5046_dsp0_bank0_s)
      val x5190 = ScalarBuffer(name="x5190").wtPort(x5111_x5111_dsp0_bank0_s)
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5198 = CounterChain(name = "x5198", ctr13).iter(1)
      Stage(operands=List(CU.load(x5203), CU.load(x5190)), op=FixMul, results=List(CU.scalarOut(x5189_x5207_s)))
    }
    val x5224_0 = Pipeline(name="x5224_0",parent=x5225) { implicit CU => 
      val x5214 = ScalarFIFO(size=1,name="x5214").wtPort(x5189_x5189_dsp0_bank0_s)
      val x5216 = ScalarFIFO(size=1,name="x5216").wtPort(x5185_x5185_dsp0_bank0_s)
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5210 = CounterChain(name = "x5210", ctr14).iter(1)
      Stage(operands=List(CU.load(x5214), CU.load(x5216)), op=FixAdd, results=List(CU.scalarOut(x5185_x5223_s)))
    }
    val x5264 = MetaPipeline(name="x5264",parent=x5285) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5227 = CounterChain(name = "x5227", ctr15).iter(16)
    }
    val x5228_dsp0_bank0 = MemoryPipeline(name="x5228_dsp0_bank0",parent="x5264") { implicit CU => 
      val x5246 = ScalarFIFO(size=1,name="x5246").wtPort(x5228_x5246_s)
      val x5237 = CounterChain.copy("x5247_0", "x5237")
      val x5249 = CounterChain.copy("x5263_0", "x5249")
      val x5228 = SRAM(size=16,name="x5228",banking = Strided(1,16)).wtPort(x5246.readPort).rdPort(x5228_x5228_dsp0_bank0_s).rdAddr(x5249(0)).wtAddr(x5237(0))
    }
    val x5247_0 = Pipeline(name="x5247_0",parent=x5264) { implicit CU => 
      val x5242 = ScalarFIFO(size=1,name="x5242").wtPort(x5046_x5046_dsp1_bank0_s)
      val x5229 = ScalarBuffer(name="x5229").wtPort(x5112_x5112_dsp0_bank0_s)
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5237 = CounterChain(name = "x5237", ctr16).iter(1)
      Stage(operands=List(CU.load(x5242), CU.load(x5229)), op=FixMul, results=List(CU.scalarOut(x5228_x5246_s)))
    }
    val x5263_0 = Pipeline(name="x5263_0",parent=x5264) { implicit CU => 
      val x5253 = ScalarFIFO(size=1,name="x5253").wtPort(x5228_x5228_dsp0_bank0_s)
      val x5255 = ScalarFIFO(size=1,name="x5255").wtPort(x5186_x5186_dsp0_bank0_s)
      val ctr17 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5249 = CounterChain(name = "x5249", ctr17).iter(1)
      Stage(operands=List(CU.load(x5253), CU.load(x5255)), op=FixAdd, results=List(CU.scalarOut(x5186_x5262_s)))
    }
    val x5274 = Pipeline(name="x5274",parent=x5285) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5267 = CounterChain(name = "x5267", ctr18).iter(16)
    }
    val x5283 = Pipeline(name="x5283",parent=x5285) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5276 = CounterChain(name = "x5276", ctr19).iter(16)
    }
    val x5462 = MetaPipeline(name="x5462",parent=x5486) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x5287 = CounterChain(name = "x5287", ctr20).iter(32)
    }
    val x5288_dsp0_bank0 = MemoryPipeline(name="x5288_dsp0_bank0",parent="x5462") { implicit CU => 
      val x5326 = ScalarFIFO(size=1,name="x5326").wtPort(x5300_x5317_data_s)
      val x5319 = CounterChain.copy("x5327", "x5319")
      val x5365 = CounterChain.copy("x5402", "x5365")
      val x5288 = SRAM(size=16,name="x5288",banking = NoBanking()).wtPort(x5326.readPort).rdPort(x5288_x5288_dsp0_bank0_s).rdAddr(x5365(0)).wtAddr(x5319(0))
    }
    val x5289_dsp0_bank0 = MemoryPipeline(name="x5289_dsp0_bank0",parent="x5462") { implicit CU => 
      val x5358 = ScalarFIFO(size=1,name="x5358").wtPort(x5332_x5349_data_s)
      val x5351 = CounterChain.copy("x5359", "x5351")
      val x5404 = CounterChain.copy("x5441", "x5404")
      val x5289 = SRAM(size=16,name="x5289",banking = NoBanking()).wtPort(x5358.readPort).rdPort(x5289_x5289_dsp0_bank0_s).rdAddr(x5404(0)).wtAddr(x5351(0))
    }
    val x5328 = StreamController(name="x5328",parent=x5462) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5298 = CounterChain(name = "x5298", ctr21).iter(1)
    }
    val x5316_0 = Pipeline(name="x5316_0",parent=x5328) { implicit CU => 
      val x5306 = CU.temp(None)
      val x5305 = CU.temp(None)
      val x5303 = CU.temp(None)
      val x5301 = CU.temp(None)
      val x5308 = ScalarBuffer(name="x5308").wtPort(a_dram_da)
      val x5316_unit = CounterChain(name = "x5316_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x5298 = CounterChain.copy("x5328", "x5298")
      val x5043 = CounterChain.copy("x5486", "x5043")
      val x5287 = CounterChain.copy("x5462", "x5287")
      Stage(operands=List(CU.ctr(x5287(0)), CU.ctr(x5298(0))), op=FixAdd, results=List(x5301))
      Stage(operands=List(x5301, Const(6)), op=FixSla, results=List(x5303))
      Stage(operands=List(x5303, CU.ctr(x5043(0))), op=FixAdd, results=List(x5305))
      Stage(operands=List(x5305, Const(3)), op=FixSla, results=List(x5306))
      Stage(operands=List(x5306, CU.load(x5308)), op=FixAdd, results=List(CU.scalarOut(x5299_b6079_x5315_b6081_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5299_b6080_x5315_b6082_s)))
    }
    val x5317 = MemoryController(name="x5317",parent=x5328,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5299_b6079 = ScalarFIFO(size=1,name="offset").wtPort(x5299_b6079_x5315_b6081_s)
      val x5299_b6080 = ScalarFIFO(size=1,name="size").wtPort(x5299_b6080_x5315_b6082_s)
      CU.newSout("data", x5300_x5317_data_s)
    }
    val x5327 = Pipeline(name="x5327",parent=x5328) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5319 = CounterChain(name = "x5319", ctr22).iter(16)
    }
    val x5360 = StreamController(name="x5360",parent=x5462) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5330 = CounterChain(name = "x5330", ctr23).iter(1)
    }
    val x5348_0 = Pipeline(name="x5348_0",parent=x5360) { implicit CU => 
      val x5337 = CU.temp(None)
      val x5335 = CU.temp(None)
      val x5338 = CU.temp(None)
      val x5333 = CU.temp(None)
      val x5340 = ScalarBuffer(name="x5340").wtPort(a_dram_da)
      val x5330 = CounterChain.copy("x5360", "x5330")
      val x5043 = CounterChain.copy("x5486", "x5043")
      val x5287 = CounterChain.copy("x5462", "x5287")
      val x5348_unit = CounterChain(name = "x5348_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5287(0)), CU.ctr(x5330(0))), op=FixAdd, results=List(x5333))
      Stage(operands=List(x5333, Const(6)), op=FixSla, results=List(x5335))
      Stage(operands=List(x5335, CU.ctr(x5043(0))), op=FixAdd, results=List(x5337))
      Stage(operands=List(x5337, Const(3)), op=FixSla, results=List(x5338))
      Stage(operands=List(x5338, CU.load(x5340)), op=FixAdd, results=List(CU.scalarOut(x5331_b6083_x5347_b6085_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5331_b6084_x5347_b6086_s)))
    }
    val x5349 = MemoryController(name="x5349",parent=x5360,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5331_b6084 = ScalarFIFO(size=1,name="size").wtPort(x5331_b6084_x5347_b6086_s)
      val x5331_b6083 = ScalarFIFO(size=1,name="offset").wtPort(x5331_b6083_x5347_b6085_s)
      CU.newSout("data", x5332_x5349_data_s)
    }
    val x5359 = Pipeline(name="x5359",parent=x5360) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5351 = CounterChain(name = "x5351", ctr24).iter(16)
    }
    val x5362_dsp0_bank0 = MemoryPipeline(name="x5362_dsp0_bank0",parent="x5402") { implicit CU => 
      val x5400 = ScalarFIFO(size=1,name="x5400").wtPort(x5362_x5400_s)
      val x5387 = CounterChain.copy("x5401_0", "x5387")
      val x5362 = SRAM(size=16,name="x5362",banking = Strided(1,16)).wtPort(x5400.readPort).rdPort(x5362_x5362_dsp0_bank0_s).rdAddr(x5387(0)).wtAddr(x5387(0))
    }
    val x5362_dsp1_bank0 = MemoryPipeline(name="x5362_dsp1_bank0",parent="x5402") { implicit CU => 
      val x5400 = ScalarFIFO(size=1,name="x5400").wtPort(x5362_x5400_s)
      val x5444 = CounterChain.copy("x5451", "x5444")
      val x5387 = CounterChain.copy("x5401_0", "x5387")
      val x5362 = SRAM(size=16,name="x5362",banking = Strided(1,16)).wtPort(x5400.readPort).rdPort(x5362_x5362_dsp1_bank0_s).rdAddr(x5444(0)).wtAddr(x5387(0))
    }
    val x5363_dsp0_bank0 = MemoryPipeline(name="x5363_dsp0_bank0",parent="x5441") { implicit CU => 
      val x5439 = ScalarFIFO(size=1,name="x5439").wtPort(x5363_x5439_s)
      val x5426 = CounterChain.copy("x5440_0", "x5426")
      val x5363 = SRAM(size=16,name="x5363",banking = Strided(1,16)).wtPort(x5439.readPort).rdPort(x5363_x5363_dsp0_bank0_s).rdAddr(x5426(0)).wtAddr(x5426(0))
    }
    val x5363_dsp1_bank0 = MemoryPipeline(name="x5363_dsp1_bank0",parent="x5441") { implicit CU => 
      val x5439 = ScalarFIFO(size=1,name="x5439").wtPort(x5363_x5439_s)
      val x5426 = CounterChain.copy("x5440_0", "x5426")
      val x5453 = CounterChain.copy("x5460", "x5453")
      val x5363 = SRAM(size=16,name="x5363",banking = Strided(1,16)).wtPort(x5439.readPort).rdPort(x5363_x5363_dsp1_bank0_s).rdAddr(x5453(0)).wtAddr(x5426(0))
    }
    val x5402 = MetaPipeline(name="x5402",parent=x5462) { implicit CU => 
      val ctr25 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5365 = CounterChain(name = "x5365", ctr25).iter(16)
    }
    val x5366_dsp0_bank0 = MemoryPipeline(name="x5366_dsp0_bank0",parent="x5402") { implicit CU => 
      val x5384 = ScalarFIFO(size=1,name="x5384").wtPort(x5366_x5384_s)
      val x5387 = CounterChain.copy("x5401_0", "x5387")
      val x5375 = CounterChain.copy("x5385_0", "x5375")
      val x5366 = SRAM(size=16,name="x5366",banking = Strided(1,16)).wtPort(x5384.readPort).rdPort(x5366_x5366_dsp0_bank0_s).rdAddr(x5387(0)).wtAddr(x5375(0))
    }
    val x5385_0 = Pipeline(name="x5385_0",parent=x5402) { implicit CU => 
      val x5367 = ScalarBuffer(name="x5367").wtPort(x5288_x5288_dsp0_bank0_s)
      val x5380 = ScalarFIFO(size=1,name="x5380").wtPort(x5047_x5047_dsp0_bank0_s)
      val ctr26 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5375 = CounterChain(name = "x5375", ctr26).iter(1)
      Stage(operands=List(CU.load(x5380), CU.load(x5367)), op=FixMul, results=List(CU.scalarOut(x5366_x5384_s)))
    }
    val x5401_0 = Pipeline(name="x5401_0",parent=x5402) { implicit CU => 
      val x5391 = ScalarFIFO(size=1,name="x5391").wtPort(x5366_x5366_dsp0_bank0_s)
      val x5393 = ScalarFIFO(size=1,name="x5393").wtPort(x5362_x5362_dsp0_bank0_s)
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5387 = CounterChain(name = "x5387", ctr27).iter(1)
      Stage(operands=List(CU.load(x5391), CU.load(x5393)), op=FixAdd, results=List(CU.scalarOut(x5362_x5400_s)))
    }
    val x5441 = MetaPipeline(name="x5441",parent=x5462) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5404 = CounterChain(name = "x5404", ctr28).iter(16)
    }
    val x5405_dsp0_bank0 = MemoryPipeline(name="x5405_dsp0_bank0",parent="x5441") { implicit CU => 
      val x5423 = ScalarFIFO(size=1,name="x5423").wtPort(x5405_x5423_s)
      val x5426 = CounterChain.copy("x5440_0", "x5426")
      val x5414 = CounterChain.copy("x5424_0", "x5414")
      val x5405 = SRAM(size=16,name="x5405",banking = Strided(1,16)).wtPort(x5423.readPort).rdPort(x5405_x5405_dsp0_bank0_s).rdAddr(x5426(0)).wtAddr(x5414(0))
    }
    val x5424_0 = Pipeline(name="x5424_0",parent=x5441) { implicit CU => 
      val x5406 = ScalarBuffer(name="x5406").wtPort(x5289_x5289_dsp0_bank0_s)
      val x5419 = ScalarFIFO(size=1,name="x5419").wtPort(x5047_x5047_dsp1_bank0_s)
      val ctr29 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5414 = CounterChain(name = "x5414", ctr29).iter(1)
      Stage(operands=List(CU.load(x5419), CU.load(x5406)), op=FixMul, results=List(CU.scalarOut(x5405_x5423_s)))
    }
    val x5440_0 = Pipeline(name="x5440_0",parent=x5441) { implicit CU => 
      val x5430 = ScalarFIFO(size=1,name="x5430").wtPort(x5405_x5405_dsp0_bank0_s)
      val x5432 = ScalarFIFO(size=1,name="x5432").wtPort(x5363_x5363_dsp0_bank0_s)
      val ctr30 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5426 = CounterChain(name = "x5426", ctr30).iter(1)
      Stage(operands=List(CU.load(x5430), CU.load(x5432)), op=FixAdd, results=List(CU.scalarOut(x5363_x5439_s)))
    }
    val x5451 = Pipeline(name="x5451",parent=x5462) { implicit CU => 
      val ctr31 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5444 = CounterChain(name = "x5444", ctr31).iter(16)
    }
    val x5460 = Pipeline(name="x5460",parent=x5462) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5453 = CounterChain(name = "x5453", ctr32).iter(16)
    }
    val x5485_0 = Pipeline(name="x5485_0",parent=x5486) { implicit CU => 
      val x5479 = CU.temp(None)
      val x5469 = ScalarFIFO(size=1,name="x5469").wtPort(x5044_x5044_dsp0_bank0_s).wtPort(x5044_x5044_dsp0_bank1_s)
      val x5471 = ScalarFIFO(size=1,name="x5471").wtPort(x5045_x5045_dsp0_bank0_s).wtPort(x5045_x5045_dsp0_bank1_s)
      val x5473 = ScalarFIFO(size=1,name="x5473").wtPort(x5040_x5040_dsp0_bank0_s)
      val ctr33 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr34 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5466 = CounterChain(name = "x5466", ctr33, ctr34).iter(64)
      Stage(operands=List(CU.load(x5469), CU.load(x5471)), op=FixAdd, results=List(x5479))
      Stage(operands=List(x5479, CU.load(x5473)), op=FixAdd, results=List(CU.scalarOut(x5040_x5484_s)))
    }
    val x5931 = MetaPipeline(name="x5931",parent=x6003) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x5488 = CounterChain(name = "x5488", ctr35).iter(2)
    }
    val x5489_dsp0_bank0 = MemoryPipeline(name="x5489_dsp0_bank0",parent="x5931") { implicit CU => 
      val b6127 = CU.temp(None)
      val b6147 = CU.temp(None)
      val x5718 = ScalarFIFO(size=1,name="x5718").wtPort(x5630_x5630_dsp1_bank0_s)
      val x5555 = CounterChain.copy("x5730", "x5555")
      val x5712 = CounterChain.copy("x5719", "x5712")
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5489 = SRAM(size=1024,name="x5489",banking = Strided(1,16)).wtPort(x5718.readPort).rdPort(x5489_x5489_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5555(0)), Const(16)), op=FixMul, results=List(b6127))
      WAStage(operands=List(b6127, CU.ctr(x5712(0))), op=FixAdd, results=List(x5489.writeAddr))
      RAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6147))
      RAStage(operands=List(b6147, CU.ctr(x5911(1))), op=FixAdd, results=List(x5489.readAddr))
    }
    val x5489_dsp0_bank1 = MemoryPipeline(name="x5489_dsp0_bank1",parent="x5931") { implicit CU => 
      val b6129 = CU.temp(None)
      val b6147 = CU.temp(None)
      val x5727 = ScalarFIFO(size=1,name="x5727").wtPort(x5631_x5631_dsp1_bank0_s)
      val x5555 = CounterChain.copy("x5730", "x5555")
      val x5721 = CounterChain.copy("x5728", "x5721")
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5489 = SRAM(size=1024,name="x5489",banking = Strided(1,16)).wtPort(x5727.readPort).rdPort(x5489_x5489_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x5555(0)), Const(16)), op=FixMul, results=List(b6129))
      WAStage(operands=List(b6129, CU.ctr(x5721(0))), op=FixAdd, results=List(x5489.writeAddr))
      RAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6147))
      RAStage(operands=List(b6147, CU.ctr(x5911(1))), op=FixAdd, results=List(x5489.readAddr))
    }
    val x5490_dsp0_bank0 = MemoryPipeline(name="x5490_dsp0_bank0",parent="x5931") { implicit CU => 
      val b6149 = CU.temp(None)
      val b6143 = CU.temp(None)
      val x5895 = ScalarFIFO(size=1,name="x5895").wtPort(x5807_x5807_dsp1_bank0_s)
      val x5732 = CounterChain.copy("x5907", "x5732")
      val x5889 = CounterChain.copy("x5896", "x5889")
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5490 = SRAM(size=1024,name="x5490",banking = Strided(1,16)).wtPort(x5895.readPort).rdPort(x5490_x5490_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5732(0)), Const(16)), op=FixMul, results=List(b6143))
      WAStage(operands=List(b6143, CU.ctr(x5889(0))), op=FixAdd, results=List(x5490.writeAddr))
      RAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6149))
      RAStage(operands=List(b6149, CU.ctr(x5911(1))), op=FixAdd, results=List(x5490.readAddr))
    }
    val x5490_dsp0_bank1 = MemoryPipeline(name="x5490_dsp0_bank1",parent="x5931") { implicit CU => 
      val b6149 = CU.temp(None)
      val b6145 = CU.temp(None)
      val x5904 = ScalarFIFO(size=1,name="x5904").wtPort(x5808_x5808_dsp1_bank0_s)
      val x5732 = CounterChain.copy("x5907", "x5732")
      val x5911 = CounterChain.copy("x5930_0", "x5911")
      val x5898 = CounterChain.copy("x5905", "x5898")
      val x5490 = SRAM(size=1024,name="x5490",banking = Strided(1,16)).wtPort(x5904.readPort).rdPort(x5490_x5490_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x5732(0)), Const(16)), op=FixMul, results=List(b6145))
      WAStage(operands=List(b6145, CU.ctr(x5898(0))), op=FixAdd, results=List(x5490.writeAddr))
      RAStage(operands=List(CU.ctr(x5911(0)), Const(16)), op=FixMul, results=List(b6149))
      RAStage(operands=List(b6149, CU.ctr(x5911(1))), op=FixAdd, results=List(x5490.readAddr))
    }
    val x5491_dsp0_bank0 = MemoryPipeline(name="x5491_dsp0_bank0",parent="x5931") { implicit CU => 
      val b6123 = CU.temp(None)
      val b6107 = CU.temp(None)
      val x5520 = ScalarFIFO(size=1,name="x5520").wtPort(x5496_x5512_data_s)
      val x5633 = CounterChain.copy("x5670", "x5633")
      val x5494 = CounterChain.copy("x5522", "x5494")
      val x5643 = CounterChain.copy("x5653_0", "x5643")
      val x5514 = CounterChain.copy("x5521", "x5514")
      val x5491 = SRAM(size=256,name="x5491",banking = Strided(1,16)).wtPort(x5520.readPort).rdPort(x5491_x5491_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5494(0)), Const(16)), op=FixMul, results=List(b6107))
      WAStage(operands=List(b6107, CU.ctr(x5514(0))), op=FixAdd, results=List(x5491.writeAddr))
      RAStage(operands=List(CU.ctr(x5633(0)), Const(16)), op=FixMul, results=List(b6123))
      RAStage(operands=List(b6123, CU.ctr(x5643(0))), op=FixAdd, results=List(x5491.readAddr))
    }
    val x5491_dsp1_bank0 = MemoryPipeline(name="x5491_dsp1_bank0",parent="x5931") { implicit CU => 
      val b6125 = CU.temp(None)
      val b6107 = CU.temp(None)
      val x5520 = ScalarFIFO(size=1,name="x5520").wtPort(x5496_x5512_data_s)
      val x5494 = CounterChain.copy("x5522", "x5494")
      val x5682 = CounterChain.copy("x5692_0", "x5682")
      val x5514 = CounterChain.copy("x5521", "x5514")
      val x5672 = CounterChain.copy("x5709", "x5672")
      val x5491 = SRAM(size=256,name="x5491",banking = Strided(1,16)).wtPort(x5520.readPort).rdPort(x5491_x5491_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5494(0)), Const(16)), op=FixMul, results=List(b6107))
      WAStage(operands=List(b6107, CU.ctr(x5514(0))), op=FixAdd, results=List(x5491.writeAddr))
      RAStage(operands=List(CU.ctr(x5672(0)), Const(16)), op=FixMul, results=List(b6125))
      RAStage(operands=List(b6125, CU.ctr(x5682(0))), op=FixAdd, results=List(x5491.readAddr))
    }
    val x5492_dsp0_bank0 = MemoryPipeline(name="x5492_dsp0_bank0",parent="x5931") { implicit CU => 
      val b6113 = CU.temp(None)
      val b6139 = CU.temp(None)
      val x5550 = ScalarFIFO(size=1,name="x5550").wtPort(x5526_x5542_data_s)
      val x5820 = CounterChain.copy("x5830_0", "x5820")
      val x5544 = CounterChain.copy("x5551", "x5544")
      val x5810 = CounterChain.copy("x5847", "x5810")
      val x5524 = CounterChain.copy("x5552", "x5524")
      val x5492 = SRAM(size=256,name="x5492",banking = Strided(1,16)).wtPort(x5550.readPort).rdPort(x5492_x5492_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x5524(0)), Const(16)), op=FixMul, results=List(b6113))
      WAStage(operands=List(b6113, CU.ctr(x5544(0))), op=FixAdd, results=List(x5492.writeAddr))
      RAStage(operands=List(CU.ctr(x5810(0)), Const(16)), op=FixMul, results=List(b6139))
      RAStage(operands=List(b6139, CU.ctr(x5820(0))), op=FixAdd, results=List(x5492.readAddr))
    }
    val x5492_dsp1_bank0 = MemoryPipeline(name="x5492_dsp1_bank0",parent="x5931") { implicit CU => 
      val b6141 = CU.temp(None)
      val b6113 = CU.temp(None)
      val x5550 = ScalarFIFO(size=1,name="x5550").wtPort(x5526_x5542_data_s)
      val x5544 = CounterChain.copy("x5551", "x5544")
      val x5849 = CounterChain.copy("x5886", "x5849")
      val x5524 = CounterChain.copy("x5552", "x5524")
      val x5859 = CounterChain.copy("x5869_0", "x5859")
      val x5492 = SRAM(size=256,name="x5492",banking = Strided(1,16)).wtPort(x5550.readPort).rdPort(x5492_x5492_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x5524(0)), Const(16)), op=FixMul, results=List(b6113))
      WAStage(operands=List(b6113, CU.ctr(x5544(0))), op=FixAdd, results=List(x5492.writeAddr))
      RAStage(operands=List(CU.ctr(x5849(0)), Const(16)), op=FixMul, results=List(b6141))
      RAStage(operands=List(b6141, CU.ctr(x5859(0))), op=FixAdd, results=List(x5492.readAddr))
    }
    val x5522 = StreamController(name="x5522",parent=x5931) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5494 = CounterChain(name = "x5494", ctr36).iter(16)
    }
    val x5511_0 = Pipeline(name="x5511_0",parent=x5522) { implicit CU => 
      val x5499 = CU.temp(None)
      val x5501 = CU.temp(None)
      val x5497 = CU.temp(None)
      val x5502 = CU.temp(None)
      val x5504 = ScalarBuffer(name="x5504").wtPort(b_dram_da)
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5494 = CounterChain.copy("x5522", "x5494")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5511_unit = CounterChain(name = "x5511_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5488(0)), CU.ctr(x5494(0))), op=FixAdd, results=List(x5497))
      Stage(operands=List(x5497, Const(6)), op=FixSla, results=List(x5499))
      Stage(operands=List(x5499, CU.ctr(x5039(0))), op=FixAdd, results=List(x5501))
      Stage(operands=List(x5501, Const(3)), op=FixSla, results=List(x5502))
      Stage(operands=List(x5502, CU.load(x5504)), op=FixAdd, results=List(CU.scalarOut(x5495_b6103_x5510_b6105_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5495_b6104_x5510_b6106_s)))
    }
    val x5512 = MemoryController(name="x5512",parent=x5522,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5495_b6103 = ScalarFIFO(size=1,name="offset").wtPort(x5495_b6103_x5510_b6105_s)
      val x5495_b6104 = ScalarFIFO(size=1,name="size").wtPort(x5495_b6104_x5510_b6106_s)
      CU.newSout("data", x5496_x5512_data_s)
    }
    val x5521 = Pipeline(name="x5521",parent=x5522) { implicit CU => 
      val ctr37 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5514 = CounterChain(name = "x5514", ctr37).iter(1)
    }
    val x5552 = StreamController(name="x5552",parent=x5931) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5524 = CounterChain(name = "x5524", ctr38).iter(16)
    }
    val x5541_0 = Pipeline(name="x5541_0",parent=x5552) { implicit CU => 
      val x5531 = CU.temp(None)
      val x5529 = CU.temp(None)
      val x5527 = CU.temp(None)
      val x5532 = CU.temp(None)
      val x5534 = ScalarBuffer(name="x5534").wtPort(b_dram_da)
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5524 = CounterChain.copy("x5552", "x5524")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5541_unit = CounterChain(name = "x5541_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5488(0)), CU.ctr(x5524(0))), op=FixAdd, results=List(x5527))
      Stage(operands=List(x5527, Const(6)), op=FixSla, results=List(x5529))
      Stage(operands=List(x5529, CU.ctr(x5039(0))), op=FixAdd, results=List(x5531))
      Stage(operands=List(x5531, Const(3)), op=FixSla, results=List(x5532))
      Stage(operands=List(x5532, CU.load(x5534)), op=FixAdd, results=List(CU.scalarOut(x5525_b6109_x5540_b6111_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5525_b6110_x5540_b6112_s)))
    }
    val x5542 = MemoryController(name="x5542",parent=x5552,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5525_b6109 = ScalarFIFO(size=1,name="offset").wtPort(x5525_b6109_x5540_b6111_s)
      val x5525_b6110 = ScalarFIFO(size=1,name="size").wtPort(x5525_b6110_x5540_b6112_s)
      CU.newSout("data", x5526_x5542_data_s)
    }
    val x5551 = Pipeline(name="x5551",parent=x5552) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5544 = CounterChain(name = "x5544", ctr39).iter(1)
    }
    val x5730 = MetaPipeline(name="x5730",parent=x5931) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x5555 = CounterChain(name = "x5555", ctr40).iter(32)
    }
    val x5556_dsp0_bank0 = MemoryPipeline(name="x5556_dsp0_bank0",parent="x5730") { implicit CU => 
      val x5594 = ScalarFIFO(size=1,name="x5594").wtPort(x5568_x5585_data_s)
      val x5633 = CounterChain.copy("x5670", "x5633")
      val x5587 = CounterChain.copy("x5595", "x5587")
      val x5556 = SRAM(size=16,name="x5556",banking = NoBanking()).wtPort(x5594.readPort).rdPort(x5556_x5556_dsp0_bank0_s).rdAddr(x5633(0)).wtAddr(x5587(0))
    }
    val x5557_dsp0_bank0 = MemoryPipeline(name="x5557_dsp0_bank0",parent="x5730") { implicit CU => 
      val x5626 = ScalarFIFO(size=1,name="x5626").wtPort(x5600_x5617_data_s)
      val x5619 = CounterChain.copy("x5627", "x5619")
      val x5672 = CounterChain.copy("x5709", "x5672")
      val x5557 = SRAM(size=16,name="x5557",banking = NoBanking()).wtPort(x5626.readPort).rdPort(x5557_x5557_dsp0_bank0_s).rdAddr(x5672(0)).wtAddr(x5619(0))
    }
    val x5596 = StreamController(name="x5596",parent=x5730) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5566 = CounterChain(name = "x5566", ctr41).iter(1)
    }
    val x5584_0 = Pipeline(name="x5584_0",parent=x5596) { implicit CU => 
      val x5569 = CU.temp(None)
      val x5571 = CU.temp(None)
      val x5574 = CU.temp(None)
      val x5573 = CU.temp(None)
      val x5576 = ScalarBuffer(name="x5576").wtPort(a_dram_da)
      val x5566 = CounterChain.copy("x5596", "x5566")
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5555 = CounterChain.copy("x5730", "x5555")
      val x5584_unit = CounterChain(name = "x5584_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5555(0)), CU.ctr(x5566(0))), op=FixAdd, results=List(x5569))
      Stage(operands=List(x5569, Const(6)), op=FixSla, results=List(x5571))
      Stage(operands=List(x5571, CU.ctr(x5488(0))), op=FixAdd, results=List(x5573))
      Stage(operands=List(x5573, Const(3)), op=FixSla, results=List(x5574))
      Stage(operands=List(x5574, CU.load(x5576)), op=FixAdd, results=List(CU.scalarOut(x5567_b6115_x5583_b6117_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5567_b6116_x5583_b6118_s)))
    }
    val x5585 = MemoryController(name="x5585",parent=x5596,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5567_b6115 = ScalarFIFO(size=1,name="offset").wtPort(x5567_b6115_x5583_b6117_s)
      val x5567_b6116 = ScalarFIFO(size=1,name="size").wtPort(x5567_b6116_x5583_b6118_s)
      CU.newSout("data", x5568_x5585_data_s)
    }
    val x5595 = Pipeline(name="x5595",parent=x5596) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5587 = CounterChain(name = "x5587", ctr42).iter(16)
    }
    val x5628 = StreamController(name="x5628",parent=x5730) { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5598 = CounterChain(name = "x5598", ctr43).iter(1)
    }
    val x5616_0 = Pipeline(name="x5616_0",parent=x5628) { implicit CU => 
      val x5603 = CU.temp(None)
      val x5606 = CU.temp(None)
      val x5605 = CU.temp(None)
      val x5601 = CU.temp(None)
      val x5608 = ScalarBuffer(name="x5608").wtPort(a_dram_da)
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5555 = CounterChain.copy("x5730", "x5555")
      val x5616_unit = CounterChain(name = "x5616_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x5598 = CounterChain.copy("x5628", "x5598")
      Stage(operands=List(CU.ctr(x5555(0)), CU.ctr(x5598(0))), op=FixAdd, results=List(x5601))
      Stage(operands=List(x5601, Const(6)), op=FixSla, results=List(x5603))
      Stage(operands=List(x5603, CU.ctr(x5488(0))), op=FixAdd, results=List(x5605))
      Stage(operands=List(x5605, Const(3)), op=FixSla, results=List(x5606))
      Stage(operands=List(x5606, CU.load(x5608)), op=FixAdd, results=List(CU.scalarOut(x5599_b6119_x5615_b6121_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5599_b6120_x5615_b6122_s)))
    }
    val x5617 = MemoryController(name="x5617",parent=x5628,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5599_b6120 = ScalarFIFO(size=1,name="size").wtPort(x5599_b6120_x5615_b6122_s)
      val x5599_b6119 = ScalarFIFO(size=1,name="offset").wtPort(x5599_b6119_x5615_b6121_s)
      CU.newSout("data", x5600_x5617_data_s)
    }
    val x5627 = Pipeline(name="x5627",parent=x5628) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5619 = CounterChain(name = "x5619", ctr44).iter(16)
    }
    val x5630_dsp0_bank0 = MemoryPipeline(name="x5630_dsp0_bank0",parent="x5670") { implicit CU => 
      val x5668 = ScalarFIFO(size=1,name="x5668").wtPort(x5630_x5668_s)
      val x5655 = CounterChain.copy("x5669_0", "x5655")
      val x5630 = SRAM(size=16,name="x5630",banking = Strided(1,16)).wtPort(x5668.readPort).rdPort(x5630_x5630_dsp0_bank0_s).rdAddr(x5655(0)).wtAddr(x5655(0))
    }
    val x5630_dsp1_bank0 = MemoryPipeline(name="x5630_dsp1_bank0",parent="x5670") { implicit CU => 
      val x5668 = ScalarFIFO(size=1,name="x5668").wtPort(x5630_x5668_s)
      val x5655 = CounterChain.copy("x5669_0", "x5655")
      val x5712 = CounterChain.copy("x5719", "x5712")
      val x5630 = SRAM(size=16,name="x5630",banking = Strided(1,16)).wtPort(x5668.readPort).rdPort(x5630_x5630_dsp1_bank0_s).rdAddr(x5712(0)).wtAddr(x5655(0))
    }
    val x5631_dsp0_bank0 = MemoryPipeline(name="x5631_dsp0_bank0",parent="x5709") { implicit CU => 
      val x5707 = ScalarFIFO(size=1,name="x5707").wtPort(x5631_x5707_s)
      val x5694 = CounterChain.copy("x5708_0", "x5694")
      val x5631 = SRAM(size=16,name="x5631",banking = Strided(1,16)).wtPort(x5707.readPort).rdPort(x5631_x5631_dsp0_bank0_s).rdAddr(x5694(0)).wtAddr(x5694(0))
    }
    val x5631_dsp1_bank0 = MemoryPipeline(name="x5631_dsp1_bank0",parent="x5709") { implicit CU => 
      val x5707 = ScalarFIFO(size=1,name="x5707").wtPort(x5631_x5707_s)
      val x5694 = CounterChain.copy("x5708_0", "x5694")
      val x5721 = CounterChain.copy("x5728", "x5721")
      val x5631 = SRAM(size=16,name="x5631",banking = Strided(1,16)).wtPort(x5707.readPort).rdPort(x5631_x5631_dsp1_bank0_s).rdAddr(x5721(0)).wtAddr(x5694(0))
    }
    val x5670 = MetaPipeline(name="x5670",parent=x5730) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5633 = CounterChain(name = "x5633", ctr45).iter(16)
    }
    val x5634_dsp0_bank0 = MemoryPipeline(name="x5634_dsp0_bank0",parent="x5670") { implicit CU => 
      val x5652 = ScalarFIFO(size=1,name="x5652").wtPort(x5634_x5652_s)
      val x5655 = CounterChain.copy("x5669_0", "x5655")
      val x5643 = CounterChain.copy("x5653_0", "x5643")
      val x5634 = SRAM(size=16,name="x5634",banking = Strided(1,16)).wtPort(x5652.readPort).rdPort(x5634_x5634_dsp0_bank0_s).rdAddr(x5655(0)).wtAddr(x5643(0))
    }
    val x5653_0 = Pipeline(name="x5653_0",parent=x5670) { implicit CU => 
      val x5648 = ScalarFIFO(size=1,name="x5648").wtPort(x5491_x5491_dsp0_bank0_s)
      val x5635 = ScalarBuffer(name="x5635").wtPort(x5556_x5556_dsp0_bank0_s)
      val ctr46 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5643 = CounterChain(name = "x5643", ctr46).iter(1)
      Stage(operands=List(CU.load(x5648), CU.load(x5635)), op=FixMul, results=List(CU.scalarOut(x5634_x5652_s)))
    }
    val x5669_0 = Pipeline(name="x5669_0",parent=x5670) { implicit CU => 
      val x5659 = ScalarFIFO(size=1,name="x5659").wtPort(x5634_x5634_dsp0_bank0_s)
      val x5661 = ScalarFIFO(size=1,name="x5661").wtPort(x5630_x5630_dsp0_bank0_s)
      val ctr47 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5655 = CounterChain(name = "x5655", ctr47).iter(1)
      Stage(operands=List(CU.load(x5659), CU.load(x5661)), op=FixAdd, results=List(CU.scalarOut(x5630_x5668_s)))
    }
    val x5709 = MetaPipeline(name="x5709",parent=x5730) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5672 = CounterChain(name = "x5672", ctr48).iter(16)
    }
    val x5673_dsp0_bank0 = MemoryPipeline(name="x5673_dsp0_bank0",parent="x5709") { implicit CU => 
      val x5691 = ScalarFIFO(size=1,name="x5691").wtPort(x5673_x5691_s)
      val x5694 = CounterChain.copy("x5708_0", "x5694")
      val x5682 = CounterChain.copy("x5692_0", "x5682")
      val x5673 = SRAM(size=16,name="x5673",banking = Strided(1,16)).wtPort(x5691.readPort).rdPort(x5673_x5673_dsp0_bank0_s).rdAddr(x5694(0)).wtAddr(x5682(0))
    }
    val x5692_0 = Pipeline(name="x5692_0",parent=x5709) { implicit CU => 
      val x5687 = ScalarFIFO(size=1,name="x5687").wtPort(x5491_x5491_dsp1_bank0_s)
      val x5674 = ScalarBuffer(name="x5674").wtPort(x5557_x5557_dsp0_bank0_s)
      val ctr49 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5682 = CounterChain(name = "x5682", ctr49).iter(1)
      Stage(operands=List(CU.load(x5687), CU.load(x5674)), op=FixMul, results=List(CU.scalarOut(x5673_x5691_s)))
    }
    val x5708_0 = Pipeline(name="x5708_0",parent=x5709) { implicit CU => 
      val x5698 = ScalarFIFO(size=1,name="x5698").wtPort(x5673_x5673_dsp0_bank0_s)
      val x5700 = ScalarFIFO(size=1,name="x5700").wtPort(x5631_x5631_dsp0_bank0_s)
      val ctr50 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5694 = CounterChain(name = "x5694", ctr50).iter(1)
      Stage(operands=List(CU.load(x5698), CU.load(x5700)), op=FixAdd, results=List(CU.scalarOut(x5631_x5707_s)))
    }
    val x5719 = Pipeline(name="x5719",parent=x5730) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5712 = CounterChain(name = "x5712", ctr51).iter(16)
    }
    val x5728 = Pipeline(name="x5728",parent=x5730) { implicit CU => 
      val ctr52 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5721 = CounterChain(name = "x5721", ctr52).iter(16)
    }
    val x5907 = MetaPipeline(name="x5907",parent=x5931) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x5732 = CounterChain(name = "x5732", ctr53).iter(32)
    }
    val x5733_dsp0_bank0 = MemoryPipeline(name="x5733_dsp0_bank0",parent="x5907") { implicit CU => 
      val x5771 = ScalarFIFO(size=1,name="x5771").wtPort(x5745_x5762_data_s)
      val x5810 = CounterChain.copy("x5847", "x5810")
      val x5764 = CounterChain.copy("x5772", "x5764")
      val x5733 = SRAM(size=16,name="x5733",banking = NoBanking()).wtPort(x5771.readPort).rdPort(x5733_x5733_dsp0_bank0_s).rdAddr(x5810(0)).wtAddr(x5764(0))
    }
    val x5734_dsp0_bank0 = MemoryPipeline(name="x5734_dsp0_bank0",parent="x5907") { implicit CU => 
      val x5803 = ScalarFIFO(size=1,name="x5803").wtPort(x5777_x5794_data_s)
      val x5849 = CounterChain.copy("x5886", "x5849")
      val x5796 = CounterChain.copy("x5804", "x5796")
      val x5734 = SRAM(size=16,name="x5734",banking = NoBanking()).wtPort(x5803.readPort).rdPort(x5734_x5734_dsp0_bank0_s).rdAddr(x5849(0)).wtAddr(x5796(0))
    }
    val x5773 = StreamController(name="x5773",parent=x5907) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5743 = CounterChain(name = "x5743", ctr54).iter(1)
    }
    val x5761_0 = Pipeline(name="x5761_0",parent=x5773) { implicit CU => 
      val x5746 = CU.temp(None)
      val x5748 = CU.temp(None)
      val x5750 = CU.temp(None)
      val x5751 = CU.temp(None)
      val x5753 = ScalarBuffer(name="x5753").wtPort(a_dram_da)
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5732 = CounterChain.copy("x5907", "x5732")
      val x5743 = CounterChain.copy("x5773", "x5743")
      val x5761_unit = CounterChain(name = "x5761_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5732(0)), CU.ctr(x5743(0))), op=FixAdd, results=List(x5746))
      Stage(operands=List(x5746, Const(6)), op=FixSla, results=List(x5748))
      Stage(operands=List(x5748, CU.ctr(x5488(0))), op=FixAdd, results=List(x5750))
      Stage(operands=List(x5750, Const(3)), op=FixSla, results=List(x5751))
      Stage(operands=List(x5751, CU.load(x5753)), op=FixAdd, results=List(CU.scalarOut(x5744_b6131_x5760_b6133_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5744_b6132_x5760_b6134_s)))
    }
    val x5762 = MemoryController(name="x5762",parent=x5773,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5744_b6132 = ScalarFIFO(size=1,name="size").wtPort(x5744_b6132_x5760_b6134_s)
      val x5744_b6131 = ScalarFIFO(size=1,name="offset").wtPort(x5744_b6131_x5760_b6133_s)
      CU.newSout("data", x5745_x5762_data_s)
    }
    val x5772 = Pipeline(name="x5772",parent=x5773) { implicit CU => 
      val ctr55 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5764 = CounterChain(name = "x5764", ctr55).iter(16)
    }
    val x5805 = StreamController(name="x5805",parent=x5907) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5775 = CounterChain(name = "x5775", ctr56).iter(1)
    }
    val x5793_0 = Pipeline(name="x5793_0",parent=x5805) { implicit CU => 
      val x5780 = CU.temp(None)
      val x5783 = CU.temp(None)
      val x5778 = CU.temp(None)
      val x5782 = CU.temp(None)
      val x5785 = ScalarBuffer(name="x5785").wtPort(a_dram_da)
      val x5775 = CounterChain.copy("x5805", "x5775")
      val x5488 = CounterChain.copy("x5931", "x5488")
      val x5793_unit = CounterChain(name = "x5793_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x5732 = CounterChain.copy("x5907", "x5732")
      Stage(operands=List(CU.ctr(x5732(0)), CU.ctr(x5775(0))), op=FixAdd, results=List(x5778))
      Stage(operands=List(x5778, Const(6)), op=FixSla, results=List(x5780))
      Stage(operands=List(x5780, CU.ctr(x5488(0))), op=FixAdd, results=List(x5782))
      Stage(operands=List(x5782, Const(3)), op=FixSla, results=List(x5783))
      Stage(operands=List(x5783, CU.load(x5785)), op=FixAdd, results=List(CU.scalarOut(x5776_b6135_x5792_b6137_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5776_b6136_x5792_b6138_s)))
    }
    val x5794 = MemoryController(name="x5794",parent=x5805,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x5776_b6136 = ScalarFIFO(size=1,name="size").wtPort(x5776_b6136_x5792_b6138_s)
      val x5776_b6135 = ScalarFIFO(size=1,name="offset").wtPort(x5776_b6135_x5792_b6137_s)
      CU.newSout("data", x5777_x5794_data_s)
    }
    val x5804 = Pipeline(name="x5804",parent=x5805) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5796 = CounterChain(name = "x5796", ctr57).iter(16)
    }
    val x5807_dsp0_bank0 = MemoryPipeline(name="x5807_dsp0_bank0",parent="x5847") { implicit CU => 
      val x5845 = ScalarFIFO(size=1,name="x5845").wtPort(x5807_x5845_s)
      val x5832 = CounterChain.copy("x5846_0", "x5832")
      val x5807 = SRAM(size=16,name="x5807",banking = Strided(1,16)).wtPort(x5845.readPort).rdPort(x5807_x5807_dsp0_bank0_s).rdAddr(x5832(0)).wtAddr(x5832(0))
    }
    val x5807_dsp1_bank0 = MemoryPipeline(name="x5807_dsp1_bank0",parent="x5847") { implicit CU => 
      val x5845 = ScalarFIFO(size=1,name="x5845").wtPort(x5807_x5845_s)
      val x5832 = CounterChain.copy("x5846_0", "x5832")
      val x5889 = CounterChain.copy("x5896", "x5889")
      val x5807 = SRAM(size=16,name="x5807",banking = Strided(1,16)).wtPort(x5845.readPort).rdPort(x5807_x5807_dsp1_bank0_s).rdAddr(x5889(0)).wtAddr(x5832(0))
    }
    val x5808_dsp0_bank0 = MemoryPipeline(name="x5808_dsp0_bank0",parent="x5886") { implicit CU => 
      val x5884 = ScalarFIFO(size=1,name="x5884").wtPort(x5808_x5884_s)
      val x5871 = CounterChain.copy("x5885_0", "x5871")
      val x5808 = SRAM(size=16,name="x5808",banking = Strided(1,16)).wtPort(x5884.readPort).rdPort(x5808_x5808_dsp0_bank0_s).rdAddr(x5871(0)).wtAddr(x5871(0))
    }
    val x5808_dsp1_bank0 = MemoryPipeline(name="x5808_dsp1_bank0",parent="x5886") { implicit CU => 
      val x5884 = ScalarFIFO(size=1,name="x5884").wtPort(x5808_x5884_s)
      val x5871 = CounterChain.copy("x5885_0", "x5871")
      val x5898 = CounterChain.copy("x5905", "x5898")
      val x5808 = SRAM(size=16,name="x5808",banking = Strided(1,16)).wtPort(x5884.readPort).rdPort(x5808_x5808_dsp1_bank0_s).rdAddr(x5898(0)).wtAddr(x5871(0))
    }
    val x5847 = MetaPipeline(name="x5847",parent=x5907) { implicit CU => 
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5810 = CounterChain(name = "x5810", ctr58).iter(16)
    }
    val x5811_dsp0_bank0 = MemoryPipeline(name="x5811_dsp0_bank0",parent="x5847") { implicit CU => 
      val x5829 = ScalarFIFO(size=1,name="x5829").wtPort(x5811_x5829_s)
      val x5820 = CounterChain.copy("x5830_0", "x5820")
      val x5832 = CounterChain.copy("x5846_0", "x5832")
      val x5811 = SRAM(size=16,name="x5811",banking = Strided(1,16)).wtPort(x5829.readPort).rdPort(x5811_x5811_dsp0_bank0_s).rdAddr(x5832(0)).wtAddr(x5820(0))
    }
    val x5830_0 = Pipeline(name="x5830_0",parent=x5847) { implicit CU => 
      val x5825 = ScalarFIFO(size=1,name="x5825").wtPort(x5492_x5492_dsp0_bank0_s)
      val x5812 = ScalarBuffer(name="x5812").wtPort(x5733_x5733_dsp0_bank0_s)
      val ctr59 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5820 = CounterChain(name = "x5820", ctr59).iter(1)
      Stage(operands=List(CU.load(x5825), CU.load(x5812)), op=FixMul, results=List(CU.scalarOut(x5811_x5829_s)))
    }
    val x5846_0 = Pipeline(name="x5846_0",parent=x5847) { implicit CU => 
      val x5836 = ScalarFIFO(size=1,name="x5836").wtPort(x5811_x5811_dsp0_bank0_s)
      val x5838 = ScalarFIFO(size=1,name="x5838").wtPort(x5807_x5807_dsp0_bank0_s)
      val ctr60 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5832 = CounterChain(name = "x5832", ctr60).iter(1)
      Stage(operands=List(CU.load(x5836), CU.load(x5838)), op=FixAdd, results=List(CU.scalarOut(x5807_x5845_s)))
    }
    val x5886 = MetaPipeline(name="x5886",parent=x5907) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5849 = CounterChain(name = "x5849", ctr61).iter(16)
    }
    val x5850_dsp0_bank0 = MemoryPipeline(name="x5850_dsp0_bank0",parent="x5886") { implicit CU => 
      val x5868 = ScalarFIFO(size=1,name="x5868").wtPort(x5850_x5868_s)
      val x5871 = CounterChain.copy("x5885_0", "x5871")
      val x5859 = CounterChain.copy("x5869_0", "x5859")
      val x5850 = SRAM(size=16,name="x5850",banking = Strided(1,16)).wtPort(x5868.readPort).rdPort(x5850_x5850_dsp0_bank0_s).rdAddr(x5871(0)).wtAddr(x5859(0))
    }
    val x5869_0 = Pipeline(name="x5869_0",parent=x5886) { implicit CU => 
      val x5864 = ScalarFIFO(size=1,name="x5864").wtPort(x5492_x5492_dsp1_bank0_s)
      val x5851 = ScalarBuffer(name="x5851").wtPort(x5734_x5734_dsp0_bank0_s)
      val ctr62 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5859 = CounterChain(name = "x5859", ctr62).iter(1)
      Stage(operands=List(CU.load(x5864), CU.load(x5851)), op=FixMul, results=List(CU.scalarOut(x5850_x5868_s)))
    }
    val x5885_0 = Pipeline(name="x5885_0",parent=x5886) { implicit CU => 
      val x5875 = ScalarFIFO(size=1,name="x5875").wtPort(x5850_x5850_dsp0_bank0_s)
      val x5877 = ScalarFIFO(size=1,name="x5877").wtPort(x5808_x5808_dsp0_bank0_s)
      val ctr63 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5871 = CounterChain(name = "x5871", ctr63).iter(1)
      Stage(operands=List(CU.load(x5875), CU.load(x5877)), op=FixAdd, results=List(CU.scalarOut(x5808_x5884_s)))
    }
    val x5896 = Pipeline(name="x5896",parent=x5907) { implicit CU => 
      val ctr64 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5889 = CounterChain(name = "x5889", ctr64).iter(16)
    }
    val x5905 = Pipeline(name="x5905",parent=x5907) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5898 = CounterChain(name = "x5898", ctr65).iter(16)
    }
    val x5930_0 = Pipeline(name="x5930_0",parent=x5931) { implicit CU => 
      val x5924 = CU.temp(None)
      val x5918 = ScalarFIFO(size=1,name="x5918").wtPort(x5041_x5041_dsp0_bank0_s)
      val x5914 = ScalarFIFO(size=1,name="x5914").wtPort(x5489_x5489_dsp0_bank0_s).wtPort(x5489_x5489_dsp0_bank1_s)
      val x5916 = ScalarFIFO(size=1,name="x5916").wtPort(x5490_x5490_dsp0_bank0_s).wtPort(x5490_x5490_dsp0_bank1_s)
      val ctr66 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5911 = CounterChain(name = "x5911", ctr66, ctr67).iter(64)
      Stage(operands=List(CU.load(x5914), CU.load(x5916)), op=FixAdd, results=List(x5924))
      Stage(operands=List(x5924, CU.load(x5918)), op=FixAdd, results=List(CU.scalarOut(x5041_x5929_s)))
    }
    val x5969 = StreamController(name="x5969",parent=x6003) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x5939 = CounterChain(name = "x5939", ctr68).iter(64)
    }
    val x5955_0 = Pipeline(name="x5955_0",parent=x5969) { implicit CU => 
      val x5947 = CU.temp(None)
      val x5946 = CU.temp(None)
      val x5944 = CU.temp(None)
      val x5949 = ScalarBuffer(name="x5949").wtPort(c_dram_da)
      val x5939 = CounterChain.copy("x5969", "x5939")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5955_unit = CounterChain(name = "x5955_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5939(0)), Const(6)), op=FixSla, results=List(x5944))
      Stage(operands=List(x5944, CU.ctr(x5039(0))), op=FixAdd, results=List(x5946))
      Stage(operands=List(x5946, Const(3)), op=FixSla, results=List(x5947))
      Stage(operands=List(x5947, CU.load(x5949)), op=FixAdd, results=List(CU.scalarOut(x5940_b6155_x5954_b6157_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5940_b6156_x5954_b6158_s)))
    }
    val x5964 = Pipeline(name="x5964",parent=x5969) { implicit CU => 
      val ctr69 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5957 = CounterChain(name = "x5957", ctr69).iter(1)
    }
    val x5965 = MemoryController(name="x5965",parent=x5969,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x5940_b6156 = ScalarFIFO(size=1,name="size").wtPort(x5940_b6156_x5954_b6158_s)
      val x5941 = ScalarFIFO(size=1,name="data").wtPort(x5040_x5040_dsp1_bank0_s)
      val x5940_b6155 = ScalarFIFO(size=1,name="offset").wtPort(x5940_b6155_x5954_b6157_s)
    }
    val x6001 = StreamController(name="x6001",parent=x6003) { implicit CU => 
      val ctr70 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x5971 = CounterChain(name = "x5971", ctr70).iter(64)
    }
    val x5987_0 = Pipeline(name="x5987_0",parent=x6001) { implicit CU => 
      val x5979 = CU.temp(None)
      val x5978 = CU.temp(None)
      val x5976 = CU.temp(None)
      val x5981 = ScalarBuffer(name="x5981").wtPort(c_dram_da)
      val x5971 = CounterChain.copy("x6001", "x5971")
      val x5039 = CounterChain.copy("x6003", "x5039")
      val x5987_unit = CounterChain(name = "x5987_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5971(0)), Const(6)), op=FixSla, results=List(x5976))
      Stage(operands=List(x5976, CU.ctr(x5039(0))), op=FixAdd, results=List(x5978))
      Stage(operands=List(x5978, Const(3)), op=FixSla, results=List(x5979))
      Stage(operands=List(x5979, CU.load(x5981)), op=FixAdd, results=List(CU.scalarOut(x5972_b6161_x5986_b6163_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x5972_b6162_x5986_b6164_s)))
    }
    val x5996 = Pipeline(name="x5996",parent=x6001) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5989 = CounterChain(name = "x5989", ctr71).iter(1)
    }
    val x5997 = MemoryController(name="x5997",parent=x6001,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x5972_b6162 = ScalarFIFO(size=1,name="size").wtPort(x5972_b6162_x5986_b6164_s)
      val x5973 = ScalarFIFO(size=1,name="data").wtPort(x5041_x5041_dsp1_bank0_s)
      val x5972_b6161 = ScalarFIFO(size=1,name="offset").wtPort(x5972_b6161_x5986_b6163_s)
    }
    
  }
}
