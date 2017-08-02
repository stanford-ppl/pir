import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Backprop extends PIRApp {
  def main(top:Top) = {
    val x17082_b18352_x17108_b18360_s = Scalar("x17082_b18352_x17108_b18360")
    val x16907_1_s = Scalar("x16907_1")
    val x16910_1_s = Scalar("x16910_1")
    val x17984_x18004_v = Vector("x17984_x18004")
    val weights2_dram_oc = OffChip("weights2_dram")
    val x16910_x17730_s = Scalar("x16910_x17730")
    val x17929_b18438_x17939_b18440_s = Scalar("x17929_b18438_x17939_b18440")
    val x17070_x17425_s = Scalar("x17070_x17425")
    val weights3_dram_oc = OffChip("weights3_dram")
    val x16907_2_s = Scalar("x16907_2")
    val x16908_x17771_s = Scalar("x16908_x17771")
    val x17082_b18354_x17108_b18362_s = Scalar("x17082_b18354_x17108_b18362")
    val x17883_b18431_x17893_b18433_s = Scalar("x17883_b18431_x17893_b18433")
    val x18011_x18027_v = Vector("x18011_x18027")
    val x16951_b18328_x16960_b18330_s = Scalar("x16951_b18328_x16960_b18330")
    val x17069_1_s = Scalar("x17069_1")
    val x16912_x17036_s = Scalar("x16912_x17036")
    val x17070_2_s = Scalar("x17070_2")
    val x16909_0_s = Scalar("x16909_0")
    val x17499_x17515_s = Scalar("x17499_x17515")
    val x16909_x17491_s = Scalar("x16909_x17491")
    val x17954_b18443_x17967_b18445_s = Scalar("x17954_b18443_x17967_b18445")
    val x17059_x17238_s = Scalar("x17059_x17238")
    val x16911_2_s = Scalar("x16911_2")
    val biases3_dram_da = DRAMAddress("biases3_dram", "biases3_dram")
    val x16997_b18338_x17009_b18340_s = Scalar("x16997_b18338_x17009_b18340")
    val x16912_0_s = Scalar("x16912_0")
    val x16911_3_s = Scalar("x16911_3")
    val x17181_x17188_s = Scalar("x17181_x17188")
    val x16914_x16924_data_v = Vector("x16914_x16924_data")
    val x17139_x17146_s = Scalar("x17139_x17146")
    val weights1_dram_oc = OffChip("weights1_dram")
    val x16913_b18320_x16922_b18322_s = Scalar("x16913_b18320_x16922_b18322")
    val x17083_x17110_data_v = Vector("x17083_x17110_data")
    val x17179_x17184_s = Scalar("x17179_x17184")
    val x17056_x17241_s = Scalar("x17056_x17241")
    val x17065_1_s = Scalar("x17065_1")
    val x16910_4_s = Scalar("x16910_4")
    val x17630_x17646_s = Scalar("x17630_x17646")
    val x16908_3_s = Scalar("x16908_3")
    val x17779_x17849_s = Scalar("x17779_x17849")
    val x16909_x17599_s = Scalar("x16909_x17599")
    val x17883_b18430_x17893_b18432_s = Scalar("x17883_b18430_x17893_b18432")
    val x17929_b18439_x17939_b18441_s = Scalar("x17929_b18439_x17939_b18441")
    val x17401_x17405_s = Scalar("x17401_x17405")
    val x17113_x17120_s = Scalar("x17113_x17120")
    val x17065_0_s = Scalar("x17065_0")
    val x16908_4_s = Scalar("x16908_4")
    val x17954_b18442_x17967_b18444_s = Scalar("x17954_b18442_x17967_b18444")
    val x17315_x17343_s = Scalar("x17315_x17343")
    val x16908_x16967_s = Scalar("x16908_x16967")
    val x17021_x17031_data_v = Vector("x17021_x17031_data")
    val x17059_0_s = Scalar("x17059_0")
    val x17065_x17386_s = Scalar("x17065_x17386")
    val biases1_dram_oc = OffChip("biases1_dram")
    val x17058_1_s = Scalar("x17058_1")
    val x17517_x17587_s = Scalar("x17517_x17587")
    val x16907_x17640_s = Scalar("x16907_x17640")
    val x16910_2_s = Scalar("x16910_2")
    val x17057_0_s = Scalar("x17057_0")
    val training_targets_dram_oc = OffChip("training_targets_dram")
    val x16908_x17870_s = Scalar("x16908_x17870")
    val x17070_1_s = Scalar("x17070_1")
    val x16933_x16943_data_v = Vector("x16933_x16943_data")
    val x16906_3_s = Scalar("x16906_3")
    val x17057_2_s = Scalar("x17057_2")
    val training_data_dram_da = DRAMAddress("training_data_dram", "training_data_dram")
    val x17058_2_s = Scalar("x17058_2")
    val x16908_1_s = Scalar("x16908_1")
    val x16910_5_s = Scalar("x16910_5")
    val x16907_x16948_s = Scalar("x16907_x16948")
    val x17055_x17079_s = Scalar("x17055_x17079")
    val weights3_dram_da = DRAMAddress("weights3_dram", "weights3_dram")
    val x16932_b18324_x16941_b18326_s = Scalar("x16932_b18324_x16941_b18326")
    val x17906_b18435_x17916_b18437_s = Scalar("x17906_b18435_x17916_b18437")
    val x16910_0_s = Scalar("x16910_0")
    val x17070_0_s = Scalar("x17070_0")
    val x17150_b18365_x17176_b18373_s = Scalar("x17150_b18365_x17176_b18373")
    val x17064_x17372_s = Scalar("x17064_x17372")
    val x17056_x17228_s = Scalar("x17056_x17228")
    val x16972_b18333_x16984_b18335_s = Scalar("x16972_b18333_x16984_b18335")
    val x17111_x17116_s = Scalar("x17111_x17116")
    val x17983_b18448_x17996_b18450_s = Scalar("x17983_b18448_x17996_b18450")
    val x16910_x17017_s = Scalar("x16910_x17017")
    val x17140_x17147_s = Scalar("x17140_x17147")
    val x17057_x17277_s = Scalar("x17057_x17277")
    val x17061_0_s = Scalar("x17061_0")
    val x17054_x17078_s = Scalar("x17054_x17078")
    val x17150_b18367_x17176_b18375_s = Scalar("x17150_b18367_x17176_b18375")
    val x16907_3_s = Scalar("x16907_3")
    val x17062_1_s = Scalar("x17062_1")
    val x17780_x17850_s = Scalar("x17780_x17850")
    val x17062_x17135_s = Scalar("x17062_x17135")
    val x17058_x17313_s = Scalar("x17058_x17313")
    val x17068_x17397_s = Scalar("x17068_x17397")
    val x17150_b18366_x17176_b18374_s = Scalar("x17150_b18366_x17176_b18374")
    val x17081_b18351_x17106_b18359_s = Scalar("x17081_b18351_x17106_b18359")
    val x17151_x17178_data_s = Scalar("x17151_x17178_data")
    val x16909_1_s = Scalar("x16909_1")
    val x16906_x16929_s = Scalar("x16906_x16929")
    val x16907_x17739_s = Scalar("x16907_x17739")
    val x16997_b18339_x17009_b18341_s = Scalar("x16997_b18339_x17009_b18341")
    val x16907_4_s = Scalar("x16907_4")
    val x16906_1_s = Scalar("x16906_1")
    val x17053_x17077_s = Scalar("x17053_x17077")
    val x16906_2_s = Scalar("x16906_2")
    val x16906_0_s = Scalar("x16906_0")
    val x17149_b18363_x17174_b18371_s = Scalar("x17149_b18363_x17174_b18371")
    val x16973_x16986_data_v = Vector("x16973_x16986_data")
    val x17056_2_s = Scalar("x17056_2")
    val x16998_x17011_data_v = Vector("x16998_x17011_data")
    val x17069_0_s = Scalar("x17069_0")
    val x17063_x17203_s = Scalar("x17063_x17203")
    val x18010_b18455_x18020_b18457_s = Scalar("x18010_b18455_x18020_b18457")
    val x17930_x17946_v = Vector("x17930_x17946")
    val training_targets_dram_da = DRAMAddress("training_targets_dram", "training_targets_dram")
    val x16908_0_s = Scalar("x16908_0")
    val weights2_dram_da = DRAMAddress("weights2_dram", "weights2_dram")
    val x17063_0_s = Scalar("x17063_0")
    val x17065_2_s = Scalar("x17065_2")
    val x16911_x17753_s = Scalar("x16911_x17753")
    val x17741_x17759_s = Scalar("x17741_x17759")
    val x17907_x17923_v = Vector("x17907_x17923")
    val x17245_x17258_s = Scalar("x17245_x17258")
    val biases1_dram_da = DRAMAddress("biases1_dram", "biases1_dram")
    val x18010_b18454_x18020_b18456_s = Scalar("x18010_b18454_x18020_b18456")
    val x17064_0_s = Scalar("x17064_0")
    val biases2_dram_oc = OffChip("biases2_dram")
    val x16911_0_s = Scalar("x16911_0")
    val x17069_x17465_s = Scalar("x17069_x17465")
    val x16909_2_s = Scalar("x16909_2")
    val x17056_1_s = Scalar("x17056_1")
    val x17057_x17264_s = Scalar("x17057_x17264")
    val iters_argin = ArgIn("iters")
    val x16906_4_s = Scalar("x16906_4")
    val x16911_4_s = Scalar("x16911_4")
    val x17955_x17975_v = Vector("x17955_x17975")
    val x16909_4_s = Scalar("x16909_4")
    val x17209_x17222_s = Scalar("x17209_x17222")
    val x17648_x17718_s = Scalar("x17648_x17718")
    val x17060_x17274_s = Scalar("x17060_x17274")
    val biases3_dram_oc = OffChip("biases3_dram")
    val x17441_x17445_s = Scalar("x17441_x17445")
    val x17058_0_s = Scalar("x17058_0")
    val x17020_b18344_x17029_b18346_s = Scalar("x17020_b18344_x17029_b18346")
    val x17149_b18364_x17174_b18372_s = Scalar("x17149_b18364_x17174_b18372")
    val x17442_x17459_s = Scalar("x17442_x17459")
    val x17066_x17477_s = Scalar("x17066_x17477")
    val x16912_1_s = Scalar("x16912_1")
    val x16909_3_s = Scalar("x16909_3")
    val x17052_x17076_s = Scalar("x17052_x17076")
    val x17056_0_s = Scalar("x17056_0")
    val x17906_b18434_x17916_b18436_s = Scalar("x17906_b18434_x17916_b18436")
    val x17062_0_s = Scalar("x17062_0")
    val x16910_x17622_s = Scalar("x16910_x17622")
    val x16906_x17509_s = Scalar("x16906_x17509")
    val x17649_x17719_s = Scalar("x17649_x17719")
    val x17884_x17900_v = Vector("x17884_x17900")
    val x16906_x17608_s = Scalar("x16906_x17608")
    val x17067_0_s = Scalar("x17067_0")
    val x17402_x17419_s = Scalar("x17402_x17419")
    val x17067_x17437_s = Scalar("x17067_x17437")
    val x17610_x17628_s = Scalar("x17610_x17628")
    val x17068_0_s = Scalar("x17068_0")
    val x16952_x16962_data_v = Vector("x16952_x16962_data")
    val x16911_x17047_s = Scalar("x16911_x17047")
    val x17058_x17300_s = Scalar("x17058_x17300")
    val x16911_5_s = Scalar("x16911_5")
    val x17112_x17118_s = Scalar("x17112_x17118")
    val x16913_b18321_x16922_b18323_s = Scalar("x16913_b18321_x16922_b18323")
    val x17057_1_s = Scalar("x17057_1")
    val x17082_b18353_x17108_b18361_s = Scalar("x17082_b18353_x17108_b18361")
    val x16972_b18332_x16984_b18334_s = Scalar("x16972_b18332_x16984_b18334")
    val biases2_dram_da = DRAMAddress("biases2_dram", "biases2_dram")
    val x17180_x17186_s = Scalar("x17180_x17186")
    val x16908_2_s = Scalar("x16908_2")
    val x16912_x17881_s = Scalar("x16912_x17881")
    val x17479_x17497_s = Scalar("x17479_x17497")
    val training_data_dram_oc = OffChip("training_data_dram")
    val x17020_b18345_x17029_b18347_s = Scalar("x17020_b18345_x17029_b18347")
    val x17281_x17294_s = Scalar("x17281_x17294")
    val weights1_dram_da = DRAMAddress("weights1_dram", "weights1_dram")
    val x17061_x17310_s = Scalar("x17061_x17310")
    val x17081_b18350_x17106_b18358_s = Scalar("x17081_b18350_x17106_b18358")
    val x17761_x17777_s = Scalar("x17761_x17777")
    val x17983_b18449_x17996_b18451_s = Scalar("x17983_b18449_x17996_b18451")
    val x16907_0_s = Scalar("x16907_0")
    val x17518_x17588_s = Scalar("x17518_x17588")
    val x16911_1_s = Scalar("x16911_1")
    val x16932_b18325_x16941_b18327_s = Scalar("x16932_b18325_x16941_b18327")
    val x16910_3_s = Scalar("x16910_3")
    val x16911_x17861_s = Scalar("x16911_x17861")
    val x16909_x16992_s = Scalar("x16909_x16992")
    val x17060_0_s = Scalar("x17060_0")
    val x17066_0_s = Scalar("x17066_0")
    val x16951_b18329_x16960_b18331_s = Scalar("x16951_b18329_x16960_b18331")
    val x18033 = Sequential(name="x18033",parent=top) { implicit CU => 
      val x18033_unit = CounterChain(name = "x18033_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16906_dsp0 = MemoryPipeline(name="x16906_dsp0",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609", "x17602")
      val x17896 = CounterChain.copy("x17901", "x17896")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_0_s).rdAddr(x17896(0)).wtAddr(x17602(0))
    }
    val x16906_dsp1 = MemoryPipeline(name="x16906_dsp1",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_1_s).rdAddr(x17602(0)).wtAddr(x17602(0))
    }
    val x16906_dsp2 = MemoryPipeline(name="x16906_dsp2",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17501 = CounterChain.copy("x17516", "x17501")
      val x17602 = CounterChain.copy("x17609", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_2_s).rdAddr(x17501(0)).wtAddr(x17602(0))
    }
    val x16906_dsp3 = MemoryPipeline(name="x16906_dsp3",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17501 = CounterChain.copy("x17516", "x17501")
      val x17602 = CounterChain.copy("x17609", "x17602")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_3_s).rdAddr(x17501(0)).wtAddr(x17602(0))
    }
    val x16906_dsp4 = MemoryPipeline(name="x16906_dsp4",parent="x18033") { implicit CU => 
      val x17608 = ScalarFIFO(size=1,name="x17608").wtPort(x16906_x17608_s)
      val x16929 = ScalarFIFO(size=1,name="x16929").wtPort(x16906_x16929_s)
      val x17509 = ScalarFIFO(size=1,name="x17509").wtPort(x16906_x17509_s)
      val x17602 = CounterChain.copy("x17609", "x17602")
      val x17208 = CounterChain.copy("x17230", "x17208")
      val x16906 = SRAM(size=64,name="x16906",banking = Strided(1)).wtPort(x16929.readPort).wtPort(x17509.readPort).wtPort(x17608.readPort).rdPort(x16906_4_s).rdAddr(x17208(0)).wtAddr(x17602(0))
    }
    val x16907_dsp0 = MemoryPipeline(name="x16907_dsp0",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740", "x17733")
      val x17919 = CounterChain.copy("x17924", "x17919")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_0_s).rdAddr(x17919(0)).wtAddr(x17733(0))
    }
    val x16907_dsp1 = MemoryPipeline(name="x16907_dsp1",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_1_s).rdAddr(x17733(0)).wtAddr(x17733(0))
    }
    val x16907_dsp2 = MemoryPipeline(name="x16907_dsp2",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17632 = CounterChain.copy("x17647", "x17632")
      val x17733 = CounterChain.copy("x17740", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_2_s).rdAddr(x17632(0)).wtAddr(x17733(0))
    }
    val x16907_dsp3 = MemoryPipeline(name="x16907_dsp3",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17632 = CounterChain.copy("x17647", "x17632")
      val x17733 = CounterChain.copy("x17740", "x17733")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_3_s).rdAddr(x17632(0)).wtAddr(x17733(0))
    }
    val x16907_dsp4 = MemoryPipeline(name="x16907_dsp4",parent="x18033") { implicit CU => 
      val x17739 = ScalarFIFO(size=1,name="x17739").wtPort(x16907_x17739_s)
      val x17640 = ScalarFIFO(size=1,name="x17640").wtPort(x16907_x17640_s)
      val x16948 = ScalarFIFO(size=1,name="x16948").wtPort(x16907_x16948_s)
      val x17733 = CounterChain.copy("x17740", "x17733")
      val x17244 = CounterChain.copy("x17266", "x17244")
      val x16907 = SRAM(size=64,name="x16907",banking = Strided(1)).wtPort(x16948.readPort).wtPort(x17640.readPort).wtPort(x17739.readPort).rdPort(x16907_4_s).rdAddr(x17244(0)).wtAddr(x17733(0))
    }
    val x16908_dsp0 = MemoryPipeline(name="x16908_dsp0",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17864 = CounterChain.copy("x17871", "x17864")
      val x17942 = CounterChain.copy("x17947", "x17942")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_0_s).rdAddr(x17942(0)).wtAddr(x17864(0))
    }
    val x16908_dsp1 = MemoryPipeline(name="x16908_dsp1",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17864 = CounterChain.copy("x17871", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_1_s).rdAddr(x17864(0)).wtAddr(x17864(0))
    }
    val x16908_dsp2 = MemoryPipeline(name="x16908_dsp2",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17763 = CounterChain.copy("x17778", "x17763")
      val x17864 = CounterChain.copy("x17871", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_2_s).rdAddr(x17763(0)).wtAddr(x17864(0))
    }
    val x16908_dsp3 = MemoryPipeline(name="x16908_dsp3",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17763 = CounterChain.copy("x17778", "x17763")
      val x17864 = CounterChain.copy("x17871", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_3_s).rdAddr(x17763(0)).wtAddr(x17864(0))
    }
    val x16908_dsp4 = MemoryPipeline(name="x16908_dsp4",parent="x18033") { implicit CU => 
      val x16967 = ScalarFIFO(size=1,name="x16967").wtPort(x16908_x16967_s)
      val x17771 = ScalarFIFO(size=1,name="x17771").wtPort(x16908_x17771_s)
      val x17870 = ScalarFIFO(size=1,name="x17870").wtPort(x16908_x17870_s)
      val x17280 = CounterChain.copy("x17302", "x17280")
      val x17864 = CounterChain.copy("x17871", "x17864")
      val x16908 = SRAM(size=16,name="x16908",banking = Strided(1)).wtPort(x16967.readPort).wtPort(x17771.readPort).wtPort(x17870.readPort).rdPort(x16908_4_s).rdAddr(x17280(0)).wtAddr(x17864(0))
    }
    val x16909_dsp0 = MemoryPipeline(name="x16909_dsp0",parent="x18033") { implicit CU => 
      val b18446 = CU.temp(None)
      val b18402 = CU.temp(None)
      val x17599 = ScalarFIFO(size=1,name="x17599").wtPort(x16909_x17599_s)
      val x16992 = ScalarFIFO(size=1,name="x16992").wtPort(x16909_x16992_s)
      val x17491 = ScalarFIFO(size=1,name="x17491").wtPort(x16909_x17491_s)
      val x17953 = CounterChain.copy("x17980", "x17953")
      val x17970 = CounterChain.copy("x17976", "x17970")
      val x17592 = CounterChain.copy("x17600", "x17592")
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
      val x17592 = CounterChain.copy("x17600", "x17592")
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
      val x17482 = CounterChain.copy("x17498", "x17482")
      val x17592 = CounterChain.copy("x17600", "x17592")
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
      val x17482 = CounterChain.copy("x17498", "x17482")
      val x17592 = CounterChain.copy("x17600", "x17592")
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
      val x17211 = CounterChain.copy("x17223", "x17211")
      val x17592 = CounterChain.copy("x17600", "x17592")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
      val x17982 = CounterChain.copy("x18009", "x17982")
      val x17999 = CounterChain.copy("x18005", "x17999")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
      val x17613 = CounterChain.copy("x17629", "x17613")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
      val x17613 = CounterChain.copy("x17629", "x17613")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
      val x17448 = CounterChain.copy("x17460", "x17448")
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
      val x17723 = CounterChain.copy("x17731", "x17723")
      val x17244 = CounterChain.copy("x17266", "x17244")
      val x17247 = CounterChain.copy("x17259", "x17247")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17875 = CounterChain.copy("x17882", "x17875")
      val x17854 = CounterChain.copy("x17862", "x17854")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17854 = CounterChain.copy("x17862", "x17854")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17744 = CounterChain.copy("x17760", "x17744")
      val x17854 = CounterChain.copy("x17862", "x17854")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17744 = CounterChain.copy("x17760", "x17744")
      val x17854 = CounterChain.copy("x17862", "x17854")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17854 = CounterChain.copy("x17862", "x17854")
      val x17408 = CounterChain.copy("x17420", "x17408")
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
      val x17047 = ScalarFIFO(size=1,name="x17047").wtPort(x16911_x17047_s)
      val x17280 = CounterChain.copy("x17302", "x17280")
      val x17283 = CounterChain.copy("x17295", "x17283")
      val x17854 = CounterChain.copy("x17862", "x17854")
      val x16911 = SRAM(size=192,name="x16911",banking = Strided(1)).wtPort(x17047.readPort).wtPort(x17753.readPort).wtPort(x17861.readPort).rdPort(x16911_5_s)
      WAStage(operands=List(CU.ctr(x17854(0)), Const(3)), op=FixMul, results=List(b18426))
      WAStage(operands=List(b18426, CU.ctr(x17854(1))), op=FixAdd, results=List(x16911.writeAddr))
      RAStage(operands=List(CU.ctr(x17283(0)), Const(3)), op=FixMul, results=List(b18380))
      RAStage(operands=List(b18380, CU.ctr(x17280(0))), op=FixAdd, results=List(x16911.readAddr))
    }
    val x16912_dsp0 = MemoryPipeline(name="x16912_dsp0",parent="x18033") { implicit CU => 
      val x17876 = CU.temp(None)
      val x17036 = ScalarFIFO(size=1,name="x17036").wtPort(x16912_x17036_s)
      val x17881 = ScalarFIFO(size=1,name="x17881").wtPort(x16912_x17881_s)
      val x17875 = CounterChain.copy("x17882", "x17875")
      val x18023 = CounterChain.copy("x18028", "x18023")
      val x16912 = SRAM(size=192,name="x16912",banking = Strided(1)).wtPort(x17036.readPort).wtPort(x17881.readPort).rdPort(x16912_0_s).rdAddr(x18023(0))
      WAStage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(x17876))
      WAStage(operands=List(x17876, CU.ctr(x17875(1))), op=FixAdd, results=List(x16912.writeAddr))
    }
    val x16912_dsp1 = MemoryPipeline(name="x16912_dsp1",parent="x18033") { implicit CU => 
      val x17042 = CU.temp(None)
      val x17876 = CU.temp(None)
      val x17036 = ScalarFIFO(size=1,name="x17036").wtPort(x16912_x17036_s)
      val x17881 = ScalarFIFO(size=1,name="x17881").wtPort(x16912_x17881_s)
      val x17041 = CounterChain.copy("x17048", "x17041")
      val x17875 = CounterChain.copy("x17882", "x17875")
      val x16912 = SRAM(size=192,name="x16912",banking = Strided(1)).wtPort(x17036.readPort).wtPort(x17881.readPort).rdPort(x16912_1_s)
      WAStage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(x17876))
      WAStage(operands=List(x17876, CU.ctr(x17875(1))), op=FixAdd, results=List(x16912.writeAddr))
      RAStage(operands=List(CU.ctr(x17041(0)), Const(3)), op=FixMul, results=List(x17042))
      RAStage(operands=List(x17042, CU.ctr(x17041(1))), op=FixAdd, results=List(x16912.readAddr))
    }
    val x16931 = StreamController(name="x16931",parent=x18033) { implicit CU => 
      val x16931_unit = CounterChain(name = "x16931_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16923 = Pipeline(name="x16923",parent=x16931) { implicit CU => 
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
    val x16930 = Pipeline(name="x16930",parent=x16931) { implicit CU => 
      val x16914 = VectorFIFO(size=1,name="x16914").wtPort(x16914_x16924_data_v)
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16926 = CounterChain(name = "x16926", ctr1).iter(4)
      Stage(operands=List(CU.load(x16914)), op=Bypass, results=List(CU.scalarOut(x16906_x16929_s)))
    }
    val x16950 = StreamController(name="x16950",parent=x18033) { implicit CU => 
      val x16950_unit = CounterChain(name = "x16950_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16942 = Pipeline(name="x16942",parent=x16950) { implicit CU => 
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
    val x16949 = Pipeline(name="x16949",parent=x16950) { implicit CU => 
      val x16933 = VectorFIFO(size=1,name="x16933").wtPort(x16933_x16943_data_v)
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16945 = CounterChain(name = "x16945", ctr2).iter(4)
      Stage(operands=List(CU.load(x16933)), op=Bypass, results=List(CU.scalarOut(x16907_x16948_s)))
    }
    val x16969 = StreamController(name="x16969",parent=x18033) { implicit CU => 
      val x16969_unit = CounterChain(name = "x16969_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x16961 = Pipeline(name="x16961",parent=x16969) { implicit CU => 
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
    val x16968 = Pipeline(name="x16968",parent=x16969) { implicit CU => 
      val x16952 = VectorFIFO(size=1,name="x16952").wtPort(x16952_x16962_data_v)
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x16964 = CounterChain(name = "x16964", ctr3).iter(1)
      Stage(operands=List(CU.load(x16952)), op=Bypass, results=List(CU.scalarOut(x16908_x16967_s)))
    }
    val x16994 = StreamController(name="x16994",parent=x18033) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val x16971 = CounterChain(name = "x16971", ctr4).iter(13)
    }
    val x16985 = Pipeline(name="x16985",parent=x16994) { implicit CU => 
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
    val x16993 = Pipeline(name="x16993",parent=x16994) { implicit CU => 
      val x16973 = VectorFIFO(size=1,name="x16973").wtPort(x16973_x16986_data_v)
      val ctr5 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x16988 = CounterChain(name = "x16988", ctr5).iter(4)
      Stage(operands=List(CU.load(x16973)), op=Bypass, results=List(CU.scalarOut(x16909_x16992_s)))
    }
    val x17019 = StreamController(name="x17019",parent=x18033) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x16996 = CounterChain(name = "x16996", ctr6).iter(64)
    }
    val x17010 = Pipeline(name="x17010",parent=x17019) { implicit CU => 
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
    val x17018 = Pipeline(name="x17018",parent=x17019) { implicit CU => 
      val x16998 = VectorFIFO(size=1,name="x16998").wtPort(x16998_x17011_data_v)
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17013 = CounterChain(name = "x17013", ctr7).iter(4)
      Stage(operands=List(CU.load(x16998)), op=Bypass, results=List(CU.scalarOut(x16910_x17017_s)))
    }
    val x17038 = StreamController(name="x17038",parent=x18033) { implicit CU => 
      val x17038_unit = CounterChain(name = "x17038_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17030 = Pipeline(name="x17030",parent=x17038) { implicit CU => 
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
    val x17037 = Pipeline(name="x17037",parent=x17038) { implicit CU => 
      val x17021 = VectorFIFO(size=1,name="x17021").wtPort(x17021_x17031_data_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x17033 = CounterChain(name = "x17033", ctr8).iter(12)
      Stage(operands=List(CU.load(x17021)), op=Bypass, results=List(CU.scalarOut(x16912_x17036_s)))
    }
    val x17048 = Pipeline(name="x17048",parent=x18033) { implicit CU => 
      val x17042 = CU.temp(None)
      val x17045 = ScalarFIFO(size=1,name="x17045").wtPort(x16912_1_s)
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17041 = CounterChain(name = "x17041", ctr9, ctr10).iter(192)
      Stage(operands=List(CU.ctr(x17041(0)), Const(3)), op=FixMul, results=List(x17042))
      Stage(operands=List(CU.load(x17045)), op=Bypass, results=List(CU.scalarOut(x16911_x17047_s)))
    }
    val x17872 = Sequential(name="x17872",parent=x18033) { implicit CU => 
      val x16809 = ScalarBuffer(name="x16809").wtPort(iters_argin)
      val ctr11 = Counter(min=Const(0), max=x16809.readPort, step=Const(1), par=1) // Counter
      val x17051 = CounterChain(name = "x17051", ctr11).iter(1)
    }
    val x17056_dsp0 = MemoryPipeline(name="x17056_dsp0",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242", "x17232")
      val x17430 = CounterChain.copy("x17438", "x17430")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_0_s).rdAddr(x17430(0)).wtAddr(x17232(0))
    }
    val x17056_dsp1 = MemoryPipeline(name="x17056_dsp1",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242", "x17232")
      val x17247 = CounterChain.copy("x17259", "x17247")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_1_s).rdAddr(x17247(0)).wtAddr(x17232(0))
    }
    val x17056_dsp2 = MemoryPipeline(name="x17056_dsp2",parent="x17872") { implicit CU => 
      val x17228 = ScalarFIFO(size=1,name="x17228").wtPort(x17056_x17228_s)
      val x17241 = ScalarFIFO(size=1,name="x17241").wtPort(x17056_x17241_s)
      val x17232 = CounterChain.copy("x17242", "x17232")
      val x17056 = SRAM(size=64,name="x17056",banking = Strided(1)).wtPort(x17228.readPort).wtPort(x17241.readPort).rdPort(x17056_2_s).rdAddr(x17232(0)).wtAddr(x17232(0))
    }
    val x17057_dsp0 = MemoryPipeline(name="x17057_dsp0",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17268 = CounterChain.copy("x17278", "x17268")
      val x17390 = CounterChain.copy("x17398", "x17390")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_0_s).rdAddr(x17390(0)).wtAddr(x17268(0))
    }
    val x17057_dsp1 = MemoryPipeline(name="x17057_dsp1",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17283 = CounterChain.copy("x17295", "x17283")
      val x17268 = CounterChain.copy("x17278", "x17268")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_1_s).rdAddr(x17283(0)).wtAddr(x17268(0))
    }
    val x17057_dsp2 = MemoryPipeline(name="x17057_dsp2",parent="x17872") { implicit CU => 
      val x17264 = ScalarFIFO(size=1,name="x17264").wtPort(x17057_x17264_s)
      val x17277 = ScalarFIFO(size=1,name="x17277").wtPort(x17057_x17277_s)
      val x17268 = CounterChain.copy("x17278", "x17268")
      val x17057 = SRAM(size=64,name="x17057",banking = Strided(1)).wtPort(x17264.readPort).wtPort(x17277.readPort).rdPort(x17057_2_s).rdAddr(x17268(0)).wtAddr(x17268(0))
    }
    val x17058_dsp0 = MemoryPipeline(name="x17058_dsp0",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314", "x17304")
      val x17346 = CounterChain.copy("x17373", "x17346")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_0_s).rdAddr(x17346(0)).wtAddr(x17304(0))
    }
    val x17058_dsp1 = MemoryPipeline(name="x17058_dsp1",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314", "x17304")
      val x17317 = CounterChain.copy("x17344", "x17317")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_1_s).rdAddr(x17317(0)).wtAddr(x17304(0))
    }
    val x17058_dsp2 = MemoryPipeline(name="x17058_dsp2",parent="x17872") { implicit CU => 
      val x17300 = ScalarFIFO(size=1,name="x17300").wtPort(x17058_x17300_s)
      val x17313 = ScalarFIFO(size=1,name="x17313").wtPort(x17058_x17313_s)
      val x17304 = CounterChain.copy("x17314", "x17304")
      val x17058 = SRAM(size=3,name="x17058",banking = Strided(1)).wtPort(x17300.readPort).wtPort(x17313.readPort).rdPort(x17058_2_s).rdAddr(x17304(0)).wtAddr(x17304(0))
    }
    val x17059_dsp0 = MemoryPipeline(name="x17059_dsp0",parent="x17872") { implicit CU => 
      val x17238 = ScalarFIFO(size=1,name="x17238").wtPort(x17059_x17238_s)
      val x17232 = CounterChain.copy("x17242", "x17232")
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17059 = SRAM(size=64,name="x17059",banking = Strided(1)).wtPort(x17238.readPort).rdPort(x17059_0_s).rdAddr(x17440(0)).wtAddr(x17232(0))
    }
    val x17060_dsp0 = MemoryPipeline(name="x17060_dsp0",parent="x17872") { implicit CU => 
      val x17274 = ScalarFIFO(size=1,name="x17274").wtPort(x17060_x17274_s)
      val x17268 = CounterChain.copy("x17278", "x17268")
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17060 = SRAM(size=64,name="x17060",banking = Strided(1)).wtPort(x17274.readPort).rdPort(x17060_0_s).rdAddr(x17400(0)).wtAddr(x17268(0))
    }
    val x17061_dsp0 = MemoryPipeline(name="x17061_dsp0",parent="x17872") { implicit CU => 
      val x17310 = ScalarFIFO(size=1,name="x17310").wtPort(x17061_x17310_s)
      val x17304 = CounterChain.copy("x17314", "x17304")
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17061 = SRAM(size=3,name="x17061",banking = Strided(1)).wtPort(x17310.readPort).rdPort(x17061_0_s).rdAddr(x17375(0)).wtAddr(x17304(0))
    }
    val x17062_dsp0 = MemoryPipeline(name="x17062_dsp0",parent="x17872") { implicit CU => 
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17111_x17116_s)
      val x17135 = ScalarFIFO(size=1,name="x17135").wtPort(x17062_x17135_s)
      val x17124 = CounterChain.copy("x17136", "x17124")
      val x17470 = CounterChain.copy("x17478", "x17470")
      val x17062 = SRAM(size=18,name="x17062",banking = Strided(1)).wtPort(x17135.readPort).rdPort(x17062_0_s).rdAddr(x17470(0))
      WAStage(operands=List(CU.ctr(x17124(0)), CU.load(x17111)), op=FixSub, results=List(x17062.writeAddr))
    }
    val x17062_dsp1 = MemoryPipeline(name="x17062_dsp1",parent="x17872") { implicit CU => 
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17111_x17116_s)
      val x17135 = ScalarFIFO(size=1,name="x17135").wtPort(x17062_x17135_s)
      val x17124 = CounterChain.copy("x17136", "x17124")
      val x17211 = CounterChain.copy("x17223", "x17211")
      val x17062 = SRAM(size=18,name="x17062",banking = Strided(1)).wtPort(x17135.readPort).rdPort(x17062_1_s).rdAddr(x17211(0))
      WAStage(operands=List(CU.ctr(x17124(0)), CU.load(x17111)), op=FixSub, results=List(x17062.writeAddr))
    }
    val x17063_dsp0 = MemoryPipeline(name="x17063_dsp0",parent="x17872") { implicit CU => 
      val x17203 = ScalarFIFO(size=1,name="x17203").wtPort(x17063_x17203_s)
      val x17179 = ScalarBuffer(name="x17179").wtPort(x17179_x17184_s)
      val x17192 = CounterChain.copy("x17204", "x17192")
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17063 = SRAM(size=16,name="x17063",banking = Strided(1)).wtPort(x17203.readPort).rdPort(x17063_0_s).rdAddr(x17375(0))
      WAStage(operands=List(CU.ctr(x17192(0)), CU.load(x17179)), op=FixSub, results=List(x17063.writeAddr))
    }
    val x17064_dsp0 = MemoryPipeline(name="x17064_dsp0",parent="x17872") { implicit CU => 
      val x17372 = ScalarFIFO(size=1,name="x17372").wtPort(x17064_x17372_s)
      val x17346 = CounterChain.copy("x17373", "x17346")
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17064 = SRAM(size=3,name="x17064",banking = Strided(1)).wtPort(x17372.readPort).rdPort(x17064_0_s).rdAddr(x17375(0)).wtAddr(x17346(0))
    }
    val x17065_dsp0 = MemoryPipeline(name="x17065_dsp0",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17763 = CounterChain.copy("x17778", "x17763")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_0_s).rdAddr(x17763(0)).wtAddr(x17375(0))
    }
    val x17065_dsp1 = MemoryPipeline(name="x17065_dsp1",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17408 = CounterChain.copy("x17420", "x17408")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_1_s).rdAddr(x17408(0)).wtAddr(x17375(0))
    }
    val x17065_dsp2 = MemoryPipeline(name="x17065_dsp2",parent="x17872") { implicit CU => 
      val x17386 = ScalarFIFO(size=1,name="x17386").wtPort(x17065_x17386_s)
      val x17375 = CounterChain.copy("x17387", "x17375")
      val x17390 = CounterChain.copy("x17398", "x17390")
      val x17065 = SRAM(size=3,name="x17065",banking = Strided(1)).wtPort(x17386.readPort).rdPort(x17065_2_s).rdAddr(x17390(1)).wtAddr(x17375(0))
    }
    val x17066_dsp0 = MemoryPipeline(name="x17066_dsp0",parent="x17872") { implicit CU => 
      val b18390 = CU.temp(None)
      val b18394 = CU.temp(None)
      val x17477 = ScalarFIFO(size=1,name="x17477").wtPort(x17066_x17477_s)
      val x17470 = CounterChain.copy("x17478", "x17470")
      val x17482 = CounterChain.copy("x17498", "x17482")
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
      val x17430 = CounterChain.copy("x17438", "x17430")
      val x17613 = CounterChain.copy("x17629", "x17613")
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
      val x17390 = CounterChain.copy("x17398", "x17390")
      val x17744 = CounterChain.copy("x17760", "x17744")
      val x17068 = SRAM(size=192,name="x17068",banking = Strided(1)).wtPort(x17397.readPort).rdPort(x17068_0_s)
      WAStage(operands=List(CU.ctr(x17390(0)), Const(3)), op=FixMul, results=List(b18382))
      WAStage(operands=List(b18382, CU.ctr(x17390(1))), op=FixAdd, results=List(x17068.writeAddr))
      RAStage(operands=List(CU.ctr(x17744(0)), Const(3)), op=FixMul, results=List(b18418))
      RAStage(operands=List(b18418, CU.ctr(x17744(1))), op=FixAdd, results=List(x17068.readAddr))
    }
    val x17069_dsp0 = MemoryPipeline(name="x17069_dsp0",parent="x17872") { implicit CU => 
      val x17465 = ScalarFIFO(size=1,name="x17465").wtPort(x17069_x17465_s)
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17501 = CounterChain.copy("x17516", "x17501")
      val x17069 = SRAM(size=64,name="x17069",banking = Strided(1)).wtPort(x17465.readPort).rdPort(x17069_0_s).rdAddr(x17501(0)).wtAddr(x17440(0))
    }
    val x17069_dsp1 = MemoryPipeline(name="x17069_dsp1",parent="x17872") { implicit CU => 
      val x17465 = ScalarFIFO(size=1,name="x17465").wtPort(x17069_x17465_s)
      val x17440 = CounterChain.copy("x17467", "x17440")
      val x17470 = CounterChain.copy("x17478", "x17470")
      val x17069 = SRAM(size=64,name="x17069",banking = Strided(1)).wtPort(x17465.readPort).rdPort(x17069_1_s).rdAddr(x17470(1)).wtAddr(x17440(0))
    }
    val x17070_dsp0 = MemoryPipeline(name="x17070_dsp0",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17632 = CounterChain.copy("x17647", "x17632")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_0_s).rdAddr(x17632(0)).wtAddr(x17400(0))
    }
    val x17070_dsp1 = MemoryPipeline(name="x17070_dsp1",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17448 = CounterChain.copy("x17460", "x17448")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_1_s).rdAddr(x17448(0)).wtAddr(x17400(0))
    }
    val x17070_dsp2 = MemoryPipeline(name="x17070_dsp2",parent="x17872") { implicit CU => 
      val x17425 = ScalarFIFO(size=1,name="x17425").wtPort(x17070_x17425_s)
      val x17400 = CounterChain.copy("x17427", "x17400")
      val x17430 = CounterChain.copy("x17438", "x17430")
      val x17070 = SRAM(size=64,name="x17070",banking = Strided(1)).wtPort(x17425.readPort).rdPort(x17070_2_s).rdAddr(x17430(1)).wtAddr(x17400(0))
    }
    val x17080 = Pipeline(name="x17080",parent=x17872) { implicit CU => 
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
    val x17109 = Pipeline(name="x17109",parent=x17138) { implicit CU => 
      val x17099 = CU.temp(None)
      val x17087 = CU.temp(None)
      val x17091 = CU.temp(None)
      val x17097 = CU.temp(None)
      val x17089 = CU.temp(None)
      val x17092 = CU.temp(None)
      val x17090 = CU.temp(None)
      val x17086 = CU.temp(None)
      val x17094 = CU.temp(None)
      val x17093 = CU.temp(None)
      val x17095 = CU.temp(None)
      val x17102 = ScalarBuffer(name="x17102").wtPort(training_data_dram_da)
      val x17053 = ScalarBuffer(name="x17053").wtPort(x17053_x17077_s)
      val x17055 = ScalarBuffer(name="x17055").wtPort(x17055_x17079_s)
      val x17109_unit = CounterChain(name = "x17109_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17053), Const(2)), op=FixSla, results=List(x17086))
      Stage(operands=List(x17086, Const(64)), op=FixMod, results=List(x17087))
      Stage(operands=List(x17087, Const(4)), op=FixDiv, results=List(x17094, CU.scalarOut(x17082_b18353_x17108_b18361_s)))
      Stage(operands=List(x17094, CU.load(x17055)), op=FixAdd, results=List(CU.scalarOut(x17082_b18354_x17108_b18362_s)))
      Stage(operands=List(CU.load(x17055), Const(2)), op=FixSla, results=List(x17089))
      Stage(operands=List(x17086, x17089), op=FixAdd, results=List(x17091))
      Stage(operands=List(x17091, Const(64)), op=FixMod, results=List(x17092))
      Stage(operands=List(Const(64), x17092), op=FixSub, results=List(x17093))
      Stage(operands=List(x17093, Const(4)), op=FixDiv, results=List(x17095))
      Stage(operands=List(CU.load(x17055), x17094), op=FixAdd, results=List(x17097))
      Stage(operands=List(x17097, x17095), op=FixAdd, results=List(CU.scalarOut(x17082_b18352_x17108_b18360_s)))
      Stage(operands=List(x17089, x17087), op=FixAdd, results=List(x17099))
      Stage(operands=List(x17099, x17093), op=FixAdd, results=List(CU.scalarOut(x17081_b18351_x17106_b18359_s)))
      Stage(operands=List(x17086, x17087), op=FixSub, results=List(x17090))
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
    val x17121 = Pipeline(name="x17121",parent=x17137) { implicit CU => 
      val x17082_b18353 = ScalarFIFO(size=16,name="x17082_b18353").wtPort(x17082_b18353_x17108_b18361_s)
      val x17082_b18352 = ScalarFIFO(size=16,name="x17082_b18352").wtPort(x17082_b18352_x17108_b18360_s)
      val x17082_b18354 = ScalarFIFO(size=16,name="x17082_b18354").wtPort(x17082_b18354_x17108_b18362_s)
      val x17121_unit = CounterChain(name = "x17121_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17082_b18353)), op=Bypass, results=List(CU.scalarOut(x17111_x17116_s)))
      Stage(operands=List(CU.load(x17082_b18354)), op=Bypass, results=List(CU.scalarOut(x17112_x17118_s)))
      Stage(operands=List(CU.load(x17082_b18352)), op=Bypass, results=List(CU.scalarOut(x17113_x17120_s)))
    }
    val x17136 = Pipeline(name="x17136",parent=x17137) { implicit CU => 
      val x17126 = CU.temp(None)
      val x17128 = CU.temp(None)
      val x17112 = ScalarBuffer(name="x17112").wtPort(x17112_x17118_s)
      val x17111 = ScalarBuffer(name="x17111").wtPort(x17111_x17116_s)
      val x17083 = VectorFIFO(size=1,name="x17083").wtPort(x17083_x17110_data_v)
      val x17113 = ScalarBuffer(name="x17113").wtPort(x17113_x17120_s)
      val ctr12 = Counter(min=Const(0), max=x17113.readPort, step=Const(1), par=16) // Counter
      val x17124 = CounterChain(name = "x17124", ctr12).iter(1)
      Stage(operands=List(CU.load(x17111), CU.ctr(x17124(0))), op=FixLeq, results=List(x17126))
      Stage(operands=List(CU.ctr(x17124(0)), CU.load(x17112)), op=FixLt, results=List(x17128))
      Stage(operands=List(CU.load(x17083)), op=Bypass, results=List(CU.scalarOut(x17062_x17135_s)))
    }
    val x17148 = Pipeline(name="x17148",parent=x17872) { implicit CU => 
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
    val x17177 = Pipeline(name="x17177",parent=x17206) { implicit CU => 
      val x17158 = CU.temp(None)
      val x17160 = CU.temp(None)
      val x17167 = CU.temp(None)
      val x17163 = CU.temp(None)
      val x17165 = CU.temp(None)
      val x17154 = CU.temp(None)
      val x17161 = CU.temp(None)
      val x17162 = CU.temp(None)
      val x17159 = CU.temp(None)
      val x17155 = CU.temp(None)
      val x17157 = CU.temp(None)
      val x17139 = ScalarBuffer(name="x17139").wtPort(x17139_x17146_s)
      val x17170 = ScalarBuffer(name="x17170").wtPort(training_targets_dram_da)
      val x17140 = ScalarBuffer(name="x17140").wtPort(x17140_x17147_s)
      val x17177_unit = CounterChain(name = "x17177_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17139), Const(2)), op=FixSla, results=List(x17154))
      Stage(operands=List(x17154, Const(64)), op=FixMod, results=List(x17155))
      Stage(operands=List(x17155, Const(4)), op=FixDiv, results=List(x17162, CU.scalarOut(x17150_b18366_x17176_b18374_s)))
      Stage(operands=List(x17162, CU.load(x17140)), op=FixAdd, results=List(CU.scalarOut(x17150_b18367_x17176_b18375_s)))
      Stage(operands=List(CU.load(x17140), Const(2)), op=FixSla, results=List(x17157))
      Stage(operands=List(x17154, x17157), op=FixAdd, results=List(x17159))
      Stage(operands=List(x17159, Const(64)), op=FixMod, results=List(x17160))
      Stage(operands=List(Const(64), x17160), op=FixSub, results=List(x17161))
      Stage(operands=List(x17161, Const(4)), op=FixDiv, results=List(x17163))
      Stage(operands=List(CU.load(x17140), x17162), op=FixAdd, results=List(x17165))
      Stage(operands=List(x17165, x17163), op=FixAdd, results=List(CU.scalarOut(x17150_b18365_x17176_b18373_s)))
      Stage(operands=List(x17157, x17155), op=FixAdd, results=List(x17167))
      Stage(operands=List(x17167, x17161), op=FixAdd, results=List(CU.scalarOut(x17149_b18364_x17174_b18372_s)))
      Stage(operands=List(x17154, x17155), op=FixSub, results=List(x17158))
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
    val x17189 = Pipeline(name="x17189",parent=x17205) { implicit CU => 
      val x17150_b18367 = ScalarFIFO(size=16,name="x17150_b18367").wtPort(x17150_b18367_x17176_b18375_s)
      val x17150_b18366 = ScalarFIFO(size=16,name="x17150_b18366").wtPort(x17150_b18366_x17176_b18374_s)
      val x17150_b18365 = ScalarFIFO(size=16,name="x17150_b18365").wtPort(x17150_b18365_x17176_b18373_s)
      val x17189_unit = CounterChain(name = "x17189_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17150_b18366)), op=Bypass, results=List(CU.scalarOut(x17179_x17184_s)))
      Stage(operands=List(CU.load(x17150_b18367)), op=Bypass, results=List(CU.scalarOut(x17180_x17186_s)))
      Stage(operands=List(CU.load(x17150_b18365)), op=Bypass, results=List(CU.scalarOut(x17181_x17188_s)))
    }
    val x17204 = Pipeline(name="x17204",parent=x17205) { implicit CU => 
      val x17194 = CU.temp(None)
      val x17196 = CU.temp(None)
      val x17180 = ScalarBuffer(name="x17180").wtPort(x17180_x17186_s)
      val x17179 = ScalarBuffer(name="x17179").wtPort(x17179_x17184_s)
      val x17151 = ScalarFIFO(size=1,name="x17151").wtPort(x17151_x17178_data_s)
      val x17181 = ScalarBuffer(name="x17181").wtPort(x17181_x17188_s)
      val ctr13 = Counter(min=Const(0), max=x17181.readPort, step=Const(1), par=1) // Counter
      val x17192 = CounterChain(name = "x17192", ctr13).iter(1)
      Stage(operands=List(CU.load(x17179), CU.ctr(x17192(0))), op=FixLeq, results=List(x17194))
      Stage(operands=List(CU.ctr(x17192(0)), CU.load(x17180)), op=FixLt, results=List(x17196))
      Stage(operands=List(CU.load(x17151)), op=Bypass, results=List(CU.scalarOut(x17063_x17203_s)))
    }
    val x17230 = MetaPipeline(name="x17230",parent=x17872) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x17208 = CounterChain(name = "x17208", ctr14).iter(64)
    }
    val x17223 = Pipeline(name="x17223",parent=x17230) { implicit CU => 
      val x17209 = CU.temp(Some(0))
      val x17215 = ScalarFIFO(size=1,name="x17215").wtPort(x17062_1_s)
      val x17214 = ScalarFIFO(size=1,name="x17214").wtPort(x16909_4_s)
      val ctr15 = Counter(min=Const(0), max=Const(13), step=Const(1), par=16) // Counter
      val x17211 = CounterChain(name = "x17211", ctr15).iter(1)
      Stage(operands=List(CU.load(x17214), CU.load(x17215)), op=FixMul, results=List(CU.reduce))
      val (_, rr1784) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17223")
      Stage(operands=List(rr1784), op=Bypass, results=List(CU.scalarOut(x17209_x17222_s)))
    }
    val x17229 = Pipeline(name="x17229",parent=x17230) { implicit CU => 
      val x17225 = ScalarFIFO(size=1,name="x17225").wtPort(x16906_4_s)
      val x17209 = ScalarBuffer(name="x17209").wtPort(x17209_x17222_s)
      val x17229_unit = CounterChain(name = "x17229_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17209), CU.load(x17225)), op=FixAdd, results=List(CU.scalarOut(x17056_x17228_s)))
    }
    val x17242 = Pipeline(name="x17242",parent=x17872) { implicit CU => 
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
    val x17259 = Pipeline(name="x17259",parent=x17266) { implicit CU => 
      val x17245 = CU.temp(Some(0))
      val x17251 = ScalarFIFO(size=1,name="x17251").wtPort(x17056_1_s)
      val x17250 = ScalarFIFO(size=1,name="x17250").wtPort(x16910_5_s)
      val ctr18 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17247 = CounterChain(name = "x17247", ctr18).iter(4)
      Stage(operands=List(CU.load(x17250), CU.load(x17251)), op=FixMul, results=List(CU.reduce))
      val (_, rr1800) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17259")
      Stage(operands=List(rr1800), op=Bypass, results=List(CU.scalarOut(x17245_x17258_s)))
    }
    val x17265 = Pipeline(name="x17265",parent=x17266) { implicit CU => 
      val x17261 = ScalarFIFO(size=1,name="x17261").wtPort(x16907_4_s)
      val x17245 = ScalarBuffer(name="x17245").wtPort(x17245_x17258_s)
      val x17265_unit = CounterChain(name = "x17265_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17245), CU.load(x17261)), op=FixAdd, results=List(CU.scalarOut(x17057_x17264_s)))
    }
    val x17278 = Pipeline(name="x17278",parent=x17872) { implicit CU => 
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
    val x17295 = Pipeline(name="x17295",parent=x17302) { implicit CU => 
      val x17281 = CU.temp(Some(0))
      val x17287 = ScalarFIFO(size=1,name="x17287").wtPort(x17057_1_s)
      val x17286 = ScalarFIFO(size=1,name="x17286").wtPort(x16911_5_s)
      val ctr21 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17283 = CounterChain(name = "x17283", ctr21).iter(4)
      Stage(operands=List(CU.load(x17286), CU.load(x17287)), op=FixMul, results=List(CU.reduce))
      val (_, rr1816) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17295")
      Stage(operands=List(rr1816), op=Bypass, results=List(CU.scalarOut(x17281_x17294_s)))
    }
    val x17301 = Pipeline(name="x17301",parent=x17302) { implicit CU => 
      val x17297 = ScalarFIFO(size=1,name="x17297").wtPort(x16908_4_s)
      val x17281 = ScalarBuffer(name="x17281").wtPort(x17281_x17294_s)
      val x17301_unit = CounterChain(name = "x17301_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17281), CU.load(x17297)), op=FixAdd, results=List(CU.scalarOut(x17058_x17300_s)))
    }
    val x17314 = Pipeline(name="x17314",parent=x17872) { implicit CU => 
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
    val x17344 = Pipeline(name="x17344",parent=x17872) { implicit CU => 
      val x17327 = CU.temp(None)
      val x17315 = CU.temp(Some(0))
      val x17325 = CU.temp(None)
      val x17336 = CU.temp(None)
      val x17328 = CU.temp(None)
      val x17340 = CU.temp(None)
      val x17338 = CU.temp(None)
      val x17322 = CU.temp(None)
      val x17329 = CU.temp(None)
      val x17331 = CU.temp(None)
      val x17330 = CU.temp(None)
      val x17323 = CU.temp(None)
      val x17333 = CU.temp(None)
      val x17337 = CU.temp(None)
      val x17332 = CU.temp(None)
      val x17339 = CU.temp(None)
      val x17335 = CU.temp(None)
      val x17334 = CU.temp(None)
      val x17326 = CU.temp(None)
      val x17324 = CU.temp(None)
      val x17319 = ScalarFIFO(size=1,name="x17319").wtPort(x17058_1_s)
      val ctr23 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17317 = CounterChain(name = "x17317", ctr23).iter(1)
      Stage(operands=List(CU.load(x17319)), op=FixNeg, results=List(x17322))
      Stage(operands=List(x17322, Const(-3.5)), op=FixLt, results=List(x17323))
      Stage(operands=List(x17322, Const(-1.2)), op=FixLt, results=List(x17324))
      Stage(operands=List(x17322, Const(0.1)), op=FixMul, results=List(x17325))
      Stage(operands=List(x17325, Const(0.35)), op=FixAdd, results=List(x17326))
      Stage(operands=List(Const(1), x17322), op=FixAdd, results=List(x17327))
      Stage(operands=List(x17322, x17322), op=FixMul, results=List(x17328))
      Stage(operands=List(x17328, Const(2)), op=FixDiv, results=List(x17329))
      Stage(operands=List(x17327, x17329), op=FixAdd, results=List(x17330))
      Stage(operands=List(x17328, x17322), op=FixMul, results=List(x17331))
      Stage(operands=List(x17331, Const(6)), op=FixDiv, results=List(x17332))
      Stage(operands=List(x17330, x17332), op=FixAdd, results=List(x17333))
      Stage(operands=List(x17331, x17322), op=FixMul, results=List(x17334))
      Stage(operands=List(x17334, Const(24)), op=FixDiv, results=List(x17335))
      Stage(operands=List(x17333, x17335), op=FixAdd, results=List(x17336))
      Stage(operands=List(x17334, x17322), op=FixMul, results=List(x17337))
      Stage(operands=List(x17337, Const(120)), op=FixDiv, results=List(x17338))
      Stage(operands=List(x17336, x17338), op=FixAdd, results=List(x17339))
      Stage(operands=List(x17324, x17326, x17339), op=Mux, results=List(x17340))
      Stage(operands=List(x17323, Const(0), x17340), op=Mux, results=List(CU.reduce))
      val (_, rr1860) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17344")
      Stage(operands=List(rr1860), op=Bypass, results=List(CU.scalarOut(x17315_x17343_s)))
    }
    val x17373 = Pipeline(name="x17373",parent=x17872) { implicit CU => 
      val x17354 = CU.temp(None)
      val x17353 = CU.temp(None)
      val x17359 = CU.temp(None)
      val x17365 = CU.temp(None)
      val x17355 = CU.temp(None)
      val x17350 = CU.temp(None)
      val x17367 = CU.temp(None)
      val x17361 = CU.temp(None)
      val x17363 = CU.temp(None)
      val x17356 = CU.temp(None)
      val x17358 = CU.temp(None)
      val x17366 = CU.temp(None)
      val x17357 = CU.temp(None)
      val x17362 = CU.temp(None)
      val x17351 = CU.temp(None)
      val x17364 = CU.temp(None)
      val x17360 = CU.temp(None)
      val x17369 = CU.temp(None)
      val x17352 = CU.temp(None)
      val x17368 = CU.temp(None)
      val x17348 = ScalarFIFO(size=1,name="x17348").wtPort(x17058_0_s)
      val x17315 = ScalarBuffer(name="x17315").wtPort(x17315_x17343_s)
      val ctr24 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17346 = CounterChain(name = "x17346", ctr24).iter(1)
      Stage(operands=List(CU.load(x17348)), op=FixNeg, results=List(x17350))
      Stage(operands=List(x17350, Const(-3.5)), op=FixLt, results=List(x17351))
      Stage(operands=List(x17350, Const(-1.2)), op=FixLt, results=List(x17352))
      Stage(operands=List(x17350, Const(0.1)), op=FixMul, results=List(x17353))
      Stage(operands=List(x17353, Const(0.35)), op=FixAdd, results=List(x17354))
      Stage(operands=List(Const(1), x17350), op=FixAdd, results=List(x17355))
      Stage(operands=List(x17350, x17350), op=FixMul, results=List(x17356))
      Stage(operands=List(x17356, Const(2)), op=FixDiv, results=List(x17357))
      Stage(operands=List(x17355, x17357), op=FixAdd, results=List(x17358))
      Stage(operands=List(x17356, x17350), op=FixMul, results=List(x17359))
      Stage(operands=List(x17359, Const(6)), op=FixDiv, results=List(x17360))
      Stage(operands=List(x17358, x17360), op=FixAdd, results=List(x17361))
      Stage(operands=List(x17359, x17350), op=FixMul, results=List(x17362))
      Stage(operands=List(x17362, Const(24)), op=FixDiv, results=List(x17363))
      Stage(operands=List(x17361, x17363), op=FixAdd, results=List(x17364))
      Stage(operands=List(x17362, x17350), op=FixMul, results=List(x17365))
      Stage(operands=List(x17365, Const(120)), op=FixDiv, results=List(x17366))
      Stage(operands=List(x17364, x17366), op=FixAdd, results=List(x17367))
      Stage(operands=List(x17352, x17354, x17367), op=Mux, results=List(x17368))
      Stage(operands=List(x17351, Const(0), x17368), op=Mux, results=List(x17369))
      Stage(operands=List(x17369, CU.load(x17315)), op=FixDiv, results=List(CU.scalarOut(x17064_x17372_s)))
    }
    val x17387 = Pipeline(name="x17387",parent=x17872) { implicit CU => 
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
    val x17398 = Pipeline(name="x17398",parent=x17872) { implicit CU => 
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
    val x17406 = Pipeline(name="x17406",parent=x17427) { implicit CU => 
      val x17404 = ScalarFIFO(size=1,name="x17404").wtPort(x17060_0_s)
      val x17406_unit = CounterChain(name = "x17406_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17404)), op=Bypass, results=List(CU.scalarOut(x17401_x17405_s)))
    }
    val x17420 = Pipeline(name="x17420",parent=x17427) { implicit CU => 
      val x17402 = CU.temp(Some(0))
      val x17412 = ScalarFIFO(size=1,name="x17412").wtPort(x16911_4_s)
      val x17411 = ScalarFIFO(size=1,name="x17411").wtPort(x17065_1_s)
      val ctr29 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17408 = CounterChain(name = "x17408", ctr29).iter(1)
      Stage(operands=List(CU.load(x17411), CU.load(x17412)), op=FixMul, results=List(CU.reduce))
      val (_, rr1906) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17420")
      Stage(operands=List(rr1906), op=Bypass, results=List(CU.scalarOut(x17402_x17419_s)))
    }
    val x17426 = Pipeline(name="x17426",parent=x17427) { implicit CU => 
      val x17401 = ScalarBuffer(name="x17401").wtPort(x17401_x17405_s)
      val x17402 = ScalarBuffer(name="x17402").wtPort(x17402_x17419_s)
      val x17426_unit = CounterChain(name = "x17426_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17401), CU.load(x17402)), op=FixMul, results=List(CU.scalarOut(x17070_x17425_s)))
    }
    val x17438 = Pipeline(name="x17438",parent=x17872) { implicit CU => 
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
    val x17446 = Pipeline(name="x17446",parent=x17467) { implicit CU => 
      val x17444 = ScalarFIFO(size=1,name="x17444").wtPort(x17059_0_s)
      val x17446_unit = CounterChain(name = "x17446_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17444)), op=Bypass, results=List(CU.scalarOut(x17441_x17445_s)))
    }
    val x17460 = Pipeline(name="x17460",parent=x17467) { implicit CU => 
      val x17442 = CU.temp(Some(0))
      val x17452 = ScalarFIFO(size=1,name="x17452").wtPort(x16910_4_s)
      val x17451 = ScalarFIFO(size=1,name="x17451").wtPort(x17070_1_s)
      val ctr33 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17448 = CounterChain(name = "x17448", ctr33).iter(4)
      Stage(operands=List(CU.load(x17451), CU.load(x17452)), op=FixMul, results=List(CU.reduce))
      val (_, rr1919) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x17460")
      Stage(operands=List(rr1919), op=Bypass, results=List(CU.scalarOut(x17442_x17459_s)))
    }
    val x17466 = Pipeline(name="x17466",parent=x17467) { implicit CU => 
      val x17442 = ScalarBuffer(name="x17442").wtPort(x17442_x17459_s)
      val x17441 = ScalarBuffer(name="x17441").wtPort(x17441_x17445_s)
      val x17466_unit = CounterChain(name = "x17466_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17441), CU.load(x17442)), op=FixMul, results=List(CU.scalarOut(x17069_x17465_s)))
    }
    val x17478 = Pipeline(name="x17478",parent=x17872) { implicit CU => 
      val x17473 = ScalarFIFO(size=1,name="x17473").wtPort(x17069_1_s)
      val x17475 = ScalarFIFO(size=1,name="x17475").wtPort(x17062_0_s)
      val ctr34 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr35 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17470 = CounterChain(name = "x17470", ctr34, ctr35).iter(52)
      Stage(operands=List(CU.load(x17473), CU.load(x17475)), op=FixMul, results=List(CU.scalarOut(x17066_x17477_s)))
    }
    val x17498 = Pipeline(name="x17498",parent=x17872) { implicit CU => 
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
    val x17516 = Pipeline(name="x17516",parent=x17872) { implicit CU => 
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
    val x17589 = Pipeline(name="x17589",parent=x17872) { implicit CU => 
      val x17542 = CU.temp(None)
      val x17580 = CU.temp(None)
      val x17529 = CU.temp(None)
      val x17533 = CU.temp(None)
      val x17549 = CU.temp(None)
      val x17560 = CU.temp(None)
      val x17585 = CU.temp(None)
      val x17570 = CU.temp(None)
      val x17581 = CU.temp(None)
      val x17572 = CU.temp(None)
      val x17565 = CU.temp(None)
      val x17534 = CU.temp(None)
      val x17574 = CU.temp(None)
      val x17536 = CU.temp(None)
      val x17527 = CU.temp(None)
      val x17578 = CU.temp(None)
      val x17547 = CU.temp(None)
      val x17568 = CU.temp(None)
      val x17525 = CU.temp(None)
      val x17544 = CU.temp(None)
      val x17551 = CU.temp(None)
      val x17557 = CU.temp(None)
      val x17562 = CU.temp(None)
      val x17554 = CU.temp(None)
      val x17528 = CU.temp(None)
      val x17571 = CU.temp(None)
      val x17546 = CU.temp(None)
      val x17532 = CU.temp(None)
      val x17575 = CU.temp(None)
      val x17543 = CU.temp(None)
      val x17579 = CU.temp(None)
      val x17577 = CU.temp(None)
      val x17563 = CU.temp(None)
      val x17555 = CU.temp(None)
      val x17576 = CU.temp(None)
      val x17567 = CU.temp(None)
      val x17523 = CU.temp(None)
      val x17559 = CU.temp(None)
      val x17522 = CU.temp(None)
      val x17564 = CU.temp(None)
      val x17540 = CU.temp(None)
      val x17548 = CU.temp(None)
      val x17556 = CU.temp(None)
      val x17531 = CU.temp(None)
      val x17538 = CU.temp(None)
      val x17582 = CU.temp(None)
      val x17566 = CU.temp(None)
      val x17561 = CU.temp(None)
      val x17573 = CU.temp(None)
      val x17541 = CU.temp(None)
      val x17530 = CU.temp(None)
      val x17583 = CU.temp(None)
      val x17569 = CU.temp(None)
      val x17524 = CU.temp(None)
      val x17521 = CU.temp(None)
      val x17550 = CU.temp(None)
      val x17539 = CU.temp(None)
      val x17535 = CU.temp(None)
      val x17584 = CU.temp(None)
      val x17545 = CU.temp(None)
      val x17520 = CU.temp(None)
      val x17558 = CU.temp(None)
      val x17526 = CU.temp(None)
      val x17537 = CU.temp(None)
      val x17479 = ScalarBuffer(name="x17479").wtPort(x17479_x17497_s)
      val x17499 = ScalarBuffer(name="x17499").wtPort(x17499_x17515_s)
      val x17589_unit = CounterChain(name = "x17589_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17499), Const(2)), op=FixLt, results=List(x17554))
      Stage(operands=List(CU.load(x17499), Const(1)), op=FixSub, results=List(x17555))
      Stage(operands=List(x17555, Const(2)), op=FixDiv, results=List(x17556))
      Stage(operands=List(Const(1), x17556), op=FixAdd, results=List(x17557))
      Stage(operands=List(x17555, x17555), op=FixMul, results=List(x17558))
      Stage(operands=List(x17558, Const(8)), op=FixDiv, results=List(x17559))
      Stage(operands=List(x17557, x17559), op=FixSub, results=List(x17560))
      Stage(operands=List(x17558, x17555), op=FixMul, results=List(x17561))
      Stage(operands=List(x17561, Const(16)), op=FixDiv, results=List(x17562))
      Stage(operands=List(x17560, x17562), op=FixAdd, results=List(x17563))
      Stage(operands=List(CU.load(x17499), Const(10)), op=FixLt, results=List(x17564))
      Stage(operands=List(CU.load(x17499), Const(0.22)), op=FixMul, results=List(x17565))
      Stage(operands=List(x17565, Const(1)), op=FixAdd, results=List(x17566))
      Stage(operands=List(CU.load(x17499), Const(100)), op=FixLt, results=List(x17567))
      Stage(operands=List(CU.load(x17499), Const(0.08)), op=FixMul, results=List(x17568))
      Stage(operands=List(x17568, Const(2.5)), op=FixAdd, results=List(x17569))
      Stage(operands=List(CU.load(x17499), Const(1000)), op=FixLt, results=List(x17570))
      Stage(operands=List(CU.load(x17499), Const(0.028)), op=FixMul, results=List(x17571))
      Stage(operands=List(x17571, Const(8)), op=FixAdd, results=List(x17572))
      Stage(operands=List(CU.load(x17499), Const(10000)), op=FixLt, results=List(x17573))
      Stage(operands=List(CU.load(x17499), Const(0.008)), op=FixMul, results=List(x17574))
      Stage(operands=List(x17574, Const(20)), op=FixAdd, results=List(x17575))
      Stage(operands=List(CU.load(x17499), Const(32767)), op=FixLt, results=List(x17576))
      Stage(operands=List(CU.load(x17499), Const(0.003)), op=FixMul, results=List(x17577))
      Stage(operands=List(x17577, Const(60)), op=FixAdd, results=List(x17578))
      Stage(operands=List(CU.load(x17499), Const(2.0E-4)), op=FixMul, results=List(x17579))
      Stage(operands=List(x17579, Const(300)), op=FixAdd, results=List(x17580))
      Stage(operands=List(x17576, x17578, x17580), op=Mux, results=List(x17581))
      Stage(operands=List(x17573, x17575, x17581), op=Mux, results=List(x17582))
      Stage(operands=List(x17570, x17572, x17582), op=Mux, results=List(x17583))
      Stage(operands=List(x17567, x17569, x17583), op=Mux, results=List(x17584))
      Stage(operands=List(x17564, x17566, x17584), op=Mux, results=List(x17585))
      Stage(operands=List(x17554, x17563, x17585), op=Mux, results=List(CU.scalarOut(x17518_x17588_s)))
      Stage(operands=List(CU.load(x17479), Const(2)), op=FixLt, results=List(x17520))
      Stage(operands=List(CU.load(x17479), Const(1)), op=FixSub, results=List(x17521))
      Stage(operands=List(x17521, Const(2)), op=FixDiv, results=List(x17522))
      Stage(operands=List(Const(1), x17522), op=FixAdd, results=List(x17523))
      Stage(operands=List(x17521, x17521), op=FixMul, results=List(x17524))
      Stage(operands=List(x17524, Const(8)), op=FixDiv, results=List(x17525))
      Stage(operands=List(x17523, x17525), op=FixSub, results=List(x17526))
      Stage(operands=List(x17524, x17521), op=FixMul, results=List(x17527))
      Stage(operands=List(x17527, Const(16)), op=FixDiv, results=List(x17528))
      Stage(operands=List(x17526, x17528), op=FixAdd, results=List(x17529))
      Stage(operands=List(CU.load(x17479), Const(10)), op=FixLt, results=List(x17530))
      Stage(operands=List(CU.load(x17479), Const(0.22)), op=FixMul, results=List(x17531))
      Stage(operands=List(x17531, Const(1)), op=FixAdd, results=List(x17532))
      Stage(operands=List(CU.load(x17479), Const(100)), op=FixLt, results=List(x17533))
      Stage(operands=List(CU.load(x17479), Const(0.08)), op=FixMul, results=List(x17534))
      Stage(operands=List(x17534, Const(2.5)), op=FixAdd, results=List(x17535))
      Stage(operands=List(CU.load(x17479), Const(1000)), op=FixLt, results=List(x17536))
      Stage(operands=List(CU.load(x17479), Const(0.028)), op=FixMul, results=List(x17537))
      Stage(operands=List(x17537, Const(8)), op=FixAdd, results=List(x17538))
      Stage(operands=List(CU.load(x17479), Const(10000)), op=FixLt, results=List(x17539))
      Stage(operands=List(CU.load(x17479), Const(0.008)), op=FixMul, results=List(x17540))
      Stage(operands=List(x17540, Const(20)), op=FixAdd, results=List(x17541))
      Stage(operands=List(CU.load(x17479), Const(32767)), op=FixLt, results=List(x17542))
      Stage(operands=List(CU.load(x17479), Const(0.003)), op=FixMul, results=List(x17543))
      Stage(operands=List(x17543, Const(60)), op=FixAdd, results=List(x17544))
      Stage(operands=List(CU.load(x17479), Const(2.0E-4)), op=FixMul, results=List(x17545))
      Stage(operands=List(x17545, Const(300)), op=FixAdd, results=List(x17546))
      Stage(operands=List(x17542, x17544, x17546), op=Mux, results=List(x17547))
      Stage(operands=List(x17539, x17541, x17547), op=Mux, results=List(x17548))
      Stage(operands=List(x17536, x17538, x17548), op=Mux, results=List(x17549))
      Stage(operands=List(x17533, x17535, x17549), op=Mux, results=List(x17550))
      Stage(operands=List(x17530, x17532, x17550), op=Mux, results=List(x17551))
      Stage(operands=List(x17520, x17529, x17551), op=Mux, results=List(CU.scalarOut(x17517_x17587_s)))
    }
    val x17600 = Pipeline(name="x17600",parent=x17872) { implicit CU => 
      val x17595 = ScalarFIFO(size=1,name="x17595").wtPort(x16909_1_s)
      val x17517 = ScalarBuffer(name="x17517").wtPort(x17517_x17587_s)
      val ctr39 = Counter(min=Const(0), max=Const(13), step=Const(1), par=1) // Counter
      val ctr40 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17592 = CounterChain(name = "x17592", ctr39, ctr40).iter(52)
      Stage(operands=List(CU.load(x17595), CU.load(x17517)), op=FixDiv, results=List(CU.scalarOut(x16909_x17599_s)))
    }
    val x17609 = Pipeline(name="x17609",parent=x17872) { implicit CU => 
      val x17518 = ScalarBuffer(name="x17518").wtPort(x17518_x17588_s)
      val x17604 = ScalarFIFO(size=1,name="x17604").wtPort(x16906_1_s)
      val ctr41 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17602 = CounterChain(name = "x17602", ctr41).iter(4)
      Stage(operands=List(CU.load(x17604), CU.load(x17518)), op=FixDiv, results=List(CU.scalarOut(x16906_x17608_s)))
    }
    val x17629 = Pipeline(name="x17629",parent=x17872) { implicit CU => 
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
    val x17647 = Pipeline(name="x17647",parent=x17872) { implicit CU => 
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
    val x17720 = Pipeline(name="x17720",parent=x17872) { implicit CU => 
      val x17702 = CU.temp(None)
      val x17669 = CU.temp(None)
      val x17662 = CU.temp(None)
      val x17691 = CU.temp(None)
      val x17660 = CU.temp(None)
      val x17710 = CU.temp(None)
      val x17657 = CU.temp(None)
      val x17659 = CU.temp(None)
      val x17681 = CU.temp(None)
      val x17714 = CU.temp(None)
      val x17696 = CU.temp(None)
      val x17667 = CU.temp(None)
      val x17690 = CU.temp(None)
      val x17673 = CU.temp(None)
      val x17688 = CU.temp(None)
      val x17703 = CU.temp(None)
      val x17712 = CU.temp(None)
      val x17693 = CU.temp(None)
      val x17706 = CU.temp(None)
      val x17707 = CU.temp(None)
      val x17654 = CU.temp(None)
      val x17700 = CU.temp(None)
      val x17709 = CU.temp(None)
      val x17705 = CU.temp(None)
      val x17708 = CU.temp(None)
      val x17651 = CU.temp(None)
      val x17692 = CU.temp(None)
      val x17678 = CU.temp(None)
      val x17666 = CU.temp(None)
      val x17685 = CU.temp(None)
      val x17671 = CU.temp(None)
      val x17675 = CU.temp(None)
      val x17665 = CU.temp(None)
      val x17655 = CU.temp(None)
      val x17689 = CU.temp(None)
      val x17676 = CU.temp(None)
      val x17716 = CU.temp(None)
      val x17711 = CU.temp(None)
      val x17653 = CU.temp(None)
      val x17661 = CU.temp(None)
      val x17677 = CU.temp(None)
      val x17704 = CU.temp(None)
      val x17680 = CU.temp(None)
      val x17713 = CU.temp(None)
      val x17682 = CU.temp(None)
      val x17670 = CU.temp(None)
      val x17695 = CU.temp(None)
      val x17686 = CU.temp(None)
      val x17658 = CU.temp(None)
      val x17701 = CU.temp(None)
      val x17663 = CU.temp(None)
      val x17656 = CU.temp(None)
      val x17687 = CU.temp(None)
      val x17699 = CU.temp(None)
      val x17674 = CU.temp(None)
      val x17652 = CU.temp(None)
      val x17694 = CU.temp(None)
      val x17698 = CU.temp(None)
      val x17672 = CU.temp(None)
      val x17664 = CU.temp(None)
      val x17679 = CU.temp(None)
      val x17697 = CU.temp(None)
      val x17715 = CU.temp(None)
      val x17668 = CU.temp(None)
      val x17610 = ScalarBuffer(name="x17610").wtPort(x17610_x17628_s)
      val x17630 = ScalarBuffer(name="x17630").wtPort(x17630_x17646_s)
      val x17720_unit = CounterChain(name = "x17720_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17630), Const(2)), op=FixLt, results=List(x17685))
      Stage(operands=List(CU.load(x17630), Const(1)), op=FixSub, results=List(x17686))
      Stage(operands=List(x17686, Const(2)), op=FixDiv, results=List(x17687))
      Stage(operands=List(Const(1), x17687), op=FixAdd, results=List(x17688))
      Stage(operands=List(x17686, x17686), op=FixMul, results=List(x17689))
      Stage(operands=List(x17689, Const(8)), op=FixDiv, results=List(x17690))
      Stage(operands=List(x17688, x17690), op=FixSub, results=List(x17691))
      Stage(operands=List(x17689, x17686), op=FixMul, results=List(x17692))
      Stage(operands=List(x17692, Const(16)), op=FixDiv, results=List(x17693))
      Stage(operands=List(x17691, x17693), op=FixAdd, results=List(x17694))
      Stage(operands=List(CU.load(x17630), Const(10)), op=FixLt, results=List(x17695))
      Stage(operands=List(CU.load(x17630), Const(0.22)), op=FixMul, results=List(x17696))
      Stage(operands=List(x17696, Const(1)), op=FixAdd, results=List(x17697))
      Stage(operands=List(CU.load(x17630), Const(100)), op=FixLt, results=List(x17698))
      Stage(operands=List(CU.load(x17630), Const(0.08)), op=FixMul, results=List(x17699))
      Stage(operands=List(x17699, Const(2.5)), op=FixAdd, results=List(x17700))
      Stage(operands=List(CU.load(x17630), Const(1000)), op=FixLt, results=List(x17701))
      Stage(operands=List(CU.load(x17630), Const(0.028)), op=FixMul, results=List(x17702))
      Stage(operands=List(x17702, Const(8)), op=FixAdd, results=List(x17703))
      Stage(operands=List(CU.load(x17630), Const(10000)), op=FixLt, results=List(x17704))
      Stage(operands=List(CU.load(x17630), Const(0.008)), op=FixMul, results=List(x17705))
      Stage(operands=List(x17705, Const(20)), op=FixAdd, results=List(x17706))
      Stage(operands=List(CU.load(x17630), Const(32767)), op=FixLt, results=List(x17707))
      Stage(operands=List(CU.load(x17630), Const(0.003)), op=FixMul, results=List(x17708))
      Stage(operands=List(x17708, Const(60)), op=FixAdd, results=List(x17709))
      Stage(operands=List(CU.load(x17630), Const(2.0E-4)), op=FixMul, results=List(x17710))
      Stage(operands=List(x17710, Const(300)), op=FixAdd, results=List(x17711))
      Stage(operands=List(x17707, x17709, x17711), op=Mux, results=List(x17712))
      Stage(operands=List(x17704, x17706, x17712), op=Mux, results=List(x17713))
      Stage(operands=List(x17701, x17703, x17713), op=Mux, results=List(x17714))
      Stage(operands=List(x17698, x17700, x17714), op=Mux, results=List(x17715))
      Stage(operands=List(x17695, x17697, x17715), op=Mux, results=List(x17716))
      Stage(operands=List(x17685, x17694, x17716), op=Mux, results=List(CU.scalarOut(x17649_x17719_s)))
      Stage(operands=List(CU.load(x17610), Const(2)), op=FixLt, results=List(x17651))
      Stage(operands=List(CU.load(x17610), Const(1)), op=FixSub, results=List(x17652))
      Stage(operands=List(x17652, Const(2)), op=FixDiv, results=List(x17653))
      Stage(operands=List(Const(1), x17653), op=FixAdd, results=List(x17654))
      Stage(operands=List(x17652, x17652), op=FixMul, results=List(x17655))
      Stage(operands=List(x17655, Const(8)), op=FixDiv, results=List(x17656))
      Stage(operands=List(x17654, x17656), op=FixSub, results=List(x17657))
      Stage(operands=List(x17655, x17652), op=FixMul, results=List(x17658))
      Stage(operands=List(x17658, Const(16)), op=FixDiv, results=List(x17659))
      Stage(operands=List(x17657, x17659), op=FixAdd, results=List(x17660))
      Stage(operands=List(CU.load(x17610), Const(10)), op=FixLt, results=List(x17661))
      Stage(operands=List(CU.load(x17610), Const(0.22)), op=FixMul, results=List(x17662))
      Stage(operands=List(x17662, Const(1)), op=FixAdd, results=List(x17663))
      Stage(operands=List(CU.load(x17610), Const(100)), op=FixLt, results=List(x17664))
      Stage(operands=List(CU.load(x17610), Const(0.08)), op=FixMul, results=List(x17665))
      Stage(operands=List(x17665, Const(2.5)), op=FixAdd, results=List(x17666))
      Stage(operands=List(CU.load(x17610), Const(1000)), op=FixLt, results=List(x17667))
      Stage(operands=List(CU.load(x17610), Const(0.028)), op=FixMul, results=List(x17668))
      Stage(operands=List(x17668, Const(8)), op=FixAdd, results=List(x17669))
      Stage(operands=List(CU.load(x17610), Const(10000)), op=FixLt, results=List(x17670))
      Stage(operands=List(CU.load(x17610), Const(0.008)), op=FixMul, results=List(x17671))
      Stage(operands=List(x17671, Const(20)), op=FixAdd, results=List(x17672))
      Stage(operands=List(CU.load(x17610), Const(32767)), op=FixLt, results=List(x17673))
      Stage(operands=List(CU.load(x17610), Const(0.003)), op=FixMul, results=List(x17674))
      Stage(operands=List(x17674, Const(60)), op=FixAdd, results=List(x17675))
      Stage(operands=List(CU.load(x17610), Const(2.0E-4)), op=FixMul, results=List(x17676))
      Stage(operands=List(x17676, Const(300)), op=FixAdd, results=List(x17677))
      Stage(operands=List(x17673, x17675, x17677), op=Mux, results=List(x17678))
      Stage(operands=List(x17670, x17672, x17678), op=Mux, results=List(x17679))
      Stage(operands=List(x17667, x17669, x17679), op=Mux, results=List(x17680))
      Stage(operands=List(x17664, x17666, x17680), op=Mux, results=List(x17681))
      Stage(operands=List(x17661, x17663, x17681), op=Mux, results=List(x17682))
      Stage(operands=List(x17651, x17660, x17682), op=Mux, results=List(CU.scalarOut(x17648_x17718_s)))
    }
    val x17731 = Pipeline(name="x17731",parent=x17872) { implicit CU => 
      val x17726 = ScalarFIFO(size=1,name="x17726").wtPort(x16910_1_s)
      val x17648 = ScalarBuffer(name="x17648").wtPort(x17648_x17718_s)
      val ctr45 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr46 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17723 = CounterChain(name = "x17723", ctr45, ctr46).iter(256)
      Stage(operands=List(CU.load(x17726), CU.load(x17648)), op=FixDiv, results=List(CU.scalarOut(x16910_x17730_s)))
    }
    val x17740 = Pipeline(name="x17740",parent=x17872) { implicit CU => 
      val x17649 = ScalarBuffer(name="x17649").wtPort(x17649_x17719_s)
      val x17735 = ScalarFIFO(size=1,name="x17735").wtPort(x16907_1_s)
      val ctr47 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x17733 = CounterChain(name = "x17733", ctr47).iter(4)
      Stage(operands=List(CU.load(x17735), CU.load(x17649)), op=FixDiv, results=List(CU.scalarOut(x16907_x17739_s)))
    }
    val x17760 = Pipeline(name="x17760",parent=x17872) { implicit CU => 
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
    val x17778 = Pipeline(name="x17778",parent=x17872) { implicit CU => 
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
    val x17851 = Pipeline(name="x17851",parent=x17872) { implicit CU => 
      val x17838 = CU.temp(None)
      val x17825 = CU.temp(None)
      val x17796 = CU.temp(None)
      val x17817 = CU.temp(None)
      val x17836 = CU.temp(None)
      val x17786 = CU.temp(None)
      val x17793 = CU.temp(None)
      val x17798 = CU.temp(None)
      val x17847 = CU.temp(None)
      val x17807 = CU.temp(None)
      val x17784 = CU.temp(None)
      val x17792 = CU.temp(None)
      val x17811 = CU.temp(None)
      val x17843 = CU.temp(None)
      val x17808 = CU.temp(None)
      val x17795 = CU.temp(None)
      val x17833 = CU.temp(None)
      val x17830 = CU.temp(None)
      val x17804 = CU.temp(None)
      val x17812 = CU.temp(None)
      val x17782 = CU.temp(None)
      val x17846 = CU.temp(None)
      val x17810 = CU.temp(None)
      val x17785 = CU.temp(None)
      val x17788 = CU.temp(None)
      val x17783 = CU.temp(None)
      val x17827 = CU.temp(None)
      val x17802 = CU.temp(None)
      val x17828 = CU.temp(None)
      val x17787 = CU.temp(None)
      val x17819 = CU.temp(None)
      val x17832 = CU.temp(None)
      val x17813 = CU.temp(None)
      val x17823 = CU.temp(None)
      val x17820 = CU.temp(None)
      val x17845 = CU.temp(None)
      val x17839 = CU.temp(None)
      val x17816 = CU.temp(None)
      val x17841 = CU.temp(None)
      val x17826 = CU.temp(None)
      val x17800 = CU.temp(None)
      val x17791 = CU.temp(None)
      val x17799 = CU.temp(None)
      val x17835 = CU.temp(None)
      val x17840 = CU.temp(None)
      val x17831 = CU.temp(None)
      val x17789 = CU.temp(None)
      val x17821 = CU.temp(None)
      val x17842 = CU.temp(None)
      val x17834 = CU.temp(None)
      val x17801 = CU.temp(None)
      val x17837 = CU.temp(None)
      val x17803 = CU.temp(None)
      val x17794 = CU.temp(None)
      val x17844 = CU.temp(None)
      val x17805 = CU.temp(None)
      val x17809 = CU.temp(None)
      val x17824 = CU.temp(None)
      val x17806 = CU.temp(None)
      val x17829 = CU.temp(None)
      val x17822 = CU.temp(None)
      val x17797 = CU.temp(None)
      val x17818 = CU.temp(None)
      val x17790 = CU.temp(None)
      val x17741 = ScalarBuffer(name="x17741").wtPort(x17741_x17759_s)
      val x17761 = ScalarBuffer(name="x17761").wtPort(x17761_x17777_s)
      val x17851_unit = CounterChain(name = "x17851_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x17761), Const(2)), op=FixLt, results=List(x17816))
      Stage(operands=List(CU.load(x17761), Const(1)), op=FixSub, results=List(x17817))
      Stage(operands=List(x17817, Const(2)), op=FixDiv, results=List(x17818))
      Stage(operands=List(Const(1), x17818), op=FixAdd, results=List(x17819))
      Stage(operands=List(x17817, x17817), op=FixMul, results=List(x17820))
      Stage(operands=List(x17820, Const(8)), op=FixDiv, results=List(x17821))
      Stage(operands=List(x17819, x17821), op=FixSub, results=List(x17822))
      Stage(operands=List(x17820, x17817), op=FixMul, results=List(x17823))
      Stage(operands=List(x17823, Const(16)), op=FixDiv, results=List(x17824))
      Stage(operands=List(x17822, x17824), op=FixAdd, results=List(x17825))
      Stage(operands=List(CU.load(x17761), Const(10)), op=FixLt, results=List(x17826))
      Stage(operands=List(CU.load(x17761), Const(0.22)), op=FixMul, results=List(x17827))
      Stage(operands=List(x17827, Const(1)), op=FixAdd, results=List(x17828))
      Stage(operands=List(CU.load(x17761), Const(100)), op=FixLt, results=List(x17829))
      Stage(operands=List(CU.load(x17761), Const(0.08)), op=FixMul, results=List(x17830))
      Stage(operands=List(x17830, Const(2.5)), op=FixAdd, results=List(x17831))
      Stage(operands=List(CU.load(x17761), Const(1000)), op=FixLt, results=List(x17832))
      Stage(operands=List(CU.load(x17761), Const(0.028)), op=FixMul, results=List(x17833))
      Stage(operands=List(x17833, Const(8)), op=FixAdd, results=List(x17834))
      Stage(operands=List(CU.load(x17761), Const(10000)), op=FixLt, results=List(x17835))
      Stage(operands=List(CU.load(x17761), Const(0.008)), op=FixMul, results=List(x17836))
      Stage(operands=List(x17836, Const(20)), op=FixAdd, results=List(x17837))
      Stage(operands=List(CU.load(x17761), Const(32767)), op=FixLt, results=List(x17838))
      Stage(operands=List(CU.load(x17761), Const(0.003)), op=FixMul, results=List(x17839))
      Stage(operands=List(x17839, Const(60)), op=FixAdd, results=List(x17840))
      Stage(operands=List(CU.load(x17761), Const(2.0E-4)), op=FixMul, results=List(x17841))
      Stage(operands=List(x17841, Const(300)), op=FixAdd, results=List(x17842))
      Stage(operands=List(x17838, x17840, x17842), op=Mux, results=List(x17843))
      Stage(operands=List(x17835, x17837, x17843), op=Mux, results=List(x17844))
      Stage(operands=List(x17832, x17834, x17844), op=Mux, results=List(x17845))
      Stage(operands=List(x17829, x17831, x17845), op=Mux, results=List(x17846))
      Stage(operands=List(x17826, x17828, x17846), op=Mux, results=List(x17847))
      Stage(operands=List(x17816, x17825, x17847), op=Mux, results=List(CU.scalarOut(x17780_x17850_s)))
      Stage(operands=List(CU.load(x17741), Const(2)), op=FixLt, results=List(x17782))
      Stage(operands=List(CU.load(x17741), Const(1)), op=FixSub, results=List(x17783))
      Stage(operands=List(x17783, Const(2)), op=FixDiv, results=List(x17784))
      Stage(operands=List(Const(1), x17784), op=FixAdd, results=List(x17785))
      Stage(operands=List(x17783, x17783), op=FixMul, results=List(x17786))
      Stage(operands=List(x17786, Const(8)), op=FixDiv, results=List(x17787))
      Stage(operands=List(x17785, x17787), op=FixSub, results=List(x17788))
      Stage(operands=List(x17786, x17783), op=FixMul, results=List(x17789))
      Stage(operands=List(x17789, Const(16)), op=FixDiv, results=List(x17790))
      Stage(operands=List(x17788, x17790), op=FixAdd, results=List(x17791))
      Stage(operands=List(CU.load(x17741), Const(10)), op=FixLt, results=List(x17792))
      Stage(operands=List(CU.load(x17741), Const(0.22)), op=FixMul, results=List(x17793))
      Stage(operands=List(x17793, Const(1)), op=FixAdd, results=List(x17794))
      Stage(operands=List(CU.load(x17741), Const(100)), op=FixLt, results=List(x17795))
      Stage(operands=List(CU.load(x17741), Const(0.08)), op=FixMul, results=List(x17796))
      Stage(operands=List(x17796, Const(2.5)), op=FixAdd, results=List(x17797))
      Stage(operands=List(CU.load(x17741), Const(1000)), op=FixLt, results=List(x17798))
      Stage(operands=List(CU.load(x17741), Const(0.028)), op=FixMul, results=List(x17799))
      Stage(operands=List(x17799, Const(8)), op=FixAdd, results=List(x17800))
      Stage(operands=List(CU.load(x17741), Const(10000)), op=FixLt, results=List(x17801))
      Stage(operands=List(CU.load(x17741), Const(0.008)), op=FixMul, results=List(x17802))
      Stage(operands=List(x17802, Const(20)), op=FixAdd, results=List(x17803))
      Stage(operands=List(CU.load(x17741), Const(32767)), op=FixLt, results=List(x17804))
      Stage(operands=List(CU.load(x17741), Const(0.003)), op=FixMul, results=List(x17805))
      Stage(operands=List(x17805, Const(60)), op=FixAdd, results=List(x17806))
      Stage(operands=List(CU.load(x17741), Const(2.0E-4)), op=FixMul, results=List(x17807))
      Stage(operands=List(x17807, Const(300)), op=FixAdd, results=List(x17808))
      Stage(operands=List(x17804, x17806, x17808), op=Mux, results=List(x17809))
      Stage(operands=List(x17801, x17803, x17809), op=Mux, results=List(x17810))
      Stage(operands=List(x17798, x17800, x17810), op=Mux, results=List(x17811))
      Stage(operands=List(x17795, x17797, x17811), op=Mux, results=List(x17812))
      Stage(operands=List(x17792, x17794, x17812), op=Mux, results=List(x17813))
      Stage(operands=List(x17782, x17791, x17813), op=Mux, results=List(CU.scalarOut(x17779_x17849_s)))
    }
    val x17862 = Pipeline(name="x17862",parent=x17872) { implicit CU => 
      val x17857 = ScalarFIFO(size=1,name="x17857").wtPort(x16911_1_s)
      val x17779 = ScalarBuffer(name="x17779").wtPort(x17779_x17849_s)
      val ctr51 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr52 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17854 = CounterChain(name = "x17854", ctr51, ctr52).iter(64)
      Stage(operands=List(CU.load(x17857), CU.load(x17779)), op=FixDiv, results=List(CU.scalarOut(x16911_x17861_s)))
    }
    val x17871 = Pipeline(name="x17871",parent=x17872) { implicit CU => 
      val x17780 = ScalarBuffer(name="x17780").wtPort(x17780_x17850_s)
      val x17866 = ScalarFIFO(size=1,name="x17866").wtPort(x16908_1_s)
      val ctr53 = Counter(min=Const(0), max=Const(3), step=Const(1), par=16) // Counter
      val x17864 = CounterChain(name = "x17864", ctr53).iter(1)
      Stage(operands=List(CU.load(x17866), CU.load(x17780)), op=FixDiv, results=List(CU.scalarOut(x16908_x17870_s)))
    }
    val x17882 = Pipeline(name="x17882",parent=x18033) { implicit CU => 
      val x17876 = CU.temp(None)
      val x17879 = ScalarFIFO(size=1,name="x17879").wtPort(x16911_0_s)
      val ctr54 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1) // Counter
      val x17875 = CounterChain(name = "x17875", ctr54, ctr55).iter(192)
      Stage(operands=List(CU.ctr(x17875(0)), Const(3)), op=FixMul, results=List(x17876))
      Stage(operands=List(CU.load(x17879)), op=Bypass, results=List(CU.scalarOut(x16912_x17881_s)))
    }
    val x17905 = StreamController(name="x17905",parent=x18033) { implicit CU => 
      val x17905_unit = CounterChain(name = "x17905_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x17894 = Pipeline(name="x17894",parent=x17905) { implicit CU => 
      val x17887 = CU.temp(None)
      val x17889 = ScalarBuffer(name="x17889").wtPort(biases1_dram_da)
      val x17894_unit = CounterChain(name = "x17894_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17887))
      Stage(operands=List(x17887, CU.load(x17889)), op=FixAdd, results=List(CU.scalarOut(x17883_b18430_x17893_b18432_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17883_b18431_x17893_b18433_s)))
    }
    val x17901 = Pipeline(name="x17901",parent=x17905) { implicit CU => 
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
    val x17917 = Pipeline(name="x17917",parent=x17928) { implicit CU => 
      val x17910 = CU.temp(None)
      val x17912 = ScalarBuffer(name="x17912").wtPort(biases2_dram_da)
      val x17917_unit = CounterChain(name = "x17917_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17910))
      Stage(operands=List(x17910, CU.load(x17912)), op=FixAdd, results=List(CU.scalarOut(x17906_b18434_x17916_b18436_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x17906_b18435_x17916_b18437_s)))
    }
    val x17924 = Pipeline(name="x17924",parent=x17928) { implicit CU => 
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
    val x17940 = Pipeline(name="x17940",parent=x17951) { implicit CU => 
      val x17933 = CU.temp(None)
      val x17935 = ScalarBuffer(name="x17935").wtPort(biases3_dram_da)
      val x17940_unit = CounterChain(name = "x17940_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x17933))
      Stage(operands=List(x17933, CU.load(x17935)), op=FixAdd, results=List(CU.scalarOut(x17929_b18438_x17939_b18440_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x17929_b18439_x17939_b18441_s)))
    }
    val x17947 = Pipeline(name="x17947",parent=x17951) { implicit CU => 
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
    val x17968 = Pipeline(name="x17968",parent=x17980) { implicit CU => 
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
    val x17976 = Pipeline(name="x17976",parent=x17980) { implicit CU => 
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
    val x17997 = Pipeline(name="x17997",parent=x18009) { implicit CU => 
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
    val x18005 = Pipeline(name="x18005",parent=x18009) { implicit CU => 
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
    val x18021 = Pipeline(name="x18021",parent=x18032) { implicit CU => 
      val x18014 = CU.temp(None)
      val x18016 = ScalarBuffer(name="x18016").wtPort(weights3_dram_da)
      val x18021_unit = CounterChain(name = "x18021_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x18014))
      Stage(operands=List(x18014, CU.load(x18016)), op=FixAdd, results=List(CU.scalarOut(x18010_b18454_x18020_b18456_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x18010_b18455_x18020_b18457_s)))
    }
    val x18028 = Pipeline(name="x18028",parent=x18032) { implicit CU => 
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
