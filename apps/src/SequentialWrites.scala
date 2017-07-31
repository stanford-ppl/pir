import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SequentialWrites extends PIRApp {
  def main(top:Top) = {
    val x907_oc = OffChip("x907")
    val x954_x968_v = Vector("x954_x968")
    val x913_b999_x920_b1001_s = Scalar("x913_b999_x920_b1001")
    val x913_b1000_x920_b1002_s = Scalar("x913_b1000_x920_b1002")
    val x914_x922_data_v = Vector("x914_x922_data")
    val x953_b1003_x961_b1005_s = Scalar("x953_b1003_x961_b1005")
    val x932_x939_s = Scalar("x932_x939")
    val x953_b1004_x961_b1006_s = Scalar("x953_b1004_x961_b1006")
    val x908_oc = OffChip("x908")
    val x912_x965_x969_s = Scalar("x912_x965_x969")
    val x909_argin = ArgIn("x909")
    val x912_x945_x951_s = Scalar("x912_x945_x951")
    val x916_da = DRAMAddress("x916", "x907")
    val x932_x943_x951_s = Scalar("x932_x943_x951")
    val x957_da = DRAMAddress("x957", "x908")
    val x912_x950_s = Scalar("x912_x950")
    val x975 = Sequential(name="x975",parent=top) { implicit CU => 
      val x975_unit = CounterChain(name = "x975_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x912_dsp0 = MemoryPipeline(name="x912_dsp0",parent="x952") { implicit CU => 
      val x950_x950 = ScalarFIFO(size=1).wtPort(x912_x950_s)
      val x942 = CounterChain.copy("x951_0", "x942")
      val x964 = CounterChain.copy("x969_0", "x964")
      val x912_x965 = SRAM(size=16,banking = Strided(1)).wtPort(x950_x950.readPort).rdPort(x912_x965_x969_s).rdAddr(x964(0)).wtAddr(x942(0))
    }
    val x912_dsp1 = MemoryPipeline(name="x912_dsp1",parent="x952") { implicit CU => 
      val x950_x950 = ScalarFIFO(size=1).wtPort(x912_x950_s)
      val x942 = CounterChain.copy("x951_0", "x942")
      val x912_x945 = SRAM(size=16,banking = Strided(1)).wtPort(x950_x950.readPort).rdPort(x912_x945_x951_s).rdAddr(x942(0)).wtAddr(x942(0))
    }
    val x929 = StreamController(name="x929",parent=x975) { implicit CU => 
      val x929_unit = CounterChain(name = "x929_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x921_0 = Pipeline(name="x921_0",parent=x929) { implicit CU => 
      val x916 = ScalarBuffer().wtPort(x916_da)
      val x921_unit = CounterChain(name = "x921_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x916)), op=FixAdd, results=List(CU.scalarOut(x913_b999_x920_b1001_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x913_b1000_x920_b1002_s)))
    }
    val x922 = MemoryController(name="x922",parent=x929,offchip=x907_oc, mctpe=TileLoad) { implicit CU => 
      val x913_b1000_x922 = ScalarFIFO(name="size",size=1).wtPort(x913_b1000_x920_b1002_s)
      val x913_b999_x922 = ScalarFIFO(name="offset",size=1).wtPort(x913_b999_x920_b1001_s)
      CU.newVout("data", x914_x922_data_v)
    }
    val x952 = MetaPipeline(name="x952",parent=x975) { implicit CU => 
      val ctr2 = Counter(min=Const(1), max=Const(6), step=Const(1), par=1) // Counter
      val x931 = CounterChain(name = "x931", ctr2).iter(5)
    }
    val x932_dsp0 = MemoryPipeline(name="x932_dsp0",parent="x952") { implicit CU => 
      val x939_x939 = ScalarFIFO(size=1).wtPort(x932_x939_s)
      val x934 = CounterChain.copy("x940_0", "x934")
      val x942 = CounterChain.copy("x951_0", "x942")
      val x932_x943 = SRAM(size=16,banking = Strided(1)).wtPort(x939_x939.readPort).rdPort(x932_x943_x951_s).rdAddr(x942(0)).wtAddr(x934(0))
    }
    val x940_0 = Pipeline(name="x940_0",parent=x952) { implicit CU => 
      val x909_x935 = ScalarBuffer().wtPort(x909_argin)
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x934 = CounterChain(name = "x934", ctr3).iter(16)
      Stage(operands=List(CU.load(x909_x935), CU.ctr(x934(0))), op=FixAdd, results=List(CU.scalarOut(x932_x939_s)))
    }
    val x951_0 = Pipeline(name="x951_0",parent=x952) { implicit CU => 
      val x943_x943 = ScalarFIFO(size=1).wtPort(x932_x943_x951_s)
      val x945_x945 = ScalarFIFO(size=1).wtPort(x912_x945_x951_s)
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x942 = CounterChain(name = "x942", ctr4).iter(16)
      Stage(operands=List(CU.load(x943_x943), CU.load(x945_x945)), op=FixAdd, results=List(CU.scalarOut(x912_x950_s)))
    }
    val x974 = StreamController(name="x974",parent=x975) { implicit CU => 
      val x974_unit = CounterChain(name = "x974_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x970 = Sequential(name="x970",parent=x974) { implicit CU => 
      val x970_unit = CounterChain(name = "x970_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x962_0 = Pipeline(name="x962_0",parent=x970) { implicit CU => 
      val x957 = ScalarBuffer().wtPort(x957_da)
      val x962_unit = CounterChain(name = "x962_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x957)), op=FixAdd, results=List(CU.scalarOut(x953_b1003_x961_b1005_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x953_b1004_x961_b1006_s)))
    }
    val x969_0 = Pipeline(name="x969_0",parent=x970) { implicit CU => 
      val x965_x965 = ScalarFIFO(size=1).wtPort(x912_x965_x969_s)
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x964 = CounterChain(name = "x964", ctr5).iter(1)
      Stage(operands=List(CU.load(x965_x965)), op=Bypass, results=List(CU.vecOut(x954_x968_v)))
    }
    val x971 = MemoryController(name="x971",parent=x974,offchip=x908_oc, mctpe=TileStore) { implicit CU => 
      val x953_b1004_x971 = ScalarFIFO(name="size",size=1).wtPort(x953_b1004_x961_b1006_s)
      val x953_b1003_x971 = ScalarFIFO(name="offset",size=1).wtPort(x953_b1003_x961_b1005_s)
      val x954_x971 = VectorFIFO(name="data",size=1).wtPort(x954_x968_v)
    }
    
  }
}
