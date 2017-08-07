import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SPMV_CRS extends PIRApp {
  def main(top:Top) = {
    val bus_1868_s = Scalar("bus_1868")
    val x6830_x6841_s = Scalar("x6830_x6841")
    val x6752_x6782_data_s = Scalar("x6752_x6782_data")
    val bus_1702_s = Scalar("bus_1702")
    val x6750_b7691_x6778_b7699_s = Scalar("x6750_b7691_x6778_b7699")
    val bus_1438_s = Scalar("bus_1438")
    val bus_1372_s = Scalar("bus_1372")
    val x7457_x7470_s = Scalar("x7457_x7470")
    val bus_1403_s = Scalar("bus_1403")
    val x6687_b7678_x6715_b7686_s = Scalar("x6687_b7678_x6715_b7686")
    val x6460_x6489_data_s = Scalar("x6460_x6489_data")
    val x6379_x6387_s = Scalar("x6379_x6387")
    val x6750_b7692_x6778_b7700_s = Scalar("x6750_b7692_x6778_b7700")
    val x7356_x7374_data_s = Scalar("x7356_x7374_data")
    val vec_dram_oc = OffChip("vec_dram")
    val bus_1867_s = Scalar("bus_1867")
    val bus_1498_s = Scalar("bus_1498")
    val bus_1659_s = Scalar("bus_1659")
    val x7020_b7704_x7048_b7712_s = Scalar("x7020_b7704_x7048_b7712")
    val x6372_x6372_dsp0_bank0_s = Scalar("x6372_x6372_dsp0_bank0")
    val x6751_b7693_x6780_b7701_s = Scalar("x6751_b7693_x6780_b7701")
    val x7155_b7733_x7184_b7741_s = Scalar("x7155_b7733_x7184_b7741")
    val x7455_x7469_s = Scalar("x7455_x7469")
    val x6926_x6958_s = Scalar("x6926_x6958")
    val x7298_x7319_s = Scalar("x7298_x7319")
    val bus_1363_s = Scalar("bus_1363")
    val x6680_x6685_s = Scalar("x6680_x6685")
    val x7156_x7186_data_s = Scalar("x7156_x7186_data")
    val bus_1568_s = Scalar("bus_1568")
    val bus_1371_s = Scalar("bus_1371")
    val x6989_x6989_dsp0_bank0_s = Scalar("x6989_x6989_dsp0_bank0")
    val x7531_x7580_s = Scalar("x7531_x7580")
    val bus_1731_s = Scalar("bus_1731")
    val x7147_x7152_s = Scalar("x7147_x7152")
    val x6751_b7695_x6780_b7703_s = Scalar("x6751_b7695_x6780_b7703")
    val cols_dram_da = DRAMAddress("cols_dram", "cols_dram")
    val bus_1870_s = Scalar("bus_1870")
    val x6688_b7682_x6717_b7690_s = Scalar("x6688_b7682_x6717_b7690")
    val x7013_x7018_s = Scalar("x7013_x7018")
    val bus_1508_s = Scalar("bus_1508")
    val bus_1842_s = Scalar("bus_1842")
    val x6553_b7653_x6581_b7661_s = Scalar("x6553_b7653_x6581_b7661")
    val x6988_x6988_dsp0_bank0_s = Scalar("x6988_x6988_dsp0_bank0")
    val vec_dram_da = DRAMAddress("vec_dram", "vec_dram")
    val x7084_b7720_x7113_b7728_s = Scalar("x7084_b7720_x7113_b7728")
    val bus_1441_s = Scalar("bus_1441")
    val x6376_x6393_s = Scalar("x6376_x6393")
    val x6831_x6852_s = Scalar("x6831_x6852")
    val x6856_x6874_data_s = Scalar("x6856_x6874_data")
    val bus_1733_s = Scalar("bus_1733")
    val x6992_x6992_dsp0_bank0_s = Scalar("x6992_x6992_dsp0_bank0")
    val bus_1528_s = Scalar("bus_1528")
    val bus_1476_s = Scalar("bus_1476")
    val x6816_x6827_s = Scalar("x6816_x6827")
    val x6546_x6551_s = Scalar("x6546_x6551")
    val x7085_x7115_data_s = Scalar("x7085_x7115_data")
    val x7355_x7372_s = Scalar("x7355_x7372")
    val bus_1477_s = Scalar("bus_1477")
    val x6687_b7679_x6715_b7687_s = Scalar("x6687_b7679_x6715_b7687")
    val bus_1764_s = Scalar("bus_1764")
    val x7473_b7756_x7503_b7758_s = Scalar("x7473_b7756_x7503_b7758")
    val bus_1558_s = Scalar("bus_1558")
    val bus_1780_s = Scalar("bus_1780")
    val bus_1537_s = Scalar("bus_1537")
    val bus_1707_s = Scalar("bus_1707")
    val x6399_b7628_x6427_b7636_s = Scalar("x6399_b7628_x6427_b7636")
    val x6889_x6907_data_s = Scalar("x6889_x6907_data")
    val x7219_x7249_data_s = Scalar("x7219_x7249_data")
    val bus_1688_s = Scalar("bus_1688")
    val bus_1362_s = Scalar("bus_1362")
    val bus_1714_s = Scalar("bus_1714")
    val x6553_b7652_x6581_b7660_s = Scalar("x6553_b7652_x6581_b7660")
    val x6554_b7654_x6583_b7662_s = Scalar("x6554_b7654_x6583_b7662")
    val x7020_b7705_x7048_b7713_s = Scalar("x7020_b7705_x7048_b7713")
    val x7297_x7308_s = Scalar("x7297_x7308")
    val x7534_x7562_s = Scalar("x7534_x7562")
    val bus_1391_s = Scalar("bus_1391")
    val bus_1861_s = Scalar("bus_1861")
    val bus_1743_s = Scalar("bus_1743")
    val x7533_x7561_s = Scalar("x7533_x7561")
    val bus_1704_s = Scalar("bus_1704")
    val bus_1398_s = Scalar("bus_1398")
    val bus_1482_s = Scalar("bus_1482")
    val x6458_b7639_x6485_b7647_s = Scalar("x6458_b7639_x6485_b7647")
    val x7154_b7730_x7182_b7738_s = Scalar("x7154_b7730_x7182_b7738")
    val bus_1536_s = Scalar("bus_1536")
    val bus_1682_s = Scalar("bus_1682")
    val x7154_b7731_x7182_b7739_s = Scalar("x7154_b7731_x7182_b7739")
    val x7217_b7743_x7245_b7751_s = Scalar("x7217_b7743_x7245_b7751")
    val bus_1530_s = Scalar("bus_1530")
    val x7323_x7341_data_s = Scalar("x7323_x7341_data")
    val x7083_b7718_x7111_b7726_s = Scalar("x7083_b7718_x7111_b7726")
    val bus_1736_s = Scalar("bus_1736")
    val x6555_x6585_data_s = Scalar("x6555_x6585_data")
    val bus_1470_s = Scalar("bus_1470")
    val bus_1843_s = Scalar("bus_1843")
    val x6525_x6525_dsp0_bank0_s = Scalar("x6525_x6525_dsp0_bank0")
    val x6372_x6372_dsp1_bank0_s = Scalar("x6372_x6372_dsp1_bank0")
    val x6459_b7642_x6487_b7650_s = Scalar("x6459_b7642_x6487_b7650")
    val values_dram_da = DRAMAddress("values_dram", "values_dram")
    val x6522_x6522_dsp0_bank0_s = Scalar("x6522_x6522_dsp0_bank0")
    val x6888_x6905_s = Scalar("x6888_x6905")
    val x7454_x7462_s = Scalar("x7454_x7462")
    val x7218_b7745_x7247_b7753_s = Scalar("x7218_b7745_x7247_b7753")
    val bus_1653_s = Scalar("bus_1653")
    val x7083_b7717_x7111_b7725_s = Scalar("x7083_b7717_x7111_b7725")
    val x6399_b7629_x6427_b7637_s = Scalar("x6399_b7629_x6427_b7637")
    val x6617_b7669_x6646_b7677_s = Scalar("x6617_b7669_x6646_b7677")
    val x6374_x6374_dsp0_bank0_s = Scalar("x6374_x6374_dsp0_bank0")
    val x6618_x6648_data_s = Scalar("x6618_x6648_data")
    val bus_1527_s = Scalar("bus_1527")
    val bus_1840_s = Scalar("bus_1840")
    val x6815_x6821_s = Scalar("x6815_x6821")
    val x7530_b7760_x7560_b7762_s = Scalar("x7530_b7760_x7560_b7762")
    val bus_1365_s = Scalar("bus_1365")
    val bus_1386_s = Scalar("bus_1386")
    val x6398_b7627_x6425_b7635_s = Scalar("x6398_b7627_x6425_b7635")
    val x6526_x6526_dsp0_bank0_s = Scalar("x6526_x6526_dsp0_bank0")
    val bus_1377_s = Scalar("bus_1377")
    val bus_1447_s = Scalar("bus_1447")
    val x7021_b7708_x7050_b7716_s = Scalar("x7021_b7708_x7050_b7716")
    val x7393_x7425_s = Scalar("x7393_x7425")
    val bus_1647_s = Scalar("bus_1647")
    val bus_1705_s = Scalar("bus_1705")
    val x6993_x6993_dsp0_bank0_s = Scalar("x6993_x6993_dsp0_bank0")
    val bus_1507_s = Scalar("bus_1507")
    val bus_1674_s = Scalar("bus_1674")
    val bus_1642_s = Scalar("bus_1642")
    val x6524_x6524_dsp0_bank0_s = Scalar("x6524_x6524_dsp0_bank0")
    val x6378_x6394_s = Scalar("x6378_x6394")
    val bus_1671_s = Scalar("bus_1671")
    val values_dram_oc = OffChip("values_dram")
    val x6554_b7656_x6583_b7664_s = Scalar("x6554_b7656_x6583_b7664")
    val x6371_x6371_dsp1_bank0_s = Scalar("x6371_x6371_dsp1_bank0")
    val bus_1683_s = Scalar("bus_1683")
    val result_dram_oc = OffChip("result_dram")
    val bus_1468_s = Scalar("bus_1468")
    val x6398_b7626_x6425_b7634_s = Scalar("x6398_b7626_x6425_b7634")
    val bus_1513_s = Scalar("bus_1513")
    val bus_1770_s = Scalar("bus_1770")
    val bus_1654_s = Scalar("bus_1654")
    val x6688_b7680_x6717_b7688_s = Scalar("x6688_b7680_x6717_b7688")
    val bus_1467_s = Scalar("bus_1467")
    val bus_1866_s = Scalar("bus_1866")
    val rowid_dram_oc = OffChip("rowid_dram")
    val x7155_b7732_x7184_b7740_s = Scalar("x7155_b7732_x7184_b7740")
    val bus_1564_s = Scalar("bus_1564")
    val bus_1542_s = Scalar("bus_1542")
    val bus_1838_s = Scalar("bus_1838")
    val x6855_x6872_s = Scalar("x6855_x6872")
    val x7478_x7506_s = Scalar("x7478_x7506")
    val bus_1734_s = Scalar("bus_1734")
    val x6459_b7641_x6487_b7649_s = Scalar("x6459_b7641_x6487_b7649")
    val x6617_b7668_x6646_b7676_s = Scalar("x6617_b7668_x6646_b7676")
    val bus_1833_s = Scalar("bus_1833")
    val bus_1839_s = Scalar("bus_1839")
    val x7282_x7288_s = Scalar("x7282_x7288")
    val x7476_x7504_s = Scalar("x7476_x7504")
    val bus_1389_s = Scalar("bus_1389")
    val x6689_x6719_data_s = Scalar("x6689_x6719_data")
    val x6521_x6521_dsp0_bank0_s = Scalar("x6521_x6521_dsp0_bank0")
    val bus_1453_s = Scalar("bus_1453")
    val x7218_b7746_x7247_b7754_s = Scalar("x7218_b7746_x7247_b7754")
    val x6459_b7643_x6487_b7651_s = Scalar("x6459_b7643_x6487_b7651")
    val x7530_b7761_x7560_b7763_s = Scalar("x7530_b7761_x7560_b7763")
    val bus_1397_s = Scalar("bus_1397")
    val bus_1774_s = Scalar("bus_1774")
    val x6554_b7655_x6583_b7663_s = Scalar("x6554_b7655_x6583_b7663")
    val x6751_b7694_x6780_b7702_s = Scalar("x6751_b7694_x6780_b7702")
    val x6990_x6990_dsp0_bank0_s = Scalar("x6990_x6990_dsp0_bank0")
    val x7474_x7523_s = Scalar("x7474_x7523")
    val x7283_x7294_s = Scalar("x7283_x7294")
    val x6399_b7630_x6427_b7638_s = Scalar("x6399_b7630_x6427_b7638")
    val x7155_b7734_x7184_b7742_s = Scalar("x7155_b7734_x7184_b7742")
    val x7217_b7744_x7245_b7752_s = Scalar("x7217_b7744_x7245_b7752")
    val x6991_x6991_dsp0_bank0_s = Scalar("x6991_x6991_dsp0_bank0")
    val bus_1644_s = Scalar("bus_1644")
    val bus_1860_s = Scalar("bus_1860")
    val x7021_b7707_x7050_b7715_s = Scalar("x7021_b7707_x7050_b7715")
    val bus_1871_s = Scalar("bus_1871")
    val x6377_x6386_s = Scalar("x6377_x6386")
    val cols_dram_oc = OffChip("cols_dram")
    val x7218_b7747_x7247_b7755_s = Scalar("x7218_b7747_x7247_b7755")
    val bus_1499_s = Scalar("bus_1499")
    val bus_1574_s = Scalar("bus_1574")
    val x6688_b7681_x6717_b7689_s = Scalar("x6688_b7681_x6717_b7689")
    val x6523_x6523_dsp0_bank0_s = Scalar("x6523_x6523_dsp0_bank0")
    val bus_1525_s = Scalar("bus_1525")
    val x6458_b7640_x6485_b7648_s = Scalar("x6458_b7640_x6485_b7648")
    val x6375_x6385_s = Scalar("x6375_x6385")
    val x6927_x6973_s = Scalar("x6927_x6973")
    val x7084_b7721_x7113_b7729_s = Scalar("x7084_b7721_x7113_b7729")
    val x7022_x7052_data_s = Scalar("x7022_x7052_data")
    val result_dram_da = DRAMAddress("result_dram", "result_dram")
    val x6617_b7667_x6646_b7675_s = Scalar("x6617_b7667_x6646_b7675")
    val x7535_x7563_s = Scalar("x7535_x7563")
    val x7021_b7706_x7050_b7714_s = Scalar("x7021_b7706_x7050_b7714")
    val bus_1436_s = Scalar("bus_1436")
    val x7473_b7757_x7503_b7759_s = Scalar("x7473_b7757_x7503_b7759")
    val bus_1465_s = Scalar("bus_1465")
    val bus_1388_s = Scalar("bus_1388")
    val bus_1439_s = Scalar("bus_1439")
    val bus_1645_s = Scalar("bus_1645")
    val x7084_b7719_x7113_b7727_s = Scalar("x7084_b7719_x7113_b7727")
    val bus_1448_s = Scalar("bus_1448")
    val bus_1496_s = Scalar("bus_1496")
    val x6616_b7666_x6644_b7674_s = Scalar("x6616_b7666_x6644_b7674")
    val x6400_x6429_data_s = Scalar("x6400_x6429_data")
    val x6373_x6373_dsp0_bank0_s = Scalar("x6373_x6373_dsp0_bank0")
    val x7477_x7505_s = Scalar("x7477_x7505")
    val bus_1748_s = Scalar("bus_1748")
    val bus_1673_s = Scalar("bus_1673")
    val x7322_x7339_s = Scalar("x7322_x7339")
    val bus_1501_s = Scalar("bus_1501")
    val rowid_dram_da = DRAMAddress("rowid_dram", "rowid_dram")
    val bus_1360_s = Scalar("bus_1360")
    val bus_1713_s = Scalar("bus_1713")
    val bus_1719_s = Scalar("bus_1719")
    val bus_1742_s = Scalar("bus_1742")
    val bus_1832_s = Scalar("bus_1832")
    val x6616_b7665_x6644_b7673_s = Scalar("x6616_b7665_x6644_b7673")
    val x7456_x7463_s = Scalar("x7456_x7463")
    val bus_1676_s = Scalar("bus_1676")
    val x7394_x7440_s = Scalar("x7394_x7440")
    val x6371_x6371_dsp0_bank0_s = Scalar("x6371_x6371_dsp0_bank0")
    val x6380_x6395_s = Scalar("x6380_x6395")
    val x7589 = Sequential(name="x7589",parent=top) { implicit CU => 
      val x7589_unit = CounterChain(name = "x7589_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7588 = MetaPipeline(name="x7588",parent=x7589) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=2) // Counter
      val x6370 = CounterChain(name = "x6370", ctr1).iter(1)
    }
    val x6371_dsp0_bank0 = MemoryPipeline(name="x6371_dsp0_bank0",parent="x7588") { implicit CU => 
      val x6454 = ScalarFIFO(size=1,name="x6454").wtPort(x6400_x6429_data_s)
      val x6430 = ScalarBuffer(name="x6430").wtPort(x6399_b7629_x6427_b7637_s)
      val x6443 = CounterChain.copy("x6455_0", "x6443")
      val x6520 = CounterChain.copy("x6985", "x6520")
      val x6371 = SRAM(size=495,name="x6371",banking = Strided(1)).wtPort(x6454.readPort).rdPort(x6371_x6371_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x6443(0)), CU.load(x6430)), op=FixSub, results=List(x6371.writeAddr))
      RAStage(operands=List(CU.ctr(x6520(0)), Const(1)), op=FixAdd, results=List(x6371.readAddr))
      RAStage(operands=List(CU.ctr(x6520(0)), Const(1)), op=FixAdd, results=List(x6371.readAddr))
    }
    val x6371_dsp1_bank0 = MemoryPipeline(name="x6371_dsp1_bank0",parent="x7588") { implicit CU => 
      val x6454 = ScalarFIFO(size=1,name="x6454").wtPort(x6400_x6429_data_s)
      val x6430 = ScalarBuffer(name="x6430").wtPort(x6399_b7629_x6427_b7637_s)
      val x6443 = CounterChain.copy("x6455_0", "x6443")
      val x6520 = CounterChain.copy("x6985", "x6520")
      val x6371 = SRAM(size=495,name="x6371",banking = Strided(1)).wtPort(x6454.readPort).rdPort(x6371_x6371_dsp1_bank0_s).rdAddr(x6520(0)).rdAddr(x6520(0))
      WAStage(operands=List(CU.ctr(x6443(0)), CU.load(x6430)), op=FixSub, results=List(x6371.writeAddr))
    }
    val x6372_dsp0_bank0 = MemoryPipeline(name="x6372_dsp0_bank0",parent="x7588") { implicit CU => 
      val x6514 = ScalarFIFO(size=1,name="x6514").wtPort(x6460_x6489_data_s)
      val x6490 = ScalarBuffer(name="x6490").wtPort(x6459_b7642_x6487_b7650_s)
      val x6503 = CounterChain.copy("x6515_0", "x6503")
      val x6987 = CounterChain.copy("x7452", "x6987")
      val x6372 = SRAM(size=495,name="x6372",banking = Strided(1)).wtPort(x6514.readPort).rdPort(x6372_x6372_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x6503(0)), CU.load(x6490)), op=FixSub, results=List(x6372.writeAddr))
      RAStage(operands=List(CU.ctr(x6987(0)), Const(1)), op=FixAdd, results=List(x6372.readAddr))
      RAStage(operands=List(CU.ctr(x6987(0)), Const(1)), op=FixAdd, results=List(x6372.readAddr))
    }
    val x6372_dsp1_bank0 = MemoryPipeline(name="x6372_dsp1_bank0",parent="x7588") { implicit CU => 
      val x6514 = ScalarFIFO(size=1,name="x6514").wtPort(x6460_x6489_data_s)
      val x6490 = ScalarBuffer(name="x6490").wtPort(x6459_b7642_x6487_b7650_s)
      val x6503 = CounterChain.copy("x6515_0", "x6503")
      val x6987 = CounterChain.copy("x7452", "x6987")
      val x6372 = SRAM(size=495,name="x6372",banking = Strided(1)).wtPort(x6514.readPort).rdPort(x6372_x6372_dsp1_bank0_s).rdAddr(x6987(0)).rdAddr(x6987(0))
      WAStage(operands=List(CU.ctr(x6503(0)), CU.load(x6490)), op=FixSub, results=List(x6372.writeAddr))
    }
    val x6373_dsp0_bank0 = MemoryPipeline(name="x6373_dsp0_bank0",parent="x7588") { implicit CU => 
      val x7476 = ScalarBuffer(name="x7476").wtPort(x7476_x7504_s)
      val x6982 = ScalarFIFO(size=1,name="x6982").wtPort(x6927_x6973_s)
      val x6978 = ScalarFIFO(size=1,name="x6978").wtPort(x6926_x6958_s)
      val x6520 = CounterChain.copy("x6985", "x6520")
      val x7510 = CounterChain.copy("x7524_0", "x7510")
      val x6373 = SRAM(size=494,name="x6373",banking = Strided(1)).wtPort(x6978.readPort).wtPort(x6982.readPort).rdPort(x6373_x6373_dsp0_bank0_s).wtAddr(x6520(0)).wtAddr(x6520(0))
      RAStage(operands=List(CU.ctr(x7510(0)), CU.load(x7476)), op=FixSub, results=List(x6373.readAddr))
    }
    val x6374_dsp0_bank0 = MemoryPipeline(name="x6374_dsp0_bank0",parent="x7588") { implicit CU => 
      val x7449 = ScalarFIFO(size=1,name="x7449").wtPort(x7394_x7440_s)
      val x7445 = ScalarFIFO(size=1,name="x7445").wtPort(x7393_x7425_s)
      val x7533 = ScalarBuffer(name="x7533").wtPort(x7533_x7561_s)
      val x6987 = CounterChain.copy("x7452", "x6987")
      val x7567 = CounterChain.copy("x7581_0", "x7567")
      val x6374 = SRAM(size=494,name="x6374",banking = Strided(1)).wtPort(x7445.readPort).wtPort(x7449.readPort).rdPort(x6374_x6374_dsp0_bank0_s).wtAddr(x6987(0)).wtAddr(x6987(0))
      RAStage(operands=List(CU.ctr(x7567(0)), CU.load(x7533)), op=FixSub, results=List(x6374.readAddr))
    }
    val x6388_0 = Pipeline(name="x6388_0",parent=x7588) { implicit CU => 
      val x6383 = CU.temp(None)
      val x6382 = CU.temp(None)
      val x6381 = CU.temp(None)
      val x6370 = CounterChain.copy("x7588", "x6370")
      val x6388_unit = CounterChain(name = "x6388_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6370(0)), Const(495)), op=FixMul, results=List(x6381, CU.scalarOut(x6375_x6385_s)))
      Stage(operands=List(CU.ctr(x6370(0)), Const(1)), op=FixAdd, results=List(x6382, CU.scalarOut(x6377_x6386_s)))
      Stage(operands=List(x6382, Const(495)), op=FixMul, results=List(x6383))
      Stage(operands=List(x6383, x6381), op=FixSub, results=List(CU.scalarOut(x6379_x6387_s)))
    }
    val x6396_0 = Pipeline(name="x6396_0",parent=x7588) { implicit CU => 
      val x6391 = CU.temp(None)
      val x6389 = CU.temp(None)
      val x6390 = CU.temp(None)
      val x6370 = CounterChain.copy("x7588", "x6370")
      val x6396_unit = CounterChain(name = "x6396_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6370(0)), Const(495)), op=FixMul, results=List(x6389, CU.scalarOut(x6376_x6393_s)))
      Stage(operands=List(CU.ctr(x6370(0)), Const(1)), op=FixAdd, results=List(x6390, CU.scalarOut(x6378_x6394_s)))
      Stage(operands=List(x6390, Const(495)), op=FixMul, results=List(x6391))
      Stage(operands=List(x6391, x6389), op=FixSub, results=List(CU.scalarOut(x6380_x6395_s)))
    }
    val x6457 = StreamController(name="x6457",parent=x7588) { implicit CU => 
      val x6457_unit = CounterChain(name = "x6457_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6428 = StreamController(name="x6428",parent=x6457) { implicit CU => 
      val x6428_unit = CounterChain(name = "x6428_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6428_0 = Pipeline(name="x6428_0",parent=x6428) { implicit CU => 
      val x6404 = CU.temp(None)
      val x6403 = CU.temp(None)
      val x6426_x6413 = CU.temp(None)
      val x6375 = ScalarBuffer(name="x6375").wtPort(x6375_x6385_s)
      val x6379 = ScalarBuffer(name="x6379").wtPort(x6379_x6387_s)
      Stage(operands=List(CU.load(x6375), Const(2)), op=FixSla, results=List(x6403, CU.scalarOut(bus_1360_s)))
      Stage(operands=List(x6403, Const(64)), op=FixMod, results=List(x6404, CU.scalarOut(bus_1362_s)))
      Stage(operands=List(x6404, Const(2)), op=FixSra, results=List(x6426_x6413, CU.scalarOut(bus_1363_s), CU.scalarOut(x6399_b7629_x6427_b7637_s)))
      Stage(operands=List(x6426_x6413, CU.load(x6379)), op=FixAdd, results=List(CU.scalarOut(x6399_b7630_x6427_b7638_s)))
      Stage(operands=List(CU.load(x6379), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1365_s)))
    }
    val x6428_1 = Pipeline(name="x6428_1",parent=x6428) { implicit CU => 
      val x6411 = CU.temp(None)
      val x6409 = CU.temp(None)
      val x6408 = CU.temp(None)
      val x6412 = CU.temp(None)
      val x6410 = CU.temp(None)
      val x6406 = ScalarFIFO(size=1,name="x6406").wtPort(bus_1365_s)
      val x6403 = ScalarFIFO(size=1,name="x6403").wtPort(bus_1360_s)
      Stage(operands=List(CU.load(x6403), CU.load(x6406)), op=FixAdd, results=List(x6408))
      Stage(operands=List(x6408, Const(64)), op=FixMod, results=List(x6409))
      Stage(operands=List(x6409, Const(0)), op=FixEql, results=List(x6410))
      Stage(operands=List(Const(64), x6409), op=FixSub, results=List(x6411))
      Stage(operands=List(x6410, Const(0), x6411), op=Mux, results=List(x6412, CU.scalarOut(bus_1371_s)))
      Stage(operands=List(x6412, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1372_s)))
    }
    val x6428_2 = Pipeline(name="x6428_2",parent=x6428) { implicit CU => 
      val x6418 = CU.temp(None)
      val x6416 = CU.temp(None)
      val x6404 = ScalarFIFO(size=1,name="x6404").wtPort(bus_1362_s)
      val x6412 = ScalarFIFO(size=1,name="x6412").wtPort(bus_1371_s)
      val x6406 = ScalarFIFO(size=1,name="x6406").wtPort(bus_1365_s)
      val x6403 = ScalarFIFO(size=1,name="x6403").wtPort(bus_1360_s)
      val x6379 = ScalarBuffer(name="x6379").wtPort(x6379_x6387_s)
      val x6414 = ScalarFIFO(size=1,name="x6414").wtPort(bus_1372_s)
      val x6413 = ScalarFIFO(size=1,name="x6413").wtPort(bus_1363_s)
      Stage(operands=List(CU.load(x6379), CU.load(x6413)), op=FixAdd, results=List(x6416))
      Stage(operands=List(x6416, CU.load(x6414)), op=FixAdd, results=List(CU.scalarOut(x6399_b7628_x6427_b7636_s)))
      Stage(operands=List(CU.load(x6406), CU.load(x6404)), op=FixAdd, results=List(x6418))
      Stage(operands=List(x6418, CU.load(x6412)), op=FixAdd, results=List(CU.scalarOut(x6398_b7627_x6425_b7635_s)))
      Stage(operands=List(CU.load(x6403), CU.load(x6404)), op=FixSub, results=List(CU.scalarOut(bus_1377_s)))
    }
    val x6428_3 = Pipeline(name="x6428_3",parent=x6428) { implicit CU => 
      val x6407 = ScalarFIFO(size=1,name="x6407").wtPort(bus_1377_s)
      val x6421 = ScalarBuffer(name="x6421").wtPort(rowid_dram_da)
      Stage(operands=List(CU.load(x6407), CU.load(x6421)), op=FixAdd, results=List(CU.scalarOut(x6398_b7626_x6425_b7634_s)))
    }
    val x6429 = MemoryController(name="x6429",parent=x6457,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6398_b7627 = ScalarFIFO(size=1,name="size").wtPort(x6398_b7627_x6425_b7635_s)
      val x6398_b7626 = ScalarFIFO(size=1,name="offset").wtPort(x6398_b7626_x6425_b7634_s)
      CU.newSout("data", x6400_x6429_data_s)
    }
    val x6456 = Sequential(name="x6456",parent=x6457) { implicit CU => 
      val x6456_unit = CounterChain(name = "x6456_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6455_0 = Pipeline(name="x6455_0",parent=x6456) { implicit CU => 
      val x6432 = ScalarBuffer(name="x6432").wtPort(x6399_b7628_x6427_b7636_s)
      val x6431 = ScalarBuffer(name="x6431").wtPort(x6399_b7630_x6427_b7638_s)
      val x6430 = ScalarBuffer(name="x6430").wtPort(x6399_b7629_x6427_b7637_s)
      val ctr2 = Counter(min=Const(0), max=x6432.readPort, step=Const(1), par=16) // Counter
      val x6443 = CounterChain(name = "x6443", ctr2).iter(1)
      Stage(operands=List(CU.load(x6430), CU.ctr(x6443(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6443(0)), CU.load(x6431)), op=FixLt, results=List())
    }
    val x6517 = StreamController(name="x6517",parent=x7588) { implicit CU => 
      val x6517_unit = CounterChain(name = "x6517_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6488 = StreamController(name="x6488",parent=x6517) { implicit CU => 
      val x6488_unit = CounterChain(name = "x6488_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6488_0 = Pipeline(name="x6488_0",parent=x6488) { implicit CU => 
      val x6486_x6473 = CU.temp(None)
      val x6464 = CU.temp(None)
      val x6463 = CU.temp(None)
      val x6380 = ScalarBuffer(name="x6380").wtPort(x6380_x6395_s)
      val x6376 = ScalarBuffer(name="x6376").wtPort(x6376_x6393_s)
      Stage(operands=List(CU.load(x6376), Const(2)), op=FixSla, results=List(x6463, CU.scalarOut(bus_1386_s)))
      Stage(operands=List(x6463, Const(64)), op=FixMod, results=List(x6464, CU.scalarOut(bus_1388_s)))
      Stage(operands=List(x6464, Const(2)), op=FixSra, results=List(x6486_x6473, CU.scalarOut(bus_1389_s), CU.scalarOut(x6459_b7642_x6487_b7650_s)))
      Stage(operands=List(x6486_x6473, CU.load(x6380)), op=FixAdd, results=List(CU.scalarOut(x6459_b7643_x6487_b7651_s)))
      Stage(operands=List(CU.load(x6380), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1391_s)))
    }
    val x6488_1 = Pipeline(name="x6488_1",parent=x6488) { implicit CU => 
      val x6470 = CU.temp(None)
      val x6469 = CU.temp(None)
      val x6468 = CU.temp(None)
      val x6472 = CU.temp(None)
      val x6471 = CU.temp(None)
      val x6466 = ScalarFIFO(size=1,name="x6466").wtPort(bus_1391_s)
      val x6463 = ScalarFIFO(size=1,name="x6463").wtPort(bus_1386_s)
      Stage(operands=List(CU.load(x6463), CU.load(x6466)), op=FixAdd, results=List(x6468))
      Stage(operands=List(x6468, Const(64)), op=FixMod, results=List(x6469))
      Stage(operands=List(x6469, Const(0)), op=FixEql, results=List(x6470))
      Stage(operands=List(Const(64), x6469), op=FixSub, results=List(x6471))
      Stage(operands=List(x6470, Const(0), x6471), op=Mux, results=List(x6472, CU.scalarOut(bus_1397_s)))
      Stage(operands=List(x6472, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1398_s)))
    }
    val x6488_2 = Pipeline(name="x6488_2",parent=x6488) { implicit CU => 
      val x6476 = CU.temp(None)
      val x6478 = CU.temp(None)
      val x6380 = ScalarBuffer(name="x6380").wtPort(x6380_x6395_s)
      val x6466 = ScalarFIFO(size=1,name="x6466").wtPort(bus_1391_s)
      val x6473 = ScalarFIFO(size=1,name="x6473").wtPort(bus_1389_s)
      val x6474 = ScalarFIFO(size=1,name="x6474").wtPort(bus_1398_s)
      val x6463 = ScalarFIFO(size=1,name="x6463").wtPort(bus_1386_s)
      val x6472 = ScalarFIFO(size=1,name="x6472").wtPort(bus_1397_s)
      val x6464 = ScalarFIFO(size=1,name="x6464").wtPort(bus_1388_s)
      Stage(operands=List(CU.load(x6380), CU.load(x6473)), op=FixAdd, results=List(x6476))
      Stage(operands=List(x6476, CU.load(x6474)), op=FixAdd, results=List(CU.scalarOut(x6459_b7641_x6487_b7649_s)))
      Stage(operands=List(CU.load(x6466), CU.load(x6464)), op=FixAdd, results=List(x6478))
      Stage(operands=List(x6478, CU.load(x6472)), op=FixAdd, results=List(CU.scalarOut(x6458_b7640_x6485_b7648_s)))
      Stage(operands=List(CU.load(x6463), CU.load(x6464)), op=FixSub, results=List(CU.scalarOut(bus_1403_s)))
    }
    val x6488_3 = Pipeline(name="x6488_3",parent=x6488) { implicit CU => 
      val x6467 = ScalarFIFO(size=1,name="x6467").wtPort(bus_1403_s)
      val x6481 = ScalarBuffer(name="x6481").wtPort(rowid_dram_da)
      Stage(operands=List(CU.load(x6467), CU.load(x6481)), op=FixAdd, results=List(CU.scalarOut(x6458_b7639_x6485_b7647_s)))
    }
    val x6489 = MemoryController(name="x6489",parent=x6517,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6458_b7640 = ScalarFIFO(size=1,name="size").wtPort(x6458_b7640_x6485_b7648_s)
      val x6458_b7639 = ScalarFIFO(size=1,name="offset").wtPort(x6458_b7639_x6485_b7647_s)
      CU.newSout("data", x6460_x6489_data_s)
    }
    val x6516 = Sequential(name="x6516",parent=x6517) { implicit CU => 
      val x6516_unit = CounterChain(name = "x6516_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6515_0 = Pipeline(name="x6515_0",parent=x6516) { implicit CU => 
      val x6492 = ScalarBuffer(name="x6492").wtPort(x6459_b7641_x6487_b7649_s)
      val x6491 = ScalarBuffer(name="x6491").wtPort(x6459_b7643_x6487_b7651_s)
      val x6490 = ScalarBuffer(name="x6490").wtPort(x6459_b7642_x6487_b7650_s)
      val ctr3 = Counter(min=Const(0), max=x6492.readPort, step=Const(1), par=16) // Counter
      val x6503 = CounterChain(name = "x6503", ctr3).iter(1)
      Stage(operands=List(CU.load(x6490), CU.ctr(x6503(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6503(0)), CU.load(x6491)), op=FixLt, results=List())
    }
    val x6985 = MetaPipeline(name="x6985",parent=x7588) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(494), step=Const(1), par=2) // Counter
      val x6520 = CounterChain(name = "x6520", ctr4).iter(247)
    }
    val x6521_dsp0_bank0 = MemoryPipeline(name="x6521_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6586 = ScalarBuffer(name="x6586").wtPort(x6554_b7655_x6583_b7663_s)
      val x6612 = ScalarFIFO(size=1,name="x6612").wtPort(x6555_x6585_data_s)
      val x6600 = CounterChain.copy("x6613_0", "x6600")
      val x6859 = CounterChain.copy("x6873_0", "x6859")
      val x6521 = SRAM(size=494,name="x6521",banking = Strided(1)).wtPort(x6612.readPort).rdPort(x6521_x6521_dsp0_bank0_s).rdAddr(x6859(0))
      WAStage(operands=List(CU.ctr(x6600(0)), CU.load(x6586)), op=FixSub, results=List(x6521.writeAddr))
    }
    val x6522_dsp0_bank0 = MemoryPipeline(name="x6522_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6746 = ScalarFIFO(size=1,name="x6746").wtPort(x6689_x6719_data_s)
      val x6720 = ScalarBuffer(name="x6720").wtPort(x6688_b7681_x6717_b7689_s)
      val x6734 = CounterChain.copy("x6747_0", "x6734")
      val x6892 = CounterChain.copy("x6906_0", "x6892")
      val x6522 = SRAM(size=494,name="x6522",banking = Strided(1)).wtPort(x6746.readPort).rdPort(x6522_x6522_dsp0_bank0_s).rdAddr(x6892(0))
      WAStage(operands=List(CU.ctr(x6734(0)), CU.load(x6720)), op=FixSub, results=List(x6522.writeAddr))
    }
    val x6523_dsp0_bank0 = MemoryPipeline(name="x6523_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6649 = ScalarBuffer(name="x6649").wtPort(x6617_b7668_x6646_b7676_s)
      val x6675 = ScalarFIFO(size=1,name="x6675").wtPort(x6618_x6648_data_s)
      val x6663 = CounterChain.copy("x6676_0", "x6663")
      val x6947 = CounterChain.copy("x6959_0", "x6947")
      val x6523 = SRAM(size=494,name="x6523",banking = Strided(1)).wtPort(x6675.readPort).rdPort(x6523_x6523_dsp0_bank0_s).rdAddr(x6947(0))
      WAStage(operands=List(CU.ctr(x6663(0)), CU.load(x6649)), op=FixSub, results=List(x6523.writeAddr))
    }
    val x6524_dsp0_bank0 = MemoryPipeline(name="x6524_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6809 = ScalarFIFO(size=1,name="x6809").wtPort(x6752_x6782_data_s)
      val x6783 = ScalarBuffer(name="x6783").wtPort(x6751_b7694_x6780_b7702_s)
      val x6797 = CounterChain.copy("x6810_0", "x6797")
      val x6962 = CounterChain.copy("x6974_0", "x6962")
      val x6524 = SRAM(size=494,name="x6524",banking = Strided(1)).wtPort(x6809.readPort).rdPort(x6524_x6524_dsp0_bank0_s).rdAddr(x6962(0))
      WAStage(operands=List(CU.ctr(x6797(0)), CU.load(x6783)), op=FixSub, results=List(x6524.writeAddr))
    }
    val x6525_dsp0_bank0 = MemoryPipeline(name="x6525_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6885 = ScalarFIFO(size=1,name="x6885").wtPort(x6856_x6874_data_s)
      val x6877 = CounterChain.copy("x6886", "x6877")
      val x6947 = CounterChain.copy("x6959_0", "x6947")
      val x6525 = SRAM(size=494,name="x6525",banking = Strided(1)).wtPort(x6885.readPort).rdPort(x6525_x6525_dsp0_bank0_s).rdAddr(x6947(0)).wtAddr(x6877(0))
    }
    val x6526_dsp0_bank0 = MemoryPipeline(name="x6526_dsp0_bank0",parent="x6985") { implicit CU => 
      val x6918 = ScalarFIFO(size=1,name="x6918").wtPort(x6889_x6907_data_s)
      val x6910 = CounterChain.copy("x6919", "x6910")
      val x6962 = CounterChain.copy("x6974_0", "x6962")
      val x6526 = SRAM(size=494,name="x6526",banking = Strided(1)).wtPort(x6918.readPort).rdPort(x6526_x6526_dsp0_bank0_s).rdAddr(x6962(0)).wtAddr(x6910(0))
    }
    val x6552_0 = Pipeline(name="x6552_0",parent=x6985) { implicit CU => 
      val x6527 = ScalarBuffer(name="x6527").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6529 = ScalarBuffer(name="x6529").wtPort(x6371_x6371_dsp0_bank0_s)
      val x6552_unit = CounterChain(name = "x6552_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6529), CU.load(x6527)), op=FixSub, results=List(CU.scalarOut(x6546_x6551_s)))
    }
    val x6615 = StreamController(name="x6615",parent=x6985) { implicit CU => 
      val x6615_unit = CounterChain(name = "x6615_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6584 = StreamController(name="x6584",parent=x6615) { implicit CU => 
      val x6584_unit = CounterChain(name = "x6584_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6584_0 = Pipeline(name="x6584_0",parent=x6584) { implicit CU => 
      val x6559 = CU.temp(None)
      val x6558 = CU.temp(None)
      val x6582_x6568 = CU.temp(None)
      val x6546 = ScalarBuffer(name="x6546").wtPort(x6546_x6551_s)
      val x6527 = ScalarBuffer(name="x6527").wtPort(x6371_x6371_dsp1_bank0_s)
      Stage(operands=List(CU.load(x6527), Const(2)), op=FixSla, results=List(x6558, CU.scalarOut(bus_1436_s)))
      Stage(operands=List(x6558, Const(64)), op=FixMod, results=List(x6559, CU.scalarOut(bus_1438_s)))
      Stage(operands=List(x6559, Const(2)), op=FixSra, results=List(x6582_x6568, CU.scalarOut(bus_1439_s), CU.scalarOut(x6554_b7655_x6583_b7663_s)))
      Stage(operands=List(x6582_x6568, CU.load(x6546)), op=FixAdd, results=List(CU.scalarOut(x6554_b7656_x6583_b7664_s)))
      Stage(operands=List(CU.load(x6546), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1441_s)))
    }
    val x6584_1 = Pipeline(name="x6584_1",parent=x6584) { implicit CU => 
      val x6565 = CU.temp(None)
      val x6567 = CU.temp(None)
      val x6566 = CU.temp(None)
      val x6564 = CU.temp(None)
      val x6563 = CU.temp(None)
      val x6561 = ScalarFIFO(size=1,name="x6561").wtPort(bus_1441_s)
      val x6558 = ScalarFIFO(size=1,name="x6558").wtPort(bus_1436_s)
      Stage(operands=List(CU.load(x6558), CU.load(x6561)), op=FixAdd, results=List(x6563))
      Stage(operands=List(x6563, Const(64)), op=FixMod, results=List(x6564))
      Stage(operands=List(x6564, Const(0)), op=FixEql, results=List(x6565))
      Stage(operands=List(Const(64), x6564), op=FixSub, results=List(x6566))
      Stage(operands=List(x6565, Const(0), x6566), op=Mux, results=List(x6567, CU.scalarOut(bus_1447_s)))
      Stage(operands=List(x6567, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1448_s)))
    }
    val x6584_2 = Pipeline(name="x6584_2",parent=x6584) { implicit CU => 
      val x6571 = CU.temp(None)
      val x6573 = CU.temp(None)
      val x6561 = ScalarFIFO(size=1,name="x6561").wtPort(bus_1441_s)
      val x6559 = ScalarFIFO(size=1,name="x6559").wtPort(bus_1438_s)
      val x6569 = ScalarFIFO(size=1,name="x6569").wtPort(bus_1448_s)
      val x6567 = ScalarFIFO(size=1,name="x6567").wtPort(bus_1447_s)
      val x6546 = ScalarBuffer(name="x6546").wtPort(x6546_x6551_s)
      val x6558 = ScalarFIFO(size=1,name="x6558").wtPort(bus_1436_s)
      val x6568 = ScalarFIFO(size=1,name="x6568").wtPort(bus_1439_s)
      Stage(operands=List(CU.load(x6546), CU.load(x6568)), op=FixAdd, results=List(x6571))
      Stage(operands=List(x6571, CU.load(x6569)), op=FixAdd, results=List(CU.scalarOut(x6554_b7654_x6583_b7662_s)))
      Stage(operands=List(CU.load(x6561), CU.load(x6559)), op=FixAdd, results=List(x6573))
      Stage(operands=List(x6573, CU.load(x6567)), op=FixAdd, results=List(CU.scalarOut(x6553_b7653_x6581_b7661_s)))
      Stage(operands=List(CU.load(x6558), CU.load(x6559)), op=FixSub, results=List(CU.scalarOut(bus_1453_s)))
    }
    val x6584_3 = Pipeline(name="x6584_3",parent=x6584) { implicit CU => 
      val x6562 = ScalarFIFO(size=1,name="x6562").wtPort(bus_1453_s)
      val x6576 = ScalarBuffer(name="x6576").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x6562), CU.load(x6576)), op=FixAdd, results=List(CU.scalarOut(x6553_b7652_x6581_b7660_s)))
    }
    val x6585 = MemoryController(name="x6585",parent=x6615,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6553_b7652 = ScalarFIFO(size=1,name="offset").wtPort(x6553_b7652_x6581_b7660_s)
      val x6553_b7653 = ScalarFIFO(size=1,name="size").wtPort(x6553_b7653_x6581_b7661_s)
      CU.newSout("data", x6555_x6585_data_s)
    }
    val x6614 = Sequential(name="x6614",parent=x6615) { implicit CU => 
      val x6614_unit = CounterChain(name = "x6614_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6613_0 = Pipeline(name="x6613_0",parent=x6614) { implicit CU => 
      val x6588 = ScalarBuffer(name="x6588").wtPort(x6554_b7654_x6583_b7662_s)
      val x6587 = ScalarBuffer(name="x6587").wtPort(x6554_b7656_x6583_b7664_s)
      val x6586 = ScalarBuffer(name="x6586").wtPort(x6554_b7655_x6583_b7663_s)
      val ctr5 = Counter(min=Const(0), max=x6588.readPort, step=Const(1), par=16) // Counter
      val x6600 = CounterChain(name = "x6600", ctr5).iter(1)
      Stage(operands=List(CU.load(x6586), CU.ctr(x6600(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6600(0)), CU.load(x6587)), op=FixLt, results=List())
    }
    val x6678 = StreamController(name="x6678",parent=x6985) { implicit CU => 
      val x6678_unit = CounterChain(name = "x6678_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6647 = StreamController(name="x6647",parent=x6678) { implicit CU => 
      val x6647_unit = CounterChain(name = "x6647_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6647_0 = Pipeline(name="x6647_0",parent=x6647) { implicit CU => 
      val x6622 = CU.temp(None)
      val x6621 = CU.temp(None)
      val x6645_x6631 = CU.temp(None)
      val x6546 = ScalarBuffer(name="x6546").wtPort(x6546_x6551_s)
      val x6527 = ScalarBuffer(name="x6527").wtPort(x6371_x6371_dsp1_bank0_s)
      Stage(operands=List(CU.load(x6527), Const(2)), op=FixSla, results=List(x6621, CU.scalarOut(bus_1465_s)))
      Stage(operands=List(x6621, Const(64)), op=FixMod, results=List(x6622, CU.scalarOut(bus_1467_s)))
      Stage(operands=List(x6622, Const(2)), op=FixSra, results=List(x6645_x6631, CU.scalarOut(bus_1468_s), CU.scalarOut(x6617_b7668_x6646_b7676_s)))
      Stage(operands=List(x6645_x6631, CU.load(x6546)), op=FixAdd, results=List(CU.scalarOut(x6617_b7669_x6646_b7677_s)))
      Stage(operands=List(CU.load(x6546), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1470_s)))
    }
    val x6647_1 = Pipeline(name="x6647_1",parent=x6647) { implicit CU => 
      val x6626 = CU.temp(None)
      val x6628 = CU.temp(None)
      val x6630 = CU.temp(None)
      val x6627 = CU.temp(None)
      val x6629 = CU.temp(None)
      val x6624 = ScalarFIFO(size=1,name="x6624").wtPort(bus_1470_s)
      val x6621 = ScalarFIFO(size=1,name="x6621").wtPort(bus_1465_s)
      Stage(operands=List(CU.load(x6621), CU.load(x6624)), op=FixAdd, results=List(x6626))
      Stage(operands=List(x6626, Const(64)), op=FixMod, results=List(x6627))
      Stage(operands=List(x6627, Const(0)), op=FixEql, results=List(x6628))
      Stage(operands=List(Const(64), x6627), op=FixSub, results=List(x6629))
      Stage(operands=List(x6628, Const(0), x6629), op=Mux, results=List(x6630, CU.scalarOut(bus_1476_s)))
      Stage(operands=List(x6630, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1477_s)))
    }
    val x6647_2 = Pipeline(name="x6647_2",parent=x6647) { implicit CU => 
      val x6636 = CU.temp(None)
      val x6634 = CU.temp(None)
      val x6630 = ScalarFIFO(size=1,name="x6630").wtPort(bus_1476_s)
      val x6546 = ScalarBuffer(name="x6546").wtPort(x6546_x6551_s)
      val x6621 = ScalarFIFO(size=1,name="x6621").wtPort(bus_1465_s)
      val x6624 = ScalarFIFO(size=1,name="x6624").wtPort(bus_1470_s)
      val x6632 = ScalarFIFO(size=1,name="x6632").wtPort(bus_1477_s)
      val x6622 = ScalarFIFO(size=1,name="x6622").wtPort(bus_1467_s)
      val x6631 = ScalarFIFO(size=1,name="x6631").wtPort(bus_1468_s)
      Stage(operands=List(CU.load(x6546), CU.load(x6631)), op=FixAdd, results=List(x6634))
      Stage(operands=List(x6634, CU.load(x6632)), op=FixAdd, results=List(CU.scalarOut(x6617_b7667_x6646_b7675_s)))
      Stage(operands=List(CU.load(x6624), CU.load(x6622)), op=FixAdd, results=List(x6636))
      Stage(operands=List(x6636, CU.load(x6630)), op=FixAdd, results=List(CU.scalarOut(x6616_b7666_x6644_b7674_s)))
      Stage(operands=List(CU.load(x6621), CU.load(x6622)), op=FixSub, results=List(CU.scalarOut(bus_1482_s)))
    }
    val x6647_3 = Pipeline(name="x6647_3",parent=x6647) { implicit CU => 
      val x6625 = ScalarFIFO(size=1,name="x6625").wtPort(bus_1482_s)
      val x6639 = ScalarBuffer(name="x6639").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x6625), CU.load(x6639)), op=FixAdd, results=List(CU.scalarOut(x6616_b7665_x6644_b7673_s)))
    }
    val x6648 = MemoryController(name="x6648",parent=x6678,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6616_b7666 = ScalarFIFO(size=1,name="size").wtPort(x6616_b7666_x6644_b7674_s)
      val x6616_b7665 = ScalarFIFO(size=1,name="offset").wtPort(x6616_b7665_x6644_b7673_s)
      CU.newSout("data", x6618_x6648_data_s)
    }
    val x6677 = Sequential(name="x6677",parent=x6678) { implicit CU => 
      val x6677_unit = CounterChain(name = "x6677_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6676_0 = Pipeline(name="x6676_0",parent=x6677) { implicit CU => 
      val x6650 = ScalarBuffer(name="x6650").wtPort(x6617_b7669_x6646_b7677_s)
      val x6649 = ScalarBuffer(name="x6649").wtPort(x6617_b7668_x6646_b7676_s)
      val x6651 = ScalarBuffer(name="x6651").wtPort(x6617_b7667_x6646_b7675_s)
      val ctr6 = Counter(min=Const(0), max=x6651.readPort, step=Const(1), par=16) // Counter
      val x6663 = CounterChain(name = "x6663", ctr6).iter(1)
      Stage(operands=List(CU.load(x6649), CU.ctr(x6663(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6663(0)), CU.load(x6650)), op=FixLt, results=List())
    }
    val x6686_0 = Pipeline(name="x6686_0",parent=x6985) { implicit CU => 
      val x6528 = ScalarBuffer(name="x6528").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6530 = ScalarBuffer(name="x6530").wtPort(x6371_x6371_dsp0_bank0_s)
      val x6686_unit = CounterChain(name = "x6686_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6530), CU.load(x6528)), op=FixSub, results=List(CU.scalarOut(x6680_x6685_s)))
    }
    val x6749 = StreamController(name="x6749",parent=x6985) { implicit CU => 
      val x6749_unit = CounterChain(name = "x6749_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6718 = StreamController(name="x6718",parent=x6749) { implicit CU => 
      val x6718_unit = CounterChain(name = "x6718_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6718_0 = Pipeline(name="x6718_0",parent=x6718) { implicit CU => 
      val x6693 = CU.temp(None)
      val x6692 = CU.temp(None)
      val x6716_x6702 = CU.temp(None)
      val x6528 = ScalarBuffer(name="x6528").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6680 = ScalarBuffer(name="x6680").wtPort(x6680_x6685_s)
      Stage(operands=List(CU.load(x6528), Const(2)), op=FixSla, results=List(x6692, CU.scalarOut(bus_1496_s)))
      Stage(operands=List(x6692, Const(64)), op=FixMod, results=List(x6693, CU.scalarOut(bus_1498_s)))
      Stage(operands=List(x6693, Const(2)), op=FixSra, results=List(x6716_x6702, CU.scalarOut(bus_1499_s), CU.scalarOut(x6688_b7681_x6717_b7689_s)))
      Stage(operands=List(x6716_x6702, CU.load(x6680)), op=FixAdd, results=List(CU.scalarOut(x6688_b7682_x6717_b7690_s)))
      Stage(operands=List(CU.load(x6680), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1501_s)))
    }
    val x6718_1 = Pipeline(name="x6718_1",parent=x6718) { implicit CU => 
      val x6698 = CU.temp(None)
      val x6700 = CU.temp(None)
      val x6701 = CU.temp(None)
      val x6699 = CU.temp(None)
      val x6697 = CU.temp(None)
      val x6695 = ScalarFIFO(size=1,name="x6695").wtPort(bus_1501_s)
      val x6692 = ScalarFIFO(size=1,name="x6692").wtPort(bus_1496_s)
      Stage(operands=List(CU.load(x6692), CU.load(x6695)), op=FixAdd, results=List(x6697))
      Stage(operands=List(x6697, Const(64)), op=FixMod, results=List(x6698))
      Stage(operands=List(x6698, Const(0)), op=FixEql, results=List(x6699))
      Stage(operands=List(Const(64), x6698), op=FixSub, results=List(x6700))
      Stage(operands=List(x6699, Const(0), x6700), op=Mux, results=List(x6701, CU.scalarOut(bus_1507_s)))
      Stage(operands=List(x6701, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1508_s)))
    }
    val x6718_2 = Pipeline(name="x6718_2",parent=x6718) { implicit CU => 
      val x6705 = CU.temp(None)
      val x6707 = CU.temp(None)
      val x6703 = ScalarFIFO(size=1,name="x6703").wtPort(bus_1508_s)
      val x6692 = ScalarFIFO(size=1,name="x6692").wtPort(bus_1496_s)
      val x6695 = ScalarFIFO(size=1,name="x6695").wtPort(bus_1501_s)
      val x6693 = ScalarFIFO(size=1,name="x6693").wtPort(bus_1498_s)
      val x6680 = ScalarBuffer(name="x6680").wtPort(x6680_x6685_s)
      val x6701 = ScalarFIFO(size=1,name="x6701").wtPort(bus_1507_s)
      val x6702 = ScalarFIFO(size=1,name="x6702").wtPort(bus_1499_s)
      Stage(operands=List(CU.load(x6680), CU.load(x6702)), op=FixAdd, results=List(x6705))
      Stage(operands=List(x6705, CU.load(x6703)), op=FixAdd, results=List(CU.scalarOut(x6688_b7680_x6717_b7688_s)))
      Stage(operands=List(CU.load(x6695), CU.load(x6693)), op=FixAdd, results=List(x6707))
      Stage(operands=List(x6707, CU.load(x6701)), op=FixAdd, results=List(CU.scalarOut(x6687_b7679_x6715_b7687_s)))
      Stage(operands=List(CU.load(x6692), CU.load(x6693)), op=FixSub, results=List(CU.scalarOut(bus_1513_s)))
    }
    val x6718_3 = Pipeline(name="x6718_3",parent=x6718) { implicit CU => 
      val x6696 = ScalarFIFO(size=1,name="x6696").wtPort(bus_1513_s)
      val x6710 = ScalarBuffer(name="x6710").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x6696), CU.load(x6710)), op=FixAdd, results=List(CU.scalarOut(x6687_b7678_x6715_b7686_s)))
    }
    val x6719 = MemoryController(name="x6719",parent=x6749,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6687_b7679 = ScalarFIFO(size=1,name="size").wtPort(x6687_b7679_x6715_b7687_s)
      val x6687_b7678 = ScalarFIFO(size=1,name="offset").wtPort(x6687_b7678_x6715_b7686_s)
      CU.newSout("data", x6689_x6719_data_s)
    }
    val x6748 = Sequential(name="x6748",parent=x6749) { implicit CU => 
      val x6748_unit = CounterChain(name = "x6748_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6747_0 = Pipeline(name="x6747_0",parent=x6748) { implicit CU => 
      val x6722 = ScalarBuffer(name="x6722").wtPort(x6688_b7680_x6717_b7688_s)
      val x6721 = ScalarBuffer(name="x6721").wtPort(x6688_b7682_x6717_b7690_s)
      val x6720 = ScalarBuffer(name="x6720").wtPort(x6688_b7681_x6717_b7689_s)
      val ctr7 = Counter(min=Const(0), max=x6722.readPort, step=Const(1), par=16) // Counter
      val x6734 = CounterChain(name = "x6734", ctr7).iter(1)
      Stage(operands=List(CU.load(x6720), CU.ctr(x6734(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6734(0)), CU.load(x6721)), op=FixLt, results=List())
    }
    val x6812 = StreamController(name="x6812",parent=x6985) { implicit CU => 
      val x6812_unit = CounterChain(name = "x6812_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6781 = StreamController(name="x6781",parent=x6812) { implicit CU => 
      val x6781_unit = CounterChain(name = "x6781_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6781_0 = Pipeline(name="x6781_0",parent=x6781) { implicit CU => 
      val x6756 = CU.temp(None)
      val x6755 = CU.temp(None)
      val x6779_x6765 = CU.temp(None)
      val x6528 = ScalarBuffer(name="x6528").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6680 = ScalarBuffer(name="x6680").wtPort(x6680_x6685_s)
      Stage(operands=List(CU.load(x6528), Const(2)), op=FixSla, results=List(x6755, CU.scalarOut(bus_1525_s)))
      Stage(operands=List(x6755, Const(64)), op=FixMod, results=List(x6756, CU.scalarOut(bus_1527_s)))
      Stage(operands=List(x6756, Const(2)), op=FixSra, results=List(x6779_x6765, CU.scalarOut(bus_1528_s), CU.scalarOut(x6751_b7694_x6780_b7702_s)))
      Stage(operands=List(x6779_x6765, CU.load(x6680)), op=FixAdd, results=List(CU.scalarOut(x6751_b7695_x6780_b7703_s)))
      Stage(operands=List(CU.load(x6680), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1530_s)))
    }
    val x6781_1 = Pipeline(name="x6781_1",parent=x6781) { implicit CU => 
      val x6761 = CU.temp(None)
      val x6764 = CU.temp(None)
      val x6762 = CU.temp(None)
      val x6760 = CU.temp(None)
      val x6763 = CU.temp(None)
      val x6755 = ScalarFIFO(size=1,name="x6755").wtPort(bus_1525_s)
      val x6758 = ScalarFIFO(size=1,name="x6758").wtPort(bus_1530_s)
      Stage(operands=List(CU.load(x6755), CU.load(x6758)), op=FixAdd, results=List(x6760))
      Stage(operands=List(x6760, Const(64)), op=FixMod, results=List(x6761))
      Stage(operands=List(x6761, Const(0)), op=FixEql, results=List(x6762))
      Stage(operands=List(Const(64), x6761), op=FixSub, results=List(x6763))
      Stage(operands=List(x6762, Const(0), x6763), op=Mux, results=List(x6764, CU.scalarOut(bus_1536_s)))
      Stage(operands=List(x6764, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1537_s)))
    }
    val x6781_2 = Pipeline(name="x6781_2",parent=x6781) { implicit CU => 
      val x6768 = CU.temp(None)
      val x6770 = CU.temp(None)
      val x6758 = ScalarFIFO(size=1,name="x6758").wtPort(bus_1530_s)
      val x6766 = ScalarFIFO(size=1,name="x6766").wtPort(bus_1537_s)
      val x6755 = ScalarFIFO(size=1,name="x6755").wtPort(bus_1525_s)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(bus_1536_s)
      val x6680 = ScalarBuffer(name="x6680").wtPort(x6680_x6685_s)
      val x6756 = ScalarFIFO(size=1,name="x6756").wtPort(bus_1527_s)
      val x6765 = ScalarFIFO(size=1,name="x6765").wtPort(bus_1528_s)
      Stage(operands=List(CU.load(x6680), CU.load(x6765)), op=FixAdd, results=List(x6768))
      Stage(operands=List(x6768, CU.load(x6766)), op=FixAdd, results=List(CU.scalarOut(x6751_b7693_x6780_b7701_s)))
      Stage(operands=List(CU.load(x6758), CU.load(x6756)), op=FixAdd, results=List(x6770))
      Stage(operands=List(x6770, CU.load(x6764)), op=FixAdd, results=List(CU.scalarOut(x6750_b7692_x6778_b7700_s)))
      Stage(operands=List(CU.load(x6755), CU.load(x6756)), op=FixSub, results=List(CU.scalarOut(bus_1542_s)))
    }
    val x6781_3 = Pipeline(name="x6781_3",parent=x6781) { implicit CU => 
      val x6773 = ScalarBuffer(name="x6773").wtPort(values_dram_da)
      val x6759 = ScalarFIFO(size=1,name="x6759").wtPort(bus_1542_s)
      Stage(operands=List(CU.load(x6759), CU.load(x6773)), op=FixAdd, results=List(CU.scalarOut(x6750_b7691_x6778_b7699_s)))
    }
    val x6782 = MemoryController(name="x6782",parent=x6812,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6750_b7691 = ScalarFIFO(size=1,name="offset").wtPort(x6750_b7691_x6778_b7699_s)
      val x6750_b7692 = ScalarFIFO(size=1,name="size").wtPort(x6750_b7692_x6778_b7700_s)
      CU.newSout("data", x6752_x6782_data_s)
    }
    val x6811 = Sequential(name="x6811",parent=x6812) { implicit CU => 
      val x6811_unit = CounterChain(name = "x6811_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6810_0 = Pipeline(name="x6810_0",parent=x6811) { implicit CU => 
      val x6785 = ScalarBuffer(name="x6785").wtPort(x6751_b7693_x6780_b7701_s)
      val x6784 = ScalarBuffer(name="x6784").wtPort(x6751_b7695_x6780_b7703_s)
      val x6783 = ScalarBuffer(name="x6783").wtPort(x6751_b7694_x6780_b7702_s)
      val ctr8 = Counter(min=Const(0), max=x6785.readPort, step=Const(1), par=16) // Counter
      val x6797 = CounterChain(name = "x6797", ctr8).iter(1)
      Stage(operands=List(CU.load(x6783), CU.ctr(x6797(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6797(0)), CU.load(x6784)), op=FixLt, results=List())
    }
    val x6822_0 = Pipeline(name="x6822_0",parent=x6985) { implicit CU => 
      val x6527 = ScalarBuffer(name="x6527").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6529 = ScalarBuffer(name="x6529").wtPort(x6371_x6371_dsp0_bank0_s)
      val x6822_unit = CounterChain(name = "x6822_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6529), CU.load(x6527)), op=FixSub, results=List(CU.scalarOut(x6815_x6821_s)))
    }
    val x6828_0 = Pipeline(name="x6828_0",parent=x6985) { implicit CU => 
      val x6528 = ScalarBuffer(name="x6528").wtPort(x6371_x6371_dsp1_bank0_s)
      val x6530 = ScalarBuffer(name="x6530").wtPort(x6371_x6371_dsp0_bank0_s)
      val x6828_unit = CounterChain(name = "x6828_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6530), CU.load(x6528)), op=FixSub, results=List(CU.scalarOut(x6816_x6827_s)))
    }
    val x6842 = StreamController(name="x6842",parent=x6985) { implicit CU => 
      val x6842_unit = CounterChain(name = "x6842_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6842_0 = Pipeline(name="x6842_0",parent=x6842) { implicit CU => 
      val x6834 = CU.temp(None)
      val x6835 = CU.temp(None)
      val x6836 = CU.temp(None)
      val x6837 = CU.temp(None)
      val x6815 = ScalarBuffer(name="x6815").wtPort(x6815_x6821_s)
      Stage(operands=List(CU.load(x6815), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1558_s)))
      Stage(operands=List(CU.load(x6815), Const(16)), op=FixMod, results=List(x6834))
      Stage(operands=List(x6834, Const(0)), op=FixEql, results=List(x6835))
      Stage(operands=List(CU.load(x6815), Const(16)), op=FixAdd, results=List(x6836))
      Stage(operands=List(x6836, x6834), op=FixSub, results=List(x6837))
      Stage(operands=List(x6835, CU.load(x6815), x6837), op=Mux, results=List(CU.scalarOut(bus_1564_s)))
    }
    val x6842_1 = Pipeline(name="x6842_1",parent=x6842) { implicit CU => 
      val x6838 = ScalarFIFO(size=1,name="x6838").wtPort(bus_1564_s)
      val x6833 = ScalarFIFO(size=1,name="x6833").wtPort(bus_1558_s)
      Stage(operands=List(CU.load(x6833), Const(16), CU.load(x6838)), op=Mux, results=List(CU.scalarOut(x6830_x6841_s)))
    }
    val x6853 = StreamController(name="x6853",parent=x6985) { implicit CU => 
      val x6853_unit = CounterChain(name = "x6853_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6853_0 = Pipeline(name="x6853_0",parent=x6853) { implicit CU => 
      val x6845 = CU.temp(None)
      val x6846 = CU.temp(None)
      val x6848 = CU.temp(None)
      val x6847 = CU.temp(None)
      val x6816 = ScalarBuffer(name="x6816").wtPort(x6816_x6827_s)
      Stage(operands=List(CU.load(x6816), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1568_s)))
      Stage(operands=List(CU.load(x6816), Const(16)), op=FixMod, results=List(x6845))
      Stage(operands=List(x6845, Const(0)), op=FixEql, results=List(x6846))
      Stage(operands=List(CU.load(x6816), Const(16)), op=FixAdd, results=List(x6847))
      Stage(operands=List(x6847, x6845), op=FixSub, results=List(x6848))
      Stage(operands=List(x6846, CU.load(x6816), x6848), op=Mux, results=List(CU.scalarOut(bus_1574_s)))
    }
    val x6853_1 = Pipeline(name="x6853_1",parent=x6853) { implicit CU => 
      val x6849 = ScalarFIFO(size=1,name="x6849").wtPort(bus_1574_s)
      val x6844 = ScalarFIFO(size=1,name="x6844").wtPort(bus_1568_s)
      Stage(operands=List(CU.load(x6844), Const(16), CU.load(x6849)), op=Mux, results=List(CU.scalarOut(x6831_x6852_s)))
    }
    val x6887 = StreamController(name="x6887",parent=x6985) { implicit CU => 
      val x6887_unit = CounterChain(name = "x6887_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6873_0 = Pipeline(name="x6873_0",parent=x6887) { implicit CU => 
      val x6868 = CU.temp(None)
      val x6870 = CU.temp(None)
      val x6861 = CU.temp(None)
      val x6866 = ScalarFIFO(size=1,name="x6866").wtPort(x6521_x6521_dsp0_bank0_s)
      val x6830 = ScalarBuffer(name="x6830").wtPort(x6830_x6841_s)
      val x6815 = ScalarBuffer(name="x6815").wtPort(x6815_x6821_s)
      val x6862 = ScalarBuffer(name="x6862").wtPort(vec_dram_da)
      val ctr9 = Counter(min=Const(0), max=x6830.readPort, step=Const(1), par=1) // Counter
      val x6859 = CounterChain(name = "x6859", ctr9).iter(1)
      Stage(operands=List(CU.load(x6815), CU.ctr(x6859(0))), op=FixLeq, results=List(x6861))
      Stage(operands=List(CU.load(x6866), Const(2)), op=FixSla, results=List(x6868))
      Stage(operands=List(x6868, CU.load(x6862)), op=FixAdd, results=List(x6870))
      Stage(operands=List(x6861, CU.load(x6862), x6870), op=Mux, results=List(CU.scalarOut(x6855_x6872_s)))
    }
    val x6874 = MemoryController(name="x6874",parent=x6887,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x6855 = ScalarFIFO(size=1,name="addr").wtPort(x6855_x6872_s)
      CU.newSout("data", x6856_x6874_data_s)
    }
    val x6886 = Pipeline(name="x6886",parent=x6887) { implicit CU => 
      val x6830 = ScalarBuffer(name="x6830").wtPort(x6830_x6841_s)
      val x6815 = ScalarBuffer(name="x6815").wtPort(x6815_x6821_s)
      val ctr10 = Counter(min=Const(0), max=x6830.readPort, step=Const(1), par=1) // Counter
      val x6877 = CounterChain(name = "x6877", ctr10).iter(1)
    }
    val x6920 = StreamController(name="x6920",parent=x6985) { implicit CU => 
      val x6920_unit = CounterChain(name = "x6920_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6906_0 = Pipeline(name="x6906_0",parent=x6920) { implicit CU => 
      val x6901 = CU.temp(None)
      val x6903 = CU.temp(None)
      val x6894 = CU.temp(None)
      val x6895 = ScalarBuffer(name="x6895").wtPort(vec_dram_da)
      val x6831 = ScalarBuffer(name="x6831").wtPort(x6831_x6852_s)
      val x6816 = ScalarBuffer(name="x6816").wtPort(x6816_x6827_s)
      val x6899 = ScalarFIFO(size=1,name="x6899").wtPort(x6522_x6522_dsp0_bank0_s)
      val ctr11 = Counter(min=Const(0), max=x6831.readPort, step=Const(1), par=1) // Counter
      val x6892 = CounterChain(name = "x6892", ctr11).iter(1)
      Stage(operands=List(CU.load(x6816), CU.ctr(x6892(0))), op=FixLeq, results=List(x6894))
      Stage(operands=List(CU.load(x6899), Const(2)), op=FixSla, results=List(x6901))
      Stage(operands=List(x6901, CU.load(x6895)), op=FixAdd, results=List(x6903))
      Stage(operands=List(x6894, CU.load(x6895), x6903), op=Mux, results=List(CU.scalarOut(x6888_x6905_s)))
    }
    val x6907 = MemoryController(name="x6907",parent=x6920,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x6888 = ScalarFIFO(size=1,name="addr").wtPort(x6888_x6905_s)
      CU.newSout("data", x6889_x6907_data_s)
    }
    val x6919 = Pipeline(name="x6919",parent=x6920) { implicit CU => 
      val x6831 = ScalarBuffer(name="x6831").wtPort(x6831_x6852_s)
      val x6816 = ScalarBuffer(name="x6816").wtPort(x6816_x6827_s)
      val ctr12 = Counter(min=Const(0), max=x6831.readPort, step=Const(1), par=1) // Counter
      val x6910 = CounterChain(name = "x6910", ctr12).iter(1)
    }
    val x6959_0 = Pipeline(name="x6959_0",parent=x6985) { implicit CU => 
      val x6815 = ScalarBuffer(name="x6815").wtPort(x6815_x6821_s)
      val x6951 = ScalarFIFO(size=1,name="x6951").wtPort(x6525_x6525_dsp0_bank0_s)
      val x6950 = ScalarFIFO(size=1,name="x6950").wtPort(x6523_x6523_dsp0_bank0_s)
      val ctr13 = Counter(min=Const(0), max=x6815.readPort, step=Const(1), par=16) // Counter
      val x6947 = CounterChain(name = "x6947", ctr13).iter(1)
      Stage(operands=List(CU.load(x6950), CU.load(x6951)), op=FixMul, results=List(CU.reduce))
      val (_, rr1606) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x6959_0")
      Stage(operands=List(rr1606), op=Bypass, results=List(CU.scalarOut(x6926_x6958_s)))
    }
    val x6974_0 = Pipeline(name="x6974_0",parent=x6985) { implicit CU => 
      val x6966 = ScalarFIFO(size=1,name="x6966").wtPort(x6526_x6526_dsp0_bank0_s)
      val x6816 = ScalarBuffer(name="x6816").wtPort(x6816_x6827_s)
      val x6965 = ScalarFIFO(size=1,name="x6965").wtPort(x6524_x6524_dsp0_bank0_s)
      val ctr14 = Counter(min=Const(0), max=x6816.readPort, step=Const(1), par=16) // Counter
      val x6962 = CounterChain(name = "x6962", ctr14).iter(1)
      Stage(operands=List(CU.load(x6965), CU.load(x6966)), op=FixMul, results=List(CU.reduce))
      val (_, rr1613) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x6974_0")
      Stage(operands=List(rr1613), op=Bypass, results=List(CU.scalarOut(x6927_x6973_s)))
    }
    val x7452 = MetaPipeline(name="x7452",parent=x7588) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(494), step=Const(1), par=2) // Counter
      val x6987 = CounterChain(name = "x6987", ctr15).iter(247)
    }
    val x6988_dsp0_bank0 = MemoryPipeline(name="x6988_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7053 = ScalarBuffer(name="x7053").wtPort(x7021_b7707_x7050_b7715_s)
      val x7079 = ScalarFIFO(size=1,name="x7079").wtPort(x7022_x7052_data_s)
      val x7067 = CounterChain.copy("x7080_0", "x7067")
      val x7326 = CounterChain.copy("x7340_0", "x7326")
      val x6988 = SRAM(size=494,name="x6988",banking = Strided(1)).wtPort(x7079.readPort).rdPort(x6988_x6988_dsp0_bank0_s).rdAddr(x7326(0))
      WAStage(operands=List(CU.ctr(x7067(0)), CU.load(x7053)), op=FixSub, results=List(x6988.writeAddr))
    }
    val x6989_dsp0_bank0 = MemoryPipeline(name="x6989_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7187 = ScalarBuffer(name="x7187").wtPort(x7155_b7733_x7184_b7741_s)
      val x7213 = ScalarFIFO(size=1,name="x7213").wtPort(x7156_x7186_data_s)
      val x7201 = CounterChain.copy("x7214_0", "x7201")
      val x7359 = CounterChain.copy("x7373_0", "x7359")
      val x6989 = SRAM(size=494,name="x6989",banking = Strided(1)).wtPort(x7213.readPort).rdPort(x6989_x6989_dsp0_bank0_s).rdAddr(x7359(0))
      WAStage(operands=List(CU.ctr(x7201(0)), CU.load(x7187)), op=FixSub, results=List(x6989.writeAddr))
    }
    val x6990_dsp0_bank0 = MemoryPipeline(name="x6990_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7116 = ScalarBuffer(name="x7116").wtPort(x7084_b7720_x7113_b7728_s)
      val x7142 = ScalarFIFO(size=1,name="x7142").wtPort(x7085_x7115_data_s)
      val x7130 = CounterChain.copy("x7143_0", "x7130")
      val x7414 = CounterChain.copy("x7426_0", "x7414")
      val x6990 = SRAM(size=494,name="x6990",banking = Strided(1)).wtPort(x7142.readPort).rdPort(x6990_x6990_dsp0_bank0_s).rdAddr(x7414(0))
      WAStage(operands=List(CU.ctr(x7130(0)), CU.load(x7116)), op=FixSub, results=List(x6990.writeAddr))
    }
    val x6991_dsp0_bank0 = MemoryPipeline(name="x6991_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7250 = ScalarBuffer(name="x7250").wtPort(x7218_b7746_x7247_b7754_s)
      val x7276 = ScalarFIFO(size=1,name="x7276").wtPort(x7219_x7249_data_s)
      val x7264 = CounterChain.copy("x7277_0", "x7264")
      val x7429 = CounterChain.copy("x7441_0", "x7429")
      val x6991 = SRAM(size=494,name="x6991",banking = Strided(1)).wtPort(x7276.readPort).rdPort(x6991_x6991_dsp0_bank0_s).rdAddr(x7429(0))
      WAStage(operands=List(CU.ctr(x7264(0)), CU.load(x7250)), op=FixSub, results=List(x6991.writeAddr))
    }
    val x6992_dsp0_bank0 = MemoryPipeline(name="x6992_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7352 = ScalarFIFO(size=1,name="x7352").wtPort(x7323_x7341_data_s)
      val x7344 = CounterChain.copy("x7353", "x7344")
      val x7414 = CounterChain.copy("x7426_0", "x7414")
      val x6992 = SRAM(size=494,name="x6992",banking = Strided(1)).wtPort(x7352.readPort).rdPort(x6992_x6992_dsp0_bank0_s).rdAddr(x7414(0)).wtAddr(x7344(0))
    }
    val x6993_dsp0_bank0 = MemoryPipeline(name="x6993_dsp0_bank0",parent="x7452") { implicit CU => 
      val x7385 = ScalarFIFO(size=1,name="x7385").wtPort(x7356_x7374_data_s)
      val x7377 = CounterChain.copy("x7386", "x7377")
      val x7429 = CounterChain.copy("x7441_0", "x7429")
      val x6993 = SRAM(size=494,name="x6993",banking = Strided(1)).wtPort(x7385.readPort).rdPort(x6993_x6993_dsp0_bank0_s).rdAddr(x7429(0)).wtAddr(x7377(0))
    }
    val x7019_0 = Pipeline(name="x7019_0",parent=x7452) { implicit CU => 
      val x6994 = ScalarBuffer(name="x6994").wtPort(x6372_x6372_dsp1_bank0_s)
      val x6996 = ScalarBuffer(name="x6996").wtPort(x6372_x6372_dsp0_bank0_s)
      val x7019_unit = CounterChain(name = "x7019_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6996), CU.load(x6994)), op=FixSub, results=List(CU.scalarOut(x7013_x7018_s)))
    }
    val x7082 = StreamController(name="x7082",parent=x7452) { implicit CU => 
      val x7082_unit = CounterChain(name = "x7082_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7051 = StreamController(name="x7051",parent=x7082) { implicit CU => 
      val x7051_unit = CounterChain(name = "x7051_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7051_0 = Pipeline(name="x7051_0",parent=x7051) { implicit CU => 
      val x7025 = CU.temp(None)
      val x7026 = CU.temp(None)
      val x7049_x7035 = CU.temp(None)
      val x6994 = ScalarBuffer(name="x6994").wtPort(x6372_x6372_dsp1_bank0_s)
      val x7013 = ScalarBuffer(name="x7013").wtPort(x7013_x7018_s)
      Stage(operands=List(CU.load(x6994), Const(2)), op=FixSla, results=List(x7025, CU.scalarOut(bus_1642_s)))
      Stage(operands=List(x7025, Const(64)), op=FixMod, results=List(x7026, CU.scalarOut(bus_1644_s)))
      Stage(operands=List(x7026, Const(2)), op=FixSra, results=List(x7049_x7035, CU.scalarOut(bus_1645_s), CU.scalarOut(x7021_b7707_x7050_b7715_s)))
      Stage(operands=List(x7049_x7035, CU.load(x7013)), op=FixAdd, results=List(CU.scalarOut(x7021_b7708_x7050_b7716_s)))
      Stage(operands=List(CU.load(x7013), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1647_s)))
    }
    val x7051_1 = Pipeline(name="x7051_1",parent=x7051) { implicit CU => 
      val x7033 = CU.temp(None)
      val x7030 = CU.temp(None)
      val x7032 = CU.temp(None)
      val x7031 = CU.temp(None)
      val x7034 = CU.temp(None)
      val x7028 = ScalarFIFO(size=1,name="x7028").wtPort(bus_1647_s)
      val x7025 = ScalarFIFO(size=1,name="x7025").wtPort(bus_1642_s)
      Stage(operands=List(CU.load(x7025), CU.load(x7028)), op=FixAdd, results=List(x7030))
      Stage(operands=List(x7030, Const(64)), op=FixMod, results=List(x7031))
      Stage(operands=List(x7031, Const(0)), op=FixEql, results=List(x7032))
      Stage(operands=List(Const(64), x7031), op=FixSub, results=List(x7033))
      Stage(operands=List(x7032, Const(0), x7033), op=Mux, results=List(x7034, CU.scalarOut(bus_1653_s)))
      Stage(operands=List(x7034, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1654_s)))
    }
    val x7051_2 = Pipeline(name="x7051_2",parent=x7051) { implicit CU => 
      val x7040 = CU.temp(None)
      val x7038 = CU.temp(None)
      val x7034 = ScalarFIFO(size=1,name="x7034").wtPort(bus_1653_s)
      val x7028 = ScalarFIFO(size=1,name="x7028").wtPort(bus_1647_s)
      val x7026 = ScalarFIFO(size=1,name="x7026").wtPort(bus_1644_s)
      val x7036 = ScalarFIFO(size=1,name="x7036").wtPort(bus_1654_s)
      val x7035 = ScalarFIFO(size=1,name="x7035").wtPort(bus_1645_s)
      val x7013 = ScalarBuffer(name="x7013").wtPort(x7013_x7018_s)
      val x7025 = ScalarFIFO(size=1,name="x7025").wtPort(bus_1642_s)
      Stage(operands=List(CU.load(x7013), CU.load(x7035)), op=FixAdd, results=List(x7038))
      Stage(operands=List(x7038, CU.load(x7036)), op=FixAdd, results=List(CU.scalarOut(x7021_b7706_x7050_b7714_s)))
      Stage(operands=List(CU.load(x7028), CU.load(x7026)), op=FixAdd, results=List(x7040))
      Stage(operands=List(x7040, CU.load(x7034)), op=FixAdd, results=List(CU.scalarOut(x7020_b7705_x7048_b7713_s)))
      Stage(operands=List(CU.load(x7025), CU.load(x7026)), op=FixSub, results=List(CU.scalarOut(bus_1659_s)))
    }
    val x7051_3 = Pipeline(name="x7051_3",parent=x7051) { implicit CU => 
      val x7029 = ScalarFIFO(size=1,name="x7029").wtPort(bus_1659_s)
      val x7043 = ScalarBuffer(name="x7043").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x7029), CU.load(x7043)), op=FixAdd, results=List(CU.scalarOut(x7020_b7704_x7048_b7712_s)))
    }
    val x7052 = MemoryController(name="x7052",parent=x7082,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7020_b7705 = ScalarFIFO(size=1,name="size").wtPort(x7020_b7705_x7048_b7713_s)
      val x7020_b7704 = ScalarFIFO(size=1,name="offset").wtPort(x7020_b7704_x7048_b7712_s)
      CU.newSout("data", x7022_x7052_data_s)
    }
    val x7081 = Sequential(name="x7081",parent=x7082) { implicit CU => 
      val x7081_unit = CounterChain(name = "x7081_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7080_0 = Pipeline(name="x7080_0",parent=x7081) { implicit CU => 
      val x7054 = ScalarBuffer(name="x7054").wtPort(x7021_b7708_x7050_b7716_s)
      val x7053 = ScalarBuffer(name="x7053").wtPort(x7021_b7707_x7050_b7715_s)
      val x7055 = ScalarBuffer(name="x7055").wtPort(x7021_b7706_x7050_b7714_s)
      val ctr16 = Counter(min=Const(0), max=x7055.readPort, step=Const(1), par=16) // Counter
      val x7067 = CounterChain(name = "x7067", ctr16).iter(1)
      Stage(operands=List(CU.load(x7053), CU.ctr(x7067(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x7067(0)), CU.load(x7054)), op=FixLt, results=List())
    }
    val x7145 = StreamController(name="x7145",parent=x7452) { implicit CU => 
      val x7145_unit = CounterChain(name = "x7145_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7114 = StreamController(name="x7114",parent=x7145) { implicit CU => 
      val x7114_unit = CounterChain(name = "x7114_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7114_0 = Pipeline(name="x7114_0",parent=x7114) { implicit CU => 
      val x7112_x7098 = CU.temp(None)
      val x7089 = CU.temp(None)
      val x7088 = CU.temp(None)
      val x6994 = ScalarBuffer(name="x6994").wtPort(x6372_x6372_dsp1_bank0_s)
      val x7013 = ScalarBuffer(name="x7013").wtPort(x7013_x7018_s)
      Stage(operands=List(CU.load(x6994), Const(2)), op=FixSla, results=List(x7088, CU.scalarOut(bus_1671_s)))
      Stage(operands=List(x7088, Const(64)), op=FixMod, results=List(x7089, CU.scalarOut(bus_1673_s)))
      Stage(operands=List(x7089, Const(2)), op=FixSra, results=List(x7112_x7098, CU.scalarOut(bus_1674_s), CU.scalarOut(x7084_b7720_x7113_b7728_s)))
      Stage(operands=List(x7112_x7098, CU.load(x7013)), op=FixAdd, results=List(CU.scalarOut(x7084_b7721_x7113_b7729_s)))
      Stage(operands=List(CU.load(x7013), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1676_s)))
    }
    val x7114_1 = Pipeline(name="x7114_1",parent=x7114) { implicit CU => 
      val x7095 = CU.temp(None)
      val x7093 = CU.temp(None)
      val x7096 = CU.temp(None)
      val x7097 = CU.temp(None)
      val x7094 = CU.temp(None)
      val x7091 = ScalarFIFO(size=1,name="x7091").wtPort(bus_1676_s)
      val x7088 = ScalarFIFO(size=1,name="x7088").wtPort(bus_1671_s)
      Stage(operands=List(CU.load(x7088), CU.load(x7091)), op=FixAdd, results=List(x7093))
      Stage(operands=List(x7093, Const(64)), op=FixMod, results=List(x7094))
      Stage(operands=List(x7094, Const(0)), op=FixEql, results=List(x7095))
      Stage(operands=List(Const(64), x7094), op=FixSub, results=List(x7096))
      Stage(operands=List(x7095, Const(0), x7096), op=Mux, results=List(x7097, CU.scalarOut(bus_1682_s)))
      Stage(operands=List(x7097, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1683_s)))
    }
    val x7114_2 = Pipeline(name="x7114_2",parent=x7114) { implicit CU => 
      val x7103 = CU.temp(None)
      val x7101 = CU.temp(None)
      val x7097 = ScalarFIFO(size=1,name="x7097").wtPort(bus_1682_s)
      val x7091 = ScalarFIFO(size=1,name="x7091").wtPort(bus_1676_s)
      val x7099 = ScalarFIFO(size=1,name="x7099").wtPort(bus_1683_s)
      val x7088 = ScalarFIFO(size=1,name="x7088").wtPort(bus_1671_s)
      val x7013 = ScalarBuffer(name="x7013").wtPort(x7013_x7018_s)
      val x7089 = ScalarFIFO(size=1,name="x7089").wtPort(bus_1673_s)
      val x7098 = ScalarFIFO(size=1,name="x7098").wtPort(bus_1674_s)
      Stage(operands=List(CU.load(x7013), CU.load(x7098)), op=FixAdd, results=List(x7101))
      Stage(operands=List(x7101, CU.load(x7099)), op=FixAdd, results=List(CU.scalarOut(x7084_b7719_x7113_b7727_s)))
      Stage(operands=List(CU.load(x7091), CU.load(x7089)), op=FixAdd, results=List(x7103))
      Stage(operands=List(x7103, CU.load(x7097)), op=FixAdd, results=List(CU.scalarOut(x7083_b7718_x7111_b7726_s)))
      Stage(operands=List(CU.load(x7088), CU.load(x7089)), op=FixSub, results=List(CU.scalarOut(bus_1688_s)))
    }
    val x7114_3 = Pipeline(name="x7114_3",parent=x7114) { implicit CU => 
      val x7092 = ScalarFIFO(size=1,name="x7092").wtPort(bus_1688_s)
      val x7106 = ScalarBuffer(name="x7106").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x7092), CU.load(x7106)), op=FixAdd, results=List(CU.scalarOut(x7083_b7717_x7111_b7725_s)))
    }
    val x7115 = MemoryController(name="x7115",parent=x7145,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7083_b7718 = ScalarFIFO(size=1,name="size").wtPort(x7083_b7718_x7111_b7726_s)
      val x7083_b7717 = ScalarFIFO(size=1,name="offset").wtPort(x7083_b7717_x7111_b7725_s)
      CU.newSout("data", x7085_x7115_data_s)
    }
    val x7144 = Sequential(name="x7144",parent=x7145) { implicit CU => 
      val x7144_unit = CounterChain(name = "x7144_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7143_0 = Pipeline(name="x7143_0",parent=x7144) { implicit CU => 
      val x7117 = ScalarBuffer(name="x7117").wtPort(x7084_b7721_x7113_b7729_s)
      val x7116 = ScalarBuffer(name="x7116").wtPort(x7084_b7720_x7113_b7728_s)
      val x7118 = ScalarBuffer(name="x7118").wtPort(x7084_b7719_x7113_b7727_s)
      val ctr17 = Counter(min=Const(0), max=x7118.readPort, step=Const(1), par=16) // Counter
      val x7130 = CounterChain(name = "x7130", ctr17).iter(1)
      Stage(operands=List(CU.load(x7116), CU.ctr(x7130(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x7130(0)), CU.load(x7117)), op=FixLt, results=List())
    }
    val x7153_0 = Pipeline(name="x7153_0",parent=x7452) { implicit CU => 
      val x6997 = ScalarBuffer(name="x6997").wtPort(x6372_x6372_dsp0_bank0_s)
      val x6995 = ScalarBuffer(name="x6995").wtPort(x6372_x6372_dsp1_bank0_s)
      val x7153_unit = CounterChain(name = "x7153_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6997), CU.load(x6995)), op=FixSub, results=List(CU.scalarOut(x7147_x7152_s)))
    }
    val x7216 = StreamController(name="x7216",parent=x7452) { implicit CU => 
      val x7216_unit = CounterChain(name = "x7216_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7185 = StreamController(name="x7185",parent=x7216) { implicit CU => 
      val x7185_unit = CounterChain(name = "x7185_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7185_0 = Pipeline(name="x7185_0",parent=x7185) { implicit CU => 
      val x7183_x7169 = CU.temp(None)
      val x7159 = CU.temp(None)
      val x7160 = CU.temp(None)
      val x7147 = ScalarBuffer(name="x7147").wtPort(x7147_x7152_s)
      val x6995 = ScalarBuffer(name="x6995").wtPort(x6372_x6372_dsp1_bank0_s)
      Stage(operands=List(CU.load(x6995), Const(2)), op=FixSla, results=List(x7159, CU.scalarOut(bus_1702_s)))
      Stage(operands=List(x7159, Const(64)), op=FixMod, results=List(x7160, CU.scalarOut(bus_1704_s)))
      Stage(operands=List(x7160, Const(2)), op=FixSra, results=List(x7183_x7169, CU.scalarOut(bus_1705_s), CU.scalarOut(x7155_b7733_x7184_b7741_s)))
      Stage(operands=List(x7183_x7169, CU.load(x7147)), op=FixAdd, results=List(CU.scalarOut(x7155_b7734_x7184_b7742_s)))
      Stage(operands=List(CU.load(x7147), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1707_s)))
    }
    val x7185_1 = Pipeline(name="x7185_1",parent=x7185) { implicit CU => 
      val x7166 = CU.temp(None)
      val x7168 = CU.temp(None)
      val x7164 = CU.temp(None)
      val x7165 = CU.temp(None)
      val x7167 = CU.temp(None)
      val x7162 = ScalarFIFO(size=1,name="x7162").wtPort(bus_1707_s)
      val x7159 = ScalarFIFO(size=1,name="x7159").wtPort(bus_1702_s)
      Stage(operands=List(CU.load(x7159), CU.load(x7162)), op=FixAdd, results=List(x7164))
      Stage(operands=List(x7164, Const(64)), op=FixMod, results=List(x7165))
      Stage(operands=List(x7165, Const(0)), op=FixEql, results=List(x7166))
      Stage(operands=List(Const(64), x7165), op=FixSub, results=List(x7167))
      Stage(operands=List(x7166, Const(0), x7167), op=Mux, results=List(x7168, CU.scalarOut(bus_1713_s)))
      Stage(operands=List(x7168, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1714_s)))
    }
    val x7185_2 = Pipeline(name="x7185_2",parent=x7185) { implicit CU => 
      val x7172 = CU.temp(None)
      val x7174 = CU.temp(None)
      val x7159 = ScalarFIFO(size=1,name="x7159").wtPort(bus_1702_s)
      val x7169 = ScalarFIFO(size=1,name="x7169").wtPort(bus_1705_s)
      val x7162 = ScalarFIFO(size=1,name="x7162").wtPort(bus_1707_s)
      val x7147 = ScalarBuffer(name="x7147").wtPort(x7147_x7152_s)
      val x7160 = ScalarFIFO(size=1,name="x7160").wtPort(bus_1704_s)
      val x7170 = ScalarFIFO(size=1,name="x7170").wtPort(bus_1714_s)
      val x7168 = ScalarFIFO(size=1,name="x7168").wtPort(bus_1713_s)
      Stage(operands=List(CU.load(x7147), CU.load(x7169)), op=FixAdd, results=List(x7172))
      Stage(operands=List(x7172, CU.load(x7170)), op=FixAdd, results=List(CU.scalarOut(x7155_b7732_x7184_b7740_s)))
      Stage(operands=List(CU.load(x7162), CU.load(x7160)), op=FixAdd, results=List(x7174))
      Stage(operands=List(x7174, CU.load(x7168)), op=FixAdd, results=List(CU.scalarOut(x7154_b7731_x7182_b7739_s)))
      Stage(operands=List(CU.load(x7159), CU.load(x7160)), op=FixSub, results=List(CU.scalarOut(bus_1719_s)))
    }
    val x7185_3 = Pipeline(name="x7185_3",parent=x7185) { implicit CU => 
      val x7163 = ScalarFIFO(size=1,name="x7163").wtPort(bus_1719_s)
      val x7177 = ScalarBuffer(name="x7177").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x7163), CU.load(x7177)), op=FixAdd, results=List(CU.scalarOut(x7154_b7730_x7182_b7738_s)))
    }
    val x7186 = MemoryController(name="x7186",parent=x7216,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7154_b7730 = ScalarFIFO(size=1,name="offset").wtPort(x7154_b7730_x7182_b7738_s)
      val x7154_b7731 = ScalarFIFO(size=1,name="size").wtPort(x7154_b7731_x7182_b7739_s)
      CU.newSout("data", x7156_x7186_data_s)
    }
    val x7215 = Sequential(name="x7215",parent=x7216) { implicit CU => 
      val x7215_unit = CounterChain(name = "x7215_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7214_0 = Pipeline(name="x7214_0",parent=x7215) { implicit CU => 
      val x7189 = ScalarBuffer(name="x7189").wtPort(x7155_b7732_x7184_b7740_s)
      val x7188 = ScalarBuffer(name="x7188").wtPort(x7155_b7734_x7184_b7742_s)
      val x7187 = ScalarBuffer(name="x7187").wtPort(x7155_b7733_x7184_b7741_s)
      val ctr18 = Counter(min=Const(0), max=x7189.readPort, step=Const(1), par=16) // Counter
      val x7201 = CounterChain(name = "x7201", ctr18).iter(1)
      Stage(operands=List(CU.load(x7187), CU.ctr(x7201(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x7201(0)), CU.load(x7188)), op=FixLt, results=List())
    }
    val x7279 = StreamController(name="x7279",parent=x7452) { implicit CU => 
      val x7279_unit = CounterChain(name = "x7279_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7248 = StreamController(name="x7248",parent=x7279) { implicit CU => 
      val x7248_unit = CounterChain(name = "x7248_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7248_0 = Pipeline(name="x7248_0",parent=x7248) { implicit CU => 
      val x7222 = CU.temp(None)
      val x7246_x7232 = CU.temp(None)
      val x7223 = CU.temp(None)
      val x7147 = ScalarBuffer(name="x7147").wtPort(x7147_x7152_s)
      val x6995 = ScalarBuffer(name="x6995").wtPort(x6372_x6372_dsp1_bank0_s)
      Stage(operands=List(CU.load(x6995), Const(2)), op=FixSla, results=List(x7222, CU.scalarOut(bus_1731_s)))
      Stage(operands=List(x7222, Const(64)), op=FixMod, results=List(x7223, CU.scalarOut(bus_1733_s)))
      Stage(operands=List(x7223, Const(2)), op=FixSra, results=List(x7246_x7232, CU.scalarOut(bus_1734_s), CU.scalarOut(x7218_b7746_x7247_b7754_s)))
      Stage(operands=List(x7246_x7232, CU.load(x7147)), op=FixAdd, results=List(CU.scalarOut(x7218_b7747_x7247_b7755_s)))
      Stage(operands=List(CU.load(x7147), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1736_s)))
    }
    val x7248_1 = Pipeline(name="x7248_1",parent=x7248) { implicit CU => 
      val x7227 = CU.temp(None)
      val x7229 = CU.temp(None)
      val x7230 = CU.temp(None)
      val x7231 = CU.temp(None)
      val x7228 = CU.temp(None)
      val x7225 = ScalarFIFO(size=1,name="x7225").wtPort(bus_1736_s)
      val x7222 = ScalarFIFO(size=1,name="x7222").wtPort(bus_1731_s)
      Stage(operands=List(CU.load(x7222), CU.load(x7225)), op=FixAdd, results=List(x7227))
      Stage(operands=List(x7227, Const(64)), op=FixMod, results=List(x7228))
      Stage(operands=List(x7228, Const(0)), op=FixEql, results=List(x7229))
      Stage(operands=List(Const(64), x7228), op=FixSub, results=List(x7230))
      Stage(operands=List(x7229, Const(0), x7230), op=Mux, results=List(x7231, CU.scalarOut(bus_1742_s)))
      Stage(operands=List(x7231, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_1743_s)))
    }
    val x7248_2 = Pipeline(name="x7248_2",parent=x7248) { implicit CU => 
      val x7235 = CU.temp(None)
      val x7237 = CU.temp(None)
      val x7147 = ScalarBuffer(name="x7147").wtPort(x7147_x7152_s)
      val x7225 = ScalarFIFO(size=1,name="x7225").wtPort(bus_1736_s)
      val x7223 = ScalarFIFO(size=1,name="x7223").wtPort(bus_1733_s)
      val x7233 = ScalarFIFO(size=1,name="x7233").wtPort(bus_1743_s)
      val x7232 = ScalarFIFO(size=1,name="x7232").wtPort(bus_1734_s)
      val x7222 = ScalarFIFO(size=1,name="x7222").wtPort(bus_1731_s)
      val x7231 = ScalarFIFO(size=1,name="x7231").wtPort(bus_1742_s)
      Stage(operands=List(CU.load(x7147), CU.load(x7232)), op=FixAdd, results=List(x7235))
      Stage(operands=List(x7235, CU.load(x7233)), op=FixAdd, results=List(CU.scalarOut(x7218_b7745_x7247_b7753_s)))
      Stage(operands=List(CU.load(x7225), CU.load(x7223)), op=FixAdd, results=List(x7237))
      Stage(operands=List(x7237, CU.load(x7231)), op=FixAdd, results=List(CU.scalarOut(x7217_b7744_x7245_b7752_s)))
      Stage(operands=List(CU.load(x7222), CU.load(x7223)), op=FixSub, results=List(CU.scalarOut(bus_1748_s)))
    }
    val x7248_3 = Pipeline(name="x7248_3",parent=x7248) { implicit CU => 
      val x7226 = ScalarFIFO(size=1,name="x7226").wtPort(bus_1748_s)
      val x7240 = ScalarBuffer(name="x7240").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x7226), CU.load(x7240)), op=FixAdd, results=List(CU.scalarOut(x7217_b7743_x7245_b7751_s)))
    }
    val x7249 = MemoryController(name="x7249",parent=x7279,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7217_b7744 = ScalarFIFO(size=1,name="size").wtPort(x7217_b7744_x7245_b7752_s)
      val x7217_b7743 = ScalarFIFO(size=1,name="offset").wtPort(x7217_b7743_x7245_b7751_s)
      CU.newSout("data", x7219_x7249_data_s)
    }
    val x7278 = Sequential(name="x7278",parent=x7279) { implicit CU => 
      val x7278_unit = CounterChain(name = "x7278_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7277_0 = Pipeline(name="x7277_0",parent=x7278) { implicit CU => 
      val x7252 = ScalarBuffer(name="x7252").wtPort(x7218_b7745_x7247_b7753_s)
      val x7251 = ScalarBuffer(name="x7251").wtPort(x7218_b7747_x7247_b7755_s)
      val x7250 = ScalarBuffer(name="x7250").wtPort(x7218_b7746_x7247_b7754_s)
      val ctr19 = Counter(min=Const(0), max=x7252.readPort, step=Const(1), par=16) // Counter
      val x7264 = CounterChain(name = "x7264", ctr19).iter(1)
      Stage(operands=List(CU.load(x7250), CU.ctr(x7264(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x7264(0)), CU.load(x7251)), op=FixLt, results=List())
    }
    val x7289_0 = Pipeline(name="x7289_0",parent=x7452) { implicit CU => 
      val x6994 = ScalarBuffer(name="x6994").wtPort(x6372_x6372_dsp1_bank0_s)
      val x6996 = ScalarBuffer(name="x6996").wtPort(x6372_x6372_dsp0_bank0_s)
      val x7289_unit = CounterChain(name = "x7289_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6996), CU.load(x6994)), op=FixSub, results=List(CU.scalarOut(x7282_x7288_s)))
    }
    val x7295_0 = Pipeline(name="x7295_0",parent=x7452) { implicit CU => 
      val x6997 = ScalarBuffer(name="x6997").wtPort(x6372_x6372_dsp0_bank0_s)
      val x6995 = ScalarBuffer(name="x6995").wtPort(x6372_x6372_dsp1_bank0_s)
      val x7295_unit = CounterChain(name = "x7295_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6997), CU.load(x6995)), op=FixSub, results=List(CU.scalarOut(x7283_x7294_s)))
    }
    val x7309 = StreamController(name="x7309",parent=x7452) { implicit CU => 
      val x7309_unit = CounterChain(name = "x7309_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7309_0 = Pipeline(name="x7309_0",parent=x7309) { implicit CU => 
      val x7303 = CU.temp(None)
      val x7304 = CU.temp(None)
      val x7301 = CU.temp(None)
      val x7302 = CU.temp(None)
      val x7282 = ScalarBuffer(name="x7282").wtPort(x7282_x7288_s)
      Stage(operands=List(CU.load(x7282), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1764_s)))
      Stage(operands=List(CU.load(x7282), Const(16)), op=FixMod, results=List(x7301))
      Stage(operands=List(x7301, Const(0)), op=FixEql, results=List(x7302))
      Stage(operands=List(CU.load(x7282), Const(16)), op=FixAdd, results=List(x7303))
      Stage(operands=List(x7303, x7301), op=FixSub, results=List(x7304))
      Stage(operands=List(x7302, CU.load(x7282), x7304), op=Mux, results=List(CU.scalarOut(bus_1770_s)))
    }
    val x7309_1 = Pipeline(name="x7309_1",parent=x7309) { implicit CU => 
      val x7305 = ScalarFIFO(size=1,name="x7305").wtPort(bus_1770_s)
      val x7300 = ScalarFIFO(size=1,name="x7300").wtPort(bus_1764_s)
      Stage(operands=List(CU.load(x7300), Const(16), CU.load(x7305)), op=Mux, results=List(CU.scalarOut(x7297_x7308_s)))
    }
    val x7320 = StreamController(name="x7320",parent=x7452) { implicit CU => 
      val x7320_unit = CounterChain(name = "x7320_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7320_0 = Pipeline(name="x7320_0",parent=x7320) { implicit CU => 
      val x7314 = CU.temp(None)
      val x7313 = CU.temp(None)
      val x7312 = CU.temp(None)
      val x7315 = CU.temp(None)
      val x7283 = ScalarBuffer(name="x7283").wtPort(x7283_x7294_s)
      Stage(operands=List(CU.load(x7283), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1774_s)))
      Stage(operands=List(CU.load(x7283), Const(16)), op=FixMod, results=List(x7312))
      Stage(operands=List(x7312, Const(0)), op=FixEql, results=List(x7313))
      Stage(operands=List(CU.load(x7283), Const(16)), op=FixAdd, results=List(x7314))
      Stage(operands=List(x7314, x7312), op=FixSub, results=List(x7315))
      Stage(operands=List(x7313, CU.load(x7283), x7315), op=Mux, results=List(CU.scalarOut(bus_1780_s)))
    }
    val x7320_1 = Pipeline(name="x7320_1",parent=x7320) { implicit CU => 
      val x7316 = ScalarFIFO(size=1,name="x7316").wtPort(bus_1780_s)
      val x7311 = ScalarFIFO(size=1,name="x7311").wtPort(bus_1774_s)
      Stage(operands=List(CU.load(x7311), Const(16), CU.load(x7316)), op=Mux, results=List(CU.scalarOut(x7298_x7319_s)))
    }
    val x7354 = StreamController(name="x7354",parent=x7452) { implicit CU => 
      val x7354_unit = CounterChain(name = "x7354_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7340_0 = Pipeline(name="x7340_0",parent=x7354) { implicit CU => 
      val x7328 = CU.temp(None)
      val x7335 = CU.temp(None)
      val x7337 = CU.temp(None)
      val x7329 = ScalarBuffer(name="x7329").wtPort(vec_dram_da)
      val x7333 = ScalarFIFO(size=1,name="x7333").wtPort(x6988_x6988_dsp0_bank0_s)
      val x7297 = ScalarBuffer(name="x7297").wtPort(x7297_x7308_s)
      val x7282 = ScalarBuffer(name="x7282").wtPort(x7282_x7288_s)
      val ctr20 = Counter(min=Const(0), max=x7297.readPort, step=Const(1), par=1) // Counter
      val x7326 = CounterChain(name = "x7326", ctr20).iter(1)
      Stage(operands=List(CU.load(x7282), CU.ctr(x7326(0))), op=FixLeq, results=List(x7328))
      Stage(operands=List(CU.load(x7333), Const(2)), op=FixSla, results=List(x7335))
      Stage(operands=List(x7335, CU.load(x7329)), op=FixAdd, results=List(x7337))
      Stage(operands=List(x7328, CU.load(x7329), x7337), op=Mux, results=List(CU.scalarOut(x7322_x7339_s)))
    }
    val x7341 = MemoryController(name="x7341",parent=x7354,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x7322 = ScalarFIFO(size=1,name="addr").wtPort(x7322_x7339_s)
      CU.newSout("data", x7323_x7341_data_s)
    }
    val x7353 = Pipeline(name="x7353",parent=x7354) { implicit CU => 
      val x7297 = ScalarBuffer(name="x7297").wtPort(x7297_x7308_s)
      val x7282 = ScalarBuffer(name="x7282").wtPort(x7282_x7288_s)
      val ctr21 = Counter(min=Const(0), max=x7297.readPort, step=Const(1), par=1) // Counter
      val x7344 = CounterChain(name = "x7344", ctr21).iter(1)
    }
    val x7387 = StreamController(name="x7387",parent=x7452) { implicit CU => 
      val x7387_unit = CounterChain(name = "x7387_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7373_0 = Pipeline(name="x7373_0",parent=x7387) { implicit CU => 
      val x7368 = CU.temp(None)
      val x7361 = CU.temp(None)
      val x7370 = CU.temp(None)
      val x7362 = ScalarBuffer(name="x7362").wtPort(vec_dram_da)
      val x7283 = ScalarBuffer(name="x7283").wtPort(x7283_x7294_s)
      val x7298 = ScalarBuffer(name="x7298").wtPort(x7298_x7319_s)
      val x7366 = ScalarFIFO(size=1,name="x7366").wtPort(x6989_x6989_dsp0_bank0_s)
      val ctr22 = Counter(min=Const(0), max=x7298.readPort, step=Const(1), par=1) // Counter
      val x7359 = CounterChain(name = "x7359", ctr22).iter(1)
      Stage(operands=List(CU.load(x7283), CU.ctr(x7359(0))), op=FixLeq, results=List(x7361))
      Stage(operands=List(CU.load(x7366), Const(2)), op=FixSla, results=List(x7368))
      Stage(operands=List(x7368, CU.load(x7362)), op=FixAdd, results=List(x7370))
      Stage(operands=List(x7361, CU.load(x7362), x7370), op=Mux, results=List(CU.scalarOut(x7355_x7372_s)))
    }
    val x7374 = MemoryController(name="x7374",parent=x7387,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x7355 = ScalarFIFO(size=1,name="addr").wtPort(x7355_x7372_s)
      CU.newSout("data", x7356_x7374_data_s)
    }
    val x7386 = Pipeline(name="x7386",parent=x7387) { implicit CU => 
      val x7283 = ScalarBuffer(name="x7283").wtPort(x7283_x7294_s)
      val x7298 = ScalarBuffer(name="x7298").wtPort(x7298_x7319_s)
      val ctr23 = Counter(min=Const(0), max=x7298.readPort, step=Const(1), par=1) // Counter
      val x7377 = CounterChain(name = "x7377", ctr23).iter(1)
    }
    val x7426_0 = Pipeline(name="x7426_0",parent=x7452) { implicit CU => 
      val x7418 = ScalarFIFO(size=1,name="x7418").wtPort(x6992_x6992_dsp0_bank0_s)
      val x7417 = ScalarFIFO(size=1,name="x7417").wtPort(x6990_x6990_dsp0_bank0_s)
      val x7282 = ScalarBuffer(name="x7282").wtPort(x7282_x7288_s)
      val ctr24 = Counter(min=Const(0), max=x7282.readPort, step=Const(1), par=16) // Counter
      val x7414 = CounterChain(name = "x7414", ctr24).iter(1)
      Stage(operands=List(CU.load(x7417), CU.load(x7418)), op=FixMul, results=List(CU.reduce))
      val (_, rr1812) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x7426_0")
      Stage(operands=List(rr1812), op=Bypass, results=List(CU.scalarOut(x7393_x7425_s)))
    }
    val x7441_0 = Pipeline(name="x7441_0",parent=x7452) { implicit CU => 
      val x7433 = ScalarFIFO(size=1,name="x7433").wtPort(x6993_x6993_dsp0_bank0_s)
      val x7283 = ScalarBuffer(name="x7283").wtPort(x7283_x7294_s)
      val x7432 = ScalarFIFO(size=1,name="x7432").wtPort(x6991_x6991_dsp0_bank0_s)
      val ctr25 = Counter(min=Const(0), max=x7283.readPort, step=Const(1), par=16) // Counter
      val x7429 = CounterChain(name = "x7429", ctr25).iter(1)
      Stage(operands=List(CU.load(x7432), CU.load(x7433)), op=FixMul, results=List(CU.reduce))
      val (_, rr1819) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x7441_0")
      Stage(operands=List(rr1819), op=Bypass, results=List(CU.scalarOut(x7394_x7440_s)))
    }
    val x7464_0 = Pipeline(name="x7464_0",parent=x7588) { implicit CU => 
      val x7458 = CU.temp(None)
      val x7460 = CU.temp(None)
      val x6377 = ScalarBuffer(name="x6377").wtPort(x6377_x6386_s)
      val x6370 = CounterChain.copy("x7588", "x6370")
      val x7464_unit = CounterChain(name = "x7464_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6370(0)), Const(494)), op=FixMul, results=List(x7458, CU.scalarOut(x7454_x7462_s)))
      Stage(operands=List(CU.load(x6377), Const(494)), op=FixMul, results=List(x7460))
      Stage(operands=List(x7460, x7458), op=FixSub, results=List(CU.scalarOut(x7456_x7463_s)))
    }
    val x7471_0 = Pipeline(name="x7471_0",parent=x7588) { implicit CU => 
      val x7467 = CU.temp(None)
      val x7465 = CU.temp(None)
      val x6378 = ScalarBuffer(name="x6378").wtPort(x6378_x6394_s)
      val x6370 = CounterChain.copy("x7588", "x6370")
      val x7471_unit = CounterChain(name = "x7471_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6370(0)), Const(494)), op=FixMul, results=List(x7465, CU.scalarOut(x7455_x7469_s)))
      Stage(operands=List(CU.load(x6378), Const(494)), op=FixMul, results=List(x7467))
      Stage(operands=List(x7467, x7465), op=FixSub, results=List(CU.scalarOut(x7457_x7470_s)))
    }
    val x7529 = StreamController(name="x7529",parent=x7588) { implicit CU => 
      val x7529_unit = CounterChain(name = "x7529_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7525 = Sequential(name="x7525",parent=x7529) { implicit CU => 
      val x7525_unit = CounterChain(name = "x7525_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7507 = StreamController(name="x7507",parent=x7525) { implicit CU => 
      val x7507_unit = CounterChain(name = "x7507_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7507_0 = Pipeline(name="x7507_0",parent=x7507) { implicit CU => 
      val x7486 = CU.temp(None)
      val x7487 = CU.temp(None)
      val x7484 = CU.temp(None)
      val x7481 = CU.temp(None)
      val x7454 = ScalarBuffer(name="x7454").wtPort(x7454_x7462_s)
      val x7456 = ScalarBuffer(name="x7456").wtPort(x7456_x7463_s)
      Stage(operands=List(CU.load(x7454), Const(2)), op=FixSla, results=List(x7481, CU.scalarOut(bus_1832_s)))
      Stage(operands=List(CU.load(x7456), Const(2)), op=FixSla, results=List(x7484, CU.scalarOut(bus_1833_s)))
      Stage(operands=List(x7481, x7484), op=FixAdd, results=List(x7486))
      Stage(operands=List(x7486, Const(64)), op=FixMod, results=List(x7487))
      Stage(operands=List(x7487, Const(0)), op=FixEql, results=List(CU.scalarOut(bus_1838_s)))
      Stage(operands=List(Const(64), x7487), op=FixSub, results=List(CU.scalarOut(bus_1839_s)))
    }
    val x7507_1 = Pipeline(name="x7507_1",parent=x7507) { implicit CU => 
      val x7482 = CU.temp(None)
      val x7491 = CU.temp(None)
      val x7490 = CU.temp(None)
      val x7494 = CU.temp(None)
      val x7492 = CU.temp(None)
      val x7489 = ScalarFIFO(size=1,name="x7489").wtPort(bus_1839_s)
      val x7456 = ScalarBuffer(name="x7456").wtPort(x7456_x7463_s)
      val x7481 = ScalarFIFO(size=1,name="x7481").wtPort(bus_1832_s)
      val x7488 = ScalarFIFO(size=1,name="x7488").wtPort(bus_1838_s)
      Stage(operands=List(CU.load(x7488), Const(0), CU.load(x7489)), op=Mux, results=List(x7490, CU.scalarOut(bus_1840_s)))
      Stage(operands=List(x7490, Const(2)), op=FixSra, results=List(x7492))
      Stage(operands=List(CU.load(x7481), Const(64)), op=FixMod, results=List(x7482, CU.scalarOut(bus_1842_s)))
      Stage(operands=List(x7482, Const(2)), op=FixSra, results=List(x7491, CU.scalarOut(bus_1843_s), CU.scalarOut(x7476_x7504_s)))
      Stage(operands=List(CU.load(x7456), x7491), op=FixAdd, results=List(x7494))
      Stage(operands=List(x7494, x7492), op=FixAdd, results=List(CU.scalarOut(x7478_x7506_s)))
    }
    val x7507_2 = Pipeline(name="x7507_2",parent=x7507) { implicit CU => 
      val x7485 = CU.temp(None)
      val x7496 = CU.temp(None)
      val x7499 = ScalarBuffer(name="x7499").wtPort(result_dram_da)
      val x7491 = ScalarFIFO(size=1,name="x7491").wtPort(bus_1843_s)
      val x7481 = ScalarFIFO(size=1,name="x7481").wtPort(bus_1832_s)
      val x7456 = ScalarBuffer(name="x7456").wtPort(x7456_x7463_s)
      val x7482 = ScalarFIFO(size=1,name="x7482").wtPort(bus_1842_s)
      val x7484 = ScalarFIFO(size=1,name="x7484").wtPort(bus_1833_s)
      val x7490 = ScalarFIFO(size=1,name="x7490").wtPort(bus_1840_s)
      Stage(operands=List(CU.load(x7491), CU.load(x7456)), op=FixAdd, results=List(CU.scalarOut(x7477_x7505_s)))
      Stage(operands=List(CU.load(x7484), CU.load(x7482)), op=FixAdd, results=List(x7496))
      Stage(operands=List(x7496, CU.load(x7490)), op=FixAdd, results=List(CU.scalarOut(x7473_b7757_x7503_b7759_s)))
      Stage(operands=List(CU.load(x7481), CU.load(x7482)), op=FixSub, results=List(x7485))
      Stage(operands=List(x7485, CU.load(x7499)), op=FixAdd, results=List(CU.scalarOut(x7473_b7756_x7503_b7758_s)))
    }
    val x7524_0 = Pipeline(name="x7524_0",parent=x7525) { implicit CU => 
      val x7514 = CU.temp(None)
      val x7512 = CU.temp(None)
      val x7522_x7515 = CU.temp(None)
      val x7476 = ScalarBuffer(name="x7476").wtPort(x7476_x7504_s)
      val x7478 = ScalarBuffer(name="x7478").wtPort(x7478_x7506_s)
      val x7519 = ScalarFIFO(size=1,name="x7519").wtPort(x6373_x6373_dsp0_bank0_s)
      val x7477 = ScalarBuffer(name="x7477").wtPort(x7477_x7505_s)
      val ctr26 = Counter(min=Const(0), max=x7478.readPort, step=Const(1), par=16) // Counter
      val x7510 = CounterChain(name = "x7510", ctr26).iter(1)
      Stage(operands=List(CU.load(x7476), CU.ctr(x7510(0))), op=FixLeq, results=List(x7512))
      Stage(operands=List(CU.ctr(x7510(0)), CU.load(x7477)), op=FixLt, results=List(x7514))
      Stage(operands=List(x7512, x7514), op=BitAnd, results=List(x7522_x7515))
      Stage(operands=List(x7522_x7515, CU.load(x7519), Const(0)), op=Mux, results=List(CU.scalarOut(x7474_x7523_s)))
    }
    val x7526 = MemoryController(name="x7526",parent=x7529,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x7473_b7757 = ScalarFIFO(size=1,name="size").wtPort(x7473_b7757_x7503_b7759_s)
      val x7473_b7756 = ScalarFIFO(size=1,name="offset").wtPort(x7473_b7756_x7503_b7758_s)
      val x7474 = ScalarFIFO(size=1,name="data").wtPort(x7474_x7523_s)
    }
    val x7586 = StreamController(name="x7586",parent=x7588) { implicit CU => 
      val x7586_unit = CounterChain(name = "x7586_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7582 = Sequential(name="x7582",parent=x7586) { implicit CU => 
      val x7582_unit = CounterChain(name = "x7582_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7564 = StreamController(name="x7564",parent=x7582) { implicit CU => 
      val x7564_unit = CounterChain(name = "x7564_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7564_0 = Pipeline(name="x7564_0",parent=x7564) { implicit CU => 
      val x7541 = CU.temp(None)
      val x7544 = CU.temp(None)
      val x7543 = CU.temp(None)
      val x7538 = CU.temp(None)
      val x7455 = ScalarBuffer(name="x7455").wtPort(x7455_x7469_s)
      val x7457 = ScalarBuffer(name="x7457").wtPort(x7457_x7470_s)
      Stage(operands=List(CU.load(x7455), Const(2)), op=FixSla, results=List(x7538, CU.scalarOut(bus_1860_s)))
      Stage(operands=List(CU.load(x7457), Const(2)), op=FixSla, results=List(x7541, CU.scalarOut(bus_1861_s)))
      Stage(operands=List(x7538, x7541), op=FixAdd, results=List(x7543))
      Stage(operands=List(x7543, Const(64)), op=FixMod, results=List(x7544))
      Stage(operands=List(x7544, Const(0)), op=FixEql, results=List(CU.scalarOut(bus_1866_s)))
      Stage(operands=List(Const(64), x7544), op=FixSub, results=List(CU.scalarOut(bus_1867_s)))
    }
    val x7564_1 = Pipeline(name="x7564_1",parent=x7564) { implicit CU => 
      val x7549 = CU.temp(None)
      val x7548 = CU.temp(None)
      val x7551 = CU.temp(None)
      val x7539 = CU.temp(None)
      val x7547 = CU.temp(None)
      val x7457 = ScalarBuffer(name="x7457").wtPort(x7457_x7470_s)
      val x7546 = ScalarFIFO(size=1,name="x7546").wtPort(bus_1867_s)
      val x7538 = ScalarFIFO(size=1,name="x7538").wtPort(bus_1860_s)
      val x7545 = ScalarFIFO(size=1,name="x7545").wtPort(bus_1866_s)
      Stage(operands=List(CU.load(x7545), Const(0), CU.load(x7546)), op=Mux, results=List(x7547, CU.scalarOut(bus_1868_s)))
      Stage(operands=List(x7547, Const(2)), op=FixSra, results=List(x7549))
      Stage(operands=List(CU.load(x7538), Const(64)), op=FixMod, results=List(x7539, CU.scalarOut(bus_1870_s)))
      Stage(operands=List(x7539, Const(2)), op=FixSra, results=List(x7548, CU.scalarOut(bus_1871_s), CU.scalarOut(x7533_x7561_s)))
      Stage(operands=List(CU.load(x7457), x7548), op=FixAdd, results=List(x7551))
      Stage(operands=List(x7551, x7549), op=FixAdd, results=List(CU.scalarOut(x7535_x7563_s)))
    }
    val x7564_2 = Pipeline(name="x7564_2",parent=x7564) { implicit CU => 
      val x7542 = CU.temp(None)
      val x7553 = CU.temp(None)
      val x7538 = ScalarFIFO(size=1,name="x7538").wtPort(bus_1860_s)
      val x7556 = ScalarBuffer(name="x7556").wtPort(result_dram_da)
      val x7539 = ScalarFIFO(size=1,name="x7539").wtPort(bus_1870_s)
      val x7457 = ScalarBuffer(name="x7457").wtPort(x7457_x7470_s)
      val x7547 = ScalarFIFO(size=1,name="x7547").wtPort(bus_1868_s)
      val x7541 = ScalarFIFO(size=1,name="x7541").wtPort(bus_1861_s)
      val x7548 = ScalarFIFO(size=1,name="x7548").wtPort(bus_1871_s)
      Stage(operands=List(CU.load(x7548), CU.load(x7457)), op=FixAdd, results=List(CU.scalarOut(x7534_x7562_s)))
      Stage(operands=List(CU.load(x7541), CU.load(x7539)), op=FixAdd, results=List(x7553))
      Stage(operands=List(x7553, CU.load(x7547)), op=FixAdd, results=List(CU.scalarOut(x7530_b7761_x7560_b7763_s)))
      Stage(operands=List(CU.load(x7538), CU.load(x7539)), op=FixSub, results=List(x7542))
      Stage(operands=List(x7542, CU.load(x7556)), op=FixAdd, results=List(CU.scalarOut(x7530_b7760_x7560_b7762_s)))
    }
    val x7581_0 = Pipeline(name="x7581_0",parent=x7582) { implicit CU => 
      val x7571 = CU.temp(None)
      val x7569 = CU.temp(None)
      val x7579_x7572 = CU.temp(None)
      val x7535 = ScalarBuffer(name="x7535").wtPort(x7535_x7563_s)
      val x7576 = ScalarFIFO(size=1,name="x7576").wtPort(x6374_x6374_dsp0_bank0_s)
      val x7534 = ScalarBuffer(name="x7534").wtPort(x7534_x7562_s)
      val x7533 = ScalarBuffer(name="x7533").wtPort(x7533_x7561_s)
      val ctr27 = Counter(min=Const(0), max=x7535.readPort, step=Const(1), par=16) // Counter
      val x7567 = CounterChain(name = "x7567", ctr27).iter(1)
      Stage(operands=List(CU.load(x7533), CU.ctr(x7567(0))), op=FixLeq, results=List(x7569))
      Stage(operands=List(CU.ctr(x7567(0)), CU.load(x7534)), op=FixLt, results=List(x7571))
      Stage(operands=List(x7569, x7571), op=BitAnd, results=List(x7579_x7572))
      Stage(operands=List(x7579_x7572, CU.load(x7576), Const(0)), op=Mux, results=List(CU.scalarOut(x7531_x7580_s)))
    }
    val x7583 = MemoryController(name="x7583",parent=x7586,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x7530_b7760 = ScalarFIFO(size=1,name="offset").wtPort(x7530_b7760_x7560_b7762_s)
      val x7531 = ScalarFIFO(size=1,name="data").wtPort(x7531_x7580_s)
      val x7530_b7761 = ScalarFIFO(size=1,name="size").wtPort(x7530_b7761_x7560_b7763_s)
    }
    
  }
}
