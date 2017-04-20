import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x766_x837_argout = ArgOut("x766_x837")
    val x778_argin = ArgIn("x778")
    val x775_b851_x781_b853_s = Scalar("x775_b851_x781_b853")
    val x775_b850_x781_b852_s = Scalar("x775_b850_x781_b852")
    val x798_x805_data_v = Vector("x798_x805_data")
    val x776_x782_s = Scalar("x776_x782")
    val x796_b855_x802_b857_s = Scalar("x796_b855_x802_b857")
    val x773_x821_v = Vector("x773_x821")
    val x763_oc = OffChip("x763")
    val x799_argin = ArgIn("x799")
    val x777_x784_data_v = Vector("x777_x784_data")
    val x796_b854_x802_b856_s = Scalar("x796_b854_x802_b856")
    val x773_x792_v = Vector("x773_x792")
    val x774_x813_v = Vector("x774_x813")
    val x774_x822_v = Vector("x774_x822")
    val x765_oc = OffChip("x765")
    val x818_x828_s = Scalar("x818_x828")
    val x760_argin = ArgIn("x760")
    val x797_x803_s = Scalar("x797_x803")
    val x839 = Sequential(name="x839",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x839_unit = CounterChain(name = "x839_unit", ctr1)
    }
    val x835 = MetaPipeline(name="x835",parent=x839) { implicit CU => 
      val x760_x770 =  ScalarBuffer().wtPort(x760_argin)
      val ctr2 = Counter(min=Const(0), max=x760_x770.load, step=Const(640), par=1) // Counter
      val x772 = CounterChain(name = "x772", ctr2)
    }
    val x773_dsp0 = MemoryPipeline(name="x773_dsp0",parent="x835") { implicit CU => 
      val x788 = CounterChain.copy("x793", "x788")
      val x820 = CounterChain.copy("x829", "x820")
      val x773_x821 =  SRAM(size = 640,banking = Strided(1)).wtPort(x773_x792_v).rdPort(x773_x821_v).rdAddr(x820(0)).wtAddr(x788(0))
      var stage: List[Stage] = Nil
    }
    val x774_dsp0 = MemoryPipeline(name="x774_dsp0",parent="x835") { implicit CU => 
      val x809 = CounterChain.copy("x814", "x809")
      val x820 = CounterChain.copy("x829", "x820")
      val x774_x822 =  SRAM(size = 640,banking = Strided(1)).wtPort(x774_x813_v).rdPort(x774_x822_v).rdAddr(x820(0)).wtAddr(x809(0))
      var stage: List[Stage] = Nil
    }
    val x795 = StreamController(name="x795",parent=x835) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x795_unit = CounterChain(name = "x795_unit", ctr3)
    }
    val x783 = Pipeline(name="x783",parent=x795) { implicit CU => 
      val x779 = CU.temp
      val x489 = CU.temp
      val x778 =  ScalarBuffer().wtPort(x778_argin)
      val x772 = CounterChain.copy("x835", "x772")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x783_unit = CounterChain(name = "x783_unit", ctr4)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x772(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x489)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x489), CU.load(stage(1), x778)), op=FixAdd, results=List(CU.scalarOut(stage(2), x775_b850_x781_b852_s), CU.temp(stage(2), x779)))
      Stage(stage(3), operands=List(Const(2560)), op=Bypass, results=List(CU.scalarOut(stage(3), x775_b851_x781_b853_s)))
      Stage(stage(4), operands=List(Const(640)), op=Bypass, results=List(CU.scalarOut(stage(4), x776_x782_s)))
    }
    val x784 = MemoryController(name="x784",parent=x795,offchip=x763_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x775_b851_x781_b853_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x775_b850_x781_b852_s)
      CU.mcvecs += "data" -> x777_x784_data_v
    }
    val x794 = MetaPipeline(name="x794",parent=x795) { implicit CU => 
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x794_unit = CounterChain(name = "x794_unit", ctr5)
    }
    val x793 = Pipeline(name="x793",parent=x794) { implicit CU => 
      val x777_x789 =  VectorFIFO(size = 1).wtPort(x777_x784_data_v)
      val ctr7 = Counter(min=Const(0), max=Const(640), step=Const(1), par=16) // Counter
      val x788 = CounterChain(name = "x788", ctr7)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x777_x789.load), op=Bypass, results=List(CU.vecOut(stage(1), x773_x792_v)))
    }
    val x816 = StreamController(name="x816",parent=x835) { implicit CU => 
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x816_unit = CounterChain(name = "x816_unit", ctr8)
    }
    val x804 = Pipeline(name="x804",parent=x816) { implicit CU => 
      val x489 = CU.temp
      val x800 = CU.temp
      val x799 =  ScalarBuffer().wtPort(x799_argin)
      val x772 = CounterChain.copy("x835", "x772")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x804_unit = CounterChain(name = "x804_unit", ctr9)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x772(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x489)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x489), CU.load(stage(1), x799)), op=FixAdd, results=List(CU.scalarOut(stage(2), x796_b854_x802_b856_s), CU.temp(stage(2), x800)))
      Stage(stage(3), operands=List(Const(2560)), op=Bypass, results=List(CU.scalarOut(stage(3), x796_b855_x802_b857_s)))
      Stage(stage(4), operands=List(Const(640)), op=Bypass, results=List(CU.scalarOut(stage(4), x797_x803_s)))
    }
    val x805 = MemoryController(name="x805",parent=x816,offchip=x765_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x796_b855_x802_b857_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x796_b854_x802_b856_s)
      CU.mcvecs += "data" -> x798_x805_data_v
    }
    val x815 = MetaPipeline(name="x815",parent=x816) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x815_unit = CounterChain(name = "x815_unit", ctr10)
    }
    val x814 = Pipeline(name="x814",parent=x815) { implicit CU => 
      val x798_x810 =  VectorFIFO(size = 1).wtPort(x798_x805_data_v)
      val ctr12 = Counter(min=Const(0), max=Const(640), step=Const(1), par=16) // Counter
      val x809 = CounterChain(name = "x809", ctr12)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x798_x810.load), op=Bypass, results=List(CU.vecOut(stage(1), x774_x813_v)))
    }
    val x829 = Pipeline(name="x829",parent=x835) { implicit CU => 
      val x826 = CU.temp
      val ar79 = CU.accum(init = Const(0))
      val ctr13 = Counter(min=Const(0), max=Const(640), step=Const(1), par=1) // Counter
      val x820 = CounterChain(name = "x820", ctr13)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x773_x821_v), CU.vecIn(stage(0), x774_x822_v)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), x826)))
      val (rs1, rr123) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(stage(2), operands=List(rr123), op=Bypass, results=List(CU.scalarOut(stage(2), x818_x828_s)))
    }
    val x834 = Pipeline(name="x834",parent=x835) { implicit CU => 
      val ar6 = CU.accum(init = Const(0))
      val x818_x831 =  ScalarBuffer().wtPort(x818_x828_s)
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x834_unit = CounterChain(name = "x834_unit", ctr14)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(x818_x831.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr126) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(stage(2), operands=List(rr126), op=Bypass, results=List(CU.scalarOut(stage(2), x766_x837_argout)))
    }
    
  }
}
