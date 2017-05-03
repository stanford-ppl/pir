import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SMV extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5255_argin = ArgIn("x5255")
    val x5252_b6032_x5277_b6040_s = Scalar("x5252_b6032_x5277_b6040")
    val x4938_oc = OffChip("x4938")
    val x5252_b6033_x5277_b6041_s = Scalar("x5252_b6033_x5277_b6041")
    val bus_1438_s = Scalar("bus_1438")
    val x5864_b6114_x5873_b6116_s = Scalar("x5864_b6114_x5873_b6116")
    val x4955_x5242_x5244_v = Vector("x4955_x5242_x5244")
    val x5253_b6034_x5284_b6042_s = Scalar("x5253_b6034_x5284_b6042")
    val x5558_x5567_s = Scalar("x5558_x5567")
    val x5122_b6022_x5153_b6030_s = Scalar("x5122_b6022_x5153_b6030")
    val bus_1431_s = Scalar("bus_1431")
    val bus_1429_s = Scalar("bus_1429")
    val x5638_x5819_x5829_v = Vector("x5638_x5819_x5829")
    val x5867_argin = ArgIn("x5867")
    val x5864_b6115_x5873_b6117_s = Scalar("x5864_b6115_x5873_b6117")
    val bus_1539_s = Scalar("bus_1539")
    val x4940_oc = OffChip("x4940")
    val x5440_x5443_s = Scalar("x5440_x5443")
    val x5596_argin = ArgIn("x5596")
    val bus_1305_s = Scalar("bus_1305")
    val bus_1314_s = Scalar("bus_1314")
    val x5322_b6048_x5353_b6056_s = Scalar("x5322_b6048_x5353_b6056")
    val x5017_argin = ArgIn("x5017")
    val x4958_b5991_x4966_b5993_s = Scalar("x4958_b5991_x4966_b5993")
    val x5789_x5801_s = Scalar("x5789_x5801")
    val x5523_x5555_data_v = Vector("x5523_x5555_data")
    val x5437_x5595_x5602_v = Vector("x5437_x5595_x5602")
    val bus_1363_s = Scalar("bus_1363")
    val x4957_x5642_x5644_v = Vector("x4957_x5642_x5644")
    val x5687_x5694_s = Scalar("x5687_x5694")
    val x5757_x5765_s = Scalar("x5757_x5765")
    val x5521_b6071_x5546_b6079_s = Scalar("x5521_b6071_x5546_b6079")
    val x5655_argin = ArgIn("x5655")
    val x5756_x5763_s = Scalar("x5756_x5763")
    val x4952_x5632_v = Vector("x4952_x5632")
    val bus_1506_s = Scalar("bus_1506")
    val bus_1455_s = Scalar("bus_1455")
    val x5455_argin = ArgIn("x5455")
    val x4951_x5432_v = Vector("x4951_x5432")
    val x5240_x5243_s = Scalar("x5240_x5243")
    val x5321_b6046_x5346_b6054_s = Scalar("x5321_b6046_x5346_b6054")
    val x4937_oc = OffChip("x4937")
    val x5653_b6088_x5684_b6096_s = Scalar("x5653_b6088_x5684_b6096")
    val bus_1352_s = Scalar("bus_1352")
    val x5213_x5227_s = Scalar("x5213_x5227")
    val bus_1464_s = Scalar("bus_1464")
    val bus_1528_s = Scalar("bus_1528")
    val x5836_b6110_x5845_b6112_s = Scalar("x5836_b6110_x5845_b6112")
    val x5652_b6084_x5677_b6092_s = Scalar("x5652_b6084_x5677_b6092")
    val bus_1504_s = Scalar("bus_1504")
    val x5157_x5165_s = Scalar("x5157_x5165")
    val x5156_x5163_s = Scalar("x5156_x5163")
    val bus_1456_s = Scalar("bus_1456")
    val x4954_x5042_x5044_v = Vector("x4954_x5042_x5044")
    val x5758_x5767_s = Scalar("x5758_x5767")
    val bus_1502_s = Scalar("bus_1502")
    val x5390_x5403_data_v = Vector("x5390_x5403_data")
    val x4956_x5442_x5444_v = Vector("x4956_x5442_x5444")
    val x5722_b6100_x5753_b6108_s = Scalar("x5722_b6100_x5753_b6108")
    val x4977_b5994_x4985_b5996_s = Scalar("x4977_b5994_x4985_b5996")
    val bus_1457_s = Scalar("bus_1457")
    val x5689_x5698_s = Scalar("x5689_x5698")
    val x5287_x5294_s = Scalar("x5287_x5294")
    val x5396_argin = ArgIn("x5396")
    val x5239_x5420_x5429_v = Vector("x5239_x5420_x5429")
    val x4996_b5998_x5004_b6000_s = Scalar("x4996_b5998_x5004_b6000")
    val x5892_b6118_x5901_b6120_s = Scalar("x5892_b6118_x5901_b6120")
    val x5522_b6074_x5553_b6082_s = Scalar("x5522_b6074_x5553_b6082")
    val x5836_b6111_x5845_b6113_s = Scalar("x5836_b6111_x5845_b6113")
    val bus_1279_s = Scalar("bus_1279")
    val x5016_x5025_data_v = Vector("x5016_x5025_data")
    val x5253_b6036_x5284_b6044_s = Scalar("x5253_b6036_x5284_b6044")
    val x5053_b6009_x5084_b6017_s = Scalar("x5053_b6009_x5084_b6017")
    val bus_1381_s = Scalar("bus_1381")
    val x4958_b5990_x4966_b5992_s = Scalar("x4958_b5990_x4966_b5992")
    val x5589_x5601_s = Scalar("x5589_x5601")
    val x4951_x5878_x5882_v = Vector("x4951_x5878_x5882")
    val x5452_b6058_x5477_b6066_s = Scalar("x5452_b6058_x5477_b6066")
    val bus_1530_s = Scalar("bus_1530")
    val x4977_b5995_x4985_b5997_s = Scalar("x4977_b5995_x4985_b5997")
    val x5613_x5627_s = Scalar("x5613_x5627")
    val x4953_x5934_x5938_v = Vector("x4953_x5934_x5938")
    val x5288_x5296_s = Scalar("x5288_x5296")
    val x5052_b6006_x5077_b6014_s = Scalar("x5052_b6006_x5077_b6014")
    val x5637_x5795_x5802_v = Vector("x5637_x5795_x5802")
    val x5557_x5565_s = Scalar("x5557_x5565")
    val x5813_x5827_s = Scalar("x5813_x5827")
    val x5895_argin = ArgIn("x5895")
    val bus_1281_s = Scalar("bus_1281")
    val x5122_b6023_x5153_b6031_s = Scalar("x5122_b6023_x5153_b6031")
    val bus_1277_s = Scalar("bus_1277")
    val bus_1355_s = Scalar("bus_1355")
    val x5190_x5203_data_v = Vector("x5190_x5203_data")
    val x4978_x4987_data_v = Vector("x4978_x4987_data")
    val x5652_b6085_x5677_b6093_s = Scalar("x5652_b6085_x5677_b6093")
    val x5688_x5696_s = Scalar("x5688_x5696")
    val bus_1378_s = Scalar("bus_1378")
    val bus_1532_s = Scalar("bus_1532")
    val x5489_x5498_s = Scalar("x5489_x5498")
    val x5389_x5401_s = Scalar("x5389_x5401")
    val x5722_b6099_x5753_b6107_s = Scalar("x5722_b6099_x5753_b6107")
    val x5189_x5201_s = Scalar("x5189_x5201")
    val x5122_b6021_x5153_b6029_s = Scalar("x5122_b6021_x5153_b6029")
    val x5054_x5086_data_v = Vector("x5054_x5086_data")
    val x5322_b6049_x5353_b6057_s = Scalar("x5322_b6049_x5353_b6057")
    val x5487_x5494_s = Scalar("x5487_x5494")
    val x5640_x5643_s = Scalar("x5640_x5643")
    val x5053_b6008_x5084_b6016_s = Scalar("x5053_b6008_x5084_b6016")
    val x5053_b6010_x5084_b6018_s = Scalar("x5053_b6010_x5084_b6018")
    val bus_1288_s = Scalar("bus_1288")
    val x5039_x5220_x5229_v = Vector("x5039_x5220_x5229")
    val x5653_b6087_x5684_b6095_s = Scalar("x5653_b6087_x5684_b6095")
    val x5522_b6075_x5553_b6083_s = Scalar("x5522_b6075_x5553_b6083")
    val x4952_x5906_x5910_v = Vector("x4952_x5906_x5910")
    val x4959_x4968_data_v = Vector("x4959_x4968_data")
    val x4950_x5232_v = Vector("x4950_x5232")
    val x5839_argin = ArgIn("x5839")
    val x5488_x5496_s = Scalar("x5488_x5496")
    val x5237_x5395_x5402_v = Vector("x5237_x5395_x5402")
    val bus_1513_s = Scalar("bus_1513")
    val bus_1354_s = Scalar("bus_1354")
    val x5357_x5365_s = Scalar("x5357_x5365")
    val x5045_x5048_s = Scalar("x5045_x5048")
    val x5238_x5419_x5429_v = Vector("x5238_x5419_x5429")
    val x5653_b6086_x5684_b6094_s = Scalar("x5653_b6086_x5684_b6094")
    val bus_1280_s = Scalar("bus_1280")
    val x5040_x5043_s = Scalar("x5040_x5043")
    val x5724_argin = ArgIn("x5724")
    val x5015_b6003_x5023_b6005_s = Scalar("x5015_b6003_x5023_b6005")
    val x5920_b6123_x5929_b6125_s = Scalar("x5920_b6123_x5929_b6125")
    val x5453_b6062_x5484_b6070_s = Scalar("x5453_b6062_x5484_b6070")
    val x5639_x5820_x5829_v = Vector("x5639_x5820_x5829")
    val bus_1382_s = Scalar("bus_1382")
    val x5453_b6061_x5484_b6069_s = Scalar("x5453_b6061_x5484_b6069")
    val x5324_argin = ArgIn("x5324")
    val x5645_x5648_s = Scalar("x5645_x5648")
    val bus_1430_s = Scalar("bus_1430")
    val x5089_x5098_s = Scalar("x5089_x5098")
    val x5037_x5195_x5202_v = Vector("x5037_x5195_x5202")
    val bus_1389_s = Scalar("bus_1389")
    val x5323_x5355_data_v = Vector("x5323_x5355_data")
    val x5453_b6060_x5484_b6068_s = Scalar("x5453_b6060_x5484_b6068")
    val x5556_x5563_s = Scalar("x5556_x5563")
    val x5158_x5167_s = Scalar("x5158_x5167")
    val bus_1453_s = Scalar("bus_1453")
    val x5038_x5219_x5229_v = Vector("x5038_x5219_x5229")
    val x4960_argin = ArgIn("x4960")
    val x5253_b6035_x5284_b6043_s = Scalar("x5253_b6035_x5284_b6043")
    val x5123_x5155_data_v = Vector("x5123_x5155_data")
    val x5055_argin = ArgIn("x5055")
    val x5439_x5620_x5629_v = Vector("x5439_x5620_x5629")
    val bus_1303_s = Scalar("bus_1303")
    val x5722_b6101_x5753_b6109_s = Scalar("x5722_b6101_x5753_b6109")
    val x5321_b6045_x5346_b6053_s = Scalar("x5321_b6045_x5346_b6053")
    val x5413_x5427_s = Scalar("x5413_x5427")
    val x4996_b5999_x5004_b6001_s = Scalar("x4996_b5999_x5004_b6001")
    val x5445_x5448_s = Scalar("x5445_x5448")
    val x5452_b6059_x5477_b6067_s = Scalar("x5452_b6059_x5477_b6067")
    val x5654_x5686_data_v = Vector("x5654_x5686_data")
    val x5454_x5486_data_v = Vector("x5454_x5486_data")
    val bus_1307_s = Scalar("bus_1307")
    val bus_1531_s = Scalar("bus_1531")
    val x5358_x5367_s = Scalar("x5358_x5367")
    val x4933_argin = ArgIn("x4933")
    val x4936_oc = OffChip("x4936")
    val x5245_x5248_s = Scalar("x5245_x5248")
    val x5438_x5619_x5629_v = Vector("x5438_x5619_x5629")
    val bus_1356_s = Scalar("bus_1356")
    val x4935_oc = OffChip("x4935")
    val x4979_argin = ArgIn("x4979")
    val x5289_x5298_s = Scalar("x5289_x5298")
    val x5121_b6019_x5146_b6027_s = Scalar("x5121_b6019_x5146_b6027")
    val x4998_argin = ArgIn("x4998")
    val x5196_argin = ArgIn("x5196")
    val x5522_b6073_x5553_b6081_s = Scalar("x5522_b6073_x5553_b6081")
    val bus_1380_s = Scalar("bus_1380")
    val x5121_b6020_x5146_b6028_s = Scalar("x5121_b6020_x5146_b6028")
    val x5254_x5286_data_v = Vector("x5254_x5286_data")
    val x4953_x5832_v = Vector("x4953_x5832")
    val bus_1427_s = Scalar("bus_1427")
    val x5356_x5363_s = Scalar("x5356_x5363")
    val x5721_b6097_x5746_b6105_s = Scalar("x5721_b6097_x5746_b6105")
    val x5015_b6002_x5023_b6004_s = Scalar("x5015_b6002_x5023_b6004")
    val x5590_x5603_data_v = Vector("x5590_x5603_data")
    val x5124_argin = ArgIn("x5124")
    val x5923_argin = ArgIn("x5923")
    val bus_1505_s = Scalar("bus_1505")
    val x5721_b6098_x5746_b6106_s = Scalar("x5721_b6098_x5746_b6106")
    val x5790_x5803_data_v = Vector("x5790_x5803_data")
    val x4997_x5006_data_v = Vector("x4997_x5006_data")
    val x5521_b6072_x5546_b6080_s = Scalar("x5521_b6072_x5546_b6080")
    val x5892_b6119_x5901_b6121_s = Scalar("x5892_b6119_x5901_b6121")
    val x5524_argin = ArgIn("x5524")
    val x5920_b6122_x5929_b6124_s = Scalar("x5920_b6122_x5929_b6124")
    val x4950_x5850_x5854_v = Vector("x4950_x5850_x5854")
    val x5088_x5096_s = Scalar("x5088_x5096")
    val x5322_b6047_x5353_b6055_s = Scalar("x5322_b6047_x5353_b6055")
    val bus_1306_s = Scalar("bus_1306")
    val x5723_x5755_data_v = Vector("x5723_x5755_data")
    val x5796_argin = ArgIn("x5796")
    val x5087_x5094_s = Scalar("x5087_x5094")
    val x5052_b6007_x5077_b6015_s = Scalar("x5052_b6007_x5077_b6015")
    val x5950 = Sequential(name="x5950",parent=top) { implicit CU => 
    }
    val x5949 = MetaPipeline(name="x5949",parent=x5950) { implicit CU => 
      val x4933_x4947 =  ScalarBuffer().wtPort(x4933_argin)
      val ctr1 = Counter(min=Const(0), max=x4933_x4947.load, step=Const(384), par=4) // Counter
      val x4949 = CounterChain(name = "x4949", ctr1).iter(1)
    }
    val x4950_dsp0 = MemoryPipeline(name="x4950_dsp0",parent="x5949") { implicit CU => 
      val x5232_x5232 =  VectorFIFO(size=1).wtPort(x4950_x5232_v)
      val x5036 = CounterChain.copy("x5234", "x5036")
      val x5848 = CounterChain.copy("x5854", "x5848")
      val x4950_x5850 =  SRAM(size=384,banking = Strided(1)).wtPort(x5232_x5232.readPort).rdPort(x4950_x5850_x5854_v).rdAddr(x5848(0)).wtAddr(x5036(0))
      var stage: List[Stage] = Nil
    }
    val x4951_dsp0 = MemoryPipeline(name="x4951_dsp0",parent="x5949") { implicit CU => 
      val x5432_x5432 =  VectorFIFO(size=1).wtPort(x4951_x5432_v)
      val x5236 = CounterChain.copy("x5434", "x5236")
      val x5876 = CounterChain.copy("x5882", "x5876")
      val x4951_x5878 =  SRAM(size=384,banking = Strided(1)).wtPort(x5432_x5432.readPort).rdPort(x4951_x5878_x5882_v).rdAddr(x5876(0)).wtAddr(x5236(0))
      var stage: List[Stage] = Nil
    }
    val x4952_dsp0 = MemoryPipeline(name="x4952_dsp0",parent="x5949") { implicit CU => 
      val x5632_x5632 =  VectorFIFO(size=1).wtPort(x4952_x5632_v)
      val x5436 = CounterChain.copy("x5634", "x5436")
      val x5904 = CounterChain.copy("x5910", "x5904")
      val x4952_x5906 =  SRAM(size=384,banking = Strided(1)).wtPort(x5632_x5632.readPort).rdPort(x4952_x5906_x5910_v).rdAddr(x5904(0)).wtAddr(x5436(0))
      var stage: List[Stage] = Nil
    }
    val x4953_dsp0 = MemoryPipeline(name="x4953_dsp0",parent="x5949") { implicit CU => 
      val x5832_x5832 =  VectorFIFO(size=1).wtPort(x4953_x5832_v)
      val x5636 = CounterChain.copy("x5834", "x5636")
      val x5932 = CounterChain.copy("x5938", "x5932")
      val x4953_x5934 =  SRAM(size=384,banking = Strided(1)).wtPort(x5832_x5832.readPort).rdPort(x4953_x5934_x5938_v).rdAddr(x5932(0)).wtAddr(x5636(0))
      var stage: List[Stage] = Nil
    }
    val x4954_dsp0 = MemoryPipeline(name="x4954_dsp0",parent="x5949") { implicit CU => 
      val x4974_x4974 =  VectorFIFO(size=1).wtPort(x4959_x4968_data_v)
      val x4970 = CounterChain.copy("x4975", "x4970")
      val x5036 = CounterChain.copy("x5234", "x5036")
      val x4954_x5042 =  SRAM(size=384,banking = Strided(1)).wtPort(x4974_x4974.readPort).rdPort(x4954_x5042_x5044_v).rdAddr(x5036(0)).wtAddr(x4970(0))
      var stage: List[Stage] = Nil
    }
    val x4955_dsp0 = MemoryPipeline(name="x4955_dsp0",parent="x5949") { implicit CU => 
      val x4993_x4993 =  VectorFIFO(size=1).wtPort(x4978_x4987_data_v)
      val x4989 = CounterChain.copy("x4994", "x4989")
      val x5236 = CounterChain.copy("x5434", "x5236")
      val x4955_x5242 =  SRAM(size=384,banking = Strided(1)).wtPort(x4993_x4993.readPort).rdPort(x4955_x5242_x5244_v).rdAddr(x5236(0)).wtAddr(x4989(0))
      var stage: List[Stage] = Nil
    }
    val x4956_dsp0 = MemoryPipeline(name="x4956_dsp0",parent="x5949") { implicit CU => 
      val x5012_x5012 =  VectorFIFO(size=1).wtPort(x4997_x5006_data_v)
      val x5008 = CounterChain.copy("x5013", "x5008")
      val x5436 = CounterChain.copy("x5634", "x5436")
      val x4956_x5442 =  SRAM(size=384,banking = Strided(1)).wtPort(x5012_x5012.readPort).rdPort(x4956_x5442_x5444_v).rdAddr(x5436(0)).wtAddr(x5008(0))
      var stage: List[Stage] = Nil
    }
    val x4957_dsp0 = MemoryPipeline(name="x4957_dsp0",parent="x5949") { implicit CU => 
      val x5031_x5031 =  VectorFIFO(size=1).wtPort(x5016_x5025_data_v)
      val x5027 = CounterChain.copy("x5032", "x5027")
      val x5636 = CounterChain.copy("x5834", "x5636")
      val x4957_x5642 =  SRAM(size=384,banking = Strided(1)).wtPort(x5031_x5031.readPort).rdPort(x4957_x5642_x5644_v).rdAddr(x5636(0)).wtAddr(x5027(0))
      var stage: List[Stage] = Nil
    }
    val x4976 = StreamController(name="x4976",parent=x5949) { implicit CU => 
    }
    val x4967_0 = Pipeline(name="x4967_0",parent=x4976) { implicit CU => 
      val x4961 = CU.temp
      val x4960 =  ScalarBuffer().wtPort(x4960_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x4961))
      Stage(operands=List(x4961, CU.load(x4960)), op=FixAdd, results=List(CU.scalarOut(x4958_b5990_x4966_b5992_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x4958_b5991_x4966_b5993_s)))
    }
    val x4968 = MemoryController(name="x4968",parent=x4976,offchip=x4937_oc, mctpe=TileLoad) { implicit CU => 
      val x4958_b5991_x4968 =  ScalarFIFO(name="size",size=1).wtPort(x4958_b5991_x4966_b5993_s)
      val x4958_b5990_x4968 =  ScalarFIFO(name="offset",size=1).wtPort(x4958_b5990_x4966_b5992_s)
      CU.newVout("data", x4959_x4968_data_v)
    }
    val x4975 = Pipeline(name="x4975",parent=x4976) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x4970 = CounterChain(name = "x4970", ctr2).iter(24)
      var stage: List[Stage] = Nil
    }
    val x4995 = StreamController(name="x4995",parent=x5949) { implicit CU => 
    }
    val x4986_0 = Pipeline(name="x4986_0",parent=x4995) { implicit CU => 
      val x4980 = CU.temp
      val x4979 =  ScalarBuffer().wtPort(x4979_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x4980))
      Stage(operands=List(x4980, CU.load(x4979)), op=FixAdd, results=List(CU.scalarOut(x4977_b5994_x4985_b5996_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x4977_b5995_x4985_b5997_s)))
    }
    val x4987 = MemoryController(name="x4987",parent=x4995,offchip=x4937_oc, mctpe=TileLoad) { implicit CU => 
      val x4977_b5995_x4987 =  ScalarFIFO(name="size",size=1).wtPort(x4977_b5995_x4985_b5997_s)
      val x4977_b5994_x4987 =  ScalarFIFO(name="offset",size=1).wtPort(x4977_b5994_x4985_b5996_s)
      CU.newVout("data", x4978_x4987_data_v)
    }
    val x4994 = Pipeline(name="x4994",parent=x4995) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x4989 = CounterChain(name = "x4989", ctr3).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5014 = StreamController(name="x5014",parent=x5949) { implicit CU => 
    }
    val x5005_0 = Pipeline(name="x5005_0",parent=x5014) { implicit CU => 
      val x4999 = CU.temp
      val x4998 =  ScalarBuffer().wtPort(x4998_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x4999))
      Stage(operands=List(x4999, CU.load(x4998)), op=FixAdd, results=List(CU.scalarOut(x4996_b5998_x5004_b6000_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x4996_b5999_x5004_b6001_s)))
    }
    val x5006 = MemoryController(name="x5006",parent=x5014,offchip=x4937_oc, mctpe=TileLoad) { implicit CU => 
      val x4996_b5998_x5006 =  ScalarFIFO(name="offset",size=1).wtPort(x4996_b5998_x5004_b6000_s)
      val x4996_b5999_x5006 =  ScalarFIFO(name="size",size=1).wtPort(x4996_b5999_x5004_b6001_s)
      CU.newVout("data", x4997_x5006_data_v)
    }
    val x5013 = Pipeline(name="x5013",parent=x5014) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5008 = CounterChain(name = "x5008", ctr4).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5033 = StreamController(name="x5033",parent=x5949) { implicit CU => 
    }
    val x5024_0 = Pipeline(name="x5024_0",parent=x5033) { implicit CU => 
      val x5018 = CU.temp
      val x5017 =  ScalarBuffer().wtPort(x5017_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x5018))
      Stage(operands=List(x5018, CU.load(x5017)), op=FixAdd, results=List(CU.scalarOut(x5015_b6002_x5023_b6004_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x5015_b6003_x5023_b6005_s)))
    }
    val x5025 = MemoryController(name="x5025",parent=x5033,offchip=x4937_oc, mctpe=TileLoad) { implicit CU => 
      val x5015_b6003_x5025 =  ScalarFIFO(name="size",size=1).wtPort(x5015_b6003_x5023_b6005_s)
      val x5015_b6002_x5025 =  ScalarFIFO(name="offset",size=1).wtPort(x5015_b6002_x5023_b6004_s)
      CU.newVout("data", x5016_x5025_data_v)
    }
    val x5032 = Pipeline(name="x5032",parent=x5033) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5027 = CounterChain(name = "x5027", ctr5).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5234 = MetaPipeline(name="x5234",parent=x5949) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5036 = CounterChain(name = "x5036", ctr6).iter(384)
    }
    val x5037_dsp0 = MemoryPipeline(name="x5037_dsp0",parent="x5234") { implicit CU => 
      val x5115_x5115 =  VectorFIFO(size=1).wtPort(x5054_x5086_data_v)
      val x5087_x5103 =  ScalarBuffer().wtPort(x5087_x5094_s)
      val x5102 = CounterChain.copy("x5116", "x5102")
      val x5192 = CounterChain.copy("x5202_0", "x5192")
      val x5037_x5195 =  SRAM(size=384,banking = Strided(1)).wtPort(x5115_x5115.readPort).rdPort(x5037_x5195_x5202_v).rdAddr(x5192(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5102(0)), CU.load(x5087_x5103)), op=FixSub, results=List(x5037_x5195.writeAddr))
    }
    val x5038_dsp0 = MemoryPipeline(name="x5038_dsp0",parent="x5234") { implicit CU => 
      val x5184_x5184 =  VectorFIFO(size=1).wtPort(x5123_x5155_data_v)
      val x5156_x5172 =  ScalarBuffer().wtPort(x5156_x5163_s)
      val x5171 = CounterChain.copy("x5185", "x5171")
      val x5216 = CounterChain.copy("x5229_0", "x5216")
      val x5038_x5219 =  SRAM(size=384,banking = Strided(1)).wtPort(x5184_x5184.readPort).rdPort(x5038_x5219_x5229_v).rdAddr(x5216(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5171(0)), CU.load(x5156_x5172)), op=FixSub, results=List(x5038_x5219.writeAddr))
    }
    val x5039_dsp0 = MemoryPipeline(name="x5039_dsp0",parent="x5234") { implicit CU => 
      val x5210_x5210 =  VectorFIFO(size=1).wtPort(x5190_x5203_data_v)
      val x5205 = CounterChain.copy("x5211", "x5205")
      val x5216 = CounterChain.copy("x5229_0", "x5216")
      val x5039_x5220 =  SRAM(size=384,banking = Strided(1)).wtPort(x5210_x5210.readPort).rdPort(x5039_x5220_x5229_v).rdAddr(x5216(0)).wtAddr(x5205(0))
      var stage: List[Stage] = Nil
    }
    val x5044_0 = Pipeline(name="x5044_0",parent=x5234) { implicit CU => 
      val x5042_x5042 =  VectorFIFO(size=1).wtPort(x4954_x5042_x5044_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5042_x5042)), op=Bypass, results=List(CU.scalarOut(x5040_x5043_s)))
    }
    val x5049_0 = Pipeline(name="x5049_0",parent=x5234) { implicit CU => 
      val x4949 = CounterChain.copy("x5949", "x4949")
      val x5036 = CounterChain.copy("x5234", "x5036")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), CU.ctr(x5036(0))), op=FixAdd, results=List(CU.scalarOut(x5045_x5048_s)))
    }
    val x5118 = StreamController(name="x5118",parent=x5234) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5051 = CounterChain(name = "x5051", ctr7).iter(1)
    }
    val x5085 = StreamController(name="x5085",parent=x5118) { implicit CU => 
    }
    val x5085_0 = Pipeline(name="x5085_0",parent=x5085) { implicit CU => 
      val x5058 = CU.temp
      val x5059 = CU.temp
      val x5067 = CU.temp
      val x5060 = CU.temp
      val x5040_x5057 =  ScalarBuffer().wtPort(x5040_x5043_s)
      val x5045_x5056 =  ScalarBuffer().wtPort(x5045_x5048_s)
      val x5051 = CounterChain.copy("x5118", "x5051")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5045_x5056), CU.ctr(x5051(0))), op=FixAdd, results=List(x5058))
      Stage(operands=List(x5058, Const(60)), op=FixMul, results=List(x5059))
      Stage(operands=List(x5059, Const(4)), op=FixMul, results=List(x5060, CU.scalarOut(bus_1277_s)))
      Stage(operands=List(x5060, Const(64)), op=FixMod, results=List(x5067, CU.scalarOut(bus_1279_s)))
      Stage(operands=List(x5067, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1280_s), CU.scalarOut(x5053_b6009_x5084_b6017_s)))
      Stage(operands=List(CU.load(x5040_x5057), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1281_s)))
    }
    val x5085_1 = Pipeline(name="x5085_1",parent=x5085) { implicit CU => 
      val x5065 = CU.temp
      val x5063 = CU.temp
      val x5064 = CU.temp
      val x5062 = CU.temp
      val x5061 =  ScalarFIFO(size=1).wtPort(bus_1281_s)
      val x5060 =  ScalarFIFO(size=1).wtPort(bus_1277_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5060), CU.load(x5061)), op=FixAdd, results=List(x5062))
      Stage(operands=List(x5062, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5053_b6010_x5084_b6018_s)))
      Stage(operands=List(x5062, Const(64)), op=FixMod, results=List(x5063))
      Stage(operands=List(x5063, Const(0)), op=FixEql, results=List(x5064))
      Stage(operands=List(Const(64), x5063), op=FixSub, results=List(x5065))
      Stage(operands=List(x5064, Const(0), x5065), op=Mux, results=List(CU.scalarOut(bus_1288_s)))
    }
    val x5085_2 = Pipeline(name="x5085_2",parent=x5085) { implicit CU => 
      val x5068 = CU.temp
      val x5080 = CU.temp
      val x5081 = CU.temp
      val x5040_x5057 =  ScalarBuffer().wtPort(x5040_x5043_s)
      val x5061 =  ScalarFIFO(size=1).wtPort(bus_1281_s)
      val x5066 =  ScalarFIFO(size=1).wtPort(bus_1288_s)
      val x5078 =  ScalarFIFO(size=1).wtPort(bus_1280_s)
      val x5067 =  ScalarFIFO(size=1).wtPort(bus_1279_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5066), Const(4)), op=FixDiv, results=List(x5080))
      Stage(operands=List(CU.load(x5040_x5057), CU.load(x5078)), op=FixAdd, results=List(x5081))
      Stage(operands=List(x5081, x5080), op=FixAdd, results=List(CU.scalarOut(x5053_b6008_x5084_b6016_s)))
      Stage(operands=List(CU.load(x5061), CU.load(x5067)), op=FixAdd, results=List(x5068))
      Stage(operands=List(x5068, CU.load(x5066)), op=FixAdd, results=List(CU.scalarOut(x5052_b6007_x5077_b6015_s)))
    }
    val x5085_3 = Pipeline(name="x5085_3",parent=x5085) { implicit CU => 
      val x5070 = CU.temp
      val x5067 =  ScalarFIFO(size=1).wtPort(bus_1279_s)
      val x5055 =  ScalarBuffer().wtPort(x5055_argin)
      val x5060 =  ScalarFIFO(size=1).wtPort(bus_1277_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5060), CU.load(x5067)), op=FixSub, results=List(x5070))
      Stage(operands=List(x5070, CU.load(x5055)), op=FixAdd, results=List(CU.scalarOut(x5052_b6006_x5077_b6014_s)))
    }
    val x5086 = MemoryController(name="x5086",parent=x5118,offchip=x4935_oc, mctpe=TileLoad) { implicit CU => 
      val x5052_b6007_x5086 =  ScalarFIFO(name="size",size=1).wtPort(x5052_b6007_x5077_b6015_s)
      val x5052_b6006_x5086 =  ScalarFIFO(name="offset",size=1).wtPort(x5052_b6006_x5077_b6014_s)
      CU.newVout("data", x5054_x5086_data_v)
    }
    val x5117 = Sequential(name="x5117",parent=x5118) { implicit CU => 
    }
    val x5099_0 = Pipeline(name="x5099_0",parent=x5117) { implicit CU => 
      val x5053_b6010_x5092_b6013 =  ScalarFIFO(size=16).wtPort(x5053_b6010_x5084_b6018_s)
      val x5053_b6009_x5092_b6012 =  ScalarFIFO(size=16).wtPort(x5053_b6009_x5084_b6017_s)
      val x5053_b6008_x5092_b6011 =  ScalarFIFO(size=16).wtPort(x5053_b6008_x5084_b6016_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5053_b6009_x5092_b6012)), op=Bypass, results=List(CU.scalarOut(x5087_x5094_s)))
      Stage(operands=List(CU.load(x5053_b6010_x5092_b6013)), op=Bypass, results=List(CU.scalarOut(x5088_x5096_s)))
      Stage(operands=List(CU.load(x5053_b6008_x5092_b6011)), op=Bypass, results=List(CU.scalarOut(x5089_x5098_s)))
    }
    val x5116 = Pipeline(name="x5116",parent=x5117) { implicit CU => 
      val x5089_x5100 =  ScalarBuffer().wtPort(x5089_x5098_s)
      val x5088_x5104 =  ScalarBuffer().wtPort(x5088_x5096_s)
      val x5087_x5103 =  ScalarBuffer().wtPort(x5087_x5094_s)
      val ctr8 = Counter(min=Const(0), max=x5089_x5100.load, step=Const(1), par=16) // Counter
      val x5102 = CounterChain(name = "x5102", ctr8).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5187 = StreamController(name="x5187",parent=x5234) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5120 = CounterChain(name = "x5120", ctr9).iter(1)
    }
    val x5154 = StreamController(name="x5154",parent=x5187) { implicit CU => 
    }
    val x5154_0 = Pipeline(name="x5154_0",parent=x5154) { implicit CU => 
      val x5129 = CU.temp
      val x5128 = CU.temp
      val x5127 = CU.temp
      val x5136 = CU.temp
      val x5040_x5126 =  ScalarBuffer().wtPort(x5040_x5043_s)
      val x5045_x5125 =  ScalarBuffer().wtPort(x5045_x5048_s)
      val x5120 = CounterChain.copy("x5187", "x5120")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5045_x5125), CU.ctr(x5120(0))), op=FixAdd, results=List(x5127))
      Stage(operands=List(x5127, Const(60)), op=FixMul, results=List(x5128))
      Stage(operands=List(x5128, Const(4)), op=FixMul, results=List(x5129, CU.scalarOut(bus_1303_s)))
      Stage(operands=List(x5129, Const(64)), op=FixMod, results=List(x5136, CU.scalarOut(bus_1305_s)))
      Stage(operands=List(x5136, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1306_s), CU.scalarOut(x5122_b6022_x5153_b6030_s)))
      Stage(operands=List(CU.load(x5040_x5126), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1307_s)))
    }
    val x5154_1 = Pipeline(name="x5154_1",parent=x5154) { implicit CU => 
      val x5132 = CU.temp
      val x5133 = CU.temp
      val x5131 = CU.temp
      val x5134 = CU.temp
      val x5129 =  ScalarFIFO(size=1).wtPort(bus_1303_s)
      val x5130 =  ScalarFIFO(size=1).wtPort(bus_1307_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5129), CU.load(x5130)), op=FixAdd, results=List(x5131))
      Stage(operands=List(x5131, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5122_b6023_x5153_b6031_s)))
      Stage(operands=List(x5131, Const(64)), op=FixMod, results=List(x5132))
      Stage(operands=List(x5132, Const(0)), op=FixEql, results=List(x5133))
      Stage(operands=List(Const(64), x5132), op=FixSub, results=List(x5134))
      Stage(operands=List(x5133, Const(0), x5134), op=Mux, results=List(CU.scalarOut(bus_1314_s)))
    }
    val x5154_2 = Pipeline(name="x5154_2",parent=x5154) { implicit CU => 
      val x5149 = CU.temp
      val x5150 = CU.temp
      val x5137 = CU.temp
      val x5135 =  ScalarFIFO(size=1).wtPort(bus_1314_s)
      val x5040_x5126 =  ScalarBuffer().wtPort(x5040_x5043_s)
      val x5147 =  ScalarFIFO(size=1).wtPort(bus_1306_s)
      val x5136 =  ScalarFIFO(size=1).wtPort(bus_1305_s)
      val x5130 =  ScalarFIFO(size=1).wtPort(bus_1307_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5135), Const(4)), op=FixDiv, results=List(x5149))
      Stage(operands=List(CU.load(x5040_x5126), CU.load(x5147)), op=FixAdd, results=List(x5150))
      Stage(operands=List(x5150, x5149), op=FixAdd, results=List(CU.scalarOut(x5122_b6021_x5153_b6029_s)))
      Stage(operands=List(CU.load(x5130), CU.load(x5136)), op=FixAdd, results=List(x5137))
      Stage(operands=List(x5137, CU.load(x5135)), op=FixAdd, results=List(CU.scalarOut(x5121_b6020_x5146_b6028_s)))
    }
    val x5154_3 = Pipeline(name="x5154_3",parent=x5154) { implicit CU => 
      val x5139 = CU.temp
      val x5129 =  ScalarFIFO(size=1).wtPort(bus_1303_s)
      val x5136 =  ScalarFIFO(size=1).wtPort(bus_1305_s)
      val x5124 =  ScalarBuffer().wtPort(x5124_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5129), CU.load(x5136)), op=FixSub, results=List(x5139))
      Stage(operands=List(x5139, CU.load(x5124)), op=FixAdd, results=List(CU.scalarOut(x5121_b6019_x5146_b6027_s)))
    }
    val x5155 = MemoryController(name="x5155",parent=x5187,offchip=x4936_oc, mctpe=TileLoad) { implicit CU => 
      val x5121_b6019_x5155 =  ScalarFIFO(name="offset",size=1).wtPort(x5121_b6019_x5146_b6027_s)
      val x5121_b6020_x5155 =  ScalarFIFO(name="size",size=1).wtPort(x5121_b6020_x5146_b6028_s)
      CU.newVout("data", x5123_x5155_data_v)
    }
    val x5186 = Sequential(name="x5186",parent=x5187) { implicit CU => 
    }
    val x5168_0 = Pipeline(name="x5168_0",parent=x5186) { implicit CU => 
      val x5122_b6022_x5161_b6025 =  ScalarFIFO(size=16).wtPort(x5122_b6022_x5153_b6030_s)
      val x5122_b6021_x5161_b6024 =  ScalarFIFO(size=16).wtPort(x5122_b6021_x5153_b6029_s)
      val x5122_b6023_x5161_b6026 =  ScalarFIFO(size=16).wtPort(x5122_b6023_x5153_b6031_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5122_b6022_x5161_b6025)), op=Bypass, results=List(CU.scalarOut(x5156_x5163_s)))
      Stage(operands=List(CU.load(x5122_b6023_x5161_b6026)), op=Bypass, results=List(CU.scalarOut(x5157_x5165_s)))
      Stage(operands=List(CU.load(x5122_b6021_x5161_b6024)), op=Bypass, results=List(CU.scalarOut(x5158_x5167_s)))
    }
    val x5185 = Pipeline(name="x5185",parent=x5186) { implicit CU => 
      val x5158_x5169 =  ScalarBuffer().wtPort(x5158_x5167_s)
      val x5157_x5173 =  ScalarBuffer().wtPort(x5157_x5165_s)
      val x5156_x5172 =  ScalarBuffer().wtPort(x5156_x5163_s)
      val ctr10 = Counter(min=Const(0), max=x5158_x5169.load, step=Const(1), par=16) // Counter
      val x5171 = CounterChain(name = "x5171", ctr10).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5212 = StreamController(name="x5212",parent=x5234) { implicit CU => 
    }
    val x5202_0 = Pipeline(name="x5202_0",parent=x5212) { implicit CU => 
      val x5198 = CU.temp
      val x5196 =  ScalarBuffer().wtPort(x5196_argin)
      val x5195_x5195 =  VectorFIFO(size=1).wtPort(x5037_x5195_x5202_v)
      val ctr11 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5192 = CounterChain(name = "x5192", ctr11).iter(384)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5195_x5195), Const(4)), op=FixMul, results=List(x5198))
      Stage(operands=List(x5198, CU.load(x5196)), op=FixAdd, results=List(CU.scalarOut(x5189_x5201_s)))
    }
    val x5203 = MemoryController(name="x5203",parent=x5212,offchip=x4938_oc, mctpe=Gather) { implicit CU => 
      val x5189_x5203 =  ScalarFIFO(name="addr",size=1).wtPort(x5189_x5201_s)
      CU.newVout("data", x5190_x5203_data_v)
    }
    val x5211 = Pipeline(name="x5211",parent=x5212) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5205 = CounterChain(name = "x5205", ctr12).iter(384)
      var stage: List[Stage] = Nil
    }
    val x5229_0 = Pipeline(name="x5229_0",parent=x5234) { implicit CU => 
      val x5220_x5220 =  VectorFIFO(size=1).wtPort(x5039_x5220_x5229_v)
      val x5040_x5214 =  ScalarBuffer().wtPort(x5040_x5043_s)
      val x5219_x5219 =  VectorFIFO(size=1).wtPort(x5038_x5219_x5229_v)
      val ctr13 = Counter(min=Const(0), max=x5040_x5214.load, step=Const(1), par=16) // Counter
      val x5216 = CounterChain(name = "x5216", ctr13).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5219_x5219), CU.load(x5220_x5220)), op=FixMul, results=List(CU.reduce))
      val (_, rr1335) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1335), op=Bypass, results=List(CU.scalarOut(x5213_x5227_s)))
    }
    val x5233_0 = Pipeline(name="x5233_0",parent=x5234) { implicit CU => 
      val x5213_x5230 =  ScalarBuffer().wtPort(x5213_x5227_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5213_x5230)), op=Bypass, results=List(CU.vecOut(x4950_x5232_v)))
    }
    val x5434 = MetaPipeline(name="x5434",parent=x5949) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5236 = CounterChain(name = "x5236", ctr14).iter(384)
    }
    val x5237_dsp0 = MemoryPipeline(name="x5237_dsp0",parent="x5434") { implicit CU => 
      val x5287_x5303 =  ScalarBuffer().wtPort(x5287_x5294_s)
      val x5315_x5315 =  VectorFIFO(size=1).wtPort(x5254_x5286_data_v)
      val x5302 = CounterChain.copy("x5316", "x5302")
      val x5392 = CounterChain.copy("x5402_0", "x5392")
      val x5237_x5395 =  SRAM(size=384,banking = Strided(1)).wtPort(x5315_x5315.readPort).rdPort(x5237_x5395_x5402_v).rdAddr(x5392(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5302(0)), CU.load(x5287_x5303)), op=FixSub, results=List(x5237_x5395.writeAddr))
    }
    val x5238_dsp0 = MemoryPipeline(name="x5238_dsp0",parent="x5434") { implicit CU => 
      val x5384_x5384 =  VectorFIFO(size=1).wtPort(x5323_x5355_data_v)
      val x5356_x5372 =  ScalarBuffer().wtPort(x5356_x5363_s)
      val x5371 = CounterChain.copy("x5385", "x5371")
      val x5416 = CounterChain.copy("x5429_0", "x5416")
      val x5238_x5419 =  SRAM(size=384,banking = Strided(1)).wtPort(x5384_x5384.readPort).rdPort(x5238_x5419_x5429_v).rdAddr(x5416(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5371(0)), CU.load(x5356_x5372)), op=FixSub, results=List(x5238_x5419.writeAddr))
    }
    val x5239_dsp0 = MemoryPipeline(name="x5239_dsp0",parent="x5434") { implicit CU => 
      val x5410_x5410 =  VectorFIFO(size=1).wtPort(x5390_x5403_data_v)
      val x5405 = CounterChain.copy("x5411", "x5405")
      val x5416 = CounterChain.copy("x5429_0", "x5416")
      val x5239_x5420 =  SRAM(size=384,banking = Strided(1)).wtPort(x5410_x5410.readPort).rdPort(x5239_x5420_x5429_v).rdAddr(x5416(0)).wtAddr(x5405(0))
      var stage: List[Stage] = Nil
    }
    val x5244_0 = Pipeline(name="x5244_0",parent=x5434) { implicit CU => 
      val x5242_x5242 =  VectorFIFO(size=1).wtPort(x4955_x5242_x5244_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5242_x5242)), op=Bypass, results=List(CU.scalarOut(x5240_x5243_s)))
    }
    val x5249_0 = Pipeline(name="x5249_0",parent=x5434) { implicit CU => 
      val x4949 = CounterChain.copy("x5949", "x4949")
      val x5236 = CounterChain.copy("x5434", "x5236")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), CU.ctr(x5236(0))), op=FixAdd, results=List(CU.scalarOut(x5245_x5248_s)))
    }
    val x5318 = StreamController(name="x5318",parent=x5434) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5251 = CounterChain(name = "x5251", ctr15).iter(1)
    }
    val x5285 = StreamController(name="x5285",parent=x5318) { implicit CU => 
    }
    val x5285_0 = Pipeline(name="x5285_0",parent=x5285) { implicit CU => 
      val x5258 = CU.temp
      val x5267 = CU.temp
      val x5260 = CU.temp
      val x5259 = CU.temp
      val x5245_x5256 =  ScalarBuffer().wtPort(x5245_x5248_s)
      val x5240_x5257 =  ScalarBuffer().wtPort(x5240_x5243_s)
      val x5251 = CounterChain.copy("x5318", "x5251")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5245_x5256), CU.ctr(x5251(0))), op=FixAdd, results=List(x5258))
      Stage(operands=List(x5258, Const(60)), op=FixMul, results=List(x5259))
      Stage(operands=List(x5259, Const(4)), op=FixMul, results=List(x5260, CU.scalarOut(bus_1352_s)))
      Stage(operands=List(x5260, Const(64)), op=FixMod, results=List(x5267, CU.scalarOut(bus_1354_s)))
      Stage(operands=List(x5267, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1355_s), CU.scalarOut(x5253_b6035_x5284_b6043_s)))
      Stage(operands=List(CU.load(x5240_x5257), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1356_s)))
    }
    val x5285_1 = Pipeline(name="x5285_1",parent=x5285) { implicit CU => 
      val x5262 = CU.temp
      val x5265 = CU.temp
      val x5264 = CU.temp
      val x5263 = CU.temp
      val x5261 =  ScalarFIFO(size=1).wtPort(bus_1356_s)
      val x5260 =  ScalarFIFO(size=1).wtPort(bus_1352_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5260), CU.load(x5261)), op=FixAdd, results=List(x5262))
      Stage(operands=List(x5262, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5253_b6036_x5284_b6044_s)))
      Stage(operands=List(x5262, Const(64)), op=FixMod, results=List(x5263))
      Stage(operands=List(x5263, Const(0)), op=FixEql, results=List(x5264))
      Stage(operands=List(Const(64), x5263), op=FixSub, results=List(x5265))
      Stage(operands=List(x5264, Const(0), x5265), op=Mux, results=List(CU.scalarOut(bus_1363_s)))
    }
    val x5285_2 = Pipeline(name="x5285_2",parent=x5285) { implicit CU => 
      val x5280 = CU.temp
      val x5268 = CU.temp
      val x5281 = CU.temp
      val x5278 =  ScalarFIFO(size=1).wtPort(bus_1355_s)
      val x5261 =  ScalarFIFO(size=1).wtPort(bus_1356_s)
      val x5267 =  ScalarFIFO(size=1).wtPort(bus_1354_s)
      val x5240_x5257 =  ScalarBuffer().wtPort(x5240_x5243_s)
      val x5266 =  ScalarFIFO(size=1).wtPort(bus_1363_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5266), Const(4)), op=FixDiv, results=List(x5280))
      Stage(operands=List(CU.load(x5240_x5257), CU.load(x5278)), op=FixAdd, results=List(x5281))
      Stage(operands=List(x5281, x5280), op=FixAdd, results=List(CU.scalarOut(x5253_b6034_x5284_b6042_s)))
      Stage(operands=List(CU.load(x5261), CU.load(x5267)), op=FixAdd, results=List(x5268))
      Stage(operands=List(x5268, CU.load(x5266)), op=FixAdd, results=List(CU.scalarOut(x5252_b6033_x5277_b6041_s)))
    }
    val x5285_3 = Pipeline(name="x5285_3",parent=x5285) { implicit CU => 
      val x5270 = CU.temp
      val x5260 =  ScalarFIFO(size=1).wtPort(bus_1352_s)
      val x5267 =  ScalarFIFO(size=1).wtPort(bus_1354_s)
      val x5255 =  ScalarBuffer().wtPort(x5255_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5260), CU.load(x5267)), op=FixSub, results=List(x5270))
      Stage(operands=List(x5270, CU.load(x5255)), op=FixAdd, results=List(CU.scalarOut(x5252_b6032_x5277_b6040_s)))
    }
    val x5286 = MemoryController(name="x5286",parent=x5318,offchip=x4935_oc, mctpe=TileLoad) { implicit CU => 
      val x5252_b6033_x5286 =  ScalarFIFO(name="size",size=1).wtPort(x5252_b6033_x5277_b6041_s)
      val x5252_b6032_x5286 =  ScalarFIFO(name="offset",size=1).wtPort(x5252_b6032_x5277_b6040_s)
      CU.newVout("data", x5254_x5286_data_v)
    }
    val x5317 = Sequential(name="x5317",parent=x5318) { implicit CU => 
    }
    val x5299_0 = Pipeline(name="x5299_0",parent=x5317) { implicit CU => 
      val x5253_b6034_x5292_b6037 =  ScalarFIFO(size=16).wtPort(x5253_b6034_x5284_b6042_s)
      val x5253_b6036_x5292_b6039 =  ScalarFIFO(size=16).wtPort(x5253_b6036_x5284_b6044_s)
      val x5253_b6035_x5292_b6038 =  ScalarFIFO(size=16).wtPort(x5253_b6035_x5284_b6043_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5253_b6035_x5292_b6038)), op=Bypass, results=List(CU.scalarOut(x5287_x5294_s)))
      Stage(operands=List(CU.load(x5253_b6036_x5292_b6039)), op=Bypass, results=List(CU.scalarOut(x5288_x5296_s)))
      Stage(operands=List(CU.load(x5253_b6034_x5292_b6037)), op=Bypass, results=List(CU.scalarOut(x5289_x5298_s)))
    }
    val x5316 = Pipeline(name="x5316",parent=x5317) { implicit CU => 
      val x5287_x5303 =  ScalarBuffer().wtPort(x5287_x5294_s)
      val x5289_x5300 =  ScalarBuffer().wtPort(x5289_x5298_s)
      val x5288_x5304 =  ScalarBuffer().wtPort(x5288_x5296_s)
      val ctr16 = Counter(min=Const(0), max=x5289_x5300.load, step=Const(1), par=16) // Counter
      val x5302 = CounterChain(name = "x5302", ctr16).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5387 = StreamController(name="x5387",parent=x5434) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5320 = CounterChain(name = "x5320", ctr17).iter(1)
    }
    val x5354 = StreamController(name="x5354",parent=x5387) { implicit CU => 
    }
    val x5354_0 = Pipeline(name="x5354_0",parent=x5354) { implicit CU => 
      val x5329 = CU.temp
      val x5327 = CU.temp
      val x5328 = CU.temp
      val x5336 = CU.temp
      val x5245_x5325 =  ScalarBuffer().wtPort(x5245_x5248_s)
      val x5240_x5326 =  ScalarBuffer().wtPort(x5240_x5243_s)
      val x5320 = CounterChain.copy("x5387", "x5320")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5245_x5325), CU.ctr(x5320(0))), op=FixAdd, results=List(x5327))
      Stage(operands=List(x5327, Const(60)), op=FixMul, results=List(x5328))
      Stage(operands=List(x5328, Const(4)), op=FixMul, results=List(x5329, CU.scalarOut(bus_1378_s)))
      Stage(operands=List(x5329, Const(64)), op=FixMod, results=List(x5336, CU.scalarOut(bus_1380_s)))
      Stage(operands=List(x5336, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1381_s), CU.scalarOut(x5322_b6048_x5353_b6056_s)))
      Stage(operands=List(CU.load(x5240_x5326), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1382_s)))
    }
    val x5354_1 = Pipeline(name="x5354_1",parent=x5354) { implicit CU => 
      val x5333 = CU.temp
      val x5331 = CU.temp
      val x5334 = CU.temp
      val x5332 = CU.temp
      val x5330 =  ScalarFIFO(size=1).wtPort(bus_1382_s)
      val x5329 =  ScalarFIFO(size=1).wtPort(bus_1378_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5329), CU.load(x5330)), op=FixAdd, results=List(x5331))
      Stage(operands=List(x5331, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5322_b6049_x5353_b6057_s)))
      Stage(operands=List(x5331, Const(64)), op=FixMod, results=List(x5332))
      Stage(operands=List(x5332, Const(0)), op=FixEql, results=List(x5333))
      Stage(operands=List(Const(64), x5332), op=FixSub, results=List(x5334))
      Stage(operands=List(x5333, Const(0), x5334), op=Mux, results=List(CU.scalarOut(bus_1389_s)))
    }
    val x5354_2 = Pipeline(name="x5354_2",parent=x5354) { implicit CU => 
      val x5349 = CU.temp
      val x5350 = CU.temp
      val x5337 = CU.temp
      val x5240_x5326 =  ScalarBuffer().wtPort(x5240_x5243_s)
      val x5347 =  ScalarFIFO(size=1).wtPort(bus_1381_s)
      val x5336 =  ScalarFIFO(size=1).wtPort(bus_1380_s)
      val x5330 =  ScalarFIFO(size=1).wtPort(bus_1382_s)
      val x5335 =  ScalarFIFO(size=1).wtPort(bus_1389_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5335), Const(4)), op=FixDiv, results=List(x5349))
      Stage(operands=List(CU.load(x5240_x5326), CU.load(x5347)), op=FixAdd, results=List(x5350))
      Stage(operands=List(x5350, x5349), op=FixAdd, results=List(CU.scalarOut(x5322_b6047_x5353_b6055_s)))
      Stage(operands=List(CU.load(x5330), CU.load(x5336)), op=FixAdd, results=List(x5337))
      Stage(operands=List(x5337, CU.load(x5335)), op=FixAdd, results=List(CU.scalarOut(x5321_b6046_x5346_b6054_s)))
    }
    val x5354_3 = Pipeline(name="x5354_3",parent=x5354) { implicit CU => 
      val x5339 = CU.temp
      val x5329 =  ScalarFIFO(size=1).wtPort(bus_1378_s)
      val x5324 =  ScalarBuffer().wtPort(x5324_argin)
      val x5336 =  ScalarFIFO(size=1).wtPort(bus_1380_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5329), CU.load(x5336)), op=FixSub, results=List(x5339))
      Stage(operands=List(x5339, CU.load(x5324)), op=FixAdd, results=List(CU.scalarOut(x5321_b6045_x5346_b6053_s)))
    }
    val x5355 = MemoryController(name="x5355",parent=x5387,offchip=x4936_oc, mctpe=TileLoad) { implicit CU => 
      val x5321_b6046_x5355 =  ScalarFIFO(name="size",size=1).wtPort(x5321_b6046_x5346_b6054_s)
      val x5321_b6045_x5355 =  ScalarFIFO(name="offset",size=1).wtPort(x5321_b6045_x5346_b6053_s)
      CU.newVout("data", x5323_x5355_data_v)
    }
    val x5386 = Sequential(name="x5386",parent=x5387) { implicit CU => 
    }
    val x5368_0 = Pipeline(name="x5368_0",parent=x5386) { implicit CU => 
      val x5322_b6049_x5361_b6052 =  ScalarFIFO(size=16).wtPort(x5322_b6049_x5353_b6057_s)
      val x5322_b6048_x5361_b6051 =  ScalarFIFO(size=16).wtPort(x5322_b6048_x5353_b6056_s)
      val x5322_b6047_x5361_b6050 =  ScalarFIFO(size=16).wtPort(x5322_b6047_x5353_b6055_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5322_b6048_x5361_b6051)), op=Bypass, results=List(CU.scalarOut(x5356_x5363_s)))
      Stage(operands=List(CU.load(x5322_b6049_x5361_b6052)), op=Bypass, results=List(CU.scalarOut(x5357_x5365_s)))
      Stage(operands=List(CU.load(x5322_b6047_x5361_b6050)), op=Bypass, results=List(CU.scalarOut(x5358_x5367_s)))
    }
    val x5385 = Pipeline(name="x5385",parent=x5386) { implicit CU => 
      val x5358_x5369 =  ScalarBuffer().wtPort(x5358_x5367_s)
      val x5357_x5373 =  ScalarBuffer().wtPort(x5357_x5365_s)
      val x5356_x5372 =  ScalarBuffer().wtPort(x5356_x5363_s)
      val ctr18 = Counter(min=Const(0), max=x5358_x5369.load, step=Const(1), par=16) // Counter
      val x5371 = CounterChain(name = "x5371", ctr18).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5412 = StreamController(name="x5412",parent=x5434) { implicit CU => 
    }
    val x5402_0 = Pipeline(name="x5402_0",parent=x5412) { implicit CU => 
      val x5398 = CU.temp
      val x5396 =  ScalarBuffer().wtPort(x5396_argin)
      val x5395_x5395 =  VectorFIFO(size=1).wtPort(x5237_x5395_x5402_v)
      val ctr19 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5392 = CounterChain(name = "x5392", ctr19).iter(384)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5395_x5395), Const(4)), op=FixMul, results=List(x5398))
      Stage(operands=List(x5398, CU.load(x5396)), op=FixAdd, results=List(CU.scalarOut(x5389_x5401_s)))
    }
    val x5403 = MemoryController(name="x5403",parent=x5412,offchip=x4938_oc, mctpe=Gather) { implicit CU => 
      val x5389_x5403 =  ScalarFIFO(name="addr",size=1).wtPort(x5389_x5401_s)
      CU.newVout("data", x5390_x5403_data_v)
    }
    val x5411 = Pipeline(name="x5411",parent=x5412) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5405 = CounterChain(name = "x5405", ctr20).iter(384)
      var stage: List[Stage] = Nil
    }
    val x5429_0 = Pipeline(name="x5429_0",parent=x5434) { implicit CU => 
      val x5420_x5420 =  VectorFIFO(size=1).wtPort(x5239_x5420_x5429_v)
      val x5419_x5419 =  VectorFIFO(size=1).wtPort(x5238_x5419_x5429_v)
      val x5240_x5414 =  ScalarBuffer().wtPort(x5240_x5243_s)
      val ctr21 = Counter(min=Const(0), max=x5240_x5414.load, step=Const(1), par=16) // Counter
      val x5416 = CounterChain(name = "x5416", ctr21).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5419_x5419), CU.load(x5420_x5420)), op=FixMul, results=List(CU.reduce))
      val (_, rr1410) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1410), op=Bypass, results=List(CU.scalarOut(x5413_x5427_s)))
    }
    val x5433_0 = Pipeline(name="x5433_0",parent=x5434) { implicit CU => 
      val x5413_x5430 =  ScalarBuffer().wtPort(x5413_x5427_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5413_x5430)), op=Bypass, results=List(CU.vecOut(x4951_x5432_v)))
    }
    val x5634 = MetaPipeline(name="x5634",parent=x5949) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5436 = CounterChain(name = "x5436", ctr22).iter(384)
    }
    val x5437_dsp0 = MemoryPipeline(name="x5437_dsp0",parent="x5634") { implicit CU => 
      val x5487_x5503 =  ScalarBuffer().wtPort(x5487_x5494_s)
      val x5515_x5515 =  VectorFIFO(size=1).wtPort(x5454_x5486_data_v)
      val x5502 = CounterChain.copy("x5516", "x5502")
      val x5592 = CounterChain.copy("x5602_0", "x5592")
      val x5437_x5595 =  SRAM(size=384,banking = Strided(1)).wtPort(x5515_x5515.readPort).rdPort(x5437_x5595_x5602_v).rdAddr(x5592(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5502(0)), CU.load(x5487_x5503)), op=FixSub, results=List(x5437_x5595.writeAddr))
    }
    val x5438_dsp0 = MemoryPipeline(name="x5438_dsp0",parent="x5634") { implicit CU => 
      val x5556_x5572 =  ScalarBuffer().wtPort(x5556_x5563_s)
      val x5584_x5584 =  VectorFIFO(size=1).wtPort(x5523_x5555_data_v)
      val x5571 = CounterChain.copy("x5585", "x5571")
      val x5616 = CounterChain.copy("x5629_0", "x5616")
      val x5438_x5619 =  SRAM(size=384,banking = Strided(1)).wtPort(x5584_x5584.readPort).rdPort(x5438_x5619_x5629_v).rdAddr(x5616(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5571(0)), CU.load(x5556_x5572)), op=FixSub, results=List(x5438_x5619.writeAddr))
    }
    val x5439_dsp0 = MemoryPipeline(name="x5439_dsp0",parent="x5634") { implicit CU => 
      val x5610_x5610 =  VectorFIFO(size=1).wtPort(x5590_x5603_data_v)
      val x5605 = CounterChain.copy("x5611", "x5605")
      val x5616 = CounterChain.copy("x5629_0", "x5616")
      val x5439_x5620 =  SRAM(size=384,banking = Strided(1)).wtPort(x5610_x5610.readPort).rdPort(x5439_x5620_x5629_v).rdAddr(x5616(0)).wtAddr(x5605(0))
      var stage: List[Stage] = Nil
    }
    val x5444_0 = Pipeline(name="x5444_0",parent=x5634) { implicit CU => 
      val x5442_x5442 =  VectorFIFO(size=1).wtPort(x4956_x5442_x5444_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5442_x5442)), op=Bypass, results=List(CU.scalarOut(x5440_x5443_s)))
    }
    val x5449_0 = Pipeline(name="x5449_0",parent=x5634) { implicit CU => 
      val x4949 = CounterChain.copy("x5949", "x4949")
      val x5436 = CounterChain.copy("x5634", "x5436")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), CU.ctr(x5436(0))), op=FixAdd, results=List(CU.scalarOut(x5445_x5448_s)))
    }
    val x5518 = StreamController(name="x5518",parent=x5634) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5451 = CounterChain(name = "x5451", ctr23).iter(1)
    }
    val x5485 = StreamController(name="x5485",parent=x5518) { implicit CU => 
    }
    val x5485_0 = Pipeline(name="x5485_0",parent=x5485) { implicit CU => 
      val x5467 = CU.temp
      val x5458 = CU.temp
      val x5459 = CU.temp
      val x5460 = CU.temp
      val x5445_x5456 =  ScalarBuffer().wtPort(x5445_x5448_s)
      val x5440_x5457 =  ScalarBuffer().wtPort(x5440_x5443_s)
      val x5451 = CounterChain.copy("x5518", "x5451")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5445_x5456), CU.ctr(x5451(0))), op=FixAdd, results=List(x5458))
      Stage(operands=List(x5458, Const(60)), op=FixMul, results=List(x5459))
      Stage(operands=List(x5459, Const(4)), op=FixMul, results=List(x5460, CU.scalarOut(bus_1427_s)))
      Stage(operands=List(x5460, Const(64)), op=FixMod, results=List(x5467, CU.scalarOut(bus_1429_s)))
      Stage(operands=List(x5467, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1430_s), CU.scalarOut(x5453_b6061_x5484_b6069_s)))
      Stage(operands=List(CU.load(x5440_x5457), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1431_s)))
    }
    val x5485_1 = Pipeline(name="x5485_1",parent=x5485) { implicit CU => 
      val x5464 = CU.temp
      val x5462 = CU.temp
      val x5465 = CU.temp
      val x5463 = CU.temp
      val x5460 =  ScalarFIFO(size=1).wtPort(bus_1427_s)
      val x5461 =  ScalarFIFO(size=1).wtPort(bus_1431_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5460), CU.load(x5461)), op=FixAdd, results=List(x5462))
      Stage(operands=List(x5462, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5453_b6062_x5484_b6070_s)))
      Stage(operands=List(x5462, Const(64)), op=FixMod, results=List(x5463))
      Stage(operands=List(x5463, Const(0)), op=FixEql, results=List(x5464))
      Stage(operands=List(Const(64), x5463), op=FixSub, results=List(x5465))
      Stage(operands=List(x5464, Const(0), x5465), op=Mux, results=List(CU.scalarOut(bus_1438_s)))
    }
    val x5485_2 = Pipeline(name="x5485_2",parent=x5485) { implicit CU => 
      val x5468 = CU.temp
      val x5481 = CU.temp
      val x5480 = CU.temp
      val x5466 =  ScalarFIFO(size=1).wtPort(bus_1438_s)
      val x5467 =  ScalarFIFO(size=1).wtPort(bus_1429_s)
      val x5461 =  ScalarFIFO(size=1).wtPort(bus_1431_s)
      val x5478 =  ScalarFIFO(size=1).wtPort(bus_1430_s)
      val x5440_x5457 =  ScalarBuffer().wtPort(x5440_x5443_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5466), Const(4)), op=FixDiv, results=List(x5480))
      Stage(operands=List(CU.load(x5440_x5457), CU.load(x5478)), op=FixAdd, results=List(x5481))
      Stage(operands=List(x5481, x5480), op=FixAdd, results=List(CU.scalarOut(x5453_b6060_x5484_b6068_s)))
      Stage(operands=List(CU.load(x5461), CU.load(x5467)), op=FixAdd, results=List(x5468))
      Stage(operands=List(x5468, CU.load(x5466)), op=FixAdd, results=List(CU.scalarOut(x5452_b6059_x5477_b6067_s)))
    }
    val x5485_3 = Pipeline(name="x5485_3",parent=x5485) { implicit CU => 
      val x5470 = CU.temp
      val x5467 =  ScalarFIFO(size=1).wtPort(bus_1429_s)
      val x5460 =  ScalarFIFO(size=1).wtPort(bus_1427_s)
      val x5455 =  ScalarBuffer().wtPort(x5455_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5460), CU.load(x5467)), op=FixSub, results=List(x5470))
      Stage(operands=List(x5470, CU.load(x5455)), op=FixAdd, results=List(CU.scalarOut(x5452_b6058_x5477_b6066_s)))
    }
    val x5486 = MemoryController(name="x5486",parent=x5518,offchip=x4935_oc, mctpe=TileLoad) { implicit CU => 
      val x5452_b6058_x5486 =  ScalarFIFO(name="offset",size=1).wtPort(x5452_b6058_x5477_b6066_s)
      val x5452_b6059_x5486 =  ScalarFIFO(name="size",size=1).wtPort(x5452_b6059_x5477_b6067_s)
      CU.newVout("data", x5454_x5486_data_v)
    }
    val x5517 = Sequential(name="x5517",parent=x5518) { implicit CU => 
    }
    val x5499_0 = Pipeline(name="x5499_0",parent=x5517) { implicit CU => 
      val x5453_b6061_x5492_b6064 =  ScalarFIFO(size=16).wtPort(x5453_b6061_x5484_b6069_s)
      val x5453_b6060_x5492_b6063 =  ScalarFIFO(size=16).wtPort(x5453_b6060_x5484_b6068_s)
      val x5453_b6062_x5492_b6065 =  ScalarFIFO(size=16).wtPort(x5453_b6062_x5484_b6070_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5453_b6061_x5492_b6064)), op=Bypass, results=List(CU.scalarOut(x5487_x5494_s)))
      Stage(operands=List(CU.load(x5453_b6062_x5492_b6065)), op=Bypass, results=List(CU.scalarOut(x5488_x5496_s)))
      Stage(operands=List(CU.load(x5453_b6060_x5492_b6063)), op=Bypass, results=List(CU.scalarOut(x5489_x5498_s)))
    }
    val x5516 = Pipeline(name="x5516",parent=x5517) { implicit CU => 
      val x5487_x5503 =  ScalarBuffer().wtPort(x5487_x5494_s)
      val x5489_x5500 =  ScalarBuffer().wtPort(x5489_x5498_s)
      val x5488_x5504 =  ScalarBuffer().wtPort(x5488_x5496_s)
      val ctr24 = Counter(min=Const(0), max=x5489_x5500.load, step=Const(1), par=16) // Counter
      val x5502 = CounterChain(name = "x5502", ctr24).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5587 = StreamController(name="x5587",parent=x5634) { implicit CU => 
      val ctr25 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5520 = CounterChain(name = "x5520", ctr25).iter(1)
    }
    val x5554 = StreamController(name="x5554",parent=x5587) { implicit CU => 
    }
    val x5554_0 = Pipeline(name="x5554_0",parent=x5554) { implicit CU => 
      val x5536 = CU.temp
      val x5528 = CU.temp
      val x5527 = CU.temp
      val x5529 = CU.temp
      val x5445_x5525 =  ScalarBuffer().wtPort(x5445_x5448_s)
      val x5440_x5526 =  ScalarBuffer().wtPort(x5440_x5443_s)
      val x5520 = CounterChain.copy("x5587", "x5520")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5445_x5525), CU.ctr(x5520(0))), op=FixAdd, results=List(x5527))
      Stage(operands=List(x5527, Const(60)), op=FixMul, results=List(x5528))
      Stage(operands=List(x5528, Const(4)), op=FixMul, results=List(x5529, CU.scalarOut(bus_1453_s)))
      Stage(operands=List(x5529, Const(64)), op=FixMod, results=List(x5536, CU.scalarOut(bus_1455_s)))
      Stage(operands=List(x5536, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1456_s), CU.scalarOut(x5522_b6074_x5553_b6082_s)))
      Stage(operands=List(CU.load(x5440_x5526), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1457_s)))
    }
    val x5554_1 = Pipeline(name="x5554_1",parent=x5554) { implicit CU => 
      val x5534 = CU.temp
      val x5533 = CU.temp
      val x5532 = CU.temp
      val x5531 = CU.temp
      val x5530 =  ScalarFIFO(size=1).wtPort(bus_1457_s)
      val x5529 =  ScalarFIFO(size=1).wtPort(bus_1453_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5529), CU.load(x5530)), op=FixAdd, results=List(x5531))
      Stage(operands=List(x5531, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5522_b6075_x5553_b6083_s)))
      Stage(operands=List(x5531, Const(64)), op=FixMod, results=List(x5532))
      Stage(operands=List(x5532, Const(0)), op=FixEql, results=List(x5533))
      Stage(operands=List(Const(64), x5532), op=FixSub, results=List(x5534))
      Stage(operands=List(x5533, Const(0), x5534), op=Mux, results=List(CU.scalarOut(bus_1464_s)))
    }
    val x5554_2 = Pipeline(name="x5554_2",parent=x5554) { implicit CU => 
      val x5537 = CU.temp
      val x5549 = CU.temp
      val x5550 = CU.temp
      val x5547 =  ScalarFIFO(size=1).wtPort(bus_1456_s)
      val x5536 =  ScalarFIFO(size=1).wtPort(bus_1455_s)
      val x5440_x5526 =  ScalarBuffer().wtPort(x5440_x5443_s)
      val x5535 =  ScalarFIFO(size=1).wtPort(bus_1464_s)
      val x5530 =  ScalarFIFO(size=1).wtPort(bus_1457_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5535), Const(4)), op=FixDiv, results=List(x5549))
      Stage(operands=List(CU.load(x5440_x5526), CU.load(x5547)), op=FixAdd, results=List(x5550))
      Stage(operands=List(x5550, x5549), op=FixAdd, results=List(CU.scalarOut(x5522_b6073_x5553_b6081_s)))
      Stage(operands=List(CU.load(x5530), CU.load(x5536)), op=FixAdd, results=List(x5537))
      Stage(operands=List(x5537, CU.load(x5535)), op=FixAdd, results=List(CU.scalarOut(x5521_b6072_x5546_b6080_s)))
    }
    val x5554_3 = Pipeline(name="x5554_3",parent=x5554) { implicit CU => 
      val x5539 = CU.temp
      val x5536 =  ScalarFIFO(size=1).wtPort(bus_1455_s)
      val x5524 =  ScalarBuffer().wtPort(x5524_argin)
      val x5529 =  ScalarFIFO(size=1).wtPort(bus_1453_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5529), CU.load(x5536)), op=FixSub, results=List(x5539))
      Stage(operands=List(x5539, CU.load(x5524)), op=FixAdd, results=List(CU.scalarOut(x5521_b6071_x5546_b6079_s)))
    }
    val x5555 = MemoryController(name="x5555",parent=x5587,offchip=x4936_oc, mctpe=TileLoad) { implicit CU => 
      val x5521_b6072_x5555 =  ScalarFIFO(name="size",size=1).wtPort(x5521_b6072_x5546_b6080_s)
      val x5521_b6071_x5555 =  ScalarFIFO(name="offset",size=1).wtPort(x5521_b6071_x5546_b6079_s)
      CU.newVout("data", x5523_x5555_data_v)
    }
    val x5586 = Sequential(name="x5586",parent=x5587) { implicit CU => 
    }
    val x5568_0 = Pipeline(name="x5568_0",parent=x5586) { implicit CU => 
      val x5522_b6073_x5561_b6076 =  ScalarFIFO(size=16).wtPort(x5522_b6073_x5553_b6081_s)
      val x5522_b6075_x5561_b6078 =  ScalarFIFO(size=16).wtPort(x5522_b6075_x5553_b6083_s)
      val x5522_b6074_x5561_b6077 =  ScalarFIFO(size=16).wtPort(x5522_b6074_x5553_b6082_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5522_b6074_x5561_b6077)), op=Bypass, results=List(CU.scalarOut(x5556_x5563_s)))
      Stage(operands=List(CU.load(x5522_b6075_x5561_b6078)), op=Bypass, results=List(CU.scalarOut(x5557_x5565_s)))
      Stage(operands=List(CU.load(x5522_b6073_x5561_b6076)), op=Bypass, results=List(CU.scalarOut(x5558_x5567_s)))
    }
    val x5585 = Pipeline(name="x5585",parent=x5586) { implicit CU => 
      val x5556_x5572 =  ScalarBuffer().wtPort(x5556_x5563_s)
      val x5558_x5569 =  ScalarBuffer().wtPort(x5558_x5567_s)
      val x5557_x5573 =  ScalarBuffer().wtPort(x5557_x5565_s)
      val ctr26 = Counter(min=Const(0), max=x5558_x5569.load, step=Const(1), par=16) // Counter
      val x5571 = CounterChain(name = "x5571", ctr26).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5612 = StreamController(name="x5612",parent=x5634) { implicit CU => 
    }
    val x5602_0 = Pipeline(name="x5602_0",parent=x5612) { implicit CU => 
      val x5598 = CU.temp
      val x5595_x5595 =  VectorFIFO(size=1).wtPort(x5437_x5595_x5602_v)
      val x5596 =  ScalarBuffer().wtPort(x5596_argin)
      val ctr27 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5592 = CounterChain(name = "x5592", ctr27).iter(384)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5595_x5595), Const(4)), op=FixMul, results=List(x5598))
      Stage(operands=List(x5598, CU.load(x5596)), op=FixAdd, results=List(CU.scalarOut(x5589_x5601_s)))
    }
    val x5603 = MemoryController(name="x5603",parent=x5612,offchip=x4938_oc, mctpe=Gather) { implicit CU => 
      val x5589_x5603 =  ScalarFIFO(name="addr",size=1).wtPort(x5589_x5601_s)
      CU.newVout("data", x5590_x5603_data_v)
    }
    val x5611 = Pipeline(name="x5611",parent=x5612) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5605 = CounterChain(name = "x5605", ctr28).iter(384)
      var stage: List[Stage] = Nil
    }
    val x5629_0 = Pipeline(name="x5629_0",parent=x5634) { implicit CU => 
      val x5619_x5619 =  VectorFIFO(size=1).wtPort(x5438_x5619_x5629_v)
      val x5620_x5620 =  VectorFIFO(size=1).wtPort(x5439_x5620_x5629_v)
      val x5440_x5614 =  ScalarBuffer().wtPort(x5440_x5443_s)
      val ctr29 = Counter(min=Const(0), max=x5440_x5614.load, step=Const(1), par=16) // Counter
      val x5616 = CounterChain(name = "x5616", ctr29).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5619_x5619), CU.load(x5620_x5620)), op=FixMul, results=List(CU.reduce))
      val (_, rr1485) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1485), op=Bypass, results=List(CU.scalarOut(x5613_x5627_s)))
    }
    val x5633_0 = Pipeline(name="x5633_0",parent=x5634) { implicit CU => 
      val x5613_x5630 =  ScalarBuffer().wtPort(x5613_x5627_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5613_x5630)), op=Bypass, results=List(CU.vecOut(x4952_x5632_v)))
    }
    val x5834 = MetaPipeline(name="x5834",parent=x5949) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5636 = CounterChain(name = "x5636", ctr30).iter(384)
    }
    val x5637_dsp0 = MemoryPipeline(name="x5637_dsp0",parent="x5834") { implicit CU => 
      val x5687_x5703 =  ScalarBuffer().wtPort(x5687_x5694_s)
      val x5715_x5715 =  VectorFIFO(size=1).wtPort(x5654_x5686_data_v)
      val x5702 = CounterChain.copy("x5716", "x5702")
      val x5792 = CounterChain.copy("x5802_0", "x5792")
      val x5637_x5795 =  SRAM(size=384,banking = Strided(1)).wtPort(x5715_x5715.readPort).rdPort(x5637_x5795_x5802_v).rdAddr(x5792(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5702(0)), CU.load(x5687_x5703)), op=FixSub, results=List(x5637_x5795.writeAddr))
    }
    val x5638_dsp0 = MemoryPipeline(name="x5638_dsp0",parent="x5834") { implicit CU => 
      val x5756_x5772 =  ScalarBuffer().wtPort(x5756_x5763_s)
      val x5784_x5784 =  VectorFIFO(size=1).wtPort(x5723_x5755_data_v)
      val x5771 = CounterChain.copy("x5785", "x5771")
      val x5816 = CounterChain.copy("x5829_0", "x5816")
      val x5638_x5819 =  SRAM(size=384,banking = Strided(1)).wtPort(x5784_x5784.readPort).rdPort(x5638_x5819_x5829_v).rdAddr(x5816(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5771(0)), CU.load(x5756_x5772)), op=FixSub, results=List(x5638_x5819.writeAddr))
    }
    val x5639_dsp0 = MemoryPipeline(name="x5639_dsp0",parent="x5834") { implicit CU => 
      val x5810_x5810 =  VectorFIFO(size=1).wtPort(x5790_x5803_data_v)
      val x5805 = CounterChain.copy("x5811", "x5805")
      val x5816 = CounterChain.copy("x5829_0", "x5816")
      val x5639_x5820 =  SRAM(size=384,banking = Strided(1)).wtPort(x5810_x5810.readPort).rdPort(x5639_x5820_x5829_v).rdAddr(x5816(0)).wtAddr(x5805(0))
      var stage: List[Stage] = Nil
    }
    val x5644_0 = Pipeline(name="x5644_0",parent=x5834) { implicit CU => 
      val x5642_x5642 =  VectorFIFO(size=1).wtPort(x4957_x5642_x5644_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5642_x5642)), op=Bypass, results=List(CU.scalarOut(x5640_x5643_s)))
    }
    val x5649_0 = Pipeline(name="x5649_0",parent=x5834) { implicit CU => 
      val x4949 = CounterChain.copy("x5949", "x4949")
      val x5636 = CounterChain.copy("x5834", "x5636")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), CU.ctr(x5636(0))), op=FixAdd, results=List(CU.scalarOut(x5645_x5648_s)))
    }
    val x5718 = StreamController(name="x5718",parent=x5834) { implicit CU => 
      val ctr31 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5651 = CounterChain(name = "x5651", ctr31).iter(1)
    }
    val x5685 = StreamController(name="x5685",parent=x5718) { implicit CU => 
    }
    val x5685_0 = Pipeline(name="x5685_0",parent=x5685) { implicit CU => 
      val x5660 = CU.temp
      val x5658 = CU.temp
      val x5659 = CU.temp
      val x5667 = CU.temp
      val x5645_x5656 =  ScalarBuffer().wtPort(x5645_x5648_s)
      val x5640_x5657 =  ScalarBuffer().wtPort(x5640_x5643_s)
      val x5651 = CounterChain.copy("x5718", "x5651")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5645_x5656), CU.ctr(x5651(0))), op=FixAdd, results=List(x5658))
      Stage(operands=List(x5658, Const(60)), op=FixMul, results=List(x5659))
      Stage(operands=List(x5659, Const(4)), op=FixMul, results=List(x5660, CU.scalarOut(bus_1502_s)))
      Stage(operands=List(x5660, Const(64)), op=FixMod, results=List(x5667, CU.scalarOut(bus_1504_s)))
      Stage(operands=List(x5667, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1505_s), CU.scalarOut(x5653_b6087_x5684_b6095_s)))
      Stage(operands=List(CU.load(x5640_x5657), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1506_s)))
    }
    val x5685_1 = Pipeline(name="x5685_1",parent=x5685) { implicit CU => 
      val x5663 = CU.temp
      val x5664 = CU.temp
      val x5665 = CU.temp
      val x5662 = CU.temp
      val x5660 =  ScalarFIFO(size=1).wtPort(bus_1502_s)
      val x5661 =  ScalarFIFO(size=1).wtPort(bus_1506_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5660), CU.load(x5661)), op=FixAdd, results=List(x5662))
      Stage(operands=List(x5662, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5653_b6088_x5684_b6096_s)))
      Stage(operands=List(x5662, Const(64)), op=FixMod, results=List(x5663))
      Stage(operands=List(x5663, Const(0)), op=FixEql, results=List(x5664))
      Stage(operands=List(Const(64), x5663), op=FixSub, results=List(x5665))
      Stage(operands=List(x5664, Const(0), x5665), op=Mux, results=List(CU.scalarOut(bus_1513_s)))
    }
    val x5685_2 = Pipeline(name="x5685_2",parent=x5685) { implicit CU => 
      val x5681 = CU.temp
      val x5680 = CU.temp
      val x5668 = CU.temp
      val x5661 =  ScalarFIFO(size=1).wtPort(bus_1506_s)
      val x5640_x5657 =  ScalarBuffer().wtPort(x5640_x5643_s)
      val x5666 =  ScalarFIFO(size=1).wtPort(bus_1513_s)
      val x5678 =  ScalarFIFO(size=1).wtPort(bus_1505_s)
      val x5667 =  ScalarFIFO(size=1).wtPort(bus_1504_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5666), Const(4)), op=FixDiv, results=List(x5680))
      Stage(operands=List(CU.load(x5640_x5657), CU.load(x5678)), op=FixAdd, results=List(x5681))
      Stage(operands=List(x5681, x5680), op=FixAdd, results=List(CU.scalarOut(x5653_b6086_x5684_b6094_s)))
      Stage(operands=List(CU.load(x5661), CU.load(x5667)), op=FixAdd, results=List(x5668))
      Stage(operands=List(x5668, CU.load(x5666)), op=FixAdd, results=List(CU.scalarOut(x5652_b6085_x5677_b6093_s)))
    }
    val x5685_3 = Pipeline(name="x5685_3",parent=x5685) { implicit CU => 
      val x5670 = CU.temp
      val x5655 =  ScalarBuffer().wtPort(x5655_argin)
      val x5660 =  ScalarFIFO(size=1).wtPort(bus_1502_s)
      val x5667 =  ScalarFIFO(size=1).wtPort(bus_1504_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5660), CU.load(x5667)), op=FixSub, results=List(x5670))
      Stage(operands=List(x5670, CU.load(x5655)), op=FixAdd, results=List(CU.scalarOut(x5652_b6084_x5677_b6092_s)))
    }
    val x5686 = MemoryController(name="x5686",parent=x5718,offchip=x4935_oc, mctpe=TileLoad) { implicit CU => 
      val x5652_b6085_x5686 =  ScalarFIFO(name="size",size=1).wtPort(x5652_b6085_x5677_b6093_s)
      val x5652_b6084_x5686 =  ScalarFIFO(name="offset",size=1).wtPort(x5652_b6084_x5677_b6092_s)
      CU.newVout("data", x5654_x5686_data_v)
    }
    val x5717 = Sequential(name="x5717",parent=x5718) { implicit CU => 
    }
    val x5699_0 = Pipeline(name="x5699_0",parent=x5717) { implicit CU => 
      val x5653_b6088_x5692_b6091 =  ScalarFIFO(size=16).wtPort(x5653_b6088_x5684_b6096_s)
      val x5653_b6087_x5692_b6090 =  ScalarFIFO(size=16).wtPort(x5653_b6087_x5684_b6095_s)
      val x5653_b6086_x5692_b6089 =  ScalarFIFO(size=16).wtPort(x5653_b6086_x5684_b6094_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5653_b6087_x5692_b6090)), op=Bypass, results=List(CU.scalarOut(x5687_x5694_s)))
      Stage(operands=List(CU.load(x5653_b6088_x5692_b6091)), op=Bypass, results=List(CU.scalarOut(x5688_x5696_s)))
      Stage(operands=List(CU.load(x5653_b6086_x5692_b6089)), op=Bypass, results=List(CU.scalarOut(x5689_x5698_s)))
    }
    val x5716 = Pipeline(name="x5716",parent=x5717) { implicit CU => 
      val x5687_x5703 =  ScalarBuffer().wtPort(x5687_x5694_s)
      val x5689_x5700 =  ScalarBuffer().wtPort(x5689_x5698_s)
      val x5688_x5704 =  ScalarBuffer().wtPort(x5688_x5696_s)
      val ctr32 = Counter(min=Const(0), max=x5689_x5700.load, step=Const(1), par=16) // Counter
      val x5702 = CounterChain(name = "x5702", ctr32).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5787 = StreamController(name="x5787",parent=x5834) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x5720 = CounterChain(name = "x5720", ctr33).iter(1)
    }
    val x5754 = StreamController(name="x5754",parent=x5787) { implicit CU => 
    }
    val x5754_0 = Pipeline(name="x5754_0",parent=x5754) { implicit CU => 
      val x5728 = CU.temp
      val x5736 = CU.temp
      val x5727 = CU.temp
      val x5729 = CU.temp
      val x5645_x5725 =  ScalarBuffer().wtPort(x5645_x5648_s)
      val x5640_x5726 =  ScalarBuffer().wtPort(x5640_x5643_s)
      val x5720 = CounterChain.copy("x5787", "x5720")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5645_x5725), CU.ctr(x5720(0))), op=FixAdd, results=List(x5727))
      Stage(operands=List(x5727, Const(60)), op=FixMul, results=List(x5728))
      Stage(operands=List(x5728, Const(4)), op=FixMul, results=List(x5729, CU.scalarOut(bus_1528_s)))
      Stage(operands=List(x5729, Const(64)), op=FixMod, results=List(x5736, CU.scalarOut(bus_1530_s)))
      Stage(operands=List(x5736, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1531_s), CU.scalarOut(x5722_b6100_x5753_b6108_s)))
      Stage(operands=List(CU.load(x5640_x5726), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_1532_s)))
    }
    val x5754_1 = Pipeline(name="x5754_1",parent=x5754) { implicit CU => 
      val x5734 = CU.temp
      val x5732 = CU.temp
      val x5733 = CU.temp
      val x5731 = CU.temp
      val x5730 =  ScalarFIFO(size=1).wtPort(bus_1532_s)
      val x5729 =  ScalarFIFO(size=1).wtPort(bus_1528_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5729), CU.load(x5730)), op=FixAdd, results=List(x5731))
      Stage(operands=List(x5731, Const(4)), op=FixDiv, results=List(CU.scalarOut(x5722_b6101_x5753_b6109_s)))
      Stage(operands=List(x5731, Const(64)), op=FixMod, results=List(x5732))
      Stage(operands=List(x5732, Const(0)), op=FixEql, results=List(x5733))
      Stage(operands=List(Const(64), x5732), op=FixSub, results=List(x5734))
      Stage(operands=List(x5733, Const(0), x5734), op=Mux, results=List(CU.scalarOut(bus_1539_s)))
    }
    val x5754_2 = Pipeline(name="x5754_2",parent=x5754) { implicit CU => 
      val x5737 = CU.temp
      val x5750 = CU.temp
      val x5749 = CU.temp
      val x5735 =  ScalarFIFO(size=1).wtPort(bus_1539_s)
      val x5747 =  ScalarFIFO(size=1).wtPort(bus_1531_s)
      val x5736 =  ScalarFIFO(size=1).wtPort(bus_1530_s)
      val x5640_x5726 =  ScalarBuffer().wtPort(x5640_x5643_s)
      val x5730 =  ScalarFIFO(size=1).wtPort(bus_1532_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5735), Const(4)), op=FixDiv, results=List(x5749))
      Stage(operands=List(CU.load(x5640_x5726), CU.load(x5747)), op=FixAdd, results=List(x5750))
      Stage(operands=List(x5750, x5749), op=FixAdd, results=List(CU.scalarOut(x5722_b6099_x5753_b6107_s)))
      Stage(operands=List(CU.load(x5730), CU.load(x5736)), op=FixAdd, results=List(x5737))
      Stage(operands=List(x5737, CU.load(x5735)), op=FixAdd, results=List(CU.scalarOut(x5721_b6098_x5746_b6106_s)))
    }
    val x5754_3 = Pipeline(name="x5754_3",parent=x5754) { implicit CU => 
      val x5739 = CU.temp
      val x5729 =  ScalarFIFO(size=1).wtPort(bus_1528_s)
      val x5736 =  ScalarFIFO(size=1).wtPort(bus_1530_s)
      val x5724 =  ScalarBuffer().wtPort(x5724_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5729), CU.load(x5736)), op=FixSub, results=List(x5739))
      Stage(operands=List(x5739, CU.load(x5724)), op=FixAdd, results=List(CU.scalarOut(x5721_b6097_x5746_b6105_s)))
    }
    val x5755 = MemoryController(name="x5755",parent=x5787,offchip=x4936_oc, mctpe=TileLoad) { implicit CU => 
      val x5721_b6097_x5755 =  ScalarFIFO(name="offset",size=1).wtPort(x5721_b6097_x5746_b6105_s)
      val x5721_b6098_x5755 =  ScalarFIFO(name="size",size=1).wtPort(x5721_b6098_x5746_b6106_s)
      CU.newVout("data", x5723_x5755_data_v)
    }
    val x5786 = Sequential(name="x5786",parent=x5787) { implicit CU => 
    }
    val x5768_0 = Pipeline(name="x5768_0",parent=x5786) { implicit CU => 
      val x5722_b6100_x5761_b6103 =  ScalarFIFO(size=16).wtPort(x5722_b6100_x5753_b6108_s)
      val x5722_b6099_x5761_b6102 =  ScalarFIFO(size=16).wtPort(x5722_b6099_x5753_b6107_s)
      val x5722_b6101_x5761_b6104 =  ScalarFIFO(size=16).wtPort(x5722_b6101_x5753_b6109_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5722_b6100_x5761_b6103)), op=Bypass, results=List(CU.scalarOut(x5756_x5763_s)))
      Stage(operands=List(CU.load(x5722_b6101_x5761_b6104)), op=Bypass, results=List(CU.scalarOut(x5757_x5765_s)))
      Stage(operands=List(CU.load(x5722_b6099_x5761_b6102)), op=Bypass, results=List(CU.scalarOut(x5758_x5767_s)))
    }
    val x5785 = Pipeline(name="x5785",parent=x5786) { implicit CU => 
      val x5756_x5772 =  ScalarBuffer().wtPort(x5756_x5763_s)
      val x5758_x5769 =  ScalarBuffer().wtPort(x5758_x5767_s)
      val x5757_x5773 =  ScalarBuffer().wtPort(x5757_x5765_s)
      val ctr34 = Counter(min=Const(0), max=x5758_x5769.load, step=Const(1), par=16) // Counter
      val x5771 = CounterChain(name = "x5771", ctr34).iter(1)
      var stage: List[Stage] = Nil
    }
    val x5812 = StreamController(name="x5812",parent=x5834) { implicit CU => 
    }
    val x5802_0 = Pipeline(name="x5802_0",parent=x5812) { implicit CU => 
      val x5798 = CU.temp
      val x5795_x5795 =  VectorFIFO(size=1).wtPort(x5637_x5795_x5802_v)
      val x5796 =  ScalarBuffer().wtPort(x5796_argin)
      val ctr35 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5792 = CounterChain(name = "x5792", ctr35).iter(384)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5795_x5795), Const(4)), op=FixMul, results=List(x5798))
      Stage(operands=List(x5798, CU.load(x5796)), op=FixAdd, results=List(CU.scalarOut(x5789_x5801_s)))
    }
    val x5803 = MemoryController(name="x5803",parent=x5812,offchip=x4938_oc, mctpe=Gather) { implicit CU => 
      val x5789_x5803 =  ScalarFIFO(name="addr",size=1).wtPort(x5789_x5801_s)
      CU.newVout("data", x5790_x5803_data_v)
    }
    val x5811 = Pipeline(name="x5811",parent=x5812) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x5805 = CounterChain(name = "x5805", ctr36).iter(384)
      var stage: List[Stage] = Nil
    }
    val x5829_0 = Pipeline(name="x5829_0",parent=x5834) { implicit CU => 
      val x5819_x5819 =  VectorFIFO(size=1).wtPort(x5638_x5819_x5829_v)
      val x5820_x5820 =  VectorFIFO(size=1).wtPort(x5639_x5820_x5829_v)
      val x5640_x5814 =  ScalarBuffer().wtPort(x5640_x5643_s)
      val ctr37 = Counter(min=Const(0), max=x5640_x5814.load, step=Const(1), par=16) // Counter
      val x5816 = CounterChain(name = "x5816", ctr37).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5819_x5819), CU.load(x5820_x5820)), op=FixMul, results=List(CU.reduce))
      val (_, rr1560) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1560), op=Bypass, results=List(CU.scalarOut(x5813_x5827_s)))
    }
    val x5833_0 = Pipeline(name="x5833_0",parent=x5834) { implicit CU => 
      val x5813_x5830 =  ScalarBuffer().wtPort(x5813_x5827_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5813_x5830)), op=Bypass, results=List(CU.vecOut(x4953_x5832_v)))
    }
    val x5863 = StreamController(name="x5863",parent=x5949) { implicit CU => 
    }
    val x5855 = Sequential(name="x5855",parent=x5863) { implicit CU => 
    }
    val x5846_0 = Pipeline(name="x5846_0",parent=x5855) { implicit CU => 
      val x5840 = CU.temp
      val x5839 =  ScalarBuffer().wtPort(x5839_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x5840))
      Stage(operands=List(x5840, CU.load(x5839)), op=FixAdd, results=List(CU.scalarOut(x5836_b6110_x5845_b6112_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x5836_b6111_x5845_b6113_s)))
    }
    val x5854 = Pipeline(name="x5854",parent=x5855) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5848 = CounterChain(name = "x5848", ctr38).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5856 = MemoryController(name="x5856",parent=x5863,offchip=x4940_oc, mctpe=TileStore) { implicit CU => 
      val x5837_x5856 =  VectorFIFO(name="data",size=1).wtPort(x4950_x5850_x5854_v)
      val x5836_b6111_x5856 =  ScalarFIFO(name="size",size=1).wtPort(x5836_b6111_x5845_b6113_s)
      val x5836_b6110_x5856 =  ScalarFIFO(name="offset",size=1).wtPort(x5836_b6110_x5845_b6112_s)
    }
    val x5891 = StreamController(name="x5891",parent=x5949) { implicit CU => 
    }
    val x5883 = Sequential(name="x5883",parent=x5891) { implicit CU => 
    }
    val x5874_0 = Pipeline(name="x5874_0",parent=x5883) { implicit CU => 
      val x5868 = CU.temp
      val x5867 =  ScalarBuffer().wtPort(x5867_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x5868))
      Stage(operands=List(x5868, CU.load(x5867)), op=FixAdd, results=List(CU.scalarOut(x5864_b6114_x5873_b6116_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x5864_b6115_x5873_b6117_s)))
    }
    val x5882 = Pipeline(name="x5882",parent=x5883) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5876 = CounterChain(name = "x5876", ctr40).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5884 = MemoryController(name="x5884",parent=x5891,offchip=x4940_oc, mctpe=TileStore) { implicit CU => 
      val x5864_b6115_x5884 =  ScalarFIFO(name="size",size=1).wtPort(x5864_b6115_x5873_b6117_s)
      val x5864_b6114_x5884 =  ScalarFIFO(name="offset",size=1).wtPort(x5864_b6114_x5873_b6116_s)
      val x5865_x5884 =  VectorFIFO(name="data",size=1).wtPort(x4951_x5878_x5882_v)
    }
    val x5919 = StreamController(name="x5919",parent=x5949) { implicit CU => 
    }
    val x5911 = Sequential(name="x5911",parent=x5919) { implicit CU => 
    }
    val x5902_0 = Pipeline(name="x5902_0",parent=x5911) { implicit CU => 
      val x5896 = CU.temp
      val x5895 =  ScalarBuffer().wtPort(x5895_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x5896))
      Stage(operands=List(x5896, CU.load(x5895)), op=FixAdd, results=List(CU.scalarOut(x5892_b6118_x5901_b6120_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x5892_b6119_x5901_b6121_s)))
    }
    val x5910 = Pipeline(name="x5910",parent=x5911) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5904 = CounterChain(name = "x5904", ctr42).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5912 = MemoryController(name="x5912",parent=x5919,offchip=x4940_oc, mctpe=TileStore) { implicit CU => 
      val x5892_b6118_x5912 =  ScalarFIFO(name="offset",size=1).wtPort(x5892_b6118_x5901_b6120_s)
      val x5893_x5912 =  VectorFIFO(name="data",size=1).wtPort(x4952_x5906_x5910_v)
      val x5892_b6119_x5912 =  ScalarFIFO(name="size",size=1).wtPort(x5892_b6119_x5901_b6121_s)
    }
    val x5947 = StreamController(name="x5947",parent=x5949) { implicit CU => 
    }
    val x5939 = Sequential(name="x5939",parent=x5947) { implicit CU => 
    }
    val x5930_0 = Pipeline(name="x5930_0",parent=x5939) { implicit CU => 
      val x5924 = CU.temp
      val x5923 =  ScalarBuffer().wtPort(x5923_argin)
      val x4949 = CounterChain.copy("x5949", "x4949")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4949(0)), Const(4)), op=FixMul, results=List(x5924))
      Stage(operands=List(x5924, CU.load(x5923)), op=FixAdd, results=List(CU.scalarOut(x5920_b6122_x5929_b6124_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x5920_b6123_x5929_b6125_s)))
    }
    val x5938 = Pipeline(name="x5938",parent=x5939) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x5932 = CounterChain(name = "x5932", ctr44).iter(24)
      var stage: List[Stage] = Nil
    }
    val x5940 = MemoryController(name="x5940",parent=x5947,offchip=x4940_oc, mctpe=TileStore) { implicit CU => 
      val x5920_b6123_x5940 =  ScalarFIFO(name="size",size=1).wtPort(x5920_b6123_x5929_b6125_s)
      val x5920_b6122_x5940 =  ScalarFIFO(name="offset",size=1).wtPort(x5920_b6122_x5929_b6124_s)
      val x5921_x5940 =  VectorFIFO(name="data",size=1).wtPort(x4953_x5934_x5938_v)
    }
    
  }
}
