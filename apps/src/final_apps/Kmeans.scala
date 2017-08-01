import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Kmeans extends PIRApp {
  def main(top:Top) = {
    val x3325_b3620_x3334_b3622_s = Scalar("x3325_b3620_x3334_b3622")
    val x3479_x3502_x3506_v = Vector("x3479_x3502_x3506")
    val x3322_x3471_x3477_v = Vector("x3322_x3471_x3477")
    val x3351_x3433_x3439_v = Vector("x3351_x3433_x3439")
    val x3493_argin = ArgIn("x3493")
    val x3326_x3336_data_v = Vector("x3326_x3336_data")
    val x3320_x3392_x3409_v = Vector("x3320_x3392_x3409")
    val x3425_x3438_v = Vector("x3425_x3438")
    val x3490_b3655_x3498_b3657_s = Scalar("x3490_b3655_x3498_b3657")
    val x3325_b3619_x3334_b3621_s = Scalar("x3325_b3619_x3334_b3621")
    val x3321_x3476_v = Vector("x3321_x3476")
    val x3354_b3626_x3366_b3628_s = Scalar("x3354_b3626_x3366_b3628")
    val x3456_x3463_v = Vector("x3456_x3463")
    val bus_520_v = Vector("bus_520")
    val x3351_x3394_x3409_v = Vector("x3351_x3394_x3409")
    val x3315_oc = OffChip("x3315")
    val x3322_x3451_v = Vector("x3322_x3451")
    val x3322_x3447_x3452_v = Vector("x3322_x3447_x3452")
    val x3321_x3393_x3409_v = Vector("x3321_x3393_x3409")
    val x3490_b3656_x3498_b3658_s = Scalar("x3490_b3656_x3498_b3658")
    val x3385_x3407_s = Scalar("x3385_x3407")
    val x3425_x3446_x3452_v = Vector("x3425_x3446_x3452")
    val x3321_x3484_x3489_v = Vector("x3321_x3484_x3489")
    val x3381_x3422_s = Scalar("x3381_x3422")
    val x3316_oc = OffChip("x3316")
    val x3356_argin = ArgIn("x3356")
    val x3456_x3470_x3477_v = Vector("x3456_x3470_x3477")
    val x3310_argin = ArgIn("x3310")
    val x3311_argin = ArgIn("x3311")
    val x3327_argin = ArgIn("x3327")
    val x3355_x3368_data_v = Vector("x3355_x3368_data")
    val x3354_b3625_x3366_b3627_s = Scalar("x3354_b3625_x3366_b3627")
    val x3322_x3460_x3464_v = Vector("x3322_x3460_x3464")
    val x3516 = Sequential(name="x3516",parent=top) { implicit CU => 
    }
    val x3515 = Sequential(name="x3515",parent=x3516) { implicit CU => 
    }
    val x3320_dsp0 = MemoryPipeline(name="x3320_dsp0",parent="x3515") { implicit CU => 
      val b3631 = CU.temp()
      val b3623 = CU.temp()
      val x3342_x3342 =  VectorFIFO(size=1).wtPort(x3326_x3336_data_v)
      val x3324 = CounterChain.copy("x3344", "x3324")
      val x3387 = CounterChain.copy("x3409", "x3387")
      val x3338 = CounterChain.copy("x3343", "x3338")
      val x3384 = CounterChain.copy("x3424", "x3384")
      val x3320_x3392 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3342_x3342.readPort).rdPort(x3320_x3392_x3409_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3324(0)), Const(96)), op=FixMul, results=List(b3623))
      WAStage(operands=List(b3623, CU.ctr(x3338(0))), op=FixAdd, results=List(x3320_x3392.writeAddr))
      RAStage(operands=List(CU.ctr(x3384(0)), Const(96)), op=FixMul, results=List(b3631))
      RAStage(operands=List(b3631, CU.ctr(x3387(0))), op=FixAdd, results=List(x3320_x3392.readAddr))
    }
    val x3321_dsp0 = MemoryPipeline(name="x3321_dsp0",parent="x3515") { implicit CU => 
      val b3651 = CU.temp()
      val b3653 = CU.temp()
      val x3476_x3476 =  VectorFIFO(size=1).wtPort(x3321_x3476_v)
      val x3482 = CounterChain.copy("x3489", "x3482")
      val x3467 = CounterChain.copy("x3477_0", "x3467")
      val x3321_x3484 =  SRAM(size=1920,banking = NoBanking()).wtPort(x3476_x3476.readPort).rdPort(x3321_x3484_x3489_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3467(0)), Const(96)), op=FixMul, results=List(b3651))
      WAStage(operands=List(b3651, CU.ctr(x3467(1))), op=FixAdd, results=List(x3321_x3484.writeAddr))
      RAStage(operands=List(CU.ctr(x3482(0)), Const(96)), op=FixMul, results=List(b3653))
      RAStage(operands=List(b3653, CU.ctr(x3482(1))), op=FixAdd, results=List(x3321_x3484.readAddr))
    }
    val x3321_dsp1 = MemoryPipeline(name="x3321_dsp1",parent="x3515") { implicit CU => 
      val b3633 = CU.temp()
      val b3651 = CU.temp()
      val x3476_x3476 =  VectorFIFO(size=1).wtPort(x3321_x3476_v)
      val x3387 = CounterChain.copy("x3409", "x3387")
      val x3467 = CounterChain.copy("x3477_0", "x3467")
      val x3384 = CounterChain.copy("x3424", "x3384")
      val x3321_x3393 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3476_x3476.readPort).rdPort(x3321_x3393_x3409_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3467(0)), Const(96)), op=FixMul, results=List(b3651))
      WAStage(operands=List(b3651, CU.ctr(x3467(1))), op=FixAdd, results=List(x3321_x3393.writeAddr))
      RAStage(operands=List(CU.ctr(x3384(0)), Const(96)), op=FixMul, results=List(b3633))
      RAStage(operands=List(b3633, CU.ctr(x3387(0))), op=FixAdd, results=List(x3321_x3393.readAddr))
    }
    val x3322_dsp0 = MemoryPipeline(name="x3322_dsp0",parent="x3454") { implicit CU => 
      val b3649 = CU.temp()
      val b3645 = CU.temp()
      val x3451_x3451 =  VectorFIFO(size=1).wtPort(x3322_x3451_v)
      val x3442 = CounterChain.copy("x3452_0", "x3442")
      val x3467 = CounterChain.copy("x3477_0", "x3467")
      val x3322_x3471 =  SRAM(size=1920,banking = NoBanking()).wtPort(x3451_x3451.readPort).rdPort(x3322_x3471_x3477_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3442(0)), Const(96)), op=FixMul, results=List(b3645))
      WAStage(operands=List(b3645, CU.ctr(x3442(1))), op=FixAdd, results=List(x3322_x3471.writeAddr))
      RAStage(operands=List(CU.ctr(x3467(0)), Const(96)), op=FixMul, results=List(b3649))
      RAStage(operands=List(b3649, CU.ctr(x3467(1))), op=FixAdd, results=List(x3322_x3471.readAddr))
    }
    val x3322_dsp1 = MemoryPipeline(name="x3322_dsp1",parent="x3454") { implicit CU => 
      val b3645 = CU.temp()
      val b3647 = CU.temp()
      val x3451_x3451 =  VectorFIFO(size=1).wtPort(x3322_x3451_v)
      val x3442 = CounterChain.copy("x3452_0", "x3442")
      val x3458 = CounterChain.copy("x3464_0", "x3458")
      val x3322_x3460 =  SRAM(size=1920,banking = NoBanking()).wtPort(x3451_x3451.readPort).rdPort(x3322_x3460_x3464_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3442(0)), Const(96)), op=FixMul, results=List(b3645))
      WAStage(operands=List(b3645, CU.ctr(x3442(1))), op=FixAdd, results=List(x3322_x3460.writeAddr))
      RAStage(operands=List(CU.ctr(x3458(0)), Const(96)), op=FixMul, results=List(b3647))
      RAStage(operands=List(b3647, Const(95)), op=FixAdd, results=List(x3322_x3460.readAddr))
    }
    val x3322_dsp2 = MemoryPipeline(name="x3322_dsp2",parent="x3454") { implicit CU => 
      val b3645 = CU.temp()
      val b3643 = CU.temp()
      val x3451_x3451 =  VectorFIFO(size=1).wtPort(x3322_x3451_v)
      val x3442 = CounterChain.copy("x3452_0", "x3442")
      val x3322_x3447 =  SRAM(size=1920,banking = NoBanking()).wtPort(x3451_x3451.readPort).rdPort(x3322_x3447_x3452_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3442(0)), Const(96)), op=FixMul, results=List(b3645))
      WAStage(operands=List(b3645, CU.ctr(x3442(1))), op=FixAdd, results=List(x3322_x3447.writeAddr))
      RAStage(operands=List(CU.ctr(x3442(0)), Const(96)), op=FixMul, results=List(b3643))
      RAStage(operands=List(b3643, CU.ctr(x3442(1))), op=FixAdd, results=List(x3322_x3447.readAddr))
    }
    val x3344 = StreamController(name="x3344",parent=x3515) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3324 = CounterChain(name = "x3324", ctr1).iter(20)
    }
    val x3335_0 = Pipeline(name="x3335_0",parent=x3344) { implicit CU => 
      val x3329 = CU.temp()
      val x3328 = CU.temp()
      val x3327 =  ScalarBuffer().wtPort(x3327_argin)
      val x3324 = CounterChain.copy("x3344", "x3324")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3324(0)), Const(96)), op=FixMul, results=List(x3328))
      Stage(operands=List(x3328, Const(4)), op=FixMul, results=List(x3329))
      Stage(operands=List(x3329, CU.load(x3327)), op=FixAdd, results=List(CU.scalarOut(x3325_b3619_x3334_b3621_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3325_b3620_x3334_b3622_s)))
    }
    val x3336 = MemoryController(name="x3336",parent=x3344,offchip=x3315_oc, mctpe=TileLoad) { implicit CU => 
      val x3325_b3620_x3336 =  ScalarFIFO(name="size",size=1).wtPort(x3325_b3620_x3334_b3622_s)
      val x3325_b3619_x3336 =  ScalarFIFO(name="offset",size=1).wtPort(x3325_b3619_x3334_b3621_s)
      CU.newVout("data", x3326_x3336_data_v)
    }
    val x3343 = Pipeline(name="x3343",parent=x3344) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3338 = CounterChain(name = "x3338", ctr2).iter(6)
      var stage: List[Stage] = Nil
    }
    val x3478 = Sequential(name="x3478",parent=x3515) { implicit CU => 
      val x3310_x3345 =  ScalarBuffer().wtPort(x3310_argin)
      val ctr3 = Counter(min=Const(0), max=x3310_x3345.load, step=Const(1), par=1) // Counter
      val x3347 = CounterChain(name = "x3347", ctr3).iter(1)
    }
    val x3455 = MetaPipeline(name="x3455",parent=x3478) { implicit CU => 
      val x3311_x3348 =  ScalarBuffer().wtPort(x3311_argin)
      val ctr4 = Counter(min=Const(0), max=x3311_x3348.load, step=Const(1), par=1) // Counter
      val x3350 = CounterChain(name = "x3350", ctr4).iter(960000)
    }
    val x3351_dsp0 = MemoryPipeline(name="x3351_dsp0",parent="x3455") { implicit CU => 
      val b3629 = CU.temp()
      val b3637 = CU.temp()
      val x3376_x3376 =  VectorFIFO(size=1).wtPort(x3355_x3368_data_v)
      val x3370 = CounterChain.copy("x3377", "x3370")
      val x3428 = CounterChain.copy("x3439_0", "x3428")
      val x3380 = CounterChain.copy("x3454", "x3380")
      val x3353 = CounterChain.copy("x3378", "x3353")
      val x3351_x3433 =  SRAM(size=96,banking = Strided(1)).wtPort(x3376_x3376.readPort).rdPort(x3351_x3433_x3439_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3353(0)), Const(96)), op=FixMul, results=List(b3629))
      WAStage(operands=List(b3629, CU.ctr(x3370(0))), op=FixAdd, results=List(x3351_x3433.writeAddr))
      RAStage(operands=List(CU.ctr(x3380(0)), Const(96)), op=FixMul, results=List(b3637))
      RAStage(operands=List(b3637, CU.ctr(x3428(1))), op=FixAdd, results=List(x3351_x3433.readAddr))
    }
    val x3351_dsp1 = MemoryPipeline(name="x3351_dsp1",parent="x3455") { implicit CU => 
      val b3635 = CU.temp()
      val b3629 = CU.temp()
      val x3376_x3376 =  VectorFIFO(size=1).wtPort(x3355_x3368_data_v)
      val x3370 = CounterChain.copy("x3377", "x3370")
      val x3387 = CounterChain.copy("x3409", "x3387")
      val x3380 = CounterChain.copy("x3454", "x3380")
      val x3353 = CounterChain.copy("x3378", "x3353")
      val x3351_x3394 =  SRAM(size=96,banking = Strided(1)).wtPort(x3376_x3376.readPort).rdPort(x3351_x3394_x3409_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3353(0)), Const(96)), op=FixMul, results=List(b3629))
      WAStage(operands=List(b3629, CU.ctr(x3370(0))), op=FixAdd, results=List(x3351_x3394.writeAddr))
      RAStage(operands=List(CU.ctr(x3380(0)), Const(96)), op=FixMul, results=List(b3635))
      RAStage(operands=List(b3635, CU.ctr(x3387(0))), op=FixAdd, results=List(x3351_x3394.readAddr))
    }
    val x3378 = StreamController(name="x3378",parent=x3455) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3353 = CounterChain(name = "x3353", ctr5).iter(1)
    }
    val x3367_0 = Pipeline(name="x3367_0",parent=x3378) { implicit CU => 
      val x3358 = CU.temp()
      val x3359 = CU.temp()
      val x3357 = CU.temp()
      val x3356 =  ScalarBuffer().wtPort(x3356_argin)
      val x3350 = CounterChain.copy("x3455", "x3350")
      val x3353 = CounterChain.copy("x3378", "x3353")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3350(0)), CU.ctr(x3353(0))), op=FixAdd, results=List(x3357))
      Stage(operands=List(x3357, Const(96)), op=FixMul, results=List(x3358))
      Stage(operands=List(x3358, Const(4)), op=FixMul, results=List(x3359))
      Stage(operands=List(x3359, CU.load(x3356)), op=FixAdd, results=List(CU.scalarOut(x3354_b3625_x3366_b3627_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3354_b3626_x3366_b3628_s)))
    }
    val x3368 = MemoryController(name="x3368",parent=x3378,offchip=x3315_oc, mctpe=TileLoad) { implicit CU => 
      val x3354_b3626_x3368 =  ScalarFIFO(name="size",size=1).wtPort(x3354_b3626_x3366_b3628_s)
      val x3354_b3625_x3368 =  ScalarFIFO(name="offset",size=1).wtPort(x3354_b3625_x3366_b3627_s)
      CU.newVout("data", x3355_x3368_data_v)
    }
    val x3377 = Pipeline(name="x3377",parent=x3378) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3370 = CounterChain(name = "x3370", ctr6).iter(6)
      var stage: List[Stage] = Nil
    }
    val x3454 = MetaPipeline(name="x3454",parent=x3455) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3380 = CounterChain(name = "x3380", ctr7).iter(1)
    }
    val x3424 = MetaPipeline(name="x3424",parent=x3454) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3384 = CounterChain(name = "x3384", ctr8).iter(20)
    }
    val x3409 = StreamController(name="x3409",parent=x3424) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3387 = CounterChain(name = "x3387", ctr9).iter(6)
    }
    val x3409_0 = Pipeline(name="x3409_0",parent=x3409) { implicit CU => 
      val x3399 = CU.temp()
      val x3401 = CU.temp()
      val x3400 = CU.temp()
      val x3394_x3394 =  VectorFIFO(size=1).wtPort(x3351_x3394_x3409_v)
      val x3393_x3393 =  VectorFIFO(size=1).wtPort(x3321_x3393_x3409_v)
      val x3392_x3392 =  VectorFIFO(size=1).wtPort(x3320_x3392_x3409_v)
      val x3347 = CounterChain.copy("x3478", "x3347")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3347(0)), Const(0)), op=FixEql, results=List(x3399))
      Stage(operands=List(x3399, CU.load(x3392_x3392), CU.load(x3393_x3393)), op=Mux, results=List(x3400))
      Stage(operands=List(CU.load(x3394_x3394), x3400), op=FixSub, results=List(x3401))
      Stage(operands=List(x3401, x3401), op=FixMul, results=List(CU.vecOut(bus_520_v)))
    }
    val x3409_1 = Pipeline(name="x3409_1",parent=x3409) { implicit CU => 
      val rr520 =  VectorFIFO(size=1).wtPort(bus_520_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr520)), op=Bypass, results=List(CU.reduce))
      val (_, rr522) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr522), op=Bypass, results=List(CU.scalarOut(x3385_x3407_s)))
    }
    val x3423_0 = Pipeline(name="x3423_0",parent=x3424) { implicit CU => 
      val x3384 = CounterChain.copy("x3424", "x3384")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3384(0))), op=Bypass, results=List(CU.scalarOut(x3381_x3422_s)))
    }
    val x3425_dsp0 = MemoryPipeline(name="x3425_dsp0",parent="x3454") { implicit CU => 
      val b3639 = CU.temp()
      val b3641 = CU.temp()
      val x3438_x3438 =  VectorFIFO(size=1).wtPort(x3425_x3438_v)
      val x3442 = CounterChain.copy("x3452_0", "x3442")
      val x3428 = CounterChain.copy("x3439_0", "x3428")
      val x3425_x3446 =  SRAM(size=1920,banking = NoBanking()).wtPort(x3438_x3438.readPort).rdPort(x3425_x3446_x3452_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3428(0)), Const(96)), op=FixMul, results=List(b3639))
      WAStage(operands=List(b3639, CU.ctr(x3428(1))), op=FixAdd, results=List(x3425_x3446.writeAddr))
      RAStage(operands=List(CU.ctr(x3442(0)), Const(96)), op=FixMul, results=List(b3641))
      RAStage(operands=List(b3641, CU.ctr(x3442(1))), op=FixAdd, results=List(x3425_x3446.readAddr))
    }
    val x3439_0 = Pipeline(name="x3439_0",parent=x3454) { implicit CU => 
      val x3436 = CU.temp()
      val x3381_x3434 =  ScalarBuffer().wtPort(x3381_x3422_s)
      val x3433_x3433 =  VectorFIFO(size=1).wtPort(x3351_x3433_x3439_v)
      val ctr10 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3428 = CounterChain(name = "x3428", ctr10, ctr11).iter(120)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3428(0)), CU.load(x3381_x3434)), op=FixEql, results=List(x3436))
      Stage(operands=List(x3436, CU.load(x3433_x3433), Const(0)), op=Mux, results=List(CU.vecOut(x3425_x3438_v)))
    }
    val x3452_0 = Pipeline(name="x3452_0",parent=x3454) { implicit CU => 
      val x3447_x3447 =  VectorFIFO(size=1).wtPort(x3322_x3447_x3452_v)
      val x3446_x3446 =  VectorFIFO(size=1).wtPort(x3425_x3446_x3452_v)
      val ctr12 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3442 = CounterChain(name = "x3442", ctr12, ctr13).iter(1920)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3446_x3446), CU.load(x3447_x3447)), op=FixAdd, results=List(CU.vecOut(x3322_x3451_v)))
    }
    val x3456_dsp0 = MemoryPipeline(name="x3456_dsp0",parent="x3478") { implicit CU => 
      val x3463_x3463 =  VectorFIFO(size=1).wtPort(x3456_x3463_v)
      val x3458 = CounterChain.copy("x3464_0", "x3458")
      val x3467 = CounterChain.copy("x3477_0", "x3467")
      val x3456_x3470 =  SRAM(size=20,banking = NoBanking()).wtPort(x3463_x3463.readPort).rdPort(x3456_x3470_x3477_v).rdAddr(x3467(0)).wtAddr(x3458(0))
      var stage: List[Stage] = Nil
    }
    val x3464_0 = Pipeline(name="x3464_0",parent=x3478) { implicit CU => 
      val x3460_x3460 =  VectorFIFO(size=1).wtPort(x3322_x3460_x3464_v)
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3458 = CounterChain(name = "x3458", ctr14).iter(20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3460_x3460), Const(1)), op=FixMax, results=List(CU.vecOut(x3456_x3463_v)))
    }
    val x3477_0 = Pipeline(name="x3477_0",parent=x3478) { implicit CU => 
      val x3472 = CU.temp()
      val x3474 = CU.temp()
      val x3471_x3471 =  VectorFIFO(size=1).wtPort(x3322_x3471_x3477_v)
      val x3470_x3470 =  VectorFIFO(size=1).wtPort(x3456_x3470_x3477_v)
      val ctr15 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3467 = CounterChain(name = "x3467", ctr15, ctr16).iter(120)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3470_x3470), Const(0)), op=FixEql, results=List(x3472))
      Stage(operands=List(CU.load(x3471_x3471), CU.load(x3470_x3470)), op=FixDiv, results=List(x3474))
      Stage(operands=List(x3472, Const(0), x3474), op=Mux, results=List(CU.vecOut(x3321_x3476_v)))
    }
    val x3479_dsp0 = MemoryPipeline(name="x3479_dsp0",parent="x3515") { implicit CU => 
      val x3486 = CU.temp()
      val x3488_x3488 =  VectorFIFO(size=1).wtPort(x3321_x3484_x3489_v)
      val x3482 = CounterChain.copy("x3489", "x3482")
      val x3501 = CounterChain.copy("x3506", "x3501")
      val x3479_x3502 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3488_x3488.readPort).rdPort(x3479_x3502_x3506_v).rdAddr(x3501(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3482(0)), Const(96)), op=FixMul, results=List(x3486))
      WAStage(operands=List(x3486, CU.ctr(x3482(1))), op=FixAdd, results=List(x3479_x3502.writeAddr))
    }
    val x3489 = Pipeline(name="x3489",parent=x3515) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3482 = CounterChain(name = "x3482", ctr17, ctr18).iter(1920)
      var stage: List[Stage] = Nil
    }
    val x3514 = StreamController(name="x3514",parent=x3515) { implicit CU => 
    }
    val x3507 = Sequential(name="x3507",parent=x3514) { implicit CU => 
    }
    val x3499_0 = Pipeline(name="x3499_0",parent=x3507) { implicit CU => 
      val x3493 =  ScalarBuffer().wtPort(x3493_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x3493)), op=FixAdd, results=List(CU.scalarOut(x3490_b3655_x3498_b3657_s)))
      Stage(operands=List(Const(7680)), op=Bypass, results=List(CU.scalarOut(x3490_b3656_x3498_b3658_s)))
    }
    val x3506 = Pipeline(name="x3506",parent=x3507) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(1920), step=Const(1), par=16) // Counter
      val x3501 = CounterChain(name = "x3501", ctr19).iter(120)
      var stage: List[Stage] = Nil
    }
    val x3508 = MemoryController(name="x3508",parent=x3514,offchip=x3316_oc, mctpe=TileStore) { implicit CU => 
      val x3490_b3656_x3508 =  ScalarFIFO(name="size",size=1).wtPort(x3490_b3656_x3498_b3658_s)
      val x3491_x3508 =  VectorFIFO(name="data",size=1).wtPort(x3479_x3502_x3506_v)
      val x3490_b3655_x3508 =  ScalarFIFO(name="offset",size=1).wtPort(x3490_b3655_x3498_b3657_s)
    }
    
  }
}
