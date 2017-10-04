import pir._
import pir.node._
import arch._
import pirc.enums._

object SimpleIf extends PIRApp {
  def main(top:Top) = {
    val x738_x757_s = Scalar("x738_x757")
    val x733_x776_data_s = Scalar("x733_x776_data")
    val in_argin = ArgIn("in").bound(43)
    val x733_x776_wa_s = Scalar("x733_x776_wa")
    val x740_x752_s = Scalar("x740_x752")
    val x733_x764_wa_s = Scalar("x733_x764_wa")
    val x733_x769_data_s = Scalar("x733_x769_data")
    val x736_x745_s = Scalar("x736_x745")
    val x739_x746_s = Scalar("x739_x746")
    val x733_x733_dsp0_bank2_data_s = Scalar("x733_x733_dsp0_bank2_data")
    val x733_x733_dsp0_bank1_data_s = Scalar("x733_x733_dsp0_bank1_data")
    val x733_x788_wa_s = Scalar("x733_x788_wa")
    val x733_x797_ra_s = Scalar("x733_x797_ra")
    val x731_x798_argout = ArgOut("x731_x798")
    val x733_x788_data_s = Scalar("x733_x788_data")
    val x737_x751_s = Scalar("x737_x751")
    val x733_x781_data_s = Scalar("x733_x781_data")
    val x741_x758_s = Scalar("x741_x758")
    val x733_x781_wa_s = Scalar("x733_x781_wa")
    val x733_x769_wa_s = Scalar("x733_x769_wa")
    val x733_x764_data_s = Scalar("x733_x764_data")
    val x733_x793_wa_s = Scalar("x733_x793_wa")
    val x733_x733_dsp0_bank0_data_s = Scalar("x733_x733_dsp0_bank0_data")
    val x733_x793_data_s = Scalar("x733_x793_data")
    val x733_dsp0_bank0 = MemoryPipeline(name="x733_dsp0_bank0",parent="top") { implicit CU => 
      val b805 = ScalarFIFO(size=1,name="b805").wtPort(x733_x764_wa_s)
      val x764 = ScalarFIFO(size=1,name="x764").wtPort(x733_x764_data_s)
      val b806 = ScalarFIFO(size=1,name="b806").wtPort(x733_x769_wa_s)
      val x769 = ScalarFIFO(size=1,name="x769").wtPort(x733_x769_data_s)
      val b811 = ScalarFIFO(size=1,name="b811").wtPort(x733_x797_ra_s)
      val x733 = SRAM(size=1,name="x733",banking = NoBanking()).wtPort(x764.readPort).wtPort(x769.readPort).rdPort(x733_x733_dsp0_bank0_data_s).rdAddr(b811.readPort).wtAddr(b805.readPort).wtAddr(b806.readPort)
    }
    val x733_dsp0_bank2 = MemoryPipeline(name="x733_dsp0_bank2",parent="top") { implicit CU => 
      val b809 = ScalarFIFO(size=1,name="b809").wtPort(x733_x788_wa_s)
      val x788 = ScalarFIFO(size=1,name="x788").wtPort(x733_x788_data_s)
      val x793 = ScalarFIFO(size=1,name="x793").wtPort(x733_x793_data_s)
      val b811 = ScalarFIFO(size=1,name="b811").wtPort(x733_x797_ra_s)
      val b810 = ScalarFIFO(size=1,name="b810").wtPort(x733_x793_wa_s)
      val x733 = SRAM(size=1,name="x733",banking = NoBanking()).wtPort(x788.readPort).wtPort(x793.readPort).rdPort(x733_x733_dsp0_bank2_data_s).rdAddr(b811.readPort).wtAddr(b809.readPort).wtAddr(b810.readPort)
    }
    val x733_dsp0_bank1 = MemoryPipeline(name="x733_dsp0_bank1",parent="top") { implicit CU => 
      val b807 = ScalarFIFO(size=1,name="b807").wtPort(x733_x776_wa_s)
      val x781 = ScalarFIFO(size=1,name="x781").wtPort(x733_x781_data_s)
      val x776 = ScalarFIFO(size=1,name="x776").wtPort(x733_x776_data_s)
      val b811 = ScalarFIFO(size=1,name="b811").wtPort(x733_x797_ra_s)
      val b808 = ScalarFIFO(size=1,name="b808").wtPort(x733_x781_wa_s)
      val x733 = SRAM(size=1,name="x733",banking = NoBanking()).wtPort(x776.readPort).wtPort(x781.readPort).rdPort(x733_x733_dsp0_bank1_data_s).rdAddr(b811.readPort).wtAddr(b807.readPort).wtAddr(b808.readPort)
    }
    val x796 = MetaPipeline(name="x796",parent="top") { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(3), step=Const(1), par=3) // Counter
      val x735 = CounterChain(name = "x735", ctr1).iter(1)
    }
    val x747_0 = Pipeline(name="x747_0",parent="x796") { implicit CU => 
      val x744 = CU.temp(None)
      val x730 = ScalarBuffer(name="x730").wtPort(in_argin)
      val x747_unit = CounterChain(name = "x747_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(42), CU.load(x730)), op=FixLeq, results=List(CU.scalarOut(x736_x745_s)))
      Stage(operands=List(x744), op=Bypass, results=List(CU.scalarOut(x739_x746_s)))
    }
    val x753_0 = Pipeline(name="x753_0",parent="x796") { implicit CU => 
      val x750 = CU.temp(None)
      val x730 = ScalarBuffer(name="x730").wtPort(in_argin)
      val x753_unit = CounterChain(name = "x753_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(42), CU.load(x730)), op=FixLeq, results=List(CU.scalarOut(x737_x751_s)))
      Stage(operands=List(x750), op=Bypass, results=List(CU.scalarOut(x740_x752_s)))
    }
    val x759_0 = Pipeline(name="x759_0",parent="x796") { implicit CU => 
      val x756 = CU.temp(None)
      val x730 = ScalarBuffer(name="x730").wtPort(in_argin)
      val x759_unit = CounterChain(name = "x759_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(42), CU.load(x730)), op=FixLeq, results=List(CU.scalarOut(x738_x757_s)))
      Stage(operands=List(x756), op=Bypass, results=List(CU.scalarOut(x741_x758_s)))
    }
    val x771 = StreamController(name="x771",parent="x796") { implicit CU => 
      val x736 = ScalarBuffer(name="x736").wtPort(x736_x745_s)
      val x739 = ScalarBuffer(name="x739").wtPort(x739_x746_s)
    }
    val x765_0 = Pipeline(name="x765_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x764_data_s)))
    }
    val x764_0 = Pipeline(name="x764_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x764_wa_s)))
    }
    val x770_0 = Pipeline(name="x770_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x735(0)), Const(1)), op=FixAdd, results=List(CU.scalarOut(x733_x769_data_s)))
    }
    val x769_0 = Pipeline(name="x769_0",parent="x771") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x769_wa_s)))
    }
    val x783 = StreamController(name="x783",parent="x796") { implicit CU => 
      val x737 = ScalarBuffer(name="x737").wtPort(x737_x751_s)
      val x740 = ScalarBuffer(name="x740").wtPort(x740_x752_s)
    }
    val x777_0 = Pipeline(name="x777_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x776_data_s)))
    }
    val x776_0 = Pipeline(name="x776_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x776_wa_s)))
    }
    val x782_0 = Pipeline(name="x782_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(CU.ctr(x735(0)), Const(1)), op=FixAdd, results=List(CU.scalarOut(x733_x781_data_s)))
    }
    val x781_0 = Pipeline(name="x781_0",parent="x783") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 1)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x781_wa_s)))
    }
    val x795 = StreamController(name="x795",parent="x796") { implicit CU => 
      val x738 = ScalarBuffer(name="x738").wtPort(x738_x757_s)
      val x741 = ScalarBuffer(name="x741").wtPort(x741_x758_s)
    }
    val x789_0 = Pipeline(name="x789_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x788_data_s)))
    }
    val x788_0 = Pipeline(name="x788_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x788_wa_s)))
    }
    val x794_0 = Pipeline(name="x794_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(CU.ctr(x735(0)), Const(1)), op=FixAdd, results=List(CU.scalarOut(x733_x793_data_s)))
    }
    val x793_0 = Pipeline(name="x793_0",parent="x795") { implicit CU => 
      val x735 = CounterChain.copy("x796", "x735").iterIdx(0, 2)
      Stage(operands=List(CU.ctr(x735(0))), op=Bypass, results=List(CU.scalarOut(x733_x793_wa_s)))
    }
    val x799_0 = Pipeline(name="x799_0",parent="top") { implicit CU => 
      val x797 = ScalarFIFO(size=1,name="x797").wtPort(x733_x733_dsp0_bank0_data_s).wtPort(x733_x733_dsp0_bank2_data_s).wtPort(x733_x733_dsp0_bank1_data_s)
      val x799_unit = CounterChain(name = "x799_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x797)), op=Bypass, results=List(CU.scalarOut(x731_x798_argout)))
    }
    val x797_0 = Pipeline(name="x797_0",parent="top") { implicit CU => 
      Stage(operands=List(Const(2)), op=Bypass, results=List(CU.scalarOut(x733_x797_ra_s)))
    }
    
  }
}
