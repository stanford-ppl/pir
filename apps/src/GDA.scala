import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(top:Top) = {
    val x4975_argin = ArgIn("x4975")
    val x5465_b6069_x5483_b6077_s = Scalar("x5465_b6069_x5483_b6077")
    val x5558_x5851_x5879_v = Vector("x5558_x5851_x5879")
    val x5750_x5766_v = Vector("x5750_x5766")
    val x5015_x5710_x5720_v = Vector("x5015_x5710_x5720")
    val x5554_x5847_x5879_v = Vector("x5554_x5847_x5879")
    val bus_4209_v = Vector("bus_4209")
    val bus_3804_s = Scalar("bus_3804")
    val x5523_b6082_x5534_b6084_s = Scalar("x5523_b6082_x5534_b6084")
    val x5177_argin = ArgIn("x5177")
    val x5408_x5415_s = Scalar("x5408_x5415")
    val bus_3969_s = Scalar("bus_3969")
    val x5493_x5498_s = Scalar("x5493_x5498")
    val x4961_oc = OffChip("x4961")
    val bus_3837_s = Scalar("bus_3837")
    val x5118_b5994_x5142_b6002_s = Scalar("x5118_b5994_x5142_b6002")
    val bus_3843_s = Scalar("bus_3843")
    val bus_4094_v = Vector("bus_4094")
    val x5609_x5625_v = Vector("x5609_x5625")
    val x5175_b6006_x5186_b6008_s = Scalar("x5175_b6006_x5186_b6008")
    val x5797_x5813_v = Vector("x5797_x5813")
    val bus_4186_v = Vector("bus_4186")
    val x5750_x5775_x5779_v = Vector("x5750_x5775_x5779")
    val x5556_x5739_x5744_v = Vector("x5556_x5739_x5744")
    val bus_3903_s = Scalar("bus_3903")
    val x5031_b5976_x5055_b5984_s = Scalar("x5031_b5976_x5055_b5984")
    val x5263_x5275_data_v = Vector("x5263_x5275_data")
    val x5887_argin = ArgIn("x5887")
    val x5205_b6013_x5229_b6021_s = Scalar("x5205_b6013_x5229_b6021")
    val x5349_b6043_x5360_b6045_s = Scalar("x5349_b6043_x5360_b6045")
    val bus_3966_s = Scalar("bus_3966")
    val bus_3810_s = Scalar("bus_3810")
    val x5466_b6071_x5490_b6079_s = Scalar("x5466_b6071_x5490_b6079")
    val bus_3975_s = Scalar("bus_3975")
    val x5024_x5114_s = Scalar("x5024_x5114")
    val x5494_x5500_s = Scalar("x5494_x5500")
    val bus_3898_s = Scalar("bus_3898")
    val x5553_x5602_v = Vector("x5553_x5602")
    val x5031_b5977_x5055_b5985_s = Scalar("x5031_b5977_x5055_b5985")
    val x5554_x5645_x5650_v = Vector("x5554_x5645_x5650")
    val bus_3901_s = Scalar("bus_3901")
    val x5018_x5568_x5579_v = Vector("x5018_x5568_x5579")
    val x4972_x5805_x5814_v = Vector("x4972_x5805_x5814")
    val x5026_x5288_s = Scalar("x5026_x5288")
    val x5032_x5057_data_v = Vector("x5032_x5057_data")
    val x5088_b5987_x5099_b5989_s = Scalar("x5088_b5987_x5099_b5989")
    val x5264_argin = ArgIn("x5264")
    val x5884_b6163_x5894_b6165_s = Scalar("x5884_b6163_x5894_b6165")
    val x5378_b6050_x5396_b6058_s = Scalar("x5378_b6050_x5396_b6058")
    val x5436_b6063_x5447_b6065_s = Scalar("x5436_b6063_x5447_b6065")
    val x5558_x5837_v = Vector("x5558_x5837")
    val x4965_oc = OffChip("x4965")
    val x5562_x5586_x5591_v = Vector("x5562_x5586_x5591")
    val bus_3876_s = Scalar("bus_3876")
    val x5117_b5993_x5135_b6001_s = Scalar("x5117_b5993_x5135_b6001")
    val x5751_x5778_v = Vector("x5751_x5778")
    val x5562_x5587_x5591_v = Vector("x5562_x5587_x5591")
    val x5089_x5101_data_v = Vector("x5089_x5101_data")
    val x5204_b6011_x5222_b6019_s = Scalar("x5204_b6011_x5222_b6019")
    val x5204_b6012_x5222_b6020_s = Scalar("x5204_b6012_x5222_b6020")
    val x4971_x5665_x5673_v = Vector("x4971_x5665_x5673")
    val x5656_x5681_x5685_v = Vector("x5656_x5681_x5685")
    val x5379_b6052_x5403_b6060_s = Scalar("x5379_b6052_x5403_b6060")
    val x5008_x5852_x5879_v = Vector("x5008_x5852_x5879")
    val x5294_argin = ArgIn("x5294")
    val x5609_x5633_x5638_v = Vector("x5609_x5633_x5638")
    val x4973_b5966_x4980_b5968_s = Scalar("x4973_b5966_x4980_b5968")
    val x5293_x5318_data_v = Vector("x5293_x5318_data")
    val x5013_x5616_x5626_v = Vector("x5013_x5616_x5626")
    val x4971_x5806_x5814_v = Vector("x4971_x5806_x5814")
    val x5380_x5405_data_v = Vector("x5380_x5405_data")
    val x5120_argin = ArgIn("x5120")
    val x5234_x5241_s = Scalar("x5234_x5241")
    val x5437_x5449_data_v = Vector("x5437_x5449_data")
    val x5656_x5680_x5685_v = Vector("x5656_x5680_x5685")
    val bus_4140_v = Vector("bus_4140")
    val x5008_x5899_x5903_v = Vector("x5008_x5899_x5903")
    val x4990_b5970_x4997_b5972_s = Scalar("x4990_b5970_x4997_b5972")
    val x5555_x5848_x5879_v = Vector("x5555_x5848_x5879")
    val x4958_argin = ArgIn("x4958")
    val x5438_argin = ArgIn("x5438")
    val x4972_x5570_x5579_v = Vector("x4972_x5570_x5579")
    val x5028_x5462_s = Scalar("x5028_x5462")
    val x5262_b6025_x5273_b6027_s = Scalar("x5262_b6025_x5273_b6027")
    val x5798_x5832_x5838_v = Vector("x5798_x5832_x5838")
    val x5524_x5536_data_v = Vector("x5524_x5536_data")
    val x5060_x5067_s = Scalar("x5060_x5067")
    val x5553_x5846_x5879_v = Vector("x5553_x5846_x5879")
    val bus_3943_s = Scalar("bus_3943")
    val x5022_x5756_x5767_v = Vector("x5022_x5756_x5767")
    val x5088_b5986_x5099_b5988_s = Scalar("x5088_b5986_x5099_b5988")
    val x5657_x5691_x5697_v = Vector("x5657_x5691_x5697")
    val x5020_x5662_x5673_v = Vector("x5020_x5662_x5673")
    val bus_3933_s = Scalar("bus_3933")
    val x5090_argin = ArgIn("x5090")
    val bus_3976_s = Scalar("bus_3976")
    val x5465_b6068_x5483_b6076_s = Scalar("x5465_b6068_x5483_b6076")
    val x4971_x5759_x5767_v = Vector("x4971_x5759_x5767")
    val x4971_x5618_x5626_v = Vector("x4971_x5618_x5626")
    val bus_3835_s = Scalar("bus_3835")
    val x5117_b5992_x5135_b6000_s = Scalar("x5117_b5992_x5135_b6000")
    val x5657_x5684_v = Vector("x5657_x5684")
    val x5058_x5063_s = Scalar("x5058_x5063")
    val x5557_x5786_x5791_v = Vector("x5557_x5786_x5791")
    val bus_3844_s = Scalar("bus_3844")
    val x5556_x5743_v = Vector("x5556_x5743")
    val bus_4117_v = Vector("bus_4117")
    val x5558_x5833_x5838_v = Vector("x5558_x5833_x5838")
    val x5556_x5849_x5879_v = Vector("x5556_x5849_x5879")
    val x5798_x5825_v = Vector("x5798_x5825")
    val x5017_x5804_x5814_v = Vector("x5017_x5804_x5814")
    val x5118_b5996_x5142_b6004_s = Scalar("x5118_b5996_x5142_b6004")
    val x5563_x5597_x5603_v = Vector("x5563_x5597_x5603")
    val x5292_b6033_x5316_b6041_s = Scalar("x5292_b6033_x5316_b6041")
    val x5703_x5728_x5732_v = Vector("x5703_x5728_x5732")
    val x5207_argin = ArgIn("x5207")
    val x5027_x5375_s = Scalar("x5027_x5375")
    val bus_3934_s = Scalar("bus_3934")
    val bus_3900_s = Scalar("bus_3900")
    val x5703_x5727_x5732_v = Vector("x5703_x5727_x5732")
    val x5703_x5719_v = Vector("x5703_x5719")
    val bus_3936_s = Scalar("bus_3936")
    val x5797_x5822_x5826_v = Vector("x5797_x5822_x5826")
    val bus_3942_s = Scalar("bus_3942")
    val x5320_x5326_s = Scalar("x5320_x5326")
    val x5008_x5878_v = Vector("x5008_x5878")
    val x4966_oc = OffChip("x4966")
    val bus_3811_s = Scalar("bus_3811")
    val x5466_b6070_x5490_b6078_s = Scalar("x5466_b6070_x5490_b6078")
    val x5704_x5731_v = Vector("x5704_x5731")
    val x5379_b6051_x5403_b6059_s = Scalar("x5379_b6051_x5403_b6059")
    val x5145_x5150_s = Scalar("x5145_x5150")
    val x5610_x5644_x5650_v = Vector("x5610_x5644_x5650")
    val bus_3931_s = Scalar("bus_3931")
    val x5205_b6014_x5229_b6022_s = Scalar("x5205_b6014_x5229_b6022")
    val x4972_x5664_x5673_v = Vector("x4972_x5664_x5673")
    val x5555_x5696_v = Vector("x5555_x5696")
    val x5232_x5237_s = Scalar("x5232_x5237")
    val bus_3832_s = Scalar("bus_3832")
    val x5233_x5239_s = Scalar("x5233_x5239")
    val x5381_argin = ArgIn("x5381")
    val x5436_b6062_x5447_b6064_s = Scalar("x5436_b6062_x5447_b6064")
    val x5610_x5637_v = Vector("x5610_x5637")
    val x5176_x5188_data_v = Vector("x5176_x5188_data")
    val bus_3877_s = Scalar("bus_3877")
    val x5014_x5663_x5673_v = Vector("x5014_x5663_x5673")
    val x5012_x5569_x5579_v = Vector("x5012_x5569_x5579")
    val x5175_b6005_x5186_b6007_s = Scalar("x5175_b6005_x5186_b6007")
    val x5351_argin = ArgIn("x5351")
    val x5379_b6053_x5403_b6061_s = Scalar("x5379_b6053_x5403_b6061")
    val x5553_x5598_x5603_v = Vector("x5553_x5598_x5603")
    val x5466_b6072_x5490_b6080_s = Scalar("x5466_b6072_x5490_b6080")
    val x5555_x5692_x5697_v = Vector("x5555_x5692_x5697")
    val x4972_x5711_x5720_v = Vector("x4972_x5711_x5720")
    val x5019_x5615_x5626_v = Vector("x5019_x5615_x5626")
    val x4973_b5965_x4980_b5967_s = Scalar("x4973_b5965_x4980_b5967")
    val x5292_b6034_x5316_b6042_s = Scalar("x5292_b6034_x5316_b6042")
    val x5407_x5413_s = Scalar("x5407_x5413")
    val x4972_x5758_x5767_v = Vector("x4972_x5758_x5767")
    val x5797_x5821_x5826_v = Vector("x5797_x5821_x5826")
    val bus_3910_s = Scalar("bus_3910")
    val x5349_b6044_x5360_b6046_s = Scalar("x5349_b6044_x5360_b6046")
    val bus_3801_s = Scalar("bus_3801")
    val x5750_x5774_x5779_v = Vector("x5750_x5774_x5779")
    val x5557_x5850_x5879_v = Vector("x5557_x5850_x5879")
    val x4963_oc = OffChip("x4963")
    val x5704_x5738_x5744_v = Vector("x5704_x5738_x5744")
    val x4971_x5712_x5720_v = Vector("x4971_x5712_x5720")
    val x5406_x5411_s = Scalar("x5406_x5411")
    val bus_4223_s = Scalar("bus_4223")
    val x5291_b6030_x5309_b6038_s = Scalar("x5291_b6030_x5309_b6038")
    val bus_3799_s = Scalar("bus_3799")
    val x5025_x5201_s = Scalar("x5025_x5201")
    val x5029_x5549_s = Scalar("x5029_x5549")
    val x5147_x5154_s = Scalar("x5147_x5154")
    val x4974_x4982_data_v = Vector("x4974_x4982_data")
    val bus_3867_s = Scalar("bus_3867")
    val bus_3964_s = Scalar("bus_3964")
    val x4991_x4999_data_v = Vector("x4991_x4999_data")
    val x5059_x5065_s = Scalar("x5059_x5065")
    val bus_3868_s = Scalar("bus_3868")
    val x5751_x5785_x5791_v = Vector("x5751_x5785_x5791")
    val x5554_x5649_v = Vector("x5554_x5649")
    val x4964_oc = OffChip("x4964")
    val x5321_x5328_s = Scalar("x5321_x5328")
    val x5378_b6049_x5396_b6057_s = Scalar("x5378_b6049_x5396_b6057")
    val x5033_argin = ArgIn("x5033")
    val x5030_b5973_x5048_b5981_s = Scalar("x5030_b5973_x5048_b5981")
    val x5523_b6081_x5534_b6083_s = Scalar("x5523_b6081_x5534_b6083")
    val x5563_x5590_v = Vector("x5563_x5590")
    val bus_3865_s = Scalar("bus_3865")
    val bus_3870_s = Scalar("bus_3870")
    val x5292_b6032_x5316_b6040_s = Scalar("x5292_b6032_x5316_b6040")
    val x5557_x5790_v = Vector("x5557_x5790")
    val bus_3909_s = Scalar("bus_3909")
    val x5319_x5324_s = Scalar("x5319_x5324")
    val x5031_b5975_x5055_b5983_s = Scalar("x5031_b5975_x5055_b5983")
    val x4990_b5969_x4997_b5971_s = Scalar("x4990_b5969_x4997_b5971")
    val x5467_x5492_data_v = Vector("x5467_x5492_data")
    val x5884_b6164_x5894_b6166_s = Scalar("x5884_b6164_x5894_b6166")
    val x5030_b5974_x5048_b5982_s = Scalar("x5030_b5974_x5048_b5982")
    val bus_3802_s = Scalar("bus_3802")
    val x5468_argin = ArgIn("x5468")
    val x5495_x5502_s = Scalar("x5495_x5502")
    val bus_4163_v = Vector("bus_4163")
    val x4971_x5571_x5579_v = Vector("x4971_x5571_x5579")
    val bus_3834_s = Scalar("bus_3834")
    val x5262_b6024_x5273_b6026_s = Scalar("x5262_b6024_x5273_b6026")
    val x5023_x5803_x5814_v = Vector("x5023_x5803_x5814")
    val bus_3967_s = Scalar("bus_3967")
    val x5021_x5709_x5720_v = Vector("x5021_x5709_x5720")
    val x5205_b6015_x5229_b6023_s = Scalar("x5205_b6015_x5229_b6023")
    val x5656_x5672_v = Vector("x5656_x5672")
    val x5562_x5578_v = Vector("x5562_x5578")
    val x5206_x5231_data_v = Vector("x5206_x5231_data")
    val x4992_argin = ArgIn("x4992")
    val x5119_x5144_data_v = Vector("x5119_x5144_data")
    val x5609_x5634_x5638_v = Vector("x5609_x5634_x5638")
    val x5016_x5757_x5767_v = Vector("x5016_x5757_x5767")
    val x5291_b6031_x5309_b6039_s = Scalar("x5291_b6031_x5309_b6039")
    val x4972_x5617_x5626_v = Vector("x4972_x5617_x5626")
    val bus_4228_s = Scalar("bus_4228")
    val x5350_x5362_data_v = Vector("x5350_x5362_data")
    val x5525_argin = ArgIn("x5525")
    val x5118_b5995_x5142_b6003_s = Scalar("x5118_b5995_x5142_b6003")
    val x5146_x5152_s = Scalar("x5146_x5152")
    val x5913 = Sequential(name="x5913",parent=top) { implicit CU => 
    }
    val x4971_dsp0 = MemoryPipeline(name="x4971_dsp0",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x5800 = CounterChain.copy("x5814", "x5800")
      val x4971_x5806 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5806_x5814_v).rdAddr(x5800(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4971_dsp1 = MemoryPipeline(name="x4971_dsp1",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x5753 = CounterChain.copy("x5767", "x5753")
      val x4971_x5759 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5759_x5767_v).rdAddr(x5753(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4971_dsp2 = MemoryPipeline(name="x4971_dsp2",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x5706 = CounterChain.copy("x5720", "x5706")
      val x4971_x5712 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5712_x5720_v).rdAddr(x5706(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4971_dsp3 = MemoryPipeline(name="x4971_dsp3",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x5659 = CounterChain.copy("x5673", "x5659")
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x4971_x5665 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5665_x5673_v).rdAddr(x5659(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4971_dsp4 = MemoryPipeline(name="x4971_dsp4",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x5612 = CounterChain.copy("x5626", "x5612")
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x4971_x5618 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5618_x5626_v).rdAddr(x5612(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4971_dsp5 = MemoryPipeline(name="x4971_dsp5",parent="x5913") { implicit CU => 
      val x4987_x4987 =  VectorFIFO(size=1).wtPort(x4974_x4982_data_v)
      val x4984 = CounterChain.copy("x4988", "x4984")
      val x5565 = CounterChain.copy("x5579", "x5565")
      val x4971_x5571 =  SRAM(size=96,banking = Strided(1)).wtPort(x4987_x4987.readPort).rdPort(x4971_x5571_x5579_v).rdAddr(x5565(0)).wtAddr(x4984(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp0 = MemoryPipeline(name="x4972_dsp0",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x5800 = CounterChain.copy("x5814", "x5800")
      val x4972_x5805 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5805_x5814_v).rdAddr(x5800(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp1 = MemoryPipeline(name="x4972_dsp1",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x5753 = CounterChain.copy("x5767", "x5753")
      val x4972_x5758 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5758_x5767_v).rdAddr(x5753(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp2 = MemoryPipeline(name="x4972_dsp2",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5706 = CounterChain.copy("x5720", "x5706")
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x4972_x5711 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5711_x5720_v).rdAddr(x5706(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp3 = MemoryPipeline(name="x4972_dsp3",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5659 = CounterChain.copy("x5673", "x5659")
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x4972_x5664 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5664_x5673_v).rdAddr(x5659(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp4 = MemoryPipeline(name="x4972_dsp4",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5612 = CounterChain.copy("x5626", "x5612")
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x4972_x5617 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5617_x5626_v).rdAddr(x5612(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4972_dsp5 = MemoryPipeline(name="x4972_dsp5",parent="x5913") { implicit CU => 
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4991_x4999_data_v)
      val x5001 = CounterChain.copy("x5005", "x5001")
      val x5565 = CounterChain.copy("x5579", "x5565")
      val x4972_x5570 =  SRAM(size=96,banking = Strided(1)).wtPort(x5004_x5004.readPort).rdPort(x4972_x5570_x5579_v).rdAddr(x5565(0)).wtAddr(x5001(0))
      var stage: List[Stage] = Nil
    }
    val x4989 = StreamController(name="x4989",parent=x5913) { implicit CU => 
    }
    val x4981_0 = Pipeline(name="x4981_0",parent=x4989) { implicit CU => 
      val x4975 =  ScalarBuffer().wtPort(x4975_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4975)), op=FixAdd, results=List(CU.scalarOut(x4973_b5965_x4980_b5967_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4973_b5966_x4980_b5968_s)))
    }
    val x4982 = MemoryController(name="x4982",parent=x4989,offchip=x4964_oc, mctpe=TileLoad) { implicit CU => 
      val x4973_b5965_x4982 =  ScalarFIFO(name="offset",size=1).wtPort(x4973_b5965_x4980_b5967_s)
      val x4973_b5966_x4982 =  ScalarFIFO(name="size",size=1).wtPort(x4973_b5966_x4980_b5968_s)
      CU.newVout("data", x4974_x4982_data_v)
    }
    val x4988 = Pipeline(name="x4988",parent=x4989) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4984 = CounterChain(name = "x4984", ctr1).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5006 = StreamController(name="x5006",parent=x5913) { implicit CU => 
    }
    val x4998_0 = Pipeline(name="x4998_0",parent=x5006) { implicit CU => 
      val x4992 =  ScalarBuffer().wtPort(x4992_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4992)), op=FixAdd, results=List(CU.scalarOut(x4990_b5969_x4997_b5971_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4990_b5970_x4997_b5972_s)))
    }
    val x4999 = MemoryController(name="x4999",parent=x5006,offchip=x4965_oc, mctpe=TileLoad) { implicit CU => 
      val x4990_b5969_x4999 =  ScalarFIFO(name="offset",size=1).wtPort(x4990_b5969_x4997_b5971_s)
      val x4990_b5970_x4999 =  ScalarFIFO(name="size",size=1).wtPort(x4990_b5970_x4997_b5972_s)
      CU.newVout("data", x4991_x4999_data_v)
    }
    val x5005 = Pipeline(name="x5005",parent=x5006) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5001 = CounterChain(name = "x5001", ctr2).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5008_dsp0 = MemoryPipeline(name="x5008_dsp0",parent="x5881") { implicit CU => 
      val b6161 = CU.temp
      val b6167 = CU.temp
      val x5878_x5878 =  VectorFIFO(size=1).wtPort(x5008_x5878_v)
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5883 = CounterChain.copy("x5912", "x5883")
      val x5897 = CounterChain.copy("x5903", "x5897")
      val x5008_x5899 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5878_x5878.readPort).rdPort(x5008_x5899_x5903_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6161))
      WAStage(operands=List(b6161, CU.ctr(x5844(1))), op=FixAdd, results=List(x5008_x5899.writeAddr))
      RAStage(operands=List(CU.ctr(x5883(0)), Const(96)), op=FixMul, results=List(b6167))
      RAStage(operands=List(b6167, CU.ctr(x5897(0))), op=FixAdd, results=List(x5008_x5899.readAddr))
    }
    val x5008_dsp1 = MemoryPipeline(name="x5008_dsp1",parent="x5881") { implicit CU => 
      val b6161 = CU.temp
      val b6159 = CU.temp
      val x5878_x5878 =  VectorFIFO(size=1).wtPort(x5008_x5878_v)
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5008_x5852 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5878_x5878.readPort).rdPort(x5008_x5852_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6161))
      WAStage(operands=List(b6161, CU.ctr(x5844(1))), op=FixAdd, results=List(x5008_x5852.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6159))
      RAStage(operands=List(b6159, CU.ctr(x5844(1))), op=FixAdd, results=List(x5008_x5852.readAddr))
    }
    val x5881 = MetaPipeline(name="x5881",parent=x5913) { implicit CU => 
      val x4958_x5009 =  ScalarBuffer().wtPort(x4958_argin)
      val ctr3 = Counter(min=Const(0), max=x4958_x5009.load, step=Const(20), par=6) // Counter
      val x5011 = CounterChain(name = "x5011", ctr3).iter(3000)
    }
    val x5012_dsp0 = MemoryPipeline(name="x5012_dsp0",parent="x5881") { implicit CU => 
      val x5082_x5082 =  VectorFIFO(size=1).wtPort(x5032_x5057_data_v)
      val x5058_x5072 =  ScalarBuffer().wtPort(x5058_x5063_s)
      val x5071 = CounterChain.copy("x5083", "x5071")
      val x5561 = CounterChain.copy("x5605", "x5561")
      val x5012_x5569 =  SRAM(size=20,banking = Strided(1)).wtPort(x5082_x5082.readPort).rdPort(x5012_x5569_x5579_v).rdAddr(x5561(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5071(0)), CU.load(x5058_x5072)), op=FixSub, results=List(x5012_x5569.writeAddr))
    }
    val x5013_dsp0 = MemoryPipeline(name="x5013_dsp0",parent="x5881") { implicit CU => 
      val x5169_x5169 =  VectorFIFO(size=1).wtPort(x5119_x5144_data_v)
      val x5145_x5159 =  ScalarBuffer().wtPort(x5145_x5150_s)
      val x5158 = CounterChain.copy("x5170", "x5158")
      val x5608 = CounterChain.copy("x5652", "x5608")
      val x5013_x5616 =  SRAM(size=20,banking = Strided(1)).wtPort(x5169_x5169.readPort).rdPort(x5013_x5616_x5626_v).rdAddr(x5608(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5158(0)), CU.load(x5145_x5159)), op=FixSub, results=List(x5013_x5616.writeAddr))
    }
    val x5014_dsp0 = MemoryPipeline(name="x5014_dsp0",parent="x5881") { implicit CU => 
      val x5256_x5256 =  VectorFIFO(size=1).wtPort(x5206_x5231_data_v)
      val x5232_x5246 =  ScalarBuffer().wtPort(x5232_x5237_s)
      val x5245 = CounterChain.copy("x5257", "x5245")
      val x5655 = CounterChain.copy("x5699", "x5655")
      val x5014_x5663 =  SRAM(size=20,banking = Strided(1)).wtPort(x5256_x5256.readPort).rdPort(x5014_x5663_x5673_v).rdAddr(x5655(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5245(0)), CU.load(x5232_x5246)), op=FixSub, results=List(x5014_x5663.writeAddr))
    }
    val x5015_dsp0 = MemoryPipeline(name="x5015_dsp0",parent="x5881") { implicit CU => 
      val x5343_x5343 =  VectorFIFO(size=1).wtPort(x5293_x5318_data_v)
      val x5319_x5333 =  ScalarBuffer().wtPort(x5319_x5324_s)
      val x5332 = CounterChain.copy("x5344", "x5332")
      val x5702 = CounterChain.copy("x5746", "x5702")
      val x5015_x5710 =  SRAM(size=20,banking = Strided(1)).wtPort(x5343_x5343.readPort).rdPort(x5015_x5710_x5720_v).rdAddr(x5702(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5332(0)), CU.load(x5319_x5333)), op=FixSub, results=List(x5015_x5710.writeAddr))
    }
    val x5016_dsp0 = MemoryPipeline(name="x5016_dsp0",parent="x5881") { implicit CU => 
      val x5430_x5430 =  VectorFIFO(size=1).wtPort(x5380_x5405_data_v)
      val x5406_x5420 =  ScalarBuffer().wtPort(x5406_x5411_s)
      val x5419 = CounterChain.copy("x5431", "x5419")
      val x5749 = CounterChain.copy("x5793", "x5749")
      val x5016_x5757 =  SRAM(size=20,banking = Strided(1)).wtPort(x5430_x5430.readPort).rdPort(x5016_x5757_x5767_v).rdAddr(x5749(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5419(0)), CU.load(x5406_x5420)), op=FixSub, results=List(x5016_x5757.writeAddr))
    }
    val x5017_dsp0 = MemoryPipeline(name="x5017_dsp0",parent="x5881") { implicit CU => 
      val x5517_x5517 =  VectorFIFO(size=1).wtPort(x5467_x5492_data_v)
      val x5493_x5507 =  ScalarBuffer().wtPort(x5493_x5498_s)
      val x5506 = CounterChain.copy("x5518", "x5506")
      val x5796 = CounterChain.copy("x5840", "x5796")
      val x5017_x5804 =  SRAM(size=20,banking = Strided(1)).wtPort(x5517_x5517.readPort).rdPort(x5017_x5804_x5814_v).rdAddr(x5796(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5506(0)), CU.load(x5493_x5507)), op=FixSub, results=List(x5017_x5804.writeAddr))
    }
    val x5018_dsp0 = MemoryPipeline(name="x5018_dsp0",parent="x5881") { implicit CU => 
      val b5990 = CU.temp
      val b6087 = CU.temp
      val x5108_x5108 =  VectorFIFO(size=1).wtPort(x5089_x5101_data_v)
      val x5087 = CounterChain.copy("x5110", "x5087")
      val x5103 = CounterChain.copy("x5109", "x5103")
      val x5561 = CounterChain.copy("x5605", "x5561")
      val x5565 = CounterChain.copy("x5579", "x5565")
      val x5018_x5568 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5108_x5108.readPort).rdPort(x5018_x5568_x5579_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5087(0)), Const(96)), op=FixMul, results=List(b5990))
      WAStage(operands=List(b5990, CU.ctr(x5103(0))), op=FixAdd, results=List(x5018_x5568.writeAddr))
      RAStage(operands=List(CU.ctr(x5561(0)), Const(96)), op=FixMul, results=List(b6087))
      RAStage(operands=List(b6087, CU.ctr(x5565(0))), op=FixAdd, results=List(x5018_x5568.readAddr))
    }
    val x5019_dsp0 = MemoryPipeline(name="x5019_dsp0",parent="x5881") { implicit CU => 
      val b6009 = CU.temp
      val b6097 = CU.temp
      val x5195_x5195 =  VectorFIFO(size=1).wtPort(x5176_x5188_data_v)
      val x5612 = CounterChain.copy("x5626", "x5612")
      val x5174 = CounterChain.copy("x5197", "x5174")
      val x5190 = CounterChain.copy("x5196", "x5190")
      val x5608 = CounterChain.copy("x5652", "x5608")
      val x5019_x5615 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5195_x5195.readPort).rdPort(x5019_x5615_x5626_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5174(0)), Const(96)), op=FixMul, results=List(b6009))
      WAStage(operands=List(b6009, CU.ctr(x5190(0))), op=FixAdd, results=List(x5019_x5615.writeAddr))
      RAStage(operands=List(CU.ctr(x5608(0)), Const(96)), op=FixMul, results=List(b6097))
      RAStage(operands=List(b6097, CU.ctr(x5612(0))), op=FixAdd, results=List(x5019_x5615.readAddr))
    }
    val x5020_dsp0 = MemoryPipeline(name="x5020_dsp0",parent="x5881") { implicit CU => 
      val b6107 = CU.temp
      val b6028 = CU.temp
      val x5282_x5282 =  VectorFIFO(size=1).wtPort(x5263_x5275_data_v)
      val x5277 = CounterChain.copy("x5283", "x5277")
      val x5659 = CounterChain.copy("x5673", "x5659")
      val x5655 = CounterChain.copy("x5699", "x5655")
      val x5261 = CounterChain.copy("x5284", "x5261")
      val x5020_x5662 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5282_x5282.readPort).rdPort(x5020_x5662_x5673_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5261(0)), Const(96)), op=FixMul, results=List(b6028))
      WAStage(operands=List(b6028, CU.ctr(x5277(0))), op=FixAdd, results=List(x5020_x5662.writeAddr))
      RAStage(operands=List(CU.ctr(x5655(0)), Const(96)), op=FixMul, results=List(b6107))
      RAStage(operands=List(b6107, CU.ctr(x5659(0))), op=FixAdd, results=List(x5020_x5662.readAddr))
    }
    val x5021_dsp0 = MemoryPipeline(name="x5021_dsp0",parent="x5881") { implicit CU => 
      val b6117 = CU.temp
      val b6047 = CU.temp
      val x5369_x5369 =  VectorFIFO(size=1).wtPort(x5350_x5362_data_v)
      val x5702 = CounterChain.copy("x5746", "x5702")
      val x5348 = CounterChain.copy("x5371", "x5348")
      val x5706 = CounterChain.copy("x5720", "x5706")
      val x5364 = CounterChain.copy("x5370", "x5364")
      val x5021_x5709 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5369_x5369.readPort).rdPort(x5021_x5709_x5720_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5348(0)), Const(96)), op=FixMul, results=List(b6047))
      WAStage(operands=List(b6047, CU.ctr(x5364(0))), op=FixAdd, results=List(x5021_x5709.writeAddr))
      RAStage(operands=List(CU.ctr(x5702(0)), Const(96)), op=FixMul, results=List(b6117))
      RAStage(operands=List(b6117, CU.ctr(x5706(0))), op=FixAdd, results=List(x5021_x5709.readAddr))
    }
    val x5022_dsp0 = MemoryPipeline(name="x5022_dsp0",parent="x5881") { implicit CU => 
      val b6127 = CU.temp
      val b6066 = CU.temp
      val x5456_x5456 =  VectorFIFO(size=1).wtPort(x5437_x5449_data_v)
      val x5749 = CounterChain.copy("x5793", "x5749")
      val x5451 = CounterChain.copy("x5457", "x5451")
      val x5435 = CounterChain.copy("x5458", "x5435")
      val x5753 = CounterChain.copy("x5767", "x5753")
      val x5022_x5756 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5456_x5456.readPort).rdPort(x5022_x5756_x5767_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5435(0)), Const(96)), op=FixMul, results=List(b6066))
      WAStage(operands=List(b6066, CU.ctr(x5451(0))), op=FixAdd, results=List(x5022_x5756.writeAddr))
      RAStage(operands=List(CU.ctr(x5749(0)), Const(96)), op=FixMul, results=List(b6127))
      RAStage(operands=List(b6127, CU.ctr(x5753(0))), op=FixAdd, results=List(x5022_x5756.readAddr))
    }
    val x5023_dsp0 = MemoryPipeline(name="x5023_dsp0",parent="x5881") { implicit CU => 
      val b6137 = CU.temp
      val b6085 = CU.temp
      val x5543_x5543 =  VectorFIFO(size=1).wtPort(x5524_x5536_data_v)
      val x5538 = CounterChain.copy("x5544", "x5538")
      val x5522 = CounterChain.copy("x5545", "x5522")
      val x5796 = CounterChain.copy("x5840", "x5796")
      val x5800 = CounterChain.copy("x5814", "x5800")
      val x5023_x5803 =  SRAM(size=1920,banking = Strided(1)).wtPort(x5543_x5543.readPort).rdPort(x5023_x5803_x5814_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5522(0)), Const(96)), op=FixMul, results=List(b6085))
      WAStage(operands=List(b6085, CU.ctr(x5538(0))), op=FixAdd, results=List(x5023_x5803.writeAddr))
      RAStage(operands=List(CU.ctr(x5796(0)), Const(96)), op=FixMul, results=List(b6137))
      RAStage(operands=List(b6137, CU.ctr(x5800(0))), op=FixAdd, results=List(x5023_x5803.readAddr))
    }
    val x5085 = StreamController(name="x5085",parent=x5881) { implicit CU => 
    }
    val x5056 = StreamController(name="x5056",parent=x5085) { implicit CU => 
    }
    val x5056_0 = Pipeline(name="x5056_0",parent=x5056) { implicit CU => 
      val x5040 = CU.temp
      val x5034 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5034, CU.scalarOut(bus_3799_s)))
      Stage(operands=List(x5034, Const(64)), op=FixMod, results=List(x5040, CU.scalarOut(bus_3801_s)))
      Stage(operands=List(x5040, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3802_s), CU.scalarOut(x5031_b5976_x5055_b5984_s)))
      Stage(operands=List(x5034, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3804_s)))
    }
    val x5056_1 = Pipeline(name="x5056_1",parent=x5056) { implicit CU => 
      val x5037 = CU.temp
      val x5036 = CU.temp
      val x5038 = CU.temp
      val x5039 = CU.temp
      val x5035 =  ScalarFIFO(size=1).wtPort(bus_3804_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5035), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5031_b5977_x5055_b5985_s)))
      Stage(operands=List(CU.load(x5035), Const(64)), op=FixMod, results=List(x5036))
      Stage(operands=List(x5036, Const(0)), op=FixEql, results=List(x5037))
      Stage(operands=List(Const(64), x5036), op=FixSub, results=List(x5038))
      Stage(operands=List(x5037, Const(0), x5038), op=Mux, results=List(x5039, CU.scalarOut(bus_3810_s)))
      Stage(operands=List(x5039, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3811_s)))
    }
    val x5056_2 = Pipeline(name="x5056_2",parent=x5056) { implicit CU => 
      val x5041 = CU.temp
      val x5043 = CU.temp
      val x5052 = CU.temp
      val x5049 =  ScalarFIFO(size=1).wtPort(bus_3802_s)
      val x5039 =  ScalarFIFO(size=1).wtPort(bus_3810_s)
      val x5040 =  ScalarFIFO(size=1).wtPort(bus_3801_s)
      val x5033 =  ScalarBuffer().wtPort(x5033_argin)
      val x5051 =  ScalarFIFO(size=1).wtPort(bus_3811_s)
      val x5034 =  ScalarFIFO(size=1).wtPort(bus_3799_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5049)), op=FixAdd, results=List(x5052))
      Stage(operands=List(x5052, CU.load(x5051)), op=FixAdd, results=List(CU.scalarOut(x5031_b5975_x5055_b5983_s)))
      Stage(operands=List(Const(80), CU.load(x5040)), op=FixAdd, results=List(x5041))
      Stage(operands=List(x5041, CU.load(x5039)), op=FixAdd, results=List(CU.scalarOut(x5030_b5974_x5048_b5982_s)))
      Stage(operands=List(CU.load(x5034), CU.load(x5040)), op=FixSub, results=List(x5043))
      Stage(operands=List(x5043, CU.load(x5033)), op=FixAdd, results=List(CU.scalarOut(x5030_b5973_x5048_b5981_s)))
    }
    val x5057 = MemoryController(name="x5057",parent=x5085,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5030_b5974_x5057 =  ScalarFIFO(name="size",size=1).wtPort(x5030_b5974_x5048_b5982_s)
      val x5030_b5973_x5057 =  ScalarFIFO(name="offset",size=1).wtPort(x5030_b5973_x5048_b5981_s)
      CU.newVout("data", x5032_x5057_data_v)
    }
    val x5084 = Sequential(name="x5084",parent=x5085) { implicit CU => 
    }
    val x5068_0 = Pipeline(name="x5068_0",parent=x5084) { implicit CU => 
      val x5031_b5977_x5061_b5980 =  ScalarFIFO(size=16).wtPort(x5031_b5977_x5055_b5985_s)
      val x5031_b5976_x5061_b5979 =  ScalarFIFO(size=16).wtPort(x5031_b5976_x5055_b5984_s)
      val x5031_b5975_x5061_b5978 =  ScalarFIFO(size=16).wtPort(x5031_b5975_x5055_b5983_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5031_b5976_x5061_b5979)), op=Bypass, results=List(CU.scalarOut(x5058_x5063_s)))
      Stage(operands=List(CU.load(x5031_b5977_x5061_b5980)), op=Bypass, results=List(CU.scalarOut(x5059_x5065_s)))
      Stage(operands=List(CU.load(x5031_b5975_x5061_b5978)), op=Bypass, results=List(CU.scalarOut(x5060_x5067_s)))
    }
    val x5083 = Pipeline(name="x5083",parent=x5084) { implicit CU => 
      val x5059_x5073 =  ScalarBuffer().wtPort(x5059_x5065_s)
      val x5058_x5072 =  ScalarBuffer().wtPort(x5058_x5063_s)
      val x5060_x5069 =  ScalarBuffer().wtPort(x5060_x5067_s)
      val ctr4 = Counter(min=Const(0), max=x5060_x5069.load, step=Const(1), par=16) // Counter
      val x5071 = CounterChain(name = "x5071", ctr4).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5110 = StreamController(name="x5110",parent=x5881) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5087 = CounterChain(name = "x5087", ctr5).iter(20)
    }
    val x5100_0 = Pipeline(name="x5100_0",parent=x5110) { implicit CU => 
      val x5091 = CU.temp
      val x5092 = CU.temp
      val x5093 = CU.temp
      val x5090 =  ScalarBuffer().wtPort(x5090_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5087 = CounterChain.copy("x5110", "x5087")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5087(0))), op=FixAdd, results=List(x5091))
      Stage(operands=List(x5091, Const(96)), op=FixMul, results=List(x5092))
      Stage(operands=List(x5092, Const(4)), op=FixMul, results=List(x5093))
      Stage(operands=List(x5093, CU.load(x5090)), op=FixAdd, results=List(CU.scalarOut(x5088_b5986_x5099_b5988_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5088_b5987_x5099_b5989_s)))
    }
    val x5101 = MemoryController(name="x5101",parent=x5110,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5088_b5987_x5101 =  ScalarFIFO(name="size",size=1).wtPort(x5088_b5987_x5099_b5989_s)
      val x5088_b5986_x5101 =  ScalarFIFO(name="offset",size=1).wtPort(x5088_b5986_x5099_b5988_s)
      CU.newVout("data", x5089_x5101_data_v)
    }
    val x5109 = Pipeline(name="x5109",parent=x5110) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5103 = CounterChain(name = "x5103", ctr6).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5115_0 = Pipeline(name="x5115_0",parent=x5881) { implicit CU => 
      val x5112 = CU.temp
      val x4958_x5111 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5111), CU.ctr(x5011(0))), op=FixSub, results=List(x5112))
      Stage(operands=List(x5112, Const(20)), op=FixMin, results=List(CU.scalarOut(x5024_x5114_s)))
    }
    val x5172 = StreamController(name="x5172",parent=x5881) { implicit CU => 
    }
    val x5143 = StreamController(name="x5143",parent=x5172) { implicit CU => 
    }
    val x5143_0 = Pipeline(name="x5143_0",parent=x5143) { implicit CU => 
      val x5127 = CU.temp
      val x5121 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5121, CU.scalarOut(bus_3832_s)))
      Stage(operands=List(x5121, Const(64)), op=FixMod, results=List(x5127, CU.scalarOut(bus_3834_s)))
      Stage(operands=List(x5127, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3835_s), CU.scalarOut(x5118_b5995_x5142_b6003_s)))
      Stage(operands=List(x5121, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3837_s)))
    }
    val x5143_1 = Pipeline(name="x5143_1",parent=x5143) { implicit CU => 
      val x5123 = CU.temp
      val x5125 = CU.temp
      val x5124 = CU.temp
      val x5126 = CU.temp
      val x5122 =  ScalarFIFO(size=1).wtPort(bus_3837_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5122), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5118_b5996_x5142_b6004_s)))
      Stage(operands=List(CU.load(x5122), Const(64)), op=FixMod, results=List(x5123))
      Stage(operands=List(x5123, Const(0)), op=FixEql, results=List(x5124))
      Stage(operands=List(Const(64), x5123), op=FixSub, results=List(x5125))
      Stage(operands=List(x5124, Const(0), x5125), op=Mux, results=List(x5126, CU.scalarOut(bus_3843_s)))
      Stage(operands=List(x5126, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3844_s)))
    }
    val x5143_2 = Pipeline(name="x5143_2",parent=x5143) { implicit CU => 
      val x5128 = CU.temp
      val x5130 = CU.temp
      val x5139 = CU.temp
      val x5120 =  ScalarBuffer().wtPort(x5120_argin)
      val x5127 =  ScalarFIFO(size=1).wtPort(bus_3834_s)
      val x5121 =  ScalarFIFO(size=1).wtPort(bus_3832_s)
      val x5136 =  ScalarFIFO(size=1).wtPort(bus_3835_s)
      val x5138 =  ScalarFIFO(size=1).wtPort(bus_3844_s)
      val x5126 =  ScalarFIFO(size=1).wtPort(bus_3843_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5136)), op=FixAdd, results=List(x5139))
      Stage(operands=List(x5139, CU.load(x5138)), op=FixAdd, results=List(CU.scalarOut(x5118_b5994_x5142_b6002_s)))
      Stage(operands=List(Const(80), CU.load(x5127)), op=FixAdd, results=List(x5128))
      Stage(operands=List(x5128, CU.load(x5126)), op=FixAdd, results=List(CU.scalarOut(x5117_b5993_x5135_b6001_s)))
      Stage(operands=List(CU.load(x5121), CU.load(x5127)), op=FixSub, results=List(x5130))
      Stage(operands=List(x5130, CU.load(x5120)), op=FixAdd, results=List(CU.scalarOut(x5117_b5992_x5135_b6000_s)))
    }
    val x5144 = MemoryController(name="x5144",parent=x5172,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5117_b5992_x5144 =  ScalarFIFO(name="offset",size=1).wtPort(x5117_b5992_x5135_b6000_s)
      val x5117_b5993_x5144 =  ScalarFIFO(name="size",size=1).wtPort(x5117_b5993_x5135_b6001_s)
      CU.newVout("data", x5119_x5144_data_v)
    }
    val x5171 = Sequential(name="x5171",parent=x5172) { implicit CU => 
    }
    val x5155_0 = Pipeline(name="x5155_0",parent=x5171) { implicit CU => 
      val x5118_b5995_x5148_b5998 =  ScalarFIFO(size=16).wtPort(x5118_b5995_x5142_b6003_s)
      val x5118_b5994_x5148_b5997 =  ScalarFIFO(size=16).wtPort(x5118_b5994_x5142_b6002_s)
      val x5118_b5996_x5148_b5999 =  ScalarFIFO(size=16).wtPort(x5118_b5996_x5142_b6004_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5118_b5995_x5148_b5998)), op=Bypass, results=List(CU.scalarOut(x5145_x5150_s)))
      Stage(operands=List(CU.load(x5118_b5996_x5148_b5999)), op=Bypass, results=List(CU.scalarOut(x5146_x5152_s)))
      Stage(operands=List(CU.load(x5118_b5994_x5148_b5997)), op=Bypass, results=List(CU.scalarOut(x5147_x5154_s)))
    }
    val x5170 = Pipeline(name="x5170",parent=x5171) { implicit CU => 
      val x5146_x5160 =  ScalarBuffer().wtPort(x5146_x5152_s)
      val x5145_x5159 =  ScalarBuffer().wtPort(x5145_x5150_s)
      val x5147_x5156 =  ScalarBuffer().wtPort(x5147_x5154_s)
      val ctr7 = Counter(min=Const(0), max=x5147_x5156.load, step=Const(1), par=16) // Counter
      val x5158 = CounterChain(name = "x5158", ctr7).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5197 = StreamController(name="x5197",parent=x5881) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5174 = CounterChain(name = "x5174", ctr8).iter(20)
    }
    val x5187_0 = Pipeline(name="x5187_0",parent=x5197) { implicit CU => 
      val x5180 = CU.temp
      val x5178 = CU.temp
      val x5179 = CU.temp
      val x5177 =  ScalarBuffer().wtPort(x5177_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5174 = CounterChain.copy("x5197", "x5174")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5174(0))), op=FixAdd, results=List(x5178))
      Stage(operands=List(x5178, Const(96)), op=FixMul, results=List(x5179))
      Stage(operands=List(x5179, Const(4)), op=FixMul, results=List(x5180))
      Stage(operands=List(x5180, CU.load(x5177)), op=FixAdd, results=List(CU.scalarOut(x5175_b6005_x5186_b6007_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5175_b6006_x5186_b6008_s)))
    }
    val x5188 = MemoryController(name="x5188",parent=x5197,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5175_b6006_x5188 =  ScalarFIFO(name="size",size=1).wtPort(x5175_b6006_x5186_b6008_s)
      val x5175_b6005_x5188 =  ScalarFIFO(name="offset",size=1).wtPort(x5175_b6005_x5186_b6007_s)
      CU.newVout("data", x5176_x5188_data_v)
    }
    val x5196 = Pipeline(name="x5196",parent=x5197) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5190 = CounterChain(name = "x5190", ctr9).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5202_0 = Pipeline(name="x5202_0",parent=x5881) { implicit CU => 
      val x5199 = CU.temp
      val x4958_x5198 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5198), CU.ctr(x5011(0))), op=FixSub, results=List(x5199))
      Stage(operands=List(x5199, Const(20)), op=FixMin, results=List(CU.scalarOut(x5025_x5201_s)))
    }
    val x5259 = StreamController(name="x5259",parent=x5881) { implicit CU => 
    }
    val x5230 = StreamController(name="x5230",parent=x5259) { implicit CU => 
    }
    val x5230_0 = Pipeline(name="x5230_0",parent=x5230) { implicit CU => 
      val x5214 = CU.temp
      val x5208 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5208, CU.scalarOut(bus_3865_s)))
      Stage(operands=List(x5208, Const(64)), op=FixMod, results=List(x5214, CU.scalarOut(bus_3867_s)))
      Stage(operands=List(x5214, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3868_s), CU.scalarOut(x5205_b6014_x5229_b6022_s)))
      Stage(operands=List(x5208, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3870_s)))
    }
    val x5230_1 = Pipeline(name="x5230_1",parent=x5230) { implicit CU => 
      val x5213 = CU.temp
      val x5212 = CU.temp
      val x5210 = CU.temp
      val x5211 = CU.temp
      val x5209 =  ScalarFIFO(size=1).wtPort(bus_3870_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5209), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5205_b6015_x5229_b6023_s)))
      Stage(operands=List(CU.load(x5209), Const(64)), op=FixMod, results=List(x5210))
      Stage(operands=List(x5210, Const(0)), op=FixEql, results=List(x5211))
      Stage(operands=List(Const(64), x5210), op=FixSub, results=List(x5212))
      Stage(operands=List(x5211, Const(0), x5212), op=Mux, results=List(x5213, CU.scalarOut(bus_3876_s)))
      Stage(operands=List(x5213, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3877_s)))
    }
    val x5230_2 = Pipeline(name="x5230_2",parent=x5230) { implicit CU => 
      val x5217 = CU.temp
      val x5226 = CU.temp
      val x5215 = CU.temp
      val x5207 =  ScalarBuffer().wtPort(x5207_argin)
      val x5225 =  ScalarFIFO(size=1).wtPort(bus_3877_s)
      val x5208 =  ScalarFIFO(size=1).wtPort(bus_3865_s)
      val x5214 =  ScalarFIFO(size=1).wtPort(bus_3867_s)
      val x5223 =  ScalarFIFO(size=1).wtPort(bus_3868_s)
      val x5213 =  ScalarFIFO(size=1).wtPort(bus_3876_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5223)), op=FixAdd, results=List(x5226))
      Stage(operands=List(x5226, CU.load(x5225)), op=FixAdd, results=List(CU.scalarOut(x5205_b6013_x5229_b6021_s)))
      Stage(operands=List(Const(80), CU.load(x5214)), op=FixAdd, results=List(x5215))
      Stage(operands=List(x5215, CU.load(x5213)), op=FixAdd, results=List(CU.scalarOut(x5204_b6012_x5222_b6020_s)))
      Stage(operands=List(CU.load(x5208), CU.load(x5214)), op=FixSub, results=List(x5217))
      Stage(operands=List(x5217, CU.load(x5207)), op=FixAdd, results=List(CU.scalarOut(x5204_b6011_x5222_b6019_s)))
    }
    val x5231 = MemoryController(name="x5231",parent=x5259,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5204_b6012_x5231 =  ScalarFIFO(name="size",size=1).wtPort(x5204_b6012_x5222_b6020_s)
      val x5204_b6011_x5231 =  ScalarFIFO(name="offset",size=1).wtPort(x5204_b6011_x5222_b6019_s)
      CU.newVout("data", x5206_x5231_data_v)
    }
    val x5258 = Sequential(name="x5258",parent=x5259) { implicit CU => 
    }
    val x5242_0 = Pipeline(name="x5242_0",parent=x5258) { implicit CU => 
      val x5205_b6013_x5235_b6016 =  ScalarFIFO(size=16).wtPort(x5205_b6013_x5229_b6021_s)
      val x5205_b6015_x5235_b6018 =  ScalarFIFO(size=16).wtPort(x5205_b6015_x5229_b6023_s)
      val x5205_b6014_x5235_b6017 =  ScalarFIFO(size=16).wtPort(x5205_b6014_x5229_b6022_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5205_b6014_x5235_b6017)), op=Bypass, results=List(CU.scalarOut(x5232_x5237_s)))
      Stage(operands=List(CU.load(x5205_b6015_x5235_b6018)), op=Bypass, results=List(CU.scalarOut(x5233_x5239_s)))
      Stage(operands=List(CU.load(x5205_b6013_x5235_b6016)), op=Bypass, results=List(CU.scalarOut(x5234_x5241_s)))
    }
    val x5257 = Pipeline(name="x5257",parent=x5258) { implicit CU => 
      val x5233_x5247 =  ScalarBuffer().wtPort(x5233_x5239_s)
      val x5232_x5246 =  ScalarBuffer().wtPort(x5232_x5237_s)
      val x5234_x5243 =  ScalarBuffer().wtPort(x5234_x5241_s)
      val ctr10 = Counter(min=Const(0), max=x5234_x5243.load, step=Const(1), par=16) // Counter
      val x5245 = CounterChain(name = "x5245", ctr10).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5284 = StreamController(name="x5284",parent=x5881) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5261 = CounterChain(name = "x5261", ctr11).iter(20)
    }
    val x5274_0 = Pipeline(name="x5274_0",parent=x5284) { implicit CU => 
      val x5265 = CU.temp
      val x5267 = CU.temp
      val x5266 = CU.temp
      val x5264 =  ScalarBuffer().wtPort(x5264_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5261 = CounterChain.copy("x5284", "x5261")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5261(0))), op=FixAdd, results=List(x5265))
      Stage(operands=List(x5265, Const(96)), op=FixMul, results=List(x5266))
      Stage(operands=List(x5266, Const(4)), op=FixMul, results=List(x5267))
      Stage(operands=List(x5267, CU.load(x5264)), op=FixAdd, results=List(CU.scalarOut(x5262_b6024_x5273_b6026_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5262_b6025_x5273_b6027_s)))
    }
    val x5275 = MemoryController(name="x5275",parent=x5284,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5262_b6025_x5275 =  ScalarFIFO(name="size",size=1).wtPort(x5262_b6025_x5273_b6027_s)
      val x5262_b6024_x5275 =  ScalarFIFO(name="offset",size=1).wtPort(x5262_b6024_x5273_b6026_s)
      CU.newVout("data", x5263_x5275_data_v)
    }
    val x5283 = Pipeline(name="x5283",parent=x5284) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5277 = CounterChain(name = "x5277", ctr12).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5289_0 = Pipeline(name="x5289_0",parent=x5881) { implicit CU => 
      val x5286 = CU.temp
      val x4958_x5285 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5285), CU.ctr(x5011(0))), op=FixSub, results=List(x5286))
      Stage(operands=List(x5286, Const(20)), op=FixMin, results=List(CU.scalarOut(x5026_x5288_s)))
    }
    val x5346 = StreamController(name="x5346",parent=x5881) { implicit CU => 
    }
    val x5317 = StreamController(name="x5317",parent=x5346) { implicit CU => 
    }
    val x5317_0 = Pipeline(name="x5317_0",parent=x5317) { implicit CU => 
      val x5295 = CU.temp
      val x5301 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5295, CU.scalarOut(bus_3898_s)))
      Stage(operands=List(x5295, Const(64)), op=FixMod, results=List(x5301, CU.scalarOut(bus_3900_s)))
      Stage(operands=List(x5301, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3901_s), CU.scalarOut(x5292_b6033_x5316_b6041_s)))
      Stage(operands=List(x5295, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3903_s)))
    }
    val x5317_1 = Pipeline(name="x5317_1",parent=x5317) { implicit CU => 
      val x5300 = CU.temp
      val x5298 = CU.temp
      val x5297 = CU.temp
      val x5299 = CU.temp
      val x5296 =  ScalarFIFO(size=1).wtPort(bus_3903_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5296), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5292_b6034_x5316_b6042_s)))
      Stage(operands=List(CU.load(x5296), Const(64)), op=FixMod, results=List(x5297))
      Stage(operands=List(x5297, Const(0)), op=FixEql, results=List(x5298))
      Stage(operands=List(Const(64), x5297), op=FixSub, results=List(x5299))
      Stage(operands=List(x5298, Const(0), x5299), op=Mux, results=List(x5300, CU.scalarOut(bus_3909_s)))
      Stage(operands=List(x5300, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3910_s)))
    }
    val x5317_2 = Pipeline(name="x5317_2",parent=x5317) { implicit CU => 
      val x5313 = CU.temp
      val x5302 = CU.temp
      val x5304 = CU.temp
      val x5294 =  ScalarBuffer().wtPort(x5294_argin)
      val x5312 =  ScalarFIFO(size=1).wtPort(bus_3910_s)
      val x5295 =  ScalarFIFO(size=1).wtPort(bus_3898_s)
      val x5300 =  ScalarFIFO(size=1).wtPort(bus_3909_s)
      val x5301 =  ScalarFIFO(size=1).wtPort(bus_3900_s)
      val x5310 =  ScalarFIFO(size=1).wtPort(bus_3901_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5310)), op=FixAdd, results=List(x5313))
      Stage(operands=List(x5313, CU.load(x5312)), op=FixAdd, results=List(CU.scalarOut(x5292_b6032_x5316_b6040_s)))
      Stage(operands=List(Const(80), CU.load(x5301)), op=FixAdd, results=List(x5302))
      Stage(operands=List(x5302, CU.load(x5300)), op=FixAdd, results=List(CU.scalarOut(x5291_b6031_x5309_b6039_s)))
      Stage(operands=List(CU.load(x5295), CU.load(x5301)), op=FixSub, results=List(x5304))
      Stage(operands=List(x5304, CU.load(x5294)), op=FixAdd, results=List(CU.scalarOut(x5291_b6030_x5309_b6038_s)))
    }
    val x5318 = MemoryController(name="x5318",parent=x5346,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5291_b6031_x5318 =  ScalarFIFO(name="size",size=1).wtPort(x5291_b6031_x5309_b6039_s)
      val x5291_b6030_x5318 =  ScalarFIFO(name="offset",size=1).wtPort(x5291_b6030_x5309_b6038_s)
      CU.newVout("data", x5293_x5318_data_v)
    }
    val x5345 = Sequential(name="x5345",parent=x5346) { implicit CU => 
    }
    val x5329_0 = Pipeline(name="x5329_0",parent=x5345) { implicit CU => 
      val x5292_b6034_x5322_b6037 =  ScalarFIFO(size=16).wtPort(x5292_b6034_x5316_b6042_s)
      val x5292_b6033_x5322_b6036 =  ScalarFIFO(size=16).wtPort(x5292_b6033_x5316_b6041_s)
      val x5292_b6032_x5322_b6035 =  ScalarFIFO(size=16).wtPort(x5292_b6032_x5316_b6040_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5292_b6033_x5322_b6036)), op=Bypass, results=List(CU.scalarOut(x5319_x5324_s)))
      Stage(operands=List(CU.load(x5292_b6034_x5322_b6037)), op=Bypass, results=List(CU.scalarOut(x5320_x5326_s)))
      Stage(operands=List(CU.load(x5292_b6032_x5322_b6035)), op=Bypass, results=List(CU.scalarOut(x5321_x5328_s)))
    }
    val x5344 = Pipeline(name="x5344",parent=x5345) { implicit CU => 
      val x5319_x5333 =  ScalarBuffer().wtPort(x5319_x5324_s)
      val x5321_x5330 =  ScalarBuffer().wtPort(x5321_x5328_s)
      val x5320_x5334 =  ScalarBuffer().wtPort(x5320_x5326_s)
      val ctr13 = Counter(min=Const(0), max=x5321_x5330.load, step=Const(1), par=16) // Counter
      val x5332 = CounterChain(name = "x5332", ctr13).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5371 = StreamController(name="x5371",parent=x5881) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5348 = CounterChain(name = "x5348", ctr14).iter(20)
    }
    val x5361_0 = Pipeline(name="x5361_0",parent=x5371) { implicit CU => 
      val x5352 = CU.temp
      val x5353 = CU.temp
      val x5354 = CU.temp
      val x5351 =  ScalarBuffer().wtPort(x5351_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5348 = CounterChain.copy("x5371", "x5348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5348(0))), op=FixAdd, results=List(x5352))
      Stage(operands=List(x5352, Const(96)), op=FixMul, results=List(x5353))
      Stage(operands=List(x5353, Const(4)), op=FixMul, results=List(x5354))
      Stage(operands=List(x5354, CU.load(x5351)), op=FixAdd, results=List(CU.scalarOut(x5349_b6043_x5360_b6045_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5349_b6044_x5360_b6046_s)))
    }
    val x5362 = MemoryController(name="x5362",parent=x5371,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5349_b6043_x5362 =  ScalarFIFO(name="offset",size=1).wtPort(x5349_b6043_x5360_b6045_s)
      val x5349_b6044_x5362 =  ScalarFIFO(name="size",size=1).wtPort(x5349_b6044_x5360_b6046_s)
      CU.newVout("data", x5350_x5362_data_v)
    }
    val x5370 = Pipeline(name="x5370",parent=x5371) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5364 = CounterChain(name = "x5364", ctr15).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5376_0 = Pipeline(name="x5376_0",parent=x5881) { implicit CU => 
      val x5373 = CU.temp
      val x4958_x5372 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5372), CU.ctr(x5011(0))), op=FixSub, results=List(x5373))
      Stage(operands=List(x5373, Const(20)), op=FixMin, results=List(CU.scalarOut(x5027_x5375_s)))
    }
    val x5433 = StreamController(name="x5433",parent=x5881) { implicit CU => 
    }
    val x5404 = StreamController(name="x5404",parent=x5433) { implicit CU => 
    }
    val x5404_0 = Pipeline(name="x5404_0",parent=x5404) { implicit CU => 
      val x5388 = CU.temp
      val x5382 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5382, CU.scalarOut(bus_3931_s)))
      Stage(operands=List(x5382, Const(64)), op=FixMod, results=List(x5388, CU.scalarOut(bus_3933_s)))
      Stage(operands=List(x5388, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3934_s), CU.scalarOut(x5379_b6052_x5403_b6060_s)))
      Stage(operands=List(x5382, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3936_s)))
    }
    val x5404_1 = Pipeline(name="x5404_1",parent=x5404) { implicit CU => 
      val x5387 = CU.temp
      val x5386 = CU.temp
      val x5385 = CU.temp
      val x5384 = CU.temp
      val x5383 =  ScalarFIFO(size=1).wtPort(bus_3936_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5383), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5379_b6053_x5403_b6061_s)))
      Stage(operands=List(CU.load(x5383), Const(64)), op=FixMod, results=List(x5384))
      Stage(operands=List(x5384, Const(0)), op=FixEql, results=List(x5385))
      Stage(operands=List(Const(64), x5384), op=FixSub, results=List(x5386))
      Stage(operands=List(x5385, Const(0), x5386), op=Mux, results=List(x5387, CU.scalarOut(bus_3942_s)))
      Stage(operands=List(x5387, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3943_s)))
    }
    val x5404_2 = Pipeline(name="x5404_2",parent=x5404) { implicit CU => 
      val x5391 = CU.temp
      val x5389 = CU.temp
      val x5400 = CU.temp
      val x5387 =  ScalarFIFO(size=1).wtPort(bus_3942_s)
      val x5381 =  ScalarBuffer().wtPort(x5381_argin)
      val x5397 =  ScalarFIFO(size=1).wtPort(bus_3934_s)
      val x5388 =  ScalarFIFO(size=1).wtPort(bus_3933_s)
      val x5399 =  ScalarFIFO(size=1).wtPort(bus_3943_s)
      val x5382 =  ScalarFIFO(size=1).wtPort(bus_3931_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5397)), op=FixAdd, results=List(x5400))
      Stage(operands=List(x5400, CU.load(x5399)), op=FixAdd, results=List(CU.scalarOut(x5379_b6051_x5403_b6059_s)))
      Stage(operands=List(Const(80), CU.load(x5388)), op=FixAdd, results=List(x5389))
      Stage(operands=List(x5389, CU.load(x5387)), op=FixAdd, results=List(CU.scalarOut(x5378_b6050_x5396_b6058_s)))
      Stage(operands=List(CU.load(x5382), CU.load(x5388)), op=FixSub, results=List(x5391))
      Stage(operands=List(x5391, CU.load(x5381)), op=FixAdd, results=List(CU.scalarOut(x5378_b6049_x5396_b6057_s)))
    }
    val x5405 = MemoryController(name="x5405",parent=x5433,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5378_b6049_x5405 =  ScalarFIFO(name="offset",size=1).wtPort(x5378_b6049_x5396_b6057_s)
      val x5378_b6050_x5405 =  ScalarFIFO(name="size",size=1).wtPort(x5378_b6050_x5396_b6058_s)
      CU.newVout("data", x5380_x5405_data_v)
    }
    val x5432 = Sequential(name="x5432",parent=x5433) { implicit CU => 
    }
    val x5416_0 = Pipeline(name="x5416_0",parent=x5432) { implicit CU => 
      val x5379_b6052_x5409_b6055 =  ScalarFIFO(size=16).wtPort(x5379_b6052_x5403_b6060_s)
      val x5379_b6051_x5409_b6054 =  ScalarFIFO(size=16).wtPort(x5379_b6051_x5403_b6059_s)
      val x5379_b6053_x5409_b6056 =  ScalarFIFO(size=16).wtPort(x5379_b6053_x5403_b6061_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5379_b6052_x5409_b6055)), op=Bypass, results=List(CU.scalarOut(x5406_x5411_s)))
      Stage(operands=List(CU.load(x5379_b6053_x5409_b6056)), op=Bypass, results=List(CU.scalarOut(x5407_x5413_s)))
      Stage(operands=List(CU.load(x5379_b6051_x5409_b6054)), op=Bypass, results=List(CU.scalarOut(x5408_x5415_s)))
    }
    val x5431 = Pipeline(name="x5431",parent=x5432) { implicit CU => 
      val x5406_x5420 =  ScalarBuffer().wtPort(x5406_x5411_s)
      val x5408_x5417 =  ScalarBuffer().wtPort(x5408_x5415_s)
      val x5407_x5421 =  ScalarBuffer().wtPort(x5407_x5413_s)
      val ctr16 = Counter(min=Const(0), max=x5408_x5417.load, step=Const(1), par=16) // Counter
      val x5419 = CounterChain(name = "x5419", ctr16).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5458 = StreamController(name="x5458",parent=x5881) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5435 = CounterChain(name = "x5435", ctr17).iter(20)
    }
    val x5448_0 = Pipeline(name="x5448_0",parent=x5458) { implicit CU => 
      val x5439 = CU.temp
      val x5440 = CU.temp
      val x5441 = CU.temp
      val x5438 =  ScalarBuffer().wtPort(x5438_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5435 = CounterChain.copy("x5458", "x5435")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5435(0))), op=FixAdd, results=List(x5439))
      Stage(operands=List(x5439, Const(96)), op=FixMul, results=List(x5440))
      Stage(operands=List(x5440, Const(4)), op=FixMul, results=List(x5441))
      Stage(operands=List(x5441, CU.load(x5438)), op=FixAdd, results=List(CU.scalarOut(x5436_b6062_x5447_b6064_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5436_b6063_x5447_b6065_s)))
    }
    val x5449 = MemoryController(name="x5449",parent=x5458,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5436_b6063_x5449 =  ScalarFIFO(name="size",size=1).wtPort(x5436_b6063_x5447_b6065_s)
      val x5436_b6062_x5449 =  ScalarFIFO(name="offset",size=1).wtPort(x5436_b6062_x5447_b6064_s)
      CU.newVout("data", x5437_x5449_data_v)
    }
    val x5457 = Pipeline(name="x5457",parent=x5458) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5451 = CounterChain(name = "x5451", ctr18).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5463_0 = Pipeline(name="x5463_0",parent=x5881) { implicit CU => 
      val x5460 = CU.temp
      val x4958_x5459 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5459), CU.ctr(x5011(0))), op=FixSub, results=List(x5460))
      Stage(operands=List(x5460, Const(20)), op=FixMin, results=List(CU.scalarOut(x5028_x5462_s)))
    }
    val x5520 = StreamController(name="x5520",parent=x5881) { implicit CU => 
    }
    val x5491 = StreamController(name="x5491",parent=x5520) { implicit CU => 
    }
    val x5491_0 = Pipeline(name="x5491_0",parent=x5491) { implicit CU => 
      val x5475 = CU.temp
      val x5469 = CU.temp
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), Const(4)), op=FixMul, results=List(x5469, CU.scalarOut(bus_3964_s)))
      Stage(operands=List(x5469, Const(64)), op=FixMod, results=List(x5475, CU.scalarOut(bus_3966_s)))
      Stage(operands=List(x5475, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3967_s), CU.scalarOut(x5466_b6071_x5490_b6079_s)))
      Stage(operands=List(x5469, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_3969_s)))
    }
    val x5491_1 = Pipeline(name="x5491_1",parent=x5491) { implicit CU => 
      val x5473 = CU.temp
      val x5474 = CU.temp
      val x5472 = CU.temp
      val x5471 = CU.temp
      val x5470 =  ScalarFIFO(size=1).wtPort(bus_3969_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5470), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5466_b6072_x5490_b6080_s)))
      Stage(operands=List(CU.load(x5470), Const(64)), op=FixMod, results=List(x5471))
      Stage(operands=List(x5471, Const(0)), op=FixEql, results=List(x5472))
      Stage(operands=List(Const(64), x5471), op=FixSub, results=List(x5473))
      Stage(operands=List(x5472, Const(0), x5473), op=Mux, results=List(x5474, CU.scalarOut(bus_3975_s)))
      Stage(operands=List(x5474, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_3976_s)))
    }
    val x5491_2 = Pipeline(name="x5491_2",parent=x5491) { implicit CU => 
      val x5487 = CU.temp
      val x5478 = CU.temp
      val x5476 = CU.temp
      val x5468 =  ScalarBuffer().wtPort(x5468_argin)
      val x5469 =  ScalarFIFO(size=1).wtPort(bus_3964_s)
      val x5475 =  ScalarFIFO(size=1).wtPort(bus_3966_s)
      val x5474 =  ScalarFIFO(size=1).wtPort(bus_3975_s)
      val x5484 =  ScalarFIFO(size=1).wtPort(bus_3967_s)
      val x5486 =  ScalarFIFO(size=1).wtPort(bus_3976_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5484)), op=FixAdd, results=List(x5487))
      Stage(operands=List(x5487, CU.load(x5486)), op=FixAdd, results=List(CU.scalarOut(x5466_b6070_x5490_b6078_s)))
      Stage(operands=List(Const(80), CU.load(x5475)), op=FixAdd, results=List(x5476))
      Stage(operands=List(x5476, CU.load(x5474)), op=FixAdd, results=List(CU.scalarOut(x5465_b6069_x5483_b6077_s)))
      Stage(operands=List(CU.load(x5469), CU.load(x5475)), op=FixSub, results=List(x5478))
      Stage(operands=List(x5478, CU.load(x5468)), op=FixAdd, results=List(CU.scalarOut(x5465_b6068_x5483_b6076_s)))
    }
    val x5492 = MemoryController(name="x5492",parent=x5520,offchip=x4963_oc, mctpe=TileLoad) { implicit CU => 
      val x5465_b6069_x5492 =  ScalarFIFO(name="size",size=1).wtPort(x5465_b6069_x5483_b6077_s)
      val x5465_b6068_x5492 =  ScalarFIFO(name="offset",size=1).wtPort(x5465_b6068_x5483_b6076_s)
      CU.newVout("data", x5467_x5492_data_v)
    }
    val x5519 = Sequential(name="x5519",parent=x5520) { implicit CU => 
    }
    val x5503_0 = Pipeline(name="x5503_0",parent=x5519) { implicit CU => 
      val x5466_b6070_x5496_b6073 =  ScalarFIFO(size=16).wtPort(x5466_b6070_x5490_b6078_s)
      val x5466_b6072_x5496_b6075 =  ScalarFIFO(size=16).wtPort(x5466_b6072_x5490_b6080_s)
      val x5466_b6071_x5496_b6074 =  ScalarFIFO(size=16).wtPort(x5466_b6071_x5490_b6079_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5466_b6071_x5496_b6074)), op=Bypass, results=List(CU.scalarOut(x5493_x5498_s)))
      Stage(operands=List(CU.load(x5466_b6072_x5496_b6075)), op=Bypass, results=List(CU.scalarOut(x5494_x5500_s)))
      Stage(operands=List(CU.load(x5466_b6070_x5496_b6073)), op=Bypass, results=List(CU.scalarOut(x5495_x5502_s)))
    }
    val x5518 = Pipeline(name="x5518",parent=x5519) { implicit CU => 
      val x5493_x5507 =  ScalarBuffer().wtPort(x5493_x5498_s)
      val x5495_x5504 =  ScalarBuffer().wtPort(x5495_x5502_s)
      val x5494_x5508 =  ScalarBuffer().wtPort(x5494_x5500_s)
      val ctr19 = Counter(min=Const(0), max=x5495_x5504.load, step=Const(1), par=16) // Counter
      val x5506 = CounterChain(name = "x5506", ctr19).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5545 = StreamController(name="x5545",parent=x5881) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5522 = CounterChain(name = "x5522", ctr20).iter(20)
    }
    val x5535_0 = Pipeline(name="x5535_0",parent=x5545) { implicit CU => 
      val x5526 = CU.temp
      val x5528 = CU.temp
      val x5527 = CU.temp
      val x5525 =  ScalarBuffer().wtPort(x5525_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      val x5522 = CounterChain.copy("x5545", "x5522")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5011(0)), CU.ctr(x5522(0))), op=FixAdd, results=List(x5526))
      Stage(operands=List(x5526, Const(96)), op=FixMul, results=List(x5527))
      Stage(operands=List(x5527, Const(4)), op=FixMul, results=List(x5528))
      Stage(operands=List(x5528, CU.load(x5525)), op=FixAdd, results=List(CU.scalarOut(x5523_b6081_x5534_b6083_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5523_b6082_x5534_b6084_s)))
    }
    val x5536 = MemoryController(name="x5536",parent=x5545,offchip=x4961_oc, mctpe=TileLoad) { implicit CU => 
      val x5523_b6082_x5536 =  ScalarFIFO(name="size",size=1).wtPort(x5523_b6082_x5534_b6084_s)
      val x5523_b6081_x5536 =  ScalarFIFO(name="offset",size=1).wtPort(x5523_b6081_x5534_b6083_s)
      CU.newVout("data", x5524_x5536_data_v)
    }
    val x5544 = Pipeline(name="x5544",parent=x5545) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5538 = CounterChain(name = "x5538", ctr21).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5550_0 = Pipeline(name="x5550_0",parent=x5881) { implicit CU => 
      val x5547 = CU.temp
      val x4958_x5546 =  ScalarBuffer().wtPort(x4958_argin)
      val x5011 = CounterChain.copy("x5881", "x5011")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4958_x5546), CU.ctr(x5011(0))), op=FixSub, results=List(x5547))
      Stage(operands=List(x5547, Const(20)), op=FixMin, results=List(CU.scalarOut(x5029_x5549_s)))
    }
    val x5553_dsp0 = MemoryPipeline(name="x5553_dsp0",parent="x5605") { implicit CU => 
      val b6095 = CU.temp
      val b6147 = CU.temp
      val x5602_x5602 =  VectorFIFO(size=1).wtPort(x5553_x5602_v)
      val x5594 = CounterChain.copy("x5603_0", "x5594")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5553_x5846 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5602_x5602.readPort).rdPort(x5553_x5846_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5594(0)), Const(96)), op=FixMul, results=List(b6095))
      WAStage(operands=List(b6095, CU.ctr(x5594(1))), op=FixAdd, results=List(x5553_x5846.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6147))
      RAStage(operands=List(b6147, CU.ctr(x5844(1))), op=FixAdd, results=List(x5553_x5846.readAddr))
    }
    val x5553_dsp1 = MemoryPipeline(name="x5553_dsp1",parent="x5605") { implicit CU => 
      val b6095 = CU.temp
      val b6093 = CU.temp
      val x5602_x5602 =  VectorFIFO(size=1).wtPort(x5553_x5602_v)
      val x5594 = CounterChain.copy("x5603_0", "x5594")
      val x5553_x5598 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5602_x5602.readPort).rdPort(x5553_x5598_x5603_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5594(0)), Const(96)), op=FixMul, results=List(b6095))
      WAStage(operands=List(b6095, CU.ctr(x5594(1))), op=FixAdd, results=List(x5553_x5598.writeAddr))
      RAStage(operands=List(CU.ctr(x5594(0)), Const(96)), op=FixMul, results=List(b6093))
      RAStage(operands=List(b6093, CU.ctr(x5594(1))), op=FixAdd, results=List(x5553_x5598.readAddr))
    }
    val x5554_dsp0 = MemoryPipeline(name="x5554_dsp0",parent="x5652") { implicit CU => 
      val b6149 = CU.temp
      val b6105 = CU.temp
      val x5649_x5649 =  VectorFIFO(size=1).wtPort(x5554_x5649_v)
      val x5641 = CounterChain.copy("x5650_0", "x5641")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5554_x5847 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5649_x5649.readPort).rdPort(x5554_x5847_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5641(0)), Const(96)), op=FixMul, results=List(b6105))
      WAStage(operands=List(b6105, CU.ctr(x5641(1))), op=FixAdd, results=List(x5554_x5847.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6149))
      RAStage(operands=List(b6149, CU.ctr(x5844(1))), op=FixAdd, results=List(x5554_x5847.readAddr))
    }
    val x5554_dsp1 = MemoryPipeline(name="x5554_dsp1",parent="x5652") { implicit CU => 
      val b6103 = CU.temp
      val b6105 = CU.temp
      val x5649_x5649 =  VectorFIFO(size=1).wtPort(x5554_x5649_v)
      val x5641 = CounterChain.copy("x5650_0", "x5641")
      val x5554_x5645 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5649_x5649.readPort).rdPort(x5554_x5645_x5650_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5641(0)), Const(96)), op=FixMul, results=List(b6105))
      WAStage(operands=List(b6105, CU.ctr(x5641(1))), op=FixAdd, results=List(x5554_x5645.writeAddr))
      RAStage(operands=List(CU.ctr(x5641(0)), Const(96)), op=FixMul, results=List(b6103))
      RAStage(operands=List(b6103, CU.ctr(x5641(1))), op=FixAdd, results=List(x5554_x5645.readAddr))
    }
    val x5555_dsp0 = MemoryPipeline(name="x5555_dsp0",parent="x5699") { implicit CU => 
      val b6151 = CU.temp
      val b6115 = CU.temp
      val x5696_x5696 =  VectorFIFO(size=1).wtPort(x5555_x5696_v)
      val x5688 = CounterChain.copy("x5697_0", "x5688")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5555_x5848 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5696_x5696.readPort).rdPort(x5555_x5848_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5688(0)), Const(96)), op=FixMul, results=List(b6115))
      WAStage(operands=List(b6115, CU.ctr(x5688(1))), op=FixAdd, results=List(x5555_x5848.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6151))
      RAStage(operands=List(b6151, CU.ctr(x5844(1))), op=FixAdd, results=List(x5555_x5848.readAddr))
    }
    val x5555_dsp1 = MemoryPipeline(name="x5555_dsp1",parent="x5699") { implicit CU => 
      val b6113 = CU.temp
      val b6115 = CU.temp
      val x5696_x5696 =  VectorFIFO(size=1).wtPort(x5555_x5696_v)
      val x5688 = CounterChain.copy("x5697_0", "x5688")
      val x5555_x5692 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5696_x5696.readPort).rdPort(x5555_x5692_x5697_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5688(0)), Const(96)), op=FixMul, results=List(b6115))
      WAStage(operands=List(b6115, CU.ctr(x5688(1))), op=FixAdd, results=List(x5555_x5692.writeAddr))
      RAStage(operands=List(CU.ctr(x5688(0)), Const(96)), op=FixMul, results=List(b6113))
      RAStage(operands=List(b6113, CU.ctr(x5688(1))), op=FixAdd, results=List(x5555_x5692.readAddr))
    }
    val x5556_dsp0 = MemoryPipeline(name="x5556_dsp0",parent="x5746") { implicit CU => 
      val b6125 = CU.temp
      val b6153 = CU.temp
      val x5743_x5743 =  VectorFIFO(size=1).wtPort(x5556_x5743_v)
      val x5735 = CounterChain.copy("x5744_0", "x5735")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5556_x5849 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5743_x5743.readPort).rdPort(x5556_x5849_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5735(0)), Const(96)), op=FixMul, results=List(b6125))
      WAStage(operands=List(b6125, CU.ctr(x5735(1))), op=FixAdd, results=List(x5556_x5849.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6153))
      RAStage(operands=List(b6153, CU.ctr(x5844(1))), op=FixAdd, results=List(x5556_x5849.readAddr))
    }
    val x5556_dsp1 = MemoryPipeline(name="x5556_dsp1",parent="x5746") { implicit CU => 
      val b6125 = CU.temp
      val b6123 = CU.temp
      val x5743_x5743 =  VectorFIFO(size=1).wtPort(x5556_x5743_v)
      val x5735 = CounterChain.copy("x5744_0", "x5735")
      val x5556_x5739 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5743_x5743.readPort).rdPort(x5556_x5739_x5744_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5735(0)), Const(96)), op=FixMul, results=List(b6125))
      WAStage(operands=List(b6125, CU.ctr(x5735(1))), op=FixAdd, results=List(x5556_x5739.writeAddr))
      RAStage(operands=List(CU.ctr(x5735(0)), Const(96)), op=FixMul, results=List(b6123))
      RAStage(operands=List(b6123, CU.ctr(x5735(1))), op=FixAdd, results=List(x5556_x5739.readAddr))
    }
    val x5557_dsp0 = MemoryPipeline(name="x5557_dsp0",parent="x5793") { implicit CU => 
      val b6135 = CU.temp
      val b6155 = CU.temp
      val x5790_x5790 =  VectorFIFO(size=1).wtPort(x5557_x5790_v)
      val x5782 = CounterChain.copy("x5791_0", "x5782")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5557_x5850 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5790_x5790.readPort).rdPort(x5557_x5850_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5782(0)), Const(96)), op=FixMul, results=List(b6135))
      WAStage(operands=List(b6135, CU.ctr(x5782(1))), op=FixAdd, results=List(x5557_x5850.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6155))
      RAStage(operands=List(b6155, CU.ctr(x5844(1))), op=FixAdd, results=List(x5557_x5850.readAddr))
    }
    val x5557_dsp1 = MemoryPipeline(name="x5557_dsp1",parent="x5793") { implicit CU => 
      val b6135 = CU.temp
      val b6133 = CU.temp
      val x5790_x5790 =  VectorFIFO(size=1).wtPort(x5557_x5790_v)
      val x5782 = CounterChain.copy("x5791_0", "x5782")
      val x5557_x5786 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5790_x5790.readPort).rdPort(x5557_x5786_x5791_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5782(0)), Const(96)), op=FixMul, results=List(b6135))
      WAStage(operands=List(b6135, CU.ctr(x5782(1))), op=FixAdd, results=List(x5557_x5786.writeAddr))
      RAStage(operands=List(CU.ctr(x5782(0)), Const(96)), op=FixMul, results=List(b6133))
      RAStage(operands=List(b6133, CU.ctr(x5782(1))), op=FixAdd, results=List(x5557_x5786.readAddr))
    }
    val x5558_dsp0 = MemoryPipeline(name="x5558_dsp0",parent="x5840") { implicit CU => 
      val b6145 = CU.temp
      val b6157 = CU.temp
      val x5837_x5837 =  VectorFIFO(size=1).wtPort(x5558_x5837_v)
      val x5829 = CounterChain.copy("x5838_0", "x5829")
      val x5844 = CounterChain.copy("x5879", "x5844")
      val x5558_x5851 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5837_x5837.readPort).rdPort(x5558_x5851_x5879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5829(0)), Const(96)), op=FixMul, results=List(b6145))
      WAStage(operands=List(b6145, CU.ctr(x5829(1))), op=FixAdd, results=List(x5558_x5851.writeAddr))
      RAStage(operands=List(CU.ctr(x5844(0)), Const(96)), op=FixMul, results=List(b6157))
      RAStage(operands=List(b6157, CU.ctr(x5844(1))), op=FixAdd, results=List(x5558_x5851.readAddr))
    }
    val x5558_dsp1 = MemoryPipeline(name="x5558_dsp1",parent="x5840") { implicit CU => 
      val b6145 = CU.temp
      val b6143 = CU.temp
      val x5837_x5837 =  VectorFIFO(size=1).wtPort(x5558_x5837_v)
      val x5829 = CounterChain.copy("x5838_0", "x5829")
      val x5558_x5833 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5837_x5837.readPort).rdPort(x5558_x5833_x5838_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5829(0)), Const(96)), op=FixMul, results=List(b6145))
      WAStage(operands=List(b6145, CU.ctr(x5829(1))), op=FixAdd, results=List(x5558_x5833.writeAddr))
      RAStage(operands=List(CU.ctr(x5829(0)), Const(96)), op=FixMul, results=List(b6143))
      RAStage(operands=List(b6143, CU.ctr(x5829(1))), op=FixAdd, results=List(x5558_x5833.readAddr))
    }
    val x5605 = MetaPipeline(name="x5605",parent=x5881) { implicit CU => 
      val x5024_x5559 =  ScalarBuffer().wtPort(x5024_x5114_s)
      val ctr22 = Counter(min=Const(0), max=x5024_x5559.load, step=Const(1), par=1) // Counter
      val x5561 = CounterChain(name = "x5561", ctr22).iter(1)
    }
    val x5562_dsp0 = MemoryPipeline(name="x5562_dsp0",parent="x5605") { implicit CU => 
      val x5578_x5578 =  VectorFIFO(size=1).wtPort(x5562_x5578_v)
      val x5565 = CounterChain.copy("x5579", "x5565")
      val x5582 = CounterChain.copy("x5591_0", "x5582")
      val x5562_x5587 =  SRAM(size=96,banking = Strided(1)).wtPort(x5578_x5578.readPort).rdPort(x5562_x5587_x5591_v).rdAddr(x5582(1)).wtAddr(x5565(0))
      var stage: List[Stage] = Nil
    }
    val x5562_dsp1 = MemoryPipeline(name="x5562_dsp1",parent="x5605") { implicit CU => 
      val x5578_x5578 =  VectorFIFO(size=1).wtPort(x5562_x5578_v)
      val x5565 = CounterChain.copy("x5579", "x5565")
      val x5582 = CounterChain.copy("x5591_0", "x5582")
      val x5562_x5586 =  SRAM(size=96,banking = Strided(1)).wtPort(x5578_x5578.readPort).rdPort(x5562_x5586_x5591_v).rdAddr(x5582(0)).wtAddr(x5565(0))
      var stage: List[Stage] = Nil
    }
    val x5563_dsp0 = MemoryPipeline(name="x5563_dsp0",parent="x5605") { implicit CU => 
      val b6089 = CU.temp
      val b6091 = CU.temp
      val x5590_x5590 =  VectorFIFO(size=1).wtPort(x5563_x5590_v)
      val x5582 = CounterChain.copy("x5591_0", "x5582")
      val x5594 = CounterChain.copy("x5603_0", "x5594")
      val x5563_x5597 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5590_x5590.readPort).rdPort(x5563_x5597_x5603_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5582(0)), Const(96)), op=FixMul, results=List(b6089))
      WAStage(operands=List(b6089, CU.ctr(x5582(1))), op=FixAdd, results=List(x5563_x5597.writeAddr))
      RAStage(operands=List(CU.ctr(x5594(0)), Const(96)), op=FixMul, results=List(b6091))
      RAStage(operands=List(b6091, CU.ctr(x5594(1))), op=FixAdd, results=List(x5563_x5597.readAddr))
    }
    val x5579 = StreamController(name="x5579",parent=x5605) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5565 = CounterChain(name = "x5565", ctr23).iter(6)
    }
    val x5579_0 = Pipeline(name="x5579_0",parent=x5579) { implicit CU => 
      val x5575 = CU.temp
      val x5570_x5570 =  VectorFIFO(size=1).wtPort(x4972_x5570_x5579_v)
      val x5569_x5569 =  VectorFIFO(size=1).wtPort(x5012_x5569_x5579_v)
      val x5571_x5571 =  VectorFIFO(size=1).wtPort(x4971_x5571_x5579_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5569_x5569), Const(1)), op=FixEql, results=List(x5575))
      Stage(operands=List(x5575, CU.load(x5570_x5570), CU.load(x5571_x5571)), op=Mux, results=List(CU.vecOut(bus_4094_v)))
    }
    val x5579_1 = Pipeline(name="x5579_1",parent=x5579) { implicit CU => 
      val x5568_x5568 =  VectorFIFO(size=1).wtPort(x5018_x5568_x5579_v)
      val x5576 =  VectorFIFO(size=1).wtPort(bus_4094_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5568_x5568), CU.load(x5576)), op=FixSub, results=List(CU.vecOut(x5562_x5578_v)))
    }
    val x5591_0 = Pipeline(name="x5591_0",parent=x5605) { implicit CU => 
      val x5586_x5586 =  VectorFIFO(size=1).wtPort(x5562_x5586_x5591_v)
      val x5587_x5587 =  VectorFIFO(size=1).wtPort(x5562_x5587_x5591_v)
      val ctr24 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr25 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5582 = CounterChain(name = "x5582", ctr24, ctr25).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5586_x5586), CU.load(x5587_x5587)), op=FixMul, results=List(CU.vecOut(x5563_x5590_v)))
    }
    val x5603_0 = Pipeline(name="x5603_0",parent=x5605) { implicit CU => 
      val x5597_x5597 =  VectorFIFO(size=1).wtPort(x5563_x5597_x5603_v)
      val x5598_x5598 =  VectorFIFO(size=1).wtPort(x5553_x5598_x5603_v)
      val ctr26 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr27 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5594 = CounterChain(name = "x5594", ctr26, ctr27).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5597_x5597), CU.load(x5598_x5598)), op=FixAdd, results=List(CU.vecOut(x5553_x5602_v)))
    }
    val x5652 = MetaPipeline(name="x5652",parent=x5881) { implicit CU => 
      val x5025_x5606 =  ScalarBuffer().wtPort(x5025_x5201_s)
      val ctr28 = Counter(min=Const(0), max=x5025_x5606.load, step=Const(1), par=1) // Counter
      val x5608 = CounterChain(name = "x5608", ctr28).iter(1)
    }
    val x5609_dsp0 = MemoryPipeline(name="x5609_dsp0",parent="x5652") { implicit CU => 
      val x5625_x5625 =  VectorFIFO(size=1).wtPort(x5609_x5625_v)
      val x5612 = CounterChain.copy("x5626", "x5612")
      val x5629 = CounterChain.copy("x5638_0", "x5629")
      val x5609_x5634 =  SRAM(size=96,banking = Strided(1)).wtPort(x5625_x5625.readPort).rdPort(x5609_x5634_x5638_v).rdAddr(x5629(1)).wtAddr(x5612(0))
      var stage: List[Stage] = Nil
    }
    val x5609_dsp1 = MemoryPipeline(name="x5609_dsp1",parent="x5652") { implicit CU => 
      val x5625_x5625 =  VectorFIFO(size=1).wtPort(x5609_x5625_v)
      val x5612 = CounterChain.copy("x5626", "x5612")
      val x5629 = CounterChain.copy("x5638_0", "x5629")
      val x5609_x5633 =  SRAM(size=96,banking = Strided(1)).wtPort(x5625_x5625.readPort).rdPort(x5609_x5633_x5638_v).rdAddr(x5629(0)).wtAddr(x5612(0))
      var stage: List[Stage] = Nil
    }
    val x5610_dsp0 = MemoryPipeline(name="x5610_dsp0",parent="x5652") { implicit CU => 
      val b6101 = CU.temp
      val b6099 = CU.temp
      val x5637_x5637 =  VectorFIFO(size=1).wtPort(x5610_x5637_v)
      val x5629 = CounterChain.copy("x5638_0", "x5629")
      val x5641 = CounterChain.copy("x5650_0", "x5641")
      val x5610_x5644 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5637_x5637.readPort).rdPort(x5610_x5644_x5650_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5629(0)), Const(96)), op=FixMul, results=List(b6099))
      WAStage(operands=List(b6099, CU.ctr(x5629(1))), op=FixAdd, results=List(x5610_x5644.writeAddr))
      RAStage(operands=List(CU.ctr(x5641(0)), Const(96)), op=FixMul, results=List(b6101))
      RAStage(operands=List(b6101, CU.ctr(x5641(1))), op=FixAdd, results=List(x5610_x5644.readAddr))
    }
    val x5626 = StreamController(name="x5626",parent=x5652) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5612 = CounterChain(name = "x5612", ctr29).iter(6)
    }
    val x5626_0 = Pipeline(name="x5626_0",parent=x5626) { implicit CU => 
      val x5622 = CU.temp
      val x5618_x5618 =  VectorFIFO(size=1).wtPort(x4971_x5618_x5626_v)
      val x5617_x5617 =  VectorFIFO(size=1).wtPort(x4972_x5617_x5626_v)
      val x5616_x5616 =  VectorFIFO(size=1).wtPort(x5013_x5616_x5626_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5616_x5616), Const(1)), op=FixEql, results=List(x5622))
      Stage(operands=List(x5622, CU.load(x5617_x5617), CU.load(x5618_x5618)), op=Mux, results=List(CU.vecOut(bus_4117_v)))
    }
    val x5626_1 = Pipeline(name="x5626_1",parent=x5626) { implicit CU => 
      val x5623 =  VectorFIFO(size=1).wtPort(bus_4117_v)
      val x5615_x5615 =  VectorFIFO(size=1).wtPort(x5019_x5615_x5626_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5615_x5615), CU.load(x5623)), op=FixSub, results=List(CU.vecOut(x5609_x5625_v)))
    }
    val x5638_0 = Pipeline(name="x5638_0",parent=x5652) { implicit CU => 
      val x5633_x5633 =  VectorFIFO(size=1).wtPort(x5609_x5633_x5638_v)
      val x5634_x5634 =  VectorFIFO(size=1).wtPort(x5609_x5634_x5638_v)
      val ctr30 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5629 = CounterChain(name = "x5629", ctr30, ctr31).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5633_x5633), CU.load(x5634_x5634)), op=FixMul, results=List(CU.vecOut(x5610_x5637_v)))
    }
    val x5650_0 = Pipeline(name="x5650_0",parent=x5652) { implicit CU => 
      val x5645_x5645 =  VectorFIFO(size=1).wtPort(x5554_x5645_x5650_v)
      val x5644_x5644 =  VectorFIFO(size=1).wtPort(x5610_x5644_x5650_v)
      val ctr32 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr33 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5641 = CounterChain(name = "x5641", ctr32, ctr33).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5644_x5644), CU.load(x5645_x5645)), op=FixAdd, results=List(CU.vecOut(x5554_x5649_v)))
    }
    val x5699 = MetaPipeline(name="x5699",parent=x5881) { implicit CU => 
      val x5026_x5653 =  ScalarBuffer().wtPort(x5026_x5288_s)
      val ctr34 = Counter(min=Const(0), max=x5026_x5653.load, step=Const(1), par=1) // Counter
      val x5655 = CounterChain(name = "x5655", ctr34).iter(1)
    }
    val x5656_dsp0 = MemoryPipeline(name="x5656_dsp0",parent="x5699") { implicit CU => 
      val x5672_x5672 =  VectorFIFO(size=1).wtPort(x5656_x5672_v)
      val x5659 = CounterChain.copy("x5673", "x5659")
      val x5676 = CounterChain.copy("x5685_0", "x5676")
      val x5656_x5681 =  SRAM(size=96,banking = Strided(1)).wtPort(x5672_x5672.readPort).rdPort(x5656_x5681_x5685_v).rdAddr(x5676(1)).wtAddr(x5659(0))
      var stage: List[Stage] = Nil
    }
    val x5656_dsp1 = MemoryPipeline(name="x5656_dsp1",parent="x5699") { implicit CU => 
      val x5672_x5672 =  VectorFIFO(size=1).wtPort(x5656_x5672_v)
      val x5659 = CounterChain.copy("x5673", "x5659")
      val x5676 = CounterChain.copy("x5685_0", "x5676")
      val x5656_x5680 =  SRAM(size=96,banking = Strided(1)).wtPort(x5672_x5672.readPort).rdPort(x5656_x5680_x5685_v).rdAddr(x5676(0)).wtAddr(x5659(0))
      var stage: List[Stage] = Nil
    }
    val x5657_dsp0 = MemoryPipeline(name="x5657_dsp0",parent="x5699") { implicit CU => 
      val b6111 = CU.temp
      val b6109 = CU.temp
      val x5684_x5684 =  VectorFIFO(size=1).wtPort(x5657_x5684_v)
      val x5676 = CounterChain.copy("x5685_0", "x5676")
      val x5688 = CounterChain.copy("x5697_0", "x5688")
      val x5657_x5691 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5684_x5684.readPort).rdPort(x5657_x5691_x5697_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5676(0)), Const(96)), op=FixMul, results=List(b6109))
      WAStage(operands=List(b6109, CU.ctr(x5676(1))), op=FixAdd, results=List(x5657_x5691.writeAddr))
      RAStage(operands=List(CU.ctr(x5688(0)), Const(96)), op=FixMul, results=List(b6111))
      RAStage(operands=List(b6111, CU.ctr(x5688(1))), op=FixAdd, results=List(x5657_x5691.readAddr))
    }
    val x5673 = StreamController(name="x5673",parent=x5699) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5659 = CounterChain(name = "x5659", ctr35).iter(6)
    }
    val x5673_0 = Pipeline(name="x5673_0",parent=x5673) { implicit CU => 
      val x5669 = CU.temp
      val x5664_x5664 =  VectorFIFO(size=1).wtPort(x4972_x5664_x5673_v)
      val x5663_x5663 =  VectorFIFO(size=1).wtPort(x5014_x5663_x5673_v)
      val x5665_x5665 =  VectorFIFO(size=1).wtPort(x4971_x5665_x5673_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5663_x5663), Const(1)), op=FixEql, results=List(x5669))
      Stage(operands=List(x5669, CU.load(x5664_x5664), CU.load(x5665_x5665)), op=Mux, results=List(CU.vecOut(bus_4140_v)))
    }
    val x5673_1 = Pipeline(name="x5673_1",parent=x5673) { implicit CU => 
      val x5662_x5662 =  VectorFIFO(size=1).wtPort(x5020_x5662_x5673_v)
      val x5670 =  VectorFIFO(size=1).wtPort(bus_4140_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5662_x5662), CU.load(x5670)), op=FixSub, results=List(CU.vecOut(x5656_x5672_v)))
    }
    val x5685_0 = Pipeline(name="x5685_0",parent=x5699) { implicit CU => 
      val x5681_x5681 =  VectorFIFO(size=1).wtPort(x5656_x5681_x5685_v)
      val x5680_x5680 =  VectorFIFO(size=1).wtPort(x5656_x5680_x5685_v)
      val ctr36 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr37 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5676 = CounterChain(name = "x5676", ctr36, ctr37).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5680_x5680), CU.load(x5681_x5681)), op=FixMul, results=List(CU.vecOut(x5657_x5684_v)))
    }
    val x5697_0 = Pipeline(name="x5697_0",parent=x5699) { implicit CU => 
      val x5692_x5692 =  VectorFIFO(size=1).wtPort(x5555_x5692_x5697_v)
      val x5691_x5691 =  VectorFIFO(size=1).wtPort(x5657_x5691_x5697_v)
      val ctr38 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr39 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5688 = CounterChain(name = "x5688", ctr38, ctr39).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5691_x5691), CU.load(x5692_x5692)), op=FixAdd, results=List(CU.vecOut(x5555_x5696_v)))
    }
    val x5746 = MetaPipeline(name="x5746",parent=x5881) { implicit CU => 
      val x5027_x5700 =  ScalarBuffer().wtPort(x5027_x5375_s)
      val ctr40 = Counter(min=Const(0), max=x5027_x5700.load, step=Const(1), par=1) // Counter
      val x5702 = CounterChain(name = "x5702", ctr40).iter(1)
    }
    val x5703_dsp0 = MemoryPipeline(name="x5703_dsp0",parent="x5746") { implicit CU => 
      val x5719_x5719 =  VectorFIFO(size=1).wtPort(x5703_x5719_v)
      val x5706 = CounterChain.copy("x5720", "x5706")
      val x5723 = CounterChain.copy("x5732_0", "x5723")
      val x5703_x5728 =  SRAM(size=96,banking = Strided(1)).wtPort(x5719_x5719.readPort).rdPort(x5703_x5728_x5732_v).rdAddr(x5723(1)).wtAddr(x5706(0))
      var stage: List[Stage] = Nil
    }
    val x5703_dsp1 = MemoryPipeline(name="x5703_dsp1",parent="x5746") { implicit CU => 
      val x5719_x5719 =  VectorFIFO(size=1).wtPort(x5703_x5719_v)
      val x5706 = CounterChain.copy("x5720", "x5706")
      val x5723 = CounterChain.copy("x5732_0", "x5723")
      val x5703_x5727 =  SRAM(size=96,banking = Strided(1)).wtPort(x5719_x5719.readPort).rdPort(x5703_x5727_x5732_v).rdAddr(x5723(0)).wtAddr(x5706(0))
      var stage: List[Stage] = Nil
    }
    val x5704_dsp0 = MemoryPipeline(name="x5704_dsp0",parent="x5746") { implicit CU => 
      val b6121 = CU.temp
      val b6119 = CU.temp
      val x5731_x5731 =  VectorFIFO(size=1).wtPort(x5704_x5731_v)
      val x5723 = CounterChain.copy("x5732_0", "x5723")
      val x5735 = CounterChain.copy("x5744_0", "x5735")
      val x5704_x5738 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5731_x5731.readPort).rdPort(x5704_x5738_x5744_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5723(0)), Const(96)), op=FixMul, results=List(b6119))
      WAStage(operands=List(b6119, CU.ctr(x5723(1))), op=FixAdd, results=List(x5704_x5738.writeAddr))
      RAStage(operands=List(CU.ctr(x5735(0)), Const(96)), op=FixMul, results=List(b6121))
      RAStage(operands=List(b6121, CU.ctr(x5735(1))), op=FixAdd, results=List(x5704_x5738.readAddr))
    }
    val x5720 = StreamController(name="x5720",parent=x5746) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5706 = CounterChain(name = "x5706", ctr41).iter(6)
    }
    val x5720_0 = Pipeline(name="x5720_0",parent=x5720) { implicit CU => 
      val x5716 = CU.temp
      val x5711_x5711 =  VectorFIFO(size=1).wtPort(x4972_x5711_x5720_v)
      val x5710_x5710 =  VectorFIFO(size=1).wtPort(x5015_x5710_x5720_v)
      val x5712_x5712 =  VectorFIFO(size=1).wtPort(x4971_x5712_x5720_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5710_x5710), Const(1)), op=FixEql, results=List(x5716))
      Stage(operands=List(x5716, CU.load(x5711_x5711), CU.load(x5712_x5712)), op=Mux, results=List(CU.vecOut(bus_4163_v)))
    }
    val x5720_1 = Pipeline(name="x5720_1",parent=x5720) { implicit CU => 
      val x5717 =  VectorFIFO(size=1).wtPort(bus_4163_v)
      val x5709_x5709 =  VectorFIFO(size=1).wtPort(x5021_x5709_x5720_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5709_x5709), CU.load(x5717)), op=FixSub, results=List(CU.vecOut(x5703_x5719_v)))
    }
    val x5732_0 = Pipeline(name="x5732_0",parent=x5746) { implicit CU => 
      val x5728_x5728 =  VectorFIFO(size=1).wtPort(x5703_x5728_x5732_v)
      val x5727_x5727 =  VectorFIFO(size=1).wtPort(x5703_x5727_x5732_v)
      val ctr42 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr43 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5723 = CounterChain(name = "x5723", ctr42, ctr43).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5727_x5727), CU.load(x5728_x5728)), op=FixMul, results=List(CU.vecOut(x5704_x5731_v)))
    }
    val x5744_0 = Pipeline(name="x5744_0",parent=x5746) { implicit CU => 
      val x5738_x5738 =  VectorFIFO(size=1).wtPort(x5704_x5738_x5744_v)
      val x5739_x5739 =  VectorFIFO(size=1).wtPort(x5556_x5739_x5744_v)
      val ctr44 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr45 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5735 = CounterChain(name = "x5735", ctr44, ctr45).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5738_x5738), CU.load(x5739_x5739)), op=FixAdd, results=List(CU.vecOut(x5556_x5743_v)))
    }
    val x5793 = MetaPipeline(name="x5793",parent=x5881) { implicit CU => 
      val x5028_x5747 =  ScalarBuffer().wtPort(x5028_x5462_s)
      val ctr46 = Counter(min=Const(0), max=x5028_x5747.load, step=Const(1), par=1) // Counter
      val x5749 = CounterChain(name = "x5749", ctr46).iter(1)
    }
    val x5750_dsp0 = MemoryPipeline(name="x5750_dsp0",parent="x5793") { implicit CU => 
      val x5766_x5766 =  VectorFIFO(size=1).wtPort(x5750_x5766_v)
      val x5753 = CounterChain.copy("x5767", "x5753")
      val x5770 = CounterChain.copy("x5779_0", "x5770")
      val x5750_x5775 =  SRAM(size=96,banking = Strided(1)).wtPort(x5766_x5766.readPort).rdPort(x5750_x5775_x5779_v).rdAddr(x5770(1)).wtAddr(x5753(0))
      var stage: List[Stage] = Nil
    }
    val x5750_dsp1 = MemoryPipeline(name="x5750_dsp1",parent="x5793") { implicit CU => 
      val x5766_x5766 =  VectorFIFO(size=1).wtPort(x5750_x5766_v)
      val x5753 = CounterChain.copy("x5767", "x5753")
      val x5770 = CounterChain.copy("x5779_0", "x5770")
      val x5750_x5774 =  SRAM(size=96,banking = Strided(1)).wtPort(x5766_x5766.readPort).rdPort(x5750_x5774_x5779_v).rdAddr(x5770(0)).wtAddr(x5753(0))
      var stage: List[Stage] = Nil
    }
    val x5751_dsp0 = MemoryPipeline(name="x5751_dsp0",parent="x5793") { implicit CU => 
      val b6129 = CU.temp
      val b6131 = CU.temp
      val x5778_x5778 =  VectorFIFO(size=1).wtPort(x5751_x5778_v)
      val x5770 = CounterChain.copy("x5779_0", "x5770")
      val x5782 = CounterChain.copy("x5791_0", "x5782")
      val x5751_x5785 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5778_x5778.readPort).rdPort(x5751_x5785_x5791_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5770(0)), Const(96)), op=FixMul, results=List(b6129))
      WAStage(operands=List(b6129, CU.ctr(x5770(1))), op=FixAdd, results=List(x5751_x5785.writeAddr))
      RAStage(operands=List(CU.ctr(x5782(0)), Const(96)), op=FixMul, results=List(b6131))
      RAStage(operands=List(b6131, CU.ctr(x5782(1))), op=FixAdd, results=List(x5751_x5785.readAddr))
    }
    val x5767 = StreamController(name="x5767",parent=x5793) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5753 = CounterChain(name = "x5753", ctr47).iter(6)
    }
    val x5767_0 = Pipeline(name="x5767_0",parent=x5767) { implicit CU => 
      val x5763 = CU.temp
      val x5759_x5759 =  VectorFIFO(size=1).wtPort(x4971_x5759_x5767_v)
      val x5758_x5758 =  VectorFIFO(size=1).wtPort(x4972_x5758_x5767_v)
      val x5757_x5757 =  VectorFIFO(size=1).wtPort(x5016_x5757_x5767_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5757_x5757), Const(1)), op=FixEql, results=List(x5763))
      Stage(operands=List(x5763, CU.load(x5758_x5758), CU.load(x5759_x5759)), op=Mux, results=List(CU.vecOut(bus_4186_v)))
    }
    val x5767_1 = Pipeline(name="x5767_1",parent=x5767) { implicit CU => 
      val x5756_x5756 =  VectorFIFO(size=1).wtPort(x5022_x5756_x5767_v)
      val x5764 =  VectorFIFO(size=1).wtPort(bus_4186_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5756_x5756), CU.load(x5764)), op=FixSub, results=List(CU.vecOut(x5750_x5766_v)))
    }
    val x5779_0 = Pipeline(name="x5779_0",parent=x5793) { implicit CU => 
      val x5774_x5774 =  VectorFIFO(size=1).wtPort(x5750_x5774_x5779_v)
      val x5775_x5775 =  VectorFIFO(size=1).wtPort(x5750_x5775_x5779_v)
      val ctr48 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr49 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5770 = CounterChain(name = "x5770", ctr48, ctr49).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5774_x5774), CU.load(x5775_x5775)), op=FixMul, results=List(CU.vecOut(x5751_x5778_v)))
    }
    val x5791_0 = Pipeline(name="x5791_0",parent=x5793) { implicit CU => 
      val x5786_x5786 =  VectorFIFO(size=1).wtPort(x5557_x5786_x5791_v)
      val x5785_x5785 =  VectorFIFO(size=1).wtPort(x5751_x5785_x5791_v)
      val ctr50 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr51 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5782 = CounterChain(name = "x5782", ctr50, ctr51).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5785_x5785), CU.load(x5786_x5786)), op=FixAdd, results=List(CU.vecOut(x5557_x5790_v)))
    }
    val x5840 = MetaPipeline(name="x5840",parent=x5881) { implicit CU => 
      val x5029_x5794 =  ScalarBuffer().wtPort(x5029_x5549_s)
      val ctr52 = Counter(min=Const(0), max=x5029_x5794.load, step=Const(1), par=1) // Counter
      val x5796 = CounterChain(name = "x5796", ctr52).iter(1)
    }
    val x5797_dsp0 = MemoryPipeline(name="x5797_dsp0",parent="x5840") { implicit CU => 
      val x5813_x5813 =  VectorFIFO(size=1).wtPort(x5797_x5813_v)
      val x5800 = CounterChain.copy("x5814", "x5800")
      val x5817 = CounterChain.copy("x5826_0", "x5817")
      val x5797_x5822 =  SRAM(size=96,banking = Strided(1)).wtPort(x5813_x5813.readPort).rdPort(x5797_x5822_x5826_v).rdAddr(x5817(1)).wtAddr(x5800(0))
      var stage: List[Stage] = Nil
    }
    val x5797_dsp1 = MemoryPipeline(name="x5797_dsp1",parent="x5840") { implicit CU => 
      val x5813_x5813 =  VectorFIFO(size=1).wtPort(x5797_x5813_v)
      val x5800 = CounterChain.copy("x5814", "x5800")
      val x5817 = CounterChain.copy("x5826_0", "x5817")
      val x5797_x5821 =  SRAM(size=96,banking = Strided(1)).wtPort(x5813_x5813.readPort).rdPort(x5797_x5821_x5826_v).rdAddr(x5817(0)).wtAddr(x5800(0))
      var stage: List[Stage] = Nil
    }
    val x5798_dsp0 = MemoryPipeline(name="x5798_dsp0",parent="x5840") { implicit CU => 
      val b6139 = CU.temp
      val b6141 = CU.temp
      val x5825_x5825 =  VectorFIFO(size=1).wtPort(x5798_x5825_v)
      val x5817 = CounterChain.copy("x5826_0", "x5817")
      val x5829 = CounterChain.copy("x5838_0", "x5829")
      val x5798_x5832 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5825_x5825.readPort).rdPort(x5798_x5832_x5838_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5817(0)), Const(96)), op=FixMul, results=List(b6139))
      WAStage(operands=List(b6139, CU.ctr(x5817(1))), op=FixAdd, results=List(x5798_x5832.writeAddr))
      RAStage(operands=List(CU.ctr(x5829(0)), Const(96)), op=FixMul, results=List(b6141))
      RAStage(operands=List(b6141, CU.ctr(x5829(1))), op=FixAdd, results=List(x5798_x5832.readAddr))
    }
    val x5814 = StreamController(name="x5814",parent=x5840) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5800 = CounterChain(name = "x5800", ctr53).iter(6)
    }
    val x5814_0 = Pipeline(name="x5814_0",parent=x5814) { implicit CU => 
      val x5810 = CU.temp
      val x5804_x5804 =  VectorFIFO(size=1).wtPort(x5017_x5804_x5814_v)
      val x5806_x5806 =  VectorFIFO(size=1).wtPort(x4971_x5806_x5814_v)
      val x5805_x5805 =  VectorFIFO(size=1).wtPort(x4972_x5805_x5814_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5804_x5804), Const(1)), op=FixEql, results=List(x5810))
      Stage(operands=List(x5810, CU.load(x5805_x5805), CU.load(x5806_x5806)), op=Mux, results=List(CU.vecOut(bus_4209_v)))
    }
    val x5814_1 = Pipeline(name="x5814_1",parent=x5814) { implicit CU => 
      val x5803_x5803 =  VectorFIFO(size=1).wtPort(x5023_x5803_x5814_v)
      val x5811 =  VectorFIFO(size=1).wtPort(bus_4209_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5803_x5803), CU.load(x5811)), op=FixSub, results=List(CU.vecOut(x5797_x5813_v)))
    }
    val x5826_0 = Pipeline(name="x5826_0",parent=x5840) { implicit CU => 
      val x5822_x5822 =  VectorFIFO(size=1).wtPort(x5797_x5822_x5826_v)
      val x5821_x5821 =  VectorFIFO(size=1).wtPort(x5797_x5821_x5826_v)
      val ctr54 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5817 = CounterChain(name = "x5817", ctr54, ctr55).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5821_x5821), CU.load(x5822_x5822)), op=FixMul, results=List(CU.vecOut(x5798_x5825_v)))
    }
    val x5838_0 = Pipeline(name="x5838_0",parent=x5840) { implicit CU => 
      val x5833_x5833 =  VectorFIFO(size=1).wtPort(x5558_x5833_x5838_v)
      val x5832_x5832 =  VectorFIFO(size=1).wtPort(x5798_x5832_x5838_v)
      val ctr56 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr57 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5829 = CounterChain(name = "x5829", ctr56, ctr57).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5832_x5832), CU.load(x5833_x5833)), op=FixAdd, results=List(CU.vecOut(x5558_x5837_v)))
    }
    val x5879 = StreamController(name="x5879",parent=x5881) { implicit CU => 
      val ctr58 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr59 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5844 = CounterChain(name = "x5844", ctr58, ctr59).iter(9216)
    }
    val x5879_0 = Pipeline(name="x5879_0",parent=x5879) { implicit CU => 
      val x5846_x5846 =  VectorFIFO(size=1).wtPort(x5553_x5846_x5879_v)
      val x5847_x5847 =  VectorFIFO(size=1).wtPort(x5554_x5847_x5879_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5846_x5846), CU.load(x5847_x5847)), op=FixAdd, results=List(CU.scalarOut(bus_4223_s)))
    }
    val x5879_1 = Pipeline(name="x5879_1",parent=x5879) { implicit CU => 
      val x5867 = CU.temp
      val x5849_x5849 =  VectorFIFO(size=1).wtPort(x5556_x5849_x5879_v)
      val x5848_x5848 =  VectorFIFO(size=1).wtPort(x5555_x5848_x5879_v)
      val x5860 =  ScalarFIFO(size=1).wtPort(bus_4223_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5848_x5848), CU.load(x5849_x5849)), op=FixAdd, results=List(x5867))
      Stage(operands=List(CU.load(x5860), x5867), op=FixAdd, results=List(CU.scalarOut(bus_4228_s)))
    }
    val x5879_2 = Pipeline(name="x5879_2",parent=x5879) { implicit CU => 
      val x5873 = CU.temp
      val x5875 = CU.temp
      val x5852_x5852 =  VectorFIFO(size=1).wtPort(x5008_x5852_x5879_v)
      val x5851_x5851 =  VectorFIFO(size=1).wtPort(x5558_x5851_x5879_v)
      val x5869 =  ScalarFIFO(size=1).wtPort(bus_4228_s)
      val x5850_x5850 =  VectorFIFO(size=1).wtPort(x5557_x5850_x5879_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5850_x5850), CU.load(x5851_x5851)), op=FixAdd, results=List(x5873))
      Stage(operands=List(CU.load(x5869), x5873), op=FixAdd, results=List(x5875))
      Stage(operands=List(x5875, CU.load(x5852_x5852)), op=FixAdd, results=List(CU.vecOut(x5008_x5878_v)))
    }
    val x5912 = StreamController(name="x5912",parent=x5913) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5883 = CounterChain(name = "x5883", ctr60).iter(96)
    }
    val x5904 = Sequential(name="x5904",parent=x5912) { implicit CU => 
    }
    val x5895_0 = Pipeline(name="x5895_0",parent=x5904) { implicit CU => 
      val x5889 = CU.temp
      val x5888 = CU.temp
      val x5887 =  ScalarBuffer().wtPort(x5887_argin)
      val x5883 = CounterChain.copy("x5912", "x5883")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5883(0)), Const(96)), op=FixMul, results=List(x5888))
      Stage(operands=List(x5888, Const(4)), op=FixMul, results=List(x5889))
      Stage(operands=List(x5889, CU.load(x5887)), op=FixAdd, results=List(CU.scalarOut(x5884_b6163_x5894_b6165_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5884_b6164_x5894_b6166_s)))
    }
    val x5903 = Pipeline(name="x5903",parent=x5904) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5897 = CounterChain(name = "x5897", ctr61).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5905 = MemoryController(name="x5905",parent=x5912,offchip=x4966_oc, mctpe=TileStore) { implicit CU => 
      val x5884_b6163_x5905 =  ScalarFIFO(name="offset",size=1).wtPort(x5884_b6163_x5894_b6165_s)
      val x5884_b6164_x5905 =  ScalarFIFO(name="size",size=1).wtPort(x5884_b6164_x5894_b6166_s)
      val x5885_x5905 =  VectorFIFO(name="data",size=1).wtPort(x5008_x5899_x5903_v)
    }
    
  }
}
