import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GEMM_Blocked extends PIRApp {
  def main(top:Top) = {
    val x9049_x9049_dsp0_bank0_s = Scalar("x9049_x9049_dsp0_bank0")
    val x8652_x8679_s = Scalar("x8652_x8679")
    val x9140_x9140_dsp0_bank0_s = Scalar("x9140_x9140_dsp0_bank0")
    val x7920_x7920_dsp0_bank0_s = Scalar("x7920_x7920_dsp0_bank0")
    val x8021_x8038_data_s = Scalar("x8021_x8038_data")
    val x7702_x9395_s = Scalar("x7702_x9395")
    val x9472_b9743_x9486_b9745_s = Scalar("x9472_b9743_x9486_b9745")
    val x7706_x7706_dsp0_bank1_s = Scalar("x7706_x7706_dsp0_bank1")
    val x8913_b9684_x8929_b9686_s = Scalar("x8913_b9684_x8929_b9686")
    val x7815_b9568_x7831_b9570_s = Scalar("x7815_b9568_x7831_b9570")
    val x8835_x8835_dsp0_bank1_s = Scalar("x8835_x8835_dsp0_bank1")
    val x8270_x8270_dsp0_bank0_s = Scalar("x8270_x8270_dsp0_bank0")
    val x7700_x7700_dsp1_bank0_s = Scalar("x7700_x7700_dsp1_bank0")
    val x8084_x8220_s = Scalar("x8084_x8220")
    val x9139_x9139_dsp0_bank0_s = Scalar("x9139_x9139_dsp0_bank0")
    val x9213_x9213_dsp1_bank0_s = Scalar("x9213_x9213_dsp1_bank0")
    val x7708_x7708_dsp1_bank0_s = Scalar("x7708_x7708_dsp1_bank0")
    val x8837_x8837_dsp0_bank0_s = Scalar("x8837_x8837_dsp0_bank0")
    val x7711_b9552_x7726_b9554_s = Scalar("x7711_b9552_x7726_b9554")
    val x8087_x8087_dsp0_bank0_s = Scalar("x8087_x8087_dsp0_bank0")
    val x8273_x8273_dsp0_bank0_s = Scalar("x8273_x8273_dsp0_bank0")
    val x8484_x8511_s = Scalar("x8484_x8511")
    val x8052_b9587_x8068_b9589_s = Scalar("x8052_b9587_x8068_b9589")
    val x8411_x8479_s = Scalar("x8411_x8479")
    val x9286_x9286_dsp0_bank0_s = Scalar("x9286_x9286_dsp0_bank0")
    val x7847_x7847_dsp1_bank0_s = Scalar("x7847_x7847_dsp1_bank0")
    val x8618_x8635_data_s = Scalar("x8618_x8635_data")
    val x8084_x8084_dsp1_bank0_s = Scalar("x8084_x8084_dsp1_bank0")
    val x8946_x8963_data_s = Scalar("x8946_x8963_data")
    val x8980_x9007_s = Scalar("x8980_x9007")
    val x9182_b9707_x9198_b9709_s = Scalar("x9182_b9707_x9198_b9709")
    val x8485_x8485_dsp0_bank0_s = Scalar("x8485_x8485_dsp0_bank0")
    val x7919_x7919_dsp0_bank0_s = Scalar("x7919_x7919_dsp0_bank0")
    val x8722_x8760_s = Scalar("x8722_x8760")
    val x8838_x8838_dsp1_bank1_s = Scalar("x8838_x8838_dsp1_bank1")
    val x8381_x8398_data_s = Scalar("x8381_x8398_data")
    val x8156_x8156_dsp0_bank0_s = Scalar("x8156_x8156_dsp0_bank0")
    val x8617_b9648_x8633_b9650_s = Scalar("x8617_b9648_x8633_b9650")
    val x7850_x7850_dsp0_bank0_s = Scalar("x7850_x7850_dsp0_bank0")
    val x8277_x8293_data_s = Scalar("x8277_x8293_data")
    val x8977_x8977_dsp1_bank0_s = Scalar("x8977_x8977_dsp1_bank0")
    val x8272_x8272_dsp1_bank0_s = Scalar("x8272_x8272_dsp1_bank0")
    val a_dram_da = DRAMAddress("a_dram", "a_dram")
    val x8020_b9584_x8036_b9586_s = Scalar("x8020_b9584_x8036_b9586")
    val x7783_b9564_x7799_b9566_s = Scalar("x7783_b9564_x7799_b9566")
    val x8052_b9588_x8068_b9590_s = Scalar("x8052_b9588_x8068_b9590")
    val x8020_b9583_x8036_b9585_s = Scalar("x8020_b9583_x8036_b9585")
    val x8653_x8653_dsp0_bank0_s = Scalar("x8653_x8653_dsp0_bank0")
    val x8842_x8858_data_s = Scalar("x8842_x8858_data")
    val x8838_x8838_dsp0_bank0_s = Scalar("x8838_x8838_dsp0_bank0")
    val b_dram_oc = OffChip("b_dram")
    val x8271_x8271_dsp0_bank1_s = Scalar("x8271_x8271_dsp0_bank1")
    val c_dram_da = DRAMAddress("c_dram", "c_dram")
    val x7846_x7846_dsp1_bank0_s = Scalar("x7846_x7846_dsp1_bank0")
    val x9218_x9256_s = Scalar("x9218_x9256")
    val x7920_x7958_s = Scalar("x7920_x7958")
    val x7847_x7983_s = Scalar("x7847_x7983")
    val x8273_x8273_dsp1_bank0_s = Scalar("x8273_x8273_dsp1_bank0")
    val x8416_x8454_s = Scalar("x8416_x8454")
    val x8586_x8603_data_s = Scalar("x8586_x8603_data")
    val c_dram_oc = OffChip("c_dram")
    val x8945_b9687_x8961_b9689_s = Scalar("x8945_b9687_x8961_b9689")
    val x8272_x8272_dsp0_bank0_s = Scalar("x8272_x8272_dsp0_bank0")
    val x8412_x8548_s = Scalar("x8412_x8548")
    val x8871_b9678_x8886_b9680_s = Scalar("x8871_b9678_x8886_b9680")
    val x8980_x8980_dsp0_bank0_s = Scalar("x8980_x8980_dsp0_bank0")
    val x7707_x7707_dsp1_bank0_s = Scalar("x7707_x7707_dsp1_bank0")
    val x8306_b9617_x8321_b9619_s = Scalar("x8306_b9617_x8321_b9619")
    val x8838_x8838_dsp1_bank0_s = Scalar("x8838_x8838_dsp1_bank0")
    val x7851_x7851_dsp0_bank0_s = Scalar("x7851_x7851_dsp0_bank0")
    val x8157_x8157_dsp0_bank0_s = Scalar("x8157_x8157_dsp0_bank0")
    val x8977_x8977_dsp0_bank0_s = Scalar("x8977_x8977_dsp0_bank0")
    val a_dram_oc = OffChip("a_dram")
    val x7701_x8830_s = Scalar("x7701_x8830")
    val x9049_x9076_s = Scalar("x9049_x9076")
    val x8349_x8366_data_s = Scalar("x8349_x8366_data")
    val x9050_x9050_dsp0_bank0_s = Scalar("x9050_x9050_dsp0_bank0")
    val x7846_x7846_dsp0_bank0_s = Scalar("x7846_x7846_dsp0_bank0")
    val x8416_x8416_dsp0_bank0_s = Scalar("x8416_x8416_dsp0_bank0")
    val x7702_x7702_dsp1_bank0_s = Scalar("x7702_x7702_dsp1_bank0")
    val x8411_x8411_dsp0_bank0_s = Scalar("x8411_x8411_dsp0_bank0")
    val x9217_x9217_dsp0_bank0_s = Scalar("x9217_x9217_dsp0_bank0")
    val x7783_b9563_x7799_b9565_s = Scalar("x7783_b9563_x7799_b9565")
    val x7850_x7877_s = Scalar("x7850_x7877")
    val x8575_x8575_dsp0_bank0_s = Scalar("x8575_x8575_dsp0_bank0")
    val x8574_x8574_dsp0_bank0_s = Scalar("x8574_x8574_dsp0_bank0")
    val x8722_x8722_dsp0_bank0_s = Scalar("x8722_x8722_dsp0_bank0")
    val x8380_b9627_x8396_b9629_s = Scalar("x8380_b9627_x8396_b9629")
    val x9408_b9732_x9422_b9734_s = Scalar("x9408_b9732_x9422_b9734")
    val x9050_x9088_s = Scalar("x9050_x9088")
    val x7847_x7847_dsp0_bank0_s = Scalar("x7847_x7847_dsp0_bank0")
    val x8348_b9624_x8364_b9626_s = Scalar("x8348_b9624_x8364_b9626")
    val x8338_x8338_dsp0_bank0_s = Scalar("x8338_x8338_dsp0_bank0")
    val x8841_b9671_x8856_b9673_s = Scalar("x8841_b9671_x8856_b9673")
    val x8871_b9677_x8886_b9679_s = Scalar("x8871_b9677_x8886_b9679")
    val x7816_x7833_data_s = Scalar("x7816_x7833_data")
    val x7705_x7705_dsp0_bank0_s = Scalar("x7705_x7705_dsp0_bank0")
    val x7772_x7772_dsp0_bank0_s = Scalar("x7772_x7772_dsp0_bank0")
    val x9217_x9244_s = Scalar("x9217_x9244")
    val x8838_x8838_dsp0_bank1_s = Scalar("x8838_x8838_dsp0_bank1")
    val x9218_x9218_dsp0_bank0_s = Scalar("x9218_x9218_dsp0_bank0")
    val x9183_x9200_data_s = Scalar("x9183_x9200_data")
    val x8009_x8009_dsp0_bank0_s = Scalar("x8009_x8009_dsp0_bank0")
    val x7742_x7758_data_s = Scalar("x7742_x7758_data")
    val x7851_x7889_s = Scalar("x7851_x7889")
    val x8649_x8785_s = Scalar("x8649_x8785")
    val x8836_x8836_dsp0_bank1_s = Scalar("x8836_x8836_dsp0_bank1")
    val x9214_x9214_dsp1_bank0_s = Scalar("x9214_x9214_dsp1_bank0")
    val x7846_x7914_s = Scalar("x7846_x7914")
    val x9440_b9737_x9454_b9739_s = Scalar("x9440_b9737_x9454_b9739")
    val x8977_x9113_s = Scalar("x8977_x9113")
    val x7706_x7706_dsp0_bank0_s = Scalar("x7706_x7706_dsp0_bank0")
    val x8276_b9611_x8291_b9613_s = Scalar("x8276_b9611_x8291_b9613")
    val x8721_x8721_dsp0_bank0_s = Scalar("x8721_x8721_dsp0_bank0")
    val x9286_x9313_s = Scalar("x9286_x9313")
    val x8156_x8183_s = Scalar("x8156_x8183")
    val x8835_x8835_dsp0_bank0_s = Scalar("x8835_x8835_dsp0_bank0")
    val x8157_x8195_s = Scalar("x8157_x8195")
    val x8415_x8442_s = Scalar("x8415_x8442")
    val x8836_x8836_dsp0_bank0_s = Scalar("x8836_x8836_dsp0_bank0")
    val x8648_x8648_dsp0_bank0_s = Scalar("x8648_x8648_dsp0_bank0")
    val x8485_x8523_s = Scalar("x8485_x8523")
    val x7741_b9557_x7756_b9559_s = Scalar("x7741_b9557_x7756_b9559")
    val x8412_x8412_dsp1_bank0_s = Scalar("x8412_x8412_dsp1_bank0")
    val x9151_x9168_data_s = Scalar("x9151_x9168_data")
    val x8617_b9647_x8633_b9649_s = Scalar("x8617_b9647_x8633_b9649")
    val x9214_x9350_s = Scalar("x9214_x9350")
    val x7707_x7707_dsp0_bank0_s = Scalar("x7707_x7707_dsp0_bank0")
    val x8380_b9628_x8396_b9630_s = Scalar("x8380_b9628_x8396_b9630")
    val x8087_x8114_s = Scalar("x8087_x8114")
    val x7815_b9567_x7831_b9569_s = Scalar("x7815_b9567_x7831_b9569")
    val x8903_x8903_dsp0_bank0_s = Scalar("x8903_x8903_dsp0_bank0")
    val x8913_b9683_x8929_b9685_s = Scalar("x8913_b9683_x8929_b9685")
    val x7705_x7705_dsp0_bank1_s = Scalar("x7705_x7705_dsp0_bank1")
    val x8053_x8070_data_s = Scalar("x8053_x8070_data")
    val x7700_x8265_s = Scalar("x7700_x8265")
    val x8976_x8976_dsp1_bank0_s = Scalar("x8976_x8976_dsp1_bank0")
    val x8415_x8415_dsp0_bank0_s = Scalar("x8415_x8415_dsp0_bank0")
    val x8837_x8837_dsp0_bank1_s = Scalar("x8837_x8837_dsp0_bank1")
    val x8841_b9672_x8856_b9674_s = Scalar("x8841_b9672_x8856_b9674")
    val x8649_x8649_dsp0_bank0_s = Scalar("x8649_x8649_dsp0_bank0")
    val x7741_b9558_x7756_b9560_s = Scalar("x7741_b9558_x7756_b9560")
    val x8270_x8270_dsp0_bank1_s = Scalar("x8270_x8270_dsp0_bank1")
    val x8306_b9618_x8321_b9620_s = Scalar("x8306_b9618_x8321_b9620")
    val x8653_x8691_s = Scalar("x8653_x8691")
    val x7708_x7708_dsp0_bank1_s = Scalar("x7708_x7708_dsp0_bank1")
    val x9182_b9708_x9198_b9710_s = Scalar("x9182_b9708_x9198_b9710")
    val x7708_x7708_dsp1_bank1_s = Scalar("x7708_x7708_dsp1_bank1")
    val x7701_x7701_dsp0_bank0_s = Scalar("x7701_x7701_dsp0_bank0")
    val x8083_x8083_dsp0_bank0_s = Scalar("x8083_x8083_dsp0_bank0")
    val x8981_x8981_dsp0_bank0_s = Scalar("x8981_x8981_dsp0_bank0")
    val x8010_x8010_dsp0_bank0_s = Scalar("x8010_x8010_dsp0_bank0")
    val x8976_x8976_dsp0_bank0_s = Scalar("x8976_x8976_dsp0_bank0")
    val x8088_x8126_s = Scalar("x8088_x8126")
    val x7702_x7702_dsp0_bank0_s = Scalar("x7702_x7702_dsp0_bank0")
    val x7707_x7707_dsp1_bank1_s = Scalar("x7707_x7707_dsp1_bank1")
    val x8945_b9688_x8961_b9690_s = Scalar("x8945_b9688_x8961_b9690")
    val x9214_x9214_dsp0_bank0_s = Scalar("x9214_x9214_dsp0_bank0")
    val x7712_x7728_data_s = Scalar("x7712_x7728_data")
    val x8083_x8083_dsp1_bank0_s = Scalar("x8083_x8083_dsp1_bank0")
    val x7707_x7707_dsp0_bank1_s = Scalar("x7707_x7707_dsp0_bank1")
    val x9287_x9325_s = Scalar("x9287_x9325")
    val x8337_x8337_dsp0_bank0_s = Scalar("x8337_x8337_dsp0_bank0")
    val x8272_x8272_dsp0_bank1_s = Scalar("x8272_x8272_dsp0_bank1")
    val x7701_x7701_dsp1_bank0_s = Scalar("x7701_x7701_dsp1_bank0")
    val x7711_b9551_x7726_b9553_s = Scalar("x7711_b9551_x7726_b9553")
    val x8837_x8837_dsp1_bank1_s = Scalar("x8837_x8837_dsp1_bank1")
    val x9213_x9213_dsp0_bank0_s = Scalar("x9213_x9213_dsp0_bank0")
    val x8837_x8837_dsp1_bank0_s = Scalar("x8837_x8837_dsp1_bank0")
    val x8484_x8484_dsp0_bank0_s = Scalar("x8484_x8484_dsp0_bank0")
    val x8411_x8411_dsp1_bank0_s = Scalar("x8411_x8411_dsp1_bank0")
    val x8273_x8273_dsp1_bank1_s = Scalar("x8273_x8273_dsp1_bank1")
    val x8648_x8716_s = Scalar("x8648_x8716")
    val x8902_x8902_dsp0_bank0_s = Scalar("x8902_x8902_dsp0_bank0")
    val x9150_b9704_x9166_b9706_s = Scalar("x9150_b9704_x9166_b9706")
    val x8976_x9044_s = Scalar("x8976_x9044")
    val x9440_b9738_x9454_b9740_s = Scalar("x9440_b9738_x9454_b9740")
    val x8088_x8088_dsp0_bank0_s = Scalar("x8088_x8088_dsp0_bank0")
    val x8981_x9019_s = Scalar("x8981_x9019")
    val x9287_x9287_dsp0_bank0_s = Scalar("x9287_x9287_dsp0_bank0")
    val x8585_b9643_x8601_b9645_s = Scalar("x8585_b9643_x8601_b9645")
    val x7773_x7773_dsp0_bank0_s = Scalar("x7773_x7773_dsp0_bank0")
    val x8648_x8648_dsp1_bank0_s = Scalar("x8648_x8648_dsp1_bank0")
    val x8872_x8888_data_s = Scalar("x8872_x8888_data")
    val x9408_b9731_x9422_b9733_s = Scalar("x9408_b9731_x9422_b9733")
    val x7708_x7708_dsp0_bank0_s = Scalar("x7708_x7708_dsp0_bank0")
    val x8412_x8412_dsp0_bank0_s = Scalar("x8412_x8412_dsp0_bank0")
    val x8273_x8273_dsp0_bank1_s = Scalar("x8273_x8273_dsp0_bank1")
    val x9472_b9744_x9486_b9746_s = Scalar("x9472_b9744_x9486_b9746")
    val x9150_b9703_x9166_b9705_s = Scalar("x9150_b9703_x9166_b9705")
    val x8721_x8748_s = Scalar("x8721_x8748")
    val x8083_x8151_s = Scalar("x8083_x8151")
    val x8649_x8649_dsp1_bank0_s = Scalar("x8649_x8649_dsp1_bank0")
    val x7919_x7946_s = Scalar("x7919_x7946")
    val x7784_x7801_data_s = Scalar("x7784_x7801_data")
    val x8348_b9623_x8364_b9625_s = Scalar("x8348_b9623_x8364_b9625")
    val x8272_x8272_dsp1_bank1_s = Scalar("x8272_x8272_dsp1_bank1")
    val x8307_x8323_data_s = Scalar("x8307_x8323_data")
    val x8652_x8652_dsp0_bank0_s = Scalar("x8652_x8652_dsp0_bank0")
    val x9213_x9281_s = Scalar("x9213_x9281")
    val b_dram_da = DRAMAddress("b_dram", "b_dram")
    val x8084_x8084_dsp0_bank0_s = Scalar("x8084_x8084_dsp0_bank0")
    val x8271_x8271_dsp0_bank0_s = Scalar("x8271_x8271_dsp0_bank0")
    val x8914_x8931_data_s = Scalar("x8914_x8931_data")
    val x8585_b9644_x8601_b9646_s = Scalar("x8585_b9644_x8601_b9646")
    val x8276_b9612_x8291_b9614_s = Scalar("x8276_b9612_x8291_b9614")
    val x7700_x7700_dsp0_bank0_s = Scalar("x7700_x7700_dsp0_bank0")
    val x9504 = Sequential(name="x9504",parent=top) { implicit CU => 
      val x9504_unit = CounterChain(name = "x9504_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x9503 = MetaPipeline(name="x9503",parent=x9504) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(16), par=3) // Counter
      val x7699 = CounterChain(name = "x7699", ctr1).iter(2)
    }
    val x7700_dsp0_bank0 = MemoryPipeline(name="x7700_dsp0_bank0",parent="x8267") { implicit CU => 
      val b9607 = CU.temp(None)
      val b9609 = CU.temp(None)
      val x8265 = ScalarFIFO(size=1,name="x8265").wtPort(x7700_x8265_s)
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x7700 = SRAM(size=1024,name="x7700",banking = Strided(1)).wtPort(x8265.readPort).rdPort(x7700_x7700_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9609))
      WAStage(operands=List(b9609, CU.ctr(x8247(1))), op=FixAdd, results=List(x7700.writeAddr))
      RAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9607))
      RAStage(operands=List(b9607, CU.ctr(x8247(1))), op=FixAdd, results=List(x7700.readAddr))
    }
    val x7700_dsp1_bank0 = MemoryPipeline(name="x7700_dsp1_bank0",parent="x8267") { implicit CU => 
      val b9609 = CU.temp(None)
      val b9735 = CU.temp(None)
      val x8265 = ScalarFIFO(size=1,name="x8265").wtPort(x7700_x8265_s)
      val x9407 = CounterChain.copy("x9437", "x9407")
      val x9425 = CounterChain.copy("x9432", "x9425")
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x7700 = SRAM(size=1024,name="x7700",banking = Strided(1)).wtPort(x8265.readPort).rdPort(x7700_x7700_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9609))
      WAStage(operands=List(b9609, CU.ctr(x8247(1))), op=FixAdd, results=List(x7700.writeAddr))
      RAStage(operands=List(CU.ctr(x9407(0)), Const(16)), op=FixMul, results=List(b9735))
      RAStage(operands=List(b9735, CU.ctr(x9425(0))), op=FixAdd, results=List(x7700.readAddr))
    }
    val x7701_dsp0_bank0 = MemoryPipeline(name="x7701_dsp0_bank0",parent="x8832") { implicit CU => 
      val b9667 = CU.temp(None)
      val b9669 = CU.temp(None)
      val x8830 = ScalarFIFO(size=1,name="x8830").wtPort(x7701_x8830_s)
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x7701 = SRAM(size=1024,name="x7701",banking = Strided(1)).wtPort(x8830.readPort).rdPort(x7701_x7701_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9669))
      WAStage(operands=List(b9669, CU.ctr(x8812(1))), op=FixAdd, results=List(x7701.writeAddr))
      RAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9667))
      RAStage(operands=List(b9667, CU.ctr(x8812(1))), op=FixAdd, results=List(x7701.readAddr))
    }
    val x7701_dsp1_bank0 = MemoryPipeline(name="x7701_dsp1_bank0",parent="x8832") { implicit CU => 
      val b9741 = CU.temp(None)
      val b9669 = CU.temp(None)
      val x8830 = ScalarFIFO(size=1,name="x8830").wtPort(x7701_x8830_s)
      val x9457 = CounterChain.copy("x9464", "x9457")
      val x9439 = CounterChain.copy("x9469", "x9439")
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x7701 = SRAM(size=1024,name="x7701",banking = Strided(1)).wtPort(x8830.readPort).rdPort(x7701_x7701_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9669))
      WAStage(operands=List(b9669, CU.ctr(x8812(1))), op=FixAdd, results=List(x7701.writeAddr))
      RAStage(operands=List(CU.ctr(x9439(0)), Const(16)), op=FixMul, results=List(b9741))
      RAStage(operands=List(b9741, CU.ctr(x9457(0))), op=FixAdd, results=List(x7701.readAddr))
    }
    val x7702_dsp0_bank0 = MemoryPipeline(name="x7702_dsp0_bank0",parent="x9397") { implicit CU => 
      val b9729 = CU.temp(None)
      val b9727 = CU.temp(None)
      val x9395 = ScalarFIFO(size=1,name="x9395").wtPort(x7702_x9395_s)
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x7702 = SRAM(size=1024,name="x7702",banking = Strided(1)).wtPort(x9395.readPort).rdPort(x7702_x7702_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9729))
      WAStage(operands=List(b9729, CU.ctr(x9377(1))), op=FixAdd, results=List(x7702.writeAddr))
      RAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9727))
      RAStage(operands=List(b9727, CU.ctr(x9377(1))), op=FixAdd, results=List(x7702.readAddr))
    }
    val x7702_dsp1_bank0 = MemoryPipeline(name="x7702_dsp1_bank0",parent="x9397") { implicit CU => 
      val b9729 = CU.temp(None)
      val b9747 = CU.temp(None)
      val x9395 = ScalarFIFO(size=1,name="x9395").wtPort(x7702_x9395_s)
      val x9471 = CounterChain.copy("x9501", "x9471")
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x9489 = CounterChain.copy("x9496", "x9489")
      val x7702 = SRAM(size=1024,name="x7702",banking = Strided(1)).wtPort(x9395.readPort).rdPort(x7702_x7702_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9729))
      WAStage(operands=List(b9729, CU.ctr(x9377(1))), op=FixAdd, results=List(x7702.writeAddr))
      RAStage(operands=List(CU.ctr(x9471(0)), Const(16)), op=FixMul, results=List(b9747))
      RAStage(operands=List(b9747, CU.ctr(x9489(0))), op=FixAdd, results=List(x7702.readAddr))
    }
    val x8267 = MetaPipeline(name="x8267",parent=x9503) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x7704 = CounterChain(name = "x7704", ctr2).iter(2)
    }
    val x7705_dsp0_bank0 = MemoryPipeline(name="x7705_dsp0_bank0",parent="x8267") { implicit CU => 
      val b9603 = CU.temp(None)
      val b9579 = CU.temp(None)
      val x7994 = ScalarFIFO(size=1,name="x7994").wtPort(x7846_x7846_dsp1_bank0_s)
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x7771 = CounterChain.copy("x8006", "x7771")
      val x7988 = CounterChain.copy("x7995", "x7988")
      val x7705 = SRAM(size=1024,name="x7705",banking = Strided(1)).wtPort(x7994.readPort).rdPort(x7705_x7705_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x7771(0)), Const(16)), op=FixMul, results=List(b9579))
      WAStage(operands=List(b9579, CU.ctr(x7988(0))), op=FixAdd, results=List(x7705.writeAddr))
      RAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9603))
      RAStage(operands=List(b9603, CU.ctr(x8247(1))), op=FixAdd, results=List(x7705.readAddr))
    }
    val x7705_dsp0_bank1 = MemoryPipeline(name="x7705_dsp0_bank1",parent="x8267") { implicit CU => 
      val b9603 = CU.temp(None)
      val b9581 = CU.temp(None)
      val x8003 = ScalarFIFO(size=1,name="x8003").wtPort(x7847_x7847_dsp1_bank0_s)
      val x7997 = CounterChain.copy("x8004", "x7997")
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x7771 = CounterChain.copy("x8006", "x7771")
      val x7705 = SRAM(size=1024,name="x7705",banking = Strided(1)).wtPort(x8003.readPort).rdPort(x7705_x7705_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x7771(0)), Const(16)), op=FixMul, results=List(b9581))
      WAStage(operands=List(b9581, CU.ctr(x7997(0))), op=FixAdd, results=List(x7705.writeAddr))
      RAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9603))
      RAStage(operands=List(b9603, CU.ctr(x8247(1))), op=FixAdd, results=List(x7705.readAddr))
    }
    val x7706_dsp0_bank0 = MemoryPipeline(name="x7706_dsp0_bank0",parent="x8267") { implicit CU => 
      val b9605 = CU.temp(None)
      val b9599 = CU.temp(None)
      val x8231 = ScalarFIFO(size=1,name="x8231").wtPort(x8083_x8083_dsp1_bank0_s)
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x8225 = CounterChain.copy("x8232", "x8225")
      val x8008 = CounterChain.copy("x8243", "x8008")
      val x7706 = SRAM(size=1024,name="x7706",banking = Strided(1)).wtPort(x8231.readPort).rdPort(x7706_x7706_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8008(0)), Const(16)), op=FixMul, results=List(b9599))
      WAStage(operands=List(b9599, CU.ctr(x8225(0))), op=FixAdd, results=List(x7706.writeAddr))
      RAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9605))
      RAStage(operands=List(b9605, CU.ctr(x8247(1))), op=FixAdd, results=List(x7706.readAddr))
    }
    val x7706_dsp0_bank1 = MemoryPipeline(name="x7706_dsp0_bank1",parent="x8267") { implicit CU => 
      val b9605 = CU.temp(None)
      val b9601 = CU.temp(None)
      val x8240 = ScalarFIFO(size=1,name="x8240").wtPort(x8084_x8084_dsp1_bank0_s)
      val x8234 = CounterChain.copy("x8241", "x8234")
      val x8247 = CounterChain.copy("x8266_0", "x8247")
      val x8008 = CounterChain.copy("x8243", "x8008")
      val x7706 = SRAM(size=1024,name="x7706",banking = Strided(1)).wtPort(x8240.readPort).rdPort(x7706_x7706_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8008(0)), Const(16)), op=FixMul, results=List(b9601))
      WAStage(operands=List(b9601, CU.ctr(x8234(0))), op=FixAdd, results=List(x7706.writeAddr))
      RAStage(operands=List(CU.ctr(x8247(0)), Const(16)), op=FixMul, results=List(b9605))
      RAStage(operands=List(b9605, CU.ctr(x8247(1))), op=FixAdd, results=List(x7706.readAddr))
    }
    val x7707_dsp0_bank0 = MemoryPipeline(name="x7707_dsp0_bank0",parent="x8267") { implicit CU => 
      val b9555 = CU.temp(None)
      val b9571 = CU.temp(None)
      val x7736 = ScalarFIFO(size=1,name="x7736").wtPort(x7712_x7728_data_s)
      val x7730 = CounterChain.copy("x7737", "x7730")
      val x7710 = CounterChain.copy("x7738", "x7710")
      val x7868 = CounterChain.copy("x7878_0", "x7868")
      val x7849 = CounterChain.copy("x7916", "x7849")
      val x7707 = SRAM(size=256,name="x7707",banking = Strided(1)).wtPort(x7736.readPort).rdPort(x7707_x7707_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x7710(0)), Const(16)), op=FixMul, results=List(b9555))
      WAStage(operands=List(b9555, CU.ctr(x7730(0))), op=FixAdd, results=List(x7707.writeAddr))
      RAStage(operands=List(CU.ctr(x7849(0)), Const(16)), op=FixMul, results=List(b9571))
      RAStage(operands=List(b9571, CU.ctr(x7868(0))), op=FixAdd, results=List(x7707.readAddr))
    }
    val x7707_dsp0_bank1 = MemoryPipeline(name="x7707_dsp0_bank1",parent="x8267") { implicit CU => 
      val b9573 = CU.temp(None)
      val b9555 = CU.temp(None)
      val x7736 = ScalarFIFO(size=1,name="x7736").wtPort(x7712_x7728_data_s)
      val x7880 = CounterChain.copy("x7890_0", "x7880")
      val x7730 = CounterChain.copy("x7737", "x7730")
      val x7710 = CounterChain.copy("x7738", "x7710")
      val x7849 = CounterChain.copy("x7916", "x7849")
      val x7707 = SRAM(size=256,name="x7707",banking = Strided(1)).wtPort(x7736.readPort).rdPort(x7707_x7707_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x7710(0)), Const(16)), op=FixMul, results=List(b9555))
      WAStage(operands=List(b9555, CU.ctr(x7730(0))), op=FixAdd, results=List(x7707.writeAddr))
      RAStage(operands=List(CU.ctr(x7849(0)), Const(16)), op=FixMul, results=List(b9573))
      RAStage(operands=List(b9573, CU.ctr(x7880(0))), op=FixAdd, results=List(x7707.readAddr))
    }
    val x7707_dsp1_bank0 = MemoryPipeline(name="x7707_dsp1_bank0",parent="x8267") { implicit CU => 
      val b9555 = CU.temp(None)
      val b9575 = CU.temp(None)
      val x7736 = ScalarFIFO(size=1,name="x7736").wtPort(x7712_x7728_data_s)
      val x7730 = CounterChain.copy("x7737", "x7730")
      val x7710 = CounterChain.copy("x7738", "x7710")
      val x7937 = CounterChain.copy("x7947_0", "x7937")
      val x7918 = CounterChain.copy("x7985", "x7918")
      val x7707 = SRAM(size=256,name="x7707",banking = Strided(1)).wtPort(x7736.readPort).rdPort(x7707_x7707_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x7710(0)), Const(16)), op=FixMul, results=List(b9555))
      WAStage(operands=List(b9555, CU.ctr(x7730(0))), op=FixAdd, results=List(x7707.writeAddr))
      RAStage(operands=List(CU.ctr(x7918(0)), Const(16)), op=FixMul, results=List(b9575))
      RAStage(operands=List(b9575, CU.ctr(x7937(0))), op=FixAdd, results=List(x7707.readAddr))
    }
    val x7707_dsp1_bank1 = MemoryPipeline(name="x7707_dsp1_bank1",parent="x8267") { implicit CU => 
      val b9555 = CU.temp(None)
      val b9577 = CU.temp(None)
      val x7736 = ScalarFIFO(size=1,name="x7736").wtPort(x7712_x7728_data_s)
      val x7949 = CounterChain.copy("x7959_0", "x7949")
      val x7730 = CounterChain.copy("x7737", "x7730")
      val x7710 = CounterChain.copy("x7738", "x7710")
      val x7918 = CounterChain.copy("x7985", "x7918")
      val x7707 = SRAM(size=256,name="x7707",banking = Strided(1)).wtPort(x7736.readPort).rdPort(x7707_x7707_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x7710(0)), Const(16)), op=FixMul, results=List(b9555))
      WAStage(operands=List(b9555, CU.ctr(x7730(0))), op=FixAdd, results=List(x7707.writeAddr))
      RAStage(operands=List(CU.ctr(x7918(0)), Const(16)), op=FixMul, results=List(b9577))
      RAStage(operands=List(b9577, CU.ctr(x7949(0))), op=FixAdd, results=List(x7707.readAddr))
    }
    val x7708_dsp0_bank0 = MemoryPipeline(name="x7708_dsp0_bank0",parent="x8267") { implicit CU => 
      val b9591 = CU.temp(None)
      val b9561 = CU.temp(None)
      val x7766 = ScalarFIFO(size=1,name="x7766").wtPort(x7742_x7758_data_s)
      val x7760 = CounterChain.copy("x7767", "x7760")
      val x7740 = CounterChain.copy("x7768", "x7740")
      val x8105 = CounterChain.copy("x8115_0", "x8105")
      val x8086 = CounterChain.copy("x8153", "x8086")
      val x7708 = SRAM(size=256,name="x7708",banking = Strided(1)).wtPort(x7766.readPort).rdPort(x7708_x7708_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x7740(0)), Const(16)), op=FixMul, results=List(b9561))
      WAStage(operands=List(b9561, CU.ctr(x7760(0))), op=FixAdd, results=List(x7708.writeAddr))
      RAStage(operands=List(CU.ctr(x8086(0)), Const(16)), op=FixMul, results=List(b9591))
      RAStage(operands=List(b9591, CU.ctr(x8105(0))), op=FixAdd, results=List(x7708.readAddr))
    }
    val x7708_dsp0_bank1 = MemoryPipeline(name="x7708_dsp0_bank1",parent="x8267") { implicit CU => 
      val b9593 = CU.temp(None)
      val b9561 = CU.temp(None)
      val x7766 = ScalarFIFO(size=1,name="x7766").wtPort(x7742_x7758_data_s)
      val x8117 = CounterChain.copy("x8127_0", "x8117")
      val x7760 = CounterChain.copy("x7767", "x7760")
      val x7740 = CounterChain.copy("x7768", "x7740")
      val x8086 = CounterChain.copy("x8153", "x8086")
      val x7708 = SRAM(size=256,name="x7708",banking = Strided(1)).wtPort(x7766.readPort).rdPort(x7708_x7708_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x7740(0)), Const(16)), op=FixMul, results=List(b9561))
      WAStage(operands=List(b9561, CU.ctr(x7760(0))), op=FixAdd, results=List(x7708.writeAddr))
      RAStage(operands=List(CU.ctr(x8086(0)), Const(16)), op=FixMul, results=List(b9593))
      RAStage(operands=List(b9593, CU.ctr(x8117(0))), op=FixAdd, results=List(x7708.readAddr))
    }
    val x7708_dsp1_bank0 = MemoryPipeline(name="x7708_dsp1_bank0",parent="x8267") { implicit CU => 
      val b9595 = CU.temp(None)
      val b9561 = CU.temp(None)
      val x7766 = ScalarFIFO(size=1,name="x7766").wtPort(x7742_x7758_data_s)
      val x8174 = CounterChain.copy("x8184_0", "x8174")
      val x7760 = CounterChain.copy("x7767", "x7760")
      val x7740 = CounterChain.copy("x7768", "x7740")
      val x8155 = CounterChain.copy("x8222", "x8155")
      val x7708 = SRAM(size=256,name="x7708",banking = Strided(1)).wtPort(x7766.readPort).rdPort(x7708_x7708_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x7740(0)), Const(16)), op=FixMul, results=List(b9561))
      WAStage(operands=List(b9561, CU.ctr(x7760(0))), op=FixAdd, results=List(x7708.writeAddr))
      RAStage(operands=List(CU.ctr(x8155(0)), Const(16)), op=FixMul, results=List(b9595))
      RAStage(operands=List(b9595, CU.ctr(x8174(0))), op=FixAdd, results=List(x7708.readAddr))
    }
    val x7708_dsp1_bank1 = MemoryPipeline(name="x7708_dsp1_bank1",parent="x8267") { implicit CU => 
      val b9597 = CU.temp(None)
      val b9561 = CU.temp(None)
      val x7766 = ScalarFIFO(size=1,name="x7766").wtPort(x7742_x7758_data_s)
      val x7760 = CounterChain.copy("x7767", "x7760")
      val x8186 = CounterChain.copy("x8196_0", "x8186")
      val x7740 = CounterChain.copy("x7768", "x7740")
      val x8155 = CounterChain.copy("x8222", "x8155")
      val x7708 = SRAM(size=256,name="x7708",banking = Strided(1)).wtPort(x7766.readPort).rdPort(x7708_x7708_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x7740(0)), Const(16)), op=FixMul, results=List(b9561))
      WAStage(operands=List(b9561, CU.ctr(x7760(0))), op=FixAdd, results=List(x7708.writeAddr))
      RAStage(operands=List(CU.ctr(x8155(0)), Const(16)), op=FixMul, results=List(b9597))
      RAStage(operands=List(b9597, CU.ctr(x8186(0))), op=FixAdd, results=List(x7708.readAddr))
    }
    val x7738 = StreamController(name="x7738",parent=x8267) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7710 = CounterChain(name = "x7710", ctr3).iter(16)
    }
    val x7727_0 = Pipeline(name="x7727_0",parent=x7738) { implicit CU => 
      val x7713 = CU.temp(None)
      val x7715 = CU.temp(None)
      val x7717 = CU.temp(None)
      val x7718 = CU.temp(None)
      val x7720 = ScalarBuffer(name="x7720").wtPort(b_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x7710 = CounterChain.copy("x7738", "x7710")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x7727_unit = CounterChain(name = "x7727_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x7704(0)), CU.ctr(x7710(0))), op=FixAdd, results=List(x7713))
      Stage(operands=List(x7713, Const(6)), op=FixSla, results=List(x7715))
      Stage(operands=List(x7715, CU.ctr(x7699(0))), op=FixAdd, results=List(x7717))
      Stage(operands=List(x7717, Const(3)), op=FixSla, results=List(x7718))
      Stage(operands=List(x7718, CU.load(x7720)), op=FixAdd, results=List(CU.scalarOut(x7711_b9551_x7726_b9553_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x7711_b9552_x7726_b9554_s)))
    }
    val x7728 = MemoryController(name="x7728",parent=x7738,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7711_b9551 = ScalarFIFO(size=1,name="offset").wtPort(x7711_b9551_x7726_b9553_s)
      val x7711_b9552 = ScalarFIFO(size=1,name="size").wtPort(x7711_b9552_x7726_b9554_s)
      CU.newSout("data", x7712_x7728_data_s)
    }
    val x7737 = Pipeline(name="x7737",parent=x7738) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7730 = CounterChain(name = "x7730", ctr4).iter(1)
    }
    val x7768 = StreamController(name="x7768",parent=x8267) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7740 = CounterChain(name = "x7740", ctr5).iter(16)
    }
    val x7757_0 = Pipeline(name="x7757_0",parent=x7768) { implicit CU => 
      val x7747 = CU.temp(None)
      val x7748 = CU.temp(None)
      val x7743 = CU.temp(None)
      val x7745 = CU.temp(None)
      val x7750 = ScalarBuffer(name="x7750").wtPort(b_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x7740 = CounterChain.copy("x7768", "x7740")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x7757_unit = CounterChain(name = "x7757_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x7704(0)), CU.ctr(x7740(0))), op=FixAdd, results=List(x7743))
      Stage(operands=List(x7743, Const(6)), op=FixSla, results=List(x7745))
      Stage(operands=List(x7745, CU.ctr(x7699(0))), op=FixAdd, results=List(x7747))
      Stage(operands=List(x7747, Const(3)), op=FixSla, results=List(x7748))
      Stage(operands=List(x7748, CU.load(x7750)), op=FixAdd, results=List(CU.scalarOut(x7741_b9557_x7756_b9559_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x7741_b9558_x7756_b9560_s)))
    }
    val x7758 = MemoryController(name="x7758",parent=x7768,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7741_b9557 = ScalarFIFO(size=1,name="offset").wtPort(x7741_b9557_x7756_b9559_s)
      val x7741_b9558 = ScalarFIFO(size=1,name="size").wtPort(x7741_b9558_x7756_b9560_s)
      CU.newSout("data", x7742_x7758_data_s)
    }
    val x7767 = Pipeline(name="x7767",parent=x7768) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7760 = CounterChain(name = "x7760", ctr6).iter(1)
    }
    val x8006 = MetaPipeline(name="x8006",parent=x8267) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x7771 = CounterChain(name = "x7771", ctr7).iter(32)
    }
    val x7772_dsp0_bank0 = MemoryPipeline(name="x7772_dsp0_bank0",parent="x8006") { implicit CU => 
      val x7810 = ScalarFIFO(size=1,name="x7810").wtPort(x7784_x7801_data_s)
      val x7803 = CounterChain.copy("x7811", "x7803")
      val x7849 = CounterChain.copy("x7916", "x7849")
      val x7772 = SRAM(size=16,name="x7772",banking = Strided(1)).wtPort(x7810.readPort).rdPort(x7772_x7772_dsp0_bank0_s).rdAddr(x7849(0)).rdAddr(x7849(0)).wtAddr(x7803(0))
    }
    val x7773_dsp0_bank0 = MemoryPipeline(name="x7773_dsp0_bank0",parent="x8006") { implicit CU => 
      val x7842 = ScalarFIFO(size=1,name="x7842").wtPort(x7816_x7833_data_s)
      val x7835 = CounterChain.copy("x7843", "x7835")
      val x7918 = CounterChain.copy("x7985", "x7918")
      val x7773 = SRAM(size=16,name="x7773",banking = Strided(1)).wtPort(x7842.readPort).rdPort(x7773_x7773_dsp0_bank0_s).rdAddr(x7918(0)).rdAddr(x7918(0)).wtAddr(x7835(0))
    }
    val x7812 = StreamController(name="x7812",parent=x8006) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x7782 = CounterChain(name = "x7782", ctr8).iter(1)
    }
    val x7800_0 = Pipeline(name="x7800_0",parent=x7812) { implicit CU => 
      val x7790 = CU.temp(None)
      val x7787 = CU.temp(None)
      val x7785 = CU.temp(None)
      val x7789 = CU.temp(None)
      val x7792 = ScalarBuffer(name="x7792").wtPort(a_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x7800_unit = CounterChain(name = "x7800_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x7771 = CounterChain.copy("x8006", "x7771")
      val x7782 = CounterChain.copy("x7812", "x7782")
      Stage(operands=List(CU.ctr(x7771(0)), CU.ctr(x7782(0))), op=FixAdd, results=List(x7785))
      Stage(operands=List(x7785, Const(6)), op=FixSla, results=List(x7787))
      Stage(operands=List(x7787, CU.ctr(x7704(0))), op=FixAdd, results=List(x7789))
      Stage(operands=List(x7789, Const(3)), op=FixSla, results=List(x7790))
      Stage(operands=List(x7790, CU.load(x7792)), op=FixAdd, results=List(CU.scalarOut(x7783_b9563_x7799_b9565_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x7783_b9564_x7799_b9566_s)))
    }
    val x7801 = MemoryController(name="x7801",parent=x7812,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7783_b9563 = ScalarFIFO(size=1,name="offset").wtPort(x7783_b9563_x7799_b9565_s)
      val x7783_b9564 = ScalarFIFO(size=1,name="size").wtPort(x7783_b9564_x7799_b9566_s)
      CU.newSout("data", x7784_x7801_data_s)
    }
    val x7811 = Pipeline(name="x7811",parent=x7812) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7803 = CounterChain(name = "x7803", ctr9).iter(16)
    }
    val x7844 = StreamController(name="x7844",parent=x8006) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x7814 = CounterChain(name = "x7814", ctr10).iter(1)
    }
    val x7832_0 = Pipeline(name="x7832_0",parent=x7844) { implicit CU => 
      val x7822 = CU.temp(None)
      val x7817 = CU.temp(None)
      val x7821 = CU.temp(None)
      val x7819 = CU.temp(None)
      val x7824 = ScalarBuffer(name="x7824").wtPort(a_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x7814 = CounterChain.copy("x7844", "x7814")
      val x7771 = CounterChain.copy("x8006", "x7771")
      val x7832_unit = CounterChain(name = "x7832_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x7771(0)), CU.ctr(x7814(0))), op=FixAdd, results=List(x7817))
      Stage(operands=List(x7817, Const(6)), op=FixSla, results=List(x7819))
      Stage(operands=List(x7819, CU.ctr(x7704(0))), op=FixAdd, results=List(x7821))
      Stage(operands=List(x7821, Const(3)), op=FixSla, results=List(x7822))
      Stage(operands=List(x7822, CU.load(x7824)), op=FixAdd, results=List(CU.scalarOut(x7815_b9567_x7831_b9569_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x7815_b9568_x7831_b9570_s)))
    }
    val x7833 = MemoryController(name="x7833",parent=x7844,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x7815_b9568 = ScalarFIFO(size=1,name="size").wtPort(x7815_b9568_x7831_b9570_s)
      val x7815_b9567 = ScalarFIFO(size=1,name="offset").wtPort(x7815_b9567_x7831_b9569_s)
      CU.newSout("data", x7816_x7833_data_s)
    }
    val x7843 = Pipeline(name="x7843",parent=x7844) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7835 = CounterChain(name = "x7835", ctr11).iter(16)
    }
    val x7846_dsp0_bank0 = MemoryPipeline(name="x7846_dsp0_bank0",parent="x7916") { implicit CU => 
      val x7914 = ScalarFIFO(size=1,name="x7914").wtPort(x7846_x7914_s)
      val x7893 = CounterChain.copy("x7915_0", "x7893")
      val x7846 = SRAM(size=16,name="x7846",banking = Strided(1)).wtPort(x7914.readPort).rdPort(x7846_x7846_dsp0_bank0_s).rdAddr(x7893(0)).wtAddr(x7893(0))
    }
    val x7846_dsp1_bank0 = MemoryPipeline(name="x7846_dsp1_bank0",parent="x7916") { implicit CU => 
      val x7914 = ScalarFIFO(size=1,name="x7914").wtPort(x7846_x7914_s)
      val x7893 = CounterChain.copy("x7915_0", "x7893")
      val x7988 = CounterChain.copy("x7995", "x7988")
      val x7846 = SRAM(size=16,name="x7846",banking = Strided(1)).wtPort(x7914.readPort).rdPort(x7846_x7846_dsp1_bank0_s).rdAddr(x7988(0)).wtAddr(x7893(0))
    }
    val x7847_dsp0_bank0 = MemoryPipeline(name="x7847_dsp0_bank0",parent="x7985") { implicit CU => 
      val x7983 = ScalarFIFO(size=1,name="x7983").wtPort(x7847_x7983_s)
      val x7962 = CounterChain.copy("x7984_0", "x7962")
      val x7847 = SRAM(size=16,name="x7847",banking = Strided(1)).wtPort(x7983.readPort).rdPort(x7847_x7847_dsp0_bank0_s).rdAddr(x7962(0)).wtAddr(x7962(0))
    }
    val x7847_dsp1_bank0 = MemoryPipeline(name="x7847_dsp1_bank0",parent="x7985") { implicit CU => 
      val x7983 = ScalarFIFO(size=1,name="x7983").wtPort(x7847_x7983_s)
      val x7997 = CounterChain.copy("x8004", "x7997")
      val x7962 = CounterChain.copy("x7984_0", "x7962")
      val x7847 = SRAM(size=16,name="x7847",banking = Strided(1)).wtPort(x7983.readPort).rdPort(x7847_x7847_dsp1_bank0_s).rdAddr(x7997(0)).wtAddr(x7962(0))
    }
    val x7916 = MetaPipeline(name="x7916",parent=x8006) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x7849 = CounterChain(name = "x7849", ctr12).iter(8)
    }
    val x7850_dsp0_bank0 = MemoryPipeline(name="x7850_dsp0_bank0",parent="x7916") { implicit CU => 
      val x7877 = ScalarFIFO(size=1,name="x7877").wtPort(x7850_x7877_s)
      val x7868 = CounterChain.copy("x7878_0", "x7868")
      val x7893 = CounterChain.copy("x7915_0", "x7893")
      val x7850 = SRAM(size=16,name="x7850",banking = Strided(1)).wtPort(x7877.readPort).rdPort(x7850_x7850_dsp0_bank0_s).rdAddr(x7893(0)).wtAddr(x7868(0))
    }
    val x7851_dsp0_bank0 = MemoryPipeline(name="x7851_dsp0_bank0",parent="x7916") { implicit CU => 
      val x7889 = ScalarFIFO(size=1,name="x7889").wtPort(x7851_x7889_s)
      val x7880 = CounterChain.copy("x7890_0", "x7880")
      val x7893 = CounterChain.copy("x7915_0", "x7893")
      val x7851 = SRAM(size=16,name="x7851",banking = Strided(1)).wtPort(x7889.readPort).rdPort(x7851_x7851_dsp0_bank0_s).rdAddr(x7893(0)).wtAddr(x7880(0))
    }
    val x7878_0 = Pipeline(name="x7878_0",parent=x7916) { implicit CU => 
      val x7873 = ScalarFIFO(size=1,name="x7873").wtPort(x7707_x7707_dsp0_bank0_s)
      val x7852 = ScalarBuffer(name="x7852").wtPort(x7772_x7772_dsp0_bank0_s)
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7868 = CounterChain(name = "x7868", ctr13).iter(1)
      Stage(operands=List(CU.load(x7873), CU.load(x7852)), op=FixMul, results=List(CU.scalarOut(x7850_x7877_s)))
    }
    val x7890_0 = Pipeline(name="x7890_0",parent=x7916) { implicit CU => 
      val x7853 = ScalarBuffer(name="x7853").wtPort(x7772_x7772_dsp0_bank0_s)
      val x7885 = ScalarFIFO(size=1,name="x7885").wtPort(x7707_x7707_dsp0_bank1_s)
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7880 = CounterChain(name = "x7880", ctr14).iter(1)
      Stage(operands=List(CU.load(x7885), CU.load(x7853)), op=FixMul, results=List(CU.scalarOut(x7851_x7889_s)))
    }
    val x7915_0 = Pipeline(name="x7915_0",parent=x7916) { implicit CU => 
      val x7909 = CU.temp(None)
      val x7897 = ScalarFIFO(size=1,name="x7897").wtPort(x7850_x7850_dsp0_bank0_s)
      val x7899 = ScalarFIFO(size=1,name="x7899").wtPort(x7851_x7851_dsp0_bank0_s)
      val x7901 = ScalarFIFO(size=1,name="x7901").wtPort(x7846_x7846_dsp0_bank0_s)
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7893 = CounterChain(name = "x7893", ctr15).iter(1)
      Stage(operands=List(CU.load(x7897), CU.load(x7899)), op=FixAdd, results=List(x7909))
      Stage(operands=List(x7909, CU.load(x7901)), op=FixAdd, results=List(CU.scalarOut(x7846_x7914_s)))
    }
    val x7985 = MetaPipeline(name="x7985",parent=x8006) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x7918 = CounterChain(name = "x7918", ctr16).iter(8)
    }
    val x7919_dsp0_bank0 = MemoryPipeline(name="x7919_dsp0_bank0",parent="x7985") { implicit CU => 
      val x7946 = ScalarFIFO(size=1,name="x7946").wtPort(x7919_x7946_s)
      val x7937 = CounterChain.copy("x7947_0", "x7937")
      val x7962 = CounterChain.copy("x7984_0", "x7962")
      val x7919 = SRAM(size=16,name="x7919",banking = Strided(1)).wtPort(x7946.readPort).rdPort(x7919_x7919_dsp0_bank0_s).rdAddr(x7962(0)).wtAddr(x7937(0))
    }
    val x7920_dsp0_bank0 = MemoryPipeline(name="x7920_dsp0_bank0",parent="x7985") { implicit CU => 
      val x7958 = ScalarFIFO(size=1,name="x7958").wtPort(x7920_x7958_s)
      val x7949 = CounterChain.copy("x7959_0", "x7949")
      val x7962 = CounterChain.copy("x7984_0", "x7962")
      val x7920 = SRAM(size=16,name="x7920",banking = Strided(1)).wtPort(x7958.readPort).rdPort(x7920_x7920_dsp0_bank0_s).rdAddr(x7962(0)).wtAddr(x7949(0))
    }
    val x7947_0 = Pipeline(name="x7947_0",parent=x7985) { implicit CU => 
      val x7942 = ScalarFIFO(size=1,name="x7942").wtPort(x7707_x7707_dsp1_bank0_s)
      val x7921 = ScalarBuffer(name="x7921").wtPort(x7773_x7773_dsp0_bank0_s)
      val ctr17 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7937 = CounterChain(name = "x7937", ctr17).iter(1)
      Stage(operands=List(CU.load(x7942), CU.load(x7921)), op=FixMul, results=List(CU.scalarOut(x7919_x7946_s)))
    }
    val x7959_0 = Pipeline(name="x7959_0",parent=x7985) { implicit CU => 
      val x7954 = ScalarFIFO(size=1,name="x7954").wtPort(x7707_x7707_dsp1_bank1_s)
      val x7922 = ScalarBuffer(name="x7922").wtPort(x7773_x7773_dsp0_bank0_s)
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7949 = CounterChain(name = "x7949", ctr18).iter(1)
      Stage(operands=List(CU.load(x7954), CU.load(x7922)), op=FixMul, results=List(CU.scalarOut(x7920_x7958_s)))
    }
    val x7984_0 = Pipeline(name="x7984_0",parent=x7985) { implicit CU => 
      val x7978 = CU.temp(None)
      val x7966 = ScalarFIFO(size=1,name="x7966").wtPort(x7919_x7919_dsp0_bank0_s)
      val x7968 = ScalarFIFO(size=1,name="x7968").wtPort(x7920_x7920_dsp0_bank0_s)
      val x7970 = ScalarFIFO(size=1,name="x7970").wtPort(x7847_x7847_dsp0_bank0_s)
      val ctr19 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x7962 = CounterChain(name = "x7962", ctr19).iter(1)
      Stage(operands=List(CU.load(x7966), CU.load(x7968)), op=FixAdd, results=List(x7978))
      Stage(operands=List(x7978, CU.load(x7970)), op=FixAdd, results=List(CU.scalarOut(x7847_x7983_s)))
    }
    val x7995 = Pipeline(name="x7995",parent=x8006) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7988 = CounterChain(name = "x7988", ctr20).iter(16)
    }
    val x8004 = Pipeline(name="x8004",parent=x8006) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x7997 = CounterChain(name = "x7997", ctr21).iter(16)
    }
    val x8243 = MetaPipeline(name="x8243",parent=x8267) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x8008 = CounterChain(name = "x8008", ctr22).iter(32)
    }
    val x8009_dsp0_bank0 = MemoryPipeline(name="x8009_dsp0_bank0",parent="x8243") { implicit CU => 
      val x8047 = ScalarFIFO(size=1,name="x8047").wtPort(x8021_x8038_data_s)
      val x8086 = CounterChain.copy("x8153", "x8086")
      val x8040 = CounterChain.copy("x8048", "x8040")
      val x8009 = SRAM(size=16,name="x8009",banking = Strided(1)).wtPort(x8047.readPort).rdPort(x8009_x8009_dsp0_bank0_s).rdAddr(x8086(0)).rdAddr(x8086(0)).wtAddr(x8040(0))
    }
    val x8010_dsp0_bank0 = MemoryPipeline(name="x8010_dsp0_bank0",parent="x8243") { implicit CU => 
      val x8079 = ScalarFIFO(size=1,name="x8079").wtPort(x8053_x8070_data_s)
      val x8072 = CounterChain.copy("x8080", "x8072")
      val x8155 = CounterChain.copy("x8222", "x8155")
      val x8010 = SRAM(size=16,name="x8010",banking = Strided(1)).wtPort(x8079.readPort).rdPort(x8010_x8010_dsp0_bank0_s).rdAddr(x8155(0)).rdAddr(x8155(0)).wtAddr(x8072(0))
    }
    val x8049 = StreamController(name="x8049",parent=x8243) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8019 = CounterChain(name = "x8019", ctr23).iter(1)
    }
    val x8037_0 = Pipeline(name="x8037_0",parent=x8049) { implicit CU => 
      val x8027 = CU.temp(None)
      val x8026 = CU.temp(None)
      val x8022 = CU.temp(None)
      val x8024 = CU.temp(None)
      val x8029 = ScalarBuffer(name="x8029").wtPort(a_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x8019 = CounterChain.copy("x8049", "x8019")
      val x8037_unit = CounterChain(name = "x8037_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8008 = CounterChain.copy("x8243", "x8008")
      Stage(operands=List(CU.ctr(x8008(0)), CU.ctr(x8019(0))), op=FixAdd, results=List(x8022))
      Stage(operands=List(x8022, Const(6)), op=FixSla, results=List(x8024))
      Stage(operands=List(x8024, CU.ctr(x7704(0))), op=FixAdd, results=List(x8026))
      Stage(operands=List(x8026, Const(3)), op=FixSla, results=List(x8027))
      Stage(operands=List(x8027, CU.load(x8029)), op=FixAdd, results=List(CU.scalarOut(x8020_b9583_x8036_b9585_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8020_b9584_x8036_b9586_s)))
    }
    val x8038 = MemoryController(name="x8038",parent=x8049,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8020_b9584 = ScalarFIFO(size=1,name="size").wtPort(x8020_b9584_x8036_b9586_s)
      val x8020_b9583 = ScalarFIFO(size=1,name="offset").wtPort(x8020_b9583_x8036_b9585_s)
      CU.newSout("data", x8021_x8038_data_s)
    }
    val x8048 = Pipeline(name="x8048",parent=x8049) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8040 = CounterChain(name = "x8040", ctr24).iter(16)
    }
    val x8081 = StreamController(name="x8081",parent=x8243) { implicit CU => 
      val ctr25 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8051 = CounterChain(name = "x8051", ctr25).iter(1)
    }
    val x8069_0 = Pipeline(name="x8069_0",parent=x8081) { implicit CU => 
      val x8059 = CU.temp(None)
      val x8056 = CU.temp(None)
      val x8058 = CU.temp(None)
      val x8054 = CU.temp(None)
      val x8061 = ScalarBuffer(name="x8061").wtPort(a_dram_da)
      val x7704 = CounterChain.copy("x8267", "x7704")
      val x8069_unit = CounterChain(name = "x8069_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8008 = CounterChain.copy("x8243", "x8008")
      val x8051 = CounterChain.copy("x8081", "x8051")
      Stage(operands=List(CU.ctr(x8008(0)), CU.ctr(x8051(0))), op=FixAdd, results=List(x8054))
      Stage(operands=List(x8054, Const(6)), op=FixSla, results=List(x8056))
      Stage(operands=List(x8056, CU.ctr(x7704(0))), op=FixAdd, results=List(x8058))
      Stage(operands=List(x8058, Const(3)), op=FixSla, results=List(x8059))
      Stage(operands=List(x8059, CU.load(x8061)), op=FixAdd, results=List(CU.scalarOut(x8052_b9587_x8068_b9589_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8052_b9588_x8068_b9590_s)))
    }
    val x8070 = MemoryController(name="x8070",parent=x8081,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8052_b9587 = ScalarFIFO(size=1,name="offset").wtPort(x8052_b9587_x8068_b9589_s)
      val x8052_b9588 = ScalarFIFO(size=1,name="size").wtPort(x8052_b9588_x8068_b9590_s)
      CU.newSout("data", x8053_x8070_data_s)
    }
    val x8080 = Pipeline(name="x8080",parent=x8081) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8072 = CounterChain(name = "x8072", ctr26).iter(16)
    }
    val x8083_dsp0_bank0 = MemoryPipeline(name="x8083_dsp0_bank0",parent="x8153") { implicit CU => 
      val x8151 = ScalarFIFO(size=1,name="x8151").wtPort(x8083_x8151_s)
      val x8130 = CounterChain.copy("x8152_0", "x8130")
      val x8083 = SRAM(size=16,name="x8083",banking = Strided(1)).wtPort(x8151.readPort).rdPort(x8083_x8083_dsp0_bank0_s).rdAddr(x8130(0)).wtAddr(x8130(0))
    }
    val x8083_dsp1_bank0 = MemoryPipeline(name="x8083_dsp1_bank0",parent="x8153") { implicit CU => 
      val x8151 = ScalarFIFO(size=1,name="x8151").wtPort(x8083_x8151_s)
      val x8130 = CounterChain.copy("x8152_0", "x8130")
      val x8225 = CounterChain.copy("x8232", "x8225")
      val x8083 = SRAM(size=16,name="x8083",banking = Strided(1)).wtPort(x8151.readPort).rdPort(x8083_x8083_dsp1_bank0_s).rdAddr(x8225(0)).wtAddr(x8130(0))
    }
    val x8084_dsp0_bank0 = MemoryPipeline(name="x8084_dsp0_bank0",parent="x8222") { implicit CU => 
      val x8220 = ScalarFIFO(size=1,name="x8220").wtPort(x8084_x8220_s)
      val x8199 = CounterChain.copy("x8221_0", "x8199")
      val x8084 = SRAM(size=16,name="x8084",banking = Strided(1)).wtPort(x8220.readPort).rdPort(x8084_x8084_dsp0_bank0_s).rdAddr(x8199(0)).wtAddr(x8199(0))
    }
    val x8084_dsp1_bank0 = MemoryPipeline(name="x8084_dsp1_bank0",parent="x8222") { implicit CU => 
      val x8220 = ScalarFIFO(size=1,name="x8220").wtPort(x8084_x8220_s)
      val x8199 = CounterChain.copy("x8221_0", "x8199")
      val x8234 = CounterChain.copy("x8241", "x8234")
      val x8084 = SRAM(size=16,name="x8084",banking = Strided(1)).wtPort(x8220.readPort).rdPort(x8084_x8084_dsp1_bank0_s).rdAddr(x8234(0)).wtAddr(x8199(0))
    }
    val x8153 = MetaPipeline(name="x8153",parent=x8243) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8086 = CounterChain(name = "x8086", ctr27).iter(8)
    }
    val x8087_dsp0_bank0 = MemoryPipeline(name="x8087_dsp0_bank0",parent="x8153") { implicit CU => 
      val x8114 = ScalarFIFO(size=1,name="x8114").wtPort(x8087_x8114_s)
      val x8130 = CounterChain.copy("x8152_0", "x8130")
      val x8105 = CounterChain.copy("x8115_0", "x8105")
      val x8087 = SRAM(size=16,name="x8087",banking = Strided(1)).wtPort(x8114.readPort).rdPort(x8087_x8087_dsp0_bank0_s).rdAddr(x8130(0)).wtAddr(x8105(0))
    }
    val x8088_dsp0_bank0 = MemoryPipeline(name="x8088_dsp0_bank0",parent="x8153") { implicit CU => 
      val x8126 = ScalarFIFO(size=1,name="x8126").wtPort(x8088_x8126_s)
      val x8117 = CounterChain.copy("x8127_0", "x8117")
      val x8130 = CounterChain.copy("x8152_0", "x8130")
      val x8088 = SRAM(size=16,name="x8088",banking = Strided(1)).wtPort(x8126.readPort).rdPort(x8088_x8088_dsp0_bank0_s).rdAddr(x8130(0)).wtAddr(x8117(0))
    }
    val x8115_0 = Pipeline(name="x8115_0",parent=x8153) { implicit CU => 
      val x8089 = ScalarBuffer(name="x8089").wtPort(x8009_x8009_dsp0_bank0_s)
      val x8110 = ScalarFIFO(size=1,name="x8110").wtPort(x7708_x7708_dsp0_bank0_s)
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8105 = CounterChain(name = "x8105", ctr28).iter(1)
      Stage(operands=List(CU.load(x8110), CU.load(x8089)), op=FixMul, results=List(CU.scalarOut(x8087_x8114_s)))
    }
    val x8127_0 = Pipeline(name="x8127_0",parent=x8153) { implicit CU => 
      val x8122 = ScalarFIFO(size=1,name="x8122").wtPort(x7708_x7708_dsp0_bank1_s)
      val x8090 = ScalarBuffer(name="x8090").wtPort(x8009_x8009_dsp0_bank0_s)
      val ctr29 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8117 = CounterChain(name = "x8117", ctr29).iter(1)
      Stage(operands=List(CU.load(x8122), CU.load(x8090)), op=FixMul, results=List(CU.scalarOut(x8088_x8126_s)))
    }
    val x8152_0 = Pipeline(name="x8152_0",parent=x8153) { implicit CU => 
      val x8146 = CU.temp(None)
      val x8134 = ScalarFIFO(size=1,name="x8134").wtPort(x8087_x8087_dsp0_bank0_s)
      val x8136 = ScalarFIFO(size=1,name="x8136").wtPort(x8088_x8088_dsp0_bank0_s)
      val x8138 = ScalarFIFO(size=1,name="x8138").wtPort(x8083_x8083_dsp0_bank0_s)
      val ctr30 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8130 = CounterChain(name = "x8130", ctr30).iter(1)
      Stage(operands=List(CU.load(x8134), CU.load(x8136)), op=FixAdd, results=List(x8146))
      Stage(operands=List(x8146, CU.load(x8138)), op=FixAdd, results=List(CU.scalarOut(x8083_x8151_s)))
    }
    val x8222 = MetaPipeline(name="x8222",parent=x8243) { implicit CU => 
      val ctr31 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8155 = CounterChain(name = "x8155", ctr31).iter(8)
    }
    val x8156_dsp0_bank0 = MemoryPipeline(name="x8156_dsp0_bank0",parent="x8222") { implicit CU => 
      val x8183 = ScalarFIFO(size=1,name="x8183").wtPort(x8156_x8183_s)
      val x8174 = CounterChain.copy("x8184_0", "x8174")
      val x8199 = CounterChain.copy("x8221_0", "x8199")
      val x8156 = SRAM(size=16,name="x8156",banking = Strided(1)).wtPort(x8183.readPort).rdPort(x8156_x8156_dsp0_bank0_s).rdAddr(x8199(0)).wtAddr(x8174(0))
    }
    val x8157_dsp0_bank0 = MemoryPipeline(name="x8157_dsp0_bank0",parent="x8222") { implicit CU => 
      val x8195 = ScalarFIFO(size=1,name="x8195").wtPort(x8157_x8195_s)
      val x8186 = CounterChain.copy("x8196_0", "x8186")
      val x8199 = CounterChain.copy("x8221_0", "x8199")
      val x8157 = SRAM(size=16,name="x8157",banking = Strided(1)).wtPort(x8195.readPort).rdPort(x8157_x8157_dsp0_bank0_s).rdAddr(x8199(0)).wtAddr(x8186(0))
    }
    val x8184_0 = Pipeline(name="x8184_0",parent=x8222) { implicit CU => 
      val x8158 = ScalarBuffer(name="x8158").wtPort(x8010_x8010_dsp0_bank0_s)
      val x8179 = ScalarFIFO(size=1,name="x8179").wtPort(x7708_x7708_dsp1_bank0_s)
      val ctr32 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8174 = CounterChain(name = "x8174", ctr32).iter(1)
      Stage(operands=List(CU.load(x8179), CU.load(x8158)), op=FixMul, results=List(CU.scalarOut(x8156_x8183_s)))
    }
    val x8196_0 = Pipeline(name="x8196_0",parent=x8222) { implicit CU => 
      val x8159 = ScalarBuffer(name="x8159").wtPort(x8010_x8010_dsp0_bank0_s)
      val x8191 = ScalarFIFO(size=1,name="x8191").wtPort(x7708_x7708_dsp1_bank1_s)
      val ctr33 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8186 = CounterChain(name = "x8186", ctr33).iter(1)
      Stage(operands=List(CU.load(x8191), CU.load(x8159)), op=FixMul, results=List(CU.scalarOut(x8157_x8195_s)))
    }
    val x8221_0 = Pipeline(name="x8221_0",parent=x8222) { implicit CU => 
      val x8215 = CU.temp(None)
      val x8203 = ScalarFIFO(size=1,name="x8203").wtPort(x8156_x8156_dsp0_bank0_s)
      val x8205 = ScalarFIFO(size=1,name="x8205").wtPort(x8157_x8157_dsp0_bank0_s)
      val x8207 = ScalarFIFO(size=1,name="x8207").wtPort(x8084_x8084_dsp0_bank0_s)
      val ctr34 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8199 = CounterChain(name = "x8199", ctr34).iter(1)
      Stage(operands=List(CU.load(x8203), CU.load(x8205)), op=FixAdd, results=List(x8215))
      Stage(operands=List(x8215, CU.load(x8207)), op=FixAdd, results=List(CU.scalarOut(x8084_x8220_s)))
    }
    val x8232 = Pipeline(name="x8232",parent=x8243) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8225 = CounterChain(name = "x8225", ctr35).iter(16)
    }
    val x8241 = Pipeline(name="x8241",parent=x8243) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8234 = CounterChain(name = "x8234", ctr36).iter(16)
    }
    val x8266_0 = Pipeline(name="x8266_0",parent=x8267) { implicit CU => 
      val x8260 = CU.temp(None)
      val x8250 = ScalarFIFO(size=1,name="x8250").wtPort(x7705_x7705_dsp0_bank0_s).wtPort(x7705_x7705_dsp0_bank1_s)
      val x8252 = ScalarFIFO(size=1,name="x8252").wtPort(x7706_x7706_dsp0_bank0_s).wtPort(x7706_x7706_dsp0_bank1_s)
      val x8254 = ScalarFIFO(size=1,name="x8254").wtPort(x7700_x7700_dsp0_bank0_s)
      val ctr37 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr38 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8247 = CounterChain(name = "x8247", ctr37, ctr38).iter(64)
      Stage(operands=List(CU.load(x8250), CU.load(x8252)), op=FixAdd, results=List(x8260))
      Stage(operands=List(x8260, CU.load(x8254)), op=FixAdd, results=List(CU.scalarOut(x7700_x8265_s)))
    }
    val x8832 = MetaPipeline(name="x8832",parent=x9503) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x8269 = CounterChain(name = "x8269", ctr39).iter(2)
    }
    val x8270_dsp0_bank0 = MemoryPipeline(name="x8270_dsp0_bank0",parent="x8832") { implicit CU => 
      val b9639 = CU.temp(None)
      val b9663 = CU.temp(None)
      val x8559 = ScalarFIFO(size=1,name="x8559").wtPort(x8411_x8411_dsp1_bank0_s)
      val x8553 = CounterChain.copy("x8560", "x8553")
      val x8336 = CounterChain.copy("x8571", "x8336")
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x8270 = SRAM(size=1024,name="x8270",banking = Strided(1)).wtPort(x8559.readPort).rdPort(x8270_x8270_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8336(0)), Const(16)), op=FixMul, results=List(b9639))
      WAStage(operands=List(b9639, CU.ctr(x8553(0))), op=FixAdd, results=List(x8270.writeAddr))
      RAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9663))
      RAStage(operands=List(b9663, CU.ctr(x8812(1))), op=FixAdd, results=List(x8270.readAddr))
    }
    val x8270_dsp0_bank1 = MemoryPipeline(name="x8270_dsp0_bank1",parent="x8832") { implicit CU => 
      val b9641 = CU.temp(None)
      val b9663 = CU.temp(None)
      val x8568 = ScalarFIFO(size=1,name="x8568").wtPort(x8412_x8412_dsp1_bank0_s)
      val x8336 = CounterChain.copy("x8571", "x8336")
      val x8562 = CounterChain.copy("x8569", "x8562")
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x8270 = SRAM(size=1024,name="x8270",banking = Strided(1)).wtPort(x8568.readPort).rdPort(x8270_x8270_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8336(0)), Const(16)), op=FixMul, results=List(b9641))
      WAStage(operands=List(b9641, CU.ctr(x8562(0))), op=FixAdd, results=List(x8270.writeAddr))
      RAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9663))
      RAStage(operands=List(b9663, CU.ctr(x8812(1))), op=FixAdd, results=List(x8270.readAddr))
    }
    val x8271_dsp0_bank0 = MemoryPipeline(name="x8271_dsp0_bank0",parent="x8832") { implicit CU => 
      val b9659 = CU.temp(None)
      val b9665 = CU.temp(None)
      val x8796 = ScalarFIFO(size=1,name="x8796").wtPort(x8648_x8648_dsp1_bank0_s)
      val x8573 = CounterChain.copy("x8808", "x8573")
      val x8790 = CounterChain.copy("x8797", "x8790")
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x8271 = SRAM(size=1024,name="x8271",banking = Strided(1)).wtPort(x8796.readPort).rdPort(x8271_x8271_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8573(0)), Const(16)), op=FixMul, results=List(b9659))
      WAStage(operands=List(b9659, CU.ctr(x8790(0))), op=FixAdd, results=List(x8271.writeAddr))
      RAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9665))
      RAStage(operands=List(b9665, CU.ctr(x8812(1))), op=FixAdd, results=List(x8271.readAddr))
    }
    val x8271_dsp0_bank1 = MemoryPipeline(name="x8271_dsp0_bank1",parent="x8832") { implicit CU => 
      val b9661 = CU.temp(None)
      val b9665 = CU.temp(None)
      val x8805 = ScalarFIFO(size=1,name="x8805").wtPort(x8649_x8649_dsp1_bank0_s)
      val x8573 = CounterChain.copy("x8808", "x8573")
      val x8799 = CounterChain.copy("x8806", "x8799")
      val x8812 = CounterChain.copy("x8831_0", "x8812")
      val x8271 = SRAM(size=1024,name="x8271",banking = Strided(1)).wtPort(x8805.readPort).rdPort(x8271_x8271_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8573(0)), Const(16)), op=FixMul, results=List(b9661))
      WAStage(operands=List(b9661, CU.ctr(x8799(0))), op=FixAdd, results=List(x8271.writeAddr))
      RAStage(operands=List(CU.ctr(x8812(0)), Const(16)), op=FixMul, results=List(b9665))
      RAStage(operands=List(b9665, CU.ctr(x8812(1))), op=FixAdd, results=List(x8271.readAddr))
    }
    val x8272_dsp0_bank0 = MemoryPipeline(name="x8272_dsp0_bank0",parent="x8832") { implicit CU => 
      val b9631 = CU.temp(None)
      val b9615 = CU.temp(None)
      val x8301 = ScalarFIFO(size=1,name="x8301").wtPort(x8277_x8293_data_s)
      val x8433 = CounterChain.copy("x8443_0", "x8433")
      val x8414 = CounterChain.copy("x8481", "x8414")
      val x8295 = CounterChain.copy("x8302", "x8295")
      val x8275 = CounterChain.copy("x8303", "x8275")
      val x8272 = SRAM(size=256,name="x8272",banking = Strided(1)).wtPort(x8301.readPort).rdPort(x8272_x8272_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8275(0)), Const(16)), op=FixMul, results=List(b9615))
      WAStage(operands=List(b9615, CU.ctr(x8295(0))), op=FixAdd, results=List(x8272.writeAddr))
      RAStage(operands=List(CU.ctr(x8414(0)), Const(16)), op=FixMul, results=List(b9631))
      RAStage(operands=List(b9631, CU.ctr(x8433(0))), op=FixAdd, results=List(x8272.readAddr))
    }
    val x8272_dsp0_bank1 = MemoryPipeline(name="x8272_dsp0_bank1",parent="x8832") { implicit CU => 
      val b9633 = CU.temp(None)
      val b9615 = CU.temp(None)
      val x8301 = ScalarFIFO(size=1,name="x8301").wtPort(x8277_x8293_data_s)
      val x8414 = CounterChain.copy("x8481", "x8414")
      val x8295 = CounterChain.copy("x8302", "x8295")
      val x8445 = CounterChain.copy("x8455_0", "x8445")
      val x8275 = CounterChain.copy("x8303", "x8275")
      val x8272 = SRAM(size=256,name="x8272",banking = Strided(1)).wtPort(x8301.readPort).rdPort(x8272_x8272_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8275(0)), Const(16)), op=FixMul, results=List(b9615))
      WAStage(operands=List(b9615, CU.ctr(x8295(0))), op=FixAdd, results=List(x8272.writeAddr))
      RAStage(operands=List(CU.ctr(x8414(0)), Const(16)), op=FixMul, results=List(b9633))
      RAStage(operands=List(b9633, CU.ctr(x8445(0))), op=FixAdd, results=List(x8272.readAddr))
    }
    val x8272_dsp1_bank0 = MemoryPipeline(name="x8272_dsp1_bank0",parent="x8832") { implicit CU => 
      val b9635 = CU.temp(None)
      val b9615 = CU.temp(None)
      val x8301 = ScalarFIFO(size=1,name="x8301").wtPort(x8277_x8293_data_s)
      val x8502 = CounterChain.copy("x8512_0", "x8502")
      val x8483 = CounterChain.copy("x8550", "x8483")
      val x8295 = CounterChain.copy("x8302", "x8295")
      val x8275 = CounterChain.copy("x8303", "x8275")
      val x8272 = SRAM(size=256,name="x8272",banking = Strided(1)).wtPort(x8301.readPort).rdPort(x8272_x8272_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8275(0)), Const(16)), op=FixMul, results=List(b9615))
      WAStage(operands=List(b9615, CU.ctr(x8295(0))), op=FixAdd, results=List(x8272.writeAddr))
      RAStage(operands=List(CU.ctr(x8483(0)), Const(16)), op=FixMul, results=List(b9635))
      RAStage(operands=List(b9635, CU.ctr(x8502(0))), op=FixAdd, results=List(x8272.readAddr))
    }
    val x8272_dsp1_bank1 = MemoryPipeline(name="x8272_dsp1_bank1",parent="x8832") { implicit CU => 
      val b9637 = CU.temp(None)
      val b9615 = CU.temp(None)
      val x8301 = ScalarFIFO(size=1,name="x8301").wtPort(x8277_x8293_data_s)
      val x8483 = CounterChain.copy("x8550", "x8483")
      val x8295 = CounterChain.copy("x8302", "x8295")
      val x8514 = CounterChain.copy("x8524_0", "x8514")
      val x8275 = CounterChain.copy("x8303", "x8275")
      val x8272 = SRAM(size=256,name="x8272",banking = Strided(1)).wtPort(x8301.readPort).rdPort(x8272_x8272_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x8275(0)), Const(16)), op=FixMul, results=List(b9615))
      WAStage(operands=List(b9615, CU.ctr(x8295(0))), op=FixAdd, results=List(x8272.writeAddr))
      RAStage(operands=List(CU.ctr(x8483(0)), Const(16)), op=FixMul, results=List(b9637))
      RAStage(operands=List(b9637, CU.ctr(x8514(0))), op=FixAdd, results=List(x8272.readAddr))
    }
    val x8273_dsp0_bank0 = MemoryPipeline(name="x8273_dsp0_bank0",parent="x8832") { implicit CU => 
      val b9621 = CU.temp(None)
      val b9651 = CU.temp(None)
      val x8331 = ScalarFIFO(size=1,name="x8331").wtPort(x8307_x8323_data_s)
      val x8305 = CounterChain.copy("x8333", "x8305")
      val x8325 = CounterChain.copy("x8332", "x8325")
      val x8670 = CounterChain.copy("x8680_0", "x8670")
      val x8651 = CounterChain.copy("x8718", "x8651")
      val x8273 = SRAM(size=256,name="x8273",banking = Strided(1)).wtPort(x8331.readPort).rdPort(x8273_x8273_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8305(0)), Const(16)), op=FixMul, results=List(b9621))
      WAStage(operands=List(b9621, CU.ctr(x8325(0))), op=FixAdd, results=List(x8273.writeAddr))
      RAStage(operands=List(CU.ctr(x8651(0)), Const(16)), op=FixMul, results=List(b9651))
      RAStage(operands=List(b9651, CU.ctr(x8670(0))), op=FixAdd, results=List(x8273.readAddr))
    }
    val x8273_dsp0_bank1 = MemoryPipeline(name="x8273_dsp0_bank1",parent="x8832") { implicit CU => 
      val b9621 = CU.temp(None)
      val b9653 = CU.temp(None)
      val x8331 = ScalarFIFO(size=1,name="x8331").wtPort(x8307_x8323_data_s)
      val x8305 = CounterChain.copy("x8333", "x8305")
      val x8325 = CounterChain.copy("x8332", "x8325")
      val x8682 = CounterChain.copy("x8692_0", "x8682")
      val x8651 = CounterChain.copy("x8718", "x8651")
      val x8273 = SRAM(size=256,name="x8273",banking = Strided(1)).wtPort(x8331.readPort).rdPort(x8273_x8273_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8305(0)), Const(16)), op=FixMul, results=List(b9621))
      WAStage(operands=List(b9621, CU.ctr(x8325(0))), op=FixAdd, results=List(x8273.writeAddr))
      RAStage(operands=List(CU.ctr(x8651(0)), Const(16)), op=FixMul, results=List(b9653))
      RAStage(operands=List(b9653, CU.ctr(x8682(0))), op=FixAdd, results=List(x8273.readAddr))
    }
    val x8273_dsp1_bank0 = MemoryPipeline(name="x8273_dsp1_bank0",parent="x8832") { implicit CU => 
      val b9621 = CU.temp(None)
      val b9655 = CU.temp(None)
      val x8331 = ScalarFIFO(size=1,name="x8331").wtPort(x8307_x8323_data_s)
      val x8739 = CounterChain.copy("x8749_0", "x8739")
      val x8720 = CounterChain.copy("x8787", "x8720")
      val x8305 = CounterChain.copy("x8333", "x8305")
      val x8325 = CounterChain.copy("x8332", "x8325")
      val x8273 = SRAM(size=256,name="x8273",banking = Strided(1)).wtPort(x8331.readPort).rdPort(x8273_x8273_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8305(0)), Const(16)), op=FixMul, results=List(b9621))
      WAStage(operands=List(b9621, CU.ctr(x8325(0))), op=FixAdd, results=List(x8273.writeAddr))
      RAStage(operands=List(CU.ctr(x8720(0)), Const(16)), op=FixMul, results=List(b9655))
      RAStage(operands=List(b9655, CU.ctr(x8739(0))), op=FixAdd, results=List(x8273.readAddr))
    }
    val x8273_dsp1_bank1 = MemoryPipeline(name="x8273_dsp1_bank1",parent="x8832") { implicit CU => 
      val b9621 = CU.temp(None)
      val b9657 = CU.temp(None)
      val x8331 = ScalarFIFO(size=1,name="x8331").wtPort(x8307_x8323_data_s)
      val x8751 = CounterChain.copy("x8761_0", "x8751")
      val x8720 = CounterChain.copy("x8787", "x8720")
      val x8305 = CounterChain.copy("x8333", "x8305")
      val x8325 = CounterChain.copy("x8332", "x8325")
      val x8273 = SRAM(size=256,name="x8273",banking = Strided(1)).wtPort(x8331.readPort).rdPort(x8273_x8273_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x8305(0)), Const(16)), op=FixMul, results=List(b9621))
      WAStage(operands=List(b9621, CU.ctr(x8325(0))), op=FixAdd, results=List(x8273.writeAddr))
      RAStage(operands=List(CU.ctr(x8720(0)), Const(16)), op=FixMul, results=List(b9657))
      RAStage(operands=List(b9657, CU.ctr(x8751(0))), op=FixAdd, results=List(x8273.readAddr))
    }
    val x8303 = StreamController(name="x8303",parent=x8832) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8275 = CounterChain(name = "x8275", ctr40).iter(16)
    }
    val x8292_0 = Pipeline(name="x8292_0",parent=x8303) { implicit CU => 
      val x8278 = CU.temp(None)
      val x8283 = CU.temp(None)
      val x8282 = CU.temp(None)
      val x8280 = CU.temp(None)
      val x8285 = ScalarBuffer(name="x8285").wtPort(b_dram_da)
      val x8269 = CounterChain.copy("x8832", "x8269")
      val x8275 = CounterChain.copy("x8303", "x8275")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x8292_unit = CounterChain(name = "x8292_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8269(0)), CU.ctr(x8275(0))), op=FixAdd, results=List(x8278))
      Stage(operands=List(x8278, Const(6)), op=FixSla, results=List(x8280))
      Stage(operands=List(x8280, CU.ctr(x7699(0))), op=FixAdd, results=List(x8282))
      Stage(operands=List(x8282, Const(3)), op=FixSla, results=List(x8283))
      Stage(operands=List(x8283, CU.load(x8285)), op=FixAdd, results=List(CU.scalarOut(x8276_b9611_x8291_b9613_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8276_b9612_x8291_b9614_s)))
    }
    val x8293 = MemoryController(name="x8293",parent=x8303,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8276_b9611 = ScalarFIFO(size=1,name="offset").wtPort(x8276_b9611_x8291_b9613_s)
      val x8276_b9612 = ScalarFIFO(size=1,name="size").wtPort(x8276_b9612_x8291_b9614_s)
      CU.newSout("data", x8277_x8293_data_s)
    }
    val x8302 = Pipeline(name="x8302",parent=x8303) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8295 = CounterChain(name = "x8295", ctr41).iter(1)
    }
    val x8333 = StreamController(name="x8333",parent=x8832) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8305 = CounterChain(name = "x8305", ctr42).iter(16)
    }
    val x8322_0 = Pipeline(name="x8322_0",parent=x8333) { implicit CU => 
      val x8313 = CU.temp(None)
      val x8310 = CU.temp(None)
      val x8308 = CU.temp(None)
      val x8312 = CU.temp(None)
      val x8315 = ScalarBuffer(name="x8315").wtPort(b_dram_da)
      val x8269 = CounterChain.copy("x8832", "x8269")
      val x8305 = CounterChain.copy("x8333", "x8305")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x8322_unit = CounterChain(name = "x8322_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8269(0)), CU.ctr(x8305(0))), op=FixAdd, results=List(x8308))
      Stage(operands=List(x8308, Const(6)), op=FixSla, results=List(x8310))
      Stage(operands=List(x8310, CU.ctr(x7699(0))), op=FixAdd, results=List(x8312))
      Stage(operands=List(x8312, Const(3)), op=FixSla, results=List(x8313))
      Stage(operands=List(x8313, CU.load(x8315)), op=FixAdd, results=List(CU.scalarOut(x8306_b9617_x8321_b9619_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8306_b9618_x8321_b9620_s)))
    }
    val x8323 = MemoryController(name="x8323",parent=x8333,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8306_b9617 = ScalarFIFO(size=1,name="offset").wtPort(x8306_b9617_x8321_b9619_s)
      val x8306_b9618 = ScalarFIFO(size=1,name="size").wtPort(x8306_b9618_x8321_b9620_s)
      CU.newSout("data", x8307_x8323_data_s)
    }
    val x8332 = Pipeline(name="x8332",parent=x8333) { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8325 = CounterChain(name = "x8325", ctr43).iter(1)
    }
    val x8571 = MetaPipeline(name="x8571",parent=x8832) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x8336 = CounterChain(name = "x8336", ctr44).iter(32)
    }
    val x8337_dsp0_bank0 = MemoryPipeline(name="x8337_dsp0_bank0",parent="x8571") { implicit CU => 
      val x8375 = ScalarFIFO(size=1,name="x8375").wtPort(x8349_x8366_data_s)
      val x8368 = CounterChain.copy("x8376", "x8368")
      val x8414 = CounterChain.copy("x8481", "x8414")
      val x8337 = SRAM(size=16,name="x8337",banking = Strided(1)).wtPort(x8375.readPort).rdPort(x8337_x8337_dsp0_bank0_s).rdAddr(x8414(0)).rdAddr(x8414(0)).wtAddr(x8368(0))
    }
    val x8338_dsp0_bank0 = MemoryPipeline(name="x8338_dsp0_bank0",parent="x8571") { implicit CU => 
      val x8407 = ScalarFIFO(size=1,name="x8407").wtPort(x8381_x8398_data_s)
      val x8400 = CounterChain.copy("x8408", "x8400")
      val x8483 = CounterChain.copy("x8550", "x8483")
      val x8338 = SRAM(size=16,name="x8338",banking = Strided(1)).wtPort(x8407.readPort).rdPort(x8338_x8338_dsp0_bank0_s).rdAddr(x8483(0)).rdAddr(x8483(0)).wtAddr(x8400(0))
    }
    val x8377 = StreamController(name="x8377",parent=x8571) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8347 = CounterChain(name = "x8347", ctr45).iter(1)
    }
    val x8365_0 = Pipeline(name="x8365_0",parent=x8377) { implicit CU => 
      val x8352 = CU.temp(None)
      val x8354 = CU.temp(None)
      val x8355 = CU.temp(None)
      val x8350 = CU.temp(None)
      val x8357 = ScalarBuffer(name="x8357").wtPort(a_dram_da)
      val x8269 = CounterChain.copy("x8832", "x8269")
      val x8365_unit = CounterChain(name = "x8365_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8347 = CounterChain.copy("x8377", "x8347")
      val x8336 = CounterChain.copy("x8571", "x8336")
      Stage(operands=List(CU.ctr(x8336(0)), CU.ctr(x8347(0))), op=FixAdd, results=List(x8350))
      Stage(operands=List(x8350, Const(6)), op=FixSla, results=List(x8352))
      Stage(operands=List(x8352, CU.ctr(x8269(0))), op=FixAdd, results=List(x8354))
      Stage(operands=List(x8354, Const(3)), op=FixSla, results=List(x8355))
      Stage(operands=List(x8355, CU.load(x8357)), op=FixAdd, results=List(CU.scalarOut(x8348_b9623_x8364_b9625_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8348_b9624_x8364_b9626_s)))
    }
    val x8366 = MemoryController(name="x8366",parent=x8377,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8348_b9623 = ScalarFIFO(size=1,name="offset").wtPort(x8348_b9623_x8364_b9625_s)
      val x8348_b9624 = ScalarFIFO(size=1,name="size").wtPort(x8348_b9624_x8364_b9626_s)
      CU.newSout("data", x8349_x8366_data_s)
    }
    val x8376 = Pipeline(name="x8376",parent=x8377) { implicit CU => 
      val ctr46 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8368 = CounterChain(name = "x8368", ctr46).iter(16)
    }
    val x8409 = StreamController(name="x8409",parent=x8571) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8379 = CounterChain(name = "x8379", ctr47).iter(1)
    }
    val x8397_0 = Pipeline(name="x8397_0",parent=x8409) { implicit CU => 
      val x8386 = CU.temp(None)
      val x8387 = CU.temp(None)
      val x8384 = CU.temp(None)
      val x8382 = CU.temp(None)
      val x8389 = ScalarBuffer(name="x8389").wtPort(a_dram_da)
      val x8379 = CounterChain.copy("x8409", "x8379")
      val x8269 = CounterChain.copy("x8832", "x8269")
      val x8397_unit = CounterChain(name = "x8397_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8336 = CounterChain.copy("x8571", "x8336")
      Stage(operands=List(CU.ctr(x8336(0)), CU.ctr(x8379(0))), op=FixAdd, results=List(x8382))
      Stage(operands=List(x8382, Const(6)), op=FixSla, results=List(x8384))
      Stage(operands=List(x8384, CU.ctr(x8269(0))), op=FixAdd, results=List(x8386))
      Stage(operands=List(x8386, Const(3)), op=FixSla, results=List(x8387))
      Stage(operands=List(x8387, CU.load(x8389)), op=FixAdd, results=List(CU.scalarOut(x8380_b9627_x8396_b9629_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8380_b9628_x8396_b9630_s)))
    }
    val x8398 = MemoryController(name="x8398",parent=x8409,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8380_b9628 = ScalarFIFO(size=1,name="size").wtPort(x8380_b9628_x8396_b9630_s)
      val x8380_b9627 = ScalarFIFO(size=1,name="offset").wtPort(x8380_b9627_x8396_b9629_s)
      CU.newSout("data", x8381_x8398_data_s)
    }
    val x8408 = Pipeline(name="x8408",parent=x8409) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8400 = CounterChain(name = "x8400", ctr48).iter(16)
    }
    val x8411_dsp0_bank0 = MemoryPipeline(name="x8411_dsp0_bank0",parent="x8481") { implicit CU => 
      val x8479 = ScalarFIFO(size=1,name="x8479").wtPort(x8411_x8479_s)
      val x8458 = CounterChain.copy("x8480_0", "x8458")
      val x8411 = SRAM(size=16,name="x8411",banking = Strided(1)).wtPort(x8479.readPort).rdPort(x8411_x8411_dsp0_bank0_s).rdAddr(x8458(0)).wtAddr(x8458(0))
    }
    val x8411_dsp1_bank0 = MemoryPipeline(name="x8411_dsp1_bank0",parent="x8481") { implicit CU => 
      val x8479 = ScalarFIFO(size=1,name="x8479").wtPort(x8411_x8479_s)
      val x8553 = CounterChain.copy("x8560", "x8553")
      val x8458 = CounterChain.copy("x8480_0", "x8458")
      val x8411 = SRAM(size=16,name="x8411",banking = Strided(1)).wtPort(x8479.readPort).rdPort(x8411_x8411_dsp1_bank0_s).rdAddr(x8553(0)).wtAddr(x8458(0))
    }
    val x8412_dsp0_bank0 = MemoryPipeline(name="x8412_dsp0_bank0",parent="x8550") { implicit CU => 
      val x8548 = ScalarFIFO(size=1,name="x8548").wtPort(x8412_x8548_s)
      val x8527 = CounterChain.copy("x8549_0", "x8527")
      val x8412 = SRAM(size=16,name="x8412",banking = Strided(1)).wtPort(x8548.readPort).rdPort(x8412_x8412_dsp0_bank0_s).rdAddr(x8527(0)).wtAddr(x8527(0))
    }
    val x8412_dsp1_bank0 = MemoryPipeline(name="x8412_dsp1_bank0",parent="x8550") { implicit CU => 
      val x8548 = ScalarFIFO(size=1,name="x8548").wtPort(x8412_x8548_s)
      val x8527 = CounterChain.copy("x8549_0", "x8527")
      val x8562 = CounterChain.copy("x8569", "x8562")
      val x8412 = SRAM(size=16,name="x8412",banking = Strided(1)).wtPort(x8548.readPort).rdPort(x8412_x8412_dsp1_bank0_s).rdAddr(x8562(0)).wtAddr(x8527(0))
    }
    val x8481 = MetaPipeline(name="x8481",parent=x8571) { implicit CU => 
      val ctr49 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8414 = CounterChain(name = "x8414", ctr49).iter(8)
    }
    val x8415_dsp0_bank0 = MemoryPipeline(name="x8415_dsp0_bank0",parent="x8481") { implicit CU => 
      val x8442 = ScalarFIFO(size=1,name="x8442").wtPort(x8415_x8442_s)
      val x8433 = CounterChain.copy("x8443_0", "x8433")
      val x8458 = CounterChain.copy("x8480_0", "x8458")
      val x8415 = SRAM(size=16,name="x8415",banking = Strided(1)).wtPort(x8442.readPort).rdPort(x8415_x8415_dsp0_bank0_s).rdAddr(x8458(0)).wtAddr(x8433(0))
    }
    val x8416_dsp0_bank0 = MemoryPipeline(name="x8416_dsp0_bank0",parent="x8481") { implicit CU => 
      val x8454 = ScalarFIFO(size=1,name="x8454").wtPort(x8416_x8454_s)
      val x8445 = CounterChain.copy("x8455_0", "x8445")
      val x8458 = CounterChain.copy("x8480_0", "x8458")
      val x8416 = SRAM(size=16,name="x8416",banking = Strided(1)).wtPort(x8454.readPort).rdPort(x8416_x8416_dsp0_bank0_s).rdAddr(x8458(0)).wtAddr(x8445(0))
    }
    val x8443_0 = Pipeline(name="x8443_0",parent=x8481) { implicit CU => 
      val x8417 = ScalarBuffer(name="x8417").wtPort(x8337_x8337_dsp0_bank0_s)
      val x8438 = ScalarFIFO(size=1,name="x8438").wtPort(x8272_x8272_dsp0_bank0_s)
      val ctr50 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8433 = CounterChain(name = "x8433", ctr50).iter(1)
      Stage(operands=List(CU.load(x8438), CU.load(x8417)), op=FixMul, results=List(CU.scalarOut(x8415_x8442_s)))
    }
    val x8455_0 = Pipeline(name="x8455_0",parent=x8481) { implicit CU => 
      val x8418 = ScalarBuffer(name="x8418").wtPort(x8337_x8337_dsp0_bank0_s)
      val x8450 = ScalarFIFO(size=1,name="x8450").wtPort(x8272_x8272_dsp0_bank1_s)
      val ctr51 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8445 = CounterChain(name = "x8445", ctr51).iter(1)
      Stage(operands=List(CU.load(x8450), CU.load(x8418)), op=FixMul, results=List(CU.scalarOut(x8416_x8454_s)))
    }
    val x8480_0 = Pipeline(name="x8480_0",parent=x8481) { implicit CU => 
      val x8474 = CU.temp(None)
      val x8466 = ScalarFIFO(size=1,name="x8466").wtPort(x8411_x8411_dsp0_bank0_s)
      val x8462 = ScalarFIFO(size=1,name="x8462").wtPort(x8415_x8415_dsp0_bank0_s)
      val x8464 = ScalarFIFO(size=1,name="x8464").wtPort(x8416_x8416_dsp0_bank0_s)
      val ctr52 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8458 = CounterChain(name = "x8458", ctr52).iter(1)
      Stage(operands=List(CU.load(x8462), CU.load(x8464)), op=FixAdd, results=List(x8474))
      Stage(operands=List(x8474, CU.load(x8466)), op=FixAdd, results=List(CU.scalarOut(x8411_x8479_s)))
    }
    val x8550 = MetaPipeline(name="x8550",parent=x8571) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8483 = CounterChain(name = "x8483", ctr53).iter(8)
    }
    val x8484_dsp0_bank0 = MemoryPipeline(name="x8484_dsp0_bank0",parent="x8550") { implicit CU => 
      val x8511 = ScalarFIFO(size=1,name="x8511").wtPort(x8484_x8511_s)
      val x8527 = CounterChain.copy("x8549_0", "x8527")
      val x8502 = CounterChain.copy("x8512_0", "x8502")
      val x8484 = SRAM(size=16,name="x8484",banking = Strided(1)).wtPort(x8511.readPort).rdPort(x8484_x8484_dsp0_bank0_s).rdAddr(x8527(0)).wtAddr(x8502(0))
    }
    val x8485_dsp0_bank0 = MemoryPipeline(name="x8485_dsp0_bank0",parent="x8550") { implicit CU => 
      val x8523 = ScalarFIFO(size=1,name="x8523").wtPort(x8485_x8523_s)
      val x8527 = CounterChain.copy("x8549_0", "x8527")
      val x8514 = CounterChain.copy("x8524_0", "x8514")
      val x8485 = SRAM(size=16,name="x8485",banking = Strided(1)).wtPort(x8523.readPort).rdPort(x8485_x8485_dsp0_bank0_s).rdAddr(x8527(0)).wtAddr(x8514(0))
    }
    val x8512_0 = Pipeline(name="x8512_0",parent=x8550) { implicit CU => 
      val x8507 = ScalarFIFO(size=1,name="x8507").wtPort(x8272_x8272_dsp1_bank0_s)
      val x8486 = ScalarBuffer(name="x8486").wtPort(x8338_x8338_dsp0_bank0_s)
      val ctr54 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8502 = CounterChain(name = "x8502", ctr54).iter(1)
      Stage(operands=List(CU.load(x8507), CU.load(x8486)), op=FixMul, results=List(CU.scalarOut(x8484_x8511_s)))
    }
    val x8524_0 = Pipeline(name="x8524_0",parent=x8550) { implicit CU => 
      val x8519 = ScalarFIFO(size=1,name="x8519").wtPort(x8272_x8272_dsp1_bank1_s)
      val x8487 = ScalarBuffer(name="x8487").wtPort(x8338_x8338_dsp0_bank0_s)
      val ctr55 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8514 = CounterChain(name = "x8514", ctr55).iter(1)
      Stage(operands=List(CU.load(x8519), CU.load(x8487)), op=FixMul, results=List(CU.scalarOut(x8485_x8523_s)))
    }
    val x8549_0 = Pipeline(name="x8549_0",parent=x8550) { implicit CU => 
      val x8543 = CU.temp(None)
      val x8535 = ScalarFIFO(size=1,name="x8535").wtPort(x8412_x8412_dsp0_bank0_s)
      val x8531 = ScalarFIFO(size=1,name="x8531").wtPort(x8484_x8484_dsp0_bank0_s)
      val x8533 = ScalarFIFO(size=1,name="x8533").wtPort(x8485_x8485_dsp0_bank0_s)
      val ctr56 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8527 = CounterChain(name = "x8527", ctr56).iter(1)
      Stage(operands=List(CU.load(x8531), CU.load(x8533)), op=FixAdd, results=List(x8543))
      Stage(operands=List(x8543, CU.load(x8535)), op=FixAdd, results=List(CU.scalarOut(x8412_x8548_s)))
    }
    val x8560 = Pipeline(name="x8560",parent=x8571) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8553 = CounterChain(name = "x8553", ctr57).iter(16)
    }
    val x8569 = Pipeline(name="x8569",parent=x8571) { implicit CU => 
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8562 = CounterChain(name = "x8562", ctr58).iter(16)
    }
    val x8808 = MetaPipeline(name="x8808",parent=x8832) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x8573 = CounterChain(name = "x8573", ctr59).iter(32)
    }
    val x8574_dsp0_bank0 = MemoryPipeline(name="x8574_dsp0_bank0",parent="x8808") { implicit CU => 
      val x8612 = ScalarFIFO(size=1,name="x8612").wtPort(x8586_x8603_data_s)
      val x8605 = CounterChain.copy("x8613", "x8605")
      val x8651 = CounterChain.copy("x8718", "x8651")
      val x8574 = SRAM(size=16,name="x8574",banking = Strided(1)).wtPort(x8612.readPort).rdPort(x8574_x8574_dsp0_bank0_s).rdAddr(x8651(0)).rdAddr(x8651(0)).wtAddr(x8605(0))
    }
    val x8575_dsp0_bank0 = MemoryPipeline(name="x8575_dsp0_bank0",parent="x8808") { implicit CU => 
      val x8644 = ScalarFIFO(size=1,name="x8644").wtPort(x8618_x8635_data_s)
      val x8720 = CounterChain.copy("x8787", "x8720")
      val x8637 = CounterChain.copy("x8645", "x8637")
      val x8575 = SRAM(size=16,name="x8575",banking = Strided(1)).wtPort(x8644.readPort).rdPort(x8575_x8575_dsp0_bank0_s).rdAddr(x8720(0)).rdAddr(x8720(0)).wtAddr(x8637(0))
    }
    val x8614 = StreamController(name="x8614",parent=x8808) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8584 = CounterChain(name = "x8584", ctr60).iter(1)
    }
    val x8602_0 = Pipeline(name="x8602_0",parent=x8614) { implicit CU => 
      val x8592 = CU.temp(None)
      val x8587 = CU.temp(None)
      val x8591 = CU.temp(None)
      val x8589 = CU.temp(None)
      val x8594 = ScalarBuffer(name="x8594").wtPort(a_dram_da)
      val x8573 = CounterChain.copy("x8808", "x8573")
      val x8584 = CounterChain.copy("x8614", "x8584")
      val x8269 = CounterChain.copy("x8832", "x8269")
      val x8602_unit = CounterChain(name = "x8602_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8573(0)), CU.ctr(x8584(0))), op=FixAdd, results=List(x8587))
      Stage(operands=List(x8587, Const(6)), op=FixSla, results=List(x8589))
      Stage(operands=List(x8589, CU.ctr(x8269(0))), op=FixAdd, results=List(x8591))
      Stage(operands=List(x8591, Const(3)), op=FixSla, results=List(x8592))
      Stage(operands=List(x8592, CU.load(x8594)), op=FixAdd, results=List(CU.scalarOut(x8585_b9643_x8601_b9645_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8585_b9644_x8601_b9646_s)))
    }
    val x8603 = MemoryController(name="x8603",parent=x8614,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8585_b9644 = ScalarFIFO(size=1,name="size").wtPort(x8585_b9644_x8601_b9646_s)
      val x8585_b9643 = ScalarFIFO(size=1,name="offset").wtPort(x8585_b9643_x8601_b9645_s)
      CU.newSout("data", x8586_x8603_data_s)
    }
    val x8613 = Pipeline(name="x8613",parent=x8614) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8605 = CounterChain(name = "x8605", ctr61).iter(16)
    }
    val x8646 = StreamController(name="x8646",parent=x8808) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8616 = CounterChain(name = "x8616", ctr62).iter(1)
    }
    val x8634_0 = Pipeline(name="x8634_0",parent=x8646) { implicit CU => 
      val x8624 = CU.temp(None)
      val x8623 = CU.temp(None)
      val x8621 = CU.temp(None)
      val x8619 = CU.temp(None)
      val x8626 = ScalarBuffer(name="x8626").wtPort(a_dram_da)
      val x8634_unit = CounterChain(name = "x8634_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8573 = CounterChain.copy("x8808", "x8573")
      val x8616 = CounterChain.copy("x8646", "x8616")
      val x8269 = CounterChain.copy("x8832", "x8269")
      Stage(operands=List(CU.ctr(x8573(0)), CU.ctr(x8616(0))), op=FixAdd, results=List(x8619))
      Stage(operands=List(x8619, Const(6)), op=FixSla, results=List(x8621))
      Stage(operands=List(x8621, CU.ctr(x8269(0))), op=FixAdd, results=List(x8623))
      Stage(operands=List(x8623, Const(3)), op=FixSla, results=List(x8624))
      Stage(operands=List(x8624, CU.load(x8626)), op=FixAdd, results=List(CU.scalarOut(x8617_b9647_x8633_b9649_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8617_b9648_x8633_b9650_s)))
    }
    val x8635 = MemoryController(name="x8635",parent=x8646,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8617_b9647 = ScalarFIFO(size=1,name="offset").wtPort(x8617_b9647_x8633_b9649_s)
      val x8617_b9648 = ScalarFIFO(size=1,name="size").wtPort(x8617_b9648_x8633_b9650_s)
      CU.newSout("data", x8618_x8635_data_s)
    }
    val x8645 = Pipeline(name="x8645",parent=x8646) { implicit CU => 
      val ctr63 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8637 = CounterChain(name = "x8637", ctr63).iter(16)
    }
    val x8648_dsp0_bank0 = MemoryPipeline(name="x8648_dsp0_bank0",parent="x8718") { implicit CU => 
      val x8716 = ScalarFIFO(size=1,name="x8716").wtPort(x8648_x8716_s)
      val x8695 = CounterChain.copy("x8717_0", "x8695")
      val x8648 = SRAM(size=16,name="x8648",banking = Strided(1)).wtPort(x8716.readPort).rdPort(x8648_x8648_dsp0_bank0_s).rdAddr(x8695(0)).wtAddr(x8695(0))
    }
    val x8648_dsp1_bank0 = MemoryPipeline(name="x8648_dsp1_bank0",parent="x8718") { implicit CU => 
      val x8716 = ScalarFIFO(size=1,name="x8716").wtPort(x8648_x8716_s)
      val x8790 = CounterChain.copy("x8797", "x8790")
      val x8695 = CounterChain.copy("x8717_0", "x8695")
      val x8648 = SRAM(size=16,name="x8648",banking = Strided(1)).wtPort(x8716.readPort).rdPort(x8648_x8648_dsp1_bank0_s).rdAddr(x8790(0)).wtAddr(x8695(0))
    }
    val x8649_dsp0_bank0 = MemoryPipeline(name="x8649_dsp0_bank0",parent="x8787") { implicit CU => 
      val x8785 = ScalarFIFO(size=1,name="x8785").wtPort(x8649_x8785_s)
      val x8764 = CounterChain.copy("x8786_0", "x8764")
      val x8649 = SRAM(size=16,name="x8649",banking = Strided(1)).wtPort(x8785.readPort).rdPort(x8649_x8649_dsp0_bank0_s).rdAddr(x8764(0)).wtAddr(x8764(0))
    }
    val x8649_dsp1_bank0 = MemoryPipeline(name="x8649_dsp1_bank0",parent="x8787") { implicit CU => 
      val x8785 = ScalarFIFO(size=1,name="x8785").wtPort(x8649_x8785_s)
      val x8764 = CounterChain.copy("x8786_0", "x8764")
      val x8799 = CounterChain.copy("x8806", "x8799")
      val x8649 = SRAM(size=16,name="x8649",banking = Strided(1)).wtPort(x8785.readPort).rdPort(x8649_x8649_dsp1_bank0_s).rdAddr(x8799(0)).wtAddr(x8764(0))
    }
    val x8718 = MetaPipeline(name="x8718",parent=x8808) { implicit CU => 
      val ctr64 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8651 = CounterChain(name = "x8651", ctr64).iter(8)
    }
    val x8652_dsp0_bank0 = MemoryPipeline(name="x8652_dsp0_bank0",parent="x8718") { implicit CU => 
      val x8679 = ScalarFIFO(size=1,name="x8679").wtPort(x8652_x8679_s)
      val x8695 = CounterChain.copy("x8717_0", "x8695")
      val x8670 = CounterChain.copy("x8680_0", "x8670")
      val x8652 = SRAM(size=16,name="x8652",banking = Strided(1)).wtPort(x8679.readPort).rdPort(x8652_x8652_dsp0_bank0_s).rdAddr(x8695(0)).wtAddr(x8670(0))
    }
    val x8653_dsp0_bank0 = MemoryPipeline(name="x8653_dsp0_bank0",parent="x8718") { implicit CU => 
      val x8691 = ScalarFIFO(size=1,name="x8691").wtPort(x8653_x8691_s)
      val x8695 = CounterChain.copy("x8717_0", "x8695")
      val x8682 = CounterChain.copy("x8692_0", "x8682")
      val x8653 = SRAM(size=16,name="x8653",banking = Strided(1)).wtPort(x8691.readPort).rdPort(x8653_x8653_dsp0_bank0_s).rdAddr(x8695(0)).wtAddr(x8682(0))
    }
    val x8680_0 = Pipeline(name="x8680_0",parent=x8718) { implicit CU => 
      val x8654 = ScalarBuffer(name="x8654").wtPort(x8574_x8574_dsp0_bank0_s)
      val x8675 = ScalarFIFO(size=1,name="x8675").wtPort(x8273_x8273_dsp0_bank0_s)
      val ctr65 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8670 = CounterChain(name = "x8670", ctr65).iter(1)
      Stage(operands=List(CU.load(x8675), CU.load(x8654)), op=FixMul, results=List(CU.scalarOut(x8652_x8679_s)))
    }
    val x8692_0 = Pipeline(name="x8692_0",parent=x8718) { implicit CU => 
      val x8687 = ScalarFIFO(size=1,name="x8687").wtPort(x8273_x8273_dsp0_bank1_s)
      val x8655 = ScalarBuffer(name="x8655").wtPort(x8574_x8574_dsp0_bank0_s)
      val ctr66 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8682 = CounterChain(name = "x8682", ctr66).iter(1)
      Stage(operands=List(CU.load(x8687), CU.load(x8655)), op=FixMul, results=List(CU.scalarOut(x8653_x8691_s)))
    }
    val x8717_0 = Pipeline(name="x8717_0",parent=x8718) { implicit CU => 
      val x8711 = CU.temp(None)
      val x8699 = ScalarFIFO(size=1,name="x8699").wtPort(x8652_x8652_dsp0_bank0_s)
      val x8701 = ScalarFIFO(size=1,name="x8701").wtPort(x8653_x8653_dsp0_bank0_s)
      val x8703 = ScalarFIFO(size=1,name="x8703").wtPort(x8648_x8648_dsp0_bank0_s)
      val ctr67 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8695 = CounterChain(name = "x8695", ctr67).iter(1)
      Stage(operands=List(CU.load(x8699), CU.load(x8701)), op=FixAdd, results=List(x8711))
      Stage(operands=List(x8711, CU.load(x8703)), op=FixAdd, results=List(CU.scalarOut(x8648_x8716_s)))
    }
    val x8787 = MetaPipeline(name="x8787",parent=x8808) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8720 = CounterChain(name = "x8720", ctr68).iter(8)
    }
    val x8721_dsp0_bank0 = MemoryPipeline(name="x8721_dsp0_bank0",parent="x8787") { implicit CU => 
      val x8748 = ScalarFIFO(size=1,name="x8748").wtPort(x8721_x8748_s)
      val x8764 = CounterChain.copy("x8786_0", "x8764")
      val x8739 = CounterChain.copy("x8749_0", "x8739")
      val x8721 = SRAM(size=16,name="x8721",banking = Strided(1)).wtPort(x8748.readPort).rdPort(x8721_x8721_dsp0_bank0_s).rdAddr(x8764(0)).wtAddr(x8739(0))
    }
    val x8722_dsp0_bank0 = MemoryPipeline(name="x8722_dsp0_bank0",parent="x8787") { implicit CU => 
      val x8760 = ScalarFIFO(size=1,name="x8760").wtPort(x8722_x8760_s)
      val x8764 = CounterChain.copy("x8786_0", "x8764")
      val x8751 = CounterChain.copy("x8761_0", "x8751")
      val x8722 = SRAM(size=16,name="x8722",banking = Strided(1)).wtPort(x8760.readPort).rdPort(x8722_x8722_dsp0_bank0_s).rdAddr(x8764(0)).wtAddr(x8751(0))
    }
    val x8749_0 = Pipeline(name="x8749_0",parent=x8787) { implicit CU => 
      val x8723 = ScalarBuffer(name="x8723").wtPort(x8575_x8575_dsp0_bank0_s)
      val x8744 = ScalarFIFO(size=1,name="x8744").wtPort(x8273_x8273_dsp1_bank0_s)
      val ctr69 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8739 = CounterChain(name = "x8739", ctr69).iter(1)
      Stage(operands=List(CU.load(x8744), CU.load(x8723)), op=FixMul, results=List(CU.scalarOut(x8721_x8748_s)))
    }
    val x8761_0 = Pipeline(name="x8761_0",parent=x8787) { implicit CU => 
      val x8724 = ScalarBuffer(name="x8724").wtPort(x8575_x8575_dsp0_bank0_s)
      val x8756 = ScalarFIFO(size=1,name="x8756").wtPort(x8273_x8273_dsp1_bank1_s)
      val ctr70 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8751 = CounterChain(name = "x8751", ctr70).iter(1)
      Stage(operands=List(CU.load(x8756), CU.load(x8724)), op=FixMul, results=List(CU.scalarOut(x8722_x8760_s)))
    }
    val x8786_0 = Pipeline(name="x8786_0",parent=x8787) { implicit CU => 
      val x8780 = CU.temp(None)
      val x8768 = ScalarFIFO(size=1,name="x8768").wtPort(x8721_x8721_dsp0_bank0_s)
      val x8770 = ScalarFIFO(size=1,name="x8770").wtPort(x8722_x8722_dsp0_bank0_s)
      val x8772 = ScalarFIFO(size=1,name="x8772").wtPort(x8649_x8649_dsp0_bank0_s)
      val ctr71 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8764 = CounterChain(name = "x8764", ctr71).iter(1)
      Stage(operands=List(CU.load(x8768), CU.load(x8770)), op=FixAdd, results=List(x8780))
      Stage(operands=List(x8780, CU.load(x8772)), op=FixAdd, results=List(CU.scalarOut(x8649_x8785_s)))
    }
    val x8797 = Pipeline(name="x8797",parent=x8808) { implicit CU => 
      val ctr72 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8790 = CounterChain(name = "x8790", ctr72).iter(16)
    }
    val x8806 = Pipeline(name="x8806",parent=x8808) { implicit CU => 
      val ctr73 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8799 = CounterChain(name = "x8799", ctr73).iter(16)
    }
    val x8831_0 = Pipeline(name="x8831_0",parent=x8832) { implicit CU => 
      val x8825 = CU.temp(None)
      val x8815 = ScalarFIFO(size=1,name="x8815").wtPort(x8270_x8270_dsp0_bank0_s).wtPort(x8270_x8270_dsp0_bank1_s)
      val x8817 = ScalarFIFO(size=1,name="x8817").wtPort(x8271_x8271_dsp0_bank0_s).wtPort(x8271_x8271_dsp0_bank1_s)
      val x8819 = ScalarFIFO(size=1,name="x8819").wtPort(x7701_x7701_dsp0_bank0_s)
      val ctr74 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8812 = CounterChain(name = "x8812", ctr74, ctr75).iter(64)
      Stage(operands=List(CU.load(x8815), CU.load(x8817)), op=FixAdd, results=List(x8825))
      Stage(operands=List(x8825, CU.load(x8819)), op=FixAdd, results=List(CU.scalarOut(x7701_x8830_s)))
    }
    val x9397 = MetaPipeline(name="x9397",parent=x9503) { implicit CU => 
      val ctr76 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x8834 = CounterChain(name = "x8834", ctr76).iter(2)
    }
    val x8835_dsp0_bank0 = MemoryPipeline(name="x8835_dsp0_bank0",parent="x9397") { implicit CU => 
      val b9699 = CU.temp(None)
      val b9723 = CU.temp(None)
      val x9124 = ScalarFIFO(size=1,name="x9124").wtPort(x8976_x8976_dsp1_bank0_s)
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x8901 = CounterChain.copy("x9136", "x8901")
      val x9118 = CounterChain.copy("x9125", "x9118")
      val x8835 = SRAM(size=1024,name="x8835",banking = Strided(1)).wtPort(x9124.readPort).rdPort(x8835_x8835_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8901(0)), Const(16)), op=FixMul, results=List(b9699))
      WAStage(operands=List(b9699, CU.ctr(x9118(0))), op=FixAdd, results=List(x8835.writeAddr))
      RAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9723))
      RAStage(operands=List(b9723, CU.ctr(x9377(1))), op=FixAdd, results=List(x8835.readAddr))
    }
    val x8835_dsp0_bank1 = MemoryPipeline(name="x8835_dsp0_bank1",parent="x9397") { implicit CU => 
      val b9723 = CU.temp(None)
      val b9701 = CU.temp(None)
      val x9133 = ScalarFIFO(size=1,name="x9133").wtPort(x8977_x8977_dsp1_bank0_s)
      val x9127 = CounterChain.copy("x9134", "x9127")
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x8901 = CounterChain.copy("x9136", "x8901")
      val x8835 = SRAM(size=1024,name="x8835",banking = Strided(1)).wtPort(x9133.readPort).rdPort(x8835_x8835_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8901(0)), Const(16)), op=FixMul, results=List(b9701))
      WAStage(operands=List(b9701, CU.ctr(x9127(0))), op=FixAdd, results=List(x8835.writeAddr))
      RAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9723))
      RAStage(operands=List(b9723, CU.ctr(x9377(1))), op=FixAdd, results=List(x8835.readAddr))
    }
    val x8836_dsp0_bank0 = MemoryPipeline(name="x8836_dsp0_bank0",parent="x9397") { implicit CU => 
      val b9719 = CU.temp(None)
      val b9725 = CU.temp(None)
      val x9361 = ScalarFIFO(size=1,name="x9361").wtPort(x9213_x9213_dsp1_bank0_s)
      val x9355 = CounterChain.copy("x9362", "x9355")
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x9138 = CounterChain.copy("x9373", "x9138")
      val x8836 = SRAM(size=1024,name="x8836",banking = Strided(1)).wtPort(x9361.readPort).rdPort(x8836_x8836_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9138(0)), Const(16)), op=FixMul, results=List(b9719))
      WAStage(operands=List(b9719, CU.ctr(x9355(0))), op=FixAdd, results=List(x8836.writeAddr))
      RAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9725))
      RAStage(operands=List(b9725, CU.ctr(x9377(1))), op=FixAdd, results=List(x8836.readAddr))
    }
    val x8836_dsp0_bank1 = MemoryPipeline(name="x8836_dsp0_bank1",parent="x9397") { implicit CU => 
      val b9721 = CU.temp(None)
      val b9725 = CU.temp(None)
      val x9370 = ScalarFIFO(size=1,name="x9370").wtPort(x9214_x9214_dsp1_bank0_s)
      val x9377 = CounterChain.copy("x9396_0", "x9377")
      val x9364 = CounterChain.copy("x9371", "x9364")
      val x9138 = CounterChain.copy("x9373", "x9138")
      val x8836 = SRAM(size=1024,name="x8836",banking = Strided(1)).wtPort(x9370.readPort).rdPort(x8836_x8836_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x9138(0)), Const(16)), op=FixMul, results=List(b9721))
      WAStage(operands=List(b9721, CU.ctr(x9364(0))), op=FixAdd, results=List(x8836.writeAddr))
      RAStage(operands=List(CU.ctr(x9377(0)), Const(16)), op=FixMul, results=List(b9725))
      RAStage(operands=List(b9725, CU.ctr(x9377(1))), op=FixAdd, results=List(x8836.readAddr))
    }
    val x8837_dsp0_bank0 = MemoryPipeline(name="x8837_dsp0_bank0",parent="x9397") { implicit CU => 
      val b9691 = CU.temp(None)
      val b9675 = CU.temp(None)
      val x8866 = ScalarFIFO(size=1,name="x8866").wtPort(x8842_x8858_data_s)
      val x8998 = CounterChain.copy("x9008_0", "x8998")
      val x8840 = CounterChain.copy("x8868", "x8840")
      val x8979 = CounterChain.copy("x9046", "x8979")
      val x8860 = CounterChain.copy("x8867", "x8860")
      val x8837 = SRAM(size=256,name="x8837",banking = Strided(1)).wtPort(x8866.readPort).rdPort(x8837_x8837_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8840(0)), Const(16)), op=FixMul, results=List(b9675))
      WAStage(operands=List(b9675, CU.ctr(x8860(0))), op=FixAdd, results=List(x8837.writeAddr))
      RAStage(operands=List(CU.ctr(x8979(0)), Const(16)), op=FixMul, results=List(b9691))
      RAStage(operands=List(b9691, CU.ctr(x8998(0))), op=FixAdd, results=List(x8837.readAddr))
    }
    val x8837_dsp0_bank1 = MemoryPipeline(name="x8837_dsp0_bank1",parent="x9397") { implicit CU => 
      val b9693 = CU.temp(None)
      val b9675 = CU.temp(None)
      val x8866 = ScalarFIFO(size=1,name="x8866").wtPort(x8842_x8858_data_s)
      val x9010 = CounterChain.copy("x9020_0", "x9010")
      val x8840 = CounterChain.copy("x8868", "x8840")
      val x8979 = CounterChain.copy("x9046", "x8979")
      val x8860 = CounterChain.copy("x8867", "x8860")
      val x8837 = SRAM(size=256,name="x8837",banking = Strided(1)).wtPort(x8866.readPort).rdPort(x8837_x8837_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8840(0)), Const(16)), op=FixMul, results=List(b9675))
      WAStage(operands=List(b9675, CU.ctr(x8860(0))), op=FixAdd, results=List(x8837.writeAddr))
      RAStage(operands=List(CU.ctr(x8979(0)), Const(16)), op=FixMul, results=List(b9693))
      RAStage(operands=List(b9693, CU.ctr(x9010(0))), op=FixAdd, results=List(x8837.readAddr))
    }
    val x8837_dsp1_bank0 = MemoryPipeline(name="x8837_dsp1_bank0",parent="x9397") { implicit CU => 
      val b9695 = CU.temp(None)
      val b9675 = CU.temp(None)
      val x8866 = ScalarFIFO(size=1,name="x8866").wtPort(x8842_x8858_data_s)
      val x9067 = CounterChain.copy("x9077_0", "x9067")
      val x8840 = CounterChain.copy("x8868", "x8840")
      val x9048 = CounterChain.copy("x9115", "x9048")
      val x8860 = CounterChain.copy("x8867", "x8860")
      val x8837 = SRAM(size=256,name="x8837",banking = Strided(1)).wtPort(x8866.readPort).rdPort(x8837_x8837_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8840(0)), Const(16)), op=FixMul, results=List(b9675))
      WAStage(operands=List(b9675, CU.ctr(x8860(0))), op=FixAdd, results=List(x8837.writeAddr))
      RAStage(operands=List(CU.ctr(x9048(0)), Const(16)), op=FixMul, results=List(b9695))
      RAStage(operands=List(b9695, CU.ctr(x9067(0))), op=FixAdd, results=List(x8837.readAddr))
    }
    val x8837_dsp1_bank1 = MemoryPipeline(name="x8837_dsp1_bank1",parent="x9397") { implicit CU => 
      val b9697 = CU.temp(None)
      val b9675 = CU.temp(None)
      val x8866 = ScalarFIFO(size=1,name="x8866").wtPort(x8842_x8858_data_s)
      val x8840 = CounterChain.copy("x8868", "x8840")
      val x9079 = CounterChain.copy("x9089_0", "x9079")
      val x9048 = CounterChain.copy("x9115", "x9048")
      val x8860 = CounterChain.copy("x8867", "x8860")
      val x8837 = SRAM(size=256,name="x8837",banking = Strided(1)).wtPort(x8866.readPort).rdPort(x8837_x8837_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x8840(0)), Const(16)), op=FixMul, results=List(b9675))
      WAStage(operands=List(b9675, CU.ctr(x8860(0))), op=FixAdd, results=List(x8837.writeAddr))
      RAStage(operands=List(CU.ctr(x9048(0)), Const(16)), op=FixMul, results=List(b9697))
      RAStage(operands=List(b9697, CU.ctr(x9079(0))), op=FixAdd, results=List(x8837.readAddr))
    }
    val x8838_dsp0_bank0 = MemoryPipeline(name="x8838_dsp0_bank0",parent="x9397") { implicit CU => 
      val b9711 = CU.temp(None)
      val b9681 = CU.temp(None)
      val x8896 = ScalarFIFO(size=1,name="x8896").wtPort(x8872_x8888_data_s)
      val x9235 = CounterChain.copy("x9245_0", "x9235")
      val x8890 = CounterChain.copy("x8897", "x8890")
      val x9216 = CounterChain.copy("x9283", "x9216")
      val x8870 = CounterChain.copy("x8898", "x8870")
      val x8838 = SRAM(size=256,name="x8838",banking = Strided(1)).wtPort(x8896.readPort).rdPort(x8838_x8838_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x8870(0)), Const(16)), op=FixMul, results=List(b9681))
      WAStage(operands=List(b9681, CU.ctr(x8890(0))), op=FixAdd, results=List(x8838.writeAddr))
      RAStage(operands=List(CU.ctr(x9216(0)), Const(16)), op=FixMul, results=List(b9711))
      RAStage(operands=List(b9711, CU.ctr(x9235(0))), op=FixAdd, results=List(x8838.readAddr))
    }
    val x8838_dsp0_bank1 = MemoryPipeline(name="x8838_dsp0_bank1",parent="x9397") { implicit CU => 
      val b9713 = CU.temp(None)
      val b9681 = CU.temp(None)
      val x8896 = ScalarFIFO(size=1,name="x8896").wtPort(x8872_x8888_data_s)
      val x8890 = CounterChain.copy("x8897", "x8890")
      val x9216 = CounterChain.copy("x9283", "x9216")
      val x8870 = CounterChain.copy("x8898", "x8870")
      val x9247 = CounterChain.copy("x9257_0", "x9247")
      val x8838 = SRAM(size=256,name="x8838",banking = Strided(1)).wtPort(x8896.readPort).rdPort(x8838_x8838_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x8870(0)), Const(16)), op=FixMul, results=List(b9681))
      WAStage(operands=List(b9681, CU.ctr(x8890(0))), op=FixAdd, results=List(x8838.writeAddr))
      RAStage(operands=List(CU.ctr(x9216(0)), Const(16)), op=FixMul, results=List(b9713))
      RAStage(operands=List(b9713, CU.ctr(x9247(0))), op=FixAdd, results=List(x8838.readAddr))
    }
    val x8838_dsp1_bank0 = MemoryPipeline(name="x8838_dsp1_bank0",parent="x9397") { implicit CU => 
      val b9715 = CU.temp(None)
      val b9681 = CU.temp(None)
      val x8896 = ScalarFIFO(size=1,name="x8896").wtPort(x8872_x8888_data_s)
      val x8890 = CounterChain.copy("x8897", "x8890")
      val x8870 = CounterChain.copy("x8898", "x8870")
      val x9304 = CounterChain.copy("x9314_0", "x9304")
      val x9285 = CounterChain.copy("x9352", "x9285")
      val x8838 = SRAM(size=256,name="x8838",banking = Strided(1)).wtPort(x8896.readPort).rdPort(x8838_x8838_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x8870(0)), Const(16)), op=FixMul, results=List(b9681))
      WAStage(operands=List(b9681, CU.ctr(x8890(0))), op=FixAdd, results=List(x8838.writeAddr))
      RAStage(operands=List(CU.ctr(x9285(0)), Const(16)), op=FixMul, results=List(b9715))
      RAStage(operands=List(b9715, CU.ctr(x9304(0))), op=FixAdd, results=List(x8838.readAddr))
    }
    val x8838_dsp1_bank1 = MemoryPipeline(name="x8838_dsp1_bank1",parent="x9397") { implicit CU => 
      val b9717 = CU.temp(None)
      val b9681 = CU.temp(None)
      val x8896 = ScalarFIFO(size=1,name="x8896").wtPort(x8872_x8888_data_s)
      val x8890 = CounterChain.copy("x8897", "x8890")
      val x8870 = CounterChain.copy("x8898", "x8870")
      val x9316 = CounterChain.copy("x9326_0", "x9316")
      val x9285 = CounterChain.copy("x9352", "x9285")
      val x8838 = SRAM(size=256,name="x8838",banking = Strided(1)).wtPort(x8896.readPort).rdPort(x8838_x8838_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x8870(0)), Const(16)), op=FixMul, results=List(b9681))
      WAStage(operands=List(b9681, CU.ctr(x8890(0))), op=FixAdd, results=List(x8838.writeAddr))
      RAStage(operands=List(CU.ctr(x9285(0)), Const(16)), op=FixMul, results=List(b9717))
      RAStage(operands=List(b9717, CU.ctr(x9316(0))), op=FixAdd, results=List(x8838.readAddr))
    }
    val x8868 = StreamController(name="x8868",parent=x9397) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8840 = CounterChain(name = "x8840", ctr77).iter(16)
    }
    val x8857_0 = Pipeline(name="x8857_0",parent=x8868) { implicit CU => 
      val x8847 = CU.temp(None)
      val x8845 = CU.temp(None)
      val x8848 = CU.temp(None)
      val x8843 = CU.temp(None)
      val x8850 = ScalarBuffer(name="x8850").wtPort(b_dram_da)
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x8840 = CounterChain.copy("x8868", "x8840")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x8857_unit = CounterChain(name = "x8857_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8834(0)), CU.ctr(x8840(0))), op=FixAdd, results=List(x8843))
      Stage(operands=List(x8843, Const(6)), op=FixSla, results=List(x8845))
      Stage(operands=List(x8845, CU.ctr(x7699(0))), op=FixAdd, results=List(x8847))
      Stage(operands=List(x8847, Const(3)), op=FixSla, results=List(x8848))
      Stage(operands=List(x8848, CU.load(x8850)), op=FixAdd, results=List(CU.scalarOut(x8841_b9671_x8856_b9673_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8841_b9672_x8856_b9674_s)))
    }
    val x8858 = MemoryController(name="x8858",parent=x8868,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8841_b9671 = ScalarFIFO(size=1,name="offset").wtPort(x8841_b9671_x8856_b9673_s)
      val x8841_b9672 = ScalarFIFO(size=1,name="size").wtPort(x8841_b9672_x8856_b9674_s)
      CU.newSout("data", x8842_x8858_data_s)
    }
    val x8867 = Pipeline(name="x8867",parent=x8868) { implicit CU => 
      val ctr78 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8860 = CounterChain(name = "x8860", ctr78).iter(1)
    }
    val x8898 = StreamController(name="x8898",parent=x9397) { implicit CU => 
      val ctr79 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8870 = CounterChain(name = "x8870", ctr79).iter(16)
    }
    val x8887_0 = Pipeline(name="x8887_0",parent=x8898) { implicit CU => 
      val x8878 = CU.temp(None)
      val x8873 = CU.temp(None)
      val x8875 = CU.temp(None)
      val x8877 = CU.temp(None)
      val x8880 = ScalarBuffer(name="x8880").wtPort(b_dram_da)
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x8870 = CounterChain.copy("x8898", "x8870")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x8887_unit = CounterChain(name = "x8887_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8834(0)), CU.ctr(x8870(0))), op=FixAdd, results=List(x8873))
      Stage(operands=List(x8873, Const(6)), op=FixSla, results=List(x8875))
      Stage(operands=List(x8875, CU.ctr(x7699(0))), op=FixAdd, results=List(x8877))
      Stage(operands=List(x8877, Const(3)), op=FixSla, results=List(x8878))
      Stage(operands=List(x8878, CU.load(x8880)), op=FixAdd, results=List(CU.scalarOut(x8871_b9677_x8886_b9679_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8871_b9678_x8886_b9680_s)))
    }
    val x8888 = MemoryController(name="x8888",parent=x8898,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8871_b9677 = ScalarFIFO(size=1,name="offset").wtPort(x8871_b9677_x8886_b9679_s)
      val x8871_b9678 = ScalarFIFO(size=1,name="size").wtPort(x8871_b9678_x8886_b9680_s)
      CU.newSout("data", x8872_x8888_data_s)
    }
    val x8897 = Pipeline(name="x8897",parent=x8898) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8890 = CounterChain(name = "x8890", ctr80).iter(1)
    }
    val x9136 = MetaPipeline(name="x9136",parent=x9397) { implicit CU => 
      val ctr81 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x8901 = CounterChain(name = "x8901", ctr81).iter(32)
    }
    val x8902_dsp0_bank0 = MemoryPipeline(name="x8902_dsp0_bank0",parent="x9136") { implicit CU => 
      val x8940 = ScalarFIFO(size=1,name="x8940").wtPort(x8914_x8931_data_s)
      val x8933 = CounterChain.copy("x8941", "x8933")
      val x8979 = CounterChain.copy("x9046", "x8979")
      val x8902 = SRAM(size=16,name="x8902",banking = Strided(1)).wtPort(x8940.readPort).rdPort(x8902_x8902_dsp0_bank0_s).rdAddr(x8979(0)).rdAddr(x8979(0)).wtAddr(x8933(0))
    }
    val x8903_dsp0_bank0 = MemoryPipeline(name="x8903_dsp0_bank0",parent="x9136") { implicit CU => 
      val x8972 = ScalarFIFO(size=1,name="x8972").wtPort(x8946_x8963_data_s)
      val x8965 = CounterChain.copy("x8973", "x8965")
      val x9048 = CounterChain.copy("x9115", "x9048")
      val x8903 = SRAM(size=16,name="x8903",banking = Strided(1)).wtPort(x8972.readPort).rdPort(x8903_x8903_dsp0_bank0_s).rdAddr(x9048(0)).rdAddr(x9048(0)).wtAddr(x8965(0))
    }
    val x8942 = StreamController(name="x8942",parent=x9136) { implicit CU => 
      val ctr82 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8912 = CounterChain(name = "x8912", ctr82).iter(1)
    }
    val x8930_0 = Pipeline(name="x8930_0",parent=x8942) { implicit CU => 
      val x8917 = CU.temp(None)
      val x8915 = CU.temp(None)
      val x8920 = CU.temp(None)
      val x8919 = CU.temp(None)
      val x8922 = ScalarBuffer(name="x8922").wtPort(a_dram_da)
      val x8912 = CounterChain.copy("x8942", "x8912")
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x8930_unit = CounterChain(name = "x8930_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x8901 = CounterChain.copy("x9136", "x8901")
      Stage(operands=List(CU.ctr(x8901(0)), CU.ctr(x8912(0))), op=FixAdd, results=List(x8915))
      Stage(operands=List(x8915, Const(6)), op=FixSla, results=List(x8917))
      Stage(operands=List(x8917, CU.ctr(x8834(0))), op=FixAdd, results=List(x8919))
      Stage(operands=List(x8919, Const(3)), op=FixSla, results=List(x8920))
      Stage(operands=List(x8920, CU.load(x8922)), op=FixAdd, results=List(CU.scalarOut(x8913_b9683_x8929_b9685_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8913_b9684_x8929_b9686_s)))
    }
    val x8931 = MemoryController(name="x8931",parent=x8942,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8913_b9683 = ScalarFIFO(size=1,name="offset").wtPort(x8913_b9683_x8929_b9685_s)
      val x8913_b9684 = ScalarFIFO(size=1,name="size").wtPort(x8913_b9684_x8929_b9686_s)
      CU.newSout("data", x8914_x8931_data_s)
    }
    val x8941 = Pipeline(name="x8941",parent=x8942) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8933 = CounterChain(name = "x8933", ctr83).iter(16)
    }
    val x8974 = StreamController(name="x8974",parent=x9136) { implicit CU => 
      val ctr84 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x8944 = CounterChain(name = "x8944", ctr84).iter(1)
    }
    val x8962_0 = Pipeline(name="x8962_0",parent=x8974) { implicit CU => 
      val x8951 = CU.temp(None)
      val x8952 = CU.temp(None)
      val x8947 = CU.temp(None)
      val x8949 = CU.temp(None)
      val x8954 = ScalarBuffer(name="x8954").wtPort(a_dram_da)
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x8944 = CounterChain.copy("x8974", "x8944")
      val x8901 = CounterChain.copy("x9136", "x8901")
      val x8962_unit = CounterChain(name = "x8962_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x8901(0)), CU.ctr(x8944(0))), op=FixAdd, results=List(x8947))
      Stage(operands=List(x8947, Const(6)), op=FixSla, results=List(x8949))
      Stage(operands=List(x8949, CU.ctr(x8834(0))), op=FixAdd, results=List(x8951))
      Stage(operands=List(x8951, Const(3)), op=FixSla, results=List(x8952))
      Stage(operands=List(x8952, CU.load(x8954)), op=FixAdd, results=List(CU.scalarOut(x8945_b9687_x8961_b9689_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x8945_b9688_x8961_b9690_s)))
    }
    val x8963 = MemoryController(name="x8963",parent=x8974,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x8945_b9688 = ScalarFIFO(size=1,name="size").wtPort(x8945_b9688_x8961_b9690_s)
      val x8945_b9687 = ScalarFIFO(size=1,name="offset").wtPort(x8945_b9687_x8961_b9689_s)
      CU.newSout("data", x8946_x8963_data_s)
    }
    val x8973 = Pipeline(name="x8973",parent=x8974) { implicit CU => 
      val ctr85 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x8965 = CounterChain(name = "x8965", ctr85).iter(16)
    }
    val x8976_dsp0_bank0 = MemoryPipeline(name="x8976_dsp0_bank0",parent="x9046") { implicit CU => 
      val x9044 = ScalarFIFO(size=1,name="x9044").wtPort(x8976_x9044_s)
      val x9023 = CounterChain.copy("x9045_0", "x9023")
      val x8976 = SRAM(size=16,name="x8976",banking = Strided(1)).wtPort(x9044.readPort).rdPort(x8976_x8976_dsp0_bank0_s).rdAddr(x9023(0)).wtAddr(x9023(0))
    }
    val x8976_dsp1_bank0 = MemoryPipeline(name="x8976_dsp1_bank0",parent="x9046") { implicit CU => 
      val x9044 = ScalarFIFO(size=1,name="x9044").wtPort(x8976_x9044_s)
      val x9023 = CounterChain.copy("x9045_0", "x9023")
      val x9118 = CounterChain.copy("x9125", "x9118")
      val x8976 = SRAM(size=16,name="x8976",banking = Strided(1)).wtPort(x9044.readPort).rdPort(x8976_x8976_dsp1_bank0_s).rdAddr(x9118(0)).wtAddr(x9023(0))
    }
    val x8977_dsp0_bank0 = MemoryPipeline(name="x8977_dsp0_bank0",parent="x9115") { implicit CU => 
      val x9113 = ScalarFIFO(size=1,name="x9113").wtPort(x8977_x9113_s)
      val x9092 = CounterChain.copy("x9114_0", "x9092")
      val x8977 = SRAM(size=16,name="x8977",banking = Strided(1)).wtPort(x9113.readPort).rdPort(x8977_x8977_dsp0_bank0_s).rdAddr(x9092(0)).wtAddr(x9092(0))
    }
    val x8977_dsp1_bank0 = MemoryPipeline(name="x8977_dsp1_bank0",parent="x9115") { implicit CU => 
      val x9113 = ScalarFIFO(size=1,name="x9113").wtPort(x8977_x9113_s)
      val x9127 = CounterChain.copy("x9134", "x9127")
      val x9092 = CounterChain.copy("x9114_0", "x9092")
      val x8977 = SRAM(size=16,name="x8977",banking = Strided(1)).wtPort(x9113.readPort).rdPort(x8977_x8977_dsp1_bank0_s).rdAddr(x9127(0)).wtAddr(x9092(0))
    }
    val x9046 = MetaPipeline(name="x9046",parent=x9136) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x8979 = CounterChain(name = "x8979", ctr86).iter(8)
    }
    val x8980_dsp0_bank0 = MemoryPipeline(name="x8980_dsp0_bank0",parent="x9046") { implicit CU => 
      val x9007 = ScalarFIFO(size=1,name="x9007").wtPort(x8980_x9007_s)
      val x8998 = CounterChain.copy("x9008_0", "x8998")
      val x9023 = CounterChain.copy("x9045_0", "x9023")
      val x8980 = SRAM(size=16,name="x8980",banking = Strided(1)).wtPort(x9007.readPort).rdPort(x8980_x8980_dsp0_bank0_s).rdAddr(x9023(0)).wtAddr(x8998(0))
    }
    val x8981_dsp0_bank0 = MemoryPipeline(name="x8981_dsp0_bank0",parent="x9046") { implicit CU => 
      val x9019 = ScalarFIFO(size=1,name="x9019").wtPort(x8981_x9019_s)
      val x9010 = CounterChain.copy("x9020_0", "x9010")
      val x9023 = CounterChain.copy("x9045_0", "x9023")
      val x8981 = SRAM(size=16,name="x8981",banking = Strided(1)).wtPort(x9019.readPort).rdPort(x8981_x8981_dsp0_bank0_s).rdAddr(x9023(0)).wtAddr(x9010(0))
    }
    val x9008_0 = Pipeline(name="x9008_0",parent=x9046) { implicit CU => 
      val x8982 = ScalarBuffer(name="x8982").wtPort(x8902_x8902_dsp0_bank0_s)
      val x9003 = ScalarFIFO(size=1,name="x9003").wtPort(x8837_x8837_dsp0_bank0_s)
      val ctr87 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x8998 = CounterChain(name = "x8998", ctr87).iter(1)
      Stage(operands=List(CU.load(x9003), CU.load(x8982)), op=FixMul, results=List(CU.scalarOut(x8980_x9007_s)))
    }
    val x9020_0 = Pipeline(name="x9020_0",parent=x9046) { implicit CU => 
      val x8983 = ScalarBuffer(name="x8983").wtPort(x8902_x8902_dsp0_bank0_s)
      val x9015 = ScalarFIFO(size=1,name="x9015").wtPort(x8837_x8837_dsp0_bank1_s)
      val ctr88 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9010 = CounterChain(name = "x9010", ctr88).iter(1)
      Stage(operands=List(CU.load(x9015), CU.load(x8983)), op=FixMul, results=List(CU.scalarOut(x8981_x9019_s)))
    }
    val x9045_0 = Pipeline(name="x9045_0",parent=x9046) { implicit CU => 
      val x9039 = CU.temp(None)
      val x9031 = ScalarFIFO(size=1,name="x9031").wtPort(x8976_x8976_dsp0_bank0_s)
      val x9027 = ScalarFIFO(size=1,name="x9027").wtPort(x8980_x8980_dsp0_bank0_s)
      val x9029 = ScalarFIFO(size=1,name="x9029").wtPort(x8981_x8981_dsp0_bank0_s)
      val ctr89 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9023 = CounterChain(name = "x9023", ctr89).iter(1)
      Stage(operands=List(CU.load(x9027), CU.load(x9029)), op=FixAdd, results=List(x9039))
      Stage(operands=List(x9039, CU.load(x9031)), op=FixAdd, results=List(CU.scalarOut(x8976_x9044_s)))
    }
    val x9115 = MetaPipeline(name="x9115",parent=x9136) { implicit CU => 
      val ctr90 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9048 = CounterChain(name = "x9048", ctr90).iter(8)
    }
    val x9049_dsp0_bank0 = MemoryPipeline(name="x9049_dsp0_bank0",parent="x9115") { implicit CU => 
      val x9076 = ScalarFIFO(size=1,name="x9076").wtPort(x9049_x9076_s)
      val x9067 = CounterChain.copy("x9077_0", "x9067")
      val x9092 = CounterChain.copy("x9114_0", "x9092")
      val x9049 = SRAM(size=16,name="x9049",banking = Strided(1)).wtPort(x9076.readPort).rdPort(x9049_x9049_dsp0_bank0_s).rdAddr(x9092(0)).wtAddr(x9067(0))
    }
    val x9050_dsp0_bank0 = MemoryPipeline(name="x9050_dsp0_bank0",parent="x9115") { implicit CU => 
      val x9088 = ScalarFIFO(size=1,name="x9088").wtPort(x9050_x9088_s)
      val x9092 = CounterChain.copy("x9114_0", "x9092")
      val x9079 = CounterChain.copy("x9089_0", "x9079")
      val x9050 = SRAM(size=16,name="x9050",banking = Strided(1)).wtPort(x9088.readPort).rdPort(x9050_x9050_dsp0_bank0_s).rdAddr(x9092(0)).wtAddr(x9079(0))
    }
    val x9077_0 = Pipeline(name="x9077_0",parent=x9115) { implicit CU => 
      val x9051 = ScalarBuffer(name="x9051").wtPort(x8903_x8903_dsp0_bank0_s)
      val x9072 = ScalarFIFO(size=1,name="x9072").wtPort(x8837_x8837_dsp1_bank0_s)
      val ctr91 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9067 = CounterChain(name = "x9067", ctr91).iter(1)
      Stage(operands=List(CU.load(x9072), CU.load(x9051)), op=FixMul, results=List(CU.scalarOut(x9049_x9076_s)))
    }
    val x9089_0 = Pipeline(name="x9089_0",parent=x9115) { implicit CU => 
      val x9052 = ScalarBuffer(name="x9052").wtPort(x8903_x8903_dsp0_bank0_s)
      val x9084 = ScalarFIFO(size=1,name="x9084").wtPort(x8837_x8837_dsp1_bank1_s)
      val ctr92 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9079 = CounterChain(name = "x9079", ctr92).iter(1)
      Stage(operands=List(CU.load(x9084), CU.load(x9052)), op=FixMul, results=List(CU.scalarOut(x9050_x9088_s)))
    }
    val x9114_0 = Pipeline(name="x9114_0",parent=x9115) { implicit CU => 
      val x9108 = CU.temp(None)
      val x9100 = ScalarFIFO(size=1,name="x9100").wtPort(x8977_x8977_dsp0_bank0_s)
      val x9096 = ScalarFIFO(size=1,name="x9096").wtPort(x9049_x9049_dsp0_bank0_s)
      val x9098 = ScalarFIFO(size=1,name="x9098").wtPort(x9050_x9050_dsp0_bank0_s)
      val ctr93 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9092 = CounterChain(name = "x9092", ctr93).iter(1)
      Stage(operands=List(CU.load(x9096), CU.load(x9098)), op=FixAdd, results=List(x9108))
      Stage(operands=List(x9108, CU.load(x9100)), op=FixAdd, results=List(CU.scalarOut(x8977_x9113_s)))
    }
    val x9125 = Pipeline(name="x9125",parent=x9136) { implicit CU => 
      val ctr94 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9118 = CounterChain(name = "x9118", ctr94).iter(16)
    }
    val x9134 = Pipeline(name="x9134",parent=x9136) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9127 = CounterChain(name = "x9127", ctr95).iter(16)
    }
    val x9373 = MetaPipeline(name="x9373",parent=x9397) { implicit CU => 
      val ctr96 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x9138 = CounterChain(name = "x9138", ctr96).iter(32)
    }
    val x9139_dsp0_bank0 = MemoryPipeline(name="x9139_dsp0_bank0",parent="x9373") { implicit CU => 
      val x9177 = ScalarFIFO(size=1,name="x9177").wtPort(x9151_x9168_data_s)
      val x9216 = CounterChain.copy("x9283", "x9216")
      val x9170 = CounterChain.copy("x9178", "x9170")
      val x9139 = SRAM(size=16,name="x9139",banking = Strided(1)).wtPort(x9177.readPort).rdPort(x9139_x9139_dsp0_bank0_s).rdAddr(x9216(0)).rdAddr(x9216(0)).wtAddr(x9170(0))
    }
    val x9140_dsp0_bank0 = MemoryPipeline(name="x9140_dsp0_bank0",parent="x9373") { implicit CU => 
      val x9209 = ScalarFIFO(size=1,name="x9209").wtPort(x9183_x9200_data_s)
      val x9202 = CounterChain.copy("x9210", "x9202")
      val x9285 = CounterChain.copy("x9352", "x9285")
      val x9140 = SRAM(size=16,name="x9140",banking = Strided(1)).wtPort(x9209.readPort).rdPort(x9140_x9140_dsp0_bank0_s).rdAddr(x9285(0)).rdAddr(x9285(0)).wtAddr(x9202(0))
    }
    val x9179 = StreamController(name="x9179",parent=x9373) { implicit CU => 
      val ctr97 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9149 = CounterChain(name = "x9149", ctr97).iter(1)
    }
    val x9167_0 = Pipeline(name="x9167_0",parent=x9179) { implicit CU => 
      val x9157 = CU.temp(None)
      val x9154 = CU.temp(None)
      val x9152 = CU.temp(None)
      val x9156 = CU.temp(None)
      val x9159 = ScalarBuffer(name="x9159").wtPort(a_dram_da)
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x9138 = CounterChain.copy("x9373", "x9138")
      val x9149 = CounterChain.copy("x9179", "x9149")
      val x9167_unit = CounterChain(name = "x9167_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9138(0)), CU.ctr(x9149(0))), op=FixAdd, results=List(x9152))
      Stage(operands=List(x9152, Const(6)), op=FixSla, results=List(x9154))
      Stage(operands=List(x9154, CU.ctr(x8834(0))), op=FixAdd, results=List(x9156))
      Stage(operands=List(x9156, Const(3)), op=FixSla, results=List(x9157))
      Stage(operands=List(x9157, CU.load(x9159)), op=FixAdd, results=List(CU.scalarOut(x9150_b9703_x9166_b9705_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9150_b9704_x9166_b9706_s)))
    }
    val x9168 = MemoryController(name="x9168",parent=x9179,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9150_b9704 = ScalarFIFO(size=1,name="size").wtPort(x9150_b9704_x9166_b9706_s)
      val x9150_b9703 = ScalarFIFO(size=1,name="offset").wtPort(x9150_b9703_x9166_b9705_s)
      CU.newSout("data", x9151_x9168_data_s)
    }
    val x9178 = Pipeline(name="x9178",parent=x9179) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9170 = CounterChain(name = "x9170", ctr98).iter(16)
    }
    val x9211 = StreamController(name="x9211",parent=x9373) { implicit CU => 
      val ctr99 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9181 = CounterChain(name = "x9181", ctr99).iter(1)
    }
    val x9199_0 = Pipeline(name="x9199_0",parent=x9211) { implicit CU => 
      val x9189 = CU.temp(None)
      val x9184 = CU.temp(None)
      val x9186 = CU.temp(None)
      val x9188 = CU.temp(None)
      val x9191 = ScalarBuffer(name="x9191").wtPort(a_dram_da)
      val x8834 = CounterChain.copy("x9397", "x8834")
      val x9199_unit = CounterChain(name = "x9199_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x9138 = CounterChain.copy("x9373", "x9138")
      val x9181 = CounterChain.copy("x9211", "x9181")
      Stage(operands=List(CU.ctr(x9138(0)), CU.ctr(x9181(0))), op=FixAdd, results=List(x9184))
      Stage(operands=List(x9184, Const(6)), op=FixSla, results=List(x9186))
      Stage(operands=List(x9186, CU.ctr(x8834(0))), op=FixAdd, results=List(x9188))
      Stage(operands=List(x9188, Const(3)), op=FixSla, results=List(x9189))
      Stage(operands=List(x9189, CU.load(x9191)), op=FixAdd, results=List(CU.scalarOut(x9182_b9707_x9198_b9709_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9182_b9708_x9198_b9710_s)))
    }
    val x9200 = MemoryController(name="x9200",parent=x9211,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9182_b9708 = ScalarFIFO(size=1,name="size").wtPort(x9182_b9708_x9198_b9710_s)
      val x9182_b9707 = ScalarFIFO(size=1,name="offset").wtPort(x9182_b9707_x9198_b9709_s)
      CU.newSout("data", x9183_x9200_data_s)
    }
    val x9210 = Pipeline(name="x9210",parent=x9211) { implicit CU => 
      val ctr100 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9202 = CounterChain(name = "x9202", ctr100).iter(16)
    }
    val x9213_dsp0_bank0 = MemoryPipeline(name="x9213_dsp0_bank0",parent="x9283") { implicit CU => 
      val x9281 = ScalarFIFO(size=1,name="x9281").wtPort(x9213_x9281_s)
      val x9260 = CounterChain.copy("x9282_0", "x9260")
      val x9213 = SRAM(size=16,name="x9213",banking = Strided(1)).wtPort(x9281.readPort).rdPort(x9213_x9213_dsp0_bank0_s).rdAddr(x9260(0)).wtAddr(x9260(0))
    }
    val x9213_dsp1_bank0 = MemoryPipeline(name="x9213_dsp1_bank0",parent="x9283") { implicit CU => 
      val x9281 = ScalarFIFO(size=1,name="x9281").wtPort(x9213_x9281_s)
      val x9355 = CounterChain.copy("x9362", "x9355")
      val x9260 = CounterChain.copy("x9282_0", "x9260")
      val x9213 = SRAM(size=16,name="x9213",banking = Strided(1)).wtPort(x9281.readPort).rdPort(x9213_x9213_dsp1_bank0_s).rdAddr(x9355(0)).wtAddr(x9260(0))
    }
    val x9214_dsp0_bank0 = MemoryPipeline(name="x9214_dsp0_bank0",parent="x9352") { implicit CU => 
      val x9350 = ScalarFIFO(size=1,name="x9350").wtPort(x9214_x9350_s)
      val x9329 = CounterChain.copy("x9351_0", "x9329")
      val x9214 = SRAM(size=16,name="x9214",banking = Strided(1)).wtPort(x9350.readPort).rdPort(x9214_x9214_dsp0_bank0_s).rdAddr(x9329(0)).wtAddr(x9329(0))
    }
    val x9214_dsp1_bank0 = MemoryPipeline(name="x9214_dsp1_bank0",parent="x9352") { implicit CU => 
      val x9350 = ScalarFIFO(size=1,name="x9350").wtPort(x9214_x9350_s)
      val x9329 = CounterChain.copy("x9351_0", "x9329")
      val x9364 = CounterChain.copy("x9371", "x9364")
      val x9214 = SRAM(size=16,name="x9214",banking = Strided(1)).wtPort(x9350.readPort).rdPort(x9214_x9214_dsp1_bank0_s).rdAddr(x9364(0)).wtAddr(x9329(0))
    }
    val x9283 = MetaPipeline(name="x9283",parent=x9373) { implicit CU => 
      val ctr101 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9216 = CounterChain(name = "x9216", ctr101).iter(8)
    }
    val x9217_dsp0_bank0 = MemoryPipeline(name="x9217_dsp0_bank0",parent="x9283") { implicit CU => 
      val x9244 = ScalarFIFO(size=1,name="x9244").wtPort(x9217_x9244_s)
      val x9235 = CounterChain.copy("x9245_0", "x9235")
      val x9260 = CounterChain.copy("x9282_0", "x9260")
      val x9217 = SRAM(size=16,name="x9217",banking = Strided(1)).wtPort(x9244.readPort).rdPort(x9217_x9217_dsp0_bank0_s).rdAddr(x9260(0)).wtAddr(x9235(0))
    }
    val x9218_dsp0_bank0 = MemoryPipeline(name="x9218_dsp0_bank0",parent="x9283") { implicit CU => 
      val x9256 = ScalarFIFO(size=1,name="x9256").wtPort(x9218_x9256_s)
      val x9247 = CounterChain.copy("x9257_0", "x9247")
      val x9260 = CounterChain.copy("x9282_0", "x9260")
      val x9218 = SRAM(size=16,name="x9218",banking = Strided(1)).wtPort(x9256.readPort).rdPort(x9218_x9218_dsp0_bank0_s).rdAddr(x9260(0)).wtAddr(x9247(0))
    }
    val x9245_0 = Pipeline(name="x9245_0",parent=x9283) { implicit CU => 
      val x9240 = ScalarFIFO(size=1,name="x9240").wtPort(x8838_x8838_dsp0_bank0_s)
      val x9219 = ScalarBuffer(name="x9219").wtPort(x9139_x9139_dsp0_bank0_s)
      val ctr102 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9235 = CounterChain(name = "x9235", ctr102).iter(1)
      Stage(operands=List(CU.load(x9240), CU.load(x9219)), op=FixMul, results=List(CU.scalarOut(x9217_x9244_s)))
    }
    val x9257_0 = Pipeline(name="x9257_0",parent=x9283) { implicit CU => 
      val x9252 = ScalarFIFO(size=1,name="x9252").wtPort(x8838_x8838_dsp0_bank1_s)
      val x9220 = ScalarBuffer(name="x9220").wtPort(x9139_x9139_dsp0_bank0_s)
      val ctr103 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9247 = CounterChain(name = "x9247", ctr103).iter(1)
      Stage(operands=List(CU.load(x9252), CU.load(x9220)), op=FixMul, results=List(CU.scalarOut(x9218_x9256_s)))
    }
    val x9282_0 = Pipeline(name="x9282_0",parent=x9283) { implicit CU => 
      val x9276 = CU.temp(None)
      val x9264 = ScalarFIFO(size=1,name="x9264").wtPort(x9217_x9217_dsp0_bank0_s)
      val x9266 = ScalarFIFO(size=1,name="x9266").wtPort(x9218_x9218_dsp0_bank0_s)
      val x9268 = ScalarFIFO(size=1,name="x9268").wtPort(x9213_x9213_dsp0_bank0_s)
      val ctr104 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9260 = CounterChain(name = "x9260", ctr104).iter(1)
      Stage(operands=List(CU.load(x9264), CU.load(x9266)), op=FixAdd, results=List(x9276))
      Stage(operands=List(x9276, CU.load(x9268)), op=FixAdd, results=List(CU.scalarOut(x9213_x9281_s)))
    }
    val x9352 = MetaPipeline(name="x9352",parent=x9373) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9285 = CounterChain(name = "x9285", ctr105).iter(8)
    }
    val x9286_dsp0_bank0 = MemoryPipeline(name="x9286_dsp0_bank0",parent="x9352") { implicit CU => 
      val x9313 = ScalarFIFO(size=1,name="x9313").wtPort(x9286_x9313_s)
      val x9304 = CounterChain.copy("x9314_0", "x9304")
      val x9329 = CounterChain.copy("x9351_0", "x9329")
      val x9286 = SRAM(size=16,name="x9286",banking = Strided(1)).wtPort(x9313.readPort).rdPort(x9286_x9286_dsp0_bank0_s).rdAddr(x9329(0)).wtAddr(x9304(0))
    }
    val x9287_dsp0_bank0 = MemoryPipeline(name="x9287_dsp0_bank0",parent="x9352") { implicit CU => 
      val x9325 = ScalarFIFO(size=1,name="x9325").wtPort(x9287_x9325_s)
      val x9329 = CounterChain.copy("x9351_0", "x9329")
      val x9316 = CounterChain.copy("x9326_0", "x9316")
      val x9287 = SRAM(size=16,name="x9287",banking = Strided(1)).wtPort(x9325.readPort).rdPort(x9287_x9287_dsp0_bank0_s).rdAddr(x9329(0)).wtAddr(x9316(0))
    }
    val x9314_0 = Pipeline(name="x9314_0",parent=x9352) { implicit CU => 
      val x9309 = ScalarFIFO(size=1,name="x9309").wtPort(x8838_x8838_dsp1_bank0_s)
      val x9288 = ScalarBuffer(name="x9288").wtPort(x9140_x9140_dsp0_bank0_s)
      val ctr106 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9304 = CounterChain(name = "x9304", ctr106).iter(1)
      Stage(operands=List(CU.load(x9309), CU.load(x9288)), op=FixMul, results=List(CU.scalarOut(x9286_x9313_s)))
    }
    val x9326_0 = Pipeline(name="x9326_0",parent=x9352) { implicit CU => 
      val x9321 = ScalarFIFO(size=1,name="x9321").wtPort(x8838_x8838_dsp1_bank1_s)
      val x9289 = ScalarBuffer(name="x9289").wtPort(x9140_x9140_dsp0_bank0_s)
      val ctr107 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9316 = CounterChain(name = "x9316", ctr107).iter(1)
      Stage(operands=List(CU.load(x9321), CU.load(x9289)), op=FixMul, results=List(CU.scalarOut(x9287_x9325_s)))
    }
    val x9351_0 = Pipeline(name="x9351_0",parent=x9352) { implicit CU => 
      val x9345 = CU.temp(None)
      val x9333 = ScalarFIFO(size=1,name="x9333").wtPort(x9286_x9286_dsp0_bank0_s)
      val x9335 = ScalarFIFO(size=1,name="x9335").wtPort(x9287_x9287_dsp0_bank0_s)
      val x9337 = ScalarFIFO(size=1,name="x9337").wtPort(x9214_x9214_dsp0_bank0_s)
      val ctr108 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9329 = CounterChain(name = "x9329", ctr108).iter(1)
      Stage(operands=List(CU.load(x9333), CU.load(x9335)), op=FixAdd, results=List(x9345))
      Stage(operands=List(x9345, CU.load(x9337)), op=FixAdd, results=List(CU.scalarOut(x9214_x9350_s)))
    }
    val x9362 = Pipeline(name="x9362",parent=x9373) { implicit CU => 
      val ctr109 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9355 = CounterChain(name = "x9355", ctr109).iter(16)
    }
    val x9371 = Pipeline(name="x9371",parent=x9373) { implicit CU => 
      val ctr110 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9364 = CounterChain(name = "x9364", ctr110).iter(16)
    }
    val x9396_0 = Pipeline(name="x9396_0",parent=x9397) { implicit CU => 
      val x9390 = CU.temp(None)
      val x9380 = ScalarFIFO(size=1,name="x9380").wtPort(x8835_x8835_dsp0_bank0_s).wtPort(x8835_x8835_dsp0_bank1_s)
      val x9382 = ScalarFIFO(size=1,name="x9382").wtPort(x8836_x8836_dsp0_bank0_s).wtPort(x8836_x8836_dsp0_bank1_s)
      val x9384 = ScalarFIFO(size=1,name="x9384").wtPort(x7702_x7702_dsp0_bank0_s)
      val ctr111 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr112 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9377 = CounterChain(name = "x9377", ctr111, ctr112).iter(64)
      Stage(operands=List(CU.load(x9380), CU.load(x9382)), op=FixAdd, results=List(x9390))
      Stage(operands=List(x9390, CU.load(x9384)), op=FixAdd, results=List(CU.scalarOut(x7702_x9395_s)))
    }
    val x9437 = StreamController(name="x9437",parent=x9503) { implicit CU => 
      val ctr113 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x9407 = CounterChain(name = "x9407", ctr113).iter(64)
    }
    val x9423_0 = Pipeline(name="x9423_0",parent=x9437) { implicit CU => 
      val x9412 = CU.temp(None)
      val x9414 = CU.temp(None)
      val x9415 = CU.temp(None)
      val x9417 = ScalarBuffer(name="x9417").wtPort(c_dram_da)
      val x9407 = CounterChain.copy("x9437", "x9407")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x9423_unit = CounterChain(name = "x9423_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9407(0)), Const(6)), op=FixSla, results=List(x9412))
      Stage(operands=List(x9412, CU.ctr(x7699(0))), op=FixAdd, results=List(x9414))
      Stage(operands=List(x9414, Const(3)), op=FixSla, results=List(x9415))
      Stage(operands=List(x9415, CU.load(x9417)), op=FixAdd, results=List(CU.scalarOut(x9408_b9731_x9422_b9733_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9408_b9732_x9422_b9734_s)))
    }
    val x9432 = Pipeline(name="x9432",parent=x9437) { implicit CU => 
      val ctr114 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9425 = CounterChain(name = "x9425", ctr114).iter(1)
    }
    val x9433 = MemoryController(name="x9433",parent=x9437,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x9408_b9731 = ScalarFIFO(size=1,name="offset").wtPort(x9408_b9731_x9422_b9733_s)
      val x9408_b9732 = ScalarFIFO(size=1,name="size").wtPort(x9408_b9732_x9422_b9734_s)
      val x9409 = ScalarFIFO(size=1,name="data").wtPort(x7700_x7700_dsp1_bank0_s)
    }
    val x9469 = StreamController(name="x9469",parent=x9503) { implicit CU => 
      val ctr115 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x9439 = CounterChain(name = "x9439", ctr115).iter(64)
    }
    val x9455_0 = Pipeline(name="x9455_0",parent=x9469) { implicit CU => 
      val x9446 = CU.temp(None)
      val x9444 = CU.temp(None)
      val x9447 = CU.temp(None)
      val x9449 = ScalarBuffer(name="x9449").wtPort(c_dram_da)
      val x9439 = CounterChain.copy("x9469", "x9439")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x9455_unit = CounterChain(name = "x9455_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9439(0)), Const(6)), op=FixSla, results=List(x9444))
      Stage(operands=List(x9444, CU.ctr(x7699(0))), op=FixAdd, results=List(x9446))
      Stage(operands=List(x9446, Const(3)), op=FixSla, results=List(x9447))
      Stage(operands=List(x9447, CU.load(x9449)), op=FixAdd, results=List(CU.scalarOut(x9440_b9737_x9454_b9739_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9440_b9738_x9454_b9740_s)))
    }
    val x9464 = Pipeline(name="x9464",parent=x9469) { implicit CU => 
      val ctr116 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9457 = CounterChain(name = "x9457", ctr116).iter(1)
    }
    val x9465 = MemoryController(name="x9465",parent=x9469,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x9440_b9737 = ScalarFIFO(size=1,name="offset").wtPort(x9440_b9737_x9454_b9739_s)
      val x9441 = ScalarFIFO(size=1,name="data").wtPort(x7701_x7701_dsp1_bank0_s)
      val x9440_b9738 = ScalarFIFO(size=1,name="size").wtPort(x9440_b9738_x9454_b9740_s)
    }
    val x9501 = StreamController(name="x9501",parent=x9503) { implicit CU => 
      val ctr117 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x9471 = CounterChain(name = "x9471", ctr117).iter(64)
    }
    val x9487_0 = Pipeline(name="x9487_0",parent=x9501) { implicit CU => 
      val x9478 = CU.temp(None)
      val x9479 = CU.temp(None)
      val x9476 = CU.temp(None)
      val x9481 = ScalarBuffer(name="x9481").wtPort(c_dram_da)
      val x9471 = CounterChain.copy("x9501", "x9471")
      val x7699 = CounterChain.copy("x9503", "x7699")
      val x9487_unit = CounterChain(name = "x9487_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9471(0)), Const(6)), op=FixSla, results=List(x9476))
      Stage(operands=List(x9476, CU.ctr(x7699(0))), op=FixAdd, results=List(x9478))
      Stage(operands=List(x9478, Const(3)), op=FixSla, results=List(x9479))
      Stage(operands=List(x9479, CU.load(x9481)), op=FixAdd, results=List(CU.scalarOut(x9472_b9743_x9486_b9745_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9472_b9744_x9486_b9746_s)))
    }
    val x9496 = Pipeline(name="x9496",parent=x9501) { implicit CU => 
      val ctr118 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9489 = CounterChain(name = "x9489", ctr118).iter(1)
    }
    val x9497 = MemoryController(name="x9497",parent=x9501,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x9473 = ScalarFIFO(size=1,name="data").wtPort(x7702_x7702_dsp1_bank0_s)
      val x9472_b9744 = ScalarFIFO(size=1,name="size").wtPort(x9472_b9744_x9486_b9746_s)
      val x9472_b9743 = ScalarFIFO(size=1,name="offset").wtPort(x9472_b9743_x9486_b9745_s)
    }
    
  }
}
