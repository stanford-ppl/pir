import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5040_x5403_s = Scalar("x5040_x5403")
    val x5079_x5237_s = Scalar("x5079_x5237")
    val x5058_x5620_v = Vector("x5058_x5620")
    val x5066_x5794_x5906_v = Vector("x5066_x5794_x5906")
    val x4973_x5323_x5334_v = Vector("x4973_x5323_x5334")
    val x4983_x5341_x5353_v = Vector("x4983_x5341_x5353")
    val x5086_x5370_s = Scalar("x5086_x5370")
    val x4973_x5228_x5239_v = Vector("x4973_x5228_x5239")
    val x5064_x5692_v = Vector("x5064_x5692")
    val x4989_argin = ArgIn("x4989")
    val x5083_x5313_s = Scalar("x5083_x5313")
    val x5068_x5740_v = Vector("x5068_x5740")
    val x5059_x5787_x5906_v = Vector("x5059_x5787_x5906")
    val x4973_x5304_x5315_v = Vector("x4973_x5304_x5315")
    val x4984_x5516_x5524_v = Vector("x4984_x5516_x5524")
    val bus_59182_s = Scalar("bus_59182")
    val x4973_x5247_x5258_v = Vector("x4973_x5247_x5258")
    val x4973_x5342_x5353_v = Vector("x4973_x5342_x5353")
    val x4984_x5504_x5512_v = Vector("x4984_x5504_x5512")
    val x4983_x5151_x5163_v = Vector("x4983_x5151_x5163")
    val x5063_x5791_x5906_v = Vector("x5063_x5791_x5906")
    val x5071_x5776_v = Vector("x5071_x5776")
    val x4983_x5094_x5106_v = Vector("x4983_x5094_x5106")
    val x4973_x5361_x5372_v = Vector("x4973_x5361_x5372")
    val x4983_x5170_x5182_v = Vector("x4983_x5170_x5182")
    val x4984_x5564_x5572_v = Vector("x4984_x5564_x5572")
    val x5063_x5680_v = Vector("x5063_x5680")
    val x5928_argin = ArgIn("x5928")
    val bus_59148_s = Scalar("bus_59148")
    val x4988_x5002_data_v = Vector("x4988_x5002_data")
    val x5014_b6018_x5024_b6020_s = Scalar("x5014_b6018_x5024_b6020")
    val x4973_x5266_x5277_v = Vector("x4973_x5266_x5277")
    val x5065_x5704_v = Vector("x5065_x5704")
    val x4983_x5664_x5669_v = Vector("x4983_x5664_x5669")
    val x5925_b6085_x5933_b6087_s = Scalar("x5925_b6085_x5933_b6087")
    val x4973_x5285_x5296_v = Vector("x4973_x5285_x5296")
    val x5081_x5275_s = Scalar("x5081_x5275")
    val x4983_x5227_x5239_v = Vector("x4983_x5227_x5239")
    val x4973_x5914_x5920_v = Vector("x4973_x5914_x5920")
    val x4983_x5676_x5681_v = Vector("x4983_x5676_x5681")
    val x4984_x5432_x5440_v = Vector("x4984_x5432_x5440")
    val x4984_x5552_x5560_v = Vector("x4984_x5552_x5560")
    val x4984_x5492_x5500_v = Vector("x4984_x5492_x5500")
    val x4987_b6011_x5000_b6013_s = Scalar("x4987_b6011_x5000_b6013")
    val x4983_x5360_x5372_v = Vector("x4983_x5360_x5372")
    val x5048_x5499_s = Scalar("x5048_x5499")
    val x5067_x5728_v = Vector("x5067_x5728")
    val x4983_x5652_x5657_v = Vector("x4983_x5652_x5657")
    val x4983_x5592_x5597_v = Vector("x4983_x5592_x5597")
    val x4983_x5303_x5315_v = Vector("x4983_x5303_x5315")
    val x4983_x5322_x5334_v = Vector("x4983_x5322_x5334")
    val x4973_x5209_x5220_v = Vector("x4973_x5209_x5220")
    val x4983_x5712_x5717_v = Vector("x4983_x5712_x5717")
    val x4983_x5760_x5765_v = Vector("x4983_x5760_x5765")
    val x5059_x5632_v = Vector("x5059_x5632")
    val x4984_x5444_x5452_v = Vector("x4984_x5444_x5452")
    val x5043_x5439_s = Scalar("x5043_x5439")
    val x5054_x5571_s = Scalar("x5054_x5571")
    val x5061_x5656_v = Vector("x5061_x5656")
    val x5062_x5668_v = Vector("x5062_x5668")
    val x4967_oc = OffChip("x4967")
    val x5066_x5716_v = Vector("x5066_x5716")
    val x4983_x5379_x5391_v = Vector("x4983_x5379_x5391")
    val x5061_x5789_x5906_v = Vector("x5061_x5789_x5906")
    val x5044_x5451_s = Scalar("x5044_x5451")
    val x4987_b6012_x5000_b6014_s = Scalar("x4987_b6012_x5000_b6014")
    val x4973_x5133_x5144_v = Vector("x4973_x5133_x5144")
    val x5078_x5218_s = Scalar("x5078_x5218")
    val x4983_x5132_x5144_v = Vector("x4983_x5132_x5144")
    val x4983_x5616_x5621_v = Vector("x4983_x5616_x5621")
    val x5045_x5463_s = Scalar("x5045_x5463")
    val x4983_x5246_x5258_v = Vector("x4983_x5246_x5258")
    val x5062_x5790_x5906_v = Vector("x5062_x5790_x5906")
    val x5069_x5797_x5906_v = Vector("x5069_x5797_x5906")
    val x5050_x5523_s = Scalar("x5050_x5523")
    val x4983_x5640_x5645_v = Vector("x4983_x5640_x5645")
    val x5074_x5142_s = Scalar("x5074_x5142")
    val x4973_x5190_x5201_v = Vector("x4973_x5190_x5201")
    val x4983_x5189_x5201_v = Vector("x4983_x5189_x5201")
    val x5085_x5351_s = Scalar("x5085_x5351")
    val x4983_x5700_x5705_v = Vector("x4983_x5700_x5705")
    val x4979_x5800_x5906_v = Vector("x4979_x5800_x5906")
    val x5047_x5487_s = Scalar("x5047_x5487")
    val x5057_x5608_v = Vector("x5057_x5608")
    val x5060_x5644_v = Vector("x5060_x5644")
    val x4983_x5628_x5633_v = Vector("x4983_x5628_x5633")
    val x4983_x5265_x5277_v = Vector("x4983_x5265_x5277")
    val x5042_x5427_s = Scalar("x5042_x5427")
    val x4983_x5772_x5777_v = Vector("x4983_x5772_x5777")
    val x4966_oc = OffChip("x4966")
    val x5056_x5596_v = Vector("x5056_x5596")
    val x5070_x5798_x5906_v = Vector("x5070_x5798_x5906")
    val x5064_x5792_x5906_v = Vector("x5064_x5792_x5906")
    val x4984_x5468_x5476_v = Vector("x4984_x5468_x5476")
    val x4983_x5736_x5741_v = Vector("x4983_x5736_x5741")
    val x5072_x5104_s = Scalar("x5072_x5104")
    val x5084_x5332_s = Scalar("x5084_x5332")
    val x5069_x5752_v = Vector("x5069_x5752")
    val x4973_x5380_x5391_v = Vector("x4973_x5380_x5391")
    val x5087_x5389_s = Scalar("x5087_x5389")
    val x4984_x5420_x5428_v = Vector("x4984_x5420_x5428")
    val x5080_x5256_s = Scalar("x5080_x5256")
    val x5014_b6017_x5024_b6019_s = Scalar("x5014_b6017_x5024_b6019")
    val x5068_x5796_x5906_v = Vector("x5068_x5796_x5906")
    val x4979_x5913_x5920_v = Vector("x4979_x5913_x5920")
    val x4973_x5095_x5106_v = Vector("x4973_x5095_x5106")
    val x5067_x5795_x5906_v = Vector("x5067_x5795_x5906")
    val x5070_x5764_v = Vector("x5070_x5764")
    val x4984_x5576_x5584_v = Vector("x4984_x5576_x5584")
    val x4960_argin = ArgIn("x4960")
    val x5076_x5180_s = Scalar("x5076_x5180")
    val x4973_x5937_x5941_v = Vector("x4973_x5937_x5941")
    val x5073_x5123_s = Scalar("x5073_x5123")
    val x4983_x5208_x5220_v = Vector("x4983_x5208_x5220")
    val x4984_x5528_x5536_v = Vector("x4984_x5528_x5536")
    val x4983_x5748_x5753_v = Vector("x4983_x5748_x5753")
    val x4984_x5396_x5404_v = Vector("x4984_x5396_x5404")
    val x4983_x5113_x5125_v = Vector("x4983_x5113_x5125")
    val x5053_x5559_s = Scalar("x5053_x5559")
    val x4984_x5456_x5464_v = Vector("x4984_x5456_x5464")
    val x4973_x5152_x5163_v = Vector("x4973_x5152_x5163")
    val x5057_x5785_x5906_v = Vector("x5057_x5785_x5906")
    val x5015_x5026_data_v = Vector("x5015_x5026_data")
    val x4983_x5688_x5693_v = Vector("x4983_x5688_x5693")
    val x4984_x5540_x5548_v = Vector("x4984_x5540_x5548")
    val x4983_x5724_x5729_v = Vector("x4983_x5724_x5729")
    val x5060_x5788_x5906_v = Vector("x5060_x5788_x5906")
    val x5065_x5793_x5906_v = Vector("x5065_x5793_x5906")
    val bus_59129_s = Scalar("bus_59129")
    val bus_59187_s = Scalar("bus_59187")
    val x4984_x5408_x5416_v = Vector("x4984_x5408_x5416")
    val x5925_b6086_x5933_b6088_s = Scalar("x5925_b6086_x5933_b6088")
    val x4984_x5480_x5488_v = Vector("x4984_x5480_x5488")
    val x4964_oc = OffChip("x4964")
    val x5051_x5535_s = Scalar("x5051_x5535")
    val x4983_x5284_x5296_v = Vector("x4983_x5284_x5296")
    val x5058_x5786_x5906_v = Vector("x5058_x5786_x5906")
    val x5082_x5294_s = Scalar("x5082_x5294")
    val x5049_x5511_s = Scalar("x5049_x5511")
    val x4983_x5604_x5609_v = Vector("x4983_x5604_x5609")
    val x5041_x5415_s = Scalar("x5041_x5415")
    val x5077_x5199_s = Scalar("x5077_x5199")
    val x4973_x5919_v = Vector("x4973_x5919")
    val x5075_x5161_s = Scalar("x5075_x5161")
    val x5052_x5547_s = Scalar("x5052_x5547")
    val x5016_argin = ArgIn("x5016")
    val x5056_x5784_x5906_v = Vector("x5056_x5784_x5906")
    val x4979_x5905_v = Vector("x4979_x5905")
    val x5055_x5583_s = Scalar("x5055_x5583")
    val x4959_argin = ArgIn("x4959")
    val x4973_x5114_x5125_v = Vector("x4973_x5114_x5125")
    val x5046_x5475_s = Scalar("x5046_x5475")
    val x4973_x5171_x5182_v = Vector("x4973_x5171_x5182")
    val x5071_x5799_x5906_v = Vector("x5071_x5799_x5906")
    val x5950 = Sequential(name="x5950",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5950_unit = CounterChain(name = "x5950_unit", ctr1)
    }
    val x4973_dsp0 = MemoryPipeline(name="x4973_dsp0",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5936 = CounterChain.copy("x5941", "x5936")
      val x4973_x5937 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5937_x5941_v).rdAddr(x5936(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp1 = MemoryPipeline(name="x4973_dsp1",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x4973_x5914 =  SRAM(size=192,banking = NoBanking()).wtPort(x5919_x5919.readPort).rdPort(x4973_x5914_x5920_v).rdAddr(x5911(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp2 = MemoryPipeline(name="x4973_dsp2",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5374 = CounterChain.copy("x5391_0", "x5374")
      val x4973_x5380 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5380_x5391_v).rdAddr(x5374(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp3 = MemoryPipeline(name="x4973_dsp3",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5355 = CounterChain.copy("x5372_0", "x5355")
      val x4973_x5361 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5361_x5372_v).rdAddr(x5355(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp4 = MemoryPipeline(name="x4973_dsp4",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5336 = CounterChain.copy("x5353_0", "x5336")
      val x4973_x5342 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5342_x5353_v).rdAddr(x5336(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp5 = MemoryPipeline(name="x4973_dsp5",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5317 = CounterChain.copy("x5334_0", "x5317")
      val x4973_x5323 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5323_x5334_v).rdAddr(x5317(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp6 = MemoryPipeline(name="x4973_dsp6",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5298 = CounterChain.copy("x5315_0", "x5298")
      val x4973_x5304 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5304_x5315_v).rdAddr(x5298(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp7 = MemoryPipeline(name="x4973_dsp7",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5279 = CounterChain.copy("x5296_0", "x5279")
      val x4973_x5285 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5285_x5296_v).rdAddr(x5279(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp8 = MemoryPipeline(name="x4973_dsp8",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5260 = CounterChain.copy("x5277_0", "x5260")
      val x4973_x5266 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5266_x5277_v).rdAddr(x5260(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp9 = MemoryPipeline(name="x4973_dsp9",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5241 = CounterChain.copy("x5258_0", "x5241")
      val x4973_x5247 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5247_x5258_v).rdAddr(x5241(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp10 = MemoryPipeline(name="x4973_dsp10",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5222 = CounterChain.copy("x5239_0", "x5222")
      val x4973_x5228 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5228_x5239_v).rdAddr(x5222(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp11 = MemoryPipeline(name="x4973_dsp11",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5203 = CounterChain.copy("x5220_0", "x5203")
      val x4973_x5209 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5209_x5220_v).rdAddr(x5203(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp12 = MemoryPipeline(name="x4973_dsp12",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5184 = CounterChain.copy("x5201_0", "x5184")
      val x4973_x5190 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5190_x5201_v).rdAddr(x5184(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp13 = MemoryPipeline(name="x4973_dsp13",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5165 = CounterChain.copy("x5182_0", "x5165")
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x4973_x5171 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5171_x5182_v).rdAddr(x5165(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp14 = MemoryPipeline(name="x4973_dsp14",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5146 = CounterChain.copy("x5163_0", "x5146")
      val x4973_x5152 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5152_x5163_v).rdAddr(x5146(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp15 = MemoryPipeline(name="x4973_dsp15",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5127 = CounterChain.copy("x5144_0", "x5127")
      val x4973_x5133 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5133_x5144_v).rdAddr(x5127(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp16 = MemoryPipeline(name="x4973_dsp16",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5108 = CounterChain.copy("x5125_0", "x5108")
      val x4973_x5114 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5114_x5125_v).rdAddr(x5108(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x4973_dsp17 = MemoryPipeline(name="x4973_dsp17",parent="x5923") { implicit CU => 
      val x5919_x5919 =  VectorFIFO(size=1).wtPort(x4973_x5919_v)
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x5089 = CounterChain.copy("x5106_0", "x5089")
      val x4973_x5095 =  SRAM(size=192,banking = Strided(1)).wtPort(x5919_x5919.readPort).rdPort(x4973_x5095_x5106_v).rdAddr(x5089(0)).wtAddr(x5911(0))
      var stage: List[Stage] = Nil
    }
    val x5924 = Sequential(name="x5924",parent=x5950) { implicit CU => 
      val x4959_x4974 =  ScalarBuffer().wtPort(x4959_argin)
      val ctr2 = Counter(min=Const(0), max=x4959_x4974.load, step=Const(1), par=1) // Counter
      val x4976 = CounterChain(name = "x4976", ctr2)
    }
    val x5923 = Sequential(name="x5923",parent=x5924) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x4978 = CounterChain(name = "x4978", ctr3)
    }
    val x4979_dsp0 = MemoryPipeline(name="x4979_dsp0",parent="x5908") { implicit CU => 
      val x5905_x5905 =  VectorFIFO(size=1).wtPort(x4979_x5905_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5911 = CounterChain.copy("x5920_0", "x5911")
      val x4979_x5913 =  SRAM(size=192,banking = NoBanking()).wtPort(x5905_x5905.readPort).rdPort(x4979_x5913_x5920_v).rdAddr(x5911(0)).wtAddr(x5780(0))
      var stage: List[Stage] = Nil
    }
    val x4979_dsp1 = MemoryPipeline(name="x4979_dsp1",parent="x5908") { implicit CU => 
      val x5905_x5905 =  VectorFIFO(size=1).wtPort(x4979_x5905_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x4979_x5800 =  SRAM(size=192,banking = NoBanking()).wtPort(x5905_x5905.readPort).rdPort(x4979_x5800_x5906_v).rdAddr(x5780(0)).wtAddr(x5780(0))
      var stage: List[Stage] = Nil
    }
    val x5909 = MetaPipeline(name="x5909",parent=x5923) { implicit CU => 
      val x4960_x4980 =  ScalarBuffer().wtPort(x4960_argin)
      val ctr4 = Counter(min=Const(0), max=x4960_x4980.load, step=Const(16), par=1) // Counter
      val x4982 = CounterChain(name = "x4982", ctr4)
    }
    val x4983_dsp0 = MemoryPipeline(name="x4983_dsp0",parent="x5909") { implicit CU => 
      val b6083 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5767 = CounterChain.copy("x5777_0", "x5767")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5772 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5772_x5777_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5772.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6083))
      RAStage(operands=List(b6083, CU.ctr(x5767(0))), op=FixAdd, results=List(x4983_x5772.readAddr))
    }
    val x4983_dsp1 = MemoryPipeline(name="x4983_dsp1",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6081 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5755 = CounterChain.copy("x5765_0", "x5755")
      val x4983_x5760 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5760_x5765_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5760.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6081))
      RAStage(operands=List(b6081, CU.ctr(x5755(0))), op=FixAdd, results=List(x4983_x5760.readAddr))
    }
    val x4983_dsp2 = MemoryPipeline(name="x4983_dsp2",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6079 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5743 = CounterChain.copy("x5753_0", "x5743")
      val x4983_x5748 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5748_x5753_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5748.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6079))
      RAStage(operands=List(b6079, CU.ctr(x5743(0))), op=FixAdd, results=List(x4983_x5748.readAddr))
    }
    val x4983_dsp3 = MemoryPipeline(name="x4983_dsp3",parent="x5909") { implicit CU => 
      val b6077 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5731 = CounterChain.copy("x5741_0", "x5731")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5736 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5736_x5741_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5736.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6077))
      RAStage(operands=List(b6077, CU.ctr(x5731(0))), op=FixAdd, results=List(x4983_x5736.readAddr))
    }
    val x4983_dsp4 = MemoryPipeline(name="x4983_dsp4",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6075 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5719 = CounterChain.copy("x5729_0", "x5719")
      val x4983_x5724 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5724_x5729_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5724.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6075))
      RAStage(operands=List(b6075, CU.ctr(x5719(0))), op=FixAdd, results=List(x4983_x5724.readAddr))
    }
    val x4983_dsp5 = MemoryPipeline(name="x4983_dsp5",parent="x5909") { implicit CU => 
      val b6073 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5707 = CounterChain.copy("x5717_0", "x5707")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5712 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5712_x5717_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5712.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6073))
      RAStage(operands=List(b6073, CU.ctr(x5707(0))), op=FixAdd, results=List(x4983_x5712.readAddr))
    }
    val x4983_dsp6 = MemoryPipeline(name="x4983_dsp6",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6071 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5695 = CounterChain.copy("x5705_0", "x5695")
      val x4983_x5700 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5700_x5705_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5700.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6071))
      RAStage(operands=List(b6071, CU.ctr(x5695(0))), op=FixAdd, results=List(x4983_x5700.readAddr))
    }
    val x4983_dsp7 = MemoryPipeline(name="x4983_dsp7",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6069 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5683 = CounterChain.copy("x5693_0", "x5683")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5688 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5688_x5693_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5688.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6069))
      RAStage(operands=List(b6069, CU.ctr(x5683(0))), op=FixAdd, results=List(x4983_x5688.readAddr))
    }
    val x4983_dsp8 = MemoryPipeline(name="x4983_dsp8",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6067 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5671 = CounterChain.copy("x5681_0", "x5671")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5676 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5676_x5681_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5676.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6067))
      RAStage(operands=List(b6067, CU.ctr(x5671(0))), op=FixAdd, results=List(x4983_x5676.readAddr))
    }
    val x4983_dsp9 = MemoryPipeline(name="x4983_dsp9",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6065 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5659 = CounterChain.copy("x5669_0", "x5659")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5664 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5664_x5669_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5664.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6065))
      RAStage(operands=List(b6065, CU.ctr(x5659(0))), op=FixAdd, results=List(x4983_x5664.readAddr))
    }
    val x4983_dsp10 = MemoryPipeline(name="x4983_dsp10",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6063 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5647 = CounterChain.copy("x5657_0", "x5647")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5652 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5652_x5657_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5652.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6063))
      RAStage(operands=List(b6063, CU.ctr(x5647(0))), op=FixAdd, results=List(x4983_x5652.readAddr))
    }
    val x4983_dsp11 = MemoryPipeline(name="x4983_dsp11",parent="x5909") { implicit CU => 
      val b6061 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5635 = CounterChain.copy("x5645_0", "x5635")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5640 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5640_x5645_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5640.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6061))
      RAStage(operands=List(b6061, CU.ctr(x5635(0))), op=FixAdd, results=List(x4983_x5640.readAddr))
    }
    val x4983_dsp12 = MemoryPipeline(name="x4983_dsp12",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6059 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5623 = CounterChain.copy("x5633_0", "x5623")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5628 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5628_x5633_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5628.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6059))
      RAStage(operands=List(b6059, CU.ctr(x5623(0))), op=FixAdd, results=List(x4983_x5628.readAddr))
    }
    val x4983_dsp13 = MemoryPipeline(name="x4983_dsp13",parent="x5909") { implicit CU => 
      val b6057 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5611 = CounterChain.copy("x5621_0", "x5611")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5616 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5616_x5621_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5616.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6057))
      RAStage(operands=List(b6057, CU.ctr(x5611(0))), op=FixAdd, results=List(x4983_x5616.readAddr))
    }
    val x4983_dsp14 = MemoryPipeline(name="x4983_dsp14",parent="x5909") { implicit CU => 
      val b6055 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5599 = CounterChain.copy("x5609_0", "x5599")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5604 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5604_x5609_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5604.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6055))
      RAStage(operands=List(b6055, CU.ctr(x5599(0))), op=FixAdd, results=List(x4983_x5604.readAddr))
    }
    val x4983_dsp15 = MemoryPipeline(name="x4983_dsp15",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6053 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5587 = CounterChain.copy("x5597_0", "x5587")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5592 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5592_x5597_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5592.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6053))
      RAStage(operands=List(b6053, CU.ctr(x5587(0))), op=FixAdd, results=List(x4983_x5592.readAddr))
    }
    val x4983_dsp16 = MemoryPipeline(name="x4983_dsp16",parent="x5909") { implicit CU => 
      val b6051 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5374 = CounterChain.copy("x5391_0", "x5374")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5379 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5379_x5391_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5379.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6051))
      RAStage(operands=List(b6051, CU.ctr(x5374(0))), op=FixAdd, results=List(x4983_x5379.readAddr))
    }
    val x4983_dsp17 = MemoryPipeline(name="x4983_dsp17",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6049 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5355 = CounterChain.copy("x5372_0", "x5355")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5360 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5360_x5372_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5360.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6049))
      RAStage(operands=List(b6049, CU.ctr(x5355(0))), op=FixAdd, results=List(x4983_x5360.readAddr))
    }
    val x4983_dsp18 = MemoryPipeline(name="x4983_dsp18",parent="x5909") { implicit CU => 
      val b6047 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5336 = CounterChain.copy("x5353_0", "x5336")
      val x4983_x5341 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5341_x5353_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5341.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6047))
      RAStage(operands=List(b6047, CU.ctr(x5336(0))), op=FixAdd, results=List(x4983_x5341.readAddr))
    }
    val x4983_dsp19 = MemoryPipeline(name="x4983_dsp19",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6045 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5317 = CounterChain.copy("x5334_0", "x5317")
      val x4983_x5322 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5322_x5334_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5322.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6045))
      RAStage(operands=List(b6045, CU.ctr(x5317(0))), op=FixAdd, results=List(x4983_x5322.readAddr))
    }
    val x4983_dsp20 = MemoryPipeline(name="x4983_dsp20",parent="x5909") { implicit CU => 
      val b6043 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5298 = CounterChain.copy("x5315_0", "x5298")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5303 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5303_x5315_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5303.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6043))
      RAStage(operands=List(b6043, CU.ctr(x5298(0))), op=FixAdd, results=List(x4983_x5303.readAddr))
    }
    val x4983_dsp21 = MemoryPipeline(name="x4983_dsp21",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6041 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5279 = CounterChain.copy("x5296_0", "x5279")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5284 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5284_x5296_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5284.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6041))
      RAStage(operands=List(b6041, CU.ctr(x5279(0))), op=FixAdd, results=List(x4983_x5284.readAddr))
    }
    val x4983_dsp22 = MemoryPipeline(name="x4983_dsp22",parent="x5909") { implicit CU => 
      val b6039 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5260 = CounterChain.copy("x5277_0", "x5260")
      val x4983_x5265 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5265_x5277_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5265.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6039))
      RAStage(operands=List(b6039, CU.ctr(x5260(0))), op=FixAdd, results=List(x4983_x5265.readAddr))
    }
    val x4983_dsp23 = MemoryPipeline(name="x4983_dsp23",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6037 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x5241 = CounterChain.copy("x5258_0", "x5241")
      val x4983_x5246 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5246_x5258_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5246.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6037))
      RAStage(operands=List(b6037, CU.ctr(x5241(0))), op=FixAdd, results=List(x4983_x5246.readAddr))
    }
    val x4983_dsp24 = MemoryPipeline(name="x4983_dsp24",parent="x5909") { implicit CU => 
      val b6035 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5222 = CounterChain.copy("x5239_0", "x5222")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5227 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5227_x5239_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5227.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6035))
      RAStage(operands=List(b6035, CU.ctr(x5222(0))), op=FixAdd, results=List(x4983_x5227.readAddr))
    }
    val x4983_dsp25 = MemoryPipeline(name="x4983_dsp25",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6033 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5203 = CounterChain.copy("x5220_0", "x5203")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5208 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5208_x5220_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5208.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6033))
      RAStage(operands=List(b6033, CU.ctr(x5203(0))), op=FixAdd, results=List(x4983_x5208.readAddr))
    }
    val x4983_dsp26 = MemoryPipeline(name="x4983_dsp26",parent="x5909") { implicit CU => 
      val b6031 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5184 = CounterChain.copy("x5201_0", "x5184")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5189 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5189_x5201_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5189.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6031))
      RAStage(operands=List(b6031, CU.ctr(x5184(0))), op=FixAdd, results=List(x4983_x5189.readAddr))
    }
    val x4983_dsp27 = MemoryPipeline(name="x4983_dsp27",parent="x5909") { implicit CU => 
      val b6029 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5165 = CounterChain.copy("x5182_0", "x5165")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5170 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5170_x5182_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5170.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6029))
      RAStage(operands=List(b6029, CU.ctr(x5165(0))), op=FixAdd, results=List(x4983_x5170.readAddr))
    }
    val x4983_dsp28 = MemoryPipeline(name="x4983_dsp28",parent="x5909") { implicit CU => 
      val b6027 = CU.temp
      val b6015 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5146 = CounterChain.copy("x5163_0", "x5146")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5151 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5151_x5163_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5151.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6027))
      RAStage(operands=List(b6027, CU.ctr(x5146(0))), op=FixAdd, results=List(x4983_x5151.readAddr))
    }
    val x4983_dsp29 = MemoryPipeline(name="x4983_dsp29",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6025 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5127 = CounterChain.copy("x5144_0", "x5127")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5132 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5132_x5144_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5132.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6025))
      RAStage(operands=List(b6025, CU.ctr(x5127(0))), op=FixAdd, results=List(x4983_x5132.readAddr))
    }
    val x4983_dsp30 = MemoryPipeline(name="x4983_dsp30",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6023 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5108 = CounterChain.copy("x5125_0", "x5108")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5113 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5113_x5125_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5113.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6023))
      RAStage(operands=List(b6023, CU.ctr(x5108(0))), op=FixAdd, results=List(x4983_x5113.readAddr))
    }
    val x4983_dsp31 = MemoryPipeline(name="x4983_dsp31",parent="x5909") { implicit CU => 
      val b6015 = CU.temp
      val b6021 = CU.temp
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4988_x5002_data_v)
      val x5089 = CounterChain.copy("x5106_0", "x5089")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x5004 = CounterChain.copy("x5012", "x5004")
      val x4983_x5094 =  SRAM(size=3072,banking = Strided(1)).wtPort(x5011_x5011.readPort).rdPort(x4983_x5094_x5106_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4986(0)), Const(192)), op=FixMul, results=List(b6015))
      WAStage(operands=List(b6015, CU.ctr(x5004(0))), op=FixAdd, results=List(x4983_x5094.writeAddr))
      RAStage(operands=List(CU.ctr(x5039(0)), Const(192)), op=FixMul, results=List(b6021))
      RAStage(operands=List(b6021, CU.ctr(x5089(0))), op=FixAdd, results=List(x4983_x5094.readAddr))
    }
    val x4984_dsp0 = MemoryPipeline(name="x4984_dsp0",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5576 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5576_x5584_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp1 = MemoryPipeline(name="x4984_dsp1",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5564 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5564_x5572_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp2 = MemoryPipeline(name="x4984_dsp2",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5552 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5552_x5560_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp3 = MemoryPipeline(name="x4984_dsp3",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5540 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5540_x5548_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp4 = MemoryPipeline(name="x4984_dsp4",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5528 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5528_x5536_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp5 = MemoryPipeline(name="x4984_dsp5",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5516 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5516_x5524_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp6 = MemoryPipeline(name="x4984_dsp6",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5504 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5504_x5512_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp7 = MemoryPipeline(name="x4984_dsp7",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5492 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5492_x5500_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp8 = MemoryPipeline(name="x4984_dsp8",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5480 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5480_x5488_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp9 = MemoryPipeline(name="x4984_dsp9",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5468 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5468_x5476_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp10 = MemoryPipeline(name="x4984_dsp10",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5456 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5456_x5464_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp11 = MemoryPipeline(name="x4984_dsp11",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5444 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5444_x5452_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp12 = MemoryPipeline(name="x4984_dsp12",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5432 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5432_x5440_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp13 = MemoryPipeline(name="x4984_dsp13",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5420 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5420_x5428_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp14 = MemoryPipeline(name="x4984_dsp14",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5408 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5408_x5416_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x4984_dsp15 = MemoryPipeline(name="x4984_dsp15",parent="x5909") { implicit CU => 
      val x5034_x5034 =  VectorFIFO(size=1).wtPort(x5015_x5026_data_v)
      val x5028 = CounterChain.copy("x5035", "x5028")
      val x5039 = CounterChain.copy("x5908", "x5039")
      val x4984_x5396 =  SRAM(size=16,banking = Strided(1)).wtPort(x5034_x5034.readPort).rdPort(x4984_x5396_x5404_v).rdAddr(x5039(0)).wtAddr(x5028(0))
      var stage: List[Stage] = Nil
    }
    val x5013 = StreamController(name="x5013",parent=x5909) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4986 = CounterChain(name = "x4986", ctr5)
    }
    val x5001_0 = Pipeline(name="x5001_0",parent=x5013) { implicit CU => 
      val x4990 = CU.temp
      val x4992 = CU.temp
      val x4991 = CU.temp
      val x4989 =  ScalarBuffer().wtPort(x4989_argin)
      val x4982 = CounterChain.copy("x5909", "x4982")
      val x4986 = CounterChain.copy("x5013", "x4986")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5001_unit = CounterChain(name = "x5001_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4982(0)), CU.ctr(x4986(0))), op=FixAdd, results=List(x4990))
      Stage(operands=List(x4990, Const(192)), op=FixMul, results=List(x4991))
      Stage(operands=List(x4991, Const(4)), op=FixMul, results=List(x4992))
      Stage(operands=List(x4992, CU.load(x4989)), op=FixAdd, results=List(CU.scalarOut(x4987_b6011_x5000_b6013_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4987_b6012_x5000_b6014_s)))
    }
    val x5002 = MemoryController(name="x5002",parent=x5013,offchip=x4964_oc, mctpe=TileLoad) { implicit CU => 
      val x4987_b6012_x5002 =  ScalarFIFO(name="size",size=1).wtPort(x4987_b6012_x5000_b6014_s)
      val x4987_b6011_x5002 =  ScalarFIFO(name="offset",size=1).wtPort(x4987_b6011_x5000_b6013_s)
      CU.newVout("data", x4988_x5002_data_v)
    }
    val x5012 = Pipeline(name="x5012",parent=x5013) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5004 = CounterChain(name = "x5004", ctr7)
      var stage: List[Stage] = Nil
    }
    val x5036 = StreamController(name="x5036",parent=x5909) { implicit CU => 
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5036_unit = CounterChain(name = "x5036_unit", ctr8)
    }
    val x5025_0 = Pipeline(name="x5025_0",parent=x5036) { implicit CU => 
      val x5017 = CU.temp
      val x5016 =  ScalarBuffer().wtPort(x5016_argin)
      val x4982 = CounterChain.copy("x5909", "x4982")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5025_unit = CounterChain(name = "x5025_unit", ctr9)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4982(0)), Const(4)), op=FixMul, results=List(x5017))
      Stage(operands=List(x5017, CU.load(x5016)), op=FixAdd, results=List(CU.scalarOut(x5014_b6017_x5024_b6019_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5014_b6018_x5024_b6020_s)))
    }
    val x5026 = MemoryController(name="x5026",parent=x5036,offchip=x4966_oc, mctpe=TileLoad) { implicit CU => 
      val x5014_b6018_x5026 =  ScalarFIFO(name="size",size=1).wtPort(x5014_b6018_x5024_b6020_s)
      val x5014_b6017_x5026 =  ScalarFIFO(name="offset",size=1).wtPort(x5014_b6017_x5024_b6019_s)
      CU.newVout("data", x5015_x5026_data_v)
    }
    val x5035 = Pipeline(name="x5035",parent=x5036) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5028 = CounterChain(name = "x5028", ctr10)
      var stage: List[Stage] = Nil
    }
    val x5908 = MetaPipeline(name="x5908",parent=x5909) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5039 = CounterChain(name = "x5039", ctr11)
    }
    val x5056_dsp0 = MemoryPipeline(name="x5056_dsp0",parent="x5908") { implicit CU => 
      val x5596_x5596 =  VectorFIFO(size=1).wtPort(x5056_x5596_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5587 = CounterChain.copy("x5597_0", "x5587")
      val x5056_x5784 =  SRAM(size=192,banking = Strided(1)).wtPort(x5596_x5596.readPort).rdPort(x5056_x5784_x5906_v).rdAddr(x5780(0)).wtAddr(x5587(0))
      var stage: List[Stage] = Nil
    }
    val x5057_dsp0 = MemoryPipeline(name="x5057_dsp0",parent="x5908") { implicit CU => 
      val x5608_x5608 =  VectorFIFO(size=1).wtPort(x5057_x5608_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5599 = CounterChain.copy("x5609_0", "x5599")
      val x5057_x5785 =  SRAM(size=192,banking = Strided(1)).wtPort(x5608_x5608.readPort).rdPort(x5057_x5785_x5906_v).rdAddr(x5780(0)).wtAddr(x5599(0))
      var stage: List[Stage] = Nil
    }
    val x5058_dsp0 = MemoryPipeline(name="x5058_dsp0",parent="x5908") { implicit CU => 
      val x5620_x5620 =  VectorFIFO(size=1).wtPort(x5058_x5620_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5611 = CounterChain.copy("x5621_0", "x5611")
      val x5058_x5786 =  SRAM(size=192,banking = Strided(1)).wtPort(x5620_x5620.readPort).rdPort(x5058_x5786_x5906_v).rdAddr(x5780(0)).wtAddr(x5611(0))
      var stage: List[Stage] = Nil
    }
    val x5059_dsp0 = MemoryPipeline(name="x5059_dsp0",parent="x5908") { implicit CU => 
      val x5632_x5632 =  VectorFIFO(size=1).wtPort(x5059_x5632_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5623 = CounterChain.copy("x5633_0", "x5623")
      val x5059_x5787 =  SRAM(size=192,banking = Strided(1)).wtPort(x5632_x5632.readPort).rdPort(x5059_x5787_x5906_v).rdAddr(x5780(0)).wtAddr(x5623(0))
      var stage: List[Stage] = Nil
    }
    val x5060_dsp0 = MemoryPipeline(name="x5060_dsp0",parent="x5908") { implicit CU => 
      val x5644_x5644 =  VectorFIFO(size=1).wtPort(x5060_x5644_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5635 = CounterChain.copy("x5645_0", "x5635")
      val x5060_x5788 =  SRAM(size=192,banking = Strided(1)).wtPort(x5644_x5644.readPort).rdPort(x5060_x5788_x5906_v).rdAddr(x5780(0)).wtAddr(x5635(0))
      var stage: List[Stage] = Nil
    }
    val x5061_dsp0 = MemoryPipeline(name="x5061_dsp0",parent="x5908") { implicit CU => 
      val x5656_x5656 =  VectorFIFO(size=1).wtPort(x5061_x5656_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5647 = CounterChain.copy("x5657_0", "x5647")
      val x5061_x5789 =  SRAM(size=192,banking = Strided(1)).wtPort(x5656_x5656.readPort).rdPort(x5061_x5789_x5906_v).rdAddr(x5780(0)).wtAddr(x5647(0))
      var stage: List[Stage] = Nil
    }
    val x5062_dsp0 = MemoryPipeline(name="x5062_dsp0",parent="x5908") { implicit CU => 
      val x5668_x5668 =  VectorFIFO(size=1).wtPort(x5062_x5668_v)
      val x5659 = CounterChain.copy("x5669_0", "x5659")
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5062_x5790 =  SRAM(size=192,banking = Strided(1)).wtPort(x5668_x5668.readPort).rdPort(x5062_x5790_x5906_v).rdAddr(x5780(0)).wtAddr(x5659(0))
      var stage: List[Stage] = Nil
    }
    val x5063_dsp0 = MemoryPipeline(name="x5063_dsp0",parent="x5908") { implicit CU => 
      val x5680_x5680 =  VectorFIFO(size=1).wtPort(x5063_x5680_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5671 = CounterChain.copy("x5681_0", "x5671")
      val x5063_x5791 =  SRAM(size=192,banking = Strided(1)).wtPort(x5680_x5680.readPort).rdPort(x5063_x5791_x5906_v).rdAddr(x5780(0)).wtAddr(x5671(0))
      var stage: List[Stage] = Nil
    }
    val x5064_dsp0 = MemoryPipeline(name="x5064_dsp0",parent="x5908") { implicit CU => 
      val x5692_x5692 =  VectorFIFO(size=1).wtPort(x5064_x5692_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5683 = CounterChain.copy("x5693_0", "x5683")
      val x5064_x5792 =  SRAM(size=192,banking = Strided(1)).wtPort(x5692_x5692.readPort).rdPort(x5064_x5792_x5906_v).rdAddr(x5780(0)).wtAddr(x5683(0))
      var stage: List[Stage] = Nil
    }
    val x5065_dsp0 = MemoryPipeline(name="x5065_dsp0",parent="x5908") { implicit CU => 
      val x5704_x5704 =  VectorFIFO(size=1).wtPort(x5065_x5704_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5695 = CounterChain.copy("x5705_0", "x5695")
      val x5065_x5793 =  SRAM(size=192,banking = Strided(1)).wtPort(x5704_x5704.readPort).rdPort(x5065_x5793_x5906_v).rdAddr(x5780(0)).wtAddr(x5695(0))
      var stage: List[Stage] = Nil
    }
    val x5066_dsp0 = MemoryPipeline(name="x5066_dsp0",parent="x5908") { implicit CU => 
      val x5716_x5716 =  VectorFIFO(size=1).wtPort(x5066_x5716_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5707 = CounterChain.copy("x5717_0", "x5707")
      val x5066_x5794 =  SRAM(size=192,banking = Strided(1)).wtPort(x5716_x5716.readPort).rdPort(x5066_x5794_x5906_v).rdAddr(x5780(0)).wtAddr(x5707(0))
      var stage: List[Stage] = Nil
    }
    val x5067_dsp0 = MemoryPipeline(name="x5067_dsp0",parent="x5908") { implicit CU => 
      val x5728_x5728 =  VectorFIFO(size=1).wtPort(x5067_x5728_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5719 = CounterChain.copy("x5729_0", "x5719")
      val x5067_x5795 =  SRAM(size=192,banking = Strided(1)).wtPort(x5728_x5728.readPort).rdPort(x5067_x5795_x5906_v).rdAddr(x5780(0)).wtAddr(x5719(0))
      var stage: List[Stage] = Nil
    }
    val x5068_dsp0 = MemoryPipeline(name="x5068_dsp0",parent="x5908") { implicit CU => 
      val x5740_x5740 =  VectorFIFO(size=1).wtPort(x5068_x5740_v)
      val x5731 = CounterChain.copy("x5741_0", "x5731")
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5068_x5796 =  SRAM(size=192,banking = Strided(1)).wtPort(x5740_x5740.readPort).rdPort(x5068_x5796_x5906_v).rdAddr(x5780(0)).wtAddr(x5731(0))
      var stage: List[Stage] = Nil
    }
    val x5069_dsp0 = MemoryPipeline(name="x5069_dsp0",parent="x5908") { implicit CU => 
      val x5752_x5752 =  VectorFIFO(size=1).wtPort(x5069_x5752_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5743 = CounterChain.copy("x5753_0", "x5743")
      val x5069_x5797 =  SRAM(size=192,banking = Strided(1)).wtPort(x5752_x5752.readPort).rdPort(x5069_x5797_x5906_v).rdAddr(x5780(0)).wtAddr(x5743(0))
      var stage: List[Stage] = Nil
    }
    val x5070_dsp0 = MemoryPipeline(name="x5070_dsp0",parent="x5908") { implicit CU => 
      val x5764_x5764 =  VectorFIFO(size=1).wtPort(x5070_x5764_v)
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5755 = CounterChain.copy("x5765_0", "x5755")
      val x5070_x5798 =  SRAM(size=192,banking = Strided(1)).wtPort(x5764_x5764.readPort).rdPort(x5070_x5798_x5906_v).rdAddr(x5780(0)).wtAddr(x5755(0))
      var stage: List[Stage] = Nil
    }
    val x5071_dsp0 = MemoryPipeline(name="x5071_dsp0",parent="x5908") { implicit CU => 
      val x5776_x5776 =  VectorFIFO(size=1).wtPort(x5071_x5776_v)
      val x5767 = CounterChain.copy("x5777_0", "x5767")
      val x5780 = CounterChain.copy("x5906", "x5780")
      val x5071_x5799 =  SRAM(size=192,banking = Strided(1)).wtPort(x5776_x5776.readPort).rdPort(x5071_x5799_x5906_v).rdAddr(x5780(0)).wtAddr(x5767(0))
      var stage: List[Stage] = Nil
    }
    val x5106_0 = Pipeline(name="x5106_0",parent=x5908) { implicit CU => 
      val x5095_x5095 =  VectorFIFO(size=1).wtPort(x4973_x5095_x5106_v)
      val x5094_x5094 =  VectorFIFO(size=1).wtPort(x4983_x5094_x5106_v)
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5089 = CounterChain(name = "x5089", ctr12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5094_x5094), CU.load(x5095_x5095)), op=FltMul, results=List(CU.reduce))
      val (_, rr58721) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58721), op=Bypass, results=List(CU.scalarOut(x5072_x5104_s)))
    }
    val x5125_0 = Pipeline(name="x5125_0",parent=x5908) { implicit CU => 
      val x5113_x5113 =  VectorFIFO(size=1).wtPort(x4983_x5113_x5125_v)
      val x5114_x5114 =  VectorFIFO(size=1).wtPort(x4973_x5114_x5125_v)
      val ctr13 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5108 = CounterChain(name = "x5108", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5113_x5113), CU.load(x5114_x5114)), op=FltMul, results=List(CU.reduce))
      val (_, rr58732) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58732), op=Bypass, results=List(CU.scalarOut(x5073_x5123_s)))
    }
    val x5144_0 = Pipeline(name="x5144_0",parent=x5908) { implicit CU => 
      val x5133_x5133 =  VectorFIFO(size=1).wtPort(x4973_x5133_x5144_v)
      val x5132_x5132 =  VectorFIFO(size=1).wtPort(x4983_x5132_x5144_v)
      val ctr14 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5127 = CounterChain(name = "x5127", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5132_x5132), CU.load(x5133_x5133)), op=FltMul, results=List(CU.reduce))
      val (_, rr58743) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58743), op=Bypass, results=List(CU.scalarOut(x5074_x5142_s)))
    }
    val x5163_0 = Pipeline(name="x5163_0",parent=x5908) { implicit CU => 
      val x5152_x5152 =  VectorFIFO(size=1).wtPort(x4973_x5152_x5163_v)
      val x5151_x5151 =  VectorFIFO(size=1).wtPort(x4983_x5151_x5163_v)
      val ctr15 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5146 = CounterChain(name = "x5146", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5151_x5151), CU.load(x5152_x5152)), op=FltMul, results=List(CU.reduce))
      val (_, rr58754) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58754), op=Bypass, results=List(CU.scalarOut(x5075_x5161_s)))
    }
    val x5182_0 = Pipeline(name="x5182_0",parent=x5908) { implicit CU => 
      val x5170_x5170 =  VectorFIFO(size=1).wtPort(x4983_x5170_x5182_v)
      val x5171_x5171 =  VectorFIFO(size=1).wtPort(x4973_x5171_x5182_v)
      val ctr16 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5165 = CounterChain(name = "x5165", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5170_x5170), CU.load(x5171_x5171)), op=FltMul, results=List(CU.reduce))
      val (_, rr58765) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58765), op=Bypass, results=List(CU.scalarOut(x5076_x5180_s)))
    }
    val x5201_0 = Pipeline(name="x5201_0",parent=x5908) { implicit CU => 
      val x5190_x5190 =  VectorFIFO(size=1).wtPort(x4973_x5190_x5201_v)
      val x5189_x5189 =  VectorFIFO(size=1).wtPort(x4983_x5189_x5201_v)
      val ctr17 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5184 = CounterChain(name = "x5184", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5189_x5189), CU.load(x5190_x5190)), op=FltMul, results=List(CU.reduce))
      val (_, rr58776) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58776), op=Bypass, results=List(CU.scalarOut(x5077_x5199_s)))
    }
    val x5220_0 = Pipeline(name="x5220_0",parent=x5908) { implicit CU => 
      val x5209_x5209 =  VectorFIFO(size=1).wtPort(x4973_x5209_x5220_v)
      val x5208_x5208 =  VectorFIFO(size=1).wtPort(x4983_x5208_x5220_v)
      val ctr18 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5203 = CounterChain(name = "x5203", ctr18)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5208_x5208), CU.load(x5209_x5209)), op=FltMul, results=List(CU.reduce))
      val (_, rr58787) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58787), op=Bypass, results=List(CU.scalarOut(x5078_x5218_s)))
    }
    val x5239_0 = Pipeline(name="x5239_0",parent=x5908) { implicit CU => 
      val x5227_x5227 =  VectorFIFO(size=1).wtPort(x4983_x5227_x5239_v)
      val x5228_x5228 =  VectorFIFO(size=1).wtPort(x4973_x5228_x5239_v)
      val ctr19 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5222 = CounterChain(name = "x5222", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5227_x5227), CU.load(x5228_x5228)), op=FltMul, results=List(CU.reduce))
      val (_, rr58798) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58798), op=Bypass, results=List(CU.scalarOut(x5079_x5237_s)))
    }
    val x5258_0 = Pipeline(name="x5258_0",parent=x5908) { implicit CU => 
      val x5247_x5247 =  VectorFIFO(size=1).wtPort(x4973_x5247_x5258_v)
      val x5246_x5246 =  VectorFIFO(size=1).wtPort(x4983_x5246_x5258_v)
      val ctr20 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5241 = CounterChain(name = "x5241", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5246_x5246), CU.load(x5247_x5247)), op=FltMul, results=List(CU.reduce))
      val (_, rr58809) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58809), op=Bypass, results=List(CU.scalarOut(x5080_x5256_s)))
    }
    val x5277_0 = Pipeline(name="x5277_0",parent=x5908) { implicit CU => 
      val x5265_x5265 =  VectorFIFO(size=1).wtPort(x4983_x5265_x5277_v)
      val x5266_x5266 =  VectorFIFO(size=1).wtPort(x4973_x5266_x5277_v)
      val ctr21 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5260 = CounterChain(name = "x5260", ctr21)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5265_x5265), CU.load(x5266_x5266)), op=FltMul, results=List(CU.reduce))
      val (_, rr58820) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58820), op=Bypass, results=List(CU.scalarOut(x5081_x5275_s)))
    }
    val x5296_0 = Pipeline(name="x5296_0",parent=x5908) { implicit CU => 
      val x5285_x5285 =  VectorFIFO(size=1).wtPort(x4973_x5285_x5296_v)
      val x5284_x5284 =  VectorFIFO(size=1).wtPort(x4983_x5284_x5296_v)
      val ctr22 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5279 = CounterChain(name = "x5279", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5284_x5284), CU.load(x5285_x5285)), op=FltMul, results=List(CU.reduce))
      val (_, rr58831) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58831), op=Bypass, results=List(CU.scalarOut(x5082_x5294_s)))
    }
    val x5315_0 = Pipeline(name="x5315_0",parent=x5908) { implicit CU => 
      val x5304_x5304 =  VectorFIFO(size=1).wtPort(x4973_x5304_x5315_v)
      val x5303_x5303 =  VectorFIFO(size=1).wtPort(x4983_x5303_x5315_v)
      val ctr23 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5298 = CounterChain(name = "x5298", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5303_x5303), CU.load(x5304_x5304)), op=FltMul, results=List(CU.reduce))
      val (_, rr58842) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58842), op=Bypass, results=List(CU.scalarOut(x5083_x5313_s)))
    }
    val x5334_0 = Pipeline(name="x5334_0",parent=x5908) { implicit CU => 
      val x5323_x5323 =  VectorFIFO(size=1).wtPort(x4973_x5323_x5334_v)
      val x5322_x5322 =  VectorFIFO(size=1).wtPort(x4983_x5322_x5334_v)
      val ctr24 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5317 = CounterChain(name = "x5317", ctr24)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5322_x5322), CU.load(x5323_x5323)), op=FltMul, results=List(CU.reduce))
      val (_, rr58853) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58853), op=Bypass, results=List(CU.scalarOut(x5084_x5332_s)))
    }
    val x5353_0 = Pipeline(name="x5353_0",parent=x5908) { implicit CU => 
      val x5341_x5341 =  VectorFIFO(size=1).wtPort(x4983_x5341_x5353_v)
      val x5342_x5342 =  VectorFIFO(size=1).wtPort(x4973_x5342_x5353_v)
      val ctr25 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5336 = CounterChain(name = "x5336", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5341_x5341), CU.load(x5342_x5342)), op=FltMul, results=List(CU.reduce))
      val (_, rr58864) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58864), op=Bypass, results=List(CU.scalarOut(x5085_x5351_s)))
    }
    val x5372_0 = Pipeline(name="x5372_0",parent=x5908) { implicit CU => 
      val x5361_x5361 =  VectorFIFO(size=1).wtPort(x4973_x5361_x5372_v)
      val x5360_x5360 =  VectorFIFO(size=1).wtPort(x4983_x5360_x5372_v)
      val ctr26 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5355 = CounterChain(name = "x5355", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5360_x5360), CU.load(x5361_x5361)), op=FltMul, results=List(CU.reduce))
      val (_, rr58875) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58875), op=Bypass, results=List(CU.scalarOut(x5086_x5370_s)))
    }
    val x5391_0 = Pipeline(name="x5391_0",parent=x5908) { implicit CU => 
      val x5379_x5379 =  VectorFIFO(size=1).wtPort(x4983_x5379_x5391_v)
      val x5380_x5380 =  VectorFIFO(size=1).wtPort(x4973_x5380_x5391_v)
      val ctr27 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5374 = CounterChain(name = "x5374", ctr27)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5379_x5379), CU.load(x5380_x5380)), op=FltMul, results=List(CU.reduce))
      val (_, rr58886) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr58886), op=Bypass, results=List(CU.scalarOut(x5087_x5389_s)))
    }
    val x5404_0 = Pipeline(name="x5404_0",parent=x5908) { implicit CU => 
      val x5401 = CU.temp
      val x5398 = CU.temp
      val x5399 = CU.temp
      val x5400 = CU.temp
      val x5396_x5396 =  VectorFIFO(size=1).wtPort(x4984_x5396_x5404_v)
      val x5072_x5397 =  ScalarBuffer().wtPort(x5072_x5104_s)
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5404_unit = CounterChain(name = "x5404_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5072_x5397)), op=FltNeg, results=List(x5398))
      Stage(operands=List(x5398), op=FltExp, results=List(x5399))
      Stage(operands=List(x5399, Const(1)), op=FltAdd, results=List(x5400))
      Stage(operands=List(Const(1), x5400), op=FltDiv, results=List(x5401))
      Stage(operands=List(CU.load(x5396_x5396), x5401), op=FltSub, results=List(CU.scalarOut(x5040_x5403_s)))
    }
    val x5416_0 = Pipeline(name="x5416_0",parent=x5908) { implicit CU => 
      val x5410 = CU.temp
      val x5413 = CU.temp
      val x5412 = CU.temp
      val x5411 = CU.temp
      val x5408_x5408 =  VectorFIFO(size=1).wtPort(x4984_x5408_x5416_v)
      val x5073_x5409 =  ScalarBuffer().wtPort(x5073_x5123_s)
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5416_unit = CounterChain(name = "x5416_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5073_x5409)), op=FltNeg, results=List(x5410))
      Stage(operands=List(x5410), op=FltExp, results=List(x5411))
      Stage(operands=List(x5411, Const(1)), op=FltAdd, results=List(x5412))
      Stage(operands=List(Const(1), x5412), op=FltDiv, results=List(x5413))
      Stage(operands=List(CU.load(x5408_x5408), x5413), op=FltSub, results=List(CU.scalarOut(x5041_x5415_s)))
    }
    val x5428_0 = Pipeline(name="x5428_0",parent=x5908) { implicit CU => 
      val x5423 = CU.temp
      val x5424 = CU.temp
      val x5425 = CU.temp
      val x5422 = CU.temp
      val x5074_x5421 =  ScalarBuffer().wtPort(x5074_x5142_s)
      val x5420_x5420 =  VectorFIFO(size=1).wtPort(x4984_x5420_x5428_v)
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5428_unit = CounterChain(name = "x5428_unit", ctr30)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5074_x5421)), op=FltNeg, results=List(x5422))
      Stage(operands=List(x5422), op=FltExp, results=List(x5423))
      Stage(operands=List(x5423, Const(1)), op=FltAdd, results=List(x5424))
      Stage(operands=List(Const(1), x5424), op=FltDiv, results=List(x5425))
      Stage(operands=List(CU.load(x5420_x5420), x5425), op=FltSub, results=List(CU.scalarOut(x5042_x5427_s)))
    }
    val x5440_0 = Pipeline(name="x5440_0",parent=x5908) { implicit CU => 
      val x5435 = CU.temp
      val x5436 = CU.temp
      val x5437 = CU.temp
      val x5434 = CU.temp
      val x5432_x5432 =  VectorFIFO(size=1).wtPort(x4984_x5432_x5440_v)
      val x5075_x5433 =  ScalarBuffer().wtPort(x5075_x5161_s)
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5440_unit = CounterChain(name = "x5440_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5075_x5433)), op=FltNeg, results=List(x5434))
      Stage(operands=List(x5434), op=FltExp, results=List(x5435))
      Stage(operands=List(x5435, Const(1)), op=FltAdd, results=List(x5436))
      Stage(operands=List(Const(1), x5436), op=FltDiv, results=List(x5437))
      Stage(operands=List(CU.load(x5432_x5432), x5437), op=FltSub, results=List(CU.scalarOut(x5043_x5439_s)))
    }
    val x5452_0 = Pipeline(name="x5452_0",parent=x5908) { implicit CU => 
      val x5446 = CU.temp
      val x5448 = CU.temp
      val x5449 = CU.temp
      val x5447 = CU.temp
      val x5444_x5444 =  VectorFIFO(size=1).wtPort(x4984_x5444_x5452_v)
      val x5076_x5445 =  ScalarBuffer().wtPort(x5076_x5180_s)
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5452_unit = CounterChain(name = "x5452_unit", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5076_x5445)), op=FltNeg, results=List(x5446))
      Stage(operands=List(x5446), op=FltExp, results=List(x5447))
      Stage(operands=List(x5447, Const(1)), op=FltAdd, results=List(x5448))
      Stage(operands=List(Const(1), x5448), op=FltDiv, results=List(x5449))
      Stage(operands=List(CU.load(x5444_x5444), x5449), op=FltSub, results=List(CU.scalarOut(x5044_x5451_s)))
    }
    val x5464_0 = Pipeline(name="x5464_0",parent=x5908) { implicit CU => 
      val x5458 = CU.temp
      val x5459 = CU.temp
      val x5460 = CU.temp
      val x5461 = CU.temp
      val x5077_x5457 =  ScalarBuffer().wtPort(x5077_x5199_s)
      val x5456_x5456 =  VectorFIFO(size=1).wtPort(x4984_x5456_x5464_v)
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5464_unit = CounterChain(name = "x5464_unit", ctr33)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5077_x5457)), op=FltNeg, results=List(x5458))
      Stage(operands=List(x5458), op=FltExp, results=List(x5459))
      Stage(operands=List(x5459, Const(1)), op=FltAdd, results=List(x5460))
      Stage(operands=List(Const(1), x5460), op=FltDiv, results=List(x5461))
      Stage(operands=List(CU.load(x5456_x5456), x5461), op=FltSub, results=List(CU.scalarOut(x5045_x5463_s)))
    }
    val x5476_0 = Pipeline(name="x5476_0",parent=x5908) { implicit CU => 
      val x5473 = CU.temp
      val x5470 = CU.temp
      val x5472 = CU.temp
      val x5471 = CU.temp
      val x5468_x5468 =  VectorFIFO(size=1).wtPort(x4984_x5468_x5476_v)
      val x5078_x5469 =  ScalarBuffer().wtPort(x5078_x5218_s)
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5476_unit = CounterChain(name = "x5476_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5078_x5469)), op=FltNeg, results=List(x5470))
      Stage(operands=List(x5470), op=FltExp, results=List(x5471))
      Stage(operands=List(x5471, Const(1)), op=FltAdd, results=List(x5472))
      Stage(operands=List(Const(1), x5472), op=FltDiv, results=List(x5473))
      Stage(operands=List(CU.load(x5468_x5468), x5473), op=FltSub, results=List(CU.scalarOut(x5046_x5475_s)))
    }
    val x5488_0 = Pipeline(name="x5488_0",parent=x5908) { implicit CU => 
      val x5485 = CU.temp
      val x5482 = CU.temp
      val x5484 = CU.temp
      val x5483 = CU.temp
      val x5480_x5480 =  VectorFIFO(size=1).wtPort(x4984_x5480_x5488_v)
      val x5079_x5481 =  ScalarBuffer().wtPort(x5079_x5237_s)
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5488_unit = CounterChain(name = "x5488_unit", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5079_x5481)), op=FltNeg, results=List(x5482))
      Stage(operands=List(x5482), op=FltExp, results=List(x5483))
      Stage(operands=List(x5483, Const(1)), op=FltAdd, results=List(x5484))
      Stage(operands=List(Const(1), x5484), op=FltDiv, results=List(x5485))
      Stage(operands=List(CU.load(x5480_x5480), x5485), op=FltSub, results=List(CU.scalarOut(x5047_x5487_s)))
    }
    val x5500_0 = Pipeline(name="x5500_0",parent=x5908) { implicit CU => 
      val x5494 = CU.temp
      val x5496 = CU.temp
      val x5495 = CU.temp
      val x5497 = CU.temp
      val x5080_x5493 =  ScalarBuffer().wtPort(x5080_x5256_s)
      val x5492_x5492 =  VectorFIFO(size=1).wtPort(x4984_x5492_x5500_v)
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5500_unit = CounterChain(name = "x5500_unit", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5080_x5493)), op=FltNeg, results=List(x5494))
      Stage(operands=List(x5494), op=FltExp, results=List(x5495))
      Stage(operands=List(x5495, Const(1)), op=FltAdd, results=List(x5496))
      Stage(operands=List(Const(1), x5496), op=FltDiv, results=List(x5497))
      Stage(operands=List(CU.load(x5492_x5492), x5497), op=FltSub, results=List(CU.scalarOut(x5048_x5499_s)))
    }
    val x5512_0 = Pipeline(name="x5512_0",parent=x5908) { implicit CU => 
      val x5507 = CU.temp
      val x5506 = CU.temp
      val x5509 = CU.temp
      val x5508 = CU.temp
      val x5504_x5504 =  VectorFIFO(size=1).wtPort(x4984_x5504_x5512_v)
      val x5081_x5505 =  ScalarBuffer().wtPort(x5081_x5275_s)
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5512_unit = CounterChain(name = "x5512_unit", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5081_x5505)), op=FltNeg, results=List(x5506))
      Stage(operands=List(x5506), op=FltExp, results=List(x5507))
      Stage(operands=List(x5507, Const(1)), op=FltAdd, results=List(x5508))
      Stage(operands=List(Const(1), x5508), op=FltDiv, results=List(x5509))
      Stage(operands=List(CU.load(x5504_x5504), x5509), op=FltSub, results=List(CU.scalarOut(x5049_x5511_s)))
    }
    val x5524_0 = Pipeline(name="x5524_0",parent=x5908) { implicit CU => 
      val x5521 = CU.temp
      val x5518 = CU.temp
      val x5520 = CU.temp
      val x5519 = CU.temp
      val x5516_x5516 =  VectorFIFO(size=1).wtPort(x4984_x5516_x5524_v)
      val x5082_x5517 =  ScalarBuffer().wtPort(x5082_x5294_s)
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5524_unit = CounterChain(name = "x5524_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5082_x5517)), op=FltNeg, results=List(x5518))
      Stage(operands=List(x5518), op=FltExp, results=List(x5519))
      Stage(operands=List(x5519, Const(1)), op=FltAdd, results=List(x5520))
      Stage(operands=List(Const(1), x5520), op=FltDiv, results=List(x5521))
      Stage(operands=List(CU.load(x5516_x5516), x5521), op=FltSub, results=List(CU.scalarOut(x5050_x5523_s)))
    }
    val x5536_0 = Pipeline(name="x5536_0",parent=x5908) { implicit CU => 
      val x5530 = CU.temp
      val x5533 = CU.temp
      val x5532 = CU.temp
      val x5531 = CU.temp
      val x5083_x5529 =  ScalarBuffer().wtPort(x5083_x5313_s)
      val x5528_x5528 =  VectorFIFO(size=1).wtPort(x4984_x5528_x5536_v)
      val ctr39 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5536_unit = CounterChain(name = "x5536_unit", ctr39)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5083_x5529)), op=FltNeg, results=List(x5530))
      Stage(operands=List(x5530), op=FltExp, results=List(x5531))
      Stage(operands=List(x5531, Const(1)), op=FltAdd, results=List(x5532))
      Stage(operands=List(Const(1), x5532), op=FltDiv, results=List(x5533))
      Stage(operands=List(CU.load(x5528_x5528), x5533), op=FltSub, results=List(CU.scalarOut(x5051_x5535_s)))
    }
    val x5548_0 = Pipeline(name="x5548_0",parent=x5908) { implicit CU => 
      val x5545 = CU.temp
      val x5543 = CU.temp
      val x5544 = CU.temp
      val x5542 = CU.temp
      val x5540_x5540 =  VectorFIFO(size=1).wtPort(x4984_x5540_x5548_v)
      val x5084_x5541 =  ScalarBuffer().wtPort(x5084_x5332_s)
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5548_unit = CounterChain(name = "x5548_unit", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5084_x5541)), op=FltNeg, results=List(x5542))
      Stage(operands=List(x5542), op=FltExp, results=List(x5543))
      Stage(operands=List(x5543, Const(1)), op=FltAdd, results=List(x5544))
      Stage(operands=List(Const(1), x5544), op=FltDiv, results=List(x5545))
      Stage(operands=List(CU.load(x5540_x5540), x5545), op=FltSub, results=List(CU.scalarOut(x5052_x5547_s)))
    }
    val x5560_0 = Pipeline(name="x5560_0",parent=x5908) { implicit CU => 
      val x5554 = CU.temp
      val x5557 = CU.temp
      val x5556 = CU.temp
      val x5555 = CU.temp
      val x5552_x5552 =  VectorFIFO(size=1).wtPort(x4984_x5552_x5560_v)
      val x5085_x5553 =  ScalarBuffer().wtPort(x5085_x5351_s)
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5560_unit = CounterChain(name = "x5560_unit", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5085_x5553)), op=FltNeg, results=List(x5554))
      Stage(operands=List(x5554), op=FltExp, results=List(x5555))
      Stage(operands=List(x5555, Const(1)), op=FltAdd, results=List(x5556))
      Stage(operands=List(Const(1), x5556), op=FltDiv, results=List(x5557))
      Stage(operands=List(CU.load(x5552_x5552), x5557), op=FltSub, results=List(CU.scalarOut(x5053_x5559_s)))
    }
    val x5572_0 = Pipeline(name="x5572_0",parent=x5908) { implicit CU => 
      val x5569 = CU.temp
      val x5567 = CU.temp
      val x5566 = CU.temp
      val x5568 = CU.temp
      val x5086_x5565 =  ScalarBuffer().wtPort(x5086_x5370_s)
      val x5564_x5564 =  VectorFIFO(size=1).wtPort(x4984_x5564_x5572_v)
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5572_unit = CounterChain(name = "x5572_unit", ctr42)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5086_x5565)), op=FltNeg, results=List(x5566))
      Stage(operands=List(x5566), op=FltExp, results=List(x5567))
      Stage(operands=List(x5567, Const(1)), op=FltAdd, results=List(x5568))
      Stage(operands=List(Const(1), x5568), op=FltDiv, results=List(x5569))
      Stage(operands=List(CU.load(x5564_x5564), x5569), op=FltSub, results=List(CU.scalarOut(x5054_x5571_s)))
    }
    val x5584_0 = Pipeline(name="x5584_0",parent=x5908) { implicit CU => 
      val x5581 = CU.temp
      val x5578 = CU.temp
      val x5580 = CU.temp
      val x5579 = CU.temp
      val x5576_x5576 =  VectorFIFO(size=1).wtPort(x4984_x5576_x5584_v)
      val x5087_x5577 =  ScalarBuffer().wtPort(x5087_x5389_s)
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5584_unit = CounterChain(name = "x5584_unit", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5087_x5577)), op=FltNeg, results=List(x5578))
      Stage(operands=List(x5578), op=FltExp, results=List(x5579))
      Stage(operands=List(x5579, Const(1)), op=FltAdd, results=List(x5580))
      Stage(operands=List(Const(1), x5580), op=FltDiv, results=List(x5581))
      Stage(operands=List(CU.load(x5576_x5576), x5581), op=FltSub, results=List(CU.scalarOut(x5055_x5583_s)))
    }
    val x5597_0 = Pipeline(name="x5597_0",parent=x5908) { implicit CU => 
      val x5592_x5592 =  VectorFIFO(size=1).wtPort(x4983_x5592_x5597_v)
      val x5040_x5593 =  ScalarBuffer().wtPort(x5040_x5403_s)
      val ctr44 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5587 = CounterChain(name = "x5587", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5592_x5592), CU.load(x5040_x5593)), op=FltSub, results=List(CU.vecOut(x5056_x5596_v)))
    }
    val x5609_0 = Pipeline(name="x5609_0",parent=x5908) { implicit CU => 
      val x5604_x5604 =  VectorFIFO(size=1).wtPort(x4983_x5604_x5609_v)
      val x5041_x5605 =  ScalarBuffer().wtPort(x5041_x5415_s)
      val ctr45 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5599 = CounterChain(name = "x5599", ctr45)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5604_x5604), CU.load(x5041_x5605)), op=FltSub, results=List(CU.vecOut(x5057_x5608_v)))
    }
    val x5621_0 = Pipeline(name="x5621_0",parent=x5908) { implicit CU => 
      val x5616_x5616 =  VectorFIFO(size=1).wtPort(x4983_x5616_x5621_v)
      val x5042_x5617 =  ScalarBuffer().wtPort(x5042_x5427_s)
      val ctr46 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5611 = CounterChain(name = "x5611", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5616_x5616), CU.load(x5042_x5617)), op=FltSub, results=List(CU.vecOut(x5058_x5620_v)))
    }
    val x5633_0 = Pipeline(name="x5633_0",parent=x5908) { implicit CU => 
      val x5628_x5628 =  VectorFIFO(size=1).wtPort(x4983_x5628_x5633_v)
      val x5043_x5629 =  ScalarBuffer().wtPort(x5043_x5439_s)
      val ctr47 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5623 = CounterChain(name = "x5623", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5628_x5628), CU.load(x5043_x5629)), op=FltSub, results=List(CU.vecOut(x5059_x5632_v)))
    }
    val x5645_0 = Pipeline(name="x5645_0",parent=x5908) { implicit CU => 
      val x5044_x5641 =  ScalarBuffer().wtPort(x5044_x5451_s)
      val x5640_x5640 =  VectorFIFO(size=1).wtPort(x4983_x5640_x5645_v)
      val ctr48 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5635 = CounterChain(name = "x5635", ctr48)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5640_x5640), CU.load(x5044_x5641)), op=FltSub, results=List(CU.vecOut(x5060_x5644_v)))
    }
    val x5657_0 = Pipeline(name="x5657_0",parent=x5908) { implicit CU => 
      val x5045_x5653 =  ScalarBuffer().wtPort(x5045_x5463_s)
      val x5652_x5652 =  VectorFIFO(size=1).wtPort(x4983_x5652_x5657_v)
      val ctr49 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5647 = CounterChain(name = "x5647", ctr49)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5652_x5652), CU.load(x5045_x5653)), op=FltSub, results=List(CU.vecOut(x5061_x5656_v)))
    }
    val x5669_0 = Pipeline(name="x5669_0",parent=x5908) { implicit CU => 
      val x5664_x5664 =  VectorFIFO(size=1).wtPort(x4983_x5664_x5669_v)
      val x5046_x5665 =  ScalarBuffer().wtPort(x5046_x5475_s)
      val ctr50 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5659 = CounterChain(name = "x5659", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5664_x5664), CU.load(x5046_x5665)), op=FltSub, results=List(CU.vecOut(x5062_x5668_v)))
    }
    val x5681_0 = Pipeline(name="x5681_0",parent=x5908) { implicit CU => 
      val x5047_x5677 =  ScalarBuffer().wtPort(x5047_x5487_s)
      val x5676_x5676 =  VectorFIFO(size=1).wtPort(x4983_x5676_x5681_v)
      val ctr51 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5671 = CounterChain(name = "x5671", ctr51)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5676_x5676), CU.load(x5047_x5677)), op=FltSub, results=List(CU.vecOut(x5063_x5680_v)))
    }
    val x5693_0 = Pipeline(name="x5693_0",parent=x5908) { implicit CU => 
      val x5048_x5689 =  ScalarBuffer().wtPort(x5048_x5499_s)
      val x5688_x5688 =  VectorFIFO(size=1).wtPort(x4983_x5688_x5693_v)
      val ctr52 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5683 = CounterChain(name = "x5683", ctr52)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5688_x5688), CU.load(x5048_x5689)), op=FltSub, results=List(CU.vecOut(x5064_x5692_v)))
    }
    val x5705_0 = Pipeline(name="x5705_0",parent=x5908) { implicit CU => 
      val x5049_x5701 =  ScalarBuffer().wtPort(x5049_x5511_s)
      val x5700_x5700 =  VectorFIFO(size=1).wtPort(x4983_x5700_x5705_v)
      val ctr53 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5695 = CounterChain(name = "x5695", ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5700_x5700), CU.load(x5049_x5701)), op=FltSub, results=List(CU.vecOut(x5065_x5704_v)))
    }
    val x5717_0 = Pipeline(name="x5717_0",parent=x5908) { implicit CU => 
      val x5050_x5713 =  ScalarBuffer().wtPort(x5050_x5523_s)
      val x5712_x5712 =  VectorFIFO(size=1).wtPort(x4983_x5712_x5717_v)
      val ctr54 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5707 = CounterChain(name = "x5707", ctr54)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5712_x5712), CU.load(x5050_x5713)), op=FltSub, results=List(CU.vecOut(x5066_x5716_v)))
    }
    val x5729_0 = Pipeline(name="x5729_0",parent=x5908) { implicit CU => 
      val x5724_x5724 =  VectorFIFO(size=1).wtPort(x4983_x5724_x5729_v)
      val x5051_x5725 =  ScalarBuffer().wtPort(x5051_x5535_s)
      val ctr55 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5719 = CounterChain(name = "x5719", ctr55)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5724_x5724), CU.load(x5051_x5725)), op=FltSub, results=List(CU.vecOut(x5067_x5728_v)))
    }
    val x5741_0 = Pipeline(name="x5741_0",parent=x5908) { implicit CU => 
      val x5052_x5737 =  ScalarBuffer().wtPort(x5052_x5547_s)
      val x5736_x5736 =  VectorFIFO(size=1).wtPort(x4983_x5736_x5741_v)
      val ctr56 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5731 = CounterChain(name = "x5731", ctr56)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5736_x5736), CU.load(x5052_x5737)), op=FltSub, results=List(CU.vecOut(x5068_x5740_v)))
    }
    val x5753_0 = Pipeline(name="x5753_0",parent=x5908) { implicit CU => 
      val x5053_x5749 =  ScalarBuffer().wtPort(x5053_x5559_s)
      val x5748_x5748 =  VectorFIFO(size=1).wtPort(x4983_x5748_x5753_v)
      val ctr57 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5743 = CounterChain(name = "x5743", ctr57)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5748_x5748), CU.load(x5053_x5749)), op=FltSub, results=List(CU.vecOut(x5069_x5752_v)))
    }
    val x5765_0 = Pipeline(name="x5765_0",parent=x5908) { implicit CU => 
      val x5760_x5760 =  VectorFIFO(size=1).wtPort(x4983_x5760_x5765_v)
      val x5054_x5761 =  ScalarBuffer().wtPort(x5054_x5571_s)
      val ctr58 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5755 = CounterChain(name = "x5755", ctr58)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5760_x5760), CU.load(x5054_x5761)), op=FltSub, results=List(CU.vecOut(x5070_x5764_v)))
    }
    val x5777_0 = Pipeline(name="x5777_0",parent=x5908) { implicit CU => 
      val x5055_x5773 =  ScalarBuffer().wtPort(x5055_x5583_s)
      val x5772_x5772 =  VectorFIFO(size=1).wtPort(x4983_x5772_x5777_v)
      val ctr59 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5767 = CounterChain(name = "x5767", ctr59)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5772_x5772), CU.load(x5055_x5773)), op=FltSub, results=List(CU.vecOut(x5071_x5776_v)))
    }
    val x5906 = StreamController(name="x5906",parent=x5908) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x5780 = CounterChain(name = "x5780", ctr60)
    }
    val x5906_0 = Pipeline(name="x5906_0",parent=x5906) { implicit CU => 
      val x5807 = CU.temp
      val x5818 = CU.temp
      val x5786_x5786 =  VectorFIFO(size=1).wtPort(x5058_x5786_x5906_v)
      val x5785_x5785 =  VectorFIFO(size=1).wtPort(x5057_x5785_x5906_v)
      val x5787_x5787 =  VectorFIFO(size=1).wtPort(x5059_x5787_x5906_v)
      val x5784_x5784 =  VectorFIFO(size=1).wtPort(x5056_x5784_x5906_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5784_x5784), CU.load(x5785_x5785)), op=FltAdd, results=List(x5807))
      Stage(operands=List(CU.load(x5786_x5786), CU.load(x5787_x5787)), op=FltAdd, results=List(x5818))
      Stage(operands=List(x5807, x5818), op=FltAdd, results=List(CU.scalarOut(bus_59129_s)))
    }
    val x5906_1 = Pipeline(name="x5906_1",parent=x5906) { implicit CU => 
      val x5843 = CU.temp
      val x5839 = CU.temp
      val x5845 = CU.temp
      val x5790_x5790 =  VectorFIFO(size=1).wtPort(x5062_x5790_x5906_v)
      val x5791_x5791 =  VectorFIFO(size=1).wtPort(x5063_x5791_x5906_v)
      val x5820 =  ScalarFIFO(size=1).wtPort(bus_59129_s)
      val x5789_x5789 =  VectorFIFO(size=1).wtPort(x5061_x5789_x5906_v)
      val x5788_x5788 =  VectorFIFO(size=1).wtPort(x5060_x5788_x5906_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5788_x5788), CU.load(x5789_x5789)), op=FltAdd, results=List(x5839))
      Stage(operands=List(CU.load(x5790_x5790), CU.load(x5791_x5791)), op=FltAdd, results=List(x5843))
      Stage(operands=List(x5839, x5843), op=FltAdd, results=List(x5845))
      Stage(operands=List(CU.load(x5820), x5845), op=FltAdd, results=List(CU.scalarOut(bus_59148_s)))
    }
    val x5906_2 = Pipeline(name="x5906_2",parent=x5906) { implicit CU => 
      val x5882 = CU.temp
      val x5886 = CU.temp
      val x5792_x5792 =  VectorFIFO(size=1).wtPort(x5064_x5792_x5906_v)
      val x5795_x5795 =  VectorFIFO(size=1).wtPort(x5067_x5795_x5906_v)
      val x5794_x5794 =  VectorFIFO(size=1).wtPort(x5066_x5794_x5906_v)
      val x5793_x5793 =  VectorFIFO(size=1).wtPort(x5065_x5793_x5906_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5792_x5792), CU.load(x5793_x5793)), op=FltAdd, results=List(x5882))
      Stage(operands=List(CU.load(x5794_x5794), CU.load(x5795_x5795)), op=FltAdd, results=List(x5886))
      Stage(operands=List(x5882, x5886), op=FltAdd, results=List(CU.scalarOut(bus_59182_s)))
    }
    val x5906_3 = Pipeline(name="x5906_3",parent=x5906) { implicit CU => 
      val x5898 = CU.temp
      val x5900 = CU.temp
      val x5892 = CU.temp
      val x5896 = CU.temp
      val x5798_x5798 =  VectorFIFO(size=1).wtPort(x5070_x5798_x5906_v)
      val x5888 =  ScalarFIFO(size=1).wtPort(bus_59182_s)
      val x5847 =  ScalarFIFO(size=1).wtPort(bus_59148_s)
      val x5799_x5799 =  VectorFIFO(size=1).wtPort(x5071_x5799_x5906_v)
      val x5797_x5797 =  VectorFIFO(size=1).wtPort(x5069_x5797_x5906_v)
      val x5796_x5796 =  VectorFIFO(size=1).wtPort(x5068_x5796_x5906_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5796_x5796), CU.load(x5797_x5797)), op=FltAdd, results=List(x5892))
      Stage(operands=List(CU.load(x5798_x5798), CU.load(x5799_x5799)), op=FltAdd, results=List(x5896))
      Stage(operands=List(x5892, x5896), op=FltAdd, results=List(x5898))
      Stage(operands=List(CU.load(x5888), x5898), op=FltAdd, results=List(x5900))
      Stage(operands=List(CU.load(x5847), x5900), op=FltAdd, results=List(CU.scalarOut(bus_59187_s)))
    }
    val x5906_4 = Pipeline(name="x5906_4",parent=x5906) { implicit CU => 
      val x5902 =  ScalarFIFO(size=1).wtPort(bus_59187_s)
      val x5800_x5800 =  VectorFIFO(size=1).wtPort(x4979_x5800_x5906_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5902), CU.load(x5800_x5800)), op=FltAdd, results=List(CU.vecOut(x4979_x5905_v)))
    }
    val x5920_0 = Pipeline(name="x5920_0",parent=x5923) { implicit CU => 
      val x5917 = CU.temp
      val x5914_x5914 =  VectorFIFO(size=1).wtPort(x4973_x5914_x5920_v)
      val x5913_x5913 =  VectorFIFO(size=1).wtPort(x4979_x5913_x5920_v)
      val ctr61 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x5911 = CounterChain(name = "x5911", ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5914_x5914), Const(1)), op=FltMul, results=List(x5917))
      Stage(operands=List(CU.load(x5913_x5913), x5917), op=FltAdd, results=List(CU.vecOut(x4973_x5919_v)))
    }
    val x5949 = StreamController(name="x5949",parent=x5950) { implicit CU => 
      val ctr62 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5949_unit = CounterChain(name = "x5949_unit", ctr62)
    }
    val x5942 = Sequential(name="x5942",parent=x5949) { implicit CU => 
      val ctr63 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5942_unit = CounterChain(name = "x5942_unit", ctr63)
    }
    val x5934_0 = Pipeline(name="x5934_0",parent=x5942) { implicit CU => 
      val x5928 =  ScalarBuffer().wtPort(x5928_argin)
      val ctr64 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5934_unit = CounterChain(name = "x5934_unit", ctr64)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x5928)), op=FixAdd, results=List(CU.scalarOut(x5925_b6085_x5933_b6087_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x5925_b6086_x5933_b6088_s)))
    }
    val x5941 = Pipeline(name="x5941",parent=x5942) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5936 = CounterChain(name = "x5936", ctr65)
      var stage: List[Stage] = Nil
    }
    val x5943 = MemoryController(name="x5943",parent=x5949,offchip=x4967_oc, mctpe=TileStore) { implicit CU => 
      val x5925_b6085_x5943 =  ScalarFIFO(name="offset",size=1).wtPort(x5925_b6085_x5933_b6087_s)
      val x5926_x5943 =  VectorFIFO(name="data",size=1).wtPort(x4973_x5937_x5941_v)
      val x5925_b6086_x5943 =  ScalarFIFO(name="size",size=1).wtPort(x5925_b6086_x5933_b6088_s)
    }
    
  }
}
