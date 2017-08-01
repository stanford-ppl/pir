import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Backprop extends PIRApp {
  def main(top:Top) = {
    val x17107_2_s = Scalar("x17107_2")
    val bus_1957_s = Scalar("bus_1957")
    val bus_2163_s = Scalar("bus_2163")
    val bus_2225_s = Scalar("bus_2225")
    val bus_2073_s = Scalar("bus_2073")
    val bus_2047_s = Scalar("bus_2047")
    val weights2_dram_oc = OffChip("weights2_dram")
    val bus_1730_s = Scalar("bus_1730")
    val x17098_2_s = Scalar("x17098_2")
    val weights3_dram_oc = OffChip("weights3_dram")
    val bus_1989_s = Scalar("bus_1989")
    val bus_1828_v = Vector("bus_1828")
    val bus_1954_s = Scalar("bus_1954")
    val x16948_1_s = Scalar("x16948_1")
    val bus_2012_s = Scalar("bus_2012")
    val bus_2071_s = Scalar("bus_2071")
    val x17155_x17162_s = Scalar("x17155_x17162")
    val x17014_b18380_x17026_b18382_s = Scalar("x17014_b18380_x17026_b18382")
    val x17973_b18487_x17983_b18489_s = Scalar("x17973_b18487_x17983_b18489")
    val bus_2066_s = Scalar("bus_2066")
    val x17652_x17670_s = Scalar("x17652_x17670")
    val bus_2009_s = Scalar("bus_2009")
    val x17154_x17160_s = Scalar("x17154_x17160")
    val x16949_1_s = Scalar("x16949_1")
    val x16950_2_s = Scalar("x16950_2")
    val x16953_x17089_s = Scalar("x16953_x17089")
    val x17039_b18386_x17051_b18388_s = Scalar("x17039_b18386_x17051_b18388")
    val x17672_x17688_s = Scalar("x17672_x17688")
    val x17950_x17966_v = Vector("x17950_x17966")
    val training_targets_dram_da = DRAMAddress("training_targets_dram", "training_targets_dram")
    val bus_1836_v = Vector("bus_1836")
    val x17124_b18402_x17150_b18410_s = Scalar("x17124_b18402_x17150_b18410")
    val bus_2089_s = Scalar("bus_2089")
    val x17191_b18412_x17216_b18420_s = Scalar("x17191_b18412_x17216_b18420")
    val bus_2003_s = Scalar("bus_2003")
    val x17191_b18411_x17216_b18419_s = Scalar("x17191_b18411_x17216_b18419")
    val bus_2176_s = Scalar("bus_2176")
    val bus_1870_v = Vector("bus_1870")
    val bus_2198_s = Scalar("bus_2198")
    val biases1_dram_da = DRAMAddress("biases1_dram", "biases1_dram")
    val x17101_x17280_s = Scalar("x17101_x17280")
    val x17107_x17428_s = Scalar("x17107_x17428")
    val x16951_1_s = Scalar("x16951_1")
    val bus_1889_v = Vector("bus_1889")
    val bus_1765_s = Scalar("bus_1765")
    val x17221_x17226_s = Scalar("x17221_x17226")
    val bus_2010_s = Scalar("bus_2010")
    val x17323_x17336_s = Scalar("x17323_x17336")
    val x16993_b18377_x17002_b18379_s = Scalar("x16993_b18377_x17002_b18379")
    val x17112_2_s = Scalar("x17112_2")
    val bus_2218_s = Scalar("bus_2218")
    val training_targets_dram_oc = OffChip("training_targets_dram")
    val x16953_x17903_s = Scalar("x16953_x17903")
    val bus_1877_v = Vector("bus_1877")
    val x17105_x17245_s = Scalar("x17105_x17245")
    val bus_1940_s = Scalar("bus_1940")
    val x17125_x17152_data_v = Vector("x17125_x17152_data")
    val x16952_2_s = Scalar("x16952_2")
    val bus_2019_s = Scalar("bus_2019")
    val bus_2113_s = Scalar("bus_2113")
    val x17100_2_s = Scalar("x17100_2")
    val x16953_2_s = Scalar("x16953_2")
    val bus_2228_s = Scalar("bus_2228")
    val bus_1952_s = Scalar("bus_1952")
    val bus_2180_s = Scalar("bus_2180")
    val x17541_x17557_s = Scalar("x17541_x17557")
    val bus_2160_s = Scalar("bus_2160")
    val x17104_0_s = Scalar("x17104_0")
    val x17559_x17629_s = Scalar("x17559_x17629")
    val bus_2001_s = Scalar("bus_2001")
    val x17124_b18401_x17150_b18409_s = Scalar("x17124_b18401_x17150_b18409")
    val x16952_0_s = Scalar("x16952_0")
    val bus_1855_v = Vector("bus_1855")
    val biases2_dram_da = DRAMAddress("biases2_dram", "biases2_dram")
    val x17357_x17385_s = Scalar("x17357_x17385")
    val bus_1879_v = Vector("bus_1879")
    val bus_1760_s = Scalar("bus_1760")
    val bus_2158_s = Scalar("bus_2158")
    val x16953_5_s = Scalar("x16953_5")
    val weights1_dram_da = DRAMAddress("weights1_dram", "weights1_dram")
    val x17123_b18398_x17148_b18406_s = Scalar("x17123_b18398_x17148_b18406")
    val x16949_0_s = Scalar("x16949_0")
    val x16952_1_s = Scalar("x16952_1")
    val x17103_x17352_s = Scalar("x17103_x17352")
    val x16952_4_s = Scalar("x16952_4")
    val x17251_x17264_s = Scalar("x17251_x17264")
    val x17097_x17121_s = Scalar("x17097_x17121")
    val bus_2000_s = Scalar("bus_2000")
    val x16951_x17641_s = Scalar("x16951_x17641")
    val x17108_0_s = Scalar("x17108_0")
    val x16948_x17650_s = Scalar("x16948_x17650")
    val bus_2212_s = Scalar("bus_2212")
    val x16953_4_s = Scalar("x16953_4")
    val x17040_x17053_data_v = Vector("x17040_x17053_data")
    val bus_2146_s = Scalar("bus_2146")
    val x18058_x18074_v = Vector("x18058_x18074")
    val x17444_x17461_s = Scalar("x17444_x17461")
    val x16952_5_s = Scalar("x16952_5")
    val bus_2068_s = Scalar("bus_2068")
    val bus_2151_s = Scalar("bus_2151")
    val bus_1866_v = Vector("bus_1866")
    val biases2_dram_oc = OffChip("biases2_dram")
    val biases3_dram_da = DRAMAddress("biases3_dram", "biases3_dram")
    val bus_1991_s = Scalar("bus_1991")
    val bus_1995_s = Scalar("bus_1995")
    val x16949_x16990_s = Scalar("x16949_x16990")
    val x17098_1_s = Scalar("x17098_1")
    val x18057_b18502_x18067_b18504_s = Scalar("x18057_b18502_x18067_b18504")
    val bus_2162_s = Scalar("bus_2162")
    val x17109_x17479_s = Scalar("x17109_x17479")
    val x16952_x17059_s = Scalar("x16952_x17059")
    val bus_2173_s = Scalar("bus_2173")
    val bus_2013_s = Scalar("bus_2013")
    val x17099_1_s = Scalar("x17099_1")
    val x17192_b18413_x17218_b18421_s = Scalar("x17192_b18413_x17218_b18421")
    val x17104_1_s = Scalar("x17104_1")
    val x16952_x17772_s = Scalar("x16952_x17772")
    val x17484_x17501_s = Scalar("x17484_x17501")
    val bus_1945_s = Scalar("bus_1945")
    val bus_1758_s = Scalar("bus_1758")
    val x16994_x17004_data_v = Vector("x16994_x17004_data")
    val bus_1959_s = Scalar("bus_1959")
    val x18000_x18020_v = Vector("x18000_x18020")
    val x17104_x17177_s = Scalar("x17104_x17177")
    val bus_2206_s = Scalar("bus_2206")
    val bus_2122_s = Scalar("bus_2122")
    val bus_1944_s = Scalar("bus_1944")
    val bus_2048_s = Scalar("bus_2048")
    val bus_2062_s = Scalar("bus_2062")
    val x17192_b18415_x17218_b18423_s = Scalar("x17192_b18415_x17218_b18423")
    val x16993_b18376_x17002_b18378_s = Scalar("x16993_b18376_x17002_b18378")
    val x17949_b18482_x17959_b18484_s = Scalar("x17949_b18482_x17959_b18484")
    val x16950_1_s = Scalar("x16950_1")
    val x16954_x17923_s = Scalar("x16954_x17923")
    val x16952_x17664_s = Scalar("x16952_x17664")
    val x16951_2_s = Scalar("x16951_2")
    val bus_2104_s = Scalar("bus_2104")
    val x17443_x17447_s = Scalar("x17443_x17447")
    val x17123_b18399_x17148_b18407_s = Scalar("x17123_b18399_x17148_b18407")
    val x17560_x17630_s = Scalar("x17560_x17630")
    val bus_2184_s = Scalar("bus_2184")
    val x17110_x17439_s = Scalar("x17110_x17439")
    val x17483_x17487_s = Scalar("x17483_x17487")
    val x16951_0_s = Scalar("x16951_0")
    val training_data_dram_da = DRAMAddress("training_data_dram", "training_data_dram")
    val bus_1986_s = Scalar("bus_1986")
    val bus_2165_s = Scalar("bus_2165")
    val bus_2195_s = Scalar("bus_2195")
    val x17821_x17891_s = Scalar("x17821_x17891")
    val x17124_b18400_x17150_b18408_s = Scalar("x17124_b18400_x17150_b18408")
    val weights3_dram_da = DRAMAddress("weights3_dram", "weights3_dram")
    val bus_1832_v = Vector("bus_1832")
    val bus_2097_s = Scalar("bus_2097")
    val x16974_b18372_x16983_b18374_s = Scalar("x16974_b18372_x16983_b18374")
    val x17014_b18381_x17026_b18383_s = Scalar("x17014_b18381_x17026_b18383")
    val x18029_b18496_x18042_b18498_s = Scalar("x18029_b18496_x18042_b18498")
    val x17521_x17539_s = Scalar("x17521_x17539")
    val bus_1965_s = Scalar("bus_1965")
    val x16950_4_s = Scalar("x16950_4")
    val bus_1947_s = Scalar("bus_1947")
    val bus_2050_s = Scalar("bus_2050")
    val x16950_x17813_s = Scalar("x16950_x17813")
    val bus_2213_s = Scalar("bus_2213")
    val x17112_0_s = Scalar("x17112_0")
    val bus_2109_s = Scalar("bus_2109")
    val x17111_1_s = Scalar("x17111_1")
    val bus_1994_s = Scalar("bus_1994")
    val bus_2059_s = Scalar("bus_2059")
    val x17100_x17355_s = Scalar("x17100_x17355")
    val x17102_x17316_s = Scalar("x17102_x17316")
    val x17925_b18478_x17935_b18480_s = Scalar("x17925_b18478_x17935_b18480")
    val x17099_x17306_s = Scalar("x17099_x17306")
    val bus_1853_v = Vector("bus_1853")
    val bus_1970_s = Scalar("bus_1970")
    val x16951_3_s = Scalar("x16951_3")
    val bus_1967_s = Scalar("bus_1967")
    val x16953_3_s = Scalar("x16953_3")
    val x17111_x17507_s = Scalar("x17111_x17507")
    val bus_2200_s = Scalar("bus_2200")
    val x17112_1_s = Scalar("x17112_1")
    val x17153_x17158_s = Scalar("x17153_x17158")
    val x17100_x17342_s = Scalar("x17100_x17342")
    val x17096_x17120_s = Scalar("x17096_x17120")
    val bus_1850_v = Vector("bus_1850")
    val x17181_x17188_s = Scalar("x17181_x17188")
    val bus_2095_s = Scalar("bus_2095")
    val x17107_0_s = Scalar("x17107_0")
    val weights1_dram_oc = OffChip("weights1_dram")
    val weights2_dram_da = DRAMAddress("weights2_dram", "weights2_dram")
    val bus_1842_v = Vector("bus_1842")
    val x16951_x17533_s = Scalar("x16951_x17533")
    val bus_1884_v = Vector("bus_1884")
    val x17095_x17119_s = Scalar("x17095_x17119")
    val x17099_x17319_s = Scalar("x17099_x17319")
    val x16949_3_s = Scalar("x16949_3")
    val bus_1974_s = Scalar("bus_1974")
    val bus_2192_s = Scalar("bus_2192")
    val x17062_b18392_x17071_b18394_s = Scalar("x17062_b18392_x17071_b18394")
    val x16948_x17551_s = Scalar("x16948_x17551")
    val x16954_x17078_s = Scalar("x16954_x17078")
    val x17099_2_s = Scalar("x17099_2")
    val bus_2057_s = Scalar("bus_2057")
    val x17803_x17819_s = Scalar("x17803_x17819")
    val x16975_x16985_data_v = Vector("x16975_x16985_data")
    val x16954_0_s = Scalar("x16954_0")
    val biases1_dram_oc = OffChip("biases1_dram")
    val x17112_x17467_s = Scalar("x17112_x17467")
    val bus_1756_s = Scalar("bus_1756")
    val x16951_x17034_s = Scalar("x16951_x17034")
    val x17105_0_s = Scalar("x17105_0")
    val bus_2077_s = Scalar("bus_2077")
    val bus_2110_s = Scalar("bus_2110")
    val x16955_b18369_x16964_b18371_s = Scalar("x16955_b18369_x16964_b18371")
    val bus_2094_s = Scalar("bus_2094")
    val x16951_4_s = Scalar("x16951_4")
    val bus_2112_s = Scalar("bus_2112")
    val x16955_b18368_x16964_b18370_s = Scalar("x16955_b18368_x16964_b18370")
    val bus_1762_s = Scalar("bus_1762")
    val bus_1876_v = Vector("bus_1876")
    val x17062_b18393_x17071_b18395_s = Scalar("x17062_b18393_x17071_b18395")
    val x17100_1_s = Scalar("x17100_1")
    val x16948_3_s = Scalar("x16948_3")
    val x16953_0_s = Scalar("x16953_0")
    val bus_2107_s = Scalar("bus_2107")
    val bus_2103_s = Scalar("bus_2103")
    val x17063_x17073_data_v = Vector("x17063_x17073_data")
    val x16956_x16966_data_v = Vector("x16956_x16966_data")
    val x17999_b18490_x18012_b18492_s = Scalar("x17999_b18490_x18012_b18492")
    val bus_2207_s = Scalar("bus_2207")
    val bus_2004_s = Scalar("bus_2004")
    val bus_2209_s = Scalar("bus_2209")
    val bus_2216_s = Scalar("bus_2216")
    val bus_2106_s = Scalar("bus_2106")
    val x16948_x16971_s = Scalar("x16948_x16971")
    val bus_2081_s = Scalar("bus_2081")
    val bus_1845_v = Vector("bus_1845")
    val bus_2150_s = Scalar("bus_2150")
    val iters_argin = ArgIn("iters")
    val x17099_0_s = Scalar("x17099_0")
    val x17111_0_s = Scalar("x17111_0")
    val bus_2022_s = Scalar("bus_2022")
    val bus_1843_v = Vector("bus_1843")
    val x16953_1_s = Scalar("x16953_1")
    val bus_1942_s = Scalar("bus_1942")
    val bus_1968_s = Scalar("bus_1968")
    val bus_2079_s = Scalar("bus_2079")
    val bus_2210_s = Scalar("bus_2210")
    val x17926_x17942_v = Vector("x17926_x17942")
    val x17925_b18479_x17935_b18481_s = Scalar("x17925_b18479_x17935_b18481")
    val x17108_x17519_s = Scalar("x17108_x17519")
    val x17098_x17270_s = Scalar("x17098_x17270")
    val x17039_b18387_x17051_b18389_s = Scalar("x17039_b18387_x17051_b18389")
    val x17287_x17300_s = Scalar("x17287_x17300")
    val bus_2055_s = Scalar("bus_2055")
    val x17783_x17801_s = Scalar("x17783_x17801")
    val bus_1864_v = Vector("bus_1864")
    val bus_1732_s = Scalar("bus_1732")
    val x16950_x17009_s = Scalar("x16950_x17009")
    val x17098_x17283_s = Scalar("x17098_x17283")
    val x16948_2_s = Scalar("x16948_2")
    val x16950_0_s = Scalar("x16950_0")
    val x18029_b18497_x18042_b18499_s = Scalar("x18029_b18497_x18042_b18499")
    val x17999_b18491_x18012_b18493_s = Scalar("x17999_b18491_x18012_b18493")
    val bus_2092_s = Scalar("bus_2092")
    val x16949_2_s = Scalar("x16949_2")
    val x17691_x17761_s = Scalar("x17691_x17761")
    val x17974_x17990_v = Vector("x17974_x17990")
    val bus_1830_v = Vector("bus_1830")
    val x17690_x17760_s = Scalar("x17690_x17760")
    val x17107_1_s = Scalar("x17107_1")
    val x18030_x18050_v = Vector("x18030_x18050")
    val bus_1956_s = Scalar("bus_1956")
    val bus_2182_s = Scalar("bus_2182")
    val x17109_0_s = Scalar("x17109_0")
    val bus_2148_s = Scalar("bus_2148")
    val x17102_0_s = Scalar("x17102_0")
    val x16949_4_s = Scalar("x16949_4")
    val bus_1887_v = Vector("bus_1887")
    val bus_2169_s = Scalar("bus_2169")
    val x16950_3_s = Scalar("x16950_3")
    val x17094_x17118_s = Scalar("x17094_x17118")
    val x16974_b18373_x16983_b18375_s = Scalar("x16974_b18373_x16983_b18375")
    val x16953_x17795_s = Scalar("x16953_x17795")
    val x18057_b18503_x18067_b18505_s = Scalar("x18057_b18503_x18067_b18505")
    val bus_1734_s = Scalar("bus_1734")
    val bus_2171_s = Scalar("bus_2171")
    val bus_2219_s = Scalar("bus_2219")
    val bus_1978_s = Scalar("bus_1978")
    val x16949_x17781_s = Scalar("x16949_x17781")
    val biases3_dram_oc = OffChip("biases3_dram")
    val x17103_0_s = Scalar("x17103_0")
    val x16952_3_s = Scalar("x16952_3")
    val bus_2045_s = Scalar("bus_2045")
    val bus_2201_s = Scalar("bus_2201")
    val bus_1963_s = Scalar("bus_1963")
    val bus_1737_s = Scalar("bus_1737")
    val x17193_x17220_data_s = Scalar("x17193_x17220_data")
    val x17822_x17892_s = Scalar("x17822_x17892")
    val bus_2215_s = Scalar("bus_2215")
    val bus_2007_s = Scalar("bus_2007")
    val x17106_0_s = Scalar("x17106_0")
    val x17973_b18486_x17983_b18488_s = Scalar("x17973_b18486_x17983_b18488")
    val x16948_0_s = Scalar("x16948_0")
    val x16950_x17912_s = Scalar("x16950_x17912")
    val x17222_x17228_s = Scalar("x17222_x17228")
    val bus_2125_s = Scalar("bus_2125")
    val x17192_b18414_x17218_b18422_s = Scalar("x17192_b18414_x17218_b18422")
    val bus_2197_s = Scalar("bus_2197")
    val x17015_x17028_data_v = Vector("x17015_x17028_data")
    val bus_2115_s = Scalar("bus_2115")
    val x17100_0_s = Scalar("x17100_0")
    val x17223_x17230_s = Scalar("x17223_x17230")
    val x17101_0_s = Scalar("x17101_0")
    val x17182_x17189_s = Scalar("x17182_x17189")
    val bus_1862_v = Vector("bus_1862")
    val x16954_1_s = Scalar("x16954_1")
    val bus_1992_s = Scalar("bus_1992")
    val bus_2116_s = Scalar("bus_2116")
    val x17110_0_s = Scalar("x17110_0")
    val bus_2174_s = Scalar("bus_2174")
    val x16948_4_s = Scalar("x16948_4")
    val bus_1976_s = Scalar("bus_1976")
    val bus_2098_s = Scalar("bus_2098")
    val x17098_0_s = Scalar("x17098_0")
    val training_data_dram_oc = OffChip("training_data_dram")
    val bus_2060_s = Scalar("bus_2060")
    val bus_2153_s = Scalar("bus_2153")
    val bus_1728_s = Scalar("bus_1728")
    val bus_2006_s = Scalar("bus_2006")
    val bus_2043_s = Scalar("bus_2043")
    val x16949_x17682_s = Scalar("x16949_x17682")
    val bus_2070_s = Scalar("bus_2070")
    val x17949_b18483_x17959_b18485_s = Scalar("x17949_b18483_x17959_b18485")
    val x17106_x17414_s = Scalar("x17106_x17414")
    val x18081 = Sequential(name="x18081",parent=top) { implicit CU => 
      val x18081_unit = CounterChain(name = "x18081_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16948_dsp0 = MemoryPipeline(name="x16948_dsp0",parent="x18081") { implicit CU => 
      val x17650 = ScalarFIFO(size=1,name="x17650").wtPort(x16948_x17650_s)
      val x16971 = ScalarFIFO(size=1,name="x16971").wtPort(x16948_x16971_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(x16948_x17551_s)
      val x17938 = CounterChain.copy("x17943_0", "x17938")
      val x17644 = CounterChain.copy("x17651_0", "x17644")
      val x16948 = SRAM(size=64,name="x16948",banking = Strided(1)).wtPort(x16971.readPort).wtPort(x17551.readPort).wtPort(x17650.readPort).rdPort(x16948_0_s).rdAddr(x17938(0)).wtAddr(x17644(0))
    }
    val x16948_dsp1 = MemoryPipeline(name="x16948_dsp1",parent="x18081") { implicit CU => 
      val x17650 = ScalarFIFO(size=1,name="x17650").wtPort(x16948_x17650_s)
      val x16971 = ScalarFIFO(size=1,name="x16971").wtPort(x16948_x16971_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(x16948_x17551_s)
      val x17644 = CounterChain.copy("x17651_0", "x17644")
      val x16948 = SRAM(size=64,name="x16948",banking = Strided(1)).wtPort(x16971.readPort).wtPort(x17551.readPort).wtPort(x17650.readPort).rdPort(x16948_1_s).rdAddr(x17644(0)).wtAddr(x17644(0))
    }
    val x16948_dsp2 = MemoryPipeline(name="x16948_dsp2",parent="x18081") { implicit CU => 
      val x17650 = ScalarFIFO(size=1,name="x17650").wtPort(x16948_x17650_s)
      val x16971 = ScalarFIFO(size=1,name="x16971").wtPort(x16948_x16971_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(x16948_x17551_s)
      val x17543 = CounterChain.copy("x17558_0", "x17543")
      val x17644 = CounterChain.copy("x17651_0", "x17644")
      val x16948 = SRAM(size=64,name="x16948",banking = Strided(1)).wtPort(x16971.readPort).wtPort(x17551.readPort).wtPort(x17650.readPort).rdPort(x16948_2_s).rdAddr(x17543(0)).wtAddr(x17644(0))
    }
    val x16948_dsp3 = MemoryPipeline(name="x16948_dsp3",parent="x18081") { implicit CU => 
      val x17650 = ScalarFIFO(size=1,name="x17650").wtPort(x16948_x17650_s)
      val x16971 = ScalarFIFO(size=1,name="x16971").wtPort(x16948_x16971_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(x16948_x17551_s)
      val x17543 = CounterChain.copy("x17558_0", "x17543")
      val x17644 = CounterChain.copy("x17651_0", "x17644")
      val x16948 = SRAM(size=64,name="x16948",banking = Strided(1)).wtPort(x16971.readPort).wtPort(x17551.readPort).wtPort(x17650.readPort).rdPort(x16948_3_s).rdAddr(x17543(0)).wtAddr(x17644(0))
    }
    val x16948_dsp4 = MemoryPipeline(name="x16948_dsp4",parent="x18081") { implicit CU => 
      val x17650 = ScalarFIFO(size=1,name="x17650").wtPort(x16948_x17650_s)
      val x16971 = ScalarFIFO(size=1,name="x16971").wtPort(x16948_x16971_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(x16948_x17551_s)
      val x17644 = CounterChain.copy("x17651_0", "x17644")
      val x17250 = CounterChain.copy("x17272", "x17250")
      val x16948 = SRAM(size=64,name="x16948",banking = Strided(1)).wtPort(x16971.readPort).wtPort(x17551.readPort).wtPort(x17650.readPort).rdPort(x16948_4_s).rdAddr(x17250(0)).wtAddr(x17644(0))
    }
    val x16949_dsp0 = MemoryPipeline(name="x16949_dsp0",parent="x18081") { implicit CU => 
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(x16949_x17682_s)
      val x17781 = ScalarFIFO(size=1,name="x17781").wtPort(x16949_x17781_s)
      val x16990 = ScalarFIFO(size=1,name="x16990").wtPort(x16949_x16990_s)
      val x17775 = CounterChain.copy("x17782_0", "x17775")
      val x17962 = CounterChain.copy("x17967_0", "x17962")
      val x16949 = SRAM(size=64,name="x16949",banking = Strided(1)).wtPort(x16990.readPort).wtPort(x17682.readPort).wtPort(x17781.readPort).rdPort(x16949_0_s).rdAddr(x17962(0)).wtAddr(x17775(0))
    }
    val x16949_dsp1 = MemoryPipeline(name="x16949_dsp1",parent="x18081") { implicit CU => 
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(x16949_x17682_s)
      val x17781 = ScalarFIFO(size=1,name="x17781").wtPort(x16949_x17781_s)
      val x16990 = ScalarFIFO(size=1,name="x16990").wtPort(x16949_x16990_s)
      val x17775 = CounterChain.copy("x17782_0", "x17775")
      val x16949 = SRAM(size=64,name="x16949",banking = Strided(1)).wtPort(x16990.readPort).wtPort(x17682.readPort).wtPort(x17781.readPort).rdPort(x16949_1_s).rdAddr(x17775(0)).wtAddr(x17775(0))
    }
    val x16949_dsp2 = MemoryPipeline(name="x16949_dsp2",parent="x18081") { implicit CU => 
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(x16949_x17682_s)
      val x17781 = ScalarFIFO(size=1,name="x17781").wtPort(x16949_x17781_s)
      val x16990 = ScalarFIFO(size=1,name="x16990").wtPort(x16949_x16990_s)
      val x17674 = CounterChain.copy("x17689_0", "x17674")
      val x17775 = CounterChain.copy("x17782_0", "x17775")
      val x16949 = SRAM(size=64,name="x16949",banking = Strided(1)).wtPort(x16990.readPort).wtPort(x17682.readPort).wtPort(x17781.readPort).rdPort(x16949_2_s).rdAddr(x17674(0)).wtAddr(x17775(0))
    }
    val x16949_dsp3 = MemoryPipeline(name="x16949_dsp3",parent="x18081") { implicit CU => 
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(x16949_x17682_s)
      val x17781 = ScalarFIFO(size=1,name="x17781").wtPort(x16949_x17781_s)
      val x16990 = ScalarFIFO(size=1,name="x16990").wtPort(x16949_x16990_s)
      val x17674 = CounterChain.copy("x17689_0", "x17674")
      val x17775 = CounterChain.copy("x17782_0", "x17775")
      val x16949 = SRAM(size=64,name="x16949",banking = Strided(1)).wtPort(x16990.readPort).wtPort(x17682.readPort).wtPort(x17781.readPort).rdPort(x16949_3_s).rdAddr(x17674(0)).wtAddr(x17775(0))
    }
    val x16949_dsp4 = MemoryPipeline(name="x16949_dsp4",parent="x18081") { implicit CU => 
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(x16949_x17682_s)
      val x17781 = ScalarFIFO(size=1,name="x17781").wtPort(x16949_x17781_s)
      val x16990 = ScalarFIFO(size=1,name="x16990").wtPort(x16949_x16990_s)
      val x17286 = CounterChain.copy("x17308", "x17286")
      val x17775 = CounterChain.copy("x17782_0", "x17775")
      val x16949 = SRAM(size=64,name="x16949",banking = Strided(1)).wtPort(x16990.readPort).wtPort(x17682.readPort).wtPort(x17781.readPort).rdPort(x16949_4_s).rdAddr(x17286(0)).wtAddr(x17775(0))
    }
    val x16950_dsp0 = MemoryPipeline(name="x16950_dsp0",parent="x18081") { implicit CU => 
      val x17009 = ScalarFIFO(size=1,name="x17009").wtPort(x16950_x17009_s)
      val x17912 = ScalarFIFO(size=1,name="x17912").wtPort(x16950_x17912_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(x16950_x17813_s)
      val x17986 = CounterChain.copy("x17991_0", "x17986")
      val x17906 = CounterChain.copy("x17913_0", "x17906")
      val x16950 = SRAM(size=16,name="x16950",banking = Strided(1)).wtPort(x17009.readPort).wtPort(x17813.readPort).wtPort(x17912.readPort).rdPort(x16950_0_s).rdAddr(x17986(0)).wtAddr(x17906(0))
    }
    val x16950_dsp1 = MemoryPipeline(name="x16950_dsp1",parent="x18081") { implicit CU => 
      val x17009 = ScalarFIFO(size=1,name="x17009").wtPort(x16950_x17009_s)
      val x17912 = ScalarFIFO(size=1,name="x17912").wtPort(x16950_x17912_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(x16950_x17813_s)
      val x17906 = CounterChain.copy("x17913_0", "x17906")
      val x16950 = SRAM(size=16,name="x16950",banking = Strided(1)).wtPort(x17009.readPort).wtPort(x17813.readPort).wtPort(x17912.readPort).rdPort(x16950_1_s).rdAddr(x17906(0)).wtAddr(x17906(0))
    }
    val x16950_dsp2 = MemoryPipeline(name="x16950_dsp2",parent="x18081") { implicit CU => 
      val x17009 = ScalarFIFO(size=1,name="x17009").wtPort(x16950_x17009_s)
      val x17912 = ScalarFIFO(size=1,name="x17912").wtPort(x16950_x17912_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(x16950_x17813_s)
      val x17805 = CounterChain.copy("x17820_0", "x17805")
      val x17906 = CounterChain.copy("x17913_0", "x17906")
      val x16950 = SRAM(size=16,name="x16950",banking = Strided(1)).wtPort(x17009.readPort).wtPort(x17813.readPort).wtPort(x17912.readPort).rdPort(x16950_2_s).rdAddr(x17805(0)).wtAddr(x17906(0))
    }
    val x16950_dsp3 = MemoryPipeline(name="x16950_dsp3",parent="x18081") { implicit CU => 
      val x17009 = ScalarFIFO(size=1,name="x17009").wtPort(x16950_x17009_s)
      val x17912 = ScalarFIFO(size=1,name="x17912").wtPort(x16950_x17912_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(x16950_x17813_s)
      val x17805 = CounterChain.copy("x17820_0", "x17805")
      val x17906 = CounterChain.copy("x17913_0", "x17906")
      val x16950 = SRAM(size=16,name="x16950",banking = Strided(1)).wtPort(x17009.readPort).wtPort(x17813.readPort).wtPort(x17912.readPort).rdPort(x16950_3_s).rdAddr(x17805(0)).wtAddr(x17906(0))
    }
    val x16950_dsp4 = MemoryPipeline(name="x16950_dsp4",parent="x18081") { implicit CU => 
      val x17009 = ScalarFIFO(size=1,name="x17009").wtPort(x16950_x17009_s)
      val x17912 = ScalarFIFO(size=1,name="x17912").wtPort(x16950_x17912_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(x16950_x17813_s)
      val x17906 = CounterChain.copy("x17913_0", "x17906")
      val x17322 = CounterChain.copy("x17344", "x17322")
      val x16950 = SRAM(size=16,name="x16950",banking = Strided(1)).wtPort(x17009.readPort).wtPort(x17813.readPort).wtPort(x17912.readPort).rdPort(x16950_4_s).rdAddr(x17322(0)).wtAddr(x17906(0))
    }
    val x16951_dsp0 = MemoryPipeline(name="x16951_dsp0",parent="x18081") { implicit CU => 
      val b18494 = CU.temp(None)
      val b18450 = CU.temp(None)
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16951_x17641_s)
      val x17034 = ScalarFIFO(size=1,name="x17034").wtPort(x16951_x17034_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(x16951_x17533_s)
      val x18015 = CounterChain.copy("x18021_0", "x18015")
      val x17634 = CounterChain.copy("x17642_0", "x17634")
      val x17998 = CounterChain.copy("x18026", "x17998")
      val x16951 = SRAM(size=832,name="x16951",banking = Strided(1)).wtPort(x17034.readPort).wtPort(x17533.readPort).wtPort(x17641.readPort).rdPort(x16951_0_s)
      WAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18450))
      WAStage(operands=List(b18450, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.writeAddr))
      RAStage(operands=List(CU.ctr(x17998(0)), Const(64)), op=FixMul, results=List(b18494))
      RAStage(operands=List(b18494, CU.ctr(x18015(0))), op=FixAdd, results=List(x16951.readAddr))
    }
    val x16951_dsp1 = MemoryPipeline(name="x16951_dsp1",parent="x18081") { implicit CU => 
      val b18448 = CU.temp(None)
      val b18450 = CU.temp(None)
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16951_x17641_s)
      val x17034 = ScalarFIFO(size=1,name="x17034").wtPort(x16951_x17034_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(x16951_x17533_s)
      val x17634 = CounterChain.copy("x17642_0", "x17634")
      val x16951 = SRAM(size=832,name="x16951",banking = Strided(1)).wtPort(x17034.readPort).wtPort(x17533.readPort).wtPort(x17641.readPort).rdPort(x16951_1_s)
      WAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18450))
      WAStage(operands=List(b18450, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.writeAddr))
      RAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18448))
      RAStage(operands=List(b18448, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.readAddr))
    }
    val x16951_dsp2 = MemoryPipeline(name="x16951_dsp2",parent="x18081") { implicit CU => 
      val b18446 = CU.temp(None)
      val b18450 = CU.temp(None)
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16951_x17641_s)
      val x17034 = ScalarFIFO(size=1,name="x17034").wtPort(x16951_x17034_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(x16951_x17533_s)
      val x17634 = CounterChain.copy("x17642_0", "x17634")
      val x17524 = CounterChain.copy("x17540_0", "x17524")
      val x16951 = SRAM(size=832,name="x16951",banking = Strided(1)).wtPort(x17034.readPort).wtPort(x17533.readPort).wtPort(x17641.readPort).rdPort(x16951_2_s)
      WAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18450))
      WAStage(operands=List(b18450, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.writeAddr))
      RAStage(operands=List(CU.ctr(x17524(0)), Const(64)), op=FixMul, results=List(b18446))
      RAStage(operands=List(b18446, CU.ctr(x17524(1))), op=FixAdd, results=List(x16951.readAddr))
    }
    val x16951_dsp3 = MemoryPipeline(name="x16951_dsp3",parent="x18081") { implicit CU => 
      val b18450 = CU.temp(None)
      val b18440 = CU.temp(None)
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16951_x17641_s)
      val x17034 = ScalarFIFO(size=1,name="x17034").wtPort(x16951_x17034_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(x16951_x17533_s)
      val x17634 = CounterChain.copy("x17642_0", "x17634")
      val x17524 = CounterChain.copy("x17540_0", "x17524")
      val x16951 = SRAM(size=832,name="x16951",banking = Strided(1)).wtPort(x17034.readPort).wtPort(x17533.readPort).wtPort(x17641.readPort).rdPort(x16951_3_s)
      WAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18450))
      WAStage(operands=List(b18450, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.writeAddr))
      RAStage(operands=List(CU.ctr(x17524(0)), Const(64)), op=FixMul, results=List(b18440))
      RAStage(operands=List(b18440, CU.ctr(x17524(1))), op=FixAdd, results=List(x16951.readAddr))
    }
    val x16951_dsp4 = MemoryPipeline(name="x16951_dsp4",parent="x18081") { implicit CU => 
      val b18450 = CU.temp(None)
      val b18424 = CU.temp(None)
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16951_x17641_s)
      val x17034 = ScalarFIFO(size=1,name="x17034").wtPort(x16951_x17034_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(x16951_x17533_s)
      val x17634 = CounterChain.copy("x17642_0", "x17634")
      val x17253 = CounterChain.copy("x17265_0", "x17253")
      val x17250 = CounterChain.copy("x17272", "x17250")
      val x16951 = SRAM(size=832,name="x16951",banking = Strided(1)).wtPort(x17034.readPort).wtPort(x17533.readPort).wtPort(x17641.readPort).rdPort(x16951_4_s)
      WAStage(operands=List(CU.ctr(x17634(0)), Const(64)), op=FixMul, results=List(b18450))
      WAStage(operands=List(b18450, CU.ctr(x17634(1))), op=FixAdd, results=List(x16951.writeAddr))
      RAStage(operands=List(CU.ctr(x17253(0)), Const(64)), op=FixMul, results=List(b18424))
      RAStage(operands=List(b18424, CU.ctr(x17250(0))), op=FixAdd, results=List(x16951.readAddr))
    }
    val x16952_dsp0 = MemoryPipeline(name="x16952_dsp0",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18500 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x18028 = CounterChain.copy("x18056", "x18028")
      val x18045 = CounterChain.copy("x18051_0", "x18045")
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_0_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x18028(0)), Const(64)), op=FixMul, results=List(b18500))
      RAStage(operands=List(b18500, CU.ctr(x18045(0))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16952_dsp1 = MemoryPipeline(name="x16952_dsp1",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18460 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_1_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18460))
      RAStage(operands=List(b18460, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16952_dsp2 = MemoryPipeline(name="x16952_dsp2",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18458 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x17655 = CounterChain.copy("x17671_0", "x17655")
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_2_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x17655(0)), Const(64)), op=FixMul, results=List(b18458))
      RAStage(operands=List(b18458, CU.ctr(x17655(1))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16952_dsp3 = MemoryPipeline(name="x16952_dsp3",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18452 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x17655 = CounterChain.copy("x17671_0", "x17655")
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_3_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x17655(0)), Const(64)), op=FixMul, results=List(b18452))
      RAStage(operands=List(b18452, CU.ctr(x17655(1))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16952_dsp4 = MemoryPipeline(name="x16952_dsp4",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18436 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x17490 = CounterChain.copy("x17502_0", "x17490")
      val x17482 = CounterChain.copy("x17509", "x17482")
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_4_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x17482(0)), Const(64)), op=FixMul, results=List(b18436))
      RAStage(operands=List(b18436, CU.ctr(x17490(0))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16952_dsp5 = MemoryPipeline(name="x16952_dsp5",parent="x18081") { implicit CU => 
      val b18462 = CU.temp(None)
      val b18426 = CU.temp(None)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(x16952_x17664_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16952_x17772_s)
      val x17059 = ScalarFIFO(size=1,name="x17059").wtPort(x16952_x17059_s)
      val x17286 = CounterChain.copy("x17308", "x17286")
      val x17765 = CounterChain.copy("x17773_0", "x17765")
      val x17289 = CounterChain.copy("x17301_0", "x17289")
      val x16952 = SRAM(size=4096,name="x16952",banking = Strided(1)).wtPort(x17059.readPort).wtPort(x17664.readPort).wtPort(x17772.readPort).rdPort(x16952_5_s)
      WAStage(operands=List(CU.ctr(x17765(0)), Const(64)), op=FixMul, results=List(b18462))
      WAStage(operands=List(b18462, CU.ctr(x17765(1))), op=FixAdd, results=List(x16952.writeAddr))
      RAStage(operands=List(CU.ctr(x17289(0)), Const(64)), op=FixMul, results=List(b18426))
      RAStage(operands=List(b18426, CU.ctr(x17286(0))), op=FixAdd, results=List(x16952.readAddr))
    }
    val x16953_dsp0 = MemoryPipeline(name="x16953_dsp0",parent="x18081") { implicit CU => 
      val b18474 = CU.temp(None)
      val b18476 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x17917 = CounterChain.copy("x17924_0", "x17917")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_0_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17917(0)), Const(3)), op=FixMul, results=List(b18476))
      RAStage(operands=List(b18476, CU.ctr(x17917(1))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16953_dsp1 = MemoryPipeline(name="x16953_dsp1",parent="x18081") { implicit CU => 
      val b18472 = CU.temp(None)
      val b18474 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_1_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18472))
      RAStage(operands=List(b18472, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16953_dsp2 = MemoryPipeline(name="x16953_dsp2",parent="x18081") { implicit CU => 
      val b18474 = CU.temp(None)
      val b18470 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17786 = CounterChain.copy("x17802_0", "x17786")
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_2_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17786(0)), Const(3)), op=FixMul, results=List(b18470))
      RAStage(operands=List(b18470, CU.ctr(x17786(1))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16953_dsp3 = MemoryPipeline(name="x16953_dsp3",parent="x18081") { implicit CU => 
      val b18464 = CU.temp(None)
      val b18474 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17786 = CounterChain.copy("x17802_0", "x17786")
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_3_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17786(0)), Const(3)), op=FixMul, results=List(b18464))
      RAStage(operands=List(b18464, CU.ctr(x17786(1))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16953_dsp4 = MemoryPipeline(name="x16953_dsp4",parent="x18081") { implicit CU => 
      val b18474 = CU.temp(None)
      val b18432 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x17442 = CounterChain.copy("x17469", "x17442")
      val x17450 = CounterChain.copy("x17462_0", "x17450")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_4_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17442(0)), Const(3)), op=FixMul, results=List(b18432))
      RAStage(operands=List(b18432, CU.ctr(x17450(0))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16953_dsp5 = MemoryPipeline(name="x16953_dsp5",parent="x18081") { implicit CU => 
      val b18428 = CU.temp(None)
      val b18474 = CU.temp(None)
      val x17903 = ScalarFIFO(size=1,name="x17903").wtPort(x16953_x17903_s)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(x16953_x17795_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(x16953_x17089_s)
      val x17896 = CounterChain.copy("x17904_0", "x17896")
      val x17325 = CounterChain.copy("x17337_0", "x17325")
      val x17322 = CounterChain.copy("x17344", "x17322")
      val x16953 = SRAM(size=192,name="x16953",banking = Strided(1)).wtPort(x17089.readPort).wtPort(x17795.readPort).wtPort(x17903.readPort).rdPort(x16953_5_s)
      WAStage(operands=List(CU.ctr(x17896(0)), Const(3)), op=FixMul, results=List(b18474))
      WAStage(operands=List(b18474, CU.ctr(x17896(1))), op=FixAdd, results=List(x16953.writeAddr))
      RAStage(operands=List(CU.ctr(x17325(0)), Const(3)), op=FixMul, results=List(b18428))
      RAStage(operands=List(b18428, CU.ctr(x17322(0))), op=FixAdd, results=List(x16953.readAddr))
    }
    val x16954_dsp0 = MemoryPipeline(name="x16954_dsp0",parent="x18081") { implicit CU => 
      val x17918 = CU.temp(None)
      val x17078 = ScalarFIFO(size=1,name="x17078").wtPort(x16954_x17078_s)
      val x17923 = ScalarFIFO(size=1,name="x17923").wtPort(x16954_x17923_s)
      val x17917 = CounterChain.copy("x17924_0", "x17917")
      val x18070 = CounterChain.copy("x18075_0", "x18070")
      val x16954 = SRAM(size=192,name="x16954",banking = Strided(1)).wtPort(x17078.readPort).wtPort(x17923.readPort).rdPort(x16954_0_s).rdAddr(x18070(0))
      WAStage(operands=List(CU.ctr(x17917(0)), Const(3)), op=FixMul, results=List(x17918))
      WAStage(operands=List(x17918, CU.ctr(x17917(1))), op=FixAdd, results=List(x16954.writeAddr))
    }
    val x16954_dsp1 = MemoryPipeline(name="x16954_dsp1",parent="x18081") { implicit CU => 
      val x17084 = CU.temp(None)
      val x17918 = CU.temp(None)
      val x17078 = ScalarFIFO(size=1,name="x17078").wtPort(x16954_x17078_s)
      val x17923 = ScalarFIFO(size=1,name="x17923").wtPort(x16954_x17923_s)
      val x17083 = CounterChain.copy("x17090_0", "x17083")
      val x17917 = CounterChain.copy("x17924_0", "x17917")
      val x16954 = SRAM(size=192,name="x16954",banking = Strided(1)).wtPort(x17078.readPort).wtPort(x17923.readPort).rdPort(x16954_1_s)
      WAStage(operands=List(CU.ctr(x17917(0)), Const(3)), op=FixMul, results=List(x17918))
      WAStage(operands=List(x17918, CU.ctr(x17917(1))), op=FixAdd, results=List(x16954.writeAddr))
      RAStage(operands=List(CU.ctr(x17083(0)), Const(3)), op=FixMul, results=List(x17084))
      RAStage(operands=List(x17084, CU.ctr(x17083(1))), op=FixAdd, results=List(x16954.readAddr))
    }
    val x16973 = StreamController(name="x16973",parent=x18081) { implicit CU => 
      val x16973_unit = CounterChain(name = "x16973_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16965_0 = Pipeline(name="x16965_0",parent=x16973) { implicit CU => 
      val x16958 = CU.temp(None)
      val x16960 = ScalarBuffer(name="x16960").wtPort(biases1_dram_da)
      val x16965_unit = CounterChain(name = "x16965_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16958))
      Stage(operands=List(x16958, CU.load(x16960)), op=FixAdd, results=List(CU.scalarOut(x16955_b18368_x16964_b18370_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16955_b18369_x16964_b18371_s)))
    }
    val x16966 = MemoryController(name="x16966",parent=x16973,offchip=biases1_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16955_b18368 = ScalarFIFO(size=1,name="offset").wtPort(x16955_b18368_x16964_b18370_s)
      val x16955_b18369 = ScalarFIFO(size=1,name="size").wtPort(x16955_b18369_x16964_b18371_s)
      CU.newVout("data", x16956_x16966_data_v)
    }
    val x16972_0 = Pipeline(name="x16972_0",parent=x16973) { implicit CU => 
      val x16956 = VectorFIFO(size=1,name="x16956").wtPort(x16956_x16966_data_v)
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16968 = CounterChain(name = "x16968", ctr1).iter(4)
      Stage(operands=List(CU.load(x16956)), op=Bypass, results=List(CU.scalarOut(x16948_x16971_s)))
    }
    val x16992 = StreamController(name="x16992",parent=x18081) { implicit CU => 
      val x16992_unit = CounterChain(name = "x16992_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16984_0 = Pipeline(name="x16984_0",parent=x16992) { implicit CU => 
      val x16977 = CU.temp(None)
      val x16979 = ScalarBuffer(name="x16979").wtPort(biases2_dram_da)
      val x16984_unit = CounterChain(name = "x16984_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16977))
      Stage(operands=List(x16977, CU.load(x16979)), op=FixAdd, results=List(CU.scalarOut(x16974_b18372_x16983_b18374_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16974_b18373_x16983_b18375_s)))
    }
    val x16985 = MemoryController(name="x16985",parent=x16992,offchip=biases2_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16974_b18373 = ScalarFIFO(size=1,name="size").wtPort(x16974_b18373_x16983_b18375_s)
      val x16974_b18372 = ScalarFIFO(size=1,name="offset").wtPort(x16974_b18372_x16983_b18374_s)
      CU.newVout("data", x16975_x16985_data_v)
    }
    val x16991_0 = Pipeline(name="x16991_0",parent=x16992) { implicit CU => 
      val x16975 = VectorFIFO(size=1,name="x16975").wtPort(x16975_x16985_data_v)
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16987 = CounterChain(name = "x16987", ctr2).iter(4)
      Stage(operands=List(CU.load(x16975)), op=Bypass, results=List(CU.scalarOut(x16949_x16990_s)))
    }
    val x17011 = StreamController(name="x17011",parent=x18081) { implicit CU => 
      val x17011_unit = CounterChain(name = "x17011_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17003_0 = Pipeline(name="x17003_0",parent=x17011) { implicit CU => 
      val x16996 = CU.temp(None)
      val x16998 = ScalarBuffer(name="x16998").wtPort(biases3_dram_da)
      val x17003_unit = CounterChain(name = "x17003_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16996))
      Stage(operands=List(x16996, CU.load(x16998)), op=FixAdd, results=List(CU.scalarOut(x16993_b18376_x17002_b18378_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x16993_b18377_x17002_b18379_s)))
    }
    val x17004 = MemoryController(name="x17004",parent=x17011,offchip=biases3_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16993_b18377 = ScalarFIFO(size=1,name="size").wtPort(x16993_b18377_x17002_b18379_s)
      val x16993_b18376 = ScalarFIFO(size=1,name="offset").wtPort(x16993_b18376_x17002_b18378_s)
      CU.newVout("data", x16994_x17004_data_v)
    }
    val x17010_0 = Pipeline(name="x17010_0",parent=x17011) { implicit CU => 
      val x16994 = VectorFIFO(size=1,name="x16994").wtPort(x16994_x17004_data_v)
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x17006 = CounterChain(name = "x17006", ctr3).iter(1)
      Stage(operands=List(CU.load(x16994)), op=Bypass, results=List(CU.scalarOut(x16950_x17009_s)))
    }
    val x17036 = StreamController(name="x17036",parent=x18081) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val x17013 = CounterChain(name = "x17013", ctr4).iter(13)
    }
    val x17027_0 = Pipeline(name="x17027_0",parent=x17036) { implicit CU => 
      val x17017 = CU.temp(None)
      val x17019 = CU.temp(None)
      val x17020 = CU.temp(None)
      val x17022 = ScalarBuffer(name="x17022").wtPort(weights1_dram_da)
      val x17013 = CounterChain.copy("x17036", "x17013")
      val x17027_unit = CounterChain(name = "x17027_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17013(0)), Const(6)), op=FixSla, results=List(x17017))
      Stage(operands=List(x17017, Const(0)), op=FixAdd, results=List(x17019))
      Stage(operands=List(x17019, Const(2)), op=FixSla, results=List(x17020))
      Stage(operands=List(x17020, CU.load(x17022)), op=FixAdd, results=List(CU.scalarOut(x17014_b18380_x17026_b18382_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17014_b18381_x17026_b18383_s)))
    }
    val x17028 = MemoryController(name="x17028",parent=x17036,offchip=weights1_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17014_b18381 = ScalarFIFO(size=1,name="size").wtPort(x17014_b18381_x17026_b18383_s)
      val x17014_b18380 = ScalarFIFO(size=1,name="offset").wtPort(x17014_b18380_x17026_b18382_s)
      CU.newVout("data", x17015_x17028_data_v)
    }
    val x17035_0 = Pipeline(name="x17035_0",parent=x17036) { implicit CU => 
      val x17015 = VectorFIFO(size=1,name="x17015").wtPort(x17015_x17028_data_v)
      val ctr5 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17030 = CounterChain(name = "x17030", ctr5).iter(4)
      Stage(operands=List(CU.load(x17015)), op=Bypass, results=List(CU.scalarOut(x16951_x17034_s)))
    }
    val x17061 = StreamController(name="x17061",parent=x18081) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17038 = CounterChain(name = "x17038", ctr6).iter(64)
    }
    val x17052_0 = Pipeline(name="x17052_0",parent=x17061) { implicit CU => 
      val x17042 = CU.temp(None)
      val x17045 = CU.temp(None)
      val x17044 = CU.temp(None)
      val x17047 = ScalarBuffer(name="x17047").wtPort(weights2_dram_da)
      val x17038 = CounterChain.copy("x17061", "x17038")
      val x17052_unit = CounterChain(name = "x17052_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17038(0)), Const(6)), op=FixSla, results=List(x17042))
      Stage(operands=List(x17042, Const(0)), op=FixAdd, results=List(x17044))
      Stage(operands=List(x17044, Const(2)), op=FixSla, results=List(x17045))
      Stage(operands=List(x17045, CU.load(x17047)), op=FixAdd, results=List(CU.scalarOut(x17039_b18386_x17051_b18388_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17039_b18387_x17051_b18389_s)))
    }
    val x17053 = MemoryController(name="x17053",parent=x17061,offchip=weights2_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17039_b18386 = ScalarFIFO(size=1,name="offset").wtPort(x17039_b18386_x17051_b18388_s)
      val x17039_b18387 = ScalarFIFO(size=1,name="size").wtPort(x17039_b18387_x17051_b18389_s)
      CU.newVout("data", x17040_x17053_data_v)
    }
    val x17060_0 = Pipeline(name="x17060_0",parent=x17061) { implicit CU => 
      val x17040 = VectorFIFO(size=1,name="x17040").wtPort(x17040_x17053_data_v)
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17055 = CounterChain(name = "x17055", ctr7).iter(4)
      Stage(operands=List(CU.load(x17040)), op=Bypass, results=List(CU.scalarOut(x16952_x17059_s)))
    }
    val x17080 = StreamController(name="x17080",parent=x18081) { implicit CU => 
      val x17080_unit = CounterChain(name = "x17080_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17072_0 = Pipeline(name="x17072_0",parent=x17080) { implicit CU => 
      val x17065 = CU.temp(None)
      val x17067 = ScalarBuffer(name="x17067").wtPort(weights3_dram_da)
      val x17072_unit = CounterChain(name = "x17072_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17065))
      Stage(operands=List(x17065, CU.load(x17067)), op=FixAdd, results=List(CU.scalarOut(x17062_b18392_x17071_b18394_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x17062_b18393_x17071_b18395_s)))
    }
    val x17073 = MemoryController(name="x17073",parent=x17080,offchip=weights3_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17062_b18393 = ScalarFIFO(size=1,name="size").wtPort(x17062_b18393_x17071_b18395_s)
      val x17062_b18392 = ScalarFIFO(size=1,name="offset").wtPort(x17062_b18392_x17071_b18394_s)
      CU.newVout("data", x17063_x17073_data_v)
    }
    val x17079_0 = Pipeline(name="x17079_0",parent=x17080) { implicit CU => 
      val x17063 = VectorFIFO(size=1,name="x17063").wtPort(x17063_x17073_data_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x17075 = CounterChain(name = "x17075", ctr8).iter(12)
      Stage(operands=List(CU.load(x17063)), op=Bypass, results=List(CU.scalarOut(x16954_x17078_s)))
    }
    val x17090_0 = Pipeline(name="x17090_0",parent=x18081) { implicit CU => 
      val x17087 = ScalarFIFO(size=1,name="x17087").wtPort(x16954_1_s)
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17083 = CounterChain(name = "x17083", ctr9, ctr10).iter(192)
      Stage(operands=List(CU.ctr(x17083(0)), Const(3)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x17087)), op=Bypass, results=List(CU.scalarOut(x16953_x17089_s)))
    }
    val x17914 = Sequential(name="x17914",parent=x18081) { implicit CU => 
      val x16851 = ScalarBuffer(name="x16851").wtPort(iters_argin)
      val ctr11 = Counter(min=Const(0), max=x16851.readPort, step=Const(1), par=1) // Counter
      val x17093 = CounterChain(name = "x17093", ctr11).iter(1)
    }
    val x17098_dsp0 = MemoryPipeline(name="x17098_dsp0",parent="x17914") { implicit CU => 
      val x17270 = ScalarFIFO(size=1,name="x17270").wtPort(x17098_x17270_s)
      val x17283 = ScalarFIFO(size=1,name="x17283").wtPort(x17098_x17283_s)
      val x17274 = CounterChain.copy("x17284_0", "x17274")
      val x17472 = CounterChain.copy("x17480_0", "x17472")
      val x17098 = SRAM(size=64,name="x17098",banking = Strided(1)).wtPort(x17270.readPort).wtPort(x17283.readPort).rdPort(x17098_0_s).rdAddr(x17472(0)).wtAddr(x17274(0))
    }
    val x17098_dsp1 = MemoryPipeline(name="x17098_dsp1",parent="x17914") { implicit CU => 
      val x17270 = ScalarFIFO(size=1,name="x17270").wtPort(x17098_x17270_s)
      val x17283 = ScalarFIFO(size=1,name="x17283").wtPort(x17098_x17283_s)
      val x17274 = CounterChain.copy("x17284_0", "x17274")
      val x17289 = CounterChain.copy("x17301_0", "x17289")
      val x17098 = SRAM(size=64,name="x17098",banking = Strided(1)).wtPort(x17270.readPort).wtPort(x17283.readPort).rdPort(x17098_1_s).rdAddr(x17289(0)).wtAddr(x17274(0))
    }
    val x17098_dsp2 = MemoryPipeline(name="x17098_dsp2",parent="x17914") { implicit CU => 
      val x17270 = ScalarFIFO(size=1,name="x17270").wtPort(x17098_x17270_s)
      val x17283 = ScalarFIFO(size=1,name="x17283").wtPort(x17098_x17283_s)
      val x17274 = CounterChain.copy("x17284_0", "x17274")
      val x17098 = SRAM(size=64,name="x17098",banking = Strided(1)).wtPort(x17270.readPort).wtPort(x17283.readPort).rdPort(x17098_2_s).rdAddr(x17274(0)).wtAddr(x17274(0))
    }
    val x17099_dsp0 = MemoryPipeline(name="x17099_dsp0",parent="x17914") { implicit CU => 
      val x17306 = ScalarFIFO(size=1,name="x17306").wtPort(x17099_x17306_s)
      val x17319 = ScalarFIFO(size=1,name="x17319").wtPort(x17099_x17319_s)
      val x17310 = CounterChain.copy("x17320_0", "x17310")
      val x17432 = CounterChain.copy("x17440_0", "x17432")
      val x17099 = SRAM(size=64,name="x17099",banking = Strided(1)).wtPort(x17306.readPort).wtPort(x17319.readPort).rdPort(x17099_0_s).rdAddr(x17432(0)).wtAddr(x17310(0))
    }
    val x17099_dsp1 = MemoryPipeline(name="x17099_dsp1",parent="x17914") { implicit CU => 
      val x17306 = ScalarFIFO(size=1,name="x17306").wtPort(x17099_x17306_s)
      val x17319 = ScalarFIFO(size=1,name="x17319").wtPort(x17099_x17319_s)
      val x17325 = CounterChain.copy("x17337_0", "x17325")
      val x17310 = CounterChain.copy("x17320_0", "x17310")
      val x17099 = SRAM(size=64,name="x17099",banking = Strided(1)).wtPort(x17306.readPort).wtPort(x17319.readPort).rdPort(x17099_1_s).rdAddr(x17325(0)).wtAddr(x17310(0))
    }
    val x17099_dsp2 = MemoryPipeline(name="x17099_dsp2",parent="x17914") { implicit CU => 
      val x17306 = ScalarFIFO(size=1,name="x17306").wtPort(x17099_x17306_s)
      val x17319 = ScalarFIFO(size=1,name="x17319").wtPort(x17099_x17319_s)
      val x17310 = CounterChain.copy("x17320_0", "x17310")
      val x17099 = SRAM(size=64,name="x17099",banking = Strided(1)).wtPort(x17306.readPort).wtPort(x17319.readPort).rdPort(x17099_2_s).rdAddr(x17310(0)).wtAddr(x17310(0))
    }
    val x17100_dsp0 = MemoryPipeline(name="x17100_dsp0",parent="x17914") { implicit CU => 
      val x17355 = ScalarFIFO(size=1,name="x17355").wtPort(x17100_x17355_s)
      val x17342 = ScalarFIFO(size=1,name="x17342").wtPort(x17100_x17342_s)
      val x17346 = CounterChain.copy("x17356_0", "x17346")
      val x17388 = CounterChain.copy("x17415", "x17388")
      val x17100 = SRAM(size=3,name="x17100",banking = Strided(1)).wtPort(x17342.readPort).wtPort(x17355.readPort).rdPort(x17100_0_s).rdAddr(x17388(0)).wtAddr(x17346(0))
    }
    val x17100_dsp1 = MemoryPipeline(name="x17100_dsp1",parent="x17914") { implicit CU => 
      val x17355 = ScalarFIFO(size=1,name="x17355").wtPort(x17100_x17355_s)
      val x17342 = ScalarFIFO(size=1,name="x17342").wtPort(x17100_x17342_s)
      val x17346 = CounterChain.copy("x17356_0", "x17346")
      val x17359 = CounterChain.copy("x17386", "x17359")
      val x17100 = SRAM(size=3,name="x17100",banking = Strided(1)).wtPort(x17342.readPort).wtPort(x17355.readPort).rdPort(x17100_1_s).rdAddr(x17359(0)).wtAddr(x17346(0))
    }
    val x17100_dsp2 = MemoryPipeline(name="x17100_dsp2",parent="x17914") { implicit CU => 
      val x17355 = ScalarFIFO(size=1,name="x17355").wtPort(x17100_x17355_s)
      val x17342 = ScalarFIFO(size=1,name="x17342").wtPort(x17100_x17342_s)
      val x17346 = CounterChain.copy("x17356_0", "x17346")
      val x17100 = SRAM(size=3,name="x17100",banking = Strided(1)).wtPort(x17342.readPort).wtPort(x17355.readPort).rdPort(x17100_2_s).rdAddr(x17346(0)).wtAddr(x17346(0))
    }
    val x17101_dsp0 = MemoryPipeline(name="x17101_dsp0",parent="x17914") { implicit CU => 
      val x17280 = ScalarFIFO(size=1,name="x17280").wtPort(x17101_x17280_s)
      val x17274 = CounterChain.copy("x17284_0", "x17274")
      val x17482 = CounterChain.copy("x17509", "x17482")
      val x17101 = SRAM(size=64,name="x17101",banking = Strided(1)).wtPort(x17280.readPort).rdPort(x17101_0_s).rdAddr(x17482(0)).wtAddr(x17274(0))
    }
    val x17102_dsp0 = MemoryPipeline(name="x17102_dsp0",parent="x17914") { implicit CU => 
      val x17316 = ScalarFIFO(size=1,name="x17316").wtPort(x17102_x17316_s)
      val x17310 = CounterChain.copy("x17320_0", "x17310")
      val x17442 = CounterChain.copy("x17469", "x17442")
      val x17102 = SRAM(size=64,name="x17102",banking = Strided(1)).wtPort(x17316.readPort).rdPort(x17102_0_s).rdAddr(x17442(0)).wtAddr(x17310(0))
    }
    val x17103_dsp0 = MemoryPipeline(name="x17103_dsp0",parent="x17914") { implicit CU => 
      val x17352 = ScalarFIFO(size=1,name="x17352").wtPort(x17103_x17352_s)
      val x17346 = CounterChain.copy("x17356_0", "x17346")
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17103 = SRAM(size=3,name="x17103",banking = Strided(1)).wtPort(x17352.readPort).rdPort(x17103_0_s).rdAddr(x17417(0)).wtAddr(x17346(0))
    }
    val x17104_dsp0 = MemoryPipeline(name="x17104_dsp0",parent="x17914") { implicit CU => 
      val x17177 = ScalarFIFO(size=1,name="x17177").wtPort(x17104_x17177_s)
      val x17153 = ScalarBuffer(name="x17153").wtPort(x17153_x17158_s)
      val x17166 = CounterChain.copy("x17178_0", "x17166")
      val x17512 = CounterChain.copy("x17520_0", "x17512")
      val x17104 = SRAM(size=18,name="x17104",banking = Strided(1)).wtPort(x17177.readPort).rdPort(x17104_0_s).rdAddr(x17512(0))
      WAStage(operands=List(CU.ctr(x17166(0)), CU.load(x17153)), op=FixSub, results=List(x17104.writeAddr))
    }
    val x17104_dsp1 = MemoryPipeline(name="x17104_dsp1",parent="x17914") { implicit CU => 
      val x17177 = ScalarFIFO(size=1,name="x17177").wtPort(x17104_x17177_s)
      val x17153 = ScalarBuffer(name="x17153").wtPort(x17153_x17158_s)
      val x17166 = CounterChain.copy("x17178_0", "x17166")
      val x17253 = CounterChain.copy("x17265_0", "x17253")
      val x17104 = SRAM(size=18,name="x17104",banking = Strided(1)).wtPort(x17177.readPort).rdPort(x17104_1_s).rdAddr(x17253(0))
      WAStage(operands=List(CU.ctr(x17166(0)), CU.load(x17153)), op=FixSub, results=List(x17104.writeAddr))
    }
    val x17105_dsp0 = MemoryPipeline(name="x17105_dsp0",parent="x17914") { implicit CU => 
      val x17245 = ScalarFIFO(size=1,name="x17245").wtPort(x17105_x17245_s)
      val x17221 = ScalarBuffer(name="x17221").wtPort(x17221_x17226_s)
      val x17234 = CounterChain.copy("x17246_0", "x17234")
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17105 = SRAM(size=16,name="x17105",banking = Strided(1)).wtPort(x17245.readPort).rdPort(x17105_0_s).rdAddr(x17417(0))
      WAStage(operands=List(CU.ctr(x17234(0)), CU.load(x17221)), op=FixSub, results=List(x17105.writeAddr))
    }
    val x17106_dsp0 = MemoryPipeline(name="x17106_dsp0",parent="x17914") { implicit CU => 
      val x17414 = ScalarFIFO(size=1,name="x17414").wtPort(x17106_x17414_s)
      val x17388 = CounterChain.copy("x17415", "x17388")
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17106 = SRAM(size=3,name="x17106",banking = Strided(1)).wtPort(x17414.readPort).rdPort(x17106_0_s).rdAddr(x17417(0)).wtAddr(x17388(0))
    }
    val x17107_dsp0 = MemoryPipeline(name="x17107_dsp0",parent="x17914") { implicit CU => 
      val x17428 = ScalarFIFO(size=1,name="x17428").wtPort(x17107_x17428_s)
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17805 = CounterChain.copy("x17820_0", "x17805")
      val x17107 = SRAM(size=3,name="x17107",banking = Strided(1)).wtPort(x17428.readPort).rdPort(x17107_0_s).rdAddr(x17805(0)).wtAddr(x17417(0))
    }
    val x17107_dsp1 = MemoryPipeline(name="x17107_dsp1",parent="x17914") { implicit CU => 
      val x17428 = ScalarFIFO(size=1,name="x17428").wtPort(x17107_x17428_s)
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17450 = CounterChain.copy("x17462_0", "x17450")
      val x17107 = SRAM(size=3,name="x17107",banking = Strided(1)).wtPort(x17428.readPort).rdPort(x17107_1_s).rdAddr(x17450(0)).wtAddr(x17417(0))
    }
    val x17107_dsp2 = MemoryPipeline(name="x17107_dsp2",parent="x17914") { implicit CU => 
      val x17428 = ScalarFIFO(size=1,name="x17428").wtPort(x17107_x17428_s)
      val x17417 = CounterChain.copy("x17429_0", "x17417")
      val x17432 = CounterChain.copy("x17440_0", "x17432")
      val x17107 = SRAM(size=3,name="x17107",banking = Strided(1)).wtPort(x17428.readPort).rdPort(x17107_2_s).rdAddr(x17432(1)).wtAddr(x17417(0))
    }
    val x17108_dsp0 = MemoryPipeline(name="x17108_dsp0",parent="x17914") { implicit CU => 
      val b18438 = CU.temp(None)
      val b18442 = CU.temp(None)
      val x17519 = ScalarFIFO(size=1,name="x17519").wtPort(x17108_x17519_s)
      val x17512 = CounterChain.copy("x17520_0", "x17512")
      val x17524 = CounterChain.copy("x17540_0", "x17524")
      val x17108 = SRAM(size=832,name="x17108",banking = Strided(1)).wtPort(x17519.readPort).rdPort(x17108_0_s)
      WAStage(operands=List(CU.ctr(x17512(0)), Const(64)), op=FixMul, results=List(b18438))
      WAStage(operands=List(b18438, CU.ctr(x17512(1))), op=FixAdd, results=List(x17108.writeAddr))
      RAStage(operands=List(CU.ctr(x17524(0)), Const(64)), op=FixMul, results=List(b18442))
      RAStage(operands=List(b18442, CU.ctr(x17524(1))), op=FixAdd, results=List(x17108.readAddr))
    }
    val x17109_dsp0 = MemoryPipeline(name="x17109_dsp0",parent="x17914") { implicit CU => 
      val b18434 = CU.temp(None)
      val b18454 = CU.temp(None)
      val x17479 = ScalarFIFO(size=1,name="x17479").wtPort(x17109_x17479_s)
      val x17472 = CounterChain.copy("x17480_0", "x17472")
      val x17655 = CounterChain.copy("x17671_0", "x17655")
      val x17109 = SRAM(size=4096,name="x17109",banking = Strided(1)).wtPort(x17479.readPort).rdPort(x17109_0_s)
      WAStage(operands=List(CU.ctr(x17472(0)), Const(64)), op=FixMul, results=List(b18434))
      WAStage(operands=List(b18434, CU.ctr(x17472(1))), op=FixAdd, results=List(x17109.writeAddr))
      RAStage(operands=List(CU.ctr(x17655(0)), Const(64)), op=FixMul, results=List(b18454))
      RAStage(operands=List(b18454, CU.ctr(x17655(1))), op=FixAdd, results=List(x17109.readAddr))
    }
    val x17110_dsp0 = MemoryPipeline(name="x17110_dsp0",parent="x17914") { implicit CU => 
      val b18466 = CU.temp(None)
      val b18430 = CU.temp(None)
      val x17439 = ScalarFIFO(size=1,name="x17439").wtPort(x17110_x17439_s)
      val x17432 = CounterChain.copy("x17440_0", "x17432")
      val x17786 = CounterChain.copy("x17802_0", "x17786")
      val x17110 = SRAM(size=192,name="x17110",banking = Strided(1)).wtPort(x17439.readPort).rdPort(x17110_0_s)
      WAStage(operands=List(CU.ctr(x17432(0)), Const(3)), op=FixMul, results=List(b18430))
      WAStage(operands=List(b18430, CU.ctr(x17432(1))), op=FixAdd, results=List(x17110.writeAddr))
      RAStage(operands=List(CU.ctr(x17786(0)), Const(3)), op=FixMul, results=List(b18466))
      RAStage(operands=List(b18466, CU.ctr(x17786(1))), op=FixAdd, results=List(x17110.readAddr))
    }
    val x17111_dsp0 = MemoryPipeline(name="x17111_dsp0",parent="x17914") { implicit CU => 
      val x17507 = ScalarFIFO(size=1,name="x17507").wtPort(x17111_x17507_s)
      val x17482 = CounterChain.copy("x17509", "x17482")
      val x17543 = CounterChain.copy("x17558_0", "x17543")
      val x17111 = SRAM(size=64,name="x17111",banking = Strided(1)).wtPort(x17507.readPort).rdPort(x17111_0_s).rdAddr(x17543(0)).wtAddr(x17482(0))
    }
    val x17111_dsp1 = MemoryPipeline(name="x17111_dsp1",parent="x17914") { implicit CU => 
      val x17507 = ScalarFIFO(size=1,name="x17507").wtPort(x17111_x17507_s)
      val x17482 = CounterChain.copy("x17509", "x17482")
      val x17512 = CounterChain.copy("x17520_0", "x17512")
      val x17111 = SRAM(size=64,name="x17111",banking = Strided(1)).wtPort(x17507.readPort).rdPort(x17111_1_s).rdAddr(x17512(1)).wtAddr(x17482(0))
    }
    val x17112_dsp0 = MemoryPipeline(name="x17112_dsp0",parent="x17914") { implicit CU => 
      val x17467 = ScalarFIFO(size=1,name="x17467").wtPort(x17112_x17467_s)
      val x17442 = CounterChain.copy("x17469", "x17442")
      val x17674 = CounterChain.copy("x17689_0", "x17674")
      val x17112 = SRAM(size=64,name="x17112",banking = Strided(1)).wtPort(x17467.readPort).rdPort(x17112_0_s).rdAddr(x17674(0)).wtAddr(x17442(0))
    }
    val x17112_dsp1 = MemoryPipeline(name="x17112_dsp1",parent="x17914") { implicit CU => 
      val x17467 = ScalarFIFO(size=1,name="x17467").wtPort(x17112_x17467_s)
      val x17442 = CounterChain.copy("x17469", "x17442")
      val x17490 = CounterChain.copy("x17502_0", "x17490")
      val x17112 = SRAM(size=64,name="x17112",banking = Strided(1)).wtPort(x17467.readPort).rdPort(x17112_1_s).rdAddr(x17490(0)).wtAddr(x17442(0))
    }
    val x17112_dsp2 = MemoryPipeline(name="x17112_dsp2",parent="x17914") { implicit CU => 
      val x17467 = ScalarFIFO(size=1,name="x17467").wtPort(x17112_x17467_s)
      val x17442 = CounterChain.copy("x17469", "x17442")
      val x17472 = CounterChain.copy("x17480_0", "x17472")
      val x17112 = SRAM(size=64,name="x17112",banking = Strided(1)).wtPort(x17467.readPort).rdPort(x17112_2_s).rdAddr(x17472(1)).wtAddr(x17442(0))
    }
    val x17122_0 = Pipeline(name="x17122_0",parent=x17914) { implicit CU => 
      val x17115 = CU.temp(None)
      val x17116 = CU.temp(None)
      val x17114 = CU.temp(None)
      val x17113 = CU.temp(None)
      val x17093 = CounterChain.copy("x17914", "x17093")
      val x17122_unit = CounterChain(name = "x17122_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17093(0)), Const(163)), op=FixMod, results=List(x17113, CU.scalarOut(x17094_x17118_s)))
      Stage(operands=List(x17113, Const(13)), op=FixMul, results=List(x17114, CU.scalarOut(x17095_x17119_s)))
      Stage(operands=List(x17113, Const(1)), op=FixAdd, results=List(x17115, CU.scalarOut(x17096_x17120_s)))
      Stage(operands=List(x17115, Const(13)), op=FixMul, results=List(x17116))
      Stage(operands=List(x17116, x17114), op=FixSub, results=List(CU.scalarOut(x17097_x17121_s)))
    }
    val x17180 = StreamController(name="x17180",parent=x17914) { implicit CU => 
      val x17180_unit = CounterChain(name = "x17180_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17151 = StreamController(name="x17151",parent=x17180) { implicit CU => 
      val x17151_unit = CounterChain(name = "x17151_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17151_0 = Pipeline(name="x17151_0",parent=x17151) { implicit CU => 
      val x17128 = CU.temp(None)
      val x17136 = CU.temp(None)
      val x17129 = CU.temp(None)
      val x17095 = ScalarBuffer(name="x17095").wtPort(x17095_x17119_s)
      val x17097 = ScalarBuffer(name="x17097").wtPort(x17097_x17121_s)
      Stage(operands=List(CU.load(x17095), Const(2)), op=FixSla, results=List(x17128, CU.scalarOut(bus_1728_s)))
      Stage(operands=List(x17128, Const(16)), op=FixMod, results=List(x17129, CU.scalarOut(bus_1730_s)))
      Stage(operands=List(x17129, Const(4)), op=FixDiv, results=List(x17136, CU.scalarOut(bus_1732_s), CU.scalarOut(x17124_b18401_x17150_b18409_s)))
      Stage(operands=List(x17136, CU.load(x17097)), op=FixAdd, results=List(CU.scalarOut(x17124_b18402_x17150_b18410_s)))
      Stage(operands=List(CU.load(x17097), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1734_s)))
    }
    val x17151_1 = Pipeline(name="x17151_1",parent=x17151) { implicit CU => 
      val x17133 = CU.temp(None)
      val x17135 = CU.temp(None)
      val x17137 = CU.temp(None)
      val x17134 = CU.temp(None)
      val x17139 = CU.temp(None)
      val x17128 = ScalarFIFO(size=1,name="x17128").wtPort(bus_1728_s)
      val x17131 = ScalarFIFO(size=1,name="x17131").wtPort(bus_1734_s)
      val x17097 = ScalarBuffer(name="x17097").wtPort(x17097_x17121_s)
      val x17136 = ScalarFIFO(size=1,name="x17136").wtPort(bus_1732_s)
      Stage(operands=List(CU.load(x17128), CU.load(x17131)), op=FixAdd, results=List(x17133))
      Stage(operands=List(x17133, Const(16)), op=FixMod, results=List(x17134))
      Stage(operands=List(Const(16), x17134), op=FixSub, results=List(x17135, CU.scalarOut(bus_1737_s)))
      Stage(operands=List(x17135, Const(4)), op=FixDiv, results=List(x17137))
      Stage(operands=List(CU.load(x17097), CU.load(x17136)), op=FixAdd, results=List(x17139))
      Stage(operands=List(x17139, x17137), op=FixAdd, results=List(CU.scalarOut(x17124_b18400_x17150_b18408_s)))
    }
    val x17151_2 = Pipeline(name="x17151_2",parent=x17151) { implicit CU => 
      val x17141 = CU.temp(None)
      val x17132 = CU.temp(None)
      val x17131 = ScalarFIFO(size=1,name="x17131").wtPort(bus_1734_s)
      val x17129 = ScalarFIFO(size=1,name="x17129").wtPort(bus_1730_s)
      val x17135 = ScalarFIFO(size=1,name="x17135").wtPort(bus_1737_s)
      val x17128 = ScalarFIFO(size=1,name="x17128").wtPort(bus_1728_s)
      val x17144 = ScalarBuffer(name="x17144").wtPort(training_data_dram_da)
      Stage(operands=List(CU.load(x17131), CU.load(x17129)), op=FixAdd, results=List(x17141))
      Stage(operands=List(x17141, CU.load(x17135)), op=FixAdd, results=List(CU.scalarOut(x17123_b18399_x17148_b18407_s)))
      Stage(operands=List(CU.load(x17128), CU.load(x17129)), op=FixSub, results=List(x17132))
      Stage(operands=List(x17132, CU.load(x17144)), op=FixAdd, results=List(CU.scalarOut(x17123_b18398_x17148_b18406_s)))
    }
    val x17152 = MemoryController(name="x17152",parent=x17180,offchip=training_data_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17123_b18399 = ScalarFIFO(size=1,name="size").wtPort(x17123_b18399_x17148_b18407_s)
      val x17123_b18398 = ScalarFIFO(size=1,name="offset").wtPort(x17123_b18398_x17148_b18406_s)
      CU.newVout("data", x17125_x17152_data_v)
    }
    val x17179 = Sequential(name="x17179",parent=x17180) { implicit CU => 
      val x17179_unit = CounterChain(name = "x17179_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17163_0 = Pipeline(name="x17163_0",parent=x17179) { implicit CU => 
      val x17124_b18400 = ScalarFIFO(size=16,name="x17124_b18400").wtPort(x17124_b18400_x17150_b18408_s)
      val x17124_b18402 = ScalarFIFO(size=16,name="x17124_b18402").wtPort(x17124_b18402_x17150_b18410_s)
      val x17124_b18401 = ScalarFIFO(size=16,name="x17124_b18401").wtPort(x17124_b18401_x17150_b18409_s)
      val x17163_unit = CounterChain(name = "x17163_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17124_b18401)), op=Bypass, results=List(CU.scalarOut(x17153_x17158_s)))
      Stage(operands=List(CU.load(x17124_b18402)), op=Bypass, results=List(CU.scalarOut(x17154_x17160_s)))
      Stage(operands=List(CU.load(x17124_b18400)), op=Bypass, results=List(CU.scalarOut(x17155_x17162_s)))
    }
    val x17178_0 = Pipeline(name="x17178_0",parent=x17179) { implicit CU => 
      val x17153 = ScalarBuffer(name="x17153").wtPort(x17153_x17158_s)
      val x17125 = VectorFIFO(size=1,name="x17125").wtPort(x17125_x17152_data_v)
      val x17155 = ScalarBuffer(name="x17155").wtPort(x17155_x17162_s)
      val x17154 = ScalarBuffer(name="x17154").wtPort(x17154_x17160_s)
      val ctr12 = Counter(min=Const(0), max=x17155.readPort, step=Const(1), par=16) // Counter
      val x17166 = CounterChain(name = "x17166", ctr12).iter(1)
      Stage(operands=List(CU.load(x17153), CU.ctr(x17166(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x17166(0)), CU.load(x17154)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x17125)), op=Bypass, results=List(CU.scalarOut(x17104_x17177_s)))
    }
    val x17190_0 = Pipeline(name="x17190_0",parent=x17914) { implicit CU => 
      val x17186 = CU.temp(None)
      val x17184 = CU.temp(None)
      val x17094 = ScalarBuffer(name="x17094").wtPort(x17094_x17118_s)
      val x17096 = ScalarBuffer(name="x17096").wtPort(x17096_x17120_s)
      val x17190_unit = CounterChain(name = "x17190_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17094), Const(3)), op=FixMul, results=List(x17184, CU.scalarOut(x17181_x17188_s)))
      Stage(operands=List(CU.load(x17096), Const(3)), op=FixMul, results=List(x17186))
      Stage(operands=List(x17186, x17184), op=FixSub, results=List(CU.scalarOut(x17182_x17189_s)))
    }
    val x17248 = StreamController(name="x17248",parent=x17914) { implicit CU => 
      val x17248_unit = CounterChain(name = "x17248_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17219 = StreamController(name="x17219",parent=x17248) { implicit CU => 
      val x17219_unit = CounterChain(name = "x17219_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17219_0 = Pipeline(name="x17219_0",parent=x17219) { implicit CU => 
      val x17196 = CU.temp(None)
      val x17197 = CU.temp(None)
      val x17204 = CU.temp(None)
      val x17182 = ScalarBuffer(name="x17182").wtPort(x17182_x17189_s)
      val x17181 = ScalarBuffer(name="x17181").wtPort(x17181_x17188_s)
      Stage(operands=List(CU.load(x17181), Const(2)), op=FixSla, results=List(x17196, CU.scalarOut(bus_1756_s)))
      Stage(operands=List(x17196, Const(16)), op=FixMod, results=List(x17197, CU.scalarOut(bus_1758_s)))
      Stage(operands=List(x17197, Const(4)), op=FixDiv, results=List(x17204, CU.scalarOut(bus_1760_s), CU.scalarOut(x17192_b18414_x17218_b18422_s)))
      Stage(operands=List(x17204, CU.load(x17182)), op=FixAdd, results=List(CU.scalarOut(x17192_b18415_x17218_b18423_s)))
      Stage(operands=List(CU.load(x17182), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1762_s)))
    }
    val x17219_1 = Pipeline(name="x17219_1",parent=x17219) { implicit CU => 
      val x17202 = CU.temp(None)
      val x17205 = CU.temp(None)
      val x17207 = CU.temp(None)
      val x17203 = CU.temp(None)
      val x17201 = CU.temp(None)
      val x17199 = ScalarFIFO(size=1,name="x17199").wtPort(bus_1762_s)
      val x17196 = ScalarFIFO(size=1,name="x17196").wtPort(bus_1756_s)
      val x17182 = ScalarBuffer(name="x17182").wtPort(x17182_x17189_s)
      val x17204 = ScalarFIFO(size=1,name="x17204").wtPort(bus_1760_s)
      Stage(operands=List(CU.load(x17196), CU.load(x17199)), op=FixAdd, results=List(x17201))
      Stage(operands=List(x17201, Const(16)), op=FixMod, results=List(x17202))
      Stage(operands=List(Const(16), x17202), op=FixSub, results=List(x17203, CU.scalarOut(bus_1765_s)))
      Stage(operands=List(x17203, Const(4)), op=FixDiv, results=List(x17205))
      Stage(operands=List(CU.load(x17182), CU.load(x17204)), op=FixAdd, results=List(x17207))
      Stage(operands=List(x17207, x17205), op=FixAdd, results=List(CU.scalarOut(x17192_b18413_x17218_b18421_s)))
    }
    val x17219_2 = Pipeline(name="x17219_2",parent=x17219) { implicit CU => 
      val x17200 = CU.temp(None)
      val x17209 = CU.temp(None)
      val x17197 = ScalarFIFO(size=1,name="x17197").wtPort(bus_1758_s)
      val x17196 = ScalarFIFO(size=1,name="x17196").wtPort(bus_1756_s)
      val x17199 = ScalarFIFO(size=1,name="x17199").wtPort(bus_1762_s)
      val x17212 = ScalarBuffer(name="x17212").wtPort(training_targets_dram_da)
      val x17203 = ScalarFIFO(size=1,name="x17203").wtPort(bus_1765_s)
      Stage(operands=List(CU.load(x17199), CU.load(x17197)), op=FixAdd, results=List(x17209))
      Stage(operands=List(x17209, CU.load(x17203)), op=FixAdd, results=List(CU.scalarOut(x17191_b18412_x17216_b18420_s)))
      Stage(operands=List(CU.load(x17196), CU.load(x17197)), op=FixSub, results=List(x17200))
      Stage(operands=List(x17200, CU.load(x17212)), op=FixAdd, results=List(CU.scalarOut(x17191_b18411_x17216_b18419_s)))
    }
    val x17220 = MemoryController(name="x17220",parent=x17248,offchip=training_targets_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17191_b18412 = ScalarFIFO(size=1,name="size").wtPort(x17191_b18412_x17216_b18420_s)
      val x17191_b18411 = ScalarFIFO(size=1,name="offset").wtPort(x17191_b18411_x17216_b18419_s)
      CU.newSout("data", x17193_x17220_data_s)
    }
    val x17247 = Sequential(name="x17247",parent=x17248) { implicit CU => 
      val x17247_unit = CounterChain(name = "x17247_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17231_0 = Pipeline(name="x17231_0",parent=x17247) { implicit CU => 
      val x17192_b18413 = ScalarFIFO(size=16,name="x17192_b18413").wtPort(x17192_b18413_x17218_b18421_s)
      val x17192_b18415 = ScalarFIFO(size=16,name="x17192_b18415").wtPort(x17192_b18415_x17218_b18423_s)
      val x17192_b18414 = ScalarFIFO(size=16,name="x17192_b18414").wtPort(x17192_b18414_x17218_b18422_s)
      val x17231_unit = CounterChain(name = "x17231_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17192_b18414)), op=Bypass, results=List(CU.scalarOut(x17221_x17226_s)))
      Stage(operands=List(CU.load(x17192_b18415)), op=Bypass, results=List(CU.scalarOut(x17222_x17228_s)))
      Stage(operands=List(CU.load(x17192_b18413)), op=Bypass, results=List(CU.scalarOut(x17223_x17230_s)))
    }
    val x17246_0 = Pipeline(name="x17246_0",parent=x17247) { implicit CU => 
      val x17222 = ScalarBuffer(name="x17222").wtPort(x17222_x17228_s)
      val x17221 = ScalarBuffer(name="x17221").wtPort(x17221_x17226_s)
      val x17193 = ScalarFIFO(size=1,name="x17193").wtPort(x17193_x17220_data_s)
      val x17223 = ScalarBuffer(name="x17223").wtPort(x17223_x17230_s)
      val ctr13 = Counter(min=Const(0), max=x17223.readPort, step=Const(1), par=1) // Counter
      val x17234 = CounterChain(name = "x17234", ctr13).iter(1)
      Stage(operands=List(CU.load(x17221), CU.ctr(x17234(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x17234(0)), CU.load(x17222)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x17193)), op=Bypass, results=List(CU.scalarOut(x17105_x17245_s)))
    }
    val x17272 = MetaPipeline(name="x17272",parent=x17914) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17250 = CounterChain(name = "x17250", ctr14).iter(64)
    }
    val x17265_0 = Pipeline(name="x17265_0",parent=x17272) { implicit CU => 
      val x17257 = ScalarFIFO(size=1,name="x17257").wtPort(x17104_1_s)
      val x17256 = ScalarFIFO(size=1,name="x17256").wtPort(x16951_4_s)
      val ctr15 = Counter(min=Const(0), max=Const(13), step=Const(1), par=16) // Counter
      val x17253 = CounterChain(name = "x17253", ctr15).iter(1)
      Stage(operands=List(CU.load(x17256), CU.load(x17257)), op=FixMul, results=List(CU.reduce))
      val (_, rr1784) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17265_0")
      Stage(operands=List(rr1784), op=Bypass, results=List(CU.scalarOut(x17251_x17264_s)))
    }
    val x17271_0 = Pipeline(name="x17271_0",parent=x17272) { implicit CU => 
      val x17267 = ScalarFIFO(size=1,name="x17267").wtPort(x16948_4_s)
      val x17251 = ScalarBuffer(name="x17251").wtPort(x17251_x17264_s)
      val x17271_unit = CounterChain(name = "x17271_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17251), CU.load(x17267)), op=FixAdd, results=List(CU.scalarOut(x17098_x17270_s)))
    }
    val x17284_0 = Pipeline(name="x17284_0",parent=x17914) { implicit CU => 
      val x17281 = CU.temp(None)
      val x17278 = CU.temp(None)
      val x17276 = ScalarFIFO(size=1,name="x17276").wtPort(x17098_2_s)
      val ctr16 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17274 = CounterChain(name = "x17274", ctr16).iter(4)
      Stage(operands=List(CU.load(x17276), Const(0)), op=FixLt, results=List(x17281))
      Stage(operands=List(x17281, Const(0), CU.load(x17276)), op=Mux, results=List(CU.scalarOut(x17098_x17283_s)))
      Stage(operands=List(Const(1), CU.load(x17276)), op=FixSub, results=List(x17278))
      Stage(operands=List(CU.load(x17276), x17278), op=FixMul, results=List(CU.scalarOut(x17101_x17280_s)))
    }
    val x17308 = MetaPipeline(name="x17308",parent=x17914) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17286 = CounterChain(name = "x17286", ctr17).iter(64)
    }
    val x17301_0 = Pipeline(name="x17301_0",parent=x17308) { implicit CU => 
      val x17293 = ScalarFIFO(size=1,name="x17293").wtPort(x17098_1_s)
      val x17292 = ScalarFIFO(size=1,name="x17292").wtPort(x16952_5_s)
      val ctr18 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17289 = CounterChain(name = "x17289", ctr18).iter(4)
      Stage(operands=List(CU.load(x17292), CU.load(x17293)), op=FixMul, results=List(CU.reduce))
      val (_, rr1800) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17301_0")
      Stage(operands=List(rr1800), op=Bypass, results=List(CU.scalarOut(x17287_x17300_s)))
    }
    val x17307_0 = Pipeline(name="x17307_0",parent=x17308) { implicit CU => 
      val x17303 = ScalarFIFO(size=1,name="x17303").wtPort(x16949_4_s)
      val x17287 = ScalarBuffer(name="x17287").wtPort(x17287_x17300_s)
      val x17307_unit = CounterChain(name = "x17307_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17287), CU.load(x17303)), op=FixAdd, results=List(CU.scalarOut(x17099_x17306_s)))
    }
    val x17320_0 = Pipeline(name="x17320_0",parent=x17914) { implicit CU => 
      val x17314 = CU.temp(None)
      val x17317 = CU.temp(None)
      val x17312 = ScalarFIFO(size=1,name="x17312").wtPort(x17099_2_s)
      val ctr19 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17310 = CounterChain(name = "x17310", ctr19).iter(4)
      Stage(operands=List(CU.load(x17312), Const(0)), op=FixLt, results=List(x17317))
      Stage(operands=List(x17317, Const(0), CU.load(x17312)), op=Mux, results=List(CU.scalarOut(x17099_x17319_s)))
      Stage(operands=List(Const(1), CU.load(x17312)), op=FixSub, results=List(x17314))
      Stage(operands=List(CU.load(x17312), x17314), op=FixMul, results=List(CU.scalarOut(x17102_x17316_s)))
    }
    val x17344 = MetaPipeline(name="x17344",parent=x17914) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17322 = CounterChain(name = "x17322", ctr20).iter(3)
    }
    val x17337_0 = Pipeline(name="x17337_0",parent=x17344) { implicit CU => 
      val x17329 = ScalarFIFO(size=1,name="x17329").wtPort(x17099_1_s)
      val x17328 = ScalarFIFO(size=1,name="x17328").wtPort(x16953_5_s)
      val ctr21 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17325 = CounterChain(name = "x17325", ctr21).iter(4)
      Stage(operands=List(CU.load(x17328), CU.load(x17329)), op=FixMul, results=List(CU.reduce))
      val (_, rr1816) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17337_0")
      Stage(operands=List(rr1816), op=Bypass, results=List(CU.scalarOut(x17323_x17336_s)))
    }
    val x17343_0 = Pipeline(name="x17343_0",parent=x17344) { implicit CU => 
      val x17339 = ScalarFIFO(size=1,name="x17339").wtPort(x16950_4_s)
      val x17323 = ScalarBuffer(name="x17323").wtPort(x17323_x17336_s)
      val x17343_unit = CounterChain(name = "x17343_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17323), CU.load(x17339)), op=FixAdd, results=List(CU.scalarOut(x17100_x17342_s)))
    }
    val x17356_0 = Pipeline(name="x17356_0",parent=x17914) { implicit CU => 
      val x17353 = CU.temp(None)
      val x17350 = CU.temp(None)
      val x17348 = ScalarFIFO(size=1,name="x17348").wtPort(x17100_2_s)
      val ctr22 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17346 = CounterChain(name = "x17346", ctr22).iter(1)
      Stage(operands=List(CU.load(x17348), Const(0)), op=FixLt, results=List(x17353))
      Stage(operands=List(x17353, Const(0), CU.load(x17348)), op=Mux, results=List(CU.scalarOut(x17100_x17355_s)))
      Stage(operands=List(Const(1), CU.load(x17348)), op=FixSub, results=List(x17350))
      Stage(operands=List(CU.load(x17348), x17350), op=FixMul, results=List(CU.scalarOut(x17103_x17352_s)))
    }
    val x17386 = StreamController(name="x17386",parent=x17914) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17359 = CounterChain(name = "x17359", ctr23).iter(1)
    }
    val x17386_0 = Pipeline(name="x17386_0",parent=x17386) { implicit CU => 
      val x17367 = CU.temp(None)
      val x17364 = CU.temp(None)
      val x17361 = ScalarFIFO(size=1,name="x17361").wtPort(x17100_1_s)
      Stage(operands=List(CU.load(x17361)), op=FixNeg, results=List(x17364, CU.vecOut(bus_1828_v)))
      Stage(operands=List(x17364, Const(-3.5)), op=FixLt, results=List(CU.vecOut(bus_1830_v)))
      Stage(operands=List(x17364, Const(-1.2)), op=FixLt, results=List(CU.vecOut(bus_1832_v)))
      Stage(operands=List(x17364, Const(0.1)), op=FixMul, results=List(x17367))
      Stage(operands=List(x17367, Const(0.35)), op=FixAdd, results=List(CU.vecOut(bus_1836_v)))
    }
    val x17386_1 = Pipeline(name="x17386_1",parent=x17386) { implicit CU => 
      val x17371 = CU.temp(None)
      val x17370 = CU.temp(None)
      val x17373 = CU.temp(None)
      val x17369 = CU.temp(None)
      val x17364 = VectorFIFO(size=1,name="x17364").wtPort(bus_1828_v)
      Stage(operands=List(Const(1), CU.load(x17364)), op=FixAdd, results=List(x17369))
      Stage(operands=List(CU.load(x17364), CU.load(x17364)), op=FixMul, results=List(x17370))
      Stage(operands=List(x17370, Const(2)), op=FixDiv, results=List(x17371))
      Stage(operands=List(x17369, x17371), op=FixAdd, results=List(CU.vecOut(bus_1842_v)))
      Stage(operands=List(x17370, CU.load(x17364)), op=FixMul, results=List(x17373, CU.vecOut(bus_1843_v)))
      Stage(operands=List(x17373, Const(6)), op=FixDiv, results=List(CU.vecOut(bus_1845_v)))
    }
    val x17386_2 = Pipeline(name="x17386_2",parent=x17386) { implicit CU => 
      val x17379 = CU.temp(None)
      val x17376 = CU.temp(None)
      val x17375 = CU.temp(None)
      val x17377 = CU.temp(None)
      val x17374 = VectorFIFO(size=1,name="x17374").wtPort(bus_1845_v)
      val x17372 = VectorFIFO(size=1,name="x17372").wtPort(bus_1842_v)
      val x17364 = VectorFIFO(size=1,name="x17364").wtPort(bus_1828_v)
      val x17373 = VectorFIFO(size=1,name="x17373").wtPort(bus_1843_v)
      Stage(operands=List(CU.load(x17372), CU.load(x17374)), op=FixAdd, results=List(x17375))
      Stage(operands=List(CU.load(x17373), CU.load(x17364)), op=FixMul, results=List(x17376))
      Stage(operands=List(x17376, Const(24)), op=FixDiv, results=List(x17377))
      Stage(operands=List(x17375, x17377), op=FixAdd, results=List(CU.vecOut(bus_1850_v)))
      Stage(operands=List(x17376, CU.load(x17364)), op=FixMul, results=List(x17379))
      Stage(operands=List(x17379, Const(120)), op=FixDiv, results=List(CU.vecOut(bus_1853_v)))
    }
    val x17386_3 = Pipeline(name="x17386_3",parent=x17386) { implicit CU => 
      val x17381 = CU.temp(None)
      val x17378 = VectorFIFO(size=1,name="x17378").wtPort(bus_1850_v)
      val x17368 = VectorFIFO(size=1,name="x17368").wtPort(bus_1836_v)
      val x17366 = VectorFIFO(size=1,name="x17366").wtPort(bus_1832_v)
      val x17380 = VectorFIFO(size=1,name="x17380").wtPort(bus_1853_v)
      Stage(operands=List(CU.load(x17378), CU.load(x17380)), op=FixAdd, results=List(x17381))
      Stage(operands=List(CU.load(x17366), CU.load(x17368), x17381), op=Mux, results=List(CU.vecOut(bus_1855_v)))
    }
    val x17386_4 = Pipeline(name="x17386_4",parent=x17386) { implicit CU => 
      val x17382 = VectorFIFO(size=1,name="x17382").wtPort(bus_1855_v)
      val x17365 = VectorFIFO(size=1,name="x17365").wtPort(bus_1830_v)
      Stage(operands=List(CU.load(x17365), Const(0), CU.load(x17382)), op=Mux, results=List(CU.reduce))
      val (_, rr1860) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17386")
      Stage(operands=List(rr1860), op=Bypass, results=List(CU.scalarOut(x17357_x17385_s)))
    }
    val x17415 = StreamController(name="x17415",parent=x17914) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17388 = CounterChain(name = "x17388", ctr24).iter(1)
    }
    val x17415_0 = Pipeline(name="x17415_0",parent=x17415) { implicit CU => 
      val x17392 = CU.temp(None)
      val x17395 = CU.temp(None)
      val x17390 = ScalarFIFO(size=1,name="x17390").wtPort(x17100_0_s)
      Stage(operands=List(CU.load(x17390)), op=FixNeg, results=List(x17392, CU.vecOut(bus_1862_v)))
      Stage(operands=List(x17392, Const(-3.5)), op=FixLt, results=List(CU.vecOut(bus_1864_v)))
      Stage(operands=List(x17392, Const(-1.2)), op=FixLt, results=List(CU.vecOut(bus_1866_v)))
      Stage(operands=List(x17392, Const(0.1)), op=FixMul, results=List(x17395))
      Stage(operands=List(x17395, Const(0.35)), op=FixAdd, results=List(CU.vecOut(bus_1870_v)))
    }
    val x17415_1 = Pipeline(name="x17415_1",parent=x17415) { implicit CU => 
      val x17401 = CU.temp(None)
      val x17398 = CU.temp(None)
      val x17397 = CU.temp(None)
      val x17399 = CU.temp(None)
      val x17392 = VectorFIFO(size=1,name="x17392").wtPort(bus_1862_v)
      Stage(operands=List(Const(1), CU.load(x17392)), op=FixAdd, results=List(x17397))
      Stage(operands=List(CU.load(x17392), CU.load(x17392)), op=FixMul, results=List(x17398))
      Stage(operands=List(x17398, Const(2)), op=FixDiv, results=List(x17399))
      Stage(operands=List(x17397, x17399), op=FixAdd, results=List(CU.vecOut(bus_1876_v)))
      Stage(operands=List(x17398, CU.load(x17392)), op=FixMul, results=List(x17401, CU.vecOut(bus_1877_v)))
      Stage(operands=List(x17401, Const(6)), op=FixDiv, results=List(CU.vecOut(bus_1879_v)))
    }
    val x17415_2 = Pipeline(name="x17415_2",parent=x17415) { implicit CU => 
      val x17404 = CU.temp(None)
      val x17407 = CU.temp(None)
      val x17403 = CU.temp(None)
      val x17405 = CU.temp(None)
      val x17392 = VectorFIFO(size=1,name="x17392").wtPort(bus_1862_v)
      val x17401 = VectorFIFO(size=1,name="x17401").wtPort(bus_1877_v)
      val x17400 = VectorFIFO(size=1,name="x17400").wtPort(bus_1876_v)
      val x17402 = VectorFIFO(size=1,name="x17402").wtPort(bus_1879_v)
      Stage(operands=List(CU.load(x17400), CU.load(x17402)), op=FixAdd, results=List(x17403))
      Stage(operands=List(CU.load(x17401), CU.load(x17392)), op=FixMul, results=List(x17404))
      Stage(operands=List(x17404, Const(24)), op=FixDiv, results=List(x17405))
      Stage(operands=List(x17403, x17405), op=FixAdd, results=List(CU.vecOut(bus_1884_v)))
      Stage(operands=List(x17404, CU.load(x17392)), op=FixMul, results=List(x17407))
      Stage(operands=List(x17407, Const(120)), op=FixDiv, results=List(CU.vecOut(bus_1887_v)))
    }
    val x17415_3 = Pipeline(name="x17415_3",parent=x17415) { implicit CU => 
      val x17409 = CU.temp(None)
      val x17394 = VectorFIFO(size=1,name="x17394").wtPort(bus_1866_v)
      val x17408 = VectorFIFO(size=1,name="x17408").wtPort(bus_1887_v)
      val x17396 = VectorFIFO(size=1,name="x17396").wtPort(bus_1870_v)
      val x17406 = VectorFIFO(size=1,name="x17406").wtPort(bus_1884_v)
      Stage(operands=List(CU.load(x17406), CU.load(x17408)), op=FixAdd, results=List(x17409))
      Stage(operands=List(CU.load(x17394), CU.load(x17396), x17409), op=Mux, results=List(CU.vecOut(bus_1889_v)))
    }
    val x17415_4 = Pipeline(name="x17415_4",parent=x17415) { implicit CU => 
      val x17411 = CU.temp(None)
      val x17410 = VectorFIFO(size=1,name="x17410").wtPort(bus_1889_v)
      val x17357 = ScalarBuffer(name="x17357").wtPort(x17357_x17385_s)
      val x17393 = VectorFIFO(size=1,name="x17393").wtPort(bus_1864_v)
      Stage(operands=List(CU.load(x17393), Const(0), CU.load(x17410)), op=Mux, results=List(x17411))
      Stage(operands=List(x17411, CU.load(x17357)), op=FixDiv, results=List(CU.scalarOut(x17106_x17414_s)))
    }
    val x17429_0 = Pipeline(name="x17429_0",parent=x17914) { implicit CU => 
      val x17423 = CU.temp(None)
      val x17424 = CU.temp(None)
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17103_0_s)
      val x17419 = ScalarFIFO(size=1,name="x17419").wtPort(x17106_0_s)
      val x17421 = ScalarFIFO(size=1,name="x17421").wtPort(x17105_0_s)
      val ctr25 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17417 = CounterChain(name = "x17417", ctr25).iter(1)
      Stage(operands=List(CU.load(x17419), CU.load(x17421)), op=FixSub, results=List(x17423))
      Stage(operands=List(x17423), op=FixNeg, results=List(x17424))
      Stage(operands=List(x17424, CU.load(x17425)), op=FixMul, results=List(CU.scalarOut(x17107_x17428_s)))
    }
    val x17440_0 = Pipeline(name="x17440_0",parent=x17914) { implicit CU => 
      val x17435 = ScalarFIFO(size=1,name="x17435").wtPort(x17099_0_s)
      val x17436 = ScalarFIFO(size=1,name="x17436").wtPort(x17107_2_s)
      val ctr26 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr27 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17432 = CounterChain(name = "x17432", ctr26, ctr27).iter(64)
      Stage(operands=List(CU.load(x17435), CU.load(x17436)), op=FixMul, results=List(CU.scalarOut(x17110_x17439_s)))
    }
    val x17469 = Sequential(name="x17469",parent=x17914) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17442 = CounterChain(name = "x17442", ctr28).iter(64)
    }
    val x17448_0 = Pipeline(name="x17448_0",parent=x17469) { implicit CU => 
      val x17446 = ScalarFIFO(size=1,name="x17446").wtPort(x17102_0_s)
      val x17448_unit = CounterChain(name = "x17448_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17446)), op=Bypass, results=List(CU.scalarOut(x17443_x17447_s)))
    }
    val x17462_0 = Pipeline(name="x17462_0",parent=x17469) { implicit CU => 
      val x17453 = ScalarFIFO(size=1,name="x17453").wtPort(x17107_1_s)
      val x17454 = ScalarFIFO(size=1,name="x17454").wtPort(x16953_4_s)
      val ctr29 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17450 = CounterChain(name = "x17450", ctr29).iter(1)
      Stage(operands=List(CU.load(x17453), CU.load(x17454)), op=FixMul, results=List(CU.reduce))
      val (_, rr1906) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17462_0")
      Stage(operands=List(rr1906), op=Bypass, results=List(CU.scalarOut(x17444_x17461_s)))
    }
    val x17468_0 = Pipeline(name="x17468_0",parent=x17469) { implicit CU => 
      val x17444 = ScalarBuffer(name="x17444").wtPort(x17444_x17461_s)
      val x17443 = ScalarBuffer(name="x17443").wtPort(x17443_x17447_s)
      val x17468_unit = CounterChain(name = "x17468_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17443), CU.load(x17444)), op=FixMul, results=List(CU.scalarOut(x17112_x17467_s)))
    }
    val x17480_0 = Pipeline(name="x17480_0",parent=x17914) { implicit CU => 
      val x17476 = ScalarFIFO(size=1,name="x17476").wtPort(x17112_2_s)
      val x17475 = ScalarFIFO(size=1,name="x17475").wtPort(x17098_0_s)
      val ctr30 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17472 = CounterChain(name = "x17472", ctr30, ctr31).iter(256)
      Stage(operands=List(CU.load(x17475), CU.load(x17476)), op=FixSub, results=List(CU.scalarOut(x17109_x17479_s)))
    }
    val x17509 = Sequential(name="x17509",parent=x17914) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17482 = CounterChain(name = "x17482", ctr32).iter(64)
    }
    val x17488_0 = Pipeline(name="x17488_0",parent=x17509) { implicit CU => 
      val x17486 = ScalarFIFO(size=1,name="x17486").wtPort(x17101_0_s)
      val x17488_unit = CounterChain(name = "x17488_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17486)), op=Bypass, results=List(CU.scalarOut(x17483_x17487_s)))
    }
    val x17502_0 = Pipeline(name="x17502_0",parent=x17509) { implicit CU => 
      val x17494 = ScalarFIFO(size=1,name="x17494").wtPort(x16952_4_s)
      val x17493 = ScalarFIFO(size=1,name="x17493").wtPort(x17112_1_s)
      val ctr33 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17490 = CounterChain(name = "x17490", ctr33).iter(4)
      Stage(operands=List(CU.load(x17493), CU.load(x17494)), op=FixMul, results=List(CU.reduce))
      val (_, rr1919) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17502_0")
      Stage(operands=List(rr1919), op=Bypass, results=List(CU.scalarOut(x17484_x17501_s)))
    }
    val x17508_0 = Pipeline(name="x17508_0",parent=x17509) { implicit CU => 
      val x17484 = ScalarBuffer(name="x17484").wtPort(x17484_x17501_s)
      val x17483 = ScalarBuffer(name="x17483").wtPort(x17483_x17487_s)
      val x17508_unit = CounterChain(name = "x17508_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17483), CU.load(x17484)), op=FixMul, results=List(CU.scalarOut(x17111_x17507_s)))
    }
    val x17520_0 = Pipeline(name="x17520_0",parent=x17914) { implicit CU => 
      val x17515 = ScalarFIFO(size=1,name="x17515").wtPort(x17111_1_s)
      val x17517 = ScalarFIFO(size=1,name="x17517").wtPort(x17104_0_s)
      val ctr34 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr35 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17512 = CounterChain(name = "x17512", ctr34, ctr35).iter(52)
      Stage(operands=List(CU.load(x17515), CU.load(x17517)), op=FixMul, results=List(CU.scalarOut(x17108_x17519_s)))
    }
    val x17540_0 = Pipeline(name="x17540_0",parent=x17914) { implicit CU => 
      val x17521 = CU.temp(Some(0))
      val x17531 = CU.temp(None)
      val x17537 = CU.temp(None)
      val x17527 = ScalarFIFO(size=1,name="x17527").wtPort(x16951_3_s)
      val x17534 = ScalarFIFO(size=1,name="x17534").wtPort(x16951_2_s)
      val x17528 = ScalarFIFO(size=1,name="x17528").wtPort(x17108_0_s)
      val ctr36 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr37 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17524 = CounterChain(name = "x17524", ctr36, ctr37).iter(52)
      Stage(operands=List(CU.load(x17534), CU.load(x17534)), op=FixMul, results=List(x17537))
      Stage(operands=List(x17537, x17521), op=FixAdd, results=List(CU.scalarOut(x17521_x17539_s)))
      Stage(operands=List(CU.load(x17528), Const(0.01)), op=FixMul, results=List(x17531))
      Stage(operands=List(CU.load(x17527), x17531), op=FixSub, results=List(CU.scalarOut(x16951_x17533_s)))
    }
    val x17558_0 = Pipeline(name="x17558_0",parent=x17914) { implicit CU => 
      val x17549 = CU.temp(None)
      val x17555 = CU.temp(None)
      val x17541 = CU.temp(Some(0))
      val x17545 = ScalarFIFO(size=1,name="x17545").wtPort(x16948_3_s)
      val x17552 = ScalarFIFO(size=1,name="x17552").wtPort(x16948_2_s)
      val x17546 = ScalarFIFO(size=1,name="x17546").wtPort(x17111_0_s)
      val ctr38 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17543 = CounterChain(name = "x17543", ctr38).iter(4)
      Stage(operands=List(CU.load(x17552), CU.load(x17552)), op=FixMul, results=List(x17555))
      Stage(operands=List(x17555, x17541), op=FixAdd, results=List(CU.scalarOut(x17541_x17557_s)))
      Stage(operands=List(CU.load(x17546), Const(0.01)), op=FixMul, results=List(x17549))
      Stage(operands=List(CU.load(x17545), x17549), op=FixSub, results=List(CU.scalarOut(x16948_x17551_s)))
    }
    val x17631 = StreamController(name="x17631",parent=x17914) { implicit CU => 
      val x17631_unit = CounterChain(name = "x17631_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17631_0 = Pipeline(name="x17631_0",parent=x17631) { implicit CU => 
      val x17600 = CU.temp(None)
      val x17598 = CU.temp(None)
      val x17597 = CU.temp(None)
      val x17541 = ScalarBuffer(name="x17541").wtPort(x17541_x17557_s)
      Stage(operands=List(CU.load(x17541), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_1940_s)))
      Stage(operands=List(CU.load(x17541), Const(1)), op=FixSub, results=List(x17597, CU.scalarOut(bus_1942_s)))
      Stage(operands=List(x17597, Const(2)), op=FixDiv, results=List(x17598))
      Stage(operands=List(Const(1), x17598), op=FixAdd, results=List(CU.scalarOut(bus_1944_s)))
      Stage(operands=List(x17597, x17597), op=FixMul, results=List(x17600, CU.scalarOut(bus_1945_s)))
      Stage(operands=List(x17600, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_1947_s)))
    }
    val x17631_1 = Pipeline(name="x17631_1",parent=x17631) { implicit CU => 
      val x17603 = CU.temp(None)
      val x17602 = CU.temp(None)
      val x17604 = CU.temp(None)
      val x17601 = ScalarFIFO(size=1,name="x17601").wtPort(bus_1947_s)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(bus_1944_s)
      val x17600 = ScalarFIFO(size=1,name="x17600").wtPort(bus_1945_s)
      val x17597 = ScalarFIFO(size=1,name="x17597").wtPort(bus_1942_s)
      val x17541 = ScalarBuffer(name="x17541").wtPort(x17541_x17557_s)
      Stage(operands=List(CU.load(x17599), CU.load(x17601)), op=FixSub, results=List(x17602))
      Stage(operands=List(CU.load(x17600), CU.load(x17597)), op=FixMul, results=List(x17603))
      Stage(operands=List(x17603, Const(16)), op=FixDiv, results=List(x17604))
      Stage(operands=List(x17602, x17604), op=FixAdd, results=List(CU.scalarOut(bus_1952_s)))
      Stage(operands=List(CU.load(x17541), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_1954_s)))
      Stage(operands=List(CU.load(x17541), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_1956_s)))
    }
    val x17631_2 = Pipeline(name="x17631_2",parent=x17631) { implicit CU => 
      val x17610 = CU.temp(None)
      val x17607 = ScalarFIFO(size=1,name="x17607").wtPort(bus_1956_s)
      val x17541 = ScalarBuffer(name="x17541").wtPort(x17541_x17557_s)
      Stage(operands=List(CU.load(x17607), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_1957_s)))
      Stage(operands=List(CU.load(x17541), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_1959_s)))
      Stage(operands=List(CU.load(x17541), Const(0.08)), op=FixMul, results=List(x17610))
      Stage(operands=List(x17610, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_1963_s)))
      Stage(operands=List(CU.load(x17541), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_1965_s)))
      Stage(operands=List(CU.load(x17541), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_1967_s)))
    }
    val x17631_3 = Pipeline(name="x17631_3",parent=x17631) { implicit CU => 
      val x17616 = CU.temp(None)
      val x17613 = ScalarFIFO(size=1,name="x17613").wtPort(bus_1967_s)
      val x17541 = ScalarBuffer(name="x17541").wtPort(x17541_x17557_s)
      Stage(operands=List(CU.load(x17613), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_1968_s)))
      Stage(operands=List(CU.load(x17541), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_1970_s)))
      Stage(operands=List(CU.load(x17541), Const(0.008)), op=FixMul, results=List(x17616))
      Stage(operands=List(x17616, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_1974_s)))
      Stage(operands=List(CU.load(x17541), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_1976_s)))
      Stage(operands=List(CU.load(x17541), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_1978_s)))
    }
    val x17631_4 = Pipeline(name="x17631_4",parent=x17631) { implicit CU => 
      val x17622 = CU.temp(None)
      val x17620 = CU.temp(None)
      val x17623 = CU.temp(None)
      val x17621 = CU.temp(None)
      val x17617 = ScalarFIFO(size=1,name="x17617").wtPort(bus_1974_s)
      val x17619 = ScalarFIFO(size=1,name="x17619").wtPort(bus_1978_s)
      val x17615 = ScalarFIFO(size=1,name="x17615").wtPort(bus_1970_s)
      val x17541 = ScalarBuffer(name="x17541").wtPort(x17541_x17557_s)
      val x17618 = ScalarFIFO(size=1,name="x17618").wtPort(bus_1976_s)
      Stage(operands=List(CU.load(x17619), Const(60)), op=FixAdd, results=List(x17620))
      Stage(operands=List(CU.load(x17541), Const(2.0E-4)), op=FixMul, results=List(x17621))
      Stage(operands=List(x17621, Const(300)), op=FixAdd, results=List(x17622))
      Stage(operands=List(CU.load(x17618), x17620, x17622), op=Mux, results=List(x17623))
      Stage(operands=List(CU.load(x17615), CU.load(x17617), x17623), op=Mux, results=List(CU.scalarOut(bus_1986_s)))
    }
    val x17631_5 = Pipeline(name="x17631_5",parent=x17631) { implicit CU => 
      val x17626 = CU.temp(None)
      val x17625 = CU.temp(None)
      val x17611 = ScalarFIFO(size=1,name="x17611").wtPort(bus_1963_s)
      val x17612 = ScalarFIFO(size=1,name="x17612").wtPort(bus_1965_s)
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(bus_1957_s)
      val x17624 = ScalarFIFO(size=1,name="x17624").wtPort(bus_1986_s)
      val x17609 = ScalarFIFO(size=1,name="x17609").wtPort(bus_1959_s)
      val x17606 = ScalarFIFO(size=1,name="x17606").wtPort(bus_1954_s)
      val x17614 = ScalarFIFO(size=1,name="x17614").wtPort(bus_1968_s)
      Stage(operands=List(CU.load(x17612), CU.load(x17614), CU.load(x17624)), op=Mux, results=List(x17625))
      Stage(operands=List(CU.load(x17609), CU.load(x17611), x17625), op=Mux, results=List(x17626))
      Stage(operands=List(CU.load(x17606), CU.load(x17608), x17626), op=Mux, results=List(CU.scalarOut(bus_1989_s)))
    }
    val x17631_6 = Pipeline(name="x17631_6",parent=x17631) { implicit CU => 
      val x17563 = CU.temp(None)
      val x17564 = CU.temp(None)
      val x17521 = ScalarBuffer(name="x17521").wtPort(x17521_x17539_s)
      val x17627 = ScalarFIFO(size=1,name="x17627").wtPort(bus_1989_s)
      val x17605 = ScalarFIFO(size=1,name="x17605").wtPort(bus_1952_s)
      val x17596 = ScalarFIFO(size=1,name="x17596").wtPort(bus_1940_s)
      Stage(operands=List(CU.load(x17596), CU.load(x17605), CU.load(x17627)), op=Mux, results=List(CU.scalarOut(x17560_x17630_s)))
      Stage(operands=List(CU.load(x17521), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_1991_s)))
      Stage(operands=List(CU.load(x17521), Const(1)), op=FixSub, results=List(x17563, CU.scalarOut(bus_1992_s)))
      Stage(operands=List(x17563, Const(2)), op=FixDiv, results=List(x17564))
      Stage(operands=List(Const(1), x17564), op=FixAdd, results=List(CU.scalarOut(bus_1994_s)))
      Stage(operands=List(x17563, x17563), op=FixMul, results=List(CU.scalarOut(bus_1995_s)))
    }
    val x17631_7 = Pipeline(name="x17631_7",parent=x17631) { implicit CU => 
      val x17570 = CU.temp(None)
      val x17568 = CU.temp(None)
      val x17567 = CU.temp(None)
      val x17569 = CU.temp(None)
      val x17521 = ScalarBuffer(name="x17521").wtPort(x17521_x17539_s)
      val x17563 = ScalarFIFO(size=1,name="x17563").wtPort(bus_1992_s)
      val x17565 = ScalarFIFO(size=1,name="x17565").wtPort(bus_1994_s)
      val x17566 = ScalarFIFO(size=1,name="x17566").wtPort(bus_1995_s)
      Stage(operands=List(CU.load(x17566), Const(8)), op=FixDiv, results=List(x17567))
      Stage(operands=List(CU.load(x17565), x17567), op=FixSub, results=List(x17568))
      Stage(operands=List(CU.load(x17566), CU.load(x17563)), op=FixMul, results=List(x17569))
      Stage(operands=List(x17569, Const(16)), op=FixDiv, results=List(x17570))
      Stage(operands=List(x17568, x17570), op=FixAdd, results=List(CU.scalarOut(bus_2000_s)))
      Stage(operands=List(CU.load(x17521), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2001_s)))
    }
    val x17631_8 = Pipeline(name="x17631_8",parent=x17631) { implicit CU => 
      val x17576 = CU.temp(None)
      val x17573 = CU.temp(None)
      val x17521 = ScalarBuffer(name="x17521").wtPort(x17521_x17539_s)
      Stage(operands=List(CU.load(x17521), Const(0.22)), op=FixMul, results=List(x17573))
      Stage(operands=List(x17573, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2003_s)))
      Stage(operands=List(CU.load(x17521), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2004_s)))
      Stage(operands=List(CU.load(x17521), Const(0.08)), op=FixMul, results=List(x17576))
      Stage(operands=List(x17576, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2006_s)))
      Stage(operands=List(CU.load(x17521), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2007_s)))
    }
    val x17631_9 = Pipeline(name="x17631_9",parent=x17631) { implicit CU => 
      val x17579 = CU.temp(None)
      val x17582 = CU.temp(None)
      val x17521 = ScalarBuffer(name="x17521").wtPort(x17521_x17539_s)
      Stage(operands=List(CU.load(x17521), Const(0.028)), op=FixMul, results=List(x17579))
      Stage(operands=List(x17579, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2009_s)))
      Stage(operands=List(CU.load(x17521), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2010_s)))
      Stage(operands=List(CU.load(x17521), Const(0.008)), op=FixMul, results=List(x17582))
      Stage(operands=List(x17582, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2012_s)))
      Stage(operands=List(CU.load(x17521), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2013_s)))
    }
    val x17631_10 = Pipeline(name="x17631_10",parent=x17631) { implicit CU => 
      val x17585 = CU.temp(None)
      val x17589 = CU.temp(None)
      val x17586 = CU.temp(None)
      val x17588 = CU.temp(None)
      val x17587 = CU.temp(None)
      val x17583 = ScalarFIFO(size=1,name="x17583").wtPort(bus_2012_s)
      val x17521 = ScalarBuffer(name="x17521").wtPort(x17521_x17539_s)
      val x17581 = ScalarFIFO(size=1,name="x17581").wtPort(bus_2010_s)
      val x17584 = ScalarFIFO(size=1,name="x17584").wtPort(bus_2013_s)
      Stage(operands=List(CU.load(x17521), Const(0.003)), op=FixMul, results=List(x17585))
      Stage(operands=List(x17585, Const(60)), op=FixAdd, results=List(x17586))
      Stage(operands=List(CU.load(x17521), Const(2.0E-4)), op=FixMul, results=List(x17587))
      Stage(operands=List(x17587, Const(300)), op=FixAdd, results=List(x17588))
      Stage(operands=List(CU.load(x17584), x17586, x17588), op=Mux, results=List(x17589))
      Stage(operands=List(CU.load(x17581), CU.load(x17583), x17589), op=Mux, results=List(CU.scalarOut(bus_2019_s)))
    }
    val x17631_11 = Pipeline(name="x17631_11",parent=x17631) { implicit CU => 
      val x17591 = CU.temp(None)
      val x17592 = CU.temp(None)
      val x17578 = ScalarFIFO(size=1,name="x17578").wtPort(bus_2007_s)
      val x17577 = ScalarFIFO(size=1,name="x17577").wtPort(bus_2006_s)
      val x17590 = ScalarFIFO(size=1,name="x17590").wtPort(bus_2019_s)
      val x17572 = ScalarFIFO(size=1,name="x17572").wtPort(bus_2001_s)
      val x17580 = ScalarFIFO(size=1,name="x17580").wtPort(bus_2009_s)
      val x17574 = ScalarFIFO(size=1,name="x17574").wtPort(bus_2003_s)
      val x17575 = ScalarFIFO(size=1,name="x17575").wtPort(bus_2004_s)
      Stage(operands=List(CU.load(x17578), CU.load(x17580), CU.load(x17590)), op=Mux, results=List(x17591))
      Stage(operands=List(CU.load(x17575), CU.load(x17577), x17591), op=Mux, results=List(x17592))
      Stage(operands=List(CU.load(x17572), CU.load(x17574), x17592), op=Mux, results=List(CU.scalarOut(bus_2022_s)))
    }
    val x17631_12 = Pipeline(name="x17631_12",parent=x17631) { implicit CU => 
      val x17562 = ScalarFIFO(size=1,name="x17562").wtPort(bus_1991_s)
      val x17571 = ScalarFIFO(size=1,name="x17571").wtPort(bus_2000_s)
      val x17593 = ScalarFIFO(size=1,name="x17593").wtPort(bus_2022_s)
      Stage(operands=List(CU.load(x17562), CU.load(x17571), CU.load(x17593)), op=Mux, results=List(CU.scalarOut(x17559_x17629_s)))
    }
    val x17642_0 = Pipeline(name="x17642_0",parent=x17914) { implicit CU => 
      val x17637 = ScalarFIFO(size=1,name="x17637").wtPort(x16951_1_s)
      val x17559 = ScalarBuffer(name="x17559").wtPort(x17559_x17629_s)
      val ctr39 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr40 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17634 = CounterChain(name = "x17634", ctr39, ctr40).iter(52)
      Stage(operands=List(CU.load(x17637), CU.load(x17559)), op=FixDiv, results=List(CU.scalarOut(x16951_x17641_s)))
    }
    val x17651_0 = Pipeline(name="x17651_0",parent=x17914) { implicit CU => 
      val x17560 = ScalarBuffer(name="x17560").wtPort(x17560_x17630_s)
      val x17646 = ScalarFIFO(size=1,name="x17646").wtPort(x16948_1_s)
      val ctr41 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17644 = CounterChain(name = "x17644", ctr41).iter(4)
      Stage(operands=List(CU.load(x17646), CU.load(x17560)), op=FixDiv, results=List(CU.scalarOut(x16948_x17650_s)))
    }
    val x17671_0 = Pipeline(name="x17671_0",parent=x17914) { implicit CU => 
      val x17662 = CU.temp(None)
      val x17652 = CU.temp(Some(0))
      val x17668 = CU.temp(None)
      val x17659 = ScalarFIFO(size=1,name="x17659").wtPort(x17109_0_s)
      val x17658 = ScalarFIFO(size=1,name="x17658").wtPort(x16952_3_s)
      val x17665 = ScalarFIFO(size=1,name="x17665").wtPort(x16952_2_s)
      val ctr42 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr43 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17655 = CounterChain(name = "x17655", ctr42, ctr43).iter(256)
      Stage(operands=List(CU.load(x17665), CU.load(x17665)), op=FixMul, results=List(x17668))
      Stage(operands=List(x17668, x17652), op=FixAdd, results=List(CU.scalarOut(x17652_x17670_s)))
      Stage(operands=List(CU.load(x17659), Const(0.01)), op=FixMul, results=List(x17662))
      Stage(operands=List(CU.load(x17658), x17662), op=FixSub, results=List(CU.scalarOut(x16952_x17664_s)))
    }
    val x17689_0 = Pipeline(name="x17689_0",parent=x17914) { implicit CU => 
      val x17672 = CU.temp(Some(0))
      val x17680 = CU.temp(None)
      val x17686 = CU.temp(None)
      val x17677 = ScalarFIFO(size=1,name="x17677").wtPort(x17112_0_s)
      val x17676 = ScalarFIFO(size=1,name="x17676").wtPort(x16949_3_s)
      val x17683 = ScalarFIFO(size=1,name="x17683").wtPort(x16949_2_s)
      val ctr44 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17674 = CounterChain(name = "x17674", ctr44).iter(4)
      Stage(operands=List(CU.load(x17683), CU.load(x17683)), op=FixMul, results=List(x17686))
      Stage(operands=List(x17686, x17672), op=FixAdd, results=List(CU.scalarOut(x17672_x17688_s)))
      Stage(operands=List(CU.load(x17677), Const(0.01)), op=FixMul, results=List(x17680))
      Stage(operands=List(CU.load(x17676), x17680), op=FixSub, results=List(CU.scalarOut(x16949_x17682_s)))
    }
    val x17762 = StreamController(name="x17762",parent=x17914) { implicit CU => 
      val x17762_unit = CounterChain(name = "x17762_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17762_0 = Pipeline(name="x17762_0",parent=x17762) { implicit CU => 
      val x17728 = CU.temp(None)
      val x17729 = CU.temp(None)
      val x17731 = CU.temp(None)
      val x17672 = ScalarBuffer(name="x17672").wtPort(x17672_x17688_s)
      Stage(operands=List(CU.load(x17672), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2043_s)))
      Stage(operands=List(CU.load(x17672), Const(1)), op=FixSub, results=List(x17728, CU.scalarOut(bus_2045_s)))
      Stage(operands=List(x17728, Const(2)), op=FixDiv, results=List(x17729))
      Stage(operands=List(Const(1), x17729), op=FixAdd, results=List(CU.scalarOut(bus_2047_s)))
      Stage(operands=List(x17728, x17728), op=FixMul, results=List(x17731, CU.scalarOut(bus_2048_s)))
      Stage(operands=List(x17731, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_2050_s)))
    }
    val x17762_1 = Pipeline(name="x17762_1",parent=x17762) { implicit CU => 
      val x17733 = CU.temp(None)
      val x17734 = CU.temp(None)
      val x17735 = CU.temp(None)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(bus_2047_s)
      val x17731 = ScalarFIFO(size=1,name="x17731").wtPort(bus_2048_s)
      val x17672 = ScalarBuffer(name="x17672").wtPort(x17672_x17688_s)
      val x17728 = ScalarFIFO(size=1,name="x17728").wtPort(bus_2045_s)
      val x17732 = ScalarFIFO(size=1,name="x17732").wtPort(bus_2050_s)
      Stage(operands=List(CU.load(x17730), CU.load(x17732)), op=FixSub, results=List(x17733))
      Stage(operands=List(CU.load(x17731), CU.load(x17728)), op=FixMul, results=List(x17734))
      Stage(operands=List(x17734, Const(16)), op=FixDiv, results=List(x17735))
      Stage(operands=List(x17733, x17735), op=FixAdd, results=List(CU.scalarOut(bus_2055_s)))
      Stage(operands=List(CU.load(x17672), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2057_s)))
      Stage(operands=List(CU.load(x17672), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_2059_s)))
    }
    val x17762_2 = Pipeline(name="x17762_2",parent=x17762) { implicit CU => 
      val x17741 = CU.temp(None)
      val x17672 = ScalarBuffer(name="x17672").wtPort(x17672_x17688_s)
      val x17738 = ScalarFIFO(size=1,name="x17738").wtPort(bus_2059_s)
      Stage(operands=List(CU.load(x17738), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2060_s)))
      Stage(operands=List(CU.load(x17672), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2062_s)))
      Stage(operands=List(CU.load(x17672), Const(0.08)), op=FixMul, results=List(x17741))
      Stage(operands=List(x17741, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2066_s)))
      Stage(operands=List(CU.load(x17672), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2068_s)))
      Stage(operands=List(CU.load(x17672), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_2070_s)))
    }
    val x17762_3 = Pipeline(name="x17762_3",parent=x17762) { implicit CU => 
      val x17747 = CU.temp(None)
      val x17672 = ScalarBuffer(name="x17672").wtPort(x17672_x17688_s)
      val x17744 = ScalarFIFO(size=1,name="x17744").wtPort(bus_2070_s)
      Stage(operands=List(CU.load(x17744), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2071_s)))
      Stage(operands=List(CU.load(x17672), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2073_s)))
      Stage(operands=List(CU.load(x17672), Const(0.008)), op=FixMul, results=List(x17747))
      Stage(operands=List(x17747, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2077_s)))
      Stage(operands=List(CU.load(x17672), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2079_s)))
      Stage(operands=List(CU.load(x17672), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_2081_s)))
    }
    val x17762_4 = Pipeline(name="x17762_4",parent=x17762) { implicit CU => 
      val x17751 = CU.temp(None)
      val x17752 = CU.temp(None)
      val x17753 = CU.temp(None)
      val x17754 = CU.temp(None)
      val x17748 = ScalarFIFO(size=1,name="x17748").wtPort(bus_2077_s)
      val x17672 = ScalarBuffer(name="x17672").wtPort(x17672_x17688_s)
      val x17749 = ScalarFIFO(size=1,name="x17749").wtPort(bus_2079_s)
      val x17746 = ScalarFIFO(size=1,name="x17746").wtPort(bus_2073_s)
      val x17750 = ScalarFIFO(size=1,name="x17750").wtPort(bus_2081_s)
      Stage(operands=List(CU.load(x17750), Const(60)), op=FixAdd, results=List(x17751))
      Stage(operands=List(CU.load(x17672), Const(2.0E-4)), op=FixMul, results=List(x17752))
      Stage(operands=List(x17752, Const(300)), op=FixAdd, results=List(x17753))
      Stage(operands=List(CU.load(x17749), x17751, x17753), op=Mux, results=List(x17754))
      Stage(operands=List(CU.load(x17746), CU.load(x17748), x17754), op=Mux, results=List(CU.scalarOut(bus_2089_s)))
    }
    val x17762_5 = Pipeline(name="x17762_5",parent=x17762) { implicit CU => 
      val x17756 = CU.temp(None)
      val x17757 = CU.temp(None)
      val x17745 = ScalarFIFO(size=1,name="x17745").wtPort(bus_2071_s)
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(bus_2060_s)
      val x17742 = ScalarFIFO(size=1,name="x17742").wtPort(bus_2066_s)
      val x17737 = ScalarFIFO(size=1,name="x17737").wtPort(bus_2057_s)
      val x17743 = ScalarFIFO(size=1,name="x17743").wtPort(bus_2068_s)
      val x17740 = ScalarFIFO(size=1,name="x17740").wtPort(bus_2062_s)
      val x17755 = ScalarFIFO(size=1,name="x17755").wtPort(bus_2089_s)
      Stage(operands=List(CU.load(x17743), CU.load(x17745), CU.load(x17755)), op=Mux, results=List(x17756))
      Stage(operands=List(CU.load(x17740), CU.load(x17742), x17756), op=Mux, results=List(x17757))
      Stage(operands=List(CU.load(x17737), CU.load(x17739), x17757), op=Mux, results=List(CU.scalarOut(bus_2092_s)))
    }
    val x17762_6 = Pipeline(name="x17762_6",parent=x17762) { implicit CU => 
      val x17695 = CU.temp(None)
      val x17694 = CU.temp(None)
      val x17736 = ScalarFIFO(size=1,name="x17736").wtPort(bus_2055_s)
      val x17652 = ScalarBuffer(name="x17652").wtPort(x17652_x17670_s)
      val x17727 = ScalarFIFO(size=1,name="x17727").wtPort(bus_2043_s)
      val x17758 = ScalarFIFO(size=1,name="x17758").wtPort(bus_2092_s)
      Stage(operands=List(CU.load(x17727), CU.load(x17736), CU.load(x17758)), op=Mux, results=List(CU.scalarOut(x17691_x17761_s)))
      Stage(operands=List(CU.load(x17652), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2094_s)))
      Stage(operands=List(CU.load(x17652), Const(1)), op=FixSub, results=List(x17694, CU.scalarOut(bus_2095_s)))
      Stage(operands=List(x17694, Const(2)), op=FixDiv, results=List(x17695))
      Stage(operands=List(Const(1), x17695), op=FixAdd, results=List(CU.scalarOut(bus_2097_s)))
      Stage(operands=List(x17694, x17694), op=FixMul, results=List(CU.scalarOut(bus_2098_s)))
    }
    val x17762_7 = Pipeline(name="x17762_7",parent=x17762) { implicit CU => 
      val x17700 = CU.temp(None)
      val x17701 = CU.temp(None)
      val x17699 = CU.temp(None)
      val x17698 = CU.temp(None)
      val x17696 = ScalarFIFO(size=1,name="x17696").wtPort(bus_2097_s)
      val x17652 = ScalarBuffer(name="x17652").wtPort(x17652_x17670_s)
      val x17694 = ScalarFIFO(size=1,name="x17694").wtPort(bus_2095_s)
      val x17697 = ScalarFIFO(size=1,name="x17697").wtPort(bus_2098_s)
      Stage(operands=List(CU.load(x17697), Const(8)), op=FixDiv, results=List(x17698))
      Stage(operands=List(CU.load(x17696), x17698), op=FixSub, results=List(x17699))
      Stage(operands=List(CU.load(x17697), CU.load(x17694)), op=FixMul, results=List(x17700))
      Stage(operands=List(x17700, Const(16)), op=FixDiv, results=List(x17701))
      Stage(operands=List(x17699, x17701), op=FixAdd, results=List(CU.scalarOut(bus_2103_s)))
      Stage(operands=List(CU.load(x17652), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2104_s)))
    }
    val x17762_8 = Pipeline(name="x17762_8",parent=x17762) { implicit CU => 
      val x17707 = CU.temp(None)
      val x17704 = CU.temp(None)
      val x17652 = ScalarBuffer(name="x17652").wtPort(x17652_x17670_s)
      Stage(operands=List(CU.load(x17652), Const(0.22)), op=FixMul, results=List(x17704))
      Stage(operands=List(x17704, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2106_s)))
      Stage(operands=List(CU.load(x17652), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2107_s)))
      Stage(operands=List(CU.load(x17652), Const(0.08)), op=FixMul, results=List(x17707))
      Stage(operands=List(x17707, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2109_s)))
      Stage(operands=List(CU.load(x17652), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2110_s)))
    }
    val x17762_9 = Pipeline(name="x17762_9",parent=x17762) { implicit CU => 
      val x17710 = CU.temp(None)
      val x17713 = CU.temp(None)
      val x17652 = ScalarBuffer(name="x17652").wtPort(x17652_x17670_s)
      Stage(operands=List(CU.load(x17652), Const(0.028)), op=FixMul, results=List(x17710))
      Stage(operands=List(x17710, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2112_s)))
      Stage(operands=List(CU.load(x17652), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2113_s)))
      Stage(operands=List(CU.load(x17652), Const(0.008)), op=FixMul, results=List(x17713))
      Stage(operands=List(x17713, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2115_s)))
      Stage(operands=List(CU.load(x17652), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2116_s)))
    }
    val x17762_10 = Pipeline(name="x17762_10",parent=x17762) { implicit CU => 
      val x17718 = CU.temp(None)
      val x17720 = CU.temp(None)
      val x17717 = CU.temp(None)
      val x17719 = CU.temp(None)
      val x17716 = CU.temp(None)
      val x17712 = ScalarFIFO(size=1,name="x17712").wtPort(bus_2113_s)
      val x17714 = ScalarFIFO(size=1,name="x17714").wtPort(bus_2115_s)
      val x17715 = ScalarFIFO(size=1,name="x17715").wtPort(bus_2116_s)
      val x17652 = ScalarBuffer(name="x17652").wtPort(x17652_x17670_s)
      Stage(operands=List(CU.load(x17652), Const(0.003)), op=FixMul, results=List(x17716))
      Stage(operands=List(x17716, Const(60)), op=FixAdd, results=List(x17717))
      Stage(operands=List(CU.load(x17652), Const(2.0E-4)), op=FixMul, results=List(x17718))
      Stage(operands=List(x17718, Const(300)), op=FixAdd, results=List(x17719))
      Stage(operands=List(CU.load(x17715), x17717, x17719), op=Mux, results=List(x17720))
      Stage(operands=List(CU.load(x17712), CU.load(x17714), x17720), op=Mux, results=List(CU.scalarOut(bus_2122_s)))
    }
    val x17762_11 = Pipeline(name="x17762_11",parent=x17762) { implicit CU => 
      val x17722 = CU.temp(None)
      val x17723 = CU.temp(None)
      val x17706 = ScalarFIFO(size=1,name="x17706").wtPort(bus_2107_s)
      val x17705 = ScalarFIFO(size=1,name="x17705").wtPort(bus_2106_s)
      val x17709 = ScalarFIFO(size=1,name="x17709").wtPort(bus_2110_s)
      val x17721 = ScalarFIFO(size=1,name="x17721").wtPort(bus_2122_s)
      val x17703 = ScalarFIFO(size=1,name="x17703").wtPort(bus_2104_s)
      val x17711 = ScalarFIFO(size=1,name="x17711").wtPort(bus_2112_s)
      val x17708 = ScalarFIFO(size=1,name="x17708").wtPort(bus_2109_s)
      Stage(operands=List(CU.load(x17709), CU.load(x17711), CU.load(x17721)), op=Mux, results=List(x17722))
      Stage(operands=List(CU.load(x17706), CU.load(x17708), x17722), op=Mux, results=List(x17723))
      Stage(operands=List(CU.load(x17703), CU.load(x17705), x17723), op=Mux, results=List(CU.scalarOut(bus_2125_s)))
    }
    val x17762_12 = Pipeline(name="x17762_12",parent=x17762) { implicit CU => 
      val x17693 = ScalarFIFO(size=1,name="x17693").wtPort(bus_2094_s)
      val x17724 = ScalarFIFO(size=1,name="x17724").wtPort(bus_2125_s)
      val x17702 = ScalarFIFO(size=1,name="x17702").wtPort(bus_2103_s)
      Stage(operands=List(CU.load(x17693), CU.load(x17702), CU.load(x17724)), op=Mux, results=List(CU.scalarOut(x17690_x17760_s)))
    }
    val x17773_0 = Pipeline(name="x17773_0",parent=x17914) { implicit CU => 
      val x17768 = ScalarFIFO(size=1,name="x17768").wtPort(x16952_1_s)
      val x17690 = ScalarBuffer(name="x17690").wtPort(x17690_x17760_s)
      val ctr45 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr46 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17765 = CounterChain(name = "x17765", ctr45, ctr46).iter(256)
      Stage(operands=List(CU.load(x17768), CU.load(x17690)), op=FixDiv, results=List(CU.scalarOut(x16952_x17772_s)))
    }
    val x17782_0 = Pipeline(name="x17782_0",parent=x17914) { implicit CU => 
      val x17691 = ScalarBuffer(name="x17691").wtPort(x17691_x17761_s)
      val x17777 = ScalarFIFO(size=1,name="x17777").wtPort(x16949_1_s)
      val ctr47 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17775 = CounterChain(name = "x17775", ctr47).iter(4)
      Stage(operands=List(CU.load(x17777), CU.load(x17691)), op=FixDiv, results=List(CU.scalarOut(x16949_x17781_s)))
    }
    val x17802_0 = Pipeline(name="x17802_0",parent=x17914) { implicit CU => 
      val x17793 = CU.temp(None)
      val x17799 = CU.temp(None)
      val x17783 = CU.temp(Some(0))
      val x17796 = ScalarFIFO(size=1,name="x17796").wtPort(x16953_2_s)
      val x17790 = ScalarFIFO(size=1,name="x17790").wtPort(x17110_0_s)
      val x17789 = ScalarFIFO(size=1,name="x17789").wtPort(x16953_3_s)
      val ctr48 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr49 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17786 = CounterChain(name = "x17786", ctr48, ctr49).iter(64)
      Stage(operands=List(CU.load(x17796), CU.load(x17796)), op=FixMul, results=List(x17799))
      Stage(operands=List(x17799, x17783), op=FixAdd, results=List(CU.scalarOut(x17783_x17801_s)))
      Stage(operands=List(CU.load(x17790), Const(0.01)), op=FixMul, results=List(x17793))
      Stage(operands=List(CU.load(x17789), x17793), op=FixSub, results=List(CU.scalarOut(x16953_x17795_s)))
    }
    val x17820_0 = Pipeline(name="x17820_0",parent=x17914) { implicit CU => 
      val x17803 = CU.temp(Some(0))
      val x17817 = CU.temp(None)
      val x17811 = CU.temp(None)
      val x17808 = ScalarFIFO(size=1,name="x17808").wtPort(x17107_0_s)
      val x17814 = ScalarFIFO(size=1,name="x17814").wtPort(x16950_2_s)
      val x17807 = ScalarFIFO(size=1,name="x17807").wtPort(x16950_3_s)
      val ctr50 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17805 = CounterChain(name = "x17805", ctr50).iter(1)
      Stage(operands=List(CU.load(x17814), CU.load(x17814)), op=FixMul, results=List(x17817))
      Stage(operands=List(x17817, x17803), op=FixAdd, results=List(CU.scalarOut(x17803_x17819_s)))
      Stage(operands=List(CU.load(x17808), Const(0.01)), op=FixMul, results=List(x17811))
      Stage(operands=List(CU.load(x17807), x17811), op=FixSub, results=List(CU.scalarOut(x16950_x17813_s)))
    }
    val x17893 = StreamController(name="x17893",parent=x17914) { implicit CU => 
      val x17893_unit = CounterChain(name = "x17893_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17893_0 = Pipeline(name="x17893_0",parent=x17893) { implicit CU => 
      val x17862 = CU.temp(None)
      val x17859 = CU.temp(None)
      val x17860 = CU.temp(None)
      val x17803 = ScalarBuffer(name="x17803").wtPort(x17803_x17819_s)
      Stage(operands=List(CU.load(x17803), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2146_s)))
      Stage(operands=List(CU.load(x17803), Const(1)), op=FixSub, results=List(x17859, CU.scalarOut(bus_2148_s)))
      Stage(operands=List(x17859, Const(2)), op=FixDiv, results=List(x17860))
      Stage(operands=List(Const(1), x17860), op=FixAdd, results=List(CU.scalarOut(bus_2150_s)))
      Stage(operands=List(x17859, x17859), op=FixMul, results=List(x17862, CU.scalarOut(bus_2151_s)))
      Stage(operands=List(x17862, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_2153_s)))
    }
    val x17893_1 = Pipeline(name="x17893_1",parent=x17893) { implicit CU => 
      val x17865 = CU.temp(None)
      val x17864 = CU.temp(None)
      val x17866 = CU.temp(None)
      val x17803 = ScalarBuffer(name="x17803").wtPort(x17803_x17819_s)
      val x17862 = ScalarFIFO(size=1,name="x17862").wtPort(bus_2151_s)
      val x17859 = ScalarFIFO(size=1,name="x17859").wtPort(bus_2148_s)
      val x17863 = ScalarFIFO(size=1,name="x17863").wtPort(bus_2153_s)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(bus_2150_s)
      Stage(operands=List(CU.load(x17861), CU.load(x17863)), op=FixSub, results=List(x17864))
      Stage(operands=List(CU.load(x17862), CU.load(x17859)), op=FixMul, results=List(x17865))
      Stage(operands=List(x17865, Const(16)), op=FixDiv, results=List(x17866))
      Stage(operands=List(x17864, x17866), op=FixAdd, results=List(CU.scalarOut(bus_2158_s)))
      Stage(operands=List(CU.load(x17803), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2160_s)))
      Stage(operands=List(CU.load(x17803), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_2162_s)))
    }
    val x17893_2 = Pipeline(name="x17893_2",parent=x17893) { implicit CU => 
      val x17872 = CU.temp(None)
      val x17869 = ScalarFIFO(size=1,name="x17869").wtPort(bus_2162_s)
      val x17803 = ScalarBuffer(name="x17803").wtPort(x17803_x17819_s)
      Stage(operands=List(CU.load(x17869), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2163_s)))
      Stage(operands=List(CU.load(x17803), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2165_s)))
      Stage(operands=List(CU.load(x17803), Const(0.08)), op=FixMul, results=List(x17872))
      Stage(operands=List(x17872, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2169_s)))
      Stage(operands=List(CU.load(x17803), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2171_s)))
      Stage(operands=List(CU.load(x17803), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_2173_s)))
    }
    val x17893_3 = Pipeline(name="x17893_3",parent=x17893) { implicit CU => 
      val x17878 = CU.temp(None)
      val x17875 = ScalarFIFO(size=1,name="x17875").wtPort(bus_2173_s)
      val x17803 = ScalarBuffer(name="x17803").wtPort(x17803_x17819_s)
      Stage(operands=List(CU.load(x17875), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2174_s)))
      Stage(operands=List(CU.load(x17803), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2176_s)))
      Stage(operands=List(CU.load(x17803), Const(0.008)), op=FixMul, results=List(x17878))
      Stage(operands=List(x17878, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2180_s)))
      Stage(operands=List(CU.load(x17803), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2182_s)))
      Stage(operands=List(CU.load(x17803), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_2184_s)))
    }
    val x17893_4 = Pipeline(name="x17893_4",parent=x17893) { implicit CU => 
      val x17882 = CU.temp(None)
      val x17883 = CU.temp(None)
      val x17885 = CU.temp(None)
      val x17884 = CU.temp(None)
      val x17803 = ScalarBuffer(name="x17803").wtPort(x17803_x17819_s)
      val x17879 = ScalarFIFO(size=1,name="x17879").wtPort(bus_2180_s)
      val x17881 = ScalarFIFO(size=1,name="x17881").wtPort(bus_2184_s)
      val x17877 = ScalarFIFO(size=1,name="x17877").wtPort(bus_2176_s)
      val x17880 = ScalarFIFO(size=1,name="x17880").wtPort(bus_2182_s)
      Stage(operands=List(CU.load(x17881), Const(60)), op=FixAdd, results=List(x17882))
      Stage(operands=List(CU.load(x17803), Const(2.0E-4)), op=FixMul, results=List(x17883))
      Stage(operands=List(x17883, Const(300)), op=FixAdd, results=List(x17884))
      Stage(operands=List(CU.load(x17880), x17882, x17884), op=Mux, results=List(x17885))
      Stage(operands=List(CU.load(x17877), CU.load(x17879), x17885), op=Mux, results=List(CU.scalarOut(bus_2192_s)))
    }
    val x17893_5 = Pipeline(name="x17893_5",parent=x17893) { implicit CU => 
      val x17887 = CU.temp(None)
      val x17888 = CU.temp(None)
      val x17876 = ScalarFIFO(size=1,name="x17876").wtPort(bus_2174_s)
      val x17868 = ScalarFIFO(size=1,name="x17868").wtPort(bus_2160_s)
      val x17874 = ScalarFIFO(size=1,name="x17874").wtPort(bus_2171_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(bus_2163_s)
      val x17873 = ScalarFIFO(size=1,name="x17873").wtPort(bus_2169_s)
      val x17871 = ScalarFIFO(size=1,name="x17871").wtPort(bus_2165_s)
      val x17886 = ScalarFIFO(size=1,name="x17886").wtPort(bus_2192_s)
      Stage(operands=List(CU.load(x17874), CU.load(x17876), CU.load(x17886)), op=Mux, results=List(x17887))
      Stage(operands=List(CU.load(x17871), CU.load(x17873), x17887), op=Mux, results=List(x17888))
      Stage(operands=List(CU.load(x17868), CU.load(x17870), x17888), op=Mux, results=List(CU.scalarOut(bus_2195_s)))
    }
    val x17893_6 = Pipeline(name="x17893_6",parent=x17893) { implicit CU => 
      val x17825 = CU.temp(None)
      val x17826 = CU.temp(None)
      val x17858 = ScalarFIFO(size=1,name="x17858").wtPort(bus_2146_s)
      val x17867 = ScalarFIFO(size=1,name="x17867").wtPort(bus_2158_s)
      val x17783 = ScalarBuffer(name="x17783").wtPort(x17783_x17801_s)
      val x17889 = ScalarFIFO(size=1,name="x17889").wtPort(bus_2195_s)
      Stage(operands=List(CU.load(x17858), CU.load(x17867), CU.load(x17889)), op=Mux, results=List(CU.scalarOut(x17822_x17892_s)))
      Stage(operands=List(CU.load(x17783), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2197_s)))
      Stage(operands=List(CU.load(x17783), Const(1)), op=FixSub, results=List(x17825, CU.scalarOut(bus_2198_s)))
      Stage(operands=List(x17825, Const(2)), op=FixDiv, results=List(x17826))
      Stage(operands=List(Const(1), x17826), op=FixAdd, results=List(CU.scalarOut(bus_2200_s)))
      Stage(operands=List(x17825, x17825), op=FixMul, results=List(CU.scalarOut(bus_2201_s)))
    }
    val x17893_7 = Pipeline(name="x17893_7",parent=x17893) { implicit CU => 
      val x17830 = CU.temp(None)
      val x17832 = CU.temp(None)
      val x17831 = CU.temp(None)
      val x17829 = CU.temp(None)
      val x17827 = ScalarFIFO(size=1,name="x17827").wtPort(bus_2200_s)
      val x17828 = ScalarFIFO(size=1,name="x17828").wtPort(bus_2201_s)
      val x17783 = ScalarBuffer(name="x17783").wtPort(x17783_x17801_s)
      val x17825 = ScalarFIFO(size=1,name="x17825").wtPort(bus_2198_s)
      Stage(operands=List(CU.load(x17828), Const(8)), op=FixDiv, results=List(x17829))
      Stage(operands=List(CU.load(x17827), x17829), op=FixSub, results=List(x17830))
      Stage(operands=List(CU.load(x17828), CU.load(x17825)), op=FixMul, results=List(x17831))
      Stage(operands=List(x17831, Const(16)), op=FixDiv, results=List(x17832))
      Stage(operands=List(x17830, x17832), op=FixAdd, results=List(CU.scalarOut(bus_2206_s)))
      Stage(operands=List(CU.load(x17783), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2207_s)))
    }
    val x17893_8 = Pipeline(name="x17893_8",parent=x17893) { implicit CU => 
      val x17838 = CU.temp(None)
      val x17835 = CU.temp(None)
      val x17783 = ScalarBuffer(name="x17783").wtPort(x17783_x17801_s)
      Stage(operands=List(CU.load(x17783), Const(0.22)), op=FixMul, results=List(x17835))
      Stage(operands=List(x17835, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2209_s)))
      Stage(operands=List(CU.load(x17783), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2210_s)))
      Stage(operands=List(CU.load(x17783), Const(0.08)), op=FixMul, results=List(x17838))
      Stage(operands=List(x17838, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2212_s)))
      Stage(operands=List(CU.load(x17783), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2213_s)))
    }
    val x17893_9 = Pipeline(name="x17893_9",parent=x17893) { implicit CU => 
      val x17841 = CU.temp(None)
      val x17844 = CU.temp(None)
      val x17783 = ScalarBuffer(name="x17783").wtPort(x17783_x17801_s)
      Stage(operands=List(CU.load(x17783), Const(0.028)), op=FixMul, results=List(x17841))
      Stage(operands=List(x17841, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2215_s)))
      Stage(operands=List(CU.load(x17783), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2216_s)))
      Stage(operands=List(CU.load(x17783), Const(0.008)), op=FixMul, results=List(x17844))
      Stage(operands=List(x17844, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2218_s)))
      Stage(operands=List(CU.load(x17783), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2219_s)))
    }
    val x17893_10 = Pipeline(name="x17893_10",parent=x17893) { implicit CU => 
      val x17847 = CU.temp(None)
      val x17850 = CU.temp(None)
      val x17848 = CU.temp(None)
      val x17849 = CU.temp(None)
      val x17851 = CU.temp(None)
      val x17845 = ScalarFIFO(size=1,name="x17845").wtPort(bus_2218_s)
      val x17783 = ScalarBuffer(name="x17783").wtPort(x17783_x17801_s)
      val x17846 = ScalarFIFO(size=1,name="x17846").wtPort(bus_2219_s)
      val x17843 = ScalarFIFO(size=1,name="x17843").wtPort(bus_2216_s)
      Stage(operands=List(CU.load(x17783), Const(0.003)), op=FixMul, results=List(x17847))
      Stage(operands=List(x17847, Const(60)), op=FixAdd, results=List(x17848))
      Stage(operands=List(CU.load(x17783), Const(2.0E-4)), op=FixMul, results=List(x17849))
      Stage(operands=List(x17849, Const(300)), op=FixAdd, results=List(x17850))
      Stage(operands=List(CU.load(x17846), x17848, x17850), op=Mux, results=List(x17851))
      Stage(operands=List(CU.load(x17843), CU.load(x17845), x17851), op=Mux, results=List(CU.scalarOut(bus_2225_s)))
    }
    val x17893_11 = Pipeline(name="x17893_11",parent=x17893) { implicit CU => 
      val x17854 = CU.temp(None)
      val x17853 = CU.temp(None)
      val x17837 = ScalarFIFO(size=1,name="x17837").wtPort(bus_2210_s)
      val x17840 = ScalarFIFO(size=1,name="x17840").wtPort(bus_2213_s)
      val x17842 = ScalarFIFO(size=1,name="x17842").wtPort(bus_2215_s)
      val x17834 = ScalarFIFO(size=1,name="x17834").wtPort(bus_2207_s)
      val x17852 = ScalarFIFO(size=1,name="x17852").wtPort(bus_2225_s)
      val x17836 = ScalarFIFO(size=1,name="x17836").wtPort(bus_2209_s)
      val x17839 = ScalarFIFO(size=1,name="x17839").wtPort(bus_2212_s)
      Stage(operands=List(CU.load(x17840), CU.load(x17842), CU.load(x17852)), op=Mux, results=List(x17853))
      Stage(operands=List(CU.load(x17837), CU.load(x17839), x17853), op=Mux, results=List(x17854))
      Stage(operands=List(CU.load(x17834), CU.load(x17836), x17854), op=Mux, results=List(CU.scalarOut(bus_2228_s)))
    }
    val x17893_12 = Pipeline(name="x17893_12",parent=x17893) { implicit CU => 
      val x17824 = ScalarFIFO(size=1,name="x17824").wtPort(bus_2197_s)
      val x17833 = ScalarFIFO(size=1,name="x17833").wtPort(bus_2206_s)
      val x17855 = ScalarFIFO(size=1,name="x17855").wtPort(bus_2228_s)
      Stage(operands=List(CU.load(x17824), CU.load(x17833), CU.load(x17855)), op=Mux, results=List(CU.scalarOut(x17821_x17891_s)))
    }
    val x17904_0 = Pipeline(name="x17904_0",parent=x17914) { implicit CU => 
      val x17899 = ScalarFIFO(size=1,name="x17899").wtPort(x16953_1_s)
      val x17821 = ScalarBuffer(name="x17821").wtPort(x17821_x17891_s)
      val ctr51 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr52 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17896 = CounterChain(name = "x17896", ctr51, ctr52).iter(64)
      Stage(operands=List(CU.load(x17899), CU.load(x17821)), op=FixDiv, results=List(CU.scalarOut(x16953_x17903_s)))
    }
    val x17913_0 = Pipeline(name="x17913_0",parent=x17914) { implicit CU => 
      val x17822 = ScalarBuffer(name="x17822").wtPort(x17822_x17892_s)
      val x17908 = ScalarFIFO(size=1,name="x17908").wtPort(x16950_1_s)
      val ctr53 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17906 = CounterChain(name = "x17906", ctr53).iter(1)
      Stage(operands=List(CU.load(x17908), CU.load(x17822)), op=FixDiv, results=List(CU.scalarOut(x16950_x17912_s)))
    }
    val x17924_0 = Pipeline(name="x17924_0",parent=x18081) { implicit CU => 
      val x17921 = ScalarFIFO(size=1,name="x17921").wtPort(x16953_0_s)
      val ctr54 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17917 = CounterChain(name = "x17917", ctr54, ctr55).iter(192)
      Stage(operands=List(CU.ctr(x17917(0)), Const(3)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x17921)), op=Bypass, results=List(CU.scalarOut(x16954_x17923_s)))
    }
    val x17948 = StreamController(name="x17948",parent=x18081) { implicit CU => 
      val x17948_unit = CounterChain(name = "x17948_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17944 = Sequential(name="x17944",parent=x17948) { implicit CU => 
      val x17944_unit = CounterChain(name = "x17944_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17936_0 = Pipeline(name="x17936_0",parent=x17944) { implicit CU => 
      val x17929 = CU.temp(None)
      val x17931 = ScalarBuffer(name="x17931").wtPort(biases1_dram_da)
      val x17936_unit = CounterChain(name = "x17936_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17929))
      Stage(operands=List(x17929, CU.load(x17931)), op=FixAdd, results=List(CU.scalarOut(x17925_b18478_x17935_b18480_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17925_b18479_x17935_b18481_s)))
    }
    val x17943_0 = Pipeline(name="x17943_0",parent=x17944) { implicit CU => 
      val x17939 = ScalarFIFO(size=1,name="x17939").wtPort(x16948_0_s)
      val ctr56 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17938 = CounterChain(name = "x17938", ctr56).iter(4)
      Stage(operands=List(CU.load(x17939)), op=Bypass, results=List(CU.vecOut(x17926_x17942_v)))
    }
    val x17945 = MemoryController(name="x17945",parent=x17948,offchip=biases1_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17925_b18478 = ScalarFIFO(size=1,name="offset").wtPort(x17925_b18478_x17935_b18480_s)
      val x17926 = VectorFIFO(size=1,name="data").wtPort(x17926_x17942_v)
      val x17925_b18479 = ScalarFIFO(size=1,name="size").wtPort(x17925_b18479_x17935_b18481_s)
    }
    val x17972 = StreamController(name="x17972",parent=x18081) { implicit CU => 
      val x17972_unit = CounterChain(name = "x17972_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17968 = Sequential(name="x17968",parent=x17972) { implicit CU => 
      val x17968_unit = CounterChain(name = "x17968_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17960_0 = Pipeline(name="x17960_0",parent=x17968) { implicit CU => 
      val x17953 = CU.temp(None)
      val x17955 = ScalarBuffer(name="x17955").wtPort(biases2_dram_da)
      val x17960_unit = CounterChain(name = "x17960_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17953))
      Stage(operands=List(x17953, CU.load(x17955)), op=FixAdd, results=List(CU.scalarOut(x17949_b18482_x17959_b18484_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17949_b18483_x17959_b18485_s)))
    }
    val x17967_0 = Pipeline(name="x17967_0",parent=x17968) { implicit CU => 
      val x17963 = ScalarFIFO(size=1,name="x17963").wtPort(x16949_0_s)
      val ctr57 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17962 = CounterChain(name = "x17962", ctr57).iter(4)
      Stage(operands=List(CU.load(x17963)), op=Bypass, results=List(CU.vecOut(x17950_x17966_v)))
    }
    val x17969 = MemoryController(name="x17969",parent=x17972,offchip=biases2_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17949_b18483 = ScalarFIFO(size=1,name="size").wtPort(x17949_b18483_x17959_b18485_s)
      val x17950 = VectorFIFO(size=1,name="data").wtPort(x17950_x17966_v)
      val x17949_b18482 = ScalarFIFO(size=1,name="offset").wtPort(x17949_b18482_x17959_b18484_s)
    }
    val x17996 = StreamController(name="x17996",parent=x18081) { implicit CU => 
      val x17996_unit = CounterChain(name = "x17996_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17992 = Sequential(name="x17992",parent=x17996) { implicit CU => 
      val x17992_unit = CounterChain(name = "x17992_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17984_0 = Pipeline(name="x17984_0",parent=x17992) { implicit CU => 
      val x17977 = CU.temp(None)
      val x17979 = ScalarBuffer(name="x17979").wtPort(biases3_dram_da)
      val x17984_unit = CounterChain(name = "x17984_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17977))
      Stage(operands=List(x17977, CU.load(x17979)), op=FixAdd, results=List(CU.scalarOut(x17973_b18486_x17983_b18488_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x17973_b18487_x17983_b18489_s)))
    }
    val x17991_0 = Pipeline(name="x17991_0",parent=x17992) { implicit CU => 
      val x17987 = ScalarFIFO(size=1,name="x17987").wtPort(x16950_0_s)
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x17986 = CounterChain(name = "x17986", ctr58).iter(1)
      Stage(operands=List(CU.load(x17987)), op=Bypass, results=List(CU.vecOut(x17974_x17990_v)))
    }
    val x17993 = MemoryController(name="x17993",parent=x17996,offchip=biases3_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17973_b18487 = ScalarFIFO(size=1,name="size").wtPort(x17973_b18487_x17983_b18489_s)
      val x17973_b18486 = ScalarFIFO(size=1,name="offset").wtPort(x17973_b18486_x17983_b18488_s)
      val x17974 = VectorFIFO(size=1,name="data").wtPort(x17974_x17990_v)
    }
    val x18026 = StreamController(name="x18026",parent=x18081) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val x17998 = CounterChain(name = "x17998", ctr59).iter(13)
    }
    val x18022 = Sequential(name="x18022",parent=x18026) { implicit CU => 
      val x18022_unit = CounterChain(name = "x18022_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x18013_0 = Pipeline(name="x18013_0",parent=x18022) { implicit CU => 
      val x18003 = CU.temp(None)
      val x18006 = CU.temp(None)
      val x18005 = CU.temp(None)
      val x18008 = ScalarBuffer(name="x18008").wtPort(weights1_dram_da)
      val x17998 = CounterChain.copy("x18026", "x17998")
      val x18013_unit = CounterChain(name = "x18013_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17998(0)), Const(6)), op=FixSla, results=List(x18003))
      Stage(operands=List(x18003, Const(0)), op=FixAdd, results=List(x18005))
      Stage(operands=List(x18005, Const(2)), op=FixSla, results=List(x18006))
      Stage(operands=List(x18006, CU.load(x18008)), op=FixAdd, results=List(CU.scalarOut(x17999_b18490_x18012_b18492_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17999_b18491_x18012_b18493_s)))
    }
    val x18021_0 = Pipeline(name="x18021_0",parent=x18022) { implicit CU => 
      val x18017 = ScalarFIFO(size=1,name="x18017").wtPort(x16951_0_s)
      val ctr60 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x18015 = CounterChain(name = "x18015", ctr60).iter(4)
      Stage(operands=List(CU.load(x18017)), op=Bypass, results=List(CU.vecOut(x18000_x18020_v)))
    }
    val x18023 = MemoryController(name="x18023",parent=x18026,offchip=weights1_dram_oc, mctpe=TileStore) { implicit CU => 
      val x18000 = VectorFIFO(size=1,name="data").wtPort(x18000_x18020_v)
      val x17999_b18490 = ScalarFIFO(size=1,name="offset").wtPort(x17999_b18490_x18012_b18492_s)
      val x17999_b18491 = ScalarFIFO(size=1,name="size").wtPort(x17999_b18491_x18012_b18493_s)
    }
    val x18056 = StreamController(name="x18056",parent=x18081) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x18028 = CounterChain(name = "x18028", ctr61).iter(64)
    }
    val x18052 = Sequential(name="x18052",parent=x18056) { implicit CU => 
      val x18052_unit = CounterChain(name = "x18052_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x18043_0 = Pipeline(name="x18043_0",parent=x18052) { implicit CU => 
      val x18033 = CU.temp(None)
      val x18035 = CU.temp(None)
      val x18036 = CU.temp(None)
      val x18038 = ScalarBuffer(name="x18038").wtPort(weights2_dram_da)
      val x18028 = CounterChain.copy("x18056", "x18028")
      val x18043_unit = CounterChain(name = "x18043_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x18028(0)), Const(6)), op=FixSla, results=List(x18033))
      Stage(operands=List(x18033, Const(0)), op=FixAdd, results=List(x18035))
      Stage(operands=List(x18035, Const(2)), op=FixSla, results=List(x18036))
      Stage(operands=List(x18036, CU.load(x18038)), op=FixAdd, results=List(CU.scalarOut(x18029_b18496_x18042_b18498_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x18029_b18497_x18042_b18499_s)))
    }
    val x18051_0 = Pipeline(name="x18051_0",parent=x18052) { implicit CU => 
      val x18047 = ScalarFIFO(size=1,name="x18047").wtPort(x16952_0_s)
      val ctr62 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x18045 = CounterChain(name = "x18045", ctr62).iter(4)
      Stage(operands=List(CU.load(x18047)), op=Bypass, results=List(CU.vecOut(x18030_x18050_v)))
    }
    val x18053 = MemoryController(name="x18053",parent=x18056,offchip=weights2_dram_oc, mctpe=TileStore) { implicit CU => 
      val x18029_b18496 = ScalarFIFO(size=1,name="offset").wtPort(x18029_b18496_x18042_b18498_s)
      val x18030 = VectorFIFO(size=1,name="data").wtPort(x18030_x18050_v)
      val x18029_b18497 = ScalarFIFO(size=1,name="size").wtPort(x18029_b18497_x18042_b18499_s)
    }
    val x18080 = StreamController(name="x18080",parent=x18081) { implicit CU => 
      val x18080_unit = CounterChain(name = "x18080_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x18076 = Sequential(name="x18076",parent=x18080) { implicit CU => 
      val x18076_unit = CounterChain(name = "x18076_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x18068_0 = Pipeline(name="x18068_0",parent=x18076) { implicit CU => 
      val x18061 = CU.temp(None)
      val x18063 = ScalarBuffer(name="x18063").wtPort(weights3_dram_da)
      val x18068_unit = CounterChain(name = "x18068_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x18061))
      Stage(operands=List(x18061, CU.load(x18063)), op=FixAdd, results=List(CU.scalarOut(x18057_b18502_x18067_b18504_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x18057_b18503_x18067_b18505_s)))
    }
    val x18075_0 = Pipeline(name="x18075_0",parent=x18076) { implicit CU => 
      val x18071 = ScalarFIFO(size=1,name="x18071").wtPort(x16954_0_s)
      val ctr63 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x18070 = CounterChain(name = "x18070", ctr63).iter(12)
      Stage(operands=List(CU.load(x18071)), op=Bypass, results=List(CU.vecOut(x18058_x18074_v)))
    }
    val x18077 = MemoryController(name="x18077",parent=x18080,offchip=weights3_dram_oc, mctpe=TileStore) { implicit CU => 
      val x18057_b18502 = ScalarFIFO(size=1,name="offset").wtPort(x18057_b18502_x18067_b18504_s)
      val x18058 = VectorFIFO(size=1,name="data").wtPort(x18058_x18074_v)
      val x18057_b18503 = ScalarFIFO(size=1,name="size").wtPort(x18057_b18503_x18067_b18505_s)
    }
    
  }
}
