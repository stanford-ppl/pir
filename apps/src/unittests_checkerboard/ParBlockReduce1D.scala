import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object ParBlockReduce1D extends PIRApp {
  arch=new SN(3,2,pattern=Checkerboard)
  def main(top:Top) = {
    val x912_b991_x920_b993_s = Scalar("x912_b991_x920_b993")
    val dstFPGA_oc = OffChip("dstFPGA")
    val sizeIn_argin = ArgIn("sizeIn")
    val x911_x933_x939_v = Vector("x911_x933_x939")
    val x907_x938_v = Vector("x907_x938")
    val x907_x934_x939_v = Vector("x907_x934_x939")
    val x942_b995_x950_b997_s = Scalar("x942_b995_x950_b997")
    val srcFPGA_oc = OffChip("srcFPGA")
    val dstFPGA_addr_da = DRAMAddress("dstFPGA_addr", "dstFPGA")
    val x942_b996_x950_b998_s = Scalar("x942_b996_x950_b998")
    val x912_b992_x920_b994_s = Scalar("x912_b992_x920_b994")
    val srcFPGA_addr_da = DRAMAddress("srcFPGA_addr", "srcFPGA")
    val x913_x922_data_v = Vector("x913_x922_data")
    val x907_x954_x958_v = Vector("x907_x954_x958")
    val x967 = Sequential(name="x967",parent=top) { implicit CU => 
      val x967_unit = CounterChain(name = "x967_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x907_dsp0 = MemoryPipeline(name="x907_dsp0",parent="x941") { implicit CU => 
      val x938_x938 = VectorFIFO(size=1).wtPort(x907_x938_v)
      val x932 = CounterChain.copy("x939_0", "x932")
      val x953 = CounterChain.copy("x958", "x953")
      val x907_x954 = SRAM(size=16,banking = Strided(1)).wtPort(x938_x938.readPort).rdPort(x907_x954_x958_v).rdAddr(x953(0)).wtAddr(x932(0))
    }
    val x907_dsp1 = MemoryPipeline(name="x907_dsp1",parent="x941") { implicit CU => 
      val x938_x938 = VectorFIFO(size=1).wtPort(x907_x938_v)
      val x932 = CounterChain.copy("x939_0", "x932")
      val x907_x934 = SRAM(size=16,banking = Strided(1)).wtPort(x938_x938.readPort).rdPort(x907_x934_x939_v).rdAddr(x932(0)).wtAddr(x932(0))
    }
    val x941 = MetaPipeline(name="x941",parent=x967) { implicit CU => 
      val x901_x908 = ScalarBuffer().wtPort(sizeIn_argin)
      val ctr1 = Counter(min=Const(0), max=x901_x908.load, step=Const(16), par=1) // Counter
      val x910 = CounterChain(name = "x910", ctr1).iter(1)
    }
    val x911_dsp0 = MemoryPipeline(name="x911_dsp0",parent="x941") { implicit CU => 
      val x928_x928 = VectorFIFO(size=1).wtPort(x913_x922_data_v)
      val x924 = CounterChain.copy("x929", "x924")
      val x932 = CounterChain.copy("x939_0", "x932")
      val x911_x933 = SRAM(size=16,banking = Strided(1)).wtPort(x928_x928.readPort).rdPort(x911_x933_x939_v).rdAddr(x932(0)).wtAddr(x924(0))
    }
    val x930 = StreamController(name="x930",parent=x941) { implicit CU => 
      val x930_unit = CounterChain(name = "x930_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x921_0 = Pipeline(name="x921_0",parent=x930) { implicit CU => 
      val x915 = CU.temp()
      val x914 = ScalarBuffer().wtPort(srcFPGA_addr_da)
      val x910 = CounterChain.copy("x941", "x910")
      val x921_unit = CounterChain(name = "x921_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x910(0)), Const(4)), op=FixMul, results=List(x915))
      Stage(operands=List(x915, CU.load(x914)), op=FixAdd, results=List(CU.scalarOut(x912_b991_x920_b993_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x912_b992_x920_b994_s)))
    }
    val x922 = MemoryController(name="x922",parent=x930,offchip=srcFPGA_oc, mctpe=TileLoad) { implicit CU => 
      val x912_b992_x922 = ScalarFIFO(name="size",size=1).wtPort(x912_b992_x920_b994_s)
      val x912_b991_x922 = ScalarFIFO(name="offset",size=1).wtPort(x912_b991_x920_b993_s)
      CU.newVout("data", x913_x922_data_v)
    }
    val x929 = Pipeline(name="x929",parent=x930) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x924 = CounterChain(name = "x924", ctr2).iter(1)
    }
    val x939_0 = Pipeline(name="x939_0",parent=x941) { implicit CU => 
      val x934_x934 = VectorFIFO(size=1).wtPort(x907_x934_x939_v)
      val x933_x933 = VectorFIFO(size=1).wtPort(x911_x933_x939_v)
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x932 = CounterChain(name = "x932", ctr3).iter(16)
      val x910 = CounterChain.copy(x941, "x910")
      val x1938 = CU.temp()
      val x1939 = CU.temp()
      Stage(operands=List(CU.ctr(x910(0)), Const(0)), op=FixEql, results=List(x1938))
      Stage(operands=List(x1938, Const(0), CU.load(x934_x934)), op=Mux, results=List(x1939))
      Stage(operands=List(CU.load(x933_x933), x1939), op=FixAdd, results=List(CU.vecOut(x907_x938_v)))
    }
    val x966 = StreamController(name="x966",parent=x967) { implicit CU => 
      val x966_unit = CounterChain(name = "x966_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x951_0 = Pipeline(name="x951_0",parent=x966) { implicit CU => 
      val x945 = ScalarBuffer().wtPort(dstFPGA_addr_da)
      val x951_unit = CounterChain(name = "x951_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x945)), op=FixAdd, results=List(CU.scalarOut(x942_b995_x950_b997_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x942_b996_x950_b998_s)))
    }
    val x958 = Pipeline(name="x958",parent=x966) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x953 = CounterChain(name = "x953", ctr4).iter(1)
    }
    val x960 = MemoryController(name="x960",parent=x966,offchip=dstFPGA_oc, mctpe=TileStore) { implicit CU => 
      val x942_b995_x960 = ScalarFIFO(name="offset",size=1).wtPort(x942_b995_x950_b997_s)
      val x943_x960 = VectorFIFO(name="data",size=1).wtPort(x907_x954_x958_v)
      val x942_b996_x960 = ScalarFIFO(name="size",size=1).wtPort(x942_b996_x950_b998_s)
    }
    
  }
}
