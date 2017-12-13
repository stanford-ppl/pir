import pir._
import pir.node._
import arch._
import pirc.enums._

object SimpleIf extends PIRApp {
  def main(top:Top) = {
    val x733_x776_data_s = Scalar("x733_x776_data")
    val in_argin = ArgIn("in").bound(43)
    val x733_x776_wa_s = Scalar("x733_x776_wa")
    val x733_x764_wa_s = Scalar("x733_x764_wa")
    val x733_x769_data_s = Scalar("x733_x769_data")
    val x733_x733_dsp0_bank2_data_s = Scalar("x733_x733_dsp0_bank2_data")
    val x733_x733_dsp0_bank1_data_s = Scalar("x733_x733_dsp0_bank1_data")
    val x733_x788_wa_s = Scalar("x733_x788_wa")
    val x731_x798_argout = ArgOut("x731_x798")
    val x733_x788_data_s = Scalar("x733_x788_data")
    val x733_x781_data_s = Scalar("x733_x781_data")
    val x733_x781_wa_s = Scalar("x733_x781_wa")
    val x733_x769_wa_s = Scalar("x733_x769_wa")
    val x733_x764_data_s = Scalar("x733_x764_data")
    val x733_x793_wa_s = Scalar("x733_x793_wa")
    val x733_x733_dsp0_bank0_data_s = Scalar("x733_x733_dsp0_bank0_data")
    val x733_x793_data_s = Scalar("x733_x793_data")
    val x733_dsp0_bank0 = MemoryPipeline(name="x733_dsp0_bank0",parent="top") { implicit CU => 
      val x769 = new ScalarFIFO()
        .size(1)
        .name("x769")
        .store(x733_x769_data_s, None, None)
      val x764 = new ScalarFIFO()
        .size(1)
        .name("x764")
        .store(x733_x764_data_s, None, None)
      val b806 = new ScalarFIFO()
        .size(1)
        .name("b806")
        .store(x733_x769_wa_s, None, None)
      val b805 = new ScalarFIFO()
        .size(1)
        .name("b805")
        .store(x733_x764_wa_s, None, None)
      val x733 = new SRAM()
        .size(1)
        .mode(SramMode)
        .name("x733")
        .banking(NoBanking())
        .buffering(1)
        .store(x764, Some(b805), None)
        .store(x769, Some(b806), None)
        .load(x733_x733_dsp0_bank0_data_s, Some(Const(2)), None)
    }
    val x733_dsp0_bank2 = MemoryPipeline(name="x733_dsp0_bank2",parent="top") { implicit CU => 
      val b810 = new ScalarFIFO()
        .size(1)
        .name("b810")
        .store(x733_x793_wa_s, None, None)
      val x793 = new ScalarFIFO()
        .size(1)
        .name("x793")
        .store(x733_x793_data_s, None, None)
      val b809 = new ScalarFIFO()
        .size(1)
        .name("b809")
        .store(x733_x788_wa_s, None, None)
      val x788 = new ScalarFIFO()
        .size(1)
        .name("x788")
        .store(x733_x788_data_s, None, None)
      val x733 = new SRAM()
        .size(1)
        .mode(SramMode)
        .name("x733")
        .banking(NoBanking())
        .buffering(1)
        .store(x788, Some(b809), None)
        .store(x793, Some(b810), None)
        .load(x733_x733_dsp0_bank2_data_s, Some(Const(2)), None)
    }
    val x733_dsp0_bank1 = MemoryPipeline(name="x733_dsp0_bank1",parent="top") { implicit CU => 
      val x776 = new ScalarFIFO()
        .size(1)
        .name("x776")
        .store(x733_x776_data_s, None, None)
      val x781 = new ScalarFIFO()
        .size(1)
        .name("x781")
        .store(x733_x781_data_s, None, None)
      val b808 = new ScalarFIFO()
        .size(1)
        .name("b808")
        .store(x733_x781_wa_s, None, None)
      val b807 = new ScalarFIFO()
        .size(1)
        .name("b807")
        .store(x733_x776_wa_s, None, None)
      val x733 = new SRAM()
        .size(1)
        .mode(SramMode)
        .name("x733")
        .banking(NoBanking())
        .buffering(1)
        .store(x776, Some(b807), None)
        .store(x781, Some(b808), None)
        .load(x733_x733_dsp0_bank1_data_s, Some(Const(2)), None)
    }
    val x796 = MetaPipeline(name="x796",parent="top") { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(3), step=Const(1), par=3) // Counter
      val x735 = CounterChain(name = "x735", ctr1).iter(1)
    }
    val x771 = StreamController(name="x771",parent="x796") { implicit CU => 
    }
    val x765_0 = Pipeline(name="x765_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x764_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x764_wa_s))
    }
    val x770_0 = Pipeline(name="x770_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(x735(0), Const(1)), op=FixAdd, results=List(x733_x769_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x769_wa_s))
    }
    val x783 = StreamController(name="x783",parent="x796") { implicit CU => 
    }
    val x777_0 = Pipeline(name="x777_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x776_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x776_wa_s))
    }
    val x782_0 = Pipeline(name="x782_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(x735(0), Const(1)), op=FixAdd, results=List(x733_x781_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x781_wa_s))
    }
    val x795 = StreamController(name="x795",parent="x796") { implicit CU => 
    }
    val x789_0 = Pipeline(name="x789_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x788_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x788_wa_s))
    }
    val x794_0 = Pipeline(name="x794_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(x735(0), Const(1)), op=FixAdd, results=List(x733_x793_data_s))
      Stage(operands=List(x735(0)), op=Bypass, results=List(x733_x793_wa_s))
    }
    val x799_0 = Pipeline(name="x799_0",parent="top") { implicit CU => 
      val x797 = new ScalarFIFO()
        .size(1)
        .name("x797")
        .store(x733_x733_dsp0_bank0_data_s, None, None)
        .store(x733_x733_dsp0_bank2_data_s, None, None)
        .store(x733_x733_dsp0_bank1_data_s, None, None)
      val x799_unit = CounterChain(name = "x799_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x797), op=Bypass, results=List(x731_x798_argout))
    }
    
  }
}
