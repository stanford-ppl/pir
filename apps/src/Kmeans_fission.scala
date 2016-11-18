import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object Kmeans_fissionDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x7197_x7913_addr_vector = Vector("x7197_x7913_addr")
    val bus_6602_vector = Vector("bus_6602")
    val x7251_x7919_addr_vector = Vector("x7251_x7919_addr")
    val x7197_x7445_data_vector = Vector("x7197_x7445_data")
    val x7314_scalar = Scalar("x7314")
    val x7308_scalar = Scalar("x7308")
    val bus_7278_vector = Vector("bus_7278")
    val x7245_x7772_addr_vector = Vector("x7245_x7772_addr")
    val bus_6458_vector = Vector("bus_6458")
    val x7368_scalar = Scalar("x7368")
    val bus_6821_vector = Vector("bus_6821")
    val x7245_vector = Vector("x7245")
    val bus_6749_vector = Vector("bus_6749")
    val x7947_x8257_addr_vector = Vector("x7947_x8257_addr")
    val bus_7254_vector = Vector("bus_7254")
    val x7620_scalar = Scalar("x7620")
    val x7245_x7916_addr_vector = Vector("x7245_x7916_addr")
    val bus_6345_vector = Vector("bus_6345")
    val bus_7286_vector = Vector("bus_7286")
    val bus_6745_vector = Vector("bus_6745")
    val x7312_scalar = Scalar("x7312")
    val bus_6381_vector = Vector("bus_6381")
    val bus_6561_vector = Vector("bus_6561")
    val bus_6633_vector = Vector("bus_6633")
    val bus_6566_vector = Vector("bus_6566")
    val x7245_x7556_addr_vector = Vector("x7245_x7556_addr")
    val x7245_x7376_addr_vector = Vector("x7245_x7376_addr")
    val bus_6713_vector = Vector("bus_6713")
    val x7197_x7409_data_vector = Vector("x7197_x7409_data")
    val x7197_x7733_data_vector = Vector("x7197_x7733_data")
    val bus_7246_vector = Vector("bus_7246")
    val x7245_x7700_addr_vector = Vector("x7245_x7700_addr")
    val x7305_scalar = Scalar("x7305")
    val x7197_x7589_data_vector = Vector("x7197_x7589_data")
    val bus_6813_vector = Vector("bus_6813")
    val x7251_x7883_addr_vector = Vector("x7251_x7883_addr")
    val bus_6350_vector = Vector("bus_6350")
    val x7950_vector = Vector("x7950")
    val bus_6857_vector = Vector("bus_6857")
    val x7197_x7769_data_vector = Vector("x7197_x7769_data")
    val bus_7262_vector = Vector("bus_7262")
    val bus_6421_vector = Vector("bus_6421")
    val bus_6777_vector = Vector("bus_6777")
    val bus_6637_vector = Vector("bus_6637")
    val x7197_x7553_addr_vector = Vector("x7197_x7553_addr")
    val x7251_x7811_addr_vector = Vector("x7251_x7811_addr")
    val bus_7337_vector = Vector("bus_7337")
    val bus_6453_vector = Vector("bus_6453")
    val bus_6818_vector = Vector("bus_6818")
    val x7197_x7517_data_vector = Vector("x7197_x7517_data")
    val x7872_scalar = Scalar("x7872")
    val x7304_scalar = Scalar("x7304")
    val x7197_x7805_data_vector = Vector("x7197_x7805_data")
    val x7197_x7661_addr_vector = Vector("x7197_x7661_addr")
    val bus_6385_vector = Vector("bus_6385")
    val x7946_vector = Vector("x7946")
    val x8398_vector = Vector("x8398")
    val bus_6669_vector = Vector("bus_6669")
    val bus_6746_vector = Vector("bus_6746")
    val bus_6674_vector = Vector("bus_6674")
    val x7197_x7409_addr_vector = Vector("x7197_x7409_addr")
    val x7197_x7373_addr_vector = Vector("x7197_x7373_addr")
    val bus_6489_vector = Vector("bus_6489")
    val x7952_vector = Vector("x7952")
    val x7245_x7736_addr_vector = Vector("x7245_x7736_addr")
    val x7251_x7631_addr_vector = Vector("x7251_x7631_addr")
    val bus_7270_vector = Vector("bus_7270")
    val bus_6605_vector = Vector("bus_6605")
    val x7955_vector = Vector("x7955")
    val x7954_x8278_addr_vector = Vector("x7954_x8278_addr")
    val x7197_x7913_data_vector = Vector("x7197_x7913_data")
    val bus_7304_vector = Vector("bus_7304")
    val x7948_vector = Vector("x7948")
    val bus_6741_vector = Vector("bus_6741")
    val x7958_vector = Vector("x7958")
    val x7251_x7847_addr_vector = Vector("x7251_x7847_addr")
    val bus_6565_vector = Vector("bus_6565")
    val x7404_scalar = Scalar("x7404")
    val x7512_scalar = Scalar("x7512")
    val x7476_scalar = Scalar("x7476")
    val x7960_x8296_addr_vector = Vector("x7960_x8296_addr")
    val x7319_scalar = Scalar("x7319")
    val bus_7298_vector = Vector("bus_7298")
    val x7692_scalar = Scalar("x7692")
    val x7310_scalar = Scalar("x7310")
    val x7155_oc = OffChip("x7155")
    val x7958_x8290_addr_vector = Vector("x7958_x8290_addr")
    val x7317_scalar = Scalar("x7317")
    val bus_6710_vector = Vector("bus_6710")
    val bus_7230_vector = Vector("bus_7230")
    val x7245_x7484_addr_vector = Vector("x7245_x7484_addr")
    val bus_6494_vector = Vector("bus_6494")
    val x7197_x7733_addr_vector = Vector("x7197_x7733_addr")
    val bus_6386_vector = Vector("bus_6386")
    val bus_6673_vector = Vector("bus_6673")
    val x7197_x7661_data_vector = Vector("x7197_x7661_data")
    val bus_7340_vector = Vector("bus_7340")
    val x7959_x8293_addr_vector = Vector("x7959_x8293_addr")
    val bus_6457_vector = Vector("bus_6457")
    val x7251_x7487_addr_vector = Vector("x7251_x7487_addr")
    val x7656_scalar = Scalar("x7656")
    val x7957_vector = Vector("x7957")
    val x7197_x7877_addr_vector = Vector("x7197_x7877_addr")
    val bus_6782_vector = Vector("bus_6782")
    val x7197_x7697_data_vector = Vector("x7197_x7697_data")
    val x7315_scalar = Scalar("x7315")
    val x7197_x7625_data_vector = Vector("x7197_x7625_data")
    val bus_7327_vector = Vector("bus_7327")
    val bus_6525_vector = Vector("bus_6525")
    val bus_6317_vector = Vector("bus_6317")
    val x7961_vector = Vector("x7961")
    val x7584_scalar = Scalar("x7584")
    val bus_6597_vector = Vector("bus_6597")
    val bus_6601_vector = Vector("bus_6601")
    val bus_7303_vector = Vector("bus_7303")
    val bus_6709_vector = Vector("bus_6709")
    val bus_6638_vector = Vector("bus_6638")
    val x7197_x7481_data_vector = Vector("x7197_x7481_data")
    val x7197_x7553_data_vector = Vector("x7197_x7553_data")
    val x7251_x7739_addr_vector = Vector("x7251_x7739_addr")
    val bus_7326_vector = Vector("bus_7326")
    val bus_6425_vector = Vector("bus_6425")
    val bus_6309_vector = Vector("bus_6309")
    val bus_6781_vector = Vector("bus_6781")
    val x7949_x8263_addr_vector = Vector("x7949_x8263_addr")
    val x7908_scalar = Scalar("x7908")
    val bus_6313_vector = Vector("bus_6313")
    val x7959_vector = Vector("x7959")
    val x7197_x7841_addr_vector = Vector("x7197_x7841_addr")
    val x7248_x8302_addr_vector = Vector("x7248_x8302_addr")
    val bus_6530_vector = Vector("bus_6530")
    val x7313_scalar = Scalar("x7313")
    val bus_6422_vector = Vector("bus_6422")
    val x7728_scalar = Scalar("x7728")
    val bus_6389_vector = Vector("bus_6389")
    val bus_6353_vector = Vector("bus_6353")
    val bus_6493_vector = Vector("bus_6493")
    val bus_6817_vector = Vector("bus_6817")
    val x7309_scalar = Scalar("x7309")
    val bus_6417_vector = Vector("bus_6417")
    val bus_6849_vector = Vector("bus_6849")
    val x7197_x7373_data_vector = Vector("x7197_x7373_data")
    val x7316_scalar = Scalar("x7316")
    val x7245_x7880_addr_vector = Vector("x7245_x7880_addr")
    val x7955_x8281_addr_vector = Vector("x7955_x8281_addr")
    val x7197_x7877_data_vector = Vector("x7197_x7877_data")
    val x7248_vector = Vector("x7248")
    val x7245_x7808_addr_vector = Vector("x7245_x7808_addr")
    val x7947_vector = Vector("x7947")
    val bus_6349_vector = Vector("bus_6349")
    val bus_6278_scalar = Scalar("bus_6278")
    val bus_6497_vector = Vector("bus_6497")
    val x7946_x8254_addr_vector = Vector("x7946_x8254_addr")
    val x7197_x7841_data_vector = Vector("x7197_x7841_data")
    val x7956_vector = Vector("x7956")
    val x7548_scalar = Scalar("x7548")
    val bus_6253_scalar = Scalar("bus_6253")
    val x7251_x7667_addr_vector = Vector("x7251_x7667_addr")
    val x7197_x7517_addr_vector = Vector("x7197_x7517_addr")
    val x7251_x7379_addr_vector = Vector("x7251_x7379_addr")
    val x7951_vector = Vector("x7951")
    val bus_6256_scalar = Scalar("bus_6256")
    val x7311_scalar = Scalar("x7311")
    val bus_6280_scalar = Scalar("bus_6280")
    val x7800_scalar = Scalar("x7800")
    val bus_6785_vector = Vector("bus_6785")
    val bus_6677_vector = Vector("bus_6677")
    val bus_6705_vector = Vector("bus_6705")
    val bus_6854_vector = Vector("bus_6854")
    val bus_7332_vector = Vector("bus_7332")
    val x7836_scalar = Scalar("x7836")
    val x7957_x8287_addr_vector = Vector("x7957_x8287_addr")
    val x7251_x7415_addr_vector = Vector("x7251_x7415_addr")
    val x7251_x7523_addr_vector = Vector("x7251_x7523_addr")
    val x7307_scalar = Scalar("x7307")
    val x7764_scalar = Scalar("x7764")
    val bus_7316_vector = Vector("bus_7316")
    val bus_6314_vector = Vector("bus_6314")
    val x7245_x7412_addr_vector = Vector("x7245_x7412_addr")
    val x7251_x7595_addr_vector = Vector("x7251_x7595_addr")
    val bus_7314_vector = Vector("bus_7314")
    val bus_6569_vector = Vector("bus_6569")
    val x7245_x7664_addr_vector = Vector("x7245_x7664_addr")
    val x7197_x7625_addr_vector = Vector("x7197_x7625_addr")
    val x7953_x8275_addr_vector = Vector("x7953_x8275_addr")
    val x7951_x8269_addr_vector = Vector("x7951_x8269_addr")
    val x7251_x7451_addr_vector = Vector("x7251_x7451_addr")
    val x7251_x7703_addr_vector = Vector("x7251_x7703_addr")
    val bus_7238_vector = Vector("bus_7238")
    val x7245_x7520_addr_vector = Vector("x7245_x7520_addr")
    val bus_7321_vector = Vector("bus_7321")
    val x7440_scalar = Scalar("x7440")
    val x7245_x7448_addr_vector = Vector("x7245_x7448_addr")
    val x7197_x7769_addr_vector = Vector("x7197_x7769_addr")
    val bus_6529_vector = Vector("bus_6529")
    val x7197_x7805_addr_vector = Vector("x7197_x7805_addr")
    val x7961_x8299_addr_vector = Vector("x7961_x8299_addr")
    val x7960_vector = Vector("x7960")
    val x7197_x7589_addr_vector = Vector("x7197_x7589_addr")
    val x7952_x8272_addr_vector = Vector("x7952_x8272_addr")
    val x7956_x8284_addr_vector = Vector("x7956_x8284_addr")
    val x7306_scalar = Scalar("x7306")
    val x7251_x7559_addr_vector = Vector("x7251_x7559_addr")
    val x7245_x7592_addr_vector = Vector("x7245_x7592_addr")
    val x7245_x7844_addr_vector = Vector("x7245_x7844_addr")
    val x7197_x7481_addr_vector = Vector("x7197_x7481_addr")
    val x7318_scalar = Scalar("x7318")
    val x7156_oc = OffChip("x7156")
    val x7950_x8266_addr_vector = Vector("x7950_x8266_addr")
    val bus_6533_vector = Vector("bus_6533")
    val bus_7309_vector = Vector("bus_7309")
    val bus_6853_vector = Vector("bus_6853")
    val x7197_x7697_addr_vector = Vector("x7197_x7697_addr")
    val x7954_vector = Vector("x7954")
    val x7245_x7628_addr_vector = Vector("x7245_x7628_addr")
    val x7949_vector = Vector("x7949")
    val x7197_x7445_addr_vector = Vector("x7197_x7445_addr")
    val x7251_x7775_addr_vector = Vector("x7251_x7775_addr")
    val x8363_vector = Vector("x8363")
    val x7953_vector = Vector("x7953")
    val bus_6641_vector = Vector("bus_6641")
    val bus_6461_vector = Vector("bus_6461")
    val x7948_x8260_addr_vector = Vector("x7948_x8260_addr")
    val x7277_mc = MemoryController(TileLoad, x7155_oc)
    val x7223_mc = MemoryController(TileLoad, x7155_oc)
    val x8430_mc = MemoryController(TileStore, x7156_oc)
    val x8434 = Sequential(name = "x8434", parent=top, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x8434_unitcc = CounterChain(name = "x8434_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7244 = MetaPipeline(name = "x7244", parent=x8434, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7200 = CounterChain(name = "x7200", ctr1)
      var stage: List[Stage] = Nil
    }
    val x7219 = StreamController(name = "x7219", parent=x7244, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7219_unitcc = CounterChain(name = "x7219_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7219_0 = StreamPipeline(name = "x7219_0", parent=x7219, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6255 = CU.temp
      val tr6252 = CU.temp
      val tr6250 = CU.temp
      val tr6246 = CU.temp
      val tr6244 = CU.temp
      val x7200 = CounterChain.copy(x7244, "x7200")
      val x7219_unitcc = CounterChain.copy(x7219, "x7219_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7200(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6244)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6244), Const("64i")), op=FixMod, results=List(CU.temp(stage(2), tr6246)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6244), CU.temp(stage(2), tr6246)), op=FixSub, results=List(CU.scalarOut(stage(3), x7223_mc.ofs)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6246), Const("96i")), op=FixAdd, results=List(CU.temp(stage(4), tr6250)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr6250), Const("64i")), op=FixMod, results=List(CU.temp(stage(5), tr6252)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6250), CU.temp(stage(5), tr6252)), op=FixSub, results=List(CU.scalarOut(stage(6), bus_6253_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr6252), Const("0i")), op=FixNeq, results=List(CU.temp(stage(7), tr6255)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr6255), Const("64i"), Const("0i")), op=Mux, results=List(CU.scalarOut(stage(8), bus_6256_scalar)))
    }
    val x7219_1 = StreamPipeline(name = "x7219_1", parent=x7219, deps=List(x7219_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7219_unitcc = CounterChain.copy(x7219, "x7219_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_6253_scalar), CU.scalarIn(stage(0), bus_6256_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(1), x7223_mc.len)))
    }
    val x8397 = Sequential(name = "x8397", parent=x8434, deps=List(x7244)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("5i").out, Const("1i").out) // Counter
      val x7247 = CounterChain(name = "x7247", ctr5)
      var stage: List[Stage] = Nil
    }
    val x8362 = MetaPipeline(name = "x8362", parent=x8397, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("1536i").out, Const("20i").out) // Counter
      val x7250 = CounterChain(name = "x7250", ctr7)
      var stage: List[Stage] = Nil
    }
    val x7298 = MetaPipeline(name = "x7298", parent=x8362, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7254 = CounterChain(name = "x7254", ctr13)
      var stage: List[Stage] = Nil
    }
    val x7273 = StreamController(name = "x7273", parent=x7298, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7273_unitcc = CounterChain(name = "x7273_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7273_0 = StreamPipeline(name = "x7273_0", parent=x7273, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6277 = CU.temp
      val tr6275 = CU.temp
      val tr6271 = CU.temp
      val tr6269 = CU.temp
      val tr6267 = CU.temp
      val x7254 = CounterChain.copy(x7298, "x7254")
      val x7273_unitcc = CounterChain.copy(x7273, "x7273_unitcc")
      val x7250 = CounterChain.copy(x8362, "x7250")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7250(0)), CU.ctr(stage(0), x7254(0))), op=FixAdd, results=List(CU.temp(stage(1), tr6267)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6267), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr6269)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6269), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr6271)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6269), CU.temp(stage(3), tr6271)), op=FixSub, results=List(CU.scalarOut(stage(4), x7277_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr6271), Const("96i")), op=FixAdd, results=List(CU.temp(stage(5), tr6275)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6275), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr6277)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr6275), CU.temp(stage(6), tr6277)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_6278_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr6277), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_6280_scalar)))
    }
    val x7273_1 = StreamPipeline(name = "x7273_1", parent=x7273, deps=List(x7273_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6281 = CU.temp
      val x7273_unitcc = CounterChain.copy(x7273, "x7273_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_6280_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr6281)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_6278_scalar), CU.temp(stage(1), tr6281)), op=FixAdd, results=List(CU.scalarOut(stage(2), x7277_mc.len)))
    }
    val x8360 = MetaPipeline(name = "x8360", parent=x8362, deps=List(x7298)) { implicit CU =>
      val stage0 = CU.emptyStage
      //val ctr11 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      //val ctr12 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      //val x7303 = CounterChain(name = "x7303", ctr11, ctr12)
      val ctr9 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7300 = CounterChain(name = "x7300", ctr9)
      var stage: List[Stage] = Nil
    }
    val x7403 = MetaPipeline(name = "x7403", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7352 = CounterChain(name = "x7352", ctr15)
      var stage: List[Stage] = Nil
    }
    val x7390 = StreamController(name = "x7390", parent=x7403, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr16 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7370 = CounterChain(name = "x7370", ctr16)
      var stage: List[Stage] = Nil
    }
    val x7390_0 = StreamPipeline(name = "x7390_0", parent=x7390, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6305 = CU.temp
      val tr6301 = CU.temp
      val x7352 = CounterChain.copy(x7403, "x7352")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7370 = CounterChain.copy(x7390, "x7370")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7352(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6301)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6301), CU.ctr(stage(1), x7370(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7373_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7352(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6305)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6305), CU.ctr(stage(3), x7370(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7376_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6309_vector)))
    }
    val x7390_1 = StreamPipeline(name = "x7390_1", parent=x7390, deps=List(x7390_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7370 = CounterChain.copy(x7390, "x7370")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6309_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6309_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6309_fifo.load, CU.ctr(stage(0), x7370(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7379_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6313_vector)))
    }
    val x7390_2 = StreamPipeline(name = "x7390_2", parent=x7390, deps=List(x7390_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7373 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7373_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7373_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7373_addr_fifo.load), op=Bypass, results=List(x7197_x7373.readAddr))
      Stage(stage(2), operands=List(x7197_x7373.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7373_data_vector)))
    }
    val x7390_3 = StreamPipeline(name = "x7390_3", parent=x7390, deps=List(x7390_0, x7390_1, x7390_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6299 = CU.temp
      val tr6298 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7376 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7376_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7376_addr_vector)
      val bus_6313_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6313_vector)
      val x7197_x7373_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7373_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7376))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6298)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6298), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7376.writeAddr, CU.temp(stage(2), tr6299)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7376_addr_fifo.load), op=Bypass, results=List(x7245_x7376.readAddr))
      Stage(stage(2), operands=List(bus_6313_fifo.load, x7197_x7373_data_fifo.load, x7245_x7376.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6314_vector)))
    }
    val x7390_4 = StreamPipeline(name = "x7390_4", parent=x7390, deps=List(x7390_1, x7390_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6315 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7379 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7379_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7379_addr_vector)
      val bus_6314_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6314_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7379_addr_fifo.load), op=Bypass, results=List(x7251_x7379.readAddr))
      Stage(stage(2), operands=List(x7251_x7379.load, bus_6314_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6315)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6315), CU.temp(stage(2), tr6315)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6317_vector)))
    }
    val x7390_5 = StreamPipeline(name = "x7390_5", parent=x7390, deps=List(x7390_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7370 = CounterChain.copy(x7390, "x7370")
      val bus_6317_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6317_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6317_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6319) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6319), op=Bypass, results=List(CU.scalarOut(stage(2), x7368_scalar)))
    }
    val x7401_0 = UnitPipeline(name = "x7401_0", parent=x7403, deps=List(x7390)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6327 = CU.temp
      val ar6323 = CU.accum(init = Const("100000i"))
      val ar6326 = CU.accum(init = Const("0i"))
      val x7352 = CounterChain.copy(x7403, "x7352")
      val x7401_unitcc = CounterChain(name = "x7401_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6323), CU.scalarIn(stage(0), x7368_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6323)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6323), CU.scalarIn(stage(1), x7368_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6327)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6327), CU.ctr(stage(2), x7352(0)), CU.accum(stage(3), ar6326)), op=Mux, results=List(CU.scalarOut(stage(3), x7304_scalar), CU.accum(stage(3), ar6326)))
    }
    val x7439 = MetaPipeline(name = "x7439", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr18 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7353 = CounterChain(name = "x7353", ctr18)
      var stage: List[Stage] = Nil
    }
    val x7426 = StreamController(name = "x7426", parent=x7439, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr19 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7406 = CounterChain(name = "x7406", ctr19)
      var stage: List[Stage] = Nil
    }
    val x7426_0 = StreamPipeline(name = "x7426_0", parent=x7426, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6341 = CU.temp
      val tr6337 = CU.temp
      val x7353 = CounterChain.copy(x7439, "x7353")
      val x7406 = CounterChain.copy(x7426, "x7406")
      val x7300 = CounterChain.copy(x8360, "x7300")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7353(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6337)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6337), CU.ctr(stage(1), x7406(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7409_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7353(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6341)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6341), CU.ctr(stage(3), x7406(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7412_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6345_vector)))
    }
    val x7426_1 = StreamPipeline(name = "x7426_1", parent=x7426, deps=List(x7426_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7406 = CounterChain.copy(x7426, "x7406")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6345_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6345_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6345_fifo.load, CU.ctr(stage(0), x7406(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7415_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6349_vector)))
    }
    val x7426_2 = StreamPipeline(name = "x7426_2", parent=x7426, deps=List(x7426_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7409 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7409_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7409_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7409_addr_fifo.load), op=Bypass, results=List(x7197_x7409.readAddr))
      Stage(stage(2), operands=List(x7197_x7409.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7409_data_vector)))
    }
    val x7426_3 = StreamPipeline(name = "x7426_3", parent=x7426, deps=List(x7426_0, x7426_1, x7426_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6335 = CU.temp
      val tr6334 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7412 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7412_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7412_addr_vector)
      val bus_6349_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6349_vector)
      val x7197_x7409_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7409_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7412))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6334)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6334), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7412.writeAddr, CU.temp(stage(2), tr6335)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7412_addr_fifo.load), op=Bypass, results=List(x7245_x7412.readAddr))
      Stage(stage(2), operands=List(bus_6349_fifo.load, x7197_x7409_data_fifo.load, x7245_x7412.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6350_vector)))
    }
    val x7426_4 = StreamPipeline(name = "x7426_4", parent=x7426, deps=List(x7426_1, x7426_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6351 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7415 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7415_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7415_addr_vector)
      val bus_6350_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6350_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7415_addr_fifo.load), op=Bypass, results=List(x7251_x7415.readAddr))
      Stage(stage(2), operands=List(x7251_x7415.load, bus_6350_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6351)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6351), CU.temp(stage(2), tr6351)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6353_vector)))
    }
    val x7426_5 = StreamPipeline(name = "x7426_5", parent=x7426, deps=List(x7426_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7406 = CounterChain.copy(x7426, "x7406")
      val bus_6353_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6353_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6353_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6355) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6355), op=Bypass, results=List(CU.scalarOut(stage(2), x7404_scalar)))
    }
    val x7437_0 = UnitPipeline(name = "x7437_0", parent=x7439, deps=List(x7426)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6363 = CU.temp
      val ar6359 = CU.accum(init = Const("100000i"))
      val ar6362 = CU.accum(init = Const("0i"))
      val x7353 = CounterChain.copy(x7439, "x7353")
      val x7437_unitcc = CounterChain(name = "x7437_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6359), CU.scalarIn(stage(0), x7404_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6359)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6359), CU.scalarIn(stage(1), x7404_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6363)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6363), CU.ctr(stage(2), x7353(0)), CU.accum(stage(3), ar6362)), op=Mux, results=List(CU.scalarOut(stage(3), x7305_scalar), CU.accum(stage(3), ar6362)))
    }
    val x7475 = MetaPipeline(name = "x7475", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr21 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7354 = CounterChain(name = "x7354", ctr21)
      var stage: List[Stage] = Nil
    }
    val x7462 = StreamController(name = "x7462", parent=x7475, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr22 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7442 = CounterChain(name = "x7442", ctr22)
      var stage: List[Stage] = Nil
    }
    val x7462_0 = StreamPipeline(name = "x7462_0", parent=x7462, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6377 = CU.temp
      val tr6373 = CU.temp
      val x7442 = CounterChain.copy(x7462, "x7442")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7354 = CounterChain.copy(x7475, "x7354")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7354(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6373)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6373), CU.ctr(stage(1), x7442(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7445_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7354(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6377)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6377), CU.ctr(stage(3), x7442(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7448_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6381_vector)))
    }
    val x7462_1 = StreamPipeline(name = "x7462_1", parent=x7462, deps=List(x7462_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7442 = CounterChain.copy(x7462, "x7442")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6381_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6381_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6381_fifo.load, CU.ctr(stage(0), x7442(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7451_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6385_vector)))
    }
    val x7462_2 = StreamPipeline(name = "x7462_2", parent=x7462, deps=List(x7462_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7445 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7445_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7445_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7445_addr_fifo.load), op=Bypass, results=List(x7197_x7445.readAddr))
      Stage(stage(2), operands=List(x7197_x7445.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7445_data_vector)))
    }
    val x7462_3 = StreamPipeline(name = "x7462_3", parent=x7462, deps=List(x7462_0, x7462_1, x7462_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6367 = CU.temp
      val tr6366 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7448 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7448_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7448_addr_vector)
      val bus_6385_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6385_vector)
      val x7197_x7445_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7445_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7448))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6366)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6366), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7448.writeAddr, CU.temp(stage(2), tr6367)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7448_addr_fifo.load), op=Bypass, results=List(x7245_x7448.readAddr))
      Stage(stage(2), operands=List(bus_6385_fifo.load, x7197_x7445_data_fifo.load, x7245_x7448.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6386_vector)))
    }
    val x7462_4 = StreamPipeline(name = "x7462_4", parent=x7462, deps=List(x7462_1, x7462_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6387 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7451 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7451_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7451_addr_vector)
      val bus_6386_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6386_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7451_addr_fifo.load), op=Bypass, results=List(x7251_x7451.readAddr))
      Stage(stage(2), operands=List(x7251_x7451.load, bus_6386_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6387)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6387), CU.temp(stage(2), tr6387)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6389_vector)))
    }
    val x7462_5 = StreamPipeline(name = "x7462_5", parent=x7462, deps=List(x7462_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7442 = CounterChain.copy(x7462, "x7442")
      val bus_6389_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6389_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6389_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6391) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6391), op=Bypass, results=List(CU.scalarOut(stage(2), x7440_scalar)))
    }
    val x7473_0 = UnitPipeline(name = "x7473_0", parent=x7475, deps=List(x7462)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6399 = CU.temp
      val ar6395 = CU.accum(init = Const("100000i"))
      val ar6398 = CU.accum(init = Const("0i"))
      val x7473_unitcc = CounterChain(name = "x7473_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7354 = CounterChain.copy(x7475, "x7354")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6395), CU.scalarIn(stage(0), x7440_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6395)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6395), CU.scalarIn(stage(1), x7440_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6399)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6399), CU.ctr(stage(2), x7354(0)), CU.accum(stage(3), ar6398)), op=Mux, results=List(CU.scalarOut(stage(3), x7306_scalar), CU.accum(stage(3), ar6398)))
    }
    val x7511 = MetaPipeline(name = "x7511", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr24 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7355 = CounterChain(name = "x7355", ctr24)
      var stage: List[Stage] = Nil
    }
    val x7498 = StreamController(name = "x7498", parent=x7511, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7478 = CounterChain(name = "x7478", ctr25)
      var stage: List[Stage] = Nil
    }
    val x7498_0 = StreamPipeline(name = "x7498_0", parent=x7498, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6413 = CU.temp
      val tr6409 = CU.temp
      val x7355 = CounterChain.copy(x7511, "x7355")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7478 = CounterChain.copy(x7498, "x7478")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7355(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6409)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6409), CU.ctr(stage(1), x7478(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7481_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7355(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6413)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6413), CU.ctr(stage(3), x7478(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7484_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6417_vector)))
    }
    val x7498_1 = StreamPipeline(name = "x7498_1", parent=x7498, deps=List(x7498_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7247 = CounterChain.copy(x8397, "x7247")
      val x7478 = CounterChain.copy(x7498, "x7478")
      val bus_6417_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6417_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6417_fifo.load, CU.ctr(stage(0), x7478(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7487_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6421_vector)))
    }
    val x7498_2 = StreamPipeline(name = "x7498_2", parent=x7498, deps=List(x7498_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7481 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7481_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7481_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7481_addr_fifo.load), op=Bypass, results=List(x7197_x7481.readAddr))
      Stage(stage(2), operands=List(x7197_x7481.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7481_data_vector)))
    }
    val x7498_3 = StreamPipeline(name = "x7498_3", parent=x7498, deps=List(x7498_0, x7498_1, x7498_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6405 = CU.temp
      val tr6404 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7484 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7484_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7484_addr_vector)
      val bus_6421_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6421_vector)
      val x7197_x7481_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7481_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7484))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6404)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6404), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7484.writeAddr, CU.temp(stage(2), tr6405)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7484_addr_fifo.load), op=Bypass, results=List(x7245_x7484.readAddr))
      Stage(stage(2), operands=List(bus_6421_fifo.load, x7197_x7481_data_fifo.load, x7245_x7484.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6422_vector)))
    }
    val x7498_4 = StreamPipeline(name = "x7498_4", parent=x7498, deps=List(x7498_1, x7498_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6423 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7487 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7487_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7487_addr_vector)
      val bus_6422_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6422_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7487_addr_fifo.load), op=Bypass, results=List(x7251_x7487.readAddr))
      Stage(stage(2), operands=List(x7251_x7487.load, bus_6422_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6423)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6423), CU.temp(stage(2), tr6423)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6425_vector)))
    }
    val x7498_5 = StreamPipeline(name = "x7498_5", parent=x7498, deps=List(x7498_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7478 = CounterChain.copy(x7498, "x7478")
      val bus_6425_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6425_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6425_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6427) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6427), op=Bypass, results=List(CU.scalarOut(stage(2), x7476_scalar)))
    }
    val x7509_0 = UnitPipeline(name = "x7509_0", parent=x7511, deps=List(x7498)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6435 = CU.temp
      val ar6431 = CU.accum(init = Const("100000i"))
      val ar6434 = CU.accum(init = Const("0i"))
      val x7355 = CounterChain.copy(x7511, "x7355")
      val x7509_unitcc = CounterChain(name = "x7509_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6431), CU.scalarIn(stage(0), x7476_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6431)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6431), CU.scalarIn(stage(1), x7476_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6435)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6435), CU.ctr(stage(2), x7355(0)), CU.accum(stage(3), ar6434)), op=Mux, results=List(CU.scalarOut(stage(3), x7307_scalar), CU.accum(stage(3), ar6434)))
    }
    val x7547 = MetaPipeline(name = "x7547", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr27 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7356 = CounterChain(name = "x7356", ctr27)
      var stage: List[Stage] = Nil
    }
    val x7534 = StreamController(name = "x7534", parent=x7547, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr28 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7514 = CounterChain(name = "x7514", ctr28)
      var stage: List[Stage] = Nil
    }
    val x7534_0 = StreamPipeline(name = "x7534_0", parent=x7534, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6449 = CU.temp
      val tr6445 = CU.temp
      val x7356 = CounterChain.copy(x7547, "x7356")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7514 = CounterChain.copy(x7534, "x7514")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7356(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6445)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6445), CU.ctr(stage(1), x7514(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7517_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7356(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6449)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6449), CU.ctr(stage(3), x7514(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7520_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6453_vector)))
    }
    val x7534_1 = StreamPipeline(name = "x7534_1", parent=x7534, deps=List(x7534_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7514 = CounterChain.copy(x7534, "x7514")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6453_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6453_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6453_fifo.load, CU.ctr(stage(0), x7514(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7523_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6457_vector)))
    }
    val x7534_2 = StreamPipeline(name = "x7534_2", parent=x7534, deps=List(x7534_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7517 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7517_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7517_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7517_addr_fifo.load), op=Bypass, results=List(x7197_x7517.readAddr))
      Stage(stage(2), operands=List(x7197_x7517.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7517_data_vector)))
    }
    val x7534_3 = StreamPipeline(name = "x7534_3", parent=x7534, deps=List(x7534_0, x7534_1, x7534_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6439 = CU.temp
      val tr6438 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7520 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7520_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7520_addr_vector)
      val bus_6457_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6457_vector)
      val x7197_x7517_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7517_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7520))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6438)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6438), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7520.writeAddr, CU.temp(stage(2), tr6439)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7520_addr_fifo.load), op=Bypass, results=List(x7245_x7520.readAddr))
      Stage(stage(2), operands=List(bus_6457_fifo.load, x7197_x7517_data_fifo.load, x7245_x7520.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6458_vector)))
    }
    val x7534_4 = StreamPipeline(name = "x7534_4", parent=x7534, deps=List(x7534_1, x7534_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6459 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7523 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7523_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7523_addr_vector)
      val bus_6458_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6458_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7523_addr_fifo.load), op=Bypass, results=List(x7251_x7523.readAddr))
      Stage(stage(2), operands=List(x7251_x7523.load, bus_6458_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6459)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6459), CU.temp(stage(2), tr6459)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6461_vector)))
    }
    val x7534_5 = StreamPipeline(name = "x7534_5", parent=x7534, deps=List(x7534_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7514 = CounterChain.copy(x7534, "x7514")
      val bus_6461_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6461_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6461_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6463) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6463), op=Bypass, results=List(CU.scalarOut(stage(2), x7512_scalar)))
    }
    val x7545_0 = UnitPipeline(name = "x7545_0", parent=x7547, deps=List(x7534)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6471 = CU.temp
      val ar6467 = CU.accum(init = Const("100000i"))
      val ar6470 = CU.accum(init = Const("0i"))
      val x7356 = CounterChain.copy(x7547, "x7356")
      val x7545_unitcc = CounterChain(name = "x7545_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6467), CU.scalarIn(stage(0), x7512_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6467)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6467), CU.scalarIn(stage(1), x7512_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6471)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6471), CU.ctr(stage(2), x7356(0)), CU.accum(stage(3), ar6470)), op=Mux, results=List(CU.scalarOut(stage(3), x7308_scalar), CU.accum(stage(3), ar6470)))
    }
    val x7583 = MetaPipeline(name = "x7583", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr30 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7357 = CounterChain(name = "x7357", ctr30)
      var stage: List[Stage] = Nil
    }
    val x7570 = StreamController(name = "x7570", parent=x7583, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr31 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7550 = CounterChain(name = "x7550", ctr31)
      var stage: List[Stage] = Nil
    }
    val x7570_0 = StreamPipeline(name = "x7570_0", parent=x7570, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6485 = CU.temp
      val tr6481 = CU.temp
      val x7357 = CounterChain.copy(x7583, "x7357")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7550 = CounterChain.copy(x7570, "x7550")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7357(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6481)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6481), CU.ctr(stage(1), x7550(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7553_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7357(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6485)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6485), CU.ctr(stage(3), x7550(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7556_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6489_vector)))
    }
    val x7570_1 = StreamPipeline(name = "x7570_1", parent=x7570, deps=List(x7570_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7550 = CounterChain.copy(x7570, "x7550")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6489_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6489_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6489_fifo.load, CU.ctr(stage(0), x7550(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7559_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6493_vector)))
    }
    val x7570_2 = StreamPipeline(name = "x7570_2", parent=x7570, deps=List(x7570_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7553 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7553_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7553_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7553_addr_fifo.load), op=Bypass, results=List(x7197_x7553.readAddr))
      Stage(stage(2), operands=List(x7197_x7553.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7553_data_vector)))
    }
    val x7570_3 = StreamPipeline(name = "x7570_3", parent=x7570, deps=List(x7570_0, x7570_1, x7570_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6475 = CU.temp
      val tr6474 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7556 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7556_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7556_addr_vector)
      val bus_6493_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6493_vector)
      val x7197_x7553_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7553_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7556))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6474)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6474), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7556.writeAddr, CU.temp(stage(2), tr6475)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7556_addr_fifo.load), op=Bypass, results=List(x7245_x7556.readAddr))
      Stage(stage(2), operands=List(bus_6493_fifo.load, x7197_x7553_data_fifo.load, x7245_x7556.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6494_vector)))
    }
    val x7570_4 = StreamPipeline(name = "x7570_4", parent=x7570, deps=List(x7570_1, x7570_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6495 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7559 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7559_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7559_addr_vector)
      val bus_6494_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6494_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7559_addr_fifo.load), op=Bypass, results=List(x7251_x7559.readAddr))
      Stage(stage(2), operands=List(x7251_x7559.load, bus_6494_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6495)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6495), CU.temp(stage(2), tr6495)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6497_vector)))
    }
    val x7570_5 = StreamPipeline(name = "x7570_5", parent=x7570, deps=List(x7570_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7550 = CounterChain.copy(x7570, "x7550")
      val bus_6497_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6497_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6497_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6499) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6499), op=Bypass, results=List(CU.scalarOut(stage(2), x7548_scalar)))
    }
    val x7581_0 = UnitPipeline(name = "x7581_0", parent=x7583, deps=List(x7570)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6507 = CU.temp
      val ar6503 = CU.accum(init = Const("100000i"))
      val ar6506 = CU.accum(init = Const("0i"))
      val x7357 = CounterChain.copy(x7583, "x7357")
      val x7581_unitcc = CounterChain(name = "x7581_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6503), CU.scalarIn(stage(0), x7548_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6503)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6503), CU.scalarIn(stage(1), x7548_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6507)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6507), CU.ctr(stage(2), x7357(0)), CU.accum(stage(3), ar6506)), op=Mux, results=List(CU.scalarOut(stage(3), x7309_scalar), CU.accum(stage(3), ar6506)))
    }
    val x7619 = MetaPipeline(name = "x7619", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr33 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7358 = CounterChain(name = "x7358", ctr33)
      var stage: List[Stage] = Nil
    }
    val x7606 = StreamController(name = "x7606", parent=x7619, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr34 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7586 = CounterChain(name = "x7586", ctr34)
      var stage: List[Stage] = Nil
    }
    val x7606_0 = StreamPipeline(name = "x7606_0", parent=x7606, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6521 = CU.temp
      val tr6517 = CU.temp
      val x7586 = CounterChain.copy(x7606, "x7586")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7358 = CounterChain.copy(x7619, "x7358")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7358(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6517)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6517), CU.ctr(stage(1), x7586(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7589_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7358(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6521)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6521), CU.ctr(stage(3), x7586(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7592_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6525_vector)))
    }
    val x7606_1 = StreamPipeline(name = "x7606_1", parent=x7606, deps=List(x7606_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7586 = CounterChain.copy(x7606, "x7586")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6525_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6525_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6525_fifo.load, CU.ctr(stage(0), x7586(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7595_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6529_vector)))
    }
    val x7606_2 = StreamPipeline(name = "x7606_2", parent=x7606, deps=List(x7606_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7589 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7589_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7589_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7589_addr_fifo.load), op=Bypass, results=List(x7197_x7589.readAddr))
      Stage(stage(2), operands=List(x7197_x7589.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7589_data_vector)))
    }
    val x7606_3 = StreamPipeline(name = "x7606_3", parent=x7606, deps=List(x7606_0, x7606_1, x7606_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6511 = CU.temp
      val tr6510 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7592 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7592_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7592_addr_vector)
      val bus_6529_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6529_vector)
      val x7197_x7589_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7589_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7592))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6510)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6510), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7592.writeAddr, CU.temp(stage(2), tr6511)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7592_addr_fifo.load), op=Bypass, results=List(x7245_x7592.readAddr))
      Stage(stage(2), operands=List(bus_6529_fifo.load, x7197_x7589_data_fifo.load, x7245_x7592.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6530_vector)))
    }
    val x7606_4 = StreamPipeline(name = "x7606_4", parent=x7606, deps=List(x7606_1, x7606_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6531 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7595 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7595_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7595_addr_vector)
      val bus_6530_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6530_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7595_addr_fifo.load), op=Bypass, results=List(x7251_x7595.readAddr))
      Stage(stage(2), operands=List(x7251_x7595.load, bus_6530_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6531)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6531), CU.temp(stage(2), tr6531)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6533_vector)))
    }
    val x7606_5 = StreamPipeline(name = "x7606_5", parent=x7606, deps=List(x7606_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7586 = CounterChain.copy(x7606, "x7586")
      val bus_6533_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6533_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6533_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6535) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6535), op=Bypass, results=List(CU.scalarOut(stage(2), x7584_scalar)))
    }
    val x7617_0 = UnitPipeline(name = "x7617_0", parent=x7619, deps=List(x7606)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6543 = CU.temp
      val ar6539 = CU.accum(init = Const("100000i"))
      val ar6542 = CU.accum(init = Const("0i"))
      val x7617_unitcc = CounterChain(name = "x7617_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7358 = CounterChain.copy(x7619, "x7358")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6539), CU.scalarIn(stage(0), x7584_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6539)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6539), CU.scalarIn(stage(1), x7584_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6543)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6543), CU.ctr(stage(2), x7358(0)), CU.accum(stage(3), ar6542)), op=Mux, results=List(CU.scalarOut(stage(3), x7310_scalar), CU.accum(stage(3), ar6542)))
    }
    val x7655 = MetaPipeline(name = "x7655", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr36 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7359 = CounterChain(name = "x7359", ctr36)
      var stage: List[Stage] = Nil
    }
    val x7642 = StreamController(name = "x7642", parent=x7655, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr37 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7622 = CounterChain(name = "x7622", ctr37)
      var stage: List[Stage] = Nil
    }
    val x7642_0 = StreamPipeline(name = "x7642_0", parent=x7642, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6557 = CU.temp
      val tr6553 = CU.temp
      val x7622 = CounterChain.copy(x7642, "x7622")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7359 = CounterChain.copy(x7655, "x7359")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7359(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6553)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6553), CU.ctr(stage(1), x7622(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7625_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7359(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6557)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6557), CU.ctr(stage(3), x7622(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7628_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6561_vector)))
    }
    val x7642_1 = StreamPipeline(name = "x7642_1", parent=x7642, deps=List(x7642_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7622 = CounterChain.copy(x7642, "x7622")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6561_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6561_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6561_fifo.load, CU.ctr(stage(0), x7622(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7631_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6565_vector)))
    }
    val x7642_2 = StreamPipeline(name = "x7642_2", parent=x7642, deps=List(x7642_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7625 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7625_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7625_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7625_addr_fifo.load), op=Bypass, results=List(x7197_x7625.readAddr))
      Stage(stage(2), operands=List(x7197_x7625.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7625_data_vector)))
    }
    val x7642_3 = StreamPipeline(name = "x7642_3", parent=x7642, deps=List(x7642_0, x7642_1, x7642_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6549 = CU.temp
      val tr6548 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7628 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7628_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7628_addr_vector)
      val bus_6565_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6565_vector)
      val x7197_x7625_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7625_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7628))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6548)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6548), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7628.writeAddr, CU.temp(stage(2), tr6549)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7628_addr_fifo.load), op=Bypass, results=List(x7245_x7628.readAddr))
      Stage(stage(2), operands=List(bus_6565_fifo.load, x7197_x7625_data_fifo.load, x7245_x7628.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6566_vector)))
    }
    val x7642_4 = StreamPipeline(name = "x7642_4", parent=x7642, deps=List(x7642_1, x7642_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6567 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7631 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7631_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7631_addr_vector)
      val bus_6566_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6566_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7631_addr_fifo.load), op=Bypass, results=List(x7251_x7631.readAddr))
      Stage(stage(2), operands=List(x7251_x7631.load, bus_6566_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6567)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6567), CU.temp(stage(2), tr6567)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6569_vector)))
    }
    val x7642_5 = StreamPipeline(name = "x7642_5", parent=x7642, deps=List(x7642_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7622 = CounterChain.copy(x7642, "x7622")
      val bus_6569_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6569_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6569_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6571) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6571), op=Bypass, results=List(CU.scalarOut(stage(2), x7620_scalar)))
    }
    val x7653_0 = UnitPipeline(name = "x7653_0", parent=x7655, deps=List(x7642)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6579 = CU.temp
      val ar6575 = CU.accum(init = Const("100000i"))
      val ar6578 = CU.accum(init = Const("0i"))
      val x7359 = CounterChain.copy(x7655, "x7359")
      val x7653_unitcc = CounterChain(name = "x7653_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6575), CU.scalarIn(stage(0), x7620_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6575)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6575), CU.scalarIn(stage(1), x7620_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6579)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6579), CU.ctr(stage(2), x7359(0)), CU.accum(stage(3), ar6578)), op=Mux, results=List(CU.scalarOut(stage(3), x7311_scalar), CU.accum(stage(3), ar6578)))
    }
    val x7691 = MetaPipeline(name = "x7691", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr39 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7360 = CounterChain(name = "x7360", ctr39)
      var stage: List[Stage] = Nil
    }
    val x7678 = StreamController(name = "x7678", parent=x7691, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr40 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7658 = CounterChain(name = "x7658", ctr40)
      var stage: List[Stage] = Nil
    }
    val x7678_0 = StreamPipeline(name = "x7678_0", parent=x7678, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6593 = CU.temp
      val tr6589 = CU.temp
      val x7658 = CounterChain.copy(x7678, "x7658")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7360 = CounterChain.copy(x7691, "x7360")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7360(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6589)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6589), CU.ctr(stage(1), x7658(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7661_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7360(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6593)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6593), CU.ctr(stage(3), x7658(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7664_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6597_vector)))
    }
    val x7678_1 = StreamPipeline(name = "x7678_1", parent=x7678, deps=List(x7678_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7658 = CounterChain.copy(x7678, "x7658")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6597_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6597_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6597_fifo.load, CU.ctr(stage(0), x7658(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7667_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6601_vector)))
    }
    val x7678_2 = StreamPipeline(name = "x7678_2", parent=x7678, deps=List(x7678_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7661 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7661_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7661_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7661_addr_fifo.load), op=Bypass, results=List(x7197_x7661.readAddr))
      Stage(stage(2), operands=List(x7197_x7661.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7661_data_vector)))
    }
    val x7678_3 = StreamPipeline(name = "x7678_3", parent=x7678, deps=List(x7678_0, x7678_1, x7678_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6587 = CU.temp
      val tr6586 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7664 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7664_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7664_addr_vector)
      val bus_6601_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6601_vector)
      val x7197_x7661_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7661_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7664))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6586)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6586), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7664.writeAddr, CU.temp(stage(2), tr6587)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7664_addr_fifo.load), op=Bypass, results=List(x7245_x7664.readAddr))
      Stage(stage(2), operands=List(bus_6601_fifo.load, x7197_x7661_data_fifo.load, x7245_x7664.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6602_vector)))
    }
    val x7678_4 = StreamPipeline(name = "x7678_4", parent=x7678, deps=List(x7678_1, x7678_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6603 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7667 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7667_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7667_addr_vector)
      val bus_6602_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6602_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7667_addr_fifo.load), op=Bypass, results=List(x7251_x7667.readAddr))
      Stage(stage(2), operands=List(x7251_x7667.load, bus_6602_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6603)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6603), CU.temp(stage(2), tr6603)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6605_vector)))
    }
    val x7678_5 = StreamPipeline(name = "x7678_5", parent=x7678, deps=List(x7678_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7658 = CounterChain.copy(x7678, "x7658")
      val bus_6605_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6605_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6605_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6607) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6607), op=Bypass, results=List(CU.scalarOut(stage(2), x7656_scalar)))
    }
    val x7689_0 = UnitPipeline(name = "x7689_0", parent=x7691, deps=List(x7678)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6615 = CU.temp
      val ar6611 = CU.accum(init = Const("100000i"))
      val ar6614 = CU.accum(init = Const("0i"))
      val x7689_unitcc = CounterChain(name = "x7689_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7360 = CounterChain.copy(x7691, "x7360")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6611), CU.scalarIn(stage(0), x7656_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6611)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6611), CU.scalarIn(stage(1), x7656_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6615)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6615), CU.ctr(stage(2), x7360(0)), CU.accum(stage(3), ar6614)), op=Mux, results=List(CU.scalarOut(stage(3), x7312_scalar), CU.accum(stage(3), ar6614)))
    }
    val x7727 = MetaPipeline(name = "x7727", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr42 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7361 = CounterChain(name = "x7361", ctr42)
      var stage: List[Stage] = Nil
    }
    val x7714 = StreamController(name = "x7714", parent=x7727, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr43 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7694 = CounterChain(name = "x7694", ctr43)
      var stage: List[Stage] = Nil
    }
    val x7714_0 = StreamPipeline(name = "x7714_0", parent=x7714, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6629 = CU.temp
      val tr6625 = CU.temp
      val x7361 = CounterChain.copy(x7727, "x7361")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7694 = CounterChain.copy(x7714, "x7694")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7361(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6625)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6625), CU.ctr(stage(1), x7694(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7697_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7361(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6629)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6629), CU.ctr(stage(3), x7694(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7700_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6633_vector)))
    }
    val x7714_1 = StreamPipeline(name = "x7714_1", parent=x7714, deps=List(x7714_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7247 = CounterChain.copy(x8397, "x7247")
      val x7694 = CounterChain.copy(x7714, "x7694")
      val bus_6633_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6633_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6633_fifo.load, CU.ctr(stage(0), x7694(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7703_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6637_vector)))
    }
    val x7714_2 = StreamPipeline(name = "x7714_2", parent=x7714, deps=List(x7714_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7697 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7697_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7697_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7697_addr_fifo.load), op=Bypass, results=List(x7197_x7697.readAddr))
      Stage(stage(2), operands=List(x7197_x7697.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7697_data_vector)))
    }
    val x7714_3 = StreamPipeline(name = "x7714_3", parent=x7714, deps=List(x7714_0, x7714_1, x7714_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6621 = CU.temp
      val tr6620 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7700 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7700_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7700_addr_vector)
      val bus_6637_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6637_vector)
      val x7197_x7697_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7697_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7700))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6620)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6620), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7700.writeAddr, CU.temp(stage(2), tr6621)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7700_addr_fifo.load), op=Bypass, results=List(x7245_x7700.readAddr))
      Stage(stage(2), operands=List(bus_6637_fifo.load, x7197_x7697_data_fifo.load, x7245_x7700.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6638_vector)))
    }
    val x7714_4 = StreamPipeline(name = "x7714_4", parent=x7714, deps=List(x7714_1, x7714_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6639 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7703 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7703_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7703_addr_vector)
      val bus_6638_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6638_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7703_addr_fifo.load), op=Bypass, results=List(x7251_x7703.readAddr))
      Stage(stage(2), operands=List(x7251_x7703.load, bus_6638_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6639)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6639), CU.temp(stage(2), tr6639)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6641_vector)))
    }
    val x7714_5 = StreamPipeline(name = "x7714_5", parent=x7714, deps=List(x7714_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7694 = CounterChain.copy(x7714, "x7694")
      val bus_6641_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6641_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6641_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6643) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6643), op=Bypass, results=List(CU.scalarOut(stage(2), x7692_scalar)))
    }
    val x7725_0 = UnitPipeline(name = "x7725_0", parent=x7727, deps=List(x7714)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6651 = CU.temp
      val ar6647 = CU.accum(init = Const("100000i"))
      val ar6650 = CU.accum(init = Const("0i"))
      val x7361 = CounterChain.copy(x7727, "x7361")
      val x7725_unitcc = CounterChain(name = "x7725_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6647), CU.scalarIn(stage(0), x7692_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6647)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6647), CU.scalarIn(stage(1), x7692_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6651)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6651), CU.ctr(stage(2), x7361(0)), CU.accum(stage(3), ar6650)), op=Mux, results=List(CU.scalarOut(stage(3), x7313_scalar), CU.accum(stage(3), ar6650)))
    }
    val x7763 = MetaPipeline(name = "x7763", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr45 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7362 = CounterChain(name = "x7362", ctr45)
      var stage: List[Stage] = Nil
    }
    val x7750 = StreamController(name = "x7750", parent=x7763, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr46 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7730 = CounterChain(name = "x7730", ctr46)
      var stage: List[Stage] = Nil
    }
    val x7750_0 = StreamPipeline(name = "x7750_0", parent=x7750, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6665 = CU.temp
      val tr6661 = CU.temp
      val x7362 = CounterChain.copy(x7763, "x7362")
      val x7730 = CounterChain.copy(x7750, "x7730")
      val x7300 = CounterChain.copy(x8360, "x7300")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7362(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6661)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6661), CU.ctr(stage(1), x7730(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7733_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7362(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6665)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6665), CU.ctr(stage(3), x7730(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7736_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6669_vector)))
    }
    val x7750_1 = StreamPipeline(name = "x7750_1", parent=x7750, deps=List(x7750_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7730 = CounterChain.copy(x7750, "x7730")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6669_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6669_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6669_fifo.load, CU.ctr(stage(0), x7730(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7739_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6673_vector)))
    }
    val x7750_2 = StreamPipeline(name = "x7750_2", parent=x7750, deps=List(x7750_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7733 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7733_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7733_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7733_addr_fifo.load), op=Bypass, results=List(x7197_x7733.readAddr))
      Stage(stage(2), operands=List(x7197_x7733.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7733_data_vector)))
    }
    val x7750_3 = StreamPipeline(name = "x7750_3", parent=x7750, deps=List(x7750_0, x7750_1, x7750_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6659 = CU.temp
      val tr6658 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7736 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7736_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7736_addr_vector)
      val bus_6673_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6673_vector)
      val x7197_x7733_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7733_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7736))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6658)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6658), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7736.writeAddr, CU.temp(stage(2), tr6659)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7736_addr_fifo.load), op=Bypass, results=List(x7245_x7736.readAddr))
      Stage(stage(2), operands=List(bus_6673_fifo.load, x7197_x7733_data_fifo.load, x7245_x7736.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6674_vector)))
    }
    val x7750_4 = StreamPipeline(name = "x7750_4", parent=x7750, deps=List(x7750_1, x7750_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6675 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7739 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7739_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7739_addr_vector)
      val bus_6674_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6674_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7739_addr_fifo.load), op=Bypass, results=List(x7251_x7739.readAddr))
      Stage(stage(2), operands=List(x7251_x7739.load, bus_6674_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6675)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6675), CU.temp(stage(2), tr6675)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6677_vector)))
    }
    val x7750_5 = StreamPipeline(name = "x7750_5", parent=x7750, deps=List(x7750_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7730 = CounterChain.copy(x7750, "x7730")
      val bus_6677_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6677_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6677_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6679) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6679), op=Bypass, results=List(CU.scalarOut(stage(2), x7728_scalar)))
    }
    val x7761_0 = UnitPipeline(name = "x7761_0", parent=x7763, deps=List(x7750)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6687 = CU.temp
      val ar6683 = CU.accum(init = Const("100000i"))
      val ar6686 = CU.accum(init = Const("0i"))
      val x7362 = CounterChain.copy(x7763, "x7362")
      val x7761_unitcc = CounterChain(name = "x7761_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6683), CU.scalarIn(stage(0), x7728_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6683)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6683), CU.scalarIn(stage(1), x7728_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6687)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6687), CU.ctr(stage(2), x7362(0)), CU.accum(stage(3), ar6686)), op=Mux, results=List(CU.scalarOut(stage(3), x7314_scalar), CU.accum(stage(3), ar6686)))
    }
    val x7799 = MetaPipeline(name = "x7799", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr48 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7363 = CounterChain(name = "x7363", ctr48)
      var stage: List[Stage] = Nil
    }
    val x7786 = StreamController(name = "x7786", parent=x7799, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr49 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7766 = CounterChain(name = "x7766", ctr49)
      var stage: List[Stage] = Nil
    }
    val x7786_0 = StreamPipeline(name = "x7786_0", parent=x7786, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6701 = CU.temp
      val tr6697 = CU.temp
      val x7363 = CounterChain.copy(x7799, "x7363")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7766 = CounterChain.copy(x7786, "x7766")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7363(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6697)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6697), CU.ctr(stage(1), x7766(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7769_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7363(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6701)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6701), CU.ctr(stage(3), x7766(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7772_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6705_vector)))
    }
    val x7786_1 = StreamPipeline(name = "x7786_1", parent=x7786, deps=List(x7786_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7766 = CounterChain.copy(x7786, "x7766")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6705_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6705_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6705_fifo.load, CU.ctr(stage(0), x7766(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7775_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6709_vector)))
    }
    val x7786_2 = StreamPipeline(name = "x7786_2", parent=x7786, deps=List(x7786_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7769 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7769_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7769_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7769_addr_fifo.load), op=Bypass, results=List(x7197_x7769.readAddr))
      Stage(stage(2), operands=List(x7197_x7769.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7769_data_vector)))
    }
    val x7786_3 = StreamPipeline(name = "x7786_3", parent=x7786, deps=List(x7786_0, x7786_1, x7786_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6693 = CU.temp
      val tr6692 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7772 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7772_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7772_addr_vector)
      val bus_6709_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6709_vector)
      val x7197_x7769_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7769_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7772))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6692)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6692), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7772.writeAddr, CU.temp(stage(2), tr6693)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7772_addr_fifo.load), op=Bypass, results=List(x7245_x7772.readAddr))
      Stage(stage(2), operands=List(bus_6709_fifo.load, x7197_x7769_data_fifo.load, x7245_x7772.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6710_vector)))
    }
    val x7786_4 = StreamPipeline(name = "x7786_4", parent=x7786, deps=List(x7786_1, x7786_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6711 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7775 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7775_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7775_addr_vector)
      val bus_6710_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6710_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7775_addr_fifo.load), op=Bypass, results=List(x7251_x7775.readAddr))
      Stage(stage(2), operands=List(x7251_x7775.load, bus_6710_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6711)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6711), CU.temp(stage(2), tr6711)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6713_vector)))
    }
    val x7786_5 = StreamPipeline(name = "x7786_5", parent=x7786, deps=List(x7786_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7766 = CounterChain.copy(x7786, "x7766")
      val bus_6713_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6713_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6713_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6715) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6715), op=Bypass, results=List(CU.scalarOut(stage(2), x7764_scalar)))
    }
    val x7797_0 = UnitPipeline(name = "x7797_0", parent=x7799, deps=List(x7786)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6723 = CU.temp
      val ar6719 = CU.accum(init = Const("100000i"))
      val ar6722 = CU.accum(init = Const("0i"))
      val x7797_unitcc = CounterChain(name = "x7797_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7363 = CounterChain.copy(x7799, "x7363")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6719), CU.scalarIn(stage(0), x7764_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6719)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6719), CU.scalarIn(stage(1), x7764_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6723)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6723), CU.ctr(stage(2), x7363(0)), CU.accum(stage(3), ar6722)), op=Mux, results=List(CU.scalarOut(stage(3), x7315_scalar), CU.accum(stage(3), ar6722)))
    }
    val x7835 = MetaPipeline(name = "x7835", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr51 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7364 = CounterChain(name = "x7364", ctr51)
      var stage: List[Stage] = Nil
    }
    val x7822 = StreamController(name = "x7822", parent=x7835, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr52 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7802 = CounterChain(name = "x7802", ctr52)
      var stage: List[Stage] = Nil
    }
    val x7822_0 = StreamPipeline(name = "x7822_0", parent=x7822, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6737 = CU.temp
      val tr6733 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7802 = CounterChain.copy(x7822, "x7802")
      val x7364 = CounterChain.copy(x7835, "x7364")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7364(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6733)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6733), CU.ctr(stage(1), x7802(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7805_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7364(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6737)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6737), CU.ctr(stage(3), x7802(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7808_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6741_vector)))
    }
    val x7822_1 = StreamPipeline(name = "x7822_1", parent=x7822, deps=List(x7822_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7802 = CounterChain.copy(x7822, "x7802")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6741_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6741_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6741_fifo.load, CU.ctr(stage(0), x7802(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7811_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6745_vector)))
    }
    val x7822_2 = StreamPipeline(name = "x7822_2", parent=x7822, deps=List(x7822_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7805 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7805_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7805_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7805_addr_fifo.load), op=Bypass, results=List(x7197_x7805.readAddr))
      Stage(stage(2), operands=List(x7197_x7805.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7805_data_vector)))
    }
    val x7822_3 = StreamPipeline(name = "x7822_3", parent=x7822, deps=List(x7822_0, x7822_1, x7822_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6729 = CU.temp
      val tr6728 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7808 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7808_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7808_addr_vector)
      val bus_6745_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6745_vector)
      val x7197_x7805_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7805_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7808))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6728)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6728), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7808.writeAddr, CU.temp(stage(2), tr6729)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7808_addr_fifo.load), op=Bypass, results=List(x7245_x7808.readAddr))
      Stage(stage(2), operands=List(bus_6745_fifo.load, x7197_x7805_data_fifo.load, x7245_x7808.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6746_vector)))
    }
    val x7822_4 = StreamPipeline(name = "x7822_4", parent=x7822, deps=List(x7822_1, x7822_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6747 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7811 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7811_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7811_addr_vector)
      val bus_6746_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6746_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7811_addr_fifo.load), op=Bypass, results=List(x7251_x7811.readAddr))
      Stage(stage(2), operands=List(x7251_x7811.load, bus_6746_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6747)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6747), CU.temp(stage(2), tr6747)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6749_vector)))
    }
    val x7822_5 = StreamPipeline(name = "x7822_5", parent=x7822, deps=List(x7822_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7802 = CounterChain.copy(x7822, "x7802")
      val bus_6749_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6749_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6749_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6751) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6751), op=Bypass, results=List(CU.scalarOut(stage(2), x7800_scalar)))
    }
    val x7833_0 = UnitPipeline(name = "x7833_0", parent=x7835, deps=List(x7822)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6759 = CU.temp
      val ar6755 = CU.accum(init = Const("100000i"))
      val ar6758 = CU.accum(init = Const("0i"))
      val x7364 = CounterChain.copy(x7835, "x7364")
      val x7833_unitcc = CounterChain(name = "x7833_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6755), CU.scalarIn(stage(0), x7800_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6755)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6755), CU.scalarIn(stage(1), x7800_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6759)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6759), CU.ctr(stage(2), x7364(0)), CU.accum(stage(3), ar6758)), op=Mux, results=List(CU.scalarOut(stage(3), x7316_scalar), CU.accum(stage(3), ar6758)))
    }
    val x7871 = MetaPipeline(name = "x7871", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr54 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7365 = CounterChain(name = "x7365", ctr54)
      var stage: List[Stage] = Nil
    }
    val x7858 = StreamController(name = "x7858", parent=x7871, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr55 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7838 = CounterChain(name = "x7838", ctr55)
      var stage: List[Stage] = Nil
    }
    val x7858_0 = StreamPipeline(name = "x7858_0", parent=x7858, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6773 = CU.temp
      val tr6769 = CU.temp
      val x7365 = CounterChain.copy(x7871, "x7365")
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7838 = CounterChain.copy(x7858, "x7838")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7365(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6769)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6769), CU.ctr(stage(1), x7838(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7841_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7365(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6773)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6773), CU.ctr(stage(3), x7838(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7844_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6777_vector)))
    }
    val x7858_1 = StreamPipeline(name = "x7858_1", parent=x7858, deps=List(x7858_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7838 = CounterChain.copy(x7858, "x7838")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6777_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6777_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6777_fifo.load, CU.ctr(stage(0), x7838(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7847_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6781_vector)))
    }
    val x7858_2 = StreamPipeline(name = "x7858_2", parent=x7858, deps=List(x7858_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7841 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7841_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7841_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7841_addr_fifo.load), op=Bypass, results=List(x7197_x7841.readAddr))
      Stage(stage(2), operands=List(x7197_x7841.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7841_data_vector)))
    }
    val x7858_3 = StreamPipeline(name = "x7858_3", parent=x7858, deps=List(x7858_0, x7858_1, x7858_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6765 = CU.temp
      val tr6764 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7844 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7844_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7844_addr_vector)
      val bus_6781_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6781_vector)
      val x7197_x7841_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7841_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7844))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6764)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6764), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7844.writeAddr, CU.temp(stage(2), tr6765)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7844_addr_fifo.load), op=Bypass, results=List(x7245_x7844.readAddr))
      Stage(stage(2), operands=List(bus_6781_fifo.load, x7197_x7841_data_fifo.load, x7245_x7844.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6782_vector)))
    }
    val x7858_4 = StreamPipeline(name = "x7858_4", parent=x7858, deps=List(x7858_1, x7858_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6783 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7847 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7847_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7847_addr_vector)
      val bus_6782_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6782_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7847_addr_fifo.load), op=Bypass, results=List(x7251_x7847.readAddr))
      Stage(stage(2), operands=List(x7251_x7847.load, bus_6782_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6783)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6783), CU.temp(stage(2), tr6783)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6785_vector)))
    }
    val x7858_5 = StreamPipeline(name = "x7858_5", parent=x7858, deps=List(x7858_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7838 = CounterChain.copy(x7858, "x7838")
      val bus_6785_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6785_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6785_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6787) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6787), op=Bypass, results=List(CU.scalarOut(stage(2), x7836_scalar)))
    }
    val x7869_0 = UnitPipeline(name = "x7869_0", parent=x7871, deps=List(x7858)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6795 = CU.temp
      val ar6791 = CU.accum(init = Const("100000i"))
      val ar6794 = CU.accum(init = Const("0i"))
      val x7365 = CounterChain.copy(x7871, "x7365")
      val x7869_unitcc = CounterChain(name = "x7869_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6791), CU.scalarIn(stage(0), x7836_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6791)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6791), CU.scalarIn(stage(1), x7836_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6795)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6795), CU.ctr(stage(2), x7365(0)), CU.accum(stage(3), ar6794)), op=Mux, results=List(CU.scalarOut(stage(3), x7317_scalar), CU.accum(stage(3), ar6794)))
    }
    val x7907 = MetaPipeline(name = "x7907", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr57 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7366 = CounterChain(name = "x7366", ctr57)
      var stage: List[Stage] = Nil
    }
    val x7894 = StreamController(name = "x7894", parent=x7907, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr58 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7874 = CounterChain(name = "x7874", ctr58)
      var stage: List[Stage] = Nil
    }
    val x7894_0 = StreamPipeline(name = "x7894_0", parent=x7894, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6809 = CU.temp
      val tr6805 = CU.temp
      val x7366 = CounterChain.copy(x7907, "x7366")
      val x7874 = CounterChain.copy(x7894, "x7874")
      val x7300 = CounterChain.copy(x8360, "x7300")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7366(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6805)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6805), CU.ctr(stage(1), x7874(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7877_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7366(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6809)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6809), CU.ctr(stage(3), x7874(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7880_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6813_vector)))
    }
    val x7894_1 = StreamPipeline(name = "x7894_1", parent=x7894, deps=List(x7894_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7874 = CounterChain.copy(x7894, "x7874")
      val x7247 = CounterChain.copy(x8397, "x7247")
      val bus_6813_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6813_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6813_fifo.load, CU.ctr(stage(0), x7874(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7883_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6817_vector)))
    }
    val x7894_2 = StreamPipeline(name = "x7894_2", parent=x7894, deps=List(x7894_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7877 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7877_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7877_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7877_addr_fifo.load), op=Bypass, results=List(x7197_x7877.readAddr))
      Stage(stage(2), operands=List(x7197_x7877.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7877_data_vector)))
    }
    val x7894_3 = StreamPipeline(name = "x7894_3", parent=x7894, deps=List(x7894_0, x7894_1, x7894_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6799 = CU.temp
      val tr6798 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7880 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7880_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7880_addr_vector)
      val bus_6817_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6817_vector)
      val x7197_x7877_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7877_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7880))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6798)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6798), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7880.writeAddr, CU.temp(stage(2), tr6799)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7880_addr_fifo.load), op=Bypass, results=List(x7245_x7880.readAddr))
      Stage(stage(2), operands=List(bus_6817_fifo.load, x7197_x7877_data_fifo.load, x7245_x7880.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6818_vector)))
    }
    val x7894_4 = StreamPipeline(name = "x7894_4", parent=x7894, deps=List(x7894_1, x7894_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6819 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7883 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7883_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7883_addr_vector)
      val bus_6818_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6818_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7883_addr_fifo.load), op=Bypass, results=List(x7251_x7883.readAddr))
      Stage(stage(2), operands=List(x7251_x7883.load, bus_6818_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6819)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6819), CU.temp(stage(2), tr6819)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6821_vector)))
    }
    val x7894_5 = StreamPipeline(name = "x7894_5", parent=x7894, deps=List(x7894_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7874 = CounterChain.copy(x7894, "x7874")
      val bus_6821_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6821_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6821_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6823) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6823), op=Bypass, results=List(CU.scalarOut(stage(2), x7872_scalar)))
    }
    val x7905_0 = UnitPipeline(name = "x7905_0", parent=x7907, deps=List(x7894)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6831 = CU.temp
      val ar6827 = CU.accum(init = Const("100000i"))
      val ar6830 = CU.accum(init = Const("0i"))
      val x7905_unitcc = CounterChain(name = "x7905_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7366 = CounterChain.copy(x7907, "x7366")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6827), CU.scalarIn(stage(0), x7872_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6827)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6827), CU.scalarIn(stage(1), x7872_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6831)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6831), CU.ctr(stage(2), x7366(0)), CU.accum(stage(3), ar6830)), op=Mux, results=List(CU.scalarOut(stage(3), x7318_scalar), CU.accum(stage(3), ar6830)))
    }
    val x7943 = MetaPipeline(name = "x7943", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr60 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x7367 = CounterChain(name = "x7367", ctr60)
      var stage: List[Stage] = Nil
    }
    val x7930 = StreamController(name = "x7930", parent=x7943, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr61 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7910 = CounterChain(name = "x7910", ctr61)
      var stage: List[Stage] = Nil
    }
    val x7930_0 = StreamPipeline(name = "x7930_0", parent=x7930, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6845 = CU.temp
      val tr6841 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7367 = CounterChain.copy(x7943, "x7367")
      val x7910 = CounterChain.copy(x7930, "x7910")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7367(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6841)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6841), CU.ctr(stage(1), x7910(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x7197_x7913_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7367(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr6845)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6845), CU.ctr(stage(3), x7910(0))), op=FixAdd, results=List(CU.vecOut(stage(4), x7245_x7916_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7300(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_6849_vector)))
    }
    val x7930_1 = StreamPipeline(name = "x7930_1", parent=x7930, deps=List(x7930_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7247 = CounterChain.copy(x8397, "x7247")
      val x7910 = CounterChain.copy(x7930, "x7910")
      val bus_6849_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6849_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6849_fifo.load, CU.ctr(stage(0), x7910(0))), op=FixAdd, results=List(CU.vecOut(stage(1), x7251_x7919_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7247(0)), Const("0i")), op=FixEql, results=List(CU.vecOut(stage(2), bus_6853_vector)))
    }
    val x7930_2 = StreamPipeline(name = "x7930_2", parent=x7930, deps=List(x7930_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7197_x7913 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x7223_mc.vdata)
      val x7197_x7913_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7913_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7197_x7913_addr_fifo.load), op=Bypass, results=List(x7197_x7913.readAddr))
      Stage(stage(2), operands=List(x7197_x7913.load), op=Bypass, results=List(CU.vecOut(stage(2), x7197_x7913_data_vector)))
    }
    val x7930_3 = StreamPipeline(name = "x7930_3", parent=x7930, deps=List(x7930_0, x7930_1, x7930_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6835 = CU.temp
      val tr6834 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val x7245_x7916 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      val x7245_x7916_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7245_x7916_addr_vector)
      val bus_6853_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6853_vector)
      val x7197_x7913_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7197_x7913_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x7916))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6834)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6834), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x7916.writeAddr, CU.temp(stage(2), tr6835)))
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x7245_x7916_addr_fifo.load), op=Bypass, results=List(x7245_x7916.readAddr))
      Stage(stage(2), operands=List(bus_6853_fifo.load, x7197_x7913_data_fifo.load, x7245_x7916.load), op=Mux, results=List(CU.vecOut(stage(2), bus_6854_vector)))
    }
    val x7930_4 = StreamPipeline(name = "x7930_4", parent=x7930, deps=List(x7930_1, x7930_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6855 = CU.temp
      val x7303 = CounterChain.copy("x8358", "x7303")
      val x7251_x7919 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      val x7251_x7919_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7251_x7919_addr_vector)
      val bus_6854_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6854_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7251_x7919_addr_fifo.load), op=Bypass, results=List(x7251_x7919.readAddr))
      Stage(stage(2), operands=List(x7251_x7919.load, bus_6854_fifo.load), op=FltSub, results=List(CU.temp(stage(2), tr6855)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6855), CU.temp(stage(2), tr6855)), op=FltMul, results=List(CU.vecOut(stage(3), bus_6857_vector)))
    }
    val x7930_5 = StreamPipeline(name = "x7930_5", parent=x7930, deps=List(x7930_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7910 = CounterChain.copy(x7930, "x7910")
      val bus_6857_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6857_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_6857_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr6859) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6859), op=Bypass, results=List(CU.scalarOut(stage(2), x7908_scalar)))
    }
    val x7941_0 = UnitPipeline(name = "x7941_0", parent=x7943, deps=List(x7930)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6867 = CU.temp
      val ar6863 = CU.accum(init = Const("100000i"))
      val ar6866 = CU.accum(init = Const("0i"))
      val x7941_unitcc = CounterChain(name = "x7941_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x7367 = CounterChain.copy(x7943, "x7367")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar6863), CU.scalarIn(stage(0), x7908_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar6863)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar6863), CU.scalarIn(stage(1), x7908_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr6867)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6867), CU.ctr(stage(2), x7367(0)), CU.accum(stage(3), ar6866)), op=Mux, results=List(CU.scalarOut(stage(3), x7319_scalar), CU.accum(stage(3), ar6866)))
    }
    val x8024_0 = Pipeline(name = "x8024_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6881 = CU.temp
      val tr6880 = CU.temp
      val tr6878 = CU.temp
      val tr6873 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr65 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr66 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7994 = CounterChain(name = "x7994", ctr65, ctr66)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8012 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6873)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6873), CU.ctr(stage(1), x7994(1))), op=FixAdd, results=List(x7251_x8012.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7994(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6878)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6878), Const("1i"), CU.load(stage(3), x7251_x8012)), op=Mux, results=List(CU.temp(stage(4), tr6880)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7994(0)), CU.scalarIn(stage(4), x7304_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6881)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6881), CU.temp(stage(5), tr6880), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7946_vector)))
    }
    val x8039_0 = Pipeline(name = "x8039_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6900 = CU.temp
      val tr6899 = CU.temp
      val tr6897 = CU.temp
      val tr6892 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr67 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr68 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7995 = CounterChain(name = "x7995", ctr67, ctr68)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8027 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6892)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6892), CU.ctr(stage(1), x7995(1))), op=FixAdd, results=List(x7251_x8027.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7995(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6897)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6897), Const("1i"), CU.load(stage(3), x7251_x8027)), op=Mux, results=List(CU.temp(stage(4), tr6899)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7995(0)), CU.scalarIn(stage(4), x7305_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6900)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6900), CU.temp(stage(5), tr6899), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7947_vector)))
    }
    val x8054_0 = Pipeline(name = "x8054_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6919 = CU.temp
      val tr6918 = CU.temp
      val tr6916 = CU.temp
      val tr6911 = CU.temp
      val ctr69 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr70 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7996 = CounterChain(name = "x7996", ctr69, ctr70)
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8042 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6911)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6911), CU.ctr(stage(1), x7996(1))), op=FixAdd, results=List(x7251_x8042.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7996(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6916)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6916), Const("1i"), CU.load(stage(3), x7251_x8042)), op=Mux, results=List(CU.temp(stage(4), tr6918)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7996(0)), CU.scalarIn(stage(4), x7306_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6919)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6919), CU.temp(stage(5), tr6918), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7948_vector)))
    }
    val x8069_0 = Pipeline(name = "x8069_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6938 = CU.temp
      val tr6937 = CU.temp
      val tr6935 = CU.temp
      val tr6930 = CU.temp
      val ctr71 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr72 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7997 = CounterChain(name = "x7997", ctr71, ctr72)
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8057 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6930)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6930), CU.ctr(stage(1), x7997(1))), op=FixAdd, results=List(x7251_x8057.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7997(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6935)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6935), Const("1i"), CU.load(stage(3), x7251_x8057)), op=Mux, results=List(CU.temp(stage(4), tr6937)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7997(0)), CU.scalarIn(stage(4), x7307_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6938)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6938), CU.temp(stage(5), tr6937), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7949_vector)))
    }
    val x8084_0 = Pipeline(name = "x8084_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6957 = CU.temp
      val tr6956 = CU.temp
      val tr6954 = CU.temp
      val tr6949 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr73 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr74 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7998 = CounterChain(name = "x7998", ctr73, ctr74)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8072 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6949)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6949), CU.ctr(stage(1), x7998(1))), op=FixAdd, results=List(x7251_x8072.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7998(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6954)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6954), Const("1i"), CU.load(stage(3), x7251_x8072)), op=Mux, results=List(CU.temp(stage(4), tr6956)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7998(0)), CU.scalarIn(stage(4), x7308_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6957)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6957), CU.temp(stage(5), tr6956), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7950_vector)))
    }
    val x8099_0 = Pipeline(name = "x8099_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6976 = CU.temp
      val tr6975 = CU.temp
      val tr6973 = CU.temp
      val tr6968 = CU.temp
      val ctr75 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr76 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x7999 = CounterChain(name = "x7999", ctr75, ctr76)
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8087 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6968)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6968), CU.ctr(stage(1), x7999(1))), op=FixAdd, results=List(x7251_x8087.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7999(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6973)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6973), Const("1i"), CU.load(stage(3), x7251_x8087)), op=Mux, results=List(CU.temp(stage(4), tr6975)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7999(0)), CU.scalarIn(stage(4), x7309_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6976)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6976), CU.temp(stage(5), tr6975), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7951_vector)))
    }
    val x8114_0 = Pipeline(name = "x8114_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6995 = CU.temp
      val tr6994 = CU.temp
      val tr6992 = CU.temp
      val tr6987 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr77 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr78 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8000 = CounterChain(name = "x8000", ctr77, ctr78)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8102 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr6987)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6987), CU.ctr(stage(1), x8000(1))), op=FixAdd, results=List(x7251_x8102.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8000(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr6992)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6992), Const("1i"), CU.load(stage(3), x7251_x8102)), op=Mux, results=List(CU.temp(stage(4), tr6994)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8000(0)), CU.scalarIn(stage(4), x7310_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr6995)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6995), CU.temp(stage(5), tr6994), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7952_vector)))
    }
    val x8129_0 = Pipeline(name = "x8129_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7014 = CU.temp
      val tr7013 = CU.temp
      val tr7011 = CU.temp
      val tr7006 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr79 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr80 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8001 = CounterChain(name = "x8001", ctr79, ctr80)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8117 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7006)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7006), CU.ctr(stage(1), x8001(1))), op=FixAdd, results=List(x7251_x8117.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8001(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7011)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7011), Const("1i"), CU.load(stage(3), x7251_x8117)), op=Mux, results=List(CU.temp(stage(4), tr7013)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8001(0)), CU.scalarIn(stage(4), x7311_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7014)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7014), CU.temp(stage(5), tr7013), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7953_vector)))
    }
    val x8144_0 = Pipeline(name = "x8144_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7033 = CU.temp
      val tr7032 = CU.temp
      val tr7030 = CU.temp
      val tr7025 = CU.temp
      val ctr81 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr82 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8002 = CounterChain(name = "x8002", ctr81, ctr82)
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8132 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7025)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7025), CU.ctr(stage(1), x8002(1))), op=FixAdd, results=List(x7251_x8132.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8002(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7030)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7030), Const("1i"), CU.load(stage(3), x7251_x8132)), op=Mux, results=List(CU.temp(stage(4), tr7032)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8002(0)), CU.scalarIn(stage(4), x7312_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7033)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7033), CU.temp(stage(5), tr7032), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7954_vector)))
    }
    val x8159_0 = Pipeline(name = "x8159_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7052 = CU.temp
      val tr7051 = CU.temp
      val tr7049 = CU.temp
      val tr7044 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr83 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr84 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8003 = CounterChain(name = "x8003", ctr83, ctr84)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8147 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7044)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7044), CU.ctr(stage(1), x8003(1))), op=FixAdd, results=List(x7251_x8147.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8003(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7049)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7049), Const("1i"), CU.load(stage(3), x7251_x8147)), op=Mux, results=List(CU.temp(stage(4), tr7051)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8003(0)), CU.scalarIn(stage(4), x7313_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7052)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7052), CU.temp(stage(5), tr7051), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7955_vector)))
    }
    val x8174_0 = Pipeline(name = "x8174_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7071 = CU.temp
      val tr7070 = CU.temp
      val tr7068 = CU.temp
      val tr7063 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr85 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr86 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8004 = CounterChain(name = "x8004", ctr85, ctr86)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8162 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7063)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7063), CU.ctr(stage(1), x8004(1))), op=FixAdd, results=List(x7251_x8162.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8004(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7068)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7068), Const("1i"), CU.load(stage(3), x7251_x8162)), op=Mux, results=List(CU.temp(stage(4), tr7070)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8004(0)), CU.scalarIn(stage(4), x7314_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7071)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7071), CU.temp(stage(5), tr7070), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7956_vector)))
    }
    val x8189_0 = Pipeline(name = "x8189_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7090 = CU.temp
      val tr7089 = CU.temp
      val tr7087 = CU.temp
      val tr7082 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val ctr87 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr88 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8005 = CounterChain(name = "x8005", ctr87, ctr88)
      val x7251_x8177 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7082)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7082), CU.ctr(stage(1), x8005(1))), op=FixAdd, results=List(x7251_x8177.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8005(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7087)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7087), Const("1i"), CU.load(stage(3), x7251_x8177)), op=Mux, results=List(CU.temp(stage(4), tr7089)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8005(0)), CU.scalarIn(stage(4), x7315_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7090)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7090), CU.temp(stage(5), tr7089), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7957_vector)))
    }
    val x8204_0 = Pipeline(name = "x8204_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7109 = CU.temp
      val tr7108 = CU.temp
      val tr7106 = CU.temp
      val tr7101 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr89 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr90 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8006 = CounterChain(name = "x8006", ctr89, ctr90)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8192 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7101)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7101), CU.ctr(stage(1), x8006(1))), op=FixAdd, results=List(x7251_x8192.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8006(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7106)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7106), Const("1i"), CU.load(stage(3), x7251_x8192)), op=Mux, results=List(CU.temp(stage(4), tr7108)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8006(0)), CU.scalarIn(stage(4), x7316_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7109)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7109), CU.temp(stage(5), tr7108), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7958_vector)))
    }
    val x8219_0 = Pipeline(name = "x8219_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7128 = CU.temp
      val tr7127 = CU.temp
      val tr7125 = CU.temp
      val tr7120 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr91 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr92 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8007 = CounterChain(name = "x8007", ctr91, ctr92)
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8207 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7120)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7120), CU.ctr(stage(1), x8007(1))), op=FixAdd, results=List(x7251_x8207.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8007(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7125)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7125), Const("1i"), CU.load(stage(3), x7251_x8207)), op=Mux, results=List(CU.temp(stage(4), tr7127)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8007(0)), CU.scalarIn(stage(4), x7317_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7128)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7128), CU.temp(stage(5), tr7127), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7959_vector)))
    }
    val x8234_0 = Pipeline(name = "x8234_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7147 = CU.temp
      val tr7146 = CU.temp
      val tr7144 = CU.temp
      val tr7139 = CU.temp
      val ctr93 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr94 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8008 = CounterChain(name = "x8008", ctr93, ctr94)
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8222 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7139)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7139), CU.ctr(stage(1), x8008(1))), op=FixAdd, results=List(x7251_x8222.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8008(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7144)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7144), Const("1i"), CU.load(stage(3), x7251_x8222)), op=Mux, results=List(CU.temp(stage(4), tr7146)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8008(0)), CU.scalarIn(stage(4), x7318_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7147)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7147), CU.temp(stage(5), tr7146), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7960_vector)))
    }
    val x8249_0 = Pipeline(name = "x8249_0", parent=x8360, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7166 = CU.temp
      val tr7165 = CU.temp
      val tr7163 = CU.temp
      val tr7158 = CU.temp
      val x7300 = CounterChain.copy(x8360, "x7300")
      val ctr95 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr96 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8009 = CounterChain(name = "x8009", ctr95, ctr96)
      //val x7303 = CounterChain.copy(x8358, "x7303")
      val x7251_x8237 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x8009(0))).wtPort(x7277_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7300(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7158)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7158), CU.ctr(stage(1), x8009(1))), op=FixAdd, results=List(x7251_x8237.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8009(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr7163)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7163), Const("1i"), CU.load(stage(3), x7251_x8237)), op=Mux, results=List(CU.temp(stage(4), tr7165)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x8009(0)), CU.scalarIn(stage(4), x7319_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr7166)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr7166), CU.temp(stage(5), tr7165), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x7961_vector)))
    }
    val x8358 = StreamController(name = "x8358", parent=x8360, deps=List(x8114_0, x8084_0, x8249_0, x8204_0, x8129_0, x8069_0, x8144_0, x8174_0, x8219_0, x8159_0, x8234_0, x8054_0, x8039_0, x8099_0, x8189_0, x8024_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr97 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr98 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7303 = CounterChain(name = "x7303", ctr97, ctr98)
      var stage: List[Stage] = Nil
    }
    val x8358_0 = StreamPipeline(name = "x8358_0", parent=x8358, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7226 = CU.temp
      val tr7222 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7222)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7222), CU.ctr(stage(1), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x7946_x8254_addr_vector)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr7226)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7226), CU.ctr(stage(3), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(4), x7947_x8257_addr_vector)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(5), bus_7230_vector)))
    }
    val x8358_1 = StreamPipeline(name = "x8358_1", parent=x8358, deps=List(x8358_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7234 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7230_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7230_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7230_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7948_x8260_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7234)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7234), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7949_x8263_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7238_vector)))
    }
    val x8358_2 = StreamPipeline(name = "x8358_2", parent=x8358, deps=List(x8358_1)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7242 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7238_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7238_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7238_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7950_x8266_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7242)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7242), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7951_x8269_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7246_vector)))
    }
    val x8358_3 = StreamPipeline(name = "x8358_3", parent=x8358, deps=List(x8358_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7250 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7246_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7246_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7246_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7952_x8272_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7250)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7250), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7953_x8275_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7254_vector)))
    }
    val x8358_4 = StreamPipeline(name = "x8358_4", parent=x8358, deps=List(x8358_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7258 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7254_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7254_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7254_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7954_x8278_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7258)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7258), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7955_x8281_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7262_vector)))
    }
    val x8358_5 = StreamPipeline(name = "x8358_5", parent=x8358, deps=List(x8358_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7266 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7262_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7262_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7262_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7956_x8284_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7266)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7266), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7957_x8287_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7270_vector)))
    }
    val x8358_6 = StreamPipeline(name = "x8358_6", parent=x8358, deps=List(x8358_5)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7274 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7270_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7270_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7270_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7958_x8290_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7274)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7274), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7959_x8293_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7278_vector)))
    }
    val x8358_7 = StreamPipeline(name = "x8358_7", parent=x8358, deps=List(x8358_6)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7282 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7278_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7278_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_7278_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7960_x8296_addr_vector)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr7282)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr7282), CU.ctr(stage(2), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(3), x7961_x8299_addr_vector)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x7303(0)), Const("96i")), op=FixMul, results=List(CU.vecOut(stage(4), bus_7286_vector)))
    }
    val x8358_8 = StreamPipeline(name = "x8358_8", parent=x8358, deps=List(x8358_7)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7303 = CounterChain.copy(x8358, "x7303")
      val bus_7286_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7286_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_7286_fifo.load, CU.ctr(stage(0), x7303(1))), op=FixAdd, results=List(CU.vecOut(stage(1), x7248_x8302_addr_vector)))
    }
    val x8358_9 = StreamPipeline(name = "x8358_9", parent=x8358, deps=List(x8358_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7196 = CU.temp
      val tr7195 = CU.temp
      val tr7199 = CU.temp
      val tr7198 = CU.temp
      val x7994 = CounterChain.copy(x8024_0, "x7994")
      val x7995 = CounterChain.copy(x8039_0, "x7995")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7947_x8257 = SRAM(size = 1920, writeCtr = x7995(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7995(0))).wtPort(x7947_vector)
      val x7946_x8254 = SRAM(size = 1920, writeCtr = x7994(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7994(0))).wtPort(x7946_vector)
      val x7947_x8257_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7947_x8257_addr_vector)
      val x7946_x8254_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7946_x8254_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7947_x8257))
      Stage(stage(1), operands=List(x7995(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7198)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7198), CU.ctr(stage(1), x7995(1))), op=FixAdd, results=List(x7947_x8257.writeAddr, CU.temp(stage(2), tr7199)))
      stage = stage0 +: WAStages(2, List(x7946_x8254))
      Stage(stage(1), operands=List(x7994(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7195)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7195), CU.ctr(stage(1), x7994(1))), op=FixAdd, results=List(x7946_x8254.writeAddr, CU.temp(stage(2), tr7196)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7947_x8257_addr_fifo.load), op=Bypass, results=List(x7947_x8257.readAddr))
      Stage(stage(2), operands=List(x7946_x8254_addr_fifo.load), op=Bypass, results=List(x7946_x8254.readAddr))
      Stage(stage(3), operands=List(x7946_x8254.load, CU.load(stage(2), x7947_x8257)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7298_vector)))
    }
    val x8358_10 = StreamPipeline(name = "x8358_10", parent=x8358, deps=List(x8358_1)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7190 = CU.temp
      val tr7189 = CU.temp
      val tr7193 = CU.temp
      val tr7192 = CU.temp
      val x7996 = CounterChain.copy(x8054_0, "x7996")
      val x7997 = CounterChain.copy(x8069_0, "x7997")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7948_x8260 = SRAM(size = 1920, writeCtr = x7996(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7996(0))).wtPort(x7948_vector)
      val x7949_x8263 = SRAM(size = 1920, writeCtr = x7997(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7997(0))).wtPort(x7949_vector)
      val x7948_x8260_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7948_x8260_addr_vector)
      val x7949_x8263_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7949_x8263_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7949_x8263))
      Stage(stage(1), operands=List(x7997(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7192)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7192), CU.ctr(stage(1), x7997(1))), op=FixAdd, results=List(x7949_x8263.writeAddr, CU.temp(stage(2), tr7193)))
      stage = stage0 +: WAStages(2, List(x7948_x8260))
      Stage(stage(1), operands=List(x7996(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7189)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7189), CU.ctr(stage(1), x7996(1))), op=FixAdd, results=List(x7948_x8260.writeAddr, CU.temp(stage(2), tr7190)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7948_x8260_addr_fifo.load), op=Bypass, results=List(x7948_x8260.readAddr))
      Stage(stage(2), operands=List(x7949_x8263_addr_fifo.load), op=Bypass, results=List(x7949_x8263.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7948_x8260), x7949_x8263.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7303_vector)))
    }
    val x8358_11 = StreamPipeline(name = "x8358_11", parent=x8358, deps=List(x8358_9, x8358_10)) { implicit CU =>
      val stage0 = CU.emptyStage
      val bus_7298_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7298_vector)
      val bus_7303_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7303_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_7298_fifo.load, bus_7303_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(1), bus_7304_vector)))
    }
    val x8358_12 = StreamPipeline(name = "x8358_12", parent=x8358, deps=List(x8358_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7217 = CU.temp
      val tr7216 = CU.temp
      val tr7184 = CU.temp
      val tr7183 = CU.temp
      val x7999 = CounterChain.copy(x8099_0, "x7999")
      val x7998 = CounterChain.copy(x8084_0, "x7998")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7950_x8266 = SRAM(size = 1920, writeCtr = x7998(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7998(0))).wtPort(x7950_vector)
      val x7951_x8269 = SRAM(size = 1920, writeCtr = x7999(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x7999(0))).wtPort(x7951_vector)
      val x7950_x8266_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7950_x8266_addr_vector)
      val x7951_x8269_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7951_x8269_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7950_x8266))
      Stage(stage(1), operands=List(x7998(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7183)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7183), CU.ctr(stage(1), x7998(1))), op=FixAdd, results=List(x7950_x8266.writeAddr, CU.temp(stage(2), tr7184)))
      stage = stage0 +: WAStages(2, List(x7951_x8269))
      Stage(stage(1), operands=List(x7999(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7216)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7216), CU.ctr(stage(1), x7999(1))), op=FixAdd, results=List(x7951_x8269.writeAddr, CU.temp(stage(2), tr7217)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7950_x8266_addr_fifo.load), op=Bypass, results=List(x7950_x8266.readAddr))
      Stage(stage(2), operands=List(x7951_x8269_addr_fifo.load), op=Bypass, results=List(x7951_x8269.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7950_x8266), x7951_x8269.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7309_vector)))
    }
    val x8358_13 = StreamPipeline(name = "x8358_13", parent=x8358, deps=List(x8358_3)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7202 = CU.temp
      val tr7201 = CU.temp
      val tr7175 = CU.temp
      val tr7174 = CU.temp
      val x8000 = CounterChain.copy(x8114_0, "x8000")
      val x8001 = CounterChain.copy(x8129_0, "x8001")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7952_x8272 = SRAM(size = 1920, writeCtr = x8000(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8000(0))).wtPort(x7952_vector)
      val x7953_x8275 = SRAM(size = 1920, writeCtr = x8001(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8001(0))).wtPort(x7953_vector)
      val x7952_x8272_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7952_x8272_addr_vector)
      val x7953_x8275_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7953_x8275_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7952_x8272))
      Stage(stage(1), operands=List(x8000(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7174)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7174), CU.ctr(stage(1), x8000(1))), op=FixAdd, results=List(x7952_x8272.writeAddr, CU.temp(stage(2), tr7175)))
      stage = stage0 +: WAStages(2, List(x7953_x8275))
      Stage(stage(1), operands=List(x8001(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7201)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7201), CU.ctr(stage(1), x8001(1))), op=FixAdd, results=List(x7953_x8275.writeAddr, CU.temp(stage(2), tr7202)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7952_x8272_addr_fifo.load), op=Bypass, results=List(x7952_x8272.readAddr))
      Stage(stage(2), operands=List(x7953_x8275_addr_fifo.load), op=Bypass, results=List(x7953_x8275.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7952_x8272), x7953_x8275.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7314_vector)))
    }
    val x8358_14 = StreamPipeline(name = "x8358_14", parent=x8358, deps=List(x8358_12, x8358_13, x8358_11)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7315 = CU.temp
      val bus_7309_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7309_vector)
      val bus_7314_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7314_vector)
      val bus_7304_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7304_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_7309_fifo.load, bus_7314_fifo.load), op=FltAdd, results=List(CU.temp(stage(1), tr7315)))
      Stage(stage(2), operands=List(bus_7304_fifo.load, CU.temp(stage(1), tr7315)), op=FltAdd, results=List(CU.vecOut(stage(2), bus_7316_vector)))
    }
    val x8358_15 = StreamPipeline(name = "x8358_15", parent=x8358, deps=List(x8358_4)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7214 = CU.temp
      val tr7213 = CU.temp
      val tr7178 = CU.temp
      val tr7177 = CU.temp
      val x8002 = CounterChain.copy(x8144_0, "x8002")
      val x8003 = CounterChain.copy(x8159_0, "x8003")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7954_x8278 = SRAM(size = 1920, writeCtr = x8002(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8002(0))).wtPort(x7954_vector)
      val x7955_x8281 = SRAM(size = 1920, writeCtr = x8003(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8003(0))).wtPort(x7955_vector)
      val x7954_x8278_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7954_x8278_addr_vector)
      val x7955_x8281_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7955_x8281_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7955_x8281))
      Stage(stage(1), operands=List(x8003(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7177)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7177), CU.ctr(stage(1), x8003(1))), op=FixAdd, results=List(x7955_x8281.writeAddr, CU.temp(stage(2), tr7178)))
      stage = stage0 +: WAStages(2, List(x7954_x8278))
      Stage(stage(1), operands=List(x8002(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7213)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7213), CU.ctr(stage(1), x8002(1))), op=FixAdd, results=List(x7954_x8278.writeAddr, CU.temp(stage(2), tr7214)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7954_x8278_addr_fifo.load), op=Bypass, results=List(x7954_x8278.readAddr))
      Stage(stage(2), operands=List(x7955_x8281_addr_fifo.load), op=Bypass, results=List(x7955_x8281.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7954_x8278), x7955_x8281.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7321_vector)))
    }
    val x8358_16 = StreamPipeline(name = "x8358_16", parent=x8358, deps=List(x8358_5)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7208 = CU.temp
      val tr7207 = CU.temp
      val tr7181 = CU.temp
      val tr7180 = CU.temp
      val x8005 = CounterChain.copy(x8189_0, "x8005")
      val x8004 = CounterChain.copy(x8174_0, "x8004")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7957_x8287 = SRAM(size = 1920, writeCtr = x8005(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8005(0))).wtPort(x7957_vector)
      val x7956_x8284 = SRAM(size = 1920, writeCtr = x8004(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8004(0))).wtPort(x7956_vector)
      val x7957_x8287_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7957_x8287_addr_vector)
      val x7956_x8284_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7956_x8284_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7956_x8284))
      Stage(stage(1), operands=List(x8004(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7180)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7180), CU.ctr(stage(1), x8004(1))), op=FixAdd, results=List(x7956_x8284.writeAddr, CU.temp(stage(2), tr7181)))
      stage = stage0 +: WAStages(2, List(x7957_x8287))
      Stage(stage(1), operands=List(x8005(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7207)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7207), CU.ctr(stage(1), x8005(1))), op=FixAdd, results=List(x7957_x8287.writeAddr, CU.temp(stage(2), tr7208)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7957_x8287_addr_fifo.load), op=Bypass, results=List(x7957_x8287.readAddr))
      Stage(stage(2), operands=List(x7956_x8284_addr_fifo.load), op=Bypass, results=List(x7956_x8284.readAddr))
      Stage(stage(3), operands=List(x7956_x8284.load, CU.load(stage(2), x7957_x8287)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7326_vector)))
    }
    val x8358_17 = StreamPipeline(name = "x8358_17", parent=x8358, deps=List(x8358_15, x8358_16)) { implicit CU =>
      val stage0 = CU.emptyStage
      val bus_7321_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7321_vector)
      val bus_7326_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7326_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_7321_fifo.load, bus_7326_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(1), bus_7327_vector)))
    }
    val x8358_18 = StreamPipeline(name = "x8358_18", parent=x8358, deps=List(x8358_6)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7205 = CU.temp
      val tr7204 = CU.temp
      val tr7187 = CU.temp
      val tr7186 = CU.temp
      val x8007 = CounterChain.copy(x8219_0, "x8007")
      val x8006 = CounterChain.copy(x8204_0, "x8006")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7958_x8290 = SRAM(size = 1920, writeCtr = x8006(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8006(0))).wtPort(x7958_vector)
      val x7959_x8293 = SRAM(size = 1920, writeCtr = x8007(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8007(0))).wtPort(x7959_vector)
      val x7958_x8290_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7958_x8290_addr_vector)
      val x7959_x8293_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7959_x8293_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7959_x8293))
      Stage(stage(1), operands=List(x8007(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7186), CU.ctr(stage(1), x8007(1))), op=FixAdd, results=List(x7959_x8293.writeAddr, CU.temp(stage(2), tr7187)))
      stage = stage0 +: WAStages(2, List(x7958_x8290))
      Stage(stage(1), operands=List(x8006(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7204)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7204), CU.ctr(stage(1), x8006(1))), op=FixAdd, results=List(x7958_x8290.writeAddr, CU.temp(stage(2), tr7205)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7958_x8290_addr_fifo.load), op=Bypass, results=List(x7958_x8290.readAddr))
      Stage(stage(2), operands=List(x7959_x8293_addr_fifo.load), op=Bypass, results=List(x7959_x8293.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7958_x8290), x7959_x8293.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7332_vector)))
    }
    val x8358_19 = StreamPipeline(name = "x8358_19", parent=x8358, deps=List(x8358_7)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7220 = CU.temp
      val tr7219 = CU.temp
      val tr7211 = CU.temp
      val tr7210 = CU.temp
      val x8008 = CounterChain.copy(x8234_0, "x8008")
      val x8009 = CounterChain.copy(x8249_0, "x8009")
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7960_x8296 = SRAM(size = 1920, writeCtr = x8008(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8008(0))).wtPort(x7960_vector)
      val x7961_x8299 = SRAM(size = 1920, writeCtr = x8009(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x7303(0), swapWrite = x8009(0))).wtPort(x7961_vector)
      val x7960_x8296_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7960_x8296_addr_vector)
      val x7961_x8299_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7961_x8299_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7961_x8299))
      Stage(stage(1), operands=List(x8009(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7210)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7210), CU.ctr(stage(1), x8009(1))), op=FixAdd, results=List(x7961_x8299.writeAddr, CU.temp(stage(2), tr7211)))
      stage = stage0 +: WAStages(2, List(x7960_x8296))
      Stage(stage(1), operands=List(x8008(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7219)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7219), CU.ctr(stage(1), x8008(1))), op=FixAdd, results=List(x7960_x8296.writeAddr, CU.temp(stage(2), tr7220)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x7960_x8296_addr_fifo.load), op=Bypass, results=List(x7960_x8296.readAddr))
      Stage(stage(2), operands=List(x7961_x8299_addr_fifo.load), op=Bypass, results=List(x7961_x8299.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x7960_x8296), x7961_x8299.load), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7337_vector)))
    }
    val x8358_20 = StreamPipeline(name = "x8358_20", parent=x8358, deps=List(x8358_18, x8358_19, x8358_17, x8358_14)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7339 = CU.temp
      val tr7338 = CU.temp
      val bus_7332_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7332_vector)
      val bus_7337_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7337_vector)
      val bus_7327_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7327_vector)
      val bus_7316_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7316_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(bus_7332_fifo.load, bus_7337_fifo.load), op=FltAdd, results=List(CU.temp(stage(1), tr7338)))
      Stage(stage(2), operands=List(bus_7327_fifo.load, CU.temp(stage(1), tr7338)), op=FltAdd, results=List(CU.temp(stage(2), tr7339)))
      Stage(stage(3), operands=List(bus_7316_fifo.load, CU.temp(stage(2), tr7339)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_7340_vector)))
    }
    val x8358_21 = StreamPipeline(name = "x8358_21", parent=x8358, deps=List(x8358_8, x8358_20)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7342 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val x7248_x8302 = SRAM(size = 1920, writeCtr = x7303(0), banking = Strided(1), buffering = SingleBuffer())
      val x7248_x8302_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x7248_x8302_addr_vector)
      val bus_7340_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_7340_vector)
      val wr7344 = CU.wtAddr(x7248_x8302)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x7248_x8302_addr_fifo.load), op=Bypass, results=List(x7248_x8302.readAddr))
      Stage(stage(2), operands=List(bus_7340_fifo.load, x7248_x8302.load), op=FltAdd, results=List(CU.vecOut(stage(2), x7248_vector), CU.store(stage(2), x7248_x8302)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7303(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr7342)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7342), CU.ctr(stage(3), x7303(1))), op=FixAdd, results=List(CU.wtAddr(stage(4), wr7344)))
    }
    val x8376_0 = Pipeline(name = "x8376_0", parent=x8397, deps=List(x8362)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7351 = CU.temp
      val tr7349 = CU.temp
      val tr7348 = CU.temp
      val x7303 = CounterChain.copy(x8358, "x7303")
      val ctr99 = (Const("0i").out, Const("20i").out, Const("16i").out) // Counter
      val x8365 = CounterChain(name = "x8365", ctr99)
      val x7248_x8368 = SRAM(size = 1920, writeCtr = x7303(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x7248_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7248_x8368))
      Stage(stage(1), operands=List(x7303(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7348)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7348), CU.ctr(stage(1), x7303(1))), op=FixAdd, results=List(x7248_x8368.writeAddr, CU.temp(stage(2), tr7349)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x8365(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7351)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7351), Const("95i")), op=FixAdd, results=List(x7248_x8368.readAddr))
      Stage(stage(3), operands=List(x7248_x8368.load), op=Bypass, results=List(CU.vecOut(stage(3), x8363_vector)))
    }
    val x8395_0 = Pipeline(name = "x8395_0", parent=x8397, deps=List(x8376_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7362 = CU.temp
      val tr7360 = CU.temp
      val tr7359 = CU.temp
      val x8365 = CounterChain.copy(x8376_0, "x8365")
      val x7303 = CounterChain.copy("x8358", "x7303")
      val ctr101 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr102 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8379 = CounterChain(name = "x8379", ctr101, ctr102)
      val x7248_x8382 = SRAM(size = 1920, writeCtr = x7303(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7248_vector)
      val x8363_x8385 = SRAM(size = 20, writeCtr = x8365(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x8363_vector).rdAddr(x8379(0)).wtAddr(x8365(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7248_x8382))
      Stage(stage(1), operands=List(x7303(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7359)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7359), CU.ctr(stage(1), x7303(1))), op=FixAdd, results=List(x7248_x8382.writeAddr, CU.temp(stage(2), tr7360)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x8379(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7362)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7362), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7248_x8382.readAddr))
      Stage(stage(3), operands=List(x7248_x8382.load, CU.load(stage(2), x8363_x8385)), op=FltDiv, results=List(CU.vecOut(stage(3), x7245_vector)))
    }
    val x8412_0 = Pipeline(name = "x8412_0", parent=x8434, deps=List(x8397)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7379 = CU.temp
      val tr7375 = CU.temp
      val tr7373 = CU.temp
      val tr7372 = CU.temp
      val x8379 = CounterChain.copy("x8395_0", "x8379")
      val ctr105 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr106 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x8401 = CounterChain(name = "x8401", ctr105, ctr106)
      val x7245_x8404 = SRAM(size = 1920, writeCtr = x8379(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x7245_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7245_x8404))
      Stage(stage(1), operands=List(x8379(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7372)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7372), CU.ctr(stage(1), x8379(1))), op=FixAdd, results=List(x7245_x8404.writeAddr, CU.temp(stage(2), tr7373)))
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x8401(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7375)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7375), CU.ctr(stage(1), x8401(1))), op=FixAdd, results=List(x7245_x8404.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x8401(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr7379)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr7379), CU.ctr(stage(3), x8401(1))), op=FixAdd, results=List())
      Stage(stage(5), operands=List(CU.load(stage(4), x7245_x8404)), op=Bypass, results=List(CU.vecOut(stage(5), x8398_vector)))
    }
    val x8432 = StreamController(name = "x8432", parent=x8434, deps=List(x8412_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x8432_unitcc = CounterChain(name = "x8432_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x8417_0 = UnitPipeline(name = "x8417_0", parent=x8432, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x8417_unitcc = CounterChain(name = "x8417_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x8430_mc.ofs)))
      Stage(stage(2), operands=List(Const("1920i")), op=Bypass, results=List(CU.scalarOut(stage(2), x8430_mc.len)))
    }
    val x8428_0 = Pipeline(name = "x8428_0", parent=x8432, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr7388 = CU.temp
      val tr7387 = CU.temp
      val x8401 = CounterChain.copy(x8412_0, "x8401")
      val ctr109 = (Const("0i").out, Const("1920i").out, Const("16i").out) // Counter
      val x8419 = CounterChain(name = "x8419", ctr109)
      val x8398_x8422 = SRAM(size = 1920, writeCtr = x8401(0), banking = Diagonal(96,1), buffering = SingleBuffer()).wtPort(x8398_vector).rdAddr(x8419(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x8398_x8422))
      Stage(stage(1), operands=List(x8401(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr7387)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr7387), CU.ctr(stage(1), x8401(1))), op=FixAdd, results=List(x8398_x8422.writeAddr, CU.temp(stage(2), tr7388)))
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x8398_x8422.load), op=Bypass, results=List(CU.vecOut(stage(1), x8430_mc.vdata)))
    }
    val x8360_leaf = UnitPipeline(name = "x8360_leaf", parent=x8360, deps=List(x7475, x7403, x7907, x7799, x7583, x7439, x7871, x7655, x7727, x7691, x7763, x8358, x7943, x7835, x7619, x7547, x7511)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7300 = CounterChain.copy(x8360, "x7300")
      val x8360_unitcc = CounterChain(name = "x8360_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }

  }
}
