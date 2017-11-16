import pir._
import pir.node._
import arch._
import pirc.enums._

object FifoLoadStore extends PIRApp {
  def main(top:Top) = {
    val x907_oc = OffChip("x907")
    val x930_b981_x940_b983_s = Scalar("x930_b981_x940_b983")
    val x911_b977_x920_b979_s = Scalar("x911_b977_x920_b979")
    val x910_x910_dsp0_bank0_data_v = Vector("x910_x910_dsp0_bank0_data")
    val x912_x922_data_v = Vector("x912_x922_data")
    val x911_b978_x920_b980_s = Scalar("x911_b978_x920_b980")
    val x916_da = DRAMAddress("x916", "x907")
    val x930_b982_x940_b984_s = Scalar("x930_b982_x940_b984")
    val x936_da = DRAMAddress("x936", "x908")
    val x908_oc = OffChip("x908")
    val x910_dsp0_bank0 = MemoryPipeline(name="x910_dsp0_bank0",parent="top") { implicit CU => 
      val x927 = new VectorFIFO()
        .size(1)
        .name("x927")
        .store(x912_x922_data_v, None, None)
      val x910 = new SRAM()
        .size(2)
        .mode(FifoMode)
        .name("x910")
        .banking(Strided(1,16))
        .buffering(1)
        .store(x927.readPort, None, None)
        .load(x910_x910_dsp0_bank0_data_v, None, None)
    }
    val x953 = Sequential(name="x953",parent="top") { implicit CU => 
      val x953_unit = CounterChain(name = "x953_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x929 = StreamController(name="x929",parent="x953") { implicit CU => 
      val x929_unit = CounterChain(name = "x929_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x921_0 = Pipeline(name="x921_0",parent="x929") { implicit CU => 
      val x914 = CU.temp(None).name("x914")
      val x916 = new ScalarBuffer()
        .name("x916")
        .buffering(1)
        .store(x916_da, None, None)
      val x921_unit = CounterChain(name = "x921_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x914))
      Stage(operands=List(x914, x916), op=FixAdd, results=List(CU.scalarOut(x911_b977_x920_b979_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x911_b978_x920_b980_s)))
    }
    val x922 = MemoryController(name="x922",parent="x929",offchip=x907_oc, mctpe=TileLoad) { implicit CU => 
      val x911_b977 = new ScalarFIFO()
        .size(1)
        .name("offset")
        .store(x911_b977_x920_b979_s, None, None)
      val x911_b978 = new ScalarFIFO()
        .size(1)
        .name("size")
        .store(x911_b978_x920_b980_s, None, None)
      CU.newOut("data", x912_x922_data_v)
    }
    val x952 = StreamController(name="x952",parent="x953") { implicit CU => 
      val x952_unit = CounterChain(name = "x952_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x941_0 = Pipeline(name="x941_0",parent="x952") { implicit CU => 
      val x934 = CU.temp(None).name("x934")
      val x936 = new ScalarBuffer()
        .name("x936")
        .buffering(1)
        .store(x936_da, None, None)
      val x941_unit = CounterChain(name = "x941_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x934))
      Stage(operands=List(x934, x936), op=FixAdd, results=List(CU.scalarOut(x930_b981_x940_b983_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x930_b982_x940_b984_s)))
    }
    val x949 = MemoryController(name="x949",parent="x952",offchip=x908_oc, mctpe=TileStore) { implicit CU => 
      val x930_b982 = new ScalarFIFO()
        .size(1)
        .name("size")
        .store(x930_b982_x940_b984_s, None, None)
      val x931 = new VectorFIFO()
        .size(1)
        .name("data")
        .store(x910_x910_dsp0_bank0_data_v, None, None)
      val x930_b981 = new ScalarFIFO()
        .size(1)
        .name("offset")
        .store(x930_b981_x940_b983_s, None, None)
    }
    
  }
}
