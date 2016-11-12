import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object MatMultCompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x3446_scalar = Scalar()
    val x2889_argin = ArgIn()
    val x3516_scalar = Scalar()
    val x3382_vector = Vector()
    val x2888_argin = ArgIn()
    val x3498_vector = Vector()
    val x3373_vector = Vector()
    val x3410_vector = Vector()
    val x2896_oc = OffChip()
    val x2887_argin = ArgIn()
    val x3383_vector = Vector()
    val x3414_scalar = Scalar()
    val x3387_scalar = Scalar()
    val x2901_oc = OffChip()
    val x2899_oc = OffChip()
    val x3422_mc_mc = MemoryController(TileLoad, x2899_oc)
    val x3524_mc_mc = MemoryController(TileStore, x2901_oc)
    val x3395_mc_mc = MemoryController(TileLoad, x2896_oc)
    val x3531 = ComputeUnit(name="x3531", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3531_unitCC = CounterChain(name = "x3531_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3528 = ComputeUnit(name="x3528", parent=x3531, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2984 = (Const("0i").out, CU.scalarIn(stage0, x2887_argin).out, Const("192i").out) // Counter
      val x2985 = (Const("0i").out, CU.scalarIn(stage0, x2888_argin).out, Const("192i").out) // Counter
      val x2986 = CounterChain(name = "x2986", x2984, x2985)
    }
    val x3497 = ComputeUnit(name="x3497", parent=x3528, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3374 = (Const("0i").out, CU.scalarIn(stage0, x2889_argin).out, Const("384i").out) // Counter
      val x3375 = CounterChain(name = "x3375", x3374)
    }
    val x3409 = ComputeUnit(name="x3409", parent=x3497, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3384 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3385 = CounterChain(name = "x3385", x3384)
    }
    val x3393 = UnitComputeUnit(name ="x3393", parent=x3409, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr150 = CU.temp
      val tr149 = CU.temp
      val x3393_unitCC = CounterChain(name = "x3393_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      val x3385 = CounterChain.copy(x3409, "x3385")
      val x3375 = CounterChain.copy(x3497, "x3375")
      val x2986 = CounterChain.copy(x3528, "x2986")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2986(0)), CU.ctr(stage(0), x3385(0))), op=FixAdd, results=List(CU.temp(stage(1), tr149)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr149), CU.scalarIn(stage(1), x2889_argin)), op=FixMul, results=List(CU.temp(stage(2), tr150)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr150), CU.ctr(stage(2), x3375(0))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3387_scalar)))
    }
    val x3395 = TileTransfer(name="x3395", parent=x3409, memctrl=x3395_mc_mc, mctpe=TileLoad, deps=List(x3393), vec=x3383_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3395_ctr = (Const("0l").out, Const("384i").out, Const("1l").out) // Counter
      val x3395_cc = CounterChain(name = "x3395_cc", x3395_ctr)
      val x3396 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x3397 = CounterChain(name = "x3397", x3396).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3387_scalar), CU.ctr(stage(0), x3395_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3395_mc_mc.saddr)))
    }
    val x3436 = ComputeUnit(name="x3436", parent=x3497, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3411 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x3412 = CounterChain(name = "x3412", x3411)
    }
    val x3420 = UnitComputeUnit(name ="x3420", parent=x3436, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr158 = CU.temp
      val tr157 = CU.temp
      val x3412 = CounterChain.copy(x3436, "x3412")
      val x3420_unitCC = CounterChain(name = "x3420_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      val x3375 = CounterChain.copy(x3497, "x3375")
      val x2986 = CounterChain.copy(x3528, "x2986")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3375(0)), CU.ctr(stage(0), x3412(0))), op=FixAdd, results=List(CU.temp(stage(1), tr157)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr157), CU.scalarIn(stage(1), x2888_argin)), op=FixMul, results=List(CU.temp(stage(2), tr158)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr158), CU.ctr(stage(2), x2986(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3414_scalar)))
    }
    val x3422 = TileTransfer(name="x3422", parent=x3436, memctrl=x3422_mc_mc, mctpe=TileLoad, deps=List(x3420), vec=x3410_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3422_ctr = (Const("0l").out, Const("192i").out, Const("1l").out) // Counter
      val x3422_cc = CounterChain(name = "x3422_cc", x3422_ctr)
      val x3423 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3424 = CounterChain(name = "x3424", x3423).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3414_scalar), CU.ctr(stage(0), x3422_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3422_mc_mc.saddr)))
    }
    val x3475 = ComputeUnit(name="x3475", parent=x3497, tpe = MetaPipeline, deps=List(x3409, x3436)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3439 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3440 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3441 = CounterChain(name = "x3441", x3439, x3440)
    }
    val x3467 = ComputeUnit(name="x3467", parent=x3475, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr171 = CU.temp
      val tr165 = CU.temp
      val tr176 = CU.temp
      val tr168 = CU.temp
      val x3412 = CounterChain.copy(x3436, "x3412")
      val x3441 = CounterChain.copy(x3475, "x3441")
      val x3397 = CounterChain.copy(x3395, "x3397")
      val x3424 = CounterChain.copy(x3422, "x3424")
      val x3385 = CounterChain.copy(x3409, "x3385")
      val x3444 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x3445 = CounterChain(name = "x3445", x3444)
      val x3380_x3454 = SRAM(size = 73728, swapCtr = x3385(0), writeCtr = x3397(0), banking = Strided(1), doubleBuffer = true).wtPort(x3383_vector)
      val x3381_x3458 = SRAM(size = 73728, swapCtr = x3412(0), writeCtr = x3424(0), banking = Diagonal(1,192), doubleBuffer = true).wtPort(x3410_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3381_x3458))
      Stage(stage(1), operands=List(x3412(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr165)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr165), CU.ctr(stage(1), x3424(0))), op=FixAdd, results=List(x3381_x3458.writeAddr))
      stage = stage0 +: WAStages(2, List(x3380_x3454))
      Stage(stage(1), operands=List(x3385(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr168)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr168), CU.ctr(stage(1), x3397(0))), op=FixAdd, results=List(x3380_x3454.writeAddr))
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3441(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr171)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr171), CU.ctr(stage(1), x3445(0))), op=FixAdd, results=List(x3380_x3454.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x3445(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr176)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr176), CU.ctr(stage(3), x3441(1))), op=FixAdd, results=List(x3381_x3458.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x3380_x3454), x3381_x3458.load), op=FltMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr183) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr183), op=Bypass, results=List(CU.scalarOut(stage(6), x3446_scalar)))
    }
    val x3473 = UnitComputeUnit(name ="x3473", parent=x3475, deps=List(x3467)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3473_unitCC = CounterChain(name = "x3473_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3446_scalar)), op=Bypass, results=List(CU.vecOut(stage(1), x3382_vector)))
    }
    val x3495 = ComputeUnit(name="x3495", parent=x3497, tpe = Pipe, deps=List(x3475)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr197 = CU.temp
      val tr187 = CU.temp
      val tr199 = CU.temp
      val tr191 = CU.temp
      val tr201 = CU.temp
      val tr198 = CU.temp
      val x3441 = CounterChain.copy(x3475, "x3441")
      val x3376 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3377 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3378 = CounterChain(name = "x3378", x3376, x3377)
      val x3375 = CounterChain.copy(x3497, "x3375")
      val x3473_unitCC = CounterChain.copy(x3473, "x3473_unitCC")
      val x3382_x3483 = SRAM(size = 36864, swapCtr = x3441(0), writeCtr = x3473_unitCC(0), banking = Strided(1), doubleBuffer = true).wtPort(x3382_vector)
      val x3373_x3484 = SRAM(size = 36864, swapCtr = x3378(0), writeCtr = x3378(0), banking = Strided(1), doubleBuffer = false)
      val wr204 = CU.wtAddr(x3373_x3484)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3382_x3483))
      Stage(stage(1), operands=List(x3441(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr187)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr187), CU.ctr(stage(1), x3441(1))), op=FixAdd, results=List(x3382_x3483.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3378(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr191)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr191), CU.ctr(stage(1), x3378(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr204), x3373_x3484.readAddr, x3382_x3483.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x3375(0)), CU.scalarIn(stage(2), x2889_argin)), op=FixLt, results=List(CU.temp(stage(3), tr197)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x3378(0)), Const("192i")), op=FixLt, results=List(CU.temp(stage(4), tr198)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr197), CU.temp(stage(4), tr198)), op=BitAnd, results=List(CU.temp(stage(5), tr199)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr199), CU.load(stage(5), x3382_x3483), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr201)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr201), CU.load(stage(6), x3373_x3484)), op=FltAdd, results=List(CU.vecOut(stage(7), x3373_vector), CU.store(stage(7), x3373_x3484)))
    }
    val x3526 = ComputeUnit(name="x3526", parent=x3528, tpe = MetaPipeline, deps=List(x3497)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3499 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3500 = CounterChain(name = "x3500", x3499)
    }
    val x3515 = ComputeUnit(name="x3515", parent=x3526, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr206 = CU.temp
      val tr209 = CU.temp
      val x3502 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3503 = CounterChain(name = "x3503", x3502)
      val x3500 = CounterChain.copy(x3526, "x3500")
      val x3378 = CounterChain.copy(x3495, "x3378")
      val x3373_x3508 = SRAM(size = 36864, swapCtr = x3378(0), writeCtr = x3378(0), banking = Strided(1), doubleBuffer = true).wtPort(x3373_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3373_x3508))
      Stage(stage(1), operands=List(x3378(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr206)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr206), CU.ctr(stage(1), x3378(1))), op=FixAdd, results=List(x3373_x3508.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3500(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr209)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr209), CU.ctr(stage(1), x3503(0))), op=FixAdd, results=List(x3373_x3508.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x3373_x3508)), op=Bypass, results=List(CU.vecOut(stage(3), x3498_vector)))
    }
    val x3522 = UnitComputeUnit(name ="x3522", parent=x3526, deps=List(x3515)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr217 = CU.temp
      val tr216 = CU.temp
      val x2986 = CounterChain.copy(x3528, "x2986")
      val x3500 = CounterChain.copy(x3526, "x3500")
      val x3522_unitCC = CounterChain(name = "x3522_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2986(0)), CU.ctr(stage(0), x3500(0))), op=FixAdd, results=List(CU.temp(stage(1), tr216)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr216), CU.scalarIn(stage(1), x2888_argin)), op=FixMul, results=List(CU.temp(stage(2), tr217)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr217), CU.ctr(stage(2), x2986(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3516_scalar)))
    }
    val x3524 = TileTransfer(name="x3524", parent=x3526, memctrl=x3524_mc_mc, mctpe=TileStore, deps=List(x3522), vec=x3498_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3524_ctr = (Const("0l").out, Const("192i").out, Const("1l").out) // Counter
      val x3524_cc = CounterChain(name = "x3524_cc", x3524_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3516_scalar), CU.ctr(stage(0), x3524_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3524_mc_mc.saddr)))
    }
    
  }
}
