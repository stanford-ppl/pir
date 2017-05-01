import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4719_x4728_data_v = Vector("x4719_x4728_data")
    val x5164_x5189_x5193_v = Vector("x5164_x5189_x5193")
    val x4921_x5063_v = Vector("x4921_x5063")
    val x4925_x5251_v = Vector("x4925_x5251")
    val x5118_x5145_v = Vector("x5118_x5145")
    val x4739_b5428_x4750_b5430_s = Scalar("x4739_b5428_x4750_b5430")
    val x4869_x4878_data_v = Vector("x4869_x4878_data")
    val x4527_argin = ArgIn("x4527")
    val x4925_x5247_x5252_v = Vector("x4925_x5247_x5252")
    val x4499_oc = OffChip("x4499")
    val x4689_b5417_x4700_b5419_s = Scalar("x4689_b5417_x4700_b5419")
    val x4507_x5078_x5087_v = Vector("x4507_x5078_x5087")
    val x4506_x5220_x5228_v = Vector("x4506_x5220_x5228")
    val x4818_b5443_x4826_b5445_s = Scalar("x4818_b5443_x4826_b5445")
    val x4508_b5386_x4515_b5388_s = Scalar("x4508_b5386_x4515_b5388")
    val x4589_b5397_x4600_b5399_s = Scalar("x4589_b5397_x4600_b5399")
    val x4929_x4954_x4958_v = Vector("x4929_x4954_x4958")
    val x5212_x5246_x5252_v = Vector("x5212_x5246_x5252")
    val x4553_x5218_x5228_v = Vector("x4553_x5218_x5228")
    val x4620_argin = ArgIn("x4620")
    val x4820_argin = ArgIn("x4820")
    val x4525_b5389_x4532_b5391_s = Scalar("x4525_b5389_x4532_b5391")
    val x4543_x5319_x5323_v = Vector("x4543_x5319_x5323")
    val x4551_x5124_x5134_v = Vector("x4551_x5124_x5134")
    val x4922_x5106_x5111_v = Vector("x4922_x5106_x5111")
    val x4641_argin = ArgIn("x4641")
    val x4496_oc = OffChip("x4496")
    val x5304_b5552_x5314_b5554_s = Scalar("x5304_b5552_x5314_b5554")
    val x4919_x4965_x4970_v = Vector("x4919_x4965_x4970")
    val x5118_x5152_x5158_v = Vector("x5118_x5152_x5158")
    val x4565_x4815_s = Scalar("x4565_x4815")
    val x4555_x4982_x4993_v = Vector("x4555_x4982_x4993")
    val x4840_x4852_data_v = Vector("x4840_x4852_data")
    val x4619_x4628_data_v = Vector("x4619_x4628_data")
    val x4924_x5265_x5299_v = Vector("x4924_x5265_x5299")
    val x4923_x5157_v = Vector("x4923_x5157")
    val x4868_b5453_x4876_b5455_s = Scalar("x4868_b5453_x4876_b5455")
    val x4506_x4985_x4993_v = Vector("x4506_x4985_x4993")
    val x5023_x5047_x5052_v = Vector("x5023_x5047_x5052")
    val x4923_x5264_x5299_v = Vector("x4923_x5264_x5299")
    val x4921_x5059_x5064_v = Vector("x4921_x5059_x5064")
    val x4506_x5032_x5040_v = Vector("x4506_x5032_x5040")
    val x4920_x5261_x5299_v = Vector("x4920_x5261_x5299")
    val x4639_b5408_x4650_b5410_s = Scalar("x4639_b5408_x4650_b5410")
    val x4493_argin = ArgIn("x4493")
    val x4976_x5001_x5005_v = Vector("x4976_x5001_x5005")
    val x4563_x4715_s = Scalar("x4563_x4715")
    val x4929_x4945_v = Vector("x4929_x4945")
    val x4589_b5398_x4600_b5400_s = Scalar("x4589_b5398_x4600_b5400")
    val x4561_x4615_s = Scalar("x4561_x4615")
    val x5117_x5133_v = Vector("x5117_x5133")
    val x4639_b5407_x4650_b5409_s = Scalar("x4639_b5407_x4650_b5409")
    val x4740_x4752_data_v = Vector("x4740_x4752_data")
    val x4976_x5000_x5005_v = Vector("x4976_x5000_x5005")
    val x5164_x5180_v = Vector("x5164_x5180")
    val x4691_argin = ArgIn("x4691")
    val x4768_b5434_x4776_b5436_s = Scalar("x4768_b5434_x4776_b5436")
    val x4839_b5447_x4850_b5449_s = Scalar("x4839_b5447_x4850_b5449")
    val x4919_x5260_x5299_v = Vector("x4919_x5260_x5299")
    val x5071_x5105_x5111_v = Vector("x5071_x5105_x5111")
    val x4500_oc = OffChip("x4500")
    val x5117_x5141_x5146_v = Vector("x5117_x5141_x5146")
    val x4590_x4602_data_v = Vector("x4590_x4602_data")
    val x4508_b5385_x4515_b5387_s = Scalar("x4508_b5385_x4515_b5387")
    val x4568_b5393_x4576_b5395_s = Scalar("x4568_b5393_x4576_b5395")
    val x4689_b5418_x4700_b5420_s = Scalar("x4689_b5418_x4700_b5420")
    val x4543_x5267_x5299_v = Vector("x4543_x5267_x5299")
    val x4564_x4765_s = Scalar("x4564_x4765")
    val x4562_x4665_s = Scalar("x4562_x4665")
    val x4819_x4828_data_v = Vector("x4819_x4828_data")
    val x4889_b5458_x4900_b5460_s = Scalar("x4889_b5458_x4900_b5460")
    val x4507_x5172_x5181_v = Vector("x4507_x5172_x5181")
    val x4543_x5298_v = Vector("x4543_x5298")
    val x4768_b5433_x4776_b5435_s = Scalar("x4768_b5433_x4776_b5435")
    val x4507_x5125_x5134_v = Vector("x4507_x5125_x5134")
    val x4568_b5394_x4576_b5396_s = Scalar("x4568_b5394_x4576_b5396")
    val x4668_b5414_x4676_b5416_s = Scalar("x4668_b5414_x4676_b5416")
    val x4920_x5016_v = Vector("x4920_x5016")
    val x5070_x5094_x5099_v = Vector("x5070_x5094_x5099")
    val x4868_b5454_x4876_b5456_s = Scalar("x4868_b5454_x4876_b5456")
    val x4741_argin = ArgIn("x4741")
    val x5165_x5192_v = Vector("x5165_x5192")
    val x4718_b5423_x4726_b5425_s = Scalar("x4718_b5423_x4726_b5425")
    val x5070_x5095_x5099_v = Vector("x5070_x5095_x5099")
    val bus_5380_s = Scalar("bus_5380")
    val x4567_x4915_s = Scalar("x4567_x4915")
    val x4507_x4984_x4993_v = Vector("x4507_x4984_x4993")
    val x4818_b5444_x4826_b5446_s = Scalar("x4818_b5444_x4826_b5446")
    val x4526_x4534_data_v = Vector("x4526_x4534_data")
    val x5071_x5098_v = Vector("x5071_x5098")
    val x4554_x4935_x4946_v = Vector("x4554_x4935_x4946")
    val x4790_x4802_data_v = Vector("x4790_x4802_data")
    val x4618_b5403_x4626_b5405_s = Scalar("x4618_b5403_x4626_b5405")
    val x4569_x4578_data_v = Vector("x4569_x4578_data")
    val x4668_b5413_x4676_b5415_s = Scalar("x4668_b5413_x4676_b5415")
    val x4507_x4937_x4946_v = Vector("x4507_x4937_x4946")
    val x4559_x5170_x5181_v = Vector("x4559_x5170_x5181")
    val x5024_x5058_x5064_v = Vector("x5024_x5058_x5064")
    val x4718_b5424_x4726_b5426_s = Scalar("x4718_b5424_x4726_b5426")
    val x4770_argin = ArgIn("x4770")
    val x4506_x4938_x4946_v = Vector("x4506_x4938_x4946")
    val x4920_x5012_x5017_v = Vector("x4920_x5012_x5017")
    val x4930_x4964_x4970_v = Vector("x4930_x4964_x4970")
    val x4506_x5126_x5134_v = Vector("x4506_x5126_x5134")
    val x4556_x5029_x5040_v = Vector("x4556_x5029_x5040")
    val x4547_x4936_x4946_v = Vector("x4547_x4936_x4946")
    val x4669_x4678_data_v = Vector("x4669_x4678_data")
    val x4923_x5153_x5158_v = Vector("x4923_x5153_x5158")
    val x4557_x5076_x5087_v = Vector("x4557_x5076_x5087")
    val x4919_x4969_v = Vector("x4919_x4969")
    val x4591_argin = ArgIn("x4591")
    val x4498_oc = OffChip("x4498")
    val x4922_x5263_x5299_v = Vector("x4922_x5263_x5299")
    val x4924_x5204_v = Vector("x4924_x5204")
    val x4891_argin = ArgIn("x4891")
    val x5070_x5086_v = Vector("x5070_x5086")
    val x4558_x5123_x5134_v = Vector("x4558_x5123_x5134")
    val x5211_x5235_x5240_v = Vector("x5211_x5235_x5240")
    val x4889_b5457_x4900_b5459_s = Scalar("x4889_b5457_x4900_b5459")
    val x5212_x5239_v = Vector("x5212_x5239")
    val x4548_x4983_x4993_v = Vector("x4548_x4983_x4993")
    val x4552_x5171_x5181_v = Vector("x4552_x5171_x5181")
    val x5211_x5227_v = Vector("x5211_x5227")
    val x4507_x5219_x5228_v = Vector("x4507_x5219_x5228")
    val x4549_x5030_x5040_v = Vector("x4549_x5030_x5040")
    val x4506_x5173_x5181_v = Vector("x4506_x5173_x5181")
    val x5164_x5188_x5193_v = Vector("x5164_x5188_x5193")
    val x4924_x5200_x5205_v = Vector("x4924_x5200_x5205")
    val x4922_x5110_v = Vector("x4922_x5110")
    val x4977_x5004_v = Vector("x4977_x5004")
    val x4507_x5031_x5040_v = Vector("x4507_x5031_x5040")
    val x4841_argin = ArgIn("x4841")
    val x4739_b5427_x4750_b5429_s = Scalar("x4739_b5427_x4750_b5429")
    val x4720_argin = ArgIn("x4720")
    val x5307_argin = ArgIn("x5307")
    val x5023_x5048_x5052_v = Vector("x5023_x5048_x5052")
    val x4670_argin = ArgIn("x4670")
    val x4690_x4702_data_v = Vector("x4690_x4702_data")
    val x5117_x5142_x5146_v = Vector("x5117_x5142_x5146")
    val x4921_x5262_x5299_v = Vector("x4921_x5262_x5299")
    val x4525_b5390_x4532_b5392_s = Scalar("x4525_b5390_x4532_b5392")
    val x4570_argin = ArgIn("x4570")
    val x4769_x4778_data_v = Vector("x4769_x4778_data")
    val x4566_x4865_s = Scalar("x4566_x4865")
    val x4870_argin = ArgIn("x4870")
    val x4789_b5437_x4800_b5439_s = Scalar("x4789_b5437_x4800_b5439")
    val x4789_b5438_x4800_b5440_s = Scalar("x4789_b5438_x4800_b5440")
    val x4925_x5266_x5299_v = Vector("x4925_x5266_x5299")
    val x4839_b5448_x4850_b5450_s = Scalar("x4839_b5448_x4850_b5450")
    val x4560_x5217_x5228_v = Vector("x4560_x5217_x5228")
    val x4506_x5079_x5087_v = Vector("x4506_x5079_x5087")
    val x4791_argin = ArgIn("x4791")
    val x4510_argin = ArgIn("x4510")
    val x5023_x5039_v = Vector("x5023_x5039")
    val x5304_b5551_x5314_b5553_s = Scalar("x5304_b5551_x5314_b5553")
    val x4509_x4517_data_v = Vector("x4509_x4517_data")
    val x4976_x4992_v = Vector("x4976_x4992")
    val x4550_x5077_x5087_v = Vector("x4550_x5077_x5087")
    val x4618_b5404_x4626_b5406_s = Scalar("x4618_b5404_x4626_b5406")
    val x4930_x4957_v = Vector("x4930_x4957")
    val x5211_x5236_x5240_v = Vector("x5211_x5236_x5240")
    val x4501_oc = OffChip("x4501")
    val x4929_x4953_x4958_v = Vector("x4929_x4953_x4958")
    val x4890_x4902_data_v = Vector("x4890_x4902_data")
    val x5165_x5199_x5205_v = Vector("x5165_x5199_x5205")
    val x4977_x5011_x5017_v = Vector("x4977_x5011_x5017")
    val x4640_x4652_data_v = Vector("x4640_x4652_data")
    val x5024_x5051_v = Vector("x5024_x5051")
    val x5333 = Sequential(name="x5333",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5333_unit = CounterChain(name = "x5333_unit", ctr1)
    }
    val x4506_dsp0 = MemoryPipeline(name="x4506_dsp0",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x5214 = CounterChain.copy("x5228_0", "x5214")
      val x4506_x5220 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x5220_x5228_v).rdAddr(x5214(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp1 = MemoryPipeline(name="x4506_dsp1",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x5167 = CounterChain.copy("x5181_0", "x5167")
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x4506_x5173 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x5173_x5181_v).rdAddr(x5167(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp2 = MemoryPipeline(name="x4506_dsp2",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x5120 = CounterChain.copy("x5134_0", "x5120")
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x4506_x5126 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x5126_x5134_v).rdAddr(x5120(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp3 = MemoryPipeline(name="x4506_dsp3",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x5073 = CounterChain.copy("x5087_0", "x5073")
      val x4506_x5079 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x5079_x5087_v).rdAddr(x5073(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp4 = MemoryPipeline(name="x4506_dsp4",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x5026 = CounterChain.copy("x5040_0", "x5026")
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x4506_x5032 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x5032_x5040_v).rdAddr(x5026(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp5 = MemoryPipeline(name="x4506_dsp5",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x4979 = CounterChain.copy("x4993_0", "x4979")
      val x4506_x4985 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x4985_x4993_v).rdAddr(x4979(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4506_dsp6 = MemoryPipeline(name="x4506_dsp6",parent="x5333") { implicit CU => 
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4509_x4517_data_v)
      val x4932 = CounterChain.copy("x4946_0", "x4932")
      val x4519 = CounterChain.copy("x4523", "x4519")
      val x4506_x4938 =  SRAM(size=96,banking = Strided(1)).wtPort(x4522_x4522.readPort).rdPort(x4506_x4938_x4946_v).rdAddr(x4932(0)).wtAddr(x4519(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp0 = MemoryPipeline(name="x4507_dsp0",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x5214 = CounterChain.copy("x5228_0", "x5214")
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4507_x5219 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x5219_x5228_v).rdAddr(x5214(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp1 = MemoryPipeline(name="x4507_dsp1",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x5167 = CounterChain.copy("x5181_0", "x5167")
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4507_x5172 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x5172_x5181_v).rdAddr(x5167(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp2 = MemoryPipeline(name="x4507_dsp2",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x5120 = CounterChain.copy("x5134_0", "x5120")
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4507_x5125 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x5125_x5134_v).rdAddr(x5120(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp3 = MemoryPipeline(name="x4507_dsp3",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x5073 = CounterChain.copy("x5087_0", "x5073")
      val x4507_x5078 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x5078_x5087_v).rdAddr(x5073(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp4 = MemoryPipeline(name="x4507_dsp4",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x5026 = CounterChain.copy("x5040_0", "x5026")
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4507_x5031 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x5031_x5040_v).rdAddr(x5026(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp5 = MemoryPipeline(name="x4507_dsp5",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4979 = CounterChain.copy("x4993_0", "x4979")
      val x4507_x4984 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x4984_x4993_v).rdAddr(x4979(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4507_dsp6 = MemoryPipeline(name="x4507_dsp6",parent="x5333") { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4526_x4534_data_v)
      val x4932 = CounterChain.copy("x4946_0", "x4932")
      val x4536 = CounterChain.copy("x4540", "x4536")
      val x4507_x4937 =  SRAM(size=96,banking = Strided(1)).wtPort(x4539_x4539.readPort).rdPort(x4507_x4937_x4946_v).rdAddr(x4932(0)).wtAddr(x4536(0))
      var stage: List[Stage] = Nil
    }
    val x4524 = StreamController(name="x4524",parent=x5333) { implicit CU => 
      val ctr2 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4524_unit = CounterChain(name = "x4524_unit", ctr2)
    }
    val x4516_0 = Pipeline(name="x4516_0",parent=x4524) { implicit CU => 
      val x4510 =  ScalarBuffer().wtPort(x4510_argin)
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4516_unit = CounterChain(name = "x4516_unit", ctr3)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4510)), op=FixAdd, results=List(CU.scalarOut(x4508_b5385_x4515_b5387_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4508_b5386_x4515_b5388_s)))
    }
    val x4517 = MemoryController(name="x4517",parent=x4524,offchip=x4499_oc, mctpe=TileLoad) { implicit CU => 
      val x4508_b5385_x4517 =  ScalarFIFO(name="offset",size=1).wtPort(x4508_b5385_x4515_b5387_s)
      val x4508_b5386_x4517 =  ScalarFIFO(name="size",size=1).wtPort(x4508_b5386_x4515_b5388_s)
      CU.newVout("data", x4509_x4517_data_v)
    }
    val x4523 = Pipeline(name="x4523",parent=x4524) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4519 = CounterChain(name = "x4519", ctr4)
      var stage: List[Stage] = Nil
    }
    val x4541 = StreamController(name="x4541",parent=x5333) { implicit CU => 
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4541_unit = CounterChain(name = "x4541_unit", ctr5)
    }
    val x4533_0 = Pipeline(name="x4533_0",parent=x4541) { implicit CU => 
      val x4527 =  ScalarBuffer().wtPort(x4527_argin)
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4533_unit = CounterChain(name = "x4533_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4527)), op=FixAdd, results=List(CU.scalarOut(x4525_b5389_x4532_b5391_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4525_b5390_x4532_b5392_s)))
    }
    val x4534 = MemoryController(name="x4534",parent=x4541,offchip=x4500_oc, mctpe=TileLoad) { implicit CU => 
      val x4525_b5390_x4534 =  ScalarFIFO(name="size",size=1).wtPort(x4525_b5390_x4532_b5392_s)
      val x4525_b5389_x4534 =  ScalarFIFO(name="offset",size=1).wtPort(x4525_b5389_x4532_b5391_s)
      CU.newVout("data", x4526_x4534_data_v)
    }
    val x4540 = Pipeline(name="x4540",parent=x4541) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4536 = CounterChain(name = "x4536", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4543_dsp0 = MemoryPipeline(name="x4543_dsp0",parent="x5301") { implicit CU => 
      val b5549 = CU.temp
      val b5555 = CU.temp
      val x5298_x5298 =  VectorFIFO(size=1).wtPort(x4543_x5298_v)
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x5303 = CounterChain.copy("x5332", "x5303")
      val x5317 = CounterChain.copy("x5323", "x5317")
      val x4543_x5319 =  SRAM(size=9216,banking = Strided(1)).wtPort(x5298_x5298.readPort).rdPort(x4543_x5319_x5323_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5549))
      WAStage(operands=List(b5549, CU.ctr(x5258(1))), op=FixAdd, results=List(x4543_x5319.writeAddr))
      RAStage(operands=List(CU.ctr(x5303(0)), Const(96)), op=FixMul, results=List(b5555))
      RAStage(operands=List(b5555, CU.ctr(x5317(0))), op=FixAdd, results=List(x4543_x5319.readAddr))
    }
    val x4543_dsp1 = MemoryPipeline(name="x4543_dsp1",parent="x5301") { implicit CU => 
      val b5547 = CU.temp
      val b5549 = CU.temp
      val x5298_x5298 =  VectorFIFO(size=1).wtPort(x4543_x5298_v)
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4543_x5267 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5298_x5298.readPort).rdPort(x4543_x5267_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5549))
      WAStage(operands=List(b5549, CU.ctr(x5258(1))), op=FixAdd, results=List(x4543_x5267.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5547))
      RAStage(operands=List(b5547, CU.ctr(x5258(1))), op=FixAdd, results=List(x4543_x5267.readAddr))
    }
    val x5301 = MetaPipeline(name="x5301",parent=x5333) { implicit CU => 
      val x4493_x4544 =  ScalarBuffer().wtPort(x4493_argin)
      val ctr8 = Counter(min=Const(0), max=x4493_x4544.load, step=Const(96), par=7) // Counter
      val x4546 = CounterChain(name = "x4546", ctr8)
    }
    val x4547_dsp0 = MemoryPipeline(name="x4547_dsp0",parent="x5301") { implicit CU => 
      val x4584_x4584 =  VectorFIFO(size=1).wtPort(x4569_x4578_data_v)
      val x4580 = CounterChain.copy("x4585", "x4580")
      val x4928 = CounterChain.copy("x4972", "x4928")
      val x4547_x4936 =  SRAM(size=96,banking = Strided(1)).wtPort(x4584_x4584.readPort).rdPort(x4547_x4936_x4946_v).rdAddr(x4928(0)).wtAddr(x4580(0))
      var stage: List[Stage] = Nil
    }
    val x4548_dsp0 = MemoryPipeline(name="x4548_dsp0",parent="x5301") { implicit CU => 
      val x4634_x4634 =  VectorFIFO(size=1).wtPort(x4619_x4628_data_v)
      val x4630 = CounterChain.copy("x4635", "x4630")
      val x4975 = CounterChain.copy("x5019", "x4975")
      val x4548_x4983 =  SRAM(size=96,banking = Strided(1)).wtPort(x4634_x4634.readPort).rdPort(x4548_x4983_x4993_v).rdAddr(x4975(0)).wtAddr(x4630(0))
      var stage: List[Stage] = Nil
    }
    val x4549_dsp0 = MemoryPipeline(name="x4549_dsp0",parent="x5301") { implicit CU => 
      val x4684_x4684 =  VectorFIFO(size=1).wtPort(x4669_x4678_data_v)
      val x4680 = CounterChain.copy("x4685", "x4680")
      val x5022 = CounterChain.copy("x5066", "x5022")
      val x4549_x5030 =  SRAM(size=96,banking = Strided(1)).wtPort(x4684_x4684.readPort).rdPort(x4549_x5030_x5040_v).rdAddr(x5022(0)).wtAddr(x4680(0))
      var stage: List[Stage] = Nil
    }
    val x4550_dsp0 = MemoryPipeline(name="x4550_dsp0",parent="x5301") { implicit CU => 
      val x4734_x4734 =  VectorFIFO(size=1).wtPort(x4719_x4728_data_v)
      val x4730 = CounterChain.copy("x4735", "x4730")
      val x5069 = CounterChain.copy("x5113", "x5069")
      val x4550_x5077 =  SRAM(size=96,banking = Strided(1)).wtPort(x4734_x4734.readPort).rdPort(x4550_x5077_x5087_v).rdAddr(x5069(0)).wtAddr(x4730(0))
      var stage: List[Stage] = Nil
    }
    val x4551_dsp0 = MemoryPipeline(name="x4551_dsp0",parent="x5301") { implicit CU => 
      val x4784_x4784 =  VectorFIFO(size=1).wtPort(x4769_x4778_data_v)
      val x4780 = CounterChain.copy("x4785", "x4780")
      val x5116 = CounterChain.copy("x5160", "x5116")
      val x4551_x5124 =  SRAM(size=96,banking = Strided(1)).wtPort(x4784_x4784.readPort).rdPort(x4551_x5124_x5134_v).rdAddr(x5116(0)).wtAddr(x4780(0))
      var stage: List[Stage] = Nil
    }
    val x4552_dsp0 = MemoryPipeline(name="x4552_dsp0",parent="x5301") { implicit CU => 
      val x4834_x4834 =  VectorFIFO(size=1).wtPort(x4819_x4828_data_v)
      val x4830 = CounterChain.copy("x4835", "x4830")
      val x5163 = CounterChain.copy("x5207", "x5163")
      val x4552_x5171 =  SRAM(size=96,banking = Strided(1)).wtPort(x4834_x4834.readPort).rdPort(x4552_x5171_x5181_v).rdAddr(x5163(0)).wtAddr(x4830(0))
      var stage: List[Stage] = Nil
    }
    val x4553_dsp0 = MemoryPipeline(name="x4553_dsp0",parent="x5301") { implicit CU => 
      val x4884_x4884 =  VectorFIFO(size=1).wtPort(x4869_x4878_data_v)
      val x4880 = CounterChain.copy("x4885", "x4880")
      val x5210 = CounterChain.copy("x5254", "x5210")
      val x4553_x5218 =  SRAM(size=96,banking = Strided(1)).wtPort(x4884_x4884.readPort).rdPort(x4553_x5218_x5228_v).rdAddr(x5210(0)).wtAddr(x4880(0))
      var stage: List[Stage] = Nil
    }
    val x4554_dsp0 = MemoryPipeline(name="x4554_dsp0",parent="x5301") { implicit CU => 
      val b5401 = CU.temp
      val b5463 = CU.temp
      val x4609_x4609 =  VectorFIFO(size=1).wtPort(x4590_x4602_data_v)
      val x4932 = CounterChain.copy("x4946_0", "x4932")
      val x4928 = CounterChain.copy("x4972", "x4928")
      val x4588 = CounterChain.copy("x4611", "x4588")
      val x4604 = CounterChain.copy("x4610", "x4604")
      val x4554_x4935 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4609_x4609.readPort).rdPort(x4554_x4935_x4946_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4588(0)), Const(96)), op=FixMul, results=List(b5401))
      WAStage(operands=List(b5401, CU.ctr(x4604(0))), op=FixAdd, results=List(x4554_x4935.writeAddr))
      RAStage(operands=List(CU.ctr(x4928(0)), Const(96)), op=FixMul, results=List(b5463))
      RAStage(operands=List(b5463, CU.ctr(x4932(0))), op=FixAdd, results=List(x4554_x4935.readAddr))
    }
    val x4555_dsp0 = MemoryPipeline(name="x4555_dsp0",parent="x5301") { implicit CU => 
      val b5473 = CU.temp
      val b5411 = CU.temp
      val x4659_x4659 =  VectorFIFO(size=1).wtPort(x4640_x4652_data_v)
      val x4654 = CounterChain.copy("x4660", "x4654")
      val x4975 = CounterChain.copy("x5019", "x4975")
      val x4979 = CounterChain.copy("x4993_0", "x4979")
      val x4638 = CounterChain.copy("x4661", "x4638")
      val x4555_x4982 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4659_x4659.readPort).rdPort(x4555_x4982_x4993_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4638(0)), Const(96)), op=FixMul, results=List(b5411))
      WAStage(operands=List(b5411, CU.ctr(x4654(0))), op=FixAdd, results=List(x4555_x4982.writeAddr))
      RAStage(operands=List(CU.ctr(x4975(0)), Const(96)), op=FixMul, results=List(b5473))
      RAStage(operands=List(b5473, CU.ctr(x4979(0))), op=FixAdd, results=List(x4555_x4982.readAddr))
    }
    val x4556_dsp0 = MemoryPipeline(name="x4556_dsp0",parent="x5301") { implicit CU => 
      val b5421 = CU.temp
      val b5483 = CU.temp
      val x4709_x4709 =  VectorFIFO(size=1).wtPort(x4690_x4702_data_v)
      val x5026 = CounterChain.copy("x5040_0", "x5026")
      val x4704 = CounterChain.copy("x4710", "x4704")
      val x5022 = CounterChain.copy("x5066", "x5022")
      val x4688 = CounterChain.copy("x4711", "x4688")
      val x4556_x5029 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4709_x4709.readPort).rdPort(x4556_x5029_x5040_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4688(0)), Const(96)), op=FixMul, results=List(b5421))
      WAStage(operands=List(b5421, CU.ctr(x4704(0))), op=FixAdd, results=List(x4556_x5029.writeAddr))
      RAStage(operands=List(CU.ctr(x5022(0)), Const(96)), op=FixMul, results=List(b5483))
      RAStage(operands=List(b5483, CU.ctr(x5026(0))), op=FixAdd, results=List(x4556_x5029.readAddr))
    }
    val x4557_dsp0 = MemoryPipeline(name="x4557_dsp0",parent="x5301") { implicit CU => 
      val b5431 = CU.temp
      val b5493 = CU.temp
      val x4759_x4759 =  VectorFIFO(size=1).wtPort(x4740_x4752_data_v)
      val x4738 = CounterChain.copy("x4761", "x4738")
      val x4754 = CounterChain.copy("x4760", "x4754")
      val x5069 = CounterChain.copy("x5113", "x5069")
      val x5073 = CounterChain.copy("x5087_0", "x5073")
      val x4557_x5076 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4759_x4759.readPort).rdPort(x4557_x5076_x5087_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4738(0)), Const(96)), op=FixMul, results=List(b5431))
      WAStage(operands=List(b5431, CU.ctr(x4754(0))), op=FixAdd, results=List(x4557_x5076.writeAddr))
      RAStage(operands=List(CU.ctr(x5069(0)), Const(96)), op=FixMul, results=List(b5493))
      RAStage(operands=List(b5493, CU.ctr(x5073(0))), op=FixAdd, results=List(x4557_x5076.readAddr))
    }
    val x4558_dsp0 = MemoryPipeline(name="x4558_dsp0",parent="x5301") { implicit CU => 
      val b5503 = CU.temp
      val b5441 = CU.temp
      val x4809_x4809 =  VectorFIFO(size=1).wtPort(x4790_x4802_data_v)
      val x4788 = CounterChain.copy("x4811", "x4788")
      val x5116 = CounterChain.copy("x5160", "x5116")
      val x5120 = CounterChain.copy("x5134_0", "x5120")
      val x4804 = CounterChain.copy("x4810", "x4804")
      val x4558_x5123 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4809_x4809.readPort).rdPort(x4558_x5123_x5134_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4788(0)), Const(96)), op=FixMul, results=List(b5441))
      WAStage(operands=List(b5441, CU.ctr(x4804(0))), op=FixAdd, results=List(x4558_x5123.writeAddr))
      RAStage(operands=List(CU.ctr(x5116(0)), Const(96)), op=FixMul, results=List(b5503))
      RAStage(operands=List(b5503, CU.ctr(x5120(0))), op=FixAdd, results=List(x4558_x5123.readAddr))
    }
    val x4559_dsp0 = MemoryPipeline(name="x4559_dsp0",parent="x5301") { implicit CU => 
      val b5451 = CU.temp
      val b5513 = CU.temp
      val x4859_x4859 =  VectorFIFO(size=1).wtPort(x4840_x4852_data_v)
      val x5167 = CounterChain.copy("x5181_0", "x5167")
      val x4854 = CounterChain.copy("x4860", "x4854")
      val x5163 = CounterChain.copy("x5207", "x5163")
      val x4838 = CounterChain.copy("x4861", "x4838")
      val x4559_x5170 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4859_x4859.readPort).rdPort(x4559_x5170_x5181_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4838(0)), Const(96)), op=FixMul, results=List(b5451))
      WAStage(operands=List(b5451, CU.ctr(x4854(0))), op=FixAdd, results=List(x4559_x5170.writeAddr))
      RAStage(operands=List(CU.ctr(x5163(0)), Const(96)), op=FixMul, results=List(b5513))
      RAStage(operands=List(b5513, CU.ctr(x5167(0))), op=FixAdd, results=List(x4559_x5170.readAddr))
    }
    val x4560_dsp0 = MemoryPipeline(name="x4560_dsp0",parent="x5301") { implicit CU => 
      val b5523 = CU.temp
      val b5461 = CU.temp
      val x4909_x4909 =  VectorFIFO(size=1).wtPort(x4890_x4902_data_v)
      val x5210 = CounterChain.copy("x5254", "x5210")
      val x5214 = CounterChain.copy("x5228_0", "x5214")
      val x4904 = CounterChain.copy("x4910", "x4904")
      val x4888 = CounterChain.copy("x4911", "x4888")
      val x4560_x5217 =  SRAM(size=9216,banking = Strided(1)).wtPort(x4909_x4909.readPort).rdPort(x4560_x5217_x5228_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4888(0)), Const(96)), op=FixMul, results=List(b5461))
      WAStage(operands=List(b5461, CU.ctr(x4904(0))), op=FixAdd, results=List(x4560_x5217.writeAddr))
      RAStage(operands=List(CU.ctr(x5210(0)), Const(96)), op=FixMul, results=List(b5523))
      RAStage(operands=List(b5523, CU.ctr(x5214(0))), op=FixAdd, results=List(x4560_x5217.readAddr))
    }
    val x4586 = StreamController(name="x4586",parent=x5301) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4586_unit = CounterChain(name = "x4586_unit", ctr9)
    }
    val x4577_0 = Pipeline(name="x4577_0",parent=x4586) { implicit CU => 
      val x4571 = CU.temp
      val x4570 =  ScalarBuffer().wtPort(x4570_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4577_unit = CounterChain(name = "x4577_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4571))
      Stage(operands=List(x4571, CU.load(x4570)), op=FixAdd, results=List(CU.scalarOut(x4568_b5393_x4576_b5395_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4568_b5394_x4576_b5396_s)))
    }
    val x4578 = MemoryController(name="x4578",parent=x4586,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4568_b5394_x4578 =  ScalarFIFO(name="size",size=1).wtPort(x4568_b5394_x4576_b5396_s)
      val x4568_b5393_x4578 =  ScalarFIFO(name="offset",size=1).wtPort(x4568_b5393_x4576_b5395_s)
      CU.newVout("data", x4569_x4578_data_v)
    }
    val x4585 = Pipeline(name="x4585",parent=x4586) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4580 = CounterChain(name = "x4580", ctr11)
      var stage: List[Stage] = Nil
    }
    val x4611 = StreamController(name="x4611",parent=x5301) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4588 = CounterChain(name = "x4588", ctr12)
    }
    val x4601_0 = Pipeline(name="x4601_0",parent=x4611) { implicit CU => 
      val x4593 = CU.temp
      val x4594 = CU.temp
      val x4592 = CU.temp
      val x4591 =  ScalarBuffer().wtPort(x4591_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4588 = CounterChain.copy("x4611", "x4588")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4601_unit = CounterChain(name = "x4601_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4588(0))), op=FixAdd, results=List(x4592))
      Stage(operands=List(x4592, Const(96)), op=FixMul, results=List(x4593))
      Stage(operands=List(x4593, Const(4)), op=FixMul, results=List(x4594))
      Stage(operands=List(x4594, CU.load(x4591)), op=FixAdd, results=List(CU.scalarOut(x4589_b5397_x4600_b5399_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4589_b5398_x4600_b5400_s)))
    }
    val x4602 = MemoryController(name="x4602",parent=x4611,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4589_b5397_x4602 =  ScalarFIFO(name="offset",size=1).wtPort(x4589_b5397_x4600_b5399_s)
      val x4589_b5398_x4602 =  ScalarFIFO(name="size",size=1).wtPort(x4589_b5398_x4600_b5400_s)
      CU.newVout("data", x4590_x4602_data_v)
    }
    val x4610 = Pipeline(name="x4610",parent=x4611) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4604 = CounterChain(name = "x4604", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4616_0 = Pipeline(name="x4616_0",parent=x5301) { implicit CU => 
      val x4613 = CU.temp
      val x4493_x4612 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4616_unit = CounterChain(name = "x4616_unit", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4612), CU.ctr(x4546(0))), op=FixSub, results=List(x4613))
      Stage(operands=List(x4613, Const(96)), op=FixMin, results=List(CU.scalarOut(x4561_x4615_s)))
    }
    val x4636 = StreamController(name="x4636",parent=x5301) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4636_unit = CounterChain(name = "x4636_unit", ctr16)
    }
    val x4627_0 = Pipeline(name="x4627_0",parent=x4636) { implicit CU => 
      val x4621 = CU.temp
      val x4620 =  ScalarBuffer().wtPort(x4620_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4627_unit = CounterChain(name = "x4627_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4621))
      Stage(operands=List(x4621, CU.load(x4620)), op=FixAdd, results=List(CU.scalarOut(x4618_b5403_x4626_b5405_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4618_b5404_x4626_b5406_s)))
    }
    val x4628 = MemoryController(name="x4628",parent=x4636,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4618_b5403_x4628 =  ScalarFIFO(name="offset",size=1).wtPort(x4618_b5403_x4626_b5405_s)
      val x4618_b5404_x4628 =  ScalarFIFO(name="size",size=1).wtPort(x4618_b5404_x4626_b5406_s)
      CU.newVout("data", x4619_x4628_data_v)
    }
    val x4635 = Pipeline(name="x4635",parent=x4636) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4630 = CounterChain(name = "x4630", ctr18)
      var stage: List[Stage] = Nil
    }
    val x4661 = StreamController(name="x4661",parent=x5301) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4638 = CounterChain(name = "x4638", ctr19)
    }
    val x4651_0 = Pipeline(name="x4651_0",parent=x4661) { implicit CU => 
      val x4642 = CU.temp
      val x4643 = CU.temp
      val x4644 = CU.temp
      val x4641 =  ScalarBuffer().wtPort(x4641_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4638 = CounterChain.copy("x4661", "x4638")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4651_unit = CounterChain(name = "x4651_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4638(0))), op=FixAdd, results=List(x4642))
      Stage(operands=List(x4642, Const(96)), op=FixMul, results=List(x4643))
      Stage(operands=List(x4643, Const(4)), op=FixMul, results=List(x4644))
      Stage(operands=List(x4644, CU.load(x4641)), op=FixAdd, results=List(CU.scalarOut(x4639_b5407_x4650_b5409_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4639_b5408_x4650_b5410_s)))
    }
    val x4652 = MemoryController(name="x4652",parent=x4661,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4639_b5408_x4652 =  ScalarFIFO(name="size",size=1).wtPort(x4639_b5408_x4650_b5410_s)
      val x4639_b5407_x4652 =  ScalarFIFO(name="offset",size=1).wtPort(x4639_b5407_x4650_b5409_s)
      CU.newVout("data", x4640_x4652_data_v)
    }
    val x4660 = Pipeline(name="x4660",parent=x4661) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4654 = CounterChain(name = "x4654", ctr21)
      var stage: List[Stage] = Nil
    }
    val x4666_0 = Pipeline(name="x4666_0",parent=x5301) { implicit CU => 
      val x4663 = CU.temp
      val x4493_x4662 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4666_unit = CounterChain(name = "x4666_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4662), CU.ctr(x4546(0))), op=FixSub, results=List(x4663))
      Stage(operands=List(x4663, Const(96)), op=FixMin, results=List(CU.scalarOut(x4562_x4665_s)))
    }
    val x4686 = StreamController(name="x4686",parent=x5301) { implicit CU => 
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4686_unit = CounterChain(name = "x4686_unit", ctr23)
    }
    val x4677_0 = Pipeline(name="x4677_0",parent=x4686) { implicit CU => 
      val x4671 = CU.temp
      val x4670 =  ScalarBuffer().wtPort(x4670_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4677_unit = CounterChain(name = "x4677_unit", ctr24)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4671))
      Stage(operands=List(x4671, CU.load(x4670)), op=FixAdd, results=List(CU.scalarOut(x4668_b5413_x4676_b5415_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4668_b5414_x4676_b5416_s)))
    }
    val x4678 = MemoryController(name="x4678",parent=x4686,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4668_b5414_x4678 =  ScalarFIFO(name="size",size=1).wtPort(x4668_b5414_x4676_b5416_s)
      val x4668_b5413_x4678 =  ScalarFIFO(name="offset",size=1).wtPort(x4668_b5413_x4676_b5415_s)
      CU.newVout("data", x4669_x4678_data_v)
    }
    val x4685 = Pipeline(name="x4685",parent=x4686) { implicit CU => 
      val ctr25 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4680 = CounterChain(name = "x4680", ctr25)
      var stage: List[Stage] = Nil
    }
    val x4711 = StreamController(name="x4711",parent=x5301) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4688 = CounterChain(name = "x4688", ctr26)
    }
    val x4701_0 = Pipeline(name="x4701_0",parent=x4711) { implicit CU => 
      val x4694 = CU.temp
      val x4693 = CU.temp
      val x4692 = CU.temp
      val x4691 =  ScalarBuffer().wtPort(x4691_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4688 = CounterChain.copy("x4711", "x4688")
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4701_unit = CounterChain(name = "x4701_unit", ctr27)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4688(0))), op=FixAdd, results=List(x4692))
      Stage(operands=List(x4692, Const(96)), op=FixMul, results=List(x4693))
      Stage(operands=List(x4693, Const(4)), op=FixMul, results=List(x4694))
      Stage(operands=List(x4694, CU.load(x4691)), op=FixAdd, results=List(CU.scalarOut(x4689_b5417_x4700_b5419_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4689_b5418_x4700_b5420_s)))
    }
    val x4702 = MemoryController(name="x4702",parent=x4711,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4689_b5418_x4702 =  ScalarFIFO(name="size",size=1).wtPort(x4689_b5418_x4700_b5420_s)
      val x4689_b5417_x4702 =  ScalarFIFO(name="offset",size=1).wtPort(x4689_b5417_x4700_b5419_s)
      CU.newVout("data", x4690_x4702_data_v)
    }
    val x4710 = Pipeline(name="x4710",parent=x4711) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4704 = CounterChain(name = "x4704", ctr28)
      var stage: List[Stage] = Nil
    }
    val x4716_0 = Pipeline(name="x4716_0",parent=x5301) { implicit CU => 
      val x4713 = CU.temp
      val x4493_x4712 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4716_unit = CounterChain(name = "x4716_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4712), CU.ctr(x4546(0))), op=FixSub, results=List(x4713))
      Stage(operands=List(x4713, Const(96)), op=FixMin, results=List(CU.scalarOut(x4563_x4715_s)))
    }
    val x4736 = StreamController(name="x4736",parent=x5301) { implicit CU => 
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4736_unit = CounterChain(name = "x4736_unit", ctr30)
    }
    val x4727_0 = Pipeline(name="x4727_0",parent=x4736) { implicit CU => 
      val x4721 = CU.temp
      val x4720 =  ScalarBuffer().wtPort(x4720_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4727_unit = CounterChain(name = "x4727_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4721))
      Stage(operands=List(x4721, CU.load(x4720)), op=FixAdd, results=List(CU.scalarOut(x4718_b5423_x4726_b5425_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4718_b5424_x4726_b5426_s)))
    }
    val x4728 = MemoryController(name="x4728",parent=x4736,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4718_b5424_x4728 =  ScalarFIFO(name="size",size=1).wtPort(x4718_b5424_x4726_b5426_s)
      val x4718_b5423_x4728 =  ScalarFIFO(name="offset",size=1).wtPort(x4718_b5423_x4726_b5425_s)
      CU.newVout("data", x4719_x4728_data_v)
    }
    val x4735 = Pipeline(name="x4735",parent=x4736) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4730 = CounterChain(name = "x4730", ctr32)
      var stage: List[Stage] = Nil
    }
    val x4761 = StreamController(name="x4761",parent=x5301) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4738 = CounterChain(name = "x4738", ctr33)
    }
    val x4751_0 = Pipeline(name="x4751_0",parent=x4761) { implicit CU => 
      val x4744 = CU.temp
      val x4742 = CU.temp
      val x4743 = CU.temp
      val x4741 =  ScalarBuffer().wtPort(x4741_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4738 = CounterChain.copy("x4761", "x4738")
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4751_unit = CounterChain(name = "x4751_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4738(0))), op=FixAdd, results=List(x4742))
      Stage(operands=List(x4742, Const(96)), op=FixMul, results=List(x4743))
      Stage(operands=List(x4743, Const(4)), op=FixMul, results=List(x4744))
      Stage(operands=List(x4744, CU.load(x4741)), op=FixAdd, results=List(CU.scalarOut(x4739_b5427_x4750_b5429_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4739_b5428_x4750_b5430_s)))
    }
    val x4752 = MemoryController(name="x4752",parent=x4761,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4739_b5427_x4752 =  ScalarFIFO(name="offset",size=1).wtPort(x4739_b5427_x4750_b5429_s)
      val x4739_b5428_x4752 =  ScalarFIFO(name="size",size=1).wtPort(x4739_b5428_x4750_b5430_s)
      CU.newVout("data", x4740_x4752_data_v)
    }
    val x4760 = Pipeline(name="x4760",parent=x4761) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4754 = CounterChain(name = "x4754", ctr35)
      var stage: List[Stage] = Nil
    }
    val x4766_0 = Pipeline(name="x4766_0",parent=x5301) { implicit CU => 
      val x4763 = CU.temp
      val x4493_x4762 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4766_unit = CounterChain(name = "x4766_unit", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4762), CU.ctr(x4546(0))), op=FixSub, results=List(x4763))
      Stage(operands=List(x4763, Const(96)), op=FixMin, results=List(CU.scalarOut(x4564_x4765_s)))
    }
    val x4786 = StreamController(name="x4786",parent=x5301) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4786_unit = CounterChain(name = "x4786_unit", ctr37)
    }
    val x4777_0 = Pipeline(name="x4777_0",parent=x4786) { implicit CU => 
      val x4771 = CU.temp
      val x4770 =  ScalarBuffer().wtPort(x4770_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4777_unit = CounterChain(name = "x4777_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4771))
      Stage(operands=List(x4771, CU.load(x4770)), op=FixAdd, results=List(CU.scalarOut(x4768_b5433_x4776_b5435_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4768_b5434_x4776_b5436_s)))
    }
    val x4778 = MemoryController(name="x4778",parent=x4786,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4768_b5433_x4778 =  ScalarFIFO(name="offset",size=1).wtPort(x4768_b5433_x4776_b5435_s)
      val x4768_b5434_x4778 =  ScalarFIFO(name="size",size=1).wtPort(x4768_b5434_x4776_b5436_s)
      CU.newVout("data", x4769_x4778_data_v)
    }
    val x4785 = Pipeline(name="x4785",parent=x4786) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4780 = CounterChain(name = "x4780", ctr39)
      var stage: List[Stage] = Nil
    }
    val x4811 = StreamController(name="x4811",parent=x5301) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4788 = CounterChain(name = "x4788", ctr40)
    }
    val x4801_0 = Pipeline(name="x4801_0",parent=x4811) { implicit CU => 
      val x4794 = CU.temp
      val x4792 = CU.temp
      val x4793 = CU.temp
      val x4791 =  ScalarBuffer().wtPort(x4791_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4788 = CounterChain.copy("x4811", "x4788")
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4801_unit = CounterChain(name = "x4801_unit", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4788(0))), op=FixAdd, results=List(x4792))
      Stage(operands=List(x4792, Const(96)), op=FixMul, results=List(x4793))
      Stage(operands=List(x4793, Const(4)), op=FixMul, results=List(x4794))
      Stage(operands=List(x4794, CU.load(x4791)), op=FixAdd, results=List(CU.scalarOut(x4789_b5437_x4800_b5439_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4789_b5438_x4800_b5440_s)))
    }
    val x4802 = MemoryController(name="x4802",parent=x4811,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4789_b5438_x4802 =  ScalarFIFO(name="size",size=1).wtPort(x4789_b5438_x4800_b5440_s)
      val x4789_b5437_x4802 =  ScalarFIFO(name="offset",size=1).wtPort(x4789_b5437_x4800_b5439_s)
      CU.newVout("data", x4790_x4802_data_v)
    }
    val x4810 = Pipeline(name="x4810",parent=x4811) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4804 = CounterChain(name = "x4804", ctr42)
      var stage: List[Stage] = Nil
    }
    val x4816_0 = Pipeline(name="x4816_0",parent=x5301) { implicit CU => 
      val x4813 = CU.temp
      val x4493_x4812 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4816_unit = CounterChain(name = "x4816_unit", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4812), CU.ctr(x4546(0))), op=FixSub, results=List(x4813))
      Stage(operands=List(x4813, Const(96)), op=FixMin, results=List(CU.scalarOut(x4565_x4815_s)))
    }
    val x4836 = StreamController(name="x4836",parent=x5301) { implicit CU => 
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4836_unit = CounterChain(name = "x4836_unit", ctr44)
    }
    val x4827_0 = Pipeline(name="x4827_0",parent=x4836) { implicit CU => 
      val x4821 = CU.temp
      val x4820 =  ScalarBuffer().wtPort(x4820_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4827_unit = CounterChain(name = "x4827_unit", ctr45)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4821))
      Stage(operands=List(x4821, CU.load(x4820)), op=FixAdd, results=List(CU.scalarOut(x4818_b5443_x4826_b5445_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4818_b5444_x4826_b5446_s)))
    }
    val x4828 = MemoryController(name="x4828",parent=x4836,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4818_b5444_x4828 =  ScalarFIFO(name="size",size=1).wtPort(x4818_b5444_x4826_b5446_s)
      val x4818_b5443_x4828 =  ScalarFIFO(name="offset",size=1).wtPort(x4818_b5443_x4826_b5445_s)
      CU.newVout("data", x4819_x4828_data_v)
    }
    val x4835 = Pipeline(name="x4835",parent=x4836) { implicit CU => 
      val ctr46 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4830 = CounterChain(name = "x4830", ctr46)
      var stage: List[Stage] = Nil
    }
    val x4861 = StreamController(name="x4861",parent=x5301) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4838 = CounterChain(name = "x4838", ctr47)
    }
    val x4851_0 = Pipeline(name="x4851_0",parent=x4861) { implicit CU => 
      val x4844 = CU.temp
      val x4843 = CU.temp
      val x4842 = CU.temp
      val x4841 =  ScalarBuffer().wtPort(x4841_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4838 = CounterChain.copy("x4861", "x4838")
      val ctr48 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4851_unit = CounterChain(name = "x4851_unit", ctr48)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4838(0))), op=FixAdd, results=List(x4842))
      Stage(operands=List(x4842, Const(96)), op=FixMul, results=List(x4843))
      Stage(operands=List(x4843, Const(4)), op=FixMul, results=List(x4844))
      Stage(operands=List(x4844, CU.load(x4841)), op=FixAdd, results=List(CU.scalarOut(x4839_b5447_x4850_b5449_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4839_b5448_x4850_b5450_s)))
    }
    val x4852 = MemoryController(name="x4852",parent=x4861,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4839_b5448_x4852 =  ScalarFIFO(name="size",size=1).wtPort(x4839_b5448_x4850_b5450_s)
      val x4839_b5447_x4852 =  ScalarFIFO(name="offset",size=1).wtPort(x4839_b5447_x4850_b5449_s)
      CU.newVout("data", x4840_x4852_data_v)
    }
    val x4860 = Pipeline(name="x4860",parent=x4861) { implicit CU => 
      val ctr49 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4854 = CounterChain(name = "x4854", ctr49)
      var stage: List[Stage] = Nil
    }
    val x4866_0 = Pipeline(name="x4866_0",parent=x5301) { implicit CU => 
      val x4863 = CU.temp
      val x4493_x4862 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4866_unit = CounterChain(name = "x4866_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4862), CU.ctr(x4546(0))), op=FixSub, results=List(x4863))
      Stage(operands=List(x4863, Const(96)), op=FixMin, results=List(CU.scalarOut(x4566_x4865_s)))
    }
    val x4886 = StreamController(name="x4886",parent=x5301) { implicit CU => 
      val ctr51 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4886_unit = CounterChain(name = "x4886_unit", ctr51)
    }
    val x4877_0 = Pipeline(name="x4877_0",parent=x4886) { implicit CU => 
      val x4871 = CU.temp
      val x4870 =  ScalarBuffer().wtPort(x4870_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4877_unit = CounterChain(name = "x4877_unit", ctr52)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), Const(4)), op=FixMul, results=List(x4871))
      Stage(operands=List(x4871, CU.load(x4870)), op=FixAdd, results=List(CU.scalarOut(x4868_b5453_x4876_b5455_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4868_b5454_x4876_b5456_s)))
    }
    val x4878 = MemoryController(name="x4878",parent=x4886,offchip=x4498_oc, mctpe=TileLoad) { implicit CU => 
      val x4868_b5454_x4878 =  ScalarFIFO(name="size",size=1).wtPort(x4868_b5454_x4876_b5456_s)
      val x4868_b5453_x4878 =  ScalarFIFO(name="offset",size=1).wtPort(x4868_b5453_x4876_b5455_s)
      CU.newVout("data", x4869_x4878_data_v)
    }
    val x4885 = Pipeline(name="x4885",parent=x4886) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4880 = CounterChain(name = "x4880", ctr53)
      var stage: List[Stage] = Nil
    }
    val x4911 = StreamController(name="x4911",parent=x5301) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4888 = CounterChain(name = "x4888", ctr54)
    }
    val x4901_0 = Pipeline(name="x4901_0",parent=x4911) { implicit CU => 
      val x4892 = CU.temp
      val x4894 = CU.temp
      val x4893 = CU.temp
      val x4891 =  ScalarBuffer().wtPort(x4891_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val x4888 = CounterChain.copy("x4911", "x4888")
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4901_unit = CounterChain(name = "x4901_unit", ctr55)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4546(0)), CU.ctr(x4888(0))), op=FixAdd, results=List(x4892))
      Stage(operands=List(x4892, Const(96)), op=FixMul, results=List(x4893))
      Stage(operands=List(x4893, Const(4)), op=FixMul, results=List(x4894))
      Stage(operands=List(x4894, CU.load(x4891)), op=FixAdd, results=List(CU.scalarOut(x4889_b5457_x4900_b5459_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x4889_b5458_x4900_b5460_s)))
    }
    val x4902 = MemoryController(name="x4902",parent=x4911,offchip=x4496_oc, mctpe=TileLoad) { implicit CU => 
      val x4889_b5457_x4902 =  ScalarFIFO(name="offset",size=1).wtPort(x4889_b5457_x4900_b5459_s)
      val x4889_b5458_x4902 =  ScalarFIFO(name="size",size=1).wtPort(x4889_b5458_x4900_b5460_s)
      CU.newVout("data", x4890_x4902_data_v)
    }
    val x4910 = Pipeline(name="x4910",parent=x4911) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4904 = CounterChain(name = "x4904", ctr56)
      var stage: List[Stage] = Nil
    }
    val x4916_0 = Pipeline(name="x4916_0",parent=x5301) { implicit CU => 
      val x4913 = CU.temp
      val x4493_x4912 =  ScalarBuffer().wtPort(x4493_argin)
      val x4546 = CounterChain.copy("x5301", "x4546")
      val ctr57 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4916_unit = CounterChain(name = "x4916_unit", ctr57)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4912), CU.ctr(x4546(0))), op=FixSub, results=List(x4913))
      Stage(operands=List(x4913, Const(96)), op=FixMin, results=List(CU.scalarOut(x4567_x4915_s)))
    }
    val x4919_dsp0 = MemoryPipeline(name="x4919_dsp0",parent="x4972") { implicit CU => 
      val b5533 = CU.temp
      val b5471 = CU.temp
      val x4969_x4969 =  VectorFIFO(size=1).wtPort(x4919_x4969_v)
      val x4961 = CounterChain.copy("x4970_0", "x4961")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4919_x5260 =  SRAM(size=9216,banking = NoBanking()).wtPort(x4969_x4969.readPort).rdPort(x4919_x5260_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4961(0)), Const(96)), op=FixMul, results=List(b5471))
      WAStage(operands=List(b5471, CU.ctr(x4961(1))), op=FixAdd, results=List(x4919_x5260.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5533))
      RAStage(operands=List(b5533, CU.ctr(x5258(1))), op=FixAdd, results=List(x4919_x5260.readAddr))
    }
    val x4919_dsp1 = MemoryPipeline(name="x4919_dsp1",parent="x4972") { implicit CU => 
      val b5469 = CU.temp
      val b5471 = CU.temp
      val x4969_x4969 =  VectorFIFO(size=1).wtPort(x4919_x4969_v)
      val x4961 = CounterChain.copy("x4970_0", "x4961")
      val x4919_x4965 =  SRAM(size=9216,banking = NoBanking()).wtPort(x4969_x4969.readPort).rdPort(x4919_x4965_x4970_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4961(0)), Const(96)), op=FixMul, results=List(b5471))
      WAStage(operands=List(b5471, CU.ctr(x4961(1))), op=FixAdd, results=List(x4919_x4965.writeAddr))
      RAStage(operands=List(CU.ctr(x4961(0)), Const(96)), op=FixMul, results=List(b5469))
      RAStage(operands=List(b5469, CU.ctr(x4961(1))), op=FixAdd, results=List(x4919_x4965.readAddr))
    }
    val x4920_dsp0 = MemoryPipeline(name="x4920_dsp0",parent="x5019") { implicit CU => 
      val b5535 = CU.temp
      val b5481 = CU.temp
      val x5016_x5016 =  VectorFIFO(size=1).wtPort(x4920_x5016_v)
      val x5008 = CounterChain.copy("x5017_0", "x5008")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4920_x5261 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5016_x5016.readPort).rdPort(x4920_x5261_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5008(0)), Const(96)), op=FixMul, results=List(b5481))
      WAStage(operands=List(b5481, CU.ctr(x5008(1))), op=FixAdd, results=List(x4920_x5261.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5535))
      RAStage(operands=List(b5535, CU.ctr(x5258(1))), op=FixAdd, results=List(x4920_x5261.readAddr))
    }
    val x4920_dsp1 = MemoryPipeline(name="x4920_dsp1",parent="x5019") { implicit CU => 
      val b5481 = CU.temp
      val b5479 = CU.temp
      val x5016_x5016 =  VectorFIFO(size=1).wtPort(x4920_x5016_v)
      val x5008 = CounterChain.copy("x5017_0", "x5008")
      val x4920_x5012 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5016_x5016.readPort).rdPort(x4920_x5012_x5017_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5008(0)), Const(96)), op=FixMul, results=List(b5481))
      WAStage(operands=List(b5481, CU.ctr(x5008(1))), op=FixAdd, results=List(x4920_x5012.writeAddr))
      RAStage(operands=List(CU.ctr(x5008(0)), Const(96)), op=FixMul, results=List(b5479))
      RAStage(operands=List(b5479, CU.ctr(x5008(1))), op=FixAdd, results=List(x4920_x5012.readAddr))
    }
    val x4921_dsp0 = MemoryPipeline(name="x4921_dsp0",parent="x5066") { implicit CU => 
      val b5491 = CU.temp
      val b5537 = CU.temp
      val x5063_x5063 =  VectorFIFO(size=1).wtPort(x4921_x5063_v)
      val x5055 = CounterChain.copy("x5064_0", "x5055")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4921_x5262 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5063_x5063.readPort).rdPort(x4921_x5262_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5055(0)), Const(96)), op=FixMul, results=List(b5491))
      WAStage(operands=List(b5491, CU.ctr(x5055(1))), op=FixAdd, results=List(x4921_x5262.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5537))
      RAStage(operands=List(b5537, CU.ctr(x5258(1))), op=FixAdd, results=List(x4921_x5262.readAddr))
    }
    val x4921_dsp1 = MemoryPipeline(name="x4921_dsp1",parent="x5066") { implicit CU => 
      val b5489 = CU.temp
      val b5491 = CU.temp
      val x5063_x5063 =  VectorFIFO(size=1).wtPort(x4921_x5063_v)
      val x5055 = CounterChain.copy("x5064_0", "x5055")
      val x4921_x5059 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5063_x5063.readPort).rdPort(x4921_x5059_x5064_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5055(0)), Const(96)), op=FixMul, results=List(b5491))
      WAStage(operands=List(b5491, CU.ctr(x5055(1))), op=FixAdd, results=List(x4921_x5059.writeAddr))
      RAStage(operands=List(CU.ctr(x5055(0)), Const(96)), op=FixMul, results=List(b5489))
      RAStage(operands=List(b5489, CU.ctr(x5055(1))), op=FixAdd, results=List(x4921_x5059.readAddr))
    }
    val x4922_dsp0 = MemoryPipeline(name="x4922_dsp0",parent="x5113") { implicit CU => 
      val b5539 = CU.temp
      val b5501 = CU.temp
      val x5110_x5110 =  VectorFIFO(size=1).wtPort(x4922_x5110_v)
      val x5102 = CounterChain.copy("x5111_0", "x5102")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4922_x5263 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5110_x5110.readPort).rdPort(x4922_x5263_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5102(0)), Const(96)), op=FixMul, results=List(b5501))
      WAStage(operands=List(b5501, CU.ctr(x5102(1))), op=FixAdd, results=List(x4922_x5263.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5539))
      RAStage(operands=List(b5539, CU.ctr(x5258(1))), op=FixAdd, results=List(x4922_x5263.readAddr))
    }
    val x4922_dsp1 = MemoryPipeline(name="x4922_dsp1",parent="x5113") { implicit CU => 
      val b5499 = CU.temp
      val b5501 = CU.temp
      val x5110_x5110 =  VectorFIFO(size=1).wtPort(x4922_x5110_v)
      val x5102 = CounterChain.copy("x5111_0", "x5102")
      val x4922_x5106 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5110_x5110.readPort).rdPort(x4922_x5106_x5111_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5102(0)), Const(96)), op=FixMul, results=List(b5501))
      WAStage(operands=List(b5501, CU.ctr(x5102(1))), op=FixAdd, results=List(x4922_x5106.writeAddr))
      RAStage(operands=List(CU.ctr(x5102(0)), Const(96)), op=FixMul, results=List(b5499))
      RAStage(operands=List(b5499, CU.ctr(x5102(1))), op=FixAdd, results=List(x4922_x5106.readAddr))
    }
    val x4923_dsp0 = MemoryPipeline(name="x4923_dsp0",parent="x5160") { implicit CU => 
      val b5511 = CU.temp
      val b5541 = CU.temp
      val x5157_x5157 =  VectorFIFO(size=1).wtPort(x4923_x5157_v)
      val x5149 = CounterChain.copy("x5158_0", "x5149")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4923_x5264 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5157_x5157.readPort).rdPort(x4923_x5264_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5149(0)), Const(96)), op=FixMul, results=List(b5511))
      WAStage(operands=List(b5511, CU.ctr(x5149(1))), op=FixAdd, results=List(x4923_x5264.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5541))
      RAStage(operands=List(b5541, CU.ctr(x5258(1))), op=FixAdd, results=List(x4923_x5264.readAddr))
    }
    val x4923_dsp1 = MemoryPipeline(name="x4923_dsp1",parent="x5160") { implicit CU => 
      val b5509 = CU.temp
      val b5511 = CU.temp
      val x5157_x5157 =  VectorFIFO(size=1).wtPort(x4923_x5157_v)
      val x5149 = CounterChain.copy("x5158_0", "x5149")
      val x4923_x5153 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5157_x5157.readPort).rdPort(x4923_x5153_x5158_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5149(0)), Const(96)), op=FixMul, results=List(b5511))
      WAStage(operands=List(b5511, CU.ctr(x5149(1))), op=FixAdd, results=List(x4923_x5153.writeAddr))
      RAStage(operands=List(CU.ctr(x5149(0)), Const(96)), op=FixMul, results=List(b5509))
      RAStage(operands=List(b5509, CU.ctr(x5149(1))), op=FixAdd, results=List(x4923_x5153.readAddr))
    }
    val x4924_dsp0 = MemoryPipeline(name="x4924_dsp0",parent="x5207") { implicit CU => 
      val b5521 = CU.temp
      val b5543 = CU.temp
      val x5204_x5204 =  VectorFIFO(size=1).wtPort(x4924_x5204_v)
      val x5196 = CounterChain.copy("x5205_0", "x5196")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4924_x5265 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5204_x5204.readPort).rdPort(x4924_x5265_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5196(0)), Const(96)), op=FixMul, results=List(b5521))
      WAStage(operands=List(b5521, CU.ctr(x5196(1))), op=FixAdd, results=List(x4924_x5265.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5543))
      RAStage(operands=List(b5543, CU.ctr(x5258(1))), op=FixAdd, results=List(x4924_x5265.readAddr))
    }
    val x4924_dsp1 = MemoryPipeline(name="x4924_dsp1",parent="x5207") { implicit CU => 
      val b5521 = CU.temp
      val b5519 = CU.temp
      val x5204_x5204 =  VectorFIFO(size=1).wtPort(x4924_x5204_v)
      val x5196 = CounterChain.copy("x5205_0", "x5196")
      val x4924_x5200 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5204_x5204.readPort).rdPort(x4924_x5200_x5205_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5196(0)), Const(96)), op=FixMul, results=List(b5521))
      WAStage(operands=List(b5521, CU.ctr(x5196(1))), op=FixAdd, results=List(x4924_x5200.writeAddr))
      RAStage(operands=List(CU.ctr(x5196(0)), Const(96)), op=FixMul, results=List(b5519))
      RAStage(operands=List(b5519, CU.ctr(x5196(1))), op=FixAdd, results=List(x4924_x5200.readAddr))
    }
    val x4925_dsp0 = MemoryPipeline(name="x4925_dsp0",parent="x5254") { implicit CU => 
      val b5545 = CU.temp
      val b5531 = CU.temp
      val x5251_x5251 =  VectorFIFO(size=1).wtPort(x4925_x5251_v)
      val x5243 = CounterChain.copy("x5252_0", "x5243")
      val x5258 = CounterChain.copy("x5299", "x5258")
      val x4925_x5266 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5251_x5251.readPort).rdPort(x4925_x5266_x5299_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5243(0)), Const(96)), op=FixMul, results=List(b5531))
      WAStage(operands=List(b5531, CU.ctr(x5243(1))), op=FixAdd, results=List(x4925_x5266.writeAddr))
      RAStage(operands=List(CU.ctr(x5258(0)), Const(96)), op=FixMul, results=List(b5545))
      RAStage(operands=List(b5545, CU.ctr(x5258(1))), op=FixAdd, results=List(x4925_x5266.readAddr))
    }
    val x4925_dsp1 = MemoryPipeline(name="x4925_dsp1",parent="x5254") { implicit CU => 
      val b5529 = CU.temp
      val b5531 = CU.temp
      val x5251_x5251 =  VectorFIFO(size=1).wtPort(x4925_x5251_v)
      val x5243 = CounterChain.copy("x5252_0", "x5243")
      val x4925_x5247 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5251_x5251.readPort).rdPort(x4925_x5247_x5252_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5243(0)), Const(96)), op=FixMul, results=List(b5531))
      WAStage(operands=List(b5531, CU.ctr(x5243(1))), op=FixAdd, results=List(x4925_x5247.writeAddr))
      RAStage(operands=List(CU.ctr(x5243(0)), Const(96)), op=FixMul, results=List(b5529))
      RAStage(operands=List(b5529, CU.ctr(x5243(1))), op=FixAdd, results=List(x4925_x5247.readAddr))
    }
    val x4972 = MetaPipeline(name="x4972",parent=x5301) { implicit CU => 
      val x4561_x4926 =  ScalarBuffer().wtPort(x4561_x4615_s)
      val ctr58 = Counter(min=Const(0), max=x4561_x4926.load, step=Const(1), par=1) // Counter
      val x4928 = CounterChain(name = "x4928", ctr58)
    }
    val x4929_dsp0 = MemoryPipeline(name="x4929_dsp0",parent="x4972") { implicit CU => 
      val x4945_x4945 =  VectorFIFO(size=1).wtPort(x4929_x4945_v)
      val x4932 = CounterChain.copy("x4946_0", "x4932")
      val x4949 = CounterChain.copy("x4958_0", "x4949")
      val x4929_x4954 =  SRAM(size=96,banking = Strided(1)).wtPort(x4945_x4945.readPort).rdPort(x4929_x4954_x4958_v).rdAddr(x4949(1)).wtAddr(x4932(0))
      var stage: List[Stage] = Nil
    }
    val x4929_dsp1 = MemoryPipeline(name="x4929_dsp1",parent="x4972") { implicit CU => 
      val x4945_x4945 =  VectorFIFO(size=1).wtPort(x4929_x4945_v)
      val x4932 = CounterChain.copy("x4946_0", "x4932")
      val x4949 = CounterChain.copy("x4958_0", "x4949")
      val x4929_x4953 =  SRAM(size=96,banking = Strided(1)).wtPort(x4945_x4945.readPort).rdPort(x4929_x4953_x4958_v).rdAddr(x4949(0)).wtAddr(x4932(0))
      var stage: List[Stage] = Nil
    }
    val x4930_dsp0 = MemoryPipeline(name="x4930_dsp0",parent="x4972") { implicit CU => 
      val b5465 = CU.temp
      val b5467 = CU.temp
      val x4957_x4957 =  VectorFIFO(size=1).wtPort(x4930_x4957_v)
      val x4949 = CounterChain.copy("x4958_0", "x4949")
      val x4961 = CounterChain.copy("x4970_0", "x4961")
      val x4930_x4964 =  SRAM(size=9216,banking = NoBanking()).wtPort(x4957_x4957.readPort).rdPort(x4930_x4964_x4970_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4949(0)), Const(96)), op=FixMul, results=List(b5465))
      WAStage(operands=List(b5465, CU.ctr(x4949(1))), op=FixAdd, results=List(x4930_x4964.writeAddr))
      RAStage(operands=List(CU.ctr(x4961(0)), Const(96)), op=FixMul, results=List(b5467))
      RAStage(operands=List(b5467, CU.ctr(x4961(1))), op=FixAdd, results=List(x4930_x4964.readAddr))
    }
    val x4946_0 = Pipeline(name="x4946_0",parent=x4972) { implicit CU => 
      val x4942 = CU.temp
      val x4943 = CU.temp
      val x4937_x4937 =  VectorFIFO(size=1).wtPort(x4507_x4937_x4946_v)
      val x4936_x4936 =  VectorFIFO(size=1).wtPort(x4547_x4936_x4946_v)
      val x4938_x4938 =  VectorFIFO(size=1).wtPort(x4506_x4938_x4946_v)
      val x4935_x4935 =  VectorFIFO(size=1).wtPort(x4554_x4935_x4946_v)
      val ctr59 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4932 = CounterChain(name = "x4932", ctr59)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4936_x4936), Const(1)), op=FixEql, results=List(x4942))
      Stage(operands=List(x4942, CU.load(x4937_x4937), CU.load(x4938_x4938)), op=Mux, results=List(x4943))
      Stage(operands=List(CU.load(x4935_x4935), x4943), op=FixSub, results=List(CU.vecOut(x4929_x4945_v)))
    }
    val x4958_0 = Pipeline(name="x4958_0",parent=x4972) { implicit CU => 
      val x4954_x4954 =  VectorFIFO(size=1).wtPort(x4929_x4954_x4958_v)
      val x4953_x4953 =  VectorFIFO(size=1).wtPort(x4929_x4953_x4958_v)
      val ctr60 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr61 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4949 = CounterChain(name = "x4949", ctr60, ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4953_x4953), CU.load(x4954_x4954)), op=FixMul, results=List(CU.vecOut(x4930_x4957_v)))
    }
    val x4970_0 = Pipeline(name="x4970_0",parent=x4972) { implicit CU => 
      val x4964_x4964 =  VectorFIFO(size=1).wtPort(x4930_x4964_x4970_v)
      val x4965_x4965 =  VectorFIFO(size=1).wtPort(x4919_x4965_x4970_v)
      val ctr62 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr63 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x4961 = CounterChain(name = "x4961", ctr62, ctr63)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4964_x4964), CU.load(x4965_x4965)), op=FixAdd, results=List(CU.vecOut(x4919_x4969_v)))
    }
    val x5019 = MetaPipeline(name="x5019",parent=x5301) { implicit CU => 
      val x4562_x4973 =  ScalarBuffer().wtPort(x4562_x4665_s)
      val ctr64 = Counter(min=Const(0), max=x4562_x4973.load, step=Const(1), par=1) // Counter
      val x4975 = CounterChain(name = "x4975", ctr64)
    }
    val x4976_dsp0 = MemoryPipeline(name="x4976_dsp0",parent="x5019") { implicit CU => 
      val x4992_x4992 =  VectorFIFO(size=1).wtPort(x4976_x4992_v)
      val x4979 = CounterChain.copy("x4993_0", "x4979")
      val x4996 = CounterChain.copy("x5005_0", "x4996")
      val x4976_x5001 =  SRAM(size=96,banking = Strided(1)).wtPort(x4992_x4992.readPort).rdPort(x4976_x5001_x5005_v).rdAddr(x4996(1)).wtAddr(x4979(0))
      var stage: List[Stage] = Nil
    }
    val x4976_dsp1 = MemoryPipeline(name="x4976_dsp1",parent="x5019") { implicit CU => 
      val x4992_x4992 =  VectorFIFO(size=1).wtPort(x4976_x4992_v)
      val x4979 = CounterChain.copy("x4993_0", "x4979")
      val x4996 = CounterChain.copy("x5005_0", "x4996")
      val x4976_x5000 =  SRAM(size=96,banking = Strided(1)).wtPort(x4992_x4992.readPort).rdPort(x4976_x5000_x5005_v).rdAddr(x4996(0)).wtAddr(x4979(0))
      var stage: List[Stage] = Nil
    }
    val x4977_dsp0 = MemoryPipeline(name="x4977_dsp0",parent="x5019") { implicit CU => 
      val b5477 = CU.temp
      val b5475 = CU.temp
      val x5004_x5004 =  VectorFIFO(size=1).wtPort(x4977_x5004_v)
      val x4996 = CounterChain.copy("x5005_0", "x4996")
      val x5008 = CounterChain.copy("x5017_0", "x5008")
      val x4977_x5011 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5004_x5004.readPort).rdPort(x4977_x5011_x5017_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4996(0)), Const(96)), op=FixMul, results=List(b5475))
      WAStage(operands=List(b5475, CU.ctr(x4996(1))), op=FixAdd, results=List(x4977_x5011.writeAddr))
      RAStage(operands=List(CU.ctr(x5008(0)), Const(96)), op=FixMul, results=List(b5477))
      RAStage(operands=List(b5477, CU.ctr(x5008(1))), op=FixAdd, results=List(x4977_x5011.readAddr))
    }
    val x4993_0 = Pipeline(name="x4993_0",parent=x5019) { implicit CU => 
      val x4990 = CU.temp
      val x4989 = CU.temp
      val x4982_x4982 =  VectorFIFO(size=1).wtPort(x4555_x4982_x4993_v)
      val x4984_x4984 =  VectorFIFO(size=1).wtPort(x4507_x4984_x4993_v)
      val x4983_x4983 =  VectorFIFO(size=1).wtPort(x4548_x4983_x4993_v)
      val x4985_x4985 =  VectorFIFO(size=1).wtPort(x4506_x4985_x4993_v)
      val ctr65 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4979 = CounterChain(name = "x4979", ctr65)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4983_x4983), Const(1)), op=FixEql, results=List(x4989))
      Stage(operands=List(x4989, CU.load(x4984_x4984), CU.load(x4985_x4985)), op=Mux, results=List(x4990))
      Stage(operands=List(CU.load(x4982_x4982), x4990), op=FixSub, results=List(CU.vecOut(x4976_x4992_v)))
    }
    val x5005_0 = Pipeline(name="x5005_0",parent=x5019) { implicit CU => 
      val x5000_x5000 =  VectorFIFO(size=1).wtPort(x4976_x5000_x5005_v)
      val x5001_x5001 =  VectorFIFO(size=1).wtPort(x4976_x5001_x5005_v)
      val ctr66 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x4996 = CounterChain(name = "x4996", ctr66, ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5000_x5000), CU.load(x5001_x5001)), op=FixMul, results=List(CU.vecOut(x4977_x5004_v)))
    }
    val x5017_0 = Pipeline(name="x5017_0",parent=x5019) { implicit CU => 
      val x5011_x5011 =  VectorFIFO(size=1).wtPort(x4977_x5011_x5017_v)
      val x5012_x5012 =  VectorFIFO(size=1).wtPort(x4920_x5012_x5017_v)
      val ctr68 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr69 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5008 = CounterChain(name = "x5008", ctr68, ctr69)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5011_x5011), CU.load(x5012_x5012)), op=FixAdd, results=List(CU.vecOut(x4920_x5016_v)))
    }
    val x5066 = MetaPipeline(name="x5066",parent=x5301) { implicit CU => 
      val x4563_x5020 =  ScalarBuffer().wtPort(x4563_x4715_s)
      val ctr70 = Counter(min=Const(0), max=x4563_x5020.load, step=Const(1), par=1) // Counter
      val x5022 = CounterChain(name = "x5022", ctr70)
    }
    val x5023_dsp0 = MemoryPipeline(name="x5023_dsp0",parent="x5066") { implicit CU => 
      val x5039_x5039 =  VectorFIFO(size=1).wtPort(x5023_x5039_v)
      val x5026 = CounterChain.copy("x5040_0", "x5026")
      val x5043 = CounterChain.copy("x5052_0", "x5043")
      val x5023_x5048 =  SRAM(size=96,banking = Strided(1)).wtPort(x5039_x5039.readPort).rdPort(x5023_x5048_x5052_v).rdAddr(x5043(1)).wtAddr(x5026(0))
      var stage: List[Stage] = Nil
    }
    val x5023_dsp1 = MemoryPipeline(name="x5023_dsp1",parent="x5066") { implicit CU => 
      val x5039_x5039 =  VectorFIFO(size=1).wtPort(x5023_x5039_v)
      val x5026 = CounterChain.copy("x5040_0", "x5026")
      val x5043 = CounterChain.copy("x5052_0", "x5043")
      val x5023_x5047 =  SRAM(size=96,banking = Strided(1)).wtPort(x5039_x5039.readPort).rdPort(x5023_x5047_x5052_v).rdAddr(x5043(0)).wtAddr(x5026(0))
      var stage: List[Stage] = Nil
    }
    val x5024_dsp0 = MemoryPipeline(name="x5024_dsp0",parent="x5066") { implicit CU => 
      val b5487 = CU.temp
      val b5485 = CU.temp
      val x5051_x5051 =  VectorFIFO(size=1).wtPort(x5024_x5051_v)
      val x5043 = CounterChain.copy("x5052_0", "x5043")
      val x5055 = CounterChain.copy("x5064_0", "x5055")
      val x5024_x5058 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5051_x5051.readPort).rdPort(x5024_x5058_x5064_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5043(0)), Const(96)), op=FixMul, results=List(b5485))
      WAStage(operands=List(b5485, CU.ctr(x5043(1))), op=FixAdd, results=List(x5024_x5058.writeAddr))
      RAStage(operands=List(CU.ctr(x5055(0)), Const(96)), op=FixMul, results=List(b5487))
      RAStage(operands=List(b5487, CU.ctr(x5055(1))), op=FixAdd, results=List(x5024_x5058.readAddr))
    }
    val x5040_0 = Pipeline(name="x5040_0",parent=x5066) { implicit CU => 
      val x5037 = CU.temp
      val x5036 = CU.temp
      val x5029_x5029 =  VectorFIFO(size=1).wtPort(x4556_x5029_x5040_v)
      val x5032_x5032 =  VectorFIFO(size=1).wtPort(x4506_x5032_x5040_v)
      val x5031_x5031 =  VectorFIFO(size=1).wtPort(x4507_x5031_x5040_v)
      val x5030_x5030 =  VectorFIFO(size=1).wtPort(x4549_x5030_x5040_v)
      val ctr71 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5026 = CounterChain(name = "x5026", ctr71)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5030_x5030), Const(1)), op=FixEql, results=List(x5036))
      Stage(operands=List(x5036, CU.load(x5031_x5031), CU.load(x5032_x5032)), op=Mux, results=List(x5037))
      Stage(operands=List(CU.load(x5029_x5029), x5037), op=FixSub, results=List(CU.vecOut(x5023_x5039_v)))
    }
    val x5052_0 = Pipeline(name="x5052_0",parent=x5066) { implicit CU => 
      val x5047_x5047 =  VectorFIFO(size=1).wtPort(x5023_x5047_x5052_v)
      val x5048_x5048 =  VectorFIFO(size=1).wtPort(x5023_x5048_x5052_v)
      val ctr72 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr73 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5043 = CounterChain(name = "x5043", ctr72, ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5047_x5047), CU.load(x5048_x5048)), op=FixMul, results=List(CU.vecOut(x5024_x5051_v)))
    }
    val x5064_0 = Pipeline(name="x5064_0",parent=x5066) { implicit CU => 
      val x5059_x5059 =  VectorFIFO(size=1).wtPort(x4921_x5059_x5064_v)
      val x5058_x5058 =  VectorFIFO(size=1).wtPort(x5024_x5058_x5064_v)
      val ctr74 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5055 = CounterChain(name = "x5055", ctr74, ctr75)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5058_x5058), CU.load(x5059_x5059)), op=FixAdd, results=List(CU.vecOut(x4921_x5063_v)))
    }
    val x5113 = MetaPipeline(name="x5113",parent=x5301) { implicit CU => 
      val x4564_x5067 =  ScalarBuffer().wtPort(x4564_x4765_s)
      val ctr76 = Counter(min=Const(0), max=x4564_x5067.load, step=Const(1), par=1) // Counter
      val x5069 = CounterChain(name = "x5069", ctr76)
    }
    val x5070_dsp0 = MemoryPipeline(name="x5070_dsp0",parent="x5113") { implicit CU => 
      val x5086_x5086 =  VectorFIFO(size=1).wtPort(x5070_x5086_v)
      val x5073 = CounterChain.copy("x5087_0", "x5073")
      val x5090 = CounterChain.copy("x5099_0", "x5090")
      val x5070_x5095 =  SRAM(size=96,banking = Strided(1)).wtPort(x5086_x5086.readPort).rdPort(x5070_x5095_x5099_v).rdAddr(x5090(1)).wtAddr(x5073(0))
      var stage: List[Stage] = Nil
    }
    val x5070_dsp1 = MemoryPipeline(name="x5070_dsp1",parent="x5113") { implicit CU => 
      val x5086_x5086 =  VectorFIFO(size=1).wtPort(x5070_x5086_v)
      val x5073 = CounterChain.copy("x5087_0", "x5073")
      val x5090 = CounterChain.copy("x5099_0", "x5090")
      val x5070_x5094 =  SRAM(size=96,banking = Strided(1)).wtPort(x5086_x5086.readPort).rdPort(x5070_x5094_x5099_v).rdAddr(x5090(0)).wtAddr(x5073(0))
      var stage: List[Stage] = Nil
    }
    val x5071_dsp0 = MemoryPipeline(name="x5071_dsp0",parent="x5113") { implicit CU => 
      val b5495 = CU.temp
      val b5497 = CU.temp
      val x5098_x5098 =  VectorFIFO(size=1).wtPort(x5071_x5098_v)
      val x5090 = CounterChain.copy("x5099_0", "x5090")
      val x5102 = CounterChain.copy("x5111_0", "x5102")
      val x5071_x5105 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5098_x5098.readPort).rdPort(x5071_x5105_x5111_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5090(0)), Const(96)), op=FixMul, results=List(b5495))
      WAStage(operands=List(b5495, CU.ctr(x5090(1))), op=FixAdd, results=List(x5071_x5105.writeAddr))
      RAStage(operands=List(CU.ctr(x5102(0)), Const(96)), op=FixMul, results=List(b5497))
      RAStage(operands=List(b5497, CU.ctr(x5102(1))), op=FixAdd, results=List(x5071_x5105.readAddr))
    }
    val x5087_0 = Pipeline(name="x5087_0",parent=x5113) { implicit CU => 
      val x5084 = CU.temp
      val x5083 = CU.temp
      val x5077_x5077 =  VectorFIFO(size=1).wtPort(x4550_x5077_x5087_v)
      val x5076_x5076 =  VectorFIFO(size=1).wtPort(x4557_x5076_x5087_v)
      val x5079_x5079 =  VectorFIFO(size=1).wtPort(x4506_x5079_x5087_v)
      val x5078_x5078 =  VectorFIFO(size=1).wtPort(x4507_x5078_x5087_v)
      val ctr77 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5073 = CounterChain(name = "x5073", ctr77)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5077_x5077), Const(1)), op=FixEql, results=List(x5083))
      Stage(operands=List(x5083, CU.load(x5078_x5078), CU.load(x5079_x5079)), op=Mux, results=List(x5084))
      Stage(operands=List(CU.load(x5076_x5076), x5084), op=FixSub, results=List(CU.vecOut(x5070_x5086_v)))
    }
    val x5099_0 = Pipeline(name="x5099_0",parent=x5113) { implicit CU => 
      val x5095_x5095 =  VectorFIFO(size=1).wtPort(x5070_x5095_x5099_v)
      val x5094_x5094 =  VectorFIFO(size=1).wtPort(x5070_x5094_x5099_v)
      val ctr78 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr79 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5090 = CounterChain(name = "x5090", ctr78, ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5094_x5094), CU.load(x5095_x5095)), op=FixMul, results=List(CU.vecOut(x5071_x5098_v)))
    }
    val x5111_0 = Pipeline(name="x5111_0",parent=x5113) { implicit CU => 
      val x5106_x5106 =  VectorFIFO(size=1).wtPort(x4922_x5106_x5111_v)
      val x5105_x5105 =  VectorFIFO(size=1).wtPort(x5071_x5105_x5111_v)
      val ctr80 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr81 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5102 = CounterChain(name = "x5102", ctr80, ctr81)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5105_x5105), CU.load(x5106_x5106)), op=FixAdd, results=List(CU.vecOut(x4922_x5110_v)))
    }
    val x5160 = MetaPipeline(name="x5160",parent=x5301) { implicit CU => 
      val x4565_x5114 =  ScalarBuffer().wtPort(x4565_x4815_s)
      val ctr82 = Counter(min=Const(0), max=x4565_x5114.load, step=Const(1), par=1) // Counter
      val x5116 = CounterChain(name = "x5116", ctr82)
    }
    val x5117_dsp0 = MemoryPipeline(name="x5117_dsp0",parent="x5160") { implicit CU => 
      val x5133_x5133 =  VectorFIFO(size=1).wtPort(x5117_x5133_v)
      val x5120 = CounterChain.copy("x5134_0", "x5120")
      val x5137 = CounterChain.copy("x5146_0", "x5137")
      val x5117_x5142 =  SRAM(size=96,banking = Strided(1)).wtPort(x5133_x5133.readPort).rdPort(x5117_x5142_x5146_v).rdAddr(x5137(1)).wtAddr(x5120(0))
      var stage: List[Stage] = Nil
    }
    val x5117_dsp1 = MemoryPipeline(name="x5117_dsp1",parent="x5160") { implicit CU => 
      val x5133_x5133 =  VectorFIFO(size=1).wtPort(x5117_x5133_v)
      val x5120 = CounterChain.copy("x5134_0", "x5120")
      val x5137 = CounterChain.copy("x5146_0", "x5137")
      val x5117_x5141 =  SRAM(size=96,banking = Strided(1)).wtPort(x5133_x5133.readPort).rdPort(x5117_x5141_x5146_v).rdAddr(x5137(0)).wtAddr(x5120(0))
      var stage: List[Stage] = Nil
    }
    val x5118_dsp0 = MemoryPipeline(name="x5118_dsp0",parent="x5160") { implicit CU => 
      val b5505 = CU.temp
      val b5507 = CU.temp
      val x5145_x5145 =  VectorFIFO(size=1).wtPort(x5118_x5145_v)
      val x5137 = CounterChain.copy("x5146_0", "x5137")
      val x5149 = CounterChain.copy("x5158_0", "x5149")
      val x5118_x5152 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5145_x5145.readPort).rdPort(x5118_x5152_x5158_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5137(0)), Const(96)), op=FixMul, results=List(b5505))
      WAStage(operands=List(b5505, CU.ctr(x5137(1))), op=FixAdd, results=List(x5118_x5152.writeAddr))
      RAStage(operands=List(CU.ctr(x5149(0)), Const(96)), op=FixMul, results=List(b5507))
      RAStage(operands=List(b5507, CU.ctr(x5149(1))), op=FixAdd, results=List(x5118_x5152.readAddr))
    }
    val x5134_0 = Pipeline(name="x5134_0",parent=x5160) { implicit CU => 
      val x5131 = CU.temp
      val x5130 = CU.temp
      val x5125_x5125 =  VectorFIFO(size=1).wtPort(x4507_x5125_x5134_v)
      val x5124_x5124 =  VectorFIFO(size=1).wtPort(x4551_x5124_x5134_v)
      val x5123_x5123 =  VectorFIFO(size=1).wtPort(x4558_x5123_x5134_v)
      val x5126_x5126 =  VectorFIFO(size=1).wtPort(x4506_x5126_x5134_v)
      val ctr83 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5120 = CounterChain(name = "x5120", ctr83)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5124_x5124), Const(1)), op=FixEql, results=List(x5130))
      Stage(operands=List(x5130, CU.load(x5125_x5125), CU.load(x5126_x5126)), op=Mux, results=List(x5131))
      Stage(operands=List(CU.load(x5123_x5123), x5131), op=FixSub, results=List(CU.vecOut(x5117_x5133_v)))
    }
    val x5146_0 = Pipeline(name="x5146_0",parent=x5160) { implicit CU => 
      val x5142_x5142 =  VectorFIFO(size=1).wtPort(x5117_x5142_x5146_v)
      val x5141_x5141 =  VectorFIFO(size=1).wtPort(x5117_x5141_x5146_v)
      val ctr84 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr85 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5137 = CounterChain(name = "x5137", ctr84, ctr85)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5141_x5141), CU.load(x5142_x5142)), op=FixMul, results=List(CU.vecOut(x5118_x5145_v)))
    }
    val x5158_0 = Pipeline(name="x5158_0",parent=x5160) { implicit CU => 
      val x5152_x5152 =  VectorFIFO(size=1).wtPort(x5118_x5152_x5158_v)
      val x5153_x5153 =  VectorFIFO(size=1).wtPort(x4923_x5153_x5158_v)
      val ctr86 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr87 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5149 = CounterChain(name = "x5149", ctr86, ctr87)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5152_x5152), CU.load(x5153_x5153)), op=FixAdd, results=List(CU.vecOut(x4923_x5157_v)))
    }
    val x5207 = MetaPipeline(name="x5207",parent=x5301) { implicit CU => 
      val x4566_x5161 =  ScalarBuffer().wtPort(x4566_x4865_s)
      val ctr88 = Counter(min=Const(0), max=x4566_x5161.load, step=Const(1), par=1) // Counter
      val x5163 = CounterChain(name = "x5163", ctr88)
    }
    val x5164_dsp0 = MemoryPipeline(name="x5164_dsp0",parent="x5207") { implicit CU => 
      val x5180_x5180 =  VectorFIFO(size=1).wtPort(x5164_x5180_v)
      val x5167 = CounterChain.copy("x5181_0", "x5167")
      val x5184 = CounterChain.copy("x5193_0", "x5184")
      val x5164_x5189 =  SRAM(size=96,banking = Strided(1)).wtPort(x5180_x5180.readPort).rdPort(x5164_x5189_x5193_v).rdAddr(x5184(1)).wtAddr(x5167(0))
      var stage: List[Stage] = Nil
    }
    val x5164_dsp1 = MemoryPipeline(name="x5164_dsp1",parent="x5207") { implicit CU => 
      val x5180_x5180 =  VectorFIFO(size=1).wtPort(x5164_x5180_v)
      val x5167 = CounterChain.copy("x5181_0", "x5167")
      val x5184 = CounterChain.copy("x5193_0", "x5184")
      val x5164_x5188 =  SRAM(size=96,banking = Strided(1)).wtPort(x5180_x5180.readPort).rdPort(x5164_x5188_x5193_v).rdAddr(x5184(0)).wtAddr(x5167(0))
      var stage: List[Stage] = Nil
    }
    val x5165_dsp0 = MemoryPipeline(name="x5165_dsp0",parent="x5207") { implicit CU => 
      val b5515 = CU.temp
      val b5517 = CU.temp
      val x5192_x5192 =  VectorFIFO(size=1).wtPort(x5165_x5192_v)
      val x5184 = CounterChain.copy("x5193_0", "x5184")
      val x5196 = CounterChain.copy("x5205_0", "x5196")
      val x5165_x5199 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5192_x5192.readPort).rdPort(x5165_x5199_x5205_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5184(0)), Const(96)), op=FixMul, results=List(b5515))
      WAStage(operands=List(b5515, CU.ctr(x5184(1))), op=FixAdd, results=List(x5165_x5199.writeAddr))
      RAStage(operands=List(CU.ctr(x5196(0)), Const(96)), op=FixMul, results=List(b5517))
      RAStage(operands=List(b5517, CU.ctr(x5196(1))), op=FixAdd, results=List(x5165_x5199.readAddr))
    }
    val x5181_0 = Pipeline(name="x5181_0",parent=x5207) { implicit CU => 
      val x5178 = CU.temp
      val x5177 = CU.temp
      val x5173_x5173 =  VectorFIFO(size=1).wtPort(x4506_x5173_x5181_v)
      val x5170_x5170 =  VectorFIFO(size=1).wtPort(x4559_x5170_x5181_v)
      val x5172_x5172 =  VectorFIFO(size=1).wtPort(x4507_x5172_x5181_v)
      val x5171_x5171 =  VectorFIFO(size=1).wtPort(x4552_x5171_x5181_v)
      val ctr89 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5167 = CounterChain(name = "x5167", ctr89)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5171_x5171), Const(1)), op=FixEql, results=List(x5177))
      Stage(operands=List(x5177, CU.load(x5172_x5172), CU.load(x5173_x5173)), op=Mux, results=List(x5178))
      Stage(operands=List(CU.load(x5170_x5170), x5178), op=FixSub, results=List(CU.vecOut(x5164_x5180_v)))
    }
    val x5193_0 = Pipeline(name="x5193_0",parent=x5207) { implicit CU => 
      val x5188_x5188 =  VectorFIFO(size=1).wtPort(x5164_x5188_x5193_v)
      val x5189_x5189 =  VectorFIFO(size=1).wtPort(x5164_x5189_x5193_v)
      val ctr90 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr91 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5184 = CounterChain(name = "x5184", ctr90, ctr91)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5188_x5188), CU.load(x5189_x5189)), op=FixMul, results=List(CU.vecOut(x5165_x5192_v)))
    }
    val x5205_0 = Pipeline(name="x5205_0",parent=x5207) { implicit CU => 
      val x5200_x5200 =  VectorFIFO(size=1).wtPort(x4924_x5200_x5205_v)
      val x5199_x5199 =  VectorFIFO(size=1).wtPort(x5165_x5199_x5205_v)
      val ctr92 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr93 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5196 = CounterChain(name = "x5196", ctr92, ctr93)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5199_x5199), CU.load(x5200_x5200)), op=FixAdd, results=List(CU.vecOut(x4924_x5204_v)))
    }
    val x5254 = MetaPipeline(name="x5254",parent=x5301) { implicit CU => 
      val x4567_x5208 =  ScalarBuffer().wtPort(x4567_x4915_s)
      val ctr94 = Counter(min=Const(0), max=x4567_x5208.load, step=Const(1), par=1) // Counter
      val x5210 = CounterChain(name = "x5210", ctr94)
    }
    val x5211_dsp0 = MemoryPipeline(name="x5211_dsp0",parent="x5254") { implicit CU => 
      val x5227_x5227 =  VectorFIFO(size=1).wtPort(x5211_x5227_v)
      val x5214 = CounterChain.copy("x5228_0", "x5214")
      val x5231 = CounterChain.copy("x5240_0", "x5231")
      val x5211_x5236 =  SRAM(size=96,banking = Strided(1)).wtPort(x5227_x5227.readPort).rdPort(x5211_x5236_x5240_v).rdAddr(x5231(1)).wtAddr(x5214(0))
      var stage: List[Stage] = Nil
    }
    val x5211_dsp1 = MemoryPipeline(name="x5211_dsp1",parent="x5254") { implicit CU => 
      val x5227_x5227 =  VectorFIFO(size=1).wtPort(x5211_x5227_v)
      val x5214 = CounterChain.copy("x5228_0", "x5214")
      val x5231 = CounterChain.copy("x5240_0", "x5231")
      val x5211_x5235 =  SRAM(size=96,banking = Strided(1)).wtPort(x5227_x5227.readPort).rdPort(x5211_x5235_x5240_v).rdAddr(x5231(0)).wtAddr(x5214(0))
      var stage: List[Stage] = Nil
    }
    val x5212_dsp0 = MemoryPipeline(name="x5212_dsp0",parent="x5254") { implicit CU => 
      val b5527 = CU.temp
      val b5525 = CU.temp
      val x5239_x5239 =  VectorFIFO(size=1).wtPort(x5212_x5239_v)
      val x5231 = CounterChain.copy("x5240_0", "x5231")
      val x5243 = CounterChain.copy("x5252_0", "x5243")
      val x5212_x5246 =  SRAM(size=9216,banking = NoBanking()).wtPort(x5239_x5239.readPort).rdPort(x5212_x5246_x5252_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5231(0)), Const(96)), op=FixMul, results=List(b5525))
      WAStage(operands=List(b5525, CU.ctr(x5231(1))), op=FixAdd, results=List(x5212_x5246.writeAddr))
      RAStage(operands=List(CU.ctr(x5243(0)), Const(96)), op=FixMul, results=List(b5527))
      RAStage(operands=List(b5527, CU.ctr(x5243(1))), op=FixAdd, results=List(x5212_x5246.readAddr))
    }
    val x5228_0 = Pipeline(name="x5228_0",parent=x5254) { implicit CU => 
      val x5225 = CU.temp
      val x5224 = CU.temp
      val x5218_x5218 =  VectorFIFO(size=1).wtPort(x4553_x5218_x5228_v)
      val x5220_x5220 =  VectorFIFO(size=1).wtPort(x4506_x5220_x5228_v)
      val x5217_x5217 =  VectorFIFO(size=1).wtPort(x4560_x5217_x5228_v)
      val x5219_x5219 =  VectorFIFO(size=1).wtPort(x4507_x5219_x5228_v)
      val ctr95 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5214 = CounterChain(name = "x5214", ctr95)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5218_x5218), Const(1)), op=FixEql, results=List(x5224))
      Stage(operands=List(x5224, CU.load(x5219_x5219), CU.load(x5220_x5220)), op=Mux, results=List(x5225))
      Stage(operands=List(CU.load(x5217_x5217), x5225), op=FixSub, results=List(CU.vecOut(x5211_x5227_v)))
    }
    val x5240_0 = Pipeline(name="x5240_0",parent=x5254) { implicit CU => 
      val x5236_x5236 =  VectorFIFO(size=1).wtPort(x5211_x5236_x5240_v)
      val x5235_x5235 =  VectorFIFO(size=1).wtPort(x5211_x5235_x5240_v)
      val ctr96 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr97 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5231 = CounterChain(name = "x5231", ctr96, ctr97)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5235_x5235), CU.load(x5236_x5236)), op=FixMul, results=List(CU.vecOut(x5212_x5239_v)))
    }
    val x5252_0 = Pipeline(name="x5252_0",parent=x5254) { implicit CU => 
      val x5247_x5247 =  VectorFIFO(size=1).wtPort(x4925_x5247_x5252_v)
      val x5246_x5246 =  VectorFIFO(size=1).wtPort(x5212_x5246_x5252_v)
      val ctr98 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr99 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5243 = CounterChain(name = "x5243", ctr98, ctr99)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5246_x5246), CU.load(x5247_x5247)), op=FixAdd, results=List(CU.vecOut(x4925_x5251_v)))
    }
    val x5299 = StreamController(name="x5299",parent=x5301) { implicit CU => 
      val ctr100 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr101 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5258 = CounterChain(name = "x5258", ctr100, ctr101)
    }
    val x5299_0 = Pipeline(name="x5299_0",parent=x5299) { implicit CU => 
      val x5279 = CU.temp
      val x5272 = CU.temp
      val x5260_x5260 =  VectorFIFO(size=1).wtPort(x4919_x5260_x5299_v)
      val x5263_x5263 =  VectorFIFO(size=1).wtPort(x4922_x5263_x5299_v)
      val x5262_x5262 =  VectorFIFO(size=1).wtPort(x4921_x5262_x5299_v)
      val x5261_x5261 =  VectorFIFO(size=1).wtPort(x4920_x5261_x5299_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5260_x5260), CU.load(x5261_x5261)), op=FixAdd, results=List(x5272))
      Stage(operands=List(CU.load(x5262_x5262), CU.load(x5263_x5263)), op=FixAdd, results=List(x5279))
      Stage(operands=List(x5272, x5279), op=FixAdd, results=List(CU.scalarOut(bus_5380_s)))
    }
    val x5299_1 = Pipeline(name="x5299_1",parent=x5299) { implicit CU => 
      val x5295 = CU.temp
      val x5293 = CU.temp
      val x5290 = CU.temp
      val x5281 =  ScalarFIFO(size=1).wtPort(bus_5380_s)
      val x5265_x5265 =  VectorFIFO(size=1).wtPort(x4924_x5265_x5299_v)
      val x5266_x5266 =  VectorFIFO(size=1).wtPort(x4925_x5266_x5299_v)
      val x5267_x5267 =  VectorFIFO(size=1).wtPort(x4543_x5267_x5299_v)
      val x5264_x5264 =  VectorFIFO(size=1).wtPort(x4923_x5264_x5299_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5264_x5264), CU.load(x5265_x5265)), op=FixAdd, results=List(x5290))
      Stage(operands=List(x5290, CU.load(x5266_x5266)), op=FixAdd, results=List(x5293))
      Stage(operands=List(CU.load(x5281), x5293), op=FixAdd, results=List(x5295))
      Stage(operands=List(x5295, CU.load(x5267_x5267)), op=FixAdd, results=List(CU.vecOut(x4543_x5298_v)))
    }
    val x5332 = StreamController(name="x5332",parent=x5333) { implicit CU => 
      val ctr102 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5303 = CounterChain(name = "x5303", ctr102)
    }
    val x5324 = Sequential(name="x5324",parent=x5332) { implicit CU => 
      val ctr103 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5324_unit = CounterChain(name = "x5324_unit", ctr103)
    }
    val x5315_0 = Pipeline(name="x5315_0",parent=x5324) { implicit CU => 
      val x5309 = CU.temp
      val x5308 = CU.temp
      val x5307 =  ScalarBuffer().wtPort(x5307_argin)
      val x5303 = CounterChain.copy("x5332", "x5303")
      val ctr104 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5315_unit = CounterChain(name = "x5315_unit", ctr104)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5303(0)), Const(96)), op=FixMul, results=List(x5308))
      Stage(operands=List(x5308, Const(4)), op=FixMul, results=List(x5309))
      Stage(operands=List(x5309, CU.load(x5307)), op=FixAdd, results=List(CU.scalarOut(x5304_b5551_x5314_b5553_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5304_b5552_x5314_b5554_s)))
    }
    val x5323 = Pipeline(name="x5323",parent=x5324) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5317 = CounterChain(name = "x5317", ctr105)
      var stage: List[Stage] = Nil
    }
    val x5325 = MemoryController(name="x5325",parent=x5332,offchip=x4501_oc, mctpe=TileStore) { implicit CU => 
      val x5305_x5325 =  VectorFIFO(name="data",size=1).wtPort(x4543_x5319_x5323_v)
      val x5304_b5552_x5325 =  ScalarFIFO(name="size",size=1).wtPort(x5304_b5552_x5314_b5554_s)
      val x5304_b5551_x5325 =  ScalarFIFO(name="offset",size=1).wtPort(x5304_b5551_x5314_b5553_s)
    }
    
  }
}
