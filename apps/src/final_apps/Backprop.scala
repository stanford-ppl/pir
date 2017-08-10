import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Backprop extends PIRApp {
  def main(top:Top) = {
    val x17082_b18352_x17108_b18360_s = Scalar("x17082_b18352_x17108_b18360")
    val bus_1957_s = Scalar("bus_1957")
    val bus_2163_s = Scalar("bus_2163")
    val bus_2225_s = Scalar("bus_2225")
    val bus_2073_s = Scalar("bus_2073")
    val x16910_1_s = Scalar("x16910_1")
    val x17984_x18004_v = Vector("x17984_x18004")
    val weights2_dram_oc = OffChip("weights2_dram")
    val bus_1730_s = Scalar("bus_1730")
    val x16910_x17730_s = Scalar("x16910_x17730")
    val x17929_b18438_x17939_b18440_s = Scalar("x17929_b18438_x17939_b18440")
    val bus_2047_s = Scalar("bus_2047")
    val weights3_dram_oc = OffChip("weights3_dram")
    val x16907_2_s = Scalar("x16907_2")
    val bus_1828_v = Vector("bus_1828")
    val bus_1954_s = Scalar("bus_1954")
    val bus_1989_s = Scalar("bus_1989")
    val bus_2012_s = Scalar("bus_2012")
    val x17883_b18431_x17893_b18433_s = Scalar("x17883_b18431_x17893_b18433")
    val x18011_x18027_v = Vector("x18011_x18027")
    val bus_2071_s = Scalar("bus_2071")
    val x17070_2_s = Scalar("x17070_2")
    val x16909_0_s = Scalar("x16909_0")
    val x17499_x17515_s = Scalar("x17499_x17515")
    val bus_2066_s = Scalar("bus_2066")
    val x17954_b18443_x17967_b18445_s = Scalar("x17954_b18443_x17967_b18445")
    val bus_2009_s = Scalar("bus_2009")
    val x17059_x17238_s = Scalar("x17059_x17238")
    val x16997_b18338_x17009_b18340_s = Scalar("x16997_b18338_x17009_b18340")
    val x16911_3_s = Scalar("x16911_3")
    val bus_1836_v = Vector("bus_1836")
    val x16914_x16924_data_v = Vector("x16914_x16924_data")
    val bus_2089_s = Scalar("bus_2089")
    val bus_2003_s = Scalar("bus_2003")
    val x17065_1_s = Scalar("x17065_1")
    val x17929_b18439_x17939_b18441_s = Scalar("x17929_b18439_x17939_b18441")
    val x17056_x17241_s = Scalar("x17056_x17241")
    val bus_1870_v = Vector("bus_1870")
    val bus_2176_s = Scalar("bus_2176")
    val bus_2198_s = Scalar("bus_2198")
    val x16909_x17599_s = Scalar("x16909_x17599")
    val x17954_b18442_x17967_b18444_s = Scalar("x17954_b18442_x17967_b18444")
    val x16908_4_s = Scalar("x16908_4")
    val bus_1889_v = Vector("bus_1889")
    val bus_1765_s = Scalar("bus_1765")
    val bus_2010_s = Scalar("bus_2010")
    val x17315_x17343_s = Scalar("x17315_x17343")
    val x16908_x16967_s = Scalar("x16908_x16967")
    val x17059_0_s = Scalar("x17059_0")
    val x17065_x17386_s = Scalar("x17065_x17386")
    val x17517_x17587_s = Scalar("x17517_x17587")
    val bus_2218_s = Scalar("bus_2218")
    val x16910_2_s = Scalar("x16910_2")
    val x17057_0_s = Scalar("x17057_0")
    val training_targets_dram_oc = OffChip("training_targets_dram")
    val x16908_x17870_s = Scalar("x16908_x17870")
    val x17070_1_s = Scalar("x17070_1")
    val x16933_x16943_data_v = Vector("x16933_x16943_data")
    val x17057_2_s = Scalar("x17057_2")
    val bus_1877_v = Vector("bus_1877")
    val bus_1940_s = Scalar("bus_1940")
    val bus_2019_s = Scalar("bus_2019")
    val x16910_5_s = Scalar("x16910_5")
    val bus_2113_s = Scalar("bus_2113")
    val bus_2228_s = Scalar("bus_2228")
    val weights3_dram_da = DRAMAddress("weights3_dram", "weights3_dram")
    val x16932_b18324_x16941_b18326_s = Scalar("x16932_b18324_x16941_b18326")
    val x16910_0_s = Scalar("x16910_0")
    val x17070_0_s = Scalar("x17070_0")
    val bus_1952_s = Scalar("bus_1952")
    val bus_2180_s = Scalar("bus_2180")
    val bus_2160_s = Scalar("bus_2160")
    val x16910_x17017_s = Scalar("x16910_x17017")
    val x17057_x17277_s = Scalar("x17057_x17277")
    val bus_2001_s = Scalar("bus_2001")
    val x17054_x17078_s = Scalar("x17054_x17078")
    val x17150_b18367_x17176_b18375_s = Scalar("x17150_b18367_x17176_b18375")
    val x17780_x17850_s = Scalar("x17780_x17850")
    val bus_1855_v = Vector("bus_1855")
    val x17062_x17135_s = Scalar("x17062_x17135")
    val x17081_b18351_x17106_b18359_s = Scalar("x17081_b18351_x17106_b18359")
    val x16909_1_s = Scalar("x16909_1")
    val bus_1879_v = Vector("bus_1879")
    val bus_1760_s = Scalar("bus_1760")
    val bus_2158_s = Scalar("bus_2158")
    val x16907_x17739_s = Scalar("x16907_x17739")
    val x16997_b18339_x17009_b18341_s = Scalar("x16997_b18339_x17009_b18341")
    val x17053_x17077_s = Scalar("x17053_x17077")
    val x16906_2_s = Scalar("x16906_2")
    val bus_2000_s = Scalar("bus_2000")
    val x16908_0_s = Scalar("x16908_0")
    val bus_2212_s = Scalar("bus_2212")
    val bus_2146_s = Scalar("bus_2146")
    val weights2_dram_da = DRAMAddress("weights2_dram", "weights2_dram")
    val x17063_0_s = Scalar("x17063_0")
    val x17741_x17759_s = Scalar("x17741_x17759")
    val bus_2068_s = Scalar("bus_2068")
    val bus_2151_s = Scalar("bus_2151")
    val x17907_x17923_v = Vector("x17907_x17923")
    val bus_1866_v = Vector("bus_1866")
    val x17064_0_s = Scalar("x17064_0")
    val biases2_dram_oc = OffChip("biases2_dram")
    val x17069_x17465_s = Scalar("x17069_x17465")
    val x16909_2_s = Scalar("x16909_2")
    val bus_1991_s = Scalar("bus_1991")
    val bus_1995_s = Scalar("bus_1995")
    val x17056_1_s = Scalar("x17056_1")
    val x17057_x17264_s = Scalar("x17057_x17264")
    val bus_2162_s = Scalar("bus_2162")
    val x16911_4_s = Scalar("x16911_4")
    val x17955_x17975_v = Vector("x17955_x17975")
    val x17209_x17222_s = Scalar("x17209_x17222")
    val bus_2173_s = Scalar("bus_2173")
    val bus_2013_s = Scalar("bus_2013")
    val x17058_0_s = Scalar("x17058_0")
    val x17149_b18364_x17174_b18372_s = Scalar("x17149_b18364_x17174_b18372")
    val x16912_1_s = Scalar("x16912_1")
    val x17052_x17076_s = Scalar("x17052_x17076")
    val x17906_b18434_x17916_b18436_s = Scalar("x17906_b18434_x17916_b18436")
    val bus_1945_s = Scalar("bus_1945")
    val x17062_0_s = Scalar("x17062_0")
    val bus_1758_s = Scalar("bus_1758")
    val x16910_x17622_s = Scalar("x16910_x17622")
    val bus_1959_s = Scalar("bus_1959")
    val x17649_x17719_s = Scalar("x17649_x17719")
    val x17884_x17900_v = Vector("x17884_x17900")
    val bus_2206_s = Scalar("bus_2206")
    val x17067_0_s = Scalar("x17067_0")
    val bus_2122_s = Scalar("bus_2122")
    val x17402_x17419_s = Scalar("x17402_x17419")
    val x17067_x17437_s = Scalar("x17067_x17437")
    val bus_1944_s = Scalar("bus_1944")
    val bus_2048_s = Scalar("bus_2048")
    val bus_2062_s = Scalar("bus_2062")
    val x16952_x16962_data_v = Vector("x16952_x16962_data")
    val x17058_x17300_s = Scalar("x17058_x17300")
    val x17057_1_s = Scalar("x17057_1")
    val bus_2104_s = Scalar("bus_2104")
    val x17082_b18353_x17108_b18361_s = Scalar("x17082_b18353_x17108_b18361")
    val x16972_b18332_x16984_b18334_s = Scalar("x16972_b18332_x16984_b18334")
    val x16908_2_s = Scalar("x16908_2")
    val bus_2184_s = Scalar("bus_2184")
    val x17479_x17497_s = Scalar("x17479_x17497")
    val bus_1986_s = Scalar("bus_1986")
    val bus_2165_s = Scalar("bus_2165")
    val bus_2195_s = Scalar("bus_2195")
    val x17061_x17310_s = Scalar("x17061_x17310")
    val x17081_b18350_x17106_b18358_s = Scalar("x17081_b18350_x17106_b18358")
    val x17983_b18449_x17996_b18451_s = Scalar("x17983_b18449_x17996_b18451")
    val bus_1832_v = Vector("bus_1832")
    val bus_2097_s = Scalar("bus_2097")
    val x17518_x17588_s = Scalar("x17518_x17588")
    val x16911_1_s = Scalar("x16911_1")
    val x16910_3_s = Scalar("x16910_3")
    val x17060_0_s = Scalar("x17060_0")
    val x17066_0_s = Scalar("x17066_0")
    val bus_1965_s = Scalar("bus_1965")
    val x16907_1_s = Scalar("x16907_1")
    val bus_1947_s = Scalar("bus_1947")
    val bus_2050_s = Scalar("bus_2050")
    val bus_2213_s = Scalar("bus_2213")
    val bus_2109_s = Scalar("bus_2109")
    val bus_1994_s = Scalar("bus_1994")
    val x17070_x17425_s = Scalar("x17070_x17425")
    val bus_2059_s = Scalar("bus_2059")
    val x16908_x17771_s = Scalar("x16908_x17771")
    val x17082_b18354_x17108_b18362_s = Scalar("x17082_b18354_x17108_b18362")
    val bus_1853_v = Vector("bus_1853")
    val bus_1970_s = Scalar("bus_1970")
    val x16951_b18328_x16960_b18330_s = Scalar("x16951_b18328_x16960_b18330")
    val x17069_1_s = Scalar("x17069_1")
    val x16912_x17036_s = Scalar("x16912_x17036")
    val bus_1967_s = Scalar("bus_1967")
    val x16909_x17491_s = Scalar("x16909_x17491")
    val bus_2200_s = Scalar("bus_2200")
    val x16911_2_s = Scalar("x16911_2")
    val biases3_dram_da = DRAMAddress("biases3_dram", "biases3_dram")
    val x16912_0_s = Scalar("x16912_0")
    val bus_1850_v = Vector("bus_1850")
    val bus_2095_s = Scalar("bus_2095")
    val x17139_x17146_s = Scalar("x17139_x17146")
    val weights1_dram_oc = OffChip("weights1_dram")
    val x16913_b18320_x16922_b18322_s = Scalar("x16913_b18320_x16922_b18322")
    val x17083_x17110_data_v = Vector("x17083_x17110_data")
    val x16910_4_s = Scalar("x16910_4")
    val x17630_x17646_s = Scalar("x17630_x17646")
    val x17779_x17849_s = Scalar("x17779_x17849")
    val x17883_b18430_x17893_b18432_s = Scalar("x17883_b18430_x17893_b18432")
    val bus_1842_v = Vector("bus_1842")
    val x16908_3_s = Scalar("x16908_3")
    val bus_1884_v = Vector("bus_1884")
    val x17065_0_s = Scalar("x17065_0")
    val bus_1974_s = Scalar("bus_1974")
    val bus_2192_s = Scalar("bus_2192")
    val bus_2057_s = Scalar("bus_2057")
    val x17021_x17031_data_v = Vector("x17021_x17031_data")
    val biases1_dram_oc = OffChip("biases1_dram")
    val x17058_1_s = Scalar("x17058_1")
    val x16907_x17640_s = Scalar("x16907_x17640")
    val bus_1756_s = Scalar("bus_1756")
    val bus_2077_s = Scalar("bus_2077")
    val bus_2110_s = Scalar("bus_2110")
    val bus_2094_s = Scalar("bus_2094")
    val x16906_3_s = Scalar("x16906_3")
    val bus_2112_s = Scalar("bus_2112")
    val training_data_dram_da = DRAMAddress("training_data_dram", "training_data_dram")
    val bus_1762_s = Scalar("bus_1762")
    val bus_1876_v = Vector("bus_1876")
    val x17058_2_s = Scalar("x17058_2")
    val x16908_1_s = Scalar("x16908_1")
    val bus_2107_s = Scalar("bus_2107")
    val bus_2103_s = Scalar("bus_2103")
    val x16907_x16948_s = Scalar("x16907_x16948")
    val x17055_x17079_s = Scalar("x17055_x17079")
    val bus_2207_s = Scalar("bus_2207")
    val bus_2004_s = Scalar("bus_2004")
    val x17906_b18435_x17916_b18437_s = Scalar("x17906_b18435_x17916_b18437")
    val bus_2209_s = Scalar("bus_2209")
    val bus_2216_s = Scalar("bus_2216")
    val x17150_b18365_x17176_b18373_s = Scalar("x17150_b18365_x17176_b18373")
    val x17064_x17372_s = Scalar("x17064_x17372")
    val bus_2106_s = Scalar("bus_2106")
    val bus_2081_s = Scalar("bus_2081")
    val x17056_x17228_s = Scalar("x17056_x17228")
    val bus_1845_v = Vector("bus_1845")
    val x16972_b18333_x16984_b18335_s = Scalar("x16972_b18333_x16984_b18335")
    val bus_2150_s = Scalar("bus_2150")
    val bus_2022_s = Scalar("bus_2022")
    val x17983_b18448_x17996_b18450_s = Scalar("x17983_b18448_x17996_b18450")
    val bus_1843_v = Vector("bus_1843")
    val bus_1942_s = Scalar("bus_1942")
    val bus_1968_s = Scalar("bus_1968")
    val bus_2079_s = Scalar("bus_2079")
    val x17140_x17147_s = Scalar("x17140_x17147")
    val bus_2210_s = Scalar("bus_2210")
    val x17061_0_s = Scalar("x17061_0")
    val x16907_3_s = Scalar("x16907_3")
    val x17062_1_s = Scalar("x17062_1")
    val x17058_x17313_s = Scalar("x17058_x17313")
    val x17068_x17397_s = Scalar("x17068_x17397")
    val x17150_b18366_x17176_b18374_s = Scalar("x17150_b18366_x17176_b18374")
    val bus_2055_s = Scalar("bus_2055")
    val bus_1864_v = Vector("bus_1864")
    val x17151_x17178_data_s = Scalar("x17151_x17178_data")
    val bus_1732_s = Scalar("bus_1732")
    val x16906_x16929_s = Scalar("x16906_x16929")
    val x16907_4_s = Scalar("x16907_4")
    val x16906_1_s = Scalar("x16906_1")
    val x16906_0_s = Scalar("x16906_0")
    val x17149_b18363_x17174_b18371_s = Scalar("x17149_b18363_x17174_b18371")
    val x16973_x16986_data_v = Vector("x16973_x16986_data")
    val x17056_2_s = Scalar("x17056_2")
    val x16998_x17011_data_v = Vector("x16998_x17011_data")
    val x17069_0_s = Scalar("x17069_0")
    val x18010_b18455_x18020_b18457_s = Scalar("x18010_b18455_x18020_b18457")
    val bus_2092_s = Scalar("bus_2092")
    val x17930_x17946_v = Vector("x17930_x17946")
    val training_targets_dram_da = DRAMAddress("training_targets_dram", "training_targets_dram")
    val bus_1830_v = Vector("bus_1830")
    val bus_1956_s = Scalar("bus_1956")
    val x17065_2_s = Scalar("x17065_2")
    val x16911_x17753_s = Scalar("x16911_x17753")
    val bus_2182_s = Scalar("bus_2182")
    val x17245_x17258_s = Scalar("x17245_x17258")
    val bus_2148_s = Scalar("bus_2148")
    val biases1_dram_da = DRAMAddress("biases1_dram", "biases1_dram")
    val bus_1887_v = Vector("bus_1887")
    val x18010_b18454_x18020_b18456_s = Scalar("x18010_b18454_x18020_b18456")
    val x16911_0_s = Scalar("x16911_0")
    val iters_argin = ArgIn("iters")
    val bus_2169_s = Scalar("bus_2169")
    val bus_1734_s = Scalar("bus_1734")
    val bus_2171_s = Scalar("bus_2171")
    val bus_2219_s = Scalar("bus_2219")
    val x16906_4_s = Scalar("x16906_4")
    val bus_1978_s = Scalar("bus_1978")
    val x16909_4_s = Scalar("x16909_4")
    val x17648_x17718_s = Scalar("x17648_x17718")
    val x17060_x17274_s = Scalar("x17060_x17274")
    val biases3_dram_oc = OffChip("biases3_dram")
    val x17020_b18344_x17029_b18346_s = Scalar("x17020_b18344_x17029_b18346")
    val x17442_x17459_s = Scalar("x17442_x17459")
    val x17066_x17477_s = Scalar("x17066_x17477")
    val x16909_3_s = Scalar("x16909_3")
    val bus_2045_s = Scalar("bus_2045")
    val bus_2201_s = Scalar("bus_2201")
    val bus_1963_s = Scalar("bus_1963")
    val x17056_0_s = Scalar("x17056_0")
    val bus_1737_s = Scalar("bus_1737")
    val bus_2215_s = Scalar("bus_2215")
    val bus_2007_s = Scalar("bus_2007")
    val x16906_x17509_s = Scalar("x16906_x17509")
    val x16906_x17608_s = Scalar("x16906_x17608")
    val bus_2125_s = Scalar("bus_2125")
    val bus_2197_s = Scalar("bus_2197")
    val bus_2115_s = Scalar("bus_2115")
    val x17610_x17628_s = Scalar("x17610_x17628")
    val x17068_0_s = Scalar("x17068_0")
    val x16911_5_s = Scalar("x16911_5")
    val bus_1862_v = Vector("bus_1862")
    val x16913_b18321_x16922_b18323_s = Scalar("x16913_b18321_x16922_b18323")
    val bus_1992_s = Scalar("bus_1992")
    val bus_2116_s = Scalar("bus_2116")
    val bus_2174_s = Scalar("bus_2174")
    val bus_1976_s = Scalar("bus_1976")
    val bus_2098_s = Scalar("bus_2098")
    val biases2_dram_da = DRAMAddress("biases2_dram", "biases2_dram")
    val training_data_dram_oc = OffChip("training_data_dram")
    val bus_2060_s = Scalar("bus_2060")
    val bus_2153_s = Scalar("bus_2153")
    val bus_1728_s = Scalar("bus_1728")
    val x17020_b18345_x17029_b18347_s = Scalar("x17020_b18345_x17029_b18347")
    val bus_2006_s = Scalar("bus_2006")
    val bus_2043_s = Scalar("bus_2043")
    val x17281_x17294_s = Scalar("x17281_x17294")
    val weights1_dram_da = DRAMAddress("weights1_dram", "weights1_dram")
    val x17761_x17777_s = Scalar("x17761_x17777")
    val x16907_0_s = Scalar("x16907_0")
    val bus_2070_s = Scalar("bus_2070")
    val x16932_b18325_x16941_b18327_s = Scalar("x16932_b18325_x16941_b18327")
    val x16911_x17861_s = Scalar("x16911_x17861")
    val x16909_x16992_s = Scalar("x16909_x16992")
    val x16951_b18329_x16960_b18331_s = Scalar("x16951_b18329_x16960_b18331")
    val x18033 = Sequential(name="x18033",parent=top) { implicit CU => 
      val x18033_unit = CounterChain(name = "x18033_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16906_dsp0 = MemoryPipeline(name="x16906_dsp0",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609_0", "x17602")
      val x17896 = CounterChain.copy("x17901_0", "x17896")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_0_s).rdAddr(x17896(0)).wtAddr(x17602(0))
    }
    val x16906_dsp1 = MemoryPipeline(name="x16906_dsp1",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609_0", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_1_s).rdAddr(x17602(0)).wtAddr(x17602(0))
    }
    val x16906_dsp2 = MemoryPipeline(name="x16906_dsp2",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17501 = CounterChain.copy("x17516_0", "x17501")
      val x17602 = CounterChain.copy("x17609_0", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_2_s).rdAddr(x17501(0)).wtAddr(x17602(0))
    }
    val x16906_dsp3 = MemoryPipeline(name="x16906_dsp3",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17501 = CounterChain.copy("x17516_0", "x17501")
      val x17602 = CounterChain.copy("x17609_0", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_3_s).rdAddr(x17501(0)).wtAddr(x17602(0))
    }
    val x16906_dsp4 = MemoryPipeline(name="x16906_dsp4",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609_0", "x17602")
      val x17208 = CounterChain.copy("x17230", "x17208")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_4_s).rdAddr(x17208(0)).wtAddr(x17602(0))
    }
    val x16907_dsp0 = MemoryPipeline(name="x16907_dsp0",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740_0", "x17733")
      val x17919 = CounterChain.copy("x17924_0", "x17919")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_0_s).rdAddr(x17919(0)).wtAddr(x17733(0))
    }
    val x16907_dsp1 = MemoryPipeline(name="x16907_dsp1",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740_0", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_1_s).rdAddr(x17733(0)).wtAddr(x17733(0))
    }
    val x16907_dsp2 = MemoryPipeline(name="x16907_dsp2",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17632 = CounterChain.copy("x17647_0", "x17632")
      val x17733 = CounterChain.copy("x17740_0", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_2_s).rdAddr(x17632(0)).wtAddr(x17733(0))
    }
    val x16907_dsp3 = MemoryPipeline(name="x16907_dsp3",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17632 = CounterChain.copy("x17647_0", "x17632")
      val x17733 = CounterChain.copy("x17740_0", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_3_s).rdAddr(x17632(0)).wtAddr(x17733(0))
    }
    val x16907_dsp4 = MemoryPipeline(name="x16907_dsp4",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740_0", "x17733")
      val x17244 = CounterChain.copy("x17266", "x17244")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_4_s).rdAddr(x17244(0)).wtAddr(x17733(0))
    }
    val x16908_dsp0 = MemoryPipeline(name="x16908_dsp0",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17864 = CounterChain.copy("x17871_0", "x17864")
      val x17942 = CounterChain.copy("x17947_0", "x17942")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_0_s).rdAddr(x17942(0)).wtAddr(x17864(0))
    }
    val x16908_dsp1 = MemoryPipeline(name="x16908_dsp1",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17864 = CounterChain.copy("x17871_0", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_1_s).rdAddr(x17864(0)).wtAddr(x17864(0))
    }
    val x16908_dsp2 = MemoryPipeline(name="x16908_dsp2",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17763 = CounterChain.copy("x17778_0", "x17763")
      val x17864 = CounterChain.copy("x17871_0", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_2_s).rdAddr(x17763(0)).wtAddr(x17864(0))
    }
    val x16908_dsp3 = MemoryPipeline(name="x16908_dsp3",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17763 = CounterChain.copy("x17778_0", "x17763")
      val x17864 = CounterChain.copy("x17871_0", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_3_s).rdAddr(x17763(0)).wtAddr(x17864(0))
    }
    val x16908_dsp4 = MemoryPipeline(name="x16908_dsp4",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17280 = CounterChain.copy("x17302", "x17280")
      val x17864 = CounterChain.copy("x17871_0", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_4_s).rdAddr(x17280(0)).wtAddr(x17864(0))
    }
    val x16909_dsp0 = MemoryPipeline(name="x16909_dsp0",parent="x18033") { implicit CU => 
      val b18446 = CU.temp(None)
      val b18402 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17953 = CounterChain.copy("x17980", "x17953")
      val x17970 = CounterChain.copy("x17976_0", "x17970")
      val x17592 = CounterChain.copy("x17600_0", "x17592")
      val x16909 = SRAM(size=832,name="x16909",banking = Strided(1)).wtPort(x16992.readPort).wtPort(x17491.readPort).wtPort(x17599.readPort).rdPort(x16909_0_s)
      WAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18402))
      WAStage(operands=List(b18402, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.writeAddr))
      RAStage(operands=List(CU.ctr(x17953(0)), Const(64)), op=FixMul, results=List(b18446))
      RAStage(operands=List(b18446, CU.ctr(x17970(0))), op=FixAdd, results=List(x16909.readAddr))
    }
    val x16909_dsp1 = MemoryPipeline(name="x16909_dsp1",parent="x18033") { implicit CU => 
      val b18402 = CU.temp(None)
      val b18400 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17592 = CounterChain.copy("x17600_0", "x17592")
      val x16909 = SRAM(size=832,name="x16909",banking = Strided(1)).wtPort(x16992.readPort).wtPort(x17491.readPort).wtPort(x17599.readPort).rdPort(x16909_1_s)
      WAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18402))
      WAStage(operands=List(b18402, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.writeAddr))
      RAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18400))
      RAStage(operands=List(b18400, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.readAddr))
    }
    val x16909_dsp2 = MemoryPipeline(name="x16909_dsp2",parent="x18033") { implicit CU => 
      val b18402 = CU.temp(None)
      val b18398 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17482 = CounterChain.copy("x17498_0", "x17482")
      val x17592 = CounterChain.copy("x17600_0", "x17592")
      val x16909 = SRAM(size=832,name="x16909",banking = Strided(1)).wtPort(x16992.readPort).wtPort(x17491.readPort).wtPort(x17599.readPort).rdPort(x16909_2_s)
      WAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18402))
      WAStage(operands=List(b18402, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.writeAddr))
      RAStage(operands=List(CU.ctr(x17482(0)), Const(64)), op=FixMul, results=List(b18398))
      RAStage(operands=List(b18398, CU.ctr(x17482(1))), op=FixAdd, results=List(x16909.readAddr))
    }
    val x16909_dsp3 = MemoryPipeline(name="x16909_dsp3",parent="x18033") { implicit CU => 
      val b18402 = CU.temp(None)
      val b18392 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17482 = CounterChain.copy("x17498_0", "x17482")
      val x17592 = CounterChain.copy("x17600_0", "x17592")
      val x16909 = SRAM(size=832,name="x16909",banking = Strided(1)).wtPort(x16992.readPort).wtPort(x17491.readPort).wtPort(x17599.readPort).rdPort(x16909_3_s)
      WAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18402))
      WAStage(operands=List(b18402, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.writeAddr))
      RAStage(operands=List(CU.ctr(x17482(0)), Const(64)), op=FixMul, results=List(b18392))
      RAStage(operands=List(b18392, CU.ctr(x17482(1))), op=FixAdd, results=List(x16909.readAddr))
    }
    val x16909_dsp4 = MemoryPipeline(name="x16909_dsp4",parent="x18033") { implicit CU => 
      val b18402 = CU.temp(None)
      val b18376 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17208 = CounterChain.copy("x17230", "x17208")
      val x17211 = CounterChain.copy("x17223_0", "x17211")
      val x17592 = CounterChain.copy("x17600_0", "x17592")
      val x16909 = SRAM(size=832,name="x16909",banking = Strided(1)).wtPort(x16992.readPort).wtPort(x17491.readPort).wtPort(x17599.readPort).rdPort(x16909_4_s)
      WAStage(operands=List(CU.ctr(x17592(0)), Const(64)), op=FixMul, results=List(b18402))
      WAStage(operands=List(b18402, CU.ctr(x17592(1))), op=FixAdd, results=List(x16909.writeAddr))
      RAStage(operands=List(CU.ctr(x17211(0)), Const(64)), op=FixMul, results=List(b18376))
      RAStage(operands=List(b18376, CU.ctr(x17208(0))), op=FixAdd, results=List(x16909.readAddr))
    }
    val x16910_dsp0 = MemoryPipeline(name="x16910_dsp0",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18452 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x17982 = CounterChain.copy("x18009", "x17982")
      val x17999 = CounterChain.copy("x18005_0", "x17999")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_0_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17982(0)), Const(64)), op=FixMul, results=List(b18452))
      RAStage(operands=List(b18452, CU.ctr(x17999(0))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16910_dsp1 = MemoryPipeline(name="x16910_dsp1",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18412 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_1_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18412))
      RAStage(operands=List(b18412, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16910_dsp2 = MemoryPipeline(name="x16910_dsp2",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18410 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x17613 = CounterChain.copy("x17629_0", "x17613")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_2_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17613(0)), Const(64)), op=FixMul, results=List(b18410))
      RAStage(operands=List(b18410, CU.ctr(x17613(1))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16910_dsp3 = MemoryPipeline(name="x16910_dsp3",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18404 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x17613 = CounterChain.copy("x17629_0", "x17613")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_3_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17613(0)), Const(64)), op=FixMul, results=List(b18404))
      RAStage(operands=List(b18404, CU.ctr(x17613(1))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16910_dsp4 = MemoryPipeline(name="x16910_dsp4",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18388 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x17448 = CounterChain.copy("x17460_0", "x17448")
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_4_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17440(0)), Const(64)), op=FixMul, results=List(b18388))
      RAStage(operands=List(b18388, CU.ctr(x17448(0))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16910_dsp5 = MemoryPipeline(name="x16910_dsp5",parent="x18033") { implicit CU => 
      val b18414 = CU.temp(None)
      val b18378 = CU.temp(None)
      val x17622 = ScalarFIFO(size=1,name="x17622").wtPort(x16910_x17622_s)
      val x17730 = ScalarFIFO(size=1,name="x17730").wtPort(x16910_x17730_s)
      val x17017 = ScalarFIFO(size=1,name="x17017").wtPort(x16910_x17017_s)
      val x17723 = CounterChain.copy("x17731_0", "x17723")
      val x17244 = CounterChain.copy("x17266", "x17244")
      val x17247 = CounterChain.copy("x17259_0", "x17247")
      val x16910 = SRAM(size=4096,name="x16910",banking = Strided(1)).wtPort(x17017.readPort).wtPort(x17622.readPort).wtPort(x17730.readPort).rdPort(x16910_5_s)
      WAStage(operands=List(CU.ctr(x17723(0)), Const(64)), op=FixMul, results=List(b18414))
      WAStage(operands=List(b18414, CU.ctr(x17723(1))), op=FixAdd, results=List(x16910.writeAddr))
      RAStage(operands=List(CU.ctr(x17247(0)), Const(64)), op=FixMul, results=List(b18378))
      RAStage(operands=List(b18378, CU.ctr(x17244(0))), op=FixAdd, results=List(x16910.readAddr))
    }
    val x16911_dsp0 = MemoryPipeline(name="x16911_dsp0",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18428 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17875 = CounterChain.copy("x17882_0", "x17875")
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_0_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(b18428))
      RAStage(operands=List(b18428, CU.ctr(x17875(1))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16911_dsp1 = MemoryPipeline(name="x16911_dsp1",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18424 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_1_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18424))
      RAStage(operands=List(b18424, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16911_dsp2 = MemoryPipeline(name="x16911_dsp2",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18422 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17744 = CounterChain.copy("x17760_0", "x17744")
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_2_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17744(0)), Const(3)), op=FixMul, results=List(b18422))
      RAStage(operands=List(b18422, CU.ctr(x17744(1))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16911_dsp3 = MemoryPipeline(name="x16911_dsp3",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18416 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17744 = CounterChain.copy("x17760_0", "x17744")
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_3_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17744(0)), Const(3)), op=FixMul, results=List(b18416))
      RAStage(operands=List(b18416, CU.ctr(x17744(1))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16911_dsp4 = MemoryPipeline(name="x16911_dsp4",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18384 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x17408 = CounterChain.copy("x17420_0", "x17408")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_4_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17400(0)), Const(3)), op=FixMul, results=List(b18384))
      RAStage(operands=List(b18384, CU.ctr(x17408(0))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16911_dsp5 = MemoryPipeline(name="x16911_dsp5",parent="x18033") { implicit CU => 
      val b18426 = CU.temp(None)
      val b18380 = CU.temp(None)
      val x17861 = ScalarFIFO(size=1,name="x17861").wtPort(x16911_x17861_s)
      val x17753 = ScalarFIFO(size=1,name="x17753").wtPort(x16911_x17753_s)
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16912_1_s)
      val x17280 = CounterChain.copy("x17302", "x17280")
      val x17283 = CounterChain.copy("x17295_0", "x17283")
      val x17854 = CounterChain.copy("x17862_0", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_5_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17283(0)), Const(3)), op=FixMul, results=List(b18380))
      RAStage(operands=List(b18380, CU.ctr(x17280(0))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16912_dsp0 = MemoryPipeline(name="x16912_dsp0",parent="x18033") { implicit CU => 
      val x17876 = CU.temp(None)
      val x17036 = ScalarFIFO(size=1,name="x17036").wtPort(x16912_x17036_s)
      val x17881 = ScalarFIFO(size=1,name="x17881").wtPort(x16911_0_s)
      val x17875 = CounterChain.copy("x17882_0", "x17875")
      val x18023 = CounterChain.copy("x18028_0", "x18023")
      val x16912 = SRAM(size=192,name="x16912",banking = Strided(1)).wtPort(x17036.readPort).wtPort(x17881.readPort).rdPort(x16912_0_s).rdAddr(x18023(0))
      WAStage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(x17876))
      WAStage(operands=List(x17876, CU.ctr(x17875(1))), op=FixAdd, results=List(x16912.writeAddr))
    }
    val x16912_dsp1 = MemoryPipeline(name="x16912_dsp1",parent="x18033") { implicit CU => 
      val x17042 = CU.temp(None)
      val x17876 = CU.temp(None)
      val x17036 = ScalarFIFO(size=1,name="x17036").wtPort(x16912_x17036_s)
      val x17881 = ScalarFIFO(size=1,name="x17881").wtPort(x16911_0_s)
      val x17041 = CounterChain.copy("x17048_0", "x17041")
      val x17875 = CounterChain.copy("x17882_0", "x17875")
      val x16912 = SRAM(size=192,name="x16912",banking = Strided(1)).wtPort(x17036.readPort).wtPort(x17881.readPort).rdPort(x16912_1_s)
      WAStage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(x17876))
      WAStage(operands=List(x17876, CU.ctr(x17875(1))), op=FixAdd, results=List(x16912.writeAddr))
      RAStage(operands=List(CU.ctr(x17041(0)), Const(3)), op=FixMul, results=List(x17042))
      RAStage(operands=List(x17042, CU.ctr(x17041(1))), op=FixAdd, results=List(x16912.readAddr))
    }
    val x16931 = StreamController(name="x16931",parent=x18033) { implicit CU => 
      val x16931_unit = CounterChain(name = "x16931_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16923_0 = Pipeline(name="x16923_0",parent=x16931) { implicit CU => 
      val x16916 = CU.temp(None)
      val x16918 = ScalarBuffer(name="x16918").wtPort(biases1_dram_da)
      val x16923_unit = CounterChain(name = "x16923_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16916))
      Stage(operands=List(x16916, CU.load(x16918)), op=FixAdd, results=List(CU.scalarOut(x16913_b18320_x16922_b18322_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16913_b18321_x16922_b18323_s)))
    }
    val x16924 = MemoryController(name="x16924",parent=x16931,offchip=biases1_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16913_b18321 = ScalarFIFO(size=1,name="size").wtPort(x16913_b18321_x16922_b18323_s)
      val x16913_b18320 = ScalarFIFO(size=1,name="offset").wtPort(x16913_b18320_x16922_b18322_s)
      CU.newVout("data", x16914_x16924_data_v)
    }
    val x16930_0 = Pipeline(name="x16930_0",parent=x16931) { implicit CU => 
      val x16914 = VectorFIFO(size=1,name="x16914").wtPort(x16914_x16924_data_v)
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16926 = CounterChain(name = "x16926", ctr1).iter(4)
      Stage(operands=List(CU.load(x16914)), op=Bypass, results=List(CU.scalarOut(x16906_x16929_s)))
    }
    val x16950 = StreamController(name="x16950",parent=x18033) { implicit CU => 
      val x16950_unit = CounterChain(name = "x16950_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16942_0 = Pipeline(name="x16942_0",parent=x16950) { implicit CU => 
      val x16935 = CU.temp(None)
      val x16937 = ScalarBuffer(name="x16937").wtPort(biases2_dram_da)
      val x16942_unit = CounterChain(name = "x16942_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16935))
      Stage(operands=List(x16935, CU.load(x16937)), op=FixAdd, results=List(CU.scalarOut(x16932_b18324_x16941_b18326_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16932_b18325_x16941_b18327_s)))
    }
    val x16943 = MemoryController(name="x16943",parent=x16950,offchip=biases2_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16932_b18325 = ScalarFIFO(size=1,name="size").wtPort(x16932_b18325_x16941_b18327_s)
      val x16932_b18324 = ScalarFIFO(size=1,name="offset").wtPort(x16932_b18324_x16941_b18326_s)
      CU.newVout("data", x16933_x16943_data_v)
    }
    val x16949_0 = Pipeline(name="x16949_0",parent=x16950) { implicit CU => 
      val x16933 = VectorFIFO(size=1,name="x16933").wtPort(x16933_x16943_data_v)
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16945 = CounterChain(name = "x16945", ctr2).iter(4)
      Stage(operands=List(CU.load(x16933)), op=Bypass, results=List(CU.scalarOut(x16907_x16948_s)))
    }
    val x16969 = StreamController(name="x16969",parent=x18033) { implicit CU => 
      val x16969_unit = CounterChain(name = "x16969_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16961_0 = Pipeline(name="x16961_0",parent=x16969) { implicit CU => 
      val x16954 = CU.temp(None)
      val x16956 = ScalarBuffer(name="x16956").wtPort(biases3_dram_da)
      val x16961_unit = CounterChain(name = "x16961_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x16954))
      Stage(operands=List(x16954, CU.load(x16956)), op=FixAdd, results=List(CU.scalarOut(x16951_b18328_x16960_b18330_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x16951_b18329_x16960_b18331_s)))
    }
    val x16962 = MemoryController(name="x16962",parent=x16969,offchip=biases3_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16951_b18328 = ScalarFIFO(size=1,name="offset").wtPort(x16951_b18328_x16960_b18330_s)
      val x16951_b18329 = ScalarFIFO(size=1,name="size").wtPort(x16951_b18329_x16960_b18331_s)
      CU.newVout("data", x16952_x16962_data_v)
    }
    val x16968_0 = Pipeline(name="x16968_0",parent=x16969) { implicit CU => 
      val x16952 = VectorFIFO(size=1,name="x16952").wtPort(x16952_x16962_data_v)
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x16964 = CounterChain(name = "x16964", ctr3).iter(1)
      Stage(operands=List(CU.load(x16952)), op=Bypass, results=List(CU.scalarOut(x16908_x16967_s)))
    }
    val x16994 = StreamController(name="x16994",parent=x18033) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val x16971 = CounterChain(name = "x16971", ctr4).iter(13)
    }
    val x16985_0 = Pipeline(name="x16985_0",parent=x16994) { implicit CU => 
      val x16975 = CU.temp(None)
      val x16978 = CU.temp(None)
      val x16977 = CU.temp(None)
      val x16980 = ScalarBuffer(name="x16980").wtPort(weights1_dram_da)
      val x16971 = CounterChain.copy("x16994", "x16971")
      val x16985_unit = CounterChain(name = "x16985_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x16971(0)), Const(6)), op=FixSla, results=List(x16975))
      Stage(operands=List(x16975, Const(0)), op=FixAdd, results=List(x16977))
      Stage(operands=List(x16977, Const(2)), op=FixSla, results=List(x16978))
      Stage(operands=List(x16978, CU.load(x16980)), op=FixAdd, results=List(CU.scalarOut(x16972_b18332_x16984_b18334_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16972_b18333_x16984_b18335_s)))
    }
    val x16986 = MemoryController(name="x16986",parent=x16994,offchip=weights1_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16972_b18332 = ScalarFIFO(size=1,name="offset").wtPort(x16972_b18332_x16984_b18334_s)
      val x16972_b18333 = ScalarFIFO(size=1,name="size").wtPort(x16972_b18333_x16984_b18335_s)
      CU.newVout("data", x16973_x16986_data_v)
    }
    val x16993_0 = Pipeline(name="x16993_0",parent=x16994) { implicit CU => 
      val x16973 = VectorFIFO(size=1,name="x16973").wtPort(x16973_x16986_data_v)
      val ctr5 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16988 = CounterChain(name = "x16988", ctr5).iter(4)
      Stage(operands=List(CU.load(x16973)), op=Bypass, results=List(CU.scalarOut(x16909_x16992_s)))
    }
    val x17019 = StreamController(name="x17019",parent=x18033) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x16996 = CounterChain(name = "x16996", ctr6).iter(64)
    }
    val x17010_0 = Pipeline(name="x17010_0",parent=x17019) { implicit CU => 
      val x17003 = CU.temp(None)
      val x17002 = CU.temp(None)
      val x17000 = CU.temp(None)
      val x17005 = ScalarBuffer(name="x17005").wtPort(weights2_dram_da)
      val x16996 = CounterChain.copy("x17019", "x16996")
      val x17010_unit = CounterChain(name = "x17010_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x16996(0)), Const(6)), op=FixSla, results=List(x17000))
      Stage(operands=List(x17000, Const(0)), op=FixAdd, results=List(x17002))
      Stage(operands=List(x17002, Const(2)), op=FixSla, results=List(x17003))
      Stage(operands=List(x17003, CU.load(x17005)), op=FixAdd, results=List(CU.scalarOut(x16997_b18338_x17009_b18340_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x16997_b18339_x17009_b18341_s)))
    }
    val x17011 = MemoryController(name="x17011",parent=x17019,offchip=weights2_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x16997_b18339 = ScalarFIFO(size=1,name="size").wtPort(x16997_b18339_x17009_b18341_s)
      val x16997_b18338 = ScalarFIFO(size=1,name="offset").wtPort(x16997_b18338_x17009_b18340_s)
      CU.newVout("data", x16998_x17011_data_v)
    }
    val x17018_0 = Pipeline(name="x17018_0",parent=x17019) { implicit CU => 
      val x16998 = VectorFIFO(size=1,name="x16998").wtPort(x16998_x17011_data_v)
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17013 = CounterChain(name = "x17013", ctr7).iter(4)
      Stage(operands=List(CU.load(x16998)), op=Bypass, results=List(CU.scalarOut(x16910_x17017_s)))
    }
    val x17038 = StreamController(name="x17038",parent=x18033) { implicit CU => 
      val x17038_unit = CounterChain(name = "x17038_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17030_0 = Pipeline(name="x17030_0",parent=x17038) { implicit CU => 
      val x17023 = CU.temp(None)
      val x17025 = ScalarBuffer(name="x17025").wtPort(weights3_dram_da)
      val x17030_unit = CounterChain(name = "x17030_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17023))
      Stage(operands=List(x17023, CU.load(x17025)), op=FixAdd, results=List(CU.scalarOut(x17020_b18344_x17029_b18346_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x17020_b18345_x17029_b18347_s)))
    }
    val x17031 = MemoryController(name="x17031",parent=x17038,offchip=weights3_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17020_b18344 = ScalarFIFO(size=1,name="offset").wtPort(x17020_b18344_x17029_b18346_s)
      val x17020_b18345 = ScalarFIFO(size=1,name="size").wtPort(x17020_b18345_x17029_b18347_s)
      CU.newVout("data", x17021_x17031_data_v)
    }
    val x17037_0 = Pipeline(name="x17037_0",parent=x17038) { implicit CU => 
      val x17021 = VectorFIFO(size=1,name="x17021").wtPort(x17021_x17031_data_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x17033 = CounterChain(name = "x17033", ctr8).iter(12)
      Stage(operands=List(CU.load(x17021)), op=Bypass, results=List(CU.scalarOut(x16912_x17036_s)))
    }
    val x17048_0 = Pipeline(name="x17048_0",parent=x18033) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17041 = CounterChain(name = "x17041", ctr9, ctr10).iter(192)
      Stage(operands=List(CU.ctr(x17041(0)), Const(3)), op=FixMul, results=List())
    }
    val x17872 = Sequential(name="x17872",parent=x18033) { implicit CU => 
      val x16809 = ScalarBuffer(name="x16809").wtPort(iters_argin)
      val ctr11 = Counter(min=Const(0), max=x16809.readPort, step=Const(1), par=1) // Counter
      val x17051 = CounterChain(name = "x17051", ctr11).iter(1)
    }
    val x17056_dsp0 = MemoryPipeline(name="x17056_dsp0",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242_0", "x17232")
      val x17430 = CounterChain.copy("x17438_0", "x17430")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_0_s).rdAddr(x17430(0)).wtAddr(x17232(0))
    }
    val x17056_dsp1 = MemoryPipeline(name="x17056_dsp1",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242_0", "x17232")
      val x17247 = CounterChain.copy("x17259_0", "x17247")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_1_s).rdAddr(x17247(0)).wtAddr(x17232(0))
    }
    val x17056_dsp2 = MemoryPipeline(name="x17056_dsp2",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242_0", "x17232")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_2_s).rdAddr(x17232(0)).wtAddr(x17232(0))
    }
    val x17057_dsp0 = MemoryPipeline(name="x17057_dsp0",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17268 = CounterChain.copy("x17278_0", "x17268")
      val x17390 = CounterChain.copy("x17398_0", "x17390")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_0_s).rdAddr(x17390(0)).wtAddr(x17268(0))
    }
    val x17057_dsp1 = MemoryPipeline(name="x17057_dsp1",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17283 = CounterChain.copy("x17295_0", "x17283")
      val x17268 = CounterChain.copy("x17278_0", "x17268")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_1_s).rdAddr(x17283(0)).wtAddr(x17268(0))
    }
    val x17057_dsp2 = MemoryPipeline(name="x17057_dsp2",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17268 = CounterChain.copy("x17278_0", "x17268")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_2_s).rdAddr(x17268(0)).wtAddr(x17268(0))
    }
    val x17058_dsp0 = MemoryPipeline(name="x17058_dsp0",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314_0", "x17304")
      val x17346 = CounterChain.copy("x17373", "x17346")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_0_s).rdAddr(x17346(0)).wtAddr(x17304(0))
    }
    val x17058_dsp1 = MemoryPipeline(name="x17058_dsp1",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314_0", "x17304")
      val x17317 = CounterChain.copy("x17344", "x17317")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_1_s).rdAddr(x17317(0)).wtAddr(x17304(0))
    }
    val x17058_dsp2 = MemoryPipeline(name="x17058_dsp2",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314_0", "x17304")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_2_s).rdAddr(x17304(0)).wtAddr(x17304(0))
    }
    val x17059_dsp0 = MemoryPipeline(name="x17059_dsp0",parent="x17872") { implicit CU => 
      val x17238 = ScalarFIFO(size=1,name="x17238").wtPort(x17059_x17238_s)
      val x17232 = CounterChain.copy("x17242_0", "x17232")
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17059 = SRAM(size=64,name="x17059",banking = Strided(1)).wtPort(x17238.readPort).rdPort(x17059_0_s).rdAddr(x17440(0)).wtAddr(x17232(0))
    }
    val x17060_dsp0 = MemoryPipeline(name="x17060_dsp0",parent="x17872") { implicit CU => 
      val x17274 = ScalarFIFO(size=1,name="x17274").wtPort(x17060_x17274_s)
      val x17268 = CounterChain.copy("x17278_0", "x17268")
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17060 = SRAM(size=64,name="x17060",banking = Strided(1)).wtPort(x17274.readPort).rdPort(x17060_0_s).rdAddr(x17400(0)).wtAddr(x17268(0))
    }
    val x17061_dsp0 = MemoryPipeline(name="x17061_dsp0",parent="x17872") { implicit CU => 
      val x17310 = ScalarFIFO(size=1,name="x17310").wtPort(x17061_x17310_s)
      val x17304 = CounterChain.copy("x17314_0", "x17304")
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17061 = SRAM(size=3,name="x17061",banking = Strided(1)).wtPort(x17310.readPort).rdPort(x17061_0_s).rdAddr(x17375(0)).wtAddr(x17304(0))
    }
    val x17062_dsp0 = MemoryPipeline(name="x17062_dsp0",parent="x17872") { implicit CU => 
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17082_b18353_x17108_b18361_s)
      val x17135 = ScalarFIFO(size=1,name="x17135").wtPort(x17062_x17135_s)
      val x17124 = CounterChain.copy("x17136_0", "x17124")
      val x17470 = CounterChain.copy("x17478_0", "x17470")
      val x17062 = SRAM(size=18,name="x17062",banking = Strided(1)).wtPort(x17135.readPort).rdPort(x17062_0_s).rdAddr(x17470(0))
      WAStage(operands=List(CU.ctr(x17124(0)), CU.load(x17111)), op=FixSub, results=List(x17062.writeAddr))
    }
    val x17062_dsp1 = MemoryPipeline(name="x17062_dsp1",parent="x17872") { implicit CU => 
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17082_b18353_x17108_b18361_s)
      val x17135 = ScalarFIFO(size=1,name="x17135").wtPort(x17062_x17135_s)
      val x17124 = CounterChain.copy("x17136_0", "x17124")
      val x17211 = CounterChain.copy("x17223_0", "x17211")
      val x17062 = SRAM(size=18,name="x17062",banking = Strided(1)).wtPort(x17135.readPort).rdPort(x17062_1_s).rdAddr(x17211(0))
      WAStage(operands=List(CU.ctr(x17124(0)), CU.load(x17111)), op=FixSub, results=List(x17062.writeAddr))
    }
    val x17063_dsp0 = MemoryPipeline(name="x17063_dsp0",parent="x17872") { implicit CU => 
      val x17203 = ScalarFIFO(size=1,name="x17203").wtPort(x17151_x17178_data_s)
      val x17179 = ScalarBuffer(name="x17179").wtPort(x17150_b18366_x17176_b18374_s)
      val x17192 = CounterChain.copy("x17204_0", "x17192")
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17063 = SRAM(size=16,name="x17063",banking = Strided(1)).wtPort(x17203.readPort).rdPort(x17063_0_s).rdAddr(x17375(0))
      WAStage(operands=List(CU.ctr(x17192(0)), CU.load(x17179)), op=FixSub, results=List(x17063.writeAddr))
    }
    val x17064_dsp0 = MemoryPipeline(name="x17064_dsp0",parent="x17872") { implicit CU => 
      val x17372 = ScalarFIFO(size=1,name="x17372").wtPort(x17064_x17372_s)
      val x17346 = CounterChain.copy("x17373", "x17346")
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17064 = SRAM(size=3,name="x17064",banking = Strided(1)).wtPort(x17372.readPort).rdPort(x17064_0_s).rdAddr(x17375(0)).wtAddr(x17346(0))
    }
    val x17065_dsp0 = MemoryPipeline(name="x17065_dsp0",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17763 = CounterChain.copy("x17778_0", "x17763")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_0_s).rdAddr(x17763(0)).wtAddr(x17375(0))
    }
    val x17065_dsp1 = MemoryPipeline(name="x17065_dsp1",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17408 = CounterChain.copy("x17420_0", "x17408")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_1_s).rdAddr(x17408(0)).wtAddr(x17375(0))
    }
    val x17065_dsp2 = MemoryPipeline(name="x17065_dsp2",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387_0", "x17375")
      val x17390 = CounterChain.copy("x17398_0", "x17390")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_2_s).rdAddr(x17390(1)).wtAddr(x17375(0))
    }
    val x17066_dsp0 = MemoryPipeline(name="x17066_dsp0",parent="x17872") { implicit CU => 
      val b18390 = CU.temp(None)
      val b18394 = CU.temp(None)
      val x17477 = ScalarFIFO(size=1,name="x17477").wtPort(x17066_x17477_s)
      val x17470 = CounterChain.copy("x17478_0", "x17470")
      val x17482 = CounterChain.copy("x17498_0", "x17482")
      val x17066 = SRAM(size=832,name="x17066",banking = Strided(1)).wtPort(x17477.readPort).rdPort(x17066_0_s)
      WAStage(operands=List(CU.ctr(x17470(0)), Const(64)), op=FixMul, results=List(b18390))
      WAStage(operands=List(b18390, CU.ctr(x17470(1))), op=FixAdd, results=List(x17066.writeAddr))
      RAStage(operands=List(CU.ctr(x17482(0)), Const(64)), op=FixMul, results=List(b18394))
      RAStage(operands=List(b18394, CU.ctr(x17482(1))), op=FixAdd, results=List(x17066.readAddr))
    }
    val x17067_dsp0 = MemoryPipeline(name="x17067_dsp0",parent="x17872") { implicit CU => 
      val b18406 = CU.temp(None)
      val b18386 = CU.temp(None)
      val x17437 = ScalarFIFO(size=1,name="x17437").wtPort(x17067_x17437_s)
      val x17430 = CounterChain.copy("x17438_0", "x17430")
      val x17613 = CounterChain.copy("x17629_0", "x17613")
      val x17067 = SRAM(size=4096,name="x17067",banking = Strided(1)).wtPort(x17437.readPort).rdPort(x17067_0_s)
      WAStage(operands=List(CU.ctr(x17430(0)), Const(64)), op=FixMul, results=List(b18386))
      WAStage(operands=List(b18386, CU.ctr(x17430(1))), op=FixAdd, results=List(x17067.writeAddr))
      RAStage(operands=List(CU.ctr(x17613(0)), Const(64)), op=FixMul, results=List(b18406))
      RAStage(operands=List(b18406, CU.ctr(x17613(1))), op=FixAdd, results=List(x17067.readAddr))
    }
    val x17068_dsp0 = MemoryPipeline(name="x17068_dsp0",parent="x17872") { implicit CU => 
      val b18418 = CU.temp(None)
      val b18382 = CU.temp(None)
      val x17397 = ScalarFIFO(size=1,name="x17397").wtPort(x17068_x17397_s)
      val x17390 = CounterChain.copy("x17398_0", "x17390")
      val x17744 = CounterChain.copy("x17760_0", "x17744")
      val x17068 = SRAM(size=192,name="x17068",banking = Strided(1)).wtPort(x17397.readPort).rdPort(x17068_0_s)
      WAStage(operands=List(CU.ctr(x17390(0)), Const(3)), op=FixMul, results=List(b18382))
      WAStage(operands=List(b18382, CU.ctr(x17390(1))), op=FixAdd, results=List(x17068.writeAddr))
      RAStage(operands=List(CU.ctr(x17744(0)), Const(3)), op=FixMul, results=List(b18418))
      RAStage(operands=List(b18418, CU.ctr(x17744(1))), op=FixAdd, results=List(x17068.readAddr))
    }
    val x17069_dsp0 = MemoryPipeline(name="x17069_dsp0",parent="x17872") { implicit CU => 
      val x17465 = ScalarFIFO(size=1,name="x17465").wtPort(x17069_x17465_s)
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17501 = CounterChain.copy("x17516_0", "x17501")
      val x17069 = SRAM(size=64,name="x17069",banking = Strided(1)).wtPort(x17465.readPort).rdPort(x17069_0_s).rdAddr(x17501(0)).wtAddr(x17440(0))
    }
    val x17069_dsp1 = MemoryPipeline(name="x17069_dsp1",parent="x17872") { implicit CU => 
      val x17465 = ScalarFIFO(size=1,name="x17465").wtPort(x17069_x17465_s)
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17470 = CounterChain.copy("x17478_0", "x17470")
      val x17069 = SRAM(size=64,name="x17069",banking = Strided(1)).wtPort(x17465.readPort).rdPort(x17069_1_s).rdAddr(x17470(1)).wtAddr(x17440(0))
    }
    val x17070_dsp0 = MemoryPipeline(name="x17070_dsp0",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17632 = CounterChain.copy("x17647_0", "x17632")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_0_s).rdAddr(x17632(0)).wtAddr(x17400(0))
    }
    val x17070_dsp1 = MemoryPipeline(name="x17070_dsp1",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17448 = CounterChain.copy("x17460_0", "x17448")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_1_s).rdAddr(x17448(0)).wtAddr(x17400(0))
    }
    val x17070_dsp2 = MemoryPipeline(name="x17070_dsp2",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17430 = CounterChain.copy("x17438_0", "x17430")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_2_s).rdAddr(x17430(1)).wtAddr(x17400(0))
    }
    val x17080_0 = Pipeline(name="x17080_0",parent=x17872) { implicit CU => 
      val x17074 = CU.temp(None)
      val x17071 = CU.temp(None)
      val x17073 = CU.temp(None)
      val x17072 = CU.temp(None)
      val x17051 = CounterChain.copy("x17872", "x17051")
      val x17080_unit = CounterChain(name = "x17080_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17051(0)), Const(163)), op=FixMod, results=List(x17071, CU.scalarOut(x17052_x17076_s)))
      Stage(operands=List(x17071, Const(13)), op=FixMul, results=List(x17072, CU.scalarOut(x17053_x17077_s)))
      Stage(operands=List(x17071, Const(1)), op=FixAdd, results=List(x17073, CU.scalarOut(x17054_x17078_s)))
      Stage(operands=List(x17073, Const(13)), op=FixMul, results=List(x17074))
      Stage(operands=List(x17074, x17072), op=FixSub, results=List(CU.scalarOut(x17055_x17079_s)))
    }
    val x17138 = StreamController(name="x17138",parent=x17872) { implicit CU => 
      val x17138_unit = CounterChain(name = "x17138_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17109 = StreamController(name="x17109",parent=x17138) { implicit CU => 
      val x17109_unit = CounterChain(name = "x17109_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17109_0 = Pipeline(name="x17109_0",parent=x17109) { implicit CU => 
      val x17087 = CU.temp(None)
      val x17086 = CU.temp(None)
      val x17107_x17094 = CU.temp(None)
      val x17053 = ScalarBuffer(name="x17053").wtPort(x17053_x17077_s)
      val x17055 = ScalarBuffer(name="x17055").wtPort(x17055_x17079_s)
      Stage(operands=List(CU.load(x17053), Const(2)), op=FixSla, results=List(x17086, CU.scalarOut(bus_1728_s)))
      Stage(operands=List(x17086, Const(64)), op=FixMod, results=List(x17087, CU.scalarOut(bus_1730_s)))
      Stage(operands=List(x17087, Const(4)), op=FixDiv, results=List(x17107_x17094, CU.scalarOut(bus_1732_s), CU.scalarOut(x17082_b18353_x17108_b18361_s)))
      Stage(operands=List(x17107_x17094, CU.load(x17055)), op=FixAdd, results=List(CU.scalarOut(x17082_b18354_x17108_b18362_s)))
      Stage(operands=List(CU.load(x17055), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1734_s)))
    }
    val x17109_1 = Pipeline(name="x17109_1",parent=x17109) { implicit CU => 
      val x17091 = CU.temp(None)
      val x17097 = CU.temp(None)
      val x17092 = CU.temp(None)
      val x17093 = CU.temp(None)
      val x17095 = CU.temp(None)
      val x17086 = ScalarFIFO(size=1,name="x17086").wtPort(bus_1728_s)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(bus_1734_s)
      val x17094 = ScalarFIFO(size=1,name="x17094").wtPort(bus_1732_s)
      val x17055 = ScalarBuffer(name="x17055").wtPort(x17055_x17079_s)
      Stage(operands=List(CU.load(x17086), CU.load(x17089)), op=FixAdd, results=List(x17091))
      Stage(operands=List(x17091, Const(64)), op=FixMod, results=List(x17092))
      Stage(operands=List(Const(64), x17092), op=FixSub, results=List(x17093, CU.scalarOut(bus_1737_s)))
      Stage(operands=List(x17093, Const(4)), op=FixDiv, results=List(x17095))
      Stage(operands=List(CU.load(x17055), CU.load(x17094)), op=FixAdd, results=List(x17097))
      Stage(operands=List(x17097, x17095), op=FixAdd, results=List(CU.scalarOut(x17082_b18352_x17108_b18360_s)))
    }
    val x17109_2 = Pipeline(name="x17109_2",parent=x17109) { implicit CU => 
      val x17099 = CU.temp(None)
      val x17090 = CU.temp(None)
      val x17089 = ScalarFIFO(size=1,name="x17089").wtPort(bus_1734_s)
      val x17093 = ScalarFIFO(size=1,name="x17093").wtPort(bus_1737_s)
      val x17086 = ScalarFIFO(size=1,name="x17086").wtPort(bus_1728_s)
      val x17087 = ScalarFIFO(size=1,name="x17087").wtPort(bus_1730_s)
      val x17102 = ScalarBuffer(name="x17102").wtPort(training_data_dram_da)
      Stage(operands=List(CU.load(x17089), CU.load(x17087)), op=FixAdd, results=List(x17099))
      Stage(operands=List(x17099, CU.load(x17093)), op=FixAdd, results=List(CU.scalarOut(x17081_b18351_x17106_b18359_s)))
      Stage(operands=List(CU.load(x17086), CU.load(x17087)), op=FixSub, results=List(x17090))
      Stage(operands=List(x17090, CU.load(x17102)), op=FixAdd, results=List(CU.scalarOut(x17081_b18350_x17106_b18358_s)))
    }
    val x17110 = MemoryController(name="x17110",parent=x17138,offchip=training_data_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17081_b18350 = ScalarFIFO(size=1,name="offset").wtPort(x17081_b18350_x17106_b18358_s)
      val x17081_b18351 = ScalarFIFO(size=1,name="size").wtPort(x17081_b18351_x17106_b18359_s)
      CU.newVout("data", x17083_x17110_data_v)
    }
    val x17137 = Sequential(name="x17137",parent=x17138) { implicit CU => 
      val x17137_unit = CounterChain(name = "x17137_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17136_0 = Pipeline(name="x17136_0",parent=x17137) { implicit CU => 
      val x17112 = ScalarBuffer(name="x17112").wtPort(x17082_b18354_x17108_b18362_s)
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17082_b18353_x17108_b18361_s)
      val x17083 = VectorFIFO(size=1,name="x17083").wtPort(x17083_x17110_data_v)
      val x17113 = ScalarBuffer(name="x17113").wtPort(x17082_b18352_x17108_b18360_s)
      val ctr12 = Counter(min=Const(0), max=x17113.readPort, step=Const(1), par=16) // Counter
      val x17124 = CounterChain(name = "x17124", ctr12).iter(1)
      Stage(operands=List(CU.load(x17111), CU.ctr(x17124(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x17124(0)), CU.load(x17112)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x17083)), op=Bypass, results=List(CU.scalarOut(x17062_x17135_s)))
    }
    val x17148_0 = Pipeline(name="x17148_0",parent=x17872) { implicit CU => 
      val x17142 = CU.temp(None)
      val x17144 = CU.temp(None)
      val x17052 = ScalarBuffer(name="x17052").wtPort(x17052_x17076_s)
      val x17054 = ScalarBuffer(name="x17054").wtPort(x17054_x17078_s)
      val x17148_unit = CounterChain(name = "x17148_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17052), Const(3)), op=FixMul, results=List(x17142, CU.scalarOut(x17139_x17146_s)))
      Stage(operands=List(CU.load(x17054), Const(3)), op=FixMul, results=List(x17144))
      Stage(operands=List(x17144, x17142), op=FixSub, results=List(CU.scalarOut(x17140_x17147_s)))
    }
    val x17206 = StreamController(name="x17206",parent=x17872) { implicit CU => 
      val x17206_unit = CounterChain(name = "x17206_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17177 = StreamController(name="x17177",parent=x17206) { implicit CU => 
      val x17177_unit = CounterChain(name = "x17177_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17177_0 = Pipeline(name="x17177_0",parent=x17177) { implicit CU => 
      val x17154 = CU.temp(None)
      val x17175_x17162 = CU.temp(None)
      val x17155 = CU.temp(None)
      val x17139 = ScalarBuffer(name="x17139").wtPort(x17139_x17146_s)
      val x17140 = ScalarBuffer(name="x17140").wtPort(x17140_x17147_s)
      Stage(operands=List(CU.load(x17139), Const(2)), op=FixSla, results=List(x17154, CU.scalarOut(bus_1756_s)))
      Stage(operands=List(x17154, Const(64)), op=FixMod, results=List(x17155, CU.scalarOut(bus_1758_s)))
      Stage(operands=List(x17155, Const(4)), op=FixDiv, results=List(x17175_x17162, CU.scalarOut(bus_1760_s), CU.scalarOut(x17150_b18366_x17176_b18374_s)))
      Stage(operands=List(x17175_x17162, CU.load(x17140)), op=FixAdd, results=List(CU.scalarOut(x17150_b18367_x17176_b18375_s)))
      Stage(operands=List(CU.load(x17140), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_1762_s)))
    }
    val x17177_1 = Pipeline(name="x17177_1",parent=x17177) { implicit CU => 
      val x17160 = CU.temp(None)
      val x17163 = CU.temp(None)
      val x17165 = CU.temp(None)
      val x17161 = CU.temp(None)
      val x17159 = CU.temp(None)
      val x17157 = ScalarFIFO(size=1,name="x17157").wtPort(bus_1762_s)
      val x17154 = ScalarFIFO(size=1,name="x17154").wtPort(bus_1756_s)
      val x17162 = ScalarFIFO(size=1,name="x17162").wtPort(bus_1760_s)
      val x17140 = ScalarBuffer(name="x17140").wtPort(x17140_x17147_s)
      Stage(operands=List(CU.load(x17154), CU.load(x17157)), op=FixAdd, results=List(x17159))
      Stage(operands=List(x17159, Const(64)), op=FixMod, results=List(x17160))
      Stage(operands=List(Const(64), x17160), op=FixSub, results=List(x17161, CU.scalarOut(bus_1765_s)))
      Stage(operands=List(x17161, Const(4)), op=FixDiv, results=List(x17163))
      Stage(operands=List(CU.load(x17140), CU.load(x17162)), op=FixAdd, results=List(x17165))
      Stage(operands=List(x17165, x17163), op=FixAdd, results=List(CU.scalarOut(x17150_b18365_x17176_b18373_s)))
    }
    val x17177_2 = Pipeline(name="x17177_2",parent=x17177) { implicit CU => 
      val x17158 = CU.temp(None)
      val x17167 = CU.temp(None)
      val x17154 = ScalarFIFO(size=1,name="x17154").wtPort(bus_1756_s)
      val x17157 = ScalarFIFO(size=1,name="x17157").wtPort(bus_1762_s)
      val x17170 = ScalarBuffer(name="x17170").wtPort(training_targets_dram_da)
      val x17155 = ScalarFIFO(size=1,name="x17155").wtPort(bus_1758_s)
      val x17161 = ScalarFIFO(size=1,name="x17161").wtPort(bus_1765_s)
      Stage(operands=List(CU.load(x17157), CU.load(x17155)), op=FixAdd, results=List(x17167))
      Stage(operands=List(x17167, CU.load(x17161)), op=FixAdd, results=List(CU.scalarOut(x17149_b18364_x17174_b18372_s)))
      Stage(operands=List(CU.load(x17154), CU.load(x17155)), op=FixSub, results=List(x17158))
      Stage(operands=List(x17158, CU.load(x17170)), op=FixAdd, results=List(CU.scalarOut(x17149_b18363_x17174_b18371_s)))
    }
    val x17178 = MemoryController(name="x17178",parent=x17206,offchip=training_targets_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x17149_b18364 = ScalarFIFO(size=1,name="size").wtPort(x17149_b18364_x17174_b18372_s)
      val x17149_b18363 = ScalarFIFO(size=1,name="offset").wtPort(x17149_b18363_x17174_b18371_s)
      CU.newSout("data", x17151_x17178_data_s)
    }
    val x17205 = Sequential(name="x17205",parent=x17206) { implicit CU => 
      val x17205_unit = CounterChain(name = "x17205_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17204_0 = Pipeline(name="x17204_0",parent=x17205) { implicit CU => 
      val x17180 = ScalarBuffer(name="x17180").wtPort(x17150_b18367_x17176_b18375_s)
      val x17179 = ScalarBuffer(name="x17179").wtPort(x17150_b18366_x17176_b18374_s)
      val x17181 = ScalarBuffer(name="x17181").wtPort(x17150_b18365_x17176_b18373_s)
      val ctr13 = Counter(min=Const(0), max=x17181.readPort, step=Const(1), par=1) // Counter
      val x17192 = CounterChain(name = "x17192", ctr13).iter(1)
      Stage(operands=List(CU.load(x17179), CU.ctr(x17192(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x17192(0)), CU.load(x17180)), op=FixLt, results=List())
    }
    val x17230 = MetaPipeline(name="x17230",parent=x17872) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17208 = CounterChain(name = "x17208", ctr14).iter(64)
    }
    val x17223_0 = Pipeline(name="x17223_0",parent=x17230) { implicit CU => 
      val x17215 = ScalarFIFO(size=1,name="x17215").wtPort(x17062_1_s)
      val x17214 = ScalarFIFO(size=1,name="x17214").wtPort(x16909_4_s)
      val ctr15 = Counter(min=Const(0), max=Const(13), step=Const(1), par=16) // Counter
      val x17211 = CounterChain(name = "x17211", ctr15).iter(1)
      Stage(operands=List(CU.load(x17214), CU.load(x17215)), op=FixMul, results=List(CU.reduce))
      val (_, rr1784) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17223_0")
      Stage(operands=List(rr1784), op=Bypass, results=List(CU.scalarOut(x17209_x17222_s)))
    }
    val x17229_0 = Pipeline(name="x17229_0",parent=x17230) { implicit CU => 
      val x17225 = ScalarFIFO(size=1,name="x17225").wtPort(x16906_4_s)
      val x17209 = ScalarBuffer(name="x17209").wtPort(x17209_x17222_s)
      val x17229_unit = CounterChain(name = "x17229_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17209), CU.load(x17225)), op=FixAdd, results=List(CU.scalarOut(x17056_x17228_s)))
    }
    val x17242_0 = Pipeline(name="x17242_0",parent=x17872) { implicit CU => 
      val x17236 = CU.temp(None)
      val x17239 = CU.temp(None)
      val x17234 = ScalarFIFO(size=1,name="x17234").wtPort(x17056_2_s)
      val ctr16 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17232 = CounterChain(name = "x17232", ctr16).iter(4)
      Stage(operands=List(CU.load(x17234), Const(0)), op=FixLt, results=List(x17239))
      Stage(operands=List(x17239, Const(0), CU.load(x17234)), op=Mux, results=List(CU.scalarOut(x17056_x17241_s)))
      Stage(operands=List(Const(1), CU.load(x17234)), op=FixSub, results=List(x17236))
      Stage(operands=List(CU.load(x17234), x17236), op=FixMul, results=List(CU.scalarOut(x17059_x17238_s)))
    }
    val x17266 = MetaPipeline(name="x17266",parent=x17872) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17244 = CounterChain(name = "x17244", ctr17).iter(64)
    }
    val x17259_0 = Pipeline(name="x17259_0",parent=x17266) { implicit CU => 
      val x17251 = ScalarFIFO(size=1,name="x17251").wtPort(x17056_1_s)
      val x17250 = ScalarFIFO(size=1,name="x17250").wtPort(x16910_5_s)
      val ctr18 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17247 = CounterChain(name = "x17247", ctr18).iter(4)
      Stage(operands=List(CU.load(x17250), CU.load(x17251)), op=FixMul, results=List(CU.reduce))
      val (_, rr1800) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17259_0")
      Stage(operands=List(rr1800), op=Bypass, results=List(CU.scalarOut(x17245_x17258_s)))
    }
    val x17265_0 = Pipeline(name="x17265_0",parent=x17266) { implicit CU => 
      val x17261 = ScalarFIFO(size=1,name="x17261").wtPort(x16907_4_s)
      val x17245 = ScalarBuffer(name="x17245").wtPort(x17245_x17258_s)
      val x17265_unit = CounterChain(name = "x17265_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17245), CU.load(x17261)), op=FixAdd, results=List(CU.scalarOut(x17057_x17264_s)))
    }
    val x17278_0 = Pipeline(name="x17278_0",parent=x17872) { implicit CU => 
      val x17275 = CU.temp(None)
      val x17272 = CU.temp(None)
      val x17270 = ScalarFIFO(size=1,name="x17270").wtPort(x17057_2_s)
      val ctr19 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17268 = CounterChain(name = "x17268", ctr19).iter(4)
      Stage(operands=List(CU.load(x17270), Const(0)), op=FixLt, results=List(x17275))
      Stage(operands=List(x17275, Const(0), CU.load(x17270)), op=Mux, results=List(CU.scalarOut(x17057_x17277_s)))
      Stage(operands=List(Const(1), CU.load(x17270)), op=FixSub, results=List(x17272))
      Stage(operands=List(CU.load(x17270), x17272), op=FixMul, results=List(CU.scalarOut(x17060_x17274_s)))
    }
    val x17302 = MetaPipeline(name="x17302",parent=x17872) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17280 = CounterChain(name = "x17280", ctr20).iter(3)
    }
    val x17295_0 = Pipeline(name="x17295_0",parent=x17302) { implicit CU => 
      val x17287 = ScalarFIFO(size=1,name="x17287").wtPort(x17057_1_s)
      val x17286 = ScalarFIFO(size=1,name="x17286").wtPort(x16911_5_s)
      val ctr21 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17283 = CounterChain(name = "x17283", ctr21).iter(4)
      Stage(operands=List(CU.load(x17286), CU.load(x17287)), op=FixMul, results=List(CU.reduce))
      val (_, rr1816) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17295_0")
      Stage(operands=List(rr1816), op=Bypass, results=List(CU.scalarOut(x17281_x17294_s)))
    }
    val x17301_0 = Pipeline(name="x17301_0",parent=x17302) { implicit CU => 
      val x17297 = ScalarFIFO(size=1,name="x17297").wtPort(x16908_4_s)
      val x17281 = ScalarBuffer(name="x17281").wtPort(x17281_x17294_s)
      val x17301_unit = CounterChain(name = "x17301_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17281), CU.load(x17297)), op=FixAdd, results=List(CU.scalarOut(x17058_x17300_s)))
    }
    val x17314_0 = Pipeline(name="x17314_0",parent=x17872) { implicit CU => 
      val x17311 = CU.temp(None)
      val x17308 = CU.temp(None)
      val x17306 = ScalarFIFO(size=1,name="x17306").wtPort(x17058_2_s)
      val ctr22 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17304 = CounterChain(name = "x17304", ctr22).iter(1)
      Stage(operands=List(CU.load(x17306), Const(0)), op=FixLt, results=List(x17311))
      Stage(operands=List(x17311, Const(0), CU.load(x17306)), op=Mux, results=List(CU.scalarOut(x17058_x17313_s)))
      Stage(operands=List(Const(1), CU.load(x17306)), op=FixSub, results=List(x17308))
      Stage(operands=List(CU.load(x17306), x17308), op=FixMul, results=List(CU.scalarOut(x17061_x17310_s)))
    }
    val x17344 = StreamController(name="x17344",parent=x17872) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17317 = CounterChain(name = "x17317", ctr23).iter(1)
    }
    val x17344_0 = Pipeline(name="x17344_0",parent=x17344) { implicit CU => 
      val x17325 = CU.temp(None)
      val x17322 = CU.temp(None)
      val x17319 = ScalarFIFO(size=1,name="x17319").wtPort(x17058_1_s)
      Stage(operands=List(CU.load(x17319)), op=FixNeg, results=List(x17322, CU.vecOut(bus_1828_v)))
      Stage(operands=List(x17322, Const(-3.5)), op=FixLt, results=List(CU.vecOut(bus_1830_v)))
      Stage(operands=List(x17322, Const(-1.2)), op=FixLt, results=List(CU.vecOut(bus_1832_v)))
      Stage(operands=List(x17322, Const(0.1)), op=FixMul, results=List(x17325))
      Stage(operands=List(x17325, Const(0.35)), op=FixAdd, results=List(CU.vecOut(bus_1836_v)))
    }
    val x17344_1 = Pipeline(name="x17344_1",parent=x17344) { implicit CU => 
      val x17327 = CU.temp(None)
      val x17328 = CU.temp(None)
      val x17329 = CU.temp(None)
      val x17331 = CU.temp(None)
      val x17322 = VectorFIFO(size=1,name="x17322").wtPort(bus_1828_v)
      Stage(operands=List(Const(1), CU.load(x17322)), op=FixAdd, results=List(x17327))
      Stage(operands=List(CU.load(x17322), CU.load(x17322)), op=FixMul, results=List(x17328))
      Stage(operands=List(x17328, Const(2)), op=FixDiv, results=List(x17329))
      Stage(operands=List(x17327, x17329), op=FixAdd, results=List(CU.vecOut(bus_1842_v)))
      Stage(operands=List(x17328, CU.load(x17322)), op=FixMul, results=List(x17331, CU.vecOut(bus_1843_v)))
      Stage(operands=List(x17331, Const(6)), op=FixDiv, results=List(CU.vecOut(bus_1845_v)))
    }
    val x17344_2 = Pipeline(name="x17344_2",parent=x17344) { implicit CU => 
      val x17333 = CU.temp(None)
      val x17337 = CU.temp(None)
      val x17335 = CU.temp(None)
      val x17334 = CU.temp(None)
      val x17331 = VectorFIFO(size=1,name="x17331").wtPort(bus_1843_v)
      val x17330 = VectorFIFO(size=1,name="x17330").wtPort(bus_1842_v)
      val x17322 = VectorFIFO(size=1,name="x17322").wtPort(bus_1828_v)
      val x17332 = VectorFIFO(size=1,name="x17332").wtPort(bus_1845_v)
      Stage(operands=List(CU.load(x17330), CU.load(x17332)), op=FixAdd, results=List(x17333))
      Stage(operands=List(CU.load(x17331), CU.load(x17322)), op=FixMul, results=List(x17334))
      Stage(operands=List(x17334, Const(24)), op=FixDiv, results=List(x17335))
      Stage(operands=List(x17333, x17335), op=FixAdd, results=List(CU.vecOut(bus_1850_v)))
      Stage(operands=List(x17334, CU.load(x17322)), op=FixMul, results=List(x17337))
      Stage(operands=List(x17337, Const(120)), op=FixDiv, results=List(CU.vecOut(bus_1853_v)))
    }
    val x17344_3 = Pipeline(name="x17344_3",parent=x17344) { implicit CU => 
      val x17339 = CU.temp(None)
      val x17336 = VectorFIFO(size=1,name="x17336").wtPort(bus_1850_v)
      val x17326 = VectorFIFO(size=1,name="x17326").wtPort(bus_1836_v)
      val x17338 = VectorFIFO(size=1,name="x17338").wtPort(bus_1853_v)
      val x17324 = VectorFIFO(size=1,name="x17324").wtPort(bus_1832_v)
      Stage(operands=List(CU.load(x17336), CU.load(x17338)), op=FixAdd, results=List(x17339))
      Stage(operands=List(CU.load(x17324), CU.load(x17326), x17339), op=Mux, results=List(CU.vecOut(bus_1855_v)))
    }
    val x17344_4 = Pipeline(name="x17344_4",parent=x17344) { implicit CU => 
      val x17340 = VectorFIFO(size=1,name="x17340").wtPort(bus_1855_v)
      val x17323 = VectorFIFO(size=1,name="x17323").wtPort(bus_1830_v)
      Stage(operands=List(CU.load(x17323), Const(0), CU.load(x17340)), op=Mux, results=List(CU.reduce))
      val (_, rr1860) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17344")
      Stage(operands=List(rr1860), op=Bypass, results=List(CU.scalarOut(x17315_x17343_s)))
    }
    val x17373 = StreamController(name="x17373",parent=x17872) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17346 = CounterChain(name = "x17346", ctr24).iter(1)
    }
    val x17373_0 = Pipeline(name="x17373_0",parent=x17373) { implicit CU => 
      val x17353 = CU.temp(None)
      val x17350 = CU.temp(None)
      val x17348 = ScalarFIFO(size=1,name="x17348").wtPort(x17058_0_s)
      Stage(operands=List(CU.load(x17348)), op=FixNeg, results=List(x17350, CU.vecOut(bus_1862_v)))
      Stage(operands=List(x17350, Const(-3.5)), op=FixLt, results=List(CU.vecOut(bus_1864_v)))
      Stage(operands=List(x17350, Const(-1.2)), op=FixLt, results=List(CU.vecOut(bus_1866_v)))
      Stage(operands=List(x17350, Const(0.1)), op=FixMul, results=List(x17353))
      Stage(operands=List(x17353, Const(0.35)), op=FixAdd, results=List(CU.vecOut(bus_1870_v)))
    }
    val x17373_1 = Pipeline(name="x17373_1",parent=x17373) { implicit CU => 
      val x17359 = CU.temp(None)
      val x17355 = CU.temp(None)
      val x17356 = CU.temp(None)
      val x17357 = CU.temp(None)
      val x17350 = VectorFIFO(size=1,name="x17350").wtPort(bus_1862_v)
      Stage(operands=List(Const(1), CU.load(x17350)), op=FixAdd, results=List(x17355))
      Stage(operands=List(CU.load(x17350), CU.load(x17350)), op=FixMul, results=List(x17356))
      Stage(operands=List(x17356, Const(2)), op=FixDiv, results=List(x17357))
      Stage(operands=List(x17355, x17357), op=FixAdd, results=List(CU.vecOut(bus_1876_v)))
      Stage(operands=List(x17356, CU.load(x17350)), op=FixMul, results=List(x17359, CU.vecOut(bus_1877_v)))
      Stage(operands=List(x17359, Const(6)), op=FixDiv, results=List(CU.vecOut(bus_1879_v)))
    }
    val x17373_2 = Pipeline(name="x17373_2",parent=x17373) { implicit CU => 
      val x17365 = CU.temp(None)
      val x17361 = CU.temp(None)
      val x17363 = CU.temp(None)
      val x17362 = CU.temp(None)
      val x17359 = VectorFIFO(size=1,name="x17359").wtPort(bus_1877_v)
      val x17350 = VectorFIFO(size=1,name="x17350").wtPort(bus_1862_v)
      val x17358 = VectorFIFO(size=1,name="x17358").wtPort(bus_1876_v)
      val x17360 = VectorFIFO(size=1,name="x17360").wtPort(bus_1879_v)
      Stage(operands=List(CU.load(x17358), CU.load(x17360)), op=FixAdd, results=List(x17361))
      Stage(operands=List(CU.load(x17359), CU.load(x17350)), op=FixMul, results=List(x17362))
      Stage(operands=List(x17362, Const(24)), op=FixDiv, results=List(x17363))
      Stage(operands=List(x17361, x17363), op=FixAdd, results=List(CU.vecOut(bus_1884_v)))
      Stage(operands=List(x17362, CU.load(x17350)), op=FixMul, results=List(x17365))
      Stage(operands=List(x17365, Const(120)), op=FixDiv, results=List(CU.vecOut(bus_1887_v)))
    }
    val x17373_3 = Pipeline(name="x17373_3",parent=x17373) { implicit CU => 
      val x17367 = CU.temp(None)
      val x17352 = VectorFIFO(size=1,name="x17352").wtPort(bus_1866_v)
      val x17366 = VectorFIFO(size=1,name="x17366").wtPort(bus_1887_v)
      val x17354 = VectorFIFO(size=1,name="x17354").wtPort(bus_1870_v)
      val x17364 = VectorFIFO(size=1,name="x17364").wtPort(bus_1884_v)
      Stage(operands=List(CU.load(x17364), CU.load(x17366)), op=FixAdd, results=List(x17367))
      Stage(operands=List(CU.load(x17352), CU.load(x17354), x17367), op=Mux, results=List(CU.vecOut(bus_1889_v)))
    }
    val x17373_4 = Pipeline(name="x17373_4",parent=x17373) { implicit CU => 
      val x17369 = CU.temp(None)
      val x17315 = ScalarBuffer(name="x17315").wtPort(x17315_x17343_s)
      val x17368 = VectorFIFO(size=1,name="x17368").wtPort(bus_1889_v)
      val x17351 = VectorFIFO(size=1,name="x17351").wtPort(bus_1864_v)
      Stage(operands=List(CU.load(x17351), Const(0), CU.load(x17368)), op=Mux, results=List(x17369))
      Stage(operands=List(x17369, CU.load(x17315)), op=FixDiv, results=List(CU.scalarOut(x17064_x17372_s)))
    }
    val x17387_0 = Pipeline(name="x17387_0",parent=x17872) { implicit CU => 
      val x17382 = CU.temp(None)
      val x17381 = CU.temp(None)
      val x17383 = ScalarFIFO(size=1,name="x17383").wtPort(x17061_0_s)
      val x17377 = ScalarFIFO(size=1,name="x17377").wtPort(x17064_0_s)
      val x17379 = ScalarFIFO(size=1,name="x17379").wtPort(x17063_0_s)
      val ctr25 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17375 = CounterChain(name = "x17375", ctr25).iter(1)
      Stage(operands=List(CU.load(x17377), CU.load(x17379)), op=FixSub, results=List(x17381))
      Stage(operands=List(x17381), op=FixNeg, results=List(x17382))
      Stage(operands=List(x17382, CU.load(x17383)), op=FixMul, results=List(CU.scalarOut(x17065_x17386_s)))
    }
    val x17398_0 = Pipeline(name="x17398_0",parent=x17872) { implicit CU => 
      val x17393 = ScalarFIFO(size=1,name="x17393").wtPort(x17057_0_s)
      val x17394 = ScalarFIFO(size=1,name="x17394").wtPort(x17065_2_s)
      val ctr26 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr27 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17390 = CounterChain(name = "x17390", ctr26, ctr27).iter(64)
      Stage(operands=List(CU.load(x17393), CU.load(x17394)), op=FixMul, results=List(CU.scalarOut(x17068_x17397_s)))
    }
    val x17427 = Sequential(name="x17427",parent=x17872) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17400 = CounterChain(name = "x17400", ctr28).iter(64)
    }
    val x17420_0 = Pipeline(name="x17420_0",parent=x17427) { implicit CU => 
      val x17412 = ScalarFIFO(size=1,name="x17412").wtPort(x16911_4_s)
      val x17411 = ScalarFIFO(size=1,name="x17411").wtPort(x17065_1_s)
      val ctr29 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17408 = CounterChain(name = "x17408", ctr29).iter(1)
      Stage(operands=List(CU.load(x17411), CU.load(x17412)), op=FixMul, results=List(CU.reduce))
      val (_, rr1906) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17420_0")
      Stage(operands=List(rr1906), op=Bypass, results=List(CU.scalarOut(x17402_x17419_s)))
    }
    val x17426_0 = Pipeline(name="x17426_0",parent=x17427) { implicit CU => 
      val x17401 = ScalarBuffer(name="x17401").wtPort(x17060_0_s)
      val x17402 = ScalarBuffer(name="x17402").wtPort(x17402_x17419_s)
      val x17426_unit = CounterChain(name = "x17426_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17401), CU.load(x17402)), op=FixMul, results=List(CU.scalarOut(x17070_x17425_s)))
    }
    val x17438_0 = Pipeline(name="x17438_0",parent=x17872) { implicit CU => 
      val x17434 = ScalarFIFO(size=1,name="x17434").wtPort(x17070_2_s)
      val x17433 = ScalarFIFO(size=1,name="x17433").wtPort(x17056_0_s)
      val ctr30 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17430 = CounterChain(name = "x17430", ctr30, ctr31).iter(256)
      Stage(operands=List(CU.load(x17433), CU.load(x17434)), op=FixSub, results=List(CU.scalarOut(x17067_x17437_s)))
    }
    val x17467 = Sequential(name="x17467",parent=x17872) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17440 = CounterChain(name = "x17440", ctr32).iter(64)
    }
    val x17460_0 = Pipeline(name="x17460_0",parent=x17467) { implicit CU => 
      val x17452 = ScalarFIFO(size=1,name="x17452").wtPort(x16910_4_s)
      val x17451 = ScalarFIFO(size=1,name="x17451").wtPort(x17070_1_s)
      val ctr33 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17448 = CounterChain(name = "x17448", ctr33).iter(4)
      Stage(operands=List(CU.load(x17451), CU.load(x17452)), op=FixMul, results=List(CU.reduce))
      val (_, rr1919) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17460_0")
      Stage(operands=List(rr1919), op=Bypass, results=List(CU.scalarOut(x17442_x17459_s)))
    }
    val x17466_0 = Pipeline(name="x17466_0",parent=x17467) { implicit CU => 
      val x17442 = ScalarBuffer(name="x17442").wtPort(x17442_x17459_s)
      val x17441 = ScalarBuffer(name="x17441").wtPort(x17059_0_s)
      val x17466_unit = CounterChain(name = "x17466_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17441), CU.load(x17442)), op=FixMul, results=List(CU.scalarOut(x17069_x17465_s)))
    }
    val x17478_0 = Pipeline(name="x17478_0",parent=x17872) { implicit CU => 
      val x17473 = ScalarFIFO(size=1,name="x17473").wtPort(x17069_1_s)
      val x17475 = ScalarFIFO(size=1,name="x17475").wtPort(x17062_0_s)
      val ctr34 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr35 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17470 = CounterChain(name = "x17470", ctr34, ctr35).iter(52)
      Stage(operands=List(CU.load(x17473), CU.load(x17475)), op=FixMul, results=List(CU.scalarOut(x17066_x17477_s)))
    }
    val x17498_0 = Pipeline(name="x17498_0",parent=x17872) { implicit CU => 
      val x17495 = CU.temp(None)
      val x17479 = CU.temp(Some(0))
      val x17489 = CU.temp(None)
      val x17485 = ScalarFIFO(size=1,name="x17485").wtPort(x16909_3_s)
      val x17492 = ScalarFIFO(size=1,name="x17492").wtPort(x16909_2_s)
      val x17486 = ScalarFIFO(size=1,name="x17486").wtPort(x17066_0_s)
      val ctr36 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr37 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17482 = CounterChain(name = "x17482", ctr36, ctr37).iter(52)
      Stage(operands=List(CU.load(x17492), CU.load(x17492)), op=FixMul, results=List(x17495))
      Stage(operands=List(x17495, x17479), op=FixAdd, results=List(CU.scalarOut(x17479_x17497_s)))
      Stage(operands=List(CU.load(x17486), Const(0.01)), op=FixMul, results=List(x17489))
      Stage(operands=List(CU.load(x17485), x17489), op=FixSub, results=List(CU.scalarOut(x16909_x17491_s)))
    }
    val x17516_0 = Pipeline(name="x17516_0",parent=x17872) { implicit CU => 
      val x17507 = CU.temp(None)
      val x17513 = CU.temp(None)
      val x17499 = CU.temp(Some(0))
      val x17503 = ScalarFIFO(size=1,name="x17503").wtPort(x16906_3_s)
      val x17510 = ScalarFIFO(size=1,name="x17510").wtPort(x16906_2_s)
      val x17504 = ScalarFIFO(size=1,name="x17504").wtPort(x17069_0_s)
      val ctr38 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17501 = CounterChain(name = "x17501", ctr38).iter(4)
      Stage(operands=List(CU.load(x17510), CU.load(x17510)), op=FixMul, results=List(x17513))
      Stage(operands=List(x17513, x17499), op=FixAdd, results=List(CU.scalarOut(x17499_x17515_s)))
      Stage(operands=List(CU.load(x17504), Const(0.01)), op=FixMul, results=List(x17507))
      Stage(operands=List(CU.load(x17503), x17507), op=FixSub, results=List(CU.scalarOut(x16906_x17509_s)))
    }
    val x17589 = StreamController(name="x17589",parent=x17872) { implicit CU => 
      val x17589_unit = CounterChain(name = "x17589_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17589_0 = Pipeline(name="x17589_0",parent=x17589) { implicit CU => 
      val x17555 = CU.temp(None)
      val x17556 = CU.temp(None)
      val x17558 = CU.temp(None)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      Stage(operands=List(CU.load(x17499), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_1940_s)))
      Stage(operands=List(CU.load(x17499), Const(1)), op=FixSub, results=List(x17555, CU.scalarOut(bus_1942_s)))
      Stage(operands=List(x17555, Const(2)), op=FixDiv, results=List(x17556))
      Stage(operands=List(Const(1), x17556), op=FixAdd, results=List(CU.scalarOut(bus_1944_s)))
      Stage(operands=List(x17555, x17555), op=FixMul, results=List(x17558, CU.scalarOut(bus_1945_s)))
      Stage(operands=List(x17558, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_1947_s)))
    }
    val x17589_1 = Pipeline(name="x17589_1",parent=x17589) { implicit CU => 
      val x17560 = CU.temp(None)
      val x17562 = CU.temp(None)
      val x17561 = CU.temp(None)
      val x17555 = ScalarFIFO(size=1,name="x17555").wtPort(bus_1942_s)
      val x17558 = ScalarFIFO(size=1,name="x17558").wtPort(bus_1945_s)
      val x17557 = ScalarFIFO(size=1,name="x17557").wtPort(bus_1944_s)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      val x17559 = ScalarFIFO(size=1,name="x17559").wtPort(bus_1947_s)
      Stage(operands=List(CU.load(x17557), CU.load(x17559)), op=FixSub, results=List(x17560))
      Stage(operands=List(CU.load(x17558), CU.load(x17555)), op=FixMul, results=List(x17561))
      Stage(operands=List(x17561, Const(16)), op=FixDiv, results=List(x17562))
      Stage(operands=List(x17560, x17562), op=FixAdd, results=List(CU.scalarOut(bus_1952_s)))
      Stage(operands=List(CU.load(x17499), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_1954_s)))
      Stage(operands=List(CU.load(x17499), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_1956_s)))
    }
    val x17589_2 = Pipeline(name="x17589_2",parent=x17589) { implicit CU => 
      val x17568 = CU.temp(None)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      val x17565 = ScalarFIFO(size=1,name="x17565").wtPort(bus_1956_s)
      Stage(operands=List(CU.load(x17565), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_1957_s)))
      Stage(operands=List(CU.load(x17499), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_1959_s)))
      Stage(operands=List(CU.load(x17499), Const(0.08)), op=FixMul, results=List(x17568))
      Stage(operands=List(x17568, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_1963_s)))
      Stage(operands=List(CU.load(x17499), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_1965_s)))
      Stage(operands=List(CU.load(x17499), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_1967_s)))
    }
    val x17589_3 = Pipeline(name="x17589_3",parent=x17589) { implicit CU => 
      val x17574 = CU.temp(None)
      val x17571 = ScalarFIFO(size=1,name="x17571").wtPort(bus_1967_s)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      Stage(operands=List(CU.load(x17571), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_1968_s)))
      Stage(operands=List(CU.load(x17499), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_1970_s)))
      Stage(operands=List(CU.load(x17499), Const(0.008)), op=FixMul, results=List(x17574))
      Stage(operands=List(x17574, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_1974_s)))
      Stage(operands=List(CU.load(x17499), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_1976_s)))
      Stage(operands=List(CU.load(x17499), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_1978_s)))
    }
    val x17589_4 = Pipeline(name="x17589_4",parent=x17589) { implicit CU => 
      val x17580 = CU.temp(None)
      val x17581 = CU.temp(None)
      val x17578 = CU.temp(None)
      val x17579 = CU.temp(None)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      val x17577 = ScalarFIFO(size=1,name="x17577").wtPort(bus_1978_s)
      val x17573 = ScalarFIFO(size=1,name="x17573").wtPort(bus_1970_s)
      val x17576 = ScalarFIFO(size=1,name="x17576").wtPort(bus_1976_s)
      val x17575 = ScalarFIFO(size=1,name="x17575").wtPort(bus_1974_s)
      Stage(operands=List(CU.load(x17577), Const(60)), op=FixAdd, results=List(x17578))
      Stage(operands=List(CU.load(x17499), Const(2.0E-4)), op=FixMul, results=List(x17579))
      Stage(operands=List(x17579, Const(300)), op=FixAdd, results=List(x17580))
      Stage(operands=List(CU.load(x17576), x17578, x17580), op=Mux, results=List(x17581))
      Stage(operands=List(CU.load(x17573), CU.load(x17575), x17581), op=Mux, results=List(CU.scalarOut(bus_1986_s)))
    }
    val x17589_5 = Pipeline(name="x17589_5",parent=x17589) { implicit CU => 
      val x17583 = CU.temp(None)
      val x17584 = CU.temp(None)
      val x17572 = ScalarFIFO(size=1,name="x17572").wtPort(bus_1968_s)
      val x17567 = ScalarFIFO(size=1,name="x17567").wtPort(bus_1959_s)
      val x17566 = ScalarFIFO(size=1,name="x17566").wtPort(bus_1957_s)
      val x17570 = ScalarFIFO(size=1,name="x17570").wtPort(bus_1965_s)
      val x17564 = ScalarFIFO(size=1,name="x17564").wtPort(bus_1954_s)
      val x17582 = ScalarFIFO(size=1,name="x17582").wtPort(bus_1986_s)
      val x17569 = ScalarFIFO(size=1,name="x17569").wtPort(bus_1963_s)
      Stage(operands=List(CU.load(x17570), CU.load(x17572), CU.load(x17582)), op=Mux, results=List(x17583))
      Stage(operands=List(CU.load(x17567), CU.load(x17569), x17583), op=Mux, results=List(x17584))
      Stage(operands=List(CU.load(x17564), CU.load(x17566), x17584), op=Mux, results=List(CU.scalarOut(bus_1989_s)))
    }
    val x17589_6 = Pipeline(name="x17589_6",parent=x17589) { implicit CU => 
      val x17522 = CU.temp(None)
      val x17521 = CU.temp(None)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      val x17563 = ScalarFIFO(size=1,name="x17563").wtPort(bus_1952_s)
      val x17585 = ScalarFIFO(size=1,name="x17585").wtPort(bus_1989_s)
      val x17554 = ScalarFIFO(size=1,name="x17554").wtPort(bus_1940_s)
      Stage(operands=List(CU.load(x17554), CU.load(x17563), CU.load(x17585)), op=Mux, results=List(CU.scalarOut(x17518_x17588_s)))
      Stage(operands=List(CU.load(x17479), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_1991_s)))
      Stage(operands=List(CU.load(x17479), Const(1)), op=FixSub, results=List(x17521, CU.scalarOut(bus_1992_s)))
      Stage(operands=List(x17521, Const(2)), op=FixDiv, results=List(x17522))
      Stage(operands=List(Const(1), x17522), op=FixAdd, results=List(CU.scalarOut(bus_1994_s)))
      Stage(operands=List(x17521, x17521), op=FixMul, results=List(CU.scalarOut(bus_1995_s)))
    }
    val x17589_7 = Pipeline(name="x17589_7",parent=x17589) { implicit CU => 
      val x17527 = CU.temp(None)
      val x17525 = CU.temp(None)
      val x17528 = CU.temp(None)
      val x17526 = CU.temp(None)
      val x17521 = ScalarFIFO(size=1,name="x17521").wtPort(bus_1992_s)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      val x17523 = ScalarFIFO(size=1,name="x17523").wtPort(bus_1994_s)
      val x17524 = ScalarFIFO(size=1,name="x17524").wtPort(bus_1995_s)
      Stage(operands=List(CU.load(x17524), Const(8)), op=FixDiv, results=List(x17525))
      Stage(operands=List(CU.load(x17523), x17525), op=FixSub, results=List(x17526))
      Stage(operands=List(CU.load(x17524), CU.load(x17521)), op=FixMul, results=List(x17527))
      Stage(operands=List(x17527, Const(16)), op=FixDiv, results=List(x17528))
      Stage(operands=List(x17526, x17528), op=FixAdd, results=List(CU.scalarOut(bus_2000_s)))
      Stage(operands=List(CU.load(x17479), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2001_s)))
    }
    val x17589_8 = Pipeline(name="x17589_8",parent=x17589) { implicit CU => 
      val x17534 = CU.temp(None)
      val x17531 = CU.temp(None)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      Stage(operands=List(CU.load(x17479), Const(0.22)), op=FixMul, results=List(x17531))
      Stage(operands=List(x17531, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2003_s)))
      Stage(operands=List(CU.load(x17479), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2004_s)))
      Stage(operands=List(CU.load(x17479), Const(0.08)), op=FixMul, results=List(x17534))
      Stage(operands=List(x17534, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2006_s)))
      Stage(operands=List(CU.load(x17479), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2007_s)))
    }
    val x17589_9 = Pipeline(name="x17589_9",parent=x17589) { implicit CU => 
      val x17540 = CU.temp(None)
      val x17537 = CU.temp(None)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      Stage(operands=List(CU.load(x17479), Const(0.028)), op=FixMul, results=List(x17537))
      Stage(operands=List(x17537, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2009_s)))
      Stage(operands=List(CU.load(x17479), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2010_s)))
      Stage(operands=List(CU.load(x17479), Const(0.008)), op=FixMul, results=List(x17540))
      Stage(operands=List(x17540, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2012_s)))
      Stage(operands=List(CU.load(x17479), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2013_s)))
    }
    val x17589_10 = Pipeline(name="x17589_10",parent=x17589) { implicit CU => 
      val x17547 = CU.temp(None)
      val x17544 = CU.temp(None)
      val x17546 = CU.temp(None)
      val x17543 = CU.temp(None)
      val x17545 = CU.temp(None)
      val x17542 = ScalarFIFO(size=1,name="x17542").wtPort(bus_2013_s)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      val x17541 = ScalarFIFO(size=1,name="x17541").wtPort(bus_2012_s)
      val x17539 = ScalarFIFO(size=1,name="x17539").wtPort(bus_2010_s)
      Stage(operands=List(CU.load(x17479), Const(0.003)), op=FixMul, results=List(x17543))
      Stage(operands=List(x17543, Const(60)), op=FixAdd, results=List(x17544))
      Stage(operands=List(CU.load(x17479), Const(2.0E-4)), op=FixMul, results=List(x17545))
      Stage(operands=List(x17545, Const(300)), op=FixAdd, results=List(x17546))
      Stage(operands=List(CU.load(x17542), x17544, x17546), op=Mux, results=List(x17547))
      Stage(operands=List(CU.load(x17539), CU.load(x17541), x17547), op=Mux, results=List(CU.scalarOut(bus_2019_s)))
    }
    val x17589_11 = Pipeline(name="x17589_11",parent=x17589) { implicit CU => 
      val x17549 = CU.temp(None)
      val x17550 = CU.temp(None)
      val x17530 = ScalarFIFO(size=1,name="x17530").wtPort(bus_2001_s)
      val x17536 = ScalarFIFO(size=1,name="x17536").wtPort(bus_2007_s)
      val x17548 = ScalarFIFO(size=1,name="x17548").wtPort(bus_2019_s)
      val x17535 = ScalarFIFO(size=1,name="x17535").wtPort(bus_2006_s)
      val x17532 = ScalarFIFO(size=1,name="x17532").wtPort(bus_2003_s)
      val x17538 = ScalarFIFO(size=1,name="x17538").wtPort(bus_2009_s)
      val x17533 = ScalarFIFO(size=1,name="x17533").wtPort(bus_2004_s)
      Stage(operands=List(CU.load(x17536), CU.load(x17538), CU.load(x17548)), op=Mux, results=List(x17549))
      Stage(operands=List(CU.load(x17533), CU.load(x17535), x17549), op=Mux, results=List(x17550))
      Stage(operands=List(CU.load(x17530), CU.load(x17532), x17550), op=Mux, results=List(CU.scalarOut(bus_2022_s)))
    }
    val x17589_12 = Pipeline(name="x17589_12",parent=x17589) { implicit CU => 
      val x17520 = ScalarFIFO(size=1,name="x17520").wtPort(bus_1991_s)
      val x17551 = ScalarFIFO(size=1,name="x17551").wtPort(bus_2022_s)
      val x17529 = ScalarFIFO(size=1,name="x17529").wtPort(bus_2000_s)
      Stage(operands=List(CU.load(x17520), CU.load(x17529), CU.load(x17551)), op=Mux, results=List(CU.scalarOut(x17517_x17587_s)))
    }
    val x17600_0 = Pipeline(name="x17600_0",parent=x17872) { implicit CU => 
      val x17595 = ScalarFIFO(size=1,name="x17595").wtPort(x16909_1_s)
      val x17517 = ScalarBuffer(name="x17517").wtPort(x17517_x17587_s)
      val ctr39 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr40 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17592 = CounterChain(name = "x17592", ctr39, ctr40).iter(52)
      Stage(operands=List(CU.load(x17595), CU.load(x17517)), op=FixDiv, results=List(CU.scalarOut(x16909_x17599_s)))
    }
    val x17609_0 = Pipeline(name="x17609_0",parent=x17872) { implicit CU => 
      val x17518 = ScalarBuffer(name="x17518").wtPort(x17518_x17588_s)
      val x17604 = ScalarFIFO(size=1,name="x17604").wtPort(x16906_1_s)
      val ctr41 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17602 = CounterChain(name = "x17602", ctr41).iter(4)
      Stage(operands=List(CU.load(x17604), CU.load(x17518)), op=FixDiv, results=List(CU.scalarOut(x16906_x17608_s)))
    }
    val x17629_0 = Pipeline(name="x17629_0",parent=x17872) { implicit CU => 
      val x17626 = CU.temp(None)
      val x17620 = CU.temp(None)
      val x17610 = CU.temp(Some(0))
      val x17623 = ScalarFIFO(size=1,name="x17623").wtPort(x16910_2_s)
      val x17617 = ScalarFIFO(size=1,name="x17617").wtPort(x17067_0_s)
      val x17616 = ScalarFIFO(size=1,name="x17616").wtPort(x16910_3_s)
      val ctr42 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr43 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17613 = CounterChain(name = "x17613", ctr42, ctr43).iter(256)
      Stage(operands=List(CU.load(x17623), CU.load(x17623)), op=FixMul, results=List(x17626))
      Stage(operands=List(x17626, x17610), op=FixAdd, results=List(CU.scalarOut(x17610_x17628_s)))
      Stage(operands=List(CU.load(x17617), Const(0.01)), op=FixMul, results=List(x17620))
      Stage(operands=List(CU.load(x17616), x17620), op=FixSub, results=List(CU.scalarOut(x16910_x17622_s)))
    }
    val x17647_0 = Pipeline(name="x17647_0",parent=x17872) { implicit CU => 
      val x17644 = CU.temp(None)
      val x17638 = CU.temp(None)
      val x17630 = CU.temp(Some(0))
      val x17641 = ScalarFIFO(size=1,name="x17641").wtPort(x16907_2_s)
      val x17635 = ScalarFIFO(size=1,name="x17635").wtPort(x17070_0_s)
      val x17634 = ScalarFIFO(size=1,name="x17634").wtPort(x16907_3_s)
      val ctr44 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17632 = CounterChain(name = "x17632", ctr44).iter(4)
      Stage(operands=List(CU.load(x17641), CU.load(x17641)), op=FixMul, results=List(x17644))
      Stage(operands=List(x17644, x17630), op=FixAdd, results=List(CU.scalarOut(x17630_x17646_s)))
      Stage(operands=List(CU.load(x17635), Const(0.01)), op=FixMul, results=List(x17638))
      Stage(operands=List(CU.load(x17634), x17638), op=FixSub, results=List(CU.scalarOut(x16907_x17640_s)))
    }
    val x17720 = StreamController(name="x17720",parent=x17872) { implicit CU => 
      val x17720_unit = CounterChain(name = "x17720_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17720_0 = Pipeline(name="x17720_0",parent=x17720) { implicit CU => 
      val x17689 = CU.temp(None)
      val x17686 = CU.temp(None)
      val x17687 = CU.temp(None)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      Stage(operands=List(CU.load(x17630), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2043_s)))
      Stage(operands=List(CU.load(x17630), Const(1)), op=FixSub, results=List(x17686, CU.scalarOut(bus_2045_s)))
      Stage(operands=List(x17686, Const(2)), op=FixDiv, results=List(x17687))
      Stage(operands=List(Const(1), x17687), op=FixAdd, results=List(CU.scalarOut(bus_2047_s)))
      Stage(operands=List(x17686, x17686), op=FixMul, results=List(x17689, CU.scalarOut(bus_2048_s)))
      Stage(operands=List(x17689, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_2050_s)))
    }
    val x17720_1 = Pipeline(name="x17720_1",parent=x17720) { implicit CU => 
      val x17691 = CU.temp(None)
      val x17693 = CU.temp(None)
      val x17692 = CU.temp(None)
      val x17688 = ScalarFIFO(size=1,name="x17688").wtPort(bus_2047_s)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      val x17690 = ScalarFIFO(size=1,name="x17690").wtPort(bus_2050_s)
      val x17689 = ScalarFIFO(size=1,name="x17689").wtPort(bus_2048_s)
      val x17686 = ScalarFIFO(size=1,name="x17686").wtPort(bus_2045_s)
      Stage(operands=List(CU.load(x17688), CU.load(x17690)), op=FixSub, results=List(x17691))
      Stage(operands=List(CU.load(x17689), CU.load(x17686)), op=FixMul, results=List(x17692))
      Stage(operands=List(x17692, Const(16)), op=FixDiv, results=List(x17693))
      Stage(operands=List(x17691, x17693), op=FixAdd, results=List(CU.scalarOut(bus_2055_s)))
      Stage(operands=List(CU.load(x17630), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2057_s)))
      Stage(operands=List(CU.load(x17630), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_2059_s)))
    }
    val x17720_2 = Pipeline(name="x17720_2",parent=x17720) { implicit CU => 
      val x17699 = CU.temp(None)
      val x17696 = ScalarFIFO(size=1,name="x17696").wtPort(bus_2059_s)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      Stage(operands=List(CU.load(x17696), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2060_s)))
      Stage(operands=List(CU.load(x17630), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2062_s)))
      Stage(operands=List(CU.load(x17630), Const(0.08)), op=FixMul, results=List(x17699))
      Stage(operands=List(x17699, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2066_s)))
      Stage(operands=List(CU.load(x17630), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2068_s)))
      Stage(operands=List(CU.load(x17630), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_2070_s)))
    }
    val x17720_3 = Pipeline(name="x17720_3",parent=x17720) { implicit CU => 
      val x17705 = CU.temp(None)
      val x17702 = ScalarFIFO(size=1,name="x17702").wtPort(bus_2070_s)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      Stage(operands=List(CU.load(x17702), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2071_s)))
      Stage(operands=List(CU.load(x17630), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2073_s)))
      Stage(operands=List(CU.load(x17630), Const(0.008)), op=FixMul, results=List(x17705))
      Stage(operands=List(x17705, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2077_s)))
      Stage(operands=List(CU.load(x17630), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2079_s)))
      Stage(operands=List(CU.load(x17630), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_2081_s)))
    }
    val x17720_4 = Pipeline(name="x17720_4",parent=x17720) { implicit CU => 
      val x17710 = CU.temp(None)
      val x17712 = CU.temp(None)
      val x17709 = CU.temp(None)
      val x17711 = CU.temp(None)
      val x17706 = ScalarFIFO(size=1,name="x17706").wtPort(bus_2077_s)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      val x17704 = ScalarFIFO(size=1,name="x17704").wtPort(bus_2073_s)
      val x17708 = ScalarFIFO(size=1,name="x17708").wtPort(bus_2081_s)
      val x17707 = ScalarFIFO(size=1,name="x17707").wtPort(bus_2079_s)
      Stage(operands=List(CU.load(x17708), Const(60)), op=FixAdd, results=List(x17709))
      Stage(operands=List(CU.load(x17630), Const(2.0E-4)), op=FixMul, results=List(x17710))
      Stage(operands=List(x17710, Const(300)), op=FixAdd, results=List(x17711))
      Stage(operands=List(CU.load(x17707), x17709, x17711), op=Mux, results=List(x17712))
      Stage(operands=List(CU.load(x17704), CU.load(x17706), x17712), op=Mux, results=List(CU.scalarOut(bus_2089_s)))
    }
    val x17720_5 = Pipeline(name="x17720_5",parent=x17720) { implicit CU => 
      val x17714 = CU.temp(None)
      val x17715 = CU.temp(None)
      val x17697 = ScalarFIFO(size=1,name="x17697").wtPort(bus_2060_s)
      val x17695 = ScalarFIFO(size=1,name="x17695").wtPort(bus_2057_s)
      val x17703 = ScalarFIFO(size=1,name="x17703").wtPort(bus_2071_s)
      val x17701 = ScalarFIFO(size=1,name="x17701").wtPort(bus_2068_s)
      val x17700 = ScalarFIFO(size=1,name="x17700").wtPort(bus_2066_s)
      val x17713 = ScalarFIFO(size=1,name="x17713").wtPort(bus_2089_s)
      val x17698 = ScalarFIFO(size=1,name="x17698").wtPort(bus_2062_s)
      Stage(operands=List(CU.load(x17701), CU.load(x17703), CU.load(x17713)), op=Mux, results=List(x17714))
      Stage(operands=List(CU.load(x17698), CU.load(x17700), x17714), op=Mux, results=List(x17715))
      Stage(operands=List(CU.load(x17695), CU.load(x17697), x17715), op=Mux, results=List(CU.scalarOut(bus_2092_s)))
    }
    val x17720_6 = Pipeline(name="x17720_6",parent=x17720) { implicit CU => 
      val x17653 = CU.temp(None)
      val x17652 = CU.temp(None)
      val x17685 = ScalarFIFO(size=1,name="x17685").wtPort(bus_2043_s)
      val x17716 = ScalarFIFO(size=1,name="x17716").wtPort(bus_2092_s)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      val x17694 = ScalarFIFO(size=1,name="x17694").wtPort(bus_2055_s)
      Stage(operands=List(CU.load(x17685), CU.load(x17694), CU.load(x17716)), op=Mux, results=List(CU.scalarOut(x17649_x17719_s)))
      Stage(operands=List(CU.load(x17610), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2094_s)))
      Stage(operands=List(CU.load(x17610), Const(1)), op=FixSub, results=List(x17652, CU.scalarOut(bus_2095_s)))
      Stage(operands=List(x17652, Const(2)), op=FixDiv, results=List(x17653))
      Stage(operands=List(Const(1), x17653), op=FixAdd, results=List(CU.scalarOut(bus_2097_s)))
      Stage(operands=List(x17652, x17652), op=FixMul, results=List(CU.scalarOut(bus_2098_s)))
    }
    val x17720_7 = Pipeline(name="x17720_7",parent=x17720) { implicit CU => 
      val x17657 = CU.temp(None)
      val x17659 = CU.temp(None)
      val x17658 = CU.temp(None)
      val x17656 = CU.temp(None)
      val x17652 = ScalarFIFO(size=1,name="x17652").wtPort(bus_2095_s)
      val x17655 = ScalarFIFO(size=1,name="x17655").wtPort(bus_2098_s)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      val x17654 = ScalarFIFO(size=1,name="x17654").wtPort(bus_2097_s)
      Stage(operands=List(CU.load(x17655), Const(8)), op=FixDiv, results=List(x17656))
      Stage(operands=List(CU.load(x17654), x17656), op=FixSub, results=List(x17657))
      Stage(operands=List(CU.load(x17655), CU.load(x17652)), op=FixMul, results=List(x17658))
      Stage(operands=List(x17658, Const(16)), op=FixDiv, results=List(x17659))
      Stage(operands=List(x17657, x17659), op=FixAdd, results=List(CU.scalarOut(bus_2103_s)))
      Stage(operands=List(CU.load(x17610), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2104_s)))
    }
    val x17720_8 = Pipeline(name="x17720_8",parent=x17720) { implicit CU => 
      val x17662 = CU.temp(None)
      val x17665 = CU.temp(None)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      Stage(operands=List(CU.load(x17610), Const(0.22)), op=FixMul, results=List(x17662))
      Stage(operands=List(x17662, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2106_s)))
      Stage(operands=List(CU.load(x17610), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2107_s)))
      Stage(operands=List(CU.load(x17610), Const(0.08)), op=FixMul, results=List(x17665))
      Stage(operands=List(x17665, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2109_s)))
      Stage(operands=List(CU.load(x17610), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2110_s)))
    }
    val x17720_9 = Pipeline(name="x17720_9",parent=x17720) { implicit CU => 
      val x17671 = CU.temp(None)
      val x17668 = CU.temp(None)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      Stage(operands=List(CU.load(x17610), Const(0.028)), op=FixMul, results=List(x17668))
      Stage(operands=List(x17668, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2112_s)))
      Stage(operands=List(CU.load(x17610), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2113_s)))
      Stage(operands=List(CU.load(x17610), Const(0.008)), op=FixMul, results=List(x17671))
      Stage(operands=List(x17671, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2115_s)))
      Stage(operands=List(CU.load(x17610), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2116_s)))
    }
    val x17720_10 = Pipeline(name="x17720_10",parent=x17720) { implicit CU => 
      val x17678 = CU.temp(None)
      val x17675 = CU.temp(None)
      val x17676 = CU.temp(None)
      val x17677 = CU.temp(None)
      val x17674 = CU.temp(None)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      val x17670 = ScalarFIFO(size=1,name="x17670").wtPort(bus_2113_s)
      val x17673 = ScalarFIFO(size=1,name="x17673").wtPort(bus_2116_s)
      val x17672 = ScalarFIFO(size=1,name="x17672").wtPort(bus_2115_s)
      Stage(operands=List(CU.load(x17610), Const(0.003)), op=FixMul, results=List(x17674))
      Stage(operands=List(x17674, Const(60)), op=FixAdd, results=List(x17675))
      Stage(operands=List(CU.load(x17610), Const(2.0E-4)), op=FixMul, results=List(x17676))
      Stage(operands=List(x17676, Const(300)), op=FixAdd, results=List(x17677))
      Stage(operands=List(CU.load(x17673), x17675, x17677), op=Mux, results=List(x17678))
      Stage(operands=List(CU.load(x17670), CU.load(x17672), x17678), op=Mux, results=List(CU.scalarOut(bus_2122_s)))
    }
    val x17720_11 = Pipeline(name="x17720_11",parent=x17720) { implicit CU => 
      val x17681 = CU.temp(None)
      val x17680 = CU.temp(None)
      val x17679 = ScalarFIFO(size=1,name="x17679").wtPort(bus_2122_s)
      val x17666 = ScalarFIFO(size=1,name="x17666").wtPort(bus_2109_s)
      val x17669 = ScalarFIFO(size=1,name="x17669").wtPort(bus_2112_s)
      val x17661 = ScalarFIFO(size=1,name="x17661").wtPort(bus_2104_s)
      val x17667 = ScalarFIFO(size=1,name="x17667").wtPort(bus_2110_s)
      val x17664 = ScalarFIFO(size=1,name="x17664").wtPort(bus_2107_s)
      val x17663 = ScalarFIFO(size=1,name="x17663").wtPort(bus_2106_s)
      Stage(operands=List(CU.load(x17667), CU.load(x17669), CU.load(x17679)), op=Mux, results=List(x17680))
      Stage(operands=List(CU.load(x17664), CU.load(x17666), x17680), op=Mux, results=List(x17681))
      Stage(operands=List(CU.load(x17661), CU.load(x17663), x17681), op=Mux, results=List(CU.scalarOut(bus_2125_s)))
    }
    val x17720_12 = Pipeline(name="x17720_12",parent=x17720) { implicit CU => 
      val x17651 = ScalarFIFO(size=1,name="x17651").wtPort(bus_2094_s)
      val x17660 = ScalarFIFO(size=1,name="x17660").wtPort(bus_2103_s)
      val x17682 = ScalarFIFO(size=1,name="x17682").wtPort(bus_2125_s)
      Stage(operands=List(CU.load(x17651), CU.load(x17660), CU.load(x17682)), op=Mux, results=List(CU.scalarOut(x17648_x17718_s)))
    }
    val x17731_0 = Pipeline(name="x17731_0",parent=x17872) { implicit CU => 
      val x17726 = ScalarFIFO(size=1,name="x17726").wtPort(x16910_1_s)
      val x17648 = ScalarBuffer(name="x17648").wtPort(x17648_x17718_s)
      val ctr45 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr46 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17723 = CounterChain(name = "x17723", ctr45, ctr46).iter(256)
      Stage(operands=List(CU.load(x17726), CU.load(x17648)), op=FixDiv, results=List(CU.scalarOut(x16910_x17730_s)))
    }
    val x17740_0 = Pipeline(name="x17740_0",parent=x17872) { implicit CU => 
      val x17649 = ScalarBuffer(name="x17649").wtPort(x17649_x17719_s)
      val x17735 = ScalarFIFO(size=1,name="x17735").wtPort(x16907_1_s)
      val ctr47 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17733 = CounterChain(name = "x17733", ctr47).iter(4)
      Stage(operands=List(CU.load(x17735), CU.load(x17649)), op=FixDiv, results=List(CU.scalarOut(x16907_x17739_s)))
    }
    val x17760_0 = Pipeline(name="x17760_0",parent=x17872) { implicit CU => 
      val x17751 = CU.temp(None)
      val x17741 = CU.temp(Some(0))
      val x17757 = CU.temp(None)
      val x17748 = ScalarFIFO(size=1,name="x17748").wtPort(x17068_0_s)
      val x17754 = ScalarFIFO(size=1,name="x17754").wtPort(x16911_2_s)
      val x17747 = ScalarFIFO(size=1,name="x17747").wtPort(x16911_3_s)
      val ctr48 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr49 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17744 = CounterChain(name = "x17744", ctr48, ctr49).iter(64)
      Stage(operands=List(CU.load(x17754), CU.load(x17754)), op=FixMul, results=List(x17757))
      Stage(operands=List(x17757, x17741), op=FixAdd, results=List(CU.scalarOut(x17741_x17759_s)))
      Stage(operands=List(CU.load(x17748), Const(0.01)), op=FixMul, results=List(x17751))
      Stage(operands=List(CU.load(x17747), x17751), op=FixSub, results=List(CU.scalarOut(x16911_x17753_s)))
    }
    val x17778_0 = Pipeline(name="x17778_0",parent=x17872) { implicit CU => 
      val x17775 = CU.temp(None)
      val x17761 = CU.temp(Some(0))
      val x17769 = CU.temp(None)
      val x17766 = ScalarFIFO(size=1,name="x17766").wtPort(x17065_0_s)
      val x17772 = ScalarFIFO(size=1,name="x17772").wtPort(x16908_2_s)
      val x17765 = ScalarFIFO(size=1,name="x17765").wtPort(x16908_3_s)
      val ctr50 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17763 = CounterChain(name = "x17763", ctr50).iter(1)
      Stage(operands=List(CU.load(x17772), CU.load(x17772)), op=FixMul, results=List(x17775))
      Stage(operands=List(x17775, x17761), op=FixAdd, results=List(CU.scalarOut(x17761_x17777_s)))
      Stage(operands=List(CU.load(x17766), Const(0.01)), op=FixMul, results=List(x17769))
      Stage(operands=List(CU.load(x17765), x17769), op=FixSub, results=List(CU.scalarOut(x16908_x17771_s)))
    }
    val x17851 = StreamController(name="x17851",parent=x17872) { implicit CU => 
      val x17851_unit = CounterChain(name = "x17851_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17851_0 = Pipeline(name="x17851_0",parent=x17851) { implicit CU => 
      val x17817 = CU.temp(None)
      val x17820 = CU.temp(None)
      val x17818 = CU.temp(None)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      Stage(operands=List(CU.load(x17761), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2146_s)))
      Stage(operands=List(CU.load(x17761), Const(1)), op=FixSub, results=List(x17817, CU.scalarOut(bus_2148_s)))
      Stage(operands=List(x17817, Const(2)), op=FixDiv, results=List(x17818))
      Stage(operands=List(Const(1), x17818), op=FixAdd, results=List(CU.scalarOut(bus_2150_s)))
      Stage(operands=List(x17817, x17817), op=FixMul, results=List(x17820, CU.scalarOut(bus_2151_s)))
      Stage(operands=List(x17820, Const(8)), op=FixDiv, results=List(CU.scalarOut(bus_2153_s)))
    }
    val x17851_1 = Pipeline(name="x17851_1",parent=x17851) { implicit CU => 
      val x17823 = CU.temp(None)
      val x17824 = CU.temp(None)
      val x17822 = CU.temp(None)
      val x17821 = ScalarFIFO(size=1,name="x17821").wtPort(bus_2153_s)
      val x17817 = ScalarFIFO(size=1,name="x17817").wtPort(bus_2148_s)
      val x17820 = ScalarFIFO(size=1,name="x17820").wtPort(bus_2151_s)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      val x17819 = ScalarFIFO(size=1,name="x17819").wtPort(bus_2150_s)
      Stage(operands=List(CU.load(x17819), CU.load(x17821)), op=FixSub, results=List(x17822))
      Stage(operands=List(CU.load(x17820), CU.load(x17817)), op=FixMul, results=List(x17823))
      Stage(operands=List(x17823, Const(16)), op=FixDiv, results=List(x17824))
      Stage(operands=List(x17822, x17824), op=FixAdd, results=List(CU.scalarOut(bus_2158_s)))
      Stage(operands=List(CU.load(x17761), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2160_s)))
      Stage(operands=List(CU.load(x17761), Const(0.22)), op=FixMul, results=List(CU.scalarOut(bus_2162_s)))
    }
    val x17851_2 = Pipeline(name="x17851_2",parent=x17851) { implicit CU => 
      val x17830 = CU.temp(None)
      val x17827 = ScalarFIFO(size=1,name="x17827").wtPort(bus_2162_s)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      Stage(operands=List(CU.load(x17827), Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2163_s)))
      Stage(operands=List(CU.load(x17761), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2165_s)))
      Stage(operands=List(CU.load(x17761), Const(0.08)), op=FixMul, results=List(x17830))
      Stage(operands=List(x17830, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2169_s)))
      Stage(operands=List(CU.load(x17761), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2171_s)))
      Stage(operands=List(CU.load(x17761), Const(0.028)), op=FixMul, results=List(CU.scalarOut(bus_2173_s)))
    }
    val x17851_3 = Pipeline(name="x17851_3",parent=x17851) { implicit CU => 
      val x17836 = CU.temp(None)
      val x17833 = ScalarFIFO(size=1,name="x17833").wtPort(bus_2173_s)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      Stage(operands=List(CU.load(x17833), Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2174_s)))
      Stage(operands=List(CU.load(x17761), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2176_s)))
      Stage(operands=List(CU.load(x17761), Const(0.008)), op=FixMul, results=List(x17836))
      Stage(operands=List(x17836, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2180_s)))
      Stage(operands=List(CU.load(x17761), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2182_s)))
      Stage(operands=List(CU.load(x17761), Const(0.003)), op=FixMul, results=List(CU.scalarOut(bus_2184_s)))
    }
    val x17851_4 = Pipeline(name="x17851_4",parent=x17851) { implicit CU => 
      val x17843 = CU.temp(None)
      val x17841 = CU.temp(None)
      val x17840 = CU.temp(None)
      val x17842 = CU.temp(None)
      val x17835 = ScalarFIFO(size=1,name="x17835").wtPort(bus_2176_s)
      val x17839 = ScalarFIFO(size=1,name="x17839").wtPort(bus_2184_s)
      val x17837 = ScalarFIFO(size=1,name="x17837").wtPort(bus_2180_s)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      val x17838 = ScalarFIFO(size=1,name="x17838").wtPort(bus_2182_s)
      Stage(operands=List(CU.load(x17839), Const(60)), op=FixAdd, results=List(x17840))
      Stage(operands=List(CU.load(x17761), Const(2.0E-4)), op=FixMul, results=List(x17841))
      Stage(operands=List(x17841, Const(300)), op=FixAdd, results=List(x17842))
      Stage(operands=List(CU.load(x17838), x17840, x17842), op=Mux, results=List(x17843))
      Stage(operands=List(CU.load(x17835), CU.load(x17837), x17843), op=Mux, results=List(CU.scalarOut(bus_2192_s)))
    }
    val x17851_5 = Pipeline(name="x17851_5",parent=x17851) { implicit CU => 
      val x17846 = CU.temp(None)
      val x17845 = CU.temp(None)
      val x17834 = ScalarFIFO(size=1,name="x17834").wtPort(bus_2174_s)
      val x17828 = ScalarFIFO(size=1,name="x17828").wtPort(bus_2163_s)
      val x17831 = ScalarFIFO(size=1,name="x17831").wtPort(bus_2169_s)
      val x17829 = ScalarFIFO(size=1,name="x17829").wtPort(bus_2165_s)
      val x17844 = ScalarFIFO(size=1,name="x17844").wtPort(bus_2192_s)
      val x17826 = ScalarFIFO(size=1,name="x17826").wtPort(bus_2160_s)
      val x17832 = ScalarFIFO(size=1,name="x17832").wtPort(bus_2171_s)
      Stage(operands=List(CU.load(x17832), CU.load(x17834), CU.load(x17844)), op=Mux, results=List(x17845))
      Stage(operands=List(CU.load(x17829), CU.load(x17831), x17845), op=Mux, results=List(x17846))
      Stage(operands=List(CU.load(x17826), CU.load(x17828), x17846), op=Mux, results=List(CU.scalarOut(bus_2195_s)))
    }
    val x17851_6 = Pipeline(name="x17851_6",parent=x17851) { implicit CU => 
      val x17784 = CU.temp(None)
      val x17783 = CU.temp(None)
      val x17825 = ScalarFIFO(size=1,name="x17825").wtPort(bus_2158_s)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      val x17816 = ScalarFIFO(size=1,name="x17816").wtPort(bus_2146_s)
      val x17847 = ScalarFIFO(size=1,name="x17847").wtPort(bus_2195_s)
      Stage(operands=List(CU.load(x17816), CU.load(x17825), CU.load(x17847)), op=Mux, results=List(CU.scalarOut(x17780_x17850_s)))
      Stage(operands=List(CU.load(x17741), Const(2)), op=FixLt, results=List(CU.scalarOut(bus_2197_s)))
      Stage(operands=List(CU.load(x17741), Const(1)), op=FixSub, results=List(x17783, CU.scalarOut(bus_2198_s)))
      Stage(operands=List(x17783, Const(2)), op=FixDiv, results=List(x17784))
      Stage(operands=List(Const(1), x17784), op=FixAdd, results=List(CU.scalarOut(bus_2200_s)))
      Stage(operands=List(x17783, x17783), op=FixMul, results=List(CU.scalarOut(bus_2201_s)))
    }
    val x17851_7 = Pipeline(name="x17851_7",parent=x17851) { implicit CU => 
      val x17788 = CU.temp(None)
      val x17787 = CU.temp(None)
      val x17789 = CU.temp(None)
      val x17790 = CU.temp(None)
      val x17783 = ScalarFIFO(size=1,name="x17783").wtPort(bus_2198_s)
      val x17785 = ScalarFIFO(size=1,name="x17785").wtPort(bus_2200_s)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      val x17786 = ScalarFIFO(size=1,name="x17786").wtPort(bus_2201_s)
      Stage(operands=List(CU.load(x17786), Const(8)), op=FixDiv, results=List(x17787))
      Stage(operands=List(CU.load(x17785), x17787), op=FixSub, results=List(x17788))
      Stage(operands=List(CU.load(x17786), CU.load(x17783)), op=FixMul, results=List(x17789))
      Stage(operands=List(x17789, Const(16)), op=FixDiv, results=List(x17790))
      Stage(operands=List(x17788, x17790), op=FixAdd, results=List(CU.scalarOut(bus_2206_s)))
      Stage(operands=List(CU.load(x17741), Const(10)), op=FixLt, results=List(CU.scalarOut(bus_2207_s)))
    }
    val x17851_8 = Pipeline(name="x17851_8",parent=x17851) { implicit CU => 
      val x17796 = CU.temp(None)
      val x17793 = CU.temp(None)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      Stage(operands=List(CU.load(x17741), Const(0.22)), op=FixMul, results=List(x17793))
      Stage(operands=List(x17793, Const(1)), op=FixAdd, results=List(CU.scalarOut(bus_2209_s)))
      Stage(operands=List(CU.load(x17741), Const(100)), op=FixLt, results=List(CU.scalarOut(bus_2210_s)))
      Stage(operands=List(CU.load(x17741), Const(0.08)), op=FixMul, results=List(x17796))
      Stage(operands=List(x17796, Const(2.5)), op=FixAdd, results=List(CU.scalarOut(bus_2212_s)))
      Stage(operands=List(CU.load(x17741), Const(1000)), op=FixLt, results=List(CU.scalarOut(bus_2213_s)))
    }
    val x17851_9 = Pipeline(name="x17851_9",parent=x17851) { implicit CU => 
      val x17802 = CU.temp(None)
      val x17799 = CU.temp(None)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      Stage(operands=List(CU.load(x17741), Const(0.028)), op=FixMul, results=List(x17799))
      Stage(operands=List(x17799, Const(8)), op=FixAdd, results=List(CU.scalarOut(bus_2215_s)))
      Stage(operands=List(CU.load(x17741), Const(10000)), op=FixLt, results=List(CU.scalarOut(bus_2216_s)))
      Stage(operands=List(CU.load(x17741), Const(0.008)), op=FixMul, results=List(x17802))
      Stage(operands=List(x17802, Const(20)), op=FixAdd, results=List(CU.scalarOut(bus_2218_s)))
      Stage(operands=List(CU.load(x17741), Const(32767)), op=FixLt, results=List(CU.scalarOut(bus_2219_s)))
    }
    val x17851_10 = Pipeline(name="x17851_10",parent=x17851) { implicit CU => 
      val x17807 = CU.temp(None)
      val x17808 = CU.temp(None)
      val x17805 = CU.temp(None)
      val x17809 = CU.temp(None)
      val x17806 = CU.temp(None)
      val x17801 = ScalarFIFO(size=1,name="x17801").wtPort(bus_2216_s)
      val x17803 = ScalarFIFO(size=1,name="x17803").wtPort(bus_2218_s)
      val x17804 = ScalarFIFO(size=1,name="x17804").wtPort(bus_2219_s)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      Stage(operands=List(CU.load(x17741), Const(0.003)), op=FixMul, results=List(x17805))
      Stage(operands=List(x17805, Const(60)), op=FixAdd, results=List(x17806))
      Stage(operands=List(CU.load(x17741), Const(2.0E-4)), op=FixMul, results=List(x17807))
      Stage(operands=List(x17807, Const(300)), op=FixAdd, results=List(x17808))
      Stage(operands=List(CU.load(x17804), x17806, x17808), op=Mux, results=List(x17809))
      Stage(operands=List(CU.load(x17801), CU.load(x17803), x17809), op=Mux, results=List(CU.scalarOut(bus_2225_s)))
    }
    val x17851_11 = Pipeline(name="x17851_11",parent=x17851) { implicit CU => 
      val x17811 = CU.temp(None)
      val x17812 = CU.temp(None)
      val x17795 = ScalarFIFO(size=1,name="x17795").wtPort(bus_2210_s)
      val x17797 = ScalarFIFO(size=1,name="x17797").wtPort(bus_2212_s)
      val x17792 = ScalarFIFO(size=1,name="x17792").wtPort(bus_2207_s)
      val x17810 = ScalarFIFO(size=1,name="x17810").wtPort(bus_2225_s)
      val x17798 = ScalarFIFO(size=1,name="x17798").wtPort(bus_2213_s)
      val x17794 = ScalarFIFO(size=1,name="x17794").wtPort(bus_2209_s)
      val x17800 = ScalarFIFO(size=1,name="x17800").wtPort(bus_2215_s)
      Stage(operands=List(CU.load(x17798), CU.load(x17800), CU.load(x17810)), op=Mux, results=List(x17811))
      Stage(operands=List(CU.load(x17795), CU.load(x17797), x17811), op=Mux, results=List(x17812))
      Stage(operands=List(CU.load(x17792), CU.load(x17794), x17812), op=Mux, results=List(CU.scalarOut(bus_2228_s)))
    }
    val x17851_12 = Pipeline(name="x17851_12",parent=x17851) { implicit CU => 
      val x17782 = ScalarFIFO(size=1,name="x17782").wtPort(bus_2197_s)
      val x17813 = ScalarFIFO(size=1,name="x17813").wtPort(bus_2228_s)
      val x17791 = ScalarFIFO(size=1,name="x17791").wtPort(bus_2206_s)
      Stage(operands=List(CU.load(x17782), CU.load(x17791), CU.load(x17813)), op=Mux, results=List(CU.scalarOut(x17779_x17849_s)))
    }
    val x17862_0 = Pipeline(name="x17862_0",parent=x17872) { implicit CU => 
      val x17857 = ScalarFIFO(size=1,name="x17857").wtPort(x16911_1_s)
      val x17779 = ScalarBuffer(name="x17779").wtPort(x17779_x17849_s)
      val ctr51 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr52 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17854 = CounterChain(name = "x17854", ctr51, ctr52).iter(64)
      Stage(operands=List(CU.load(x17857), CU.load(x17779)), op=FixDiv, results=List(CU.scalarOut(x16911_x17861_s)))
    }
    val x17871_0 = Pipeline(name="x17871_0",parent=x17872) { implicit CU => 
      val x17780 = ScalarBuffer(name="x17780").wtPort(x17780_x17850_s)
      val x17866 = ScalarFIFO(size=1,name="x17866").wtPort(x16908_1_s)
      val ctr53 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17864 = CounterChain(name = "x17864", ctr53).iter(1)
      Stage(operands=List(CU.load(x17866), CU.load(x17780)), op=FixDiv, results=List(CU.scalarOut(x16908_x17870_s)))
    }
    val x17882_0 = Pipeline(name="x17882_0",parent=x18033) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17875 = CounterChain(name = "x17875", ctr54, ctr55).iter(192)
      Stage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List())
    }
    val x17905 = StreamController(name="x17905",parent=x18033) { implicit CU => 
      val x17905_unit = CounterChain(name = "x17905_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17894_0 = Pipeline(name="x17894_0",parent=x17905) { implicit CU => 
      val x17887 = CU.temp(None)
      val x17889 = ScalarBuffer(name="x17889").wtPort(biases1_dram_da)
      val x17894_unit = CounterChain(name = "x17894_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17887))
      Stage(operands=List(x17887, CU.load(x17889)), op=FixAdd, results=List(CU.scalarOut(x17883_b18430_x17893_b18432_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17883_b18431_x17893_b18433_s)))
    }
    val x17901_0 = Pipeline(name="x17901_0",parent=x17905) { implicit CU => 
      val x17897 = ScalarFIFO(size=1,name="x17897").wtPort(x16906_0_s)
      val ctr56 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17896 = CounterChain(name = "x17896", ctr56).iter(4)
      Stage(operands=List(CU.load(x17897)), op=Bypass, results=List(CU.vecOut(x17884_x17900_v)))
    }
    val x17902 = MemoryController(name="x17902",parent=x17905,offchip=biases1_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17883_b18430 = ScalarFIFO(size=1,name="offset").wtPort(x17883_b18430_x17893_b18432_s)
      val x17883_b18431 = ScalarFIFO(size=1,name="size").wtPort(x17883_b18431_x17893_b18433_s)
      val x17884 = VectorFIFO(size=1,name="data").wtPort(x17884_x17900_v)
    }
    val x17928 = StreamController(name="x17928",parent=x18033) { implicit CU => 
      val x17928_unit = CounterChain(name = "x17928_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17917_0 = Pipeline(name="x17917_0",parent=x17928) { implicit CU => 
      val x17910 = CU.temp(None)
      val x17912 = ScalarBuffer(name="x17912").wtPort(biases2_dram_da)
      val x17917_unit = CounterChain(name = "x17917_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17910))
      Stage(operands=List(x17910, CU.load(x17912)), op=FixAdd, results=List(CU.scalarOut(x17906_b18434_x17916_b18436_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17906_b18435_x17916_b18437_s)))
    }
    val x17924_0 = Pipeline(name="x17924_0",parent=x17928) { implicit CU => 
      val x17920 = ScalarFIFO(size=1,name="x17920").wtPort(x16907_0_s)
      val ctr57 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17919 = CounterChain(name = "x17919", ctr57).iter(4)
      Stage(operands=List(CU.load(x17920)), op=Bypass, results=List(CU.vecOut(x17907_x17923_v)))
    }
    val x17925 = MemoryController(name="x17925",parent=x17928,offchip=biases2_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17907 = VectorFIFO(size=1,name="data").wtPort(x17907_x17923_v)
      val x17906_b18435 = ScalarFIFO(size=1,name="size").wtPort(x17906_b18435_x17916_b18437_s)
      val x17906_b18434 = ScalarFIFO(size=1,name="offset").wtPort(x17906_b18434_x17916_b18436_s)
    }
    val x17951 = StreamController(name="x17951",parent=x18033) { implicit CU => 
      val x17951_unit = CounterChain(name = "x17951_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17940_0 = Pipeline(name="x17940_0",parent=x17951) { implicit CU => 
      val x17933 = CU.temp(None)
      val x17935 = ScalarBuffer(name="x17935").wtPort(biases3_dram_da)
      val x17940_unit = CounterChain(name = "x17940_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17933))
      Stage(operands=List(x17933, CU.load(x17935)), op=FixAdd, results=List(CU.scalarOut(x17929_b18438_x17939_b18440_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x17929_b18439_x17939_b18441_s)))
    }
    val x17947_0 = Pipeline(name="x17947_0",parent=x17951) { implicit CU => 
      val x17943 = ScalarFIFO(size=1,name="x17943").wtPort(x16908_0_s)
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x17942 = CounterChain(name = "x17942", ctr58).iter(1)
      Stage(operands=List(CU.load(x17943)), op=Bypass, results=List(CU.vecOut(x17930_x17946_v)))
    }
    val x17948 = MemoryController(name="x17948",parent=x17951,offchip=biases3_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17929_b18439 = ScalarFIFO(size=1,name="size").wtPort(x17929_b18439_x17939_b18441_s)
      val x17930 = VectorFIFO(size=1,name="data").wtPort(x17930_x17946_v)
      val x17929_b18438 = ScalarFIFO(size=1,name="offset").wtPort(x17929_b18438_x17939_b18440_s)
    }
    val x17980 = StreamController(name="x17980",parent=x18033) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val x17953 = CounterChain(name = "x17953", ctr59).iter(13)
    }
    val x17968_0 = Pipeline(name="x17968_0",parent=x17980) { implicit CU => 
      val x17961 = CU.temp(None)
      val x17960 = CU.temp(None)
      val x17958 = CU.temp(None)
      val x17963 = ScalarBuffer(name="x17963").wtPort(weights1_dram_da)
      val x17953 = CounterChain.copy("x17980", "x17953")
      val x17968_unit = CounterChain(name = "x17968_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17953(0)), Const(6)), op=FixSla, results=List(x17958))
      Stage(operands=List(x17958, Const(0)), op=FixAdd, results=List(x17960))
      Stage(operands=List(x17960, Const(2)), op=FixSla, results=List(x17961))
      Stage(operands=List(x17961, CU.load(x17963)), op=FixAdd, results=List(CU.scalarOut(x17954_b18442_x17967_b18444_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17954_b18443_x17967_b18445_s)))
    }
    val x17976_0 = Pipeline(name="x17976_0",parent=x17980) { implicit CU => 
      val x17972 = ScalarFIFO(size=1,name="x17972").wtPort(x16909_0_s)
      val ctr60 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17970 = CounterChain(name = "x17970", ctr60).iter(4)
      Stage(operands=List(CU.load(x17972)), op=Bypass, results=List(CU.vecOut(x17955_x17975_v)))
    }
    val x17977 = MemoryController(name="x17977",parent=x17980,offchip=weights1_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17955 = VectorFIFO(size=1,name="data").wtPort(x17955_x17975_v)
      val x17954_b18442 = ScalarFIFO(size=1,name="offset").wtPort(x17954_b18442_x17967_b18444_s)
      val x17954_b18443 = ScalarFIFO(size=1,name="size").wtPort(x17954_b18443_x17967_b18445_s)
    }
    val x18009 = StreamController(name="x18009",parent=x18033) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17982 = CounterChain(name = "x17982", ctr61).iter(64)
    }
    val x17997_0 = Pipeline(name="x17997_0",parent=x18009) { implicit CU => 
      val x17987 = CU.temp(None)
      val x17989 = CU.temp(None)
      val x17990 = CU.temp(None)
      val x17992 = ScalarBuffer(name="x17992").wtPort(weights2_dram_da)
      val x17982 = CounterChain.copy("x18009", "x17982")
      val x17997_unit = CounterChain(name = "x17997_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x17982(0)), Const(6)), op=FixSla, results=List(x17987))
      Stage(operands=List(x17987, Const(0)), op=FixAdd, results=List(x17989))
      Stage(operands=List(x17989, Const(2)), op=FixSla, results=List(x17990))
      Stage(operands=List(x17990, CU.load(x17992)), op=FixAdd, results=List(CU.scalarOut(x17983_b18448_x17996_b18450_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17983_b18449_x17996_b18451_s)))
    }
    val x18005_0 = Pipeline(name="x18005_0",parent=x18009) { implicit CU => 
      val x18001 = ScalarFIFO(size=1,name="x18001").wtPort(x16910_0_s)
      val ctr62 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17999 = CounterChain(name = "x17999", ctr62).iter(4)
      Stage(operands=List(CU.load(x18001)), op=Bypass, results=List(CU.vecOut(x17984_x18004_v)))
    }
    val x18006 = MemoryController(name="x18006",parent=x18009,offchip=weights2_dram_oc, mctpe=TileStore) { implicit CU => 
      val x17983_b18448 = ScalarFIFO(size=1,name="offset").wtPort(x17983_b18448_x17996_b18450_s)
      val x17984 = VectorFIFO(size=1,name="data").wtPort(x17984_x18004_v)
      val x17983_b18449 = ScalarFIFO(size=1,name="size").wtPort(x17983_b18449_x17996_b18451_s)
    }
    val x18032 = StreamController(name="x18032",parent=x18033) { implicit CU => 
      val x18032_unit = CounterChain(name = "x18032_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x18021_0 = Pipeline(name="x18021_0",parent=x18032) { implicit CU => 
      val x18014 = CU.temp(None)
      val x18016 = ScalarBuffer(name="x18016").wtPort(weights3_dram_da)
      val x18021_unit = CounterChain(name = "x18021_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x18014))
      Stage(operands=List(x18014, CU.load(x18016)), op=FixAdd, results=List(CU.scalarOut(x18010_b18454_x18020_b18456_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x18010_b18455_x18020_b18457_s)))
    }
    val x18028_0 = Pipeline(name="x18028_0",parent=x18032) { implicit CU => 
      val x18024 = ScalarFIFO(size=1,name="x18024").wtPort(x16912_0_s)
      val ctr63 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x18023 = CounterChain(name = "x18023", ctr63).iter(12)
      Stage(operands=List(CU.load(x18024)), op=Bypass, results=List(CU.vecOut(x18011_x18027_v)))
    }
    val x18029 = MemoryController(name="x18029",parent=x18032,offchip=weights3_dram_oc, mctpe=TileStore) { implicit CU => 
      val x18010_b18454 = ScalarFIFO(size=1,name="offset").wtPort(x18010_b18454_x18020_b18456_s)
      val x18011 = VectorFIFO(size=1,name="data").wtPort(x18011_x18027_v)
      val x18010_b18455 = ScalarFIFO(size=1,name="size").wtPort(x18010_b18455_x18020_b18457_s)
    }
    
  }
}
