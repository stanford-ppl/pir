import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SPMV_CRS extends PIRApp {
  def main(top:Top) = {
    val x6063_x7076_s = Scalar("x6063_x7076")
    val bus_1522_s = Scalar("bus_1522")
    val x6330_x6336_s = Scalar("x6330_x6336")
    val bus_1648_s = Scalar("bus_1648")
    val x6838_x6845_s = Scalar("x6838_x6845")
    val bus_1396_s = Scalar("bus_1396")
    val x6600_x6629_s = Scalar("x6600_x6629")
    val x6211_0_s = Scalar("x6211_0")
    val x6807_b7355_x6834_b7363_s = Scalar("x6807_b7355_x6834_b7363")
    val x6239_b7279_x6266_b7287_s = Scalar("x6239_b7279_x6266_b7287")
    val x6946_x6967_s = Scalar("x6946_x6967")
    val x6088_b7253_x6114_b7261_s = Scalar("x6088_b7253_x6114_b7261")
    val bus_1586_s = Scalar("bus_1586")
    val x6460_x6466_s = Scalar("x6460_x6466")
    val x7083_x7090_s = Scalar("x7083_x7090")
    val bus_1326_s = Scalar("bus_1326")
    val x6087_b7250_x6112_b7258_s = Scalar("x6087_b7250_x6112_b7258")
    val x6239_b7278_x6266_b7286_s = Scalar("x6239_b7278_x6266_b7286")
    val bus_1452_s = Scalar("bus_1452")
    val x6429_b7314_x6455_b7322_s = Scalar("x6429_b7314_x6455_b7322")
    val bus_1771_s = Scalar("bus_1771")
    val x6240_x6268_data_v = Vector("x6240_x6268_data")
    val x6769_x6776_s = Scalar("x6769_x6776")
    val x7156_x7203_v = Vector("x7156_x7203")
    val x6238_b7275_x6264_b7283_s = Scalar("x6238_b7275_x6264_b7283")
    val x6430_b7316_x6457_b7324_s = Scalar("x6430_b7316_x6457_b7324")
    val x6649_x7033_s = Scalar("x6649_x7033")
    val bus_1346_s = Scalar("bus_1346")
    val bus_1454_s = Scalar("bus_1454")
    val bus_1613_s = Scalar("bus_1613")
    val x6868_b7368_x6895_b7376_s = Scalar("x6868_b7368_x6895_b7376")
    val bus_1675_s = Scalar("bus_1675")
    val bus_1475_s = Scalar("bus_1475")
    val x6806_b7354_x6832_b7362_s = Scalar("x6806_b7354_x6832_b7362")
    val x6492_x6498_s = Scalar("x6492_x6498")
    val bus_1651_s = Scalar("bus_1651")
    val bus_1516_s = Scalar("bus_1516")
    val x6119_x6126_s = Scalar("x6119_x6126")
    val x6807_b7356_x6834_b7364_s = Scalar("x6807_b7356_x6834_b7364")
    val bus_1320_s = Scalar("bus_1320")
    val x6430_b7318_x6457_b7326_s = Scalar("x6430_b7318_x6457_b7326")
    val bus_1765_s = Scalar("bus_1765")
    val x6206_0_s = Scalar("x6206_0")
    val x6177_x6184_s = Scalar("x6177_x6184")
    val x7155_b7384_x7183_b7386_s = Scalar("x7155_b7384_x7183_b7386")
    val bus_1477_s = Scalar("bus_1477")
    val bus_1764_s = Scalar("bus_1764")
    val x6599_x6614_s = Scalar("x6599_x6614")
    val x6117_x6122_s = Scalar("x6117_x6122")
    val x6677_b7329_x6704_b7337_s = Scalar("x6677_b7329_x6704_b7337")
    val x6646_0_s = Scalar("x6646_0")
    val x6900_x6908_s = Scalar("x6900_x6908")
    val x6206_x6295_s = Scalar("x6206_x6295")
    val bus_1716_s = Scalar("bus_1716")
    val bus_1678_s = Scalar("bus_1678")
    val bus_1590_s = Scalar("bus_1590")
    val x6145_b7262_x6170_b7270_s = Scalar("x6145_b7262_x6170_b7270")
    val x6145_b7263_x6170_b7271_s = Scalar("x6145_b7263_x6170_b7271")
    val x6709_x6717_s = Scalar("x6709_x6717")
    val x6146_b7265_x6172_b7273_s = Scalar("x6146_b7265_x6172_b7273")
    val x6300_b7292_x6327_b7300_s = Scalar("x6300_b7292_x6327_b7300")
    val x7037_x7052_s = Scalar("x7037_x7052")
    val x6209_x6486_s = Scalar("x6209_x6486")
    val bus_1588_s = Scalar("bus_1588")
    val x7100_b7379_x7128_b7381_s = Scalar("x7100_b7379_x7128_b7381")
    val bus_1619_s = Scalar("bus_1619")
    val x6645_x6863_s = Scalar("x6645_x6863")
    val x6369_b7304_x6396_b7312_s = Scalar("x6369_b7304_x6396_b7312")
    val x6238_b7276_x6264_b7284_s = Scalar("x6238_b7276_x6264_b7284")
    val x6147_x6174_data_v = Vector("x6147_x6174_data")
    val x6738_b7344_x6765_b7352_s = Scalar("x6738_b7344_x6765_b7352")
    val x6868_b7370_x6895_b7378_s = Scalar("x6868_b7370_x6895_b7378")
    val x6566_x6584_data_s = Scalar("x6566_x6584_data")
    val x6369_b7303_x6396_b7311_s = Scalar("x6369_b7303_x6396_b7311")
    val cols_dram_da = DRAMAddress("cols_dram", "cols_dram")
    val x6061_0_s = Scalar("x6061_0")
    val x7038_x7067_s = Scalar("x7038_x7067")
    val x6738_b7343_x6765_b7351_s = Scalar("x6738_b7343_x6765_b7351")
    val bus_1772_s = Scalar("bus_1772")
    val x6648_x7000_s = Scalar("x6648_x7000")
    val bus_1423_s = Scalar("bus_1423")
    val bus_1592_s = Scalar("bus_1592")
    val x6737_b7340_x6763_b7348_s = Scalar("x6737_b7340_x6763_b7348")
    val x6431_x6459_data_v = Vector("x6431_x6459_data")
    val bus_1350_s = Scalar("bus_1350")
    val x6269_x6275_s = Scalar("x6269_x6275")
    val x6970_x6987_s = Scalar("x6970_x6987")
    val x6089_x6116_data_v = Vector("x6089_x6116_data")
    val bus_1790_s = Scalar("bus_1790")
    val x6676_b7328_x6702_b7336_s = Scalar("x6676_b7328_x6702_b7336")
    val x6088_b7252_x6114_b7260_s = Scalar("x6088_b7252_x6114_b7260")
    val x6739_x6767_data_v = Vector("x6739_x6767_data")
    val x6300_b7290_x6327_b7298_s = Scalar("x6300_b7290_x6327_b7298")
    val x6231_x6236_s = Scalar("x6231_x6236")
    val x7160_x7186_s = Scalar("x7160_x7186")
    val rowid_dram_oc = OffChip("rowid_dram")
    val x6646_x6794_s = Scalar("x6646_x6794")
    val x7105_x7131_s = Scalar("x7105_x7131")
    val x6945_x6956_s = Scalar("x6945_x6956")
    val x6868_b7369_x6895_b7377_s = Scalar("x6868_b7369_x6895_b7377")
    val bus_1791_s = Scalar("bus_1791")
    val bus_1615_s = Scalar("bus_1615")
    val x6062_x6638_s = Scalar("x6062_x6638")
    val x6066_x6075_s = Scalar("x6066_x6075")
    val x6300_b7291_x6327_b7299_s = Scalar("x6300_b7291_x6327_b7299")
    val bus_1322_s = Scalar("bus_1322")
    val x6837_x6843_s = Scalar("x6837_x6843")
    val x6270_x6277_s = Scalar("x6270_x6277")
    val x7158_x7184_s = Scalar("x7158_x7184")
    val bus_1644_s = Scalar("bus_1644")
    val x6669_x6674_s = Scalar("x6669_x6674")
    val cols_dram_oc = OffChip("cols_dram")
    val x7104_x7130_s = Scalar("x7104_x7130")
    val x6370_x6398_data_v = Vector("x6370_x6398_data")
    val x7081_x7089_s = Scalar("x7081_x7089")
    val x6461_x6468_s = Scalar("x6461_x6468")
    val x6209_0_s = Scalar("x6209_0")
    val x6807_b7357_x6834_b7365_s = Scalar("x6807_b7357_x6834_b7365")
    val bus_1700_s = Scalar("bus_1700")
    val x6301_x6329_data_v = Vector("x6301_x6329_data")
    val x6063_x7072_s = Scalar("x6063_x7072")
    val x6210_0_s = Scalar("x6210_0")
    val bus_1392_s = Scalar("bus_1392")
    val x7159_x7185_s = Scalar("x7159_x7185")
    val bus_1669_s = Scalar("bus_1669")
    val x6839_x6847_s = Scalar("x6839_x6847")
    val x6146_b7264_x6172_b7272_s = Scalar("x6146_b7264_x6172_b7272")
    val x6799_x6804_s = Scalar("x6799_x6804")
    val x6649_0_s = Scalar("x6649_0")
    val bus_1344_s = Scalar("bus_1344")
    val bus_1512_s = Scalar("bus_1512")
    val x6508_x6529_s = Scalar("x6508_x6529")
    val x6400_x6407_s = Scalar("x6400_x6407")
    val x6647_x6924_s = Scalar("x6647_x6924")
    val x6647_0_s = Scalar("x6647_0")
    val x6507_x6518_s = Scalar("x6507_x6518")
    val x6175_x6180_s = Scalar("x6175_x6180")
    val x6062_x6634_s = Scalar("x6062_x6634")
    val x6060_0_s = Scalar("x6060_0")
    val x6207_0_s = Scalar("x6207_0")
    val x6207_x6425_s = Scalar("x6207_x6425")
    val x6368_b7301_x6394_b7309_s = Scalar("x6368_b7301_x6394_b7309")
    val x6215_x6228_s = Scalar("x6215_x6228")
    val vec_dram_oc = OffChip("vec_dram")
    val x6677_b7330_x6704_b7338_s = Scalar("x6677_b7330_x6704_b7338")
    val x6212_x6220_s = Scalar("x6212_x6220")
    val x7003_x7020_s = Scalar("x7003_x7020")
    val x6971_x6989_data_s = Scalar("x6971_x6989_data")
    val bus_1479_s = Scalar("bus_1479")
    val bus_1419_s = Scalar("bus_1419")
    val x6650_x6658_s = Scalar("x6650_x6658")
    val bus_1769_s = Scalar("bus_1769")
    val x6931_x6942_s = Scalar("x6931_x6942")
    val x6088_b7251_x6114_b7259_s = Scalar("x6088_b7251_x6114_b7259")
    val bus_1506_s = Scalar("bus_1506")
    val bus_1797_s = Scalar("bus_1797")
    val x6067_x6083_s = Scalar("x6067_x6083")
    val bus_1481_s = Scalar("bus_1481")
    val x6399_x6405_s = Scalar("x6399_x6405")
    val x6898_x6904_s = Scalar("x6898_x6904")
    val bus_1421_s = Scalar("bus_1421")
    val result_dram_da = DRAMAddress("result_dram", "result_dram")
    val x6299_b7289_x6325_b7297_s = Scalar("x6299_b7289_x6325_b7297")
    val x6899_x6906_s = Scalar("x6899_x6906")
    val x7101_x7148_v = Vector("x7101_x7148")
    val x6737_b7341_x6763_b7349_s = Scalar("x6737_b7341_x6763_b7349")
    val bus_1798_s = Scalar("bus_1798")
    val bus_1457_s = Scalar("bus_1457")
    val values_dram_da = DRAMAddress("values_dram", "values_dram")
    val x6677_b7331_x6704_b7339_s = Scalar("x6677_b7331_x6704_b7339")
    val x6214_x6221_s = Scalar("x6214_x6221")
    val x6176_x6182_s = Scalar("x6176_x6182")
    val x6708_x6715_s = Scalar("x6708_x6715")
    val bus_1398_s = Scalar("bus_1398")
    val x6644_0_s = Scalar("x6644_0")
    val bus_1484_s = Scalar("bus_1484")
    val x6869_x6897_data_v = Vector("x6869_x6897_data")
    val x6087_b7249_x6112_b7257_s = Scalar("x6087_b7249_x6112_b7257")
    val x6060_1_s = Scalar("x6060_1")
    val bus_1401_s = Scalar("bus_1401")
    val bus_1394_s = Scalar("bus_1394")
    val x6678_x6706_data_v = Vector("x6678_x6706_data")
    val x6806_b7353_x6832_b7361_s = Scalar("x6806_b7353_x6832_b7361")
    val x6738_b7342_x6765_b7350_s = Scalar("x6738_b7342_x6765_b7350")
    val x6401_x6409_s = Scalar("x6401_x6409")
    val x6808_x6836_data_v = Vector("x6808_x6836_data")
    val bus_1617_s = Scalar("bus_1617")
    val x6768_x6774_s = Scalar("x6768_x6774")
    val x6118_x6124_s = Scalar("x6118_x6124")
    val x6430_b7317_x6457_b7325_s = Scalar("x6430_b7317_x6457_b7325")
    val bus_1642_s = Scalar("bus_1642")
    val bus_1671_s = Scalar("bus_1671")
    val bus_1706_s = Scalar("bus_1706")
    val values_dram_oc = OffChip("values_dram")
    val bus_1622_s = Scalar("bus_1622")
    val x6648_0_s = Scalar("x6648_0")
    val result_dram_oc = OffChip("result_dram")
    val x6361_x6366_s = Scalar("x6361_x6366")
    val x6331_x6338_s = Scalar("x6331_x6338")
    val x7155_b7383_x7183_b7385_s = Scalar("x7155_b7383_x7183_b7385")
    val x6565_x6582_s = Scalar("x6565_x6582")
    val x6770_x6778_s = Scalar("x6770_x6778")
    val x6064_x6074_s = Scalar("x6064_x6074")
    val x7084_x7097_s = Scalar("x7084_x7097")
    val x6532_x6549_s = Scalar("x6532_x6549")
    val x7103_x7129_s = Scalar("x7103_x7129")
    val x6061_1_s = Scalar("x6061_1")
    val x6867_b7366_x6893_b7374_s = Scalar("x6867_b7366_x6893_b7374")
    val bus_1710_s = Scalar("bus_1710")
    val x6211_x6595_s = Scalar("x6211_x6595")
    val x6213_x6227_s = Scalar("x6213_x6227")
    val x6332_x6340_s = Scalar("x6332_x6340")
    val x6069_x6084_s = Scalar("x6069_x6084")
    val x6208_0_s = Scalar("x6208_0")
    val x6239_b7277_x6266_b7285_s = Scalar("x6239_b7277_x6266_b7285")
    val x6068_x6076_s = Scalar("x6068_x6076")
    val x6368_b7302_x6394_b7310_s = Scalar("x6368_b7302_x6394_b7310")
    val x6210_x6562_s = Scalar("x6210_x6562")
    val x6493_x6504_s = Scalar("x6493_x6504")
    val x6271_x6279_s = Scalar("x6271_x6279")
    val bus_1353_s = Scalar("bus_1353")
    val x6061_x6199_s = Scalar("x6061_x6199")
    val bus_1595_s = Scalar("bus_1595")
    val rowid_dram_da = DRAMAddress("rowid_dram", "rowid_dram")
    val x6676_b7327_x6702_b7335_s = Scalar("x6676_b7327_x6702_b7335")
    val x6065_x6082_s = Scalar("x6065_x6082")
    val x7004_x7022_data_s = Scalar("x7004_x7022_data")
    val x6652_x6659_s = Scalar("x6652_x6659")
    val x6063_0_s = Scalar("x6063_0")
    val x6299_b7288_x6325_b7296_s = Scalar("x6299_b7288_x6325_b7296")
    val x6062_0_s = Scalar("x6062_0")
    val x6462_x6470_s = Scalar("x6462_x6470")
    val x6146_b7266_x6172_b7274_s = Scalar("x6146_b7266_x6172_b7274")
    val x6644_x6733_s = Scalar("x6644_x6733")
    val x7100_b7380_x7128_b7382_s = Scalar("x7100_b7380_x7128_b7382")
    val x7082_x7096_s = Scalar("x7082_x7096")
    val x6707_x6713_s = Scalar("x6707_x6713")
    val x6429_b7315_x6455_b7323_s = Scalar("x6429_b7315_x6455_b7323")
    val bus_1450_s = Scalar("bus_1450")
    val bus_1428_s = Scalar("bus_1428")
    val bus_1425_s = Scalar("bus_1425")
    val bus_1324_s = Scalar("bus_1324")
    val x6867_b7367_x6893_b7375_s = Scalar("x6867_b7367_x6893_b7375")
    val x6930_x6936_s = Scalar("x6930_x6936")
    val x6653_x6666_s = Scalar("x6653_x6666")
    val bus_1348_s = Scalar("bus_1348")
    val bus_1448_s = Scalar("bus_1448")
    val x6651_x6665_s = Scalar("x6651_x6665")
    val bus_1329_s = Scalar("bus_1329")
    val x6645_0_s = Scalar("x6645_0")
    val bus_1673_s = Scalar("bus_1673")
    val bus_1646_s = Scalar("bus_1646")
    val x6533_x6551_data_s = Scalar("x6533_x6551_data")
    val x6208_x6356_s = Scalar("x6208_x6356")
    val x6060_x6141_s = Scalar("x6060_x6141")
    val x6369_b7305_x6396_b7313_s = Scalar("x6369_b7305_x6396_b7313")
    val bus_1795_s = Scalar("bus_1795")
    val vec_dram_da = DRAMAddress("vec_dram", "vec_dram")
    val x7212 = Sequential(name="x7212",parent=top) { implicit CU => 
      val x7212_unit = CounterChain(name = "x7212_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7211 = MetaPipeline(name="x7211",parent=x7212) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=2) // Counter
      val x6059 = CounterChain(name = "x6059", ctr1).iter(1)
    }
    val x6060_dsp0 = MemoryPipeline(name="x6060_dsp0",parent="x7211") { implicit CU => 
      val x6141 = ScalarFIFO(size=1,name="x6141").wtPort(x6060_x6141_s)
      val x6117 = ScalarBuffer(name="x6117").wtPort(x6117_x6122_s)
      val x6130 = CounterChain.copy("x6142_0", "x6130")
      val x6205 = CounterChain.copy("x6641", "x6205")
      val x6060 = SRAM(size=495,name="x6060",banking = Strided(1)).wtPort(x6141.readPort).rdPort(x6060_0_s)
      WAStage(operands=List(CU.ctr(x6130(0)), CU.load(x6117)), op=FixSub, results=List(x6060.writeAddr))
      RAStage(operands=List(CU.ctr(x6205(0)), Const(1)), op=FixAdd, results=List(x6060.readAddr))
    }
    val x6060_dsp1 = MemoryPipeline(name="x6060_dsp1",parent="x7211") { implicit CU => 
      val x6141 = ScalarFIFO(size=1,name="x6141").wtPort(x6060_x6141_s)
      val x6117 = ScalarBuffer(name="x6117").wtPort(x6117_x6122_s)
      val x6130 = CounterChain.copy("x6142_0", "x6130")
      val x6205 = CounterChain.copy("x6641", "x6205")
      val x6060 = SRAM(size=495,name="x6060",banking = Strided(1)).wtPort(x6141.readPort).rdPort(x6060_1_s).rdAddr(x6205(0))
      WAStage(operands=List(CU.ctr(x6130(0)), CU.load(x6117)), op=FixSub, results=List(x6060.writeAddr))
    }
    val x6061_dsp0 = MemoryPipeline(name="x6061_dsp0",parent="x7211") { implicit CU => 
      val x6175 = ScalarBuffer(name="x6175").wtPort(x6175_x6180_s)
      val x6199 = ScalarFIFO(size=1,name="x6199").wtPort(x6061_x6199_s)
      val x6188 = CounterChain.copy("x6200_0", "x6188")
      val x6643 = CounterChain.copy("x7079", "x6643")
      val x6061 = SRAM(size=495,name="x6061",banking = Strided(1)).wtPort(x6199.readPort).rdPort(x6061_0_s)
      WAStage(operands=List(CU.ctr(x6188(0)), CU.load(x6175)), op=FixSub, results=List(x6061.writeAddr))
      RAStage(operands=List(CU.ctr(x6643(0)), Const(1)), op=FixAdd, results=List(x6061.readAddr))
    }
    val x6061_dsp1 = MemoryPipeline(name="x6061_dsp1",parent="x7211") { implicit CU => 
      val x6175 = ScalarBuffer(name="x6175").wtPort(x6175_x6180_s)
      val x6199 = ScalarFIFO(size=1,name="x6199").wtPort(x6061_x6199_s)
      val x6188 = CounterChain.copy("x6200_0", "x6188")
      val x6643 = CounterChain.copy("x7079", "x6643")
      val x6061 = SRAM(size=495,name="x6061",banking = Strided(1)).wtPort(x6199.readPort).rdPort(x6061_1_s).rdAddr(x6643(0))
      WAStage(operands=List(CU.ctr(x6188(0)), CU.load(x6175)), op=FixSub, results=List(x6061.writeAddr))
    }
    val x6062_dsp0 = MemoryPipeline(name="x6062_dsp0",parent="x7211") { implicit CU => 
      val x6638 = ScalarFIFO(size=1,name="x6638").wtPort(x6062_x6638_s)
      val x6634 = ScalarFIFO(size=1,name="x6634").wtPort(x6062_x6634_s)
      val x7103 = ScalarBuffer(name="x7103").wtPort(x7103_x7129_s)
      val x6205 = CounterChain.copy("x6641", "x6205")
      val x7135 = CounterChain.copy("x7149_0", "x7135")
      val x6062 = SRAM(size=494,name="x6062",banking = Strided(1)).wtPort(x6634.readPort).wtPort(x6638.readPort).rdPort(x6062_0_s).wtAddr(x6205(0))
      RAStage(operands=List(CU.ctr(x7135(0)), CU.load(x7103)), op=FixSub, results=List(x6062.readAddr))
    }
    val x6063_dsp0 = MemoryPipeline(name="x6063_dsp0",parent="x7211") { implicit CU => 
      val x7072 = ScalarFIFO(size=1,name="x7072").wtPort(x6063_x7072_s)
      val x7158 = ScalarBuffer(name="x7158").wtPort(x7158_x7184_s)
      val x7076 = ScalarFIFO(size=1,name="x7076").wtPort(x6063_x7076_s)
      val x6643 = CounterChain.copy("x7079", "x6643")
      val x7190 = CounterChain.copy("x7204_0", "x7190")
      val x6063 = SRAM(size=494,name="x6063",banking = Strided(1)).wtPort(x7072.readPort).wtPort(x7076.readPort).rdPort(x6063_0_s).wtAddr(x6643(0))
      RAStage(operands=List(CU.ctr(x7190(0)), CU.load(x7158)), op=FixSub, results=List(x6063.readAddr))
    }
    val x6077_0 = Pipeline(name="x6077_0",parent=x7211) { implicit CU => 
      val x6072 = CU.temp(None)
      val x6071 = CU.temp(None)
      val x6070 = CU.temp(None)
      val x6059 = CounterChain.copy("x7211", "x6059")
      val x6077_unit = CounterChain(name = "x6077_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6059(0)), Const(495)), op=FixMul, results=List(x6070, CU.scalarOut(x6064_x6074_s)))
      Stage(operands=List(CU.ctr(x6059(0)), Const(1)), op=FixAdd, results=List(x6071, CU.scalarOut(x6066_x6075_s)))
      Stage(operands=List(x6071, Const(495)), op=FixMul, results=List(x6072))
      Stage(operands=List(x6072, x6070), op=FixSub, results=List(CU.scalarOut(x6068_x6076_s)))
    }
    val x6085_0 = Pipeline(name="x6085_0",parent=x7211) { implicit CU => 
      val x6080 = CU.temp(None)
      val x6078 = CU.temp(None)
      val x6079 = CU.temp(None)
      val x6059 = CounterChain.copy("x7211", "x6059")
      val x6085_unit = CounterChain(name = "x6085_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6059(0)), Const(495)), op=FixMul, results=List(x6078, CU.scalarOut(x6065_x6082_s)))
      Stage(operands=List(CU.ctr(x6059(0)), Const(1)), op=FixAdd, results=List(x6079, CU.scalarOut(x6067_x6083_s)))
      Stage(operands=List(x6079, Const(495)), op=FixMul, results=List(x6080))
      Stage(operands=List(x6080, x6078), op=FixSub, results=List(CU.scalarOut(x6069_x6084_s)))
    }
    val x6144 = StreamController(name="x6144",parent=x7211) { implicit CU => 
      val x6144_unit = CounterChain(name = "x6144_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6115 = StreamController(name="x6115",parent=x6144) { implicit CU => 
      val x6115_unit = CounterChain(name = "x6115_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6115_0 = Pipeline(name="x6115_0",parent=x6115) { implicit CU => 
      val x6092 = CU.temp(None)
      val x6093 = CU.temp(None)
      val x6100 = CU.temp(None)
      val x6064 = ScalarBuffer(name="x6064").wtPort(x6064_x6074_s)
      val x6068 = ScalarBuffer(name="x6068").wtPort(x6068_x6076_s)
      Stage(operands=List(CU.load(x6064), Const(2)), op=FixSla, results=List(x6092, CU.scalarOut(bus_1320_s)))
      Stage(operands=List(x6092, Const(64)), op=FixMod, results=List(x6093, CU.scalarOut(bus_1322_s)))
      Stage(operands=List(x6093, Const(4)), op=FixDiv, results=List(x6100, CU.scalarOut(bus_1324_s), CU.scalarOut(x6088_b7252_x6114_b7260_s)))
      Stage(operands=List(x6100, CU.load(x6068)), op=FixAdd, results=List(CU.scalarOut(x6088_b7253_x6114_b7261_s)))
      Stage(operands=List(CU.load(x6068), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1326_s)))
    }
    val x6115_1 = Pipeline(name="x6115_1",parent=x6115) { implicit CU => 
      val x6103 = CU.temp(None)
      val x6098 = CU.temp(None)
      val x6097 = CU.temp(None)
      val x6101 = CU.temp(None)
      val x6099 = CU.temp(None)
      val x6092 = ScalarFIFO(size=1,name="x6092").wtPort(bus_1320_s)
      val x6100 = ScalarFIFO(size=1,name="x6100").wtPort(bus_1324_s)
      val x6095 = ScalarFIFO(size=1,name="x6095").wtPort(bus_1326_s)
      val x6068 = ScalarBuffer(name="x6068").wtPort(x6068_x6076_s)
      Stage(operands=List(CU.load(x6092), CU.load(x6095)), op=FixAdd, results=List(x6097))
      Stage(operands=List(x6097, Const(64)), op=FixMod, results=List(x6098))
      Stage(operands=List(Const(64), x6098), op=FixSub, results=List(x6099, CU.scalarOut(bus_1329_s)))
      Stage(operands=List(x6099, Const(4)), op=FixDiv, results=List(x6101))
      Stage(operands=List(CU.load(x6068), CU.load(x6100)), op=FixAdd, results=List(x6103))
      Stage(operands=List(x6103, x6101), op=FixAdd, results=List(CU.scalarOut(x6088_b7251_x6114_b7259_s)))
    }
    val x6115_2 = Pipeline(name="x6115_2",parent=x6115) { implicit CU => 
      val x6105 = CU.temp(None)
      val x6096 = CU.temp(None)
      val x6092 = ScalarFIFO(size=1,name="x6092").wtPort(bus_1320_s)
      val x6095 = ScalarFIFO(size=1,name="x6095").wtPort(bus_1326_s)
      val x6099 = ScalarFIFO(size=1,name="x6099").wtPort(bus_1329_s)
      val x6093 = ScalarFIFO(size=1,name="x6093").wtPort(bus_1322_s)
      val x6108 = ScalarBuffer(name="x6108").wtPort(rowid_dram_da)
      Stage(operands=List(CU.load(x6095), CU.load(x6093)), op=FixAdd, results=List(x6105))
      Stage(operands=List(x6105, CU.load(x6099)), op=FixAdd, results=List(CU.scalarOut(x6087_b7250_x6112_b7258_s)))
      Stage(operands=List(CU.load(x6092), CU.load(x6093)), op=FixSub, results=List(x6096))
      Stage(operands=List(x6096, CU.load(x6108)), op=FixAdd, results=List(CU.scalarOut(x6087_b7249_x6112_b7257_s)))
    }
    val x6116 = MemoryController(name="x6116",parent=x6144,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6087_b7250 = ScalarFIFO(size=1,name="size").wtPort(x6087_b7250_x6112_b7258_s)
      val x6087_b7249 = ScalarFIFO(size=1,name="offset").wtPort(x6087_b7249_x6112_b7257_s)
      CU.newVout("data", x6089_x6116_data_v)
    }
    val x6143 = Sequential(name="x6143",parent=x6144) { implicit CU => 
      val x6143_unit = CounterChain(name = "x6143_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6127_0 = Pipeline(name="x6127_0",parent=x6143) { implicit CU => 
      val x6088_b7252 = ScalarFIFO(size=16,name="x6088_b7252").wtPort(x6088_b7252_x6114_b7260_s)
      val x6088_b7251 = ScalarFIFO(size=16,name="x6088_b7251").wtPort(x6088_b7251_x6114_b7259_s)
      val x6088_b7253 = ScalarFIFO(size=16,name="x6088_b7253").wtPort(x6088_b7253_x6114_b7261_s)
      val x6127_unit = CounterChain(name = "x6127_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6088_b7252)), op=Bypass, results=List(CU.scalarOut(x6117_x6122_s)))
      Stage(operands=List(CU.load(x6088_b7253)), op=Bypass, results=List(CU.scalarOut(x6118_x6124_s)))
      Stage(operands=List(CU.load(x6088_b7251)), op=Bypass, results=List(CU.scalarOut(x6119_x6126_s)))
    }
    val x6142_0 = Pipeline(name="x6142_0",parent=x6143) { implicit CU => 
      val x6118 = ScalarBuffer(name="x6118").wtPort(x6118_x6124_s)
      val x6117 = ScalarBuffer(name="x6117").wtPort(x6117_x6122_s)
      val x6119 = ScalarBuffer(name="x6119").wtPort(x6119_x6126_s)
      val x6089 = VectorFIFO(size=1,name="x6089").wtPort(x6089_x6116_data_v)
      val ctr2 = Counter(min=Const(0), max=x6119.readPort, step=Const(1), par=16) // Counter
      val x6130 = CounterChain(name = "x6130", ctr2).iter(1)
      Stage(operands=List(CU.load(x6117), CU.ctr(x6130(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6130(0)), CU.load(x6118)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6089)), op=Bypass, results=List(CU.scalarOut(x6060_x6141_s)))
    }
    val x6202 = StreamController(name="x6202",parent=x7211) { implicit CU => 
      val x6202_unit = CounterChain(name = "x6202_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6173 = StreamController(name="x6173",parent=x6202) { implicit CU => 
      val x6173_unit = CounterChain(name = "x6173_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6173_0 = Pipeline(name="x6173_0",parent=x6173) { implicit CU => 
      val x6150 = CU.temp(None)
      val x6151 = CU.temp(None)
      val x6158 = CU.temp(None)
      val x6069 = ScalarBuffer(name="x6069").wtPort(x6069_x6084_s)
      val x6065 = ScalarBuffer(name="x6065").wtPort(x6065_x6082_s)
      Stage(operands=List(CU.load(x6065), Const(2)), op=FixSla, results=List(x6150, CU.scalarOut(bus_1344_s)))
      Stage(operands=List(x6150, Const(64)), op=FixMod, results=List(x6151, CU.scalarOut(bus_1346_s)))
      Stage(operands=List(x6151, Const(4)), op=FixDiv, results=List(x6158, CU.scalarOut(bus_1348_s), CU.scalarOut(x6146_b7265_x6172_b7273_s)))
      Stage(operands=List(x6158, CU.load(x6069)), op=FixAdd, results=List(CU.scalarOut(x6146_b7266_x6172_b7274_s)))
      Stage(operands=List(CU.load(x6069), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1350_s)))
    }
    val x6173_1 = Pipeline(name="x6173_1",parent=x6173) { implicit CU => 
      val x6155 = CU.temp(None)
      val x6159 = CU.temp(None)
      val x6157 = CU.temp(None)
      val x6156 = CU.temp(None)
      val x6161 = CU.temp(None)
      val x6069 = ScalarBuffer(name="x6069").wtPort(x6069_x6084_s)
      val x6158 = ScalarFIFO(size=1,name="x6158").wtPort(bus_1348_s)
      val x6153 = ScalarFIFO(size=1,name="x6153").wtPort(bus_1350_s)
      val x6150 = ScalarFIFO(size=1,name="x6150").wtPort(bus_1344_s)
      Stage(operands=List(CU.load(x6150), CU.load(x6153)), op=FixAdd, results=List(x6155))
      Stage(operands=List(x6155, Const(64)), op=FixMod, results=List(x6156))
      Stage(operands=List(Const(64), x6156), op=FixSub, results=List(x6157, CU.scalarOut(bus_1353_s)))
      Stage(operands=List(x6157, Const(4)), op=FixDiv, results=List(x6159))
      Stage(operands=List(CU.load(x6069), CU.load(x6158)), op=FixAdd, results=List(x6161))
      Stage(operands=List(x6161, x6159), op=FixAdd, results=List(CU.scalarOut(x6146_b7264_x6172_b7272_s)))
    }
    val x6173_2 = Pipeline(name="x6173_2",parent=x6173) { implicit CU => 
      val x6163 = CU.temp(None)
      val x6154 = CU.temp(None)
      val x6157 = ScalarFIFO(size=1,name="x6157").wtPort(bus_1353_s)
      val x6150 = ScalarFIFO(size=1,name="x6150").wtPort(bus_1344_s)
      val x6151 = ScalarFIFO(size=1,name="x6151").wtPort(bus_1346_s)
      val x6166 = ScalarBuffer(name="x6166").wtPort(rowid_dram_da)
      val x6153 = ScalarFIFO(size=1,name="x6153").wtPort(bus_1350_s)
      Stage(operands=List(CU.load(x6153), CU.load(x6151)), op=FixAdd, results=List(x6163))
      Stage(operands=List(x6163, CU.load(x6157)), op=FixAdd, results=List(CU.scalarOut(x6145_b7263_x6170_b7271_s)))
      Stage(operands=List(CU.load(x6150), CU.load(x6151)), op=FixSub, results=List(x6154))
      Stage(operands=List(x6154, CU.load(x6166)), op=FixAdd, results=List(CU.scalarOut(x6145_b7262_x6170_b7270_s)))
    }
    val x6174 = MemoryController(name="x6174",parent=x6202,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6145_b7263 = ScalarFIFO(size=1,name="size").wtPort(x6145_b7263_x6170_b7271_s)
      val x6145_b7262 = ScalarFIFO(size=1,name="offset").wtPort(x6145_b7262_x6170_b7270_s)
      CU.newVout("data", x6147_x6174_data_v)
    }
    val x6201 = Sequential(name="x6201",parent=x6202) { implicit CU => 
      val x6201_unit = CounterChain(name = "x6201_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6185_0 = Pipeline(name="x6185_0",parent=x6201) { implicit CU => 
      val x6146_b7266 = ScalarFIFO(size=16,name="x6146_b7266").wtPort(x6146_b7266_x6172_b7274_s)
      val x6146_b7265 = ScalarFIFO(size=16,name="x6146_b7265").wtPort(x6146_b7265_x6172_b7273_s)
      val x6146_b7264 = ScalarFIFO(size=16,name="x6146_b7264").wtPort(x6146_b7264_x6172_b7272_s)
      val x6185_unit = CounterChain(name = "x6185_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6146_b7265)), op=Bypass, results=List(CU.scalarOut(x6175_x6180_s)))
      Stage(operands=List(CU.load(x6146_b7266)), op=Bypass, results=List(CU.scalarOut(x6176_x6182_s)))
      Stage(operands=List(CU.load(x6146_b7264)), op=Bypass, results=List(CU.scalarOut(x6177_x6184_s)))
    }
    val x6200_0 = Pipeline(name="x6200_0",parent=x6201) { implicit CU => 
      val x6175 = ScalarBuffer(name="x6175").wtPort(x6175_x6180_s)
      val x6147 = VectorFIFO(size=1,name="x6147").wtPort(x6147_x6174_data_v)
      val x6177 = ScalarBuffer(name="x6177").wtPort(x6177_x6184_s)
      val x6176 = ScalarBuffer(name="x6176").wtPort(x6176_x6182_s)
      val ctr3 = Counter(min=Const(0), max=x6177.readPort, step=Const(1), par=16) // Counter
      val x6188 = CounterChain(name = "x6188", ctr3).iter(1)
      Stage(operands=List(CU.load(x6175), CU.ctr(x6188(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6188(0)), CU.load(x6176)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6147)), op=Bypass, results=List(CU.scalarOut(x6061_x6199_s)))
    }
    val x6641 = MetaPipeline(name="x6641",parent=x7211) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(494), step=Const(1), par=2) // Counter
      val x6205 = CounterChain(name = "x6205", ctr4).iter(247)
    }
    val x6206_dsp0 = MemoryPipeline(name="x6206_dsp0",parent="x6641") { implicit CU => 
      val x6269 = ScalarBuffer(name="x6269").wtPort(x6269_x6275_s)
      val x6295 = ScalarFIFO(size=1,name="x6295").wtPort(x6206_x6295_s)
      val x6283 = CounterChain.copy("x6296_0", "x6283")
      val x6536 = CounterChain.copy("x6550_0", "x6536")
      val x6206 = SRAM(size=494,name="x6206",banking = Strided(1)).wtPort(x6295.readPort).rdPort(x6206_0_s).rdAddr(x6536(0))
      WAStage(operands=List(CU.ctr(x6283(0)), CU.load(x6269)), op=FixSub, results=List(x6206.writeAddr))
    }
    val x6207_dsp0 = MemoryPipeline(name="x6207_dsp0",parent="x6641") { implicit CU => 
      val x6399 = ScalarBuffer(name="x6399").wtPort(x6399_x6405_s)
      val x6425 = ScalarFIFO(size=1,name="x6425").wtPort(x6207_x6425_s)
      val x6413 = CounterChain.copy("x6426_0", "x6413")
      val x6569 = CounterChain.copy("x6583_0", "x6569")
      val x6207 = SRAM(size=494,name="x6207",banking = Strided(1)).wtPort(x6425.readPort).rdPort(x6207_0_s).rdAddr(x6569(0))
      WAStage(operands=List(CU.ctr(x6413(0)), CU.load(x6399)), op=FixSub, results=List(x6207.writeAddr))
    }
    val x6208_dsp0 = MemoryPipeline(name="x6208_dsp0",parent="x6641") { implicit CU => 
      val x6330 = ScalarBuffer(name="x6330").wtPort(x6330_x6336_s)
      val x6356 = ScalarFIFO(size=1,name="x6356").wtPort(x6208_x6356_s)
      val x6344 = CounterChain.copy("x6357_0", "x6344")
      val x6603 = CounterChain.copy("x6615_0", "x6603")
      val x6208 = SRAM(size=494,name="x6208",banking = Strided(1)).wtPort(x6356.readPort).rdPort(x6208_0_s).rdAddr(x6603(0))
      WAStage(operands=List(CU.ctr(x6344(0)), CU.load(x6330)), op=FixSub, results=List(x6208.writeAddr))
    }
    val x6209_dsp0 = MemoryPipeline(name="x6209_dsp0",parent="x6641") { implicit CU => 
      val x6486 = ScalarFIFO(size=1,name="x6486").wtPort(x6209_x6486_s)
      val x6460 = ScalarBuffer(name="x6460").wtPort(x6460_x6466_s)
      val x6474 = CounterChain.copy("x6487_0", "x6474")
      val x6618 = CounterChain.copy("x6630_0", "x6618")
      val x6209 = SRAM(size=494,name="x6209",banking = Strided(1)).wtPort(x6486.readPort).rdPort(x6209_0_s).rdAddr(x6618(0))
      WAStage(operands=List(CU.ctr(x6474(0)), CU.load(x6460)), op=FixSub, results=List(x6209.writeAddr))
    }
    val x6210_dsp0 = MemoryPipeline(name="x6210_dsp0",parent="x6641") { implicit CU => 
      val x6562 = ScalarFIFO(size=1,name="x6562").wtPort(x6210_x6562_s)
      val x6554 = CounterChain.copy("x6563_0", "x6554")
      val x6603 = CounterChain.copy("x6615_0", "x6603")
      val x6210 = SRAM(size=494,name="x6210",banking = Strided(1)).wtPort(x6562.readPort).rdPort(x6210_0_s).rdAddr(x6603(0)).wtAddr(x6554(0))
    }
    val x6211_dsp0 = MemoryPipeline(name="x6211_dsp0",parent="x6641") { implicit CU => 
      val x6595 = ScalarFIFO(size=1,name="x6595").wtPort(x6211_x6595_s)
      val x6587 = CounterChain.copy("x6596_0", "x6587")
      val x6618 = CounterChain.copy("x6630_0", "x6618")
      val x6211 = SRAM(size=494,name="x6211",banking = Strided(1)).wtPort(x6595.readPort).rdPort(x6211_0_s).rdAddr(x6618(0)).wtAddr(x6587(0))
    }
    val x6222_0 = Pipeline(name="x6222_0",parent=x6641) { implicit CU => 
      val x6219 = ScalarFIFO(size=1,name="x6219").wtPort(x6060_0_s)
      val x6217 = ScalarFIFO(size=1,name="x6217").wtPort(x6060_1_s)
      val x6222_unit = CounterChain(name = "x6222_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6217)), op=Bypass, results=List(CU.scalarOut(x6212_x6220_s)))
      Stage(operands=List(CU.load(x6219)), op=Bypass, results=List(CU.scalarOut(x6214_x6221_s)))
    }
    val x6229_0 = Pipeline(name="x6229_0",parent=x6641) { implicit CU => 
      val x6224 = ScalarFIFO(size=1,name="x6224").wtPort(x6060_1_s)
      val x6226 = ScalarFIFO(size=1,name="x6226").wtPort(x6060_0_s)
      val x6229_unit = CounterChain(name = "x6229_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6224)), op=Bypass, results=List(CU.scalarOut(x6213_x6227_s)))
      Stage(operands=List(CU.load(x6226)), op=Bypass, results=List(CU.scalarOut(x6215_x6228_s)))
    }
    val x6237_0 = Pipeline(name="x6237_0",parent=x6641) { implicit CU => 
      val x6214 = ScalarBuffer(name="x6214").wtPort(x6214_x6221_s)
      val x6212 = ScalarBuffer(name="x6212").wtPort(x6212_x6220_s)
      val x6237_unit = CounterChain(name = "x6237_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6214), CU.load(x6212)), op=FixSub, results=List(CU.scalarOut(x6231_x6236_s)))
    }
    val x6298 = StreamController(name="x6298",parent=x6641) { implicit CU => 
      val x6298_unit = CounterChain(name = "x6298_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6267 = StreamController(name="x6267",parent=x6298) { implicit CU => 
      val x6267_unit = CounterChain(name = "x6267_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6267_0 = Pipeline(name="x6267_0",parent=x6267) { implicit CU => 
      val x6244 = CU.temp(None)
      val x6243 = CU.temp(None)
      val x6251 = CU.temp(None)
      val x6231 = ScalarBuffer(name="x6231").wtPort(x6231_x6236_s)
      val x6212 = ScalarBuffer(name="x6212").wtPort(x6212_x6220_s)
      Stage(operands=List(CU.load(x6212), Const(2)), op=FixSla, results=List(x6243, CU.scalarOut(bus_1392_s)))
      Stage(operands=List(x6243, Const(64)), op=FixMod, results=List(x6244, CU.scalarOut(bus_1394_s)))
      Stage(operands=List(x6244, Const(4)), op=FixDiv, results=List(x6251, CU.scalarOut(bus_1396_s), CU.scalarOut(x6239_b7278_x6266_b7286_s)))
      Stage(operands=List(x6251, CU.load(x6231)), op=FixAdd, results=List(CU.scalarOut(x6239_b7279_x6266_b7287_s)))
      Stage(operands=List(CU.load(x6231), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1398_s)))
    }
    val x6267_1 = Pipeline(name="x6267_1",parent=x6267) { implicit CU => 
      val x6249 = CU.temp(None)
      val x6252 = CU.temp(None)
      val x6250 = CU.temp(None)
      val x6254 = CU.temp(None)
      val x6248 = CU.temp(None)
      val x6243 = ScalarFIFO(size=1,name="x6243").wtPort(bus_1392_s)
      val x6231 = ScalarBuffer(name="x6231").wtPort(x6231_x6236_s)
      val x6246 = ScalarFIFO(size=1,name="x6246").wtPort(bus_1398_s)
      val x6251 = ScalarFIFO(size=1,name="x6251").wtPort(bus_1396_s)
      Stage(operands=List(CU.load(x6243), CU.load(x6246)), op=FixAdd, results=List(x6248))
      Stage(operands=List(x6248, Const(64)), op=FixMod, results=List(x6249))
      Stage(operands=List(Const(64), x6249), op=FixSub, results=List(x6250, CU.scalarOut(bus_1401_s)))
      Stage(operands=List(x6250, Const(4)), op=FixDiv, results=List(x6252))
      Stage(operands=List(CU.load(x6231), CU.load(x6251)), op=FixAdd, results=List(x6254))
      Stage(operands=List(x6254, x6252), op=FixAdd, results=List(CU.scalarOut(x6239_b7277_x6266_b7285_s)))
    }
    val x6267_2 = Pipeline(name="x6267_2",parent=x6267) { implicit CU => 
      val x6256 = CU.temp(None)
      val x6247 = CU.temp(None)
      val x6259 = ScalarBuffer(name="x6259").wtPort(cols_dram_da)
      val x6246 = ScalarFIFO(size=1,name="x6246").wtPort(bus_1398_s)
      val x6244 = ScalarFIFO(size=1,name="x6244").wtPort(bus_1394_s)
      val x6243 = ScalarFIFO(size=1,name="x6243").wtPort(bus_1392_s)
      val x6250 = ScalarFIFO(size=1,name="x6250").wtPort(bus_1401_s)
      Stage(operands=List(CU.load(x6246), CU.load(x6244)), op=FixAdd, results=List(x6256))
      Stage(operands=List(x6256, CU.load(x6250)), op=FixAdd, results=List(CU.scalarOut(x6238_b7276_x6264_b7284_s)))
      Stage(operands=List(CU.load(x6243), CU.load(x6244)), op=FixSub, results=List(x6247))
      Stage(operands=List(x6247, CU.load(x6259)), op=FixAdd, results=List(CU.scalarOut(x6238_b7275_x6264_b7283_s)))
    }
    val x6268 = MemoryController(name="x6268",parent=x6298,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6238_b7275 = ScalarFIFO(size=1,name="offset").wtPort(x6238_b7275_x6264_b7283_s)
      val x6238_b7276 = ScalarFIFO(size=1,name="size").wtPort(x6238_b7276_x6264_b7284_s)
      CU.newVout("data", x6240_x6268_data_v)
    }
    val x6297 = Sequential(name="x6297",parent=x6298) { implicit CU => 
      val x6297_unit = CounterChain(name = "x6297_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6280_0 = Pipeline(name="x6280_0",parent=x6297) { implicit CU => 
      val x6239_b7278 = ScalarFIFO(size=16,name="x6239_b7278").wtPort(x6239_b7278_x6266_b7286_s)
      val x6239_b7277 = ScalarFIFO(size=16,name="x6239_b7277").wtPort(x6239_b7277_x6266_b7285_s)
      val x6239_b7279 = ScalarFIFO(size=16,name="x6239_b7279").wtPort(x6239_b7279_x6266_b7287_s)
      val x6280_unit = CounterChain(name = "x6280_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6239_b7278)), op=Bypass, results=List(CU.scalarOut(x6269_x6275_s)))
      Stage(operands=List(CU.load(x6239_b7279)), op=Bypass, results=List(CU.scalarOut(x6270_x6277_s)))
      Stage(operands=List(CU.load(x6239_b7277)), op=Bypass, results=List(CU.scalarOut(x6271_x6279_s)))
    }
    val x6296_0 = Pipeline(name="x6296_0",parent=x6297) { implicit CU => 
      val x6240 = VectorFIFO(size=1,name="x6240").wtPort(x6240_x6268_data_v)
      val x6270 = ScalarBuffer(name="x6270").wtPort(x6270_x6277_s)
      val x6269 = ScalarBuffer(name="x6269").wtPort(x6269_x6275_s)
      val x6271 = ScalarBuffer(name="x6271").wtPort(x6271_x6279_s)
      val ctr5 = Counter(min=Const(0), max=x6271.readPort, step=Const(1), par=16) // Counter
      val x6283 = CounterChain(name = "x6283", ctr5).iter(1)
      Stage(operands=List(CU.load(x6269), CU.ctr(x6283(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6283(0)), CU.load(x6270)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6240)), op=Bypass, results=List(CU.scalarOut(x6206_x6295_s)))
    }
    val x6359 = StreamController(name="x6359",parent=x6641) { implicit CU => 
      val x6359_unit = CounterChain(name = "x6359_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6328 = StreamController(name="x6328",parent=x6359) { implicit CU => 
      val x6328_unit = CounterChain(name = "x6328_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6328_0 = Pipeline(name="x6328_0",parent=x6328) { implicit CU => 
      val x6304 = CU.temp(None)
      val x6312 = CU.temp(None)
      val x6305 = CU.temp(None)
      val x6231 = ScalarBuffer(name="x6231").wtPort(x6231_x6236_s)
      val x6212 = ScalarBuffer(name="x6212").wtPort(x6212_x6220_s)
      Stage(operands=List(CU.load(x6212), Const(2)), op=FixSla, results=List(x6304, CU.scalarOut(bus_1419_s)))
      Stage(operands=List(x6304, Const(64)), op=FixMod, results=List(x6305, CU.scalarOut(bus_1421_s)))
      Stage(operands=List(x6305, Const(4)), op=FixDiv, results=List(x6312, CU.scalarOut(bus_1423_s), CU.scalarOut(x6300_b7291_x6327_b7299_s)))
      Stage(operands=List(x6312, CU.load(x6231)), op=FixAdd, results=List(CU.scalarOut(x6300_b7292_x6327_b7300_s)))
      Stage(operands=List(CU.load(x6231), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1425_s)))
    }
    val x6328_1 = Pipeline(name="x6328_1",parent=x6328) { implicit CU => 
      val x6313 = CU.temp(None)
      val x6311 = CU.temp(None)
      val x6310 = CU.temp(None)
      val x6315 = CU.temp(None)
      val x6309 = CU.temp(None)
      val x6307 = ScalarFIFO(size=1,name="x6307").wtPort(bus_1425_s)
      val x6231 = ScalarBuffer(name="x6231").wtPort(x6231_x6236_s)
      val x6312 = ScalarFIFO(size=1,name="x6312").wtPort(bus_1423_s)
      val x6304 = ScalarFIFO(size=1,name="x6304").wtPort(bus_1419_s)
      Stage(operands=List(CU.load(x6304), CU.load(x6307)), op=FixAdd, results=List(x6309))
      Stage(operands=List(x6309, Const(64)), op=FixMod, results=List(x6310))
      Stage(operands=List(Const(64), x6310), op=FixSub, results=List(x6311, CU.scalarOut(bus_1428_s)))
      Stage(operands=List(x6311, Const(4)), op=FixDiv, results=List(x6313))
      Stage(operands=List(CU.load(x6231), CU.load(x6312)), op=FixAdd, results=List(x6315))
      Stage(operands=List(x6315, x6313), op=FixAdd, results=List(CU.scalarOut(x6300_b7290_x6327_b7298_s)))
    }
    val x6328_2 = Pipeline(name="x6328_2",parent=x6328) { implicit CU => 
      val x6317 = CU.temp(None)
      val x6308 = CU.temp(None)
      val x6307 = ScalarFIFO(size=1,name="x6307").wtPort(bus_1425_s)
      val x6320 = ScalarBuffer(name="x6320").wtPort(values_dram_da)
      val x6304 = ScalarFIFO(size=1,name="x6304").wtPort(bus_1419_s)
      val x6305 = ScalarFIFO(size=1,name="x6305").wtPort(bus_1421_s)
      val x6311 = ScalarFIFO(size=1,name="x6311").wtPort(bus_1428_s)
      Stage(operands=List(CU.load(x6307), CU.load(x6305)), op=FixAdd, results=List(x6317))
      Stage(operands=List(x6317, CU.load(x6311)), op=FixAdd, results=List(CU.scalarOut(x6299_b7289_x6325_b7297_s)))
      Stage(operands=List(CU.load(x6304), CU.load(x6305)), op=FixSub, results=List(x6308))
      Stage(operands=List(x6308, CU.load(x6320)), op=FixAdd, results=List(CU.scalarOut(x6299_b7288_x6325_b7296_s)))
    }
    val x6329 = MemoryController(name="x6329",parent=x6359,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6299_b7289 = ScalarFIFO(size=1,name="size").wtPort(x6299_b7289_x6325_b7297_s)
      val x6299_b7288 = ScalarFIFO(size=1,name="offset").wtPort(x6299_b7288_x6325_b7296_s)
      CU.newVout("data", x6301_x6329_data_v)
    }
    val x6358 = Sequential(name="x6358",parent=x6359) { implicit CU => 
      val x6358_unit = CounterChain(name = "x6358_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6341_0 = Pipeline(name="x6341_0",parent=x6358) { implicit CU => 
      val x6300_b7290 = ScalarFIFO(size=16,name="x6300_b7290").wtPort(x6300_b7290_x6327_b7298_s)
      val x6300_b7292 = ScalarFIFO(size=16,name="x6300_b7292").wtPort(x6300_b7292_x6327_b7300_s)
      val x6300_b7291 = ScalarFIFO(size=16,name="x6300_b7291").wtPort(x6300_b7291_x6327_b7299_s)
      val x6341_unit = CounterChain(name = "x6341_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6300_b7291)), op=Bypass, results=List(CU.scalarOut(x6330_x6336_s)))
      Stage(operands=List(CU.load(x6300_b7292)), op=Bypass, results=List(CU.scalarOut(x6331_x6338_s)))
      Stage(operands=List(CU.load(x6300_b7290)), op=Bypass, results=List(CU.scalarOut(x6332_x6340_s)))
    }
    val x6357_0 = Pipeline(name="x6357_0",parent=x6358) { implicit CU => 
      val x6301 = VectorFIFO(size=1,name="x6301").wtPort(x6301_x6329_data_v)
      val x6330 = ScalarBuffer(name="x6330").wtPort(x6330_x6336_s)
      val x6332 = ScalarBuffer(name="x6332").wtPort(x6332_x6340_s)
      val x6331 = ScalarBuffer(name="x6331").wtPort(x6331_x6338_s)
      val ctr6 = Counter(min=Const(0), max=x6332.readPort, step=Const(1), par=16) // Counter
      val x6344 = CounterChain(name = "x6344", ctr6).iter(1)
      Stage(operands=List(CU.load(x6330), CU.ctr(x6344(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6344(0)), CU.load(x6331)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6301)), op=Bypass, results=List(CU.scalarOut(x6208_x6356_s)))
    }
    val x6367_0 = Pipeline(name="x6367_0",parent=x6641) { implicit CU => 
      val x6213 = ScalarBuffer(name="x6213").wtPort(x6213_x6227_s)
      val x6215 = ScalarBuffer(name="x6215").wtPort(x6215_x6228_s)
      val x6367_unit = CounterChain(name = "x6367_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6215), CU.load(x6213)), op=FixSub, results=List(CU.scalarOut(x6361_x6366_s)))
    }
    val x6428 = StreamController(name="x6428",parent=x6641) { implicit CU => 
      val x6428_unit = CounterChain(name = "x6428_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6397 = StreamController(name="x6397",parent=x6428) { implicit CU => 
      val x6397_unit = CounterChain(name = "x6397_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6397_0 = Pipeline(name="x6397_0",parent=x6397) { implicit CU => 
      val x6373 = CU.temp(None)
      val x6381 = CU.temp(None)
      val x6374 = CU.temp(None)
      val x6213 = ScalarBuffer(name="x6213").wtPort(x6213_x6227_s)
      val x6361 = ScalarBuffer(name="x6361").wtPort(x6361_x6366_s)
      Stage(operands=List(CU.load(x6213), Const(2)), op=FixSla, results=List(x6373, CU.scalarOut(bus_1448_s)))
      Stage(operands=List(x6373, Const(64)), op=FixMod, results=List(x6374, CU.scalarOut(bus_1450_s)))
      Stage(operands=List(x6374, Const(4)), op=FixDiv, results=List(x6381, CU.scalarOut(bus_1452_s), CU.scalarOut(x6369_b7304_x6396_b7312_s)))
      Stage(operands=List(x6381, CU.load(x6361)), op=FixAdd, results=List(CU.scalarOut(x6369_b7305_x6396_b7313_s)))
      Stage(operands=List(CU.load(x6361), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1454_s)))
    }
    val x6397_1 = Pipeline(name="x6397_1",parent=x6397) { implicit CU => 
      val x6379 = CU.temp(None)
      val x6378 = CU.temp(None)
      val x6384 = CU.temp(None)
      val x6380 = CU.temp(None)
      val x6382 = CU.temp(None)
      val x6376 = ScalarFIFO(size=1,name="x6376").wtPort(bus_1454_s)
      val x6373 = ScalarFIFO(size=1,name="x6373").wtPort(bus_1448_s)
      val x6381 = ScalarFIFO(size=1,name="x6381").wtPort(bus_1452_s)
      val x6361 = ScalarBuffer(name="x6361").wtPort(x6361_x6366_s)
      Stage(operands=List(CU.load(x6373), CU.load(x6376)), op=FixAdd, results=List(x6378))
      Stage(operands=List(x6378, Const(64)), op=FixMod, results=List(x6379))
      Stage(operands=List(Const(64), x6379), op=FixSub, results=List(x6380, CU.scalarOut(bus_1457_s)))
      Stage(operands=List(x6380, Const(4)), op=FixDiv, results=List(x6382))
      Stage(operands=List(CU.load(x6361), CU.load(x6381)), op=FixAdd, results=List(x6384))
      Stage(operands=List(x6384, x6382), op=FixAdd, results=List(CU.scalarOut(x6369_b7303_x6396_b7311_s)))
    }
    val x6397_2 = Pipeline(name="x6397_2",parent=x6397) { implicit CU => 
      val x6386 = CU.temp(None)
      val x6377 = CU.temp(None)
      val x6380 = ScalarFIFO(size=1,name="x6380").wtPort(bus_1457_s)
      val x6389 = ScalarBuffer(name="x6389").wtPort(cols_dram_da)
      val x6374 = ScalarFIFO(size=1,name="x6374").wtPort(bus_1450_s)
      val x6373 = ScalarFIFO(size=1,name="x6373").wtPort(bus_1448_s)
      val x6376 = ScalarFIFO(size=1,name="x6376").wtPort(bus_1454_s)
      Stage(operands=List(CU.load(x6376), CU.load(x6374)), op=FixAdd, results=List(x6386))
      Stage(operands=List(x6386, CU.load(x6380)), op=FixAdd, results=List(CU.scalarOut(x6368_b7302_x6394_b7310_s)))
      Stage(operands=List(CU.load(x6373), CU.load(x6374)), op=FixSub, results=List(x6377))
      Stage(operands=List(x6377, CU.load(x6389)), op=FixAdd, results=List(CU.scalarOut(x6368_b7301_x6394_b7309_s)))
    }
    val x6398 = MemoryController(name="x6398",parent=x6428,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6368_b7302 = ScalarFIFO(size=1,name="size").wtPort(x6368_b7302_x6394_b7310_s)
      val x6368_b7301 = ScalarFIFO(size=1,name="offset").wtPort(x6368_b7301_x6394_b7309_s)
      CU.newVout("data", x6370_x6398_data_v)
    }
    val x6427 = Sequential(name="x6427",parent=x6428) { implicit CU => 
      val x6427_unit = CounterChain(name = "x6427_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6410_0 = Pipeline(name="x6410_0",parent=x6427) { implicit CU => 
      val x6369_b7305 = ScalarFIFO(size=16,name="x6369_b7305").wtPort(x6369_b7305_x6396_b7313_s)
      val x6369_b7304 = ScalarFIFO(size=16,name="x6369_b7304").wtPort(x6369_b7304_x6396_b7312_s)
      val x6369_b7303 = ScalarFIFO(size=16,name="x6369_b7303").wtPort(x6369_b7303_x6396_b7311_s)
      val x6410_unit = CounterChain(name = "x6410_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6369_b7304)), op=Bypass, results=List(CU.scalarOut(x6399_x6405_s)))
      Stage(operands=List(CU.load(x6369_b7305)), op=Bypass, results=List(CU.scalarOut(x6400_x6407_s)))
      Stage(operands=List(CU.load(x6369_b7303)), op=Bypass, results=List(CU.scalarOut(x6401_x6409_s)))
    }
    val x6426_0 = Pipeline(name="x6426_0",parent=x6427) { implicit CU => 
      val x6399 = ScalarBuffer(name="x6399").wtPort(x6399_x6405_s)
      val x6401 = ScalarBuffer(name="x6401").wtPort(x6401_x6409_s)
      val x6400 = ScalarBuffer(name="x6400").wtPort(x6400_x6407_s)
      val x6370 = VectorFIFO(size=1,name="x6370").wtPort(x6370_x6398_data_v)
      val ctr7 = Counter(min=Const(0), max=x6401.readPort, step=Const(1), par=16) // Counter
      val x6413 = CounterChain(name = "x6413", ctr7).iter(1)
      Stage(operands=List(CU.load(x6399), CU.ctr(x6413(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6413(0)), CU.load(x6400)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6370)), op=Bypass, results=List(CU.scalarOut(x6207_x6425_s)))
    }
    val x6489 = StreamController(name="x6489",parent=x6641) { implicit CU => 
      val x6489_unit = CounterChain(name = "x6489_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6458 = StreamController(name="x6458",parent=x6489) { implicit CU => 
      val x6458_unit = CounterChain(name = "x6458_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6458_0 = Pipeline(name="x6458_0",parent=x6458) { implicit CU => 
      val x6434 = CU.temp(None)
      val x6442 = CU.temp(None)
      val x6435 = CU.temp(None)
      val x6213 = ScalarBuffer(name="x6213").wtPort(x6213_x6227_s)
      val x6361 = ScalarBuffer(name="x6361").wtPort(x6361_x6366_s)
      Stage(operands=List(CU.load(x6213), Const(2)), op=FixSla, results=List(x6434, CU.scalarOut(bus_1475_s)))
      Stage(operands=List(x6434, Const(64)), op=FixMod, results=List(x6435, CU.scalarOut(bus_1477_s)))
      Stage(operands=List(x6435, Const(4)), op=FixDiv, results=List(x6442, CU.scalarOut(bus_1479_s), CU.scalarOut(x6430_b7317_x6457_b7325_s)))
      Stage(operands=List(x6442, CU.load(x6361)), op=FixAdd, results=List(CU.scalarOut(x6430_b7318_x6457_b7326_s)))
      Stage(operands=List(CU.load(x6361), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1481_s)))
    }
    val x6458_1 = Pipeline(name="x6458_1",parent=x6458) { implicit CU => 
      val x6440 = CU.temp(None)
      val x6441 = CU.temp(None)
      val x6443 = CU.temp(None)
      val x6439 = CU.temp(None)
      val x6445 = CU.temp(None)
      val x6442 = ScalarFIFO(size=1,name="x6442").wtPort(bus_1479_s)
      val x6434 = ScalarFIFO(size=1,name="x6434").wtPort(bus_1475_s)
      val x6437 = ScalarFIFO(size=1,name="x6437").wtPort(bus_1481_s)
      val x6361 = ScalarBuffer(name="x6361").wtPort(x6361_x6366_s)
      Stage(operands=List(CU.load(x6434), CU.load(x6437)), op=FixAdd, results=List(x6439))
      Stage(operands=List(x6439, Const(64)), op=FixMod, results=List(x6440))
      Stage(operands=List(Const(64), x6440), op=FixSub, results=List(x6441, CU.scalarOut(bus_1484_s)))
      Stage(operands=List(x6441, Const(4)), op=FixDiv, results=List(x6443))
      Stage(operands=List(CU.load(x6361), CU.load(x6442)), op=FixAdd, results=List(x6445))
      Stage(operands=List(x6445, x6443), op=FixAdd, results=List(CU.scalarOut(x6430_b7316_x6457_b7324_s)))
    }
    val x6458_2 = Pipeline(name="x6458_2",parent=x6458) { implicit CU => 
      val x6447 = CU.temp(None)
      val x6438 = CU.temp(None)
      val x6435 = ScalarFIFO(size=1,name="x6435").wtPort(bus_1477_s)
      val x6437 = ScalarFIFO(size=1,name="x6437").wtPort(bus_1481_s)
      val x6441 = ScalarFIFO(size=1,name="x6441").wtPort(bus_1484_s)
      val x6450 = ScalarBuffer(name="x6450").wtPort(values_dram_da)
      val x6434 = ScalarFIFO(size=1,name="x6434").wtPort(bus_1475_s)
      Stage(operands=List(CU.load(x6437), CU.load(x6435)), op=FixAdd, results=List(x6447))
      Stage(operands=List(x6447, CU.load(x6441)), op=FixAdd, results=List(CU.scalarOut(x6429_b7315_x6455_b7323_s)))
      Stage(operands=List(CU.load(x6434), CU.load(x6435)), op=FixSub, results=List(x6438))
      Stage(operands=List(x6438, CU.load(x6450)), op=FixAdd, results=List(CU.scalarOut(x6429_b7314_x6455_b7322_s)))
    }
    val x6459 = MemoryController(name="x6459",parent=x6489,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6429_b7314 = ScalarFIFO(size=1,name="offset").wtPort(x6429_b7314_x6455_b7322_s)
      val x6429_b7315 = ScalarFIFO(size=1,name="size").wtPort(x6429_b7315_x6455_b7323_s)
      CU.newVout("data", x6431_x6459_data_v)
    }
    val x6488 = Sequential(name="x6488",parent=x6489) { implicit CU => 
      val x6488_unit = CounterChain(name = "x6488_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6471_0 = Pipeline(name="x6471_0",parent=x6488) { implicit CU => 
      val x6430_b7317 = ScalarFIFO(size=16,name="x6430_b7317").wtPort(x6430_b7317_x6457_b7325_s)
      val x6430_b7316 = ScalarFIFO(size=16,name="x6430_b7316").wtPort(x6430_b7316_x6457_b7324_s)
      val x6430_b7318 = ScalarFIFO(size=16,name="x6430_b7318").wtPort(x6430_b7318_x6457_b7326_s)
      val x6471_unit = CounterChain(name = "x6471_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6430_b7317)), op=Bypass, results=List(CU.scalarOut(x6460_x6466_s)))
      Stage(operands=List(CU.load(x6430_b7318)), op=Bypass, results=List(CU.scalarOut(x6461_x6468_s)))
      Stage(operands=List(CU.load(x6430_b7316)), op=Bypass, results=List(CU.scalarOut(x6462_x6470_s)))
    }
    val x6487_0 = Pipeline(name="x6487_0",parent=x6488) { implicit CU => 
      val x6462 = ScalarBuffer(name="x6462").wtPort(x6462_x6470_s)
      val x6461 = ScalarBuffer(name="x6461").wtPort(x6461_x6468_s)
      val x6431 = VectorFIFO(size=1,name="x6431").wtPort(x6431_x6459_data_v)
      val x6460 = ScalarBuffer(name="x6460").wtPort(x6460_x6466_s)
      val ctr8 = Counter(min=Const(0), max=x6462.readPort, step=Const(1), par=16) // Counter
      val x6474 = CounterChain(name = "x6474", ctr8).iter(1)
      Stage(operands=List(CU.load(x6460), CU.ctr(x6474(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6474(0)), CU.load(x6461)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6431)), op=Bypass, results=List(CU.scalarOut(x6209_x6486_s)))
    }
    val x6499_0 = Pipeline(name="x6499_0",parent=x6641) { implicit CU => 
      val x6214 = ScalarBuffer(name="x6214").wtPort(x6214_x6221_s)
      val x6212 = ScalarBuffer(name="x6212").wtPort(x6212_x6220_s)
      val x6499_unit = CounterChain(name = "x6499_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6214), CU.load(x6212)), op=FixSub, results=List(CU.scalarOut(x6492_x6498_s)))
    }
    val x6505_0 = Pipeline(name="x6505_0",parent=x6641) { implicit CU => 
      val x6213 = ScalarBuffer(name="x6213").wtPort(x6213_x6227_s)
      val x6215 = ScalarBuffer(name="x6215").wtPort(x6215_x6228_s)
      val x6505_unit = CounterChain(name = "x6505_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6215), CU.load(x6213)), op=FixSub, results=List(CU.scalarOut(x6493_x6504_s)))
    }
    val x6519 = StreamController(name="x6519",parent=x6641) { implicit CU => 
      val x6519_unit = CounterChain(name = "x6519_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6519_0 = Pipeline(name="x6519_0",parent=x6519) { implicit CU => 
      val x6511 = CU.temp(None)
      val x6514 = CU.temp(None)
      val x6513 = CU.temp(None)
      val x6512 = CU.temp(None)
      val x6492 = ScalarBuffer(name="x6492").wtPort(x6492_x6498_s)
      Stage(operands=List(CU.load(x6492), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1506_s)))
      Stage(operands=List(CU.load(x6492), Const(16)), op=FixMod, results=List(x6511))
      Stage(operands=List(x6511, Const(0)), op=FixEql, results=List(x6512))
      Stage(operands=List(CU.load(x6492), Const(16)), op=FixAdd, results=List(x6513))
      Stage(operands=List(x6513, x6511), op=FixSub, results=List(x6514))
      Stage(operands=List(x6512, CU.load(x6492), x6514), op=Mux, results=List(CU.scalarOut(bus_1512_s)))
    }
    val x6519_1 = Pipeline(name="x6519_1",parent=x6519) { implicit CU => 
      val x6515 = ScalarFIFO(size=1,name="x6515").wtPort(bus_1512_s)
      val x6510 = ScalarFIFO(size=1,name="x6510").wtPort(bus_1506_s)
      Stage(operands=List(CU.load(x6510), Const(16), CU.load(x6515)), op=Mux, results=List(CU.scalarOut(x6507_x6518_s)))
    }
    val x6530 = StreamController(name="x6530",parent=x6641) { implicit CU => 
      val x6530_unit = CounterChain(name = "x6530_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6530_0 = Pipeline(name="x6530_0",parent=x6530) { implicit CU => 
      val x6522 = CU.temp(None)
      val x6523 = CU.temp(None)
      val x6525 = CU.temp(None)
      val x6524 = CU.temp(None)
      val x6493 = ScalarBuffer(name="x6493").wtPort(x6493_x6504_s)
      Stage(operands=List(CU.load(x6493), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1516_s)))
      Stage(operands=List(CU.load(x6493), Const(16)), op=FixMod, results=List(x6522))
      Stage(operands=List(x6522, Const(0)), op=FixEql, results=List(x6523))
      Stage(operands=List(CU.load(x6493), Const(16)), op=FixAdd, results=List(x6524))
      Stage(operands=List(x6524, x6522), op=FixSub, results=List(x6525))
      Stage(operands=List(x6523, CU.load(x6493), x6525), op=Mux, results=List(CU.scalarOut(bus_1522_s)))
    }
    val x6530_1 = Pipeline(name="x6530_1",parent=x6530) { implicit CU => 
      val x6521 = ScalarFIFO(size=1,name="x6521").wtPort(bus_1516_s)
      val x6526 = ScalarFIFO(size=1,name="x6526").wtPort(bus_1522_s)
      Stage(operands=List(CU.load(x6521), Const(16), CU.load(x6526)), op=Mux, results=List(CU.scalarOut(x6508_x6529_s)))
    }
    val x6564 = StreamController(name="x6564",parent=x6641) { implicit CU => 
      val x6564_unit = CounterChain(name = "x6564_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6550_0 = Pipeline(name="x6550_0",parent=x6564) { implicit CU => 
      val x6538 = CU.temp(None)
      val x6545 = CU.temp(None)
      val x6547 = CU.temp(None)
      val x6543 = ScalarFIFO(size=1,name="x6543").wtPort(x6206_0_s)
      val x6507 = ScalarBuffer(name="x6507").wtPort(x6507_x6518_s)
      val x6492 = ScalarBuffer(name="x6492").wtPort(x6492_x6498_s)
      val x6539 = ScalarBuffer(name="x6539").wtPort(vec_dram_da)
      val ctr9 = Counter(min=Const(0), max=x6507.readPort, step=Const(1), par=1) // Counter
      val x6536 = CounterChain(name = "x6536", ctr9).iter(1)
      Stage(operands=List(CU.load(x6492), CU.ctr(x6536(0))), op=FixLeq, results=List(x6538))
      Stage(operands=List(CU.load(x6543), Const(2)), op=FixSla, results=List(x6545))
      Stage(operands=List(x6545, CU.load(x6539)), op=FixAdd, results=List(x6547))
      Stage(operands=List(x6538, CU.load(x6539), x6547), op=Mux, results=List(CU.scalarOut(x6532_x6549_s)))
    }
    val x6551 = MemoryController(name="x6551",parent=x6564,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x6532 = ScalarFIFO(size=1,name="addr").wtPort(x6532_x6549_s)
      CU.newSout("data", x6533_x6551_data_s)
    }
    val x6563_0 = Pipeline(name="x6563_0",parent=x6564) { implicit CU => 
      val x6507 = ScalarBuffer(name="x6507").wtPort(x6507_x6518_s)
      val x6533 = ScalarFIFO(size=1,name="x6533").wtPort(x6533_x6551_data_s)
      val ctr10 = Counter(min=Const(0), max=x6507.readPort, step=Const(1), par=1) // Counter
      val x6554 = CounterChain(name = "x6554", ctr10).iter(1)
      Stage(operands=List(CU.load(x6533)), op=Bypass, results=List(CU.scalarOut(x6210_x6562_s)))
    }
    val x6597 = StreamController(name="x6597",parent=x6641) { implicit CU => 
      val x6597_unit = CounterChain(name = "x6597_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6583_0 = Pipeline(name="x6583_0",parent=x6597) { implicit CU => 
      val x6578 = CU.temp(None)
      val x6580 = CU.temp(None)
      val x6571 = CU.temp(None)
      val x6572 = ScalarBuffer(name="x6572").wtPort(vec_dram_da)
      val x6508 = ScalarBuffer(name="x6508").wtPort(x6508_x6529_s)
      val x6493 = ScalarBuffer(name="x6493").wtPort(x6493_x6504_s)
      val x6576 = ScalarFIFO(size=1,name="x6576").wtPort(x6207_0_s)
      val ctr11 = Counter(min=Const(0), max=x6508.readPort, step=Const(1), par=1) // Counter
      val x6569 = CounterChain(name = "x6569", ctr11).iter(1)
      Stage(operands=List(CU.load(x6493), CU.ctr(x6569(0))), op=FixLeq, results=List(x6571))
      Stage(operands=List(CU.load(x6576), Const(2)), op=FixSla, results=List(x6578))
      Stage(operands=List(x6578, CU.load(x6572)), op=FixAdd, results=List(x6580))
      Stage(operands=List(x6571, CU.load(x6572), x6580), op=Mux, results=List(CU.scalarOut(x6565_x6582_s)))
    }
    val x6584 = MemoryController(name="x6584",parent=x6597,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x6565 = ScalarFIFO(size=1,name="addr").wtPort(x6565_x6582_s)
      CU.newSout("data", x6566_x6584_data_s)
    }
    val x6596_0 = Pipeline(name="x6596_0",parent=x6597) { implicit CU => 
      val x6566 = ScalarFIFO(size=1,name="x6566").wtPort(x6566_x6584_data_s)
      val x6508 = ScalarBuffer(name="x6508").wtPort(x6508_x6529_s)
      val ctr12 = Counter(min=Const(0), max=x6508.readPort, step=Const(1), par=1) // Counter
      val x6587 = CounterChain(name = "x6587", ctr12).iter(1)
      Stage(operands=List(CU.load(x6566)), op=Bypass, results=List(CU.scalarOut(x6211_x6595_s)))
    }
    val x6615_0 = Pipeline(name="x6615_0",parent=x6641) { implicit CU => 
      val x6606 = ScalarFIFO(size=1,name="x6606").wtPort(x6208_0_s)
      val x6492 = ScalarBuffer(name="x6492").wtPort(x6492_x6498_s)
      val x6607 = ScalarFIFO(size=1,name="x6607").wtPort(x6210_0_s)
      val ctr13 = Counter(min=Const(0), max=x6492.readPort, step=Const(1), par=16) // Counter
      val x6603 = CounterChain(name = "x6603", ctr13).iter(1)
      Stage(operands=List(CU.load(x6606), CU.load(x6607)), op=FixMul, results=List(CU.reduce))
      val (_, rr1550) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x6615_0")
      Stage(operands=List(rr1550), op=Bypass, results=List(CU.scalarOut(x6599_x6614_s)))
    }
    val x6630_0 = Pipeline(name="x6630_0",parent=x6641) { implicit CU => 
      val x6622 = ScalarFIFO(size=1,name="x6622").wtPort(x6211_0_s)
      val x6493 = ScalarBuffer(name="x6493").wtPort(x6493_x6504_s)
      val x6621 = ScalarFIFO(size=1,name="x6621").wtPort(x6209_0_s)
      val ctr14 = Counter(min=Const(0), max=x6493.readPort, step=Const(1), par=16) // Counter
      val x6618 = CounterChain(name = "x6618", ctr14).iter(1)
      Stage(operands=List(CU.load(x6621), CU.load(x6622)), op=FixMul, results=List(CU.reduce))
      val (_, rr1557) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x6630_0")
      Stage(operands=List(rr1557), op=Bypass, results=List(CU.scalarOut(x6600_x6629_s)))
    }
    val x6635_0 = Pipeline(name="x6635_0",parent=x6641) { implicit CU => 
      val x6599 = ScalarBuffer(name="x6599").wtPort(x6599_x6614_s)
      val x6635_unit = CounterChain(name = "x6635_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6599)), op=Bypass, results=List(CU.scalarOut(x6062_x6634_s)))
    }
    val x6639_0 = Pipeline(name="x6639_0",parent=x6641) { implicit CU => 
      val x6600 = ScalarBuffer(name="x6600").wtPort(x6600_x6629_s)
      val x6639_unit = CounterChain(name = "x6639_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6600)), op=Bypass, results=List(CU.scalarOut(x6062_x6638_s)))
    }
    val x7079 = MetaPipeline(name="x7079",parent=x7211) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(494), step=Const(1), par=2) // Counter
      val x6643 = CounterChain(name = "x6643", ctr15).iter(247)
    }
    val x6644_dsp0 = MemoryPipeline(name="x6644_dsp0",parent="x7079") { implicit CU => 
      val x6707 = ScalarBuffer(name="x6707").wtPort(x6707_x6713_s)
      val x6733 = ScalarFIFO(size=1,name="x6733").wtPort(x6644_x6733_s)
      val x6721 = CounterChain.copy("x6734_0", "x6721")
      val x6974 = CounterChain.copy("x6988_0", "x6974")
      val x6644 = SRAM(size=494,name="x6644",banking = Strided(1)).wtPort(x6733.readPort).rdPort(x6644_0_s).rdAddr(x6974(0))
      WAStage(operands=List(CU.ctr(x6721(0)), CU.load(x6707)), op=FixSub, results=List(x6644.writeAddr))
    }
    val x6645_dsp0 = MemoryPipeline(name="x6645_dsp0",parent="x7079") { implicit CU => 
      val x6837 = ScalarBuffer(name="x6837").wtPort(x6837_x6843_s)
      val x6863 = ScalarFIFO(size=1,name="x6863").wtPort(x6645_x6863_s)
      val x6851 = CounterChain.copy("x6864_0", "x6851")
      val x7007 = CounterChain.copy("x7021_0", "x7007")
      val x6645 = SRAM(size=494,name="x6645",banking = Strided(1)).wtPort(x6863.readPort).rdPort(x6645_0_s).rdAddr(x7007(0))
      WAStage(operands=List(CU.ctr(x6851(0)), CU.load(x6837)), op=FixSub, results=List(x6645.writeAddr))
    }
    val x6646_dsp0 = MemoryPipeline(name="x6646_dsp0",parent="x7079") { implicit CU => 
      val x6794 = ScalarFIFO(size=1,name="x6794").wtPort(x6646_x6794_s)
      val x6768 = ScalarBuffer(name="x6768").wtPort(x6768_x6774_s)
      val x6782 = CounterChain.copy("x6795_0", "x6782")
      val x7041 = CounterChain.copy("x7053_0", "x7041")
      val x6646 = SRAM(size=494,name="x6646",banking = Strided(1)).wtPort(x6794.readPort).rdPort(x6646_0_s).rdAddr(x7041(0))
      WAStage(operands=List(CU.ctr(x6782(0)), CU.load(x6768)), op=FixSub, results=List(x6646.writeAddr))
    }
    val x6647_dsp0 = MemoryPipeline(name="x6647_dsp0",parent="x7079") { implicit CU => 
      val x6898 = ScalarBuffer(name="x6898").wtPort(x6898_x6904_s)
      val x6924 = ScalarFIFO(size=1,name="x6924").wtPort(x6647_x6924_s)
      val x6912 = CounterChain.copy("x6925_0", "x6912")
      val x7056 = CounterChain.copy("x7068_0", "x7056")
      val x6647 = SRAM(size=494,name="x6647",banking = Strided(1)).wtPort(x6924.readPort).rdPort(x6647_0_s).rdAddr(x7056(0))
      WAStage(operands=List(CU.ctr(x6912(0)), CU.load(x6898)), op=FixSub, results=List(x6647.writeAddr))
    }
    val x6648_dsp0 = MemoryPipeline(name="x6648_dsp0",parent="x7079") { implicit CU => 
      val x7000 = ScalarFIFO(size=1,name="x7000").wtPort(x6648_x7000_s)
      val x6992 = CounterChain.copy("x7001_0", "x6992")
      val x7041 = CounterChain.copy("x7053_0", "x7041")
      val x6648 = SRAM(size=494,name="x6648",banking = Strided(1)).wtPort(x7000.readPort).rdPort(x6648_0_s).rdAddr(x7041(0)).wtAddr(x6992(0))
    }
    val x6649_dsp0 = MemoryPipeline(name="x6649_dsp0",parent="x7079") { implicit CU => 
      val x7033 = ScalarFIFO(size=1,name="x7033").wtPort(x6649_x7033_s)
      val x7025 = CounterChain.copy("x7034_0", "x7025")
      val x7056 = CounterChain.copy("x7068_0", "x7056")
      val x6649 = SRAM(size=494,name="x6649",banking = Strided(1)).wtPort(x7033.readPort).rdPort(x6649_0_s).rdAddr(x7056(0)).wtAddr(x7025(0))
    }
    val x6660_0 = Pipeline(name="x6660_0",parent=x7079) { implicit CU => 
      val x6655 = ScalarFIFO(size=1,name="x6655").wtPort(x6061_1_s)
      val x6657 = ScalarFIFO(size=1,name="x6657").wtPort(x6061_0_s)
      val x6660_unit = CounterChain(name = "x6660_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6655)), op=Bypass, results=List(CU.scalarOut(x6650_x6658_s)))
      Stage(operands=List(CU.load(x6657)), op=Bypass, results=List(CU.scalarOut(x6652_x6659_s)))
    }
    val x6667_0 = Pipeline(name="x6667_0",parent=x7079) { implicit CU => 
      val x6662 = ScalarFIFO(size=1,name="x6662").wtPort(x6061_1_s)
      val x6664 = ScalarFIFO(size=1,name="x6664").wtPort(x6061_0_s)
      val x6667_unit = CounterChain(name = "x6667_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6662)), op=Bypass, results=List(CU.scalarOut(x6651_x6665_s)))
      Stage(operands=List(CU.load(x6664)), op=Bypass, results=List(CU.scalarOut(x6653_x6666_s)))
    }
    val x6675_0 = Pipeline(name="x6675_0",parent=x7079) { implicit CU => 
      val x6650 = ScalarBuffer(name="x6650").wtPort(x6650_x6658_s)
      val x6652 = ScalarBuffer(name="x6652").wtPort(x6652_x6659_s)
      val x6675_unit = CounterChain(name = "x6675_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6652), CU.load(x6650)), op=FixSub, results=List(CU.scalarOut(x6669_x6674_s)))
    }
    val x6736 = StreamController(name="x6736",parent=x7079) { implicit CU => 
      val x6736_unit = CounterChain(name = "x6736_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6705 = StreamController(name="x6705",parent=x6736) { implicit CU => 
      val x6705_unit = CounterChain(name = "x6705_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6705_0 = Pipeline(name="x6705_0",parent=x6705) { implicit CU => 
      val x6689 = CU.temp(None)
      val x6682 = CU.temp(None)
      val x6681 = CU.temp(None)
      val x6650 = ScalarBuffer(name="x6650").wtPort(x6650_x6658_s)
      val x6669 = ScalarBuffer(name="x6669").wtPort(x6669_x6674_s)
      Stage(operands=List(CU.load(x6650), Const(2)), op=FixSla, results=List(x6681, CU.scalarOut(bus_1586_s)))
      Stage(operands=List(x6681, Const(64)), op=FixMod, results=List(x6682, CU.scalarOut(bus_1588_s)))
      Stage(operands=List(x6682, Const(4)), op=FixDiv, results=List(x6689, CU.scalarOut(bus_1590_s), CU.scalarOut(x6677_b7330_x6704_b7338_s)))
      Stage(operands=List(x6689, CU.load(x6669)), op=FixAdd, results=List(CU.scalarOut(x6677_b7331_x6704_b7339_s)))
      Stage(operands=List(CU.load(x6669), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1592_s)))
    }
    val x6705_1 = Pipeline(name="x6705_1",parent=x6705) { implicit CU => 
      val x6688 = CU.temp(None)
      val x6687 = CU.temp(None)
      val x6690 = CU.temp(None)
      val x6686 = CU.temp(None)
      val x6692 = CU.temp(None)
      val x6684 = ScalarFIFO(size=1,name="x6684").wtPort(bus_1592_s)
      val x6681 = ScalarFIFO(size=1,name="x6681").wtPort(bus_1586_s)
      val x6689 = ScalarFIFO(size=1,name="x6689").wtPort(bus_1590_s)
      val x6669 = ScalarBuffer(name="x6669").wtPort(x6669_x6674_s)
      Stage(operands=List(CU.load(x6681), CU.load(x6684)), op=FixAdd, results=List(x6686))
      Stage(operands=List(x6686, Const(64)), op=FixMod, results=List(x6687))
      Stage(operands=List(Const(64), x6687), op=FixSub, results=List(x6688, CU.scalarOut(bus_1595_s)))
      Stage(operands=List(x6688, Const(4)), op=FixDiv, results=List(x6690))
      Stage(operands=List(CU.load(x6669), CU.load(x6689)), op=FixAdd, results=List(x6692))
      Stage(operands=List(x6692, x6690), op=FixAdd, results=List(CU.scalarOut(x6677_b7329_x6704_b7337_s)))
    }
    val x6705_2 = Pipeline(name="x6705_2",parent=x6705) { implicit CU => 
      val x6685 = CU.temp(None)
      val x6694 = CU.temp(None)
      val x6684 = ScalarFIFO(size=1,name="x6684").wtPort(bus_1592_s)
      val x6688 = ScalarFIFO(size=1,name="x6688").wtPort(bus_1595_s)
      val x6697 = ScalarBuffer(name="x6697").wtPort(cols_dram_da)
      val x6681 = ScalarFIFO(size=1,name="x6681").wtPort(bus_1586_s)
      val x6682 = ScalarFIFO(size=1,name="x6682").wtPort(bus_1588_s)
      Stage(operands=List(CU.load(x6684), CU.load(x6682)), op=FixAdd, results=List(x6694))
      Stage(operands=List(x6694, CU.load(x6688)), op=FixAdd, results=List(CU.scalarOut(x6676_b7328_x6702_b7336_s)))
      Stage(operands=List(CU.load(x6681), CU.load(x6682)), op=FixSub, results=List(x6685))
      Stage(operands=List(x6685, CU.load(x6697)), op=FixAdd, results=List(CU.scalarOut(x6676_b7327_x6702_b7335_s)))
    }
    val x6706 = MemoryController(name="x6706",parent=x6736,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6676_b7328 = ScalarFIFO(size=1,name="size").wtPort(x6676_b7328_x6702_b7336_s)
      val x6676_b7327 = ScalarFIFO(size=1,name="offset").wtPort(x6676_b7327_x6702_b7335_s)
      CU.newVout("data", x6678_x6706_data_v)
    }
    val x6735 = Sequential(name="x6735",parent=x6736) { implicit CU => 
      val x6735_unit = CounterChain(name = "x6735_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6718_0 = Pipeline(name="x6718_0",parent=x6735) { implicit CU => 
      val x6677_b7329 = ScalarFIFO(size=16,name="x6677_b7329").wtPort(x6677_b7329_x6704_b7337_s)
      val x6677_b7331 = ScalarFIFO(size=16,name="x6677_b7331").wtPort(x6677_b7331_x6704_b7339_s)
      val x6677_b7330 = ScalarFIFO(size=16,name="x6677_b7330").wtPort(x6677_b7330_x6704_b7338_s)
      val x6718_unit = CounterChain(name = "x6718_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6677_b7330)), op=Bypass, results=List(CU.scalarOut(x6707_x6713_s)))
      Stage(operands=List(CU.load(x6677_b7331)), op=Bypass, results=List(CU.scalarOut(x6708_x6715_s)))
      Stage(operands=List(CU.load(x6677_b7329)), op=Bypass, results=List(CU.scalarOut(x6709_x6717_s)))
    }
    val x6734_0 = Pipeline(name="x6734_0",parent=x6735) { implicit CU => 
      val x6707 = ScalarBuffer(name="x6707").wtPort(x6707_x6713_s)
      val x6709 = ScalarBuffer(name="x6709").wtPort(x6709_x6717_s)
      val x6678 = VectorFIFO(size=1,name="x6678").wtPort(x6678_x6706_data_v)
      val x6708 = ScalarBuffer(name="x6708").wtPort(x6708_x6715_s)
      val ctr16 = Counter(min=Const(0), max=x6709.readPort, step=Const(1), par=16) // Counter
      val x6721 = CounterChain(name = "x6721", ctr16).iter(1)
      Stage(operands=List(CU.load(x6707), CU.ctr(x6721(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6721(0)), CU.load(x6708)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6678)), op=Bypass, results=List(CU.scalarOut(x6644_x6733_s)))
    }
    val x6797 = StreamController(name="x6797",parent=x7079) { implicit CU => 
      val x6797_unit = CounterChain(name = "x6797_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6766 = StreamController(name="x6766",parent=x6797) { implicit CU => 
      val x6766_unit = CounterChain(name = "x6766_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6766_0 = Pipeline(name="x6766_0",parent=x6766) { implicit CU => 
      val x6742 = CU.temp(None)
      val x6750 = CU.temp(None)
      val x6743 = CU.temp(None)
      val x6650 = ScalarBuffer(name="x6650").wtPort(x6650_x6658_s)
      val x6669 = ScalarBuffer(name="x6669").wtPort(x6669_x6674_s)
      Stage(operands=List(CU.load(x6650), Const(2)), op=FixSla, results=List(x6742, CU.scalarOut(bus_1613_s)))
      Stage(operands=List(x6742, Const(64)), op=FixMod, results=List(x6743, CU.scalarOut(bus_1615_s)))
      Stage(operands=List(x6743, Const(4)), op=FixDiv, results=List(x6750, CU.scalarOut(bus_1617_s), CU.scalarOut(x6738_b7343_x6765_b7351_s)))
      Stage(operands=List(x6750, CU.load(x6669)), op=FixAdd, results=List(CU.scalarOut(x6738_b7344_x6765_b7352_s)))
      Stage(operands=List(CU.load(x6669), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1619_s)))
    }
    val x6766_1 = Pipeline(name="x6766_1",parent=x6766) { implicit CU => 
      val x6751 = CU.temp(None)
      val x6747 = CU.temp(None)
      val x6748 = CU.temp(None)
      val x6753 = CU.temp(None)
      val x6749 = CU.temp(None)
      val x6750 = ScalarFIFO(size=1,name="x6750").wtPort(bus_1617_s)
      val x6742 = ScalarFIFO(size=1,name="x6742").wtPort(bus_1613_s)
      val x6745 = ScalarFIFO(size=1,name="x6745").wtPort(bus_1619_s)
      val x6669 = ScalarBuffer(name="x6669").wtPort(x6669_x6674_s)
      Stage(operands=List(CU.load(x6742), CU.load(x6745)), op=FixAdd, results=List(x6747))
      Stage(operands=List(x6747, Const(64)), op=FixMod, results=List(x6748))
      Stage(operands=List(Const(64), x6748), op=FixSub, results=List(x6749, CU.scalarOut(bus_1622_s)))
      Stage(operands=List(x6749, Const(4)), op=FixDiv, results=List(x6751))
      Stage(operands=List(CU.load(x6669), CU.load(x6750)), op=FixAdd, results=List(x6753))
      Stage(operands=List(x6753, x6751), op=FixAdd, results=List(CU.scalarOut(x6738_b7342_x6765_b7350_s)))
    }
    val x6766_2 = Pipeline(name="x6766_2",parent=x6766) { implicit CU => 
      val x6755 = CU.temp(None)
      val x6746 = CU.temp(None)
      val x6745 = ScalarFIFO(size=1,name="x6745").wtPort(bus_1619_s)
      val x6749 = ScalarFIFO(size=1,name="x6749").wtPort(bus_1622_s)
      val x6742 = ScalarFIFO(size=1,name="x6742").wtPort(bus_1613_s)
      val x6743 = ScalarFIFO(size=1,name="x6743").wtPort(bus_1615_s)
      val x6758 = ScalarBuffer(name="x6758").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x6745), CU.load(x6743)), op=FixAdd, results=List(x6755))
      Stage(operands=List(x6755, CU.load(x6749)), op=FixAdd, results=List(CU.scalarOut(x6737_b7341_x6763_b7349_s)))
      Stage(operands=List(CU.load(x6742), CU.load(x6743)), op=FixSub, results=List(x6746))
      Stage(operands=List(x6746, CU.load(x6758)), op=FixAdd, results=List(CU.scalarOut(x6737_b7340_x6763_b7348_s)))
    }
    val x6767 = MemoryController(name="x6767",parent=x6797,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6737_b7341 = ScalarFIFO(size=1,name="size").wtPort(x6737_b7341_x6763_b7349_s)
      val x6737_b7340 = ScalarFIFO(size=1,name="offset").wtPort(x6737_b7340_x6763_b7348_s)
      CU.newVout("data", x6739_x6767_data_v)
    }
    val x6796 = Sequential(name="x6796",parent=x6797) { implicit CU => 
      val x6796_unit = CounterChain(name = "x6796_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6779_0 = Pipeline(name="x6779_0",parent=x6796) { implicit CU => 
      val x6738_b7344 = ScalarFIFO(size=16,name="x6738_b7344").wtPort(x6738_b7344_x6765_b7352_s)
      val x6738_b7343 = ScalarFIFO(size=16,name="x6738_b7343").wtPort(x6738_b7343_x6765_b7351_s)
      val x6738_b7342 = ScalarFIFO(size=16,name="x6738_b7342").wtPort(x6738_b7342_x6765_b7350_s)
      val x6779_unit = CounterChain(name = "x6779_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6738_b7343)), op=Bypass, results=List(CU.scalarOut(x6768_x6774_s)))
      Stage(operands=List(CU.load(x6738_b7344)), op=Bypass, results=List(CU.scalarOut(x6769_x6776_s)))
      Stage(operands=List(CU.load(x6738_b7342)), op=Bypass, results=List(CU.scalarOut(x6770_x6778_s)))
    }
    val x6795_0 = Pipeline(name="x6795_0",parent=x6796) { implicit CU => 
      val x6770 = ScalarBuffer(name="x6770").wtPort(x6770_x6778_s)
      val x6739 = VectorFIFO(size=1,name="x6739").wtPort(x6739_x6767_data_v)
      val x6769 = ScalarBuffer(name="x6769").wtPort(x6769_x6776_s)
      val x6768 = ScalarBuffer(name="x6768").wtPort(x6768_x6774_s)
      val ctr17 = Counter(min=Const(0), max=x6770.readPort, step=Const(1), par=16) // Counter
      val x6782 = CounterChain(name = "x6782", ctr17).iter(1)
      Stage(operands=List(CU.load(x6768), CU.ctr(x6782(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6782(0)), CU.load(x6769)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6739)), op=Bypass, results=List(CU.scalarOut(x6646_x6794_s)))
    }
    val x6805_0 = Pipeline(name="x6805_0",parent=x7079) { implicit CU => 
      val x6653 = ScalarBuffer(name="x6653").wtPort(x6653_x6666_s)
      val x6651 = ScalarBuffer(name="x6651").wtPort(x6651_x6665_s)
      val x6805_unit = CounterChain(name = "x6805_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6653), CU.load(x6651)), op=FixSub, results=List(CU.scalarOut(x6799_x6804_s)))
    }
    val x6866 = StreamController(name="x6866",parent=x7079) { implicit CU => 
      val x6866_unit = CounterChain(name = "x6866_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6835 = StreamController(name="x6835",parent=x6866) { implicit CU => 
      val x6835_unit = CounterChain(name = "x6835_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6835_0 = Pipeline(name="x6835_0",parent=x6835) { implicit CU => 
      val x6811 = CU.temp(None)
      val x6819 = CU.temp(None)
      val x6812 = CU.temp(None)
      val x6799 = ScalarBuffer(name="x6799").wtPort(x6799_x6804_s)
      val x6651 = ScalarBuffer(name="x6651").wtPort(x6651_x6665_s)
      Stage(operands=List(CU.load(x6651), Const(2)), op=FixSla, results=List(x6811, CU.scalarOut(bus_1642_s)))
      Stage(operands=List(x6811, Const(64)), op=FixMod, results=List(x6812, CU.scalarOut(bus_1644_s)))
      Stage(operands=List(x6812, Const(4)), op=FixDiv, results=List(x6819, CU.scalarOut(bus_1646_s), CU.scalarOut(x6807_b7356_x6834_b7364_s)))
      Stage(operands=List(x6819, CU.load(x6799)), op=FixAdd, results=List(CU.scalarOut(x6807_b7357_x6834_b7365_s)))
      Stage(operands=List(CU.load(x6799), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1648_s)))
    }
    val x6835_1 = Pipeline(name="x6835_1",parent=x6835) { implicit CU => 
      val x6817 = CU.temp(None)
      val x6818 = CU.temp(None)
      val x6820 = CU.temp(None)
      val x6822 = CU.temp(None)
      val x6816 = CU.temp(None)
      val x6799 = ScalarBuffer(name="x6799").wtPort(x6799_x6804_s)
      val x6814 = ScalarFIFO(size=1,name="x6814").wtPort(bus_1648_s)
      val x6811 = ScalarFIFO(size=1,name="x6811").wtPort(bus_1642_s)
      val x6819 = ScalarFIFO(size=1,name="x6819").wtPort(bus_1646_s)
      Stage(operands=List(CU.load(x6811), CU.load(x6814)), op=FixAdd, results=List(x6816))
      Stage(operands=List(x6816, Const(64)), op=FixMod, results=List(x6817))
      Stage(operands=List(Const(64), x6817), op=FixSub, results=List(x6818, CU.scalarOut(bus_1651_s)))
      Stage(operands=List(x6818, Const(4)), op=FixDiv, results=List(x6820))
      Stage(operands=List(CU.load(x6799), CU.load(x6819)), op=FixAdd, results=List(x6822))
      Stage(operands=List(x6822, x6820), op=FixAdd, results=List(CU.scalarOut(x6807_b7355_x6834_b7363_s)))
    }
    val x6835_2 = Pipeline(name="x6835_2",parent=x6835) { implicit CU => 
      val x6824 = CU.temp(None)
      val x6815 = CU.temp(None)
      val x6811 = ScalarFIFO(size=1,name="x6811").wtPort(bus_1642_s)
      val x6818 = ScalarFIFO(size=1,name="x6818").wtPort(bus_1651_s)
      val x6812 = ScalarFIFO(size=1,name="x6812").wtPort(bus_1644_s)
      val x6814 = ScalarFIFO(size=1,name="x6814").wtPort(bus_1648_s)
      val x6827 = ScalarBuffer(name="x6827").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x6814), CU.load(x6812)), op=FixAdd, results=List(x6824))
      Stage(operands=List(x6824, CU.load(x6818)), op=FixAdd, results=List(CU.scalarOut(x6806_b7354_x6832_b7362_s)))
      Stage(operands=List(CU.load(x6811), CU.load(x6812)), op=FixSub, results=List(x6815))
      Stage(operands=List(x6815, CU.load(x6827)), op=FixAdd, results=List(CU.scalarOut(x6806_b7353_x6832_b7361_s)))
    }
    val x6836 = MemoryController(name="x6836",parent=x6866,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6806_b7353 = ScalarFIFO(size=1,name="offset").wtPort(x6806_b7353_x6832_b7361_s)
      val x6806_b7354 = ScalarFIFO(size=1,name="size").wtPort(x6806_b7354_x6832_b7362_s)
      CU.newVout("data", x6808_x6836_data_v)
    }
    val x6865 = Sequential(name="x6865",parent=x6866) { implicit CU => 
      val x6865_unit = CounterChain(name = "x6865_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6848_0 = Pipeline(name="x6848_0",parent=x6865) { implicit CU => 
      val x6807_b7356 = ScalarFIFO(size=16,name="x6807_b7356").wtPort(x6807_b7356_x6834_b7364_s)
      val x6807_b7355 = ScalarFIFO(size=16,name="x6807_b7355").wtPort(x6807_b7355_x6834_b7363_s)
      val x6807_b7357 = ScalarFIFO(size=16,name="x6807_b7357").wtPort(x6807_b7357_x6834_b7365_s)
      val x6848_unit = CounterChain(name = "x6848_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6807_b7356)), op=Bypass, results=List(CU.scalarOut(x6837_x6843_s)))
      Stage(operands=List(CU.load(x6807_b7357)), op=Bypass, results=List(CU.scalarOut(x6838_x6845_s)))
      Stage(operands=List(CU.load(x6807_b7355)), op=Bypass, results=List(CU.scalarOut(x6839_x6847_s)))
    }
    val x6864_0 = Pipeline(name="x6864_0",parent=x6865) { implicit CU => 
      val x6839 = ScalarBuffer(name="x6839").wtPort(x6839_x6847_s)
      val x6808 = VectorFIFO(size=1,name="x6808").wtPort(x6808_x6836_data_v)
      val x6838 = ScalarBuffer(name="x6838").wtPort(x6838_x6845_s)
      val x6837 = ScalarBuffer(name="x6837").wtPort(x6837_x6843_s)
      val ctr18 = Counter(min=Const(0), max=x6839.readPort, step=Const(1), par=16) // Counter
      val x6851 = CounterChain(name = "x6851", ctr18).iter(1)
      Stage(operands=List(CU.load(x6837), CU.ctr(x6851(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6851(0)), CU.load(x6838)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6808)), op=Bypass, results=List(CU.scalarOut(x6645_x6863_s)))
    }
    val x6927 = StreamController(name="x6927",parent=x7079) { implicit CU => 
      val x6927_unit = CounterChain(name = "x6927_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6896 = StreamController(name="x6896",parent=x6927) { implicit CU => 
      val x6896_unit = CounterChain(name = "x6896_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6896_0 = Pipeline(name="x6896_0",parent=x6896) { implicit CU => 
      val x6873 = CU.temp(None)
      val x6880 = CU.temp(None)
      val x6872 = CU.temp(None)
      val x6799 = ScalarBuffer(name="x6799").wtPort(x6799_x6804_s)
      val x6651 = ScalarBuffer(name="x6651").wtPort(x6651_x6665_s)
      Stage(operands=List(CU.load(x6651), Const(2)), op=FixSla, results=List(x6872, CU.scalarOut(bus_1669_s)))
      Stage(operands=List(x6872, Const(64)), op=FixMod, results=List(x6873, CU.scalarOut(bus_1671_s)))
      Stage(operands=List(x6873, Const(4)), op=FixDiv, results=List(x6880, CU.scalarOut(bus_1673_s), CU.scalarOut(x6868_b7369_x6895_b7377_s)))
      Stage(operands=List(x6880, CU.load(x6799)), op=FixAdd, results=List(CU.scalarOut(x6868_b7370_x6895_b7378_s)))
      Stage(operands=List(CU.load(x6799), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1675_s)))
    }
    val x6896_1 = Pipeline(name="x6896_1",parent=x6896) { implicit CU => 
      val x6883 = CU.temp(None)
      val x6881 = CU.temp(None)
      val x6878 = CU.temp(None)
      val x6877 = CU.temp(None)
      val x6879 = CU.temp(None)
      val x6872 = ScalarFIFO(size=1,name="x6872").wtPort(bus_1669_s)
      val x6875 = ScalarFIFO(size=1,name="x6875").wtPort(bus_1675_s)
      val x6799 = ScalarBuffer(name="x6799").wtPort(x6799_x6804_s)
      val x6880 = ScalarFIFO(size=1,name="x6880").wtPort(bus_1673_s)
      Stage(operands=List(CU.load(x6872), CU.load(x6875)), op=FixAdd, results=List(x6877))
      Stage(operands=List(x6877, Const(64)), op=FixMod, results=List(x6878))
      Stage(operands=List(Const(64), x6878), op=FixSub, results=List(x6879, CU.scalarOut(bus_1678_s)))
      Stage(operands=List(x6879, Const(4)), op=FixDiv, results=List(x6881))
      Stage(operands=List(CU.load(x6799), CU.load(x6880)), op=FixAdd, results=List(x6883))
      Stage(operands=List(x6883, x6881), op=FixAdd, results=List(CU.scalarOut(x6868_b7368_x6895_b7376_s)))
    }
    val x6896_2 = Pipeline(name="x6896_2",parent=x6896) { implicit CU => 
      val x6876 = CU.temp(None)
      val x6885 = CU.temp(None)
      val x6872 = ScalarFIFO(size=1,name="x6872").wtPort(bus_1669_s)
      val x6879 = ScalarFIFO(size=1,name="x6879").wtPort(bus_1678_s)
      val x6875 = ScalarFIFO(size=1,name="x6875").wtPort(bus_1675_s)
      val x6873 = ScalarFIFO(size=1,name="x6873").wtPort(bus_1671_s)
      val x6888 = ScalarBuffer(name="x6888").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x6875), CU.load(x6873)), op=FixAdd, results=List(x6885))
      Stage(operands=List(x6885, CU.load(x6879)), op=FixAdd, results=List(CU.scalarOut(x6867_b7367_x6893_b7375_s)))
      Stage(operands=List(CU.load(x6872), CU.load(x6873)), op=FixSub, results=List(x6876))
      Stage(operands=List(x6876, CU.load(x6888)), op=FixAdd, results=List(CU.scalarOut(x6867_b7366_x6893_b7374_s)))
    }
    val x6897 = MemoryController(name="x6897",parent=x6927,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x6867_b7367 = ScalarFIFO(size=1,name="size").wtPort(x6867_b7367_x6893_b7375_s)
      val x6867_b7366 = ScalarFIFO(size=1,name="offset").wtPort(x6867_b7366_x6893_b7374_s)
      CU.newVout("data", x6869_x6897_data_v)
    }
    val x6926 = Sequential(name="x6926",parent=x6927) { implicit CU => 
      val x6926_unit = CounterChain(name = "x6926_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6909_0 = Pipeline(name="x6909_0",parent=x6926) { implicit CU => 
      val x6868_b7368 = ScalarFIFO(size=16,name="x6868_b7368").wtPort(x6868_b7368_x6895_b7376_s)
      val x6868_b7370 = ScalarFIFO(size=16,name="x6868_b7370").wtPort(x6868_b7370_x6895_b7378_s)
      val x6868_b7369 = ScalarFIFO(size=16,name="x6868_b7369").wtPort(x6868_b7369_x6895_b7377_s)
      val x6909_unit = CounterChain(name = "x6909_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6868_b7369)), op=Bypass, results=List(CU.scalarOut(x6898_x6904_s)))
      Stage(operands=List(CU.load(x6868_b7370)), op=Bypass, results=List(CU.scalarOut(x6899_x6906_s)))
      Stage(operands=List(CU.load(x6868_b7368)), op=Bypass, results=List(CU.scalarOut(x6900_x6908_s)))
    }
    val x6925_0 = Pipeline(name="x6925_0",parent=x6926) { implicit CU => 
      val x6869 = VectorFIFO(size=1,name="x6869").wtPort(x6869_x6897_data_v)
      val x6898 = ScalarBuffer(name="x6898").wtPort(x6898_x6904_s)
      val x6900 = ScalarBuffer(name="x6900").wtPort(x6900_x6908_s)
      val x6899 = ScalarBuffer(name="x6899").wtPort(x6899_x6906_s)
      val ctr19 = Counter(min=Const(0), max=x6900.readPort, step=Const(1), par=16) // Counter
      val x6912 = CounterChain(name = "x6912", ctr19).iter(1)
      Stage(operands=List(CU.load(x6898), CU.ctr(x6912(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x6912(0)), CU.load(x6899)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x6869)), op=Bypass, results=List(CU.scalarOut(x6647_x6924_s)))
    }
    val x6937_0 = Pipeline(name="x6937_0",parent=x7079) { implicit CU => 
      val x6650 = ScalarBuffer(name="x6650").wtPort(x6650_x6658_s)
      val x6652 = ScalarBuffer(name="x6652").wtPort(x6652_x6659_s)
      val x6937_unit = CounterChain(name = "x6937_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6652), CU.load(x6650)), op=FixSub, results=List(CU.scalarOut(x6930_x6936_s)))
    }
    val x6943_0 = Pipeline(name="x6943_0",parent=x7079) { implicit CU => 
      val x6653 = ScalarBuffer(name="x6653").wtPort(x6653_x6666_s)
      val x6651 = ScalarBuffer(name="x6651").wtPort(x6651_x6665_s)
      val x6943_unit = CounterChain(name = "x6943_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x6653), CU.load(x6651)), op=FixSub, results=List(CU.scalarOut(x6931_x6942_s)))
    }
    val x6957 = StreamController(name="x6957",parent=x7079) { implicit CU => 
      val x6957_unit = CounterChain(name = "x6957_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6957_0 = Pipeline(name="x6957_0",parent=x6957) { implicit CU => 
      val x6950 = CU.temp(None)
      val x6949 = CU.temp(None)
      val x6951 = CU.temp(None)
      val x6952 = CU.temp(None)
      val x6930 = ScalarBuffer(name="x6930").wtPort(x6930_x6936_s)
      Stage(operands=List(CU.load(x6930), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1700_s)))
      Stage(operands=List(CU.load(x6930), Const(16)), op=FixMod, results=List(x6949))
      Stage(operands=List(x6949, Const(0)), op=FixEql, results=List(x6950))
      Stage(operands=List(CU.load(x6930), Const(16)), op=FixAdd, results=List(x6951))
      Stage(operands=List(x6951, x6949), op=FixSub, results=List(x6952))
      Stage(operands=List(x6950, CU.load(x6930), x6952), op=Mux, results=List(CU.scalarOut(bus_1706_s)))
    }
    val x6957_1 = Pipeline(name="x6957_1",parent=x6957) { implicit CU => 
      val x6953 = ScalarFIFO(size=1,name="x6953").wtPort(bus_1706_s)
      val x6948 = ScalarFIFO(size=1,name="x6948").wtPort(bus_1700_s)
      Stage(operands=List(CU.load(x6948), Const(16), CU.load(x6953)), op=Mux, results=List(CU.scalarOut(x6945_x6956_s)))
    }
    val x6968 = StreamController(name="x6968",parent=x7079) { implicit CU => 
      val x6968_unit = CounterChain(name = "x6968_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6968_0 = Pipeline(name="x6968_0",parent=x6968) { implicit CU => 
      val x6962 = CU.temp(None)
      val x6963 = CU.temp(None)
      val x6960 = CU.temp(None)
      val x6961 = CU.temp(None)
      val x6931 = ScalarBuffer(name="x6931").wtPort(x6931_x6942_s)
      Stage(operands=List(CU.load(x6931), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_1710_s)))
      Stage(operands=List(CU.load(x6931), Const(16)), op=FixMod, results=List(x6960))
      Stage(operands=List(x6960, Const(0)), op=FixEql, results=List(x6961))
      Stage(operands=List(CU.load(x6931), Const(16)), op=FixAdd, results=List(x6962))
      Stage(operands=List(x6962, x6960), op=FixSub, results=List(x6963))
      Stage(operands=List(x6961, CU.load(x6931), x6963), op=Mux, results=List(CU.scalarOut(bus_1716_s)))
    }
    val x6968_1 = Pipeline(name="x6968_1",parent=x6968) { implicit CU => 
      val x6964 = ScalarFIFO(size=1,name="x6964").wtPort(bus_1716_s)
      val x6959 = ScalarFIFO(size=1,name="x6959").wtPort(bus_1710_s)
      Stage(operands=List(CU.load(x6959), Const(16), CU.load(x6964)), op=Mux, results=List(CU.scalarOut(x6946_x6967_s)))
    }
    val x7002 = StreamController(name="x7002",parent=x7079) { implicit CU => 
      val x7002_unit = CounterChain(name = "x7002_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6988_0 = Pipeline(name="x6988_0",parent=x7002) { implicit CU => 
      val x6985 = CU.temp(None)
      val x6976 = CU.temp(None)
      val x6983 = CU.temp(None)
      val x6981 = ScalarFIFO(size=1,name="x6981").wtPort(x6644_0_s)
      val x6945 = ScalarBuffer(name="x6945").wtPort(x6945_x6956_s)
      val x6930 = ScalarBuffer(name="x6930").wtPort(x6930_x6936_s)
      val x6977 = ScalarBuffer(name="x6977").wtPort(vec_dram_da)
      val ctr20 = Counter(min=Const(0), max=x6945.readPort, step=Const(1), par=1) // Counter
      val x6974 = CounterChain(name = "x6974", ctr20).iter(1)
      Stage(operands=List(CU.load(x6930), CU.ctr(x6974(0))), op=FixLeq, results=List(x6976))
      Stage(operands=List(CU.load(x6981), Const(2)), op=FixSla, results=List(x6983))
      Stage(operands=List(x6983, CU.load(x6977)), op=FixAdd, results=List(x6985))
      Stage(operands=List(x6976, CU.load(x6977), x6985), op=Mux, results=List(CU.scalarOut(x6970_x6987_s)))
    }
    val x6989 = MemoryController(name="x6989",parent=x7002,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x6970 = ScalarFIFO(size=1,name="addr").wtPort(x6970_x6987_s)
      CU.newSout("data", x6971_x6989_data_s)
    }
    val x7001_0 = Pipeline(name="x7001_0",parent=x7002) { implicit CU => 
      val x6945 = ScalarBuffer(name="x6945").wtPort(x6945_x6956_s)
      val x6971 = ScalarFIFO(size=1,name="x6971").wtPort(x6971_x6989_data_s)
      val ctr21 = Counter(min=Const(0), max=x6945.readPort, step=Const(1), par=1) // Counter
      val x6992 = CounterChain(name = "x6992", ctr21).iter(1)
      Stage(operands=List(CU.load(x6971)), op=Bypass, results=List(CU.scalarOut(x6648_x7000_s)))
    }
    val x7035 = StreamController(name="x7035",parent=x7079) { implicit CU => 
      val x7035_unit = CounterChain(name = "x7035_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7021_0 = Pipeline(name="x7021_0",parent=x7035) { implicit CU => 
      val x7009 = CU.temp(None)
      val x7016 = CU.temp(None)
      val x7018 = CU.temp(None)
      val x6946 = ScalarBuffer(name="x6946").wtPort(x6946_x6967_s)
      val x6931 = ScalarBuffer(name="x6931").wtPort(x6931_x6942_s)
      val x7014 = ScalarFIFO(size=1,name="x7014").wtPort(x6645_0_s)
      val x7010 = ScalarBuffer(name="x7010").wtPort(vec_dram_da)
      val ctr22 = Counter(min=Const(0), max=x6946.readPort, step=Const(1), par=1) // Counter
      val x7007 = CounterChain(name = "x7007", ctr22).iter(1)
      Stage(operands=List(CU.load(x6931), CU.ctr(x7007(0))), op=FixLeq, results=List(x7009))
      Stage(operands=List(CU.load(x7014), Const(2)), op=FixSla, results=List(x7016))
      Stage(operands=List(x7016, CU.load(x7010)), op=FixAdd, results=List(x7018))
      Stage(operands=List(x7009, CU.load(x7010), x7018), op=Mux, results=List(CU.scalarOut(x7003_x7020_s)))
    }
    val x7022 = MemoryController(name="x7022",parent=x7035,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x7003 = ScalarFIFO(size=1,name="addr").wtPort(x7003_x7020_s)
      CU.newSout("data", x7004_x7022_data_s)
    }
    val x7034_0 = Pipeline(name="x7034_0",parent=x7035) { implicit CU => 
      val x6946 = ScalarBuffer(name="x6946").wtPort(x6946_x6967_s)
      val x7004 = ScalarFIFO(size=1,name="x7004").wtPort(x7004_x7022_data_s)
      val ctr23 = Counter(min=Const(0), max=x6946.readPort, step=Const(1), par=1) // Counter
      val x7025 = CounterChain(name = "x7025", ctr23).iter(1)
      Stage(operands=List(CU.load(x7004)), op=Bypass, results=List(CU.scalarOut(x6649_x7033_s)))
    }
    val x7053_0 = Pipeline(name="x7053_0",parent=x7079) { implicit CU => 
      val x7045 = ScalarFIFO(size=1,name="x7045").wtPort(x6648_0_s)
      val x7044 = ScalarFIFO(size=1,name="x7044").wtPort(x6646_0_s)
      val x6930 = ScalarBuffer(name="x6930").wtPort(x6930_x6936_s)
      val ctr24 = Counter(min=Const(0), max=x6930.readPort, step=Const(1), par=16) // Counter
      val x7041 = CounterChain(name = "x7041", ctr24).iter(1)
      Stage(operands=List(CU.load(x7044), CU.load(x7045)), op=FixMul, results=List(CU.reduce))
      val (_, rr1744) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x7053_0")
      Stage(operands=List(rr1744), op=Bypass, results=List(CU.scalarOut(x7037_x7052_s)))
    }
    val x7068_0 = Pipeline(name="x7068_0",parent=x7079) { implicit CU => 
      val x7060 = ScalarFIFO(size=1,name="x7060").wtPort(x6649_0_s)
      val x6931 = ScalarBuffer(name="x6931").wtPort(x6931_x6942_s)
      val x7059 = ScalarFIFO(size=1,name="x7059").wtPort(x6647_0_s)
      val ctr25 = Counter(min=Const(0), max=x6931.readPort, step=Const(1), par=16) // Counter
      val x7056 = CounterChain(name = "x7056", ctr25).iter(1)
      Stage(operands=List(CU.load(x7059), CU.load(x7060)), op=FixMul, results=List(CU.reduce))
      val (_, rr1751) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x7068_0")
      Stage(operands=List(rr1751), op=Bypass, results=List(CU.scalarOut(x7038_x7067_s)))
    }
    val x7073_0 = Pipeline(name="x7073_0",parent=x7079) { implicit CU => 
      val x7037 = ScalarBuffer(name="x7037").wtPort(x7037_x7052_s)
      val x7073_unit = CounterChain(name = "x7073_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x7037)), op=Bypass, results=List(CU.scalarOut(x6063_x7072_s)))
    }
    val x7077_0 = Pipeline(name="x7077_0",parent=x7079) { implicit CU => 
      val x7038 = ScalarBuffer(name="x7038").wtPort(x7038_x7067_s)
      val x7077_unit = CounterChain(name = "x7077_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x7038)), op=Bypass, results=List(CU.scalarOut(x6063_x7076_s)))
    }
    val x7091_0 = Pipeline(name="x7091_0",parent=x7211) { implicit CU => 
      val x7085 = CU.temp(None)
      val x7087 = CU.temp(None)
      val x6066 = ScalarBuffer(name="x6066").wtPort(x6066_x6075_s)
      val x6059 = CounterChain.copy("x7211", "x6059")
      val x7091_unit = CounterChain(name = "x7091_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6059(0)), Const(494)), op=FixMul, results=List(x7085, CU.scalarOut(x7081_x7089_s)))
      Stage(operands=List(CU.load(x6066), Const(494)), op=FixMul, results=List(x7087))
      Stage(operands=List(x7087, x7085), op=FixSub, results=List(CU.scalarOut(x7083_x7090_s)))
    }
    val x7098_0 = Pipeline(name="x7098_0",parent=x7211) { implicit CU => 
      val x7092 = CU.temp(None)
      val x7094 = CU.temp(None)
      val x6067 = ScalarBuffer(name="x6067").wtPort(x6067_x6083_s)
      val x6059 = CounterChain.copy("x7211", "x6059")
      val x7098_unit = CounterChain(name = "x7098_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6059(0)), Const(494)), op=FixMul, results=List(x7092, CU.scalarOut(x7082_x7096_s)))
      Stage(operands=List(CU.load(x6067), Const(494)), op=FixMul, results=List(x7094))
      Stage(operands=List(x7094, x7092), op=FixSub, results=List(CU.scalarOut(x7084_x7097_s)))
    }
    val x7154 = StreamController(name="x7154",parent=x7211) { implicit CU => 
      val x7154_unit = CounterChain(name = "x7154_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7150 = Sequential(name="x7150",parent=x7154) { implicit CU => 
      val x7150_unit = CounterChain(name = "x7150_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7132 = StreamController(name="x7132",parent=x7150) { implicit CU => 
      val x7132_unit = CounterChain(name = "x7132_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7132_0 = Pipeline(name="x7132_0",parent=x7132) { implicit CU => 
      val x7114 = CU.temp(None)
      val x7108 = CU.temp(None)
      val x7113 = CU.temp(None)
      val x7111 = CU.temp(None)
      val x7115 = CU.temp(None)
      val x7081 = ScalarBuffer(name="x7081").wtPort(x7081_x7089_s)
      val x7083 = ScalarBuffer(name="x7083").wtPort(x7083_x7090_s)
      Stage(operands=List(CU.load(x7081), Const(2)), op=FixSla, results=List(x7108, CU.scalarOut(bus_1764_s)))
      Stage(operands=List(CU.load(x7083), Const(2)), op=FixSla, results=List(x7111, CU.scalarOut(bus_1765_s)))
      Stage(operands=List(x7108, x7111), op=FixAdd, results=List(x7113))
      Stage(operands=List(x7113, Const(64)), op=FixMod, results=List(x7114))
      Stage(operands=List(Const(64), x7114), op=FixSub, results=List(x7115, CU.scalarOut(bus_1769_s)))
      Stage(operands=List(x7115, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1771_s)))
    }
    val x7132_1 = Pipeline(name="x7132_1",parent=x7132) { implicit CU => 
      val x7116 = CU.temp(None)
      val x7109 = CU.temp(None)
      val x7119 = CU.temp(None)
      val x7083 = ScalarBuffer(name="x7083").wtPort(x7083_x7090_s)
      val x7117 = ScalarFIFO(size=1,name="x7117").wtPort(bus_1771_s)
      val x7108 = ScalarFIFO(size=1,name="x7108").wtPort(bus_1764_s)
      Stage(operands=List(CU.load(x7108), Const(64)), op=FixMod, results=List(x7109, CU.scalarOut(bus_1772_s)))
      Stage(operands=List(x7109, Const(4)), op=FixDiv, results=List(x7116, CU.scalarOut(x7103_x7129_s)))
      Stage(operands=List(CU.load(x7083), x7116), op=FixAdd, results=List(x7119))
      Stage(operands=List(x7119, CU.load(x7117)), op=FixAdd, results=List(CU.scalarOut(x7105_x7131_s)))
      Stage(operands=List(x7116, CU.load(x7083)), op=FixAdd, results=List(CU.scalarOut(x7104_x7130_s)))
    }
    val x7132_2 = Pipeline(name="x7132_2",parent=x7132) { implicit CU => 
      val x7121 = CU.temp(None)
      val x7112 = CU.temp(None)
      val x7111 = ScalarFIFO(size=1,name="x7111").wtPort(bus_1765_s)
      val x7115 = ScalarFIFO(size=1,name="x7115").wtPort(bus_1769_s)
      val x7108 = ScalarFIFO(size=1,name="x7108").wtPort(bus_1764_s)
      val x7109 = ScalarFIFO(size=1,name="x7109").wtPort(bus_1772_s)
      val x7124 = ScalarBuffer(name="x7124").wtPort(result_dram_da)
      Stage(operands=List(CU.load(x7111), CU.load(x7109)), op=FixAdd, results=List(x7121))
      Stage(operands=List(x7121, CU.load(x7115)), op=FixAdd, results=List(CU.scalarOut(x7100_b7380_x7128_b7382_s)))
      Stage(operands=List(CU.load(x7108), CU.load(x7109)), op=FixSub, results=List(x7112))
      Stage(operands=List(x7112, CU.load(x7124)), op=FixAdd, results=List(CU.scalarOut(x7100_b7379_x7128_b7381_s)))
    }
    val x7149_0 = Pipeline(name="x7149_0",parent=x7150) { implicit CU => 
      val x7137 = CU.temp(None)
      val x7140 = CU.temp(None)
      val x7139 = CU.temp(None)
      val x7144 = ScalarFIFO(size=1,name="x7144").wtPort(x6062_0_s)
      val x7105 = ScalarBuffer(name="x7105").wtPort(x7105_x7131_s)
      val x7104 = ScalarBuffer(name="x7104").wtPort(x7104_x7130_s)
      val x7103 = ScalarBuffer(name="x7103").wtPort(x7103_x7129_s)
      val ctr26 = Counter(min=Const(0), max=x7105.readPort, step=Const(1), par=16) // Counter
      val x7135 = CounterChain(name = "x7135", ctr26).iter(1)
      Stage(operands=List(CU.load(x7103), CU.ctr(x7135(0))), op=FixLeq, results=List(x7137))
      Stage(operands=List(CU.ctr(x7135(0)), CU.load(x7104)), op=FixLt, results=List(x7139))
      Stage(operands=List(x7137, x7139), op=BitAnd, results=List(x7140))
      Stage(operands=List(x7140, CU.load(x7144), Const(0)), op=Mux, results=List(CU.vecOut(x7101_x7148_v)))
    }
    val x7151 = MemoryController(name="x7151",parent=x7154,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x7100_b7380 = ScalarFIFO(size=1,name="size").wtPort(x7100_b7380_x7128_b7382_s)
      val x7100_b7379 = ScalarFIFO(size=1,name="offset").wtPort(x7100_b7379_x7128_b7381_s)
      val x7101 = VectorFIFO(size=1,name="data").wtPort(x7101_x7148_v)
    }
    val x7209 = StreamController(name="x7209",parent=x7211) { implicit CU => 
      val x7209_unit = CounterChain(name = "x7209_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7205 = Sequential(name="x7205",parent=x7209) { implicit CU => 
      val x7205_unit = CounterChain(name = "x7205_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7187 = StreamController(name="x7187",parent=x7205) { implicit CU => 
      val x7187_unit = CounterChain(name = "x7187_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x7187_0 = Pipeline(name="x7187_0",parent=x7187) { implicit CU => 
      val x7166 = CU.temp(None)
      val x7168 = CU.temp(None)
      val x7169 = CU.temp(None)
      val x7170 = CU.temp(None)
      val x7163 = CU.temp(None)
      val x7084 = ScalarBuffer(name="x7084").wtPort(x7084_x7097_s)
      val x7082 = ScalarBuffer(name="x7082").wtPort(x7082_x7096_s)
      Stage(operands=List(CU.load(x7082), Const(2)), op=FixSla, results=List(x7163, CU.scalarOut(bus_1790_s)))
      Stage(operands=List(CU.load(x7084), Const(2)), op=FixSla, results=List(x7166, CU.scalarOut(bus_1791_s)))
      Stage(operands=List(x7163, x7166), op=FixAdd, results=List(x7168))
      Stage(operands=List(x7168, Const(64)), op=FixMod, results=List(x7169))
      Stage(operands=List(Const(64), x7169), op=FixSub, results=List(x7170, CU.scalarOut(bus_1795_s)))
      Stage(operands=List(x7170, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_1797_s)))
    }
    val x7187_1 = Pipeline(name="x7187_1",parent=x7187) { implicit CU => 
      val x7171 = CU.temp(None)
      val x7164 = CU.temp(None)
      val x7174 = CU.temp(None)
      val x7084 = ScalarBuffer(name="x7084").wtPort(x7084_x7097_s)
      val x7172 = ScalarFIFO(size=1,name="x7172").wtPort(bus_1797_s)
      val x7163 = ScalarFIFO(size=1,name="x7163").wtPort(bus_1790_s)
      Stage(operands=List(CU.load(x7163), Const(64)), op=FixMod, results=List(x7164, CU.scalarOut(bus_1798_s)))
      Stage(operands=List(x7164, Const(4)), op=FixDiv, results=List(x7171, CU.scalarOut(x7158_x7184_s)))
      Stage(operands=List(CU.load(x7084), x7171), op=FixAdd, results=List(x7174))
      Stage(operands=List(x7174, CU.load(x7172)), op=FixAdd, results=List(CU.scalarOut(x7160_x7186_s)))
      Stage(operands=List(x7171, CU.load(x7084)), op=FixAdd, results=List(CU.scalarOut(x7159_x7185_s)))
    }
    val x7187_2 = Pipeline(name="x7187_2",parent=x7187) { implicit CU => 
      val x7176 = CU.temp(None)
      val x7167 = CU.temp(None)
      val x7179 = ScalarBuffer(name="x7179").wtPort(result_dram_da)
      val x7166 = ScalarFIFO(size=1,name="x7166").wtPort(bus_1791_s)
      val x7163 = ScalarFIFO(size=1,name="x7163").wtPort(bus_1790_s)
      val x7164 = ScalarFIFO(size=1,name="x7164").wtPort(bus_1798_s)
      val x7170 = ScalarFIFO(size=1,name="x7170").wtPort(bus_1795_s)
      Stage(operands=List(CU.load(x7166), CU.load(x7164)), op=FixAdd, results=List(x7176))
      Stage(operands=List(x7176, CU.load(x7170)), op=FixAdd, results=List(CU.scalarOut(x7155_b7384_x7183_b7386_s)))
      Stage(operands=List(CU.load(x7163), CU.load(x7164)), op=FixSub, results=List(x7167))
      Stage(operands=List(x7167, CU.load(x7179)), op=FixAdd, results=List(CU.scalarOut(x7155_b7383_x7183_b7385_s)))
    }
    val x7204_0 = Pipeline(name="x7204_0",parent=x7205) { implicit CU => 
      val x7192 = CU.temp(None)
      val x7195 = CU.temp(None)
      val x7194 = CU.temp(None)
      val x7158 = ScalarBuffer(name="x7158").wtPort(x7158_x7184_s)
      val x7199 = ScalarFIFO(size=1,name="x7199").wtPort(x6063_0_s)
      val x7160 = ScalarBuffer(name="x7160").wtPort(x7160_x7186_s)
      val x7159 = ScalarBuffer(name="x7159").wtPort(x7159_x7185_s)
      val ctr27 = Counter(min=Const(0), max=x7160.readPort, step=Const(1), par=16) // Counter
      val x7190 = CounterChain(name = "x7190", ctr27).iter(1)
      Stage(operands=List(CU.load(x7158), CU.ctr(x7190(0))), op=FixLeq, results=List(x7192))
      Stage(operands=List(CU.ctr(x7190(0)), CU.load(x7159)), op=FixLt, results=List(x7194))
      Stage(operands=List(x7192, x7194), op=BitAnd, results=List(x7195))
      Stage(operands=List(x7195, CU.load(x7199), Const(0)), op=Mux, results=List(CU.vecOut(x7156_x7203_v)))
    }
    val x7206 = MemoryController(name="x7206",parent=x7209,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x7156 = VectorFIFO(size=1,name="data").wtPort(x7156_x7203_v)
      val x7155_b7383 = ScalarFIFO(size=1,name="offset").wtPort(x7155_b7383_x7183_b7385_s)
      val x7155_b7384 = ScalarFIFO(size=1,name="size").wtPort(x7155_b7384_x7183_b7386_s)
    }
    
  }
}
