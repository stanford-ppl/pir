import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object MatMult_inner extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x6541_x7384_x7390_v = Vector("x6541_x7384_x7390")
    val x7643_b8390_x7658_b8392_s = Scalar("x7643_b8390_x7658_b8392")
    val x6534_x6642_x6648_v = Vector("x6534_x6642_x6648")
    val x6901_b8249_x6916_b8251_s = Scalar("x6901_b8249_x6916_b8251")
    val x7078_x7154_x7167_v = Vector("x7078_x7154_x7167")
    val x7221_argin = ArgIn("x7221")
    val x6544_x7707_v = Vector("x6544_x7707")
    val x7994_b8445_x8009_b8447_s = Scalar("x7994_b8445_x8009_b8447")
    val x7464_x7483_s = Scalar("x7464_x7483")
    val x7676_x7695_s = Scalar("x7676_x7695")
    val x6541_x8016_x8020_v = Vector("x6541_x8016_x8020")
    val x7084_argin = ArgIn("x7084")
    val x6535_x7776_x7780_v = Vector("x6535_x7776_x7780")
    val x6553_x6569_data_v = Vector("x6553_x6569_data")
    val x6538_x7071_v = Vector("x6538_x7071")
    val x6542_x7490_x7496_v = Vector("x6542_x7490_x7496")
    val x8037_argin = ArgIn("x8037")
    val x6764_b8223_x6779_b8225_s = Scalar("x6764_b8223_x6779_b8225")
    val x6540_x7278_x7284_v = Vector("x6540_x7278_x7284")
    val x7082_b8283_x7097_b8285_s = Scalar("x7082_b8283_x7097_b8285")
    val x7506_b8363_x7521_b8365_s = Scalar("x7506_b8363_x7521_b8365")
    val x7397_x7473_x7485_v = Vector("x7397_x7473_x7485")
    val x8077_argin = ArgIn("x8077")
    val x6691_argin = ArgIn("x6691")
    val x7837_argin = ArgIn("x7837")
    val x7717_argin = ArgIn("x7717")
    val x7954_b8439_x7969_b8441_s = Scalar("x7954_b8439_x7969_b8441")
    val x8117_argin = ArgIn("x8117")
    val x7007_b8270_x7022_b8272_s = Scalar("x7007_b8270_x7022_b8272")
    val x6976_b8263_x6991_b8265_s = Scalar("x6976_b8263_x6991_b8265")
    val x6584_x6600_data_v = Vector("x6584_x6600_data")
    val x6543_x7601_v = Vector("x6543_x7601")
    val x6552_b8183_x6567_b8185_s = Scalar("x6552_b8183_x6567_b8185")
    val x6977_x6993_data_v = Vector("x6977_x6993_data")
    val x7614_argin = ArgIn("x7614")
    val x7994_b8446_x8009_b8448_s = Scalar("x7994_b8446_x8009_b8448")
    val x7188_b8304_x7203_b8306_s = Scalar("x7188_b8304_x7203_b8306")
    val x7040_x7059_s = Scalar("x7040_x7059")
    val x7219_b8309_x7234_b8311_s = Scalar("x7219_b8309_x7234_b8311")
    val x6796_x6812_data_v = Vector("x6796_x6812_data")
    val x7296_argin = ArgIn("x7296")
    val x7954_b8440_x7969_b8442_s = Scalar("x7954_b8440_x7969_b8442")
    val x7184_x7260_x7273_v = Vector("x7184_x7260_x7273")
    val x7431_b8349_x7446_b8351_s = Scalar("x7431_b8349_x7446_b8351")
    val x6536_x6854_x6860_v = Vector("x6536_x6854_x6860")
    val x6542_x7495_v = Vector("x6542_x7495")
    val x7957_argin = ArgIn("x7957")
    val x6616_x6635_s = Scalar("x6616_x6635")
    val x6520_oc = OffChip("x6520")
    val x7644_x7660_data_v = Vector("x7644_x7660_data")
    val x7874_b8428_x7889_b8430_s = Scalar("x7874_b8428_x7889_b8430")
    val x8034_b8452_x8049_b8454_s = Scalar("x8034_b8452_x8049_b8454")
    val x7538_x7554_data_v = Vector("x7538_x7554_data")
    val x6535_x6753_v = Vector("x6535_x6753")
    val x7326_x7342_data_v = Vector("x7326_x7342_data")
    val x7146_x7165_s = Scalar("x7146_x7165")
    val x7325_b8330_x7340_b8332_s = Scalar("x7325_b8330_x7340_b8332")
    val x6934_x6953_s = Scalar("x6934_x6953")
    val x7079_x7155_x7167_v = Vector("x7079_x7155_x7167")
    val x7188_b8303_x7203_b8305_s = Scalar("x7188_b8303_x7203_b8305")
    val x6795_b8229_x6810_b8231_s = Scalar("x6795_b8229_x6810_b8231")
    val x6972_x7048_x7061_v = Vector("x6972_x7048_x7061")
    val x6537_x6965_v = Vector("x6537_x6965")
    val x7082_b8284_x7097_b8286_s = Scalar("x7082_b8284_x7097_b8286")
    val x7294_b8324_x7309_b8326_s = Scalar("x7294_b8324_x7309_b8326")
    val x7189_x7205_data_v = Vector("x7189_x7205_data")
    val x7757_argin = ArgIn("x7757")
    val x7009_argin = ArgIn("x7009")
    val x7433_argin = ArgIn("x7433")
    val x7714_b8404_x7729_b8406_s = Scalar("x7714_b8404_x7729_b8406")
    val x6795_b8230_x6810_b8232_s = Scalar("x6795_b8230_x6810_b8232")
    val x6689_b8209_x6704_b8211_s = Scalar("x6689_b8209_x6704_b8211")
    val x6765_x6781_data_v = Vector("x6765_x6781_data")
    val x6536_x6859_v = Vector("x6536_x6859")
    val x6514_argin = ArgIn("x6514")
    val x6540_x7976_x7980_v = Vector("x6540_x7976_x7980")
    val x7914_b8433_x7929_b8435_s = Scalar("x7914_b8433_x7929_b8435")
    val x7396_x7472_x7485_v = Vector("x7396_x7472_x7485")
    val x7754_b8410_x7769_b8412_s = Scalar("x7754_b8410_x7769_b8412")
    val x7113_b8290_x7128_b8292_s = Scalar("x7113_b8290_x7128_b8292")
    val x6544_x7702_x7708_v = Vector("x6544_x7702_x7708")
    val x7917_argin = ArgIn("x7917")
    val x7358_x7377_s = Scalar("x7358_x7377")
    val x6539_x7177_v = Vector("x6539_x7177")
    val x6512_argin = ArgIn("x6512")
    val x7401_x7417_data_v = Vector("x7401_x7417_data")
    val x6654_x6730_x6743_v = Vector("x6654_x6730_x6743")
    val x6549_x6625_x6637_v = Vector("x6549_x6625_x6637")
    val x6544_x8136_x8140_v = Vector("x6544_x8136_x8140")
    val x6760_x6836_x6849_v = Vector("x6760_x6836_x6849")
    val x6543_x7596_x7602_v = Vector("x6543_x7596_x7602")
    val x7794_b8415_x7809_b8417_s = Scalar("x7794_b8415_x7809_b8417")
    val x7507_x7523_data_v = Vector("x7507_x7523_data")
    val x7613_x7629_data_v = Vector("x7613_x7629_data")
    val x6660_argin = ArgIn("x6660")
    val x7008_x7024_data_v = Vector("x7008_x7024_data")
    val x8074_b8457_x8089_b8459_s = Scalar("x8074_b8457_x8089_b8459")
    val x6902_x6918_data_v = Vector("x6902_x6918_data")
    val x7612_b8383_x7627_b8385_s = Scalar("x7612_b8383_x7627_b8385")
    val x7874_b8427_x7889_b8429_s = Scalar("x7874_b8427_x7889_b8429")
    val x6655_x6731_x6743_v = Vector("x6655_x6731_x6743")
    val x7327_argin = ArgIn("x7327")
    val x7402_argin = ArgIn("x7402")
    val x7754_b8409_x7769_b8411_s = Scalar("x7754_b8409_x7769_b8411")
    val x6539_x7936_x7940_v = Vector("x6539_x7936_x7940")
    val x6513_argin = ArgIn("x6513")
    val x6526_oc = OffChip("x6526")
    val x8114_b8464_x8129_b8466_s = Scalar("x8114_b8464_x8129_b8466")
    val x7114_x7130_data_v = Vector("x7114_x7130_data")
    val x7570_x7589_s = Scalar("x7570_x7589")
    val x6658_b8203_x6673_b8205_s = Scalar("x6658_b8203_x6673_b8205")
    val x7290_x7366_x7379_v = Vector("x7290_x7366_x7379")
    val x7219_b8310_x7234_b8312_s = Scalar("x7219_b8310_x7234_b8312")
    val x7431_b8350_x7446_b8352_s = Scalar("x7431_b8350_x7446_b8352")
    val x6540_x7283_v = Vector("x6540_x7283")
    val x7503_x7579_x7591_v = Vector("x7503_x7579_x7591")
    val x6552_b8184_x6567_b8186_s = Scalar("x6552_b8184_x6567_b8186")
    val x6973_x7049_x7061_v = Vector("x6973_x7049_x7061")
    val x7325_b8329_x7340_b8331_s = Scalar("x7325_b8329_x7340_b8331")
    val x7612_b8384_x7627_b8386_s = Scalar("x7612_b8384_x7627_b8386")
    val x7007_b8269_x7022_b8271_s = Scalar("x7007_b8269_x7022_b8271")
    val x6761_x6837_x6849_v = Vector("x6761_x6837_x6849")
    val x6548_x6624_x6637_v = Vector("x6548_x6624_x6637")
    val x7643_b8389_x7658_b8391_s = Scalar("x7643_b8389_x7658_b8391")
    val x6537_x6960_x6966_v = Vector("x6537_x6960_x6966")
    val x7502_x7578_x7591_v = Vector("x7502_x7578_x7591")
    val x7539_argin = ArgIn("x7539")
    val x6541_x7389_v = Vector("x6541_x7389")
    val x6689_b8210_x6704_b8212_s = Scalar("x6689_b8210_x6704_b8212")
    val x6764_b8224_x6779_b8226_s = Scalar("x6764_b8224_x6779_b8226")
    val x6870_b8244_x6885_b8246_s = Scalar("x6870_b8244_x6885_b8246")
    val x6903_argin = ArgIn("x6903")
    val x6976_b8264_x6991_b8266_s = Scalar("x6976_b8264_x6991_b8266")
    val x6797_argin = ArgIn("x6797")
    val x6538_x7066_x7072_v = Vector("x6538_x7066_x7072")
    val x6536_x7816_x7820_v = Vector("x6536_x7816_x7820")
    val x6866_x6942_x6955_v = Vector("x6866_x6942_x6955")
    val x7113_b8289_x7128_b8291_s = Scalar("x7113_b8289_x7128_b8291")
    val x7609_x7685_x7697_v = Vector("x7609_x7685_x7697")
    val x7185_x7261_x7273_v = Vector("x7185_x7261_x7273")
    val x6523_oc = OffChip("x6523")
    val x6658_b8204_x6673_b8206_s = Scalar("x6658_b8204_x6673_b8206")
    val x7794_b8416_x7809_b8418_s = Scalar("x7794_b8416_x7809_b8418")
    val x7537_b8369_x7552_b8371_s = Scalar("x7537_b8369_x7552_b8371")
    val x7252_x7271_s = Scalar("x7252_x7271")
    val x7834_b8422_x7849_b8424_s = Scalar("x7834_b8422_x7849_b8424")
    val x6537_x7856_x7860_v = Vector("x6537_x7856_x7860")
    val x8114_b8463_x8129_b8465_s = Scalar("x8114_b8463_x8129_b8465")
    val x7295_x7311_data_v = Vector("x7295_x7311_data")
    val x7997_argin = ArgIn("x7997")
    val x6539_x7172_x7178_v = Vector("x6539_x7172_x7178")
    val x7115_argin = ArgIn("x7115")
    val x7834_b8421_x7849_b8423_s = Scalar("x7834_b8421_x7849_b8423")
    val x8034_b8451_x8049_b8453_s = Scalar("x8034_b8451_x8049_b8453")
    val x8074_b8458_x8089_b8460_s = Scalar("x8074_b8458_x8089_b8460")
    val x7645_argin = ArgIn("x7645")
    val x6766_argin = ArgIn("x6766")
    val x6659_x6675_data_v = Vector("x6659_x6675_data")
    val x7714_b8403_x7729_b8405_s = Scalar("x7714_b8403_x7729_b8405")
    val x7877_argin = ArgIn("x7877")
    val x7291_x7367_x7379_v = Vector("x7291_x7367_x7379")
    val x6543_x8096_x8100_v = Vector("x6543_x8096_x8100")
    val x6870_b8243_x6885_b8245_s = Scalar("x6870_b8243_x6885_b8245")
    val x6535_x6748_x6754_v = Vector("x6535_x6748_x6754")
    val x6583_b8189_x6598_b8191_s = Scalar("x6583_b8189_x6598_b8191")
    val x7294_b8323_x7309_b8325_s = Scalar("x7294_b8323_x7309_b8325")
    val x6534_x7736_x7740_v = Vector("x6534_x7736_x7740")
    val x6867_x6943_x6955_v = Vector("x6867_x6943_x6955")
    val x7400_b8343_x7415_b8345_s = Scalar("x7400_b8343_x7415_b8345")
    val x7400_b8344_x7415_b8346_s = Scalar("x7400_b8344_x7415_b8346")
    val x6828_x6847_s = Scalar("x6828_x6847")
    val x6542_x8056_x8060_v = Vector("x6542_x8056_x8060")
    val x7190_argin = ArgIn("x7190")
    val x6690_x6706_data_v = Vector("x6690_x6706_data")
    val x7797_argin = ArgIn("x7797")
    val x6871_x6887_data_v = Vector("x6871_x6887_data")
    val x6872_argin = ArgIn("x6872")
    val x7537_b8370_x7552_b8372_s = Scalar("x7537_b8370_x7552_b8372")
    val x6538_x7896_x7900_v = Vector("x6538_x7896_x7900")
    val x7083_x7099_data_v = Vector("x7083_x7099_data")
    val x7914_b8434_x7929_b8436_s = Scalar("x7914_b8434_x7929_b8436")
    val x6554_argin = ArgIn("x6554")
    val x6583_b8190_x6598_b8192_s = Scalar("x6583_b8190_x6598_b8192")
    val x7608_x7684_x7697_v = Vector("x7608_x7684_x7697")
    val x7506_b8364_x7521_b8366_s = Scalar("x7506_b8364_x7521_b8366")
    val x6978_argin = ArgIn("x6978")
    val x6722_x6741_s = Scalar("x6722_x6741")
    val x7508_argin = ArgIn("x7508")
    val x6901_b8250_x6916_b8252_s = Scalar("x6901_b8250_x6916_b8252")
    val x6585_argin = ArgIn("x6585")
    val x7220_x7236_data_v = Vector("x7220_x7236_data")
    val x7432_x7448_data_v = Vector("x7432_x7448_data")
    val x6534_x6647_v = Vector("x6534_x6647")
    val x8154 = Sequential(name="x8154",parent=top) { implicit CU => 
    }
    val x8153 = MetaPipeline(name="x8153",parent=x8154) { implicit CU => 
      val x6512_x6531 =  ScalarBuffer().wtPort(x6512_argin)
      val x6513_x6529 =  ScalarBuffer().wtPort(x6513_argin)
      val ctr1 = Counter(min=Const(0), max=x6512_x6531.load, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x6513_x6529.load, step=Const(48), par=11) // Counter
      val x6533 = CounterChain(name = "x6533", ctr1, ctr2).iter(1)
    }
    val x6534_dsp0 = MemoryPipeline(name="x6534_dsp0",parent="x8153") { implicit CU => 
      val b8201 = CU.temp
      val b8407 = CU.temp
      val x6647_x6647 =  VectorFIFO(size=1).wtPort(x6534_x6647_v)
      val x6615 = CounterChain.copy("x6649", "x6615")
      val x7713 = CounterChain.copy("x7751", "x7713")
      val x7732 = CounterChain.copy("x7740", "x7732")
      val x6534_x7736 =  SRAM(size=768,banking = NoBanking()).wtPort(x6647_x6647.readPort).rdPort(x6534_x7736_x7740_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6615(0)), Const(48)), op=FixMul, results=List(b8201))
      WAStage(operands=List(b8201, CU.ctr(x6615(1))), op=FixAdd, results=List(x6534_x7736.writeAddr))
      RAStage(operands=List(CU.ctr(x7713(0)), Const(48)), op=FixMul, results=List(b8407))
      RAStage(operands=List(b8407, CU.ctr(x7732(0))), op=FixAdd, results=List(x6534_x7736.readAddr))
    }
    val x6534_dsp1 = MemoryPipeline(name="x6534_dsp1",parent="x8153") { implicit CU => 
      val b8201 = CU.temp
      val b8199 = CU.temp
      val x6647_x6647 =  VectorFIFO(size=1).wtPort(x6534_x6647_v)
      val x6615 = CounterChain.copy("x6649", "x6615")
      val x6534_x6642 =  SRAM(size=768,banking = NoBanking()).wtPort(x6647_x6647.readPort).rdPort(x6534_x6642_x6648_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6615(0)), Const(48)), op=FixMul, results=List(b8201))
      WAStage(operands=List(b8201, CU.ctr(x6615(1))), op=FixAdd, results=List(x6534_x6642.writeAddr))
      RAStage(operands=List(CU.ctr(x6615(0)), Const(48)), op=FixMul, results=List(b8199))
      RAStage(operands=List(b8199, CU.ctr(x6615(1))), op=FixAdd, results=List(x6534_x6642.readAddr))
    }
    val x6535_dsp0 = MemoryPipeline(name="x6535_dsp0",parent="x8153") { implicit CU => 
      val b8221 = CU.temp
      val b8413 = CU.temp
      val x6753_x6753 =  VectorFIFO(size=1).wtPort(x6535_x6753_v)
      val x6721 = CounterChain.copy("x6755", "x6721")
      val x7753 = CounterChain.copy("x7791", "x7753")
      val x7772 = CounterChain.copy("x7780", "x7772")
      val x6535_x7776 =  SRAM(size=768,banking = NoBanking()).wtPort(x6753_x6753.readPort).rdPort(x6535_x7776_x7780_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6721(0)), Const(48)), op=FixMul, results=List(b8221))
      WAStage(operands=List(b8221, CU.ctr(x6721(1))), op=FixAdd, results=List(x6535_x7776.writeAddr))
      RAStage(operands=List(CU.ctr(x7753(0)), Const(48)), op=FixMul, results=List(b8413))
      RAStage(operands=List(b8413, CU.ctr(x7772(0))), op=FixAdd, results=List(x6535_x7776.readAddr))
    }
    val x6535_dsp1 = MemoryPipeline(name="x6535_dsp1",parent="x8153") { implicit CU => 
      val b8219 = CU.temp
      val b8221 = CU.temp
      val x6753_x6753 =  VectorFIFO(size=1).wtPort(x6535_x6753_v)
      val x6721 = CounterChain.copy("x6755", "x6721")
      val x6535_x6748 =  SRAM(size=768,banking = NoBanking()).wtPort(x6753_x6753.readPort).rdPort(x6535_x6748_x6754_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6721(0)), Const(48)), op=FixMul, results=List(b8221))
      WAStage(operands=List(b8221, CU.ctr(x6721(1))), op=FixAdd, results=List(x6535_x6748.writeAddr))
      RAStage(operands=List(CU.ctr(x6721(0)), Const(48)), op=FixMul, results=List(b8219))
      RAStage(operands=List(b8219, CU.ctr(x6721(1))), op=FixAdd, results=List(x6535_x6748.readAddr))
    }
    val x6536_dsp0 = MemoryPipeline(name="x6536_dsp0",parent="x8153") { implicit CU => 
      val b8419 = CU.temp
      val b8241 = CU.temp
      val x6859_x6859 =  VectorFIFO(size=1).wtPort(x6536_x6859_v)
      val x6827 = CounterChain.copy("x6861", "x6827")
      val x7793 = CounterChain.copy("x7831", "x7793")
      val x7812 = CounterChain.copy("x7820", "x7812")
      val x6536_x7816 =  SRAM(size=768,banking = NoBanking()).wtPort(x6859_x6859.readPort).rdPort(x6536_x7816_x7820_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6827(0)), Const(48)), op=FixMul, results=List(b8241))
      WAStage(operands=List(b8241, CU.ctr(x6827(1))), op=FixAdd, results=List(x6536_x7816.writeAddr))
      RAStage(operands=List(CU.ctr(x7793(0)), Const(48)), op=FixMul, results=List(b8419))
      RAStage(operands=List(b8419, CU.ctr(x7812(0))), op=FixAdd, results=List(x6536_x7816.readAddr))
    }
    val x6536_dsp1 = MemoryPipeline(name="x6536_dsp1",parent="x8153") { implicit CU => 
      val b8239 = CU.temp
      val b8241 = CU.temp
      val x6859_x6859 =  VectorFIFO(size=1).wtPort(x6536_x6859_v)
      val x6827 = CounterChain.copy("x6861", "x6827")
      val x6536_x6854 =  SRAM(size=768,banking = NoBanking()).wtPort(x6859_x6859.readPort).rdPort(x6536_x6854_x6860_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6827(0)), Const(48)), op=FixMul, results=List(b8241))
      WAStage(operands=List(b8241, CU.ctr(x6827(1))), op=FixAdd, results=List(x6536_x6854.writeAddr))
      RAStage(operands=List(CU.ctr(x6827(0)), Const(48)), op=FixMul, results=List(b8239))
      RAStage(operands=List(b8239, CU.ctr(x6827(1))), op=FixAdd, results=List(x6536_x6854.readAddr))
    }
    val x6537_dsp0 = MemoryPipeline(name="x6537_dsp0",parent="x8153") { implicit CU => 
      val b8261 = CU.temp
      val b8425 = CU.temp
      val x6965_x6965 =  VectorFIFO(size=1).wtPort(x6537_x6965_v)
      val x6933 = CounterChain.copy("x6967", "x6933")
      val x7833 = CounterChain.copy("x7871", "x7833")
      val x7852 = CounterChain.copy("x7860", "x7852")
      val x6537_x7856 =  SRAM(size=768,banking = NoBanking()).wtPort(x6965_x6965.readPort).rdPort(x6537_x7856_x7860_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6933(0)), Const(48)), op=FixMul, results=List(b8261))
      WAStage(operands=List(b8261, CU.ctr(x6933(1))), op=FixAdd, results=List(x6537_x7856.writeAddr))
      RAStage(operands=List(CU.ctr(x7833(0)), Const(48)), op=FixMul, results=List(b8425))
      RAStage(operands=List(b8425, CU.ctr(x7852(0))), op=FixAdd, results=List(x6537_x7856.readAddr))
    }
    val x6537_dsp1 = MemoryPipeline(name="x6537_dsp1",parent="x8153") { implicit CU => 
      val b8259 = CU.temp
      val b8261 = CU.temp
      val x6965_x6965 =  VectorFIFO(size=1).wtPort(x6537_x6965_v)
      val x6933 = CounterChain.copy("x6967", "x6933")
      val x6537_x6960 =  SRAM(size=768,banking = NoBanking()).wtPort(x6965_x6965.readPort).rdPort(x6537_x6960_x6966_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6933(0)), Const(48)), op=FixMul, results=List(b8261))
      WAStage(operands=List(b8261, CU.ctr(x6933(1))), op=FixAdd, results=List(x6537_x6960.writeAddr))
      RAStage(operands=List(CU.ctr(x6933(0)), Const(48)), op=FixMul, results=List(b8259))
      RAStage(operands=List(b8259, CU.ctr(x6933(1))), op=FixAdd, results=List(x6537_x6960.readAddr))
    }
    val x6538_dsp0 = MemoryPipeline(name="x6538_dsp0",parent="x8153") { implicit CU => 
      val b8431 = CU.temp
      val b8281 = CU.temp
      val x7071_x7071 =  VectorFIFO(size=1).wtPort(x6538_x7071_v)
      val x7039 = CounterChain.copy("x7073", "x7039")
      val x7873 = CounterChain.copy("x7911", "x7873")
      val x7892 = CounterChain.copy("x7900", "x7892")
      val x6538_x7896 =  SRAM(size=768,banking = NoBanking()).wtPort(x7071_x7071.readPort).rdPort(x6538_x7896_x7900_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7039(0)), Const(48)), op=FixMul, results=List(b8281))
      WAStage(operands=List(b8281, CU.ctr(x7039(1))), op=FixAdd, results=List(x6538_x7896.writeAddr))
      RAStage(operands=List(CU.ctr(x7873(0)), Const(48)), op=FixMul, results=List(b8431))
      RAStage(operands=List(b8431, CU.ctr(x7892(0))), op=FixAdd, results=List(x6538_x7896.readAddr))
    }
    val x6538_dsp1 = MemoryPipeline(name="x6538_dsp1",parent="x8153") { implicit CU => 
      val b8281 = CU.temp
      val b8279 = CU.temp
      val x7071_x7071 =  VectorFIFO(size=1).wtPort(x6538_x7071_v)
      val x7039 = CounterChain.copy("x7073", "x7039")
      val x6538_x7066 =  SRAM(size=768,banking = NoBanking()).wtPort(x7071_x7071.readPort).rdPort(x6538_x7066_x7072_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7039(0)), Const(48)), op=FixMul, results=List(b8281))
      WAStage(operands=List(b8281, CU.ctr(x7039(1))), op=FixAdd, results=List(x6538_x7066.writeAddr))
      RAStage(operands=List(CU.ctr(x7039(0)), Const(48)), op=FixMul, results=List(b8279))
      RAStage(operands=List(b8279, CU.ctr(x7039(1))), op=FixAdd, results=List(x6538_x7066.readAddr))
    }
    val x6539_dsp0 = MemoryPipeline(name="x6539_dsp0",parent="x8153") { implicit CU => 
      val b8437 = CU.temp
      val b8301 = CU.temp
      val x7177_x7177 =  VectorFIFO(size=1).wtPort(x6539_x7177_v)
      val x7145 = CounterChain.copy("x7179", "x7145")
      val x7913 = CounterChain.copy("x7951", "x7913")
      val x7932 = CounterChain.copy("x7940", "x7932")
      val x6539_x7936 =  SRAM(size=768,banking = NoBanking()).wtPort(x7177_x7177.readPort).rdPort(x6539_x7936_x7940_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7145(0)), Const(48)), op=FixMul, results=List(b8301))
      WAStage(operands=List(b8301, CU.ctr(x7145(1))), op=FixAdd, results=List(x6539_x7936.writeAddr))
      RAStage(operands=List(CU.ctr(x7913(0)), Const(48)), op=FixMul, results=List(b8437))
      RAStage(operands=List(b8437, CU.ctr(x7932(0))), op=FixAdd, results=List(x6539_x7936.readAddr))
    }
    val x6539_dsp1 = MemoryPipeline(name="x6539_dsp1",parent="x8153") { implicit CU => 
      val b8299 = CU.temp
      val b8301 = CU.temp
      val x7177_x7177 =  VectorFIFO(size=1).wtPort(x6539_x7177_v)
      val x7145 = CounterChain.copy("x7179", "x7145")
      val x6539_x7172 =  SRAM(size=768,banking = NoBanking()).wtPort(x7177_x7177.readPort).rdPort(x6539_x7172_x7178_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7145(0)), Const(48)), op=FixMul, results=List(b8301))
      WAStage(operands=List(b8301, CU.ctr(x7145(1))), op=FixAdd, results=List(x6539_x7172.writeAddr))
      RAStage(operands=List(CU.ctr(x7145(0)), Const(48)), op=FixMul, results=List(b8299))
      RAStage(operands=List(b8299, CU.ctr(x7145(1))), op=FixAdd, results=List(x6539_x7172.readAddr))
    }
    val x6540_dsp0 = MemoryPipeline(name="x6540_dsp0",parent="x8153") { implicit CU => 
      val b8321 = CU.temp
      val b8443 = CU.temp
      val x7283_x7283 =  VectorFIFO(size=1).wtPort(x6540_x7283_v)
      val x7251 = CounterChain.copy("x7285", "x7251")
      val x7953 = CounterChain.copy("x7991", "x7953")
      val x7972 = CounterChain.copy("x7980", "x7972")
      val x6540_x7976 =  SRAM(size=768,banking = NoBanking()).wtPort(x7283_x7283.readPort).rdPort(x6540_x7976_x7980_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7251(0)), Const(48)), op=FixMul, results=List(b8321))
      WAStage(operands=List(b8321, CU.ctr(x7251(1))), op=FixAdd, results=List(x6540_x7976.writeAddr))
      RAStage(operands=List(CU.ctr(x7953(0)), Const(48)), op=FixMul, results=List(b8443))
      RAStage(operands=List(b8443, CU.ctr(x7972(0))), op=FixAdd, results=List(x6540_x7976.readAddr))
    }
    val x6540_dsp1 = MemoryPipeline(name="x6540_dsp1",parent="x8153") { implicit CU => 
      val b8319 = CU.temp
      val b8321 = CU.temp
      val x7283_x7283 =  VectorFIFO(size=1).wtPort(x6540_x7283_v)
      val x7251 = CounterChain.copy("x7285", "x7251")
      val x6540_x7278 =  SRAM(size=768,banking = NoBanking()).wtPort(x7283_x7283.readPort).rdPort(x6540_x7278_x7284_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7251(0)), Const(48)), op=FixMul, results=List(b8321))
      WAStage(operands=List(b8321, CU.ctr(x7251(1))), op=FixAdd, results=List(x6540_x7278.writeAddr))
      RAStage(operands=List(CU.ctr(x7251(0)), Const(48)), op=FixMul, results=List(b8319))
      RAStage(operands=List(b8319, CU.ctr(x7251(1))), op=FixAdd, results=List(x6540_x7278.readAddr))
    }
    val x6541_dsp0 = MemoryPipeline(name="x6541_dsp0",parent="x8153") { implicit CU => 
      val b8341 = CU.temp
      val b8449 = CU.temp
      val x7389_x7389 =  VectorFIFO(size=1).wtPort(x6541_x7389_v)
      val x7357 = CounterChain.copy("x7391", "x7357")
      val x7993 = CounterChain.copy("x8031", "x7993")
      val x8012 = CounterChain.copy("x8020", "x8012")
      val x6541_x8016 =  SRAM(size=768,banking = NoBanking()).wtPort(x7389_x7389.readPort).rdPort(x6541_x8016_x8020_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7357(0)), Const(48)), op=FixMul, results=List(b8341))
      WAStage(operands=List(b8341, CU.ctr(x7357(1))), op=FixAdd, results=List(x6541_x8016.writeAddr))
      RAStage(operands=List(CU.ctr(x7993(0)), Const(48)), op=FixMul, results=List(b8449))
      RAStage(operands=List(b8449, CU.ctr(x8012(0))), op=FixAdd, results=List(x6541_x8016.readAddr))
    }
    val x6541_dsp1 = MemoryPipeline(name="x6541_dsp1",parent="x8153") { implicit CU => 
      val b8341 = CU.temp
      val b8339 = CU.temp
      val x7389_x7389 =  VectorFIFO(size=1).wtPort(x6541_x7389_v)
      val x7357 = CounterChain.copy("x7391", "x7357")
      val x6541_x7384 =  SRAM(size=768,banking = NoBanking()).wtPort(x7389_x7389.readPort).rdPort(x6541_x7384_x7390_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7357(0)), Const(48)), op=FixMul, results=List(b8341))
      WAStage(operands=List(b8341, CU.ctr(x7357(1))), op=FixAdd, results=List(x6541_x7384.writeAddr))
      RAStage(operands=List(CU.ctr(x7357(0)), Const(48)), op=FixMul, results=List(b8339))
      RAStage(operands=List(b8339, CU.ctr(x7357(1))), op=FixAdd, results=List(x6541_x7384.readAddr))
    }
    val x6542_dsp0 = MemoryPipeline(name="x6542_dsp0",parent="x8153") { implicit CU => 
      val b8361 = CU.temp
      val b8455 = CU.temp
      val x7495_x7495 =  VectorFIFO(size=1).wtPort(x6542_x7495_v)
      val x7463 = CounterChain.copy("x7497", "x7463")
      val x8033 = CounterChain.copy("x8071", "x8033")
      val x8052 = CounterChain.copy("x8060", "x8052")
      val x6542_x8056 =  SRAM(size=768,banking = NoBanking()).wtPort(x7495_x7495.readPort).rdPort(x6542_x8056_x8060_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7463(0)), Const(48)), op=FixMul, results=List(b8361))
      WAStage(operands=List(b8361, CU.ctr(x7463(1))), op=FixAdd, results=List(x6542_x8056.writeAddr))
      RAStage(operands=List(CU.ctr(x8033(0)), Const(48)), op=FixMul, results=List(b8455))
      RAStage(operands=List(b8455, CU.ctr(x8052(0))), op=FixAdd, results=List(x6542_x8056.readAddr))
    }
    val x6542_dsp1 = MemoryPipeline(name="x6542_dsp1",parent="x8153") { implicit CU => 
      val b8359 = CU.temp
      val b8361 = CU.temp
      val x7495_x7495 =  VectorFIFO(size=1).wtPort(x6542_x7495_v)
      val x7463 = CounterChain.copy("x7497", "x7463")
      val x6542_x7490 =  SRAM(size=768,banking = NoBanking()).wtPort(x7495_x7495.readPort).rdPort(x6542_x7490_x7496_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7463(0)), Const(48)), op=FixMul, results=List(b8361))
      WAStage(operands=List(b8361, CU.ctr(x7463(1))), op=FixAdd, results=List(x6542_x7490.writeAddr))
      RAStage(operands=List(CU.ctr(x7463(0)), Const(48)), op=FixMul, results=List(b8359))
      RAStage(operands=List(b8359, CU.ctr(x7463(1))), op=FixAdd, results=List(x6542_x7490.readAddr))
    }
    val x6543_dsp0 = MemoryPipeline(name="x6543_dsp0",parent="x8153") { implicit CU => 
      val b8461 = CU.temp
      val b8381 = CU.temp
      val x7601_x7601 =  VectorFIFO(size=1).wtPort(x6543_x7601_v)
      val x7569 = CounterChain.copy("x7603", "x7569")
      val x8073 = CounterChain.copy("x8111", "x8073")
      val x8092 = CounterChain.copy("x8100", "x8092")
      val x6543_x8096 =  SRAM(size=768,banking = NoBanking()).wtPort(x7601_x7601.readPort).rdPort(x6543_x8096_x8100_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7569(0)), Const(48)), op=FixMul, results=List(b8381))
      WAStage(operands=List(b8381, CU.ctr(x7569(1))), op=FixAdd, results=List(x6543_x8096.writeAddr))
      RAStage(operands=List(CU.ctr(x8073(0)), Const(48)), op=FixMul, results=List(b8461))
      RAStage(operands=List(b8461, CU.ctr(x8092(0))), op=FixAdd, results=List(x6543_x8096.readAddr))
    }
    val x6543_dsp1 = MemoryPipeline(name="x6543_dsp1",parent="x8153") { implicit CU => 
      val b8381 = CU.temp
      val b8379 = CU.temp
      val x7601_x7601 =  VectorFIFO(size=1).wtPort(x6543_x7601_v)
      val x7569 = CounterChain.copy("x7603", "x7569")
      val x6543_x7596 =  SRAM(size=768,banking = NoBanking()).wtPort(x7601_x7601.readPort).rdPort(x6543_x7596_x7602_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7569(0)), Const(48)), op=FixMul, results=List(b8381))
      WAStage(operands=List(b8381, CU.ctr(x7569(1))), op=FixAdd, results=List(x6543_x7596.writeAddr))
      RAStage(operands=List(CU.ctr(x7569(0)), Const(48)), op=FixMul, results=List(b8379))
      RAStage(operands=List(b8379, CU.ctr(x7569(1))), op=FixAdd, results=List(x6543_x7596.readAddr))
    }
    val x6544_dsp0 = MemoryPipeline(name="x6544_dsp0",parent="x8153") { implicit CU => 
      val b8401 = CU.temp
      val b8467 = CU.temp
      val x7707_x7707 =  VectorFIFO(size=1).wtPort(x6544_x7707_v)
      val x7675 = CounterChain.copy("x7709", "x7675")
      val x8113 = CounterChain.copy("x8151", "x8113")
      val x8132 = CounterChain.copy("x8140", "x8132")
      val x6544_x8136 =  SRAM(size=768,banking = NoBanking()).wtPort(x7707_x7707.readPort).rdPort(x6544_x8136_x8140_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7675(0)), Const(48)), op=FixMul, results=List(b8401))
      WAStage(operands=List(b8401, CU.ctr(x7675(1))), op=FixAdd, results=List(x6544_x8136.writeAddr))
      RAStage(operands=List(CU.ctr(x8113(0)), Const(48)), op=FixMul, results=List(b8467))
      RAStage(operands=List(b8467, CU.ctr(x8132(0))), op=FixAdd, results=List(x6544_x8136.readAddr))
    }
    val x6544_dsp1 = MemoryPipeline(name="x6544_dsp1",parent="x8153") { implicit CU => 
      val b8401 = CU.temp
      val b8399 = CU.temp
      val x7707_x7707 =  VectorFIFO(size=1).wtPort(x6544_x7707_v)
      val x7675 = CounterChain.copy("x7709", "x7675")
      val x6544_x7702 =  SRAM(size=768,banking = NoBanking()).wtPort(x7707_x7707.readPort).rdPort(x6544_x7702_x7708_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7675(0)), Const(48)), op=FixMul, results=List(b8401))
      WAStage(operands=List(b8401, CU.ctr(x7675(1))), op=FixAdd, results=List(x6544_x7702.writeAddr))
      RAStage(operands=List(CU.ctr(x7675(0)), Const(48)), op=FixMul, results=List(b8399))
      RAStage(operands=List(b8399, CU.ctr(x7675(1))), op=FixAdd, results=List(x6544_x7702.readAddr))
    }
    val x6650 = MetaPipeline(name="x6650",parent=x8153) { implicit CU => 
      val x6514_x6545 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr3 = Counter(min=Const(0), max=x6514_x6545.load, step=Const(48), par=1) // Counter
      val x6547 = CounterChain(name = "x6547", ctr3).iter(1)
    }
    val x6548_dsp0 = MemoryPipeline(name="x6548_dsp0",parent="x6650") { implicit CU => 
      val b8195 = CU.temp
      val b8187 = CU.temp
      val x6578_x6578 =  VectorFIFO(size=1).wtPort(x6553_x6569_data_v)
      val x6615 = CounterChain.copy("x6649", "x6615")
      val x6618 = CounterChain.copy("x6637_0", "x6618")
      val x6551 = CounterChain.copy("x6580", "x6551")
      val x6571 = CounterChain.copy("x6579", "x6571")
      val x6548_x6624 =  SRAM(size=768,banking = Strided(1)).wtPort(x6578_x6578.readPort).rdPort(x6548_x6624_x6637_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6551(0)), Const(48)), op=FixMul, results=List(b8187))
      WAStage(operands=List(b8187, CU.ctr(x6571(0))), op=FixAdd, results=List(x6548_x6624.writeAddr))
      RAStage(operands=List(CU.ctr(x6615(0)), Const(48)), op=FixMul, results=List(b8195))
      RAStage(operands=List(b8195, CU.ctr(x6618(0))), op=FixAdd, results=List(x6548_x6624.readAddr))
    }
    val x6549_dsp0 = MemoryPipeline(name="x6549_dsp0",parent="x6650") { implicit CU => 
      val b8193 = CU.temp
      val b8197 = CU.temp
      val x6609_x6609 =  VectorFIFO(size=1).wtPort(x6584_x6600_data_v)
      val x6615 = CounterChain.copy("x6649", "x6615")
      val x6618 = CounterChain.copy("x6637_0", "x6618")
      val x6602 = CounterChain.copy("x6610", "x6602")
      val x6582 = CounterChain.copy("x6611", "x6582")
      val x6549_x6625 =  SRAM(size=2304,banking = Strided(1)).wtPort(x6609_x6609.readPort).rdPort(x6549_x6625_x6637_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6582(0)), Const(48)), op=FixMul, results=List(b8193))
      WAStage(operands=List(b8193, CU.ctr(x6602(0))), op=FixAdd, results=List(x6549_x6625.writeAddr))
      RAStage(operands=List(CU.ctr(x6618(0)), Const(48)), op=FixMul, results=List(b8197))
      RAStage(operands=List(b8197, CU.ctr(x6615(1))), op=FixAdd, results=List(x6549_x6625.readAddr))
    }
    val x6580 = StreamController(name="x6580",parent=x6650) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x6551 = CounterChain(name = "x6551", ctr4).iter(16)
    }
    val x6568_0 = Pipeline(name="x6568_0",parent=x6580) { implicit CU => 
      val x6557 = CU.temp
      val x6559 = CU.temp
      val x6558 = CU.temp
      val x6556 = CU.temp
      val x6554 =  ScalarBuffer().wtPort(x6554_argin)
      val x6514_x6555 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x6551 = CounterChain.copy("x6580", "x6551")
      val x6547 = CounterChain.copy("x6650", "x6547")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x6551(0))), op=FixAdd, results=List(x6556))
      Stage(operands=List(x6556, CU.load(x6514_x6555)), op=FixMul, results=List(x6557))
      Stage(operands=List(x6557, CU.ctr(x6547(0))), op=FixAdd, results=List(x6558))
      Stage(operands=List(x6558, Const(4)), op=FixMul, results=List(x6559))
      Stage(operands=List(x6559, CU.load(x6554)), op=FixAdd, results=List(CU.scalarOut(x6552_b8183_x6567_b8185_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6552_b8184_x6567_b8186_s)))
    }
    val x6569 = MemoryController(name="x6569",parent=x6580,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x6552_b8184_x6569 =  ScalarFIFO(name="size",size=1).wtPort(x6552_b8184_x6567_b8186_s)
      val x6552_b8183_x6569 =  ScalarFIFO(name="offset",size=1).wtPort(x6552_b8183_x6567_b8185_s)
      CU.newVout("data", x6553_x6569_data_v)
    }
    val x6579 = Pipeline(name="x6579",parent=x6580) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6571 = CounterChain(name = "x6571", ctr5).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6611 = StreamController(name="x6611",parent=x6650) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6582 = CounterChain(name = "x6582", ctr6).iter(48)
    }
    val x6599_0 = Pipeline(name="x6599_0",parent=x6611) { implicit CU => 
      val x6589 = CU.temp
      val x6590 = CU.temp
      val x6588 = CU.temp
      val x6587 = CU.temp
      val x6585 =  ScalarBuffer().wtPort(x6585_argin)
      val x6513_x6586 =  ScalarBuffer().wtPort(x6513_argin)
      val x6547 = CounterChain.copy("x6650", "x6547")
      val x6582 = CounterChain.copy("x6611", "x6582")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6547(0)), CU.ctr(x6582(0))), op=FixAdd, results=List(x6587))
      Stage(operands=List(x6587, CU.load(x6513_x6586)), op=FixMul, results=List(x6588))
      Stage(operands=List(x6588, CU.ctr(x6533(1))), op=FixAdd, results=List(x6589))
      Stage(operands=List(x6589, Const(4)), op=FixMul, results=List(x6590))
      Stage(operands=List(x6590, CU.load(x6585)), op=FixAdd, results=List(CU.scalarOut(x6583_b8189_x6598_b8191_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6583_b8190_x6598_b8192_s)))
    }
    val x6600 = MemoryController(name="x6600",parent=x6611,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x6583_b8190_x6600 =  ScalarFIFO(name="size",size=1).wtPort(x6583_b8190_x6598_b8192_s)
      val x6583_b8189_x6600 =  ScalarFIFO(name="offset",size=1).wtPort(x6583_b8189_x6598_b8191_s)
      CU.newVout("data", x6584_x6600_data_v)
    }
    val x6610 = Pipeline(name="x6610",parent=x6611) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6602 = CounterChain(name = "x6602", ctr7).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6649 = MetaPipeline(name="x6649",parent=x6650) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr9 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6615 = CounterChain(name = "x6615", ctr8, ctr9).iter(768)
    }
    val x6637_0 = Pipeline(name="x6637_0",parent=x6649) { implicit CU => 
      val x6624_x6624 =  VectorFIFO(size=1).wtPort(x6548_x6624_x6637_v)
      val x6625_x6625 =  VectorFIFO(size=1).wtPort(x6549_x6625_x6637_v)
      val ctr10 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x6618 = CounterChain(name = "x6618", ctr10).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6624_x6624), CU.load(x6625_x6625)), op=FixMul, results=List(CU.reduce))
      val (_, rr4933) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr4933), op=Bypass, results=List(CU.scalarOut(x6616_x6635_s)))
    }
    val x6648_0 = Pipeline(name="x6648_0",parent=x6649) { implicit CU => 
      val x6644 = CU.temp
      val x6645 = CU.temp
      val x6616_x6643 =  ScalarBuffer().wtPort(x6616_x6635_s)
      val x6642_x6642 =  VectorFIFO(size=1).wtPort(x6534_x6642_x6648_v)
      val x6547 = CounterChain.copy("x6650", "x6547")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6547(0)), Const(0)), op=FixEql, results=List(x6644))
      Stage(operands=List(x6644, Const(0), CU.load(x6642_x6642)), op=Mux, results=List(x6645))
      Stage(operands=List(x6645, CU.load(x6616_x6643)), op=FixAdd, results=List(CU.vecOut(x6534_x6647_v)))
    }
    val x6756 = MetaPipeline(name="x6756",parent=x8153) { implicit CU => 
      val x6514_x6651 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr11 = Counter(min=Const(0), max=x6514_x6651.load, step=Const(48), par=1) // Counter
      val x6653 = CounterChain(name = "x6653", ctr11).iter(1)
    }
    val x6654_dsp0 = MemoryPipeline(name="x6654_dsp0",parent="x6756") { implicit CU => 
      val b8207 = CU.temp
      val b8215 = CU.temp
      val x6684_x6684 =  VectorFIFO(size=1).wtPort(x6659_x6675_data_v)
      val x6677 = CounterChain.copy("x6685", "x6677")
      val x6721 = CounterChain.copy("x6755", "x6721")
      val x6657 = CounterChain.copy("x6686", "x6657")
      val x6724 = CounterChain.copy("x6743_0", "x6724")
      val x6654_x6730 =  SRAM(size=768,banking = Strided(1)).wtPort(x6684_x6684.readPort).rdPort(x6654_x6730_x6743_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6657(0)), Const(48)), op=FixMul, results=List(b8207))
      WAStage(operands=List(b8207, CU.ctr(x6677(0))), op=FixAdd, results=List(x6654_x6730.writeAddr))
      RAStage(operands=List(CU.ctr(x6721(0)), Const(48)), op=FixMul, results=List(b8215))
      RAStage(operands=List(b8215, CU.ctr(x6724(0))), op=FixAdd, results=List(x6654_x6730.readAddr))
    }
    val x6655_dsp0 = MemoryPipeline(name="x6655_dsp0",parent="x6756") { implicit CU => 
      val b8213 = CU.temp
      val b8217 = CU.temp
      val x6715_x6715 =  VectorFIFO(size=1).wtPort(x6690_x6706_data_v)
      val x6688 = CounterChain.copy("x6717", "x6688")
      val x6708 = CounterChain.copy("x6716", "x6708")
      val x6721 = CounterChain.copy("x6755", "x6721")
      val x6724 = CounterChain.copy("x6743_0", "x6724")
      val x6655_x6731 =  SRAM(size=2304,banking = Strided(1)).wtPort(x6715_x6715.readPort).rdPort(x6655_x6731_x6743_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6688(0)), Const(48)), op=FixMul, results=List(b8213))
      WAStage(operands=List(b8213, CU.ctr(x6708(0))), op=FixAdd, results=List(x6655_x6731.writeAddr))
      RAStage(operands=List(CU.ctr(x6724(0)), Const(48)), op=FixMul, results=List(b8217))
      RAStage(operands=List(b8217, CU.ctr(x6721(1))), op=FixAdd, results=List(x6655_x6731.readAddr))
    }
    val x6686 = StreamController(name="x6686",parent=x6756) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x6657 = CounterChain(name = "x6657", ctr12).iter(16)
    }
    val x6674_0 = Pipeline(name="x6674_0",parent=x6686) { implicit CU => 
      val x6664 = CU.temp
      val x6662 = CU.temp
      val x6665 = CU.temp
      val x6663 = CU.temp
      val x6514_x6661 =  ScalarBuffer().wtPort(x6514_argin)
      val x6660 =  ScalarBuffer().wtPort(x6660_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x6657 = CounterChain.copy("x6686", "x6657")
      val x6653 = CounterChain.copy("x6756", "x6653")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x6657(0))), op=FixAdd, results=List(x6662))
      Stage(operands=List(x6662, CU.load(x6514_x6661)), op=FixMul, results=List(x6663))
      Stage(operands=List(x6663, CU.ctr(x6653(0))), op=FixAdd, results=List(x6664))
      Stage(operands=List(x6664, Const(4)), op=FixMul, results=List(x6665))
      Stage(operands=List(x6665, CU.load(x6660)), op=FixAdd, results=List(CU.scalarOut(x6658_b8203_x6673_b8205_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6658_b8204_x6673_b8206_s)))
    }
    val x6675 = MemoryController(name="x6675",parent=x6686,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x6658_b8203_x6675 =  ScalarFIFO(name="offset",size=1).wtPort(x6658_b8203_x6673_b8205_s)
      val x6658_b8204_x6675 =  ScalarFIFO(name="size",size=1).wtPort(x6658_b8204_x6673_b8206_s)
      CU.newVout("data", x6659_x6675_data_v)
    }
    val x6685 = Pipeline(name="x6685",parent=x6686) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6677 = CounterChain(name = "x6677", ctr13).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6717 = StreamController(name="x6717",parent=x6756) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6688 = CounterChain(name = "x6688", ctr14).iter(48)
    }
    val x6705_0 = Pipeline(name="x6705_0",parent=x6717) { implicit CU => 
      val x6695 = CU.temp
      val x6696 = CU.temp
      val x6694 = CU.temp
      val x6693 = CU.temp
      val x6691 =  ScalarBuffer().wtPort(x6691_argin)
      val x6513_x6692 =  ScalarBuffer().wtPort(x6513_argin)
      val x6653 = CounterChain.copy("x6756", "x6653")
      val x6688 = CounterChain.copy("x6717", "x6688")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6653(0)), CU.ctr(x6688(0))), op=FixAdd, results=List(x6693))
      Stage(operands=List(x6693, CU.load(x6513_x6692)), op=FixMul, results=List(x6694))
      Stage(operands=List(x6694, CU.ctr(x6533(1))), op=FixAdd, results=List(x6695))
      Stage(operands=List(x6695, Const(4)), op=FixMul, results=List(x6696))
      Stage(operands=List(x6696, CU.load(x6691)), op=FixAdd, results=List(CU.scalarOut(x6689_b8209_x6704_b8211_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6689_b8210_x6704_b8212_s)))
    }
    val x6706 = MemoryController(name="x6706",parent=x6717,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x6689_b8210_x6706 =  ScalarFIFO(name="size",size=1).wtPort(x6689_b8210_x6704_b8212_s)
      val x6689_b8209_x6706 =  ScalarFIFO(name="offset",size=1).wtPort(x6689_b8209_x6704_b8211_s)
      CU.newVout("data", x6690_x6706_data_v)
    }
    val x6716 = Pipeline(name="x6716",parent=x6717) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6708 = CounterChain(name = "x6708", ctr15).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6755 = MetaPipeline(name="x6755",parent=x6756) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr17 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6721 = CounterChain(name = "x6721", ctr16, ctr17).iter(768)
    }
    val x6743_0 = Pipeline(name="x6743_0",parent=x6755) { implicit CU => 
      val x6731_x6731 =  VectorFIFO(size=1).wtPort(x6655_x6731_x6743_v)
      val x6730_x6730 =  VectorFIFO(size=1).wtPort(x6654_x6730_x6743_v)
      val ctr18 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x6724 = CounterChain(name = "x6724", ctr18).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6730_x6730), CU.load(x6731_x6731)), op=FixMul, results=List(CU.reduce))
      val (_, rr4984) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr4984), op=Bypass, results=List(CU.scalarOut(x6722_x6741_s)))
    }
    val x6754_0 = Pipeline(name="x6754_0",parent=x6755) { implicit CU => 
      val x6750 = CU.temp
      val x6751 = CU.temp
      val x6722_x6749 =  ScalarBuffer().wtPort(x6722_x6741_s)
      val x6748_x6748 =  VectorFIFO(size=1).wtPort(x6535_x6748_x6754_v)
      val x6653 = CounterChain.copy("x6756", "x6653")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6653(0)), Const(0)), op=FixEql, results=List(x6750))
      Stage(operands=List(x6750, Const(0), CU.load(x6748_x6748)), op=Mux, results=List(x6751))
      Stage(operands=List(x6751, CU.load(x6722_x6749)), op=FixAdd, results=List(CU.vecOut(x6535_x6753_v)))
    }
    val x6862 = MetaPipeline(name="x6862",parent=x8153) { implicit CU => 
      val x6514_x6757 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr19 = Counter(min=Const(0), max=x6514_x6757.load, step=Const(48), par=1) // Counter
      val x6759 = CounterChain(name = "x6759", ctr19).iter(1)
    }
    val x6760_dsp0 = MemoryPipeline(name="x6760_dsp0",parent="x6862") { implicit CU => 
      val b8235 = CU.temp
      val b8227 = CU.temp
      val x6790_x6790 =  VectorFIFO(size=1).wtPort(x6765_x6781_data_v)
      val x6830 = CounterChain.copy("x6849_0", "x6830")
      val x6783 = CounterChain.copy("x6791", "x6783")
      val x6827 = CounterChain.copy("x6861", "x6827")
      val x6763 = CounterChain.copy("x6792", "x6763")
      val x6760_x6836 =  SRAM(size=768,banking = Strided(1)).wtPort(x6790_x6790.readPort).rdPort(x6760_x6836_x6849_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6763(0)), Const(48)), op=FixMul, results=List(b8227))
      WAStage(operands=List(b8227, CU.ctr(x6783(0))), op=FixAdd, results=List(x6760_x6836.writeAddr))
      RAStage(operands=List(CU.ctr(x6827(0)), Const(48)), op=FixMul, results=List(b8235))
      RAStage(operands=List(b8235, CU.ctr(x6830(0))), op=FixAdd, results=List(x6760_x6836.readAddr))
    }
    val x6761_dsp0 = MemoryPipeline(name="x6761_dsp0",parent="x6862") { implicit CU => 
      val b8237 = CU.temp
      val b8233 = CU.temp
      val x6821_x6821 =  VectorFIFO(size=1).wtPort(x6796_x6812_data_v)
      val x6830 = CounterChain.copy("x6849_0", "x6830")
      val x6794 = CounterChain.copy("x6823", "x6794")
      val x6827 = CounterChain.copy("x6861", "x6827")
      val x6814 = CounterChain.copy("x6822", "x6814")
      val x6761_x6837 =  SRAM(size=2304,banking = Strided(1)).wtPort(x6821_x6821.readPort).rdPort(x6761_x6837_x6849_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6794(0)), Const(48)), op=FixMul, results=List(b8233))
      WAStage(operands=List(b8233, CU.ctr(x6814(0))), op=FixAdd, results=List(x6761_x6837.writeAddr))
      RAStage(operands=List(CU.ctr(x6830(0)), Const(48)), op=FixMul, results=List(b8237))
      RAStage(operands=List(b8237, CU.ctr(x6827(1))), op=FixAdd, results=List(x6761_x6837.readAddr))
    }
    val x6792 = StreamController(name="x6792",parent=x6862) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x6763 = CounterChain(name = "x6763", ctr20).iter(16)
    }
    val x6780_0 = Pipeline(name="x6780_0",parent=x6792) { implicit CU => 
      val x6768 = CU.temp
      val x6769 = CU.temp
      val x6770 = CU.temp
      val x6771 = CU.temp
      val x6766 =  ScalarBuffer().wtPort(x6766_argin)
      val x6514_x6767 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x6763 = CounterChain.copy("x6792", "x6763")
      val x6759 = CounterChain.copy("x6862", "x6759")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x6763(0))), op=FixAdd, results=List(x6768))
      Stage(operands=List(x6768, CU.load(x6514_x6767)), op=FixMul, results=List(x6769))
      Stage(operands=List(x6769, CU.ctr(x6759(0))), op=FixAdd, results=List(x6770))
      Stage(operands=List(x6770, Const(4)), op=FixMul, results=List(x6771))
      Stage(operands=List(x6771, CU.load(x6766)), op=FixAdd, results=List(CU.scalarOut(x6764_b8223_x6779_b8225_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6764_b8224_x6779_b8226_s)))
    }
    val x6781 = MemoryController(name="x6781",parent=x6792,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x6764_b8223_x6781 =  ScalarFIFO(name="offset",size=1).wtPort(x6764_b8223_x6779_b8225_s)
      val x6764_b8224_x6781 =  ScalarFIFO(name="size",size=1).wtPort(x6764_b8224_x6779_b8226_s)
      CU.newVout("data", x6765_x6781_data_v)
    }
    val x6791 = Pipeline(name="x6791",parent=x6792) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6783 = CounterChain(name = "x6783", ctr21).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6823 = StreamController(name="x6823",parent=x6862) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6794 = CounterChain(name = "x6794", ctr22).iter(48)
    }
    val x6811_0 = Pipeline(name="x6811_0",parent=x6823) { implicit CU => 
      val x6799 = CU.temp
      val x6802 = CU.temp
      val x6801 = CU.temp
      val x6800 = CU.temp
      val x6797 =  ScalarBuffer().wtPort(x6797_argin)
      val x6513_x6798 =  ScalarBuffer().wtPort(x6513_argin)
      val x6759 = CounterChain.copy("x6862", "x6759")
      val x6794 = CounterChain.copy("x6823", "x6794")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6759(0)), CU.ctr(x6794(0))), op=FixAdd, results=List(x6799))
      Stage(operands=List(x6799, CU.load(x6513_x6798)), op=FixMul, results=List(x6800))
      Stage(operands=List(x6800, CU.ctr(x6533(1))), op=FixAdd, results=List(x6801))
      Stage(operands=List(x6801, Const(4)), op=FixMul, results=List(x6802))
      Stage(operands=List(x6802, CU.load(x6797)), op=FixAdd, results=List(CU.scalarOut(x6795_b8229_x6810_b8231_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6795_b8230_x6810_b8232_s)))
    }
    val x6812 = MemoryController(name="x6812",parent=x6823,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x6795_b8229_x6812 =  ScalarFIFO(name="offset",size=1).wtPort(x6795_b8229_x6810_b8231_s)
      val x6795_b8230_x6812 =  ScalarFIFO(name="size",size=1).wtPort(x6795_b8230_x6810_b8232_s)
      CU.newVout("data", x6796_x6812_data_v)
    }
    val x6822 = Pipeline(name="x6822",parent=x6823) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6814 = CounterChain(name = "x6814", ctr23).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6861 = MetaPipeline(name="x6861",parent=x6862) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr25 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6827 = CounterChain(name = "x6827", ctr24, ctr25).iter(768)
    }
    val x6849_0 = Pipeline(name="x6849_0",parent=x6861) { implicit CU => 
      val x6837_x6837 =  VectorFIFO(size=1).wtPort(x6761_x6837_x6849_v)
      val x6836_x6836 =  VectorFIFO(size=1).wtPort(x6760_x6836_x6849_v)
      val ctr26 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x6830 = CounterChain(name = "x6830", ctr26).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6836_x6836), CU.load(x6837_x6837)), op=FixMul, results=List(CU.reduce))
      val (_, rr5035) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5035), op=Bypass, results=List(CU.scalarOut(x6828_x6847_s)))
    }
    val x6860_0 = Pipeline(name="x6860_0",parent=x6861) { implicit CU => 
      val x6857 = CU.temp
      val x6856 = CU.temp
      val x6828_x6855 =  ScalarBuffer().wtPort(x6828_x6847_s)
      val x6854_x6854 =  VectorFIFO(size=1).wtPort(x6536_x6854_x6860_v)
      val x6759 = CounterChain.copy("x6862", "x6759")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6759(0)), Const(0)), op=FixEql, results=List(x6856))
      Stage(operands=List(x6856, Const(0), CU.load(x6854_x6854)), op=Mux, results=List(x6857))
      Stage(operands=List(x6857, CU.load(x6828_x6855)), op=FixAdd, results=List(CU.vecOut(x6536_x6859_v)))
    }
    val x6968 = MetaPipeline(name="x6968",parent=x8153) { implicit CU => 
      val x6514_x6863 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr27 = Counter(min=Const(0), max=x6514_x6863.load, step=Const(48), par=1) // Counter
      val x6865 = CounterChain(name = "x6865", ctr27).iter(1)
    }
    val x6866_dsp0 = MemoryPipeline(name="x6866_dsp0",parent="x6968") { implicit CU => 
      val b8255 = CU.temp
      val b8247 = CU.temp
      val x6896_x6896 =  VectorFIFO(size=1).wtPort(x6871_x6887_data_v)
      val x6869 = CounterChain.copy("x6898", "x6869")
      val x6889 = CounterChain.copy("x6897", "x6889")
      val x6936 = CounterChain.copy("x6955_0", "x6936")
      val x6933 = CounterChain.copy("x6967", "x6933")
      val x6866_x6942 =  SRAM(size=768,banking = Strided(1)).wtPort(x6896_x6896.readPort).rdPort(x6866_x6942_x6955_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6869(0)), Const(48)), op=FixMul, results=List(b8247))
      WAStage(operands=List(b8247, CU.ctr(x6889(0))), op=FixAdd, results=List(x6866_x6942.writeAddr))
      RAStage(operands=List(CU.ctr(x6933(0)), Const(48)), op=FixMul, results=List(b8255))
      RAStage(operands=List(b8255, CU.ctr(x6936(0))), op=FixAdd, results=List(x6866_x6942.readAddr))
    }
    val x6867_dsp0 = MemoryPipeline(name="x6867_dsp0",parent="x6968") { implicit CU => 
      val b8257 = CU.temp
      val b8253 = CU.temp
      val x6927_x6927 =  VectorFIFO(size=1).wtPort(x6902_x6918_data_v)
      val x6920 = CounterChain.copy("x6928", "x6920")
      val x6936 = CounterChain.copy("x6955_0", "x6936")
      val x6900 = CounterChain.copy("x6929", "x6900")
      val x6933 = CounterChain.copy("x6967", "x6933")
      val x6867_x6943 =  SRAM(size=2304,banking = Strided(1)).wtPort(x6927_x6927.readPort).rdPort(x6867_x6943_x6955_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6900(0)), Const(48)), op=FixMul, results=List(b8253))
      WAStage(operands=List(b8253, CU.ctr(x6920(0))), op=FixAdd, results=List(x6867_x6943.writeAddr))
      RAStage(operands=List(CU.ctr(x6936(0)), Const(48)), op=FixMul, results=List(b8257))
      RAStage(operands=List(b8257, CU.ctr(x6933(1))), op=FixAdd, results=List(x6867_x6943.readAddr))
    }
    val x6898 = StreamController(name="x6898",parent=x6968) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x6869 = CounterChain(name = "x6869", ctr28).iter(16)
    }
    val x6886_0 = Pipeline(name="x6886_0",parent=x6898) { implicit CU => 
      val x6875 = CU.temp
      val x6874 = CU.temp
      val x6877 = CU.temp
      val x6876 = CU.temp
      val x6514_x6873 =  ScalarBuffer().wtPort(x6514_argin)
      val x6872 =  ScalarBuffer().wtPort(x6872_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x6869 = CounterChain.copy("x6898", "x6869")
      val x6865 = CounterChain.copy("x6968", "x6865")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x6869(0))), op=FixAdd, results=List(x6874))
      Stage(operands=List(x6874, CU.load(x6514_x6873)), op=FixMul, results=List(x6875))
      Stage(operands=List(x6875, CU.ctr(x6865(0))), op=FixAdd, results=List(x6876))
      Stage(operands=List(x6876, Const(4)), op=FixMul, results=List(x6877))
      Stage(operands=List(x6877, CU.load(x6872)), op=FixAdd, results=List(CU.scalarOut(x6870_b8243_x6885_b8245_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6870_b8244_x6885_b8246_s)))
    }
    val x6887 = MemoryController(name="x6887",parent=x6898,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x6870_b8244_x6887 =  ScalarFIFO(name="size",size=1).wtPort(x6870_b8244_x6885_b8246_s)
      val x6870_b8243_x6887 =  ScalarFIFO(name="offset",size=1).wtPort(x6870_b8243_x6885_b8245_s)
      CU.newVout("data", x6871_x6887_data_v)
    }
    val x6897 = Pipeline(name="x6897",parent=x6898) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6889 = CounterChain(name = "x6889", ctr29).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6929 = StreamController(name="x6929",parent=x6968) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6900 = CounterChain(name = "x6900", ctr30).iter(48)
    }
    val x6917_0 = Pipeline(name="x6917_0",parent=x6929) { implicit CU => 
      val x6906 = CU.temp
      val x6908 = CU.temp
      val x6907 = CU.temp
      val x6905 = CU.temp
      val x6903 =  ScalarBuffer().wtPort(x6903_argin)
      val x6513_x6904 =  ScalarBuffer().wtPort(x6513_argin)
      val x6865 = CounterChain.copy("x6968", "x6865")
      val x6900 = CounterChain.copy("x6929", "x6900")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6865(0)), CU.ctr(x6900(0))), op=FixAdd, results=List(x6905))
      Stage(operands=List(x6905, CU.load(x6513_x6904)), op=FixMul, results=List(x6906))
      Stage(operands=List(x6906, CU.ctr(x6533(1))), op=FixAdd, results=List(x6907))
      Stage(operands=List(x6907, Const(4)), op=FixMul, results=List(x6908))
      Stage(operands=List(x6908, CU.load(x6903)), op=FixAdd, results=List(CU.scalarOut(x6901_b8249_x6916_b8251_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6901_b8250_x6916_b8252_s)))
    }
    val x6918 = MemoryController(name="x6918",parent=x6929,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x6901_b8250_x6918 =  ScalarFIFO(name="size",size=1).wtPort(x6901_b8250_x6916_b8252_s)
      val x6901_b8249_x6918 =  ScalarFIFO(name="offset",size=1).wtPort(x6901_b8249_x6916_b8251_s)
      CU.newVout("data", x6902_x6918_data_v)
    }
    val x6928 = Pipeline(name="x6928",parent=x6929) { implicit CU => 
      val ctr31 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6920 = CounterChain(name = "x6920", ctr31).iter(48)
      var stage: List[Stage] = Nil
    }
    val x6967 = MetaPipeline(name="x6967",parent=x6968) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr33 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6933 = CounterChain(name = "x6933", ctr32, ctr33).iter(768)
    }
    val x6955_0 = Pipeline(name="x6955_0",parent=x6967) { implicit CU => 
      val x6943_x6943 =  VectorFIFO(size=1).wtPort(x6867_x6943_x6955_v)
      val x6942_x6942 =  VectorFIFO(size=1).wtPort(x6866_x6942_x6955_v)
      val ctr34 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x6936 = CounterChain(name = "x6936", ctr34).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6942_x6942), CU.load(x6943_x6943)), op=FixMul, results=List(CU.reduce))
      val (_, rr5086) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5086), op=Bypass, results=List(CU.scalarOut(x6934_x6953_s)))
    }
    val x6966_0 = Pipeline(name="x6966_0",parent=x6967) { implicit CU => 
      val x6962 = CU.temp
      val x6963 = CU.temp
      val x6934_x6961 =  ScalarBuffer().wtPort(x6934_x6953_s)
      val x6960_x6960 =  VectorFIFO(size=1).wtPort(x6537_x6960_x6966_v)
      val x6865 = CounterChain.copy("x6968", "x6865")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6865(0)), Const(0)), op=FixEql, results=List(x6962))
      Stage(operands=List(x6962, Const(0), CU.load(x6960_x6960)), op=Mux, results=List(x6963))
      Stage(operands=List(x6963, CU.load(x6934_x6961)), op=FixAdd, results=List(CU.vecOut(x6537_x6965_v)))
    }
    val x7074 = MetaPipeline(name="x7074",parent=x8153) { implicit CU => 
      val x6514_x6969 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr35 = Counter(min=Const(0), max=x6514_x6969.load, step=Const(48), par=1) // Counter
      val x6971 = CounterChain(name = "x6971", ctr35).iter(1)
    }
    val x6972_dsp0 = MemoryPipeline(name="x6972_dsp0",parent="x7074") { implicit CU => 
      val b8275 = CU.temp
      val b8267 = CU.temp
      val x7002_x7002 =  VectorFIFO(size=1).wtPort(x6977_x6993_data_v)
      val x7039 = CounterChain.copy("x7073", "x7039")
      val x6995 = CounterChain.copy("x7003", "x6995")
      val x6975 = CounterChain.copy("x7004", "x6975")
      val x7042 = CounterChain.copy("x7061_0", "x7042")
      val x6972_x7048 =  SRAM(size=768,banking = Strided(1)).wtPort(x7002_x7002.readPort).rdPort(x6972_x7048_x7061_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6975(0)), Const(48)), op=FixMul, results=List(b8267))
      WAStage(operands=List(b8267, CU.ctr(x6995(0))), op=FixAdd, results=List(x6972_x7048.writeAddr))
      RAStage(operands=List(CU.ctr(x7039(0)), Const(48)), op=FixMul, results=List(b8275))
      RAStage(operands=List(b8275, CU.ctr(x7042(0))), op=FixAdd, results=List(x6972_x7048.readAddr))
    }
    val x6973_dsp0 = MemoryPipeline(name="x6973_dsp0",parent="x7074") { implicit CU => 
      val b8277 = CU.temp
      val b8273 = CU.temp
      val x7033_x7033 =  VectorFIFO(size=1).wtPort(x7008_x7024_data_v)
      val x7039 = CounterChain.copy("x7073", "x7039")
      val x7026 = CounterChain.copy("x7034", "x7026")
      val x7042 = CounterChain.copy("x7061_0", "x7042")
      val x7006 = CounterChain.copy("x7035", "x7006")
      val x6973_x7049 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7033_x7033.readPort).rdPort(x6973_x7049_x7061_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7006(0)), Const(48)), op=FixMul, results=List(b8273))
      WAStage(operands=List(b8273, CU.ctr(x7026(0))), op=FixAdd, results=List(x6973_x7049.writeAddr))
      RAStage(operands=List(CU.ctr(x7042(0)), Const(48)), op=FixMul, results=List(b8277))
      RAStage(operands=List(b8277, CU.ctr(x7039(1))), op=FixAdd, results=List(x6973_x7049.readAddr))
    }
    val x7004 = StreamController(name="x7004",parent=x7074) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x6975 = CounterChain(name = "x6975", ctr36).iter(16)
    }
    val x6992_0 = Pipeline(name="x6992_0",parent=x7004) { implicit CU => 
      val x6983 = CU.temp
      val x6980 = CU.temp
      val x6982 = CU.temp
      val x6981 = CU.temp
      val x6978 =  ScalarBuffer().wtPort(x6978_argin)
      val x6514_x6979 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x6975 = CounterChain.copy("x7004", "x6975")
      val x6971 = CounterChain.copy("x7074", "x6971")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x6975(0))), op=FixAdd, results=List(x6980))
      Stage(operands=List(x6980, CU.load(x6514_x6979)), op=FixMul, results=List(x6981))
      Stage(operands=List(x6981, CU.ctr(x6971(0))), op=FixAdd, results=List(x6982))
      Stage(operands=List(x6982, Const(4)), op=FixMul, results=List(x6983))
      Stage(operands=List(x6983, CU.load(x6978)), op=FixAdd, results=List(CU.scalarOut(x6976_b8263_x6991_b8265_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x6976_b8264_x6991_b8266_s)))
    }
    val x6993 = MemoryController(name="x6993",parent=x7004,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x6976_b8264_x6993 =  ScalarFIFO(name="size",size=1).wtPort(x6976_b8264_x6991_b8266_s)
      val x6976_b8263_x6993 =  ScalarFIFO(name="offset",size=1).wtPort(x6976_b8263_x6991_b8265_s)
      CU.newVout("data", x6977_x6993_data_v)
    }
    val x7003 = Pipeline(name="x7003",parent=x7004) { implicit CU => 
      val ctr37 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x6995 = CounterChain(name = "x6995", ctr37).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7035 = StreamController(name="x7035",parent=x7074) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7006 = CounterChain(name = "x7006", ctr38).iter(48)
    }
    val x7023_0 = Pipeline(name="x7023_0",parent=x7035) { implicit CU => 
      val x7014 = CU.temp
      val x7013 = CU.temp
      val x7012 = CU.temp
      val x7011 = CU.temp
      val x7009 =  ScalarBuffer().wtPort(x7009_argin)
      val x6513_x7010 =  ScalarBuffer().wtPort(x6513_argin)
      val x6971 = CounterChain.copy("x7074", "x6971")
      val x7006 = CounterChain.copy("x7035", "x7006")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6971(0)), CU.ctr(x7006(0))), op=FixAdd, results=List(x7011))
      Stage(operands=List(x7011, CU.load(x6513_x7010)), op=FixMul, results=List(x7012))
      Stage(operands=List(x7012, CU.ctr(x6533(1))), op=FixAdd, results=List(x7013))
      Stage(operands=List(x7013, Const(4)), op=FixMul, results=List(x7014))
      Stage(operands=List(x7014, CU.load(x7009)), op=FixAdd, results=List(CU.scalarOut(x7007_b8269_x7022_b8271_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7007_b8270_x7022_b8272_s)))
    }
    val x7024 = MemoryController(name="x7024",parent=x7035,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7007_b8270_x7024 =  ScalarFIFO(name="size",size=1).wtPort(x7007_b8270_x7022_b8272_s)
      val x7007_b8269_x7024 =  ScalarFIFO(name="offset",size=1).wtPort(x7007_b8269_x7022_b8271_s)
      CU.newVout("data", x7008_x7024_data_v)
    }
    val x7034 = Pipeline(name="x7034",parent=x7035) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7026 = CounterChain(name = "x7026", ctr39).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7073 = MetaPipeline(name="x7073",parent=x7074) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr41 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7039 = CounterChain(name = "x7039", ctr40, ctr41).iter(768)
    }
    val x7061_0 = Pipeline(name="x7061_0",parent=x7073) { implicit CU => 
      val x7048_x7048 =  VectorFIFO(size=1).wtPort(x6972_x7048_x7061_v)
      val x7049_x7049 =  VectorFIFO(size=1).wtPort(x6973_x7049_x7061_v)
      val ctr42 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7042 = CounterChain(name = "x7042", ctr42).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7048_x7048), CU.load(x7049_x7049)), op=FixMul, results=List(CU.reduce))
      val (_, rr5137) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5137), op=Bypass, results=List(CU.scalarOut(x7040_x7059_s)))
    }
    val x7072_0 = Pipeline(name="x7072_0",parent=x7073) { implicit CU => 
      val x7069 = CU.temp
      val x7068 = CU.temp
      val x7066_x7066 =  VectorFIFO(size=1).wtPort(x6538_x7066_x7072_v)
      val x7040_x7067 =  ScalarBuffer().wtPort(x7040_x7059_s)
      val x6971 = CounterChain.copy("x7074", "x6971")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6971(0)), Const(0)), op=FixEql, results=List(x7068))
      Stage(operands=List(x7068, Const(0), CU.load(x7066_x7066)), op=Mux, results=List(x7069))
      Stage(operands=List(x7069, CU.load(x7040_x7067)), op=FixAdd, results=List(CU.vecOut(x6538_x7071_v)))
    }
    val x7180 = MetaPipeline(name="x7180",parent=x8153) { implicit CU => 
      val x6514_x7075 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr43 = Counter(min=Const(0), max=x6514_x7075.load, step=Const(48), par=1) // Counter
      val x7077 = CounterChain(name = "x7077", ctr43).iter(1)
    }
    val x7078_dsp0 = MemoryPipeline(name="x7078_dsp0",parent="x7180") { implicit CU => 
      val b8287 = CU.temp
      val b8295 = CU.temp
      val x7108_x7108 =  VectorFIFO(size=1).wtPort(x7083_x7099_data_v)
      val x7081 = CounterChain.copy("x7110", "x7081")
      val x7148 = CounterChain.copy("x7167_0", "x7148")
      val x7101 = CounterChain.copy("x7109", "x7101")
      val x7145 = CounterChain.copy("x7179", "x7145")
      val x7078_x7154 =  SRAM(size=768,banking = Strided(1)).wtPort(x7108_x7108.readPort).rdPort(x7078_x7154_x7167_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7081(0)), Const(48)), op=FixMul, results=List(b8287))
      WAStage(operands=List(b8287, CU.ctr(x7101(0))), op=FixAdd, results=List(x7078_x7154.writeAddr))
      RAStage(operands=List(CU.ctr(x7145(0)), Const(48)), op=FixMul, results=List(b8295))
      RAStage(operands=List(b8295, CU.ctr(x7148(0))), op=FixAdd, results=List(x7078_x7154.readAddr))
    }
    val x7079_dsp0 = MemoryPipeline(name="x7079_dsp0",parent="x7180") { implicit CU => 
      val b8297 = CU.temp
      val b8293 = CU.temp
      val x7139_x7139 =  VectorFIFO(size=1).wtPort(x7114_x7130_data_v)
      val x7132 = CounterChain.copy("x7140", "x7132")
      val x7112 = CounterChain.copy("x7141", "x7112")
      val x7148 = CounterChain.copy("x7167_0", "x7148")
      val x7145 = CounterChain.copy("x7179", "x7145")
      val x7079_x7155 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7139_x7139.readPort).rdPort(x7079_x7155_x7167_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7112(0)), Const(48)), op=FixMul, results=List(b8293))
      WAStage(operands=List(b8293, CU.ctr(x7132(0))), op=FixAdd, results=List(x7079_x7155.writeAddr))
      RAStage(operands=List(CU.ctr(x7148(0)), Const(48)), op=FixMul, results=List(b8297))
      RAStage(operands=List(b8297, CU.ctr(x7145(1))), op=FixAdd, results=List(x7079_x7155.readAddr))
    }
    val x7110 = StreamController(name="x7110",parent=x7180) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7081 = CounterChain(name = "x7081", ctr44).iter(16)
    }
    val x7098_0 = Pipeline(name="x7098_0",parent=x7110) { implicit CU => 
      val x7089 = CU.temp
      val x7086 = CU.temp
      val x7088 = CU.temp
      val x7087 = CU.temp
      val x7084 =  ScalarBuffer().wtPort(x7084_argin)
      val x6514_x7085 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7081 = CounterChain.copy("x7110", "x7081")
      val x7077 = CounterChain.copy("x7180", "x7077")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7081(0))), op=FixAdd, results=List(x7086))
      Stage(operands=List(x7086, CU.load(x6514_x7085)), op=FixMul, results=List(x7087))
      Stage(operands=List(x7087, CU.ctr(x7077(0))), op=FixAdd, results=List(x7088))
      Stage(operands=List(x7088, Const(4)), op=FixMul, results=List(x7089))
      Stage(operands=List(x7089, CU.load(x7084)), op=FixAdd, results=List(CU.scalarOut(x7082_b8283_x7097_b8285_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7082_b8284_x7097_b8286_s)))
    }
    val x7099 = MemoryController(name="x7099",parent=x7110,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7082_b8283_x7099 =  ScalarFIFO(name="offset",size=1).wtPort(x7082_b8283_x7097_b8285_s)
      val x7082_b8284_x7099 =  ScalarFIFO(name="size",size=1).wtPort(x7082_b8284_x7097_b8286_s)
      CU.newVout("data", x7083_x7099_data_v)
    }
    val x7109 = Pipeline(name="x7109",parent=x7110) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7101 = CounterChain(name = "x7101", ctr45).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7141 = StreamController(name="x7141",parent=x7180) { implicit CU => 
      val ctr46 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7112 = CounterChain(name = "x7112", ctr46).iter(48)
    }
    val x7129_0 = Pipeline(name="x7129_0",parent=x7141) { implicit CU => 
      val x7118 = CU.temp
      val x7117 = CU.temp
      val x7120 = CU.temp
      val x7119 = CU.temp
      val x7115 =  ScalarBuffer().wtPort(x7115_argin)
      val x6513_x7116 =  ScalarBuffer().wtPort(x6513_argin)
      val x7077 = CounterChain.copy("x7180", "x7077")
      val x7112 = CounterChain.copy("x7141", "x7112")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7077(0)), CU.ctr(x7112(0))), op=FixAdd, results=List(x7117))
      Stage(operands=List(x7117, CU.load(x6513_x7116)), op=FixMul, results=List(x7118))
      Stage(operands=List(x7118, CU.ctr(x6533(1))), op=FixAdd, results=List(x7119))
      Stage(operands=List(x7119, Const(4)), op=FixMul, results=List(x7120))
      Stage(operands=List(x7120, CU.load(x7115)), op=FixAdd, results=List(CU.scalarOut(x7113_b8289_x7128_b8291_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7113_b8290_x7128_b8292_s)))
    }
    val x7130 = MemoryController(name="x7130",parent=x7141,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7113_b8289_x7130 =  ScalarFIFO(name="offset",size=1).wtPort(x7113_b8289_x7128_b8291_s)
      val x7113_b8290_x7130 =  ScalarFIFO(name="size",size=1).wtPort(x7113_b8290_x7128_b8292_s)
      CU.newVout("data", x7114_x7130_data_v)
    }
    val x7140 = Pipeline(name="x7140",parent=x7141) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7132 = CounterChain(name = "x7132", ctr47).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7179 = MetaPipeline(name="x7179",parent=x7180) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr49 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7145 = CounterChain(name = "x7145", ctr48, ctr49).iter(768)
    }
    val x7167_0 = Pipeline(name="x7167_0",parent=x7179) { implicit CU => 
      val x7155_x7155 =  VectorFIFO(size=1).wtPort(x7079_x7155_x7167_v)
      val x7154_x7154 =  VectorFIFO(size=1).wtPort(x7078_x7154_x7167_v)
      val ctr50 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7148 = CounterChain(name = "x7148", ctr50).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7154_x7154), CU.load(x7155_x7155)), op=FixMul, results=List(CU.reduce))
      val (_, rr5188) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5188), op=Bypass, results=List(CU.scalarOut(x7146_x7165_s)))
    }
    val x7178_0 = Pipeline(name="x7178_0",parent=x7179) { implicit CU => 
      val x7174 = CU.temp
      val x7175 = CU.temp
      val x7146_x7173 =  ScalarBuffer().wtPort(x7146_x7165_s)
      val x7172_x7172 =  VectorFIFO(size=1).wtPort(x6539_x7172_x7178_v)
      val x7077 = CounterChain.copy("x7180", "x7077")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7077(0)), Const(0)), op=FixEql, results=List(x7174))
      Stage(operands=List(x7174, Const(0), CU.load(x7172_x7172)), op=Mux, results=List(x7175))
      Stage(operands=List(x7175, CU.load(x7146_x7173)), op=FixAdd, results=List(CU.vecOut(x6539_x7177_v)))
    }
    val x7286 = MetaPipeline(name="x7286",parent=x8153) { implicit CU => 
      val x6514_x7181 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr51 = Counter(min=Const(0), max=x6514_x7181.load, step=Const(48), par=1) // Counter
      val x7183 = CounterChain(name = "x7183", ctr51).iter(1)
    }
    val x7184_dsp0 = MemoryPipeline(name="x7184_dsp0",parent="x7286") { implicit CU => 
      val b8307 = CU.temp
      val b8315 = CU.temp
      val x7214_x7214 =  VectorFIFO(size=1).wtPort(x7189_x7205_data_v)
      val x7251 = CounterChain.copy("x7285", "x7251")
      val x7254 = CounterChain.copy("x7273_0", "x7254")
      val x7207 = CounterChain.copy("x7215", "x7207")
      val x7187 = CounterChain.copy("x7216", "x7187")
      val x7184_x7260 =  SRAM(size=768,banking = Strided(1)).wtPort(x7214_x7214.readPort).rdPort(x7184_x7260_x7273_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7187(0)), Const(48)), op=FixMul, results=List(b8307))
      WAStage(operands=List(b8307, CU.ctr(x7207(0))), op=FixAdd, results=List(x7184_x7260.writeAddr))
      RAStage(operands=List(CU.ctr(x7251(0)), Const(48)), op=FixMul, results=List(b8315))
      RAStage(operands=List(b8315, CU.ctr(x7254(0))), op=FixAdd, results=List(x7184_x7260.readAddr))
    }
    val x7185_dsp0 = MemoryPipeline(name="x7185_dsp0",parent="x7286") { implicit CU => 
      val b8317 = CU.temp
      val b8313 = CU.temp
      val x7245_x7245 =  VectorFIFO(size=1).wtPort(x7220_x7236_data_v)
      val x7218 = CounterChain.copy("x7247", "x7218")
      val x7251 = CounterChain.copy("x7285", "x7251")
      val x7238 = CounterChain.copy("x7246", "x7238")
      val x7254 = CounterChain.copy("x7273_0", "x7254")
      val x7185_x7261 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7245_x7245.readPort).rdPort(x7185_x7261_x7273_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7218(0)), Const(48)), op=FixMul, results=List(b8313))
      WAStage(operands=List(b8313, CU.ctr(x7238(0))), op=FixAdd, results=List(x7185_x7261.writeAddr))
      RAStage(operands=List(CU.ctr(x7254(0)), Const(48)), op=FixMul, results=List(b8317))
      RAStage(operands=List(b8317, CU.ctr(x7251(1))), op=FixAdd, results=List(x7185_x7261.readAddr))
    }
    val x7216 = StreamController(name="x7216",parent=x7286) { implicit CU => 
      val ctr52 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7187 = CounterChain(name = "x7187", ctr52).iter(16)
    }
    val x7204_0 = Pipeline(name="x7204_0",parent=x7216) { implicit CU => 
      val x7192 = CU.temp
      val x7195 = CU.temp
      val x7193 = CU.temp
      val x7194 = CU.temp
      val x7190 =  ScalarBuffer().wtPort(x7190_argin)
      val x6514_x7191 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7187 = CounterChain.copy("x7216", "x7187")
      val x7183 = CounterChain.copy("x7286", "x7183")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7187(0))), op=FixAdd, results=List(x7192))
      Stage(operands=List(x7192, CU.load(x6514_x7191)), op=FixMul, results=List(x7193))
      Stage(operands=List(x7193, CU.ctr(x7183(0))), op=FixAdd, results=List(x7194))
      Stage(operands=List(x7194, Const(4)), op=FixMul, results=List(x7195))
      Stage(operands=List(x7195, CU.load(x7190)), op=FixAdd, results=List(CU.scalarOut(x7188_b8303_x7203_b8305_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7188_b8304_x7203_b8306_s)))
    }
    val x7205 = MemoryController(name="x7205",parent=x7216,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7188_b8304_x7205 =  ScalarFIFO(name="size",size=1).wtPort(x7188_b8304_x7203_b8306_s)
      val x7188_b8303_x7205 =  ScalarFIFO(name="offset",size=1).wtPort(x7188_b8303_x7203_b8305_s)
      CU.newVout("data", x7189_x7205_data_v)
    }
    val x7215 = Pipeline(name="x7215",parent=x7216) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7207 = CounterChain(name = "x7207", ctr53).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7247 = StreamController(name="x7247",parent=x7286) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7218 = CounterChain(name = "x7218", ctr54).iter(48)
    }
    val x7235_0 = Pipeline(name="x7235_0",parent=x7247) { implicit CU => 
      val x7223 = CU.temp
      val x7224 = CU.temp
      val x7225 = CU.temp
      val x7226 = CU.temp
      val x7221 =  ScalarBuffer().wtPort(x7221_argin)
      val x6513_x7222 =  ScalarBuffer().wtPort(x6513_argin)
      val x7183 = CounterChain.copy("x7286", "x7183")
      val x7218 = CounterChain.copy("x7247", "x7218")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7183(0)), CU.ctr(x7218(0))), op=FixAdd, results=List(x7223))
      Stage(operands=List(x7223, CU.load(x6513_x7222)), op=FixMul, results=List(x7224))
      Stage(operands=List(x7224, CU.ctr(x6533(1))), op=FixAdd, results=List(x7225))
      Stage(operands=List(x7225, Const(4)), op=FixMul, results=List(x7226))
      Stage(operands=List(x7226, CU.load(x7221)), op=FixAdd, results=List(CU.scalarOut(x7219_b8309_x7234_b8311_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7219_b8310_x7234_b8312_s)))
    }
    val x7236 = MemoryController(name="x7236",parent=x7247,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7219_b8310_x7236 =  ScalarFIFO(name="size",size=1).wtPort(x7219_b8310_x7234_b8312_s)
      val x7219_b8309_x7236 =  ScalarFIFO(name="offset",size=1).wtPort(x7219_b8309_x7234_b8311_s)
      CU.newVout("data", x7220_x7236_data_v)
    }
    val x7246 = Pipeline(name="x7246",parent=x7247) { implicit CU => 
      val ctr55 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7238 = CounterChain(name = "x7238", ctr55).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7285 = MetaPipeline(name="x7285",parent=x7286) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr57 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7251 = CounterChain(name = "x7251", ctr56, ctr57).iter(768)
    }
    val x7273_0 = Pipeline(name="x7273_0",parent=x7285) { implicit CU => 
      val x7261_x7261 =  VectorFIFO(size=1).wtPort(x7185_x7261_x7273_v)
      val x7260_x7260 =  VectorFIFO(size=1).wtPort(x7184_x7260_x7273_v)
      val ctr58 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7254 = CounterChain(name = "x7254", ctr58).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7260_x7260), CU.load(x7261_x7261)), op=FixMul, results=List(CU.reduce))
      val (_, rr5239) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5239), op=Bypass, results=List(CU.scalarOut(x7252_x7271_s)))
    }
    val x7284_0 = Pipeline(name="x7284_0",parent=x7285) { implicit CU => 
      val x7281 = CU.temp
      val x7280 = CU.temp
      val x7252_x7279 =  ScalarBuffer().wtPort(x7252_x7271_s)
      val x7278_x7278 =  VectorFIFO(size=1).wtPort(x6540_x7278_x7284_v)
      val x7183 = CounterChain.copy("x7286", "x7183")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7183(0)), Const(0)), op=FixEql, results=List(x7280))
      Stage(operands=List(x7280, Const(0), CU.load(x7278_x7278)), op=Mux, results=List(x7281))
      Stage(operands=List(x7281, CU.load(x7252_x7279)), op=FixAdd, results=List(CU.vecOut(x6540_x7283_v)))
    }
    val x7392 = MetaPipeline(name="x7392",parent=x8153) { implicit CU => 
      val x6514_x7287 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr59 = Counter(min=Const(0), max=x6514_x7287.load, step=Const(48), par=1) // Counter
      val x7289 = CounterChain(name = "x7289", ctr59).iter(1)
    }
    val x7290_dsp0 = MemoryPipeline(name="x7290_dsp0",parent="x7392") { implicit CU => 
      val b8327 = CU.temp
      val b8335 = CU.temp
      val x7320_x7320 =  VectorFIFO(size=1).wtPort(x7295_x7311_data_v)
      val x7313 = CounterChain.copy("x7321", "x7313")
      val x7357 = CounterChain.copy("x7391", "x7357")
      val x7293 = CounterChain.copy("x7322", "x7293")
      val x7360 = CounterChain.copy("x7379_0", "x7360")
      val x7290_x7366 =  SRAM(size=768,banking = Strided(1)).wtPort(x7320_x7320.readPort).rdPort(x7290_x7366_x7379_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7293(0)), Const(48)), op=FixMul, results=List(b8327))
      WAStage(operands=List(b8327, CU.ctr(x7313(0))), op=FixAdd, results=List(x7290_x7366.writeAddr))
      RAStage(operands=List(CU.ctr(x7357(0)), Const(48)), op=FixMul, results=List(b8335))
      RAStage(operands=List(b8335, CU.ctr(x7360(0))), op=FixAdd, results=List(x7290_x7366.readAddr))
    }
    val x7291_dsp0 = MemoryPipeline(name="x7291_dsp0",parent="x7392") { implicit CU => 
      val b8337 = CU.temp
      val b8333 = CU.temp
      val x7351_x7351 =  VectorFIFO(size=1).wtPort(x7326_x7342_data_v)
      val x7324 = CounterChain.copy("x7353", "x7324")
      val x7344 = CounterChain.copy("x7352", "x7344")
      val x7357 = CounterChain.copy("x7391", "x7357")
      val x7360 = CounterChain.copy("x7379_0", "x7360")
      val x7291_x7367 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7351_x7351.readPort).rdPort(x7291_x7367_x7379_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7324(0)), Const(48)), op=FixMul, results=List(b8333))
      WAStage(operands=List(b8333, CU.ctr(x7344(0))), op=FixAdd, results=List(x7291_x7367.writeAddr))
      RAStage(operands=List(CU.ctr(x7360(0)), Const(48)), op=FixMul, results=List(b8337))
      RAStage(operands=List(b8337, CU.ctr(x7357(1))), op=FixAdd, results=List(x7291_x7367.readAddr))
    }
    val x7322 = StreamController(name="x7322",parent=x7392) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7293 = CounterChain(name = "x7293", ctr60).iter(16)
    }
    val x7310_0 = Pipeline(name="x7310_0",parent=x7322) { implicit CU => 
      val x7299 = CU.temp
      val x7301 = CU.temp
      val x7300 = CU.temp
      val x7298 = CU.temp
      val x7296 =  ScalarBuffer().wtPort(x7296_argin)
      val x6514_x7297 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7293 = CounterChain.copy("x7322", "x7293")
      val x7289 = CounterChain.copy("x7392", "x7289")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7293(0))), op=FixAdd, results=List(x7298))
      Stage(operands=List(x7298, CU.load(x6514_x7297)), op=FixMul, results=List(x7299))
      Stage(operands=List(x7299, CU.ctr(x7289(0))), op=FixAdd, results=List(x7300))
      Stage(operands=List(x7300, Const(4)), op=FixMul, results=List(x7301))
      Stage(operands=List(x7301, CU.load(x7296)), op=FixAdd, results=List(CU.scalarOut(x7294_b8323_x7309_b8325_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7294_b8324_x7309_b8326_s)))
    }
    val x7311 = MemoryController(name="x7311",parent=x7322,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7294_b8324_x7311 =  ScalarFIFO(name="size",size=1).wtPort(x7294_b8324_x7309_b8326_s)
      val x7294_b8323_x7311 =  ScalarFIFO(name="offset",size=1).wtPort(x7294_b8323_x7309_b8325_s)
      CU.newVout("data", x7295_x7311_data_v)
    }
    val x7321 = Pipeline(name="x7321",parent=x7322) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7313 = CounterChain(name = "x7313", ctr61).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7353 = StreamController(name="x7353",parent=x7392) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7324 = CounterChain(name = "x7324", ctr62).iter(48)
    }
    val x7341_0 = Pipeline(name="x7341_0",parent=x7353) { implicit CU => 
      val x7332 = CU.temp
      val x7329 = CU.temp
      val x7331 = CU.temp
      val x7330 = CU.temp
      val x7327 =  ScalarBuffer().wtPort(x7327_argin)
      val x6513_x7328 =  ScalarBuffer().wtPort(x6513_argin)
      val x7289 = CounterChain.copy("x7392", "x7289")
      val x7324 = CounterChain.copy("x7353", "x7324")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7289(0)), CU.ctr(x7324(0))), op=FixAdd, results=List(x7329))
      Stage(operands=List(x7329, CU.load(x6513_x7328)), op=FixMul, results=List(x7330))
      Stage(operands=List(x7330, CU.ctr(x6533(1))), op=FixAdd, results=List(x7331))
      Stage(operands=List(x7331, Const(4)), op=FixMul, results=List(x7332))
      Stage(operands=List(x7332, CU.load(x7327)), op=FixAdd, results=List(CU.scalarOut(x7325_b8329_x7340_b8331_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7325_b8330_x7340_b8332_s)))
    }
    val x7342 = MemoryController(name="x7342",parent=x7353,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7325_b8330_x7342 =  ScalarFIFO(name="size",size=1).wtPort(x7325_b8330_x7340_b8332_s)
      val x7325_b8329_x7342 =  ScalarFIFO(name="offset",size=1).wtPort(x7325_b8329_x7340_b8331_s)
      CU.newVout("data", x7326_x7342_data_v)
    }
    val x7352 = Pipeline(name="x7352",parent=x7353) { implicit CU => 
      val ctr63 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7344 = CounterChain(name = "x7344", ctr63).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7391 = MetaPipeline(name="x7391",parent=x7392) { implicit CU => 
      val ctr64 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr65 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7357 = CounterChain(name = "x7357", ctr64, ctr65).iter(768)
    }
    val x7379_0 = Pipeline(name="x7379_0",parent=x7391) { implicit CU => 
      val x7367_x7367 =  VectorFIFO(size=1).wtPort(x7291_x7367_x7379_v)
      val x7366_x7366 =  VectorFIFO(size=1).wtPort(x7290_x7366_x7379_v)
      val ctr66 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7360 = CounterChain(name = "x7360", ctr66).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7366_x7366), CU.load(x7367_x7367)), op=FixMul, results=List(CU.reduce))
      val (_, rr5290) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5290), op=Bypass, results=List(CU.scalarOut(x7358_x7377_s)))
    }
    val x7390_0 = Pipeline(name="x7390_0",parent=x7391) { implicit CU => 
      val x7387 = CU.temp
      val x7386 = CU.temp
      val x7358_x7385 =  ScalarBuffer().wtPort(x7358_x7377_s)
      val x7384_x7384 =  VectorFIFO(size=1).wtPort(x6541_x7384_x7390_v)
      val x7289 = CounterChain.copy("x7392", "x7289")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7289(0)), Const(0)), op=FixEql, results=List(x7386))
      Stage(operands=List(x7386, Const(0), CU.load(x7384_x7384)), op=Mux, results=List(x7387))
      Stage(operands=List(x7387, CU.load(x7358_x7385)), op=FixAdd, results=List(CU.vecOut(x6541_x7389_v)))
    }
    val x7498 = MetaPipeline(name="x7498",parent=x8153) { implicit CU => 
      val x6514_x7393 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr67 = Counter(min=Const(0), max=x6514_x7393.load, step=Const(48), par=1) // Counter
      val x7395 = CounterChain(name = "x7395", ctr67).iter(1)
    }
    val x7396_dsp0 = MemoryPipeline(name="x7396_dsp0",parent="x7498") { implicit CU => 
      val b8355 = CU.temp
      val b8347 = CU.temp
      val x7426_x7426 =  VectorFIFO(size=1).wtPort(x7401_x7417_data_v)
      val x7399 = CounterChain.copy("x7428", "x7399")
      val x7466 = CounterChain.copy("x7485_0", "x7466")
      val x7419 = CounterChain.copy("x7427", "x7419")
      val x7463 = CounterChain.copy("x7497", "x7463")
      val x7396_x7472 =  SRAM(size=768,banking = Strided(1)).wtPort(x7426_x7426.readPort).rdPort(x7396_x7472_x7485_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7399(0)), Const(48)), op=FixMul, results=List(b8347))
      WAStage(operands=List(b8347, CU.ctr(x7419(0))), op=FixAdd, results=List(x7396_x7472.writeAddr))
      RAStage(operands=List(CU.ctr(x7463(0)), Const(48)), op=FixMul, results=List(b8355))
      RAStage(operands=List(b8355, CU.ctr(x7466(0))), op=FixAdd, results=List(x7396_x7472.readAddr))
    }
    val x7397_dsp0 = MemoryPipeline(name="x7397_dsp0",parent="x7498") { implicit CU => 
      val b8357 = CU.temp
      val b8353 = CU.temp
      val x7457_x7457 =  VectorFIFO(size=1).wtPort(x7432_x7448_data_v)
      val x7450 = CounterChain.copy("x7458", "x7450")
      val x7466 = CounterChain.copy("x7485_0", "x7466")
      val x7430 = CounterChain.copy("x7459", "x7430")
      val x7463 = CounterChain.copy("x7497", "x7463")
      val x7397_x7473 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7457_x7457.readPort).rdPort(x7397_x7473_x7485_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7430(0)), Const(48)), op=FixMul, results=List(b8353))
      WAStage(operands=List(b8353, CU.ctr(x7450(0))), op=FixAdd, results=List(x7397_x7473.writeAddr))
      RAStage(operands=List(CU.ctr(x7466(0)), Const(48)), op=FixMul, results=List(b8357))
      RAStage(operands=List(b8357, CU.ctr(x7463(1))), op=FixAdd, results=List(x7397_x7473.readAddr))
    }
    val x7428 = StreamController(name="x7428",parent=x7498) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7399 = CounterChain(name = "x7399", ctr68).iter(16)
    }
    val x7416_0 = Pipeline(name="x7416_0",parent=x7428) { implicit CU => 
      val x7404 = CU.temp
      val x7405 = CU.temp
      val x7406 = CU.temp
      val x7407 = CU.temp
      val x7402 =  ScalarBuffer().wtPort(x7402_argin)
      val x6514_x7403 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7399 = CounterChain.copy("x7428", "x7399")
      val x7395 = CounterChain.copy("x7498", "x7395")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7399(0))), op=FixAdd, results=List(x7404))
      Stage(operands=List(x7404, CU.load(x6514_x7403)), op=FixMul, results=List(x7405))
      Stage(operands=List(x7405, CU.ctr(x7395(0))), op=FixAdd, results=List(x7406))
      Stage(operands=List(x7406, Const(4)), op=FixMul, results=List(x7407))
      Stage(operands=List(x7407, CU.load(x7402)), op=FixAdd, results=List(CU.scalarOut(x7400_b8343_x7415_b8345_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7400_b8344_x7415_b8346_s)))
    }
    val x7417 = MemoryController(name="x7417",parent=x7428,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7400_b8343_x7417 =  ScalarFIFO(name="offset",size=1).wtPort(x7400_b8343_x7415_b8345_s)
      val x7400_b8344_x7417 =  ScalarFIFO(name="size",size=1).wtPort(x7400_b8344_x7415_b8346_s)
      CU.newVout("data", x7401_x7417_data_v)
    }
    val x7427 = Pipeline(name="x7427",parent=x7428) { implicit CU => 
      val ctr69 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7419 = CounterChain(name = "x7419", ctr69).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7459 = StreamController(name="x7459",parent=x7498) { implicit CU => 
      val ctr70 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7430 = CounterChain(name = "x7430", ctr70).iter(48)
    }
    val x7447_0 = Pipeline(name="x7447_0",parent=x7459) { implicit CU => 
      val x7435 = CU.temp
      val x7437 = CU.temp
      val x7438 = CU.temp
      val x7436 = CU.temp
      val x7433 =  ScalarBuffer().wtPort(x7433_argin)
      val x6513_x7434 =  ScalarBuffer().wtPort(x6513_argin)
      val x7395 = CounterChain.copy("x7498", "x7395")
      val x7430 = CounterChain.copy("x7459", "x7430")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7395(0)), CU.ctr(x7430(0))), op=FixAdd, results=List(x7435))
      Stage(operands=List(x7435, CU.load(x6513_x7434)), op=FixMul, results=List(x7436))
      Stage(operands=List(x7436, CU.ctr(x6533(1))), op=FixAdd, results=List(x7437))
      Stage(operands=List(x7437, Const(4)), op=FixMul, results=List(x7438))
      Stage(operands=List(x7438, CU.load(x7433)), op=FixAdd, results=List(CU.scalarOut(x7431_b8349_x7446_b8351_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7431_b8350_x7446_b8352_s)))
    }
    val x7448 = MemoryController(name="x7448",parent=x7459,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7431_b8349_x7448 =  ScalarFIFO(name="offset",size=1).wtPort(x7431_b8349_x7446_b8351_s)
      val x7431_b8350_x7448 =  ScalarFIFO(name="size",size=1).wtPort(x7431_b8350_x7446_b8352_s)
      CU.newVout("data", x7432_x7448_data_v)
    }
    val x7458 = Pipeline(name="x7458",parent=x7459) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7450 = CounterChain(name = "x7450", ctr71).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7497 = MetaPipeline(name="x7497",parent=x7498) { implicit CU => 
      val ctr72 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr73 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7463 = CounterChain(name = "x7463", ctr72, ctr73).iter(768)
    }
    val x7485_0 = Pipeline(name="x7485_0",parent=x7497) { implicit CU => 
      val x7472_x7472 =  VectorFIFO(size=1).wtPort(x7396_x7472_x7485_v)
      val x7473_x7473 =  VectorFIFO(size=1).wtPort(x7397_x7473_x7485_v)
      val ctr74 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7466 = CounterChain(name = "x7466", ctr74).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7472_x7472), CU.load(x7473_x7473)), op=FixMul, results=List(CU.reduce))
      val (_, rr5341) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5341), op=Bypass, results=List(CU.scalarOut(x7464_x7483_s)))
    }
    val x7496_0 = Pipeline(name="x7496_0",parent=x7497) { implicit CU => 
      val x7492 = CU.temp
      val x7493 = CU.temp
      val x7464_x7491 =  ScalarBuffer().wtPort(x7464_x7483_s)
      val x7490_x7490 =  VectorFIFO(size=1).wtPort(x6542_x7490_x7496_v)
      val x7395 = CounterChain.copy("x7498", "x7395")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7395(0)), Const(0)), op=FixEql, results=List(x7492))
      Stage(operands=List(x7492, Const(0), CU.load(x7490_x7490)), op=Mux, results=List(x7493))
      Stage(operands=List(x7493, CU.load(x7464_x7491)), op=FixAdd, results=List(CU.vecOut(x6542_x7495_v)))
    }
    val x7604 = MetaPipeline(name="x7604",parent=x8153) { implicit CU => 
      val x6514_x7499 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr75 = Counter(min=Const(0), max=x6514_x7499.load, step=Const(48), par=1) // Counter
      val x7501 = CounterChain(name = "x7501", ctr75).iter(1)
    }
    val x7502_dsp0 = MemoryPipeline(name="x7502_dsp0",parent="x7604") { implicit CU => 
      val b8375 = CU.temp
      val b8367 = CU.temp
      val x7532_x7532 =  VectorFIFO(size=1).wtPort(x7507_x7523_data_v)
      val x7525 = CounterChain.copy("x7533", "x7525")
      val x7505 = CounterChain.copy("x7534", "x7505")
      val x7569 = CounterChain.copy("x7603", "x7569")
      val x7572 = CounterChain.copy("x7591_0", "x7572")
      val x7502_x7578 =  SRAM(size=768,banking = Strided(1)).wtPort(x7532_x7532.readPort).rdPort(x7502_x7578_x7591_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7505(0)), Const(48)), op=FixMul, results=List(b8367))
      WAStage(operands=List(b8367, CU.ctr(x7525(0))), op=FixAdd, results=List(x7502_x7578.writeAddr))
      RAStage(operands=List(CU.ctr(x7569(0)), Const(48)), op=FixMul, results=List(b8375))
      RAStage(operands=List(b8375, CU.ctr(x7572(0))), op=FixAdd, results=List(x7502_x7578.readAddr))
    }
    val x7503_dsp0 = MemoryPipeline(name="x7503_dsp0",parent="x7604") { implicit CU => 
      val b8373 = CU.temp
      val b8377 = CU.temp
      val x7563_x7563 =  VectorFIFO(size=1).wtPort(x7538_x7554_data_v)
      val x7536 = CounterChain.copy("x7565", "x7536")
      val x7569 = CounterChain.copy("x7603", "x7569")
      val x7572 = CounterChain.copy("x7591_0", "x7572")
      val x7556 = CounterChain.copy("x7564", "x7556")
      val x7503_x7579 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7563_x7563.readPort).rdPort(x7503_x7579_x7591_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7536(0)), Const(48)), op=FixMul, results=List(b8373))
      WAStage(operands=List(b8373, CU.ctr(x7556(0))), op=FixAdd, results=List(x7503_x7579.writeAddr))
      RAStage(operands=List(CU.ctr(x7572(0)), Const(48)), op=FixMul, results=List(b8377))
      RAStage(operands=List(b8377, CU.ctr(x7569(1))), op=FixAdd, results=List(x7503_x7579.readAddr))
    }
    val x7534 = StreamController(name="x7534",parent=x7604) { implicit CU => 
      val ctr76 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7505 = CounterChain(name = "x7505", ctr76).iter(16)
    }
    val x7522_0 = Pipeline(name="x7522_0",parent=x7534) { implicit CU => 
      val x7512 = CU.temp
      val x7510 = CU.temp
      val x7513 = CU.temp
      val x7511 = CU.temp
      val x7508 =  ScalarBuffer().wtPort(x7508_argin)
      val x6514_x7509 =  ScalarBuffer().wtPort(x6514_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7505 = CounterChain.copy("x7534", "x7505")
      val x7501 = CounterChain.copy("x7604", "x7501")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7505(0))), op=FixAdd, results=List(x7510))
      Stage(operands=List(x7510, CU.load(x6514_x7509)), op=FixMul, results=List(x7511))
      Stage(operands=List(x7511, CU.ctr(x7501(0))), op=FixAdd, results=List(x7512))
      Stage(operands=List(x7512, Const(4)), op=FixMul, results=List(x7513))
      Stage(operands=List(x7513, CU.load(x7508)), op=FixAdd, results=List(CU.scalarOut(x7506_b8363_x7521_b8365_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7506_b8364_x7521_b8366_s)))
    }
    val x7523 = MemoryController(name="x7523",parent=x7534,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7506_b8364_x7523 =  ScalarFIFO(name="size",size=1).wtPort(x7506_b8364_x7521_b8366_s)
      val x7506_b8363_x7523 =  ScalarFIFO(name="offset",size=1).wtPort(x7506_b8363_x7521_b8365_s)
      CU.newVout("data", x7507_x7523_data_v)
    }
    val x7533 = Pipeline(name="x7533",parent=x7534) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7525 = CounterChain(name = "x7525", ctr77).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7565 = StreamController(name="x7565",parent=x7604) { implicit CU => 
      val ctr78 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7536 = CounterChain(name = "x7536", ctr78).iter(48)
    }
    val x7553_0 = Pipeline(name="x7553_0",parent=x7565) { implicit CU => 
      val x7542 = CU.temp
      val x7544 = CU.temp
      val x7543 = CU.temp
      val x7541 = CU.temp
      val x7539 =  ScalarBuffer().wtPort(x7539_argin)
      val x6513_x7540 =  ScalarBuffer().wtPort(x6513_argin)
      val x7501 = CounterChain.copy("x7604", "x7501")
      val x7536 = CounterChain.copy("x7565", "x7536")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7501(0)), CU.ctr(x7536(0))), op=FixAdd, results=List(x7541))
      Stage(operands=List(x7541, CU.load(x6513_x7540)), op=FixMul, results=List(x7542))
      Stage(operands=List(x7542, CU.ctr(x6533(1))), op=FixAdd, results=List(x7543))
      Stage(operands=List(x7543, Const(4)), op=FixMul, results=List(x7544))
      Stage(operands=List(x7544, CU.load(x7539)), op=FixAdd, results=List(CU.scalarOut(x7537_b8369_x7552_b8371_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7537_b8370_x7552_b8372_s)))
    }
    val x7554 = MemoryController(name="x7554",parent=x7565,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7537_b8370_x7554 =  ScalarFIFO(name="size",size=1).wtPort(x7537_b8370_x7552_b8372_s)
      val x7537_b8369_x7554 =  ScalarFIFO(name="offset",size=1).wtPort(x7537_b8369_x7552_b8371_s)
      CU.newVout("data", x7538_x7554_data_v)
    }
    val x7564 = Pipeline(name="x7564",parent=x7565) { implicit CU => 
      val ctr79 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7556 = CounterChain(name = "x7556", ctr79).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7603 = MetaPipeline(name="x7603",parent=x7604) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr81 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7569 = CounterChain(name = "x7569", ctr80, ctr81).iter(768)
    }
    val x7591_0 = Pipeline(name="x7591_0",parent=x7603) { implicit CU => 
      val x7579_x7579 =  VectorFIFO(size=1).wtPort(x7503_x7579_x7591_v)
      val x7578_x7578 =  VectorFIFO(size=1).wtPort(x7502_x7578_x7591_v)
      val ctr82 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7572 = CounterChain(name = "x7572", ctr82).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7578_x7578), CU.load(x7579_x7579)), op=FixMul, results=List(CU.reduce))
      val (_, rr5392) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5392), op=Bypass, results=List(CU.scalarOut(x7570_x7589_s)))
    }
    val x7602_0 = Pipeline(name="x7602_0",parent=x7603) { implicit CU => 
      val x7599 = CU.temp
      val x7598 = CU.temp
      val x7570_x7597 =  ScalarBuffer().wtPort(x7570_x7589_s)
      val x7596_x7596 =  VectorFIFO(size=1).wtPort(x6543_x7596_x7602_v)
      val x7501 = CounterChain.copy("x7604", "x7501")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7501(0)), Const(0)), op=FixEql, results=List(x7598))
      Stage(operands=List(x7598, Const(0), CU.load(x7596_x7596)), op=Mux, results=List(x7599))
      Stage(operands=List(x7599, CU.load(x7570_x7597)), op=FixAdd, results=List(CU.vecOut(x6543_x7601_v)))
    }
    val x7710 = MetaPipeline(name="x7710",parent=x8153) { implicit CU => 
      val x6514_x7605 =  ScalarBuffer().wtPort(x6514_argin)
      val ctr83 = Counter(min=Const(0), max=x6514_x7605.load, step=Const(48), par=1) // Counter
      val x7607 = CounterChain(name = "x7607", ctr83).iter(1)
    }
    val x7608_dsp0 = MemoryPipeline(name="x7608_dsp0",parent="x7710") { implicit CU => 
      val b8387 = CU.temp
      val b8395 = CU.temp
      val x7638_x7638 =  VectorFIFO(size=1).wtPort(x7613_x7629_data_v)
      val x7675 = CounterChain.copy("x7709", "x7675")
      val x7631 = CounterChain.copy("x7639", "x7631")
      val x7678 = CounterChain.copy("x7697_0", "x7678")
      val x7611 = CounterChain.copy("x7640", "x7611")
      val x7608_x7684 =  SRAM(size=768,banking = Strided(1)).wtPort(x7638_x7638.readPort).rdPort(x7608_x7684_x7697_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7611(0)), Const(48)), op=FixMul, results=List(b8387))
      WAStage(operands=List(b8387, CU.ctr(x7631(0))), op=FixAdd, results=List(x7608_x7684.writeAddr))
      RAStage(operands=List(CU.ctr(x7675(0)), Const(48)), op=FixMul, results=List(b8395))
      RAStage(operands=List(b8395, CU.ctr(x7678(0))), op=FixAdd, results=List(x7608_x7684.readAddr))
    }
    val x7609_dsp0 = MemoryPipeline(name="x7609_dsp0",parent="x7710") { implicit CU => 
      val b8393 = CU.temp
      val b8397 = CU.temp
      val x7669_x7669 =  VectorFIFO(size=1).wtPort(x7644_x7660_data_v)
      val x7675 = CounterChain.copy("x7709", "x7675")
      val x7662 = CounterChain.copy("x7670", "x7662")
      val x7678 = CounterChain.copy("x7697_0", "x7678")
      val x7642 = CounterChain.copy("x7671", "x7642")
      val x7609_x7685 =  SRAM(size=2304,banking = Strided(1)).wtPort(x7669_x7669.readPort).rdPort(x7609_x7685_x7697_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7642(0)), Const(48)), op=FixMul, results=List(b8393))
      WAStage(operands=List(b8393, CU.ctr(x7662(0))), op=FixAdd, results=List(x7609_x7685.writeAddr))
      RAStage(operands=List(CU.ctr(x7678(0)), Const(48)), op=FixMul, results=List(b8397))
      RAStage(operands=List(b8397, CU.ctr(x7675(1))), op=FixAdd, results=List(x7609_x7685.readAddr))
    }
    val x7640 = StreamController(name="x7640",parent=x7710) { implicit CU => 
      val ctr84 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7611 = CounterChain(name = "x7611", ctr84).iter(16)
    }
    val x7628_0 = Pipeline(name="x7628_0",parent=x7640) { implicit CU => 
      val x7616 = CU.temp
      val x7618 = CU.temp
      val x7617 = CU.temp
      val x7619 = CU.temp
      val x6514_x7615 =  ScalarBuffer().wtPort(x6514_argin)
      val x7614 =  ScalarBuffer().wtPort(x7614_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7611 = CounterChain.copy("x7640", "x7611")
      val x7607 = CounterChain.copy("x7710", "x7607")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7611(0))), op=FixAdd, results=List(x7616))
      Stage(operands=List(x7616, CU.load(x6514_x7615)), op=FixMul, results=List(x7617))
      Stage(operands=List(x7617, CU.ctr(x7607(0))), op=FixAdd, results=List(x7618))
      Stage(operands=List(x7618, Const(4)), op=FixMul, results=List(x7619))
      Stage(operands=List(x7619, CU.load(x7614)), op=FixAdd, results=List(CU.scalarOut(x7612_b8383_x7627_b8385_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7612_b8384_x7627_b8386_s)))
    }
    val x7629 = MemoryController(name="x7629",parent=x7640,offchip=x6520_oc, mctpe=TileLoad) { implicit CU => 
      val x7612_b8384_x7629 =  ScalarFIFO(name="size",size=1).wtPort(x7612_b8384_x7627_b8386_s)
      val x7612_b8383_x7629 =  ScalarFIFO(name="offset",size=1).wtPort(x7612_b8383_x7627_b8385_s)
      CU.newVout("data", x7613_x7629_data_v)
    }
    val x7639 = Pipeline(name="x7639",parent=x7640) { implicit CU => 
      val ctr85 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7631 = CounterChain(name = "x7631", ctr85).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7671 = StreamController(name="x7671",parent=x7710) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7642 = CounterChain(name = "x7642", ctr86).iter(48)
    }
    val x7659_0 = Pipeline(name="x7659_0",parent=x7671) { implicit CU => 
      val x7648 = CU.temp
      val x7650 = CU.temp
      val x7649 = CU.temp
      val x7647 = CU.temp
      val x7645 =  ScalarBuffer().wtPort(x7645_argin)
      val x6513_x7646 =  ScalarBuffer().wtPort(x6513_argin)
      val x7607 = CounterChain.copy("x7710", "x7607")
      val x7642 = CounterChain.copy("x7671", "x7642")
      val x6533 = CounterChain.copy("x8153", "x6533")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7607(0)), CU.ctr(x7642(0))), op=FixAdd, results=List(x7647))
      Stage(operands=List(x7647, CU.load(x6513_x7646)), op=FixMul, results=List(x7648))
      Stage(operands=List(x7648, CU.ctr(x6533(1))), op=FixAdd, results=List(x7649))
      Stage(operands=List(x7649, Const(4)), op=FixMul, results=List(x7650))
      Stage(operands=List(x7650, CU.load(x7645)), op=FixAdd, results=List(CU.scalarOut(x7643_b8389_x7658_b8391_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7643_b8390_x7658_b8392_s)))
    }
    val x7660 = MemoryController(name="x7660",parent=x7671,offchip=x6523_oc, mctpe=TileLoad) { implicit CU => 
      val x7643_b8390_x7660 =  ScalarFIFO(name="size",size=1).wtPort(x7643_b8390_x7658_b8392_s)
      val x7643_b8389_x7660 =  ScalarFIFO(name="offset",size=1).wtPort(x7643_b8389_x7658_b8391_s)
      CU.newVout("data", x7644_x7660_data_v)
    }
    val x7670 = Pipeline(name="x7670",parent=x7671) { implicit CU => 
      val ctr87 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7662 = CounterChain(name = "x7662", ctr87).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7709 = MetaPipeline(name="x7709",parent=x7710) { implicit CU => 
      val ctr88 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr89 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7675 = CounterChain(name = "x7675", ctr88, ctr89).iter(768)
    }
    val x7697_0 = Pipeline(name="x7697_0",parent=x7709) { implicit CU => 
      val x7685_x7685 =  VectorFIFO(size=1).wtPort(x7609_x7685_x7697_v)
      val x7684_x7684 =  VectorFIFO(size=1).wtPort(x7608_x7684_x7697_v)
      val ctr90 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x7678 = CounterChain(name = "x7678", ctr90).iter(3)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7684_x7684), CU.load(x7685_x7685)), op=FixMul, results=List(CU.reduce))
      val (_, rr5443) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr5443), op=Bypass, results=List(CU.scalarOut(x7676_x7695_s)))
    }
    val x7708_0 = Pipeline(name="x7708_0",parent=x7709) { implicit CU => 
      val x7704 = CU.temp
      val x7705 = CU.temp
      val x7676_x7703 =  ScalarBuffer().wtPort(x7676_x7695_s)
      val x7702_x7702 =  VectorFIFO(size=1).wtPort(x6544_x7702_x7708_v)
      val x7607 = CounterChain.copy("x7710", "x7607")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7607(0)), Const(0)), op=FixEql, results=List(x7704))
      Stage(operands=List(x7704, Const(0), CU.load(x7702_x7702)), op=Mux, results=List(x7705))
      Stage(operands=List(x7705, CU.load(x7676_x7703)), op=FixAdd, results=List(CU.vecOut(x6544_x7707_v)))
    }
    val x7751 = StreamController(name="x7751",parent=x8153) { implicit CU => 
      val ctr91 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7713 = CounterChain(name = "x7713", ctr91).iter(16)
    }
    val x7741 = Sequential(name="x7741",parent=x7751) { implicit CU => 
    }
    val x7730_0 = Pipeline(name="x7730_0",parent=x7741) { implicit CU => 
      val x7722 = CU.temp
      val x7719 = CU.temp
      val x7720 = CU.temp
      val x7721 = CU.temp
      val x7717 =  ScalarBuffer().wtPort(x7717_argin)
      val x6513_x7718 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7713 = CounterChain.copy("x7751", "x7713")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7713(0))), op=FixAdd, results=List(x7719))
      Stage(operands=List(x7719, CU.load(x6513_x7718)), op=FixMul, results=List(x7720))
      Stage(operands=List(x7720, CU.ctr(x6533(1))), op=FixAdd, results=List(x7721))
      Stage(operands=List(x7721, Const(4)), op=FixMul, results=List(x7722))
      Stage(operands=List(x7722, CU.load(x7717)), op=FixAdd, results=List(CU.scalarOut(x7714_b8403_x7729_b8405_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7714_b8404_x7729_b8406_s)))
    }
    val x7740 = Pipeline(name="x7740",parent=x7741) { implicit CU => 
      val ctr92 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7732 = CounterChain(name = "x7732", ctr92).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7742 = MemoryController(name="x7742",parent=x7751,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7714_b8403_x7742 =  ScalarFIFO(name="offset",size=1).wtPort(x7714_b8403_x7729_b8405_s)
      val x7715_x7742 =  VectorFIFO(name="data",size=1).wtPort(x6534_x7736_x7740_v)
      val x7714_b8404_x7742 =  ScalarFIFO(name="size",size=1).wtPort(x7714_b8404_x7729_b8406_s)
    }
    val x7791 = StreamController(name="x7791",parent=x8153) { implicit CU => 
      val ctr94 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7753 = CounterChain(name = "x7753", ctr94).iter(16)
    }
    val x7781 = Sequential(name="x7781",parent=x7791) { implicit CU => 
    }
    val x7770_0 = Pipeline(name="x7770_0",parent=x7781) { implicit CU => 
      val x7759 = CU.temp
      val x7760 = CU.temp
      val x7761 = CU.temp
      val x7762 = CU.temp
      val x7757 =  ScalarBuffer().wtPort(x7757_argin)
      val x6513_x7758 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7753 = CounterChain.copy("x7791", "x7753")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7753(0))), op=FixAdd, results=List(x7759))
      Stage(operands=List(x7759, CU.load(x6513_x7758)), op=FixMul, results=List(x7760))
      Stage(operands=List(x7760, CU.ctr(x6533(1))), op=FixAdd, results=List(x7761))
      Stage(operands=List(x7761, Const(4)), op=FixMul, results=List(x7762))
      Stage(operands=List(x7762, CU.load(x7757)), op=FixAdd, results=List(CU.scalarOut(x7754_b8409_x7769_b8411_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7754_b8410_x7769_b8412_s)))
    }
    val x7780 = Pipeline(name="x7780",parent=x7781) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7772 = CounterChain(name = "x7772", ctr95).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7782 = MemoryController(name="x7782",parent=x7791,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7754_b8409_x7782 =  ScalarFIFO(name="offset",size=1).wtPort(x7754_b8409_x7769_b8411_s)
      val x7754_b8410_x7782 =  ScalarFIFO(name="size",size=1).wtPort(x7754_b8410_x7769_b8412_s)
      val x7755_x7782 =  VectorFIFO(name="data",size=1).wtPort(x6535_x7776_x7780_v)
    }
    val x7831 = StreamController(name="x7831",parent=x8153) { implicit CU => 
      val ctr97 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7793 = CounterChain(name = "x7793", ctr97).iter(16)
    }
    val x7821 = Sequential(name="x7821",parent=x7831) { implicit CU => 
    }
    val x7810_0 = Pipeline(name="x7810_0",parent=x7821) { implicit CU => 
      val x7800 = CU.temp
      val x7802 = CU.temp
      val x7801 = CU.temp
      val x7799 = CU.temp
      val x7797 =  ScalarBuffer().wtPort(x7797_argin)
      val x6513_x7798 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7793 = CounterChain.copy("x7831", "x7793")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7793(0))), op=FixAdd, results=List(x7799))
      Stage(operands=List(x7799, CU.load(x6513_x7798)), op=FixMul, results=List(x7800))
      Stage(operands=List(x7800, CU.ctr(x6533(1))), op=FixAdd, results=List(x7801))
      Stage(operands=List(x7801, Const(4)), op=FixMul, results=List(x7802))
      Stage(operands=List(x7802, CU.load(x7797)), op=FixAdd, results=List(CU.scalarOut(x7794_b8415_x7809_b8417_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7794_b8416_x7809_b8418_s)))
    }
    val x7820 = Pipeline(name="x7820",parent=x7821) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7812 = CounterChain(name = "x7812", ctr98).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7822 = MemoryController(name="x7822",parent=x7831,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7794_b8415_x7822 =  ScalarFIFO(name="offset",size=1).wtPort(x7794_b8415_x7809_b8417_s)
      val x7795_x7822 =  VectorFIFO(name="data",size=1).wtPort(x6536_x7816_x7820_v)
      val x7794_b8416_x7822 =  ScalarFIFO(name="size",size=1).wtPort(x7794_b8416_x7809_b8418_s)
    }
    val x7871 = StreamController(name="x7871",parent=x8153) { implicit CU => 
      val ctr100 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7833 = CounterChain(name = "x7833", ctr100).iter(16)
    }
    val x7861 = Sequential(name="x7861",parent=x7871) { implicit CU => 
    }
    val x7850_0 = Pipeline(name="x7850_0",parent=x7861) { implicit CU => 
      val x7840 = CU.temp
      val x7841 = CU.temp
      val x7842 = CU.temp
      val x7839 = CU.temp
      val x7837 =  ScalarBuffer().wtPort(x7837_argin)
      val x6513_x7838 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7833 = CounterChain.copy("x7871", "x7833")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7833(0))), op=FixAdd, results=List(x7839))
      Stage(operands=List(x7839, CU.load(x6513_x7838)), op=FixMul, results=List(x7840))
      Stage(operands=List(x7840, CU.ctr(x6533(1))), op=FixAdd, results=List(x7841))
      Stage(operands=List(x7841, Const(4)), op=FixMul, results=List(x7842))
      Stage(operands=List(x7842, CU.load(x7837)), op=FixAdd, results=List(CU.scalarOut(x7834_b8421_x7849_b8423_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7834_b8422_x7849_b8424_s)))
    }
    val x7860 = Pipeline(name="x7860",parent=x7861) { implicit CU => 
      val ctr101 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7852 = CounterChain(name = "x7852", ctr101).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7862 = MemoryController(name="x7862",parent=x7871,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7835_x7862 =  VectorFIFO(name="data",size=1).wtPort(x6537_x7856_x7860_v)
      val x7834_b8421_x7862 =  ScalarFIFO(name="offset",size=1).wtPort(x7834_b8421_x7849_b8423_s)
      val x7834_b8422_x7862 =  ScalarFIFO(name="size",size=1).wtPort(x7834_b8422_x7849_b8424_s)
    }
    val x7911 = StreamController(name="x7911",parent=x8153) { implicit CU => 
      val ctr103 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7873 = CounterChain(name = "x7873", ctr103).iter(16)
    }
    val x7901 = Sequential(name="x7901",parent=x7911) { implicit CU => 
    }
    val x7890_0 = Pipeline(name="x7890_0",parent=x7901) { implicit CU => 
      val x7881 = CU.temp
      val x7880 = CU.temp
      val x7882 = CU.temp
      val x7879 = CU.temp
      val x7877 =  ScalarBuffer().wtPort(x7877_argin)
      val x6513_x7878 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7873 = CounterChain.copy("x7911", "x7873")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7873(0))), op=FixAdd, results=List(x7879))
      Stage(operands=List(x7879, CU.load(x6513_x7878)), op=FixMul, results=List(x7880))
      Stage(operands=List(x7880, CU.ctr(x6533(1))), op=FixAdd, results=List(x7881))
      Stage(operands=List(x7881, Const(4)), op=FixMul, results=List(x7882))
      Stage(operands=List(x7882, CU.load(x7877)), op=FixAdd, results=List(CU.scalarOut(x7874_b8427_x7889_b8429_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7874_b8428_x7889_b8430_s)))
    }
    val x7900 = Pipeline(name="x7900",parent=x7901) { implicit CU => 
      val ctr104 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7892 = CounterChain(name = "x7892", ctr104).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7902 = MemoryController(name="x7902",parent=x7911,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7874_b8427_x7902 =  ScalarFIFO(name="offset",size=1).wtPort(x7874_b8427_x7889_b8429_s)
      val x7874_b8428_x7902 =  ScalarFIFO(name="size",size=1).wtPort(x7874_b8428_x7889_b8430_s)
      val x7875_x7902 =  VectorFIFO(name="data",size=1).wtPort(x6538_x7896_x7900_v)
    }
    val x7951 = StreamController(name="x7951",parent=x8153) { implicit CU => 
      val ctr106 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7913 = CounterChain(name = "x7913", ctr106).iter(16)
    }
    val x7941 = Sequential(name="x7941",parent=x7951) { implicit CU => 
    }
    val x7930_0 = Pipeline(name="x7930_0",parent=x7941) { implicit CU => 
      val x7921 = CU.temp
      val x7919 = CU.temp
      val x7920 = CU.temp
      val x7922 = CU.temp
      val x7917 =  ScalarBuffer().wtPort(x7917_argin)
      val x6513_x7918 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7913 = CounterChain.copy("x7951", "x7913")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7913(0))), op=FixAdd, results=List(x7919))
      Stage(operands=List(x7919, CU.load(x6513_x7918)), op=FixMul, results=List(x7920))
      Stage(operands=List(x7920, CU.ctr(x6533(1))), op=FixAdd, results=List(x7921))
      Stage(operands=List(x7921, Const(4)), op=FixMul, results=List(x7922))
      Stage(operands=List(x7922, CU.load(x7917)), op=FixAdd, results=List(CU.scalarOut(x7914_b8433_x7929_b8435_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7914_b8434_x7929_b8436_s)))
    }
    val x7940 = Pipeline(name="x7940",parent=x7941) { implicit CU => 
      val ctr107 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7932 = CounterChain(name = "x7932", ctr107).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7942 = MemoryController(name="x7942",parent=x7951,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7915_x7942 =  VectorFIFO(name="data",size=1).wtPort(x6539_x7936_x7940_v)
      val x7914_b8434_x7942 =  ScalarFIFO(name="size",size=1).wtPort(x7914_b8434_x7929_b8436_s)
      val x7914_b8433_x7942 =  ScalarFIFO(name="offset",size=1).wtPort(x7914_b8433_x7929_b8435_s)
    }
    val x7991 = StreamController(name="x7991",parent=x8153) { implicit CU => 
      val ctr109 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7953 = CounterChain(name = "x7953", ctr109).iter(16)
    }
    val x7981 = Sequential(name="x7981",parent=x7991) { implicit CU => 
    }
    val x7970_0 = Pipeline(name="x7970_0",parent=x7981) { implicit CU => 
      val x7960 = CU.temp
      val x7961 = CU.temp
      val x7959 = CU.temp
      val x7962 = CU.temp
      val x7957 =  ScalarBuffer().wtPort(x7957_argin)
      val x6513_x7958 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7953 = CounterChain.copy("x7991", "x7953")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7953(0))), op=FixAdd, results=List(x7959))
      Stage(operands=List(x7959, CU.load(x6513_x7958)), op=FixMul, results=List(x7960))
      Stage(operands=List(x7960, CU.ctr(x6533(1))), op=FixAdd, results=List(x7961))
      Stage(operands=List(x7961, Const(4)), op=FixMul, results=List(x7962))
      Stage(operands=List(x7962, CU.load(x7957)), op=FixAdd, results=List(CU.scalarOut(x7954_b8439_x7969_b8441_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7954_b8440_x7969_b8442_s)))
    }
    val x7980 = Pipeline(name="x7980",parent=x7981) { implicit CU => 
      val ctr110 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x7972 = CounterChain(name = "x7972", ctr110).iter(48)
      var stage: List[Stage] = Nil
    }
    val x7982 = MemoryController(name="x7982",parent=x7991,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7954_b8439_x7982 =  ScalarFIFO(name="offset",size=1).wtPort(x7954_b8439_x7969_b8441_s)
      val x7954_b8440_x7982 =  ScalarFIFO(name="size",size=1).wtPort(x7954_b8440_x7969_b8442_s)
      val x7955_x7982 =  VectorFIFO(name="data",size=1).wtPort(x6540_x7976_x7980_v)
    }
    val x8031 = StreamController(name="x8031",parent=x8153) { implicit CU => 
      val ctr112 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7993 = CounterChain(name = "x7993", ctr112).iter(16)
    }
    val x8021 = Sequential(name="x8021",parent=x8031) { implicit CU => 
    }
    val x8010_0 = Pipeline(name="x8010_0",parent=x8021) { implicit CU => 
      val x8002 = CU.temp
      val x8001 = CU.temp
      val x8000 = CU.temp
      val x7999 = CU.temp
      val x7997 =  ScalarBuffer().wtPort(x7997_argin)
      val x6513_x7998 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x7993 = CounterChain.copy("x8031", "x7993")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x7993(0))), op=FixAdd, results=List(x7999))
      Stage(operands=List(x7999, CU.load(x6513_x7998)), op=FixMul, results=List(x8000))
      Stage(operands=List(x8000, CU.ctr(x6533(1))), op=FixAdd, results=List(x8001))
      Stage(operands=List(x8001, Const(4)), op=FixMul, results=List(x8002))
      Stage(operands=List(x8002, CU.load(x7997)), op=FixAdd, results=List(CU.scalarOut(x7994_b8445_x8009_b8447_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x7994_b8446_x8009_b8448_s)))
    }
    val x8020 = Pipeline(name="x8020",parent=x8021) { implicit CU => 
      val ctr113 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x8012 = CounterChain(name = "x8012", ctr113).iter(48)
      var stage: List[Stage] = Nil
    }
    val x8022 = MemoryController(name="x8022",parent=x8031,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x7994_b8445_x8022 =  ScalarFIFO(name="offset",size=1).wtPort(x7994_b8445_x8009_b8447_s)
      val x7995_x8022 =  VectorFIFO(name="data",size=1).wtPort(x6541_x8016_x8020_v)
      val x7994_b8446_x8022 =  ScalarFIFO(name="size",size=1).wtPort(x7994_b8446_x8009_b8448_s)
    }
    val x8071 = StreamController(name="x8071",parent=x8153) { implicit CU => 
      val ctr115 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8033 = CounterChain(name = "x8033", ctr115).iter(16)
    }
    val x8061 = Sequential(name="x8061",parent=x8071) { implicit CU => 
    }
    val x8050_0 = Pipeline(name="x8050_0",parent=x8061) { implicit CU => 
      val x8040 = CU.temp
      val x8039 = CU.temp
      val x8042 = CU.temp
      val x8041 = CU.temp
      val x8037 =  ScalarBuffer().wtPort(x8037_argin)
      val x6513_x8038 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x8033 = CounterChain.copy("x8071", "x8033")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x8033(0))), op=FixAdd, results=List(x8039))
      Stage(operands=List(x8039, CU.load(x6513_x8038)), op=FixMul, results=List(x8040))
      Stage(operands=List(x8040, CU.ctr(x6533(1))), op=FixAdd, results=List(x8041))
      Stage(operands=List(x8041, Const(4)), op=FixMul, results=List(x8042))
      Stage(operands=List(x8042, CU.load(x8037)), op=FixAdd, results=List(CU.scalarOut(x8034_b8451_x8049_b8453_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x8034_b8452_x8049_b8454_s)))
    }
    val x8060 = Pipeline(name="x8060",parent=x8061) { implicit CU => 
      val ctr116 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x8052 = CounterChain(name = "x8052", ctr116).iter(48)
      var stage: List[Stage] = Nil
    }
    val x8062 = MemoryController(name="x8062",parent=x8071,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x8035_x8062 =  VectorFIFO(name="data",size=1).wtPort(x6542_x8056_x8060_v)
      val x8034_b8452_x8062 =  ScalarFIFO(name="size",size=1).wtPort(x8034_b8452_x8049_b8454_s)
      val x8034_b8451_x8062 =  ScalarFIFO(name="offset",size=1).wtPort(x8034_b8451_x8049_b8453_s)
    }
    val x8111 = StreamController(name="x8111",parent=x8153) { implicit CU => 
      val ctr118 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8073 = CounterChain(name = "x8073", ctr118).iter(16)
    }
    val x8101 = Sequential(name="x8101",parent=x8111) { implicit CU => 
    }
    val x8090_0 = Pipeline(name="x8090_0",parent=x8101) { implicit CU => 
      val x8082 = CU.temp
      val x8081 = CU.temp
      val x8079 = CU.temp
      val x8080 = CU.temp
      val x8077 =  ScalarBuffer().wtPort(x8077_argin)
      val x6513_x8078 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x8073 = CounterChain.copy("x8111", "x8073")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x8073(0))), op=FixAdd, results=List(x8079))
      Stage(operands=List(x8079, CU.load(x6513_x8078)), op=FixMul, results=List(x8080))
      Stage(operands=List(x8080, CU.ctr(x6533(1))), op=FixAdd, results=List(x8081))
      Stage(operands=List(x8081, Const(4)), op=FixMul, results=List(x8082))
      Stage(operands=List(x8082, CU.load(x8077)), op=FixAdd, results=List(CU.scalarOut(x8074_b8457_x8089_b8459_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x8074_b8458_x8089_b8460_s)))
    }
    val x8100 = Pipeline(name="x8100",parent=x8101) { implicit CU => 
      val ctr119 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x8092 = CounterChain(name = "x8092", ctr119).iter(48)
      var stage: List[Stage] = Nil
    }
    val x8102 = MemoryController(name="x8102",parent=x8111,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x8074_b8457_x8102 =  ScalarFIFO(name="offset",size=1).wtPort(x8074_b8457_x8089_b8459_s)
      val x8074_b8458_x8102 =  ScalarFIFO(name="size",size=1).wtPort(x8074_b8458_x8089_b8460_s)
      val x8075_x8102 =  VectorFIFO(name="data",size=1).wtPort(x6543_x8096_x8100_v)
    }
    val x8151 = StreamController(name="x8151",parent=x8153) { implicit CU => 
      val ctr121 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8113 = CounterChain(name = "x8113", ctr121).iter(16)
    }
    val x8141 = Sequential(name="x8141",parent=x8151) { implicit CU => 
    }
    val x8130_0 = Pipeline(name="x8130_0",parent=x8141) { implicit CU => 
      val x8119 = CU.temp
      val x8122 = CU.temp
      val x8121 = CU.temp
      val x8120 = CU.temp
      val x8117 =  ScalarBuffer().wtPort(x8117_argin)
      val x6513_x8118 =  ScalarBuffer().wtPort(x6513_argin)
      val x6533 = CounterChain.copy("x8153", "x6533")
      val x8113 = CounterChain.copy("x8151", "x8113")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x6533(0)), CU.ctr(x8113(0))), op=FixAdd, results=List(x8119))
      Stage(operands=List(x8119, CU.load(x6513_x8118)), op=FixMul, results=List(x8120))
      Stage(operands=List(x8120, CU.ctr(x6533(1))), op=FixAdd, results=List(x8121))
      Stage(operands=List(x8121, Const(4)), op=FixMul, results=List(x8122))
      Stage(operands=List(x8122, CU.load(x8117)), op=FixAdd, results=List(CU.scalarOut(x8114_b8463_x8129_b8465_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x8114_b8464_x8129_b8466_s)))
    }
    val x8140 = Pipeline(name="x8140",parent=x8141) { implicit CU => 
      val ctr122 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x8132 = CounterChain(name = "x8132", ctr122).iter(48)
      var stage: List[Stage] = Nil
    }
    val x8142 = MemoryController(name="x8142",parent=x8151,offchip=x6526_oc, mctpe=TileStore) { implicit CU => 
      val x8114_b8463_x8142 =  ScalarFIFO(name="offset",size=1).wtPort(x8114_b8463_x8129_b8465_s)
      val x8115_x8142 =  VectorFIFO(name="data",size=1).wtPort(x6544_x8136_x8140_v)
      val x8114_b8464_x8142 =  ScalarFIFO(name="size",size=1).wtPort(x8114_b8464_x8129_b8466_s)
    }
    
  }
}
